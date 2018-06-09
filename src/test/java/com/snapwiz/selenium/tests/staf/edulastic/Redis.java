package com.snapwiz.selenium.tests.staf.edulastic;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Login;
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
           new Login().directLoginAsAuthor();
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
