package utils.uiutils;

import aquality.selenium.browser.AqualityServices;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

@UtilityClass
public class TakeScreenshot {

    public static void takeScreenshot(String pathScreenshot) {
        File screenshotFile = ((TakesScreenshot) AqualityServices.getBrowser().getDriver()).getScreenshotAs(OutputType.FILE);
        File file = new File(pathScreenshot);
        try {
            FileUtils.copyFile(screenshotFile, file);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
