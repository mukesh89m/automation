package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R3;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.KeysSend;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;

public class LinkTabWorking extends Driver{
	
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.LinkTabWorking");
	
	@Test(priority=1, enabled = true)
	 public void DefaultMessageForLinkTab()
	 {
	  try
	  {

	   new LoginUsingLTI().ltiLogin("220");
	   new Navigator().NavigateTo("Course Stream");
	   driver.findElement(By.linkText("Link")).click();
	   String deafultText = driver.switchTo().frame("iframe-user-link-input-div").findElement(By.xpath("/html/body/font")).getText();
		driver.switchTo().defaultContent();
	   
	         if(!deafultText.trim().contains("Share your link"))
	         {
	        	 Assert.fail("Default Text 'Share Your Link' is NOT present");
	         }
	        
	          
	  }
	  catch(Exception e)
	   {
		  e.printStackTrace();
		  Assert.fail("Exception TestCase DefaultMessageForLinkTab in class LinkTabWorking",e);	
	   }
	 }
@Test(priority=2, enabled=true)
	public void linkTabWorking() 
	{
		try{
			

			new LoginUsingLTI().ltiLogin("223");
			new Navigator().NavigateTo("Course Stream");
			new PostMessage().postLinkWithoutSubmit("www.yahoo.com");
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("scrapper")));

            boolean thumbnail=driver.findElement(By.id("scrapper")).isDisplayed();
		  	if(!thumbnail)
		  		Assert.fail("Thumbnail is NOT present after typing the URL");
		
		  	driver.findElement(By.id("post-submit-cancel-button")).click();
		  	Thread.sleep(5000);
		
		  	//URL entered along with a space
		  	new PostMessage().postLinkWithoutSubmit("www.yahoo.com ");
		  	boolean thumbnail1=driver.findElement(By.id("scrapper")).isDisplayed();
		  	if(!thumbnail1)
		  		Assert.fail("Thumbnail is NOT present after entering the URL with a space");
		  	driver.findElement(By.id("post-submit-cancel-button")).click();
		  	//TAB pressed after URL entering
		  	new PostMessage().postLinkWithoutSubmit("www.yahoo.com"+Keys.TAB);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("scrapper")));


            boolean thumbnail2=driver.findElement(By.id("scrapper")).isDisplayed();
		  	if(!thumbnail2)
		  		Assert.fail("Thumbnail is NOT present after entering the URL with TAB key being pressed");
		  	
		  	driver.findElement(By.id("post-submit-cancel-button")).click();
		  	
		}
		catch(Exception e)
		{
			Assert.fail("Exception TestCase linkTabWorking in class LinkTabWorking",e);	
		}
	}
	
@Test(priority=3, enabled=true)
public void deleteUrl()
{
	try
	{

		new LoginUsingLTI().ltiLogin("227");
		new Navigator().NavigateTo("Course Stream");
		String link = "www.google.com";
		new PostMessage().postLinkWithoutSubmit(link);
		
		WebElement t=driver.findElement(By.id("iframe-user-link-input-div"));
		driver.switchTo().frame(t) ;
		//driver.switchTo().frame("iframe-user-link-input-div");
		
		for(int i=0; i<link.length(); i++)		
			driver.findElement(By.xpath("/html/body")).sendKeys(Keys.BACK_SPACE);
		
		driver.switchTo().defaultContent();
	  	Thread.sleep(10000);
	  	boolean thumbnail=driver.findElement(By.id("scrapper")).isDisplayed();
	  	if(thumbnail)	  
	  		Assert.fail("Thumbnail doesn't get removed after deleting the URL");	
	}
	catch(Exception e)
	{
		Assert.fail("Exception TestCase deleteUrl in class LinkTabWorking",e);	
	}
}

@Test(priority=4, enabled=true)

public void enterTwoUrlCheckThumbnail()
{
	try
	{

		new LoginUsingLTI().ltiLogin("228");
		new Navigator().NavigateTo("Course Stream");
		new PostMessage().postLinkWithoutSubmit("www.wikipedia.com ");
		if(Config.browser.equals("ie"))
		{		
			new KeysSend().sendKeyBoardKeys("www.google.com");
		}
		else if(Config.browser.equals("chrome"))
		{
			WebElement t=driver.findElement(By.id("iframe-user-link-input-div"));
			driver.switchTo().frame(t) ;
			driver.findElement(By.xpath("/html/body")).sendKeys("www.google.com");
			driver.switchTo().defaultContent();
		}
		else
		{
		driver.switchTo().frame("iframe-user-link-input-div").findElement(By.xpath("/html/body")).sendKeys("www.google.com");
		driver.switchTo().defaultContent();
		}	
		Thread.sleep(10000);
	  	WebElement thumbnail=driver.findElement(By.id("scrapper"));
	  	if(!thumbnail.getText().trim().contains("Wikipedia"))
	  		Assert.fail("On Entering Two URLs, Thumbnail of the First entered URL is NOT present.");	  	
	}
	catch(Exception e)
	{
		Assert.fail("Exception TestCase enterTwoUrlCheckThumbnail in class LinkTabWorking",e);	
	}
}

