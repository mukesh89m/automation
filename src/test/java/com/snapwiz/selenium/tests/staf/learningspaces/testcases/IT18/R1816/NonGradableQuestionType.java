package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R1816;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Mukesh on 1/8/15.
 */
public class NonGradableQuestionType extends Driver {
    @Test(priority = 1,enabled = true)
    public void nonGradableQuestionType()
    {

        //Tc row no 47
        //"1. Login as instructor 2. Navigate to Assignment Summary Page from Main navigator 3. Click on “View Grade” Button for Gradable Assignment"
        try {
            Assignments assignments;
            assignments= PageFactory.initElements(driver, Assignments.class);
            new LoginUsingLTI().ltiLogin("354_1");//login as student
            new LoginUsingLTI().ltiLogin("354");//login as instructor

            //new Assignment().create(354); //create assignment
            new LoginUsingLTI().ltiLogin("354");//login as instructor
            new Assignment().assignToStudent(354); //assign to the student

            new LoginUsingLTI().ltiLogin("354_1"); //login as student
            new Assignment().submitAssignmentAsStudent(354); //submit assignment

            new LoginUsingLTI().ltiLogin("354");//login as instructor

            new Navigator().NavigateTo("Current Assignments");// navigate to the assignment summary page
            new Click().clickByclassname("ls-grade-book-assessment");//click on the view feedback

            provideFeedback(354,"This is a FeedbackText"); //provide feedback to the student
            new Assignment().releaseGrades(354, "Release Feedback for All");//click on the release feedback

            new LoginUsingLTI().ltiLogin("354_1"); //login as student

            new Navigator().NavigateTo("Assignments");// navigate to the assignment page
            assignments.getAssignmentName().click();//click on the assignment name
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.className("title-and-question-header")));
            new ClickOnquestionCard().clickonquestioncard(0);
            String feedBackMsgB4Update=new TextFetch().textfetchbyclass("feedback-content");
            System.out.println("feedBackMsg1B4Update:"+feedBackMsgB4Update);
            new LoginUsingLTI().ltiLogin("354");//login as instructor

            new Navigator().NavigateTo("Current Assignments");// navigate to the assignment summary page
            new Click().clickByclassname("ls-grade-book-assessment");//click on the view feedback

            provideFeedback(354,"This is a New FeedbackText");//provide feedback to student

            new LoginUsingLTI().ltiLogin("354_1"); //login as student

            new Navigator().NavigateTo("Assignments");// navigate to the assignment page
            assignments.getAssignmentName().click();//click on the assignment name
            new WebDriverWait(driver, 500).until(ExpectedConditions.presenceOfElementLocated(By.className("title-and-question-header")));

            new ClickOnquestionCard().clickonquestioncard(0);
            String feedBackMsgAfterUpdate=new TextFetch().textfetchbyclass("feedback-content");
            System.out.println("feedBackMsgAfterUpdate:"+feedBackMsgAfterUpdate);

