package test.delete;

import consts.UrlParamsValues;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.BaseTest;

import java.util.Map;

import static consts.BoardsEndpoints.*;

public class DeleteBoardTest extends BaseTest {
    private String boardIdToDelete;

    @Test
    public void checkDeleteBoard(){
        requestWithAuth()
                .pathParam("id", boardIdToDelete)
                .delete(DELETE_BOARD_URL)
                .then()
                .statusCode(200)
                .log().body();
        requestWithAuth()
                .log().method()
                .queryParams("fields", "id,name")
                .pathParam("id", UrlParamsValues.USER_NAME)
                .get(GET_ALL_BOARDS_URL)
                .then()
                .statusCode(200)
                .body("id", Matchers.not(Matchers.hasItem(boardIdToDelete)))
                .log().body();
    }

    @BeforeEach
    public void createBoard(){
        boardIdToDelete = requestWithAuth()
                .log().method()
                .body(Map.of("name", "boardName"))
                .contentType(ContentType.JSON)
                .post(CREATE_BOARD_URL)
                .body().jsonPath().get("id");
    }
}
