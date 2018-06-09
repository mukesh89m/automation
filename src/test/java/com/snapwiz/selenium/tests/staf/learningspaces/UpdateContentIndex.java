package com.snapwiz.selenium.tests.staf.learningspaces;

import com.snapwiz.selenium.tests.staf.framework.controller.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by root on 4/28/14.
 */
public class UpdateContentIndex extends com.snapwiz.selenium.tests.staf.framework.controller.Driver{

    @Test
    public void updateIndex()
    {
        try {
            new com.snapwiz.selenium.tests.staf.learningspaces.apphelper.UpdateContentIndex().updatecontentindex("222");
        }
        catch (Exception e)
        {
            Assert.fail("Exception while updating content index of solr",e);
        }
    }


}
