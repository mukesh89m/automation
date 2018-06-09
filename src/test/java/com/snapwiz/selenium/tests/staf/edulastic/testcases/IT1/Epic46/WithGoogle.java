package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.Epic46;


import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Edulastic.Login;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Google.GoogleAccounts;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Google.HomePage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Google.SignIn;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login.LoginPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.InstructorDashboard;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 * Created by Dharaneesh T Gowda on 27-01-2015.
 */
public class WithGoogle extends Driver{
    String baseUrl = "http://idc-dev1.snapwiz.net/";
    String googleUrl = "https://www.google.co.in/";
    String password = "snapwiz2015";
    String email = "snaplogic.automation22";
    String email_teacher = "snaplogicautomationteacher";
    String email_student = "snaplogicautomationstudent";
    String email_verification_teacher = "mukesh89jh@gmail.com";
    String email_verification_student = "22priyanka.das@gmail.com";

    public void googleSignUpForTeacher_Edulastic(String email,String password){
        try{
            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            SignIn signIn = PageFactory.initElements(driver, SignIn.class);
            Login login = PageFactory.initElements(driver, Login.class);
            driver.get(baseUrl);
            login.getButton_Teacher().click();//Click on Teacher button
            Thread.sleep(3000);
            loginPage.getButton_WithGoogle().click();//Click on button 'With Google'
            signIn.getTextField_email().sendKeys(email);// Type Email
            signIn.getTextField_password().sendKeys(password);//Type Password
            signIn.getButton_SignIn().click();// Click button 'Sign in'
            Thread.sleep(10000);
            if(driver.findElements(By.id("submit_approve_access")).size()!=0){
                WebElement we = driver.findElement(By.id("submit_approve_access"));
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();",we);
                Thread.sleep(5000);
            }
        }catch(Exception e){
            Assert.fail("Exception in the method 'googleSignUp_Edulastic' in the class 'WithGoogle'", e);
        }
    }

    public void googleSignUpForStudent_Edulastic(String email,String password,String classCode){
        try{
            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            SignIn signIn = PageFactory.initElements(driver, SignIn.class);
            Login login = PageFactory.initElements(driver, Login.class);
            driver.get(baseUrl);
            login.getButton_Student().click();//Click on student button
            Thread.sleep(3000);
            loginPage.getButton_WithGoogle().click();//Click on button 'With Google'
            Thread.sleep(3000);
            System.out.println("Class coe : " +classCode);
            login.getTextField_EnterClassCode().sendKeys(classCode);
            Thread.sleep(3000);
            login.getButton_ConnectWithGoogle().click();
            if(driver.findElements(By.id("account-chooser-link")).size()!=0){
                driver.findElement(By.id("account-chooser-link")).click();
            }
            if(driver.findElements(By.id("account-chooser-add-account")).size()!=0){
                login.geLink_AddAccount().click();
            }
            signIn.getTextField_email().sendKeys(email);// Type Email
            signIn.getTextField_password().sendKeys(password);//Type Password
            Thread.sleep(3000);
            signIn.getButton_SignIn().click();// Click button 'Sign in'
            Thread.sleep(10000);
            if(driver.findElements(By.id("submit_approve_access")).size()!=0){
                WebElement we = driver.findElement(By.id("submit_approve_access"));
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();",we);
                Thread.sleep(5000);
            }
        }catch(Exception e){
            Assert.fail("Exception in the method 'googleSignUp_Edulastic' in the class 'WithGoogle'", e);
        }
    }


    public void googleSignIn_Edulastic(String email,String password){
        try{
            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            SignIn signIn = PageFactory.initElements(driver, SignIn.class);
            driver.get(baseUrl);
            loginPage.getButton_WithGoogle().click();//Click on button 'With Google'
            Thread.sleep(5000);
            if(driver.findElements(By.id("account-chooser-add-account")).size()!=0){
                driver.findElement(By.id("account-chooser-add-account")).click();
                Thread.sleep(5000);
            }
            signIn.getTextField_email().sendKeys(email);// Type Email
            signIn.getTextField_password().sendKeys(password);//Type Password
            Thread.sleep(5000);
            signIn.getButton_SignIn().click();// Click button 'Sign in'
            Thread.sleep(5000);
        }catch(Exception e){
            Assert.fail("Exception in the method 'googleSignIn_Edulastic' in the class 'WithGoogle'", e);
        }
    }



