package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R162;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.RandomString;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ReportContentIssue;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.SelectAnswerAndSubmit;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ValidateContentErrorInDB;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.ValidateEmail;

/*
 * Created by Sumit on 8/8/2014.
 */
public class ReportContentErrorForPassageTypeQuestionsInAssignments extends Driver {

    @Test
    public void reportContentErrorForPassageTypeQuestionsInAssignments()
    {
        try
        {
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "107");
            new Assignment().create(107);
            new Assignment().addPassagetypequestion(107, "qtn-passage-based-img", assignmentname,"qtn-type-true-false-img");

            new LoginUsingLTI().ltiLogin("107_1");//login as student
            new LoginUsingLTI().ltiLogin("107");//login as instructor
            new Assignment().assignToStudent(107);

            new LoginUsingLTI().ltiLogin("107_1");//login as student
            new Assignment().openAssignmentFromCourseStream("107");
            new SelectAnswerAndSubmit().staticAssignment("A");

            int rowCountBefore;
            boolean emailReceived ;
            int rowCountAfter ;
            rowCountBefore = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();

            String text = new RandomString().randomstring(5);
            new ReportContentIssue().reportContentIssue(text);//TC row no. 107

            //TC row no. 108--3. Report a content error for passage type question. --1. Email should be sent to the configured mail-id.
            emailReceived = new ValidateEmail().validateEmail(107,text);    //login to email and verify
            if(emailReceived == false)
                Assert.fail("On reporting a content issue for passage based question from question card then email is still not sent to the configured email id.");
            rowCountAfter = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();

            //TC row no. 108---3. Report a content error for passage type question. --2. Records for the content error should get inserted into the database.
            if(rowCountBefore+1 != rowCountAfter)
                Assert.fail("On reporting a content issue for passage based question from question card then the record is still not inserted in DB.");

            new LoginUsingLTI().ltiLogin("107_1");//login as student
            new Assignment().openAssignmentFromCourseStream("107");
            new SelectAnswerAndSubmit().staticAssignment("A");

            List<WebElement> allQuestionCard = driver.findElements(By.className("question-card-question-content"));
            allQuestionCard.get(1).click(); //passage based question will be at 1st index

            rowCountBefore = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();

            String text1 = new RandomString().randomstring(5);
            new ReportContentIssue().reportContentIssueForPassageBaseQuestion(text1);

            //TC row no. 109--3. Report a content error for passage type question. --1. Email should be sent to the configured mail-id.
            emailReceived = new ValidateEmail().validateEmail(107,text1);    //login to email and verify
            if(emailReceived == false)
                Assert.fail("On reporting a content issue for passage based question from question card then email is still not sent to the configured email id.");
            rowCountAfter = new ValidateContentErrorInDB().rowCountBeforeReportingContentError();

            //TC row no. 109---3. Report a content error for passage type question. --2. Records for the content error should get inserted into the database.
            if(rowCountBefore+1 != rowCountAfter)
                Assert.fail("On reporting a content issue for passage based question from question card then the record is still not inserted in DB.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case reportContentErrorForPassageTypeQuestionsInAssignments in class ReportContentErrorForPassageTypeQuestionsInAssignments.", e);
        }
    }

}
