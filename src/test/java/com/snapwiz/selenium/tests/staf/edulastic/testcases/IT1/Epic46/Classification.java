package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.Epic46;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.preview.PreviewPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.DragAndDrop;
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
 * Created by pragya on 13-01-2015.
 */
public class Classification extends Driver{

    @Test(priority = 1,enabled = true)
    //TC row no. - 11 : The default landing page of the instructor when he clicks 'Classification type question'
    public void  classificationUI(){
        try{Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment =PageFactory.initElements(driver,CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver,TLOListPage.class);
            QuestionTypesPage questionTypesPage =PageFactory.initElements(driver,QuestionTypesPage.class);
            ClassificationQuestionCreation classification= PageFactory.initElements(driver,ClassificationQuestionCreation.class);
            String appendChar = "a";
            int dataIndex = 24;
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getButton_create().click();//Click on create new assessment
            tloListPage.getButton_create().click();//Click on create button

            questionTypesPage.getIcon_Classification().click();//Click on Classification question type

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name

            //Expected - 1 : Question title should be displayed as “Classification question”
            Assert.assertEquals(classification.getQuestionTitle().getText(), "Classification question", "Question title is not shown as expected");

            //Expected - 3 : Text field to enter question body  with 'Enter question text' as default content
            Assert.assertEquals(classification.getTextBox_QuestionField().getText(),"Enter Question Text","By default 'Enter question text' is not displayed in the question body");

            classification.getTextBox_QuestionField().clear();
            classification.getTextBox_QuestionField().sendKeys(questiontext);//Enter the question
            driver.findElement(By.xpath("//html/body")).click();

            //Expected - 2 : 'Redactor' must be enabled for the question text field
            Assert.assertEquals(classification.getTextBox_QuestionField().getText(), questiontext, "Redactor is not enabled");

            //Expected - 4.a : 2 text fields by default to enter 2 class names
            if(classification.getList_classNames().size()!=1)
            {
                Assert.fail("By default 1 text field is not displayed to enter a class name");
            }
            //Expected - 4.b : '+Add a new Class' link to add more classes
            Assert.assertEquals(classification.getLink_addNewClass().isDisplayed(),true,"'Add a new class' link is not present");

            //Expected - 5.a : 4 text fields under that by default to enter the drop values
            if(classification.getList_enterAnswerChoice().size()!=4)
            {
                Assert.fail("By default 4 text fields ar not displayed to enter the drop values");
            }

            //Expected - 5.b :  '+Add a new answer choice' link should be present
            Assert.assertEquals(classification.getLink_addNewAnswerChoice().isDisplayed(),true,"'Add new answer choice is not displayed'");

            //Expected - 6 : 'Shuffle answer choices'  checkbox should be unchecked
            Assert.assertEquals(classification.getCheckbox_shuffleAnswerChoices().isSelected(),false,"Shuffle answer choices checkbox is checked");

            //Expected - 7 : 'Allow students to use writeboard' checkbox should be unchecked
            Assert.assertEquals(classification.getCheckbox_scratchpad().isSelected(),false,"'Allow student to use Scratchpad' checkbox is checked");

            //Expected - 8 : Solution text field should be displayed
            Assert.assertEquals(classification.getTextbox_solution().isDisplayed(),true,"Solution text box is not displayed");

            //Expected - 9 : Hint text field should be displayed
            Assert.assertEquals(classification.getTextbox_hint().isDisplayed(),true,"Hint text box is not displayed");

            //>>>>>>TC row no. - 13 : Instructor should be able to upload an image on the canvas as background (optional)

            //Expected - 1 : "Upload a background image (optional)" link must be present
            Assert.assertEquals(classification.getLink_uploadABackgroundImage().isDisplayed(),true,"'Upload a background image (optional)' is not displayed");

            //TC row no. - 14 : On clicking the "Upload a background image (optional)" link
            classification.getLink_uploadABackgroundImage().click();//Click on Upload a background image link

            //Expected - 1 : Image upload pop up must be displayed
            Assert.assertEquals(classification.getPopUp_upload().isDisplayed(),true,"Image upload pop is not displayed after clicking on Upload a background image");

            //Expected - 2 : Selected image must be uploaded
            new QuestionCreate().fileUploadInQuestionCreation("24");
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'ui-resizable')]")));

            if(!classification.getBackgroundImage_uploaded().getAttribute("style").contains("img"))
            {
                Assert.fail("Selected image is not uploaded");
            }

            //>>>>>>TC row no. - 16 : Deleting class, by clicking 'Delete' icon across the created class
            classification.getList_classNames().get(0).click();
            driver.switchTo().activeElement().sendKeys("class 1");//Enter the 1st class
            classification.getLink_addNewClass().click();//Click on add new class

            classification.getList_classNames().get(1).click();
            driver.switchTo().activeElement().sendKeys("class 2");//Enter 2nd class
            driver.findElement(By.xpath("//html/body")).click();

            List<WebElement> listOfClass=classification.getList_classArea();

            //Expected - 2 : Only move option should come, when the 2 classes are present
            //Delete option should not appear
            boolean deleteBefore = false;
            for(int i=0;i<listOfClass.size();i++)
            {
                if (classification.getList_deleteClass().get(i).isDisplayed() == true) {
                    deleteBefore = true;
                    break;
                }
            }
            Assert.assertEquals(deleteBefore,false,"Delete button is displayed when the 2 classes are present");

            //Drag button should appear
            boolean drag = true;
            for(int i=0;i<listOfClass.size();i++)
            {
                classification.getList_classArea().get(i).click();
                if(!classification.getDragClass().getAttribute("innerHTML").contains("drag.png"))
                {
                    drag=false;
                    break;
                }
            }
            Assert.assertEquals(drag, true, "Move button is not displayed when the 2 classes are present");

            //Expected - 1 : Delete and move option should appear when more than 2 classes are added
            classification.getLink_addNewClass().click();//Click on Add new class link
            classification.getList_classNames().get(2).click();
            driver.switchTo().activeElement().sendKeys("class 3");//Enter 3rd class
            driver.findElement(By.xpath("//html/body")).click();

            //Expected : Instructor must be able to create a new class by entering the text in the text input field provided
            Assert.assertEquals(classification.getList_classNames().get(2).getText(),"class 3","Instructor is not able to create a new class");

            //Delete option should appear
            boolean deleteAfter = true;
            for(int i=0;i<listOfClass.size();i++)
            {
                 classification.getList_classArea().get(i).click();
                if (classification.getList_deleteClass().get(i).isDisplayed() != true) {
                    deleteAfter = false;
                    break;
                }
            }
            Assert.assertEquals(deleteAfter, true,"Delete button is not displayed when the classes are more than 2");

