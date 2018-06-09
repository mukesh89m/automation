package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


import com.snapwiz.selenium.tests.staf.orion.Driver;


public class SearchResultShouldAsPerStudentInput 
{
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.orion.apphelper.SearchResultShouldAsPerStudentInput");
	
	public void searchResultShouldAsPerStudentInput(String sendKey, String searchResult1, String searchResult2)
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
		 
		  if(str1.trim().equals(searchResult1) && str2.trim().equals(searchResult2))
		  {
			  logger.log(Level.INFO,"Search results are coming based on search text given by student");
		  }
		  else
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
