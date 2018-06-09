package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.signupsignin;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login.LoginPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.AdminSignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.StudentSignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.TeacherSignUpPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextSend;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Size;

import java.sql.ResultSet;

public class InstructorAbleToRegisterInApplication extends Driver
{
    @Test(priority = 1,enabled = true)
    public void instructorRegisterUI()
    {
        try
        {
            TeacherSignUpPage teacherSignUpPage = PageFactory.initElements(driver,TeacherSignUpPage.class);
            LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
            StudentSignUpPage studentSignUpPage = PageFactory.initElements(driver,StudentSignUpPage.class);
            AdminSignUpPage adminSignUpPage = PageFactory.initElements(driver,AdminSignUpPage.class);


            driver.get(Config.baseURL);//land on home page
            Thread.sleep(2000);
            //5 1.Verify the log in page elements. "1.The log in page should have the following elements
            //Edulastic(logo)
            boolean logo=new BooleanValue().booleanbycssselector("a[class='as-logo navbar-brand p-sm']");
            if(logo==false)
                Assert.fail("edulastic logo not shown on loginAsInstructor page");

            //Sign In (Label)
            boolean signinLabel=new BooleanValue().booleanbyclass("as-registration-title-left");
            if(signinLabel==false)
                Assert.fail("Sign In Label not shown");

            //Email editbox(Default text-Email)
            String username= driver.findElement(By.id("login-email")).getAttribute("placeholder");
            if(!username.contains("Username / email"))
                Assert.fail("in username filed default text not shown");

            //-Password editbox(Default text-Password)
            String password= driver.findElement(By.id("login-password")).getAttribute("placeholder");
            if(!password.contains("Password"))
                Assert.fail("in password filed default text is not shown");

            //Forgot Password link
            int forgotPwd=driver.findElements(By.xpath("//div[contains(@class,'as-registration-forgot')]")).size();
            System.out.println("forgot"+forgotPwd);
            if(forgotPwd==0)
                Assert.fail("forgot password link not shown");
            int rememberMe=driver.findElements(By.xpath("//div[contains(@class,'as-registration-remember')]")).size();

            //Remember me(Check box)
            if(rememberMe==0)
                Assert.fail("rememberMe  link not shown");

            //New to Edulastic?Sign Up!It's Free""(Text)
            String newRegistration=loginPage.getButton_signUpItsFree().getText();
            if(!newRegistration.contains("Sign Up! It's Free"))
                Assert.fail("for new registration no message shown");

            loginPage.getButton_signUpItsFree().click();//Click on sign up its free button
            Thread.sleep(2000);

            String registrationPage=new TextFetch().textfetchbycssselector("div[class='as-signupType-wrapper text-center']");
            // Student(Button)
            if(!registrationPage.contains("Student"))
                Assert.fail("student link not shown");
            //Teacher(Button)
            if(!registrationPage.contains("Teacher"))
                Assert.fail("Teacher link not shown");
            if(!registrationPage.contains("Administrator"))
                Assert.fail("Administrator link not shown");

            //Row No. 6 1.Click on "Teacher" button. 1.The user should be redirected to teacher registration page.
            teacherSignUpPage.getButton_signUp().click();//click on signup button without enter any things

            //Rown NO. 7 Click on "Student" button.
            new Click().clickByXpath("//span[@mode='student']");
            studentSignUpPage.getButton_signUp().click();//click on signup button without enter any things


            //Rown NO. 7 Click on "Administrator" button.
            new Click().clickByXpath("//span[@mode='admin']");
            adminSignUpPage.getButton_signUp().click();//click on signup button without enter any things

            //10 Verify the teacher registration page functionality
            new Click().clickBycssselector("span[class='as-show-teacher-signup as-signup-option btn-outline btn btn-blue btn-lg']");//click on teacher link
            String fname= driver.findElement(By.id("first-name")).getAttribute("placeholder");
            if(!fname.contains("Enter your Full Name"))
                Assert.fail("in first name filed default text not shown");


            teacherSignUpPage.getButton_signUp().click();//click on signup button without enter any things
            String errorMessageOfFName=new TextFetch().textfetchbycssselector("div[class='as-errorMsg first-name-message']");
            if(!errorMessageOfFName.contains("Please provide your full name."))
                Assert.fail("error message for name not dispaly");

            String errorMessageOfEmail=new TextFetch().textfetchbycssselector("div[class='as-errorMsg email-message']");
            if(!errorMessageOfEmail.contains("Please provide a valid Email id."))
                Assert.fail("error message for email not display");

            String errorMessageOfPassword=new TextFetch().textfetchbycssselector("div[class='as-errorMsg password-message']");
            if(!errorMessageOfPassword.contains("Please provide a valid password."))
                Assert.fail("error message for password not display");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase InstructorAbleToRegisterInApplication in test method instructorRegisterUI",e);
        }
    }
    @Test(priority = 2,enabled = true) //row no 11
    public void instructorSignUpValidateMsgsDisappear()
    {
        try
        {
            LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);
            TeacherSignUpPage teacherSignUpPage = PageFactory.initElements(driver,TeacherSignUpPage.class);
            AdminSignUpPage adminSignUpPage = PageFactory.initElements(driver,AdminSignUpPage.class);
            StudentSignUpPage studentSignUpPage = PageFactory.initElements(driver,StudentSignUpPage.class);

            String appendChar = "a01";
            String appendCharacterBuild=System.getProperty("UCHAR");
            if (appendCharacterBuild!=null)
                appendCharacterBuild=appendChar+appendCharacterBuild;
            else
            {
                appendCharacterBuild=appendChar;
            }
            new SignUp().teacher(appendChar, 0);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 0);//create school
            new Classes().createClass(appendChar, 0);//create class
            new Navigator().logout();//log out

