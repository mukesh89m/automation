package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;


import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessageValidate;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;

public class InsSharingAPost extends Driver {
    /*
     * 209-211
     */
    @Test(priority=1,enabled=true)
    public void sharePostWithClassSectionName()
    {
        try
        {
            String shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "209_R24");
            new LoginUsingLTI().ltiLogin("209_R24");
            new Navigator().NavigateTo("Course Stream");
            String randomstring=new RandomString().randomstring(4);
            if(!new PostMessage().postMessageAndShare(randomstring, shareWithInitialString, "studentnametag", "209_R24","true"))
                Assert.fail("Message not posted which is shared with class");
            boolean postpresent = new PostMessageValidate().postMessageValidateForInstructor(randomstring);
            if(postpresent == true)
            {
                String attachedstring=driver.findElement(By.className("ls-stream-post__action")).getText();
                if(!attachedstring.trim().equals("posted a discussion"))
                {
                    Assert.fail("Posting with title 'Posted a Discussion' not added in course stream");
                }
            }
            else
            {
                Assert.fail("Posted Text not Found");
            }
            new LoginUsingLTI().ltiLogin("210_R24");
            new Navigator().NavigateTo("Course Stream");

            boolean postpresent1 = new PostMessageValidate().postMessageValidateForInstructor(randomstring);

            if(postpresent1==false)
            {
                Assert.fail("Other instructor of same class section is unable to see the post posted by an instructor.");

            }

            new LoginUsingLTI().ltiLogin("211_R24");
            new Navigator().NavigateTo("Course Stream");
            boolean postpresent2 = new PostMessageValidate().postMessageValidate(randomstring);
            if(postpresent2 == true)
            {
                Assert.fail("Instructor of other class section is able to see the post in course stream.");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception  in testcase sharePostWithClassSectionName in class InsSharingAPost",e);
        }
    }


}
