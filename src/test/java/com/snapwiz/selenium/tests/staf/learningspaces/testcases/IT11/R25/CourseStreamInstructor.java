package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT11.R25;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.FileUpload;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessageValidate;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;

public class CourseStreamInstructor extends Driver
{
	@Test(priority = 1, enabled=true)
	public void courseStreamValidElements()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("2");
			
			new Navigator().NavigateTo("Course Stream");														//Navigating to CS, 4			
			String randomtext=new RandomString().randomstring(5);												// Here it posts a random message
			new PostMessage().postmessage(randomtext);															// Line 6
			boolean poststatus = new PostMessageValidate().postMessageValidate(randomtext);                     // Validates the message 
			if(poststatus == false)			
				Assert.fail("Post NOT posted in course stream successfully");
																												// Line 8
			driver.findElement(By.linkText("Post")).click();
			driver.findElement(By.linkText("Link")).click();
			driver.findElement(By.linkText("File")).click();
			driver.findElement(By.id("post-submit-cancel-button")).click();
			driver.findElement(By.linkText("Post")).click();
			WebElement t=driver.findElement(By.id("iframe-user-text-input-div"));
			driver.switchTo().frame(t) ;
			String defaultTextInPost = driver.findElement(By.xpath("/html/body/font")).getText();
			driver.switchTo().defaultContent();
			String texttoverify1 =  ReadTestData.readDataByTagName("", "texttoverify1", "2");			
			if(!defaultTextInPost.equals(texttoverify1))			
				Assert.fail("Text box  say by default:Share your knowledge or seek answers not shown");
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase courseStreamValidElements in class CourseStreamInstructor",e);
		}
	}

	@Test(priority = 2, enabled= true)
	public void courseStreamClickOnRightPane()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("9");
			new Navigator().NavigateTo("Course Stream");			    									 // Line 9,11
			driver.findElement(By.linkText("Post")).click();	     									// Line 10,13 Validate all elements in rightpane.
			driver.findElement(By.linkText("Link")).click();
			driver.findElement(By.linkText("File")).click();

			driver.findElement(By.cssSelector("div[class='ls-shareImg']")).click();                   // Line 13 To validate T icon,click on T icon.

			String text = driver.findElement(By.cssSelector("a[title='Cancel']")).getText();
			System.out.println("Output"+ text);
			if(!text.contains("Cancel"))
				Assert.fail("Cancel button is not found");

			String text2 = driver.findElement(By.className("item-text")).getText();          		  // Validate class_section name.
			System.out.println("Output"+ text2);
			if(!text2.contains("studtitle"))
				Assert.fail("ClassSection name is not found");

			driver.findElement(By.id("post-submit-button")).click();

			driver.findElement(By.linkText("Post")).click();	                                				  // Line 16
			WebElement t1=driver.findElement(By.id("iframe-user-text-input-div"));
			driver.switchTo().frame(t1) ;
			String defaultTextInPost1 = driver.findElement(By.xpath("/html/body/font")).getText();
			driver.switchTo().defaultContent();
			String texttoverify1 =  ReadTestData.readDataByTagName("", "texttoverify1", "9");			
			if(!defaultTextInPost1.equals(texttoverify1))			
				Assert.fail("Text box  say by default:Share your knowledge or seek answers not shown");
			
			String defaulttextpresent = ReadTestData.readDataByTagName("", "defaulttextpresent", "9");                        // Line 17
			new FileUpload().fileUpload("9", false);
			driver.findElement(By.id("post-submit-cancel-button")).click();
			driver.findElement(By.linkText("Link")).click();	                                				  // Line 16
			WebElement we = driver.findElement(By.id("iframe-user-link-input-div"));
			driver.switchTo().frame(we);
			Thread.sleep(3000);
			String defaulttext = driver.findElement(By.xpath("html/body/font")).getText();
            System.out.println("defaulttext: "+defaulttext);
           	driver.switchTo().defaultContent();
			if(!defaulttext.equals(defaulttextpresent))
					Assert.fail("Default Text 'Provide a description for this file' is NOT Present  within the text-box");

			driver.findElement(By.linkText("File")).click();
			
			String text1 = driver.findElement(By.className("stream-head__upload-label")).getText();           // Line 18
			System.out.println("Output"+ text1);
			if(!text1.contains("UPLOAD FILES"))                       
				Assert.fail("Upload files - TEXT not found");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase courseStreamClickOnRightPane in class CourseStreamInstructor",e);
		}
	}
	
	@Test(priority = 3, enabled = true)
	public void courseStreamVerifyAllRightPaneElements()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("19");
			new Navigator().NavigateTo("Course Stream");			     
			String randomtext=new RandomString().randomstring(5);													// Here it posts a random message
			new PostMessage().postmessage(randomtext);															   // Line 21
			boolean poststatus = new PostMessageValidate().postMessageValidate(randomtext);                       // Validates the message  - To work Post TAB.
			if(poststatus == false)			
				Assert.fail("Post NOT posted in course stream successfully"); 
			
			new PostMessage().postMessageWithoutSubmit("19");                       						 // Line 22

			new PostMessage().postLinkWithoutSubmit("www.wikipedia.com");                                	    // To work Link TAB. - Line  23
			boolean thumbnail=driver.findElement(By.id("scrapper")).isDisplayed();
			if(!thumbnail)
		  		Assert.fail("Thumbnail is NOT present after typing the URL");
		  	driver.findElement(By.id("post-submit-button")).click();

		  	new PostMessage().postLinkWithoutSubmit("Dummy Text");                      						   // Line 24

			new FileUpload().fileUploadAndValidate("19");														 // Line 26   

			driver.findElement(By.linkText("Post")).click();                                 		    // Line 28
			driver.findElement(By.cssSelector("div[class='ls-shareImg']")).click();
			boolean tclick = driver.findElement(By.id("html-editor-non-draggable")).isDisplayed();
			if(tclick ==true)
			{
				boolean value1=driver.findElement(By.id("undo")).isDisplayed();

				boolean value2=driver.findElement(By.id("redo")).isDisplayed();

				boolean value3=driver.findElement(By.id("createlink")).isDisplayed();

				boolean value4=driver.findElement(By.id("createequation")).isDisplayed();

				boolean value5=driver.findElement(By.id("languages")).isDisplayed();

				boolean value6=driver.findElement(By.id("bold")).isDisplayed();
				
				boolean value7=driver.findElement(By.id("italic")).isDisplayed();
				
				boolean value8=driver.findElement(By.id("underline")).isDisplayed();

				boolean value9=driver.findElement(By.id("superscript")).isDisplayed();
				
				boolean value10=driver.findElement(By.id("subscript")).isDisplayed();
				
				boolean value11=driver.findElement(By.id("hilitecolor")).isDisplayed();
				
				boolean value12=driver.findElement(By.id("hilitecolor")).isDisplayed();
				
				boolean value13=driver.findElement(By.id("insertorderedlist")).isDisplayed();
				
				boolean value14=driver.findElement(By.id("insertunorderedlist")).isDisplayed();

				boolean value15=driver.findElement(By.id("outdent")).isDisplayed();
				
				boolean value16=driver.findElement(By.id("indent")).isDisplayed();

				if(value1==true && value2==true && value3==true && value4==true && value5==true && value6==true && value7==true && value8== true && value9==true && value10==true && value11==true && value12==true && value13==true && value14==true && value15==true && value16==true )
				{
					System.out.println("All is Well" +tclick);
				}
				else
				{
					new Screenshot().captureScreenshotFromTestCase();
					Assert.fail("All the options in rich text editor are NOT visible");
				}
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in testcase courseStreamVerifyAllRightPaneElements in class CourseStreamInstructor",e);
		}
	}
	
	@Test(priority = 4, enabled= true)
	public void courseStreamAndShare()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("30");
			new Navigator().NavigateTo("Course Stream");          											 // Line 30
		
			driver.findElement(By.linkText("Post")).click();	       									 // Line 31
			driver.findElement(By.id("post-submit-button")).click();

			driver.findElement(By.linkText("Link")).click();        									 // Line 32
			driver.findElement(By.id("post-submit-button")).click();

				
			driver.findElement(By.linkText("File")).click();  										// Line 33
			driver.findElement(By.id("post-submit-button")).click();

			new LoginUsingLTI().ltiLogin("30");                                  	   					   //Line 35
			new Navigator().NavigateTo("Course Stream");    	
					
			driver.findElement(By.linkText("Post")).click();	          							  // Line 36

			String submit = driver.findElement(By.id("post-submit-button")).getText();
			System.out.println("submit text found" +submit);
					
			driver.findElement(By.xpath("/html/body")).click();      							   // Line 37

			new LoginUsingLTI().ltiLogin("32");                                    					   //Login to student

			new LoginUsingLTI().ltiLogin("30");
			new Navigator().NavigateTo("Course Stream");			                         			    //Line 38 and 39    	
						
			driver.findElement(By.linkText("Post")).click();

			String randomtext=new RandomString().randomstring(5);         						 // Posts 5 times random messages
			for(int i =0; i< 5; i++)
			new PostMessage().postmessage(randomtext);			
			boolean poststatus = new PostMessageValidate().postMessageValidate(randomtext);                      
			if(poststatus == false)			
				Assert.fail("Post NOT posted in course stream successfully");
						
			JavascriptExecutor jsx = (JavascriptExecutor)driver;
			jsx.executeScript("window.scrollBy(0,800)");

			new PostMessage().postMessageAndShare(randomtext, "805second, 805first", "shareName", "30", "");         // Line 41

			new LoginUsingLTI().ltiLogin("32");
			new Navigator().NavigateTo("Course Stream");

			String randomtext2 =driver.findElement(By.className("ls-link-span")).getText();     // To verify in student that same message posted by instructor is Displayed in Student's course stream.
			 System.out.println(randomtext2);
			 if(!randomtext.equals(randomtext2))
				Assert.fail("Message not found-1");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in Testcase courseStreamAndShare in class CourseStreamInstructor ",e);
		}
	}

}