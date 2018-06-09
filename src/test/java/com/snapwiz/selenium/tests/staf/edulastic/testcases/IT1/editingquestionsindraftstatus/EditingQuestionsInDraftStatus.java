package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.editingquestionsindraftstatus;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentQuestionPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.QuestionEditPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.StringTokenizer;

/**
 * Created by pragya on 17-12-2014.
 */
public class EditingQuestionsInDraftStatus extends Driver {
    String owner;
    String questionID;
    String questionText;


    @Test(priority = 1,enabled = true)
    //TC row no. 15. Instructors should be allowed to edit questions as long as the assignment is in draft status and if the instructor is the owner of the assignment
    public void editingQuestionsInDraftStatusInstructorBeingTheOwner() {
        try {

            Assignments pf_assignments =PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);
            String appendChar = "a";

            int dataIndex = 29;
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            new Assignment().create(dataIndex, "truefalse");//Create a new assignment
            pf_assignments.getButton_review().click();//Click on 'Review' Button
            pf_assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(dataIndex);

            owner = pf_assignments.getLabelValue_Ownwer().getText();
            questionID = pf_assignments.getLabelValue_QuestionID().getText();
            questionText = pf_assignments.getCreateAssignmentQuestionName().getText();

            pf_assignments.getRightArrow().click();//Click on Question's Right arrow

            //Expected - 1 : The particular question should be opened
            if(!questionText.contains(assessmentQuestionPage.getLabel_questionName().getText())){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_Owner().getText(),owner,"Owner of the assignment is not displayed");

            //Expected - 4 : Edit and delete option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");
            Assert.assertEquals(assessmentQuestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");

            //Expected - 5. Preview button must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Preview().isDisplayed(), true, "Preview button is not displayed");

            //Expected - 6. "Shuffle answer" check box must be displayed as set by the instructor
           /* Shuffle Question has been removed
            boolean isSelected = assessmentQuestionPage.getCheckBox_shuffleAnswerChoices().isSelected();
            Assert.assertEquals(isSelected,false,"Check box is selected even though it was not selected by instructor");*/

            // Expected - 7. "TLO and ELO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");
            Assert.assertEquals(assessmentQuestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'editingQuestionsInDraftStatusInstructorBeingTheOwner' in class 'EditingQuestionsInDraftStatus'", e);
        }
    }

    @Test(priority = 2,enabled = true)
    //TC row no. 15. Instructors should be allowed to edit questions as long as the assignment is in draft status and if the instructor is the owner of the assignment for Use Existing
    public void editingQuestionsInDraftStatusInstructorBeingTheOwnerForUseExisting(){

        try {

            Assignments pf_assignments =PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);

            String appendChar = "a";
            int dataIndex = 85;
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Assignment().create(dataIndex, "truefalse");//Create a new assignment
            new Assignment().assignToStudent(dataIndex,appendChar);//assign the assignment
            new Assignment().openUseExistingAssignment(1,"Me");//select the assignment created by instructor
            driver.findElement(By.className("as-assignment-customize")).click();//Click on customize

            owner = pf_assignments.getLabelValue_Ownwer().getText();
            questionID = pf_assignments.getLabelValue_QuestionID().getText();
            questionText = pf_assignments.getCreateAssignmentQuestionName().getText();

            pf_assignments.getRightArrow().click();//Click on Question's Right arrow

            //Expected - 1 : The particular question should be opened
            if(!questionText.contains(assessmentQuestionPage.getLabel_questionName().getText())){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_Owner().getText(),owner,"Owner of the assignment is not displayed");

            boolean editFound  = false;
            try{
                driver.findElement(By.className("as-question-preview-edit-button"));
                editFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            //Expected - 4 : Edit option must be displayed as per E7 feature
            Assert.assertEquals(editFound, true, "Edit option is not displayed");

            boolean deleteFound = false;
            try{
                driver.findElement(By.className("as-question-preview-delete-button"));
                deleteFound = true;
            }
            catch (Exception e){
                //empty catch block
            }

            //Expected - 4 : delete option must not be displayed
            Assert.assertEquals(deleteFound, false, "Delete option is displayed");

            //Expected - 5. Preview button must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Preview().isDisplayed(), true, "Preview button is not displayed");

           /* //Expected - 6. "Shuffle answer" check box must be displayed as set by the instructor
            boolean isSelected = assessmentQuestionPage.getCheckBox_shuffleAnswerChoices().isSelected();
            Assert.assertEquals(isSelected,false,"Check box is selected even though it was not selected by instructor");*/

            // Expected - 7. "TLO and ELO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");
            Assert.assertEquals(assessmentQuestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'editingQuestionsInDraftStatusInstructorBeingTheOwnerForUseExisting' in class 'EditingQuestionsInDraftStatus'", e);
        }
    }

    @Test(priority = 3,enabled = true)
    //TC row no. 132. An instructor should be able to edit 'Cloze (Formula)' type question upon clicking edit option (owns the assignment)
    public void anAssignmentInDraftStatusWithAClozeTypeQuestionInstructorBeingTheOwner(){

        try{
            Assignments pf_assignments =PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);

            String appendChar = "a";
            int dataIndex = 159;
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            new Assignment().create(dataIndex, "clozeformula");//Create a new assignment
            pf_assignments.getButton_review().click();//Click on 'Review' Button
            pf_assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(dataIndex);

            owner = pf_assignments.getLabelValue_Ownwer().getText();
            questionID = pf_assignments.getLabelValue_QuestionID().getText();
            questionText = pf_assignments.getCreateAssignmentQuestionName().getText();

            pf_assignments.getRightArrow().click();//Click on Question's Right arrow

            //Expected - 1 : The particular question should be opened
            if(!questionText.contains(assessmentQuestionPage.getLabel_questionName().getText())){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_Owner().getText(),owner,"Owner of the assignment is not displayed");

            //Expected - 4 : Edit and delete option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");
            Assert.assertEquals(assessmentQuestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");

            //Expected - 5 : Preview button must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Preview().isDisplayed(), true, "Preview button is not displayed");

            // Expected - 7. "TLO and ELO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");
            Assert.assertEquals(assessmentQuestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");

            assessmentQuestionPage.getButton_Edit().click();//Click on edit button

            questionEditPage.getTextBox_QuestionEditField().clear();
            questionEditPage.getTextBox_QuestionEditField().sendKeys("EditQuestion");
            driver.findElement(By.xpath("/html/body")).click();
            String text = questionEditPage.getTextBox_QuestionEditField().getText();

            //Expected - 8 : Instructor must be taken to the question edit mode and he should be able to edit the question now
            Assert.assertEquals(text,"EditQuestion","Not able to edit the question");

            questionEditPage.getButton_Save();//Click on save button

            //Expected - 9 :Changes should be saved and reflected on the question
            String questionText = questionEditPage.getTextBox_QuestionEditField().getText();
            Assert.assertEquals(questionText,"EditQuestion","Changes are not saved and reflected on the question");
        }catch(Exception e){
            Assert.fail("Exception in testcase 'anAssignmentInDraftStatusWithAClozeTypeQuestionInstructorBeingTheOwner' in class 'EditingQuestionsInDraftStatus'",e);
        }
    }

    @Test(priority = 4,enabled = true)
    //TC row no. 132. An instructor should be able to edit 'Cloze (Formula)' type question upon clicking edit option (owns the assignment) for Use Existing
    public void anAssignmentInDraftStatusWithAClozeTypeQuestionInstructorBeingTheOwnerForUSeExisting() {

        try {
            Assignments pf_assignments =PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);

            String appendChar = "a";
            int dataIndex = 227;
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//Create class
            new Assignment().create(dataIndex, "clozeformula");//Create a new assignment
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            new Assignment().openUseExistingAssignment(1,"Me");//select the assignment created by instructor
            driver.findElement(By.className("as-assignment-customize")).click();//Click on customize

            owner = pf_assignments.getLabelValue_Ownwer().getText();
            questionID = pf_assignments.getLabelValue_QuestionID().getText();
            questionText = pf_assignments.getCreateAssignmentQuestionName().getText();

            pf_assignments.getRightArrow().click();//Click on Question's Right arrow

            //Expected - 1 : The particular question should be opened
            if(!questionText.contains(assessmentQuestionPage.getLabel_questionName().getText())){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_Owner().getText(),owner,"Owner of the assignment is not displayed");

            boolean editFound  = false;
            try{
                driver.findElement(By.className("as-question-preview-edit-button"));
                editFound = true;
            }
            catch (Exception e){
                //empty catch block
            }

            //Expected - 4 : Edit option must be displayed as per E7 feature
            Assert.assertEquals(editFound, true, "Edit option is not displayed");

            boolean deleteFound = false;
            try{
                //assessmentQuestionPage.getButton_Delete();
                driver.findElement(By.className("as-question-preview-delete-button"));
                deleteFound = true;
            }
            catch (Exception e){
                //empty catch block
            }

            //Expected - 4 : delete option must not be displayed
            Assert.assertEquals(deleteFound, false, "Delete option is displayed");

            //Expected - 5. Preview button must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Preview().isDisplayed(), true, "Preview button is not displayed");

            // Expected - 7. "TLO and ELO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");
            Assert.assertEquals(assessmentQuestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");


        }catch (Exception e) {
            Assert.fail("Exception in testcase 'anAssignmentInDraftStatusWithAClozeTypeQuestionInstructorBeingTheOwnerForUSeExisting' in class 'EditingQuestionsInDraftStatus'",e);
        }
    }

    @Test(priority=5,enabled = true)
    //TC row no. 145. Instructor should be allowed to preview questions during editing
    public void instructorToBeAllowedToPreviewQuestionDuringEditing(){

        try{
            Assignments pf_assignments =PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);

            String appendChar = "a";
            int dataIndex = 300;
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            new Assignment().create(dataIndex, "truefalse");//Create a new assignment
            pf_assignments.getButton_review().click();//Click on 'Review' Button
            pf_assignments.getButton_saveForLater().click();//Click on 'Save for later' button

            new Assignment().selectAssessment(dataIndex);

            owner = pf_assignments.getLabelValue_Ownwer().getText();
            questionID = pf_assignments.getLabelValue_QuestionID().getText();
            questionText = pf_assignments.getCreateAssignmentQuestionName().getText();

            pf_assignments.getRightArrow().click();//Click on Question's Right arrow

            //Expected - 1 : The particular question should be opened
            if(!questionText.contains(assessmentQuestionPage.getLabel_questionName().getText())){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_Owner().getText(),owner,"Owner of the assignment is not displayed");

            //Expected - 4 : Edit and delete option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");
            Assert.assertEquals(assessmentQuestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");

            //Expected - 5 : Preview button must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Preview().isDisplayed(), true, "Preview button is not displayed");

          /*  //Expected - 6. "Shuffle answer" check box must be displayed as set by the instructor
            boolean isSelected = assessmentQuestionPage.getCheckBox_shuffleAnswerChoices().isSelected();
            Assert.assertEquals(isSelected,false,"Check box is selected even though it was not selected by instructor");*/

            // Expected - 7. "TLO and ELO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");
            Assert.assertEquals(assessmentQuestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");

            assessmentQuestionPage.getButton_Edit().click();//Click on edit button

            questionEditPage.getTextBox_QuestionEditField().clear();
            questionEditPage.getTextBox_QuestionEditField().sendKeys("EditQuestion");
            driver.findElement(By.xpath("/html/body")).click();
            String text = questionEditPage.getTextBox_QuestionEditField().getText();

            //Expected - 8 : Instructor must be taken to the question edit mode and he should be able to edit the question now
            Assert.assertEquals(text,"EditQuestion","Not able to edit the question");


            boolean previewFound = false;
            try{
                driver.findElement(By.className("as-question-teacher-preview-button"));
                previewFound = true;
            }
            catch (Exception e){
                //empty catch block
            }

            //Expected - 9 : Preview option must not be displayed
            Assert.assertEquals(previewFound, false, "Preview option is displayed");

        }catch(Exception e){
            Assert.fail("Exception in testcase 'instructorToBeAllowedToPreviewQuestionDuringEditing' in class 'EditingQuestionsInDraftStatus'",e);
        }

    }
    @Test(priority = 6,enabled = true)
    //TC row no. 145. Instructor should be allowed to preview questions during editing for Use Existing
    public void instructorToBeAllowedToPreviewQuestionDuringEditingForUseExisting(){

        try{
            Assignments pf_assignments =PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);

            String appendChar = "a";
            int dataIndex = 379;
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//Create class
            new Assignment().create(dataIndex, "truefalse");//Create a new assignment
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            new Assignment().openUseExistingAssignment(1,"Me");//select the assignment created by instructor
            driver.findElement(By.className("as-assignment-customize")).click();//Click on customize

            owner = pf_assignments.getLabelValue_Ownwer().getText();
            questionID = pf_assignments.getLabelValue_QuestionID().getText();
            questionText = pf_assignments.getCreateAssignmentQuestionName().getText();

            pf_assignments.getRightArrow().click();//Click on Question's Right arrow

            //Expected - 1 : The particular question should be opened
            if(!questionText.contains(assessmentQuestionPage.getLabel_questionName().getText())){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_Owner().getText(),owner,"Owner of the assignment is not displayed");

            boolean editFound  = false;
            try{
                driver.findElement(By.className("as-question-preview-edit-button"));
                editFound = true;
            }
            catch (Exception e){
                //empty catch block
            }

            //Expected - 4 : Edit option must be displayed as per E7 feature
            Assert.assertEquals(editFound, true, "Edit option is not displayed");

            boolean deleteFound = false;
            try{
                //assessmentQuestionPage.getButton_Delete();
                driver.findElement(By.className("as-question-preview-delete-button"));
                deleteFound = true;
            }
            catch (Exception e){
                //empty catch block
            }

            //Expected - 4 : delete option must not be displayed
            Assert.assertEquals(deleteFound, false, "Delete option is displayed");

            //Expected -5 : Preview option must not be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Preview().isDisplayed(), true, "Preview button is not displayed");

            // Expected - 7. "TLO and ELO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");
            Assert.assertEquals(assessmentQuestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase 'instructorToBeAllowedToPreviewQuestionDuringEditingForUseExisting' in class 'EditingQuestionsInDraftStatus'",e);
        }
    }

    @Test(priority = 7,enabled = true)
    //TC row no. 139. An instructor should be able to delete the question upon clicking delete option (owns the assignment)
    public void anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForTrueAndFalse(){

        try{
            Assignments pf_assignments =PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);

            String appendChar = "a";
            int dataIndex = 454;
            new SignUp().teacher(appendChar,1);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            new Assignment().create(dataIndex, "truefalse");//Create a new assignment
            pf_assignments.getButton_review().click();//Click on 'Review' Button
            pf_assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(dataIndex);

            owner = pf_assignments.getLabelValue_Ownwer().getText();
            questionID = pf_assignments.getLabelValue_QuestionID().getText();
            questionText = pf_assignments.getCreateAssignmentQuestionName().getText();

            pf_assignments.getRightArrow().click();//Click on Question's Right arrow

            //Expected - 1 : The particular question should be opened
            if(!questionText.contains(assessmentQuestionPage.getLabel_questionName().getText())){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_Owner().getText(),owner,"Owner of the assignment is not displayed");

            //Expected - 4 : Edit option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");

            //Expected - 5 : Delete option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");

            //Expected - 6. Preview button must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Preview().isDisplayed(), true, "Preview button is not displayed");

          /*  //Expected - 7. "Shuffle answer" check box must be displayed as set by the instructor
            boolean isSelected = assessmentQuestionPage.getCheckBox_shuffleAnswerChoices().isSelected();
            Assert.assertEquals(isSelected,false,"Check box is selected even though it was not selected by instructor");*/

            //Expected - 8. "TLO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");

            //Expected - 9. "ELO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");

            assessmentQuestionPage.getButton_Delete().click();//Click on Delete button
            assessmentQuestionPage.getYesButton_Delete().click();

            boolean questionFound  = false;

            try{
                driver.findElement(By.className("question-edit"));
                questionFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            //Expected - 10. The question must be deleted
            Assert.assertEquals(questionFound, false, "Question is not deleted");
        }catch(Exception e){
            Assert.fail("Exception in testcase 'anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForTrueFalse' in class 'EditingQuestionsInDraftStatus'",e);
        }
    }

    @Test(priority = 8,enabled = true)
    //TC row no. 139. An instructor should be able to delete the question upon clicking delete option (owns the assignment)
    public void anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForMultipleChoice(){

        try{
            Assignments pf_assignments =PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);

            String appendChar = "a";
            int dataIndex = 528;
            new SignUp().teacher(appendChar,1);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            new Assignment().create(dataIndex, "multiplechoice");//Create a new assignment
            pf_assignments.getButton_review().click();//Click on 'Review' Button
            pf_assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(dataIndex);

            owner = pf_assignments.getLabelValue_Ownwer().getText();
            questionID = pf_assignments.getLabelValue_QuestionID().getText();
            questionText = pf_assignments.getCreateAssignmentQuestionName().getText();

            pf_assignments.getRightArrow().click();//Click on Question's Right arrow

            //Expected - 1 : The particular question should be opened
            if(!questionText.contains(assessmentQuestionPage.getLabel_questionName().getText())){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_Owner().getText(),owner,"Owner of the assignment is not displayed");

            //Expected - 4 : Edit option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");

            //Expected - 5 : Delete option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");

            //Expected - 6. Preview button must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Preview().isDisplayed(), true, "Preview button is not displayed");

            /*//Expected - 7. "Shuffle answer" check box must be displayed as set by the instructor
            boolean isSelected = assessmentQuestionPage.getCheckBox_shuffleAnswerChoices().isSelected();
            Assert.assertEquals(isSelected,false,"Check box is selected even though it was not selected by instructor");*/

            //Expected - 8. "TLO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");

            //Expected - 9. "ELO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");

            assessmentQuestionPage.getButton_Delete().click();//Click on Delete button
            assessmentQuestionPage.getYesButton_Delete().click();

            boolean questionFound  = false;

            try{
                driver.findElement(By.className("question-edit"));
                questionFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            //Expected - 10. The question must be deleted
            Assert.assertEquals(questionFound, false, "Question is not deleted");

        }catch(Exception e){
            Assert.fail("Exception in testcase 'anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForMultipleChoice' in class 'EditingQuestionsInDraftStatus'",e);
        }
    }


    @Test(priority = 9,enabled = true)
    //TC row no. 139. An instructor should be able to delete the question upon clicking delete option (owns the assignment)
    public void anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForMultipleSelection(){

        try{
            Assignments pf_assignments =PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);

            String appendChar = "a";
            int dataIndex = 604;
            new SignUp().teacher(appendChar,1);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            new Assignment().create(dataIndex, "multipleselection");//Create a new assignment
            pf_assignments.getButton_review().click();//Click on 'Review' Button
            pf_assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(dataIndex);

            owner = pf_assignments.getLabelValue_Ownwer().getText();
            questionID = pf_assignments.getLabelValue_QuestionID().getText();
            questionText = pf_assignments.getCreateAssignmentQuestionName().getText();

            pf_assignments.getRightArrow().click();//Click on Question's Right arrow

            //Expected - 1 : The particular question should be opened
            if(!questionText.contains(assessmentQuestionPage.getLabel_questionName().getText())){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_Owner().getText(),owner,"Owner of the assignment is not displayed");

            //Expected - 4 : Edit option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");

            //Expected - 5 : Delete option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");

            //Expected - 6. Preview button must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Preview().isDisplayed(), true, "Preview button is not displayed");

            /*//Expected - 7. "Shuffle answer" check box must be displayed as set by the instructor
            boolean isSelected = assessmentQuestionPage.getCheckBox_shuffleAnswerChoices().isSelected();
            Assert.assertEquals(isSelected,false,"Check box is selected even though it was not selected by instructor");*/

            //Expected - 8. "TLO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");

            //Expected - 9. "ELO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");

            assessmentQuestionPage.getButton_Delete().click();//Click on Delete button
            assessmentQuestionPage.getYesButton_Delete().click();

            boolean questionFound  = false;

            try{
                driver.findElement(By.className("question-edit"));
                questionFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            //Expected - 10. The question must be deleted
            Assert.assertEquals(questionFound, false, "Question is not deleted");

        }catch(Exception e){
            Assert.fail("Exception in testcase 'anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForMultipleSelection' in class 'EditingQuestionsInDraftStatus'",e);
        }
    }

    @Test(priority = 10,enabled = true)
    //TC row no. 139. An instructor should be able to delete the question upon clicking delete option (owns the assignment)
    public void anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForTextEntry(){

        try{
            Assignments pf_assignments =PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);

            String appendChar = "a";
            int dataIndex = 679;
            new SignUp().teacher(appendChar,1);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            new Assignment().create(dataIndex, "textentry");//Create a new assignment
            pf_assignments.getButton_review().click();//Click on 'Review' Button
            pf_assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(dataIndex);

            owner = pf_assignments.getLabelValue_Ownwer().getText();
            questionID = pf_assignments.getLabelValue_QuestionID().getText();
            questionText = pf_assignments.getCreateAssignmentQuestionName().getText();

            pf_assignments.getRightArrow().click();//Click on Question's Right arrow

            //Expected - 1 : The particular question should be opened
            if(!questionText.contains(assessmentQuestionPage.getLabel_questionName().getText())){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_Owner().getText(),owner,"Owner of the assignment is not displayed");

            //Expected - 4 : Edit option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");

            //Expected - 5 : Delete option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");

            //Expected - 6. Preview button must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Preview().isDisplayed(), true, "Preview button is not displayed");

            //Expected - 8. "TLO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");

            //Expected - 9. "ELO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");

            assessmentQuestionPage.getButton_Delete().click();//Click on Delete button
            assessmentQuestionPage.getYesButton_Delete().click();

            boolean questionFound  = false;

            try{
                driver.findElement(By.className("question-edit"));
                questionFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            //Expected - 10. The question must be deleted
            Assert.assertEquals(questionFound, false, "Question is not deleted");

        }catch(Exception e){
            Assert.fail("Exception in testcase 'anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForTextEntry' in class 'EditingQuestionsInDraftStatus'",e);
        }
    }


    @Test(priority = 11,enabled = true)
    //TC row no. 139. An instructor should be able to delete the question upon clicking delete option (owns the assignment)
    public void anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForTextDropDown(){

        try{
            Assignments pf_assignments =PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);

            String appendChar = "a";
            int dataIndex = 751;
            new SignUp().teacher(appendChar,1);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            new Assignment().create(dataIndex, "textdropdown");//Create a new assignment
            pf_assignments.getButton_review().click();//Click on 'Review' Button
            pf_assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(dataIndex);

            owner = pf_assignments.getLabelValue_Ownwer().getText();
            questionID = pf_assignments.getLabelValue_QuestionID().getText();
            questionText = pf_assignments.getCreateAssignmentQuestionName().getText();

            pf_assignments.getRightArrow().click();//Click on Question's Right arrow
            //Expected - 1 : The particular question should be opened
            int indexOFA=assessmentQuestionPage.getLabel_questionName().getText().indexOf("A");

            if(!questionText.contains(assessmentQuestionPage.getLabel_questionName().getText().substring(0, indexOFA - 1))){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_Owner().getText(),owner,"Owner of the assignment is not displayed");

            //Expected - 4 : Edit option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");

            //Expected - 5 : Delete option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");

            //Expected - 6. Preview button must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Preview().isDisplayed(), true, "Preview button is not displayed");

            //Expected - 8. "TLO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");

            //Expected - 9. "ELO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");

            assessmentQuestionPage.getButton_Delete().click();//Click on Delete button
            assessmentQuestionPage.getYesButton_Delete().click();

            boolean questionFound  = false;

            try{
                driver.findElement(By.className("question-edit"));
                questionFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            //Expected - 10. The question must be deleted
            Assert.assertEquals(questionFound, false, "Question is not deleted");


        }catch(Exception e){
            Assert.fail("Exception in testcase 'anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForTextDropDown' in class 'EditingQuestionsInDraftStatus'",e);
        }
    }


    @Test(priority = 12,enabled = true)
    //TC row no. 139. An instructor should be able to delete the question upon clicking delete option (owns the assignment)
    public void anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForNumericEntryWithUnits(){

        try{
            Assignments pf_assignments =PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);

            String appendChar = "a";
            int dataIndex = 824;
            new SignUp().teacher(appendChar,1);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            new Assignment().create(dataIndex, "numericentrywithunits");//Create a new assignment
            Thread.sleep(3000);
            pf_assignments.getButton_review().click();//Click on 'Review' Button
            pf_assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(dataIndex);

            owner = pf_assignments.getLabelValue_Ownwer().getText();
            questionID = pf_assignments.getLabelValue_QuestionID().getText();
            questionText = pf_assignments.getCreateAssignmentQuestionName().getText();

            pf_assignments.getRightArrow().click();//Click on Question's Right arrow
            int indexOf4 = assessmentQuestionPage.getLabel_questionName().getText().indexOf('4');

            //Expected - 1 : The particular question should be opened
            if(!questionText.contains(assessmentQuestionPage.getLabel_questionName().getText().substring(0,indexOf4+1))){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_Owner().getText(),owner,"Owner of the assignment is not displayed");

            //Expected - 4 : Edit option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");

            //Expected - 5 : Delete option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");

            //Expected - 6. Preview button must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Preview().isDisplayed(), true, "Preview button is not displayed");

            //Expected - 8. "TLO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");

            //Expected - 9. "ELO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");

            assessmentQuestionPage.getButton_Delete().click();//Click on Delete button
            assessmentQuestionPage.getYesButton_Delete().click();

            boolean questionFound  = false;

            try{
                driver.findElement(By.className("question-edit"));
                questionFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            //Expected - 10. The question must be deleted
            Assert.assertEquals(questionFound, false, "Question is not deleted");
            new Navigator().logout();//Click on logout



        }catch(Exception e){
            Assert.fail("Exception in testcase 'anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForNumericEntryWithUnits' in class 'EditingQuestionsInDraftStatus'",e);
        }
    }


    @Test(priority = 13,enabled = true)
    //TC row no. 139. An instructor should be able to delete the question upon clicking delete option (owns the assignment)
    public void anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForAdvancedNumeric(){

        try{
            Assignments pf_assignments =PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);

            String appendChar = "a";
            int dataIndex = 899;
            new SignUp().teacher(appendChar,1);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class

            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            new Assignment().create(dataIndex, "advancednumeric");//Create a new assignment
            pf_assignments.getButton_review().click();//Click on 'Review' Button
            pf_assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(dataIndex);

            owner = pf_assignments.getLabelValue_Ownwer().getText();
            questionID = pf_assignments.getLabelValue_QuestionID().getText();
            questionText = pf_assignments.getCreateAssignmentQuestionName().getText();
            pf_assignments.getRightArrow().click();//Click on Question's Right arrow

            //Expected - 1 : The particular question should be opened
            if(!questionText.trim().contains(assessmentQuestionPage.getLabel_questionName().getText().trim())){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_Owner().getText(),owner,"Owner of the assignment is not displayed");

            //Expected - 4 : Edit option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");

            //Expected - 5 : Delete option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");

            //Expected - 6. Preview button must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Preview().isDisplayed(), true, "Preview button is not displayed");

            //Expected - 8. "TLO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");

            //Expected - 9. "ELO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");

            assessmentQuestionPage.getButton_Delete().click();//Click on Delete button
            assessmentQuestionPage.getYesButton_Delete().click();

            boolean questionFound  = false;

            try{
                driver.findElement(By.className("question-edit"));
                questionFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            //Expected - 10. The question must be deleted
            Assert.assertEquals(questionFound, false, "Question is not deleted");


        }catch(Exception e){
            Assert.fail("Exception in testcase 'anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForAdvancedNumeric' in class 'EditingQuestionsInDraftStatus'",e);
        }
    }


    @Test(priority = 14,enabled = true)
    //TC row no. 139. An instructor should be able to delete the question upon clicking delete option (owns the assignment)
    public void anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForExpressionEvaluator(){

        try{
            Assignments pf_assignments =PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);

            String appendChar = "a";
            int dataIndex = 972;
            new SignUp().teacher(appendChar,1);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            new Assignment().create(dataIndex, "expressionevaluator");//Create a new assignment
            pf_assignments.getButton_review().click();//Click on 'Review' Button
            pf_assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(dataIndex);

            owner = pf_assignments.getLabelValue_Ownwer().getText();
            questionID = pf_assignments.getLabelValue_QuestionID().getText();

            questionText = pf_assignments.getCreateAssignmentQuestionName().getText();
            StringTokenizer getQuestionText= new StringTokenizer(questionText,"\n");

            pf_assignments.getRightArrow().click();//Click on Question's Right arrow

            StringTokenizer getQuestionTextVerify = new StringTokenizer(assessmentQuestionPage.getLabel_questionName().getText(),"\n");

            //Expected - 1 : The particular question should be opened
            if(!getQuestionText.nextToken().contains(getQuestionTextVerify.nextToken())){
                Assert.fail("The particular question is not opened.");
            }
            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_Owner().getText(),owner,"Owner of the assignment is not displayed");

            //Expected - 4 : Edit option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");

            //Expected - 5 : Delete option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");

            //Expected - 6. Preview button must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Preview().isDisplayed(), true, "Preview button is not displayed");


            //Expected - 8. "TLO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");

            //Expected - 9. "ELO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");
            assessmentQuestionPage.getButton_Delete().click();//Click on Delete button
            assessmentQuestionPage.getYesButton_Delete().click();

            boolean questionFound  = false;

            try{
                driver.findElement(By.className("question-edit"));
                questionFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            //Expected - 10. The question must be deleted
            Assert.assertEquals(questionFound, false, "Question is not deleted");


        }catch(Exception e){
            Assert.fail("Exception in testcase 'anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForExpressionEvaluator' in class 'EditingQuestionsInDraftStatus'",e);
        }
    }


    @Test(priority = 15,enabled = true)
    //TC row no. 139. An instructor should be able to delete the question upon clicking delete option (owns the assignment)
    public void anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForMatchTheFollowing(){

        try{
            Assignments pf_assignments =PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);

            String appendChar = "a";
            int dataIndex = 1048;
            new SignUp().teacher(appendChar,1);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class

            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            new Assignment().create(dataIndex, "matchthefollowing");//Create a new assignment
            pf_assignments.getButton_review().click();//Click on 'Review' Button
            pf_assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(dataIndex);

            owner = pf_assignments.getLabelValue_Ownwer().getText();
            questionID = pf_assignments.getLabelValue_QuestionID().getText();
            questionText = pf_assignments.getCreateAssignmentQuestionName().getText();

            pf_assignments.getRightArrow().click();//Click on Question's Right arrow

            //Expected - 1 : The particular question should be opened
            if(!questionText.trim().contains(assessmentQuestionPage.getLabel_questionName().getText().trim())){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_Owner().getText(),owner,"Owner of the assignment is not displayed");

            //Expected - 4 : Edit option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");

            //Expected - 5 : Delete option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");

            //Expected - 6. Preview button must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Preview().isDisplayed(), true, "Preview button is not displayed");

            //Expected - 8. "TLO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");

            //Expected - 9. "ELO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");

            assessmentQuestionPage.getButton_Delete().click();//Click on Delete button
            assessmentQuestionPage.getYesButton_Delete().click();

            boolean questionFound  = false;

            try{
                driver.findElement(By.className("question-edit"));
                questionFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            //Expected - 10. The question must be deleted
            Assert.assertEquals(questionFound, false, "Question is not deleted");


        }catch(Exception e){
            Assert.fail("Exception in testcase 'anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForMatchTheFollowing' in class 'EditingQuestionsInDraftStatus'",e);
        }
    }


    @Test(priority = 16,enabled = true)
    //TC row no. 139. An instructor should be able to delete the question upon clicking delete option (owns the assignment)
    public void anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForDragAndDrop(){

        try{
            Assignments pf_assignments =PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);

            String appendChar = "a";
            int dataIndex = 1122;
            new SignUp().teacher(appendChar,1);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            new Assignment().create(dataIndex, "draganddrop");//Create a new assignment
            pf_assignments.getButton_review().click();//Click on 'Review' Button
            pf_assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(dataIndex);

            owner = pf_assignments.getLabelValue_Ownwer().getText();
            questionID = pf_assignments.getLabelValue_QuestionID().getText();
            questionText = pf_assignments.getCreateAssignmentQuestionName().getText();

            pf_assignments.getRightArrow().click();//Click on Question's Right arrow

            //Expected - 1 : The particular question should be opened
            if(!questionText.trim().contains(assessmentQuestionPage.getLabel_questionName().getText().trim())){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_Owner().getText(),owner,"Owner of the assignment is not displayed");

            //Expected - 4 : Edit option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");

            //Expected - 5 : Delete option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");

            //Expected - 6. Preview button must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Preview().isDisplayed(), true, "Preview button is not displayed");

            //Expected - 8. "TLO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");

            //Expected - 9. "ELO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");

            assessmentQuestionPage.getButton_Delete().click();//Click on Delete button
            assessmentQuestionPage.getYesButton_Delete().click();

            boolean questionFound  = false;

            try{
                driver.findElement(By.className("question-edit"));
                questionFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            //Expected - 10. The question must be deleted
            Assert.assertEquals(questionFound, false, "Question is not deleted");


        }catch(Exception e){
            Assert.fail("Exception in testcase 'anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForDragAndDrop' in class 'EditingQuestionsInDraftStatus'",e);
        }
    }



    @Test(priority = 17,enabled = true)
    //TC row no. 139. An instructor should be able to delete the question upon clicking delete option (owns the assignment)
    public void anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForEssay(){

        try{
            Assignments pf_assignments =PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);

            String appendChar = "a";
            int dataIndex = 1196;
            new SignUp().teacher(appendChar,1);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            new Assignment().create(dataIndex, "essay");//Create a new assignment
            pf_assignments.getButton_review().click();//Click on 'Review' Button
            pf_assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(dataIndex);

            owner = pf_assignments.getLabelValue_Ownwer().getText();
            questionID = pf_assignments.getLabelValue_QuestionID().getText();
            questionText = pf_assignments.getCreateAssignmentQuestionName().getText();

            pf_assignments.getRightArrow().click();//Click on Question's Right arrow

            //Expected - 1 : The particular question should be opened
            if(!questionText.trim().contains(assessmentQuestionPage.getLabel_questionName().getText().trim())){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_Owner().getText(),owner,"Owner of the assignment is not displayed");

            //Expected - 4 : Edit option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");

            //Expected - 5 : Delete option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");

            //Expected - 6. Preview button must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Preview().isDisplayed(), true, "Preview button is not displayed");

            // Expected - 8. "TLO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");

            //Expected - 9. "ELO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");

            assessmentQuestionPage.getButton_Delete().click();//Click on Delete button
            assessmentQuestionPage.getYesButton_Delete().click();

            boolean questionFound  = false;

            try{
                driver.findElement(By.className("question-edit"));
                questionFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            //Expected - 10. The question must be deleted
            Assert.assertEquals(questionFound, false, "Question is not deleted");


        }catch(Exception e){
            Assert.fail("Exception in testcase 'anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForEssay' in class 'EditingQuestionsInDraftStatus'",e);
        }
    }


    @Test(priority = 18,enabled = true)
    //TC row no. 139. An instructor should be able to delete the question upon clicking delete option (owns the assignment)
    public void anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForLabelAnImageText(){

        try{
            Assignments pf_assignments =PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);

            String appendChar = "a";
            int dataIndex = 1269;
            new SignUp().teacher(appendChar,1);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            new Assignment().create(dataIndex, "labelanimagetext");//Create a new assignment
            pf_assignments.getButton_review().click();//Click on 'Review' Button
            pf_assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(dataIndex);

            owner = pf_assignments.getLabelValue_Ownwer().getText();
            questionID = pf_assignments.getLabelValue_QuestionID().getText();
            questionText = pf_assignments.getCreateAssignmentQuestionName().getText();

            pf_assignments.getRightArrow().click();//Click on Question's Right arrow

            //Expected - 1 : The particular question should be opened
            if(!questionText.trim().contains(assessmentQuestionPage.getLabel_questionName().getText().trim())){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_Owner().getText(),owner,"Owner of the assignment is not displayed");

            //Expected - 4 : Edit option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");

            //Expected - 5 : Delete option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");

            //Expected - 6. Preview button must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Preview().isDisplayed(), true, "Preview button is not displayed");

            //Expected - 8. "TLO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");

            //Expected - 9. "ELO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");

            assessmentQuestionPage.getButton_Delete().click();//Click on Delete button
            assessmentQuestionPage.getYesButton_Delete().click();

            boolean questionFound  = false;

            try{
                driver.findElement(By.className("question-edit"));
                questionFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            //Expected - 10. The question must be deleted
            Assert.assertEquals(questionFound, false, "Question is not deleted");


        }catch(Exception e){
            Assert.fail("Exception in testcase 'anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForLabelAnImageText' in class 'EditingQuestionsInDraftStatus'",e);
        }
    }


    @Test(priority = 19,enabled = true)
    //TC row no. 139. An instructor should be able to delete the question upon clicking delete option (owns the assignment)
    public void anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForLabelAnImageDropdown(){

        try{
            Assignments pf_assignments =PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);

            String appendChar = "a";
            int dataIndex = 1342;
            new SignUp().teacher(appendChar,1);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            new Assignment().create(dataIndex, "labelanimagedropdown");//Create a new assignment
            pf_assignments.getButton_review().click();//Click on 'Review' Button
            pf_assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(dataIndex);

            owner = pf_assignments.getLabelValue_Ownwer().getText();
            questionID = pf_assignments.getLabelValue_QuestionID().getText();
            questionText = pf_assignments.getCreateAssignmentQuestionName().getText();

            pf_assignments.getRightArrow().click();//Click on Question's Right arrow

            //Expected - 1 : The particular question should be opened
            if(!questionText.trim().contains(assessmentQuestionPage.getLabel_questionName().getText().trim())){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_Owner().getText(),owner,"Owner of the assignment is not displayed");

            //Expected - 4 : Edit option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");

            //Expected - 5 : Delete option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");

            //Expected - 6. Preview button must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Preview().isDisplayed(), true, "Preview button is not displayed");

            //Expected - 8. "TLO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");

            //Expected - 9. "ELO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");

            assessmentQuestionPage.getButton_Delete().click();//Click on Delete button
            assessmentQuestionPage.getYesButton_Delete().click();

            boolean questionFound  = false;

            try{
                driver.findElement(By.className("question-edit"));
                questionFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            //Expected - 10. The question must be deleted
            Assert.assertEquals(questionFound, false, "Question is not deleted");


        }catch(Exception e){
            Assert.fail("Exception in testcase 'anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForLabelAnImageDropdown' in class 'EditingQuestionsInDraftStatus'",e);
        }
    }


    @Test(priority = 20,enabled = true)
    //TC row no. 139. An instructor should be able to delete the question upon clicking delete option (owns the assignment)
    public void anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForResequence(){

        try{
            Assignments pf_assignments =PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);

            String appendChar = "a";
            int dataIndex = 1415;
            new SignUp().teacher(appendChar,1);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            new Assignment().create(dataIndex, "resequence");//Create a new assignment
            pf_assignments.getButton_review().click();//Click on 'Review' Button
            pf_assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(dataIndex);

            owner = pf_assignments.getLabelValue_Ownwer().getText();
            questionID = pf_assignments.getLabelValue_QuestionID().getText();
            questionText = pf_assignments.getCreateAssignmentQuestionName().getText();

            pf_assignments.getRightArrow().click();//Click on Question's Right arrow

            //Expected - 1 : The particular question should be opened
            if(!questionText.trim().contains(assessmentQuestionPage.getLabel_questionName().getText().trim())){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_Owner().getText(),owner,"Owner of the assignment is not displayed");

            //Expected - 4 : Edit option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");

            //Expected - 5 : Delete option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");

            //Expected - 6. Preview button must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Preview().isDisplayed(), true, "Preview button is not displayed");

            //Expected - 8. "TLO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");

            //Expected - 9. "ELO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");

            assessmentQuestionPage.getButton_Delete().click();//Click on Delete button
            assessmentQuestionPage.getYesButton_Delete().click();

            boolean questionFound  = false;

            try{
                driver.findElement(By.className("question-edit"));
                questionFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            //Expected - 10. The question must be deleted
            Assert.assertEquals(questionFound, false, "Question is not deleted");


        }catch(Exception e){
            Assert.fail("Exception in testcase 'anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForResequence' in class 'EditingQuestionsInDraftStatus'",e);
        }
    }




    @Test(priority = 21,enabled = true)
    //TC row no. 139. An instructor should be able to delete the question upon clicking delete option (owns the assignment)
    public void anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForClozeMatrix(){

        try{
            Assignments pf_assignments =PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);

            String appendChar = "a";
            int dataIndex = 1490;
            new SignUp().teacher(appendChar,1);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            new Assignment().create(dataIndex, "clozematrix");//Create a new assignment
            pf_assignments.getButton_review().click();//Click on 'Review' Button
            pf_assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(dataIndex);

            owner = pf_assignments.getLabelValue_Ownwer().getText();
            questionID = pf_assignments.getLabelValue_QuestionID().getText();
            questionText = pf_assignments.getCreateAssignmentQuestionName().getText();

            pf_assignments.getRightArrow().click();//Click on Question's Right arrow

            //Expected - 1 : The particular question should be opened
            if(!questionText.trim().contains(assessmentQuestionPage.getLabel_questionName().getText().trim())){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_Owner().getText(),owner,"Owner of the assignment is not displayed");

            //Expected - 4 : Edit option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");

            //Expected - 5 : Delete option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");

            //Expected - 6. Preview button must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Preview().isDisplayed(), true, "Preview button is not displayed");

            //Expected - 8. "TLO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");

            //Expected - 9. "ELO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");

            assessmentQuestionPage.getButton_Delete().click();//Click on Delete button
            assessmentQuestionPage.getYesButton_Delete().click();

            boolean questionFound  = false;

            try{
                driver.findElement(By.className("question-edit"));
                questionFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            //Expected - 10. The question must be deleted
            Assert.assertEquals(questionFound, false, "Question is not deleted");


        }catch(Exception e){
            Assert.fail("Exception in testcase 'anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForClozeMatrix' in class 'EditingQuestionsInDraftStatus'",e);
        }
    }




    @Test(priority = 22,enabled = true)
    //TC row no. 139. An instructor should be able to delete the question upon clicking delete option (owns the assignment)
    public void anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForGraphPlotter(){

        try{
            Assignments pf_assignments =PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);

            String appendChar = "a";
            int dataIndex = 1565;
            new SignUp().teacher(appendChar,1);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            new Assignment().create(dataIndex, "graphplotter");//Create a new assignment
            pf_assignments.getButton_review().click();//Click on 'Review' Button
            pf_assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(dataIndex);

            owner = pf_assignments.getLabelValue_Ownwer().getText();
            questionID = pf_assignments.getLabelValue_QuestionID().getText();
            questionText = pf_assignments.getCreateAssignmentQuestionName().getText();

            pf_assignments.getRightArrow().click();//Click on Question's Right arrow

            //Expected - 1 : The particular question should be opened
            if(!questionText.trim().contains(assessmentQuestionPage.getLabel_questionName().getText().trim())){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_Owner().getText(),owner,"Owner of the assignment is not displayed");

            //Expected - 4 : Edit option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");

            //Expected - 5 : Delete option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");

            //Expected - 6. Preview button must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Preview().isDisplayed(), true, "Preview button is not displayed");

            //Expected - 8. "TLO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");

            //Expected - 9. "ELO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");

            assessmentQuestionPage.getButton_Delete().click();//Click on Delete button
            assessmentQuestionPage.getYesButton_Delete().click();

            boolean questionFound  = false;

            try{
                driver.findElement(By.className("question-edit"));
                questionFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            //Expected - 10. The question must be deleted
            Assert.assertEquals(questionFound, false, "Question is not deleted");


        }catch(Exception e){
            Assert.fail("Exception in testcase 'anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForGraphPlotter' in class 'EditingQuestionsInDraftStatus'",e);
        }
    }


    @Test(priority = 23,enabled = true)
    //TC row no. 139. An instructor should be able to delete the question upon clicking delete option (owns the assignment)
    public void anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForClozeFormula(){

        try{
            Assignments pf_assignments =PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);

            String appendChar = "a";
            int dataIndex = 1638;
            new SignUp().teacher(appendChar,1);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            new Assignment().create(dataIndex, "clozeformula");//Create a new assignment
            pf_assignments.getButton_review().click();//Click on 'Review' Button
            pf_assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(dataIndex);

            owner = pf_assignments.getLabelValue_Ownwer().getText();
            questionID = pf_assignments.getLabelValue_QuestionID().getText();
            questionText = pf_assignments.getCreateAssignmentQuestionName().getText();

            pf_assignments.getRightArrow().click();//Click on Question's Right arrow

            //Expected - 1 : The particular question should be opened
            if(!questionText.trim().contains(assessmentQuestionPage.getLabel_questionName().getText().trim())){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_Owner().getText(),owner,"Owner of the assignment is not displayed");

            //Expected - 4 : Edit option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");

            //Expected - 5 : Delete option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");

            //Expected - 6. Preview button must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Preview().isDisplayed(), true, "Preview button is not displayed");

            //Expected - 8. "TLO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");

            //Expected - 9. "ELO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");

            assessmentQuestionPage.getButton_Delete().click();//Click on Delete button
            assessmentQuestionPage.getYesButton_Delete().click();

            boolean questionFound  = false;

            try{
                driver.findElement(By.className("question-edit"));
                questionFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            //Expected - 10. The question must be deleted
            Assert.assertEquals(questionFound, false, "Question is not deleted");


        }catch(Exception e){
            Assert.fail("Exception in testcase 'anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForClozeFormula' in class 'EditingQuestionsInDraftStatus'",e);
        }
    }

    @Test(priority = 24,enabled = true)
    //TC row no. 139. An instructor should be able to delete the question upon clicking delete option (owns the assignment)
    public void anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForNumberLine(){

        try{
            Assignments pf_assignments =PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);

            String appendChar = "a";
            int dataIndex = 1710;
            new SignUp().teacher(appendChar,1);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Assignment().create(dataIndex, "numberline");
            pf_assignments.getButton_review().click();//Click on 'Review' Button
            pf_assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(dataIndex);

            owner = pf_assignments.getLabelValue_Ownwer().getText();
            questionID = pf_assignments.getLabelValue_QuestionID().getText();
            questionText = pf_assignments.getCreateAssignmentQuestionName().getText();

            pf_assignments.getRightArrow().click();//Click on Question's Right arrow

            //Expected - 1 : The particular question should be opened
            if(!questionText.trim().contains(assessmentQuestionPage.getLabel_questionName().getText().trim())){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_Owner().getText(),owner,"Owner of the assignment is not displayed");

            //Expected - 4 : Edit option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");

            //Expected - 5 : Delete option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");

            //Expected - 6. Preview button must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Preview().isDisplayed(), true, "Preview button is not displayed");

            //Expected - 8. "TLO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");

            //Expected - 9. "ELO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");

            assessmentQuestionPage.getButton_Delete().click();//Click on Delete button
            assessmentQuestionPage.getYesButton_Delete().click();

            boolean questionFound  = false;
            try{
                driver.findElement(By.className("question-edit"));
                questionFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            //Expected - 10. The question must be deleted
            Assert.assertEquals(questionFound, false, "Question is not deleted");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase 'anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForNumberLine' in class 'EditingQuestionsInDraftStatus'",e);
        }
    }

    @Test(priority = 25,enabled = true)
    //TC row no. 139. An instructor should be able to delete the question upon clicking delete option (owns the assignment)
    public void anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForClassification(){

        try{
            Assignments pf_assignments =PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);

            String appendChar = "a";
            int dataIndex = 1781;
            new SignUp().teacher(appendChar,1);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Assignment().create(dataIndex, "classification");
            pf_assignments.getButton_review().click();//Click on 'Review' Button
            pf_assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(dataIndex);

            owner = pf_assignments.getLabelValue_Ownwer().getText();
            questionID = pf_assignments.getLabelValue_QuestionID().getText();
            questionText = pf_assignments.getCreateAssignmentQuestionName().getText();

            pf_assignments.getRightArrow().click();//Click on Question's Right arrow

            //Expected - 1 : The particular question should be opened
            if(!questionText.trim().contains(assessmentQuestionPage.getLabel_questionName().getText().trim())){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_Owner().getText(),owner,"Owner of the assignment is not displayed");

            //Expected - 4 : Edit option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");

            //Expected - 5 : Delete option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");

            //Expected - 6. Preview button must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Preview().isDisplayed(), true, "Preview button is not displayed");

            //Expected - 8. "TLO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");

            //Expected - 9. "ELO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");

            assessmentQuestionPage.getButton_Delete().click();//Click on Delete button
            assessmentQuestionPage.getYesButton_Delete().click();

            boolean questionFound  = false;
            try{
                driver.findElement(By.className("question-edit"));
                questionFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            //Expected - 10. The question must be deleted
            Assert.assertEquals(questionFound, false, "Question is not deleted");


        }catch(Exception e){
            Assert.fail("Exception in testcase 'anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForClassification' in class 'EditingQuestionsInDraftStatus'",e);
        }
    }

    @Test(priority = 26,enabled = true)
    //TC row no. 139. An instructor should be able to delete the question upon clicking delete option (owns the assignment)
    public void anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForPassageBased(){

        try{
            Assignments pf_assignments =PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);

            String appendChar = "a";
            int dataIndex = 1852;
            new SignUp().teacher(appendChar,1);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Assignment().create(dataIndex, "passage");
            pf_assignments.getButton_review().click();//Click on 'Review' Button
            pf_assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(dataIndex);
            owner = driver.findElements(By.xpath("//span[@class='as-questionType']")).get(1).getText();
            driver.findElement(By.cssSelector("div[class='as-question-set-details']")).click();


            questionID = pf_assignments.getLabelValue_QuestionID().getText();
            questionText = driver.findElements(By.cssSelector("div[class='lsm-createAssignment-Question']")).get(1).getText();
            Thread.sleep(1000);
            new Click().clickByListClassName("as-questionDetails-clickArrow",1);
            //Expected - 1 : The particular question should be opened
            if(!questionText.trim().contains(assessmentQuestionPage.getLabel_questionName().getText().trim())){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_Owner().getText(),owner,"Owner of the assignment is not displayed");

            //Expected - 4 : Edit option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");

            //Expected - 5 : Delete option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");

            //Expected - 6. Preview button must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Preview().isDisplayed(), true, "Preview button is not displayed");

            //Expected - 8. "TLO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");

            //Expected - 9. "ELO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");

            assessmentQuestionPage.getButton_Delete().click();//Click on Delete button
            assessmentQuestionPage.getYesButton_Delete().click();

            boolean questionFound  = false;
            try{
                driver.findElement(By.className("question-edit"));
                questionFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            //Expected - 10. The question must be deleted
            Assert.assertEquals(questionFound, false, "Question is not deleted");


        }catch(Exception e){
            Assert.fail("Exception in testcase 'anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForForPassageBased' in class 'EditingQuestionsInDraftStatus'",e);
        }

    }


    @Test(priority = 27,enabled = true)
    //TC row no. 139. An instructor should be able to delete the question upon clicking delete option (owns the assignment)
    public void anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForMatchingTables(){

        try{
            Assignments pf_assignments =PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);

            String appendChar = "a";
            int dataIndex = 1925;
            new SignUp().teacher(appendChar,1);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Assignment().create(dataIndex, "matchingtables");
            pf_assignments.getButton_review().click();//Click on 'Review' Button
            pf_assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(dataIndex);

            owner = pf_assignments.getLabelValue_Ownwer().getText();
            questionID = pf_assignments.getLabelValue_QuestionID().getText();
            questionText = pf_assignments.getCreateAssignmentQuestionName().getText();

            pf_assignments.getRightArrow().click();//Click on Question's Right arrow

            //Expected - 1 : The particular question should be opened
            if(!questionText.trim().contains(assessmentQuestionPage.getLabel_questionName().getText().trim())){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_Owner().getText(),owner,"Owner of the assignment is not displayed");

            //Expected - 4 : Edit option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");

            //Expected - 5 : Delete option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");

            //Expected - 6. Preview button must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Preview().isDisplayed(), true, "Preview button is not displayed");

            //Expected - 8. "TLO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");

            //Expected - 9. "ELO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");

            assessmentQuestionPage.getButton_Delete().click();//Click on Delete button
            assessmentQuestionPage.getYesButton_Delete().click();

            boolean questionFound  = false;
            try{
                driver.findElement(By.className("question-edit"));
                questionFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            //Expected - 10. The question must be deleted
            Assert.assertEquals(questionFound, false, "Question is not deleted");


        }catch(Exception e){
            Assert.fail("Exception in testcase 'anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForForMatchingTables' in class 'EditingQuestionsInDraftStatusForClassification'",e);
        }

    }

    @Test(priority = 28,enabled = true)
    //TC row no. 139. An instructor should be able to delete the question upon clicking delete option (owns the assignment)
    public void anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForSentenceResponse(){

        try{
            Assignments pf_assignments =PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);

            String appendChar = "a";
            int dataIndex = 1997;
            new SignUp().teacher(appendChar,1);//SignUp as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Assignment().create(dataIndex, "sentenceresponse");
            pf_assignments.getButton_review().click();//Click on 'Review' Button
            pf_assignments.getButton_saveForLater().click();//Click on 'Save for later' button
            new Assignment().selectAssessment(dataIndex);

            owner = pf_assignments.getLabelValue_Ownwer().getText();
            questionID = pf_assignments.getLabelValue_QuestionID().getText();
            questionText = pf_assignments.getCreateAssignmentQuestionName().getText();

            pf_assignments.getRightArrow().click();//Click on Question's Right arrow

            //Expected - 1 : The particular question should be opened
            if(!questionText.trim().contains(assessmentQuestionPage.getLabel_questionName().getText().trim())){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : ID of the question must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_QuestionID().getText(),questionID,"ID of the question is not displayed");

            //Expected - 3 : Owner of the assignment must be displayed
            Assert.assertEquals(assessmentQuestionPage.getLabelValue_Owner().getText(),owner,"Owner of the assignment is not displayed");

            //Expected - 4 : Edit option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Edit().getText(),"Edit","Edit Option is not displayed");

            //Expected - 5 : Delete option must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Delete().getText(),"Delete","Delete Option is not displayed");

            //Expected - 6. Preview button must be displayed
            Assert.assertEquals(assessmentQuestionPage.getButton_Preview().isDisplayed(), true, "Preview button is not displayed");

            //Expected - 8. "TLO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_tlo().getText(), "1.OA - Operations & Algebraic Thinking", "TLO is not displayed properly");

            //Expected - 9. "ELO should be displayed properly"
            Assert.assertEquals(assessmentQuestionPage.getLabel_elo().getText(), "1.OA.A.1 - Use addition and subtraction within 20 to solve word problems involving situations of adding to", "ELO is not displayed properly");

            assessmentQuestionPage.getButton_Delete().click();//Click on Delete button
            assessmentQuestionPage.getYesButton_Delete().click();

            boolean questionFound  = false;
            try{
                driver.findElement(By.className("question-edit"));
                questionFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            //Expected - 10. The question must be deleted
            Assert.assertEquals(questionFound, false, "Question is not deleted");


        }catch(Exception e){
            Assert.fail("Exception in testcase 'anAssignmentInDraftStatusThatCoversAllTypesOfQuestionsInstructorBeingTheOwnerForForSentenceResponse' in class 'EditingQuestionsInDraftStatusForClassification'",e);
        }

    }






}



