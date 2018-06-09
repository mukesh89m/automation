package com.snapwiz.selenium.tests.staf.learningspaces.testcases.smoke.nongradableassignment;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by priyanka on 22-02-2016.
 */
public class NonGradableAssignment extends Driver {

    @Test(priority=1,enabled = true)
    public void nonGradAbleAssignment()
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments=PageFactory.initElements(driver, Assignments.class);

            ReportUtil.log("Description","This test case validates creation of all types of questions,instructor assign nongradable assignment to class section,assignment submitted by students,status verification at student and instructor side,","info");

            new Assignment().create(1);
            new Assignment().addQuestions(1, "all", "");
            new Assignment().addQuestions(1,"multipart","");

            ReportUtil.log("Create Question", "Created all types of questions", "info");

            new LoginUsingLTI().ltiLogin("2");//login as student1
            ReportUtil.log("Student1 Login", "Student1 logged in successfully", "Pass");

            new LoginUsingLTI().ltiLogin("3");//login as student2
            ReportUtil.log("Student2 Login", "Student2 logged in successfully", "Pass");

            new LoginUsingLTI().ltiLogin("4");//login as student3
            ReportUtil.log("Student3 Login", "Student3 logged in successfully", "Pass");

            new LoginUsingLTI().ltiLogin("1");//login as instructor
            ReportUtil.log("Instructor Login", "Instructor logged in successfully", "Pass");
            new Assignment().assignAssignmentWithDueDate(1);
            ReportUtil.log("Instructor assign assignment", "Instructor assigned non gradable assignment to class section", "Pass");

            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            new Assignment().verifyClassAssignmentStatus(1,"Available for Students");

            new LoginUsingLTI().ltiLogin("2");	//login as a student1
            ReportUtil.log("Student1 Login", "Student1 logged in successfully", "Pass");
            new Assignment().checkStatusCountInStudentAssignmentPage(5);
            new Assignment().submitAssignmentAsStudent(1);
            new Assignment().checkStatusCountInStudentAssignmentPage(2);

            new LoginUsingLTI().ltiLogin("3");	//login as a student2
            ReportUtil.log("Student2 Login", "Student2 logged in successfully", "Pass");
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            for (int i = 0; i <=1;     i++) {
                new AttemptQuestion().trueFalse(false, "correct", 1);
                new Assignment().nextButtonInQuestionClick();
            }
            new AttemptQuestion().multipleChoice(false, "correct", 1);
            new Assignment().nextButtonInQuestionClick();
            new AttemptQuestion().multipleSelection(false, "correct", 1);
            new Assignment().nextButtonInQuestionClick();
            new AttemptQuestion().textEntry(false, "correct", 1);
            new Assignment().nextButtonInQuestionClick();
            new Assignment().checkStatusCountInStudentAssignmentPage(3);

            new LoginUsingLTI().ltiLogin("1");//login as instructor
            ReportUtil.log("Instructor Login", "Instructor logged in successfully", "Pass");
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            new Assignment().verifyClassAssignmentStatus(1,"Available for Students");
            Thread.sleep(300000);

            new LoginUsingLTI().ltiLogin("1");//login as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            new Assignment().verifyClassAssignmentStatus(1,"Review in Progress");

            new LoginUsingLTI().ltiLogin("3");	//login as a student2
            ReportUtil.log("Student2 Login", "Student2 logged in successfully", "Pass");
            new Assignment().checkStatusCountInStudentAssignmentPage(6);

            new LoginUsingLTI().ltiLogin("4");	//login as a student3
            ReportUtil.log("Student3 Login", "Student3 logged in successfully", "Pass");
            new Assignment().checkStatusCountInStudentAssignmentPage(4);

            new LoginUsingLTI().ltiLogin("1");//login as instructor
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            new Assignment().releaseGrades(1,"Release Feedback for All");
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            new Assignment().verifyClassAssignmentStatus(1, "Reviewed");

            new LoginUsingLTI().ltiLogin("3");	//login as a student2
            ReportUtil.log("Student2 Login", "Student2 logged in successfully", "Pass");
            new Navigator().NavigateTo("Assignments"); //navigate to Assignments
            CustomAssert.assertEquals(assignments.status_submitted.getText().trim(), "Reviewed", "Verify assignment status","the status of the activity is \"Reviewed\"","the status of the activity is \"Reviewed\"");

            new LoginUsingLTI().ltiLogin("4");	//login as a student3
            ReportUtil.log("Student3 Login", "Student3 logged in successfully", "Pass");
            new Navigator().NavigateTo("Assignments"); //navigate to Assignments
            CustomAssert.assertEquals(assignments.status_submitted.getText().trim(), "Reviewed", "Verify assignment status","the status of the activity is \"Reviewed\"","the status of the activity is \"Reviewed\"");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in test case nonGradAbleAssignment of class NonGradableAssignment" + e);
        }
    }

}
