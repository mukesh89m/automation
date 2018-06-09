package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R1410;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.StaticAssignmentSubmit;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;

public class SortingOrderOfChapterLevelGradableAssignmentbasedOnStatus extends Driver
{
	@Test(priority=1,enabled=true)
	public void sortingOrderOfChapterLevelGradableAssignmentbasedOnStatus()
	{
		try
		{
			String assignment1=ReadTestData.readDataByTagName("", "assessmentname", "29");
			String assignment2=ReadTestData.readDataByTagName("", "assessmentname", "291");
			new Assignment().create(29);
			new Assignment().create(291);
			new LoginUsingLTI().ltiLogin("29_1");
			new LoginUsingLTI().ltiLogin("29");//login as instructor
			new Assignment().assignToStudent(29);
			new Assignment().assignToStudent(291);
			new LoginUsingLTI().ltiLogin("29_1");//login as student
			new Navigator().NavigateTo("eTextBook");
			new TopicOpen().chapterOpen(0);
			new Click().clickbylist("inner-assignment-block", 0);//click on assignment from study plan
			new StaticAssignmentSubmit().staticAssignmentSubmit(0);//submit assignment by student
			new Navigator().NavigateTo("eTextbook");
			new TopicOpen().chapterOpen(0);//open chapter 1
			new Click().clickbylist("inner-assignment-block", 0);//click on assignment from study plan
			new StaticAssignmentSubmit().staticAssignmentSubmit(0);//submit assignment by student
			new LoginUsingLTI().ltiLogin("29");//login as instructor
			new Assignment().provideGRadeToStudent(29);//provide grade to student
			new Assignment().provideGRadeToStudent(291);//provide grade to student
			new LoginUsingLTI().ltiLogin("29_1");//login as student
			new Navigator().NavigateTo("eTextBook");
			new TopicOpen().chapterOpen(0);
			//29-33

			WebElement element=driver.findElement(By.xpath("//a[starts-with(@title,'Assessment_IT14_R1410')]"));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
			Thread.sleep(500);
			List<WebElement> allasignmentsubmitted=driver.findElements(By.xpath("//a[starts-with(@title,'Assessment_IT14_R1410')]"));//fetch all assignemt name
			System.out.println("assignment1:"+assignment1);

			if(!allasignmentsubmitted.get(0).getText().contains(assignment1))
				Assert.fail("assignment 1 not sort as due date after status submitted");
			if(!allasignmentsubmitted.get(1).getText().contains(assignment2))
				Assert.fail("assignment 2  not sort as due date after status submitted");
			//74 -after click on assignment from study plan by instructor its redirect to assignment review page
			new LoginUsingLTI().ltiLogin("29");//login as instructor
			new Navigator().NavigateTo("eTextBook");
			new TopicOpen().chapterOpen(0);
			new Click().clickbylist("inner-assignment-block", 0);//lcik on assignment name1
			boolean questionreviewpage=new BooleanValue().booleanbyclass("cms-content-question-review-wrapper");//verify review page
			if(questionreviewpage==false)
				Assert.fail("afetr click on assignment its not redirect ot question review page");
		} 
		catch (Exception e)
		{
			Assert.fail("Exception in testcase method sortingOrderOfChapterLevelGradableAssignmentbasedOnStatus in class SortingOrderOfChapterLevelGradableAssignmentbasedOnStatus ",e);
		}
	}
	
	@Test(priority=2,enabled=true)
	public void SortingOrderOftopicLevelGradableAssignmentbasedOnStatus()
	{
		try
		{
			String assignment1=ReadTestData.readDataByTagName("", "assessmentname", "46");
			String assignment2=ReadTestData.readDataByTagName("", "assessmentname", "461");
			new Assignment().createAssignmentAtTopicLevel(46);
			new Assignment().createAssignmentAtTopicLevel(461);
			new LoginUsingLTI().ltiLogin("46_1");
			new LoginUsingLTI().ltiLogin("46");//login as instructor
			new Assignment().assignToStudent(46);
			new Assignment().assignToStudent(461);
			new LoginUsingLTI().ltiLogin("46_1");//login as student
			new Navigator().NavigateTo("eTextBook");
			new TopicOpen().chapterOpen(0);						
			new Click().clickbylist("inner-assignment-block", 0);//click on topic level assignment from study plan
			new StaticAssignmentSubmit().staticAssignmentSubmit(0);//submit topic level assignment by student
			new Navigator().NavigateTo("eTextbook");
			new TopicOpen().chapterOpen(0);//open chapter 1
			new Click().clickbylist("inner-assignment-block", 0);//click on topic level assignment from study plan
			new StaticAssignmentSubmit().staticAssignmentSubmit(0);//submit  topic level assignment by student
			new LoginUsingLTI().ltiLogin("46");//login as instructor
			new Assignment().provideGRadeToStudent(46);//provide grade to student
			new Assignment().provideGRadeToStudent(461);//provide grade to student
			new LoginUsingLTI().ltiLogin("46_1");//login as student
			new Navigator().NavigateTo("eTextBook");
			new TopicOpen().chapterOpen(0);
			//46-50
			List<WebElement> allasignmentsubmitted=driver.findElements(By.className("inner-assignment-block"));//fetch all assignemt name

			if(!allasignmentsubmitted.get(0).getText().contains(assignment1))
				Assert.fail("topic level assignment 1 not sort as due date after status submitted");
			if(!allasignmentsubmitted.get(1).getText().contains(assignment2))
				Assert.fail("topic level assignment 2  not sort as due date after status submitted");
			//75 -after click on topic level assignment from study plan by instructor its redirect to assignment review page
			new LoginUsingLTI().ltiLogin("46");//login as instructor
			new Navigator().NavigateTo("eTextBook");
			new TopicOpen().chapterOpen(0);
			new Click().clickbylist("inner-assignment-block", 0);//click  on topic level assignment name1
			boolean questionreviewpage=new BooleanValue().booleanbyclass("cms-content-question-review-wrapper");//verify review page
			if(questionreviewpage==false)
				Assert.fail("afetr click on topic level assignment its not redirect ot question review page");
		}
		catch (Exception e)
		{
			Assert.fail("Exception in testcase method SortingOrderOftopicLevelGradableAssignmentbasedOnStatus in class SortingOrderOfChapterLevelGradableAssignmentbasedOnStatus ",e);
		}
	}

}
