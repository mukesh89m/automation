package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.signupsignin;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login.LoginPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.SignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.StudentSignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*
 * Created by root on 19/8/14.
 */
public class StudentRegisterFromRegPage extends Driver {

    @Test(priority = 1,enabled = true)
    public void registrationUI()
    {
        try
        {
            SignUpPage signUpPage = PageFactory.initElements(driver,SignUpPage.class);
            LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);

            driver.get(Config.baseURL);
            new Navigator().studentSignUp();
            //Row no 103 1.Verify the student registration page elements.
            if(new BooleanValue().booleanbyclass("as-logo")==false)
                Assert.fail("edulastic logo on student direct registration page not shown on loginAsInstructor page");

            if(new BooleanValue().booleanbyclass("as-registration-titleTag")==false)
                Assert.fail("Student Sign up Label on student direct registration page not shown");

            if(!driver.findElement(By.id("class-code")).getAttribute("placeholder").equals("Enter Class Code"))
                Assert.fail("Placeholder for class code not valid in student direct registration page");

            if(!driver.findElement(By.id("user-email")).getAttribute("placeholder").equals("Enter your Username or Email"))
                Assert.fail("Placeholder for user email not valid in student direct registration page");

            if(!driver.findElement(By.id("user-password")).getAttribute("placeholder").equals("Enter your Password"))
                Assert.fail("Placeholder for password not valid in student direct registration page");
            if(!driver.findElement(By.id("first-name")).getAttribute("placeholder").equals("Enter your Full Name"))
                Assert.fail("Placeholder for first name not valid in student direct registration page");


            if(new BooleanValue().booleanbycssselector("button[class='col-xs-12 btn btn-primary btn-lg as-signup-button col-xs-12 col-sm-6 pull-right']")==false)
                Assert.fail("Sign Up Button on student direct registration page not shown");

            if(!signUpPage.getButton_signIn().getText().contains("Sign In"))
                Assert.fail("Sign in button is not present");

            signUpPage.getButton_signIn().click();//Click on sign in button

            //Expected - User should be navigated to sign in page
            Assert.assertEquals(loginPage.getSignIntoYourAccount().getText(),"Sign into your Account","User is not navigated to sign in page after clicking on sign in button");



        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase registrationUI in class StudentRegisterFromRegPage",e);
        }
    }
    @Test(priority = 2,enabled = true)
    public void validationMessages()
    {
        try
        {

            StudentSignUpPage studentSignUpPage = PageFactory.initElements(driver,StudentSignUpPage.class);

            driver.get(Config.baseURL);
            new Navigator().studentSignUp();
            //Row no 105 3.Click on "Sign Up" button without entering anything in any of th fields. Verify validation messages
            studentSignUpPage.getButton_signUp().click();
            if(!new TextFetch().textfetchbycssselector("div[class='as-errorMsg class-code-message']").equalsIgnoreCase("Please provide a valid class code."))
                Assert.fail("Error Message to provide a valid class code not coming when user clicks on finish button without entering any value in class code field in student direct register page");

            if(!new TextFetch().textfetchbycssselector("div[class='as-errorMsg email-message']").equalsIgnoreCase("Please provide valid Username or Email id."))
                Assert.fail("Error Message to provide a username or email not coming when user clicks on finish button without entering any value in class code field in student direct register page");

            if(!new TextFetch().textfetchbycssselector("div[class='as-errorMsg password-message']").equalsIgnoreCase("Please provide a valid password."))
                Assert.fail("Error Message to provide a password not coming when user clicks on finish button without entering any value in class code field in student direct register page");

            if(!new TextFetch().textfetchbycssselector("div[class='as-errorMsg first-name-message']").equalsIgnoreCase("Please provide your full name."))
                Assert.fail("Error Message to provide a first name not coming when user clicks on finish button without entering any value in class code field in student direct register page");

            //Row no 106 4.Enter an invalid class code and change the focus out of it. 1."Please provide a valid class code." validation message should not get hidden.
            new TextSend().textsendbyid("invalidclasscode","class-code");
            new Click().clickByid("user-email"); //changing focus
            if((new Size().getSizeofElement("cssselector","div[class='as-errorMsg class-code-message']") == 0))
                Assert.fail("The error message not shown for entering invalide value of class code while student register using direct register page");

            new TextSend().textsendbyid("firstname","first-name");
            new Click().clickByid("user-email"); //changing focus
            if((new Size().getSizeofElement("cssselector","div[class='as-errorMsg first-name-message']") != 0))
                Assert.fail("The error message shown for not entering proper value of first name while student register using direct register page is visible even if user enters valid value");

            new TextSend().textsendbyid("username","user-email");
            new Click().clickByid("first-name"); //changing focus
            if((new Size().getSizeofElement("cssselector","div[class='as-errorMsg email-message']") != 0))
                Assert.fail("The error message shown for not entering proper value of password while student register using direct register page is visible even if user enters valid value");

            //Row no 107--5.Enter some value in the first name field and change focus.

            String appendChar = "a1";
            new SignUp().teacher(appendChar, 39);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 39);//create school
            String classCode = new Classes().createClass(appendChar, 39);//create class
            new Navigator().logout();//log out

            new Navigator().studentSignUp();
            new TextSend().textsendbyid(classCode,"class-code");
            if((new Size().getSizeofElement("cssselector","div[class='as-errorMsg first-name-message']") != 0))
                Assert.fail("The error message shown for entering proper value of class code while student register using direct register page is visible even if user enters valid value");

            //Row no. 108---5.Enter some value in the first name field and change focus.
            new TextSend().textsendbyid("firstname","first-name");
            new Click().clickByid("user-email"); //changing focus
            if((new Size().getSizeofElement("cssselector","div[class='as-errorMsg first-name-message']") != 0))
                Assert.fail("The error message shown for entering proper value of class code while student register using direct register page is visible even if user enters valid value");

            //Row no. 109---6.Enter some format email id in mail id field and change the focus out side of it.
            new TextSend().textsendbyid("username@gmail.com","user-email");
            new Click().clickByid("first-name"); //changing focus
            if((new Size().getSizeofElement("cssselector","div[class='as-errorMsg email-message']") != 0))
                Assert.fail("The error message is shown entering proper email id while student register using direct register page is visible even if user enters valid value");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase validationMessages in class StudentRegisterFromRegPage",e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void registerStudent()
    {
        try
        {
            String appendCharacterBuild=System.getProperty("UCHAR");
            String appendChar = "e";
            if (appendCharacterBuild!=null)
                appendCharacterBuild=appendChar+appendCharacterBuild;
            else
            {
                appendCharacterBuild=appendChar;
            }
            driver.get(Config.baseURL);
            new SignUp().studentDirectRegister(appendChar, new SignUp().classCodeFromDB(0), 0);

            //Row no 110 7.Enter some existing email id for the entered class code in mail id field and change the focus outside of it. 1."Please provide a valid Email id." validation message should get hidden.
            driver.quit();
            reInitDriver();
            driver.get(Config.baseURL);
            new Navigator().studentSignUp(); //Navigate to student registration page
            new TextSend().textsendbyid(new SignUp().classCodeFromDB(0),"class-code"); //entering a valid class code
            StudentSignUpPage studentSignUp=PageFactory.initElements(driver,StudentSignUpPage.class);
            studentSignUp.getButton_signUp().click();
            studentSignUp.getTextBox_email().sendKeys("registerStudentst"+appendCharacterBuild+"@snapwiz.com");
            studentSignUp.getTextBox_name().click();//changing focus
            Thread.sleep(4000);


            if(!driver.findElement(By.cssSelector("div[class='as-errorMsg email-message as-errorMsg-right']")).getText().contains("Username/Email already exists (in your district). Please")){
                    Assert.fail("Error message is not displayed as expected on email field");
                 }


            //Row no 111 8.Enter the same password for the existing email id for the entered class code and change the focus out side of it. 1."Please provide a valid password" validation message should disappear.
            new TextSend().textsendbyid("snapwiz","user-password"); //password
            new Click().clickByid("first-name"); //changing focus
            if((new Size().getSizeofElement("cssselector","div[class='as-errorMsg password-message']") != 0))
                Assert.fail("The error message shown for not entering proper value of username or email while student register using direct register page is visible even if user enters an existing value of email");

            new TextSend().textsendbyid("registerStudentins"+appendCharacterBuild,"first-name");
            studentSignUp.getButton_signUp().click();//clicking on submit button present on student sign up page after entering details of already registered user
            if(!new TextFetch().textfetchbycssselector("div[class='as-errorMsg email-message as-errorMsg-right']").contains("Username/Email already exists (in your district). Please"))
                Assert.fail("Error Message saying username or email already exists not coming when user clicks on submit button after entering details of an already registered user");

            //Row no 113 10.Change the password keeping the entered existing email id and click on "Sign Up" button. 1.Validation message "Student already registered with different password" should get displayed.
            //bug id - 11053
            new TextSend().textsendbyid("snapwiz123","user-password"); //password
            studentSignUp.getButton_signUp().click();//clicking on submit button
            String message = new TextFetch().textfetchbycssselector("div[class='as-errorMsg email-message as-errorMsg-right']");
            if(!message.contains("Username/Email already exists (in your district). Please"))
                Assert.fail("Error Message saying username or email already registered with different password not coming when user clicks on submit button after entering details of an already registered user but a different password");

            //Row no 114 11.Enter an existing email id which is not associated to the class and a password and click on "Sign Up" button 1.The student should get registered with the class.
            new TextSend().textsendbyid(new SignUp().classCodeFromDB(1),"class-code"); //entering a valid class code
            new TextSend().textsendbyid("snapwiz123","user-password"); //password
            studentSignUp.getButton_signUp().click();//clicking on submit button
            /*new Click().clickByclassname("swhelp-button-next");
            if(!(new TextFetch().textfetchbycssselector("div[class='center header-title']")).equalsIgnoreCase("Dashboard"))
                Assert.fail("Student did not land to Dashboard after getting registered");*/

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase registerStudent in class StudentRegisterFromRegPage",e);
        }
    }
}