@Test(priority=5, enabled=true)

public void textFirstThenUrlCheck()
{
	try
	{

		new LoginUsingLTI().ltiLogin("229");
		new Navigator().NavigateTo("Course Stream");
		String str = new RandomString().randomstring(4);
		new PostMessage().postLinkWithoutSubmit(str+Keys.SPACE);
		if(Config.browser.equals("ie"))
		{		
			new KeysSend().sendKeyBoardKeys("www.google.com"+Keys.TAB);
		}
		else if(Config.browser.equals("chrome"))
		{
			WebElement t=driver.findElement(By.id("iframe-user-link-input-div"));
			driver.switchTo().frame(t) ;
			driver.findElement(By.xpath("/html/body")).sendKeys("www.google.com"+Keys.TAB);
			driver.switchTo().defaultContent();
		}
		else
		{
		driver.switchTo().frame("iframe-user-link-input-div").findElement(By.xpath("/html/body")).sendKeys("www.google.com"+Keys.TAB);
		driver.switchTo().defaultContent();
		}	
	  	Thread.sleep(10000);  
		
		WebElement thumbnail=driver.findElement(By.id("scrapper"));
	  	System.out.println(thumbnail.getText());
		if(thumbnail.getText().trim().contains(str))
			Assert.fail("On entering text then URL, The thumbnail of the web page doesnt appear separated from the user entered text");
	  	
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
		Assert.fail("Exception TestCase textFirstThenUrlCheck in class LinkTabWorking",e);	
	}
}

@Test(priority=6, enabled=true)
public void urlFirstThentextCheck()
{
	try
	{

		new LoginUsingLTI().ltiLogin("230");
		new Navigator().NavigateTo("Course Stream");
		new PostMessage().postLinkWithoutSubmit("www.google.com ");		
		String str = new RandomString().randomstring(4);
		if(Config.browser.equals("ie"))
		{		
			new KeysSend().sendKeyBoardKeys(str+Keys.TAB);
		}
		else if(Config.browser.equals("chrome"))
		{
			WebElement t=driver.findElement(By.id("iframe-user-link-input-div"));
			driver.switchTo().frame(t) ;
			driver.findElement(By.xpath("/html/body")).sendKeys(str+Keys.TAB);
			driver.switchTo().defaultContent();
		}
		else
		{
		driver.switchTo().frame("iframe-user-link-input-div").findElement(By.xpath("/html/body")).sendKeys(str+Keys.TAB);
		driver.switchTo().defaultContent();
		}	
	  	Thread.sleep(10000);  
		WebElement thumbnail=driver.findElement(By.id("scrapper"));
	  	if(!thumbnail.getText().trim().equals(str))
	  	{
	  		logger.log(Level.INFO,"On entering URL then text, The thumbnail of the web page appears separated from the user entered text");
	  	}
	  	else
	  	{
	  		logger.log(Level.INFO,"On entering URL then text, The thumbnail of the web page doesnt appear separated from the user entered text");
	  		Assert.fail("On entering URL then text, The thumbnail of the web page doesnt appear separated from the user entered text");
	  	}
	  	driver.findElement(By.id("post-submit-cancel-button")).click();
	  	Thread.sleep(5000);
	  
		
		String str1 = new RandomString().randomstring(4);
		String str2 = new RandomString().randomstring(4);
		new PostMessage().postLinkWithoutSubmit(str+Keys.SPACE+"www.google.com"+Keys.SPACE+str2);

	  	
	  	WebElement thumbnail2=driver.findElement(By.id("scrapper"));
	  	if(!thumbnail2.getText().trim().contains(str1)||!thumbnail2.getText().trim().contains(str2))
	  	{
	  		logger.log(Level.INFO,"On entering text then URL and then text, Thumbnail appears separated from the text.");
	  	}
	  	else
	  	{
	  		Assert.fail("On entering text then URL and then text, Thumbnail appears separated from the text.");
	  	}
	  	
		
	}
	catch(Exception e)
	{
		Assert.fail("Exception TestCase urlFirstThentextCheck in class LinkTabWorking",e);	
	}
}

}	
