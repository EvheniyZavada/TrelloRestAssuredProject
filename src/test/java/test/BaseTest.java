package test;
import consts.UrlParamsValues;
import io.qameta.allure.Allure;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class BaseTest {

    @BeforeAll
    public static void setup() throws IOException {
        RestAssured.filters(new AllureRestAssured());//для работы с фильтрами
        FileUtils.cleanDirectory(new File("allure-results")); //краткая аналогия написанному методу
        //cleanAllureResults();
        RestAssured.baseURI = "https://api.trello.com/1";
    }

    protected static RequestSpecification requestWithAuth(){
        return RestAssured.given()
                .queryParams(Map.of("key", UrlParamsValues.VALID_KEY,
                        "token", UrlParamsValues.VALID_TOKEN))
                .header("Accept", "application/json");
    }

    protected static RequestSpecification requestWithoutAuth(){
        return RestAssured.given();
    }

    private static void cleanAllureResults(){
        Path allureResultsDir = Paths.get("allure-results");//в классе path пишем только имя без пути

        if(!Files.exists(allureResultsDir)){
            Allure.addAttachment("allure CleanUp", "text/plain",
                    "Directory 'allure-results' doesnt exist. Skipping cleanup");
            return;
        }

        try{
            FileUtils.cleanDirectory(allureResultsDir.toFile());
            Allure.addAttachment("allure CleanUp","text/plain",
                    "successfully cleaned 'allure-results' directory");
        }catch (Exception e){
            Allure.addAttachment("allure Cleanup Error", "text/plain",
                    "Failed to clean 'allure-results': " + e.getMessage());
            throw new RuntimeException("Failed to clean 'allure-results'", e);
        }
    }
}
