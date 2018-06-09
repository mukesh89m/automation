package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R5;

import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ResourseCreate;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;

public class RightSideFrameAssignmentResourcesStream extends Driver
{
	public static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.RightSideFrameAssignmentResourcesStream");
	/*
	 * 924-934
	 */
	@Test(priority=1,enabled=true)
	public void rightFrameSetUp()
	{
		try
		{			
		new ResourseCreate().resourseCreate(924,0);
		new Assignment().create(924);
        new LoginUsingLTI().ltiLogin("924"); //creating student
		new LoginUsingLTI().ltiLogin("925");
	    new Assignment().assignToStudent(925);
	    new LoginUsingLTI().ltiLogin("924");
		new Navigator().NavigateTo("eTextBook");
		new TopicOpen().lessonOpen(0, 0);
		driver.findElement(By.cssSelector("span[title='Resources']")).click();
		driver.findElement(By.cssSelector("span[title='Stream']")).click();
		driver.findElement(By.cssSelector("span[title='Assignments']")).click();
		//Validating the stream tab
		if(driver.findElements(By.cssSelector("span[title='Stream']")).size() != 1)
		if(driver.findElements(By.cssSelector("span[title='Assignments']")).size() != 1)
			Assert.fail("Assignments tab not displayed on eTextBook right side frame");
		//Validating Resources Tab
		if(driver.findElements(By.cssSelector("span[title='Resources']")).size() != 1)
			Assert.fail("Resources tab not displayed on eTextBook right side frame");
		driver.findElement(By.cssSelector("span[title='Assignments']")).click();
		//Validating assignment present or not
		if(driver.findElements(By.cssSelector("li[class='assignment-content-posts-list']")).size() != 1)
			Assert.fail("Assignment posted not found in the assignments tab");
		//Validating instructor tag
		if(!driver.findElement(By.cssSelector("span[class='ls-side-istructor']")).getText().equals("Instructor"))
			Assert.fail("Instructor tag not present in the assignement posted");
		String posttext = driver.findElement(By.className("ls-right-user-subhead")).getText().replaceAll("\\n", " ");
		if(!posttext.contains("posted an assignment"))
			Assert.fail("Text 'posted an assignment' not found under assignments tab.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception while creating Resourse and Assessment",e);
		}
	}		


}
