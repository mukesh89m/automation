package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT22.R228;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.QuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by priyanka on 7/1/2015.
 */
public class InstructorAbleToShareCustomAssessments extends Driver {
    @Test(priority = 1, enabled = true)
    public void instructorAbleToViewOneMoreOptionAsShareThis() {
        try {
            //tc row no 10
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "10");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "10");
            new LoginUsingLTI().ltiLogin("10");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("10");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='resource-title']")));
            Assert.assertEquals(myQuestionBank.getAssessment().getText(), "New Name", "created custom assignment is not added under my question bank");
            Assert.assertEquals(myQuestionBank.getPreviewButton().isDisplayed(), true, "preview link is not available");
            Assert.assertEquals(myQuestionBank.shareThis.getAttribute("title"), "Share This", " Share This link is not available");
            Assert.assertEquals(myQuestionBank.getAssignThis().isDisplayed(), true, "Assign This link is not available");
            Assert.assertEquals(myQuestionBank.getDeleteButtonOfOriginal().getAttribute("title"), "Delete This", "Delete This link is not available");
            Assert.assertEquals(myQuestionBank.tryItIcon.get(0).isDisplayed(), true, " Try it link is not available");
            new AssignLesson().Assigncustomeassignemnt(10);//assign assignment
            Thread.sleep(2000);
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            Assert.assertEquals(myQuestionBank.getPreviewButton().isDisplayed(), true, "preview link is not available");
            Assert.assertEquals(myQuestionBank.shareThis.getAttribute("title"), "Share This", " Share This link is not available");
            Assert.assertEquals(myQuestionBank.getAssignThis().isDisplayed(), true, "Assign This link is not available");
            Assert.assertEquals(questionBank.getCustomizeThis().isDisplayed(), true, "Customize This link is not available");
            Assert.assertEquals(myQuestionBank.tryItIcon.get(0).isDisplayed(), true, " Try it link is not available");


        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToViewOneMoreOptionAsShareThis of class InstructorAbleToShareCustomAssessments ", e);
        }
    }


    @Test(priority = 2, enabled = true)
    public void shareThisLink() {
        try {
            //tc row no 13
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "13");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "13");
            new LoginUsingLTI().ltiLogin("13");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("13");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            myQuestionBank.shareThis.click();//click on share this
            Assert.assertEquals(myQuestionBank.shareWithPopUp.isDisplayed(), true, "Share With Pop-up is not appear below the \"Share this\" link");
            Assert.assertEquals(myQuestionBank.helpIcon.isDisplayed(), true, "question mark label icon beside the \"Share with\" label in the pop-up is not available");
            Assert.assertEquals(myQuestionBank.shareWithTextBox.isDisplayed(), true, " A Text box is not available to add Instructor name for sharing the custom assessment.");
            Assert.assertEquals(myQuestionBank.shareButton.isDisplayed(), true, "share button is not available");
            Assert.assertEquals(myQuestionBank.cancelPopUp.isDisplayed(), true, "cancel button is not available");
            myQuestionBank.cancelPopUp.click();//click on cancel
            boolean elementFound = false;
            try {
                driver.findElement(By.id("ir-ls-assign-dialog"));
                elementFound = true;
            } catch (Exception e) {
                //empty catch block
            }
            Assert.assertEquals(elementFound, false, "share with popup is not closed");
            myQuestionBank.shareThis.click();//click on share this
            myQuestionBank.helpIcon.click();//click on help icon
            Assert.assertEquals(myQuestionBank.helpMessage.getText(), "Use this option to share custom assessments with other instructors. Once you share, you will not be able to delete this assessment.", "help mesage is not available");


        } catch (Exception e) {
            Assert.fail("Exception in test case shareThisLink of class InstructorAbleToShareCustomAssessments ", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void shareWithSectionShouldBeBlank() {
        try {
            //tc row no 20
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "20");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "20");
            new LoginUsingLTI().ltiLogin("20");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("20");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            myQuestionBank.shareThis.click();//click on share this
            boolean elementFound = false;
            try {
                driver.findElement(By.cssSelector("//ul[contains(@class,'holder ls-already-shared-users-ul')]//li"));
                elementFound = true;
            } catch (Exception e) {
                //empty catch block
            }
            Assert.assertEquals(elementFound, false, "space between the text box and below Share with\" Label is not blank");


        } catch (Exception e) {
            Assert.fail("Exception in test case shareWithSectionShouldBeBlank of class InstructorAbleToShareCustomAssessments ", e);
        }
    }


    @Test(priority = 4, enabled = true)
    public void instructorAddInstructorWhoArePartOfActiveClassSections() {
        try {
            //tc row no 21
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "21");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "21");
            new LoginUsingLTI().ltiLogin("211");//login as instructor
            new LoginUsingLTI().ltiLogin("212");//login as instructor
            new LoginUsingLTI().ltiLogin("213");//login as instructor
            new LoginUsingLTI().ltiLogin("214");//login as instructor
            new LoginUsingLTI().ltiLogin("215");//login as instructor
            new LoginUsingLTI().ltiLogin("216");//login as instructor
            new LoginUsingLTI().ltiLogin("217");//login as instructor
            new LoginUsingLTI().ltiLogin("218");//login as instructor
            new LoginUsingLTI().ltiLogin("219");//login as instructor
            new LoginUsingLTI().ltiLogin("2110");//login as instructor
            new LoginUsingLTI().ltiLogin("2111");//login as instructor
            new LoginUsingLTI().ltiLogin("2112");//login as instructor
            new LoginUsingLTI().ltiLogin("2113");//login as instructor
            new LoginUsingLTI().ltiLogin("2114");//login as instructor
            new LoginUsingLTI().ltiLogin("2115");//login as instructor
            new LoginUsingLTI().ltiLogin("2116");//login as instructor

            new LoginUsingLTI().ltiLogin("21");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("21");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            myQuestionBank.shareThis.click();//click on share this
            myQuestionBank.shareWithTextBox.click();//click on share text box
            Thread.sleep(2000);
            myQuestionBank.shareTextBox.sendKeys("fam");
            Assert.assertEquals(myQuestionBank.suggestionBox.get(0).isDisplayed(), true, "Auto-suggest options are not appearing ");
            if(myQuestionBank.suggestionBox.size()!=15)
                Assert.fail("more than 15 suggestion are available");

            myQuestionBank.suggestionBox.get(0).click();//click on 1st instructor
            myQuestionBank.shareButton.click();//click on share button
            Assert.assertEquals(myQuestionBank.getNotificationMessage().get(0).getText(), "You will not be able to delete a shared assessment.Continue? Yes | No", "Notification message is not displaying");
            myQuestionBank.noOnShareMessage.click();//click on no
            boolean elementFound = false;
            try {
                driver.findElement(By.className("ls-notification-text-span"));
                elementFound = true;
            } catch (Exception e) {
                //empty catch block
            }
            Assert.assertEquals(elementFound, false, "share pop is not closed");
            myQuestionBank.shareThis.click();//click on share this
            myQuestionBank.shareWithTextBox.click();//click on share text box
            Thread.sleep(2000);
            myQuestionBank.shareTextBox.sendKeys("fam");
            myQuestionBank.suggestionBox.get(0).click();//click on 1st instructor
            myQuestionBank.shareButton.click();//click on share button
            myQuestionBank.yesOnShareMessage.click();//click on yes
            Thread.sleep(1000);
            myQuestionBank.shareThis.click();//click on share this
            Assert.assertEquals(myQuestionBank.sharedName.get(0).getText(), "family1, givenname1", "Already shared name is not available");
            myQuestionBank.shareWithTextBox.click();//click on share text box
            Thread.sleep(2000);
            myQuestionBank.shareTextBox.sendKeys("fam");
            myQuestionBank.suggestionBox.get(0).click();//click on 1st instructor
            Assert.assertEquals(myQuestionBank.shareWithTextBox.getText(), "family10, givenname10", " The Instructor name in the above specified format is not selected in the text box.");
            Assert.assertEquals(myQuestionBank.closeIcon_instructorCard.isDisplayed(), true, "close icon is not available for Instructor");
            myQuestionBank.closeIcon_instructorCard.click();//click on close
            myQuestionBank.shareWithTextBox.click();//click on share text box
            Thread.sleep(2000);
            myQuestionBank.shareTextBox.sendKeys("fam");
            List<WebElement> autoSuggestion = myQuestionBank.suggestionBox;
            for (WebElement e : autoSuggestion) {
                if (e.getText().contains("family1, givenname1"))
                    Assert.fail("All the names of the Instructor who are already part of the shared list for this assessment is showing up in the auto suggest");
            }

            myQuestionBank.suggestionBox.get(0).click();//click on 1st instructor
            myQuestionBank.shareWithTextBox.click();//click on share text box
            Thread.sleep(2000);
            myQuestionBank.shareTextBox.sendKeys("fam");
            for (WebElement e : autoSuggestion) {
                if (e.getText().contains("family10, givenname10"))
                    Assert.fail("Already typed/selected instructor name in the textbox is displaying in Auto-suggest drop down.");
            }
            myQuestionBank.shareThis.click();//click on share this
            myQuestionBank.shareWithTextBox.click();//click on share text box
            Thread.sleep(2000);
            myQuestionBank.shareTextBox.sendKeys("fam");
            myQuestionBank.suggestionBox.get(0).click();//click on 1st instructor
            myQuestionBank.closeIcon_instructorCard.click();//click on close
            Thread.sleep(2000);
           /* if(!myQuestionBank.shareFieldBlank.isEmpty() )
                    Assert.fail("The Instructor card is not deleted from the share text box.");
*/

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAddInstructorWhoArePartOfActiveClassSections of class InstructorAbleToShareCustomAssessments ", e);
        }
    }




    @Test(priority = 5, enabled = true)
    public void instructorShareACustomAssignment() {
        try {
            //tc row no 32
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "32");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "32");
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(321));
            String shareName1 = ReadTestData.readDataByTagName("", "shareName", Integer.toString(322));
            new LoginUsingLTI().ltiLogin("321");//login as instructor
            new LoginUsingLTI().ltiLogin("322");//login as instructor

            new LoginUsingLTI().ltiLogin("32");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("32");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new UIElement().waitAndFindElement(By.xpath("//span[@title='Share This']"));
            new Assignment().shareCustomAssignment(shareName);
            myQuestionBank.yesOnShareMessage.click();//click on yes
            Thread.sleep(1000);
            new Assignment().shareCustomAssignment(shareName1);
            myQuestionBank.shareThis.click();//click on share this
            Assert.assertEquals(myQuestionBank.sharedName.get(0).getText(), "family1, givenname1", "Already shared name is not available");
            boolean elementFound = false;
            try {
                driver.findElement(By.xpath("(//span[@title='Delete This'])[1]"));
                elementFound = true;
            } catch (Exception e) {
                //empty catch block
            }
            Assert.assertEquals(elementFound, false, "Delete this link is displaying after share the assignment");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShareACustomAssignment of class InstructorAbleToShareCustomAssessments ", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void validationOnSharingEmpty() {
        try {
            //tc row no 35
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "35");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "35");

            new LoginUsingLTI().ltiLogin("35");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("35");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new UIElement().waitAndFindElement(By.xpath("//span[@title='Share This']"));
            myQuestionBank.shareThis.click();//click on share this
            myQuestionBank.shareButton.click();//click on share button
            Assert.assertEquals(myQuestionBank.errorMessage.getText(),"Please add at-least one instructor to share this assessment.","Error message is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case validationOnSharingEmpty of class InstructorAbleToShareCustomAssessments ", e);
        }
    }


    @Test(priority = 7, enabled = true)
    public void editThisLinkShouldBeChangedToCustomizeThisLink() {
        try {
            //tc row no 37
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "37");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "37");
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);

            new LoginUsingLTI().ltiLogin("37");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("37");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(37);//assign assignment
            Thread.sleep(2000);
            new Navigator().NavigateTo("My Question Bank");//click on myquestion bank
            boolean elementFound = false;
            try {
                driver.findElement(By.xpath("//span[@title='Edit This']"));
                elementFound = true;
            } catch (Exception e) {
                //empty catch block
            }
            Assert.assertEquals(elementFound, false, "Edit this field is available");
            Assert.assertEquals(questionBank.getCustomizeThis().isDisplayed(), true, "Customize This link is not available");


        } catch (Exception e) {
            Assert.fail("Exception in test case editThisLinkShouldBeChangedToCustomizeThisLink  of class InstructorAbleToShareCustomAssessments ", e);
        }
    }


    @Test(priority = 8, enabled = true)
    public void logInAsSourceInstructor() {
        try {
            //tc row no 39
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "39");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "39");
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(391));

            new LoginUsingLTI().ltiLogin("391");//login as  destination instructor
            new LoginUsingLTI().ltiLogin("39");//login as  source instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("39");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new UIElement().waitAndFindElement(By.xpath("//span[@title='Share This']"));
            new Assignment().shareCustomAssignment(shareName);
            myQuestionBank.yesOnShareMessage.click();//click on yes
            Thread.sleep(1000);
            new LoginUsingLTI().ltiLogin("391");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestion bank
            new AssignLesson().Assigncustomeassignemnt(39);//assign assignment
            Thread.sleep(2000);
            new LoginUsingLTI().ltiLogin("39");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestion bank
            boolean elementFound = false;
            try {
                driver.findElement(By.xpath("//span[@title='Edit This']"));
                elementFound = true;
            } catch (Exception e) {
                //empty catch block
            }
            Assert.assertEquals(elementFound, false, "Edit this field is available");
            Assert.assertEquals(questionBank.getCustomizeThis().isDisplayed(), true, "Customize This link is not available");

        } catch (Exception e) {
            Assert.fail("Exception in test case logInAsSourceInstructor  of class InstructorAbleToShareCustomAssessments ", e);
        }
    }


    @Test(priority = 9, enabled = true)
    public void editThisScenario() {
        try {
            //tc row no 41
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "41");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "41");
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(411));
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);

            new LoginUsingLTI().ltiLogin("411");//login as  destination instructor
            new LoginUsingLTI().ltiLogin("41");//login as  source instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("41");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new UIElement().waitAndFindElement(By.xpath("//span[@title='Share This']"));
            new Assignment().shareCustomAssignment(shareName);
            myQuestionBank.yesOnShareMessage.click();//click on yes
            new LoginUsingLTI().ltiLogin("41");//login as  source instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            boolean elementFound = false;
            try {
                driver.findElement(By.xpath("//span[@title='Edit This']"));

                elementFound = true;
            } catch (Exception e) {
                //empty catch block
            }
            Assert.assertEquals(elementFound,true, "Edit this field is not available");
            myQuestionBank.editThis.click();//click on edit this
            newAssignment.delete_Icon.click();//click on delete on 1st question
            returnCurrentDateAndTime();
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(60000);
            String myDate=returnCurrentDateAndTime();

            new LoginUsingLTI().ltiLogin("411");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestion bank
            String updatedBy=myQuestionBank.updatedBy.get(0).getText();
            String family=myQuestionBank.updatedBy.get(1).getText();
            String time=myQuestionBank.updatedBy.get(2).getText().trim();
            Assert.assertEquals(updatedBy+family,"Updated byfamily, givenname","upadatedby and family name is not available");
            Assert.assertEquals(time,myDate);
            Assert.assertEquals(questionBank.getCustomizeThis().isDisplayed(), true, "Customize This link is not available");
            myQuestionBank.getPreviewButton().click();//click on preview
            Thread.sleep(4000);
            System.out.println(newAssignment.question_Card.size());
            if(newAssignment.question_Card.size() != 1)
                Assert.fail(" deleted question is displaying");
        } catch (Exception e) {
            Assert.fail("Exception in test case editThisScenario of class InstructorAbleToShareCustomAssessments ", e);
        }
    }

 public String returnCurrentDateAndTime() {
        String time=null;
     try {
         SimpleDateFormat formatter = new SimpleDateFormat("MMM dd, yyyy, hh:mm a");
         //get current date time with Date()
         Date date = new Date();
         System.out.println(formatter.format(date));
         time=formatter.format(date);

         //get current date time with Calendar()
     } catch (Exception e) {
         e.printStackTrace();
     }
        return time;
 }
}
