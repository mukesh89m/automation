package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R165;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignmentPolicy;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/*
 * Created by Sumit on 8/28/2014.
 */
public class MentorVerifyGradebookForAssignmentWithImmediateFeedback extends Driver{

    @Test
    public void mentorVerifyGradebookForAssignmentWithImmediateFeedback()
    {
        try
        {
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", "34");
            new Assignment().create(34);//create assignment

            new LoginUsingLTI().ltiLogin("34_1");    //create student1

            new LoginUsingLTI().ltiLogin("34");    //login as mentor
            new Navigator().NavigateTo("Policies");
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicy, "description text", "1", "", true, "1", "", "", "", "", "");
            new Assignment().assignToStudent(34);//assign assignment

            new LoginUsingLTI().ltiLogin("34_1");    //log in as student
            new Assignment().submitAssignmentWithImmediateFeedBack(34);

            //TC row no. 69..."Gradebook should show the new assignment cell with grade in the format: student score / Total score"
            new LoginUsingLTI().ltiLogin("34");    //login as mentor
            new Navigator().NavigateTo("Gradebook");    //go to Gradebook
            String score = driver.findElement(By.className("student-assignment-score-cell")).getText();
            if(!score.equals("1/1"))
                Assert.fail("For mentor, Gradebook for Assignment with immediate feedback does not show the new assignment cell with grade in the format: student score / Total score.");

            //TC row no. 70...Overall Score in percentage should be displayed in the Overall Score column
            List<WebElement> overallScore = driver.findElements(By.className("ls-ins-gradebook-overall-score"));
            if(!overallScore.get(1).getText().equals("100%"))
                Assert.fail("For mentor,, Gradebook for Assignment with immediate feedback does not show the Overall Score in percentage in the Overall Score column");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case mentorVerifyGradebookForAssignmentWithImmediateFeedback in class MentorVerifyGradebookForAssignmentWithImmediateFeedback.", e);
        }
    }

}
