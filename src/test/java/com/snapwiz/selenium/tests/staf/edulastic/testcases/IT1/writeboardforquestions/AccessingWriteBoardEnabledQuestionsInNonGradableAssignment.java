
package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.writeboardforquestions;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;


/*
 * Created by sumit on 18/12/14.
 */

public class AccessingWriteBoardEnabledQuestionsInNonGradableAssignment extends Driver{

    @Test(priority = 1, enabled = true)
    public void accessingWriteBoardEnabledQuestionsInNonGradableAssignment()
    {
        try
        {
            String appendChar = "a";
            int dataIndex = 61;

            new SignUp().teacher(appendChar, dataIndex);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//log in as teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode = new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, dataIndex);//create student
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//log in as teacher
            new Assignment().create(dataIndex, "truefalse");
            new Assignment().addQuestion(dataIndex, "multiplechoice");
            new Assignment().addQuestion(dataIndex, "multipleselection");
            new Assignment().addQuestion(dataIndex, "textentry");
            new Assignment().addQuestion(dataIndex, "textdropdown");
            new Assignment().addQuestion(dataIndex, "numericentrywithunits");
            new Assignment().addQuestion(dataIndex, "advancednumeric");
            new Assignment().addQuestion(dataIndex, "expressionevaluator");
            new Assignment().addQuestion(dataIndex, "matchthefollowing");
            new Assignment().addQuestion(dataIndex, "draganddrop");

            new Assignment().assignToStudent(dataIndex, appendChar);//assign to student
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, dataIndex);
            new Assignment().openAssignment(dataIndex);//open assignment

            String writeBoard = new TextFetch().textfetchbyid("show-your-work-label");
            Assert.assertEquals(writeBoard, "Show your work", "\"Show your work\" link is not present in true/false type question when the write board is enabled for that true/false type question.");
            String downArrow = driver.findElement(By.className("show-your-link")).getAttribute("src");
            if(!downArrow.contains("show-your-link-icon.png"))
                Assert.fail("The arrow near the \"Show your work\" link is not downward.");

            new Click().clickByid("show-your-work-label");//4. Click on "Show your link"
            boolean isWriteBoardDisplayed = driver.findElement(By.id("cms-write-board-zwibbler-wrapper")).isDisplayed();
            Assert.assertEquals(isWriteBoardDisplayed, true, "Clicking \"Show your work\" link the write board does not expand in true/false type question.");

            String writeBoardText = new TextFetch().textfetchbyid("show-your-work-label");
            Assert.assertEquals(writeBoardText, "Hide your work", "\"Hide your work\" link is not present in true/false type question when the write board is open");
            String upArrow = driver.findElement(By.className("show-your-link")).getAttribute("src");
            if(!upArrow.contains("show-your-link-icon-upArrow.png"))
                Assert.fail("After opening the Write board the arrow near the \"Hide your work\" link is  not upward.");

            new Click().clickByid("show-your-work-label");//Click on "Hide your work"
            String str = new RandomString().randomstring(5);
            new WriteBoard().enterTextInWriteBoard(str, dataIndex);

            new Click().clickByid("show-your-work-label");//Click on "Hide your work"
            boolean isWriteBoardDisplayed1 = driver.findElement(By.id("cms-write-board-zwibbler-wrapper")).isDisplayed();
            Assert.assertEquals(isWriteBoardDisplayed1, false, "Clicking \"Hide your work\" link the write board does not collapse in true/false type question.");

            new Assignment().clickOnNextButton(dataIndex);

            new Assignment().clickOnQuestion(0, dataIndex);//go to True false type question

            String tickMark = driver.findElement(By.id("white-board-check-mark")).getAttribute("src");
            if(!tickMark.contains("white-board-check-mark.png"))
                Assert.fail("Green tick mark is not displayed beside \"View your work\" link on coming back to question.");

            boolean dataFound;
            dataFound = new WriteBoard().verifyWriteBoardDataIsSaved(str);
            Assert.assertEquals(dataFound, true, "Writeboard data is not saved for true false type question.");

            new Assignment().clickOnQuestion(1, dataIndex);//go to Multiple choice type question
            new WriteBoard().enterTextInWriteBoard(str, dataIndex);//enter data in Writeboard
            new Assignment().clickOnNextButton(dataIndex);//go to next question
            new Assignment().clickOnQuestion(1, dataIndex);//go to Multiple choice type question

            dataFound = new WriteBoard().verifyWriteBoardDataIsSaved(str);
            Assert.assertEquals(dataFound, true, "Writeboard data is not saved for Multiple choice type question.");

            new Assignment().clickOnQuestion(2, dataIndex);//go to Multiple Select type question
            new WriteBoard().enterTextInWriteBoard(str, dataIndex);//enter data in Writeboard
            new Assignment().clickOnNextButton(dataIndex);//go to next question
            new Assignment().clickOnQuestion(2, dataIndex);//go to Multiple Select type question

            dataFound = new WriteBoard().verifyWriteBoardDataIsSaved(str);
            Assert.assertEquals(dataFound, true, "Writeboard data is not saved for Multiple Select type question.");

            new Assignment().clickOnQuestion(3, dataIndex);//go to Text Entry type question
            new WriteBoard().enterTextInWriteBoard(str, dataIndex);//enter data in Writeboard
            new Assignment().clickOnNextButton(dataIndex);//go to next question
            new Assignment().clickOnQuestion(3, dataIndex);//go to Text Entry type question

            dataFound = new WriteBoard().verifyWriteBoardDataIsSaved(str);
            Assert.assertEquals(dataFound, true, "Writeboard data is not saved for Text Entry type question.");

            new Assignment().clickOnQuestion(4, dataIndex);//go to Text Dropdown type question
            new WriteBoard().enterTextInWriteBoard(str, dataIndex);//enter data in Writeboard
            new Assignment().clickOnNextButton(dataIndex);//go to next question
            new Assignment().clickOnQuestion(4, dataIndex);//go to Text Dropdown type question

            dataFound = new WriteBoard().verifyWriteBoardDataIsSaved(str);
            Assert.assertEquals(dataFound, true, "Writeboard data is not saved for Text Dropdown type question.");

            new Assignment().clickOnQuestion(5, dataIndex);//go to Numeric entry with units type question
            new WriteBoard().enterTextInWriteBoard(str, dataIndex);//enter data in Writeboard
            new Assignment().clickOnNextButton(dataIndex);//go to next question
            new Assignment().clickOnQuestion(5, dataIndex);//go to Numeric entry with units type question

            dataFound = new WriteBoard().verifyWriteBoardDataIsSaved(str);
            Assert.assertEquals(dataFound, true, "Writeboard data is not saved for Numeric entry with units type question.");

            new Assignment().clickOnQuestion(6, dataIndex);//go to Advanced Numeric type question
            new WriteBoard().enterTextInWriteBoard(str, dataIndex);//enter data in Writeboard
            new Assignment().clickOnNextButton(dataIndex);//go to next question
            new Assignment().clickOnQuestion(6, dataIndex);//go to Advanced Numeric type question

            dataFound = new WriteBoard().verifyWriteBoardDataIsSaved(str);
            Assert.assertEquals(dataFound, true, "Writeboard data is not saved for Advanced Numeric type question.");

            new Assignment().clickOnQuestion(7, dataIndex);//go to Expression evaluator type question
            new WriteBoard().enterTextInWriteBoard(str, dataIndex);//enter data in Writeboard
            new Assignment().clickOnNextButton(dataIndex);//go to next question
            new Assignment().clickOnQuestion(7, dataIndex);//go to Expression evaluator type question

            dataFound = new WriteBoard().verifyWriteBoardDataIsSaved(str);
            Assert.assertEquals(dataFound, true, "Writeboard data is not saved for Expression evaluator type question.");

            new Assignment().clickOnQuestion(8, dataIndex);//go to Match the following type question
            new WriteBoard().enterTextInWriteBoard(str, dataIndex);//enter data in Writeboard
            new Assignment().clickOnNextButton(dataIndex);//go to next question
            new Assignment().clickOnQuestion(8, dataIndex);//go to Match the following type question

            dataFound = new WriteBoard().verifyWriteBoardDataIsSaved(str);
            Assert.assertEquals(dataFound, true, "Writeboard data is not saved for Match the following type question.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase accessingWriteBoardEnabledQuestionsInNonGradableAssignment in class AccessingWriteboardEnabledQuestionsInGradableAssignment.", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void accessingWriteBoardEnabledQuestionsInNonGradableAssignmentOtherQuestions()
    {
        try
        {
            String appendChar = "a";
            int dataIndex = 76;

            new SignUp().teacher(appendChar, dataIndex);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//log in as teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode = new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, dataIndex);//create student
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//log in as teacher
            new Assignment().create(dataIndex, "draganddrop");
            new Assignment().addQuestion(dataIndex, "essay");
            new Assignment().addQuestion(dataIndex, "labelanimagetext");
            new Assignment().addQuestion(dataIndex, "resequence");
            new Assignment().addQuestion(dataIndex, "clozematrix");
            new Assignment().addQuestion(dataIndex, "graphplotter");
            new Assignment().addQuestion(dataIndex, "clozeformula");
            new Assignment().addQuestion(dataIndex, "labelanimagedropdown");

            new Assignment().assignToStudent(dataIndex, appendChar);//assign to student
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, dataIndex);
            new Assignment().openAssignment(dataIndex);//open assignment
            String str = new RandomString().randomstring(5);
            new WriteBoard().enterTextInWriteBoard(str, dataIndex);

            new Assignment().clickOnNextButton(dataIndex);
            new Assignment().clickOnQuestion(0, dataIndex);//go to Drag And Drop type question
            boolean dataFound;
            dataFound = new WriteBoard().verifyWriteBoardDataIsSaved(str);
            Assert.assertEquals(dataFound, true, "Writeboard data is not saved for Drag And Drop type question.");

            new Assignment().clickOnQuestion(1, dataIndex);//go to essay type question
            new WriteBoard().enterTextInWriteBoard(str, dataIndex);//enter data in Writeboard
            new Assignment().clickOnNextButton(dataIndex);//go to next question
            new Assignment().clickOnQuestion(1, dataIndex);//go to essay type question

            dataFound = new WriteBoard().verifyWriteBoardDataIsSaved(str);
            Assert.assertEquals(dataFound, true, "Writeboard data is not saved for essay type question.");

            new Assignment().clickOnQuestion(2, dataIndex);//go to label an image text type question
            new WriteBoard().enterTextInWriteBoard(str, dataIndex);//enter data in Writeboard
            new Assignment().clickOnNextButton(dataIndex);//go to next question
            new Assignment().clickOnQuestion(2, dataIndex);//go to label an image text type question

            dataFound = new WriteBoard().verifyWriteBoardDataIsSaved(str);
            Assert.assertEquals(dataFound, true, "Writeboard data is not saved for label an image text type question.");

            new Assignment().clickOnQuestion(3, dataIndex);//go to resequence type question
            new WriteBoard().enterTextInWriteBoard(str, dataIndex);//enter data in Writeboard
            new Assignment().clickOnNextButton(dataIndex);//go to next question
            new Assignment().clickOnQuestion(3, dataIndex);//go to resequence type question

            dataFound = new WriteBoard().verifyWriteBoardDataIsSaved(str);
            Assert.assertEquals(dataFound, true, "Writeboard data is not saved for resequence type question.");

            new Assignment().clickOnQuestion(4, dataIndex);//go to cloze matrix type question
            new WriteBoard().enterTextInWriteBoard(str, dataIndex);//enter data in Writeboard
            new Assignment().clickOnNextButton(dataIndex);//go to next question
            new Assignment().clickOnQuestion(4, dataIndex);//go to cloze matrix type question

            dataFound = new WriteBoard().verifyWriteBoardDataIsSaved(str);
            Assert.assertEquals(dataFound, true, "Writeboard data is not saved for cloze matrix type question.");

            new Assignment().clickOnQuestion(5, dataIndex);//go to graph plotter type question
            new WriteBoard().enterTextInWriteBoard(str, dataIndex);//enter data in Writeboard
            new Assignment().clickOnNextButton(dataIndex);//go to next question
            new Assignment().clickOnQuestion(5, dataIndex);//go to graph plotter type question

            dataFound = new WriteBoard().verifyWriteBoardDataIsSaved(str);
            Assert.assertEquals(dataFound, true, "Writeboard data is not saved for graph plotter type question.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase accessingWriteBoardEnabledQuestionsInNonGradableAssignmentOtherQuestions in class AccessingWriteboardEnabledQuestionsInGradableAssignment.", e);
        }
    }
}
