package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT22.R228;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.QuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by priyanka on 7/3/2015.
 */
public class DestinationInstructorAccessAndUseAssessments extends Driver {

    @Test(priority = 1, enabled = true)
    public void destinationInstructorAccessAndUseAssessments() {
        try {
            //tc row no 46
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "46");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "46");
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(461));
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            String customassignmentname1 = ReadTestData.readDataByTagName("", "customassignmentname", "47");

            new LoginUsingLTI().ltiLogin("461");//login as  destination instructor
            new LoginUsingLTI().ltiLogin("46");//login as  source instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("46");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new UIElement().waitAndFindElement(By.xpath("//span[@title='Share This']"));
            new Assignment().shareCustomAssignment(shareName);
            myQuestionBank.yesOnShareMessage.click();//click on yes
            Thread.sleep(1000);
            boolean elementFound = false;
            try {
                driver.findElement(By.xpath("//span[@title='Edit This']"));
                elementFound = true;
            } catch (Exception e) {
                //empty catch block
            }
            Assert.assertEquals(elementFound,true, "Edit this field is not available");
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.editThis.click();//click on edit this
            newAssignment.delete_Icon.click();//click on delete on 1st question
            myQuestionBank.getSaveForLater().click();//click on save for later
            new LoginUsingLTI().ltiLogin("461");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestion bank
            Assert.assertEquals(myQuestionBank.getAssessment().getText(), "New Name", "created custom assignment is not added under my question bank");
            Assert.assertEquals(myQuestionBank.getPreviewButton().isDisplayed(), true, "preview link is not available");
            Assert.assertEquals(myQuestionBank.getAssignThis().isDisplayed(), true, "Assign This link is not available");
            Assert.assertEquals(myQuestionBank.getDeleteButtonOfOriginal().getAttribute("title"), "Delete This", "Delete This link is not available");
            Assert.assertEquals(myQuestionBank.tryItIcon.get(0).isDisplayed(), true, " Try it link is not available");
            Assert.assertEquals(questionBank.getCustomizeThis().isDisplayed(), true, "Customize This link is not available");
            myQuestionBank.getPreviewButton().click();//click on preview
            Thread.sleep(2000);
            Assert.assertEquals(newAssignment.tabTittle.get(2).getText(), "New Name", "A New tab is not opened next to Question Bank");
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            questionBank.getCustomizeThis().click();//click on customize this link
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("47");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname1);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            myQuestionBank.getDeleteButtonOfOriginal().click();
            myQuestionBank.yesLink.click();//click on yes
            new AssignLesson().Assigncustomeassignemnt(47);//assign assignment
            new LoginUsingLTI().ltiLogin("46");//login as  source instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            boolean elementFound1 = false;
            try {
                driver.findElement(By.xpath("//span[@title='Edit This']"));
                elementFound1 = true;
            } catch (Exception e) {
                //empty catch block
            }
            Assert.assertEquals(elementFound1, false, "Edit this field is available");
            Assert.assertEquals(questionBank.getCustomizeThis().isDisplayed(), true, "Customize This link is not available");


        } catch (Exception e) {
            Assert.fail("Exception in test case destinationInstructorAccessAndUseAssessments of class DestinationInstructorAccessAndUseAssessments ", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void deleteThisFunctionality() {
        try {
            //tc row no 58
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "58");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "58");
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(581));

            new LoginUsingLTI().ltiLogin("581");//login as  destination instructor
            new LoginUsingLTI().ltiLogin("58");//login as  source instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("58");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new UIElement().waitAndFindElement(By.xpath("//span[@title='Share This']"));
            new Assignment().shareCustomAssignment(shareName);
            myQuestionBank.yesOnShareMessage.click();//click on yes
            Thread.sleep(1000);
            new LoginUsingLTI().ltiLogin("581");//login as  destination instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            Assert.assertEquals(myQuestionBank.getDeleteButtonOfOriginal().getAttribute("title"), "Delete This", "Delete This link is not available");
            myQuestionBank.getDeleteButtonOfOriginal().click();//click on delete
            Assert.assertEquals(myQuestionBank.notification.getText(), "Are you sure you want to delete this assessment?   Yes      No", "Delete This link is not available");
            myQuestionBank.noLink.click();//click on no link
            Assert.assertEquals(myQuestionBank.getAssessment().getText(), "New Name", "created custom assignment is not deleted");
            myQuestionBank.getDeleteButtonOfOriginal().click();//click on delete
            myQuestionBank.yesLink.click();//click on yes
            boolean elementFound = false;
            try {
                driver.findElement(By.xpath("//div[@class='resource-title']"));
                elementFound = true;
            } catch (Exception e) {
                //empty catch block
            }
            Assert.assertEquals(elementFound,true, "Assignment is not deleted");

        } catch (Exception e) {
            Assert.fail("Exception in test case deleteThisFunctionality of class DestinationInstructorAccessAndUseAssessments ", e);
        }
    }



    @Test(priority = 3, enabled = true)
    public void sharedByLabelShouldBeChangedToUpdatedBy() {
        try {
            //tc row no 63
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "63");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "63");
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(631));
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);

            new LoginUsingLTI().ltiLogin("631");//login as  destination instructor
            new LoginUsingLTI().ltiLogin("63");//login as  source instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("63");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question
            Thread.sleep(2000);
            new UIElement().waitAndFindElement(By.xpath("//span[@title='Share This']"));
            new Assignment().shareCustomAssignment(shareName);
            myQuestionBank.yesOnShareMessage.click();//click on yes
            Thread.sleep(1000);
            myQuestionBank.closeTab.click();
            myQuestionBank.editThis.click();//click on edit this
            newAssignment.delete_Icon.click();//click on delete on 1st question
            myQuestionBank.getSaveForLater().click();//click on save for
            new LoginUsingLTI().ltiLogin("631");//login as  destination instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            Assert.assertEquals(myQuestionBank.updatedBy.get(0).getText(),"Updated by","Shared by label is not changed to updated by label");
            questionBank.tryItIcon.get(0).click();//Click on tryit icon
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(questionBank.wileyLogo.isDisplayed(), true, "A new window pop is not opened to attempt it");

        } catch (Exception e) {
            Assert.fail("Exception in test case sharedByLabelShouldBeChangedToUpdatedBy of class DestinationInstructorAccessAndUseAssessments ", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void customizeThisLinkForExistingCustomAssessment() {
        try {
            //tc row no 66
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "66");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "66");
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            String customassignmentname1 = ReadTestData.readDataByTagName("", "customassignmentname", "67");

            new LoginUsingLTI().ltiLogin("66");//login as  source instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("66");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(2000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='resource-title']")));
            new AssignLesson().Assigncustomeassignemnt(66);//assign assignment
            Thread.sleep(2000);
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            questionBank.getCustomizeThis().click();//click on customize this link
            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("67");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname1);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(8000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='resource-title']")));
            Assert.assertEquals(myQuestionBank.getAssessment().getText(),"New_Name","created custom assignment is not added under my question bank");
            Assert.assertEquals(myQuestionBank.editThis.getText(),"Edit This","Edit This link is not available");

        } catch (Exception e) {
            Assert.fail("Exception in test case customizeThisLinkForExistingCustomAssessment of class DestinationInstructorAccessAndUseAssessments ", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void shareThisImpactsForInstructorsWithMultipleClassesAndSections() {
        try {
            //tc row no 69
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "69");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "69");
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(72));
            String shareName1 = ReadTestData.readDataByTagName("", "shareName", Integer.toString(73));

            new LoginUsingLTI().ltiLogin("72");//login as  source instructor2
            new LoginUsingLTI().ltiLogin("73");//login as  source instructor3
            new LoginUsingLTI().ltiLogin("69");//login as  source instructor1 for section A
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("63");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question
            new UIElement().waitAndFindElement(By.xpath("//span[@title='Share This']"));
            new Assignment().shareCustomAssignment(shareName);
            myQuestionBank.yesOnShareMessage.click();//click on yes
            Thread.sleep(1000);
            new Assignment().shareCustomAssignment(shareName1);
            Thread.sleep(1000);
            new LoginUsingLTI().ltiLogin("70");//login as  source instructor1 for section B
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            boolean elementFound = false;
            try {
                driver.findElement(By.xpath("(//span[@title='Delete This'])[1]"));
                elementFound = true;
            } catch (Exception e) {
                //empty catch block
            }
            Assert.assertEquals(elementFound, false, "Delete this link is displaying after share the assignment");
            myQuestionBank.shareThis.click();//click on share this
            Assert.assertEquals(myQuestionBank.sharedName.get(0).getText(), "family2, givenname2", "Already shared name is not available");
            Assert.assertEquals(myQuestionBank.sharedName.get(1).getText(), "family3, givenname3", "Already shared name is not available");

            new LoginUsingLTI().ltiLogin("71");//login as  source instructor1 for section c
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            boolean elementFound1 = false;
            try {
                driver.findElement(By.xpath("(//span[@title='Delete This'])[1]"));
                elementFound1 = true;
            } catch (Exception e) {
                //empty catch block
            }
            Assert.assertEquals(elementFound1, false, "Delete this link is displaying after share the assignment");
            new LoginUsingLTI().ltiLogin("69");//login as  source instructor1 for section A
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            new AssignLesson().Assigncustomeassignemnt(69);//assign assignment
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            Assert.assertEquals(questionBank.getCustomizeThis().isDisplayed(), true, "Customize This link is not available");

            new LoginUsingLTI().ltiLogin("72");//login as  source instructor2 for section B
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            boolean elementFound2 = false;
            try {
                driver.findElement(By.xpath("(//span[@title='Delete This'])[1]"));
                elementFound2 = true;
            } catch (Exception e) {
                //empty catch block
            }
            Assert.assertEquals(elementFound2,true, "Delete this link is  not displaying after share the assignment");

        } catch (Exception e) {
            Assert.fail("Exception in test case shareThisImpactsForInstructorsWithMultipleClassesAndSections of class DestinationInstructorAccessAndUseAssessments ", e);
        }
    }

        }


