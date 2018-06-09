package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R1816;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TabClose;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Mukesh on 1/5/15.
 */
public class DashboardTilesUpdateForDWAssignment extends Driver{
    @Test(priority = 1,enabled = true)
    public void dashboardTilesUpdateForGradableDWAssignment()
    {

        //Tc row no 197
        //""1.Login as Instructor  2.Navigate to Dashboard from main navigator

        try {
            Dashboard dashboard;
            dashboard=PageFactory.initElements(driver,Dashboard.class);
            new LoginUsingLTI().ltiLogin("197_1");//login as student
            new LoginUsingLTI().ltiLogin("197"); //login as instructor
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(197);

            new LoginUsingLTI().ltiLogin("197_1");//login a student
            new Navigator().NavigateTo("Assignments");	//navigate to Assignment
            new Click().clickByclassname("learning-activity-title"); //click on DW
            String perspective = new RandomString().randomstring(10);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspective); //add perspective to DW assignment

            new LoginUsingLTI().ltiLogin("197"); //login as instructor
            new Assignment().releaseGrades(197, "Release Grade for All");
            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("197"); //login as instructor
             dashboard=PageFactory.initElements(driver,Dashboard.class);

            new Navigator().NavigateTo("Dashboard"); //navigate to dashboard
            String sampleText =dashboard.getAvgPerformance().getText().trim();
            int index1=sampleText.lastIndexOf("e");
            int index2=sampleText.indexOf("%");
            String str=sampleText.substring(index1+1,index2);
            int avgPerformance=Integer.parseInt(str);
            System.out.println("avgPerformance:"+avgPerformance);
            String height=dashboard.getGradedBarChart().getAttribute("height");
            int recentlyGraded= Integer.parseInt(height);
            System.out.println("recentlyGraded:"+recentlyGraded);
            String performance=dashboard.getAvgQuestionPerformance().get(2).getText().trim();
            int index4=performance.indexOf("%");
            String performance2=performance.substring(0,index4);
            int avgQuestionPerformance=Integer.parseInt(performance2);
            System.out.println("avgQuestionPerformance:"+avgQuestionPerformance);

        } catch (Exception e) {
            Assert.fail("Exception in test case dashboardTilesUpdateForGradableDWAssignment of class DashboardTilesUpdateForDWAssignment", e);
        }
    }

    @Test(priority = 2,enabled = true)
    public void dashboardTilesUpdateForNonGradableDWAssignment()
    {

        //Tc row no 197
        //""1.Login as Instructor  2.Navigate to Dashboard from main navigator

        try {
            Dashboard dashboard=PageFactory.initElements(driver,Dashboard.class);
            CurrentAssignments currentAssignments= PageFactory.initElements(driver,CurrentAssignments.class);

            new LoginUsingLTI().ltiLogin("201_1");//login as student1
            new LoginUsingLTI().ltiLogin("202_1");//login as student2
            new LoginUsingLTI().ltiLogin("201"); //login as instructor
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(201);

            new LoginUsingLTI().ltiLogin("201_1");//login a student
            new Navigator().NavigateTo("Assignments");	//navigate to Assignment
            new Click().clickByclassname("learning-activity-title"); //click on DW
            String perspective = new RandomString().randomstring(10);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspective); //add perspective to DW assignment*/

            new LoginUsingLTI().ltiLogin("201"); //login as instructor
            new Assignment().provideFeedback(201);//provide feedback

            new LoginUsingLTI().ltiLogin("202_1");//login a student
            new Navigator().NavigateTo("Assignments");	//navigate to Assignment
            new Click().clickByclassname("learning-activity-title"); //click on DW
            String perspective1 = new RandomString().randomstring(10);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspective1); //add perspective to DW assignment

            new LoginUsingLTI().ltiLogin("201"); //login as instructor
            new Assignment().provideFeedBackForMultipleQuestion(201, "This is a New FeedbackText");

            int index = 0;
            WebElement provideFeedback=driver.findElement(By.cssSelector("div[class='idb-gradebook-content-coloumn']"));
            new MouseHover().mouserhoverbywebelement(provideFeedback);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link")));

             Assert.assertNotEquals(currentAssignments.getFeedBack_textBox().getText().trim(),"This is a FeedbackText","The updated feedback has not displayed");


        } catch (Exception e) {
            Assert.fail("Exception in test case dashboardTilesUpdateForNonGradableDWAssignment of class DashboardTilesUpdateForDWAssignment", e);
        }
    }



}
