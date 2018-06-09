package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.signupsignin;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Assignment;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Login;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Navigator;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.SignUp;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentDetailsPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assign;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.cleversync.CleverSync;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.StudentDashboard;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

/**
 * Created by shashank on 23-07-2015.
 */
public class AssessmentSharingWithCleverSync extends Driver {

    @Test(priority = 1,enabled = true)
    public void assessmentSharingWithCleverSyncBeforeSignIn()
    {
        int dataIndex=17;
        String appendChar1="a1";
        CleverSync cleverSync= PageFactory.initElements(driver, CleverSync.class);
        String assessmentName= ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        try {
            //  String assessmentURl="http://idc-edulastic-replica.snapwiz.net/#renderResource/close/55b1d3890cf29f70e2f533ad";
            AssessmentDetailsPage assessmentDetailsPage = PageFactory.initElements(driver,AssessmentDetailsPage.class);
            Assign assign= PageFactory.initElements(driver, Assign.class);
            StudentDashboard studentDashboard= PageFactory.initElements(driver, StudentDashboard.class);
            String teacherEmail = "teacher110@snapwiz.com";
            String teacherEmail2="teacher107@snapwiz.com";
            String studentEmail="edustudent12@snapwiz.com";
            String password="snapwiz";
            new Login().cleverSyncLogin(teacherEmail, password);
            new Assignment().create(dataIndex, "truefalse");//create assessment
            new Assignment().createAssessmentUsingDraftStatus(dataIndex);//assign assessment
            Thread.sleep(3000);
            String assessmentURl = assign.getAssessmentShareLink().getText();//fetch url from UI
            System.out.println(assessmentURl);
            new Navigator().logout();
            driver.get("clever.com/in/edulastic");
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(cleverSync.cleverHeaderMenu));
            cleverSync.cleverHeaderMenu.click();
            cleverSync.cleverLogout.click();
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(cleverSync.logInWithClever));

            //    http://idc-edulastic-replica.snapwiz.net/#renderResource/close/55b1ee0c0cf29f70e2f537bc

            //login with different Teacher

