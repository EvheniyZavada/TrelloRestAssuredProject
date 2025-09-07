package test.put;

import holders.AuthValidationArgumentsHolder;
import holders.CardIdValidationArgumentsHolder;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import providers.AuthCardValidationArgumentsProvider;
import providers.AuthUpdateCardValidationArgumentsProvider;
import providers.UpdateCardIdValidationArgumentsProvider;
import test.BaseTest;

import javax.imageio.spi.RegisterableService;

import java.util.Map;

import static consts.CardsEndpoints.UPDATE_CARD_URL;
import static consts.UrlParamsValues.CARD_ID_TO_UPDATE;

public class UpdateCardValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(AuthUpdateCardValidationArgumentsProvider.class)
    public void checkUpdateCardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments){
        Response response = requestWithoutAuth()
                .log().method()
                .pathParams("id", CARD_ID_TO_UPDATE)
                .queryParams(validationArguments.getAuthParams())
                .contentType(ContentType.JSON)
                .body(Map.of("name", "noname"))
                .put(UPDATE_CARD_URL);
        response
                .then()
                .statusCode(401)
                .log().body();
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }

    @ParameterizedTest
    @ArgumentsSource(UpdateCardIdValidationArgumentsProvider.class)
    public void checkUpdateCardWithInvalidId(CardIdValidationArgumentsHolder validationArguments) {
        Response response = requestWithAuth()
                .log().method()
                .pathParams(validationArguments.getPathParams())
                .contentType(ContentType.JSON)
                .body(Map.of("desc", "new desq"))
                .put(UPDATE_CARD_URL);
        response
                .then()
                .statusCode(validationArguments.getStatusCode())
                .log().body();
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }
}
