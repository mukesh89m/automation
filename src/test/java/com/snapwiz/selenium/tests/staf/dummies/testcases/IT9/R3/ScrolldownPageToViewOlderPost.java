package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.JavascriptExecutor;
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



public class ScrolldownPageToViewOlderPost {
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.testcases.ScrolldownPageToViewOlderPost");
	/*
	 * 52-56
	 */
	@Test
	public void scrolldownPageToViewOlderPost()
	{
		try
		{
			String texttopost=new RandomString().randomstring(6);
			String texttopost1=new RandomString().randomstring(6);
			String texttopost2=new RandomString().randomstring(6);
			String texttopost3=new RandomString().randomstring(6);						
			Driver.startDriver();
			new UserCreate().CreateStudent("aa", "");//create student
			new DirectLogin().studentLogin("aa");;
			new Navigator().NavigateTo("Course Stream");
			Thread.sleep(2000);
			new PostMessage().postmessage(texttopost);
			boolean  value=new PostMessageValidate().postMessageValidate(texttopost);
			new PostMessage().postmessage(texttopost1);
			boolean  value1=new PostMessageValidate().postMessageValidate(texttopost1);
			new PostMessage().postmessage(texttopost2);
			boolean  value2=new PostMessageValidate().postMessageValidate(texttopost2);
			new PostMessage().postmessage(texttopost3);
			boolean  value3=new PostMessageValidate().postMessageValidate(texttopost3);
			
			if(value==true && value1==true && value2==true && value3==true)
			{
				JavascriptExecutor jse = (JavascriptExecutor)Driver.driver;
				jse.executeScript("window.scrollBy(0,500)", "");
				Thread.sleep(5000);
				boolean valuepass=new PostMessageValidate().postMessageValidate(texttopost1);
				if(valuepass==true)
				{
					  logger.log(Level.INFO,"Testcase  ScrolldownPageToViewOlderPost Pass");
					  
					  jse.executeScript("window.scrollBy(500,900)", "");
					  boolean valuepassagain=new PostMessageValidate().postMessageValidate(texttopost);
					  if(valuepassagain==true)
					  {
						  logger.log(Level.INFO,"Testcase  ScrolldownAgailPageToViewOlderPost Pass");
					  }
					  else{
						  logger.log(Level.INFO,"Testcase  ScrolldownAgailPageToViewOlderPost Fail");
					  	 Assert.fail("on the next scrolling The next set of postings not retrieved and displayed to the student.");
					  }
					  
				 }
				 else
				 {
					 logger.log(Level.INFO," Testcase ScrolldownPageToViewOlderPost fail");
					 Assert.fail(" On scrolling down the page, student not be able to view the older posts.");
				  }
			  }
			  else
			  {
				  logger.log(Level.INFO,"1st post is not posted");
				  Assert.fail("One or More Messages not Posted Successfully");
			  }					
		}
		catch (Exception e)
		 {
			  logger.log(Level.SEVERE,"Exception  in testcase scrolldownPageToViewOlderPost in class ScrolldownPageToViewOlderPost",e);	
			  Assert.fail("Exception  in testcase scrolldownPageToViewOlderPost in class ScrolldownPageToViewOlderPost",e);
		  }
	}
	@AfterMethod
	  public void tearDown() throws Exception 
	  {
	    Driver.driver.quit();    
	  }

	

}
