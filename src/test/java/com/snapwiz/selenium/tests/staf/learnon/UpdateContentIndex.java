package com.snapwiz.selenium.tests.staf.learnon;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*
 * Created by Sumit on 4/28/14.
 */
public class UpdateContentIndex {

    @Test
    public void updateIndex()
    {
        try {
            Driver.startDriver();
            new com.snapwiz.selenium.tests.staf.learnon.apphelper.UpdateContentIndex().updatecontentindex("222");
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
