package test.get;
import consts.ConstsHolder;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;
import test.BaseTest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class GetBoardsTest extends BaseTest {

    @Test
    public void checkGetBoards(){
        requestWithAuth()
                .log().method()//log настраивается вариативно
                .queryParams("fields", "id,name")
                .pathParam("id", ConstsHolder.userName)
                .get(ConstsHolder.getBoardsEndpoint)
                .then()
                .statusCode(200)
                .log().body();// получить тело запроса в консоль
    }

    @Test
    public void checkGetBoard(){
        requestWithAuth()
                .log().method()
                .pathParam("id", ConstsHolder.boardId)
                .queryParams("fields", "id,name")
                .get(ConstsHolder.getBoardEndpoint)
                .then()
                .statusCode(200)
//        var timeInSeconds = response.getTimeIn(TimeUnit.SECONDS);
//        System.out.println("response time " + timeInSeconds + " seconds");
//              .assertThat()
                .time(lessThan(3000L))
                .body("name",equalTo("доска"))//проверка значений body
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_board.json"))//проверка схемы
                .log().body();
    }
}
