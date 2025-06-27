package test;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class GetCardsTest extends  BaseTest{
    @Test
    public void checkGetLists(){
        requestWithAuth()
                .log().method()
                .pathParam("id", "68499967310c9b0128bb22c1")
                .get("/boards/{id}/lists")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void checkGetCards(){
        requestWithAuth()
                .log().method()
                .pathParam("id", "68499967310c9b0128bb2315")
                .get("/lists/{id}/cards")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
    public void checkGetCard(){
        requestWithAuth()
                .log().method()
                .pathParam("id", "68345fadb7caea7fdd9104e3")
                .get("/cards/{id}")
                .then()
                .statusCode(200)
                .time(lessThan(3000L))
                .body("desc",equalTo("WOW"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_card.json"))
                .log().body();
    }
}
