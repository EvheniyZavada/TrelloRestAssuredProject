package test;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class CreateCardTest extends BaseTest{

    private String createdCardId;

    @Test
    public void checkGetLists(){
        requestWithAuth()
                .pathParam("id", "684497a8f96fc4515a307c4d")
                .queryParams("fields", "id,name")
                .get("/boards/{id}/lists")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void checkCreateCard(){

        String cardName = "createdCardByRestAssured" + LocalDateTime.now();

        Response response = requestWithAuth()
                .log().method()
                .contentType("application/json") //must have(как в постмане нужно тип тела выбрать)
              //  .queryParams("fields", "id,name,desc")
                .body(Map.of("idList","684497a8f96fc4515a307c9f", "name", cardName, "desc", "*_*"))
                .post("/cards");
        createdCardId = response.jsonPath().get("id");
        response
                .then()
                .statusCode(200)
                .body("name",equalTo(cardName))
                .log().body();
    }
    @AfterEach
    public void deleteCreatedCard(){
        requestWithAuth()
                .pathParam("id", createdCardId)
                .delete("/cards/{id}")
                .then()
                .statusCode(200);
    }
}
