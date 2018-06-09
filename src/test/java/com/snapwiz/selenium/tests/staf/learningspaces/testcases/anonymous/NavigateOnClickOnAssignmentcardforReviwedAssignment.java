package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ExpandFirstChapter;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.UpdateContentIndex;

public class NavigateOnClickOnAssignmentcardforReviwedAssignment extends Driver
{
	@Test
	public void navigateonclickonassignmentcardforreviwedassignment()
	{
		try
		{
			String assignmentname=ReadTestData.readDataByTagName("NavigateOnClickOnAssignmentcardforReviwedAssignment", "assessmentname", "3367");
			String topic1=ReadTestData.readDataByTagName("tocdata", "card2topic1", "1");//fetch topic 1
			new Assignment().create(3367);//create assignment
			new LoginUsingLTI().ltiLogin("33671");//create student
			new UpdateContentIndex().updatecontentindex("3367");//update content index
			driver.quit();
			startDriver("firefox");
			new LoginUsingLTI().ltiLogin("3367");//login as instructor
			new Assignment().assignToStudent(3367);//assignment assign to student
			new LoginUsingLTI().ltiLogin("33671");//login as student
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new ExpandFirstChapter().expandFirstChapter();//expanfd first chapter
			new TopicOpen().topicOpen(topic1);//open topic 1 of chapter 1
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("span[title='Assignments']")).click();//click on assignment tab
			Thread.sleep(3000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.linkText(assignmentname)));//click on assignment
			Thread.sleep(2000);			
			driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")).click();//assignment submit
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("span[title='Assignments']")).click();//click on assignment tab
			Thread.sleep(3000);
			String aftersubmit=driver.findElement(By.cssSelector("span[class='assessmentStatus']")).getText();//get assignment status
			if(!aftersubmit.equals("Submitted:"))
				Assert.fail("assignment status in not submitted after submit the assignment");
			new LoginUsingLTI().ltiLogin("3367");//login as instructor
			new Assignment().provideFeedback(3367);//provise feedback to assigbnment
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("span[id='close-view-responses']")).click();//close view responce page
			Thread.sleep(2000);			
			driver.findElement(By.cssSelector("div[class='idb-gradeBook-feedback-section-release idb-gradeBook-release-feedback-section idb-gradeBook-feedback-release']")).click();//click on release all grade
			Thread.sleep(3000);
			new LoginUsingLTI().ltiLogin("33671");//login as student
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new ExpandFirstChapter().expandFirstChapter();//expand 1st chapter
			new TopicOpen().topicOpen(topic1);//open topic 1
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("span[title='Assignments']")).click();//click on assignment tab
			Thread.sleep(3000);
			String afterreviewd=driver.findElement(By.cssSelector("span[class='assessmentStatus']")).getText();//get assignment status
			if(!afterreviewd.equals("Reviewed"))
				Assert.fail("status not change from submitted to reviewed after instructor give feedback to assignment");
			driver.findElement(By.linkText(assignmentname)).click();//click on assignment
			Thread.sleep(2000);
			boolean performance1=driver.findElement(By.cssSelector("div[class='report-chart-title']")).isDisplayed();//check performance page
			if(performance1==false)
				Assert.fail("after cilck on graded assignment its not goes to performance page");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase navigateonclickonassignmentcardforreviwedassignment in class NavigateOnClickOnAssignmentcardforReviwedAssignment ",e);
		}
	}

}
