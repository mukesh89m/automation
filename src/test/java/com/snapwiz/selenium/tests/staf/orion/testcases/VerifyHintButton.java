package com.snapwiz.selenium.tests.staf.orion.testcases;

import java.util.List;

import org.openqa.selenium.By;
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

public class VerifyHintButton {
@Test(priority = 1, enabled = true)
	public void verifyHintButton()
	{
		try
		{
			Driver.startDriver();	
			List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids	
			new Assignment().createChapter(118, Integer.parseInt(tloids.get(0)));
			//create diag test
			new Assignment().createPracticeAtChapterLevel(118, "Adaptive Component Diagnostic", true, false, true, Integer.parseInt(tloids.get(0)));
			//create adaptive test
			new Assignment().createPracticeAtChapterLevel(118, "Adaptive Component Practice", false, true, true, Integer.parseInt(tloids.get(0)));
			new LoginUsingLTI().ltiLogin("118");
			
			new DiagnosticTest().openLastDiagnosticTest();
			
			new SelectAnswerAndSubmit().DiagTestWithCorrectAnswer(3);	
			new Navigator().orionDashboard();
			Thread.sleep(3000);
			
			new PracticeTest().startTLOLevelPracticeTest(0);//select practice test of TLO 1
			
		    Driver.driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();		//click hint button
		    Thread.sleep(2000);
		    String solutionLabel = Driver.driver.findElement(By.className("al-notification-message-header-text")).getText();
		    if(!solutionLabel.equals("Hint"))
		   		Assert.fail("On Clicking Hint button the solution is not displayed.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in verifyHintButton in class VerifyHintButton",e);
		}
	}
@Test(priority = 2, enabled = true)
public void verifyHintButtonForQuestionNotHavingHint()
	{
	 try
	  {
		Driver.startDriver();
		List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids	
		new Assignment().createChapter(124, Integer.parseInt(tloids.get(0)));
		//create diag test
		new Assignment().createPracticeAtChapterLevel(124, "Adaptive Component Diagnostic", true, false, true, Integer.parseInt(tloids.get(0)));
		//create adaptive test without giving hint
		new Assignment().createPracticeAtChapterLevel(124, "Adaptive Component Practice", false, false, true, Integer.parseInt(tloids.get(0)));
		new LoginUsingLTI().ltiLogin("124");
		
		new DiagnosticTest().openLastDiagnosticTest();
		
		new SelectAnswerAndSubmit().DiagTestWithCorrectAnswer(3);	
		new Navigator().orionDashboard();
		new PracticeTest().startTLOLevelPracticeTest(0);//select practice test of TLO 1
		int size = Driver.driver.findElements(By.cssSelector("img[title=\"Hint\"]")).size();
		if(size != 0)
			Assert.fail("The Hint button is present even if the question doesnt have any Hint provided by author.");
	  }
	catch(Exception e)
	 {
		Assert.fail("Exception in verifyHintButtonForQuestionNotHavingHint in class VerifyHintButton",e);
	 }
	
	}
@Test(priority = 3, enabled = true)
	public void verifyHintButtonForTwoQuestion()
	{
		try
		{
			Driver.startDriver();	
			List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids	
			String practiceassessmentname= ReadTestData.readDataByTagName("VerifyHintButton", "practiceassessmentname", "125");
			new Assignment().createChapter(125, Integer.parseInt(tloids.get(0)));
			//create diag test
			new Assignment().createPracticeAtChapterLevel(125, "Adaptive Component Diagnostic", true, false, true, Integer.parseInt(tloids.get(0)));
			//create adaptive test with
			new Assignment().createPracticeAtChapterLevel(125, "Adaptive Component Practice", false, true, true, Integer.parseInt(tloids.get(0)));
			new Assignment().addQuestions(125, "qtn-multiple-choice-img", practiceassessmentname, false, true, null, true, Integer.parseInt(tloids.get(0)));
			new LoginUsingLTI().ltiLogin("125");
			
			new DiagnosticTest().openLastDiagnosticTest();
			
			new SelectAnswerAndSubmit().DiagTestWithCorrectAnswer(3);	
			new Navigator().orionDashboard();
			new PracticeTest().startTLOLevelPracticeTest(0);//select practice test of TLO 1
			int size = Driver.driver.findElements(By.cssSelector("img[title=\"Hint\"]")).size();
			if(size == 0)
				Assert.fail("The Hint button is absent for first question.");
			
			Driver.driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();		//click hint button
		    Thread.sleep(2000);
		    String solutionLabel = Driver.driver.findElement(By.className("al-notification-message-header-text")).getText();
		    List<WebElement> hintText= Driver.driver.findElements(By.className("hint-text"));
		    if(!solutionLabel.equals("Hint") || hintText.get(1).getText().length() == 0)
		   		Assert.fail("On Clicking Hint button the solution is not displayed.");
			//answer the question
		    Driver.driver.findElement(By.className("qtn-label")).click();
		    Thread.sleep(2000);
		    Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")).click();		//click submit 
			Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();		//close notification
			Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")).click();		//click submit 
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")).click();		//click submit 
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();		//click hint button
		    Thread.sleep(2000);
		    String solutionLabel1 = Driver.driver.findElement(By.className("al-notification-message-header-text")).getText();
		    List<WebElement> hintText1= Driver.driver.findElements(By.className("hint-text"));
		    if(!solutionLabel1.equals("Hint") || hintText1.get(1).getText().length() == 0)
		   		Assert.fail("On Clicking Hint button the solution is not displayed.");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in verifyHintButtonForTwoQuestion in class VerifyHintButton",e);
		}
	}

@Test(priority = 4, enabled = true)
	public void verifyHintButtonForFirstQuestionWithHintSecondWithoutHint()
	{
		try
		{
			Driver.startDriver();	
			List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids	
			String practiceassessmentname= ReadTestData.readDataByTagName("VerifyHintButton", "practiceassessmentname", "129");
			new Assignment().createChapter(129, Integer.parseInt(tloids.get(0)));
			//create diagnostic test
			new Assignment().createPracticeAtChapterLevel(129, "Adaptive Component Diagnostic", true, false, true, Integer.parseInt(tloids.get(0)));
			//create adaptive test with
			new Assignment().createPracticeAtChapterLevel(129, "Adaptive Component Practice", false, true, true, Integer.parseInt(tloids.get(0)));
			new Assignment().addQuestions(129, "qtn-multiple-choice-img", practiceassessmentname, false, false,null, true, Integer.parseInt(tloids.get(0)));
			new LoginUsingLTI().ltiLogin("129");
			
			new DiagnosticTest().openLastDiagnosticTest();
			
			new SelectAnswerAndSubmit().DiagTestWithCorrectAnswer(3);	
			new Navigator().orionDashboard();
			new PracticeTest().startTLOLevelPracticeTest(0);//select practice test of TLO 1
			Driver.driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();		//click hint button
		    Thread.sleep(2000);
		    String hintLabel = Driver.driver.findElement(By.className("al-notification-message-header-text")).getText();
		    List<WebElement> hintText= Driver.driver.findElements(By.className("hint-text"));
		    if(!hintLabel.equals("Hint") || hintText.get(1).getText().length() == 0)
		   		Assert.fail("On Clicking Hint button the solution is not displayed.");
		    //answer the question
		    Driver.driver.findElement(By.className("qtn-label")).click();
		    Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")).click();		//click submit 
			Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();		//close notification
			Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")).click();		//click submit 
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")).click();		//click submit 
			Thread.sleep(2000);
			int hintSize = Driver.driver.findElements(By.cssSelector("img[title=\"Hint\"]")).size();
			if(hintSize != 1)
				Assert.fail("Hint button is present for 2nd question for which hint was not provided by author.");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in verifyHintButtonForFirstQuestionWithHintSecondWithoutHint in class VerifyHintButton",e);
		}
	}

@Test(priority = 5, enabled = true)
public void verifyHintButtonForFirstQuestionWithoutHintSecondWithHint()
{
	try
	{
		Driver.startDriver();
		List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids	
		String practiceassessmentname= ReadTestData.readDataByTagName("VerifyHintButton", "practiceassessmentname", "132");
		new Assignment().createChapter(132, Integer.parseInt(tloids.get(0)));
		//create diagnostic test
		new Assignment().createPracticeAtChapterLevel(132, "Adaptive Component Diagnostic", true, false, true, Integer.parseInt(tloids.get(0)));
		//create adaptive test with
		new Assignment().createPracticeAtChapterLevel(132, "Adaptive Component Practice", false, false, true, Integer.parseInt(tloids.get(0)));
		new Assignment().addQuestions(132, "qtn-multiple-choice-img", practiceassessmentname, false, true,null, true, Integer.parseInt(tloids.get(0)));
		new LoginUsingLTI().ltiLogin("132");
		
		new DiagnosticTest().openLastDiagnosticTest();
		
		new SelectAnswerAndSubmit().DiagTestWithCorrectAnswer(3);	
		new Navigator().orionDashboard();
		new PracticeTest().startTLOLevelPracticeTest(0);//select practice test of TLO 1
		
	    int hintSize = Driver.driver.findElements(By.cssSelector("img[title=\"Hint\"]")).size();
		if(hintSize != 0)
			Assert.fail("Hint button is present for 1st question for which hint was not provided by author.");
	    //answer the question
	    Driver.driver.findElement(By.className("qtn-label")).click();
	    Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")).click();		//click submit 
		Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();		//close notification
		Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")).click();		//click submit 
		Thread.sleep(2000);
		Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")).click();		//click submit 
		Thread.sleep(2000);
		Driver.driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();		//click hint button
	    Thread.sleep(2000);
		String hintLabel = Driver.driver.findElement(By.className("al-notification-message-header-text")).getText();
		List<WebElement> hintText= Driver.driver.findElements(By.className("hint-text"));
		if(!hintLabel.equals("Hint") || hintText.get(1).getText().length() == 0)
		   		Assert.fail("On Clicking Hint button the solution is not displayed.");
	}
	catch(Exception e)
	{
		Assert.fail("Exception in verifyHintButtonForFirstQuestionWithHintSecondWithoutHint in class VerifyHintButton",e);
	}
}
@Test(priority = 6, enabled = true)
public void verifyHintButtonForTwoQuestionWithoutHint()
{
	try
	{
		Driver.startDriver();	
		List<String> tloids = new PracticeTest().tloIds();		//Get TLO Ids	
		String practiceassessmentname= ReadTestData.readDataByTagName("VerifyHintButton", "practiceassessmentname", "135");
		new Assignment().createChapter(135, Integer.parseInt(tloids.get(0)));
		//create diagnostic test
		new Assignment().createPracticeAtChapterLevel(135, "Adaptive Component Diagnostic", true, false, true, Integer.parseInt(tloids.get(0)));
		//create adaptive test with
		new Assignment().createPracticeAtChapterLevel(135, "Adaptive Component Practice", false, false, true, Integer.parseInt(tloids.get(0)));
		new Assignment().addQuestions(135, "qtn-multiple-choice-img", practiceassessmentname, false, false,null, true, Integer.parseInt(tloids.get(0)));
		new LoginUsingLTI().ltiLogin("135");
		
		new DiagnosticTest().openLastDiagnosticTest();
		
		new SelectAnswerAndSubmit().DiagTestWithCorrectAnswer(3);	
		new Navigator().orionDashboard();
		new PracticeTest().startTLOLevelPracticeTest(0);//select practice test of TLO 1
		
	    int hintSize = Driver.driver.findElements(By.cssSelector("img[title=\"Hint\"]")).size();
		if(hintSize != 0)
			Assert.fail("Hint button is present for 1st question for which hint was not provided by author.");
	    //answer the question
	    Driver.driver.findElement(By.className("qtn-label")).click();
	    Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")).click();		//click submit 
		Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();		//close notification
		Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")).click();		//click submit 
		Thread.sleep(2000);
		Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")).click();		//click submit 
		Thread.sleep(2000);
		int hintSize1 = Driver.driver.findElements(By.cssSelector("img[title=\"Hint\"]")).size();
		if(hintSize1 != 0)
			Assert.fail("Hint button is present for 2nd question for which hint was not provided by author.");
	}
	catch(Exception e)
	{
		Assert.fail("Exception in verifyHintButtonForTwoQuestionWithoutHint in class VerifyHintButton",e);
	}
}

	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
}
