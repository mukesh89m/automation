package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.signupsignin;


import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Edulastic.Login;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Google.GoogleAccounts;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Google.HomePage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Google.SignIn;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login.LoginPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
/**
 * Created by Shashank on 15-07-2015.
 */
public class TeacherSignUpWithGoogle extends Driver{
    String baseUrl = "http://idc-dev1.snapwiz.net/";
    String googleUrl = "https://www.google.co.in/";
    String password = "snapwiz2015";
    String email = "snaplogic.automation22";
    String email_teacher = "snaplogicautomationteacher";

    @Test(priority = 1, enabled = true)
    public void teacherSignUpCreateNewSchool(){
        try{
            String randNumber=new  RandomString().randominteger(4);
            //Execute sql query to remove user from database
            int rst =  DBConnect.st.executeUpdate("update t_user set email = '" + email + randNumber + "', username = '" + randNumber + "',open_id ='" + randNumber + "' where email = '" + email + "@gmail.com';");
            String appendChar1="13";
            String appendChar2="12";

            SignIn signIn = PageFactory.initElements(driver, SignIn.class);
            SchoolPage schoolPage = PageFactory.initElements(driver,SchoolPage.class);
            /*Steps : 1.Create a google ID
              2.Sign in as a teacher in Edulastic using 'With Google' without sign up*/
            //Expected : Teacher should not be able to login if he has not signed up
            Login login = PageFactory.initElements(driver, Login.class);
            new SignUp().googleSignIn_Edulastic(email,password);
            Thread.sleep(5000);
            if(signIn.buttonAccept.size()>0)
                signIn.buttonAccept.get(0).click();
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(login.getButton_Teacher()));
            login.getButton_Teacher().click();
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("add")));
            new Navigator().addNewSchoolTeacher();
            schoolPage.getButton_addSchool().click();//Click on 'Add school button'

            //Expected - Error should appear if school name is not entered
            Assert.assertEquals(schoolPage.getError_msg_schoolName().getText(),"Please provide a valid school name.","Error message is not appearing correctly if school is not entered");

            schoolPage.getEditbox_schoolName().click();
            schoolPage.getEditbox_schoolName().clear();
            driver.switchTo().activeElement().sendKeys("School");//Enter the school name
            schoolPage.getButton_addSchool().click();//Click on 'Add school button'

            //Expected - Error should appear if the zip code is not entered
            Assert.assertEquals(schoolPage.getError_msg_zip().getText(),"Please provide a valid zip code.","Error message is not appearing correctly if zip is not entered");

            schoolPage.getClose().click();//Click on close button
            String zipcode= new School().createWithOnlyName(appendChar1,1);//Create a school
            new Classes().createClass(appendChar1, 1);//create class
            new Navigator().logout();//log out
            driver.close();
            reInitDriver();
            signIn = PageFactory.initElements(driver, SignIn.class);
            login = PageFactory.initElements(driver, Login.class);
            schoolPage = PageFactory.initElements(driver,SchoolPage.class);
            rst =  DBConnect.st.executeUpdate("update t_user set email = '" + email_teacher + randNumber + "', username = '" + randNumber + "',open_id ='" + randNumber + "' where email = '" + email_teacher + "@gmail.com';");
            new SignUp().googleSignIn_Edulastic(email_teacher,password);
            Thread.sleep(5000);
            if(signIn.buttonAccept.size()>0)
                signIn.buttonAccept.get(0).click();
            Thread.sleep(5000);
            login.getButton_Teacher().click();
            Thread.sleep(5000);
            new Navigator().addNewSchoolTeacher();
            schoolPage.getButton_addSchool().click();//Click on 'Add school button'

            //Expected - Error should appear if school name is not entered
            Assert.assertEquals(schoolPage.getError_msg_schoolName().getText(),"Please provide a valid school name.","Error message is not appearing correctly if school is not entered");
            schoolPage.getClose().click();//Click on close button
            new School().enterAndSelectSchool(zipcode, 1, false);//Select the same school created by instructor 1
            schoolPage.getContinueButton().click();//Click on continue button
            new Classes().createClass(appendChar2,1);//Create a class
            new Navigator().logout();//Log out



        }catch(Exception e){
            Assert.fail("Exception in the testcase 'checkEdulasticSignInWithoutSignUpForTeacher' in the class 'WithGoogle'", e);
        }
    }


}
