package utils.uiutils;

import aquality.selenium.browser.AqualityServices;
import lombok.experimental.UtilityClass;

import java.util.Set;

@UtilityClass
public class BrowserMethods {

    public void switchToSelectedWindow(String selectedWindow) {
        AqualityServices.getBrowser().getDriver().switchTo().window(selectedWindow);
    }

    public void switchToNewWindow(String currentWindow) {
        Set<String> allOpenWindows = AqualityServices.getBrowser().getDriver().getWindowHandles();
        String newWindow = null;
        for (String window:allOpenWindows) {
            if(!window.equals(currentWindow)) {
                newWindow = window;
                break;
            }
        }
        switchToSelectedWindow(newWindow);
    }
}
