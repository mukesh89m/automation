package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.MouseHover;

public class Logout {
	 private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Logout");
	/* Method to log out from learning spaces.*/
	  public void logout()
	  {
		  try
		  {
		  logger.log(Level.INFO,"Entered Logout function and finding logout menu button");
		/*  WebElement we=Driver.driver.findElement(By.cssSelector("div[class='nav--dropped__toggle']"));
		  ((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",we);
		  WebElement we1=Driver.driver.findElement(By.id("logout"));
		  ((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",we1);
		  Thread.sleep(2000);*/
		  MouseHover.mouserhover("ls-user-nav__username");
		  Driver.driver.findElement(By.linkText("Logout")).click();
		  }
		  catch(Exception e)
		  {
			  Assert.fail("Exception in Logout",e);
			 
		  }
		  
	  }
	
}
