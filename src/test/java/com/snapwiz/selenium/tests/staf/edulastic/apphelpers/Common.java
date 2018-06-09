package com.snapwiz.selenium.tests.staf.edulastic.apphelpers;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Murthi on 11/29/2016.
 */
public class Common extends Driver{

    /**
     * wait for overlay to disappears with the time span
     * @param timeout
     * @throws InterruptedException
     */
    public void waitForUIBlockerToClose(int timeout) throws InterruptedException{
        WebDriver driver=Driver.getWebDriver();
        int count=0;
        while(driver.findElements(By.cssSelector("div.blockUI.blockOverlay")).size()>0){
            if(count<timeout) {
                Thread.sleep(1000);
                System.out.println("overlay present");
                count++;
            }
            else break;
        }
    }

    public void waitForUIBlockerToClose(WebDriver driver, int timeout) throws InterruptedException{
        int count=0;
        while(driver.findElements(By.cssSelector("div.blockUI.blockOverlay")).size()>0){
            if(count<timeout) {
                Thread.sleep(1000);
                System.out.println("overlay present");
                count++;
            }
            else break;
        }
    }
    public void waitForToastBlockerToClose(int timeout){
        WebDriverUtil.waitTillInvisibilityOfElement(By.id("toast-container"), timeout);
    }

}
