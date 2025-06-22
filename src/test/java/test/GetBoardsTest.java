package test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class GetBoardsTest {

    @Test
    public void checkGetBoards(){
        RestAssured.given()//предусловие для вызова конструкций
                .log().method()//log настраивается вариативно
                .baseUri("https://api.trello.com/1")
                .queryParams(Map.of("key", TrelloAuthInfo.getKey(), "token", TrelloAuthInfo.getToken()))
                .header("Accept","application/json")
                .queryParams("fields", "id,name")
                .pathParam("id", "zavada1997@gmail.com")
                .get("/members/{id}/boards")
                .then()
                .statusCode(200)
                .log().body();// получить тело запроса в консоль
    }

    @Test
    public void checkGetBoard(){
        RestAssured.given()
                .log().method()
                .baseUri("https://api.trello.com/1")
                .pathParam("id", "68499967310c9b0128bb22c1")
                .queryParams(Map.of("key", TrelloAuthInfo.getKey(), "token", TrelloAuthInfo.getToken()))
                .get("/boards/{id}?key=APIKey&token=APIToken")
                .then()
                .statusCode(200)
//        var timeInSeconds = response.getTimeIn(TimeUnit.SECONDS);
//        System.out.println("response time " + timeInSeconds + " seconds");
//              .assertThat()
                .time(lessThan(3000L))
                .body("name",equalTo("доска"))//проверка значений body
                .log().body();
    }
}
