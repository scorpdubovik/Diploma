package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPageSelenide {

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
}
