package baseEntity;

import core.ReadProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.protocol.HTTP;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.given;

public class BaseApiTest {

    @BeforeTest
    public void setupApiTest() {
        // Настройка slf4j
        org.apache.log4j.BasicConfigurator.configure();

        // Setup RestAssured
        RestAssured.baseURI = ReadProperties.getApiUrl();

        // Setup request Object
        RestAssured.requestSpecification = given()
                .header(HTTP.CONTENT_TYPE, ContentType.JSON)
                .header("Token", "9531b8f17dddd36986411cb6d7ed5849b2a29f75");
    }
}
