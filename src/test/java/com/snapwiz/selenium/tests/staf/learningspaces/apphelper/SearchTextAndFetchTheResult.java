package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import junit.framework.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class SearchTextAndFetchTheResult extends Driver{
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases");
	
	public String[] searchTextAndFetchTheResult(String glossaryStringToSearch)
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).clear();
			driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).sendKeys(glossaryStringToSearch);
			Thread.sleep(5000);
			driver.findElement(By.cssSelector("div[class='ls-search-icon']")).click();
			Thread.sleep(5000);
			List<String> stringarray = new ArrayList<String>();
			
			List<WebElement> allElements = driver.findElements(By.xpath("//*[starts-with(@class, 'ls-result-view-title')]"));
		     for (WebElement element: allElements) 
		     {         
		    	 
				stringarray.add(element.getText());
		    	
		     }
		     String [] searchresults = stringarray.toArray(new String[stringarray.size()]);
		     stringarray = null;
		     allElements = null;
		     //System.out.println("The text content for "+resultIndex+"th card is "+(allElements.get(resultIndex)).getText());
		     return searchresults;
		     
		}
		catch(Exception e)
		{
			e.printStackTrace();
			TestCase.fail();
			logger.log(Level.SEVERE,"Exception in SearchTextAndFetchTheResult",e);
			return null;
		}
	}
}
