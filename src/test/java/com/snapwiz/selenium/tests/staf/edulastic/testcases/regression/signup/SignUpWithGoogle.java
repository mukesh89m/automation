package com.snapwiz.selenium.tests.staf.edulastic.testcases.regression.signup;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.DBConnect;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.RandomString;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.SignUp;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.School;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Classes;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Navigator;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login.LoginPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.MyAssessments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.classpage.ClassPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.manageClass.ManageClass;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.StudentSignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.TeacherSignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.BooleanValue;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.logging.Level;

/**
 * Created by Shashank on 10-12-2015.
 */
public class SignUpWithGoogle extends Driver{

    static String zipcode;
    @Test(priority = 1, enabled = true)
    public void teacherAndStudentSignUpWithGoogleNewSchool(){
        try{

            String appendChar = "a";
            String randNumber=new RandomString().randominteger(4);
            logger.log(Level.INFO, "Random string  " + appendChar);

            String password = "snapwiz2015";
            String email = "snaplogicautomationteacher";
            String email_student = "snaplogic.automation22";
            SchoolPage schoolPage = PageFactory.initElements(driver,SchoolPage.class);
            TeacherSignUpPage teacherSignUpPage = PageFactory.initElements(driver,TeacherSignUpPage.class);
            ClassPage classPage = PageFactory.initElements(driver,ClassPage.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            MyAssessments myAssessments = PageFactory.initElements(driver,MyAssessments.class);
            ManageClass manageClass = PageFactory.initElements(driver,ManageClass.class);

            String currentMethodName = new Exception().getStackTrace()[0].getMethodName();
            DBConnect.st.executeUpdate("update t_user set email = '" + email + randNumber + "', username = '" + randNumber + "',open_id ='" + randNumber + "' where email = '" + email + "@gmail.com';");
            DBConnect.st.executeUpdate("update t_user set email = '" + email_student + randNumber + "', username = '" + randNumber + "',open_id ='" + randNumber + "' where email = '" + email_student + "@gmail.com';");
            new SignUp().googleSignUp(email,password);
            schoolPage.getButton_add().click();//Click on add button
            schoolPage.getButton_addSchool().click();//Click on 'Add school button'

            //Expected - Error should appear if school name and zip is not entered
            Assert.assertEquals(schoolPage.getError_msg_schoolName().getText(),"Please provide a valid school name.","Error message is not appearing correctly if school is not entered");
            Assert.assertEquals(schoolPage.getError_msg_zip().getText(),"Please provide a valid zip code.","Error message is not displayed correctly if zip is not entered");
            schoolPage.getEditbox_schoolName().click();
            schoolPage.getEditbox_schoolName().clear();
            driver.switchTo().activeElement().sendKeys("School");//Enter the school name

            String str = new RandomString().randomstring(6);
            schoolPage.editBox_zipCode.sendKeys(str);//Enter zip code as string
            schoolPage.getButton_addSchool().click();//Click on 'Add school button'

            //Expected - Error should appear on zip code
            Assert.assertEquals(schoolPage.getError_msg_zip().getText(),"Please provide a valid zip code.","Error message is not appearing correctly if zip is not entered");

            //Error message should not appear on school name
            boolean errorOnSchoolFound = new BooleanValue().presenceOfElementByWebElement(1,teacherSignUpPage.getErrorMessage_name());
            Assert.assertEquals(errorOnSchoolFound,false,"Error message is displayed on school even after entering the value");

            schoolPage.getClose().click();//Click on close button
            zipcode=new School().createWithOnlyName(appendChar,1);//Create a school

            //By default class name should appear on class page
            Assert.assertEquals(classPage.nameOfClass.getAttribute("placeholder"),"Dharanesha Gowda's class","By default class name is not displayed as expected");

            classPage.finishButton.click();//Click on finish button

            //Error message should appear on all the field except class name
            Assert.assertEquals(classPage.errorMsg_standard.getText(),"Subject Area is not selected","Error message is not displayed as expected on subject area");
            Assert.assertEquals(classPage.errorMsg_subject.getText(),"Subject is not selected","Error message is not displayed as expected on subject");
            Assert.assertEquals(classPage.errorMsg_subjectGrade.getText(),"Grade is not selected","Error message is not displayed as expected on subject grade");

            new Classes().createClass(appendChar, 1);//create class

            new Navigator().navigateTo("assignment");//Navigate to assignment

            //Expected - Assignment should not appear
            Assert.assertTrue(assignments.message_noAssignment.getText().contains("No Assignments Available For Your Class"), "Assignments are appearing for newly signed up instructor");

            new Navigator().navigateTo("myAssessments");//Navigate to my assessments

            //No assessments should appear under published and draft
            Assert.assertTrue(assignments.message_noAssignment.getText().contains("Assessment not available"),"Assessments are appearing for newly signed up instructor under published");

            myAssessments.draft.click();//Click on draft

            Assert.assertTrue(assignments.message_noAssignment.getText().contains("Drafts not available"),"Assessments are appearing under draft for newly signed up instructor");

            new Navigator().navigateTo("manageclass");//Navigate to manage class

            //Expected - Verify class name as created
            Assert.assertEquals(manageClass.getClassNameStudent().get(0).getAttribute("title"),currentMethodName+"class"+appendChar,"class name is not same as created");

            //Expected - Verify active and archived tab
            Assert.assertEquals(manageClass.tab_activeAndArchived.get(0).getText(),"Active (2)","Active tab is not displayed as expected");
            Assert.assertEquals(manageClass.tab_activeAndArchived.get(1).getText(),"Archived (0)","Archive tab is not displayed as expected");

            String url = manageClass.getUrl().getText();
            String classCodeInstructor = manageClass.getclassCode().get(0).getText();
            new Navigator().logout();//log out
            driver.quit();

            reInitDriver();
            assignments = PageFactory.initElements(driver,Assignments.class);
            manageClass = PageFactory.initElements(driver,ManageClass.class);
            new SignUp().edulasticStudentSignUpWithUrl(email_student,password,url);

            new Navigator().navigateTo("assignment");//Navigate to assignment page

            //Assignment should not appear
            Assert.assertTrue(assignments.message_noAssignment.getText().contains("No Assignments Available For You"),"Assignments are appearing");

            new Navigator().navigateTo("manageclass");//Navigate to amnage class
            //Expected - Verify active and archived tab
            Assert.assertEquals(manageClass.tab_activeAndArchived.get(0).getText(),"Active (1)","Active tab is not displayed as expected");
            Assert.assertEquals(manageClass.tab_activeAndArchived.get(1).getText(),"Archived (0)","Archive tab is not displayed as expected");
            //Expected - Verify class name of student and class code
            Assert.assertEquals(manageClass.getClassNameStudent().get(0).getAttribute("title"),currentMethodName+"class"+appendChar,"Student class name is not correct");
            String classCodeStudent = manageClass.getclassCode().get(0).getText();
            if(!classCodeInstructor.equals(classCodeStudent))
            {
                Assert.fail("Class code in not correct in student side");
            }
        }catch (Exception e){
            Assert.fail("Exception in testcase 'teacherAndStudentSignUpNewSchool' in class 'SignUp'",e);

        }

    }



    @Test(priority = 2, enabled = true)
    public void teacherAndStudentSignUpWithGoogleExistingSchool(){
        try{

            String appendChar = "a";
            String randNumber=new RandomString().randominteger(4);
            logger.log(Level.INFO, "Random string  " + appendChar);

            String password = "snapwiz2015";
            String email = "snaplogicautomationteacher";
            String email_student = "snaplogic.automation22";
            SchoolPage schoolPage = PageFactory.initElements(driver,SchoolPage.class);
            TeacherSignUpPage teacherSignUpPage = PageFactory.initElements(driver,TeacherSignUpPage.class);
            ClassPage classPage = PageFactory.initElements(driver,ClassPage.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            MyAssessments myAssessments = PageFactory.initElements(driver,MyAssessments.class);
            ManageClass manageClass = PageFactory.initElements(driver,ManageClass.class);

            String currentMethodName = new Exception().getStackTrace()[0].getMethodName();
            DBConnect.st.executeUpdate("update t_user set email = '" + email + randNumber + "', username = '" + randNumber + "',open_id ='" + randNumber + "' where email = '" + email + "@gmail.com';");
            DBConnect.st.executeUpdate("update t_user set email = '" + email_student + randNumber + "', username = '" + randNumber + "',open_id ='" + randNumber + "' where email = '" + email_student + "@gmail.com';");
            new SignUp().googleSignUp(email,password);
            new School().enterAndSelectSchool(zipcode,1,false);//enter zip code and select school
            schoolPage.getContinueButton().click();//Click on continue button

            //By default class name should appear on class page
            Assert.assertEquals(classPage.nameOfClass.getAttribute("placeholder"),"Dharanesha Gowda's class","By default class name is not displayed as expected");

            classPage.finishButton.click();//Click on finish button

            //Error message should appear on all the field except class name
            Assert.assertEquals(classPage.errorMsg_standard.getText(),"Subject Area is not selected","Error message is not displayed as expected on subject area");
            Assert.assertEquals(classPage.errorMsg_subject.getText(),"Subject is not selected","Error message is not displayed as expected on subject");
            Assert.assertEquals(classPage.errorMsg_subjectGrade.getText(),"Grade is not selected","Error message is not displayed as expected on subject grade");

            new Classes().createClass(appendChar, 1);//create class

            new Navigator().navigateTo("assignment");//Navigate to assignment

            //Expected - Assignment should not appear
            Assert.assertTrue(assignments.message_noAssignment.getText().contains("No Assignments Available For Your Class"), "Assignments are appearing for newly signed up instructor");

            new Navigator().navigateTo("myAssessments");//Navigate to my assessments

            //No assessments should appear under published and draft
            Assert.assertTrue(assignments.message_noAssignment.getText().contains("Assessment not available"),"Assessments are appearing for newly signed up instructor under published");

            myAssessments.draft.click();//Click on draft

            Assert.assertTrue(assignments.message_noAssignment.getText().contains("Drafts not available"),"Assessments are appearing under draft for newly signed up instructor");

            new Navigator().navigateTo("manageclass");//Navigate to manage class

            //Expected - Verify class name as created
            Assert.assertEquals(manageClass.getClassNameStudent().get(0).getAttribute("title"),currentMethodName+"class"+appendChar,"class name is not same as created");

            //Expected - Verify active and archived tab
            Assert.assertEquals(manageClass.tab_activeAndArchived.get(0).getText(),"Active (2)","Active tab is not displayed as expected");
            Assert.assertEquals(manageClass.tab_activeAndArchived.get(1).getText(),"Archived (0)","Archive tab is not displayed as expected");

            String url = manageClass.getUrl().getText();
            String classCodeInstructor = manageClass.getclassCode().get(0).getText();
            new Navigator().logout();//log out
            driver.quit();

            reInitDriver();
            assignments = PageFactory.initElements(driver,Assignments.class);
            manageClass = PageFactory.initElements(driver,ManageClass.class);
            new SignUp().edulasticStudentSignUpWithUrl(email_student,password,url);

            new Navigator().navigateTo("assignment");//Navigate to assignment page

            //Assignment should not appear
            Assert.assertTrue(assignments.message_noAssignment.getText().contains("No Assignments Available For You"),"Assignments are appearing");

            new Navigator().navigateTo("manageclass");//Navigate to amnage class
            //Expected - Verify active and archived tab
            Assert.assertEquals(manageClass.tab_activeAndArchived.get(0).getText(),"Active (1)","Active tab is not displayed as expected");
            Assert.assertEquals(manageClass.tab_activeAndArchived.get(1).getText(),"Archived (0)","Archive tab is not displayed as expected");
            //Expected - Verify class name of student and class code
            Assert.assertEquals(manageClass.getClassNameStudent().get(0).getAttribute("title"),currentMethodName+"class"+appendChar,"Student class name is not correct");
            String classCodeStudent = manageClass.getclassCode().get(0).getText();
            if(!classCodeInstructor.equals(classCodeStudent))
            {
                Assert.fail("Class code in not correct in student side");
            }
        }catch (Exception e){
            Assert.fail("Exception in testcase 'teacherAndStudentSignUpNewSchool' in class 'SignUp'",e);

        }

    }
}
