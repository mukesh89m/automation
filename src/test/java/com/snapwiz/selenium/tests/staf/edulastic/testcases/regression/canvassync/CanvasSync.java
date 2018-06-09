package com.snapwiz.selenium.tests.staf.edulastic.testcases.regression.canvassync;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Google.SignIn;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.canvasclassroom.CanvasClassRoom;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.dashboard.StudentDashboard;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.googleclassroom.GoogleclassRoom;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.manageClass.ManageClass;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.reports.MyReports;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.studentresponse.StudentResponse;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.yourresponse.YourResponse;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.*;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by priyanka on 14-09-2016.
 */
public class CanvasSync extends Driver {
    WebDriver driver;
    ManageClass manageClass;
    CanvasClassRoom canvasClassRoom;
    StudentDashboard studentDashboard;
    AssignmentDetails assignmentDetails;
    MyAssessments myAssessments;
    Assessments assessments;
    AssignmentSummary assignmentSummary;
    StudentResponse studentResponse;
    YourResponse yourResponse;
    MyReports myReports;
    TakeAssignment takeAssignment;
    Performance performance;
    Assignments assignments;
    SchoolPage schoolPage;
    SignIn signIn;
    String actual = "";
    String expected = "";


    @BeforeMethod()
    public void init() {
        driver = getWebDriver();
        schoolPage = PageFactory.initElements(driver, SchoolPage.class);
        manageClass = PageFactory.initElements(driver, ManageClass.class);
        canvasClassRoom = PageFactory.initElements(driver, CanvasClassRoom.class);
        myAssessments = PageFactory.initElements(driver, MyAssessments.class);
        assignmentDetails = PageFactory.initElements(driver, AssignmentDetails.class);
        performance = PageFactory.initElements(driver, Performance.class);
        assignmentSummary = PageFactory.initElements(driver, AssignmentSummary.class);
        yourResponse = PageFactory.initElements(driver, YourResponse.class);
        studentResponse = PageFactory.initElements(driver, StudentResponse.class);
        assessments = PageFactory.initElements(driver, Assessments.class);
        myReports = PageFactory.initElements(driver, MyReports.class);
        takeAssignment = PageFactory.initElements(driver, TakeAssignment.class);
        signIn = PageFactory.initElements(driver, SignIn.class);
    }

    @Test(priority = 1, enabled = true)
    public void canvasSync() {
        try {

            ReportUtil.log("Description", "This case validates the canvas sync", "info");

            int dataIndex = 1;
            //String appendChar = "akR";
            String appendChar = "a" + StringUtil.generateRandomString(2, StringUtil.Mode.ALPHA);
            String canvasStudent2Math = "Auto.Student2@yopmail.com";
            String canvasStudent6Science = "Auto.Sci6@yopmail.com";
            String canvasClassroomUrl = "https://edulastic.instructure.com";
            WebDriver driver = getWebDriver();

            studentDashboard = PageFactory.initElements(driver, StudentDashboard.class);
            assignments = PageFactory.initElements(driver, Assignments.class);
            schoolPage = PageFactory.initElements(driver, SchoolPage.class);

            //testda@snapwiz.com da is present in the same district of instructor (canvas checkbox is checked)
            new SignUp().teacher(appendChar, dataIndex);//Sign up as an instructor1
            new School().enterAndSelectSchool("654321369", dataIndex, false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode1 = new Classes().createClass(appendChar, dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode1, dataIndex);//Sign up as a student
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as instructor
            new Navigator().navigateTo("manageclass");//Navigate to manage class

            // Verify "Share with Canvas Class" is available for non-clever teacher when checkbox is selected
            CustomAssert.assertEquals(manageClass.syncWithCanvas.getText(), "Sync with Canvas", "Verify sync with canvas classroom", "sync with canvas classroom is available", "Sync with canvas classroom is not available");

            // Verify "student count"
            actual = manageClass.currentStudentCount.getText();
            CustomAssert.assertEquals(actual, "1", "Verify current student count", "Current student count is 1", "Current student count is more than 1");
            int studentSize = manageClass.studentPresentInTable.size();
            CustomAssert.assertEquals(studentSize, 1, "Verify student present in table", "Only 1 student is present in the table", "More than 1 student present in table");

            //sync with class
            syncWithClass(0,2);
            actual = manageClass.currentStudentCount.getText();
            CustomAssert.assertEquals(actual, "2", "Verify current student count", "Current student count is 2", "Current student count is not 2");
            int studentSize1 = manageClass.studentPresentInTable.size();
            CustomAssert.assertEquals(studentSize1, 2, "Verify student present in table", "2 students present in the table", "In student table count is not 2");

            //Synced Canvas course name should be dispaleyd in dropdown
            manageClass.syncWithCanvas.click();//click on sync with canvas
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.changeCanvasCourse, 60);
            manageClass.changeCanvasCourse.click();//click on change canvas course
            actual = manageClass.changeCourseDropDown.get(1).getText();
            CustomAssert.assertEquals(actual, "Automation Math Class", "Verify synced class name", "Synced Canvas course name should be displayed in dropdown", "Synced Canvas course name is not displayed in dropdown");

            // Popup should get closed
            manageClass.cancelSyncPopUp.click();//click on cancel
            Thread.sleep(2000);
            boolean syncPopUp = new BooleanValue().presenceOfElementByWebElement(dataIndex, manageClass.syncPopUp);
            CustomAssert.assertEquals(syncPopUp, false, "Verify the sync pop up", "Sync pop up should closed", "Sync pop up is not closed");

            manageClass.syncWithCanvas.click();//click on sync with canvas
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.changeCanvasCourse, 60);
            manageClass.changeCanvasCourse.click();//click on change canvas course
            manageClass.courseDropDown.get(1).click();
            Thread.sleep(1000);
            manageClass.selectCourse.get(1).click();
            Thread.sleep(3000);
            manageClass.courseDropDown.get(2).click();
            Thread.sleep(2000);
            manageClass.selectSection.get(1).click();
            Thread.sleep(1000);
            manageClass.syncButton.click();
            manageClass.yesOnSyncPopUp.click();//click on yes
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.closeButton, 900);
            manageClass.closeButton.click();
            Thread.sleep(2000);
            actual = manageClass.currentStudentCount.getText();
            CustomAssert.assertEquals(actual, "1", "Verify current student count", "Current student count is 1", "Current student count is not 2");
            int studentSize2 = manageClass.studentPresentInTable.size();
            CustomAssert.assertEquals(studentSize2, 2, "Verify student present in table", "2 students present in the table", "In student table count is not 2");

