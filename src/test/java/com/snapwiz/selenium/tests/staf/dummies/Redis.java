package com.snapwiz.selenium.tests.staf.dummies;

import com.snapwiz.selenium.tests.staf.dummies.apphelper.DirectLogin;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by root on 7/23/14.
 */
public class Redis {

    @Test
    public void deleteKeys()
    {
        try
        {
           Driver.startDriver();
           new DirectLogin().CMSLogin();
           Driver.driver.get(Config.baseURL+"/secure/deleteRedisKeysWithExpiry");
          if(!(Driver.driver.findElement(By.tagName("body")).getText().contains("Successfully deleted keys with expiry from Redis")))
           Assert.fail("Failed to remove keys from redis");
        }
        catch (Exception e)
        {
            Assert.fail("Exception while deleting redis keys",e);
        }

    }
    @AfterMethod
    public void tearDown() throws Exception
    {
        Driver.driver.quit();
    }
}
