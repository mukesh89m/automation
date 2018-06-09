package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT21.R217;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.CourseOutline;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.Summary;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.EngagementReport;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by priyanka on 6/25/2015.
 */
public class InstructorGivenWorkboardFeedbackForStaticAssessment extends Driver {
    @Test(priority = 1, enabled = true)
    public void instructorGivenWorkBoardFeedbackForStaticAssessment() {

        try {
            //tc row no 205
            Questions questions = PageFactory.initElements(driver, Questions.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            EngagementReport engagementReport = PageFactory.initElements(driver, EngagementReport.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            new Assignment().create(205);//Create an assignment
            new LoginUsingLTI().ltiLogin("205_1");//login as student
            new TOCShow().chaptertree();//click on toc
            new TopicOpen().chapterOpen(2);
            new SelectCourse().selectInvisibleAssignment("IT21_R217_static_Assessment_205");
            Thread.sleep(5000);
            new AttemptQuestion().trueFalse(false, "incorrect", 205);
            new WriteBoard().enterTextInWriteBoardFromCMS("text", 205);
            questions.crossIcon.click();//click on 'x'
            Thread.sleep(2000);
            assignments.button_submitAnswer.click();//click on submit
            new UIElement().waitAndFindElement(By.xpath("//div[@id='footer-info-content']//div[@class='ls-static-practice-test-next-button']//input[@title='Finish']"));
            List<WebElement> button = assignments.submitButton;
            for (WebElement ele : button) {
                if (ele.isDisplayed()) {
                    ele.click();//click on finish

                }
            }
            new LoginUsingLTI().ltiLogin("205");//login as instructor
            new Navigator().NavigateTo("Engagement Report");//navigate to engagement report
            engagementReport.assessmentBox.get(1).click();
            new TopicOpen().chapterOpen(2);
            new SelectCourse().selectInvisibleAssignment("IT21_R217_static_Assessment_205");
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            new WriteBoard().drawSquareInWriteBoardInstructorSide(205);//draw square in workBoard
            assignmentResponsesPage.crossIconOnWorkBoard.click();//click on 'x'
            assignmentResponsesPage.getSaveButton().click();//click on save
            new LoginUsingLTI().ltiLogin("205_1");//login as student
            dashboard.assessmentLink.get(0).click();//click on assessment
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'report-sidebar-question-card-sectn')]")));
            questions.question_card.get(0).click();//click on 1st card
            Thread.sleep(3000);
            Assert.assertEquals(questions.whiteboardLink.isDisplayed(), true, "The View your work button with feedback icon on the button is not displaying in right top corner of the question view.");
            questions.plusWorkBoard.click();//click on view your work button
            String crossIcon = questions.crossIcon.getText();
            Assert.assertEquals(crossIcon, "x", "An overlay on top of the question with workboard editor is not displaying");
            driver.switchTo().frame(questions.frame);
            Assert.assertEquals(questions.teacherFeedback.isEnabled(), true, "The feedback icon in the tool bar is not enabled.");
            Actions act = new Actions(driver);
            WebElement teacherFeedback = questions.teacherFeedback;
            act.moveToElement(teacherFeedback).build().perform();
            Assert.assertEquals(questions.teacherFeedback.getAttribute("title"), "Show instructor comments", "Show Instructor comments Tool is not appear for feedback icon.");
            questions.teacherFeedback.click();//click on teacher feedback icon

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorGivenWorkBoardFeedbackForStaticAssessment of class InstructorGivenWorkboardFeedbackForStaticAssessment", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void instructorMustHaveGivenWorkBoardFeedbackForAssignment() {

        try {
            //tc row no 214
            Questions questions = PageFactory.initElements(driver, Questions.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);

            new Assignment().create(214);//Create an assignment
            new LoginUsingLTI().ltiLogin("214_1");//login as student
            new LoginUsingLTI().ltiLogin("214");//login as instructor
            new Assignment().assignToStudent(214);//assign to student
            new LoginUsingLTI().ltiLogin("214_1");//login as student
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssignmentName().click();//click on assignment
            Thread.sleep(6000);
            new AttemptQuestion().trueFalse(false, "incorrect", 214);
            new WriteBoard().enterTextInWriteBoardFromCMS("text", 214);
            questions.crossIcon.click();//click on 'x'
            Thread.sleep(2000);
            new Assignment().submitButtonInQuestionClick();
            new LoginUsingLTI().ltiLogin("214");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current Assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            new WriteBoard().drawSquareInWriteBoardInstructorSide(214);//draw square in workBoard
            assignmentResponsesPage.crossIconOnWorkBoard.click();//click on 'x'
            assignmentResponsesPage.getSaveButton().click();//click on save
            new LoginUsingLTI().ltiLogin("214_1");//login as student
            dashboard.assessmentLink.get(0).click();//click on assessment
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'report-sidebar-question-card-sectn')]")));
            questions.question_card.get(0).click();//click on 1st card
            Thread.sleep(3000);
            Assert.assertEquals(questions.whiteboardLink.isDisplayed(), true, "The View your work button with feedback icon on the button is not displaying in right top corner of the question view.");
            questions.plusWorkBoard.click();//click on view your work button
            String crossIcon = questions.crossIcon.getText();
            Assert.assertEquals(crossIcon, "x", "An overlay on top of the question with workboard editor is not displaying");
            driver.switchTo().frame(questions.frame);
            Assert.assertEquals(questions.teacherFeedback.isEnabled(), true, "The feedback icon in the tool bar is not enabled.");
            Actions act = new Actions(driver);
            WebElement teacherFeedback = questions.teacherFeedback;
            act.moveToElement(teacherFeedback).build().perform();
            Assert.assertEquals(questions.teacherFeedback.getAttribute("title"), "Show instructor comments", "Show Instructor comments Tool is not appear for feedback icon.");
            questions.teacherFeedback.click();//click on teacher feedback icon


        } catch (Exception e) {
            Assert.fail("Exception in test case instructorMustHaveGivenWorkBoardFeedbackForAssignment of class InstructorGivenWorkboardFeedbackForStaticAssessment", e);
        }

    }

    @Test(priority = 3, enabled = true)
    public void enhancementsToSupportLatestWriteBoardVersionToSupportWorkBoardQuestion() {

        try {
            //tc row no 223
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(223));
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);

            new Assignment().create(223);//Create an assignment
            new Assignment().addQuestions(223, "writeboard", "");
            new LoginUsingLTI().ltiLogin("223");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            Thread.sleep(1000);
            questionBank.getQuestionBankTitle().click();//click on question bank
            Thread.sleep(1000);
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Thread.sleep(1000);
            questionBank.getCustomizeThis().click();//click on customize this link
            Thread.sleep(2000);
            newAssignment.searchButton.click();//click on search button
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myQuestionBank.workBoard.get(0));
            Assert.assertEquals(myQuestionBank.workBoard.get(0).getText(), "Workboard", "The name of question type is not changed from “Writeboard” to “Workboard”.");
            Thread.sleep(1000);
            newAssignment.browseButton.click();//click on browse button
            Thread.sleep(2000);
           // newAssignment.browseDropDown.click();//click on browse dropdown
            //Thread.sleep(1000);
            myQuestionBank.AllQuestionType_Arrow.get(1).click();//click on all question type
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", myQuestionBank.workBoard.get(1));
            Assert.assertEquals(myQuestionBank.workBoard.get(1).getText(), "Workboard", "The name of question type is not changed from “Writeboard” to “Workboard”.");
            newAssignment.searchButton.click();//click on search button
            new AssignLesson().selectQuestionForCustomAssignment("223");//select one question
            Thread.sleep(1000);
            newAssignment.tabTittle.get(4).click();//click on selected question sub tab
            Assert.assertEquals(newAssignment.questionType.getText(), "Workboard", "The name of question type is not changed from “Writeboard” to “Workboard” in \"Question type\" label.");


        } catch (Exception e) {
            Assert.fail("Exception in test case enhancementsToSupportLatestWriteBoardVersionToSupportWorkBoardQuestion of class InstructorGivenWorkboardFeedbackForStaticAssessment", e);
        }

    }

    @Test(priority = 4, enabled = true)
    public void nameChangeOverUpdateAssignmentFlow() {

        try {
            //tc row no 226
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new Assignment().create(226);//Create an assignment
            new Assignment().addQuestions(226, "writeboard", "");
            new LoginUsingLTI().ltiLogin("226");//login as instructor
            new Assignment().assignToStudent(226);
            currentAssignments.updateAssignment_link.get(0).click();//click on update assignment
            Assert.assertEquals(currentAssignments.question_Type.get(1).getText(), "Workboard", "The name of question type is not changed from “Writeboard” to “Workboard” in \"Question type\" label.");

        } catch (Exception e) {
            Assert.fail("Exception in test case nameChangeOverUpdateAssignmentFlow of class InstructorGivenWorkboardFeedbackForStaticAssessment", e);
        }

    }

    @Test(priority = 5, enabled = true)
    public void questionCreationPageOfLsAdaptive() {

        try {
            //tc row no 227
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectcourse();//select course biology
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            manageContent.createPractice.click();//click on create practice
            manageContent.createRegularAssessment.click();//click on regular assessment
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", manageContent.writeBoardQuestion);
            String workboard = manageContent.writeBoardQuestion.getAttribute("title");
            Assert.assertEquals(workboard, "Workboard", "Writeboard question label is not changed to Workboard ");
            manageContent.writeBoardQuestion.click();// click on writeboard question
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("content-label")));
            Assert.assertEquals(manageContent.workBoardPreviewText.getText(), "Workboard will be available on preview", " Text in Default image is not changed from Writeboard will be avalable on preview to Workboard will be avalable on preview");
            new Assignment().create(227);//Create an assignment
            new Assignment().addQuestions(227, "writeboard", "");
            Assert.assertEquals(manageContent.question_Type.getText(), "Workboard", "Type over left corner on Editor should be changed to Writeboard to Workboard");

        } catch (Exception e) {
            Assert.fail("Exception in test case questionCreationPageOfLsAdaptive of class InstructorGivenWorkboardFeedbackForStaticAssessment", e);
        }

    }

    @Test(priority = 6, enabled = true)
    public void questionCreationPageOfLs() {

        try {
            //tc row no 227
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            driver.findElement(By.cssSelector("img[title='Geography: Realms, Regions, and Concepts - Sixteenth Edition']")).click();
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            manageContent.createPractice.click();//click on create practice
            manageContent.createRegularAssessment.click();//click on regular assessment
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", manageContent.writeBoardQuestion);
            String workboard=manageContent.writeBoardQuestion.getAttribute("title");
            Assert.assertEquals(workboard, "Workboard", "Writeboard question label is not changed to Workboard ");
            manageContent.writeBoardQuestion.click();// click on writeboard question
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("content-label")));
            Assert.assertEquals(manageContent.workBoardPreviewText.getText(), "Workboard will be available on preview", " Text in Default image is not changed from Writeboard will be avalable on preview to Workboard will be avalable on preview");
            new Assignment().create(227);//Create an assignment
            new Assignment().addQuestions(227, "writeboard", "");
            Assert.assertEquals(manageContent.question_Type.getText(), "Workboard", "Type over left corner on Editor should be changed to Writeboard to Workboard");

        } catch (Exception e) {
            Assert.fail("Exception in test case questionCreationPageOfLs of class InstructorGivenWorkboardFeedbackForStaticAssessment", e);
        }

    }


    @Test(priority = 7, enabled = true)
    public void searchPageOfLsAdaptive() {

        try {
            //tc row no 231
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new OpenSearchPage().openSearchPage();//open search page
            manageContent.searchField.click();//click on search field
            manageContent.searchField.sendKeys("writeboard");
            manageContent.searchFilterLink.click();//click on search filter link
            manageContent.allQuestionDropDownArrow.get(0).click();//click on all question dropdown arrow
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", manageContent.workBoardQuestionType.get(0));
            Assert.assertEquals(manageContent.workBoardQuestionType.get(0).getText(), "Workboard", "The name of question type is not changed from “Writeboard” to “Workboard”.");
            manageContent.workBoardQuestionType.get(0).click();//click on workboard
            manageContent.go_Button.click();//click on go

            Actions action = new Actions(driver);
            List<WebElement> we2 = driver.findElements(By.xpath("//div[@id = 'question-check-box-div']//label"));
            action.moveToElement(we2.get(0)).build().perform();// Mouse Over on Edit icon on 2nd question
            Thread.sleep(2000);
            action.moveToElement(manageContent.quickReviewExpand.get(0)).click().build().perform();//click on quick preview

            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("cms-content-review-question-type")));
            Assert.assertEquals(manageContent.questionType.getText(),"Workboard","QuestionType: Writeboard Label should be changed to QuestionType: Workboard");
            new Click().clickbylistxpath("//div[@id = 'question-check-box-div']//label", 0);
            manageContent.lunchReviewButton.click();//click on launch review
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("cms-content-review-question-type")));
            Assert.assertEquals(manageContent.questionType.getText(),"Workboard","QuestionType: Writeboard Label should be changed to QuestionType: Workboard");

        } catch (Exception e) {
            Assert.fail("Exception in test case searchPageOfLsAdaptive of class InstructorGivenWorkboardFeedbackForStaticAssessment", e);
        }

    }


    @Test(priority = 8, enabled = true)
    public void searchPageOfLs() {

        try {
            //tc row no 231
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();
            driver.findElement(By.cssSelector("img[title='Geography: Realms, Regions, and Concepts - Sixteenth Edition']")).click();
            new Click().clickByid("content-search-icon");
            new UIElement().waitAndFindElement(By.id("content-search-field"));
            manageContent.searchField.click();//click on search field
            manageContent.searchField.sendKeys("writeboard");
            manageContent.searchFilterLink.click();//click on search filter link
            manageContent.allQuestionDropDownArrow.get(0).click();//click on all question dropdown arrow
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", manageContent.workBoardQuestionType.get(0));
            Assert.assertEquals(manageContent.workBoardQuestionType.get(0).getText(), "Workboard", "The name of question type is not changed from “Writeboard” to “Workboard”.");
            manageContent.workBoardQuestionType.get(0).click();//click on workboard
            manageContent.go_Button.click();//click on go

            Actions action = new Actions(driver);
            List<WebElement> we2 = driver.findElements(By.xpath("//div[@id = 'question-check-box-div']//label"));
            action.moveToElement(we2.get(0)).build().perform();// Mouse Over on Edit icon on 2nd question
            Thread.sleep(2000);
            action.moveToElement(manageContent.quickReviewExpand.get(0)).click().build().perform();//click on quick preview

            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("cms-content-review-question-type")));
            Assert.assertEquals(manageContent.questionType.getText(), "Workboard", "QuestionType: Writeboard Label should be changed to QuestionType: Workboard");
            new Click().clickbylistxpath("//div[@id = 'question-check-box-div']//label", 0);
            manageContent.lunchReviewButton.click();//click on launch review
            Thread.sleep(3000);
            Assert.assertEquals(manageContent.questionType.getText(),"Workboard","QuestionType: Writeboard Label should be changed to QuestionType: Workboard");

        } catch (Exception e) {
            Assert.fail("Exception in test case searchPageOfLs of class InstructorGivenWorkboardFeedbackForStaticAssessment", e);
        }

    }

    @Test(priority = 9, enabled = true)
    public void summaryPage() {

        try {
            //tc row no 238
            Summary summary = PageFactory.initElements(driver, Summary.class);

            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();//select course biology
            Thread.sleep(2000);
            summary.summary.click();//click on summary tab
            summary.difficultyLevelCountDropdown.click();//click on difficulty levelcount dropdown
            summary.questionTypeCountDropdown.click();//click on question type count dropdown
            boolean found=false;
            List<WebElement> writeBoard=summary.question_Type;
            for(WebElement e:writeBoard)
            {
                if(e.getText().contains("Workboard"));
                {
                    found=true;
                }

            }

            Assert.assertEquals(found,true,"The name of question type is not changed from “Writeboard” to “Workboard”.");


        } catch (Exception e) {
            Assert.fail("Exception in test case summaryPage of class InstructorGivenWorkboardFeedbackForStaticAssessment", e);
        }

    }


        @Test(priority = 10, enabled = true)
        public void workBoardQuestionType() {

        try {
            //tc row no 239
            Questions questions = PageFactory.initElements(driver, Questions.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);
            String chapterName = ReadTestData.readDataByTagName("", "newChapterName", Integer.toString(239));
            WebDriverWait wait = new WebDriverWait(driver, 200);
            new Assignment().createChapter(239, 1);//create a chapter
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectcourse();//select a course
            Thread.sleep(2000);
            courseOutline.courseOutline.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@title,'Add New Chapter')]")));
            //  Actions actions=new Actions(driver);
            //publish chapter
            List<WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label node course-chapter-label-unpublished')]"));
            for (WebElement chapters : allChapters) {
                if (chapters.getText().contains(chapterName)) {
                    Locatable hoverItem = (Locatable) chapters;
                    Mouse mouse = ((HasInputDevices) driver).getMouse();
                    mouse.mouseMove(hoverItem.getCoordinates());
                }
            }
            //  actions.moveToElement(driver.findElement(By.xpath("//div[contains(text(),'"+chapterName+"')]"))).build().perform();
            Thread.sleep(500);
            courseOutline.editButtonAtChapter.click();
            Thread.sleep(500);
            courseOutline.checkBoxToPublishChapter.click();//click on publish
            courseOutline.saveButton.click();//click on save
            Thread.sleep(5000);
            courseOutline.saveMyChanges.click();
            Thread.sleep(2000);
            create(239);//Create an assignment

            new LoginUsingLTI().ltiLogin("239");//login as student
            new TOCShow().chaptertree();//click on toc
            new TopicOpen().openLastChapter();
            new DiagnosticTest().startTest(4);
            Assert.assertEquals(questions.plusWorkBoard.isDisplayed(), true, "Workboard tool is not available down to question content.");
            new AttemptQuestion().trueFalse(false, "incorrect", 239);
            Thread.sleep(1000);
            questions.plusWorkBoard.click();//click on plus work board
            driver.switchTo().frame(questions.frame);
            Assert.assertEquals(questions.teacherFeedback.isEnabled(), false, "The feedback icon in the tool bar i enabled");
            driver.switchTo().defaultContent();
            questions.crossIcon.click();//click on cross icon
            new WriteBoard().enterTextInWriteBoardFromCMS("text", 239);
            questions.crossIcon.click();//click on 'x'
            List<WebElement> button = assignments.submitButton;
            for (WebElement ele : button) {
                if (ele.isDisplayed()) {
                    ele.click();//click on finish
                    ele.click();//click on finish

                }
            }
            new UIElement().waitAndFindElement(By.xpath("//div[contains(@class,'report-sidebar-question-card-sectn')]"));
            questions.question_card.get(0).click();//click on question card
            questions.plusWorkBoard.click();//click on view your work link

        } catch (Exception e) {
            Assert.fail("Exception in test case workBoardQuestionType of class InstructorGivenWorkboardFeedbackForStaticAssessment", e);
        }

    }
        @Test(priority = 11, enabled = true)
        public void workBoardQuestionTypeInStudent() {

            try {

                //tc row no 245
                Questions questions = PageFactory.initElements(driver, Questions.class);
                AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
                Assignments assignments = PageFactory.initElements(driver, Assignments.class);
                EngagementReport engagementReport = PageFactory.initElements(driver, EngagementReport.class);
                new Assignment().create(245);//Create an assignment
                new LoginUsingLTI().ltiLogin("245_1");//login as student
                new TOCShow().chaptertree();//click on toc
                new TopicOpen().chapterOpen(2);
                new SelectCourse().selectInvisibleAssignment("IT21_R217_static_Assessment_245");
                Thread.sleep(2000);
                new AttemptQuestion().trueFalse(false, "incorrect", 245);
                new WriteBoard().enterTextInWriteBoardFromCMS("text", 245);
                questions.crossIcon.click();//click on 'x'
                Thread.sleep(2000);
                assignments.button_submitAnswer.click();//click on submit
                new UIElement().waitAndFindElement(By.xpath("//div[@class='ls-static-practice-test-next-button']//input[@title='Finish']"));
                assignments.finish_Button.click();//click on finish*/
                new LoginUsingLTI().ltiLogin("245");//login as instructor
                new Navigator().NavigateTo("Engagement Report");//navigate to engagement report
                engagementReport.assessmentBox.get(1).click();
                new TopicOpen().chapterOpen(2);
                new SelectCourse().selectInvisibleAssignment("IT21_R217_static_Assessment_245");
                new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
                new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
                Thread.sleep(1000);
                questions.plusWorkBoard.click();//click on plus work board
                driver.switchTo().frame(questions.frameInstructor);
                Assert.assertEquals(questions.teacherFeedback.isDisplayed(), false, "The feedback icon in the tool bar i enabled");
                driver.switchTo().defaultContent();
                questions.crossIcon.click();//click on cross icon
                new WriteBoard().drawSquareInWriteBoardInstructorSide(245);//draw square in workBoard
                assignmentResponsesPage.crossIconOnWorkBoard.click();//click on 'x'
                assignmentResponsesPage.getSaveButton().click();//click on save
                Assert.assertEquals(assignmentResponsesPage.getSavedSuccessfullyMessage().getText(), "Saved successfully.", "The typed text is not  saved and Saved Successfully messageis not displayed below the textbox");

            } catch (Exception e) {
                Assert.fail("Exception in test case workBoardQuestionTypeInStudent of class InstructorGivenWorkboardFeedbackForStaticAssessment", e);
            }
        }



    @Test(priority = 12, enabled = true)
    public void workBoardQuestionTypeWithOutWorkBoardDataInStudent() {

        try {

            //tc row no 251
            Questions questions = PageFactory.initElements(driver, Questions.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            EngagementReport engagementReport = PageFactory.initElements(driver, EngagementReport.class);
            new Assignment().create(251);//Create an assignment
            new LoginUsingLTI().ltiLogin("251_1");//login as student
            new TOCShow().chaptertree();//click on toc
            new TopicOpen().chapterOpen(2);
            new SelectCourse().selectInvisibleAssignment("IT21_R217_static_Assessment_251");
            Thread.sleep(2000);
            new AttemptQuestion().trueFalse(false, "incorrect", 251);
            assignments.button_submitAnswer.click();//click on submit
            new UIElement().waitAndFindElement(By.xpath("//div[@id='footer-info-content']//div[@class='ls-static-practice-test-next-button']//input[@title='Finish']"));
            try{
                driver.findElement(By.xpath("//div[@id='footer-info-content']//div[@class='ls-static-practice-test-next-button']//input[@title='Finish']")).click();
            }
            catch (Exception e){

                driver.findElement(By.xpath("//div[@id='footer-info-content']//div[@class='ls-static-practice-test-next-button']//input[@title='Finish']")).click();
            }
            new LoginUsingLTI().ltiLogin("251");//login as instructor
            new Navigator().NavigateTo("Engagement Report");//navigate to engagement report
            engagementReport.assessmentBox.get(1).click();
            new TopicOpen().chapterOpen(2);
            new SelectCourse().selectInvisibleAssignment("IT21_R217_static_Assessment_251");
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(1000);
            questions.plusWorkBoard.click();//click on plus work board
            driver.switchTo().frame(questions.frameInstructor);
            Assert.assertEquals(questions.teacherFeedback.isDisplayed(), false, "The feedback icon in the tool bar i enabled");
            driver.switchTo().defaultContent();
            questions.crossIcon.click();//click on cross icon
            new WriteBoard().drawSquareInWriteBoardInstructorSide(251);//draw square in workBoard
            assignmentResponsesPage.crossIconOnWorkBoard.click();//click on 'x'

        } catch (Exception e) {
            Assert.fail("Exception in test case workBoardQuestionTypeWithOutWorkBoardDataInStudent of class InstructorGivenWorkboardFeedbackForStaticAssessment", e);
        }
    }


    @Test(priority = 13, enabled = true)
    public void studentCompletedAssignmentContainingWorkBoardQuestionWithWorkBoard() {

        try {
            //tc row no 255
            Questions questions = PageFactory.initElements(driver, Questions.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new Assignment().create(255);//Create an assignment
            new LoginUsingLTI().ltiLogin("255_1");//login as student
            new LoginUsingLTI().ltiLogin("255");//login as instructor
            new Assignment().assignToStudent(255);//assign to student
            new LoginUsingLTI().ltiLogin("255_1");//login as student
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssignmentName().click();//click on assignment
            Thread.sleep(6000);
            new AttemptQuestion().trueFalse(false, "incorrect", 255);
            new WriteBoard().enterTextInWriteBoardFromCMS("text", 255);
            questions.crossIcon.click();//click on 'x'
            Thread.sleep(2000);
            new Assignment().submitButtonInQuestionClick();
            new LoginUsingLTI().ltiLogin("255");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current Assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(1000);
            questions.plusWorkBoard.click();//click on plus work board
            driver.switchTo().frame(questions.frameInstructor);
            Assert.assertEquals(questions.teacherFeedback.isDisplayed(), false, "The feedback icon in the tool bar i enabled");
            driver.switchTo().defaultContent();
            questions.crossIcon.click();//click on cross icon
            new WriteBoard().drawSquareInWriteBoardInstructorSide(255);//draw square in workBoard
            assignmentResponsesPage.crossIconOnWorkBoard.click();//click on 'x'
            assignmentResponsesPage.getSaveButton().click();//click on save
            Assert.assertEquals(assignmentResponsesPage.getSavedSuccessfullyMessage().getText(), "Saved successfully.", "The typed text is not  saved and Saved Successfully messageis not displayed below the textbox");


        } catch (Exception e) {
            Assert.fail("Exception in test case studentCompletedAssignmentContainingWorkBoardQuestionWithWorkBoard of class InstructorGivenWorkboardFeedbackForStaticAssessment", e);
        }

    }

    @Test(priority = 13, enabled = true)
    public void studentCompletedAssignmentContainingWorkBoardQuestionWithOutWorkBoard() {

        try {
            //tc row no 260
            Questions questions = PageFactory.initElements(driver, Questions.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new Assignment().create(260);//Create an assignment
            new LoginUsingLTI().ltiLogin("260_1");//login as student
            new LoginUsingLTI().ltiLogin("260");//login as instructor
            new Assignment().assignToStudent(260);//assign to student
            new LoginUsingLTI().ltiLogin("260_1");//login as student
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssignmentName().click();//click on assignment
            Thread.sleep(6000);
            new AttemptQuestion().trueFalse(false, "incorrect", 260);
            Thread.sleep(2000);
            new Assignment().submitButtonInQuestionClick();
            new LoginUsingLTI().ltiLogin("260");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current Assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(1000);
            questions.plusWorkBoard.click();//click on plus work board
            driver.switchTo().frame(questions.frameInstructor);
            Assert.assertEquals(questions.teacherFeedback.isDisplayed(), false, "The feedback icon in the tool bar i enabled");
            driver.switchTo().defaultContent();
            questions.crossIcon.click();//click on cross icon
            new WriteBoard().drawSquareInWriteBoardInstructorSide(260);//draw square in workBoard
            assignmentResponsesPage.crossIconOnWorkBoard.click();//click on 'x'
            assignmentResponsesPage.getSaveButton().click();//click on save
            Assert.assertEquals(assignmentResponsesPage.getSavedSuccessfullyMessage().getText(), "Saved successfully.", "The typed text is not  saved and Saved Successfully messageis not displayed below the textbox");


        } catch (Exception e) {
            Assert.fail("Exception in test case studentCompletedAssignmentContainingWorkBoardQuestionWithOutWorkBoard of class InstructorGivenWorkboardFeedbackForStaticAssessment", e);
        }

    }

    @Test(priority = 14, enabled = true)
    public void instructorGivenWorkBoardFeedbackForStaticAssessmentThroughEngagement() {

        try {
            //tc row no 265
            Questions questions = PageFactory.initElements(driver, Questions.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            EngagementReport engagementReport = PageFactory.initElements(driver, EngagementReport.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            new Assignment().create(265);//Create an assignment
            new Assignment().addQuestions(265, "writeboard", "");
            new LoginUsingLTI().ltiLogin("265_1");//login as student
            new TOCShow().chaptertree();//click on toc
            new TopicOpen().chapterOpen(2);
            new SelectCourse().selectInvisibleAssignment("IT21_R217_static_Assessment_265");
            Thread.sleep(5000);
            new AttemptQuestion().trueFalse(false, "incorrect", 265);
            assignments.button_submitAnswer.click();//click on submit
            new Assignment().nextButtonInQuestionClick();
            new AttemptQuestion().writeBoard(true, "correct", 265);
            assignments.button_submitAnswer.click();//click on submit
            Thread.sleep(3000);
            new UIElement().waitAndFindElement(By.xpath("//div[@id='footer-info-content']//div[@class='ls-static-practice-test-next-button']//input[@title='Finish']"));
            driver.findElement(By.xpath("//div[@id='footer-info-content']//div[@class='ls-static-practice-test-next-button']//input[@title='Finish']")).click();

          /*  List<WebElement> button = assignments.submitButton;
            for (WebElement ele : button) {
                if (ele.isDisplayed()) {
                    ele.click();//click on finish

                }
            }*/

            new LoginUsingLTI().ltiLogin("265");//login as instructor
            new Navigator().NavigateTo("Engagement Report");//navigate to engagement report
            engagementReport.assessmentBox.get(1).click();
            new TopicOpen().chapterOpen(2);
            new SelectCourse().selectInvisibleAssignment("IT21_R217_static_Assessment_265");
            new MouseHover().mouserhoverbywebelement(driver.findElement(By.xpath("(//div[@class='idb-gradebook-content-coloumn'])[2]"))); //mouse over view response link
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(1000);
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'whiteBoard_iframe_kedukWBTEACHER__whiteBoard')]")));
            new Click().clickByid("text-btn");//click on text button
            String widthvalue= driver.findElement(By.xpath("//canvas")).getAttribute("width");
            driver.findElement(By.cssSelector("canvas[width='"+widthvalue+"']")).click();
            Thread.sleep(3000);
            new TextSend().textsendbyid("text", "textEditor");
            Thread.sleep(2000);
            driver.findElement(By.xpath("(//div[@class='ui-dialog-buttonset'])[2]//button[1]")).click();
            Thread.sleep(2000);
            driver.switchTo().defaultContent();//switch to main window
            assignmentResponsesPage.getSaveButton().click();//click on save
            Thread.sleep(2000);

            new LoginUsingLTI().ltiLogin("265_1");//login as student
            dashboard.assessmentLink.get(0).click();//click on assessment
            new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'report-sidebar-question-card-sectn')]")));
            questions.question_card.get(0).click();//click on 1st card
            Assert.assertEquals(questions.frame.isDisplayed(), true, " The workboard layer is not open by default.");
            driver.switchTo().frame(questions.frame);
            new UIElement().waitAndFindElement(By.id("teacher-btn"));
            Assert.assertEquals(questions.teacherFeedback.isEnabled(), true, "The feedback icon in the tool bar is not enabled.");
            questions.teacherFeedback.click();//click on instructor feedback icon
            questions.teacherFeedback.click();//click on instructor feedback icon


        } catch (Exception e) {
            Assert.fail("Exception in test case instructorGivenWorkBoardFeedbackForStaticAssessmentThroughEngagement of class InstructorGivenWorkboardFeedbackForStaticAssessment", e);
        }

    }


    @Test(priority = 15, enabled = true)
    public void studentAndInstructorMustNotHaveAddedAnyWorkBoardData() {

        try {
            //tc row no 270
            Questions questions = PageFactory.initElements(driver, Questions.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            new Assignment().create(270);//Create an assignment
            new LoginUsingLTI().ltiLogin("270");//login as student
            new TOCShow().chaptertree();//click on toc
            new TopicOpen().chapterOpen(2);
            new SelectCourse().selectInvisibleAssignment("IT21_R217_static_Assessment_270");
            Thread.sleep(5000);
            new AttemptQuestion().trueFalse(false, "incorrect", 270);
            assignments.button_submitAnswer.click();//click on submit
            new UIElement().waitAndFindElement(By.xpath("//div[@id='footer-info-content']//div[@class='ls-static-practice-test-next-button']//input[@title='Finish']"));
            try{
                driver.findElement(By.xpath("//div[@id='footer-info-content']//div[@class='ls-static-practice-test-next-button']//input[@title='Finish']")).click();
            }
            catch (Exception e){

                driver.findElement(By.xpath("//div[@id='footer-info-content']//div[@class='ls-static-practice-test-next-button']//input[@title='Finish']")).click();
            }

            new LoginUsingLTI().ltiLogin("270");//login as student
            dashboard.assessmentLink.get(0).click();//click on assessment
            new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'report-sidebar-question-card-sectn')]")));
            questions.question_card.get(0).click();//click on 1st card
            boolean workBoard = new BooleanValue().presenceOfElement(214, By.id("show-your-work-label"));
            Assert.assertEquals(workBoard,false,"view your work button is available");



        } catch (Exception e) {
            Assert.fail("Exception in test case studentAndInstructorMustNotHaveAddedAnyWorkBoardData of class InstructorGivenWorkboardFeedbackForStaticAssessment", e);
        }

    }



    @Test(priority = 16, enabled = true)
    public void instructorMustNotHaveGivenFeedbackForParticularQuestion() {

        try {
            //tc row no 271
            Questions questions = PageFactory.initElements(driver, Questions.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            new Assignment().create(271);//Create an assignment
            new LoginUsingLTI().ltiLogin("271");//login as student
            new TOCShow().chaptertree();//click on toc
            new TopicOpen().chapterOpen(2);
            new SelectCourse().selectInvisibleAssignment("IT21_R217_static_Assessment_271");
            Thread.sleep(5000);
            new AttemptQuestion().trueFalse(false, "incorrect", 271);
            new WriteBoard().enterTextInWriteBoardFromCMS("text", 271);
            questions.crossIcon.click();//click on 'x'
            assignments.button_submitAnswer.click();//click on submit
            new UIElement().waitAndFindElement(By.xpath("//div[@id='footer-info-content']//div[@class='ls-static-practice-test-next-button']//input[@title='Finish']"));
            driver.findElement(By.xpath("//div[@id='footer-info-content']//div[@class='ls-static-practice-test-next-button']//input[@title='Finish']")).click();

            new LoginUsingLTI().ltiLogin("271");//login as student
            dashboard.assessmentLink.get(1).click();//click on assessment
            new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[contains(@class,'report-sidebar-question-card-sectn')]")));
            questions.question_card.get(0).click();//click on 1st card
            Assert.assertEquals(new BooleanValue().presenceOfElement(271,By.className("whiteboard-feedback-teacher")),false,"Instructor feedback icon is present");
            questions.plusWorkBoard.click();//click on plus work board
            driver.switchTo().frame(questions.frame);
            Assert.assertEquals(questions.teacherFeedback.isEnabled(), false, "The feedback icon in the tool bar i enabled");


        } catch (Exception e) {
            Assert.fail("Exception in test case instructorMustNotHaveGivenFeedbackForParticularQuestion of class InstructorGivenWorkboardFeedbackForStaticAssessment", e);
        }

    }


    @Test(priority = 17, enabled = true)
    public void instructorGivenWorkBoardFeedbackForAssignment() {

        try {
            //tc row no 274
            Questions questions = PageFactory.initElements(driver, Questions.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new Assignment().create(274);//Create an assignment
            new Assignment().addQuestions(274, "writeboard", "");
            new LoginUsingLTI().ltiLogin("274_1");//login as student
            new LoginUsingLTI().ltiLogin("274");//login as instructor
            new Assignment().assignToStudent(274);//assign to student
            new LoginUsingLTI().ltiLogin("274_1");//login as student
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssignmentName().click();//click on assignment
            Thread.sleep(6000);
            new AttemptQuestion().trueFalse(true, "correct", 274);
            new Assignment().nextButtonInQuestionClick();//click on next button
            new AttemptQuestion().writeBoard(true, "correct", 274);
            Thread.sleep(2000);
            assignments.getFinishAssignment().click();//click on finish
            driver.findElement(By.className("next-or-submit-link")).click();
            new LoginUsingLTI().ltiLogin("274");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current Assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(driver.findElement(By.xpath("(//div[@class='idb-gradebook-content-coloumn'])[2]"))); //mouse over view response link
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(2000);
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@id,'whiteBoard_iframe_kedukWBTEACHER__whiteBoard')]")));
            new Click().clickByid("text-btn");//click on text button
            String widthvalue= driver.findElement(By.xpath("//canvas")).getAttribute("width");
            driver.findElement(By.cssSelector("canvas[width='"+widthvalue+"']")).click();
            Thread.sleep(3000);
            new TextSend().textsendbyid("text", "textEditor");
            driver.findElement(By.xpath("(//div[@class='ui-dialog-buttonset'])[2]//button[1]")).click();
            Thread.sleep(2000);
            driver.switchTo().defaultContent();//switch to main window
            assignmentResponsesPage.getSaveButton().click();//click on save
            Thread.sleep(2000);

            new LoginUsingLTI().ltiLogin("274_1");//login as student
            dashboard.assessmentLink.get(0).click();//click on assessment
            Thread.sleep(2000);
            questions.question_card.get(1).click();//click on 1st card
            new UIElement().waitAndFindElement(By.xpath("//iframe[starts-with(@id,'whiteBoard_iframe_kedukWBSTUDENT')]"));
            Assert.assertEquals(questions.frame.isDisplayed(),true, " The workboard layer is not open by default.");
            driver.switchTo().frame(questions.frame);
            new UIElement().waitAndFindElement(By.id("teacher-btn"));
            Assert.assertEquals(questions.teacherFeedback.isEnabled(),true, "The feedback icon in the tool bar is not enabled.");
            questions.teacherFeedback.click();//click on instructor feedback icon
            questions.teacherFeedback.click();//click on instructor feedback icon
        } catch (Exception e) {
            Assert.fail("Exception in test case instructorGivenWorkBoardFeedbackForAssignment of class InstructorGivenWorkboardFeedbackForStaticAssessment", e);
        }

    }

    @Test(priority = 18, enabled = true)
    public void aWorkBoardEnabledQuestionMustBeAvailableAsAPartOfAnyAssessments() {

        try {
            //tc row no 279
            Questions questions = PageFactory.initElements(driver, Questions.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new Assignment().create(279);//Create an assignment
            new LoginUsingLTI().ltiLogin("279_1");//login as student
            new LoginUsingLTI().ltiLogin("279");//login as instructor
            new Assignment().assignToStudent(279);//assign to student
            new LoginUsingLTI().ltiLogin("279_1");//login as student
            new TOCShow().chaptertree();//click on toc
            new TopicOpen().chapterOpen(2);
            new SelectCourse().selectInvisibleAssignment("IT21_R217_static_Assessment_279");
            questions.plusWorkBoard.click();//click on plus work board
            questions.tab.get(2).click();
            questions.tab.get(3).click();
            Assert.assertEquals(questions.frame.isDisplayed(), true, " The overlay not in open state after switching tabs.");
            questions.crossIcon.click();//click on 'x'
            questions.tab.get(2).click();
            questions.tab.get(3).click();
            Assert.assertEquals(questions.frame.isDisplayed(),false, " The overlay in open state after switching tabs.");
            new AttemptQuestion().trueFalse(false, "incorrect", 279);
            new WriteBoard().enterTextInWriteBoardFromCMS("text", 279);
            questions.crossIcon.click();//click on 'x'
            Thread.sleep(1000);
            new Assignment().submitButtonInQuestionClick();
            Thread.sleep(2000);
            questions.question_card.get(0).click();//click on 1st card
            questions.plusWorkBoard.click();//click on plus work board
            questions.tab.get(2).click();
            questions.tab.get(3).click();
            Assert.assertEquals(questions.frame.isDisplayed(), true, " The overlay not in open state after switching tabs.");
            questions.crossIcon.click();//click on 'x'
            questions.tab.get(2).click();
            questions.tab.get(3).click();
            Assert.assertEquals(questions.frame.isDisplayed(),false, " The overlay in open state after switching tabs.");


        } catch (Exception e) {
            Assert.fail("Exception in test case aWorkBoardEnabledQuestionMustBeAvailableAsAPartOfAnyAssessments of class InstructorGivenWorkboardFeedbackForStaticAssessment", e);
        }

    }



    @Test(priority = 19, enabled = true)
    public void anAssignmentWithWorkboardEnabledQuestionMustBeCompletedByStudent() {

        try {
            //tc row no 284
            Questions questions = PageFactory.initElements(driver, Questions.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new Assignment().create(284);//Create an assignment
            new LoginUsingLTI().ltiLogin("284_1");//login as student
            new LoginUsingLTI().ltiLogin("284");//login as instructor
            new Assignment().assignToStudent(284);//assign to student
            new LoginUsingLTI().ltiLogin("284_1");//login as student
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssignmentName().click();//click on assignment
            Thread.sleep(6000);
            new AttemptQuestion().trueFalse(false, "incorrect", 284);
            new WriteBoard().enterTextInWriteBoardFromCMS("text", 284);
            questions.crossIcon.click();//click on 'x'
            Thread.sleep(1000);
            new Assignment().submitButtonInQuestionClick();
            Thread.sleep(2000);
            new LoginUsingLTI().ltiLogin("284");//login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current Assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(1000);
            questions.plusWorkBoard.click();
            currentAssignments.tab_Title.get(1).click();
            currentAssignments.tab_Title.get(2).click();
            Assert.assertEquals(questions.frameInstructor.isDisplayed(), true, " The overlay in open state after switching tabs.");
            questions.crossIcon.click();
            currentAssignments.tab_Title.get(1).click();
            currentAssignments.tab_Title.get(2).click();
            Assert.assertEquals(questions.frameInstructor.isDisplayed(), false, " The overlay in open state after switching tabs.");


        } catch (Exception e) {
            Assert.fail("Exception in test case anAssignmentWithWorkboardEnabledQuestionMustBeCompletedByStudent of class InstructorGivenWorkboardFeedbackForStaticAssessment", e);
        }

    }


    @Test(priority = 20, enabled = false)
    public void assignmentsFlowForGradableAssignmentsWithDifferentPolicies() {

        try {
            //tc row no 287
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "287");

            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new Assignment().create(287);//Create an assignment
            new LoginUsingLTI().ltiLogin("287_1");//login as student
            new LoginUsingLTI().ltiLogin("287");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");//till save policy*/
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(287);//assign to student
            new LoginUsingLTI().ltiLogin("287_1");//login as student
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssignmentName().click();//click on assignment
            Thread.sleep(6000);

        } catch (Exception e) {
            Assert.fail("Exception in test case assignmentsFlowForGradableAssignmentsWithDifferentPolicies of class InstructorGivenWorkboardFeedbackForStaticAssessment", e);
        }

    }


    public void create(int dataIndex) {
        try {
            String course = "";
            String overrideDefaultLogin = ReadTestData.readDataByTagName("", "overrideDefaultLogin", Integer.toString(dataIndex));
            String course_type = ReadTestData.readDataByTagName("", "course_type", Integer.toString(dataIndex));
            String course_name = ReadTestData.readDataByTagName("", "course_name", Integer.toString(dataIndex));
            String practice_type = ReadTestData.readDataByTagName("", "practice_type", Integer.toString(dataIndex));

            String overrideDefaultQuestionCreate = ReadTestData.readDataByTagName("", "overrideDefaultQuestionCreate", Integer.toString(dataIndex));

            course = course_name;
            if (course_name == null) {
                if (course_type == null || course_type.equals(""))
                    course = Config.course;

                else if (course_type.equals("ls"))
                    course = Config.lscourse;
            }
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            String questiontype = ReadTestData.readDataByTagName("", "questiontype", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            String courselevel = ReadTestData.readDataByTagName("", "courselevel", Integer.toString(dataIndex));
            String reservforassignment = ReadTestData.readDataByTagName("", "reservforassignment", Integer.toString(dataIndex));

            if (overrideDefaultLogin == null)
                new DirectLogin().CMSLogin();
            else {
                String role = ReadTestData.readDataByTagName("", "role", Integer.toString(dataIndex));
                new DBConnect().Connect();
                ResultSet rs = DBConnect.st.executeQuery("select id,username from t_user where id in (SELECT user_id FROM t_course_user where course_id = 253) and id in(select user_id from t_user_role where type = '" + role + "') limit 1,1;");
                String username = "";
                while (rs.next()) {
                    username = rs.getString("username");
                }
                new DirectLogin().directLoginWithCreditial(username, "snapwiz", 0);
                new Click().clickbyxpath("(//select[@name='product-drop-down']/following-sibling::div/a)[1]");
                new Click().clickbylinkText("Learning Space + Adaptive Component");
                DBConnect.conn.close();
            }

            String title = driver.getTitle();
            if (title.equals("Course Content Details")) {
                driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();

                if (chapterName == null) {
                    new Click().clickBycssselector("div.course-chapter-label.node");
                } else if (courselevel != null) {
                    new Click().clickBycssselector("div[class='course-label node']");
                } else {
                    List<WebElement> allChapterswithSameName = driver.findElements(By.xpath("//div[contains(@title,'"+chapterName+"')]"));
                    driver.findElements(By.xpath("//div[contains(@title, '"+chapterName+"')]")).get(allChapterswithSameName.size()-1).click();


                }
                driver.findElement(By.cssSelector("div.create-practice")).click();

                if (practice_type == null) {
                    new Click().clickByclassname("create-regular-assessment-popup-item"); //click on the create regular assessment
                } else {
                    new Click().clickByclassname("create-file-assessment-popup-item");//click on the create file assessment
                }

                new Click().clickbylinkText("Adaptive Component Diagnostic");
                new Click().clickbylinkText(questiontype);
                int popup = 1;
                try {
                    WebDriverWait wait = new WebDriverWait(driver, 3);
                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='cms-notification-message-body']")));

                } catch (Exception e) {
                    popup = 0;
                }
                if (popup == 0) {
                    driver.findElement(By.id("assessmentName")).click();
                    driver.findElement(By.id("assessmentName")).clear();
                    driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
                    driver.findElement(By.id("questionSetName")).clear();
                    driver.findElement(By.id("questionSetName")).sendKeys(questionset);

                    if (reservforassignment == null) {
                        driver.findElement(By.xpath("//*[@id='instructor-only-check-box-div']/label")).click(); //checking 'Enable for intructor only'
                    }

                    if(overrideDefaultQuestionCreate ==null){
                        new QuestionCreate().trueFalseQuestions(dataIndex);
                    }else{
                        if(overrideDefaultQuestionCreate.equalsIgnoreCase("essay")){
                            new QuestionCreate().essay(dataIndex);
                        } else if(overrideDefaultQuestionCreate.equalsIgnoreCase("multipart")){
                            new QuestionCreate().multiPartQuestion(dataIndex);
                        }
                    }
                }

            } else {
                Assert.fail("CMS login failed");
            }
            if (!(Config.browser.equalsIgnoreCase("firefox"))) {
                driver.quit();
                startDriver("firefox");
            }
        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper create in class AssignmentCreate", e);
        }
    }





    public void addQuestions(int dataIndex, String questionType, String assignmentname) {
        try {
            String course = "";
            assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String overrideDefaultLogin = ReadTestData.readDataByTagName("", "overrideDefaultLogin", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            String courselevel = ReadTestData.readDataByTagName("", "courselevel", Integer.toString(dataIndex));
            String course_type = ReadTestData.readDataByTagName("", "course_type", Integer.toString(dataIndex));
            String course_name = ReadTestData.readDataByTagName("", "course_name", Integer.toString(dataIndex));
            String subSection_level = ReadTestData.readDataByTagName("", "subSection_level", Integer.toString(dataIndex));
            String chapterIndex = ReadTestData.readDataByTagName("", "chapterIndex", Integer.toString(dataIndex));
            String topicIndex = ReadTestData.readDataByTagName("", "topicIndex", Integer.toString(dataIndex));

            course = course_name;

            if (course_name == null) {
                if (course_type == null || course_type.equals(""))
                    course = Config.course;

                else if (course_type.equals("ls"))
                    course = Config.lscourse;
            }
            if (overrideDefaultLogin == null)
                new DirectLogin().CMSLogin();
            else {
                String role = ReadTestData.readDataByTagName("", "role", Integer.toString(dataIndex));
                new DBConnect().Connect();
                ResultSet rs = DBConnect.st.executeQuery("select id,username from t_user where id in (SELECT user_id FROM t_course_user where course_id = 253) and id in(select user_id from t_user_role where type = '" + role + "') limit 1,1;");
                String username = "";
                while (rs.next()) {
                    username = rs.getString("username");
                }
                new DirectLogin().directLoginWithCreditial(username, "snapwiz", dataIndex);
                new Click().clickbyxpath("(//select[@name='product-drop-down']/following-sibling::div/a)[1]");
                new Click().clickbylinkText("Learning Space + Adaptive Component");
            }
            String title = driver.getTitle();
            if (title.equals("Course Content Details")) {
                driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                if (chapterName == null) {
                    driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
                } else if (courselevel != null) {
                    new Click().clickBycssselector("div[class='course-label node']");
                } else {
                    List<WebElement> allChapterswithSameName = driver.findElements(By.xpath("//div[contains(@title,'" + chapterName + "')]"));
                    driver.findElements(By.xpath("//div[contains(@title, '" + chapterName + "')]")).get(allChapterswithSameName.size() - 1).click();
                }
                Thread.sleep(3000);
                if (subSection_level != null) {
                    new SelectCourse().expandChapterTreeByIndex(dataIndex, Integer.parseInt(chapterIndex));
                    new SelectCourse().selectTopicByIndex(dataIndex, Integer.parseInt(topicIndex));

                }
                boolean assignmentExists = false;
                List<WebElement> elements = driver.findElements(By.xpath("//div[@title='" + assignmentname + "']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elements.get(elements.size()-1));
                Thread.sleep(5000);
                try {
                    new Click().clickByElement(elements.get(elements.size()-1));
                    //elements.get(elements.size() - 1).click();
                    assignmentExists = true;
                }
                catch (Exception e) {

                }

                if (assignmentExists == true)
                    addQuestionLink();
                Thread.sleep(2000);
                if (assignmentExists == true) {
                    if (questionType.equalsIgnoreCase("all")) {
                        new QuestionCreate().trueFalseQuestions(dataIndex);
                        addQuestionLink();
                        new QuestionCreate().multipleChoice(dataIndex);
                        addQuestionLink();
                        new QuestionCreate().multipleSelection(dataIndex);
                        addQuestionLink();
                        new QuestionCreate().essay(dataIndex);
                        addQuestionLink();
                        new QuestionCreate().writeBoard(dataIndex);
                        addQuestionLink();
                        new QuestionCreate().audio(dataIndex);
                    } else if (questionType.equals("truefalse") || questionType.equals("qtn-type-true-false-img"))
                        new QuestionCreate().trueFalseQuestions(dataIndex);

                    else if (questionType.equals("multiplechoice") || questionType.equals("qtn-multiple-choice-img"))
                        new QuestionCreate().multipleChoice(dataIndex);

                    else if (questionType.equals("multipleselection") || questionType.equals("qtn-multiple-selection-img"))
                        new QuestionCreate().multipleSelection(dataIndex);

                    else if (questionType.equals("essay") || questionType.equals("qtn-essay-type"))
                        new QuestionCreate().essay(dataIndex);

                    else if (questionType.equals("writeboard") || questionType.equals("qtn-writeboard-type-new"))
                        new QuestionCreate().writeBoard(dataIndex);

                    else if (questionType.equals("audio") || questionType.equals("qtn-audio-type"))
                        new QuestionCreate().audio(dataIndex);

                }

            } //if condition  ends here
            if (!(Config.browser.equalsIgnoreCase("firefox"))) {
                driver.quit();
                startDriver("firefox");
            }
        } catch (Exception e) {

            Assert.fail("Exception in create in Apphelper addQuestions in class AssignmentCreate", e);
        }
    }

    public void addQuestionLink() {
        try {
            Thread.sleep(2000);
            new Click().clickByid("questionOptions");
            Thread.sleep(2000);
            new Click().clickByid("addQuestion");
        } catch (Exception e) {
            Assert.fail("Exception while clicking on add questions link in CMS", e);
        }

    }
}