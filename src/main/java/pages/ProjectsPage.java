package pages;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import data.Attributes;
import org.openqa.selenium.By;
import utils.commonutils.Parsing;
import utils.uiutils.BrowserMethods;

import static aquality.selenium.browser.AqualityServices.getBrowser;

public class ProjectsPage extends Form {

    private final ITextBox txbPortalVersion = getElementFactory().
            getTextBox(By.xpath("//p[@class='text-muted text-center footer-text']//span"),
                    "TextBox Version");
    private final IButton btnAddNewProject = getElementFactory().
            getButton(By.xpath("//a[@class='btn btn-xs btn-primary pull-right']"),
            "Button Add New Project");
    private final String STRING_PROJECT_ITEM = "//a[@class='list-group-item' and contains(text(),'%s')]";
    protected String currentWindow = AqualityServices.getBrowser().getDriver().getWindowHandle();

    public ProjectsPage() {
        super(By.xpath("//div[@class='list-group']"), "Projects Page");
    }

    public String getVersion() {
        return txbPortalVersion.getText();
    }

    public void openSelectedProjectPage(String projectName) {
        IButton btnProject = getElementFactory().getButton(By.xpath
                (String.format(STRING_PROJECT_ITEM, projectName)),
                String.format("Button %s Project", projectName));
        btnProject.click();
        getBrowser().waitForPageToLoad();
    }

    public String getIdSelectedProject(String projectName) {
        IButton btnProject = getElementFactory().getButton(By.xpath
                        (String.format(STRING_PROJECT_ITEM, projectName)),
                String.format("Button %s Project", projectName));
        return String.valueOf(Parsing.getValue(btnProject.getAttribute(Attributes.SEARCH.getAttribute())));
    }

    public void openAddNewProjectPage() {
        btnAddNewProject.click();
        BrowserMethods.switchToNewWindow(currentWindow);
    }

    public void switchToCurrentWindow() {
        BrowserMethods.switchToSelectedWindow(currentWindow);
    }
}
