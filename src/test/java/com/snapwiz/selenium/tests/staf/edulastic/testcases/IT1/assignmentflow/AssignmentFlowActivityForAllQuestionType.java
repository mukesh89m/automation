package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.assignmentflow;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Calendar;

/**
 * Created by pragya on 29-12-2014.
 */
public class AssignmentFlowActivityForAllQuestionType extends Driver {
    @Test(priority = 1, enabled = true)
    public void assignmentFlowActivity() {
        try {

            //Tc row no 81
            String appendChar = "a";
            int index = 81;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().create(index, "all");//create an Assignment
            new Assignment().addQuestion(index, "all");//add a question
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, index);
            new Navigator().navigateTo("assignment");//Navigate to assignment
            String assignmentName=new TextFetch().textfetchbycssselector("h4.as-title");
            if (!assignmentName.contains("IT1_assignmentFlow_Assessment81"))
                Assert.fail("Student is not able to access the Assignment from Assignment page");
            new Navigator().navigateTo("dashboard");
            String assignmentName1 = new TextFetch().textfetchbycssselector("h4.as-title");
            if (!assignmentName1.contains("IT1_assignmentFlow_Assessment81"))
                Assert.fail("Student is not able to access the Assignment from Dashboard page");

            //TC row no 82
            Calendar calendar=Calendar.getInstance();
            int month = (calendar.get((Calendar.MONTH)));
            int year = (calendar.get((Calendar.YEAR)));
            String actualDate = new Calender().getCurrentMonthName(month+1).substring(0,3) + " 28, "+year+" 12:00 AM";
            String dueDate = new TextFetch().textfetchbycssselector("div[class='col-xs-12 col-sm-5']").substring(10).trim();
            Assert.assertEquals(dueDate, actualDate, "Due date is not proper");

            //Tc row no 83
            String status = new TextFetch().textfetchbycssselector("span[class='status-label cinnabar']");//get the status of assignment
            Assert.assertEquals(status, "Not Started", "Assignment's status is not \"Not Started\"");
            //Tc row no 84
            new Navigator().navigateTo("assignment");//Navigate to assignment
            new Click().clickBycssselector("h4.as-title");//click on the assignment
            new Click().clickByListClassName("true-false-student-answer-select", 0);//click on the true Question
            new Click().clickBycssselector("div[class='lsMobile-sprite quit-assignment-clock-icon']");//click on the x icon
            String inProgressStatus = new TextFetch().textfetchbycssselector("span[class='status-label tuliptree']");
            Assert.assertEquals(inProgressStatus, "In Progress", "Assignment's status is not \"In Progress\" ");

            new Assignment().submitAssignment(index);

            new Navigator().navigateTo("assignment");//Navigate to assignment
            String turnedIn = new TextFetch().textfetchbycssselector("span[class='status-label mantis']");
            Assert.assertEquals(turnedIn, "Turned In", "Assignment status is not \"Turned In\"");
            new Navigator().logout();//logout

            new Login().loginAsInstructor(appendChar, index);//log in as teacher
            new Assignment().releaseGrades(index, "Release Feedback");//click on  Release Feedback for All
            String status3 = new Assignment().assignmentStatus(index);//get the status of assignment
            System.out.println("status3 "+status3);
            Assert.assertEquals(status3,"Reviewed","Status is not displayed as Reviewed'");
            new Navigator().logout();//logout

            new Login().loginAsStudent(appendChar, index);
            new Navigator().navigateTo("assignment");//Navigate to assignment
            String graded = new TextFetch().textfetchbycssselector("span[class='status-label mantis']");
            if (!graded.contains("Reviewed"))
                Assert.fail("Grade has not increased yet");


        } catch (Exception e) {
            Assert.fail("Exception in test case  assignmentFlowActivity of class AssignmentFlowActivity", e);
        }
    }
}
