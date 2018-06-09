package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R1824;


import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Gradebook.Gradebook;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.MyActivity.MyActivity;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.DiagnosticTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions;

import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.EngagementReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.PerformanceSummary;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.ProficiencyReport;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
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
 * Created by Dharaneesh T Gowda on 28-01-2015.
 */
public class QuestionVersions extends Driver {

    String actual = null;
    String expected = null;

    @Test(priority = 1, enabled = true)
    public void student1HasSubmittedQsForDiagnosticAssessment() {
        String diagnosticTitleName = "Diagnostic - Ch 2: The Chemical Foundation of Life";
        try {
            String dataIndex = "243";
            startDriver("firefox");

            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            Questions questions = PageFactory.initElements(driver, Questions.class);
            DiagnosticTab diagnosticTab = PageFactory.initElements(driver, DiagnosticTab.class);
            ProficiencyReport proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);
            MyActivity myActivity = PageFactory.initElements(driver, MyActivity.class);


            new LoginUsingLTI().ltiLogin(dataIndex);//log in as Student 1
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(1); //open first chapter
            new DiagnosticTest().startTestBasedOnTitle(4, diagnosticTitleName);
            new DiagnosticTest().attemptAllCorrect(4, false, false);
            postDiscussion4LastQuestionInQuestionCard("Hi, It's time to discuss");
            String questionText = createVersionsForQuestion();
            publishQuestion();

            /*Row N0 - 243 : "1. Login as a student1
            2. Go to eTextbook
            3. Click on the Diagnostic assessment of the same chapter which is already attempted by student (one of the question has new version)"*/
            //String questionText = "A hydrogen atom consists of";
            new LoginUsingLTI().ltiLogin(dataIndex);//log in as Student 1
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(1); //open first chapter
            new DiagnosticTest().startTestBasedOnTitle(4, diagnosticTitleName);
            Thread.sleep(3000);

            //Expected - 1 : On the Performance Summary page, the older version of question should be shown based on original entry of question including Question Bar and Question Card which can be correct/incorrect or skipped


            //Row No : 244 - 4. Click on the question card/question bar of older version question
            //Expected - 1. Notification Message should be shown on the top of the question as "This question is outdated. Please contact your instructor for any score related issue"

            WebElement element = driver.findElements(By.className("question-card-question-content")).get(driver.findElements(By.className("question-card-question-content")).size() - 1);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(3000);
            new Click().clickByElement(element);
            Thread.sleep(7000);
            actual = questions.getMsg_Notification().getText();
            expected = "This question is outdated. Please contact your instructor for any score related issues.";
            Assert.assertEquals(actual, expected, "Notification Message should be shown on the top of the question as \"This question is outdated. Please contact your instructor for any score related issue\"");


            //Expected - 2 : The “Report Content Issue” icon should be hidden for a outdated question.
            if (driver.findElement(By.cssSelector("div[title = 'Report Content Errors']")).isDisplayed()) {
                Assert.fail("The “Report Content Issue” icon should be hidden for a outdated question, but it is not hidden in this case");
            }


            /**********************************************************************************************************************************/
            /*Row No  - 246 : "5. Navigate to Course Stream
            6. Click on the jump-out icon of posted discussion of older version question"*/
            new Navigator().NavigateTo("Course Stream");
            Thread.sleep(3000);
            new Click().clickBycssselector("span[class='ls-jumpout-icon-link ls-jumpout-icon']");
            Thread.sleep(5000);

            //Expected - 1 : Student should get navigated to older version of question
            actual = questions.getLabel_QuestionText().getText().trim();
            expected = questionText;
            Assert.assertEquals(actual, expected, "Student is not navigated to older version of question");

            //Expected - 2 : Notification Message should be shown on the top of the question as "This question is outdated. Please contact your instructor for any score related issue"
            actual = questions.getMsg_Notification().getText();
            expected = "This question is outdated. Please contact your instructor for any score related issues.";
            Assert.assertEquals(actual, expected, "Notification Message should be shown on the top of the question as \"This question is outdated. Please contact your instructor for any score related issue\"");

            //Expected - 3 : The “Report Content Issue” icon should be hidden for a outdated question.
            if (driver.findElements(By.cssSelector("div[title = 'Report Content Errors']")).size() != 0) {
                Assert.fail("The “Report Content Issue” icon should be hidden for a outdated question, but it is not hidden in this case");
            }
            /**********************************************************************************************************************************/



            /*Row No - 249 : "7. Navigate to My Activities
            8. Click on the discussion card of older version question"*/
            //Expected -1 : Clicking on a discussion card of older version of question should navigate the student to that question

            new Navigator().NavigateTo("My Activity");
            myActivity.getDiscussionLinkIcon().click();
            Thread.sleep(1000);
            myActivity.getDiscussionLinkIcon().click();
            Thread.sleep(1000);
            actual = questions.getLabel_QuestionText().getText().trim();
            expected = questionText;
            Assert.assertEquals(actual, expected, "Student is not navigated to older version of question");

            //Expected - 2 : Notification Message should be shown on the top of the question as "This question is outdated. Please contact your instructor for any score related issue"
            actual = questions.getMsg_Notification().getText();
            expected = "This question is outdated. Please contact your instructor for any score related issues.";
            Assert.assertEquals(actual, expected, "Notification Message should be shown on the top of the question as \"This question is outdated. Please contact your instructor for any score related issue\"");

            //Expected - 3 : The “Report Content Issue” icon should be hidden for a outdated question.
            if (driver.findElements(By.cssSelector("div[title = 'Report Content Errors']")).size() != 0) {
                Assert.fail("The “Report Content Issue” icon should be hidden for a outdated question, but it is not hidden in this case");
            }


            /**********************************************************************************************************************************/
            //Row No - 252 : 9. Navigate to Proficiency Report Page
            //Expected - Course proficiency summary page should show the older version of question on the right card and should be shown based on original entry of question which can be correct/incorrect or skipped
            new Navigator().NavigateTo("Proficiency Report");
            Thread.sleep(3000);
            showOlderQuestionOnRightCardAndOriginalEntry(questionText);
            /**********************************************************************************************************************************/
            //Row No - 253 : 10. Click on the Chapter bar
            //Expected - Chapter proficiency summary page should show the older version of question on the right card and should be shown based on original entry of question which can be correct/incorrect or skipped
            proficiencyReport.getBarChart().click();// Click on the chapter bar
            Thread.sleep(2000);
            showOlderQuestionOnRightCardAndOriginalEntry(questionText);
            /**********************************************************************************************************************************/
            //Row No - 254 : 11. Click on the TLO associated to deactivated question
            //Expected  - Older version question should be shown based on original entry of question including Question Bar and Question Card which can be correct/incorrect or skipped
            Thread.sleep(5000);
            proficiencyReport.getBarChartElementsList().get(1).click();//Click on 3rd bar
            Thread.sleep(5000);
            String backgroundColor = proficiencyReport.getBarChartElementsList().get(proficiencyReport.getBarChartElementsList().size() - 1).getCssValue("background-color");
            System.out.println("backgroundColor : " + backgroundColor);
            showOlderQuestionOnRightCardAndOriginalEntry(questionText);
            //**********************************************************************************************************************************//*
            //Row No - 255 : 12. Click on the question card/question bar of deactivated question
            //Expected - 1 : Notification Message should be shown on the top of the question as "This question is outdated. Please contact your instructor for any score related issue"
            clickOnLastQuestionInQuestionCard();
            validateOutDatedQuestion(questionText);


            //Expected - 2 : In About tab, “student got it correct” should show 0%
            actual = proficiencyReport.getLabelValue_StudentGotItCorrect().getText();
            expected = "%";
            if (!(actual.contains(expected))) {
                Assert.fail("In About tab, “student got it correct” is not showing 0%");
            }
            //Assert.assertEquals(actual,expected,"In About tab, “student got it correct” is not showing 0%");

            //Expected - 3 : The “Report Content Issue” icon should be hidden for a outdated question.
            if (driver.findElement(By.cssSelector("div[title = 'Report Content Errors']")).isDisplayed()) {
                Assert.fail("The “Report Content Issue” icon should be hidden for a outdated question, but it is not hidden in this case");
            }




            /*Row No - 258 : "1. Login as a student2
            2. Go to eTextbook
            3. Click on the Diagnostic assessment of the same chapter in which new version of question is created
            4. Attempt each question of Diagnostic Assessment"*/
            //Expected 1- Student should get the newer version of the question

            dataIndex = "258";
            questionText = questionText + "V1";
            new LoginUsingLTI().ltiLogin(dataIndex);
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(1); //open first chapter
            new DiagnosticTest().startTestBasedOnTitle(4, diagnosticTitleName);
            boolean isNewVersionQuestionAvailable = false;
            Thread.sleep(7000);
            if (driver.findElements(By.cssSelector("input[type = 'button']")).size() != 0) {
                System.out.println("Element Present");
            }
            while (driver.findElements(By.cssSelector("input[type = 'button']")).size() != 0) {
                Thread.sleep(1000);
                String questionTitle = questions.getLabel_QuestionText().getText();
                if (questionTitle.equals(questionText)) {
                    isNewVersionQuestionAvailable = true;
                    break;
                }
                driver.findElement(By.cssSelector("input[type = 'button']")).click();
            }
            if (isNewVersionQuestionAvailable == false) {
                Assert.fail("Student should get the newer version of the question");
            }


            dataIndex = "260";
            new LoginUsingLTI().ltiLogin(dataIndex);
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(1); //open first chapter
            new DiagnosticTest().startTestBasedOnTitle(4, diagnosticTitleName);

            //Row No - 260 : 5. Finish the Diagnostic assessment
            //Expected 3- On the Performance Summary page, the newer version question should be be shown including Question Bar and Question Card
            new DiagnosticTest().attemptAllCorrect(4, false, false);
            String newVersionQuestionText = driver.findElement(By.className("question-card-question-content")).getText().trim();
            Assert.assertEquals(newVersionQuestionText, questionText, "On the Performance Summary page, the newer version question is not showing in Question Card");


        } catch (Exception e) {
            Assert.fail("Exception in the test 'student1HasSubmittedQsForDiagnosticAssessment' in the class 'QuestionVersions'", e);
        }
    }


    @Test(priority = 2, enabled = true)
    public void student1HasSubmittedQsForPracticeAssessment() {
        try {

            String dataIndex = "262";
            String staticTestName = "Personalized Practice - Ch 2: The Chemical Foundation of Life";
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            Questions questions = PageFactory.initElements(driver, Questions.class);
            DiagnosticTab diagnosticTab = PageFactory.initElements(driver, DiagnosticTab.class);
            ProficiencyReport proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);
            MyActivity myActivity = PageFactory.initElements(driver, MyActivity.class);

            new LoginUsingLTI().ltiLogin(dataIndex);
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(1); //open first chapter
            new DiagnosticTest().startTestBasedOnTitle(4, "Diagnostic - Ch 2: The Chemical Foundation of Life");
            new DiagnosticTest().attemptAllCorrect(4, false, false);

            new LoginUsingLTI().ltiLogin(dataIndex);
            new Navigator().NavigateTo("e-Textbook");
            System.out.println("1");
            new TopicOpen().chapterOpen(1); //open first chapter
            System.out.println("2");
            new PracticeTest().startPracticeTest(staticTestName);//start practice test
            System.out.println("3");
            int count = 0;
            for (int i = 0; i < 12; i++) {
                new PracticeTest().AttemptCorrectAnswer(0,"3");
                count++;
            }
            new PracticeTest().quitThePracticeTest();
            Thread.sleep(5000);
            String questionText = questions.getLabel_QuestionCardElementsList().get(questions.getLabel_QuestionCardElementsList().size() - 1).getAttribute("title");
            List<WebElement> contentList = driver.findElements(By.cssSelector("div[class = 'question-card-question-content']"));
            System.out.println("contentList Size : " + contentList.size());
            for (int a = 0; a < contentList.size(); a++) {
                String text = contentList.get(a).getAttribute("title");
                System.out.println("text : " + text);
            }
            questionText = contentList.get(contentList.size() - 1).getAttribute("title");
            postDiscussion4LastQuestionInQuestionCard("Hi, It's time to discuss");
            String questionTextAfterNewVersion = createVersionsForQuestion_Practice(questionText);
            publishQuestion();


            /*Row No = 262 : "1. Login as a student1
            2. Go to My Activity
            3. Click on the ""Completed a Personalized Practice"" assessment card (having older version question in practice assessment)"*/
            new LoginUsingLTI().ltiLogin(dataIndex);
            new Navigator().NavigateTo("My Activity");
            Thread.sleep(5000);
            //myActivity.getLink_CompletedAPersonalizedPracticeAssessmentCard().click();
            new Click().clickbypartiallinkText("Personalized Practice");
            Thread.sleep(5000);


            //Expected - 1 : On the Performance Summary page, the older version of question should be shown based on original entry of question including Question Bar and Question Card which can be correct/incorrect or skipped

            new UIElement().waitAndFindElement(By.className("question-card-question-content"));
            //Row No : 244 - 4. Click on the question card/question bar of older version question
            WebElement element = driver.findElements(By.className("question-card-question-content")).get(driver.findElements(By.className("question-card-question-content")).size() - 1);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(500);
            //element.click();
            new Click().clickByElement(element);
            Thread.sleep(3000);

            //Expected - 1. Notification Message should be shown on the top of the question as "This question is outdated. Please contact your instructor for any score related issue"
            actual = questions.getMsg_Notification().getText();
            expected = "This question is outdated. Please contact your instructor for any score related issues.";
            Assert.assertEquals(actual, expected, "Notification Message should be shown on the top of the question as \"This question is outdated. Please contact your instructor for any score related issue\"");

            //Expected - 2 : The “Report Content Issue” icon should be hidden for a outdated question.
            if (driver.findElement(By.cssSelector("div[title = 'Report Content Errors']")).isDisplayed()) {
                Assert.fail("The “Report Content Issue” icon should be hidden for a outdated question, but it is not hidden in this case");
            }


            /**********************************************************************************************************************************/
            /*Row No  - 246 : "5. Navigate to Course Stream
            6. Click on the jump-out icon of posted discussion of older version question"*/
            new Navigator().NavigateTo("Course Stream");
            Thread.sleep(3000);
            //diagnosticTab.jumpOut_icon.click();//Click on Jump out Icon
            new Click().clickBycssselector("span[class='ls-jumpout-icon-link ls-jumpout-icon']");
            Thread.sleep(5000);

            //Expected - 1 : Student should get navigated to older version of question
            actual = questions.getLabel_QuestionText().getText().trim();
            expected = questionText;
            Assert.assertEquals(actual, expected, "Student is not navigated to older version of question");

            //Expected - 2 : Notification Message should be shown on the top of the question as "This question is outdated. Please contact your instructor for any score related issue"
            actual = questions.getMsg_Notification().getText();
            expected = "This question is outdated. Please contact your instructor for any score related issues.";
            Assert.assertEquals(actual, expected, "Notification Message should be shown on the top of the question as \"This question is outdated. Please contact your instructor for any score related issue\"");

            //Expected - 3 : The “Report Content Issue” icon should be hidden for a outdated question.
            if (driver.findElements(By.cssSelector("div[title = 'Report Content Errors']")).size() != 0) {
                Assert.fail("The “Report Content Issue” icon should be hidden for a outdated question, but it is not hidden in this case");
            }
            /**********************************************************************************************************************************/



            /*Row No - 249 : "7. Navigate to My Activities
            8. Click on the discussion card of older version question"*/
            //Expected -1 : Clicking on a discussion card of older version of question should navigate the student to that question
            new Navigator().NavigateTo("My Activity");
            myActivity.getDiscussionLinkIcon().click();
            Thread.sleep(1000);
            myActivity.getDiscussionLinkIcon().click();
            Thread.sleep(1000);
            actual = questions.getLabel_QuestionText().getText().trim();
            expected = questionText;
            Assert.assertEquals(actual, expected, "Student is not navigated to older version of question");

            //Expected - 2 : Notification Message should be shown on the top of the question as "This question is outdated. Please contact your instructor for any score related issue"
            actual = questions.getMsg_Notification().getText();
            expected = "This question is outdated. Please contact your instructor for any score related issues.";
            Assert.assertEquals(actual, expected, "Notification Message should be shown on the top of the question as \"This question is outdated. Please contact your instructor for any score related issue\"");

            //Expected - 3 : The “Report Content Issue” icon should be hidden for a outdated question.
            if (driver.findElements(By.cssSelector("div[title = 'Report Content Errors']")).size() != 0) {
                Assert.fail("The “Report Content Issue” icon should be hidden for a outdated question, but it is not hidden in this case");
            }


            /**********************************************************************************************************************************/
            //Row No - 252 : 9. Navigate to Proficiency Report Page
            //Expected - Course proficiency summary page should show the older version of question on the right card and should be shown based on original entry of question which can be correct/incorrect or skipped
            new Navigator().NavigateTo("Proficiency Report");
            Thread.sleep(3000);
            showOlderQuestionOnRightCardAndOriginalEntry_PracticeTest(questionText);
            /**********************************************************************************************************************************/
            //Row No - 253 : 10. Click on the Chapter bar
            //Expected - Chapter proficiency summary page should show the older version of question on the right card and should be shown based on original entry of question which can be correct/incorrect or skipped
            proficiencyReport.getBarChart().click();// Click on the chapter bar
            Thread.sleep(2000);
            showOlderQuestionOnRightCardAndOriginalEntry_PracticeTest(questionText);
            /**********************************************************************************************************************************/
            //Row No - 254 : 11. Click on the TLO associated to deactivated question
            //Expected  - Older version question should be shown based on original entry of question including Question Bar and Question Card which can be correct/incorrect or skipped
            proficiencyReport.getBarChartElementsList().get(2).click();//Click on 3rd bar
            Thread.sleep(5000);
            String backgroundColor = proficiencyReport.getBarChartElementsList().get(proficiencyReport.getBarChartElementsList().size() - 1).getCssValue("background-color");
            System.out.println("backgroundColor : " + backgroundColor);
            showOlderQuestionOnRightCardAndOriginalEntry_PracticeTest(questionText);
            /**********************************************************************************************************************************/
            //Row No - 255 : 12. Click on the question card/question bar of deactivated question
            //Expected - 1 : Notification Message should be shown on the top of the question as "This question is outdated. Please contact your instructor for any score related issue"
            clickOnLastQuestionInQuestionCard();
            /*validateOutDatedQuestion(questionText);*/


        } catch (Exception e) {
            Assert.fail("Exception in the test 'student1HasSubmittedQsForPracticeAssessment' in the class 'QuestionVersions'", e);
        }
    }


    @Test(priority = 3, enabled = true)
    public void student1HasSubmittedQsForStaticAssessment() {
        String diagnosticTitleName = "Diagnostic - Ch 2: The Chemical Foundation of Life";
        try {
            int dataIndex = 278;
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            Questions questions = PageFactory.initElements(driver, Questions.class);
            DiagnosticTab diagnosticTab = PageFactory.initElements(driver, DiagnosticTab.class);
            ProficiencyReport proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);
            MyActivity myActivity = PageFactory.initElements(driver, MyActivity.class);
            new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");
            new Assignment().addQuestions(dataIndex, "truefalse", "");

            new LoginUsingLTI().ltiLogin("278");//log in as an Instructor
            new Assignment().assignToStudent(dataIndex);// Assign the assignment to class
            new LoginUsingLTI().ltiLogin("279");//log in as Student 1
            new Assignment().submitAssignmentAsStudent(279);
            System.out.println("Completed");
            postDiscussion4LastQuestionInQuestionCard("Hi, It's time to discuss");
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "278");//fetch assignment name from textdata

            String questionText = createVersionsForQuestion_staticAssessmnet(assessmentName);

            publishQuestion();

            /*Row No - 278 : "1. Login as a student1
            2. Go to eTextbook
            3. Click on the Static assessment of the same chapter which is already attempted by student (new version of question is created by author)"*/
            //Expected - On the Performance Summary page, the older version of question should be shown based on original entry of question including Question Bar and Question Card which can be correct/incorrect or skipped
            new LoginUsingLTI().ltiLogin("279");//log in as Student 1
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(1); //open first chapter
            new SelectCourse().selectInvisibleAssignment(assessmentName);
            Thread.sleep(3000);

            //Expected - 1 : On the Performance Summary page, the older version of question should be shown based on original entry of question including Question Bar and Question Card which can be correct/incorrect or skipped


            //Row No : 279 - 4. Click on the question card/question bar of older version question
            //Expected - 1. Notification Message should be shown on the top of the question as "This question is outdated. Please contact your instructor for any score related issue
            WebElement element = driver.findElement(By.className("question-card-question-content"));
            Thread.sleep(3000);
            new Click().clickByElement(element);
            Thread.sleep(7000);
            actual = questions.getMsg_Notification().getText();
            expected = "This question is outdated. Please contact your instructor for any score related issues.";
            Assert.assertEquals(actual, expected, "Notification Message should be shown on the top of the question as \"This question is outdated. Please contact your instructor for any score related issue\"");


            //Expected - 2 : The “Report Content Issue” icon should be hidden for a outdated question.
            if (driver.findElement(By.cssSelector("div[title = 'Report Content Errors']")).isDisplayed()) {
                Assert.fail("The “Report Content Issue” icon should be hidden for a outdated question, but it is not hidden in this case");
            }


            /**********************************************************************************************************************************/
            /*Row No  - 281 : "5. Navigate to Course Stream
            6. Click on the jump-out icon of posted discussion of older version question"*/
            new Navigator().NavigateTo("Course Stream");
            Thread.sleep(3000);
            new Click().clickBycssselector("span[class='ls-jumpout-icon-link ls-jumpout-icon']");
            Thread.sleep(5000);

            //Expected - 1 : Student should get navigated to older version of question
            actual = questions.getLabel_QuestionText().getText().trim();
            expected = questionText;
            Assert.assertEquals(actual, expected, "Student is not navigated to older version of question");

            //Expected - 2 : Notification Message should be shown on the top of the question as "This question is outdated. Please contact your instructor for any score related issue"
            actual = questions.getMsg_Notification().getText();
            expected = "This question is outdated. Please contact your instructor for any score related issues.";
            Assert.assertEquals(actual, expected, "Notification Message should be shown on the top of the question as \"This question is outdated. Please contact your instructor for any score related issue\"");

            //Expected - 3 : The “Report Content Issue” icon should be hidden for a outdated question.
            if (driver.findElements(By.cssSelector("div[title = 'Report Content Errors']")).size() != 0) {
                Assert.fail("The “Report Content Issue” icon should be hidden for a outdated question, but it is not hidden in this case");
            }
            /**********************************************************************************************************************************/



            /*Row No - 284 : "7. Navigate to My Activities
            8. Click on the discussion card of older version question"*/
            //Expected -1 : Clicking on a discussion card of older version of question should navigate the student to that question

            new Navigator().NavigateTo("My Activity");
            myActivity.getDiscussionLinkIcon().click();
            Thread.sleep(1000);
            myActivity.getDiscussionLinkIcon().click();
            Thread.sleep(1000);
            actual = questions.getLabel_QuestionText().getText().trim();
            expected = questionText;
            Assert.assertEquals(actual, expected, "Student is not navigated to older version of question");

            //Expected - 2 : Notification Message should be shown on the top of the question as "This question is outdated. Please contact your instructor for any score related issue"
            actual = questions.getMsg_Notification().getText();
            expected = "This question is outdated. Please contact your instructor for any score related issues.";
            Assert.assertEquals(actual, expected, "Notification Message should be shown on the top of the question as \"This question is outdated. Please contact your instructor for any score related issue\"");

            //Expected - 3 : The “Report Content Issue” icon should be hidden for a outdated question.
            if (driver.findElements(By.cssSelector("div[title = 'Report Content Errors']")).size() != 0) {
                Assert.fail("The “Report Content Issue” icon should be hidden for a outdated question, but it is not hidden in this case");
            }


            /**********************************************************************************************************************************/
            //Row No - 287 : 9. Navigate to Proficiency Report Page
            //Expected - Course proficiency summary page should show the older version of question on the right card and should be shown based on original entry of question which can be correct/incorrect or skipped
            new Navigator().NavigateTo("Proficiency Report");
            Thread.sleep(3000);
            showOlderQuestionOnRightCardAndOriginalEntry_staticAssesment(questionText);
            /**********************************************************************************************************************************/
            //Row No - 288 : 10. Click on the Chapter bar
            //Expected - Chapter proficiency summary page should show the older version of question on the right card and should be shown based on original entry of question which can be correct/incorrect or skipped
            proficiencyReport.getBarChart().click();// Click on the chapter bar
            Thread.sleep(2000);
            showOlderQuestionOnRightCardAndOriginalEntry_staticAssesment(questionText);
            /**********************************************************************************************************************************/
            //Row No - 289 : 11. Click on the TLO associated to deactivated question
            //Expected  - Older version question should be shown based on original entry of question including Question Bar and Question Card which can be correct/incorrect or skipped
            Thread.sleep(5000);
            proficiencyReport.getBarChartElementsList().get(1).click();//Click on 3rd bar
            Thread.sleep(5000);
            String backgroundColor = proficiencyReport.getBarChartElementsList().get(proficiencyReport.getBarChartElementsList().size() - 1).getCssValue("background-color");
            System.out.println("backgroundColor : " + backgroundColor);
            showOlderQuestionOnRightCardAndOriginalEntry_staticAssesment(questionText);
            //**********************************************************************************************************************************//*
            //Row No - 290 : 12. Click on the question card/question bar of deactivated question
            //Expected - 1 : Notification Message should be shown on the top of the question as "This question is outdated. Please contact your instructor for any score related issue"
            element = driver.findElement(By.className("question-card-question-content"));
            element.click();
            Thread.sleep(2000);
            validateOutDatedQuestion(questionText);


            //Expected - 2 : In About tab, “student got it correct” should show 0%
            actual = proficiencyReport.getLabelValue_StudentGotItCorrect().getText();
            expected = "%";
            if (!(actual.contains(expected))) {
                Assert.fail("In About tab, “student got it correct” is not showing 0%");
            }
            //Assert.assertEquals(actual,expected,"In About tab, “student got it correct” is not showing 0%");

            //Expected - 3 : The “Report Content Issue” icon should be hidden for a outdated question.
            if (driver.findElement(By.cssSelector("div[title = 'Report Content Errors']")).isDisplayed()) {
                Assert.fail("The “Report Content Issue” icon should be hidden for a outdated question, but it is not hidden in this case");
            }




            /*Row No - 293 : "1. Login as a student2
            2. Go to eTextbook
            3. Click on the static assessment of the same chapter in which new version of question is created by author"*/
            //Expected - Student should get the newer version of the question

            questionText = questionText + "V1";
            new LoginUsingLTI().ltiLogin("293");//log in as Student 1
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(1); //open first chapter
            new SelectCourse().selectInvisibleAssignment(assessmentName);
            boolean isNewVersionQuestionAvailable = false;
            Thread.sleep(7000);
            if (driver.findElements(By.cssSelector("input[type = 'button']")).size() != 0) {
                System.out.println("Element Present");
            }
            while (driver.findElements(By.cssSelector("input[type = 'button']")).size() != 0) {
                Thread.sleep(1000);
                String questionTitle = questions.getLabel_QuestionText().getText();
                if (questionTitle.equals(questionText)) {
                    isNewVersionQuestionAvailable = true;
                    break;
                }
                driver.findElement(By.cssSelector("input[type = 'button']")).click();
            }
            if (isNewVersionQuestionAvailable == false) {
                Assert.fail("Student should get the newer version of the question");
            }


            //Steps : 5. Finish the static assessment
            //Expected -  On the Performance Summary page, the newer version question should be be shown including Question Bar and Question Card
            new LoginUsingLTI().ltiLogin("294");
            new Assignment().submitAssignmentAsStudent(279);
            String newVersionQuestionText = driver.findElement(By.className("question-card-question-content")).getText().trim();
            Assert.assertEquals(newVersionQuestionText, questionText, "On the Performance Summary page, the newer version question is not showing in Question Card");


        } catch (Exception e) {
            Assert.fail("Exception in the test 'student1HasSubmittedQsForDiagnosticAssessment' in the class 'QuestionVersions'", e);
        }
    }


    @Test(priority = 4, enabled = true)
    public void checkGradableAssignment() {
        String diagnosticTitleName = "Diagnostic - Ch 2: The Chemical Foundation of Life";
        try {
            int dataIndex = 297;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "297");//fetch assignment name from text data
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            Questions questions = PageFactory.initElements(driver, Questions.class);
            DiagnosticTab diagnosticTab = PageFactory.initElements(driver, DiagnosticTab.class);
            ProficiencyReport proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);
            MyActivity myActivity = PageFactory.initElements(driver, MyActivity.class);
            PerformanceSummary performanceSummary = PageFactory.initElements(driver, PerformanceSummary.class);

            new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex,"multiplechoice", "");
            new Assignment().addQuestions(dataIndex,"multiplechoice", "");
            new Assignment().addQuestions(dataIndex,"multipleselection", "");
            new Assignment().addQuestions(dataIndex,"textentry","");
            //1. Login as instructor with 4 students in class
            new LoginUsingLTI().ltiLogin("297");//log in as an Instructor
            new LoginUsingLTI().ltiLogin("297_1");//log in as a student1
            new LoginUsingLTI().ltiLogin("297_2");//log in as a student2
            new LoginUsingLTI().ltiLogin("297_3");//log in as a student3
            new LoginUsingLTI().ltiLogin("297_4");//log in as a student4


            //2. Assign a gradable assignment
            new LoginUsingLTI().ltiLogin("297");//log in as an Instructor
            new Assignment().assignToStudent(dataIndex);// Assign the assignment to class
            Thread.sleep(20000);
            //3. Login as student2 and leave the assignment in progress without attempting "to be older version question"
            new LoginUsingLTI().ltiLogin("297_2");//log in as Student 2
            new Navigator().NavigateTo("Assignments");
            new Assignment().clickonAssignment(assessmentName);
            new Assignment().nextButtonInQuestionClick();
            new AttemptQuestion().trueFalse(false,"correct",297_2);
            new Assignment().nextButtonInQuestionClick();


            //4. Login as student 3 and leaving the assignment in progress attempting "to be older version question"
            new LoginUsingLTI().ltiLogin("297_3");//log in as Student 3
            new Navigator().NavigateTo("Assignments");
            new Assignment().clickonAssignment(assessmentName);
            new AttemptQuestion().trueFalse(false,"correct",297_3);
            new Assignment().nextButtonInQuestionClick();


            //5. Login as student 4 and submit the assignment
            new LoginUsingLTI().ltiLogin("297_4");//log in as Student 4
            new Assignment().submitAssignmentAsStudent(297);



            /*6. Login as CMS user
            7. Create a new version of a question and publish it*/




            String questionText = createVersionsForQuestion_staticAssessmnet(assessmentName);
            publishQuestion();


            Thread.sleep(20000);
            new LoginUsingLTI().ltiLogin("297_1");//log in as Student 1
            new Navigator().NavigateTo("Assignments");
            new Assignment().clickonAssignment(assessmentName);

            //Expected - 1 : Summary dropdown should exclude count and label of older version question
            //Expected - 3 : Newer version question should get delivered
            //Expected - 4 : Question label should get updated post newer version creation
            summaryDropDownCountToBeExcludedAndLabelOfOlderVersionQuestion("Question (5 of 5)",questionText);



            //Expected -2 : Older version question should not get delivered throughout the assignment
            olderVersionQuestionNotToBeDelivered(questionText);



            //Expected - 5 : Student should be able to submit the assignment
            new Assignment().submitAssignmentAsStudent(297);


            //Expected - 6 : No bar or question card should get displayed for older version question
            for(int i=0;i<performanceSummary.getQuestionCardList().size();i++){
                if(performanceSummary.getQuestionCardList().get(i).getText().equals(questionText)){
                    Assert.fail("No bar or question card should get displayed for older version question");
                    //Assert.assertNotEquals(performanceSummary.getQuestionCardList().get(i).getText(),questionText,"No bar or question card should get displayed for older version question");
                }
            }



            //Expected - 7 : Updated question label should be displayed for bar and card for questions remaining post newer version creation
            boolean isThereOlderVersionQuestion = false;
            List<WebElement> questionCardElementsList = driver.findElements(By.className("question-card-question-content"));
            System.out.println("questionCardElementsList.size() : " + questionCardElementsList.size());

            for(int i=0;i<questionCardElementsList.size();i++){
                System.out.println("Question Card : " + questionCardElementsList.get(i).getText());
                System.out.println("questionText+\"V1\" : " + questionText+"V1");

                if(questionCardElementsList.get(i).getText().trim().equals(questionText+"V1")){
                    System.out.println("Yes");
                    isThereOlderVersionQuestion = true;
                    break;
                }
            }

            if(isThereOlderVersionQuestion ==false){
                Assert.fail("Updated question label should be displayed for bar and card for questions remaining post newer version creation");
            }
            //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

            //Row No - 304 - 9. Login as student 2 and restart the assignment
            new LoginUsingLTI().ltiLogin("297_2");//log in as Student 2
            new Navigator().NavigateTo("Assignments");
            new Assignment().clickonAssignment(assessmentName);

            //Expected - 1 : Summary dropdown should exclude count and label of older version question
            //Expected - 3 : Newer version question should get delivered
            //Expected - 4 : Question label should get updated post newer version creation
            summaryDropDownCountToBeExcludedAndLabelOfOlderVersionQuestion("Question (11 of 11)",questionText);



            //Expected -2 : Older version question should not get delivered throughout the assignment
            olderVersionQuestionNotToBeDelivered(questionText);



            //Expected - 5 : Student should be able to submit the assignment
            new Assignment().submitAssignmentAsStudent(297);


            //Expected - 6 : No bar or question card should get displayed for older version question
            for(int i=0;i<performanceSummary.getQuestionCardList().size();i++){
                if(performanceSummary.getQuestionCardList().get(i).getText().equals(questionText)){
                    Assert.fail("No bar or question card should get displayed for older version question");
                    //Assert.assertNotEquals(performanceSummary.getQuestionCardList().get(i).getText(),questionText,"No bar or question card should get displayed for older version question");
                }
            }



            //Expected - 7 : Updated question label should be displayed for bar and card for questions remaining post newer version creation
            isThereOlderVersionQuestion = false;
            System.out.println("performanceSummary.getQuestionCardList().size() : " + performanceSummary.getQuestionCardList().size());
            System.out.println("questionText : " + questionText);
            for(int i=0;i<performanceSummary.getQuestionCardList().size();i++){
                System.out.println("Special : "+performanceSummary.getQuestionCardList().get(i).getText());
                if(performanceSummary.getQuestionCardList().get(i).getText().trim().equals(questionText + "V1")){
                    System.out.println("Yes");
                    isThereOlderVersionQuestion = true;
                    break;
                }
            }

            if(isThereOlderVersionQuestion ==false){
                Assert.fail("Updated question label should be displayed for bar and card for questions remaining post newer version creation");
            }

            performanceSummary.getQuestionCardList().get(0).click();
            Thread.sleep(5000);
            validateOutDatedQuestion(questionText);


            //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

            //Row No - 311 - 10. Login as student 3 and restart the assignment
            new LoginUsingLTI().ltiLogin("297_3");//log in as Student 3
            new Navigator().NavigateTo("Assignments");
            new Assignment().clickonAssignment(assessmentName);

            //Expected - 1 : Summary dropdown should exclude count and label of older version question
            //Expected - 3 : Newer version question should get delivered
            //Expected - 4 : Question label should get updated post newer version creation
            summaryDropDownCountToBeExcludedAndLabelOfOlderVersionQuestion("Question (11 of 11)",questionText);



            //Expected -2 : Older version question should not get delivered throughout the assignment
            olderVersionQuestionNotToBeDelivered(questionText);



            //Expected - 5 : Student should be able to submit the assignment
            new Assignment().submitAssignmentAsStudent(297);




            //Expected - 6 :  bar or question card should get displayed for older version question
            isThereOlderVersionQuestion = false;

            for(int i=0;i<performanceSummary.getQuestionCardList().size();i++){
                if(performanceSummary.getQuestionCardList().get(i).getText().trim().equals(questionText)){
                    isThereOlderVersionQuestion = true;
                    break;
                }
            }
            if(isThereOlderVersionQuestion ==false){
                Assert.fail("bar or question card should get displayed for older version question");
            }



            //Expected - 7 : Updated question label should be displayed for bar and card for questions remaining post newer version creation
            isThereOlderVersionQuestion = false;
            for(int i=0;i<performanceSummary.getQuestionCardList().size();i++){
                if(performanceSummary.getQuestionCardList().get(i).getText().trim().equals(questionText+"V1")){
                    isThereOlderVersionQuestion = true;
                    break;
                }
            }

            if(isThereOlderVersionQuestion ==false){
                Assert.fail("Updated question label should be displayed for bar and card for questions remaining post newer version creation");
            }



        } catch (Exception e) {
            Assert.fail("Exception in the test 'student1HasSubmittedQsForDiagnosticAssessment' in the class 'QuestionVersions'", e);
        }
    }




    @Test(priority = 4, enabled = true)
    public void checkNonGradableAssignment() {
        //Working fine
        try {
            int dataIndex = 346;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "346");//fetch assignment name from text data
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            Questions questions = PageFactory.initElements(driver, Questions.class);
            DiagnosticTab diagnosticTab = PageFactory.initElements(driver, DiagnosticTab.class);
            ProficiencyReport proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);
            MyActivity myActivity = PageFactory.initElements(driver, MyActivity.class);
            PerformanceSummary performanceSummary = PageFactory.initElements(driver, PerformanceSummary.class);

            new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex,"multiplechoice", "");
            new Assignment().addQuestions(dataIndex,"multiplechoice", "");
            new Assignment().addQuestions(dataIndex,"multipleselection", "");
            new Assignment().addQuestions(dataIndex,"textentry","");
            //1. Login as instructor with 4 students in class
            new LoginUsingLTI().ltiLogin("346");//log in as an Instructor
            new LoginUsingLTI().ltiLogin("346_1");//log in as a student1
            new LoginUsingLTI().ltiLogin("346_2");//log in as a student2
            new LoginUsingLTI().ltiLogin("346_3");//log in as a student3
            new LoginUsingLTI().ltiLogin("346_4");//log in as a student4


            //2. Assign a gradable assignment
            new LoginUsingLTI().ltiLogin("346");//log in as an Instructor
            new Assignment().assignToStudent(dataIndex);// Assign the assignment to class
            Thread.sleep(20000);
            //3. Login as student2 and leave the assignment in progress without attempting "to be older version question"
            new LoginUsingLTI().ltiLogin("346_2");//log in as Student 2
            new Navigator().NavigateTo("Assignments");
            new Assignment().clickonAssignment(assessmentName);
            new Assignment().nextButtonInQuestionClick();
            new AttemptQuestion().trueFalse(false,"correct",346_2);
            new Assignment().nextButtonInQuestionClick();


            //4. Login as student 3 and leaving the assignment in progress attempting "to be older version question"
            new LoginUsingLTI().ltiLogin("346_3");//log in as Student 3
            new Navigator().NavigateTo("Assignments");
            new Assignment().clickonAssignment(assessmentName);
            new AttemptQuestion().trueFalse(false,"correct",346_3);
            new Assignment().nextButtonInQuestionClick();


            //5. Login as student 4 and submit the assignment
            new LoginUsingLTI().ltiLogin("346_4");//log in as Student 4
            new Assignment().submitAssignmentAsStudent(346);



            /*6. Login as CMS user
            7. Create a new version of a question and publish it*/




            String questionText = createVersionsForQuestion_staticAssessmnet(assessmentName);
            publishQuestion();
            Thread.sleep(20000);
            new LoginUsingLTI().ltiLogin("346_1");//log in as Student 1
            new Navigator().NavigateTo("Assignments");
            new Assignment().clickonAssignment(assessmentName);

            //Expected - 1 : Summary dropdown should exclude count and label of older version question
            //Expected - 3 : Newer version question should get delivered
            //Expected - 4 : Question label should get updated post newer version creation
            summaryDropDownCountToBeExcludedAndLabelOfOlderVersionQuestion("Question (5 of 5)",questionText);



            //Expected -2 : Older version question should not get delivered throughout the assignment
            olderVersionQuestionNotToBeDelivered(questionText);



            //Expected - 5 : Student should be able to submit the assignment
            new Assignment().submitAssignmentAsStudent(346);


            //Expected - 6 : No bar or question card should get displayed for older version question
            for(int i=0;i<performanceSummary.getQuestionCardList().size();i++){
                if(performanceSummary.getQuestionCardList().get(i).getText().equals(questionText)){
                    Assert.fail("No bar or question card should get displayed for older version question");
                    //Assert.assertNotEquals(performanceSummary.getQuestionCardList().get(i).getText(),questionText,"No bar or question card should get displayed for older version question");
                }
            }



            //Expected - 7 : Updated question label should be displayed for bar and card for questions remaining post newer version creation
            boolean isThereOlderVersionQuestion = false;
            List<WebElement> questionCardElementsList = driver.findElements(By.className("question-card-question-content"));
            System.out.println("questionCardElementsList.size() : " + questionCardElementsList.size());

            for(int i=0;i<questionCardElementsList.size();i++){
                System.out.println("Question Card : " + questionCardElementsList.get(i).getText());
                System.out.println("questionText+\"V1\" : " + questionText+"V1");

                if(questionCardElementsList.get(i).getText().trim().equals(questionText+"V1")){
                    System.out.println("Yes");
                    isThereOlderVersionQuestion = true;
                    break;
                }
            }

            if(isThereOlderVersionQuestion ==false){
                Assert.fail("Updated question label should be displayed for bar and card for questions remaining post newer version creation");
            }
            //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

            //Row No - 304 - 9. Login as student 2 and restart the assignment
            new LoginUsingLTI().ltiLogin("346_2");//log in as Student 2
            new Navigator().NavigateTo("Assignments");
            new Assignment().clickonAssignment(assessmentName);

            //Expected - 1 : Summary dropdown should exclude count and label of older version question
            //Expected - 3 : Newer version question should get delivered
            //Expected - 4 : Question label should get updated post newer version creation
            summaryDropDownCountToBeExcludedAndLabelOfOlderVersionQuestion("Question (11 of 11)",questionText);



            //Expected -2 : Older version question should not get delivered throughout the assignment
            olderVersionQuestionNotToBeDelivered(questionText);



            //Expected - 5 : Student should be able to submit the assignment
            new Assignment().submitAssignmentAsStudent(346);


            //Expected - 6 : No bar or question card should get displayed for older version question
            for(int i=0;i<performanceSummary.getQuestionCardList().size();i++){
                if(performanceSummary.getQuestionCardList().get(i).getText().equals(questionText)){
                    Assert.fail("No bar or question card should get displayed for older version question");
                    //Assert.assertNotEquals(performanceSummary.getQuestionCardList().get(i).getText(),questionText,"No bar or question card should get displayed for older version question");
                }
            }



            //Expected - 7 : Updated question label should be displayed for bar and card for questions remaining post newer version creation
            isThereOlderVersionQuestion = false;
            System.out.println("performanceSummary.getQuestionCardList().size() : " + performanceSummary.getQuestionCardList().size());
            System.out.println("questionText : " + questionText);
            for(int i=0;i<performanceSummary.getQuestionCardList().size();i++){
                System.out.println("Special : "+performanceSummary.getQuestionCardList().get(i).getText());
                if(performanceSummary.getQuestionCardList().get(i).getText().trim().equals(questionText + "V1")){
                    System.out.println("Yes");
                    isThereOlderVersionQuestion = true;
                    break;
                }
            }

            if(isThereOlderVersionQuestion ==false){
                Assert.fail("Updated question label should be displayed for bar and card for questions remaining post newer version creation");
            }

            performanceSummary.getQuestionCardList().get(0).click();
            Thread.sleep(5000);
            validateOutDatedQuestion(questionText);


            //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

            //Row No - 311 - 10. Login as student 3 and restart the assignment
            new LoginUsingLTI().ltiLogin("346_3");//log in as Student 3
            new Navigator().NavigateTo("Assignments");
            new Assignment().clickonAssignment(assessmentName);

            //Expected - 1 : Summary dropdown should exclude count and label of older version question
            //Expected - 3 : Newer version question should get delivered
            //Expected - 4 : Question label should get updated post newer version creation
            summaryDropDownCountToBeExcludedAndLabelOfOlderVersionQuestion("Question (11 of 11)",questionText);



            //Expected -2 : Older version question should not get delivered throughout the assignment
            olderVersionQuestionNotToBeDelivered(questionText);



            //Expected - 5 : Student should be able to submit the assignment
            new Assignment().submitAssignmentAsStudent(346);




            //Expected - 6 :  bar or question card should get displayed for older version question
            isThereOlderVersionQuestion = false;

            for(int i=0;i<performanceSummary.getQuestionCardList().size();i++){
                if(performanceSummary.getQuestionCardList().get(i).getText().trim().equals(questionText)){
                    isThereOlderVersionQuestion = true;
                    break;
                }
            }
            if(isThereOlderVersionQuestion ==false){
                Assert.fail("bar or question card should get displayed for older version question");
            }



            //Expected - 7 : Updated question label should be displayed for bar and card for questions remaining post newer version creation
            isThereOlderVersionQuestion = false;
            for(int i=0;i<performanceSummary.getQuestionCardList().size();i++){
                if(performanceSummary.getQuestionCardList().get(i).getText().trim().equals(questionText+"V1")){
                    isThereOlderVersionQuestion = true;
                    break;
                }
            }

            if(isThereOlderVersionQuestion ==false){
                Assert.fail("Updated question label should be displayed for bar and card for questions remaining post newer version creation");
            }






            //Expected 8 - Notification Message should be shown on the top of the question as "This question is outdated. Please contact your instructor for any score related issue"
            performanceSummary.getQuestionCardList().get(0).click();
            Thread.sleep(5000);
            validateOutDatedQuestion(questionText);



            //Expected - 10 : Questions attempted count over dashboard should include older version question
            actual = performanceSummary.getQuestionCardList().get(0).getText().trim();
            Assert.assertEquals(actual,questionText,"Questions attempted count over dashboard should include older version question");


            proficiencyReport.getBarChart().click();
            Assert.assertEquals(actual,questionText,"Questions attempted count over dashboard should include older version question");



            //Expected - 11 : Performance tile at dashboard should include older version question and newer version questions




        } catch (Exception e) {
            Assert.fail("Exception in the test 'student1HasSubmittedQsForDiagnosticAssessment' in the class 'QuestionVersions'", e);
        }
    }






    @Test(priority = 5)
    public void checkCourseStreamEntryFrDiscussionAddedToOlderVersionQuestion(){
        try{
            //Row No - 382
            int dataIndex = 382;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "382");//fetch assignment name from text data
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            Questions questions = PageFactory.initElements(driver, Questions.class);
            DiagnosticTab diagnosticTab = PageFactory.initElements(driver, DiagnosticTab.class);
            ProficiencyReport proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);
            MyActivity myActivity = PageFactory.initElements(driver, MyActivity.class);
            PerformanceSummary performanceSummary = PageFactory.initElements(driver, PerformanceSummary.class);

            new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex,"multiplechoice", "");
            new Assignment().addQuestions(dataIndex,"multiplechoice", "");
            new Assignment().addQuestions(dataIndex,"multipleselection", "");
            new Assignment().addQuestions(dataIndex,"multiplechoice", "");



            //1. Login as instructor having 2 students in class.
            new LoginUsingLTI().ltiLogin("382");//log in as an Instructor
            new LoginUsingLTI().ltiLogin("382_1");//log in as a student1
            new LoginUsingLTI().ltiLogin("382_2");//log in as a student2

            //2. Assign a gradable custom assignment.
            new LoginUsingLTI().ltiLogin("382");//log in as an Instructor
            new Assignment().assignToStudent(dataIndex);// Assign the assignment to class
            Thread.sleep(20000);

            //3. Login as student1 and submit the assignment
            new LoginUsingLTI().ltiLogin("382_1");//log in as Student 1
            new Assignment().submitAssignmentAsStudent(382);

            //4. Add discussion over to be older version question
            //postDiscussion4LastQuestionInQuestionCard("Hi, It's time to discuss");
            questions.getLabel_QuestionCardElementsList().get(0).click();
            Thread.sleep(3000);
            questions.getTab_Discussion().click();
            Thread.sleep(1000);
            new Discussion().postDiscussion("Hi, It's time to discuss");


            /*4. Login as CMS user
            5. Create a new version of the question and publish it*/
            String questionText = createVersionsForQuestion_staticAssessmnet(assessmentName);
            publishQuestion();
            Thread.sleep(20000);
            //6. Login as student and go to course stream

            new LoginUsingLTI().ltiLogin("382_1");//log in as Student 1
            new Navigator().NavigateTo("Course Stream");
            Thread.sleep(20000);

            //Click on jump out for discussion entry over course stream
            new Click().clickBycssselector("span[class='ls-jumpout-icon-link ls-jumpout-icon']");
            Thread.sleep(5000);

            //Row No - 382 : Expected - 1 : Student should get navigated to older version of question
            actual = questions.getLabel_QuestionText().getText().trim();
            expected = questionText;
            Assert.assertEquals(actual, expected, "Student is not navigated to older version of question");

            //Row No - 383 : Expected - 2 : Notification Message should be shown on the top of the question as "This question is outdated. Please contact your instructor for any score related issue"
            actual = questions.getMsg_Notification().getText();
            expected = "This question is outdated. Please contact your instructor for any score related issues.";
            Assert.assertEquals(actual, expected, "Notification Message should be shown on the top of the question as \"This question is outdated. Please contact your instructor for any score related issue\"");

            //Row No - 384 : Expected - 3 : The “Report Content Issue” icon should be hidden for a older version question.
            if (driver.findElements(By.cssSelector("div[title = 'Report Content Errors']")).size() != 0) {
                Assert.fail("The “Report Content Issue” icon should be hidden for a outdated question, but it is not hidden in this case");
            }


        }catch(Exception e){
            Assert.fail("Exception in the testcase 'checkCourseStreamEntryFrDiscussionAddedToOlderVersionQuestion' in class 'QuestionVersions'",e);
        }

    }




    @Test(priority = 5)
    public void checkDiscussionEntryOverMyActivity(){
        try{
            //Row No - 382
            int dataIndex = 385;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "385");//fetch assignment name from text data
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            Questions questions = PageFactory.initElements(driver, Questions.class);
            DiagnosticTab diagnosticTab = PageFactory.initElements(driver, DiagnosticTab.class);
            ProficiencyReport proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);
            MyActivity myActivity = PageFactory.initElements(driver, MyActivity.class);
            PerformanceSummary performanceSummary = PageFactory.initElements(driver, PerformanceSummary.class);

            new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex,"multiplechoice", "");
            new Assignment().addQuestions(dataIndex,"multiplechoice", "");
            new Assignment().addQuestions(dataIndex,"multipleselection", "");
            new Assignment().addQuestions(dataIndex,"multiplechoice", "");



            //1. Login as instructor having 2 students in class.
            new LoginUsingLTI().ltiLogin("385");//log in as an Instructor
            new LoginUsingLTI().ltiLogin("385_1");//log in as a student1
            new LoginUsingLTI().ltiLogin("385_2");//log in as a student2

            //2. Assign a gradable custom assignment.
            new LoginUsingLTI().ltiLogin("385");//log in as an Instructor
            new Assignment().assignToStudent(dataIndex);// Assign the assignment to class
            Thread.sleep(20000);

            //3. Login as student1 and submit the assignment
            new LoginUsingLTI().ltiLogin("385_1");//log in as Student 1
            new Assignment().submitAssignmentAsStudent(382);

            //4. Add discussion over to be older version question
            //postDiscussion4LastQuestionInQuestionCard("Hi, It's time to discuss");
            questions.getLabel_QuestionCardElementsList().get(0).click();
            Thread.sleep(3000);
            questions.getTab_Discussion().click();
            Thread.sleep(1000);
            new Discussion().postDiscussion("Hi, It's time to discuss");


            /*4. Login as CMS user
            5. Create a new version of the question and publish it*/
            String questionText = createVersionsForQuestion_staticAssessmnet(assessmentName);
            publishQuestion();
            Thread.sleep(20000);
            //6. Login as student and go to course stream

            new LoginUsingLTI().ltiLogin("385_1");//log in as Student 1
            new Navigator().NavigateTo("My Activity");
            Thread.sleep(20000);

            //Click on jump out for discussion entry over course stream
            new Click().clickBycssselector("span[class='ls-jumpout-icon-link ls-jumpout-icon']");
            Thread.sleep(5000);

            //Row No - 382 : Expected - 1 : Student should get navigated to older version of question
            actual = questions.getLabel_QuestionText().getText().trim();
            expected = questionText;
            Assert.assertEquals(actual, expected, "Student is not navigated to older version of question");

            //Row No - 383 : Expected - 2 : Notification Message should be shown on the top of the question as "This question is outdated. Please contact your instructor for any score related issue"
            actual = questions.getMsg_Notification().getText();
            expected = "This question is outdated. Please contact your instructor for any score related issues.";
            Assert.assertEquals(actual, expected, "Notification Message should be shown on the top of the question as \"This question is outdated. Please contact your instructor for any score related issue\"");

            //Row No - 384 : Expected - 3 : The “Report Content Issue” icon should be hidden for a older version question.
            if (driver.findElements(By.cssSelector("div[title = 'Report Content Errors']")).size() != 0) {
                Assert.fail("The “Report Content Issue” icon should be hidden for a outdated question, but it is not hidden in this case");
            }


        }catch(Exception e){
            Assert.fail("Exception in the testcase 'checkCourseStreamEntryFrDiscussionAddedToOlderVersionQuestion' in class 'QuestionVersions'",e);
        }

    }



    @Test(priority = 6)
    public void checkWhenQuestionIsOlderVersionBeforeStudentHasAccessedAssignment(){
        try{
            //Row No - 389
            int dataIndex = 389;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "389");//fetch assignment name from text data
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            Questions questions = PageFactory.initElements(driver, Questions.class);
            DiagnosticTab diagnosticTab = PageFactory.initElements(driver, DiagnosticTab.class);
            ProficiencyReport proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);
            MyActivity myActivity = PageFactory.initElements(driver, MyActivity.class);
            PerformanceSummary performanceSummary = PageFactory.initElements(driver, PerformanceSummary.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Gradebook gradebook = PageFactory.initElements(driver, Gradebook.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            EngagementReport engagementReport = PageFactory.initElements(driver, EngagementReport.class);


            new Assignment().create(dataIndex);
            new Assignment().addQuestions(dataIndex,"multiplechoice", "");
            new Assignment().addQuestions(dataIndex,"multiplechoice", "");
            new Assignment().addQuestions(dataIndex,"multipleselection", "");
            new Assignment().addQuestions(dataIndex,"multiplechoice", "");



            //1. Login as instructor
            //2. Assign a gradable assignment
            new LoginUsingLTI().ltiLogin("389");//log in as an Instructor
            new LoginUsingLTI().ltiLogin("389_1");//log in as Student1


            new LoginUsingLTI().ltiLogin("389");//log in as an Instructor
            new Assignment().assignToStudent(dataIndex);// Assign the assignment to class
            Thread.sleep(20000);

            //3. Login as CMS user and create a new version of a question
            String questionText = createVersionsForQuestion_staticAssessmnet(assessmentName);
            publishQuestion();
            Thread.sleep(20000);


            //4. Login as instructor
            //5. Go to assignment responses page of the assignment


            new LoginUsingLTI().ltiLogin("389");//log in as an Instructor
            new Navigator().NavigateTo("Current Assignments");
            Thread.sleep(5000);

            //currentAssignments.getViewGrade_link().click();
            new Click().clickByclassname("ls-grade-book-assessment");
            Thread.sleep(5000);

            //Row No - 389 : Expected -1 : Column for older version question should not be available


            //Row No - 390 : Expected - 2 : Column for newer version question be available



            //Row No - 391 : Total points displayed should get updated including newer version question
            Assert.assertEquals(assignmentResponsesPage.getTotalPoints().getText(),"Total Points: 5","Total points displayed should get updated including newer version question");


            //Row No - 392 : Expected - 4 : Preview opened from assignment response page should not contain older version question and should show newer version
            //assignmentResponsesPage.assessmentName_AssignmentResponses.click();
            new Click().clickBycssselector("span[class='ls-assignment-name instructor-assessment-review']");
            Thread.sleep(5000);
            boolean isOlderVersionQuestion = false;
            List<WebElement>questionsElementsList = driver.findElements(By.className("control-label"));
            for(int a=0;a<questionsElementsList.size();a++){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",questionsElementsList.get(a));
                if(questionsElementsList.get(a).getText().trim().equals(questionText)){
                    isOlderVersionQuestion = true;
                    break;
                }
            }

            if(isOlderVersionQuestion ==true){
                Assert.fail("Preview opened from assignment response page should not contain older version question and");
            }


            isOlderVersionQuestion = false;
            questionsElementsList = driver.findElements(By.className("control-label"));
            System.out.println("questionsElementsList size : " + questionsElementsList.size());
            for(int a=0;a<questionsElementsList.size();a++){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",questionsElementsList.get(a));
                Thread.sleep(1000);
                if(questionsElementsList.get(a).getText().trim().equals(questionText+"V1")){
                    isOlderVersionQuestion = true;
                    break;
                }
            }

            if(isOlderVersionQuestion ==false){
                Assert.fail("Preview opened from assignment response page should show newer version");
            }


            //Row No - 393 : Preview opened from current assignment page should not contain older version question and should show newer version
            new Navigator().NavigateTo("Current Assignments");
            new Click().clickBycssselector("span[class='ls-assignment-name instructor-assessment-review']");//click on the assignment name
            Thread.sleep(5000);
            isOlderVersionQuestion = false;
            questionsElementsList = driver.findElements(By.className("control-label"));
            for(int a=0;a<questionsElementsList.size();a++){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",questionsElementsList.get(a));
                if(questionsElementsList.get(a).getText().trim().equals(questionText)){
                    isOlderVersionQuestion = true;
                    break;
                }
            }

            if(isOlderVersionQuestion ==true){
                Assert.fail("Preview opened from assignment response page should not contain older version question and");
            }


            isOlderVersionQuestion = false;
            questionsElementsList = driver.findElements(By.className("control-label"));
            System.out.println("questionsElementsList size : " + questionsElementsList.size());
            for(int a=0;a<questionsElementsList.size();a++){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",questionsElementsList.get(a));
                Thread.sleep(1000);
                if(questionsElementsList.get(a).getText().trim().equals(questionText+"V1")){
                    isOlderVersionQuestion = true;
                    break;
                }
            }

            if(isOlderVersionQuestion ==false){
                Assert.fail("Preview opened from assignment response page should show newer version");
            }




            /*Row No -  394 : "6. Login as student and submit the assignment
            7. Login as instructor and go to assignment responses page
            8. Open view response page for first question and traverse through all available question"*/
            //Expected 2 - View resposne for older version question should not appear
            //Expected 1 - Question label should appear updated after newer version creation

            new LoginUsingLTI().ltiLogin("389_1");//log in as a Student1
            new Assignment().submitAssignmentAsStudent(389);


            new LoginUsingLTI().ltiLogin("389");//log in as an Instructor
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getViewGrade_link().click();
            Thread.sleep(5000);
            new Click().clickBycssselector("span[class='ls-assignment-name instructor-assessment-review']");
            //assignmentResponsesPage.assessmentName_AssignmentResponse.get(0).click();
            Thread.sleep(5000);
            isOlderVersionQuestion = false;
            questionsElementsList = driver.findElements(By.className("control-label"));
            for(int a=0;a<questionsElementsList.size();a++){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",questionsElementsList.get(a));
                if(questionsElementsList.get(a).getText().trim().equals(questionText)){
                    isOlderVersionQuestion = true;
                    break;
                }
            }

            if(isOlderVersionQuestion ==true){
                Assert.fail("Preview opened from assignment response page should not contain older version question and");
            }


            isOlderVersionQuestion = false;
            questionsElementsList = driver.findElements(By.className("control-label"));
            System.out.println("questionsElementsList size : " + questionsElementsList.size());
            for(int a=0;a<questionsElementsList.size();a++){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",questionsElementsList.get(a));
                Thread.sleep(1000);
                if(questionsElementsList.get(a).getText().trim().equals(questionText+"V1")){
                    isOlderVersionQuestion = true;
                    break;
                }
            }

            if(isOlderVersionQuestion ==false){
                Assert.fail("Preview opened from assignment response page should show newer version");
            }


            //Row No - 396 : 9. Fill in grades to all questions and release the grades
            new Assignment().provideGradeToStudentForMultipleQuestions(389);
            new Assignment().releaseGrades(389,"Release Grade for All");


            //Row No - 396 : Score bar height should be per calculation excluding older version question over assignment responses page

            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getViewGrade_link().click();
            Thread.sleep(7000);
            List<WebElement> rectElementList = driver.findElement(By.cssSelector("g[class = 'highcharts-series highcharts-tracker']")).findElements(By.tagName("rect"));
            rectElementList.get(7).getAttribute("height");
            Assert.assertEquals(rectElementList.get(7).getAttribute("height"),"96","Score bar height should be per calculation excluding older version question over assignment responses page");


            //Row No - 397 : Score bar height should be per calculation excluding older version question and include newer version question over current assignment page
            new Navigator().NavigateTo("Current Assignments");
            Thread.sleep(7000);
            rectElementList = driver.findElement(By.cssSelector("g[class = 'highcharts-series highcharts-tracker']")).findElements(By.tagName("rect"));
            Assert.assertEquals(rectElementList.get(7).getAttribute("height"),"96","Score bar height should be per calculation excluding older version question over assignment responses page");



            //Row No - 398 : Total grade over engagement report should exclude older version question and include newer version question
            new Navigator().NavigateTo("Engagement Report");
            actual = engagementReport.getStudTotalGrade().getText().trim();
            expected = "60.0%";
            Assert.assertEquals(actual,expected,"Total grade over engagement report should exclude older version question and include newer version question");

            //Row No - 399 : Score displayed over gradebook page should exclude older version question
            new Navigator().NavigateTo("Gradebook");
            actual = gradebook.getOverAllScore().get(1).getText().trim();
            expected = "60%";
            Assert.assertEquals(actual,expected,"Score displayed over gradebook page should exclude older version question");


            //Row No - 400 : Question attempted count over dashboard tile should exclude older version question and include newer version question



            //Row No - 401 : Question attempted count over engagement report should exclude older version question and include newer version question
            new Navigator().NavigateTo("Engagement Report");
            actual = engagementReport.getQuestionAttempted().getText().trim();
            expected = "5";
            Assert.assertEquals(actual,expected,"Question attempted count over engagement report should exclude older version question and include newer version question");



            //Row No - 402 : Performance tile over dashboard should exclude older version question and include newer version question*/

            new Navigator().NavigateTo("Dashboard");
            actual = dashboard.getAvgPerformance().getText();
            actual = actual.substring(19);
            expected = "60%";
            Assert.assertEquals(actual,expected,"Performance tile over dashboard should exclude older version question and include newer version question");


            //Row No - 403 : Assignment tile over dashboard should exclude older version question and include newer version question
            actual = dashboard.questionPerformance.getText();
            String actualTokens[] = actual.split(":");
            actual = actualTokens[1];
            expected  = "60%";
            Assert.assertEquals(actual,expected,"Assignment tile over dashboard should exclude older version question and include newer version question");



        }catch(Exception e){
            Assert.fail("Exception in the testcase 'checkCourseStreamEntryFrDiscussionAddedToOlderVersionQuestion' in class 'QuestionVersions'",e);
        }

    }







    @Test(priority = 7)
    public void checkWhenQuestionIsOlderVersionAfterSomeStudentsHaveAttemptedToBeOlderQuestion_Gradable(){
        try{
            //Row No - 405
            int dataIndex = 405;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "405");//fetch assignment name from text data
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            Questions questions = PageFactory.initElements(driver, Questions.class);
            DiagnosticTab diagnosticTab = PageFactory.initElements(driver, DiagnosticTab.class);
            ProficiencyReport proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);
            MyActivity myActivity = PageFactory.initElements(driver, MyActivity.class);
            PerformanceSummary performanceSummary = PageFactory.initElements(driver, PerformanceSummary.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);



            new Assignment().create(dataIndex); //create assignment
            new Assignment().addQuestions(dataIndex, "essay", "");
            new Assignment().addQuestions(dataIndex, "essay", "");
            new Assignment().addQuestions(dataIndex, "multiplechoice", "");
            new Assignment().addQuestions(dataIndex, "multiplechoice", "");

            //1. Login as instructor having 4 students in class.
            new LoginUsingLTI().ltiLogin("405");//log in as an Instructor
            new LoginUsingLTI().ltiLogin("405_1");//log in as Student1
            new LoginUsingLTI().ltiLogin("405_2");//log in as Student2
            new LoginUsingLTI().ltiLogin("405_3");//log in as Student3
            new LoginUsingLTI().ltiLogin("405_4");//log in as Student4


            //2. Assign a gradable assignment having auto graded and manually graded question
            new LoginUsingLTI().ltiLogin("405");//log in as an Instructor
            new Assignment().assignToStudent(dataIndex);// Assign the assignment to class
            Thread.sleep(2000);


            //4. Ensure that student 2 has started the assignment but has not attempted the "question to be older version"
            new LoginUsingLTI().ltiLogin("405_2");//login as student2
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            new Click().clickBycssselector("span[class='ls-assignment-name instructor-assessment-review']");//click on the assignment name
            new WebDriverWait(driver, 300).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            //new AttemptQuestion().trueFalse(false, "correct", 405);
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);
            new AttemptQuestion().essay(false, "correct", 405);//attempt incorrect
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);
            new Assignment().nextButtonInQuestionClick(); //click on the next button


            new LoginUsingLTI().ltiLogin("405_3");//login as student3
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            new Click().clickBycssselector("span[class='ls-assignment-name instructor-assessment-review']");//click on the assignment name
            new WebDriverWait(driver, 300).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().trueFalse(false, "correct", 405);
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);
            new AttemptQuestion().essay(false, "correct", 405);//attempt incorrect
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);



            new LoginUsingLTI().ltiLogin("405_4");//login as student4
            new Assignment().submitAssignmentAsStudent(405);//submit the Assignment





            //7. Login as CMS user and create a new version of the question and publish it
            String questiontext = createVersionsForQuestion_staticAssessmnet(assessmentName);
            publishQuestion();
            //8. Login as instructor and Go to assignment responses page

            new LoginUsingLTI().ltiLogin("405");//log in as an Instructor
            new Navigator().NavigateTo("Current Assignments");
            Thread.sleep(5000);
            new Click().clickByclassname("ls-grade-book-assessment");
            Thread.sleep(9000);

            //Row No  - 405 : Questions column for older version question should appear at the end

            //Row No - 451: Expected - Questions column for older version question should appear at the end
            boolean lastQuestionLabel = assignmentResponsesPage.olderVersionQuestionPosition.isDisplayed();
            Assert.assertEquals(lastQuestionLabel, true, "Questions column for older version question should appear at the end");

            //Row No - 452: Expected - - icon should appear under older version question column for student 1 and 2
            if(!(driver.findElement(By.xpath("((//div[@class = 'idb-question-score-wrapper'])[1]//div[@class='idb-gradebook-content-coloumn-not-attempted'])[6]")).isDisplayed())){
                Assert.fail("- icon should appear under older version question column for student 1");
            }

            if(!(driver.findElement(By.xpath("(//div[@class = 'idb-question-score-wrapper'])[2]//span[@qlabelindex='Q6']")).isDisplayed())){
                Assert.fail("- icon should appear under older version question column for student 2");

            }

            //Row No - 454 : Tick mark should be displayed under older version question column for student 3
            if(!(driver.findElement(By.xpath("(//div[@class = 'idb-question-score-wrapper'])[3]//span[@qlabelindex='Q6']")).isDisplayed())){
                Assert.fail("Tick mark should be displayed under older version question column for student 3");

            }

            String tickMark = driver.findElement(By.xpath("(//div[@class = 'idb-question-score-wrapper'])[3]//span[@qlabelindex='Q6']")).getText();
            System.out.println("TickMArk:" + tickMark);
            if (!tickMark.equals("1.0"))
                Assert.fail("Tick mark should be displayed under older version question column for student 3");


            //Row No - 456 : Expected  : Preview opened from assignment response page should not contain older version question
            new Click().clickBycssselector("span[class='ls-assignment-name instructor-assessment-review']");
            Thread.sleep(5000);
            boolean isOlderVersionQuestion = false;
            List<WebElement>questionsElementsList = driver.findElements(By.className("control-label"));
            for(int a=0;a<questionsElementsList.size();a++){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",questionsElementsList.get(a));
                if(questionsElementsList.get(a).getText().trim().equals(questiontext)){
                    isOlderVersionQuestion = true;
                    break;
                }
            }

            if(isOlderVersionQuestion ==true){
                Assert.fail("Preview opened from assignment response page should not contain older version question and");
            }


            isOlderVersionQuestion = false;
            questionsElementsList = driver.findElements(By.className("control-label"));
            System.out.println("questionsElementsList size : " + questionsElementsList.size());
            for(int a=0;a<questionsElementsList.size();a++){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",questionsElementsList.get(a));
                Thread.sleep(1000);
                System.out.println("questionsElementsList.get(a).getText().trim() : " + questionsElementsList.get(a).getText().trim());
                if(questionsElementsList.get(a).getText().trim().equals("V1True False "+questiontext)){
                    isOlderVersionQuestion = true;
                    break;
                }
            }

            if(isOlderVersionQuestion ==false){
                Assert.fail("Preview opened from assignment response page should show newer version");
            }

            //Row No - 457 : Preview opened from current assignment page should not contain older version question
            new Navigator().NavigateTo("Current Assignments");
            new Click().clickBycssselector("span[class='ls-assignment-name instructor-assessment-review']");//click on the assignment name
            Thread.sleep(5000);
            isOlderVersionQuestion = false;
            questionsElementsList = driver.findElements(By.className("control-label"));
            for(int a=0;a<questionsElementsList.size();a++){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",questionsElementsList.get(a));
                if(questionsElementsList.get(a).getText().trim().equals(questiontext)){
                    isOlderVersionQuestion = true;
                    break;
                }
            }

            if(isOlderVersionQuestion ==true){
                Assert.fail("Preview opened from assignment response page should not contain older version question and");
            }


            isOlderVersionQuestion = false;
            questionsElementsList = driver.findElements(By.className("control-label"));
            System.out.println("questionsElementsList size : " + questionsElementsList.size());
            for(int a=0;a<questionsElementsList.size();a++){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",questionsElementsList.get(a));
                Thread.sleep(1000);
                if(questionsElementsList.get(a).getText().trim().equals("V1True False "+questiontext)){
                    isOlderVersionQuestion = true;
                    break;
                }
            }

            if(isOlderVersionQuestion ==false){
                Assert.fail("Preview opened from assignment response page should show newer version");
            }



            new LoginUsingLTI().ltiLogin("405_1");//login as student1
            new Assignment().submitAssignmentAsStudent(405);
            new LoginUsingLTI().ltiLogin("405_2");//login as student2
            new Assignment().submitAssignmentAsStudent(405);
            new LoginUsingLTI().ltiLogin("405_3");//login as student3
            new Assignment().submitAssignmentAsStudent(405);

            new LoginUsingLTI().ltiLogin("405");//login as instructor
            new Navigator().NavigateTo("Current Assignments"); //navigate to current assignment

            //Tc row no 180
            String status = currentAssignments.status_reviewInProgress.getText();
            Assert.assertEquals(status, "Needs Grading", "status is not showing review in progress");

            //Tc row no 181
            String submitBoxCount = currentAssignments.getSubmittedBoxCount().getText();
            Assert.assertEquals(submitBoxCount, "4", "submitbox is not displaying the total student count");

            //Tc row no 182
            currentAssignments.getViewGrade_link().click();//click on view student responses
            Thread.sleep(5000);

            //Tc row no 183 and 184
            String xIconForStudent = assignmentResponsesPage.xIcon_deActivatedQuestion.getAttribute("title").trim();
            if (!xIconForStudent.contains("Question not delivered"))
                Assert.fail("Marks based on student is not displayed under deactivated question column for student 3 and 4");

            //Tc row no 185
            String marksForStudent1 = assignmentResponsesPage.deActivatedQuestion_Text.getText().trim();
            if (!marksForStudent1.contains("1.0"))
                Assert.fail("Marks based on student is not displayed under deactivated question column for student 3 and 4");

            //Tc row no 187 & 188

            new Assignment().provideGradeToStudentForMultipleQuestions(405);

            new LoginUsingLTI().ltiLogin("405");//login as instructor
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            currentAssignments.getViewGrade_link().click(); //click on the view student response*/
            enterGradeOnParticularQuestion(2, 5, "0.7");
            Thread.sleep(5000);


            //Tc row no 423
            new Assignment().releaseGrades(405, "Release Grade for All");

            //Tc row no 425
            String xIconForStudent2 = assignmentResponsesPage.xIcon_deActivatedQuestion.getAttribute("title").trim();
            if (!xIconForStudent2.contains("Question not delivered"))
                Assert.fail("View resposnes for deactivated question is not appear for student 1 and 2");

            //Tc row no 194
            WebElement thirdQuestion = driver.findElement(By.xpath("(//span[@class='idb-gradebook-assignment-username'])[last()]/ancestor::div[2]/div[@class='idb-question-score-wrapper']/div[last()]/span"));
            Thread.sleep(5000);
            new MouseHover().mouserhoverbywebelement(thirdQuestion);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link")));

            //Tc row no 198
            new Navigator().NavigateTo("Engagement Report");
            String attemptedQuestion1 = assignmentResponsesPage.attemptedQuestion.get(0).getText().trim();
            if (!attemptedQuestion1.equals("5"))
                Assert.fail("Question attempted count over engagement report should exclude deactivated question for student 1,2");

            String attemptedQuestion2 = assignmentResponsesPage.attemptedQuestion.get(1).getText().trim();
            if (!attemptedQuestion2.equals("6"))
                Assert.fail("Question attempted count over engagement report should exclude deactivated question for student 1,2");

            //Tc row no 431
            //Question attempted count over engagement report should exclude older version question for student 1,2 and include older version question for student 3,4
            String attemptedQuestion3 = assignmentResponsesPage.attemptedQuestion1.get(0).getText().trim();
            if (!attemptedQuestion3.equals("6"))
                Assert.fail("Question attempted count over engagement report should exclude older version question for student 3,4");

            String attemptedQuestion4 = assignmentResponsesPage.attemptedQuestion1.get(1).getText().trim();
            if (!attemptedQuestion4.equals("5"))
                Assert.fail("Question attempted count over engagement report should exclude older version question for student 3,4");

        }catch(Exception e){
            Assert.fail("Exception in the testcase 'checkCourseStreamEntryFrDiscussionAddedToOlderVersionQuestion' in class 'QuestionVersions'",e);
        }

    }



    @Test(priority = 8, enabled = true)
    public void  courseStreamEntryForDiscussionAddedToOlderVersionQuestion() {
        try {
            //Tc row np 434
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "434");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "434");
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "434");

            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            new Assignment().create(434);
            new Assignment().addQuestions(434, "multiplechoice", "");
            new LoginUsingLTI().ltiLogin("434_1");//create student1
            new LoginUsingLTI().ltiLogin("434_2");//create student2
            new LoginUsingLTI().ltiLogin("434");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            System.out.println("searchquestion : " + searchquestion);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            String questionName=driver.findElement(By.className("ls-search-question-text")).getText().trim();
            System.out.println("questionName:" + questionName);

            new AssignLesson().selectQuestionForCustomAssignment("434");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(434);//assign assignment
            new LoginUsingLTI().ltiLogin("434_1");//login as student1
            new Navigator().NavigateTo("Assignments");
            new Assignment().submitAssignmentAsStudent(434); //submit assignment
            Thread.sleep(2000);
            new ClickOnquestionCard().clickonquestioncard(0);
            assignments.getDiscussionTab().click();
            assignments.getNewButton().click();
            assignments.getEditBox().sendKeys("This is new discussion");
            assignments.getSubmit().click();
            new LoginUsingLTI().ltiLogin("434");//log in as instructor

            new OpenSearchPage().openSearchPage(); //open search page  through cms
            new OpenSearchPage().searchquestion(questionName);
            Thread.sleep(5000);

            String questionText = createVersionsForQuestion_Practice(questionName);
            publishQuestion();
            Thread.sleep(20000);
            new LoginUsingLTI().ltiLogin("434");//log in as instructor
            new Navigator().NavigateTo("Course Stream");
            //courseStreamPage.getJumpOut().click();//click on jumpOut icon
            new Click().clickBycssselector("span[class='ls-jumpout-icon-link ls-jumpout-icon']");
            Thread.sleep(4000);
            verifyMessage(202);



        } catch (Exception e) {
            Assert.fail("Exception in test case courseStreamEntryForDiscussionAddedToDeActivatedQuestion in class StudentSubmittedQs", e);
        }
    }




    @Test(priority = 9, enabled = true)
    public void  proficiencyReportForOlderVersionQuestion() {
        try {


            //Row No - 435 : Proficiency report for older version question

            /*Steps : "1. Login as instructor having 2 students in class.
            2. Assign a gradable custom assignment.
            3. Login as student1 and submit the assignment
            4. Login as CMS user
            5. Create a new version of the question
            6. Login as student2 and submit the assignment
            7. Login as instructor and release the grades
            8. Go to proficiency report
            "*/

            //Expected - Class performance by question should show the older version question bar in color based on student response

            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "435");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "435");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);

            new Assignment().create(4355);
            new Assignment().addQuestions(4355,"multiplechoice","");
            new Assignment().addQuestions(4355,"multipleselection","");
            new Assignment().addQuestions(4355,"textentry","");
            new Assignment().addQuestions(4355,"textdropdown","");
            new Assignment().addQuestions(4355,"multiplechoice","");
            new Assignment().addQuestions(4355,"multiplechoice","");
            new Assignment().addQuestions(4355,"multiplechoice","");
            new Assignment().addQuestions(4355,"multiplechoice","");
            new Assignment().addQuestions(4355,"multiplechoice","");
            new Assignment().addQuestions(4355,"multipleselection","");
            new Assignment().addQuestions(4355,"multipleselection","");
            new LoginUsingLTI().ltiLogin("435_1");//create student1
            new LoginUsingLTI().ltiLogin("435_2");//create student2
            new LoginUsingLTI().ltiLogin("435");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            String questionName=driver.findElement(By.className("ls-search-question-text")).getText().trim();
            System.out.println("questionName:"+questionName);

            new AssignLesson().selectQuestionForCustomAssignment("435");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(435);//assign assignment
            new LoginUsingLTI().ltiLogin("435_1");//login as student1
            new Navigator().NavigateTo("Assignments");
            new Assignment().submitAssignmentAsStudent(435); //submit assignment by student1

            new OpenSearchPage().openSearchPage(); //open search page  through cms
            new OpenSearchPage().searchquestion(questionName);
            Thread.sleep(5000);
            String questionText = createVersionsForQuestion_Practice(questionName);
            publishQuestion();
            new LoginUsingLTI().ltiLogin("435_2");//login as student2
            new Navigator().NavigateTo("Assignments");
            new Assignment().submitAssignmentAsStudent(435); //submit assignment by student2

            new LoginUsingLTI().ltiLogin("435");//log in as instructor
            new Assignment().releaseGrades(435,"Release Grade for All");


            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("435"); //login as instructor


        } catch (Exception e) {
            Assert.fail("Exception in test case proficiencyReportForDeActivatedQuestion in class StudentSubmittedQs", e);
        }
    }






    @Test(priority = 10, enabled = true)
    public void  assignmentPolicyWithGradeReleaseOption1() {
        try {


            //Row No - 436 : Assignment with policy having grade release option1

            /*Steps - "1. Login as instructor with 2 students in class
            2. Assign a gradable custom assignment with policy having grade release option 1
            3. Login as student1 and attempt to be deactivated question leaving assignment in progress
            4. Login as CMS user
            5. Create a new version of the question
            6. Login as student1 and 2 and submit the assignment.
            "*/

            //Expected - Colored bars based on responses should appear as soon as assignment is submitted

            String diaAssessment = ReadTestData.readDataByTagName("", "diaassessmentname", "436");
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "436");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "436");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "436");
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);

            new LoginUsingLTI().ltiLogin("436");//log in as instructor
            new LoginUsingLTI().ltiLogin("436_1");//log in as student1
            new LoginUsingLTI().ltiLogin("436_2");//log in as student2

            new Assignment().create(436); //create assignment
            new Assignment().addQuestions(436,"truefalse","");
            new Assignment().addQuestions(436,"truefalse","");
            new Assignment().addQuestions(436,"multiplechoice","");
            new Assignment().addQuestions(436,"multipleselection","");

            new LoginUsingLTI().ltiLogin("436");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");//policy1


            new LoginUsingLTI().ltiLogin("436");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            String questionName=driver.findElement(By.className("ls-search-question-text")).getText().trim();
            System.out.println("questionName:"+questionName);
            new AssignLesson().selectQuestionForCustomAssignment("436");
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later

            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(436);//assign assignment

            new LoginUsingLTI().ltiLogin("436_1");//login as student1
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            assignments.getAssignmentName().click();//click on the assignment name
            Thread.sleep(3000);
            new AttemptQuestion().multipleSelection(false,"correct",436);
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);
            new AttemptQuestion().trueFalse(false,"correct",436);
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);
            new AttemptQuestion().trueFalse(false,"correct",436);
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);


            //new Assignment().deActivateParticularQuestion(436,diaAssessment); //deactivate fourth position question
            String questionText = createVersionsForQuestion_Practice(questionName);
            publishQuestion();

            new LoginUsingLTI().ltiLogin("436_1");//log in as student1
            new Assignment().submitAssignmentAsStudent(436);

            new LoginUsingLTI().ltiLogin("436_2");//log in as student2
            new Assignment().submitAssignmentAsStudent(436);

            new LoginUsingLTI().ltiLogin("436_1");//log in as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            Thread.sleep(2000);
            int question1=assignments.getWidth().size();
            if(question1< 5){
                Assert.fail("scoreover assignment page is not included deleted question");
            }

            new LoginUsingLTI().ltiLogin("436_2");//log in as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            Thread.sleep(2000);
            int question2=assignments.getWidth().size();
            if(question2>5){
                Assert.fail("scoreover assignment page is not included deleted question");
            }
            new LoginUsingLTI().ltiLogin("436");//login as instructor
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            currentAssignments.getViewGrade_link().click(); //click on the view student response

            String deActivatedQuestionMarks1=new TextFetch().textfetchbyxpath("(//span[@class='idb-gradebook-assignment-username'])[position()<2]/ancestor::div[2]/div[@class='idb-question-score-wrapper']/div[last()]/span");
            Assert.assertEquals(deActivatedQuestionMarks1,"2.0","Deactivated question column has not contained value based on student response for student 1");

            //Tc row no 193
            String xIconForStudent2 = driver.findElement(By.xpath("(//span[@class='idb-gradebook-assignment-username'])[position()>1]/ancestor::div[2]/div[@class='idb-question-score-wrapper']/div[last()]/span")).getAttribute("title").trim();
            if (!xIconForStudent2.contains("Question not delivered"))
                Assert.fail("x icon is not displaying for student 2");

            //Once the bug : 14000 is fixed, continue with scripting


        } catch (Exception e) {
            Assert.fail("Exception in test case assignmentPolicyWithGradeReleaseOption1 in class StudentSubmittedQs", e);
        }
    }








    @Test(priority = 11, enabled = true)
    public void  assignmentPolicyWithGradeReleaseOption3() {
        try {
            //Row No - 444 : Assignment with policy having grade release option3


            /*Steps : "1. Login as instructor with 3 students in class
            2. Assign a gradable custom assignment with policy having grade release option 3(3 auto+2 manual)
            3. Login as student1 and submit the assignment
            4. Login as student2 and attempt to be older version question(manual question) leaving assignment in progress
            5. Login as CMS user
            6. Create a new version of the question
            7. Login as student2 and 3 and submit the assignment.
            8. Login as instructor.
            9. Fill in grades to all attempted question except older version question for student1/2
            10. Refresh the page
            "*/
            //Expected - Grades should not get released
            //The bug has been raised, Once it is fixed, continue with scripting

            String diaAssessment = ReadTestData.readDataByTagName("", "diaassessmentname", "444");
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "444");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "444");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "444");
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);

            new LoginUsingLTI().ltiLogin("444");//log in as instructor
            new LoginUsingLTI().ltiLogin("444_1");//log in as student1
            new LoginUsingLTI().ltiLogin("444_2");//log in as student2
            new LoginUsingLTI().ltiLogin("444_3");//log in as student3

            new Assignment().create(444); //create assignment
            new Assignment().addQuestions(444,"truefalse","");
            new Assignment().addQuestions(444,"essay","");
            new Assignment().addQuestions(444,"multiplechoice","");
            new Assignment().addQuestions(444,"writeboard","");


            new LoginUsingLTI().ltiLogin("444");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "");//policy3


            new LoginUsingLTI().ltiLogin("444");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            String questionName=driver.findElement(By.className("ls-search-question-text")).getText().trim();
            System.out.println("questionName:"+questionName);

            new AssignLesson().selectQuestionForCustomAssignment("444");
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later

            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(444);//assign assignment

            new LoginUsingLTI().ltiLogin("444_1");//login as student1
            new Assignment().submitAssignmentAsStudent(444);

            new LoginUsingLTI().ltiLogin("444_2");//log in as student2
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            assignments.getAssignmentName().click();//click on the assignment name
            new AttemptQuestion().writeBoard(false,"correct",444);
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);
            new AttemptQuestion().essay(false,"correct",444);
            new Assignment().nextButtonInQuestionClick(); //click on the next button
            Thread.sleep(2000);


            String questionText = createVersionsForQuestion_Practice(questionName);
            publishQuestion();


            new LoginUsingLTI().ltiLogin("444_2");//log in as student2
            new Assignment().submitAssignmentAsStudent(444);
            new LoginUsingLTI().ltiLogin("444_3");//log in as student3
            new Assignment().submitAssignmentAsStudent(444);

            //Once the bug : 14000 is fixed, continue with scripting

        } catch (Exception e) {
            Assert.fail("Exception in test case assignmentPolicyWithGradeReleaseOption3 in class QuestionVersions", e);
        }
    }










    @Test(priority = 12,enabled = true)
    public  void studentAttemptDeActivatedQuestionOfNonGradableAssignment() {

        try {
            /*Row No - 451 : "When question is older version after some students have attempted the ""to be older version question""
            with non gradable assignment"*/

            /*Steps : "1. Login as instructor having 4 students in class.
            2. Assign a non-gradable assignment
            3. Ensure that student 1 has not yet started the assignment
            4. Ensure that student 2 has started the assignment but has not attempted the ""question to be older version""
            5. Ensure that student 3 has started the assignment and has attempted the ""question to be older version""
            6. Ensure that student 4 has submitted the assignment.
            7. Login as CMS user and Create a new version of the question
            8. Login as instructor and Go to assignment responses page"*/

            //Expected - Questions column for older version question should appear at the end

            String diaAssessment = ReadTestData.readDataByTagName("", "diaassessmentname", "451");
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", "451");
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "451");
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "451");

            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);

            new LoginUsingLTI().ltiLogin("451"); //login as instructor
            new LoginUsingLTI().ltiLogin("451_1");//login as student1
            new LoginUsingLTI().ltiLogin("451_2");//login as student2
            new LoginUsingLTI().ltiLogin("451_3");//login as student3
            new LoginUsingLTI().ltiLogin("451_4");//login as student4

            new Assignment().create(451);
            new Assignment().addQuestions(451,"multiplechoice", "");
            new Assignment().addQuestions(451,"multiplechoice", "");
            new Assignment().addQuestions(451,"multipleselection", "");
            new Assignment().addQuestions(451,"textentry","");

            new LoginUsingLTI().ltiLogin("451");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            String questionName=driver.findElement(By.className("ls-search-question-text")).getText().trim();

            //2. Assign a gradable assignment
            new LoginUsingLTI().ltiLogin("451");//log in as an Instructor
            new Assignment().assignToStudent(451);// Assign the assignment to class
            Thread.sleep(20000);
            //3. Login as student2 and leave the assignment in progress without attempting "to be older version question"
            new LoginUsingLTI().ltiLogin("451_2");//log in as Student 2
            new Navigator().NavigateTo("Assignments");
            new Assignment().clickonAssignment(assessmentName);
            new Assignment().nextButtonInQuestionClick();
            new AttemptQuestion().multipleChoice(false,"correct",451_2);
            new Assignment().nextButtonInQuestionClick();


            //4. Login as student 3 and leaving the assignment in progress attempting "to be older version question"
            new LoginUsingLTI().ltiLogin("451_3");//log in as Student 3
            new Navigator().NavigateTo("Assignments");
            new Assignment().clickonAssignment(assessmentName);
            new AttemptQuestion().trueFalse(false,"correct",451_3);
            new Assignment().nextButtonInQuestionClick();


            //5. Login as student 4 and submit the assignment
            new LoginUsingLTI().ltiLogin("451_4");//log in as Student 4
            new Assignment().submitAssignmentAsStudent(451);


            /*6. Login as CMS user
            7. Create a new version of a question and publish it*/

            String questionText = createVersionsForQuestion_staticAssessmnet(assessmentName);
            publishQuestion();

            new LoginUsingLTI().ltiLogin("451"); //login as instructor
            new Navigator().NavigateTo("Current Assignments"); //navigate to current assignment
            new UIElement().waitAndFindElement(By.className("ls-grade-book-assessment"));
            currentAssignments.getViewGrade_link().click(); //click on the view student response

            //Row No - 451: Expected - Questions column for older version question should appear at the end
            boolean lastQuestionLabel = assignmentResponsesPage.olderVersionQuestionPosition.isDisplayed();
            Assert.assertEquals(lastQuestionLabel, true, "Questions column for older version question should appear at the end");

            //Row No - 452: Expected - - icon should appear under older version question column for student 1 and 2
            if(!(driver.findElement(By.xpath("((//div[@class = 'idb-question-score-wrapper'])[1]//div[@class='idb-gradebook-content-coloumn-not-attempted'])[6]")).isDisplayed())){
                Assert.fail("- icon should appear under older version question column for student 1");
            }

            if(!(driver.findElement(By.xpath("(//div[@class = 'idb-question-score-wrapper'])[2]//span[@qlabelindex='Q6']")).isDisplayed())){
                Assert.fail("- icon should appear under older version question column for student 2");

            }

            //Row No - 454 : Tick mark should be displayed under older version question column for student 3
            if(!(driver.findElement(By.xpath("(//div[@class = 'idb-question-score-wrapper'])[3]//span[@qlabelindex='Q6']")).isDisplayed())){
                Assert.fail("Tick mark should be displayed under older version question column for student 3");

            }

            String tickMark = driver.findElement(By.xpath("(//div[@class = 'idb-question-score-wrapper'])[3]//span[@qlabelindex='Q6']//span")).getCssValue("background-image").trim();
            System.out.println("TickMArk:" + tickMark);
            if (!tickMark.contains("/images/ls/check-mark.png"))
                Assert.fail("Tick mark should be displayed under older version question column for student 3");


            //Row No - 456 : Expected  : Preview opened from assignment response page should not contain older version question
            new Click().clickBycssselector("span[class='ls-assignment-name instructor-assessment-review']");
            Thread.sleep(5000);
            boolean isOlderVersionQuestion = false;
            List<WebElement>questionsElementsList = driver.findElements(By.className("control-label"));
            for(int a=0;a<questionsElementsList.size();a++){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",questionsElementsList.get(a));
                if(questionsElementsList.get(a).getText().trim().equals(questiontext)){
                    isOlderVersionQuestion = true;
                    break;
                }
            }

            if(isOlderVersionQuestion ==true){
                Assert.fail("Preview opened from assignment response page should not contain older version question and");
            }


            isOlderVersionQuestion = false;
            questionsElementsList = driver.findElements(By.className("control-label"));
            System.out.println("questionsElementsList size : " + questionsElementsList.size());
            for(int a=0;a<questionsElementsList.size();a++){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",questionsElementsList.get(a));
                Thread.sleep(1000);
                System.out.println("questionsElementsList.get(a).getText().trim() : " + questionsElementsList.get(a).getText().trim());
                if(questionsElementsList.get(a).getText().trim().equals("V1True False "+questiontext)){
                    isOlderVersionQuestion = true;
                    break;
                }
            }

            if(isOlderVersionQuestion ==false){
                Assert.fail("Preview opened from assignment response page should show newer version");
            }

            //Row No - 457 : Preview opened from current assignment page should not contain older version question
            new Navigator().NavigateTo("Current Assignments");
            new Click().clickBycssselector("span[class='ls-assignment-name instructor-assessment-review']");//click on the assignment name
            Thread.sleep(5000);
            isOlderVersionQuestion = false;
            questionsElementsList = driver.findElements(By.className("control-label"));
            for(int a=0;a<questionsElementsList.size();a++){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",questionsElementsList.get(a));
                if(questionsElementsList.get(a).getText().trim().equals(questiontext)){
                    isOlderVersionQuestion = true;
                    break;
                }
            }

            if(isOlderVersionQuestion ==true){
                Assert.fail("Preview opened from assignment response page should not contain older version question and");
            }


            isOlderVersionQuestion = false;
            questionsElementsList = driver.findElements(By.className("control-label"));
            System.out.println("questionsElementsList size : " + questionsElementsList.size());
            for(int a=0;a<questionsElementsList.size();a++){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",questionsElementsList.get(a));
                Thread.sleep(1000);
                if(questionsElementsList.get(a).getText().trim().equals("V1True False "+questiontext)){
                    isOlderVersionQuestion = true;
                    break;
                }
            }

            if(isOlderVersionQuestion ==false){
                Assert.fail("Preview opened from assignment response page should show newer version");
            }



            /*Steps : "8. Submit the assignment by student1,2 and 3
            9. Login as instructor
            10. Go to current assignment page"*/

            new LoginUsingLTI().ltiLogin("451_1");//login as student1
            new Assignment().submitAssignmentAsStudent(451);
            new LoginUsingLTI().ltiLogin("451_2");//login as student2
            new Assignment().submitAssignmentAsStudent(451);
            new LoginUsingLTI().ltiLogin("451_3");//login as student3
            new Assignment().submitAssignmentAsStudent(451);

            new LoginUsingLTI().ltiLogin("451");//login as instructor
            new Navigator().NavigateTo("Current Assignments"); //navigate to current assignment


            //Row No - 458: Assignment status should be review in progress
            String status = currentAssignments.status_reviewInProgress.getText();
            Assert.assertEquals(status, "Review in Progress", "Assignment status should be review in progress");

            //Row No - 459: All student count should be displayed under submitted box
            String submitBoxCount = currentAssignments.getSubmittedBoxCount().getText();
            Assert.assertEquals(submitBoxCount, "4", "All student count should be displayed under submitted box");

            //Steps: Row No - 460: 11. Go to assignment responses page
            //Expected -  Older version question column should be available
            currentAssignments.getViewGrade_link().click();//click on view student responses
            lastQuestionLabel = assignmentResponsesPage.olderVersionQuestionPosition.isDisplayed();
            Assert.assertEquals(lastQuestionLabel, true, "Older version question column should be available");


            //Row No - 461 : x icon should be displayed under older version question column for student 1 and 4

            if(!(driver.findElements(By.xpath("//div[@class='idb-question-score-wrapper']//div[@class='idb-gradebook-content-coloumn-not-attempted']")).size()==2)){
                Assert.fail("x icon should be displayed under older version question column for student 1 and 4");
            }

            //Row No - 462 : Hover on x should display tooltip notifying the user that question was not delivered

            if(!(driver.findElements(By.xpath("//div[@class='idb-question-score-wrapper']//div[@class='idb-gradebook-content-coloumn-not-attempted']//span")).get(0).getAttribute("title").equals("Question not delivered"))){
                Assert.fail("Hover on x should display tooltip notifying the user that question was not delivered for Student 1");
            }


            if(!(driver.findElements(By.xpath("//div[@class='idb-question-score-wrapper']//div[@class='idb-gradebook-content-coloumn-not-attempted']//span")).get(1).getAttribute("title").equals("Question not delivered"))){
                Assert.fail("Hover on x should display tooltip notifying the user that question was not delivered for Student 4");
            }

            //Row No - 463 : Tick should be displayed for student 3 & 4

            if(!(driver.findElement(By.xpath("(//div[@class = 'idb-question-score-wrapper'])[3]//span[@qlabelindex='Q6']")).isDisplayed())){
                Assert.fail("Tick should be displayed for student 3");

            }

            tickMark = driver.findElement(By.xpath("(//div[@class = 'idb-question-score-wrapper'])[3]//span[@qlabelindex='Q6']//span")).getCssValue("background-image").trim();
            System.out.println("TickMArk:" + tickMark);
            if (!tickMark.contains("/images/ls/check-mark.png"))
                Assert.fail("Tick mark should be displayed under older version question column for student 3");


            tickMark = driver.findElement(By.xpath("(//div[@class = 'idb-question-score-wrapper'])[4]//span[@qlabelindex='Q6']//span")).getCssValue("background-image").trim();
            System.out.println("TickMArk:" + tickMark);
            if (!tickMark.contains("/images/ls/check-mark.png"))
                Assert.fail("Tick mark should be displayed under older version question column for student 4");


            //Row No - 464 :View resposnes for older version question should not appear for student 1






            //Row No - 465 :View resposnes for older version question should appear for student 3





            //Row No - 466 : Notification message should be displayed at the top as "Newer version exists for this question"
            new MouseHover().mouserhoverbyXpath("(//div[@class = 'idb-question-score-wrapper'])[3]//span[@qlabelindex='Q6']");
            Thread.sleep(1000);
            new Click().clickbyxpath("//div[@class='ls-view-response-link']");
            Thread.sleep(3000);
            actual = assignments.getNotificationMessage().getText();
            expected  = "Newer version exists for this question.";
            Assert.assertEquals(actual,expected,"Notification message should be displayed at the top as \"Newer version exists for this question\"");


            //Row No - 467 : Instructor should be able to add feedback to older version question
            currentAssignments.getFeedBack_textBox().sendKeys("This is a FeedbackText");
            currentAssignments.getSave_button().click();
            actual = currentAssignments.getSaveMessage().getText().trim();
            expected  = "Saved successfully.";
            Assert.assertEquals(actual,expected,"Instructor should be able to add feedback to older version question");


            new LoginUsingLTI().ltiLogin("451");//login as instructor
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            currentAssignments.getViewGrade_link().click(); //click on the view student response

            new Assignment().releaseGrades(451,"Release Feedback for All");

            //Expected 1 - Row No - 469 : Status of assignment should get updated to reviewed
            Thread.sleep(3000);
            String status1=assignmentResponsesPage.getReviewStatus().getAttribute("title");
            Assert.assertEquals(status1,"Reviewed","Reviewed status is not displaying after released feedback for all");

            //Expected 2 - Row No - 469 : Question attempted count over engagement report should exclude older version question for student 1,4 and include older version question for student 2 & 3

            new Navigator().NavigateTo("Engagement Report");
            String attemptedQuestion1 = assignmentResponsesPage.attemptedQuestion.get(0).getText().trim();
            if (!attemptedQuestion1.equals("5"))
                Assert.fail("Question attempted count over dashboard tile should exclude older version question for student 1,2 and include older version question for student 3");

            String attemptedQuestion2 = assignmentResponsesPage.attemptedQuestion.get(1).getText().trim();
            if (!attemptedQuestion2.equals("6"))
                Assert.fail("Question attempted count over engagement report should exclude deactivated question for student 1,2");

            //Tc row no 199
            String attemptedQuestion3 = assignmentResponsesPage.attemptedQuestion1.get(0).getText().trim();
            if (!attemptedQuestion3.equals("6"))
                Assert.fail("Question attempted count over engagement report should exclude deactivated question for student 3,4");

            String attemptedQuestion4 = assignmentResponsesPage.attemptedQuestion1.get(1).getText().trim();
            if (!attemptedQuestion4.equals("5"))
                Assert.fail("Question attempted count over engagement report should exclude deactivated question for student 3,4");

            //Row No - 472 : Performance tile over dashboard should exclude older version question for student 1,2 and include older version question for student 3

            new Navigator().NavigateTo("Dashboard");
            actual = driver.findElement(By.cssSelector("div[class='box avg-practice-performance']")).getText();
            System.out.println("actual : " + actual);
            actual = actual.substring(19);
            expected = "96%";
            if(!(actual.contains(expected))) {
            Assert.fail("Performance tile over dashboard should exclude older version question and include newer version question");
            }

        } catch (Exception e) {
            Assert.fail("Exception in testcase studentAttemptDeActivatedQuestionOfNonGradableAssignment of class StudentSubmittedQs", e);
        }
    }












    public void verifyMessage(int dataIndex)
    {
        DiagnosticTab diagnosticTab= PageFactory.initElements(driver,DiagnosticTab.class);
        try {

            String deActivatedQuestionMsg="Newer version exists for this question.";
            Assert.assertEquals(diagnosticTab.deActivatedMsg.getText().trim(),deActivatedQuestionMsg,"This question is deactivated and is no longer part of this assignment/practice is not showing");

            //Expected 3
            Thread.sleep(3000);
            boolean hidden=new BooleanValue().presenceOfElement(17,By.cssSelector("div[class='add-content-error show-content-issues-dialog']"));
            if(hidden==true)
                Assert.fail(" “Report Content Issue” icon is not hidden for a deactivated question.");
        } catch (InterruptedException e) {
            Assert.fail("Exception in method verifyMessage of class StudentSubmittedQs", e);
        }

    }


    public void olderVersionQuestionNotToBeDelivered(String questionText) {

        try {

        } catch (Exception e) {
            Assert.fail("Exception in the method 'olderVersionQuestionNotToBeDelivered' in the class 'QuestionVersions'", e);

        }
    }

    public void summaryDropDownCountToBeExcludedAndLabelOfOlderVersionQuestion(String countingText,String questionText) {
        CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

        try {

            //Expected -1 : Summary dropdown should exclude count and label of older version question
            //Expected - 3 : Newer version question should get delivered

            //Assert.assertEquals(currentAssignments.questionCount_dropDown.getText(),countingText,"The count in Summary dropdown is not matching");
            Thread.sleep(7000);
            currentAssignments.questionCount_dropDown.click();
            WebElement element=driver.findElements(By.className("s--check-enable")).get(driver.findElements(By.className("s--check-enable")).size() - 1);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(3000);
            /*element.click();
            currentAssignments.getLastQuestionInDropDown().click();*/
            driver.findElements(By.xpath("//div[@class = 'questions_navigation_index']//tr")).get(driver.findElements(By.xpath("//div[@class = 'questions_navigation_index']//tr")).size()-1).click();
            Thread.sleep(5000);
            Assert.assertEquals(currentAssignments.getQuestionText().getText(),(questionText+"V1"),"label of older version question");
            Assert.assertEquals(currentAssignments.getQuestionLabelText().getText(),"Q 5:","label of older version question is  not matching");

        } catch (Exception e) {
            Assert.fail("Exception in the method 'olderVersionQuestionNotToBeDelivered' in the class 'QuestionVersions'", e);

        }
    }




    public String createVersionsForQuestion_Practice(String questionText){
        String questionTextAfterNewVersion = null;



        try{
            new OpenSearchPage().openSearchPage();
            new OpenSearchPage().searchquestion(questionText);
            Actions action2 = new Actions(driver);
            Questions questions = PageFactory.initElements(driver, Questions.class);
            List<WebElement> we2 = driver.findElements(By.xpath("//div[@id = 'question-check-box-div']//label"));
            action2.moveToElement(we2.get(0)).build().perform();// Mouse Over on Edit icon on first question
            Thread.sleep(2000);

            List<WebElement> alllist1=driver.findElements(By.xpath("//div[@class='edit-question-content']"));
            Thread.sleep(2000);
            alllist1.get(0).click();//click on the edit Button
            Thread.sleep(2000);

            questions.getLink_New().click();//Click on link 'New'
            Thread.sleep(200);

            /*questions.getLink_Revisions().click();//Click on link 'Revisions'
            Thread.sleep(9000);*/




            Thread.sleep(2000);
            WebElement we=driver.findElement(By.id("questionRevisions"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",we);
            Thread.sleep(2000);


            questions.getLink_createNewVersion().click();//Click on link 'Create New Version'
            Thread.sleep(2000);
            questionTextAfterNewVersion = questions.getLabel_NewVersionQuestionText().getText();
            /*driver.findElement(By.className("question-set-add-text")).click();//Click on link 'New'
            driver.findElement(By.xpath("//div[text()='Revisions'")).click();//Click on link 'Revisions'*/
            System.out.println("questionTextAfterNewVersion : "+questionTextAfterNewVersion);


        }catch(Exception e){
            Assert.fail("Exception in the method 'createVersionsForQuestion' in the class 'QuestionVersions'",e);
        }
        return questionTextAfterNewVersion;
    }


    public String createVersionsForQuestion_staticAssessmnet(String assessmentName){
        String questionText  = null;
        try{
            startDriver("firefox");
            Questions questions = PageFactory.initElements(driver, Questions.class);
            new DirectLogin().directLoginWithCreditial("lspaces2","snapwiz",42);
           /* new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(1); //open first chapter*/
            new SelectCourse().selectcourse();
            //questions.getIcon_BioCourse().click();//Click on 'Bio Principles.. ' course
            //driver.findElement(By.xpath("//img[@alt = 'BIO Principles, First Edition WileyPLUS Learning Space Course']")).click();
            Thread.sleep(5000);
            questions.getLink_Ch3().click();//open Second chapter
            Thread.sleep(5000);
             selectInvisibleAssignment(assessmentName);

            questions.getLabel_questionText();
            //new DiagnosticTest().startTest(4);
            questions.getLink_New().click();//Click on link 'New'
            Thread.sleep(200);

            /*questions.getLink_Revisions().click();//Click on link 'Revisions'
            Thread.sleep(9000);*/




            Thread.sleep(2000);
          /*  WebElement we=driver.findElement(By.id("questionRevisions"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",we);*/
            Thread.sleep(2000);
            //new Click().clickByid("questionRevisions");
            questions.getLink_Revisions().click();
            //new Click().clickByclassname("question-options-text");

            questionText = questions.getLabel_NewVersionQuestionText().getText();
            questions.getLink_createNewVersion().click();//Click on link 'Create New Version'
            Thread.sleep(2000);
            questions.getLabel_NewVersionQuestionText().click();
            Thread.sleep(2000);
            questions.getLabel_NewVersionQuestionText().sendKeys("V1");
            /*driver.findElement(By.className("question-set-add-text")).click();//Click on link 'New'
            driver.findE-rawlement(By.xpath("//div[text()='Revisions'")).click();//Click on link 'Revisions'*/
            System.out.println("QuestionText 1 : "+questionText);


        }catch(Exception e){
            Assert.fail("Exception in the method 'createVersionsForQuestion' in the class 'QuestionVersions'",e);
        }
        return questionText;
    }






    public String createVersionsForQuestion(){
        String questionText  = null;
        try{
            startDriver("firefox");
            Questions questions = PageFactory.initElements(driver, Questions.class);
            new DirectLogin().directLoginWithCreditial("lspaces2","snapwiz",42);
            /*new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(1); //open first chapter*/
            new SelectCourse().selectcourse();
            //questions.getIcon_BioCourse().click();//Click on 'Bio Principles.. ' course
            //driver.findElement(By.xpath("//img[@alt = 'BIO Principles, First Edition WileyPLUS Learning Space Course']")).click();
            Thread.sleep(5000);
            questions.getLink_Ch3().click();//open Second chapter
            Thread.sleep(5000);

            //new TopicOpen().chapterOpen(0); //open first chapter
            questions.getLink_diagnosticTest().click();//Click on diagnostic test in the chapter
            Thread.sleep(9000);
            questions.getLabel_questionText();
            //new DiagnosticTest().startTest(4);
            questions.getLink_New().click();//Click on link 'New'
            Thread.sleep(200);
            /*questions.getLink_Revisions().click();//Click on link 'Revisions'
            Thread.sleep(9000);*/




            Thread.sleep(2000);
          /*  WebElement we=driver.findElement(By.id("questionRevisions"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",we);*/
            Thread.sleep(2000);
            new Click().clickByid("questionRevisions");

            questionText = questions.getLabel_NewVersionQuestionText().getText();
            questions.getLink_createNewVersion().click();//Click on link 'Create New Version'
            Thread.sleep(2000);
            questions.getLabel_NewVersionQuestionText().click();
            Thread.sleep(2000);
            questions.getLabel_NewVersionQuestionText().sendKeys("V1");
            /*driver.findElement(By.className("question-set-add-text")).click();//Click on link 'New'
            driver.findE-rawlement(By.xpath("//div[text()='Revisions'")).click();//Click on link 'Revisions'*/
            System.out.println("QuestionText 1 : "+questionText);


        }catch(Exception e){
            Assert.fail("Exception in the method 'createVersionsForQuestion' in the class 'QuestionVersions'",e);
        }
        return questionText;
    }

    public void publishQuestion(){
        try{
            Questions questions = PageFactory.initElements(driver, Questions.class);
            questions.getComboBox_Draft().click();//Click on Combo box 'Draft'
            Thread.sleep(2000);
            questions.getLink_ReadyToPublish().click();//Click on 'Ready to Publish' option
            Thread.sleep(2000);
            questions.getButton_Save().click();////Click on 'Save' Button
            Thread.sleep(5000);
        }catch(Exception e){
            Assert.fail("Exception in the method 'publishQuestion' in the class 'QuestionVersions'",e);
        }
    }

    public void postDiscussion4LastQuestionInQuestionCard(String postMessage){
        //String questionTextBefore
        try{
            Questions questions = PageFactory.initElements(driver, Questions.class);
            WebElement element=driver.findElements(By.className("question-card-question-content")).get(driver.findElements(By.className("question-card-question-content")).size()-1);
            driver.findElements(By.className("question-card-question-content")).get(driver.findElements(By.className("question-card-question-content")).size()-1).getText();
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(500);
            element.click();
            Thread.sleep(3000);
            questions.getTab_Discussion().click();
            Thread.sleep(1000);
            new Discussion().postDiscussion(postMessage);
        }catch(Exception e){
            Assert.fail("Exception in the method 'publishQuestion' in the class 'QuestionVersions'",e);
        }
    }


    public void postDiscussion4LastQuestionInQuestionCard_static(String postMessage){
        //String questionTextBefore
        try{
            Questions questions = PageFactory.initElements(driver, Questions.class);
            WebElement element=driver.findElements(By.className("question-card-question-content")).get(driver.findElements(By.className("question-card-question-content")).size()-1);
            driver.findElements(By.className("question-card-question-content")).get(driver.findElements(By.className("question-card-question-content")).size()-1).getText();
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(500);
            element.click();
            Thread.sleep(3000);
            questions.getTab_Discussion().click();
            Thread.sleep(1000);
            new Discussion().postDiscussion(postMessage);
        }catch(Exception e){
            Assert.fail("Exception in the method 'publishQuestion' in the class 'QuestionVersions'",e);
        }
    }


    public void showOlderQuestionOnRightCardAndOriginalEntry(String questionText){
        try{
            //Add remaining Code also
            WebElement element=driver.findElements(By.className("question-card-question-content")).get(driver.findElements(By.className("question-card-question-content")).size()-1);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
            Thread.sleep(1000);
            actual = element.getAttribute("title").trim();
            expected = questionText;
            Assert.assertEquals(actual,expected,"Course proficiency summary page is not showing the older version of question on the right card ");


            String ex = element.getCssValue("color");
            System.out.println("Ex : " + ex);
        }catch(Exception e){
            Assert.fail("Exception in the method 'showOlderQuestionOnRightCardAndOriginalEntry' in the class 'QuestionVersions'",e);
        }
    }


    public void showOlderQuestionOnRightCardAndOriginalEntry_staticAssesment(String questionText){
        try{
            //Add remaining Code also
            WebElement element=driver.findElement(By.className("question-card-question-content"));
            //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
            Thread.sleep(1000);
            actual = element.getAttribute("title").trim();
            expected = questionText;
            Assert.assertEquals(actual,expected,"Course proficiency summary page is not showing the older version of question on the right card ");


            String ex = element.getCssValue("color");
            System.out.println("Ex : " + ex);
        }catch(Exception e){
            Assert.fail("Exception in the method 'showOlderQuestionOnRightCardAndOriginalEntry_staticAssesment' in the class 'QuestionVersions'",e);
        }
    }


    public void showOlderQuestionOnRightCardAndOriginalEntry_PracticeTest(String questionText){
        try{
            //Add remaining Code also
            /*WebElement element=driver.findElements(By.className("question-card-question-content")).get(driver.findElements(By.className("question-card-question-content")).size()-1);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
            Thread.sleep(1000);
            actual = element.getAttribute("title").trim();
            expected = questionText;
            Assert.assertEquals(actual,expected,"Course proficiency summary page is not showing the older version of question on the right card ");*/

            if(!(driver.getPageSource().contains(questionText))){
                Assert.fail("Course proficiency summary page is not showing the older version of question on the right card");
            }

        }catch(Exception e){
            Assert.fail("Exception in the method 'showOlderQuestionOnRightCardAndOriginalEntry' in the class 'QuestionVersions'",e);
        }
    }

    public void clickOnLastQuestionInQuestionCard(){
        try{
            WebElement element=driver.findElements(By.className("question-card-question-content")).get(driver.findElements(By.className("question-card-question-content")).size()-1);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",element);
            Thread.sleep(1000);
            element.click();
            Thread.sleep(3000);
        }catch(Exception e){
            Assert.fail("Exception in the method 'clickOnLastQuestionInQuestionCard' in the class 'QuestionVersions'",e);
        }
    }



    public void validateOutDatedQuestion(String questionText){
        try{
            Questions questions = PageFactory.initElements(driver, Questions.class);

            //Expected - 1 : Student should get navigated to older version of question
            actual = questions.getLabel_QuestionText().getText().trim();
            expected = questionText;
            Assert.assertEquals(actual, expected, "Student is not navigated to older version of question");

            //Expected - 2 : Notification Message should be shown on the top of the question as "This question is outdated. Please contact your instructor for any score related issue"
            actual = questions.getMsg_Notification().getText();
            expected = "This question is outdated. Please contact your instructor for any score related issues.";
            Assert.assertEquals(actual,expected,"Notification Message should be shown on the top of the question as \"This question is outdated. Please contact your instructor for any score related issue\"");


        }catch(Exception e){
            Assert.fail("Exception in the method 'clickOnLastQuestionInQuestionCard' in the class 'QuestionVersions'",e);
        }
    }

    public void enterGradeOnParticularQuestion(int studentIndex,int questionIndex,String sendKey)
    {
        try {
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            List<WebElement> menuitem =driver.findElements(By.xpath("//span[@class='idb-gradebook-assignment-username']"));
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(menuitem.get(studentIndex));
            Thread.sleep(2000);
            List<WebElement> enterGradeLink = driver.findElements(By.id("idb-grade-now-link"));
            Thread.sleep(2000);
            System.out.println("studentIndex : " + studentIndex);
            //enterGradeLink.get(studentIndex).click();
            new Click().clickByElement(enterGradeLink.get(studentIndex));
            List<WebElement> gradeBox = driver.findElements(By.xpath("//input[@class='idb-grade-points']"));
            Thread.sleep(2000);
            gradeBox.get(questionIndex).clear();
            currentAssignments.clearGrade_textBox.sendKeys(sendKey);
            Thread.sleep(2000);
            driver.findElement(By.xpath("//html/body")).click();
        } catch (InterruptedException e) {
            Assert.fail("Exception in method enterGradeOnParticularQuestion");
        }

    }


    public void deActivateStaticAssessment(int dataIndex)
    {
        try {
            Actions action2 = new Actions(driver);
            List<WebElement> we2 = driver.findElements(By.xpath("//div[@id = 'question-check-box-div']//label"));
            action2.moveToElement(we2.get(0)).build().perform();// Mouse Over on Edit icon on first question
            Thread.sleep(2000);
            action2.moveToElement( driver.findElement(By.xpath("//div[@class='edit-question-content']"))).click().build().perform();
            Thread.sleep(2000);
            new Click().clickByid("questionOptions"); //click on question option
            Thread.sleep(2000);
            new Click().clickByid("questionRevisions"); // click on revisions
            Thread.sleep(2000);
            new Click().clickByid("cms-question-revision-deactivate-button");//click on the deactivate button


        } catch (InterruptedException e) {
            Assert.fail("Exception in method deActivateStaticAssessment of class StudentSubmittedQs",e);
        }
    }

    public void deActivateQuestionInAssignmentReview(int dataIndex,String questionName)
    {
        AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage.class);

        List<WebElement> questionNames=assignmentResponsesPage.questionText_assignmentReviewPage;
        boolean found=false;
        for(WebElement str:questionNames)
        {
            if(str.getText().contains(questionName)) {
                found = true;
            }
        }
        if(found == true)
            Assert.fail("Preview opened from current/assignment response page has contained deactivated question ");

    }


    public void selectInvisibleAssignment(String assignmentname)
    {
        try
        {
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@title='"+assignmentname+"']")));
            List <WebElement> element = driver.findElements(By.xpath(".//*[@title='" + assignmentname + "']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element.get(element.size()-1));
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",element.get(element.size()-1));
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase selectInvisibleAssignment in class SelectCourse",e);
        }
    }

}
