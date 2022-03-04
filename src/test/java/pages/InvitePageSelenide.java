package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class InvitePageSelenide {
    private static final By PAGE_OPENED_IDENTIFIER = byText("Invite new user");
    private final By emailSelector = By.id("email");
    private final By nameSelector = By.id("name");
    private final By roleTitleSelector = By.id("role_title");
    private final String inviteButtonSelector = "button[class = 'btn btn-primary']";

    public SelenideElement getEmailField() {return $(emailSelector);}

    public SelenideElement getNameField() {
        return $(nameSelector);
    }

    public SelenideElement getRoleTitleField() {
        return $(roleTitleSelector);
    }

    public SelenideElement getInviteButton() {
        return $(inviteButtonSelector);
    }

    public SelenideElement getPageOpenIdentifier() {
        return $(PAGE_OPENED_IDENTIFIER);
    }
}
