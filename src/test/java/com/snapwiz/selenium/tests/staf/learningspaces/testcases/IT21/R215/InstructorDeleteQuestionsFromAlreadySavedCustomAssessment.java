package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT21.R215;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by priyanka on 6/24/2015.
 */
public class InstructorDeleteQuestionsFromAlreadySavedCustomAssessment extends Driver {
    @Test(priority = 1, enabled = true)
    public void instructorDeleteQuestionsFromAlreadySavedCustomAssessment() {
        try {
            //tc row no 102
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "102");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "102");
            new LoginUsingLTI().ltiLogin("102");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("102");//select one question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            dashboard.getMainNavigator().click();//click on left corner main navigator
            dashboard.getAssignments().click();//click on '>' icon adjacent to assignment
            List<WebElement> subMenu = dashboard.subMenu;
            Assert.assertEquals(subMenu.get(0).getText(), "New Assignment", "New Assignment submenu option is not available");
            Assert.assertEquals(subMenu.get(1).getText(), "Current Assignments", "Current Assignments submenu option is not available");
            Assert.assertEquals(subMenu.get(2).getText(), "My Question Bank", "My Question Bank submenu option is not available");
            Assert.assertEquals(subMenu.get(3).getText(), "Policies", "Policies submenu option is not available");
            dashboard.getMainNavigatorAfterSelected().click();//click on main navigator
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            String myQuestionBankTitle = myQuestionBank.getMyQuestionBankTitle().getAttribute("title");
            Assert.assertEquals(myQuestionBankTitle, "My Question Bank", "MyQuestionBank page is not opened");
            myQuestionBank.editThis.click();//click on edit this
            Assert.assertEquals(newAssignment.tabTittle.get(2).getText(), "New Name", "A New tab is not opened next to Question Bank tab to edit the Custom Assessment ");
            Assert.assertEquals(myQuestionBank.getAssignmentNameField().getText(), "New Name", "The Name of the assessment is not displaying in the Assessment name field");//click on name field
            Assert.assertEquals(newAssignment.tabTittle.get(3).getText(), "Find Questions", "Find Questions tab is not displaying");
            Thread.sleep(3000);
            Assert.assertEquals(newAssignment.tabTittle.get(4).getText().substring(0, 18),"Selected Questions","Selected Questions tab is not displaying");
            Assert.assertEquals(newAssignment.selectedQuestion.isEnabled(),true,"selected question tab is not selected by default");
            newAssignment.delete_Icon.click();//click on delete on 1st question
            if(newAssignment.questionBar.size() < 1)
                Assert.fail("question is not deleted from the assessment");
            myQuestionBank.getSaveForLater().click();//click on save for later
            Assert.assertEquals(newAssignment.notification_message.getText(), "Updated Custom Assessment Successfully.", "Saved Custom Assignment Successfully message is not displaying at the top");
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='resource-title']")));
            Assert.assertEquals(myQuestionBank.getAssessment().getText(),"New Name","created custom assignment is not added under my question bank");
            myQuestionBank.getPreviewButton().click();//click on preview
            Thread.sleep(2000);
            Assert.assertEquals(newAssignment.tabTittle.get(2).getText(), "New Name", "A New tab is not opened next to Question Bank");
            if(newAssignment.question_Card.size() < 1)
                Assert.fail(" deleted question is displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorDeleteQuestionsFromAlreadySavedCustomAssessment of class InstructorDeleteQuestionsFromAlreadySavedCustomAssessment ", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void instructorSeeANotificationMessageOnDeletingQuestions() {
        try {
            //tc row no 112
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "112");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "112");
            new LoginUsingLTI().ltiLogin("112");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("112");//select one question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            dashboard.getMainNavigator().click();//click on left corner main navigator
            dashboard.getAssignments().click();//click on '>' icon adjacent to assignment
            List<WebElement> subMenu = dashboard.subMenu;
            Assert.assertEquals(subMenu.get(0).getText(), "New Assignment", "New Assignment submenu option is not available");
            Assert.assertEquals(subMenu.get(1).getText(), "Current Assignments", "Current Assignments submenu option is not available");
            Assert.assertEquals(subMenu.get(2).getText(), "My Question Bank", "My Question Bank submenu option is not available");
            Assert.assertEquals(subMenu.get(3).getText(), "Policies", "Policies submenu option is not available");
            dashboard.getMainNavigatorAfterSelected().click();//click on main navigator
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            String myQuestionBankTitle = myQuestionBank.getMyQuestionBankTitle().getAttribute("title");
            Assert.assertEquals(myQuestionBankTitle, "My Question Bank", "MyQuestionBank page is not opened");
            myQuestionBank.editThis.click();//click on edit this
            Assert.assertEquals(newAssignment.tabTittle.get(2).getText(), "New Name", "A New tab is not opened next to Question Bank tab to edit the Custom Assessment ");
            Assert.assertEquals(myQuestionBank.getAssignmentNameField().getText(), "New Name", "The Name of the assessment is not displaying in the Assessment name field");//click on name field
            Assert.assertEquals(newAssignment.tabTittle.get(3).getText(), "Find Questions", "Find Questions tab is not displaying");
            Thread.sleep(3000);
            Assert.assertEquals(newAssignment.tabTittle.get(4).getText().substring(0, 18),"Selected Questions","Selected Questions tab is not displaying");
            Assert.assertEquals(newAssignment.selectedQuestion.isEnabled(),true,"selected question tab is not selected by default");
            newAssignment.delete_Icon.click();//click on delete on 1st question
            boolean elementFound = false;
            try{
                driver.findElement(By.linkText("Delete"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,false,"question is not deleted");
            myQuestionBank.getSaveForLater().click();//click on save for later
            Assert.assertEquals(newAssignment.notification_message.getText(),"You have not added any questions for this custom assessment. Please add questions before saving the assessment","notification message is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in test case instructorSeeANotificationMessageOnDeletingQuestions of class InstructorDeleteQuestionsFromAlreadySavedCustomAssessment ", e);
        }
    }


    @Test(priority = 3, enabled = true)
    public void instructorVerifyThatTheUserIsAbleToReorderTheQuestions() {
        try {
            //tc row no 121
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "121");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "121");
            new LoginUsingLTI().ltiLogin("121");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("121");//select three question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            dashboard.getMainNavigator().click();//click on left corner main navigator
            dashboard.getAssignments().click();//click on '>' icon adjacent to assignment
            List<WebElement> subMenu = dashboard.subMenu;
            Assert.assertEquals(subMenu.get(0).getText(), "New Assignment", "New Assignment submenu option is not available");
            Assert.assertEquals(subMenu.get(1).getText(), "Current Assignments", "Current Assignments submenu option is not available");
            Assert.assertEquals(subMenu.get(2).getText(), "My Question Bank", "My Question Bank submenu option is not available");
            Assert.assertEquals(subMenu.get(3).getText(), "Policies", "Policies submenu option is not available");
            dashboard.getMainNavigatorAfterSelected().click();//click on main navigator
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            String myQuestionBankTitle = myQuestionBank.getMyQuestionBankTitle().getAttribute("title");
            Assert.assertEquals(myQuestionBankTitle, "My Question Bank", "MyQuestionBank page is not opened");
            myQuestionBank.editThis.click();//click on edit this
            Assert.assertEquals(newAssignment.tabTittle.get(2).getText(), "New Name", "A New tab is not opened next to Question Bank tab to edit the Custom Assessment ");
            Assert.assertEquals(myQuestionBank.getAssignmentNameField().getText(), "New Name", "The Name of the assessment is not displaying in the Assessment name field");//click on name field
            Assert.assertEquals(newAssignment.tabTittle.get(3).getText(), "Find Questions", "Find Questions tab is not displaying");
            Thread.sleep(3000);
            Assert.assertEquals(newAssignment.tabTittle.get(4).getText().substring(0, 18),"Selected Questions","Selected Questions tab is not displaying");
            Assert.assertEquals(newAssignment.selectedQuestion.isEnabled(),true,"selected question tab is not selected by default");
            String beforeDrag=newAssignment.secondQuestionBar.get(0).getText();
            Actions act =new Actions(driver);
            WebElement dragAble=newAssignment.questionBar.get(2);
            WebElement dropAble=newAssignment.questionBar.get(0);
            act.clickAndHold(dragAble).build().perform();
            act.moveToElement(dropAble).release().build().perform();
            String afterDrag=newAssignment.secondQuestionBar.get(0).getText();
            if(beforeDrag.equals(afterDrag))
                Assert.fail("The Question is not displaying at the dropped position for the Assessment");
            Assert.assertEquals(newAssignment.questionLabel.getText(),"Q1:","The question number is not starting with 1");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorSeeANotificationMessageOnDeletingQuestions of class InstructorDeleteQuestionsFromAlreadySavedCustomAssessment ", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void instructorVerifyThatTheUserIsAbleToReorderTheNewlyAddedQuestions () {
        try {
            //tc row no 129
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "129");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "129");
            new LoginUsingLTI().ltiLogin("129");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("129");//select one question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            dashboard.getMainNavigator().click();//click on left corner main navigator
            dashboard.getAssignments().click();//click on '>' icon adjacent to assignment
            List<WebElement> subMenu = dashboard.subMenu;
            Assert.assertEquals(subMenu.get(0).getText(), "New Assignment", "New Assignment submenu option is not available");
            Assert.assertEquals(subMenu.get(1).getText(), "Current Assignments", "Current Assignments submenu option is not available");
            Assert.assertEquals(subMenu.get(2).getText(), "My Question Bank", "My Question Bank submenu option is not available");
            Assert.assertEquals(subMenu.get(3).getText(), "Policies", "Policies submenu option is not available");
            dashboard.getMainNavigatorAfterSelected().click();//click on main navigator
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            String myQuestionBankTitle = myQuestionBank.getMyQuestionBankTitle().getAttribute("title");
            Assert.assertEquals(myQuestionBankTitle, "My Question Bank", "MyQuestionBank page is not opened");
            myQuestionBank.editThis.click();//click on edit this
            Assert.assertEquals(newAssignment.tabTittle.get(2).getText(), "New Name", "A New tab is not opened next to Question Bank tab to edit the Custom Assessment ");
            Assert.assertEquals(myQuestionBank.getAssignmentNameField().getText(), "New Name", "The Name of the assessment is not displaying in the Assessment name field");//click on name field
            Assert.assertEquals(newAssignment.tabTittle.get(3).getText(), "Find Questions", "Find Questions tab is not displaying");
            Thread.sleep(3000);
            String selectedQuestionCount=newAssignment.tabTittle.get(4).getText().substring(20,21);
            int questionCount=Integer.parseInt(selectedQuestionCount);
            Assert.assertEquals(newAssignment.tabTittle.get(4).getText().substring(0, 18),"Selected Questions","Selected Questions tab is not displaying");
            Assert.assertEquals(newAssignment.selectedQuestion.isEnabled(),true,"selected question tab is not selected by default");
            newAssignment.tabTittle.get(3).click();//click on find question tab
            Assert.assertEquals(newAssignment.tabTittle.get(3).isEnabled(), true, "find question tab is not selected");
            Assert.assertEquals(newAssignment.searchButton.isDisplayed(),true,"search button is not displaying");
            Assert.assertEquals(newAssignment.browseButton.isDisplayed(),true,"browse button is not displaying");
            Assert.assertEquals(newAssignment.noQuestionFound.isDisplayed(),true,"questions are displaying by default");
            newAssignment.searchButton.click();//click on search button
            Assert.assertEquals(newAssignment.searchTextArea.isDisplayed(), true, "search text box is not selected");
            newAssignment.browseButton.click();//click on browse button
            //Assert.assertEquals(newAssignment.browseDropDown.isDisplayed(),true,"browse dropdown area is not displaying");
            newAssignment.searchButton.click();//click on search button
            newAssignment.searchTextArea.sendKeys("animals");
            newAssignment.searchButton.click();//click on search button
            boolean elementFound = false;
            try{
                driver.findElement(By.linkText("No Results found"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,false,"The questions relative to the search value is not displaying below");
            Thread.sleep(1000);
            new AssignLesson().selectQuestionForCustomAssignment("129");//select one question
            Thread.sleep(1000);
            Assert.assertEquals(newAssignment.checkCheckedBox.isDisplayed(),true,"checkbox is not checked");
            String searchText=newAssignment.searchText.get(0).getText();
            String selectedQuestionCount1=newAssignment.tabTittle.get(4).getText().substring(20,21);
            int questionCount1=Integer.parseInt(selectedQuestionCount1);
            if(questionCount > questionCount1 )
                Assert.fail("the question count of selected Question is not increased");
            newAssignment.tabTittle.get(4).click();//click on selected question sub tab
            Assert.assertEquals(newAssignment.selectedQuestion.isEnabled(),true,"selected question tab is not selected");
            String secondQuestionBar=newAssignment.secondQuestionBar.get(2).getText().trim();
            Thread.sleep(2000);
            if(!searchText.contains(secondQuestionBar))
                Assert.fail("Newly added questions is not displayed at the end of the previously saved question list");
            Assert.assertEquals(newAssignment.tabTittle.get(2).getText(), "New Name", "A New tab is not opened next to Question Bank tab to edit the Custom Assessment ");
            Thread.sleep(2000);
            String beforeDrag=newAssignment.secondQuestionBar.get(0).getText();
            Actions act =new Actions(driver);
            WebElement dragAble=newAssignment.secondQuestionBar.get(3);
            WebElement dropAble=newAssignment.secondQuestionBar.get(0);
            act.clickAndHold(dragAble).build().perform();
            act.moveToElement(dropAble).release().build().perform();
            String afterDrag=newAssignment.secondQuestionBar.get(0).getText();
            String afterDrag1=newAssignment.secondQuestionBar.get(1).getText();
            if(beforeDrag.equals(afterDrag))
                Assert.fail("The Question is not displaying at the dropped position for the Assessment");
            Assert.assertEquals(newAssignment.questionLabel.getText(),"Q1:","The question number is not starting with 1");
            myQuestionBank.getSaveForLater().click();//click on save for later
            Assert.assertEquals(newAssignment.notification_message.getText(), "Updated Custom Assessment Successfully.", "Saved Custom Assignment Successfully message is not displaying at the top");
            Thread.sleep(5000);
            newAssignment.close_Icon.click();//click on close
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='resource-title']")));
            Thread.sleep(2000);
            myQuestionBank.getPreviewButton().click();//click on preview
            Thread.sleep(2000);
            Assert.assertEquals(newAssignment.tabTittle.get(2).getText(), "New Name", "A New tab is not opened next to Question Bank");
            Thread.sleep(1000);
            String questionPreview1=newAssignment.questionPreview.get(1).getText();
            Thread.sleep(2000);
            if(!afterDrag1.contains(questionPreview1))
                Assert.fail("The order of the Questions displayed in the preview is not as per the newly reordered position");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorVerifyThatTheUserIsAbleToReorderTheNewlyAddedQuestions of class InstructorDeleteQuestionsFromAlreadySavedCustomAssessment ", e);
        }
    }


    @Test(priority = 5, enabled = true)
    public void instructorVerifyThatVerifyUserAbleToChangePointsAndManualGrading() {
        try {
            //tc row no 146
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "146");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "146");
            new LoginUsingLTI().ltiLogin("146");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("146");//select one question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            dashboard.getMainNavigator().click();//click on left corner main navigator
            dashboard.getAssignments().click();//click on '>' icon adjacent to assignment
            List<WebElement> subMenu = dashboard.subMenu;
            Assert.assertEquals(subMenu.get(0).getText(), "New Assignment", "New Assignment submenu option is not available");
            Assert.assertEquals(subMenu.get(1).getText(), "Current Assignments", "Current Assignments submenu option is not available");
            Assert.assertEquals(subMenu.get(2).getText(), "My Question Bank", "My Question Bank submenu option is not available");
            Assert.assertEquals(subMenu.get(3).getText(), "Policies", "Policies submenu option is not available");
            dashboard.getMainNavigatorAfterSelected().click();//click on main navigator
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            String myQuestionBankTitle = myQuestionBank.getMyQuestionBankTitle().getAttribute("title");
            Assert.assertEquals(myQuestionBankTitle, "My Question Bank", "MyQuestionBank page is not opened");
            myQuestionBank.editThis.click();//click on edit this
            Assert.assertEquals(newAssignment.tabTittle.get(2).getText(), "New Name", "A New tab is not opened next to Question Bank tab to edit the Custom Assessment ");
            Assert.assertEquals(myQuestionBank.getAssignmentNameField().getText(), "New Name", "The Name of the assessment is not displaying in the Assessment name field");//click on name field
            Assert.assertEquals(newAssignment.tabTittle.get(3).getText(), "Find Questions", "Find Questions tab is not displaying");
            Thread.sleep(3000);
            Assert.assertEquals(newAssignment.tabTittle.get(4).getText().substring(0, 18),"Selected Questions","Selected Questions tab is not displaying");
            Assert.assertEquals(newAssignment.selectedQuestion.isEnabled(),true,"selected question tab is not selected by default");
            newAssignment.pointCheckBox.get(0).clear();
            newAssignment.pointCheckBox.get(0).sendKeys("2");
            Assert.assertEquals(newAssignment.pointCheckBox.get(0).getAttribute("value"), "2", "Points available for that question is not 2");
            newAssignment.manualGradingCheckBox.click();//click on manual grading checkbox
            Assert.assertEquals(newAssignment.manualGradingCheckedCheckBox.isDisplayed(), true, "the checkbox is not checked");
            myQuestionBank.getSaveForLater().click();//click on save for later
            Assert.assertEquals(newAssignment.notification_message.getText(), "Updated Custom Assessment Successfully.", "Saved Custom Assignment Successfully message is not displaying at the top");
            Thread.sleep(5000);
            newAssignment.close_Icon.click();//click on close
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='resource-title']")));
            myQuestionBank.editThis.click();//click on edit this
            Assert.assertEquals(newAssignment.tabTittle.get(2).getText(), "New Name", "A New tab is not opened next to Question Bank tab to edit the Custom Assessment ");
            Assert.assertEquals(newAssignment.pointCheckBox.get(0).getAttribute("value"), "2", "Points available for that question is not 2");
            Assert.assertEquals(newAssignment.manualGradingCheckedCheckBox.isDisplayed(), true, "the checkbox is not checked");


        } catch (Exception e) {
            Assert.fail("Exception in test case instructorVerifyThatVerifyUserAbleToChangePointsAndManualGrading of class InstructorDeleteQuestionsFromAlreadySavedCustomAssessment ", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void endToEndFlowOfCustomAssignment() {
        try {
            //tc row no 158
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "158");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "158");
            new LoginUsingLTI().ltiLogin("158");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("158");//select one question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            dashboard.getMainNavigator().click();//click on left corner main navigator
            dashboard.getAssignments().click();//click on '>' icon adjacent to assignment
            List<WebElement> subMenu = dashboard.subMenu;
            Assert.assertEquals(subMenu.get(0).getText(), "New Assignment", "New Assignment submenu option is not available");
            Assert.assertEquals(subMenu.get(1).getText(), "Current Assignments", "Current Assignments submenu option is not available");
            Assert.assertEquals(subMenu.get(2).getText(), "My Question Bank", "My Question Bank submenu option is not available");
            Assert.assertEquals(subMenu.get(3).getText(), "Policies", "Policies submenu option is not available");
            dashboard.getMainNavigatorAfterSelected().click();//click on main navigator
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            String myQuestionBankTitle = myQuestionBank.getMyQuestionBankTitle().getAttribute("title");
            Assert.assertEquals(myQuestionBankTitle, "My Question Bank", "MyQuestionBank page is not opened");
            myQuestionBank.editThis.click();//click on edit this
            Assert.assertEquals(newAssignment.tabTittle.get(2).getText(), "New Name", "A New tab is not opened next to Question Bank tab to edit the Custom Assessment ");
            Assert.assertEquals(myQuestionBank.getAssignmentNameField().getText(), "New Name", "The Name of the assessment is not displaying in the Assessment name field");//click on name field
            Assert.assertEquals(newAssignment.tabTittle.get(3).getText(), "Find Questions", "Find Questions tab is not displaying");
            Thread.sleep(3000);
            String selectedQuestionCount=newAssignment.tabTittle.get(4).getText().substring(20,21);
            int questionCount=Integer.parseInt(selectedQuestionCount);
            Assert.assertEquals(newAssignment.tabTittle.get(4).getText().substring(0, 18),"Selected Questions","Selected Questions tab is not displaying");
            Assert.assertEquals(newAssignment.selectedQuestion.isEnabled(),true,"selected question tab is not selected by default");
            newAssignment.tabTittle.get(3).click();//click on find question tab
            Assert.assertEquals(newAssignment.tabTittle.get(3).isEnabled(), true, "find question tab is not selected");
            Assert.assertEquals(newAssignment.searchButton.isDisplayed(),true,"search button is not displaying");
            Assert.assertEquals(newAssignment.browseButton.isDisplayed(),true,"browse button is not displaying");
            Assert.assertEquals(newAssignment.noQuestionFound.isDisplayed(),true,"questions are displaying by default");
            newAssignment.searchButton.click();//click on search button
            Assert.assertEquals(newAssignment.searchTextArea.isDisplayed(), true, "search text box is not selected");
            newAssignment.browseButton.click();//click on browse button
           // Assert.assertEquals(newAssignment.browseDropDown.isDisplayed(),true,"browse dropdown area is not displaying");
            newAssignment.searchButton.click();//click on search button
            newAssignment.searchTextArea.sendKeys("animals");
            newAssignment.searchButton.click();//click on search button
            boolean elementFound = false;
            try{
                driver.findElement(By.linkText("No Results found"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,false,"The questions relative to the search value is not displaying below");
            Thread.sleep(1000);
            new AssignLesson().selectQuestionForCustomAssignment("158");//select one question
            Thread.sleep(1000);
            Assert.assertEquals(newAssignment.checkCheckedBox.isDisplayed(),true,"checkbox is not checked");
            String searchText=newAssignment.searchText.get(0).getText();
            String selectedQuestionCount1=newAssignment.tabTittle.get(4).getText().substring(20,21);
            int questionCount1=Integer.parseInt(selectedQuestionCount1);
            if(questionCount > questionCount1 )
                Assert.fail("the question count of selected Question is not increased");
            newAssignment.tabTittle.get(4).click();//click on selected question sub tab
            Assert.assertEquals(newAssignment.selectedQuestion.isEnabled(),true,"selected question tab is not selected");
            String secondQuestionBar=newAssignment.secondQuestionBar.get(1).getText().trim();
            Thread.sleep(2000);
            if(!searchText.contains(secondQuestionBar))
                Assert.fail("Newly added questions is not displayed at the end of the previously saved question list");
            newAssignment.pointCheckBox.get(0).clear();
            newAssignment.pointCheckBox.get(0).sendKeys("2");
            Assert.assertEquals(newAssignment.pointCheckBox.get(0).getAttribute("value"), "2", "Points available for that question is not 2");
            newAssignment.manualGradingCheckBox.click();//click on manual grading checkbox
            Assert.assertEquals(newAssignment.manualGradingCheckedCheckBox.isDisplayed(), true, "the checkbox is not checked");
            myQuestionBank.getSaveForLater().click();//click on save for later
            Assert.assertEquals(newAssignment.notification_message.getText(), "Updated Custom Assessment Successfully.", "Saved Custom Assignment Successfully message is not displaying at the top");
            Thread.sleep(5000);
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='resource-title']")));
            new AssignLesson().Assigncustomeassignemnt(158);//assign assignment
            Thread.sleep(2000);
            new LoginUsingLTI().ltiLogin("158_1");//login as student
            new Assignment().submitAssignmentAsStudent(158);
            new LoginUsingLTI().ltiLogin("158");//login as instructor
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            String title1 = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title1, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            dashboard.getMainNavigator().click();//click on left corner main navigator
            dashboard.getAssignments().click();//click on '>' icon adjacent to assignment
            List<WebElement> subMenu1 = dashboard.subMenu;
            Assert.assertEquals(subMenu1.get(0).getText(), "New Assignment", "New Assignment submenu option is not available");
            Assert.assertEquals(subMenu1.get(1).getText(), "Current Assignments", "Current Assignments submenu option is not available");
            Assert.assertEquals(subMenu1.get(2).getText(), "My Question Bank", "My Question Bank submenu option is not available");
            Assert.assertEquals(subMenu1.get(3).getText(), "Policies", "Policies submenu option is not available");
            dashboard.getMainNavigatorAfterSelected().click();//click on main navigator
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.getViewGrade_link().click();//click on view response link
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            Assert.assertEquals(assignmentResponsesPage.pointsOnQuestion.getText(),"1.0","the points is not same with the points given at the time of creation of custom assignment");

        } catch (Exception e) {
            Assert.fail("Exception in test case endToEndFlowOfCustomAssignment of class InstructorDeleteQuestionsFromAlreadySavedCustomAssessment ", e);
        }
    }
}
