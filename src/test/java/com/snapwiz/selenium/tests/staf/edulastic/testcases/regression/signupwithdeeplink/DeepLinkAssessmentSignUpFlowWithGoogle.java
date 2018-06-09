package com.snapwiz.selenium.tests.staf.edulastic.testcases.regression.signupwithdeeplink;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Edulastic.Login;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Google.SignIn;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login.LoginPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentLibrary;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assign;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.classpage.ClassPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.manageClass.ManageClass;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.DropDown;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextSend;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.logging.Level;

/**
 * Created by shashank on 17-07-2015.
 */
public class DeepLinkAssessmentSignUpFlowWithGoogle extends Driver{
    String baseUrl = "http://idc-dev1.snapwiz.net/";
    String password = "snapwiz2015";
    String emailStudent = "snaplogic.automation22";
    String email_teacher = "snaplogicautomationteacher";
    String email_teacher1 = "snaplogicautomationstudent1";

    @Test (priority = 1,enabled = true)
    public void deepLinkAssessmentSignupFlowWithGoogle()
    {
        int dataIndex=26;
        String appendChar1="13";
        logger.log(Level.INFO, "Random string  " + appendChar1);

        try {

            SignIn signIn=PageFactory.initElements(driver,SignIn.class);
            Login login = PageFactory.initElements(driver, Login.class);
            ManageClass manageClass=PageFactory.initElements(driver,ManageClass.class);
            String randNumber = new RandomString().randominteger(4);
            Assign assign=PageFactory.initElements(driver,Assign.class);
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
            manageClass=PageFactory.initElements(driver,ManageClass.class);
            ClassPage classPage = PageFactory.initElements(driver,ClassPage.class);

            new SignUp().googleSignIn_EdulasticFromSignUpWithUrl(email_teacher1, password,assessmentURl);//login with another teacher
            login = PageFactory.initElements(driver, Login.class);
            SignIn signInAfterQuit=PageFactory.initElements(driver,SignIn.class);
            if(signInAfterQuit.buttonAccept.size()>0)
                signInAfterQuit.buttonAccept.get(0).click();
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(login.getButton_Teacher()));
            login.getButton_Teacher().click();
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("add")));
            new School().createWithOnlyName(appendChar1,1);//Create a school
            //create class
            classPage.nameOfClass.sendKeys("Automation class");//Enter Class name
            new DropDown().selectValue("class", "as-add-grade-dropDown", "Grade 1");//Select a grade
            new DropDown().selectValue("class", "as-add-subjectArea-dropDown", "Mathematics");//Select subject area
            Thread.sleep(2000);
            new DropDown().selectValue("class", "as-add-subject-dropDown", "Math - Common Core");//select subject



            classPage.finishButton.click();// Click on Finish button
            Thread.sleep(2000);
            // new Click().clickBycssselector("div[class='as-search-blue-btn btn btn-blue as-add-save-btn']");//Finish button
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='as-search-blue-btn as-search-next btn btn-blue js-manageclass']")));
            new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(manageClass.showMeMyAssessment));
            manageClass.showMeMyAssessment.click();
            //check the name of assessment
            Assert.assertTrue( driver.findElement(By.cssSelector("div.as-card-header")).getText().contains(assessmentName),"Not open same assessment");


        //    Navigate to assessment
            new Navigator().logout();
            driver.close();
            reInitDriver();
            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            signIn=PageFactory.initElements(driver,SignIn.class);
            login = PageFactory.initElements(driver, Login.class);
            driver.get(assessmentURl);
            new Navigator().studentSignUp();//Navigate to student sign up page
            loginPage.signUpWithGoogle.click();//Click on button 'With Google'
            Thread.sleep(2000);
            if(driver.findElements(By.id("account-chooser-add-account")).size()!=0){
                driver.findElement(By.id("account-chooser-add-account")).click();
                Thread.sleep(5000);
            }
            signIn.getTextField_email().sendKeys(emailStudent);// Type Email
            signIn.buttonNext.get(0).click();
            signIn.getTextField_password().sendKeys(password);//Type Password
            signIn.getButton_SignIn().click();// Click button 'Sign in'
            //login with student
            if(signIn.buttonAccept.size()>0)
                signIn.buttonAccept.get(0).click();
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span#cntdwn-assignment-timer")));
        }
        catch (Exception e)
        {
           Assert.fail("Exception in testcase registrationUI in class StudentRegisterFromRegPage", e);
        }
    }
    @Test (priority = 2,enabled = true)
    public void deepLinkAssessmentSignUpFlowWithGoogleUseExistingSchool()
    {
        int dataIndex=126;
        String appendChar1="12";
        logger.log(Level.INFO, "Random string  " + appendChar1);

        try {
            SchoolPage schoolPage=PageFactory.initElements(driver,SchoolPage.class);
            SignIn signIn=PageFactory.initElements(driver,SignIn.class);
            Login login = PageFactory.initElements(driver, Login.class);
            ManageClass manageClass=PageFactory.initElements(driver,ManageClass.class);
            String randNumber = new RandomString().randominteger(4);
            Assign assign=PageFactory.initElements(driver,Assign.class);
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
            String zipcode=new School().createWithOnlyName(appendChar1,1);//Create a school
            String classscode=new Classes().createClass(appendChar1, 1);//create class
            new Assignment().create(dataIndex,"truefalse");//create assessment
            new Assignment().assignToStudent(dataIndex,appendChar1);//assign assessment
            Thread.sleep(3000);
            String assessmentURl=assign.getAssessmentShareLink().getText();//fetch url from UI
            System.out.println(assessmentURl);
            new Navigator().logout();
            driver.close();
            reInitDriver();
            schoolPage=PageFactory.initElements(driver,SchoolPage.class);
            manageClass=PageFactory.initElements(driver,ManageClass.class);
            new SignUp().googleSignIn_EdulasticFromSignUpWithUrl(email_teacher1, password,assessmentURl);//login with another teacher
            login = PageFactory.initElements(driver, Login.class);
            SignIn signInAfterQuit=PageFactory.initElements(driver,SignIn.class);
            if(signInAfterQuit.buttonAccept.size()>0)
                signInAfterQuit.buttonAccept.get(0).click();
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(login.getButton_Teacher()));
            login.getButton_Teacher().click();
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("add")));
            new School().enterAndSelectSchool(zipcode, 1, false);//Create a school
            Thread.sleep(3000);
            schoolPage.getContinueButton().click(); //clicking on save button
            //create class
            new TextSend().textsendbyid("Automation class", "class-name");//Enter Class name
            new DropDown().selectValue("class", "as-add-grade-dropDown", "Grade 1");//Select a grade
            new DropDown().selectValue("class", "as-add-subjectArea-dropDown", "Mathematics");//Select subject area
            Thread.sleep(2000);
            new DropDown().selectValue("class", "as-add-subject-dropDown", "Math - Common Core");//select subject



            new Click().clickBycssselector("div[class='as-search-blue-btn btn btn-blue as-add-save-btn']");//Finish button
            Thread.sleep(2000);
            // new Click().clickBycssselector("div[class='as-search-blue-btn btn btn-blue as-add-save-btn']");//Finish button
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='as-search-blue-btn as-search-next btn btn-blue js-manageclass']")));
            new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(manageClass.showMeMyAssessment));
            manageClass.showMeMyAssessment.click();
            //check the name of assessment
            Assert.assertTrue(driver.findElement(By.cssSelector("div.as-card-header")).getText().contains(assessmentName),"Not open same assessment");


            //    Navigate to assessment
            new Navigator().logout();
            driver.close();
            reInitDriver();
            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            signIn=PageFactory.initElements(driver,SignIn.class);
            login = PageFactory.initElements(driver, Login.class);
            driver.get(assessmentURl);
            new Navigator().studentSignUp();//Navigate to student sign up page
            loginPage.signUpWithGoogle.click();//Click on button 'With Google'
            Thread.sleep(2000);
            if(driver.findElements(By.id("account-chooser-add-account")).size()!=0){
                driver.findElement(By.id("account-chooser-add-account")).click();
                Thread.sleep(5000);
            }
            signIn.getTextField_email().sendKeys(emailStudent);// Type Email
            signIn.buttonNext.get(0).click();
            signIn.getTextField_password().sendKeys(password);//Type Password
            signIn.getButton_SignIn().click();// Click button 'Sign in'
            //login with student
            if(signIn.buttonAccept.size()>0)
                signIn.buttonAccept.get(0).click();

            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span#cntdwn-assignment-timer")));

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase registrationUI in class StudentRegisterFromRegPage", e);
        }
    }













    @Test (priority = 3,enabled = true)
    public void deepLinkAssessmentSignupFlowWithGoogleSharedAtDistrictLevel()
    {
        int dataIndex=127;
        String appendChar1="132";
        String appendChar2="142";
        logger.log(Level.INFO, "Random string1  " + appendChar1);
        logger.log(Level.INFO, "Random string2  " + appendChar2);

        try {

            SignIn signIn=PageFactory.initElements(driver,SignIn.class);
            Login login = PageFactory.initElements(driver, Login.class);
            ManageClass manageClass=PageFactory.initElements(driver,ManageClass.class);
            String randNumber = new RandomString().randominteger(4);
            Assign assign=PageFactory.initElements(driver,Assign.class);
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
            AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
            manageClass=PageFactory.initElements(driver,ManageClass.class);
            ClassPage classPage = PageFactory.initElements(driver,ClassPage.class);

            new SignUp().googleSignIn_EdulasticFromSignUpWithUrl(email_teacher1, password,assessmentURl);//login with another teacher
            login = PageFactory.initElements(driver, Login.class);
            SignIn signInAfterQuit=PageFactory.initElements(driver,SignIn.class);
            if(signInAfterQuit.buttonAccept.size()>0)
                signInAfterQuit.buttonAccept.get(0).click();
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(login.getButton_Teacher()));
            login.getButton_Teacher().click();
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.linkText("add")));
            new School().createWithOnlyName(appendChar2,1);//Create a school
            //create class
            classPage.nameOfClass.sendKeys("Automation class");//Enter Class name
            new DropDown().selectValue("class", "as-add-grade-dropDown", "Grade 1");//Select a grade
            new DropDown().selectValue("class", "as-add-subjectArea-dropDown", "Mathematics");//Select subject area
            Thread.sleep(2000);
            new DropDown().selectValue("class", "as-add-subject-dropDown", "Math - Common Core");//select subject



            classPage.finishButton.click();// Click on Finish button
            Thread.sleep(2000);
            // new Click().clickBycssselector("div[class='as-search-blue-btn btn btn-blue as-add-save-btn']");//Finish button
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='as-search-blue-btn as-search-next btn btn-blue js-manageclass']")));
            new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOf(manageClass.showMeMyAssessment));
            manageClass.showMeMyAssessment.click();
            //check the name of assessment
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("toast-message")));
            Assert.assertEquals(assessmentLibrary.errorMessage.getText(), "You are not authorized to view this assessment.", "error message not matches");

            //    Navigate to assessment
            new Navigator().logout();
            driver.close();
            reInitDriver();
            assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            signIn=PageFactory.initElements(driver,SignIn.class);
            login = PageFactory.initElements(driver, Login.class);
            driver.get(assessmentURl);
            new Navigator().studentSignUp();//Navigate to student sign up page
            loginPage.signUpWithGoogle.click();//Click on button 'With Google'
            Thread.sleep(2000);
            if(driver.findElements(By.id("account-chooser-add-account")).size()!=0){
                driver.findElement(By.id("account-chooser-add-account")).click();
                Thread.sleep(5000);
            }
            signIn.getTextField_email().sendKeys(emailStudent);// Type Email
            signIn.buttonNext.get(0).click();
            signIn.getTextField_password().sendKeys(password);//Type Password
            signIn.getButton_SignIn().click();// Click button 'Sign in'
            //login with student
            if(signIn.buttonAccept.size()>0)
                signIn.buttonAccept.get(0).click();
            //Assessment should not appear and error message should appear
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span#cntdwn-assignment-timer")));
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase registrationUI in class StudentRegisterFromRegPage", e);
        }
    }

}
