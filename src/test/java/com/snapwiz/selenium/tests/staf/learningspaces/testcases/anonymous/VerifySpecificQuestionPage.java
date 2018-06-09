package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AttemptTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.logging.Level;
import java.util.logging.Logger;

public class VerifySpecificQuestionPage extends Driver
{
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.VerifySpecificQuestionPage");
@Test
public void verifySpecificQuestionPage()
{
	try
	{
		new LoginUsingLTI().ltiLogin("1287");
		new Navigator().NavigateTo("eTextbook");
		new DiagnosticTest().startTest(2);
		new AttemptTest().Diagonestictest();
		driver.findElement(By.cssSelector("div.question-card-question-content")).click();
		Thread.sleep(3000);
		String chName = driver.findElement(By.cssSelector("div[class='al-peformance-title']")).getAttribute("title");
		
		int sizeGraphIcon = driver.findElements(By.cssSelector("img[title=\"Performance by Questions\"]")).size();
		if(!chName.equals("") && sizeGraphIcon==1)
		{
			logger.log(Level.INFO,"Chapter name and graph icon are present");
		}
		else
		{
			Assert.fail("Chapter name and graph icon are absent");
		}
		String QNum= driver.findElement(By.cssSelector("div[class='al-diag-test-question-label']")).getText();
		
		String Question = driver.findElement(By.cssSelector("div[class='al-diag-test-question-raw-content']")).getText();
		
		if(!QNum.equals("") && !Question.equals(""))
		{
			logger.log(Level.INFO,"Question Number and Question is present");
		}
		else
		{
			Assert.fail("Question Number and Question is absent");
		}
		int reviewCheckBox = driver.findElements(By.cssSelector("label[id='al-diagtest-markForReview']")).size();
		
		String reviewText = driver.findElement(By.cssSelector("div[class='al-diagtest-markForReview-text']")).getText();
		
		if(reviewText.trim().equals("Mark for Review") && reviewCheckBox==1)
		{
			logger.log(Level.INFO,"Review checkbox is present");
		}
		else
		{
			Assert.fail("Review checkbox is absent");
		}
	}
	catch(Exception e)
	{
		
		Assert.fail("Exception in TestCase verifySpecificQuestionPage in class VerifySpecificQuestionPage");
	}
}

}
