package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.http.ContentType;

import java.util.Map;

import static consts.BoardsEndpoints.CREATE_BOARD_URL;
import static consts.Endpoint.*;
import static utils.AuthorizationRequestProvider.requestWithAuth;


public class Hooks {

    private final ScenarioContext scenarioContext;

    public Hooks(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @After("@deleteBoard")
    public void deleteBoard(){
        requestWithAuth()
                .pathParam("id", scenarioContext.getBoardId())
                .delete(DELETE_A_BOARD.getUrl())
                .then()
                .statusCode(200);
    }

    @After("@deleteCard")
    public void deleteCard(){
        requestWithAuth()
                .pathParam("id", scenarioContext.getCardId())
                .delete(DELETE_A_CARD.getUrl())
                .then()
                .statusCode(200);
    }

    @Before("@createBoard")
    public void createBoard(){
        String boardId = requestWithAuth()
                .body(Map.of("name", "Board_to_delete"))
                .contentType(ContentType.JSON)
                .post(CREATE_A_BOARD.getUrl())
                .then()
                .statusCode(200)
                .extract().body().jsonPath().get("id");
        scenarioContext.setBoardId(boardId);

    }

}
