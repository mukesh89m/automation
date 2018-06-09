package com.snapwiz.selenium.tests.staf.orion.testcases.IT15.R151;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;

public class MentorOffDiagnosticAndTryToOnByInstructor {
	
	@Test
	public void mentorOffDiagnosticAndTryToOnByInstructor()
	{
		try
		{
			Driver.startDriver();
			 new LoginUsingLTI().ltiLogin("131");		//login as mentor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");
            Driver.driver.findElement(By.cssSelector("div[class='al-customize-course-enabled customize-topic-enabled al-chkbox1']")).click();// click ON for "Customize Orion topics"
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("div.tloToggle.al-chkbox-holder > div.al-customize-course-disabled.al-chkbox")).click();//click on OFF for TLO
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("div.diag-test.al-chkbox-holder > div.al-customize-course-disabled.al-chkbox")).click();    //click OFF Diagnostic Test for Chapter
            Thread.sleep(2000);
            Driver.driver.findElement(By.linkText("Yes")).click();	//click on YES in pop-up
            Thread.sleep(2000);
            
            new LoginUsingLTI().ltiLogin("131_1");		//login as another mentor of same class section
            new Navigator().navigateFromProfileDropDownForOrion("Settings");
            Driver.driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("div.diag-test.al-chkbox-holder > div.al-customize-course-enabled.al-chkbox1")).click();    //click ON Diagnostic Test for Chapter
            Thread.sleep(2000);
            //TC row no. 132
            int popup = Driver.driver.findElements(By.className("al-notification-message-body")).size();
            if(popup == 1)
            {
            	new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Instructor is allowed to ON the diagnostic once it is OFF by a mentor");
            }
            
            new LoginUsingLTI().ltiLogin("131_2");		//login as student
            //TC row no. 131
            int practice = Driver.driver.findElements(By.xpath("/*//*[starts-with(@class, 'al-link al-start-practice-button al-chapter-start-practice-button-')]")).size();
            if(practice == 0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Diagnostic test is not skipped with showing all the TLOs of the chapter, after mentor off the diagnostic.");
            }
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase mentorOffDiagnosticAndTryToOnByInstructor in class MentorOffDiagnosticAndTryToOnByInstructor.", e);
		}
	}
	@AfterMethod
    public void tearDown() throws Exception
    {
        Driver.driver.quit();
    }
}
