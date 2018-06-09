package com.snapwiz.selenium.tests.staf.AppiumLearningspaces;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by shashank on 17-07-2015.
 */
public class UpdateContentIndexForAllCourses {

    @Test
    public void updateIndexForAllCourses()
    {
        try {
            com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver.startDriver();
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.UpdateContentIndex().updateContentIndexForAllCourses("222");
        }
        catch (Exception e)
        {
            Assert.fail("Exception while updating content index of solr", e);
        }
    }
    @AfterMethod
    public void tearDown() throws Exception
    {
        com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver.driver.quit();
    }
}
