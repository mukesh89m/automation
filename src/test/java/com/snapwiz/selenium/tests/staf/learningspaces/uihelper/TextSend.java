package com.snapwiz.selenium.tests.staf.learningspaces.uihelper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class TextSend extends Driver{
    public void textsendbyclass(String text,String classname)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            driver.findElement(By.className(classname)).clear();
            driver.findElement(By.className(classname)).sendKeys(text);
        }
        catch(Exception e)
        {
            Assert.fail("exceptionm in app heleper method textsendbyclass",e);
        }
    }
    public void textSendByXpath(String text,String xpath)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            driver.findElement(By.xpath(xpath)).clear();
            driver.findElement(By.xpath(xpath)).sendKeys(text);
        }
        catch(Exception e)
        {
            Assert.fail("exceptionm in app heleper method textsendbyclass",e);
        }
    }

    public void textsendbyid(String text,String id)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            driver.findElement(By.id(id)).clear();
            driver.findElement(By.id(id)).sendKeys(text);
        }
        catch(Exception e)
        {
            Assert.fail("exceptionm in app heleper method textsendbyid",e);
        }
    }


    public void textSendByWebElement(String text,WebElement element)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            element.clear();
            element.sendKeys(text);
        }
        catch(Exception e)
        {
            Assert.fail("exceptionm in app heleper method textSendByWebElement",e);
        }
    }

    public void textsendbycssSelector(String text,String cssselector)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            driver.findElement(By.cssSelector(cssselector)).clear();
            driver.findElement(By.cssSelector(cssselector)).sendKeys(text);
        }
        catch(Exception e)
        {
            Assert.fail("exceptionm in app heleper method textsendbycssSelector",e);
        }
    }

    public void textsendbyclasslist(String text,String classname,int index)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            List<WebElement> we=driver.findElements(By.className(classname));
            we.get(index).clear();
            we.get(index).sendKeys(text);
        }
        catch(Exception e)
        {
            Assert.fail("exceptionm in app heleper method textsendbyclass",e);
        }
    }



}
