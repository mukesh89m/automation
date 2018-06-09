package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R189;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*
 * Created by sumit on 31/12/14.
 */
public class UpdateAssignmentWhenAssignmentSubmitted extends Driver {

    @Test(priority = 1, enabled = true)
    public void updateNonGradableQuestionAssignmentWhenAssignmentSubmitted()
    {
        try
        {
            //TC row no. 185 - 194
            new Assignment().create(185);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            String duedate = ReadTestData.readDataByTagName("", "duedate", "185");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "185");
            String shareName = ReadTestData.readDataByTagName("", "shareName", "185");

            new LoginUsingLTI().ltiLogin("185_1");		//login as student1
            new LoginUsingLTI().ltiLogin("185_2");      //login as student2
            new LoginUsingLTI().ltiLogin("185");		//login a instructor
            new Assignment().assignToStudent(185);      //assign non gradable question assignment with class

            //TC row no 185
            new LoginUsingLTI().ltiLogin("185_1");		//login as student1
            new Assignment().submitAssignmentAsStudent(185);    //submit the assignment

            new LoginUsingLTI().ltiLogin("185");		//login a instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getUpdateAssignment_button().click(); //click on Update Assignment link
            currentAssignments.getReassign_button().click();    //click on Re-assign button
            //TC row no 189
            String firstDueDate = currentAssignments.getDueDateOnAssignPopUp().getAttribute("value");
            if(!firstDueDate.contains(duedate)){
                Assert.fail("Due Date field does not contain the last due date for the Non Gradable Question assignment, irrespective of the CLASS or STUDENT.");
            }
            //TC row no 190, 192
            String extendDueDateLabel = currentAssignments.getExtendDueDateTab().getText();
            Assert.assertEquals(extendDueDateLabel, "Extend Due Date", "\"Extend Due Date\" is not visible besides \"Assignment Details\" tab for a Non Gradable Question assignment.");


            currentAssignments.getExtendDueDateTab().click();//click on Extend Due Date Tab
            //TC row no 193
            String assignmentName1 = currentAssignments.getAssignmentNameOnAssignPopUpList().get(1).getText();
            if(!assignmentName1.contains(assessmentname)){
                Assert.fail("The Non Gradable Question assignment name is not shown on the top of extend due date tab.");
            }

            //TC row no 194
            Assert.assertEquals(currentAssignments.getExtendDueDateForField().get(1).getText(), shareName, "The \"Extend due date for\" is not populated with the class section/ list of students for a Non Gradable Question assignment.");
            String firstDueDate1 = currentAssignments.getNewDueDateField().getAttribute("value");
            if(!firstDueDate1.contains(duedate)){
                Assert.fail("\"New Due date\" is not populated with the first \"Due Date\" for a Non Gradable Question assignment.");
            }

        }
        catch (Exception e){
            Assert.fail("Exception in class UpdateAssignmentWhenAssignmentSubmitted in method updateNonGradableQuestionAssignmentWhenAssignmentSubmitted.", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void updateGradableQuestionAssignmentWithNoPolicyWhenAssignmentSubmitted()
    {
        try
        {
            //TC row no. 195 - 204
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            String duedate = ReadTestData.readDataByTagName("", "duedate", "195");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "195");
            String shareName = ReadTestData.readDataByTagName("", "shareName", "195");
            new Assignment().create(195);

            new LoginUsingLTI().ltiLogin("195_1");		//login as student1
            new LoginUsingLTI().ltiLogin("195_2");    //login as student2
            new LoginUsingLTI().ltiLogin("195");		//login a instructor
            new Assignment().assignToStudent(195);//assign non gradable question assignment with class

            //TC row no 195
            new LoginUsingLTI().ltiLogin("195_1");		//login as student1
            new Assignment().submitAssignmentAsStudent(195);    //submit the assignment


            new LoginUsingLTI().ltiLogin("195");		//login a instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getUpdateAssignment_button().click(); //click on Update Assignment link
            currentAssignments.getReassign_button().click();    //click on Re-assign button
            //TC row no 199
            String firstDueDate = currentAssignments.getDueDateOnAssignPopUp().getAttribute("value");
            if(!firstDueDate.contains(duedate)){
                Assert.fail("Due Date field does not contain the last due date for the Gradable Question assignment, irrespective of the CLASS or STUDENT.");
            }
            //TC row no 200, 202
            String extendDueDateLabel = currentAssignments.getExtendDueDateTab().getText();
            Assert.assertEquals(extendDueDateLabel, "Extend Due Date", "\"Extend Due Date\" is not visible besides \"Assignment Details\" tab for a Gradable Question assignment.");


            currentAssignments.getExtendDueDateTab().click();//click on Extend Due Date Tab
            //TC row no 203
            String assignmentName1 = currentAssignments.getAssignmentNameOnAssignPopUpList().get(1).getText();
            if(!assignmentName1.contains(assessmentname)){
                Assert.fail("The Gradable Question assignment name is not shown on the top of extend due date tab.");
            }

            //TC row no 204
            Assert.assertEquals(currentAssignments.getExtendDueDateForField().get(1).getText(), shareName, "The \"Extend due date for\" is not populated with the class section/ list of students for a Gradable Question assignment.");
            String firstDueDate1 = currentAssignments.getNewDueDateField().getAttribute("value");
            System.out.println("firstDueDate1: "+firstDueDate1);
            if(!firstDueDate1.contains(duedate)){
                Assert.fail("\"New Due date\" is not populated with the first \"Due Date\" for a Gradable Question assignment.");
            }

        }
        catch (Exception e){
            Assert.fail("Exception in class UpdateAssignmentWhenAssignmentSubmitted in method updateGradableQuestionAssignmentWithNoPolicyWhenAssignmentSubmitted.", e);
        }
    }
    @Test(priority = 3, enabled = true)
    public void updateGradableQuestionAssignmentWithPolicyWhenAssignmentSubmitted()
    {
        try
        {
            //TC row no. 205 - 215
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            String duedate = ReadTestData.readDataByTagName("", "duedate", "205");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "205");
            String shareName = ReadTestData.readDataByTagName("", "shareName", "205");
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", "205");

            new Assignment().create(205);

            new LoginUsingLTI().ltiLogin("205_1");		//login as student1
            new LoginUsingLTI().ltiLogin("205_2");    //login as student2
            new LoginUsingLTI().ltiLogin("205");		//login a instructor
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicy, "205 Policy description", "2", null, false, "1", "", null, "", "", "");
            new Assignment().assignToStudent(205);//assign non gradable question assignment with class

            //TC row no 205
            new LoginUsingLTI().ltiLogin("205_1");		//login as student1
            new Assignment().submitAssignmentAsStudent(205);    //submit the assignment


            new LoginUsingLTI().ltiLogin("205");		//login a instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            currentAssignments.getUpdateAssignment_button().click(); //click on Update Assignment link
            currentAssignments.getReassign_button().click();    //click on Re-assign button
            //TC row no 215
            new Click().clickByid("assignment-policy-icons");   //click on eye icon
            Assert.assertEquals(new TextFetch().textfetchbyclass("policy-dialog-header-text"), "Policy name", "Clicking on eye icon does not open \"View Assignment Policy\" popup.");
            new Click().clickByid("dialog-close");//close the pop up
            //TC row no 209
            String firstDueDate = currentAssignments.getDueDateOnAssignPopUp().getAttribute("value");
            if(!firstDueDate.contains(duedate)){
                Assert.fail("Due Date field does not contain the last due date for the Gradable Question assignment, irrespective of the CLASS or STUDENT.");
            }
            //TC row no 210, 212
            String extendDueDateLabel = currentAssignments.getExtendDueDateTab().getText();
            Assert.assertEquals(extendDueDateLabel, "Extend Due Date", "\"Extend Due Date\" is not visible besides \"Assignment Details\" tab for a Gradable Question assignment.");


            currentAssignments.getExtendDueDateTab().click();//click on Extend Due Date Tab
            //TC row no 213
            String assignmentName1 = currentAssignments.getAssignmentNameOnAssignPopUpList().get(1).getText();
            if(!assignmentName1.contains(assessmentname)){
                Assert.fail("The Gradable Question assignment name is not shown on the top of extend due date tab.");
            }

            //TC row no 214
            Assert.assertEquals(currentAssignments.getExtendDueDateForField().get(1).getText(), shareName, "The \"Extend due date for\" is not populated with the class section/ list of students for a Gradable Question assignment.");
            String firstDueDate1 = currentAssignments.getNewDueDateField().getAttribute("value");
            if(!firstDueDate1.contains(duedate)){
                Assert.fail("\"New Due date\" is not populated with the first \"Due Date\" for a Gradable Question assignment.");
            }

        }
        catch (Exception e){
            Assert.fail("Exception in class UpdateAssignmentWhenAssignmentSubmitted in method updateGradableQuestionAssignmentWithPolicyWhenAssignmentSubmitted.", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void updateResourceAssignmentWhenAssignmentSubmitted()
    {
        try
        {
            //TC row no. 145
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            String duedate = ReadTestData.readDataByTagName("", "duedate", "145");
            String resourcesname = ReadTestData.readDataByTagName("", "resourcesname", "145");
            String shareName = ReadTestData.readDataByTagName("", "shareName", "145");

            new LoginUsingLTI().ltiLogin("145_1");		//login as student1
            new LoginUsingLTI().ltiLogin("145_2");    //login as student2

            new LoginUsingLTI().ltiLogin("145");		//login a instructor
            new UploadResources().uploadResources("145", true, false, true);//upload resource
            new AssignLesson().assignResourceFromMyResources(145);//assign with class

            //TC row no 145
            new LoginUsingLTI().ltiLogin("145_1");		//login as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getLessonAssignment().click();//open resource assignment

            new LoginUsingLTI().ltiLogin("145");		//login a instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to Current Assignments
            currentAssignments.getUpdateAssignment_button().click(); //click on Update Assignment link
            //TC row no 149
            String firstDueDate = currentAssignments.getDueDateOnAssignPopUp().getAttribute("value");
            if(!firstDueDate.contains(duedate)){
                Assert.fail("Due Date field does not contain the last due date for the lesson assignment, irrespective of the CLASS or STUDENT.");
            }
            //TC row no 150, 152
            String extendDueDateLabel = currentAssignments.getExtendDueDateTab().getText();
            Assert.assertEquals(extendDueDateLabel, "Extend Due Date", "\"Extend Due Date\" is not visible besides \"Assignment Details\" tab for a lesson assignment.");

            currentAssignments.getExtendDueDateTab().click();//click on Extend Due Date Tab
            //TC row no 153
            if( System.getProperty("UCHAR") == null) {
               resourcesname = resourcesname+LoginUsingLTI.appendChar;
            }
            else {
               resourcesname = resourcesname+ System.getProperty("UCHAR");
            }
            Assert.assertEquals(currentAssignments.getAssignmentNameOnAssignPopUpList().get(1).getText(), resourcesname, "The lesson assignment name is not shown on the top of extend due date tab.");

            //TC row no 154
            Assert.assertEquals(currentAssignments.getExtendDueDateForField().get(1).getText(), shareName, "The \"Extend due date for\" is not populated with the class section/ list of students for a lesson assignment.");
            String firstDueDate1 = currentAssignments.getNewDueDateField().getAttribute("value");
            if(!firstDueDate1.contains(duedate)){
                Assert.fail("\"New Due date\" is not populated with the first \"Due Date\" for a lesson assignment.");
            }

        }
        catch (Exception e){
            Assert.fail("Exception in class UpdateAssignmentWhenAssignmentSubmitted in method updateResourceAssignmentWhenAssignmentSubmitted.", e);
        }
    }

}
