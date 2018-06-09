package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;


public class VerifyEtextAndHelpPage extends Driver
{
	@Test(priority=1, enabled=true)
	public void verifyEtextAndHelpPage()
	{
		try
		{
			  new LoginUsingLTI().ltiLogin("531");
			  new Navigator().NavigateTo("Course Stream");
			  String courseStream = driver.getTitle();
	
			  if(!courseStream.trim().equals("WileyPLUS"))
				  Assert.fail("Course stream page doesn't show header as 'WileyPLUS'");	
			  
			  //Opening eTextBook from course stream
			  driver.findElement(By.className("navigator")).click();
			  Thread.sleep(2000);
			  driver.findElement(By.linkText("eTextbook")).click();
			  Thread.sleep(3000);
			  int helpclose = driver.findElements(By.cssSelector("div[class='close-help-page']")).size();
			  if(helpclose==0)
			  				  Assert.fail("Help Page not present");
			  else
				  driver.findElement(By.cssSelector("div[class='close-help-page']")).click();
		}
		catch(Exception e)
		   {
			Assert.fail("Exception TestCase verifyEtextAndHelpPage in class VerifyEtextAndHelpPage",e);
		   }
		
	}

@Test(priority=2, enabled=true)
public void verifyHelpPageAfterLogoutLogin()
{
	try
	{

		  new LoginUsingLTI().ltiLogin("534");
		  //Opening eTextBook from course stream
		  driver.findElement(By.className("navigator")).click();
		  Thread.sleep(2000);
		  driver.findElement(By.linkText("eTextbook")).click();
		  Thread.sleep(3000);
		  int helpclose = driver.findElements(By.cssSelector("div[class='close-help-page']")).size();
		  if(helpclose==0)
		  				  Assert.fail("Help Page not present at first login");
		  else
			  driver.findElement(By.cssSelector("div[class='close-help-page']")).click();
		 Thread.sleep(2000);
		  new LoginUsingLTI().ltiLogin("534");
		 
		  //Opening eTextBook from course stream
		  driver.findElement(By.className("navigator")).click();
		  Thread.sleep(2000);
		  driver.findElement(By.linkText("eTextbook")).click();
		  Thread.sleep(3000);
		  
		  int tocSize1 = driver.findElements(By.cssSelector("div[class='close-help-page']")).size();
		
		  if(tocSize1!=0)
		  {
			  Assert.fail("HelpPage is present on login for second time");
		  }
		 
	}
	catch(Exception e)
	   {
		Assert.fail("Exception TestCase verifyHelpPageAfterLogoutLogin in class VerifyEtextAndHelpPage",e);
	   }
	
}

}
