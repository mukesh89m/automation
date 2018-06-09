package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT21.R215;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.QuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by priyanka on 6/23/2015.
 */
public class InstructorAbleToLunchEditCustomAssessment extends Driver {
    @Test(priority = 1, enabled = true)
    public void instructorAbleToLunchEditCustomAssessment()
    {
        try {

            //tc row no 9
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            new LoginUsingLTI().ltiLogin("9");//login as instructor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            dashboard.getMainNavigator().click();//click on left corner main navigator
            dashboard.getAssignments().click();//click on '>' icon adjacent to assignment
            List<WebElement> subMenu=dashboard.subMenu;
            Assert.assertEquals(subMenu.get(0).getText(),"New Assignment","New Assignment submenu option is not available");
            Assert.assertEquals(subMenu.get(1).getText(),"Current Assignments","Current Assignments submenu option is not available");
            Assert.assertEquals(subMenu.get(2).getText(),"My Question Bank","My Question Bank submenu option is not available");
            Assert.assertEquals(subMenu.get(3).getText(),"Policies","Policies submenu option is not available");
            dashboard.getMainNavigatorAfterSelected().click();//click on main navigator
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            String myQuestionBankTitle = myQuestionBank.getMyQuestionBankTitle().getAttribute("title");
            Assert.assertEquals(myQuestionBankTitle, "My Question Bank", "MyQuestionBank page is not opened");


        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToLunchEditCustomAssessment of class InstructorAbleToLunchEditCustomAssessment ", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void instructorAbleToCreateAndSaveCustomAssignment() {
        try {

            //tc row no 13
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "13");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "13");
            new LoginUsingLTI().ltiLogin("13");//login as instructor
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
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("13");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Assert.assertEquals(newAssignment.notification_message.getText(), "Saved Custom Assessment Successfully.", "Saved Custom Assignment Successfully message is not displaying at the top");
            Thread.sleep(5000);

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToCreateAndSaveCustomAssignment of class InstructorAbleToLunchEditCustomAssessment ", e);
        }
    }
    @Test(priority = 3, enabled = true)
    public void customAssignmentCreatedFromCustomizeThis() {
        try {

            //tc row no 21
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "21");
            new LoginUsingLTI().ltiLogin("21");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            Thread.sleep(1000);
            questionBank.getQuestionBankTitle().click();//click on question bank
            Thread.sleep(1000);
            questionBank.getCustomizeThis().click();//click on customize this link
            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("21");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(8000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='resource-title']")));
            Assert.assertEquals(myQuestionBank.getAssessment().getText(),"New_Name","created custom assignment is not added under my question bank");
            Assert.assertEquals(myQuestionBank.editThis.getText(),"Edit This","Edit This link is not available");


        } catch (Exception e) {
            Assert.fail("Exception in test case customAssignmentCreatedFromCustomizeThis of class InstructorAbleToLunchEditCustomAssessment ", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void instructorAbleToSeeTheSavedCustomAssignment() {
        try {
            //tc row no 22
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "22");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "22");
            new LoginUsingLTI().ltiLogin("22");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("22");//select two question
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
            Assert.assertEquals(myQuestionBank.getAssessment().getText(),"New Name","created custom assignment is not added under my question bank");
            Assert.assertEquals(myQuestionBank.editThis.getText(),"Edit This","Edit This link is not available");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSeeTheSavedCustomAssignment of class InstructorAbleToLunchEditCustomAssessment ", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void instructorShouldNotAbleToSeeTheEditThisLinkAfterAssigning() {
        try {
            //tc row no 29
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "29");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "29");
            new LoginUsingLTI().ltiLogin("29");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("29");//select two question
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
            Assert.assertEquals(myQuestionBank.getAssessment().getText(),"New Name","created custom assignment is not added under my question bank");
            Assert.assertEquals(myQuestionBank.editThis.getText(),"Edit This","Edit This link is not available");
            new AssignLesson().Assigncustomeassignemnt(29);//assign assignment
            Thread.sleep(2000);
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            boolean elementFound = false;
            try{
                driver.findElement(By.linkText("Edit This"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,false,"Edit option is available");
            Thread.sleep(1000);


        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldNotAbleToSeeTheEditThisLinkAfterAssigning of class InstructorAbleToLunchEditCustomAssessment ", e);
        }
    }



    @Test(priority = 6, enabled = true)
    public void customAssignmentAssigningFromCustomizeThis() {
        try {
            //tc row no 38
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "38");
            new LoginUsingLTI().ltiLogin("38");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            Thread.sleep(1000);
            questionBank.getQuestionBankTitle().click();//click on question bank
            Thread.sleep(1000);
            questionBank.getCustomizeThis().click();//click on customize this link
            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("38");//select one question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            newAssignment.assignNowButton.click();//click on assign now
            new AssignLesson().assignTOCWithDefaultClassSection(38);//assign assignment
            Thread.sleep(2000);
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            boolean elementFound = false;
            try{
                driver.findElement(By.linkText("Edit This"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,false,"Edit option is available");
            Thread.sleep(1000);

        } catch (Exception e) {
            Assert.fail("Exception in test case customAssignmentAssigningFromCustomizeThis of class InstructorAbleToLunchEditCustomAssessment ", e);
        }
    }


       @Test(priority = 7, enabled = true)
    public void instructorAbleToClickOnEditThisLink() {
        try {
            //tc row no 40
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "40");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "40");
            new LoginUsingLTI().ltiLogin("40");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("40");//select two question
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
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys("1");
            newAssignment.descriptionField.click();//click on description field
            newAssignment.descriptionTextArea.sendKeys("Description");
            Assert.assertEquals(newAssignment.tabTittle.get(3).getText(), "Find Questions", "Find Questions tab is not displaying");
            Assert.assertEquals(newAssignment.tabTittle.get(4).getText().substring(0,18),"Selected Questions","Selected Questions tab is not displaying");
            // Assert.assertEquals(newAssignment.tabTittle.get(4).isSelected(),true,"selected question tab is not selected by default");


        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToClickOnEditThisLink of class InstructorAbleToLunchEditCustomAssessment ", e);
        }
    }

    @Test(priority = 8, enabled = true)
    public void instructorAbleToViewAssessmentDetailsOnThePageWhileEditing() {
        try {
            //tc row no 50
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "50");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "50");
            new LoginUsingLTI().ltiLogin("50");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("50");//select two question
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
            Assert.assertEquals(newAssignment.tabTittle.get(4).getText().substring(0, 18),"Selected Questions","Selected Questions tab is not displaying");
            Assert.assertEquals(newAssignment.delete_Icon.isDisplayed(),true,"Delete icon is not available before the question number on the question card");
            Assert.assertEquals(newAssignment.questionLabel.isDisplayed(),true,"question label is not available before the question number on the question card");
            new UIElement().waitAndFindElement(By.id("showExpendQuestionIcon"));
            Assert.assertEquals(newAssignment.expandIcon.get(0).isDisplayed(),true,"expand icon is not available");
            Assert.assertEquals(newAssignment.manualGradingCheckBox.isDisplayed(),true,"manual grading check box is not available");
            Assert.assertEquals(newAssignment.pointCheckBox.get(0).isDisplayed(),true,"points check box is not available");
            newAssignment.pointCheckBox.clear();//clear the check box
            newAssignment.pointCheckBox.get(0).sendKeys("2");
            String questionType=newAssignment.questionType.getText();
            Assert.assertEquals("Question Type:True/False","Question Type:"+questionType+"","question type is not available");
            Thread.sleep(2000);
            Assert.assertEquals(newAssignment.expandAllIcon.isDisplayed(),true,"Expand icon is not available at the top end of the question card");
            newAssignment.expandAllIcon.click();//click on expand all icon
            Assert.assertEquals(newAssignment.collapseAllIcon.isDisplayed(),true,"Collapse icon is not available at the top end of the question card");
            Assert.assertEquals(newAssignment.difficultyLevel.isDisplayed(),true,"All the questions under Selected Questions is not expanded");
            newAssignment.collapseAllIcon.click();//click on collapse icon
            boolean elementFound = false;
            try{
                driver.findElement(By.linkText("Difficulty Level"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,false,"selected question is not collapsed");


        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToViewAssessmentDetailsOnThePageWhileEditing of class InstructorAbleToLunchEditCustomAssessment ", e);
        }
    }



}

