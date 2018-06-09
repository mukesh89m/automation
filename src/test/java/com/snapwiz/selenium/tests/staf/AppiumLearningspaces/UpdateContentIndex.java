package com.snapwiz.selenium.tests.staf.AppiumLearningspaces;

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
            com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver.startDriver();
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.UpdateContentIndex().updatecontentindex("222");
        }
        catch (Exception e)
        {
            Assert.fail("Exception while updating content index of solr",e);
        }
    }

    @AfterMethod
    public void tearDown() throws Exception
    {
        com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver.driver.quit();
    }
}
