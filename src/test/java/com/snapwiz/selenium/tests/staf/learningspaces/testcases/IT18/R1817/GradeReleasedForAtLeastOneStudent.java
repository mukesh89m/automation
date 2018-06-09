package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R1817;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignmentPolicy;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*
 * Created by sumit on 6/1/15.
 */
public class GradeReleasedForAtLeastOneStudent extends Driver{

    @Test(priority = 1, enabled = true)
    public void gradeReleasedForAtLeastOneStudent()
    {
        try
        {
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", "63");
            new Assignment().create(63);

            new LoginUsingLTI().ltiLogin("63_1");		//login as student1
            new LoginUsingLTI().ltiLogin("63_2");    //login as student2
            new LoginUsingLTI().ltiLogin("63");		//login a instructor
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicy, "63 Policy description", "2", null, true, "1", "", null, "", "", "");
            new Assignment().assignToStudent(63);

            new LoginUsingLTI().ltiLogin("63_1");		//login as student1
            new Assignment().submitAssignmentWithImmediateFeedBack(63);

            new LoginUsingLTI().ltiLogin("63");		//login a instructor
            new Navigator().NavigateTo("Current Assignments");
            boolean unAssign = new BooleanValue().presenceOfElement(63, By.className("delete-assigned-task"));
            Assert.assertEquals(unAssign, true, "\"Unassign Assignment\" option is present for that Assignment for which the policy Immediate feedback is enabled.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class GradeReleasedForAtLeastOneStudent in method gradeReleasedForAtLeastOneStudent.", e);
        }
    }
    @Test(priority = 2, enabled = true)
    public void assignmentSubmittedByAllStudent()
    {
        try
        {
            new Assignment().create(64);

            new LoginUsingLTI().ltiLogin("64_1");   //login as student1
            new LoginUsingLTI().ltiLogin("64_2");   //login as student2
            new LoginUsingLTI().ltiLogin("64");		//login a instructor
            new Assignment().assignToStudent(64);

            new LoginUsingLTI().ltiLogin("64_1");		//login as student1
            new Assignment().submitAssignmentAsStudent(64);

            new LoginUsingLTI().ltiLogin("64_2");		//login as student2
            new Assignment().submitAssignmentAsStudent(64);

            new LoginUsingLTI().ltiLogin("64");		//login a instructor
            new Navigator().NavigateTo("Current Assignments");
            boolean unAssign = new BooleanValue().presenceOfElement(64, By.className("delete-assigned-task"));
            Assert.assertEquals(unAssign, false, "\"Unassign Assignment\" option is present for that Assignment where all the student has submitted the assignment.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class GradeReleasedForAtLeastOneStudent in method assignmentSubmittedByAllStudent.", e);
        }
    }
}
