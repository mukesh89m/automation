package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class VerifyReportContentIssue extends Driver{
@Test
	public void verifyReportContentIssue()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("2565");
			new Navigator().NavigateTo("eTextbook");
			new DiagnosticTest().startTest(2);
			//click on 'Report Content Issue'
			driver.findElement(By.cssSelector("div.add-content-error.show-content-issues-dialog")).click();
			Thread.sleep(2000);
			//find the header text
			String contentIssueHeader = driver.findElement(By.className("content-issue-header-text")).getText();
			if(!contentIssueHeader.equals("Report Content Issue"))
				Assert.fail("On clicking report content issues icon Pop up doesnt open with header'Report content issue'.");
			//fine the Send Button
			int sendButtonSize = driver.findElements(By.id("send-content-issue-btn")).size();
			if(sendButtonSize == 0)
				Assert.fail("Sendbutton is not available over pop up in report content issue.");
			//click on quit icon over the pop up
			driver.findElement(By.id("close-content-issue-dialog")).click();
			Thread.sleep(2000);
			//find the header text to verify whether the pop closed or not
			String contentIssueHeader1 = driver.findElement(By.className("content-issue-header-text")).getText();
			if(contentIssueHeader1.length() > 0)
				Assert.fail("On clicking close icon over the Report issue pop up the pop doesn't close.");
			//click on 'Report Content Issue'
			driver.findElement(By.cssSelector("div.add-content-error.show-content-issues-dialog")).click();
			Thread.sleep(2000);
			//type text to send report
			driver.findElement(By.cssSelector("textarea[id='text-area-content-issue']")).sendKeys("This is report content issue text.");
			//click on quit icon over the pop up
			driver.findElement(By.id("close-content-issue-dialog")).click();
			Thread.sleep(2000);
			String contentIssueHeader2 = driver.findElement(By.className("content-issue-header-text")).getText();
			if(contentIssueHeader2.length() > 0)
				Assert.fail("After typing some text in the content issue textarea then On clicking close icon over the Report issue pop up the pop doesn't close.");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in verifyReportContentIssue in class VerifyReportContentIssue",e);
		}
	}

}
