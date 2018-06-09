package com.snapwiz.selenium.tests.staf.edulastic.uihelper;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

/**
 * Created by root on 18/8/14.
 */
public class DropDown extends Driver {

    public void selectValue(String selectorType, String selector,String value)
    {
        try
        {
            Select dropdown;
            WebDriver driver=Driver.getWebDriver();
            dropdown = new Select(driver.findElement(By.className(selector)));
            dropdown.selectByValue(value);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper selectValue in class DropDown",e);
        }

    }

    public String getSelectedValue(String selectorType, String selector)
    {
        String dropdownValue = null;
        try
        {
            WebDriver driver=Driver.getWebDriver();
           Select dropdown = new Select(driver.findElement(By.className(selector)));
           dropdownValue = dropdown.getFirstSelectedOption().getText();

        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper selectValue in class DropDown",e);
        }
        return dropdownValue;
    }
}
