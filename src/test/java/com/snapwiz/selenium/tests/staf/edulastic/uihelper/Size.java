package com.snapwiz.selenium.tests.staf.edulastic.uihelper;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * Created by root on 8/8/14.
 * Description - Returns the size of the element based on the locator passed. If the element is not present it will return 0.
 */
public class Size extends Driver {

    public int getSizeofElement(String locatorType,String locator)
    {
        int size = -1;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            if(locatorType.equals("id"))
                size = driver.findElements(By.id(locator)).size();

            else if(locatorType.equals("class"))
                size = driver.findElements(By.className(locator)).size();

            else if(locatorType.equals("cssselector"))
                size = driver.findElements(By.cssSelector(locator)).size();
        }
        catch (Exception e)
        {
            Assert.fail("Exception in UI helper getSizeofElementById in class Size",e);
        }
        return size;
    }
}
