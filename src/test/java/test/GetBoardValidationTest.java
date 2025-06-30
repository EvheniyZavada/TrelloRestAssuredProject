package test;

import holders.AuthValidationArgumentsHolder;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import providers.AuthGetBoardValidationArgumentsProvider;


public class GetBoardValidationTest extends BaseTest{

    @ParameterizedTest //тест параметризован
    @ArgumentsSource(AuthGetBoardValidationArgumentsProvider.class) //откуда берем параметры
    public void checkGetBoardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments){
            Response response = requestWithoutAuth() //убрали авторизацию из BaseTest(оставили пустые), добавили response
                    .log().method()
                    .pathParam("id", "68499967310c9b0128bb22c1")
                    .queryParams(validationArguments.getAuthParams())
                    .get("/boards/{id}");
        response
                    .then()
                    .statusCode(401)
                    .log().body();
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
        }

    @Test
    public void chekGetBoardWithInvalidId(){
          Response response = requestWithAuth()
                .log().method()
                .pathParam("id", "68499967310c9b0128bb22c7") //7 instead 1 in the end
                .get("/boards/{id}");
          response
                .then()
                .statusCode(404);
        Assertions.assertEquals("The requested resource was not found.",response.body().asString());
    }

    @Test
    public void chekGetBoardWithInvalidShortId(){
        Response response = requestWithAuth()
                .log().method()
                .pathParam("id", "68499967310c9b0128bb22c")// -1 char
                .get("/boards/{id}");
        response
                .then()
                .statusCode(400);
        Assertions.assertEquals("invalid id", response.body().asString());
    }

    @Test
    public void chekGetBoardWithInvalidLongId(){
        Response response = requestWithAuth()
                .log().method()
                .pathParam("id", "68499967310c9b0128bb22c17")// +1 char
                .get("/boards/{id}");
        response
                .then()
                .statusCode(400);
        Assertions.assertEquals("invalid id", response.body().asString());
    }

    @Test
    public void chekGetBoardWithoutId(){
        Response response = requestWithAuth()
                .log().method()
                .pathParam("id", "")//no id
                .get("/boards/{id}");
        response
                .then()
                .statusCode(404);
        Assertions.assertEquals("Cannot GET /1/boards/?key=4d9dd97638a81eaec3d5a7f125b6b562&token=ATTAac7d6572f06502abadc634407cef8927be972cf00c41777a053365e7ffcb6101C7527E9B",
                response.body().asString());
    }
}

