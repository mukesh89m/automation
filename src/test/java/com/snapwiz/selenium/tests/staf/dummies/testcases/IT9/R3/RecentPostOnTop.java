package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;

import org.openqa.selenium.By;
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


public class RecentPostOnTop {

	/*
	 *59-61
	 */
	@Test
	public void recentPostOnTop()
	{
		try
		{
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			String texttopost=new RandomString().randomstring(6);
			new PostMessage().postmessage(texttopost);
			WebElement text = null;				
			text=Driver.driver.findElement(By.className("ls-stream-share__title"));			
			String textvalue=text.getText();
			if(!textvalue.equals(texttopost))
			{
				Assert.fail("The recently added posts did not appear at the top.");
			}			
		}
		catch (Exception e)
		 {
				  Assert.fail("Exception  in testcase recentPostOnTop in class RecentPostOnTop",e);
		  }
	}
	@AfterMethod
	  public void tearDown() throws Exception {
	    Driver.driver.quit();    
	  }

}
