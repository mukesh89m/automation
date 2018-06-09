package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.signupsignin;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Edulastic.Login;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Google.SignIn;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login.LoginPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentDetailsPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assign;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.SignUpPage;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

/**
 * Created by shashank on 17-07-2015.
 */
public class DeepLinkAssessmentSignInFlowWithGoogle extends Driver{
    String baseUrl = "http://idc-dev1.snapwiz.net/";
    String password = "snapwiz2015";
    String emailStudent = "snaplogic.automation22";
    String email_teacher = "snaplogicautomationteacher";
    String email_teacher1 = "snaplogicautomationstudent1";

    @Test (priority = 1,enabled = true)
    public void deepLinkAssessmentSignInFlowWithGoogle()
    {
        int dataIndex=24;
        String appendChar1="12";
        try {
            SignIn signIn=PageFactory.initElements(driver,SignIn.class);
            Login login = PageFactory.initElements(driver, Login.class);
            String randNumber = new RandomString().randominteger(4);
            Assign assign=PageFactory.initElements(driver,Assign.class);
            AssessmentDetailsPage assessmentDetailsPage = PageFactory.initElements(driver,AssessmentDetailsPage.class);
            String assessmentName= ReadTestData.readDataByTagName("","assessmentname",Integer.toString(dataIndex));
            DBConnect.st.executeUpdate("update t_user set email = '" + emailStudent + randNumber + "', username = '" + randNumber + "',open_id ='" + randNumber + "' where email = '" + emailStudent+ "@gmail.com';");
            DBConnect.st.executeUpdate("update t_user set email = '" + email_teacher + randNumber + "', username = '" + randNumber + "',open_id ='" + randNumber + "' where email = '" + email_teacher + "@gmail.com';");
            DBConnect.st.executeUpdate("update t_user set email = '" + email_teacher1+ randNumber + "', username = '" + randNumber + "',open_id ='" + randNumber + "' where email = '" + email_teacher1+ "@gmail.com';");
            new SignUp().googleSignIn_Edulastic(email_teacher, password);//login with google user
            if(signIn.buttonAccept.size()>0)
                signIn.buttonAccept.get(0).click();
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(login.getButton_Teacher()));
            login.getButton_Teacher().click();//select teacher
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("add")));
            new School().createWithOnlyName(appendChar1,1);//Create a school
            String classscode=new Classes().createClass(appendChar1, 1);//create class
            new Assignment().create(dataIndex,"truefalse");//create assessment
            new Assignment().assignToStudent(dataIndex,appendChar1);//assign assessment
            Thread.sleep(3000);
            String assessmentURl=assign.getAssessmentShareLink().getText();//fetch url from UI
            System.out.println(assessmentURl);
            new Navigator().logout();
            driver.close();
            reInitDriver();

            new SignUp().googleSignIn_Edulastic(email_teacher1, password);//login with another teacher
            login = PageFactory.initElements(driver, Login.class);
            SignIn signInAfterQuit=PageFactory.initElements(driver,SignIn.class);
            if(signInAfterQuit.buttonAccept.size()>0)
                signInAfterQuit.buttonAccept.get(0).click();
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(login.getButton_Teacher()));
            login.getButton_Teacher().click();
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("add")));
            new School().createWithOnlyName(appendChar1,1);//Create a school
            new Classes().createClass(appendChar1, 1);//create class
            new Navigator().logout();
            driver.close();
            reInitDriver();
            //access assessment url after sign in
            //login with student




            signIn = PageFactory.initElements(driver, SignIn.class);
            SignUpPage signUpPage=PageFactory.initElements(driver,SignUpPage.class);
            driver.get(Config.launchURL+"regcd/"+classscode);
            signUpPage.signUpWithGoogle.click();//Click on button 'With Google'
            Thread.sleep(2000);
            if(driver.findElements(By.id("account-chooser-add-account")).size()!=0){
                driver.findElement(By.id("account-chooser-add-account")).click();
                Thread.sleep(5000);
            }
            signIn.getTextField_email().sendKeys(emailStudent);// Type Email
            signIn.buttonNext.get(0).click();
            signIn.getTextField_password().sendKeys(password);//Type Password
            signIn.getButton_SignIn().click();// Click button 'Sign in'
            Thread.sleep(5000);







//            new SignUp().googleSignIn_EdulasticWithUrl(emailStudent, password, Config.launchURL+"regcd/"+classscode);
            new Navigator().navigateTo("manageclass");
            //navigate to assessment URL
            driver.get(assessmentURl);

            //check Timer is present on UI
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span#cntdwn-assignment-timer")));
            new Navigator().logout();
            driver.close();
            reInitDriver();
            //login with student
            new SignUp().googleSignIn_Edulastic(email_teacher1, password);
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("navbar-username")));
            //navigate to assessment URL
            driver.get(assessmentURl);
            Thread.sleep(10000);
            assessmentDetailsPage = PageFactory.initElements(driver,AssessmentDetailsPage.class);
            //check the name of assessment
            Assert.assertTrue("Not open same assessment",assessmentDetailsPage.getAssessment_name().getText().contains(assessmentName));


            //access url before sign in


            new Navigator().logout();
            driver.close();
            reInitDriver();
            //login with student
            new SignUp().googleSignIn_EdulasticFromSignUpWithUrl(emailStudent, password, assessmentURl);
          //      new Navigator().navigateTo("manageclass");
            //navigate to assessment URL


            //check Timer is present on UI
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span#cntdwn-assignment-timer")));
            new Navigator().logout();
            driver.close();
            reInitDriver();
            driver.get(assessmentURl);
            //login with student
            new SignUp().googleSignIn_EdulasticFromSignUpWithUrl(email_teacher1, password, assessmentURl);

            //navigate to assessment URL

            Thread.sleep(10000);
            assessmentDetailsPage = PageFactory.initElements(driver,AssessmentDetailsPage.class);
            //check the name of assessment
            Assert.assertTrue("Not open same assessment",assessmentDetailsPage.getAssessment_name().getText().contains(assessmentName));


        }
        catch (Exception e)
        {
            org.testng.Assert.fail("Exception in testcase registrationUI in class StudentRegisterFromRegPage", e);
        }
        }


}
