package pages.workspace;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WorkspacePageSelenide {

    private static String ENDPOINT = "/workspace?page=1";
    private final By membersSelector = By.xpath("//span[. = 'Members']");
    private final By invitesSelector = By.xpath("//span[text() = 'Invites']");
    private final By groupsSelector = By.xpath("//span[text() = 'Groups']");
    private final By rolesSelector = By.xpath("//span[. = 'Roles']");
    private final By customFieldsSelector = By.xpath("//span[. = 'Custom Fields']");
    private final By tagsSelector = By.xpath("//span[. = 'Tags']");
    private final By attachmentsSelector = By.xpath("//span[. = 'Attachments']");
    private final By logsSelector = By.xpath("//span[. = 'Logs']");
    private final By settingsSelector = By.xpath("//span[. = 'Settings']");
    private final String inviteNewMemberButtonSelector = ".btn.btn-primary.me-2";
    private String successFieldSelector = "alert.alert-success.show";
    private String errorFieldSelector = ".alert.alert-error.alert-dismissible.show";

    public SelenideElement getMembersItem() {
        return $(membersSelector);
    }
    public SelenideElement getInvitesItem() {return $(invitesSelector);}
    public SelenideElement getGroupsItem() {
        return $(groupsSelector);
    }
    public SelenideElement getRolesItem() {return $(rolesSelector);}
    public SelenideElement getCustomFieldsItem() {return $(customFieldsSelector);}
    public SelenideElement getTagsItem() {return $(tagsSelector);}
    public SelenideElement getAttachmentsItem() {return $(attachmentsSelector);}
    public SelenideElement getLogsItem() {return $(logsSelector);}
    public SelenideElement getSettingsItem() {return $(settingsSelector);}
    public SelenideElement getInviteNewMemberButton() {return $(inviteNewMemberButtonSelector);}
    public SelenideElement getSuccessMessage() {return $(successFieldSelector);}
    public SelenideElement getErrorMessage() {return $(errorFieldSelector);}

    public void openPageByUrl() {
        open(ENDPOINT);
    }
}
