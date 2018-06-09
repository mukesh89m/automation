package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT21.R215;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by priyanka on 6/23/2015.
 */
public class InstructorAbleToSearchAndAddMoreQuestions extends Driver {
    @Test(priority = 1, enabled = true)
    public void instructorAbleToSearchAndAddMoreQuestions() {
        try {
            //tc row no 65
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "65");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "65");
            new LoginUsingLTI().ltiLogin("65");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("65");//select two question
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
            new AssignLesson().selectQuestionForCustomAssignment("65");//select one question
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
            Assert.assertEquals(newAssignment.pointCheckBox.get(1).isDisplayed(),true,"Points available for that question is not available ");
            Assert.assertEquals(newAssignment.pointCheckBox.get(1).getAttribute("value"),"1","Points available for that question is not 1 by default");


        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToSearchAndAddMoreQuestions of class InstructorAbleToSearchAndAddMoreQuestions ", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void instructorVerifyThatTheAddedQuestionsAreDiscarded() {
        try {
            //tc row no 78
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "78");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "78");
            new LoginUsingLTI().ltiLogin("78");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("78");//select one question
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
            new AssignLesson().selectQuestionForCustomAssignment("78");//select one question
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
            newAssignment.close_Icon.click();//click on close
            newAssignment.yesOnPopUp.click();//click on yes
            String myQuestionBankTitle1 = myQuestionBank.getMyQuestionBankTitle().getAttribute("title");
            Assert.assertEquals(myQuestionBankTitle1, "My Question Bank", "MyQuestionBank page is not opened");
            myQuestionBank.editThis.click();//click on edit this
            Assert.assertEquals(newAssignment.tabTittle.get(2).getText(), "New Name", "A New tab is not opened next to Question Bank tab to edit the Custom Assessment ");
            if(newAssignment.questionBar.size() < 1)
                Assert.fail("Newly added questions is displaying with the saved question list");


        } catch (Exception e) {
            Assert.fail("Exception in test case instructorVerifyThatTheAddedQuestionsAreDiscarded of class InstructorAbleToSearchAndAddMoreQuestions ", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void instructorAbleToAssignTheSavedCustomAssessment() {
        try {
            //tc row no 92
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "92");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "92");
            new LoginUsingLTI().ltiLogin("92");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            new AssignLesson().selectQuestionForCustomAssignment("92");//select one question
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
            newAssignment.assignNowButton.click();//click on assign now
            new AssignLesson().assignTOCWithDefaultClassSection(92);//assign assignment
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
            Assert.fail("Exception in test case instructorAbleToAssignTheSavedCustomAssessment of class InstructorAbleToSearchAndAddMoreQuestions ", e);
        }
    }
}
