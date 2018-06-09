package com.snapwiz.selenium.tests.staf.learnon.testcases.IT17.R58;
/*
 * Created by Sumit on 9/23/2014.
 */

import com.snapwiz.selenium.tests.staf.learnon.Driver;
import com.snapwiz.selenium.tests.staf.learnon.ReadTestData;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.TextFetch;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class VerifyGradableAssignmentCheckBox {

    @Test
    public void verifyGradableAssignmentCheckBox()
    {
        try
        {
            Driver.startDriver();
            String text;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "34");
            new Assignment().create(34);
            new Assignment().addQuestions(34, "multiplechoice", "");
            new Assignment().addQuestions(34, "multipleselection", "");

            new LoginUsingLTI().ltiLogin("36"); //log in as student
            new LoginUsingLTI().ltiLogin("34"); //log in as instructor
            new Assignment().assignToStudent(34);//assign to student

            text = new TextFetch().textfetchbycssselector("span[class='ls-assignment-graded-quiz-icon assessment-block-icon']");
            Assert.assertEquals(text, "Assessment Task", "The label \"Graded\" for an assignment does not changes to \"Assessment Task\" in Assignment page.");
            new Assignment().clickViewResponse(assessmentname);
            text = new TextFetch().textfetchbylistcssselector("span[class='ls-assignment-graded-quiz-icon assessment-block-icon']", 1);
            Assert.assertEquals(text, "Assessment Task", "The label \"Graded\" for an assignment does not changes to \"Assessment Task\" in Assignment Response page.");
            new Navigator().NavigateTo("Course");//go to Course
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Assignments");//navigate to Assignments tab
            text = new TextFetch().textfetchbycssselector("span[class='ls-side-gradaded-label']");
            Assert.assertEquals(text, "Assessment Task", "The label \"Graded\" for an assignment does not changes to \"Assessment Task\" in Assignment tab of Course.");

            new LoginUsingLTI().ltiLogin("36"); //log in as student
            new Navigator().NavigateTo("Assignments");
            text = new TextFetch().textfetchbycssselector("span[class='ls-assignment-graded-quiz-icon assessment-block-icon']");
            Assert.assertEquals(text, "Assessment Task", "The label \"Graded\" for an assignment does not changes to \"Assessment Task\" in Assignment page in student side.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC verifyGradableAssignmentCheckBox in class VerifyGradableAssignmentCheckBox.", e);
        }
    }

    @AfterMethod
    public void tearDown() throws Exception
    {
        Driver.driver.quit();
    }
}
