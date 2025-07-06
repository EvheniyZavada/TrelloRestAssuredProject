package test.get;
import consts.UrlParamsValues;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;
import test.BaseTest;

import static consts.CardsEndpoints.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class GetCardsTest extends BaseTest {
    @Test
    public void checkGetLists(){
        requestWithAuth()
                .log().method()
                .pathParam("id", UrlParamsValues.MY_BOARD_ID)
                .get(GET_ALL_LISTS_URL)
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void checkGetCards(){
        requestWithAuth()
                .log().method()
                .pathParam("id", UrlParamsValues.MY_BOARD_IN_PROGRESS_LIST_ID)
                .get(GET_ALL_CARDS_URL)
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void checkGetCard(){
        requestWithAuth()
                .log().method()
                .pathParam("id", UrlParamsValues.THEIR_BOARD_CARD_ID)
                .get(GET_CARD_URL)
                .then()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("desc",equalTo("WOW"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_card.json"))
                .log().body();
    }
}
