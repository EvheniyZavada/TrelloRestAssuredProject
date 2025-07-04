package test.post;

import consts.ConstsHolder;
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

public class CreateCardValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(AuthCreateCardValidationArgumentsProvider.class)
    public void checkCreateCardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments){
        Response response = requestWithoutAuth()
                .log().method()
                .contentType(ContentType.JSON)
                .body(Map.of("idList", ConstsHolder.myBoardInProgressListId, "name", "My_name"))
                .post(ConstsHolder.createCardEndpoint);
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
                .post(ConstsHolder.createCardEndpoint);
        response
                .then()
                .statusCode(400)
                .log().body();
        Assertions.assertEquals(validationArgumentsHolder.getErrorMessage(), response.body().asString());
    }
}
