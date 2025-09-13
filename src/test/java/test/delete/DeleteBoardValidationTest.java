package test.delete;

import holders.AuthValidationArgumentsHolder;
import holders.BoardIdValidationArgumentsHolder;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import providers.AuthDeleteBoardArgumentsProvider;
import providers.DeleteBoardIdValidationArgumentsProvider;
import test.BaseTest;

import static consts.BoardsEndpoints.DELETE_BOARD_URL;
import static consts.UrlParamsValues.BOARD_ID;

public class DeleteBoardValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(AuthDeleteBoardArgumentsProvider.class)
    public void checkDeleteBoardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments){
        Response response = requestWithoutAuth()
                .log().method()
                .queryParams(validationArguments.getAuthParams())
                .pathParam("id",BOARD_ID)
                .delete(DELETE_BOARD_URL);
        response
                .then()
                .statusCode(401)
                .log().body();
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }

    @ParameterizedTest
    @ArgumentsSource(DeleteBoardIdValidationArgumentsProvider.class)
    public void checkDeleteBoardWithInvalidId(BoardIdValidationArgumentsHolder argumentsHolder){
        Response response = requestWithAuth()
                .log().method()
                .pathParams(argumentsHolder.getPathParams())
                .delete(DELETE_BOARD_URL);
        response
                .then()
                .statusCode(argumentsHolder.getStatusCode())
                .log().body();
        Assertions.assertEquals(argumentsHolder.getErrorMessage(), response.body().asString());
    }
}
