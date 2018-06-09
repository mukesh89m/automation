package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.assignpoints;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.preview.PreviewPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.KeysSend;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextSend;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by pragya on 03-03-2015.
 */
public class AssignPointsDragAndDrop extends Driver{

    @Test(priority = 1, enabled = true)
    public void addingPointsDragAndDrop() {
        //Adding the points to DragAndDrop question explicitly
        try {
            String appendChar = "a";
            int dataIndex = 4020;

            TrueFalseQuestionCreation trueFalse = PageFactory.initElements(driver, TrueFalseQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver, AssignmentReview.class);
            QuestionEditPage questionEditPage = PageFactory.initElements(driver, QuestionEditPage.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            Assessments assessments = PageFactory.initElements(driver,Assessments.class);

            new SignUp().teacher(appendChar, dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getButton_create().click();//Click on create new assessment
            addDragAndDropQuestion(dataIndex);
            trueFalse.getSave_button().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));

            //TC row no. - 10 : Adding the points true false question explicitly
            String winHandleBefore = driver.getWindowHandle();

            new Assignment().submitPreviewQuestion();

            //Expected - 2 : The default points should be 1
            Assert.assertEquals(previewPage.getQuestionPoint_previewPage().getAttribute("value"), "1", "Question is not carrying one point by default");
            driver.close();
            driver.switchTo().window(winHandleBefore);//Switch the driver control back to main window

            trueFalse.getPoint_editor().click();//Click on point editor
            trueFalse.getPoint_textbox().clear();//Remove the point
            trueFalse.getSave_button().click();//Click on save button
            Thread.sleep(4000);

            //Expected - 3 : An error message should be displayed if no points are assigned
            Assert.assertEquals(trueFalse.getError_savingQuestion().getText(), "Please enter points", "Error message is not displayed correctly if the point is not entered");

            trueFalse.getPoint_textbox().click();//Click on point text box
            trueFalse.getPoint_textbox().sendKeys("0");//Enter the point
            trueFalse.getSave_button().click();//Click on save button
            Thread.sleep(4000);

            //Expected - 4 : 0 point should not be allowed
            Assert.assertEquals(trueFalse.getError_savingQuestion().getText(), "Please enter points", "Error message is not displayed correctly if the point entered as 0");

            trueFalse.getPoint_textbox().click();//Click on point text box
            trueFalse.getPoint_textbox().sendKeys("2.3334");//Enter the point
            trueFalse.getSave_button().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));

            new Assignment().submitPreviewQuestion();

            //Expected - Point should take only 2 digits after decimal
            Assert.assertEquals(previewPage.getQuestionPoint_previewPage().getAttribute("value"), "2.33", "Question is not carrying one point by default");

            driver.close();
            driver.switchTo().window(winHandleBefore);//Switch the driver control back to main window

