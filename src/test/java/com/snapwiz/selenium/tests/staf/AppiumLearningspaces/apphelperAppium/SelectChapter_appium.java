package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelperAppium;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/**
 * Created by Snapwiz on 24/08/15.
 */
public class SelectChapter_appium extends Driver {
    public void selectChapter (int chapterNumber){
        try{
            Thread.sleep(5000);
            //appiumDriver.findElement(By.xpath("//UIALink[@name = 'Chapter 4 Cell Structure']"));
            //appiumDriver.findElement(By.xpath("//UIALink[contains(@name,'Chapter "+chapterNumber+"')]")).click();
            List<WebElement> chaptersELementsList = appiumDriver.findElements(By.xpath("//UIAStaticText[@name ='Chapter']"));
            chaptersELementsList.get(chapterNumber-1).click();

        }catch(Exception e){
            Assert.fail("Exception in apphelper 'selectChapter' in class 'SelectChapter_appium' ",e);
        }
    }
}
