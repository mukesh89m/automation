package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class NewButtonActions extends Driver {

	public void verifyNewButton()
	{
		try
		{
			WebDriver driver=Driver.getWebDriver();
            String discussionButton = driver.findElement(By.cssSelector("a[class='ls-toc-btn ls-right-new-btn']")).getText();
            if(!discussionButton.contains("+ New Discussion"))
				Assert.fail("+ New Discussion button is not present discussion tab in e-Textbook.");
            new Navigator().navigateToTab("Fav");
            List<WebElement> noteButtonOptions = driver.findElements(By.cssSelector("a[class='ls-toc-btn ls-right-new-btn']"));
			if(!noteButtonOptions.get(1).getText().contains("+ New Note"))
                Assert.fail("+ New Note button is not present discussion tab in e-Textbook.");
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
				WebDriver driver=Driver.getWebDriver();
                driver.findElement(By.cssSelector("a[class='ls-toc-btn ls-right-new-btn']")).click();//click on + New Discussion button
                //Verifying if add discussion box is opened or not
                int cnt;
                if(Config.browser.equals("chrome"))
                     cnt = driver.findElements(By.cssSelector("div[class='editdiscussion-text']")).size();
                else
                     cnt = driver.findElements(By.cssSelector("div[class='editdiscussion-text']")).size();
                if(cnt == 0)
                        Assert.fail("Discussion box not opened even after selecting Add Discussion from +Add options in eTextBook");

                WebElement submitbutton = driver.findElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']"));
                WebElement cancelbutton = driver.findElement(By.cssSelector("button[class='editdiscussion-button pagecontext-cancel']"));
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
		{	WebDriver driver=Driver.getWebDriver();
			String adddissstring = new RandomString().randomstring(5);
			driver.findElement(By.className("editdiscussion-text")).sendKeys(adddissstring);
			WebElement cancelbutton = driver.findElement(By.cssSelector("button[class='editdiscussion-button pagecontext-cancel']"));
			cancelbutton.click();
			int cnt;
			if(Config.browser.equals("chrome"))
				 cnt = driver.findElements(By.cssSelector("div[class='editdiscussion-text']")).size();
			else	
				 cnt = driver.findElements(By.cssSelector("div[class='editdiscussion-text']")).size();
			if(cnt != 0)
					Assert.fail("Discussion box is opened even after clicking the cancel button.");
			WebElement nopost = driver.findElement(By.className("stream-content-posts"));
			if(!nopost.getText().equals("Start a discussion with your class."))
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
		{	WebDriver driver=Driver.getWebDriver();
			String adddissstring = new RandomString().randomstring(5);
			driver.findElement(By.className("editdiscussion-text")).sendKeys(adddissstring);
			WebElement submitbutton = driver.findElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']"));
			submitbutton.click();			
			new UIElement().waitAndFindElement(By.cssSelector("div[class='editdiscussion-text']"));
			int cnt = driver.findElements(By.cssSelector("div[class='editdiscussion-text']")).size();
			if(cnt != 0)
					Assert.fail("Discussion box is opened even after clicking the submit button.");
			
			//Validating right side panel	
			
			WebElement profilepic =  driver.findElement(By.className("ls-right-user-img"));
			String imgtag = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",profilepic);
			
			if(!imgtag.contains("src=\"/webresources/images/ls/user-default-thumbnail.png\""))
				Assert.fail("Profile Pic not preset in the add discussion posted shown at the right side of the screen under Stream tab in eTextBook");
			
			WebElement note = driver.findElement(By.className("ls-right-user-post-body"));
			if(!adddissstring.equals(note.getText().trim()))
			Assert.fail("Discussion added by user not found on the right side panel of the screen under Stream tab in eTextBook");  //Validating Discussion Text
			
			
			WebElement noteelement = driver.findElement(By.className("ls-right-user-head"));
			String postedanote = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",noteelement);
		    
			if(!postedanote.contains(" posted a discussion"))
			Assert.fail("Text 'Posted a Discussion' not found on right side panel inside the note block after posting a Discussion"); //Validating posted a discussion text
			
			String username = driver.findElement(By.cssSelector("a[class='ls-right-user-name ellipsis']")).getText();

			if(!username.equals("You"))
				Assert.fail("Username invalid or blank on right side panel inside the discussion block after posting a Discussion"); //Validating user name
			
			Boolean staricon = driver.findElement(By.cssSelector("i[class='ls-right-section-sprites ls--right-star-icon']")).isDisplayed();
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
		{WebDriver driver=Driver.getWebDriver();
		//Verifying if add note box is opened or not
            new Navigator().navigateToTab("Fav");
            new Click().clickbylistcssselector("a[class='ls-toc-btn ls-right-new-btn']", 1);//click on note button
			int cnt = driver.findElements(By.cssSelector("div[class='editnote-text ']")).size();
			if(cnt == 0)
				Assert.fail("Note box not opened even after selecting Add Note from +Add options in eTextBook");
			
		WebElement submitbutton = driver.findElement(By.cssSelector("button[class='editnote-button editnote-submit']"));
		WebElement cancelbutton = driver.findElement(By.cssSelector("button[class='editnote-button pagecontext-cancel']"));
	
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
		{	WebDriver driver=Driver.getWebDriver();
			String adddissstring = new RandomString().randomstring(5);
			driver.findElement(By.className("editnote-text")).sendKeys(adddissstring);
			WebElement cancelbutton = driver.findElement(By.cssSelector("button[class='editnote-button pagecontext-cancel']"));
			cancelbutton.click();
			int cnt = driver.findElements(By.className("editnote-text")).size();
			if(cnt != 0)
					Assert.fail("Note Box is opened even after clicking the cancel button.");

            if(driver.findElements(By.className("stream-content-posts-list")).size() != 0)//if earlier notes are present
            {
                WebElement nopost = driver.findElement(By.className("stream-content-posts-list"));
                if(nopost.getText().equals(adddissstring))
                    Assert.fail("The Note got posted even after pressing cancel button of the Add Note Box.");
            }
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper cancelButtonPressAddDiscussionBox in class NewButtonActions",e);
		}
		
	}
	
	public void addNote()
	{
		try
		{	WebDriver driver=Driver.getWebDriver();
			String adddissstring = new RandomString().randomstring(5);
			driver.findElement(By.className("editnote-text")).sendKeys(adddissstring);
			WebElement submitbutton = driver.findElement(By.cssSelector("button[class='editnote-button editnote-submit']"));
			submitbutton.click();
			int cnt = driver.findElements(By.cssSelector("div[class='editnote-text']")).size();
			if(cnt != 0)
					Assert.fail("Note box is opened even after clicking the submit button.");
			
			//Validating right side panel	
			//images will not be there for notes
			/*WebElement profilepic =  driver.findElement(By.className("ls-right-user-img"));
			String imgtag = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",profilepic);
			
			if(!imgtag.contains("src=\"/webresources/images/ls/user-default-thumbnail.png\""))
				Assert.fail("Profile Pic not present in the add note posted shown at the right side of the screen under Stream tab in eTextBook");
			*/
			WebElement note = driver.findElement(By.className("ls-right-user-post-body"));
            if(!adddissstring.equals(note.getText()))
			Assert.fail("Note added by user not found on the right side panel of the screen under Stream tab in eTextBook");  //Validating Discussion Text
			
			
			WebElement noteelement = driver.findElement(By.className("ls-right-user-head"));
			String postedanote = (String) ((JavascriptExecutor)driver).executeScript("return arguments[0].innerHTML",noteelement);
		    
			if(!postedanote.contains(" posted a note"))
			Assert.fail("Text 'Posted a Note' not found on right side panel inside the note block after posting a note"); //Validating posted a discussion text
			
			String username = driver.findElement(By.cssSelector("a[class='ls-right-user-name ellipsis']")).getText();
			if(!username.equals("You"))
				Assert.fail("Username invalid or blank on right side panel inside the note block after posting a note"); //Validating user name
			
			boolean staricon = driver.findElement(By.cssSelector("i[class='ls-right-section-sprites ls--right-star-icon']")).isDisplayed();
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
			WebDriver driver=Driver.getWebDriver();
		new UIElement().waitAndFindElement(By.className("stream-content-posts-list"));
		driver.findElement(By.className("stream-content-posts-list")).click();
		new UIElement().waitAndFindElement(By.cssSelector("div[class='prow editdiscussion-wrapper']"));
		if(!driver.findElement(By.cssSelector("div[class='prow editdiscussion-wrapper']")).isDisplayed())
			Assert.fail("Discussion box not getting expanded after clicking on the discussion in the stream view");
		driver.findElement(By.className("ls-dialog-entry-edit")).click();
		int commentlink = driver.findElements(By.cssSelector("a[class='right-post-comment active']")).size();
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
		new UIElement().waitAndFindElement(By.className("stream-content-posts-list"));
			WebDriver driver=Driver.getWebDriver();
		driver.findElement(By.className("stream-content-posts-list")).click();
		new UIElement().waitAndFindElement(By.cssSelector("div[class='editnote-wrapper prow']"));
		if(!driver.findElement(By.cssSelector("div[class='editnote-wrapper prow']")).isDisplayed())
			Assert.fail("Note box not getting expanded after clicking on the note in the stream view");
		driver.findElement(By.className("ls-user-posted-note-edit")).click();
		/*int commentlinkpresent = driver.findElements(By.cssSelector("a[class='right-post-comment active']")).size();
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
