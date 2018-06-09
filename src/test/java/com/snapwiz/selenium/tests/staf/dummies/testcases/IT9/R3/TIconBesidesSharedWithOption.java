package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;
import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;

public class TIconBesidesSharedWithOption {
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.testcases.ScrolldownPageToViewOlderPost");
    /*
     * 65-68
     */
	@Test
	public void tIconBesidesSharedWithOption()
	{
		try
		{
			Driver.startDriver();
		    new UserCreate().CreateStudent("a", "");//create student
			new DirectLogin().studentLogin("a");
			new Navigator().NavigateTo("Course Stream");
			Driver.driver.findElement(By.linkText("Post")).click();
			Thread.sleep(3000);
			int ticon = Driver.driver.findElements(By.className("ls-shareImg")).size(); //checking T-icon 
			if(ticon == 0)
				Assert.fail("T-Icon not found Beside the share with option in course stream");			
			WebElement value=Driver.driver.findElement(By.className("ls-shareImg")); 
			boolean elementvaluepost=value.isDisplayed();
			Driver.driver.findElement(By.linkText("Link")).click();		//Clicking on Link Tab	
			Thread.sleep(3000);
			
			 ticon = Driver.driver.findElements(By.className("ls-shareImg")).size(); //checking T-icon
				if(ticon == 0)
					Assert.fail("T-Icon not found Beside the share with option in course stream");				
			WebElement value1=Driver.driver.findElement(By.className("ls-shareImg"));			
			boolean elementvalueLink=value1.isDisplayed();
			Driver.driver.findElement(By.linkText("File")).click(); //Clicking on File Tab
			Thread.sleep(3000);			
			 ticon = Driver.driver.findElements(By.className("ls-shareImg")).size(); //checking T-icon
				if(ticon == 0)
					Assert.fail("T-Icon not found Beside the share with option in course stream");
				
			WebElement value2=Driver.driver.findElement(By.className("ls-shareImg"));
			boolean elementvalueupload=value2.isDisplayed();
			
			if(elementvaluepost==true && elementvalueLink==true && elementvalueupload==true)
			{
				logger.log(Level.INFO,"Testcase TIconBesidesSharedWithOption Pass");
			}
			else
			{
				logger.log(Level.INFO,"Testcase TIconBesidesSharedWithOption Fail");
				Assert.fail("T option not  present for Post/Link/file share");
			}
			
		}
		catch (Exception e)
		 {
			  logger.log(Level.SEVERE,"Exception in DirectLogin Application Helper");
			  Assert.fail("Exception  in testcase loginlti in class LoginUsingLTI",e);
		  }
	}
	@AfterMethod
	  public void tearDown() throws Exception
	  {
	    	Driver.driver.quit();    
	  }

}
