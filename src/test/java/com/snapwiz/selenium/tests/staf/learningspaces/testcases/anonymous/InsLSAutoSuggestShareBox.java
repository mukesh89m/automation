package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class InsLSAutoSuggestShareBox extends Driver{
    private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.LSAutoSuggestShareBox");

    @Test
    public void autoSuggestShareBox()
    {
        try
        {
            String studentnametext =  ReadTestData.readDataByTagName("InsLSAutoSuggestShareBox", "givenname", "141_1");
            String instructor =  ReadTestData.readDataByTagName("InsLSAutoSuggestShareBox", "givenname", "141");
            new LoginUsingLTI().ltiLogin("141_1");
            new LoginUsingLTI().ltiLogin("141");
            new Navigator().NavigateTo("Course Stream");
            driver.findElement(By.className("share-to-ls-label")).click();
            driver.findElement(By.cssSelector("a[class='closebutton']")).click();

            WebElement textbox = driver.findElement(By.className("maininput"));
            textbox.sendKeys(studentnametext);
            WebElement studentname = (WebElement)((JavascriptExecutor)driver).executeScript("return arguments[0];",driver.findElement(By.cssSelector("ul[id='share-with_feed']")));

            driver.findElement(By.className("maininput")).clear();
            driver.findElement(By.className("maininput")).sendKeys(instructor);
            WebElement instructorname = (WebElement)((JavascriptExecutor)driver).executeScript("return arguments[0];",driver.findElement(By.cssSelector("ul[id='share-with_feed']")));

            if(studentname.getText().trim().equals("No results found") || instructorname.getText().trim().equals("No results found") )			  {
                logger.log(Level.INFO,"share with box not suggest student or group and instructor name");
                Assert.fail("share with box does not suggest student or group or instructor name");
            }
            else
            {
                logger.log(Level.INFO,"share with box  suggest student or group and instructor name");

            }
        }
        catch(Exception e)
        {
            logger.log(Level.SEVERE,"Exception  in testcase InsLSAutoSuggestShareBox");
            Assert.fail("Exception  in testcase InsLSAutoSuggestShareBox",e);
        }
    }



}
