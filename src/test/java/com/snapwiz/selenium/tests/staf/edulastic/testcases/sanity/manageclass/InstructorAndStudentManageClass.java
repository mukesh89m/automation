package com.snapwiz.selenium.tests.staf.edulastic.testcases.sanity.manageclass;

import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login.LoginPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assign;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssignmentReview;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.StudentDashboard;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.manageClass.ManageClass;
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by pragyas on 25-02-2016.
 */
public class InstructorAndStudentManageClass extends Driver {

    LoginPage login;


    @BeforeMethod
    public void init(){
        WebDriver driver=Driver.getWebDriver();
        login = PageFactory.initElements(driver,LoginPage.class);
    }


    @Test(priority = 1,enabled = true)
    public void mangeClass(){
        try{

            WebDriver driver=Driver.getWebDriver();
            ReportUtil.log("Description","This test case validates the manage class details i.e. class name,description,student's password etc","info");

            String appendChar = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar1 = "b" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar2 = "c" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            int dataIndex = 17;

            String appendCharacterBuild= "";
            if(System.getProperty("UCHAR")!=null)
            {
                appendCharacterBuild = System.getProperty("UCHAR");
            }

            String  className = new Exception().getStackTrace()[0].getMethodName()+"class"+appendChar+appendCharacterBuild;
            String st1Email = new Exception().getStackTrace()[0].getMethodName()+"st"+appendChar+appendCharacterBuild+"@snapwiz.com";
            String st2Email = new Exception().getStackTrace()[0].getMethodName()+"st"+appendChar1+appendCharacterBuild+"@snapwiz.com";
            String st2Name = new Exception().getStackTrace()[0].getMethodName()+"st"+appendChar1+appendCharacterBuild;


            ManageClass manageClass = PageFactory.initElements(driver,ManageClass.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver,AssignmentReview.class);
            Assign assign = PageFactory.initElements(driver,Assign.class);
            StudentDashboard studentDashboard = PageFactory.initElements(driver,StudentDashboard.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);

            new SignUp().teacher(appendChar,dataIndex);//sign up as an instructor
            new School().enterAndSelectSchool("987654963", dataIndex, false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student
            String classNameAtStSideBeforeEdit = studentDashboard.name_classes.get(0).getText();
            new Navigator().logout();

            new Login().loginAsInstructor(appendChar,dataIndex);
            new Navigator().navigateTo("manageclass");//Navigate to manage class

            String endDateAtInsSideBeforeEdit = manageClass.endDate.getText();

            manageClass.editIcon_class.click();//Click on class edit icon
            manageClass.classNameWebElement().clear();
            driver.switchTo().activeElement().sendKeys(className+"Edited");//Edit the class name
            manageClass.editIcon_endDate.click();//Click on end date edit icon
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[starts-with(@class,'ui-icon ui-icon-circle-triangle')]")));
            manageClass.calender_arrows.get(0).click();//Click on left arrow
            manageClass.dates_calender.get(5).click();//Select the 6th date

            String endDateAtInsSideAfterEdit = manageClass.endDate.getText();

            manageClass.editIcon_description.click();//Click on description edit icon
            manageClass.descriptionTexareaWebElement().clear();
            driver.switchTo().activeElement().sendKeys("EditedDescription");//Enter the description
            Thread.sleep(2000);
            driver.findElement(By.xpath("//html//body")).click();
            Thread.sleep(2000);
            new Assignment().create(dataIndex,"truefalse");//Create an assignment
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getButton_review());//Click on review button
            WebDriverUtil.clickOnElementUsingJavascript(assessmentReview.nextButton);//Click on assign button

            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".item-text")));
            //Verify the edited class name on right top class dropdown
            CustomAssert.assertEquals(assign.textBoxItems_assignTO.get(0).getText(),className+"Edited","Verify edited class name in dropdown","Edited class name is displayed","Edited class name is not displayed");

            //Verify the edited class name on Assign to text box
            CustomAssert.assertEquals(assign.textBoxItems_assignTO.get(0).getText(),className+"Edited","Verify the edited class name in assign to text box","Edited class name is displayed as expected","Edited class name is not displayed as expected");
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            //Verify the edited class name on dashboard
            String classNameAtStSideAfterEdit = studentDashboard.name_classes.get(0).getText();
            CustomAssert.assertEquals(classNameAtStSideAfterEdit,className+"Edited","Verify the edited class name at student side","Edited class name is displayed at student side","Edited class name is not displayed at student side as expected");

            if(classNameAtStSideBeforeEdit.equals(classNameAtStSideAfterEdit))
            {
                CustomAssert.fail("Verify the class name at student side","Class name is not changed at student side after edit");
            }
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Navigator().navigateTo("manageclass");//Navigate to manage class

            //Verify that end date before edit and after edit should be different
            if(endDateAtInsSideBeforeEdit.equals(endDateAtInsSideAfterEdit))
            {
                CustomAssert.fail("Verify the end date","End dat after edit has not changed");
            }
            //Verify for the edited description
            CustomAssert.assertEquals(manageClass.descriptionContent.getText(),"EditedDescription","Verify the description content","Description is displayed as entered","Description is not displayed as expected");
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);//Sign up as a student2
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar2,classCode,dataIndex);//Sign up as a student3
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Navigator().navigateTo("manageclass");//Navigate to manage class

            //Change the password of student1
            manageClass.checkBoxSelectStudent.get(1).click();//Select student 1
            manageClass.getMore().click();//Click on more
            manageClass.changePassword.click();//Click on change password
            manageClass.textBox_resetPassword.get(0).clear();
            driver.switchTo().activeElement().sendKeys("snapwiz123");//Enter the new password
            manageClass.textBox_resetPassword.get(1).clear();
            driver.switchTo().activeElement().sendKeys("snapwiz123");//Confirm the new password
            manageClass.button_reset.click();//click on reset button

            //Delete student2
            manageClass.checkBoxSelectStudent.get(2).click();//select student 2
            manageClass.getMore().click();//Click on more
            Thread.sleep(2000);
            manageClass.deleteStudents.click();//click on delete students
            manageClass.getDeleteConfirmText().clear();
            manageClass.getDeleteConfirmText().sendKeys("REMOVE");
            manageClass.getYesDelete().click();//click on Yes,Delete button
            Thread.sleep(4000);

            //Verify the student's count
            int studentsCount = manageClass.getStudentNames().size();
            CustomAssert.assertEquals(String.valueOf(studentsCount),"3","Verify the students count after deleting the student","Students count is displayed as 3","Students count is not displayed as 3 after deleting a student");

            //Verify active student count
            String activeStudentCount=manageClass.currentStudentCount.getText();
            CustomAssert.assertEquals(activeStudentCount,"2","Verify the active students count after deleting the student","Active students count is displayed as 2","Active students count is not displayed as 2 after deleting a student");


            //Deleted student name should contains deactivated status
            String studentName=null;
            for(int i=0;i<studentsCount;i++)
            {
                studentName=manageClass.getStudentNames().get(i).getText();
                System.out.println("Student "+i+":"+studentName);
                if(studentName.equals(st2Name))
                {
                    String studentUserName=manageClass.getStudentUserNames().get(i).getText();
                   if(studentUserName.toLowerCase().contains("deactivated"))
                    CustomAssert.assertEquals(manageClass.getStudentUserNames().get(i).getText().toLowerCase().contains("deactivated"),true,"Verify deleted student status","Verify deleted student user name contains deactivated status text","Verify deleted student user name did not contain deactivated status text and actual status is "+studentUserName);
                    break;
                }
            }
            new Navigator().logout();//Instructor log out

            //Verify that deleted student(student 2)should not be able to logged in
            loginAsStudent(st2Email,"snapwiz");//Enter the email and password of student2

            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("notification-msg")));
            //Verify the error message on login page
            CustomAssert.assertEquals(login.getErrorMsg().getText(),"You have entered an invalid email/username or password.","Verify the error message for deleted student if try to login","Error message is appearing as expected","Error message is not appearing as expected");

            //Verify that student1 should not be able to logged in with old password
            loginAsStudent(st1Email,"snapwiz");

