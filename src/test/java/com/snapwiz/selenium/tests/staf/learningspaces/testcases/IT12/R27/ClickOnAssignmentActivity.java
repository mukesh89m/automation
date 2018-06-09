package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT12.R27;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;

public class ClickOnAssignmentActivity extends Driver{
	
	@Test(priority = 1, enabled = true)
	public void clickOnAssignmentActivity()
	{
		try
		{
			new Assignment().create(34);
			new LoginUsingLTI().ltiLogin("33");//login as student
			new LoginUsingLTI().ltiLogin("34");//login as instructor
			new Assignment().assignToStudent(34);//assign to student
			new LoginUsingLTI().ltiLogin("34");//login as instructor
			driver.findElement(By.className("ls-dashboard-view-all")).click();//click on View all link from assessment tile
			Thread.sleep(2000);
			String assignmentPage = driver.findElement(By.cssSelector("div[class='tab active']")).getText();
            if(!assignmentPage.contains("Current Assignments"))
			{
				Assert.fail("Click on the assignment activity from dashboard Instructor doesn't navigate to the assignment page.");
			}
			String actions = driver.findElement(By.className("ls-assignment-assign-more")).getText();
			if(!actions.contains("View Student Responses"))
			{
				Assert.fail("Click on the assignment activity from dashboard in assignment page the 'View Responses' link is absent.");
			}
			if(!actions.contains("Update Assignment"))
			{
				Assert.fail("Click on the assignment activity from dashboard in assignment page the 'Update Assignment' link is absent.");
			}
			if(!actions.contains("Un-assign Assignment"))
			{
				Assert.fail("Click on the assignment activity from dashboard in assignment page the 'Delete Assignment' link is absent.");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase clickOnAssignmentActivity in class ClickOnAssignmentActivity.",e);
		}
	}
		
		@Test(priority = 2, enabled = true)
		public void clickOnStudyLink()
		{
			try
			{
				new LoginUsingLTI().ltiLogin("39");//login as instructor
				driver.findElement(By.linkText("STUDY")).click();//click on study link
				Thread.sleep(5000);
				String url = driver.getCurrentUrl();
				System.out.println("url: "+url);
				if(!url.contains("eTextBook"))
				{
					new Screenshot().captureScreenshotFromTestCase();
					Assert.fail("On clicking the STUDY link from dashboard it doesn't navigate to etextbook.");
				}
			}
			catch(Exception e)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Exception in testcase clickOnStudyLink in class ClickOnAssignmentActivity.",e);
			}
		}

}
