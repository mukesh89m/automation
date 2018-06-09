package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import org.openqa.selenium.By;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.Click;

public class AddDiscussionAndNotesWithQuestion extends  Driver
{
	public void addDiscussion()
	{
		try
		{
			Driver.driver.findElement(By.xpath("//*[@id='ls-learn-content']/div[3]/div[1]/div[3]")).click();//click on discussion tab
			Thread.sleep(2000);
			Driver.driver.findElement(By.xpath("//*[@id='ls-learn-content']/div[3]/div[2]/div[1]/div[1]/div/a")).click();//click on new discussion
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("editdiscussion-text")).click();
			Driver.driver.findElement(By.className("editdiscussion-text")).clear();
			Driver.driver.findElement(By.className("editdiscussion-text")).sendKeys("this is discussion");//text entry for discussion
			new Click().clickBycssselector("button[class='editdiscussion-button editdiscussion-submit']");
			boolean discussionpost=new BooleanValue().booleanbyclass("stream-content-posts-list");
			if(discussionpost==false)
			{
				Assert.fail("discussion not posted");
			}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper addDiscussion in class AddDiscussionAndNotesWithQuestion ",e);
		}
	}
	public void addNote()
	{
		try
		{
			Driver.driver.findElement(By.xpath("//*[@id='ls-learn-content']/div[3]/div[1]/div[4]")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.xpath("//*[@id='ls-learn-content']/div[3]/div[2]/div[3]/div[1]/div/a")).click();
			Thread.sleep(2000);

			Driver.driver.findElement(By.className("editnote-text")).click();
			Driver.driver.findElement(By.className("editnote-text")).clear();
			Driver.driver.findElement(By.className("editnote-text")).sendKeys("this is notes");//text entry for note			
			new Click().clickBycssselector("button[class='editnote-button editnote-submit']");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper addNote in class AddDiscussionAndNotesWithQuestion ",e);
		}
	}

}
