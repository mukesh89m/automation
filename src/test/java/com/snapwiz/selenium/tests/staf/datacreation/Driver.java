package com.snapwiz.selenium.tests.staf.datacreation;

import com.snapwiz.selenium.tests.staf.datacreation.Config;
import com.snapwiz.selenium.tests.staf.datacreation.apphelper.DBConnect;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


public  class Driver {
	
	 public static WebDriver driver;
	 public static Boolean flashplugin;
	 private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.datacreation.Driver");
	 public static String workingDir = System.getProperty("user.dir");

    @BeforeMethod
	  public WebDriver startDriver() throws Exception {
		if(flashplugin == null)
			flashplugin = true;
		Config.readconfiguration();

		if(Config.browser.equals("firefox"))
		{
		logger.log(Level.INFO,"Initializing Firefox WebDriver Profile");
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("app.update.auto", false);
		profile.setPreference("app.update.enabled", false);
        /*profile.setPreference("permissions.default.stylesheet", 2);
        profile.setPreference("permissions.default.image", 2);*/
		if(flashplugin == false)
		profile.setPreference("plugin.state.flash", 0);
		else
			profile.setPreference("plugin.state.flash", 1);
		driver = new FirefoxDriver(profile);
		}
		if(Config.browser.equals("ie"))
		{
			logger.log(Level.INFO,"Initializing IE WebDriver Profile");
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, false);

			File file = new File(workingDir+"/src/test/java/com/snapwiz/selenium/tests/staf/drivers/IEDriverServer");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			 driver = new InternetExplorerDriver(capabilities);
		}
		if(Config.browser.equals("chrome"))
		{
			 ChromeDriverService  service = new ChromeDriverService.Builder()
	         .usingDriverExecutable(new File(workingDir+"/src/test/java/com/snapwiz/selenium/tests/staf/drivers/chromedriver"))
	         .usingAnyFreePort()
	         .build();
	     service.start();
	     driver = new RemoteWebDriver(service.getUrl(),
	             DesiredCapabilities.chrome());
		}
		logger.log(Level.INFO,"WebDriver Profile Initialized");
	    driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	    driver.manage().deleteAllCookies();
	    driver.manage().window().maximize();
        DBConnect.Connect();
	    return driver;
	  }

	  public void chromeDriverStart() throws Exception
	  {
		  if(!Config.browser.equals("chrome"))
		  {
		  Driver.driver.quit(); //Quitting firefox driver and Starting chrome driver
		  ChromeDriverService  service = new ChromeDriverService.Builder()
		  .usingDriverExecutable(new File(workingDir + "/src/test/java/com/snapwiz/selenium/tests/staf/drivers/chromedriver"))
	         .usingAnyFreePort()
	         .build();
	     service.start();
	     Driver.driver = new RemoteWebDriver(service.getUrl(),
	             DesiredCapabilities.chrome());
	     Driver.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	     Driver.driver.manage().deleteAllCookies();
	     Driver.driver.manage().window().maximize();
		  }
	  }

	  public void firefoxDriverStart() throws Exception
	  {
		  if(!Config.browser.equals("firefox"))
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
			Driver.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		    Driver.driver.manage().deleteAllCookies();
			Driver.driver.manage().window().maximize();
		  }
	  }

	  public void ieDriverStart() throws Exception
	  {
		  if(!Config.browser.equals("ie"))
		  {
		  Driver.driver.quit(); //Quitting existing driver
		  logger.log(Level.INFO,"Initializing IE WebDriver Profile");
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, false);
		
			File file = new File(workingDir+"/src/test/java/com/snapwiz/selenium/tests/staf/drivers/IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			 driver = new InternetExplorerDriver(capabilities);
			Driver.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		    Driver.driver.manage().deleteAllCookies();
			Driver.driver.manage().window().maximize();
		  }
	  }
}
