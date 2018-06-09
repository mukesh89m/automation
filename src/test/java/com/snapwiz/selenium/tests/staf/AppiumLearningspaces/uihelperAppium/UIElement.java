package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelperAppium;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UIElement extends Driver
{

    public WebElement waitAndFindElement(final By by) {

        WebElement element =  new WebDriverWait(driver,180)
                .until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver driver) {
                        return appiumDriver.findElement(by);
                    }
                });
        return element;
    }

    public void waitTillInvisibleElement(final By by) {
        new WebDriverWait(driver,30).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
    public WebElement waitAndFindElement(final WebElement element)
    {
        WebElement element1 =  new WebDriverWait(driver,120)
                    .until(new ExpectedCondition<WebElement>() {
                        @Override
                        public WebElement apply(WebDriver driver) {
                            return element;
                        }
                    });
            return element1;

    }

    public boolean waitAndFindElementWithoutAssert(final By element)
    {
        try {
           new WebDriverWait(driver, 120)
                    .until(new ExpectedCondition<WebElement>() {
                        @Override
                        public WebElement apply(WebDriver driver) {
                            return driver.findElement(element);
                        }
                    });
            return true;
        }
        catch (Exception e) {
            return false;
        }


    }
}
