package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.Epic46;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.preview.PreviewPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

/**
 * Created by root on 12/29/14.
 */
public class NumberLine extends Driver{
    @Test(priority = 1,enabled = true)
    //TC row no. 214 - The following authoring UI for teachers should be displayed to the user on clicking on the question type
    public void numberLineUI(){
        try{
            Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            CreateAssignment createAssessment =PageFactory.initElements(driver,CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver,TLOListPage.class);
            QuestionTypesPage questionTypesPage =PageFactory.initElements(driver,QuestionTypesPage.class);
            NumberLineQuestionCreation numberline = PageFactory.initElements(driver,NumberLineQuestionCreation.class);
            String appendChar = "a";
            int dataIndex = 31;
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getButton_create().click();//Click on create new assessment
            tloListPage.getButton_create().click();//Click on create button
            questionTypesPage.getIcon_NumberLine().click();//Click on Number Line question type
            closeHelp();

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name

            String questionName =numberline.getQuestionTitle().getText();

            // Expected - 1: Question title will be shown as “Number Line question”
            Assert.assertEquals(questionName,"Number Line question","Question title is not shown as expected");

            String questionBodyText = numberline.getTextBox_QuestionField().getText();

            //Expected - 2 : “Enter question text” should be shown by default in the question body
            Assert.assertEquals(questionBodyText,"Enter Question Text","By default 'Enter question text' is not displayed in the question body");

            numberline.getTextBox_QuestionField().clear();
            numberline.getTextBox_QuestionField().sendKeys(questiontext);//Enter the question
            driver.findElement(By.xpath("/html/body")).click();
            String text = numberline.getTextBox_QuestionField().getText();

            //Expected - 3 : Teacher can enter text and other elements in the question body using the standard editing palette (redactor)
            Assert.assertEquals(text,questiontext,"Not able to enter text in question text box");

            //Expected - 4 : Default answer choices under "Enter answer Choice" should be displayed
            List<WebElement> answerList = numberline.getList_enterAnswerChoice();
            int listSize = answerList.size();

            //4.1 : By default, there should be 5 answer choices present
            if(listSize!=4)
            {
                Assert.fail("By default five answer choices are not present");
            }

            //4.2 : By default, text should be 'Enter answer choice' for all the answer choices
            for(WebElement answer: answerList)
            {
                Assert.assertEquals(answer.getText(), "Enter answer choice", "Expected text 'Enter answer choice' is not present");
            }

            //Expected - 5 : Link to add more options should be present
            Assert.assertEquals(numberline.getLink_addNewAnswerChoice().getText().trim(),"Add new answer choice"," 'Add new answer choice' link is not present");

            //Expected - 6 : Under Option following check boxes must be displayed:
            boolean shuffleSelected = numberline.getCheckbox_shuffleAnswerChoices().isSelected();

            //Expected - 6.1 : check box for Shuffle answer choice and should be unchecked
            Assert.assertEquals(shuffleSelected,false,"Shuffle check box is checked");

            boolean majorTicksSelected =numberline.getCheckbox_majorTicks().isSelected();

            //Expected - 6.2 : check box for Show numbers for major ticks and should be checked
            Assert.assertEquals(majorTicksSelected,true,"major ticks check box is not checked");

            boolean scratchpadSelected = numberline.getCheckbox_scratchpad().isSelected();

            //Expected - 6.3 :  Allow students to use scratchpad checkbox should be unchecked
            Assert.assertEquals(scratchpadSelected,false,"scratchpad is checked");

            boolean isDisplayed = numberline.getNumberLine().isDisplayed();

            //Expected - 7 : A Number line to the right side of the page
            Assert.assertEquals(isDisplayed,true,"Number Line is not displayed");

            //>>>>>>TC row no. - 221 : Following should be displayed by default for number lines
            String axisStartValue=numberline.getTextbox_axisStartValue().getAttribute("value");

            //Expected - 1 : A Number line to the right side of the page

            //Expected - 2 : Start Value:0
            Assert.assertEquals(axisStartValue,"0","'Axis start Value' is not '0'");

            String endValue = numberline.getTextbox_endValue().getAttribute("value");

            //Expected - 3 : Start Value:10
            Assert.assertEquals(endValue,"10","'Axis start Value' is not '10'");

            String majorTicks = numberline.getTextbox_majorTicks().getAttribute("value");

            //Expected - 4 : Major Ticks:9
            Assert.assertEquals(majorTicks,"9","'Axis start Value' is not '9'");

            String minorTicks = numberline.getTextbox_minorTicks().getAttribute("value");

            //Expected - 3 : Start Value:3
            Assert.assertEquals(minorTicks,"3","'Axis start Value' is not '3'");


            //>>>>>>TC row no. - 226 : Option for "Enter Drop values"(Create questions of all the types)
            //Expected - 1.Can be text
            //           2.Can be MathMl
            //           3.can be Image
            answerList.get(0).click();//click on 1st answer option
            numberline.getAnswerchoice_text().click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 1");//Type the question

            answerList.get(0).click();//click on 2nd answer option
            numberline.getAnswerChoice_mathML().click();//Click on MathML type
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","31");

            answerList.get(2).click();//Click on 3rd answer option
            numberline.getAnswerChoice_image().click();//Click on image type
            new QuestionCreate().fileUploadInQuestionCreation("31");

            List<WebElement> answerListToDragOrDelete = numberline.getList_answerChoiceToDragOrDelete();

            //>>>>>>TC row no - 229 : Deleting answer choice
            answerListToDragOrDelete.get(0).click();//Click on 1st answer option
            numberline.getDelete_answerChoice().click();//Delete 1st answer option
            int newListSizeAfterDeleting = numberline.getList_enterAnswerChoice().size();

            // Expected - 1 : After answer choice is entered, user should be able to delete answer choice
            if(newListSizeAfterDeleting!=3)
            {
                Assert.fail("answer choice is not deleted'");
            }

            //>>>>>>TC row no. - 230 : Adding answer choice
            answerList.get(2).click();//Click on 2nd answer choice
            numberline.getAnswerchoice_text().click();//Click on text type
            driver.switchTo().activeElement().sendKeys("Answer 1");//Type the question
            driver.findElement(By.xpath("/html/body")).click();

            String answerChoice_3rd=answerList.get(2).getText();

            //Expected - 1 : Answers can be added on clicking "Enter Answer Choice"
            Assert.assertEquals(answerChoice_3rd,"Answer 1","Answer is not added");

            numberline.getLink_addNewAnswerChoice().click();//Click on 'Add new answer choice' link
            int newListSizeafterAdding = numberline.getList_enterAnswerChoice().size();

            //Expected - 2 : More Options can be added on clicking link "Add new answer Choice"
            if(newListSizeafterAdding!=4)
            {
                Assert.fail("Enter answer choice list is not increased after clicking on 'Add new answer choice'");
            }

        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase 'numberLineUI' in class 'NumberLine'", e);
        }
    }

