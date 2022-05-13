package requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import utils.SetProperties;

import static io.restassured.RestAssured.given;


public class DeleteRequest extends SetProperties {
    public DeleteRequest() {
        super();
    }

    PostRequest postRequest = new PostRequest();

    private String deleteMoviePath = "/movie/";

    public void deleteRating(int movieId){
        Response response = given()
                .queryParam("api_key", getApi_key())
                .queryParam("session_id", postRequest.createSession())
                .when()
                .delete(getBase_url()+deleteMoviePath+movieId+"/rating")
                .then()
                .statusCode(200)
                .extract().response();
        Assert.assertEquals("true", response.jsonPath().getString("success"));

    }



}
