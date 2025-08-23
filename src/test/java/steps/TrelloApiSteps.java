package steps;

import consts.UrlParamsValues;
import io.cucumber.core.options.CurlOption;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.runner.Request;

import java.util.HashMap;
import java.util.List;
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

    @And("the request has query params:")
    public void theRequestHasQueryParams(DataTable dataTable){ //dataTable возволяет исп параметры в виде Map
        Map<String,String> queryParams = dataTable.asMap();
        request = request.queryParams(queryParams);
    }

    @And("the request has path params:")
    public void theRequestHasPathParams(DataTable dataTable){
        Map<String,String> pathParams = new HashMap<>();
        List<Map<String,String>> rows = dataTable.asMaps();
        for (Map<String,String> row : rows){
            pathParams.put(row.get("name"),row.get("value"));
        }
        request = request.pathParams(pathParams);
    }

    @When("the '{}' request is sent to {string} endpoint")
    public void theRequestIsSentToEndpoint(CurlOption.HttpMethod method,String endpoint){
        switch (method){
            case GET -> request.get(endpoint);
            case POST -> request.post(endpoint);
            case PUT -> request.put(endpoint);
            case DELETE -> request.delete(endpoint);
            default -> throw new RuntimeException();
        }
    }

    @Then("the response status code is {int}")
    public void theResponseStatusCodeIs(int expectedStatusCode){
        response.then().statusCode(expectedStatusCode);
    }

}
