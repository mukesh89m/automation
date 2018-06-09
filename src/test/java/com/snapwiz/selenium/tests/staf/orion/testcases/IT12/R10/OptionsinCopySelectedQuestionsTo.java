package com.snapwiz.selenium.tests.staf.orion.testcases.IT12.R10;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.CMSActions;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectCourse;
import com.snapwiz.selenium.tests.staf.orion.uihelper.Click;

public class OptionsinCopySelectedQuestionsTo 
{
	
	@Test(priority = 1, enabled = true)
	public void notificationBySelectingDone()
	{
		try
		{
			Driver.startDriver();
			new DirectLogin().CMSLogin();
			Thread.sleep(2000);
			new SelectCourse().selectcourse();
			Thread.sleep(2000);
			new SelectCourse().selectChapterByIndex(0);
			Thread.sleep(2000);
			new SelectCourse().selectAssessmentByIndex(1);
			Thread.sleep(6000);
			new CMSActions().addQuestion("true-false", "Search true false Question in browse page - Test content");
			Thread.sleep(4000);
			Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
			Thread.sleep(5000);
			new CMSActions().searchQuestion("Search true false Question in browse page");
			Thread.sleep(2000);
			String checkbox1id =  Driver.driver.findElement(By.className("content-search-qtn-chk")).getAttribute("id");       // Click on CheckBox
		    Driver.driver.findElement(By.xpath("//label[@id='"+checkbox1id+"']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("img[title='Copy']")).click();
			Thread.sleep(4000);
			Driver.driver.findElement(By.cssSelector("a[title='Select a course']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(Config.course)).click();
			Thread.sleep(3000);
			Driver.driver.findElement(By.cssSelector("a[title='Select a chapter']")).click();
			Thread.sleep(5000);
			Driver.driver.findElement(By.partialLinkText("Ch 1:")).click();
			Thread.sleep(4000);
			new Click().clickByclassname("assessment-custom-radio-btn"); //checked check box
			Thread.sleep(4000);
			Driver.driver.findElement(By.className("add-assesments-in-contentSearch")).click();
			Thread.sleep(9000);
			String doneNotification = Driver.driver.findElement(By.tagName("body")).getText();
			if(!doneNotification.contains("1 Questions have been copied."))
			{
				Assert.fail("1 Questions have been copied notification is not displayed after click on Done");
			}			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase notificationBySelectingDone in class OptionsinCopySelectedQuestionsTo",e);
		}				
	}
	
	@Test(priority = 2, enabled = true)
	public void vefirycopiedquestion()
	{
		try
		{
			Driver.startDriver();
			new DirectLogin().CMSLogin();
			Thread.sleep(2000);
			new SelectCourse().selectcourse();
			Thread.sleep(2000);
			new SelectCourse().selectChapterByIndex(0);
			Thread.sleep(2000);
			new SelectCourse().selectAssessmentByIndex(1);
			Thread.sleep(6000);
			new CMSActions().addQuestion("true-false", "100005 Search true false Question in browse page - Test content");
			Thread.sleep(4000);
			Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
			Thread.sleep(5000);
			new CMSActions().searchQuestion("100005 Search true false Question in browse page - Test content");
			Thread.sleep(2000);
			int numberOfQs = Driver.driver.findElements(By.className("content-search-question-data")).size();	
			Thread.sleep(2000);
			String checkbox1id =  Driver.driver.findElement(By.className("content-search-qtn-chk")).getAttribute("id");       // Click on CheckBox
		    Driver.driver.findElement(By.xpath("//label[@id='"+checkbox1id+"']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("img[title='Copy']")).click();
			Thread.sleep(4000);
			Driver.driver.findElement(By.cssSelector("a[title='Select a course']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(Config.course)).click();
			Thread.sleep(3000);
			Driver.driver.findElement(By.cssSelector("a[title='Select a chapter']")).click();
			Thread.sleep(5000);
			Driver.driver.findElement(By.partialLinkText("Ch 1:")).click();
			Thread.sleep(4000);
			new Click().clickByclassname("assessment-custom-radio-btn"); //checked check box
			Thread.sleep(4000);
			Driver.driver.findElement(By.className("add-assesments-in-contentSearch")).click();
			Thread.sleep(12000);
			String doneNotification = Driver.driver.findElement(By.tagName("body")).getText();
			if(!doneNotification.contains("1 Questions have been copied."))
			{
				Assert.fail("1 Questions have been copied notification is not displayed after click on Done");
			}	
			Thread.sleep(4000);
			Driver.driver.findElement(By.id("refreshSearchResult")).click();
			Thread.sleep(4000);
			int afternumberOfQs = Driver.driver.findElements(By.className("content-search-question-data")).size();	
			if(afternumberOfQs!=2)
			{
				Assert.fail("Not able to copy the question");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase vefirycopiedquestion in class OptionsinCopySelectedQuestionsTo",e);
		}				
	}
	
	@Test(priority = 3, enabled = true)
	public void newQuestionSetForAcopiedquestion()
	{
		try
		{
			Driver.startDriver();
			new DirectLogin().CMSLogin();
			Thread.sleep(2000);
			new SelectCourse().selectcourse();
			Thread.sleep(2000);
			new SelectCourse().selectChapterByIndex(0);
			Thread.sleep(2000);
			new SelectCourse().selectAssessmentByIndex(1);
			Thread.sleep(6000);
			new CMSActions().addQuestion("true-false", "100007 New question Set - Copy a question from Search page, true false Question");
			Thread.sleep(4000);
			Driver.driver.findElement(By.id("saveQuestionDetails1")).click();
			Thread.sleep(5000);
			new CMSActions().searchQuestion("100007 New question Set - Copy a question from Search page, true false Question");
			Thread.sleep(2000);
			int numberOfQs = Driver.driver.findElements(By.className("content-search-question-data")).size();	
			Thread.sleep(2000);
			String checkbox1id =  Driver.driver.findElement(By.className("content-search-qtn-chk")).getAttribute("id");       // Click on CheckBox
		    Driver.driver.findElement(By.xpath("//label[@id='"+checkbox1id+"']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("img[title='Copy']")).click();
			Thread.sleep(4000);
			Driver.driver.findElement(By.cssSelector("a[title='Select a course']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText(Config.course)).click();
			Thread.sleep(3000);
			Driver.driver.findElement(By.cssSelector("a[title='Select a chapter']")).click();
			Thread.sleep(5000);
			Driver.driver.findElement(By.partialLinkText("Ch 4:")).click();
			Thread.sleep(4000);
			new Click().clickByclassname("assessment-custom-radio-btn"); //checked check box
			Thread.sleep(4000);
			Driver.driver.findElement(By.className("add-assesments-in-contentSearch")).click();
			Thread.sleep(12000);
			String doneNotification = Driver.driver.findElement(By.tagName("body")).getText();
			if(!doneNotification.contains("1 Questions have been copied."))
			{
				Assert.fail("1 Questions have been copied notification is not displayed after click on Done");
			}	
			Thread.sleep(4000);
			Driver.driver.findElement(By.id("refreshSearchResult")).click();
			Thread.sleep(4000);
			int afternumberOfQs = Driver.driver.findElements(By.className("content-search-question-data")).size();	
			if(afternumberOfQs!=2)
			{
				Assert.fail("Not able to copy the question");
			}
			Thread.sleep(2000);
			List<WebElement> editQs = Driver.driver.findElements(By.id("content-search-question-data-div"));
			Actions ac=new Actions(Driver.driver);
			ac.moveToElement(editQs.get(1)).build().perform();
			List<WebElement> editbox =Driver.driver.findElements(By.className("edit-question-content"));
			editbox.get(1).click();
			Thread.sleep(2000);
			boolean qsSetName = Driver.driver.findElement(By.tagName("body")).getText().contains("default-");
			if(qsSetName==false)
			{
				Assert.fail("New question_set is not created with the name as default-n");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase newQuestionSetForAcopiedquestion in class OptionsinCopySelectedQuestionsTo",e);
		}				
	}
	

	@AfterMethod
	 public void tearDown() throws Exception
	 {
	 Driver.driver.quit();
	 }
}
