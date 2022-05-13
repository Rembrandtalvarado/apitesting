package requests;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import utils.SetProperties;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest extends SetProperties {
    private String token_path = "/authentication/token/new";

    public GetRequest(){
        super();
    }

    public String generateToken(){
        Response response = given()
                .queryParam("api_key", getApi_key())
                .when().get(getBase_url()+token_path)
                .then()
                .statusCode(200)
                .extract().response();
        Assert.assertEquals("true", response.jsonPath().getString("success"));
        return response.jsonPath().getString("request_token");
    }

    public void getListMovies(int listId){
        ValidatableResponse response = given()
                .queryParam("api_key", getApi_key())
                .when()
                .get(getBase_url()+"/list/"+listId)
                .then()
                .log().body();

    }

}
