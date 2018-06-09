package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT11.R22;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelectParticularQuestionFromCustomAssignment;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class BrowseOptionFunctionality extends Driver
{
	@Test
	public void browseOptionFunctionality()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("134");//login as instructor
			new Navigator().NavigateTo("Question Banks");//Navigate to resources
			new Click().clickByid("customAssignment");//click on create custom assignment
			new Click().clickByclassname("ls-ins-browse-icon");//click on browse option
			boolean browsedropdown=new BooleanValue().booleanbyclass("ls-ins-view-browse-options-position");
			if(browsedropdown==false)
			{
				Assert.fail("browse option not expand");
			}
			new Click().clickByclassname("ls-ins-browse-icon");//click on view browse option
			boolean browsedropdown1=new BooleanValue().booleanbyclass("ls-ins-view-browse-options-position");
			if(browsedropdown1==true)
			{
				Assert.fail("browse option not collapse after 2nd time click on view browse option");
			}
			new Click().clickByclassname("ls-ins-browse-icon");//click on view browse option
			String filterdropdownoption=new TextFetch().textfetchbyid("ls-ins-view-browse-options-dropdown");//fetch text of view filter default options
			if(!filterdropdownoption.contains("All Chapters"))
			{
				Assert.fail("all chapter not present");
			}
			if(!filterdropdownoption.contains("All Sections"))
			{
				Assert.fail("All Sections not present");
			}
			if(!filterdropdownoption.contains("All Sections"))
			{
				Assert.fail("All Sub-sections not present");
			}
			if(!filterdropdownoption.contains("All Objectives"))
			{
				Assert.fail("All Objectives not present");
			}
			if(!filterdropdownoption.contains("All Difficulty"))
			{
				Assert.fail("All Difficulty not present");
			}
			if(!filterdropdownoption.contains("All Question types"))
			{
				Assert.fail("All Question types not present");
			}
			if(!filterdropdownoption.contains("Show questions from e-Textbook"))
			{
				Assert.fail("Show questions from e-Textbook not present");
			}
			boolean instructoronlychecked=new BooleanValue().booleanbyclass("ls-instructor-only-assignment-check");
			if(instructoronlychecked==false)
			{
				Assert.fail("by deafult instructor only chech box checked");
			}
			driver.findElement(By.linkText("All Chapters")).click();
			driver.findElement(By.partialLinkText("Ch 1:")).click();
			driver.findElement(By.linkText("All Objectives")).click();
			driver.findElement(By.linkText("All Sections")).click();
			driver.findElement(By.partialLinkText("1.1")).click();
			driver.findElement(By.linkText("All Difficulty Levels")).click();
			driver.findElement(By.linkText("Easy")).click();
			driver.findElement(By.xpath("//span[@title='All Question types']")).click();

			new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");

		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase BrowseOptionFunctionality in method browseOptionFunctionality ",e);
		}			
	}


}
