package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.Driver;

public class NewButtonActions {

	public void verifyNewButton()
	{
		try
		{
			Boolean addnotepresent = false,adddisscussionpresent = false;
			Driver.driver.findElement(By.linkText("+ New")).click();
			List<WebElement> newbuttonoptions = Driver.driver.findElements(By.className("ptext"));
			for (WebElement newbtnoptions : newbuttonoptions)
			{
				if(newbtnoptions.getText().equals("Add Note"))
					addnotepresent = true;
				if(newbtnoptions.getText().equals("Add Discussion"))
					adddisscussionpresent = true;
			}
			if(addnotepresent==false)
				Assert.fail("Add Note Option not present after clicking on + New Button in eTextBook");
			if(adddisscussionpresent == false)
				Assert.fail("Add Discussion Option not present after clicking on + New Button in eTextBook");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper verifyNewButton in class NewButtonActions",e);
		}
	}
	
	public void verifyAddDiscussionBox()
	{
		try
		{		
		Driver.driver.findElement(By.linkText("+ New")).click();
		List<WebElement> newbuttonoptions = Driver.driver.findElements(By.className("ptext"));
		for (WebElement newbtnoptions : newbuttonoptions)
		{			
			if(newbtnoptions.getText().equals("Add Discussion"))
			{
				newbtnoptions.click();
				break;
			}
		}	
	
		//Verifying if add discussion box is opened or not
		int cnt;
		if(Config.browser.equals("chrome"))
			 cnt = Driver.driver.findElements(By.cssSelector("div[class='pagecontext pagecontext350 right-side-pagecontext']")).size();
		else	
			 cnt = Driver.driver.findElements(By.cssSelector("div[class='pagecontext pagecontext360 right-side-pagecontext']")).size();	
			if(cnt == 0)
				Assert.fail("Discussion box not opened even after selecting Add Discussion from +Add options in eTextBook");
			
		WebElement submitbutton = Driver.driver.findElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']"));
		WebElement cancelbutton = Driver.driver.findElement(By.cssSelector("button[class='editdiscussion-button pagecontext-cancel']"));
		if(submitbutton.isDisplayed() == false)
			Assert.fail("Submit Button Not Present in Add Discussion Box opened after clicking Add Discussion option from +New Button in eTextBook");
		if(cancelbutton.isDisplayed() == false)
			Assert.fail("Cancel Button Not Present in Add Discussion Box opened after clicking Add Discussion option from +New Button in eTextBook");
		
		
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper verifyAddDiscussionBox in class NewButtonActions",e);
		}
	}
	public void cancelButtonPressAddDiscussionBox()
	{
		try
		{	
			String adddissstring = new RandomString().randomstring(5);
			Driver.driver.findElement(By.className("editdiscussion-text")).sendKeys(adddissstring);
			WebElement cancelbutton = Driver.driver.findElement(By.cssSelector("button[class='editdiscussion-button pagecontext-cancel']"));			
			cancelbutton.click();			
			Thread.sleep(2000);
			int cnt;
			if(Config.browser.equals("chrome"))
				 cnt = Driver.driver.findElements(By.cssSelector("div[class='pagecontext pagecontext350 right-side-pagecontext']")).size();
			else	
				 cnt = Driver.driver.findElements(By.cssSelector("div[class='pagecontext pagecontext360 right-side-pagecontext']")).size();	
			if(cnt != 0)
					Assert.fail("Discussion box is opened even after clicking the cancel button.");
			WebElement nopost = Driver.driver.findElement(By.className("stream-content-posts"));
			if(!nopost.getText().equals("Get help from teacher and peers - ask question and start a discussion OR save a personal note."))
				Assert.fail("The discussion got posted even after pressing cancel button of the Add Discussion Box opened after clicking Add Discussion option from +New Button in eTextBook");
			  
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper cancelButtonPressAddDiscussionBox in class NewButtonActions",e);
		}
		
	}
	