    @Test(priority = 2,enabled = true)
    //TC row no. 232 - Defining correct answer choice
    public void definingCorrectAnswerChoice(){
        try{
            Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            CreateAssignment createAssessment =PageFactory.initElements(driver,CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver,TLOListPage.class);
            QuestionTypesPage questionTypesPage =PageFactory.initElements(driver,QuestionTypesPage.class);
            NumberLineQuestionCreation numberline = PageFactory.initElements(driver,NumberLineQuestionCreation.class);
            String appendChar = "a";
            int dataIndex = 195;
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getButton_create().click();//Click on create new assessment
            tloListPage.getButton_create().click();//Click on create button
            questionTypesPage.getIcon_NumberLine().click();//Click on Number Line question type
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            closeHelp();

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name

            numberline.getTextBox_QuestionField().clear();
            numberline.getTextBox_QuestionField().sendKeys(questiontext);//Enter the question

            List<WebElement> answerList = numberline.getList_enterAnswerChoice();

            answerList.get(0).click();//click on 1st answer option
            numberline.getAnswerchoice_text().click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 1");//Type the question

            answerList.get(0).click();//click on 2nd answer option
            numberline.getAnswerchoice_text().click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 2");//Type the question

            answerList.get(1).click();//click on 3rd answer option
            numberline.getAnswerchoice_text().click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 3");//Type the question

            answerList.get(2).click();//click on 4th answer option
            numberline.getAnswerchoice_text().click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 4");//Type the question

            List<WebElement> answerChoiceListToDrag = numberline.getList_answerChoiceToDragOrDelete();//Get the answer choice list size

            Actions ac = new Actions(driver);

            for(int i=0;i<3;i++)
            {
                answerChoiceListToDrag.get(0).click();
                ac.dragAndDrop(numberline.getList_dragAnswerChoice().get(i), numberline.getList_dropClass().get(i)).build().perform();
            }

            List<WebElement> allAnswers = numberline.getList_droppedAnswerChoice();
            //Expected - 1 : Answer choices can be dropped on the numberline only
            Assert.assertEquals(allAnswers.get(0).getText(),"Answer 1","Answer 1 is not dropped on the numberline");
            Assert.assertEquals(allAnswers.get(1).getText(),"Answer 2","Answer 2 is not dropped on the numberline");
            Assert.assertEquals(allAnswers.get(2).getText(),"Answer 3","Answer 3 is not dropped on the numberline");

            //Expected - 2 : They cannot be dropped in the class row or outside the table
            numberline.getAnswerChoice_1st().click();//Click on 1st answer choice
            ac.dragAndDrop(numberline.getDrag_answerChoice(),numberline.getLeftPanel()).build().perform();//Drop to left panel

            if(numberline.getLeftPanel().getText().equals("Answer 1"))
            {
                Assert.fail("answer choice is dropped outside the number line");
            }

            ac.dragAndDrop(numberline.getDrag_answerChoice(),numberline.getNumberLine_attribute()).build().perform();//Drop to attribute area

            if(numberline.getNumberLine_attribute().getText().equals("Answer 1"))
            {
                Assert.fail("answer choice is dropped outside the number line");
            }

        }catch(Exception e)
        {
            Assert.fail("Exception in testcase 'definingCorrectAnswerChoice' in class 'NumberLine'", e);
        }

    }