    public void googleSignOut(){
        try{
            HomePage homePage = PageFactory.initElements(driver, HomePage.class);
            driver.get(googleUrl);
            if(driver.findElements(By.linkText("Sign in")).size()==0){
                homePage.getIcon_userWindow().click();//Click on down arrow icon to view sign out option
                homePage.getButton_SignOut().click();//Click on 'Sign out' option
            }
        }catch(Exception e){
            Assert.fail("Exception in the method 'googleSignOut' in the class 'WithGoogle'", e);
        }
    }

    public void googleSignIn(String email,String password){
        try{
            SignIn signIn = PageFactory.initElements(driver, SignIn.class);
            HomePage homePage = PageFactory.initElements(driver, HomePage.class);
            GoogleAccounts googleAccounts = PageFactory.initElements(driver, GoogleAccounts.class);
            homePage.getButton_SignIn().click();//Click on 'Sign in' button
            if(driver.findElements(By.id("account-chooser-add-account")).size()!=0){
                googleAccounts.getLink_AddAccount().click();//Click on 'Add account' button
            }
            signIn.getTextField_email().clear();//Clear email Id Entry
            signIn.getTextField_email().sendKeys(email);// Type Email
            signIn.getTextField_password().clear();//Clear Password Entry
            signIn.getTextField_password().sendKeys(password);//Type Password
            signIn.getButton_SignIn().click();// Click button 'Sign in'
            Thread.sleep(5000);
        }catch(Exception e){
            Assert.fail("Exception in the method 'googleSignIn' in the class 'WithGoogle'", e);
        }
    }



    @Test(priority = 1, enabled = true)
    public void checkEdulasticSignInWithoutSignUpForTeacher(){
        try{
            /*Steps : 1.Create a google ID
              2.Sign in as a teacher in Edulastic using 'With Google' without sign up*/
            //Expected : Teacher should not be able to login if he has not signed up
            Login login = PageFactory.initElements(driver, Login.class);
            googleSignIn_Edulastic(email,password);
            Thread.sleep(5000);
            login.getButton_Teacher().click();
            Thread.sleep(5000);
            if(!(driver.getCurrentUrl().contains("/teacher"))){
                Assert.fail("Teacher should not get log in if he is not sighned up");
            }
        }catch(Exception e){
            Assert.fail("Exception in the testcase 'checkEdulasticSignInWithoutSignUpForTeacher' in the class 'WithGoogle'", e);
        }
    }
    @Test(priority = 2, enabled = true)
    public void checkEdulasticSignInWithoutSignUpForStudent(){
        try{
             /*Steps : 1.Create a google ID
              2.Sign in as a student in Edulastic using 'With Google' without sign up*/
            //Expected : Student should not be able to login if he has not signed up
            Login login = PageFactory.initElements(driver, Login.class);
            googleSignIn_Edulastic(email,password);
            Thread.sleep(5000);
            login.getButton_Student().click();
            Thread.sleep(5000);
            if(!(driver.getCurrentUrl().contains("/student"))){
                Assert.fail("Teacher should not get log in if he is not sighned up");
            }
        }catch(Exception e){
            Assert.fail("Exception in the testcase 'checkEdulasticSignInWithoutSignUpForStudent' in the class 'WithGoogle'", e);
        }
    }



