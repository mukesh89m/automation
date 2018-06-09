package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R3;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessageValidate;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;



public class LSPostedADisscussion extends Driver {
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.LSPostedADisscussion");
/*
 * 204-
 */
	@Test(priority=1,enabled=true)
	public void DefaultMessage()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("204");
			new Navigator().NavigateTo("Course Stream");
			driver.findElement(By.linkText("Post")).click();
			WebElement t=driver.findElement(By.id("iframe-user-text-input-div"));
			driver.switchTo().frame(t) ;
			String labelNodeText = driver.findElement(By.xpath("/html/body/font")).getText();
			driver.switchTo().defaultContent();
			String texttoverify1 =  ReadTestData.readDataByTagName("", "texttoverify1", "204");
			boolean stringvalue=labelNodeText.contains(texttoverify1);
			if(stringvalue==true)
			{
			    logger.log(Level.INFO,"Testcase DefaultMessage Pass");
			}
			else
			{
			    logger.log(Level.INFO,"Testcase DefaultMessage Fail");
			    Assert.fail("Text box  say by default:Share your knowledge or seek answers not shown");
			}			       
		}
		catch(Exception e)
		{
			logger.log(Level.SEVERE,"Exception in LoginUsingLTI Application Helper");
			Assert.fail("Exception  in testcase DefaultMessage in class LSPostedADisscussion",e);
			
		}			
	}

	
	@Test(priority=2,enabled=true)
	public void PostedaDiscussionAddedinStream()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("204");
			new Navigator().NavigateTo("Course Stream");			 
			String randomtext=new RandomString().randomstring(6);
			new PostMessage().postmessage(randomtext);
			boolean poststatus = new PostMessageValidate().postMessageValidate(randomtext);
			if(poststatus == false)
			{
				Assert.fail("Post NOT posted in course stream successfully");
			}
		  
		}
		catch(Exception e)
		{
			
			Assert.fail("Exception  in testcase PostedaDiscussionAddedinStream in class LSPostedADisscussion",e);
		}
		
	}

	
		
	
}
