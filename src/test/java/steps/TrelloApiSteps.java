package steps;

import consts.UrlParamsValues;
import io.cucumber.core.options.CurlOption;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.runner.Request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class TrelloApiSteps {

    private RequestSpecification request;
    private Response response;

    protected static RequestSpecification requestWithAuth(){
        RestAssured.baseURI = "https://api.trello.com/1";
        return RestAssured.given()
                .queryParams(Map.of("key", UrlParamsValues.VALID_KEY,
                        "token", UrlParamsValues.VALID_TOKEN))
                .header("Accept", "application/json");
    }

    protected static RequestSpecification requestWithoutAuth(){
        RestAssured.baseURI = "https://api.trello.com/1";
        return RestAssured.given();
    }

    @Given("request with authorization")
    public void requestWithAuthorization(){
        request = requestWithAuth();
    }

    @And("the request has query params:")
    public void theRequestHasQueryParams(DataTable dataTable){ //dataTable позволяет исп параметры в виде Map
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
            case GET -> response = request.get(endpoint);
            case POST -> response = request.post(endpoint);
            case PUT -> response = request.put(endpoint);
            case DELETE -> response = request.delete(endpoint);
            default -> throw new RuntimeException();
        }
    }

    @Then("the response status code is {int}")
    public void theResponseStatusCodeIs(int expectedStatusCode){
        response.then().statusCode(expectedStatusCode);
    }

    @And("the response time is less than {long}")
    public void theResponseTimeIs(long expectedTime){
        response.then().time(lessThan(expectedTime));
    }

    @And("body value has the following values by paths:")
    public void theResponseNameOfBoard(DataTable dataTable){
        List<Map<String,String>> rows = dataTable.asMaps();
        for (Map<String,String> row : rows){
            response.then().body(row.get("path"), equalTo(row.get("expected_value")));
        }
    }

    @And("the jsonSchema is '{}' valid")
    public void theJsonSchemaIsValid(String pathToSchema){
        response.then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/" + pathToSchema));
    }

}
