package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R165;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Sumit on 8/28/2014.
 */
public class MentorAssignsDiscussionWidgetAsGradableAssignment extends Driver{

    @Test
    public void mentorAssignsDiscussionWidgetAsGradableAssignment()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("71_1");    //create student1
            new LoginUsingLTI().ltiLogin("71");		//login a mentor
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TopicOpen().openLessonWithDiscussionWidget();
            new DiscussionWidget().assignDiscussionWidget("71");
            new Navigator().NavigateTo("Gradebook");    //go to Gradebook

            //TC row no. 71...DW Assignment name should be displayed in Gradebook
            String assignmenName = driver.findElement(By.cssSelector("span[class='ls-ins-gradebook-activity-midterm ellipsis']")).getText();
            if(assignmenName == null)
                Assert.fail("DW Assignment name is not displayed in Gradebook page for mentor.");

            //TC row no. 72..."Grades Not Released" text should be displayed in DW Assignment column
            String status = driver.findElement(By.cssSelector("div[class='student-assignment-score-cell']")).getText();
            if(!status.equals("Grades Not Released"))
                Assert.fail("\"Grades Not Released\" text is not displayed in DW Assignment column in Gradebook page for mentor.");


            new LoginUsingLTI().ltiLogin("71_1");		//login a student
            new Navigator().NavigateTo("Assignments");	//navigate to Assignment
            driver.findElement(By.cssSelector("span[class='learning-activity-title']")).click(); //click on DW assignment
            new WebDriverWait(driver, 300).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Perspectives']")));
            new Click().clickBycssselector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']");
            new Click().clickByclassname("navigate-to-jump-out-icon");
            String perspective = new RandomString().randomstring(10);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspective); //add perspective to DW assignment

            new LoginUsingLTI().ltiLogin("71");		//login a mentor
            new Navigator().NavigateTo("Assignments");
            driver.findElement(By.cssSelector("span[title='View Student Responses']")).click();	//click on View Student Responses
            new MouseHover().mouserhover("idb-gradebook-question-content");
            new WebDriverWait(driver, 300).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("div[class='ls-view-response-link']")));	//click on View Response link
            Thread.sleep(2000);
            driver.findElement(By.id("view-user-question-performance-score-box")).click();
            driver.findElement(By.id("view-user-question-performance-score-box")).sendKeys("2");
            driver.findElement(By.className("view-user-discussion-performance-save-btn")).click(); //clicking on not started box to save the marks
            new Navigator().NavigateTo("Assignments");
            driver.findElement(By.cssSelector("span[title='View Student Responses']")).click();	//click on View Student Responses
            driver.findElement(By.cssSelector("div[title='Release Grade for All']")).click();
            new Navigator().NavigateTo("Gradebook");    //go to Gradebook

            //TC row no. 74...Overall Score for each student should be updated under Overall Score column
            List<WebElement> overallScore = driver.findElements(By.cssSelector("span[class='ls-ins-gradebook-overall-score']"));
            if(!overallScore.get(1).getText().contains("100%"))
                Assert.fail("For mentor, Overall Score for student does not updated under Overall Score column after grade is released.");

            //TC row no. 73...Grade of each student should be updated for DW assignment in Gradebook
            String status1 = driver.findElement(By.cssSelector("div[class='student-assignment-score-cell']")).getText();
            if(!status1.equals("2/2"))
                Assert.fail("Grade of student is not updated for DW assignment in Gradebook after release of grade by the mentor.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case mentorAssignsDiscussionWidgetAsGradableAssignment in class MentorAssignsDiscussionWidgetAsGradableAssignment.", e);
        }
    }

}
