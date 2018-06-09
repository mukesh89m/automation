package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelperAppium;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import org.openqa.selenium.By;
import org.testng.Assert;

/**
 * Created by Snapwiz on 17/08/15.
 */
public class TouchActions extends Driver{


    public void longPressByClassName(String className){
        try {
            io.appium.java_client.TouchAction action = new io.appium.java_client.TouchAction(appiumDriver);
            action.longPress(appiumDriver.findElement(By.className(className)),60).release().perform();
        }catch(Exception e){
            Assert.fail("Exception in the uihelper 'longPress' in the class 'TouchActions'",e);
        }
    }
}
