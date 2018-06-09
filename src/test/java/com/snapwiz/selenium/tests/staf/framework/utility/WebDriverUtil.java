package com.snapwiz.selenium.tests.staf.framework.utility;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

/**
 * Created by murthi on 07-01-2016.
 */
public class WebDriverUtil {

    /**
     * This method will launch the browser with base URL.
     */
    public static void launchURL(){
        WebDriver driver=Driver.getWebDriver();
        String baseURL=Properties.getPropertyValue("BaseURL");
        baseURL=baseURL.trim().startsWith("http://")?baseURL:"http://"+baseURL;
        if(driver!=null)
            driver.get(baseURL);
        else
            System.out.println("driver is not initialized yet.");
    }

    /**
     * This will execute the javascript command using driver
     * @param script -- this is any javascript command
     * @return String -- if the javascript contain any return value
     */
    public static String executeJavascript(String script){
        WebDriver driver=Driver.getWebDriver();
        return (String) ((JavascriptExecutor) driver).executeScript(script);
    }

    /**
     * This will execute the javascript command on objects using driver
     * @param script -- this is any javascript command
     * @param objects -- Any objects
     * @return String -- if the javascript contain any return value
     * example executeJavascript("arguments[0].click();", element);
     */
    public static String executeJavascript(String script,Object... objects){
        WebDriver driver=Driver.getWebDriver();
        return (String) ((JavascriptExecutor) driver).executeScript(script, objects);
    }

    /**
     * This will click on element using javascript executor
     * @param element -- element to be clicked
     */
    public static void clickOnElementUsingJavascript(WebElement element)
    {
        WebDriver driver=Driver.getWebDriver();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",element);
    }

    /**
     * This will enter value to element using javascript executor
     * @param element
     */
    public static void enterValueUsingJavascript(WebElement element,String keyToSend)
    {
        WebDriver driver=Driver.getWebDriver();
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='"+keyToSend+"';",element);
    }

