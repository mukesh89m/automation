package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R1817;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
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
public class StudentSubmitNonGradableAssignment extends Driver{

    @Test(priority = 1, enabled = true)
    public void studentSubmitNonGradableAssignment()
    {
        try
        {
            new Assignment().create(97);

            new LoginUsingLTI().ltiLogin("97_1");		//login as student1
            new LoginUsingLTI().ltiLogin("97_2");    //login as student2
            new LoginUsingLTI().ltiLogin("97");		//login a instructor
            new Assignment().assignToStudent(97);

            new LoginUsingLTI().ltiLogin("97_1");		//login as student1
            new Assignment().submitAssignmentAsStudent(97);

            new LoginUsingLTI().ltiLogin("97");		//login a instructor
            new Navigator().NavigateTo("Current Assignments");
            boolean unAssign = new BooleanValue().presenceOfElement(97, By.className("delete-assigned-task"));
            Assert.assertEquals(unAssign, true, "\"Unassign Assignment\" option is present for that Assignment where single student has submitted the non-gradable assignment.");

            new LoginUsingLTI().ltiLogin("97_2");		//login as student2
            new Assignment().submitAssignmentAsStudent(97);
            new LoginUsingLTI().ltiLogin("97");		//login a instructor
            new Navigator().NavigateTo("Current Assignments");
            boolean unAssign1 = new BooleanValue().presenceOfElement(97, By.className("delete-assigned-task"));
            Assert.assertEquals(unAssign1, false, "\"Unassign Assignment\" option is present for that Assignment where all the student has submitted the non-gradable assignment.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class StudentSubmitNonGradableAssignment in method studentSubmitNonGradableAssignment.", e);
        }
    }

}
