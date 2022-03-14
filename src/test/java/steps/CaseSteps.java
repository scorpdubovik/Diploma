package steps;

import models.CaseBuilder;
import org.openqa.selenium.By;
import pages.cases.AddCasePageSelenide;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;


public class CaseSteps {
    private AddCasePageSelenide addCasePageSelenide = new AddCasePageSelenide();

    public AddCasePageSelenide addCase(CaseBuilder qase) {
        $(By.xpath("//*[.= ' Case']")).click();
        $(By.xpath("//div[@class='case-create-block']")).shouldBe(visible).shouldHave(text("Basic"));

        addCasePageSelenide.getTitleField().sendKeys(qase.getTitle());
        addCasePageSelenide.getDescriptionField().sendKeys(qase.getDescription());
        addCasePageSelenide.getAddCaseButton().click();
        return addCasePageSelenide;
    }
}