	public void addDiscussion()
	{
		try
		{	
			String adddissstring = new RandomString().randomstring(5);
			Driver.driver.findElement(By.className("editdiscussion-text")).sendKeys(adddissstring);
			WebElement submitbutton = Driver.driver.findElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']"));			
			submitbutton.click();			
			Thread.sleep(2000);
			int cnt = Driver.driver.findElements(By.cssSelector("div[class='pagecontext pagecontext360 right-side-pagecontext']")).size();	
			if(cnt != 0)
					Assert.fail("Discussion box is opened even after clicking the submit button.");
			
			//Validating right side panel	
			
			WebElement profilepic =  Driver.driver.findElement(By.className("ls-right-user-img"));
			String imgtag = (String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",profilepic);
			
			if(!imgtag.contains("src=\"/webresources/images/ls/user-default-thumbnail.png\""))
				Assert.fail("Profile Pic not preset in the add discussion posted shown at the right side of the screen under Stream tab in eTextBook");
			
			WebElement note = Driver.driver.findElement(By.className("ls-right-user-post-body"));
			if(!adddissstring.equals(note.getText().trim()))
			Assert.fail("Discussion added by user not found on the right side panel of the screen under Stream tab in eTextBook");  //Validating Discussion Text
			
			
			WebElement noteelement = Driver.driver.findElement(By.className("ls-right-user-head"));
			String postedanote = (String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",noteelement);
		    
			if(!postedanote.contains(" posted a discussion"))
			Assert.fail("Text 'Posted a Discussion' not found on right side panel inside the note block after posting a Discussion"); //Validating posted a discussion text
			
			String username = Driver.driver.findElement(By.cssSelector("a[class='ls-right-user-name ellipsis']")).getText();
			System.out.println(username);
			String user = Config.givenname+" "+Config.familyname;
			if(!username.equals(user))
				Assert.fail("Username invalid or blank on right side panel inside the discussion block after posting a Discussion"); //Validating user name
			
			Boolean staricon = Driver.driver.findElement(By.cssSelector("i[class='ls-right-section-sprites ls--right-star-icon']")).isDisplayed();
			if(staricon == false)
				Assert.fail("Star Icon not displayed on right side panel inside the discussion block after posting a Discussion");
						
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper addDiscussion in class NewButtonActions",e);
		}
		
	}
	
	public void verifyAddNoteBox()
	{
		try
		{		
		Driver.driver.findElement(By.linkText("+ New")).click();
		List<WebElement> newbuttonoptions = Driver.driver.findElements(By.className("ptext"));
		for (WebElement newbtnoptions : newbuttonoptions)
		{			
			if(newbtnoptions.getText().equals("Add Note"))
			{
				newbtnoptions.click();
				break;
			}
		}		
		//Verifying if add note box is opened or not
			int cnt = Driver.driver.findElements(By.cssSelector("div[class='pagecontext right-side-pagecontext right-side-pc-handle-scroll']")).size();	
			if(cnt == 0)
				Assert.fail("Note box not opened even after selecting Add Note from +Add options in eTextBook");
			
		WebElement submitbutton = Driver.driver.findElement(By.cssSelector("button[class='editnote-button editnote-submit']"));		
		WebElement cancelbutton = Driver.driver.findElement(By.cssSelector("button[class='editnote-button pagecontext-cancel']"));
	
		if(submitbutton.isDisplayed() == false)
			Assert.fail("Submit Button Not Present in Add Discussion Box opened after clicking Add Discussion option from +New Button in eTextBook");
		if(cancelbutton.isDisplayed() == false)
			Assert.fail("Cancel Button Not Present in Add Discussion Box opened after clicking Add Discussion option from +New Button in eTextBook");
		
		
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper verifyAddDiscussionBox in class NewButtonActions",e);
		}
	}
	
	public void cancelButtonPressAddNoteBox()
	{
		try
		{	
			String adddissstring = new RandomString().randomstring(5);
			Driver.driver.findElement(By.className("editnote-text")).sendKeys(adddissstring);
			WebElement cancelbutton = Driver.driver.findElement(By.cssSelector("button[class='editnote-button pagecontext-cancel']"));			
			cancelbutton.click();			
			Thread.sleep(2000);
			int cnt = Driver.driver.findElements(By.className("editnote-text")).size();	
			if(cnt != 0)
					Assert.fail("Note Box is opened even after clicking the cancel button.");
			WebElement nopost = Driver.driver.findElement(By.className("stream-content-posts"));
			if(!nopost.getText().equals("Get help from teacher and peers - ask question and start a discussion OR save a personal note."))
				Assert.fail("The Note got posted even after pressing cancel button of the Add Note Box opened after clicking Add Note option from +New Button in eTextBook");
			  
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper cancelButtonPressAddDiscussionBox in class NewButtonActions",e);
		}
		
	}
	
	public void addNote()
	{
		try
		{	
			String adddissstring = new RandomString().randomstring(5);
			Driver.driver.findElement(By.className("editnote-text")).sendKeys(adddissstring);
			WebElement submitbutton = Driver.driver.findElement(By.cssSelector("button[class='editnote-button editnote-submit']"));			
			submitbutton.click();			
			Thread.sleep(2000);
			int cnt = Driver.driver.findElements(By.cssSelector("div[class='editnote-text']")).size();	
			if(cnt != 0)
					Assert.fail("Note box is opened even after clicking the submit button.");
			
			//Validating right side panel	
			
			WebElement profilepic =  Driver.driver.findElement(By.className("ls-right-user-img"));
			String imgtag = (String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",profilepic);
			
			if(!imgtag.contains("src=\"/webresources/images/ls/user-default-thumbnail.png\""))
				Assert.fail("Profile Pic not present in the add note posted shown at the right side of the screen under Stream tab in eTextBook");
			
			WebElement note = Driver.driver.findElement(By.className("ls-right-user-post-body"));
			if(!adddissstring.equals(note.getText().trim()))
			Assert.fail("Note added by user not found on the right side panel of the screen under Stream tab in eTextBook");  //Validating Discussion Text
			
			
			WebElement noteelement = Driver.driver.findElement(By.className("ls-right-user-head"));
			String postedanote = (String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",noteelement);
		    
			if(!postedanote.contains(" posted a note"))
			Assert.fail("Text 'Posted a Note' not found on right side panel inside the note block after posting a note"); //Validating posted a discussion text
			
			String username = Driver.driver.findElement(By.cssSelector("a[class='ls-right-user-name ellipsis']")).getText();
			System.out.println(username);
			String user = Config.givenname+" "+Config.familyname;
			if(!username.equals(user))
				Assert.fail("Username invalid or blank on right side panel inside the note block after posting a note"); //Validating user name
			
			Boolean staricon = Driver.driver.findElement(By.cssSelector("i[class='ls-right-section-sprites ls--right-star-icon']")).isDisplayed();
			if(staricon == false)
				Assert.fail("Star Icon not displayed on right side panel inside the note block after posting a Note");
						
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper addNote in class NewButtonActions",e);
		}
		
	}
	
	public void discussionBoxAfterDiscussionClickVerify()
	{
		try
		{
		Thread.sleep(3000);
		Driver.driver.findElement(By.className("stream-content-posts-list")).click();
		Thread.sleep(3000);
		if(!Driver.driver.findElement(By.cssSelector("div[class='prow editdiscussion-wrapper']")).isDisplayed())
			Assert.fail("Discussion box not getting expanded after clicking on the discussion in the stream view");
		Driver.driver.findElement(By.className("ls-dialog-entry-edit")).click();
		int commentlink = Driver.driver.findElements(By.cssSelector("a[class='right-post-comment active']")).size();
		if(commentlink == 0)
			Assert.fail("Discussion Box with arrow pointing downwards opened after clicking the discussion under streams doesnot contain comment link.");
		
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper discussionBoxAfterDiscussionClickVerify in class NewButtonActions",e);
		}
		
	}
	
	public void noteBoxAfterNoteClickVerify()
	{
		try
		{
		Thread.sleep(3000);
		Driver.driver.findElement(By.className("stream-content-posts-list")).click();
		Thread.sleep(3000);
		if(!Driver.driver.findElement(By.cssSelector("div[class='editnote-wrapper prow']")).isDisplayed())
			Assert.fail("Note box not getting expanded after clicking on the note in the stream view");
		Driver.driver.findElement(By.className("ls-user-posted-note-edit")).click();
		/*int commentlinkpresent = Driver.driver.findElements(By.cssSelector("a[class='right-post-comment active']")).size();
		if(commentlinkpresent == 0)
			Assert.fail("Note Box with arrow pointing downwards opened after clicking the note under streams doesnot contain comment link.");
		*/
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper noteBoxAfterNoteClickVerify in class NewButtonActions",e);
		}
		
	}
	
}
