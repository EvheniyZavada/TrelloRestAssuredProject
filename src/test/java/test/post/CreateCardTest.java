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

import static consts.CardsEndpoints.*;
import static org.hamcrest.Matchers.equalTo;

public class CreateCardTest extends BaseTest {

    private String createdCardId;

    @Test
    public void checkCreateCard(){
        String cardName = "createdCardByRestAssured" + LocalDateTime.now();
        Response response = requestWithAuth()
                .log().method()
                .contentType(ContentType.JSON) //must have(как в постмане нужно тип тела выбрать)
              //  .queryParams("fields", "id,name,desc")
                .body(Map.of("idList", UrlParamsValues.MY_BOARD_IN_PROGRESS_LIST_ID, "name", cardName, "desc", "*_*"))
                .post(CREATE_CARD_URL);
        createdCardId = response.jsonPath().get("id");
        response
                .then()
                .statusCode(200)
                .body("name",equalTo(cardName))
                .log().body();
        requestWithAuth()
                .pathParam("id", UrlParamsValues.MY_BOARD_IN_PROGRESS_LIST_ID)
                .header("Accept", "application/json")
                .queryParams("fields", "id,name")
                .get(GET_ALL_CARDS_URL)
                .then()
                .body("name", Matchers.hasItem(cardName))
                .statusCode(200)
                .log().body();
    }

    @AfterEach
    public void deleteCreatedCard(){
        requestWithAuth()
                .pathParam("id", createdCardId)
                .delete(DELETE_CARD_URL)
                .then()
                .statusCode(200);
    }
}
