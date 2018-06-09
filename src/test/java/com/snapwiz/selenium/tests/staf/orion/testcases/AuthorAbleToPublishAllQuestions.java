package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectCourse;

public class AuthorAbleToPublishAllQuestions
{
	@Test
	public void publishquestion()
	{
		try
		{
			Driver.startDriver();
			String[] tloid=ReadTestData.readData("tloids", "0");
			String assignmentname=ReadTestData.readDataByTagName("", "assessmentname", "405");
			String chaptername=ReadTestData.readDataByTagName("chapternames", "chapter1", "0");
			new Assignment().create(405);//create practice test at chapter 1
			new Assignment().addQuestions(406, "qtn-type-true-false-img", assignmentname, false, false,null, false,Integer.parseInt(tloid[0]),Integer.parseInt(tloid[1]));//add question 2
			new Assignment().addQuestions(407, "qtn-type-true-false-img", assignmentname, false, false,null, false,Integer.parseInt(tloid[0]),Integer.parseInt(tloid[1]));	//add question 2
			new DirectLogin().CMSLogin();	//login as publisher		
			new SelectCourse().selectcourse();
			Thread.sleep(1000);
			Driver.driver.findElement(By.cssSelector("div[title='"+chaptername+"']")).click();//select chapter name
			Actions ac=new Actions(Driver.driver);
			WebElement we=Driver.driver.findElement(By.cssSelector("div[title='"+assignmentname+"']"));//hover assignment name
			ac.moveToElement(we).build().perform();
			List<WebElement> allpublish=Driver.driver.findElements(By.cssSelector("span[class='publish publish-category-assessment']"));//fetch list of all the question type
			int ins=allpublish.size();
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",allpublish.get(ins-1));
			Thread.sleep(4000);
			boolean popup=Driver.driver.findElement(By.className("cms-assessment-publish-questions-title")).isDisplayed();
			if(popup==false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("after click on publish link pop up  not open");
			}				
			List<WebElement> allquestiontype=Driver.driver.findElements(By.className("question-publish-status-check"));	//fetch type of question under publish link		
			int questiontypes=allquestiontype.size();			
			if(questiontypes!=8) //removed DEACTIVATE & EXPIRED status
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("All status not listed even if there are no questions associated.");
			}
			Driver.driver.findElement(By.id("cms-cassessment-publish-questions-btn")).click();//click on publish button
			Thread.sleep(2000);
			String poptext=Driver.driver.findElement(By.cssSelector("div[class='cms-notification-message-body-title two-line-title']")).getText();
			if(!poptext.equals("Please select the questions to publish."))
				Assert.fail("notification text not shown.");
			Driver.driver.findElement(By.cssSelector("input[status='80']")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("cms-cassessment-publish-questions-btn")).click();
			Thread.sleep(2000);
			String poptext1=Driver.driver.findElement(By.cssSelector("div[class='cms-notification-message-body-title two-line-title']")).getText();//fetch text of notification
			if(!poptext1.equals("Publish action cannot be done as questions selected are already published."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("notification text not shown after puublish question try to publish.");
			}
			Driver.driver.findElement(By.cssSelector("input[status='80']")).click();//click on check box which in publish
			Driver.driver.findElement(By.cssSelector("input[status='10']")).click();//click on check box which in draft
			Thread.sleep(5000);
			Driver.driver.findElement(By.id("cms-cassessment-publish-questions-btn")).click();
			Thread.sleep(5000);
			String popuptextString=Driver.driver.findElement(By.className("cms-notification-message-body")).getText();//fetch text of notification
			if(!popuptextString.contains("You are about to publish selected questions. Do you want to continue?"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("notification not deliver.");
			}
			
			Driver.driver.findElement(By.cssSelector("span[class='cms-notification-message-back-to-question cms-notification-cancel-publish-content']")).click();//click on cancel
			Driver.driver.findElement(By.id("cms-cassessment-publish-questions-btn")).click();
			Thread.sleep(5000);
			Driver.driver.findElement(By.cssSelector("span[class='cms-notification-message-ignore-changes cms-notification-yes-publish-content']")).click();//click on yes to publish
			Thread.sleep(15000);
			String textafterpublishquestion=Driver.driver.findElement(By.cssSelector("div[class='cms-notification-message-body-title two-line-title']")).getAttribute("innerHTML");//fetch text of notification
			if(!textafterpublishquestion.contains("All selected questions for this assessment have been published"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After  pulish the all question notification not shown.");	
			}
		}			
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase publishquestion in class AuthorAbleToPublishAllQuestions",e);
		}
	}
	
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
	
}
