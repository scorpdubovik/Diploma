package tests.gui;

import baseEntity.BaseTest;
import core.ReadProperties;
import dbEntries.CaseTable;
import dbEntries.ProjectTable;
import models.CaseBuilder;
import models.ProjectBuilder;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import pages.AddCasePageSelenide;
import pages.AddProjectPageSelenide;
import pages.LoginPageSelenide;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class UiPositiveTests extends BaseTest {
    private CaseBuilder addCases;
    private AddCasePageSelenide addCasePageSelenide;
    private ProjectBuilder addProject;
    private AddProjectPageSelenide addProjectSelenide;
    public static String addProjectName;
    public static String addCaseName;
    public static Logger logger = Logger.getLogger(UiPositiveTests.class);
    ProjectTable projectTable = new ProjectTable(dataBaseService);
    CaseTable caseTable = new CaseTable(dataBaseService);

    @Test
    public void createProjectTest() {
        db_projectSteps.createProjectTable(dataBaseService);

        open(ReadProperties.getUrl());

        LoginPageSelenide loginPageSelenide = new LoginPageSelenide();
        loginPageSelenide.loginUsers();

        addProject = db_projectSteps.createAddProject(dataBaseService, 1);
        addProjectName = addProject.getName();

        addProjectSelenide = projectSteps.addProject(addProject);
        $(By.xpath("//p[@class='header']")).shouldBe(visible).shouldHave(text("Kanye"));

    }

    @Test(description = "createProjectTest")
    public void CaseForCheckDialogBoxTest(){
        db_caseSteps.createCaseTable(dataBaseService);

        addCases = db_caseSteps.createAddCase(dataBaseService, 1);
        addCaseName = addCases.getTitle();

        addCasePageSelenide = caseSteps.addCase(addCases);
        $(By.xpath("//*[@class='style_title-1ehyC']")).shouldBe(visible).shouldHave(text("Test cases without suite"));
    }

    @Test(description = "CaseForCheckDialogBoxTest")
    public void deleteProjectTest() {
        addProjectSelenide = projectSteps.deleteProject(addProject);
        $(By.xpath("//*[.= 'Kanye']")).shouldNotBe(visible);
    }

    @Test(description = "deleteProjectTest")
    public void popUpWindowTest1(){
        $(By.xpath("//*[.= 'Workspace']")).click();
        $(By.xpath("//*[.= 'Logs']")).click();
        $(By.xpath("//h1[@class='header']")).shouldBe(visible).shouldHave(text("Upgrade"));
        $(By.xpath("//*[.= 'not now']")).doubleClick();

    }
}

