package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignmentTabDetails;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ExpandFirstChapter;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.UpdateContentIndex;

public class SubmitedAssignmentDisplayedperformance extends Driver
{
	@Test
	public void submitedassignmentdisplayedperformnace()
	{
		try
		{
			String topic1=ReadTestData.readDataByTagName("tocdata", "card2topic1", "1");//fetch topic 1
			String assignments1=ReadTestData.readDataByTagName("SubmitedAssignmentDisplayedperformance", "assessmentname", "3319");//fetch assignment name
			new Assignment().create(3319);//create assignment
			new LoginUsingLTI().ltiLogin("33191");//create student
			new UpdateContentIndex().updatecontentindex("3319");//update index
			driver.quit();
            startDriver("firefox");
 			new LoginUsingLTI().ltiLogin("3319");//login as instructor
			new Assignment().assignToStudent(3319);//Assignment assign to student
			new LoginUsingLTI().ltiLogin("33191");//login as student
			new Navigator().NavigateTo("eTextbook");//navigate to etextbook
			new ExpandFirstChapter().expandFirstChapter();//expand 1st chapter
			new TopicOpen().topicOpen(topic1);//open topic 1
			driver.findElement(By.cssSelector("span[title='Assignments']")).click();//click on assignment tab
			Thread.sleep(3000);
			new AssignmentTabDetails().assignmentdetailsatrightsideframe(assignments1);
			driver.findElement(By.linkText(assignments1)).click();//click on assignment name
			Thread.sleep(2000);
			driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")).click();//click on submit button of assignment
			Thread.sleep(2000);
			boolean perfromancetab=driver.findElement(By.cssSelector("span[title='Performance']")).isDisplayed();//check performance tab
			if(perfromancetab==false)
				Assert.fail(" performance  not displayed of the assignment on the right");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelpper submitedassignmentdisplayedperformnace in class SubmitedAssignmentDisplayedperformance.",e);
		}
	}


}
