package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.util.List;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class MyJournal extends Driver{
	
	public boolean myJournalEntryOnBookmarking(String type, int zerobasedindex )
	{
		boolean result = false;
		try
		{
			String entryTitle = "";
			WebDriver driver=Driver.getWebDriver();
			List<WebElement> allElements = driver.findElements(By.className("journal-card-title"));
			if(type.equalsIgnoreCase("lesson"))
			{
				entryTitle = allElements.get(zerobasedindex).getText();
				if(!entryTitle.contains("Bookmarked a lesson on"))
					
					result = false;
				else
					result = true;
			}
			if(type.equalsIgnoreCase("resource"))
			{
				entryTitle = allElements.get(zerobasedindex).getText();
				if(!entryTitle.contains("Bookmarked a resource on"))
					
					result = false;
				else
					result = true;
			}
			
			else if(type.equalsIgnoreCase("widget"))
			{
				entryTitle = allElements.get(zerobasedindex).getText();
				if(!entryTitle.contains("Bookmarked a media on"))
					{
					result = false;
					}
				else
					result = true;
			}
			
			else if(type.equalsIgnoreCase("note"))
			{
				entryTitle = allElements.get(zerobasedindex).getText();
				if(entryTitle.contains("Posted a note on"))
					
					result = true;
					
				else
					result = false;
			}
			
			else if(type.equalsIgnoreCase("discussion"))
			{
				entryTitle = allElements.get(zerobasedindex).getText();
				if(entryTitle.contains("Posted a discussion on"))
					
					result = true;
					
				else
					result = false;
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper MyJournal in method myJournalEntryOnBookmarking", e);
		}
		return result;
	}
	public boolean myJournalEntryOnHighlightingText()
	{
		boolean result = false;
		WebDriver driver=Driver.getWebDriver();
		try
		{
				String journalText = driver.findElement(By.className("journal-card-title")).getText();
				if(journalText.contains("Highlighted a text on"))
					
					result = true;
					
				else
					result = false;
			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper MyJournal in method myJournalEntryOnHighlighting", e);
		}
		return result;
	}
	
	public int countoflikeinMyJournal(int zerobasedindex)
	{
		int likecount=0;
		WebDriver driver=Driver.getWebDriver();
		try
		{
			List<WebElement> allassignment=driver.findElements(By.cssSelector("span[class='ls-right-post-like-count']"));
			int index=0;
			for(WebElement assignment:allassignment)
			{
				String numberoflike=assignment.getText();
				if(index==zerobasedindex)
				{
					likecount=Integer.parseInt(numberoflike);
					break;
				}
				index++;				
			}			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper method countoflikeinMyJournal in class MyJournal",e);
		}
		return likecount;
	}
	public int countofcommmentinMyJournal(int zerobasedindex)
	{
		int commentcount=0;
		WebDriver driver=Driver.getWebDriver();
		try
		{
			List<WebElement> allassignment=driver.findElements(By.cssSelector("span[class='ls-right-stream-post-comment-count']"));
			int index=0;
			for(WebElement assignment:allassignment)
			{
				String numberoflike=assignment.getText();
				if(index==zerobasedindex)
				{
					commentcount=Integer.parseInt(numberoflike);
					break;
				}
				index++;				
			}			
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper method countoflikeinMyJournal in class MyJournal",e);
		}
		return commentcount;
	}

    /*
       @Author: Sumit on 18/08/2014

     */
    public void deleteNoteFromMyJournal(String dataIndex)
    {
		WebDriver driver=Driver.getWebDriver();
        try
        {
            driver.findElement(By.className("my-journal-trash-icon")).click();//click on delete icon
            new UIElement().waitAndFindElement(By.className("confirm-study-note-delete"));
            driver.findElement(By.className("confirm-study-note-delete")).click();//click on Yes link on pop-up
            Thread.sleep(2000);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper method deleteFromMyJournal in class MyJournal",e);
        }
    }
}
