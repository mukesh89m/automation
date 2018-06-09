package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R3;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
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

public class SharingAPost extends Driver{
	private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.SharingAPost");
	/*
	 * 209-211
	 */
   @Test(priority=1,enabled=true)
	public void sharepostwithclasssectionname()
	{
		try
		{

			new LoginUsingLTI().ltiLogin("209");
			new Navigator().NavigateTo("Course Stream");
			driver.findElement(By.linkText("Post")).click();
			String randomstring=new RandomString().randomstring(20);
			if(!new PostMessage().postMessageAndShare(randomstring, "stud", "classname", "209","true"))
				Assert.fail("Message not posted which is shared with class");
			boolean postpresent = new PostMessageValidate().postMessageValidate(randomstring);
			if(postpresent == true)
			{
				 String attachedstring=driver.findElement(By.className("ls-stream-post__action")).getText();
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

		new LoginUsingLTI().ltiLogin("209");
		new Navigator().NavigateTo("Course Stream");
		driver.findElement(By.linkText("Post")).click();
		String randomstring=new RandomString().randomstring(20);
		if(!new PostMessage().postMessageAndShare(randomstring, "stud", "classname", "209","true"))
			Assert.fail("Message not posted which is shared with class");		
		new LoginUsingLTI().ltiLogin("210");
		new Navigator().NavigateTo("Course Stream");
		driver.findElement(By.linkText("Post")).click();
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


@Test(priority=3,enabled=true)
public void loginstudentwithDiffrentclasssectionverifypostedwithdisscution()
{
	try
	{

		new LoginUsingLTI().ltiLogin("209");
		new Navigator().NavigateTo("Course Stream");
		driver.findElement(By.linkText("Post")).click();
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
		
	}
}

}
