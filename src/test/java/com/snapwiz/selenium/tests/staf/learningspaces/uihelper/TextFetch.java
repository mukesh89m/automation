package com.snapwiz.selenium.tests.staf.learningspaces.uihelper;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;

public class TextFetch extends Driver
{
    public String textfetchbyid(String id)
    {
        String text=null;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            text=driver.findElement(By.id(id)).getText();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in  apphelper textfetchbyid ",e);
        }
        return text;
    }


    public String textFetchByXPathWithAttribute(String xpath, String attributeName)
    {
        String text=null;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            text=driver.findElement(By.xpath(xpath)).getAttribute(attributeName);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in  apphelper textfetchbyXPathAttribute ",e);
        }
        return text;
    }



    public String textFetchByWebElementWithAttribute(WebElement element, String attributeName)
    {
        String text=null;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            text=element.getAttribute(attributeName);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in  apphelper textFetchByWebElementWithAttribute ",e);
        }
        return text;
    }

    public String textfetchbyxpath(String xpath)
    {
        String text=null;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            text=driver.findElement(By.xpath(xpath)).getText();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in  apphelper textfetchbyxpath ",e);
        }
        return text;
    }

    public String textfetchbytagname(String tagname)
    {
        String text=null;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            text=driver.findElement(By.tagName(tagname)).getText();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in  apphelper textfetchbytagname ",e);
        }
        return text;
    }

    public String textfetchbylinktext(String linktext)
    {
        String text=null;
        WebDriver driver=Driver.getWebDriver();

        try
        {
            text=driver.findElement(By.linkText(linktext)).getText();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in  apphelper textfetchbylinktext ",e);
        }
        return text;
    }

    public String textfetchbyclass(String classname)
    {
        String text=null;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            text=driver.findElement(By.className(classname)).getText();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in  apphelper textfetchbyclass ",e);
        }
        return text;
    }

    public String textfetchbycssselector(String cssselector)
    {
        String text=null;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            text=driver.findElement(By.cssSelector(cssselector)).getText();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in  apphelper textfetchbycssselector ",e);
        }
        return text;
    }

    public String textfetchbylistid(String id,int index)
    {
        String text=null;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            List<WebElement> alllist=driver.findElements(By.id(id));
            text=alllist.get(index).getText();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in  apphelper textfetchbylistid ",e);
        }
        return text;
    }

    public String textfetchbylistbyxpath(String xpath,int index)
    {
        String text=null;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            List<WebElement> alllist=driver.findElements(By.xpath(xpath));
            text=alllist.get(index).getText();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in  apphelper textfetchbylistbyxpath ",e);
        }
        return text;
    }
    public String textfetchbylistcssselector(String cssselector,int index)
    {
        String text=null;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            List<WebElement> alllist=driver.findElements(By.cssSelector(cssselector));
            text=alllist.get(index).getText();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in  apphelper textfetchbylistcssselector ",e);
        }
        return text;
    }

    public String textfetchbylistclass(String classname,int index)
    {
        String text=null;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            List<WebElement> alllist=driver.findElements(By.className(classname));
            text=alllist.get(index).getText();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in  apphelper textfetchbylistclass ",e);
        }
        return text;
    }
    public List<String> textfetchbylistclasswithoutindex(String classname)
    {
        List<String> allText=new ArrayList<>();
        WebDriver driver=Driver.getWebDriver();

        try
        {
            List<WebElement> allList=driver.findElements(By.className(classname));
            for(WebElement temp:allList)
            {
                allText.add(temp.getText());
            }

        }
        catch(Exception e)
        {
            Assert.fail("Exception in  apphelper textfetchbylistclasswithoutindex ",e);
        }
        return allText;

    }



    public List<String> textfetchbylistXpathwithoutindex(String xpath)
    {
        List<String> allText=new ArrayList<>();
        WebDriver driver=Driver.getWebDriver();

        try
        {
            List<WebElement> allList=driver.findElements(By.xpath(xpath));
            for(WebElement temp:allList)
            {
                allText.add(temp.getText());
            }

        }
        catch(Exception e)
        {
            Assert.fail("Exception in  apphelper textfetchbylistclasswithoutindex ",e);
        }
        return allText;

    }


    public List<String> textfetchbylistxpathwithoutindex(String xpath)
    {
        List<String> allText=new ArrayList<>();
        WebDriver driver=Driver.getWebDriver();

        try
        {
            List<WebElement> allList=driver.findElements(By.xpath(xpath));
            for(WebElement temp:allList)
            {
                allText.add(temp.getText());

            }

        }
        catch(Exception e)
        {
            Assert.fail("Exception in  apphelper  textfetchbylistxpathwithoutindex ",e);
        }
        return allText;

    }
    public List<String> fetchhiddentextbyxpath(String xpath)
    {
        List<String> allText=new ArrayList<>();
        WebDriver driver=Driver.getWebDriver();

        try
        {
            List<WebElement> allList=driver.findElements(By.xpath(xpath));
            for(WebElement temp:allList)
            {
                allText.add(temp.getAttribute("textContent"));

            }

        }
        catch(Exception e)
        {
            Assert.fail("Exception in  apphelper  fetchhiddentextbyxpath ",e);
        }
        return allText;

    }

}
