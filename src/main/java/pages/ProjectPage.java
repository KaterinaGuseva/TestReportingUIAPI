package pages;

import aquality.selenium.elements.interfaces.*;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import pogo.TestModel;
import utils.commonutils.CreateString;
import utils.uiutils.TableMethods;

import java.util.ArrayList;
import java.util.List;

public class ProjectPage extends Form {

    private String projectName;
    private int initialValueTableRowIterator = 2;
    private final String TABLE_HEADING_NAME = "Test name";
    private final String TABLE_HEADING_METHOD = "Test method";
    private final String TABLE_HEADING_RESULT = "Latest test result";
    private final String TABLE_HEADING_START_TIME = "Latest test start time";
    private final String TABLE_HEADING_END_TIME = "Latest test end time";
    private final String TABLE_HEADING_DURATION = "Latest test duration (H:m:s.S)";
    private static final String TABLE_ROWS = "//table[@class='table']//tr";
    private static final String TABLE_SELECTED_VALUE = "[%d]";
    private static final String TABLE_HEADING = "//th";
    private static final String TABLE_COLUMN = "//td";
    private static final String TABLE_VALUE_BY_COLUMN_NAME = TABLE_ROWS + TABLE_SELECTED_VALUE + TABLE_COLUMN +
            "[count(" + TABLE_ROWS + TABLE_HEADING + "[text()='%s']//preceding-sibling::th)+1]";
    private final ILink lnkHome = getElementFactory().getLink(By.xpath("//a[@href='/web/projects']"), "Link Home");
    private final ITextBox waitModalWindow = getElementFactory().
            getTextBox(By.xpath("//div[@class='messi-box']"),
                    "Modal Window");
    private final ITextBox noTestWindow = getElementFactory().
            getTextBox(By.xpath("//div[@class='alert alert-danger']//span[@class='sr-only']"),
                    "Modal Window");

    public ProjectPage(String projectName) {
        super(By.xpath(String.format("//li[contains(text(),'%s')]", projectName)), String.format("%s Page", projectName));
        this.projectName = projectName;
    }

    public void waitTestsLoad() {
        waitModalWindow.state().waitForNotDisplayed();
    }

    public void waitNewTestAdd() {
        noTestWindow.state().waitForNotDisplayed();
    }

    public String getValueByColumnName (int numberRow, String nameColumn) {
        return getElementFactory().getLabel
                (By.xpath(String.format(TABLE_VALUE_BY_COLUMN_NAME, numberRow, nameColumn)), "Table cell").getText();
    }

    public void openProjectsPage() {
        lnkHome.getMouseActions().moveMouseToElement();
        lnkHome.getMouseActions().click();
    }

    public List<TestModel> setTableTestsDataToClass() {
        String columnHeadings = CreateString.getString(TABLE_ROWS,
                String.format(TABLE_SELECTED_VALUE, 1),
                TABLE_HEADING);
        String columnHeadingsName = CreateString.getString(TABLE_ROWS,
                String.format(TABLE_SELECTED_VALUE, 1),
                TABLE_HEADING, TABLE_SELECTED_VALUE);
        List<TestModel> testsUi = new ArrayList<>();
        for (int row = initialValueTableRowIterator; row <= TableMethods.getRows(TABLE_ROWS).size(); row++) {
            TestModel testUi = new TestModel();
            for (int column = 1; column <= (TableMethods.getColumnHeadings
                    (columnHeadings).size()-1); column++) {
                switch (TableMethods.getColumnHeadingName(String.format(columnHeadingsName, column))) {
                    case TABLE_HEADING_NAME:
                        testUi.setName(getValueByColumnName(row, TABLE_HEADING_NAME));
                        break;
                    case TABLE_HEADING_METHOD:
                        testUi.setMethod(getValueByColumnName(row, TABLE_HEADING_METHOD));
                        break;
                    case TABLE_HEADING_RESULT:
                        testUi.setStatus(getValueByColumnName(row, TABLE_HEADING_RESULT));
                        break;
                    case TABLE_HEADING_START_TIME:
                        testUi.setStartTime(getValueByColumnName(row, TABLE_HEADING_START_TIME));
                        break;
                    case TABLE_HEADING_END_TIME:
                        testUi.setEndTime(getValueByColumnName(row, TABLE_HEADING_END_TIME));
                        break;
                    case TABLE_HEADING_DURATION:
                        testUi.setDuration(getValueByColumnName(row, TABLE_HEADING_DURATION));
                        break;
                }
            }
            testsUi.add(testUi);
        }
        return testsUi;
    }
}
