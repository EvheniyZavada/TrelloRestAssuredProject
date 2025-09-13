package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class TrelloApiAssertSteps {

    private final ScenarioContext scenarioContext;

    public TrelloApiAssertSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @And("the response body is equal to {string}")
    public void theResponseBodyIsEqualTo(String expectedValue){
        Assertions.assertEquals(expectedValue, scenarioContext.getResponse().body().asString());
    }

    @Then("the response status code is {int}")
    public void theResponseStatusCodeIs(int expectedStatusCode){
        scenarioContext.getResponse().then().statusCode(expectedStatusCode);
    }

    @And("the response matches '{}' schema")
    public void theJsonSchemaIsValid(String pathToSchema){
        scenarioContext.getResponse().then().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/" + pathToSchema));
    }

    @And("body value has the following values by paths:")
    public void theResponseNameOfBoard(DataTable dataTable){
        List<Map<String,String>> rows = dataTable.asMaps();
        for (Map<String,String> row : rows){
            scenarioContext.getResponse().then().body(row.get("path"), equalTo(row.get("expected_value")));
        }
    }

    @And("body value has one the following values by paths:")
    public void theResponseNameOfUpdatedBoard(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps();
        for (Map<String, String> row : rows) {
            scenarioContext.getResponse().then().body(row.get("path"), Matchers.hasItem(row.get("expected_value")));
        }
    }

    @And("body value has not the following values by paths:")
    public void theResponseIdOfDeletedBoard(DataTable dataTable) {
        for(Map.Entry<String,String> row : dataTable.asMap().entrySet()){
            String rowValue = row.getValue();
            String expectedValue = rowValue.equals("created_board_id")? scenarioContext.getBoardId() : rowValue;
            scenarioContext.getResponse().then().body("id", Matchers.not(Matchers.hasItem(expectedValue)));
        }
    }

    @And("the response time is less than {long}")
    public void theResponseTimeIs(long expectedTime){
        scenarioContext.getResponse().then().time(lessThan(expectedTime));
    }

}
