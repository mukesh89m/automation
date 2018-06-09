package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.questiontypes;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/*
 * Created by sumit on 20/11/14.
 */
public class CreateDifferentTypesOfQuestion extends Driver{

    @Test(priority = 1, enabled = true)
    public void createTrueFalseTypeQuestion()
    {
        try
        {
            String appendChar = "a";
            String filename = ReadTestData.readDataByTagName("", "filename", "1");
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver,QuestionTypesPage.class);
            TrueFalseQuestionCreation trueFalseQuestionCreation = PageFactory.initElements(driver,TrueFalseQuestionCreation.class);

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "1");

            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");//go to Assignments
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys(assessmentname);//give the assignment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create link
            createAssessment.getTabs_differentQuestionType().get(0).click();
            questionTypesPage.getIcon_TrueFalseType().click();//click on True/False type question
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            Thread.sleep(2000);
            trueFalseQuestionCreation.getTextField_EnterQuestionText().clear();//click on Question
            trueFalseQuestionCreation.getTextField_EnterQuestionText().sendKeys("True False Type Question");//type the question
            trueFalseQuestionCreation.getButton_True().click();//click on Answer Option A
            insertImageMathMlTable("1", filename);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase createTrueFalseTypeQuestion in class CreateDifferentTypesOfQuestion.", e);
        }
    }
    @Test(priority = 2, enabled = true)
    public void createMultipleChoiceTypeQuestion()
    {
        try
        {
            String appendChar = "a";
            String filename = ReadTestData.readDataByTagName("", "filename", "1");
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver,QuestionTypesPage.class);

            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");//go to Assignments
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys("Create MultipleChoiceTypeQuestion");//give the assignment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create link
            createAssessment.getTabs_differentQuestionType().get(0).click();
            questionTypesPage.getIcon_multipleChoice().click();//click on Multiple choice type question
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("question-mc-raw-content")));
            Thread.sleep(2000);
            new Click().clickByid("question-mc-raw-content");//Click on question
            driver.findElement(By.id("question-mc-raw-content")).sendKeys("Multiple Choice Type Question");//type the question
            List<WebElement> answerOptions = driver.findElements(By.xpath("//*[starts-with(@id, 'popupEditor_')]"));
            answerOptions.get(0).sendKeys("Option 1");
            answerOptions.get(1).sendKeys("Option 2");
            answerOptions.get(2).sendKeys("Option 3");
            answerOptions.get(3).sendKeys("Option 4");
            new Click().clickByid("question-mc-raw-content");//Click on question
            new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='pickfiles']")));
            Thread.sleep(2000);
            new KeysSend().sendKeyBoardKeys("$"+ Config.fileUploadPath+filename+"^");
            Thread.sleep(15000);
            new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
            new Click().clickByid("question-mc-raw-content");//click on Question
            new Click().clickBycssselector("a[class='re-icon re-matheditor redactor-btn-image']");//click on MathMl icon
            new QuestionCreate().enterValueInMathMLEditor("Square root","10");
            new Click().clickByid("question-mc-raw-content");//click on Question
            new Click().clickBycssselector("a[class='re-icon re-table']");//click on table icon
            new Click().clickBycssselector("a[class=' redactor_dropdown_insert_table']");//click on table icon
            new Click().clickByid("redactor_insert_table_btn");//click on Insert button
            new Select(driver.findElement(By.id("difficulty-level-drop-down"))).selectByVisibleText("Easy");//select difficulty level
            new Select(driver.findElement(By.id("difficulty-level-drop-down"))).selectByVisibleText("Medium");
            new Select(driver.findElement(By.id("difficulty-level-drop-down"))).selectByVisibleText("Hard");
            new TextSend().textsendbyid("Multiple Choice Solution Text","content-solution");//enter solution
            new TextSend().textsendbyid("Multiple Choice Hint Text","content-hint");//enter hint text

            new Click().clickByid("new-single-selection-choice");//click on Add new answer choice
            List<WebElement> answerOptions1 = driver.findElements(By.xpath("//*[starts-with(@id, 'popupEditor_')]"));
            answerOptions1.get(4).sendKeys("Option 5");
            new Click().clickByclassname("back-pointer-icon");//Click the ">" option on the right side of every option
            String dragIcon = driver.findElement(By.id("single-select-drag-icon")).getAttribute("src");
            if(!dragIcon.contains("drag.png"))
                Assert.fail("Grag icon is absent in rhs of answer option");
            String deleteIcon = driver.findElement(By.id("single-select-delete-icon")).getAttribute("src");
            if(!deleteIcon.contains("delete.png"))
                Assert.fail("Delete icon is absent in rhs of answer option");
            new Click().clickByid("single-select-delete-icon");//click on Delete icon
            new Click().clickBycssselector("div[class='single-select-choice-icon single-select-choice-icon-deselect']"); //select correct answer as A
            new Click().clickByid("saveQuestionDetails1");//click on Save button

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase createMultipleChoiceTypeQuestion in class CreateDifferentTypesOfQuestion.", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void createMultipleSelectTypeQuestion()
    {
        try
        {
            String appendChar = "a";

            String filename = ReadTestData.readDataByTagName("", "filename", "1");
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver,QuestionTypesPage.class);

            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment"); //go to Assignments
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys("Create MultipleSelectTypeQuestion");//give the assignment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create link
            createAssessment.getTabs_differentQuestionType().get(0).click();
            questionTypesPage.getIcon_multipleSelect().click();//click on Multiple select type question
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("question-ms-raw-content")));
            Thread.sleep(2000);
            new Click().clickByid("question-ms-raw-content");//click on Question
            driver.findElement(By.id("question-ms-raw-content")).sendKeys("Multiple Select type Question");//type the question
            List<WebElement> answerOptions = driver.findElements(By.xpath("//*[starts-with(@id, 'popupEditor_')]"));
            answerOptions.get(0).sendKeys("Option 1");
            answerOptions.get(1).sendKeys("Option 2");
            answerOptions.get(2).sendKeys("Option 3");
            answerOptions.get(3).sendKeys("Option 4");
            new Click().clickByid("question-ms-raw-content");//click on Question
            new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='pickfiles']")));
            Thread.sleep(2000);
            new KeysSend().sendKeyBoardKeys("$"+ Config.fileUploadPath+filename+"^");
            Thread.sleep(15000);
            new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
            new Click().clickByid("question-ms-raw-content");//click on Question
            new Click().clickBycssselector("a[class='re-icon re-matheditor redactor-btn-image']");//click on MathMl icon
            new QuestionCreate().enterValueInMathMLEditor("Square root","10");
            new Click().clickByid("question-ms-raw-content");//click on Question
            new Click().clickBycssselector("a[class='re-icon re-table']");//click on table icon
            new Click().clickBycssselector("a[class=' redactor_dropdown_insert_table']");//click on table icon
            new Click().clickByid("redactor_insert_table_btn");//click on Insert button
            new Select(driver.findElement(By.id("difficulty-level-drop-down"))).selectByVisibleText("Easy");//select difficulty level
            new Select(driver.findElement(By.id("difficulty-level-drop-down"))).selectByVisibleText("Medium");
            new Select(driver.findElement(By.id("difficulty-level-drop-down"))).selectByVisibleText("Hard");
            new TextSend().textsendbyid("Multiple Selection Solution Text","content-solution");//enter solution
            new TextSend().textsendbyid("Multiple Selection Hint Text","content-hint");//enter hint text

            new Click().clickByid("new-single-selection-choice");//click on Add new answer choice
            List<WebElement> answerOptions1 = driver.findElements(By.xpath("//*[starts-with(@id, 'popupEditor_')]"));
            answerOptions1.get(4).sendKeys("Option 5");
            new Click().clickByclassname("back-pointer-icon");//Click the ">" option on the right side of every option
            String dragIcon = driver.findElement(By.id("multiple-select-drag-icon")).getAttribute("src");
            if(!dragIcon.contains("drag.png"))
                Assert.fail("Grag icon is absent in rhs of multiple select answer option");
            String deleteIcon = driver.findElement(By.id("multiple-select-delete-icon")).getAttribute("src");
            if(!deleteIcon.contains("delete.png"))
                Assert.fail("Delete icon is absent in rhs of multiple select answer option");
            new Click().clickByid("multiple-select-delete-icon");//click on Delete icon
            List<WebElement> multipleSelections = driver.findElements(By.cssSelector("div[class='multiple-select-choice-icon multiple-select-choice-icon-deselect']"));
            multipleSelections.get(0).click(); multipleSelections.get(1).click(); //selecting option A and B as correct answers
            new Click().clickByid("saveQuestionDetails1");//click on Save button

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase createMultipleSelectTypeQuestion in class CreateDifferentTypesOfQuestion.", e);
        }
    }
    @Test(priority = 4, enabled = true)
    public void createTextEntryTypeQuestion()
    {
        try
        {
            String appendChar = "a";
            String filename = ReadTestData.readDataByTagName("", "filename", "1");
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver,QuestionTypesPage.class);

            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");//go to Assignments
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys("Create TextEntryTypeQuestion");//give the assignment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create link
            createAssessment.getTabs_differentQuestionType().get(0).click();
            questionTypesPage.getIcon_textEntry().click();//click on Text Entry type question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Multiple Select type Question");//type the question
            new TextSend().textsendbycssSelector("Correct Answer", "input[class='numeric_text_entry_input get-user-entry']");
            new Click().clickBycssselector("span[class='btn sty-green save-spanish-text accept_answer text_entry_accept_answer']");//click on Accept answer button
            boolean isSelected = driver.findElement(By.id("ignore-text-entry-checkbox")).isSelected();
            Assert.assertEquals(isSelected, true, "For text entry type question Ignore case check box is not checked by default");
            boolean isSelected1 = driver.findElement(By.id("allow-single-letter-mistake-entry-checkbox")).isSelected();
            Assert.assertEquals(isSelected1, false, "For text entry type question \"Allow single letter mistake\" not unchecked\" by default");
            new Click().clickByclassname("right-container-img");//click on + icon
            driver.findElements(By.cssSelector("input[class='numeric_text_entry_input get-user-entry']")).get(0).sendKeys("Alternate answer");//add alternate answer
            new Click().clickBycssselector("span[class='btn sty-green save-spanish-text accept_answer text_entry_accept_answer']"); //click on Accept answer button
            String boxValue = driver.findElement(By.className("visible_redactor_input")).getAttribute("value");
            Assert.assertEquals(boxValue, "Correct Answer,Alternate answer", "For text entry type question Correct Answer is not not reflected in the box. ");

            new Click().clickByid("question-raw-content");//click on Question
            new Click().clickBycssselector("a[class='re-icon re-textentry re-entry-box redactor-btn-image']");//click on textbox
            driver.findElements(By.cssSelector("input[class='numeric_text_entry_input get-user-entry']")).get(0).sendKeys("Correct Answer 1");
            new Click().clickbylistcssselector("span[class='btn sty-green save-spanish-text accept_answer text_entry_accept_answer']", 1); //click on Accept answer button
            new Click().clickByListClassName("right-container-img", 1);//click on + icon
            driver.findElements(By.cssSelector("input[class='numeric_text_entry_input get-user-entry']")).get(0).sendKeys("Alternate answer 1");//add alternate answer
            new Click().clickbylistcssselector("span[class='btn sty-green save-spanish-text accept_answer text_entry_accept_answer']", 1); //click on Accept answer button
            String boxValue1 = driver.findElement(By.className("visible_redactor_input")).getAttribute("value");
            Assert.assertEquals(boxValue1, "Correct Answer 1,Alternate answer 1", "For text entry type question Correct Answer is not not reflected in the box if there are multiple textboxes. ");
            insertImageMathMlTable("1", filename);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase createTextEntryTypeQuestion in class CreateDifferentTypesOfQuestion.", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void createTextDropDownTypeQuestion()
    {
        try
        {
            //TC row no. 8-10
            //Creating Text Dropdown Type of Questions
            //Deleting options in text dropdown questions
            //Inserting multiple "text drop down" entries
            String appendChar = "a";
            String filename = ReadTestData.readDataByTagName("", "filename", "1");
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver,QuestionTypesPage.class);

            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment"); //go to Assignments
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys("Create TextDropDownTypeQuestion");//give the assignment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create link
            createAssessment.getTabs_differentQuestionType().get(0).click();
            questionTypesPage.getIcon_textDropDown().click();//click on Text Entry Drop Down type question
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Text Entry Drop Down type Question");//type the question
            new Click().clickByclassname("text-drop-val");
            new Click().clickByclassname("text-drop-val");
            driver.switchTo().activeElement().sendKeys("Answer1");

            driver.findElements(By.className("text-drop-val")).get(1).click();
            driver.switchTo().activeElement().sendKeys("Answer2");

            driver.findElements(By.className("text-drop-val")).get(2).click();
            driver.switchTo().activeElement().sendKeys("Answer3");

            new Click().clickByXpath("//html/body");

            new Click().clickByclassname("text-drop-val");
            new Click().clickByclassname("select-icon-text-drop-down"); //select answer 1 as correct answer

            new Click().clickBycssselector("span[class='done-button btn sty-green text-drop-accept accept_answer']"); //click on accept answer button
            new Click().clickByid("add-new-text-drop-down-value");//click on +add new value

            driver.findElements(By.className("text-drop-val")).get(3).click();
            driver.findElements(By.id("ans1")).get(3).sendKeys("Answer4");
            new Click().clickByclassname("text-drop-val");//click on 1st answer choice
            new Click().clickByclassname("remove-icon-drop-down");//click on delete
            String value = new TextFetch().textfetchbyclass("text-drop-val");
            Assert.assertEquals(value, "Answer2", "Unable to delete a answer choice from text entry drop down.");
            new Click().clickByclassname("text-drop-val");//click on answer choice
            new Click().clickByclassname("select-icon-text-drop-down"); //select answer 2 as correct answer
            new Click().clickBycssselector("span[class='done-button btn sty-green text-drop-accept accept_answer']"); //click on accept answer button

            //Inserting multiple "text drop down" entries
            new Click().clickByid("question-raw-content");//click on Question
            new Click().clickBycssselector("a[class='re-icon re-textdropdown re-entry-box redactor-btn-image']");//click on text dropdown icon

            driver.findElements(By.className("text-drop-val")).get(3).click();
            driver.findElements(By.id("ans1")).get(3).sendKeys("First Answer");
            new Click().clickByListClassName("select-icon-text-drop-down", 3);//click on correct answer

            driver.findElements(By.className("text-drop-val")).get(4).click();
            driver.findElements(By.id("ans1")).get(4).sendKeys("Second Answer");

            driver.findElements(By.className("text-drop-val")).get(5).click();
            driver.findElements(By.id("ans1")).get(5).sendKeys("Third Answer");
            List<WebElement> all = driver.findElements(By.cssSelector("span[class='done-button btn sty-green text-drop-accept accept_answer']"));
            for(WebElement l: all)
            {
                System.out.println("-->"+l.getText());
            }

            new Click().clickbylistcssselector("span[class='done-button btn sty-green text-drop-accept accept_answer']" , 1);

            String text = new TextFetch().textfetchbyclass("question-raw-content-dropdown");
            if(!text.contains("First Answer")){
                Assert.fail("For text entry drop down type question Correct Answer is not reflected in the box if there are multiple drop down boxes.");
            }
            insertImageMathMlTable("1", filename);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase createTextDropDownTypeQuestion in class CreateDifferentTypesOfQuestion.", e);
        }
    }
    @Test(priority = 6, enabled = true)
    public void createNumericEntryWithUnitsTypeQuestion()
    {
        try
        {
            //TC row no. 11, 12
            //Creating Numeric Entry with Units question types
            //Inserting multiple "Numeric Entry with units"
            String appendChar = "a";
            String filename = ReadTestData.readDataByTagName("", "filename", "1");
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver,QuestionTypesPage.class);

            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");//go to Assignments
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys("Create NumericEntryWithUnitsTypeQuestion");//give the assignment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create link
            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_numericEntryWithUnits().click();//Click on 'Numeric Entry with units' question type
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Numeric Entry With Units ");//type the question

            new TextSend().textsendbycssSelector("10", "input[class='numeric_unit_correct_answer numeric_text_entry numeric_text_entry_input']");//enter correct answer

            new Click().clickBycssselector("div[class='unit-arrow-down add-more-num-entry-unit']"); //click on add more units
            new Click().clickBycssselector("li[value='feet']"); //select feet
            new Click().clickByclassname("unit_tick_image"); //click on the tick mark for unit
            Thread.sleep(2000);
            new Click().clickBycssselector("div[class='add-more-num-entry-unit unit-arrow-down']"); //click on add more units
            new Click().clickBycssselector("li[value='yards']"); //select yards
            new Click().clickbylistcssselector("span[class='done-button btn sty-green tab_event_widget num_ent_unit_accept_answer']", 0); //click on accept answer
            Thread.sleep(1000);
            new Click().clickbylistcssselector("span[class='done-button btn sty-green tab_event_widget num_ent_unit_accept_answer']", 0); //click on accept answer

            //Inserting multiple "Numeric Entry with units"
            new Click().clickByid("question-raw-content");//click on Question
            new Click().clickBycssselector("a[class='re-icon re-numericunit re-entry-box redactor-btn-image']");//click on Numeric Entry with units icon
            new TextSend().textsendbycssSelector("20", "input[class='numeric_unit_correct_answer numeric_text_entry numeric_text_entry_input']");//enter correct answer

            new Click().clickBycssselector("div[class='unit-arrow-down add-more-num-entry-unit']"); //click on add more units
            new Click().clickbylistcssselector("li[value='feet']", 2); //select feet
            new Click().clickByListClassName("unit_tick_image",2);
            // Click().clickByclassname("unit_tick_image"); //click on the tick mark for unit
            new Click().clickbylistcssselector("div[class='add-more-num-entry-unit unit-arrow-down']",1); //click on add more units
            new Click().clickbylistcssselector("li[value='yards']",2); //click on add more units
            new Click().clickbylistcssselector("span[class='done-button btn sty-green tab_event_widget num_ent_unit_accept_answer']",1); //click on accept answer
            String str = driver.findElement(By.className("visible_redactor_input")).getAttribute("value");
            Assert.assertEquals(str, "20", "Unable to insert multiple numeric entry with units boxes.");
            insertImageMathMlTable("1", filename);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase createNumericEntryWithUnitsTypeQuestion in class CreateDifferentTypesOfQuestion.", e);
        }
    }

    @Test(priority = 7, enabled = true)
    public void createAdvancedNumericTypeQuestion()
    {
        try
        {
            //TC row no. 13-15
            //Creating Advanced Numeric type questions
            //Alternate answers in "Advanced Numeric type"
            //Inserting multiple "Advanced Numeric Types" options
            String appendChar = "a";
            String filename = ReadTestData.readDataByTagName("", "filename", "1");
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver,QuestionTypesPage.class);

            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");//go to Assignments
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys("Create AdvancedNumericTypeQuestion");//give the assignment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create link
            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_advancedNumeric().click();//click on advanced numeric type question
            Thread.sleep(2000);
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Advanced Numeric question");//type the question

            new TextSend().textsendbycssSelector("10","input[class='numeric_correct_text_entry_input num-entry-ans border-color-gray']");

            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget accept_answer accept_answer_maple_numeric']"); //click on accept answer
            new Click().clickByclassname("num-entry-add-alternate-ans");//click on + icon to add alternate answer
            new TextSend().textsendbycssSelector("20", "input[class='numeric_alternate_text_entry_input num-entry-ans border-color-gray']");//enter alternate answer
            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget accept_answer accept_answer_maple_numeric']"); //click on accept answer
            String str = driver.findElement(By.className("visible_redactor_input")).getAttribute("value");
            Assert.assertEquals(str, "10,20", "Unable to insert answer option for advanced numeric type question.");

            //Inserting multiple "Advanced Numeric Types" options
            new Click().clickByid("question-raw-content");//click on Question
            new Click().clickBycssselector("a[class='re-icon re-maplenumeric re-entry-box redactor-btn-image']");//click on advanced numeric icon
            driver.findElements(By.cssSelector("input[class='numeric_correct_text_entry_input num-entry-ans border-color-gray']")).get(1).sendKeys("30");//enter correct answer
            new Click().clickByListClassName("num-entry-add-alternate-ans", 1);//click on + icon to add alternate answer
            driver.findElements(By.cssSelector("input[class='numeric_alternate_text_entry_input num-entry-ans border-color-gray']")).get(1).sendKeys("40");//enter alternate answer
            new Click().clickbylistcssselector("span[class='done-button btn sty-green tab_event_widget accept_answer accept_answer_maple_numeric']", 1); //click on accept answer
            String str1 = driver.findElement(By.className("visible_redactor_input")).getAttribute("value");
            Assert.assertEquals(str1, "30,40", "Unable to insert multiple \"Advanced Numeric Types\" option for advanced numeric type question.");
            insertImageMathMlTable("1", filename);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase createAdvancedNumericTypeQuestion in class CreateDifferentTypesOfQuestion.", e);
        }
    }

    @Test(priority = 8, enabled = true)
    public void createExpressionEvaluatorTypeQuestion()
    {
        try
        {
            //TC row no. 16-18
            //Expression Evaluator type question creation
            //Alternate answers in "Expression Evaluator type"
            //Inserting multiple "Expression Evaluator type"
            String filename = ReadTestData.readDataByTagName("", "filename", "1");
            String appendChar = "a";
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver,QuestionTypesPage.class);

            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");//go to Assignments
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys("Create ExpressionEvaluatorTypeQuestion");//give the assignment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create link
            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_expressionEvaluator().click();//click on expression evaluator type question
            Thread.sleep(2000);
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Expression Evaluator question");//type the question

            driver.findElement(By.xpath("//html/body")).click();
            new Click().clickByid("answer_math_edit");
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root", "5");

            new Click().clickByclassname("add-new-alternate-ans");//click on + icon to add alternate answer

            driver.findElement(By.xpath("//html/body")).click();
            new Click().clickbylistid("answer_math_edit", 2);
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root", "6");

            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget accept_answer maple_symc_not_accept_answer']"); //click on accept answer
            String str = new TextFetch().textfetchbycssselector("div[class='maple-ques-inner-container']");
            if(!str.contains("5"))
                Assert.fail("Unable to create expression Evaluator type question.");

            if(!str.contains("6"))
                Assert.fail("Alternate answer is not present in question textbox of Expression Evaluator type question.");

            //Inserting multiple "Expression Evaluator type"
            new Click().clickByid("question-raw-content");//click on Question
            new Click().clickBycssselector("a[class='re-icon re-maplesymbolic re-entry-box redactor-btn-image']");//click on expression evaluator icon

            driver.findElement(By.xpath("//html/body")).click();
            new Click().clickbylistid("answer_math_edit", 4);
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root", "1");

            new Click().clickByListClassName("add-new-alternate-ans", 1);//click on + icon to add alternate answer

            new Click().clickbylistid("answer_math_edit", 6);
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root", "2");

            new Click().clickbylistcssselector("span[class='done-button btn sty-green tab_event_widget accept_answer maple_symc_not_accept_answer']", 1); //click on accept answer

            String str1 = new TextFetch().textfetchbycssselector("div[class='maple-ques-inner-container']");
            if(!str1.contains("1"))
                Assert.fail("Unable to insert multiple textboxes in expression Evaluator type question.");

            insertImageMathMlTable("1", filename);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase createExpressionEvaluatorTypeQuestion in class CreateDifferentTypesOfQuestion.", e);
        }
    }

    @Test(priority = 9, enabled = true)
    public void createMatchTheFollowingTypeQuestion()
    {
        try
        {
            //TC row no 20 - 22
            String appendChar = "a";
            String filename = ReadTestData.readDataByTagName("", "filename", "1");
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver,QuestionTypesPage.class);

            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");//go to Assignments
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys("Create MatchTheFollowingTypeQuestion");//give the assignment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create link
            createAssessment.getTabs_differentQuestionType().get(2).click();//Click on ELA Tech Enhanced tab
            questionTypesPage.getIcon_matchTheFollowing().click();//click on Match the Following type question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Match the Following question text");//type the question

            List<WebElement> lhsboxes = driver.findElements(By.cssSelector("div[class='dnd-match-lhs box']")); //Fetching all the lhs boxes

            lhsboxes.get(0).click();
            lhsboxes.get(0).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("answer_choice_txt")));
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            lhsboxes.get(1).click();
            driver.findElement(By.id("answer_math_edit")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","5");


            lhsboxes.get(2).click();
            new Click().clickByid("answer_image_edit");
            new Click().clickByid("pickfiles");
            new KeysSend().sendKeyBoardKeys("$"+ Config.fileUploadPath+filename+"^");
            Thread.sleep(3000);
            new Click().clickByid("widget-createimage_start_queue");
            (new WebDriverWait(driver, 20))
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.id("widget-createimage_start_queue")));

            lhsboxes.get(3).click();
            driver.findElement(By.id("answer_math_edit")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root", "6");

            lhsboxes.get(4).click();
            driver.findElement(By.id("answer_math_edit")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root", "7");

            //Filling RHS

            List<WebElement> rhsboxes = driver.findElements(By.cssSelector("div[class='dnd-match-rhs box']")); //Fetching all the lhs boxes

            rhsboxes.get(0).click();
            driver.findElement(By.id("answer_math_edit")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root", "5");

            rhsboxes.get(1).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            rhsboxes.get(2).click();
            new Click().clickByid("answer_image_edit");
            new Click().clickByid("pickfiles");
            new KeysSend().sendKeyBoardKeys("$"+ Config.fileUploadPath+filename+"^");
            Thread.sleep(3000);
            new Click().clickByid("widget-createimage_start_queue");
            (new WebDriverWait(driver, 20))
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.id("widget-createimage_start_queue")));

            rhsboxes.get(3).click();
            driver.findElement(By.id("answer_math_edit")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root", "6");

            rhsboxes.get(4).click();
            driver.findElement(By.id("answer_math_edit")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root", "7");

            //TC row no. 20...Deleting options in the match the following type
            lhsboxes.get(0).click();//click on 1st row
            new Click().clickBycssselector("div[class='dnd-match-delete cursor-pointer']");//click on delete icon
            String str = new TextFetch().textfetchbycssselector("div[class='answer-con match label-to-control']");
            Assert.assertEquals(str, "1000", "Unable to delete a row from Match the following type of questions.");

            //TC row no. 21...."Add a new row" in "Match the following type"
            new Click().clickByid("add-new-row");//click on add new row
            int lhsBoxesCount = driver.findElements(By.xpath("//div[normalize-space(@class)='dnd-match-lhs box']")).size();
            Assert.assertEquals(lhsBoxesCount, 5, "Unable to add a new row in Match the following type of questions.");

            //TC row no. 22 ..."Add Distractor" option should be proper
            new Click().clickByid("add-distractor");//click on add distractor
            int rhsBoxesCount = driver.findElements(By.xpath("//div[normalize-space(@class)='dnd-match-rhs box']")).size();
            Assert.assertEquals(rhsBoxesCount, 6, "Unable to add a new row in RHS, \"Add Distractor\" option is not working properly in Match the following type of questions..");

            List<WebElement> lhsboxes1 = driver.findElements(By.xpath("//div[normalize-space(@class)='dnd-match-lhs box']")); //Fetching all the lhs boxes
            lhsboxes1.get(4).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            List<WebElement> rhsboxes1 = driver.findElements(By.xpath("//div[normalize-space(@class)='dnd-match-rhs box']"));
            rhsboxes1.get(4).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            rhsboxes1.get(5).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            insertImageMathMlTable("1", filename);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase createMatchTheFollowingTypeQuestion in class CreateDifferentTypesOfQuestion.", e);
        }
    }

    @Test(priority = 10, enabled = true)
    public void createDragAndDropTypeQuestion()
    {
        try
        {
            //TC row no 23-24
            //TC row no. 23--Creating Drag and Drop type questions
            String appendChar = "a";
            String filename = ReadTestData.readDataByTagName("", "filename", "1");
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver,QuestionTypesPage.class);

            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");//go to Assignments
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys("Create DragAndDropTypeQuestion");//give the assignment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create link
            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_dragAndDrop().click();//click on Drag and Drop type question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Drag and Drop question text" );//type the question

            List<WebElement> answers = driver.findElements(By.xpath("//li[starts-with(@id,'ans_ch')]"));
            answers.get(0).click();
            driver.findElement(By.id("answer_choice_edit")).click();
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.id("answer_choice_txt")));
            new TextSend().textsendbyid("1000", "answer_choice_txt");

            answers.get(1).click();
            driver.findElement(By.id("answer_math_edit")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root", "5");

            answers.get(2).click();
            new Click().clickByid("answer_image_edit");
            new Click().clickByid("pickfiles");
            new KeysSend().sendKeyBoardKeys("$"+ Config.fileUploadPath+filename+"^");
            Thread.sleep(3000);
            new Click().clickByid("widget-createimage_start_queue");
            (new WebDriverWait(driver, 20))
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.id("widget-createimage_start_queue")));

            answers.get(3).click();
            driver.findElement(By.id("answer_math_edit")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root", "10");

            List<WebElement> answerstodrag = driver.findElements(By.className("answer"));
            Actions ac = new Actions(driver);
            answerstodrag.get(0).click();
            ac.dragAndDrop(driver.findElement(By.id("ans-drag-btn")),driver.findElement(By.id("canvas"))).build().perform();
            String correctAnswer = new TextFetch().textfetchbycssselector("div[class='drag-ans ']");
            Assert.assertEquals(correctAnswer, "1000", "Unable to drag the correct answer in drag and drop type question..");

            //TC row no. 24...Deleting the correct answer
            new Click().clickByid("delete-drop-zone");//click on x icon to delete correct answer
            String text = new TextFetch().textfetchbyid("drop-area");
            if(text.length()>0)
            {
                Assert.fail("Unable to delete the correct answer of Drag and Drop type question.");
            }
            //drag correct answer
            List<WebElement> answerstodrag1 = driver.findElements(By.className("answer"));
            Actions ac1 = new Actions(driver);
            answerstodrag1.get(0).click();
            ac1.dragAndDrop(driver.findElement(By.id("ans-drag-btn")),driver.findElement(By.id("canvas"))).build().perform();
            insertImageMathMlTable("1", filename);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase createMatchTheFollowingTypeQuestion in class CreateDifferentTypesOfQuestion.", e);
        }
    }

    @Test(priority = 11, enabled = true)
    public void createEssayTypeQuestion()
    {
        try {
            //TC row no 25
            //Creating "essay type" questions
            String appendChar = "a";
            String filename = ReadTestData.readDataByTagName("", "filename", "1");
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver,QuestionTypesPage.class);

            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");//go to Assignments
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys("Create EssayTypeQuestion");//give the assignment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create link
            createAssessment.getTabs_differentQuestionType().get(0).click();//Click on Classic question types tab
            questionTypesPage.getIcon_essay().click();//click on Essay type question
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content")));
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Essay type question");//type the question
            new TextSend().textsendbyid("10", "essay-question-height");//enter line height
            insertImageMathMlTable("1", filename);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase createMatchTheFollowingTypeQuestion in class CreateDifferentTypesOfQuestion.", e);
        }
    }

    @Test(priority = 12, enabled = true)
    public void createLabelAnImageTypeQuestion()
    {
        try {
            //TC row no 26---Creating "Label an image (text) question"
            String appendChar = "a";
            String filename = ReadTestData.readDataByTagName("", "filename", "1");
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver,QuestionTypesPage.class);

            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");//go to Assignments
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys("Create LabelAnImageTypeQuestion");//give the assignment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create link
            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_labelAnImageText().click();//click on Label An Image(Text) type question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Label An Image(Text) question text" );//type the question

            List<WebElement> answers = driver.findElements(By.className("labelAnswer"));
            answers.get(0).click();
            driver.switchTo().activeElement().sendKeys("Answer 1");
            //answers.get(0).sendKeys();

            answers.get(1).click();
            driver.switchTo().activeElement().sendKeys("Answer 2");

            answers.get(2).click();
            driver.switchTo().activeElement().sendKeys("Answer 3");

            driver.findElement(By.className("labelAnswer")).click();
            closeHelp();
            Actions ac = new Actions(driver);
            ac.dragAndDrop(driver.findElement(By.id("ans-drag-btn")),driver.findElement(By.id("canvas"))).build().perform();


            Thread.sleep(1000);
            String correctAnswer = new TextFetch().textfetchbycssselector("div[class='drag-ans wrap-text']");
            Assert.assertEquals(correctAnswer, "Answer 1", "Unable to drag the correct answer in Label An Image(Text) type question..");

            //TC row no. 27...Deleting an option from "Label an image (text) question"
            new Click().clickByid("delete-drop-zone");//click on x icon to delete correct answer
            String text = new TextFetch().textfetchbyid("drop-area");
            if(text.length()>0)
            {
                Assert.fail("Unable to delete the correct answer of Label An Image(Text) type question.");
            }
            //drag the correct answer
            List<WebElement> answerstodrag1 = driver.findElements(By.className("labelAnswer"));
            Actions ac1 = new Actions(driver);
            answerstodrag1.get(0).click();
            ac1.dragAndDrop(driver.findElement(By.id("ans-drag-btn")),driver.findElement(By.id("canvas"))).build().perform();

            insertImageMathMlTable("1", filename);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase createLabelAnImageTypeQuestion in class CreateDifferentTypesOfQuestion.", e);
        }
    }

    @Test(priority = 13, enabled = true)
    public void createLabelAnImageDropdownTypeQuestion()
    {
        try
        {
            //TC row no 28-31
            //Creating Label An Image Dropdown type questions
            String appendChar = "a";
            String filename = ReadTestData.readDataByTagName("", "filename", "1");
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver,QuestionTypesPage.class);

            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");//go to Assignments
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys("Create LabelAnImageDropdownTypeQuestion");//give the assignment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create link
            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_labelAnImageDropDown().click();//click on Label An Image Dropdown type question

            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Label An Image dropdown question text" );//type the question

            List<WebElement> answerChoices = driver.findElements(By.className("label-drpdwn-answerChoice-style"));
            answerChoices.get(0).click();
            List<WebElement> subAnswerChoices = driver.findElements(By.className("label-drpdwn-subAnswerChoice-text"));
            subAnswerChoices.get(0).click();
            subAnswerChoices.get(0).click();
            driver.switchTo().activeElement().sendKeys("Answer 1");
            new Click().clickbylistid("tick-image", 0);//select correct answer
            new Click().clickbylistid("tick-image", 0);//select correct answer

            subAnswerChoices.get(1).click();
            driver.switchTo().activeElement().sendKeys("Answer 2");

            subAnswerChoices.get(2).click();
            driver.switchTo().activeElement().sendKeys("Answer 3");

            answerChoices.get(1).click();//click on second dropdown
            subAnswerChoices.get(3).click();
            driver.switchTo().activeElement().sendKeys("Answer 4");
            new Click().clickbylistid("tick-image", 3);//select correct answer
            new Click().clickbylistid("tick-image", 3);//select correct answer

            subAnswerChoices.get(4).click();
            driver.switchTo().activeElement().sendKeys("Answer 5");

            subAnswerChoices.get(5).click();
            driver.switchTo().activeElement().sendKeys("Answer 6");

            answerChoices.get(2).click();//click on third dropdown
            subAnswerChoices.get(6).click();
            driver.switchTo().activeElement().sendKeys("Answer 7");
            new Click().clickbylistid("tick-image", 6);//select correct answer
            new Click().clickbylistid("tick-image", 6);//select correct answer

            subAnswerChoices.get(7).click();
            driver.switchTo().activeElement().sendKeys("Answer 8");

            subAnswerChoices.get(8).click();
            driver.switchTo().activeElement().sendKeys("Answer 9");

            answerChoices.get(0).click();//click on 1st answer
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("delete_label_drpdwn_answer_choice")));
            new Click().clickByid("delete_label_drpdwn_answer_choice");//delete the 1st answer

            List<WebElement> answerChoices1 = driver.findElements(By.className("label-drpdwn-answerChoice-style"));
            String str = answerChoices1.get(0).getText();
            Assert.assertEquals(str, "Answer 4", "Unable to delete answer dropdown from Label An Image Dropdown Type question.");

            Thread.sleep(2000);
            List<WebElement> answerChoices2 = driver.findElements(By.className("label-drpdwn-answerChoice-style"));
            answerChoices2.get(0).click();//click on 1st answer
            List<WebElement> answerstodrag = driver.findElements(By.className("label-drpdwn-answerChoice-style"));
            Actions ac = new Actions(driver);
            answerstodrag.get(0).click();
            ac.dragAndDrop(driver.findElement(By.id("ans-drag-btn")),driver.findElement(By.id("canvas"))).build().perform();
            Thread.sleep(2000);
            String correctAnswer = new TextFetch().textfetchbycssselector("div[class='drag-ans wrap-text']");
            Assert.assertEquals(correctAnswer, "Answer 4", "Unable to drag the correct answer in Label An Image(Dropdown) type question..");

            List<WebElement> answerstodrag1 = driver.findElements(By.className("label-drpdwn-answerChoice-style"));
            Actions ac1 = new Actions(driver);
            answerstodrag1.get(0).click();
            ac1.dragAndDrop(driver.findElement(By.id("ans-drag-btn")),driver.findElement(By.id("canvas"))).build().perform();

            insertImageMathMlTable("1", filename);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase createLabelAnImageDropdownTypeQuestion in class CreateDifferentTypesOfQuestion.", e);
        }
    }

    @Test(priority = 14, enabled = true)
    public void createResequenceTypeQuestion()
    {
        try
        {
            //TC row no 32-35
            //Creating Resequence type questions
            //Undo/Delete Option in resequence type questions
            String appendChar = "a";
            String filename = ReadTestData.readDataByTagName("", "filename", "1");
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver,QuestionTypesPage.class);

            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");//go to Assignments
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys("Create ResequenceTypeQuestion");//give the assignment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create link
            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_resequence().click();//click on resequence type question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Resequence question type");//type the question

            List<WebElement> allAnswers = driver.findElements(By.cssSelector("div[class='resequence-answer-choice hover-border']"));
            allAnswers.get(0).click();//click on 1st row
            new Click().clickByid("answer_choice_edit");
            driver.findElement(By.id("answer_choice_txt")).sendKeys("1000");

            allAnswers.get(1).click();//click on 2nd row
            new Click().clickByid("answer_math_edit");
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","10");

            allAnswers.get(2).click();//click on 3rd row
            new Click().clickByid("answer_image_edit");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='pickfiles']")));
            Thread.sleep(2000);
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(15000);
            new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
            (new WebDriverWait(driver, 60))
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.id("widget-createimage_start_queue")));
            allAnswers.get(3).click();//click on 4th row
            new Click().clickByid("answer_image_and_text_edit");
            new Click().clickbylistcssselector("span[id='plus']", 1);//click on + icon to upload image
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='pickfiles']")));
            Thread.sleep(2000);
            new KeysSend().sendKeyBoardKeys("$"+ Config.fileUploadPath+filename+"^");
            Thread.sleep(15000);
            new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
            (new WebDriverWait(driver, 60))
                    .until(ExpectedConditions.invisibilityOfElementLocated(By.id("widget-createimage_start_queue")));
            new Click().clickBycssselector("div[class='dashed resequence-answer-imageText label-to-control']");
            driver.switchTo().activeElement().sendKeys("2000");

            allAnswers.get(4).click();//click on 5th row
            new Click().clickByid("answer_choice_edit");
            driver.switchTo().activeElement().sendKeys("2000");

            new Click().clickByclassname("back-pointer-icon");//click on arrow icon
            new Click().clickByXpath("//img[@src='/webresources/javascript/questions/images/clear.png']");//click on undo icon
            String str = driver.findElements(By.cssSelector("div[class='resequence-answer-choice hover-border']")).get(0).getText();
            System.out.println("str: "+str);
            if(str.length() != 0){
                Assert.fail("Teacher is unable to clear the answer option by clicking the undo button of resquence type question.");
            }

            allAnswers.get(0).click();//click on 1st row
            new Click().clickByid("answer_choice_edit");
            driver.switchTo().activeElement().sendKeys("1000");

            new Click().clickByclassname("back-pointer-icon");//click on arrow icon

            new Click().clickByXpath("//img[@src='/webresources/javascript/questions/images/delete_icon.png']");//click on delete icon
            String str1 = driver.findElements(By.cssSelector("div[class='resequence-answer-choice hover-border']")).get(0).getText();
            System.out.println("str1: "+str1);
            if(str1.equals("1000")){
                Assert.fail("Teacher is unable to delete the answer option of resquence type question.");
            }

            insertImageMathMlTable("1", filename);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase createResequenceTypeQuestion in class CreateDifferentTypesOfQuestion.", e);
        }
    }
    @Test(priority = 15, enabled = true)
    public void createClozeMatrixTypeQuestion()
    {
        try
        {
            //TC row no 36-38
            //Creating "Cloze Matrix" type question
            String appendChar = "a";
            String filename = ReadTestData.readDataByTagName("", "filename", "1");
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver,QuestionTypesPage.class);

            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");//go to Assignments
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys("Create ClozeMatrixTypeQuestion");//give the assignment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create link
            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_clozeMatrix().click();//click on Cloze Matrix type question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Cloze Matrix " );//type the question
            new QuestionCreate().enterValueInClozeMatrix(0, 1);
            new QuestionCreate().enterValueInClozeMatrix(1, 2);
            new QuestionCreate().enterValueInClozeMatrix(2, 3);
            new QuestionCreate().enterValueInClozeMatrix(3, 4);
            new QuestionCreate().enterValueInClozeMatrix(4, 5);
            new QuestionCreate().enterValueInClozeMatrix(5, 6);
            new QuestionCreate().enterValueInClozeMatrix(6, 7);
            new QuestionCreate().enterValueInClozeMatrix(7, 8);
            new QuestionCreate().enterValueInClozeMatrix(8, 9);
            driver.findElement(By.xpath("//tr[@id='row1']/td/div/div/div")).click();//click on 1st cell
            new Click().clickByclassname("make-text-entry-box");//select correct answer
            List<WebElement> matrixCells = driver.findElements(By.className("matrix-text-area"));
            matrixCells.get(2).click();//click on 1st row last column cell

            //Delete option for "Cloze Matrix" type question
            List<WebElement> allDelete = driver.findElements(By.xpath("//img[@src='/webresources/javascript/questions/images/delete_icon.png']"));//click on delete icon
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",allDelete.get(1));
            List<WebElement> matrixCellsValues = driver.findElements(By.cssSelector("div[class='answer-matrix-con matrix label-to-control']"));
            boolean found = false;
            for (WebElement value : matrixCellsValues)
            {
                if(value.getText().equals("3"))
                {
                    found = true;
                    break;
                }
            }
            Assert.assertEquals(found, false, "Unable to delete a row/column from cloze matrix question.");

            insertImageMathMlTable("1", filename);

        }

    catch (Exception e)
    {
        Assert.fail("Exception in testcase createClozeMatrixTypeQuestion in class CreateDifferentTypesOfQuestion.", e);
    }
}


    @Test(priority = 16, enabled = true)
    public void createGraphPlotterTypeQuestion()
    {
        try {
            //TC row no 39
            //Creating Graph Plotter type of questions
            String appendChar = "a5";
            String filename = ReadTestData.readDataByTagName("", "filename", "1");
            CreateAssignment createAssessment = PageFactory.initElements(driver, CreateAssignment.class);
            Assessments assessments = PageFactory.initElements(driver,Assessments.class);
            TLOListPage tloListPage = PageFactory.initElements(driver, TLOListPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver,QuestionTypesPage.class);

            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");//go to Assignments
            assessments.getButton_newAssessment().click();//Click on new assessment
            createAssessment.getAssessment_editbox().clear();
            createAssessment.getAssessment_editbox().sendKeys("Create GraphPlotterTypeQuestion");//give the assignment name
            createAssessment.getButton_create().click();//Click on create
            tloListPage.getButton_create().click();//Click on create link
            createAssessment.getTabs_differentQuestionType().get(1).click();//Click on Math Tech Enhanced tab
            questionTypesPage.getIcon_graphPlotter().click();//click on Graph Plotter type question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Graph Plotter ");//type the question

            new TextSend().textsendbyid("Year", "labelX");//label X
            new TextSend().textsendbyid("population", "labelY");//label Y

            new TextSend().textsendbyid("10", "minY");//Scale Y
            new TextSend().textsendbyid("80", "maxY");//Max Y

            new Click().clickByid("add-new-category");//add new category
            new Click().clickByid("add-new-category");//add new category
            List<WebElement> allCategories = driver.findElements(By.className("graph-plotter-category"));
            allCategories.get(0).click();
            allCategories.get(0).clear();
            driver.switchTo().activeElement().sendKeys("2001");
            allCategories.get(1).click();
            allCategories.get(1).clear();
            driver.switchTo().activeElement().sendKeys("2002");
            allCategories.get(2).click();
            allCategories.get(2).clear();
            driver.switchTo().activeElement().sendKeys("2003");
            allCategories.get(3).click();
            allCategories.get(3).clear();
            driver.switchTo().activeElement().sendKeys("2004");
            allCategories.get(4).click();
            allCategories.get(4).clear();
            driver.switchTo().activeElement().sendKeys("2005");

            List<WebElement> allCategories1 = driver.findElements(By.tagName("tspan"));
            for(WebElement cat: allCategories1)
            {
                System.out.println("-->"+cat.getText());
            }
            Assert.assertEquals(allCategories1.get(0).getText(), "population", "Label Y is not updated in graph in graph plotter type question");
            Assert.assertEquals(allCategories1.get(1).getText(), "Year", "Label X is not updated in graph in graph plotter type question");
            Assert.assertEquals(allCategories1.get(2).getText(), "2001", "Categories are not updated in graph in graph plotter type question");
            Assert.assertEquals(allCategories1.get(4).getText(), "2003", "Categories are not updated in graph in graph plotter type question");
            Assert.assertEquals(allCategories1.get(6).getText(), "2005", "Categories are not updated in graph in graph plotter type question");

            insertImageMathMlTable("1", filename);
        }

        catch (Exception e)
        {
            Assert.fail("Exception in testcase createGraphPlotterTypeQuestion in class CreateDifferentTypesOfQuestion.", e);
        }
    }

    @Test(priority = 17, enabled = true)
    public void createClozeFormulaTypeQuestion()
    {
        try {
            //TC row no 40, 41
            //Creating Cloze Formula type of questions
            CreateAssignment createAssessment=PageFactory.initElements(driver,CreateAssignment.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            String filename = ReadTestData.readDataByTagName("", "filename", "1");
            String appendChar = "a";
            new SignUp().teacher(appendChar, 1);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 1);//create school
            new Classes().createClass(appendChar, 1);//create class
            new Navigator().navigateTo("assignment");    //go to Assignments
            assignments.getButton_newAssessment().click();
           // new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            createAssessment.assignmentName.clear();
            createAssessment.assignmentName.sendKeys("Cloze formula");
            createAssessment.getButton_create().click();
            new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']");
            new Click().clickbylinkText("Math Tech Enhanced");//click on Math Tech Enhanced tab
            new Click().clickByid("qtn-cf-type");//click on Cloze Formula type question
            closeHelp();
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Cloze Formula ");//type the question

            List<WebElement> answers =  driver.findElements(By.className("answer"));
            answers.get(0).click();
            driver.findElement(By.id("answer_math_edit")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","1");

            answers.get(1).click();
            driver.findElement(By.id("answer_math_edit")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","2");

            answers.get(2).click();
            driver.findElement(By.id("answer_math_edit")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root", "3");

            answers.get(3).click();
            driver.findElement(By.id("answer_math_edit")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root", "4");

            answers.get(4).click();
            driver.findElement(By.id("answer_math_edit")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","5");

            List<WebElement> formulaLHS = driver.findElements(By.cssSelector("div[class='dnd-match-lhs box cloze-formula-lhs']"));
            formulaLHS.get(0).click();
            driver.findElement(By.xpath("//div[@class='dnd-match-lhs box cloze-formula-lhs']/div/img")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root", "6");

            formulaLHS.get(1).click();
            driver.findElement(By.xpath("//div[@class='dnd-match-lhs box cloze-formula-lhs']/div/img")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","7");

            formulaLHS.get(2).click();
            driver.findElement(By.xpath("//div[@class='dnd-match-lhs box cloze-formula-lhs']/div/img")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","8");

            formulaLHS.get(3).click();
            driver.findElement(By.xpath("//div[@class='dnd-match-lhs box cloze-formula-lhs']/div/img")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","9");

            formulaLHS.get(4).click();
            driver.findElement(By.xpath("//div[@class='dnd-match-lhs box cloze-formula-lhs']/div/img")).click();
            new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","10");


            //dragging and dropping values
            Actions ac = new Actions(driver);
            List<WebElement> answerstodrop =  driver.findElements(By.className("answer"));
            List<WebElement> rhsFields = driver.findElements(By.cssSelector("div[class='dnd-match-rhs box cloze-formula-rhs ui-droppable']"));

            answerstodrop.get(0).click();
            ac.dragAndDrop(driver.findElement(By.id("ans-drag-btn")), rhsFields.get(0)).build().perform();

            answerstodrop.get(1).click();
            ac.dragAndDrop(driver.findElements(By.id("ans-drag-btn")).get(1), rhsFields.get(1)).build().perform();

            answerstodrop.get(2).click();
            ac.dragAndDrop(driver.findElements(By.id("ans-drag-btn")).get(2), rhsFields.get(2)).build().perform();

            answerstodrop.get(3).click();
            ac.dragAndDrop(driver.findElements(By.id("ans-drag-btn")).get(3), rhsFields.get(3)).build().perform();

            answerstodrop.get(4).click();
            ac.dragAndDrop(driver.findElements(By.id("ans-drag-btn")).get(4), rhsFields.get(4)).build().perform();

            insertImageMathMlTable("1", filename);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase createClozeFormulaTypeQuestion in class CreateDifferentTypesOfQuestion.", e);
        }
    }
        public void insertImageMathMlTable(String dataIndex, String filename)
    {
        try
        {
            for(int i=1;i<3;i++){
            try {
                new Action().doubleClick(driver.findElement(By.id("question-raw-content")));
                break;
            } catch (Exception e) {
            }

            }

            Thread.sleep(2000);
            new Click().clickBycssselector("a[class='re-icon re-uploadimage redactor-btn-image']");//click on image icon
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//*[@id='pickfiles']")));
            Thread.sleep(2000);
            new KeysSend().sendKeyBoardKeys("$"+ Config.fileUploadPath+filename+"^");
            Thread.sleep(15000);
            new Click().clickByid("widget-createimage_start_queue");//click on Upload Now
            new Click().clickByid("question-raw-content");//click on Question
            new Click().clickBycssselector("a[class='re-icon re-matheditor redactor-btn-image']");//click on MathMl icon
            new QuestionCreate().enterValueInMathMLEditor("Square root","10");
            new Click().clickByid("question-raw-content");//click on Question
            new Click().clickBycssselector("a[class='re-icon re-table']");//click on table icon
            new Click().clickBycssselector("a[class=' redactor_dropdown_insert_table']");//click on table icon
            new Click().clickByid("redactor_insert_table_btn");//click on Insert button
            new Select(driver.findElement(By.id("difficulty-level-drop-down"))).selectByVisibleText("Easy");//select difficulty level
            new Select(driver.findElement(By.id("difficulty-level-drop-down"))).selectByVisibleText("Medium");
            new Select(driver.findElement(By.id("difficulty-level-drop-down"))).selectByVisibleText("Hard");
            new TextSend().textsendbyid("True False Solution Text","content-solution");//enter solution
            new TextSend().textsendbyid("True False Hint Text","content-hint");//enter hint text
            new Click().clickByid("saveQuestionDetails1");//click on Save button
            WebDriverWait wait=new WebDriverWait(driver,30);
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//label[text()='Saved.']"))));
            String saved = new TextFetch().textfetchbycssselector("small[class='lsm-createAssignment-total-questions count-badge']");
            Assert.assertEquals(saved, "1", "Question is not created.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase insertImageMathMlTable in class CreateDifferentTypesOfQuestion.", e);
        }
    }
}
