package com.snapwiz.selenium.tests.staf.dummies.apphelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Config;
import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.Click;

public class Discussion {
public void postDiscussion(String discussionText)
{
	try
	{
		//click on NEW
		((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.linkText("+ New")));
		Thread.sleep(2000);
		//select 'Add a Discussion' 
		((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[type='1']")));
		Thread.sleep(2000);
		((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")));
		Thread.sleep(2000);
		((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")));
		Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")).clear();
		Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")).sendKeys(discussionText);
		Thread.sleep(2000);
		Driver.driver.findElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']")).click();//click submit
		Thread.sleep(2000);
		
		String discussion = Driver.driver.findElement(By.className("ls-right-user-post-body")).getText();
		if(!discussion.equals(discussionText))
		{
			//Assert.fail("The note has not been posted.");
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")));
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")));
			Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")).clear();
			Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")).sendKeys(discussionText);
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']")).click();//click submit
			Thread.sleep(2000);
			if(!discussion.equals(discussionText))
				Assert.fail("The discussion has not been posted.");
		}
	}
	catch(Exception e)
	{
		Assert.fail("Exception in aphhelper Discussion in method postDiscussion", e);
	}
}

public void postNote(String discussionText)
{
	try
	{
		//click on NEW
		((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.linkText("+ New")));
		Thread.sleep(2000);
		//select 'Add a Note' 
		((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div.ptext")));
		//((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[type='1']")));
		Thread.sleep(2000);
		((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editnote-text ']")));
		Thread.sleep(2000);
		((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editnote-text ']")));
		Thread.sleep(2000);
		Driver.driver.findElement(By.cssSelector("div[class='editnote-text ']")).clear();
		Driver.driver.findElement(By.cssSelector("div[class='editnote-text ']")).sendKeys(discussionText);
		Thread.sleep(2000);
		Driver.driver.findElement(By.cssSelector("button[class='editnote-button editnote-submit']")).click();//click submit
		Thread.sleep(2000);
		String note = Driver.driver.findElement(By.className("ls-right-user-post-body")).getText();
		if(!note.equals(discussionText))
		{
			//Assert.fail("The note has not been posted.");
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editnote-text ']")));
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("div[class='editnote-text ']")).clear();
			Driver.driver.findElement(By.cssSelector("div[class='editnote-text ']")).sendKeys(discussionText);
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("button[class='editnote-button editnote-submit']")).click();//click submit
			Thread.sleep(2000);
			if(!note.equals(discussionText))
				Assert.fail("The note has not been posted.");
		}
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
			Driver.driver.findElement(By.cssSelector("i.ls-right-section-sprites.ls--right-star-icon")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("div.ls-user-posted-note-edit")).click();
			Thread.sleep(2000);
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
			Driver.driver.findElement(By.cssSelector("i.ls-right-section-sprites.ls--right-star-icon")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.linkText("Edit")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("div[class='remove-annotation']")).click();
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Discussion in method deleteDiscussion", e);
		}
	}
	public void addDiscussionAfterHighlightingText(String discussionText)
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
				actions.moveToElement(element, 0, 0)
				    .clickAndHold()
				    .moveByOffset(200, 0)
				    .release()
				    .perform();
			}
			List<WebElement> allColors = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'pcolor discussioncolor')]"));
			allColors.get(0).click();
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")));
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")));
			Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")).clear();
			Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")).sendKeys(discussionText);
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']")).click();//click submit
			Thread.sleep(2000);
			
			String discussion = Driver.driver.findElement(By.className("ls-right-user-post-body")).getText();
			if(!discussion.equals(discussionText))
			{
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")));
				Thread.sleep(2000);
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")));
				Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")).clear();
				Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")).sendKeys(discussionText);
				Thread.sleep(2000);
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
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.linkText("Edit")));
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")));
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")));
			Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")).clear();
			Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")).sendKeys(editedText);
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']")).click();//click submit
			Thread.sleep(2000);
			
			String discussion = Driver.driver.findElement(By.className("ls-right-user-post-body")).getText();
			if(!discussion.equals(editedText))
			{
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")));
				Thread.sleep(2000);
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")));
				Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")).clear();
				Driver.driver.findElement(By.cssSelector("div[class='editdiscussion-text']")).sendKeys(editedText);
				Thread.sleep(2000);
				Driver.driver.findElement(By.cssSelector("button[class='editdiscussion-button editdiscussion-submit']")).click();//click submit
				Thread.sleep(2000);
				if(!discussion.equals(editedText))
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
				actions.moveToElement(element, 0, 0)
				    .clickAndHold()
				    .moveByOffset(200, 0)
				    .release()
				    .perform();
			}
			List<WebElement> allColors = Driver.driver.findElements(By.xpath("//*[starts-with(@class, 'pcolor notecolor')]"));
			if(color.equalsIgnoreCase("yellow"))
				allColors.get(0).click();
			if(color.equalsIgnoreCase("blue"))
				allColors.get(1).click();
			if(color.equalsIgnoreCase("green"))
				allColors.get(2).click();
			if(color.equalsIgnoreCase("red"))
				allColors.get(3).click();
			Thread.sleep(2000);
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editnote-text ']")));
			Thread.sleep(2000);
			((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editnote-text ']")));
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("div[class='editnote-text ']")).clear();
			Driver.driver.findElement(By.cssSelector("div[class='editnote-text ']")).sendKeys(noteText);
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("button[class='editnote-button editnote-submit']")).click();//click submit
			Thread.sleep(2000);
			String note = Driver.driver.findElement(By.className("ls-right-user-post-body")).getText();
			if(!note.equals(noteText))
			{
				//Assert.fail("The note has not been posted.");
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editnote-text ']")));
				Thread.sleep(2000);
				Driver.driver.findElement(By.cssSelector("div[class='editnote-text ']")).clear();
				Driver.driver.findElement(By.cssSelector("div[class='editnote-text ']")).sendKeys(noteText);
				Thread.sleep(2000);
				Driver.driver.findElement(By.cssSelector("button[class='editnote-button editnote-submit']")).click();//click submit
				Thread.sleep(2000);
				if(!note.equals(noteText))
					Assert.fail("The note has not been posted.");
			}
			
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
			Driver.driver.findElement(By.className("ls-user-posted-note-edit")).click();//click on edit
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("div[class='editnote-text edit-mode-note']")).clear();
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("div[class='editnote-text edit-mode-note']")).sendKeys(editedNote);
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("button[class='editnote-button editnote-submit']")).click();//click submit
			Thread.sleep(2000);
			String note = Driver.driver.findElement(By.className("ls-right-user-post-body")).getText();
			if(!note.equals(editedNote))
			{
				//Assert.fail("The note has not been posted.");
				((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='editnote-text ']")));
				Thread.sleep(2000);
				Driver.driver.findElement(By.cssSelector("div[class='editnote-text ']")).clear();
				Driver.driver.findElement(By.cssSelector("div[class='editnote-text ']")).sendKeys(editedNote);
				Thread.sleep(2000);
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
			 Thread.sleep(2000);
			 Driver.driver.findElement(By.cssSelector("a.right-post-comment.active")).click();
			 Thread.sleep(2000);
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
			 Thread.sleep(2000);
			 new Click().clickByclassname("right-post-like"); //click on like    
		}
		catch(Exception e)
		{
			Assert.fail("Exception in aphhelper Discussion in method likeDiscussion", e);
		}
	}
}
