package baseEntity;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import core.DataBaseService;
import core.ReadProperties;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.log4j.Logger;
import org.testng.annotations.*;
import pages.LoginPageSelenide;
import steps.*;
import tests.gui.UiNegativeTests;
import tests.gui.UiPositiveTests;

import static com.codeborne.selenide.Selenide.open;


public class BaseTest {
    public static Logger logger = Logger.getLogger(UiPositiveTests.class);
    public static Logger logger1 = Logger.getLogger(UiNegativeTests.class);
    protected ProjectSteps projectSteps;
    protected CaseSteps caseSteps;
    protected DB_ProjectSteps db_projectSteps;
    protected DB_CaseSteps db_caseSteps;
    protected InviteSteps inviteSteps;
    protected DataBaseService dataBaseService;


    @BeforeClass(dependsOnMethods = "setupConnection")
    public void setUp() {

        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );

        dataBaseService = new DataBaseService();
        Configuration.baseUrl = ReadProperties.getUrl();
        Configuration.browser = ReadProperties.getBrowserName().toLowerCase();
        Configuration.startMaximized = false;
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

    @BeforeClass
    public void setupConnection() {
        org.apache.log4j.BasicConfigurator.configure();
        dataBaseService = new DataBaseService();
    }

    @AfterClass
    public void closeConnection() {
        dataBaseService.closeConnection();
    }
}