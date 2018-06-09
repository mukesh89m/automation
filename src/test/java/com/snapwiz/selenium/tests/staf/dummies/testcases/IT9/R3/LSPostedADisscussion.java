package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.ReadTestData;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PostMessageValidate;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;



public class LSPostedADisscussion {
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.testcases.LSPostedADisscussion");
	/*
	 * 204-
	 */
	@Test(priority = 1, enabled = true)
	public void DefaultMessage()
	{
		try
		{
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			Driver.driver.findElement(By.linkText("Post")).click();
			WebElement t=Driver.driver.findElement(By.id("iframe-user-text-input-div")); 
			Driver.driver.switchTo().frame(t) ;
			String labelNodeText = Driver.driver.findElement(By.xpath("/html/body/font")).getText();
			Driver.driver.switchTo().defaultContent();
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


	@Test(priority = 2,enabled = true)
	public void PostedaDiscussionAddedinStream()
	{
		try
		{
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
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
	@AfterMethod
	public void tearDown() throws Exception 
	{
		Driver.driver.quit();    
	}
}
