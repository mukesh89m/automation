package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.writeboardforquestions;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextSend;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 * Created by sumit on 18/12/14.
 */
public class AccessingWriteBoardEnabledQuestionsAsInstructorForNonGradable extends Driver{

    @Test(priority = 1, enabled = true)
    public void accessingWriteBoardEnabledQuestionsAsInstructorForNonGradable()
    {
        try{
            String appendChar = "a";
            int dataIndex = 152;
            String writeboarddata = ReadTestData.readDataByTagName("", "writeboarddata", Integer.toString(dataIndex));

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

            new Assignment().assignToStudent(dataIndex, appendChar);//assign to student
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, dataIndex);//log in as student
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//log in as teacher
            new Assignment().clickOnStudentResponse(dataIndex);//click on Student Responses
            new Click().clickByclassname("idb-gradebook-question-content");//click on tick mark
            Thread.sleep(2000);
            boolean dataFound;
            //TC row no. 133
            String text = new TextFetch().textfetchbyid("show-your-work-label");
            Assert.assertEquals(text, "Review student's work and provide feedback on Scratchpad", "\"Review student's work and provide feedback on Scratchpad\" for non gradable true false type question");

            //TC row no. 135
            dataFound = new WriteBoard().verifyWriteBoardDataIsSaved(writeboarddata);
            Assert.assertEquals(dataFound, true, "The Writeboard  tool does not have the student entered response.");

            //TC row no. 137 - 139
            new Assignment().clickOnStudentResponse(dataIndex);//click on Student Responses
            new Click().clickByclassname("idb-gradebook-question-content");//click on tick mark
            String str = new RandomString().randomstring(5);
            new WriteBoard().instructorEnterTextInWriteBoard(str, dataIndex);//instructor enter text in  Write Board
            new Click().clickByclassname("view-user-question-performance-save-btn");//click on Save
            new Click().clickByid("next-question-performance-view");//click on Next arrow
            new Click().clickByid("prev-question-performance-view");//click on Previous arrow

            dataFound = new WriteBoard().verifyWriteBoardDataIsSaved(writeboarddata);
            Assert.assertEquals(dataFound, true, "The Writeboard tool does not save the instructor entered response.");

            new Assignment().clickOnStudentResponse(dataIndex);//click on Student Responses
            new Click().clickByclassname("idb-gradebook-question-content");//click on tick mark
            //TC row no. 140
            dataFound = new WriteBoard().verifyWriteBoardDataIsSaved(writeboarddata);
            Assert.assertEquals(dataFound, true, "The Writeboard tool does not have saved data if instructor opens the response page once gain.");

            String feedback = new RandomString().randomstring(5);
            new TextSend().textsendbyid(feedback, "view-user-question-performance-feedback-box");//give feedback
            new Click().clickByclassname("view-user-question-performance-save-btn");//click on Save
            new Assignment().clickOnStudentResponse(dataIndex);//click on Student Responses
            new Click().clickByclassname("idb-gradebook-question-content");//click on tick mark
            //TC row no. 141
            String feedBackText = new TextFetch().textfetchbyid("view-user-question-performance-feedback-box");
            Assert.assertEquals(feedBackText, feedback, "Instructor is unable to save feedback comment without any change in Writeboard  content");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase accessingWriteBoardEnabledQuestionsAsInstructor in class AccessingWriteBoardEnabledQuestionsAsInstructor.", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void accessingWriteBoardWhereStudentHasNotUsedWriteBoardForNonGradable()
    {
        try{
            String appendChar = "a";
            int dataIndex = 154;

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

            new Assignment().assignToStudent(dataIndex, appendChar);//assign to student
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, dataIndex);//log in as student
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//log in as teacher
            new Assignment().clickOnStudentResponse(dataIndex);//click on Student Responses
            new Click().clickByclassname("idb-gradebook-question-content");//click on tick mark
            Thread.sleep(2000);
            boolean dataFound;
            //TC row no. 142
            String text = new TextFetch().textfetchbyid("show-your-work-label");
            Assert.assertEquals(text, "No Scratchpad work submitted by student. Click here to enter feedback using Scratchpad", "\"No Scratchpad work submitted by student. Click here to enter feedback using Scratchpad\" for non gradable true false type question");
            //TC row no. 144
            boolean data = false;
            try{
                driver.findElement(By.id("saved_whiteBoard_JSONData"));
                data = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(data, false, "The Writeboard tool has data but the student has not submitted the data.");

            //TC row no. 145 - 147
            new Assignment().clickOnStudentResponse(dataIndex);//click on Student Responses
            new Click().clickByclassname("idb-gradebook-question-content");//click on tick mark
            String str = new RandomString().randomstring(5);
            new WriteBoard().instructorEnterTextInWriteBoard(str, dataIndex);//instructor enter text in  Write Board
            new Click().clickByclassname("view-user-question-performance-save-btn");//click on Save
            new Click().clickByid("next-question-performance-view");//click on Next arrow
            new Click().clickByid("prev-question-performance-view");//click on Previous arrow

            dataFound = new WriteBoard().verifyWriteBoardDataIsSaved(str);
            Assert.assertEquals(dataFound, true, "The Writeboard tool does not save the instructor entered data.");

            new Assignment().clickOnStudentResponse(dataIndex);//click on Student Responses
            new Click().clickByclassname("idb-gradebook-question-content");//click on tick mark
            //TC row no. 148
            dataFound = new WriteBoard().verifyWriteBoardDataIsSaved(str);
            Assert.assertEquals(dataFound, true, "The Writeboard tool does not have saved data if instructor opens the response page once gain.");

            String feedback = new RandomString().randomstring(5);
            new TextSend().textsendbyid(feedback, "view-user-question-performance-feedback-box");//give feedback
            new Click().clickByclassname("view-user-question-performance-save-btn");//click on Save
            new Assignment().clickOnStudentResponse(dataIndex);//click on Student Responses
            new Click().clickByclassname("idb-gradebook-question-content");//click on tick mark
            //TC row no. 149
            String feedBackText = new TextFetch().textfetchbyid("view-user-question-performance-feedback-box");
            Assert.assertEquals(feedBackText, feedback, "Instructor is unable to save feedback comment without any change in Writeboard  content");
            new Assignment().releaseGrades(dataIndex, " Release Feedback");
            new Navigator().logout();//log out

            //TC row no. 150, 151
            new Login().loginAsStudent(appendChar, dataIndex);//log in as student
            new Assignment().openAssignment(dataIndex);
            new Click().clickByclassname("student-report-continue-button");//click on Continue button
            String link = new TextFetch().textfetchbyid("white-board-feedBack-link-text");
            Assert.assertEquals(link, "View Teacher Feedback", "\"View Teacher Feedback\" link is not available to student whereas the teacher has provided the feedback and released.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase accessingWriteBoardWhereStudentHasNotUsedWriteBoard in class AccessingWriteBoardEnabledQuestionsAsInstructor.", e);
        }
    }
}