            //Drag option should appear
            for(int i=0;i<listOfClass.size();i++)
            {
                classification.getList_classArea().get(i).click();
                if(!classification.getDragClass().getAttribute("innerHTML").contains("drag.png"))
                {
                    drag=false;
                    break;
                }
            }
            Assert.assertEquals(drag,true,"Move button is not displayed when classes are more than 2");

            classification.getList_classArea().get(0).click();//Click on 1st class
            classification.getList_deleteClass().get(0).click();//Delete the class

            //Expected - 3 : When user add a new class, delete button should appear and on removing one of the classes, delete button should disappear
            //Delete option should not appear
            boolean afterDelete = false;
            for(int i=0;i<listOfClass.size();i++)
            {
                if(classification.getList_deleteClass().get(i).isDisplayed() == true) {
                    afterDelete = true;
                    break;
                }
            }
            Assert.assertEquals(afterDelete,false,"Delete button is displayed when the 2 classes are present");

        }catch (Exception e) {
            Assert.fail("Exception in testcase 'ClassificationUI' in class 'Classification'", e);
        }
    }


    @Test(priority = 2,enabled = true)
    //TC row no. - 21 : Adding a new answer choice
    public void classificationAnswerChoiceDragAndDrop(){
        try{Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment =PageFactory.initElements(driver,CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver,TLOListPage.class);
            QuestionTypesPage questionTypesPage =PageFactory.initElements(driver,QuestionTypesPage.class);
            ClassificationQuestionCreation classification= PageFactory.initElements(driver,ClassificationQuestionCreation.class);
            String appendChar = "a";
            int dataIndex = 212;
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getButton_create().click();//Click on create new assessment
            tloListPage.getButton_create().click();//Click on create button

            questionTypesPage.getIcon_Classification().click();//Click on Classification question type

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name

            classification.getTextBox_QuestionField().clear();
            classification.getTextBox_QuestionField().sendKeys(questiontext);//Enter the question
            driver.findElement(By.xpath("//html/body")).click();

            classification.getList_classNames().get(0).click();
            driver.switchTo().activeElement().sendKeys("class 1");//Enter the 1st class

            classification.getList_enterAnswerChoice().get(0).click();
            classification.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("Answer 1");//Enter 1st answer choice
            classification.getList_enterAnswerChoice().get(0).click();
            classification.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("Answer 2");//Enter 2nd answer choice
            classification.getList_enterAnswerChoice().get(1).click();
            classification.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("Answer 3");//Enter 3rd answer choice
            classification.getList_enterAnswerChoice().get(2).click();
            classification.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("Answer 4");//Enter 4th answer choice

            classification.getLink_addNewAnswerChoice().click();//Click on Add new answer choice
            classification.getList_enterAnswerChoice().get(4).click();
            classification.getAnswerchoice_text().click();
            driver.switchTo().activeElement().sendKeys("Answer 5");//Enter 5th answer choice
            driver.findElement(By.xpath("//html/body")).click();


            //Expected - 1 : Instructor must be able to add a new answer choice by clicking on '+Add a new answer choice'
            Assert.assertEquals(classification.getList_enterAnswerChoice().get(4).getText(),"Answer 5","Instructor is not able to add new answer choice");

           //>>>>>>TC row no. - 28 : Instructor creating drop values as classes

           //Expected - 3 : Drop area must be transparent
            if(classification.getDrop_class().getText().length() != 0)
            {
                Assert.fail("Drop area is not transparent");
            }
            classification.getList_classArea().get(0).click();//Click on 1st class
            new DragAndDrop().dragAndDrop(classification.getDragClass(),classification.getDrop_class());
            driver.findElement(By.xpath("//html/body")).click();

            //Expected - 1 : Instructor should be able to drag the Class  value and drop it on to anywhere on the canvas
            Assert.assertEquals(classification.getDropped_classHeader().getText(),"class 1","Instructor is not able to drop the class on canvas");

            //>>>>>>TC row no. - 22 : Defining correct answer choice
            classification.getList_enterAnswerChoice().get(4).click();//Click on 5th answer choice

            //Expected - 1 : Clicking on the created answer choice should display a 'Delete' icon and 'Move' icon to drag the answer choice
            Assert.assertEquals(classification.getDrag_answerChoice().isDisplayed(),true,"Move option is not displayed");
            Assert.assertEquals(classification.getDelete_answerChoice().isDisplayed(),true,"Delete option is not displayed");

            classification.getList_enterAnswerChoice().get(0).click();//Click on 1st answer choice
            classification.getList_drag_answerChoice().get(0).click();
            Thread.sleep(2000);
            new DragAndDrop().dragAndDrop(classification.getDrag_answerChoice(),classification.getListDropped_classArea().get(0));//Drop the 1st answer choice to dropped class area

            //Expected - 2 : Clicking on 'Move' should enable dragging
            //Expected - 3 :  Dragging and dropping the answer choice into the 'Drop area' Field should assign the answer choice to the class that was dropped in
            Assert.assertEquals(classification.getListDropped_answerChoice().get(0).getText(),"Answer 1","Answer choice is not dropped to class");

            classification.getDelete_droppedAnswerChoice().click();//Delete the dropped answer choice

            boolean classFound = false;
            try{
                classification.getListDropped_answerChoice().get(0);
                classFound = true;
            }
            catch (Exception e){
                //empty catch block
            }

            //Expected - 4 : Instructor should be able to delete the answer choices from the class
            Assert.assertEquals(classFound,false,"Instructor is not able to delete the answer choice from class");

            new DragAndDrop().dragAndDrop(classification.getList_drag_answerChoice().get(0), classification.getListDropped_classArea().get(0));//Drop the 1st answer choice to dropped class area
            classification.getButton_save().click();//Click on save button
            Thread.sleep(4000);

            //Commented as per existing functionality
            /*//Expected - 5 : All the answer choices must be assigned to at least one of the class
            Assert.assertEquals(classification.getMessage_text().getText(),"Please assign all answer choices to the classification container","Error is not coming even if all the answer choices are not assigned to at least one of the class");
*/
            classification.getList_enterAnswerChoice().get(1).click();//Click on 2nd answer choice
            new DragAndDrop().dragAndDrop(classification.getList_drag_answerChoice().get(1), classification.getListDropped_classArea().get(0));//Drop the 2nd answer choice to dropped class area
            driver.findElement(By.xpath("//html/body")).click();
            classification.getList_enterAnswerChoice().get(2).click();//Click on 3rd answer choice
            new DragAndDrop().dragAndDrop(classification.getList_drag_answerChoice().get(2),classification.getListDropped_classArea().get(0));//Drop the 3rd answer choice to dropped class area
            classification.getList_enterAnswerChoice().get(3).click();//Click on 4th answer choice
            new DragAndDrop().dragAndDrop(classification.getList_drag_answerChoice().get(3),classification.getListDropped_classArea().get(0));//Drop the 4th answer choice to dropped class area
            classification.getList_enterAnswerChoice().get(4).click();//Click on 5th answer choice
            new DragAndDrop().dragAndDrop(classification.getList_drag_answerChoice().get(4),classification.getListDropped_classArea().get(0));//Drop the 5th answer choice to dropped class area

            List<WebElement> answers = classification.getList_answerChoiceEditBox();
            boolean disable = true;
            for(WebElement ans:answers)
            {
                if(!ans.getAttribute("style").contains("background: none")) {
                    disable = false;
                    break;
                }
            }
            //Expected - 6 : On dropping an answer choice in to any class, it must be greyed out from the answer choices panel
            Assert.assertEquals(disable,true, "Answer choice is not grayed out after dropping onto class");

            //Expected - 7 : On dropping the answer choices into the drop area of the class, table must be resized to accommodate more values
            Assert.assertEquals(classification.getListDropped_answerChoice().get(1).getText(),"Answer 2","Answer choice is not dropped to class");
            Assert.assertEquals(classification.getListDropped_answerChoice().get(2).getText(),"Answer 3","Answer choice is not dropped to class");
            Assert.assertEquals(classification.getListDropped_answerChoice().get(3).getText(),"Answer 4","Answer choice is not dropped to class");
            Assert.assertEquals(classification.getListDropped_answerChoice().get(4).getText(),"Answer 5","Answer choice is not dropped to class");

            //>>>>>>TC row no. - 31 : Creating a new class by clicking on 'Add a new class' link
            classification.getLink_addNewClass().click();//Click on 'Add new class' link

            //Expected - 1 : A new class must be created with the default prompt as 'Enter class'
            Assert.assertEquals(classification.getList_classNames().get(1).getText(),"Enter Class Name","A new class is not created with the default prompt as 'Enter Class Name'" );

            classification.getList_classNames().get(1).click();
            driver.switchTo().activeElement().sendKeys("class 2");//Enter 2nd class

            classification.getLink_addNewClass().click();//Click on 'Add new class' link
            classification.getList_classNames().get(2).click();
            driver.switchTo().activeElement().sendKeys("class 3");//Enter 3rd class

            classification.getLink_addNewClass().click();//Click on 'Add new class' link
            classification.getList_classNames().get(3).click();
            driver.switchTo().activeElement().sendKeys("class 4");//Enter 4th class

            classification.getLink_addNewClass().click();//Click on 'Add new class' link
            classification.getList_classNames().get(4).click();
            driver.switchTo().activeElement().sendKeys("class 5");//Enter 5th class
            driver.findElement(By.xpath("//html/body")).click();

            //Expected - On click a text field must be displayed for the Instructor to enter the text
            Assert.assertEquals(classification.getList_classNames().get(4).getText(),"class 5","Instructor is not able to enter a class");

            classification.getLink_addNewClass().click();//Click on 'Add new class' link

            //Expected - 2 : User can have a maximum of 5 classes
            if(classification.getList_classNames().size()!=5)
            {
                Assert.fail("User have more than 5 classes");
            }

        }catch(Exception e){
            Assert.fail("Exception in testcase 'classificationAnswerChoiceDragAndDrop' in class 'Classification'", e);

        }
    }

    @Test(priority =3,enabled = true)
    //TC row no. - 33 : Instructor should not be able to delete a class if there are class values assigned to it
    public void deleteAClassAndParameter(){
        try{Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            CreateAssignment createAssessment =PageFactory.initElements(driver,CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver,TLOListPage.class);
            QuestionTypesPage questionTypesPage =PageFactory.initElements(driver,QuestionTypesPage.class);
            ClassificationQuestionCreation classification= PageFactory.initElements(driver,ClassificationQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver,PreviewPage.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver,AssignmentReview.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);

            String appendChar = "b";
            int dataIndex = 384;
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getButton_create().click();//Click on create new assessment
            tloListPage.getButton_create().click();//Click on create button

            questionTypesPage.getIcon_Classification().click();//Click on Classification question type
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("question-raw-content")));
            closeHelp();
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name

            //>>>>>>TC row no. - 36 : parameters
            //Expected - 5 : The error message ( if any ) should be displayed.
            classification.getButton_save().click();//Click on Save button
            Thread.sleep(2000);
            Assert.assertEquals(classification.getMessage_text().getText(),"Question title should not be empty","error message is not displayed when question is not present");

            classification.getTextBox_QuestionField().clear();
            classification.getTextBox_QuestionField().sendKeys(questiontext);//Enter the question

            classification.getButton_save().click();//Click on Save button

            classification.getTextBox_QuestionField().clear();
            classification.getTextBox_QuestionField().sendKeys(questiontext);//Enter the question

            classification.getButton_save().click();//Click on Save button

            //Instructor must not be able to save the question without at least one class value
            //1. If at least one class value is not assigned, the question must not be saved
            //2. An error message saying “Please enter at least one class value.” must be displayed
            Assert.assertEquals(classification.getMessage_text().getText(),"Atleast 1 drop value must be classified","error message is not displayed when class is not present");

            classification.getList_classNames().get(0).click();
            driver.switchTo().activeElement().sendKeys("class 1");//Enter the 1st class
            classification.getLink_addNewClass().click();//Click on add new class link

            classification.getList_classNames().get(1).click();
            driver.switchTo().activeElement().sendKeys("class 2");//Enter 2nd class
            driver.findElement(By.xpath("//html/body")).click();

            classification.getLink_addNewClass().click();//Click on 'Add new class'

            classification.getList_classNames().get(2).click();
            driver.switchTo().activeElement().sendKeys("class 3");//Enter 3rd class
            driver.findElement(By.xpath("//html/body")).click();
            Actions builder = new Actions(driver);
            builder.moveToElement(classification.getList_classArea().get(2), 20, 5 ).click().build().perform();//Click on 3rd class
            classification.getList_deleteClass().get(2).click();//Delete 3rd class

            //Expected - Clicking on delete icon must delete the class value
            if(classification.getList_classNames().size()!=2)
            {
                Assert.fail("class is not deleted");
            }

            driver.findElement(By.xpath("//html/body")).click();


            classification.getButton_save().click();//Click on Save button

            Assert.assertEquals(classification.getMessage_text().getText(),"Please drag all the classes","Expected error 'Please add answer choices' is not displayed");

            builder.moveToElement(classification.getList_classArea().get(1), 20, 5 ).click().build().perform();//Click on 2nd class
            classification.getList_deleteClass().get(1).click();//Delete 2nd class

            builder.moveToElement(classification.getList_classArea().get(0), 20, 5 ).click().build().perform();//Click on 1st class
            new DragAndDrop().dragAndDrop(classification.getDragClass(),classification.getDrop_class());

            classification.getButton_save().click();//Click on save button

            //Correct error message should appear
            Assert.assertEquals(classification.getMessage_text().getText(),"Atleast 1 drop value must be classified","Expected error message is not displayed");

            classification.getLink_addNewClass().click();//Click on add new class link

            classification.getList_classNames().get(1).click();
            driver.switchTo().activeElement().sendKeys("class 2");//Enter 2nd class
            driver.findElement(By.xpath("//html/body")).click();

            classification.getList_enterAnswerChoice().get(0).click();
            classification.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("Answer 1");//Enter 1st answer choice
            classification.getList_enterAnswerChoice().get(0).click();
            classification.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("Answer 2");//Enter 2nd answer choice
            driver.findElement(By.xpath("//html/body")).click();

            classification.getButton_save().click();//Click on Save button

            //Instructor must not be able to save the question without at least one class value
            // 1. Instructor must not be able to save the question if the any class is not assigned to the Classification table
            // 2. An error message saying “Please assign all answer choices” must be displayed
            Assert.assertEquals(classification.getMessage_text().getText(),"Please drag all the classes","Expected error is not displayed if values are not assigned to class");

            classification.getButton_save().click();//Click on Save button

            classification.getList_enterAnswerChoice().get(0).click();//Click on 1st answer choice
            new DragAndDrop().dragAndDrop(classification.getList_drag_answerChoice().get(0), classification.getListDropped_classArea().get(0));//Drop the 1st answer choice to dropped class area
            driver.findElement(By.xpath("//html/body")).click();
            classification.getList_enterAnswerChoice().get(1).click();//Click on 2nd answer choice
            new DragAndDrop().dragAndDrop(classification.getList_drag_answerChoice().get(1),classification.getListDropped_classArea().get(0));//Drop the 2nd answer choice to dropped class area

            builder.moveToElement(classification.getList_classArea().get(0), 20, 5 ).click().build().perform();//Click on 2nd class
            new DragAndDrop().dragAndDrop(classification.getList_dragClass().get(1),classification.getDrop_class());


            classification.getButton_save().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("footer-notification-text")));
            //>>>>>>TC row no. - 38 : Saving the question by clicking on save button
            //Expected- 1 : Clicking on save must save the changes in the question, A notification 'Saved' must be displayed it is saved.
            Assert.assertEquals(classification.getMessage_text().getText(),"Saved.","'Saved' notification is not displayed after saving the question");

            String winHandleBefore = driver.getWindowHandle();

            classification.getButton_preview().click();//Click on Preview button

            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            Thread.sleep(2000);
            classification.getPreview_submit().click();

            //Expected - 1 : One question should carry 1 Point
            Assert.assertEquals(previewPage.getQuestionPoint_previewPage().getAttribute("value"),"1","Question is not carrying one point");

            driver.close();
            driver.switchTo().window(winHandleBefore);//Switch the driver control back to main window

            classification.getTextbox_solution().clear();
            driver.switchTo().activeElement().sendKeys("Solution Text");
            classification.getTextbox_hint().clear();
            driver.switchTo().activeElement().sendKeys("Hint Text");

            //Expected - 2 : Questions should have solution and hint as other question types
            Assert.assertEquals(classification.getTextbox_solution().isDisplayed(),true,"Solution text box is not displayed");
            Assert.assertEquals(classification.getTextbox_hint().isDisplayed(),true,"Hint text box is not displayed");

            Select select = new Select(classification.getDifficulty_level());

            //Expected - 3 : Instructor should be able to tag the question with different levels of difficulty
            select.selectByVisibleText("Easy");
            select.selectByVisibleText("Medium");
            select.selectByVisibleText("Hard");
            Thread.sleep(2000);

            classification.getLearningObjective().click();//Click on learning objective

            //Expected - 4 : Instructor should be able to tag the question with LOs
            if(!classification.getList_tloElo_learningObjective().get(0).getText().trim().contains("1.OA"))
            {
                Assert.fail("Expected TLO is not displayed");
            }

            if(!classification.getList_tloElo_learningObjective().get(1).getText().trim().contains("1.OA.A.1"))
            {
                Assert.fail("Expected ELO is not displayed");
            }

            int defaultListSize = classification.getList_tloElo_learningObjective().size();

            if(defaultListSize!=2)
            {
                Assert.fail("Learning Objective list size is not equal to 2");
            }

            classification.getAdd_learningObjetive().click();//Click on add Learning Objective link
            classification.getCheckbox_elo1_OA_A_2().click();//Click on 1.OA.A.2 ELO
            classification.getButton_associate().click();//Click on Associate button
            Thread.sleep(2000);

            classification.getLearningObjective().click();//Click on learning objective
            classification.getLearningObjective().click();//Click on learning objective
            Thread.sleep(2000);

            if(!classification.getList_tloElo_learningObjective().get(2).getText().trim().contains("1.OA.A.2"))
            {
                Assert.fail("Expected ELO is not displayed");
            }

            int listSizeAfterAddingTLO = classification.getList_tloElo_learningObjective().size();

            if(listSizeAfterAddingTLO!=3)
            {
                Assert.fail("Learning Objective list size is not equal to 3");
            }
            classification.getCloseButton_learningObjective().click();//Close the learning objective

           //>>>>>>TC row no - 44 : Instructor short view of classification type question when he clicks on 'Review'
            classification.getButton_review().click();//Click on Review button

            //Expected - 1 : Only question title should be displayed.
            if(!listPage.getCreateAssignmentQuestionName().getText().contains(questiontext)){
                Assert.fail("Question title is not displayed");
            }

            //Expected - 2 : Question Type should be displayed as “Classification Question”
            Assert.assertEquals(listPage.getQuestionType().getText(),"Classification","Question type is not displayed");

            //Expected - 3 : Owner of the question must be displayed
            Assert.assertEquals(listPage.getLabelValue_Ownwer().getText(),"You","Owner of the question is not displayed");

            //Expected - 4 : Question ID must be displayed
            if(listPage.getLabelValue_QuestionID().getText().length()==0)
            {
                Assert.fail("Question id is not displayed");

            }
            //Expected - 5 : Corresponding TLO and ELO under which the question falls must be displayed
            if(!listPage.getLabelValue_StandardTlo().getText().contains("1.OA, 1.OA.A.1, 1.OA.A.2"))
            {
                Assert.fail("Tlo and Elo is not displayed");
            }
            String str1 = listPage.getCreateAssignmentQuestionName().getText();

            listPage.getArrowLink().click();//Select the assignment
            String str2 = assessmentQuestionPage.getLabel_questionName().getText();

            //>>>>>>TC row no. - 45 : Instructor question expanded view
            //Expected - 1 :  Question body must be displayed
            if(!str1.trim().contains(str2)){
                Assert.fail("The particular question is not opened.");
            }

            //Expected - 2 : Class names must be displayed with drop areas
            Assert.assertEquals(classification.getDropped_classHeader().getText(),"class 1","class name is not displayed with drop area");

            //Expected - 3 : Value names must be displayed on the left side
            Assert.assertEquals(assessmentReview.getList_answerChoice().get(0).getText(),"Answer 1","Answer choice is not displayed on left side");
            Assert.assertEquals(assessmentReview.getList_answerChoice().get(1).getText(),"Answer 2","Answer choice is not displayed on left side");

            assessmentReview.getList_answerChoice().get(0).click();//Click on 1st answer choice

            //Expected - 4 : Drag and drop must not be allowed
            boolean dragFound = new BooleanValue().presenceOfElement(dataIndex,By.id("ans-drag-btn"));
            Assert.assertEquals(dragFound,false,"Drag and drop is allowed");

            //>>>>>>TC row no. - 42 : Instructor should be able edit the question by clicking on the edit link
            assignments.getEditLink().click();//Click on Edit link

            //Expected - 1 : The question with values should be populated. Instructor can edit, answer choices, classes, etc.
            Assert.assertEquals(classification.getTextBox_QuestionField().getText(),questiontext,"question name is not populated correctly");
            Assert.assertEquals(classification.getList_classNames().get(0).getText(),"class 1","Class name is not displayed entered by instructor");
            Assert.assertEquals(classification.getList_classNames().get(1).getText(),"class 2","Class name is not displayed entered by instructor");
            Assert.assertEquals(classification.getList_enterAnswerChoice().get(0).getText(),"Answer 1","Answer choice is not displayed entered by instructor");
            Assert.assertEquals(classification.getList_enterAnswerChoice().get(1).getText(),"Answer 2","Answer choice is not displayed entered by instructor");
            Assert.assertEquals(classification.getDropped_classHeader().getText(),"class 1","Dropped class is not displayed");
            Assert.assertEquals(classification.getListDropped_answerChoice().get(0).getText(),"Answer 1","Dropped answer choice is not displayed");
            Assert.assertEquals(classification.getListDropped_answerChoice().get(1).getText(),"Answer 2","Dropped answer choice is not displayed");

            classification.getTextBox_QuestionField().clear();
            driver.switchTo().activeElement().sendKeys(questiontext + "1");//Edit the question
            driver.findElement(By.xpath("/html/body"));

            classification.getRemoveButton().get(1).click();//Remove the 2nd class for drop area
            Thread.sleep(2000);

            classification.getList_classNames().get(1).click();
            driver.switchTo().activeElement().clear();
            driver.switchTo().activeElement().sendKeys("class 21");//Edit the 2nd class

            builder.moveToElement(classification.getList_classArea().get(0), 20, 5 ).click().build().perform();//Click on 2nd class
            new DragAndDrop().dragAndDrop(classification.getList_dragClass().get(0),classification.getDrop_class());

            classification.getButton_save().click();//Click on Save button
            Thread.sleep(2000);
            Assert.assertEquals(classification.getMessage_text().getText(),"Saved.","Question is not saved after edit");

            //Expected - 2 : Clicking save must save all the changes and the changes must be reflected
            Assert.assertEquals(classification.getTextBox_QuestionField().getText(),questiontext+"1","Question is not saved");
            Assert.assertEquals(classification.getList_classNames().get(1).getText(),"class 21","Class name is not displayed entered by instructor");

            //>>>>>>TC row no - 44 : Instructor short view of classification type question when he clicks on 'Review'
            classification.getButton_review().click();//Click on review button
            listPage.getButton_addMore().click();//Click on Add more button
            tloListPage.getButton_create().click();//Click on create button
            questionTypesPage.getIcon_Classification().click();//Click on Classification question type
            closeHelp();

            classification.getTextBox_QuestionField().sendKeys(questiontext+"2");//Enter the question
            classification.getList_classNames().get(0).click();
            driver.switchTo().activeElement().clear();
            driver.switchTo().activeElement().sendKeys("class 1");//Enter 1st class
            driver.findElement(By.xpath("//html/body")).click();
            classification.getList_enterAnswerChoice().get(0).click();
            classification.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("Answer 1");
            driver.findElement(By.xpath("//html/body")).click();

            builder.moveToElement(classification.getList_classArea().get(0), 20, 5 ).click().build().perform();//Click on 1st class
            new DragAndDrop().dragAndDrop(classification.getDragClass(),classification.getDrop_class());

            classification.getList_enterAnswerChoice().get(0).click();//Click on 1st answer choice
            new DragAndDrop().dragAndDrop(classification.getList_drag_answerChoice().get(0),classification.getListDropped_classArea().get(0));

            classification.getButton_save().click();//Click on save button
            Thread.sleep(10000);
            classification.getButton_review().click();//Click on Review button

            listPage.getList_dragQuestion().get(1).click();//Click on drag area
            new DragAndDrop().dragAndDrop(listPage.getList_dragQuestion().get(1), listPage.getList_dragQuestion().get(0));

            //Expected - 6 : Re-ordering in list view should be supported
            Assert.assertEquals(listPage.getList_questionBody().get(0).getText(),"Q1: "+questiontext+"2","Questions are not re ordered");
            Assert.assertEquals(listPage.getList_questionBody().get(1).getText(),"Q2: "+questiontext+"1","Questions are not re ordered");

        }catch(Exception e)
        {
            Assert.fail("Exception in testcase 'deleteAClassAndParameter' in class 'Classification'", e);

        }
    }

        @Test(priority = 4,enabled = true)
        public void preview(){
            try{Assessments assessments = PageFactory.initElements(driver, Assessments.class);
                CreateAssignment createAssessment =PageFactory.initElements(driver,CreateAssignment.class);
                TLOListPage tloListPage = PageFactory.initElements(driver,TLOListPage.class);
                QuestionTypesPage questionTypesPage =PageFactory.initElements(driver,QuestionTypesPage.class);
                ClassificationQuestionCreation classification= PageFactory.initElements(driver,ClassificationQuestionCreation.class);
                PreviewPage previewPage = PageFactory.initElements(driver,PreviewPage.class);
                AssignmentReview assessmentReview = PageFactory.initElements(driver,AssignmentReview.class);
                String appendChar = "a";
                int dataIndex = 691;
                new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
                new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
                new School().createWithOnlyName(appendChar, dataIndex);//create school
                String classCode=new Classes().createClass(appendChar, dataIndex);//create class

                new Navigator().logout();

                new SignUp().studentDirectRegister(appendChar, classCode, 845);//create student
                new Navigator().logout();//log out

                new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
                new Navigator().navigateTo("assignment");//Navigate to Assignment page
                assessments.getButton_newAssessment().click();//Click on new assessment
                createAssessment.getButton_create().click();//Click on create new assessment
                tloListPage.getButton_create().click();//Click on create button
                questionTypesPage.getIcon_Classification().click();//Click on Classification question type

                String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
                String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

                createAssessment.getAssessment_editbox().clear();
                createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name

                classification.getTextBox_QuestionField().clear();
                classification.getTextBox_QuestionField().sendKeys(questiontext);//Enter the question

                classification.getList_classNames().get(0).click();
                driver.switchTo().activeElement().clear();
                driver.switchTo().activeElement().sendKeys("class 1");//Enter 1st class

                classification.getList_enterAnswerChoice().get(0).click();
                classification.getAnswerchoice_text().click();//Click on text type
                driver.switchTo().activeElement().sendKeys("Answer 1");//Enter 1st answer choice
                driver.findElement(By.xpath("//html/body")).click();
                classification.getList_enterAnswerChoice().get(1).click();
                classification.getAnswerchoice_text().click();//Click on text type
                driver.switchTo().activeElement().sendKeys("Answer 2");//Enter 2nd answer choice
                driver.findElement(By.xpath("//html/body")).click();
                classification.getList_enterAnswerChoice().get(2).click();
                classification.getAnswerchoice_text().click();//Click on text type
                driver.switchTo().activeElement().sendKeys("Answer 3");//Enter 3rd answer choice
                driver.findElement(By.xpath("//html/body")).click();
                classification.getList_enterAnswerChoice().get(3).click();
                classification.getAnswerchoice_text().click();//Click on text type
                driver.switchTo().activeElement().sendKeys("Answer 4");//Enter 4th answer choice
                driver.findElement(By.xpath("//html/body")).click();


                classification.getList_classArea().get(0).click();//Click on 1st class
                closeHelp();
                new DragAndDrop().dragAndDrop(classification.getList_dragClass().get(0),classification.getDrop_class());

                classification.getList_enterAnswerChoice().get(0).click();//Click on 1st answer choice
                new DragAndDrop().dragAndDrop(classification.getList_drag_answerChoice().get(0),classification.getListDropped_classArea().get(0));

                classification.getList_enterAnswerChoice().get(1).click();//Click on 2nd answer choice
                new DragAndDrop().dragAndDrop(classification.getList_drag_answerChoice().get(1),classification.getListDropped_classArea().get(0));

                classification.getList_enterAnswerChoice().get(2).click();//Click on 3rd answer choice
                new DragAndDrop().dragAndDrop(classification.getList_drag_answerChoice().get(2),classification.getListDropped_classArea().get(0));

                classification.getList_enterAnswerChoice().get(3).click();//Click on 4th answer choice
                new DragAndDrop().dragAndDrop(classification.getList_drag_answerChoice().get(3),classification.getListDropped_classArea().get(0));

                classification.getButton_save().click();//Click on Save button
                classification.getButton_preview().click();//Click on Preview page
                Thread.sleep(2000);

                //Expected - 1 : A new tab must be opened that displays the preview
                String winHandleBefore = driver.getWindowHandle();
                for(String handle:driver.getWindowHandles())
                {
                    driver.switchTo().window(handle);
                }
                String questionTextPreviewPage = previewPage.getQuestion_text().getText();
                String answerChoice1PreviewPage = assessmentReview.getList_answerChoice().get(0).getText();
                String answerChoice2PreviewPage = assessmentReview.getList_answerChoice().get(1).getText();
                String answerChoice3PreviewPage = assessmentReview.getList_answerChoice().get(2).getText();
                String answerChoice4PreviewPage = assessmentReview.getList_answerChoice().get(3).getText();

               String droppedClassOnPreviewPage = classification.getDropped_classHeader().getText();

                driver.close();
                driver.switchTo().window(winHandleBefore);
                new Assignment().assignToStudent(dataIndex,appendChar);//Assign to class

                new Navigator().logout();//logout

                new Login().loginAsStudent(appendChar,dataIndex);//Login as student
                new Navigator().navigateTo("assignment");//Navigate to Assessments page

                assessments.getAssignment().click();//Select the assignment
                closeHelp();

                //Expected - 2 : The preview should be displayed just like a student would view the question
                Assert.assertEquals(previewPage.getQuestion_text().getText(), questionTextPreviewPage, "Question is not appearing as in preview page on instructor side");
                Assert.assertEquals(assessmentReview.getList_answerChoice().get(0).getText(),answerChoice1PreviewPage,"Answer Choice 1 is not appearing as in preview page on instructor side");
                Assert.assertEquals(assessmentReview.getList_answerChoice().get(1).getText(),answerChoice2PreviewPage,"Answer Choice 2 is not appearing as in preview page on instructor side");
                Assert.assertEquals(assessmentReview.getList_answerChoice().get(2).getText(),answerChoice3PreviewPage,"Answer Choice 3 is not appearing as in preview page on instructor side");
                Assert.assertEquals(assessmentReview.getList_answerChoice().get(3).getText(),answerChoice4PreviewPage,"Answer Choice 4 is not appearing as in preview page on instructor side");
                Assert.assertEquals(classification.getDropped_classHeader().getText(),droppedClassOnPreviewPage,"Dropped class is not appearing as in preview page on instructor side");

                //>>>>>>TC row no. - 46 : Student question attempt view
                //Student should be able to attempt the 'Classification question'
                assessments.getList_answerChoice().get(0).click();
                new DragAndDrop().dragAndDrop(assessments.getList_answerChoice().get(0),classification.getListDropped_classArea().get(0));

                //Expected - 4 : Student should be able to drag and drop the answer choices into the class area
                Assert.assertEquals(classification.getListDropped_answerChoice().get(0).getText(),"Answer 1","Student is not able to drag and drop the answer choice");

                new DragAndDrop().dragAndDrop(assessments.getList_answerChoice().get(1),classification.getListDropped_classArea().get(0));
                new DragAndDrop().dragAndDrop(assessments.getList_answerChoice().get(2),classification.getListDropped_classArea().get(0));
                new DragAndDrop().dragAndDrop(assessments.getList_answerChoice().get(3),classification.getListDropped_classArea().get(0));

                //Expected - 5 : The size of the table must be resized based the values dropped
                Assert.assertEquals(classification.getListDropped_answerChoice().get(1).getText(),"Answer 2","Student is not able to drag and drop the answer choice");
                Assert.assertEquals(classification.getListDropped_answerChoice().get(2).getText(),"Answer 3","Student is not able to drag and drop the answer choice");
                Assert.assertEquals(classification.getListDropped_answerChoice().get(3).getText(),"Answer 4","Student is not able to drag and drop the answer choice");

                //Expected - 6 :  The answer choices should disappear from the left when it is dropped onto any class area
                Assert.assertEquals(assessments.getList_answerChoiceDisabled().get(0).getAttribute("aria-disabled"),"true","1st Answer choice is not disappeared after dropping onto class area");
                Assert.assertEquals(assessments.getList_answerChoiceDisabled().get(1).getAttribute("aria-disabled"),"true","2nd Answer choice is not disappeared after dropping onto class area");
                Assert.assertEquals(assessments.getList_answerChoiceDisabled().get(2).getAttribute("aria-disabled"),"true","3rd Answer choice is not disappeared after dropping onto class area");
                Assert.assertEquals(assessments.getList_answerChoiceDisabled().get(3).getAttribute("aria-disabled"),"true","4th Answer choice is not disappeared after dropping onto class area");

                classification.getListDelete_droppedAnswerChoice().get(0).click();//Delete the 1st answer choice from dropped class
                Thread.sleep(2000);

                //Expected - 7 : Student must be able to move the values from the class back on to the left side
                Assert.assertEquals(assessments.getList_answerChoiceDisabled().get(0).getAttribute("aria-disabled"),"false","Student is not able to move the 1st answer choice back on the left side");

                if(classification.getListDropped_answerChoice().size()!=3)
                {
                    Assert.fail("Student is not able to move the 1st answer choice back on the left side");
                }

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

            }catch(Exception e){
                Assert.fail("Exception in testcase 'preview' in class 'Classification'", e);
            }
        }

            @Test(priority = 5,enabled = true)
            public void evaluation(){
                try{Assessments assessments = PageFactory.initElements(driver, Assessments.class);
                    CreateAssignment createAssessment =PageFactory.initElements(driver,CreateAssignment.class);
                    TLOListPage tloListPage = PageFactory.initElements(driver,TLOListPage.class);
                    QuestionTypesPage questionTypesPage =PageFactory.initElements(driver,QuestionTypesPage.class);
                    ClassificationQuestionCreation classification= PageFactory.initElements(driver,ClassificationQuestionCreation.class);
                    AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);

                    String appendChar = "a";
                    int dataIndex = 852;
                    new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
                    new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
                    new School().createWithOnlyName(appendChar, dataIndex);//create school
                    String classCode=new Classes().createClass(appendChar, dataIndex);//create class

                    new Navigator().logout();

                    new SignUp().studentDirectRegister(appendChar, classCode, 845);//create student
                    new Navigator().logout();//log out

                    new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
                    new Navigator().navigateTo("assignment");//Navigate to Assignment page
                    assessments.getButton_newAssessment().click();//Click on new assessment
                    createAssessment.getButton_create().click();//Click on create new assessment
                    tloListPage.getButton_create().click();//Click on create button
                    questionTypesPage.getIcon_Classification().click();//Click on Classification question type
                    new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
                    closeHelp();

                    String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
                    String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

                    createAssessment.getAssessment_editbox().clear();
                    createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name

                    classification.getTextBox_QuestionField().clear();
                    classification.getTextBox_QuestionField().sendKeys(questiontext);//Enter the question

                    classification.getList_classNames().get(0).click();
                    driver.switchTo().activeElement().clear();
                    driver.switchTo().activeElement().sendKeys("class 1");//Enter 1st class
                    classification.getLink_addNewClass().click();//Click on add new class link to add the new class
                    classification.getList_classNames().get(1).click();
                    driver.switchTo().activeElement().clear();
                    driver.switchTo().activeElement().sendKeys("class 2");//Enter 2nd class
                    driver.findElement(By.xpath("//html/body")).click();

                    classification.getList_enterAnswerChoice().get(0).click();
                    classification.getAnswerchoice_text().click();//Click on text type
                    driver.switchTo().activeElement().sendKeys("Answer 1");//Enter 1st answer choice
                    driver.findElement(By.xpath("//html/body")).click();
                    classification.getList_enterAnswerChoice().get(1).click();
                    classification.getAnswerchoice_text().click();//Click on text type
                    driver.switchTo().activeElement().sendKeys("Answer 2");//Enter 2nd answer choice
                    driver.findElement(By.xpath("//html/body")).click();

                    classification.getList_classArea().get(0).click();//Click on 1st class
                    new DragAndDrop().dragAndDrop(classification.getList_dragClass().get(0),classification.getDrop_class());
                    Thread.sleep(2000);

                    classification.getList_enterAnswerChoice().get(0).click();//Click on 1st answer choice
                    new DragAndDrop().dragAndDrop(classification.getList_drag_answerChoice().get(0),classification.getListDropped_classArea().get(0));//Drop 1st answer choice on 1st class

                    classification.getList_classArea().get(0).click();//Click on 2nd class
                    new DragAndDrop().dragAndDrop(classification.getList_dragClass().get(1),classification.getDrop_class());
                    Thread.sleep(2000);

                    classification.getList_enterAnswerChoice().get(1).click();//Click on 2nd answer choice
                    new DragAndDrop().dragAndDrop(classification.getList_drag_answerChoice().get(1),classification.getListDropped_classArea().get(1));//Drop 2nd answer choice on 2nd class

                    classification.getCheckbox_scratchpad().click();//Click on scratchpad checkbox
                    classification.getButton_save().click();//Click on save button

                    classification.getButton_review().click();//Click on Review button
                    questionsListPage.getButton_saveForLater().click();//Click on Save for later button

                    new Assignment().assignToStudent(dataIndex,appendChar);//Assign to class

                    new Navigator().logout();//logout

                    new Login().loginAsStudent(appendChar,dataIndex);//Login as student
                    new Navigator().navigateTo("assignment");//Navigate to Assessments page

                    assessments.getAssignment().click();//Select the assignment

                    String str = new RandomString().randomstring(5);

                    //>>>>>>TC row no - 51 :  Student should be able to use the writeboard if it is allowed for the question
                    //Expected - 1 : ' Show your work' link should be displayed if the question has writeboard enabled
                    //Expected - 2 :  On clicking ' Show your work' link, writeboard should be opened
                    new WriteBoard().enterTextInWriteBoard(str,dataIndex);

                    assessments.getShowOrHideYourWork().click();//Click on Show your work

                    boolean dataSaved =new WriteBoard().verifyWriteBoardDataIsSavedForNumberLine(str);
                    Assert.assertEquals(dataSaved,true,"WriteBoard data is not saved");

                    assessments.getShowOrHideYourWork().click();//Click on Show your work

                    new WriteBoard().deleteWriteBoardData();
                    boolean dataDeleted = new WriteBoard().verifyWriteBoardDataIsDeletedForNumberLine(str);
                    Assert.assertEquals(dataDeleted,true,"WriteBoard data is not deleted");
                    Thread.sleep(2000);

                    new DragAndDrop().dragAndDrop(assessments.getList_answerChoice().get(0),classification.getListDropped_classArea().get(1));//Drop 1st answer choice onto class 2
                    new DragAndDrop().dragAndDrop(assessments.getList_answerChoice().get(1),classification.getListDropped_classArea().get(1));//Drop 2nd answer choice onto class 2

                    assessments.getButton_submit().click();//Click on Submit button
                    assessments.getFinal_submit().click();//Click on submit

                    assessments.getButton_continue().click();//Click on Continue button

                    //Expected - 6 : The marks should be should be shown if the assessment is graded
                    Assert.assertEquals(assessments.getScore().getAttribute("value"),"0.5","Score is not 0.5 for partially  correct answer");

                    //>>>>>>TC row no - 57 : Student's report view
                    //Student must be able to view the answers given by him for the question
                    //Expected - 1 :  The class areas must be displayed
                    Assert.assertEquals(classification.getListDropped_classHeader().get(0).getText(),"class 1","Class is not displayed");
                    Assert.assertEquals(classification.getListDropped_classHeader().get(1).getText(),"class 2","Class is not displayed");

                    //Expected - 2 : The value names dropped by the him in a class must be displayed
                    if(!assessments.getAnswers_class2().getText().replaceAll("[\\t\\n\\r]"," ").contains("Answer 1 Answer 2"))
                    {
                        Assert.fail("The value names dropped by the student in a class2 is not displayed");
                    }

                    //Expected - 3 : The correct answer should be marked with green color and a tick mark
                    Assert.assertEquals(assessments.getCorrectAnswer_class2().getText(),"Answer 2","The correct answer is not marked with green color and a tick mark");

                    //Expected - 4 : The wrong answer must be marked with Red color and a cross
                    Assert.assertEquals(assessments.getWrongAnswer_class2().getText(),"Answer 1","The wrong answer is not marked with red color and a cross");

                    assessments.getLink_showCorrectAnswer().click();//Click on 'Show Correct Answer'

                    //Expected - 7 : Clicking on "View correct answer" must display all the correct answers
                    Assert.assertEquals(assessments.getAnswers_class2().getText(),"Answer 2","show correct answer link is not displayed the correct answers");
                    Assert.assertEquals(assessments.getAnswers_class1().getText(),"Answer 1","show correct answer link is not displayed the correct answers");

                    assessments.getLink_showYourAnswer().click();//Click on 'Show your Answer'

                    //Expected - 8 : Clicking on "Show your answer" should display the answer given by the him
                    if(!assessments.getAnswers_class2().getText().replaceAll("[\\t\\n\\r]"," ").contains("Answer 1 Answer 2"))
                    {
                        Assert.fail("'Show Your Answer' is not displayed the answer given by student");

                    }
                    //Expected - 9 : The marks area should be shown if the assessment is graded
                    Assert.assertEquals(assessments.getScoreBoard().isDisplayed(),true,"marks area is not displayed even if the assessment is graded");

                    new Navigator().logout();//log out

                    new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
                    new Navigator().navigateTo("assignment");//Navigate to Assessment page
                    assessments.getView_grades().click();//click on view grades
                    assessments.getGrade_gradable().click();//Click on score assigned to student

                    //>>>>>>TC row no - 53 : Instructor gradebook view
                    //Instructor must be able to view the student's answer
                    //Expected - 1 : The class areas must be displayed
                    Assert.assertEquals(classification.getListDropped_classHeader().get(0).getText(),"class 1","Class area is not displayed");
                    Assert.assertEquals(classification.getListDropped_classHeader().get(1).getText(),"class 2","Class area is not displayed");

                    //Expected - 2 : The value names dropped by the student in a class must be displayed correctly
                    if(!assessments.getAnswers_class2().getText().replaceAll("[\\t\\n\\r]"," ").contains("Answer 1 Answer 2"))
                    {
                        Assert.fail("The value names dropped by the student in a class is not displayed correctly");

                    }

                    //Expected - 3 : The correct answer should be marked with green color and a tick mark
                    Assert.assertEquals(assessments.getCorrectAnswer_class2().getText(),"Answer 2","The correct answer is not marked with green color and a tick mark");

                    //Expected - 4 : The wrong answer must be marked with Red color and a cross
                    Assert.assertEquals(assessments.getWrongAnswer_class2().getText(),"Answer 1","The wrong answer is not marked with red color and a cross");

                    //Expected - 6 : The marks should be should be shown if the assessment is graded
                    Assert.assertEquals(assessments.getScore().getAttribute("value"),"0.5","Mark is not shown as 0.5");

                    //>>>>>>TC row no. - 56 : Instructor should be able to view the correct and student's answers by clicking on the respective link
                    assessments.getLink_showCorrectAnswer().click();//Click on 'Show Correct Answer' link

                   //Expected - 1 : .Clicking on "View correct answer must display all the correct answers
                    Assert.assertEquals(assessments.getAnswers_class2().getText(),"Answer 2","show correct answer link is not displayed the correct answers");
                    Assert.assertEquals(assessments.getAnswers_class1().getText(),"Answer 1","show correct answer link is not displayed the correct answers");

                    assessments.getLink_showYourAnswer().click();//Click on 'Show your Answer'

                    //Expected - 2 : Clicking on "Show your answer" should display the answer given by the him
                    if(!assessments.getAnswers_class2().getText().replaceAll("[\\t\\n\\r]"," ").contains("Answer 1 Answer 2"))
                    {
                        Assert.fail("'Show Your Answer' is not displayed the answer given by student");

                    }
                }catch (Exception e){
                    Assert.fail("Exception in testcase 'evaluation' in class 'Classification'", e);
                }
            }
}
