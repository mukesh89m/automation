package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;


//Test case id 38
public class LoginToAdaptiveCourse extends Driver{
	
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin");
	@Test
	public void loginToAdaptiveCourse() 
	  {
		try
		 {
		 logger.log(Level.INFO,"Starting TestCase LoginToAdaptiveCourse");
		 new DirectLogin().directLogin("38");
		 Thread.sleep(5000);
		 WebElement text=driver.findElement(By.name("Change"));
		 boolean value=text.isDisplayed();
		 if(value==true)
		 {
			 logger.log(Level.INFO,"Testcase LoginToAdaptiveCourse Fail");
			 TestCase.fail();
		 }
		 else
		 {
			 logger.log(Level.INFO,"Testcase LoginToAdaptiveCourse pass");
		 }
		 }
        
		 catch(Exception e)
		 {
			 logger.log(Level.SEVERE,"Exception in  TestCase LoginToAdaptiveCourse",e);			 
		 }
		
 }

	
}
