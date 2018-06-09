package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.classManagement;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentQuestionsListPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.TLOListPage;
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
 * Created by shashank on 27-02-2015.
 */
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
public class ClassManagement extends Driver{
    @Test(priority = 1)
    public void classManagement(){
        try {
            String appendChar = "a29";
            String appendChar1 ="b29";
            int dataIndex = 172;
          //  String classCode="97798";
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
            new Assignment().create(172,"truefalse");//create assessment with one question
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            AssessmentQuestionsListPage assessmentQuestionsListPage=PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            assignments.getButton_review().click();//click on review button
            assessmentQuestionsListPage.getButton_saveForLater().click();//click on save for later
            Thread.sleep(3000);
            new Assignment().assignToStudent(dataIndex,appendChar);//assign assessment to class
            Thread.sleep(3000);
            new Classes().createNewClass(appendChar1, dataIndex);//create class
            new Navigator().navigateTo("manageclass");//navigate to manageclass
            Thread.sleep(2000);
            try {
                if (  !manageClass.getclassCode().get(0).getText().contains(classCode)) {
                    manageClass.getExpandClass().click();//expand class
                    Thread.sleep(5000);
                }
            }
            catch (Exception e)
            {

            }

            //validate class code
            Assert.assertTrue(manageClass.getclassCode().get(0).getText().contains(classCode),"Exception in ClassManagement in method classManagement");
            //validate grade
            Assert.assertTrue(manageClass.getGrade().getText().contains("Grade"),"Exception in ClassManagement in method classManagement");
            //validate Subject
            Assert.assertTrue(manageClass.getSubjectArea().getText().contains("Mathematics"), "Exception in ClassManagement in method classManagement");
            //validate count of number of students
            Assert.assertTrue(manageClass.getStudentNames().size() == 2, "Exception in ClassManagement in method classManagement");
            manageClass.getVisitClass().click();//click on visit class
            Thread.sleep(2000);
            Assert.assertTrue(driver.findElements(By.className("dashboard-header-label")).size()==1,"Exception in ClassManagement in method classManagement");
            new Navigator().navigateTo("manageclass");//navigate to manageclass
            //click on settings icon on top of class
            manageClass.getSettings().click();
            Thread.sleep(1000);
            //click on Edit button
            manageClass.getEditClass().click();
            WebDriverWait wait=new WebDriverWait(driver,30);
            Thread.sleep(4000);
            Actions action=new Actions(driver);
            //click on delete student from the class
            action.moveToElement(manageClass.getStudentNames().get(0)).build().perform();
            action.moveToElement(manageClass.getDeleteStudent()).click().build().perform();
            wait.until(ExpectedConditions.visibilityOf(manageClass.getYesDelete()));
            //validate error  message
            Assert.assertTrue(manageClass.getNotificationMessage().getText().contains("Are you sure you want to remove this student from the class?"),"Exception in ClassManagement in method classManagement");
            manageClass.getNoCancel().click();//click on cancel button
            Thread.sleep(1000);
            String studentName=manageClass.getStudentNames().get(0).getText();//get name of first student
            Assert.assertTrue(manageClass.getStudentNames().size() == 2, "Exception in ClassManagement in method classManagement");//count number of students in the class
            //click on delete student from the class
            action.moveToElement(manageClass.getStudentNames().get(0)).build().perform();
            action.moveToElement(manageClass.getDeleteStudent()).click().build().perform();
            wait.until(ExpectedConditions.visibilityOf(manageClass.getYesDelete()));
            manageClass.getDeleteConfirmText().sendKeys("DELETE");//type Delete on corfirm screen
            manageClass.getYesDelete().click();//click on yes delete
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getStudentNames().size() == 1, "Exception in ClassManagement in method classManagement");//count number of students in the class
            manageClass.getCancelButton().click();//click on cancel button
            Thread.sleep(3000);
            //validate description and Class name should not present on screen after click on cancel button
            Assert.assertTrue(!manageClass.descriptionTexareaWebElement().isDisplayed(), "Exception in ClassManagement in method classManagement");
            Assert.assertTrue(!manageClass.classNameWebElement().isDisplayed(), "Exception in ClassManagement in method classManagement");

            Assert.assertTrue(manageClass.getStudentNames().size() == 2, "Exception in ClassManagement in method classManagement");//count number of students in the class
            //click on settings icon
            manageClass.getSettings().click();
            //click on edit
            manageClass.getEditClass().click();
            Thread.sleep(3000);
            //validate description and Class name should be present on screen
            Assert.assertTrue(manageClass.descriptionTexareaWebElement().isDisplayed(), "Exception in ClassManagement in method classManagement");
            Assert.assertTrue(manageClass.classNameWebElement().isDisplayed(), "Exception in ClassManagement in method classManagement");
            //click on delete student from the class
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
            new Navigator().logout();//logout
            new Login().directLoginAsDAStudent(0,studentName+"@snapwiz.com");//try to login with deleted student
            //validate error message
            Assert.assertTrue(manageClass.getErrorMessageAtLogin().getText().contains("Email or Password do not match"), "Exception in ClassManagement in method classManagement");
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher

            //navigate to assessments
            new Navigator().navigateTo("assignment");
            Thread.sleep(2000);
            manageClass.getMore().click();
            //click on share under assessment
            manageClass.getShare().click();
            //get share URL
            String shareURL=manageClass.getShareUrl().getText();
            new Navigator().logout();//logout
            driver.get(shareURL);//type the same URL copied from share
            new Login().directLoginAsDAStudent(0,studentName+"@snapwiz.com");
            Assert.assertTrue(manageClass.getErrorMessageAtLogin().getText().contains("Email or Password do not match"), "Exception in ClassManagement in method classManagement");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase 'classManagement' in class 'ClassManagement'", e);
        }
    }


}
