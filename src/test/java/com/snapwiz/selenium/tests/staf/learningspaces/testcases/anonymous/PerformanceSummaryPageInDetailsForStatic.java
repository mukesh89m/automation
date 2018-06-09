package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AttemptTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PerfomancePageVerification;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;

public class PerformanceSummaryPageInDetailsForStatic extends Driver
{
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.PerformanceSummaryPageInDetailsForStatic");
@Test
public void performanceSummaryPageInDetailsForStatic()
{
	try
	{
		new LoginUsingLTI().ltiLogin("1282");
		String statictest = ReadTestData.readDataByTagName("tocdata", "statictest", "1");
		new Navigator().NavigateTo("eTextbook");
		new TopicOpen().topicOpen(statictest);
		if(!new PerfomancePageVerification().checkforTextAlongBarGraphForStatic())
			Assert.fail("Text along x-axis and y-axis are not correct");
		
		new AttemptTest().StaticTest();
		String performanceSummaryText = driver.findElement(By.cssSelector("div[class='al-performance-chart-title']")).getText();
		
		String questionCount = driver.findElement(By.cssSelector("div[id='al-performance-chart-label-id']")).getText().replaceAll("[\n\r]", "");
		
		Thread.sleep(3000);
		
		if(!performanceSummaryText.trim().equals("Performance Summary") && questionCount.trim().equals("18Questions"))
			Assert.fail("Doesnt Display 'Performance Summary' and total number of questions properly");
		
		String filterText = driver.findElement(By.cssSelector("span[class='filter-questions-label']")).getText();
		
		String questionCount1 = driver.findElement(By.cssSelector("div[class='static-assessment-question-count']")).getText();
		
		int sizeQuestionCard = driver.findElements(By.className("al-performance-report-sidebar-content")).size();
		
		if(filterText.trim().equals("Filter Questions") && questionCount1.contains("Question Count") && sizeQuestionCard==1)
		{
			logger.log(Level.INFO,"The options 'Filter Questions' and 'question count' is also present along with the question card");
		}
		else
		{
			Assert.fail("The options 'Filter Questions' and 'question count' and the question card is not displayed properly");
		}
		
		boolean getRoboicon = driver.findElement(By.cssSelector("div[class='robo-icon']")).isDisplayed();
		
		String retakeButton = driver.findElement(By.cssSelector("span[class='static-assessment-retake']")).getText();
		
		int sizecontinueButton = driver.findElements(By.cssSelector("input[type='button']")).size();
		
		if(getRoboicon == true && retakeButton.trim().equals("Retake") && sizecontinueButton==1) //changed Re-Take to Retake as per coming in the product
		{
			logger.log(Level.INFO,"The footer contains the Robo icon, 'Ra-take' option along with 'Continue' button");
		}
		else
		{
			Assert.fail("In The footer Robo icon, 'Re-take' option and 'Continue' button are not displayed properly.");
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
		Assert.fail("Exception in TestCase performanceSummaryPageInDetailsForStatic in class PerformanceSummaryPageInDetailsForStatic",e);
	}
}

}
