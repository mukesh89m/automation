package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;

public class VerifyReportContentIssueForStatic extends Driver{
@Test
	public void verifyReportContentIssueForStatic()
	{
		try
		{
			String card1topic3 = ReadTestData.readDataByTagName("tocdata", "card1topic3", "1");
			new LoginUsingLTI().ltiLogin("2699");
			new Navigator().NavigateTo("eTextbook");
			new TopicOpen().topicOpen(card1topic3);		//start static test
			driver.findElement(By.cssSelector("input[type=\"button\"]")).click();		//click submit button
			Thread.sleep(3000);
			//click on 'Report Content Issue'
			driver.findElement(By.cssSelector("div.add-content-error.show-content-issues-dialog")).click();
			Thread.sleep(2000);
			//find the header text
			String contentIssueHeader = driver.findElement(By.className("content-issue-header-text")).getText();
			if(!contentIssueHeader.equals("Report Content Issue"))
				Assert.fail("On clicking report content issues icon Pop up doesnt open with header'Report content issue' in static test.");
			//fine the Send Button
			int sendButtonSize = driver.findElements(By.id("send-content-issue-btn")).size();
			if(sendButtonSize == 0)
				Assert.fail("Sendbutton is not available over pop up in report content issue in static test.");
			//type text to send report
			driver.findElement(By.cssSelector("textarea[id='text-area-content-issue']")).sendKeys("This is report content issue text.");
			//click on quit icon over the pop up
			driver.findElement(By.id("close-content-issue-dialog")).click();
			Thread.sleep(2000);
			//find the header text to verify whether the pop closed or not
			String contentIssueHeader1 = driver.findElement(By.className("content-issue-header-text")).getText();
			if(contentIssueHeader1.length() > 0)
				Assert.fail("After typing some text in the content issue textarea then On clicking close icon over the Report issue pop up the pop doesn't close(In Static Test).");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in verifyReportContentIssueForStatic in class VerifyReportContentIssueForStatic",e);
		}
	}

}
