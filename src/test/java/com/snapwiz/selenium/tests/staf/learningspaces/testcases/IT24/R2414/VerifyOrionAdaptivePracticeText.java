package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT24.R2414;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.MyActivity.MyActivity;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.TocSearch;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.MetacognitiveReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.MostChallengingReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.PerformanceSummaryReport;
import com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.LTILogin;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by mukesh on 22/12/15.
 */
public class VerifyOrionAdaptivePracticeText extends Driver {

    @Test(priority = 1,enabled = true)
    public void verifyOrionAdaptivePracticeText(){

        try {
            LessonPage lessonPage= PageFactory.initElements(driver,LessonPage.class);
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion=PageFactory.initElements(driver,com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
            Dashboard dashboard= PageFactory.initElements(driver,Dashboard.class);

            new LoginUsingLTI().ltiLogin("12"); //login as student
            lessonPage.getToc().click(); //click on the TOC
            new TopicOpen().chapterOpen(2); // Click on chapter 3
            new DiagnosticTest().startTest(2);
            new DiagnosticTest().attemptAllCorrect(2,false, false);// attempt all correct

            lessonPage.getToc().click(); //click on the TOC
            new TopicOpen().chapterOpen(2); // Click on chapter 3
            new PracticeTest().startTest();
            Assert.assertEquals(lessonPage.orionAdaptive_link.get(3).getAttribute("title").trim(), "ORION  Ch 3: Biological Macromolecules", "label as “ORION Ch X: Chapter Name” as tooltip is not displaying");
            new MouseHover().mouserhover("al-diag-test-title");
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.practiceTestHeader.getAttribute("title").trim(),"ORION Ch 3: Biological Macromolecules","label as “ORION Ch X: Chapter Name” as tooltip is not displaying");

            lessonPage.submitAnswer.click();//click on the submit answer
            new Navigator().navigateToTab("Discussion"); //navigate to Discussion
            discussion.newDiscussion_button.click(); //click on the new discussion link
            discussion.discussion_text.sendKeys("new Discussion");
            discussion.submit_button.click();//submit discussion*/
            new Navigator().NavigateTo("Dashboard"); //navigate to Dashboard
            Assert.assertEquals(dashboard.post_inDashboard.isDisplayed(),true,"label as “ORION Ch X: Chapter Name” as tooltip is not displaying in dashboard");


        } catch (Exception e) {
            Assert.fail("Exception in TC verifyOrionAdaptivePracticeText of class VerifyOrionAdaptivePracticeText",e);
        }
    }

