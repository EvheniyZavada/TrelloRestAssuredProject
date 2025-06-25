package test;
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
                .queryParams(Map.of("key", "4d9dd97638a81eaec3d5a7f125b6b562",
                        "token", "ATTAac7d6572f06502abadc634407cef8927be972cf00c41777a053365e7ffcb6101C7527E9B"))
                .header("Accept", "application/json");
    }

    protected static RequestSpecification requestWithoutAuth(){
        return RestAssured.given();
    }
}
