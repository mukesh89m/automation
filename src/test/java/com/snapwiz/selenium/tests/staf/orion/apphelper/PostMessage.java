package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;

public class PostMessage {
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.orion.apphelper.PostMessage");
	/*
	 * post any message in course
	 */
	public void postmessage(String randomtext)
	{
		try
		{
			Driver.driver.findElement(By.linkText("Post")).click();	
			if(Config.browser.equals("ie"))
			{		
				new KeysSend().sendKeyBoardKeys(randomtext);
			}
			else if(Config.browser.equals("chrome"))
			{
				WebElement t=Driver.driver.findElement(By.id("iframe-user-text-input-div")); 
				Driver.driver.switchTo().frame(t) ;
				Driver.driver.findElement(By.xpath("/html/body/font")).click();
				Driver.driver.findElement(By.xpath("/html/body")).sendKeys(randomtext);
				Driver.driver.switchTo().defaultContent();
			}
			else
			{
			Driver.driver.switchTo().frame("iframe-user-text-input-div").findElement(By.xpath("/html/body/font")).sendKeys(randomtext);
			Driver.driver.switchTo().defaultContent();
			}
			Thread.sleep(2000);
			Driver.driver.findElement(By.id("post-submit-button")).click();
			logger.log(Level.INFO,"Message Post");
			Thread.sleep(5000);			
		}
		catch(Exception e)
		{
			Assert.fail("Message not posted",e);
		}
	}
	
	public boolean postMessageAndShare(String randomtext,String shareWithInitialString,String studentnametag,String dataIndex,String shareWithClass)
	{
		Driver.driver.findElement(By.linkText("Post")).click();	
		boolean sharefound = false;
		try
		{
			if(Config.browser.equals("ie"))
			{
				
				Thread.sleep(2000);
				new KeysSend().sendKeyBoardKeys(randomtext);
			}
			else if(Config.browser.equals("chrome"))
			{
				WebElement t=Driver.driver.findElement(By.id("iframe-user-text-input-div")); 
				Driver.driver.switchTo().frame(t) ;
				Driver.driver.findElement(By.xpath("/html/body/font")).click();
				Driver.driver.findElement(By.xpath("/html/body")).sendKeys(randomtext);
				Driver.driver.switchTo().defaultContent();
			}
			else
			{
			Driver.driver.switchTo().frame("iframe-user-text-input-div").findElement(By.xpath("/html/body/font")).sendKeys(randomtext);
			Driver.driver.switchTo().defaultContent();
			}
			
			
			
			Driver.driver.findElement(By.className("maininput")).click();
			
			Driver.driver.findElement(By.className("closebutton")).click();
			
			Driver.driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString);
			List<WebElement> suggestname;
			if(shareWithClass.toUpperCase().equals("TRUE"))
				 suggestname = Driver.driver.findElements(By.xpath("//*[starts-with(@rel, 'cls_')]")); 
			else
				 suggestname = Driver.driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]"));  
			   for (WebElement answerchoice: suggestname)
			   {    
			    String studentnameverify=ReadTestData.readDataByTagName("", studentnametag, dataIndex);
			    if(answerchoice.getText().trim().equals(studentnameverify))
			    {
			     answerchoice.click();
			     sharefound=true;
			     break;
			    }
			   }			
			
			Driver.driver.findElement(By.id("post-submit-button")).click();
			logger.log(Level.INFO,"Message Posted with Share");
			Thread.sleep(5000);		
			
		}		
		catch(Exception e)
		{
			Assert.fail("Message not posted along with sharing it with student/instructor");
		}		
		return sharefound;
	}
	
	public void postlink(String linktext)
	{
		try
		{
			Driver.driver.findElement(By.linkText("Link")).click();
			Thread.sleep(2000);			
			if(Config.browser.equals("ie"))
			{		
				new KeysSend().sendKeyBoardKeys(linktext);
			}
			else if(Config.browser.equals("chrome"))
			{
				WebElement t=Driver.driver.findElement(By.id("iframe-user-link-input-div")); 
				Driver.driver.switchTo().frame(t) ;
				Driver.driver.findElement(By.xpath("/html/body/font")).click();
				Driver.driver.findElement(By.xpath("/html/body")).sendKeys(linktext);
				Driver.driver.switchTo().defaultContent();
			}
			else
			{
			Driver.driver.switchTo().frame("iframe-user-link-input-div").findElement(By.xpath("/html/body/font")).sendKeys(linktext);
			Driver.driver.switchTo().defaultContent();
			}
			Thread.sleep(5000);
			Driver.driver.findElement(By.id("post-submit-button")).click();
			logger.log(Level.INFO,"LinkPost Post");
			Thread.sleep(5000);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Link not posted");
		}
	}
	
	public void postLinkWithoutSubmit(String linktext)
	{
		try
		{
			Driver.driver.findElement(By.linkText("Link")).click();
			Thread.sleep(2000);			
			if(Config.browser.equals("ie"))
			{		
				new KeysSend().sendKeyBoardKeys(linktext);
			}
			else if(Config.browser.equals("chrome"))
			{
				WebElement t=Driver.driver.findElement(By.id("iframe-user-link-input-div")); 
				Driver.driver.switchTo().frame(t) ;
				Driver.driver.findElement(By.xpath("/html/body/font")).click();
				Driver.driver.findElement(By.xpath("/html/body")).sendKeys(linktext);
				Driver.driver.switchTo().defaultContent();
			}
			else
			{
			Driver.driver.switchTo().frame("iframe-user-link-input-div").findElement(By.xpath("/html/body/font")).sendKeys(linktext);
			Driver.driver.switchTo().defaultContent();
			}	
			Thread.sleep(10000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Link not posted");
		}
	}
	
	public Boolean postLinkAndShare(String linktext,String shareWithInitialString,String studentnametag,String dataIndex)
	{
		Boolean sharefound = false;
		try
		{
			Driver.driver.findElement(By.linkText("Link")).click();
			Thread.sleep(2000);			
			if(Config.browser.equals("ie"))
			{		
				new KeysSend().sendKeyBoardKeys(linktext);
			}
			else if(Config.browser.equals("chrome"))
			{
				WebElement t=Driver.driver.findElement(By.id("iframe-user-link-input-div")); 
				Driver.driver.switchTo().frame(t) ;
				Driver.driver.findElement(By.xpath("/html/body/font")).click();
				Driver.driver.findElement(By.xpath("/html/body")).sendKeys(linktext);
				Driver.driver.switchTo().defaultContent();
			}
			else
			{
			Driver.driver.switchTo().frame("iframe-user-link-input-div").findElement(By.xpath("/html/body/font")).sendKeys(linktext);
			Driver.driver.switchTo().defaultContent();
			}
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("maininput")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("closebutton")).click();
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("maininput")).sendKeys(shareWithInitialString);
			Thread.sleep(5000);
			List<WebElement> suggestname = Driver.driver.findElements(By.xpath("//*[starts-with(@rel, 'uid_')]")); 
			   for (WebElement answerchoice: suggestname)
			   {    
			    String studentnameverify=ReadTestData.readDataByTagName("", studentnametag, dataIndex);
			    if(answerchoice.getText().trim().equals(studentnameverify))
			    {
			     answerchoice.click();
			     sharefound=true;
			     break;
			    }
			   }
			
			Thread.sleep(3000);
			Driver.driver.findElement(By.id("post-submit-button")).click();
			logger.log(Level.INFO,"LinkPost Post");
			Thread.sleep(5000);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Link not posted along with sharing it with student/instructor");
		}
		return sharefound;
	}
	
}
