package test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.Map;
import static org.hamcrest.Matchers.equalTo;

public class CreateBoardTest extends BaseTest {

    private String createdBoardId;

    @Test
    public void checkCreateBoard(){

        String boardName = "";// + LocalDateTime.now();
         Response response = requestWithAuth()
                .log().method()
                .body(Map.of("name", boardName))
                 .contentType(ContentType.JSON)
                .post("/boards/");
         createdBoardId = response.jsonPath().get("id");
         response
                .then()//validatable response
                .statusCode(200)
                .body("name",equalTo(boardName))
                .log().body();
        requestWithAuth()
                .log().method()
                .queryParams("fields", "id,name")
                .pathParam("id", "zavada1997@gmail.com")
                .get("/members/{id}/boards")
                .then()
                .statusCode(200)
                .body("name", Matchers.hasItem(boardName));
    }

    @AfterEach//исп для очистки временных данных после тестов(вспомогательный атрибут для пост условий)
    public void deleteCreatedBoard(){
        requestWithAuth()
                .pathParam("id", createdBoardId)
                .delete("/boards/{id}")
                .then()
                .statusCode(200);
    }
}
