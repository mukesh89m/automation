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
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class FunctionalityOfGoButton extends Driver
{

	@Test(priority=1,enabled=true)
	public void functionalityOfGoButton()
	{
		try
		{
			MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
			new LoginUsingLTI().ltiLogin("165");// login as instructor
			new Navigator().NavigateTo("My Question Bank");//click on my question bank
			myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
			Thread.sleep(2000);
			String searchtext=ReadTestData.readDataByTagName("tocdata", "searchtext", "1");
			new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchtext);
			Thread.sleep(2000);
			int numberofquestion=driver.findElements(By.className("ls-ins-question-wrapper")).size();//check how many question appper in the search
			new Click().clickByclassname("ls-ins-browse-text");//click on view browse option
			driver.findElement(By.linkText("All Chapters")).click();
			Thread.sleep(2000);
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

			String filtercount=new TextFetch().textfetchbyclass("ls-ins-search-filter-count");//fetch count of filter
			//new Click().clickBycssselector("div[class='view-browse-options-drop-down view-browse-dropdown-enabled']");//click on view browse option
			String filtercount1=new TextFetch().textfetchbyclass("ls-ins-search-filter-count");
			if(!filtercount.equals(filtercount1))
			{
				Assert.fail("Filter count is not equal again click on browse option");
			}
			new Click().clickByclassname("ls-instructor-only-assignment-check");//checked box instrictor only
			new Click().clickByclassname("ls-ins-browse-go");//click on go button
			int numberofquestion1=driver.findElements(By.className("ls-ins-question-wrapper")).size();
			if(numberofquestion1>numberofquestion)
			{
				Assert.fail("after click on instructor only filter number of question not decrease");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase class FunctionalityOfGoButton  in testcase method functionalityOfGoButton",e);
		}
	}
	@Test(priority=2,enabled=true)
	public void functionalityOfGoButtonofFilter()
	{
		try
		{
			MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
			new LoginUsingLTI().ltiLogin("165");// login as instructor
			new Navigator().NavigateTo("My Question Bank");//click on my question bank
			myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
			Thread.sleep(2000);
			String searchtext=ReadTestData.readDataByTagName("tocdata", "searchtext", "1");
			new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchtext);
			Thread.sleep(2000);
			int numberofquestion=driver.findElements(By.className("ls-ins-question-wrapper")).size();//check how many question appper in the search
			new Click().clickByclassname("ls-ins-view-filters");//click on filter
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
			String filtercount=new TextFetch().textfetchbyclass("ls-ins-search-filter-count");//fetch count of filter
			new Click().clickByclassname("ls-ins-view-filters");//click on filter
			String filtercount1=new TextFetch().textfetchbyclass("ls-ins-search-filter-count");
			if(!filtercount.equals(filtercount1))
			{
				Assert.fail("Filter count is not equal again click on browse option");
			}
			new Click().clickByclassname("ls-instructor-only-assignment-check");//checked box instrictor only
			new Click().clickByclassname("ls-ins-browse-go");//click on go button
			int numberofquestion1=driver.findElements(By.className("ls-ins-question-wrapper")).size();
			if(numberofquestion1>numberofquestion)
			{
				Assert.fail("after click on instructor only filter number of question not decrease");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase class FunctionalityOfGoButton  in testcase method functionalityOfGoButtonofFilter",e);
		}
	}

}
