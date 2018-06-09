package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R167;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.ProficiencyReport;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Sumit on 8/20/2014.
 */
public class StudentAbleToViewOverallScoreForAllAssignment extends Driver {

    @Test(priority = 1, enabled = true)
    public void studentAbleToViewOverallScoreForAllAssignment()
    {
        try
        {

            new LoginUsingLTI().ltiLogin("2");    //login as student
            new Navigator().NavigateTo("Assignments");  //go to Assignments page

            //TC row no. 2..."1. Login as new studentA   2. Go to Assignments page."...Only assignment filter and status boxes should be present
            int dropDown = driver.findElements(By.cssSelector("a[title='All Assignments']")).size();
            if(dropDown == 0)
                Assert.fail("In Assignments page of a new student side the assignment filter is absent.");

            //TC row no. 3...Overall Score box should not be present
            int overallScore = driver.findElements(By.id("ls-overall-score-btn")).size();
            if(overallScore != 0)
                Assert.fail("In Assignments page of new student side the over score field is present.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case studentAbleToViewOverallScoreForAllAssignment in class StudentAbleToViewOverallScoreForAllAssignment.", e);
        }
    }
    @Test(priority = 2, enabled = true)
    public void overallScoreForGradableAssignment()
    {
        try
        {
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new Assignment().create(4);

            new LoginUsingLTI().ltiLogin("4_1");    //create a student
            new LoginUsingLTI().ltiLogin("4");//login as instructor
            new Assignment().assignToStudent(4);

            new LoginUsingLTI().ltiLogin("4_1");//login as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();//open lesson assignment
            Thread.sleep(10000);
            new AttemptQuestion().trueFalse(false,"correct",4_1);
            driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")).click();

           // new Assignment().submitAssignmentAsStudent(4);

            new LoginUsingLTI().ltiLogin("4");//log in instructor
            new Assignment().releaseGrades(4, "Release Grade for All");

            new LoginUsingLTI().ltiLogin("4_1");    //login as student
            new Navigator().NavigateTo("Assignments");  //go to Assignments page

            //TC row no. 4..."Overall Score: <Score>" box should be displayed at the top right side of the assignments page
            String overallScoreLabel = driver.findElement(By.className("ls-overall-score-text")).getText();
            if(!overallScoreLabel.contains("Overall Score:"))
                Assert.fail("\"Overall Score:\" score label is absent in Assignments page of student side.");

            //TC row no. 5
            String overallScore = driver.findElement(By.className("ls-overall-score-percent")).getText();
            if(!overallScore.contains("100%"))
                Assert.fail("Overall Score is absent in Assignments page of student side.");

            //TC row no. 6...Overall Score should have "%" sign
            if(!overallScore.contains("%"))
                Assert.fail("Overall Score does not have \"%\" sign.");


            new Assignment().create(5);

            new LoginUsingLTI().ltiLogin("4_1");    //create a student
            new LoginUsingLTI().ltiLogin("4");//login as instructor
            new Assignment().assignToStudent(5);

            new LoginUsingLTI().ltiLogin("4_1");//login as student1
            new Assignment().openAssignmentFromCourseStream("5");//open the assignment
            new SelectAnswerAndSubmit().staticAssignment("B");//attempt incorrect as option B is the wrong answer

            new LoginUsingLTI().ltiLogin("4");//log in instructor
            new Assignment().releaseGrades(5, "Release Feedback for All");

            new LoginUsingLTI().ltiLogin("4_1");    //login as student
            new Navigator().NavigateTo("Assignments");  //go to Assignments page

            //TC row no. 19..submit Non-gradable assignment..For non-gradable assignment, overall score should not get changed
            String overallScore1 = driver.findElement(By.className("ls-overall-score-percent")).getText();
            if(!overallScore1.contains("100%"))//it should be 100% because gradable assignment is 100% and non-gradable was 0%
                Assert.fail("\"Overall Score:\" score changes in Assignments page on submitting a non-gradable assignment.");

            new Navigator().NavigateTo("Dashboard");    //go to Dashboard

            //TC row no. 21....Overall Score should be displayed under Graded Assignment card
            String overallScoreLabel1 = driver.findElement(By.cssSelector("text[class='highcharts-title']")).getText();
            if(!overallScoreLabel1.contains("Overall Score"))
                Assert.fail("\"Overall Score:\" score label is absent in Dashboard of student side.");

            //TC row no. 22...Overall Score displayed in dashboard should be same as in assignments page
            String overallScoreInDashboard = driver.findElement(By.id("donut-chart")).getText();
            if(!overallScoreInDashboard.contains("0%"))
                Assert.fail("Overall score is absent in Dashboard of student side.");

            if(!overallScoreInDashboard.contains("0%"))
                Assert.fail("Overall score is in Dashboard is not same as in Assignment page.");

            //TC row no. 23...Overall Score should have "%" sign
            if(!overallScoreInDashboard.contains("%"))
                Assert.fail("Overall score does not have % symbol in dashboard.");


            new LoginUsingLTI().ltiLogin("4");//log in instructor
            new Navigator().NavigateTo("Gradebook");    //go to gradebook

            //Tc row no. 25...Same Overall Score of student A should be displayed in instructor Gradebook
            List<WebElement> overallScoreInGradebook = driver.findElements(By.className("ls-ins-gradebook-overall-score"));
            if(!overallScoreInGradebook.get(1).getText().contains("0%"))
                Assert.fail("Same Overall Score of student A is not displayed in instructor Gradebook");

            new LoginUsingLTI().ltiLogin("4_2");//log in instructor
            new Navigator().NavigateTo("Gradebook");    //go to gradebook

            //Tc row no. 26...Same Overall Score of student A should be displayed in instructor Gradebook
            List<WebElement> overallScoreInMentorGradebook = driver.findElements(By.className("ls-ins-gradebook-overall-score"));
            if(!overallScoreInMentorGradebook.get(1).getText().contains("0%"))
                Assert.fail("Same Overall Score of student A is not displayed in mentor Gradebook");
        }

        catch (Exception e)
        {
            Assert.fail("Exception in test case studentAbleToViewOverallScoreForAllAssignment in class StudentAbleToViewOverallScoreForAllAssignment.", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void overallScoreOfAssignmentWithPolicy()
    {
        ProficiencyReport report=PageFactory.initElements(driver,ProficiencyReport.class);
        try
        {
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            String policyname = ReadTestData.readDataByTagName("", "policyname", "14");
            new Assignment().create(14);

            new LoginUsingLTI().ltiLogin("14_1");    //create a student
            new LoginUsingLTI().ltiLogin("14");     //log in instructor
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(policyname, "14 Policy description text", "1",null, false, "1", "", "Auto-release on assignment submission", "", "", "");
            new Assignment().assignToStudent(14);

            new LoginUsingLTI().ltiLogin("14_1");//login as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();//open lesson assignment
            Thread.sleep(10000);
            new AttemptQuestion().trueFalse(false, "correct", 14);
            driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")).click();
            new Navigator().navigateToTab("Assignments");  //go to Assignments page

            //TC row no. 15
           // String overallScore = driver.findElement(By.className("assignment-submitted-percent")).getText();
            String overallScore=  report.getCourseProficiency().getText().trim();
            System.out.println("overallScore"+overallScore);
            if(!overallScore.contains("100.0%"))
                Assert.fail("\"Overall Score:\" score label is absent in Assignments page of student side.");

            //TC row no. 16...Overall Score should have "%" sign
            if(!overallScore.contains("%"))
                Assert.fail("Overall Score does not have \"%\" sign.");
        }

        catch (Exception e)
        {
            Assert.fail("Exception in test case overallScoreOfAssignmentWithPolicy in class StudentAbleToViewOverallScoreForAllAssignment.", e);
        }
    }

}
