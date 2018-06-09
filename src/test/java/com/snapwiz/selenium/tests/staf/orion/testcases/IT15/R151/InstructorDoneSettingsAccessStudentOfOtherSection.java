package com.snapwiz.selenium.tests.staf.orion.testcases.IT15.R151;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;

public class InstructorDoneSettingsAccessStudentOfOtherSection {

	@Test
	public void instructorDoneSettingsAccessStudentOfOtherSection()
	{
		try
		{
			Driver.startDriver();
            new LoginUsingLTI().ltiLogin("144");		//login as instructor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");    //go to "Settings" page
            Driver.driver.findElement(By.cssSelector("div[class='al-customize-course-enabled customize-topic-enabled al-chkbox1']")).click();// click ON for "Customize Orion topics"
            Thread.sleep(2000);
            Driver.driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("div.al-customize-course-disabled.al-chkbox")).click();    //click OFF button for chapter
            Thread.sleep(2000);
            
            new LoginUsingLTI().ltiLogin("144_1");		//login as student of other class section
            //TC row no. 144
            String chapter = Driver.driver.findElement(By.className("al-content-header-row")).getText();
            if(!chapter.contains("Ch 1:"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After clicking on OFF for first chapter the student of other class section cant see the first chapter.");
            }
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase instructorDoneSettingsAccessStudentOfOtherSection in class InstructorDoneSettingsAccessStudentOfOtherSection.", e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception
	{
		   Driver.driver.quit();
	}
}
