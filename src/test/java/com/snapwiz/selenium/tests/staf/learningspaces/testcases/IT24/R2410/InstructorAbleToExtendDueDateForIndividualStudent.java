package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT24.R2410;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.TocSearch;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by rashmi on 27-11-2015.
 */
public class InstructorAbleToExtendDueDateForIndividualStudent extends Driver
{
    @Test(priority = 1, enabled = true)
    public  void  instructorAbleToExtendDueDateForIndividualStudent(){
        try {

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(10));


            new Assignment().create(10);
            new LoginUsingLTI().ltiLogin("10_1");// log in as stu1
            new LoginUsingLTI().ltiLogin("10_2");// log in as stu2
            new LoginUsingLTI().ltiLogin("10");// log in as instructor
            new Assignment().assignToStudent(10);
            new Navigator().NavigateTo("Current Assignments");
            Assert.assertEquals(currentAssignments.getUpdateAssignment_button().getText(), "| Update Assignment", "Update assignment link is not present");
            currentAssignments.getUpdateAssignment_button().click();// click on update assignment link
            currentAssignments.reAssign_link.click();//click on re-assign button.
            //update assignment pop-up should be open
            Assert.assertEquals(currentAssignments.extendDueDatePopUp.isDisplayed(), true, "Extend due date pop up did not open");

            //extend due date tab should be present
            Assert.assertEquals(currentAssignments.getExtendDueDate().getText(), "Extend Due Date", "Extend Due Date tab is not present");

            //update assignment tab should be available
            Assert.assertEquals(currentAssignments.UpdateAssignmentDetails.getText(), "Update Assignment Details", "Update Assignment Details tab is not present");

            currentAssignments.getDueDateOnAssignPopUp().click();// update assignment tab should be editable
            Thread.sleep(1000);
            currentAssignments.Next_Date_picker.click();
            Thread.sleep(2000);
            driver.findElement(By.linkText("6")).click();

            currentAssignments.getExtendDueDate().click();//click on extend due date
            //1. The assignment name should show on the top.
            Assert.assertEquals(currentAssignments.getAssignmentNameOnAssignPopUpList().get(1).getText(), assessmentname, " The assignment name is not available");

            currentAssignments.getNewDueDateField().click();
            Thread.sleep(2000);
            driver.findElement(By.linkText("1")).click();// select a earlier due date

            currentAssignments.getUpdateButtonInReassign_button().get(0).click();

            Assert.assertEquals(currentAssignments.invalidDatepickerValue.isDisplayed(), true, "validation is not handled");//1. The Date picker box should be changed to red outlined border.
            currentAssignments.getNewDueDateField().click();
            Thread.sleep(2000);
            currentAssignments.Next_Date_picker.click();
            driver.findElement(By.linkText("15")).click();
            currentAssignments.getUpdateButtonInReassign_button().get(0).click();

            String str= currentAssignments.updateNotificationMessage.getText();

            //1. A notification with text "The changes will be saved only for the current tab, Continue?" Yes| No(links) should appear.
            Assert.assertEquals(str,"The changes will be saved for the current tab only. Continue ? Yes | No","Update notification message is not displayed");

            currentAssignments.updateNotificationNoLink.click();
            currentAssignments.getUpdateButtonInReassign_button().get(0).click();

            currentAssignments.updateNotificationYesLink.click();
            Thread.sleep(1000);
            Assert.assertEquals(currentAssignments.getCurrentAssignmentTitle().getText(), "Current Assignments", "User is not navigated to current assignments page");//1. User should be navigated to the current Assignments page.
            new LoginUsingLTI().ltiLogin("10_1");//login as student
            new Navigator().NavigateTo("Assignments");//navigate to assignments page
            Assert.assertEquals(currentAssignments.DueDateHasBeenExtendedMessage.isDisplayed(),true,"Due date has not been extended");// verify whether due date has been extended



        }
        catch (Exception e) {
            Assert.fail("Exception in TC instructorAbleToExtendDueDateForIndividualStudent of class InstructorAbleToExtendDueDateForIndividualStudent", e);

        }
    }

    @Test(priority = 2, enabled = true)
    public void extendDueDateMultipleTimes()
    {
        try{
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(10));


            new LoginUsingLTI().ltiLogin("10");//Login as instructor
            new Navigator().NavigateTo("Current Assignments");
            for (int i = 0; i < 1; i++) {//extend due date 2 times

                currentAssignments.getUpdateAssignment_button().click();// click on update assignment link
                currentAssignments.reAssign_link.click();//click on re-assign button.
                currentAssignments.getExtendDueDate().click();//click on update assignment
                currentAssignments.getNewDueDateField().click();//click on date picker-month
                currentAssignments.Next_Date_picker.click();//click on next month arrow
                Thread.sleep(2000);
                driver.findElement(By.linkText("6")).click();//select date
                currentAssignments.getUpdateButtonInReassign_button().get(0).click();//click on re-assign button
                currentAssignments.updateNotificationYesLink.click();//click on Yes link in notification

            }

            String firstDueDate=currentAssignments.original_DueDate.get(0).getText();//get original due date

            String subFirstDueDate=firstDueDate.substring(4,6);//copy only the date from original due date
            Assert.assertEquals(subFirstDueDate,duedate,"Due date is changing");//1. The same default values for “due date” should be populated irrespective of how many times instructor extends due date for any student or the class-section.


        }catch (Exception e){
            Assert.fail("Exception in TC extendDueDateMultipleTimes of class InstructorAbleToExtendDueDateForIndividualStudent",e);

        }
    }

    @Test(priority = 3, enabled = true)
    public void verifyExtendDueDateWhenStudentHasStarted()
    {
        try{
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            new LoginUsingLTI().ltiLogin("10_1");//login as student-1
            new Navigator().NavigateTo("Assignments");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", assignments.assignmentName);
            Thread.sleep(1000);
            new LoginUsingLTI().ltiLogin("10");//login as instructor
            new Navigator().NavigateTo("Current Assignments");
            Assert.assertEquals(currentAssignments.getUpdateAssignment_button().getText(), "| Update Assignment", "Update assignment link is not present");
            currentAssignments.getUpdateAssignment_button().click();// click on update assignment link
            currentAssignments.reAssign_link.click();//click on re-assign button.
            Assert.assertEquals(currentAssignments.extendDueDatePopUp.isDisplayed(), true, "Extend due date pop up did not open");
            Assert.assertEquals(currentAssignments.getExtendDueDate().getText(), "Extend Due Date", "Extend Due Date tab is not present");
            Assert.assertEquals(currentAssignments.UpdateAssignmentDetails.getText(), "Update Assignment Details", "Update Assignment Details tab is not present");


        }catch (Exception e){
            Assert.fail("Exception in TC verifyExtendDueDateWhenStudentHasStarted of class InstructorAbleToExtendDueDateForIndividualStudent",e);
        }
    }


    @Test(priority = 4, enabled = true)
    public void extendDueDateTabUIWhenStudentHasNotStartedAdaptivePractice()
    {
        try{

            //tc row no 32
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);

            new LoginUsingLTI().ltiLogin("32_1");//login as student
            new LoginUsingLTI().ltiLogin("32");//login as instructor
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab
            lessonPage.practice_Arrow.click();//Click on practice arrow
            lessonPage.assignThis_link.click();//Click on assign this link
            new AssignLesson().assignTOCWithDefaultClassSection(32);//Assign to class
            updateLinkFunctionality(32);


        }catch (Exception e){
            Assert.fail("Exception in TC extendDueDateTabUIWhenStudentHasNotStartedAdaptivePractice of class InstructorAbleToExtendDueDateForIndividualStudent",e);
        }
    }


    @Test(priority = 5, enabled = true)
    public void extendDueDateTabUIWhenStudentHasNotStartedFileBased()
    {
        try{

            //tc row no 32

            new LoginUsingLTI().ltiLogin("32");//login as instructor
            new Assignment().createFileBasedAssessmentAtInstructorSide(32);
            new Assignment().assignFileBasedAssignmentFromMyQuestionBank(32);
            updateLinkFunctionality(32);

        }catch (Exception e){
            Assert.fail("Exception in TC extendDueDateTabUIWhenStudentHasNotStartedFileBased of class InstructorAbleToExtendDueDateForIndividualStudent",e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void extendDueDateTabUIWhenStudentHasNotStartedDW()
    {
        try{

            //tc row no 32
            new LoginUsingLTI().ltiLogin("32");//login as instructor
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().openLessonWithDiscussionWidget();
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(32);
            updateLinkFunctionality(33);

        }catch (Exception e){
            Assert.fail("Exception in TC extendDueDateTabUIWhenStudentHasNotStartedDW of class InstructorAbleToExtendDueDateForIndividualStudent",e);
        }
    }

    @Test(priority = 7, enabled = true)
    public void extendDueDateTabUIWhenStudentHasNotStartedCartType()
    {
        try{

            //tc row no 32
            new LoginUsingLTI().ltiLogin("32");//login as instructor
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TopicOpen().chapterOpen(0);
            new AssignLesson().assignAllLesson("32");//assign all lesson
            updateLinkFunctionality(34);

        }catch (Exception e){
            Assert.fail("Exception in TC extendDueDateTabUIWhenStudentHasNotStartedCartType of class InstructorAbleToExtendDueDateForIndividualStudent",e);
        }
    }

    @Test(priority = 8, enabled = true)
    public void extendDueDateTabUIWhenStudentHasNotStartedResource()
    {
        try{

            //tc row no 32
            new LoginUsingLTI().ltiLogin("32");//login as instructor
            new UploadResources().uploadResources("32", true, false, true);//upload chapterlevel resource
            new AssignLesson().assignResourceFromMyResources(32);
            updateLinkFunctionality(35);

        }catch (Exception e){
            Assert.fail("Exception in TC extendDueDateTabUIWhenStudentHasNotStartedResource of class InstructorAbleToExtendDueDateForIndividualStudent",e);
        }
    }
    @Test(priority = 9, enabled = true)
    public void extendDueDateTabUIWhenStudentHasNotStartedActivity()
    {
        try{

            //tc row no 32
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);
            new LoginUsingLTI().ltiLogin("32");//login as instructor
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(0);
            Thread.sleep(2000);
            new SelectCourse().selectInvisibleAssignment("Introduction");
            //new TOCShow().tocHide();
            Thread.sleep(3000);
            new AddCart().widgetaddtocart();//add image widget to cart
            new Navigator().navigateToTab("Resources");
            lessonPage.arrow_resourceTab.click();
            lessonPage.addToActivity.click();
            new Click().clickByclassname("assignment-cart-wrapper");//click on assignment cart
            new AssignLesson().assigncart(32);
            Thread.sleep(3000);
            updateLinkFunctionality(35);

        }catch (Exception e){
            Assert.fail("Exception in TC extendDueDateTabUIWhenStudentHasNotStartedActivity of class InstructorAbleToExtendDueDateForIndividualStudent",e);
        }
    }




    public void updateLinkFunctionality(int dataIndex)
    {
        try {
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String resourceappendchar = ReadTestData.readDataByTagName("", "resourceappendchar", Integer.toString(dataIndex));

            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            if (System.getProperty("UCHAR") == null) {
                if (resourceappendchar!=null) {
                    if (resourceappendchar.equals("true")) {
                        assessmentname = assessmentname + LoginUsingLTI.appendChar;
                    } else {
                        assessmentname = assessmentname + System.getProperty("UCHAR");
                    }
                }
            }

        Assert.assertEquals(currentAssignments.getUpdateAssignment_button().getText(), "| Update Assignment", "Update assignment link is not present");
        currentAssignments.getUpdateAssignment_button().click();// click on update assignment link
        Assert.assertEquals(currentAssignments.extendDueDatePopUp.isDisplayed(), true, "Extend due date pop up did not open");
        Assert.assertEquals(currentAssignments.getExtendDueDate().getText(), "Extend Due Date", "Extend Due Date tab is not present");
        Assert.assertEquals(currentAssignments.UpdateAssignmentDetails.getText(), "Update Assignment Details", "Update Assignment Details tab is not present");
        currentAssignments.getDueDateOnAssignPopUp().click();// update assignment tab should be editable
        Thread.sleep(1000);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",currentAssignments.Next_Date_picker);//click on next button of date picker
        Thread.sleep(2000);
        driver.findElement(By.linkText("6")).click();//click on date
        currentAssignments.getExtendDueDate().click();//click on extend due date//click on extend due date tab
        Assert.assertEquals(currentAssignments.getAssignmentNameOnAssignPopUpList().get(1).getText(), assessmentname, " The assignment name is not available");
        currentAssignments.getNewDueDateField().click();//click on date picker
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",currentAssignments.prev_Date_picker);//click on previous button of date picker
        Thread.sleep(2000);
        driver.findElement(By.linkText("1")).click();// select a earlier due date
        currentAssignments.getUpdateButtonInReassign_button().get(0).click();//click on update button
        Assert.assertEquals(currentAssignments.invalidDatepickerValue.isDisplayed(), true, "validation is not handled");//1. The Date picker box should be changed to red outlined border.
        currentAssignments.closeCard.click();//click on 'x' on class section name
        Assert.assertEquals(currentAssignments.textInputField.getText(),"","class section card is not closed");
        List<WebElement> ele=  driver.findElements(By.className("maininput"));
        for( WebElement e:ele){
                if(e.isDisplayed()) {
                    e.sendKeys("las");
                    Assert.assertEquals(currentAssignments.autoSuggestName.getText(), "LastName_R2410_32_1, FirstName_R2410_32_1", "Student's <Lastname>, <Firstname> is not displaying in the Autosuggest.");
                    e.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                    e.sendKeys(Keys.BACK_SPACE);
                    e.sendKeys("stu");
                }

            }
        Assert.assertEquals(currentAssignments.autoSuggestName.getText(),"studtitle_IT24_R2410_32","instructor classsection name  is not displaying in the Autosuggest.");

        new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
        currentAssignments.getUpdateAssignment_button().click();// click on update assignment link
        currentAssignments.getExtendDueDate().click();//click on extend due date
        List<WebElement> ele2=  driver.findElements(By.className("maininput"));
        for( WebElement e2:ele2) {
                if (e2.isDisplayed()) {
                    e2.sendKeys("las");
                }
            }
        currentAssignments.autoSuggestName.click();//click on auto suggest
        currentAssignments.getNewDueDateField().click();//click on date picker
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",currentAssignments.Next_Date_picker);//click on previous button of date picker
        Thread.sleep(2000);
        driver.findElement(By.linkText("1")).click();// select a next due date
        currentAssignments.getUpdateButtonInReassign_button().get(0).click();//click on update button
        String str= currentAssignments.updateNotificationMessage.getText();
        Assert.assertEquals(str,"The changes will be saved for the current tab only. Continue ? Yes | No","Update notification message is not displayed");
        currentAssignments.updateNotificationNoLink.click();//click on no
        boolean elementFound = false;
            try{
                driver.findElement(By.cssSelector("span[class='confirm-submit-yes submit-assign']"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
        Assert.assertEquals(elementFound,false,"update notification is opened");
        Thread.sleep(1000);
        currentAssignments.cancelUpdatePopup.get(1).click();//click on cancel
        currentAssignments.getUpdateAssignment_button().click();// click on update assignment link
        currentAssignments.getExtendDueDate().click();//click on extend due date
        Assert.assertEquals(currentAssignments.getExtendDueDateForField().get(1).getText(),"studtitle_IT24_R2410_32","Changes done in the \"Extend Due Date for\" field is not reverted to last extended class section/students.");
        currentAssignments.getNewDueDateField().click();//click on date picker
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",currentAssignments.Next_Date_picker);//click on previous button of date picker
        Thread.sleep(2000);
        driver.findElement(By.linkText("1")).click();// select a next due date
        currentAssignments.getUpdateButtonInReassign_button().get(0).click();//click on update button
        currentAssignments.updateNotificationYesLink.click();//click on yes
        Thread.sleep(1000);
        Assert.assertEquals(currentAssignments.tab_Title.get(0).getText(),"Current Assignments"," User has not navigated to the current Assignments page.");

        }catch (Exception e){
            Assert.fail("Exception in TC updateLinkFunctionality of class InstructorAbleToExtendDueDateForIndividualStudent",e);
        }
    }
}
