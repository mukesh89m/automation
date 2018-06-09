package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R162;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*
 * Created by Sumit on 8/7/2014.
 */
public class StudentReportContentIssueForGradableAssignment extends Driver{

    @Test
    public void studentReportContentIssueForGradableAssignment()
    {
        try
        {

            new Assignment().create(84);

            new LoginUsingLTI().ltiLogin("84_1");//login as student
            new LoginUsingLTI().ltiLogin("84");//login as instructor
            new Assignment().assignToStudent(84);

            new LoginUsingLTI().ltiLogin("84_1");//login as student
            new Assignment().openAssignmentFromCourseStream("84");
            int rowCountBefore;
            boolean emailReceived ;
            int rowCountAfter ;
            DBConnect.Connect();
            rowCountBefore = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();
            String text = new RandomString().randomstring(5);
            new ReportContentIssue().reportContentIssue(text);//TC row no. 84
            emailReceived = new ValidateEmail().validateEmail(84_1,text);    //login to email and verify
            //TC row no. 85
            if(emailReceived == false)
                Assert.fail("Student reporting content error for a  gradable assignment question then email is not sent to the configured email id.");
            rowCountAfter = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();
            //TC row no. 85
            if(rowCountBefore+1 != rowCountAfter)
                Assert.fail("Student reporting content error for a gradable assignment question then the record is not inserted in DB.");

            new LoginUsingLTI().ltiLogin("84_1");//login as student
            new Assignment().submitAssignmentAsStudent(84);//submit the whole static assignment
            new QuestionCard().clickOnCard("", 0);//click on question card
            Thread.sleep(2000);
            DBConnect.Connect();
            rowCountBefore = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();
            String text1 = new RandomString().randomstring(5);
            new ReportContentIssue().reportContentIssue(text1);
            emailReceived = new ValidateEmail().validateEmail(84_1,text1);    //login to email and verify
            //TC row no. 86
            if(emailReceived == false)
                Assert.fail("Student reporting content error for a  gradable assignment question from question card the email is not sent to the configured email id.");
            rowCountAfter = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();
            //TC row no. 86
            if(rowCountBefore+1 != rowCountAfter)
                Assert.fail("Student reporting content error for a gradable assignment question from question card the record is not inserted in DB.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case studentReportContentIssueForNonGradableAssignment in class StudentReportContentIssueForNonGradableAssignment.", e);
        }
    }

}
