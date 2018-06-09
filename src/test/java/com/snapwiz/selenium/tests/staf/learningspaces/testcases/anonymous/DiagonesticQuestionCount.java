package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;

public class DiagonesticQuestionCount extends Driver
{
	/*
	 * 1105-1106
	 */
	@Test
	public void diagonsticquestioncount()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1105");
            new Navigator().NavigateTo("eTextbook");
			new DiagnosticTest().startTest(2);

            String chapterName = driver.findElement(By.className("al-diag-test-chapter-label")).getText();
            if(!chapterName.contains("Diagnostic - Ch 1:"))
                Assert.fail("Chapter name is absent after opening the diagnostic test.");

            String qNo = driver.findElement(By.className("al-diag-chapter-details")).getText();
            if(!qNo.contains("1 of 23"))
                Assert.fail("Present question number and total number of questions are not shown after opening the diagnostic test.");

            new SelectAnswerAndSubmit().daigonestianswersubmit("A");

            String qNo1 = driver.findElement(By.className("al-diag-chapter-details")).getText();
            if(!qNo1.contains("2 of 23"))
                Assert.fail("Present question number and total number of questions are not shown after submitting first question of diagnostic question.");

		}
		catch(Exception e)
	    {
				  Assert.fail("Exception in testcase diagonsticquestioncount in class DiagonesticQuestionCount.",e);
				  
		}
	}


}
