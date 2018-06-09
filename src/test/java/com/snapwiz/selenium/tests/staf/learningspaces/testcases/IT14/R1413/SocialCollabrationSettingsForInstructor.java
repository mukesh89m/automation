package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R1413;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;

public class SocialCollabrationSettingsForInstructor extends Driver{

	@Test
	public void socialCollabrationSettingsForInstructor()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("2");		//login a instructor
			driver.findElement(By.className("ls-user-nav__username")).click();	//click on profile dropdown
			Thread.sleep(2000);
			driver.findElement(By.linkText("Settings")).click();//click on setting
			Thread.sleep(2000);
			//TC row no. 2
			String title = driver.findElement(By.className("settings-bg-img")).getText();
			if(!title.contains("Settings"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In setting page the title is not \"Icon Settings\" .");
			}
			//TC row no. 3
			String heading = driver.findElement(By.className("settings-content-heading")).getText();
			if(!heading.contains("Social Policy"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In setting page, Below the title it doesnt show text as \"Social policy\".");
			}
			//TC row no. 4
			String threeOptions = driver.findElement(By.className("settings-policy-modes-wrapper")).getText();
			if(!threeOptions.contains("All On"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In setting page, \"All On\" radio button is absent.");
			}
			if(!threeOptions.contains("“Student to Student” level communication only"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In setting page, \"All On\" radio button is absent.");
			}
			if(!threeOptions.contains("“Student to Student” level communication only"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In setting page, \"Student to Student level communication only\" radio button is absent.");
			}
			if(!threeOptions.contains("“Student to Instructor“ level communication only"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In setting page, \"Student to Instructor level communication only\" radio button is absent.");
			}
			//TC row no. 5
			List<WebElement> helpIcon = driver.findElements(By.className("settings-help-icon"));
			System.out.println("help icon size "+helpIcon.size());
			if(helpIcon.size() != 5)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In setting page, at the end of each option it doesnt show \"?\" mark indicating \"Help context\".");
			}
			helpIcon.get(0).click(); // Click on "?" for option-1
			Thread.sleep(2000);
			//TC row no. 7
			String help = driver.findElement(By.className("help-data-container")).getText();
			if(!help.contains("Students can post to anyone. There is no restriction on posts."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In setting page, Clicking on \"?\" for option-1 it didnt show pop_up with message \"Students can post to anyone.There is no restriction on posts .\".");
			}
			helpIcon.get(1).click(); // Click on "?" for option-2
			Thread.sleep(2000);
			//TC row no. 8
			String help1 = driver.findElement(By.className("help-data-container")).getText();
			if(!help1.contains("The instructor is not monitoring the social activities."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In setting page, Clicking on \"?\" for option-2 it didnt show pop_up with message \"The instructor is not monitoring the social activities.\".");
			}
			helpIcon.get(2).click(); // Click on "?" for option-3
			Thread.sleep(2000);
			//TC row no. 9
			String help2 = driver.findElement(By.className("help-data-container")).getText();
			if(!help2.contains("A student can communicate with ONLY instructors of the class section."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In setting page, Clicking on \"?\" for option-3 it didnt show pop_up with message \"A student can communicate with ONLY instructors of the class section.\".");
			}

			new Click().clickBycssselector("span[class='settings-help-icon audio-settings']");
			String helpaudio = driver.findElement(By.className("help-data-container")).getText();
			if(!helpaudio.contains("Turn audio recording on or off. Students will be able to record audio in course stream and discussion questions if the setting is turned on."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In setting page, Clicking on \"?\" for help audio it didnt show pop_up with message \"Turn audio recording on or off. Students will be able to record audio in course stream and discussion questions if the setting is turned on.\".");
			}

			new Click().clickBycssselector("span[class='settings-help-icon customize-settings']");
			String helpstudyplan = driver.findElement(By.className("help-data-container")).getText();
			if(!helpstudyplan.contains("Turn customize study plan settings on or off. Disabled section or chapter will not be shown to all the students of the current class-section."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In setting page, Clicking on \"?\" for customize study plan didnt show pop_up with message \"Turn customize study plan settings on or off. Disabled section or chapter will not be shown to all the students of the current class-section.\".");
			}

		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase socialCollabrationSettingsForInstructor in class SocialCollabrationSettingsForInstructor.",e);
		}
	}
}
