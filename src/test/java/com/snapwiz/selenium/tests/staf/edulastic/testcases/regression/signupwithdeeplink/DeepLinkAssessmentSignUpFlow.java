package com.snapwiz.selenium.tests.staf.edulastic.testcases.regression.signupwithdeeplink;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login.LoginPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentDetailsPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentLibrary;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assign;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.classpage.ClassPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.manageClass.ManageClass;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.StudentSignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.TeacherSignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.DropDown;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextSend;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.logging.Level;

/**
 * Created by pragya on 21-07-2015.
 */
public class DeepLinkAssessmentSignUpFlow extends Driver {


    @Test(priority = 1,enabled = true)
    public void deepAssessmentLinkSignUpFlow(){
        try{
            String appendChar1 = "a";
            String appendChar2 = "b";
            logger.log(Level.INFO, "Random string1  " + appendChar1);
            logger.log(Level.INFO, "Random string2  " + appendChar2);

            int dataIndex = 17;

            ClassPage classPage = PageFactory.initElements(driver,ClassPage.class);
            Assign assign = PageFactory.initElements(driver, Assign.class);
            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            AssessmentDetailsPage assessmentDetailsPage = PageFactory.initElements(driver,AssessmentDetailsPage.class);
            StudentSignUpPage studentSignUpPage = PageFactory.initElements(driver,StudentSignUpPage.class);
            TeacherSignUpPage teacherSignUpPage = PageFactory.initElements(driver,TeacherSignUpPage.class);
            ManageClass manageClass=PageFactory.initElements(driver,ManageClass.class);
            String assessmentName= ReadTestData.readDataByTagName("","assessmentname",Integer.toString(dataIndex));

            new SignUp().teacher(appendChar1,dataIndex);//Create a teacher1
            new School().createWithOnlyName(appendChar1,dataIndex);//Create a school
            new Classes().createClass(appendChar1,dataIndex);//Create a class
            new Assignment().create(dataIndex,"truefalse");//Create an assessment
            new Assignment().assignToStudent(dataIndex,appendChar1);//Assign to class
            String assessmentSharingLink = assign.getAssessmentShareLink().getText();
            new Navigator().logout();//Log out

            driver.get(assessmentSharingLink);//Open the assessment sharing url
            new Navigator().studentSignUp();//Navigate to student sign up page
            String classCodeFieldDisabled = studentSignUpPage.getTextBox_classCode().getAttribute("disabled");

            //Expected - Class code should be disabled by default
            Assert.assertEquals(classCodeFieldDisabled,"true","Class code field is not disabled");

            studentSignUpPage.getTextBox_name().sendKeys("deepAssessmentLinkSignUpFlow"+appendChar1);//Enter the name
            studentSignUpPage.getTextBox_email().sendKeys("deepAssessmentLinkSignUpFlowst"+appendChar1+"@snapwiz.com");
            studentSignUpPage.getTextBox_password().sendKeys("snapwiz");//Enter password
            studentSignUpPage.getTextBox_retypePassword().sendKeys("snapwiz");//Retype password
            studentSignUpPage.getButton_signUp().click();//Click on sign up button

            //check Timer is present on UI(Student should be navigated to assessment attempt page)
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span#cntdwn-assignment-timer")));
            new Navigator().logout();//log out

            //Instructor side verification
            driver.get(assessmentSharingLink);//Open the assessment sharing url
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[class='btn btn-primary center-block as-goto-signup-button']")));
            loginPage.getButton_signUpItsFree().click();//Click on sign up its free
            studentSignUpPage.getTextBox_name().sendKeys("deepAssessmentLinkSignUpFlow"+appendChar2);//Enter the name of instructor 1
            studentSignUpPage.getTextBox_email().sendKeys("deepAssessmentLinkSignUpFlowins"+appendChar2+"@snapwiz.com");//Enter instructor 2 email
            studentSignUpPage.getTextBox_password().sendKeys("snapwiz");//Enter password
            studentSignUpPage.getTextBox_retypePassword().sendKeys("snapwiz");//Retype password
            teacherSignUpPage.getButton_signUp().click();//Click on sign in button
            new School().createWithOnlyName(appendChar2,dataIndex);//Add a new school
            //create class
            classPage.nameOfClass.sendKeys("Automation class");//Enter Class name
            new DropDown().selectValue("class", "as-add-grade-dropDown", "Grade 1");//Select a grade
            new DropDown().selectValue("class", "as-add-subjectArea-dropDown", "Mathematics");//Select subject area
            Thread.sleep(1000);
            new DropDown().selectValue("class", "as-add-subject-dropDown", "Math - Common Core");//select subject
            classPage.finishButton.click();// Click on Finish button
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='as-search-blue-btn as-search-next btn btn-blue js-manageclass']")));
            new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(manageClass.showMeMyAssessment));
            manageClass.showMeMyAssessment.click();

