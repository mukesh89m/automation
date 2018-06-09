package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.fractionEditor;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentQuestionsListPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.FractionEditorQuestionCreation;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by shashank on 30-04-2015.
 */
public class FractionEditor extends Driver{

    @Test(priority = 1)
    public void createFractionEditor()
    {
        WebDriverWait wait=new WebDriverWait(driver,30);
        String appendChar="a";
        int dataIndex=10;
        try {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            FractionEditorQuestionCreation fractionEditorQuestionCreation= PageFactory.initElements(driver, FractionEditorQuestionCreation.class);
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher*/
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode=new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickBycssselector("#create-new-assignment > div.as-assignment-flow-link-content > div.as-assignment-flow-link-title");//click on Create New Assignment
            new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn']");//click on Create link
            new Click().clickBycssselector("div[title='Fraction Editor']");//click on Fraction Editor type question
            //validate Fraction model default selection is rectangular

            //validate 1 rectangle present with 2rows and two column
            Assert.assertTrue(fractionEditorQuestionCreation.getRectangleCount().size()==1,"Displaying more than one rectangle");//count number of rectangle
            Assert.assertTrue(fractionEditorQuestionCreation.getNumberOfRow().getAttribute("value").equals("2"),"Default number of row should be 2");//value inside in Rows textbox
            Assert.assertTrue(fractionEditorQuestionCreation.getNumberOfColumn().getAttribute("value").equals("2"),"Default number of column should 2");//value inside in Column textbox
            Assert.assertTrue(fractionEditorQuestionCreation.getNumberOfFigures().getAttribute("value").equals("1"),"Default count of figure should be 1");//value inside in Count textbox

            Assert.assertTrue(fractionEditorQuestionCreation.getRowCount().size()==2,"Not displaying 2 rows in rectangle");//count number of row in rectangle
            Assert.assertTrue(fractionEditorQuestionCreation.getColumnCount().size()==4,"Not displaying 2 column in rectangle");//count number of column

            //change Rows from 2 to 3
            fractionEditorQuestionCreation.getNumberOfRow().clear();
            fractionEditorQuestionCreation.getNumberOfRow().sendKeys("3");
            fractionEditorQuestionCreation.getFractionModel().click();
            Assert.assertTrue(fractionEditorQuestionCreation.getRowCount().size()==3,"not displaying three rows");//count number of Rows
            Assert.assertTrue(fractionEditorQuestionCreation.getColumnCount().size()==6,"not displaying three rows");//count number of Rows


            fractionEditorQuestionCreation.getNumberOfColumn().clear();
            fractionEditorQuestionCreation.getNumberOfColumn().sendKeys("3");
            fractionEditorQuestionCreation.getFractionModel().click();
            Assert.assertTrue(fractionEditorQuestionCreation.getRowCount().size()==3,"not displaying three rows");//count number of Rows
            Assert.assertTrue(fractionEditorQuestionCreation.getColumnCount().size()==9,"not displaying three Column");//count number of Rows

            //change count from 1 to 2
            fractionEditorQuestionCreation.getNumberOfFigures().clear();
            fractionEditorQuestionCreation.getNumberOfFigures().sendKeys("2");
            fractionEditorQuestionCreation.getFractionModel().click();
            Assert.assertTrue(fractionEditorQuestionCreation.getRectangleCount().size()==2,"not displaying two rectangle");//count number of rectangle
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys(assessmentname);//give the assignment name

            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("True False "+questiontext);//type the question
            new Click().clickByid("saveQuestionDetails1");//click on save button
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Saved.']")));

            new Click().clickByclassname("as-question-editor-back");//click on back
            Thread.sleep(2000);

            //create Fraction editor using fraction model as circle
            new Click().clickBycssselector("div[title='Fraction Editor']");//click on Fraction Editor type question
            //validate Fraction model default selection is rectangular
            //select fraction model as circle
            Select select=new Select(fractionEditorQuestionCreation.getFractionModel());
            select.selectByValue("circle");
            //check the message while changing fraction model
            Assert.assertTrue(fractionEditorQuestionCreation.
                    getMessageWhileChangingFractionModel().
                    getText().
                    contains("Changing the illustration will reset the selections. Do you wish to continue?"),"Message is not correct");

            fractionEditorQuestionCreation.getYesButton().click();//click on yes button


            Assert.assertTrue(fractionEditorQuestionCreation.getCircleCount().size()==1,"Not displaying one circle");//count number of Circle
            System.out.println(fractionEditorQuestionCreation.getNumberOfSector().getAttribute("value"));
            Assert.assertTrue(fractionEditorQuestionCreation.getNumberOfSector().getAttribute("value").equals("4"),"Default number of row should be 4");//value inside in Rows textbox

            //change count from 1 to 2
            fractionEditorQuestionCreation.getNumberOfFigures().clear();
            fractionEditorQuestionCreation.getNumberOfFigures().sendKeys("2");
            fractionEditorQuestionCreation.getFractionModel().click();
            Assert.assertTrue(fractionEditorQuestionCreation.getCircleCount().size()==2,"not displaying two circle");//count number of circle
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys(assessmentname);//give the assignment name

            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("True False "+questiontext);//type the question
            new Click().clickByid("saveQuestionDetails1");//click on save button
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Saved.']")));

            new Click().clickByclassname("as-question-editor-back");//click on back
            Thread.sleep(2000);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in class FractionEditor in method createFractionEditor", e);
        }
    }
    @Test(priority = 2)
    public void editFractionEditor()
    {
        String appendChar="a2";
        int dataIndex=11;
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssessmentQuestionsListPage listPage = PageFactory.initElements(driver,AssessmentQuestionsListPage.class);

        try
        {
            FractionEditorQuestionCreation fractionEditorQuestionCreation=PageFactory.initElements(driver,FractionEditorQuestionCreation.class);
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher*/
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode=new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().navigateTo("assignment");
            new Assignment().create(11,"fractioneditor");
            fractionEditorQuestionCreation.getButton_review().click();
            //Expected - Only question title should be displayed.
            if(!listPage.getCreateAssignmentQuestionName().getText().contains(questiontext)){
                Assert.fail("Question title is not displayed");
            }
            //Expected -  Question Type should be displayed as “Classification Question”
            Assert.assertEquals(listPage.getQuestionType().getText(),"Fraction Editor","Question type is not displayed");






        }
        catch (Exception e)
        {
            Assert.fail("Exception in class FractionEditor in method editFractionEditor", e);
        }



    }

    @Test(priority = 2)
    public void previewFractionEditor()
    {
        String appendChar="a";
        int dataIndex=12;

        try
        {
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher*/
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode=new Classes().createClass(appendChar, dataIndex);//create class


        }
        catch (Exception e)
        {
            Assert.fail("Exception in class FractionEditor in method editFractionEditor", e);
        }



    }
}
