package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelperAppium;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class ComboBox {

	public void selectValue(int index,String value)
	{
		try
		{
		List<WebElement> allElements = Driver.driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
		 Thread.sleep(2000);
		 allElements.get(index).click();
 		Thread.sleep(2000);
	    Driver.driver.findElement(By.linkText(value)).click();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in UI helper ComboBox",e);
		}
	}
	
	public void selectValuebycssselector(String value,String cssselector)
	{
		try
		{
			new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.Click().clickBycssselector(cssselector);
			Driver.driver.findElement(By.linkText(value)).click();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in UI helper selectValuebycssselector",e);
		}
	}

    public void selectValueByScrollToView(String valueToExpandDropDown, String value)
    {
        try {

            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.Click().clickbylinkText(valueToExpandDropDown);
            WebElement element=Driver.driver.findElement(By.xpath("//a[text()='"+value+"']"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].scrollIntoView(true);",element);
            Thread.sleep(500);
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.Click().clickbylinkText(value);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in UI helper selectValueByScrollToView",e);
        }
    }

    public void selectValueByScrollToViewByXpath(String xpath, String value)
    {
        try {

            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.Click().clickbyxpath(xpath);
            WebElement element=Driver.driver.findElement(By.xpath("//a[text()='"+value+"']"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].scrollIntoView(true);",element);
            Thread.sleep(500);
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.Click().clickbylinkText(value);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in UI helper selectValueByScrollToView",e);
        }
    }
}
