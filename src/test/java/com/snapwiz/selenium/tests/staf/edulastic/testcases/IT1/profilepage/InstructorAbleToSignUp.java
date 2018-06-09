package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.profilepage;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Google.SignIn;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login.LoginPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.classpage.ClassPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.InstructorDashboard;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.profile.Profile;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.AdminSignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.SignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.StudentSignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.TeacherSignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/*
 * Created by sumit on 25/11/14.
 */
public class InstructorAbleToSignUp extends Driver {

    @Test(priority = 1, enabled = true)
    public void instructorAbleToSignUp()
    {
        try
        {
            //TC row no. 2-5
            String appendCharacterBuild=System.getProperty("UCHAR");
            LoginPage loginPage= PageFactory.initElements(driver,LoginPage.class);
            SignUpPage signUpPage= PageFactory.initElements(driver,SignUpPage.class);
            StudentSignUpPage studentSignUpPage=PageFactory.initElements(driver,StudentSignUpPage.class);
            TeacherSignUpPage teacherSignUpPage=PageFactory.initElements(driver,TeacherSignUpPage.class);
            ClassPage classPage=PageFactory.initElements(driver,ClassPage.class);
            SchoolPage schoolPage=PageFactory.initElements(driver,SchoolPage.class);
            String  className = "Class name";
            String grade = ReadTestData.readDataByTagName("", "grade", "1");
            String subjectArea = ReadTestData.readDataByTagName("", "subjectArea", "1");
            String subject = ReadTestData.readDataByTagName("", "subject", "1");
            String firstname = ReadTestData.readDataByTagName("", "firstname", "1");
            String email = ReadTestData.readDataByTagName("", "email", "1")+appendCharacterBuild+"@gmail.com";
            String schoolName = ReadTestData.readDataByTagName("", "schoolName", "1")+appendCharacterBuild;
            String editedfirstname = ReadTestData.readDataByTagName("", "editedfirstname", "1");
            String editedlastname = ReadTestData.readDataByTagName("", "editedlastname", "1");
            String editedemail = ReadTestData.readDataByTagName("", "editedemail", "1")+appendCharacterBuild+"@gmail.com";
            driver.get(Config.baseURL);//land on home page

            loginPage.getButton_signUpItsFree().click();//Click on 'Sign Up its free'
            studentSignUpPage.getTextBox_name().clear();
            studentSignUpPage.getTextBox_name().sendKeys(firstname);
            studentSignUpPage.getTextBox_email().clear();
            studentSignUpPage.getTextBox_email().sendKeys(email);//give teacher email
            studentSignUpPage.getTextBox_password().clear();
            studentSignUpPage.getTextBox_password().sendKeys("snapwiz");
            studentSignUpPage.getTextBox_retypePassword().sendKeys("snapwiz");
            teacherSignUpPage.getButton_signUp().click();//click on sign up as teacher button
            new WebDriverWait(driver,60).until(ExpectedConditions.visibilityOf(schoolPage.getButton_add()));
            schoolPage.getButton_add().click();
            schoolPage.getEditbox_schoolName().sendKeys(schoolName);//Enter school name in school name field
            schoolPage.editBox_zipCode.sendKeys("14789");
            schoolPage.getButton_addSchool().click();//clicking on 'Add School' button
            classPage.nameOfClass.sendKeys(className);//Enter Class name
            Select selectGrade=new Select(classPage.selectGrade);
            Select selectSubjectArea=new Select(classPage.selectSubjectArea);
            Select selectSubject=new Select(classPage.selectSubject);
            selectGrade.selectByValue(grade);//Select a grade
            selectSubjectArea.selectByValue(subjectArea);//Select subject area
            selectSubject.selectByValue(subject);//select subject
            classPage.finishButton.click();

            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='as-search-blue-btn as-search-next btn btn-blue js-manageclass']")));
            new Click().clickBycssselector("a[class='as-search-blue-btn as-search-next btn btn-blue js-manageclass']");//Click on Manage class

            new Click().clickByclassname("navbar-username");//Click on user name
            new Click().clickByclassname("username");//Click on my profile

            String fName = driver.findElement(By.id("user-first-name")).getAttribute("value");
            Assert.assertEquals(fName, firstname, "First name is not displayed properly in edit profile page of teacher.");

            String eMail = driver.findElement(By.id("user-email")).getAttribute("value");
            Assert.assertEquals(eMail, email, "Email is not displayed properly in edit profile page of teacher.");

            String save = driver.findElement(By.id("save")).getAttribute("value");
            Assert.assertEquals(save, "Save", "Save button is not displayed in edit profile page of teacher.");

            new Click().clickBycssselector("span[class='as-profile-edit-icon pull-right']");//click on edit icon
            new Select(driver.findElement(By.id("register-teacher-title"))).selectByVisibleText("Mrs.");
            driver.findElement(By.id("user-first-name")).clear();
            new TextSend().textsendbyid(editedfirstname, "user-first-name");
            driver.findElement(By.id("user-last-name")).clear();
            new TextSend().textsendbyid(editedlastname, "user-last-name");
            driver.findElement(By.id("user-email")).clear();
            new TextSend().textsendbyid(editedemail, "user-email");
            new Click().clickByid("save"); //Save button

            String fName1 = driver.findElement(By.id("user-first-name")).getAttribute("value");
            Assert.assertEquals(fName1, editedfirstname, "Edited First name is not displayed properly in edit profile page of teacher.");

            String lName1 = driver.findElement(By.id("user-last-name")).getAttribute("value");
            Assert.assertEquals(lName1, editedlastname, "Edited Last name is not displayed properly in edit profile page of teacher.");

            String eMail1 = driver.findElement(By.id("user-email")).getAttribute("value");
            Assert.assertEquals(eMail1, editedemail, "Edited Email is not displayed properly in edit profile page of teacher.");
            new Navigator().navigateTo("manageclass");//Navigate to Manage class
            Thread.sleep(2000);
            String url = driver.getCurrentUrl();
            if(!url.contains("manageclass")){
                Assert.fail("After editing the profile Details is not updated and Manage Class page should be displayed");
            }
            new Click().clickByclassname("as-manage-class-visit");//click on Visit class
            Thread.sleep(2000);
            new Click().clickByclassname("navbar-username");//Click on user name
            new Click().clickByclassname("username");//Click on my profile

            String fName2 = driver.findElement(By.id("user-first-name")).getAttribute("value");
            Assert.assertEquals(fName2, editedfirstname, "First name is not displayed correctly in profile page for a teacher.");

            String lName2 = driver.findElement(By.id("user-last-name")).getAttribute("value");
            Assert.assertEquals(lName2, editedlastname, "Last name is not displayed correctly in profile page for a teacher.");

            String eMail2 = driver.findElement(By.id("user-email")).getAttribute("value");
            Assert.assertEquals(eMail2, editedemail, "Email is not displayed correctly in profile page for a teacher.");

            String img = driver.findElement(By.id("upload-user-profile")).getCssValue("background-image");
            if(!img.equals("none")){
                Assert.fail("In profile page the default profile pic is absent.");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase instructorAbleToSignUp in class InstructorAbleToSignUp.", e);

        }
    }

