package com.snapwiz.selenium.tests.staf.orion.uihelper;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by root on 8/12/15.
 */
public class UIElement {
    public WebDriver driver= Driver.getWebDriver();
    public WebElement waitAndFindElement(final By by) {

        WebElement element = new WebDriverWait(driver, 120)
                .until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver driver) {
                        return driver.findElement(by);
                    }
                });
        return element;
    }

    public void waitTillVisibleElement(final By by) {
        new WebDriverWait(driver, 120).until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void waitTillInvisibleElement(final By by) {
        new WebDriverWait(driver, 120).until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public WebElement waitAndFindElement(final WebElement element) {
        WebElement element1 = new WebDriverWait(driver, 120)
                .until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver driver) {
                        return element;
                    }
                });
        return element1;

    }

    public boolean waitAndFindElementWithoutAssert(final By element) {
        try {
            new WebDriverWait(driver, 120)
                    .until(new ExpectedCondition<WebElement>() {
                        @Override
                        public WebElement apply(WebDriver driver) {
                            return driver.findElement(element);
                        }
                    });
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}