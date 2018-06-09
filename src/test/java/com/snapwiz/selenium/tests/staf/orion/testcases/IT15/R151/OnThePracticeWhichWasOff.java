package com.snapwiz.selenium.tests.staf.orion.testcases.IT15.R151;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectChapterForTest;

public class OnThePracticeWhichWasOff {
	@Test
	public void onThePracticeWhichWasOff()
	{
		try
		{
			Driver.startDriver();
			new LoginUsingLTI().ltiLogin("133");		//login as mentor
			new Navigator().navigateFromProfileDropDownForOrion("Settings");
			Driver.driver.findElement(By.cssSelector("div[class='al-customize-course-enabled customize-topic-enabled al-chkbox1']")).click();// click ON for "Customize Orion topics"
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("div.tloToggle.al-chkbox-holder > div.al-customize-course-disabled.al-chkbox")).click();//click on OFF for TLO
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("div.chapter-practice.al-chkbox-holder > div.al-customize-course-disabled.al-chkbox")).click();    //click OFF Chapter practice
            Thread.sleep(2000);
			
			new LoginUsingLTI().ltiLogin("133_2");		//login as student
			new SelectChapterForTest().selectchapterfortest(0, 4); //Start the Diagnostic test
            new DiagnosticTest().attemptAllCorrect(2, false, false);
            new Navigator().orionDashboard();
            //TC row no. 133
			String isPracticeDisabled = Driver.driver.findElement(By.cssSelector("span[title='Practice']")).getAttribute("allowpractice");
			if(!isPracticeDisabled.contains("false"))
			{
				new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After Switching OFF settings for chapter practice by mentor, the Practice button is not disabled in student side.");
			}
			
			new LoginUsingLTI().ltiLogin("133_1");		//login as instructor
			new Navigator().navigateFromProfileDropDownForOrion("Settings");
			Driver.driver.findElement(By.cssSelector("div[class='al-customize-course-enabled customize-topic-enabled al-chkbox1']")).click();// click ON for "Customize Orion topics"
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("div.chapter-practice.al-chkbox-holder > div.al-customize-course-enabled.al-chkbox1")).click();    //click OFF Chapter practice
            Thread.sleep(2000);
            
            new LoginUsingLTI().ltiLogin("133_2");        //login as student
            //TC row no. 134
			String isPracticeDisabled1 = Driver.driver.findElement(By.cssSelector("span[title='Practice']")).getAttribute("allowpractice");
			if(isPracticeDisabled1 != null)
			{
				new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After Switching OFF settings for chapter practice the Practice button is not disabled in student side.");
			}
			Driver.driver.findElement(By.cssSelector("span[title='Practice']")).click();//click on Practice button
			Thread.sleep(2000);
			//TC row no. 135
			String question = Driver.driver.findElement(By.className("al-diag-test-question-raw-content")).getText();
			if(question.length() == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After clicking ON the Practice Which Was OFF the Practice button doesnt get enabled in student side.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase onThePracticeWhichWasOff in class OnThePracticeWhichWasOff.", e);
		}
	}

	@AfterMethod
	public void tearDown() throws Exception
	{
		 Driver.driver.quit();
	}
}
