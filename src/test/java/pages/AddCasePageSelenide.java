package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class AddCasePageSelenide {

    private final By titleCaseSelector = By.xpath("//*[@id= 'title']");
    private final By descriptionCaseSelector = By.xpath("//p[@data-placeholder='For example: We can authorize on page http://example.com/login']");
    private final By addCaseButton = By.id("save-case");

    public SelenideElement getTitleField() {
        return $(titleCaseSelector);
    }
    public SelenideElement getDescriptionField() {
        return $(descriptionCaseSelector);
    }
    public SelenideElement getAddCaseButton() {return $(addCaseButton);}
}
