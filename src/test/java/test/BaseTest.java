package test;
import consts.ConstsHolder;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

import java.util.Map;

public class BaseTest {

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "https://api.trello.com/1";
    }

    protected static RequestSpecification requestWithAuth(){
        return RestAssured.given()
                .queryParams(Map.of("key", ConstsHolder.TrelloKeyValue,
                        "token", ConstsHolder.TrelloTokenValue))
                .header("Accept", "application/json");
    }

    protected static RequestSpecification requestWithoutAuth(){
        return RestAssured.given();
    }
}
