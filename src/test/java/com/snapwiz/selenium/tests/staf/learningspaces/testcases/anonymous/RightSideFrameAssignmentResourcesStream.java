package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.By;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ResourseCreate;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.UpdateContentIndex;

public class RightSideFrameAssignmentResourcesStream extends Driver
{
	public static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.RightSideFrameAssignmentResourcesStream");
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
		new UpdateContentIndex().updatecontentindex("924");
		new LoginUsingLTI().ltiLogin("925");
	    new Assignment().assignToStudent(925);
		}
		catch(Exception e)
		{
			Assert.fail("Exception while creating Resourse and Assessment",e);
		}
	}
	@Test(priority=2,enabled=true)
	public void rightSideFrameValidate()
	{
		try
		{
			//String defaultstreamtabtext = ReadTestData.readDataByTagName("RightSideFrameAssignmentResourcesStream", "defaultstreamtabtext", "924");
			new LoginUsingLTI().ltiLogin("924");
			new Navigator().NavigateTo("eTextbook");
			driver.findElement(By.cssSelector("span[title='Resources']")).click();
			driver.findElement(By.cssSelector("span[title='Stream']")).click();
			driver.findElement(By.cssSelector("span[title='Assignments']")).click();
			//Validating the stream tab
			if(driver.findElements(By.cssSelector("span[title='Stream']")).size() != 1)
				Assert.fail("Stream tab not displayed on eTextBook right side frame");
			//Validating the default text in stream tab
			//if(!driver.findElement(By.className("stream-content-posts")).getText().equals(defaultstreamtabtext))
			//	Assert.fail("Text 'No Posts Available' not present by default under Stream tab in right side frame");
			//Validating Assignments Tab
			if(driver.findElements(By.cssSelector("span[title='Assignments']")).size() != 1)
				Assert.fail("Assignments tab not displayed on eTextBook right side frame");
			//Validating Resources Tab
			if(driver.findElements(By.cssSelector("span[title='Resources']")).size() != 1)
				Assert.fail("Resources tab not displayed on eTextBook right side frame");
			
		}
		catch(Exception e)
		   {
				  logger.log(Level.SEVERE,"Exception in TestCase rightSideFrameValidate in class RightSideFrameAssignmentResourcesStream",e);
				  Assert.fail("Exception in TestCase rightSideFrameValidate in class RightSideFrameAssignmentResourcesStream",e);
				  
		   }
	}


	@Test(priority=3,enabled=true)
	public void rightSideFramewithResourceStreamAssignment()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("924");
			new Navigator().NavigateTo("eTextbook");
			driver.findElement(By.cssSelector("span[title='Assignments']")).click();
			//Validating assignment present or not
			if(driver.findElements(By.cssSelector("li[class='assignment-content-posts-list']")).size() != 1)
				Assert.fail("Assignment posted not found in the assignments tab");
			//Validating instructor tag
			if(!driver.findElement(By.cssSelector("span[class='ls-side-istructor']")).getText().equals("Instructor"))
				Assert.fail("Instructor tag not present in the assignement posted");
			String posttext = driver.findElement(By.className("ls-right-user-subhead")).getText().replaceAll("\\n", " ");
			System.out.println(posttext);
			if(!posttext.equals("posted an assignment Graded"))
				Assert.fail("Text 'posted an assignment' not found under assignments tab.");
			
			
		}
		catch(Exception e)
		   {
				  logger.log(Level.SEVERE,"Exception in TestCase rightSideFramewithResourceStreamAssignment in class RightSideFrameAssignmentResourcesStream",e);				  
				  Assert.fail("Exception in TestCase rightSideFramewithResourceStreamAssignment in class RightSideFrameAssignmentResourcesStream",e);
				  
		   }
	}
	


}
