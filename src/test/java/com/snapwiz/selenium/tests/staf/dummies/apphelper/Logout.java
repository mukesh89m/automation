package com.snapwiz.selenium.tests.staf.dummies.apphelper;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.uihelper.MouseHover;

public class Logout {
	/* Method to log out from learning spaces.*/
	  public void logout()
	  {
		  try
		  {
			 WebElement we= Driver.driver.findElement(By.className("ls-user-nav__username"));
			 we.click();
			 Driver.driver.findElement(By.linkText("Logout")).click();
			 Thread.sleep(3000);
		  }
		  catch(Exception e)
		  {
			  Assert.fail("Exception in Logout",e);
			 
		  }
		  
	  }
	
}
