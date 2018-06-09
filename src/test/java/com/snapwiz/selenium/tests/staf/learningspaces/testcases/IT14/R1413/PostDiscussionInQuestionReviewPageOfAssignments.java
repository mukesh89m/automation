package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R1413;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;

public class PostDiscussionInQuestionReviewPageOfAssignments extends Driver{

	@Test
	public void postDiscussionInQuestionReviewPageOfAssignments()
	{
		try
		{
			String assignmentname = ReadTestData.readDataByTagName("", "assignmentname", "74");
			String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "74");
			new Assignment().create(74);//create assignment
			new Assignment().addQuestions(74, "qtn-type-true-false-img", assignmentname);
			new Assignment().addQuestions(74, "qtn-type-true-false-img", assignmentname);

			new LoginUsingLTI().ltiLogin("74_1");		//login a student
			new LoginUsingLTI().ltiLogin("74");		//login a instructor
			new Navigator().navigateFromProfileDropDown("Settings");	//navigate to Settings page from profile dropdown
			driver.findElement(By.cssSelector("label[for='two']")).click(); //Select Option-2 in the settings page
			driver.findElement(By.id("settings-save")).click();	//click on Save button
			new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
			new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, true, "1", "", "1", "1", "", "");
			new Assignment().assignToStudent(74);

			new LoginUsingLTI().ltiLogin("74_1");		//login a student
			new Assignment().openAssignmentFromAssignmentPage(74_1);
            if(driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).size() > 0) //next
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")));

            else if(driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")).size() > 0) //next
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next']")));
            Thread.sleep(4000);
			new Navigator().navigateToTab("Discussion"); //navigate to Discussion tab
            String discussionText = new RandomString().randomstring(15);//post a discussion
            new Discussion().postDiscussion(discussionText);
			List<WebElement> allElement = driver.findElements(By.className("stream-content-posts-list"));	//list all discussion
			allElement.get(0).click();	//click on a discussion
			Thread.sleep(2000);
            new Assignment().submitAssignmentWithImmediateFeedBack(74);//submit the static assignment
			driver.findElement(By.className("question-card-question-details")).click();//click on question card
			Thread.sleep(2000);
			new Navigator().navigateToTab("Discussion"); //navigate to Discussion tab
            String discussionText1 = new RandomString().randomstring(15);//post a discussion
            new Discussion().postDiscussion(discussionText1);//post a discussion
			List<WebElement> allElement1 = driver.findElements(By.className("stream-content-posts-list"));	//list all discussion
			allElement1.get(0).click();	//click on a discussion
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase postDiscussionInQuestionReviewPageOfAssignments in class PostDiscussionInQuestionReviewPageOfAssignments.",e);
		}
	}
}
