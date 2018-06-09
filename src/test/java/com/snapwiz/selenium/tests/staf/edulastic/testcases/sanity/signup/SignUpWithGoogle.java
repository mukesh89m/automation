package com.snapwiz.selenium.tests.staf.edulastic.testcases.sanity.signup;


import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.SignUp;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.MyAssessments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.classpage.ClassPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.manageClass.ManageClass;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.reports.MyReports;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.TeacherSignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Shashank on 10-12-2015.
 */
public class SignUpWithGoogle extends Driver{


    @Test(priority = 1, enabled = true)
    public void teacherAndStudentSignUpWithGoogle(){
        try{
            WebDriver driver=Driver.getWebDriver();
            ReportUtil.log("Description", "This test case validates the instructor sign up with google using new school and student sign up", "info");
            String appendChar = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String randNumber=new RandomString().randominteger(4);
            com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Edulastic.Login login=PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Edulastic.Login.class);
            String password = "snapwiz2015";
            String email = "snaplogicautomationteacher";
            String email_student = "snaplogic.automation22";
            SchoolPage schoolPage = PageFactory.initElements(driver,SchoolPage.class);
            TeacherSignUpPage teacherSignUpPage = PageFactory.initElements(driver,TeacherSignUpPage.class);
            ClassPage classPage = PageFactory.initElements(driver,ClassPage.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            MyAssessments myAssessments = PageFactory.initElements(driver,MyAssessments.class);
            ManageClass manageClass = PageFactory.initElements(driver,ManageClass.class);

            DBConnect.Connect();
            Config.readconfiguration();
            driver.get(Config.launchURL);//Open the application


            String currentMethodName = new Exception().getStackTrace()[0].getMethodName();
            DBConnect.Connect();
            DBConnect.st.executeUpdate("update t_user set email = '" + email + randNumber + "', username = '" + randNumber + "',open_id ='" + randNumber + "' where email = '" + email + "@gmail.com';");
            DBConnect.st.executeUpdate("update t_user set email = '" + email_student + randNumber + "', username = '" + randNumber + "',open_id ='" + randNumber + "' where email = '" + email_student + "@gmail.com';");
            DBConnect.st.executeUpdate("update t_user set email = '" + email + randNumber + "', username = '" + randNumber + "',open_id ='" + randNumber + "' where username = '" + email + "@gmail.com';");
            DBConnect.st.executeUpdate("update t_user set email = '" + email_student + randNumber + "', username = '" + randNumber + "',open_id ='" + randNumber + "' where username = '" + email_student + "@gmail.com';");


            new SignUp().googleSignUp(email,password);
            List<WebElement>popUp= driver.findElements(By.xpath("//a[text()='Sign up as Teacher']"));

            if(popUp.size() ==1){
                WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.xpath("//a[text()='Sign up as Teacher']")));

            }

            schoolPage.getButton_add().click();//Click on add button
            schoolPage.getButton_addSchool().click();//Click on 'Add school button'

            //Expected - Error should appear if school name and zip is not entered
            CustomAssert.assertEquals(schoolPage.getError_msg_schoolName().getText(),"Please provide a valid school name.","Check for error message on school name field","Error message is appearing correctly if school is not entered","Error message is not appearing correctly if school is not entered");
            CustomAssert.assertEquals(schoolPage.getError_msg_zip().getText(), "Please provide a valid zip code." , "Check for error message on zip code field" , "Error message is displayed correctly if zip is not entered" , "Error message is not displayed correctly if zip is not entered");
            schoolPage.getEditbox_schoolName().click();
            schoolPage.getEditbox_schoolName().clear();
            driver.switchTo().activeElement().sendKeys("School");//Enter the school name


            schoolPage.getClose().click();//Click on close button
            new School().enterAndSelectSchool("987654963",1,false);//enter zip code and select school
            schoolPage.getContinueButton().click();//Click on continue button

            //By default class name should appear on class page
            CustomAssert.assertEquals(classPage.nameOfClass.getAttribute("placeholder"), "Dharanesha Gowda's class" , "Verify default class name" , "Default class name is displayed as expected" , "Default class name is not displayed as expected");

            classPage.finishButton.click();//Click on finish button

            //Error message should appear only for standard
            CustomAssert.assertEquals(classPage.errorMsg_standard.getText(),"Standard is not selected","Verify error message on standard","Error message is displayed as expected on standard","Error message is not displayed as expected on standard");

            new Classes().createClass(appendChar, 1);//create class

            new Navigator().navigateTo("assignment");//Navigate to assignment

            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div.as-noData-msgWrapper")));
            //Expected - Assignment should not appear
            CustomAssert.assertTrue(assignments.message_noAssignment.getText().contains("No Assignments Available For Your Class"),"Verify default image of assignment","Assignments are not appearing for newly signed up instructor", "Assignments are appearing for newly signed up instructor");

            new Navigator().navigateTo("myAssessments");//Navigate to my assessments

            //No assessments should appear under published and draft
            CustomAssert.assertTrue(assignments.message_noAssignment.getText().contains("Assessment not available"),"Verify default image under published tab","Assessments are not appearing for newly signed up instructor under published tab","Assessments are appearing for newly signed up instructor under published tab");

            WebDriverUtil.clickOnElementUsingJavascript(myAssessments.draft);//Click on draft

            CustomAssert.assertTrue(assignments.message_noAssignment.getText().contains("Drafts not available"),"Verify default image under draft tab","Assessments are not appearing under draft for newly signed up instructor","Assessments are appearing under draft for newly signed up instructor");

            new Navigator().navigateTo("manageclass");//Navigate to manage class

            //Expected - Verify class name as created
            CustomAssert.assertEquals(manageClass.tab_activeAndArchived.get(0).getText(),"Active (2)","Verify Active tab at instructor side","Active tab is displayed as expected","Active tab is not displayed as expected");
            CustomAssert.assertEquals(manageClass.tab_activeAndArchived.get(1).getText(),"Archived (0)","Verify Archived tab at instructor side","Archive tab is displayed as expected","Archive tab is not displayed as expected");

            String classCodeInstructor = manageClass.getclassCode().get(0).getText();
            new Navigator().navigateTo("myReports");//Navigate to my reports

            MyReports myReports = PageFactory.initElements(driver,MyReports.class);
            //Verify the default reports and value of Standard mastered and assessed
            CustomAssert.assertTrue(myReports.defaultReport.getText().contains("Skill Report Not Available"),"Verify default skill report","Default skill report is displayed as expected","Default skill report is not displayed as expected");
            CustomAssert.assertEquals(myReports.standards.get(0).getText(),"0%","Verify the value of Standard Mastered","Standard mastered value is displayed as expected","Standard mastered value is not displayed as expected");
            CustomAssert.assertEquals(myReports.standards.get(2).getText(),"0%","Verify the value of Standard Assesssed","Standard Assesssed value is displayed as expected","Standard Assesssed value is not displayed as expected");

            new Navigator().logout();//log out

            //Student sign up using class code
            new com.snapwiz.selenium.tests.staf.edulastic.apphelpers.SignUp().studentDirectRegister(appendChar,classCodeInstructor,1);

            new Navigator().navigateTo("assignment");//Navigate to assignment page

            //Assignment should not appear
            CustomAssert.assertTrue(assignments.message_noAssignment.getText().contains("No Assignments Available For You"),"Verify the assignment at student side","Assignments are not appearing as expected","Assignments are appearing");
            new Navigator().logout();//log out



        }catch (Exception e){
            Assert.fail("Exception in testcase 'teacherAndStudentSignUpNewSchool' in class 'SignUp'",e);

        }

    }


}
