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
                .queryParams(Map.of("key", "4d9dd97638a81eaec3d5a7f125b6b562",
                       "token", "ATTAac7d6572f06502abadc634407cef8927be972cf00c41777a053365e7ffcb6101C7527E9B"))
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
                .queryParams(Map.of("key", "4d9dd97638a81eaec3d5a7f125b6b562",
                        "token", "ATTAac7d6572f06502abadc634407cef8927be972cf00c41777a053365e7ffcb6101C7527E9B"))
                .get("/boards/{id}?key=APIKey&token=APIToken")
                .then()
                .statusCode(200)
//        var timeInSeconds = response.getTimeIn(TimeUnit.SECONDS);
//        System.out.println("response time " + timeInSeconds + " seconds");
//              .assertThat()
                .time(lessThan(3000l))
                .body("name",equalTo("доска"))//проверка значений body
                .log().body();
    }
}
