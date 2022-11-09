package utils.uiutils;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IElement;
import lombok.experimental.UtilityClass;
import org.openqa.selenium.By;

import java.util.List;

import static aquality.selenium.browser.AqualityServices.getElementFactory;

@UtilityClass
public class TableMethods {

    public static List<IElement> getRows(String locator) {
        return AqualityServices.getConditionalWait().
                waitFor(Waits.visibilityOfAllElements(getElementFactory().findElements
                (By.xpath(locator), ElementType.LABEL)));
    }

    public static List<IElement> getColumnHeadings(String locator) {
        return getElementFactory().findElements
                (By.xpath(locator), ElementType.LABEL);
    }

    public static String getColumnHeadingName(String locator) {
        return getElementFactory().getLabel(By.xpath(locator), "Table column").getText();
    }
}
