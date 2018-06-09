package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT20.R204;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.Preview;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.EngagementReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.PerformanceSummaryReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.ProficiencyReport;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by pragya on 06-05-2015.
 */
public class StudyPlanEnhancement extends Driver {

    @Test(priority = 1, enabled = true)
    public void studyPlanNewView() {
        try {
            int dataIndex = 15;
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

            new Assignment().create(15);//CMS login and create an assignment

            new LoginUsingLTI().ltiLogin("15_1");//Login as an instructor
            new Assignment().assignToStudent(15);

            new LoginUsingLTI().ltiLogin("15");//Login as a student
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook

            //Expected - The chapter view should show chapter options in the column one.
            Assert.assertEquals(lessonPage.getChapterName().isDisplayed(), true, "Chapter view is not displayed");

            new TopicOpen().chapterOpen(1);//Open 2nd chapter
            Thread.sleep(2000);

            //Expected - Study tab should be opened by default for any chapter view.
            Assert.assertEquals(lessonPage.studyPlanTab.getAttribute("class"), "tab active", "Study Plan tab is not opened by default ");

            //Expected - Study plan header should contain chapter name.
            Assert.assertEquals(lessonPage.chapterNameInHeader.getText(), "Ch 2: The Chemical Foundation of Life", "Study plan headed is not containing chapter name");

            //Expected - Study plan should have two tabs "Study" and "ORION Adaptive Practice”.
            Assert.assertEquals(lessonPage.studyPlanTab.getText(), "Study", "Study plan does not hav 'Study' tab");
            Assert.assertEquals(lessonPage.orionAdaptivePracticeTab.getText(), "ORION Adaptive Practice", "Study plan does not hav 'ORION Adaptive Practice' tab");

            //Expected - "Study" tab should contain " lesson, assignment and static assessment".

            WebElement scroll = driver.findElement(By.cssSelector("#chapter-details-scrollbar-toc > div.scrollbar > div.track > div.thumb"));
            Actions actions = new Actions(driver);
            actions.moveToElement(scroll, 0, 0)
                    .clickAndHold()
                    .moveByOffset(0, 450)
                    .release()
                    .perform();
            Thread.sleep(1000);
            Assert.assertEquals(lessonPage.getIntroductionUnderLesson().get(1).isDisplayed(), true, "Study tab does not have 'introduction' as a lesson");
            Assert.assertEquals(lessonPage.staticPracticeName_2nd.getText(), "2.1 Concept Check", "Study tab does not have 'static assessment' ");
            if (!lessonPage.list_postChapterAssignment.get(0).getAttribute("title").equals("IT204_static Assessment_15")) {
                Assert.fail("Study tab does not have an assignment");
            }

            lessonPage.getIntroductionUnderLesson().get(1).click();//Click on introduction
            Thread.sleep(5000);
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(@title,'Introduction')]")));

            //Expected - Introduction lesson should get rendered
            Assert.assertEquals(lessonPage.getIntroductionTab().getText(), "Introduction", "Introduction tab is displayed");

            //Expected -Lesson should contain footer navigation for suggested next element
            Assert.assertEquals(lessonPage.getNextChapterFooter().get(0).getAttribute("title"), "2.1: Atoms, Isotopes, Ions, and Molecules: The Building Blocks", "Lesson is not containing footer navigation for suggested next element");

            lessonPage.getNextChapterFooter().get(0).click();//Click on footer navigation

            new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='ch02-sec2-1']")));

            //Expected - Next element should get rendered
            Assert.assertEquals(lessonPage.orionAdaptive_link.get(2).getAttribute("title"), "2.1: Atoms, Isotopes, Ions, and Molecules: The Building Blocks", "Next element is not rendered");

            //Expected - Only lesson and static assessment should appear as element link over footer
            Assert.assertEquals(lessonPage.getNextChapterFooter().get(0).getAttribute("title"), "2.1 Concept Check", "Static assessment is not appearing as element link over footer");
            Assert.assertEquals(lessonPage.getPreviousChapterFooter().get(0).getAttribute("title"), "Introduction", "lesson is not appearing as element link over footer");

            lessonPage.getNextChapterFooter().get(0).click();//Click on static practice test via footer

            //Expected - Student should be able to navigate to static practice test by footer.
            Assert.assertEquals(lessonPage.button_submitStatic.getAttribute("title"), "Submit Answer", "Student is not able to navigate to static practice test by footer");

            new AttemptTest().StaticTest();

            //Expected - Footer navigator should be visible in Performance summary Page
            Assert.assertEquals(lessonPage.getPreviousChapterFooter().get(1).getAttribute("title"), "2.1: Atoms, Isotopes, Ions, and Molecules: The Building Blocks", "previous footer navigation is not visible on performance summary page");
            Assert.assertEquals(lessonPage.getNextChapterFooter().get(1).getAttribute("title"), "2.2: Water", "next footer navigation is not visible on performance summary page");

