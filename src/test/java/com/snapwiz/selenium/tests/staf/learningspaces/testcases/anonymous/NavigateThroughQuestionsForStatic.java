package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AttemptTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TabClose;

public class NavigateThroughQuestionsForStatic extends Driver {

	@Test
	public void navigateThroughQuestionsForStatic()
	{
		try
		{
			String statictest = ReadTestData.readDataByTagName("tocdata", "statictest", "1");
			new LoginUsingLTI().ltiLogin("2661");
			Thread.sleep(3000);
			new Navigator().NavigateTo("eTextbook");
			new TopicOpen().topicOpen(statictest);		//start static test
			String totalQuestions = driver.findElement(By.className("al-diag-chapter-details")).getText();
			if(!totalQuestions.contains("1 of"))
				Assert.fail("1st question is not showing for static tests.");
			//check for submit button
			int submitButton = driver.findElements(By.className("ls-static-practice-test-submit-button")).size();
			if(submitButton == 0)
				Assert.fail("Submit button is absent for static test.");

			driver.findElement(By.cssSelector("input[type=\"button\"]")).click();		//click submit button
			Thread.sleep(3000);
			String totalQuestions1 = driver.findElement(By.className("al-diag-chapter-details")).getText();
			if(!totalQuestions1.contains("1 of"))
					Assert.fail("After clicking Submit button, Feedback screen doesnt' appear for static tests.");
			
			
			//check for next button
			int nextButton = driver.findElements(By.className("ls-static-practice-test-next-button")).size();
			if(nextButton == 0)
				Assert.fail("Next button is absent for static test.");
			
			driver.findElement(By.cssSelector("input[type=\"button\"]")).click();		//click on NEXT button
			Thread.sleep(3000);
			driver.findElement(By.cssSelector("input[type=\"button\"]")).click();		//click submit button
			Thread.sleep(3000);
			//check for correct option tick mark in feedback screen
			List<WebElement> WE = driver.findElements(By.cssSelector("div[class='user-response-data']"));
			String correctOption = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",WE.get(2));	//the coreect icon is at index 2
			if(!correctOption.contains("correct-icon.png"))
				Assert.fail("For static test, in Feedback screen the correct answer is not delivered.");
			
			String totalQuestions2 = driver.findElement(By.className("al-diag-chapter-details")).getText();
			if(!totalQuestions2.contains("2 of"))
					Assert.fail("After clicking Next button, The next question is not appearing for static tests.");
			driver.findElement(By.cssSelector("input[type=\"button\"]")).click();		//click on NEXT button
			Thread.sleep(3000);
			//again check for submit button
			int submitButton1 = driver.findElements(By.className("ls-static-practice-test-submit-button")).size();
			if(submitButton1 == 0)
				Assert.fail("Submit button is absent in the next question for static test.");
			
			driver.findElement(By.cssSelector("input[type=\"button\"]")).click();		//click submit button
			Thread.sleep(3000);
			String totalQuestions3 = driver.findElement(By.className("al-diag-chapter-details")).getText();
			if(!totalQuestions3.contains("3 of"))
					Assert.fail("After clicking Submit button, Feedback screen doesn't appear for static tests.");
			//again check for next button
			int nextButton1 = driver.findElements(By.className("ls-static-practice-test-next-button")).size();
			if(nextButton1 == 0)
				Assert.fail("Next button is absent in other questions for static test.");
			
			new AttemptTest().StaticTest();		//finish the static test
			//check for performance summary page
			String performanceSummary = driver.findElement(By.cssSelector("div[class='al-performance-chart-title']")).getText();
			if(!performanceSummary.equals("Performance Summary"))
				Assert.fail("performance summary page is not displayed properly after submission of Statis test.");
			
			new TabClose().tabClose(1);			//close the tab
			new TopicOpen().topicOpen(statictest);		//start the same static test
			//check performance summary page
			String performanceSummary1 = driver.findElement(By.cssSelector("div[class='al-performance-chart-title']")).getText();
			if(!performanceSummary1.equals("Performance Summary"))
				Assert.fail("After restatring a static test which has been finished earlier, it doesnt land on the performance summary page.");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in navigateThroughQuestionsForStatic in class NavigateThroughQuestionsForStatic",e);
		}
	}

}
