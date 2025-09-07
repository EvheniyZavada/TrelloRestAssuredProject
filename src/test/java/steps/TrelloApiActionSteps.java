package steps;

import consts.Endpoint;
import consts.UrlParamsValues;
import io.cucumber.core.options.CurlOption;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrelloApiActionSteps {

    private final ScenarioContext scenarioContext;

    public TrelloApiActionSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }


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

    @Given("request {with} authorization")
    public void requestWithAuthorization(boolean withAuth){
       scenarioContext.setRequest(withAuth ? requestWithAuth() : requestWithoutAuth());
    }

//    @Given("request without authorization")
//    public void requestWithoutAuthorization(){
//        scenarioContext.setRequest(requestWithoutAuth());
//    }

    @And("the request has query params:")
    public void theRequestHasQueryParams(Map<String, String> queryParams){ //dataTable позволяет исп параметры в виде Map
        scenarioContext.setRequest(scenarioContext.getRequest().queryParams(queryParams));
    }

    @And("the request has path params:")
    public void theRequestHasPathParams(DataTable dataTable){
        Map<String,String> pathParams = new HashMap<>();
        List<Map<String,String>> rows = dataTable.asMaps();
        for (Map<String,String> row : rows){
            pathParams.put(row.get("name"),row.get("value"));
        }
        scenarioContext.setRequest(scenarioContext.getRequest().pathParams(pathParams));
    }

    @When("the '{}' request is sent to '{endpoint}' endpoint")
    public void theRequestIsSentToEndpoint(CurlOption.HttpMethod method, Endpoint endpoint){
        String url = endpoint.getUrl();
        switch (method){
            case GET -> scenarioContext.setResponse(scenarioContext.getRequest().get(url));
            case POST -> scenarioContext.setResponse(scenarioContext.getRequest().post(url));
            case PUT -> scenarioContext.setResponse(scenarioContext.getRequest().put(url));
            case DELETE -> scenarioContext.setResponse(scenarioContext.getRequest().delete(url));
            default -> throw new RuntimeException();
        }
    }


    @And("the request has headers:")
    public void theRequestHasHeader(DataTable dataTable){
        scenarioContext.setRequest(scenarioContext.getRequest().headers(dataTable.asMap()));
    }

    @And("the request has body params:")
    public void theRequestHasBodyParams(DataTable dataTable){
        scenarioContext.setRequest(scenarioContext.getRequest().body(dataTable.asMap()));
    }




}
