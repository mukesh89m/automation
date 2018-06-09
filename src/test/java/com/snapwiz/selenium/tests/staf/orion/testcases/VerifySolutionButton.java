package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectAnswerAndSubmit;

public class VerifySolutionButton {
	@Test(priority = 1, enabled = true)
	public void verifySolutionButton()
	{
		try
		{
			Driver.startDriver();
			List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids	
			new Assignment().createChapter(137, Integer.parseInt(tloids.get(0)));
			//create diag test
			new Assignment().createPracticeAtChapterLevel(137, "Adaptive Component Diagnostic", true, false, true, Integer.parseInt(tloids.get(0)));
			//create adaptive test
			new Assignment().createPracticeAtChapterLevel(137, "Adaptive Component Practice", true, false, true, Integer.parseInt(tloids.get(0)));
			new LoginUsingLTI().ltiLogin("137");
			//open last chapter diag test
			List<WebElement> allbegin=Driver.driver.findElements(By.cssSelector("img[title=\"Begin\"]"));			
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",allbegin.get(allbegin.size()-1));//click on latest created test 
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("a[id='2']")).click();//click on confidence level
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("al-continue-to-diagnostic")).click();//click on continue button to start test
			Thread.sleep(2000);
			
			new SelectAnswerAndSubmit().DiagTestWithCorrectAnswer(3);	
			Driver.driver.findElement(By.cssSelector("img[title=\"ORION Dashboard\"]")).click();		//click to go to dashboard
			Thread.sleep(3000);
			List<WebElement> allPractice=Driver.driver.findElements(By.cssSelector("span[title='Practice']"));			
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",allPractice.get(allPractice.size()-1));//click on latest created practice test 
			Thread.sleep(2000);
			String corranswer=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
			Thread.sleep(2000);
			List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
			for (WebElement answerchoice: answer)
			{
				
				if(answerchoice.getText().trim().equals(corranswer))
				{
					((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",answerchoice);		//select correct answer			
					break;
				}
			}
		   Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")).click();
		   Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
		   Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")).click();
		   Thread.sleep(2000);
		   Driver.driver.findElement(By.cssSelector("img[title=\"Solution\"]")).click();
		   Thread.sleep(2000);
		   String solutionLabel = Driver.driver.findElement(By.className("al-diag-test-solution-content-title")).getText();
		   String solutionText = Driver.driver.findElement(By.className("diag-test-solution-raw-content")).getText();
		   if(!solutionLabel.equals("Solution") || solutionText.length()==0 )
		   		Assert.fail("On Clicking Solution button the solution is not displayed.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in verifySolutionButton in class VerifySolutionButton",e);
		}
	}
	
	
	
