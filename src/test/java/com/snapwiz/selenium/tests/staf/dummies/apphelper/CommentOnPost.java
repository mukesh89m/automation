package com.snapwiz.selenium.tests.staf.dummies.apphelper;

import java.util.List;



import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Driver;

public class CommentOnPost {
	
	public boolean commentOnPost(String commenttext,int postOneBasedIndex)
	{
		Boolean commentposted = false;
		try
		{

                    List<WebElement> allComments = Driver.driver.findElements(By.cssSelector("a[title='Comments']"));
                    allComments.get(postOneBasedIndex).click();//click on Comments Link
                    Thread.sleep(3000);
                    Driver.driver.switchTo().activeElement().sendKeys(commenttext+Keys.RETURN);
                    Thread.sleep(3000);
                    commentposted = true;

		Thread.sleep(3000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper CommentOnPost",e);
		}
		return commentposted;
	}

}
