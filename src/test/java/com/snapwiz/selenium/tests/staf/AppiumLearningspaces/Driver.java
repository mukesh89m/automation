package com.snapwiz.selenium.tests.staf.AppiumLearningspaces;

import java.io.File;
import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelperAppium.Calender_appium;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pageFactoryAppium.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public  class Driver {
	public static String appendChar;
	public static  WebDriver driver;
	public static AppiumDriver appiumDriver;
	public static Boolean flashplugin;
	private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver");
	public static String workingDir = System.getProperty("user.dir");
	public static Dashboard_appiumPF dashBoard;
	public static HomePage_appium homePage;
	public static LoginPage_appium loginPage;
	public static Assignments_appium assignments;
	public static EtextBook_appium eTextbook;
	public static CourseStream_appium courseStreamAppium;
	public static RandomLoginPage_appium randomLoginPage;

	@AfterMethod
	public void tearDown() throws Exception
	{
        try {
            //DBConnect.conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
		driver.quit();
		appiumDriver.quit();
	}

	@BeforeClass
	public static  String generateAppendChar() throws Exception {
		try{
			appendChar = System.getProperty("UCHAR");
			if(appendChar ==null){
				appendChar = new Calender_appium().getTodaysDateAsperFormat();
				System.out.println("APPEND CHARACTER : " + appendChar);
				appendChar= appendChar.substring(0,5)+"c";
			}
		}catch(Exception e){
			Assert.fail("Exception in the method 'generateAppendChar' in the class 'AppiumLearningspaces'",e);
		}
		return  appendChar;
	}

		@BeforeMethod
	  public static  WebDriver startDriver() throws Exception {
		try {
			if (flashplugin == null)
				flashplugin = true;
			Config.readconfiguration();
			//DBConnect.Connect();
			String className = new Exception().getStackTrace()[2].getClassName();
			if (className.equals("sun.reflect.NativeMethodAccessorImpl"))
				className = new Exception().getStackTrace()[1].getClassName();

			if (Config.browser.equals("firefox")) {
				logger.log(Level.INFO, "Initializing Firefox WebDriver Profile for class " + className);
				FirefoxProfile profile = new FirefoxProfile();
				profile.setPreference("app.update.auto", false);
				profile.setPreference("app.update.enabled", false);
				if (flashplugin == false)
					profile.setPreference("plugin.state.flash", 0);
				else
					profile.setPreference("plugin.state.flash", 2);
				driver = new FirefoxDriver(profile);
				logger.log(Level.INFO, "WebDriver Profile Initialized");
				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
				driver.manage().timeouts().setScriptTimeout(300, TimeUnit.SECONDS);
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
			}


			if (Config.platformName.equalsIgnoreCase("iOS")) {
				try {
					DesiredCapabilities capabilities = new DesiredCapabilities();
					capabilities.setCapability("deviceName", Config.deviceName);
					capabilities.setCapability("platformName", Config.platformName);
					capabilities.setCapability("platformVersion", Config.platformVersion);
					capabilities.setCapability("udid", Config.udId);
					capabilities.setCapability("bundleid", Config.bundleId);
					File app = new File(Config.nativeAppPath);
					capabilities.setCapability("app", app.getAbsolutePath());
					appiumDriver = new IOSDriver(new URL(Config.serverAddress + ":" + Config.portAddress + "/wd/hub"), capabilities);//instantiate driver with URl 'http://0.0.0.0:4723/wd/hub'

						logger.log(Level.INFO, "WebDriver Profile Initialized");
					appiumDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					appiumDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
					appiumDriver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
					appiumDriver.manage().window().maximize();
					dashBoard = PageFactory.initElements(appiumDriver, Dashboard_appiumPF.class);
					homePage = PageFactory.initElements(appiumDriver, HomePage_appium.class);
					loginPage = PageFactory.initElements(appiumDriver, LoginPage_appium.class);
					randomLoginPage = PageFactory.initElements(appiumDriver, RandomLoginPage_appium.class);
					assignments = PageFactory.initElements(appiumDriver, Assignments_appium.class);
					eTextbook = PageFactory.initElements(appiumDriver, EtextBook_appium.class);
					courseStreamAppium = PageFactory.initElements(appiumDriver, CourseStream_appium.class);
					}catch(Exception e){
					Assert.fail("Exception in part",e);
				}
			}

		}catch(Exception e){
			Assert.fail("Exception in the method 'startdrver'", e);
		}

	    return driver;
	  }

	  public void firefoxDriverStart() throws Exception
	  {
		  if(!Config.browser.equals("firefox"))
		  {
			  Driver.driver.quit(); //Quitting existing driver
			  logger.log(Level.INFO, "Initializing Firefox WebDriver Profile");
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("app.update.auto", false);
			profile.setPreference("app.update.enabled", false);
			if(flashplugin == false)
			profile.setPreference("plugin.state.flash", 0);
			else
				profile.setPreference("plugin.state.flash", 1);
			driver = new FirefoxDriver(profile);
			Driver.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            Driver.driver.manage().timeouts().pageLoadTimeout(120,TimeUnit.SECONDS);
            Driver.driver.manage().timeouts().setScriptTimeout(120,TimeUnit.SECONDS);
		    Driver.driver.manage().deleteAllCookies();
			Driver.driver.manage().window().maximize();
		  }
	  }


    public void closeHelp()
    {
        try
        {
            if(driver.findElements(By.className("swhelp-button-cancel")).size() > 0)
                driver.findElement(By.className("swhelp-button-cancel")).click();
        }
        catch (Exception e)
        {
            Assert.fail("Exception while closing help", e);
        }
    }
}
