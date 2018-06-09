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

public class NavigationOnClickingAnAssignmentCard extends Driver
{
	@Test(priority=1,enabled=true)
	public void navigationonclickinganassignmentcard()
	{
		try
		{
			String assignmentname=ReadTestData.readDataByTagName("NavigationOnClickingAnAssignmentCard", "assessmentname", "3361");
			String topic1=ReadTestData.readDataByTagName("tocdata", "card2topic1", "1");//fetch topic 1
			new Assignment().create(3361);
			new LoginUsingLTI().ltiLogin("33612");
			new UpdateContentIndex().updatecontentindex("3361");
			new LoginUsingLTI().ltiLogin("3361");//login as instructor
			new Assignment().assignToStudent(3361);//assignment assign to student
			new LoginUsingLTI().ltiLogin("33612");//login as student
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new ExpandFirstChapter().expandFirstChapter();//expand first chapter
			new TopicOpen().topicOpen(topic1);//open topic 1
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("span[title='Assignments']")).click();//click on assignment tab
			Thread.sleep(3000);
			String firsttime=driver.findElement(By.cssSelector("span[class='assessmentStatus']")).getText();//get assignment status
			if(!firsttime.equals("Not Started"))
				Assert.fail("status of 1st time assignment is no---not started");
			((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.linkText(assignmentname)));
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("span[title='Assignments']")).click();//click on assignment tab
			Thread.sleep(3000);
			String secondtime=driver.findElement(By.cssSelector("span[class='assessmentStatus']")).getText();//get assignment status
			if(!secondtime.equals("In Progress:"))
				Assert.fail("status of 2nd time assignment is no---In Progress:");
			driver.findElement(By.cssSelector("span[title='Assignments']")).click();//click on assignment tab
			Thread.sleep(3000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.linkText(assignmentname)));//click on assignment
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")).click();
			driver.findElement(By.cssSelector("span[title='Assignments']")).click();//click on assignment tab
			Thread.sleep(3000);
			String aftersubmit=driver.findElement(By.cssSelector("span[class='assessmentStatus']")).getText();//get assignment status
			if(!aftersubmit.equals("Submitted:"))
				Assert.fail("status of  assignment after submit  is no---Submitted:");
			driver.findElement(By.linkText(assignmentname)).click();//click on assignment
			Thread.sleep(2000);
			boolean performance=driver.findElement(By.cssSelector("div[id='performance-chart-label-id']")).isDisplayed();
			if(performance==false)
				Assert.fail("after cilck on submitted assignment its not goes to performance page");
			new LoginUsingLTI().ltiLogin("3361");//login as instructor
			new Assignment().provideGRadeToStudent(3361);
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("div[class='idb-gradeBook-grades-section-release idb-gradeBook-release-grades-section idb-gradeBook-grades-release']")).click();//click on release all grade
			Thread.sleep(3000);
			new LoginUsingLTI().ltiLogin("33612");//login as student
			new Navigator().NavigateTo("eTextbook");
			driver.findElement(By.cssSelector("span[title='Assignments']")).click();//click on assignment tab
			Thread.sleep(3000);
			String aftergrade=driver.findElement(By.cssSelector("span[class='assessmentStatus']")).getText();//get assignment status
			if(!aftergrade.equals("Graded:"))
				Assert.fail("status of 2nd time assignment is no---In Progress:");
			driver.findElement(By.linkText(assignmentname)).click();//click on assignment
			Thread.sleep(2000);
			boolean performance1=driver.findElement(By.cssSelector("div[class='report-chart-title']")).isDisplayed();
			if(performance1==false)
				Assert.fail("after cilck on graded assignment its not goes to performance page");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase navigationonclickinganassignmentcard in class NavigationOnClickingAnAssignmentCard ",e);
		}
	}


}
