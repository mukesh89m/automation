package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;

import org.openqa.selenium.By;
import com.snapwiz.selenium.tests.staf.learningspaces.*;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*
 * R3.1020
 * Verify message is displayed when the student has not entered any name
 */
public class LSMessageForPostWithoutUser extends Driver{


    @Test
    public void messageForPostWithoutUser()
    {
        try {
            new LoginUsingLTI().ltiLogin("1508");
            new Navigator().NavigateTo("Course Stream");
            new Click().clickbylinkText("Post");
            driver.findElement(By.className("closebutton")).click();
            driver.findElement(By.id("post-submit-button")).click();
            String errorMessage = new TextFetch().textfetchbyclass("notification-message-wrapper");
            new Navigator().NavigateTo("Course Stream");
            if (!errorMessage.trim().contains("You need to select at least one user or a group for your post."))
                Assert.fail("Error message not found while trying to submit blank Post with blank Share Box");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC messageForPostWithoutUser in class LSMessageForPostWithoutUser.", e);
        }
    }



}
