package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PerfomancePageVerification;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;

public class PerformanceTabForStatic extends Driver {
	@Test
	public void performanceTabForStatic()
	{
		try
		{
			String statictest = ReadTestData.readDataByTagName("tocdata", "statictest", "1");
			new LoginUsingLTI().ltiLogin("2657");
			Thread.sleep(3000);
			new Navigator().NavigateTo("eTextbook");
			new TopicOpen().topicOpen(statictest);		//start static test
			String assessmentName = driver.findElement(By.className("al-diag-test-chapter-label")).getText();
			if(!assessmentName.trim().contains(statictest))
				Assert.fail("Header Doesnot contain the actual static assessment name.");
			
			String totalQuestions = driver.findElement(By.className("al-diag-chapter-details")).getText();
			if(!totalQuestions.contains("1 of"))
				Assert.fail("Total question count is not showing for static tests.");
			
			String timeSpentLabel = driver.findElement(By.className("timer-label")).getText();
			if(!timeSpentLabel.equals("Time Spent"))
				Assert.fail("Time Spent label is absent.");
		//time icon has been removed	
			/*String timeIcon = driver.findElement(By.id("assessmentTimer")).getCssValue("background-image");
			if(!timeIcon.contains("timer.png"))
				Assert.fail("Time icon is absent in static assessment page.");*/
			
			String quitIcon = driver.findElement(By.className("al-quit-diag-test-icon")).getCssValue("background-image");
			if(!quitIcon.contains("quit-diag-icon.png"))
				Assert.fail("Quit icon is absent in static assessment page.");
			
			String performanceFrame = driver.findElement(By.className("al-diagnostic-test-sidebar-content-wrapper")).getText();
			if(!performanceFrame.contains("Points"))
				Assert.fail("Performance frame at RHS the 'Points' option is absent.");
			
			String str = driver.findElement(By.className("al-content-box-title-header")).getText();
			if(!str.contains("Performance in Last 10 Qs"))
				Assert.fail("Perfomance for last 10 question is absent in Performance frame at RHS.");
			
			if(!new PerfomancePageVerification().checkTenBarsAreComingForAnswersForStaic())
				Assert.fail("Performance in the last 10 Q's are coming in bar chart");
			
			if(!performanceFrame.contains("%"))
				Assert.fail("Percentage of students got it correct is absent in Performance frame at RHS.");
			
			
			if(!performanceFrame.contains("Students got it correct"))
				Assert.fail("'Percentage of students got it correct' label is absent in Performance frame at RHS.");
			
			if(!performanceFrame.contains("Question Objectives"))
				Assert.fail("'Question Objectives' is absent in Performance frame at RHS.");
			
			String review = driver.findElement(By.className("al-diagtest-markForReview-text")).getText();
			if(!review.equals("Mark for Review"))
				Assert.fail("'Mark for Review' label is absent in the footer for static assessmant.");
			
			String reviewCheckBox = driver.findElement(By.cssSelector("label[id='al-diagtest-markForReview']")).getCssValue("background-image");
			if(!reviewCheckBox.contains("check-box-small-unselected.png"))
				Assert.fail("Mark for review checkbox is absent.");
			
			int submitButtonSize = driver.findElements(By.className("ls-static-practice-test-submit-button")).size();
			if(submitButtonSize == 0)
				Assert.fail("Submit button is absent in the static assessment page.");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in performanceTabForStatic in class PerformanceTabForStatic",e);
		}
	}

}
