package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;



import javax.swing.text.NavigationFilter;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ExpandCollapseChapter;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;


public class StudentAbleToGoAnotherAssessment extends Driver
{
	@Test
	public void studentabletogoassessment()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("2887");//login as student
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new DiagnosticTest().startTest(1);
			new TOCShow().tocShow();
			Thread.sleep(3000);
			String diagotest=ReadTestData.readDataByTagName("tocdata", "daigonestictest", "1");//fetch 1st static test to open
			String test1=ReadTestData.readDataByTagName("tocdata", "statictest", "1");//fetch 1st static test to open
			String statictestchapter2=ReadTestData.readDataByTagName("tocdata", "statictestchp2", "1");//fetch 1st static test to open
			new TopicOpen().topicOpen(test1);
			Thread.sleep(2000);
			String errormessage=ReadTestData.readDataByTagName("StudentAbleToGoAnotherAssessment", "errormessage", "2887");//fetch 1st tipoc name to open
			String errormsg=driver.findElement(By.className("al-notification-message-body")).getText();
			if(!errormessage.trim().equals(errormsg))
				Assert.fail("Notification not apper when student try to open new assessment");
			driver.findElement(By.cssSelector("div[class='reload-assessment-notification-option assessment-notification-option-no']")).click();
			Thread.sleep(2000);
			String activetabtext=driver.findElement(By.cssSelector("div[data-type='diag-assessment']")).getText();
			if(!activetabtext.equals(diagotest))
				Assert.fail("after click on -No-link in notification its does not go to last open test.");
			new TOCShow().tocShow();
			Thread.sleep(3000);
			new TopicOpen().topicOpen(test1);
			driver.findElement(By.cssSelector("div[class='reload-assessment-notification-option assessment-notification-option-yes']")).click();
			Thread.sleep(2000);
			String activetabtext1=driver.findElement(By.cssSelector("div[data-type='static-assessment']")).getText();
			if(!activetabtext1.equals(test1))
				Assert.fail("after click on -Yes-link in notification its does not open the new assessment");
			new TOCShow().tocShow();
			Thread.sleep(3000);
			new ExpandCollapseChapter().expandChapter(2);
			Thread.sleep(3000);
			new TopicOpen().topicOpen(statictestchapter2);
	
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase method studentabletogoassessment in class StudentAbleToGoAnotherAssessment",e);
		}
	}



}
