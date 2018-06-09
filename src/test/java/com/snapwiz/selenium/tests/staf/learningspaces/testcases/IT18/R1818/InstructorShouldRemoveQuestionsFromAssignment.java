package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R1818;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by priyanka on 1/9/2015.
 */
public class InstructorShouldRemoveQuestionsFromAssignment extends Driver{
    @Test(priority = 1, enabled = true)
    public void instructorShouldRemoveQuestionsFromAssignment() {
        try {
            //tc row no 10
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "10");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "10");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver,MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("10");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("10");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(10);
            String authorName=currentAssignments.getAuthorNmae().getText();
            currentAssignments.getUpdateAssignment_button().click();//click on update assignment
            String assignment = currentAssignments.getCreatedAssignmentTitle().getAttribute("title");
            Assert.assertEquals(assignment,"New Name","New tab is not opened in current assignment page");
            String assignmentTitle = currentAssignments.getCreatedAssignmentName().getText();
            Assert.assertEquals(assignmentTitle,"New Name","Assignment name is not displaying");
            String cancel = currentAssignments.getCancelButton().getText();
            Assert.assertEquals(cancel,"Cancel","Cancel button is not displaying");
            String reAssign = currentAssignments.getReassign_button().getText();
            Assert.assertEquals(reAssign,"RE-ASSIGN","RE-ASSIGN is not displaying");
            int question = currentAssignments.getQuestion().size();
            if ( question == 0){
                Assert.fail("All the questions are not displaying in card view");
            }
            String quesionNo=currentAssignments.getQuestionNo().getText();
            Assert.assertEquals(quesionNo,"Q1:","question no is not displaying");
            String questionType=currentAssignments.getQuestionType().getText();
            Assert.assertEquals(questionType.trim(),"Question Type:","Questiontype is not displaying in the 2nd line");
            String delete = currentAssignments.getDeleteIcon().getAttribute("title");
            Assert.assertEquals(delete,"Delete","The Delete icon is not displaying ");
            currentAssignments.getThreeDots().click();//click on three dots
            Thread.sleep(2000);
            //tc row no 20
            currentAssignments.getCloseButton().click();//click on close button
            if(!authorName.contains("family, givenname")){
                Assert.fail("Changes has been done after click on close symbol");
            }
            currentAssignments.getUpdateAssignment_button().click();//click on update assignment
            currentAssignments.getCancelButton().click();//click on cancel
            if(!authorName.contains("family, givenname")){
                Assert.fail("Changes has been done after click on close symbol");
            }

            currentAssignments.getUpdateAssignment_button().click();//click on update assignment
            currentAssignments.getThreeDots().click();//click on three dots
            currentAssignments.getExpendQuestion().click();//click on expand icon
            String difficultyLevel = currentAssignments.getDifficultyLevel().getText();
            Assert.assertEquals(difficultyLevel,"Difficulty Level:","Question is not expanded after clicking on three dots");
            boolean expandIcon=currentAssignments.getExpandIcon().isDisplayed();
            Assert.assertEquals(expandIcon,true,"The symbol is not converted to collapse icon");
            currentAssignments.getExpandIcon().click();//click on expand icon
            boolean threedots=currentAssignments.getThreeDots().isDisplayed();
            Assert.assertEquals(threedots,true,"The symbol is not converted to three dots");
            boolean difficulty=currentAssignments.getDifficultyLevel().isDisplayed();
            Assert.assertEquals(difficulty,false,"The question is not collapsed");
            currentAssignments.getDeleteIcon().click();
            String message=currentAssignments.getNotificationMessage().get(0).getText().trim();
            Assert.assertEquals(message,"The question would be deleted from this assignment. Do you want to proceed? Yes | No","Notification message is not displaying");
            String close=currentAssignments.getCloseButtonOnNotificationMessage().getAttribute("title");
            Assert.assertEquals(close,"Close","close icon is not displaying");
            currentAssignments.getCloseButtonOnNotificationMessage().click();
            int notification=currentAssignments.getNotificationMessage().size();
            Assert.assertEquals(notification, 0, "Notification message is displaying");
            currentAssignments.getDeleteIcon().click();//click on delete
            currentAssignments.getNoOnNotificationMessage().click();//click on no
            int notification1=currentAssignments.getNotificationMessage().size();
            Assert.assertEquals(notification1,0,"Notification message is displaying");
            int question1 = currentAssignments.getQuestion().size();
            if ( question1 == 0){
                Assert.fail("All the questions are not displaying in card view");
            }
            currentAssignments.getDeleteIcon().click();//click on delete
            currentAssignments.getYesOnNotificationMessage().click();//click on yes
            Thread.sleep(2000);
            int question2 = currentAssignments.getQuestion().size();
            if ( question2 > 2){
                Assert.fail("One question is not deleted");
            }

            String elementFound=currentAssignments.getDeleteIcon().getAttribute("class");
            Assert.assertEquals(elementFound,"visible invisible","delete icon is displaying");
            currentAssignments.getCancelButton().click();//click on cancel
            if(!authorName.contains("family, givenname")){
                Assert.fail("Changes has been done after click on close symbol");
            }

