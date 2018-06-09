package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.signupsignin;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Edulastic.Login;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Google.SignIn;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login.LoginPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.manageClass.ManageClass;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.SignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.StudentSignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by shashank on 15-07-2015.
 */
public class StudentSignUpWithGoogle extends Driver{

    String baseUrl = "http://idc-dev1.snapwiz.net/";
    String googleUrl = "https://www.google.co.in/";
    String password = "snapwiz2015";
    String email_student = "snaplogicautomationstudent";
    String email_student1 = "snaplogicautomationstudent1";


    @Test(priority = 1, enabled = true)
    public void studentSignUpWithGoogle(){
        try{

            String randNumber=new RandomString().randominteger(4);
            ManageClass manageClass=PageFactory.initElements(driver,ManageClass.class);
            SignUpPage signUpPage=PageFactory.initElements(driver,SignUpPage.class);
            //Execute sql query to remove user from database
            int rst =  DBConnect.st.executeUpdate("update t_user set email = '" + email_student + randNumber + "', username = '" + randNumber + "',open_id ='" + randNumber + "' where email = '" + email_student + "@gmail.com';");

            String appendChar="1";

            SignIn signIn = PageFactory.initElements(driver, SignIn.class);
            SchoolPage schoolPage = PageFactory.initElements(driver,SchoolPage.class);
            /*Steps : 1.Create a google ID
              2.Sign in as a teacher in Edulastic using 'With Google' without sign up*/
            //Expected : Teacher should not be able to login if he has not signed up
            Login login = PageFactory.initElements(driver, Login.class);
            new SignUp().teacher(appendChar, 23);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 23);//create school
            String code = new Classes().createClass(appendChar, 23);//create class
            new Navigator().navigateTo("manageclass");//navigate to manage class
            String url = manageClass.getUrl().getAttribute("title");//Get the url
            System.out.println("url "+url);
            new Navigator().logout();
            driver.get( Config.launchURL+"regcd/"+code);
            signUpPage.signUpWithGoogle.click();//Click on button 'With Google'
            Thread.sleep(2000);
            if(driver.findElements(By.id("account-chooser-add-account")).size()!=0){
                driver.findElement(By.id("account-chooser-add-account")).click();
                Thread.sleep(5000);
            }
            signIn.getTextField_email().sendKeys(email_student);// Type Email
            signIn.buttonNext.get(0).click();
            signIn.getTextField_password().sendKeys(password);//Type Password
            signIn.getButton_SignIn().click();// Click button 'Sign in'
            Thread.sleep(5000);
            new Navigator().navigateTo("manageclass");
            //check whether Student login insame class or not
            Assert.assertEquals(new TextFetch().textfetchbyclass("as-manageClass-codeValue"),code,"Not same class login");

            driver.quit();

            reInitDriver();
            Login loginAfterQuit = PageFactory.initElements(driver, Login.class);
            SignIn signInAfterQuit = PageFactory.initElements(driver, SignIn.class);
            StudentSignUpPage studentSignUpPage = PageFactory.initElements(driver, StudentSignUpPage.class);
            driver.get(Config.launchURL);
            rst =  DBConnect.st.executeUpdate("update t_user set email = '" + email_student1 + randNumber + "', username = '" + randNumber + "',open_id ='" + randNumber + "' where email = '" + email_student1 + "@gmail.com';");
            new SignUp().googleSignIn_EdulasticFromSignUpWithUrl(email_student1, password, Config.launchURL);

            Thread.sleep(5000);
            if(signInAfterQuit.buttonAccept.size()>0)
                signInAfterQuit.buttonAccept.get(0).click();
            Thread.sleep(5000);
            loginAfterQuit.getButton_Student().click();
            Thread.sleep(5000);
            studentSignUpPage.getTextBox_classCode().sendKeys("1457");//Enter invalid class code
            studentSignUpPage.getTextBox_name().sendKeys("Student");//Enter the student name
            studentSignUpPage.getTextBox_email().sendKeys(email_student1);//Enter the student email
            studentSignUpPage.getTextBox_password().sendKeys("snapwiz");//Enter the password
            studentSignUpPage.getTextBox_retypePassword().sendKeys("snapwiz");//Enter retype password
            studentSignUpPage.getButton_signUp().click();//Click on sign up button
            Assert.assertEquals(studentSignUpPage.getErrorMessage_classCode().getText(),"Please provide a valid class code.","Error message is not displayed as expected");
            studentSignUpPage.getTextBox_classCode().clear();
            studentSignUpPage.getTextBox_classCode().sendKeys(code);
            studentSignUpPage.getButton_signUp().click();//Click on sign up button
            Thread.sleep(3000);
            new Navigator().navigateTo("manageclass");
            //check whether Student login insame class or not
            Assert.assertEquals(new TextFetch().textfetchbyclass("as-manageClass-codeValue"),code,"Not same class login");

        }catch(Exception e){
            Assert.fail("Exception in the testcase 'checkEdulasticSignInWithoutSignUpForTeacher' in the class 'WithGoogle'", e);
        }
    }


}
