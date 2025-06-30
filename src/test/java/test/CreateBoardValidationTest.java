package test;

import holders.AuthValidationArgumentsHolder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import providers.AuthCreateBoardValidationArgumentsProvider;
import java.util.Map;

public class CreateBoardValidationTest extends BaseTest {

    @ParameterizedTest
    @ArgumentsSource(AuthCreateBoardValidationArgumentsProvider.class)
    public void checkCreateBoardWithInvalidAuth(AuthValidationArgumentsHolder validationArguments){
        Response response = requestWithoutAuth()
                .log().method()
                .body(Map.of("name", "noname"))
                .post("/boards/");
        response
                .then()
                .statusCode(400)
                .log().body();
        Assertions.assertEquals(validationArguments.getErrorMessage(), response.body().asString());
    }

    @Test
    public void checkCreateBoardWithEmptyValueForName(){
        Response response = requestWithAuth()
                .log().method()
                .contentType(ContentType.JSON)
                .body(Map.of("name", ""))
                .post("/boards/");
        response
                .then()
                .statusCode(400)
                .log().body();
        Assertions.assertEquals("{\"message\":\"invalid value for name\",\"error\":\"ERROR\"}", response.body().asString());
    }

    @Test
    public void checkCreateBoardWithSpaceInsteadOfName(){
        Response response = requestWithAuth()
                .log().method()
                .contentType(ContentType.JSON)
                .body(Map.of("name", " "))
                .post("/boards/");
        response
                .then()
                .statusCode(400)
                .log().body();
        Assertions.assertEquals("{\"message\":\"invalid value for name\",\"error\":\"ERROR\"}", response.body().asString());
    }

    @Test
    public void checkCreateBoardWithEmptyKeyForName(){
        Response response = requestWithAuth()
                .log().method()
                .contentType(ContentType.JSON)// нужно? когда?
                .body(Map.of("", "qwe"))
                .post("/boards/");
        response
                .then()
                .statusCode(400)
                .log().body();
        Assertions.assertEquals("{\"message\":\"invalid value for name\",\"error\":\"ERROR\"}", response.body().asString());
    }

    @Test
    public void checkCreateBoardWithEmptyKeyAndValueForName(){
        Response response = requestWithAuth()
                .log().method()
                .body(Map.of("",""))
                .post("/boards/");
        response
                .then()
                .statusCode(400)
                .log().body();
        Assertions.assertEquals("{\"message\":\"invalid value for name\",\"error\":\"ERROR\"}", response.body().asString());
    }
}
