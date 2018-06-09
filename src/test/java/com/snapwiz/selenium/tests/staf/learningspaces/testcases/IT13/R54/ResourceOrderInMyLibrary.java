package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT13.R54;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.UploadResources;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Sumit on 8/20/2014...script created as per bug 10976
 */
public class ResourceOrderInMyLibrary extends Driver{

    @Test
    public void resourceOrderInMyLibrary()
    {
        try
        {
            String resourcesname1 = ReadTestData.readDataByTagName("", "resourcesname", "10976_1");
            new LoginUsingLTI().ltiLogin("10976");//login as instructor
            new UploadResources().uploadResources("10976", false, true, false);//upload resource1
            new UploadResources().uploadResources("10976_1", false, true, false);//upload resource1
            Thread.sleep(3000);
            String resourceTitle = driver.findElement(By.className("resource-title")).getText();
            if(!resourceTitle.contains(resourcesname1))
                Assert.fail("Newly added resource is not adding at the top of \"My Library\" page.");

            new Navigator().NavigateTo("Resources");//go to Resources
            driver.findElement(By.cssSelector("span[title='Add to My Resources']")).click();//bookmark a resource
            Thread.sleep(2000);
            new Navigator().navigateToTab("My Resources");
            Thread.sleep(2000);
            String resourceTitle1 = driver.findElement(By.className("resource-title")).getText();
            if (System.getProperty("UCHAR") == null) {
                resourcesname1 = resourcesname1 + LoginUsingLTI.appendChar;
            } else {
                resourcesname1 = resourcesname1 + System.getProperty("UCHAR");
            }
            if(resourceTitle1.contains(resourcesname1))
                Assert.fail("After bookmarking a resource from All Resource tab it is not adding at the top of \"My Library\" page.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in tescase resourceOrderInMyLibrary in method ResourceOrderInMyLibrary", e);
        }
    }
}
