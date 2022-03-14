package pages.projects;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static java.lang.Thread.sleep;

public class AddProjectPageSelenide {

    private final By nameProjectSelector = By.id("inputTitle");
    private final By codeProjectSelector = By.id("inputCode");
    private final By descriptionProjectSelector = By.id("inputDescription");
    private final By addProjectButton = By.xpath("//*[text() = 'Create project']");

    public SelenideElement getNameField() {
        return $(nameProjectSelector);
    }
    public SelenideElement getCodeField() {
        return $(codeProjectSelector);
    }
    public SelenideElement getDescriptionField() {
        return $(descriptionProjectSelector);
    }
    public SelenideElement getAddProjectButton() {return $(addProjectButton);}
}