    @Test(priority = 2, enabled = true)
    public void instructorAbleToViewAndUpdateProfileDetails()
    {
        try
        {

            Profile profile = PageFactory.initElements(driver,Profile.class);

            String appendChar = "a";
            //TC row no. 6-11
            String academicinterest = ReadTestData.readDataByTagName("", "academicinterest", "6");
            String awards = ReadTestData.readDataByTagName("", "awards", "6");
            String societies = ReadTestData.readDataByTagName("", "societies", "6");
            String editedacademicinterest = ReadTestData.readDataByTagName("", "editedacademicinterest", "6");
            String editedawards = ReadTestData.readDataByTagName("", "editedawards", "6");
            String editedsocieties = ReadTestData.readDataByTagName("", "editedsocieties", "6");
            String editedfirstname = ReadTestData.readDataByTagName("", "editedfirstname", "6");
            String editedlastname = ReadTestData.readDataByTagName("", "editedlastname", "6");
            String editedemail = ReadTestData.readDataByTagName("", "editedemail", "6");

            new SignUp().teacher(appendChar, 6);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 6);//create school
            new Classes().createClass(appendChar, 6);//create class

            Thread.sleep(2000);
            profile.dropdown_username.click();//Click on user name
            profile.myProfile.click();//Click on my profile

            String fName2 = driver.findElement(By.id("user-first-name")).getAttribute("value");
            if(fName2.length() == 0){
                Assert.fail("First name is not displayed correctly in profile page for a teacher.");
            }

            String eMail2 = driver.findElement(By.id("user-email")).getAttribute("value");
            if(eMail2.length() == 0){
                Assert.fail("Email is not displayed correctly in profile page for a teacher.");
            }

            String img = driver.findElement(By.id("upload-user-profile")).getCssValue("background-image");
            if(!img.equals("none")) {
                Assert.fail("In profile page the default profile pic is absent.");
            }

            driver.findElement(By.cssSelector("span[class='as-profile-edit-icon pull-right']")).click();//Click on edit right side

            new Select(driver.findElement(By.id("register-teacher-title"))).selectByVisibleText("Mr.");
            new TextSend().textsendbyid(editedfirstname, "user-first-name");
            new TextSend().textsendbyid(editedlastname, "user-last-name");
            new TextSend().textsendbyid(editedemail, "user-email");

            new Click().clickByid("save");//click on Save

          /*  String message = new TextFetch().textfetchbyclass("as-notification-message");
            Assert.assertEquals(message, "Your changes have been saved successfully.", "Clicking on save button in profile page the confirmation message does not appear.");
*/
            String fName1 = driver.findElement(By.id("user-first-name")).getAttribute("value");
            Assert.assertEquals(fName1, editedfirstname, "Edited First name is not displayed correctly in profile page for a teacher.");

            String lName1 = driver.findElement(By.id("user-last-name")).getAttribute("value");
            Assert.assertEquals(lName1, editedlastname, "Edited Last name is not displayed correctly in profile page for a teacher.");

            String eMail1 = driver.findElement(By.id("user-email")).getAttribute("value");
            Assert.assertEquals(eMail1, editedemail, "Edited Email is not displayed correctly in profile page for a teacher.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase instructorAbleToViewAndUpdateProfileDetails in class InstructorAbleToSignUp.", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void instructorAbleToCancelTheEditedPortion()
    {
        try
        {
            Profile profile = PageFactory.initElements(driver,Profile.class);

            String appendChar = "a";
            //TC row no. 12
            String academicinterest = ReadTestData.readDataByTagName("", "academicinterest", "12");
            String awards = ReadTestData.readDataByTagName("", "awards", "12");
            String societies = ReadTestData.readDataByTagName("", "societies", "12");
            String editedacademicinterest = ReadTestData.readDataByTagName("", "editedacademicinterest", "12");
            String editedawards = ReadTestData.readDataByTagName("", "editedawards", "12");
            String editedsocieties = ReadTestData.readDataByTagName("", "editedsocieties", "12");
            String editedfirstname = ReadTestData.readDataByTagName("", "editedfirstname", "12");
            String editedlastname = ReadTestData.readDataByTagName("", "editedlastname", "12");
            String editedemail = ReadTestData.readDataByTagName("", "editedemail", "12");

            new SignUp().teacher(appendChar, 12);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 12);//create school
            new Classes().createClass(appendChar, 12);//create class

            Thread.sleep(2000);
            profile.dropdown_username.click();//Click on user name
            profile.myProfile.click();//Click on my profile

            driver.findElement(By.cssSelector("span[class='as-profile-edit-icon pull-right']")).click();//Click on edit right side

            new Select(driver.findElement(By.id("register-teacher-title"))).selectByVisibleText("Mr.");
            Thread.sleep(2000);

            new TextSend().textsendbyid(editedfirstname, "user-first-name");
            Thread.sleep(2000);

            new TextSend().textsendbyid(editedlastname, "user-last-name");
            new TextSend().textsendbyid(editedemail, "user-email");
            Thread.sleep(2000);

            new Click().clickByclassname("as-profile-cancel");//click on cancel

            String fName1 = driver.findElement(By.id("user-first-name")).getAttribute("value");
            if(fName1.equals(editedfirstname))
                Assert.fail("Edited First name is displayed in profile page for a teacher though the cancel button is clicked.");

            String lName1 = driver.findElement(By.id("user-last-name")).getAttribute("value");
            if(lName1.equals(editedlastname))
                Assert.fail("Edited Last name is displayed in profile page for a teacher though the cancel button is clicked.");

            String eMail1 = driver.findElement(By.id("user-email")).getAttribute("value");
            if(eMail1.equals(editedemail))
                Assert.fail("Edited Email is  displayed in profile page for a teacher though the cancel button is clicked.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase instructorAbleToCancelTheEditedPortion in class InstructorAbleToSignUp.", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void instructorAbleToChangePassword()
    {
        try
        {
            Profile profile = PageFactory.initElements(driver,Profile.class);

            //TC row no. 13-16
            String appendChar = "a3";
            String appendCharacterBuild=System.getProperty("UCHAR");
            if (appendCharacterBuild!=null)
                appendCharacterBuild=appendChar+appendCharacterBuild;
            else
            {
                appendCharacterBuild=appendChar;
            }
            new SignUp().teacher(appendChar, 13);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 13);//create school
            new Classes().createClass(appendChar, 13);//create class
            Thread.sleep(2000);
            profile.dropdown_username.click();//Click on user name
            profile.myProfile.click();//Click on my profile
            new Click().clickByclassname("as-profile-change-paswdLink");//click on change password link
            new TextSend().textsendbyid("snapwiz123", "user-password");//type password
            new TextSend().textsendbyid("snapwiz123", "user-confirm-password");//retype password
            Thread.sleep(1000);
            new Click().clickByid("save");//click on Save

            String message = new TextFetch().textfetchbyclass("as-notification-message");
            Assert.assertEquals(message, "Your changes have been saved successfully.", "On changing password and Clicking on save button in profile page the confirmation message does not appear.");
            new Navigator().logout();

            LoginPage loginPage=PageFactory.initElements(driver,LoginPage.class);
            String methodName = "instructorAbleToChangePassword";
            String email = methodName+"ins"+appendCharacterBuild+"@snapwiz.com";
            new TextSend().textsendbyid(email,"login-email");
            new TextSend().textsendbyid("snapwiz123","login-password");
            loginPage.getButton_signIn().click();
            Thread.sleep(2000);
            new Navigator().navigateTo("dashboard");

            String url = driver.getCurrentUrl();
            if(!url.contains("dashboard"))
                Assert.fail("Teacher is unable login with new password.");

            new Navigator().logout();

            new TextSend().textsendbyid(email,"login-email");
            new TextSend().textsendbyid("snapwiz","login-password");
            loginPage.getButton_signIn().click();
            String url1 = driver.getCurrentUrl();
            if(url1.contains("dashboard"))
                Assert.fail("Teacher is able login with old password.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase instructorAbleToChangePassword in class InstructorAbleToSignUp.", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void instructorAbleToChangeProfilePic()
    {
        try
        {

            Profile profile = PageFactory.initElements(driver,Profile.class);

            //TC row no. 17-19
            String appendChar = "a";
            String filename = ReadTestData.readDataByTagName("", "filename", "17");
            new SignUp().teacher(appendChar, 17);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 17);//create school
            new Classes().createClass(appendChar, 17);//create class

            Thread.sleep(2000);
            profile.dropdown_username.click();//Click on user name
            profile.myProfile.click();//Click on my profile
            Thread.sleep(1000);
            new Click().clickByXpath("//input[@type='file']");
            Thread.sleep(1000);
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(1000);
            //Insert Explicit wait here
            new ExplicitWait().explicitWaitById("start_queue",30);
            new Click().clickByid("start_queue");//click on upload button
            Thread.sleep(15000);
            String img = driver.findElement(By.id("as-user-profile-img")).getAttribute("src");
            if(img.contains("as-default.png"))
                Assert.fail("Instructor is unable to change the profile pic.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase instructorAbleToChangeProfilePic in class InstructorAbleToSignUp.", e);
        }
    }

}
