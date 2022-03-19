package baseEntity;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import core.DataBaseService;
import core.ReadProperties;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.*;
import pages.login.LoginPageSelenide;
import steps.*;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    protected ProjectSteps projectSteps;
    protected CaseSteps caseSteps;
    protected DB_ProjectSteps db_projectSteps;
    protected DB_CaseSteps db_caseSteps;
    protected InviteSteps inviteSteps;
    protected DataBaseService dataBaseService;

    @BeforeClass
    public void setupConnection() {
        org.apache.log4j.BasicConfigurator.configure();
        dataBaseService = new DataBaseService();
    }

    @BeforeClass
    public void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );
        // Настройка slf4j
        org.apache.log4j.BasicConfigurator.configure();

        // Настройка Selenide
        Configuration.baseUrl = ReadProperties.getUrl();
        Configuration.browser = ReadProperties.getBrowserName().toLowerCase();
        Configuration.startMaximized = true;
        Configuration.fastSetValue = true;
        Configuration.timeout = 8000;
        Configuration.headless = ReadProperties.isHeadless();

        projectSteps = new ProjectSteps();
        caseSteps = new CaseSteps();
        db_projectSteps = new DB_ProjectSteps();
        db_caseSteps = new DB_CaseSteps();
        inviteSteps = new InviteSteps();

        // Login
        open("/");
        LoginPageSelenide loginPageSelenide = new LoginPageSelenide();
        loginPageSelenide.loginUsers();
    }

    @AfterClass
    public void closePage() {
        closeWebDriver();
    }

    @AfterClass
    public void closeConnection() {
        dataBaseService.closeConnection();
    }
}