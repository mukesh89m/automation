package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R1;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;

/*
 * TC 26
 */
public class DirectLoginWithInvaildEmailPassword extends Driver{
	
	
	@Test
	public void directLoginWithInvaildEmailPassword() 
	  {
		 try
		 {
			 
			 driver.get(Config.baseURL);
			 String  username  =  ReadTestData.readDataByTagName("", "username", "26");
			 String  password  =  ReadTestData.readDataByTagName("", "password", "26");
			 driver.findElement(By.id("username")).sendKeys(username);
			 driver.findElement(By.id("password")).sendKeys(password);
			 driver.findElement(By.id("loginSubmitBtn")).click();
			 String errortext=driver.findElement(By.id("notificationMessageContDiv")).getText();
			 if(!errortext.contains("Username and Password doesn't match."))
				 Assert.fail("error text not shown");
		 }
		 catch(Exception e)
		 {
			 Assert.fail("Exception in testcase class DirectLoginWithInvaildEmailPassword",e);
		 }
	  }


}