            //Synced Canvas course name should be displayed in dropdown
            manageClass.syncWithCanvas.click();//click on sync with canvas
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.changeCanvasCourse, 60);
            manageClass.changeCanvasCourse.click();//click on change canvas course
            actual = manageClass.changeCourseDropDown.get(1).getText();
            CustomAssert.assertEquals(actual, "Automation Science Class", "Verify synced class name", "Synced Canvas course name should be displayed in dropdown", "Synced Canvas course name is not displayed in dropdown");
            manageClass.cancelSyncPopUp.click();//click on cancel
            Thread.sleep(2000);

            driver.get(canvasClassroomUrl);
            new Login().canvasLogout();
            new Login().loginToCanvasClassRoom(1, canvasStudent6Science);
            canvasClassRoom.sideNavigator.get(0).click();//click on account
            Thread.sleep(1000);
            canvasClassRoom.profile_Link.click();//click on canvas

        } catch (Exception e) {
            Assert.fail("Exception in 'googleSyncAfterResetAndDisable' in 'CanvasSync' method", e);

        }
    }

    @Test(priority = 2, enabled = true)
    public void assignAssignmentAndVerifyCanvasPopUp() {
        try {
            ReportUtil.log("Description", "This case validates the google sync when edulastic ins and google classroom stu is in same district", "info");

            int dataIndex = 2;
            String appendChar = "a" + StringUtil.generateRandomString(2, StringUtil.Mode.ALPHA);
            //String appendChar = "asM";
            String canvasClassroomTeacher = "auto.tech1@yopmail.com";
            String canvasStudent2Math = "Auto.Student2@yopmail.com";
            String canvasClassroomUrl = "https://edulastic.instructure.com";

            WebDriver driver = getWebDriver();
            String assignmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            studentDashboard = PageFactory.initElements(driver, StudentDashboard.class);
            assignments = PageFactory.initElements(driver, Assignments.class);

            //testda@snapwiz.com da is present in the same district of instructor (canvas checkbox is checked)
            new SignUp().teacher(appendChar, dataIndex);//Sign up as an instructor1
            new School().enterAndSelectSchool("654321369", dataIndex, false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode1 = new Classes().createClass(appendChar, dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode1, dataIndex);//Sign up as a student
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as instructor
            //Browser popup should appear to login to Canvas
            new Navigator().navigateTo("manageclass");//Navigate to manage class
            manageClass.syncWithCanvas.click();//click on sync with canvas
            String parentWindow = driver.getWindowHandle();
            WebDriverUtil.switchToNewWindow();
            Thread.sleep(1000);
            WebDriverUtil.waitTillVisibilityOfElement(canvasClassRoom.canvasLogo,90);
            boolean canvasPopUp=canvasClassRoom.canvasLogo.isDisplayed();
            CustomAssert.assertTrue(canvasPopUp,"Verify canvas pop up","Canvas pop is displaying","Canvas pop up is not displayed");
            new Login().directLoginToCanvasClassRoom(1);
            Thread.sleep(9000);
            driver.switchTo().window(parentWindow);
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.selectCanvasCoursePopUp,90);
            CustomAssert.assertTrue(manageClass.selectCanvasCoursePopUp.isDisplayed(),"Verify select canvas course pop up","Canvas course pop up is displayed","Canvas course pop up is not displayed");
            CustomAssert.assertTrue(manageClass.courseDropDown.get(1).isDisplayed(),"Verify select course drop down","Select course dropdown is displayed","Select course dropdown is not displayed");
            CustomAssert.assertTrue(manageClass.courseDropDown.get(2).isDisplayed(),"Verify select section drop down","Select section dropdown is displayed","Select section dropdown is not displayed");
            CustomAssert.assertEquals(manageClass.cancelSyncPopUp.getText(),"Cancel","Verify cancel link","Cancel link is displayed","Cancel link is not displayed");

            manageClass.courseDropDown.get(1).click();
            Thread.sleep(1000);
            manageClass.selectCourse.get(0).click();
            Thread.sleep(4000);
            manageClass.courseDropDown.get(2).click();
            Thread.sleep(3000);
            manageClass.selectSection.get(0).click();
            Thread.sleep(1000);
            manageClass.syncButton.click();
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.closeButton, 90);
            actual = manageClass.syncSuccessMessage.getText();
            expected = "Class successfully synced with Canvas Course.";
            CustomAssert.assertEquals(actual,expected,"Verify sync success message","Class is synced successfully","Class is not synced successfully");
            manageClass.closeButton.click();//click on close

            //Upload students button should NOT be shown for this class, once the sync has been successful.
            boolean uploadClassRoaster =WebDriverUtil.isElementPresent(By.xpath("//*[text()='Upload Class Roster']"));
            CustomAssert.assertEquals(uploadClassRoaster, false, "Verify upload Class Roaster", "upload Class Roaster is not present", "upload Class Roaster is present");

            //Change Password, Delete students, “+ Add students via email” - should be shown for this class.
            WebDriverUtil.clickOnElementUsingJavascript(manageClass.getMore());//click on more
            actual = manageClass.addStudent.getText();
            CustomAssert.assertEquals(actual,"Add Student","Verify add student link","Add student link is available","Add student link is not available");
            actual = manageClass.changePassword.getText();
            CustomAssert.assertEquals(actual,"Change Password for selected student(s)","Verify Change Password for selected student(s) link","Change Password for selected student(s)t link is available","Change Password for selected student(s) link is not available");
            actual = manageClass.deleteSelectedStudent.getText();
            CustomAssert.assertEquals(actual,"Remove selected student(s)","Verify Delete selected student(s) link","Delete selected student(s) link is available","Delete selected student(s) link is not available");

            new Assignment().create(dataIndex, "truefalse");//Create assessment
            new Assignment().assignToStudent(dataIndex, appendChar);//Assign to class
            Thread.sleep(3000);

            //Assignment Name, Due date should be same as defined in Edulastic.
            //new Login().loginToCanvasClassRoom(dataIndex,canvasClassroomTeacher);
            driver.get(canvasClassroomUrl);
            clickOnAssignmentsInCanvas();
            String assignment = canvasClassRoom.assignmentName.get(canvasClassRoom.assignmentName.size()-1).getText().trim();
            CustomAssert.assertEquals(assignment,assignmentName,"Verify assignment name","Assignment name is same as defined in edulastic","Assignment name is not same as defined in edulastic");

            //Assignment should get removed from Edulastic
            //new Login().loginAsInstructor(appendChar,dataIndex);
            driver.get(Config.baseURL);//Will be logged in as an instructor in edulastic
            new Navigator().navigateTo("assignment");//navigate to assignments
            assignments.more.click();//Click on more
            assignments.deleteAssessment.get(0).click();//click on delete
            assignments.deleteTextBox.sendKeys("DELETE");
            assignments.yesDelete.click();//click on yes
            Thread.sleep(1000);
            actual = assignments.assessmentNotAvailable.getText().trim();
            CustomAssert.assertEquals(actual,"No Assignments Available For Your Class","Verify deleted assessment","Assessment is deleted from edulastic","Assessment is not deleted fron edulastic");

            new Navigator().navigateTo("myAssessments");//navigate to my assessments
            WebDriverUtil.clickOnElementUsingJavascript(myAssessments.assignmentList.get(0));//click on assessment name
            WebDriverUtil.clickOnElementUsingJavascript(assignmentDetails.deleteButton);//click on delete
            assignments.deleteTextBox.sendKeys("DELETE");
            assignments.yesButtonOnDeletePopUp.click();//click on yes
            Thread.sleep(1000);
            actual = myAssessments.assessmentNotAvailable.getText().trim();
            CustomAssert.assertEquals(actual,"Assessment not available","Verify deleted assessment","Assessment is deleted from edulastic","Assessment is not deleted fron edulastic");
            new Navigator().logout();//Instructor log out

            //new Login().loginToCanvasClassRoom(dataIndex,canvasClassroomTeacher);
            driver.get(canvasClassroomUrl);
            clickOnAssignmentsInCanvas();
            actual = canvasClassRoom.assignmentName.get(canvasClassRoom.assignmentName.size()-1).getText();
            CustomAssert.assertEquals(actual,assignmentName,"Verify assignment after deleted from edulastic","Assignment is present","Assignment is deleted");
            new Login().canvasLogout();

            new Login().loginToCanvasClassRoom(dataIndex,canvasStudent2Math);
            clickOnAssignmentsInCanvas();
            actual = canvasClassRoom.assignmentName.get(canvasClassRoom.assignmentName.size()-1).getText();
            CustomAssert.assertEquals(actual,assignmentName,"Verify assignment after deleted from edulastic","Assignment is present","Assignment is deleted");
            new Login().canvasLogout();

            //Change the password for canvas student2
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as instructor
            new Navigator().navigateTo("manageclass");//Navigate to manage class
            manageClass.checkBoxSelectStudent.get(1).click();//Select canvas math student2
            manageClass.getMore().click();//click on more
            manageClass.changePassword.click();//Click on change password
            manageClass.textBox_resetPassword.get(0).sendKeys("snapwiz123");//Enter new password
            manageClass.textBox_resetPassword.get(1).sendKeys("snapwiz123");//Confirm password
            manageClass.button_reset.click();//Click on reset button
            new Navigator().logout();//Instructor log out

           //Verify login of canvas sync student through Edulastic Credentials
            new Login().directLoginAsInstructor(dataIndex,canvasStudent2Math,"snapwiz123");//Login as google st1 in edu app after resetting password
            new Navigator().navigateTo("manageclass");//Navigate to manage class
            CustomAssert.assertEquals(studentDashboard.heading_dashboard.getText(),"Manage Class","Verify the canvas math student2 log in in edu app","canvas math student2 is logged in as expected","canvas math student2 is not logged in");
            new Navigator().logout();//Instructor log out

            //Again reset the password for student2 from 'snapwiz123' to 'snapwiz'
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as instructor
            new Navigator().navigateTo("manageclass");//Navigate to manage class
            manageClass.checkBoxSelectStudent.get(1).click();//Select canvas math student2
            manageClass.getMore().click();//click on more
            manageClass.changePassword.click();//Click on change password
            manageClass.textBox_resetPassword.get(0).sendKeys("snapwiz");//Enter new password
            manageClass.textBox_resetPassword.get(1).sendKeys("snapwiz");//Confirm password
            manageClass.button_reset.click();//Click on reset button
            new Navigator().logout();//Instructor log out

        } catch (Exception e) {
            Assert.fail("Exception in 'assignAssignmentAndVerifyCanvasPopUp' in 'CanvasSync' method", e);

        }

    }
    @Test(priority = 3, enabled = true)
    public void instructorCloseTheAssignment(){
        try{
            int dataIndex = 3;
            String appendChar = "a" + StringUtil.generateRandomString(2, StringUtil.Mode.ALPHA);
            //String appendChar = "aKq";

            String canvasClassroomTeacher = "auto.tech1@yopmail.com";
            String canvasStudent2Math = "Auto.Student2@yopmail.com";
            String canvasClassroomUrl = "https://edulastic.instructure.com";

            WebDriver driver = getWebDriver();

            String assignmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            studentDashboard = PageFactory.initElements(driver, StudentDashboard.class);
            assignments = PageFactory.initElements(driver, Assignments.class);

            //testda@snapwiz.com da is present in the same district of instructor (canvas checkbox is checked)
            new SignUp().teacher(appendChar, dataIndex);//Sign up as an instructor1
            new School().enterAndSelectSchool("654321369", dataIndex, false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode1 = new Classes().createClass(appendChar, dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode1, dataIndex);//Sign up as a student
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as instructor
            //canvas sync with class
            syncWithClass(0,0);

            new Assignment().create(dataIndex, "truefalse");//Create assessment
            new Assignment().assignToStudent(dataIndex, appendChar);//Assign to class
            Thread.sleep(3000);

            driver.get(canvasClassroomUrl);
            new Login().canvasLogout();//logout as instructor
            new Login().loginToCanvasClassRoom(dataIndex,canvasStudent2Math);
            clickOnAssignmentsInCanvas();
            Thread.sleep(4000);
            List<WebElement> assignmentsname = driver.findElements(By.xpath("//div[@id='assignment_group_upcoming_assignments']//a[contains(text(),'"+assignmentName+"')]"));
            assignmentsname.get(assignmentsname.size()-1).click();
            canvasClassRoom.loadRegressionInNewWindow.click();//click on load assignment in a new window
            Thread.sleep(9000);
            //No login should be required in Edulastic when student is navigated
            String parentWindow = driver.getWindowHandle();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            WebDriverUtil.waitForAjax(driver,150);
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.className("true-false-student-content-text")),900);
            List<WebElement> allOptions = driver.findElements(By.className("true-false-student-content-text"));
            int index = 0;
            for(WebElement option : allOptions)
            {
                if(option.getText().equals("True"))
                {
                    break;
                }
                index++;
            }
            new Click().clickbylist("true-false-student-answer-label", index);//click on correct option
            driver.findElement(By.id("as-take-next-question")).click();//click on next
            new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'btn sty-blue submit')]")));
            driver.findElement(By.xpath("//span[contains(@class,'btn sty-blue submit')]")).click();//click on Submit
            Thread.sleep(2000);
            performance.backArrow.click();//click on back arrow
            driver.switchTo().window(parentWindow);
            //new Login().canvasLogout();

            //close the assignment
            driver.get(Config.baseURL);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as instructor
            new Navigator().navigateTo("assignment");//navigate to assignments
            assignments.more.click();
            assignments.closeAssignment.click();//click on close assignment
            assignments.deleteTextBox.sendKeys("CLOSE");
            assignments.yesCloseButton.click();//click on yes close
            new Navigator().logout();

            Thread.sleep(2000);
            driver.get(canvasClassroomUrl);
            Thread.sleep(4000);
            new Login().canvasLogout();
            new Login().loginToCanvasClassRoom(dataIndex,canvasClassroomTeacher);
            clickOnAssignmentsInCanvas();
            Thread.sleep(9000);
            canvasClassRoom.grades.click();//click on grades from option
            Thread.sleep(9000);
            WebDriverUtil.waitForAjax(driver,60);
            WebDriverUtil.enterValueUsingJavascript(canvasClassRoom.studentFilterTextBox,canvasStudent2Math);
            actual = driver.findElement(By.xpath("//div[@title='"+assignmentName+"']/../../following-sibling::div[3]//div[@class='gradebook-cell   ']")).getText();
            CustomAssert.assertEquals(actual,"1","Verify grade of assignment after instructor close the assignment in edulastic","Grade is released","Grade is not released");
            new Login().canvasLogout();//logout as instructor

            new Login().loginToCanvasClassRoom(dataIndex,canvasStudent2Math);
            //Assignment Name, Due date should be same as defined in Edulastic.
            canvasClassRoom.classes.get(0).click();//click on "Automation math class"
            WebDriverUtil.clickOnElementUsingJavascript(canvasClassRoom.showMore);//click on show more
            actual = canvasClassRoom.assignmentList.get(0).getText();
            expected = "Assignment created - "+assignmentName+", Automation Math Class";
            CustomAssert.assertEquals(actual,expected,"Verify assignment","Assignment is displayed","Assignment is not displayed");

            canvasClassRoom.assignments_Menu.click();//click on assignments from menu
            String assignment = canvasClassRoom.assignmentName.get(canvasClassRoom.assignmentName.size()-1).getText().trim();
            CustomAssert.assertEquals(assignment,assignmentName,"Verify assignment name","Assignment name is same as defined in edulastic","Assignment name is not same as defined in edulastic");
            Thread.sleep(2000);
            List<WebElement> assignments = driver.findElements(By.xpath("//div[@id='assignment_group_upcoming_assignments']//a[contains(text(),'"+assignmentName+"')]/../div//span[@class='score-display']"));
            actual = assignments.get(assignments.size()-1).getText();
            CustomAssert.assertEquals(actual,"1/1 pts","Verify score","Score is as per expected","Score is not as per expected");
            canvasClassRoom.grades.click();//click on grades from option
            List<WebElement> grades = driver.findElements(By.xpath("//a[text()='"+assignmentName+"']/../following-sibling::td[2]//span[@class='grade']"));
            actual = grades.get(grades.size()-1).getText().trim();
            if(!actual.contains("1")) {
                CustomAssert.fail("Verify grade of assignment after instructor close the assignment in edulastic","Grade is not released");
            }

            //Student should be navigated to Performance Summary page
            canvasClassRoom.assignments_Menu.click();//click on assignments from menu
            canvasClassRoom.assignmentName.get( canvasClassRoom.assignmentName.size()-1).click();//click on assignment
            canvasClassRoom.loadRegressionInNewWindow.click();//click on load assignment in a new window
            WebDriverUtil.switchToNewWindow();
            WebDriverUtil.waitTillVisibilityOfElement(performance.assignmentName,900);
            actual = performance.performanceSummary.getText();
            CustomAssert.assertEquals(actual,"Performance Summary","Verify student should navigated to performance summary","Student navigated to performance summary","Student is not navigated to performance summary");

        }catch (Exception e) {
            Assert.fail("Exception in 'instructorCloseTheAssignment' in 'CanvasSync' method", e);

        }
    }

    @Test(priority = 4, enabled = false)
    public void verifyAssignmentReportsForMergedStudent() {
        try {
            int dataIndex = 4;
            String appendChar = "aDe";//aRi";
            //String appendChar = "a" + StringUtil.generateRandomString(2, StringUtil.Mode.ALPHA);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            String canvasClassroomTeacher = "auto.tech1@yopmail.com";
            String canvasStudent8Science = "Auto.Sci8@yopmail.com";

            WebDriver driver = getWebDriver();

            studentDashboard = PageFactory.initElements(driver, StudentDashboard.class);
            assignments = PageFactory.initElements(driver, Assignments.class);

            //testda@snapwiz.com da is present in the same district of instructor (canvas checkbox is checked)
          /*  new SignUp().teacher(appendChar, dataIndex);//Sign up as an instructor1
            new School().enterAndSelectSchool("654321369", dataIndex, false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode1 = new Classes().createClass(appendChar, dataIndex);//Create class
            new Navigator().logout();//log out*/
/*
            driver.get(Config.baseURL);
            new Navigator().studentSignUp();//Navigate to student registration page
            new TextSend().textsendbyid("Auto Sci Stu8", "first-name"); //first name
            new TextSend().textsendbyid(classCode1, "class-code"); //class code
            new TextSend().textsendbyid(canvasStudent8Science, "user-email"); //email
            new TextSend().textsendbyid("snapwiz", "user-password"); //password
            new TextSend().textsendbyid("snapwiz", "retype-password"); // retype password
            driver.findElement(By.xpath("//button[@type='submit' and @mode='student']")).click();//click on sign up button
            new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("navbar-username")));
            ReportUtil.log("Student Registration using class code", "Student registration is successfully completed", "pass");
            new Navigator().logout();//log out*/

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as instructor
            new Assignment().create(dataIndex, "truefalse");//Create assessment
            new Assignment().addQuestion(dataIndex, "truefalse");
            new Assignment().addQuestion(dataIndex, "truefalse");
            new Assignment().assignToStudent(dataIndex, appendChar);//Assign to class
            new Navigator().logout();

            new Login().directLoginAsStudent(4,canvasStudent8Science);
            new ExplicitWait().explicitWaitByCssSelector(".as-title.twoline-ellipsis", 10);
            Thread.sleep(3000);
            List<WebElement> allAssignment = driver.findElements(By.cssSelector(".as-title.twoline-ellipsis"));
            for (int i =0;i<allAssignment.size(); i++) {
                if (allAssignment.get(i).getText().contains(assessmentname)) {
                    allAssignment.get(i).click();//click on Assignment
                    break;
                }
            }
            Thread.sleep(2000);
            new AttemptQuestion().trueFalse(true,"correct", dataIndex);
            driver.findElement(By.id("as-take-next-question")).click();//click on next
            Thread.sleep(2000);
            new AttemptQuestion().trueFalse(true,"incorrect", dataIndex);
            driver.findElement(By.id("as-take-next-question")).click();//click on next
            Thread.sleep(2000);
            new AttemptQuestion().trueFalse(true,"skip", dataIndex);
            driver.findElement(By.id("as-take-next-question")).click();//click on next
            new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'btn sty-blue submit')]")));
            driver.findElement(By.xpath("//span[contains(@class,'btn sty-blue submit')]")).click();//click on Submit
            Thread.sleep(2000);
            new Performance().backArrow.click();
            Thread.sleep(2000);
            new Navigator().logout();

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as instructor
            syncWithClass(1,1);
            new Navigator().logout();

            new Login().directLoginAsStudent(4,canvasStudent8Science);
            new Navigator().navigateTo("assignment");
            assignments.assignment.click();
            Thread.sleep(1000);
            //verify percentage at assignment summary
            CustomAssert.assertEquals(assignmentSummary.percentage.get(0).getText(),"33","Verify percentage on assignment summary","Percentage is displayed as expected","Percentage is not displayed as expected");

            assignmentSummary.button_continue.click();//Click on continue button
            Thread.sleep(3000);
            CustomAssert.assertEquals(yourResponse.scoreByQuestion.get(0).getAttribute("value"),"1", "All answer of student should be preserved", "Score is displaying", "Score is not displaying");
            new Navigator().logout();

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            assignments.viewResponse.get(0).click();//Click on view response
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button[class='btn gradebook-left-btn as-live-print-wrapper']")));
            CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(1).getText(), "33.33%", "Verify the percentage", "Percentage is displayed as expected", "Percentage is not displayed as expected");
            CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(0).getText(), "1/3", "Verify the score on student card", "Score is displayed as expected", "Score is not displayed as expected");
            studentResponse.viewDetailedResponse.get(0).click();//Click on view detailed responses
            Thread.sleep(2000);
            WebDriverUtil.scrollIntoView(driver.findElement(By.xpath("(//div[@id='view-user-question-performance-score-box'])[1]")),false);
            actual = driver.findElement(By.xpath("(//div[@id='view-user-question-performance-score-box'])[1]")).getText();
            CustomAssert.assertEquals(actual,"1","Verify score","Score is as per expected","Score is not as per expected");
            Thread.sleep(2000);
            WebDriverUtil.scrollIntoView(driver.findElement(By.xpath("(//div[@id='view-user-question-performance-score-box'])[2]")),false);
            actual = driver.findElement(By.xpath("(//div[@id='view-user-question-performance-score-box'])[2]")).getText();
            CustomAssert.assertEquals(actual,"0","Verify score","Score is as per expected","Score is not as per expected");
            Thread.sleep(2000);
            WebDriverUtil.scrollIntoView(driver.findElement(By.xpath("(//div[@id='view-user-question-performance-score-box'])[3]")),false);
            actual = driver.findElement(By.xpath("(//div[@id='view-user-question-performance-score-box'])[3]")).getText();
            CustomAssert.assertEquals(actual,"0","Verify score","Score is as per expected","Score is not as per expected");
            Thread.sleep(2000);
            //navigate to report
            new Navigator().navigateTo("premiumReports");
            myReports.singleAssessmentReport.click();
            actual = myReports.averagePerformance.get(0).getText();
            CustomAssert.assertEquals(actual,assessmentname+" | Average Performance : 33%","Verify average performance","Average performance is as per expected","Average performance is not as per expected");

        }catch (Exception e) {
            Assert.fail("Exception in 'verifyAssignmentReportsForMergedStudent' in 'CanvasSync' method", e);

        }
    }

    @Test(priority = 5, enabled = true)
    public  void verifyDeleteOfCanvasSyncStudentFromApplicaton(){
        try {

            int dataIndex = 5;
            //String appendChar = "ayn";
            String appendChar = "a" + StringUtil.generateRandomString(2, StringUtil.Mode.ALPHA);

            String canvasClassroomTeacher = "auto.tech1@yopmail.com";
            String canvasStudent3Science = "Auto.Sci3@yopmail.com";

            WebDriver driver = getWebDriver();

            studentDashboard = PageFactory.initElements(driver, StudentDashboard.class);
            assignments = PageFactory.initElements(driver, Assignments.class);

            //testda@snapwiz.com da is present in the same district of instructor (canvas checkbox is checked)

            new SignUp().teacher(appendChar, dataIndex);//Sign up as an instructor1
            new School().enterAndSelectSchool("654321369", dataIndex, false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode1 = new Classes().createClass(appendChar, dataIndex);//Create class

            //canvas sync with class
            syncWithClass(1,2);

            //delete the student
            WebDriverUtil.clickOnElementUsingJavascript(manageClass.checkBoxSelectStudent.get(0));//Select canvas student3
            manageClass.getMore().click();//click on more
            manageClass.changePassword.click();//Click on change password
            manageClass.textBox_resetPassword.get(0).sendKeys("snap2016wiz");//Enter new password
            manageClass.textBox_resetPassword.get(1).sendKeys("snap2016wiz");//Confirm password
            manageClass.button_reset.click();//Click on reset button
            Thread.sleep(8000);
            manageClass.checkBoxSelectStudent.get(0).click();//Select canvas student7
            manageClass.checkBoxSelectStudent.get(0).click();//Select canvas student7
            manageClass.getMore().click();//click on more
            manageClass.deleteSelectedStudent.click();//click on delete selected student
            Thread.sleep(1000);
            manageClass.deleteTextBox.sendKeys("REMOVE");
            manageClass.yesButtonOnDeletePopUp.click();//click on yes
            Thread.sleep(3000);
            actual = manageClass.currentStudentCount.getText();
            CustomAssert.assertEquals(actual, "1", "Verify current student count", "Current student count is 1", "Student is not deleted from class");
            new Navigator().logout();//Instructor log out

            //Verify login of canvas sync student through Edulastic Credentials(Student can able to sign in because he is part of multiple class section)
            new Login().directLoginAsInstructor(dataIndex,canvasStudent3Science,"snap2016wiz");//Login as canvas st7 in edu app after instructor deleted
            CustomAssert.assertEquals(studentDashboard.heading_dashboard.getText(),"Dashboard","Verify the canvas student log in in edu app","Canvas student is logged in as expected","Canvas student is not logged in");
            new Navigator().logout();//Instructor log out

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as instructor
            new Navigator().navigateTo("manageclass");//Navigate to manage class
            manageClass.syncWithCanvas.click();//click on sync with canvas
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.syncButton, 900);
            manageClass.syncButton.click();
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.closeButton, 20);
            manageClass.closeButton.click();
            Thread.sleep(3000);
            actual = manageClass.currentStudentCount.getText();
            CustomAssert.assertEquals(actual, "2", "Verify current student count", "Current student count is 2", "Student is not added again to the class");

        }catch (Exception e) {
            Assert.fail("Exception in 'verifyDeleteOfCanvasSyncStudentFromApplication' in 'CanvasSync' method", e);

        }
    }


    @Test(priority = 6, enabled = false)
    public  void verifyOnGoingAssignmentWhenCanvasClassCodeIsChanged(){
        try {

            int dataIndex = 6;
            //String appendChar = "atp";
            String appendChar = "a" + StringUtil.generateRandomString(2, StringUtil.Mode.ALPHA);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            String canvasClassroomTeacher = "auto.tech1@yopmail.com";
            String canvasStudent7Science = "Auto.Sci7@yopmail.com";
            String canvasStudent2Math = "Auto.Student2@yopmail.com";
            String canvasClassroomUrl = "https://edulastic.instructure.com";

            WebDriver driver = getWebDriver();

            studentDashboard = PageFactory.initElements(driver, StudentDashboard.class);
            assignments = PageFactory.initElements(driver, Assignments.class);

            //testda@snapwiz.com da is present in the same district of instructor (canvas checkbox is checked)
            new SignUp().teacher(appendChar, dataIndex);//Sign up as an instructor1
            new School().enterAndSelectSchool("654321369", dataIndex, false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode1 = new Classes().createClass(appendChar, dataIndex);//Create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode1, dataIndex);//Sign up as a student
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as instructor
            syncWithClass(0,0);
            actual = manageClass.studentTable.get(2).getText();
            CustomAssert.assertEquals(actual,canvasStudent2Math,"Verify student ","Student is added","Student is not added successfully");

            new Assignment().create(dataIndex, "truefalse");//Create assessment
            new Assignment().assignToStudent(dataIndex, appendChar);//Assign to class
            new Navigator().navigateTo("assignment");
            actual = assignments.sectionCount.get(0).getText();
            CustomAssert.assertEquals(actual,"2","Verify current assigned count","Assigned count is 2","Assigned count is not 2");
            new Navigator().logout();
            //Assignment in progress
            new Login().loginAsStudent(appendChar,dataIndex);//Login as an instructor
            new Assignment().openAssignment(dataIndex);
            takeAssignment.closeIcon.click();//click on close icon
            Thread.sleep(2000);
            driver.findElement(By.cssSelector(".as-modal-yes-btn")).click();
            new Navigator().logout();

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as instructor
            //canvas sync with class
            new Navigator().navigateTo("manageclass");//Navigate to manage class
            manageClass.syncWithCanvas.click();//click on sync with canvas
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.syncButton, 900);
            manageClass.changeCanvasCourse.click();
            Thread.sleep(1000);
            manageClass.courseDropDown.get(1).click();
            Thread.sleep(1000);
            manageClass.selectCourse.get(1).click();
            Thread.sleep(4000);
            manageClass.courseDropDown.get(2).click();
            Thread.sleep(3000);
            manageClass.selectSection.get(1).click();
            Thread.sleep(1000);
            manageClass.syncButton.click();
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.yesOnSyncPopUp, 20);
            manageClass.yesOnSyncPopUp.click();//click on yes
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.closeButton, 70);
            manageClass.closeButton.click();
            Thread.sleep(3000);
            //Canvas sync should get completed and new students should be displayed in the list
            actual = manageClass.currentStudentCount.getText();
            CustomAssert.assertEquals(actual, "2", "Verify current student count", "Current student count is 2", "Student is not added to the class");
            actual = manageClass.studentTable.get(7).getText();
            CustomAssert.assertEquals(actual,canvasStudent7Science,"Verify student after changing class","Student is added","Student is not added after changing of class code");

            //Assigned Count should get new count i.e. total students present in class
            new Navigator().navigateTo("assignment");
            actual = assignments.sectionCount.get(0).getText();
            CustomAssert.assertEquals(actual,"3","Verify current assigned count","Assigned count is 3","Assigned count is not 3");

            //share the assignment with class
            shareTheAssignmentWithClass();
            Thread.sleep(4000);
            //Performance Summary and Student Responce should be blank
            WebDriverUtil.clickOnElementUsingJavascript(assignments.viewResponse.get(0));//click on view response
            WebDriverUtil.waitTillVisibilityOfElement(studentResponse.scoreAndPercentageOnStudentCard.get(0),70);
            CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(1).getText(), "0%", "Verify the percentage", "Percentage is displayed as expected", "Percentage is not displayed as expected");
            CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(0).getText(), "0/1", "Verify the score on student card", "Score is displayed as expected", "Score is not displayed as expected");
            CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(3).getText(), "0%", "Verify the percentage", "Percentage is displayed as expected", "Percentage is not displayed as expected");
            CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(2).getText(), "0/1", "Verify the score on student card", "Score is displayed as expected", "Score is not displayed as expected");
            CustomAssert.assertEquals(studentResponse.performanceMasteryPercentage.get(0).getText(),"0%","verify average score","Average score is as per expected","Not displaying percentage properly");

            new Navigator().logout();//Log out

            new Login().loginToCanvasClassRoom(dataIndex,canvasStudent7Science);
            clickOnAssignmentsInCanvas();
            int size1 = canvasClassRoom.assignmentName.size()-1;
            canvasClassRoom.assignmentName.get(size1).click();
            canvasClassRoom.loadRegressionInNewWindow.click();//click on load assignment in a new window

            //No login should be required in Edulastic when student is navigated
            String parentWindow = driver.getWindowHandle();
            WebDriverUtil.switchToNewWindow();
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.className("true-false-student-content-text")),900);
            List<WebElement> allOptions1 = driver.findElements(By.className("true-false-student-content-text"));
            int index1 = 0;
            for(WebElement option : allOptions1)
            {
                if(option.getText().equals("True"))
                {
                    break;
                }
                index1++;
            }
            new Click().clickbylist("true-false-student-answer-label", index1);//click on correct option
            driver.findElement(By.id("as-take-next-question")).click();//click on next
            new WebDriverWait(driver,20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'btn sty-blue submit')]")));
            driver.findElement(By.xpath("//span[contains(@class,'btn sty-blue submit')]")).click();//click on Submit
            Thread.sleep(2000);
            performance.backArrow.click();//click on back arrow
            new Navigator().logout();//log out
            driver.switchTo().window(parentWindow);
            new Login().canvasLogout();

            new Login().loginAsStudent(appendChar,dataIndex);//Login as an instructor
            new Assignment().submitAssignment(6);
            performance.backArrow.click();//click on back arrow
            new Navigator().logout();

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as instructor
            new Navigator().navigateTo("assignment");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.viewResponse.get(0));//click on view response
            WebDriverUtil.waitTillVisibilityOfElement(studentResponse.scoreAndPercentageOnStudentCard.get(0),70);
            CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(3).getText(), "100%", "Verify the percentage", "Percentage is displayed as expected", "Percentage is not displayed as expected");
            CustomAssert.assertEquals(studentResponse.scoreAndPercentageOnStudentCard.get(2).getText(), "1/1", "Verify the score on student card", "Score is displayed as expected", "Score is not displayed as expected");
            CustomAssert.assertEquals(studentResponse.performanceMasteryPercentage.get(0).getText(),"100%","verify average score","Average score is as per expected","Not displaying percentage properly");

           /* new Navigator().navigateTo("premiumReports");
            myReports.singleAssessmentReport.click();
            actual = myReports.averagePerformance.get(0).getText();
            CustomAssert.assertEquals(actual,assessmentname+" | Average Performance : 100%","Verify average performance","Average performance is as per expected","Average performance is not as per expected");
*/

        }catch (Exception e) {
            Assert.fail("Exception in 'verifyOnGoingAssignmentWhenCanvasClassCodeIsChanged' in 'CanvasSync' method", e);

        }
    }


    @Test(priority = 7, enabled = true)
    public  void verifyCanvasClassSyncWhenStudentIsDeletedFromTheCanvasClass(){
        try {

            int dataIndex = 7;
            //String appendChar = "api";
            String appendChar = "a" + StringUtil.generateRandomString(2, StringUtil.Mode.ALPHA);

            String canvasClassroomTeacher = "auto.tech1@yopmail.com";
            String canvasStudent3Science = "auto.sci3@yopmail.com";
            String canvasClassroomUrl = "https://edulastic.instructure.com";

            WebDriver driver = getWebDriver();

            String assignmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            studentDashboard = PageFactory.initElements(driver, StudentDashboard.class);
            assignments = PageFactory.initElements(driver, Assignments.class);
            String methodName = new Exception().getStackTrace()[0].getMethodName();

            //testda@snapwiz.com da is present in the same district of instructor (canvas checkbox is checked)
            new SignUp().teacher(appendChar, dataIndex);//Sign up as an instructor1
            new School().enterAndSelectSchool("654321369", dataIndex, false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode1 = new Classes().createClass(appendChar, dataIndex);//Create class
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as instructor
            syncWithClass(1,2);
            new Navigator().logout();//Log out

            driver.get(canvasClassroomUrl);
            new Login().canvasLogout();

            new Login().loginToCanvasClassRoom(dataIndex,canvasClassroomTeacher);
            canvasClassRoom.classes.get(1).click();//click on "Automation science class"
            canvasClassRoom.people.click();//click on people from menu
            canvasClassRoom.dropdownArrow.get(1).click();//click on right dropdown
            Thread.sleep(5000);
            canvasClassRoom.removeFromCourse.get(1).click();//click on remove from course
            Thread.sleep(3000);
            driver.switchTo().alert().accept();
            Thread.sleep(3000);
            boolean deletedStudent =WebDriverUtil.isElementPresent(By.xpath("//*[text()='Student3']"));
            CustomAssert.assertEquals(deletedStudent, false, "Verify student3", "Student3 is not present", "Student3 is present");
            new Login().canvasLogout();

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as instructor
            new Navigator().navigateTo("manageclass");//Navigate to manage class
            Thread.sleep(200);
            actual = manageClass.studentTable.get(2).getText();
            CustomAssert.assertEquals(actual,canvasStudent3Science,"Verify student3","Student3 is present","Student3 is deleted");
            manageClass.checkBoxSelectStudent.get(0).click();//Select canvas student3

            manageClass.getMore().click();//click on more
            manageClass.changePassword.click();//Click on change password
            manageClass.textBox_resetPassword.get(0).sendKeys("snap2016wiz");//Enter new password
            manageClass.textBox_resetPassword.get(1).sendKeys("snap2016wiz");//Confirm password
            manageClass.button_reset.click();//Click on reset button
            new Navigator().logout();

            new Login().directLoginAsInstructor(dataIndex,canvasStudent3Science,"snap2016wiz");//Login as canvas st3 in edu app after instructor deleted from canvas
            CustomAssert.assertEquals(studentDashboard.heading_dashboard.getText(),"Dashboard","Verify the canvas student log in in edu app","Canvas student is logged in as expected","Canvas student is not logged in");
            new Navigator().logout();//Instructor log out

            //Student should be able to login to canvas but course will not be displayed
            new Login().loginToCanvasClassRoom(dataIndex,canvasStudent3Science);
            if(!canvasClassRoom.noCourseNotificationMessage.getText().contains("You don't have any courses, so this page won't be very exciting for now.")){
               CustomAssert.fail("Verify no course notification message","Course is displaying");
            }
            new Login().canvasLogout();

           //Deleted student should get removed from list of students
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as instructor
            new Navigator().navigateTo("manageclass");//Navigate to manage class
            Thread.sleep(1000);
            manageClass.syncWithCanvas.click();//click on sync with canvas
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.syncButton, 60);
            manageClass.syncButton.click();
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.closeButton, 20);
            manageClass.closeButton.click();
            Thread.sleep(3000);
            boolean deletedStudent1 =WebDriverUtil.isElementPresent(By.xpath("//*[text()='Student3']"));
            CustomAssert.assertEquals(deletedStudent1, false, "Verify student3", "Student3 is not present", "Student3 is present");
            new Navigator().logout();

            new Login().directLoginAsInstructor(dataIndex,canvasStudent3Science,"snap2016wiz");//Login as canvas st3 in edu app after instructor deleted from canvas
            new Navigator().navigateTo("manageclass");//Navigate to manage class
            CustomAssert.assertEquals(driver.findElement(By.xpath("//span[text()='Not Enrolled']")).isDisplayed(),true,"Verify deleted class","Class is not displaying from which student has been removed","Class is displaying from which student has been removed");
            new Navigator().logout();

            //add student3
            new Login().loginToCanvasClassRoom(dataIndex,canvasClassroomTeacher);
            canvasClassRoom.classes.get(1).click();//click on "Automation science class"
            canvasClassRoom.people.click();//click on people from menu
            canvasClassRoom.addPeople.click();//click on +people
            canvasClassRoom.userListTextArea.sendKeys(canvasStudent3Science);
            canvasClassRoom.nextButton.click();//click on next
            canvasClassRoom.addUser.click();//click on add users
            WebDriverUtil.waitTillVisibilityOfElement(canvasClassRoom.doneButton,50);
            canvasClassRoom.doneButton.click();//click on done
            Thread.sleep(3000);
            new Login().canvasLogout();

           //Login as student3 and accept invitation
            new Login().loginToCanvasClassRoom(dataIndex,canvasStudent3Science);
            canvasClassRoom.accept.click();//click on accept
            Thread.sleep(3000);
            new Login().canvasLogout();

            //Login as instructor and Deactivate student3
            new Login().loginToCanvasClassRoom(dataIndex,canvasClassroomTeacher);
            canvasClassRoom.classes.get(1).click();//click on "Automation science class"
            canvasClassRoom.people.click();//click on people from menu
            canvasClassRoom.dropdownArrow.get(1).click();//click on right dropdown
            Thread.sleep(5000);
            canvasClassRoom.deactivateUser.get(1).click();//click on deactivate user
            Thread.sleep(3000);
            driver.switchTo().alert().accept();
            Thread.sleep(3000);
            actual = canvasClassRoom.inactive.getText();
            CustomAssert.assertEquals(actual,"inactive","Verify inactive user student3","Student3 is inactive","Student3 is not inactive");
            new Login().canvasLogout();

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as instructor
            new Navigator().navigateTo("manageclass");//Navigate to manage class
            Thread.sleep(1000);
            manageClass.syncWithCanvas.click();//click on sync with canvas
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.syncButton, 60);
            manageClass.syncButton.click();
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.closeButton, 20);
            manageClass.closeButton.click();
            Thread.sleep(3000);
            boolean deletedStudent3 =WebDriverUtil.isElementPresent(By.xpath("//*[text()='Student3']"));
            CustomAssert.assertEquals(deletedStudent3, false, "Verify student3", "Student3 is not present", "Student3 is present");
            new Navigator().logout();

            new Login().loginToCanvasClassRoom(dataIndex,canvasClassroomTeacher);
            canvasClassRoom.classes.get(1).click();//click on "Automation science class"
            canvasClassRoom.people.click();//click on people from menu
            canvasClassRoom.dropdownArrow.get(1).click();//click on right dropdown
            Thread.sleep(5000);
            canvasClassRoom.reactivateUser.get(0).click();//click on reactivate user
            Thread.sleep(3000);
            //driver.switchTo().alert().accept();
            Thread.sleep(3000);
            new Login().canvasLogout();

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as instructor
            new Navigator().navigateTo("manageclass");//Navigate to manage class
            Thread.sleep(1000);
            manageClass.syncWithCanvas.click();//click on sync with canvas
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.syncButton, 60);
            manageClass.syncButton.click();
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.closeButton, 20);
            manageClass.closeButton.click();
            Thread.sleep(3000);
            actual = manageClass.studentTable.get(2).getText();
            CustomAssert.assertEquals(actual,canvasStudent3Science,"Verify student3","Student3 is present","Student3 is deleted");
            manageClass.checkBoxSelectStudent.get(1).click();//Select canvas student3
            new Navigator().logout();

            new Login().loginToCanvasClassRoom(dataIndex,canvasClassroomTeacher);
            canvasClassRoom.classes.get(1).click();//click on "Automation math class"
            canvasClassRoom.people.click();//click on people from menu
            driver.findElement(By.xpath("//tbody/tr[2]/td[2]")).click();//click on student3
            canvasClassRoom.moreUserDetails.click();//click on more user details
            Thread.sleep(2000);
            canvasClassRoom.concludeEnrollment.click();//conclude enrollment
            Thread.sleep(3000);
            driver.switchTo().alert().accept();
            Thread.sleep(3000);
            new Login().canvasLogout();

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as instructor
            new Navigator().navigateTo("manageclass");//Navigate to manage class
            Thread.sleep(1000);
            manageClass.syncWithCanvas.click();//click on sync with canvas
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.syncButton, 60);
            manageClass.syncButton.click();
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.closeButton, 20);
            manageClass.closeButton.click();
            Thread.sleep(3000);
            boolean deletedStudentAfterConclude =WebDriverUtil.isElementPresent(By.xpath("//*[text()='Student3']"));
            CustomAssert.assertEquals(deletedStudentAfterConclude, false, "Verify student3", "Student3 is not present", "Student3 is present");
            new Navigator().logout();

            new Login().loginToCanvasClassRoom(dataIndex,canvasClassroomTeacher);
            canvasClassRoom.classes.get(1).click();//click on "Automation science class"
            canvasClassRoom.people.click();//click on people from menu
            canvasClassRoom.addPeople.click();//click on +people
            canvasClassRoom.userListTextArea.sendKeys(canvasStudent3Science);
            canvasClassRoom.nextButton.click();//click on next
            canvasClassRoom.addUser.click();//click on add users
            WebDriverUtil.waitTillVisibilityOfElement(canvasClassRoom.doneButton,50);
            canvasClassRoom.doneButton.click();//click on done
            Thread.sleep(3000);
            new Login().canvasLogout();

            //Login as student3 and accept invitation
            new Login().loginToCanvasClassRoom(dataIndex,canvasStudent3Science);
            canvasClassRoom.accept.click();//click on accept
            Thread.sleep(3000);
            new Login().canvasLogout();

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as instructor
            new Navigator().navigateTo("manageclass");//Navigate to manage class
            Thread.sleep(1000);
            manageClass.syncWithCanvas.click();//click on sync with canvas
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.syncButton, 60);
            manageClass.syncButton.click();
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.closeButton, 20);
            manageClass.closeButton.click();
            Thread.sleep(3000);
            actual = manageClass.studentTable.get(2).getText();
            CustomAssert.assertEquals(actual,canvasStudent3Science,"Verify student3","Student3 is present","Student3 is deleted");
            new Navigator().logout();

        }catch (Exception e) {
            Assert.fail("Exception in 'verifyCanvasClassSyncWhenStudentIsDeletedFromTheCanvasClass' in 'CanvasSync' method", e);

        }
    }

    @Test(priority = 8, enabled = true)
    public  void verifyLoginToEduasticWithCanvasStudent(){
        try {

            int dataIndex = 8;
            //String appendChar = "aQT";
            String appendChar = "a" + StringUtil.generateRandomString(2, StringUtil.Mode.ALPHA);

            String canvasClassroomTeacher = "auto.tech1@yopmail.com";
            String canvasGmailStudent = "snaplogic.automation22@gmail.com";
            String canvasClassroomUrl = "https://edulastic.instructure.com";
            String password = "snapwiz2015";
            WebDriver driver = getWebDriver();

            String assignmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            studentDashboard = PageFactory.initElements(driver, StudentDashboard.class);
            assignments = PageFactory.initElements(driver, Assignments.class);
            String methodName = new Exception().getStackTrace()[0].getMethodName();

            //testda@snapwiz.com da is present in the same district of instructor (canvas checkbox is checked)
            new SignUp().teacher(appendChar, dataIndex);//Sign up as an instructor1
            new School().enterAndSelectSchool("654321369", dataIndex, false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode1 = new Classes().createClass(appendChar, dataIndex);//Create class
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as instructor
            syncWithClass(1,2);
            new Navigator().logout();//Log out

            driver.get(Config.baseURL);
            driver.findElement(By.id("google-openid-login")).click();//click on sign in with google
            signIn.getTextField_email().sendKeys(canvasGmailStudent);// Type Email
            signIn.buttonNext.get(0).click();
            signIn.getTextField_password().sendKeys(password);//Type Password
            signIn.getButton_SignIn().click();// Click button 'Sign in'
            Thread.sleep(5000);
            new Navigator().navigateTo("manageclass");
            CustomAssert.assertEquals(studentDashboard.heading_dashboard.getText(),"Manage Class","Verify the canvas math student2 log in in edu app","canvas math student2 is logged in as expected","canvas math student2 is not logged in");
            actual = manageClass.classTitle.get(0).getText();
            System.out.println(actual);
          /*  expected = methodName +"Classclass"+appendChar;
            System.out.println(expected);

            if(!actual.equals(expected)){
                CustomAssert.fail("Verify class","Student sign in to the same class");
            }*/
            new Navigator().logout();

        }catch (Exception e) {
            Assert.fail("Exception in 'verifyLoginToEduasticWithCanvasStudent' in 'CanvasSync' method", e);

        }
    }


    public void syncWithClass(int course,int section){
        try{
            new Navigator().navigateTo("manageclass");//Navigate to manage class
            manageClass.syncWithCanvas.click();//click on sync with canvas
            String parentWindow = driver.getWindowHandle();
            WebDriverUtil.switchToNewWindow();
            Thread.sleep(3000);
            new Login().directLoginToCanvasClassRoom(1);
            Thread.sleep(9000);
            driver.switchTo().window(parentWindow);
            Thread.sleep(6000);
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.courseDropDown.get(1), 300);
            manageClass.courseDropDown.get(1).click();
            Thread.sleep(1000);
            manageClass.selectCourse.get(course).click();
            Thread.sleep(4000);
            manageClass.courseDropDown.get(2).click();
            Thread.sleep(3000);
            manageClass.selectSection.get(section).click();
            Thread.sleep(1000);
            manageClass.syncButton.click();
            WebDriverUtil.waitTillVisibilityOfElement(manageClass.closeButton, 20);
            manageClass.closeButton.click();
            Thread.sleep(3000);

        }catch(Exception e){
            Assert.fail("Exception in syncWithClass method",e);

        }
    }


    public void clickOnAssignmentsInCanvas(){
        try{

            canvasClassRoom.classes.get(0).click();//click on "Automation math class"
            canvasClassRoom.assignments_Menu.click();//click on assignments from menu

        }catch (Exception e){
            Assert.fail("Exception in clickOnAssignmentsInCanvas method",e);
        }
    }

    public void shareTheAssignmentWithClass(){
        try{
            new Navigator().navigateTo("assignment");//navigate to assignments
            assignments.more.click();//Click on more
            assignments.canvasIcon.click();//click on canvas icon
            Thread.sleep(3000);
            assignments.shareButton.click();//click on share
            Thread.sleep(3000);
            assignments.closeButton.click();//click on close button

        }catch (Exception e){
            Assert.fail("Exception in shareTheAssignmentWithClass method",e);
        }
    }


}