package baseEntity;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import core.DataBaseService;
import core.ReadProperties;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.log4j.Logger;
import org.testng.annotations.*;
import tests.gui.selenide.UiSelenideTests;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {
    public static Logger logger = Logger.getLogger(UiSelenideTests.class);

    protected DataBaseService dataBaseService;


    @BeforeClass
    public void setUp() {
        //for fine-tuning:
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .screenshots(true)
                .savePageSource(true)
        );

        // Настройка slf4j
        org.apache.log4j.BasicConfigurator.configure();

        // Настройка Selenide
        dataBaseService = new DataBaseService();
        Configuration.baseUrl = ReadProperties.getUrl();
        Configuration.browser = ReadProperties.getBrowserName().toLowerCase();
        Configuration.startMaximized = false;
        Configuration.fastSetValue = true;
        Configuration.timeout = 8000;
        Configuration.headless = ReadProperties.isHeadless();
    }

    @AfterClass
    public void closePage() {
        closeWebDriver();
    }
}