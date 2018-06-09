package com.snapwiz.selenium.tests.staf.orion.testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.AddNotesInQuestions;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectAnswerAndSubmit;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;

public class StudentAbleToAddNotesWhileGoingThroughQuestion
{
	@Test
	public void studentAbleToAddNotesWhileGoingThroughQuestion()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("706");//login as  student
			new SelectChapterForTest().selectchapterfortest(0, 3);
			new DiagnosticTest().attemptAllCorrect(2, false, false);
			new Navigator().orionDashboard();
			new PracticeTest().startTLOLevelPracticeTest(0);
			Driver.driver.findElement(By.cssSelector("img[title='Submit']")).click();//click on submit question
			int submitbutton=Driver.driver.findElements(By.cssSelector("img[title='Submit']")).size();
			if(submitbutton>=1)
				Driver.driver.findElement(By.cssSelector("img[title='Submit']")).click();
			Thread.sleep(2000);			
			new AddNotesInQuestions().addnotes("this is notes 1");//add notes and verify
			Driver.driver.findElement(By.cssSelector("img[title='Next']")).click();
			new SelectAnswerAndSubmit().practiceTestAttempt(2,12,"correct",false,false);
			Driver.driver.findElement(By.cssSelector("img[title='Submit']")).click();
			Thread.sleep(2000);			
			new AddNotesInQuestions().addnotes("this is notes 2");//add notes and verify
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromAppHelper();
			Assert.fail("Exception in testcase studentAbleToAddNotesWhileGoingThroughQuestion in class StudentAbleToAddNotesWhileGoingThroughQuestion",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}
	
}
