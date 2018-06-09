package com.snapwiz.selenium.tests.staf.learnon.testcases.IT17.R58;
/*
 * Created by Sumit on 9/22/2014.
 */

import com.snapwiz.selenium.tests.staf.learnon.Driver;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.TextFetch;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class VerifyAssignmentPolicyPage {

    @Test
    public void verifyAssignmentPolicyPage()
    {
        try
        {
            Driver.startDriver();
            new LoginUsingLTI().ltiLogin("26"); //log in as instructor
            new Navigator().NavigateTo("Policies");//navigate to Policies
            new Click().clickByid("newAssignmentPolicy-link");//click on + New Assignment Policy link
            String text;
            text = new TextFetch().textfetchbycssselector("span[data-localize='point-per-question']");
            Assert.assertEquals(text , "Marks per question", "\"Marks per question\" label is absent in Assignment policy page.");

            text = new TextFetch().textfetchbylistcssselector("div[class='ls-assignment-policy-question']", 2);
            Assert.assertEquals(text , "Mark Release options", "\"Mark Release options\" label is absent in Assignment policy page.");

            text = new TextFetch().textfetchbyclass("ls-ls-assignment-policy-mode").trim();
            if(!text.contains("Students see their mark when they complete their assignment"))
                Assert.fail("\"Students see their mark when they complete their assignment\" is not displayed in Assignment policy page.");

            if(!text.contains("Students see their mark at the time of the assignment due date"))
                Assert.fail("\"Students see their mark at the time of the assignment due date\" is not displayed in Assignment policy page.");

            if(!text.contains("For assignments with manual marking, students see their marks after you have marked them"))
                Assert.fail("\"For assignments with manual marking, students see their marks after you have marked them\" is not displayed in Assignment policy page.");

            if(!text.contains("Students see their marks when you decide to release the marks"))
                Assert.fail("\"Students see their marks when you decide to release the marks\" is not displayed in Assignment policy page.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC verifyAssignmentPolicyPage in class VerifyAssignmentPolicyPage.", e);
        }
    }
    @AfterMethod
    public void tearDown() throws Exception
    {
        Driver.driver.quit();
    }
}
