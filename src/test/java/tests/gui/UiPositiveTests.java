package tests.gui;

import baseEntity.BaseTest;
import data.StaticProvider;
import dbEntries.CaseTable;
import dbEntries.ProjectTable;
import models.CaseBuilder;
import models.ProjectBuilder;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.AddCasePageSelenide;
import pages.AddProjectPageSelenide;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.sleep;

public class UiPositiveTests extends BaseTest {
    private CaseBuilder addCases;
    private AddCasePageSelenide addCasePageSelenide;
    private ProjectBuilder addProject;
    private AddProjectPageSelenide addProjectSelenide;
    public static String addProjectName;
    public static String addCaseName;

    @Test
    public void createProjectTest() {
        db_projectSteps.createProjectTable(dataBaseService);

        addProject = db_projectSteps.createAddProject(dataBaseService, 1);
        addProjectName = addProject.getName();

        addProjectSelenide = projectSteps.addProject(addProject);
        $(By.xpath("//p[@class='header']")).shouldBe(visible).shouldHave(text("Kanye"));
    }

    @Test(dataProvider = "dataForLimitTest", dataProviderClass = StaticProvider.class)
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

    @Test(description = "createProjectTest")
    public void ddCaseForCheckDialogBoxTest() {
        db_caseSteps.createCaseTable(dataBaseService);

        addCases = db_caseSteps.createAddCase(dataBaseService, 1);
        addCaseName = addCases.getTitle();

        addCasePageSelenide = caseSteps.addCase(addCases);
        $(By.xpath("//*[@class='style_title-1ehyC']")).shouldBe(visible).shouldHave(text("Test cases without suite"));
    }

    @Test(description = "ddCaseForCheckDialogBoxTest")
    public void deleteProjectTest() {
        addProjectSelenide = projectSteps.deleteProject(addProject);
        $(By.xpath("//*[.= 'Kanye']")).shouldNotBe(visible);
    }

    @Test(description = "deleteProjectTest")
    public void popUpWindowTest() {
        $(By.xpath("//*[.= 'Workspace']")).click();
        $(By.xpath("//*[.= 'Logs']")).click();
        $(By.xpath("//h1[@class='header']")).shouldBe(visible).shouldHave(text("Upgrade"));
        $(By.xpath("//*[.= 'not now']")).doubleClick();
    }

    @Test(description = "popUpWindowTest")
    public void uploadFileTest() {
        $(By.id("user-menu")).click();
        $(By.xpath("//*[.= ' Profile']")).click();
        $("input#fileupload").uploadFile(new File("src\\test\\resources\\files\\smile.png"));
        sleep(12000);

        $("#project-image").shouldBe(visible);
    }
}

