package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R141;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiscussionWidget;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;

public class ImpactOfNonGradableDWAssignmentOverEngagementReport extends Driver {
	
	@Test
	public void impactOfNonGradableDWAssignmentOverEngagementReport()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("316_1");		//create a student
			
			new LoginUsingLTI().ltiLogin("316");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(316);
			new Navigator().NavigateTo("Engagement Report"); //navigate to Engagement Report
			List<WebElement> status = driver.findElements(By.className("row-assigned-status"));
			//TC row no. 316
			if(!status.get(1).getText().contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In engagement report page the Not started count under assigned section of bottom table doesnt consider the non gradable DW assignment.");
			}
			
			new LoginUsingLTI().ltiLogin("316_1");		//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			Thread.sleep(2000);
			String perspective = new RandomString().randomstring(10);
			new DiscussionWidget().addPerspectiveForDWAssignment(perspective);
			
			new LoginUsingLTI().ltiLogin("316");		//login as instructor
			new Navigator().NavigateTo("Engagement Report"); //navigate to Engagement Report
			//TC row no. 320
			String status2 = driver.findElement(By.xpath(".//*[@id='idb-body-content']/div/div/div[5]/div[2]/div[2]/div[2]/div/div[3]/span")).getText();
			if(!status2.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In engagement report page the Completed count under assigned section of bottom table doesnt consider the the non gradable DW assignment.");
			}
			//TC row no. 321
			String completedGraph = driver.findElement(By.className("assigned-reading-progress")).getText();
			if(!completedGraph.contains("Assigned Learning Activities Completed"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In engagement report page the Assigned reading completed graph doesnt consider the the non gradable DW assignment.");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase impactOfNonGradableDWAssignmentOverEngagementReport in class ImpactOfNonGradableDWAssignmentOverEngagementReport.",e);
		}
	}

}
