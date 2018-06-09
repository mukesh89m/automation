package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R199;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;

/**
 * Created by priyanka on 3/5/2015.
 */
public class CreationAndAssignmentOfTheFileBasedAssignment extends Driver {
    @Test(priority = 1, enabled = true)
    public void newAssignmentPopupFunctionality() {

        try {
            //tc row no 9
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("9");//login as instructor
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
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            currentAssignments.newAssignment_Button.click();//click on new assignment;
            if (currentAssignments.close_Icon.isDisplayed() == false) {
                Assert.fail("Create New Assignment pop-up window is not opened with three different types of Assignments");
            }
            Assert.assertEquals(currentAssignments.createCustomAssignmentButton.getText(), "Create Custom Assignment", "Create Custom Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.usePreCreatedAssignmentButton.getText(), "Use Pre-created Assignment", "Use Pre-created Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.createFileBasedAssignmentButton.getText(), "Create File based Assignment", "Create File based Assignment button is not displaying");
            currentAssignments.close_Icon.click();//click on 'x'
            boolean verifyPopUp = new BooleanValue().presenceOfElement(9, By.cssSelector("div[class='ls-inst-dashboard-assignment-popup-button ls--create-custom-assignment-view']"));
            Assert.assertEquals(verifyPopUp, false, "The pop-up window is not closed");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            dashboard.getNewAssignmentButton().click();//click on new assignment;
            if (currentAssignments.close_Icon.isDisplayed() == false) {
                Assert.fail("Create New Assignment pop-up window is not opened with three different types of Assignments");
            }
            Assert.assertEquals(currentAssignments.createCustomAssignmentButton.getText(), "Create Custom Assignment", "Create Custom Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.usePreCreatedAssignmentButton.getText(), "Use Pre-created Assignment", "Use Pre-created Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.createFileBasedAssignmentButton.getText(), "Create File based Assignment", "Create File based Assignment button is not displaying");
            currentAssignments.close_Icon.click();//click on 'x'
            boolean verifyPopUp1 = new BooleanValue().presenceOfElement(9, By.cssSelector("div[class='ls-inst-dashboard-assignment-popup-button ls--create-custom-assignment-view']"));
            Assert.assertEquals(verifyPopUp1, false, "The pop-up window is not closed");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            String currentAssignmentTitle = currentAssignments.getCurrentAssignmentTitle().getAttribute("title");
            Assert.assertEquals(currentAssignmentTitle, "Current Assignments", "CurrentAssignment page is not opened");

        } catch (Exception e) {
            Assert.fail("Exception in test case newAssignmentPopupFunctionality of class CreationAndAssignmentOfTheFileBasedAssignment", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void createCustomAssignmentButtonFunctionality() {

        try {
            //tc row no 21
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("21");//login as instructor
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
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            currentAssignments.newAssignment_Button.click();//click on new assignment;
            if (currentAssignments.close_Icon.isDisplayed() == false) {
                Assert.fail("Create New Assignment pop-up window is not opened with three different types of Assignments");
            }
            Assert.assertEquals(currentAssignments.createCustomAssignmentButton.getText(), "Create Custom Assignment", "Create Custom Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.usePreCreatedAssignmentButton.getText(), "Use Pre-created Assignment", "Use Pre-created Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.createFileBasedAssignmentButton.getText(), "Create File based Assignment", "Create File based Assignment button is not displaying");
            currentAssignments.close_Icon.click();//click on 'x'
            boolean verifyPopUp = new BooleanValue().presenceOfElement(21, By.cssSelector("div[class='ls-inst-dashboard-assignment-popup-button ls--create-custom-assignment-view']"));
            Assert.assertEquals(verifyPopUp, false, "The pop-up window is not closed");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            dashboard.getNewAssignmentButton().click();//click on new assignment;
            if (currentAssignments.close_Icon.isDisplayed() == false) {
                Assert.fail("Create New Assignment pop-up window is not opened with three different types of Assignments");
            }
            Assert.assertEquals(currentAssignments.createCustomAssignmentButton.getText(), "Create Custom Assignment", "Create Custom Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.usePreCreatedAssignmentButton.getText(), "Use Pre-created Assignment", "Use Pre-created Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.createFileBasedAssignmentButton.getText(), "Create File based Assignment", "Create File based Assignment button is not displaying");
            currentAssignments.createCustomAssignmentButton.click();//click on custom assignment
            Assert.assertEquals(currentAssignments.tab_Title.get(2).getAttribute("title"), "New Assignment", "The user is not navigated to the New Custom Assignment Creation page");

        } catch (Exception e) {
            Assert.fail("Exception in test case createCustomAssignmentButtonFunctionality of class CreationAndAssignmentOfTheFileBasedAssignment", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void usePreCreatedAssignmentButtonFunctionality() {

        try {
            //tc row no 26
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("26");//login as instructor
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
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            currentAssignments.newAssignment_Button.click();//click on new assignment;
            if (currentAssignments.close_Icon.isDisplayed() == false) {
                Assert.fail("Create New Assignment pop-up window is not opened with three different types of Assignments");
            }
            Assert.assertEquals(currentAssignments.createCustomAssignmentButton.getText(), "Create Custom Assignment", "Create Custom Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.usePreCreatedAssignmentButton.getText(), "Use Pre-created Assignment", "Use Pre-created Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.createFileBasedAssignmentButton.getText(), "Create File based Assignment", "Create File based Assignment button is not displaying");
            currentAssignments.close_Icon.click();//click on 'x'
            boolean verifyPopUp = new BooleanValue().presenceOfElement(26, By.cssSelector("div[class='ls-inst-dashboard-assignment-popup-button ls--create-custom-assignment-view']"));
            Assert.assertEquals(verifyPopUp, false, "The pop-up window is not closed");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            dashboard.getNewAssignmentButton().click();//click on new assignment;
            dashboard.getNewAssignmentButton().click();//click on new assignment;
            if (currentAssignments.close_Icon.isDisplayed() == false) {
                Assert.fail("Create New Assignment pop-up window is not opened with three different types of Assignments");
            }
            Assert.assertEquals(currentAssignments.createCustomAssignmentButton.getText(), "Create Custom Assignment", "Create Custom Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.usePreCreatedAssignmentButton.getText(), "Use Pre-created Assignment", "Use Pre-created Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.createFileBasedAssignmentButton.getText(), "Create File based Assignment", "Create File based Assignment button is not displaying");
            currentAssignments.usePreCreatedAssignmentButton.click();//click on use pre create assignment
            Assert.assertEquals(currentAssignments.tab_Title.get(1).getAttribute("title"), "Question Banks", "The user is not navigated to the Question Banks page");

        } catch (Exception e) {
            Assert.fail("Exception in test case usePreCreatedAssignmentButtonFunctionality of class CreationAndAssignmentOfTheFileBasedAssignment", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void fileBasedAssignmentButtonFunctionality() {

        try {
            //tc row no 31
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("31");//login as instructor
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
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            currentAssignments.newAssignment_Button.click();//click on new assignment;
            if (currentAssignments.close_Icon.isDisplayed() == false) {
                Assert.fail("Create New Assignment pop-up window is not opened with three different types of Assignments");
            }
            Assert.assertEquals(currentAssignments.createCustomAssignmentButton.getText(), "Create Custom Assignment", "Create Custom Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.usePreCreatedAssignmentButton.getText(), "Use Pre-created Assignment", "Use Pre-created Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.createFileBasedAssignmentButton.getText(), "Create File based Assignment", "Create File based Assignment button is not displaying");
            currentAssignments.close_Icon.click();//click on 'x'
            boolean verifyPopUp = new BooleanValue().presenceOfElement(31, By.cssSelector("div[class='ls-inst-dashboard-assignment-popup-button ls--create-custom-assignment-view']"));
            Assert.assertEquals(verifyPopUp, false, "The pop-up window is not closed");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            dashboard.getNewAssignmentButton().click();//click on new assignment;
            if (currentAssignments.close_Icon.isDisplayed() == false) {
                Assert.fail("Create New Assignment pop-up window is not opened with three different types of Assignments");
            }
            Assert.assertEquals(currentAssignments.createCustomAssignmentButton.getText(), "Create Custom Assignment", "Create Custom Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.usePreCreatedAssignmentButton.getText(), "Use Pre-created Assignment", "Use Pre-created Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.createFileBasedAssignmentButton.getText(), "Create File based Assignment", "Create File based Assignment button is not displaying");
            currentAssignments.createFileBasedAssignmentButton.click();//click on file based assignment
            Assert.assertEquals(currentAssignments.tab_Title.get(2).getAttribute("title"), "New Assignment", "The user is not navigated to the new File Based Assignment tab page");

        } catch (Exception e) {
            Assert.fail("Exception in test case fileBasedAssignmentButtonFunctionality of class CreationAndAssignmentOfTheFileBasedAssignment", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void fileBasedAssignmentPageFunctionality() {

        try {
            //tc row no 36
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            new LoginUsingLTI().ltiLogin("36");//login as instructor
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
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            currentAssignments.newAssignment_Button.click();//click on new assignment;
            if (currentAssignments.close_Icon.isDisplayed() == false) {
                Assert.fail("Create New Assignment pop-up window is not opened with three different types of Assignments");
            }
            Assert.assertEquals(currentAssignments.createCustomAssignmentButton.getText(), "Create Custom Assignment", "Create Custom Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.usePreCreatedAssignmentButton.getText(), "Use Pre-created Assignment", "Use Pre-created Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.createFileBasedAssignmentButton.getText(), "Create File based Assignment", "Create File based Assignment button is not displaying");
            currentAssignments.close_Icon.click();//click on 'x'
            boolean verifyPopUp = new BooleanValue().presenceOfElement(36, By.cssSelector("div[class='ls-inst-dashboard-assignment-popup-button ls--create-custom-assignment-view']"));
            Assert.assertEquals(verifyPopUp, false, "The pop-up window is not closed");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            dashboard.getNewAssignmentButton().click();//click on new assignment;
            if (currentAssignments.close_Icon.isDisplayed() == false) {
                Assert.fail("Create New Assignment pop-up window is not opened with three different types of Assignments");
            }
            Assert.assertEquals(currentAssignments.createCustomAssignmentButton.getText(), "Create Custom Assignment", "Create Custom Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.usePreCreatedAssignmentButton.getText(), "Use Pre-created Assignment", "Use Pre-created Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.createFileBasedAssignmentButton.getText(), "Create File based Assignment", "Create File based Assignment button is not displaying");
            currentAssignments.createFileBasedAssignmentButton.click();//click on file based assignment
            Assert.assertEquals(currentAssignments.tab_Title.get(2).getAttribute("title"), "New Assignment", "The user is not navigated to the new File Based Assignment tab page");
            Assert.assertEquals(newAssignment.addQuestionTextBox.getText(), "Click to add question prompt for students...", "Click to add question prompt for students is not displaying");
            Assert.assertEquals(newAssignment.assessmentNameTextBox.getText(), "Click to enter assignment name...", "Click to enter assignment name is not displaying");
            new MouseHover().mouserhoverbywebelement(newAssignment.assessmentNameTextBox);
            if (newAssignment.pencil_Icon.isDisplayed() == false) {
                Assert.fail("A pencil icon is not displaying");
            }
            newAssignment.assessmentNameTextBox.click();//click on the text box
            Thread.sleep(2000);
            newAssignment.assessmentName_TextBox.sendKeys("Assessment");
            Thread.sleep(2000);
            driver.findElement(By.id("question-prompt-raw-content")).click();
            Assert.assertEquals(newAssignment.assessmentNameTextBox.getText(), "Assessment", "The tab is not same with the entered name by the user");
            driver.findElement(By.id("question-prompt-raw-content")).sendKeys("Description");
            newAssignment.linkIconTextEditor.click();//click on link icon on text editor
            newAssignment.insertLink.click();//click on insert link
            newAssignment.popUpUrl.sendKeys("https://www.google.co.in/");
            newAssignment.textBox.sendKeys("Google");
            newAssignment.checkBox.click();//click on check box
            newAssignment.insertButton.click();//click on insert
            Thread.sleep(2000);
            driver.findElement(By.xpath("/html/body")).click();
            newAssignment.google.click();//click on google
            Thread.sleep(2000);
            newAssignment.googleLink.click();//click on google link
            String main = driver.getWindowHandle();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            String currentUrl = driver.getCurrentUrl();
            System.out.println("Current url:" + currentUrl);
            if (!currentUrl.contains("https://www.google.co.in/"))
                Assert.fail("Instructor is not navigated to the link google.com");
            driver.switchTo().window(main); //switch back to the main window
            Assert.assertEquals(newAssignment.uploadFileButton.getText(), "Upload file(s)", "upload file button is not available");


        } catch (Exception e) {
            Assert.fail("Exception in test case fileBasedAssignmentPageFunctionality of class CreationAndAssignmentOfTheFileBasedAssignment", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void uploadFileButtonFunctionalityOnFileBasedAssignmentPage() {

        try {
            //tc row no 51
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            new LoginUsingLTI().ltiLogin("51");//login as instructor
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
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            currentAssignments.newAssignment_Button.click();//click on new assignment;
            if (currentAssignments.close_Icon.isDisplayed() == false) {
                Assert.fail("Create New Assignment pop-up window is not opened with three different types of Assignments");
            }
            Assert.assertEquals(currentAssignments.createCustomAssignmentButton.getText(), "Create Custom Assignment", "Create Custom Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.usePreCreatedAssignmentButton.getText(), "Use Pre-created Assignment", "Use Pre-created Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.createFileBasedAssignmentButton.getText(), "Create File based Assignment", "Create File based Assignment button is not displaying");
            currentAssignments.createFileBasedAssignmentButton.click();//click on file based assignment
            newAssignment.assessmentNameTextBox.click();//click on the text box
            Thread.sleep(2000);
            newAssignment.assessmentName_TextBox.sendKeys("Assessment");
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.tab_Title.get(2).getAttribute("title"), "New Assignment", "The user is not navigated to the new File Based Assignment tab page");
            fileUploadNotificationMessageValidate("51", "Your file upload request is being processed...");
            Thread.sleep(5000);
            fileUploadNotificationMessageValidate("51", "Your file upload request is being processed...");
            Thread.sleep(5000);
            if (newAssignment.fieUpload.get(0).isDisplayed() == false)
                Assert.fail("The newly added file is not come above the previously uploaded file");
            if (newAssignment.imageIcon.get(0).isDisplayed() == false)
                Assert.fail("The icons and the file names is not proper with its extensions.");


        } catch (Exception e) {
            Assert.fail("Exception in test case uploadFileButtonFunctionalityOnFileBasedAssignmentPage of class CreationAndAssignmentOfTheFileBasedAssignment", e);
        }
    }

    public  boolean fileUploadNotificationMessageValidate(String dataIndex, String notificationMessage) {
        boolean uploaded = true;
        try {
            String filename = ReadTestData.readDataByTagName("", "filename", dataIndex);
            driver.findElement(By.id("uploadFile")).click();
            Thread.sleep(3000);
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(2000);
            int errmsg = driver.findElements(By.className("notification-message-body")).size();
            if (errmsg > 0) {
                String notificationtext = driver.findElement(By.className("notification-message-body")).getText();
                System.out.println("notificationtext "+notificationtext);
                if (!notificationtext.equals(notificationMessage))
                    uploaded = false;
            } else {
                Assert.fail("Notification Message during file upload did not appear.");
            }

            driver.findElement(By.id("ls-ins-save-assigment-btn")).click(); //click on save for later
            Thread.sleep(500);
            if (errmsg > 0) {
                String notificationtext1 = driver.findElement(By.className("notification-message-body")).getText();
                System.out.println("notificationtext1 " + notificationtext1);
                if (!notificationtext1.trim().equals("File upload in progress. Please try after some time."))
                    Assert.fail("File upload in progress. Please try after some time message is not displaying");

            }
            else {
                Assert.fail("Notification Message during file upload did not appear.");
            }

        } catch (Exception e) {
            Assert.fail("Exception in app helper fileUploadValidate in class FileUpload", e);
        }
        return uploaded;
    }

    @Test(priority = 7, enabled = true)
    public void deleteFunctionalityAfterFileUploadsOnFileBasedAssignment() {

        try {
            //tc row no 66
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            new LoginUsingLTI().ltiLogin("66");//login as instructor
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
            currentAssignments.newAssignment_Button.click();//click on new assignment;
            if (currentAssignments.close_Icon.isDisplayed() == false) {
                Assert.fail("Create New Assignment pop-up window is not opened with three different types of Assignments");
            }
            Assert.assertEquals(currentAssignments.createCustomAssignmentButton.getText(), "Create Custom Assignment", "Create Custom Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.usePreCreatedAssignmentButton.getText(), "Use Pre-created Assignment", "Use Pre-created Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.createFileBasedAssignmentButton.getText(), "Create File based Assignment", "Create File based Assignment button is not displaying");
            currentAssignments.createFileBasedAssignmentButton.click();//click on file based assignment
            newAssignment.assessmentNameTextBox.click();//click on the text box
            Thread.sleep(2000);
            newAssignment.assessmentName_TextBox.sendKeys("Assessment");
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.tab_Title.get(2).getAttribute("title"), "New Assignment", "The user is not navigated to the new File Based Assignment tab page");
            fileUploadNotificationMessageValidate("66", "Your file upload request is being processed...");
            Thread.sleep(5000);
            if (newAssignment.fieUpload.get(0).isDisplayed() == false)
                Assert.fail("The uploaded file is not displaying  above the Upload File(s) button.");

            newAssignment.deleteIcon.click();//click on delete icon
            Assert.assertEquals(newAssignment.deleteNotificationMessage.getText(), "You are about to delete this file. Are you sure?   Yes      No", "You are about to delete this file. Are you sure? message is not displaying");
            newAssignment.yesOnDeleteMessage.click();//click on yes
            boolean file = new BooleanValue().presenceOfElement(66, By.className("ls-uploaded-file"));
            Assert.assertEquals(file, false, "The file is not deleted and it is displaying on the page");
            fileUploadNotificationMessageValidate("66", "Your file upload request is being processed...");
            Thread.sleep(5000);
            if (newAssignment.fieUpload.get(0).isDisplayed() == false)
                Assert.fail("The uploaded file is not displaying  above the Upload File(s) button.");

            newAssignment.deleteIcon.click();//click on delete icon
            Assert.assertEquals(newAssignment.deleteNotificationMessage.getText(), "You are about to delete this file. Are you sure?   Yes      No", "You are about to delete this file. Are you sure? message is not displaying");
            newAssignment.noOnDeleteMessage.click();//click on no
            if (newAssignment.fieUpload.get(0).isDisplayed() == false)
                Assert.fail("The file is deleted and should not be displayed on the ");

            newAssignment.deleteIcon.click();//click on delete icon
            Assert.assertEquals(newAssignment.deleteNotificationMessage.getText(), "You are about to delete this file. Are you sure?   Yes      No", "You are about to delete this file. Are you sure? message is not displaying");
            newAssignment.closeIcon.click();//click on 'x'
            Thread.sleep(2000);
            if (newAssignment.fieUpload.get(0).isDisplayed() == false)
                Assert.fail("The file is deleted and should not be displayed");


        } catch (Exception e) {
            Assert.fail("Exception in test case deleteFunctionalityAfterFileUploadsOnFileBasedAssignment of class CreationAndAssignmentOfTheFileBasedAssignment", e);
        }
    }

    @Test(priority = 8, enabled = true)
    public void deleteWarningMessagePopUpVerificationOnFileBasedAssignment() {

        try {
            //tc row no 83
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            new LoginUsingLTI().ltiLogin("83");//login as instructor
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
            currentAssignments.newAssignment_Button.click();//click on new assignment;
            if (currentAssignments.close_Icon.isDisplayed() == false) {
                Assert.fail("Create New Assignment pop-up window is not opened with three different types of Assignments");
            }
            Assert.assertEquals(currentAssignments.createCustomAssignmentButton.getText(), "Create Custom Assignment", "Create Custom Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.usePreCreatedAssignmentButton.getText(), "Use Pre-created Assignment", "Use Pre-created Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.createFileBasedAssignmentButton.getText(), "Create File based Assignment", "Create File based Assignment button is not displaying");
            currentAssignments.createFileBasedAssignmentButton.click();//click on file based assignment
            newAssignment.assessmentNameTextBox.click();//click on the text box
            Thread.sleep(2000);
            newAssignment.assessmentName_TextBox.sendKeys("Assessment");
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.tab_Title.get(2).getAttribute("title"), "New Assignment", "The user is not navigated to the new File Based Assignment tab page");
            fileUploadNotificationMessageValidate("83", "Your file upload request is being processed...");
            Thread.sleep(5000);
            if (newAssignment.fieUpload.get(0).isDisplayed() == false)
                Assert.fail("The uploaded file is not displaying  above the Upload File(s) button.");

            newAssignment.deleteIcon.click();//click on delete icon
            Assert.assertEquals(newAssignment.deleteNotificationMessage.getText(), "You are about to delete this file. Are you sure?   Yes      No", "You are about to delete this file. Are you sure? message is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in test case deleteWarningMessagePopUpVerificationOnFileBasedAssignment of class CreationAndAssignmentOfTheFileBasedAssignment", e);
        }
    }

    @Test(priority = 9, enabled = true)
    public void saveForLaterButtonFunctionalityOnFileBasedAssignment() {

        try {
            //tc row no 93
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            new LoginUsingLTI().ltiLogin("93");//login as instructor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            dashboard.getMainNavigator().click();//click on left corner main navigator
            dashboard.getAssignments().click();//click on '>' icon adjacent to assignment
            dashboard.getNewAssignments().click();//click on new assignment
            dashboard.getCrossIcon().click();//click on cross icon*/
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            new Navigator().NavigateTo("Policies");//click on policies
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            currentAssignments.newAssignment_Button.click();//click on new assignment;
            if (currentAssignments.close_Icon.isDisplayed() == false) {
                Assert.fail("Create New Assignment pop-up window is not opened with three different types of Assignments");
            }
            Assert.assertEquals(currentAssignments.createCustomAssignmentButton.getText(), "Create Custom Assignment", "Create Custom Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.usePreCreatedAssignmentButton.getText(), "Use Pre-created Assignment", "Use Pre-created Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.createFileBasedAssignmentButton.getText(), "Create File based Assignment", "Create File based Assignment button is not displaying");
            currentAssignments.createFileBasedAssignmentButton.click();//click on file based assignment
            newAssignment.saveForLater_Button.click();//click on save for later
            String colour = newAssignment.assessmentNameTextBox.getAttribute("style");//click on the text box
            if (colour.contains("background-color: rgb(255, 255, 255)"))
                Assert.fail("The Assignment Name text box is not highlighted with a red border");
            newAssignment.assessmentNameTextBox.click();//click on the text box
            Thread.sleep(2000);
            newAssignment.assessmentName_TextBox.sendKeys("Assessment");
            Thread.sleep(2000);
            fileUploadNotificationMessageValidate("93", "Your file upload request is being processed...");
            Thread.sleep(10000);
            newAssignment.saveForLater_Button.click();//click on save for later
            Assert.assertEquals(newAssignment.notification_message.getText(), "Saved file type assignment successfully.", " Saved file type assignment successfully is not displaying at the top");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            Assert.assertEquals(myQuestionBank.getMyQuestionBankTitle().getText(), "My Question Bank", "My Question bank tab is not opened");
            Assert.assertEquals(myQuestionBank.getAssessment().getText(), "Assessment", "The newly saved assessment is not displaying at the top on My Question Bank page");
            myQuestionBank.getPreviewButton().click();//click on preview

            Assert.assertEquals(currentAssignments.tab_Title.get(2).getText(), "Assessment", "The Assignment preview page is not  opened as a new tab next to Question Banks ");
            if (myQuestionBank.filename.isDisplayed() == false)
                Assert.fail("The uploaded files, Prompt is not displaying with all the entered details");
            myQuestionBank.closeTabIcon.get(2).click();//click on 'x'
            Assert.assertEquals(myQuestionBank.getMyQuestionBankTitle().getText(), "My Question Bank", "My Question bank tab is not opened");
            Thread.sleep(3000);
            boolean customize = myQuestionBank.customizeThis.isDisplayed();
            if (myQuestionBank.customizeThis.isDisplayed() == true)
                Assert.fail("Customize this option is available for File Based Assessment");
            myQuestionBank.getAssignThis().click();//click on assign this
            if (myQuestionBank.popUP.isDisplayed() == false) {
                Assert.fail("Assign popup is not opened");
            }

            myQuestionBank.cancelPopUp.click();//click on cancel
            boolean popup = new BooleanValue().presenceOfElement(93, By.cssSelector("span[class='btn sty-green submit-assign']"));
            Assert.assertEquals(popup, false, "Assign pop up is opened");
            myQuestionBank.getDeleteButtonOfOriginal().click();//click on delete
            Assert.assertEquals(myQuestionBank.notification.getText().trim(), "Are you sure you want to delete this assignment?   Yes      No", "Are you sure you want to delete this assignment is not displaying");
            myQuestionBank.noLink.click();//click on no
            boolean notification = new BooleanValue().presenceOfElement(93, By.className("notification-text-span"));
            Assert.assertEquals(notification, false, "Notification message is displaying");
            Assert.assertEquals(myQuestionBank.getAssessment().getText(), "Assessment", "Assignment is deleted");
            myQuestionBank.getDeleteButtonOfOriginal().click();//click on delete
            Assert.assertEquals(myQuestionBank.notification.getText().trim(), "Are you sure you want to delete this assignment?   Yes      No", "Are you sure you want to delete this assignment is not displaying");
            myQuestionBank.yesLink.click();//click on yes
            Thread.sleep(3000);
            boolean notification1 = new BooleanValue().presenceOfElement(93, By.className("notification-text-span"));
            Assert.assertEquals(notification1, false, "Notification message is displaying");


        } catch (Exception e) {
            Assert.fail("Exception in test case saveForLaterButtonFunctionalityOnFileBasedAssignment of class CreationAndAssignmentOfTheFileBasedAssignment", e);
        }
    }

    @Test(priority = 10, enabled = true)
    public void assignNowButtonFunctionalityOnFileBasedAssignmentPage() {

        try {
            //tc row no 118
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(118));
            new LoginUsingLTI().ltiLogin("118");//login as instructor
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
            currentAssignments.newAssignment_Button.click();//click on new assignment;
            if (currentAssignments.close_Icon.isDisplayed() == false) {
                Assert.fail("Create New Assignment pop-up window is not opened with three different types of Assignments");
            }
            Assert.assertEquals(currentAssignments.createCustomAssignmentButton.getText(), "Create Custom Assignment", "Create Custom Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.usePreCreatedAssignmentButton.getText(), "Use Pre-created Assignment", "Use Pre-created Assignment button is not displaying");
            Assert.assertEquals(currentAssignments.createFileBasedAssignmentButton.getText(), "Create File based Assignment", "Create File based Assignment button is not displaying");
            currentAssignments.createFileBasedAssignmentButton.click();//click on file based assignment
            newAssignment.assignNowButton.click();//click on assign now
            String colour = newAssignment.assessmentNameTextBox.getAttribute("style");//click on the text box
            if (colour.contains("background-color: rgb(255, 255, 255)"))
                Assert.fail("The Assignment Name text box is not highlighted with a red border");
            newAssignment.assessmentNameTextBox.click();//click on the text box
            Thread.sleep(2000);
            newAssignment.assessmentName_TextBox.sendKeys("Assessment");
            Thread.sleep(2000);
            fileUploadNotificationMessageValidate("118", "Your file upload request is being processed...");
            Thread.sleep(10000);
            newAssignment.assignNowButton.click();//click on assign now
            Assert.assertEquals(newAssignment.popUP.getText(), "Assessment", "Popup is not opened");
            for(int a=0;a<newAssignment.assignField.size();a++){
                System.out.println("newAssignment.assignField : " + newAssignment.assignField.get(a).getText());
            }
            Assert.assertEquals(newAssignment.assignField.get(0).getText(), "Assign To: *", "Assign To field is not available");
            Assert.assertEquals(newAssignment.assignField.get(2).getText(), "Assignment Reference:", "Grading Policy field is not available");
            Assert.assertEquals(newAssignment.assignField.get(3).getText(), "Assignment Reference Description:", "Grading Policy Description field is not available");
            Assert.assertEquals(newAssignment.assignField.get(4).getText(), "Accessible After: *", "Accessible After field is not available");
            Assert.assertEquals(newAssignment.assignField.get(5).getText(), "Due Date: *", "Due Date Description field is not available");
            Assert.assertEquals(newAssignment.assignField.get(6).getText(), "Description:", "Description field is not available");
            Assert.assertEquals(newAssignment.assignPopUp.getText(), "Assign", "Assign field is not available");
            Assert.assertEquals(newAssignment.cancelPopUp.getText(), "Cancel", "Cancel field is not available");
            newAssignment.gradableCheckBox.click();//click on gradable check box
            Assert.assertEquals(newAssignment.assignField.get(2).getText(), "Total Points: *", "Total points field is not available");
            newAssignment.helpIconOFPoints.click();//click on help of total points
            Assert.assertEquals(newAssignment.helpMessage.getText(), "Enter the points available for the file type assignment.", "It is not displaying Enter the points available for the file type assignment.");
            driver.findElement(By.id("total-points")).sendKeys("2");
            newAssignment.assignNowButton.click();//click on assign now
            driver.findElement(By.id("due-date")).click();//click on due date
            driver.findElement(By.cssSelector("a[title='Next']")).click();
            driver.findElement(By.linkText(duedate)).click();
            driver.findElement(By.id("due-time")).click();//click on due time
            driver.findElement(By.xpath("(//ul[@class='ui-timepicker-list']/li)[5]")).click();
            newAssignment.assignPopUp.click();//click on assign
            Thread.sleep(4000);
            Assert.assertEquals(currentAssignments.tab_Title.get(0).getText(), "Current Assignments", "The user is not redirected to Current Assignments page");
            new Navigator().NavigateTo("Course Stream");//navigate to course stream
            Assert.assertEquals(courseStreamPage.assessmentName.getText(), "(shor) Assessment", "The File Based Assignment is not displaying at the top with its mandatory details");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            dashboard.getMainNavigator().click();//click on left corner main navigator
            dashboard.getAssignments().click();//click on '>' icon adjacent to assignment
            dashboard.getNewAssignments().click();//click on new assignment
            dashboard.getCrossIcon().click();//click on cross icon
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            new Navigator().NavigateTo("Policies");//click on policies
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            Assert.assertEquals(currentAssignments.getAssignmentName().getText(), "(shor) Assessment", "The File Based Assignment is not displaying at the top with its mandatory details");
            Assert.assertEquals(currentAssignments.resourceLink.getText(), "img.png", "image link is not available");
            Thread.sleep(3000);
            currentAssignments.resourceLink.click();//click on resource


        } catch (Exception e) {
            Assert.fail("Exception in test case assignNowButtonFunctionalityOnFileBasedAssignmentPage of class CreationAndAssignmentOfTheFileBasedAssignment", e);
        }
    }

    @Test(priority = 11, enabled = true)
    public void viewFunctionalityOfFileBasedAssignment () {

        try {
            //tc row no 145
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            new LoginUsingLTI().ltiLogin("118");//login as instructor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("e-Textbook");//navigate to e-textbook
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            String title1 = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title1, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            Assert.assertEquals(dashboard.assessment.getText(),"(shor) Assessment","File Based Assessment is not displaying under Class Activity section in a card form");
            if(dashboard.assignmentIcon.isDisplayed()==false){
                Assert.fail("File Based Assessment is not  having a Folder Icon with a Pencil icon before the Assessment Name ");
            }
            new Navigator().NavigateTo("Course Stream");//navigate to course stream
            Assert.assertEquals(courseStreamPage.assessmentName.getText(), "(shor) Assessment", "The File Based Assignment is not displaying at the top with its mandatory details");

            if(courseStreamPage.assignmentIcon.isDisplayed()==false){
                Assert.fail("File Based Assessment is not  having a Folder Icon with a Pencil icon before the Assessment Name ");
            }

            Assert.assertEquals(courseStreamPage.resourceLink.getText(), "img.png", "image link is not available");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            dashboard.getMainNavigator().click();//click on left corner main navigator
            dashboard.getAssignments().click();//click on '>' icon adjacent to assignment
            dashboard.getNewAssignments().click();//click on new assignment
            dashboard.getCrossIcon().click();//click on cross icon
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            new Navigator().NavigateTo("Policies");//click on policies
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            Assert.assertEquals(currentAssignments.getAssignmentName().getText(), "(shor) Assessment", "The File Based Assignment is not displaying at the top with its mandatory details");
            Assert.assertEquals(currentAssignments.resourceLink.getText(), "img.png", "image link is not available");
            if(currentAssignments.imageIcon.isDisplayed()==false){
                Assert.fail("File Based Assessment is not  having a Folder Icon with a Pencil icon before the Assessment Name ");
            }


        } catch (Exception e) {
            Assert.fail("Exception in test case viewFunctionalityOfFileBasedAssignment of class CreationAndAssignmentOfTheFileBasedAssignment", e);
        }
    }


}



