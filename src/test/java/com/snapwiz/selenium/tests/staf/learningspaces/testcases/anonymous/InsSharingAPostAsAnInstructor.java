package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

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

public class InsSharingAPostAsAnInstructor extends Driver {

    @Test(priority=1,enabled=true)
    public void postAText()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("212_R24");//login as instructor
            new LoginUsingLTI().ltiLogin("213_R24");//login as student
            new LoginUsingLTI().ltiLogin("214_R24");//login as instructor
            new LoginUsingLTI().ltiLogin("215_R24");//login as instructor

            new LoginUsingLTI().ltiLogin("212_R24");//login as instructor
            new Navigator().NavigateTo("Course Stream");
            String randomstring=new RandomString().randomstring(6);
            String shareWithInitialString;
            shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "213_R24");
            boolean postDone = new PostMessage().postMessageAndShare(randomstring, shareWithInitialString, "studentnametag", "213_R24","false");//share with a student
            if(postDone == false)
                Assert.fail("Post is not Posted successfully");
            boolean textPresent = new PostMessageValidate().postMessageValidate(randomstring);
            if(textPresent==false)
                Assert.fail("Post Text is not found as expected");

            new LoginUsingLTI().ltiLogin("213_R24");//login as student
            new Navigator().NavigateTo("Course Stream");
            boolean textPresent1 = new PostMessageValidate().postMessageValidate(randomstring);
            if(textPresent1 == false)
                Assert.fail("Instructor posted discussion in CS is not visible to student with whom the post has been shared.");

            new LoginUsingLTI().ltiLogin("212_R24");//login as instructor
            new Navigator().NavigateTo("Course Stream");
            String randomstring1 = new RandomString().randomstring(6);
            shareWithInitialString = ReadTestData.readDataByTagName("", "shareWithInitialString", "214_R24");
            boolean postDone1 = new PostMessage().postMessageAndShare(randomstring1, shareWithInitialString, "studentnametag", "214_R24","false");//share with a instructor
            if(postDone1 == false)
                Assert.fail("Post is not Posted successfully");

            new LoginUsingLTI().ltiLogin("214_R24");//login as instructor
            new Navigator().NavigateTo("Course Stream");
            boolean textPresent2 = new PostMessageValidate().postMessageValidate(randomstring1);
            if(textPresent2 == false)
                Assert.fail("Instructor posted discussion in CS is not visible to other instructor with whom the post has been shared.");

            new LoginUsingLTI().ltiLogin("215_R24");//login as instructor
            new Navigator().NavigateTo("Course Stream");
            boolean textPresent3 = new PostMessageValidate().postMessageValidate(randomstring);
            if(textPresent3 == true)
                Assert.fail("Instructor posted discussion in CS is visible to other instructor with whom the post has NOT been shared.");
        }
        catch(Exception e)
        {
            Assert.fail("Exception  in testcase postAText in class InsSharingAPostAsAnInstructor",e);
        }
    }


}
