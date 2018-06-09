package com.snapwiz.selenium.tests.staf.learningspaces.testcases.sanityPack.ls;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.AssignmentTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.PerformanceSummary;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by priyanka on 20-11-2015.
 */
public class  AssignmentFlowThroughToc extends Driver {

    @Test(priority=1,enabled = true)
    public void staticAssessmentNonGradeAble()
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            //TC row no 2
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(2));
            PerformanceSummary performanceSummary = PageFactory.initElements(driver, PerformanceSummary.class);

            new Assignment().create(2);
            new Assignment().addQuestions(2, "all", "");
            new Assignment().addQuestions(2,"multipart","");

            new LoginUsingLTI().ltiLogin("2");//log in as instructor
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().clickOnStaticAssessmentArrow(assessmentname);
            lessonPage.assignThis_link.click(); //click on the assign this link
            new AssignLesson().assignTOCWithDefaultClassSection(2);//Assign to class

            new LoginUsingLTI().ltiLogin("2_1");//log in as student
            new Assignment().submitAssignmentAsStudent(2);
            Assert.assertEquals(performanceSummary.titlePerformanceSummary.getText().trim(),"Performance Summary","User is not landing  to Performance summary page.");

        }
            catch(Exception e)
            {
                Assert.fail("Exception in testcase staticAssessmentNonGradeAble of class AssignmentFlowThroughToc" + e);
            }
        }

    @Test(priority=2,enabled = true)
    public void staticAssessmentGradeAblePolicyOne()
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            //TC row no 4
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(4));
            PerformanceSummary performanceSummary = PageFactory.initElements(driver, PerformanceSummary.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "4");
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);


            System.out.println("assessmentname:"+assessmentname);
            new LoginUsingLTI().ltiLogin("4");//log in as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");//policy 1

            new Navigator().NavigateTo("eTextBook");//navigate to e-po
            new TopicOpen().clickOnStaticAssessmentArrow(assessmentname);
            lessonPage.assignThis_link.click(); //click on the assign this link
            new AssignLesson().assignTOCWithDefaultClassSection(4);//Assign to class

            new LoginUsingLTI().ltiLogin("4_1");//log in as student
            new Assignment().submitAssignmentAsStudent(4);
            Assert.assertEquals(performanceSummary.titlePerformanceSummary.getText().trim(),"Performance Summary","User is not landing  to Performance summary page.");

            new LoginUsingLTI().ltiLogin("4");//login as instructor
            new Navigator().NavigateTo("Current Assignments");
            Assert.assertEquals(currentAssignments.gradedStatus.getText(),"Graded","grade is not released after student submitted the assignment");
            currentAssignments.getViewGrade_link().click();//click on view grades link
            boolean elementFound = false;
            try{
                driver.findElement(By.cssSelector("div[title='Release Grade for All']"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,false,"grade is not released after student submitted the assignment");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase staticAssessmentNonGradeAble of class AssignmentFlowThroughToc" + e);
        }
    }

    @Test(priority=3,enabled = true)
    public void staticAssessmentGradeAblePolicyThree()
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            //TC row no 8
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(8));
            PerformanceSummary performanceSummary = PageFactory.initElements(driver, PerformanceSummary.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "8");
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);


            new LoginUsingLTI().ltiLogin("8");//log in as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "");

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().clickOnStaticAssessmentArrow(assessmentname);
            lessonPage.assignThis_link.click(); //click on the assign this link
            new AssignLesson().assignTOCWithDefaultClassSection(8);//Assign to class

            new LoginUsingLTI().ltiLogin("8_1");//log in as student
            new Assignment().submitAssignmentAsStudent(8);
            Assert.assertEquals(performanceSummary.titlePerformanceSummary.getText().trim(),"Performance Summary","User is not landing  to Performance summary page.");

            new LoginUsingLTI().ltiLogin("8");//login as instructor
            new Navigator().NavigateTo("Current Assignments");
            new Assignment().releaseGrades(8, "Release Grade for All");
            Thread.sleep(1000);
            Assert.assertEquals(assignmentResponsesPage.gradeBook_Box.getAttribute("title"), "Grade Released", "grade is not released after instructor released the grade");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase staticAssessmentNonGradeAble of class AssignmentFlowThroughToc" + e);
        }
    }

    @Test(priority=4,enabled = true)
    public void staticAssessmentGradeAblePolicyFour()
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            //TC row no 10
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(10));
            PerformanceSummary performanceSummary = PageFactory.initElements(driver, PerformanceSummary.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "10");
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);

            System.out.println(assessmentname);
            new LoginUsingLTI().ltiLogin("10");//log in as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description21", "2", null, false, "1", "", "Release explicitly by the instructor", "", "", "");//policy 4

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().clickOnStaticAssessmentArrow(assessmentname);
            lessonPage.assignThis_link.click(); //click on the assign this link
            new AssignLesson().assignTOCWithDefaultClassSection(10);//Assign to class

            new LoginUsingLTI().ltiLogin("10_1");//log in as student
            new Assignment().submitAssignmentAsStudent(10);
            Assert.assertEquals(performanceSummary.titlePerformanceSummary.getText().trim(),"Performance Summary","User is not landing  to Performance summary page.");

            new LoginUsingLTI().ltiLogin("10");//login as instructor
            new Navigator().NavigateTo("Current Assignments");
            new Assignment().releaseGrades(10, "Release Grade for All");
            Thread.sleep(1000);
            Assert.assertEquals(assignmentResponsesPage.gradeBook_Box.getAttribute("title"), "Grade Released", "grade is not released after instructor released the grade");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase staticAssessmentNonGradeAble of class AssignmentFlowThroughToc" + e);
        }
    }
    @Test(priority=5,enabled = true)
    public void adaptivePracticeAsAssignmentGradable()
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            //TC row no 12
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            PerformanceSummary performanceSummary = PageFactory.initElements(driver, PerformanceSummary.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new LoginUsingLTI().ltiLogin("12");//login as student
            new Navigator().NavigateTo("e-Textbook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            new DiagnosticTest().startTest(1); //start Diagnostic test
            new DiagnosticTest().attemptAllCorrect(2, false, false); //attempt all correct question

            new LoginUsingLTI().ltiLogin("12_1");//login as an instructor
            WebElement html = driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab
            lessonPage.practice_Arrow.click();//Click on practice arrow
            lessonPage.assignThis_link.click();//Click on assign this link
            new AssignLesson().assignTOCFromOrionAdaptivePractice(12);//Assign to class

            new LoginUsingLTI().ltiLogin("12");//login as student
            new Navigator().NavigateTo("Assignments"); //navigate to eTextbook
            driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();
            for(int i = 0; i < 19; i++) {
                Thread.sleep(2000);
                driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).click();
            }
            driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button adaptive-assignment-finish-button']")).click();
            Thread.sleep(1000);
            driver.findElement(By.cssSelector("div[class='adaptive-assignment-notification-option option-i-am-finished']")).click();
            Assert.assertEquals(performanceSummary.titlePerformanceSummary.getText(),"Performance Summary  ","User is not landing  to Performance summary page.");

            new LoginUsingLTI().ltiLogin("12_1");//login as instructor
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getViewGrade_link().click();//click on view grades link
            boolean elementFound = false;
            try{
                driver.findElement(By.cssSelector("div[title='Release Grade for All']"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,false,"grade is not released after student submitted the assignment");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase staticAssessmentNonGradeAble of class AssignmentFlowThroughToc" + e);
        }
    }

    @Test(priority=6,enabled = true)
    public void adaptivePracticeAsAssignmentNonGradable()
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            //TC row no 17
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            PerformanceSummary performanceSummary = PageFactory.initElements(driver, PerformanceSummary.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new LoginUsingLTI().ltiLogin("17");//login as student
            new Navigator().NavigateTo("e-Textbook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            new DiagnosticTest().startTest(1); //start Diagnostic test
            new DiagnosticTest().attemptAllCorrect(2, false, false); //attempt all correct question

            new LoginUsingLTI().ltiLogin("17_1");//login as an instructor
            WebElement html = driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab
            lessonPage.practice_Arrow.click();//Click on practice arrow
            lessonPage.assignThis_link.click();//Click on assign this link
            new AssignLesson().assignTOCFromOrionAdaptivePractice(17);//Assign to class

            new LoginUsingLTI().ltiLogin("17");//login as student
            new Navigator().NavigateTo("Assignments"); //navigate to eTextbook
            driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();
            for(int i = 0; i < 19; i++) {
                Thread.sleep(2000);
                driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).click();
            }
            driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button adaptive-assignment-finish-button']")).click();
            Thread.sleep(1000);
            driver.findElement(By.cssSelector("div[class='adaptive-assignment-notification-option option-i-am-finished']")).click();
            Assert.assertEquals(performanceSummary.titlePerformanceSummary.getText(),"Performance Summary  ","User is not landing  to Performance summary page.");

            new LoginUsingLTI().ltiLogin("17_1");//login as instructor
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getViewGrade_link().click();//click on view grades link
            boolean elementFound = false;
            try{
                driver.findElement(By.cssSelector("div[title='Release Grade for All']"));
                elementFound = true;
            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound,false,"grade is not released after student submitted the assignment");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase staticAssessmentNonGradeAble of class AssignmentFlowThroughToc" + e);
        }
    }

    @Test(priority=7,enabled = true)
    public void learningActivityDiscussionWidget()
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            //TC row no 20
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "20");

            new LoginUsingLTI().ltiLogin("20");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");//policy 1

            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(20);

            new LoginUsingLTI().ltiLogin("20_1");//login as student
            new Navigator().NavigateTo("Assignments");    //navigate to Assignment
            new Click().clickByclassname("learning-activity-title"); //click on DW
            String perspective = new RandomString().randomstring(2);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspective); //add perspective to DW assignment


        }
        catch(Exception e)
        {
            Assert.fail("Exception in TC learningActivityDiscussionWidget of class AssignmentFlowThroughToc" + e);
        }
    }

    @Test(priority=8,enabled = true)
    public void learningActivityDiscussionWidgetNonGradable()
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            //TC row no 24
            new LoginUsingLTI().ltiLogin("24");//login as instructor
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(24);

            new LoginUsingLTI().ltiLogin("24_1");//login as student
            new Navigator().NavigateTo("Assignments");    //navigate to Assignment
            new Click().clickByclassname("learning-activity-title"); //click on DW
            String perspective = new RandomString().randomstring(2);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspective); //add perspective to DW assignment


        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase learningActivityDiscussionWidgetNonGradable of class AssignmentFlowThroughToc" + e);
        }
    }

    @Test(priority=9,enabled = true)
    public void fileBasedAssignment()
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            //TC row no 27
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);

            new Assignment().createFileBasedAssessment(27);
            new LoginUsingLTI().ltiLogin("27");//login as instructor
            new Assignment().assignFileBasedAssignmentToStudent(27);
            new LoginUsingLTI().ltiLogin("27_1");//login as student
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssessmentName().click();//click on assignment
            Thread.sleep(10000);
            assignmentTab.finishButton.click();//click on finish assignment
            assignmentTab.continueButton.click();//click on continue

            String str=driver.findElement(By.className("ls-assignment-status-submitted")).getText();
            Assert.assertEquals(str,"Submitted","status is not submitted");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in testcase learningActivityDiscussionWIdget of class AssignmentFlowThroughToc" + e);
        }
    }


    @Test(priority=10,enabled = true)
    public void autoSubmitFunctionalityNonGradAble()
    {
        WebDriver driver=Driver.getWebDriver();
        try {
            //TC row no 30
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String assignmentName = ReadTestData.readDataByTagName("", "assessmentname", "30");

            new Assignment().create(30);
            new LoginUsingLTI().ltiLogin("30");//login as instructor
            new Assignment().assignAssignmentWithDueDate(30);

            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            Assert.assertEquals(currentAssignments.getAssessmentName().getText().trim(),assignmentName);
            new Assignment().verifyClassAssignmentStatus(30,"Available for Students");


            new LoginUsingLTI().ltiLogin("30_1");	//login as a student1
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().statusBoxCheckInStudentAssignmentPage(30,"1","Not Started");
            new Assignment().verifyClassAssignmentStatus(30, "Not Started");

            Thread.sleep(300000); //wait for 4 minute till due date is expired

            new LoginUsingLTI().ltiLogin("30_1");	//login as a student1
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().verifyClassAssignmentStatus(30, "Submitted");

            new LoginUsingLTI().ltiLogin("30");//login as instructor
            new Assignment().releaseGrades(30,"Release Feedback for All");
            new Navigator().NavigateTo("Current Assignments");  //navigate to Current Assignments
            new Assignment().verifyClassAssignmentStatus(30, "Reviewed");


        }
        catch(Exception e)
        {
            Assert.fail("Exception in TC autoSubmitFunctionalityNonGradAble of class AssignmentFlowThroughToc" + e);
        }
    }





}
