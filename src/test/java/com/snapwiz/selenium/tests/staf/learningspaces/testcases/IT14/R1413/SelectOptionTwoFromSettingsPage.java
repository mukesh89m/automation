package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R1413;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignmentSocialElement;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ResourseCreate;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;

public class SelectOptionTwoFromSettingsPage extends Driver{
	
	@Test(priority = 1, enabled = true)
	public void selectOptionTwoFromSettingsPage()
	{
		try
		{
			new DirectLogin().CMSLogin();
			new ResourseCreate().resourseCreate(14, 0);
			
			new LoginUsingLTI().ltiLogin("14_1");		//login a student
			new LoginUsingLTI().ltiLogin("14");		//login a instructor
			new Navigator().navigateFromProfileDropDown("Settings");	//navigate to Settings page from profile dropdown
			driver.findElement(By.cssSelector("label[for='two']")).click(); //Select Option-2 in the settings page
			driver.findElement(By.id("settings-save")).click();	//click on Save button
			
			new LoginUsingLTI().ltiLogin("14_1");		//login a student
			new Navigator().NavigateTo("Course Stream");  //navigate to Course Stream
			//TC row no. 14
			String socialPolicy = driver.findElement(By.className("social-policy-configuration-text")).getText();
			if(!socialPolicy.contains("Any discussions for this class section are not monitored by your instructor."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting the option 2 from Setting page by the instructor, in Course Stream of student side the required message is not shown.");
			}
			String randomtext = new RandomString().randomstring(15);
			new PostMessage().postmessage(randomtext);
			
			new LoginUsingLTI().ltiLogin("14");		//login a instructor
			new Navigator().NavigateTo("Course Stream");  //navigate to Course Stream
			//TC row no. 16
			String message = driver.findElement(By.className("ls-stream-post__action")).getText();
			if(!message.contains("posted a discussion"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting the option 2 from Setting page by the instructor, in Course Stream of instructor side the data posted by the instructor is not shown.");
			}

			new LoginUsingLTI().ltiLogin("14_1");		//login a student
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new TOCShow().tocHide();
			new Navigator().navigateToTab("Resources"); //navigate to Resources tab
			//TC row no. 17
			List<WebElement> icon = driver.findElements(By.cssSelector("div[class='social-policy-configuration']"));
			if(!icon.get(2).getAttribute("style").contains("instructor-monitored.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting the option 2 from Settings page by the instructor, in resource tab it doesnt show a visual indicator.");
			}
            //ignoring color related TCs
			/*new MouseHover();
			MouseHover.mouserhoverbywebelement(icon.get(2));
			//TC row no. 19
			List<WebElement> iconColor1 = driver.findElements(By.cssSelector("div[class='social-policy-configuration']"));
			if(!iconColor1.get(2).getCssValue("background-position").contains("23px"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On mouse hovering over the visual indicator the resource tab doesnt become blue.");
			}*/
			new Navigator().NavigateTo("Assignments");	//navigate to Assignments
			String icon1 = driver.findElement(By.cssSelector("div[class='social-policy-configuration']")).getCssValue("background-image");
			if(!icon1.contains("instructor-monitored.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting the option 2 from Settings page by the instructor, in Assignments page in student side the visual indicator is not shown.");
			}
            //ignoring color related TCs
			/*new MouseHover();
			MouseHover.mouserhover("social-policy-configuration");
			String iconColor = driver.findElement(By.cssSelector("div[class='social-policy-configuration']")).getCssValue("background-position");
			if(!iconColor.contains("23px"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting the option 2 from Settings page by the instructor, in Assignments page in student side the visual indicator color doesnt changes to blue on mouse hovering.");
			}*/
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase selectOptionTwoFromSettingsPage in class SelectOptionTwoFromSettingsPage.",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void selectOptionTwoFromSettingsPageAndCheckAssignmentsTab()
	{
		try
		{
			new Assignment().create(20);
			
			new LoginUsingLTI().ltiLogin("20_1");		//login a student
			new LoginUsingLTI().ltiLogin("20");		//login a instructor
			new Assignment().assignToStudent(20);	// assign assignment
			new Navigator().navigateFromProfileDropDown("Settings");	//navigate to Settings page from profile dropdown
			driver.findElement(By.cssSelector("label[for='two']")).click(); //Select Option-2 in the settings page
			driver.findElement(By.id("settings-save")).click();	//click on Save button
			
			new LoginUsingLTI().ltiLogin("20_1");		//login a student
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new TOCShow().tocHide();
			new Navigator().navigateToTab("Assignments"); //navigate to Assignments tab
			//TC row no. 20
			List<WebElement> icon = driver.findElements(By.cssSelector("div[class='social-policy-configuration']"));
			if(!icon.get(2).getAttribute("style").contains("instructor-monitored.png"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting the option 2 from Settings page by the instructor, in Assignments tab it doesnt show a visual indicator.");
			}
			new LoginUsingLTI().ltiLogin("20_2");		//login a student of another class section
			new Navigator().NavigateTo("Course Stream");  //navigate to Course Stream
			//TC row no. 21
			int socialPolicy = driver.findElements(By.className("social-policy-configuration-text")).size();
			if(socialPolicy != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("The visual indicator for social policy configuration is also shown for the students of other class section.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase selectOptionTwoFromSettingsPageAndCheckAssignmentsTab in class SelectOptionTwoFromSettingsPage.",e);
		}
	}
	@Test(priority = 3, enabled = true)
	public void selectOptionOneFromSettingsPage()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("11");		//login a instructor
			new LoginUsingLTI().ltiLogin("11_1");		//login a student
			new Navigator().NavigateTo("Course Stream");  //navigate to Course Stream
			String randomtext = new RandomString().randomstring(15);
			new PostMessage().postmessage(randomtext);
			//TC row no. 12
			int socialPolicy = driver.findElements(By.className("social-policy-configuration-text")).size();
			if(socialPolicy != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting the option 1 from Settings page by the instructor, in course stream of student side the visual indicator is shown.");
			}
			new Navigator().NavigateTo("eTextBook");	//navigate to eTextBook
			new TOCShow().tocHide();
			String comment = new RandomString().randomstring(15);
			new AssignmentSocialElement().addComment(comment);
			//TC row no. 13
			int icon = driver.findElements(By.cssSelector("div[class='social-policy-configuration']")).size();
			if(icon != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting the option 1 from Settings page by the instructor, on posting a comment the visual indicator icon is shown.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase selectOptionOneFromSettingsPage in class SelectOptionTwoFromSettingsPage.",e);
		}
	}
}