            driver.get(Config.baseURL);//land on home page
            loginPage.getButton_signUpItsFree().click();
            teacherSignUpPage.getButton_signUp().click();//click on signup button without enter any things

            //Row no 11 -- 2.Enter some value in the first name field and change focus. 1.The validation message should disappear.
            new TextSend().textsendbyid("instructorname","first-name");
            new Click().clickByid("user-email");
            if(driver.findElements(By.cssSelector("div[class='as-errorMsg first-name-message']")).size()>0)
                Assert.fail("Error Message of empty first name field is visible even after entering the name in the field while instructor is signing up");

            //Row no 12 -- 3.Enter some invalid format email id in mail id field and change the focus out side of it. 1.The validation message should not disappear.
            new TextSend().textsendbyid("invalidemail","user-email");
            new Click().clickByid("user-password");
            if(driver.findElements(By.cssSelector("div[class='as-errorMsg email-message']")).size()!=1)
                Assert.fail("Error Message of invalid email field is not visible after entering invalid email in the field while instructor is signing up");

            //Row no. 13---4.Enter some existing email id in mail id field and change the focus outside of it.
            new TextSend().textsendbyid("instructorSignUpValidateMsgsDisappearins"+appendCharacterBuild+"@snapwiz.com","user-email");
            new Click().clickByid("user-password");
            Thread.sleep(2000);
            Assert.assertTrue(driver.findElement(By.id("user-password")).isDisplayed(),"User able to signIn with existing registered emailId");

            String message = new TextFetch().textfetchbycssselector("div[class='as-errorMsg email-message as-errorMsg-right']");
            Assert.assertTrue(message.contains("Email already exists"), "\"Email already exists.\" validation message is not displayed.");


            //Row no 14 --5.Enter some email id which does not exist in the system and change the foucs out side of it. 1.The validation messages "Please provide a valid Email id."/"Email already exists." should disappear.
            new TextSend().textsendbyid("instructorSignUpValidateMsgsDisappear@snapwiz.com","user-email");
            new Click().clickByid("user-password");
            if(driver.findElements(By.cssSelector("div[class='as-errorMsg email-message']")).size()>0)
                Assert.fail("Error Message of invalid email field is visible even after entering valid email in the field while instructor is signing up");

