package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R1410;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignLesson;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.CreateCustomAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;

public class CustomeAssignmentatStudyPlan extends Driver
{
	@Test(priority=1,enabled=true)
	public void CustomeAssilgnmentatStudyPlanchapterlevel()
	{
		try
		{
			String searchtext=ReadTestData.readDataByTagName("", "searchtext", "70");
			new LoginUsingLTI().ltiLogin("70_1");//login as student
			new LoginUsingLTI().ltiLogin("70");//login as instructor
			new CreateCustomAssignment().createcustomassignmentatchapterlevel(searchtext);
			Thread.sleep(2000);
			new Click().clickBycssselector("div[data-id='my-resources']");
			new AssignLesson().Assigncustomeassignemnt(70);
			new LoginUsingLTI().ltiLogin("70_1");
			new Navigator().NavigateTo("eTextBook");
			new TopicOpen().chapterOpen(0);
			//70--chapter level; custom assignment
            Thread.sleep(2000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.className("inner-assignment-block"))); //opening the topic level assignment from toc
            Thread.sleep(2000);
            if(!driver.findElement(By.className("title-and-question")).getText().contains("shor:"))
                Assert.fail("chapter level custom assignment not opened from study plan");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase method CustomeAssignmentatStudyPlanchapterlevel in class CustomeAssignmentatStudyPlan ",e);
		}
	}
	
	@Test(priority=2,enabled=true)
	public void CustomeAssignmentatStudyPlanTopiclevel()
	{
		try
		{
			String searchtext=ReadTestData.readDataByTagName("", "searchtext", "71");
			new LoginUsingLTI().ltiLogin("71_1");
			new LoginUsingLTI().ltiLogin("71");//login as instructor
			new CreateCustomAssignment().createcustomassignmentattopiclevel(searchtext);//create custom assignment at topic level
			Thread.sleep(3000);
            new Click().clickBycssselector("div[data-id='my-resources']");
			new AssignLesson().Assigncustomeassignemnt(71);
			new LoginUsingLTI().ltiLogin("71_1");
			new Navigator().NavigateTo("eTextBook");
			new TopicOpen().chapterOpen(0);
            Thread.sleep(3000);
			//71--topic level; custom assignment
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.className("inner-assignment-block"))); //opening the topic level assignment from toc
            Thread.sleep(3000);
            if(!driver.findElement(By.className("title-and-question")).getText().contains("shor:"))
                Assert.fail("topic level custom assignment not opened from study plan");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase method CustomeAssignmentatStudyPlanTopiclevel in class CustomeAssignmentatStudyPlan ",e);
		}
	}
}
