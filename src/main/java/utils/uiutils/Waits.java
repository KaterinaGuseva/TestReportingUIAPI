package utils.uiutils;

import aquality.selenium.elements.interfaces.IElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

public class Waits {

    public static ExpectedCondition<List<IElement>> visibilityOfAllElements(
            final List<IElement> elements) {
        return new ExpectedCondition<List<IElement>>() {
            @Override
            public List<IElement> apply(WebDriver driver) {
                for (IElement element : elements) {
                    if(!element.state().isDisplayed()) {
                        return null;
                    }
                }
                return elements.size() > 0 ? elements : null;
            }
            @Override
            public String toString() {
                return "visibility of all " + elements;
            }
        };
    }
}
