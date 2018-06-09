package com.snapwiz.selenium.tests.staf.datacreation;

import com.snapwiz.selenium.tests.staf.datacreation.apphelper.DirectLogin;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by root on 7/23/14.
 */
public class Redis extends Driver {

    @Test
    public void deleteKeys()
    {
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