            new Login().cleverSyncLogin(teacherEmail2, password,assessmentURl);
            Assert.assertTrue(assessmentDetailsPage.getAssessment_name().getText().contains(assessmentName),"Not open same assessment");
            new Navigator().logout();
            driver.get("clever.com/in/edulastic");
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(cleverSync.cleverHeaderMenu));
            cleverSync.cleverHeaderMenu.click();
            cleverSync.cleverLogout.click();
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(cleverSync.logInWithClever));
            new Login().cleverSyncLogin(studentEmail, password,assessmentURl);
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(studentDashboard.notificationMessage));
            Assert.assertEquals(studentDashboard.notificationMessage.getText(),"Assessment not found. Please check with your instructor.","Error message not displayed");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in SignInWithCleverSync class in method assessmentSharingWithCleverSync",e);
        }

    }
    @Test(priority = 2,enabled = true)
    public void assessmentSharingWithCleverSyncAfterSignIn()
    {
        int dataIndex=17;
        String appendChar1="b1";
        StudentDashboard studentDashboard= PageFactory.initElements(driver, StudentDashboard.class);
        String assessmentName= ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        try {
            AssessmentDetailsPage assessmentDetailsPage = PageFactory.initElements(driver,AssessmentDetailsPage.class);
            Assign assign= PageFactory.initElements(driver, Assign.class);
            String teacherEmail = "teacher110@snapwiz.com";
            String teacherEmail2="teacher107@snapwiz.com";
            String studentEmail="edustudent11@snapwiz.com";
            String password="snapwiz";
            new Login().cleverSyncLogin(teacherEmail, password);
            new Assignment().create(dataIndex, "truefalse");//create assessment
            new Assignment().createAssessmentUsingDraftStatus(dataIndex);//assign assessment
            Thread.sleep(3000);
            String assessmentURl = assign.getAssessmentShareLink().getText();//fetch url from UI
            System.out.println(assessmentURl);
            new Navigator().logout();
            driver.close();
            reInitDriver();
            //login with different Teacher
            new Login().cleverSyncLogin(teacherEmail2, password);
            driver.get(assessmentURl);
            Thread.sleep(3000);
            assessmentDetailsPage = PageFactory.initElements(driver,AssessmentDetailsPage.class);
            System.out.println(assessmentDetailsPage.getAssessment_name().getText());
            Assert.assertTrue(assessmentDetailsPage.getAssessment_name().getText().contains(assessmentName), "Not open same assessment");
            new Navigator().logout();
            driver.close();
            reInitDriver();
            studentDashboard= PageFactory.initElements(driver, StudentDashboard.class);
            new Login().cleverSyncLogin(studentEmail, password);
            driver.get(assessmentURl);
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(studentDashboard.notificationMessage));
            Assert.assertEquals(studentDashboard.notificationMessage.getText(), "Assessment not found. Please check with your instructor.", "Error message not displayed");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in SignInWithCleverSync class in method assessmentSharingWithCleverSync",e);
        }

    }
    @Test(priority = 3,enabled = true)
    public void assessmentSharingBeforeSignInWithGoogleCleverSync()
    {
        int dataIndex=17;
        String appendChar1="a1";
        String assessmentName= ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        try {
            AssessmentDetailsPage assessmentDetailsPage = PageFactory.initElements(driver,AssessmentDetailsPage.class);
            Assign assign= PageFactory.initElements(driver, Assign.class);
            String teacherEmail = "teacher107@snapwiz.com";
            String teacherEmail2="teacher2.snapwiz@gmail.com";
            String studentEmail="student2.snapwiz@gmail.com";
            String password="snapwiz123";
            new Login().cleverSyncLogin(teacherEmail, "snapwiz");
            new Assignment().create(dataIndex, "truefalse");//create assessment
            new Assignment().createAssessmentUsingDraftStatus(dataIndex);//assign assessment
            Thread.sleep(3000);
            String assessmentURl = assign.getAssessmentShareLink().getText();//fetch url from UI
            System.out.println(assessmentURl);
            new Navigator().logout();
            driver.close();
            reInitDriver();
            //login with different Teacher
            new SignUp().googleSignIn_EdulasticFromSignUpWithUrl(teacherEmail2, password, assessmentURl);
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.navbar-username")));
            assessmentDetailsPage = PageFactory.initElements(driver,AssessmentDetailsPage.class);
            Assert.assertTrue(assessmentDetailsPage.getAssessment_name().getText().contains(assessmentName),"Not open same assessment");
            new Navigator().logout();
            driver.close();
            reInitDriver();
            StudentDashboard studentDashboard= PageFactory.initElements(driver, StudentDashboard.class);
            new SignUp().googleSignIn_EdulasticFromSignUpWithUrl(studentEmail, password, assessmentURl);
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(studentDashboard.notificationMessage));
            Assert.assertEquals(studentDashboard.notificationMessage.getText(), "Assessment not found. Please check with your instructor.", "Error message not displayed");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in SignInWithCleverSync class in method assessmentSharingWithCleverSync",e);
        }



    }

    @Test(priority = 4,enabled = true)
    public void assessmentSharingAfterSignInWithGoogleCleverSync()
    {
        int dataIndex=17;
        String appendChar1="b1";
        String assessmentName= ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        try {
            AssessmentDetailsPage assessmentDetailsPage = PageFactory.initElements(driver, AssessmentDetailsPage.class);
            Assign assign = PageFactory.initElements(driver, Assign.class);
            String teacherEmail = "teacher107@snapwiz.com";
            String teacherEmail2 = "teacher2.snapwiz@gmail.com";
            String studentEmail = "student2.snapwiz@gmail.com";
            String password="snapwiz123";
            new Login().cleverSyncLogin(teacherEmail, "snapwiz");
            new Assignment().create(dataIndex, "truefalse");//create assessment
            new Assignment().createAssessmentUsingDraftStatus(dataIndex);//assign assessment
            Thread.sleep(3000);
            String assessmentURl = assign.getAssessmentShareLink().getText();//fetch url from UI
            System.out.println(assessmentURl);
            new Navigator().logout();
            driver.close();
            reInitDriver();
            //login with different Teacher
            new SignUp().googleSignIn_Edulastic(teacherEmail2, password);
            driver.get(assessmentURl);
            Thread.sleep(3000);
            assessmentDetailsPage = PageFactory.initElements(driver, AssessmentDetailsPage.class);
            System.out.println(assessmentDetailsPage.getAssessment_name().getText());
            Assert.assertTrue(assessmentDetailsPage.getAssessment_name().getText().contains(assessmentName), "Not open same assessment");
            new Navigator().logout();
            driver.close();
            reInitDriver();
            new Login().cleverSyncLogin(studentEmail, password);
            driver.get(assessmentURl);
            StudentDashboard studentDashboard= PageFactory.initElements(driver, StudentDashboard.class);
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(studentDashboard.notificationMessage));
            Assert.assertEquals(studentDashboard.notificationMessage.getText(), "Assessment not found. Please check with your instructor.", "Error message not displayed");
        }
        catch (Exception e) {
            Assert.fail("Exception in SignInWithCleverSync class in method assessmentSharingWithCleverSync",e);
        }


    }

    @Test(priority = 5, enabled = true)
    public void assessmentSharingWithCleverSyncGoogleUserBeforeSignIn() {
        try {

            int dataIndex = 17;
            String appendChar1 = "a05";
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            CleverSync cleverSync= PageFactory.initElements(driver, CleverSync.class);
            AssessmentDetailsPage assessmentDetailsPage = PageFactory.initElements(driver, AssessmentDetailsPage.class);
            Assign assign = PageFactory.initElements(driver, Assign.class);

            String teacherEmail = "teacher107@snapwiz.com";
            String teacherEmail2 = "teacher2.snapwiz@gmail.com";
            String studentEmail = "student2.snapwiz@gmail.com";
            String password = "snapwiz";

            new Login().cleverSyncLogin(teacherEmail, password);
            new Assignment().create(dataIndex, "truefalse");//create assessment
            new Assignment().createAssessmentUsingDraftStatus(dataIndex);//assign assessment
            Thread.sleep(3000);
            String assessmentURl = assign.getAssessmentShareLink().getText();//fetch url from UI
            new Navigator().logout();
            driver.get("clever.com/in/edulastic");
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(cleverSync.cleverHeaderMenu));
            cleverSync.cleverHeaderMenu.click();
            cleverSync.cleverLogout.click();
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(cleverSync.logInWithClever));

            //login with different Teacher
            new Login().cleverSyncLogin(teacherEmail2, "snapwiz123", assessmentURl);
            Assert.assertTrue(assessmentDetailsPage.getAssessment_name().getText().contains(assessmentName), "Not open same assessment");
            new Assignment().create(dataIndex, "truefalse");//create assessment
            new Assignment().assignToStudent(dataIndex, appendChar1);//assign assessment
            Thread.sleep(3000);
            assessmentURl = assign.getAssessmentShareLink().getText();//fetch url from UI
            new Navigator().logout();
            driver.get("clever.com/in/edulastic");
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(cleverSync.cleverHeaderMenu));
            cleverSync.cleverHeaderMenu.click();
            cleverSync.cleverLogout.click();
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(cleverSync.logInWithClever));

            new Login().cleverSyncLogin(studentEmail, "snapwiz123", assessmentURl);
            StudentDashboard studentDashboard= PageFactory.initElements(driver, StudentDashboard.class);
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(studentDashboard.notificationMessage));
            Assert.assertEquals(studentDashboard.notificationMessage.getText(), "Assessment not found. Please check with your instructor.", "Error message not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in AssessmentSharingWithCleverSync class in method assignmentSharingWithCleverSyncGoogleUserBeforeSignIn",e);

        }

    }

    @Test(priority = 6, enabled = true)
    public void assessmentSharingWithCleverSyncGoogleUserAfterSignIn() {
        try {

            int dataIndex = 17;
            String appendChar1 = "a05";
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            CleverSync cleverSync= PageFactory.initElements(driver, CleverSync.class);
            AssessmentDetailsPage assessmentDetailsPage = PageFactory.initElements(driver, AssessmentDetailsPage.class);
            Assign assign = PageFactory.initElements(driver, Assign.class);

            String teacherEmail = "teacher107@snapwiz.com";
            String teacherEmail2 = "teacher2.snapwiz@gmail.com";
            String studentEmail = "student2.snapwiz@gmail.com";
            String password = "snapwiz";

            new Login().cleverSyncLogin(teacherEmail, password);
            new Assignment().create(dataIndex, "truefalse");//create assessment
            new Assignment().createAssessmentUsingDraftStatus(dataIndex);//assign assessment
            Thread.sleep(3000);
            String assessmentURl = assign.getAssessmentShareLink().getText();//fetch url from UI
            new Navigator().logout();
            driver.get("clever.com/in/edulastic");
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(cleverSync.cleverHeaderMenu));
            cleverSync.cleverHeaderMenu.click();
            cleverSync.cleverLogout.click();
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(cleverSync.logInWithClever));

            //login with different Teacher
            new Login().cleverSyncLogin(teacherEmail2, "snapwiz123");
            driver.get(assessmentURl);
            Assert.assertTrue(assessmentDetailsPage.getAssessment_name().getText().contains(assessmentName), "Not open same assessment");
            new Assignment().create(dataIndex, "truefalse");//create assessment
            new Assignment().createAssessmentUsingDraftStatus(dataIndex);//assign assessment
            Thread.sleep(3000);
            assessmentURl = assign.getAssessmentShareLink().getText();//fetch url from UI
            new Navigator().logout();
            driver.get("clever.com/in/edulastic");
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(cleverSync.cleverHeaderMenu));
            cleverSync.cleverHeaderMenu.click();
            cleverSync.cleverLogout.click();
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(cleverSync.logInWithClever));

            new Login().cleverSyncLogin(studentEmail, "snapwiz123");
            driver.get(assessmentURl);
            StudentDashboard studentDashboard= PageFactory.initElements(driver, StudentDashboard.class);
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(studentDashboard.notificationMessage));
            Assert.assertEquals(studentDashboard.notificationMessage.getText(), "Assessment not found. Please check with your instructor.", "Error message not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in AssessmentSharingWithCleverSync class in method assignmentSharingWithCleverSyncGoogleUserBeforeSignIn",e);

        }

    }
}
