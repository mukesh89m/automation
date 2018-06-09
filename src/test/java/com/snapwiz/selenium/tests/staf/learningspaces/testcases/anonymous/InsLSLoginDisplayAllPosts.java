package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessageValidate;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;

public class InsLSLoginDisplayAllPosts extends Driver{
    @Test
    public void LSLoginDisplayAllPosts()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("48_R24");
            new Navigator().NavigateTo("Course Stream");
            String texttobeposted = new RandomString().randomstring(6);
            new PostMessage().postmessage(texttobeposted);
            boolean postfound = new PostMessageValidate().postMessageValidateForInstructor(texttobeposted);
            if(postfound == false)
            {
                Assert.fail("Instructor not able to see the first post");
            }
            texttobeposted=new RandomString().randomstring(6);
            new PostMessage().postmessage(texttobeposted);
            postfound = new PostMessageValidate().postMessageValidateForInstructor(texttobeposted);

            if(postfound == false)
            {
                Assert.fail("Instructor not able to see the second post");
            }
            texttobeposted=new RandomString().randomstring(6);
            new PostMessage().postmessage(texttobeposted);
            postfound = new PostMessageValidate().postMessageValidateForInstructor(texttobeposted);
            if(postfound == false)
            {
                Assert.fail("Instructor not able to see the third post");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception  in testcase LSLoginDisplayAllPosts in class InsLSLoginDisplayAllPosts",e);
        }
    }


}
