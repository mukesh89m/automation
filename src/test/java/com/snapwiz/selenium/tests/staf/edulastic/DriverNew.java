package com.snapwiz.selenium.tests.staf.edulastic;

import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.DBConnect;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

//import io.appium.java_client.AppiumDriver;


public  class DriverNew {

    public WebDriver driver=null;
    protected ThreadLocal<WebDriver> threadDriver = null;
	 public static Boolean flashplugin;
	 private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.edulastic.Driver");
	 public static String workingDir = System.getProperty("user.dir");
    @BeforeMethod
	  public WebDriver startDriver() throws Exception {
        threadDriver = new ThreadLocal<WebDriver>();
		if(flashplugin == null)
			flashplugin = true;
		Config.readconfiguration();
          DBConnect.Connect();
         String className = new Exception().getStackTrace()[2].getClassName();
         if(className.equals("sun.reflect.NativeMethodAccessorImpl"))
              className = new Exception().getStackTrace()[1].getClassName();
		if(Config.browser.equals("firefox") && (Config.platform.equals("linux") || Config.platform.equals("windows")))
		{
		logger.log(Level.INFO,"Initializing Firefox WebDriver Profile for class "+className);


       // FirefoxProfile profile = new FirefoxProfile(new File("/root/.mozilla/firefox/QaAutomationFFProfile"));


		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("app.update.auto", false);
		profile.setPreference("app.update.enabled", false);
        if(flashplugin == false)
		profile.setPreference("plugin.state.flash", 0);
		else
			profile.setPreference("plugin.state.flash", 1);
		driver = new FirefoxDriver(profile);
		}
		if(Config.browser.equals("ie")&& (Config.platform.equals("linux") || Config.platform.equals("windows")))
		{
			logger.log(Level.INFO,"Initializing IE WebDriver Profile for class "+className);
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, false);
		
			File file = new File(workingDir+"/src/test/java/com/snapwiz/selenium/tests/staf/drivers/IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			 driver = new InternetExplorerDriver(capabilities);
		}
		if(Config.browser.equals("chrome")&& (Config.platform.equals("linux") || Config.platform.equals("windows")))
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
        if(Config.platform.equals("android"))
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("platformVersion", "4.4.2");
            capabilities.setCapability("deviceName", "Snapwiz (SM-T530NU)");
            capabilities.setCapability("browserName", "Chrome");
            //driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        }
		logger.log(Level.INFO,"WebDriver Profile Initialized");
          if(!(Config.platform.equals("android") || Config.platform.equals("ios"))) {
              driver.manage().window().maximize();
              driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
          }
          else
	        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);


        driver.manage().timeouts().pageLoadTimeout(300,TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(300,TimeUnit.SECONDS);
	    driver.manage().deleteAllCookies();
        //Adding mixpanel opt out cookie
        driver.get("https://.mixpanel.com/404");
        Cookie cookie = new Cookie("mp_optout", "1",".mixpanel.com","/",null);
        driver.manage().addCookie(cookie);
        threadDriver.set(driver);
	    return driver;
	  }

    @AfterMethod
    public void tearDown()
    {
        driver=threadDriver.get();
        try {
            DBConnect.conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    public WebDriver startDriver(WebDriver webDriver) throws Exception {
        if(flashplugin == null)
            flashplugin = true;
        Config.readconfiguration();
        DBConnect.Connect();
        String className = new Exception().getStackTrace()[2].getClassName();
        if(className.equals("sun.reflect.NativeMethodAccessorImpl"))
            className = new Exception().getStackTrace()[1].getClassName();
        if(Config.browser.equals("firefox") && (Config.platform.equals("linux") || Config.platform.equals("windows")))
        {
            logger.log(Level.INFO,"Initializing Firefox WebDriver Profile for class "+className);
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("app.update.auto", false);
            profile.setPreference("app.update.enabled", false);
            if(flashplugin == false)
                profile.setPreference("plugin.state.flash", 0);
            else
                profile.setPreference("plugin.state.flash", 1);
            webDriver = new FirefoxDriver(profile);
        }
        if(Config.browser.equals("ie")&& (Config.platform.equals("linux") || Config.platform.equals("windows")))
        {
            logger.log(Level.INFO,"Initializing IE WebDriver Profile for class "+className);
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, false);

            File file = new File(workingDir+"/src/test/java/com/snapwiz/selenium/tests/staf/drivers/IEDriverServer.exe");
            System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
            webDriver = new InternetExplorerDriver(capabilities);
        }
        if(Config.browser.equals("chrome")&& (Config.platform.equals("linux") || Config.platform.equals("windows")))
        {
            logger.log(Level.INFO,"Initializing chrome WebDriver Profile for class "+className);
            ChromeDriverService  service = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File(workingDir+"/src/test/java/com/snapwiz/selenium/tests/staf/drivers/chromedriver.exe"))
                    .usingAnyFreePort()
                    .build();
            service.start();
            webDriver = new RemoteWebDriver(service.getUrl(),
                    DesiredCapabilities.chrome());
        }
        if(Config.platform.equals("android"))
        {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("platformVersion", "4.4.2");
            capabilities.setCapability("deviceName", "Snapwiz (SM-T530NU)");
            capabilities.setCapability("browserName", "Chrome");
            //driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        }
        logger.log(Level.INFO,"WebDriver Profile Initialized");
        if(!(Config.platform.equals("android") || Config.platform.equals("ios"))) {
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
        else
            webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        webDriver.manage().timeouts().pageLoadTimeout(300,TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(300,TimeUnit.SECONDS);
        webDriver.manage().deleteAllCookies();
        //Adding mixpanel opt out cookie
        webDriver.get("https://.mixpanel.com/404");
        Cookie cookie = new Cookie("mp_optout", "1",".mixpanel.com","/",null);
        webDriver.manage().addCookie(cookie);
        return webDriver;

    }

    public void closeHelp()
    {
        try
        {
            if(driver.findElements(By.className("swhelp-button-cancel")).size() > 0)
                driver.findElement(By.className("swhelp-button-cancel")).click();
            Thread.sleep(5000);
        }
        catch (Exception e)
        {
        }
    }
}
