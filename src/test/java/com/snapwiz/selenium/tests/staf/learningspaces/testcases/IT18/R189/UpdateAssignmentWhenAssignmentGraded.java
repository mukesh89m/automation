package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R189;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignmentPolicy;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*
 * Created by sumit on 31/12/14.
 */
public class UpdateAssignmentWhenAssignmentGraded extends Driver {

    @Test(priority = 1, enabled = true)
    public void updateGradableQuestionAssignmentWithPolicyWhenAssignmentGraded()
    {
        try
        {
            //TC row no. 216 - 226
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            String duedate = ReadTestData.readDataByTagName("", "duedate", "216");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "216");
            String shareName = ReadTestData.readDataByTagName("", "shareName", "216");
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", "216");

            new Assignment().create(216);

            new LoginUsingLTI().ltiLogin("216_1");		//login as student1
            new LoginUsingLTI().ltiLogin("216_2");    //login as student2
            new LoginUsingLTI().ltiLogin("216");		//login a instructor
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicy, "216 Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");
            new Assignment().assignToStudent(216);//assign non gradable question assignment with class

            //TC row no 216
            new LoginUsingLTI().ltiLogin("216_1");		//login as student1
            new Assignment().submitAssignmentAsStudent(216);    //submit the assignment


            new LoginUsingLTI().ltiLogin("216");		//login a instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getUpdateAssignment_button().click(); //click on Update Assignment link
            currentAssignments.getReassign_button().click();    //click on Re-assign button
            //TC row no 226
            new Click().clickByid("assignment-policy-icons");   //click on eye icon
            Assert.assertEquals(new TextFetch().textfetchbyclass("policy-dialog-header-text"), "Policy name", "Clicking on eye icon does not open \"View Assignment Policy\" popup.");
            new Click().clickByid("dialog-close");//close the pop up
            //TC row no 220
            String firstDueDate = currentAssignments.getDueDateOnAssignPopUp().getAttribute("value");
            if(!firstDueDate.contains(duedate)){
                Assert.fail("Due Date field does not contain the last due date for the Gradable Question assignment, irrespective of the CLASS or STUDENT.");
            }
            //TC row no 221, 223
            String extendDueDateLabel = currentAssignments.getExtendDueDateTab().getText();
            Assert.assertEquals(extendDueDateLabel, "Extend Due Date", "\"Extend Due Date\" is not visible besides \"Assignment Details\" tab for a Gradable Question assignment.");


            currentAssignments.getExtendDueDateTab().click();//click on Extend Due Date Tab
            //TC row no 224
            String assignmentName1 = currentAssignments.getAssignmentNameOnAssignPopUpList().get(1).getText();
            if(!assignmentName1.contains(assessmentname)){
                Assert.fail("The Gradable Question assignment name is not shown on the top of extend due date tab.");
            }

            //TC row no 225
            Assert.assertEquals(currentAssignments.getExtendDueDateForField().get(1).getText(), shareName, "The \"Extend due date for\" is not populated with the class section/ list of students for a Gradable Question assignment.");
            String firstDueDate1 = currentAssignments.getNewDueDateField().getAttribute("value");
            if(!firstDueDate1.contains(duedate)){
                Assert.fail("\"New Due date\" is not populated with the first \"Due Date\" for a Gradable Question assignment.");
            }

        }
        catch (Exception e){
            Assert.fail("Exception in class UpdateAssignmentWhenAssignmentGraded in method updateGradableQuestionAssignmentWithPolicyWhenAssignmentGraded.", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void noUpdateLinkAfterAssignmentSubmissionByAllStudent()
    {
        try
        {
            //TC row no. 227
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new Assignment().create(227);

            new LoginUsingLTI().ltiLogin("227_1");		//login as student1
            new LoginUsingLTI().ltiLogin("227_2");    //login as student2
            new LoginUsingLTI().ltiLogin("227");		//login a instructor
            new Assignment().assignToStudent(227);//assign non gradable question assignment with class

            new LoginUsingLTI().ltiLogin("227_1");		//login as student1
            new Assignment().submitAssignmentAsStudent(227);    //submit the assignment

            new LoginUsingLTI().ltiLogin("227_2");		//login as student1
            new Assignment().submitAssignmentAsStudent(227);    //submit the assignment*/

            new LoginUsingLTI().ltiLogin("227");		//login a instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getUpdateAssignment_button();
            boolean updateLinkFound = new BooleanValue().presenceOfElement(227, By.xpath("//span[@title='Update Assignment']"));
            Assert.assertEquals(updateLinkFound, false, "\"Update Assignment\" link is still visible after submission of the assignment by all student of the class section, as a result cannot Extend Due Date.");

        }
        catch (Exception e){
            Assert.fail("Exception in class UpdateAssignmentWhenAssignmentGraded in method noUpdateLinkAfterAssignmentSubmissionByAllStudent.", e);
        }
    }

}
