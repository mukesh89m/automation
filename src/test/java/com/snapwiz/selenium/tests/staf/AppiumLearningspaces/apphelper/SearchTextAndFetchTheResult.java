package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import junit.framework.TestCase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;

public class SearchTextAndFetchTheResult {
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.AppiumLearningspaces.testcases");
	
	public String[] searchTextAndFetchTheResult(String glossaryStringToSearch)
	{
		try
		{
			Driver.driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).clear();
			Driver.driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).sendKeys(glossaryStringToSearch);
			Thread.sleep(5000);
			Driver.driver.findElement(By.cssSelector("div[class='ls-search-icon']")).click();
			Thread.sleep(5000);
			List<String> stringarray = new ArrayList<String>();
			
			List<WebElement> allElements = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'ls-result-view-title')]"));
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
