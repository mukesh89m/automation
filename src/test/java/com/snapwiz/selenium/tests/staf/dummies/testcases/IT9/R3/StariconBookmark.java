package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;
import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PostMessageValidate;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.RandomString;

public class StariconBookmark {
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.testcases.StariconBookmark");
	/*
	 * 341-344
	 */
	@Test
	public void VerifyStarIcon()
	{
		try
		{
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			String randomstring=new RandomString().randomstring(4);
			new PostMessage().postmessage(randomstring);
			if(new PostMessageValidate().postMessageValidate(randomstring))
			{
				
				int star=Driver.driver.findElements(By.cssSelector("i[class='ls-icon-img ls--star-icon']")).size();
				if(star > 0)
				{
					logger.log(Level.INFO,"Star Icon present with post");
					String starcolor=Driver.driver.findElement(By.cssSelector("i[class='ls-icon-img ls--star-icon']")).getCssValue("background-color");
					System.out.println(starcolor);
					Driver.driver.findElement(By.cssSelector("i[class='ls-icon-img ls--star-icon']")).click();
					Thread.sleep(2000);
					String starcolor1=Driver.driver.findElement(By.cssSelector("i[class='ls-icon-img ls--star-icon']")).getCssValue("background-color");
					System.out.println(starcolor1);
				}
				else
				{
					Assert.fail("Star icon NOT present on the posting under course stream to bookmark the post");
				}
			}
						
		}
		catch(Exception e)
		{
			 logger.log(Level.SEVERE,"Exception  in testcase VerifyStarIcon in class StariconBookmark",e);			 
			 Assert.fail("Exception  in testcase VerifyStarIcon in class StariconBookmark",e);			  
		}
	}
	@AfterMethod
	  public void tearDown() throws Exception 
	  {
	     Driver.driver.quit();    
	  }
	

}
