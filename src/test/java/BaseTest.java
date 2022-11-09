import aquality.selenium.browser.AqualityServices;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import utils.apiutils.Specifications;
import utils.commonutils.CreateUrl;

public class BaseTest {

    protected String sessionId = String.valueOf(System.currentTimeMillis());

    @BeforeClass
    public void setUp() {
        CreateUrl apiUrl = new CreateUrl();
        RestAssured.requestSpecification = Specifications.requestSpecification(apiUrl.getApiUrl());

        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @AfterMethod
    public void tearDown() {
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }
}
