package test;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class GetCardsTest {

    @Test
    public void checkGetCards(){
        RestAssured.given()
                .log().method()
                .baseUri("https://api.trello.com/1")
                .queryParams(Map.of("key","4d9dd97638a81eaec3d5a7f125b6b562",
                                    "token", "ATTAac7d6572f06502abadc634407cef8927be972cf00c41777a053365e7ffcb6101C7527E9B"))
                .pathParam("id", "68345f96a7da9a0cf7c72917")
                .get("/lists/{id}/cards")
                .then()
                .statusCode(200)
                .log().body();
    }
    @Test
    public void checkGetCard(){
        RestAssured.given()
                .log().method()
                .baseUri("https://api.trello.com/1")
                .queryParams(Map.of("key","4d9dd97638a81eaec3d5a7f125b6b562",
                                    "token", "ATTAac7d6572f06502abadc634407cef8927be972cf00c41777a053365e7ffcb6101C7527E9B"))
                .pathParam("id", "68345fadb7caea7fdd9104e3")
                .get("/cards/{id}")
                .then()
                .statusCode(200)
                .time(lessThan(3000l))
                .body("desc",equalTo("WOW"))
                .log().body();
    }
}
