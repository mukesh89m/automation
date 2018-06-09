package com.snapwiz.selenium.tests.staf.learningspaces.testcases.smoke.updateAssignment;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.CreateCustomAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.AssignmentTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * Created by Dharaneesha on 3/8/16.
 */
public class UpdateAssignment extends Driver{

    @Test(priority = 1,enabled = true)
    public void updateDueDate(){
        try{
            ReportUtil.log("Updating due date while updating the assignment", "This Checks the updated due date is updated in Student & instructor side", "info");
            WebDriver driver=Driver.getWebDriver();
            String dataIndex = "2";
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver,AssignmentTab.class);
            NewAssignment newAssignment = PageFactory.initElements(driver,NewAssignment.class);
            Dashboard dashboard = PageFactory.initElements(driver,Dashboard.class);
            ReportUtil.log("Update Due date", "Updating the due date of an assignment in reassigning", "info");

            //1. Login as an instructor, create Custom Assignment & Assign to class Section
              new LoginUsingLTI().ltiLogin(dataIndex+"_1");//Login as a student 1
              new LoginUsingLTI().ltiLogin(dataIndex+"_2");//Login as a student 2
              new LoginUsingLTI().ltiLogin(dataIndex+"_3");//Login as a student 3
              new LoginUsingLTI().ltiLogin(dataIndex);
              new CreateCustomAssignment().assignByCreatingCustomAssignment("true", dataIndex);//Assign an assignment after creating Custom Assignment
              ReportUtil.log("Assigning the Custom Assignment", "Custom assignment is assigned to whole class sections", "info");

            //2.Click on Update Assignment & delete some questions
            //Expected - Instructor should be able to delete the questions
            currentAssignments.updateAssignmentButton.click();//Click on 'Update Assignment'
            Thread.sleep(3000);
            currentAssignments.getDeleteIcon().click();//Click on delete icon
            currentAssignments.yesLink.click();//Click on 'Yes' link
            Thread.sleep(3000);
            if(currentAssignments.getDeleteIcon().isDisplayed()){
                CustomAssert.fail("Delete Questions in update Assignment","Questions are not deleted while updating the custom Assignment");
            }
            /*"3. Click on 'Re_ASSIGN' button  & click on it
            4. Update the date & time"*/
            //Expected - Instructor should be able to update the date & time
            currentAssignments.getReassign_button().click();//Click on 'Re-Assign' button
            ReportUtil.log("Reassigning the Custom Assignment", "Custom assignment is reassigned by updating the due date to whole class sections", "info");
            Thread.sleep(5000);
            String dueDate = assignmentTab.dueDate.getAttribute("value");
            System.out.println("dueDate : " + dueDate);
            String accessibleDate =newAssignment.datePicker.getAttribute("value");
            System.out.println("accessibleDate : " + accessibleDate);
            assignmentTab.dueDate.click();//CLick on 'due date' text field
            currentAssignments.nextIcon.click();//Click on next icon
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.linkText("8")), 30);
            driver.findElement(By.linkText("8")).click();//Click on data '8'
            currentAssignments.getAssign().click();//Click on 'Assign' button
           // currentAssignments.yesLinkAfterAssign.click();
            Thread.sleep(5000);
            dueDate = currentAssignments.getOriginalDueDate().getText();
            if(!dueDate.contains("Aug 08, 2016,")){
                CustomAssert.fail("Updating date & time","Instructor is not able to update the date & time");
            }

            //5. Login as a student
            //Expected - Student should be able to view updated due date
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");//Login as a student
            ReportUtil.log("LTI login", "Student logged in", "info");
            String duedate = dashboard.upcomingTimeStamp.getText();
            if(!duedate.contains("Aug 08, 2016,")){
                CustomAssert.fail("View updated due date","Student is  not able to view updated due date");
            }
        }catch(Exception  e){
            Assert.fail("Exception in the test method 'updateDueDate' in class 'UpdateAssignment'", e);
        }
    }

    @Test(priority = 2,enabled = true)
    public void addUsersInAssignToField(){
        try{
            ReportUtil.log("Adding users while updating the assignment", "This Checks the users are added", "info");
            WebDriver driver=Driver.getWebDriver();
            String dataIndex = "5";
            //1. Login as an instructor, create Custom Assignment & Assign to a single Student
            new LoginUsingLTI().ltiLogin(dataIndex);
            new LoginUsingLTI().ltiLogin(dataIndex+"_1");//Login as a student 1
            new LoginUsingLTI().ltiLogin(dataIndex+"_2");//Login as a student 2
            new LoginUsingLTI().ltiLogin(dataIndex+"_3");//Login as a student 3

            new LoginUsingLTI().ltiLogin(dataIndex);//Log in as an instructor
            new CreateCustomAssignment().assignByCreatingCustomAssignment("true", dataIndex);//Assign an assignment after creating Custom Assignment
            ReportUtil.log("Update Due date", "Updating the due date of an assignment in reassigning", "info");

            new LoginUsingLTI().ltiLogin(dataIndex);//Log in as an instructor
            ReportUtil.log("LTI login", "Instructor logged in", "info");

            addStudents();//Adds the Students in 'Assign To' field
            ReportUtil.log("Adding Student", "Student 2 is added in 'Assign To' field while updating the assignment", "info");

            Thread.sleep(5000);
            checkAssignmentAvailability();//Checks whether the assignment is available or not in Current Assignment Page
            ReportUtil.log("Assignment in Current Assignment Page", "Assignment is available in Current assignment page", "info");

            new LoginUsingLTI().ltiLogin(dataIndex);//Log in as an instructor
            ReportUtil.log("LTI login", "Instructor logged in", "info");

            addStudents();//Adds the Students in 'Assign To' field
            ReportUtil.log("Adding Student", "Student 2 is added in 'Assign To' field while updating the assignment", "info");
            checkAssignmentAvailability();//Checks whether the assignment is available or not in Current Assignment Page
            ReportUtil.log("Assignment in Current Assignment Page", "Assignment is available in Current assignment page", "info");
        }catch(Exception  e){
            Assert.fail("Exception in the test method 'addUsersInAssignToField' in the class 'UpdateAssignment'", e);
        }
    }

    public void addStudents(){
        WebDriver driver=Driver.getWebDriver();
        try{
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);

            //2.Click on Update Assignment
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.updateAssignmentButton.click();//Click on 'Update Assignment'
            Thread.sleep(3000);
            if(currentAssignments.getDeleteIcon().isDisplayed()) {
                currentAssignments.getDeleteIcon().click();//Click on delete icon
                currentAssignments.yesLink.click();
                Thread.sleep(3000);
            }

            if(currentAssignments.getDeleteIcon().isDisplayed()){
                CustomAssert.fail("Delete Questions in update Assignment","Questions are not deleted while updating the custom Assignment");
            }

            //Click on 'Re_ASSIGN' button  & Add other Student
            currentAssignments.getReassign_button().click();
            Thread.sleep(5000);
            currentAssignments.extendDueDateField.sendKeys("Month");//Type first 3 letters of the student
            currentAssignments.autoSuggestElement.click();//Select the 2nd student
            currentAssignments.getAssign().click();//CLick on 'Assign' button
            //currentAssignments.yesLinkAfterAssign.click();//Click on 'Yes' link
        }catch(Exception e){
            Assert.fail("Exception in the method 'addStudents' in the class 'UpdateAssignment'",e);
        }
    }

    public void checkAssignmentAvailability(){
        try{
            WebDriver driver=Driver.getWebDriver();
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            boolean isAssignmentPresent = true;
            try{
                currentAssignments.assignment.click();
                isAssignmentPresent = false;
                Thread.sleep(7000);
            }catch(Exception e){

            }
            if(isAssignmentPresent ==true){
                CustomAssert.fail("Assignment Entry in Current Assignment Page","Assignment entry is not there in Current Assignment Page");
            }
        }catch(Exception e){
            Assert.fail("Exception in the method 'checkAssignmentAvailability' in the class 'UpdateAssignment'",e);
        }
    }

}