    /**
     * This will wait the element to be visible within the timeout otherwise throw timeout exception
     * @param element -- element to be visible
     * @param timeout -- time to wait
     */
    public static void waitTillVisibilityOfElement(WebElement element,int timeout) {
        WebDriver driver = Driver.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * This will wait the element to be invisible within the timeout otherwise throw timeout exception
     * @param element -- element to be invisible
     * @param timeout -- time to wait
     */
    public static void waitTillInvisibilityOfElement(By element,int timeout) {
        WebDriver driver = Driver.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
    }

    /**
     * This will wait the element to be clickable within the timeout otherwise throw timeout exception
     * @param element -- element to be clickable
     * @param timeout -- time to wait
     */
    public static void waitTillElementToBeClickable(WebElement element,int timeout) {
        WebDriver driver = Driver.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * This will wait the element to be visible within the timeout otherwise throw timeout exception
     * @param element -- An expectation for checking if the given text is present in the specified element
     * @Sting -- to be present in the element
     * @param timeout -- time to wait
     */
    public static boolean waitUntilTextToBePresentInElement(WebElement element,String text,int timeout) {
        WebDriver driver = Driver.getWebDriver();
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        return wait.until(ExpectedConditions.textToBePresentInElement(element,text));
    }
    /**
     * This method selects a item from list by visible text
     * @param webElement -- element type of select
     * @param text -- text to be selected
     */
    public static void selectItemByVisibleText(WebElement webElement,String text){

        Select select = new Select(webElement);
        select.selectByVisibleText(text);

    }

    /**
     * This method selects a item from list by index
     * @param webElement -- element type of select
     * @param index -- the index value of a item to be selected
     */
    public static void selectItemByIndex(WebElement webElement,int index) {

        Select select = new Select(webElement);
        select.selectByIndex(index);

    }

     /**
     * This method selects a item from list by its value
     * @param webElement -- element type of select
     * @param value the value of a item to be selected
     */
    public static void selectByValue(WebElement webElement,String value){

        Select select = new Select(webElement);
        select.selectByValue(value);

    }

    /**
     * This method get the first select item as a string from a list
     * @param webElement -- element type of list
     * @return String -- first selected item value as string
     */
    public static String getFirstSelectItem(WebElement webElement){
        Select select = new Select(webElement);
        return select.getFirstSelectedOption().getText();
    }



    /**
     *This selects the link text in dropdown based on the default selection
     * @param defaultSelection - The link text which is already selected by default in dropdown
     * @param textToBeSelected - The link text which should be selected dropdown
     */
    public static void selectDropdownValue(String defaultSelection,String textToBeSelected)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            driver.findElement(By.linkText(defaultSelection)).click();
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.linkText(textToBeSelected)),20);
            driver.findElement(By.linkText(textToBeSelected)).click();
            waitForAjax(driver,20);
            ReportUtil.log("Selecting dropdown Value", "Selected the dropdown value '"+textToBeSelected+"'", "info");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in UI helper selectDropdownValue", e);
        }
    }

    /**
     * This helper method mouse over on a particular element based on the By class input
     * @param by - (Ex - By.id("")
     */
    public static void mouseHover(By by)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            Actions action = new Actions(driver);
            WebElement we = driver.findElement(by);
            action.moveToElement(we).build().perform();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in method mouseHover in WebDriverUtil.java",e);
        }
    }
    /**
     * This helper method mouse over on a particular element based on the By class input
     * @param element - (Ex - By.id("")
     */
    public static void mouseHover(WebElement element)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            Actions action = new Actions(driver);
            action.moveToElement(element).build().perform();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in method mouseHover in WebDriverUtil.java",e);
        }
    }


    public static ExpectedCondition<Boolean> waitForPageLoadUsingJS(){
        return new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                try {
                    Object readyState= ((JavascriptExecutor) driver).executeScript("return document.readyState;");
                    return readyState.toString().equalsIgnoreCase("complete");
                }catch(UnreachableBrowserException e){
                    return null;
                }
            }
        };
    }

    /**
     * This will make the element to be visible on page with top or bottom alignment
     * @param webElement -- element to be scrolled
     * @param alignToTop -- align to top or not
     */
    public static void scrollIntoView(WebElement webElement,boolean alignToTop){
        WebDriver driver=Driver.getWebDriver();
        if(alignToTop)
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
        else
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", webElement);
    }
    public static void switchToNewWindow(){
        try {
            WebDriver driver=Driver.getWebDriver();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
        } catch (Exception e) {
            Assert.fail("Exception in method switchToWindow in WebDriverUtil.java", e);
        }

    }

    /**
     * This function will wait for ajax calls to finish
     * @author MurthiS
     * @param driver
     * @param timeoutInSeconds
     */
    public static void waitForAjax(WebDriver driver,int timeoutInSeconds)  {
        try {
            if (driver instanceof JavascriptExecutor) {
                JavascriptExecutor jsDriver = (JavascriptExecutor)driver;

                for (int i = 0; i< timeoutInSeconds; i++)
                {
                    Object numberOfAjaxConnections = jsDriver.executeScript("return jQuery.active");
                    // return should be a number
                    if (numberOfAjaxConnections instanceof Long) {
                        Long n = (Long)numberOfAjaxConnections;
                        System.out.println("Number of active jquery ajax calls: " + n);
                        if (n.longValue() == 0L)
                            break;
                    }
                    Thread.sleep(1000);
                }
            }
            else {
                System.out.println("Web driver: " + driver + " cannot execute javascript");
            }
        }
        catch (Exception e) {
        }
    }

    /**
     * This function  check for presence of element
     * @author Mukesh
     * @param listElement
     */
    public static boolean isElementPresent(List<WebElement> listElement) {
        System.out.println("size:"+listElement.size());
        if (listElement.size() != 0)
            return true;
        else
            return false;
    }

    /**
     * This function will return the hexa value of color
     * @author Mukesh
     * @param element
     */

    public static String verifyColor(WebElement element) {
        String color = element.getCssValue("color");
        Color col = Color.fromString(color);
        String hexValue = col.asHex();
        return hexValue;
    }


    /**
     * This function will return the hexa value of background color
     * @author Mukesh
     * @param element
     */
    public static String verifyBgColor(WebElement element) {
        String color = element.getCssValue("background-color");
        Color col = Color.fromString(color);
        String hexValue = col.asHex();
        return hexValue;
    }

    /**
     *  isElementPresent is an internal /private method that checks whether an element is present using JS
     * @author : Mukesh
     * @param : by
     * @return : returns true if operation is successfully,and false if operation fails
     */
    public static boolean isElementPresent(By by)
    {
        Boolean elementFound = Boolean.FALSE;
        WebDriver driver=Driver.getWebDriver();
        try{
            driver.findElement(by);
            elementFound = Boolean.TRUE;
        }
        catch (Exception e){
          elementFound=Boolean.FALSE;
        }
        return elementFound;
    }

}
