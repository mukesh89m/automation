package com.snapwiz.selenium.tests.staf.learnon.apphelper;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learnon.Config;
import com.snapwiz.selenium.tests.staf.learnon.Driver;
import com.snapwiz.selenium.tests.staf.learnon.ReadTestData;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DirectLogin {
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learnon.apphelper.DirectLogin");
	
	/* Method for direct login for learning spaces. This method enters user id and password and logs in. The login will fail is the text verified
	 * is not present on the loaded page after clicking the login button. */
	  public void directLogin(String dataIndex) 
	  {
		 try
		 {
			String  username  =  ReadTestData.readDataByTagName("", "username", dataIndex);
			String  password  =  ReadTestData.readDataByTagName("", "password", dataIndex);
		    logger.log(Level.INFO,"Logging in using Direct Login of Learning Spaces");	      
		    Driver.driver.get(Config.baseURL + "/");
		    logger.log(Level.INFO,"Base URL for Direct login is:"+ Config.baseURL);	    
		    Driver.driver.findElement(By.id("username")).sendKeys(username);		   
		    Driver.driver.findElement(By.id("password")).sendKeys(password);		    
		    Driver.driver.findElement(By.id("loginSubmitBtn")).click();
		    Thread.sleep(3000);
		    logger.log(Level.INFO,"Clicked on Submit Button of Login page");	    
		    int welcompopup = Driver.driver.findElements(By.className("al-dialog-welcome-close-icon")).size();
			if(welcompopup==1)
			{
				Driver.driver.findElement(By.className("al-dialog-welcome-close-icon")).click();
			}    
		 }
		 catch (Exception e)
		 {
			 Assert.fail("Exception in directLogin in class DirectLogin",e);		 
		  }
		   
	  }
	  
	  public void CMSLogin()
	  {
		  try
		  {
			new Driver().firefoxDriverStart();
		    Driver.driver.get(Config.baseURL);
			Thread.sleep(3000);
			Driver.driver.findElement(By.id("username")).sendKeys(Config.cmsUserName);
			Driver.driver.findElement(By.id("password")).sendKeys(Config.cmsPassword);
			Driver.driver.findElement(By.id("loginSubmitBtn")).click();
			Thread.sleep(3000);
		  }
		  catch(Exception e)
		  {
			  Assert.fail("Exception in CMSLogin in class DirectLogin",e);
		  }
	  }
	  
	  public void publisherAdmin()
	  {
		  try
		  {
			new Driver().firefoxDriverStart();
		    Driver.driver.get(Config.baseURL);
			Thread.sleep(3000);
			Driver.driver.findElement(By.id("username")).sendKeys(Config.publisheradmin);
			Driver.driver.findElement(By.id("password")).sendKeys(Config.cmsPassword);
			Driver.driver.findElement(By.id("loginSubmitBtn")).click();
			Thread.sleep(3000);
		  }
		  catch(Exception e)
		  {
			  Assert.fail("Exception in CMSLogin in class DirectLogin",e);
		  }
	  }


    public void directLoginWithCreditial(String username,String password, int dataIndex)
    {
        try
        {
            new Driver().firefoxDriverStart();
            Driver.driver.get(com.snapwiz.selenium.tests.staf.learnon.Config.baseURL);
            Driver.driver.findElement(By.id("username")).sendKeys(username);
            Driver.driver.findElement(By.id("password")).sendKeys(password);
            Driver.driver.findElement(By.id("loginSubmitBtn")).click();
        }
        catch(Exception e)
        {
            new com.snapwiz.selenium.tests.staf.learnon.apphelper.Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in the method directLoginWithCreditial in class 'DirectLogin'",e);
        }
    }

}

