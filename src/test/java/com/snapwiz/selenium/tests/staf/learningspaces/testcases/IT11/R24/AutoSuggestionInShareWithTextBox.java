package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT11.R24;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

public class AutoSuggestionInShareWithTextBox extends Driver {

	@Test(priority = 1, enabled = true)
	public void autoSuggestionInShareWithTextBox()
	{
		try
		{
			String defaultClassSection = ReadTestData.readDataByTagName("", "context_title", "36");
			String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "36_1");
			String shareName = ReadTestData.readDataByTagName("", "shareName", "36_1");
			new LoginUsingLTI().ltiLogin("36_1");  	//create instructor
			new LoginUsingLTI().ltiLogin("36");  	//login as instructor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			driver.findElement(By.linkText("Post")).click();
			Thread.sleep(2000);
			int postvalue=driver.findElements(By.linkText("Post")).size();
			if(postvalue!=1)		
				Assert.fail("POST tabs not present on header");
			int linkvalue=driver.findElements(By.linkText("Link")).size();
			if(linkvalue!=1)		
				Assert.fail("LINK tabs not present on header");
			int uploadvalue=driver.findElements(By.linkText("File")).size();
			if(uploadvalue!=1)		
				Assert.fail("UPLOAD tabs not present on header");
			//TC row no. 38
			int submitButton=driver.findElements(By.id("post-submit-button")).size();
			if(submitButton != 1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Submit button is absent near Share With option.");
			}
			int cancelButton=driver.findElements(By.id("post-submit-cancel-button")).size();
			if(cancelButton != 1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Cancel button is absent near Share With option.");
			}
			int textBox=driver.findElements(By.className("maininput")).size();
			if(textBox != 1)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Textbox is absent near Share With option.");
			}
			//TC row no. 39
			String shareWith = new TextFetch().textfetchbyclass("text--nowrap");
			if(!shareWith.contains("Share With"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Share with text is absent.");
			}
			//TC row no. 40
			if(!driver.findElement(By.className("item-text")).getText().trim().equals(defaultClassSection))
	  			Assert.fail("Class Section Name not found by default in Share TextBox");
			driver.findElement(By.className("closebutton")).click();
			Thread.sleep(2000);
			driver.findElement(By.className("maininput")).click();
			Thread.sleep(2000);
			driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString);
			Thread.sleep(5000);
			//TC row no. 41
			String autosuggestion = new TextFetch().textfetchbyid("share-with_feed");
			if(autosuggestion == null)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Auto-suggest option in Share with textbox in Course Stream is not working.");
			}
			String y[]=shareName.split(" ");
			shareName = y[1] + ", " + y[0];//reverse the name with comma in between

			boolean sharefound = false;
			List<WebElement> suggestname;
				 suggestname = driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]"));
			   for (WebElement answerchoice: suggestname)
			   {
				   if(answerchoice.getText().trim().equals(shareName))
			    {
			    	((JavascriptExecutor) driver).executeScript("arguments[0].click();", answerchoice);
			        answerchoice.click();	
			        sharefound = true;
			        break;
			    }
			   }
			//TC row no . 45
			if(sharefound == false)
					Assert.fail("No value selected from the Assign To field");
			driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString);
			Thread.sleep(5000);
			//TC row no . 46 and 43
			int noResult = driver.findElements(By.cssSelector("div[class='no-results-message']")).size();
			System.out.println("noResult"+noResult);
			if(noResult == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Already added student name is again in auto-suggestion box.");
			}


		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase autoSuggestionInShareWithTextBox in class AutoSuggestionInShareWithTextBox.",e);
		}
	}
	
	@Test(priority = 2, enabled = true)
	public void shareWithBoxEmpty()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("47");  	//login as instructor
			new Navigator().NavigateTo("Course Stream"); //navigate to Course Stream
			driver.findElement(By.linkText("Post")).click();
			Thread.sleep(2000);
			driver.findElement(By.className("closebutton")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("post-submit-button")).click();
			Thread.sleep(2000);
			String notice = "";
			notice = new TextFetch().textfetchbyclass("notification-message-wrapper");
			if(!notice.contains("You need to select at least one user or a group for your post."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("No notice is displayed when the Instructor has not entered any name in share with box for post message.");
			}
			driver.findElement(By.linkText("Link")).click();
			Thread.sleep(2000);
			driver.findElement(By.className("closebutton")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("post-submit-button")).click();
			Thread.sleep(2000);
		    notice = new TextFetch().textfetchbyclass("notification-message-wrapper");
			if(!notice.contains("You need to select at least one user or a group for your post."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("No notice is displayed when the Instructor has not entered any name in share with box for Link.");
			}
			driver.findElement(By.linkText("File")).click();
			Thread.sleep(2000);
			driver.findElement(By.className("closebutton")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("post-submit-button")).click();
			Thread.sleep(2000);
		    notice = new TextFetch().textfetchbyclass("notification-message-wrapper");
			if(!notice.contains("You need to select at least one user or a group for your post."))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("No notice is displayed when the Instructor has not entered any name in share with box for File.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in testcase shareWithBoxEmpty in class AutoSuggestionInShareWithTextBox.",e);
		}
	}
	
	@Test(priority = 3, enabled = true)
	public void autoSuggestStartsWithThreeAlphabets()
	{ 
		try
		{
			new LoginUsingLTI().ltiLogin("49");
			String defaultClasssection = ReadTestData.readDataByTagName("", "context_title", "49");
			String shareWithInitialStringSingle = ReadTestData.readDataByTagName("", "shareWithInitialStringSingle", "49");
			String shareWithInitialStringDouble = ReadTestData.readDataByTagName("", "shareWithInitialStringDouble", "49");
			String shareWithInitialStringTriple = ReadTestData.readDataByTagName("", "shareWithInitialStringTriple", "49");
			String shareWithEnterFullString = ReadTestData.readDataByTagName("", "shareWithEnterFullString", "49");
			new Navigator().NavigateTo("Course Stream");
			driver.findElement(By.linkText("Post")).click();
			driver.findElement(By.className("maininput")).click();
			Thread.sleep(2000);
			driver.findElement(By.className("closebutton")).click();
			Thread.sleep(5000);
			//Tc row no. 49
			String classsection = driver.findElement(By.className("holder")).getText();
		  	if(classsection.trim().equals(defaultClasssection))	  		
		  			Assert.fail("Default ClassSection is Present after removing it ");
            driver.findElement(By.className("holder")).click();
			String  defaultPrompt= driver.findElement(By.className("default")).getText();
            if(!defaultPrompt.trim().equals("(Enter Group or User Name)"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Default Prompt '(Enter Group or User Name)' OnClick at ShareWith box is absent");
			}
			driver.findElement(By.className("maininput")).sendKeys(shareWithInitialStringSingle);
			Thread.sleep(5000);
			//Tc row no. 51
		    String oneLetter = driver.findElement(By.className("error-message")).getText();
			if(!oneLetter.trim().equals("Enter at least 3 characters"))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Autosuggestion comes on entering one letter");
			}
			driver.findElement(By.className("maininput")).sendKeys(shareWithInitialStringDouble);
			Thread.sleep(5000);
			//Tc row no. 51
			String twoLetter = driver.findElement(By.className("error-message")).getText();
			if(!twoLetter.trim().equals("Enter at least 3 characters"))
	  		{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Autosuggestion comes on entering two letter");
	  		}
			driver.findElement(By.className("maininput")).sendKeys(shareWithInitialStringTriple);
			Thread.sleep(5000);
			//Tc row no. 50
			String moreLetter = driver.findElement(By.id("share-with_feed")).getText();
			if(moreLetter.equals("Enter at least 3 characters") || moreLetter.equals("(Enter Group or User Name)"))
	  		{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Autosuggestion doesnt not come on entering three letter");
	  		}
			driver.findElement(By.className("maininput")).clear();
			driver.findElement(By.className("maininput")).sendKeys(shareWithEnterFullString);
			Thread.sleep(7000);
			//Tc row no. 52 & 53
			String fullName = driver.findElement(By.id("share-with_feed")).getText();
			if(!fullName.trim().equals(shareWithEnterFullString.trim()))
	  		{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("On enetring full name Auto suggest doesnt display the name entered.");
	  		}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in TestCase autoSuggestStartsWithThreeAlphabets in class AutoSuggestionInShareWithTextBox", e);
		}		
	}
	@Test(priority = 4, enabled = true)
	public void verifySearchSpaceForAutoSuggestFunctionality()
	{ 
		try
		{
			String shareWithClassSection = ReadTestData.readDataByTagName("", "shareWithClassSection", "55");
			String classSection = ReadTestData.readDataByTagName("", "context_title", "55");
			String shareWithInstructor = ReadTestData.readDataByTagName("", "shareWithInstructor", "55_1");
			String instructorName = ReadTestData.readDataByTagName("", "instructorName", "55_1");
			String shareWithOtherClassSection = ReadTestData.readDataByTagName("", "context_title", "49");
			String shareWithInstructorOfOtherClassSection = ReadTestData.readDataByTagName("", "shareWithInitialString", "36_1");
			String shareWithMentor = ReadTestData.readDataByTagName("", "shareWithMentor", "55_2");
			new LoginUsingLTI().ltiLogin("55_1");
			new LoginUsingLTI().ltiLogin("55_2");
			new LoginUsingLTI().ltiLogin("55");
			new Navigator().NavigateTo("Course Stream");
			driver.findElement(By.linkText("Post")).click();
			driver.findElement(By.className("maininput")).click();
			Thread.sleep(2000);
			driver.findElement(By.className("closebutton")).click();
			Thread.sleep(5000);
			driver.findElement(By.className("maininput")).sendKeys(shareWithClassSection);
			Thread.sleep(5000);
			String autosuggestion = "";
			//TC row no. 57
			autosuggestion = new TextFetch().textfetchbyid("share-with_feed");
			if(!autosuggestion.equals(classSection))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Typing class section name in share with field the auto-suggest doesnt appear considering currect class section.");
			}
			driver.findElement(By.className("maininput")).clear();
			driver.findElement(By.className("maininput")).sendKeys(shareWithInstructor);
			Thread.sleep(5000);
			//TC row no. 58 & 59
			String y[]=instructorName.split(" ");
			instructorName = y[1] + ", " + y[0];//reverse the name with comma in between

			autosuggestion = new TextFetch().textfetchbyid("share-with_feed");
			if(!autosuggestion.equals(instructorName))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Typing class instructor name in share with field the auto-suggest doesnt appear considering currect class section.");
			}
			driver.findElement(By.className("maininput")).clear();
			driver.findElement(By.className("maininput")).sendKeys(shareWithOtherClassSection);
			Thread.sleep(5000);
			//TC row no. 60
			int noResult = driver.findElements(By.cssSelector("div[class='no-results-message']")).size();
			if(noResult == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Typing class section name other than current class section in share with field the auto-suggest appears.");
			}
			driver.findElement(By.className("maininput")).clear();
			driver.findElement(By.className("maininput")).sendKeys(shareWithInstructorOfOtherClassSection);
			Thread.sleep(5000);
			//TC row no. 61
			int noResult1 = driver.findElements(By.cssSelector("div[class='no-results-message']")).size();
			if(noResult1 == 0)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Typing instructor of other class section other than current class section in share with field the auto-suggest appears.");
			}
			String message = new RandomString().randomstring(5);
			new PostMessage().postMessageAndShare(message, shareWithInstructor, "studentnametag", "55_1","");
			//TC row no. 86
	  		String post = new TextFetch().textfetchbyclass("ls-link-span");
			if(!post.equals(message))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor unable to share post with a specific instructor.");
			}
			String message1 = new RandomString().randomstring(5);
			new PostMessage().postMessageAndShare(message1, shareWithMentor, "studentnametag", "55_2","");
			//TC row no. 86
	  		String post1 = new TextFetch().textfetchbyclass("ls-link-span");
			if(!post1.equals(message1))
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Instructor unable to share post with a specific mentor.");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			Assert.fail("Exception in TestCase verifySearchSpaceForAutoSuggestFunctionality in class AutoSuggestionInShareWithTextBox", e);
		}
	}

}
