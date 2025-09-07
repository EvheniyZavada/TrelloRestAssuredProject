package test.put;

import holders.AuthValidationArgumentsHolder;
import holders.BoardIdValidationArgumentsHolder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import providers.AuthUpdateBoardValidationArgumentsProvider;
import providers.UpdateBoardIdValidationArgumentsProvider;
import test.BaseTest;

import java.util.Map;

import static consts.BoardsEndpoints.UPDATE_BOARD_URL;
import static consts.UrlParamsValues.BOARD_ID_TO_UPDATE;

public class UpdateBoardValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(AuthUpdateBoardValidationArgumentsProvider.class)
    public void checkUpdateBoardWithInvalidAuth(AuthValidationArgumentsHolder argumentsHolder){
        Response response = requestWithoutAuth()
                .log().method()
                .pathParam("id", BOARD_ID_TO_UPDATE)
                .queryParams(argumentsHolder.getAuthParams())
                .contentType(ContentType.JSON)
                .body(Map.of("name", "new_name"))
                .put(UPDATE_BOARD_URL);
        response
                .then()
                .statusCode(401)
                .log().body();
        Assertions.assertEquals(argumentsHolder.getErrorMessage(), response.body().asString());
    }

    @ParameterizedTest
    @ArgumentsSource(UpdateBoardIdValidationArgumentsProvider.class)
    public void checkUpdateBoardWithInvalidId(BoardIdValidationArgumentsHolder argumentsHolder){
        Response response = requestWithAuth()
                .log().method()
                .pathParams(argumentsHolder.getPathParams())
                .contentType(ContentType.JSON)
                .body(Map.of("name","new name"))
                .put(UPDATE_BOARD_URL);
        response
                .then()
                .statusCode(argumentsHolder.getStatusCode())
                .log().body();
        Assertions.assertEquals(argumentsHolder.getErrorMessage(), response.body().asString());
    }
}