            currentAssignments.getUpdateAssignment_button().click();//click on update assignment
            currentAssignments.getDeleteIcon().click();//click on delete
            currentAssignments.getYesOnNotificationMessage().click();//click on yes
            currentAssignments.getReassign_button().click();//click on reassign
            String popupTitle = currentAssignments.getPopUpTitle().getAttribute("title");
            Assert.assertEquals(popupTitle,"New Name","popup window is not opened");
            String cancelPopup=currentAssignments.getCancelPopup().getText();
            Assert.assertEquals(cancelPopup,"Cancel","cancel link is not displaying");
            String assign=currentAssignments.getAssign().getText();
            Assert.assertEquals(assign,"Assign","Assign link is not displaying");
            currentAssignments.getCancelPopup().click();//click on cancel in popup
            int question3 = currentAssignments.getQuestion().size();
            if ( question3 > 2){
                Assert.fail("changes are affected");
            }
            String assignmentTitle1 = currentAssignments.getCreatedAssignmentName().getText();
            Assert.assertEquals(assignmentTitle1,"New Name","Assignment name is not displaying");
            currentAssignments.getReassign_button().click();//click on reassign
            currentAssignments.getAssign().click();//click on assign
            Thread.sleep(1000);
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            String resourceTitle = myQuestionBank.getResourceTitle().getText();
            Assert.assertEquals(resourceTitle,"New Name - V1","The copied assessment name is not displaying with format “Assessment Name - V1”. ");
            myQuestionBank.getPreviewButton().click();//click on preview of “Assessment Name - V1”
            int questionCount = myQuestionBank.getQuestionCount().size();
            if(questionCount > 1){
                Assert.fail("List of question preview except the deleted question is not displaying");
            }

        } catch (Exception e) {
        Assert.fail("Exception in test case instructorShouldRemoveQuestionsFromAssignment in class InstructorShouldRemoveQuestionsFromAssignment", e);
    }
}
    @Test(priority = 2, enabled = true)
    public void deleteOptionIfGradesAreReleasedForOneStudentWithPolicyOptionOne() {
        try {
            //tc row no 38
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "38");
            new LoginUsingLTI().ltiLogin("38");//login as instructor
            new Assignment().create(38);
            new LoginUsingLTI().ltiLogin("38_1");//login as student1
            new LoginUsingLTI().ltiLogin("38_2");//login as student2
            new LoginUsingLTI().ltiLogin("38");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");//till save policy
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(38);//assign to student
            new LoginUsingLTI().ltiLogin("38_1");//login as student1
            new Assignment().submitAssignmentAsStudent(38); //submit assignment
            new LoginUsingLTI().ltiLogin("38");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.getUpdateAssignment_button().click();//click on update assignment
            String elementFound=currentAssignments.getDeleteIcon().getAttribute("class");
            Assert.assertEquals(elementFound,"invisible","delete icon is displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case deleteOptionIfGradesAreReleasedForOneStudentWithPolicyOptionOne in class InstructorShouldRemoveQuestionsFromAssignment", e);
        }
    }
    @Test(priority = 3, enabled = true)
    public void deleteOptionIfGradesAreReleasedForOneStudentWithPolicyOptionThree() {
        try {
            //tc row no 39
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "39");
            new LoginUsingLTI().ltiLogin("39");//login as instructor
            new Assignment().create(39);
            new LoginUsingLTI().ltiLogin("39_1");//login as student1
            new LoginUsingLTI().ltiLogin("39_2");//login as student2
            new LoginUsingLTI().ltiLogin("39");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "");//till save policy
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(39);//assign to student
            new LoginUsingLTI().ltiLogin("39_1");//login as student1
            new Assignment().submitAssignmentAsStudent(39); //submit assignment
            new LoginUsingLTI().ltiLogin("39");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.getUpdateAssignment_button().click();//click on update assignment
            String elementFound=currentAssignments.getDeleteIcon().getAttribute("class");
            Assert.assertEquals(elementFound,"invisible","delete icon is displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case deleteOptionIfGradesAreReleasedForOneStudentWithPolicyOptionThree in class InstructorShouldRemoveQuestionsFromAssignment", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void  allTheStudentsHaveSubmittedTheGradableAssignment() {
        try {
            //tc row no 40
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "40");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "40");
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver,MyQuestionBank.class);
            new LoginUsingLTI().ltiLogin("40_1");//create student1
            new LoginUsingLTI().ltiLogin("40_2");//create student2
            new LoginUsingLTI().ltiLogin("40");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("40");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(40);
            new LoginUsingLTI().ltiLogin("40_1");//login as student1
            new Assignment().submitAssignmentAsStudent(40); //submit assignment as student1
            new LoginUsingLTI().ltiLogin("40_2");//login as student2
            new Assignment().submitAssignmentAsStudent(40); //submit assignment as student2
            new LoginUsingLTI().ltiLogin("40");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            boolean elementFound = false;
            try{
                driver.findElement(By.className("assign-more"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,false,"Update Assignment link is displayed");


        } catch (Exception e) {
            Assert.fail("Exception in test case allTheStudentsHaveSubmittedTheGradableAssignment in class InstructorShouldRemoveQuestionsFromAssignment", e);
        }
    }


}
