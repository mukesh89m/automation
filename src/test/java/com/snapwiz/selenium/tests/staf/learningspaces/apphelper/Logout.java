package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;

public class Logout extends Driver {
	 private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Logout");
	/* Method to log out from learning spaces.*/
	  public void logout()
	  {
		  try
		  {
			  WebDriver driver=Driver.getWebDriver();
		  logger.log(Level.INFO,"Entered Logout function and finding logout menu button");
		/*  WebElement we=driver.findElement(By.cssSelector("div[class='nav--dropped__toggle']"));
		  ((JavascriptExecutor)driver).executeScript("arguments[0].click();",we);
		  WebElement we1=driver.findElement(By.id("logout"));
		  ((JavascriptExecutor)driver).executeScript("arguments[0].click();",we1);
		  Thread.sleep(2000);*/
		  new MouseHover().mouserhover("ls-user-nav__username");
		  driver.findElement(By.linkText("Logout")).click();
		  }
		  catch(Exception e)
		  {
			  Assert.fail("Exception in Logout",e);
			 
		  }
		  
	  }
	
}
