package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.R7;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 * Created by Sumit on 9/12/2014.
 */
public class UseExistingAssignment extends Driver{

    /*
    Status of gradable assignment at various level with students attempting it(an existing assignment is used for assigning)
     */
    @Test(priority = 1, enabled = true)
    public void useExistingAssignmentAsGradableAssignment()
    {
        try
        {
            String appendChar = "A";
            String appendCharSecondTeacher = "B";
            String appendCharSecondStudent = "C";
            String appendCharThirdStudent = "D";
            new SignUp().teacher(appendChar, 50);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 50);//log in as teacher
           String zipcode= new School().createWithOnlyName(appendChar, 50);//create school
            String classCode = new Classes().createClass(appendChar, 50);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, 50);//create student1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 50);//log in as teacher
            new Assignment().create(50, "truefalse");//create an Assignment
            new Assignment().addQuestion(50, "multipleselection");//add a multiple selection question
            new Assignment().assignToStudent(50, appendChar);//assign to student1;
            new Navigator().logout();//log out

            new SignUp().teacher(appendCharSecondTeacher, 50);//Sign up as teacher2
            new Login().loginAsInstructor(appendCharSecondTeacher, 50);//log in as teacher
            new School().enterAndSelectSchool(zipcode, 50, false);//select existing school
            new Navigator().findSchoolToCreateClassTeacher();//Clicking Next Step button present in 'Find School' page to navigate to create class page
            String classCode1 = new Classes().createClass(appendChar, 50);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode1, 50);//create student2
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendCharThirdStudent, classCode1, 50);//create student2
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendCharSecondTeacher, 50);//log in as teacher2
            new Assignment().useExistingAssignment(50, appendCharSecondStudent, appendCharThirdStudent);//assign existing assignment student2
            String status = new Assignment().assignmentStatus(50);//get the status of assignment
            Assert.assertEquals(status, "Awaiting Submission", "Gradable Assignment's status is not \"Awaiting Submission\" after assigning the existing assignment");
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendCharSecondStudent, 50);//log in as student 2
            new Assignment().submitAssignment(50);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendCharSecondTeacher, 50);//log in as teacher
            String status1 = new Assignment().assignmentStatus(50);//get the status of assignment
            Assert.assertEquals(status1, "Awaiting Submission", "Gradable Assignment's status is not \"Awaiting Submission\" though the existing assignment is still not submitted by student2.");
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendCharThirdStudent, 50);//log in as student 3
            new Assignment().submitAssignment(50);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendCharSecondTeacher, 50);//log in as teacher
            String status2 = new Assignment().assignmentStatus(50);//get the status of assignment
            Assert.assertEquals(status2, "Grading in Progress", "Gradable Assignment's status is not \"Grading in Progress\" after submission of existing assignment by all students of the class.");
            new Assignment().releaseGrades(50, " Release Grade for All");//click on Release Release Grade for All
            new Navigator().navigateTo("assignment");//navigate To Assignments
            String status3 = new Assignment().assignmentStatus(50);//get the status of assignment
            Assert.assertEquals(status3, "Graded", "Gradable Assignment's status is not \"Graded\" after grade is released by the teacher for that existing assignment.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase useExistingAssignmentAsGradableAssignment in class UseExistingAssignment.", e);
        }
    }

    /*
    Status of non-gradable assignment at various level with students attempting it(an existing assignment is used for assigning)
     */
    @Test(priority = 2, enabled = true)
    public void useExistingAssignmentAsNonGradableAssignment()
    {
        try
        {
            String appendChar = "A";
            String appendCharSecondTeacher = "B";
            String appendCharSecondStudent = "C";
            String appendCharThirdStudent = "D";
            new SignUp().teacher(appendChar, 55);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 55);//log in as teacher
           String zipcode= new School().createWithOnlyName(appendChar, 55);//create school
            String classCode=new Classes().createClass(appendChar, 55,"Grade 2", "Mathematics", "Math - Common Core");//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, 55);//create student1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 55);//log in as teacher
            new Assignment().create(55, "truefalse");//create an Assignment
            new Assignment().addQuestion(55, "multipleselection");//add a multiple selection question
            new Assignment().assignToStudent(55, appendChar);//assign to student1;
            new Navigator().logout();//log out

            new SignUp().teacher(appendCharSecondTeacher, 55);//Sign up as teacher2
            new Login().loginAsInstructor(appendCharSecondTeacher, 55);//log in as teacher
            new School().enterAndSelectSchool(zipcode, 55, false);//select existing school
            new Navigator().findSchoolToCreateClassTeacher();//Clicking Next Step button present in 'Find School' page to navigate to create class page
            String classCode1 = new Classes().createClass(appendChar, 55);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode1, 55);//create student2
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendCharThirdStudent, classCode1, 55);//create student2
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendCharSecondTeacher, 55);//log in as teacher2
            new Assignment().useExistingAssignment(55, appendCharSecondStudent, appendCharThirdStudent);//assign existing assignment student2
            String status = new Assignment().assignmentStatus(55);//get the status of assignment
            Assert.assertEquals(status, "Awaiting Submission", "Non Gradable Assignment's status is not \"Awaiting Submission\" after assigning the existing assignment");
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendCharSecondStudent, 55);//log in as student 2
            new Assignment().submitAssignment(55);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendCharSecondTeacher, 55);//log in as teacher
            String status1 = new Assignment().assignmentStatus(55);//get the status of assignment
            Assert.assertEquals(status1, "Awaiting Submission", "Non Gradable Assignment's status is not \"Awaiting Submission\" though the existing assignment is still not submitted by student2.");
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendCharThirdStudent, 55);//log in as student 3
            new Assignment().submitAssignment(55);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendCharSecondTeacher, 55);//log in as teacher
            String status2 = new Assignment().assignmentStatus(55);//get the status of assignment
            Assert.assertEquals(status2, "Review in Progress", "Existing Non-Gradable Assignment's status is not \"Review in Progress\" after submission by all students of the class.");
            new Assignment().releaseGrades(55, " Release Feedback for All");//click on Release Feedback for All
            new Navigator().navigateTo("assignment");//navigate To Assignments
            String status3 = new Assignment().assignmentStatus(55);//get the status of assignment
            Assert.assertEquals(status3, "Reviewed", "Existing Non-Gradable Assignment's status is not \"Reviewed\" after feedback is released by the teacher.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase useExistingAssignmentAsNonGradableAssignment in class UseExistingAssignment.", e);
        }
    }
}
