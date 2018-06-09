package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.classManagement;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentQuestionsListPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.manageClass.ManageClass;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by shashank on 03-03-2015.
 */
public class DeleteStudentGradableExplicitlyByInstructor extends Driver{

    @Test(priority = 1)
    public void deleteStudentGradableScheduledAfterAssessment()
    {
        try {
            String appendChar = "a1";
            String appendChar1 ="b1";
            int dataIndex = 177;
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionsListPage assessmentQuestionsListPage=PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            ManageClass manageClass= PageFactory.initElements(driver,ManageClass.class);
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode=new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar, classCode, 1119);//create student
            new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar1, classCode, 1119);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"truefalse");//create assessment with one question
            assignments.getButton_review().click();//click on review button
            assessmentQuestionsListPage.getButton_saveForLater().click();//click on save for later
            new Assignment().assignToStudent(dataIndex,appendChar);//assign assessment to class
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Scheduled"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");

            Thread.sleep(900000);
            driver.navigate().refresh();
            Thread.sleep(3000);
            manageClass.getViewResponse().click();
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==2,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            new Navigator().logout();
            new Login().loginAsStudent(appendChar,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar1,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Grading in Progress"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            Thread.sleep(1000);
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==2,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            new Navigator().navigateTo("manageclass");//navigate to manageclass
            Thread.sleep(2000);
            manageClass.getSettings().click();
            //click on edit
            manageClass.getEditClass().click();
            Thread.sleep(3000);
            Assert.assertTrue(manageClass.getStudentNames().size() == 2, "Exception in ClassManagement in method classManagement");
            //validate description and Class name should be present on screen
            //click on delete student from the class
            Actions action=new Actions(driver);
            WebDriverWait wait=new WebDriverWait(driver,30);
            action.moveToElement(manageClass.getStudentNames().get(0)).build().perform();
            action.moveToElement(manageClass.getDeleteStudent()).click().build().perform();
            wait.until(ExpectedConditions.visibilityOf(manageClass.getYesDelete()));
            //type delete in confirm screen
            manageClass.getDeleteConfirmText().sendKeys("DELETE");
            Thread.sleep(1000);
            //click on yes delete option
            manageClass.getYesDelete().click();
            Thread.sleep(2000);
            //click on save the class
            manageClass.getSaveTheClass().click();
            Thread.sleep(3000);
            Assert.assertTrue(manageClass.getStudentNames().size() == 1, "Exception in ClassManagement in method classManagement");
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Grading in Progress"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size() == 1, "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");

            Thread.sleep(3000);
            new Click().clickBycssselector("label[class='as-gradeBook-checkbox']");
            new Click().clickByXpath("//div[contains(@title,'Release Grade')]");

            new Click().clickBycssselector("span.as-gradebook-back-arrow");
            Assert.assertTrue(driver.findElement(By.xpath("//span[@class='mantis']")).getText().contains("Graded"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment",e);
        }

    }


    @Test(priority = 2)
    public void deleteStudentGradableScheduledBeforeAssessment()
    {
        try {
            String appendChar = "a11";
            String appendChar1 ="b11";
            int dataIndex = 178;
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            AssessmentQuestionsListPage assessmentQuestionsListPage=PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            ManageClass manageClass= PageFactory.initElements(driver,ManageClass.class);
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode=new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar, classCode, 1119);//create student
            new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar1, classCode, 1119);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"truefalse");//create assessment with one question
            assignments.getButton_review().click();//click on review button
            assessmentQuestionsListPage.getButton_saveForLater().click();//click on save for later
            new Assignment().assignToStudent(dataIndex,appendChar);//assign assessment to class
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Scheduled"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            Thread.sleep(900000);
            driver.navigate().refresh();
            Thread.sleep(3000);
            manageClass.getViewResponse().click();
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==2,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            new Navigator().logout();
            new Login().loginAsStudent(appendChar,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Navigator().navigateTo("manageclass");//navigate to manageclass
            Thread.sleep(2000);
            manageClass.getSettings().click();
            //click on edit
            manageClass.getEditClass().click();
            Thread.sleep(3000);
            Assert.assertTrue(manageClass.getStudentNames().size() == 2, "Exception in ClassManagement in method classManagement");
            //validate description and Class name should be present on screen
            //click on delete student from the class
            Actions action=new Actions(driver);
            WebDriverWait wait=new WebDriverWait(driver,30);

            action.moveToElement(manageClass.getStudentNames().get(0)).build().perform();
            action.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'deleteStudentGradableScheduledBeforeAssessmentst" + appendChar1 + "')]"))).click().build().perform();
            wait.until(ExpectedConditions.visibilityOf(manageClass.getYesDelete()));
            //type delete in confirm screen
            manageClass.getDeleteConfirmText().sendKeys("DELETE");
            //click on yes delete option
            manageClass.getYesDelete().click();
            Thread.sleep(2000);
            //click on save the class
            manageClass.getSaveTheClass().click();
            Thread.sleep(3000);
            Assert.assertTrue(manageClass.getStudentNames().size() == 1, "Exception in ClassManagement in method classManagement");

            new Navigator().logout();
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Grading in Progress"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==1,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");

            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Grading in Progress"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==1,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size() == 1, "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");

            Thread.sleep(3000);
            new Click().clickBycssselector("label[class='as-gradeBook-checkbox']");
            new Click().clickByXpath("//div[contains(@title,'Release Grade')]");

            new Click().clickBycssselector("span.as-gradebook-back-arrow");
            Assert.assertTrue(driver.findElement(By.xpath("//span[@class='mantis']")).getText().contains("Graded"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledBeforeAssessment",e);
        }

    }

    @Test(priority = 3)
    public void deleteStudentGradableAwaitingResponseAfterAssessment()
    {
        try {
            String appendChar = "a11";
            String appendChar1 ="b11";
            int dataIndex = 179;
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            AssessmentQuestionsListPage assessmentQuestionsListPage=PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            ManageClass manageClass= PageFactory.initElements(driver,ManageClass.class);
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode=new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar, classCode, 1119);//create student
            new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar1, classCode, 1119);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"truefalse");//create assessment with one question
            assignments.getButton_review().click();//click on review button
            assessmentQuestionsListPage.getButton_saveForLater().click();//click on save for later
            new Assignment().assignToStudent(dataIndex,appendChar);//assign assessment to class
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Awaiting Submission"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(3000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==2,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            new Navigator().logout();
            new Login().loginAsStudent(appendChar,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar1,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Grading in Progress"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==2,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            new Navigator().navigateTo("manageclass");//navigate to manageclass
            Thread.sleep(2000);
            manageClass.getSettings().click();
            //click on edit
            manageClass.getEditClass().click();
            Thread.sleep(3000);
            Assert.assertTrue(manageClass.getStudentNames().size() == 2, "Exception in ClassManagement in method classManagement");
            //validate description and Class name should be present on screen
            //click on delete student from the class
            Actions action=new Actions(driver);
            WebDriverWait wait=new WebDriverWait(driver,30);
            action.moveToElement(manageClass.getStudentNames().get(0)).build().perform();
            action.moveToElement(manageClass.getDeleteStudent()).click().build().perform();
            wait.until(ExpectedConditions.visibilityOf(manageClass.getYesDelete()));
            //type delete in confirm screen
            manageClass.getDeleteConfirmText().sendKeys("DELETE");
            //click on yes delete option
            manageClass.getYesDelete().click();
            Thread.sleep(2000);
            //click on save the class
            manageClass.getSaveTheClass().click();
            Thread.sleep(3000);
            Assert.assertTrue(manageClass.getStudentNames().size() == 1, "Exception in ClassManagement in method classManagement");
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Grading in Progress"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size() == 1, "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size() == 1, "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");

            Thread.sleep(3000);
            new Click().clickBycssselector("label[class='as-gradeBook-checkbox']");
            new Click().clickByXpath("//div[contains(@title,'Release Grade')]");

            new Click().clickBycssselector("span.as-gradebook-back-arrow");
            Assert.assertTrue(driver.findElement(By.xpath("//span[@class='mantis']")).getText().contains("Graded"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in DeleteStudentGradable Class in method deleteStudentGradableAwaitingResponseAfterAssessment",e);
        }

    }


    @Test(priority = 4)
    public void deleteStudentGradableAwaitingResponseBeforeAssessment()
    {
        try {
            String appendChar = "a20";
            String appendChar1 ="b20";
            int dataIndex = 180;
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            AssessmentQuestionsListPage assessmentQuestionsListPage=PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            ManageClass manageClass= PageFactory.initElements(driver,ManageClass.class);
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode=new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar, classCode, 1119);//create student
            new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar1, classCode, 1119);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"truefalse");//create assessment with one question
            assignments.getButton_review().click();//click on review button
            assessmentQuestionsListPage.getButton_saveForLater().click();//click on save for later
            new Assignment().assignToStudent(dataIndex,appendChar);//assign assessment to class
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Awaiting Submission"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(3000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==2,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            new Navigator().logout();
            new Login().loginAsStudent(appendChar,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Navigator().navigateTo("manageclass");//navigate to manageclass
            Thread.sleep(2000);
            manageClass.getSettings().click();
            //click on edit
            manageClass.getEditClass().click();
            Thread.sleep(3000);
            Assert.assertTrue(manageClass.getStudentNames().size() == 2, "Exception in ClassManagement in method classManagement");
            //validate description and Class name should be present on screen
            //click on delete student from the class
            Actions action=new Actions(driver);
            WebDriverWait wait=new WebDriverWait(driver,30);

            action.moveToElement(manageClass.getStudentNames().get(0)).build().perform();
            action.moveToElement(driver.findElement(By.xpath("//span[contains(text(),'deleteStudentGradableAwaitingResponseBeforeAssessmentst"+appendChar1+"')]"))).click().build().perform();
            wait.until(ExpectedConditions.visibilityOf(manageClass.getYesDelete()));
            //type delete in confirm screen
            manageClass.getDeleteConfirmText().sendKeys("DELETE");
            //click on yes delete option
            manageClass.getYesDelete().click();
            Thread.sleep(2000);
            //click on save the class
            manageClass.getSaveTheClass().click();
            Thread.sleep(3000);
            Assert.assertTrue(manageClass.getStudentNames().size() == 1, "Exception in ClassManagement in method classManagement");

            new Navigator().logout();
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Grading in Progress"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==1,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");

            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Grading in Progress"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==1,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size() == 1, "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");

            Thread.sleep(3000);
            new Click().clickBycssselector("label[class='as-gradeBook-checkbox']");
            new Click().clickByXpath("//div[contains(@title,'Release Grade')]");

            new Click().clickBycssselector("span.as-gradebook-back-arrow");
            Assert.assertTrue(driver.findElement(By.xpath("//span[@class='mantis']")).getText().contains("Graded"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in DeleteStudentGradable Class in method deleteStudentGradableAwaitingResponseBeforeAssessment",e);
        }

    }
}
