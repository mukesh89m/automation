package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.util.logging.Level;
import java.util.logging.Logger;


import org.openqa.selenium.By;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Driver;

public class Logout {
	 private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.orion.apphelper.Logout");
	/* Method to log out from learning spaces.*/
	  public void logout()
	  {
		  try
		  {
		 
		  
		  Thread.sleep(5000);
		  logger.log(Level.INFO,"Entered Logout function and finding logout menu button");
		//  Actions action = new Actions(Driver.driver);
		 //   WebElement we = Driver.driver.findElement(By.xpath("/html/body/header/div/div[2]/div/span"));
		 //   action.moveToElement(we).moveToElement(driver.findElement(By.("Logout"))).click().build().perform();
		 //   action.moveToElement(we).moveToElement(Driver.driver.findElement(By.linkText("Logout"))).click().build().perform();
		  ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("/html/body/header/div/div[2]/div[2]/ul/li[2]/a")));
		  logger.log(Level.INFO,"Logged out Successfully");		 
		  Thread.sleep(5000);
		  if(!Driver.driver.getTitle().equals("Learning Personalized"))
		  {
			  Assert.fail("Logout Failed");
		  }
		  }
		  catch(Exception e)
		  {
			  Assert.fail("Exception in Logout",e);
			 
		  }
		  
	  }
	
}
