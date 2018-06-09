package com.snapwiz.selenium.tests.staf.learningspaces.testcases.jenkins.prac;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by mukesh on 22/7/16.
 */
public class ConfigureDriver {

    public static Map<Long, WebDriver> webDriverMap = new HashMap<>();

    @BeforeMethod
    @Parameters("myBrowser")
    public void beforeClass(String myBrowser) throws MalformedURLException {
        RemoteWebDriver driver=null;
        if(myBrowser.equals("chrome")){
            DesiredCapabilities capability = new DesiredCapabilities().chrome();
            capability.setBrowserName("chrome");
            capability.setPlatform(Platform.WINDOWS);
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
        }
        else if(myBrowser.equals("firefox")){
            System.out.println("inside firefox");
            DesiredCapabilities capability = new DesiredCapabilities().firefox();
            capability.setBrowserName("firefox");
            capability.setPlatform(Platform.LINUX);
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capability);
        }

        webDriverMap.put(Thread.currentThread().getId(), driver);
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    public static WebDriver getDriver() {
        return webDriverMap.get(Thread.currentThread().getId());
    }


    @AfterClass
    public void afterClass(){
       /* getDriver().quit();
        remoteWebDriverThreadLocal.set(null);
*/
    }
}