            //check the name of assessment(Teacher should be navigated to assessment details page)
            Assert.assertTrue(assessmentDetailsPage.getAssessment_name().getText().contains(assessmentName),"Not open same assessment");

        }catch (Exception e){
            Assert.fail("Exception in testcase 'deepAssessmentLinkSignUpFlow' in class 'DeepLinkAssessmentSignUpFlow'", e);

        }
    }

    @Test(priority = 2,enabled = true)
    public void deepAssessmentLinkSignUpFlowInstructorExistingSchool(){
        try{
            String appendChar1 = "a";
            String appendChar2 = "b";
            logger.log(Level.INFO, "Random string1  " + appendChar1);
            logger.log(Level.INFO, "Random string2  " + appendChar2);

            int dataIndex = 116;

            ClassPage classPage = PageFactory.initElements(driver,ClassPage.class);
            Assign assign = PageFactory.initElements(driver, Assign.class);
            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            AssessmentDetailsPage assessmentDetailsPage = PageFactory.initElements(driver,AssessmentDetailsPage.class);
            StudentSignUpPage studentSignUpPage = PageFactory.initElements(driver,StudentSignUpPage.class);
            TeacherSignUpPage teacherSignUpPage = PageFactory.initElements(driver,TeacherSignUpPage.class);
            ManageClass manageClass=PageFactory.initElements(driver,ManageClass.class);
            String assessmentName= ReadTestData.readDataByTagName("","assessmentname",Integer.toString(dataIndex));
            SchoolPage schoolPage = PageFactory.initElements(driver,SchoolPage.class);

            new SignUp().teacher(appendChar1,dataIndex);//Create a teacher1
            String zipcode=new School().createWithOnlyName(appendChar1,dataIndex);//Create a school
            new Classes().createClass(appendChar1,dataIndex);//Create a class
            new Assignment().create(dataIndex,"truefalse");//Create an assessment
            new Assignment().assignToStudent(dataIndex,appendChar1);//Assign to class
            String assessmentSharingLink = assign.getAssessmentShareLink().getText();
            new Navigator().logout();//Log out

            driver.get(assessmentSharingLink);//Open the assessment sharing url
            loginPage.getButton_signUpItsFree().click();//Click on sign up its free
            studentSignUpPage.getTextBox_name().sendKeys("deepAssessmentLinkSignUpFlowInstructorExistingSchool"+appendChar2);//Enter the name of instructor 1
            studentSignUpPage.getTextBox_email().sendKeys("deepAssessmentLinkSignUpFlowInstructorExistingSchoolins"+appendChar2+"@snapwiz.com");//Enter instructor 2 email
            studentSignUpPage.getTextBox_password().sendKeys("snapwiz");//Enter password
            studentSignUpPage.getTextBox_retypePassword().sendKeys("snapwiz");//Retype password
            teacherSignUpPage.getButton_signUp().click();//Click on sign up button
            new School().enterAndSelectSchool(zipcode,dataIndex,false);//Select the existing school
            schoolPage.getContinueButton().click();//Click on continue button
            //create class
            classPage.nameOfClass.sendKeys("Automation class");//Enter Class name
            new DropDown().selectValue("class", "as-add-grade-dropDown", "Grade 1");//Select a grade
            new DropDown().selectValue("class", "as-add-subjectArea-dropDown", "Mathematics");//Select subject area
            Thread.sleep(1000);
            new DropDown().selectValue("class", "as-add-subject-dropDown", "Math - Common Core");//select subject
            classPage.finishButton.click();// Click on Finish button
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='as-search-blue-btn as-search-next btn btn-blue js-manageclass']")));
            new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(manageClass.showMeMyAssessment));
            manageClass.showMeMyAssessment.click();

            //check the name of assessment(Teacher should be navigated to assessment details page)
            Assert.assertTrue(assessmentDetailsPage.getAssessment_name().getText().contains(assessmentName),"Not open same assessment");

        }catch (Exception e){

        }
    }


    @Test(priority = 3,enabled = true)
    public void deepAssessmentLinkSignUpFlowSharedAtDistrictLevel(){
        try{
            String appendChar1 = "a";
            String appendChar2 = "b";
            logger.log(Level.INFO, "Random string1  " + appendChar1);
            logger.log(Level.INFO, "Random string2  " + appendChar2);

            int dataIndex = 175;

            AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
            ClassPage classPage = PageFactory.initElements(driver,ClassPage.class);
            Assign assign = PageFactory.initElements(driver, Assign.class);
            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            AssessmentDetailsPage assessmentDetailsPage = PageFactory.initElements(driver,AssessmentDetailsPage.class);
            StudentSignUpPage studentSignUpPage = PageFactory.initElements(driver,StudentSignUpPage.class);
            TeacherSignUpPage teacherSignUpPage = PageFactory.initElements(driver,TeacherSignUpPage.class);
            ManageClass manageClass=PageFactory.initElements(driver,ManageClass.class);
            String assessmentName= ReadTestData.readDataByTagName("","assessmentname",Integer.toString(dataIndex));

            new SignUp().teacher(appendChar1,dataIndex);//Create a teacher1
            new School().createWithOnlyName(appendChar1,dataIndex);//Create a school
            new Classes().createClass(appendChar1,dataIndex);//Create a class
            new Assignment().create(dataIndex,"truefalse");//Create an assessment
            new Assignment().assignToStudent(dataIndex,appendChar1);//Assign to class
            String assessmentSharingLink = assign.getAssessmentShareLink().getText();
            new Navigator().logout();//Log out

            driver.get(assessmentSharingLink);//Open the assessment sharing url
            new Navigator().studentSignUp();//Navigate to student sign up page
            String classCodeFieldDisabled = studentSignUpPage.getTextBox_classCode().getAttribute("disabled");

            //Expected - Class code should be disabled by default
            Assert.assertEquals(classCodeFieldDisabled,"true","Class code field is not disabled");

            studentSignUpPage.getTextBox_name().sendKeys("deepAssessmentLinkSignUpFlow"+appendChar1);//Enter the name
            studentSignUpPage.getTextBox_email().sendKeys("deepAssessmentLinkSignUpFlowst"+appendChar1+"@snapwiz.com");
            studentSignUpPage.getTextBox_password().sendKeys("snapwiz");//Enter password
            studentSignUpPage.getTextBox_retypePassword().sendKeys("snapwiz");//Retype password
            studentSignUpPage.getButton_signUp().click();//Click on sign up button

            //check Timer is present on UI(Student should be navigated to assessment attempt page)
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span#cntdwn-assignment-timer")));
            new Navigator().logout();//log out

            //Instructor side verification
            driver.get(assessmentSharingLink);//Open the assessment sharing url
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[class='btn btn-primary center-block as-goto-signup-button']")));
            loginPage.getButton_signUpItsFree().click();//Click on sign up its free
            studentSignUpPage.getTextBox_name().sendKeys("deepAssessmentLinkSignUpFlow"+appendChar2);//Enter the name of instructor 1
            studentSignUpPage.getTextBox_email().sendKeys("deepAssessmentLinkSignUpFlowins"+appendChar2+"@snapwiz.com");//Enter instructor 2 email
            studentSignUpPage.getTextBox_password().sendKeys("snapwiz");//Enter password
            studentSignUpPage.getTextBox_retypePassword().sendKeys("snapwiz");//Retype password
            teacherSignUpPage.getButton_signUp().click();//Click on sign in button
            new School().createWithOnlyName(appendChar2,dataIndex);//Add a new school
            //create class
            classPage.nameOfClass.sendKeys("Automation class");//Enter Class name
            new DropDown().selectValue("class", "as-add-grade-dropDown", "Grade 1");//Select a grade
            new DropDown().selectValue("class", "as-add-subjectArea-dropDown", "Mathematics");//Select subject area
            Thread.sleep(1000);
            new DropDown().selectValue("class", "as-add-subject-dropDown", "Math - Common Core");//select subject
            classPage.finishButton.click();// Click on Finish button
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='as-search-blue-btn as-search-next btn btn-blue js-manageclass']")));
            new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(manageClass.showMeMyAssessment));
            manageClass.showMeMyAssessment.click();

            //Assessment should not appear and error message should appear
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("toast-message")));
            Assert.assertEquals(assessmentLibrary.errorMessage.getText(), "You are not authorized to view this assessment.", "error message not matches");


        }catch (Exception e){
            Assert.fail("Exception in testcase 'deepAssessmentLinkSignUpFlow' in class 'DeepLinkAssessmentSignUpFlow'", e);

        }
    }


}
