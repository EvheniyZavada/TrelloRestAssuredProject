package test.post;

import consts.UrlParamsValues;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import test.BaseTest;

import java.time.LocalDateTime;
import java.util.Map;

import static consts.BoardsEndpoints.*;
import static org.hamcrest.Matchers.equalTo;

public class CreateBoardTest extends BaseTest {

    private String createdBoardId;

    @Test
    public void checkCreateBoard(){

        String boardName = "CreatedBoard1" + LocalDateTime.now();
         Response response = requestWithAuth()
                .log().method()
                .body(Map.of("name", boardName))
                 .contentType(ContentType.JSON)
                .post(CREATE_BOARD_URL);
         createdBoardId = response.jsonPath().get("id");
         response
                .then()//validatable response
                .statusCode(200)
                .body("name",equalTo(boardName))
                .log().body();
        requestWithAuth()
                .log().method()
                .queryParams("fields", "id,name")
                .pathParam("id", UrlParamsValues.USER_NAME)
                .get(GET_ALL_BOARDS_URL)
                .then()
                .statusCode(200)
                .body("name", Matchers.hasItem(boardName));
    }

    @AfterEach//исп для очистки временных данных после тестов(вспомогательный атрибут для пост условий)
    public void deleteCreatedBoard(){
        requestWithAuth()
                .pathParam("id", createdBoardId)
                .delete(DELETE_BOARD_URL)
                .then()
                .statusCode(200);
    }
}
