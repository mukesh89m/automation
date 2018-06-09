package com.snapwiz.selenium.tests.staf.datacreation.apphelper;

import com.snapwiz.selenium.tests.staf.datacreation.Config;
import com.snapwiz.selenium.tests.staf.datacreation.Driver;
import com.snapwiz.selenium.tests.staf.datacreation.ReadTestData;
import com.snapwiz.selenium.tests.staf.datacreation.uihelper.TextSend;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DirectLogin extends Driver {
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin");
	
	/* Method for direct login for learning spaces. This method enters user id and password and logs in. The login will fail is the text verified
	 * is not present on the loaded page after clicking the login button. */
	  public void directLogin(String dataIndex) 
	  {
		 try
		 {
			String  username  =  ReadTestData.readDataByTagName("", "username", dataIndex);
			String  password  =  ReadTestData.readDataByTagName("", "password", dataIndex);
		    logger.log(Level.INFO,"Logging in using Direct Login of Learning Spaces");	      
		    driver.get(Config.baseURL + "/");
		    logger.log(Level.INFO,"Base URL for Direct login is:"+ Config.baseURL);	    
		    driver.findElement(By.id("username")).sendKeys(username);
		    driver.findElement(By.id("password")).sendKeys(password);
		    driver.findElement(By.id("loginSubmitBtn")).click();
		    Thread.sleep(3000);
		    logger.log(Level.INFO,"Clicked on Submit Button of Login page");	    
		    int welcompopup = driver.findElements(By.className("al-dialog-welcome-close-icon")).size();
			if(welcompopup==1)
			{
				driver.findElement(By.className("al-dialog-welcome-close-icon")).click();
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
		    driver.get(Config.baseURL);
			Thread.sleep(3000);
            //new TextSend().textsendbyid("gaurav.author@snapwiz.com","login-email");
            //new TextSend().textsendbyid("snapwiz","login-password");
			driver.findElement(By.id("username")).sendKeys(Config.cmsUserName);
			driver.findElement(By.id("password")).sendKeys(Config.cmsPassword);
            driver.findElement(By.cssSelector("input[type='submit']")).click();
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
            driver.get(Config.baseURL);
            Thread.sleep(3000);
            driver.findElement(By.id("username")).sendKeys(username);
            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.id("loginSubmitBtn")).click();
            Thread.sleep(3000);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in CMSLogin in class DirectLogin",e);
        }
    }

}

