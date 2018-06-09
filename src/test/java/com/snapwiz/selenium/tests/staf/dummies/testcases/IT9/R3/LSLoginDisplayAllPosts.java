package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;


import java.util.logging.Level;
import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.PostMessageValidate;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Screenshot;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;

public class LSLoginDisplayAllPosts {
	public static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3.LSLoginDisplayAllPosts");
	@Test
	public void lsloginDisplayAllPosts()
	{
		try
		{
			Driver.startDriver();
			new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");			
			String texttobeposted=new RandomString().randomstring(6);
			new PostMessage().postmessage(texttobeposted);
			Boolean postfound = new PostMessageValidate().postMessageValidate(texttobeposted);
			if(postfound == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Student not able to see the first post");
			}
			texttobeposted=new RandomString().randomstring(6);
			new PostMessage().postmessage(texttobeposted);
			postfound = new PostMessageValidate().postMessageValidate(texttobeposted);
			if(postfound == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Student not able to see the second post");
			}

			texttobeposted=new RandomString().randomstring(6);
			new PostMessage().postmessage(texttobeposted);
			postfound = new PostMessageValidate().postMessageValidate(texttobeposted);
			if(postfound == false)
			{
				new Screenshot().captureScreenshotFromTestCase();
				Assert.fail("Student not able to see the third post");
			}
		}
		catch(Exception e)
		{
			new Screenshot().captureScreenshotFromTestCase();
			logger.log(Level.SEVERE,"Exception in DirectLogin Application Helper",e);
			Assert.fail("Exception  in testcase lsloginDisplayAllPosts in class LSLoginDisplayAllPosts",e);
		}
	}
	@AfterMethod
	public void tearDown() throws Exception {
		Driver.driver.quit();
	}
}