	@Test(priority = 2, enabled = true)
	public void verifySolutionButtonByNotProvidingSolutionText()
	{
		try
		{
			Driver.startDriver();	
			List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids	
			new Assignment().createChapter(139, Integer.parseInt(tloids.get(0)));
			//create diag test
			new Assignment().createPracticeAtChapterLevel(139, "Adaptive Component Diagnostic", true, false, true, Integer.parseInt(tloids.get(0)));
			//create adaptive test
			new Assignment().createPracticeAtChapterLevel(139, "Adaptive Component Practice", false, false, true, Integer.parseInt(tloids.get(0)));
			new LoginUsingLTI().ltiLogin("139");
			//open last chapter diag test
			List<WebElement> allbegin=Driver.driver.findElements(By.cssSelector("img[title=\"Begin\"]"));			
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",allbegin.get(allbegin.size()-1));//click on latest created test 
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("a[id='2']")).click();//click on confidence level
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("al-continue-to-diagnostic")).click();//click on continue button to start test
			Thread.sleep(2000);
			//answer the diag test first
			new SelectAnswerAndSubmit().DiagTestWithCorrectAnswer(3);	
			Driver.driver.findElement(By.cssSelector("img[title=\"ORION Dashboard\"]")).click();		//click to go to dashboard
			Thread.sleep(3000);
			List<WebElement> allPractice=Driver.driver.findElements(By.cssSelector("span[title='Practice']"));			
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",allPractice.get(allPractice.size()-1));//click on latest created practice test 
			Thread.sleep(2000);
			String corranswer=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
			Thread.sleep(2000);
			List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
			for (WebElement answerchoice: answer)
			{
				
				if(answerchoice.getText().trim().equals(corranswer))
				{
					((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",answerchoice);		//select correct answer			
					break;
				}
			}
		   Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")).click();		//click submit 
		   Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();		//close notofication
		   Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")).click();		//click submit 
		   Thread.sleep(2000);
		   int solutionButton = Driver.driver.findElements(By.cssSelector("img[title=\"Solution\"]")).size();
		   if(solutionButton != 0)
		   		Assert.fail("Solution button is present for the questions that dont have any solution text provided.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in verifySolutionButtonByNotProvidingSolutionText in class VerifySolutionButton",e);
		}
	}
	@Test(priority = 3, enabled = true)
	public void verifySolutionButtonForTwoQuestion()
	{
		try
		{
			Driver.startDriver();	
			List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids	
			String practiceassessmentname= ReadTestData.readDataByTagName("VerifySolutionButton", "practiceassessmentname", "140");
			new Assignment().createChapter(140, Integer.parseInt(tloids.get(0)));
			//create diag test
			new Assignment().createPracticeAtChapterLevel(140, "Adaptive Component Diagnostic", true, false, true, Integer.parseInt(tloids.get(0)));
			//create adaptive test
			new Assignment().createPracticeAtChapterLevel(140, "Adaptive Component Practice", false, false, false, Integer.parseInt(tloids.get(0)));
			new Assignment().addQuestions(140, "qtn-multiple-choice-img", practiceassessmentname, true, false, null, true, Integer.parseInt(tloids.get(0)));
			new LoginUsingLTI().ltiLogin("140");
			
			//open last chapter diag test
			List<WebElement> allbegin=Driver.driver.findElements(By.cssSelector("img[title=\"Begin\"]"));			
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",allbegin.get(allbegin.size()-1));//click on latest created test 
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("a[id='2']")).click();//click on confidence level
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("al-continue-to-diagnostic")).click();//click on continue button to start test
			Thread.sleep(2000);
			//answer the diag test first
			new SelectAnswerAndSubmit().DiagTestWithCorrectAnswer(3);	
			Driver.driver.findElement(By.cssSelector("img[title=\"ORION Dashboard\"]")).click();		//click to go to dashboard
			Thread.sleep(3000);
			List<WebElement> allPractice=Driver.driver.findElements(By.cssSelector("span[title='Practice']"));			
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",allPractice.get(allPractice.size()-1));//click on latest created practice test 
			Thread.sleep(2000);
			for( int i =0 ; i< 2; i++)		//submit two questions
			{
			String corranswer=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
			Thread.sleep(2000);
			List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
			for (WebElement answerchoice: answer)
			{
				
				if(answerchoice.getText().trim().equals(corranswer))
				{
					((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",answerchoice);		//select correct answer			
					break;
				}
			}
		   Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")).click();		//click submit 
		   Thread.sleep(2000);
		   int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
		   if(noticesize==1)
			
			{
				String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
				if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
				{
					Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
					Thread.sleep(2000);
					Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")).click();
					Thread.sleep(2000);
					Driver.driver.findElement(By.cssSelector("img[title=\"Solution\"]")).click();
					Thread.sleep(2000);
					String solutionLabel = Driver.driver.findElement(By.className("al-diag-test-solution-content-title")).getText();
					String solutionText = Driver.driver.findElement(By.className("diag-test-solution-raw-content")).getText();
					if(!solutionLabel.equals("Solution") || solutionText.length()==0 )
					   	Assert.fail("On Clicking Solution button the solution is not displayed.");
					
				}
				
			}
		  
		   ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Solution\"]")));
		   Thread.sleep(2000);
		   String solutionLabel = Driver.driver.findElement(By.className("al-diag-test-solution-content-title")).getText();
		   String solutionText = Driver.driver.findElement(By.className("diag-test-solution-raw-content")).getText();
		   if(!solutionLabel.equals("Solution") || solutionText.length()==0 )
		   		Assert.fail("On Clicking Solution button the solution is not displayed for 1st question.");
		   Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")).click();
		   Thread.sleep(2000);
		 }
		}
		catch(Exception e)
		{
			Assert.fail("Exception in verifySolutionButtonForTwoQuestion in class VerifySolutionButton",e);
		}
	}
	@Test(priority = 4, enabled = true)
	public void verifySolutionButtonForFirstQuestionWithSolutionSecondWithoutSolution()
	{
		try
		{
		Driver.startDriver();	
		List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids	
		String practiceassessmentname= ReadTestData.readDataByTagName("VerifySolutionButton", "practiceassessmentname", "144");
		new Assignment().createChapter(144, Integer.parseInt(tloids.get(0)));
		//create diagnostic test
		new Assignment().createPracticeAtChapterLevel(144, "Adaptive Component Diagnostic", true, false,true, Integer.parseInt(tloids.get(0)));
		//create adaptive test
		new Assignment().createPracticeAtChapterLevel(144, "Adaptive Component Practice", true, false, true, Integer.parseInt(tloids.get(0)));
		new Assignment().addQuestions(144, "qtn-multiple-choice-img", practiceassessmentname, false, false,null, true, Integer.parseInt(tloids.get(0)));
		
		new LoginUsingLTI().ltiLogin("144");
		
		//open last chapter diag test
		List<WebElement> allbegin=Driver.driver.findElements(By.cssSelector("img[title=\"Begin\"]"));			
		((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",allbegin.get(allbegin.size()-1));//click on latest created test 
		Thread.sleep(2000);
		Driver.driver.findElement(By.cssSelector("a[id='2']")).click();//click on confidence level
		Thread.sleep(2000);
		Driver.driver.findElement(By.className("al-continue-to-diagnostic")).click();//click on continue button to start test
		Thread.sleep(2000);
		//answer the diag test first
		new SelectAnswerAndSubmit().DiagTestWithCorrectAnswer(3);	
		Driver.driver.findElement(By.cssSelector("img[title=\"ORION Dashboard\"]")).click();		//click to go to dashboard
		Thread.sleep(3000);
		List<WebElement> allPractice=Driver.driver.findElements(By.cssSelector("span[title='Practice']"));			
		((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",allPractice.get(allPractice.size()-1));//click on latest created practice test 
		Thread.sleep(2000);
		for( int i =0 ; i< 2; i++)		//submit two questions
		{
		String corranswer=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
		Thread.sleep(2000);
		List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
		for (WebElement answerchoice: answer)
		{
			
			if(answerchoice.getText().trim().equals(corranswer))
			{
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",answerchoice);		//select correct answer			
				break;
			}
		}
	   Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")).click();		//click submit 
	   Thread.sleep(2000);
	   int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
	   if(noticesize==1)
		
		{
			String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
			if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
			{
				Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
				Thread.sleep(2000);
				Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")).click();
				Thread.sleep(2000);
				
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Solution\"]")));
				Thread.sleep(2000);
				String solutionLabel = Driver.driver.findElement(By.className("al-diag-test-solution-content-title")).getText();
				String solutionText = Driver.driver.findElement(By.className("diag-test-solution-raw-content")).getText();
				Thread.sleep(2000);
				if(!solutionLabel.equals("Solution") || solutionText.length()==0 )
			   		Assert.fail("On Clicking Solution button the solution is not displayed for 1st question.");
				
				
				}
			 }
			
		  ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Solution\"]")));
		   Thread.sleep(2000);
		   int solution = Driver.driver.findElements(By.cssSelector("img[title=\"Solution\"]")).size();
		   Thread.sleep(2000);
		   if(solution > 1)
			   	Assert.fail("The solution button is present for those question for which the solution is not provided.");
		   Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")).click();
		   Thread.sleep(2000);
		}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in verifySolutionButtonForFirstQuestionWithSolutionSecondWithoutSolution in class VerifySolutionButton",e);
		}
	}
	
	@Test(priority = 5, enabled = true)
	public void verifySolutionButtonForFirstQuestionWithoutSolutionSecondWithSolution()
	{
		try
		{
			Driver.startDriver();	
			List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids	
			String practiceassessmentname= ReadTestData.readDataByTagName("VerifySolutionButton", "practiceassessmentname", "147");
			new Assignment().createChapter(147, Integer.parseInt(tloids.get(0)));
			//create diagnostic test
			new Assignment().createPracticeAtChapterLevel(147, "Adaptive Component Diagnostic", true, false, true, Integer.parseInt(tloids.get(0)));
			//create adaptive test
			new Assignment().createPracticeAtChapterLevel(147, "Adaptive Component Practice", false, false, true, Integer.parseInt(tloids.get(0)));
			new Assignment().addQuestions(147, "qtn-multiple-choice-img", practiceassessmentname, true, false, null, true, Integer.parseInt(tloids.get(0)));
			
			new LoginUsingLTI().ltiLogin("147");
			
			//open last chapter diag test
			List<WebElement> allbegin=Driver.driver.findElements(By.cssSelector("img[title=\"Begin\"]"));			
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",allbegin.get(allbegin.size()-1));//click on latest created test 
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("a[id='2']")).click();//click on confidence level
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("al-continue-to-diagnostic")).click();//click on continue button to start test
			Thread.sleep(2000);
			//answer the diag test first
			new SelectAnswerAndSubmit().DiagTestWithCorrectAnswer(3);	
			Driver.driver.findElement(By.cssSelector("img[title=\"ORION Dashboard\"]")).click();		//click to go to dashboard
			Thread.sleep(3000);
			List<WebElement> allPractice=Driver.driver.findElements(By.cssSelector("span[title='Practice']"));			
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",allPractice.get(allPractice.size()-1));//click on latest created practice test 
			Thread.sleep(2000);
			
			String corranswer=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
			Thread.sleep(2000);
			List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
			for (WebElement answerchoice: answer)
			{
				
				if(answerchoice.getText().trim().equals(corranswer))
				{
					((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",answerchoice);		//select correct answer			
					break;
				}
			}
		   Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")).click();		//click submit 
		   Thread.sleep(2000);
		   int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
		   if(noticesize==1)
			
			{
				String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
				if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
				{
					Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
					Thread.sleep(2000);
					Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")).click();
					Thread.sleep(2000);
					int solution = Driver.driver.findElements(By.cssSelector("img[title=\"Solution\"]")).size();
					Thread.sleep(2000);
					if(solution != 0)
						   Assert.fail("The solution button is present for those question for which the solution is not provided.");
					
					}
				}
			   ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")));
		       Thread.sleep(2000);
		       ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
		       Thread.sleep(2000);
			   ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Solution\"]")));
			   Thread.sleep(2000);
			   String solutionLabel = Driver.driver.findElement(By.className("al-diag-test-solution-content-title")).getText();
			   String solutionText = Driver.driver.findElement(By.className("diag-test-solution-raw-content")).getText();
			   Thread.sleep(2000);
			   if(!solutionLabel.equals("Solution") || solutionText.length()==0 )
			   Assert.fail("On Clicking Solution button the solution is not displayed for 1st question.");
			   Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")).click();
			   Thread.sleep(2000);
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in verifySolutionButtonForFirstQuestionWithoutSolutionSecondWithSolution in class VerifySolutionButton",e);
		}
	}
	
	@Test(priority = 6, enabled = true)
	public void verifySolutionButtonForTwoQuestionWithoutSolution()
	{
		try
		{
			Driver.startDriver();	
			List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids	
			String practiceassessmentname= ReadTestData.readDataByTagName("VerifySolutionButton", "practiceassessmentname", "150");
			new Assignment().createChapter(150, Integer.parseInt(tloids.get(0)));
			//create diagnostic test
			new Assignment().createPracticeAtChapterLevel(150, "Adaptive Component Diagnostic", true, false, true, Integer.parseInt(tloids.get(0)));
			//create adaptive test
			new Assignment().createPracticeAtChapterLevel(150, "Adaptive Component Practice", false, false, true, Integer.parseInt(tloids.get(0)));
			new Assignment().addQuestions(150, "qtn-multiple-choice-img", practiceassessmentname, false, false, null, true, Integer.parseInt(tloids.get(0)));
			
			new LoginUsingLTI().ltiLogin("150");
			
			//open last chapter diag test
			List<WebElement> allbegin=Driver.driver.findElements(By.cssSelector("img[title=\"Begin\"]"));			
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",allbegin.get(allbegin.size()-1));//click on latest created test 
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("a[id='2']")).click();//click on confidence level
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("al-continue-to-diagnostic")).click();//click on continue button to start test
			Thread.sleep(2000);
			//answer the diag test first
			new SelectAnswerAndSubmit().DiagTestWithCorrectAnswer(3);	
			Driver.driver.findElement(By.cssSelector("img[title=\"ORION Dashboard\"]")).click();		//click to go to dashboard
			Thread.sleep(3000);
			List<WebElement> allPractice=Driver.driver.findElements(By.cssSelector("span[title='Practice']"));			
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",allPractice.get(allPractice.size()-1));//click on latest created practice test 
			Thread.sleep(2000);
			
			String corranswer=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
			Thread.sleep(2000);
			List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
			for (WebElement answerchoice: answer)
			{
				
				if(answerchoice.getText().trim().equals(corranswer))
				{
					((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",answerchoice);		//select correct answer			
					break;
				}
			}
		   Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")).click();		//click submit 
		   Thread.sleep(2000);
		   int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
		   if(noticesize==1)
			
			{
				String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
				if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
				{
					Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
					Thread.sleep(2000);
					Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")).click();
					Thread.sleep(2000);
					int solution = Driver.driver.findElements(By.cssSelector("img[title=\"Solution\"]")).size();
					Thread.sleep(2000);
					if(solution != 0)
						   Assert.fail("The solution button is present for those question for which the solution is not provided.");
					
					}
				}
			   ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")));
		       Thread.sleep(2000);
		       ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
		       Thread.sleep(2000);
		       int solution = Driver.driver.findElements(By.cssSelector("img[title=\"Solution\"]")).size();
			   Thread.sleep(2000);
			   if(solution != 0)
					 Assert.fail("The solution button is present for those question for which the solution is not provided.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in verifySolutionButtonForTwoQuestionWithoutSolution in class VerifySolutionButton",e);
		}
	}
	
	@Test(priority = 7, enabled = true)
	public void verifySolutionButtonBySkippingTheQuestion()
	{
		try
		{
			Driver.startDriver();	
			List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids	
			new Assignment().createChapter(121, Integer.parseInt(tloids.get(0)));
			//create diag test
			new Assignment().createPracticeAtChapterLevel(121, "Adaptive Component Diagnostic", true, false, true, Integer.parseInt(tloids.get(0)));
			//create adaptive test
			new Assignment().createPracticeAtChapterLevel(121, "Adaptive Component Practice", true, false, true, Integer.parseInt(tloids.get(0)));
			new LoginUsingLTI().ltiLogin("121");
			new DiagnosticTest().openLastDiagnosticTest();
			
			new SelectAnswerAndSubmit().DiagTestWithCorrectAnswer(3);	
			new Navigator().orionDashboard();
			List<WebElement> allPractice=Driver.driver.findElements(By.cssSelector("span[title='Practice']"));			
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",allPractice.get(allPractice.size()-1));//click on latest created practice test 
			Thread.sleep(2000);
		  //skip the question	
		   Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")).click();
		   Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
		   Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")).click();
		   Thread.sleep(2000);
		   Driver.driver.findElement(By.cssSelector("img[title=\"Solution\"]")).click();
		   Thread.sleep(2000);
		   String solutionLabel = Driver.driver.findElement(By.className("al-diag-test-solution-content-title")).getText();
		   String solutionText = Driver.driver.findElement(By.className("diag-test-solution-raw-content")).getText();
		   if(!solutionLabel.equals("Solution") || solutionText.length()==0 )
		   		Assert.fail("On Clicking Solution button the solution is not displayed.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in verifySolutionButton in class VerifySolutionButton",e);
		}
	}
	
	
	
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
