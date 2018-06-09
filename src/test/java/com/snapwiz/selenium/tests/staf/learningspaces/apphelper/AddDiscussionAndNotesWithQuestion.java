package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;

public class AddDiscussionAndNotesWithQuestion extends Driver
{
	public void addDiscussion()
	{
		WebDriver driver=Driver.getWebDriver();
		try
		{
			driver.findElement(By.xpath("//*[@id='ls-learn-content']/div[3]/div[1]/div[3]")).click();//click on discussion tab
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='ls-learn-content']/div[3]/div[2]/div[1]/div[1]/div/a")).click();//click on new discussion
			Thread.sleep(2000);
			driver.findElement(By.className("editdiscussion-text")).click();
			driver.findElement(By.className("editdiscussion-text")).clear();
			driver.findElement(By.className("editdiscussion-text")).sendKeys("this is discussion");//text entry for discussion
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
		WebDriver driver=Driver.getWebDriver();
		try
		{
			driver.findElement(By.xpath("//*[@id='ls-learn-content']/div[3]/div[1]/div[4]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id='ls-learn-content']/div[3]/div[2]/div[3]/div[1]/div/a")).click();
			Thread.sleep(2000);

			driver.findElement(By.className("editnote-text")).click();
			driver.findElement(By.className("editnote-text")).clear();
			driver.findElement(By.className("editnote-text")).sendKeys("this is notes");//text entry for note
			new Click().clickBycssselector("button[class='editnote-button editnote-submit']");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper addNote in class AddDiscussionAndNotesWithQuestion ",e);
		}
	}

}
