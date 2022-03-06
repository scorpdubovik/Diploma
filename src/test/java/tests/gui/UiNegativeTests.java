package tests.gui;

import baseEntity.BaseTest;
import core.ReadProperties;
import io.qameta.allure.Description;
import models.UserBuilder;
import org.testng.annotations.Test;
import pages.LoginPageSelenide;
import pages.WorkspacePageSelenide;
import utils.Randomization;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.open;

public class UiNegativeTests extends BaseTest {
    private UserBuilder newUser;
    private WorkspacePageSelenide workspacePageSelenide;

    @Test
    @Description("Incorrect data entry test")
    public void badDataTest() {
        open(ReadProperties.getUrl());

        LoginPageSelenide loginPageSelenide = new LoginPageSelenide();
        loginPageSelenide.loginUsers();

        newUser = new UserBuilder.Builder()
                .withEmail("#$@#")
                .withName(Randomization.getRandomString(8))
                .withRoleTitle(Randomization.getRandomString(4))
                .build();

        workspacePageSelenide = inviteSteps.inviteNewUser(newUser);
        workspacePageSelenide.getErrorMessage().shouldBe(visible).shouldHave(text("Validation error"));
    }

    @Test
    @Description("Test for input data exceeding the allowable")
    public void dataExcessTest() {
        newUser = new UserBuilder.Builder()
                .withEmail(Randomization.getRandomString(7) + "@gmail.com")
                .withName(Randomization.getRandomString(5))
                .withRoleTitle(Randomization.getRandomString(256))
                .build();
        System.out.println(Randomization.getRandomString(256));
        workspacePageSelenide = inviteSteps.inviteNewUser(newUser);
        workspacePageSelenide.getErrorMessage().shouldBe(visible).shouldHave(text("Server Error"));
    }

    @Test
    @Description("Test that reproduces defect")
    public void defectReproducingTest() {
        newUser = new UserBuilder.Builder()
                .withEmail(Randomization.getRandomString(3) + "@gmail.com")
                .withName(Randomization.getRandomString(4))
                .withRoleTitle(Randomization.getRandomString(100))
                .build();
        System.out.println(Randomization.getRandomString(256));
        workspacePageSelenide = inviteSteps.inviteNewUser(newUser);
        workspacePageSelenide.getErrorMessage().shouldBe(visible).shouldHave(text("Validation error"));
    }
}