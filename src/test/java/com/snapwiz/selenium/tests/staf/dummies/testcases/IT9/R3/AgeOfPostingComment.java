package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;


public class AgeOfPostingComment {
	
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.testcases.AgeOfPostingComment");
	
	@Test
	public void ageOfPostingComment()
	{
		try
		{
			logger.log(Level.INFO,"TestCase ageOfPostingComment under class AgeOfPostingComment Execution Started");
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");	
			
			String randomstring = new RandomString().randomstring(5);
			new PostMessage().postmessage(randomstring);
			
			WebElement commentage =  Driver.driver.findElement(By.cssSelector("time[class='ls-time-stamp ls-already-formatted']"));
			   
		    String age = (String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",commentage);
		    if(!age.equals("a second ago"))
		    	Assert.fail("Age of newly added comment not found as 'a second ago' or '1 seconds ago'");
		    
		    Thread.sleep(30000);
		    
		    Driver.driver.navigate().refresh();
		  
		    commentage =  Driver.driver.findElement(By.cssSelector("time[class='ls-time-stamp ls-already-formatted']"));
		    age = (String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",commentage);
		    if(!age.contains("seconds ago"))
		    	Assert.fail("Age of newly added comment not found as 'seconds ago' after few seconds of posting a comment");
		    
		    Thread.sleep(30000);
		    Driver.driver.navigate().refresh();
		  
		    commentage =  Driver.driver.findElement(By.cssSelector("time[class='ls-time-stamp ls-already-formatted']"));
		    age = (String) ((JavascriptExecutor)Driver.driver).executeScript("return arguments[0].innerHTML",commentage);
		    if(!age.equals("a minute ago"))
		    	Assert.fail("Age of newly added comment not found as 'a minute ago' after 1 minute of posting a comment");
		    
		    
		}
		catch(Exception e)
		{
			Assert.fail("Exception in TestCase ageOfPostingComment in class AgeOfPostingComment",e );
		}
	}
	
	@AfterMethod
	public void tearDown() throws Exception
	{
		Driver.driver.quit();
	}

}
