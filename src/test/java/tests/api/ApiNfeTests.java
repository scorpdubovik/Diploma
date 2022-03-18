package tests.api;

import baseEntity.BaseApiTest;
import enums.MilestoneStatusType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.MilestoneBuilder;
import models.ProjectBuilder;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import steps.DB_CaseSteps;
import utils.Endpoints;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ApiNfeTests extends BaseApiTest {
    private ProjectBuilder newProject;
    private ProjectBuilder expectedProject;
    MilestoneBuilder newMilestone;
    MilestoneBuilder expectedMilestone1;
    MilestoneBuilder expectedMilestone2;
    private String projectCode;
    private int milestoneId;

    public static Logger logger = Logger.getLogger(ApiNfeTests.class);

    @Test
    public void addProjectTest() {
        logger.trace("addProjectTest is started...");

        newProject = new ProjectBuilder.Builder()
                .withName("Project1")
                .withCode("Our")
                .build();

        projectCode = given()
                .body(newProject, ObjectMapperType.GSON)
                .log().body()
                .when()
                .post(Endpoints.ADD_PROJECT)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().body()
                .extract().jsonPath().get("result.code");

        System.out.println(projectCode);
    }

    @Test(dependsOnMethods = "addProjectTest")
    public void getProjectTest() {
        logger.trace("getProjectTest is started...");

        expectedProject = new ProjectBuilder.Builder()
                .withName("Project1")
                .build();

        given()
                .pathParam("code", projectCode)
                .when()
                .get(Endpoints.GET_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .body("result.title", is(expectedProject.getName()));
    }

    @Test(dependsOnMethods = "addProjectTest")
    public void addMilestoneTest() {
        logger.trace("addMilestoneTest is started...");

        newMilestone = new MilestoneBuilder.Builder()
                .withName("Sprint1")
                .withDescription("My new Milestone")
                .withStatus(MilestoneStatusType.COMPLETED)
                .withDueDate(1651043416)
                .build();

        milestoneId = given()
                .pathParam("code", projectCode)
                .body(newMilestone, ObjectMapperType.GSON)
                .log().body()
                .when()
                .post(Endpoints.ADD_MILESTONE)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().body()
                .extract().jsonPath().get("result.id");

        System.out.println(milestoneId);
    }

    @Test(dependsOnMethods = {"addProjectTest", "addMilestoneTest"})
    public void getMilestoneTest() {
        expectedMilestone1 = new MilestoneBuilder.Builder()
                .withName("Sprint1")
                .withDescription("My new Milestone")
                .build();

        Response response = given()
                .pathParam("code", projectCode)
                .pathParam("id", milestoneId)
                .when()
                .get(Endpoints.GET_MILESTONE)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().body()
                .extract().response();

        Assert.assertEquals(response.getBody().jsonPath().get("result.title"),
                expectedMilestone1.getName());
    }

    @Test(dependsOnMethods = {"addProjectTest", "addMilestoneTest"})
    public void getMilestonesTest() {
        expectedMilestone2 = new MilestoneBuilder.Builder()
                .withName("Sprint1")
                .withDescription("My new Milestone")
                .build();

        given()
                .pathParam("code", projectCode)
                .when()
                .get(Endpoints.GET_MILESTONES)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .log().body()
                .body("result.entities[0].title", equalTo(expectedMilestone2.getName()))
                .body("result.entities[0].description", equalTo(expectedMilestone2.getDescription()));
    }

    @Test(dependsOnMethods = {"addProjectTest", "getProjectTest", "addMilestoneTest", "getMilestoneTest", "getMilestonesTest"})
    public void deleteProjectTest() {
        given()
                .pathParam("code", projectCode)
                .when()
                .delete(Endpoints.DEL_PROJECT)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
