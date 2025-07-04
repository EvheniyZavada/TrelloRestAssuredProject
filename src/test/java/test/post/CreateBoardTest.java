package test.post;

import consts.ConstsHolder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import test.BaseTest;

import java.time.LocalDateTime;
import java.util.Map;
import static org.hamcrest.Matchers.equalTo;

public class CreateBoardTest extends BaseTest {

    private String createdBoardId;

    @Test
    public void checkCreateBoard(){

        String boardName = "CreatedBoard" + LocalDateTime.now();
         Response response = requestWithAuth()
                .log().method()
                .body(Map.of("name", boardName))
                 .contentType(ContentType.JSON)
                .post(ConstsHolder.createBoardEndpoint);
         createdBoardId = response.jsonPath().get("id");
         response
                .then()//validatable response
                .statusCode(200)
                .body("name",equalTo(boardName))
                .log().body();
        requestWithAuth()
                .log().method()
                .queryParams("fields", "id,name")
                .pathParam("id", ConstsHolder.userName)
                .get(ConstsHolder.getBoardsEndpoint)
                .then()
                .statusCode(200)
                .body("name", Matchers.hasItem(boardName));
    }

    @AfterEach//исп для очистки временных данных после тестов(вспомогательный атрибут для пост условий)
    public void deleteCreatedBoard(){
        requestWithAuth()
                .pathParam("id", createdBoardId)
                .delete(ConstsHolder.deleteBoardEndpoint)
                .then()
                .statusCode(200);
    }
}
