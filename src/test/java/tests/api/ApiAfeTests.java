package tests.api;

import baseEntity.BaseApiTest;
import io.restassured.mapper.ObjectMapperType;
import models.ProjectBuilder;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import utils.Endpoints;

import static io.restassured.RestAssured.given;

public class ApiAfeTests extends BaseApiTest {
    private ProjectBuilder newProject;

    @Test
    public void addProjectAfeTest() {
        newProject = new ProjectBuilder.Builder()
                .withName("Project2")
                .withCode("@%_BadCode")
                .build();

        given()
                .body(newProject, ObjectMapperType.GSON)
                .log().body()
                .when()
                .post(Endpoints.ADD_PROJECT)
                .then()
                .statusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY);
    }

    @Test
    public void getProjectsAfeTest() {

        given()
                .header("Token", "WrongToken")
                .when()
                .get(Endpoints.GET_PROJECTS)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }
}