    @Test(priority = 3,enabled = true)
    //TC row no. - 232 : Defining correct answer choice
    public void definingCorrectAnswerChoiceNumberLine(){
        try{Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            CreateAssignment createAssessment =PageFactory.initElements(driver,CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver,TLOListPage.class);
            QuestionTypesPage questionTypesPage =PageFactory.initElements(driver,QuestionTypesPage.class);
            NumberLineQuestionCreation numberline = PageFactory.initElements(driver,NumberLineQuestionCreation.class);
            String appendChar = "a";
            int dataIndex = 288;
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getButton_create().click();//Click on create new assessment
            tloListPage.getButton_create().click();//Click on create button
            questionTypesPage.getIcon_NumberLine().click();//Click on Number Line question type
            closeHelp();

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name

            numberline.getTextBox_QuestionField().clear();
            numberline.getTextBox_QuestionField().sendKeys(questiontext);//Enter the question

            List<WebElement> answerList = numberline.getList_enterAnswerChoice();

            answerList.get(0).click();//click on 1st answer option
            numberline.getAnswerchoice_text().click();//click on Text Type
            String str = new RandomString().randomstring(3);
            driver.switchTo().activeElement().sendKeys(str);//Type the question


            answerList.get(0).click();//click on 2nd answer option
            numberline.getAnswerchoice_text().click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 2");//Type the question

            answerList.get(1).click();//click on 3rd answer option
            numberline.getAnswerchoice_text().click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 3");//Type the question
            driver.findElement(By.xpath("//html/body")).click();
            Thread.sleep(8000);

            Actions ac = new Actions(driver);

            numberline.getList_answerChoiceToDragOrDelete().get(1).click();//Click on 2nd answer choice
            ac.dragAndDrop(numberline.getDrag_answerChoice(),numberline.getList_dropClass().get(0)).build().perform();//Drop 2nd answer choice on 0th number line
            String droppedText = numberline.getList_droppedAnswerChoice().get(0).getText();

            numberline.getList_answerChoiceToDragOrDelete().get(0).click();//Click on 1st answer choice
            ac.dragAndDrop(numberline.getDrag_answerChoice(), numberline.getList_dropClass().get(0)).build().perform();//Drop 1st answer choice on same (0th) number line
            String replacedDroppedText = numberline.getList_droppedAnswerChoice().get(0).getText();

            //Expected 3 : If there is already an answer present, then another answer placed on the same tick will replace the existing answer.
            if(replacedDroppedText.equals(droppedText))
            {
                Assert.fail("answer choice is not replaced");
            }

            String verifyWrapText = numberline.getList_droppedAnswerChoice().get(0).getText();

            //Expected - 6 : Text fields should wrap
            if(!str.equals(verifyWrapText)){
                Assert.fail("Text field is not wrapped");
            }

            numberline.getList_droppedAnswerChoice().get(0).click();//Click on dropped object
            numberline.getRemove_droppedObject().click();//Remove the object

            boolean elementFound = false;
            try{
                numberline.getList_droppedAnswerChoice().get(0);
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }

            //Expected - 8 : On click, the dropped object will show a red cross. This is for deleting the object from the number line
            Assert.assertEquals(elementFound,false,"Dropped object is not deleted");

           //>>>>>>TC row no. - 240 : The number line is drawn using the following 4 fields to control the construction of the number line
            numberline.getTextbox_axisStartValue().clear();
            driver.switchTo().activeElement().sendKeys("5");//Type the axis start value
            driver.findElement(By.xpath("//html/body")).click();

            //Expected - 1 : Verify the value of Major Ticks(=End Value-Start Value-1)
            if(!numberline.getTextbox_majorTicks().getAttribute("value").contains("4"))
            {
                Assert.fail("Value of Major Ticks is not correct");
            }

            //Verify major ticks value on numberline
            if(numberline.getMajorTicks().size()!=6)
            {
                Assert.fail("major ticks is not reflected on numberline");
            }

            numberline.getTextbox_endValue().clear();
            driver.switchTo().activeElement().sendKeys("12");//Change and type the end value
            driver.findElement(By.xpath("/html/body")).click();

            if(!numberline.getTextbox_majorTicks().getAttribute("value").contains("6"))
            {
                Assert.fail("Value of Major Ticks is not correct");
            }

            //Verify major ticks value on numberline
            if(numberline.getMajorTicks().size()!=8)
            {
                Assert.fail("major ticks is not reflected on numberline");
            }

            numberline.getTextbox_majorTicks().clear();
            driver.switchTo().activeElement().sendKeys("22");
            driver.findElement(By.xpath("/html/body")).click();

            //Expected - 2 : Error message should appear if major ticks value is greater than 21
            Assert.assertEquals(numberline.getError_message_majorTicks().getText(),"Major tick should lies between 0 to 21","Error message is not displayed");

            numberline.getTextbox_axisStartValue().clear();
            driver.switchTo().activeElement().sendKeys("3");//Type the axis start value
            numberline.getTextbox_endValue().clear();
            driver.switchTo().activeElement().sendKeys("5");//Type the end value
            numberline.getTextbox_majorTicks().clear();
            driver.switchTo().activeElement().sendKeys("2");//Type the Major Ticks value
            driver.findElement(By.xpath("/html/body")).click();

            //Expected - 3 : Verify the labels on number line
            Assert.assertEquals(numberline.getListNumberLine_number().get(2).getText(),"3.67","Value of 1st major tick is not correct");
            Assert.assertEquals(numberline.getListNumberLine_number().get(3).getText(),"4.33","Value of 2nd major tick is not correct");

            //>>>>>>TC row no. - 243 : Labels for the number Line
            numberline.getTextbox_axisStartValue().clear();
            driver.switchTo().activeElement().sendKeys("5");//Type the value in axis start value
            numberline.getTextbox_endValue().clear();
            driver.switchTo().activeElement().sendKeys("15");//Type the value in end value
            driver.findElement(By.xpath("/html/body")).click();

            String startValue = numberline.getListNumberLine_number().get(0).getText();
            String endValue = numberline.getListNumberLine_number().get(1).getText();

            //Expected - 1 : Number line will labeled at the start and end value
            Assert.assertEquals(startValue,"5","Number line is not labeled at the start value");
            Assert.assertEquals(endValue,"15","Number line is not labeled at the end value");

            numberline.getTextbox_axisStartValue().clear();
            driver.switchTo().activeElement().sendKeys("1.1");//type the value in axis start value
            driver.findElement(By.xpath("/html/body")).click();

           List<WebElement> all = numberline.getListNumberLine_number();

            //Expected - 2 : The labels on the number line should be upto a maximum of 3 decimal places
            for(WebElement l:all){
                String number = l.getText();
                if(number.contains(".")){
                    int decimal = number.indexOf(".");
                    String subString = number.substring(decimal+1);
                    if(subString.length()>3){
                        Assert.fail("The labels on the number line are more than 3 decimal places");
                    }
                }

            }

            //>>>>>>TC row no. - 276,277 : Instructor should be able to Modify the major ticks and minor ticks value
            numberline.getTextbox_axisStartValue().clear();
            driver.switchTo().activeElement().sendKeys("5");//Type the axis start value
            numberline.getTextbox_endValue().clear();
            driver.switchTo().activeElement().sendKeys("10");//Type the end value
            numberline.getTextbox_majorTicks().clear();
            driver.switchTo().activeElement().sendKeys("4");//Type the major ticks value
            numberline.getTextbox_minorTicks().clear();
            driver.switchTo().activeElement().sendKeys("4");//Type the minor ticks value
            driver.findElement(By.xpath("//html/body")).click();

            //Expected - 1 : Major and Minor ticks must be reflected on numberline
            if(numberline.getMajorTicks().size()!=6)
            {
                Assert.fail("Major ticks value is not reflected on numberline");
            }

            if(numberline.getMinorTicks().size()!=20)
            {
                Assert.fail("MMinor ticks value is not reflected on numberline");
            }

           //>>>>>>TC row no. - 246 : Instructor should be able to enter annotations

            //Expected : Add annotation link should be displayed
            Assert.assertEquals(numberline.getLink_addAnnotation().getText().trim(), "Add Annotation", " 'Add Annotation' link is not present");

            //>>>>>>TC row no. - 247 : On click on add annotation link
            numberline.getLink_addAnnotation().click();//Click on "Add Annotation link"

            //Expected : Image / Text / MathML options should be displayed
            // Verifying Image option
            boolean imageFound = false;
            try{
                numberline.getList_annotationImage().get(1);
                imageFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(imageFound,true,"Image option is not displayed");

            //Verifying Text option
            boolean textFound = false;
            try{
                numberline.getList_annotationText().get(1);
                textFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(textFound,true,"Text option is not displayed");

            //Verifying MathML option
            boolean mathMLFound = false;
            try{
                numberline.getList_annotationMathML().get(1);
                mathMLFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(mathMLFound,true,"MathML option is not displayed");

            //>>>>>>TC row no. - 248 : Instructor should be able to Click on Text and enter the text for annotation
            numberline.getList_annotationText().get(1).click();
            driver.switchTo().activeElement().sendKeys("Annotation 1");//Type the text
            driver.findElement(By.xpath("/html/body")).click();

            //Expected - 1 : The entered text should be displayed as annotation
            Assert.assertEquals(numberline.getAnnotationContent().getText(),"Annotation 1","The entered text is not displayed as annotation");

            numberline.getAnnotationContent().click();//Click on added annotation
            numberline.getDrag_annotation().click();//click on drag button
            ac.dragAndDrop(numberline.getDrag_annotation(), numberline.getDrop_annotation()).build().perform();//Drop the annotation to canvas

            //Expected - 2 : Instructor should be able to drag and drop the annotation on to the canvas
            Assert.assertEquals(numberline.getList_droppedAnnotation().get(0).getText(), "Annotation 1", "Text Annotation is not dragged and dropped properly");

            //>>>>>>TC row no. - 249 : Instructor should be able to Click on MathML and enter the MathML as annotation
            numberline.getLink_addAnnotation().click();//Click on 'Add Annotation' link
            numberline.getList_annotationMathML().get(1).click();

            //Expected - 1 : MathML pop up should be opened
            Assert.assertEquals(numberline.getMathML_popup().isDisplayed(),true,"MathML pop is not displayed");

            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","25");//Type the equation
            numberline.getAnnotationContent().click();//Click on added annotation
            String mathMLAnnotationText = numberline.getAnnotationContent().getText().replaceAll("[\\t\\n\\r]"," ");

            //Expected - 2 : The formulated equation must be saved as annotation
            if(!mathMLAnnotationText.contains("25"))
            {
                Assert.fail("The formulated equation is not saved");
            }
            numberline.getDrag_annotation().click();//Click on drag button
            ac.dragAndDrop(numberline.getDrag_annotation(),numberline.getDrop_annotation()).build().perform();
            Thread.sleep(2000);

            //Expected - 3 : Instructor must be able to drag and drop the annotation on to the canvas
            if(!numberline.getList_droppedAnnotation().get(1).getText().replaceAll("[\\t\\n\\r]","").contains("25")){
                Assert.fail("MathML Annotation is not dragged and dropped properly");
            }

            //>>>>>>TC row no - 250 : Instructor should be able to Click on Image upload and upload the image as annotation
            numberline.getLink_addAnnotation().click();//Click on 'Add Annotation' link
            numberline.getList_annotationImage().get(1).click();

            boolean imagePopUpIsDisplayed= numberline.getImage_popup().isDisplayed();

            //Expected - 1 : Image upload pop up should be displayed
            Assert.assertEquals(imagePopUpIsDisplayed,true,"Image pop up is not displayed");

            //Expected - 2 : Instructor should be able to upload the image
            new QuestionCreate().fileUploadInQuestionCreation("288");//Upload an image
            Thread.sleep(10000);

           //Expected - 3 : The image must be saved as an annotation
           if(numberline.getImageAnnotation().getAttribute("src").length() == 0)
          {
              Assert.fail("Image is not saved as annotation");
          }

            numberline.getAnnotationContent().click();//Click on added annotation
            numberline.getDrag_annotation().click();//Click on drag button
            ac.dragAndDrop(numberline.getDrag_annotation(),numberline.getDrop_annotation()).build().perform();

            //Expected - 4 : Instructor should be able to drag and drop the annotation on to the canvas
            Assert.assertEquals(numberline.getImage_droppedAnnotation().getAttribute("src"),numberline.getImageAnnotation().getAttribute("src"),"Image annotation is not dragged and dropped properly");

        }catch(Exception e)
        {
            Assert.fail("Exception in testcase 'definingCorrectAnswerChoiceDragAndDrop' in class 'NumberLine'", e);
        }
    }


    @Test(priority=4,enabled = true)
    //TC row no. - 252 : Instructor should be able to save the question by clicking on the save button
    public void parametersAndSave(){
        try{Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            CreateAssignment createAssessment =PageFactory.initElements(driver,CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver,TLOListPage.class);
            QuestionTypesPage questionTypesPage =PageFactory.initElements(driver,QuestionTypesPage.class);
            NumberLineQuestionCreation numberline = PageFactory.initElements(driver,NumberLineQuestionCreation.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            AssessmentQuestionPage assessmentQuestionPage = PageFactory.initElements(driver,AssessmentQuestionPage.class);
            PreviewPage previewPage = PageFactory.initElements(driver,PreviewPage.class);

            String appendChar = "a";
            int dataIndex = 559;
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getButton_create().click();//Click on create new assessment
            tloListPage.getButton_create().click();//Click on create button
            questionTypesPage.getIcon_NumberLine().click();//Click on Number Line question type
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            closeHelp();

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name

            numberline.getSave_button().click();//Click on Save button
            Thread.sleep(2000);

            //Expected - 2 :  Questions cannot be saved without question body and answer choices assigned on the number line
            Assert.assertEquals(numberline.getError_savingQuestion().getText(),"Question title should not be empty","error message is not displayed when question is not present");

            numberline.getTextBox_QuestionField().clear();
            numberline.getTextBox_QuestionField().sendKeys(questiontext);//Enter the question

            numberline.getSave_button().click();//Click on Save button

            Assert.assertEquals(numberline.getError_savingQuestion().getText(),"Enter the answer choice.","error message is not displayed when answer choice is not present");

            List<WebElement> answerList = numberline.getList_enterAnswerChoice();
            answerList.get(0).click();//click on 1st answer option
            numberline.getAnswerchoice_text().click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 1");//Type the answer

            numberline.getLink_addNewAnswerChoice().click();
            answerList.get(1).click();//click on 2nd answer option
            numberline.getAnswerchoice_text().click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 2");//Type the answer

            numberline.getSave_button().click();//Click on Save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("footer-notification-text")));

            Assert.assertEquals(numberline.getError_savingQuestion().getText(),"Drag and drop the answer choice.","error message is not displayed id answer choice is not dropped");

            numberline.getAnswerChoice_1st().click();//Click on 1st added answer choice
            closeHelp();

            Actions ac = new Actions(driver);
            ac.dragAndDrop(numberline.getDrag_answerChoice(),numberline.getList_dropClass().get(0)).build().perform();

            numberline.getSave_button().click();//Click on Save button
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//span[text()='Saved.']"))));

            //Expected - 1 : Question should be saved on clicking save
            Assert.assertEquals(numberline.getError_savingQuestion().getText(),"Saved.","Question is not saved");
            Assert.assertEquals(numberline.getTextBox_QuestionField().getText(),questiontext,"Question is not saved");

            //>>>>>>TC row no. - 252 : Parameters to be followed
            String winHandleBefore = driver.getWindowHandle();

            new Assignment().submitPreviewQuestion();

            //Expected - 1 : One question should carry 1 Point
            Assert.assertEquals(previewPage.getQuestionPoint_previewPage().getAttribute("value"),"1","Question is not carrying one point");

            driver.close();
            driver.switchTo().window(winHandleBefore);//Switch the driver control back to main window

            numberline.getTextbox_solution().clear();
            driver.switchTo().activeElement().sendKeys("Solution Text");
            numberline.getTextbox_hint().clear();
            driver.switchTo().activeElement().sendKeys("Hint Text");

            //Expected - 2 : Questions should have solution and hint as other question types
            Assert.assertEquals(numberline.getTextbox_solution().getText(),"Solution Text","Solution text box is not displayed");
            Assert.assertEquals(numberline.getTextbox_hint().getText(),"Hint Text","Hint text box is not displayed");

            Select select = new Select(numberline.getDifficulty_level());

            //Expected - 3 : Instructor should be able to tag the question with different levels of difficulty
            select.selectByVisibleText("Easy");
            select.selectByVisibleText("Medium");
            select.selectByVisibleText("Hard");
            Thread.sleep(2000);

            numberline.getLearningObjective().click();//Click on learning objective

            //Expected - 4 : Instructor should be able to tag the question with LOs
            if(!numberline.getList_tloElo_learningObjective().get(0).getText().trim().contains("1.OA"))
            {
                Assert.fail("Expected TLO is not displayed");
            }

            if(!numberline.getList_tloElo_learningObjective().get(1).getText().trim().contains("1.OA.A.1"))
            {
                Assert.fail("Expected ELO is not displayed");
            }

            int defaultListSize = numberline.getList_tloElo_learningObjective().size();

            if(defaultListSize!=2)
            {
                Assert.fail("Learning Objective list size is not equal to 2");
            }

            numberline.getAdd_learningObjetive().click();//Click on add Learning Objective link
            numberline.getCheckbox_elo1_OA_A_2().click();//Click on 1.OA.A.2 ELO
            numberline.getButton_associate().click();//Click on Associate button
            Thread.sleep(2000);

            numberline.getLearningObjective().click();//Click on learning objective
            numberline.getLearningObjective().click();//Click on learning objective
            Thread.sleep(2000);

            if(!numberline.getList_tloElo_learningObjective().get(2).getText().trim().contains("1.OA.A.2"))
            {
                Assert.fail("Expected ELO is not displayed");
            }

            int listSizeAfterAddingTLO = numberline.getList_tloElo_learningObjective().size();

            if(listSizeAfterAddingTLO!=3)
            {
                Assert.fail("Learning Objective list size is not equal to 3");
            }
            numberline.getCloseButton_learningObjective().click();//Close the learning objective

            //>>>>>>TC row no. - 256 :  Instructor question short view
            numberline.getButton_review().click();//Click on Review button

            //Expected - 1 :  Only question title should be displayed
            if(!listPage.getCreateAssignmentQuestionName().getText().contains(questiontext)){
                Assert.fail("Question title is not displayed");
            }

            //Expected - 2 : Question Type should be displayed as “Number line”
            Assert.assertEquals(listPage.getQuestionType().getText(),"Numberline","Question type is not isplayed");

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

            //>>>>>>TC row no. - 258 : Instructor question expanded view
            //Expected - 1 :  Question body must be displayed
            if(!str1.trim().contains(str2)){
                Assert.fail("The particular question is not opened.");
            }
            //Expected - 2 : Number line must be displayed
            Assert.assertEquals(numberline.getNumberLine().isDisplayed(),true,"Number line is not displayed");

            //Expected - 3 : Answer choices must be displayed on the left
            Assert.assertEquals(numberline.getList_enterAnswerChoice().get(0).isDisplayed(),true,"Answer choice is not displayed");


            //>>>>>>TC row no. - 255 : Instructor should be able to edit by clicking Edit link
            assignments.getEditLink().click();//Click on Edit link

            //Expected - 1 : The question should be populated with all the values
            Assert.assertEquals(numberline.getTextBox_QuestionField().getText(),questiontext,"question name is not populated correctly");
            Assert.assertEquals(numberline.getList_enterAnswerChoice().get(0).getText(),"Answer 1","Answer choice is not populated correctly");
            Assert.assertEquals(numberline.getTextbox_axisStartValue().getAttribute("value"),"0","Axis atart value is not populated correctly");
            Assert.assertEquals(numberline.getTextbox_endValue().getAttribute("value"),"10","End value is not populated correctly");
            Assert.assertEquals(numberline.getTextbox_majorTicks().getAttribute("value"),"9","Major ticks value is not populated correctly");
            Assert.assertEquals(numberline.getTextbox_minorTicks().getAttribute("value"),"3","Minor ticks value is not populated correctly");
            Assert.assertEquals(numberline.getList_droppedAnswerChoice().get(0).getText(),"Answer 1","Answer choice is not dropped properly");

            numberline.getLearningObjective().click();//Click on learning objective
            if(!numberline.getList_tloElo_learningObjective().get(0).getText().trim().contains("1.OA"))
            {
                Assert.fail("Expected TLO is not displayed");
            }

            if(!numberline.getList_tloElo_learningObjective().get(1).getText().trim().contains("1.OA.A.1"))
            {
                Assert.fail("Expected ELO is not displayed");
            }

            if(!numberline.getList_tloElo_learningObjective().get(1).getText().trim().contains("1.OA.A.1"))
            {
                Assert.fail("Expected ELO is not displayed");
            }

            if(listSizeAfterAddingTLO!=3)
            {
                Assert.fail("Learning Objective list size is not equal to 3");
            }
            numberline.getCloseButton_learningObjective().click();//Close the learning objective

            numberline.getTextBox_QuestionField().clear();
            driver.switchTo().activeElement().sendKeys(questiontext + "1");//Edit the question
            driver.findElement(By.xpath("/html/body"));
            numberline.getList_answerChoiceToDragOrDelete().get(1).click();
            numberline.getDelete_answerChoice().click();//Delete the 2nd answer choice
            numberline.getSave_button().click();//Click on Save button
            Thread.sleep(2000);
            Assert.assertEquals(numberline.getError_savingQuestion().getText(),"Saved.","Question is not saved after edit");

            //Expected - 2 : Instructor should be able to edit the question body and the answer choices
            //Expected - 3 : Instructor should be able to Make the changes just like any other question
            //Expected - 4 : Clicking on save after editing the question should reflect all the changes made
            Assert.assertEquals(numberline.getTextBox_QuestionField().getText(),questiontext+"1","Question is not saved");

            List<WebElement> listAnswers = numberline.getList_enterAnswerChoice();
            boolean value = false;
            for(WebElement listValues:listAnswers)
            {
                if(listValues.getText()=="Answer 2")
                {
                   value = true;
                    break;
                }
            }
            Assert.assertEquals(value,false,"Deleted answer choice is not saved.It is still appearing");

            //>>>>>>TC row no. - 257 : Instructor question short view
            numberline.getButton_review().click();//Click on review button
            listPage.getButton_addMore().click();//Click on Add more button
            tloListPage.getButton_create().click();//Click on create button
            questionTypesPage.getIcon_NumberLine().click();//Click on Number Line question type
            Thread.sleep(2000);
            numberline.getTextBox_QuestionField().sendKeys(questiontext+"2");//Enter the question
            closeHelp();
            answerList.get(0).click();//click on 1st answer option
            numberline.getAnswerchoice_text().click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 2");//Type the question
            driver.findElement(By.xpath("//html/body")).click();
            Thread.sleep(2000);
            numberline.getAnswerChoice_1st().click();//Click on 1st answer choice
            ac.dragAndDrop(numberline.getDrag_answerChoice(), numberline.getList_dropClass().get(0)).build().perform();//Drop 1st answer choice on same (0th) number line

            numberline.getSave_button().click();//Click on Save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));
            numberline.getButton_review().click();//Click on Review button

            listPage.getList_dragQuestion().get(1).click();//Click on drag area
            ac.dragAndDrop(listPage.getList_dragQuestion().get(1),listPage.getList_dragQuestion().get(0)).build().perform();

            //Expected - 6 : Re-ordering in list view should be supported
            Assert.assertEquals(listPage.getList_questionBody().get(0).getText(),"Q1: "+questiontext+"2","Questions are not re ordered");
            Assert.assertEquals(listPage.getList_questionBody().get(1).getText(),"Q2: "+questiontext+"1","Questions are not re ordered");
        }
        catch(Exception e){
            Assert.fail("Exception in testcase 'parametersAndSave' in class 'NumberLine'", e);
        }

    }

    @Test(priority = 5,enabled = true)
    //TC row no. - 256 : Instructor should be able to Preview the question and The question should appear just like it appears on the student's side
    public void preview(){
        try{Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            CreateAssignment createAssessment =PageFactory.initElements(driver,CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver,TLOListPage.class);
            QuestionTypesPage questionTypesPage =PageFactory.initElements(driver,QuestionTypesPage.class);
            NumberLineQuestionCreation numberline = PageFactory.initElements(driver,NumberLineQuestionCreation.class);
            PreviewPage previewPage = PageFactory.initElements(driver,PreviewPage.class);
            String appendChar = "a";
            int dataIndex = 845;
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
            questionTypesPage.getIcon_NumberLine().click();//Click on Number Line question type
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            closeHelp();

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name

            numberline.getTextBox_QuestionField().clear();
            numberline.getTextBox_QuestionField().sendKeys(questiontext);//Enter the question

            List<WebElement> answerList = numberline.getList_enterAnswerChoice();
            answerList.get(0).click();//click on 1st answer option
            numberline.getAnswerchoice_text().click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 1");//Type the question

            answerList.get(0).click();//click on 2nd answer option
            numberline.getAnswerchoice_text().click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 2");//Type the question

            numberline.getLink_addAnnotation().click();//Click on Add annotation link
            numberline.getList_annotationText().get(1).click();
            driver.switchTo().activeElement().sendKeys("Annotation 1");//Type the text
            driver.findElement(By.xpath("//html/body")).click();

            Actions ac = new Actions(driver);

            numberline.getAnnotationContent().click();//Click on added annotation
            Thread.sleep(10000);
            ac.dragAndDrop(numberline.getDrag_annotation(), numberline.getDrop_annotation()).build().perform();//Drop the annotation to canvas

            numberline.getList_answerChoiceToDragOrDelete().get(1).click();//Click on 2nd added answer choice
            ac.dragAndDrop(numberline.getDrag_answerChoice(),numberline.getList_dropClass().get(0)).build().perform();


            numberline.getAnswerChoice_1st().click();//Click on 1st added answer choice
            ac.dragAndDrop(numberline.getDrag_answerChoice(),numberline.getList_dropClass().get(1)).build().perform();

            numberline.getSave_button().click();//Click on Save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));
            numberline.getButton_preview().click();//Click on Preview page
            Thread.sleep(2000);

            //Expected - 1 : Clicking on preview should open a new tab
            String winHandleBefore = driver.getWindowHandle();
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            String questionTextPreviewPage = previewPage.getQuestion_text().getText();
            String answerChoice1TextPreviewPage = previewPage.getList_answerChoice().get(0).getText();
            String answerChoice2TextPreviewPage = previewPage.getList_answerChoice().get(1).getText();
            String annotationPreviewPage = numberline.getList_droppedAnnotation().get(0).getText();
            String startNumberPreviewPage = numberline.getListNumberLine_number().get(0).getText();
            String endNumberPreviewPage = numberline.getListNumberLine_number().get(1).getText();

            driver.close();
            driver.switchTo().window(winHandleBefore);
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign to class

            new Navigator().logout();//logout

            new Login().loginAsStudent(appendChar,dataIndex);//Login as student
            new Navigator().navigateTo("assignment");//Navigate to Assessments page

            assessments.getAssignment().click();//Select the assignment

            //Expected - 2 : The question should appear just like a student would view
            Assert.assertEquals(previewPage.getQuestion_text().getText(),questionTextPreviewPage,"Question is not appearing as in preview page on instructor side");
            Assert.assertEquals(previewPage.getList_answerChoice().get(0).getText(),answerChoice1TextPreviewPage,"Answer Choice 1 is not appearing as in preview page on instructor side");
            Assert.assertEquals(previewPage.getList_answerChoice().get(1).getText(),answerChoice2TextPreviewPage,"Answer Choice 2 is not appearing as in preview page on instructor side");

            //>>>>>>TC row no. - 259 : Student question attempt view, student should be able to see the number line initialized by the instructor

           //Expected - 1 : The number line initialized by the instructor must be seen
            Assert.assertEquals(numberline.getListNumberLine_number().get(0).getText(),startNumberPreviewPage,"Start Number line is not appearing as in preview page on instructor side");
            Assert.assertEquals(numberline.getListNumberLine_number().get(1).getText(),endNumberPreviewPage,"End Number line is not appearing as in preview page on instructor side");

           //Expected - 2 : All the annotations used be displayed (if any)
            Assert.assertEquals(numberline.getList_droppedAnnotation().get(0).getText(),annotationPreviewPage,"Annotation is not appearing as in preview page on instructor side");
            Thread.sleep(8000);
            ac.dragAndDrop(numberline.getList_enterAnswerChoice().get(0),numberline.getList_dropClass().get(0)).build().perform();

            //Expected - 4 : Student should be able to drag and drop the answer choices on to the number line
            Assert.assertEquals(previewPage.getList_answerChoice().get(0).getText(),"Answer 1","Answer choice is not dragged and dropped");

            ac.dragAndDrop(previewPage.getList_answerChoice().get(1),assessments.getAnswerChoice_leftPanel()).build().perform();//Drop to left panel

            //Expected - 6 : Student should not be able to drop the object anywhere else on the canvas apart from the number line
            if(assessments.getAnswerChoice_leftPanel().getText().equals("Answer 2"))
            {
                Assert.fail("answer choice is dropped outside the number line");
            }

            //>>>>>>TC row no. - 263 :  Student should be able to edit the answer
            numberline.getList_droppedAnswerChoice().get(0).click();//Click on  dropped answer choice

            //Expected - 1 : Clicking on the dropped answer choice should display a red cross
            Assert.assertEquals(numberline.getRemove_droppedObject().isDisplayed(),true,"Red cross is not displayed on dropped answer choice");

            numberline.getRemove_droppedObject().click();//Delete dropped answer choice

            boolean elementFound = false;
            try{
                driver.findElement(By.cssSelector("div[class = 'numline-question-drag ui-draggable']"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Thread.sleep(2000);

            //Expected - 2 : Clicking on the red cross should delete the answer from the number line, and the answer choice should display on the left side again
            Assert.assertEquals(elementFound,false,"Dropped object is not deleted");

            ac.dragAndDrop(numberline.getList_enterAnswerChoice().get(0),numberline.getList_dropClass().get(0)).build().perform();//Drop the 1st answer choice to 0th index

           //Expected - 3 :  Student should be able to drag and drop the answer choices from the number line itself
            ac.dragAndDrop(numberline.getList_droppedAnswerChoice().get(0),numberline.getList_dropClass().get(1)).build().perform();//Shift the dropped answer choice to 1st index(correct answer)

            if((!assessments.getDroppedAnswerChoice_1stIndex().getText().equals("Answer 1") && (assessments.getDroppedAnswerChoice_1stIndex().getText().length()!=0)))
            {
            Assert.fail("Answer Choice is not dragged and dropped from the number line itself");
            }

            ac.dragAndDrop(numberline.getList_enterAnswerChoice().get(1),numberline.getList_dropClass().get(2)).build().perform();//Drop the answer to 2nd index(incorrect answer)

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
            assessments.getFinal_submit().click();//Click on submit button

            assessments.getButton_continue().click();//Click on Continue button

            //>>>>>>TC row no . - 266 : Student's evaluation(Partial correct)

           //Expected - 1 : Student should get 1 point for every correct answer and 0 point for every wrong answer
            Assert.assertEquals(assessments.getScore().getAttribute("value"),"0.5","Score is not correct");

            //>>>>>>TC row no. - 270 : Student report view
            //Expected - 1 :  Number line must be displayed with the answers given by him
            Assert.assertEquals(assessments.getDroppedAnswerChoice_2ndIndex().getText(),"Answer 2","Number line is not displayed with the answers given by him");
            Assert.assertEquals(assessments.getDroppedAnswerChoice_1stIndex().getText(),"Answer 1","Number line is not displayed with the answer given by him");
            assessments.getLink_showCorrectAnswer().click();//Click on show correct answer link

            //Expected - 2 : Clicking on show correct answer link must show the correct answers
            Assert.assertEquals(assessments.getDroppedAnswerChoice_0thIndex().getText(),"Answer 2","show correct answer link is not displayed the correct answers");
            Assert.assertEquals(assessments.getDroppedAnswerChoice_1stIndex().getText(),"Answer 1","show correct answer link is not displayed the correct answers");

            assessments.getLink_showYourAnswer().click();//Click on show your answer link

            //Expected - 3 : Clicking on the link 'Show your answer' should display the answers given by the student
            Assert.assertEquals(assessments.getDroppedAnswerChoice_2ndIndex().getText(),"Answer 2","Number line is not displayed with the answers given by him");
            Assert.assertEquals(assessments.getDroppedAnswerChoice_1stIndex().getText(),"Answer 1","Number line is not displayed with the answer given by him");
       }catch(Exception e)
        {
            Assert.fail("Exception in testcase 'preview' in class 'NumberLine'", e);
        }
    }


    @Test(priority = 6,enabled = true)
    //TC row no . - 266 : Student's evaluation(correct answer)
    public void studentEvaluationCorrectAnswer(){
        try{Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            CreateAssignment createAssessment =PageFactory.initElements(driver,CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver,TLOListPage.class);
            QuestionTypesPage questionTypesPage =PageFactory.initElements(driver,QuestionTypesPage.class);
            NumberLineQuestionCreation numberline = PageFactory.initElements(driver,NumberLineQuestionCreation.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            String appendChar = "a";
            int dataIndex = 1039;
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode=new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar, classCode, 1039);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getButton_create().click();//Click on create new assessment
            tloListPage.getButton_create().click();//Click on create button
            questionTypesPage.getIcon_NumberLine().click();//Click on Number Line question type
            closeHelp();

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name

            numberline.getTextBox_QuestionField().clear();
            numberline.getTextBox_QuestionField().sendKeys(questiontext);//Enter the question

            List<WebElement> answerList = numberline.getList_enterAnswerChoice();
            answerList.get(0).click();//click on 1st answer option
            numberline.getAnswerchoice_text().click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 1");//Type the question

            answerList.get(0).click();//click on 2nd answer option
            numberline.getAnswerchoice_text().click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 2");//Type the question

            numberline.getAnswerChoice_1st().click();//Click on 1st added answer choice
            Thread.sleep(5000);

            Actions ac = new Actions(driver);
            ac.dragAndDrop(numberline.getDrag_answerChoice(),numberline.getList_dropClass().get(0)).build().perform();//Drop the answer choice to 0th index

            numberline.getCheckbox_scratchpad().click();//Check the scratchpad checkbox

            numberline.getSave_button().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));

            numberline.getButton_review().click();//Click on Review button
            questionsListPage.getButton_saveForLater().click();//Click on Save for later button

            new Assignment().assignToStudent(dataIndex,appendChar);//Assign to class

            new Navigator().logout();//logout
            new Login().loginAsStudent(appendChar,dataIndex);//Login as student
            new Navigator().navigateTo("assignment");//Navigate to Assessments page

            assessments.getAssignment().click();//Select the assignment

            Thread.sleep(12000);

            ac.dragAndDrop(numberline.getList_enterAnswerChoice().get(0),numberline.getList_dropClass().get(0)).build().perform();//Drop the 1st answer choice to 0th index(correct answer)

            String str = new RandomString().randomstring(5);

            //>>>>>>TC row no. - 265 : Student should be able to use scratchpad, if it is enabled
            //Expected - 1 :  Clicking on the 'Show your work' should open the scratchpad
            //Expected - 2 :  Student should be able to use the scratchpad without any glitches
            new WriteBoard().enterTextInWriteBoard(str,dataIndex);

            assessments.getShowOrHideYourWork().click();//Click on Show your work

            boolean dataSaved =new WriteBoard().verifyWriteBoardDataIsSavedForNumberLine(str);
            Assert.assertEquals(dataSaved,true,"WriteBoard data is not saved");

            assessments.getShowOrHideYourWork().click();//Click on Show your work

            new WriteBoard().deleteWriteBoardData();
            boolean dataDeleted = new WriteBoard().verifyWriteBoardDataIsDeletedForNumberLine(str);
            Assert.assertEquals(dataDeleted,true,"WriteBoard data is not deleted");

            Thread.sleep(2000);
            assessments.getButton_submit().click();//Click on Submit button
            assessments.getFinal_submit().click();//Click on submit
            String percentage = new TextFetch().textfetchbycssselector("tspan");

            //Expected - Percentage should be 100%
            Assert.assertEquals(percentage,"100","Percentage is not 100%");

            assessments.getButton_continue().click();//Click on Continue button

            //Expected - 1 : Student should get 1 point for every correct answer
            Assert.assertEquals(assessments.getScore().getAttribute("value"),"1","Score is not 1 for correct answer");

        }catch(Exception e){
            Assert.fail("Exception in testcase 'studentEvaluationCorrectAnswer' in class 'NumberLine'", e);
        }
    }

    @Test(priority = 7,enabled = true)
    //TC row no . - 266 : Student's evaluation(partial correct non gradable)
    public void studentEvaluationPartialCorrectNonGradable(){
        try{Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            CreateAssignment createAssessment =PageFactory.initElements(driver,CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver,TLOListPage.class);
            QuestionTypesPage questionTypesPage =PageFactory.initElements(driver,QuestionTypesPage.class);
            NumberLineQuestionCreation numberline = PageFactory.initElements(driver,NumberLineQuestionCreation.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            String appendChar = "a";
            int dataIndex = 1119;
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode=new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar, classCode, 1119);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getButton_create().click();//Click on create new assessment
            tloListPage.getButton_create().click();//Click on create button
            questionTypesPage.getIcon_NumberLine().click();//Click on Number Line question type

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name

            numberline.getTextBox_QuestionField().clear();
            numberline.getTextBox_QuestionField().sendKeys(questiontext);//Enter the question

            List<WebElement> answerList = numberline.getList_enterAnswerChoice();
            answerList.get(0).click();//click on 1st answer option
            numberline.getAnswerchoice_text().click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 1");//Type the question

            answerList.get(0).click();//click on 2nd answer option
            numberline.getAnswerchoice_text().click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 2");//Type the question

            answerList.get(1).click();//click on 3rd answer option
            numberline.getAnswerchoice_text().click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 3");//Type the question

            Actions ac = new Actions(driver);

            Thread.sleep(8000);

            numberline.getList_answerChoiceToDragOrDelete().get(1).click();//Click on 2nd added answer choice
            ac.dragAndDrop(numberline.getDrag_answerChoice(),numberline.getList_dropClass().get(0)).build().perform();

            numberline.getAnswerChoice_1st().click();//Click on 1st added answer choice
            ac.dragAndDrop(numberline.getDrag_answerChoice(),numberline.getList_dropClass().get(1)).build().perform();
            Thread.sleep(3000);
            numberline.getSave_button().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));

            numberline.getButton_review().click();//Click on Review button
            questionsListPage.getButton_saveForLater().click();//Click on Save for later button

            new Assignment().assignToStudent(dataIndex,appendChar);//Assign to class

            new Navigator().logout();//logout
            new Login().loginAsStudent(appendChar,dataIndex);//Login as student
            new Navigator().navigateTo("assignment");//Navigate to Assessments page

            assessments.getAssignment().click();//Select the assignment

            Thread.sleep(8000);

            ac.dragAndDrop(numberline.getList_enterAnswerChoice().get(0),numberline.getList_dropClass().get(1)).build().perform();//Drop the 1st answer choice to 1st index(correct answer)
            ac.dragAndDrop(numberline.getList_enterAnswerChoice().get(1),numberline.getList_dropClass().get(2)).build().perform();//Drop the 2nd answer choice to 2nd index(incorrect answer)

            assessments.getButton_submit().click();//Click on Submit button
            assessments.getFinal_submit().click();//Click on submit

            String percentage = new TextFetch().textfetchbycssselector("tspan");

            //Expected - Percentage should be 50%
            Assert.assertEquals(percentage,"50","Percentage is not 50%");

            assessments.getButton_continue().click();//Click on Continue button

            boolean scoreFound= new BooleanValue().presenceOfElement(dataIndex,By.className("question-performance-score"));
            //Expected - 1 : Student should not get any point for given answer
            Assert.assertEquals(scoreFound,false,"Score board is appearing");

            //>>>>>>TC row no. 267 : Instructor grade book view
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar,dataIndex);
            new Navigator().navigateTo("assignment");//Navigate to Assessment page
            assessments.getView_grades().click();//click on view responses
            Thread.sleep(5000);
            assessments.getGrade_nonGradable().click();
            boolean scoreFoundInstructorside= new BooleanValue().presenceOfElement(dataIndex,By.className("question-performance-score"));

           //Expected - Marks should not be shown for non gradable assessment
            Assert.assertEquals(scoreFoundInstructorside,false,"Score board is appearing");

        }catch(Exception e){
            Assert.fail("Exception in testcase 'studentEvaluationPartialCorrectNonGradable' in class 'NumberLine'", e);
        }
    }


    @Test(priority = 8,enabled = true)
    //TC row no . - 266 : Student's evaluation(correct answer)
    public void studentEvaluationWrongAnswer(){
        try{Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            CreateAssignment createAssessment =PageFactory.initElements(driver,CreateAssignment.class);
            TLOListPage tloListPage = PageFactory.initElements(driver,TLOListPage.class);
            QuestionTypesPage questionTypesPage =PageFactory.initElements(driver,QuestionTypesPage.class);
            NumberLineQuestionCreation numberline = PageFactory.initElements(driver,NumberLineQuestionCreation.class);
            AssessmentQuestionsListPage questionsListPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);

            String appendChar = "a";
            int dataIndex = 1241;
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode=new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar, classCode, 1241);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");//Navigate to Assignment page
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getButton_create().click();//Click on create new assessment
            tloListPage.getButton_create().click();//Click on create button
            questionTypesPage.getIcon_NumberLine().click();//Click on Number Line question type
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            closeHelp();

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assessment name

            numberline.getTextBox_QuestionField().clear();
            numberline.getTextBox_QuestionField().sendKeys(questiontext);//Enter the question

            List<WebElement> answerList = numberline.getList_enterAnswerChoice();
            answerList.get(0).click();//click on 1st answer option
            numberline.getAnswerchoice_text().click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 1");//Type the question

            answerList.get(0).click();//click on 2nd answer option
            numberline.getAnswerchoice_text().click();//click on Text Type
            driver.switchTo().activeElement().sendKeys("Answer 2");//Type the question

            numberline.getAnswerChoice_1st().click();//Click on 1st added answer choice

            Actions ac = new Actions(driver);
            ac.dragAndDrop(numberline.getDrag_answerChoice(),numberline.getList_dropClass().get(0)).build().perform();//Drop the answer choice to 0th index

            numberline.getCheckbox_scratchpad().click();//Check the scratchpad checkbox

            numberline.getSave_button().click();//Click on save button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Saved.']")));

            numberline.getButton_review().click();//Click on Review button
            questionsListPage.getButton_saveForLater().click();//Click on Save for later button

            new Assignment().assignToStudent(dataIndex,appendChar);//Assign to class

            new Navigator().logout();//logout
            new Login().loginAsStudent(appendChar,dataIndex);//Login as student
            new Navigator().navigateTo("assignment");//Navigate to Assessments page

            assessments.getAssignment().click();//Select the assignment

            Thread.sleep(12000);

            ac.dragAndDrop(numberline.getList_enterAnswerChoice().get(0),numberline.getList_dropClass().get(1)).build().perform();//Drop the 1st answer choice to 1st index(incorrect answer)

            assessments.getButton_submit().click();//Click on Submit button
            assessments.getFinal_submit().click();//Click on submit
            Thread.sleep(2000);
            String percentage = new TextFetch().textfetchbycssselector("tspan");

            //Expected - Percentage should be 0%
            Assert.assertEquals(percentage,"0","Percentage is not 0%");

            assessments.getButton_continue().click();//Click on Continue button


            //Expected - 1 : Student should get 0 point for every wrong answer
            Assert.assertEquals(assessments.getScore().getAttribute("value"),"0","Score is not 0 for wrong answer");

            new Navigator().logout();//logout

           //>>>>>>TC row no. 267 : Instructor grade book view
            new Login().loginAsInstructor(appendChar,dataIndex);
            new Navigator().navigateTo("assignment");//Navigate to Assessment page
            assessments.getView_grades().click();//click on view grades
            assessments.getGrade_gradable().click();//Click on score assigned to student
            //Expected - 1 : The number line should be displayed with answers given by the student
            Assert.assertEquals(assessments.getDroppedAnswerChoice_1stIndex().getText(),"Answer 1","Number line is not displayed with answer given by the student");

            //>>>>>>TC row no. - 268 : Instructor should be able to view correct answer by clicking on 'Show correct answers'
            assessments.getLink_showCorrectAnswer().click();//Click on show correct answer

            //Expected - Correct answer should be displayed
            Assert.assertEquals(assessments.getDroppedAnswerChoice_0thIndex().getText(),"Answer 1","correct answer is not displayed");

            //Expected - Marked should be shown if the assessment is graded
            Assert.assertEquals(assessments.getScore().getAttribute("value"),"0","Mark is not displayed");

        }catch(Exception e){
            Assert.fail("Exception in testcase 'studentEvaluationWrongAnswer' in class 'NumberLine'", e);
        }
    }




}

