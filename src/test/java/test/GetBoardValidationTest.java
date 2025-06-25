package test;

import holders.AuthValidationArgumentsHolder;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import providers.AuthValidationArgumentsProvider;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class GetBoardValidationTest extends BaseTest{

    @ParameterizedTest //тест параметризован
    @ArgumentsSource(AuthValidationArgumentsProvider.class) //откуда берем параметры
    public void checkGetBoardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments){
            Response response = requestWithoutAuth() //убрали авторизацию из BaseTest(оставили пустые), добавили response
                    .log().method()
                    .pathParam("id", "68499967310c9b0128bb22c1")
                    .queryParams("fields", "id,name")
                    .get("/boards/{id}?key=APIKey&token=APIToken");
        response
                    .then()
                    .statusCode(401)
                    .log().body();
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
        }
    }
