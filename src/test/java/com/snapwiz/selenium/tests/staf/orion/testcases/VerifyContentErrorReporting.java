package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.ReportContentIssue;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;

public class VerifyContentErrorReporting {
	@Test(priority = 1, enabled = true)
	public void verifyContentErrorReporting()
	{
		try
		{
			Driver.startDriver();
			String course = Config.course;
			String chapterName = ReadTestData.readDataByTagName("VerifyContentErrorReporting", "chapterName", "284");
			new LoginUsingLTI().ltiLogin("284"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diag test for 1st chapter
			String ques = Driver.driver.findElement(By.className("al-diag-test-question-raw-content")).getText();
			System.out.println("ques in student view: "+ques);
			//click on error report icon
			Driver.driver.findElement(By.cssSelector("div[class='add-content-error show-content-issues-dialog']")).click();
			Thread.sleep(3000);
			//click on textarea to type error report
			Driver.driver.findElement(By.cssSelector("textarea[id='text-area-content-issue']")).click();
			Thread.sleep(3000);
			Driver.driver.switchTo().activeElement().sendKeys("Content error report text.");
			//click on send button
			Driver.driver.findElement(By.id("send-content-issue-btn")).click();
			Thread.sleep(3000);
			//find the notification pop up text
			String notice = Driver.driver.findElement(By.className("al-notification-message-body")).getText();
			String trimmmedNotice = notice.replaceAll("[\n\r]", "");
			System.out.println("  "+trimmmedNotice);
			if(!trimmmedNotice.equals("Are you sure there is a problem with this question? You may want to start a Discussion on this question with your classmates before reporting a problem.Click Yes to report this issue, orCancel to go back to the question"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For content error report the required notification not coming.");
			}
			//click on 'Yes' on the notification
			Driver.driver.findElement(By.id("send-content-issue")).click();
			int noticeSize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
			System.out.println("noticeSize: "+noticeSize);
			if(noticeSize != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Content issue is not reported after we click on 'Yes' link in the notification.");
			}
			
			new DirectLogin().CMSLogin();
			String title=Driver.driver.getTitle();
			if(title.equals("Course Content Details"))	
			{
				Driver.driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();
				if(chapterName == null)
				 {
				 Driver.driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
				 }
				 else
				 {
					 List <WebElement> allChapters = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label')]"));
					 for(WebElement chapters: allChapters)
					 {
						 if(chapters.getText().contains("Ch 1: The Changing Face of Business"))
						 {
							 //chapters.click();
							 ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", chapters); 
							 break;
						 }
						 
					 }
					 
				 }
				
				List<WebElement> elements = Driver.driver.findElements(By.className("collection-assessment-name"));
				for(WebElement content : elements)
					{
					if(content.getText().trim().equals("Diagnostic - The Changing Face of Busi..."))
					{
						Thread.sleep(3000);
					   // content.click();
						((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", content); 
					    Thread.sleep(3000);
	            		break;
					}
				}
			}
		  List <WebElement> allQuestionsNumbers = Driver.driver.findElements(By.xpath("//*[starts-with(@id, 'question-label-')]"));
		  for(int i = 2; i<10; i++)
		  {
			  allQuestionsNumbers.get(i).click();
			  Thread.sleep(20000);
		  }
		  /*  Thread.sleep(6000);
		  int i = 1;
		 for(WebElement question: allQuestionsNumbers)  
		 {
			//   System.out.println("Number: "+question.getText());
			   String str = Driver.driver.findElement(By.id("question-raw-content")).getText();
			   System.out.println("ques in CMS:" +str);
			   if(str.equals(ques))
			   {
				   System.out.println("Found");
				   
			   }
			   else
			   {
				   System.out.println("Not Found");
				   //((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", allQuestionsNumbers.get(i+1));
				  // question.click();
				   allQuestionsNumbers.get(i+1).click();
				   Thread.sleep(20000);
				   i++;
				   System.out.println("increment: "+i);
			   }
			 
		 }*/
	
		  
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in verifyContentErrorReporting in class VerifyContentErrorReporting",e);
		}
	}
	@Test(priority = 2, enabled = false)
	public void verifyContentErrorReportingByQuiting()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("286"); //  Logging in as student to orion
			new SelectChapterForTest().selectchapterfortest(0, 2);		//open diag test for 1st chapter
			//click on error report icon
			Driver.driver.findElement(By.cssSelector("div[class='add-content-error show-content-issues-dialog']")).click();
			Thread.sleep(3000);
			//click on textarea to type error report
			Driver.driver.findElement(By.cssSelector("textarea[id='text-area-content-issue']")).click();
			Thread.sleep(3000);
			Driver.driver.switchTo().activeElement().sendKeys("Content error report text.");
			//click on cancel icon
			Driver.driver.findElement(By.id("close-content-issue-dialog")).click();
			Thread.sleep(3000);
			//find the notification pop up text
			String notice = Driver.driver.findElement(By.className("al-notification-message-body")).getText();
			String trimmmedNotice = notice.replaceAll("[\n\r]", "");
			System.out.println("  "+trimmmedNotice);
			if(!trimmmedNotice.equals("Are you sure there is a problem with this question? You may want to start a Discussion on this question with your classmates before reporting a problem.Click Yes to report this issue, orCancel to go back to the question"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("For content error report the required notification not coming.");
			}
			//click on 'Yes' on the notification
			Driver.driver.findElement(By.id("send-content-issue")).click();
			int noticeSize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
			System.out.println("noticeSize: "+noticeSize);
			if(noticeSize != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Content issue is not reported after we click on 'Yes' link in the notification.");
			}
			//driver.findElement(By.id("close-content-issue-dialog")).click();
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in verifyContentErrorReportingByQuiting in class VerifyContentErrorReporting",e);
		}
	}
	
	@Test(priority=3,enabled=false)
	public void ContentIssueReportedByInstrictor()
	{
		try
		{
			Driver.startDriver();
			int reportissuecount=new ReportContentIssue().countofreportcontentissue(); //fetch count of report content issue
			new LoginUsingLTI().ltiLogin("287");
			new ReportContentIssue().reportcontentissue(0, 0, 0);//report issue by instructor
			String message=Driver.driver.findElement(By.className("al-notification-message-body")).getText();//text pop up			
			String texttoverify=ReadTestData.readDataByTagName("VerifyContentErrorReporting", "Texttoverify", "287");			
			if(!message.trim().equals(texttoverify.trim()))
				Assert.fail("Notification not appear with actual text");
			Driver.driver.findElement(By.id("send-content-issue")).click();//click on 'yes' in pop up
			Thread.sleep(2000);			
			int reportissuecount1=new ReportContentIssue().countofreportcontentissue();	 //fetch count of report content issue		
			if(reportissuecount>reportissuecount1)
				Assert.fail("after report content issue count not increase");
			new LoginUsingLTI().ltiLogin("288");
			new ReportContentIssue().reportcontentissue(0, 0, 0);	//report issue by instructor		
			Driver.driver.findElement(By.id("back-to-question")).click();//click on 'cancle' in pop up
			Thread.sleep(2000);			
			int reportissuecount2=new ReportContentIssue().countofreportcontentissue();		 //fetch count of report content issue
			if(reportissuecount1!=reportissuecount2)
				Assert.fail("After cancle the report content issue in summary page number varies");
			
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in ContentIssueReportedByInstrictor in class VerifyContentErrorReporting",e);
		}
	}
	
	@Test(priority=4,enabled=true)
	public void listofcontentissue()
	{
		try
		{
			
			Driver.startDriver();
			String course = Config.course;
			new DirectLogin().CMSLogin();
			Driver.driver.findElement(By.cssSelector("img[title='"+course+"']")).click();//click on course
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("deliver-course")).click();//click on summary link
			((JavascriptExecutor)Driver.driver).executeScript("window.scrollTo(0,300)");
			Thread.sleep(2000);
			List<WebElement> reviewpotentialissue=Driver.driver.findElements(By.className("review-potential-text"));
			reviewpotentialissue.get(0).click();
			Thread.sleep(2000);
			int listofreportcontentissue=Driver.driver.findElements(By.cssSelector("div[class='col id']")).size();
			if(listofreportcontentissue<1)
				Assert.fail("Afetr click on REPORTED CONTENT ISSUE its not shown list of all the issue.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in listofcontentissue in class VerifyContentErrorReporting",e);
		}
	}
	
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
