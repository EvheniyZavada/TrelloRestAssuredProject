package test.get;

import consts.ConstsHolder;
import holders.AuthValidationArgumentsHolder;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import providers.AuthCardValidationArgumentsProvider;
import test.BaseTest;


public class GetCardsValidationTest extends BaseTest {

    @ParameterizedTest //тест параметризован
    @ArgumentsSource(AuthCardValidationArgumentsProvider.class) //откуда берем параметры
    public void checkGetCardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments){
        Response response = requestWithoutAuth() //убрали авторизацию из BaseTest(оставили пустые), добавили response
                .log().method()
                .pathParam("id", ConstsHolder.theirBoardCardId)
                .queryParams(validationArguments.getAuthParams())
                .get(ConstsHolder.getCardEndpoint);
        response
                .then()
                .statusCode(401)
                .log().body();
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }

    @Test
    public void chekGetCardWithInvalidId(){
        Response response = requestWithAuth()
                .log().method()
                .pathParam("id", ConstsHolder.invalidCardId) //7 instead 1 in the end
                .get(ConstsHolder.getCardEndpoint);
        response
                .then()
                .statusCode(404);
        Assertions.assertEquals("The requested resource was not found.",response.body().asString());
    }

    @Test
    public void chekGetCardWithInvalidShortId(){
        Response response = requestWithAuth()
                .log().method()
                .pathParam("id", ConstsHolder.invalidShortCardId)// -1 char
                .get(ConstsHolder.getCardEndpoint);
        response
                .then()
                .statusCode(400);
        Assertions.assertEquals("invalid id", response.body().asString());
    }

    @Test
    public void chekGetCardWithInvalidLongId(){
        Response response = requestWithAuth()
                .log().method()
                .pathParam("id", ConstsHolder.invalidLongCardId)// +1 char
                .get(ConstsHolder.getCardEndpoint);
        response
                .then()
                .statusCode(400);
        Assertions.assertEquals("invalid id", response.body().asString());
    }

    @Test
    public void chekGetCardWithoutId(){
        Response response = requestWithAuth()
                .log().method()
                .pathParam("id", "")//no id
                .get(ConstsHolder.getCardEndpoint);
        response
                .then()
                .statusCode(404);
        Assertions.assertEquals("Cannot GET /1/cards/?token=ATTAac7d6572f06502abadc634407cef8927be972cf00c41777a053365e7ffcb6101C7527E9B&key=4d9dd97638a81eaec3d5a7f125b6b562",
                response.body().asString());
    }
}
