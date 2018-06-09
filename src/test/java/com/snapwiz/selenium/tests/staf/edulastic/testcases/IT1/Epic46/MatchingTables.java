package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.Epic46;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.preview.PreviewPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Action;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.ExplicitWait;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.BooleanValue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

/**
 * Created by pragya on 22-01-2015.
 */
public class MatchingTables extends Driver {

    @Test(priority = 1, enabled = true)
    //TC row no. - 111 : Default landing page behaviour when instructor selects Matching tables question type
    public void defaultLandingPage() {
        try {
            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            MatchingTablesQuestionCreation matchingTables = PageFactory.initElements(driver, MatchingTablesQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver,PreviewPage.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver,AssignmentReview.class);
            String appendChar = "a";
            int dataIndex = 26;
            new SignUp().teacher(appendChar, dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getButton_create().click();//Click on create new assessment
            tloListPage.getButton_create().click();//Click on create button

            questionTypesPage.getIcon_MatchingTables().click();//Click on Matching Tables question type

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name

            //Expected - 1 :  Question title must be displayed as "Matching tables question"
            Assert.assertEquals(matchingTables.getQuestionTitle().getText(), "Matching Tables question", "Question title is not shown as expected");

            //Expected - 2 : Text field to enter question body  with 'Enter question text' as default content
            Assert.assertEquals(matchingTables.getTextBox_QuestionField().getText(), "Enter Question Text", "By default 'Enter question text' is not displayed in the question body");

            matchingTables.getTextBox_QuestionField().clear();
            matchingTables.getTextBox_QuestionField().sendKeys(questiontext);//Enter the question
            driver.findElement(By.xpath("//html/body")).click();

            //Expected - 3 : 'Redactor' must be enabled for the question text field
            Assert.assertEquals(matchingTables.getTextBox_QuestionField().getText(), questiontext, "Redactor is not enabled");

            List<WebElement> answerChoices = matchingTables.getList_editbox_answerChoice();

            boolean defaultAnswerFound = false;
            for (WebElement defaultAnswer : answerChoices) {
                if (defaultAnswer.getText().length() != 0) {
                    defaultAnswerFound = true;
                    break;
                }
            }
            //Expected - 4 : No default answer choice must be there, instructor must be able to create the answer choices in the table
            Assert.assertEquals(defaultAnswerFound, false, "Default answer choice is present in table");

            matchingTables.getList_editbox_answerChoice().get(0).click();//Click on 1st answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("MatchingTables");//Enter the text
            driver.findElement(By.xpath("//html/body")).click();

            matchingTables.getList_editbox_answerChoice().get(1).click();//Click on 2nd answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("2");//Enter the text

            matchingTables.getList_editbox_answerChoice().get(2).click();//Click on 3rd answer choice
            matchingTables.getAnswerChoice_image().click();//Click on image
            new QuestionCreate().fileUploadInQuestionCreation("26");
            Thread.sleep(5000);

            matchingTables.getList_editbox_answerChoice().get(3).click();//Click on 4th answer choice
            matchingTables.getAnswerChoice_mathML().click();//Click on MAthML type
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root", "9");

            matchingTables.getList_editbox_answerChoice().get(4).click();//Click on 5th answer choice
            matchingTables.getAnswerChoice_mathML().click();//Click on MAthML type
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root", "4");
            driver.findElement(By.xpath("//html/body")).click();

            //Expected - 5 : A default 3 row 3 column must be displayed

            if (matchingTables.getList_rowsInTable().size() != 3) {
                Assert.fail("by default,3 rows are not displayed");
            }
            if (matchingTables.getList_columnsInTable().size() != 3) {
                Assert.fail("by default,3 columns are not displayed");
            }
            //Expected - 6 : Instructor must be able to edit the values of the first column of the matching table to create a problem statement
            //Expected - 7 : Instructor must be able to edit the 1st row of the matching tables to enter the "Matching column" values
            //Expected - 8 : Instructor must be able to edit the 1st column of the matching tables to enter the Problem statement
            Assert.assertEquals(matchingTables.getList_editbox_answerChoice().get(0).getText(), "MatchingTables", "Instructor is not able to edit the values in table");
            Assert.assertEquals(matchingTables.getList_editbox_answerChoice().get(1).getText(), "2", "Instructor is not able to edit the values in table");

            //Expected - The column width must be auto adjusted when the text is entered
            //Assert.assertEquals(matchingTables.getList_editbox_answerChoice().get(2).getText(), str, "Instructor is not able to edit the values in table");

            if(!matchingTables.getUploadedImage().getAttribute("src").contains("img")){
                Assert.fail("image is not uploaded");
            }

            if (!matchingTables.getList_editbox_answerChoice().get(3).getText().replaceAll("[\\t\\n\\r]", " ").contains("9")) {
                Assert.fail("Instructor is not able to edit the values in table");
            }

            if (!matchingTables.getList_editbox_answerChoice().get(4).getText().replaceAll("[\\t\\n\\r]", " ").contains("4")) {
                Assert.fail("Instructor is not able to edit the values in table");

            }
            List<WebElement> checkboxesInTable = matchingTables.getList_checkboxOrRadioButtonToVerify();
            boolean checkboxFound = true;
            for (WebElement checkbox : checkboxesInTable) {
                if (!checkbox.getAttribute("type").equals("checkbox")) {
                    checkboxFound = false;
                    break;
                }
            }
            //Expected - 10 :  By default checkboxes must be displayed for matching value inputs
            Assert.assertEquals(checkboxFound, true, "By default checkboxes are not displayed for matching value inputs");

            //>>>>>>TC row no.- 119 : Instructor must be able to Add rows or Columns by clicking on the link 'Add a new row'
            matchingTables.getLink_addNewRow().click();//Click on Add new row

            //Expected - 1 : Clicking on 'Add a new row' should add a row to the matching table at the bottom
            if (matchingTables.getList_rowsInTable().size() != 4) {
                Assert.fail("new row is not added after adding a new row");
            }
            matchingTables.getList_editbox_answerChoice().get(5).click();//Click on 5th edit box
            matchingTables.getAnswerchoice_text().click();//Click on text
            driver.switchTo().activeElement().sendKeys("6");//Enter the value
            driver.findElement(By.xpath("//html/body")).click();
            Assert.assertEquals(matchingTables.getList_editbox_answerChoice().get(5).getText(), "6", "New row is not added at the bottom");

            for (WebElement checkbox : checkboxesInTable) {
                if (!checkbox.getAttribute("type").equals("checkbox")) {
                    checkboxFound = false;
                    break;
                }
            }
            //Expected - 2 :  The new row should have either checkbox or radio button based on the type of matching table selected
            Assert.assertEquals(checkboxFound, true, "The new row does not have checkbox");

            //>>>>>>TC row no. - 120 : Instructor must be able to Add rows or Columns by clicking on the link 'Add a new column'
            matchingTables.getLink_addNewColumn().click();//Click on Add new column

            //Expected - 1 :  Clicking on 'Add a new column' should add a column to the matching table on the right side
            if (matchingTables.getList_columnsInTable().size() != 4) {
                Assert.fail("new column is not added after adding a new column");
            }

            for (WebElement checkbox : checkboxesInTable) {
                if (!checkbox.getAttribute("type").equals("checkbox")) {
                    checkboxFound = false;
                    break;
                }
            }
            //Expected - 2 : The new column should have either checkbox or radio button based on the type of matching table selected
            Assert.assertEquals(checkboxFound, true, "The new column does not have checkbox");

            //Expected - 3 : Text box should be enabled on double click on the new column
            //Expected - 4 : Instructor should be able to enter the text in the text input field to assign a matching name
            Actions ac = new Actions(driver);
            ac.doubleClick(matchingTables.getList_editbox_answerChoice().get(3)).build().perform();
            ac.doubleClick(matchingTables.getList_editbox_answerChoice().get(3)).build().perform();

            matchingTables.getList_editbox_answerChoice().get(3).click();//Click on 4th column text field
            matchingTables.getAnswerchoice_text().click();//Click on text
            driver.switchTo().activeElement().sendKeys("9");
            driver.findElement(By.xpath("//html/body")).click();
            Assert.assertEquals(matchingTables.getList_editbox_answerChoice().get(3).getText(), "9", "New column is not added to the right");

            //Allow multiple selection checkbox should be displayed
            Assert.assertEquals(matchingTables.getCheckBox_allowMultipleSelection().isDisplayed(),true,"Allow multiple selection checkbox is not displayed");

            //>>>>>>TC row no. - 122 : Unchecking 'Allow multiple selection'
            matchingTables.getCheckBox_allowMultipleSelection().click();//Uncheck 'Allow multiple selection' checkbox
            for (WebElement checkbox : checkboxesInTable) {
                if (!checkbox.getAttribute("type").equals("radio")) {
                    checkboxFound = false;
                    break;
                }
            }
            //Expected - 1 : The check boxes for matching values must be changed to radio button
            Assert.assertEquals(checkboxFound, true, "The checkboxes for matching values are not changed to radio button");

            //>>>>>TC row no. - 124 : Managing answer choices when 'Allow multiple selection' is selected
            matchingTables.getCheckBox_allowMultipleSelection().click();//Check 'Allow multiple selection' checkbox

            //Expected - 2 : Instructor should be able to select multiple Checkboxes as correct answers
            matchingTables.getList_checkboxOrRadioButtonInTable().get(0).click();//Check 1st checkbox
            matchingTables.getList_checkboxOrRadioButtonInTable().get(3).click();//Check 4th checkbox
            matchingTables.getList_checkboxOrRadioButtonInTable().get(4).click();//Check 5th checkbox
            matchingTables.getList_checkboxOrRadioButtonInTable().get(6).click();//Check 7th checkbox

            //>>>>>>TC row no. 125 : Managing answer choices when 'Allow multiple selection' is not selected
            matchingTables.getCheckBox_allowMultipleSelection().click();//Uncheck 'Allow multiple selection' checkbox

            //Expected - 2 : Instructor should be able to select only one Radio button as correct answers
            matchingTables.getList_checkboxOrRadioButtonInTable().get(0).click();//Check 1st radio button
            matchingTables.getList_checkboxOrRadioButtonInTable().get(1).click();//Check 2nd radio button
            matchingTables.getList_checkboxOrRadioButtonInTable().get(4).click();//Check 5th radio button
            matchingTables.getList_checkboxOrRadioButtonInTable().get(3).click();//Check 4th radio button
            matchingTables.getList_checkboxOrRadioButtonInTable().get(7).click();//Check 8th radio button

            //>>>>>>TC row o. - 126 : Options allowed
            //Expected - 2 :  'Allow students to use scratchpad' should be displayed, should be disabled by default
            Assert.assertEquals(matchingTables.getCheckbox_scratchpad().isSelected(), false, "'Allow student to use Scratchpad' checkbox is checked");


            Actions builder = new Actions(driver);
            builder.moveToElement(matchingTables.getList_editbox_answerChoice().get(4), 10, 3 ).click().build().perform();
            matchingTables.getDeleteButton_2nd_row().click();//delete the 2nd row

            //Expected - Clicking on 'Delete' icon should delete the respective row
            if(matchingTables.getList_rowsInTable().size() !=3){
                Assert.fail("2nd row is not deleted");
            }

            builder.moveToElement(matchingTables.getList_editbox_answerChoice().get(1), 10, 3 ).click().build().perform();
            matchingTables.getDeleteButton_2nd_column().click();//delete the 2nd column

            //Expected - Clicking on 'Delete' icon should delete the respective Column
            if(matchingTables.getList_columnsInTable().size() !=3){
                Assert.fail("2nd column is not deleted");
            }

            //Delete icon must not be displayed for first row and column header
            builder.moveToElement(matchingTables.getList_editbox_answerChoice().get(0), 10, 3 ).click().build().perform();

            boolean deletePresentIn1stRow = new BooleanValue().presenceOfElement(dataIndex,By.xpath("//td[@class='delete-cell matrix-box-bg-highlight']/div/img"));
            Assert.assertEquals(deletePresentIn1stRow,false,"Delete icon is displayed for first row");

            builder.moveToElement(matchingTables.getList_editbox_answerChoice().get(3), 10, 3 ).click().build().perform();

            builder.moveToElement(matchingTables.getList_editbox_answerChoice().get(0), 10, 3 ).click().build().perform();

            boolean deletePresentIn1stColumn = new BooleanValue().presenceOfElement(dataIndex,By.xpath("/th[@class='delete-cell matrix-box-bg-highlight']/div/img"));
            Assert.assertEquals(deletePresentIn1stColumn,false,"Delete icon is displayed for first column");

            matchingTables.getList_checkboxOrRadioButtonInTable().get(1).click();//Check 2nd radio button
            matchingTables.getButton_save().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));
            matchingTables.getButton_preview().click();//Click on preview button
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }

