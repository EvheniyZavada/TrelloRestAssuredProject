package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class TwoNumbersAdditionSteps {

    private int numberOne;

    private int numberTwo;

    private int result;

    @Given("user has number one as {int}")
    public void userHasNumberOneAs(Integer int1){
        numberOne = int1;
    }

    @And("user has number two as 20")
    public void userHasNumberTwoAs(){
        numberTwo = 20;
    }

    @When("user adds number one and number two")
    public void userAddsNumbers(){
        result = numberOne + numberTwo;
    }

    @Then("the result is 30")
    public void theResultIs(){
        Assertions.assertEquals(30, result);
    }

}
