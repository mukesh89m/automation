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
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;

public class PostLinkInCourseStreamPage extends Driver{
	
	@Test
	public void postLinkInCourseStreamPage()
	{
		try 
		{
			String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "92_1");
			String shareName1 = ReadTestData.readDataByTagName("", "shareName", "92_2");
            String y[]=shareName1.split(" ");
            shareName1 = y[1] + ", " + y[0];//reverse the name with comma in between
            String shareName2 = ReadTestData.readDataByTagName("", "shareName", "92_1");
            String a[]=shareName2.split(" ");
            shareName2 = a[1] + ", " + a[0];//reverse the name with comma in between
			new LoginUsingLTI().ltiLogin("92_1");		//login a mentor
			new LoginUsingLTI().ltiLogin("92_2");		//login a student
			new LoginUsingLTI().ltiLogin("92_3");		//login a student
			new LoginUsingLTI().ltiLogin("92");		//login a instructor
			new Navigator().navigateFromProfileDropDown("Settings");	//navigate to Settings page from profile dropdown
			driver.findElement(By.cssSelector("label[for='three']")).click(); //Select Option-3 in the settings page
			driver.findElement(By.id("settings-save")).click();	//click on Save button
			
			new LoginUsingLTI().ltiLogin("92_2");		//login a student
			new Navigator().NavigateTo("Course Stream");
			driver.findElement(By.linkText("Link")).click();
			Thread.sleep(2000);
			//TC row no. 92
			int defaulttClass= driver.findElements(By.className("item-text")).size();
			if(defaulttClass != 0)
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
					studentFound = true;
					break;
				}
			}	
			//TC row no. 94
			if(studentFound == true)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting option-3 from Settings page, in share with field of Course Stream in student side displays the student names of current class section.");
			}	
			boolean found = false;
			for(WebElement l: suggestname)
			{
				if(l.getText().contains(shareName2))
				{
					found = true;
					break;
				}
			}
			//TC row no. 93
			if(found == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting option-3 from Settings page, in share with field of Course Stream in student side doesnt display the mentors names of current class section.");
			}
           for(WebElement l: suggestname)
			{
				if(l.getText().contains(shareName2))
				{
					l.click();
					Thread.sleep(2000);
					break;
				}
			}
			WebElement t=driver.findElement(By.id("iframe-user-link-input-div"));
			driver.switchTo().frame(t) ;
			driver.findElement(By.xpath("/html/body/font")).click();
			driver.findElement(By.xpath("/html/body")).sendKeys("www.yahoo.com");
		    driver.switchTo().defaultContent();
			Thread.sleep(15000);
            driver.findElement(By.id("post-submit-button")).click();//click on submit button
            Thread.sleep(2000);

			new LoginUsingLTI().ltiLogin("92_1");		//login a mentor
			new Navigator().NavigateTo("Course Stream");
			//TC row no. 95
			String post = driver.findElement(By.className("ls-stream-post__action")).getText();
			if(!post.contains("shared a link"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting option-3 from Settings page, the link posted by student is not visible in mentor side Course Stream.");
			}
			int visualIndicator = driver.findElements(By.className("social-policy-configuration-text")).size();
			if(visualIndicator != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting option-3 from Settings page, in instructor side Course Stream the visual indicator is still shown.");
			}
			
			new LoginUsingLTI().ltiLogin("92");		//login as instructor
			new Navigator().NavigateTo("Course Stream");
			//TC row no. 96
			int post1 = driver.findElements(By.className("ls-stream-post__action")).size();
			if(post1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting option-3 from Settings page, the link posted by student is visible to instructor to whom it was not shared.");
			}
			//TC row no. 97
			int visualIndicator1 = driver.findElements(By.className("social-policy-configuration-text")).size();
			if(visualIndicator1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting option-3 from Settings page, in instructor side Course Stream the visual indicator is still shown.");
			}
			
			
			new LoginUsingLTI().ltiLogin("92_3");		//login as student
			new Navigator().NavigateTo("Course Stream");
			//TC row no. 98
			int post2 = driver.findElements(By.className("ls-stream-post__action")).size();
			if(post2 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting option-3 from Settings page, the link posted by student is visible to students to whom it was not shared.");
			}
			//TC row no. 99
			int visualIndicator2 = driver.findElements(By.className("social-policy-configuration-text")).size();
			if(visualIndicator2 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting option-3 from Settings page, in students side Course Stream the visual indicator is still shown.");
			}
		} 
		catch (Exception e) 
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase postLinkInCourseStreamPage in class PostLinkInCourseStreamPage.",e);
		}
	}
}
