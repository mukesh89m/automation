package com.snapwiz.selenium.tests.staf.dummies.apphelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


import com.snapwiz.selenium.tests.staf.dummies.Driver;


public class SearchResultShouldAsPerStudentInput 
{
	
	public void searchResultShouldAsPerStudentInput(String sendKey)
	{
		try
		{
	       
		  Driver.driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).sendKeys(sendKey);
		  Thread.sleep(2000);
		  Driver.driver.findElement(By.cssSelector("div[class='ls-search-icon']")).click();
		  Thread.sleep(5000);
		  List<WebElement> allElements = Driver.driver.findElements(By.cssSelector("a[class='toc-auto-suggest-label']"));
		 
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
