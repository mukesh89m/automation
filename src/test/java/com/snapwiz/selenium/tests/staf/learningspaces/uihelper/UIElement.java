package com.snapwiz.selenium.tests.staf.learningspaces.uihelper;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


public class UIElement extends Driver
{

    public WebElement waitAndFindElement(final By by) {
        WebDriver driver=Driver.getWebDriver();

        WebElement element =  new WebDriverWait(driver,120)
                .until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver driver) {
                        return driver.findElement(by);
                    }
                });
        return element;
    }

    public void waitTillInvisibleElement(final By by) {
        WebDriver driver=Driver.getWebDriver();
        new WebDriverWait(driver,120).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }
    public WebElement waitAndFindElement(final WebElement element)
    {
        WebDriver driver=Driver.getWebDriver();
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
            WebDriver driver=Driver.getWebDriver();
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
