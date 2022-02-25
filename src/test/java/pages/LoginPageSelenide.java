package pages;

import com.codeborne.selenide.SelenideElement;
import core.ReadProperties;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPageSelenide {

    public LoginPageSelenide() {

    }

    private final By username_selector = By.id("inputEmail");
    private final By password_selector = By.id("inputPassword");
    private final By login_button_selector = By.id("btnLogin");

    public SelenideElement getUsernameField() {
        return $(username_selector);
    }

    public SelenideElement getPasswordField() {
        return $(password_selector);
    }

    public SelenideElement getLoginButton() {
        return $(login_button_selector);
    }

    public void loginUsers() {
        getUsernameField().setValue(ReadProperties.getUsername());
        getPasswordField().setValue(ReadProperties.getPassword());
        getLoginButton().click();
    }
}