    @Test(priority = 3, enabled = true)
    public void checkGoogleSignUpForTeacher(){

        try{
            String appendChar = "Try";
            int index = 1;
            googleSignOut();
            InstructorDashboard instructorDashboard = PageFactory.initElements(driver, InstructorDashboard.class);
            Thread.sleep(9000);
            googleSignUpForTeacher_Edulastic(email_teacher, password);
            Thread.sleep(5000);
            handleGoogleVerificationProcess_teacher();
            String actual = instructorDashboard.getLabel_userName().getText().trim();
            String expected = email_teacher;
            Assert.assertEquals(actual,expected,"Teacher has not logged in");
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar,index);//create class
            System.out.println("Class code : " +classCode);
            Thread.sleep(3000);
            new Navigator().logout();//log out
            Thread.sleep(3000);
        }catch(Exception e){
            Assert.fail("Exception in the testcase 'checkGoogleSignUpForTeacher' in the class 'WithGoogle'", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void checkGoogleSigInForTeacher(){
        try{
            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            driver.get(googleUrl);
            Thread.sleep(1000);
            googleSignIn(email_teacher,password);
            driver.findElement(By.linkText("Gmail")).sendKeys(Keys.CONTROL + "t");
            Thread.sleep(10000);
            driver.get(baseUrl);
            Thread.sleep(10000);
            loginPage.getButton_WithGoogle().click();//Click on button 'With Google'
            Thread.sleep(20000);
            if(!(driver.getCurrentUrl().contains("dashboard"))){
                Assert.fail("Automatically Student is not getting logged in");
            }
        }catch(Exception e){
            Assert.fail("Exception in the testcase 'checkGoogleSigInForTeacher' in the class 'WithGoogle'", e);
        }
    }



    @Test(priority = 5, enabled = true)
    public void checkGoogleSignUpForStudent(){
        try{
            String appendChar = "Try";
            int index = 1;
            new SignUp().teacher(appendChar, index);//Sign up as teacher
            new com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Login().loginAsInstructor(appendChar, index);//log in as teacher
            new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar,index);//create class
            System.out.println("Class code : " +classCode);
            new Navigator().logout();//log out
            googleSignOut();
            Thread.sleep(9000);
            googleSignUpForStudent_Edulastic(email_student, password,classCode);
            Thread.sleep(5000);
            handleGoogleVerificationProcess_student();
        }catch(Exception e){
            Assert.fail("Exception in the testcase 'checkGoogleSignUpForStudent' in the class 'WithGoogle'", e);
        }
    }


    @Test(priority = 6, enabled = true)
    public void checkGoogleSigInForStudent(){
        try{
            HomePage homePage = PageFactory.initElements(driver, HomePage.class);
            googleSignOut();
            Thread.sleep(9000);
            googleSignIn_Edulastic(email_student,password);
            Thread.sleep(1000);
            if(driver.findElements(By.name("student")).size()!=0){
                driver.findElement(By.name("student")).click();
            }
            Thread.sleep(9000);
            driver.findElement(By.xpath("//body")).sendKeys(Keys.CONTROL + "t");
            Thread.sleep(5000);
            driver.get(googleUrl);
            Thread.sleep(20000);
            String accountName  = homePage.getIcon_userWindow().getText();
            String expected = email_student.trim()+"@gmail.com";
            Assert.assertEquals(accountName,expected,"Google Account has not logged in");

        }catch(Exception e){
            Assert.fail("Exception in the testcase 'checkGoogleSigInForStudent' in the class 'WithGoogle'", e);
        }
    }



    public void handleGoogleVerificationProcess_teacher(){
        try{
           if(driver.findElements(By.id("RecoveryEmailChallenge")).size()!=0){
               driver.findElement(By.id("RecoveryEmailChallenge")).click();
               driver.findElement(By.id("emailAnswer")).clear();
               driver.findElement(By.id("emailAnswer")).sendKeys(email_verification_teacher);
               driver.findElement(By.id("submitChallenge")).click();

           }
        }catch(Exception e){
            Assert.fail("Exception in the method 'handleGoogleVerificationProcess' in the class 'WithGoogle'", e);
        }
    }

    public void handleGoogleVerificationProcess_student(){
        try{
            if(driver.findElements(By.id("RecoveryEmailChallenge")).size()!=0){
                driver.findElement(By.id("RecoveryEmailChallenge")).click();
                driver.findElement(By.id("emailAnswer")).clear();
                driver.findElement(By.id("emailAnswer")).sendKeys(email_verification_student);
                driver.findElement(By.id("submitChallenge")).click();

            }
        }catch(Exception e){
            Assert.fail("Exception in the method 'handleGoogleVerificationProcess' in the class 'WithGoogle'", e);
        }
    }
}
