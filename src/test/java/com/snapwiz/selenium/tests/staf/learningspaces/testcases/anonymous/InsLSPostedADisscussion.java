package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessageValidate;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;

public class InsLSPostedADisscussion extends Driver {
    private static Logger logger=Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.InsLSPostedADisscussion");
    /*
     * 204-
     */
    @Test(priority=1,enabled=true)
    public void defaultMessage()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("204_R24");
            new Navigator().NavigateTo("Course Stream");
            driver.findElement(By.className("ls-post-tab")).click();
            String labelNodeText = driver.switchTo().frame("iframe-user-text-input-div").findElement(By.xpath("/html/body/font")).getText();
            System.out.println("labelNodeText: "+labelNodeText);
            driver.switchTo().defaultContent();
            if(labelNodeText.equals("Share your knowledge or seek answers..."))
            {
                logger.log(Level.INFO,"Testcase DefaultMessage Pass");
            }
            else
            {
                logger.log(Level.INFO,"Testcase DefaultMessage Fail");
                Assert.fail("Text box  say by default:Share your knowledge or seek answers not shown");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception  in testcase defaultMessage in class InsLSPostedADisscussion",e);
        }
    }


    @Test(priority=2,enabled=true)
    public void postedDiscussionAddedInStream()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("204_R24");
            new Navigator().NavigateTo("Course Stream");
            String randomtext = new RandomString().randomstring(6);
            new PostMessage().postmessage(randomtext);
            boolean poststatus = new PostMessageValidate().postMessageValidateForInstructor(randomtext);
            if(poststatus == false)
            {
                Assert.fail("Post NOT posted in course stream successfully");
            }

        }
        catch(Exception e)
        {

            Assert.fail("Exception  in testcase postedDiscussionAddedInStream in class InsLSPostedADisscussion",e);
        }

    }



}
