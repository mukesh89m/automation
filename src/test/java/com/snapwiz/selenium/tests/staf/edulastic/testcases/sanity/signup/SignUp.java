package com.snapwiz.selenium.tests.staf.edulastic.testcases.sanity.signup;

import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Classes;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Navigator;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.School;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.MyAssessments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.classpage.ClassPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.manageClass.ManageClass;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.reports.MyReports;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Pragya on 07-12-2015.
 */
public class SignUp extends Driver {

    @Test(priority = 1, enabled = true)
    public void teacherSignUpExistingSchoolAndStudentSignUp(){
        try{
            WebDriver driver=Driver.getWebDriver();
            ReportUtil.log("Description","This test case validates the instructor sign up using new school and student sign up using class code","info");
            String appendChar = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar1 = "b" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            String appendCharacterBuild="";
            if (System.getProperty("UCHAR")!=null)
                appendCharacterBuild=System.getProperty("UCHAR");

            SchoolPage schoolPage = PageFactory.initElements(driver,SchoolPage.class);
            ClassPage classPage = PageFactory.initElements(driver,ClassPage.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            MyAssessments myAssessments = PageFactory.initElements(driver,MyAssessments.class);
            ManageClass manageClass = PageFactory.initElements(driver,ManageClass.class);

            String currentMethodName = new Exception().getStackTrace()[0].getMethodName();

            //Expected - Teacher SignUp through app creating new school
            new com.snapwiz.selenium.tests.staf.edulastic.apphelpers.SignUp().teacher(appendChar,1);//Register teacher1

            new School().enterAndSelectSchool("987654963", 1, false);
            schoolPage.getContinueButton().click(); //clicking on continue button

            //By default class name should appear on class page
            CustomAssert.assertEquals(classPage.nameOfClass.getAttribute("placeholder"),currentMethodName+"ins"+appendChar+appendCharacterBuild+"'s "+"class","Verify default class name","Default class name is displayed as expected","Default class name is not displayed as expected");

            String classCodeInstructor = new Classes().createClass(appendChar, 1);//create class

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

            //Expected - Verify active and archived tab
            CustomAssert.assertEquals(manageClass.tab_activeAndArchived.get(0).getText(),"Active (2)","Verify Active tab at instructor side","Active tab is displayed as expected","Active tab is not displayed as expected");
            CustomAssert.assertEquals(manageClass.tab_activeAndArchived.get(1).getText(),"Archived (0)","Verify Archived tab at instructor side","Archive tab is displayed as expected","Archive tab is not displayed as expected");

            new Navigator().logout();//log out

            //Student sign up using class code
            new com.snapwiz.selenium.tests.staf.edulastic.apphelpers.SignUp().studentDirectRegister(appendChar1,classCodeInstructor,1);

            new Navigator().navigateTo("assignment");//Navigate to assignment page

            //Assignment should not appear
            CustomAssert.assertTrue(assignments.message_noAssignment.getText().contains("No Assignments Available For You"),"Verify the assignment at student side","Assignments are not appearing as expected","Assignments are appearing");
            new Navigator().logout();//log out

        }catch (Exception e){
            Assert.fail("Exception in testcase 'teacherSignUpExistingSchoolAndStudentSignUp' in class 'SignUp'", e);
        }
    }
}
