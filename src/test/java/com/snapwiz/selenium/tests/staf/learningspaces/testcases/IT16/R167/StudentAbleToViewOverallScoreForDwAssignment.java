package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R167;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by Sumit on 8/20/2014.
 */
public class StudentAbleToViewOverallScoreForDwAssignment extends Driver{

    @Test(priority = 1, enabled = true)
    public void studentAbleToViewOverallScoreForDwAssignment()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("9_1");		//create a student
            new LoginUsingLTI().ltiLogin("9");		//login as instructor
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TopicOpen().openLessonWithDiscussionWidget();
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(9);

            new LoginUsingLTI().ltiLogin("9_1");		//login as student
            new Navigator().NavigateTo("Assignments");	//navigate to Assignments
            driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
            Thread.sleep(10000);
            String perspective = new RandomString().randomstring(5);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspective);

            new LoginUsingLTI().ltiLogin("9");		//login as instructor
            new Navigator().NavigateTo("Assignments");
            driver.findElement(By.cssSelector("span[title='View Student Responses']")).click();	//click on View Student Responses
            new DiscussionWidget().provideGradeToStudent(0, "1");//provide grade
            new Navigator().NavigateTo("Assignments");
            driver.findElement(By.cssSelector("span[title='View Student Responses']")).click();	//click on View Student Responses
            driver.findElement(By.cssSelector("div[title='Release Grade for All']")).click();

            new LoginUsingLTI().ltiLogin("9_1");//login as student1
            new Navigator().NavigateTo("Assignments");  //go to Assignments page
            //TC row no. 9..."Overall Score: <Score>" box should be displayed at the top right side of the assignments page
            String overallScoreLabel = driver.findElement(By.className("ls-overall-score-text")).getText();
            if(!overallScoreLabel.contains("Overall Score:"))
                Assert.fail("\"Overall Score:\" score label is absent in Assignments page of student side.");

            //TC row no. 10
            String overallScore = driver.findElement(By.className("ls-overall-score-percent")).getText();
            if(!overallScore.contains("100%"))
                Assert.fail("\"Overall Score:\" score label is absent in Assignments page of student side.");

            //TC row no. 11...Overall Score should have "%" sign
            if(!overallScore.contains("%"))
                Assert.fail("Overall Score does not have \"%\" sign.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case studentAbleToViewOverallScoreForDwAssignment in class StudentAbleToViewOverallScoreForDwAssignment.", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void studentAbleToViewOverallScoreForNonGradableDwAssignment()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("20_1");		//create a student
            new LoginUsingLTI().ltiLogin("20");		//login as instructor
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TopicOpen().openLessonWithDiscussionWidget();
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(20);

            new LoginUsingLTI().ltiLogin("20_1");		//login as student
            new Navigator().NavigateTo("Assignments");	//navigate to Assignments
            driver.findElement(By.className("ls-stream-learing-activity-level")).click();	//click on DW assignment
            Thread.sleep(2000);
            String perspective = new RandomString().randomstring(5);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspective);

            new LoginUsingLTI().ltiLogin("20");		//login as instructor
            new Navigator().NavigateTo("Assignments");	//navigate to Assignments
            driver.findElement(By.cssSelector("span[class='ls-grade-book-assessment']")).click();	//click on View Student Responses
            Thread.sleep(3000);
            String feedback = new RandomString().randomstring(10);
            new DiscussionWidget().provideFeedbackToStudent(0, feedback);

            new LoginUsingLTI().ltiLogin("20_1");//login as student1
            new Navigator().NavigateTo("Assignments");  //go to Assignments page

            //TC row no. 21...For non-gradeable DW assignment, overall score should not get changed
            int overallScore = driver.findElements(By.className("ls-overall-score-percent")).size();
            if(overallScore != 0)//there will be no overalls core for non-gradable DW assignment
                Assert.fail("\"Overall Score:\" score changes in Assignments page on submitting a non-gradable DW assignment.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case studentAbleToViewOverallScoreForNonGradableDwAssignment in class StudentAbleToViewOverallScoreForDwAssignment.", e);
        }
    }

}
