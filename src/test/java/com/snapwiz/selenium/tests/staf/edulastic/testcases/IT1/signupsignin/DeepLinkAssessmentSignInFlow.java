package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.signupsignin;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login.LoginPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentDetailsPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assign;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by pragya on 20-07-2015.
 */
public class DeepLinkAssessmentSignInFlow extends Driver {

    @Test(priority = 1, enabled = true)
    public void deepLinkAssessmentSignInFlow(){
        try{
            String appendChar1 = "a";
            String appendChar2 = "b";
            String appendCharacterBuild=System.getProperty("UCHAR");
            if(appendCharacterBuild!=null){
                appendChar1 = appendChar1+appendCharacterBuild;
                appendChar2 = appendChar2+appendCharacterBuild;
            }

            int dataIndex = 16;
            Assign assign = PageFactory.initElements(driver, Assign.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
            AssessmentDetailsPage assessmentDetailsPage = PageFactory.initElements(driver,AssessmentDetailsPage.class);

            new SignUp().teacher(appendChar1,dataIndex);//Create a teacher1
            new School().createWithOnlyName(appendChar1,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar1,dataIndex);//Create a class
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);//Sign up as a student with the class code of 1st teacher
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.className("navbar-username")));
            new Navigator().logout();//log out

            new SignUp().teacher(appendChar2,dataIndex);//Create a teacher2
            new School().createWithOnlyName(appendChar2,dataIndex);//Create a school
            new Classes().createClass(appendChar2,dataIndex);//Create a class
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as an instructor1
            new Assignment().create(dataIndex,"truefalse");//Create an assessment
            new Assignment().assignToStudent(dataIndex,appendChar1);//Assign to class
            String assessmentSharingLink = assign.getAssessmentShareLink().getText();
            new Navigator().logout();//Log out

            //Teacher side deep assessment link(login and paste the sharing link)
            new Login().loginAsInstructor(appendChar2,dataIndex);//Login as an instructor
            driver.get(assessmentSharingLink);//Open the assessment sharing url
            Thread.sleep(5000);

            //Instructor should be navigated to assessment details page(check the assessment name)
            Assert.assertTrue(assessmentDetailsPage.getAssessment_name().getText().contains(assessmentname),"Assessment is not found");
            new Navigator().logout();//log out
            Thread.sleep(2000);

            //Teacher side deep assessment link(paste the sharing link and login)
            driver.get(assessmentSharingLink);//Open the assessment sharing url
            loginPage.getTextBox_username().sendKeys("deepLinkAssessmentSignInFlowins" + appendChar2 + "@snapwiz.com");//Login as an instructor2
            loginPage.getTextBox_login_password().sendKeys("snapwiz");//Enter the password
            loginPage.getButton_signIn().click();//Click on sign in page
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.as-card-header")));

            //Instructor should be navigated to assessment details page(check the assessment name)
            Assert.assertTrue(assessmentDetailsPage.getAssessment_name().getText().contains(assessmentname),"Assessment is not found");
            new Navigator().logout();//log out

            //Student side deep assessment link(login and paste assessment sharing url)
            new Login().loginAsStudent(appendChar1,dataIndex);//Login as a student
            //check Timer is present on UI
            driver.get(assessmentSharingLink);//Open the assessment sharing url
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span#cntdwn-assignment-timer")));
            new Navigator().logout();

            //Student side deep assessment link(paste assessment sharing url and Login)
            driver.get(Config.baseURL);//Navigate to app
            loginPage.getTextBox_username().sendKeys("deepLinkAssessmentSignInFlowst"+ appendChar1+ "@snapwiz.com");//Enter mail
            loginPage.getTextBox_login_password().sendKeys("snapwiz");//Enter password
            loginPage.getButton_signIn().click();//Click on sign in button
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("navbar-username")));
            driver.get(assessmentSharingLink);//Open the assessment sharing url

            //check Timer is present on UI
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span#cntdwn-assignment-timer")));
            new Navigator().logout();

        }catch (Exception e){
            Assert.fail("Exception in testcase 'deepLinkAssessmentSignInFlow' in class 'DeepLinkAssessmentSignInFlow'", e);
        }
    }

}
