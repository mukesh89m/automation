package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R141;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class ImpactOfGradableDWAssignmentOverEngagementReport extends Driver{
	
	@Test
	public void impactOfGradableDWAssignmentOverEngagementReport()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("229_1");		//create a student
			
			new LoginUsingLTI().ltiLogin("229");		//login as instructor
			new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
			new TopicOpen().openLessonWithDiscussionWidget();
			new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(229);
			new Navigator().NavigateTo("Engagement Report"); //navigate to Engagement Report
			List<WebElement> status = driver.findElements(By.className("row-assigned-status"));
			//TC row no .229
			if(!status.get(1).getText().contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In engagement report page the Not started count under assigned section of bottom table doesnt consider the assignment.");
			}
			
			new LoginUsingLTI().ltiLogin("229_1");		//login as student
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
			new WebDriverWait(driver, 2000).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
			String perspective = new RandomString().randomstring(10);
			new DiscussionWidget().addPerspectiveForDWAssignment(perspective);
			
			new LoginUsingLTI().ltiLogin("229");		//login as instructor
			new Navigator().NavigateTo("Engagement Report"); //navigate to Engagement Report
			//TC row no .233
			String status2 = driver.findElement(By.xpath(".//*[@id='idb-body-content']/div/div/div[5]/div[2]/div[2]/div[2]/div/div[3]/span")).getText();
			if(!status2.contains("1"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In engagement report page the Completed count under assigned section of bottom table doesnt consider the assignment.");
			}
			//TC row no .234
			String completedGraph = driver.findElement(By.className("assigned-reading-progress")).getText();
			if(!completedGraph.contains("Assigned Learning Activities Completed"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In engagement report page the Assigned reading completed graph doesnt consider the assignment.");
			}
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			driver.findElement(By.cssSelector("span[title='View Student Responses']")).click();	//click on View Student Responses
			new DiscussionWidget().provideGradeToStudent(0, "2");	//enter grade
			driver.findElement(By.cssSelector("div[title='Release Grade for All']")).click(); //click on Release Grade for All button
			Thread.sleep(2000);
			new Navigator().NavigateTo("Engagement Report"); //navigate to Engagement Report
			//TC row no .235
			List<WebElement> marks = driver.findElements(By.className("students-report-content-body-six"));
			if(!marks.get(2).getText().contains("100.0%"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In engagement report page the the mark displayed doesnt consider the assignment.");
			}
			
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase impactOfGradableDWAssignmentOverEngagementReport in class ImpactOfGradableDWAssignmentOverEngagementReport.",e);
		}
	}

}
