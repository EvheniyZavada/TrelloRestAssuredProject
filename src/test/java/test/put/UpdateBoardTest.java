package test.put;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.joda.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import test.BaseTest;

import java.util.Map;

import static consts.BoardsEndpoints.GET_ALL_BOARDS_URL;
import static consts.BoardsEndpoints.UPDATE_BOARD_URL;
import static consts.UrlParamsValues.BOARD_ID_TO_UPDATE;
import static consts.UrlParamsValues.USER_NAME;

public class UpdateBoardTest extends BaseTest {
    @Test
    public void checkUpdateBoard(){

        String newBoardName = "Updated_Board " + LocalDateTime.now();
        Response response = requestWithAuth()
                .log().method()
                .contentType(ContentType.JSON)
                .pathParam("id", BOARD_ID_TO_UPDATE)
                .body(Map.of("name", newBoardName))
                .put(UPDATE_BOARD_URL);
        response
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo(newBoardName));
        requestWithAuth()
                .queryParams("fields", "name,id")
                .pathParam("id", USER_NAME)
                .get(GET_ALL_BOARDS_URL)
                .then()
                .statusCode(200)
                .body("name", Matchers.hasItem(newBoardName))
                .log().body();

    }
}
