package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.R7;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 * Created by Sumit on 9/11/2014.
 */
public class VerifyGradeGraph extends Driver{

    @Test
    public void verifyGradeGraphForGradableAssignment()
    {
        try
        {
            String appendChar = "a";
            new SignUp().teacher(appendChar, 85);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 85);//log in as teacher
            new School().createWithOnlyName(appendChar, 85);//create school
            String classCode = new Classes().createClass(appendChar, 85);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, 85);//create student1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 85);//log in as teacher
            new Assignment().create(85, "truefalse");//create an Assignment
            new Assignment().addQuestion(85, "truefalse");//add a question
            new Assignment().assignToStudent(85, appendChar);//assign to student1 as gradable assignment
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, 85);//log in as student 1
            new Assignment().submitAssignment(85);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 85);//log in as teacher
            new Assignment().releaseGrades(85, " Release Grade");//click on Release Grade for All
            new Navigator().navigateTo("assignment");//navigate to Assignment
            int graph = driver.findElements(By.className("student-score-range-assignment-graph")).size();
            Assert.assertEquals(graph, 1, "View Grades option is not available under actions for gradable assignment over assignments page after teacher releases the grades.");
            String button = new TextFetch().textfetchbyclass("as-response");
            Assert.assertEquals(button,"View Grades", "View Grades option is not available under actions for gradable assignment over assignments page after teacher releases the grades.");
            new Click().clickByclassname("as-response");
            String pageHeader = new TextFetch().textfetchbycssselector("div[class='center header-title']");
            Assert.assertEquals(pageHeader,"ASSIGNMENT RESPONSES", "On clicking View Grades option for gradable assignment teacher does not navigate to Assignment responses page.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase verifyGradeGraphForGradableAssignment in class StatusOfAssignment.", e);
        }
    }

    @Test
    public void verifyGradeGraphForNonGradableAssignmentA()
    {
        try
        {
            String appendChar = "a";
            new SignUp().teacher(appendChar, 90);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 90);//log in as teacher
            new School().createWithOnlyName(appendChar, 90);//create school
            String classCode = new Classes().createClass(appendChar, 90);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, 90);//create student1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 90);//log in as teacher
            new Assignment().create(90, "truefalse");//create an Assignment
            new Assignment().addQuestion(90, "truefalse");//add a question
            new Assignment().assignToStudent(90, appendChar);//assign to student1 as non gradable assignment
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, 90);//log in as student 1
            new Assignment().submitAssignment(90);//submit the assignment
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 90);//log in as teacher
            new Assignment().releaseGrades(90, " Release Feedback");//Release Feedback for All
            new Navigator().navigateTo("assignment");//navigate to Assignment
            int graph = driver.findElements(By.className("student-score-range-assignment-graph")).size();
            Assert.assertEquals(graph, 1, "View Grades option is not available under actions for non gradable assignment over assignments page after teacher releases the grades.");
            String button = new TextFetch().textfetchbyclass("as-response");
            Assert.assertEquals(button,"View Feedback", "View Feedback option is not available under actions for non gradable assignment over assignments page after teacher releases the grades.");
            new Click().clickByclassname("as-response");
            String pageHeader = new TextFetch().textfetchbycssselector("div[class='center header-title']");
            Assert.assertEquals(pageHeader,"ASSIGNMENT RESPONSES", "On clicking View Feedback option for non gradable assignment teacher does not navigate to Assignment responses page.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase verifyGradeGraphForNonGradableAssignment in class StatusOfAssignment.", e);
        }
    }

}
