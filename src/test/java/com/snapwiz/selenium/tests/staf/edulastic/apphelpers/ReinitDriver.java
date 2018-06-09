package com.snapwiz.selenium.tests.staf.edulastic.apphelpers;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by pragyas on 02-02-2016.
 */
public class ReinitDriver extends Driver {

    public static String workingDir = System.getProperty("user.dir");
    public static Boolean flashplugin;
    public static Logger logger = Logger.getLogger(Driver.class.getName());
    private String browser;
    private String chromeBinaryPath;



    public WebDriver startDriver(WebDriver webDriver) throws Exception {
        if (flashplugin == null)
            flashplugin = true;
        Config.readconfiguration();
        DBConnect.Connect();
        String className = new Exception().getStackTrace()[2].getClassName();
        if (className.equals("sun.reflect.NativeMethodAccessorImpl"))
            className = new Exception().getStackTrace()[1].getClassName();
        if (Config.browser.equals("firefox") && (Config.platform.equals("linux") || Config.platform.equals("windows"))) {
            logger.log(Level.INFO, "Initializing Firefox WebDriver Profile for class " + className);
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("app.update.auto", false);
            profile.setPreference("app.update.enabled", false);
            if (flashplugin == false)
                profile.setPreference("plugin.state.flash", 0);
            else
                profile.setPreference("plugin.state.flash", 1);
            webDriver = new FirefoxDriver(profile);
        }
        if (Config.browser.equals("ie") && (Config.platform.equals("linux") || Config.platform.equals("windows"))) {
            logger.log(Level.INFO, "Initializing IE WebDriver Profile for class " + className);
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, false);

            File file = new File(workingDir + "/src/test/java/com/snapwiz/selenium/tests/staf/drivers/IEDriverServer.exe");
            System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
            webDriver = new InternetExplorerDriver(capabilities);
        }
        if (Config.browser.equals("chrome") && (Config.platform.equals("linux") || Config.platform.equals("windows"))) {
            logger.log(Level.INFO, "Initializing chrome WebDriver Profile for class " + className);
            ChromeDriverService service = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File(workingDir + "/src/test/java/com/snapwiz/selenium/tests/staf/drivers/chromedriver.exe"))
                    .usingAnyFreePort()
                    .build();
            service.start();
            webDriver = new RemoteWebDriver(service.getUrl(),
                    DesiredCapabilities.chrome());
        }
        if (Config.platform.equals("android")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("platformVersion", "4.4.2");
            capabilities.setCapability("deviceName", "Snapwiz (SM-T530NU)");
            capabilities.setCapability("browserName", "Chrome");
            //driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        }
        logger.log(Level.INFO, "WebDriver Profile Initialized");
        if (!(Config.platform.equals("android") || Config.platform.equals("ios"))) {
            webDriver.manage().window().maximize();
            webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else
            webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        webDriver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(300, TimeUnit.SECONDS);
        webDriver.manage().deleteAllCookies();
        //Adding mixpanel opt out cookie
        webDriver.get("https://.mixpanel.com/404");
        Cookie cookie = new Cookie("mp_optout", "1", ".mixpanel.com", "/", null);
        webDriver.manage().addCookie(cookie);
        return webDriver;

    }

    public WebDriver initChromeDriver() throws Exception {
        String architecture = System.getProperty("os.arch");
        String os = System.getProperty("os.name").toLowerCase();
        String zipName = "chromedriver_";
        if (os.contains("mac")) {
            zipName += "mac32.zip";
        } else if (os.contains("window")) {
            zipName += "win32.zip";
        } else {
            if (architecture.contains("_64")) {
                zipName = "linux64.zip";
            } else {     //32 bit architecture
                zipName = "linux32.zip";
            }
        }
        ClassLoader classLoader = getClass().getClassLoader();
        File zipFile = new File(classLoader.getResource(zipName).getFile());
        chromeBinaryPath = unZipDriver(zipFile);

        File driverExecutable = new File(chromeBinaryPath);
        driverExecutable.setExecutable(true);
        System.setProperty("webdriver.chrome.driver", chromeBinaryPath);
        ChromeDriver chromeDriver = new ChromeDriver();

        if (!(Config.platform.equals("android") || Config.platform.equals("ios"))) {
            chromeDriver.manage().window().maximize();
            chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else
            chromeDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return chromeDriver;
    }

    @Parameters("browser")
    public WebDriver startDriver(@Optional("firefox") String browser) throws Exception {
        WebDriver driver=null;
        this.browser = browser;
        Config.readconfiguration();
        DBConnect.Connect();
        if (browser.equals("chrome")) {
            driver = initChromeDriver();
        } else {
            driver = initFirefoxDriver();
        }

        webDriverMap.put(Thread.currentThread().getId(), driver);
        return driver;
    }

    public WebDriver initFirefoxDriver() throws Exception {
        WebDriver driver=null;
        if (flashplugin == null)
            flashplugin = true;

        String className = new Exception().getStackTrace()[2].getClassName();
        if (className.equals("sun.reflect.NativeMethodAccessorImpl"))
            className = new Exception().getStackTrace()[1].getClassName();
        if (Config.browser.equals("firefox") && (Config.platform.equals("linux") || Config.platform.equals("windows"))) {
            logger.log(Level.INFO, "Initializing Firefox WebDriver Profile for class " + className);


            // FirefoxProfile profile = new FirefoxProfile(new File("/root/.mozilla/firefox/QaAutomationFFProfile"));
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("app.update.auto", false);
            profile.setPreference("app.update.enabled", false);
            if (flashplugin == false)
                profile.setPreference("plugin.state.flash", 0);
            else
                profile.setPreference("plugin.state.flash", 1);
            driver = new FirefoxDriver(profile);
        }

        if (Config.browser.equals("ie") && (Config.platform.equals("linux") || Config.platform.equals("windows"))) {
            logger.log(Level.INFO, "Initializing IE WebDriver Profile for class " + className);
            DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
            capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, false);

            File file = new File(workingDir + "/src/test/java/com/snapwiz/selenium/tests/staf/drivers/IEDriverServer.exe");
            System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
            driver = new InternetExplorerDriver(capabilities);
        }
        if (Config.browser.equals("chrome") && (Config.platform.equals("linux") || Config.platform.equals("windows"))) {
            logger.log(Level.INFO, "Initializing chrome WebDriver Profile for class " + className);
            ChromeDriverService service = new ChromeDriverService.Builder()
                    .usingDriverExecutable(new File(workingDir + "/src/test/java/com/snapwiz/selenium/tests/staf/drivers/chromedriver.exe"))
                    .usingAnyFreePort()
                    .build();
            service.start();
            driver = new RemoteWebDriver(service.getUrl(),
                    DesiredCapabilities.chrome());
        }
        if (Config.platform.equals("android")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("platformVersion", "4.4.2");
            capabilities.setCapability("deviceName", "Snapwiz (SM-T530NU)");
            capabilities.setCapability("browserName", "Chrome");
            //driver = new AppiumDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        }
        logger.log(Level.INFO, "WebDriver Profile Initialized");
        if (!(Config.platform.equals("android") || Config.platform.equals("ios"))) {
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } else
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(300, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        //Adding mixpanel opt out cookie
        driver.get("https://.mixpanel.com/404");
        Cookie cookie = new Cookie("mp_optout", "1", ".mixpanel.com", "/", null);
        driver.manage().addCookie(cookie);
        return driver;
    }

    public WebDriver reInitDriver() throws Exception {
        return startDriver(this.browser);
    }

    public String unZipDriver(File zipFile) {
        String absFilePath = null;
        byte[] buffer = new byte[1024];
        try {

            //get the zip file content
            ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
            //get the zipped file list entry
            ZipEntry ze = zis.getNextEntry();

            String fileName = ze.getName();
            File newFile = new File(System.currentTimeMillis() + fileName);

            absFilePath = newFile.getPath();

            FileOutputStream fos = new FileOutputStream(newFile);

            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }

            fos.close();

            zis.closeEntry();
            zis.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return absFilePath;
    }
}
