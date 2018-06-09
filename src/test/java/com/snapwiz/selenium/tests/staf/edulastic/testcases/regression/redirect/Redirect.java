package com.snapwiz.selenium.tests.staf.edulastic.testcases.regression.redirect;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.InstructorDashboard;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.StudentDashboard;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.profile.Profile;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.studentresponse.StudentResponse;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.yourresponse.YourResponse;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by pragyas on 24-05-2016.
 */
public class Redirect extends Driver {

    @Test(priority = 1,enabled = true)
    public void editAssessment()
    {
        try
        {
            ReportUtil.log("Description","This test case validates the older assessment at student side after assigning and editing and redirect","info");

            WebDriver driver=Driver.getWebDriver();
            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);
            YourResponse yourResponse = PageFactory.initElements(driver,YourResponse.class);
            AssignmentSummary assignmentSummary = PageFactory.initElements(driver,AssignmentSummary.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            Assign assign = PageFactory.initElements(driver,Assign.class);

            String appendChar = "a" + StringUtil.generateRandomString(2, StringUtil.Mode.ALPHA);
//            String appendChar = "test1";

            System.out.println("appendChar:"+appendChar);
            int dataIndex = 1;

            new SignUp().teacher(appendChar, dataIndex);//sign up as an instructor
            new School().enterAndSelectSchool("987654963", dataIndex, false);
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode = new Classes().createClass(appendChar,dataIndex);
            new Navigator().logout();//Log out

            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as
            new Navigator().logout();

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Assignment().create(dataIndex, "truefalse");//Create an assignment
            new Assignment().addQuestion(dataIndex, "multiplechoice");
            new Assignment().addQuestion(dataIndex, "multipleselection");
            new Assignment().addQuestion(dataIndex, "textentry");
            new Assignment().assignToStudent(dataIndex,appendChar);//Assign the assignment

            //Edit the assessments by adding one more question in assessment
            new Assignment().addQuestion(dataIndex, "textdropdown");
            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            new Navigator().logout();//Log out

            new Login().loginAsStudent(appendChar,dataIndex);//Login as a student
            new Assignment().submitAssignment(dataIndex);

            //Student should see the older version only not edited one.
            CustomAssert.assertEquals(assignmentSummary.totalScore.getText(),"4","Verify the total question point","Older question point is displayed before editing teh assessment","Older question point is not displayed");

            assignmentSummary.button_continue.click();//Click on continue button

            //Only 4 questions should be present(Arrow should not appear on last question)
            WebDriverUtil.waitTillVisibilityOfElement(yourResponse.arrow_next,60);
            Thread.sleep(2000);
            for(int i = 0; i<3; i++)
            {
                yourResponse.arrow_next.click();//Click on next arrow
                WebDriverUtil.waitForAjax(driver,60);

            }

            boolean arrowFoundAfterOnLastQuestion = new BooleanValue().presenceOfElementByWebElement(dataIndex,yourResponse.arrow_next);

            CustomAssert.assertEquals(arrowFoundAfterOnLastQuestion,false,"Verify the next arrow on last question","Next arrow is not present on last question","Next arrow is displayed on last question");

            new Navigator().navigateTo("dashboard");//Navigate to dashboard
            new Navigator().logout();

            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.viewResponse.get(0).click();//Click on view responses
            WebDriverUtil.waitTillVisibilityOfElement(studentResponse.button_redirect,20);

            //Verify the error message on redirect
            studentResponse.button_redirect.click();//Click on redirect
            WebDriverUtil.waitTillVisibilityOfElement(studentResponse.notificationMsg_redirect,20);
            CustomAssert.assertEquals(studentResponse.notificationMsg_redirect.getText(),"At least one student should be selected to redirect assessment.","Verify the notification message for redirect","Notification message is  displayed as expected for redirect","Notification message is not displayed as expected for redirect");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase 'editAssessment' in class 'Redirect'", e);
        }
    }


