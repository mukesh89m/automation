package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;

public class InsRecentPostOnTop extends Driver{

	@Test
	public void recentPostOnTop()
	{
		try
		{
			new LoginUsingLTI().ltiLogin("59_R24");
			new Navigator().NavigateTo("Course Stream");
			String texttopost=new RandomString().randomstring(6);
			new PostMessage().postmessage(texttopost);

            String texttopost1 = new RandomString().randomstring(6);
            new PostMessage().postmessage(texttopost1);
            WebElement	text=driver.findElement(By.className("ls-stream-share__title"));
			
			String textvalue=text.getText();
			if(!textvalue.equals(texttopost))
			{
				Assert.fail("The recently added posts did not appear at the top.");
			}			
		}
		catch (Exception e)
		 {
			  Assert.fail("Exception  in testcase recentPostOnTop in class InsRecentPostOnTop",e);
		  }
	}

}
