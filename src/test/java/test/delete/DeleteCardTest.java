package test.delete;

import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import test.BaseTest;

import java.util.Map;

import static consts.CardsEndpoints.*;
import static consts.UrlParamsValues.MY_BOARD_IN_PROGRESS_LIST_ID;

public class DeleteCardTest extends BaseTest {

    private String cardIdToDelete;

    @Test
    public void checkDeleteCard(){
            requestWithAuth()
                    .log().method()
                    .pathParam("id", cardIdToDelete)
                    .delete(DELETE_CARD_URL)
                    .then()
                    .statusCode(200)
                    .log().body();
            requestWithAuth()
                    .log().method()
                    .pathParam("id", MY_BOARD_IN_PROGRESS_LIST_ID)
                    .queryParams("fields", "name,id")
                    .get(GET_ALL_CARDS_URL)
                    .then()
                    .statusCode(200)
                    .body("id", Matchers.not(Matchers.hasItem(cardIdToDelete)))
                    .log().body();

    }

    @BeforeEach
    public void createCard(){
        cardIdToDelete = requestWithAuth()
                .log().method()
                .contentType(ContentType.JSON)
                .body(Map.of("idList", MY_BOARD_IN_PROGRESS_LIST_ID, "name", "cardName"))
                .post(CREATE_CARD_URL)
                .body().jsonPath().get("id");
    }
}