    @Test(priority = 2,enabled = true)
    public void confirmationscreenView(){
        try{

            ReportUtil.log("Description","This test case validates the dashboard, assign page ,confirmation screen and result view after redirect","info");

            int dataIndex = 403;

            String appendChar = StringUtil.generateRandomString(2, StringUtil.Mode.ALPHA);
            //String appendChar="kt";
            String appendChar1 = "test1"+appendChar;
            String appendChar2 = "test2"+appendChar;
            String appendChar3 = "test3"+appendChar;

            WebDriver driver = getWebDriver();

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            StudentResponse studentResponse = PageFactory.initElements(driver,StudentResponse.class);
            Assign assign = PageFactory.initElements(driver,Assign.class);
            StudentDashboard studentDashboard = PageFactory.initElements(driver,StudentDashboard.class);
            InstructorDashboard instructorDashboard=PageFactory.initElements(driver,InstructorDashboard.class);
            Profile profile = PageFactory.initElements(driver,Profile.class);

            String methodName = new Exception().getStackTrace()[0].getMethodName();
            String studentName1 =  methodName+"st"+appendChar1;
            String studentName1Edited =  methodName+"stEdited";
            String studentName2 =  methodName+"st"+appendChar2;
            String  className = methodName+"class"+appendChar1;

            new SignUp().teacher(appendChar1,dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("987654963",dataIndex,false);
            schoolPage.getContinueButton().click();//click on continue button
            String classCode = new Classes().createClass(appendChar1,dataIndex);//Create class
            new Navigator().logout();

            //Register first student
            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);
            new Navigator().logout();

            //Register second student
            new SignUp().studentDirectRegister(appendChar2,classCode,dataIndex);
            new Navigator().logout();

            //Register third student
            new SignUp().studentDirectRegister(appendChar3,classCode,dataIndex);
            new Navigator().logout();

            //Login as an instructor, create an assignment with 2 questions and assign to class
            new Login().loginAsInstructor(appendChar1,dataIndex);
            new Assignment().create(dataIndex, "truefalse");
            new Assignment().addQuestion(dataIndex, "multiplechoice");
            new Assignment().assignToStudent(dataIndex, appendChar1);
            new Navigator().navigateTo("dashboard");
            WebDriverUtil.waitForAjax(driver,60);
            System.out.println(assessmentname);
            //Newly assigned assignment should be displayed
            CustomAssert.assertTrue(instructorDashboard.getLink_activeAsssessment().getText().contains(assessmentname),"Verify assignment name on instructor dashbaord","Newly created assignment is displayed on dashboard","Newly created assignment is not displayed on instructor dashboard");
            new Navigator().logout();

            //Login as student1 and submit assignment
            new Login().loginAsStudent(appendChar1,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().navigateTo("dashboard");
            new Navigator().logout();

            //Login as student2 and submit assignment
            new Login().loginAsStudent(appendChar2,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().navigateTo("dashboard");
            new Navigator().logout();

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.viewResponse.get(0).click();//Click on view responses
            WebDriverUtil.waitTillVisibilityOfElement(studentResponse.button_redirect,20);
            Thread.sleep(2000);
            //Total checkbox should be displayed as 3 (including 1 checkbox of header responses) (only for the student who has submitted the assignment i.e. 2 student in this case)
            CustomAssert.assertEquals(String.valueOf(studentResponse.checkboxInStudentCard.size()-1),"4","Verify the size of checkbox present in student card","Checkbox is not appearing for the student who has not started the assignment","Checkbox is appearing for the student who has not started the assignment");

            //Select all students who have submitted the assignment
            studentResponse.checkboxInStudentCard.get(1).click();
            studentResponse.checkboxInStudentCard.get(2).click();

            studentResponse.button_redirect.click();//Click on redirect button

            //3 students name who have submitted the assignment should be displayed in assign to field
            CustomAssert.assertEquals(String.valueOf(assign.textBoxItems_assignTO.size()),"2","Verify the number of students in assign to field","Number of students are displayed as expected","Number of students are not displayed as expected");

            for(int i=0;i<assign.textBoxItems_assignTO.size();i++)
            {
                if(assign.textBoxItems_assignTO.get(i).getText().trim().equals(studentName1))
                {
                }
                else if(assign.textBoxItems_assignTO.get(i).getText().trim().equals(studentName2))
                {
                }else if(assign.textBoxItems_assignTO.get(i).getText().trim().equals(className))
                {
                    CustomAssert.fail("Verify class name on assign to field","Class name is present after redirect");
                }
                else
                {
                    CustomAssert.fail("Verify student in assign to field","All the selected student is not displayed in assign to field");
                }
            }

            assign.backArrow.click();//Click on back arrow
            Thread.sleep(2000);
            //User should be taken to the Results view (Verify redirect button and student responses page)
            CustomAssert.assertEquals(studentResponse.activePage.get(1).getText(),"Student Responses","Verify the page name","Student responses page is displayed after clicking on '<' arrow on assign page","Student responses page is not displayed after clicking on '<' arrow on assign page");
            CustomAssert.assertEquals(studentResponse.button_redirect.getText(),"Redirect","Verify redirect button","Redirect button is displayed after clicking on '<' arrow on assign page","Redirect button is not displayed after clicking on '<' arrow on assign page");

            new Navigator().logout();//Log out

            //Login as a student1 and change the student name
            new Login().loginAsStudent(appendChar1,dataIndex);//Login as a student1
            studentDashboard.dropdown_userName.click();//Click on user name drop down
            profile.myProfile.click();//Click on my profile
            profile.editProfile.click();//Click on edit profile
            profile.firstName_user.clear();
            profile.firstName_user.sendKeys(studentName1Edited);//Change the student name
            profile.buttonSave.click();//Click on save button
            new Navigator().logout();//Log out

            new Login().loginAsInstructor(appendChar1,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.viewResponse.get(0).click();//Click on view responses
            Thread.sleep(4000);
            WebDriverUtil.waitTillVisibilityOfElement(studentResponse.button_redirect,20);

            //Select any student who have submitted the assignment
            studentResponse.checkboxInStudentCard.get(2).click();
            studentResponse.button_redirect.click();//Click on redirect button

            //Old name of the user should NOT be searchable
            assign.textBox_assignTO.click();//Click on assign to field
            assign.assignTo_close.get(0).click();//remove the existing name
            assign.textBox_assignTO.sendKeys(studentName1);

            CustomAssert.assertEquals(assign.assignTo_noResultFound.getText(),"No results found","Search for the student old name","Old name is not displayed as expected","Old name is displayed even after changing the name");

            //New name of the user should be searchable
            assign.textBox_assignTO.clear();
            assign.textBox_assignTO.sendKeys(studentName1Edited);

            CustomAssert.assertEquals(assign.assignTo_studentName.get(0).getText(),studentName1Edited,"Search for the student new name","New name is displayed as expected","New name is not displayed after changing the name");

            assign.backArrow.click();//Click on back arrow
            Thread.sleep(9000);
            studentResponse.checkboxInStudentCard.get(1).click();
            studentResponse.checkboxInStudentCard.get(2).click();
            studentResponse.button_redirect.click();//Click on redirect button

            // Assignment Name should be read-only.
            if(!(assign.assignmentName.getAttribute("value").trim().equals(assessmentname) && assign.assignmentName.getAttribute("disabled").equals("true")))
            {
                CustomAssert.fail("Verify the assignment name", "Assignment name is not disabled as expected");
            }
            Thread.sleep(2000);
            //Teacher should only be able to view Mastery Criteria which was set for the assignment during creation
            assign.editMasteryCriteria.click();//Expand mastery
            Thread.sleep(1000);

            CustomAssert.assertTrue(assign.masteryCriteria.getAttribute("class").contains("disable"),"Verify mastery criteria","Mastery criteria is disabled as expected","Mastery criteria is not disabled");

            Thread.sleep(2000);
            assign.getButton_assign().click();//Click on redirect button
            Thread.sleep(4000);

            // User should be taken to the Confirmation screen, with deep link
            //CustomAssert.assertTrue(assign.confirmationScreen.getText().contains("Assignment "+assessmentname+" has been assigned"),"Verify the confirmation screen","Confirmation screen is displayed as expected","Confirmation screen is not displayed as expected");
           // CustomAssert.assertTrue(assign.confirmationScreen.getText().contains(Config.baseURL+"/#renderResource/close"),"Verify the deep link","Deep link is displayed as expected","Deep link is not displayed as expected");


            driver.findElement(By.id("view-response-button")).click();
            //assign.button_viewAssignment.click();//Click on view assignment button
            Thread.sleep(4000);
            //User should be taken to the Results view (Verify Performance mastery percentage and student responses page)
            CustomAssert.assertEquals(studentResponse.activePage.get(1).getText(),"Student Responses","Verify the page name","Student responses page is displayed after clicking on view button","Student responses page is not displayed after clicking on view button");
            CustomAssert.assertEquals(studentResponse.performanceMasteryPercentage.get(0).getText(),"0%","Verify performance percentage","Redirect button is displayed after clicking on view button","Redirect button is not displayed after clicking on view button");

            //Redirect button should be disabled
            boolean redirectDisabled = new BooleanValue().presenceOfElementByWebElement(dataIndex,studentResponse.button_redirect);

            CustomAssert.assertEquals(redirectDisabled,false,"Verify redirect button","Redirect button is disabled as expected after clicking on view button","Redirect button is not disabled after clicking on view button");

            new Navigator().navigateTo("dashboard");

            //Reassigned assignment should be displayed
            String assessment = driver.findElement(By.xpath("//a[@title='"+assessmentname+"']")).getAttribute("title");
            CustomAssert.assertEquals(assessment,assessmentname,"Verify assignment name on instructor dashbaord","Newly created assignment is displayed on dashboard","Newly created assignment is not displayed on instructor dashboard");

        }catch (Exception e)
        {
            Assert.fail("Exception in app helper 'confirmationscreenView' in method 'Redirect'", e);

        }
    }



}   
