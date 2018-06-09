package com.snapwiz.selenium.tests.staf.dummies;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public  class Driver {
	
	 public static  WebDriver driver;
	 public static Boolean flashplugin;
	 private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.dummies.Driver");
	 public static String workingDir = System.getProperty("user.dir");
	  public static  WebDriver startDriver() throws Exception {
		if(flashplugin == null)
			flashplugin = true;
		com.snapwiz.selenium.tests.staf.dummies.Config.readconfiguration();
         String className = new Exception().getStackTrace()[2].getClassName();
         if(className.equals("sun.reflect.NativeMethodAccessorImpl"))
              className = new Exception().getStackTrace()[1].getClassName();
		if(com.snapwiz.selenium.tests.staf.dummies.Config.browser.equals("firefox"))
		{
		logger.log(Level.INFO,"Initializing Firefox WebDriver Profile for class "+className);
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("app.update.auto", false);
		profile.setPreference("app.update.enabled", false);
		if(flashplugin == false)
		profile.setPreference("plugin.state.flash", 0);
		else
			profile.setPreference("plugin.state.flash", 2);
		driver = new FirefoxDriver(profile);
		}
		if(com.snapwiz.selenium.tests.staf.dummies.Config.browser.equals("ie"))
		{
			logger.log(Level.INFO,"Initializing IE WebDriver Profile for class "+className);
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		
			File file = new File(workingDir+"/src/test/java/com/snapwiz/selenium/tests/staf/drivers/IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			 driver = new InternetExplorerDriver(capabilities);
		}
		if(com.snapwiz.selenium.tests.staf.dummies.Config.browser.equals("chrome"))
		{
             logger.log(Level.INFO,"Initializing chrome WebDriver Profile for class "+className);
			 ChromeDriverService  service = new ChromeDriverService.Builder()
	         .usingDriverExecutable(new File(workingDir+"/src/test/java/com/snapwiz/selenium/tests/staf/drivers/chromedriver.exe"))
	         .usingAnyFreePort()
	         .build();
	     service.start();
	     driver = new RemoteWebDriver(service.getUrl(),
	             DesiredCapabilities.chrome());
		}
		logger.log(Level.INFO,"WebDriver Profile Initialized");	  
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    driver.manage().deleteAllCookies();
	    driver.manage().window().maximize();
		
	    return driver;
	  }
	
	  public void chromeDriverStart() throws Exception
	  {
		  if(!com.snapwiz.selenium.tests.staf.dummies.Config.browser.equals("chrome"))
		  {
		  Driver.driver.quit(); //Quitting firefox driver and Starting chrome driver
		  ChromeDriverService  service = new ChromeDriverService.Builder() 
		  .usingDriverExecutable(new File(workingDir+"/src/test/java/com/snapwiz/selenium/tests/staf/drivers/chromedriver.exe"))
	         .usingAnyFreePort()
	         .build();
	     service.start();
	     Driver.driver = new RemoteWebDriver(service.getUrl(),
	             DesiredCapabilities.chrome());
	     Driver.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	     Driver.driver.manage().deleteAllCookies();
	     Driver.driver.manage().window().maximize();
		  }
	  }
	  
	  public void firefoxDriverStart() throws Exception
	  {
		  if(!com.snapwiz.selenium.tests.staf.dummies.Config.browser.equals("firefox"))
		  {
		  Driver.driver.quit(); //Quitting existing driver
		  logger.log(Level.INFO,"Initializing Firefox WebDriver Profile");			
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("app.update.auto", false);
			profile.setPreference("app.update.enabled", false);
			if(flashplugin == false)
			profile.setPreference("plugin.state.flash", 0);
			else
				profile.setPreference("plugin.state.flash", 1);
			driver = new FirefoxDriver(profile);
			Driver.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		    Driver.driver.manage().deleteAllCookies();
			Driver.driver.manage().window().maximize();
		  }
	  }
	  
	  public void ieDriverStart() throws Exception 
	  {
		  if(!com.snapwiz.selenium.tests.staf.dummies.Config.browser.equals("ie"))
		  {
		  Driver.driver.quit(); //Quitting existing driver
		  logger.log(Level.INFO,"Initializing IE WebDriver Profile");
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		
			File file = new File(workingDir+"/src/test/java/com/snapwiz/selenium/tests/staf/drivers/IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			 driver = new InternetExplorerDriver(capabilities);
			Driver.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		    Driver.driver.manage().deleteAllCookies();
			Driver.driver.manage().window().maximize();
		  }
	  }
}