            //“Orion Adaptive Practice” tab

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(0);//Open chapter 1
            new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@title='ORION Adaptive Practice']")));
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab

            new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Begin Diagnostic']")));

            //Expected - Student should be able to click on Orion Adaptive Practice tab.
            Assert.assertEquals(lessonPage.orionAdaptivePracticeTabActive.getAttribute("class"), "tab active", "Student is not able to click on Orion Adaptive Practice tab");

            //Expected - "Orion Adaptive Practice" should be contain diagnostic test
            Assert.assertEquals(lessonPage.beginDiagnostic.get(1).isDisplayed(), true, "'Orion Adaptive Practice' is not having diagnostic test");

            lessonPage.studyPlanTab.click();//Click on study plan tab

            //Expected - Student should be able to toggle between study and orion tab
            Assert.assertEquals(lessonPage.studyPlanTab.getAttribute("class"), "tab active", "Student is not able to toggle between study and orion tab as study tab is ot active after clicking on it");

            new DiagnosticTest().startTest(1);
            new DiagnosticTest().attemptAllCorrect(1, false, false);

            //Expected - Footer navigator should not be shown on performance summary page
            if (lessonPage.footer_content.getText().length() != 0) {
                Assert.fail("Footer navigator is shown on performance summary page");
            }

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(0);//Open chapter 1
            new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@title='ORION Adaptive Practice']")));
            lessonPage.orionAdaptivePracticeTab.click();//Click on Orion Adaptive Practice test

            new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.id("practice-test-button")));

            //Expected - Chapter level adaptive practice with a "Practice" button will be displayed
            Assert.assertEquals(lessonPage.button_practiceTest.get(1).getAttribute("value"), "Practice", "Practice button is not displayed");

            //Expected - First column should contain Section name and number "<section_number> <section_name>" format
            Assert.assertEquals(lessonPage.adaptivePracticeTestName.getText(), "1.1 The Science of Biology", "Format of adaptive practice test is not correct");

            boolean listProficiencyDisplayed = isDisplayed(15,lessonPage.list_proficiency);
            //Expected - Below of Section name should contain "Proficiency value" color bar
            Assert.assertEquals(listProficiencyDisplayed, true, "Proficiency value is not displayed");

            String proficiencyPercentage = textVerify(15,lessonPage.proficiencyPercentage);
            //Expected - After "Proficiency value" color bar should contain Proficiency percent in "<percent>%" format.
            if(!proficiencyPercentage.contains("%")){
                Assert.fail("Proficiency percentage is not displayed");
            }

            boolean adaptiveLogoDisplayed = isDisplayed(15,lessonPage.adaptiveLogo);
            //Expected -Second column should contain Adaptive logo
            Assert.assertEquals(adaptiveLogoDisplayed, true, "Adaptive logo is not displayed");

            //Expected - Section number and name should be shown in "<Section_number> : <Name> format
            Assert.assertEquals(lessonPage.adaptiveAssessmentSectionName.getText(), "1.1: Discuss the scientific basis for the study of biology - Practice", "Adaptive assessment section name is not in correct format");

            boolean arrowDispalyed = isDisplayed(15,lessonPage.arrows);
            //Expected - End of row in last column should contain an arrow icon
            Assert.assertEquals(arrowDispalyed, true, "Arrow is not displayed");

            new PracticeTest().startAdaptivePracticeTest("adaptivepracticetest");
            for (int i = 0; i < 5; i++)
                new PracticeTest().AttemptCorrectAnswer(0, "14");

            new PracticeTest().quitThePracticeTest();

            //Expected - The student should be navigated to the “Performance Summary” page for that adaptive Practice.
            Assert.assertEquals(lessonPage.performanceSummaryPage.isDisplayed(), true, "Performance summary page is not displayed after attempting the adaptive practice test");

            //Suggested next activity
            //Expected - Performance Summary page should not contain footer navigator
            if (lessonPage.footer_content.getText().length() != 0) {
                Assert.fail("Footer navigator is shown on performance summary page");
            }

        } catch (Exception e) {
            Assert.fail("Exception in method 'studyPlanNewView' in class 'StudyPlanEnhancement'", e);

        }
    }


    @Test(priority = 2, enabled = true)
    public void ChangesInOrionAdaptivePracticeTest() {
        try {
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            new LoginUsingLTI().ltiLogin("167");//Login as a student
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook

            //Expected - The chapter view should show chapter options in the column one.
            Assert.assertEquals(lessonPage.getChapterName().isDisplayed(), true, "Chapter view is not displayed");

            Thread.sleep(2000);

            lessonPage.orionAdaptivePracticeTab.click();//Click on Orion adaptive practice tab

            //Expected - First row should contain text " First, indicate how confident you are about this chapter"
            Assert.assertEquals(lessonPage.texts_orionAdaptivePractice.get(0).getText(), "First, indicate how confident you are about this chapter", "First row is not displayed as expected");

            //Expected - Second row should contain text "Then continue to your quick diagnostic activity".
            Assert.assertEquals(lessonPage.texts_orionAdaptivePractice.get(1).getText(), "Then continue to your quick diagnostic activity", "Second row is not displayed as expected");

            String enterConfidenceLevelString = textVerify(167, lessonPage.text_enterConfidenceLevel);
            //Expected - Third row should be contain text "Enter confidence level"
            Assert.assertEquals(enterConfidenceLevelString, "Enter Confidence Level", "Third row is not displayed as expected");

            boolean confidenceLevelDisplayed = isDisplayed(167, lessonPage.box_confidenceLevel);
            //Expected - It should be contain a confidence level box
            Assert.assertEquals(confidenceLevelDisplayed, true, "Confidence level box  is ot displayed");

            String BeginDiagnosticButtonFound = getAttribute(167, lessonPage.beginDiagnostic, "type");
            //Expected - It should be contain "Begin Diagnostic" button
            Assert.assertEquals(BeginDiagnosticButtonFound, "submit", "Begin Diagnostic button is not displayed");

            boolean practiceDisplayed = isDisplayed(167, lessonPage.button_practiceTest);
            //Expected - User should not be able to view adaptive Practice test
            Assert.assertEquals(practiceDisplayed, false, "Practice test is present");

            new DiagnosticTest().startTest(2);
            new DiagnosticTest().DiagonesticTestQuitBetween(2, 2, "correct", false, false, false);//Click on continue later link

            //Expected - Student should navigate to Study tab.
            Assert.assertEquals(lessonPage.studyPlanTab.getAttribute("class"), "tab active", "Student is not navigated to Study tab");

            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab

            boolean firstAndSecondText = new BooleanValue().presenceOfElement(167, By.xpath("//div[@class='ls-dignostic-text']/descendant::span[2]"));
            boolean enterConfidenceLevelText = isDisplayed(167, lessonPage.text_enterConfidenceLevel);
            //Expected - Text in First,Second row and third row should be invisible
            Assert.assertEquals(firstAndSecondText, false, "First and Second row text is visible");
            Assert.assertEquals(enterConfidenceLevelText, false, "'Enter Confidence level text visible'");

            //Expected - First row should be contain text "<color flower>" and text "Orion Adaptive Practice".
            Assert.assertEquals(lessonPage.adaptiveLogo.get(0).isDisplayed(), true, "color flower is not displayed");
            Assert.assertEquals(lessonPage.getForDiagnostic().getText(), "ORION Adaptive Practice", "'Orion Adaptive Practice is not displayed'");

            //Expected - After text "Continue Diagnostic" button should be display.
            Assert.assertEquals(lessonPage.button_continueDiagnostic.getAttribute("value"), "Continue Diagnostic", "'Continue Diagnostic' button is not displayed");

            lessonPage.button_continueDiagnostic.click();//Click on Continue diagnostic button

            //Expected - It should be navigate to last unattempted question in  diagnostic test.
            if(!lessonPage.leftQuestionNumber.getText().contains("3")){
                Assert.fail("page is not navigated to the last unattempted question");
            }

            new DiagnosticTest().attemptAllCorrect(2, false, false);

            //Expected - The student should be navigated to the “Performance Summary” page for that diagnostic
            Assert.assertEquals(lessonPage.performanceSummaryPage.isDisplayed(), true, "Performance summary page is not displayed");

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(0);//Open chapter 1
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab

            //Expected - "Practice" Button should display.
            Assert.assertEquals(lessonPage.button_practiceTest.get(0).getAttribute("type"), "button", "'Practice' button is not displayed");

            //Expected - All the section level adaptive practice elements should be display
            Assert.assertEquals(lessonPage.sectionLevelAdaptivePractice.get(0).getText(), "1.1: Discuss the scientific basis for the study of biology - Practice", "Section level adaptive practice element is not displayed");
            Assert.assertEquals(lessonPage.sectionLevelAdaptivePractice.get(1).getText(), "1.2: Describe the properties and levels of organization of living things - Practice", "Section level adaptive practice element is not displayed");
            Assert.assertEquals(lessonPage.sectionLevelAdaptivePractice.get(2).getText(), "1.0: Analyze the process of science and the concepts of biology using critical thinking skills - Practice", "Section level adaptive practice element is not displayed");

            new TopicOpen().chapterOpen(1);//Select chapter 2

            (new DiagnosticTest()).startTest(2);
            new DiagnosticTest().DiagonesticTestQuitBetween(2, 2, "correct", false, false, true); //quit the question attempt

            //Expected - The student should be navigated to the “Performance Summary” page for that diagnostic
            Assert.assertEquals(lessonPage.performanceSummaryPage.isDisplayed(), true, "Performance summary page is not displayed");

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Click on chapter 2

            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab

            //Expected - Student should be able to view "practice" button.
            Assert.assertEquals(lessonPage.button_practiceTest.get(0).getAttribute("type"), "button", "'Practice' button is not displayed");

            //Expected - All the section level adaptive practice elements should be display
            Assert.assertEquals(lessonPage.sectionLevelAdaptivePractice.get(3).getText(), "2.1: Explain the components of atoms and how atoms make up all matter - Practice", "Section level adaptive practice element is not displayed");
            Assert.assertEquals(lessonPage.sectionLevelAdaptivePractice.get(4).getText(), "2.2: Describe the properties of water and the roles of acids and bases in the living organisms - Practice", "Section level adaptive practice element is not displayed");
            Assert.assertEquals(lessonPage.sectionLevelAdaptivePractice.get(5).getText(), "2.3: Discuss hydrocarbons, including isomers, enantiomers, and functional groups - Practice", "Section level adaptive practice element is not displayed");
            Assert.assertEquals(lessonPage.sectionLevelAdaptivePractice.get(6).getText(), "2.0: Analyze the structure of an atom, how the isotopes of an element differ, and how atoms combine to form molecules and larger structures using critical thinking skills - Practice");

        } catch (Exception e) {

            Assert.fail("Exception in method 'studyPlanNewView' in class 'StudyPlanEnhancement'", e);
        }
    }


    @Test(priority = 3, enabled = true)
    public void practiceButton() {
        try {
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

            new LoginUsingLTI().ltiLogin("282");//Login as a student
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(0);//Open chapter 1

            new DiagnosticTest().startTest(1);
            new DiagnosticTest().attemptAllCorrect(1, false, false);
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(0);//Open chapter 1

            //Expected - Student should be able to view "practice" button.
            Assert.assertEquals(lessonPage.button_practiceTest.get(0).getAttribute("type"), "button", "'Practice' button is not displayed");


            new PracticeTest().startTest();
            for (int i = 0; i < 5; i++)
                new PracticeTest().AttemptCorrectAnswer(1, "282");

            new PracticeTest().quitThePracticeTest();

            //Expected - The student should be navigated to the “Performance Summary” page for that adaptive Practice.
            Assert.assertEquals(lessonPage.performanceSummaryPage.isDisplayed(), true, "'Performance summary page is not displayed'");

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(0);//Open chapter 1
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab

            //Expected - Chapter should be contain section level adaptive practice test
            Assert.assertEquals(lessonPage.sectionLevelAdaptivePractice.get(0).getText(), "1.1: Discuss the scientific basis for the study of biology - Practice", "Section level adaptive practice element is not displayed");
            Assert.assertEquals(lessonPage.sectionLevelAdaptivePractice.get(1).getText(), "1.2: Describe the properties and levels of organization of living things - Practice", "Section level adaptive practice element is not displayed");
            Assert.assertEquals(lessonPage.sectionLevelAdaptivePractice.get(2).getText(), "1.0: Analyze the process of science and the concepts of biology using critical thinking skills - Practice", "Section level adaptive practice element is not displayed");

            new PracticeTest().startAdaptivePracticeTest("adaptivepracticetest");
            for (int i = 0; i < 5; i++)
                new PracticeTest().AttemptCorrectAnswer(0, "14");

            new PracticeTest().quitThePracticeTest();

            //Expected - The student should be navigated to the “Performance Summary” page for that adaptive Practice
            Assert.assertEquals(lessonPage.performanceSummaryPage.isDisplayed(), true, "Performance summary page is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in method 'practiceButton' in class 'StudyPlanEnhancement'", e);

        }
    }

    @Test(priority = 4, enabled = true)
    public void proficiencyValue() {
        try {
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

            new LoginUsingLTI().ltiLogin("332");//Login as a student
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(0);//Open chapter 1

            //Expected - Header should contain chapter name "Ch<chapter number>:<chapter name>" Format in white color left side of header
            Assert.assertEquals(lessonPage.chapterNameInHeader.getText(), "Ch 1: The Study of Life", "Chapter name format is not displayed as expectds");

            boolean closeDisplayed = new BooleanValue().presenceOfElement(332, By.className("hide-toc"));
            //Expected -Right side of header should contain "cross icon" in circle
            Assert.assertEquals(closeDisplayed, true, "Close button is not displayed");

            boolean proficiencyPercentageOnHeaderBeforeDiagnostic = new BooleanValue().presenceOfElement(332, By.xpath("//div[@class='chapter-report-score-wrapper']/descendant::span[2]"));
            //Expected - Header for Study should not contain Proficiency percent.
            Assert.assertEquals(proficiencyPercentageOnHeaderBeforeDiagnostic, false, "Proficiency percentage is displayed");

            new DiagnosticTest().startTest(1);
            new DiagnosticTest().DiagonesticTestQuitBetween(2, 10, "correct", false, false, false);//Click on continue later link

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(0);//Open chapter 1
            boolean proficiencyPercentageOnHeaderBeforeContinueDiagnostic = new BooleanValue().presenceOfElement(332, By.className("//div[@class='chapter-report-score-wrapper']/descendant::span[2]"));
            //Expected - Header for Study should not contain Proficiency percent.
            Assert.assertEquals(proficiencyPercentageOnHeaderBeforeContinueDiagnostic, false, "Proficiency percentage is displayed");

            new DiagnosticTest().continueDiagnosticTest();
            new DiagnosticTest().attemptAllCorrect(1, false, false);
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(0);//Open chapter 1

            //Expected - Header for Study should contains Proficiency percent
            if (!lessonPage.proficiencyPercentage_header.getText().contains("%")) {
                Assert.fail("Proficiency percentage is not displayed under study tab");
            }

            if(lessonPage.proficiencyPercentage_header.getText().contains(".")){
                Assert.fail("Percentage is not displayed in whole number");
            }

            new TopicOpen().chapterOpen(1);//Open chapter 2
            new DiagnosticTest().startTest(1);//Start diagnostic test
            new DiagnosticTest().DiagonesticTestQuitBetween(1, 12, "correct", false, false, true);//Quit the diagnostic test after attempting 12 questions

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2

            //Expected - Proficiency percent should show "PROFICIENCY : "value %".
            if(!lessonPage.proficiencyPercentage_header.getText().contains("%")){
                Assert.fail("Percentage is not displayed in whole number");
            }

            if(lessonPage.proficiencyPercentage_header.getText().contains(".")){
                Assert.fail("Percentage is not displayed in whole number");
            }

            new TopicOpen().chapterOpen(2);//Open chapter 3
            new DiagnosticTest().startTest(1);//Start diagnostic test
            new DiagnosticTest().DiagonesticTestQuitBetween(1, 0, "correct", false, false, true);//Quit the diagnostic test without attempting any questions

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(2);//Open chapter 3

            boolean percentageDisPlayed = new BooleanValue().presenceOfElement(332, By.xpath("//div[@class='chapter-report-score-wrapper']/descendant::span[2]"));
            //Expected - Header for Study should not contain proficiency percent
            Assert.assertEquals(percentageDisPlayed, false, "Proficiency percentage is displayed even if none of the questions are attempted");

        } catch (Exception e) {
            Assert.fail("Exception in method 'proficiencyValue' in class 'StudyPlanEnhancement'", e);

        }
    }


    @Test(priority = 5, enabled = true)
    public void ecfBelowThreshold() {
        try {
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

            new LoginUsingLTI().ltiLogin("404");//Login as a student
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook

            //ecf<0.2
            new TopicOpen().chapterOpen(0);//Open chapter 1

            boolean percentageDisPlayedBefore = new BooleanValue().presenceOfElement(332, By.xpath("//div[@class='chapter-report-score-wrapper']/descendant::span[2]"));
            //Expected - Header for Study should not contain proficiency percent
            Assert.assertEquals(percentageDisPlayedBefore, false, "Proficiency percentage is displayed");

            new DiagnosticTest().startTest(1);
            new DiagnosticTest().DiagonesticTestQuitBetween(1, 3, "correct", false, false, true);//Quit the diagnostic test after attempting 3 questions

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(0);//Open chapter 1

            boolean percentageDisPlayedAfter = new BooleanValue().presenceOfElement(332, By.xpath("//div[@class='chapter-report-score-wrapper']/descendant::span[2]"));
            //Expected - Header for Study should not contain proficiency percent
            Assert.assertEquals(percentageDisPlayedAfter, false, "Proficiency percentage is displayed");

            //ecf=0.2
            new TopicOpen().chapterOpen(1);//Open chapter 2
            new DiagnosticTest().startTest(1);
            new DiagnosticTest().DiagonesticTestQuitBetween(1, 11, "correct", false, false, true);//Quit the diagnostic test after attempting 11 questions

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2

            //Expected - Header for Study should contain Proficiency percent.
            if (!lessonPage.proficiencyPercentage_header.getText().contains("%")) {
                Assert.fail("Proficiency percentage is not displayed under study tab");
            }

            if(lessonPage.proficiencyPercentage_header.getText().contains(".")){
                Assert.fail("Percentage is not displayed in whole number");
            }

            //ecf>0.2
            new TopicOpen().chapterOpen(2);//Open chapter 3
            new DiagnosticTest().startTest(1);
            new DiagnosticTest().attemptAllCorrect(1, false, false);//Attempt all question

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(2);//Open chapter 3

            //Expected - Proficiency percent should show "PROFICIENCY : "value %"
            if (!lessonPage.proficiencyPercentage_header.getText().contains("%")) {
                Assert.fail("Proficiency percentage is not displayed under study tab");
            }

            if(lessonPage.proficiencyPercentage_header.getText().contains(".")){
                Assert.fail("Percentage is not displayed in whole number");
            }

        } catch (Exception e) {
            Assert.fail("Exception in method 'ecfBelowThreshold' in class 'StudyPlanEnhancement'", e);

        }

    }

    @Test(priority = 6, enabled = true)
    public void verifyProficiencyPercentageOnReportAndStudyHeader() {
        try {
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            ProficiencyReport proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);

            new LoginUsingLTI().ltiLogin("460");//Login as a student
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);

            new DiagnosticTest().startTest(1);//Start the diagnostic test
            new DiagnosticTest().attemptAllCorrect(1, false, false);//Attempt all the questions

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);

            String percentageOnHeader = lessonPage.proficiencyPercentage_header.getText();

            new Navigator().NavigateTo("Proficiency Report");//Navigate to Proficiency Report
            new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")));

            proficiencyReport.getBarChart().click();//Click on chapter

            //Expected - Proficiency percentage in section equal to proficiency percentage display on Proficiency Report in Chapter Proficiency Summary
            Assert.assertEquals(proficiencyReport.getCourseProficiency().getText() + "%", percentageOnHeader, "Proficiency percentage on study header is not same as in Proficiency report ");

        } catch (Exception e) {
            Assert.fail("Exception in method 'verifyProficiencyPercentageOnReortAndStudyHeader' in class 'StudyPlanEnhancement'", e);

        }
    }

    @Test(priority = 7, enabled = true)
    public void proficiencyAtSectionLevel() {
        try {
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

            new LoginUsingLTI().ltiLogin("494");//Login as a student
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(0);//Open chapter 1
            WebElement html = driver.findElement(By.tagName("html"));
           // html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
           // html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

            String section1 = textVerify(494, lessonPage.section_11UnderStudyTab);
            String section2 = textVerify(494, lessonPage.section_12UnderStudyTab);

            //Expected - Student able to view all section in study plan page
            Assert.assertEquals(lessonPage.introductionsectionUnderStudyTab.getText(), "Introduction", "'Introduction is not displayed under study section'");
            Assert.assertEquals(section1, "1.1 The Science of Biology", "'section1 is not displayed under study section'");

           // html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            Assert.assertEquals(section2, "1.2 Themes and Concepts of Biology", "'section2 is not displayed under study section'");

            boolean proficiencyBarDisplayed = new BooleanValue().presenceOfElement(494, By.cssSelector("div.ls-proficiency"));
            //Expected - Study tab should not contain Proficiency bar
            Assert.assertEquals(proficiencyBarDisplayed, false, "Proficiency bar is displayed");

            new DiagnosticTest().startTest(1);//Start diagnostic test
            new DiagnosticTest().DiagonesticTestQuitBetween(1, 3, "correct", false, false, false);//Continue the diagnostic test after attempting 3 questions

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(0);//Open chapter 1

            boolean proficiencyBarBeforeContinue = new BooleanValue().presenceOfElement(494, By.cssSelector("div.ls-proficiency"));
            //Expected - Study plan should not contain Proficiency bar
            Assert.assertEquals(proficiencyBarBeforeContinue, false, "Proficiency bar is displayed");

            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab

            boolean proficiencyBarUnderOrionAdaptiveBeforeContinue = new BooleanValue().presenceOfElement(494, By.cssSelector("div.ls-proficiency"));
            //Expected - Orion Adaptive Practice card should not contain Proficiency bar.
            Assert.assertEquals(proficiencyBarUnderOrionAdaptiveBeforeContinue, false, "Proficiency bar is displayed");

            new DiagnosticTest().continueDiagnosticTest();//Continue diagnostic test
            new DiagnosticTest().attemptAllCorrect(1, false, false);

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(0);//Open chapter 1

            boolean proficiencyBarUnderStudyTab = isDisplayed(494, lessonPage.list_proficiency);
            //Expected - Study should contain proficiency bar in sections based on TLO linked to the section
            Assert.assertEquals(proficiencyBarUnderStudyTab, true, "Proficiency bar is not displayed");

            boolean proficiencyBarUnderOrionTab = isDisplayed(494, lessonPage.list_proficiency);
            //Expected - Orion Adaptive Practice tab should contain Proficiency bar in sections based on linked TLO.
            Assert.assertEquals(proficiencyBarUnderOrionTab, true, "Proficiency bar is not displayed");
           // html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
        } catch (Exception e) {
            Assert.fail("Exception in method 'proficienctAtSectionLevel' in class 'StudyPlanEnhancement'", e);

        }
    }


    @Test(priority = 8, enabled = true)
    public void proficiencyPin() {
        try {
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

            new LoginUsingLTI().ltiLogin("553");//Login as a student
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(0);//Open chapter 1

            boolean postChapterSectionDisplayed = new BooleanValue().presenceOfElement(632,By.cssSelector("div[class='assignment-left-cart-wrapper']"));
            //Expected - Study tab should not contain post chapter section
            Assert.assertEquals(postChapterSectionDisplayed, false, "Post chapter section is displayed");

            new DiagnosticTest().startTest(1);//Start diagnostic test
            new DiagnosticTest().DiagonesticTestQuitBetween(1, 11, "correct", false, false, false);//Continue the diagnostic test after attempting 11 questions

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(0);//Open chapter 1

            boolean pinUnderStudyDisplayed = new BooleanValue().presenceOfElement(553, By.className("ls-proficiency-pin"));
           //Expected - Without attempting full diagnostic test proficiency bar should not contain any pin
            Assert.assertEquals(pinUnderStudyDisplayed, false, "Pin is displayed without attempting all the questions");

            boolean pinUnderOrionDisplayed = new BooleanValue().presenceOfElement(553, By.className("ls-proficiency-pin"));
            //Expected - Without attempting full diagnostic test proficiency bar should not contain any pin.
            Assert.assertEquals(pinUnderOrionDisplayed, false, "Pin is displayed without attempting all the questions");

            new DiagnosticTest().continueDiagnosticTest();//Continue diagnostic test
            new DiagnosticTest().attemptAllCorrect(1, false, false);//Attempt all the questions

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(0);//Open chapter 1
            /*WebElement html = driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));*/
            boolean pinUnderStudyAfterContinue = isDisplayed(553, lessonPage.proficiencyPin);
            //Expected - Pin in the proficiency bar should show the level of the student in that section
            Assert.assertEquals(pinUnderStudyAfterContinue, true, "Pin in the proficiency bar is not displayed");

            String toolTipText = toolTip(553,lessonPage.proficiencyPin);

            //Expected - On tooltip it should show “This is where you were after the Diagnostic".
            Assert.assertEquals(toolTipText, "This is where you were after the Diagnostic", "Tool tip is not displayed as expected");

            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab

            boolean proficiencyBar = isDisplayed(553, lessonPage.list_proficiency);
            //Expected - Orion Adaptive Practice tab should contain Proficiency bar.
            Assert.assertEquals(proficiencyBar, true, "Proficiency bar is not displayed");

            String toolTipTextOrion = null;
            try {
                int i = 0;

                for (WebElement c : lessonPage.proficiencyPin) {
                    if (c.isDisplayed()) {
                        new MouseHover().mouserhoverbywebelement(lessonPage.proficiencyPin.get(i));
                        Thread.sleep(2000);
                        toolTipTextOrion = lessonPage.toolTiptext.get(1).getText();
                        break;
                    }
                    i++;
                }
            }catch (Exception e){
                Assert.fail("Exception while fetching the tooltip text");
            }

            //Expected - On tooltip it should show “This is where you were after the Diagnostic"
            Assert.assertEquals(toolTipTextOrion, "This is where you were after the Diagnostic", "Tool tip is not displayed as expected");

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2

            new DiagnosticTest().startTest(1);//Start diagnostic test
            new DiagnosticTest().DiagonesticTestQuitBetween(1, 11, "correct", false, false, true);//Quit the diagnostic test after attempting 11 questions

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2

             boolean pinDisplayed = isDisplayed(553,lessonPage.proficiencyPin);
            //Expected - Pin in the proficiency bar should show the level of the student in that section
            Assert.assertEquals(pinDisplayed, true, "Pin in the proficiency bar is not displayed");

            String toolTipTextAfterQuittingUnderStudy = toolTip(553,lessonPage.proficiencyPin);
            //Expected - On tooltip it should show “This is where you were after the Diagnostic"
            Assert.assertEquals(toolTipTextAfterQuittingUnderStudy, "This is where you were after the Diagnostic", "Tool tip text is not displayed as expected");

            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive tab
           // html.sendKeys(Keys.chord(Keys.CONTROL, "0"));

            String toolTipTextAfterQuittingUnderOrion = null;
            try {
                int i = 0;

                for (WebElement c : lessonPage.proficiencyPin) {
                    if (c.isDisplayed()) {
                        new MouseHover().mouserhoverbywebelement(lessonPage.proficiencyPin.get(i));
                        Thread.sleep(2000);
                        toolTipTextAfterQuittingUnderOrion = lessonPage.toolTiptext.get(1).getText();
                        break;
                    }
                    i++;
                }
            }catch (Exception e){
                Assert.fail("Exception while fetching the tooltip text");
            }

            //Expected - On tooltip it should show “This is where you were after the Diagnostic"
            Assert.assertEquals(toolTipTextAfterQuittingUnderOrion,"This is where you were after the Diagnostic","Tool tip text is not displayed as expected");


        } catch (Exception e) {
            Assert.fail("Exception in method 'proficiencyPin' in class 'StudyPlanEnhancement'", e);

        }
    }


    @Test(priority = 9,enabled = true)
    public void postChapterSection(){
        try{
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "632");
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "632");

            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);

            new LoginUsingLTI().ltiLogin("632_1");//Login as a student


            new LoginUsingLTI().ltiLogin("632");//Login as an instructor
            /*WebElement html = driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));*/

            new Navigator().NavigateTo("My Question Bank");//click on my question bank

            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment

            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            Thread.sleep(3000);
            myQuestionBank.getAllChapters().click();//click on all chapters
            Thread.sleep(2000);
            List<WebElement> list = driver.findElements(By.xpath("//a[@title='Ch 2: The Chemical Foundation of Life']"));
            for(WebElement ele:list) {
                if(ele.isDisplayed()) {
                    ele.click();
                }
            }


            myQuestionBank.getGo_Button().click();//click on go
            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("632");//select a question

            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(632);//assign assignment

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            //html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            new TopicOpen().chapterOpen(1);
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='New Name']")));

            //Expected - Assignments should be exist in Post chapter
            Assert.assertEquals(lessonPage.postChapterSection.getText(),"Post Chapter","Post chapter is not displayed");
            Assert.assertEquals(lessonPage.postChapterAssignment.getAttribute("title"),"New Name","Assignment is not appearing in Post Chapter");


            new LoginUsingLTI().ltiLogin("632_1");//Login as a student
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 1

            //Expected - Study should contain post chapter section.
            Assert.assertEquals(lessonPage.postChapterSection.getText(), "Post Chapter", "Post chapter is not displayed");

            //Expected - "Post chapter" should contain assigned chapter level assignment
            Assert.assertEquals(lessonPage.postChapterAssignment.getAttribute("title"), "New Name", "Assignment is not appearing in Post Chapter");

            //Expected  - Date should be in "(Due Date:<month><date>,<year>)" format
            if(!lessonPage.assignment_dueDate.getText().contains("15, 201")){
               Assert.fail("Due date is not displayed as expected");
           }

            new SelectCourse().selectInvisibleAssignment("New Name");
            new AttemptQuestion().trueFalse(false, "correct", 632_1);
            lessonPage.button_finish.click();//Click on finish button

          //  new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TOCShow().chaptertree();
            new TopicOpen().chapterOpen(1);//Open chapter 1

            boolean proficiency = new BooleanValue().presenceOfElement(632, By.className("ls-proficiency-percentage"));
            //Expected - Proficiency should NOT show for “Post-Chapter” section.
            Assert.assertEquals(proficiency, false, "Proficiency is displayed for post chapter");

        }catch (Exception e){
            Assert.fail("Exception in method 'postChapterSection' in class 'StudyPlanEnhancement'", e);

        }
    }


    @Test(priority = 10,enabled = true)
    public void helpPopUp(){
        try{
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

            new LoginUsingLTI().ltiLogin("711");//Login as a student
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook

            //Expected - Help popup should be displayed.
            Assert.assertEquals(lessonPage.helpPopUp.isDisplayed(), true, "Help pop up is not displayed");

            //Expected - Pop up should be contain header text "Search"
            Assert.assertEquals(lessonPage.helpTitle.getText(), "Search", "Help title is is not containing text as 'Search'");

            //Expected - Pop up should be contain a button "next"
            Assert.assertEquals(lessonPage.button_next_helpPopUp.getText(), "Next", "Next button is not displayed on help pop up");

            //Expected - Pop up should be contain a cross button after header text right side.
            Assert.assertEquals(lessonPage.close_helpPopUp.isDisplayed(), true, "Close button is not displayed on help pop up");

            lessonPage.close_helpPopUp.click();//click on close button
            new UIElement().waitTillInvisibleElement(By.className("swhelp-content-wrapper"));
            boolean popUpDisplayed = new BooleanValue().presenceOfElement(711,By.className("swhelp-content-wrapper"));
            //Expected - Pop up should be get closed
            Assert.assertEquals(popUpDisplayed, false, "Pop up is displayed after clicking on close button");

            lessonPage.button_help.click();//Click on help button

            //Expected - Same pop up should be display
            Assert.assertEquals(lessonPage.helpPopUp.isDisplayed(),true,"Help pop up is not displayed");
            Assert.assertEquals(lessonPage.helpTitle.getText(), "Search", "Help title is is not containing text as 'Search'");
            Assert.assertEquals(lessonPage.button_next_helpPopUp.isDisplayed(), true, "Next button is not displayed on help pop up");
            Assert.assertEquals(lessonPage.close_helpPopUp.isDisplayed(), true, "Close button is not displayed on help pop up");

            lessonPage.close_helpPopUp.click();//click on close button
            Thread.sleep(6000);

            boolean popUpDisplayed1 = new BooleanValue().presenceOfElement(711,By.className("swhelp-content-wrapper"));
            //Expected - Pop up should be get closed
            Assert.assertEquals(popUpDisplayed1, false, "Pop up is displayed after clicking on close button");

            lessonPage.button_help.click();//Click on help button

            lessonPage.button_next_helpPopUp.click();//Click on next button

            //Expected - Pop up should be contain header text "Adaptive practice".
            Assert.assertEquals(lessonPage.helpTitle.getText(), "Adaptive practice", "Help title is is not containing text as 'Adaptive practice'");

            //Expected - Pop up should be contain a button "next" and cross button as same as seen before.
            Assert.assertEquals(lessonPage.button_next_helpPopUp.getText(), "Next", "Next button is not displayed on help pop up");
            Assert.assertEquals(lessonPage.close_helpPopUp.isDisplayed(), true, "Close button is not displayed on help pop up");

            lessonPage.button_next_helpPopUp.click();//Click on next button

            //Expected - Pop up should be contain header text "Open in new tab"
            Assert.assertEquals(lessonPage.helpTitle.getText(), "Open in new tab", "Help title is is not containing text as 'Open in new tab'");

            //Expected - Pop up should be contain a button "next" and cross button  as same as seen before.
            Assert.assertEquals(lessonPage.button_next_helpPopUp.getText(), "Next", "Next button is not displayed on help pop up");
            Assert.assertEquals(lessonPage.close_helpPopUp.isDisplayed(), true, "Close button is not displayed on help pop up");

            lessonPage.button_next_helpPopUp.click();//Click on next button

            //Expected - Pop up should be contain header text "Section level proficiency".
            Assert.assertEquals(lessonPage.helpTitle.getText(),"Section level Proficiency","Help title is is not containing text as 'Section level proficiency'");

            //Expected - It should be contain text in body "Current Secton level Proficiency".
            Assert.assertEquals(lessonPage.bodyText_helpPopUp.getText(),"Current section level proficiency","Body text of help pop up is not displayed as expected");

            //Expected - Body of pop up should be contain Proficiency bar with percent value.
            Assert.assertEquals(lessonPage.proficiencyBar_helpPopUp.isDisplayed(),true,"Proficiency bar is not displayed");
            if(!lessonPage.proficiencyPercentage_helpPopUp.getText().contains("%")){
                Assert.fail("Proficiency percentage is not displayed in help pop up");
            }

            //Expected - After line body should be contain text "View current level section proficiency"
            Assert.assertEquals(lessonPage.textBelowLine_helpPopUp.getText(), "View current section level proficiency", "Text is not displayed as expected");

            //Expected - Pop up should be contain a button "Get started" and cross button  as same as seen before.
            Assert.assertEquals(lessonPage.close_helpPopUp.isDisplayed(), true, "Close button is not displayed on help pop up");
            Assert.assertEquals(lessonPage.button_next_helpPopUp.getText(), "Get Started", "'Get Started' button is not displayed");

            lessonPage.button_next_helpPopUp.click();//Click on get started
            Thread.sleep(6000);

            boolean popUpLastDisplayed = new BooleanValue().presenceOfElement(711,By.className("swhelp-content-wrapper"));
            //Expected - Pop up should be get closed
            Assert.assertEquals(popUpLastDisplayed,false,"Pop up is displayed after clicking on close button");

        }catch (Exception e){
            Assert.fail("Exception in method 'helpPopUp' in class 'StudyPlanEnhancement'", e);

        }
    }


    @Test(priority = 11,enabled = true)
    public void enhancedAdaptivePractice(){
        try{
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            Preview preview = PageFactory.initElements(driver,Preview.class);
            EngagementReport engagementReport = PageFactory.initElements(driver,EngagementReport.class);

            new Assignment().create(808);//CMS login and create an assignment
            new LoginUsingLTI().ltiLogin("808_1");//Login as a student

            new LoginUsingLTI().ltiLogin("808");//Login as an instructor
            new Assignment().assignToStudent(808);
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2

            lessonPage.static_arrow1.click();//Click on static arrow of chapter 2
            lessonPage.tryIt_link.click();//Click on try it link

            String windowHandleBefore = driver.getWindowHandle();
            for(String window : driver.getWindowHandles()){
                driver.switchTo().window(window);
            }

            //Expected - "Try" button should be Navigate to test in new browser pop up window
            Assert.assertEquals(lessonPage.button_checkAnswer.isDisplayed(),true,"New window is not opened as 'Check answer button is not displayed'");

            driver.close();
            driver.switchTo().window(windowHandleBefore);

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.static_arrow1.click();//Click on static arrow of chapter 2
            lessonPage.assignThis_link.click();//Click on assign this link
            new AssignLesson().assignTOCWithDefaultClassSection(808);//Assign to class

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.static_arrow1.click();//Click on static arrow of chapter 2
            lessonPage.newTab_chapter2.click();//Click on new tab
            Thread.sleep(2000);

            //Expected - New tab icon should be use to open the preview in a new etextbook tab.
            Assert.assertEquals(lessonPage.title_previewPage.getText(),"(P2.1) 2.1 Concept Check","New tab is not navigated to preview page");

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            Thread.sleep(4000);

            String section1 = lessonPage.section_21UnderStudyTab.getText();
            String section2 = lessonPage.section_22UnderStudyTab.getText();
            String section3 = lessonPage.section_23UnderStudyTab.getText();

            //Expected - The chapter view should show chapter options in the column one.
            //Assert.assertEquals(lessonPage.getIntroductionUnderLesson().get(0).getText(), "Introduction", "'Introduction is not displayed under study section'");
            Assert.assertEquals(section1, "2.1 Atoms, Isotopes, Ions, and Molecules: The Building Blocks", "'Section1 is not displayed under study section'");
            Assert.assertEquals(section2, "2.2 Water", "'section2 is not displayed under study section'");
            Assert.assertEquals(section3, "2.3 Carbon", "'section3 is not displayed under study section'");

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(0);//Open chapter 1

            //Expected - The right part of the chapter view should have two tabs as
            // Study tab and Orion Adaptive Practice tab
            Assert.assertEquals(lessonPage.orionAdaptivePracticeTab.getText(),"ORION Adaptive Practice","Orion Adaptive Practice tab is not displayed");
            Assert.assertEquals(lessonPage.studyPlanTab.getText(),"Study","Study tab is not displayed");

            //Expected - Study plan header should contain chapter name.
            Assert.assertEquals(lessonPage.chapterNameInHeader.getText(),"Ch 1: The Study of Life","Chapter name is not displayed on header");

            //Expected - Header should be contain "Assign this" logo.
            Assert.assertEquals(lessonPage.assignLogo_header.isDisplayed(),true,"Assign logo is not displayed");

            //Expected - Header should be contain close icon to close e-textbook.
            Assert.assertEquals(lessonPage.getCrossIcon().isDisplayed(),true,"Cross icon is not displayed on header");

            //Expected - In the right part of the study plan , study tab should be opened by default for chapter view.
            Assert.assertEquals(lessonPage.studyPlanTab.getAttribute("class"),"tab active","Study tab is not opened by default");

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            Thread.sleep(2000);

            //Expected - "Study" tab should be contain " lesson, assignment and static assessment".
            Assert.assertEquals(lessonPage.getIntroductionUnderLesson().get(1).isDisplayed(), true, "Study tab does not have 'introduction' as a lesson");
            if(!lessonPage.staticPracticeName_2nd.getText().contains("2.1 Concept Check")){
                Assert.fail("Study tab does not have 'static assessment");
            }
            if (!lessonPage.list_postChapterAssignment.get(0).getText().contains("IT204_static Assessment_808")) {
                Assert.fail("Study tab does not have an assignment");
            }

            //Expected - In first column section name should be display in "<section_number> <section_name>" format.
            Assert.assertEquals(lessonPage.sectionFormat.getText(),"2.2 Water","Section name is not displayed as expected");

            boolean arrowDisplayed = isDisplayed(808,lessonPage.arrowInstructorSide);
            //Expected - In end of  lesson/assessment/assignment name in second column a arrow icon should be display.
            Assert.assertEquals(arrowDisplayed,true,"Arrow is not displayed");

            Thread.sleep(2000);
            lessonPage.getCrossIcon().click();//Close the assignments

            //Expected - user should be able to close assignment tab
            Assert.assertEquals(lessonPage.getCrossIcon().isDisplayed(),false,"Assignments are not close after clicking on close");

            new Navigator().NavigateTo("Current Assignments");//Navigate to current assignment

            //Expected - Assignment should be display in current assignment.
            System.out.println("currentAssignments.getList_assignmentName::"+currentAssignments.getList_assignmentName().get(0).getAttribute("assignmentname"));
            Assert.assertEquals(currentAssignments.getList_assignmentName().get(0).getAttribute("assignmentname"), "IT204_static Assessment_808","Assignment is not displayed in current assignment");

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2

            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab

            //Expected - Diagnostic name should be display in "Diagnostic: ch<chapter_number>: <chapter_name>" format in first colum.
            Assert.assertEquals(lessonPage.list_diagnosticCard.get(1).getText(),"Diagnostic - Ch 2: The Chemical Foundation of Life","Diagnostic name is not displayed as expected");

            //Expected - "Preview" Button should be display in second column
            Assert.assertEquals(lessonPage.previewButton.get(2).isDisplayed(),true,"Preview button is not displayed");

            lessonPage.previewButton.get(2).click();//Click on 1st (Diagnostic) preview button
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@title='Diagnostic - Ch 2: The Chemical Foundation of Life']")));

            //Expected - Diagnostic preview tab should get rendered
            Assert.assertEquals(preview.previewTab_diagnostic_2ndChapter.getAttribute("class"),"tab_title","Diagnostic preview tab is not rendered");

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab

            boolean arrowUnderOrionDisplayed = isDisplayed(808,lessonPage.arrowInstructorSide);
            //Expected - In First row End of second column should be contain a Arrow icon .
            Assert.assertEquals(arrowUnderOrionDisplayed,true,"Arrow is not displayed");

            lessonPage.diagnostic_Arrow.click();//Click on arrow

            lessonPage.tryIt_link.click();//Click on try it link

            String windowHandleBefore1 = driver.getWindowHandle();
            for(String window1 : driver.getWindowHandles()){
                driver.switchTo().window(window1);
            }

            //Expected - Diagnostic test should get rendered in a new tab
            Assert.assertEquals(lessonPage.button_checkAnswer.isDisplayed(),true,"Diagnostic test is not opened in new tab");

            driver.close();

             boolean newBrowserDisplayed = new BooleanValue().presenceOfElement(808,By.id("question-reveview-submit"));
            //Expected - User should be able to close browser pop up.
            Assert.assertEquals(newBrowserDisplayed,false,"browser pop up is not closed as check answer button is still displayed");

            driver.switchTo().window(windowHandleBefore1);

            lessonPage.diagnostic_Arrow.click();//Click on arrow
            lessonPage.newTab_chapter2.click();//Click on new tab

            //Expected - User should be able to view Diagnostic test preview in new tab..
            Assert.assertEquals(preview.previewTab_diagnostic_2ndChapter.getAttribute("class"),"tab_title","Diagnostic preview tab is not rendered");

            preview.list_closeTab.get(3).click();//Close the preview tab opened

            boolean previewDisplayedAfterClose = new BooleanValue().presenceOfElement(808,By.xpath("//span[@title='Diagnostic - Ch 2: The Chemical Foundation of Life']"));
            //Expected - User should able to close tab.
            Assert.assertEquals(previewDisplayedAfterClose,false,"Preview tab is not closed after clicking on close button");

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2

            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab

            //Expected - Second row should be contain "Orion Adaptive Practice' card
            Assert.assertEquals(lessonPage.adaptiveAssessmentCard.get(1).getText(),"ORION Adaptive Practice");

            lessonPage.previewButton.get(3).click();//Click on Adaptive Practice preview button
            Thread.sleep(3000);
            //Expected - Adaptive practice preview should get rendered
            Assert.assertEquals(preview.previewTab_adaptivePractice_2ndChapter.getAttribute("class"),"tab_title","Adaptive Practice preview is not rendered");

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab

            lessonPage.practice_Arrow.click();//Click on practice arrow
            lessonPage.tryIt_link.click();//Click on try it link

            String windowHandleBeforeAdaptivePractice = driver.getWindowHandle();
            for(String windowAdaptivePractice : driver.getWindowHandles()){
                driver.switchTo().window(windowAdaptivePractice);
            }

            //Expected - Diagnostic test should get rendered in a new tab
            Assert.assertEquals(lessonPage.button_checkAnswer.isDisplayed(),true,"Diagnostic test is not opened in new tab");

            driver.close();

            boolean newBrowserDisplayedAdaptivePractice = new BooleanValue().presenceOfElement(808,By.id("question-reveview-submit"));
            //Expected - User should be able to close browser pop up.
            Assert.assertEquals(newBrowserDisplayedAdaptivePractice,false,"browser pop up is not closed as check answer button is still displayed");

            driver.switchTo().window(windowHandleBeforeAdaptivePractice);

            lessonPage.practice_Arrow.click();//Click on practice arrow
            lessonPage.newTab_chapter2.click();//Click on new tab icon

            //Expected - User should be able to view Practice test preview in new tab..
            Assert.assertEquals(preview.previewTab_adaptivePractice_2ndChapter.getAttribute("class"),"tab_title","Practice preview tab is not opened");

            preview.list_closeTab.get(3).click();//Close the preview tab opened

            boolean previewPracticeDisplayedAfterClose = new BooleanValue().presenceOfElement(808,By.xpath("//span[@title='Diagnostic - Ch 2: The Chemical Foundation of Life']"));
            //Expected - User should able to close tab.
            Assert.assertEquals(previewPracticeDisplayedAfterClose,false,"Preview tab is not closed after clicking on close button");

            WebElement html = driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2

            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab
            Thread.sleep(2000);
            lessonPage.practice_Arrow.click();//Click on practice arrow

            lessonPage.assignThis_link.click();//Click on assign this link
            new AssignLesson().assignTOCWithDefaultClassSection(808);//Assign to class
            Thread.sleep(2000);
            new Navigator().NavigateTo("Current Assignments");

            //Expected - Assignment should be display in current assignment.
            Assert.assertEquals(currentAssignments.getList_assignmentName().get(0).getAttribute("assignmentname"),"IT204_static Assessment_808","Assignment is not displayed under current assignment");

            new Navigator().NavigateTo("Engagement Report");//Navigate to engagement report
            engagementReport.assessment.click();//Click on assessment

            //Expected - 2 tab view should be displayed for study plan
            Assert.assertEquals(lessonPage.studyPlanTab.getAttribute("class"),"tab active","Study tab is not displayed");
            Assert.assertEquals(lessonPage.orionAdaptivePracticeTab.getAttribute("class"),"tab_title","Orion adaptive practice tab is not displayed");

        }catch (Exception e){
            Assert.fail("Exception in method 'enhancedAdaptivePractice' in class 'StudyPlanEnhancement'", e);

        }
    }


    @Test(priority = 12,enabled = true)
    public void suggestedNextActivity(){
        try{
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);

            new LoginUsingLTI().ltiLogin("1042");//Login as an instructor
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(0);//Open chapter 1

            lessonPage.getLessonIcon().click();//Click on 1st lesson
            lessonPage.newTab_chapter2.click();//Click on new tab

            //Expected - Lesson/static test should appear as link over footer
           //Assert.assertEquals(lessonPage.getPreviousChapterFooter().get(0).getText(),"Introduction","Lessonis not displayed as a link over footer");
              Assert.assertEquals(driver.findElement(By.xpath("(//a[@title='1.1 Concept Check'])[2]")).getText(),"1.1 Concept Check","Static test is not displayed as a link over footer");

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.staticArrow_2.click();//Click on static arrow
            Thread.sleep(2000);
            lessonPage.newTab_chapter2.click();//Click on new tab
            Thread.sleep(4000);

            System.out.println("previous "+lessonPage.getPreviousChapterFooter().get(0).getText());
            System.out.println("next "+lessonPage.getNextChapterFooter().get(0).getText());
            //Expected - Lesson/static test should appear as link over footer
            Assert.assertEquals(lessonPage.getPreviousChapterFooter().get(1).getAttribute("title"),"2.2: Water","Lessonis not displayed as a link over footer");
          //  Assert.assertEquals(lessonPage.getNextChapterFooter().get(0).getAttribute("title"),"2.3: Carbon","Static test is not displayed as a link over footer");

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab

            lessonPage.diagnostic_Arrow.click();
            lessonPage.newTab_chapter2.click();//Click on new tab
            Thread.sleep(2000);
            //Expected - Footer navigation option should not be available
            Assert.assertEquals(lessonPage.getPreviousChapterFooter().get(0).isDisplayed(), false, "Previous footer is present");
            Assert.assertEquals(lessonPage.getNextChapterFooter().get(0).isDisplayed(), false, "Next footer is present");

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab
            lessonPage.practice_Arrow.click();//Click on practice arrow

            //Expected - Footer navigation option should not be available
         /*   Assert.assertEquals(lessonPage.getPreviousChapterFooter().get(0),false,"Previous footer is present");
            Assert.assertEquals(lessonPage.getNextChapterFooter().get(0),false,"Next footer is present");*/


        }catch (Exception e){
            Assert.fail("Exception in method 'suggestedNextActivity' in class 'StudyPlanEnhancement'", e);

        }
    }


    @Test(priority = 13,enabled = true)
    public void performanceLSCourse(){
        try{
            LessonPage lessonPage =PageFactory.initElements(driver,LessonPage.class);
            PerformanceSummaryReport performanceSummary = PageFactory.initElements(driver,PerformanceSummaryReport.class);
            ProficiencyReport proficiencyReport = PageFactory.initElements(driver,ProficiencyReport.class);

            new Assignment().create(1104);
            new Assignment().addQuestions(1104,"truefalse","");
            new LoginUsingLTI().ltiLogin("1104");//Login as a student
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(0);//Open chapter 1

            //Expected - Header should contain chapter name "Ch 'chapter number' : 'chapter name' " Format in white color left side of header .
            Assert.assertEquals(lessonPage.chapterNameInHeader.getText(),"Ch G: Introduction: World Regional Geography: Global Perspectives","Chapter name is not displayed in a format as expected");

            //Expected - Right side of header should contain "cross icon" in circle.
            Assert.assertEquals(lessonPage.getCrossIcon().isDisplayed(),true,"Cross icon is not displayed");

            boolean performancePercentage = new BooleanValue().presenceOfElement(1104,By.xpath("//div[@class='chapter-report-score-wrapper']/descendant::span[2]"));
            //Expected - Header for Study should not contain Performance percent.
            Assert.assertEquals(performancePercentage,false,"Performance percentage is displayed");

            new SelectCourse().selectInvisibleAssignment("IT204_static Assessment_1104");
            Thread.sleep(2000);

            lessonPage.options.get(1).click();//Select option B
            lessonPage.button_submitStatic.click();
            Thread.sleep(2000);
            lessonPage.button_next.click();

            new Click().clickByclassname("al-quit-diag-test-icon");//Quit static Test

            boolean performancePercentageAfterQuit = new BooleanValue().presenceOfElement(1104,By.xpath("//div[@class='chapter-report-score-wrapper']/descendant::span[2]"));
            //Expected - Header for Study should not contain Performance percent.
            Assert.assertEquals(performancePercentageAfterQuit,false,"Performance percentage is displayed");

            new Navigator().NavigateTo("e-Textbook");
            new SelectCourse().selectInvisibleAssignment("IT204_static Assessment_1104");

            lessonPage.options.get(0).click();//Select option A
            lessonPage.button_submitStatic.click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("//div[@id='footer-info-content']/div[4]/input")).click();
            Thread.sleep(3000);

            new Navigator().NavigateTo("e-Textbook");

            //Expected - Header for Study should contain Performance percent.
            if(!lessonPage.performance_header.getText().contains("PERFORMANCE :")){
                Assert.fail("Performance is not displayed");
            }

            String performancePercentageOnHeader = lessonPage.proficiencyPercentage_header.getText();

           // Assert.assertEquals(performancePercentageOnHeader,"50%","Performance percentage is not displayed as expected");
            if(!performancePercentageOnHeader.contains("%")){
                Assert.fail("Performance percentage is not displayed as expected");
            }

            if(performancePercentageOnHeader.contains(".")){
                Assert.fail("Performance percentage is not displayed in whole number");
            }

            new Navigator().NavigateTo("Performance Report");//Navigate to performance report
            performanceSummary.list_report.get(2).click();

            //Expected - Performance percentage in section equal to Performance percentage display on Performance Report in Chapter Performance Summary.
            Assert.assertEquals(proficiencyReport.getCourseProficiency().getText()+"%",performancePercentageOnHeader,"Performance percentage on performance summary report is not same as in chapter header");


            new Navigator().NavigateTo("e-Textbook");

            new TopicOpen().chapterOpen(1);//Open chapter 1A
            new SelectCourse().selectInvisibleAssignment("1A.2 Concept Check");//Select static test
            lessonPage.button_submitStatic.click();//Click on submit button
            Thread.sleep(2000);
            //lessonPage.getButton_finish_lsCourse.click();//Click on finish button
            driver.findElement(By.xpath("//div[@id='footer-info-content']/div[2]/input")).click();

            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(1);//Open chapter 1A

            //Expected - Header for Study should contain performance percent(0%).
            Assert.assertEquals(lessonPage.proficiencyPercentage_header.getText(),"0%","Performance percentage is not displayed as 0%");

        }catch (Exception e){
            Assert.fail("Exception in method 'performanceLSCourse' in class 'StudyPlanEnhancement'", e);

        }
    }

    @Test(priority = 14,enabled = true)
    public void performanceSection(){
        try{
            LessonPage lessonPage =PageFactory.initElements(driver,LessonPage.class);
            ProficiencyReport proficiencyReport = PageFactory.initElements(driver,ProficiencyReport.class);

            new LoginUsingLTI().ltiLogin("1193");//Login as a student
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(1);//Open chapter 1A

            boolean performanceBarPresent = new BooleanValue().presenceOfElement(1193,By.cssSelector("div.ls-proficiency"));
            //Expected - Sections should not contain Performance bar
            Assert.assertEquals(performanceBarPresent,false,"Performance bar is displayed");

            new SelectCourse().selectInvisibleAssignment("1A.3 Concept Check");//Select static test
            lessonPage.button_submitStatic.click();//Click on submit button
            Thread.sleep(2000);
            driver.findElement(By.xpath("//div[@id='footer-info-content']/div[2]/input")).click();
            Thread.sleep(3000);

            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(1);//Open chapter 1A

            new SelectCourse().selectInvisibleAssignment("1A.2 Concept Check");//Select static test

            new SelectAnswerAndSubmit().staticanswersubmit("A");

            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(1);//Open chapter 1A

            String proficiencyPercentageAt1A_2 = lessonPage.proficiencyPercentage.get(0).getText();
            String proficiencyPercentageAt1A_3 = lessonPage.proficiencyPercentage.get(1).getText();

            //Expected - Sections should contain Performance bar.
            Assert.assertEquals(lessonPage.list_proficiency.get(0).isDisplayed(),true,"Performance bar is not displayed");
            Assert.assertEquals(lessonPage.list_proficiency.get(1).isDisplayed(),true,"Performance bar is not displayed");

            //Expected - Section should contain Performance percent after performance bar
            if(!lessonPage.list_proficiency.get(0).getText().contains("%")){
                Assert.fail("Performance percentage is not displayed as expected");
            }

            if(!lessonPage.list_proficiency.get(1).getText().contains("%")){
                Assert.fail("Performance percentage is not displayed as expected");
            }

            if(lessonPage.list_proficiency.get(0).getText().contains(".")){
                Assert.fail("Performance percentage is not displayed in whole number");
            }


            if(lessonPage.list_proficiency.get(1).getText().contains(".")){
                Assert.fail("Performance percentage is not displayed in whole number");
            }


            new Navigator().NavigateTo("Performance Report");//Navigate to performance report

            new Click().clickbyxpath("(//a[@title='Show All Chapters'])[1]");
            new Click().clickbyxpath("//a[@title='Ch 1A: The European Realm']");
            new Click().clickbyxpath("(//a[@title='All Sections'])[1]");
            new Click().clickbyxpath("//a[@title='1A.2: Geographical Features']");

            //Expected - Performance percentage in section equal to performance percentage display on Performance Report
            Assert.assertEquals(proficiencyPercentageAt1A_2,proficiencyReport.getCourseProficiency().getText()+"%","Performance percentage in section is not equal to performance percentage display on Performance Report");

            new Click().clickbyxpath("//a[@title='1A.3: Ancient Europe']");

            Assert.assertEquals(proficiencyPercentageAt1A_3,proficiencyReport.getCourseProficiency().getText()+"%","Performance percentage in section is not equal to performance percentage display on Performance Report");

        }catch (Exception e){
            Assert.fail("Exception in method 'performanceSectionLevelLSCourse' in class 'StudyPlanEnhancement'", e);

        }
    }


    @Test(priority = 15,enabled = true)
    public void postChapterSectionLS(){
        try{
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver,MyQuestionBank.class);
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "1260");
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "1260");

            new LoginUsingLTI().ltiLogin("1260_1");//Login as an instructor
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(2);//Open chapter 1B

            boolean postChapterDisplayed = new BooleanValue().presenceOfElement(1260,By.cssSelector("div[class='assignment-left-cart-wrapper']"));
            //Expected - Study should not contain post chapter section
            Assert.assertEquals(postChapterDisplayed,false,"Post chapter section is displayed");

            new LoginUsingLTI().ltiLogin("1260");//Login as a student
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(2);//Open chapter 1B

            boolean postChapterDisplayedStudent = new BooleanValue().presenceOfElement(1260,By.cssSelector("div[class='assignment-left-cart-wrapper']"));
            //Expected - Study should not contain post chapter section
            Assert.assertEquals(postChapterDisplayedStudent,false,"Post chapter section is displayed");

            new LoginUsingLTI().ltiLogin("1260_1");//Login as an instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllChapters().click();//click on all chapters
            Thread.sleep(2000);
            myQuestionBank.getEuropeanRegion().click();//click on chapter
            myQuestionBank.getGo_Button().click();//click on go
            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("1260");//select a question

            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new AssignLesson().Assigncustomeassignemnt(1260);//assign assignment

            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(1);//Open chapter 1B

            //Expected - Assignments exist in Post chapter.
            Assert.assertEquals(lessonPage.postChapterSection.getText(),"Post Chapter","Post chapter is not displayed");
            Assert.assertEquals(lessonPage.postChapterAssignment.getAttribute("title"),"New Name","Assignment is not appearing in Post Chapter");

            new LoginUsingLTI().ltiLogin("1260");//Login as a student
             new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(1);//Open chapter 1B

            //Expected - Assignments exist in Post chapter.
            Assert.assertEquals(lessonPage.postChapterSection.getText(),"Post Chapter","Post chapter is not displayed");
            Assert.assertEquals(lessonPage.postChapterAssignment.getAttribute("title"),"New Name","Assignment is not appearing in Post Chapter");

            new SelectCourse().selectInvisibleAssignment("New Name");
            new AttemptQuestion().trueFalse(false,"correct",632_1);
            lessonPage.button_finish.click();//Click on finish button

            //new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
             new TOCShow().chaptertree();
            new TopicOpen().chapterOpen(0);//Open chapter 1

            boolean proficiency = new BooleanValue().presenceOfElement(632, By.className("ls-proficiency-percentage"));
            //Expected - Performance should NOT show for “Post-Chapter” section.
            Assert.assertEquals(proficiency,false,"Proficiency is displayed for post chapter");


        }catch (Exception e){
            Assert.fail("Exception in method 'performanceSectionLevelLSCourse' in class 'StudyPlanEnhancement'", e);

        }
    }


    @Test(priority = 16,enabled = true)
    public void helpPopUpLSCourse(){
        try{
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

            new LoginUsingLTI().ltiLogin("1340");//Login as a student
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook

            //Expected - Help popup should be displayed.
            new UIElement().waitAndFindElement(lessonPage.helpPopUp);
            Assert.assertEquals(lessonPage.helpPopUp.isDisplayed(), true, "Help pop up is not displayed");

            //Expected - Pop up should be contain header text "Search"
            Assert.assertEquals(lessonPage.helpTitle.getText(),"Search","Help title is is not containing text as 'Search'");

            //Expected - Pop up should be contain a button "next"
            Assert.assertEquals(lessonPage.button_next_helpPopUp.getText(),"Next","Next button is not displayed on help pop up");

            //Expected - Pop up should be contain a cross button after header text right side.
            Assert.assertEquals(lessonPage.close_helpPopUp.isDisplayed(),true,"Close button is not displayed on help pop up");

            lessonPage.close_helpPopUp.click();//click on close button
            new UIElement().waitTillInvisibleElement(By.className("swhelp-content-wrapper"));
            boolean popUpDisplayed = new BooleanValue().presenceOfElement(1340,By.className("swhelp-content-wrapper"));
            //Expected - Pop up should be get closed
            Assert.assertEquals(popUpDisplayed,false,"Pop up is displayed after clicking on close button");

            lessonPage.button_help.click();//Click on help button

            //Expected - Same pop up should be display
            Assert.assertEquals(lessonPage.helpPopUp.isDisplayed(),true,"Help pop up is not displayed");
            Assert.assertEquals(lessonPage.helpTitle.getText(),"Search","Help title is is not containing text as 'Search'");
            Assert.assertEquals(lessonPage.button_next_helpPopUp.isDisplayed(),true,"Next button is not displayed on help pop up");
            Assert.assertEquals(lessonPage.close_helpPopUp.isDisplayed(),true,"Close button is not displayed on help pop up");

            lessonPage.close_helpPopUp.click();//click on close button
            Thread.sleep(6000);

            boolean popUpDisplayed1 = new BooleanValue().presenceOfElement(1340,By.className("swhelp-content-wrapper"));
            //Expected - Pop up should be get closed
            Assert.assertEquals(popUpDisplayed1,false,"Pop up is displayed after clicking on close button");
            Thread.sleep(2000);
            lessonPage.button_help.click();//Click on help button
            Thread.sleep(2000);
            lessonPage.button_next_helpPopUp.click();//Click on next button
            Thread.sleep(2000);
            //Expected - Pop up should be contain header text "Adaptive practice".
            Assert.assertEquals(lessonPage.helpTitle.getText(),"Adaptive practice","Help title is is not containing text as 'Adaptive practice'");
            Thread.sleep(2000);
            //Expected - Pop up should be contain a button "next" and cross button as same as seen before.
            Assert.assertEquals(lessonPage.button_next_helpPopUp.getText(),"Next","Next button is not displayed on help pop up");
            Assert.assertEquals(lessonPage.close_helpPopUp.isDisplayed(),true,"Close button is not displayed on help pop up");

            lessonPage.button_next_helpPopUp.click();//Click on next button

            //Expected - Pop up should be contain header text "Open in new tab"
            Assert.assertEquals(lessonPage.helpTitle.getText(),"Open in new tab","Help title is is not containing text as 'Open in new tab'");

            //Expected - Pop up should be contain a button "next" and cross button  as same as seen before.
            Assert.assertEquals(lessonPage.button_next_helpPopUp.getText(),"Next","Next button is not displayed on help pop up");
            Assert.assertEquals(lessonPage.close_helpPopUp.isDisplayed(),true,"Close button is not displayed on help pop up");

            lessonPage.button_next_helpPopUp.click();//Click on next button

            //Expected - Pop up should be contain header text "Section level proficiency".
            Assert.assertEquals(lessonPage.helpTitle.getText(),"Section level Proficiency","Help title is is not containing text as 'Section level proficiency'");

            //Expected - It should be contain text in body "Current Secton level Proficiency".
            Assert.assertEquals(lessonPage.bodyText_helpPopUp.getText(),"Current section level proficiency","Body text of help pop up is not displayed as expected");

            //Expected - Body of pop up should be contain Proficiency bar with percent value.
            Assert.assertEquals(lessonPage.proficiencyBar_helpPopUp.isDisplayed(),true,"Proficiency bar is not displayed");
            if(!lessonPage.proficiencyPercentage_helpPopUp.getText().contains("%")){
                Assert.fail("Proficiency percentage is not displayed in help pop up");
            }

            //Expected - After line body should be contain text "View current level section proficiency"
            Assert.assertEquals(lessonPage.textBelowLine_helpPopUp.getText(),"View current section level proficiency","Text is not displayed as expected");

            //Expected - Pop up should be contain a button "Get started" and cross button  as same as seen before.
            Assert.assertEquals(lessonPage.close_helpPopUp.isDisplayed(),true,"Close button is not displayed on help pop up");
            Assert.assertEquals(lessonPage.button_next_helpPopUp.getText(),"Get Started","'Get Started' button is not displayed");

            lessonPage.button_next_helpPopUp.click();//Click on get started
            Thread.sleep(6000);

            boolean popUpLastDisplayed = new BooleanValue().presenceOfElement(1340,By.className("swhelp-content-wrapper"));
            //Expected - Pop up should be get closed
            Assert.assertEquals(popUpLastDisplayed,false,"Pop up is displayed after clicking on close button");

        }catch (Exception e){
            Assert.fail("Exception in method 'helpPopUpLSCourse' in class 'StudyPlanEnhancement'", e);

        }
    }

    @Test(priority = 17,enabled = true)
    public void enhancedAdaptivePracticeLSCourse(){
        try{
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            Preview preview = PageFactory.initElements(driver,Preview.class);
            EngagementReport engagementReport = PageFactory.initElements(driver,EngagementReport.class);

            new Assignment().create(1440);//CMS login and create an assignment
            new LoginUsingLTI().ltiLogin("1440_1");//Login as a student

            new LoginUsingLTI().ltiLogin("1440");//Login as an instructor
            new Assignment().assignToStudent(1440);
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 1A

            lessonPage.static_arrow.click();//Click on static arrow of chapter 1A
            lessonPage.tryIt_link.click();//Click on try it link

            String windowHandleBefore = driver.getWindowHandle();
            for(String window : driver.getWindowHandles()){
                driver.switchTo().window(window);
            }

            //Expected - "Try" button should be Navigate to test in new browser pop up window
            Assert.assertEquals(lessonPage.button_checkAnswer.isDisplayed(),true,"New window is not opened as 'Check answer button is not displayed'");

            driver.close();
            driver.switchTo().window(windowHandleBefore);

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 1A
            lessonPage.static_arrow.click();//Click on static arrow of chapter 2
            lessonPage.assignThis_link.click();//Click on assign this link
            new AssignLesson().assignTOCWithDefaultClassSection(1440);//Assign to class

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 1A
            lessonPage.static_arrow.click();//Click on static arrow of chapter 1A
            lessonPage.newTab_chapter2.click();//Click on new tab
            Thread.sleep(2000);

            //Expected - New tab icon should be use to open the preview in a new etextbook tab.
            Assert.assertEquals(lessonPage.title_previewPageLS.getText(),"1A.2 Concept Check","New tab is not navigated to preview page");

            new LoginUsingLTI().ltiLogin("1440_1");//Login as a student
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Click on chapter 1A
            new SelectCourse().selectInvisibleAssignment("1A.2 Concept Check");
            new AttemptTest().StaticTest();

            new LoginUsingLTI().ltiLogin("1440");//Login as an instructor
            new Navigator().NavigateTo("Engagement Report");//Navigate to engagement report
            engagementReport.assessment.click();//Click on assessment

            //Expected - Study plan should continue to display in old view
            Assert.assertEquals(lessonPage.studyPageLSCourse.isDisplayed(),true,"Study plan is not displayed as earlier");

        }catch (Exception e){
            Assert.fail("Exception in method 'enhancedAdaptivePractice' in class 'StudyPlanEnhancement'", e);

        }
    }


    public String toolTip(int dataIndex, List<WebElement> we) {
        LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);
        String toolTipText = null;
        try{
            int i =0;

            for (WebElement c : we) {
                if (c.isDisplayed()) {
                    new MouseHover().mouserhoverbywebelement(lessonPage.proficiencyPin.get(i));
                    Thread.sleep(2000);
                    toolTipText = lessonPage.toolTiptext.get(0).getText();
                    break;
                }
                i++;
            }

        }catch (Exception e){
            Assert.fail("Exception in method 'proficiencyPin' in class 'StudyPlanEnhancement'", e);

        }
        return toolTipText;
}

    public String textVerify(int dataIndex, List<WebElement> we)
    {
        String text = null;
        try {
            for (WebElement c : we) {
                Coordinates coordinate = ((Locatable)c).getCoordinates();
                coordinate.inViewPort();
                if (c.isDisplayed()) {
                    text = c.getAttribute("textContent");
                    break;
                }
            }
         }catch (Exception e){
        Assert.fail("Exception in method 'textVerify' in class 'StudyPlanEnhancement'", e);
    }
        return text;
    }


    public boolean isDisplayed(int dataIndex, List<WebElement> we)
    {
        boolean elementFound=false;
        try
        {
            for(WebElement c : we)
            {
               if(c.isDisplayed())
               {
                 elementFound=true;
                   break;
               }
            }

        }catch (Exception e){
            Assert.fail("Exception in method 'isDisplayed' in class 'StudyPlanEnhancement'", e);

        }
        return elementFound;
    }


    public String getAttribute(int dataIndex, List<WebElement> we, String attribute)
    {
        String getAttributeText = null;
        try{
            int elementCount=0;
            for(WebElement c: we){
                if(c.isDisplayed())
                {
                    getAttributeText = we.get(elementCount).getAttribute(attribute);
                    break;
                }
                elementCount++;
            }
        }catch (Exception e){
            Assert.fail("Exception in method 'getAttribute' in class 'StudyPlanEnhancement'", e);

        }
        return getAttributeText;
    }


















}