    @Test(priority = 2,enabled = true)
    public void verifyOrionAdaptivePracticeTextWhileQuitingPractice(){

        try {
            LessonPage lessonPage= PageFactory.initElements(driver,LessonPage.class);
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion=PageFactory.initElements(driver,com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
            Dashboard dashboard= PageFactory.initElements(driver,Dashboard.class);
            PerformanceSummaryReport performanceSummaryReport=PageFactory.initElements(driver,PerformanceSummaryReport.class);
            MyActivity myActivity= PageFactory.initElements(driver,MyActivity.class);
            MostChallengingReport mostChallengingReport= PageFactory.initElements(driver,MostChallengingReport.class);
            MetacognitiveReport metacognitiveReport= PageFactory.initElements(driver,MetacognitiveReport.class);

            new LoginUsingLTI().ltiLogin("21"); //login as student
            lessonPage.getToc().click(); //click on the TOC
            new TopicOpen().chapterOpen(2); // Click on chapter 3
            new DiagnosticTest().startTest(2);
            new DiagnosticTest().attemptAllCorrect(2,false, false);// attempt all correct

            lessonPage.getToc().click(); //click on the TOC
            new TopicOpen().chapterOpen(2); // Click on chapter 3
            new PracticeTest().startTest();
            Assert.assertEquals(lessonPage.orionAdaptive_link.get(3).getAttribute("title").trim(), "ORION  Ch 3: Biological Macromolecules", "label as “ORION Ch X: Chapter Name” as tooltip is not displaying");
            new MouseHover().mouserhover("al-diag-test-title");
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.practiceTestHeader.getAttribute("title").trim(),"ORION Ch 3: Biological Macromolecules","label as “ORION Ch X: Chapter Name” as tooltip is not displaying");

            for(int i = 0; i < 11; i++) {
                new PracticeTest().AttemptCorrectAnswer(0,"21");
            }

            lessonPage.quitPractice_Test.click();
            Thread.sleep(4000);
            Assert.assertEquals(lessonPage.viewReport_link.getText().trim(),"View Report","View Report is not displaying");
            Assert.assertEquals(lessonPage.backToLastSession_link.getText().trim(), "Back to last lesson", "Back to last lesson link in not displaying");


            lessonPage.viewReport_link.click(); //click on the view Report link;
            Assert.assertEquals(performanceSummaryReport.title_performanceSummary.getText(), "Performance Summary", "Student is not navigated to Performance Summary page");
            Assert.assertEquals(lessonPage.orionAdaptive_link.get(3).getAttribute("title").trim(), "ORION  Ch 3: Biological Macromolecules", "label as “ORION Ch X: Chapter Name” as tooltip is not displaying");
            Assert.assertEquals(performanceSummaryReport.performanceReportTitle.getAttribute("title").trim(),"ORION Ch 3: Biological Macromolecules","label as “ORION Ch X: Chapter Name” as tooltip is not displaying");


            new LoginUsingLTI().ltiLogin("21"); //login as student
            new Navigator().NavigateTo("Dashboard"); //navigate to Dashboard
            Assert.assertEquals(dashboard.assessmentLink.get(0).getText().trim(),"ORION Ch 3: Biological Macromolecules","On the \"Recent Activities Tile\" attepmted Practice not in the form of  “ORION Ch X: Chapter Name”");

            new Navigator().NavigateTo("My Activity"); //navigate to My Activity
            Assert.assertEquals(myActivity.cardTitle.get(0).getAttribute("title").trim(), "Completed an ORION Adaptive practice");
            Assert.assertEquals(myActivity.assessment_title.get(0).getText().trim(),"\"ORION Ch 3: Biological Macromolecules\"");

            new Navigator().NavigateTo("Metacognitive Report"); //navigate to Metacognitive Report
            metacognitiveReport.proficiencyArrow.click();
            Assert.assertEquals(metacognitiveReport.reportPerformance_link.get(0).getText().trim(),"Study","Study button is not displaying after clicking on arrow");
            Assert.assertEquals(metacognitiveReport.reportPerformance_link.get(1).getText().trim(),"Practice","Practice button is not displaying after clicking on arrow");

            metacognitiveReport.reportPerformance_link.get(1).click(); //click on the practice button
            Thread.sleep(4000);
            new UIElement().waitAndFindElement(lessonPage.practiceTestHeader);
            Assert.assertEquals(lessonPage.orionAdaptive_link.get(3).getAttribute("title").trim(), "ORION  Ch 3: Biological Macromolecules", "label as “ORION Ch X: Chapter Name” as tooltip is not displaying");
            new MouseHover().mouserhover("al-diag-test-title");
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.practiceTestHeader.getAttribute("title").trim(),"ORION Ch 3: Biological Macromolecules","label as “ORION Ch X: Chapter Name” as tooltip is not displaying");


            //Metacognitive Report
            new Navigator().NavigateTo("Metacognitive Report"); //navigate to Metacognitive Report
            new UIElement().waitAndFindElement(metacognitiveReport.getColoredMarker());
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());
            Assert.assertEquals(metacognitiveReport.reportPerformance_link.get(0).getText().trim(),"Study","Study button is not displaying after clicking on arrow");
            Assert.assertEquals(metacognitiveReport.reportPerformance_link.get(1).getText().trim(),"Practice","Practice button is not displaying after clicking on arrow");

