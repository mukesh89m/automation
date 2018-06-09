package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.R7;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 * Created by Sumit on 9/11/2014.
 */
public class StatusOfAssignment extends Driver {

    @Test(priority = 1, enabled = true)
    public void statusOfGradableAssignment()
    {
        try
        {
            String appendChar = "a";
            String appendCharSecondStudent = "b";
            new SignUp().teacher(appendChar, 39);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 39);//log in as teacher
            new School().createWithOnlyName(appendChar, 39);//create school
            String classCode = new Classes().createClass(appendChar, 39);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, 39);//create student1
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 42);//create student2
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 39);//log in as teacher
            new Assignment().create(39, "truefalse");//create an Assignment
            new Assignment().addQuestion(39, "truefalse");//add a question
            new Assignment().assignToStudent(39, appendChar);//assign to student1;
            new Assignment().updateAssignment(39, appendCharSecondStudent);//assign to student1;
            String status = new Assignment().assignmentStatus(39);//get the status of assignment
            Assert.assertEquals(status, "Awaiting Submission", "Gradable Assignment's status is not \"Awaiting Submission\" after assigning the assignment");
            new Navigator().logout();//log out


            new Login().loginAsStudent(appendChar, 39);//log in as student 1
            new Assignment().submitAssignment(39);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 39);//log in as teacher
            String status1 = new Assignment().assignmentStatus(39);//get the status of assignment
            Assert.assertEquals(status1, "Awaiting Submission", "Gradable Assignment's status is not \"Awaiting Submission\" though the assignment is still not submitted by student2.");
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendCharSecondStudent, 39);//log in as student 2
            new Assignment().submitAssignment(39);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 39);//log in as teacher
            String status2 = new Assignment().assignmentStatus(39);//get the status of assignment
            Assert.assertEquals(status2, "Grading in Progress", "Gradable Assignment's status is not \"Grading in Progress\" after submission by all students of the class.");
            new Assignment().releaseGrades(39, " Release Grade");//click on Release Release Grade for All
            new Navigator().navigateTo("assignment");//navigate To Assignments
            String status3 = new Assignment().assignmentStatus(39);//get the status of assignment
            Assert.assertEquals(status3, "Graded", "Gradable Assignment's status is not \"Graded\" after grade is released by the teacher.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase statusOfGradableAssignment in class StatusOfAssignment.", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void statusOfNonGradableAssignmentA()
    {
        try
        {
            String appendChar = "a";
            String appendCharSecondStudent = "b";
            new SignUp().teacher(appendChar, 40);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 40);//log in as teacher
            new School().createWithOnlyName(appendChar, 40);//create school
            String classCode = new Classes().createClass(appendChar, 40);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, 40);//create student1
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 42);//create student2
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 40);//log in as teacher
            new Assignment().create(40, "truefalse");//create an Assignment
            new Assignment().addQuestion(40, "truefalse");//add a question
            new Assignment().assignToStudent(40, appendChar);//assign to student1;
            new Assignment().updateAssignment(40, appendCharSecondStudent);//assign to student1;
            String status = new Assignment().assignmentStatus(40);//get the status of assignment
            Assert.assertEquals(status, "Awaiting Submission", "Non-Gradable Assignment's status is not \"Awaiting Submission\" after assigning the assignment");
            new Navigator().logout();//log out


            new Login().loginAsStudent(appendChar, 40);//log in as student 1
            new Assignment().submitAssignment(40);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 40);//log in as teacher
            String status1 = new Assignment().assignmentStatus(40);//get the status of assignment
            Assert.assertEquals(status1, "Awaiting Submission", "Non-Gradable Assignment's status is not \"Awaiting Submission\" though the assignment is still not submitted by student2.");
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendCharSecondStudent, 40);//log in as student 2
            new Assignment().submitAssignment(40);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 40);//log in as teacher
            String status2 = new Assignment().assignmentStatus(40);//get the status of assignment
            Assert.assertEquals(status2, "Review in Progress", "Non-Gradable Assignment's status is not \"Review in Progress\" after submission by all students of the class.");
            new Assignment().releaseGrades(40, " Release Feedback");//click on  Release Feedback for All
            new Navigator().navigateTo("assignment");//navigate To Assignments
            String status3 = new Assignment().assignmentStatus(40);//get the status of assignment
            Assert.assertEquals(status3, "Reviewed", "Non-Gradable Assignment's status is not \"Reviewed\" after feedback is released by the teacher.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase statusOfNonGradableAssignment in class StatusOfAssignment.", e);
        }
    }
}
