package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class AddCasePageSelenide {

    private final By titleCaseSelector = By.xpath("//*[@id= 'title']");
    private final By descriptionCaseSelector = By.xpath("//p[@data-placeholder='For example: We can authorize on page http://example.com/login']");
    private final By addAttachmentButton = By.xpath("//*[@class = 'add-attachment btn btn-invisible b-0 p-0']");
    private final By addUploadButton = By.xpath("//*[text() = 'Drop files here to upload']");
    private final By addCaseButton = By.id("save-case");

    public SelenideElement getTitleField() {
        return $(titleCaseSelector);
    }
    public SelenideElement getDescriptionField() {
        return $(descriptionCaseSelector);
    }
    public SelenideElement getAttachmentField() {
        return $(addAttachmentButton);
    }
    public SelenideElement getUploadButton() {return $(addUploadButton);}
    public SelenideElement getAddCaseButton() {return $(addCaseButton);}
}
