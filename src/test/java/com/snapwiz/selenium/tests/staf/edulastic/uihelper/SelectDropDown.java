package com.snapwiz.selenium.tests.staf.edulastic.uihelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

/**
 * Created by pragya on 28-05-2015.
 */
public class SelectDropDown {

    public void selectByText(WebElement we, String text){
        try{
            Select sel = new Select(we);
            sel.selectByVisibleText(text);

        }catch (Exception e){
            Assert.fail("Exception in app helper 'selectByText' in class 'SelectDropDown'", e);

        }
    }

    public String getSelectedValue(WebElement we)
    {
        String dropdownValue = null;
        try
        {

            Select sel = new Select(we);
            dropdownValue = sel.getFirstSelectedOption().getText();

        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper getSelectedValue in class SelectDropDown",e);
        }
        return dropdownValue;
    }
}
