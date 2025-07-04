package test.get;
import consts.ConstsHolder;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;
import test.BaseTest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class GetCardsTest extends BaseTest {
    @Test
    public void checkGetLists(){
        requestWithAuth()
                .log().method()
                .pathParam("id", ConstsHolder.myBoardId)
                .get(ConstsHolder.getListsEndpoint)
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void checkGetCards(){
        requestWithAuth()
                .log().method()
                .pathParam("id", ConstsHolder.myBoardInProgressListId)
                .get(ConstsHolder.getCardsEndpoint)
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void checkGetCard(){
        requestWithAuth()
                .log().method()
                .pathParam("id", ConstsHolder.theirBoardCardId)
                .get(ConstsHolder.getCardEndpoint)
                .then()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("desc",equalTo("WOW"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_card.json"))
                .log().body();
    }
}
