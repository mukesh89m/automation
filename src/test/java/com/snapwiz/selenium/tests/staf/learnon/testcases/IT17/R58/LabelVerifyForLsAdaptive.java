package com.snapwiz.selenium.tests.staf.learnon.testcases.IT17.R58;/*
 * Created by Sumit on 9/24/2014.
 */

import com.snapwiz.selenium.tests.staf.learnon.Driver;
import com.snapwiz.selenium.tests.staf.learnon.ReadTestData;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.*;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.TextFetch;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class LabelVerifyForLsAdaptive {

    @Test
    public void labelVerifyForLsAdaptive()
    {
        try
        {
            Driver.startDriver();
            new LoginUsingLTI().ltiLogin("46"); //log in as student to LS+Adaptive course
            new Navigator().NavigateTo("Course");
            new TopicOpen().staticAssessmentOpen(0, 0);
            new AttemptTest().StaticTest();

            new LoginUsingLTI().ltiLogin("44"); //log in as instructor
            new Navigator().NavigateTo("Completion Report");//check for Completion Report in Navigator
            String header = new TextFetch().textfetchbyclass("student-engagement-report-text");
            Assert.assertEquals(header, "Student Completion Report", "In \"Completion Report\" the header is not \"Student Completion Report\".");
            String text;

            new Assignment().create(44);
            new Assignment().addQuestions(44, "multiplechoice", "");
            new Assignment().addQuestions(44, "multipleselection", "");

            new LoginUsingLTI().ltiLogin("46"); //log in as student
            new LoginUsingLTI().ltiLogin("44"); //log in as instructor
            new Assignment().assignToStudent(44);//assign to student
            new Navigator().NavigateTo("Gradebook");
            text = new TextFetch().textfetchbylistcssselector("span[class='ls-ins-gradebook-activity-midterm']", 4);
            Assert.assertEquals(text, "Not Marked", "The label \"Not Graded\" for an assignment does not changes to \"Not Marked\" in Gradebook page for LS+Adaptive course.");


            new LoginUsingLTI().ltiLogin("44"); //log in as instructor
            new Navigator().NavigateTo("Resources");//Navigate to Resource
            new Click().clickBycssselector("span[title='My Library']");//click on My Library tab
            new Click().clickByclassname("instructor-uploadResource-new-txt");//click on Upload resource
            text = new TextFetch().textfetchbyclass("ins-useResource-grading-policy-txt");
            Assert.assertEquals(text, "Reserved for marking policy", "The label \"Reserved for marking policy\" is absent when we upload a resource from Resources page.");

            new LoginUsingLTI().ltiLogin("44"); //log in as instructor
            new UploadResources().uploadResources("44", false, true, false);//upload for marking policy
            text = new TextFetch().textfetchbyclass("ls-grading-policy-icon");
            Assert.assertEquals(text, "Marking Policy", "The label \"Marking Policy\" is absent in My library when we upload a resource from Resources page.");

            new LoginUsingLTI().ltiLogin("44"); //log in as instructor
            new UploadResources().uploadResources("46", true, false, true);//upload as resource
            new Navigator().NavigateTo("Course");//Navigate to Course
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Resources");
            String resourcesname = ReadTestData.readDataByTagName("", "resourcesname", "46");
            text = new TextFetch().textfetchbycssselector("div[class='ls-right-section-status ls-right-section-resource']");
            Assert.assertEquals(text, resourcesname, "The student unable to view the resource in the resource tab in the right on the textbook pages for the specified node.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC labelVerifyInNavigator in class LabelVerifyInNavigator.", e);
        }
    }
    @AfterMethod
    public void tearDown() throws Exception
    {
        Driver.driver.quit();
    }
}
