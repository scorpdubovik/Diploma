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
                .header("Token", ReadProperties.getToken());
    }
}
