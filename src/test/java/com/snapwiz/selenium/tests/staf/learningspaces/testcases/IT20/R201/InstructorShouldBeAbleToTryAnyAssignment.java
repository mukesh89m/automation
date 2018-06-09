package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT20.R201;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by priyanka on 4/8/2015.
 */
public class InstructorShouldBeAbleToTryAnyAssignment extends Driver {
    @Test(priority = 1,enabled = true)
    public void instructorShouldBeAbleToTryAnyAssignment() {
        try {
            //tc row no 13
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            new Assignment().create(13);
            new LoginUsingLTI().ltiLogin("13");//log in as instructor
            new Assignment().assignToStudent(13); //assign to the student
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            Assert.assertEquals(currentAssignments.getAssignmentName().getText(), "Assignment_IT20_201_13", "Assigned assignment is not available in current assignment");
            Assert.assertEquals(currentAssignments.tryIt_Link.get(0).getText(), "| Try it", "Try it link is not available");
            currentAssignments.getUnAssignButtonOfVersionAssignment().click();//click on unassign assignment
            driver.findElement(By.cssSelector("div[class='as-modal-yes-btn delete-button']")).click();//click on yes
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new AssignLesson().assignAllLesson("13");//assign all lesson
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(13);
            new UploadResources().uploadResources("13", false, false, true);//upload chapterlevel resource
            new AssignLesson().assignResourceFromMyResources(13);
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            currentAssignments.newAssignment_Button.click();//click on new assignment;
            currentAssignments.createFileBasedAssignmentButton.click();//click on file based assignment
            newAssignment.assessmentNameTextBox.click();//click on the text box
            Thread.sleep(2000);
            newAssignment.assessmentName_TextBox.sendKeys("Assessment");
            Thread.sleep(2000);
            fileUploadNotificationMessageValidate("13", "Your file upload request is being processed...");
            //Thread.sleep(7000);
            new UIElement().waitAndFindElement(By.className("ls-delete-file-icon"));
            driver.findElement(By.id("ls-ins-save-assigment-btn")).click(); //click on save for later
            new Assignment().assignFileBasedAssignmentFromMyQuestionBank(13);
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'Try it')]"));
            if(list.size()>0)
            {
                Assert.fail("Try it link is available");
            }
            new LoginUsingLTI().ltiLogin("313");//log in as instructor
            new Assignment().assignToStudent(313); //assign to the student
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.allActivity_DropDown.click();//click on all activity dropdown
            currentAssignments.questionAssignment.click();//click on question assignment
            Thread.sleep(3000);
           // currentAssignments.getUnAssignButtonOfVersionAssignment().click();//click on unassign assignment
           // driver.findElement(By.cssSelector("div[class='as-modal-yes-btn delete-button']")).click();//click on yes
            String parentWindow=driver.getWindowHandle();
            currentAssignments.tryIt_Link.get(0).click();//click on try it link
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }

            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(currentAssignments.wiley_Logo.isDisplayed(), true, "Wiley Plus logo is not displaying at left side header");
            Assert.assertEquals(currentAssignments.course_Name.isDisplayed(),true,"Course name is not displaying at middle part of header");
            Assert.assertEquals(currentAssignments.assignmentNamePreviewPage.getText(),"(Assi) Assignment_IT20_201_13","Assignment name with short name in the next line below the header is not displaying");
            Assert.assertEquals(currentAssignments.summaryDropDown.isDisplayed(),true,"Summary drop-down on the right corner of assignment name line is not present");
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(),"Q1:","first question of the assignment is not displaying by default");
            Assert.assertEquals(currentAssignments.getQuestionLabel().getText(),"True False Test Question for IT20_201_13","first question of the assignment is not displaying by default");
            Assert.assertEquals(currentAssignments.hint_Button.getText(),"Hint","Hint button is not displaying in buttom footer");
            Assert.assertEquals(currentAssignments.solution_Button.getText(),"Solution","solution button is not displaying in buttom footer");
            Assert.assertEquals(currentAssignments.checkAnswer_Button.getText(),"Check Answer","check answer button is not displaying in buttom footer");
            Assert.assertEquals(currentAssignments.reportContentError_Link.isDisplayed(),true,"Report Content Error' icon at the right side bottom corner at footer is not present.");
            Assert.assertEquals(currentAssignments.performanceTab.isDisplayed(),true,"performance tab at the right side of the question part is not displaying");
            Assert.assertEquals(currentAssignments.performanceTabImage.isDisplayed(),true,"performance tab at the right side of the question part with minimize (-) option is not displaying");
            Assert.assertEquals(currentAssignments.userThumbNail.isDisplayed(),true,"default thumbnail at the right side of header is not displaying");

            new WebDriverWait(driver,240).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(),"Q1:","first question of the assignment is not displaying by default");
            String assignmentName=currentAssignments.assignmentNamePreviewPage.getText();
            driver.switchTo().window(parentWindow);
            currentAssignments.tryIt_Link.get(0).click();//click on try it link
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);


            }
            String assignmentName1=currentAssignments.assignmentNamePreviewPage.getText();
            if(!assignmentName.equals(assignmentName1)){
                Assert.fail("same window is not getting maximized");
            }


        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToTryAnyAssignment in class InstructorShouldBeAbleToTryAnyAssignment", e);
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
            String notificationtext1 = null;
            try {
                notificationtext1 = driver.findElement(By.className("notification-message-body")).getText();
            } catch (Exception e) {
                notificationtext1 = driver.findElement(By.className("notification-message-body")).getText();
            }
            System.out.println("notificationtext1:"+notificationtext1);
            if (!notificationtext1.trim().equals("File upload in progress. Please try after some time."))
                Assert.fail("File upload in progress. Please try after some time message is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in app helper fileUploadValidate in class FileUpload", e);
        }
        return uploaded;
    }

    @Test(priority = 2,enabled = true)
    public void instructorShouldBeAbleToTryAnyAssignmentNonGradable() {
        try {
            //tc row no 13
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            new LoginUsingLTI().ltiLogin("14");//log in as instructor
            new Assignment().assignToStudent(14); //assign to the student
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            Assert.assertEquals(currentAssignments.getAssignmentName().getText(), "Assignment_IT20_201_13", "Assigned assignment is not available in current assignment");
            Assert.assertEquals(currentAssignments.tryIt_Link.get(0).getText(), "| Try it", "Try it link is not available");
            currentAssignments.getUnAssignButtonOfVersionAssignment().click();//click on unassign assignment
            driver.findElement(By.cssSelector("div[class='as-modal-yes-btn delete-button']")).click();//click on yes
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(14);
            new UploadResources().uploadResources("14", false, false, true);//upload chapterlevel resource
            new AssignLesson().assignResourceFromMyResources(14);
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            currentAssignments.newAssignment_Button.click();//click on new assignment;
            currentAssignments.createFileBasedAssignmentButton.click();//click on file based assignment
            newAssignment.assessmentNameTextBox.click();//click on the text box
            Thread.sleep(2000);
            newAssignment.assessmentName_TextBox.sendKeys("Assessment");
            Thread.sleep(2000);
            fileUploadNotificationMessageValidate("14", "Your file upload request is being processed...");
            //Thread.sleep(5000);
            new UIElement().waitAndFindElement(By.className("ls-delete-file-icon"));
            driver.findElement(By.id("ls-ins-save-assigment-btn")).click(); //click on save for later
            Thread.sleep(5000);
            new Assignment().assignFileBasedAssignmentFromMyQuestionBank(14);

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToTryAnyAssignmentNonGradable in class InstructorShouldBeAbleToTryAnyAssignment", e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void instructorShouldBeAbleToTryAnyAssignmentManualGraded() {
        try {
            //tc row no 13
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            new Assignment().create(15); //create assignment
            new Assignment().addQuestions(15, "writeboard", "");
            new Assignment().addQuestions(15, "essay", "");
            new Assignment().addQuestions(15,"audio","");
            new LoginUsingLTI().ltiLogin("15");//log in as instructor
            new Assignment().assignToStudent(15); //assign to the student
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.tryIt_Link.get(0).click();//click on try it link
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }

            new WebDriverWait(driver,240).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            currentAssignments.next_Button.click();//click on next button
            Thread.sleep(2000);
            int found= driver.findElements(By.xpath("//div[text()='Check Answer']")).size();
            if(found!=0) {
                Assert.fail("Check Answer option is displayed");

            }
            Assert.assertEquals(currentAssignments.previous_Button.isDisplayed(),true,"Previous button is not available");
            currentAssignments.nextButton.click();//click on next button
            Thread.sleep(2000);
            int found1= driver.findElements(By.xpath("//div[text()='Check Answer']")).size();
            if(found1!=0) {
                Assert.fail("Check Answer option is displayed");

            }
            currentAssignments.nextButton.click();//click on next button
            Thread.sleep(2000);
            int found2= driver.findElements(By.xpath("//div[text()='Check Answer']")).size();
            if(found2!=0) {
                Assert.fail("Check Answer option is displayed");

            }
        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToTryAnyAssignmentManualGraded in class InstructorShouldBeAbleToTryAnyAssignment", e);
        }
    }

    @Test(priority = 4,enabled = true)
    public void tryItLinkForMultipleAssignment() {
        try {
            //tc row no 38
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            new Assignment().create(38); //create assignment
            new Assignment().create(38_1); //create assignment
            new Assignment().create(38_2); //create assignment
            new Assignment().create(38_3); //create assignment
            new Assignment().create(38_4); //create assignment
            new LoginUsingLTI().ltiLogin("38");//log in as instructor
            new Assignment().assignToStudent(38); //assign to the student
            new Assignment().assignToStudent(381); //assign to the student
            new Assignment().assignToStudent(382); //assign to the student
            new Assignment().assignToStudent(383); //assign to the student
            new Assignment().assignToStudent(384); //assign to the student
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            String parentWindow=driver.getWindowHandle();
            currentAssignments.tryIt_Link.get(0).click();//click on 1st try-it
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }

            new WebDriverWait(driver,240).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            Assert.assertEquals(currentAssignments.assignmentNamePreviewPage.isDisplayed(), true, "1st browser pop up is not opened");
            driver.switchTo().window(parentWindow);
            currentAssignments.tryIt_Link.get(1).click();//click on 1st try-it
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver,240).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            Assert.assertEquals(currentAssignments.assignmentNamePreviewPage.isDisplayed(), true, "2nd browser pop up is not opened");
            driver.switchTo().window(parentWindow);

            currentAssignments.tryIt_Link.get(2).click();//click on 1st try-it
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver,240).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            Assert.assertEquals(currentAssignments.assignmentNamePreviewPage.isDisplayed(), true, "3rd browser pop up is not opened");
            driver.switchTo().window(parentWindow);
            currentAssignments.tryIt_Link.get(3).click();//click on 1st try-it
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver,240).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            Assert.assertEquals(currentAssignments.assignmentNamePreviewPage.isDisplayed(), true, "4th browser pop up is not opened");
            driver.switchTo().window(parentWindow);
            currentAssignments.tryIt_Link.get(4).click();//click on 1st try-it
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver,240).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            Assert.assertEquals(currentAssignments.assignmentNamePreviewPage.isDisplayed(), true, "5th browser pop up is not opened");

        } catch (Exception e) {
            Assert.fail("Exception in test case tryItLinkForMultipleAssignment in class InstructorShouldBeAbleToTryAnyAssignment", e);
        }
    }

    @Test(priority = 5,enabled = true)
    public void mentorShouldBeAbleToTryAnyAssignment() {
        try {
            //tc row no 13
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            new LoginUsingLTI().ltiLogin("131");//log in as mentor
            new Assignment().assignToStudent(131); //assign to the student
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            Assert.assertEquals(currentAssignments.getAssignmentName().getText(), "Assignment_IT20_201_13", "Assigned assignment is not available in current assignment");
            Assert.assertEquals(currentAssignments.tryIt_Link.get(0).getText(), "| Try it", "Try it link is not available");
            currentAssignments.getUnAssignButtonOfVersionAssignment().click();//click on unassign assignment
            driver.findElement(By.cssSelector("div[class='as-modal-yes-btn delete-button']")).click();//click on yes
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new AssignLesson().assignAllLesson("131");//assign all lesson
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(131);
            new UploadResources().uploadResources("131", false, false, true);//upload chapterlevel resource
            new AssignLesson().assignResourceFromMyResources(131);
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            currentAssignments.newAssignment_Button.click();//click on new assignment;
            currentAssignments.createFileBasedAssignmentButton.click();//click on file based assignment
            newAssignment.assessmentNameTextBox.click();//click on the text box
            Thread.sleep(2000);
            newAssignment.assessmentName_TextBox.sendKeys("Assessment");
            Thread.sleep(2000);
            fileUploadNotificationMessageValidate("131", "Your file upload request is being processed...");
            //Thread.sleep(7000);
            new UIElement().waitAndFindElement(By.className("ls-delete-file-icon"));
            driver.findElement(By.id("ls-ins-save-assigment-btn")).click(); //click on save for later
            Thread.sleep(3000);
            new Assignment().assignFileBasedAssignmentFromMyQuestionBank(13);
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'Try it')]"));
            if(list.size()>0)
            {
                Assert.fail("Try it link is available");
            }
            new LoginUsingLTI().ltiLogin("1311");//log in as mentor
            new Assignment().assignToStudent(1311); //assign to the student
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.allActivity_DropDown.click();//click on all activity dropdown
            currentAssignments.questionAssignment.click();//click on question assignment
            Thread.sleep(3000);
            //currentAssignments.getUnAssignButtonOfVersionAssignment().click();//click on unassign assignment
            //driver.findElement(By.cssSelector("div[class='as-modal-yes-btn delete-button']")).click();//click on yes
            String parentWindow=driver.getWindowHandle();
            currentAssignments.tryIt_Link.get(0).click();//click on try it link
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }

            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(currentAssignments.wiley_Logo.isDisplayed(), true, "Wiley Plus logo is not displaying at left side header");
            Assert.assertEquals(currentAssignments.course_Name.isDisplayed(),true,"Wiley Plus logo is not displaying at middle part of header");
            Assert.assertEquals(currentAssignments.assignmentNamePreviewPage.getText(),"(Assi) Assignment_IT20_201_13","Assignment name with short name in the next line below the header is not displaying");
            Assert.assertEquals(currentAssignments.summaryDropDown.isDisplayed(),true,"Summary drop-down on the right corner of assignment name line is not present");
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(),"Q1:","first question of the assignment is not displaying by default");
            Assert.assertEquals(currentAssignments.getQuestionLabel().getText(),"True False Test Question for IT20_201_13","first question of the assignment is not displaying by default");
            Assert.assertEquals(currentAssignments.hint_Button.getText(),"Hint","Hint button is not displaying in buttom footer");
            Assert.assertEquals(currentAssignments.solution_Button.getText(),"Solution","solution button is not displaying in buttom footer");
            Assert.assertEquals(currentAssignments.checkAnswer_Button.getText(),"Check Answer","check answer button is not displaying in buttom footer");
            Assert.assertEquals(currentAssignments.reportContentError_Link.isDisplayed(),true,"Report Content Error' icon at the right side bottom corner at footer is not present.");
            Assert.assertEquals(currentAssignments.performanceTab.isDisplayed(),true,"performance tab at the right side of the question part is not displaying");
            Assert.assertEquals(currentAssignments.performanceTabImage.isDisplayed(),true,"performance tab at the right side of the question part with minimize (-) option is not displaying");
            Assert.assertEquals(currentAssignments.userThumbNail.isDisplayed(),true,"default thumbnail at the right side of header is not displaying");

            new WebDriverWait(driver,240).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(),"Q1:","first question of the assignment is not displaying by default");
            String assignmentName=currentAssignments.assignmentNamePreviewPage.getText();
            driver.switchTo().window(parentWindow);
            currentAssignments.tryIt_Link.get(0).click();//click on try it link
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);


            }
            String assignmentName1=currentAssignments.assignmentNamePreviewPage.getText();
            if(!assignmentName.equals(assignmentName1)){
                Assert.fail("same window is not getting maximized");
            }


        } catch (Exception e) {
            Assert.fail("Exception in test case mentorShouldBeAbleToTryAnyAssignment in class InstructorShouldBeAbleToTryAnyAssignment", e);
        }
    }

    @Test(priority = 6,enabled = true)
    public void mentorShouldBeAbleToTryAnyAssignmentNonGradable() {
        try {
            //tc row no 13
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            new LoginUsingLTI().ltiLogin("141");//log in as mentor
            new Assignment().assignToStudent(141); //assign to the student
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            Assert.assertEquals(currentAssignments.getAssignmentName().getText(), "Assignment_IT20_201_13", "Assigned assignment is not available in current assignment");
            Assert.assertEquals(currentAssignments.tryIt_Link.get(0).getText(), "| Try it", "Try it link is not available");
            currentAssignments.getUnAssignButtonOfVersionAssignment().click();//click on unassign assignment
            driver.findElement(By.cssSelector("div[class='as-modal-yes-btn delete-button']")).click();//click on yes
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(141);
            new UploadResources().uploadResources("141", false, false, true);//upload chapterlevel resource
            new AssignLesson().assignResourceFromMyResources(141);
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            currentAssignments.newAssignment_Button.click();//click on new assignment;
            currentAssignments.createFileBasedAssignmentButton.click();//click on file based assignment
            newAssignment.assessmentNameTextBox.click();//click on the text box
            Thread.sleep(2000);
            newAssignment.assessmentName_TextBox.sendKeys("Assessment");
            Thread.sleep(2000);
            fileUploadNotificationMessageValidate("141", "Your file upload request is being processed...");
            //Thread.sleep(5000);
            new UIElement().waitAndFindElement(By.className("ls-delete-file-icon"));
            driver.findElement(By.id("ls-ins-save-assigment-btn")).click(); //click on save for later
            Thread.sleep(3000);
            new Assignment().assignFileBasedAssignmentFromMyQuestionBank(141);

        } catch (Exception e) {
            Assert.fail("Exception in test case mentorShouldBeAbleToTryAnyAssignmentNonGradable in class InstructorShouldBeAbleToTryAnyAssignment", e);
        }
    }

    @Test(priority = 7,enabled = true)
    public void mentorShouldBeAbleToTryAnyAssignmentManualGraded() {
        try {
            //tc row no 13
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("151");//log in as mentor
            new Assignment().assignToStudent(151); //assign to the student
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.tryIt_Link.get(0).click();//click on try it link
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }

            new WebDriverWait(driver,240).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            currentAssignments.next_Button.click();//click on next button
            Thread.sleep(2000);
            int found= driver.findElements(By.xpath("//div[text()='Check Answer']")).size();
            if(found!=0) {
                Assert.fail("Check Answer option is displayed");

            }
            Assert.assertEquals(currentAssignments.previous_Button.isDisplayed(),true,"Previous button is not available");
            currentAssignments.nextButton.click();//click on next button
            Thread.sleep(2000);
            int found1= driver.findElements(By.xpath("//div[text()='Check Answer']")).size();
            if(found1!=0) {
                Assert.fail("Check Answer option is displayed");

            }
            currentAssignments.nextButton.click();//click on next button
            Thread.sleep(2000);
            int found2= driver.findElements(By.xpath("//div[text()='Check Answer']")).size();
            if(found2!=0) {
                Assert.fail("Check Answer option is displayed");

            }
        } catch (Exception e) {
            Assert.fail("Exception in test case mentorShouldBeAbleToTryAnyAssignmentManualGraded in class InstructorShouldBeAbleToTryAnyAssignment", e);
        }
    }

    @Test(priority = 8,enabled = true)
    public void instructorShouldBeAbleToCheckAnswer() {
        try {
            //tc row no 40
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("40");//log in as instructor
            new Assignment().assignToStudent(40); //assign to the student
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.tryIt_Link.get(0).click();//click on try it link
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "first question of the assignment is not displaying by default");
            Assert.assertEquals(currentAssignments.hint_Button.getText(),"Hint","Hint button is not displaying in buttom footer");
            Assert.assertEquals(currentAssignments.solution_Button.getText(),"Solution","solution button is not displaying in buttom footer");
            Assert.assertEquals(currentAssignments.checkAnswer_Button.getText(),"Check Answer","check answer button is not displaying in buttom footer");
            currentAssignments.answerChoice.get(0).click();//click on option 'A'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Assert.assertEquals(currentAssignments.footerText.getText(), "You got it right.", "You got it right message is not displaying");
            currentAssignments.answerChoice.get(0).click();//click on option 'B'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Thread.sleep(3000);
            Assert.assertEquals(currentAssignments.footerText.getText(),"You got it wrong.","You got it wrong message is not displaying");

        } catch (Exception e) {
        Assert.fail("Exception in test case instructorShouldBeAbleToCheckAnswer in class InstructorShouldBeAbleToTryAnyAssignment", e);
    }
}
    @Test(priority = 9,enabled = true)
    public void instructorShouldBeAbleToCheckAnswerPartiallyCorrect() {
        try {
            //tc row no 40
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            new Assignment().create(41); //create assignment
            new Assignment().addQuestions(41, "multipleselection", "");
            new LoginUsingLTI().ltiLogin("41");//log in as instructor
            new Assignment().assignToStudent(41); //assign to the student
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.tryIt_Link.get(0).click();//click on try it link
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            currentAssignments.next_Button.click();//click on next button
            Thread.sleep(2000);
            currentAssignments.answerOption_MultipleChoice.get(0).click();//click on option 'A'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Thread.sleep(3000);
            Assert.assertEquals(currentAssignments.footerText.getText(), "You got it partially correct.", "You got it partially correct is not displaying");
            currentAssignments.previous_Button.click();//click on previous button
            Thread.sleep(2000);
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Assert.assertEquals(currentAssignments.footerText.getText(), "Choose the answer", "Choose the answer is not displaying");
            currentAssignments.next_Button.click();//click on next button
            Thread.sleep(2000);
            currentAssignments.answerOption_MultipleChoice.get(2).click();//click on option 'C'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Thread.sleep(3000);
            Assert.assertEquals(currentAssignments.footerText.getText(), "You got it wrong.", "You got it wrong is not displaying");
        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToCheckAnswerPartiallyCorrect in class InstructorShouldBeAbleToTryAnyAssignment", e);
        }
    }
    @Test(priority = 10,enabled = true)
    public void mentorShouldBeAbleToCheckAnswer() {
        try {
            //tc row no 40
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("401");//log in as mentor
            new Assignment().assignToStudent(401); //assign to the student
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.tryIt_Link.get(0).click();//click on try it link
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "first question of the assignment is not displaying by default");
            Assert.assertEquals(currentAssignments.hint_Button.getText(),"Hint","Hint button is not displaying in buttom footer");
            Assert.assertEquals(currentAssignments.solution_Button.getText(),"Solution","solution button is not displaying in buttom footer");
            Assert.assertEquals(currentAssignments.checkAnswer_Button.getText(),"Check Answer","check answer button is not displaying in buttom footer");
            currentAssignments.answerChoice.get(0).click();//click on option 'A'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Assert.assertEquals(currentAssignments.footerText.getText(), "You got it right.", "You got it right message is not displaying");
            currentAssignments.answerChoice.get(0).click();//click on option 'B'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Thread.sleep(3000);
            Assert.assertEquals(currentAssignments.footerText.getText(),"You got it wrong.","You got it wrong message is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToCheckAnswer in class InstructorShouldBeAbleToTryAnyAssignment", e);
        }
    }
    @Test(priority = 11,enabled = true)
    public void mentorShouldBeAbleToCheckAnswerPartiallyCorrect() {
        try {
            //tc row no 40
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("411");//log in as mentor
            new Assignment().assignToStudent(411); //assign to the student
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.tryIt_Link.get(0).click();//click on try it link
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Thread.sleep(2000);
            currentAssignments.next_Button.click();//click on next button
            Thread.sleep(2000);
            currentAssignments.answerOption_MultipleChoice.get(0).click();//click on option 'A'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Thread.sleep(3000);
            Assert.assertEquals(currentAssignments.footerText.getText(), "You got it partially correct.", "You got it partially correct is not displaying");
            currentAssignments.previous_Button.click();//click on previous button
            Thread.sleep(2000);
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Assert.assertEquals(currentAssignments.footerText.getText(), "Choose the answer", "Choose the answer is not displaying");
            currentAssignments.next_Button.click();//click on next button
            Thread.sleep(2000);
            currentAssignments.answerOption_MultipleChoice.get(2).click();//click on option 'C'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Thread.sleep(3000);
            Assert.assertEquals(currentAssignments.footerText.getText(), "You got it wrong.", "You got it wrong is not displaying");
        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToCheckAnswerPartiallyCorrect in class InstructorShouldBeAbleToTryAnyAssignment", e);
        }
    }


    @Test(priority = 12,enabled = true)
    public void instructorShouldBeAbleToNavigatePreviousAndNext() {
        try {
            //tc row no 49
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            new Assignment().create(49); //create assignment
            new Assignment().addQuestions(49, "truefalse", "");
            new Assignment().addQuestions(49, "truefalse", "");
            new Assignment().addQuestions(49, "truefalse", "");
            new Assignment().addQuestions(49, "truefalse", "");
            new LoginUsingLTI().ltiLogin("49");//log in as instructor
            new Assignment().assignToStudent(49); //assign to the student
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.tryIt_Link.get(0).click();//click on try it link
            String parent=driver.getWindowHandle();
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "first question of the assignment is not displaying by default");
            Assert.assertEquals(currentAssignments.next_Button.isDisplayed(),true, "Next button is not available");
            int found= driver.findElements(By.xpath("//div[text()='Previous']")).size();
            if(found!=0) {
                Assert.fail("previous button is displayed");

            }
            Assert.assertEquals(currentAssignments.reportContentError_Link.isDisplayed(), true, "Report Content Error' icon at the right side bottom corner at footer is not present.");
            currentAssignments.next_Button.click();//click on next
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q2:", "It is not navigated to the respective question");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q3");
            Thread.sleep(2000);
            currentAssignments.previous_Button.click();//click on previous button
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q2:", "It is not navigated to the respective question");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q5");
            Assert.assertEquals(currentAssignments.finishButton.isDisplayed(), true, "Finish button is not available");
            currentAssignments.finishButton.click();//click on finish button
            driver.switchTo().window(parent);
            String url=driver.getCurrentUrl();
            if(url.contains("tryItAssessmentOrAssignment"))
            {
                Assert.fail("Browser popup is not closed after click on finish button");
            }


        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToNavigatePreviousAndNext in class InstructorShouldBeAbleToTryAnyAssignment", e);
        }
    }

    public void selectQuestion(String questionNo)
    {
        driver.findElement(By.xpath("//div[text()='"+questionNo+"']")).click();

    }

    @Test(priority = 13,enabled = true)
    public void mentorShouldBeAbleToNavigatePreviousAndNext() {
        try {
            //tc row no 49
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("491");//log in as mentor
            new Assignment().assignToStudent(491); //assign to the student
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.tryIt_Link.get(0).click();//click on try it link
            String parent=driver.getWindowHandle();
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "first question of the assignment is not displaying by default");
            Assert.assertEquals(currentAssignments.next_Button.isDisplayed(),true, "Next button is not available");
            int found= driver.findElements(By.xpath("//div[text()='Previous']")).size();
            if(found!=0) {
                Assert.fail("previous button is displayed");

            }
            Assert.assertEquals(currentAssignments.reportContentError_Link.isDisplayed(), true, "Report Content Error' icon at the right side bottom corner at footer is not present.");
            currentAssignments.next_Button.click();//click on next
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q2:", "It is not navigated to the respective question");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q3");
            Thread.sleep(2000);
            currentAssignments.previous_Button.click();//click on previous button
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q2:", "It is not navigated to the respective question");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q5");
            Assert.assertEquals(currentAssignments.finishButton.isDisplayed(), true, "Finish button is not available");
            currentAssignments.finishButton.click();//click on finish button
            driver.switchTo().window(parent);
            String url=driver.getCurrentUrl();
            if(url.contains("tryItAssessmentOrAssignment"))
            {
                Assert.fail("Browser popup is not closed after click on finish button");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case mentorShouldBeAbleToNavigatePreviousAndNext in class InstructorShouldBeAbleToTryAnyAssignment", e);
        }
    }


    @Test(priority = 14,enabled = true)
    public void instructorShouldBeAbleToNavigateToParticularQuestion() {
        try {
            //tc row no 57
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("57");//log in as instructor
            new Assignment().assignToStudent(57); //assign to the student
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.tryIt_Link.get(0).click();//click on try it link
            String parent=driver.getWindowHandle();
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "first question of the assignment is not displaying by default");
            Assert.assertEquals(currentAssignments.summaryDropDown.isDisplayed(),true,"Summary drop-down on the right corner of assignment name line is not present");
            currentAssignments.helpIconOnSummaryDropDown.click();//click on help icon on summary dropdown
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            Assert.assertEquals(currentAssignments.dropDownQuestion.get(4).isDisplayed(),true,"It should not opened dropdown followed by question");
            WebElement scroll=driver.findElement(By.xpath("//div[text()='Q5']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            selectQuestion("Q3");//select question 3
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q3:", "It is not navigated to the respective question");
            currentAssignments.answerChoice.get(0).click();//click on option 'A'
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q5");//select question 5
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q3");//select question 3
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q3:", "It is not navigated to the respective question");
            Assert.assertEquals(currentAssignments.answerChoice.get(0).isSelected(),false,"The answer is saved for that question");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToNavigateToParticularQuestion in class InstructorShouldBeAbleToTryAnyAssignment", e);
        }
    }

    @Test(priority = 15,enabled = true)
    public void mentorShouldBeAbleToNavigateToParticularQuestion() {
        try {
            //tc row no 57
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("571");//log in as mentor
            new Assignment().assignToStudent(571); //assign to the student
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.tryIt_Link.get(0).click();//click on try it link
            String parent=driver.getWindowHandle();
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "first question of the assignment is not displaying by default");
            Assert.assertEquals(currentAssignments.summaryDropDown.isDisplayed(),true,"Summary drop-down on the right corner of assignment name line is not present");
            currentAssignments.helpIconOnSummaryDropDown.click();//click on help icon on summary dropdown
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            Assert.assertEquals(currentAssignments.dropDownQuestion.get(4).isDisplayed(),true,"It should not opened dropdown followed by question");
            WebElement scroll=driver.findElement(By.xpath("//div[text()='Q5']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            selectQuestion("Q3");//select question 3
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q3:", "It is not navigated to the respective question");
            currentAssignments.answerChoice.get(0).click();//click on option 'A'
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q5");//select question 5
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q3");//select question 3
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q3:", "It is not navigated to the respective question");
            Assert.assertEquals(currentAssignments.answerChoice.get(0).isSelected(),false,"The answer is saved for that question");

        } catch (Exception e) {
            Assert.fail("Exception in test case mentorShouldBeAbleToNavigateToParticularQuestion in class InstructorShouldBeAbleToTryAnyAssignment", e);
        }
    }

    @Test(priority = 16,enabled = true)
    public void instructorShouldBeAbleToViewQuestion() {
        try {
            //tc row no 63
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            new Assignment().create(63);
            new Assignment().addQuestions(631, "truefalse", "");
            new Assignment().addQuestions(632, "truefalse", "");
            new LoginUsingLTI().ltiLogin("63");//log in as instructor
            new Assignment().assignToStudent(63); //assign to the student
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.tryIt_Link.get(0).click();//click on try it link
            String parent=driver.getWindowHandle();
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Thread.sleep(1000);
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "first question of the assignment is not displaying by default");
            Assert.assertEquals(currentAssignments.performanceTab.isDisplayed(),true,"performance tab at the right side of the question part is not displaying");
            Assert.assertEquals(currentAssignments.performanceTabImage.isDisplayed(),true,"performance tab at the right side of the question part with minimize (-) option is not displaying");
            Assert.assertEquals(currentAssignments.totalPoints.isDisplayed(),true,"Total points in performance tab at the righ side panel not displaying");
            Assert.assertEquals(currentAssignments.performanceInQuestion.isDisplayed(),true,"Perfomance in last 10 question not displaying");
            Assert.assertEquals(currentAssignments.performanceGraph.isDisplayed(),true,"blank graph below points available option as of CMS preview is not displaying");
            Assert.assertEquals(currentAssignments.difficultyBar.isDisplayed(),true,"difficulty bar is not available");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q2");//select question 2
            Thread.sleep(3000);
            String str=driver.findElement(By.className("cms-question-preview-sidebar-title-sectn")).getText();
            Assert.assertEquals(str,"Points Available : 5","5 points for question no 2 is not available");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q1");//select question 2
            Thread.sleep(3000);
            String low=currentAssignments.difficultyLevelBar.getAttribute("style");
            if(!low.contains("10%"))
                Assert.fail("Question with difficulty level low is not displaying");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q2");//select question 2
            Thread.sleep(3000);
            String medium=currentAssignments.difficultyLevelBar.getAttribute("style");
            if(!medium.contains("20%"))
                Assert.fail("Question with difficulty level medium is not displaying");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q3");//select question 2
            Thread.sleep(3000);
            String high=currentAssignments.difficultyLevelBar.getAttribute("style");
            if(!high.contains("30%"))
                Assert.fail("Question with difficulty level high is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToViewQuestion in class InstructorShouldBeAbleToTryAnyAssignment", e);
        }
    }

    @Test(priority = 17,enabled = false)
    public void mentorShouldBeAbleToViewQuestion() {
        try {
            //tc row no 63
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("631");//log in as mentor
            new Assignment().assignToStudent(631); //assign to the student
            new Navigator().NavigateTo("Current Assignments");//navigate to current assignment
            currentAssignments.tryIt_Link.get(0).click();//click on try it link
            String parent=driver.getWindowHandle();
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "first question of the assignment is not displaying by default");
            Assert.assertEquals(currentAssignments.performanceTab.isDisplayed(),true,"performance tab at the right side of the question part is not displaying");
            Assert.assertEquals(currentAssignments.performanceTabImage.isDisplayed(),true,"performance tab at the right side of the question part with minimize (-) option is not displaying");
            Assert.assertEquals(currentAssignments.totalPoints.isDisplayed(),true,"Total points in performance tab at the righ side panel not displaying");
            Assert.assertEquals(currentAssignments.performanceInQuestion.isDisplayed(),true,"Perfomance in last 10 question not displaying");
            Assert.assertEquals(currentAssignments.performanceGraph.isDisplayed(),true,"blank graph below points available option as of CMS preview is not displaying");
            Assert.assertEquals(currentAssignments.difficultyBar.isDisplayed(),true,"difficulty bar is not available");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q2");//select question 2
            Thread.sleep(3000);
            String str=driver.findElement(By.className("cms-question-preview-sidebar-title-sectn")).getText();
            Assert.assertEquals(str,"Points Available : 5","5 points for question no 2 is not available");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q1");//select question 2
            Thread.sleep(3000);
            String low=currentAssignments.difficultyLevelBar.getAttribute("style");
            if(!low.contains("10%"))
                Assert.fail("Question with difficulty level low is not displaying");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q2");//select question 2
            Thread.sleep(3000);
            String medium=currentAssignments.difficultyLevelBar.getAttribute("style");
            if(!medium.contains("20%"))
                Assert.fail("Question with difficulty level medium is not displaying");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q3");//select question 2
            Thread.sleep(3000);
            String high=currentAssignments.difficultyLevelBar.getAttribute("style");
            if(!high.contains("30%"))
                Assert.fail("Question with difficulty level high is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in test case mentorShouldBeAbleToViewQuestion in class InstructorShouldBeAbleToTryAnyAssignment", e);
        }
    }
}


