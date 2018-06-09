package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R57;

import java.util.ArrayList;
import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class VerifyTOC extends Driver{

	@Test(priority = 1, enabled = true)
	public void verifyTOC()
	{
		try
		{
		    new LoginUsingLTI().ltiLogin("2");//login as student
            new Navigator().NavigateTo("Learning Content");//navigate to Learning Content
            new UIElement().waitAndFindElement(By.cssSelector("i[class='close-study-plan-icon close-study-plan']"));
            //TC row no. 2
		    int tocSize = driver.findElements(By.cssSelector("div[class='toc-container']")).size();
		    if(tocSize == 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("TOC not in expanded mode when land on eTextbook.");
		    }
		    //TC row no. 3
		    int searchboxSize = driver.findElements(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).size();
		    if(searchboxSize == 0)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("At the left top there is no search box present.");
		    }
		    String searchIcon = driver.findElement(By.cssSelector("i[class='ls-search-chapter-icon ls-toc-sprite']")).getCssValue("height");
            System.out.println("searchIcon"+searchIcon);
            if(!searchIcon.contains("24px"))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Search icon is absent in the TOC.");
		    }
		    String closeIcon = driver.findElement(By.cssSelector("i[class='close-study-plan-icon close-study-plan']")).getCssValue("background-image");
            System.out.println("image:"+closeIcon);
            if(!closeIcon.contains("ls-eTextbook-close-icon.png"))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("No \"x\" icon to close the TOC block.");
		    }

		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyTOC in class VerifyTOC.",e);
		}
	}
	@Test(priority = 2, enabled = true)
	public void searchTextInTOC()
	{
		try
		{
		    new LoginUsingLTI().ltiLogin("14");//login as student
		    new Navigator().NavigateTo("Learning Content");//navigate to Learning Content
            new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(By.className("chapter-heading-label")));
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
			 //TC row no. 14
			String searchResult = driver.findElement(By.className("ls-result-view-title")).getText();
			if(searchResult == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Search for a text in search block is not working.");
			}
			String randString = new RandomString().randomstring(5);
			driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).click();
		    Thread.sleep(2000);
			driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).clear();
			driver.findElement(By.cssSelector("input[class='toc-sprite search-course-stream ui-autocomplete-input']")).sendKeys("xyz");
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("div[class='ls-search-icon']")).click();//click on search icon
			Thread.sleep(2000);	
			//TC row no. 15
			String searchResult1 = driver.findElement(By.className("ls-search-results-not-found")).getText();
			if(!searchResult1.contains("No Results Found"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("No Results Found message is not shown for which data is not present.");
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
		    //TC row no. 16
		    if(misMatchFound == true)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On searching texts from TOC, the second and third column the data doesnt remain the same.");
		    }
            new Click().clickbyxpath("//*[.='Done']");//click on the done

		    Thread.sleep(2000);
		    //TC row no. 18 & 24
		    String toc = driver.findElement(By.className("chapter-heading-label")).getText();
		    if(!toc.contains("Chapter"))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After closing toc search result the TOC is not dispalyed with chapters.");
		    }
            new Click().clickbyxpath("//span[@title='ORION Adaptive Practice']");
            Thread.sleep(1000);
		    new Click().clickbyxpath("//a[contains(@title,'Diagnostic')]");
		    //driver.findElement(By.partialLinkText("Diagnostic")).click();
		    Thread.sleep(2000);

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("input.ls-assessment-continue-btn")));
		    Thread.sleep(2000);
		    //TC row no. 41
		    String notice = driver.findElement(By.className("al-notification-message-wrapper")).getText();
		    if(!notice.contains("Please say how confident you feel about this chapter. You will get more accurate recommendations!")) {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("For a diagnostic test without selecting the confidence level click on right arrow icon the required notification is not displayed.");
            }

                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id(Integer.toString(4))));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("input.ls-assessment-continue-btn")));

		    //TC row no. 42
		    String question = new TextFetch().textfetchbyclass("al-diag-chapter-details");
		    if(!question.trim().contains("(1 of"))
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On starting a diagnostic test it doesnt take to first question of the diag test");
		    }
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase searchTextInTOC in class VerifyTOC.",e);
		}
	}
	@Test(priority = 3, enabled = true)
	public void classSectionName()
	{
		try
		{
		    new LoginUsingLTI().ltiLogin("101");//login as student
		    String sectionName = driver.findElement(By.cssSelector("div[class='ls-class-section-name ellipsis']")).getText();
		    if(sectionName == null)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("There is no class section name present in header in Dashboard at student side.");
		    }
		    new Navigator().NavigateTo("Learning Content");//navigate to Learning Content
		    String sectionName1 = driver.findElement(By.cssSelector("div[class='ls-class-section-name ellipsis']")).getText();
		    if(sectionName1 == null)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("There is no class section name present in header in Learning Content at student side.");
		    }
		    new Navigator().NavigateTo("Course Stream");//navigate to Course Stream
		    String sectionName2 = driver.findElement(By.cssSelector("div[class='ls-class-section-name ellipsis']")).getText();
		    if(sectionName2 == null)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("There is no class section name present in header in Course Stream at student side.");
		    }
		    new Navigator().NavigateTo("Assignments");//navigate to Assignments
		    String sectionName3 = driver.findElement(By.cssSelector("div[class='ls-class-section-name ellipsis']")).getText();
		    if(sectionName3 == null)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("There is no class section name present in header in Assignments at student side.");
		    }
		    new Navigator().NavigateTo("My Journal");//navigate to My Journal
		    String sectionName4 = driver.findElement(By.cssSelector("div[class='ls-class-section-name ellipsis']")).getText();
		    if(sectionName4 == null)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("There is no class section name present in header in My Journal at student side.");
		    }
		    new Navigator().NavigateTo("My Activity");//navigate to My Activity
		    String sectionName5 = driver.findElement(By.cssSelector("div[class='ls-class-section-name ellipsis']")).getText();
		    if(sectionName5 == null)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("There is no class section name present in header in My Activity at student side.");
		    }
		    new Navigator().NavigateTo("Proficiency Report");//navigate to Proficiency Report
		    String sectionName6 = driver.findElement(By.cssSelector("div[class='ls-class-section-name ellipsis']")).getText();
		    if(sectionName6 == null)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("There is no class section name present in header in Proficiency Report at student side.");
		    }
		    new Navigator().NavigateTo("Metacognitive Report");//navigate to Metacognitive Report
		    String sectionName7 = driver.findElement(By.cssSelector("div[class='ls-class-section-name ellipsis']")).getText();
		    if(sectionName7 == null)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("There is no class section name present in header in Metacognitive Report at student side.");
		    }
		    new Navigator().NavigateTo("Productivity Report");//navigate to Productivity Report
		    String sectionName8 = driver.findElement(By.cssSelector("div[class='ls-class-section-name ellipsis']")).getText();
		    if(sectionName8 == null)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("There is no class section name present in header in Metacognitive Report at student side.");
		    }
		    new Navigator().NavigateTo("Most Challenging Activities Report");//navigate to Most Challenging Activities Report
		    String sectionName9 = driver.findElement(By.cssSelector("div[class='ls-class-section-name ellipsis']")).getText();
		    if(sectionName9 == null)
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("There is no class section name present in header in Most Challenging Activities Report at student side.");
		    }
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase classSectionName in class VerifyTOC.",e);
		}
	}
}
