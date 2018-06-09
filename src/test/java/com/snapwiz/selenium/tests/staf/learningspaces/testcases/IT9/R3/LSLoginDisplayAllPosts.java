package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT9.R3;


import java.util.logging.Level;
import java.util.logging.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessage;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.PostMessageValidate;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Screenshot;

public class LSLoginDisplayAllPosts extends Driver{

    @Test
    public void lsloginDisplayAllPosts()
    {
        try
        {

            new LoginUsingLTI().ltiLogin("48");
            new Navigator().NavigateTo("Course Stream");
            String texttobeposted=new RandomString().randomstring(6);
            new PostMessage().postmessage(texttobeposted);
            Boolean postfound = new PostMessageValidate().postMessageValidate(texttobeposted);

            if(postfound == false)
            {
                Assert.fail("Student not able to see the first post");
            }


            texttobeposted=new RandomString().randomstring(6);
            new PostMessage().postmessage(texttobeposted);
            postfound = new PostMessageValidate().postMessageValidate(texttobeposted);

            if(postfound == false)
            {
                Assert.fail("Student not able to see the second post");
            }


            texttobeposted=new RandomString().randomstring(6);
            new PostMessage().postmessage(texttobeposted);
            postfound = new PostMessageValidate().postMessageValidate(texttobeposted);
            if(postfound == false)
            {
                Assert.fail("Student not able to see the third post");
            }


        }
        catch(Exception e)
        {
            Assert.fail("Exception  in testcase lsloginDisplayAllPosts in class LSLoginDisplayAllPosts",e);
        }
    }


}
