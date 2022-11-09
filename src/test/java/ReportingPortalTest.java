import aquality.selenium.browser.AqualityServices;
import aquality.selenium.browser.Browser;
import configurations.TestConfiguration;
import constants.ConfigurationConstants;
import constants.UiConstants;
import data.ContentType;
import org.openqa.selenium.Cookie;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.NewProjectPage;
import pages.ProjectPage;
import pages.ProjectsPage;
import pogo.TestModel;
import utils.apiutils.ApiQueryParameters;
import utils.apiutils.ReportingPortalMethodsApi;
import utils.commonutils.Base64Converting;
import utils.commonutils.CreateUrl;
import utils.uiutils.TakeScreenshot;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

public class ReportingPortalTest extends BaseTest {

    CreateUrl uiUrl = new CreateUrl();
    private String idTest;
    private List<TestModel> testsUi = new ArrayList<>();
    private List<TestModel> testsApi = new ArrayList<>();
    private TestConfiguration testUiData = new TestConfiguration(ConfigurationConstants.UI_DATA_FILE);
    private TestConfiguration testApiData = new TestConfiguration(ConfigurationConstants.API_DATA_FILE);

    public String getUiDataValue(String jsonKeyName){
        return testUiData.getTestConfiguration(jsonKeyName);
    }

    public String getApiDataValue(String jsonKeyName){
        return testApiData.getTestConfiguration(jsonKeyName);
    }

    @Test
    public void checkTestCases(Method testMethod) {

        String token = ReportingPortalMethodsApi.getToken(getApiDataValue(
                ConfigurationConstants.KEY_TASK_VARIANT));
        Assert.assertNotNull(token, "Token value shouldn't be empty");
        Browser browser = AqualityServices.getBrowser();
        browser.maximize();
        browser.goTo(uiUrl.getUiUrl());
        browser.waitForPageToLoad();
        browser.getDriver().manage().addCookie(new Cookie("token", token));
        browser.refresh();
        ProjectsPage projectsPage = new ProjectsPage();
        Assert.assertEquals(projectsPage.getVersion(),
                getUiDataValue(ConfigurationConstants.KEY_VERSION_TASK),
                "The page version should match the task variant");

        String idProject = projectsPage.getIdSelectedProject(
                getUiDataValue(ConfigurationConstants.KEY_PROJECT_NAME));
        projectsPage.openSelectedProjectPage(
                getUiDataValue(ConfigurationConstants.KEY_PROJECT_NAME));

        ProjectPage selectedProjectPage = new ProjectPage(getUiDataValue(
                ConfigurationConstants.KEY_PROJECT_NAME));
        selectedProjectPage.waitTestsLoad();
        testsUi = selectedProjectPage.setTableTestsDataToClass();
        List<String> startTimeTests = testsUi.stream().map(TestModel::getStartTime).collect(Collectors.toList());
        List<String> sortedStartTimeTests = startTimeTests.stream().sorted(Collections.reverseOrder()).
                collect(Collectors.toList());
        Assert.assertEquals(startTimeTests, sortedStartTimeTests,
                "Tests should be sorted descending by Start Time");

        testsApi = ReportingPortalMethodsApi.getTests(idProject, TestModel.class);
        List<TestModel> sortedTestsOnTimeApiList = testsApi.stream()
                .sorted((o1, o2) -> o2.getStartTime().compareTo(o1.getStartTime()))
                .limit(testsUi.size())
                .collect(Collectors.toList());

        for (int i = 0; i < testsUi.size(); i++) {
            Assert.assertEquals(testsUi.get(i), sortedTestsOnTimeApiList.get(i), "Data of UI tests and API tests should match");
        }

        selectedProjectPage.openProjectsPage();
        projectsPage.openAddNewProjectPage();
        NewProjectPage newProjectPage = new NewProjectPage();
        String uniqueProjectName = sessionId + getUiDataValue(
                ConfigurationConstants.KEY_NEW_PROJECT_NAME);
        newProjectPage.inputNewProject(uniqueProjectName);
        newProjectPage.saveProject();
        Assert.assertEquals(newProjectPage.getSavedProjectMessage(),
                String.format(getUiDataValue(ConfigurationConstants.MESSAGE_SAVE_PROJECT), uniqueProjectName),
                String.format("Message about saved project should be %s %s",
                        getUiDataValue(ConfigurationConstants.MESSAGE_SAVE_PROJECT), uniqueProjectName));
        newProjectPage.closeNewProjectPage();
        projectsPage.switchToCurrentWindow();
        browser.refresh();
        projectsPage.openSelectedProjectPage(uniqueProjectName);
        idTest = ReportingPortalMethodsApi.getIdPostedTest(
                ApiQueryParameters.getMapParamsPostedTest(sessionId, uniqueProjectName,
                        getClass().getName(), testMethod.getName(),
                        uiUrl.getConfigurationValue(ConfigurationConstants.KEY_DOMAIN_NAME)));
        selectedProjectPage.waitTestsLoad();
        selectedProjectPage.waitNewTestAdd();
        String screenshotAbsolutePath = String.format(UiConstants.SCREENSHOT_PATH, sessionId);
        TakeScreenshot.takeScreenshot(screenshotAbsolutePath);
        ReportingPortalMethodsApi.sendApplication(ApiQueryParameters.getMapParamsSendApplication(
                idTest, Base64Converting.encodeImageToBase64(screenshotAbsolutePath),
                ContentType.TYPE_IMAGE.getContentType()));
        ReportingPortalMethodsApi.sendApplication(ApiQueryParameters.getMapParamsSendApplication(
                idTest, Base64Converting.encodeImageToBase64(UiConstants.LOG_PATH),
                ContentType.TYPE_TEXT.getContentType()));

        List<TestModel> newProjectTests = selectedProjectPage.setTableTestsDataToClass();
        TestModel newTest = newProjectTests.stream().findFirst().orElseThrow(()->
                new NoSuchElementException("Test isn't found"));
        Assert.assertEquals(newTest.getName(), getClass().getName(),
                String.format("Test with name %s should be added", getClass().getName()));
        Assert.assertEquals(newTest.getMethod(), testMethod.getName(),
                String.format("Test with method %s should be added", testMethod.getName()));
    }
}
