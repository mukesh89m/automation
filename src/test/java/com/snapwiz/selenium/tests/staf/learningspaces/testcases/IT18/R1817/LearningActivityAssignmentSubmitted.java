package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R1817;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignLesson;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*
 * Created by sumit on 6/1/15.
 */
public class LearningActivityAssignmentSubmitted extends Driver{

    @Test(priority = 1, enabled = true)
    public void learningActivityAssignmentSubmitted()
    {
        try
        {
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new LoginUsingLTI().ltiLogin("162_1");		//login as student1
            new LoginUsingLTI().ltiLogin("162_2");    //login as student2
            new LoginUsingLTI().ltiLogin("162");		//login a instructor
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TOCShow().tocHide();                    //hide the TOC
            new AssignLesson().assignLesson("162");//assign lesson to class

            //TC row no 162
            new LoginUsingLTI().ltiLogin("162_1");		//login as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getLessonAssignment().click();//open lesson assignment to submit
            Thread.sleep(2000);

            new LoginUsingLTI().ltiLogin("162");		//login a instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            boolean found = new BooleanValue().presenceOfElement(162, By.className("delete-assigned-task"));
            Assert.assertEquals(found, true, "\"Unassign Assignment\" option is present for learning activity Assignment which has been submitted.");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in class UnAssignLearningActivityAssignment in method unAssignLearningActivityAssignment.", e);

        }
    }

}
