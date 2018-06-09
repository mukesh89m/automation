package com.snapwiz.selenium.tests.staf.orion.apphelper;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Driver;

public class AddNotesInQuestions 
{
	public void  addnotes(String notes)
	{
		try
		{
			Driver.driver.findElement(By.className("al-question-notes-icon-section")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("al-notes-input-text-section")).click();
			Thread.sleep(2000);
			Actions ac=new Actions(Driver.driver);
			Driver.driver.findElement(By.className("al-notes-input-text-section")).sendKeys(notes);
			Thread.sleep(2000);
			ac.sendKeys(Keys.RETURN).build().perform();
			Thread.sleep(1000);
			boolean addednotes=Driver.driver.findElement(By.id("al-user-notes-content")).isDisplayed();
			if(addednotes==false)
			{
				new Screenshot().captureScreenshotFromAppHelper();
				Assert.fail("notes not post");
			}
			
		}
		catch(Exception e)
		{
			Assert.fail("exception in app helper addnotes",e);
		}
	}
}