            String questionTextPreviewPage = previewPage.getQuestion_text().getText();
            String answerChoice1PreviewPage = assessmentReview.getList_editbox_answerChoice().get(0).getText();
            String answerChoice2PreviewPage = previewPage.getImage_answerchoice().getAttribute("src");
            String answerChoice3PreviewPage = assessmentReview.getList_editbox_answerChoice().get(2).getText();
            String answerChoice4PreviewPage = assessmentReview.getList_editbox_answerChoice().get(3).getText();
            String answerChoice5PreviewPage = assessmentReview.getList_editbox_answerChoice().get(4).getText();

            //Expected - The question should be displayed properly with all the radio buttons
            // deleted rows and columns should not be displayed
            Assert.assertEquals(questionTextPreviewPage,questiontext,"Question text is not same in preview page as entered by instructor");
            Assert.assertEquals(answerChoice1PreviewPage,"MatchingTables","Answer choice1 is not displayed as entered by instructor");

            if(!answerChoice2PreviewPage.contains("img")){
                Assert.fail("Image is not displayed in 2nd answer choice as uploaded by instructor");
            }
            Assert.assertEquals(answerChoice3PreviewPage,"9","Answer choice3 is not displayed as entered by instructor");

            if(!answerChoice4PreviewPage.replaceAll("[\\t\\n\\r]"," ").contains("4")){
                Assert.fail("Answer choice4 is not displayed as entered by instructor");
            }

            Assert.assertEquals(answerChoice5PreviewPage,"6","Answer choice5 is not displayed as entered by instructor");

            for (WebElement checkbox : checkboxesInTable) {
                if (!checkbox.getAttribute("type").equals("radio")) {
                    checkboxFound = false;
                    break;
                }
            }
            Assert.assertEquals(checkboxFound, true, "Question is not displayed with all the radio buttons");

            //Attempt the question and submit it
            previewPage.getList_radiobuttonorcheckbox().get(1).click();
            previewPage.getList_radiobuttonorcheckbox().get(3).click();

            matchingTables.getPreview_submit().click();//Click on submit button
            Thread.sleep(2000);

            //Expected - The validation should not be broken
            Assert.assertEquals(matchingTables.getListofanswers_radiobutton().get(0).getAttribute("class"),"matrix-box-preview matrix-ans-redit","Validation is not correct");
            Assert.assertEquals(matchingTables.getListofanswers_radiobutton().get(1).getAttribute("class"),"matrix-box-preview matrix-ans-redit correct-answer","Validation is not correct");
            Assert.assertEquals(matchingTables.getListofanswers_radiobutton().get(2).getAttribute("class"),"matrix-box-preview matrix-ans-redit matrix-preview-unchecked-correct-answer","Validation is not correct");
            Assert.assertEquals(matchingTables.getListofanswers_radiobutton().get(3).getAttribute("class"),"matrix-box-preview matrix-ans-redit wrong-answer","Validation is not correct");


