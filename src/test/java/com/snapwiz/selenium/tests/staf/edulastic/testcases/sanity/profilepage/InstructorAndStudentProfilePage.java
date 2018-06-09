package com.snapwiz.selenium.tests.staf.edulastic.testcases.sanity.profilepage;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Google.SignIn;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login.LoginPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.InstructorDashboard;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.StudentDashboard;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.manageClass.ManageClass;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.profile.Profile;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.SignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.ExplicitWait;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.KeysSend;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextSend;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.*;
import gherkin.lexer.Th;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by pragyas on 19-02-2016.
 */
public class InstructorAndStudentProfilePage extends Driver{

    String email_student = "snaplogicautomationstudent";
    String password = "snapwiz2015";


    Profile profile;
    InstructorDashboard instructorDashboard;
    ManageClass manageClass;
    SchoolPage schoolPage;

    @BeforeMethod
    public void pageInit(){
        WebDriver driver=Driver.getWebDriver();
        profile = PageFactory.initElements(driver,Profile.class);
        instructorDashboard = PageFactory.initElements(driver,InstructorDashboard.class);
        manageClass = PageFactory.initElements(driver,ManageClass.class);
        schoolPage = PageFactory.initElements(driver, SchoolPage.class);


    }

    @Test(priority = 1,enabled = false)
    public void profilePageVerification(){
        WebDriver driver=Driver.getWebDriver();
        try{
            ReportUtil.log("Description","This test case validates profile page information at both instructor and Student side","info");

            int dataIndex = 17;
            String appendChar = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            //String appendChar ="aIDM";
            String appendCharacterBuild= "";
            if (System.getProperty("UCHAR")!=null)
                appendCharacterBuild=System.getProperty("UCHAR");

            String methodName = new Exception().getStackTrace()[0].getMethodName();
            String insEmail = methodName+"ins"+appendChar+appendCharacterBuild+"@snapwiz.com";
            String insEditedFirstName = methodName+"ins"+appendChar+appendCharacterBuild+"EditedName";
            String insEditedEmail = methodName+"ins"+appendChar+appendCharacterBuild+"EditedEmail"+"@snapwiz.com";

            String stEmail = methodName+"st"+appendChar+appendCharacterBuild+"@snapwiz.com";
            String stEditedFirstName = methodName+"st"+appendChar+appendCharacterBuild+"EditedName";
            String stEditedEmail = methodName+"st"+appendChar+appendCharacterBuild+"EditedEmail"+"@snapwiz.com";

            StudentDashboard studentDashboard = PageFactory.initElements(driver,StudentDashboard.class);
            LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);

            new SignUp().teacher(appendChar, dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("987654963", dataIndex, false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            System.out.println("classCode :"+classCode);
            new Assignment().create(dataIndex,"truefalse");//Create an assignment
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign to student

            //new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor

            editProfileAndUploadImage(insEditedFirstName, "InsLastName", "null");//Edit the instructor profile(only first and last name) and upload profile image
            changePassword("snapwiz123");//Change the instructor password

            //Verify the changed instructor information on dashboard and profile image on profile page
            //Verify the uploaded image
            WebDriverUtil.waitForAjax(driver,60);
            WebDriverUtil.waitTillVisibilityOfElement(profile.profileImage,120);
            CustomAssert.assertTrue(profile.profileImage.getAttribute("src").contains("img"),"Verify the uploaded image","Uploaded image is displayed","Uploaded image is not displayed");

            new Navigator().navigateTo("dashboard");//Navigate to dashboard page
            CustomAssert.assertEquals(studentDashboard.userName.getText(),insEditedFirstName+" InsLastName","Verify the user name on dashboard dropdown","User name is  displayed as expected","USer name is not displayed as expected");

            editProfileAndUploadImage(insEditedFirstName, "InsLastName", insEditedEmail);//Edit the email of the instructor

            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",profile.buttonSaveList.get(1));//Click on pop up save button

            //Instructor should be logged out and navigated to login page
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.id("login-email")));
            String loginUrl = driver.getCurrentUrl();

            if(!loginUrl.contains("login"))
            {
                Assert.fail("Instructor is not logged out after changing the mailid on profile page at instructor side");
            }

            //Instructor should not be able to log in with old mail id
            loginPage.getTextBox_username().sendKeys(insEmail);//Enter previous mail id
            loginPage.getTextBox_login_password().sendKeys("snapwiz");//Enter password
            loginPage.getButton_signIn().click();//Click on sign in button

            //Expected - Error message should appear
            CustomAssert.assertEquals(loginPage.getErrorMsg().getText(),"You have entered an invalid email/username or password.","Verify the error message when email and password does not matches","Error message is appearing as expected","Error message is not appearing as expected ");

            //Instructor should be able to log in with changed mail id and password
            new  Login().directLoginAsInstructor(dataIndex,insEditedEmail,"snapwiz123");
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student

            //Verify the changed instructor name on assignment at student side
            CustomAssert.assertTrue(studentDashboard.list_instructorName.get(0).getText().contains(insEditedFirstName+" InsLastName"),"Verify instructor name","Instructor name is displayed as expected","Instructor name is not displayed as expected");

            editProfileAndUploadImage(stEditedFirstName, "StLastName", "null");//Edit only first name and last name and upload profile image
            changePassword("snapwiz1234");//Change password

            //Verify the changed student information on dashboard and profile image on profile page
            //Verify the uploaded image
            WebDriverUtil.waitForAjax(driver,60);
            WebDriverUtil.waitTillVisibilityOfElement(profile.profileImage,120);
            CustomAssert.assertTrue(profile.profileImage.getAttribute("src").contains("img"),"Verify the uploaded image","Uploaded image is displayed","Uploaded image is not displayed");

            new Navigator().navigateTo("dashboard");//Navigate to dashboard page
            CustomAssert.assertEquals(studentDashboard.userName.getText(),stEditedFirstName+" StLastName","Verify the user name on dashboard dropdown","User name is  displayed as expected","USer name is not displayed as expected");

            editProfileAndUploadImage(stEditedFirstName, "StLastName", stEditedEmail);//Edit the email of the student

            //Student should not be logged out and navigated to login page
            String profilePageURL = driver.getCurrentUrl();

            if(!profilePageURL.contains("login")) {
                Assert.fail("Student is logged out after changing the information on profile page at student side");
            }
            new Navigator().logout();//log out

            //Student should not be able to log in with new(edited)mail id and new password
            loginPage.getTextBox_username().sendKeys(stEditedEmail);//Enter previous mail id
            loginPage.getTextBox_login_password().sendKeys("snapwiz1234");//Enter password
            loginPage.getButton_signIn().click();//Click on sign in button

            //Expected - Error message should appear
            CustomAssert.assertEquals(loginPage.getErrorMsg().getText(),"You have entered an invalid email/username or password.","Verify the error message when email and password does not matches","Error message is appearing as expected","Error message is not appearing as expected ");

            //Student should be able to log in with old mail id and new password
            new  Login().directLoginAsInstructor(dataIndex,stEmail,"snapwiz1234");

            profile.dropdown_username.click();
            profile.myProfile.click();//Click on my profile
            //Verify the edited information on profile page
            CustomAssert.assertEquals(profile.firstName_user.getAttribute("value"),stEditedFirstName,"Verify the edited first name of the student","Edited student first name is displayed as expected","Edited student first name is not displayed as expected");
            CustomAssert.assertEquals(profile.lastName_user.getAttribute("value"),"StLastName","Verify the edited last name of the student","Edited student last name is displayed as expected","Edited student last name is not displayed as expected");
            CustomAssert.assertEquals(profile.email.getAttribute("value"),stEditedEmail,"Verify the edited email of the student","Edited student email is displayed as expected","Edited student email is not displayed as expected");

            new Navigator().logout();//log out

            //Login as existing instructor with edited mail and password
            new  Login().directLoginAsInstructor(dataIndex,insEditedEmail,"snapwiz123");
            editProfileAndUploadImage(insEditedFirstName, "InsLastName", "null");//Edit the instructor profile(only first and last name) and upload profile image
            changePassword("snapwiz123");//Change the instructor password
            WebDriverUtil.waitForAjax(driver,60);
            WebDriverUtil.waitTillVisibilityOfElement(profile.profileImage,120);
            CustomAssert.assertTrue(profile.profileImage.getAttribute("src").contains("img"),"Verify the uploaded image","Uploaded image is displayed","Uploaded image is not displayed");

            new Navigator().navigateTo("dashboard");//Navigate to dashboard page
            CustomAssert.assertEquals(studentDashboard.userName.getText(),insEditedFirstName+" InsLastName","Verify the user name on dashboard dropdown","User name is  displayed as expected","USer name is not displayed as expected");

            editProfileAndUploadImage(insEditedFirstName, "InsLastName", insEditedEmail+"i");//Edit the email of the instructor
            Thread.sleep(5000);
            WebDriverUtil.waitTillVisibilityOfElement(profile.buttonSaveList.get(1),60);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",profile.buttonSaveList.get(1));//Click on pop up save button

            //Instructor should be logged out and navigated to login page
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.id("login-email")));
            String loginUrl1 = driver.getCurrentUrl();

            if(!loginUrl1.contains("login"))
            {
                Assert.fail("Instructor is not logged out after changing the mailid on profile page at instructor side");
            }

            //Instructor should not be able to log in with old mail id
            loginPage.getTextBox_username().sendKeys(insEditedEmail+"i");//Enter previous mail id
            loginPage.getTextBox_login_password().sendKeys("snapwiz");//Enter password
            loginPage.getButton_signIn().click();//Click on sign in button
            //Expected - Error message should appear
            CustomAssert.assertEquals(loginPage.getErrorMsg().getText(),"You have entered an invalid email/username or password.","Verify the error message when email and password does not matches","Error message is appearing as expected","Error message is not appearing as expected ");

        }catch (Exception e) {
            Assert.fail("Exception in testcase 'profilePageVerification' in class 'InstructorAndStudentProfilePage'", e);
        }

    }


    @Test(priority = 2,enabled = false)
    public void profilePageVerificationForDA(){
        WebDriver driver=Driver.getWebDriver();
        try{
            ReportUtil.log("Description","This test case validates profile page information at both instructor and Student side","info");

            int dataIndex = 18;
            String appendChar = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String email = "ryanda@snapwiz.com";
            String appendCharacterBuild= "";
            if (System.getProperty("UCHAR")!=null)
                appendCharacterBuild=System.getProperty("UCHAR");


            String methodName = new Exception().getStackTrace()[0].getMethodName();
            String insEditedFirstName = methodName+"ins"+appendChar+appendCharacterBuild+"EditedName";
            String insEditedEmail = methodName+"ins"+appendChar+appendCharacterBuild+"EditedEmail"+"@snapwiz.com";

            StudentDashboard studentDashboard = PageFactory.initElements(driver,StudentDashboard.class);
            LoginPage loginPage = PageFactory.initElements(driver,LoginPage.class);

            new Login().loginAsDA(dataIndex, email); //login as District Admin
            editProfileAndUploadImage(insEditedFirstName, "InsLastName", "null");//Edit the instructor profile(only first and last name) and upload profile image
            changePassword("snapwiz123");//Change the instructor password

            //Verify the changed instructor information on dashboard and profile image on profile page
            //Verify the uploaded image
            WebDriverUtil.waitForAjax(driver,60);
            WebDriverUtil.waitTillVisibilityOfElement(profile.profileImage,120);
            CustomAssert.assertTrue(profile.profileImage.getAttribute("src").contains("img"),"Verify the uploaded image","Uploaded image is displayed","Uploaded image is not displayed");

            new Navigator().navigateTo("dashboard");//Navigate to dashboard page
            CustomAssert.assertEquals(studentDashboard.userName.getText(),insEditedFirstName+" InsLastName","Verify the user name on dashboard dropdown","User name is  displayed as expected","USer name is not displayed as expected");

            editProfileAndUploadImage(insEditedFirstName, "InsLastName", insEditedEmail);//Edit the email of the instructor

            //Instructor should be logged out and navigated to login page
            new Navigator().logout();
            //Instructor should not be able to log in with old mail id
            loginPage.getTextBox_username().sendKeys(email);//Enter previous mail id
            loginPage.getTextBox_login_password().sendKeys("snapwiz");//Enter password
            loginPage.getButton_signIn().click();//Click on sign in button

            //Expected - Error message should appear
            CustomAssert.assertEquals(loginPage.getErrorMsg().getText(),"You have entered an invalid email/username or password.","Verify the error message when email and password does not matches","Error message is appearing as expected","Error message is not appearing as expected ");

            //Instructor should be able to log in with changed mail id and password
            new  Login().directLoginAsInstructor(dataIndex,"ryanda@snapwiz.com","snapwiz123");
            profile.dropdown_username.click();//Click on user name
            profile.myProfile.click();//Click on my profile
            profile.editProfile.click();//Click on edit profile page
            profile.firstName_user.clear();
            profile.firstName_user.sendKeys("Ryan");//Enter new first name
            profile.lastName_user.clear();
            profile.lastName_user.sendKeys("DA");//Enter new last name of the teacher
            loginPage.userlogin.clear();
            loginPage.userlogin.sendKeys("ryanda@snapwiz.com");//Enter previous mail id
            changePassword("snapwiz");//Change the instructor password
            Thread.sleep(4000);
            new Navigator().logout();//log out


        }catch (Exception e) {
            Assert.fail("Exception in testcase 'profilePageVerification' in class 'InstructorAndStudentProfilePage'", e);
        }

    }



    public void changePassword(String newPassword)
    {
        try{
            profile.changePassword.click();//click on change password
            Thread.sleep(2000);
            profile.textBox_newPassword.clear();
            profile.textBox_newPassword.sendKeys(newPassword);//enter new password
            profile.textBox_confirmPassword.sendKeys(newPassword);//Retype the password
            profile.buttonSave.click();//click on save button

        }catch (Exception e){
            Assert.fail("Exception while changing the password", e);

        }
    }


    public void editProfileAndUploadImage(String firstName,String lastName,String email)
    {
        WebDriver driver=Driver.getWebDriver();
        try{
            profile.dropdown_username.click();//Click on user name
            profile.myProfile.click();//Click on my profile
            Thread.sleep(5000);
            profile.editProfile.click();//Click on edit profile page
            if(email.equals("null"))
            {
                System.out.println("inside if");
                profile.firstName_user.clear();
                profile.firstName_user.sendKeys(firstName);//Enter new first name
                profile.lastName_user.clear();
                profile.lastName_user.sendKeys(lastName);//Enter new last name of the teacher
                Thread.sleep(4000);
                //Upload a profile image
                Actions action=new Actions(driver);
                action.moveToElement(profile.uploadProfile).click().build().perform();
                Thread.sleep(6000);
                WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.id("pickfiles")), 60);
                driver.findElement(By.xpath("//a[.='Select files']")).click();
                System.out.println(Config.fileUploadPath);
                Thread.sleep(3000);
                if( Properties.getPropertyValue("Browser").equals("CHROME")) {
                    new KeysSend().sendKeyBoardKeys("!");
                }
                new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + "img.png" + "^");
                Thread.sleep(3000);
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();",profile.uploadNow_uploadPopUp);
                new WebDriverWait(driver,300).until(ExpectedConditions.invisibilityOfElementLocated(By.id("widget-createimage_start_queue")));
                profile.buttonSave.click();//Click on save button
                Thread.sleep(2000);

            }else {
                System.out.println("inside else");
                profile.email.clear();
                profile.email.sendKeys(email);//Enter new email of the teacher
                WebDriverUtil.clickOnElementUsingJavascript(profile.buttonSave);//Click on save button
            }

        }catch (Exception e)
        {

        }

    }

    @Test(priority = 3,enabled = true)
    public void googleStudentProfileVerification()
    {
        WebDriver driver=Driver.getWebDriver();
        try{
            ReportUtil.log("Description","This test case validates that email field should not be editable for google student","info");
            String appendChar = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String randNumber=new RandomString().randominteger(4);
            System.out.printf("random :"+randNumber);

            //Execute sql query to remove user from database
            DBConnect.Connect();
            DBConnect.st.executeUpdate("update t_user set email = '" + email_student + randNumber + "', username = '" + randNumber + "',open_id ='" + randNumber + "' where email = '" + email_student + "@gmail.com';");

            SignIn signIn = PageFactory.initElements(driver, SignIn.class);
            SignUpPage signUpPage=PageFactory.initElements(driver,SignUpPage.class);
            com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Edulastic.Login login = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Edulastic.Login.class);

            new SignUp().teacher(appendChar, 23);//Sign up as teacher
            new School().enterAndSelectSchool("987654963", 23, false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String code = new Classes().createClass(appendChar, 23);//create class
            System.out.println("classcode "+code);
            new Navigator().logout();
            Thread.sleep(2000);

            //driver.get(Config.launchURL+"/regcd/"+code);
            new ExplicitWait().explicitWaitByCssSelector("span[class='btn btn-primary center-block as-goto-signup-button']");
            driver.findElement(By.cssSelector("span[class='btn btn-primary center-block as-goto-signup-button']")).click();
            new ExplicitWait().explicitWaitbyxpath("//span[contains(@class,'as-show-teacher-signup as-signup-option')]");
            driver.findElement(By.xpath("//span[contains(@class,'as-show-student-signup as-signup-option')]")).click();
            new TextSend().textsendbyid(code,"class-code"); //class code
            signUpPage.signUpWithGoogle.click();//Click on button 'With Google'
            Thread.sleep(4000);
            if(driver.findElements(By.id("submit_approve_access")).size()>0)
            {
                driver.findElement(By.id("submit_approve_access")).click();
            }
            Thread.sleep(2000);
            if(driver.findElements(By.id("account-chooser-add-account")).size()!=0){
                driver.findElement(By.id("account-chooser-add-account")).click();
                new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("Email")));
            }
            signIn.getTextField_email().sendKeys(email_student);// Type Email
            if(signIn.buttonNext.size()>0)
            {
                signIn.buttonNext.get(0).click();

            }
            signIn.getTextField_password().sendKeys(password);//Type Password
            signIn.getButton_SignIn().click();// Click button 'Sign in'
            if(driver.findElements(By.id("submit_approve_access")).size()>0)
            {
                driver.findElement(By.id("submit_approve_access")).click();
            }

            List<WebElement> popUp= driver.findElements(By.xpath("//a[text()='Sign up as Student']"));

            if(popUp.size() ==1){
                WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.xpath("//a[text()='Sign up as Student']")));
                new TextSend().textsendbyid(code,"class-code"); //class code
                signUpPage.signUpWithGoogle.click();//Click on button 'With Google'

            }


            //driver.findElement(By.xpath("//a[text()='Sign up as Student']")).click();
            new WebDriverWait(driver,400).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("user-name")));
            ReportUtil.log("Student login with google","Student login is successfully completed","pass");
            profile.dropdown_username.click();//Click on user name dropdown
            profile.myProfile.click();//click on my profile
            profile.editProfile.click();//Click on edit profile

            //Expected - Email field should be disabled
            CustomAssert.assertEquals(profile.email.getAttribute("class"),"as-userProfile-input disabled is-open-id-user","Verify email field","Email field is disabled for google student","Email field is not disabled for google student");

        }catch (Exception e)
        {
            Assert.fail("Exception in testcase 'googleStudentProfileVerification' in class 'InstructorAndStudentProfilePage'", e);

        }
    }



}
