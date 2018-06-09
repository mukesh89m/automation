package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R199;

import com.google.common.base.Predicate;
import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.MyActivity.MyActivity;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.AssignmentTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.MetacognitiveReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.MostChallengingReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.ProductivityReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.ProficiencyReport;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


/**
 * Created by priyanka on 3/10/2015.
 */
public class AttemptingFileBasedAssignmentAssignedByInstructor extends Driver {

    @Test(priority = 1, enabled = true)
    public void viewFunctionalityOfFileBasedAssignment () {

        try {
            //tc row no 205
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver,AssignmentTab.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            MyActivity myActivity = PageFactory.initElements(driver,MyActivity.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(205));
            new Assignment().createFileBasedAssessment(205);
            new LoginUsingLTI().ltiLogin("205_1");//login as student
            new LoginUsingLTI().ltiLogin("205");//login as instructor
            new Assignment().assignToStudent(205);
            new LoginUsingLTI().ltiLogin("205_1");//login as student
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            Assert.assertEquals(dashboard.assessment.getText().trim(),"(shor) "+assignmentname+"","File Based Assessment is not displaying under Class Activity section in a card form");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            new Navigator().NavigateTo("e-Textbook");//navigate to e-textbook
            new SelectCourse().selectInvisibleAssignment("Introduction");
            Thread.sleep(3000);
            new Navigator().navigateToTab("Assignments");//click on assignment tab
            String text=driver.findElement(By.xpath("//ul[@class='ls-right-assignment-status']/li[2]")).getText();
            String dueDate=text.replaceAll(text,"Due Date:");
            Assert.assertEquals(dueDate,"Due Date:","Due date field is not available");
            String status=driver.findElement(By.xpath("(//div[@class='ls-right-section-status'])[2]")).getText();
            String status1=status.replaceAll(status,"Status:");
            Assert.assertEquals(status1,"Status:","status field is not available");
            if(assignmentTab.likeIcon.isDisplayed()==false)
                Assert.fail("Like icon is not available");
            if(assignmentTab.commentIcon.isDisplayed()==false)
                Assert.fail("comment icon is not available");
            if(assignmentTab.assignmentIcon.isDisplayed()==false)
                Assert.fail("File Based Assessment is not having a Folder Icon with a Pencil icon before the Assessment Name ");

            assignmentTab.rightArrow.click();//click on '<'
            Thread.sleep(5000);
            if(assignmentTab.open_button.isDisplayed()==false)
                Assert.fail("open button is not available");
            assignmentTab.open_button.click();//click on open
            Thread.sleep(5000);
            Assert.assertEquals(assignmentTab.resourceName.getText().trim(),"shor: "+assignmentname+"", "The Assessment is not opened in the same tab");
            assignmentTab.closeAssignment.click();//click on 'x' on assignment
            new Navigator().navigateToTab("Assignments");//click on assignment tab
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            Assert.assertEquals(assignments.getDueDateMessage().get(0).getText().trim(),"Due Date:","Due date field is not available");
            String status2=driver.findElement(By.className("ls-assignment-status")).getText();
            Thread.sleep(3000);
            String status3=status2.replaceAll(status2,"Your Status:");
            Assert.assertEquals(status3,"Your Status:","status field is not available");
            if(assignments.likeIcon.isDisplayed()==false)
                Assert.fail("Like icon is not available");
            if(assignments.commentIcon.isDisplayed()==false)
                Assert.fail("comment icon is not available");
            if(assignments.assessmentIcon.isDisplayed()==false)
                Assert.fail("File Based Assessment is not having a Folder Icon with a Pencil icon before the Assessment Name ");

            currentAssignments.getAssessmentName().click();//click on
            new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title")));
            String resourceName=assignmentTab.resourceName.getText().trim();
            if(!resourceName.contains("shor: FileBasedAssessment"))
                Assert.fail("The User is not navigated to the Assignments page with the Assignment details");

            new Navigator().NavigateTo("Dashboard");//click on dashboard
            new Navigator().NavigateTo("Course Stream");//navigate to course stream
            Assert.assertEquals(courseStreamPage.assessmentName.getText(), "(shor) "+assignmentname+"", "The File Based Assignment is not displaying at the top with its mandatory details");
            String duedate=driver.findElement(By.className("ls-stream-assignment-due-date")).getText();
            Thread.sleep(3000);
            String duedate1=duedate.replaceAll(duedate,"Due Date:");
            Assert.assertEquals(duedate1,"Due Date:","Due date field is not available");
            if(assignments.likeIcon.isDisplayed()==false)
                Assert.fail("Like icon is not available");
            if(assignments.commentIcon.isDisplayed()==false)
                Assert.fail("comment icon is not available");
            if(courseStreamPage.assignmentIcon.isDisplayed()==false){
                Assert.fail("File Based Assessment is not  having a Folder Icon with a Pencil icon before the Assessment Name ");
            }

            Assert.assertEquals(courseStreamPage.resourceLink.getText(), "img.png", "image link is not available");
            courseStreamPage.assessmentName.click();//click on assessment name
            new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title")));
            String resourceName1=assignmentTab.resourceName.getText().trim();
            if(!resourceName1.contains("shor: FileBasedAssessment"))
                Assert.fail("The User is not navigated to the Assignments page with the Assignment details");

            new Navigator().NavigateTo("Dashboard");//click on dashboard
            new Navigator().NavigateTo("My Activity");//navigate to my activity
            Assert.assertEquals(myActivity.myActivityTitle.getText(),"My Activities","The User is not navigated to the My Activity page");
            if (myActivity.assessmentLink.isDisplayed()==false)
                Assert.fail("The File Based Assessment is not displaying with its mandatory details");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            Assert.assertEquals(assignments.getDueDateMessage().get(0).getText().trim(),"Due Date:","Due date field is not available");
            String status4=driver.findElement(By.className("ls-assignment-status")).getText();
            Thread.sleep(3000);
            String status5=status4.replaceAll(status4,"Your Status:");
            Assert.assertEquals(status5,"Your Status:","status field is not available");
            if(assignments.likeIcon.isDisplayed()==false)
                Assert.fail("Like icon is not available");
            if(assignments.commentIcon.isDisplayed()==false)
                Assert.fail("comment icon is not available");
            if(assignments.assessmentIcon.isDisplayed()==false)
                Assert.fail("File Based Assessment is not having a Folder Icon with a Pencil icon before the Assessment Name ");


        } catch (Exception e) {
            Assert.fail("Exception in test case viewFunctionalityOfFileBasedAssignment of class AttemptingFileBasedAssignmentAssignedByInstructor", e);
        }
    }


