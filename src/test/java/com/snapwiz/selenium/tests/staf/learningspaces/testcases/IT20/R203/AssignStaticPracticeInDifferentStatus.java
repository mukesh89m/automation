package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT20.R203;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.MyActivity.MyActivity;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.AssignmentTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.TocSearch;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.PerformanceSummaryReport;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ScrollElement;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by shashank on 20-05-2015.
 */
public class AssignStaticPracticeInDifferentStatus extends Driver{

    @Test(priority = 1,enabled = true)
    public void viewInProgressStaticPracticeAssignmentAdaptive()
    {
        try {
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            TocSearch tocSearch=PageFactory.initElements(driver,TocSearch.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            MyActivity myActivity=PageFactory.initElements(driver,MyActivity.class);
            PerformanceSummaryReport performanceSummaryReport=PageFactory.initElements(driver,PerformanceSummaryReport.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("60");//login as instructor
            new LoginUsingLTI().ltiLogin("60_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            boolean assessment_icon=tocSearch.assessment_icon_Adaptive.isDisplayed();
            Assert.assertEquals(assessment_icon, true, "Before the static practice was assigned , the Study is not showing the assessment with the default view");
            tocSearch.assignmentNameAdaptive.click();
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.al-quit-diag-test-icon")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='A']")));
            assignments.submitAnswer.click();
            for(int i=1;i<=3;i++) {
                try {
                    assignments.nextButton.click();
                    break;
                } catch (Exception e) {

                }
            }
            Thread.sleep(3000);
            driver.findElement(By.cssSelector("div.al-quit-diag-test-icon")).click();//quit test so that assessment status will be in In-Progress
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            Assert.assertEquals(tocSearch.assessment_icon_Adaptive.getAttribute("class"),"toc-icon inprogress","Assessment status not change to in Progress");

            new LoginUsingLTI().ltiLogin("60");//login as instructor
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            lessonPage.static_arrow1.click(); // click on the static arrow link
            //On clicking Assign icon, Assign pop-up should appear
            lessonPage.assignThis_link.click(); //click on the assign this link
            boolean assign_popup1 = lessonPage.assign_popup.isDisplayed();
            Assert.assertEquals(assign_popup1, true, "On clicking Assign icon, Assign pop-up is not displayed");
            new AssignLesson().assignTOCWithDefaultClassSection(60); //Assign Toc with default class section
            new TopicOpen().chapterOpen(1);

            new LoginUsingLTI().ltiLogin("60_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            int assignmentIcon = tocSearch.assignmentInProgress_icon.size();
            System.out.println("assignmentIcon:"+assignmentIcon);
            Assert.assertEquals(assignmentIcon,1,"The assessment icon in not changed to assignment icon.");

            String assignmentName=tocSearch.assignmentNameAdaptive.getAttribute("title").trim();
            Assert.assertEquals(assignmentName,"2.1 Concept Check","The name of the assignment should be the is not same as the assessment");
            Thread.sleep(2000);
            String dueDate=tocSearch.tocDueDate.getText();
            Assert.assertTrue(dueDate.contains("01,"),"Due Date of the assignment should be is not shown : The format should be the same as for other assignments in the Study Plan.");

            //Tc row no 48
            new Navigator().NavigateTo("Assignments"); //navigate to Assignments tab
            boolean assessmentName2 = currentAssignments.getAssignmentName().isDisplayed();
            Assert.assertEquals(assessmentName2,true,"Assignment entry of static practice is not present in Assignments tab of lesson page to which static practice is associated ");

            // Tc row no 50
            new Navigator().NavigateTo("Assignments"); //navigate to Assignments
            Assert.assertEquals(assignments.status_inProgress.getText().trim(),"Not Started","Assignment entry is not present in Assignments page showing \"Not started\" status");

            currentAssignments.getList_assignmentName().get(0).click();
            Thread.sleep(2000);
            //Tc row no 52
            new Navigator().NavigateTo("My Activity");
            Thread.sleep(5000);
            myActivity.assignment_card.click();  //click on the assessment card
            new WebDriverWait(driver,300).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title ")));
            new Assignment().submitButtonInQuestionClick(); //finish assignment
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            new SelectCourse().selectInvisibleAssignment("2.1 Concept Check");

            boolean performanceReport=performanceSummaryReport.report.isDisplayed();
            Assert.assertEquals(performanceReport,true,"performance is not generated");

            //Tc row no 54
            //Retake option should not be available
            boolean found= new BooleanValue().presenceOfElement(54,By.xpath("//div[text()='Retake']"));
            Assert.assertEquals(found,false,"Retake option is displayed");

            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(1);
            new SelectCourse().selectInvisibleAssignment("2.1 Concept Check"); //click on the assessment name

            boolean performanceReport1=performanceSummaryReport.report.isDisplayed();
            Assert.assertEquals(performanceReport1,true,"performance is not generated");



        } catch (Exception e) {
            Assert.fail("Exception in test case  viewInProgressStaticPracticeAssignmentAdaptive of class AssignStaticPracticeFromStudy",e);
        }
    }


    @Test(priority = 2,enabled = true)
    public void attemptedStaticPracticeAssignmentAdaptive()
    {
        try {
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            TocSearch tocSearch=PageFactory.initElements(driver,TocSearch.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            MyActivity myActivity=PageFactory.initElements(driver,MyActivity.class);
            PerformanceSummaryReport performanceSummaryReport=PageFactory.initElements(driver,PerformanceSummaryReport.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("70");//login as instructor
            new LoginUsingLTI().ltiLogin("70_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            boolean assessment_icon=tocSearch.assessment_icon_Adaptive.isDisplayed();
            Assert.assertEquals(assessment_icon, true, "Before the static practice was assigned , the Study is not showing the assessment with the default view");
            tocSearch.assignmentNameAdaptive.click();
            new SelectCourse().selectInvisibleAssignment("2.1 Concept Check");

            new SelectAnswerAndSubmit().staticanswersubmit("A");
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.al-quit-diag-test-icon")));
            new AttemptTest().StaticTest();//
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            Assert.assertEquals(tocSearch.assessment_icon_Adaptive.getAttribute("class"),"toc-icon completed","Assessment status not change to in Progress");

            new LoginUsingLTI().ltiLogin("70");//login as instructor
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            lessonPage.static_arrow1.click(); // click on the static arrow link

            //Tc row no 12
            //On clicking Assign icon, Assign pop-up should appear
            lessonPage.assignThis_link.click(); //click on the assign this link
            boolean assign_popup1 = lessonPage.assign_popup.isDisplayed();
            Assert.assertEquals(assign_popup1, true, "On clicking Assign icon, Assign pop-up is not displayed");
            new AssignLesson().assignTOCWithDefaultClassSection(70); //Assign Toc with default class section
            new TopicOpen().chapterOpen(1);
            new LoginUsingLTI().ltiLogin("70_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            int assignmentIcon = tocSearch.studentAssessment_icon1.size();
            System.out.println("assignmentIcon:"+assignmentIcon);
            Assert.assertEquals(assignmentIcon,1,"The assessment icon in not changed to assignment icon.");

            String assignmentName=tocSearch.assignmentNameAdaptive.getAttribute("title").trim();
            Assert.assertEquals(assignmentName,"2.1 Concept Check","The name of the assignment should be the is not same as the assessment");
            String dueDate=tocSearch.tocDueDate.getText();
            Assert.assertTrue(dueDate.contains("01,"),"Due Date of the assignment should be is not shown : The format should be the same as for other assignments in the Study Plan.");

            //Tc row no 48
            new Navigator().NavigateTo("Assignments"); //navigate to Assignments tab
            boolean assessmentName2 = currentAssignments.getAssignmentName().isDisplayed();
            Assert.assertEquals(assessmentName2,true,"Assignment entry of static practice is not present in Assignments tab of lesson page to which static practice is associated ");

            // Tc row no 50
            new Navigator().NavigateTo("Assignments"); //navigate to Assignments
            Assert.assertEquals(assignments.status_inProgress.getText().trim(),"Not Started","Assignment entry is not present in Assignments page showing \"Not started\" status");

            currentAssignments.getList_assignmentName().get(0).click();
            Thread.sleep(2000);
            //Tc row no 52
            new Navigator().NavigateTo("My Activity");
            Thread.sleep(5000);
            myActivity.assignment_card.click();  //click on the assessment card
            new WebDriverWait(driver,300).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title ")));
            new Assignment().submitButtonInQuestionClick(); //finish assignment
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            new SelectCourse().selectInvisibleAssignment("2.1 Concept Check");
            new AttemptTest().attemptAllCorrect(1,false,false);
            boolean performanceReport=performanceSummaryReport.report.isDisplayed();
            Assert.assertEquals(performanceReport,true,"performance is not generated");

            //Tc row no 54
            //Retake option should not be available
            boolean found= new BooleanValue().presenceOfElement(54,By.xpath("//div[text()='Retake']"));
            Assert.assertEquals(found,false,"Retake option is displayed");

            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(1);
            new SelectCourse().selectInvisibleAssignment("2.1 Concept Check"); //click on the assessment name

            boolean performanceReport1=performanceSummaryReport.report.isDisplayed();
            Assert.assertEquals(performanceReport1,true,"performance is not generated");



        } catch (Exception e) {
            Assert.fail("Exception in test case  attemptedStaticPracticeAssignmentAdaptive of class AssignStaticPracticeFromStudy",e);
        }
    }




    @Test(priority = 3,enabled = true)
    public void viewInProgressStaticPracticeAssignmentLS()
    {
        try {
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            TocSearch tocSearch=PageFactory.initElements(driver,TocSearch.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            MyActivity myActivity=PageFactory.initElements(driver,MyActivity.class);
            PerformanceSummaryReport performanceSummaryReport=PageFactory.initElements(driver,PerformanceSummaryReport.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("61");//login as instructor
            new LoginUsingLTI().ltiLogin("61_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            boolean assessment_icon=tocSearch.assessment_icon.isDisplayed();
            Assert.assertEquals(assessment_icon, true, "Before the static practice was assigned , the Study is not showing the assessment with the default view");
            tocSearch.assignmentName.click();
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.al-quit-diag-test-icon")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//span[text()='A']")));
            assignments.submitAnswer.click();
            for(int i=1;i<=3;i++) {
                try {
                    assignments.nextButton.click();
                    break;
                } catch (Exception e) {

                }
            }
            driver.findElement(By.cssSelector("div.al-quit-diag-test-icon")).click();//quit test so that assessment status will be in In-Progress
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            Assert.assertEquals(tocSearch.assessment_icon.getAttribute("class"),"toc-icon completed","Assessment status not change to in Progress");

            new LoginUsingLTI().ltiLogin("61");//login as instructor
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            lessonPage.static_arrow.click(); // click on the static arrow link

            //Tc row no 12
            //On clicking Assign icon, Assign pop-up should appear
            lessonPage.assignThis_link.click(); //click on the assign this link
            boolean assign_popup1 = lessonPage.assign_popup.isDisplayed();
            Assert.assertEquals(assign_popup1, true, "On clicking Assign icon, Assign pop-up is not displayed");
            new AssignLesson().assignTOCWithDefaultClassSection(61); //Assign Toc with default class section
            new TopicOpen().chapterOpen(1);
            new LoginUsingLTI().ltiLogin("61_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            int assignmentIcon = tocSearch.studentAssessment_icon.size();
            System.out.println("assignmentIcon:"+assignmentIcon);
            Assert.assertEquals(assignmentIcon,1,"The assessment icon in not changed to assignment icon.");

            String assignmentName=tocSearch.assignmentName.getAttribute("title").trim();
            Assert.assertEquals(assignmentName,"1A.2 Concept Check","The name of the assignment should be the is not same as the assessment");
            String dueDate=tocSearch.tocDueDate.getText();
            Assert.assertTrue(dueDate.contains("01,"),"Due Date of the assignment should be is not shown : The format should be the same as for other assignments in the Study Plan.");

            //Tc row no 48
            new Navigator().NavigateTo("Assignments"); //navigate to Assignments tab
            boolean assessmentName2 = currentAssignments.getAssignmentName().isDisplayed();
            Assert.assertEquals(assessmentName2,true,"Assignment entry of static practice is not present in Assignments tab of lesson page to which static practice is associated ");

            // Tc row no 50
            new Navigator().NavigateTo("Assignments"); //navigate to Assignments
            Assert.assertEquals(assignments.status_inProgress.getText().trim(),"Not Started","Assignment entry is not present in Assignments page showing \"Not started\" status");

            currentAssignments.getList_assignmentName().get(0).click();
            Thread.sleep(2000);
            //Tc row no 52
            new Navigator().NavigateTo("My Activity");
            Thread.sleep(5000);
            myActivity.assignment_card.click();  //click on the assessment card
            new WebDriverWait(driver,300).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title ")));
            new Assignment().submitButtonInQuestionClick(); //finish assignment
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            new SelectCourse().selectInvisibleAssignment("1A.2 Concept Check");

            boolean performanceReport=performanceSummaryReport.report.isDisplayed();
            Assert.assertEquals(performanceReport,true,"performance is not generated");

            //Tc row no 54
            //Retake option should not be available
            boolean found= new BooleanValue().presenceOfElement(54,By.xpath("//div[text()='Retake']"));
            Assert.assertEquals(found,false,"Retake option is displayed");

            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(1);
            new SelectCourse().selectInvisibleAssignment("1A.2 Concept Check"); //click on the assessment name

            boolean performanceReport1=performanceSummaryReport.report.isDisplayed();
            Assert.assertEquals(performanceReport1,true,"performance is not generated");



        } catch (Exception e) {
            Assert.fail("Exception in test case  viewInProgressStaticPracticeAssignmentLS of class AssignStaticPracticeFromStudy",e);
        }
    }


    @Test(priority = 4,enabled = true)
    public void attemptedStaticPracticeAssignmentLS()
    {
        try {
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            TocSearch tocSearch=PageFactory.initElements(driver,TocSearch.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            MyActivity myActivity=PageFactory.initElements(driver,MyActivity.class);
            PerformanceSummaryReport performanceSummaryReport=PageFactory.initElements(driver,PerformanceSummaryReport.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("71");//login as instructor
            new LoginUsingLTI().ltiLogin("71_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);

            boolean assessment_icon=tocSearch.assessment_icon.isDisplayed();
            Assert.assertEquals(assessment_icon, true, "Before the static practice was assigned , the Study is not showing the assessment with the default view");
            tocSearch.assignmentName.click();
            new SelectCourse().selectInvisibleAssignment("1A.2 Concept Check");
            new SelectAnswerAndSubmit().staticanswersubmit("A");
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            Assert.assertEquals(tocSearch.assessment_icon.getAttribute("class"),"toc-icon completed","Assessment status not change to in Progress");

            new LoginUsingLTI().ltiLogin("71");//login as instructor
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            lessonPage.static_arrow.click(); // click on the static arrow link

            //Tc row no 12
            //On clicking Assign icon, Assign pop-up should appear
            lessonPage.assignThis_link.click(); //click on the assign this link
            boolean assign_popup1 = lessonPage.assign_popup.isDisplayed();
            Assert.assertEquals(assign_popup1, true, "On clicking Assign icon, Assign pop-up is not displayed");
            new AssignLesson().assignTOCWithDefaultClassSection(71); //Assign Toc with default class section
            new TopicOpen().chapterOpen(1);
            new LoginUsingLTI().ltiLogin("71_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            int assignmentIcon = tocSearch.studentAssessment_icon.size();
            System.out.println("assignmentIcon:"+assignmentIcon);
            Assert.assertEquals(assignmentIcon,1,"The assessment icon in not changed to assignment icon.");

            String assignmentName=tocSearch.assignmentName.getAttribute("title").trim();
            Assert.assertEquals(assignmentName,"1A.2 Concept Check","The name of the assignment should be the is not same as the assessment");
            String dueDate=tocSearch.tocDueDate.getText();
            Assert.assertTrue(dueDate.contains("01,"),"Due Date of the assignment should be is not shown : The format should be the same as for other assignments in the Study Plan.");

            //Tc row no 48
            new Navigator().NavigateTo("Assignments"); //navigate to Assignments tab
            boolean assessmentName2 = currentAssignments.getAssignmentName().isDisplayed();
            Assert.assertEquals(assessmentName2,true,"Assignment entry of static practice is not present in Assignments tab of lesson page to which static practice is associated ");

            // Tc row no 50
            new Navigator().NavigateTo("Assignments"); //navigate to Assignments
            Assert.assertEquals(assignments.status_inProgress.getText().trim(),"Not Started","Assignment entry is not present in Assignments page showing \"Not started\" status");

            currentAssignments.getList_assignmentName().get(0).click();
            Thread.sleep(2000);
            //Tc row no 52
            new Navigator().NavigateTo("My Activity");
            Thread.sleep(5000);
            myActivity.assignment_card.click();  //click on the assessment card
            new WebDriverWait(driver,300).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title ")));
            new Assignment().submitButtonInQuestionClick(); //finish assignment
            Thread.sleep(2000);

            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            new SelectCourse().selectInvisibleAssignment("1A.2 Concept Check");
            new AttemptTest().attemptAllCorrect(1,false,false);
            boolean performanceReport=performanceSummaryReport.report.isDisplayed();
            Assert.assertEquals(performanceReport,true,"performance is not generated");

            //Tc row no 54
            //Retake option should not be available
            boolean found= new BooleanValue().presenceOfElement(54,By.xpath("//div[text()='Retake']"));
            Assert.assertEquals(found,false,"Retake option is displayed");

            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(1);
            new SelectCourse().selectInvisibleAssignment("1A.2 Concept Check"); //click on the assessment name

            boolean performanceReport1=performanceSummaryReport.report.isDisplayed();
            Assert.assertEquals(performanceReport1,true,"performance is not generated");



        } catch (Exception e) {
            Assert.fail("Exception in test case  attemptedStaticPracticeAssignmentLS of class AssignStaticPracticeFromStudy",e);
        }
    }


    @Test(priority = 5,enabled = true)
    public void attemptedStaticPracticeAfterDiagnostic()
    {
        try {
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            DiagnosticTest diagnosticTest=PageFactory.initElements(driver,DiagnosticTest.class);
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            TocSearch tocSearch=PageFactory.initElements(driver,TocSearch.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            MyActivity myActivity=PageFactory.initElements(driver,MyActivity.class);
            PerformanceSummaryReport performanceSummaryReport=PageFactory.initElements(driver,PerformanceSummaryReport.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            //login with three student and attempt diagnostic test
            new LoginUsingLTI().ltiLogin("390");//login as instructor
            new LoginUsingLTI().ltiLogin("3901");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            diagnosticTest.startTest(2);
            diagnosticTest.attemptAllCorrect(2,false,false);
            new LoginUsingLTI().ltiLogin("3902");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            diagnosticTest.startTest(2);
            diagnosticTest.attemptAllCorrect(2,false,false);
            new LoginUsingLTI().ltiLogin("390");//login as instructor
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            lessonPage.orionAdaptivePracticeTab.click();
            lessonPage.practice_Arrow.click(); // click on the static arrow link

            //Tc row no 12
            //On clicking Assign icon, Assign pop-up should appear
            lessonPage.assignThis_link.click();
            new AssignLesson().assignTOCFromOrionAdaptivePractice(3901); //Assign Toc with default class section
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getViewGrade_link().click();
            //validate percentage completion and total score should be 0
            //First student
            Assert.assertTrue(currentAssignments.totalScore.get(0).getText().equals("0.0"),"Total score should Zero");
            Assert.assertTrue(currentAssignments.totalQuestionAttempted.get(0).getText().equals("0"),"totalQuestionAttempted  should Zero");
            Assert.assertTrue(currentAssignments.totalCorrectAnswer.get(0).getText().equals("0"),"totalCorrectAnswer   should Zero");
            Assert.assertTrue(currentAssignments.totalPartiallyCorrectAnswer.get(0).getText().equals("0"),"totalPartiallyCorrectAnswer  should Zero");
            Assert.assertTrue(currentAssignments.totalIncorrectAnswer.get(0).getText().equals("0"),"totalIncorrectAnswer  should Zero");
            Assert.assertTrue(currentAssignments.totalSkippedAnswer.get(0).getText().equals("0"),"totalSkippedAnswer  should Zero");
            //Second student
            Assert.assertTrue(currentAssignments.totalScore.get(1).getText().equals("0.0"),"Total score should Zero");
            Assert.assertTrue(currentAssignments.totalQuestionAttempted.get(1).getText().equals("0"),"totalQuestionAttempted  should Zero");
            Assert.assertTrue(currentAssignments.totalCorrectAnswer.get(1).getText().equals("0"),"totalCorrectAnswer   should Zero");
            Assert.assertTrue(currentAssignments.totalPartiallyCorrectAnswer.get(1).getText().equals("0"),"totalPartiallyCorrectAnswer  should Zero");
            Assert.assertTrue(currentAssignments.totalIncorrectAnswer.get(1).getText().equals("0"),"totalIncorrectAnswer  should Zero");
            Assert.assertTrue(currentAssignments.totalSkippedAnswer.get(1).getText().equals("0"),"totalSkippedAnswer  should Zero should Zero");
            //login with student 1 and attempt assignment
            new LoginUsingLTI().ltiLogin("3901");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab

            lessonPage.getForAssignmentName().click();//click on practice assignment
            for (int i=0;i<5;i++)
                new PracticeTest().attemptPracticeTestAfterAssign(1);
            lessonPage.getList_notificationMessage().get(1).click();//Click on I'm Finished
       new LoginUsingLTI().ltiLogin("3902");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab

            lessonPage.getForAssignmentName().click();//click on practice assignment
            for (int i=0;i<5;i++)
                new PracticeTest().attemptPracticeTestAfterAssign(1);
            lessonPage.getList_notificationMessage().get(2).click();//Click on Keep the assignment open till due date.*//**//*
           for (int i=0;i<3;i++)
                new PracticeTest().attemptPracticeTestAfterAssign(1);
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button adaptive-assignment-finish-button']")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button adaptive-assignment-finish-button']")));
            lessonPage.getList_notificationMessage().get(1).click();//Click on I'm Finished*//*
            new LoginUsingLTI().ltiLogin("390");//login as instructor
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getViewGrade_link().click();
            Thread.sleep(4000);
            try {
                new ScrollElement().scrollWindowDown();
            } catch (Exception e) {
                new ScrollElement().scrollWindowDown();

            }
            Assert.assertTrue(currentAssignments.totalQuestionAttempted.get(0).getText().equals("5"),"totalQuestionAttempted  should Zero");
            new MouseHover().mouserhoverbywebelement(driver.findElements(By.cssSelector("div[class='idb-gradebook-questions-attempted-status-entries ']")).get(0));
            WebElement we1 = assignmentResponsesPage.getViewResponseLink();
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", we1);//click on View Response
            assignmentResponsesPage.getScore().clear();
            assignmentResponsesPage.getScore().sendKeys("1.0");
            assignmentResponsesPage.getSaveButton().click();
            currentAssignments.getNextArrow().click();

            new LoginUsingLTI().ltiLogin("390");//login as instructor
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getViewGrade_link().click();
            ((JavascriptExecutor) driver).executeScript("scroll(0, 250);");
            Assert.assertTrue(currentAssignments.totalQuestionAttempted.get(1).getText().equals("9"),"totalQuestionAttempted  should Zero");
            new MouseHover().mouserhoverbywebelement(driver.findElements(By.cssSelector("div[class='idb-gradebook-questions-attempted-status-entries ']")).get(0));

             we1 = assignmentResponsesPage.getViewResponseLink();
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", we1);//click on View Response
            //Second student
            assignmentResponsesPage.getScore().clear();
            assignmentResponsesPage.getScore().sendKeys("1.0");
            assignmentResponsesPage.getSaveButton().click();
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getViewGrade_link().click();

        } catch (Exception e) {
            Assert.fail("Exception in test case  attemptedStaticPracticeAfterDiadnostic of class AssignStaticPracticeFromStudy",e);
        }
    }

    @Test(priority = 6,enabled = true)
    public void attemptedStaticPracticeAfterDiagnosticNonGraded()
    {
        try {
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            DiagnosticTest diagnosticTest=PageFactory.initElements(driver,DiagnosticTest.class);
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            TocSearch tocSearch=PageFactory.initElements(driver,TocSearch.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            MyActivity myActivity=PageFactory.initElements(driver,MyActivity.class);
            PerformanceSummaryReport performanceSummaryReport=PageFactory.initElements(driver,PerformanceSummaryReport.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            //login with three student and attempt diagnostic test
            new LoginUsingLTI().ltiLogin("423");//login as instructor
            new LoginUsingLTI().ltiLogin("4231");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            diagnosticTest.startTest(2);
            diagnosticTest.attemptAllCorrect(2, false, false);
            new LoginUsingLTI().ltiLogin("4232");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            diagnosticTest.startTest(2);
            diagnosticTest.attemptAllCorrect(2,false,false);
            new LoginUsingLTI().ltiLogin("423");//login as instructor
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            lessonPage.orionAdaptivePracticeTab.click();
            lessonPage.practice_Arrow.click(); // click on the static arrow link

            //Tc row no 12
            //On clicking Assign icon, Assign pop-up should appear
            lessonPage.assignThis_link.click();
            new AssignLesson().assignTOCFromOrionAdaptivePractice(4231); //Assign Toc with default class section
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getViewGrade_link().click();
            //validate percentage completion and total score should be 0
            //First student
            Assert.assertTrue(currentAssignments.totalScore.get(0).getText().equals("0.0"),"Total score should Zero");
            Assert.assertTrue(currentAssignments.totalQuestionAttempted.get(0).getText().equals("0"),"totalQuestionAttempted  should Zero");
            Assert.assertTrue(currentAssignments.totalCorrectAnswer.get(0).getText().equals("0"),"totalCorrectAnswer   should Zero");
            Assert.assertTrue(currentAssignments.totalPartiallyCorrectAnswer.get(0).getText().equals("0"),"totalPartiallyCorrectAnswer  should Zero");
            Assert.assertTrue(currentAssignments.totalIncorrectAnswer.get(0).getText().equals("0"),"totalIncorrectAnswer  should Zero");
            Assert.assertTrue(currentAssignments.totalSkippedAnswer.get(0).getText().equals("0"),"totalSkippedAnswer  should Zero");
            //Second student
            Assert.assertTrue(currentAssignments.totalScore.get(1).getText().equals("0.0"),"Total score should Zero");
            Assert.assertTrue(currentAssignments.totalQuestionAttempted.get(1).getText().equals("0"),"totalQuestionAttempted  should Zero");
            Assert.assertTrue(currentAssignments.totalCorrectAnswer.get(1).getText().equals("0"),"totalCorrectAnswer   should Zero");
            Assert.assertTrue(currentAssignments.totalPartiallyCorrectAnswer.get(1).getText().equals("0"),"totalPartiallyCorrectAnswer  should Zero");
            Assert.assertTrue(currentAssignments.totalIncorrectAnswer.get(1).getText().equals("0"),"totalIncorrectAnswer  should Zero");
            Assert.assertTrue(currentAssignments.totalSkippedAnswer.get(1).getText().equals("0"),"totalSkippedAnswer  should Zero should Zero");
            //login with student 1 and attempt assignment
            new LoginUsingLTI().ltiLogin("4231");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab

            lessonPage.getForAssignmentName().click();//click on practice assignment
            for (int i=0;i<5;i++)
                new PracticeTest().attemptPracticeTestAfterAssign(1);
            lessonPage.getList_notificationMessage().get(1).click();//Click on I'm Finished

            new LoginUsingLTI().ltiLogin("4232");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab

            lessonPage.getForAssignmentName().click();//click on practice assignment
            for (int i=0;i<5;i++)
                new PracticeTest().attemptPracticeTestAfterAssign(1);
            lessonPage.getList_notificationMessage().get(2).click();//Click on Keep the assignment open till due date.
            for (int i=0;i<3;i++)
                new PracticeTest().attemptPracticeTestAfterAssign(1);
                Thread.sleep(5000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button adaptive-assignment-finish-button']")));
            lessonPage.getList_notificationMessage().get(1).click();//Click on I'm Finished



            new LoginUsingLTI().ltiLogin("423");//login as instructor
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getViewGrade_link().click();
            Thread.sleep(4000);
            try {
                new ScrollElement().scrollWindowDown();
            } catch (Exception e) {
                new ScrollElement().scrollWindowDown();

            }
            Assert.assertTrue(currentAssignments.totalQuestionAttempted.get(0).getText().equals("5"),"totalQuestionAttempted  should Zero");
            new MouseHover().mouserhoverbywebelement(driver.findElements(By.cssSelector("div[class='idb-gradebook-questions-attempted-status-entries ']")).get(0));
            WebElement we1 = assignmentResponsesPage.getViewResponseLink();
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", we1);//click on View Response

            //Second student
            for(int i=0;i<4;i++)
            {
                Thread.sleep(2000);
                assignmentResponsesPage.getScore().clear();
                assignmentResponsesPage.getScore().sendKeys("1.0");
                assignmentResponsesPage.getSaveButton().click();
                currentAssignments.getNextArrow().click();
            }

            assignmentResponsesPage.getScore().clear();
            assignmentResponsesPage.getScore().sendKeys("1.0");
            assignmentResponsesPage.getSaveButton().click();
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getViewGrade_link().click();
            Thread.sleep(4000);
            try {
                new ScrollElement().scrollWindowDown();
            } catch (Exception e) {
                new ScrollElement().scrollWindowDown();

            }            Assert.assertTrue(currentAssignments.totalQuestionAttempted.get(1).getText().equals("9"),"totalQuestionAttempted  should Zero");
            new MouseHover().mouserhoverbywebelement(driver.findElements(By.cssSelector("div[class='idb-gradebook-questions-attempted-status-entries ']")).get(0));
            we1 = assignmentResponsesPage.getViewResponseLink();
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", we1);//click on View Response
            //Second student
            assignmentResponsesPage.getScore().clear();
            assignmentResponsesPage.getScore().sendKeys("1.0");
            assignmentResponsesPage.getSaveButton().click();
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getViewGrade_link().click();

        } catch (Exception e) {
            Assert.fail("Exception in test case  attemptedStaticPracticeAfterDiadnostic of class AssignStaticPracticeFromStudy",e);
        }
    }


    @Test(priority = 7,enabled = true)
    public void accessingAdaptivePracticePostSubmissionPracticeAssignment()
    {
        try {
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
            DiagnosticTest diagnosticTest=PageFactory.initElements(driver,DiagnosticTest.class);
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            TocSearch tocSearch=PageFactory.initElements(driver,TocSearch.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            MyActivity myActivity=PageFactory.initElements(driver,MyActivity.class);
            PerformanceSummaryReport performanceSummaryReport=PageFactory.initElements(driver,PerformanceSummaryReport.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            //login with three student and attempt diagnostic test
            new LoginUsingLTI().ltiLogin("472");//login as instructor
            new LoginUsingLTI().ltiLogin("4721");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            diagnosticTest.startTest(2);
            diagnosticTest.attemptAllCorrect(2,false,false);
            new Navigator().NavigateTo("e-Textbook"); //navigate to eTextbook
            new TopicOpen().chapterOpen(1);
            new PracticeTest().startTest(); //start practice test
            for(int i = 0; i < 13; i++)
            {
                new PracticeTest().AttemptCorrectAnswer(0,"713");
            }
            new PracticeTest().quitThePracticeTest();
            new LoginUsingLTI().ltiLogin("472");//login as instructor
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            lessonPage.orionAdaptivePracticeTab.click();
            lessonPage.practice_Arrow.click(); // click on the static arrow link

            //Tc row no 12
            //On clicking Assign icon, Assign pop-up should appear
            lessonPage.assignThis_link.click();
            new AssignLesson().assignTOCFromOrionAdaptivePractice(4721); //Assign Toc with default class section
            new LoginUsingLTI().ltiLogin("4721");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab
            lessonPage.getForAssignmentName().click();//click on practice assignment
            for (int i=0;i<5;i++)
                new PracticeTest().attemptPracticeTestAfterAssign(1);
            lessonPage.getList_notificationMessage().get(1).click();//Click on I am finished

            new LoginUsingLTI().ltiLogin("472");//login as instructor
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getViewGrade_link().click();
            Assert.assertTrue(currentAssignments.totalQuestionAttempted.get(0).getText().equals("5"),"totalQuestionAttempted  should Zero");
            new MouseHover().mouserhoverbywebelement(driver.findElements(By.cssSelector("div[class='idb-gradebook-questions-attempted-status-entries ']")).get(0));
            WebElement we1 = assignmentResponsesPage.getViewResponseLink();
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", we1);//click on View Response
        } catch (Exception e) {
            Assert.fail("Exception in test case  accessingAdaptivePracticePostSubmissionPracticeAssignment of class AssignStaticPracticeFromStudy",e);
        }
    }



}