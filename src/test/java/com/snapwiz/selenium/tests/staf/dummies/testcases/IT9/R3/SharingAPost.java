package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.ReadTestData;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Logout;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PostMessageValidate;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;

public class SharingAPost {
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.testcases.SharingAPost");
	/*
	 * 209-211
	 */
   @Test(priority=1,enabled=true)
	public void sharepostwithclasssectionname()
	{
		try
		{
			Driver.startDriver();
			String randomstring=new RandomString().randomstring(20);
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");			
			new PostMessage().postmessage(randomstring);
			boolean postpresent = new PostMessageValidate().postMessageValidate(randomstring);
			if(postpresent == true)
			{
				 String attachedstring=Driver.driver.findElement(By.className("ls-stream-post__action")).getText();				  
				 Thread.sleep(5000);
				 String texttoverify=ReadTestData.readDataByTagName("", "texttoverify", "209");
				 if(!attachedstring.trim().equals(texttoverify))
				 {
					 Assert.fail("Posting with title 'Posted a Discussion' not added in course stream");
					  
				 }				
			}
		    else
			{
				 Assert.fail("Posted Text not Found");				  
			}
		}
		catch(Exception e)
		{
			 logger.log(Level.SEVERE,"Exception  in testcase sharepostwithclasssectionname in class SharingAPost",e);
			 Assert.fail("Exception  in testcase sharepostwithclasssectionname in class SharingAPost",e);     
		}
	}
 

@Test(priority=2,enabled=true)
public void loginstudentwithsameclasssectionverifypostedwithdisscution()
{
	try
	{
		Driver.startDriver();
		String randomstring=new RandomString().randomstring(20);
		new UserCreate().CreateStudent("a", "");//create student
		new UserCreate().CreateStudent("b", "");//create student
		new DirectLogin().studentLogin("a");
		new Navigator().NavigateTo("Course Stream");
		new PostMessage().postMessageAndShare(randomstring, "", "b", "","");				
		new Logout().logout();
		new DirectLogin().studentLogin("b");
		new Navigator().NavigateTo("Course Stream");
		Driver.driver.findElement(By.linkText("Post")).click();
		boolean postpresent = new PostMessageValidate().postMessageValidate(randomstring);
		
		  if(postpresent==false)
		  {
			  logger.log(Level.INFO,"Testcase loginstudentwithsameclasssectionverifypostedwithdisscution fail");
			  Assert.fail("Posting with title 'Posted a Discussion' not available in course stream.");
			 
		  }
				
	}
	catch(Exception e)
	  {
		  logger.log(Level.SEVERE,"Exception  in testcase loginstudentwithsameclasssectionverifypostedwithdisscution in class SharingAPost",e);
		  Assert.fail("Exception  in testcase loginstudentwithsameclasssectionverifypostedwithdisscution in class SharingAPost",e);
	     
	  }
}


/*@Test(priority=3,enabled=true)
public void loginstudentwithDiffrentclasssectionverifypostedwithdisscution()
{
	try
	{
		Driver.startDriver();
		new LoginUsingLTI().ltiLogin("209");
		new Navigator().NavigateTo("Course Stream");
		Driver.driver.findElement(By.linkText("Post")).click();
		String randomstring=new RandomString().randomstring(20);
		if(!new PostMessage().postMessageAndShare(randomstring, "stud", "classname", "209","true"))
			Assert.fail("Message not posted which is shared with class");
		new LoginUsingLTI().ltiLogin("211");
		new Navigator().NavigateTo("Course Stream");		
			Boolean postpresent = new PostMessageValidate().postMessageValidate(randomstring);	
			if(postpresent == true)
			{			  
				logger.log(Level.INFO,"Testcase loginstudentwithDiffrentclasssectionverifypostedwithdisscution Fail ");
				 Assert.fail("Posting with title 'Posted a Discussion'  available in course stream.");					
			}
			
		}		
	
	catch(Exception e)
	{
		
		logger.log(Level.SEVERE,"Exception  in testcase loginstudentwithDiffrentclasssectionverifypostedwithdisscution in class SharingAPost",e);
		Assert.fail("Exception  in testcase loginstudentwithDiffrentclasssectionverifypostedwithdisscution in class SharingAPost",e);
		
	}*/
@AfterMethod
public void TearDown()throws Exception
{
	Driver.driver.quit();
}
}
