package com.snapwiz.selenium.tests.staf.edulastic.uihelper;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

/**
 * Created by pragya on 12-01-2015.
 */
public class DragAndDrop extends Driver {

    public void dragAndDrop(WebElement src,WebElement dst) {
        try {
            WebDriver driver=Driver.getWebDriver();
            Actions ac = new Actions(driver);
            ac.dragAndDrop(src,dst).build().perform();
        } catch (Exception e) {
            Assert.fail("Exception in method dragAndDrop of uihelper DragAndDrop ", e);
        }

    }
}
