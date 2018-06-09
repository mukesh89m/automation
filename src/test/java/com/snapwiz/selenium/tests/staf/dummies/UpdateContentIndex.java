package com.snapwiz.selenium.tests.staf.dummies;

import com.snapwiz.selenium.tests.staf.dummies.Driver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by root on 4/28/14.
 */
public class UpdateContentIndex {

    @Test
    public void updateIndex()
    {
        try {
            com.snapwiz.selenium.tests.staf.dummies.Driver.startDriver();
            new com.snapwiz.selenium.tests.staf.dummies.apphelper.UpdateContentIndex().updatecontentindex("222");
        }
        catch (Exception e)
        {
            Assert.fail("Exception while updating content index of solr",e);
        }
    }

    @AfterMethod
    public void tearDown() throws Exception
    {
        Driver.driver.quit();
    }
}
