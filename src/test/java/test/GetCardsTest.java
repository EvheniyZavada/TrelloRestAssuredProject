package test;

import io.restassured.RestAssured;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class GetCardsTest {
    private static final Log log = LogFactory.getLog(GetCardsTest.class);
    @Test
    public void checkGetLists(){
        RestAssured.given()
                .log().method()
                .baseUri("https://api.trello.com/1")
                .queryParams(Map.of("key", TrelloAuthInfo.getKey(),"token", TrelloAuthInfo.getToken()))
                .pathParam("id", "68499967310c9b0128bb22c1")
                .get("/boards/{id}/lists")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void checkGetCards(){
        RestAssured.given()
                .log().method()
                .baseUri("https://api.trello.com/1")
                .queryParams(Map.of("key", TrelloAuthInfo.getKey(),"token", TrelloAuthInfo.getToken()))
                .pathParam("id", "68499967310c9b0128bb2315")
                .get("/lists/{id}/cards")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void checkGetCard(){
        RestAssured.given()
                .log().method()
                .baseUri("https://api.trello.com/1")
                .queryParams(Map.of("key",TrelloAuthInfo.getKey(), "token", TrelloAuthInfo.getToken()))
                .pathParam("id", "68345fadb7caea7fdd9104e3")
                .get("/cards/{id}")
                .then()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("desc",equalTo("WOW"))
                .log().body();
    }
}
