package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;

public class AdaptiveQuestionDifficulty extends Driver
{
	/*o
	 * 1157-1158
	 */
	@Test
	public void adaptivequestiondifficulty()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("1157");
			new Navigator().NavigateTo("e-Textbook");
            new DiagnosticTest().startTest(4);
            new DiagnosticTest().attemptAllCorrect(0, false, false);
            new Navigator().NavigateTo("e-Textbook");
			new PracticeTest().startTest();
            boolean diffcultybar = driver.findElement(By.className("al-difficulty-level-status")).isDisplayed();
            System.out.println(diffcultybar);
            Assert.assertEquals(diffcultybar, true, "Difficulty bar is absent in practice questions.");

            String diffcultypercent = driver.findElement(By.className("al-diagtest-percentage-score")).getText();
            System.out.println("diffcultypercent: "+diffcultypercent);
            Assert.assertEquals(diffcultypercent, "100.0%", "Difficulty percentage is absent in practice questions.");

            String text = driver.findElement(By.className("al-diagtest-percentage-score-text")).getText();
            System.out.println("difficltylevel: "+text);
            Assert.assertEquals(text, "Students got it correct", "\"Students got it correct\" is absent in practice questions.");

			
		}
		catch(Exception e)
	    {
				  Assert.fail("Exception in LoginUsingLTI Application Helper",e);
		}
	}


}



