package test.get;
import consts.UrlParamsValues;
import io.qameta.allure.*;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import test.BaseTest;

import static consts.CardsEndpoints.*;
import static consts.UrlParamsValues.MY_BOARD_IN_PROGRESS_LIST_ID;
import static io.qameta.allure.Allure.step;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class GetCardsTest extends BaseTest {
    @Test
    @Epic("TrelloApi")
    @Feature("Работа с досками")
    @Story("Получение списков на доске")
    @Description("Проверка успешного получения списков на доске по id")
    @Link(name = "trello api docs", url = "https://developer.atlassian.com/cloud/trello/rest/api-group-actions/#api-group-actions")
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
    @Story("Получить карточки списка")
    @Severity(SeverityLevel.CRITICAL) // severity теста
    @DisplayName("GetCards - у существующего списка вернутся карточки")
    public void checkGetCards(){

        Allure.parameter("listId", MY_BOARD_IN_PROGRESS_LIST_ID);//отдельно выделяем параметры в отчете(любые)

        var response = step("делаем запрос и проверяем статус", ()->
                requestWithAuth()
                        .log().method()
                        .pathParam("id", MY_BOARD_IN_PROGRESS_LIST_ID)
                        .get(GET_ALL_CARDS_URL)
                        .then()
                        .statusCode(200)
                        .extract().response() // extract сохраняем ответ в переменную response
                );

        Allure.addAttachment("cards.json", "application/json",
                response.prettyPrint(), ".json"); //prettyPrint цветной json в аллюре отчете
//        requestWithAuth()
//                .log().method()
//                .pathParam("id", MY_BOARD_IN_PROGRESS_LIST_ID)
//                .get(GET_ALL_CARDS_URL)
//                .then()
//                .statusCode(200)
//                .log().body();
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
                .body("desc",equalTo("NONE"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schemas/get_card.json"))
                .log().body();
    }
}
