package com.snapwiz.selenium.tests.staf.dummies.apphelper;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Config;
import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.ShareWith;

public class PostMessage {
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.apphelper.PostMessage");
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
				new com.snapwiz.selenium.tests.staf.dummies.apphelper.KeysSend().sendKeyBoardKeys(randomtext);
			}
			else// if(Config.browser.equals("chrome"))
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
			Driver.driver.findElement(By.id("post-submit-button")).click();
			logger.log(Level.INFO,"Message Post");
			Thread.sleep(5000);			
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
				new com.snapwiz.selenium.tests.staf.dummies.apphelper.KeysSend().sendKeyBoardKeys(randomtext);
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

    public void postMessageWithshare(String randomtext, String shareWithInitialString, String appendCharacter, String dataIndex, String shareWithClass)
    {
        try
        {
            Driver.driver.findElement(By.linkText("Post")).click();
            if(Config.browser.equals("ie"))
            {
                new com.snapwiz.selenium.tests.staf.dummies.apphelper.KeysSend().sendKeyBoardKeys(randomtext);
            }
            else //if(Config.browser.equals("chrome"))
            {
                WebElement t=Driver.driver.findElement(By.id("iframe-user-text-input-div"));
                Driver.driver.switchTo().frame(t) ;
                Driver.driver.findElement(By.xpath("/html/body/font")).click();
                Driver.driver.findElement(By.xpath("/html/body")).sendKeys(randomtext);
                Driver.driver.switchTo().defaultContent();
            }
            String methodName = new Exception().getStackTrace()[1].getMethodName();
            String shareName = methodName+appendCharacter;
            System.out.println("shareName: "+shareName);
            new ShareWith().share(shareName,true);
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


    //in this method we can ignore shareWithInitialString, dataIndex, shareWithClass parameter and the appendCharacter for student name need to passed while calling the method
	public boolean postMessageAndShare(String randomtext, String shareWithInitialString, String appendCharacter, String dataIndex, String shareWithClass)
	{
		Driver.driver.findElement(By.linkText("Post")).click();	
		boolean sharefound = false;
		try
		{
			if(Config.browser.equals("ie"))
			{
				
				Thread.sleep(2000);
				new com.snapwiz.selenium.tests.staf.dummies.apphelper.KeysSend().sendKeyBoardKeys(randomtext);
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
			
			//String shareName=ReadTestData.readDataByTagName("", studentnametag, dataIndex);
			String methodName = new Exception().getStackTrace()[1].getMethodName();
			String shareName = methodName+appendCharacter;
			System.out.println("shareName: "+shareName);
			new ShareWith().share(shareName,true);
			
			Driver.driver.findElement(By.id("post-submit-button")).click();
			logger.log(Level.INFO,"Message Posted with Share");
			Thread.sleep(5000);
            sharefound = new PostMessageValidate().postMessageValidate(randomtext);
            if(sharefound == false) Assert.fail("Message posted not visible on Course Stream");

		}		
		catch(Exception e)
		{
			Assert.fail("Message not posted along with sharing it with student/instructor",e);
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
				new com.snapwiz.selenium.tests.staf.dummies.apphelper.KeysSend().sendKeyBoardKeys(linktext);
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
				new com.snapwiz.selenium.tests.staf.dummies.apphelper.KeysSend().sendKeyBoardKeys(linktext);
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
	
	//in this method we can ignore shareWithInitialString, dataIndex, shareWithClass parameter and the appendCharacter for student name need to passed while calling the method 
	public boolean postLinkAndShare(String linktext,String shareWithInitialString,String studentnametag,String dataIndex)
	{
		boolean sharefound = false;
		try
		{
			Driver.driver.findElement(By.linkText("Link")).click();
			Thread.sleep(2000);			
			if(Config.browser.equals("ie"))
			{		
				new com.snapwiz.selenium.tests.staf.dummies.apphelper.KeysSend().sendKeyBoardKeys(linktext);
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
			 //String shareName=ReadTestData.readDataByTagName("", studentnametag, dataIndex);
			String methodName = new Exception().getStackTrace()[1].getMethodName();
			String shareName = methodName+studentnametag;
			new ShareWith().share(shareName,true);
			
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
