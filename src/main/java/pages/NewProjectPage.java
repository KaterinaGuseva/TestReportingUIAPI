package pages;

import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import data.Attributes;
import org.openqa.selenium.By;
import utils.jsutils.JavaScriptMethods;

public class NewProjectPage extends Form {

    private static final String TXB_INPUT_PROJECT_NAME = "//input[@id='projectName']";

    private final ITextBox txbInputProjectName = getElementFactory().
            getTextBox(By.xpath(TXB_INPUT_PROJECT_NAME),
                    "TextBox Input Project Name");

    private final IButton btnSaveProject = getElementFactory().
            getButton(By.xpath("//button[@class='btn btn-primary']"),
                    "Button Save New Project");

    private final ITextBox txbGetSavedProjectMessage = getElementFactory().
            getTextBox(By.xpath("//div[@class='alert alert-success']"),
                    "TextBox Saved Project Message");

    public NewProjectPage() {
        super(By.xpath(TXB_INPUT_PROJECT_NAME), "New Project Page");
    }

    public void inputNewProject(String newProjectName) {
        txbInputProjectName.sendKeys(newProjectName);
    }

    public void saveProject() {
        btnSaveProject.click();
    }

    public String getSavedProjectMessage() {
        return txbGetSavedProjectMessage.getAttribute(Attributes.INNER_TEXT.getAttribute());
    }

    public void closeNewProjectPage() {
        JavaScriptMethods.closeWindow();
    }
}
