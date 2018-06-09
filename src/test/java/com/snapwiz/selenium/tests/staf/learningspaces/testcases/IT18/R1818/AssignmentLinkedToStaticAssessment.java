package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R1818;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignLesson;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SearchQuestionInCustomCourseAssignemnt;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by priyanka on 1/29/2015.
 */
public class AssignmentLinkedToStaticAssessment extends Driver {

    @Test(priority = 1, enabled = true)
    public void instructorShouldAssignTheAssignmentsLinkedToStaticAssessment() {
        try {
            //tc row no 231
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "231");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "231");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("231");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("231");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(231);
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
            Thread.sleep(3000);
            Assert.assertEquals(questionType.trim(),"Question Type:","Questiontype is not displaying in the 2nd line");
            String delete = currentAssignments.getDeleteIcon().getAttribute("title");
            Assert.assertEquals(delete,"Delete","The Delete icon is not displaying ");
            currentAssignments.getThreeDots().click();//click on three dots
            Thread.sleep(2000);
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


        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldAssignTheAssignmentsLinkedToStaticAssessment in class AssignmentLinkedToStaticAssessment", e);
        }
    }

}