            //Row no. 15 6.Enter some value in the password field and change the focus outside of it. 1."Please provide a valid password" validation message should disappear.
            new TextSend().textsendbyid("password","user-password");
            new Click().clickByid("user-email");
            if(driver.findElements(By.cssSelector("div[class='as-errorMsg password-message']")).size()>0)
                Assert.fail("Error Message of password field is visible even after entering valid password in the field while instructor is signing up");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase InstructorAbleToRegisterInApplication in test method instructorSignUpValidateMsgsDisappear",e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void instructorSignUpWithValidValues()
    {
        try
        {
            TeacherSignUpPage teacherSignUpPage=PageFactory.initElements(driver,TeacherSignUpPage.class);
            String appendCharacter = "a";
            String appendCharacterBuild=System.getProperty("UCHAR");
            if (appendCharacterBuild!=null)
                appendCharacterBuild=appendCharacter+appendCharacterBuild;
            else
            {
                appendCharacterBuild=appendCharacter;
            }
            //Row no 17 7.Enter values in all the required fields and click on "Sign Up" button. 1.The user should be redirected to "Find Your School" page.
            String methodName = new Exception().getStackTrace()[0].getMethodName();
            String firstName = methodName+"ins"+appendCharacter;
            String email = methodName+"ins"+appendCharacterBuild+"@snapwiz.com";
            driver.get(Config.baseURL);//land on home page
            new Click().clickBycssselector("span[class='btn btn-primary center-block as-goto-signup-button']");//click on signup! its free
            driver.findElement(By.id("first-name")).clear();//clear teacher 1st name
            driver.findElement(By.id("first-name")).sendKeys(firstName);//give teacher first name
            driver.findElement(By.id("user-email")).clear();
            driver.findElement(By.id("user-email")).sendKeys(email);//give teacher email
            driver.findElement(By.id("user-password")).clear();
            driver.findElement(By.id("user-password")).sendKeys("snapwiz");//give teacher password
            teacherSignUpPage.getButton_signUp().click();
            Assert.assertEquals(driver.findElement(By.cssSelector("div[class='as-errorMsg retype-password-message']")).getText(),"Please retype the password.","Error message is not appearing as expected");
            driver.findElement(By.id("retype-password")).sendKeys("snapwiz1");//retype the wrong password
            teacherSignUpPage.getButton_signUp().click();
            Assert.assertEquals(driver.findElement(By.cssSelector("div[class='as-errorMsg retype-password-message']")).getText(),"Retyped password do not match","Error message is not displayed as expected");
            driver.findElement(By.id("retype-password")).clear();
            driver.findElement(By.id("retype-password")).sendKeys("snapwiz");//retype the correct password
            teacherSignUpPage.getButton_signUp().click();
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[class='as-find-addSection pull-right']")));
            if(!driver.findElement(By.cssSelector("div[class='as-find-addSection pull-right']")).getText().contains("Could not find your school?"))
                Assert.fail("User not redirected to 'Find your school' page after signing up as instructor");

            // Row no 20 12.Log in with the previously registered credentials. 1.The user should be redirected to  "Find Your School" page.
            driver.quit();
            reInitDriver();
            new Login().loginAsInstructor(appendCharacter, 0);
            String url = driver.getCurrentUrl();

            if(!url.contains("dashboard")){
                Assert.fail("instructor is not navigated to dashboard");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase InstructorAbleToRegisterInApplication in test method instructorSignUpWithValidValues",e);
        }
    }

    @Test(priority = 4,enabled = true)
    public  void instructorLogin()
    {
        try
        {
            String appendCharacter = "a";
            new SignUp().teacher(appendCharacter, 0);
            driver.findElement(By.cssSelector("div[class='as-search-blue-btn as-search-next btn btn-blue pull-right m-t m-b']")).click();
            //Row no 23 2.Click on "Next Step" button without entering anything for the school name. 1.The validation message "You need to add a school in order to proceed." should be displayed.
            if(driver.findElements(By.cssSelector("div[class='as-errorMsg add-school-message']")).size() == 0)
                Assert.fail("The notification for error message is not shown when teacher tries to click Next Step button without entering school name in Find your school text box");
            //validating the text of the above notification shown
            if(!driver.findElement(By.cssSelector("div[class='as-errorMsg add-school-message']")).getText().equalsIgnoreCase("You need to add a school in order to proceed."))
                Assert.fail("The notification for error message shown when teacher tries to click Next Step button without entering school name in Find your school text box is incorrect");

            //Row no 24 3.Try enterying something inside the school name edit box. 1.The validation message "You need to add a school in order to proceed." should be hidden.
            new TextSend().textsendbycssSelector("a", "input[class='maininput maininput-center-placeholder']");
            Thread.sleep(1000);

            //Row no. 25 4.Enter less than 3 characters(1-2)in the school edit box. 1.The validation message "Enter at least 3 characters" should be displayed.
            if(new Size().getSizeofElement("class", "error-message") == 0)
                Assert.fail("The message 'Enter at least 3 characters' not shown when instructor enters less than 3 characters in Find your school textbox");
            if(!new TextFetch().textfetchbyclass("error-message").equals("Enter at least 3 characters"))
                Assert.fail("The message 'Enter at least 3 characters' not correct when instructor enters less than 3 characters in Find your school textbox");
            new TextSend().textsendbycssSelector("ab", "input[class='maininput maininput-center-placeholder']");
            if(!new TextFetch().textfetchbyclass("error-message").equals("Enter at least 3 characters"))
                Assert.fail("The message 'Enter at least 3 characters' not correct when instructor enters less than 3 characters in Find your school textbox");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase instructorLogin in class InstructorAbleToRegisterInApplication",e);
        }
    }

    @Test(priority = 5,enabled = true)
    public void addSchoolUI()
    {
        try {
            String appendCharacter = "a";
            new SignUp().teacher(appendCharacter, 0);
            //Row no 35 8.Click on "+add" button. 1.It takes the user to "Add a new school" page
            new Navigator().addNewSchoolTeacher();
            if(!new TextFetch().textfetchbycssselector("div[class='modal-header as-teacher-signup-label text-center']").equalsIgnoreCase("Add new school"))
                Assert.fail("Instructor not navigated to 'Add new school' page after clicking on +add button");


            //Row no 37 2.Click on "Back" link. 1.The user should be redirected back to "Find Your School" page.
            driver.findElement(By.xpath("//*[contains(@class,'as-close-popup')]")).click();
            if(!driver.findElement(By.cssSelector("div[class='as-find-addSection pull-right']")).getText().contains("Could not find your school?"))
                Assert.fail("User not redirected to 'Find your school' page after clicking browser back button on 'Add a new school' page");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.linkText("add"))); //clicking add button to again go back to the add new school page
            //Row no 38 2.Click on "Save" button without entering anything. 1.The validation message "Please provide a valid school name." should get dispalyed.
            driver.findElement(By.cssSelector("div[class='as-search-blue-btn as-add-save-btn btn btn-blue pull-right']")).click(); //Click on save button
            Thread.sleep(2000);
            if(!driver.findElement(By.cssSelector("div[class='as-errorMsg school-name-message']")).getText().equalsIgnoreCase("Please provide a valid school name."))
                Assert.fail("The validation message 'Please provide a valid school name' not shown after clicking on save button without entering anything in Add new school page");

            //Row no 39 3.Enter some school name and change the focus out side of it. 1.The validation message "Please provide a valid school name." should get disappeared.
            new TextSend().textsendbyid("Harvard","school-name");
            new Click().clickByid("address");
            if(driver.findElements(By.cssSelector("div[class='as-errorMsg school-name-message']")).size() != 0)
                Assert.fail("The validation message 'Please provide a valid school name' shown even after entering valid data in 'Name' field in Add new school page");

            //Row no 40 4.Verify the user is able to enter value in address,city,zip,state edit box. 1.The user should be able to enter value in address,city,zip,state edit box.4
            new TextSend().textsendbyid(new RandomString().randomstring(5),"address");
            new TextSend().textsendbyid(new RandomString().randomstring(5),"city-name");
            new TextSend().textsendbyid("560034","zip-code");
            new TextSend().textsendbyid(new RandomString().randomstring(5),"state-name");
            //as-add-new-school-dropDown as-add-country-drop-down
            Select dropdown = new Select(driver.findElement(By.xpath("//*[contains(@class, 'as-add-new-school-dropDown')]")));
            dropdown.selectByValue("US");
            dropdown.selectByValue("ZW");
            //Row no 41 5.Enter a wrong Zip code. 1.The validation message "Please provide a valid zip code." should be displayed.
            new TextSend().textsendbyid("560034&","zip-code");
            new Click().clickByid("state-name");
            if(driver.findElements(By.cssSelector("div[class='as-errorMsg zip-code-message']")).size() == 0)
                Assert.fail("Validation message for providing incorrect zip code not shown after entering an invalid zip code while instructor in creating a new school");

            if(!driver.findElement(By.cssSelector("div[class='as-errorMsg zip-code-message']")).getText().equalsIgnoreCase("Please provide a valid zip code."))
                Assert.fail("Validation message for providing incorrect zip code is not correct after entering an invalid zip code while instructor in creating a new school");

            //Row no. 42
            new TextSend().textsendbyid("560034","zip-code");
            new Click().clickByid("state-name");
            if(driver.findElements(By.cssSelector("div[class='as-errorMsg zip-code-message']")).size() != 0)
                Assert.fail("Validation message for providing incorrect zip code not shown after entering an invalid zip code while instructor in creating a new school");
            //TC row no. 43
            new Select(driver.findElement(By.xpath("//*[contains(@class, 'as-add-new-school-dropDown')]"))).selectByVisibleText("United States");//select a country


        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase addSchoolUI in class InstructorAbleToRegisterInApplication",e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void addSchool()
    {
        try
        {
            String appendCharacter = "a1";
            String appendCharacterBuild=System.getProperty("UCHAR");
            if (appendCharacterBuild!=null)
                appendCharacter=appendCharacter+appendCharacterBuild;

            String schoolName = "addSchoolHarvard"+appendCharacter;
            new SignUp().teacher(appendCharacter, 0);
            new Navigator().addNewSchoolTeacher(); //click on add school link
            //Row no 44 8.Enter all the values or only required values and click on "Save" button. 1.The user should be redirected to "Create Your class" page.
            new TextSend().textsendbyid(schoolName,"school-name");
            new TextSend().textsendbyid("addSchooladdress","address");
            new TextSend().textsendbyid("addSchoolcity","city-name");
            new TextSend().textsendbyid("560034","zip-code");
            new TextSend().textsendbyid("addSchoolstate","state-name");
            Select dropdown = new Select(driver.findElement(By.xpath("//*[contains(@class, 'as-add-new-school-dropDown')]")));
            dropdown.selectByValue("US");
            driver.findElement(By.cssSelector("div[class='as-search-blue-btn as-add-save-btn btn btn-blue pull-right']")).click();
            Thread.sleep(5000);
            if(!(new TextFetch().textFetchByXpath("//label[@class='control-label col-sm-3']").contains("Name of the class")))
                Assert.fail("Teacher not directed to 'Create your class' page after adding all the valid details in add school page");

            //Row no 45 2.The school should be created and saved in DB.
            ResultSet rst =  DBConnect.st.executeQuery("select name as instname from t_institution where name like '"+schoolName+"';");
            boolean instfound = false;
            while(rst.next())
            {
                if(rst.getString("instname").equals(schoolName))
                    instfound = true;
                if(instfound == true) break;

            }
            if(instfound == false) Assert.fail("The new school created by teacher in not present in the database inside t_instituion table");

            //Row no 46 9.Click on "Back" link. 1.The user should be redirected back to "Find Your School" page.
            new Navigator().createClassToFindSchoolTeacher();
            Thread.sleep(3000);
            //Row no 47 2.The newly created school should be added by default in school edit box.
            if(!driver.findElement(By.className("bit-box")).getText().equals(schoolName))
                Assert.fail("The newly created school is not added by default in school edit box when teacher created presses back button in 'Create your class' page after creating a new school from 'create school page''");

            //Row no 48 10.Remove the school and search for the newly added school.
            new Click().clickByclassname("closebutton"); //removing the by default present school
            new TextSend().textsendbyclass("560034", "maininput"); //adding first three characters to the school name
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[starts-with(@rel, 'inst_')]")).click(); //Selecting the school from the auto suggested list
            Thread.sleep(2000);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase addSchool in class InstructorAbleToRegisterInApplication",e);
        }
    }

}
