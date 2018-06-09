package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.Epic2;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.preview.PreviewPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.ExplicitWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 * Created by Dharaneesha on 17-Dec-14.
 */
public class AllowEditingOfQuestionsForAssignmentsInDraftStatus extends Driver {


    @Test(priority = 1,enabled = true)
    public void instructorToBeAbleToEditTrueFalseQuestions(){
        try{
            int index = 20;
            String appendChar = "A";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar,index);//create class
            new Assignment().create(index, "truefalse");
            completeQuestionEditProcess_newAssessment(index);
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Assignment().selectExistingRecentAssignment(index);
            completeUseExistingAssessmentProcess(index);

        }catch(Exception e){
            Assert.fail("Exception in testcase 'instructorToBeAbleToEditTrueFalseQuestions' in the class 'AllowEditingOfQuestionsForAssignmentsInDraftStatus'",e);
        }
    }






    @Test(priority = 2,enabled = true)
    public void instructorToBeAbleToEditMultipleChoiceQuestions(){
        try{
            int index = 27;
            String appendChar = "A";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar, index);//create class
            new Assignment().create(index, "multiplechoice");
            completeQuestionEditProcess_newAssessment(index);
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Assignment().selectExistingRecentAssignment(index);//Select Existing assignments
            completeUseExistingAssessmentProcess(index);

        }catch(Exception e){
            Assert.fail("Exception in testcase 'instructorToBeAbleToEditMultipleChoiceQuestions' in the class 'AllowEditingOfQuestionsForAssignmentsInDraftStatus'",e);
        }
    }


    @Test(priority = 3,enabled = true)
    public void instructorToBeAbleToEditMultipleSelectionQuestions(){
        try{
            int index = 34;
            String appendChar = "A";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar,index);//create class
            new Assignment().create(index, "multipleselection");
            completeQuestionEditProcess_newAssessment(index);
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Assignment().selectExistingRecentAssignment(index);//Select Existing assignments
            completeUseExistingAssessmentProcess(index);

        }catch(Exception e){
            Assert.fail("Exception in testcase 'instructorToBeAbleToEditMultipleSelectionQuestions' in the class 'AllowEditingOfQuestionsForAssignmentsInDraftStatus'",e);
        }
    }



    @Test(priority = 4,enabled = true)
    public void instructorToBeAbleToEditTextEntryQuestions(){
        try{
            int index = 41;
            String appendChar = "A";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar,index);//create class
            new Assignment().create(index, "textentry");
            completeQuestionEditProcess_newAssessment(index);
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Assignment().selectExistingRecentAssignment(index);//Select Existing assignments
            completeUseExistingAssessmentProcess(index);

        }catch(Exception e){
            Assert.fail("Exception in testcase 'instructorToBeAbleToEditTextEntryQuestions' in the class 'AllowEditingOfQuestionsForAssignmentsInDraftStatus'",e);
        }
    }




    @Test(priority = 5,enabled = true)
    public void instructorToBeAbleToEditTextDropDownQuestions(){
        try{
            int index = 48;
            String appendChar = "f";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar,index);//create class
            new Assignment().create(index, "textdropdown");
            completeQuestionEditProcess_newAssessment(index);
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Assignment().selectExistingRecentAssignment(index);//Select Existing assignments
            completeUseExistingAssessmentProcess(index);

        }catch(Exception e){
            Assert.fail("Exception in testcase 'instructorToBeAbleToEditTextDropDownQuestions' in the class 'AllowEditingOfQuestionsForAssignmentsInDraftStatus'",e);
        }
    }




    @Test(priority = 6,enabled = true)
    public void instructorToBeAbleToEditNumericEntryWithUnitsQuestions(){
        try{
            int index = 55;
            String appendChar = "A";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar,index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "numericentrywithunits");

            completeQuestionEditProcess_newAssessment(index);
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Assignment().selectExistingRecentAssignment(index);//Select Existing assignments
            completeUseExistingAssessmentProcess(index);

        }catch(Exception e){
            Assert.fail("Exception in testcase 'instructorToBeAbleToEditNumericEntryWithUnitsQuestions' in the class 'AllowEditingOfQuestionsForAssignmentsInDraftStatus'",e);
        }
    }



    @Test(priority = 7,enabled = true)
    public void instructorToBeAbleToEditAdvancedNumericQuestions(){
        try{
            int index = 62;
            String appendChar = "A";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar,index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "advancednumeric");

            completeQuestionEditProcess_newAssessment(index);
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Assignment().selectExistingRecentAssignment(index);//Select Existing assignments
            completeUseExistingAssessmentProcess(index);

        }catch(Exception e){
            Assert.fail("Exception in testcase 'instructorToBeAbleToEditAdvancedNumericQuestions' in the class 'AllowEditingOfQuestionsForAssignmentsInDraftStatus'",e);
        }
    }



    @Test(priority = 8,enabled = true)
    public void instructorToBeAbleToEditExpressionEvaluatorsQuestions(){
        try{
            int index = 69;
            String appendChar = "d";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar,index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "expressionevaluator");

            completeQuestionEditProcess_newAssessment(index);
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Assignment().selectExistingRecentAssignment(index);//Select Existing assignments
            completeUseExistingAssessmentProcess(index);

        }catch(Exception e){
            Assert.fail("Exception in testcase 'instructorToBeAbleToEditExpressionEvaluatorsQuestions' in the class 'AllowEditingOfQuestionsForAssignmentsInDraftStatus'",e);
        }
    }



    @Test(priority = 9,enabled = true)
    public void instructorToBeAbleToEditMatchTheFollowingQuestions(){
        try{
            int index = 76;
            String appendChar = "A";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar,index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "matchthefollowing");

            completeQuestionEditProcess_newAssessment(index);
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Assignment().selectExistingRecentAssignment(index);//Select Existing assignments
            completeUseExistingAssessmentProcess(index);

        }catch(Exception e){
            Assert.fail("Exception in testcase 'instructorToBeAbleToEditMatchTheFollowingQuestions' in the class 'AllowEditingOfQuestionsForAssignmentsInDraftStatus'",e);
        }
    }



    @Test(priority = 10,enabled = true)
    public void instructorToBeAbleToEditDragAndDropQuestions(){
        try{
            int index = 83;
            String appendChar = "A";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar,index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "draganddrop");

            completeQuestionEditProcess_newAssessment(index);
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Assignment().selectExistingRecentAssignment(index);//Select Existing assignments
            completeUseExistingAssessmentProcess(index);

        }catch(Exception e){
            Assert.fail("Exception in testcase 'instructorToBeAbleToEditDragAndDropQuestions' in the class 'AllowEditingOfQuestionsForAssignmentsInDraftStatus'",e);
        }
    }



    @Test(priority = 11,enabled = true)
    public void instructorToBeAbleToEditEssayQuestions(){
        try{
            int index = 90;
            String appendChar = "A";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar,index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "essay");

            completeQuestionEditProcess_newAssessment(index);
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Assignment().selectExistingRecentAssignment(index);//Select Existing assignments
            completeUseExistingAssessmentProcess(index);

        }catch(Exception e){
            Assert.fail("Exception in testcase 'instructorToBeAbleToEditEssayQuestions' in the class 'AllowEditingOfQuestionsForAssignmentsInDraftStatus'",e);
        }
    }


    @Test(priority = 12,enabled = true)
    public void instructorToBeAbleToEditLabelAnImageTextQuestions(){
        try{
            int index = 97;
            String appendChar = "A";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar,index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "labelanimagetext");

            completeQuestionEditProcess_newAssessment(index);
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Assignment().selectExistingRecentAssignment(index);//Select Existing assignments
            completeUseExistingAssessmentProcess(index);

        }catch(Exception e){
            Assert.fail("Exception in testcase 'instructorToBeAbleToEditLabelAnImageTextQuestions' in the class 'AllowEditingOfQuestionsForAssignmentsInDraftStatus'",e);
        }
    }



    @Test(priority = 13,enabled = true)
    public void instructorToBeAbleToEditLabelAnImageDropDownQuestions(){
        try{
            int index = 104;
            String appendChar = "A";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar,index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "labelanimagedropdown");

            completeQuestionEditProcess_newAssessment(index);
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Assignment().selectExistingRecentAssignment(index);//Select Existing assignments
            completeUseExistingAssessmentProcess(index);

        }catch(Exception e){
            Assert.fail("Exception in testcase 'instructorToBeAbleToEditLabelAnImageDropDownQuestions' in the class 'AllowEditingOfQuestionsForAssignmentsInDraftStatus'",e);
        }
    }


    @Test(priority = 14,enabled = true)
    public void instructorToBeAbleToEditResequenceQuestions(){
        try{
            int index = 111;
            String appendChar = "A";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar,index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "resequence");

            completeQuestionEditProcess_newAssessment(index);
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Assignment().selectExistingRecentAssignment(index);//Select Existing assignments
            completeUseExistingAssessmentProcess(index);

        }catch(Exception e){
            Assert.fail("Exception in testcase 'instructorToBeAbleToEditResequenceQuestions' in the class 'AllowEditingOfQuestionsForAssignmentsInDraftStatus'",e);
        }
    }



    @Test(priority = 15,enabled = true)
    public void instructorToBeAbleToEditClozeMatrixQuestions(){
        try{
            int index = 118;
            String appendChar = "A";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar,index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "clozematrix");
            completeQuestionEditProcess_newAssessment(index);
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Assignment().selectExistingRecentAssignment(index);//Select Existing assignments
            completeUseExistingAssessmentProcess(index);

        }catch(Exception e){
            Assert.fail("Exception in testcase 'instructorToBeAbleToEditClozeMatrixQuestions' in the class 'AllowEditingOfQuestionsForAssignmentsInDraftStatus'",e);
        }
    }



    @Test(priority = 16,enabled = true)
    public void instructorToBeAbleToEditGraphPlotterQuestions(){
        try{
            int index = 125;
            String appendChar = "A";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar,index);//create class
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "graphplotter");

            completeQuestionEditProcess_newAssessment(index);
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Assignment().selectExistingRecentAssignment(index);//Select Existing assignments
            completeUseExistingAssessmentProcess(index);

        }catch(Exception e){
            Assert.fail("Exception in testcase 'instructorToBeAbleToEditGraphPlotterQuestions' in the class 'AllowEditingOfQuestionsForAssignmentsInDraftStatus'",e);
        }
    }








           /*>>>>>>>>>>>>>>>>>>>>>>>>>>From 200th row>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>*/











    @Test(priority = 17,enabled = true)
    public void instructorToBeAbleToClickPreviewTextDropdownQuestions(){
        try{
            int index = 200;
            String appendChar = "A";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar,index);//create class
            new Assignment().create(index, "textdropdown");
            completeQuestionEditProcess_useExisting(index);
        }catch(Exception e){
            Assert.fail("Exception in testcase 'instructorToBeAbleToEditGraphPlotterQuestions' in the class 'AllowEditingOfQuestionsForAssignmentsInDraftStatus'",e);
        }
    }


    @Test(priority = 18,enabled = true)
    public void instructorToBeAbleToClickPreviewNumericEntryWithUnitsQuestions(){
        try{
            int index = 212;
            String appendChar = "A";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar,index);//create class
            new Assignment().create(index, "numericentrywithunits");
            completeQuestionEditProcess_useExisting(index);
        }catch(Exception e){
            Assert.fail("Exception in testcase 'instructorToBeAbleToClickPreviewNumericEntryWithUnitsQuestions' in the class 'AllowEditingOfQuestionsForAssignmentsInDraftStatus'",e);
        }
    }




    @Test(priority = 19,enabled = true)
    public void instructorToBeAbleToClickPreviewAdvancedNumericQuestions(){
        try{
            int index = 224;
            String appendChar = "A";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar,index);//create class
            new Assignment().create(index, "advancednumeric");
            completeQuestionEditProcess_useExisting(index);
        }catch(Exception e){
            Assert.fail("Exception in testcase 'instructorToBeAbleToClickPreviewAdvancedNumericQuestions' in the class 'AllowEditingOfQuestionsForAssignmentsInDraftStatus'",e);
        }
    }





    @Test(priority = 20,enabled = true)
    public void instructorToBeAbleToClickPreviewExpressionEvaluatorQuestions(){
        try{
            int index = 236;
            String appendChar = "C";
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            new Classes().createClass(appendChar,index);//create class
            new Assignment().create(index, "expressionevaluator");
            completeQuestionEditProcess_useExisting(index);
        }catch(Exception e){
            Assert.fail("Exception in testcase 'instructorToBeAbleToClickPreviewExpressionEvaluatorQuestions' in the class 'AllowEditingOfQuestionsForAssignmentsInDraftStatus'",e);
        }
    }




    public void completeQuestionEditProcess_newAssessment(int index){

        try{
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));

            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);
            Thread.sleep(2000);
            pf_assignments.getButton_review().click();//Click on 'Review' Button
            pf_assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            pf_assignments.getButton_ViewDraftStatus().click();//Click on 'View draft status' button*/
            pf_assignments.getAssessmentName().click();//Click on Assessment name*/
            String questionID = pf_assignments.getLabelValue_QuestionID().getText();
            String questionText =  pf_assignments.getCreateAssignmentQuestionName().getText();
            String tloText = questionsListPage.getLabelValue_StandardTlo().getText();

            Assert.assertEquals(tloText,"STANDARD : 1.OA, 1.OA.A.1","TLO is not displayed properly");
            pf_assignments.getRightArrow().click();//Click on Question's Right arrow*/
            String questionTextTokens[] = questionText.split(":");


            //Expected - 1 : The particular question should be opened



            if(!(index == 69)){

                int indexOf4 = pf_assQuestion.getLabel_questionName().getText().indexOf('4');

                //Expected - 1 : The particular question should be opened
                if(!questionText.contains(pf_assQuestion.getLabel_questionName().getText().substring(0,indexOf4+2))){
                    Assert.fail("The particular question is not opened.");
                }
            }else{
                new ExplicitWait().explicitWaitByClass("control-label",3);
                System.out.println("pf_assQuestion.getLabel_questionName().getText() : "+ pf_assQuestion.getLabel_questionName().getText());
                System.out.println("questionTextTokens[1].trim() : " + questionTextTokens[1].trim());
                String []  questionTextTokensSet = questionTextTokens[1].trim().split("\n");
                System.out.println("questionTextTokensSet : " + questionTextTokensSet[0]);

                if(!(pf_assQuestion.getLabel_questionName().getText().trim().contains(questionTextTokensSet[0].trim()))){
                    Assert.fail("The particular question is not opened");
                }
/*
            if(!(pf_assQuestion.getLabel_questionName().getText().trim().contains(questionTextTokens[1].trim()))){
                Assert.fail("The particular question is not opened");
            }*/
            }




            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(pf_assQuestion.getLabelValue_QuestionID().getText().trim(),questionID.trim(),"ID of the question is not displayed");


            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals("Owner: "+pf_assQuestion.getLabelValue_Owner().getText(),"Owner: You","Owner of the assignment is not displayed");

            //Expected - 4 : Edit and delete option must be displayed
            Assert.assertEquals(pf_assQuestion.getButton_Edit().getText(),"Edit","Edit Option is not displayed");
            Assert.assertEquals(pf_assQuestion.getButton_Delete().getText(), "Delete", "Delete Option is not displayed");

            //Expected - 5 : "  Preview button must be displayed"
            Assert.assertEquals(pf_assQuestion.getButton_Preview().getText(),"Preview","Preview Option is not displayed");


           /*Shuffle Answer Removed

           //Expected - 6 : ""Shuffle answer"" check box must be displayed as set by the instructor"
            if(index == 20 || index == 27 ||index == 34 ||index == 83) {
                boolean isSelected = pf_assQuestion.getCheckBox_shuffleAnswerChoices().isSelected();
                Assert.assertNotEquals(isSelected, true, "Shuffle answer check is displayed as set by the instructor");
                boolean isDisplayed = pf_assQuestion.getCheckBox_shuffleAnswerChoices().isDisplayed();
                Assert.assertEquals(isDisplayed, true, "Shuffle answer check is not displayed as set by the instructor");
            }*/


            //Expected - "7. TLO and ELO should be displayed properly
            Assert.assertEquals(pf_assQuestion.getLabel_tlo().getText(),"1.OA - Operations & Algebraic Thinking","TLO is not displayed properly");
            Assert.assertEquals(pf_assQuestion.getLabel_elo().getText(),"1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to","ELO is not displayed properly");



            //Row No - 25 : 6. Click on "Edit option"
            //Expected - 8.Instructor must be taken to the question edit mode and he should be able to edit the question now
           /* Row No - 26 : "7. Edit the question
            8. Click on save"*/
            //Expected - 9. Changes should be saved and reflected on the question




            if(index ==20) {
            pf_assQuestion.getButton_Edit().click();//CLick on 'Edit' button
            Thread.sleep(2000);
            questionEditPage.getTextBox_QuestionEditField().clear();//Clear the question
            questionEditPage.getTextBox_QuestionEditField().sendKeys("new " + questiontext);//Type the new question*/
            questionEditPage.getButton_Save().click();
            Assert.assertEquals(questionEditPage.getTextBox_QuestionEditField().getText(),"new "+questiontext,"Instructor is not able to edit the question");
            }


            if(index ==27){
                pf_assQuestion.getButton_Edit().click();//CLick on 'Edit' button
                Thread.sleep(2000);
                questionEditPage.getTextBox_MultipleChoiceQuestionEditField().clear();//Clear the question
                questionEditPage.getTextBox_MultipleChoiceQuestionEditField().sendKeys("new " + questiontext);//Type the new question
                questionEditPage.getButton_Save().click();
                Assert.assertEquals(questionEditPage.getTextBox_MultipleChoiceQuestionEditField().getText(),"new "+questiontext,"Instructor is not able to edit the question");
            }


            if(index ==34){
                pf_assQuestion.getButton_Edit().click();//CLick on 'Edit' button
                Thread.sleep(2000);
                questionEditPage.getTextBox_MultipleSelectionEditField().clear();//Clear the question
                questionEditPage.getTextBox_MultipleSelectionEditField().sendKeys("new " + questiontext);//Type the new question
                questionEditPage.getButton_Save().click();
                Assert.assertEquals(questionEditPage.getTextBox_MultipleSelectionEditField().getText(),"new "+questiontext,"Instructor is not able to edit the question");
            }

            if(index ==41) {
                pf_assQuestion.getButton_Edit().click();//CLick on 'Edit' button
                Thread.sleep(2000);
                questionEditPage.getTextBox_CorrectAnswerField().clear();//Clear the Correct answer text field
                questionEditPage.getTextBox_CorrectAnswerField().sendKeys("Wrong answer");//Replace the Correct answer text field with "Wrong answer"
                questionEditPage.getButton_AcceptAnswer_TextEntry().click();// CLick on 'Accept answer' Button
                questionEditPage.getButton_Save().click();// Click on 'Save' Button
                String actual = questionEditPage.getTextBox_QuestionPostEditField_TextEntry().getAttribute("value");
                String expected = "Wrong answer";
                Assert.assertEquals(actual,expected,"Instructor is not able to edit the question");
            }
            if(index ==48) {
                pf_assQuestion.getButton_Edit().click();//CLick on 'Edit' button
                Thread.sleep(2000);
                questionEditPage.getTextBox_AnswerEditField().click();//Click on Answer Choice 1
                questionEditPage.getTextBox_Answer1EditField().sendKeys("Answer4");//Edit Answer Choice 1 by Answer4
                questionEditPage.getIcon_Answer1SelectSymbol().click();//Click on select symbol
                questionEditPage.getButton_AcceptAnswer().click();// Click on button 'Accept Answer'
                questionEditPage.getButton_Save().click();//Click on Save Button
                String actual = questionEditPage.getTextBox_Answer1PostEditField().getText();
                String expected = "Answer4Answer1";
                Assert.assertEquals(actual,expected,"Instructor is not able to edit the question");
            }
            if(index ==55) {
                pf_assQuestion.getButton_Edit().click();//CLick on 'Edit' button
                Thread.sleep(2000);
                questionEditPage.getTextBox_CorrectAnswerEditField().clear();//Clear the Correct answer text field
                questionEditPage.getTextBox_CorrectAnswerEditField().sendKeys("90");////Replace the Correct answer text field with "90"
                questionEditPage.getButton_AcceptAnswer_NumericEntry().click();// CLick on 'Accept answer' Button
                questionEditPage.getButton_Save().click();// Click on 'Save' Button
                String actual = questionEditPage.getTextBox_QuestionPostEditField_TextEntry().getAttribute("value");//get the text from question's edit field after edit.
                String expected = "90";
                Assert.assertEquals(actual,expected,"Instructor is not able to edit the question");
            }
            if(index ==62) {
                pf_assQuestion.getButton_Edit().click();//CLick on 'Edit' button
                Thread.sleep(2000);
                questionEditPage.getTextField_CorrectAnswer_AdvancedNumeric().clear();//Clear the Correct answer text field
                questionEditPage.getTextField_CorrectAnswer_AdvancedNumeric().sendKeys("90");////Replace the Correct answer text field with "90"
                questionEditPage.getButton_AcceptAnswer_AdvancedNumeric().click();// CLick on 'Accept answer' Button
                questionEditPage.getButton_Save().click();// Click on 'Save' Button
                String actual = questionEditPage.getTextBox_QuestionPostEditField_TextEntry().getAttribute("value");//get the text from question's edit field after edit.
                String expected = "90";
                Assert.assertEquals(actual,expected,"Instructor is not able to edit the question");
            }
            if(index ==69) {
                pf_assQuestion.getButton_Edit().click();//CLick on 'Edit' button
                Thread.sleep(2000);
                questionEditPage.getTextField_CorrectAnswer_expressionEvaluator().click();//Click on Correct answer text field space to open a Math editor window
                questionEditPage.getButton_SquareRoot_expressionEvaluator().click();//Click on 'Square root' icon
                questionEditPage.getTextArea_CorrectAnswerEditWindow_expressionEvaluator().sendKeys("90");//Type value 90
                questionEditPage.getButton_Save_expressionEvaluator().click();//Click on Save Button in Math Editor
                questionEditPage.getButton_AcceptAnswer_expressionEvaluator().click();//Click on 'Accept answer' button
                questionEditPage.getButton_Save().click();// Click on 'Save' Button
                /*String actual = questionEditPage.getTextBox_formulaTypeAnswerPostEditField().getText();
                String expected =  "590";
                Assert.assertEquals(actual,expected,"Instructor is not able to edit the question");*/
                if(!(driver.findElements(By.xpath("//div[@id = 'answer_math_edit']//span[@class = 'mn']")).get(0).getText().equals("5") &&driver.findElements(By.xpath("//div[@id = 'answer_math_edit']//span[@class = 'mn']")).get(1).getText().equals("90"))){
                    Assert.fail("Instructor is not able to edit the question");
                }
            }
            if(index ==76) {
                String quesText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
                pf_assQuestion.getButton_Edit().click();//CLick on 'Edit' button
                Thread.sleep(2000);
                questionEditPage.getTextBox_QuestionEditField().clear();
                questionEditPage.getTextBox_QuestionEditField().sendKeys("New " + quesText);
                questionEditPage.getButton_Save().click();// Click on 'Save' Button
                String actual = questionEditPage.getTextBox_QuestionEditField().getText();
                String expected =  "New "+quesText;
                Assert.assertEquals(actual,expected,"Instructor is not able to edit the question");
            }

            if(index ==83) {
                String quesText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
                pf_assQuestion.getButton_Edit().click();//CLick on 'Edit' button
                Thread.sleep(2000);
                questionEditPage.getTextBox_QuestionEditField().clear();
                questionEditPage.getTextBox_QuestionEditField().sendKeys("New " + quesText);
                questionEditPage.getButton_Save().click();// Click on 'Save' Button
                String actual = questionEditPage.getTextBox_QuestionEditField().getText();
                String expected =  "New "+quesText;
                Assert.assertEquals(actual,expected,"Instructor is not able to edit the question");
            }

            if(index ==90) {
                String quesText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
                pf_assQuestion.getButton_Edit().click();//CLick on 'Edit' button
                Thread.sleep(2000);
                questionEditPage.getTextBox_QuestionEditField().clear();
                questionEditPage.getTextBox_QuestionEditField().sendKeys("New " + quesText);
                questionEditPage.getButton_Save().click();// Click on 'Save' Button
                String actual = questionEditPage.getTextBox_QuestionEditField().getText();
                String expected =  "New "+quesText;
                Assert.assertEquals(actual,expected,"Instructor is not able to edit the question");
            }

            if(index ==97) {
                String quesText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
                pf_assQuestion.getButton_Edit().click();//CLick on 'Edit' button
                Thread.sleep(2000);
                questionEditPage.getTextBox_QuestionEditField().clear();
                questionEditPage.getTextBox_QuestionEditField().sendKeys("New " + quesText);
                questionEditPage.getButton_Save().click();// Click on 'Save' Button
                String actual = questionEditPage.getTextBox_QuestionEditField().getText();
                String expected =  "New "+quesText;
                Assert.assertEquals(actual,expected,"Instructor is not able to edit the question");
            }

            if(index ==104) {
                String quesText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
                pf_assQuestion.getButton_Edit().click();//Click on 'Edit' button
                Thread.sleep(2000);
                questionEditPage.getTextBox_QuestionEditField().clear();
                questionEditPage.getTextBox_QuestionEditField().sendKeys("New " + quesText);
                questionEditPage.getButton_Save().click();// Click on 'Save' Button
                String actual = questionEditPage.getTextBox_QuestionEditField().getText();
                String expected =  "New "+quesText;
                Assert.assertEquals(actual,expected,"Instructor is not able to edit the question");
            }
            if(index ==111) {
                String quesText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
                pf_assQuestion.getButton_Edit().click();//Click on 'Edit' button
                Thread.sleep(2000);
                questionEditPage.getTextBox_QuestionEditField().clear();
                questionEditPage.getTextBox_QuestionEditField().sendKeys("New " + quesText);
                questionEditPage.getButton_Save().click();// Click on 'Save' Button
                String actual = questionEditPage.getTextBox_QuestionEditField().getText();
                String expected =  "New "+quesText;
                Assert.assertEquals(actual,expected,"Instructor is not able to edit the question");
            }
            if(index ==118) {
                String quesText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
                pf_assQuestion.getButton_Edit().click();//Click on 'Edit' button
                Thread.sleep(2000);
                questionEditPage.getTextBox_QuestionEditField().clear();
                questionEditPage.getTextBox_QuestionEditField().sendKeys("New " + quesText);
                questionEditPage.getButton_Save().click();// Click on 'Save' Button
                String actual = questionEditPage.getTextBox_QuestionEditField().getText();
                String expected =  "New "+quesText;
                Assert.assertEquals(actual,expected,"Instructor is not able to edit the question");
            }

            if(index ==125) {
                String quesText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));
                pf_assQuestion.getButton_Edit().click();//Click on 'Edit' button
                Thread.sleep(2000);



                questionEditPage.getTextBox_QuestionEditField().clear();
                questionEditPage.getTextBox_QuestionEditField().sendKeys("New " + quesText);
                questionEditPage.getButton_Save().click();// Click on 'Save' Button
                String actual = questionEditPage.getTextBox_QuestionEditField().getText();
                String expected =  "New "+quesText;
                Assert.assertEquals(actual,expected,"Instructor is not able to edit the question");
            }


            new Navigator().logout();//logout

        }catch(Exception e){
            Assert.fail("Exception in method 'completeQuestionEditProcess_newAssessment' in the class 'AllowEditingOfQuestionsForAssignmentsInDraftStatus'",e);
        }
    }




    public void completeQuestionEditProcess_useExisting(int index){

        try{
            PreviewPage previewPage=PageFactory.initElements(driver, PreviewPage.class);
            AssessmentQuestionPage  assessmentQuestionPage=PageFactory.initElements(driver,AssessmentQuestionPage.class);

            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);
            Thread.sleep(2000);
            pf_assignments.getButton_review().click();//Click on 'Review' Button
            pf_assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            pf_assignments.getButton_ViewDraftStatus().click();//Click on 'View draft status' button*/
            pf_assignments.getAssessmentName().click();//Click on Assessment name*/
            String questionID = pf_assignments.getLabelValue_QuestionID().getText();
            String questionText =  pf_assignments.getCreateAssignmentQuestionName().getText();
            String tloText = questionsListPage.getLabelValue_StandardTlo().getText();

            Assert.assertEquals(tloText,"STANDARD : 1.OA, 1.OA.A.1","TLO is not displayed properly");
            pf_assignments.getRightArrow().click();//Click on Question's Right arrow*/
            String questionTextTokens[] = questionText.split(":");


            //Expected - 1 : The particular question should be opened

            if(!(index == 69)){
                if(index ==236){
                    int indexOfEpic2 = pf_assQuestion.getLabel_questionName().getText().indexOf("Epic2");

                    //Expected - 1 : The particular question should be opened
                    if(!questionText.contains(pf_assQuestion.getLabel_questionName().getText().substring(0,indexOfEpic2+4))){
                        Assert.fail("The particular question is not opened.");
                    }
                }else {
                    int indexOfEpic2 = pf_assQuestion.getLabel_questionName().getText().indexOf("Epic2");

                    //Expected - 1 : The particular question should be opened
                    if(!questionText.contains(pf_assQuestion.getLabel_questionName().getText().substring(0,indexOfEpic2+4))){
                        Assert.fail("The particular question is not opened.");
                    }
                }
                }else{
                int indexOfEpic2 = pf_assQuestion.getLabel_questionName().getText().indexOf("Epic2");

                //Expected - 1 : The particular question should be opened
                if(!questionText.contains(pf_assQuestion.getLabel_questionName().getText().substring(0,indexOfEpic2+4))){
                    Assert.fail("The particular question is not opened.");
                }
            }




            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(pf_assQuestion.getLabelValue_QuestionID().getText().trim(),questionID.trim(),"ID of the question is not displayed");



            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals("Owner: "+pf_assQuestion.getLabelValue_Owner().getText(),"Owner: You","Owner of the assignment is not displayed");




            //Expected - 4 : Edit and delete option must be displayed
            Assert.assertEquals(pf_assQuestion.getButton_Edit().getText(),"Edit","Edit Option is not displayed");
            Assert.assertEquals(pf_assQuestion.getButton_Delete().getText(), "Delete", "Delete Option is not displayed");


            //Expected - 5 : "  Preview button must be displayed"
            Assert.assertEquals(pf_assQuestion.getButton_Preview().getText(),"Preview","Preview Option is not displayed");

            /*Shuffle Answer Removed
            //Expected - 6 : ""Shuffle answer"" check box must be displayed as set by the instructor"
            if(index == 20 || index == 27 ||index == 34 ||index == 83) {
                boolean isSelected = pf_assQuestion.getCheckBox_shuffleAnswerChoices().isSelected();
                Assert.assertNotEquals(isSelected, true, "Shuffle answer check is displayed as set by the instructor");
                boolean isDisplayed = pf_assQuestion.getCheckBox_shuffleAnswerChoices().isDisplayed();
                Assert.assertEquals(isDisplayed, true, "Shuffle answer check is not displayed as set by the instructor");
            }
            */


            //Expected - "7. TLO and ELO should be displayed properly
            Assert.assertEquals(pf_assQuestion.getLabel_tlo().getText(),"1.OA - Operations & Algebraic Thinking","TLO is not displayed properly");
            Assert.assertEquals(pf_assQuestion.getLabel_elo().getText(),"1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to","ELO is not displayed properly");




           /*Row No - 206 : "7. Edit the question
            8. Click on ""Save"" button
            9. Click on ""Preview"" button"*/
            //Expected - A pop up should open that shows a preview of the question as a student would view


            if(index ==200) {
                pf_assQuestion.getButton_Edit().click();//CLick on 'Edit' button
                Thread.sleep(2000);
                questionEditPage.getTextBox_AnswerEditField().click();//Click on Answer Choice 1
                questionEditPage.getTextBox_Answer1EditField().sendKeys("Answer4");//Edit Answer Choice 1 by Answer4
                questionEditPage.getIcon_Answer1SelectSymbol().click();//Click on select symbol
                questionEditPage.getButton_AcceptAnswer().click();// Click on button 'Accept Answer'
                questionEditPage.getButton_Save().click();//Click on Save Button
                String actual = questionEditPage.getTextBox_Answer1PostEditField().getText();
                String expected = "Answer4Answer1";
                Assert.assertEquals(actual,expected,"Instructor is not able to edit the question");
            }

               if(index ==212) {
                pf_assQuestion.getButton_Edit().click();//CLick on 'Edit' button
                Thread.sleep(2000);
                questionEditPage.getTextBox_CorrectAnswerEditField().clear();
                questionEditPage.getTextBox_CorrectAnswerEditField().sendKeys("700");
                questionEditPage.getButton_AcceptAnswer_NumericEntry().click();// Click on button 'Accept Answer'
                questionEditPage.getButton_Save().click();//Click on Save Button
                String actual = questionEditPage.getTextBox_QuestionPostEditField().getAttribute("value");
                String expected =  "700";
                Assert.assertEquals(actual,expected,"Instructor is not able to edit the question");
            }

            if(index ==224) {
                pf_assQuestion.getButton_Edit().click();//CLick on 'Edit' button
                Thread.sleep(2000);
                questionEditPage.getTextField_CorrectAnswer_AdvancedNumeric().clear();
                questionEditPage.getTextField_CorrectAnswer_AdvancedNumeric().sendKeys("700");
                questionEditPage.getButton_AcceptAnswer_AdvancedNumeric().click();// Click on button 'Accept Answer'
                questionEditPage.getButton_Save().click();//Click on Save Button
                String actual = questionEditPage.getTextBox_QuestionPostEditField().getAttribute("value");
                String expected =  "700";
                Assert.assertEquals(actual,expected,"Instructor is not able to edit the question");
            }


            if(index ==236) {
                pf_assQuestion.getButton_Edit().click();//CLick on 'Edit' button
                Thread.sleep(2000);
                questionEditPage.getTextField_CorrectAnswer_expressionEvaluator().click();
                questionEditPage.getTextArea_CorrectAnswerEditWindow_expressionEvaluator().clear();
                questionEditPage.getButton_SquareRoot_expressionEvaluator().click();
                questionEditPage.getTextArea_CorrectAnswerEditWindow_expressionEvaluator().sendKeys("10");
                questionEditPage.getButton_Save_expressionEvaluator().click();
                questionEditPage.getButton_AcceptAnswer_expressionEvaluator().click();// Click on button 'Accept Answer'
                questionEditPage.getButton_Save().click();//Click on Save Button
                Thread.sleep(5000);
                /*String actual = questionEditPage.getTextBox_formulaTypeAnswerPostEditField().getText();
                String expected =  "510";
                Assert.assertEquals(actual,expected,"Instructor is not able to edit the question");*/

                if(!(driver.findElements(By.xpath("//div[@id = 'answer_math_edit']//span[@class = 'mn']")).get(0).getText().equals("5") &&driver.findElements(By.xpath("//div[@id = 'answer_math_edit']//span[@class = 'mn']")).get(1).getText().equals("10"))){
                    Assert.fail("Instructor is not able to edit the question");
                }
            }




            assessmentQuestionPage.getButton_Preview().click();//click on the Preview button
            Thread.sleep(10000);
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);//switch to the Preview Window
            }

            //Expected:A pop up should open that shows a preview of the question as a student would view
            if(!previewPage.getPreviewPageTitle().getText().equals("PREVIEW"))
                Assert.fail("A pop up is not Displayed");



            //Expected10. Question should be displayed
            if(index==212) {
                String expected = "Numeric Entry With Units Question_Text_IT1_Epic2_212";
                if (!previewPage.getQuestionText().getText().contains(expected))
                    Assert.fail("Question is not displayed in the Preview Page");
            }else if (index == 200){
                    String expected = "Text Drop Down Question_Text_IT1_Epic2_200";
                    if (!previewPage.getQuestionText().getText().contains(expected))
                        Assert.fail("Question is not displayed in the Preview Page");
            }else if (index == 224){
                String expected = "Advanced Numeric Question_Text_IT1_Epic2_224";
                if (!previewPage.getQuestionText().getText().contains(expected))
                    Assert.fail("Question is not displayed in the Preview Page");
            }else if (index == 236){
                String expected = "Expression Evaluator Question_Text_IT1_Epic2_236";
                if (!previewPage.getQuestionText().getText().contains(expected))
                    Assert.fail("Question is not displayed in the Preview Page");
            }
            //Expected 12. A submit button should be displayed
            Assert.assertEquals(previewPage.getSubmitButton().getText(),"Submit","Submit button is not displayed");


            //11. Click on ""submit"" button"
            if(index == 200){
                //previewPage.getTextField_QuestionInputNumericEntry().sendKeys("200");
                WebElement ele = driver.findElement(By.xpath("//div[@id = 'question-raw-content-preview']//select"));
                new Select(ele).selectByVisibleText("Answer2");
                previewPage.getSubmitButton().click();//click on the submit button
                try {
                    previewPage.getRedBorderColor_NumericEntry();
                }catch(Exception e){

                }

            }else if(index == 212){
            previewPage.getTextField_QuestionInputNumericEntry().sendKeys("200");
            previewPage.getSubmitButton().click();//click on the submit button
             try {
                 previewPage.getRedBorderColor_NumericEntry();
             }catch(Exception e){

             }

            }else if(index == 224){

                previewPage.getTextField_AdvancedNumeric().sendKeys("Advanced Numeric");
                previewPage.getSubmitButton().click();//click on the submit button
                Thread.sleep(15000);
                try {
                    previewPage.getRedBorderColor_NumericEntry();
                }catch(Exception e){

                }

            }else if(index == 236){

                try{
                    previewPage.getAnsweringSpace_ExpressionEvaluatorPreview().click();
                    Thread.sleep(3000);

                }catch(Exception e){

                }
                driver.findElement(By.id("answer_math_edit")).click();
                previewPage.getButton_SquareRoot_expressionEvaluator().click();
                previewPage.getTextArea_CorrectAnswerEditWindow_expressionEvaluator().sendKeys("10");
                previewPage.getButton_Save_expressionEvaluator().click();
                previewPage.getSubmitButton().click();//click on the submit button
                Thread.sleep(15000);
                try {
                    previewPage.getRedBorderColor_NumericEntry();
                }catch(Exception e){

                }
            }
            /*System.out.println("previewPage.getHintContent().getText().trim() : " + previewPage.getHintContent().getText().trim());
            System.out.println("previewPage.getHintContent().getAttribute(\"textContent\") : "+previewPage.getHintContent().getAttribute("textContent"));
            System.out.println("previewPage.getHintContent().get  Attribute(\"value\") : "+previewPage.getHintContent().getAttribute("value"));
            System.out.println("previewPage.getHintContent().getAttribute(\"Text\")"+previewPage.getHintContent().getAttribute("Text"));
            System.out.println("previewPage.getHintContent().getAttribute(\"innerHTML\") : " + previewPage.getHintContent().getAttribute("innerHTML"));*/
            Thread.sleep(5000);
            Assert.assertEquals(previewPage.getHintLabel().getText().trim(), "Hint", "Hint Field is not visible after submit");
            Assert.assertEquals(previewPage.getHintContent().getAttribute("innerHTML").trim(),"True False Hint Text","Hint Content Field is not visible after submit");
            Assert.assertEquals(previewPage.getSolutionLabel().getText().trim(), "Solution", "Solution Field is not visible after submit");
            Assert.assertEquals(previewPage.getSolutionLabelContent().getAttribute("innerHTML").trim(),"True False Solution Text","Solution Content Field is not visible after submit");
            Assert.assertEquals(previewPage.getPoints().getAttribute("value"),"1", "Points is not visible in the preview page after submit");
        }catch(Exception e){
            Assert.fail("Exception in method 'completeQuestionEditProcess_useExisting' in the class 'AllowEditingOfQuestionsForAssignmentsInDraftStatus'",e);
        }
    }

    public void completeUseExistingAssessmentProcess(int index){

        try{
            Assignments pf_assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            AssessmentQuestionPage pf_assQuestion = PageFactory.initElements(driver, AssessmentQuestionPage.class);



            String questionID = pf_assignments.getLabelValue_QuestionID().getText();
            String questionText =  pf_assignments.getCreateAssignmentQuestionName().getText();
            String tloText = questionsListPage.getLabelValue_StandardTlo().getText();
            Assert.assertEquals(tloText,"STANDARD : 1.OA, 1.OA.A.1","TLO is not displayed properly");
            pf_assignments.getRightArrow().click();//Click on Question's Right arrow*/
            String [] questionTextTokens = questionText.split(":");

//            //Expected - 1 : The particular question should be opened
//            Assert.assertEquals(pf_assQuestion.getLabel_questionName().getText().trim(),questionTextTokens[1].trim(),"The particular question is not opened");


            if(!(index == 69)){
                Assert.assertEquals(pf_assQuestion.getLabel_questionName().getText().trim(),questionTextTokens[1].trim(),"The particular question is not opened");
            }else{
                new ExplicitWait().explicitWaitByClass("control-label",3);
                System.out.println("pf_assQuestion.getLabel_questionName().getText() : "+ pf_assQuestion.getLabel_questionName().getText());
                System.out.println("questionTextTokens[1].trim() : " + questionTextTokens[1].trim());
                String []  questionTextTokensSet = questionTextTokens[1].trim().split("\n");
                System.out.println("questionTextTokensSet : " + questionTextTokensSet[0]);
                if(!(pf_assQuestion.getLabel_questionName().getText().trim().contains(questionTextTokensSet[0].trim()))){
                    Assert.fail("The particular question is not opened");
                }
               /* if(!(pf_assQuestion.getLabel_questionName().getText().trim().contains(questionTextTokens[1].trim()))){
                    Assert.fail("The particular question is not opened");
                }*/
            }




            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(pf_assQuestion.getLabelValue_QuestionID().getText().trim(),questionID.trim(),"ID of the question is not displayed");


            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals("Owner: "+pf_assQuestion.getLabelValue_Owner().getText(),"Owner: You","Owner of the assignment is not displayed");

            //Expected - 4 : "  Preview button must be displayed"
            Assert.assertEquals(pf_assQuestion.getButton_Preview().getText(),"Preview","Preview Option is not displayed");





            //Expected - 4.1 : Edit and delete option must not be displayed
            //Assert.assertEquals(pf_assQuestion.getButton_Edit().getText(),"Edit","Edit Option is not displayed");
            //Assert.assertEquals(pf_assQuestion.getButton_Delete().getText(), "Delete", "Delete Option is not displayed");

            boolean editButtonFound  = false;

            try{
                driver.findElement(By.className("as-question-preview-edit-button"));
                //pf_assQuestion.getButton_Edit();
                editButtonFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            //Expected - 10. The question must be deleted
            Assert.assertEquals(editButtonFound, true, "Edit option must not be displayed");


            boolean deleteButtonFound  = false;

            try{
                driver.findElement(By.className("as-question-preview-delete-button"));
               // pf_assQuestion.getButton_Delete();
                deleteButtonFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            //Expected - 10. The question must be deleted
            Assert.assertEquals(deleteButtonFound, false, "Delete option must not be displayed");





              /*Shuffle Answer Removed
            if(index == 20 || index == 27 ||index == 34 ||index == 83) {
                //Expected - 5 : ""Shuffle answer"" check box must be displayed as set by the instructor"
                boolean isSelected = pf_assQuestion.getCheckBox_shuffleAnswerChoices().isSelected();
                Assert.assertNotEquals(isSelected, true, "Shuffle answer check is displayed as set by the instructor");
                System.out.println("isSelected : " + isSelected);
                boolean isDisplayed = pf_assQuestion.getCheckBox_shuffleAnswerChoices().isDisplayed();
                Assert.assertEquals(isDisplayed, true, "Shuffle answer check is not displayed as set by the instructor");
                System.out.println("isSelected : " + isSelected);
            }
            */

            //Expected - "6. TLO and ELO should be displayed properly
            Assert.assertEquals(pf_assQuestion.getLabel_tlo().getText(),"1.OA - Operations & Algebraic Thinking","TLO is not displayed properly");
            Assert.assertEquals(pf_assQuestion.getLabel_elo().getText(),"1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to","ELO is not displayed properly");



        }catch(Exception e){
            Assert.fail("Exception in method 'completeUseExistingAssessmentProcess' in the class 'AllowEditingOfQuestionsForAssignmentsInDraftStatus'",e);
        }
    }

}
