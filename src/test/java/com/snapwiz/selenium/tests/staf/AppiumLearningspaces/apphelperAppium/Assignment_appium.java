package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelperAppium;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Created by Snapwiz on 01/09/15.
 */
public class Assignment_appium extends Driver{
    public void startAssignmentbyStudent(String dataIndex, String assessmentName){
        try{
            new Navigator_appium().navigateTo("Assignments");
            WebDriverWait wait = new WebDriverWait(appiumDriver,30);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//UIAStaticText[@name = '(shor) " + assessmentName + "']")));
            appiumDriver.findElement(By.xpath("//UIAStaticText[@name = '(shor) " + assessmentName+"']")).click();
        }catch(Exception e){
            Assert.fail("Exception in apphelperAppium 'startAssignmentbyStudent' in class 'Assignment_appium.java'",e);
        }
    }
}
