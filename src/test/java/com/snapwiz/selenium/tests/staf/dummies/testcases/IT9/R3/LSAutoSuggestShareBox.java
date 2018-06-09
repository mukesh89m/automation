package com.snapwiz.selenium.tests.staf.dummies.testcases.IT9.R3;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.dummies.Driver;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.dummies.apphelper.UserCreate;


/*
 * R3.1020
 * 141-
 * Verify the auto-suggest while typing in the "share with" box
 */
public class LSAutoSuggestShareBox {
	
		  private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.testcases.LSAutoSuggestShareBox");
	 
	  @Test
	  public void autoSuggestShareBox() 
	  {
		  try
		  {
			  String methodName = new Exception().getStackTrace()[0].getMethodName();
			  String username = methodName.substring(0, 4);
			  System.out.println("username: "+username);
			  logger.log(Level.INFO,"Starting Execution of Testcase");	
			  Driver.startDriver();		 
			  new UserCreate().CreateStudent("a", "");//create student
			  new DirectLogin().studentLogin("a");
			  new Navigator().NavigateTo("Course Stream");
			  Driver.driver.findElement(By.linkText("Post")).click();
			  Driver.driver.findElement(By.cssSelector("a[class='closebutton']")).click();	
			  WebElement textbox = Driver.driver.findElement(By.className("maininput"));				 
			  textbox.sendKeys(username);		
			  WebElement studentname = (WebElement)((JavascriptExecutor)Driver.driver).executeScript("return arguments[0];",Driver.driver.findElement(By.cssSelector("ul[id='share-with_feed']")));
			  if(studentname.getText().trim().equals("No results found")) {
				  logger.log(Level.INFO,"share with box not suggest student or group and instructor name");
				  Assert.fail("share with box does not suggest student or group or instructor name");
			  }
			  else
			  {
				  logger.log(Level.INFO,"share with box  suggest student or group and instructor name");
				  
			  }
		  }
		  catch(Exception e)
		  {
			  	logger.log(Level.SEVERE,"Exception  in testcase LSAutoSuggestShareBox");
			  	Assert.fail("Exception  in testcase LSAutoSuggestShareBox",e);
		  }
	  }
	  
	  @AfterMethod
	  public void tearDown() throws Exception 
	  {
	     Driver.driver.quit();    
	  }

}
