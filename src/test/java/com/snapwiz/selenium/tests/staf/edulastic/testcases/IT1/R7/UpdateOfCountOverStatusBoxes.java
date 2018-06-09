package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.R7;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by Sumit on 9/11/2014.
 */
public class UpdateOfCountOverStatusBoxes extends Driver{

    @Test(priority = 1, enabled = true)
    public void updateOfCountOverStatusBoxesForGradableAssignment()
    {
        try
        {
            String appendChar = "a";
            String appendCharSecondStudent = "b";
            String appendCharThirdStudent = "c";
            String appendCharFourthStudent = "d";
            new SignUp().teacher(appendChar, 64);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 64);//log in as teacher
            new School().createWithOnlyName(appendChar, 64);//create school
            String classCode = new Classes().createClass(appendChar, 64);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, 64);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 64);//create student2
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharThirdStudent, classCode, 64);//create student3
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharFourthStudent, classCode, 64);//create student4
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 64);//log in as teacher
            new Assignment().create(64, "truefalse");//create an Assignment
            new Assignment().addQuestion(64, "truefalse");//add a question
            new Assignment().assignToStudent(64, appendChar);//assign to student1 as gradable assignment
            new Assignment().updateAssignment(64, appendCharSecondStudent);//assign to student2
            new Assignment().updateAssignment(64, appendCharThirdStudent);//assign to student3
            new Assignment().updateAssignment(64, appendCharFourthStudent);//assign to student4

            List<Integer> allCount;
            allCount = new Assignment().allStatusBoxCount(64);//get the count of all status box
            List<Integer> expected = new ArrayList<>();
            expected.add(4); expected.add(0); expected.add(0); expected.add(0);
            Assert.assertEquals(allCount,expected, "Status count boxes are not displaying accurate count after assigning the gradable assignment.");
            expected.clear();//clear the List
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, 64);//log in as student 1
            new Assignment().openAssignment(64);//just open the Assignment to make it in progress
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 64);//log in as teacher
            allCount = new Assignment().allStatusBoxCount(64);//get the count of all status box
            expected.add(3); expected.add(1); expected.add(0); expected.add(0);
            Assert.assertEquals(allCount,expected, "Status count boxes are not displaying accurate count after starting the gradable assignment by student1.");
            expected.clear();//clear the List
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, 64);//log in as student 1
            new Assignment().submitAssignment(64);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 64);//log in as teacher
            allCount = new Assignment().allStatusBoxCount(64);//get the count of all status box
            expected.add(3); expected.add(0); expected.add(0); expected.add(1);//it will be graded because the assignment is auto graded
            Assert.assertEquals(allCount,expected, "Status count boxes are not displaying accurate count after submitting the gradable assignment by student1.");
            expected.clear();//clear the List
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendCharSecondStudent, 64);//log in as student2
            new Assignment().submitAssignment(64);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendCharThirdStudent, 64);//log in as student3
            new Assignment().submitAssignment(64);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendCharFourthStudent, 64);//log in as student4
            new Assignment().submitAssignment(64);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 64);//log in as teacher
            allCount = new Assignment().allStatusBoxCount(64);//get the count of all status box
            expected.add(0); expected.add(0); expected.add(0); expected.add(4);//it will be graded because the assignment is auto graded
            Assert.assertEquals(allCount,expected, "Status count boxes are not displaying accurate count after submitting the gradable assignment by all the students of the class.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase updateOfCountOverStatusBoxesForGradableAssignment in class StatusOfAssignment.", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void updateOfCountOverStatusBoxesForNonGradableAssignment()
    {
        try
        {
            String appendChar = "a";
            String appendCharSecondStudent = "b";
            String appendCharThirdStudent = "c";
            String appendCharFourthStudent = "d";
            new SignUp().teacher(appendChar, 68);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 68);//log in as teacher
            new School().createWithOnlyName(appendChar, 68);//create school
            String classCode = new Classes().createClass(appendChar, 68);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, 68);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 68);//create student2
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharThirdStudent, classCode, 68);//create student3
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharFourthStudent, classCode, 68);//create student4
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 68);//log in as teacher
            new Assignment().create(68, "truefalse");//create an Assignment
            new Assignment().addQuestion(68, "truefalse");//add a question
            new Assignment().assignToStudent(68, appendChar);//assign to student1 as non-gradable assignment
            new Assignment().updateAssignment(68, appendCharSecondStudent);//assign to student2
            new Assignment().updateAssignment(68, appendCharThirdStudent);//assign to student3
            new Assignment().updateAssignment(68, appendCharFourthStudent);//assign to student4

            List<Integer> allCount;
            allCount = new Assignment().allStatusBoxCount(68);//get the count of all status box
            List<Integer> expected = new ArrayList<>();
            expected.add(4); expected.add(0); expected.add(0); expected.add(0);
            Assert.assertEquals(allCount,expected, "Status count boxes are not displaying accurate count after assigning the non-gradable assignment.");
            expected.clear();//clear the List
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, 68);//log in as student 1
            new Assignment().openAssignment(68);//just open the Assignment to make it in progress
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 68);//log in as teacher
            allCount = new Assignment().allStatusBoxCount(68);//get the count of all status box
            expected.add(3); expected.add(1); expected.add(0); expected.add(0);
            Assert.assertEquals(allCount,expected, "Status count boxes are not displaying accurate count after starting the non-gradable assignment by student1.");
            expected.clear();//clear the List
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, 68);//log in as student 1
            new Assignment().submitAssignment(68);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 68);//log in as teacher
            allCount = new Assignment().allStatusBoxCount(68);//get the count of all status box
            expected.add(3); expected.add(0); expected.add(1); expected.add(0);
            Assert.assertEquals(allCount,expected, "Status count boxes are not displaying accurate count after submitting the non-gradable assignment by student1.");
            expected.clear();//clear the List
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendCharSecondStudent, 68);//log in as student2
            new Assignment().submitAssignment(68);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendCharThirdStudent, 68);//log in as student3
            new Assignment().submitAssignment(68);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendCharFourthStudent, 68);//log in as student4
            new Assignment().submitAssignment(68);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 68);//log in as teacher
            allCount = new Assignment().allStatusBoxCount(68);//get the count of all status box
            expected.add(0); expected.add(0); expected.add(4); expected.add(0);
            Assert.assertEquals(allCount,expected, "Status count boxes are not displaying accurate count after submitting the non-gradable assignment by all the students of the class.");
            expected.clear();//clear the List
            new Assignment().releaseGrades(68, " Release Feedback");//click on Release Feedback for All
            allCount = new Assignment().allStatusBoxCount(68);//get the count of all status box
            expected.add(0); expected.add(0); expected.add(0); expected.add(4);
            Assert.assertEquals(allCount,expected, "Status count boxes are not displaying accurate count after feedback is released for all students of that non-gradable assignment.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase updateOfCountOverStatusBoxesForGradableAssignment in class StatusOfAssignment.", e);
        }
    }
}
