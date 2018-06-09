package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R187;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Resources.AllResources;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Resources.MyResources;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by priyanka on 1/7/2015.
 */
public class AssignmentSubMenuFunctionality extends Driver{
    @Test(priority = 1, enabled = true)
    public void userShouldAbleToSeeTheSubMenuOptionsUnderAssignment() {
        try {
            //tc row no 10
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            new LoginUsingLTI().ltiLogin("10");//login as instructor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            dashboard.getMainNavigator().click();//click on left corner main navigator
            dashboard.getAssignments().click();//click on '>' icon adjacent to assignment
            dashboard.getNewAssignments().click();//click on new assignment
            dashboard.getCrossIcon().click();//click on cross icon
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            new Navigator().NavigateTo("Policies");//click on policies
        } catch (Exception e) {
            Assert.fail("Exception in test case userShouldAbleToSeeTheSubMenuOptionsUnderAssignment in class AssignmentSubMenuFunctionality", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void userShouldAbleToSeeTheSubMenuOptionsAsMentor() {
        try {
            //tc row no 10
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            new LoginUsingLTI().ltiLogin("11");//login as mentor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            dashboard.getMainNavigator().click();//click on left corner main navigator
            dashboard.getAssignments().click();//click on '>' icon adjacent to assignment
            dashboard.getNewAssignments().click();//click on new assignment
            dashboard.getCrossIcon().click();//click on cross icon
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            new Navigator().NavigateTo("Policies");//click on policies
        } catch (Exception e) {
            Assert.fail("Exception in test case userShouldAbleToSeeTheSubMenuOptionsAsMentor in class AssignmentSubMenuFunctionality", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void assignmentSubmenuFunctionalityForCurrentAssignments() {
        try {
            //tc row no 14
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("14");//login as instructor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            dashboard.getMainNavigator().click();//click on left corner main navigator
            dashboard.getAssignments().click();//click on '>' icon adjacent to assignment
            dashboard.getNewAssignments().click();//click on new assignment
            dashboard.getCrossIcon().click();//click on cross icon
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            new Navigator().NavigateTo("Policies");//click on policies
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            String currentAssignmentTitle = currentAssignments.getCurrentAssignmentTitle().getAttribute("title");
            Assert.assertEquals(currentAssignmentTitle, "Current Assignments", "CurrentAssignment page is not opened");

        } catch (Exception e) {
            Assert.fail("Exception in test case assignmentSubmenuFunctionalityForCurrentAssignments in class AssignmentSubMenuFunctionality", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void assignmentSubmenuFunctionalityForCurrentAssignmentsAsMentor() {
        try {
            //tc row no 14
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("15");//login as mentor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            dashboard.getMainNavigator().click();//click on left corner main navigator
            dashboard.getAssignments().click();//click on '>' icon adjacent to assignment
            dashboard.getNewAssignments().click();//click on new assignment
            dashboard.getCrossIcon().click();//click on cross icon
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            new Navigator().NavigateTo("Policies");//click on policies
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            String currentAssignmentTitle = currentAssignments.getCurrentAssignmentTitle().getAttribute("title");
            Assert.assertEquals(currentAssignmentTitle, "Current Assignments", "CurrentAssignment page is not opened");

        } catch (Exception e) {
            Assert.fail("Exception in test case assignmentSubmenuFunctionalityForCurrentAssignmentsAsMentor in class AssignmentSubMenuFunctionality", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void navigateToQuestionBanksPageFromSubmenuOptionsUnderAssignment() {
        try {
            //tc row no 19
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            new LoginUsingLTI().ltiLogin("19");//login as instructor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            dashboard.getMainNavigator().click();//click on left corner main navigator
            dashboard.getAssignments().click();//click on '>' icon adjacent to assignment
            dashboard.getNewAssignments().click();//click on new assignment
            dashboard.getCrossIcon().click();//click on cross icon
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            new Navigator().NavigateTo("Policies");//click on policies
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            Thread.sleep(1000);
            questionBank.getQuestionBankTitle().click();//click on question bank
            String questionBankTitle = questionBank.getQuestionBankTitle().getAttribute("title");
            Assert.assertEquals(questionBankTitle, "Question Banks", "QuestionBank page is not opened");

        } catch (Exception e) {
            Assert.fail("Exception in test case navigateToQuestionBanksPageFromSubmenuOptionsUnderAssignment in class AssignmentSubMenuFunctionality", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void navigateToQuestionBanksPageFromSubmenuOptionsAsMentor() {
        try {
            //tc row no 19
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            new LoginUsingLTI().ltiLogin("20");//login as mentor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            dashboard.getMainNavigator().click();//click on left corner main navigator
            dashboard.getAssignments().click();//click on '>' icon adjacent to assignment
            dashboard.getNewAssignments().click();//click on new assignment
            dashboard.getCrossIcon().click();//click on cross icon
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            new Navigator().NavigateTo("Policies");//click on policies
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            Thread.sleep(1000);
            questionBank.getQuestionBankTitle().click();//click on question bank
            String questionBankTitle = questionBank.getQuestionBankTitle().getAttribute("title");
            Assert.assertEquals(questionBankTitle, "Question Banks", "QuestionBank page is not opened");

        } catch (Exception e) {
            Assert.fail("Exception in test case navigateToQuestionBanksPageFromSubmenuOptionsAsMentor in class AssignmentSubMenuFunctionality", e);
        }
    }

    @Test(priority = 7, enabled = true)
    public void navigateToMyQuestionBanksPageFromSubmenuOptionsUnderAssignment() {
        try {
            //tc row no 23
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            new LoginUsingLTI().ltiLogin("23");//login as instructor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            dashboard.getMainNavigator().click();//click on left corner main navigator
            dashboard.getAssignments().click();//click on '>' icon adjacent to assignment
            dashboard.getNewAssignments().click();//click on new assignment
            dashboard.getCrossIcon().click();//click on cross icon
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            new Navigator().NavigateTo("Policies");//click on policies
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            String myQuestionBankTitle = myQuestionBank.getMyQuestionBankTitle().getAttribute("title");
            Assert.assertEquals(myQuestionBankTitle, "My Question Bank", "MyQuestionBank page is not opened");

        } catch (Exception e) {
            Assert.fail("Exception in test case navigateToMyQuestionBanksPageFromSubmenuOptionsUnderAssignment in class AssignmentSubMenuFunctionality", e);
        }
    }

    @Test(priority = 8, enabled = true)
    public void navigateToMyQuestionBanksPageFromSubmenuOptionsAsMentor() {
        try {
            //tc row no 23
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            new LoginUsingLTI().ltiLogin("24");//login as mentor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            dashboard.getMainNavigator().click();//click on left corner main navigator
            dashboard.getAssignments().click();//click on '>' icon adjacent to assignment
            dashboard.getNewAssignments().click();//click on new assignment
            dashboard.getCrossIcon().click();//click on cross icon
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            new Navigator().NavigateTo("Policies");//click on policies
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            String myQuestionBankTitle = myQuestionBank.getMyQuestionBankTitle().getAttribute("title");
            Assert.assertEquals(myQuestionBankTitle, "My Question Bank", "MyQuestionBank page is not opened");

        } catch (Exception e) {
            Assert.fail("Exception in test case navigateToMyQuestionBanksPageFromSubmenuOptionsAsMentor in class AssignmentSubMenuFunctionality", e);
        }
    }

    @Test(priority = 9, enabled = true)
    public void navigateToAssignmentPoliciesFromSubmenuOptionsAsInstructor() {
        try {
            //tc row no 27
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Policies policies = PageFactory.initElements(driver, Policies.class);
            new LoginUsingLTI().ltiLogin("27");//login as instructor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            dashboard.getMainNavigator().click();//click on left corner main navigator
            dashboard.getAssignments().click();//click on '>' icon adjacent to assignment
            dashboard.getNewAssignments().click();//click on new assignment
            dashboard.getCrossIcon().click();//click on cross icon
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            new Navigator().NavigateTo("Policies");//click on policies
            String policiesTitle = policies.getAssignmentPolicies().getAttribute("title");
            Assert.assertEquals(policiesTitle, "Assignment Policies", "Assignment Policies page is not opened");

        } catch (Exception e) {
            Assert.fail("Exception in test case navigateToAssignmentPoliciesFromSubmenuOptionsAsInstructor in class AssignmentSubMenuFunctionality", e);
        }
    }

    @Test(priority = 10, enabled = true)
    public void navigateToAssignmentPoliciesFromSubmenuOptionsAsMentor() {
        try {
            //tc row no 27
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Policies policies = PageFactory.initElements(driver, Policies.class);
            new LoginUsingLTI().ltiLogin("28");//login as mentor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            dashboard.getMainNavigator().click();//click on left corner main navigator
            dashboard.getAssignments().click();//click on '>' icon adjacent to assignment
            dashboard.getNewAssignments().click();//click on new assignment
            dashboard.getCrossIcon().click();//click on cross icon
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            new Navigator().NavigateTo("Policies");//click on policies
            String policiesTitle = policies.getAssignmentPolicies().getAttribute("title");
            Assert.assertEquals(policiesTitle, "Assignment Policies", "Assignment Policies page is not opened");

        } catch (Exception e) {
            Assert.fail("Exception in test case navigateToAssignmentPoliciesFromSubmenuOptionsAsMentor in class AssignmentSubMenuFunctionality", e);
        }
    }

    @Test(priority = 11, enabled = true)
    public void userShouldBeBableToSeeTheSubmenuOptionsUnderResources() {
        try {
            //tc row no 31
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            new LoginUsingLTI().ltiLogin("31");//login as instructor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Resources");//click on Resources
            new Navigator().NavigateTo("All Resources");//click on All Resources
            new Navigator().NavigateTo("My Resources");//click on My Resources

        } catch (Exception e) {
            Assert.fail("Exception in test case userShouldBeBableToSeeTheSubmenuOptionsUnderResources in class AssignmentSubMenuFunctionality", e);
        }
    }

    @Test(priority = 12, enabled = true)
    public void userShouldBeBableToSeeTheSubmenuOptionsAsMentor() {
        try {
            //tc row no 31
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            new LoginUsingLTI().ltiLogin("32");//login as mentor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Resources");//click on Resources
            new Navigator().NavigateTo("All Resources");//click on All Resources
            new Navigator().NavigateTo("My Resources");//click on My Resources

        } catch (Exception e) {
            Assert.fail("Exception in test case userShouldBeBableToSeeTheSubmenuOptionsAsMentor in class AssignmentSubMenuFunctionality", e);
        }
    }

    @Test(priority = 13, enabled = true)
    public void userShouldBeAbleToNavigateToAllResourcespageAsInstructor() {
        try {
            //tc row no 35
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            AllResources allResources = PageFactory.initElements(driver, AllResources.class);
            new LoginUsingLTI().ltiLogin("35");//login as instructor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Resources");//click on Resources
            new Navigator().NavigateTo("All Resources");//click on All Resources
            new Navigator().NavigateTo("My Resources");//click on My Resources
            new Navigator().NavigateTo("All Resources");//click on All Resources
            String allResourceTitle = allResources.getAllResources().getAttribute("title");
            Assert.assertEquals(allResourceTitle, "All Resources", "All Resources page is not opened");

        } catch (Exception e) {
            Assert.fail("Exception in test case userShouldBeAbleToNavigateToAllResourcespageAsInstructor in class AssignmentSubMenuFunctionality", e);
        }
    }

    @Test(priority = 14, enabled = true)
    public void userShouldBeAbleToNavigateToAllResourcespageAsMentor() {
        try {
            //tc row no 35
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            AllResources allResources = PageFactory.initElements(driver, AllResources.class);
            new LoginUsingLTI().ltiLogin("36");//login as mentor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Resources");//click on Resources
            new Navigator().NavigateTo("All Resources");//click on All Resources
            new Navigator().NavigateTo("My Resources");//click on My Resources
            new Navigator().NavigateTo("All Resources");//click on All Resources
            String allResourceTitle = allResources.getAllResources().getAttribute("title");
            Assert.assertEquals(allResourceTitle, "All Resources", "All Resources page is not opened");

        } catch (Exception e) {
            Assert.fail("Exception in test case userShouldBeAbleToNavigateToAllResourcespageAsMentor in class AssignmentSubMenuFunctionality", e);
        }
    }

    @Test(priority = 15, enabled = true)
    public void userShouldBeAbleToNavigateToMyResourcespageAsInstructor() {
        try {
            //tc row no 40
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MyResources myResources = PageFactory.initElements(driver, MyResources.class);
            new LoginUsingLTI().ltiLogin("40");//login as instructor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Resources");//click on Resources
            new Navigator().NavigateTo("All Resources");//click on All Resources
            new Navigator().NavigateTo("My Resources");//click on My Resources
            String myResourceTitle = myResources.getMyResourceTitle().getAttribute("title");
            Assert.assertEquals(myResourceTitle, "My Resources", "My Resources page is not opened");

        } catch (Exception e) {
            Assert.fail("Exception in test case userShouldBeAbleToNavigateToMyResourcespageAsInstructor in class AssignmentSubMenuFunctionality", e);
        }
    }

    @Test(priority = 16, enabled = true)
    public void viewAllFunctionalityForAssignmentsPanelAsInstructor() {
        try {
            //tc row no 44
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("44");//login as instructor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            dashboard.getViewAllLink().click();//click on viewall link
            Thread.sleep(5000);
            String currentAssignmentTitle = currentAssignments.getCurrentAssignmentTitle().getAttribute("title");
            Assert.assertEquals(currentAssignmentTitle, "Current Assignments", "CurrentAssignment page is not opened");

        } catch (Exception e) {
            Assert.fail("Exception in test case viewAllFunctionalityForAssignmentsPanelAsInstructor in class AssignmentSubMenuFunctionality", e);
        }
    }

    @Test(priority = 17, enabled = true)
    public void viewAllFunctionalityForAssignmentsPanelAsMentor() {
        try {
            //tc row no 44
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("45");//login as mentor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            dashboard.getViewAllLink().click();//click on viewall link
            Thread.sleep(5000);
            String currentAssignmentTitle = currentAssignments.getCurrentAssignmentTitle().getAttribute("title");
            Assert.assertEquals(currentAssignmentTitle, "Current Assignments", "CurrentAssignment page is not opened");

        } catch (Exception e) {
            Assert.fail("Exception in test case viewAllFunctionalityForAssignmentsPanelAsMentor in class AssignmentSubMenuFunctionality", e);
        }
    }

    @Test(priority = 18, enabled = true)
    public void navigateToQuestionBankspageThroughNewAssignmentButton() {
        try {
            //tc row no 46
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            new LoginUsingLTI().ltiLogin("46");//login as instructor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            dashboard.getNewAssignmentButton().click();//click on +newassignment
            newAssignment.getUsePreCreatedAssignment().click();//click on useprecreate assignment
            String questionBankTitle = questionBank.getQuestionBankTitle().getAttribute("title");
            Assert.assertEquals(questionBankTitle, "Question Banks", "QuestionBank page is not opened");
        } catch (Exception e) {
            Assert.fail("Exception in test case navigateToQuestionBankspageThroughNewAssignmentButton in class AssignmentSubMenuFunctionality", e);
        }
    }

    @Test(priority = 19, enabled = true)
    public void navigateToQuestionBankspageThroughNewAssignmentButtonAsMentor() {
        try {
            //tc row no 46
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            new LoginUsingLTI().ltiLogin("47");//login as mentor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            dashboard.getNewAssignmentButton().click();//click on +newassignment
            newAssignment.getUsePreCreatedAssignment().click();//click on useprecreate assignment
            String questionBankTitle = questionBank.getQuestionBankTitle().getAttribute("title");
            Assert.assertEquals(questionBankTitle, "Question Banks", "QuestionBank page is not opened");
        } catch (Exception e) {
            Assert.fail("Exception in test case navigateToQuestionBankspageThroughNewAssignmentButtonAsMentor in class AssignmentSubMenuFunctionality", e);
        }
    }

    @Test(priority = 20, enabled = true)
    public void actionsLayoutFunctionalityAsInstructor() {
        try {
            //tc row no 67
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            new Assignment().create(67);
            new LoginUsingLTI().ltiLogin("67");//login as instructor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            dashboard.getMainNavigator().click();//click on left corner main navigator
            dashboard.getAssignments().click();//click on '>' icon adjacent to assignment
            dashboard.getNewAssignments().click();//click on new assignment
            dashboard.getCrossIcon().click();//click on cross icon
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            new Navigator().NavigateTo("Policies");//click on policies
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            questionBank.getQuestionBankTitle().click();//click on question bank
            Thread.sleep(2000);
            new UIElement().waitAndFindElement(By.className("ls-actions"));
            String questionBankTitle = questionBank.getQuestionBankTitle().getAttribute("title");
            Assert.assertEquals(questionBankTitle, "Question Banks", "QuestionBank page is not opened");

            String addtomyquestionbank = questionBank.getAddToMyQuestionBank().getAttribute("title");
            Assert.assertEquals(addtomyquestionbank, "Add to My Question Bank", "Add to My Question Bank is not displaying");
            String assignThis = questionBank.getAssignThis().getAttribute("title");
            Assert.assertEquals(assignThis, "Assign This", "Assign This is not displaying");
            String customizeThis = questionBank.getCustomizeThis().getAttribute("title");
            Assert.assertEquals(customizeThis, "Customize This", "Customize This is not displaying");
            questionBank.getPreviewButton().click();//click on preview

            new WebDriverWait(driver,200).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[title='Assessment_67']")));
            String assessment = driver.findElement(By.cssSelector("span[title='Assessment_67']")).getAttribute("title");
            Assert.assertEquals(assessment, "Assessment_67", "Assessment is not displaying");
            questionBank.getQuestionBankTitle().click();//click on question bank
            Thread.sleep(4000);
            questionBank.getAddToMyQuestionBank().click();//click on add to my question bank
            String notificationMessage = questionBank.getNotificationMessage().get(0).getText().trim();
            Assert.assertEquals(notificationMessage, "1 Assessment added to My Question Bank.", "notofication message is not displaying");
            String removeFromQuestionBank = questionBank.getRemoveFromMyQuestionBank().getAttribute("title");
            Assert.assertEquals(removeFromQuestionBank, "Remove from My Question Bank", "Remove from My Question Bank is not displaying");
            questionBank.getAssignThisButtton().click();//click on assign this
            String popup = questionBank.getPopupHeader().getAttribute("title");
            Assert.assertEquals(popup, "Assessment_67", "popup window is not opened");
            questionBank.getCustomizeThis().click();//click on customize this
            Thread.sleep(2000);
            String newAssignmentTitle = newAssignment.getNewAssignment().getAttribute("title").trim();
            Assert.assertEquals(newAssignmentTitle, "New Assignment", "New Assignment is not displaying");
            String findQuestion = newAssignment.getFindQuestion().getAttribute("title");
            Assert.assertEquals(findQuestion, "Find Questions", "find question tab is not selected bydefault");


        } catch (Exception e) {
            Assert.fail("Exception in test case actionsLayoutFunctionalityAsInstructor in class AssignmentSubMenuFunctionality", e);
        }
    }

    @Test(priority = 21, enabled = true)
    public void addToMyQuestionBankLinkFunctionality() {
        try {
            //tc row no 76
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            new Assignment().create(76);
            new LoginUsingLTI().ltiLogin("76");//login as instructor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            dashboard.getMainNavigator().click();//click on left corner main navigator
            dashboard.getAssignments().click();//click on '>' icon adjacent to assignment
            dashboard.getNewAssignments().click();//click on new assignment
            dashboard.getCrossIcon().click();//click on cross icon
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            new Navigator().NavigateTo("Policies");//click on policies
            new Navigator().NavigateTo("My Question Bank");//click on myquestionbank
            questionBank.getQuestionBankTitle().click();//click on question bank
            new UIElement().waitAndFindElement(By.className("bookmark-assign-this-wrapper"));
            Thread.sleep(2000);
            String questionBankTitle = questionBank.getQuestionBankTitle().getAttribute("title");
            Assert.assertEquals(questionBankTitle, "Question Banks", "QuestionBank page is not opened");
            String addtomyquestionbank = questionBank.getAddToMyQuestionBank().getAttribute("title");
            Assert.assertEquals(addtomyquestionbank, "Add to My Question Bank", "Add to My Question Bank is not displaying");

            String assignThis = questionBank.getAssignThis().getAttribute("title");
            Assert.assertEquals(assignThis, "Assign This", "Assign This is not displaying");
            String customizeThis = questionBank.getCustomizeThis().getAttribute("title");
            Assert.assertEquals(customizeThis, "Customize This", "Customize This is not displaying");
            questionBank.getQuestionBankTitle().click();//click on question bank
            questionBank.getAddToMyQuestionBank().click();//click on add to my question bank
            String notificationMessage = questionBank.getNotificationMessage().get(0).getText().trim();
            Assert.assertEquals(notificationMessage, "1 Assessment added to My Question Bank.", "notofication message is not displaying");
            questionBank.getCloseIconOnNavigationMessage().click();//click on close
            String removeFromQuestionBank = questionBank.getRemoveFromMyQuestionBank().getAttribute("title");
            Assert.assertEquals(removeFromQuestionBank, "Remove from My Question Bank", "Remove from My Question Bank is not displaying");
            Thread.sleep(2000);
            questionBank.getAddToMyQuestionBank().click();//click on add to my question bank
            String assessmentCount = questionBank.getMyQuestionBankCount().getText();
            Assert.assertEquals(assessmentCount, "1", "Assessment count is not displaying");
            Thread.sleep(6000);
            int notificationMessage1 = questionBank.getNotificationMessage().size();
            if (notificationMessage1 > 1) {
                Assert.fail("notification message is displaying");
            }
            String assessment = questionBank.getSecondAssessment().getText();
            System.out.println("ass" + assessment);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank

        } catch (Exception e) {
            Assert.fail("Exception in test case addToMyQuestionBankLinkFunctionality in class AssignmentSubMenuFunctionality", e);
        }
    }

    @Test(priority = 22, enabled = true)
    public void myReportsSubmenuFunctionality() {
        try {
            //tc row no 87
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            new LoginUsingLTI().ltiLogin("87");//login as instructor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            dashboard.getMainNavigator().click();//click on left corner main navigator
            driver.findElement(By.linkText("My Reports")).click();
            Thread.sleep(2000);
            dashboard.getMainNavigatorAfterSelected().click();//click on left corner main navigator
            new Navigator().NavigateTo("Proficiency Report");//click on proficiency report
            new Navigator().NavigateTo("Metacognitive Report");//click on Metacognitive Report
            new Navigator().NavigateTo("Productivity Report");//click on Productivity Report
            new Navigator().NavigateTo("Most Challenging Activities Report");//click on Most Challenging Activities Report
            new Navigator().NavigateTo("Engagement Report");//click on Engagement Report

        } catch (Exception e) {
            Assert.fail("Exception in test case myReportsSubmenuFunctionality in class AssignmentSubMenuFunctionality", e);
        }
    }

    @Test(priority = 23, enabled = true)
    public void myReportsSubmenuFunctionalityNavigateToProficiencyReport() {
        try {
            //tc row no 91
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            ProficiencyReport proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);
            new LoginUsingLTI().ltiLogin("91");//login as instructor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            dashboard.getMainNavigator().click();//click on left corner main navigator
            driver.findElement(By.linkText("My Reports")).click();
            Thread.sleep(2000);
            dashboard.getMainNavigatorAfterSelected().click();//click on left corner main navigator
            new Navigator().NavigateTo("Proficiency Report");//click on proficiency report
            new Navigator().NavigateTo("Metacognitive Report");//click on Metacognitive Report
            new Navigator().NavigateTo("Productivity Report");//click on Productivity Report
            new Navigator().NavigateTo("Most Challenging Activities Report");//click on Most Challenging Activities Report
            new Navigator().NavigateTo("Engagement Report");//click on Engagement Report
            new Navigator().NavigateTo("Proficiency Report");//click on proficiency report
            String proficiency = proficiencyReport.getProficiencyReportTitle().getText();
            Assert.assertEquals(proficiency, "Class Proficiency by Chapters", "Proficiency report page is not opened");


        } catch (Exception e) {
            Assert.fail("Exception in test case myReportsSubmenuFunctionalityNavigateToProficiencyReport in class AssignmentSubMenuFunctionality", e);
        }
    }

    @Test(priority = 24, enabled = true)
    public void myReportsSubmenuFunctionalityNavigateToMetacognitiveReport() {
        try {
            //tc row no 96
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MetacognitiveReport metacognitiveReport = PageFactory.initElements(driver,MetacognitiveReport.class);
            new LoginUsingLTI().ltiLogin("96");//login as instructor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Proficiency Report");//click on proficiency report
            new Navigator().NavigateTo("Metacognitive Report");//click on Metacognitive Report
            new Navigator().NavigateTo("Productivity Report");//click on Productivity Report
            new Navigator().NavigateTo("Most Challenging Activities Report");//click on Most Challenging Activities Report
            new Navigator().NavigateTo("Engagement Report");//click on Engagement Report
            new Navigator().NavigateTo("Metacognitive Report");//click on Metacognitive Report
            String metacognitive = metacognitiveReport.getMetacognitiveReportPage().getText();
            Assert.assertEquals(metacognitive, "Metacognitive Report", "Metacognitive Report page is not opened");
            dashboard.getMainNavigator().click();//click on left corner main navigator

        } catch (Exception e) {
            Assert.fail("Exception in test case myReportsSubmenuFunctionalityNavigateToMetacognitiveReport in class AssignmentSubMenuFunctionality", e);
        }
    }
    @Test(priority = 25, enabled = true)
    public void myReportsSubmenuFunctionalityNavigateToProductivityReport() {
        try {
            //tc row no 103
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            ProductivityReport productivityReport = PageFactory.initElements(driver,ProductivityReport.class);
            new LoginUsingLTI().ltiLogin("103");//login as instructor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Proficiency Report");//click on proficiency report
            new Navigator().NavigateTo("Metacognitive Report");//click on Metacognitive Report
            new Navigator().NavigateTo("Productivity Report");//click on Productivity Report
            new Navigator().NavigateTo("Most Challenging Activities Report");//click on Most Challenging Activities Report
            new Navigator().NavigateTo("Engagement Report");//click on Engagement Report
            new Navigator().NavigateTo("Productivity Report");//click on Productivity Report
            String productivity = productivityReport.getProductivityReportTitle().getText();
            Assert.assertEquals(productivity, "Productivity Report", "Productivity Report page is not opened");

        } catch (Exception e) {
            Assert.fail("Exception in test case myReportsSubmenuFunctionalityNavigateToProductivityReport in class AssignmentSubMenuFunctionality", e);
        }
    }
    @Test(priority = 26, enabled = true)
    public void myReportsSubmenuFunctionalityNavigateToMostChallengingActivitiesReport() {
        try {
            //tc row no 107
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MostChallengingReport mostChallengingReport = PageFactory.initElements(driver,MostChallengingReport.class);
            new LoginUsingLTI().ltiLogin("107");//login as instructor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Proficiency Report");//click on proficiency report
            new Navigator().NavigateTo("Metacognitive Report");//click on Metacognitive Report
            new Navigator().NavigateTo("Productivity Report");//click on Productivity Report
            new Navigator().NavigateTo("Most Challenging Activities Report");//click on Most Challenging Activities Report
            new Navigator().NavigateTo("Engagement Report");//click on Engagement Report
            new Navigator().NavigateTo("Most Challenging Activities Report");//click on Most Challenging Activities Report
            String mostChallenging = mostChallengingReport.getMostChallengingReportTitle().getText();
            Assert.assertEquals(mostChallenging, "Most Challenging Activities", "Most Challenging Activities page is not opened");

        } catch (Exception e) {
            Assert.fail("Exception in test case myReportsSubmenuFunctionalityNavigateToMostChallengingActivitiesReport in class AssignmentSubMenuFunctionality", e);
        }
    }

    @Test(priority = 27, enabled = true)
    public void myReportsSubmenuFunctionalityNavigateToEngagementReport() {
        try {
            //tc row no 111
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            EngagementReport engagementReport = PageFactory.initElements(driver,EngagementReport.class);
            new LoginUsingLTI().ltiLogin("111");//login as instructor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Proficiency Report");//click on proficiency report
            new Navigator().NavigateTo("Metacognitive Report");//click on Metacognitive Report
            new Navigator().NavigateTo("Productivity Report");//click on Productivity Report
            new Navigator().NavigateTo("Most Challenging Activities Report");//click on Most Challenging Activities Report
            new Navigator().NavigateTo("Engagement Report");//click on Engagement Report
            String engagement = engagementReport.getEngagementReportTitle().getText();
            Assert.assertEquals(engagement, "Student Engagement Report", "Engagement Report page is not opened");

        } catch (Exception e) {
            Assert.fail("Exception in test case myReportsSubmenuFunctionalityNavigateToEngagementReport in class AssignmentSubMenuFunctionality", e);
        }
    }
    @Test(priority = 28, enabled = true)
    public void myReportsSubmenuFunctionalityLoginAsStudent() {
        try {
            //tc row no 115
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            new LoginUsingLTI().ltiLogin("115");//login as student
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            dashboard.getMainNavigator().click();//click on left corner main navigator
            driver.findElement(By.linkText("My Reports")).click();
            Thread.sleep(2000);
            dashboard.getMainNavigatorAfterSelected().click();//click on left corner main navigator
            new Navigator().NavigateTo("Proficiency Report");//click on proficiency report
            new Navigator().NavigateTo("Metacognitive Report");//click on Metacognitive Report
            new Navigator().NavigateTo("Productivity Report");//click on Productivity Report
            new Navigator().NavigateTo("Most Challenging Activities Report");//click on Most Challenging Activities Report
        } catch (Exception e) {
            Assert.fail("Exception in test case myReportsSubmenuFunctionalityLoginAsStudent in class AssignmentSubMenuFunctionality", e);
        }
    }
    @Test(priority = 29, enabled = true)
    public void navigateToProficiencyReportLogInAsStudent() {
        try {
            //tc row no 119
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            ProficiencyReport proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);
            new LoginUsingLTI().ltiLogin("119");//login as student
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            dashboard.getMainNavigator().click();//click on left corner main navigator
            driver.findElement(By.linkText("My Reports")).click();
            Thread.sleep(2000);
            dashboard.getMainNavigatorAfterSelected().click();//click on left corner main navigator
            new Navigator().NavigateTo("Proficiency Report");//click on proficiency report
            new Navigator().NavigateTo("Metacognitive Report");//click on Metacognitive Report
            new Navigator().NavigateTo("Productivity Report");//click on Productivity Report
            new Navigator().NavigateTo("Most Challenging Activities Report");//click on Most Challenging Activities Report
            new Navigator().NavigateTo("Proficiency Report");//click on proficiency report
            String proficiency = proficiencyReport.getProficiencyReportTitleStudentSide().getText();
            Assert.assertEquals(proficiency, "Proficiency Report", "Proficiency report page is not opened");

        } catch (Exception e) {
            Assert.fail("Exception in test case navigateToProficiencyReportLogInAsStudent in class AssignmentSubMenuFunctionality", e);
        }
    }
    @Test(priority = 30, enabled = true)
    public void navigateToMetacognitiveReportLogInAsStudent() {
        try {
            //tc row no 124
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MetacognitiveReport metacognitiveReport = PageFactory.initElements(driver,MetacognitiveReport.class);
            new LoginUsingLTI().ltiLogin("124");//login as student
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Proficiency Report");//click on proficiency report
            new Navigator().NavigateTo("Metacognitive Report");//click on Metacognitive Report
            new Navigator().NavigateTo("Productivity Report");//click on Productivity Report
            new Navigator().NavigateTo("Most Challenging Activities Report");//click on Most Challenging Activities Report
            new Navigator().NavigateTo("Metacognitive Report");//click on Metacognitive Report
            Thread.sleep(3000);
            String metacognitive = metacognitiveReport.getMetacognitiveReportPageTitle().getAttribute("title");
            Assert.assertEquals(metacognitive, "Metacognitive Report", "Metacognitive Report page is not opened");
            dashboard.getMainNavigator().click();//click on left corner main navigator

        } catch (Exception e) {
            Assert.fail("Exception in test case navigateToMetacognitiveReportLogInAsStudent in class AssignmentSubMenuFunctionality", e);
        }
    }
    @Test(priority = 31, enabled = true)
    public void navigateToProductivityReportLogInAsStudent() {
        try {
            //tc row no 131
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            ProductivityReport productivityReport = PageFactory.initElements(driver,ProductivityReport.class);
            new LoginUsingLTI().ltiLogin("131");//login as student
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Proficiency Report");//click on proficiency report
            new Navigator().NavigateTo("Metacognitive Report");//click on Metacognitive Report
            new Navigator().NavigateTo("Productivity Report");//click on Productivity Report
            new Navigator().NavigateTo("Most Challenging Activities Report");//click on Most Challenging Activities Report
            new Navigator().NavigateTo("Productivity Report");//click on Productivity Report
            String productivity = productivityReport.getProductivityReportPageTitle().getAttribute("title");
            Assert.assertEquals(productivity, "Productivity Report", "Productivity Report page is not opened");

        } catch (Exception e) {
            Assert.fail("Exception in test case navigateToProductivityReportLogInAsStudent in class AssignmentSubMenuFunctionality", e);
        }
    }
    @Test(priority = 32, enabled = true)
    public void navigateToMostChallengingActivitiesReportLogInAsStudent() {
        try {
            //tc row no 135
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            MostChallengingReport mostChallengingReport = PageFactory.initElements(driver,MostChallengingReport.class);
            new LoginUsingLTI().ltiLogin("135");//login as student
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Proficiency Report");//click on proficiency report
            new Navigator().NavigateTo("Metacognitive Report");//click on Metacognitive Report
            new Navigator().NavigateTo("Productivity Report");//click on Productivity Report
            new Navigator().NavigateTo("Most Challenging Activities Report");//click on Most Challenging Activities Report
            String mostChallenging = mostChallengingReport.getMostChallengingReportPageTitle().getText();
            Assert.assertEquals(mostChallenging, "Most Challenging Activities", "Most Challenging Activities page is not opened");

        } catch (Exception e) {
            Assert.fail("Exception in test case navigateToMostChallengingActivitiesReportLogInAsStudent in class AssignmentSubMenuFunctionality", e);
        }
    }

}