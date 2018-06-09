package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.profilepage;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login.LoginPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.StudentSignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextSend;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/*
 * Created by sumit on 26/11/14.
 */
public class StudentAbleToEditProfile extends Driver{

    @Test(priority = 1, enabled = true)
    public void studentAbleToEditProfile()
    {
        try
        {
            LoginPage loginPageFactory = PageFactory.initElements(driver, LoginPage.class);
            StudentSignUpPage studentSignUpPageSignUpPageFactory = PageFactory.initElements(driver, StudentSignUpPage.class);
            //TC row no. 20-22
            String appendCharacterBuild=System.getProperty("UCHAR");
            String firstname = ReadTestData.readDataByTagName("", "firstname", "20");
            String lastname = ReadTestData.readDataByTagName("", "lastname", "20");
            String email = ReadTestData.readDataByTagName("", "email", "20")+appendCharacterBuild;
            String favoritesubject = ReadTestData.readDataByTagName("", "favoritesubject", "20");
            String editedfirstname = ReadTestData.readDataByTagName("", "editedfirstname", "20");
            String editedlastname = ReadTestData.readDataByTagName("", "editedlastname", "20");
            String editedemail = ReadTestData.readDataByTagName("", "editedemail", "20");
            String editedfavoritesubject = ReadTestData.readDataByTagName("", "editedfavoritesubject", "20");

            String appendChar = "a";

            new SignUp().teacher(appendChar, 39);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 39);//log in as teacher
            new School().createWithOnlyName(appendChar, 39);//create school
            String classCode = new Classes().createClass(appendChar, 39);//create class
            new Navigator().logout();//log out

            driver.get(Config.baseURL);//land on home page
            loginPageFactory.studentLink().click();//click on student
            studentSignUpPageSignUpPageFactory.getTextBox_classCode().sendKeys(classCode);//enter class code
            driver.findElement(By.id("first-name")).clear();//clear student 1st name
            driver.findElement(By.id("first-name")).sendKeys(firstname);//give student first name
            driver.findElement(By.id("last-name")).clear();//clear student 1st name
            driver.findElement(By.id("last-name")).sendKeys(lastname);//give student first name
            driver.findElement(By.id("user-email")).clear();
            driver.findElement(By.id("user-email")).sendKeys(email);//give student email
            driver.findElement(By.id("user-password")).clear();
            driver.findElement(By.id("user-password")).sendKeys("snapwiz");//give student password
            new Click().clickByclassname("as-terms-condition-checkbox");
            driver.findElement(By.cssSelector("input[type='submit']")).click();//click on sign up button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[class='ls-user-nav__username']")));
            closeHelp();

            new Click().clickBycssselector("span[class='ls-user-nav__username']");//click on profile link on header

            List<WebElement> allEdit1 = driver.findElements(By.className("as-profile-edit-icon"));
            allEdit1.get(1).click();
            new TextSend().textsendbyid(favoritesubject, "user-academic-interest");//enter favorite subject
            new Click().clickByid("save");//click on Save

            String fName2 = driver.findElement(By.id("user-first-name")).getAttribute("value");
            Assert.assertEquals(fName2, firstname, "First name is not displayed correctly in profile page for a student.");

            String lName2 = driver.findElement(By.id("user-last-name")).getAttribute("value");
            Assert.assertEquals(lName2, lastname, "Last name is not displayed correctly in profile page for a student.");

            String eMail2 = driver.findElement(By.id("user-email")).getAttribute("value");
            Assert.assertEquals(eMail2, email, "Email is not displayed correctly in profile page for a student.");

            String passwd = new TextFetch().textfetchbyclass("as-profile-change-paswdLink");
            Assert.assertEquals(passwd, "Change password", "Change password link is not displayed correctly in profile page for a student.");


            String img = driver.findElement(By.id("as-user-profile-img")).getAttribute("src");
            if(!img.contains("as-default-profile.png"))
                Assert.fail("Default Profile pic of student is absent.");

            String desc = new TextFetch().textfetchbylistclass("as-profile-description", 1);
            Assert.assertEquals(desc, favoritesubject, "favorite subject is not displayed correctly in profile page for a student.");

            Thread.sleep(2000);
            List<WebElement> allEdit = driver.findElements(By.className("as-profile-edit-icon"));
            allEdit.get(0).click();//click on edit icon
            driver.findElement(By.id("user-first-name")).clear();
            driver.findElement(By.id("user-first-name")).sendKeys(editedfirstname);
            //new TextSend().textsendbyid("2122", "user-first-name");
            Thread.sleep(3000);
            new TextSend().textsendbyid(editedlastname, "user-last-name");
            Thread.sleep(3000);
            new TextSend().textsendbyid(editedemail, "user-email");
            allEdit.get(1).click();
            new TextSend().textsendbyid(editedfavoritesubject, "user-academic-interest");//edit favorite subject
            new Click().clickByid("save");//click on Save
            String message = new TextFetch().textfetchbyclass("as-notification-message");
            Assert.assertEquals(message, "Your changes have been saved successfully.", "Clicking on save button in profile page the confirmation message does not appear.");

            String fName1 = driver.findElement(By.id("user-first-name")).getAttribute("value");
            Assert.assertEquals(fName1, editedfirstname, "Edited First name is not displayed correctly in profile page for a student.");

            String lName1 = driver.findElement(By.id("user-last-name")).getAttribute("value");
            Assert.assertEquals(lName1, editedlastname, "Edited Last name is not displayed correctly in profile page for a student.");

            String eMail1 = driver.findElement(By.id("user-email")).getAttribute("value");
            Assert.assertEquals(eMail1, editedemail, "Edited Email is not displayed correctly in profile page for a student.");

            String fvrtsubject = new TextFetch().textfetchbylistclass("as-profile-description", 1);
            Assert.assertEquals(fvrtsubject, editedfavoritesubject, "Edited favorite subject is not displayed correctly in profile page for a student.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase studentAbleToEditProfile in class StudentAbleToEditProfile.", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void studentAbleToChangePassword()
    {
        try
        {
            String appendChar = "a";
            new SignUp().teacher(appendChar, 23);//Sign up as teacher
            new Login().loginAsInstructor(appendChar, 23);//log in as teacher
            new School().createWithOnlyName(appendChar, 23);//create school
            String classCode = new Classes().createClass(appendChar, 23);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, 23);//create student1
            new Click().clickBycssselector("span[class='ls-user-nav__username']");//click on profile link on header

            new Click().clickByclassname("as-profile-change-paswdLink");//click on change password link
            new TextSend().textsendbyid("snapwiz123", "user-password");//type password
            new TextSend().textsendbyid("snapwiz123", "user-confirm-password");//retype password
            new Click().clickByid("save");//click on Save

            String message = new TextFetch().textfetchbyclass("as-notification-message");
            Assert.assertEquals(message, "Your changes have been saved successfully.", "On changing password and Clicking on save button in profile page the confirmation message does not appear.");
            new Navigator().logout();
            String appendCharacterBuild=System.getProperty("UCHAR");
            String methodName = "studentAbleToChangePassword";
            String email = methodName+"st"+appendChar+appendCharacterBuild+"@snapwiz.com";
            new TextSend().textsendbyid(email,"login-email");
            new TextSend().textsendbyid("snapwiz123","login-password");
            driver.findElement(By.cssSelector("input[type='submit']")).click();
            Thread.sleep(2000);

            String url = driver.getCurrentUrl();
            if(!url.contains("dashboard"))
                Assert.fail("Student is unable login with new password.");
            new Navigator().logout();

            new Login().loginAsStudent(appendChar,23);//log in as Student
            String url1 = driver.getCurrentUrl();
            if(url1.contains("dashboard"))
                Assert.fail("Student is able login with old password.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase studentAbleToChangePassword in class StudentAbleToEditProfile.", e);
        }
    }

}
