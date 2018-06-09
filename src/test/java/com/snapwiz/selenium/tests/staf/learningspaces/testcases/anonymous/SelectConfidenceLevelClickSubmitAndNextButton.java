package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class SelectConfidenceLevelClickSubmitAndNextButton extends Driver{
	@Test
	public void selectConfidenceLevelClickSubmitAndNextButton()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("2539");
			Thread.sleep(3000);
			new Navigator().NavigateTo("eTextbook");
			new DiagnosticTest().startTest(2);
			driver.findElement(By.className("qtn-label")).click();
			Thread.sleep(3000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("confidence-level-somewhat")));  //select confidence  level
			Thread.sleep(3000);
			//click submit a notifiaction will come
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("al-diag-test-submit-button")));
			
			String question = driver.findElement(By.className("al-diag-chapter-details")).getText();
			if(!question.contains("(2 of"))
				Assert.fail("After selecting the confidence level and then submtting the next question doesnot come.");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in selectConfidenceLevelClickSubmitAndNextButton in class SelectConfidenceLevelClickSubmitAndNextButton",e);
		}
	}

}
