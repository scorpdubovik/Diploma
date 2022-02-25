package steps;

import core.ReadProperties;
import models.ProjectBuilder;
import org.openqa.selenium.By;
import pages.AddProjectSelenide;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class ProjectSteps {
    private AddProjectSelenide addProjectSelenide = new AddProjectSelenide();

    public AddProjectSelenide addProject(ProjectBuilder project) {
        $(By.id("createButton")).shouldBe(visible).shouldHave(text("Create new project"));
        $("#createButton").click();

        addProjectSelenide.getNameField().val(project.getName());
        addProjectSelenide.getCodeField().val(project.getCode());
        addProjectSelenide.getDescriptionField().val(project.getDescription());
        addProjectSelenide.getAddProjectButton().click();
        return addProjectSelenide;
    }

    public AddProjectSelenide deleteProject(ProjectBuilder project) {
        $(By.xpath("//*[.= 'Settings']")).click();
        $(By.xpath("//a[@class= 'btn btn-cancel']")).click();
        $(By.xpath("//*[@class= 'btn btn-cancel']")).click();
        return addProjectSelenide;
    }
}
