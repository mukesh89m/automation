package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R198;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.QuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.CourseOutline;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.Lesson;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.Summary;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.DiagnosticTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.MetacognitiveReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.MostChallengingReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.ProficiencyReport;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

/**
 * Created by shashank on 04-02-2015.
 * instructorLoginToCheckQuestionCreated (2nd test) execution is dependent on createNewChapterAndCheckRelatedCases (1st test)
 */
public class TakeTestAssignment extends Driver {
   @Test(priority = 1,enabled = true)
    public void createNewChapterAndCheckRelatedCases() {
        try {
            String questiontextPractice = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(62));
            Lesson lesson = PageFactory.initElements(driver, Lesson.class);
            Summary summary = PageFactory.initElements(driver, Summary.class);
            ManageContent manageContent=PageFactory.initElements(driver,ManageContent.class);
            CourseOutline courseOutline=PageFactory.initElements(driver,CourseOutline.class);

            DiagnosticTab diagnosticTab=PageFactory.initElements(driver,DiagnosticTab.class);
            String assesmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(62));
            String chapterName = ReadTestData.readDataByTagName("", "newChapterName", Integer.toString(62));
            WebDriverWait wait=new WebDriverWait(driver,200);
            //Create chapter under Course outline
            new Assignment().createChapter(62,1);
            //create Diagnostic assessment under above created chapter
             new Assignment().create(62);
             new Assignment().addQuestionsWithCustomizedQuestionText(62, "truefalse", "", 4);
            //CMS login

            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            courseOutline.courseOutline.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'"+chapterName+"')]")));
            Actions actions=new Actions(driver);
            //publish chapter
             int size=driver.findElements(By.xpath("//div[contains(text(),'"+chapterName+"')]")).size();
            actions.moveToElement(driver.findElements(By.xpath("//div[contains(text(),'"+chapterName+"')]")).get(size-1)).build().perform();
            Thread.sleep(500);
            courseOutline.editButtonAtChapter.click();
            Thread.sleep(500);
            courseOutline.checkBoxToPublishChapter.click();
            courseOutline.saveButton.click();
            Thread.sleep(1000);
            courseOutline.saveMyChanges.click();
            Thread.sleep(2000);
            //LTI login as a student to take diagnostic test
            new LoginUsingLTI().ltiLogin("62");
            new Navigator().NavigateTo("e-Textbook"); //navigate to etextbook
           // WebDriverWait wait=new WebDriverWait(driver,200);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='toc-sprite search-course-stream ui-autocomplete-input']")));
            Thread.sleep(9000);
            new TopicOpen().openLastChapter();
            new Navigator().NavigateToOrion();
            new DiagnosticTest().startTest(2);
            new DiagnosticTest().attemptAllCorrect(0,false,false);
            //check the number of question correct
            Assert.assertEquals(diagnosticTab.questionCountUnderPerformace.getText(),"5");
            List<WebElement> questionListOnPerformancePage=diagnosticTab.questionCardList;
            if(!(questionListOnPerformancePage.size()==5)) {
                Assert.fail("Question count on performance page After Diagnostic test not same");
            }
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(62));

                for(WebElement questionList:questionListOnPerformancePage)
                {
               Assert.assertTrue(questionList.getText().contains(questiontext),"Text of the question dose not match");
            }
            //CMS login
             new DirectLogin().CMSLogin();
             //create Adaptive Component Practice under above created chapter
             new Assignment().create(77);
             new Assignment().addQuestionsWithCustomizedQuestionText(77, "truefalse", "", 4);
            //Lti login as a student to take practice test
            new LoginUsingLTI().ltiLogin("62");
            (new Navigator()).NavigateTo("e-Textbook");//navigate to e-textbook
            new TopicOpen().openLastChapter();
            (new PracticeTest()).startTest(); //start practice test
            int count = 0;
            for(int i = 0; i < 5; i++) {
                (new PracticeTest()).AttemptCorrectAnswer(0,null);
                count++;
            }
            //CMS login
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
          //  new Assignment().createChapter(62);
            new Assignment().create(62);
            //create Static practice questions
            new Assignment().addQuestionsWithCustomizedQuestionText(62, "truefalse", "", 14);
            //CMS login to attempt Static practice test
            new LoginUsingLTI().ltiLogin("62");
           (new Navigator()).NavigateTo("e-Textbook");//navigate to e-textbook
            new TopicOpen().openLastChapter();
            Thread.sleep(1000);
            new SelectCourse().selectInvisibleAssignment(assesmentName);
            new AttemptTest().StaticTestWithConfidence(3);
            Assert.assertEquals(diagnosticTab.questionCount.getText(),"15");
            List<WebElement> questionListOnPerformancePagePractice=diagnosticTab.questionCardList;
            if(!(questionListOnPerformancePagePractice.size()==15)) {
                Assert.fail("Question count on performance page After Diagnostic test not same");
            }

                Assert.assertTrue(questionListOnPerformancePagePractice.get(1).getText().contains(questiontextPractice),"Text of the question dose not match");


            ProficiencyReport proficiencyReport=PageFactory.initElements(driver,ProficiencyReport.class);
            MetacognitiveReport metacognitiveReport=PageFactory.initElements(driver,MetacognitiveReport.class);
            MostChallengingReport mostChallengingReport=PageFactory.initElements(driver,MostChallengingReport.class);

            //check Proficiency report fot this student
            new Navigator().NavigateTo("Proficiency Report");
            proficiencyReport.getBarChart().click();
            Thread.sleep(1000);
            proficiencyReport.getBarChart().click();
            Thread.sleep(1000);
            proficiencyReport.getBarChart().click();
            Thread.sleep(1000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='True']")));
            Thread.sleep(2000);
            System.out.println("asasdas " + diagnosticTab.questionContext.getText());
            System.out.println("webpage "+questiontextPractice);
            Assert.assertTrue(diagnosticTab.questionContext.getText().contains(questiontextPractice), "Question content not equal");

            //check Metacognistic report fot this student
            new Navigator().NavigateTo("Metacognitive Report");
            metacognitiveReport.getColoredMarker().click();
            Thread.sleep(1000);
            metacognitiveReport.getColoredMarker().click();
            Thread.sleep(1000);
            metacognitiveReport.practicePageButton.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='True']")));
            Assert.assertTrue(diagnosticTab.questionContext.getText().contains(questiontextPractice),"Question content not equal");
            //check Productivity report fot this student
            new Navigator().NavigateTo("Productivity Report");
            metacognitiveReport.getColoredMarker().click();
            Thread.sleep(1000);
            metacognitiveReport.getColoredMarker().click();
            Thread.sleep(1000);
            metacognitiveReport.practicePageButton.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='True']")));
            Assert.assertTrue(diagnosticTab.questionContext.getText().contains(questiontextPractice),"Question content not equal");

            //check Most Challenging Activities Report fot this student
            new Navigator().NavigateTo("Most Challenging Activities Report");
            mostChallengingReport.navigateToPractice.click();
            Thread.sleep(1000);
            metacognitiveReport.practicePageButton.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='True']")));
            Assert.assertTrue(diagnosticTab.questionContext.getText().contains(questiontextPractice), "Question content not equal");





        } catch (Exception e) {
            Assert.fail("Exception in testcase Take Assessment/Assignment in class TakeTestAssignment", e);
        }


    }

    @Test(priority = 2,enabled = true)
    public void instructorLoginToCheckQuestionCreated() {
        try {
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            Lesson lesson = PageFactory.initElements(driver, Lesson.class);
            Summary summary = PageFactory.initElements(driver, Summary.class);
            ManageContent manageContent=PageFactory.initElements(driver,ManageContent.class);
            CourseOutline courseOutline=PageFactory.initElements(driver,CourseOutline.class);
            QuestionBank questionBank=PageFactory.initElements(driver,QuestionBank.class);
            DiagnosticTab diagnosticTab=PageFactory.initElements(driver,DiagnosticTab.class);
            String assesmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(62));
            String chapterName = ReadTestData.readDataByTagName("", "newChapterName", Integer.toString(62));
            WebDriverWait wait=new WebDriverWait(driver,200);
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            //create Static assignment
            new Assignment().create(79);
            //create Static assignment  questions
            new Assignment().addQuestionsWithCustomizedQuestionText(79, "truefalse", "", 9);
            //instructor login
            new LoginUsingLTI().ltiLogin("77");
            new Navigator().NavigateTo("e-Textbook"); //navigate to etextbook
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='toc-sprite search-course-stream ui-autocomplete-input']")));
            //navigate to diagnostic test in a particular chapter
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");
            Thread.sleep(500);
            lessonPage.orionAdaptivePracticeTabActive.click();
            lessonPage.previewButton.get(1).click();
            Thread.sleep(3000);
            //fetch all question text from web page
            List<WebElement> questionListOnPerformancePagePractice=diagnosticTab.questionCardListInstructor;
            if(!(questionListOnPerformancePagePractice.size()==5)) {
                Assert.fail("Question count on performance page After Diagnostic test not same");
            }
            //fetch question text from XML
            String questiontextPractice = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(62));
            Assert.assertTrue(summary.getQuestionText.getText().contains(questiontextPractice),"Question text is not equal");

            new Navigator().NavigateTo("e-Textbook"); //navigate to etextbook
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='toc-sprite search-course-stream ui-autocomplete-input']")));
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");
            Thread.sleep(500);
            List<WebElement> assessmentLink=driver.findElements(By.xpath("//a[contains(text(),'"+chapterName+"')]"));
            assessmentLink.get(1).click();
            List<WebElement> questionListOnInstructorPersonalizedPractice=diagnosticTab.questionCardListInstructor;
            if(!(questionListOnInstructorPersonalizedPractice.size()==5)) {
                Assert.fail("Question count on performance page After Diagnostic test not same");
            }
            String questiontextPersonalizedPractice = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(77));
            Assert.assertTrue(summary.getQuestionText.getText().contains(questiontextPersonalizedPractice),"Question text is not equal");

            new Navigator().NavigateTo("e-Textbook"); //navigate to etextbook
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='toc-sprite search-course-stream ui-autocomplete-input']")));
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");
            Thread.sleep(1000);
            new SelectCourse().selectInvisibleAssignment(assesmentName);
            Thread.sleep(3000);
            List<WebElement> questionListOnInstructorStaticPractice=diagnosticTab.questionCardListInstructor;
            if(!(questionListOnInstructorStaticPractice.size()==15)) {
                Assert.fail("Question count on performance page After Diagnostic test not same");
            }
            String questiontextStaticPractice = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(62));
            Assert.assertTrue(summary.getQuestionText.getText().contains(questiontextStaticPractice),"Question text is not equal");
           new Navigator().NavigateTo("Question Banks");
            Thread.sleep(3000);
            questionBank.getSearchInputFieldOnQuestionBank().get(1).sendKeys(ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(79)));
            questionBank.getSearchButtonOnQuestionBank().get(1).click();
            Thread.sleep(2000);
            questionBank.getPreviewButton().click();
            List<WebElement> questionListOnInstructorStaticAssignment=diagnosticTab.questionCardListInstructor;
            if(!(questionListOnInstructorStaticAssignment.size()==5))
                Assert.fail("Question count on performance page After Diagnostic test not same");
            String questiontextStaticAssignment = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(79));
            Assert.assertTrue(summary.getQuestionText.getText().contains(questiontextStaticAssignment),"Question text is not equal");
            new Assignment().assignToStudent(62);
            new LoginUsingLTI().ltiLogin("62");
            new Assignment().submitAssignmentAsStudent(62);
            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("77");
            new Navigator().NavigateTo("Proficiency Report");
            questionBank.getPerformanceByChapters().click();
            Thread.sleep(2000);
            questionBank.getPerformanceByObjectives().click();
            Thread.sleep(2000);
            questionBank.getPerformanceByQuestions().click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[text()='True']")));
            String questiontextStaticPracticeAssignment = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(79));
            System.out.println(questiontextStaticPracticeAssignment);
            System.out.println(summary.getQuestionText.getText());
            Assert.assertTrue(summary.getQuestionText.getText().contains(questiontextStaticPracticeAssignment),"Question text is not equal");

        } catch (Exception e) {
            Assert.fail("Exception in testcase Take Assessment/Assignment in class TakeTestAssignment", e);
        }
    }

}
