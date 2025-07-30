package steps;

import consts.UrlParamsValues;
import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.runner.Request;

import java.util.Map;

public class TrelloApiSteps {

    private RequestSpecification request;
    private Response response;

    protected static RequestSpecification requestWithAuth(){
        return RestAssured.given()
                .queryParams(Map.of("key", UrlParamsValues.VALID_KEY,
                        "token", UrlParamsValues.VALID_TOKEN))
                .header("Accept", "application/json");
    }

    protected static RequestSpecification requestWithoutAuth(){
        return RestAssured.given();
    }

    @Given("request with authorization")
    public void requestWithAuthorization(){
        request = requestWithAuth();
    }

}