            metacognitiveReport.reportPerformance_link.get(1).click(); //click on the practice button
            Thread.sleep(4000);
            new UIElement().waitAndFindElement(lessonPage.practiceTestHeader);
            Assert.assertEquals(lessonPage.orionAdaptive_link.get(3).getAttribute("title").trim(), "ORION  Ch 3: Biological Macromolecules", "label as “ORION Ch X: Chapter Name” as tooltip is not displaying");
            new MouseHover().mouserhover("al-diag-test-title");
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.practiceTestHeader.getAttribute("title").trim(),"ORION Ch 3: Biological Macromolecules","label as “ORION Ch X: Chapter Name” as tooltip is not displaying");



            //Productivity Report
            new Navigator().NavigateTo("Productivity Report"); //navigate to Productivity Report
            metacognitiveReport.proficiencyArrow.click();
            Assert.assertEquals(metacognitiveReport.reportPerformance_link.get(0).getText().trim(),"Study","Study button is not displaying after clicking on arrow");
            Assert.assertEquals(metacognitiveReport.reportPerformance_link.get(1).getText().trim(),"Practice","Practice button is not displaying after clicking on arrow");

            metacognitiveReport.reportPerformance_link.get(1).click(); //click on the practice button
            Thread.sleep(4000);
            new UIElement().waitAndFindElement(lessonPage.practiceTestHeader);
            Assert.assertEquals(lessonPage.orionAdaptive_link.get(3).getAttribute("title").trim(), "ORION  Ch 3: Biological Macromolecules", "label as “ORION Ch X: Chapter Name” as tooltip is not displaying");
            new MouseHover().mouserhover("al-diag-test-title");
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.practiceTestHeader.getAttribute("title").trim(),"ORION Ch 3: Biological Macromolecules","label as “ORION Ch X: Chapter Name” as tooltip is not displaying");


            new Navigator().NavigateTo("Productivity Report"); //navigate to Productivity Report
            new UIElement().waitAndFindElement(metacognitiveReport.getColoredMarker());
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());
            Assert.assertEquals(metacognitiveReport.productivityReportPerformance_link.get(0).getText().trim(),"Study","Study button is not displaying after clicking on arrow");
            Assert.assertEquals(metacognitiveReport.productivityReportPerformance_link.get(1).getText().trim(),"Practice","Practice button is not displaying after clicking on arrow");

            metacognitiveReport.productivityReportPerformance_link.get(1).click(); //click on the practice button
            Thread.sleep(4000);
            new UIElement().waitAndFindElement(lessonPage.practiceTestHeader);
            Assert.assertEquals(lessonPage.orionAdaptive_link.get(3).getAttribute("title").trim(), "ORION  Ch 3: Biological Macromolecules", "label as “ORION Ch X: Chapter Name” as tooltip is not displaying");
            new MouseHover().mouserhover("al-diag-test-title");
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.practiceTestHeader.getAttribute("title").trim(),"ORION Ch 3: Biological Macromolecules","label as “ORION Ch X: Chapter Name” as tooltip is not displaying");

            //Most Challenging Activities Report
            new Navigator().NavigateTo("Most Challenging Activities Report"); //navigate to Most Challenging Activities Report
            Assert.assertTrue(mostChallengingReport.viewByChapter_tab.isDisplayed());
            mostChallengingReport.navigateToPractice.click();

            Assert.assertEquals(metacognitiveReport.mostReportPerformance_link.get(0).getText().trim(),"Study","Study button is not displaying after clicking on arrow");
            Assert.assertEquals(metacognitiveReport.mostReportPerformance_link.get(1).getText().trim(),"Practice","Practice button is not displaying after clicking on arrow");

            metacognitiveReport.mostReportPerformance_link.get(1).click(); //click on the practice button
            Thread.sleep(4000);
            new UIElement().waitAndFindElement(lessonPage.practiceTestHeader);
            Assert.assertEquals(lessonPage.orionAdaptive_link.get(3).getAttribute("title").trim(), "ORION  Ch 3: Biological Macromolecules", "label as “ORION Ch X: Chapter Name” as tooltip is not displaying");
            new MouseHover().mouserhover("al-diag-test-title");
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.practiceTestHeader.getAttribute("title").trim(),"ORION Ch 3: Biological Macromolecules","label as “ORION Ch X: Chapter Name” as tooltip is not displaying");



        } catch (Exception e) {
            Assert.fail("Exception in TC verifyOrionAdaptivePracticeTextWhileQuitingPractice of class VerifyOrionAdaptivePracticeText",e);
        }

    }


    @Test(priority = 3,enabled = true)
    public void instructorHasAssignedAdaptivePractice(){

        try {
            LessonPage lessonPage= PageFactory.initElements(driver,LessonPage.class);
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion=PageFactory.initElements(driver,com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
            Dashboard dashboard= PageFactory.initElements(driver,Dashboard.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);

            new LoginUsingLTI().ltiLogin("99_1"); //login as student
            new LoginUsingLTI().ltiLogin("99"); //login as instructor
            dashboard.toc_drop.click(); //click on the study plan icon
            new TopicOpen().chapterOpen(2);
            new Navigator().NavigateToOrion();
            new TopicOpen().clickOnAdaptivePracticeArrow();
            new TopicOpen().clickOnAssignThisIcon();
            new AssignLesson().assignTOCFromOrionAdaptivePractice(99);

            new LoginUsingLTI().ltiLogin("99_1"); //login as student
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new TopicOpen().chapterOpen(2);
            new DiagnosticTest().startDiagnosticTestForAssignedPractice(2);
            new DiagnosticTest().DiagonesticTestQuitBetween(2, 10, "correct", false, false, true);//quit

            new Navigator().NavigateTo("e-Textbook"); //navigate to eTextbook
            new TopicOpen().chapterOpen(2);
            new Click().clickBycssselector("span[class='ls-toc-due-date ls-adaptive-toc-due-date']");
            new AttemptTest().attemptAllCorrectForAdaptivePractive(2, false, false, 10);
            assignments.adaptiveFinish_button.click();//click on the finish button
            Thread.sleep(2000);
            assignments.iamFinished_link.click(); //click on the i am finished link
            new QuestionCard().clickOnCard("99",1);
            new Navigator().navigateToTab("Discussion"); //navigate to Discussion
            discussion.newDiscussion_button.click(); //click on the new discussion link
            discussion.discussion_text.sendKeys("new Discussion");
            discussion.submit_button.click();//submit discussion


            Assert.assertEquals(lessonPage.orionAdaptive_link.get(3).getAttribute("title").trim(), "ORION  Ch 3: Biological Macromolecules", "label as “ORION Ch X: Chapter Name” as tooltip is not displaying");
            new MouseHover().mouserhover("resource-title ");
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.resource_title.getAttribute("title").trim(),"ORION Ch 3: Biological Macromolecules","label as “ORION Ch X: Chapter Name” as tooltip is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in TC instructorHasAssignedAdaptivePractice of class VerifyOrionAdaptivePracticeText",e);
        }
    }


    @Test(priority = 4,enabled = true)
    public void studentCourseStreamPage(){

        try {
            LessonPage lessonPage= PageFactory.initElements(driver,LessonPage.class);
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion=PageFactory.initElements(driver,com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
            Dashboard dashboard= PageFactory.initElements(driver,Dashboard.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            CourseStreamPage courseStreamPage=PageFactory.initElements(driver,CourseStreamPage.class);


            new LoginUsingLTI().ltiLogin("104_1"); //login as student
            new LoginUsingLTI().ltiLogin("104"); //login as instructor
            dashboard.toc_drop.click(); //click on the study plan icon
            new TopicOpen().chapterOpen(2);
            new Navigator().NavigateToOrion();
            new TopicOpen().clickOnAdaptivePracticeArrow();
            new TopicOpen().clickOnAssignThisIcon();
            new AssignLesson().assignTOCFromOrionAdaptivePractice(104);

            new LoginUsingLTI().ltiLogin("104_1"); //login as student
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new TopicOpen().chapterOpen(2);
            new DiagnosticTest().startDiagnosticTestForAssignedPractice(2);
            new DiagnosticTest().DiagonesticTestQuitBetween(2, 10, "correct", false, false, true);//quit

            new LoginUsingLTI().ltiLogin("104_1"); //login as student
            new Navigator().NavigateTo("Dashboard"); //navigate to Dashboard
            Assert.assertEquals(dashboard.courseStream_entry.getText().trim(),"ORION Ch 3: Biological Macromolecules");
            dashboard.courseStream_entry.click();
            new UIElement().waitAndFindElement(courseStreamPage.assessmentName);
            new MouseHover().mouserhover("ls-stream-assignment-title");
            Thread.sleep(2000);
            Assert.assertEquals(courseStreamPage.assessmentName.getAttribute("title").trim(),"ORION Ch 3: Biological Macromolecules","label as “ORION Ch X: Chapter Name” as tooltip is not displaying");

            courseStreamPage.assessmentName.click(); //click on the assessment link
            Thread.sleep(4000);
            Assert.assertEquals(lessonPage.orionAdaptive_link.get(0).getAttribute("title").trim(), "ORION Ch 3: Biological Macromolecules", "label as “ORION Ch X: Chapter Name” as tooltip is not displaying");
            new MouseHover().mouserhoverbyCss("span[class='resource-title practice-assignment-title']");
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.resource_titleFromCousreStream.getAttribute("title").trim(),"ORION Ch 3: Biological Macromolecules","label as “ORION Ch X: Chapter Name” as tooltip is not displaying");

            new AttemptTest().attemptAllCorrectForAdaptivePractive(2, false, false, 10);
            assignments.adaptiveFinish_button.click();//click on the finish button
            Thread.sleep(2000);
            assignments.iamFinished_link.click(); //click on the i am finished link
            new QuestionCard().clickOnCard("99",1);
            new Navigator().navigateToTab("Discussion"); //navigate to Discussion
            discussion.newDiscussion_button.click(); //click on the new discussion link
            discussion.discussion_text.sendKeys("new Discussion");
            discussion.submit_button.click();//submit discussion

            Thread.sleep(3000);
            Assert.assertEquals(lessonPage.orionAdaptive_link.get(3).getAttribute("title").trim(), "ORION  Ch 3: Biological Macromolecules", "label as “ORION Ch X: Chapter Name” as tooltip is not displaying");
            new MouseHover().mouserhover("resource-title ");
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.resource_title.getAttribute("title").trim(),"ORION Ch 3: Biological Macromolecules","label as “ORION Ch X: Chapter Name” as tooltip is not displaying");

            new Navigator().NavigateTo("Assignments");
            Assert.assertEquals(assignments.assignmentName.getAttribute("title").trim(),"ORION Ch 3: Biological Macromolecules","label as “ORION Ch X: Chapter Name” as tooltip is not displaying");

            assignments.assignmentName.click();
            Thread.sleep(4000);
            Assert.assertEquals(lessonPage.orionAdaptive_link.get(0).getAttribute("title").trim(), "ORION Ch 3: Biological Macromolecules", "label as “ORION Ch X: Chapter Name” as tooltip is not displaying");
            new MouseHover().mouserhoverbyCss("span[class='resource-title practice-assignment-title']");
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.resource_titleFromCousreStream.getAttribute("title").trim(),"ORION Ch 3: Biological Macromolecules","label as “ORION Ch X: Chapter Name” as tooltip is not displaying");

            new Navigator().NavigateTo("Dashboard"); //navigate to Dashboard
            new MouseHover().mouserHoverByClassList("ls-dashboard-activities-chaptername",1);
            Thread.sleep(2000);
            Assert.assertEquals(dashboard.assessmentLink.get(1).getText().trim(), "ORION Ch 3: Biological Macromolecules", "label as “ORION Ch X: Chapter Name” as tooltip is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in TC studentCourseStreamPage of class VerifyOrionAdaptivePracticeText",e);
        }
    }


}
