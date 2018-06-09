package com.snapwiz.selenium.tests.staf.learnon.testcases.IT17.R58;
/*
 * Created by Sumit on 9/19/2014.
 */
import com.snapwiz.selenium.tests.staf.learnon.Driver;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.*;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.TextFetch;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class VerifyTOC {

    @Test
    public void verifyTOC()
    {
        try
        {
            Driver.startDriver();
            new LoginUsingLTI().ltiLogin("18");//login as instructor
            new Navigator().NavigateTo("Course Stream");//go to Course Stream
            String post = new RandomString().randomstring(10);
            String text;
            new PostMessage().postmessage(post);//post a message
            text = new TextFetch().textfetchbyclass("ls-instructor-icon");
            Assert.assertEquals(text , "Teacher", "\"Teacher\" label is absent in Course Stream entry if a teacher post a message in Course Stream.");

            new Assignment().create(18);
            new Assignment().addQuestions(18, "multiplechoice", "");
            new Assignment().addQuestions(18, "multipleselection", "");

            new LoginUsingLTI().ltiLogin("20"); //log in as student
            new LoginUsingLTI().ltiLogin("18"); //log in as instructor
            new Assignment().assignToStudent(18);
            new Navigator().NavigateTo("Course Stream");//go to Course Stream
            text = new TextFetch().textfetchbyclass("ls-instructor-icon");
            Assert.assertEquals(text , "Teacher", "\"Teacher\" label is absent in Course Stream entry when assignment is assigned to student.");

            new Navigator().NavigateTo("Course");//go to Course
            new TOCShow().tocHide();//close the TOC
            String discussion = new RandomString().randomstring(10);
            new Discussion().postDiscussion(discussion);//post a discussion
            text = new TextFetch().textfetchbyclass("ls-side-istructor");
            Assert.assertEquals(text , "Teacher", "\"Teacher\" label is absent when instructor post a discussion in lesson page.");


            new Navigator().NavigateTo("Dashboard");//go to Dashboard
            text = new TextFetch().textfetchbyclass("instructor");
            Assert.assertEquals(text , "Teacher", "\"Teacher\" label is absent CS tile for instructor entry.");

            new UploadResources().uploadResources("18", false, false, true);//upload a resource
            new Navigator().NavigateTo("Course");//go to Course
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Assignments");//navigate to Assignments tab
            text = new TextFetch().textfetchbylistclass("ls-side-istructor", 1);
            Assert.assertEquals(text , "Teacher", "\"Teacher\" label is absent in Assignment tab.");

            new Navigator().navigateToTab("Resources");//navigate to Resource tab
            text = new TextFetch().textfetchbylistclass("ls-side-istructor", 2);
            Assert.assertEquals(text , "Teacher", "\"Teacher\" label is absent in Resources tab.");

            new LoginUsingLTI().ltiLogin("20"); //log in as student
            text = new TextFetch().textfetchbyclass("instructor");
            Assert.assertEquals(text , "Teacher", "\"Teacher\" label is absent CS tile for instructor entry in student side.");

            new Navigator().NavigateTo("Course Stream");//go to Course Stream
            text = new TextFetch().textfetchbylistclass("ls-instructor-icon", 0);
            Assert.assertEquals(text , "Teacher", "In student side \"Teacher\" label is absent in Course Stream entry when instructor post a discussion on lesson page.");

            text = new TextFetch().textfetchbylistclass("ls-instructor-icon", 1);
            Assert.assertEquals(text , "Teacher", "In student side \"Teacher\" label is absent in Course Stream entry when assignment is assigned to student.");

            new Navigator().NavigateTo("Course");//go to Course
            new TOCShow().tocHide();
            text = new TextFetch().textfetchbylistclass("ls-side-istructor", 0);
            Assert.assertEquals(text , "Teacher", "In student side \"Teacher\" label is absent in Discussion tab.");

            new Navigator().navigateToTab("Assignments");//navigate to Assignments tab
            text = new TextFetch().textfetchbylistclass("ls-side-istructor", 1);
            Assert.assertEquals(text , "Teacher", "In student side \"Teacher\" label is absent in Assignment tab.");

            new Navigator().navigateToTab("Resources");//navigate to Resource tab
            text = new TextFetch().textfetchbylistclass("ls-side-istructor", 2);
            Assert.assertEquals(text , "Teacher", "In student side \"Teacher\" label is absent in Resources tab.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC verifyTOC in class VerifyTOC", e);
        }
    }
    @AfterMethod
    public void tearDown() throws Exception
    {
        Driver.driver.quit();
    }
}
