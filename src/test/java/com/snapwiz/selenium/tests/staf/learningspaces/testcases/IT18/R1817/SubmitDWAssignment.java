package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R1817;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*
 * Created by sumit on 7/1/15.
 */
public class SubmitDWAssignment extends Driver{

    @Test(priority = 1, enabled = true)
    public void submitGradableDWAssignment()
    {
        try
        {
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new LoginUsingLTI().ltiLogin("201_1");		//login as student1
            new LoginUsingLTI().ltiLogin("201_2");      //login as student2
            new LoginUsingLTI().ltiLogin("201");		//login as instructor
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TopicOpen().openLessonWithDiscussionWidget();
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(201);

            new LoginUsingLTI().ltiLogin("201_1");		//login as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getLessonAssignment().click();//open DW assignment
            String perspective = new RandomString().randomstring(15);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspective);//submit the DW assignment

            new LoginUsingLTI().ltiLogin("201_2");		//login as student2
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getLessonAssignment().click();//open DW assignment
            new DiscussionWidget().addPerspectiveForDWAssignment(perspective);//submit the DW assignment

            new LoginUsingLTI().ltiLogin("201");		//login as instructor
            new Navigator().NavigateTo("Current Assignments");
            boolean unAssign1 = new BooleanValue().presenceOfElement(201, By.className("delete-assigned-task"));
            Assert.assertEquals(unAssign1, false, "\"Unassign Assignment\" option is present for DW gradable Assignment where all the student has submitted the assignment.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class SubmitDWAssignment in method submitGradableDWAssignment.", e);
        }
    }
    @Test(priority = 2, enabled = true)
    public void submitNonGradableDWAssignment()
    {
        try
        {
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new LoginUsingLTI().ltiLogin("202_1");		//login as student1
            new LoginUsingLTI().ltiLogin("202_2");      //login as student2
            new LoginUsingLTI().ltiLogin("202");		//login as instructor
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TopicOpen().openLessonWithDiscussionWidget();
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(202);

            new LoginUsingLTI().ltiLogin("202_1");		//login as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getLessonAssignment().click();//open DW assignment
            String perspective = new RandomString().randomstring(15);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspective);//submit the DW assignment

            new LoginUsingLTI().ltiLogin("202");		//login as instructor
            new Navigator().NavigateTo("Current Assignments");
            boolean unAssign1 = new BooleanValue().presenceOfElement(202, By.className("delete-assigned-task"));
            Assert.assertEquals(unAssign1, true, "\"Unassign Assignment\" option is present for DW non-gradable Assignment where a single student has submitted the assignment.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class SubmitDWAssignment in method submitNonGradableDWAssignment.", e);
        }
    }

}