    @Test(priority = 2, enabled = true)
    public void pageFunctionalityOfFileBasedAssignmentOnStudentSide() {

        try {
            //tc row no 236
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver,AssignmentTab.class);
            new Assignment().createFileBasedAssessment(236);
            new LoginUsingLTI().ltiLogin("236_1");//login as student
            new LoginUsingLTI().ltiLogin("236");//login as instructor
            new Assignment().assignFileBasedAssignmentToStudent(236);

            new LoginUsingLTI().ltiLogin("236_1");//login as student
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            Assert.assertEquals(assignments.getDueDateMessage().get(0).getText().trim(),"Due Date:","Due date field is not available");
            String status2=driver.findElement(By.className("ls-assignment-status")).getText();
            Thread.sleep(3000);
            String status3=status2.replaceAll(status2,"Your Status:");
            Assert.assertEquals(status3,"Your Status:","status field is not available");
            if(assignments.likeIcon.isDisplayed()==false)
                Assert.fail("Like icon is not available");
            if(assignments.commentIcon.isDisplayed()==false)
                Assert.fail("comment icon is not available");
            if(assignments.assessmentIcon.isDisplayed()==false)
                Assert.fail("File Based Assessment is not having a Folder Icon with a Pencil icon before the Assessment Name ");
            currentAssignments.getAssessmentName().click();//click on assignment
            new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title")));
            String resourceName=assignmentTab.resourceName.getText().trim();
            if(!resourceName.contains("shor: FileBasedAssessment"))
                Assert.fail("The User is not navigated to the Assignments page with the Assignment details");
            Assert.assertEquals(assignmentTab.uploadedResource.getText(),"img.png"," Uploaded Files by the Instructor is not displaying");
            Assert.assertEquals(assignmentTab.finishButton.getText(),"Finish Assignment"," Finish Assignment button is not displaying");
            Assert.assertEquals(assignmentTab.uploadFileLink.getText(),"+ Upload file(s)"," + Upload file(s) link is not displaying");
            if(assignmentTab.optionalField.isDisplayed()==false)
                Assert.fail(" A free form text field for the student response is not available");
            Assert.assertEquals(assignmentTab.gradingPolicyField.getText(),"Assignment Reference:","Assignment Reference: field is not displaying");
            Assert.assertEquals(assignmentTab.description.getText(),"promptdetails","promptdetails field is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in test case pageFunctionalityOfFileBasedAssignmentOnStudentSide of class AttemptingFileBasedAssignmentAssignedByInstructor", e);
        }
    }


    @Test(priority = 3, enabled = true)
    public void uploadFileLinkFunctionalityOnFileBasedAssignmentPage () {

        try {
            //tc row no 242
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver,AssignmentTab.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            new Assignment().createFileBasedAssessment(242);
            new LoginUsingLTI().ltiLogin("242_1");//login as student
            new LoginUsingLTI().ltiLogin("242");//login as instructor
            new Assignment().assignFileBasedAssignmentToStudent(242);
            new LoginUsingLTI().ltiLogin("242_1");//login as student
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssessmentName().click();//click on assignment
            new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title")));
            String resourceName=assignmentTab.resourceName.getText().trim();
            if(!resourceName.contains("shor: FileBasedAssessment"))
                Assert.fail("The User is not navigated to the Assignments page with the Assignment details");
            fileUploadNotificationMessageValidate("242", "Your file upload request is being processed...");
            Thread.sleep(5000);
            if (newAssignment.fieUpload.get(1).isDisplayed() == false)
                Assert.fail("The newly added file is not come under your response section");
            fileUploadNotificationMessageValidate("242", "Your file upload request is being processed...");
            Thread.sleep(5000);
            if (newAssignment.fieUpload.get(1).isDisplayed() == false)
                Assert.fail("The uploaded file is not displaying  above the Upload File(s) button.");
            if (newAssignment.imageIcon.get(1).isDisplayed() == false)
                Assert.fail("The icons and the file names is not proper with its extensions.");


        } catch (Exception e) {
            Assert.fail("Exception in test case uploadFileLinkFunctionalityOnFileBasedAssignmentPage of class AttemptingFileBasedAssignmentAssignedByInstructor", e);
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


    @Test(priority = 4, enabled = true)
    public void deleteFunctionalityAfterFileUploadsOnFileBasedAssignment() {

        try {
            //tc row no 253
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver,AssignmentTab.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            new Assignment().createFileBasedAssessment(253);
            new LoginUsingLTI().ltiLogin("253_1");//login as student
            new LoginUsingLTI().ltiLogin("253");//login as instructor
            new Assignment().assignFileBasedAssignmentToStudent(253);
            new LoginUsingLTI().ltiLogin("253_1");//login as student
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssessmentName().click();//click on assignment
            new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title")));
            String resourceName=assignmentTab.resourceName.getText().trim();
            if(!resourceName.contains("shor: FileBasedAssessment"))
                Assert.fail("The User is not navigated to the Assignments page with the Assignment details");
            fileUploadNotificationMessageValidate("253", "Your file upload request is being processed...");
            Thread.sleep(5000);
            if (newAssignment.fieUpload.get(1).isDisplayed() == false)
                Assert.fail("The newly added file is not come under your response section");
            newAssignment.deleteIcon.click();//click on delete icon
            Assert.assertEquals(newAssignment.deleteNotificationMessage.getText(), "You are about to delete this file. Are you sure?   Yes      No", "You are about to delete this file. Are you sure? message is not displaying");
            newAssignment.yesOnDeleteMessage.click();//click on yes
            Thread.sleep(4000);
            boolean file = new BooleanValue().presenceOfElement(253, By.xpath("(//div[@class='ls-uploaded-file'])[2]"));
            Assert.assertEquals(file, false, "The file is not deleted and it is displaying on the page");
            fileUploadNotificationMessageValidate("253", "Your file upload request is being processed...");
            Thread.sleep(5000);
            if (newAssignment.fieUpload.get(1).isDisplayed() == false)
                Assert.fail("The uploaded file is not displaying  above the Upload File(s) button.");

            newAssignment.deleteIcon.click();//click on delete icon
            Assert.assertEquals(newAssignment.deleteNotificationMessage.getText(), "You are about to delete this file. Are you sure?   Yes      No", "You are about to delete this file. Are you sure? message is not displaying");
            newAssignment.noOnDeleteMessage.click();//click on no
            if (newAssignment.fieUpload.get(1).isDisplayed() == false)
                Assert.fail("The file is deleted and should not be displayed on the ");
            newAssignment.deleteIcon.click();//click on delete icon
            Assert.assertEquals(newAssignment.deleteNotificationMessage.getText(), "You are about to delete this file. Are you sure?   Yes      No", "You are about to delete this file. Are you sure? message is not displaying");
            newAssignment.closeIcon.click();//click on 'x'
            if (newAssignment.fieUpload.get(1).isDisplayed() == false)
                Assert.fail("The file is deleted and should not be displayed on the ");


        } catch (Exception e) {
            Assert.fail("Exception in test case deleteFunctionalityAfterFileUploadsOnFileBasedAssignment of class AttemptingFileBasedAssignmentAssignedByInstructor", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void finishAssignmentButtonFunctionalityOnFileBasedAssignment() {

        try {
            //tc row no 268
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver,AssignmentTab.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            new Assignment().createFileBasedAssessment(268);
            new LoginUsingLTI().ltiLogin("268_1");//login as student
            new LoginUsingLTI().ltiLogin("268");//login as instructor
            new Assignment().assignFileBasedAssignmentToStudent(268);
            new LoginUsingLTI().ltiLogin("268_1");//login as student
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssessmentName().click();//click on assignment
            new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title")));
            String resourceName=assignmentTab.resourceName.getText().trim();
            if(!resourceName.contains("shor: FileBasedAssessment"))
                Assert.fail("The User is not navigated to the Assignments page with the Assignment details");
            fileUploadNotificationMessageValidate("253", "Your file upload request is being processed...");
            Thread.sleep(7000);
            if (newAssignment.fieUpload.get(1).isDisplayed() == false)
                Assert.fail("The newly added file is not come under your response section");
            assignmentTab.finishButton.click();//click on finish assignment
            Thread.sleep(3000);
            Assert.assertEquals(assignmentTab.responseTag.getText(), "Your Response:", "The assignment is not submitted and the user not at same page");
            assignmentTab.continueButton.click();//click on continue
            if(currentAssignments.getAssessmentName().isDisplayed()==false)
                Assert.fail("The User is not navigated to the Assignment page");

            Assert.assertEquals(assignments.status_submitted.getText(), "Submitted", "Status is not submitted");
            if (driver.findElement(By.cssSelector("i[class='ls-right-assignment-status-icon ls-right-assignment-submitted']")).isDisplayed()==false)
                Assert.fail("Tick mark is not available");


        } catch (Exception e) {
            Assert.fail("Exception in test case finishAssignmentButtonFunctionalityOnFileBasedAssignment of class AttemptingFileBasedAssignmentAssignedByInstructor", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void downloadingFilesFunctionalityOnFileBasedAssignment() {

        try {
            //tc row no 279
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver,AssignmentTab.class);
            new Assignment().createFileBasedAssessment(279);
            new LoginUsingLTI().ltiLogin("279_1");//login as student
            new LoginUsingLTI().ltiLogin("279");//login as instructor
            new Assignment().assignFileBasedAssignmentToStudent(279);
            new LoginUsingLTI().ltiLogin("279_1");//login as student
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssessmentName().click();//click on assignment
            Thread.sleep(10000);
            String resourceName=assignmentTab.resourceName.getText().trim();
            if(!resourceName.contains("shor: FileBasedAssessment"))
                Assert.fail("The User is not navigated to the Assignments page with the Assignment details");
            Assert.assertEquals(assignmentTab.uploadedResource.getText(),"img.png"," Uploaded Files by the Instructor is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in test case downloadingFilesFunctionalityOnFileBasedAssignment of class AttemptingFileBasedAssignmentAssignedByInstructor", e);
        }
    }
    @Test(priority = 7, enabled = true)
    public void gradingAndFeedbackOfFileBasedAssignment () {

        try {
            //tc row no 286
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver,AssignmentTab.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            new Assignment().createFileBasedAssessment(286);
            new LoginUsingLTI().ltiLogin("286_1");//login as student
            new LoginUsingLTI().ltiLogin("286");//login as instructor
            new Assignment().assignFileBasedAssignmentToStudent(286);

            new LoginUsingLTI().ltiLogin("286_1");//login as student
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssessmentName().click();//click on assignment
            Thread.sleep(10000);
            assignmentTab.finishButton.click();//click on finish assignment
            assignmentTab.continueButton.click();//click on continue

            new LoginUsingLTI().ltiLogin("286");//login as instructor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            Assert.assertEquals(currentAssignments.getCurrentAssignmentTitle().getText(), "Current Assignments", "Current Assignmnet page is not displaying to the user");
            Assert.assertEquals(currentAssignments.getAssignmentName().getText(),"(shor) FileBasedAssessment_R199_286","File Based Assessment is not available ");
            currentAssignments.getViewGrade_link().click();//click on view response
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.tab_Title.get(1).getText(),"Response - (shor) FileBasedAssessment_R199_286","The Assignment Response page is not opened as a new tab next to current Assignment tab");

            if(assignmentResponsesPage.percentageColumn.isDisplayed()==false)
                Assert.fail("Percentage column is not available");
            Assert.assertEquals(assignmentResponsesPage.getViewResponse_link().getText(),"100%","The % complete cloumn is not showing 100%");

            List<WebElement> list = driver.findElements(By.xpath("/*//*[contains(text(),'Score')]"));
            Assert.assertTrue(list.size() > 0,"Score field is present");

            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            Assert.assertEquals(currentAssignments.tab_Title.get(2).getText(), "Response - (shor) FileBasedAssessment_R199_286", "The user is not navigated to the Student response page in a new tab");
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("This is a feedback");
            Thread.sleep(1000);
            assignmentResponsesPage.getSaveButton().click();//click on save
            Thread.sleep(1000);
            Assert.assertEquals(assignmentResponsesPage.getSavedSuccessfullyMessage().getText(), "Saved successfully.", "The typed text is not  saved and Saved Successfully messageis not displayed below the textbox");
            currentAssignments.tab_Title.get(1).click();//click on assignment response page
            new Assignment().releaseGrades(286, "Release Feedback for All");
            Thread.sleep(3000);
            Assert.assertEquals(assignmentResponsesPage.feedback_Box.getText(),"Feedback Released","Feedback is not released");
            Assert.assertEquals(assignmentResponsesPage.getReviewStatus().getText(),"Reviewed","The Class Status on the page is not changed to Reviewed from Review in Progress");


        } catch (Exception e) {
            Assert.fail("Exception in test case gradingAndFeedbackOfFileBasedAssignment of class AttemptingFileBasedAssignmentAssignedByInstructor", e);
        }
    }

    @Test(priority = 8, enabled = true)
    public void gradingAndFeedbackOfFileBasedAssignmentGradeAble () {

        try {
            //tc row no 299
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver,AssignmentTab.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            new Assignment().createFileBasedAssessment(299);
            new LoginUsingLTI().ltiLogin("299_1");//login as student
            new LoginUsingLTI().ltiLogin("299");//login as instructor
            new Assignment().assignFileBasedAssignmentToStudent(299);
            new LoginUsingLTI().ltiLogin("299_1");//login as student
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssessmentName().click();//click on assignment
            Thread.sleep(10000);
            assignmentTab.finishButton.click();//click on finish assignment
            assignmentTab.continueButton.click();//click on continue
            new LoginUsingLTI().ltiLogin("299");//login as instructor
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            Assert.assertEquals(currentAssignments.getCurrentAssignmentTitle().getText(), "Current Assignments", "Current Assignmnet page is not displaying to the user");
            Assert.assertEquals(currentAssignments.getAssignmentName().getText(),"(shor) FileBasedAssessment_R199_299","File Based Assessment is not available ");
            currentAssignments.getViewGrade_link().click();//click on view response
            Assert.assertEquals(currentAssignments.tab_Title.get(1).getText(),"Response - (shor) FileBasedAssessment_R199_299","The Assignment Response page is not opened as a new tab next to current Assignment tab");
            List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'Score')]"));
            Assert.assertTrue(list.size() > 0,"Score field is present");
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            Assert.assertEquals(currentAssignments.tab_Title.get(2).getText(), "Response - (shor) FileBasedAssessment_R199_299", "The user is not navigated to the Student response page in a new tab");
            Assert.assertEquals(assignmentResponsesPage.details.getText(),"promptdetails","prompt details is not available");
            Assert.assertEquals(assignmentResponsesPage.resourceTitle.getText(),"img.png","uploaded resource is not available");
            assignmentResponsesPage.getScore().sendKeys("1");
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("This is a feedback");
            Thread.sleep(1000);
            assignmentResponsesPage.getSaveButton().click();//click on save
            Thread.sleep(1000);
            Assert.assertEquals(assignmentResponsesPage.getSavedSuccessfullyMessage().getText(), "Saved successfully.", "The typed text is not  saved and Saved Successfully messageis not displayed below the textbox");
            currentAssignments.tab_Title.get(1).click();//click on assignment response page
            new Assignment().releaseGrades(299,"Release Grade for All");
            Thread.sleep(3000);
            Assert.assertEquals(assignmentResponsesPage.grade_Box.getText(),"Grades Released","Feedback is not released");
            Assert.assertEquals(assignmentResponsesPage.getReviewStatus().getText(),"Graded","The Class Status on the page is not changed to Reviewed from Review in Progress");


        } catch (Exception e) {
            Assert.fail("Exception in test case gradingAndFeedbackOfFileBasedAssignmentGradeAble of class AttemptingFileBasedAssignmentAssignedByInstructor", e);
        }
    }



    @Test(priority = 9, enabled = true)
    public void feedbackOfSubmittedFileBasedAssignment() {

        try {
            //tc row no 318
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(318));
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            new Assignment().createFileBasedAssessment(318);
            new LoginUsingLTI().ltiLogin("318_1");//login as student
            new LoginUsingLTI().ltiLogin("318");//login as instructor
            new Assignment().assignFileBasedAssignmentToStudent(318);
            new LoginUsingLTI().ltiLogin("318_1");//login as student
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssessmentName().click();//click on assignment
            Thread.sleep(10000);
            assignmentTab.finishButton.click();//click on finish assignment
            assignmentTab.continueButton.click();//click on continue
            new LoginUsingLTI().ltiLogin("318");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("This is a feedback");
            Thread.sleep(1000);
            assignmentResponsesPage.getSaveButton().click();//click on save
            Thread.sleep(1000);
            currentAssignments.tab_Title.get(1).click();//click on assignment response page
            new Assignment().releaseGrades(318, "Release Feedback for All");
            new LoginUsingLTI().ltiLogin("318_1");//login as student
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            Assert.assertEquals(dashboard.assessment.getText().trim(),"(shor) "+assignmentname+"","File Based Assessment is not displaying under Class Activity section in a card form");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssessmentName().click();//click on assignment
            new WebDriverWait(driver,3000).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title")));
            String resourceName=assignmentTab.resourceName.getText().trim();
            if(!resourceName.contains("shor: FileBasedAssessment"))
                Assert.fail("The User is not navigated to the Assignments page with the Assignment details");
            Assert.assertEquals(assignmentTab.teacherFeedBack.getText(),"This is a feedback","Teacher feedback is not available");


        } catch (Exception e) {
            Assert.fail("Exception in test case feedbackOfSubmittedFileBasedAssignment of class AttemptingFileBasedAssignmentAssignedByInstructor", e);
        }
    }

    @Test(priority = 10, enabled = true)
    public void gradesAndFeedbackOfSubmittedFileBasedAssignment() {

        try {
            //tc row no 324
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver,AssignmentTab.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(324));
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            new Assignment().createFileBasedAssessment(324);
            new LoginUsingLTI().ltiLogin("324_1");//login as student
            new LoginUsingLTI().ltiLogin("324");//login as instructor
            new Assignment().assignFileBasedAssignmentToStudent(324);
            new LoginUsingLTI().ltiLogin("324_1");//login as student
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssessmentName().click();//click on assignment
            Thread.sleep(10000);
            assignmentTab.finishButton.click();//click on finish assignment
            assignmentTab.continueButton.click();//click on continue
            new LoginUsingLTI().ltiLogin("324");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            currentAssignments.getViewGrade_link().click();//click on view response
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
            new Assignment().releaseGrades(324, "Release Grade for All");
            new LoginUsingLTI().ltiLogin("324_1");//login as student
            String title = dashboard.getTitle().getAttribute("title");
            Assert.assertEquals(title, "WileyPLUS Learning Space", "Title as 'WileyPLUS Learning Space' is not displaying");
            Assert.assertEquals(dashboard.assessment.getText().trim(),"(shor) "+assignmentname+"","File Based Assessment is not displaying under Class Activity section in a card form");
            new Navigator().NavigateTo("Dashboard");//click on dashboard
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssessmentName().click();//click on assignment
            new WebDriverWait(driver,3000).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title")));
            String resourceName=assignmentTab.resourceName.getText().trim();
            if(!resourceName.contains("shor: FileBasedAssessment"))
                Assert.fail("The User is not navigated to the Assignments page with the Assignment details");
            Assert.assertEquals(assignmentTab.teacherFeedBack.getText(),"This is a feedback","Teacher feedback is not available");
            Assert.assertEquals(assignmentTab.score.getText(),"Score 1.0/2.0","score is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in test case gradesAndFeedbackOfSubmittedFileBasedAssignment of class AttemptingFileBasedAssignmentAssignedByInstructor", e);
        }
    }


    @Test(priority = 11, enabled = true)
    public void dashBoardAndReportsImpactOfSubmittedFileBasedAssignment () {

        try {
            //tc row no 331
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver,AssignmentTab.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(324));
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            ProficiencyReport proficiencyReport = PageFactory.initElements(driver,ProficiencyReport.class);
            MetacognitiveReport metacognitiveReport = PageFactory.initElements(driver,MetacognitiveReport.class);
            ProductivityReport productivityReport = PageFactory.initElements(driver,ProductivityReport.class);
            MostChallengingReport mostChallengingReport = PageFactory.initElements(driver,MostChallengingReport.class);
            new Assignment().createFileBasedAssessment(331);
            new LoginUsingLTI().ltiLogin("331_1");//login as student
            new LoginUsingLTI().ltiLogin("331");//login as instructor
            new Assignment().assignFileBasedAssignmentToStudent(331);
            new LoginUsingLTI().ltiLogin("331_1");//login as student
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssessmentName().click();//click on assignment
            Thread.sleep(10000);
            assignmentTab.finishButton.click();//click on finish assignment
            assignmentTab.continueButton.click();//click on continue

            new LoginUsingLTI().ltiLogin("331");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();",currentAssignments.getViewResponseLink());
            } catch (Exception e) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", currentAssignments.getViewResponseLink());
            }
            assignmentResponsesPage.getScore().sendKeys("1");
            assignmentResponsesPage.getFeedbackTextArea().sendKeys("This is a feedback");
            Thread.sleep(1000);
            assignmentResponsesPage.getSaveButton().click();//click on save
            Thread.sleep(1000);
            currentAssignments.tab_Title.get(1).click();//click on assignment response page
            new Assignment().releaseGrades(331, "Release Grade for All");

            new LoginUsingLTI().ltiLogin("331_1");//login as student
            Assert.assertEquals(dashboard.timeSpent.getText(), "0", "The time spent section is changed");
            Assert.assertEquals(dashboard.getAvgQuestionPerformance().get(0).getText().trim(),"0\n%","Participation rating is changed");
            Assert.assertEquals(dashboard.getAvgQuestionPerformance().get(1).getText(),"0","Question Performance rating is changed");
            Assert.assertEquals(dashboard.getAvgQuestionPerformance().get(2).getText().trim(),"0\n%","Question Attempted is changed");
            new  Navigator().NavigateTo("Proficiency Report");
            Assert.assertEquals(proficiencyReport.getProficiencyReportTitleStudentSide().getText(),"Proficiency Report","Proficiency report page is not opened");
            if(proficiencyReport.notificationBlock.isDisplayed()==false)
                Assert.fail("Proficiency report page is changed");

            new Navigator().NavigateTo("Metacognitive Report");
            Assert.assertEquals(metacognitiveReport.getMetacognitiveReportPageTitle().getAttribute("title"),"Metacognitive Report","Metacognitive Report page is not opened");
            if(metacognitiveReport.notificationBlock.isDisplayed()==false)
                Assert.fail("Metacognitive report page is changed");

            new Navigator().NavigateTo("Productivity Report");
            Assert.assertEquals(productivityReport.getProductivityReportPageTitle().getAttribute("title"),"Productivity Report","Productivity Report page is not opened");
            if(productivityReport.notificationBlock.isDisplayed()==false)
                Assert.fail("Productivity report page is changed");

            new Navigator().NavigateTo("Most Challenging Activities Report");
            Assert.assertEquals(mostChallengingReport.getMostChallengingReportPageTitle().getAttribute("title"),"Most Challenging Activities","Productivity Report page is not opened");
            if(mostChallengingReport.notificationBlock.isDisplayed()==false)
                Assert.fail("Most Challenging Activities Report page is changed");


        } catch (Exception e) {
            Assert.fail("Exception in test case dashBoardAndReportsImpactOfSubmittedFileBasedAssignment of class AttemptingFileBasedAssignmentAssignedByInstructor", e);
        }
    }


}
