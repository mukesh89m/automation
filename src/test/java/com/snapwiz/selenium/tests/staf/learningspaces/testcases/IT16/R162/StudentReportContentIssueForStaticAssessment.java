package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R162;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

/*
 * Created by Sumit on 8/7/2014.
 */
public class StudentReportContentIssueForStaticAssessment extends  Driver{

    @Test
    public void studentReportContentIssueForStaticAssessment()
    {
        try
        {

            new LoginUsingLTI().ltiLogin("78");//login as student
            new Navigator().NavigateTo("e-Textbook");
            new SelectCourse().selectInvisibleAssignment("1.1 Concept Check");
            Thread.sleep(5000);
            driver.findElement(By.cssSelector("div[class='ls-static-practice-test-submit-button']")).click();
            Thread.sleep(3000);
            int rowCountBefore;
            boolean emailReceived ;
            int rowCountAfter ;
            DBConnect.Connect();
            rowCountBefore = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();
            String text2 = new RandomString().randomstring(5);
            new ReportContentIssue().reportContentIssue(text2);//TC row no. 78
            emailReceived = new ValidateEmail().validateEmail(78,text2);    //login to email and verify
            //TC row no. 79
            if(emailReceived == false)
                Assert.fail("Student reporting content error for a static assessment question then email is not sent to the configured email id.");
            rowCountAfter = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();
            //TC row no. 79
            if(rowCountBefore+1 != rowCountAfter)
                Assert.fail("Student reporting content error for a static assessment question then the record is not inserted in DB.");

            new LoginUsingLTI().ltiLogin("78");//login as student
            new Navigator().NavigateTo("e-Textbook");
            new SelectCourse().selectInvisibleAssignment("1.1 Concept Check");
            new StaticAssignmentSubmit().staticAssesment();//submit the whole static assessment
            driver.findElement(By.className("question-card-question-content")).click();//click on question card
            Thread.sleep(2000);
            DBConnect.Connect();
            rowCountBefore = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();
            String text1 = new RandomString().randomstring(5);
            new ReportContentIssue().reportContentIssue(text1);
            emailReceived = new ValidateEmail().validateEmail(78,text1);    //login to email and verify
            //TC row no. 80
            if(emailReceived == false)
                Assert.fail("Student reporting content error for a static assessment question from question card the email is not sent to the configured email id.");
            rowCountAfter = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();
            //TC row no. 80
            if(rowCountBefore+1 != rowCountAfter)
                Assert.fail("Student reporting content error for a static assessment question from question card the record is not inserted in DB.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case studentReportContentIssueForStaticAssessment in class StudentReportContentIssueForStaticAssessment.", e);
        }
    }

}