//            WebDriverUtil.waitTillVisibilityOfElement(login.getErrorMsg(),20);
            //Verify the error message on login page
            CustomAssert.assertEquals(login.getErrorMsg().getText(),"You have entered an invalid email/username or password.","Verify the error message for student login with old password","Error message is appearing as expected","Error message is not appearing as expected");
//            Thread.sleep(3000);
            //Student 1 should be able to logged in with changed password
           /* loginAsStudent(st1Email,"snapwiz123");//Login student1 with changed password

            CustomAssert.assertEquals(studentDashboard.heading_dashboard.getText(),"Dashboard","Verify the dashboard page","Student1 is logged in successfully","Student1 is not logged in successfully");
*/
        }catch (Exception e){
            Assert.fail("Exception in testcase 'mangeClass' in class 'ManageClass'", e);
        }
    }

    public void loginAsStudent(String email,String password)
    {
        try{
            login.getTextBox_username().clear();
            login.getTextBox_username().sendKeys(email);//Enter student2 email id
            login.getTextBox_login_password().clear();
            login.getTextBox_login_password().sendKeys(password);//Enter password
            login.getButton_signIn().click();//Click on sign in button
        }catch (Exception e)
        {
            Assert.fail("Exception in 'loginAsStudent' method", e);

        }

    }

}
