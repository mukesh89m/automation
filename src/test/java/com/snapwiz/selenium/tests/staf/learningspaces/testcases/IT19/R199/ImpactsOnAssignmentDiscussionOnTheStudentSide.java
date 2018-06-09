package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R199;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Gradebook.Gradebook;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.AssignmentTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by priyanka on 3/12/2015.
 */
public class ImpactsOnAssignmentDiscussionOnTheStudentSide extends Driver {
    @Test(priority = 1, enabled = true)
    public void addingDiscussionToQuestionsForFileBasedAssignment() {

        try {
            //tc row no 352
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            new Assignment().createFileBasedAssessment(352);
            new LoginUsingLTI().ltiLogin("352_1");//login as student
            new LoginUsingLTI().ltiLogin("352");//login as instructor
            new Assignment().assignFileBasedAssignmentToStudent(352);
            new LoginUsingLTI().ltiLogin("352_1");//login as student
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssessmentName().click();//click on assignment
            Thread.sleep(10000);
            assignmentTab.finishButton.click();//click on finish assignment
            assignmentTab.continueButton.click();//click on continue
            new LoginUsingLTI().ltiLogin("352");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            assignmentResponsesPage.getScore().sendKeys("1");
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("This is a feedback");
            Thread.sleep(1000);
            assignmentResponsesPage.getSaveButton().click();//click on save
            Thread.sleep(1000);
            currentAssignments.tab_Title.get(1).click();//click on assignment response page
            new Assignment().releaseGrades(352, "Release Grade for All");
            new LoginUsingLTI().ltiLogin("352_1");//login as student
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssessmentName().click();//click on assignment
            Thread.sleep(10000);
            new Navigator().navigateToTab("Assignments");
            List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'Discussion')]"));
            Assert.assertTrue(list.size() > 0, "Discussion tab is present");


        } catch (Exception e) {
            Assert.fail("Exception in test case addingDiscussionToQuestionsForFileBasedAssignment of class ImpactsOnAssignmentDiscussionOnTheStudentSide", e);
        }
    }


    @Test(priority = 2, enabled = true)
    public void dashboardAndAssignmentPageContributionThroughFileBasedAssignment() {

        try {
            //tc row no 357
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(357));

            new Assignment().createFileBasedAssessment(357);
            new LoginUsingLTI().ltiLogin("357_1");//login as student
            new LoginUsingLTI().ltiLogin("357");//login as instructor
            new Assignment().assignFileBasedAssignmentToStudent(357);
            new LoginUsingLTI().ltiLogin("357_1");//login as student
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssessmentName().click();//click on assignment
            Thread.sleep(10000);
            assignmentTab.finishButton.click();//click on finish assignment
            assignmentTab.continueButton.click();//click on continue


            new LoginUsingLTI().ltiLogin("357");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            }
            Thread.sleep(5000);
            assignmentResponsesPage.getScore().sendKeys("1");
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("This is a feedback");
            Thread.sleep(1000);
            assignmentResponsesPage.getSaveButton().click();//click on save
            Thread.sleep(1000);
            currentAssignments.tab_Title.get(1).click();//click on assignment response page
            new Assignment().releaseGrades(357, "Release Grade for All");
            new LoginUsingLTI().ltiLogin("357_1");//login as student
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            if (dashboard.overallScore.isDisplayed() == false)
                Assert.fail("The over all score percentage is not generated");

            List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'FileBasedAssessment')]"));
            Assert.assertTrue(list.size() > 0, "File based assessment name is displaying");
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            Assert.assertEquals(assignments.getAssignmentName().getText().trim(), "(shor) " + assignmentname + "", "The Assignment name is not listed on the Assignment page");
            Assert.assertEquals(assignments.getScore().getText(), "Score (1/2)", "Score is not displaying below due date of that assignment");
            new Navigator().NavigateTo("Dashboard");//click on dashboard

            String overAll = driver.findElement(By.cssSelector("tspan[x='100']")).getText().trim();
            if (overAll.equals("") || overAll == null)
                Assert.fail("The Overall Score percentage is not increase from the previously recorded percentage");
            dashboard.getGradedBarChart().click();//click on bar under graded assignment section

            List<WebElement> list1 = driver.findElements(By.xpath("//*[contains(text(),'FileBasedAssessment')]"));
            Assert.assertTrue(list1.size() > 0, "File based assessment name is displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case dashboardAndAssignmentPageContributionThroughFileBasedAssignment of class ImpactsOnAssignmentDiscussionOnTheStudentSide", e);
        }
    }


    @Test(priority = 3, enabled = true)
    public void dashboardAndReportsImpactOfSubmittedFileBasedAssignment() {

        try {
            //tc row no 370
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            ProficiencyReport proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);
            MetacognitiveReport metacognitiveReport = PageFactory.initElements(driver, MetacognitiveReport.class);
            ProductivityReport productivityReport = PageFactory.initElements(driver, ProductivityReport.class);
            MostChallengingReport mostChallengingReport = PageFactory.initElements(driver, MostChallengingReport.class);
            new Assignment().createFileBasedAssessment(370);
            new LoginUsingLTI().ltiLogin("370_1");//login as student
            new LoginUsingLTI().ltiLogin("370");//login as instructor
            new Assignment().assignFileBasedAssignmentToStudent(370);
            new LoginUsingLTI().ltiLogin("370_1");//login as student
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssessmentName().click();//click on assignment
            Thread.sleep(10000);
            assignmentTab.finishButton.click();//click on finish assignment
            assignmentTab.continueButton.click();//click on continue
            new LoginUsingLTI().ltiLogin("370");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            assignmentResponsesPage.getScore().sendKeys("1");
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("This is a feedback");
            Thread.sleep(1000);
            assignmentResponsesPage.getSaveButton().click();//click on save
            Thread.sleep(1000);
            currentAssignments.tab_Title.get(1).click();//click on assignment response page
            new Assignment().releaseGrades(370, "Release Grade for All");

            new LoginUsingLTI().ltiLogin("370");//login as instructor
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            Assert.assertEquals(dashboard.timeSpent.getText(), "0", "The time spent section is changed");
            Assert.assertEquals(dashboard.getAvgQuestionPerformance().get(0).getText().substring(2).trim(), "Contributions per student", "Participation rating is changed");
            Assert.assertEquals(dashboard.getAvgQuestionPerformance().get(1).getText(), "0", "Question Performance rating is changed");
            Assert.assertEquals(dashboard.getAvgQuestionPerformance().get(2).getText(), "0%", "Question Attempted is changed");
            new Navigator().NavigateTo("Proficiency Report");
            Assert.assertEquals(proficiencyReport.getProficiencyReportTitle().getText(), "Class Proficiency by Chapters", "Proficiency report page is not opened");
            if (proficiencyReport.notification_Block.isDisplayed() == false)
                Assert.fail("Proficiency report page is changed");

            new Navigator().NavigateTo("Metacognitive Report");
            Assert.assertEquals(metacognitiveReport.getMetacognitiveReportPage().getText(), "Metacognitive Report", "Metacognitive Report page is not opened");
            if (metacognitiveReport.notification_Block.isDisplayed() == false)
                Assert.fail("Metacognitive report page is changed");

            new Navigator().NavigateTo("Productivity Report");
            Assert.assertEquals(productivityReport.getProductivityReportTitle().getText(), "Productivity Report", "Productivity Report page is not opened");
            if (productivityReport.notification_Block.isDisplayed() == false)
                Assert.fail("Productivity report page is changed");

            new Navigator().NavigateTo("Most Challenging Activities Report");
            Assert.assertEquals(mostChallengingReport.getMostChallengingReportTitle().getText(), "Most Challenging Activities", "Productivity Report page is not opened");
            if (mostChallengingReport.notification_Block.isDisplayed() == false)
                Assert.fail("Most Challenging Activities Report page is changed");


        } catch (Exception e) {
            Assert.fail("Exception in test case dashboardAndReportsImpactOfSubmittedFileBasedAssignment of class ImpactsOnAssignmentDiscussionOnTheStudentSide", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void dashboardAssignmentContributionThroughFileBasedAssignment() {

        try {
            //tc row no 391
            CurrentAssignments currentAssignments;
            currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentTab assignmentTab;
            assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            AssignmentResponsesPage assignmentResponsesPage;
            assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Dashboard dashboard;
            dashboard = PageFactory.initElements(driver, Dashboard.class);
            EngagementReport engagementReport;
            engagementReport = PageFactory.initElements(driver, EngagementReport.class);
            Gradebook gradebook;
            gradebook = PageFactory.initElements(driver, Gradebook.class);
            new Assignment().createFileBasedAssessment(391);
            new LoginUsingLTI().ltiLogin("391_1");//login as student
            new LoginUsingLTI().ltiLogin("391");//login as instructor
            new Assignment().assignFileBasedAssignmentToStudent(391);
            new LoginUsingLTI().ltiLogin("391_1");//login as student
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssessmentName().click();//click on assignment
            Thread.sleep(10000);
            assignmentTab.finishButton.click();//click on finish assignment
            assignmentTab.continueButton.click();//click on continue
            new LoginUsingLTI().ltiLogin("391");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            assignmentResponsesPage.getScore().sendKeys("1");
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("This is a feedback");
            Thread.sleep(1000);
            assignmentResponsesPage.getSaveButton().click();//click on save
            Thread.sleep(1000);
            currentAssignments.tab_Title.get(1).click();//click on assignment response page
            new Assignment().releaseGrades(391, "Release Grade for All");
            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("391");//login as instructor
            currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            dashboard = PageFactory.initElements(driver, Dashboard.class);
            engagementReport = PageFactory.initElements(driver, EngagementReport.class);
            gradebook = PageFactory.initElements(driver, Gradebook.class);
            new Navigator().NavigateTo("Dashboard");//click on dashboard

            String overAll = driver.findElement(By.cssSelector("tspan[x='100']")).getText().trim();
            if (overAll.equals("") || overAll == null)
                Assert.fail("The Overall Score percentage is not increase from the previously recorded percentage");
            dashboard.getGradedBarChart().click();//click on bar under graded assignment section

            new Navigator().NavigateTo("Engagement Report");//navigate to engagement report
            Assert.assertEquals(engagementReport.getEngagementReportTitle().getText(), "Student Engagement Report", "student name is not available");
            Assert.assertEquals(engagementReport.studentName.get(1).getText(), "family, givenname", "Engagement Report page is not opened");
            new Navigator().NavigateTo("Gradebook");//navigate to gradebook
            Assert.assertEquals(gradebook.getGradeBookHeader().getText(), "Gradebook", "Gradebook page is not opened");
            Assert.assertEquals(gradebook.getLeftContentCount().get(0).getText(), "family, givenname", "student name is not available");
            if (driver.findElement(By.className("ls-ins-gradebook-overall-score")).isDisplayed() == false)
                Assert.fail("overall score percentage is not available");

        } catch (Exception e) {
            Assert.fail("Exception in test case dashboardAssignmentContributionThroughFileBasedAssignment of class ImpactsOnAssignmentDiscussionOnTheStudentSide", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void endToEndFileBasedAssignmentOnInstructorAndStudentSide() {

        try {
            //tc row no 412
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(412));
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);

            new LoginUsingLTI().ltiLogin("412");//login as instructor
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
            Assert.assertEquals(currentAssignments.tab_Title.get(2).getAttribute("title"), "New Assignment", "The user is not navigated to the new File Based Assignment tab page");
            newAssignment.assessmentNameTextBox.click();//click on the text box
            Thread.sleep(2000);
            newAssignment.assessmentName_TextBox.sendKeys("Assessment");
            Thread.sleep(2000);
            driver.findElement(By.id("question-prompt-raw-content")).click();
            Assert.assertEquals(newAssignment.assessmentNameTextBox.getText(), "Assessment", "The tab is not same with the entered name by the user");
            Thread.sleep(2000);
            fileUploadNotificationMessageValidate("412", "Your file upload request is being processed...");
            new UIElement().waitAndFindElement(By.className("ls-uploaded-file"));
            if (newAssignment.fieUpload.get(0).isDisplayed() == false)
                Assert.fail("The uploaded file is not displaying  above the Upload File(s) button.");
            newAssignment.assignNowButton.click();//click on assign now

            Assert.assertEquals(newAssignment.popUP.getText(), "Assessment", "Popup is not opened");
            Assert.assertEquals(newAssignment.assignField.get(0).getText(), "Assign To: *", "Assign To field is not available");
            Assert.assertEquals(newAssignment.assignField.get(2).getText(), "Assignment Reference:", "Assignment Reference field is not available");
            Assert.assertEquals(newAssignment.assignField.get(3).getText(), "Assignment Reference Description:", "Assignment Reference Description field is not available");
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
            Thread.sleep(2000);
            newAssignment.assignNowButton.click();//click on assign now
            Thread.sleep(3000);
            driver.findElement(By.id("due-date")).click();//click on due date
            driver.findElement(By.cssSelector("a[title='Next']")).click();
            driver.findElement(By.linkText(duedate)).click();
            driver.findElement(By.id("due-time")).click();//click on due time
            driver.findElement(By.xpath("(//ul[@class='ui-timepicker-list']/li)[5]")).click();
            newAssignment.assignPopUp.click();//click on assign
            Thread.sleep(4000);
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            Assert.assertEquals(currentAssignments.getAssignmentName().getText().trim(), "(shor) Assessment", "The Assignment name is not listed on the Assignment page");
            Assert.assertEquals(currentAssignments.uploadedResources.getText(), "img.png", "Uploaded resources is not available");

            new LoginUsingLTI().ltiLogin("412_1");//login as student
            String title1 = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title1, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            Assert.assertEquals(assignments.getAssignmentName().getText().trim(), "(shor) Assessment", "The Assignment name is not listed on the Assignment page");
            assignments.getAssignmentName().click();//click  on assignment
            Thread.sleep(10000);
            String resourceName = assignmentTab.resourceName.getText().trim();
            System.out.println(resourceName);
            if (!resourceName.contains("shor: Assessment"))
                Assert.fail("The User is not navigated to the Assignments page with the Assignment details");
            fileUploadNotificationMessageValidateStudent("412", "Your file upload request is being processed...");
            new UIElement().waitAndFindElement(By.className("ls-uploaded-file"));

            if (newAssignment.fieUpload.get(0).isDisplayed() == false)
                Assert.fail("The newly added file is not come under your response section");
            fileUploadNotificationMessageValidateStudent("412", "Your file upload request is being processed...");
            new UIElement().waitAndFindElement(By.className("ls-uploaded-file"));
            if (newAssignment.fieUpload.get(1).isDisplayed() == false)
                Assert.fail("The uploaded file is not displaying  above the Upload File(s) button.");
            if (newAssignment.imageIcon.get(1).isDisplayed() == false)
                Assert.fail("The icons and the file names is not proper with its extensions.");
            Thread.sleep(9000);
            List<WebElement> FinishAssignmentElementsList = driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']"));
            System.out.println("FinishAssignmentElementsList size : " + FinishAssignmentElementsList.size());
            for(int a=0;a<FinishAssignmentElementsList.size();a++){
                if(FinishAssignmentElementsList.get(a).isDisplayed()){
                    FinishAssignmentElementsList.get(a).click();
                    System.out.println("A");
                }else{
                    System.out.println("B");
                }
            }            Thread.sleep(3000);
            Assert.assertEquals(assignmentTab.responseTag.getText(), "Your Response:", "The assignment is not submitted and the user not at same page");
            assignmentTab.continueButton.click();//click on continue

            if (currentAssignments.getAssessmentName().isDisplayed() == false)
                Assert.fail("The User is not navigated to the Assignment page");



            new LoginUsingLTI().ltiLogin("412");//login as instructor
            String title2 = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title2, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            Assert.assertEquals(currentAssignments.getCurrentAssignmentTitle().getText(), "Current Assignments", "Current Assignmnet page is not displaying to the user");
            Assert.assertEquals(currentAssignments.getAssignmentName().getText(), "(shor) Assessment", "File Based Assessment is not available ");
            currentAssignments.getViewGrade_link().click();//click on view response
            Thread.sleep(3000);
            Assert.assertEquals(currentAssignments.tab_Title.get(1).getText(), "Response - (shor) Assessment", "The Assignment Response page is not opened as a new tab next to current Assignment tab");
            if (assignmentResponsesPage.percentageColumn.isDisplayed() == false)
                Assert.fail("Percentage column is not available");
            Assert.assertEquals(assignmentResponsesPage.getViewResponse_link().getText(), "100%", "The % complete cloumn is not showing 100%");

            List<WebElement> list = driver.findElements(By.xpath("/*//*[contains(text(),'Score')]"));
            Assert.assertTrue(list.size() > 0, "Score field is present");

            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            Assert.assertEquals(currentAssignments.tab_Title.get(2).getText(), "Response - (shor) Assessment", "The user is not navigated to the Student response page in a new tab");
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("This is a feedback");
            Thread.sleep(1000);
            assignmentResponsesPage.getSaveButton().click();//click on save
            Thread.sleep(1000);
            Assert.assertEquals(assignmentResponsesPage.getSavedSuccessfullyMessage().getText(), "Saved successfully.", "The typed text is not  saved and Saved Successfully messageis not displayed below the textbox");
            currentAssignments.tab_Title.get(1).click();//click on assignment response page
            Thread.sleep(5000);
            driver.findElement(By.cssSelector("div[title='Release Feedback for All']")).click();
            Thread.sleep(3000);
            Assert.assertEquals(assignmentResponsesPage.feedback_Box.getText(), "Feedback Released", "Feedback is not released");
            Assert.assertEquals(assignmentResponsesPage.getReviewStatus().getText(), "Reviewed", "The Class Status on the page is not changed to Reviewed from Review in Progress");

            new LoginUsingLTI().ltiLogin("412_1");//login as student
            String title3 = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title3, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            Assert.assertEquals(dashboard.assessment.getText().trim(), "(shor) Assessment", "File Based Assessment is not displaying under Class Activity section in a card form");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssessmentName().click();//click on assignment
            Thread.sleep(10000);
            String resourceName1 = assignmentTab.resourceName.getText().trim();
            if (!resourceName1.contains("shor: Assessment"))
                Assert.fail("The User is not navigated to the Assignments page with the Assignment details");
            Assert.assertEquals(assignmentTab.teacherFeedBack.getText(), "This is a feedback", "Teacher feedback is not available");


        } catch (Exception e) {
            Assert.fail("Exception in test case endToEndFileBasedAssignmentOnInstructorAndStudentSide of class ImpactsOnAssignmentDiscussionOnTheStudentSide", e);
        }
    }
        @Test(priority = 6, enabled = true)
        public void endToEndFileBasedAssignmentOnInstructorAndStudentSideGradeAble () {

            try {
                //tc row no 464
                CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
                AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
                AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
                Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
                NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
                String duedate = ReadTestData.readDataByTagName("", "duedate", Integer.toString(464));
                Assignments assignments = PageFactory.initElements(driver, Assignments.class);

                new LoginUsingLTI().ltiLogin("464");//login as instructor
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
                Assert.assertEquals(currentAssignments.tab_Title.get(2).getAttribute("title"), "New Assignment", "The user is not navigated to the new File Based Assignment tab page");
                newAssignment.assessmentNameTextBox.click();//click on the text box
                Thread.sleep(2000);
                newAssignment.assessmentName_TextBox.sendKeys("Assessment");
                Thread.sleep(2000);
                driver.findElement(By.id("question-prompt-raw-content")).click();
                Assert.assertEquals(newAssignment.assessmentNameTextBox.getText(), "Assessment", "The tab is not same with the entered name by the user");
                Thread.sleep(2000);
                fileUploadNotificationMessageValidate("464", "Your file upload request is being processed...");
                new UIElement().waitAndFindElement(By.className("ls-uploaded-file"));
                if (newAssignment.fieUpload.get(0).isDisplayed() == false)
                    Assert.fail("The uploaded file is not displaying  above the Upload File(s) button.");
                newAssignment.assignNowButton.click();//click on assign now

                Assert.assertEquals(newAssignment.popUP.getText(), "Assessment", "Popup is not opened");
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
                Thread.sleep(2000);
                newAssignment.assignNowButton.click();//click on assign now
                Thread.sleep(3000);
                newAssignment.gradableCheckBox.click();//click on gradable check box
                driver.findElement(By.id("total-points")).sendKeys("2");
                driver.findElement(By.id("due-date")).click();//click on due date
                driver.findElement(By.cssSelector("a[title='Next']")).click();
                driver.findElement(By.linkText(duedate)).click();
                driver.findElement(By.id("due-time")).click();//click on due time
                driver.findElement(By.xpath("(//ul[@class='ui-timepicker-list']/li)[5]")).click();
                newAssignment.assignPopUp.click();//click on assign
                Thread.sleep(4000);
                new Navigator().NavigateTo("Current Assignments");//click on current assignment
                Assert.assertEquals(currentAssignments.getAssignmentName().getText().trim(), "(shor) Assessment", "The Assignment name is not listed on the Assignment page");
                Assert.assertEquals(currentAssignments.uploadedResources.getText(), "img.png", "Uploaded resources is not available");

                new LoginUsingLTI().ltiLogin("464_1");//login as student
                String title1 = dashboard.getTitle().getAttribute("title");
                Assert.assertEquals(title1, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
                new Navigator().NavigateTo("Assignments");//navigate to assignment
                Assert.assertEquals(assignments.getAssignmentName().getText().trim(), "(shor) Assessment", "The Assignment name is not listed on the Assignment page");
                assignments.getAssignmentName().click();//click  on assignment
                Thread.sleep(10000);
                String resourceName = assignmentTab.resourceName.getText().trim();
                System.out.println(resourceName);
                if (!resourceName.contains("shor: Assessment"))
                    Assert.fail("The User is not navigated to the Assignments page with the Assignment details");
                fileUploadNotificationMessageValidateStudent("464", "Your file upload request is being processed...");
                new UIElement().waitAndFindElement(By.className("ls-uploaded-file"));
                if (newAssignment.fieUpload.get(0).isDisplayed() == false)
                    Assert.fail("The newly added file is not come under your response section");
                Thread.sleep(5000);
                fileUploadNotificationMessageValidateStudent("464", "Your file upload request is being processed...");
                new UIElement().waitAndFindElement(By.className("ls-uploaded-file"));
                if (newAssignment.fieUpload.get(1).isDisplayed() == false)
                    Assert.fail("The uploaded file is not displaying  above the Upload File(s) button.");
                if (newAssignment.imageIcon.get(1).isDisplayed() == false)
                    Assert.fail("The icons and the file names is not proper with its extensions.");

                Thread.sleep(9000);
                List<WebElement> FinishAssignmentElementsList = driver.findElements(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']"));
                System.out.println("FinishAssignmentElementsList size : " + FinishAssignmentElementsList.size());
                for(int a=0;a<FinishAssignmentElementsList.size();a++){
                    if(FinishAssignmentElementsList.get(a).isDisplayed()){
                        FinishAssignmentElementsList.get(a).click();
                        System.out.println("A");
                    }else{
                        System.out.println("B");
                    }
                }


                Thread.sleep(3000);
                Assert.assertEquals(assignmentTab.responseTag.getText(), "Your Response:", "The assignment is not submitted and the user not at same page");
                assignmentTab.continueButton.click();//click on continue
                if (currentAssignments.getAssessmentName().isDisplayed() == false)
                    Assert.fail("The User is not navigated to the Assignment page");



                new LoginUsingLTI().ltiLogin("464");//login as instructor
                String title2 = dashboard.getTitle().getAttribute("title");
                Assert.assertEquals(title2, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
                new Navigator().NavigateTo("Dashboard");//click on dashboard
                new Navigator().NavigateTo("Current Assignments");//click on current assignment
               Assert.assertEquals(currentAssignments.getCurrentAssignmentTitle().getText(), "Current Assignments", "Current Assignmnet page is not displaying to the user");
                Assert.assertEquals(currentAssignments.getAssignmentName().getText(), "(shor) Assessment", "File Based Assessment is not available ");
                currentAssignments.getViewGrade_link().click();//click on view response
                Thread.sleep(3000);
                Assert.assertEquals(currentAssignments.tab_Title.get(1).getText(), "Response - (shor) Assessment", "The Assignment Response page is not opened as a new tab next to current Assignment tab");

                List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'Score')]"));
                Assert.assertTrue(list.size() > 0, "Score field is present");

                new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
                new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
                Thread.sleep(5000);
                Assert.assertEquals(currentAssignments.tab_Title.get(2).getText(), "Response - (shor) Assessment", "The user is not navigated to the Student response page in a new tab");
                assignmentResponsesPage.getScore().sendKeys("1");
                assignmentResponsesPage.getFeedbackTextArea().sendKeys("This is a feedback");
                Thread.sleep(1000);
                assignmentResponsesPage.getSaveButton().click();//click on save
                Thread.sleep(1000);
                Assert.assertEquals(assignmentResponsesPage.getSavedSuccessfullyMessage().getText(), "Saved successfully.", "The typed text is not  saved and Saved Successfully messageis not displayed below the textbox");
                currentAssignments.tab_Title.get(1).click();//click on assignment response page
                Thread.sleep(5000);
                driver.findElement(By.cssSelector("div[title='Release Grade for All']")).click();
                Thread.sleep(5000);
                Assert.assertEquals(assignmentResponsesPage.grade_Box.getText(),"Grades Released","Feedback is not released");

                String assigmentStatus1=assignmentResponsesPage.graded_box.get(3).getText().trim();
                System.out.println("assigmentStatus1:"+assigmentStatus1);
                Assert.assertEquals(assigmentStatus1,"Graded","Assignment status not displaying graded");

                new LoginUsingLTI().ltiLogin("464_1");//login as student
                String title3 = dashboard.getTitle().getAttribute("title");
                Assert.assertEquals(title3, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
                Assert.assertEquals(dashboard.assessment.getText().trim(), "(shor) Assessment", "File Based Assessment is not displaying under Class Activity section in a card form");
                new Navigator().NavigateTo("Dashboard");//click on dashboard
                new Navigator().NavigateTo("Assignments");//navigate to assignment
                currentAssignments.getAssessmentName().click();//click on assignment
                Thread.sleep(10000);
                String resourceName1 = assignmentTab.resourceName.getText().trim();
                if (!resourceName1.contains("shor: Assessment"))
                    Assert.fail("The User is not navigated to the Assignments page with the Assignment details");
                Assert.assertEquals(assignmentTab.teacherFeedBack.getText(), "This is a feedback", "Teacher feedback is not available");
                Assert.assertEquals(assignmentTab.score.getText(),"Score 1.0/2.0","score is not displaying");


            } catch (Exception e) {
                Assert.fail("Exception in test case endToEndFileBasedAssignmentOnInstructorAndStudentSideGradeAble of class ImpactsOnAssignmentDiscussionOnTheStudentSide", e);

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
                System.out.println(notificationtext);
                if (!notificationtext.equals(notificationMessage))
                    uploaded = false;
            } else {
                Assert.fail("Notification Message during file upload did not appear.");
            }

            driver.findElement(By.id("ls-ins-save-assigment-btn")).click(); //click on save for later
            Thread.sleep(500);
            if (errmsg > 0) {
                String notificationtext1 = driver.findElement(By.className("notification-message-body")).getText();
                System.out.println(notificationtext1);
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


    public  boolean fileUploadNotificationMessageValidateStudent(String dataIndex, String notificationMessage) {
        boolean uploaded = true;
        try {
            String filename = ReadTestData.readDataByTagName("", "filename", dataIndex);
            Thread.sleep(4000);
            if( driver.findElement(By.id("uploadFile")).isDisplayed()){
            driver.findElement(By.id("uploadFile")).click();
            }
            Thread.sleep(3000);
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(2000);
            int errmsg = driver.findElements(By.className("al-notification-message-body")).size();
            if (errmsg > 0) {
                String notificationtext = driver.findElement(By.className("al-notification-message-body")).getText();
                System.out.println(notificationtext);
                if (!notificationtext.equals(notificationMessage))
                    uploaded = false;
            } else {
                Assert.fail("Notification Message during file upload did not appear.");
            }

        } catch (Exception e) {
            Assert.fail("Exception in app helper fileUploadValidate in class FileUpload", e);
        }
        return uploaded;
    }
}
