package tests.gui;

import baseEntity.BaseTest;
import data.StaticProvider;
import io.qameta.allure.Description;
import models.CaseBuilder;
import models.ProjectBuilder;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.cases.AddCasePageSelenide;
import pages.projects.AddProjectPageSelenide;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class UiPositiveTests extends BaseTest {
    private CaseBuilder addCases;
    private ProjectBuilder addProject;
    private AddCasePageSelenide addCasePageSelenide;
    private AddProjectPageSelenide addProjectSelenide;
    public static String addProjectName;
    public static String addCaseName;

    @Test(priority = 1)
    @Description("Create new project test")
    public void createProjectTest() {
        db_projectSteps.createProjectTable(dataBaseService);

        addProject = db_projectSteps.createAddProject(dataBaseService, 1);
        addProjectName = addProject.getName();

        addProjectSelenide = projectSteps.addProject(addProject);
        $(By.xpath("//p[@class='header']")).shouldBe(visible).shouldHave(text("Kanye"));
    }

    @Test(priority = 2)
    @Description("Check dialog box test")
    public void ddCaseForCheckDialogBoxTest() {
        db_caseSteps.createCaseTable(dataBaseService);

        addCases = db_caseSteps.createAddCase(dataBaseService, 1);
        addCaseName = addCases.getTitle();

        addCasePageSelenide = caseSteps.addCase(addCases);
        $(By.xpath("//*[@class='style_title-1ehyC']")).shouldBe(visible).shouldHave(text("Test cases without suite"));
    }

    @Test(priority = 3)
    @Description("Delete project test")
    public void deleteProjectTest() {
        addProjectSelenide = projectSteps.deleteProject(addProject);
        $(By.xpath("//*[.= 'Kanye']")).shouldNotBe(visible);
    }

    @Test(dataProvider = "dataForLimitTest", dataProviderClass = StaticProvider.class, priority = 4)
    @Description("limit test")
    public void limitTest(String name, String code, String expectedResult) {
        ProjectBuilder newProject = new ProjectBuilder.Builder()
                .withName(name)
                .withCode(code)
                .build();

        addProjectSelenide = projectSteps.addProject(newProject);
        if (code.length() != 1) {
            $(".alert-message").shouldBe(visible).shouldHave(text(expectedResult));
            projectSteps.deleteProject(newProject);
        } else {
            $(".form-control-feedback").shouldBe(visible).shouldHave(text(expectedResult));
        }
    }

    @Test(priority = 5)
    @Description("Pop up window test")
    public void popUpWindowTest() {
        $(By.xpath("//*[.= 'Workspace']")).click();
        $(By.xpath("//*[.= 'Logs']")).click();
        $(By.xpath("//h1[@class='header']")).shouldBe(visible).shouldHave(text("Upgrade"));
        $(By.xpath("//*[.= 'not now']")).doubleClick();
        open("/projects");
    }

    @Test(priority = 5)
    @Description("Upload file test")
    public void uploadFileTest() {
        $(By.id("user-menu")).click();
        $(By.xpath("//*[.= ' Profile']")).click();
        $("input#fileupload").uploadFile(new File("src\\test\\resources\\files\\smile.png"));
        sleep(12000);

        $("#project-image").shouldBe(visible);
        open("/projects");
    }
}

