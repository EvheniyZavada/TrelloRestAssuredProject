package test.post;

import consts.UrlParamsValues;
import holders.AuthValidationArgumentsHolder;
import holders.CardNameValidationArgumentsHolder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import providers.AuthCreateCardValidationArgumentsProvider;
import providers.CardNameValidationArgumentsProvider;
import test.BaseTest;

import java.util.Map;

import static consts.CardsEndpoints.CREATE_CARD_URL;

public class CreateCardValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(AuthCreateCardValidationArgumentsProvider.class)
    public void checkCreateCardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments){
        Response response = requestWithoutAuth()
                .log().method()
                .contentType(ContentType.JSON)
                .queryParams(validationArguments.getAuthParams())
                .body(Map.of("idList", UrlParamsValues.MY_BOARD_IN_PROGRESS_LIST_ID, "name", "My_name"))
                .post(CREATE_CARD_URL);
        response
                .then()
                .statusCode(401)
                .log().body();
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }

    @ParameterizedTest
    @ArgumentsSource(CardNameValidationArgumentsProvider.class)
    public void checkCreateCardWithInvalidName(CardNameValidationArgumentsHolder validationArgumentsHolder){
        Response response = requestWithAuth()
                .log().method()
                .contentType(ContentType.JSON)
                .body(validationArgumentsHolder.getBodyParams())
                .post(CREATE_CARD_URL);
        response
                .then()
                .statusCode(400)
                .log().body();
        Assertions.assertEquals(validationArgumentsHolder.getErrorMessage(), response.body().asString());
    }
}
