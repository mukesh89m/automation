package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R1413;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

//testcase is created as per bug 8280

public class SelectOptionThreeFromSettingsPage extends Driver{
	
	@Test(priority = 1, enabled = true)
	public void selectOptionTwoFromSettingsPage()
	{
		try
		{
            new LoginUsingLTI().ltiLogin("111");		//login a instructor
            new SetSocialPolicy().setSocialPolicy("", "three");

            new LoginUsingLTI().ltiLogin("111_1");		//login a student
            new Navigator().NavigateTo("e-Textbook");   //go to etextbook
            new TOCShow().tocHide();

            String discussionButton = driver.findElement(By.cssSelector("a[class='ls-toc-btn ls-right-new-btn']")).getText();
            if(discussionButton.contains("+ New Discussion"))
                Assert.fail("Discussion button for posting discussion still appears on eTextbook, where as instructor has set the social policy to \"Student to Instructor\".");

            String disabledMessage = driver.findElement(By.className("no-post-message")).getText();
            if(!disabledMessage.contains("Discussions in this class has been disabled by the instructor."))
                Assert.fail("The student doesnt see the message \"Discussions in this class has been disabled by the instructor.\"");

		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase selectOptionOneFromSettingsPage in class SelectOptionTwoFromSettingsPage.",e);
		}
	}
}
