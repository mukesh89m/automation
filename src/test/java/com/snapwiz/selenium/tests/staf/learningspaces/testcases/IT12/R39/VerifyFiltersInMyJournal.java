package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R39;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;

public class VerifyFiltersInMyJournal extends Driver{
	
	@Test(priority = 1, enabled = true)
	public void verifyFiltersInMyJournal()
	{
		try
		{		
			new LoginUsingLTI().ltiLogin("185");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Bookmark().bookmark("lesson");//bookmark the lesson
			new Bookmark().bookmark("widget");
			new Navigator().NavigateTo("My Journal");//go to My Journal
			//check for filters
			String filters = driver.findElement(By.id("ls-my-journal-filter-wrapper")).getText();
			if(!filters.contains("All Chapters"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Chapter Filters in 'My Journal' is absent.");
			}
			if(!filters.contains("All Sections"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Sections Filters in 'My Journal' is absent.");
			}
			List<WebElement> allElements = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
			 Thread.sleep(2000);
			allElements.get(0).click();  //click on all chapter filter
			Thread.sleep(2000);
		    driver.findElement(By.partialLinkText("Ch 1:")).click();//click on chapter then it is obvious that chapter are present under "All chapter" filter
		    Thread.sleep(2000);
		    allElements.get(0).click();  //select another chapter
		    driver.findElement(By.partialLinkText("Ch 3:")).click();//select another chapter
		    Thread.sleep(2000);
			String notice = driver.findElement(By.cssSelector("div[class='notification-message-body notification-type-warning-message-body']")).getText();
			if(!notice.equals("No data is available for selected filters. To see your \"My Notes\" entries, please change your filters."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On selecting a chapter with no entry No Journal entries found notification doesn't get displayed.");
			}
			driver.findElement(By.cssSelector("div[id='close-notification-dialog']")).click();
			Thread.sleep(2000);
			allElements.get(0).click();  //click on all chapter filter
			Thread.sleep(2000);
		    driver.findElement(By.partialLinkText("Ch 1:")).click();//click
		    Thread.sleep(2000);
		    List<WebElement> allElements1 = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
			allElements1.get(1).click();  //click on all section filter
			Thread.sleep(2000);
		    driver.findElement(By.partialLinkText("1.1")).click();//click on sub section then it is obvious that sub sections are present under "All Section" filter
		    Thread.sleep(2000);
            notice = driver.findElement(By.cssSelector("div[class='notification-message-body notification-type-warning-message-body']")).getText();
		    if(!notice.equals("No data is available for selected filters. To see your \"My Notes\" entries, please change your filters."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On selecting a section with no entry No Journal entries found notification doesn't get displayed.");
			}
		    driver.findElement(By.cssSelector("div[id='close-notification-dialog']")).click();
			Thread.sleep(2000);
            List<WebElement> allElements2 = driver.findElements(By.xpath("/*//*[starts-with(@id, 'sbSelector_')]"));
		    allElements2.get(0).click();  //click on all chapter filter
			Thread.sleep(2000);
		    driver.findElement(By.partialLinkText("Ch 2:")).click();//click
		    Thread.sleep(2000);
            notice = driver.findElement(By.cssSelector("div[class='notification-message-body notification-type-warning-message-body']")).getText();
            if(!notice.equals("No data is available for selected filters. To see your \"My Notes\" entries, please change your filters."))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("On selecting a section with no entry No Journal entries found notification doesn't get displayed.");
            }
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase verifyFiltersInMyJournal in class VerifyFiltersInMyJournal",e);
		}
	}

	@Test(priority = 2, enabled = true)
	public void filterResetToDefault()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("216");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Bookmark().bookmark("lesson");//bookmark the lesson
			new Navigator().NavigateTo("My Journal");//go to My Journal
			//check for filters
			String filters = driver.findElement(By.id("ls-my-journal-filter-wrapper")).getText();
			if(!filters.contains("All Chapters"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Chapter Filters in 'My Journal' is absent.");
			}
			if(!filters.contains("All Sections"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Sections Filters in 'My Journal' is absent.");
			}
			boolean isPresent = new Bookmark().isPresentInMyJournal("lesson");
			if(isPresent == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("All journal entries aren't displayed over timeline.");
			}
			List<WebElement> allElements = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
			Thread.sleep(2000);
			allElements.get(0).click();  //click on all chapter filter
			Thread.sleep(2000);
		    driver.findElement(By.partialLinkText("Ch 1:")).click();//click on chapter1
		    Thread.sleep(2000);
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new Navigator().NavigateTo("My Journal");//come back to My Journal
			//check for filters
			String filters1 = driver.findElement(By.id("ls-my-journal-filter-wrapper")).getText();
			if(!filters1.contains("All Chapters"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After going to some other page and coming back again to My Journal the Filters are not set to default.");
			}
			if(!filters1.contains("All Sections"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After going to some other page and coming back again to My Journal the Filters are not set to default.");
			}
			boolean isPresent1 = new Bookmark().isPresentInMyJournal("lesson");
			if(isPresent1 == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("All journal entries aren't displayed over timeline.");
			}
			//list all weeks
			List<WebElement> allweeks = driver.findElements(By.xpath("//a[starts-with(@class, 'weeks')]"));
			Thread.sleep(2000);
			allweeks.get(1).click();  //selecting a week having no activity
			Thread.sleep(2000);
			String notice = driver.findElement(By.cssSelector("div[class='notification-message-body notification-type-warning-message-body']")).getText();
			if(!notice.equals("No data is available for selected timeline. To see your \"My Notes\" entries, please select a different timeline."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On selecting a week having no activity from right navigation then notification doesn't get displayed.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase filterResetToDefault in class VerifyFiltersInMyJournal",e);
		}
	}
	
	@Test(priority = 3, enabled = true)
	public void allSectionFilter()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("189");
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new TOCShow().tocHide();
			new Bookmark().bookmark("lesson");//bookmark the lesson
			new Navigator().NavigateTo("My Journal");//go to My Journal
			int isEnabled = driver.findElements(By.cssSelector("div[class='sbHolder sectionSelecBoxDisabled']")).size();
			if(isEnabled == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In my Journal page the All Section Dropdown is disabled when all chapter option is selected.");
			}
			List<WebElement> allElements = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
			if(!allElements.get(1).getText().equals("All Sections"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In My journal page, Default value selected in section filter is not 'All Sections'.");
			}
			allElements.get(0).click();  //click on all chapter filter
			Thread.sleep(2000);
		    driver.findElement(By.partialLinkText("Ch 1:")).click();//click
		    Thread.sleep(2000);
		    List<WebElement> allElements1 = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
		    allElements1.get(1).click();  //click on all section filter
		    Thread.sleep(2000);
		    List<WebElement> allSection = driver.findElements(By.className("overview"));
		    if(allSection.get(1).getText() == null || allSection.get(1).getText() == "")
		    {
		    	new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In my Journal page the All Section dropdown is not showing its option when a particular chapter from All Chapter dropdown is selected.");
		    }
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase allSectionFilter in class VerifyFiltersInMyJournal",e);
		}
	}

}
