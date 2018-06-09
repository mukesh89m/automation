package com.snapwiz.selenium.tests.staf.learningspaces;

import com.snapwiz.selenium.tests.staf.framework.controller.*;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by root on 7/23/14.
 */
public class Redis extends com.snapwiz.selenium.tests.staf.framework.controller.Driver {

    @Test
    public void deleteKeys()
    {
        WebDriver driver= Driver.getWebDriver();
        try
        {
           new DirectLogin().CMSLogin();
           driver.get(Config.baseURL+"/secure/deleteRedisKeysWithExpiry");
          if(!(driver.findElement(By.tagName("body")).getText().contains("Successfully deleted keys with expiry from Redis")))
           Assert.fail("Failed to remove keys from redis");
        }
        catch (Exception e)
        {
            Assert.fail("Exception while deleting redis keys",e);
        }

    }

}
