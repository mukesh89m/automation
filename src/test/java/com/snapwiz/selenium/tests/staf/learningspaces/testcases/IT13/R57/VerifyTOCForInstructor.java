package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R57;

import java.util.ArrayList;
import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;

public class VerifyTOCForInstructor extends Driver{

	@Test(priority = 1, enabled = true)
	public void verifyTOC()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("53");//login as instructor
            new Navigator().NavigateTo("Learning Content");//navigate to Learning Content
            new UIElement().waitAndFindElement(By.cssSelector("i[class='close-study-plan-icon close-study-plan']"));

            //TC row no. 53
		    int tocSize = driver.findElements(By.cssSelector("div[class='toc-container']")).size();
		    if(tocSize == 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("TOC not in expanded mode when land on eTextbook(for instructor).");
		    }
		    //TC row no. 54
		    int searchboxSize = driver.findElements(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).size();
		    if(searchboxSize == 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("At the left top there is no search box present.");
		    }
		    String searchIcon = driver.findElement(By.cssSelector("i[class='ls-search-chapter-icon ls-toc-sprite']")).getCssValue("height");
		    if(!searchIcon.contains("20px"))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Search icon is absent in the TOC(for instructor).");
		    }
		    //TC row no. 61
		    String closeIcon = driver.findElement(By.cssSelector("i[class='close-study-plan-icon close-study-plan']")).getCssValue("background-image");
		    if(!closeIcon.contains("ls-eTextbook-close-icon.png"))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("No \"x\" icon to close the TOC block.");
		    }
		   
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyTOC in class VerifyTOCForInstructor.",e);
		}
	}
	@Test(priority = 2, enabled = true)
	public void searchTextInTOC()
	{
		try
		{
		    new LoginUsingLTI().ltiLogin("65");//login as instructor
		    new Navigator().NavigateTo("Learning Content");//navigate to Learning Content
		    List<String> stringarray = new ArrayList<String>();
			List<WebElement> allSecondColumn = driver.findElements(By.cssSelector("div[class='topic-card toc-top-border']"));
			for (WebElement element: allSecondColumn) 
		           stringarray.add(element.getText());
			String [] searchresults = stringarray.toArray(new String[stringarray.size()]);
		    driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).click();
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).clear();
			driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).sendKeys("Introduction");
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("div[class='ls-search-icon']")).click();//click on search icon
			Thread.sleep(2000);
			 //TC row no. 65
			String searchResult = driver.findElement(By.className("ls-result-view-title")).getText();
            System.out.println("Search result:"+searchResult);
            if(searchResult == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Search for a text in search block is not working(for instructor).");
			}
			//String randString = new RandomString().randomstring(2);
			driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).click();
		    Thread.sleep(2000);
			driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).clear();
			driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).sendKeys("xpqjsdh");
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("div[class='ls-search-icon']")).click();//click on search icon
			Thread.sleep(10000);
			//TC row no. 66
			String searchResult1 = driver.findElement(By.className("ls-search-results-not-found")).getText();
			if(!searchResult1.contains("No Results Found"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("No Results Found message is not shown for which data is not present(for instructor).");
			}
			List<String> stringarray1 = new ArrayList<String>();
			List<WebElement> allSecondColumn1 = driver.findElements(By.cssSelector("div[class='topic-card toc-top-border']"));
			for (WebElement element: allSecondColumn1) 
		           stringarray1.add(element.getText());
			String [] searchresults1 = stringarray1.toArray(new String[stringarray1.size()]);
		    boolean misMatchFound = false;	
			if (searchresults.length != searchresults1.length) 
				misMatchFound = true;
		    for (int i = 0; i < searchresults.length; i++)
		        if (!searchresults[i].equals(searchresults1[i]))
		        	misMatchFound = true;
		    misMatchFound = false;
		    //TC row no. 67
		    if(misMatchFound == true)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On searching texts from TOC, the second and third column the data doesnt remain the same.");
		    }
		    //driver.findElement(By.cssSelector("i[class='close-search-results search-close ']")).click();//click to close search result
            new Click().clickbyxpath("//*[.='Done']");//click on the done
		    Thread.sleep(2000);
		    //TC row no. 69
		    String toc = driver.findElement(By.className("chapter-heading-label")).getText();
		    if(!toc.contains("Chapter"))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After closing toc search result the TOC is not dispalyed with chapters(for instructor).");
		    }
		    
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase searchTextInTOC in class VerifyTOCForInstructor.",e);
		}
	}
}
