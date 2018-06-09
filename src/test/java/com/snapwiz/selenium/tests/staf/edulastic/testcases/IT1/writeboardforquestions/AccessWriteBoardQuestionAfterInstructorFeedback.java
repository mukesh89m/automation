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
 * Created by sumit on 19/12/14.
 */
public class AccessWriteBoardQuestionAfterInstructorFeedback extends Driver{

    @Test(priority = 1, enabled = true)
    public void accessWriteBoardQuestionAfterInstructorFeedbackForGradable(){
        try{
            String appendChar = "g";
            String appendChar2 = "h";
            int dataIndex = 162;
            String writeboarddata = ReadTestData.readDataByTagName("", "writeboarddata", Integer.toString(dataIndex));

            new SignUp().teacher(appendChar, dataIndex);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//log in as teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode = new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, dataIndex);//create student1
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar2, classCode, dataIndex);//create student2
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//log in as teacher
            new Assignment().create(dataIndex, "truefalse");
            new Assignment().addQuestion(dataIndex, "multiplechoice");
            new Assignment().assignToStudent(dataIndex, appendChar);//assign to student
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar2, dataIndex);//login as student2
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, dataIndex);//login as student1
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//log in as teacher
            new Assignment().clickOnStudentResponse(dataIndex);//click on Student Responses
            new Click().clickByclassname("idb-gradebook-question-content");//click on tick mark
            String feedback = new RandomString().randomstring(5);
            new TextSend().textsendbyid(feedback, "view-user-question-performance-feedback-box");//give feedback
            new Click().clickByclassname("view-user-question-performance-save-btn");//click on Save
            String instructorData = new RandomString().randomstring(5);
            new WriteBoard().instructorEnterTextInWriteBoard(instructorData, dataIndex);//instructor enter text in  Write Board
            new Click().clickByclassname("view-user-question-performance-save-btn");//click on Save
            new Click().clickByid("next-question-performance-view");//click on Next arrow
            new Assignment().releaseGrades(dataIndex, " Release Grade");
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, dataIndex);//login as student1
            new Assignment().openAssignment(dataIndex);//open the assignment
            new Click().clickByclassname("student-report-continue-button");//click on Continue button

            //TC row no. 170
            String viewWork = new TextFetch().textfetchbyid("show-your-work-label");
            Assert.assertEquals(viewWork, "View your work", "\"View your work\" link is not present for the question for which student has used the writeboard");


            //TC row no. 162, 171
            boolean dataFound;
            dataFound = new WriteBoard().verifyInstructorWriteBoardDataIsSavedInStudentSide(instructorData);
            Assert.assertEquals(dataFound, true, "For a particular question if teacher has used Writeboard  it is not shown as a image for the student by clicking on \"View teacher feedback\".");

            //TC row no. 169
            String writeBoardText = new TextFetch().textfetchbyid("white-board-feedBack-link-text");
            Assert.assertEquals(writeBoardText, "View Teacher Feedback", "\"View Teacher Feedback\" link is not present for the question for which instructor has used the writeboard");

            //TC row no. 165
            String teacherFeedbackLabel = new TextFetch().textfetchbyclass("response-and-feedback-block-header");
            Assert.assertEquals(teacherFeedbackLabel, "Teacher Feedback", "\"Teacher Feedback:\" label is not present for the question for which teacher has given feedback.");

            String teacherFeedback = new TextFetch().textfetchbyid("view-user-question-performance-feedback-box");
            Assert.assertEquals(teacherFeedback, feedback, "Teacher feedback content is not present for the question for which teacher has given feedback.");

            //TC row no. 166
            String points = new TextFetch().textfetchbyclass("report-points-container");
            Assert.assertEquals(points, "Points: 1", "Marks awarded for the question is not shown to student.");


            dataFound = new WriteBoard().verifyWriteBoardDataIsSaved(writeboarddata);
            Assert.assertEquals(dataFound, true, "For a particular question if student has used Writeboard  it is not shown to student after grade is released.");
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar2, dataIndex);//login as student2
            new Assignment().openAssignment(dataIndex);//open the assignment
            new Click().clickByclassname("student-report-continue-button");//click on Continue button

            boolean dataFound1 = false;
            //TC row no. 167
            try{
                driver.findElement(By.id("whiteBoard_feedback_JSONData"));//search for feedback
                dataFound1 = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(dataFound1, false, "For a particular question if teacher has not used Writeboard  it is still shown as a image for the student by clicking on \"View teacher feedback\".");

        }
        catch (Exception e){
            Assert.fail("Exception in testcase accessWriteBoardQuestionAfterInstructorFeedbackForGradable in class AccessWriteBoardQuestionAfterInstructorFeedbackForGradable.", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void accessWriteBoardQuestionAfterInstructorFeedbackForNonGradable(){
        try{
            String appendChar = "a";
            String appendChar2 = "b";
            int dataIndex = 172;
            String writeboarddata = ReadTestData.readDataByTagName("", "writeboarddata", Integer.toString(dataIndex));

            new SignUp().teacher(appendChar, dataIndex);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//log in as teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode = new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, dataIndex);//create student1
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar2, classCode, dataIndex);//create student2
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//log in as teacher
            new Assignment().create(dataIndex, "truefalse");
            new Assignment().addQuestion(dataIndex, "multiplechoice");
            new Assignment().assignToStudent(dataIndex, appendChar);//assign to student
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar2, dataIndex);//login as student2
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, dataIndex);//login as student1
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//log in as teacher
            new Assignment().clickOnStudentResponse(dataIndex);//click on Student Responses
            new Click().clickByclassname("idb-gradebook-question-content");//click on tick mark
            String feedback = new RandomString().randomstring(5);
            new TextSend().textsendbyid(feedback, "view-user-question-performance-feedback-box");//give feedback
            new Click().clickByclassname("view-user-question-performance-save-btn");//click on Save
            String instructorData = new RandomString().randomstring(5);
            new WriteBoard().instructorEnterTextInWriteBoard(instructorData, dataIndex);//instructor enter text in  Write Board
            new Click().clickByclassname("view-user-question-performance-save-btn");//click on Save
            new Click().clickByid("next-question-performance-view");//click on Next arrow
            new Assignment().releaseGrades(dataIndex, " Release Feedback");
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, dataIndex);//login as student1
            new Assignment().openAssignment(dataIndex);//open the assignment
            new Click().clickByclassname("student-report-continue-button");//click on Continue button

            String viewWork = new TextFetch().textfetchbyid("show-your-work-label");
            Assert.assertEquals(viewWork, "View your work", "\"View your work\" link is not present for the question for which student has used the writeboard");


            boolean dataFound;
            dataFound = new WriteBoard().verifyInstructorWriteBoardDataIsSavedInStudentSide(instructorData);
            Assert.assertEquals(dataFound, true, "For a particular question if teacher has used Writeboard  it is not shown as a image for the student by clicking on \"View teacher feedback\".");

            String writeBoardText = new TextFetch().textfetchbyid("white-board-feedBack-link-text");
            Assert.assertEquals(writeBoardText, "View Teacher Feedback", "\"View Teacher Feedback\" link is not present for the question for which instructor has used the writeboard");

            String teacherFeedbackLabel = new TextFetch().textfetchbyclass("response-and-feedback-block-header");
            Assert.assertEquals(teacherFeedbackLabel, "Teacher Feedback", "\"Teacher Feedback:\" label is not present for the question for which teacher has given feedback.");

            String teacherFeedback = new TextFetch().textfetchbyclass("view-user-question-performance-feedback-box");
            Assert.assertEquals(teacherFeedback, feedback, "Teacher feedback content is not present for the question for which teacher has given feedback.");

            String points = new TextFetch().textfetchbyclass("report-points-container");
            Assert.assertEquals(points, "Points: 1", "Marks awarded for the question is not shown to student.");


            dataFound = new WriteBoard().verifyWriteBoardDataIsSaved(writeboarddata);
            Assert.assertEquals(dataFound, true, "For a particular question if student has used Writeboard  it is not shown to student after grade is released.");
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar2, dataIndex);//login as student2
            new Assignment().openAssignment(dataIndex);//open the assignment
            new Click().clickByclassname("student-report-continue-button");//click on Continue button

            boolean dataFound1 = false;
            try{
                driver.findElement(By.id("whiteBoard_feedback_JSONData"));//search for feedback
                dataFound1 = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(dataFound1, false, "For a particular question if teacher has not used Writeboard  it is still shown as a image for the student by clicking on \"View teacher feedback\".");

        }
        catch (Exception e){
            Assert.fail("Exception in testcase accessWriteBoardQuestionAfterInstructorFeedbackForNonGradable in class AccessWriteBoardQuestionAfterInstructorFeedbackForGradable.", e);
        }
    }
}
