package test.put;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import test.BaseTest;

import java.time.LocalDateTime;
import java.util.Map;

import static consts.CardsEndpoints.GET_CARD_URL;
import static consts.CardsEndpoints.UPDATE_CARD_URL;
import static consts.UrlParamsValues.CARD_ID_TO_UPDATE;

public class UpdateCardTest extends BaseTest {

    @Test
    public void checkUpdateCard(){

        String updatedCardName = "updatedNameOfCard" + LocalDateTime.now();
        Response response = requestWithAuth()
                .log().method()
                .pathParam("id", CARD_ID_TO_UPDATE)
                .contentType(ContentType.JSON)
                .body(Map.of("name", updatedCardName,"desc", "NONE"))
                .put(UPDATE_CARD_URL);
        response
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo(updatedCardName))
                .log().body();
        requestWithAuth()
                .log().method()
                .pathParam("id", CARD_ID_TO_UPDATE)
                .queryParams("fields", "id,name")
                .get(GET_CARD_URL)
                .then()
                .statusCode(200)
                .body("name", Matchers.equalTo(updatedCardName))
                .log().body();
    }
}
