package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.util.logging.Level;
import java.util.logging.Logger;


import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;

public class DirectLogin extends Driver{
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.orion.apphelper.DirectLogin");
	public WebDriver driver=Driver.getWebDriver();
	public DirectLogin(){
		Config.readconfiguration();
	}
	
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
		    logger.log(Level.INFO,"Clicked on Submit Button of Login page");	    
		    Thread.sleep(5000);
			 ReportUtil.log("Login into LS","Login is successful","pass");
		 }
		 catch (Exception e)
		 {
			 ReportUtil.log("Login into LS","Unable to login as there was a exception "+e.getMessage(),"fail");
			 Assert.fail("Exception in directLogin in class DirectLogin",e);		 
		  }
		   
	  }
	  
	  public void CMSLogin()
	  {
		  try
		  {
		    driver.get(Config.baseURL);
			Thread.sleep(3000);
			driver.findElement(By.id("username")).sendKeys(Config.cmsUserName);
			driver.findElement(By.id("password")).sendKeys(Config.cmsPassword);
			driver.findElement(By.id("loginSubmitBtn")).click();
			Thread.sleep(3000);
			  ReportUtil.log("Login into CMS","Login is successful","pass");
		  }
		  catch(Exception e)
		  {
			  ReportUtil.log("Login into CMS","Unable to login as there was a exception "+e.getMessage(),"fail");
			  Assert.fail("Exception in CMSLogin in class DirectLogin",e);
		  }
	  }

}
