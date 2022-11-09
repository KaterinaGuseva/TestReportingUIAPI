package utils.jsutils;

import aquality.selenium.browser.AqualityServices;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.JavascriptExecutor;

@UtilityClass
public class JavaScriptMethods {

    private JavascriptExecutor js = AqualityServices.getBrowser().getDriver();

    public void closeWindow() {
        js.executeScript(ScriptMethods.CLOSE_WINDOW.getMethodName());
    }
}
