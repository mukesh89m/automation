package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelperAppium;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import org.testng.Assert;

/**
 * Created by Snapwiz on 26/08/15.
 */
public class Tap_appium extends Driver {

    public void tapMainNavigator() {
        try {
            Thread.sleep(2000);
            appiumDriver.tap(1, 6, 8, 1);
            Thread.sleep(2000);
        } catch (Exception e) {
            Assert.fail("Exception in class 'Tap_appium' in the appHelper 'tapMainNavigator'", e);
        }
    }


    public void tapQuitDiagTest() {
        try {
            appiumDriver.tap(1, 684, 121, 1);
        } catch (Exception e) {
            Assert.fail("Exception in class 'Tap_appium' in the appHelper 'tapQuitDiagTest'", e);
        }
    }


    public void tapConfidenceLevel(int pos) {
        try {
            if(pos==1) {
                appiumDriver.tap(1, 484, 256, 1);
            }
        } catch (Exception e) {
            Assert.fail("Exception in class 'Tap_appium' in the appHelper 'tapConfidenceLevel'", e);
        }
    }

    public void tapReportContentErrorIcon() {
        try {
                appiumDriver.tap(1, 719, 740, 1);


        } catch (Exception e) {
            Assert.fail("Exception in class 'Tap_appium' in the appHelper 'tapReportContentErrorIcon'", e);
        }
    }

    public void tapOnQuestionCard() {
        try {
            appiumDriver.tap(1, 757, 119, 1);

        } catch (Exception e) {
            Assert.fail("Exception in class 'Tap_appium' in the appHelper 'tapOnQuestionCard'", e);
        }
    }

    public void tapOnHelpIcon() {
        try {
            appiumDriver.tap(1, 795, 175, 1);

        } catch (Exception e) {
            Assert.fail("Exception in class 'Tap_appium' in the appHelper 'tapOnQuestionCard'", e);
        }
    }

    public void tapOnplusIcon() {
        try {
            appiumDriver.tap(1, 695, 238, 1);

        } catch (Exception e) {
            Assert.fail("Exception in class 'Tap_appium' in the appHelper 'tapOnQuestionCard'", e);
        }
    }

}
