package com.snapwiz.selenium.tests.staf.learnon.testcases.IT17.R58;
/*
 * Created by Sumit on 9/22/2014.
 */

import com.snapwiz.selenium.tests.staf.learnon.Driver;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.*;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.TextFetch;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class SubmitStaticTest {

    @Test
    public void submitStaticTest()
    {
        try
        {
            Driver.startDriver();
            new LoginUsingLTI().ltiLogin("32"); //log in as student
            new Navigator().NavigateTo("Course");
            new TopicOpen().staticAssessmentOpen(0, 0);
            new AttemptTest().StaticTest();
            String text = new TextFetch().textfetchbyid("performance-bar-chart-wrapper");
            if(!text.contains("Marks"))
                Assert.fail("\"Marks\" is not displayed along Y-axis in report graph of static assessment.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC submitStaticTest in class SubmitStaticTest.", e);
        }
    }
    @AfterMethod
    public void tearDown() throws Exception
    {
        Driver.driver.quit();
    }
}
