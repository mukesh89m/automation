package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

/**
 * Created by root on 7/2/15.
 */
public class ResponsePage extends Driver{
    public void openViewResponsePage (){
        try{
            MouseHover.mouserhover("idb-gradebook-question-content");
            new UIElement().waitAndFindElement(By.className("ls-view-response-link"));

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link")));

        }catch(Exception e){
            Assert.fail("Exception in the test method 'openViewResponsePage' in the class 'ResponsePage'",e);
        }
    }
}
