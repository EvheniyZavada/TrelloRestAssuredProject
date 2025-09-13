package test.delete;

import holders.AuthValidationArgumentsHolder;
import holders.CardIdValidationArgumentsHolder;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import providers.AuthDeleteCardValidationArgumentsProvider;
import providers.DeleteCardIdValidationArgumentsProvider;
import test.BaseTest;
import static consts.CardsEndpoints.DELETE_CARD_URL;
import static consts.UrlParamsValues.CARD_ID_TO_UPDATE;

public class DeleteCardValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(AuthDeleteCardValidationArgumentsProvider.class)
    public void checkDeleteCardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments){
        Response response = requestWithoutAuth()
                .log().method()
                .queryParams(validationArguments.getAuthParams())
                .pathParam("id", CARD_ID_TO_UPDATE)
                .delete(DELETE_CARD_URL);
        response
                .then()
                .statusCode(401);
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }

    @ParameterizedTest
    @ArgumentsSource(DeleteCardIdValidationArgumentsProvider.class)
    public void checkDeleteCardWithInvalidId(CardIdValidationArgumentsHolder validationArguments){
        Response response = requestWithAuth()
                .log().method()
                .pathParams(validationArguments.getPathParams())
                .delete(DELETE_CARD_URL);
        response
                .then()
                .statusCode(validationArguments.getStatusCode());
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }
}
