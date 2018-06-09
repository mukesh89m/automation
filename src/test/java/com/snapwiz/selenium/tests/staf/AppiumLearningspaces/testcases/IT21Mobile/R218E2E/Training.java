package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.testcases.IT21Mobile.R218E2E;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Config;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelperAppium.Tap_appium;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.pageFactoryAppium.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * Created by Snapwiz on 02/09/15.
 */
public class Training{
    public static AppiumDriver appiumDriver;

    @Test
    public void test1(){
        try{
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("deviceName", "iPad 2");
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("platformVersion", "8.3");
            capabilities.setCapability(CapabilityType.BROWSER_NAME, "safari");
            appiumDriver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);//instantiate driver
            appiumDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            appiumDriver.manage().timeouts().pageLoadTimeout(300, TimeUnit.SECONDS);
            appiumDriver.manage().timeouts().setScriptTimeout(300, TimeUnit.SECONDS);
            appiumDriver.manage().deleteAllCookies();
            appiumDriver.manage().window().maximize();
            System.out.println("Its LS web app");
            appiumDriver.get("https://www.google.co.in");
            WebElement serachField = appiumDriver.findElement(By.name("q"));
            serachField.sendKeys("Pooja Shah Selenium youtube");
            serachField.sendKeys(Keys.ENTER);
            System.out.println(appiumDriver.getCurrentUrl());
        }catch(Exception e){
            Assert.fail("Exception",e);
        }
    }



    @Test
    public void test2(){
        try{
            try {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("deviceName", "iPad 2");
                capabilities.setCapability("platformName", "iOS");
                capabilities.setCapability("platformVersion", "8.3");
                capabilities.setCapability("udid", "cf99c8794de4caf1475ed1f44beab57a879902dd");
                capabilities.setCapability("bundleid", "com.snapwiz.learningspaces");
                File app = new File("/Users/snapwizmacmini2/Library/Developer/Xcode/DerivedData/learningspaces-gvpolbslgllaluekpitjlyqifrbw/Build/Products/Debug-iphoneos/learningspaces.app");
                capabilities.setCapability("app", app.getAbsolutePath());
                appiumDriver = new IOSDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);//instantiate driver with URl 'http://0.0.0.0:4723/wd/hub'
                appiumDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
                appiumDriver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
                appiumDriver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
                appiumDriver.manage().window().maximize();
                //appiumDriver.findElement(By.xpath("//UIAApplication[1]/UIAWindow[4]/UIAAlert[1]/UIACollectionView[1]/UIACollectionCell[5]")).click();
                appiumDriver.findElement(By.xpath("//UIACollectionCell[@name='student1']")).click();

                Thread.sleep(9000);
                List<WebElement> elementsLIst = appiumDriver.findElements(By.xpath("//UIAImage"));
                System.out.println("Ele Size : " +elementsLIst.size() );
                elementsLIst.get(2).click();


                new Tap_appium().tapMainNavigator();
            }catch(Exception e){
                Assert.fail("Exception",e);
            }


            /*dashBoard = PageFactory.initElements(appiumDriver, Dashboard_appiumPF.class);
            homePage = PageFactory.initElements(appiumDriver, HomePage_appium.class);
            loginPage = PageFactory.initElements(appiumDriver, LoginPage_appium.class);
            randomLoginPage = PageFactory.initElements(appiumDriver, RandomLoginPage_appium.class);
            assignments = PageFactory.initElements(appiumDriver, Assignments_appium.class);
            eTextbook = PageFactory.initElements(appiumDriver, EtextBook_appium.class);*/

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
