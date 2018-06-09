package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;


public class SearchResultShouldAsPerStudentInput extends Driver
{
	
	public void searchResultShouldAsPerStudentInput(String sendKey)
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
		  	driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).sendKeys(sendKey);
		    Thread.sleep(2000);
		    driver.findElement(By.cssSelector("div[class='ls-search-icon']")).click();
		    Thread.sleep(5000);
		    List<WebElement> allElements = driver.findElements(By.cssSelector("a[class='toc-auto-suggest-label']"));

		    String str1 = allElements.get(0).getText();
		    String str2 = allElements.get(1).getText();

              if(str1.length() == 0 || str2.length() == 0)
              {
                  Assert.fail("Search results are not coming based on search text given by student");
              }
		}
	  catch(Exception e)
	    {
		  e.printStackTrace();
		  Assert.fail("Exception in App Helper SearchResultShouldAsPerStudentInput",e);
		
	    }
	}

}
