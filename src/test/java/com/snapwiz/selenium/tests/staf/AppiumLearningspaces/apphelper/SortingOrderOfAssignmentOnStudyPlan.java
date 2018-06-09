package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import java.util.List;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.TopicOpen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.Click;

public class SortingOrderOfAssignmentOnStudyPlan
{
	public void sortingOrderOfAssignmentOnStudyplan(String assignment1,String assignment2,String assignment3)
	{
		try
		{
			new Navigator().NavigateTo("eTextbook");
			new TopicOpen().chapterOpen(1);//open chapter 1
			//sorting order of assignment before its open 1st time
			List<WebElement> allasignment=Driver.driver.findElements(By.className("inner-assignment-block"));

			if(!allasignment.get(0).getText().contains(assignment1))
				Assert.fail("assignment 1 not sort as due date");
			if(!allasignment.get(1).getText().contains(assignment2))
				Assert.fail("assignment 2  not sort as due date");
			if(!allasignment.get(2).getText().contains(assignment3))
				Assert.fail("assignment 3  not sort as due date");
			new Navigator().NavigateTo("eTextbook");
			new TopicOpen().chapterOpen(1);//open chapter 1
			new Click().clickbylist("inner-assignment-block", 0);
			new Navigator().NavigateTo("eTextbook");
			new TopicOpen().chapterOpen(1);//open chapter 1
			new Click().clickbylist("inner-assignment-block", 0);
			new Navigator().NavigateTo("eTextbook");
			new TopicOpen().chapterOpen(1);//open chapter 1
			new Click().clickbylist("inner-assignment-block", 0);
			new Navigator().NavigateTo("eTextbook");
			new TopicOpen().chapterOpen(1);//open chapter 1
			//sorting order of assignment after in progress

			List<WebElement> allasignmentinprog=Driver.driver.findElements(By.className("inner-assignment-block"));

			if(!allasignmentinprog.get(0).getText().contains(assignment1))
				Assert.fail("assignment 1 not sort as due date after status in progress");
			if(!allasignmentinprog.get(1).getText().contains(assignment2))
				Assert.fail("assignment 2  not sort as due date after status in progress");
			if(!allasignmentinprog.get(2).getText().contains(assignment3))
				Assert.fail("assignment 3  not sort as due date after status in progress");
			new Navigator().NavigateTo("eTextbook");
			new TopicOpen().chapterOpen(1);//open chapter 1
			new Click().clickbylist("inner-assignment-block", 0);
			new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.StaticAssignmentSubmit().staticAssignmentSubmit(0);
			new Navigator().NavigateTo("eTextbook");
			new TopicOpen().chapterOpen(1);//open chapter 1
			new Click().clickbylist("inner-assignment-block", 0);
			new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.StaticAssignmentSubmit().staticAssignmentSubmit(0);
		    new Navigator().NavigateTo("eTextbook");
			new TopicOpen().chapterOpen(1);//open chapter 1
			//sorting order of assignment after submitted
            List<WebElement> allAssignments = Driver.driver.findElements(By.className("inner-assignment-block"));

			if(!allAssignments.get(0).getText().contains(assignment3))
				Assert.fail("assignment 3 not sort as due date after status submitted");
			if(!allAssignments.get(1).getText().contains(assignment1))
				Assert.fail("assignment 1  not sort as due date after status submitted");
			if(!allAssignments.get(2).getText().contains(assignment2))
				Assert.fail("assignment 2  not sort as due date after status submitted");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper SortingOrderOfAssignmentOnStudyPlan in method SortingOrderOfAssignmentOnStudyPlan",e);
		}
	}

}
