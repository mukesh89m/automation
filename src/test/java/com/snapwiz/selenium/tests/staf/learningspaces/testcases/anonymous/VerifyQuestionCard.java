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



public class VerifyQuestionCard extends Driver
{
private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.ClickQuestionCardSpecificQuestionShouldBeDisplayed");
	
	@Test
	public void verifyQuestionCard()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1289");
			new Navigator().NavigateTo("eTextbook");
			new DiagnosticTest().startTest(2);
			new AttemptTest().Diagonestictest();
			String QNum = driver.findElement(By.cssSelector("div[class='question-card-question-no']")).getText();
			
			String QDesc = driver.findElement(By.cssSelector("div[class='question-card-question-content']")).getText();
			
			String Points = driver.findElement(By.cssSelector("div[class='question-card-difficulty-level']")).getText();
			
			String timeTaken = driver.findElement(By.cssSelector("div[class='question-card-time-taken']")).getText();
			
			if((!QNum.equals("")) && (!QDesc.equals("")) && (!Points.equals("")) && (!timeTaken.equals("")))
			{
				logger.log(Level.INFO,"Question Number, Question Description, Points/Diffculty Level, Time taken are present in Question Card ");
			}
			else
			{
				Assert.fail("Question Number, Question Description, Points/Diffculty Level, Time taken are absent in Question Card");
			}
			
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TestCase verifyQuestionCard in class VerifyQuestionCard");
		}
	}

}
