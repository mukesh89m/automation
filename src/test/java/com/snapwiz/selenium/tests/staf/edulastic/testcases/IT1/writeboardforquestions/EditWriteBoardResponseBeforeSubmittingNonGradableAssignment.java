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
public class EditWriteBoardResponseBeforeSubmittingNonGradableAssignment extends Driver{

    @Test(priority = 1, enabled = true)
    public void editWriteBoardResponseBeforeSubmittingNonGradableAssignment()
    {
        try{
            String appendChar = "a";
            int dataIndex = 124;

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
            /*new Assignment().addQuestion(dataIndex, "multipleselection");
            new Assignment().addQuestion(dataIndex, "textentry");
            new Assignment().addQuestion(dataIndex, "textdropdown");
            new Assignment().addQuestion(dataIndex, "numericentrywithunits");
            new Assignment().addQuestion(dataIndex, "advancednumeric");
            new Assignment().addQuestion(dataIndex, "expressionevaluator");
            new Assignment().addQuestion(dataIndex, "matchthefollowing");
            new Assignment().addQuestion(dataIndex, "draganddrop");*/

            new Assignment().assignToStudent(dataIndex, appendChar);//assign to student
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, dataIndex);//log in as student
            new Assignment().openAssignment(dataIndex);//open assignment
            //TC row no. 117-119
            String writeBoard1 = new TextFetch().textfetchbyid("show-your-work-label");
            Assert.assertEquals(writeBoard1, "Show your work", "\"Show your work\" link is not present in true/false type question when the write board is enabled for that true/false type question.");
            String downArrow = driver.findElement(By.className("show-your-link")).getAttribute("src");
            if(!downArrow.contains("show-your-link-icon.png"))
                Assert.fail("The arrow near the \"Show your work\" link is not downward.");
            new Click().clickByid("show-your-work-label");// Click on "Show your link"

            boolean isWriteBoardDisplayed = driver.findElement(By.id("cms-write-board-zwibbler-wrapper")).isDisplayed();
            Assert.assertEquals(isWriteBoardDisplayed, true, "Clicking \"Show your work\" link the write board does not expand in true/false type question.");
            new Click().clickByid("show-your-work-label");//Click on "Hide your work"


            String str = new RandomString().randomstring(5);
            // for(int i = 0; i < 9; i++){//write in first 9 questions
            new WriteBoard().enterTextInWriteBoard(str, dataIndex);
            new Assignment().clickOnNextButton(dataIndex);//click on Next button
            //  }
            //  new WriteBoard().enterTextInWriteBoard(str, dataIndex);//write in 10th question
            new Assignment().clickOnQuestion(0, dataIndex);//come back to 1st question
            //TC row no. 113
            String writeBoard = new TextFetch().textfetchbyid("show-your-work-label");
            Assert.assertEquals(writeBoard, "View your work", "\"View your work\" link is not present in true/false type question when the write board is enabled for that true/false type question.");

            //TC row no. 114, 115
            String str1 = new RandomString().randomstring(5);
            new WriteBoard().enterTextInWriteBoard(str1, dataIndex);
            new Assignment().clickOnNextButton(dataIndex);//click on Next button
            new Assignment().clickOnQuestion(0, dataIndex);//come back to 1st question

            boolean edited;
            edited = new WriteBoard().verifyWriteBoardDataIsSaved(str1);
            System.out.println("edited: "+edited);
            Assert.assertEquals(edited, true, "Instructor is unable to edit writeboard data for gradable true false type question.");

            //TC row no. 116
            new Assignment().clickOnNextButton(dataIndex);//click on Next button
            new WriteBoard().enterTextInWriteBoard(str1, dataIndex);//enter text in multiple choice question
            new Assignment().clickOnQuestion(0, dataIndex);//go to previous question
            new Assignment().clickOnNextButton(dataIndex);//click on Next button

            edited = new WriteBoard().verifyWriteBoardDataIsSaved(str1);
            System.out.println("edited: "+edited);
            Assert.assertEquals(edited, true, "Instructor is unable to edit writeboard data for gradable multiple choice type question.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase editWriteBoardResponseBeforeSubmittingNonGradableAssignment in class EditWriteBoardResponseBeforeSubmittingNonGradableAssignment.", e);
        }
    }

}
