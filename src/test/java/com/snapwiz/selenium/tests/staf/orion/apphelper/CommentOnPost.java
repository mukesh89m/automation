package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.util.List;



import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Driver;

public class CommentOnPost {
	
	public boolean commentOnPost(String commenttext,int postOneBasedIndex)
	{
		Boolean commentposted = false;
		try
		{
		int index = 1;
		List<WebElement> commentarea = Driver.driver.findElements(By.cssSelector("textarea[class='ls-textarea-focus']"));
		for(WebElement comment : commentarea)
		{
			if(index == postOneBasedIndex)
			{
				Thread.sleep(3000);				
				comment.sendKeys(commenttext);
				Thread.sleep(3000);
				comment.sendKeys(Keys.ENTER);
				Thread.sleep(3000);
				commentposted = true;
				break;
			}
			
			index++;
		}
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper CommentOnPost",e);
		}
		return commentposted;
	}

}
