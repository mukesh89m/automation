package com.snapwiz.selenium.tests.staf.learningspaces.uihelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class BooleanValue extends Driver {
    public boolean  booleanbyid(String id)
    {
        boolean text=false;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            text=driver.findElement(By.id(id)).isDisplayed();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in  apphelper booleanbyid ",e);
        }
        return text;
    }


    public boolean  verifyNotElementPresentByXpath(String xpath)
    {
        boolean text=false;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            if(driver.findElements(By.xpath(xpath)).size()==0){
                text = true;
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in  apphelper verifyElementPresent ",e);
        }
        System.out.println("text Value : " + text);

        return text;
    }



    public boolean  booleanbyclass(String classname)
    {
        boolean text=false;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            text=driver.findElement(By.className(classname)).isDisplayed();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in  apphelper booleanbyclass ",e);
        }
        return text;
    }

    public boolean  booleanbycssselector(String cssselector)
    {
        boolean text=false;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            text=driver.findElement(By.cssSelector(cssselector)).isDisplayed();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in  apphelper booleanbycssselector ",e);
        }
        return text;
    }

    public boolean  booleanbylistid(String id,int index)
    {
        boolean text=false;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            List<WebElement> alllist=driver.findElements(By.id(id));
            text=alllist.get(index).isDisplayed();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in  apphelper booleanbylistid ",e);
        }
        return text;
    }

    public boolean booleanbylistclass(String classname,int index)
    {
        boolean text=false;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            List<WebElement> alllist=driver.findElements(By.className(classname));
            text=alllist.get(index).isDisplayed();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in  apphelper booleanbylistclass ",e);
        }
        return text;
    }

    public boolean booleanbylistcssselector(String cssselector,int index)
    {
        boolean text=false;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            List<WebElement> alllist=driver.findElements(By.cssSelector(cssselector));
            text=alllist.get(index).isDisplayed();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in  apphelper booleanbylistcssselector ",e);
        }
        return text;
    }
    public boolean presenceOfElement(int dataIndex,By by)
    {
        boolean elementFound = false;
        WebDriver driver=Driver.getWebDriver();
        try{
            driver.findElement(by);
            elementFound = true;
        }
        catch (Exception e){
            //empty catch block
        }
        return elementFound;
    }
    public boolean isElementPresent(WebElement webElement)
    {
        boolean elementFound = false;
        try{
            webElement.isDisplayed();
            elementFound = true;
        }
        catch (Exception e){
            //empty catch block
        }
        return elementFound;
    }
}
