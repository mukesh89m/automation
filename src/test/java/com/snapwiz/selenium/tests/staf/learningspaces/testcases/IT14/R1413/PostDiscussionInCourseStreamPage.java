package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R1413;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;

public class PostDiscussionInCourseStreamPage extends Driver{
	
	@Test
	public void postDiscussionInCourseStreamPage()
	{
		try 
		{
			String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "83");
			String shareName = ReadTestData.readDataByTagName("", "shareName", "83");
            String a[]=shareName.split(" ");
            shareName = a[1] + ", " + a[0];//reverse the name with comma in between
			String shareName1 = ReadTestData.readDataByTagName("", "shareName", "83_2");
            String y[]=shareName1.split(" ");
            shareName1 = y[1] + ", " + y[0];//reverse the name with comma in between
			new LoginUsingLTI().ltiLogin("83_1");		//login a instructor
			new LoginUsingLTI().ltiLogin("83_2");		//login a student
			new LoginUsingLTI().ltiLogin("83_3");		//login a mentor
			new LoginUsingLTI().ltiLogin("83");		//login a instructor
			new Navigator().navigateFromProfileDropDown("Settings");	//navigate to Settings page from profile dropdown
			driver.findElement(By.cssSelector("label[for='three']")).click(); //Select Option-3 in the settings page
			driver.findElement(By.id("settings-save")).click();	//click on Save button
			
			new LoginUsingLTI().ltiLogin("83_2");		//login a student
			new Navigator().NavigateTo("Course Stream");
			driver.findElement(By.linkText("Post")).click();
			Thread.sleep(2000);
			//TC row no. 83
			int defalutClass= driver.findElements(By.className("item-text")).size();
			if(defalutClass != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting option-3 from Settings page, in Course Stream of student side the default class section is present in share with box.");
			}
			driver.findElement(By.className("maininput")).click();
			Thread.sleep(2000);
			driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString);
			Thread.sleep(3000);
			
			List<WebElement> suggestname = driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]"));
			boolean studentFound = false;
			for(WebElement l: suggestname)
			{
				if(l.getText().contains(shareName1))
				{
					System.out.println(":found");
					studentFound = true;
					break;
				}
			}	
			//TC row no. 85
			if(studentFound == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting option-3 from Settings page, in share with field of Course Stream in student side displays the student names of current class section.");
			}	
			boolean found = false;
			for(WebElement l: suggestname)
			{
				if(l.getText().contains(shareName))
				{
					System.out.println("found");
					l.click();
					Thread.sleep(2000);
					found = true;
					break;
				}
			}
			//TC row no. 84
			if(found == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting option-3 from Settings page, in share with field of Course Stream in student side doesnt display the instructor names of current class section.");
			}
			String randomtext = new RandomString().randomstring(15);
			WebElement t=driver.findElement(By.id("iframe-user-text-input-div"));
			driver.switchTo().frame(t) ;
			driver.findElement(By.xpath("/html/body/font")).click();
			driver.findElement(By.xpath("/html/body")).sendKeys(randomtext);
			driver.switchTo().defaultContent();
			driver.findElement(By.id("post-submit-button")).click();
			Thread.sleep(2000);	
			
			new LoginUsingLTI().ltiLogin("83");		//login a instructor
			new Navigator().NavigateTo("Course Stream");
			//TC row no. 86
			String post = driver.findElement(By.className("ls-stream-post__action")).getText();
			if(!post.contains("posted a discussion"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting option-3 from Settings page, the discussion posted by student is not visible in instructor side Course Stream.");
			}
			//TC row no. 87
			int visualIndicator = driver.findElements(By.className("social-policy-configuration-text")).size();
			if(visualIndicator != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting option-3 from Settings page, in instructor side Course Stream the visual indicator is still shown.");
			}
			
			new LoginUsingLTI().ltiLogin("83_1");		//login as other instructor
			new Navigator().NavigateTo("Course Stream");
			//TC row no. 88
			int post1 = driver.findElements(By.className("ls-stream-post__action")).size();
			if(post1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting option-3 from Settings page, the discussion posted by student is visible to instructor to whom it was not shared.");
			}
			//TC row no. 89
			int visualIndicator1 = driver.findElements(By.className("social-policy-configuration-text")).size();
			if(visualIndicator1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting option-3 from Settings page, in instructor side Course Stream the visual indicator is still shown.");
			}
			
			
			new LoginUsingLTI().ltiLogin("83_3");		//login as mentor
			new Navigator().NavigateTo("Course Stream");
			//TC row no. 90
			int post2 = driver.findElements(By.className("ls-stream-post__action")).size();
			if(post2 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting option-3 from Settings page, the discussion posted by student is visible to mentor to whom it was not shared.");
			}
			//TC row no. 91
			int visualIndicator2 = driver.findElements(By.className("social-policy-configuration-text")).size();
			if(visualIndicator2 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting option-3 from Settings page, in mentor side Course Stream the visual indicator is still shown.");
			}
		} 
		catch (Exception e) 
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase postDiscussionInCourseStreamPage in class PostDiscussionInCourseStreamPage.",e);
		}
	}
}