            for (WebElement checkbox : checkboxesInTable) {
                if (!checkbox.getAttribute("type").equals("radio")) {
                    checkboxFound = false;
                    break;
                }
            }
            Assert.assertEquals(checkboxFound, true, "Question is not displayed with all the radio buttons");

        } catch (Exception e) {
            Assert.fail("Exception in testcase 'defaultLandingPage' in class ' MatchingTable '", e);

        }
    }

    @Test(priority = 2, enabled = true)
    //TC row no. - 127 : Parameters
    public void parameterAndEdit() {
        try {Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            MatchingTablesQuestionCreation matchingTables = PageFactory.initElements(driver, MatchingTablesQuestionCreation.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver,AssignmentReview.class);
            String appendChar = "a";
            int dataIndex = 234;
            new SignUp().teacher(appendChar, dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getButton_create().click();//Click on create new assessment
            tloListPage.getButton_create().click();//Click on create button

            questionTypesPage.getIcon_MatchingTables().click();//Click on MatchingTables question type
            closeHelp();

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name

            //Expected - 5 : The error message ( if any ) should be displayed.
            matchingTables.getButton_save().click();//Click on Save button
            Thread.sleep(2000);

            Assert.assertEquals(matchingTables.getMessage_text().getText(), "Question title should not be empty", "error message is not displayed correctly when question is not present");

            matchingTables.getTextBox_QuestionField().clear();
            matchingTables.getTextBox_QuestionField().sendKeys(questiontext);//Enter the question

            matchingTables.getButton_save().click();//Click on Save button
            Thread.sleep(2000);

            Assert.assertEquals(matchingTables.getMessage_text().getText(),"Set atleast one answer for each row","error message is not displayed correctly");

            matchingTables.getList_editbox_answerChoice().get(3).click();//Click on 2nd row
            matchingTables.getDeleteButton_2nd_row().click();//Click on delete button

            //Expected - A warning message saying "At Least two rows and two columns are mandatory"
            Assert.assertEquals(matchingTables.getMessage_text().getText(),"Atleast two rows and two columns are mandatory","error message is not displayed correctly");

            matchingTables.getList_editbox_answerChoice().get(0).click();//Click on 1st answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("MatchingTables");//Enter the text

            matchingTables.getList_editbox_answerChoice().get(1).click();//Click on 2nd column
            matchingTables.getDeleteButton_2nd_column().click();//Click on delete button

            //Expected - A warning message saying "At Least two rows and two columns are mandatory"
            Assert.assertEquals(matchingTables.getMessage_text().getText(),"Atleast two rows and two columns are mandatory","error message is not displayed correctly");

            matchingTables.getList_editbox_answerChoice().get(1).click();//Click on 2nd answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("2");//Enter the text

            matchingTables.getList_editbox_answerChoice().get(2).click();//Click on 3rd answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("3");//Enter the text

            matchingTables.getList_editbox_answerChoice().get(3).click();//Click on 4th answer choice
            matchingTables.getAnswerChoice_mathML().click();//Click on MAthML type
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root", "9");

            matchingTables.getList_editbox_answerChoice().get(4).click();//Click on 5th answer choice
            matchingTables.getAnswerChoice_mathML().click();//Click on MAthML type
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root", "4");
            driver.findElement(By.xpath("//html/body")).click();

            matchingTables.getButton_save().click();//Click on save button
            Thread.sleep(4000);

            Assert.assertEquals(matchingTables.getMessage_text().getText(),"Set atleast one answer for each row","error message is not displayed correctly");

            //>>>>>>TC row no. - 129 : Instructor should not be able to save the question unless one matching column has been added
            //Expected - 1 : A notification must be displayed as “Set atleast one answer for each row”
            Assert.assertEquals(matchingTables.getMessage_text().getText(),"Set atleast one answer for each row","error message is not displayed correctly");

            //>>>>>>TC row no. - 127 : Parameters
            matchingTables.getTextbox_solution().clear();
            driver.switchTo().activeElement().sendKeys("Solution Text");
            matchingTables.getTextbox_hint().clear();
            driver.switchTo().activeElement().sendKeys("Hint Text");
            driver.findElement(By.xpath("//html/body")).click();

            //Expected - 2 : Questions should have solution and hint as other question types
            Assert.assertEquals(matchingTables.getTextbox_solution().isDisplayed(),true,"Solution text box is not displayed");
            Assert.assertEquals(matchingTables.getTextbox_hint().isDisplayed(),true,"Hint text box is not displayed");

            //>>>>>>TC row no. - 128 : Instructor must be able to save the question by clicking on 'save' button
            matchingTables.getList_checkboxOrRadioButtonInTable().get(1).click();//Check 2nd checkbox
            matchingTables.getList_checkboxOrRadioButtonInTable().get(2).click();//Check 3rd checkbox
            matchingTables.getButton_save().click();//Click on save button
            Thread.sleep(4000);

            //Expected - 1 : The question must be saved
            Assert.assertEquals(matchingTables.getTextBox_QuestionField().getText(),"MatchingTables Question Type parameter","Question is not saved");

            //Expected - 2 :  A notification must be displayed as 'Saved'
            Assert.assertEquals(matchingTables.getMessage_text().getText(),"Saved.","A notification 'Saved' is not displayed");

            //>>>>>>TC row no. - 134 : Instructor short view
            matchingTables.getButton_review().click();//Click on Review button

            //Expected- 1 : Only question title should be displayed.
            if(!listPage.getCreateAssignmentQuestionName().getText().contains(questiontext)){
                Assert.fail("Question title is not displayed");
            }

            //Expected - 2 : Question Type should be displayed as “Matching Tables”
            Assert.assertEquals(listPage.getQuestionType().getText(),"Matching Tables","Question type is not displayed correctly");


            //Expected - 3 : Owner of the question must be displayed
            Assert.assertEquals(listPage.getLabelValue_Ownwer().getText(),"You","Owner of the question is not displayed");

            //Expected - 4 : Question ID must be displayed
            if(listPage.getLabelValue_QuestionID().getText().length()==0)
            {
                Assert.fail("Question id is not displayed");

            }
            //Expected - 5 : Corresponding TLO and ELO under which the question falls must be displayed
            if(!listPage.getLabelValue_StandardTlo().getText().contains("1.OA, 1.OA.A.1"))
            {
                Assert.fail("Tlo and Elo is not displayed");
            }
            String str1 = listPage.getCreateAssignmentQuestionName().getText();

            listPage.getArrowLink().click();//Select the assignment
            String str2 = assessmentQuestionPage.getLabel_questionName().getText();

            //>>>>>>TC row no. - 135 : Instructor question expanded view
            //Expected : Edit,delete,duplicate and preview link should be displayed
            Assert.assertEquals(assessmentReview.getButton_duplicate().isDisplayed(),true,"duplicate link is not displayed");
            Assert.assertEquals(assessmentReview.getButton_Edit().isDisplayed(),true,"edit link is not displayed");
            Assert.assertEquals(matchingTables.getButton_preview().isDisplayed(),true,"preview link is not displayed");
            Assert.assertEquals(assessmentReview.getButton_delete().isDisplayed(),true,"delete link is not displayed");

            //Expected - 1 :  Question body must be displayed
            if(!str1.trim().contains(str2)){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : Matching tables must be displayed
            Assert.assertEquals(assessmentReview.getList_editbox_answerChoice().get(0).getText(),"MatchingTables","Answer choice1 is not displayed");
            Assert.assertEquals(assessmentReview.getList_editbox_answerChoice().get(1).getText(), "2", "Answer choice2 is not displayed");
            Assert.assertEquals(assessmentReview.getList_editbox_answerChoice().get(2).getText(), "3", "Answer choice3 is not displayed");

            if (!assessmentReview.getList_editbox_answerChoice().get(3).getText().replaceAll("[\\t\\n\\r]", " ").contains("9")) {
                Assert.fail("Answer choice4 is not displayed");
            }

            if (!assessmentReview.getList_editbox_answerChoice().get(4).getText().replaceAll("[\\t\\n\\r]", " ").contains("4")) {
                Assert.fail("Answer choice5 is not displayed");

            }
            List<WebElement> checkboxesInTable = matchingTables.getList_checkboxOrRadioButtonToVerify();
            boolean checkboxFound = true;
            for (WebElement checkbox : checkboxesInTable) {
                if (!checkbox.getAttribute("type").equals("checkbox")) {
                    checkboxFound = false;
                    break;
                }
            }
            Assert.assertEquals(checkboxFound,true,"Checkbox is not displayed even if 'Allow multiple election' checkbox is checked" );

            //>>>>>>TC row no. - 130 : Instructor should be able to edit the question by clicking on 'Edit' button
            matchingTables.getEditLink().click();//Click on Edit button

            //Expected - 1 : On clicking on edit, the questions must be populated with values
            Assert.assertEquals(matchingTables.getTextBox_QuestionField().getText(),questiontext,"Question is not populated as entered by instructor");
            Assert.assertEquals(matchingTables.getList_editbox_answerChoice_editPage().get(0).getText(),"MatchingTables","Answer choice1 is not populated with the value given by instructor");
            Assert.assertEquals(matchingTables.getList_editbox_answerChoice_editPage().get(1).getText(), "2", "Answer choice2 is not populated with the value given by instructor");
            Assert.assertEquals(matchingTables.getList_editbox_answerChoice_editPage().get(2).getText(), "3", "Answer choice3 is not populated with the value given by instructord");


            if (!matchingTables.getList_editbox_answerChoice_editPage().get(3).getText().replaceAll("[\\t\\n\\r]", " ").contains("9")) {
                Assert.fail("Answer choice4 is not displayed");
            }

            if (!matchingTables.getList_editbox_answerChoice_editPage().get(4).getText().replaceAll("[\\t\\n\\r]", " ").contains("4")) {
                Assert.fail("Answer choice5 is not displayed");

            }

            new Action().doubleClick(matchingTables.getAnswerChoice_1st());
            driver.switchTo().activeElement().clear();
            driver.switchTo().activeElement().sendKeys("MatchingTables1");
            driver.findElement(By.xpath("//html/body")).click();

            //Expected - 2 : Instructor should be able to edit the text in the matching tables by double clicking on the text
            Assert.assertEquals(matchingTables.getList_editbox_answerChoice_editPage().get(0).getText(),"MatchingTables1","Instructor is not able to edit the text by double click");


        } catch (Exception e) {
            Assert.fail("Exception in testcase 'parameterAndEdit' in class ' MatchingTable '", e);
        }
    }

        @Test(priority = 3,enabled = true)
        public void duplicateQuestion(){
            try{Assessments assessments = PageFactory.initElements(driver, Assessments.class);
                CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
                TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
                QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
                MatchingTablesQuestionCreation matchingTables = PageFactory.initElements(driver, MatchingTablesQuestionCreation.class);
                AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
                AssignmentReview assessmentReview = PageFactory.initElements(driver,AssignmentReview.class);
                String appendChar = "a";
                int dataIndex = 555;
                new SignUp().teacher(appendChar, dataIndex);//SignUp as a teacher
                new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
                new School().createWithOnlyName(appendChar, dataIndex);//create school
                new Classes().createClass(appendChar, dataIndex);//create class
                new Navigator().navigateTo("assignment");//Navigate to Assignment page
                assessments.getButton_newAssessment().click();//Click on new assessment
                createAssessment.getButton_create().click();//Click on create new assessment
                tloListPage.getButton_create().click();//Click on create button

                questionTypesPage.getIcon_MatchingTables().click();//Click on MatchingTables question type

                String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
                String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

                createAssessment.getAssessment_editbox().clear();
                createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name

                matchingTables.getTextBox_QuestionField().clear();
                matchingTables.getTextBox_QuestionField().sendKeys(questiontext);//Enter the question

                matchingTables.getList_editbox_answerChoice().get(0).click();//Click on 1st answer choice
                matchingTables.getAnswerchoice_text().click();//Click on text type
                driver.switchTo().activeElement().sendKeys("MatchingTables");//Enter the text

                matchingTables.getList_editbox_answerChoice().get(1).click();//Click on 2nd answer choice
                matchingTables.getAnswerchoice_text().click();//Click on text type
                driver.switchTo().activeElement().sendKeys("2");//Enter the text

                matchingTables.getList_editbox_answerChoice().get(2).click();//Click on 3rd answer choice
                matchingTables.getAnswerchoice_text().click();//Click on text type
                driver.switchTo().activeElement().sendKeys("3");//Enter the text

                matchingTables.getList_editbox_answerChoice().get(3).click();//Click on 4th answer choice
                matchingTables.getAnswerchoice_text().click();//Click on text type
                driver.switchTo().activeElement().sendKeys("4");//Enter the text

                matchingTables.getList_editbox_answerChoice().get(4).click();//Click on 5th answer choice
                matchingTables.getAnswerchoice_text().click();//Click on text type
                driver.switchTo().activeElement().sendKeys("5");//Enter the text
                driver.findElement(By.xpath("//html/body")).click();

                matchingTables.getTextbox_solution().clear();
                driver.switchTo().activeElement().sendKeys("Solution Text");
                matchingTables.getTextbox_hint().clear();
                driver.switchTo().activeElement().sendKeys("Hint Text");
                driver.findElement(By.xpath("//html/body")).click();

                matchingTables.getList_checkboxOrRadioButtonInTable().get(1).click();//Check 2nd checkbox
                matchingTables.getList_checkboxOrRadioButtonInTable().get(2).click();//Check 3rd checkbox
                matchingTables.getButton_save().click();//Click on save button
                new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));

                matchingTables.getButton_review().click();//Click on Review button
                String id = listPage.getLabelValue_QuestionID().getText();
                listPage.getArrowLink().click();//Select the assignment

                assessmentReview.getButton_duplicate().click();//Click on duplicate button
                new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[contains(text(),'Question "+id+" has been duplicated')]")));

                //Expected - The question must be duplicated
                Assert.assertEquals(matchingTables.getNoofquestions().getText(),"2","question is not duplicated as no. of questions are not increased on review button");

                // A notification message saying "Question <id> has been duplicated"
                Assert.assertEquals(matchingTables.getMessage_text().getText(),"Question "+id+" has been duplicated","Duplicate message is not displayed");

            }catch (Exception e){
                Assert.fail("Exception in testcase 'duplicateQuestion' in class ' MatchingTable '", e);

            }
        }


    @Test(priority = 4,enabled = true)
    public void deleteQuestionAndReordering(){
        try{Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            MatchingTablesQuestionCreation matchingTables = PageFactory.initElements(driver, MatchingTablesQuestionCreation.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver,AssignmentReview.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            String appendChar = "a";
            int dataIndex = 639;
            new SignUp().teacher(appendChar, dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getButton_create().click();//Click on create new assessment
            tloListPage.getButton_create().click();//Click on create button

            questionTypesPage.getIcon_MatchingTables().click();//Click on MatchingTables question type

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name

            matchingTables.getTextBox_QuestionField().clear();
            matchingTables.getTextBox_QuestionField().sendKeys(questiontext+"1");//Enter the question

            matchingTables.getList_editbox_answerChoice().get(0).click();//Click on 1st answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("MatchingTables");//Enter the text

            matchingTables.getList_editbox_answerChoice().get(1).click();//Click on 2nd answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("2");//Enter the text

            matchingTables.getList_editbox_answerChoice().get(2).click();//Click on 3rd answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("3");//Enter the text

            matchingTables.getList_editbox_answerChoice().get(3).click();//Click on 4th answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("4");//Enter the text

            matchingTables.getList_editbox_answerChoice().get(4).click();//Click on 5th answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("5");//Enter the text
            driver.findElement(By.xpath("//html/body")).click();

            matchingTables.getTextbox_solution().clear();
            driver.switchTo().activeElement().sendKeys("Solution Text");
            matchingTables.getTextbox_hint().clear();
            driver.switchTo().activeElement().sendKeys("Hint Text");
            driver.findElement(By.xpath("//html/body")).click();

            matchingTables.getList_checkboxOrRadioButtonInTable().get(1).click();//Check 2nd checkbox
            matchingTables.getList_checkboxOrRadioButtonInTable().get(2).click();//Check 3rd checkbox
            matchingTables.getButton_save().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));

            matchingTables.getButton_review().click();//Click on Review button
            listPage.getButton_addMore().click();//Click on add more button
            closeHelp();
            tloListPage.getButton_create().click();//Click on create button
            questionTypesPage.getIcon_MatchingTables().click();//Click on Matching Tables question type

            Thread.sleep(2000);
            matchingTables.getTextBox_QuestionField().clear();
            matchingTables.getTextBox_QuestionField().sendKeys(questiontext+"2");//Enter the question

            matchingTables.getList_editbox_answerChoice().get(0).click();//Click on 1st answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("MatchingTables");//Enter the text

            matchingTables.getList_editbox_answerChoice().get(1).click();//Click on 2nd answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("5");//Enter the text

            matchingTables.getList_editbox_answerChoice().get(2).click();//Click on 3rd answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("4");//Enter the text

            matchingTables.getList_editbox_answerChoice().get(3).click();//Click on 4th answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("3");//Enter the text

            matchingTables.getList_editbox_answerChoice().get(4).click();//Click on 5th answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("2");//Enter the text
            matchingTables.getList_checkboxOrRadioButtonInTable().get(1).click();//Check 2nd checkbox
            matchingTables.getList_checkboxOrRadioButtonInTable().get(3).click();//Check 3rd checkbox

            matchingTables.getButton_save().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));
            matchingTables.getButton_review().click();//Click on review button

            Actions ac = new Actions(driver);
            listPage.getList_dragQuestion().get(1).click();//Click on drag area
            ac.dragAndDrop(listPage.getList_dragQuestion().get(1),listPage.getList_dragQuestion().get(0)).build().perform();

            //Expected - The question must be reordered
            Assert.assertEquals(listPage.getList_questionBody().get(0).getText(),"Q1: "+questiontext+"2","Questions are not re ordered");
            Assert.assertEquals(listPage.getList_questionBody().get(1).getText(),"Q2: "+questiontext+"1","Questions are not re ordered");

            listPage.getArrowLink().click();//Select the question 2
            assessmentReview.getButton_delete().click();//Click on delete button
            assessmentReview.getButton_yesDelete().click();//Click on yes
            Thread.sleep(4000);

            //Expected - The question must be deleted
            if(listPage.getList_questionBody().size()!=1){
                Assert.fail("question is not deleted");
            }

            if(!listPage.getList_questionBody().get(0).getText().contains(questiontext+"1")){
                Assert.fail("question is not deleted");

            }

            listPage.getButton_saveForLater().click();//Click on save for later button
            assignments.getButton_ViewDraftStatus().click();//Click on view drat status
            assignments.getAssessmentName().click();//Select the assessment


            //The question should no longer be displayed in the assessment
            if(listPage.getList_questionBody().size()!=1){
                Assert.fail("question is not deleted");
            }

            if(!listPage.getList_questionBody().get(0).getText().contains(questiontext+"1")){
                Assert.fail("question is not deleted");

            }


        }catch (Exception e){
            Assert.fail("Exception in testcase 'deleteQuestion' in class ' MatchingTable '", e);

        }
    }

       @Test(priority = 5,enabled = true)
       //TC row no - 54 : Deleting the rows and columns when there are answers in the Table (Allow multiple selection is selected)
       public void previewWithCheckbox(){
           try{Assessments assessments = PageFactory.initElements(driver, Assessments.class);
               CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
               TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
               QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
               MatchingTablesQuestionCreation matchingTables = PageFactory.initElements(driver, MatchingTablesQuestionCreation.class);
               PreviewPage previewPage = PageFactory.initElements(driver,PreviewPage.class);
               AssignmentReview assessmentReview = PageFactory.initElements(driver,AssignmentReview.class);
               String appendChar = "a";
               int dataIndex = 545;
               new SignUp().teacher(appendChar, dataIndex);//SignUp as a teacher
               new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
               new School().createWithOnlyName(appendChar, dataIndex);//create school
               new Classes().createClass(appendChar, dataIndex);//create class
               new Navigator().navigateTo("assignment");//Navigate to Assignment page
               assessments.getButton_newAssessment().click();//Click on new assessment
               createAssessment.getButton_create().click();//Click on create new assessment
               tloListPage.getButton_create().click();//Click on create button

               questionTypesPage.getIcon_MatchingTables().click();//Click on Matching Tables question type
               new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
               closeHelp();

               String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
               String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

               createAssessment.getAssessment_editbox().clear();
               createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name

               matchingTables.getTextBox_QuestionField().clear();
               matchingTables.getTextBox_QuestionField().sendKeys(questiontext);//Enter the question
               driver.findElement(By.xpath("//html/body")).click();

               matchingTables.getList_editbox_answerChoice().get(0).click();//Click on 1st answer choice
               matchingTables.getAnswerchoice_text().click();//Click on text type
               driver.switchTo().activeElement().sendKeys("MatchingTables");//Enter the text
               driver.findElement(By.xpath("//html/body")).click();

               matchingTables.getList_editbox_answerChoice().get(1).click();//Click on 2nd answer choice
               matchingTables.getAnswerchoice_text().click();//Click on text type
               driver.switchTo().activeElement().sendKeys("2");//Enter the text

               matchingTables.getList_editbox_answerChoice().get(2).click();//Click on 3rd answer choice
               matchingTables.getAnswerChoice_image().click();//Click on image
               new QuestionCreate().fileUploadInQuestionCreation("26");
               Thread.sleep(5000);

               matchingTables.getList_editbox_answerChoice().get(3).click();//Click on 4th answer choice
               matchingTables.getAnswerChoice_mathML().click();//Click on MAthML type
               new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root", "9");

               matchingTables.getList_editbox_answerChoice().get(4).click();//Click on 5th answer choice
               matchingTables.getAnswerChoice_mathML().click();//Click on MAthML type
               new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root", "4");
               driver.findElement(By.xpath("//html/body")).click();

               matchingTables.getLink_addNewRow().click();//Click on Add new row

               matchingTables.getList_editbox_answerChoice().get(5).click();//Click on 5th edit box
               matchingTables.getAnswerchoice_text().click();//Click on text
               driver.switchTo().activeElement().sendKeys("6");//Enter the value
               driver.findElement(By.xpath("//html/body")).click();

               matchingTables.getLink_addNewColumn().click();//Click on Add new column

               Actions ac = new Actions(driver);
               ac.doubleClick(matchingTables.getList_editbox_answerChoice().get(3)).build().perform();
               ac.doubleClick(matchingTables.getList_editbox_answerChoice().get(3)).build().perform();

               matchingTables.getList_editbox_answerChoice().get(3).click();//Click on 4th column text field
                matchingTables.getAnswerchoice_text().click();//Click on text
               driver.switchTo().activeElement().sendKeys("9");
               driver.findElement(By.xpath("//html/body")).click();

               Actions builder = new Actions(driver);
               builder.moveToElement(matchingTables.getList_editbox_answerChoice().get(4), 10, 3 ).click().build().perform();
               matchingTables.getDeleteButton_2nd_row().click();//delete the 2nd row

               builder.moveToElement(matchingTables.getList_editbox_answerChoice().get(1), 10, 3 ).click().build().perform();
               matchingTables.getDeleteButton_2nd_column().click();//delete the 2nd column

               previewPage.getList_radiobuttonorcheckbox().get(0).click();
               previewPage.getList_radiobuttonorcheckbox().get(1).click();
               previewPage.getList_radiobuttonorcheckbox().get(3).click();

               matchingTables.getButton_save().click();//Click on save button
               new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));

               matchingTables.getButton_preview().click();//Click on preview button
               for(String handle:driver.getWindowHandles())
               {
                   driver.switchTo().window(handle);
               }

               String questionTextPreviewPage = previewPage.getQuestion_text().getText();
               String answerChoice1PreviewPage = assessmentReview.getList_editbox_answerChoice().get(0).getText();
               String answerChoice2PreviewPage = previewPage.getImage_answerchoice().getAttribute("src");
               String answerChoice3PreviewPage = assessmentReview.getList_editbox_answerChoice().get(2).getText();
               String answerChoice4PreviewPage = assessmentReview.getList_editbox_answerChoice().get(3).getText();
               String answerChoice5PreviewPage = assessmentReview.getList_editbox_answerChoice().get(4).getText();

               //Expected - The question should be displayed properly with all the checkboxes
               // deleted rows and columns should not be displayed
               Assert.assertEquals(questionTextPreviewPage,questiontext,"Question text is not same in preview page as entered by instructor");
               Assert.assertEquals(answerChoice1PreviewPage,"MatchingTables","Answer choice1 is not displayed as entered by instructor");

               if(!answerChoice2PreviewPage.contains("img")){
                   Assert.fail("Image is not displayed in 2nd answer choice as uploaded by instructor");
               }
               Assert.assertEquals(answerChoice3PreviewPage,"9","Answer choice3 is not displayed as entered by instructor");

               if(!answerChoice4PreviewPage.replaceAll("[\\t\\n\\r]"," ").contains("4")){
                   Assert.fail("Answer choice4 is not displayed as entered by instructor");
               }

               Assert.assertEquals(answerChoice5PreviewPage,"6","Answer choice5 is not displayed as entered by instructor");

               List<WebElement> checkboxesInTable = matchingTables.getList_checkboxOrRadioButtonToVerify();
               boolean checkboxFound = true;

               for (WebElement checkbox : checkboxesInTable) {
                   if (!checkbox.getAttribute("type").equals("checkbox")) {
                       checkboxFound = false;
                       break;
                   }
               }
               Assert.assertEquals(checkboxFound, true, "Question is not displayed with all the checkboxes");

               //Attempt the question and submit it
               previewPage.getList_radiobuttonorcheckbox().get(0).click();
               previewPage.getList_radiobuttonorcheckbox().get(2).click();

               matchingTables.getPreview_submit().click();//Click on submit button
               Thread.sleep(2000);

               //Expected - The validation should not be broken
               Assert.assertEquals(matchingTables.getListofanswers_radiobutton().get(0).getAttribute("class"),"matrix-box-preview matrix-ans-redit correct-answer","Validation is not correct");
               Assert.assertEquals(matchingTables.getListofanswers_radiobutton().get(1).getAttribute("class"),"matrix-box-preview matrix-ans-redit matrix-preview-unchecked-correct-answer","Validation is not correct");
               Assert.assertEquals(matchingTables.getListofanswers_radiobutton().get(2).getAttribute("class"),"matrix-box-preview matrix-ans-redit wrong-answer","Validation is not correct");
               Assert.assertEquals(matchingTables.getListofanswers_radiobutton().get(3).getAttribute("class"),"matrix-box-preview matrix-ans-redit matrix-preview-unchecked-correct-answer","Validation is not correct");

               for (WebElement checkbox : checkboxesInTable) {
                   if (!checkbox.getAttribute("type").equals("checkbox")) {
                       checkboxFound = false;
                       break;
                   }
               }
               Assert.assertEquals(checkboxFound, true, "Question is not displayed with all the checkboxes");

           }catch (Exception e){
               Assert.fail("Exception in testcase 'previewWithCheckbox' in class ' MatchingTable '", e);

           }


       }


        @Test(priority = 6,enabled = true)
        //TC row no. - Instructor should be able to have a preview look by clicking on ' Preview button'
        public void previewAndStudentAttemptMultipleSelect(){
            try{Assessments assessments = PageFactory.initElements(driver, Assessments.class);
                CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
                TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
                QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
                MatchingTablesQuestionCreation matchingTables = PageFactory.initElements(driver, MatchingTablesQuestionCreation.class);
                PreviewPage previewPage = PageFactory.initElements(driver,PreviewPage.class);
                AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
                AssignmentReview assessmentReview = PageFactory.initElements(driver,AssignmentReview.class);
                String appendChar = "a";
                int dataIndex = 497;
                new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
                new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
                new School().createWithOnlyName(appendChar, dataIndex);//create school
                String classCode=new Classes().createClass(appendChar, dataIndex);//create class

                new Navigator().logout();

                new SignUp().studentDirectRegister(appendChar, classCode, 497);//create student
                new Navigator().logout();//log out

                new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
                new Navigator().navigateTo("assignment");//Navigate to Assignment page
                assessments.getButton_newAssessment().click();//Click on new assessment
                createAssessment.getButton_create().click();//Click on create new assessment
                tloListPage.getButton_create().click();//Click on create button
                questionTypesPage.getIcon_MatchingTables().click();//Click on 'Matching Tables' question type
                new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
                closeHelp();

                String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
                String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

                createAssessment.getAssessment_editbox().clear();
                createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name

                matchingTables.getTextBox_QuestionField().clear();
                matchingTables.getTextBox_QuestionField().sendKeys(questiontext);//Enter the question

                matchingTables.getList_editbox_answerChoice().get(0).click();//Click on 1st answer choice
                matchingTables.getAnswerchoice_text().click();//Click on text type
                driver.switchTo().activeElement().sendKeys("MatchingTables");//Enter the text

                matchingTables.getList_editbox_answerChoice().get(1).click();//Click on 2nd answer choice
                matchingTables.getAnswerchoice_text().click();//Click on text type
                driver.switchTo().activeElement().sendKeys("2");//Enter the text

                matchingTables.getList_editbox_answerChoice().get(2).click();//Click on 3rd answer choice
                matchingTables.getAnswerchoice_text().click();//Click on text type
                driver.switchTo().activeElement().sendKeys("3");//Enter the text

                matchingTables.getList_editbox_answerChoice().get(3).click();//Click on 4th answer choice
                matchingTables.getAnswerchoice_text().click();//Click on text type
                driver.switchTo().activeElement().sendKeys("4");//Enter the text

                matchingTables.getList_editbox_answerChoice().get(4).click();//Click on 5th answer choice
                matchingTables.getAnswerchoice_text().click();//Click on text type
                driver.switchTo().activeElement().sendKeys("5");//Enter the text

                matchingTables.getList_checkboxOrRadioButtonInTable().get(0).click();//Check 1st checkbox
                matchingTables.getList_checkboxOrRadioButtonInTable().get(1).click();//Check 2nd checkbox
                matchingTables.getList_checkboxOrRadioButtonInTable().get(2).click();//Check 3rd checkbox

                matchingTables.getButton_save().click();//Click on save button
                new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));
                matchingTables.getButton_preview().click();//Click on Preview page

                //Expected - 1 : A new tab must be opened that displays the preview
                String winHandleBefore = driver.getWindowHandle();
                for(String handle:driver.getWindowHandles())
                {
                    driver.switchTo().window(handle);
                }
                String questionTextPreviewPage = previewPage.getQuestion_text().getText();
                String answerChoice1PreviewPage = assessmentReview.getList_editbox_answerChoice().get(0).getText();
                String answerChoice2PreviewPage = assessmentReview.getList_editbox_answerChoice().get(1).getText();
                String answerChoice3PreviewPage = assessmentReview.getList_editbox_answerChoice().get(2).getText();
                String answerChoice4PreviewPage = assessmentReview.getList_editbox_answerChoice().get(3).getText();
                String answerChoice5PreviewPage = assessmentReview.getList_editbox_answerChoice().get(4).getText();

                matchingTables.getPreview_submit().click();//Click on submit button

                //>>>>>>TC row no. - 127 : Parameters
                //Expected - 1 : One question should be worth one point
                Assert.assertEquals(previewPage.getQuestionPoint_previewPage().getAttribute("value"),"1","Question is not carrying one point");

                driver.close();
                driver.switchTo().window(winHandleBefore);

                Select select = new Select(matchingTables.getDifficulty_level());

                //Expected - 3 :  Questions should be allowed to be  tagged with difficulty level
                select.selectByVisibleText("Easy");
                select.selectByVisibleText("Medium");
                select.selectByVisibleText("Hard");
                Thread.sleep(2000);

                matchingTables.getLearningObjective().click();//Click on learning objective

                //Expected - 4 : Questions can be tagged with LOs as other question types
                if(!matchingTables.getList_tloElo_learningObjective().get(0).getText().trim().contains("1.OA"))
                {
                    Assert.fail("Expected TLO is not displayed");
                }

                if(!matchingTables.getList_tloElo_learningObjective().get(1).getText().trim().contains("1.OA.A.1"))
                {
                    Assert.fail("Expected ELO is not displayed");
                }

                int defaultListSize = matchingTables.getList_tloElo_learningObjective().size();

                if(defaultListSize!=2)
                {
                    Assert.fail("Learning Objective list size is not equal to 2");
                }

                matchingTables.getAdd_learningObjetive().click();//Click on add Learning Objective link
                matchingTables.getCheckbox_elo1_OA_A_2().click();//Click on 1.OA.A.2 ELO
                matchingTables.getButton_associate().click();//Click on Associate button
                Thread.sleep(2000);

                matchingTables.getLearningObjective().click();//Click on learning objective
                matchingTables.getLearningObjective().click();//Click on learning objective
                Thread.sleep(2000);

                if(!matchingTables.getList_tloElo_learningObjective().get(2).getText().trim().contains("1.OA.A.2"))
                {
                    Assert.fail("Expected ELO is not displayed");
                }

                int listSizeAfterAddingTLO = matchingTables.getList_tloElo_learningObjective().size();

                if(listSizeAfterAddingTLO!=3)
                {
                    Assert.fail("Learning Objective list size is not equal to 3");
                }
                matchingTables.getCloseButton_learningObjective().click();//Close the learning objective

                matchingTables.getButton_review().click();//Click on review button
                listPage.getButton_saveForLater().click();//Click on save for later button
                new Assignment().assignToStudent(dataIndex,appendChar);//Assign to class

                new Navigator().logout();//logout

                new Login().loginAsStudent(appendChar,dataIndex);//Login as student
                new Navigator().navigateTo("assignment");//Navigate to Assessments page

                assessments.getAssignment().click();//Select the assignment

                //>>>>>>TC row  no. - 133 : Instructor should be able to have a preview look by clicking on ' Preview button'
                //Expected - 2 :  Instructor should be able to view it as a student would view
                Assert.assertEquals(previewPage.getQuestion_text().getText(), questionTextPreviewPage, "Question is not appearing as in preview page on instructor side");
                Assert.assertEquals(assessmentReview.getList_editbox_answerChoice().get(0).getText(),answerChoice1PreviewPage,"Answer choice1 is not appearing as in preview page on instructor side");
                Assert.assertEquals(assessmentReview.getList_editbox_answerChoice().get(1).getText(),answerChoice2PreviewPage,"Answer choice2 is not appearing as in preview page on instructor side");
                Assert.assertEquals(assessmentReview.getList_editbox_answerChoice().get(2).getText(), answerChoice3PreviewPage, "Answer choice3 is not appearing as in preview page on instructor side");
                Assert.assertEquals(assessmentReview.getList_editbox_answerChoice().get(3).getText(), answerChoice4PreviewPage, "Answer choice4 is not appearing as in preview page on instructor side");
                Assert.assertEquals(assessmentReview.getList_editbox_answerChoice().get(4).getText(), answerChoice5PreviewPage, "Answer choice5 is not appearing as in preview page on instructor side");



                //>>>>>>TC row no. - 136 : Student's attempt view when the 'Allow multiple selection' check box is selected
                List<WebElement> checkboxesInTable = matchingTables.getList_checkboxOrRadioButtonToVerify();
                boolean checkboxFound = true;
                for (WebElement checkbox : checkboxesInTable) {
                    if (!checkbox.getAttribute("type").equals("checkbox")) {
                        checkboxFound = false;
                        break;
                    }
                }
                //Expected - 3 :  The checkboxes for the matching values must be displayed
                Assert.assertEquals(checkboxFound, true, " checkboxes are not displayed for matching value inputs");

                //Expected - 4 : Student should be able to select one or more checkboxes as answer
                matchingTables.getList_checkboxOrRadioButtonInTable().get(0).click();//Check 1st checkbox
                matchingTables.getList_checkboxOrRadioButtonInTable().get(1).click();//Check 2nd checkbox
                matchingTables.getList_checkboxOrRadioButtonInTable().get(3).click();//Check 4th checkbox

                //>>>>>>TC row no. - 140 : Student should be able to edit the answer
                //Expected - 1 : Student should be able to deselect an already selected answer
                matchingTables.getList_checkboxOrRadioButtonInTable().get(0).click();//Uncheck 1st checkbox
                matchingTables.getList_checkboxOrRadioButtonInTable().get(3).click();//Uncheck 4th checkbox

                //Student should be able to select one or more checkboxes as answer
                matchingTables.getList_checkboxOrRadioButtonInTable().get(3).click();//Check 4th checkbox
                assessments.getButton_submit().click();//Click on Submit button
                assessments.getFinal_submit().click();//Click on submit
                assessments.getButton_continue().click();//Click on continue button

                //Expected - The question must be submitted with the answers given by the student
                Assert.assertEquals(matchingTables.getListofanswers_radiobutton().get(0).getAttribute("class"),"matrix-box-preview matrix-ans-redit matrix-preview-unchecked-correct-answer","Question is not submitted with the answer given by student");
                Assert.assertEquals(matchingTables.getListofanswers_radiobutton().get(1).getAttribute("class"),"matrix-box-preview matrix-ans-redit correct-answer","Question is not submitted with the answer given by student");
                Assert.assertEquals(matchingTables.getListofanswers_radiobutton().get(2).getAttribute("class"),"matrix-box-preview matrix-ans-redit matrix-preview-unchecked-correct-answer","Question is not submitted with the answer given by student");
                Assert.assertEquals(matchingTables.getListofanswers_radiobutton().get(3).getAttribute("class"),"matrix-box-preview matrix-ans-redit wrong-answer","Question is not submitted with the answer given by student");

            }catch(Exception e){
                Assert.fail("Exception in testcase 'previewAndStudentAttemptMultipleSelect' in class ' MatchingTable '", e);
            }
        }

            @Test(priority = 7,enabled = true)
            public void studentAttemptRadioButton(){
                try{Assessments assessments = PageFactory.initElements(driver, Assessments.class);
                    CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
                    TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
                    QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
                    MatchingTablesQuestionCreation matchingTables = PageFactory.initElements(driver, MatchingTablesQuestionCreation.class);
                    PreviewPage previewPage = PageFactory.initElements(driver,PreviewPage.class);
                    AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
                    AssignmentReview assessmentReview = PageFactory.initElements(driver,AssignmentReview.class);

                    String appendChar = "a";
                    int dataIndex = 498;
                    new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
                    new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
                    new School().createWithOnlyName(appendChar, dataIndex);//create school
                    String classCode=new Classes().createClass(appendChar, dataIndex);//create class

                    new Navigator().logout();

                    new SignUp().studentDirectRegister(appendChar, classCode, 498);//create student
                    new Navigator().logout();//log out

                    new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
                    new Navigator().navigateTo("assignment");//Navigate to Assignment page
                    assessments.getButton_newAssessment().click();//Click on new assessment
                    createAssessment.getButton_create().click();//Click on create new assessment
                    tloListPage.getButton_create().click();//Click on create button
                    questionTypesPage.getIcon_MatchingTables().click();//Click on 'Matching Tables' question type
                    closeHelp();

                    String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
                    String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

                    createAssessment.getAssessment_editbox().clear();
                    createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name

                    matchingTables.getTextBox_QuestionField().clear();
                    matchingTables.getTextBox_QuestionField().sendKeys(questiontext);//Enter the question

                    matchingTables.getCheckBox_allowMultipleSelection().click();//Uncheck 'Allow multiple selection' checkbox

                    matchingTables.getList_editbox_answerChoice().get(0).click();//Click on 1st answer choice
                    matchingTables.getAnswerchoice_text().click();//Click on text type
                    driver.switchTo().activeElement().sendKeys("MatchingTables");//Enter the text

                    matchingTables.getList_editbox_answerChoice().get(1).click();//Click on 2nd answer choice
                    matchingTables.getAnswerchoice_text().click();//Click on text type
                    driver.switchTo().activeElement().sendKeys("2");//Enter the text

                    matchingTables.getList_editbox_answerChoice().get(2).click();//Click on 3rd answer choice
                    matchingTables.getAnswerchoice_text().click();//Click on text type
                    driver.switchTo().activeElement().sendKeys("3");//Enter the text

                    matchingTables.getList_editbox_answerChoice().get(3).click();//Click on 4th answer choice
                    matchingTables.getAnswerChoice_mathML().click();//Click on MAthML type
                    new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root", "9");

                    matchingTables.getList_editbox_answerChoice().get(4).click();//Click on 5th answer choice
                    matchingTables.getAnswerChoice_mathML().click();//Click on MAthML type
                    new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root", "4");
                    driver.findElement(By.xpath("//html/body")).click();

                    matchingTables.getList_checkboxOrRadioButtonInTable().get(1).click();//Select 2nd radio button
                    matchingTables.getList_checkboxOrRadioButtonInTable().get(2).click();//Select 3rd radio button

                    matchingTables.getButton_save().click();//Click on save button

                    matchingTables.getButton_review().click();//Click on Review button
                    listPage.getButton_saveForLater().click();//Click on Save for later button

                    new Assignment().assignToStudent(dataIndex,appendChar);//Assign to class

                    new Navigator().logout();//logout

                    new Login().loginAsStudent(appendChar,dataIndex);//Login as student
                    new Navigator().navigateTo("assignment");//Navigate to Assessments page

                    assessments.getAssignment().click();//Select the assignment

                    //>>>>>>TC row no. - 138 : Student's attempt view when the 'Allow multiple selection' check box is unselected
                    //Expected - 1 : The question body must be displayed
                    Assert.assertEquals(previewPage.getQuestion_text().getText(), questiontext, "Question is not appearing as in preview page on instructor side");

                    //Expected - 2 : The matching table must be displayed
                    Assert.assertEquals(assessmentReview.getList_editbox_answerChoice().get(0).getText(),"MatchingTables","Answer choice1 is not appearing as in preview page on instructor side");
                    Assert.assertEquals(assessmentReview.getList_editbox_answerChoice().get(1).getText(),"2","Answer choice2 is not appearing as in preview page on instructor side");
                    Assert.assertEquals(assessmentReview.getList_editbox_answerChoice().get(2).getText(), "3", "Answer choice3 is not appearing as in preview page on instructor side");

                    if(!assessmentReview.getList_editbox_answerChoice().get(3).getText().replaceAll("[\\t\\n\\r]", " ").contains("9"))
                    {
                        Assert.fail("Answer choice4 is not appearing as in preview page on instructor side");

                    }
                    if(!assessmentReview.getList_editbox_answerChoice().get(4).getText().replaceAll("[\\t\\n\\r]", " ").contains("4"))
                    {
                        Assert.fail("Answer choice5 is not appearing as in preview page on instructor side");

                    }

                    List<WebElement> checkboxesInTable = matchingTables.getList_checkboxOrRadioButtonToVerify();
                    boolean checkboxFound = true;
                    for (WebElement checkbox : checkboxesInTable) {
                        if (!checkbox.getAttribute("type").equals("radio")) {
                            checkboxFound = false;
                            break;
                        }
                    }
                    //Expected - 3 : The radio buttons for the matching values should be displayed.
                    Assert.assertEquals(checkboxFound, true, " radio buttons are not displayed for matching value inputs");

                    //Expected - 4 : Student should be able to select one radio button in a row as answer
                    matchingTables.getList_checkboxOrRadioButtonInTable().get(1).click();//Select 2nd radio button
                    matchingTables.getList_checkboxOrRadioButtonInTable().get(2).click();//Select 3rd radio button

                    //>>>>>>TC row no. - 140 : Student should be able to edit the answer
                    //Expected - 2 : In case of a radio button student should be able to select a different radio button as an answer
                    matchingTables.getList_checkboxOrRadioButtonInTable().get(1).click();//Select 1st radio button
                    matchingTables.getList_checkboxOrRadioButtonInTable().get(3).click();//Select 4th radio button

                    boolean scratchpadFound = false;
                    try{
                        driver.findElement(By.id("show-your-work-label"));//Search for scratchpad
                        scratchpadFound = true;
                    }
                    catch (Exception e){
                        //empty catch block
                    }
                    //Expected - Scratchpad should not appear if instructor has not checked the scratchpad checkbox
                    Assert.assertEquals(scratchpadFound,false,"Scratchpad is displayed even if instructor has not checked the checkbox");

                    assessments.getButton_submit().click();//Click on Submit button
                    assessments.getFinal_submit().click();//Click on submit
                    assessments.getButton_continue().click();//Click on continue button

                    //Expected - The question must be submitted with the answers given by the student
                    Assert.assertEquals(matchingTables.getListofanswers_radiobutton().get(0).getAttribute("class"),"matrix-box-preview matrix-ans-redit","Question is not submitted with the answer given by student");
                    Assert.assertEquals(matchingTables.getListofanswers_radiobutton().get(1).getAttribute("class"),"matrix-box-preview matrix-ans-redit correct-answer","Question is not submitted with the answer given by student");
                    Assert.assertEquals(matchingTables.getListofanswers_radiobutton().get(2).getAttribute("class"),"matrix-box-preview matrix-ans-redit matrix-preview-unchecked-correct-answer","Question is not submitted with the answer given by student");
                    Assert.assertEquals(matchingTables.getListofanswers_radiobutton().get(3).getAttribute("class"),"matrix-box-preview matrix-ans-redit wrong-answer","Question is not submitted with the answer given by student");



                }catch(Exception e){
                    Assert.fail("Exception in testcase 'studentAttemptRadioButton' in class ' MatchingTable '", e);
                }
            }


    @Test(priority = 8,enabled = true)
    //Student should be able to edit the answer
    public void editAnswer(){
        try{Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            MatchingTablesQuestionCreation matchingTables = PageFactory.initElements(driver, MatchingTablesQuestionCreation.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            TakeAssignment takeAssignment = PageFactory.initElements(driver,TakeAssignment.class);
            String appendChar = "a";
            int dataIndex = 1297;
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode=new Classes().createClass(appendChar, dataIndex);//create class

            new Navigator().logout();

            new SignUp().studentDirectRegister(appendChar, classCode, 1297);//create student
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getButton_create().click();//Click on create new assessment
            tloListPage.getButton_create().click();//Click on create button
            addMatchingTables(1297);//Create matching tables question with multiple selection enabled
            listPage.getButton_addMore().click();//Click on add more button
            tloListPage.getButton_create().click();//Click on create button
            addMatchingTables(1298);//Create matching tables question with multiple selection disabled
            listPage.getButton_saveForLater().click();//click on save for later button
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assessment

            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            new Navigator().navigateTo("assignment");//Navigate to assignment
            assessments.getAssignment().click();//Select assignment
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.id("show-your-work-label")));

            //TC row no. - 119 : Student should be able to use the writeboard if it is allowed for the question
            //Expected - 'Show your work' link should be displayed
            Assert.assertEquals(assessments.getShowOrHideYourWork().isDisplayed(),true,"'Show your work' link is not displayed");

            //The scratchpad should be displayed
            //Student must be able to use all the tools in the scratchpad
            String str = new RandomString().randomstring(5);

            new WriteBoard().enterTextInWriteBoard(str,dataIndex);

            assessments.getShowOrHideYourWork().click();//Click on Show your work

            boolean dataSaved =new WriteBoard().verifyWriteBoardDataIsSavedForNumberLine(str);
            Assert.assertEquals(dataSaved,true,"WriteBoard data is not saved");

            assessments.getShowOrHideYourWork().click();//Click on Show your work

            new WriteBoard().deleteWriteBoardData();
            boolean dataDeleted = new WriteBoard().verifyWriteBoardDataIsDeletedForNumberLine(str);
            Assert.assertEquals(dataDeleted,true,"WriteBoard data is not deleted");

            matchingTables.getList_checkboxOrRadioButtonInTable().get(0).click();//Select 1st checkbox
            matchingTables.getList_checkboxOrRadioButtonInTable().get(2).click();//Select 3rd checkbox

            takeAssignment.button_next.click();//Click on next button

            matchingTables.getList_checkboxOrRadioButtonInTable().get(0).click();//Select 1st radio button
            matchingTables.getList_checkboxOrRadioButtonInTable().get(2).click();//Select 3rd radio button

            takeAssignment.question_number.get(0).click();//Navigate to question no. 1

            boolean dataSaved1 =new WriteBoard().verifyWriteBoardDataIsSavedForNumberLine(str);

            //Writeboard should be opened and the content entered should be displayed
            Assert.assertEquals(dataSaved1,true,"WriteBoard data is not saved");

            matchingTables.getList_checkboxOrRadioButtonInTable().get(0).click();//Deselect 1st checkbox
            matchingTables.getList_checkboxOrRadioButtonInTable().get(2).click();//Deselect 3rd checkbox

            takeAssignment.question_number.get(1).click();//Navigate to question no. 2

            matchingTables.getList_checkboxOrRadioButtonInTable().get(1).click();//Select 2nd radio button
            matchingTables.getList_checkboxOrRadioButtonInTable().get(3).click();//Select 4th radio button

        }catch (Exception e){
            Assert.fail("Exception in testcase 'editAnswer' in class ' MatchingTable '", e);

        }
    }


    @Test(priority = 9,enabled = true)
    //student should be able to navigate between questions by clicking on 'Next' or the question numbers
    public void multipleQuestions(){
        try{Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            TakeAssignment takeAssignment = PageFactory.initElements(driver,TakeAssignment.class);
            PreviewPage previewPage = PageFactory.initElements(driver,PreviewPage.class);

            String appendChar = "a";
            int dataIndex = 1385;
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode=new Classes().createClass(appendChar, dataIndex);//create class

            new Navigator().logout();

            new SignUp().studentDirectRegister(appendChar, classCode, 1385);//create student
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getButton_create().click();//Click on create new assessment
            tloListPage.getButton_create().click();//Click on create button
            addMatchingTables(1385);//Create matching tables question
            listPage.getButton_addMore().click();//Click on add more button
            tloListPage.getButton_create().click();//Click on create button
            addMatchingTables(1386);//Create matching tables question
            listPage.getButton_addMore().click();//Click on add more button
            tloListPage.getButton_create().click();//Click on create button
            addMatchingTables(1387);//Create matching tables question
            listPage.getButton_addMore().click();//Click on add more button
            tloListPage.getButton_create().click();//Click on create button
            addMatchingTables(1388);//Create matching tables question
            listPage.getButton_addMore().click();//Click on add more button
            tloListPage.getButton_create().click();//Click on create button
            addMatchingTables(1389);//Create matching tables question
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assessment

            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);
            new Navigator().navigateTo("assignment");//Navigate to assignment
            assessments.getAssignment().click();//Select assignment
            Thread.sleep(4000);

            takeAssignment.button_next.click();//Click on next button

            //Expected - Clicking on next must display the next question
            Assert.assertEquals(previewPage.getQuestion_text().getText(),"MatchingTables Question2 multipleQuestions","2nd question is not displayed after clicking on next");

            takeAssignment.button_next.click();//Click on next button
            Assert.assertEquals(previewPage.getQuestion_text().getText(),"MatchingTables Question3 multipleQuestions","2nd question is not displayed after clicking on next");

            takeAssignment.button_next.click();//Click on next button
            Assert.assertEquals(previewPage.getQuestion_text().getText(),"MatchingTables Question4 multipleQuestions","2nd question is not displayed after clicking on next");

            takeAssignment.button_next.click();//Click on next button
            Assert.assertEquals(previewPage.getQuestion_text().getText(),"MatchingTables Question5 multipleQuestions","2nd question is not displayed after clicking on next");

            //navigate to the next question by using the Number navigation
            takeAssignment.question_number.get(0).click();//Navigate to 1st question
            Assert.assertEquals(previewPage.getQuestion_text().getText(),"MatchingTables Question1 multipleQuestions","Corresponding question is not displayed");

            takeAssignment.question_number.get(1).click();//Navigate to 1st question
            Assert.assertEquals(previewPage.getQuestion_text().getText(),"MatchingTables Question2 multipleQuestions","Corresponding question is not displayed");

            takeAssignment.question_number.get(2).click();//Navigate to 1st question
            Assert.assertEquals(previewPage.getQuestion_text().getText(),"MatchingTables Question3 multipleQuestions","Corresponding question is not displayed");

            takeAssignment.question_number.get(3).click();//Navigate to 1st question
            Assert.assertEquals(previewPage.getQuestion_text().getText(),"MatchingTables Question4 multipleQuestions","Corresponding question is not displayed");

            takeAssignment.question_number.get(4).click();//Navigate to 1st question
            Assert.assertEquals(previewPage.getQuestion_text().getText(),"MatchingTables Question5 multipleQuestions","Corresponding question is not displayed");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'multipleQuestions' in class ' MatchingTable '", e);

        }
    }


    @Test(priority = 10,enabled = true)
    public void evaluationMultipeSelection(){
        try{Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            MatchingTablesQuestionCreation matchingTables = PageFactory.initElements(driver, MatchingTablesQuestionCreation.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            TrueFalseQuestionCreation trueFalse = PageFactory.initElements(driver, TrueFalseQuestionCreation.class);
            AssessmentResponses assessmentResponses = PageFactory.initElements(driver,AssessmentResponses.class);

            String appendChar = "a";
            int dataIndex = 1468;
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode=new Classes().createClass(appendChar, dataIndex);//create class

            new Navigator().logout();

            new SignUp().studentDirectRegister(appendChar, classCode, 1468);//create student
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getButton_create().click();//Click on create new assessment
            tloListPage.getButton_create().click();//Click on create button

            questionTypesPage.getIcon_MatchingTables().click();//Click on MatchingTables question type

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name

            matchingTables.getTextBox_QuestionField().clear();
            matchingTables.getTextBox_QuestionField().sendKeys(questiontext);//Enter the question

            matchingTables.getList_editbox_answerChoice().get(0).click();//Click on 1st answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("MatchingTables");//Enter the text

            matchingTables.getList_editbox_answerChoice().get(1).click();//Click on 2nd answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("2");//Enter the text

            matchingTables.getList_editbox_answerChoice().get(2).click();//Click on 3rd answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("3");//Enter the text

            matchingTables.getList_editbox_answerChoice().get(3).click();//Click on 4th answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("4");//Enter the text

            matchingTables.getList_editbox_answerChoice().get(4).click();//Click on 5th answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("5");//Enter the text
            driver.findElement(By.xpath("//html/body")).click();

            matchingTables.getList_checkboxOrRadioButtonInTable().get(1).click();//Check 2nd checkbox
            matchingTables.getList_checkboxOrRadioButtonInTable().get(2).click();//Check 3rd checkbox

            trueFalse.getPoint_editor().click();//Click on point editor
            trueFalse.getPoint_textbox().click();//Click on point text box
            trueFalse.getPoint_textbox().clear();//Enter the point
            trueFalse.getPoint_textbox().sendKeys("3");//Enter the point

            matchingTables.getButton_save().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));

            matchingTables.getButton_review().click();//Click on Review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment

            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assessments.getAssignment().click();//Select assignment

            matchingTables.getList_checkboxOrRadioButtonInTable().get(1).click();//Check 2nd checkbox
            matchingTables.getList_checkboxOrRadioButtonInTable().get(3).click();//Check 4th checkbox

            assessments.getButton_submit().click();//Click on submit button
            assessments.getFinal_submit().click();//Click on submit
            assessments.getButton_continue().click();//Click on continue button

            //Expected - 1 : Student should get only 1 point for the correct answer
                       //2 : Student should not get any points for the incorrect choices
                       //3 : If the student does not select any checkbox that is defined as correct answer then no points should be given
                       //4 : Total points should be = (Sum of points)/ total number of correct checkboxes. (i.e 1 in this case)
            Assert.assertEquals(assessments.getQuestionpoint().getText(),"3","Question point is not displayed as 3");
            Assert.assertEquals(assessments.getScore().getAttribute("value"),"1","Score of the student is not displayed as 1");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'evaluationMultipeSelection' in class ' MatchingTable '", e);

        }
    }

    @Test(priority = 11,enabled = true)
    public void evaluationRadioButton(){
        try{Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            MatchingTablesQuestionCreation matchingTables = PageFactory.initElements(driver, MatchingTablesQuestionCreation.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            TrueFalseQuestionCreation trueFalse = PageFactory.initElements(driver, TrueFalseQuestionCreation.class);

            String appendChar = "a";
            int dataIndex = 1572;
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode=new Classes().createClass(appendChar, dataIndex);//create class

            new Navigator().logout();

            new SignUp().studentDirectRegister(appendChar, classCode, 1572);//create student
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getButton_create().click();//Click on create new assessment
            tloListPage.getButton_create().click();//Click on create button
            questionTypesPage.getIcon_MatchingTables().click();//Click on MatchingTables question type

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name

            matchingTables.getTextBox_QuestionField().clear();
            matchingTables.getTextBox_QuestionField().sendKeys(questiontext);//Enter the question

            matchingTables.getList_editbox_answerChoice().get(0).click();//Click on 1st answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("MatchingTables");//Enter the text

            matchingTables.getList_editbox_answerChoice().get(1).click();//Click on 2nd answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("2");//Enter the text

            matchingTables.getList_editbox_answerChoice().get(2).click();//Click on 3rd answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("3");//Enter the text

            matchingTables.getList_editbox_answerChoice().get(3).click();//Click on 4th answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("4");//Enter the text

            matchingTables.getList_editbox_answerChoice().get(4).click();//Click on 5th answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("5");//Enter the text
            driver.findElement(By.xpath("//html/body")).click();

            matchingTables.getCheckBox_allowMultipleSelection().click();//Deselect allow multiple selection checkbox

            matchingTables.getList_checkboxOrRadioButtonInTable().get(1).click();//Check 2nd radio button
            matchingTables.getList_checkboxOrRadioButtonInTable().get(2).click();//Check 3rd radio button

            trueFalse.getPoint_editor().click();//Click on point editor
            trueFalse.getPoint_textbox().click();//Click on point text box
            trueFalse.getPoint_textbox().clear();//Enter the point
            trueFalse.getPoint_textbox().sendKeys("3");//Enter the point

            matchingTables.getButton_save().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));

            matchingTables.getButton_review().click();//Click on Review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment

            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assessments.getAssignment().click();//Select assignment

            matchingTables.getList_checkboxOrRadioButtonInTable().get(1).click();//Check 2nd radio button
            matchingTables.getList_checkboxOrRadioButtonInTable().get(3).click();//Check 4th radio button

            assessments.getButton_submit().click();//Click on submit button
            assessments.getFinal_submit().click();//Click on submit
            assessments.getButton_continue().click();//Click on continue button

            //Expected - 1 : Student should get only 1 point for the correct answer
            //2 : Student should not get any points for the incorrect choices
            //3 : If the student does not select any checkbox that is defined as correct answer then no points should be given
            //4 : Total points should be = (Sum of points)/ total number of correct checkboxes. (i.e 1 in this case)
            Assert.assertEquals(assessments.getQuestionpoint().getText(),"3","Question point is not displayed as 3");
            Assert.assertEquals(assessments.getScore().getAttribute("value"),"1.5","Score of the student is not displayed as 1.5");

            //Expected - The matching tables must be displayed with all the right and wrong answers marked
            Assert.assertEquals(matchingTables.getListofanswers_radiobutton().get(0).getAttribute("class"),"matrix-box-preview matrix-ans-redit","Matching table is not displayed as attempted by student");
            Assert.assertEquals(matchingTables.getListofanswers_radiobutton().get(1).getAttribute("class"),"matrix-box-preview matrix-ans-redit correct-answer","Matching table is not displayed as attempted by studen");
            Assert.assertEquals(matchingTables.getListofanswers_radiobutton().get(2).getAttribute("class"),"matrix-box-preview matrix-ans-redit matrix-preview-unchecked-correct-answer","Matching table is not displayed as attempted by studen");
            Assert.assertEquals(matchingTables.getListofanswers_radiobutton().get(3).getAttribute("class"),"matrix-box-preview matrix-ans-redit wrong-answer","Matching table is not displayed as attempted by studen");


        }catch (Exception e){
            Assert.fail("Exception in testcase 'evaluationRadioButton' in class ' MatchingTable '", e);

        }
    }


    @Test(priority = 12,enabled = true)
    public void instructorEvaluationMultipeSelection(){
        try{Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            MatchingTablesQuestionCreation matchingTables = PageFactory.initElements(driver, MatchingTablesQuestionCreation.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            TrueFalseQuestionCreation trueFalse = PageFactory.initElements(driver, TrueFalseQuestionCreation.class);
            AssessmentResponses assessmentResponses = PageFactory.initElements(driver,AssessmentResponses.class);

            String appendChar = "a";
            int dataIndex = 1677;
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode=new Classes().createClass(appendChar, dataIndex);//create class

            new Navigator().logout();

            new SignUp().studentDirectRegister(appendChar, classCode, 1677);//create student
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getButton_create().click();//Click on create new assessment
            tloListPage.getButton_create().click();//Click on create button

            questionTypesPage.getIcon_MatchingTables().click();//Click on MatchingTables question type
            closeHelp();
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name

            matchingTables.getTextBox_QuestionField().clear();
            matchingTables.getTextBox_QuestionField().sendKeys(questiontext);//Enter the question

            matchingTables.getList_editbox_answerChoice().get(0).click();//Click on 1st answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("MatchingTables");//Enter the text

            matchingTables.getList_editbox_answerChoice().get(1).click();//Click on 2nd answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("2");//Enter the text

            matchingTables.getList_editbox_answerChoice().get(2).click();//Click on 3rd answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("3");//Enter the text

            matchingTables.getList_editbox_answerChoice().get(3).click();//Click on 4th answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("4");//Enter the text

            matchingTables.getList_editbox_answerChoice().get(4).click();//Click on 5th answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("5");//Enter the text
            driver.findElement(By.xpath("//html/body")).click();

            matchingTables.getList_checkboxOrRadioButtonInTable().get(1).click();//Check 2nd checkbox
            matchingTables.getList_checkboxOrRadioButtonInTable().get(2).click();//Check 3rd checkbox

            trueFalse.getPoint_editor().click();//Click on point editor
            trueFalse.getPoint_textbox().click();//Click on point text box
            trueFalse.getPoint_textbox().clear();//Enter the point
            trueFalse.getPoint_textbox().sendKeys("3");//Enter the point

            matchingTables.getButton_save().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));

            matchingTables.getButton_review().click();//Click on Review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment

            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assessments.getAssignment().click();//Select assignment

            matchingTables.getList_checkboxOrRadioButtonInTable().get(1).click();//Check 2nd checkbox
            matchingTables.getList_checkboxOrRadioButtonInTable().get(3).click();//Check 4th checkbox

            assessments.getButton_submit().click();//Click on submit button
            assessments.getFinal_submit().click();//Click on submit

            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment
            assessments.getView_grades().click();//Click on view response
            assessmentResponses.getGradeBookContentColumn().click();//Click on question present in grid

            //Expected - 1 : If the student has selected a checkbox that is marked correct by the instructor, then it should be marked in correct
            Assert.assertEquals(matchingTables.getListofanswers_radiobutton().get(1).getAttribute("class"),"matrix-box-preview matrix-ans-redit correct-answer","1st checkbox is not marked as correct");

            //Expected - 2 :  If a student checks on boxes that are NOT marked as correct by instructor, the student response should be marked as incorrect
            Assert.assertEquals(matchingTables.getListofanswers_radiobutton().get(3).getAttribute("class"),"matrix-box-preview matrix-ans-redit wrong-answer","4th checkbox is not marked as incorrect");

            //Expected - Total number of points scored should be = (Sum of points)/number of rows (i.e 1 in this case)
            //Expected - Grades should be displayed for the question (if gradable)
            Assert.assertEquals(assessments.getScore().getAttribute("value"),"1","Grade is not displayed correctly for the question");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'instructorEvaluationMultipeSelection' in class ' MatchingTable '", e);

        }
    }


    @Test(priority = 13,enabled = true)
    public void instructorEvaluationRadioButton(){
        try{Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            MatchingTablesQuestionCreation matchingTables = PageFactory.initElements(driver, MatchingTablesQuestionCreation.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);
            TrueFalseQuestionCreation trueFalse = PageFactory.initElements(driver, TrueFalseQuestionCreation.class);
            AssessmentResponses assessmentResponses = PageFactory.initElements(driver,AssessmentResponses.class);

            String appendChar = "a";
            int dataIndex = 1789;
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode=new Classes().createClass(appendChar, dataIndex);//create class

            new Navigator().logout();

            new SignUp().studentDirectRegister(appendChar, classCode, 1789);//create student
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getButton_create().click();//Click on create new assessment
            tloListPage.getButton_create().click();//Click on create button
            questionTypesPage.getIcon_MatchingTables().click();//Click on MatchingTables question type

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name

            matchingTables.getTextBox_QuestionField().clear();
            matchingTables.getTextBox_QuestionField().sendKeys(questiontext);//Enter the question

            matchingTables.getList_editbox_answerChoice().get(0).click();//Click on 1st answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("MatchingTables");//Enter the text

            matchingTables.getList_editbox_answerChoice().get(1).click();//Click on 2nd answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("2");//Enter the text

            matchingTables.getList_editbox_answerChoice().get(2).click();//Click on 3rd answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("3");//Enter the text

            matchingTables.getList_editbox_answerChoice().get(3).click();//Click on 4th answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("4");//Enter the text

            matchingTables.getList_editbox_answerChoice().get(4).click();//Click on 5th answer choice
            matchingTables.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("5");//Enter the text
            driver.findElement(By.xpath("//html/body")).click();

            matchingTables.getCheckBox_allowMultipleSelection().click();//Deselect allow multiple selection checkbox

            matchingTables.getList_checkboxOrRadioButtonInTable().get(1).click();//Check 2nd radio button
            matchingTables.getList_checkboxOrRadioButtonInTable().get(2).click();//Check 3rd radio button

            trueFalse.getPoint_editor().click();//Click on point editor
            trueFalse.getPoint_textbox().click();//Click on point text box
            trueFalse.getPoint_textbox().clear();//Enter the point
            trueFalse.getPoint_textbox().sendKeys("3");//Enter the point

            matchingTables.getButton_save().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));

            matchingTables.getButton_review().click();//Click on Review button
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment

            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assessments.getAssignment().click();//Select assignment

            matchingTables.getList_checkboxOrRadioButtonInTable().get(1).click();//Check 2nd radio button
            matchingTables.getList_checkboxOrRadioButtonInTable().get(3).click();//Check 4th radio button

            assessments.getButton_submit().click();//Click on submit button
            assessments.getFinal_submit().click();//Click on submit

            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment
            assessments.getView_grades().click();//Click on view response
            assessmentResponses.getGradeBookContentColumn().click();//Click on question present in grid

            //Expected - 1 : If the student has selected a checkbox that is marked correct by the instructor, then it should be marked in correct
            Assert.assertEquals(matchingTables.getListofanswers_radiobutton().get(1).getAttribute("class"),"matrix-box-preview matrix-ans-redit correct-answer","1st radio button is not marked as correct");

            //Expected - 2 :  If a student checks on boxes that are NOT marked as correct by instructor, the student response should be marked as incorrect
            Assert.assertEquals(matchingTables.getListofanswers_radiobutton().get(3).getAttribute("class"),"matrix-box-preview matrix-ans-redit wrong-answer","4th radio button is not marked as incorrect");

            //Expected - Total number of points scored should be = (Sum of points)/number of rows (i.e 1 in this case)
            //Expected - Grades should be displayed for the question (if gradable)
            Assert.assertEquals(assessments.getScore().getAttribute("value"),"1.5","Grade is not displayed correctly for the question");


        }catch (Exception e){
            Assert.fail("Exception in testcase 'instructorEvaluationRadioButton' in class ' MatchingTable '", e);

        }
    }


    @Test(priority = 14, enabled = true)
    public void GradesExplicitlyByTeacher(){
        try{Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            TakeAssignment takeAssignment = PageFactory.initElements(driver,TakeAssignment.class);
            AssessmentResponses assessmentResponses = PageFactory.initElements(driver,AssessmentResponses.class);
            Performance performance = PageFactory.initElements(driver,Performance.class);
            MatchingTablesQuestionCreation matchingTables = PageFactory.initElements(driver,MatchingTablesQuestionCreation.class);

            String appendChar = "a";

            int dataIndex = 1907;
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode=new Classes().createClass(appendChar, dataIndex);//create class

            new Navigator().logout();

            new SignUp().studentDirectRegister(appendChar, classCode, 1907);//create student
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getButton_create().click();//Click on create new assessment
            tloListPage.getButton_create().click();//Click on create button
            addMatchingTables(1907);//Create matching tables question
            listPage.getButton_addMore().click();//Click on add more button
            tloListPage.getButton_create().click();//Click on create button
            addMatchingTables(1908);//Create matching tables question
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assessment

            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getAssignment().click();//select assignment

            matchingTables.getList_checkboxOrRadioButtonInTable().get(1).click();//Check 2nd checkbox
            matchingTables.getList_checkboxOrRadioButtonInTable().get(3).click();//Check 4th checkbox

            takeAssignment.button_next.click();//Click on next button
            Thread.sleep(2000);

            matchingTables.getList_checkboxOrRadioButtonInTable().get(1).click();//Check 2nd checkbox
            matchingTables.getList_checkboxOrRadioButtonInTable().get(2).click();//Check 3rd checkbox
            assessments.getButton_submit().click();//Click on submit button
            assessments.getFinal_submit().click();//Click on submit

            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getView_grades().click();//Click on view response
            assessments.getGrade_gradable().click();//Click on 1st question score assigned to student
            Thread.sleep(2000);
            performance.getTextArea_EnterFeedback().click();//Click on feedback edit box
            performance.getTextArea_EnterFeedback().clear();
            driver.switchTo().activeElement().sendKeys("feedback for question 1");
            Thread.sleep(2000);
            performance.getButton_next().click();//Navigate to 2nd question
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("view-user-question-performance-feedback-box")));
            performance.getTextArea_EnterFeedback().click();//Click on feedback edit box
            performance.getTextArea_EnterFeedback().clear();
            driver.switchTo().activeElement().sendKeys("feedback for question 2");
            performance.getButton_Save().click();//Click on save button
            performance.getBackarrow_assessmentResponse().click();//Navigate to assessment response page
            assessmentResponses.getButton_ReleaseGradeForAll().click();//Click on release grade for all

            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);
            new Navigator().navigateTo("assignment");//Navigate to assignment
            assessments.getAssignment().click();//Open assessment
            assessments.getButton_continue().click();//Click on continue button

            //Expected - Feedback entered by the Instructor should be displayed for the question
            //Expected -  Grades should be displayed
            Assert.assertEquals(assessments.getFeedback_content().getText(),"feedback for question 1","Feedback is not displayed as given by instructor");
            Assert.assertEquals(assessments.getScore().getAttribute("value"),"0.33","grade is not displayed correctly for 1st question");

            assessments.getButton_next().click();//Click on next button

            Assert.assertEquals(assessments.getFeedback_content().getText(),"feedback for question 2","Feedback is not displayed as given by instructor");
            Assert.assertEquals(assessments.getScore().getAttribute("value"),"1","grade is not displayed correctly for 2nd question");

        }catch (Exception e){
        Assert.fail("Exception in testcase 'feedbackByInstructor' in class ' MatchingTable '", e);

    }

    }


    @Test(priority = 15, enabled = true)
    public void GradesOnAssignmentSubmission(){
        try{Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            TakeAssignment takeAssignment = PageFactory.initElements(driver,TakeAssignment.class);
            AssessmentResponses assessmentResponses = PageFactory.initElements(driver,AssessmentResponses.class);
            Performance performance = PageFactory.initElements(driver,Performance.class);
            MatchingTablesQuestionCreation matchingTables = PageFactory.initElements(driver,MatchingTablesQuestionCreation.class);

            String appendChar = "a";
            int dataIndex = 2005;
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode=new Classes().createClass(appendChar, dataIndex);//create class

            new Navigator().logout();

            new SignUp().studentDirectRegister(appendChar, classCode, 2005);//create student
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getButton_create().click();//Click on create new assessment
            tloListPage.getButton_create().click();//Click on create button
            addMatchingTables(2005);//Create matching tables question
            listPage.getButton_addMore().click();//Click on add more button
            tloListPage.getButton_create().click();//Click on create button
            addMatchingTables(2006);//Create matching tables question
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assessment

            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getAssignment().click();//select assignment

            matchingTables.getList_checkboxOrRadioButtonInTable().get(1).click();//Check 2nd checkbox
            matchingTables.getList_checkboxOrRadioButtonInTable().get(3).click();//Check 4th checkbox

            takeAssignment.button_next.click();//Click on next button
            Thread.sleep(2000);

            matchingTables.getList_checkboxOrRadioButtonInTable().get(1).click();//Check 2nd checkbox
            matchingTables.getList_checkboxOrRadioButtonInTable().get(2).click();//Check 3rd checkbox

            assessments.getButton_submit().click();//Click on submit button
            assessments.getFinal_submit().click();//Click on submit
            assessments.getButton_continue().click();//Click on continue button

            new ExplicitWait().explicitWaitByClass("question-performance-score",20);

            //Expected - Grades should be displayed for the questions
            Assert.assertEquals(assessments.getScore().getAttribute("value"),"0.33","Grade is not displayed correctly for 1st question");

            assessments.getButton_next().click();//Click on next button

            Assert.assertEquals(assessments.getScore().getAttribute("value"),"1","Grade is not displayed correctly for 2nd question");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'feedbackByInstructor' in class ' MatchingTable '", e);

        }
    }


    @Test(priority = 16, enabled = true)
    public void feedbackByInstructorNonGradable(){
        try{Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            TakeAssignment takeAssignment = PageFactory.initElements(driver,TakeAssignment.class);
            AssessmentResponses assessmentResponses = PageFactory.initElements(driver,AssessmentResponses.class);
            Performance performance = PageFactory.initElements(driver,Performance.class);
            MatchingTablesQuestionCreation matchingTables = PageFactory.initElements(driver,MatchingTablesQuestionCreation.class);

            String appendChar = "a";

            int dataIndex = 2074;
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode=new Classes().createClass(appendChar, dataIndex);//create class

            new Navigator().logout();

            new SignUp().studentDirectRegister(appendChar, classCode, 2074);//create student
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getButton_create().click();//Click on create new assessment
            tloListPage.getButton_create().click();//Click on create button
            addMatchingTables(2074);//Create matching tables question
            listPage.getButton_addMore().click();//Click on add more button
            tloListPage.getButton_create().click();//Click on create button
            addMatchingTables(2075);//Create matching tables question
            listPage.getButton_saveForLater().click();//Click on save for later button
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assessment

            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getAssignment().click();//select assignment

            matchingTables.getList_checkboxOrRadioButtonInTable().get(1).click();//Check 2nd checkbox
            matchingTables.getList_checkboxOrRadioButtonInTable().get(3).click();//Check 4th checkbox

            takeAssignment.button_next.click();//Click on next button
            Thread.sleep(2000);

            matchingTables.getList_checkboxOrRadioButtonInTable().get(1).click();//Check 2nd checkbox
            matchingTables.getList_checkboxOrRadioButtonInTable().get(2).click();//Check 3rd checkbox
            assessments.getButton_submit().click();//Click on submit button
            assessments.getFinal_submit().click();//Click on submit

            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getView_grades().click();//Click on view response
            assessments.getGrade_gradable().click();//Click on 1st question score assigned to student
            Thread.sleep(2000);
            performance.getTextArea_EnterFeedback().click();//Click on feedback edit box
            performance.getTextArea_EnterFeedback().clear();
            driver.switchTo().activeElement().sendKeys("feedback for question 1");
            performance.getButton_next().click();//Navigate to 2nd question
            Thread.sleep(2000);
            performance.getTextArea_EnterFeedback().click();//Click on feedback edit box
            performance.getTextArea_EnterFeedback().clear();
            driver.switchTo().activeElement().sendKeys("feedback for question 2");
            performance.getButton_Save().click();//Click on save button
            performance.getBackarrow_assessmentResponse().click();//Navigate to assessment response page
            assessmentResponses.getButton_ReleaseFeedbackForAll().click();//Click on release feedback for all

            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);//Loin as a student
            new Navigator().navigateTo("assignment");//Navigate to assignment
            assessments.getAssignment().click();//Open assessment
            assessments.getButton_continue().click();//Click on continue button

            //Expected - Feedback entered by the Instructor should be displayed for the question
            Assert.assertEquals(assessments.getFeedback_content().getText(),"feedback for question 1","Feedback is not displayed as given by instructor");

            assessments.getButton_next().click();//Click on next button

            Assert.assertEquals(assessments.getFeedback_content().getText(),"feedback for question 2","Feedback is not displayed as given by instructor");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'feedbackByInstructor' in class ' MatchingTable '", e);

        }

    }

    public void addMatchingTables(int dataIndex)
    {
        try
        {
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String multipleSelection = ReadTestData.readDataByTagName("", "multipleSelection", Integer.toString(dataIndex));
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            new Click().clickByid("qtn-matching-tables-type");//click on Matching Tables type question
            closeHelp();

            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys(assessmentname);//give the assignment name

            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys(questiontext);//type the question
            List<WebElement> matrixCells = driver.findElements(By.cssSelector("div[class='matrix-text-area matching-text']"));
            matrixCells.get(0).click();
            new Click().clickbylistid("answer_choice_edit", 1);
            driver.switchTo().activeElement().sendKeys("1st column");
            driver.findElement(By.xpath("//html/body")).click();

            matrixCells.get(1).click();
            new Click().clickbylistid("answer_choice_edit", 1);
            driver.switchTo().activeElement().sendKeys("2nd column");

            matrixCells.get(2).click();
            new Click().clickbylistid("answer_choice_edit", 1);
            driver.switchTo().activeElement().sendKeys("3rd column");

            matrixCells.get(3).click();
            new Click().clickbylistid("answer_choice_edit", 1);
            driver.switchTo().activeElement().sendKeys("1st row");

            matrixCells.get(4).click();
            new Click().clickbylistid("answer_choice_edit", 1);
            driver.switchTo().activeElement().sendKeys("2nd row");

            new Click().clickBycssselector("label[for='matrix-box-checkbox-btn-2']");//click on 2nd checkbox
            new Click().clickBycssselector("label[for='matrix-box-checkbox-btn-3']");//click on 3rd checkbox

            new Click().clickBycssselector("label[class='writeboardchkbox as-writeboard-checkbox-unchecked']");//Select the scratchpad checkbox

            if(multipleSelection.equals("false")){
                new Click().clickByXpath("//input[@id='multiple-selection-check-btn']/following-sibling::label");//Deselect multiple selection checkbox
                driver.findElements(By.xpath("//div[@class='matrix-box-select-wrapper']/input/following-sibling::label")).get(1).click();//Click on 2nd radio button
                driver.findElements(By.xpath("//div[@class='matrix-box-select-wrapper']/input/following-sibling::label")).get(2).click();//Click on 3rd radio button
            }

            new Click().clickByid("saveQuestionDetails1");//click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));
            new Click().clickBycssselector("div[class='lsm-createAssignment-done selected']");//Click on review button
        }
        catch (Exception e) {
            Assert.fail("Exception while creating matching Tables question type",e);
        }
    }



}
