package com.snapwiz.selenium.tests.staf.orion.uihelper;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.testng.Assert;

/**
 * Created by mukesh on 18/11/15.
 */
public class ScrollElement extends Driver {
    WebDriver driver=Driver.getWebDriver();
    JavascriptExecutor jse = (JavascriptExecutor)driver;

    public void scrollWindowDown() {
        try {
            jse.executeScript("scroll(0, 250)"); //y value '250' can be altered
        } catch (Exception e) {
            Assert.fail("Exception in method scrollWindowDown in class ScrollElement", e);
        }
    }

    public void scrollWindowUp() {
        try {
            jse.executeScript("scroll(250, 0)"); //x value '250' can be altered
        } catch (Exception e) {
            Assert.fail("Exception in method scrollWindowUp in class ScrollElement", e);
        }
    }

    public void scrollBottomOfPage() {
        try {
            jse.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        } catch (Exception e) {
            Assert.fail("Exception in method scrollBottomOfPage in class ScrollElement", e);
        }
    }

    public void scrollBottomOfPageUsingActionClass() {
        try {
            Actions actions = new Actions(driver);
            actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
        } catch (Exception e) {
            Assert.fail("Exception in method scrollBottomOfPageUsingActionClass in class ScrollElement", e);
        }
    }

    public void scrollToViewOfElement(WebElement element)  {
        try {
            jse.executeScript("arguments[0].scrollIntoView(true);",element);
        } catch (Exception e) {
            Assert.fail("Exception in method scrollToViewOfElement in class ScrollElement", e);
        }
    }

    public void scrollToElementUsingCoordinates(WebElement element)  {
        try {
           /* Point hoverItem =element.getLocation();
            ((JavascriptExecutor)driver).executeScript("return window.title;");
            Thread.sleep(6000);
            ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,"+(hoverItem.getY())+");");
*/

            Coordinates coordinate = ((Locatable)element).getCoordinates();
            coordinate.onPage();
            coordinate.inViewPort();
        } catch (Exception e) {
            Assert.fail("Exception in method scrollToElementUsingCoordinates in class ScrollElement", e);
        }
    }
}
