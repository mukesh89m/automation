package com.snapwiz.selenium.tests.staf.learnon.apphelper;

import com.snapwiz.selenium.tests.staf.learnon.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class DropDown {

	    public void selectValue(String selectorType, String selector,String value)
	    {
	        try
	        {
	            Select dropdown;
	            dropdown = new Select(Driver.driver.findElement(By.className(selector)));
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

	           Select dropdown = new Select(Driver.driver.findElement(By.className(selector)));
	           dropdownValue = dropdown.getFirstSelectedOption().getText();

	        }
	        catch (Exception e)
	        {
	            Assert.fail("Exception in app helper selectValue in class DropDown",e);
	        }
	        return dropdownValue;
	    }
	
}
