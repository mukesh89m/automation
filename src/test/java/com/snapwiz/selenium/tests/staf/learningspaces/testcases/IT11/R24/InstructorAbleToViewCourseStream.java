package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT11.R24;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.CommentOnPost;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class InstructorAbleToViewCourseStream extends Driver {
	
	@Test(priority = 1, enabled = true)
	public void instructorAbleToViewCourseStream()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("4");  	//login as instructor
			//TC row no. 4
			String dashboard = driver.getCurrentUrl();
			if(!dashboard.contains("learningSpaceInstructorDashboard"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("After login to LS instructor doesnt land to Dashboard.");
			}
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			//TC row no. 5 & 16
			String courseStream = driver.getCurrentUrl();
			if(!courseStream.contains("coursestream"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On clicking Course Stream from the navigator the instructor doesnt land on Course Stream.");
			}
			String message1 = new RandomString().randomstring(2);
			new PostMessage().postmessage(message1);
			String message2 = new RandomString().randomstring(2);
			new PostMessage().postmessage(message2);
			String message3 = new RandomString().randomstring(2);
			new PostMessage().postmessage(message3);
			//TC row no. 17
			String latestMessage = new TextFetch().textfetchbyclass("ls-link-span");
			if(!latestMessage.equals(message3))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course Stream the recently added posts doesnt appear at the top.");
			}
			String commenttext = new RandomString().randomstring(2);
			new CommentOnPost().commentOnPost(commenttext, 2);	//comment on message which was added first
			driver.navigate().refresh();
			Thread.sleep(3000);
			//TC row no. 18
			String latestCommentedPost = new TextFetch().textfetchbyclass("ls-link-span");
			if(!latestCommentedPost.equals(message1))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("In Course Stream the recently added posts doesnt appear at the topfor posting a post.");
			}	
			//TC row no. 21, 22
			driver.findElement(By.linkText("Post")).click();//click on post link
			Thread.sleep(2000);
			String ticon = driver.findElement(By.className("ls-shareImg")).getCssValue("background-image"); //checking T-icon
			if(!ticon.contains("t-icon.png"))
				Assert.fail("T-Icon not found Beside the share with option in course stream");
			
			driver.findElement(By.linkText("Link")).click();		//Clicking on Link Tab
			Thread.sleep(3000);
			String ticon1 = driver.findElement(By.className("ls-shareImg")).getCssValue("background-image"); //checking T-icon
			if(!ticon1.contains("t-icon.png"))
				Assert.fail("T-Icon not found Beside the share with option in course stream for posting a link.");
			
			driver.findElement(By.linkText("File")).click();		//Clicking on File Tab
			Thread.sleep(3000);			
			String ticon2 = driver.findElement(By.className("ls-shareImg")).getCssValue("background-image"); //checking T-icon
			if(!ticon2.contains("t-icon.png"))
				Assert.fail("T-Icon not found Beside the share with option in course stream for posting a File.");
			driver.findElement(By.linkText("Post")).click();//click on post link
			Thread.sleep(2000);
			//TC row no. 24, 25, 26
			driver.findElement(By.cssSelector("div[class='ls-shareImg']")).click();
			Thread.sleep(2000);
			boolean tclick = driver.findElement(By.id("html-editor-non-draggable")).isDisplayed();
			if(tclick ==true)
			{
				boolean value1=driver.findElement(By.id("undo")).isDisplayed();
				if(value1 == false)
				{
					new Screenshot().captureScreenshotFromTestCase();
					Assert.fail("undo option in rich text editor is not visible");
				}
				boolean value2=driver.findElement(By.id("redo")).isDisplayed();
				if(value2 == false)
				{
					new Screenshot().captureScreenshotFromTestCase();
					Assert.fail("redo option in rich text editor is not visible");
				}
				boolean value3=driver.findElement(By.id("createlink")).isDisplayed();
				if(value3 == false)
				{
					new Screenshot().captureScreenshotFromTestCase();
					Assert.fail("create link option in rich text editor is not visible");
				}
				boolean value4=driver.findElement(By.id("createequation")).isDisplayed();
				if(value4 == false)
				{
					new Screenshot().captureScreenshotFromTestCase();
					Assert.fail("create equation option in rich text editor is not visible");
				}
				boolean value5=driver.findElement(By.id("spanish")).isDisplayed();
				if(value5 == false)
				{
					new Screenshot().captureScreenshotFromTestCase();
					Assert.fail("spanish option in rich text editor is not visible");
				}
				boolean value6=driver.findElement(By.id("bold")).isDisplayed();
				if(value6 == false)
				{
					new Screenshot().captureScreenshotFromTestCase();
					Assert.fail("bold option in rich text editor is not visible");
				}
				boolean value7=driver.findElement(By.id("italic")).isDisplayed();
				if(value7 == false)
				{
					new Screenshot().captureScreenshotFromTestCase();
					Assert.fail("italic option in rich text editor is not visible");
				}
				boolean value8=driver.findElement(By.id("underline")).isDisplayed();
				if(value8 == false)
				{
					new Screenshot().captureScreenshotFromTestCase();
					Assert.fail("underline option in rich text editor is not visible");
				}
				boolean value9=driver.findElement(By.id("subscript")).isDisplayed();
				if(value9 == false)
				{
					new Screenshot().captureScreenshotFromTestCase();
					Assert.fail("subscript option in rich text editor is not visible");
				}
				boolean value10=driver.findElement(By.id("superscript")).isDisplayed();
				if(value10 == false)
				{
					new Screenshot().captureScreenshotFromTestCase();
					Assert.fail("superscript option in rich text editor is not visible");
				}
				boolean value11=driver.findElement(By.id("forecolor")).isDisplayed();
				if(value11 == false)
				{
					new Screenshot().captureScreenshotFromTestCase();
					Assert.fail("forecolor option in rich text editor is not visible");
				}
				boolean value12=driver.findElement(By.id("hilitecolor")).isDisplayed();
				if(value12 == false)
				{
					new Screenshot().captureScreenshotFromTestCase();
					Assert.fail("Highlight color option in rich text editor is not visible");
				}
				boolean value13=driver.findElement(By.id("insertorderedlist")).isDisplayed();
				if(value13 == false)
				{
					new Screenshot().captureScreenshotFromTestCase();
					Assert.fail("insert ordered list option in rich text editor is not visible");
				}
				boolean value14=driver.findElement(By.id("insertunorderedlist")).isDisplayed();
				if(value14 == false)
				{
					new Screenshot().captureScreenshotFromTestCase();
					Assert.fail("insert unordered list option in rich text editor is not visible");
				}
				boolean value15=driver.findElement(By.id("outdent")).isDisplayed();
				if(value15 == false)
				{
					new Screenshot().captureScreenshotFromTestCase();
					Assert.fail("outdent option in rich text editor is not visible");
				}
				boolean value16=driver.findElement(By.id("indent")).isDisplayed();
				if(value16 == false)
				{
					new Screenshot().captureScreenshotFromTestCase();
					Assert.fail("indent option in rich text editor is not visible");
				}
			}
			else
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Unable to click on T icon");
			}
			String colorhex1 = driver.findElement(By.cssSelector("div[class='ls-shareImg']")).getCssValue("background-color");
			String rgbhiglighted1 = "";
			if(colorhex1.length()>10)
            {
               String colorx = colorhex1.substring(5, 8);
               String colory = colorhex1.substring(10,13);
               String colorz = colorhex1.substring(15, 18);
            
               rgbhiglighted1 = String.format("#%02x%02x%02x", Integer.parseInt(colorx), Integer.parseInt(colory), Integer.parseInt(colorz));
            }
			//TC row no. 31
			if(!rgbhiglighted1.contains("#9f9f9f"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On click T-icon, the color of the rich text icon does not change.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase instructorAbleToViewCourseStream in class InstructorAbleToViewCourseStream.",e);
		}
	}

}
