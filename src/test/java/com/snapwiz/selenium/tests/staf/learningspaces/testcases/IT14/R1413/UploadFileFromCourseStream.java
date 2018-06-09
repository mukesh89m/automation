package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT14.R1413;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;

public class UploadFileFromCourseStream extends Driver {
	@Test
	public void uploadFileFromCourseStream()
	{
		try 
		{
			String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "100_1");
			String shareName1 = ReadTestData.readDataByTagName("", "shareName", "100_2");
            String y[]=shareName1.split(" ");
            shareName1 = y[1] + ", " + y[0];//reverse the name with comma in between
			String shareName2 = ReadTestData.readDataByTagName("", "shareName", "100_1");
            String a[]=shareName2.split(" ");
            shareName2 = a[1] + ", " + a[0];//reverse the name with comma in between
			String filename =  ReadTestData.readDataByTagName("", "filename", "100");
			new LoginUsingLTI().ltiLogin("100_1");		//login a mentor
			new LoginUsingLTI().ltiLogin("100_2");		//login a student
			new LoginUsingLTI().ltiLogin("100_3");		//login a student
			new LoginUsingLTI().ltiLogin("100");		//login a instructor
            new SetSocialPolicy().setSocialPolicy("6", "three");//Select Option-3 in the settings page
			
			new LoginUsingLTI().ltiLogin("100_2");		//login a student
			new Navigator().NavigateTo("Course Stream");
			driver.findElement(By.linkText("File")).click();
			Thread.sleep(2000);
			//TC row no. 100
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
					studentFound = true;
					break;
				}
			}	
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
			//TC row no. 101
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
			((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='file-upload-button']")));
			 Thread.sleep(2000);
			 new KeysSend().sendKeyBoardKeys("$"+Config.fileUploadPath+filename+"^");
			 Thread.sleep(20000);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("post-submit-button")));
			Thread.sleep(2000);	

			new LoginUsingLTI().ltiLogin("100_1");		//login a mentor
			new Navigator().NavigateTo("Course Stream");
			//TC row no. 102
			String post = driver.findElement(By.className("ls-stream-post__action")).getText();
			if(!post.contains("shared a file"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting option-3 from Settings page, the file uploaded by student is not visible in mentor side Course Stream.");
			}
			int visualIndicator = driver.findElements(By.className("social-policy-configuration-text")).size();
			if(visualIndicator != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting option-3 from Settings page, in instructor side Course Stream the visual indicator is still shown.");
			}
			
			new LoginUsingLTI().ltiLogin("100");		//login as instructor
			new Navigator().NavigateTo("Course Stream");
			//TC row no. 103
			int post1 = driver.findElements(By.className("ls-stream-post__action")).size();
			if(post1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting option-3 from Settings page, the file uploaded by student is visible to instructor to whom it was not shared.");
			}
			int visualIndicator1 = driver.findElements(By.className("social-policy-configuration-text")).size();
			if(visualIndicator1 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting option-3 from Settings page, in instructor side Course Stream the visual indicator is still shown.");
			}
			
			
			new LoginUsingLTI().ltiLogin("100_3");		//login as student
			new Navigator().NavigateTo("Course Stream");
			//TC row no. 104
			int post2 = driver.findElements(By.className("ls-stream-post__action")).size();
			if(post2 != 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After selecting option-3 from Settings page, the file uploaded by student is visible to students to whom it was not shared.");
			}
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
			Assert.fail("Exception in testcase uploadFileFromCourseStream in class UploadFileFromCourseStream.",e);
		}
	}
}
