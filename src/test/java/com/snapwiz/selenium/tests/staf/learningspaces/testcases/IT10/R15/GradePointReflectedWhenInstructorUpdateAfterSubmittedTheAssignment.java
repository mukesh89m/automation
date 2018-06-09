package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R15;

import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class GradePointReflectedWhenInstructorUpdateAfterSubmittedTheAssignment extends Driver
{
    @Test(priority=1,enabled=true)
    public void  gradePointReflectedWhenInstructorUpdateAfterSubmittedTheAssignment()
    {
        try
        {
            new Assignment().create(2579);//Assignment create
            new LoginUsingLTI().ltiLogin("25791");//student create
            new LoginUsingLTI().ltiLogin("2579");//login as instructor
            new Assignment().assignToStudent(2579);//assign assignment to student
            new LoginUsingLTI().ltiLogin("25791");//login as student
            new Assignment().submitAssignmentAsStudent(2579);
            boolean summaryvalue=driver.findElement(By.className("report-chart-title")).isDisplayed();//verify summary page
            if(summaryvalue==false)
                Assert.fail("After submitting submit page its not gone to summary page");
            new LoginUsingLTI().ltiLogin("2579");
            new Assignment().provideGRadeToStudent(2579);
            Thread.sleep(3000);
            driver.findElement(By.cssSelector("div[class='idb-gradeBook-grades-section-release idb-gradeBook-release-grades-section idb-gradeBook-grades-release']")).click();
            new LoginUsingLTI().ltiLogin("25791");
            new Navigator().NavigateTo("Assignments");
            String gradetext=driver.findElement(By.cssSelector("span[class='assignment-score']")).getText();
            System.out.println(gradetext);
            if(!gradetext.contains("(0.6/1)"))
                Assert.fail("Number not reflected after update grade by instructor");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase gradePointReflectedWhenInstructorUpdateAfterSubmittedTheAssignment in class GradePointReflectedWhenInstructorUpdateAfterSubmittedTheAssignment",e);
        }
    }
    @Test(priority=2,enabled=true, dependsOnMethods={"gradePointReflectedWhenInstructorUpdateAfterSubmittedTheAssignment"})
    public void studentisabletoclickoncontiniouebutton()
    {
        try
        {
            String assignmentName = ReadTestData.readDataByTagName("", "assessmentname", "2579");
            new LoginUsingLTI().ltiLogin("25791");
            new Navigator().NavigateTo("Assignments");
            new Assignment().clickonAssignment(assignmentName);
            driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--continue']")).click();
            Thread.sleep(3000);
            int assignmentspage=driver.findElements(By.cssSelector("span[class='ls-assignment-not-due-date-title']")).size();
            if(assignmentspage<1)
                Assert.fail("After click on continue button ils not land on assignments page.");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase gradePointReflectedWhenInstructorUpdateAfterSubmittedTheAssignment in class GradePointReflectedWhenInstructorUpdateAfterSubmittedTheAssignment",e);
        }
    }


}
