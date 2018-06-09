package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R162;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.sql.SQLException;

/*
 * Created by Sumit on 8/7/2014.
 */
public class StudentReportContentIssueForNonGradableAssignment extends Driver{

    @Test
    public void studentReportContentIssueForNonGradableAssignment()
    {
        try
        {

            new Assignment().create(81);

            new LoginUsingLTI().ltiLogin("81_1");//login as student
            new LoginUsingLTI().ltiLogin("81");//login as instructor
            new Assignment().assignToStudent(81);
            new LoginUsingLTI().ltiLogin("81_1");//login as student
            new Assignment().openAssignmentFromCourseStream("81");
            int rowCountBefore;
            boolean emailReceived ;
            int rowCountAfter ;
            DBConnect.Connect();
            rowCountBefore = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();
            String text = new RandomString().randomstring(5);
            new ReportContentIssue().reportContentIssue(text);//TC row no. 81
            emailReceived = new ValidateEmail().validateEmail(81_1,text);    //login to email and verify
            //TC row no. 82
            if(emailReceived == false)
                Assert.fail("Student reporting content error for a non gradable assignment question then email is not sent to the configured email id.");
            rowCountAfter = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();
            //TC row no. 82
            if(rowCountBefore+1 != rowCountAfter)
                Assert.fail("Student reporting content error for anon gradable assignment question then the record is not inserted in DB.");

            new LoginUsingLTI().ltiLogin("81_1");//login as student
            new Assignment().submitAssignmentAsStudent(81);//submit the whole static assignment
            new QuestionCard().clickOnCard("", 0);//click on question card
            Thread.sleep(2000);
            DBConnect.Connect();
            rowCountBefore = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();
            String text1 = new RandomString().randomstring(5);
            new ReportContentIssue().reportContentIssue(text1);
            emailReceived = new ValidateEmail().validateEmail(81_1,text1);    //login to email and verify
            //TC row no. 83
            if(emailReceived == false)
                Assert.fail("Student reporting content error for a non gradable assignment question from question card the email is not sent to the configured email id.");
            rowCountAfter = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();
            //TC row no. 83
            if(rowCountBefore+1 != rowCountAfter)
                Assert.fail("Student reporting content error for a non gradable assignment question from question card the record is not inserted in DB.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case studentReportContentIssueForNonGradableAssignment in class StudentReportContentIssueForNonGradableAssignment.", e);
        }
    }

    /*@AfterMethod
    public void connectionClose()
    {
        try {
            DBConnect.conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

}
