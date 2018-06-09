package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Config;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;

public class Discussion {
public void postDiscussion(String discussionText)
{
	try
	{
        Thread.sleep(3000);
		//click on +New Discussion
		((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[class='ls-toc-btn ls-right-new-btn']")));
		Thread.sleep(2000);
		((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")));
		Thread.sleep(2000);
		//((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")));
		Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")).clear();
		Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")).sendKeys(discussionText);
		Thread.sleep(2000);
		((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']")));//click submit
		//Driver.driver.findElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']")).click();//click submit
		Thread.sleep(2000);

		String discussion = Driver.driver.findElement(By.className("stream-content")).getText();
		if(!discussion.contains(discussionText))
		    Assert.fail("The discussion has not been posted.");

	}
	catch(Exception e)
	{
		Assert.fail("Exception in aphhelper Discussion in method postDiscussion", e);
	}
}

public void postNote(String noteText)
{
	try
	{
		//click on NEW
		((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[data-id='fav']")));
		new UIElement().waitAndFindElement(By.linkText("+ New Note"));
        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.linkText("+ New Note")));
        new UIElement().waitAndFindElement(By.xpath("//div[starts-with(@id,'editnote-text')]"));
		((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//div[starts-with(@id,'editnote-text')]")));
		new UIElement().waitAndFindElement(By.xpath("//div[starts-with(@id,'editnote-text')]"));
		((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//div[starts-with(@id,'editnote-text')]")));
		new UIElement().waitAndFindElement(By.xpath("//div[starts-with(@id,'editnote-text')]"));
		Driver.driver.findElement(By.xpath("//div[starts-with(@id,'editnote-text')]")).clear();
		Driver.driver.findElement(By.xpath("//div[starts-with(@id,'editnote-text')]")).sendKeys(noteText);
		new UIElement().waitAndFindElement(By.xpath("//button[text()='Submit']"));
		Driver.driver.findElement(By.xpath("//button[text()='Submit']")).click();//click submit
		Thread.sleep(2000);
        boolean notepresent = false;
		List<WebElement> notes = Driver.driver.findElements(By.className("star-content"));
        for(WebElement note : notes)
        {
            if(note.getText().contains(noteText)) {
                notepresent = true;
                break;
            }
        }
		if(notepresent == false)
			Assert.fail("The note has not been posted.");

	}
	catch(Exception e)
	{
		Assert.fail("Exception in aphhelper Discussion in method postNote", e);
	}
}
	public void deleteNote()
	{
		try
		{
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[data-id='fav']")));
            Thread.sleep(2000);
            int index=0;
            List<WebElement> boxes = Driver.driver.findElements(By.className("ls-right-user-content"));
            for(WebElement box:boxes)
            {
                if(!box.getText().contains("You posted a note"))
                {
                    index++;
                }
                else
                    break;
            }
            Driver.driver.findElements(By.className("ls-right-user-post-body")).get(index).click();
			//Driver.driver.findElement(By.cssSelector("i.ls-right-section-sprites.ls--right-star-icon")).click();
			new UIElement().waitAndFindElement(By.cssSelector("div.ls-user-posted-note-edit"));
			Driver.driver.findElement(By.cssSelector("div.ls-user-posted-note-edit")).click();
			new UIElement().waitAndFindElement(By.cssSelector("div[class='editnote-button remove-annotation']"));
			Driver.driver.findElement(By.cssSelector("div[class='editnote-button remove-annotation']")).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Discussion in method deleteNote", e);
		}
	}
	public void deleteDiscussion()
	{
		try
		{
			Driver.driver.findElement(By.cssSelector("a[class='ls-right-user-name ellipsis']")).click();
			new UIElement().waitAndFindElement(By.linkText("Edit"));
			Driver.driver.findElement(By.linkText("Edit")).click();
			new UIElement().waitAndFindElement(By.cssSelector("div[class='remove-annotation']"));
			Driver.driver.findElement(By.cssSelector("div[class='remove-annotation']")).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Discussion in method deleteDiscussion", e);
		}
	}

    public void deletePost(int index)
    {
        try
        {
            List<WebElement> toggle = Driver.driver.findElements(By.className("ls-dropdown__toggle"));
            toggle.get(index).click(); //click on the toggle

            WebElement remove = Driver.driver.findElement(By.linkText("Report Abuse"));
            if(remove.isDisplayed())
            {
                remove.click();
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in aphhelper Discussion in method deletePost", e);
        }
    }
	public void addDiscussionAfterHighlightingText(String discussionText)
	{
		try
		{
			
			new CloseHelpPage().closehelppage();
            WebElement element = (new WebDriverWait(Driver.driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("p[class='nonindent default']")));
			Actions actions = new Actions(Driver.driver);		
			if(Config.browser.equals("chrome"))
			{
			actions.moveToElement(element, 0, 0)
				.doubleClick()		
			    .release()
			    .perform();
			}
			else
			{
				actions.moveToElement(element, 60, 120)
				    .clickAndHold()
				    .moveByOffset(150, 150)
				    .release()
				    .perform();
			}
			List<WebElement> allColors = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'pcolor discussioncolor')]"));
			System.out.println("discussioncolor:"+allColors);
            allColors.get(0).click();
			new UIElement().waitAndFindElement(By.cssSelector("div[class='editdiscussion-text']"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")));
			new UIElement().waitAndFindElement(By.cssSelector("div[class='editdiscussion-text']"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")));
			Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")).clear();
			Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")).sendKeys(discussionText);
			new UIElement().waitAndFindElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']"));
			Driver.driver.findElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']")).click();//click submit
			new UIElement().waitAndFindElement(By.className("ls-right-user-post-body"));
			
			String discussion = Driver.driver.findElement(By.className("ls-right-user-post-body")).getText();
			if(!discussion.equals(discussionText))
			{
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")));
				new UIElement().waitAndFindElement(By.cssSelector("div[class='editdiscussion-text']"));
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")));
				Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")).clear();
				Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")).sendKeys(discussionText);
				 new UIElement().waitAndFindElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']"));
				Driver.driver.findElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']")).click();//click submit
				Thread.sleep(2000);
				if(!discussion.equals(discussionText))
					Assert.fail("The discussion has not been posted.");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Discussion in method addDiscussionAfterHighlightingText", e);
		}
	}
	public void editDiscussion(String editedText)
	{
		try
		{
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("ls-dialog-entry-edit")));
			new UIElement().waitAndFindElement(By.cssSelector("div[class='editdiscussion-text']"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")));
			new UIElement().waitAndFindElement(By.cssSelector("div[class='editdiscussion-text']"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")));
			Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")).clear();
			Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")).sendKeys(editedText);
			new UIElement().waitAndFindElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']"));

			Driver.driver.findElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']")).click();//click submit
            new WebDriverWait(Driver.driver,200).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-right-user-post-body")));
			String discussion = Driver.driver.findElement(By.className("ls-right-user-post-body")).getText();
            System.out.println("editedText:"+editedText);
            System.out.println("discussion:"+discussion);

            if(!discussion.equals(editedText)){

					Assert.fail("The edited discussion has not been posted.");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Discussion in method editDiscussion", e);
		}
	}
	
	
	public void addNoteAfterHighlightingText(String noteText, String color)
	{
		try
		{
            Thread.sleep(8000);//sleep added so that the help pop-up disappears

			WebElement element = (new WebDriverWait(Driver.driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("p[class='nonindent default']")));
			Actions actions = new Actions(Driver.driver);		
			if(Config.browser.equals("chrome"))
			{
			actions.moveToElement(element, 0, 0)
				.doubleClick()		
			    .release()
			    .perform();
			}
			else
			{
				actions.moveToElement(element, 60, 60)
				    .clickAndHold()
				    .moveByOffset(150, 150)
				    .release()
				    .perform();
			}
            Thread.sleep(5000);
			List<WebElement> allColors = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'pcolor notecolor')]"));
            System.out.println("noteColors:"+ allColors);
			if(color.equalsIgnoreCase("yellow"))
				allColors.get(0).click();
			if(color.equalsIgnoreCase("blue"))
				allColors.get(1).click();
			if(color.equalsIgnoreCase("green"))
				allColors.get(2).click();
			if(color.equalsIgnoreCase("red"))
				allColors.get(3).click();
			new UIElement().waitAndFindElement(By.cssSelector("div[class='editnote-text ']"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editnote-text ']")));
			new UIElement().waitAndFindElement(By.cssSelector("div[class='editnote-text ']"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editnote-text ']")));
			new UIElement().waitAndFindElement(By.cssSelector("div[class='editnote-text ']"));
			Driver.driver.findElement(By.cssSelector("div[class='editnote-text ']")).clear();
			Driver.driver.findElement(By.cssSelector("div[class='editnote-text ']")).sendKeys(noteText);
			new UIElement().waitAndFindElement(By.cssSelector("button[class='editnote-button editnote-submit']"));
			Driver.driver.findElement(By.cssSelector("button[class='editnote-button editnote-submit']")).click();//click submit
			new UIElement().waitAndFindElement(By.cssSelector("div[data-id='fav']"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[data-id='fav']")));
            Thread.sleep(2000);
            boolean notepresent = false;
            List<WebElement> notes = Driver.driver.findElements(By.className("ls-right-user-post-body"));
            for(WebElement note : notes)
            {
                if(note.getText().equals(noteText)) {
                    notepresent = true;
                    break;
                }
            }
            if(notepresent == false)
                Assert.fail("The note has not been posted.");


        }
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Discussion in method addNoteAfterHighlightingText", e);
		}
	}
	
	public void editNote(String editedNote)
	{
		try
		{
            new WebDriverWait(Driver.driver,120).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("ls-user-posted-note-edit")));
			Driver.driver.findElement(By.className("ls-user-posted-note-edit")).click();//click on edit
			new UIElement().waitAndFindElement(By.cssSelector("div[class='editnote-text edit-mode-note']"));
			Driver.driver.findElement(By.cssSelector("div[class='editnote-text edit-mode-note']")).clear();
			new UIElement().waitAndFindElement(By.cssSelector("div[class='editnote-text edit-mode-note']"));
			Driver.driver.findElement(By.cssSelector("div[class='editnote-text edit-mode-note']")).sendKeys(editedNote);
			new UIElement().waitAndFindElement(By.cssSelector("button[class='editnote-button editnote-submit']"));
			Driver.driver.findElement(By.cssSelector("button[class='editnote-button editnote-submit']")).click();//click submit
			new UIElement().waitAndFindElement(By.className("ls-right-user-post-body"));
			String note = Driver.driver.findElement(By.className("ls-right-user-post-body")).getText();
			if(!note.equals(editedNote))
			{
				//Assert.fail("The note has not been posted.");
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editnote-text ']")));
				new UIElement().waitAndFindElement(By.cssSelector("div[class='editnote-text ']"));
				Driver.driver.findElement(By.cssSelector("div[class='editnote-text ']")).clear();
				Driver.driver.findElement(By.cssSelector("div[class='editnote-text ']")).sendKeys(editedNote);
				new UIElement().waitAndFindElement(By.cssSelector("button[class='editnote-button editnote-submit']"));
				Driver.driver.findElement(By.cssSelector("button[class='editnote-button editnote-submit']")).click();//click submit
				Thread.sleep(2000);
				if(!note.equals(editedNote))
					Assert.fail("The note has not been edited.");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Discussion in method editNote", e);
		}
	}
	
	
	public void commentOnDiscussion(int index, String comment)
	{
		try
		{
			 List<WebElement> allElement = Driver.driver.findElements(By.className("stream-content-posts-list"));
			 allElement.get(index).click();
			 new UIElement().waitAndFindElement(By.cssSelector("a.right-post-comment.active"));
			 Driver.driver.findElement(By.cssSelector("a.right-post-comment.active")).click();
			 Thread.sleep(500);
			 Driver.driver.switchTo().activeElement().sendKeys(comment);
			 Driver.driver.switchTo().activeElement().sendKeys(Keys.RETURN);
			 Thread.sleep(2000);
			 List<WebElement> allComments = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'ls-comments-text')]"));
			 if(!allComments.get(allComments.size()-1).getText().contains(comment))
			  {
				Assert.fail("Comment for the discusion not posted.");
			  }
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Discussion in method commentOnDiscussion", e);
		}
	}
	public void likeDiscussion(int index, String comment)
	{
		try
		{
			 List<WebElement> allElement = Driver.driver.findElements(By.className("stream-content-posts-list"));
			 allElement.get(index).click();
			 new UIElement().waitAndFindElement(By.className("right-post-like"));
			 new Click().clickByclassname("right-post-like"); //click on like    
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Discussion in method likeDiscussion", e);
		}
	}
	
	public void addDiscussioninLessonText(String discussionText)
	{
		try
		{
			WebElement element = (new WebDriverWait(Driver.driver,10)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("p[class='nonindent default']")));
			Actions actions = new Actions(Driver.driver);		
			if(Config.browser.equals("chrome"))
			{
			actions.moveToElement(element, 0, 0)
				.doubleClick()		
			    .release()
			    .perform();
			}
			else
			{
				actions.moveToElement(element, 60, 60)
				    .clickAndHold()
				    .moveByOffset(150, 150)
				    .release()
				    .perform();
			}
			List<WebElement> allColors = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'pcolor discussioncolor')]"));
			allColors.get(0).click();
			new UIElement().waitAndFindElement(By.cssSelector("div[class='editdiscussion-text']"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")));
			new UIElement().waitAndFindElement(By.cssSelector("div[class='editdiscussion-text']"));
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")));
			Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")).clear();
			Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")).sendKeys(discussionText);
			new UIElement().waitAndFindElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']"));
			Driver.driver.findElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']")).click();//click submit
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Discussion in method addDiscussioninLessonText", e);
		}
	}
	
	public void DiscussionAndNotesBookmarkedValidation(String randomtext,boolean questioncard)
	{
		try
		{
			if(questioncard==true)
			{
				new Click().clickbylist("question-card-label", 0);//clikc on question card
			}
			new com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator().navigateToTab("Discussion");//navigate to discussion tab
			new Discussion().postDiscussion(randomtext);
			new com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator().navigateToTab("Fav");//navigater to favrouite tab
			boolean thumnailUnderFavTab=new BooleanValue().booleanbyclass("ls-right-user-img");//verify thumnail under fav tab
			if(thumnailUnderFavTab==true)
				Assert.fail("thumnail shown for discussion under fav tab");
			new com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator().navigateToTab("Discussion");//navigate to discussion tab
			Thread.sleep(2000);
			new Click().clickbylist("ls-right-user-content",0);//clikc on discussion posted
			new Click().clickbylistcssselector("i[class='ls-right-section-sprites ls--right-star-icon']", 0);//unbookmark the discussion
			new com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator().navigateToTab("Fav");//navigater to favrouite tab
			boolean discussionAfterunbookmark=new BooleanValue().booleanbyclass("ls-right-user-content");//verify thumnail under fav tab
			if(discussionAfterunbookmark==true)
				Assert.fail("discussion shown under fav tab after unbookmark");
			new com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator().navigateToTab("Discussion");//navigate to discussion tab
			new UIElement().waitAndFindElement(By.className("ls-right-user-post-body"));
			new Click().clickByclassname("ls-right-user-post-body");//click on added discussion
			new Click().clickByclassname("ls-dialog-entry-edit");//click on edit icon
			new Click().clickByclassname("remove-annotation");//delete the post
			new com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator().navigateToTab("Fav");//navigater to favrouite tab
			
			new Discussion().postNote(randomtext);//post notes
			new Click().clickbylist("stream-content-posts-list", 0);
			new Click().clickByclassname("ls-user-posted-note-edit");//click on edit link for notes
			//38
			new Click().clickBycssselector("div[class='editnote-button remove-annotation']");//click,on delete icon
			int notesAfterDelete=Driver.driver.findElements(By.className("ls-right-user-post-body")).size();
            if(notesAfterDelete != 0)
				Assert.fail("after unbookmark the highlighted text its not remove form star tab");
		}
		catch(Exception e)
		{
			Assert.fail("Excpetion in app helper DiscussionAndNotesBookmarkedValidation",e);
		}
	}
    //Author Sumit
    //checks whether a note od discussion is present under a particular tab
    public boolean isDiscussionOrNotePresentUnderTabs(String tabName, String discussionOrNote)
    {
        boolean isPresent = false;
        try
        {
            new com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator().navigateToTab(tabName);
            List<WebElement> discussion;
            discussion = Driver.driver.findElements(By.className("ls-right-user-post-body"));
            for(WebElement dis:discussion)
            {
				new UIElement().waitAndFindElement(dis);
                if(dis.getText().equals(discussionOrNote))
                {
                    isPresent = true;
                    break;
                }
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper isDiscussionOrNotePresentUnderTabs in class Discussion",e);
        }
        return isPresent;
    }
}
