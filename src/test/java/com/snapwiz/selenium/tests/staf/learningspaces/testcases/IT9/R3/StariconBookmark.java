package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R3;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessageValidate;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;

public class StariconBookmark extends Driver{
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.StariconBookmark");
	/*
	 * 341-344
	 */
	@Test
	public void VerifyStarIcon()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("340");
			new Navigator().NavigateTo("Course Stream");
			String randomstring=new RandomString().randomstring(4);
			new PostMessage().postmessage(randomstring);
			if(new PostMessageValidate().postMessageValidate(randomstring))
			{
				
				int star=driver.findElements(By.cssSelector("i[class='ls-icon-img ls--star-icon']")).size();
				if(star > 0)
				{
					logger.log(Level.INFO,"Star Icon present with post");
					String starcolor=driver.findElement(By.cssSelector("i[class='ls-icon-img ls--star-icon']")).getCssValue("background-color");
					System.out.println(starcolor);
					driver.findElement(By.cssSelector("i[class='ls-icon-img ls--star-icon']")).click();
					Thread.sleep(2000);
					String starcolor1=driver.findElement(By.cssSelector("i[class='ls-icon-img ls--star-icon']")).getCssValue("background-color");
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

	

}
