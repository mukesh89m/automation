package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R14;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.By;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class StatusOfAssignments extends Driver{

    @Test(priority=1,enabled=true)
    public void assignmentscheduledforfuture()
    {
        try
        {
            new Assignment().create(1737);
            new Assignment().addQuestions(1737,"multiplechoice","");
            new LoginUsingLTI().ltiLogin("17371");  //Creating student with ID 1731student
            new LoginUsingLTI().ltiLogin("1737"); //  Logging in as instructor
            new Assignment().assignToStudent(1737);  //Assigning assignment to student 1
            new Navigator().NavigateTo("Assignments");
            driver.findElement(By.linkText("All Assignment Status")).click(); //clicking on All Assignment Status filter dropdown
            Thread.sleep(2000);
            driver.findElement(By.linkText("Scheduled")).click(); //Selecting filter as Scheduled
            Thread.sleep(3000);
            if(!driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).getText().equals("(shor) 1737 Assessment"))
                Assert.fail("The assignment name is not as expected");
            if(!driver.findElement(By.className("ls-assignment-status")).getText().trim().equals("Class Status:  Scheduled"))
                Assert.fail("The assessment status not found as Scheduled for assessment scheduled for future.");
            new LoginUsingLTI().ltiLogin("17371"); //Logging in as student to check if the scheduled assignment is there on the student dashboard
            new Navigator().NavigateTo("Course Stream");
            if(driver.findElements(By.cssSelector("span[class='ls-stream-assignment-title']")).size() != 0)
                Assert.fail("The assessment scheduled for future is available for student");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase assignmentscheduledforfuture in class StatusOfAssignments",e);
        }
    }
    @Test(priority=2,enabled=true)
    public void gradeableAssignmentAccessibleDatePast()
    {
        try
        {
            new Assignment().create(1739);
            new Assignment().addQuestions(1739,"all","");
            new LoginUsingLTI().ltiLogin("17391");  //Creating student with ID 17391student
            new LoginUsingLTI().ltiLogin("17392");  //Creating student with ID 17392student
            new LoginUsingLTI().ltiLogin("1739"); //  Logging in as instructor
            new Assignment().assignToStudent(1739);  //Assigning assignment to student 17391
            new Assignment().updateAssignment(17392,true); // Assigning assignment to student 2
            new Navigator().NavigateTo("Assignments");
            driver.findElement(By.linkText("All Assignment Status")).click(); //clicking on All Assignment Status filter dropdown
            Thread.sleep(2000);
            driver.findElement(By.linkText("Available for Students")).click(); //Selecting filter as Available for Students
            if(!driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).getText().equals("(shor) 1739 Assessment")) {
                Assert.fail("The assignment name is not as expected");
            }
            new LoginUsingLTI().ltiLogin("17391");
            new Assignment().submitAssignmentAsStudent(1739);

            new LoginUsingLTI().ltiLogin("1739"); //  Logging in as instructor
            new Assignment().statusValidate(1739,"Status:  Available for Students");

            new LoginUsingLTI().ltiLogin("17392");
            new Assignment().submitAssignmentAsStudent(1739);

            new LoginUsingLTI().ltiLogin("1739"); //  Logging in as instructor
            new Navigator().NavigateTo("Assignments");
            new Assignment().statusValidate(1739,"Status:  Needs Grading");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase gradeableAssignmentAccessibleDatePast in class StatusOfAssignments",e);
        }
    }

    @Test(priority=3,enabled=true)
    public void gradeableAssignmentStatusAsGradingInProgress()
    {
        try
        {
            new Assignment().create(1742);
            new LoginUsingLTI().ltiLogin("17421");  //Creating student with ID 17421student
            new LoginUsingLTI().ltiLogin("1742"); //  Logging in as instructor
            new Assignment().assignToStudent(1742);  //Assigning assignment to student 17421
            new LoginUsingLTI().ltiLogin("17421");
            new Assignment().submitAssignmentAsStudent(1742);
            new LoginUsingLTI().ltiLogin("1742"); //  Logging in as instructor to validate the status of assignment after the student attempts it
            new Assignment().statusValidate(1742,"Status:  Needs Grading");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase gradeableAssignmentStatusAsGradingInProgress in class StatusOfAssignments",e);
        }
    }

    @Test(priority=4,enabled=true)
    public void nonGradeableAssignmentStatusAsReviewInProgress()
    {
        try
        {
            new Assignment().create(1744);
            new LoginUsingLTI().ltiLogin("17441");  //Creating student with ID 17441student
            new LoginUsingLTI().ltiLogin("1744"); //  Logging in as instructor
            new Assignment().assignToStudent(1744);  //Assigning assignment to student 17441
            new LoginUsingLTI().ltiLogin("17441");
            new Assignment().submitAssignmentAsStudent(1744);
            new LoginUsingLTI().ltiLogin("1744"); //  Logging in as instructor to validate the status of assignment after the student attempts it
            new Assignment().statusValidate(1744,"Status:  Review in Progress");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase nonGradeableAssignmentStatusAsReviewInProgress in class StatusOfAssignments",e);
        }
    }

    @Test(priority=5,enabled=true)
    public void gradeableAssignmentReleaseGrade()
    {
        try
        {
            new Assignment().create(1746);
            new LoginUsingLTI().ltiLogin("17461");  //Creating student with ID 17441student
            new LoginUsingLTI().ltiLogin("1746"); //  Logging in as instructor
            new Assignment().assignToStudent(1746);  //Assigning assignment to student 17441
            new LoginUsingLTI().ltiLogin("17461");
            new Assignment().submitAssignmentAsStudent(1746);
            new LoginUsingLTI().ltiLogin("1746"); //  Logging in as instructor
            new Assignment().releaseGrades(1746,"Release Grade for All");
            driver.navigate().refresh();
            new Assignment().statusValidate(1746,"Status:  Graded");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase gradeableAssignmentReleaseGrade in class StatusOfAssignments",e);
        }
    }

    @Test(priority=6,enabled=true)
    public void nonGradeableAssignmentReleaseFeedback()
    {
        try
        {
            new Assignment().create(1748);
            new LoginUsingLTI().ltiLogin("17481");  //Creating student with ID 17481student
            new LoginUsingLTI().ltiLogin("1748"); //  Logging in as instructor
            new Assignment().assignToStudent(1748);  //Assigning assignment to student 17481
            new LoginUsingLTI().ltiLogin("17481");
            new Assignment().submitAssignmentAsStudent(1748);
            new LoginUsingLTI().ltiLogin("1748"); //  Logging in as instructor
            new Assignment().releaseGrades(1748,"Release Feedback for All");
            driver.navigate().refresh();
            new Assignment().statusValidate(1748,"Status:  Reviewed");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase nonGradeableAssignmentReleaseFeedback in class StatusOfAssignments",e);
        }
    }



}