            //355 Feedback should be updated.
           Assert.assertNotEquals(feedBackMsgB4Update,feedBackMsgAfterUpdate,"Feedback has not updated.");



        } catch (Exception e) {
            Assert.fail("Exception in test case nonGradableQuestionType of class NonGradableQuestionType", e);
        }
    }
    @Test(priority = 2,enabled = true)
    public void forGradableDWAssignment()
    {

        //Tc row no 356
        //""1.Login as Instructor  2.Navigate to Dashboard from main navigator

        try {
            Dashboard dashboard=PageFactory.initElements(driver,Dashboard.class);
            CurrentAssignments currentAssignments= PageFactory.initElements(driver,CurrentAssignments.class);

            new LoginUsingLTI().ltiLogin("356_1");//login as student
            new LoginUsingLTI().ltiLogin("356"); //login as instructor
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(356);

            new LoginUsingLTI().ltiLogin("356_1");//login a student
            new Navigator().NavigateTo("Assignments");	//navigate to Assignment
            new Click().clickByclassname("learning-activity-title"); //click on DW
            String perspective = new RandomString().randomstring(10);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspective); //add perspective to DW assignment

            new LoginUsingLTI().ltiLogin("356"); //login as instructor
            new Assignment().releaseGrades(356, "Release Grade for All");

            new Navigator().NavigateTo("Current Assignments");// navigate to the assignment summary page
            currentAssignments.getViewGrade_link().click();//click on the view grade link
            Thread.sleep(5000);
            Actions actions=new Actions(driver);
            actions.keyDown(Keys.CONTROL).sendKeys(Keys.END);
            Actions action = new Actions(driver);
            List<WebElement> we = driver.findElements(By.cssSelector("span[class='idb-gradebook-content-label-text idb-gradebook-username']"));
            action.moveToElement(we.get(0)).build().perform();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("idb-grade-now-link")));	//click on Enter Grade
            Thread.sleep(3000);
            driver.findElement(By.cssSelector("input[class='idb-grade-points']")).clear();
            Thread.sleep(2000);
            currentAssignments.clearGrade_textBox.sendKeys("0.2");
            driver.findElement(By.id("ls-assignment-not-started-count")).click(); //clicking on not started box to save the marks

            new LoginUsingLTI().ltiLogin("356_1");//login a student
            String sampleText =dashboard.getAvgPerformance().getText().trim();
            int index1=sampleText.lastIndexOf("e");
            int index2=sampleText.indexOf("%");
            String dashboardOverallScore=sampleText.substring(index1+1,index2);
            Thread.sleep(5000);
            System.out.println("Average Performance : " + dashboardOverallScore);

            String recentlyGraded=dashboard.getGradedBarChart().getAttribute("height");
            System.out.println("Height:"+recentlyGraded);
            Thread.sleep(4000);
            String performance=dashboard.getAvgQuestionPerformance().get(2).getText().trim();
            int index4=performance.indexOf("%");
            String questionPerformance=performance.substring(0,index4);


            new LoginUsingLTI().ltiLogin("356"); //login as instructor
            new Assignment().updateGradeAfterGradeRelease(357);

            new LoginUsingLTI().ltiLogin("356_1");//login a student
            String sampleText1 =dashboard.getAvgPerformance().getText().trim();
            int index11=sampleText1.lastIndexOf("e");
            int index21=sampleText1.indexOf("%");
            String dashboardOverallScore1=sampleText.substring(index11+1,index21);
            Thread.sleep(5000);
            System.out.println("Average Performance : " + dashboardOverallScore1);

            String recentlyGraded1=dashboard.getGradedBarChart().getAttribute("height");
            System.out.println("Height:"+recentlyGraded1);
            Thread.sleep(4000);
            String performance1=dashboard.getAvgQuestionPerformance().get(2).getText().trim();
            int index41=performance1.indexOf("%");
            String questionPerformance1=performance.substring(0,index41);
            Thread.sleep(2000);
            System.out.println("AvgQuestionPerformance :"+questionPerformance1);
            System.out.println("pass");

            //Expected 1 Question Performance” tile should update.
            Assert.assertNotEquals(questionPerformance,questionPerformance1,"/“Average Question Performance/” tile has not updated ");

            //Expected 2 Overall Score' block should be updated
            Assert.assertNotEquals(dashboardOverallScore,dashboardOverallScore1,"Percentage value in “Overall Score” tile in the top right panel has not updated");

            //Expected 3 "Recently graded" graph for that assignment should update.
            Assert.assertNotEquals(recentlyGraded,recentlyGraded1,"\"Recently graded\" graph is not updated\"");


        } catch (Exception e) {
            Assert.fail("Exception in test case forGradableDWAssignment of class NonGradableQuestionType", e);
        }
    }
    @Test(priority = 3,enabled = true)
    public void forNonGradableDWAssignment()
    {

        //Tc row no 360
        //""1.Login as Instructor  2.Navigate to Dashboard from main navigator

        try {
            Dashboard dashboard=PageFactory.initElements(driver,Dashboard.class);
            CurrentAssignments currentAssignments= PageFactory.initElements(driver,CurrentAssignments.class);
            Discussion discussion=PageFactory.initElements(driver,Discussion.class);

            new LoginUsingLTI().ltiLogin("360_1");//login as student1
            new LoginUsingLTI().ltiLogin("360"); //login as instructor
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(360);

            new LoginUsingLTI().ltiLogin("360_1");//login a student
            new Navigator().NavigateTo("Assignments");	//navigate to Assignment
            new Click().clickByclassname("learning-activity-title"); //click on DW
            String perspective = new RandomString().randomstring(10);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspective); //add perspective to DW assignment

            new LoginUsingLTI().ltiLogin("360"); //login as instructor
            new Assignment().provideFeedBackForMultipleQuestion(360, "This is a New FeedbackText");

            new LoginUsingLTI().ltiLogin("360_1");//login as student
            new Navigator().NavigateTo("Assignments");	//navigate to Assignment
            new Click().clickByclassname("learning-activity-title"); //click on DW
            new WebDriverWait(driver, 1200).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
            new Click().clickBycssselector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']"); //click on the perspective
            new Click().clickByclassname("navigate-to-jump-out-icon"); //clickon the enter submission
            String insFeedback=discussion.getInsFeedback().getText().trim();
            System.out.println("Instructor feedback in student side:"+insFeedback);

            new LoginUsingLTI().ltiLogin("360"); //login as instructor
            new Assignment().provideFeedBackForMultipleQuestion(360, "This is a New FeedbackText1");

            new LoginUsingLTI().ltiLogin("360_1");//login as student
            new Navigator().NavigateTo("Assignments");	//navigate to Assignment
            new Click().clickByclassname("learning-activity-title"); //click on DW
            new WebDriverWait(driver, 1200).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
            new Click().clickBycssselector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']"); //click on the perspective
            new Click().clickByclassname("navigate-to-jump-out-icon"); //clickon the enter submission
            String insFeedback1=discussion.getInsFeedback().getText().trim();
            System.out.println("Instructor feedback in student side:"+insFeedback1);

            //Tc row no 361 The updated Feedback should be displayed
             Assert.assertNotEquals(insFeedback,insFeedback1,"The updated Feedback has not displayed");


        } catch (Exception e) {
            Assert.fail("Exception in test case forNonGradableDWAssignment of class NonGradableQuestionType", e);
        }
    }


    public void provideFeedback(int dataIndex,String feedback)
    {
        int index = 0;
        List<WebElement> provideFeedback=driver.findElements(By.cssSelector("div[class='idb-gradebook-content-coloumn']"));
        for(WebElement eachFeedback:provideFeedback)
        {
            for(int i = index; i < provideFeedback.size(); i++){
                new MouseHover().mouserHoverByClassList("idb-gradebook-question-content", index);
                index++;
                break;
            }
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            driver.findElement(By.id("view-user-question-performance-feedback-box")).sendKeys(feedback);
            driver.findElement(By.className("view-user-question-performance-save-btn")).click();
            new Click().clickByid("close-view-responses");
        }

    }

}
