package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import org.testng.Assert;

/**
 * Created by Snapwiz on 19/08/15.
 */
public class Tap extends Driver{
    public void tapMainNavigator() {
        try {
            appiumDriver.tap(1, 6, 8, 1);
        } catch (Exception e) {
            Assert.fail("Exception in class 'Tap' in the appHelper 'tapMainNavigator'", e);
        }
    }
}
