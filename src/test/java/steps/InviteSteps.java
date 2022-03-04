package steps;

import models.UserBuilder;
import pages.InvitePageSelenide;
import pages.WorkspacePageSelenide;

import static com.codeborne.selenide.Condition.enabled;

public class InviteSteps {
    WorkspacePageSelenide workspacePageSelenide = new WorkspacePageSelenide();
    InvitePageSelenide invitePageSelenide = new InvitePageSelenide();

    public WorkspacePageSelenide inviteNewUser(UserBuilder user) {
        workspacePageSelenide.openPageByUrl();
        workspacePageSelenide.getInvitesItem().click();
        workspacePageSelenide.getInviteNewMemberButton().click();

        invitePageSelenide.getPageOpenIdentifier().shouldBe(enabled);

        invitePageSelenide.getEmailField().sendKeys(user.getEmail());
        invitePageSelenide.getNameField().val(user.getName());
        invitePageSelenide.getRoleTitleField().sendKeys(user.getRoleTitle());

        invitePageSelenide.getInviteButton().scrollIntoView(true).click();
        return workspacePageSelenide;
    }
}