            trueFalse.getPoint_editor().click();//Click on point text box
            trueFalse.getPoint_textbox().clear();//Remove the point
            trueFalse.getPoint_textbox().sendKeys("2");//Enter the point
            trueFalse.getSave_button().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));
            trueFalse.getButton_review().click();//Click on Review button

            //Instructor question short view
            //Expected 1 : Instructor should be able to add the Points to each question explicitly
            // In the question authoring view, instructor should be able to define the points to that particular question
            Assert.assertEquals(listPage.getPoint().getText(), "2", "Point is not displaying as entered by instructor");

            //TC row no. - 16 : Instructor question edit
            listPage.getArrowLink().click();//Click on arrow link

            //Instructor question expanded view
            Assert.assertEquals(trueFalse.getPoint_textbox().getAttribute("value"), "2", "Point is not displayed correctly in instructor expanded view");

            trueFalse.getPoint_editor().click();//Click on point editor
            trueFalse.getPoint_textbox().clear();//Remove the point
            trueFalse.getPoint_textbox().sendKeys("3");//Enter the point
            driver.findElement(By.xpath("//html/body")).click();
            assessmentReview.getBack_arrow().click();//Click on back arrow button

            //Expected - 1 : Instructor should be able to edit the points assigned to a question, in the Edit view
            Assert.assertEquals(listPage.getPoint().getText(), "3", "Point is not displaying as modified by instructor");

            listPage.getArrowLink().click();//Click on arrow
            assessmentReview.getButton_Edit().click();//Click on edit button
            Thread.sleep(2000);
            trueFalse.getPoint_editor().click();//Click on point editor
            trueFalse.getPoint_textbox().clear();//Remove the point
            trueFalse.getPoint_textbox().click();//Click on Point text box
            Thread.sleep(2000);
            trueFalse.getPoint_textbox().sendKeys("4");//Enter the point
            driver.findElement(By.xpath("//html/body")).click();
            questionEditPage.getBack_arrow().click();//Click on back button
            Thread.sleep(2000);
            trueFalse.getButton_review().click();//Click on review button

            //Expected : Instructor should be able to edit the points assigned to a question, in the Edit view
            //Assert.assertEquals(listPage.getPoint().getText(), "4", "Point is not displaying as modified by instructor");

            listPage.getArrowLink().click();//Click on arrow link
            assessmentReview.getButton_Edit().click();//Click on edit button
            Thread.sleep(2000);
            trueFalse.getPoint_editor().click();//Click on point editor
            trueFalse.getPoint_textbox().clear();//Remove the point
            trueFalse.getPoint_textbox().click();//Click on Point text box
            trueFalse.getPoint_textbox().sendKeys("5");//Enter the point
            driver.findElement(By.xpath("//html/body")).click();
            trueFalse.getButton_review().click();//Click on review button

            //Expected : Instructor should be able to edit the points assigned to a question, in the Edit view
            Assert.assertEquals(listPage.getPoint().getText(), "5", "Point is not displaying as modified by instructor");

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'addingPointsDragAndDrop' in class 'AssignPoints'", e);

        }
    }

    @Test(priority = 2, enabled = true)
    public void pointAssignedToParticularQuestionDragAndDrop() {
        try {
            String appendChar = "a1";
            String appendChar1 = "b1";
            int dataIndex = 4157;

            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            TrueFalseQuestionCreation trueFalse = PageFactory.initElements(driver, TrueFalseQuestionCreation.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver,AssignmentReview.class);

            new SignUp().teacher(appendChar,dataIndex);//SignUp as 1st teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex,"Grade 2","Mathematics","Math - Common Core");//create class
            new Navigator().logout();//log out

            new SignUp().teacher(appendChar1,dataIndex);//SignUp as 2nd teacher
            new Login().loginAsInstructor(appendChar1, dataIndex);//Login as 2nd teacher
            new School().createWithOnlyName(appendChar1, dataIndex);//create school
            new Classes().createClass(appendChar1, dataIndex,"Grade 2","Mathematics","Math - Common Core");//create class
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as 1st teacher
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getButton_create().click();//Click on create new assessment            trueFalse.getPoint_editor().click();//Click on point text box
            addDragAndDropQuestion(dataIndex);
            trueFalse.getPoint_editor().click();//Click on point editor
            trueFalse.getPoint_textbox().clear();//Remove the point
            trueFalse.getPoint_textbox().sendKeys("2");//Enter the point
            trueFalse.getSave_button().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));
            trueFalse.getButton_review().click();//Click on review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assessment

            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1, dataIndex);//Login as 2nd instructor
            new Navigator().navigateTo("assignment");//Navigate to assessment page
            assignments.getButton_newAssessment().click();//Click on new assessment
            assignments.getUseExistingAssessmentLink().click();//Click on use existing assessment link
            assignments.getRadioButton_public().click();//Click on Community
            new AssignPointsTrueFalse().selectExistingAssessmentToCustomize(dataIndex);//Select the existing assessment to customize
            listPage.getArrowLink().click();//Click on arrow
            Thread.sleep(2000);
            trueFalse.getPoint_editor().click();//Click on Point editor
            trueFalse.getPoint_textbox().clear();//Remove the point
            driver.switchTo().activeElement().sendKeys("5");//Enter the point
            driver.findElement(By.xpath("//html/body")).click();

            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as 1st instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment
            assessments.getAssignment().click();//Select assessment

            listPage.getArrowLink().click();//Click on arrow
            Thread.sleep(2000);

            //Expected - 1 : The points assigned should reflect only to that particular assessment
            Assert.assertEquals(trueFalse.getPoint_textbox().getAttribute("value"), "2", "Point is not displayed correctly in instructor expanded view");

            new Navigator().navigateTo("assignment");//Navigate to assessment page
            assignments.getButton_newAssessment().click();//Click on new assessment
            assignments.getLinks_useExistingAndCreateAssessment().get(1).click();//Click on create new assessment link
            createAssessment.getAssessment_editbox().sendKeys("New Assessment");//give the assessment name
            Thread.sleep(2000);
            tloListPage.getButton_select().click();//Click on select button
            new Assignment().selectOwner("4157","Me");
            tloListPage.getCheckbox().click();//Select the question
            trueFalse.getButton_review().click();//Click on review button
            Thread.sleep(2000);
            listPage.getArrowLink().click();//Click on arrow
            trueFalse.getPoint_editor().click();//Click on point editor
            trueFalse.getPoint_textbox().clear();//Remove the point
            trueFalse.getPoint_textbox().sendKeys("6");//Enter the point
            driver.findElement(By.xpath("//html/body")).click();
            assessmentReview.getBack_arrow().click();//Click on back arrow button
            listPage.getButton_saveForLater().click();//Click on save for later buttons
            assignments.getAssessmentName().click();//Select the assessment
            listPage.getArrowLink().click();//Click on arrow
            Thread.sleep(2000);

            //Expected - 2 : If the same question appears in another assessment, then the points should not reflect
            Assert.assertEquals(trueFalse.getPoint_textbox().getAttribute("value"), "2", "Point is not displayed correctly in instructor expanded view");

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'pointAssignedToParticularQuestionDragAndDrop' in class 'AssignPoints'", e);

        }
    }

    @Test(priority = 3,enabled = true)
    public void gradedOnAssignmentSubNotToChangePointsDragAndDrop(){
        try{String appendChar = "a";
            int dataIndex = 4253;

            TrueFalseQuestionCreation trueFalse = PageFactory.initElements(driver, TrueFalseQuestionCreation.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            AssessmentResponses assessmentResponses = PageFactory.initElements(driver,AssessmentResponses.class);
            PreviewPage previewPage = PageFactory.initElements(driver,PreviewPage.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);

            new SignUp().teacher(appendChar, dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode = new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Register a student
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getButton_create().click();//Click on create new assessment
            addDragAndDropQuestion(dataIndex);
            trueFalse.getPoint_editor().click();//Click on point editor
            trueFalse.getPoint_textbox().clear();//remove the point
            driver.switchTo().activeElement().sendKeys("5");//Enter the point
            trueFalse.getSave_button().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));

            //TC row no. - 17 : Instructor question Preview
            trueFalse.getButton_Preview().click();//Click on Preview button

            String winHandleBefore = driver.getWindowHandle();

            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            //Expected - 1 :  No points should be displayed in the preview attempt screen
            if(previewPage.getPreview_page().getText().contains("5.0")){
                Assert.fail("Point is displayed in preview attempt screen");
            }
            Thread.sleep(2000);

            trueFalse.getPreview_submit().click();//Click on Submit button

            //Expected - 2 : Once the answer is submitted, the points should be displayed in the evaluation view of Preview
            Assert.assertEquals(previewPage.getQuestionPoint_previewPage().getAttribute("value"), "5", "Question is not carrying one point by default");

            driver.close();
            driver.switchTo().window(winHandleBefore);//Switch the driver control back to main window

            trueFalse.getButton_review().click();//Click on review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign to class
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            new Assignment().submitAssignment(dataIndex);
            assessments.getButton_continue().click();//Click on Continue button

            //TC row no. - 26 : Evaluation
            //Expected - 1 : The evaluation should be for the points set by the instructor and not the default points
            //TC row no. - 30 : Student report view
            //Expected : For each question, the grading should take the points assigned by the instructor and the default points
            //Expected : In the performance summary view, the points for each question should be displayed as assigned by the instructor
            Assert.assertEquals(assessments.getQuestionpoint().getText(),"5","Evaluation point is nott displayed correctly as assigned by instructor");

            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment
            assessments.getView_grades().click();//Click on view grades
            Thread.sleep(2000);
            assessmentResponses.getQuestionPresentInGrid().click();//Click on question

            //TC row no. - 27 : Instructor gradebook view
            //Expected - 1 : The marks in the view response page should show the Points assigned by the instructor and not the default points
            Assert.assertEquals(previewPage.getQuestionPoint_previewPage().getAttribute("value"),"5","The marks in the view response is not showing the point assigned by instructor");

            boolean pointEditorPresent =  new BooleanValue().presenceOfElement(dataIndex,By.className("points-input-edit-icon"));

            //Expected - 3 :  If the assessment is in graded state, instructor should not be able to Change the points
            Assert.assertEquals(pointEditorPresent,false,"Point editor is present to edit the point");

            assessments.getBackarrow_assessmentResponse().click();//Click on back arrow

            assessments.getGrade_gradable().click();//Click on score assigned to student

            //Expected - The point for each question should be displayed
            Assert.assertEquals(assessments.getQuestionpoint().getText(),"5","Evaluation point is not displayed correctly as assigned by instructor");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'gradedOnAssignmentSubNotToChangePointsDragAndDrop' in class 'AssignPoints'", e);
        }
    }


    @Test(priority = 4,enabled = true)
    //Assigning the points
    public void gradedExplicitByTeacherNotToChangePointsDragAndDrop(){
        try{String appendChar = "a";
            int dataIndex = 4351;

            TrueFalseQuestionCreation trueFalse = PageFactory.initElements(driver, TrueFalseQuestionCreation.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            AssessmentResponses assessmentResponses = PageFactory.initElements(driver,AssessmentResponses.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);

            new SignUp().teacher(appendChar, dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode = new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Register a student
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getButton_create().click();//Click on create new assessment
            addDragAndDropQuestion(dataIndex);
            trueFalse.getPoint_editor().click();//Click on point editor
            trueFalse.getPoint_textbox().clear();//remove the point
            driver.switchTo().activeElement().sendKeys("5");//Enter the point
            trueFalse.getSave_button().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));
            trueFalse.getButton_review().click();//Click on review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign to class
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            new Assignment().submitAssignment(dataIndex);
            assessments.getButton_continue().click();//Click on Continue button

            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment
            assessments.getView_grades().click();//Click on view grades
            Thread.sleep(2000);
            assessmentResponses.getQuestionPresentInGrid().click();//Click on question

            boolean pointEditorPresent =  new BooleanValue().presenceOfElement(dataIndex,By.className("points-input-edit-icon"));

            //Expected - 3 :  If the assessment is in graded state, instructor should not be able to Change the points
            Assert.assertEquals(pointEditorPresent,false,"Point editor is present to edit the point");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'gradedExplicitByTeacherNotToChangePointsDragAndDrop' in class 'AssignPoints'", e);
        }
    }

    @Test(priority = 5,enabled = true)
    public void pointsVerifyMultipleQuestionForDragAndDrop(){
        try{String appendChar = "a";
            int dataIndex = 4408;

            TrueFalseQuestionCreation trueFalse = PageFactory.initElements(driver, TrueFalseQuestionCreation.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            AssessmentResponses assessmentResponses = PageFactory.initElements(driver,AssessmentResponses.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver,AssignmentReview.class);

            new SignUp().teacher(appendChar, dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode = new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Register a student
            new Navigator().logout();//Log out
            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getButton_create().click();//Click on create new assessment
            addDragAndDropQuestion(dataIndex);
            trueFalse.getSave_button().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));
            trueFalse.getButton_review().click();//Click on review button
            listPage.getButton_addMore().click();//Click on add more button
            addDragAndDropQuestion(4409);
            trueFalse.getPoint_editor().click();//Click on Point editor
            trueFalse.getPoint_textbox().clear();//Remove the point
            trueFalse.getPoint_textbox().sendKeys("4");//Enter the point
            trueFalse.getSave_button().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));
            trueFalse.getButton_review().click();//Click on review button
            listPage.getButton_addMore().click();//Click on add more button
            addDragAndDropQuestion(4410);
            trueFalse.getPoint_editor().click();//Click on Point editor
            trueFalse.getPoint_textbox().clear();//Remove the point
            trueFalse.getPoint_textbox().sendKeys("5");//Enter the point
            trueFalse.getSave_button().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));
            trueFalse.getButton_review().click();//Click on review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign to class

            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            new Navigator().navigateTo("assignment");//Navigate to assignment

            new Assignment().submitAssignment(dataIndex);
            Thread.sleep(2000);
            assessments.getButton_continue().click();//Click on Continue button
            Thread.sleep(2000);


            new Navigator().logout();//Log out

            //Instructor gradebook view
            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment
            assessments.getView_grades().click();//Click on view response

            //Expected - 2 :  For each question the grading should take the Points assigned by the instructor and not the default points
            //Expected - 3 :  If instructor doesn't assign any points to a question explicitly then, the default points should be considered
            assessmentResponses.getQuestionPresentInGrid().click();//Click on 1st question
            Assert.assertEquals(trueFalse.getPoint_textbox().getAttribute("value"), "1", "Default point is not shown");
            assessmentReview.getBack_arrow().click();//Click on back arrow
            assessmentResponses.getList_questionPresentInGrid().get(1).click();//Click on 2nd question
            Assert.assertEquals(trueFalse.getPoint_textbox().getAttribute("value"), "4", "Point is not displayed as entered by instructor");
            assessmentReview.getBack_arrow().click();//Click on back arrow
            assessmentResponses.getList_questionPresentInGrid().get(2).click();//Click on 3rd question
            Assert.assertEquals(trueFalse.getPoint_textbox().getAttribute("value"), "5", "Point is not displayed as entered by instructor");
            assessmentReview.getBack_arrow().click();//Click on back arrow

            assessments.getGrade_gradable().click();//Click on 1st question score assigned to student
            Assert.assertEquals(assessments.getQuestionpoint().getText(),"1","Default point is not shown");
            assessments.getButton_next().click();//Click on next button
            Thread.sleep(2000);
            Assert.assertEquals(assessments.getQuestionpoint().getText(),"4","Default point is not shown");
            Thread.sleep(2000);
            assessments.getButton_next().click();//Click on next button
            Thread.sleep(2000);
            Assert.assertEquals(assessments.getQuestionpoint().getText(),"5","Default point is not shown");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'pointsVerifyMultipleQuestionForDragAndDrop' in class 'AssignPoints'", e);
        }
    }


    public void addDragAndDropQuestion(int index){
        try{String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(index));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(index));

            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(index));

            tloListPage.getButton_createForAll().click();//Click on create button

            if (filename == null){
                filename = "img.png";
            }
            new Click().clickByid("qtn-dnd-type");//click on Drag and Drop type question
            closeHelp();
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name

            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Drag and Drop " + questiontext);//type the question

            List<WebElement> answers = driver.findElements(By.className("answer"));
            answers.get(0).click();
            driver.findElement(By.id("answer_math_edit")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","5");


            answers.get(1).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            answers.get(2).click();
            new Click().clickByid("answer_image_edit");
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("pickfiles")));
            //new Click().clickByid("pickfiles");
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(3000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",driver.findElement(By.id("widget-createimage_start_queue")));
            //new Click().clickByid("widget-createimage_start_queue");
            (new WebDriverWait(driver, 10))
                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("img[class='answer-choice-image-size']")));

            answers.get(3).click();
            driver.findElement(By.id("answer_math_edit")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","10");

            List<WebElement> answerstodrag = driver.findElements(By.className("answer"));
            Actions ac = new Actions(driver);
            answerstodrag.get(0).click();
            ac.dragAndDrop(driver.findElement(By.id("ans-drag-btn")),driver.findElement(By.id("canvas"))).build().perform();

        }catch (Exception e){
            Assert.fail("Exception in testcase 'addDragAndDrop' in class 'AssignPoints'", e);

        }
    }

}
