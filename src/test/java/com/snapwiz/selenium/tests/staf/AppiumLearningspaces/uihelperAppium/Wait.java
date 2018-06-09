package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelperAppium;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Created by Snapwiz on 03/09/15.
 */
public class Wait extends Driver{
    public void visibilityOf(WebElement element){
        try {
            WebDriverWait wait = new WebDriverWait(appiumDriver, 180);
            wait.until(ExpectedConditions.visibilityOf(element));
        }catch(Exception e){
            Assert.fail("Exception in uihelperAppium 'visibilityOf' in the class 'Wait'",e);
        }
    }

    public void presenceOfElementLocated(By by){
        try {
            WebDriverWait wait = new WebDriverWait(appiumDriver, 180);
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        }catch(Exception e){
            Assert.fail("Exception in uihelperAppium 'presenceOfElementLocated' in the class 'Wait'",e);
        }
    }
}
