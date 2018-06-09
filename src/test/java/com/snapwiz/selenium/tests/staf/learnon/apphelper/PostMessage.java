package com.snapwiz.selenium.tests.staf.learnon.apphelper;

import com.snapwiz.selenium.tests.staf.learnon.Config;
import com.snapwiz.selenium.tests.staf.learnon.Driver;
import com.snapwiz.selenium.tests.staf.learnon.ReadTestData;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.ShareWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostMessage {
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learnon.apphelper.PostMessage");
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
			else// if(Config.browser.equals("chrome"))
			{
				WebElement t=Driver.driver.findElement(By.id("iframe-user-text-input-div"));
				Driver.driver.switchTo().frame(t) ;
				Driver.driver.findElement(By.xpath("/html/body/font")).click();
				Driver.driver.findElement(By.xpath("/html/body")).sendKeys(randomtext);
				Driver.driver.switchTo().defaultContent();
			}
            new Click().clickByid("post-submit-button");
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Message not posted",e);
		}
	}

	public void postMessageWithoutSubmit(String randomtext)
	{
		try
		{
			Driver.driver.findElement(By.linkText("Post")).click();
			if(Config.browser.equals("ie"))
			{
				new KeysSend().sendKeyBoardKeys(randomtext);
			}
			else //if(Config.browser.equals("chrome"))
			{
				WebElement t=Driver.driver.findElement(By.id("iframe-user-text-input-div"));
				Driver.driver.switchTo().frame(t) ;
				Driver.driver.findElement(By.xpath("/html/body/font")).click();
				Driver.driver.findElement(By.xpath("/html/body")).sendKeys(randomtext);
				Driver.driver.switchTo().defaultContent();
			}
			/*else
			{
			Driver.driver.switchTo().frame("iframe-user-text-input-div").findElement(By.xpath("/html/body/font")).sendKeys(randomtext);
			Driver.driver.switchTo().defaultContent();
			}*/
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Message not entered in the share-with box",e);
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
			else //if(Config.browser.equals("chrome"))
			{
				WebElement t=Driver.driver.findElement(By.id("iframe-user-text-input-div"));
				Driver.driver.switchTo().frame(t) ;
				Driver.driver.findElement(By.xpath("/html/body/font")).click();
				Driver.driver.findElement(By.xpath("/html/body")).sendKeys(randomtext);
				Driver.driver.switchTo().defaultContent();
			}
			/*else
			{
			Driver.driver.switchTo().frame("iframe-user-text-input-div").findElement(By.xpath("/html/body/font")).sendKeys(randomtext);
			Driver.driver.switchTo().defaultContent();
			}*/

			String shareName=ReadTestData.readDataByTagName("", studentnametag, dataIndex);
            String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
			new ShareWith().shareInCS(shareWithInitialString, shareName, shareWithClass,context_title,true);

			Driver.driver.findElement(By.id("post-submit-button")).click();
			logger.log(Level.INFO,"Message Posted with Share");
			Thread.sleep(5000);

		}
		catch(Exception e)
		{
			Assert.fail("Message not posted along with sharing it with student/instructor",e);
		}
		sharefound = new PostMessageValidate().postMessageValidate(randomtext);
        if(sharefound == false) Assert.fail("Message posted not visible on Course Stream");
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
			else //if(Config.browser.equals("chrome"))
			{
				WebElement t=Driver.driver.findElement(By.id("iframe-user-link-input-div"));
				Driver.driver.switchTo().frame(t) ;
				Driver.driver.findElement(By.xpath("/html/body/font")).click();
				Driver.driver.findElement(By.xpath("/html/body")).sendKeys(linktext);
				Driver.driver.switchTo().defaultContent();
			}
			/*else
			{
			Driver.driver.switchTo().frame("iframe-user-link-input-div").findElement(By.xpath("/html/body/font")).sendKeys(linktext);
			Driver.driver.switchTo().defaultContent();
			}*/
			Thread.sleep(10000);
			Driver.driver.findElement(By.id("post-submit-button")).click();
			logger.log(Level.INFO,"LinkPost Post");
			Thread.sleep(5000);

		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Link not posted",e);
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
			else //if(Config.browser.equals("chrome"))
			{
				WebElement t=Driver.driver.findElement(By.id("iframe-user-link-input-div"));
				Driver.driver.switchTo().frame(t) ;
				Driver.driver.findElement(By.xpath("/html/body/font")).click();
				Driver.driver.findElement(By.xpath("/html/body")).sendKeys(linktext);
				Driver.driver.switchTo().defaultContent();
			}
			/*else
			{
			Driver.driver.switchTo().frame("iframe-user-link-input-div").findElement(By.xpath("/html/body/font")).sendKeys(linktext);
			Driver.driver.switchTo().defaultContent();
			}*/
			Thread.sleep(10000);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Link not posted",e);
		}
	}

	public boolean postLinkAndShare(String linktext,String shareWithInitialString,String studentnametag,String dataIndex)
	{
		boolean sharefound = false;
		try
		{
			Driver.driver.findElement(By.linkText("Link")).click();
			Thread.sleep(2000);
			if(Config.browser.equals("ie"))
			{
				new KeysSend().sendKeyBoardKeys(linktext);
			}
			else //if(Config.browser.equals("chrome"))
			{
				WebElement t=Driver.driver.findElement(By.id("iframe-user-link-input-div")); 
				Driver.driver.switchTo().frame(t) ;
				Driver.driver.findElement(By.xpath("/html/body/font")).click();
				Driver.driver.findElement(By.xpath("/html/body")).sendKeys(linktext);
				Driver.driver.switchTo().defaultContent();
			}
			/*else
			{
			Driver.driver.switchTo().frame("iframe-user-link-input-div").findElement(By.xpath("/html/body/font")).sendKeys(linktext);
			Driver.driver.switchTo().defaultContent();
			}*/
			 String shareName=ReadTestData.readDataByTagName("", studentnametag, dataIndex);
             String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);
			 new ShareWith().shareInCS(shareWithInitialString, shareName, "false",context_title,true);
			
			Thread.sleep(3000);
			Driver.driver.findElement(By.id("post-submit-button")).click();
			logger.log(Level.INFO,"LinkPost Post");
			Thread.sleep(5000);
			List<WebElement> allSharedElements = Driver.driver.findElements(By.className("ls-stream-share__title"));
			if(allSharedElements.get(0).getText().contains(linktext))
				sharefound = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Assert.fail("Link not posted along with sharing it with student/instructor",e);
		}
		return sharefound;
	}
	
}
