package com.snapwiz.selenium.tests.staf.dummies.apphelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Driver;

public class PostMessageValidate {
	
	public boolean postMessageValidate(String postString)
	{
		boolean postfound = false;
		try
		{
			Thread.sleep(5000);
			List<WebElement> posts = Driver.driver.findElements(By.className("ls-stream-share__title"));
			for(WebElement post : posts)
			{
				if(post.getText().trim().equals(postString))
						{
							postfound = true;
							break;
						}
				
			}	
			
			if(postfound == true)
			{
				WebElement posttext = Driver.driver.findElement(By.className("ls-stream-post__action"));
			
					if(!posttext.getText().trim().equals("posted a discussion"))
							{								
								postfound = false;
							}				
			}
				
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper PostMessageValidate",e);
		}
		return postfound;
	}
	
	public boolean validateLink(String linktoValidate)
	{
		boolean linkfound = false;
		try
		{
		Thread.sleep(5000);
		List<WebElement> links = Driver.driver.findElements(By.className("ls-stream-share__title"));
		for(WebElement linktext : links)
			{
			if(linktext.getText().trim().equals(linktoValidate))
					{
						
						linkfound = true;
						break;
						
					}
			
			}		
		
		if(linkfound == true)
		{
			WebElement posttext = Driver.driver.findElement(By.className("ls-stream-post__action"));
		
				if(!posttext.getText().trim().equals("shared a link"))
						{								
							linkfound = false;
						}				
		}
		
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper validateLink in class PostMessageValidate",e);
		}
		return linkfound;
	}
	
	public boolean postMessageValidateForInstructor(String postString)
	{
		boolean postfound = false;
		try
		{
			Thread.sleep(5000);
			int index = 0;			
			
			List<WebElement> posts = Driver.driver.findElements(By.className("ls-stream-share__title"));
			for(WebElement post : posts)
			{
				if(post.getText().trim().equals(postString))
						{
							postfound = true;
							break;
						}
				index++;
			}	
	
			if(postfound == true)
			{
				WebElement posttext = Driver.driver.findElement(By.className("ls-stream-post__action"));
			
					if(!posttext.getText().trim().equals("posted a discussion"))
							{								
								postfound = false;
							}				
			}
			
			List <WebElement> instructortags = Driver.driver.findElements(By.className("ls-instructor-icon"));
			if(!instructortags.get(index).getText().trim().equals("Instructor"))
				Assert.fail("Instructor tag not present when an instructor has posted in course stream");
				
		}
		catch(Exception e)
		{
			Assert.fail("Exception in App Helper PostMessageValidate",e);
		}
		return postfound;
	}
	
}
