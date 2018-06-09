package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R14;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TabClose;

public class StatusBoxCount extends Driver{

    @Test(priority=1,enabled=true)
    public void nongradeableAssignmentSubmitInAnotherBrowserAndStatusCount()
    {
        try
        {
            new Assignment().create(1783);

            new LoginUsingLTI().ltiLogin("17831");  //Creating student with ID 17831student
            new LoginUsingLTI().ltiLogin("17832");  //Creating student with ID 17832student

            new LoginUsingLTI().ltiLogin("1783"); //  Logging in as instructor
            new Assignment().assignToStudent(1783);  //Assigning assignment to student 17391

            new LoginUsingLTI().ltiLogin("1783"); //  Logging in as instructor
            int notstarted = new Assignment().statusBoxCount(1783, "Not Started");
            if(notstarted != 2) Assert.fail("The Not Started status count of assignment not equal to 2 after assigning it to two students of the class");

            new LoginUsingLTI().ltiLogin("17831");
            new Assignment().submitAssignmentAsStudent(1783);

            new LoginUsingLTI().ltiLogin("1783"); //  Logging in as instructor
            notstarted = new Assignment().statusBoxCount(1783, "Not Started");
            int submitted = new Assignment().statusBoxCount(1783, "Submitted");
            if(notstarted != 1) Assert.fail("Not Started count not equal to 1 in the status box after assignement submitted by first student");
            if(submitted != 1) Assert.fail("Submitted count not equal to 1 in the status box after assignement submitted by first student");

            new LoginUsingLTI().ltiLogin("17832");
            new Assignment().submitAssignmentAsStudent(1783);

            new LoginUsingLTI().ltiLogin("1783"); //  Logging in as instructor
            notstarted = new Assignment().statusBoxCount(1783, "Not Started");
            submitted = new Assignment().statusBoxCount(1783, "Submitted");
            if(notstarted != 0) Assert.fail("Not Started count not equal to 1 in the status box after assignement submitted by first student");
            if(submitted != 2) Assert.fail("Submitted count not equal to 1 in the status box after assignement submitted by first student");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase nongradeableAssignmentSubmitInAnotherBrowserAndStatusCount in class StatusBoxCount",e);
        }
    }

    @Test(priority=2,enabled=true)
    public void gradeableAssignmentStatus()
    {
        try
        {
            new Assignment().create(1785);
            new LoginUsingLTI().ltiLogin("17851");  //Creating student with ID 17421student
            new LoginUsingLTI().ltiLogin("1785"); //  Logging in as instructor
            new Assignment().assignToStudent(1785);  //Assigning assignment to student 17421
            new LoginUsingLTI().ltiLogin("17851");
            new Assignment().submitAssignmentAsStudent(1785);
            new LoginUsingLTI().ltiLogin("1785"); // Login as instructor to release grades
            new Assignment().releaseGrades(1785, "Release Grade for All");
            new LoginUsingLTI().ltiLogin("1785"); //Login again as instructor to verify assignment page as per testcase
            new Assignment().statusValidate(1785, "Status:  Graded");
            new Assignment().gradesValidation(1785);
            new TabClose().tabClose(1);
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase gradeableAssignmentStatus in class StatusBoxCount",e);
        }
    }
    @Test(priority=3,enabled=false) //the css value is not getting fetched so disabling this testcase
    public void statusBoxColors()
    {
        try
        {
            String notstartedcolor = ReadTestData.readDataByTagName("", "notstartedcolor", "1799");
            String inprogresscolor = ReadTestData.readDataByTagName("", "inprogresscolor", "1799");
            String submittedcolor = ReadTestData.readDataByTagName("", "submittedcolor", "1799");
            String gradedcolor = ReadTestData.readDataByTagName("", "gradedcolor", "1799");
            new LoginUsingLTI().ltiLogin("1799"); // Login as instructor to verify the status box of an assignment in assignmet page
            new Navigator().NavigateTo("Assignments");
            System.out.println("Not started "+driver.findElement(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-first not-started']")).getCssValue("border"));
            if(!driver.findElement(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-first not-started']")).getCssValue("border").equals(notstartedcolor))
                Assert.fail("Border color of Not started box not as expected");
            if(!driver.findElement(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-second in-progress']")).getCssValue("border").equals(inprogresscolor))
                Assert.fail("Border color of In Progress box not as expected");
            if(!driver.findElement(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-third submitted']")).getCssValue("border").equals(submittedcolor))
                Assert.fail("Border color of Submitted box not as expected");
            if(!driver.findElement(By.cssSelector("div[class='ls-instructor-activity-cards ls-instructor-cards-fourth submitted']")).getCssValue("border").equals(gradedcolor))
                Assert.fail("Border color of Graded box not as expected");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase statusBoxColors in class StatusBoxCount",e);
        }
    }


}
