package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT11.R22;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelectParticularQuestionFromCustomAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SearchQuestionInCustomCourseAssignemnt;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class SearchFilterFunctionality extends Driver
{
	@Test
	public void searchFilterFunctionality()
	{
		try
		{
			MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
			new LoginUsingLTI().ltiLogin("84");//login as instructor
			new Navigator().NavigateTo("My Question Bank");//click on my question bank
			new Click().clickByid("customAssignment");//click on create custom assignment
			Thread.sleep(2000);
			String searchtext=ReadTestData.readDataByTagName("tocdata", "searchtext", "1");
			new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchtext);
			new Click().clickByclassname("ls-ins-view-filters");//click on view filter
			boolean filterdropdown=new BooleanValue().booleanbyid("ls-ins-view-browse-options-dropdown");
			if(filterdropdown==false)
			{
				Assert.fail("filter dropdown value not shown");
			}
			new Click().clickByclassname("ls-ins-view-filters");//click on view filter
			boolean filterdropdown1=new BooleanValue().booleanbyid("ls-ins-view-browse-options-dropdown");
			if(filterdropdown1==true)
			{
				Assert.fail("filetr drop down still shown after 2nd time click on filter dropdown");
			}
			new Click().clickByclassname("ls-ins-view-filters");//click on view filter
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
			Thread.sleep(3000);
			myQuestionBank.getStudyOfLife().click();//click on studyoflife
			Thread.sleep(2000);
			driver.findElement(By.linkText("All Objectives")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[contains(@title,'Discuss the scientific')]")).click();
			Thread.sleep(2000);
			driver.findElement(By.linkText("All Sections")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[contains(@title,'1.1 The Science of Biology')]")).click();
			Thread.sleep(2000);
			driver.findElement(By.linkText("All Difficulty Levels")).click();
			Thread.sleep(2000);
			driver.findElement(By.linkText("Easy")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//span[@title='All Question types']")).click();
			Thread.sleep(2000);
			new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase SearchFilterFunctionality in method searchFilterFunctionality ",e);
		}			
	}

}
