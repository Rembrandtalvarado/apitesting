package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.json.JSONObject;
import org.testng.Assert;
import utils.SetProperties;

import static io.restassured.RestAssured.given;

public class PostRequest extends SetProperties {
    GetRequest getRequest = new GetRequest();
    JSONObject jsonObject = new JSONObject();

    private String validateTokenPath = "/authentication/token/validate_with_login";
    private String createSessionPath = "/authentication/session/new";
    private String rateMoviePath = "/movie/";
    private String createListPath = "/list";


    public PostRequest(){
        super();
    }

    public String validateToken(){
        jsonObject
                .put("username",getUsername())
                .put("password", getPassword())
                .put("request_token", getRequest.generateToken());
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key",getApi_key())
                .body(jsonObject.toString())
                .when()
                .post(getBase_url()+validateTokenPath)
                .then()
                .statusCode(200)
                .extract().response();
        Assert.assertEquals("true", response.jsonPath().getString("success"));
        return response.jsonPath().getString("request_token");
    }

    public String createSession(){
        jsonObject
                .put("request_token", validateToken());
       Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key", getApi_key())
                .body(jsonObject.toString())
                .when()
                .post(getBase_url()+createSessionPath)
                .then()
               .statusCode(200).extract().response();

        Assert.assertEquals("true", response.jsonPath().getString("success"));
       return response.jsonPath().getString("session_id");

    }

    public void rateMovie(int idMovie, int rate){
        jsonObject
                .put("value", rate);
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key", getApi_key())
                .queryParam("session_id", createSession())
                .body(jsonObject.toString())
                .when()
                .post(getBase_url()+rateMoviePath+idMovie+"/rating")
                .then()
                .statusCode(201)
                .extract().response();
    Assert.assertEquals("true", response.jsonPath().getString("success"));

    }

    public int createList(){
        String listName="Juana";
        String listDescription = "la loca";
        String listLanguage = "en";
        jsonObject
                .put("name", listName)
                .put("description", listDescription)
                .put("language", listLanguage);
        Response response = given()
                .contentType(ContentType.JSON)
                .queryParam("api_key", getApi_key())
                .queryParam("session_id", createSession())
                .body(jsonObject.toString())
                .when()
                .post(getBase_url() + createListPath)
                .then()
                .statusCode(201)
                .extract().response();

        Assert.assertEquals("true", response.jsonPath().getString("success"));
        Assert.assertEquals(1, response.jsonPath().getInt("status_code"));
        return response.jsonPath().getInt("list_id");
    }
}
