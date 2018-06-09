package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessageValidate;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;

public class InsScrolldownPageToViewOlderPost extends Driver {
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.InsScrolldownPageToViewOlderPost");
	
	@Test
	public void scrolldownPageToViewOlderPost()
	{
		try
		{
			String texttopost=new RandomString().randomstring(6);
			String texttopost1=new RandomString().randomstring(6);
			String texttopost2=new RandomString().randomstring(6);
			String texttopost3=new RandomString().randomstring(6);						
			new LoginUsingLTI().ltiLogin("52_R24");
			new Navigator().NavigateTo("Course Stream");
			Thread.sleep(2000);
			new PostMessage().postmessage(texttopost);
			boolean  value=new PostMessageValidate().postMessageValidateForInstructor(texttopost);
			new PostMessage().postmessage(texttopost1);
			boolean  value1=new PostMessageValidate().postMessageValidateForInstructor(texttopost1);
			new PostMessage().postmessage(texttopost2);
			boolean  value2=new PostMessageValidate().postMessageValidateForInstructor(texttopost2);
			new PostMessage().postmessage(texttopost3);
			boolean  value3=new PostMessageValidate().postMessageValidateForInstructor(texttopost3);
			
			if(value==true && value1==true && value2==true && value3==true)
			{
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("window.scrollBy(0,500)", "");
				Thread.sleep(5000);
				boolean valuepass=new PostMessageValidate().postMessageValidateForInstructor(texttopost1);
				if(valuepass==true)
				{
					  logger.log(Level.INFO,"Testcase  ScrolldownPageToViewOlderPost Pass");
					  
					  jse.executeScript("window.scrollBy(500,900)", "");
					  boolean valuepassagain=new PostMessageValidate().postMessageValidateForInstructor(texttopost);
					  if(valuepassagain==false)					 
						  Assert.fail("on the next scrolling The next set of postings not retrieved and displayed to the instructor.");
									  
				 }
				 else
				 {
					 logger.log(Level.INFO," Testcase ScrolldownPageToViewOlderPost fail");
					 Assert.fail(" On scrolling down the page, Instrcutor not be able to view the older posts.");
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
			  logger.log(Level.SEVERE,"Exception  in testcase scrolldownPageToViewOlderPost in class InsScrolldownPageToViewOlderPost",e);	
			  Assert.fail("Exception  in testcase scrolldownPageToViewOlderPost in class InsScrolldownPageToViewOlderPost",e);
		  }
	}

}
