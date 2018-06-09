package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.Driver;

public class ReportContentIssue
{
	/*
	 * Brajesh
	 * Report content issue on specific question of specific course
	 */
	public void reportcontentissue(int chapterno,int tlono,int questionno)
	{
		try
		{
			Driver.driver.findElement(By.className("idb-user-profile-name")).click();//click on name of instructor
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("instructorReports")).click();//click on my report
			Thread.sleep(2000);
			if(Driver.driver.findElements(By.id("al-performance-report")).size()>0)
			{
				Driver.driver.findElement(By.id("al-performance-report")).click();//click on performance report
				Thread.sleep(2000);
			}
			List<WebElement> chaptername=Driver.driver.findElements(By.className("coursePerformanceByChapters-xAxisLabel"));//click on chapter
			chaptername.get(chapterno).click();
			Thread.sleep(2000);
			List<WebElement> alltlo=Driver.driver.findElements(By.className("chapterPerformanceByObjectives-xAxisLabel"));//click on TLO
			alltlo.get(tlono).click();
			Thread.sleep(2000);
			List<WebElement> allquestion=Driver.driver.findElements(By.className("chapterPerformanceByQuestions-xAxisLabel"));//click on question no
			allquestion.get(questionno).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("div[class='add-content-error show-content-issues-dialog']")).click();//click on report content issue
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("text-area-content-issue")).click();//click on report content issue text area
			Driver.driver.findElement(By.id("text-area-content-issue")).sendKeys("This is reported Issue");//report text
			Driver.driver.findElement(By.id("send-content-issue-btn")).click();//click on report content issue send button
			Thread.sleep(3000);
			Driver.driver.findElement(By.id("send-content-issue")).click(); //clicking on Yes link inside notification message
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in method reportcontentissue in class ReportContentIssue",e);
		}	
	}
	//Return count of report content issue
	public int countofreportcontentissue()
	{
		int reportissuecount=0;
		try
		{
			String course = Config.course;
			new DirectLogin().CMSLogin();
			Driver.driver.findElement(By.cssSelector("img[title='"+course+"']")).click();//click on course
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("deliver-course")).click();//click on summary link
			((JavascriptExecutor)Driver.driver).executeScript("window.scrollTo(0,500)");
			Thread.sleep(2000);
			List<WebElement> totalcountofreport=Driver.driver.findElements(By.className("issues-count"));//fetch data of report content issue
			String totalreport=totalcountofreport.get(0).getText();
			reportissuecount=Integer.parseInt(totalreport);
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in method countofreportcontentissue in class ReportContentIssue",e);
		}
		return reportissuecount;
	}
	
	//report a content issue on a question
	public void reportIssueOnQuestion(String issueText)
	{
		try
		{
			Driver.driver.findElement(By.cssSelector("div[class='add-content-error show-content-issues-dialog']")).click();//click on report content issue
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("text-area-content-issue")).click();//click on report content issue text area
			Driver.driver.findElement(By.id("text-area-content-issue")).sendKeys(issueText);//report text
			Driver.driver.findElement(By.id("send-content-issue-btn")).click();//click on report content issue send button
			Thread.sleep(3000);
			Driver.driver.findElement(By.id("send-content-issue")).click(); //clicking on Yes link inside notification message
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in method reportIssueOnQuestion in class ReportContentIssue",e);
		}
	}
}
