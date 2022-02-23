package tests.gui.selenide;

import baseEntity.BaseTest;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import core.DataBaseService;
import core.ReadProperties;
import dbEntries.ProjectTable;
import io.qameta.allure.selenide.AllureSelenide;
import models.ProjectBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.AddProjectSelenide;
import pages.LoginPageSelenide;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class UiSelenideTests extends BaseTest {
    ProjectBuilder addProject;

    @Test
    public void createProjectTest() throws InterruptedException {

        // Начинаем писать тест
        open(ReadProperties.getUrl());

        LoginPageSelenide loginPageSelenide = new LoginPageSelenide();
        loginPageSelenide.getUsernameField().setValue(ReadProperties.getUsername());
        loginPageSelenide.getPasswordField().setValue(ReadProperties.getPassword());
        loginPageSelenide.getLoginButton().submit();

        $(By.id("createButton")).shouldBe(visible).shouldHave(text("Create new project"));

        $("#createButton").click();

        ProjectTable projectTable = new ProjectTable(dataBaseService);

        projectTable.createTable();
        projectTable.addProject("Kanye1", "West1", "123");

        String nameName = null;
        String code = null;
        String description = null;

        ResultSet rs = projectTable.getProjectByID(20);

        try {
            while (rs.next()) {
                nameName = rs.getString("name");
                code = rs.getString("code");
                description = rs.getString("description");

                logger.info("nameMilestone: " + nameName);
                logger.info("reference: " + code);
                logger.info("description: " + description);
            }
        } catch (SQLException e) {
            logger.error(e.toString());
        }

        addProject = new ProjectBuilder();
        addProject.setName(nameName);
        addProject.setCode(code);
        addProject.setDescription(description);

        AddProjectSelenide addProjectSelenide = new AddProjectSelenide();
        addProjectSelenide.addProject(addProject);

        $(By.xpath("//p[@class='header']")).shouldBe(visible).shouldHave(text("Kanye1"));
    }

    @Test(dependsOnMethods = "createProjectTest")
    public void deleteProjectTest() {

        $(By.xpath("//*[.= 'Settings']")).click();
        $(By.xpath("//a[@class= 'btn btn-cancel']")).click();
        $(By.xpath("//*[@class= 'btn btn-cancel']")).click();

        $(By.xpath("//*[.= 'Kanye1']")).shouldNotBe(visible);
    }
}
