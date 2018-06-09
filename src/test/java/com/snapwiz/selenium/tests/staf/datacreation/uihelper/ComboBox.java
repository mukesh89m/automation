package com.snapwiz.selenium.tests.staf.datacreation.uihelper;

import com.snapwiz.selenium.tests.staf.datacreation.Driver;
import com.snapwiz.selenium.tests.staf.datacreation.testcases.UpdateContentIndexEdulastic;
import com.snapwiz.selenium.tests.staf.datacreation.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class ComboBox extends Driver {

	public void selectValue(int index,String value)
	{
		try {
        index++;
		//driver.findElement(By.xpath("//*[@id='home-course-drop-down-wrapper']/div/a")).click();
        driver.findElement(By.xpath("//*[@id='course-drop-down-selectbox']/div/a")).click();
		//allElements.get(index).click();
        WebElement element=Driver.driver.findElement(By.cssSelector("a[rel='"+value+"']"));
        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].scrollIntoView(true);",element);
	    driver.findElement(By.cssSelector("a[rel='"+value+"']")).click();
		}
		catch(Exception e)
		{
            System.out.println("Not able to select value "+value);
            new UpdateContentIndexEdulastic().writeToFile("","","","Not able to select course id "+value,"");
		}
	}

	public void selectValuebycssselector(String value,String cssselector)
	{
		try
		{
			new com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click().clickBycssselector(cssselector);
			driver.findElement(By.linkText(value)).click();
		}
		catch(Exception e)
		{
			Assert.fail("Exception in UI helper selectValuebycssselector",e);
		}
	}

    public void selectValueByScrollToView(String valueToExpandDropDown, String value)
    {
        try {

            new com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click().clickbylinkText(valueToExpandDropDown);
            WebElement element=driver.findElement(By.xpath("//a[text()='"+value+"']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
            Thread.sleep(500);
            new com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click().clickbylinkText(value);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in UI helper selectValueByScrollToView",e);
        }
    }

    public void selectValueByScrollToViewByXpath(String xpath, String value)
    {
        try {

            new com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click().clickbyxpath(xpath);
            WebElement element=Driver.driver.findElement(By.xpath("//a[text()='"+value+"']"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].scrollIntoView(true);",element);
            Thread.sleep(500);
            new Click().clickbylinkText(value);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in UI helper selectValueByScrollToView",e);
        }
    }

    public void selectValue(String selector,String value)
    {
        try
        {
            Select dropdown;
            dropdown = new Select(driver.findElement(By.id(selector)));
            dropdown.selectByValue(value);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper selectValue in class DropDown",e);
        }

    }
}
