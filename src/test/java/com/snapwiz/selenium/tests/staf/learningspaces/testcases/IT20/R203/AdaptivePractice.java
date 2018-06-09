package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT20.R203;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.MyActivity.MyActivity;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.About;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.AssignmentTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.PerformanceSummaryReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.ProficiencyReport;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by pragya on 20-05-2015.
 */
public class AdaptivePractice extends Driver {

    @Test(priority = 1,enabled = true)
    public void adaptivePracticeInAssignment(){
        try{
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            Questions questions = PageFactory.initElements(driver, Questions.class);

            new LoginUsingLTI().ltiLogin("36");//login as an instructor
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab
            lessonPage.practice_Arrow.click();//Click on practice arrow
            lessonPage.assignThis_link.click();//Click on assign this link
            new AssignLesson().assignTOCFromOrionAdaptivePractice(36);//Assign to class

            new LoginUsingLTI().ltiLogin("36_1");//login as a student
            new Navigator().NavigateTo("Assignments");//navigate to Assignment page

            //Expected - Assignment entry should be available
            if(!assignments.getAssignmentName().getText().contains("Ch 2: The Chemical Foundation of Life")){
                Assert.fail("Assignment entry is not available");
            }

            assignments.getAssignmentName().click();//Click on assignment

            //Expected - Pop up should have elements as mentioned below
            // Header : Diagnostic Test,Close icon
            //Text: The chapter-level adaptive practice has been assigned by your Instructor/ Mentor/Mentor. Work on the diagnostic first before starting the assignment.
            // Cancel link and Begin Diagnostic button
            Assert.assertEquals(assignments.popUp_diagnosticTestHeader.getText(),"ORION Assignment","'ORION Assignment' text is not displayed in header");
            System.out.println(assignments.popUp_diagnosticTestBody.getText());
            Assert.assertEquals(assignments.popUp_diagnosticTestBody.getText(), "Attention! Before you can start your assignment, you need to finish a quick set of diagnostic questions.\nOnce you finish your diagnostic, you will see a report with a link to continue to your assignment.", "Body text is not displayed as expected");
            Assert.assertEquals(assignments.popUp_diagnosticTestClose.isDisplayed(),true,"Close icon is not displayed");
            Assert.assertEquals(assignments.popUp_diagnosticTestCancel.getText(),"Cancel","Cancel button is not displayed");
            Assert.assertEquals(assignments.popUp_diagnosticTest_beginDiagnostic.getText(),"Begin Diagnostic","'Begin Diagnostic' button is not displayed");

            assignments.popUp_diagnosticTestCancel.click();//Click on cancel button

            boolean popUpDisplayedAfterCancel  = new BooleanValue().presenceOfElement(36,By.className("diagnostic-test-attempt-notification-title"));
            //Expected - Click on “Cancel” should close the popup without any action.
            Assert.assertEquals(popUpDisplayedAfterCancel,false,"Pop up is displayed after clicking on cancel");

            assignments.getAssignmentName().click();//Click on assignment
            assignments.popUp_diagnosticTestClose.click();//Click on close button

            boolean popUpDisplayedAfterClose  = new BooleanValue().presenceOfElement(36,By.className("diagnostic-test-attempt-notification-title"));
            //Expected - Pop up should get closed
            Assert.assertEquals(popUpDisplayedAfterClose,false,"Pop up is displayed after clicking on cancel");
            assignments.getAssignmentName().click();//Click on assignment

            assignments.popUp_diagnosticTest_beginDiagnostic.click();//Click on begin Diagnostic

            //Expected - Cancel  and begin button should get replaced by:
            //Enter confidence level
            // Confidence level icons
            //Arrow icon
            Assert.assertEquals(assignments.popUp_diagnosticTest_enterConfidenceLevel.getText(),"Enter Confidence Level","Enter Confidence level is not  displayed");
            Assert.assertEquals(assignments.popUp_diagnosticTest_confidenceLevelIcons.get(0).getAttribute("confidence-level"),"I don't know","Confidence level is not displayed as expected");
            Assert.assertEquals(assignments.popUp_diagnosticTest_confidenceLevelIcons.get(1).getAttribute("confidence-level"),"Somewhat confident","Confidence level is not displayed as expected");
            Assert.assertEquals(assignments.popUp_diagnosticTest_confidenceLevelIcons.get(2).getAttribute("confidence-level"),"Almost confident","Confidence level is not displayed as expected");
            Assert.assertEquals(assignments.popUp_diagnosticTest_confidenceLevelIcons.get(3).getAttribute("confidence-level"),"I know it","Confidence level is not displayed as expected");
            Assert.assertEquals(assignments.popUp_diagnosticTest_arrow.getAttribute("type"),"submit","Arrow is not displayed");

            assignments.popUp_diagnosticTest_arrow.click();//Click on arrow
            Thread.sleep(1000);

            //Expected - Selecting confidence factor should be mandatory
            Assert.assertEquals(assignments.popUp_error.getText(),"Error","Error message is not appearing on clicking on arrow without selecting confidence level");

            assignments.popUp_diagnosticTest_confidenceLevelIcons.get(1).click();//Click on confidence level icon

            assignments.popUp_diagnosticTest_arrow.click();//Click on arrow

            Thread.sleep(60000);
            //Expected - First diagnostic questions should get rendered
            Assert.assertEquals(questions.questionLabel.getText(),"Q 2.1:","first question label is not displayed");


        }catch (Exception e){
            Assert.fail("Exception in test case  'adaptivePracticeInAssignment' of class 'AdaptivePractice'", e);

        }
    }

    @Test(priority = 2, enabled = true)
    public void adaptivePracticeInStudyPlan(){
        try{
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            Questions questions = PageFactory.initElements(driver, Questions.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);

            new LoginUsingLTI().ltiLogin("124");//login as an instructor
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab
            lessonPage.practice_Arrow.click();//Click on practice arrow
            lessonPage.assignThis_link.click();//Click on assign this link
            new AssignLesson().assignTOCFromOrionAdaptivePractice(124);//Assign to class

            new LoginUsingLTI().ltiLogin("124_1");//login as a student
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab

            //Expected - Hybrid diagnostic card should be displayed with begin diagnostic button and practice assignment end date
            Assert.assertEquals(lessonPage.beginDiagnostic.get(1).getAttribute("type"),"submit","Begin diagnostic button is not displayed");
            System.out.println("lessonPage.assignment_dueDate.getText()::"+lessonPage.assignment_dueDate.getText());
            if(!lessonPage.assignment_dueDate.getText().contains("26, 201")){
                Assert.fail("Due date is not displayed as expected");
            }

            if(!lessonPage.dueDate.getText().contains("(Due Date:")){
                Assert.fail("Due date text is not displayed");
            }

            Thread.sleep(1000);
            boolean secAdpatice = driver.findElement(By.xpath("//div[@class='tlo-ellipsis-content-wrapper']/a")).isDisplayed();
            System.out.println("secAdpatice::"+secAdpatice);
            boolean adaptiveSectionLevel = driver.findElement(By.xpath("//div[@class='tlo-ellipsis-content-wrapper']/a")).isDisplayed();
            //Expected - All the section level adaptive practice should be disabled
            Assert.assertEquals(adaptiveSectionLevel,false,"Section level adaptive practice test is displayed");

            selectConfidence(1);
            lessonPage.beginDiagnostic.get(1).click();//Click on Begin Diagnostic button

            //Expected - Robo-notification should be displayed with below mentioned text:
            //”The chapter-level adaptive practice has been assigned by your Instructor/ Mentor/Mentor. Work on the diagnostic first before starting the assignment. Continue >>"
            if(!lessonPage.roboNotificationMessage.getText().contains("Before you can start your assignment, you need to finish a quick set of diagnostic questions. Once you finish your diagnostic, you will see a report with a link to continue to your assignment.")){
                Assert.fail("Robo notification is not displayed as expected");
            }

            if(!lessonPage.roboNotificationMessage.getText().contains("Begin Diagnostic>>")){
                Assert.fail("Robo notification is not displayed as expected");
            }

            lessonPage.closeIconOfRoboNotificationMessage.click();//close robo notification

            boolean roboNotificationDisplayedAfterClose = new BooleanValue().presenceOfElement(124,By.cssSelector("div.al-notification-message-body"));
            //Expected - The robo-notification should get closed on clicking x icon
            Assert.assertEquals(roboNotificationDisplayedAfterClose,false,"Robo notification is still displayed after clicking on close button");

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab
            lessonPage.beginDiagnostic.get(1).click();//Click on Begin Diagnostic button

            //Expected - Selecting confidence factor should be mandatory
            Assert.assertEquals(assignments.popUp_error.getText(),"Error","Error message is not appearing on clicking on arrow without selecting confidence level");
            selectConfidence(1);
            lessonPage.beginDiagnostic.get(1).click();//Click on Begin Diagnostic button
            lessonPage.continue_roboNotification.click();//Click on continue

            Thread.sleep(6000);
            //Expected - First diagnostic questions should get rendered
            Assert.assertEquals(questions.questionLabel.getText(),"Q 2.1:","first question label is not displayed");

            //assignments.popUp_diagnosticTest_confidenceLevelIcons.get(1).click();//Click on confidence level icon
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab
            lessonPage.button_continueDiagnostic.click();
            Thread.sleep(6000);
            //Expected - First diagnostic questions should get rendered
            Assert.assertEquals(questions.questionLabel.getText(),"Q 2.1:","first question label is not displayed");

        }catch (Exception e){
            Assert.fail("Exception in test case  'adaptivePracticeInStudyPlan' of class 'AdaptivePractice'", e);

        }
    }


    @Test(priority = 3, enabled = true)
    public void adaptivePracticeInCourseStream(){
        try{
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver,CourseStreamPage.class);
            Questions questions = PageFactory.initElements(driver, Questions.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver,AssignmentTab.class);

            new LoginUsingLTI().ltiLogin("200");//login as an instructor
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab
            lessonPage.practice_Arrow.click();//Click on practice arrow
            lessonPage.assignThis_link.click();//Click on assign this link
            new AssignLesson().assignTOCFromOrionAdaptivePractice(200);//Assign to class

            new LoginUsingLTI().ltiLogin("200_1");//login as a student
            new Navigator().NavigateTo("Course Stream");//navigate to Course Stream
            courseStreamPage.assessmentName.click();//Click on assignment

            //Expected - Pop up should have elements as mentioned below
            // Header : Diagnostic Test,Close icon
            // Text: The chapter-level adaptive practice has been assigned by your Instructor/ Mentor/Mentor. Work on the diagnostic first before starting the assignment.
            //Cancel link and Begin Diagnostic button
            Assert.assertEquals(assignments.popUp_diagnosticTestHeader.getText(), "ORION Assignment", "'ORION Assignment' text is not displayed in header");
            System.out.println(assignments.popUp_diagnosticTestBody.getText());
            Assert.assertEquals(assignments.popUp_diagnosticTestBody.getText(), "Attention! Before you can start your assignment, you need to finish a quick set of diagnostic questions.\nOnce you finish your diagnostic, you will see a report with a link to continue to your assignment.", "Body text is not displayed as expected");
            Assert.assertEquals(assignments.popUp_diagnosticTestClose.isDisplayed(),true,"Close icon is not displayed");
            Assert.assertEquals(assignments.popUp_diagnosticTestCancel.getText(),"Cancel","Cancel button is not displayed");
            Assert.assertEquals(assignments.popUp_diagnosticTest_beginDiagnostic.getText(), "Begin Diagnostic", "'Begin Diagnostic' button is not displayed");

            assignments.popUp_diagnosticTestCancel.click();//Click on cancel button

            boolean popUpDisplayedAfterCancel  = new BooleanValue().presenceOfElement(200,By.className("diagnostic-test-attempt-notification-title"));
            //Expected - Click on “Cancel” should close the popup without any action.
            Assert.assertEquals(popUpDisplayedAfterCancel, false, "Pop up is displayed after clicking on cancel");

            courseStreamPage.assessmentName.click();//Click on assignment
            assignments.popUp_diagnosticTestClose.click();//Click on close button

            boolean popUpDisplayedAfterClose  = new BooleanValue().presenceOfElement(200,By.className("diagnostic-test-attempt-notification-title"));
            //Expected - Pop up should get closed
            Assert.assertEquals(popUpDisplayedAfterClose, false, "Pop up is displayed after clicking on cancel");
            courseStreamPage.assessmentName.click();//Click on assignment

            assignments.popUp_diagnosticTest_beginDiagnostic.click();//Click on begin Diagnostic

            //Expected - Cancel  and begin button should get replaced by:
            //Enter confidence level
            // Confidence level icons
            //Arrow icon
            Assert.assertEquals(assignments.popUp_diagnosticTest_enterConfidenceLevel.getText(),"Enter Confidence Level","Enter Confidence level is not  displayed");
            Assert.assertEquals(assignments.popUp_diagnosticTest_confidenceLevelIcons.get(0).getAttribute("confidence-level"),"I don't know","Confidence level is not displayed as expected");
            Assert.assertEquals(assignments.popUp_diagnosticTest_confidenceLevelIcons.get(1).getAttribute("confidence-level"),"Somewhat confident","Confidence level is not displayed as expected");
            Assert.assertEquals(assignments.popUp_diagnosticTest_confidenceLevelIcons.get(2).getAttribute("confidence-level"),"Almost confident","Confidence level is not displayed as expected");
            Assert.assertEquals(assignments.popUp_diagnosticTest_confidenceLevelIcons.get(3).getAttribute("confidence-level"),"I know it","Confidence level is not displayed as expected");
            Assert.assertEquals(assignments.popUp_diagnosticTest_arrow.getAttribute("type"), "submit", "Arrow is not displayed");

            assignments.popUp_diagnosticTest_confidenceLevelIcons.get(1).click();//Click on confidence level icon
            assignments.popUp_diagnosticTest_arrow.click();//Click on arrow

            Thread.sleep(60000);
            //Expected - First diagnostic questions should get rendered
            Assert.assertEquals(questions.questionLabel.getText(),"Q 2.1:","first question label is not displayed");

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            Thread.sleep(5000);
            for(int a=0;a<lessonPage.getIntroductionUnderLesson().size();a++){
                if(lessonPage.getIntroductionUnderLesson().get(a).isDisplayed()){
                    lessonPage.getIntroductionUnderLesson().get(a).click();
                    Thread.sleep(5000);
                    break;

                }
            }
            new Navigator().navigateToTab("Assignments");//Navigate to Assignment tab

            //Expected - Assignment should be accessible from assessment tab at right
            if(!assignmentTab.assessmentName.getText().contains("Ch 2: The Chemical Foundation of Life")){
                Assert.fail("Assignment is not available from assessment tab");
            }

        }catch (Exception e){
            Assert.fail("Exception in test case  'adaptivePracticeInCourseStream' of class 'AdaptivePractice'", e);

        }
    }

    @Test(priority = 4, enabled = true)
    public void diagnosticInProgressAndPracticeAssigned(){
        try{
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            Questions questions = PageFactory.initElements(driver,Questions.class);

            new LoginUsingLTI().ltiLogin("271_1");//login as a student
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            new DiagnosticTest().startTest(1);//Start diagnostic test
            new DiagnosticTest().DiagonesticTestQuitBetween(1, 12, "correct", false, false, false);//continue the diagnostic test after attempting 12 questions

            new LoginUsingLTI().ltiLogin("271");//login as an instructor
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab
            lessonPage.practice_Arrow.click();//Click on practice arrow
            lessonPage.assignThis_link.click();//Click on assign this link
            new AssignLesson().assignTOCFromOrionAdaptivePractice(271);//Assign to class

            new LoginUsingLTI().ltiLogin("271_1");//login as a student
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab

            //Expected - Hybrid diagnostic card should be displayed with continue button and practice assignment end date
            Assert.assertEquals(lessonPage.button_continueDiagnostic.getAttribute("value"),"Continue Diagnostic","'Continue Diagnostic button is not displayed'");

            if(!lessonPage.assignment_dueDate.getText().contains("26, 201")){
                Assert.fail("Due date is not displayed as expected");
            }

            if(!lessonPage.dueDate.getText().contains("(Due Date:")){
                Assert.fail("Due date text is not displayed");
            }

            boolean adaptiveSectionLevel =  driver.findElement(By.xpath("//div[@class='tlo-ellipsis-content-wrapper']/a")).isDisplayed(); //new BooleanValue().presenceOfElement(271,By.xpath("//div[@class='tlo-ellipsis-content-wrapper']/a"));
                    //Expected - All the section level adaptive practice should be disabled
                    Assert.assertEquals(adaptiveSectionLevel, false, "Section level adaptive practice test is displayed");

            lessonPage.button_continueDiagnostic.click();//Click on continue diagnostic button

            Thread.sleep(60000);
            //Expected - First unattempted diagnostic questions should get rendered
            Assert.assertEquals(questions.questionLabel.getText(),"Q 2.13:","first unattempted queston is not displayed i.e. 13 as expected");

        }catch (Exception e){
            Assert.fail("Exception in test case 'diagnosticInProgressAndPracticeAssigned' of class 'AdaptivePractice'", e);

        }
    }

    @Test(priority = 5,enabled = true)
    public void diagnosticInProgressAndPracticeAssignedInAssignment(){
        try{
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            Questions questions = PageFactory.initElements(driver, Questions.class);

            new LoginUsingLTI().ltiLogin("323_1");//login as a student
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            new DiagnosticTest().startTest(1);//Start diagnostic test
            new DiagnosticTest().DiagonesticTestQuitBetween(1, 12, "correct", false, false, false);//continue the diagnostic test after attempting 12 questions

            new LoginUsingLTI().ltiLogin("323");//login as an instructor
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab
            lessonPage.practice_Arrow.click();//Click on practice arrow
            lessonPage.assignThis_link.click();//Click on assign this link
            new AssignLesson().assignTOCFromOrionAdaptivePractice(323);//Assign to class

            new LoginUsingLTI().ltiLogin("323_1");//login as a student
            new Navigator().NavigateTo("Assignments");//navigate to Assignment page

            //Expected - Assignment entry should be available
            if(!assignments.getAssignmentName().getText().contains("Ch 2: The Chemical Foundation of Life")){
                Assert.fail("Assignment entry is not available");
            }

            assignments.getAssignmentName().click();//Click on assignment

            //Expected - Pop up should have elements as mentioned below
            // Header : Diagnostic Test,Close icon
            //Text: The chapter-level adaptive practice has been assigned by your Instructor/ Mentor/Mentor. Work on the diagnostic first before starting the assignment.
            // Cancel link and Begin Diagnostic button
            Assert.assertEquals(assignments.popUp_diagnosticTestHeader.getText(),"ORION Assignment","'ORION Assignment' text is not displayed in header");
            System.out.println(assignments.popUp_diagnosticTestBody.getText());
            Assert.assertEquals(assignments.popUp_diagnosticTestBody.getText(), "Attention! Before you can start your assignment, you need to finish a quick set of diagnostic questions.\nOnce you finish your diagnostic, you will see a report with a link to continue to your assignment.", "Body text is not displayed as expected");
            Assert.assertEquals(assignments.popUp_diagnosticTestClose.isDisplayed(),true,"Close icon is not displayed");
            Assert.assertEquals(assignments.popUp_diagnosticTestCancel.getText(),"Cancel","Cancel button is not displayed");
            Assert.assertEquals(assignments.popUp_diagnosticTest_beginDiagnostic.getText(),"Continue Diagnostic","'Begin Diagnostic' button is not displayed");

            assignments.popUp_diagnosticTestCancel.click();//Click on cancel button

            boolean popUpDisplayedAfterCancel  = new BooleanValue().presenceOfElement(323,By.className("diagnostic-test-attempt-notification-title"));
            //Expected - Click on “Cancel” should close the popup without any action.
            Assert.assertEquals(popUpDisplayedAfterCancel,false,"Pop up is displayed after clicking on cancel");

            assignments.getAssignmentName().click();//Click on assignment

            assignments.popUp_diagnosticTestClose.click();//Click on close button
            boolean popUpDisplayedAfterClose  = new BooleanValue().presenceOfElement(323,By.className("diagnostic-test-attempt-notification-title"));
            //Expected - Pop up should get closed
            Assert.assertEquals(popUpDisplayedAfterClose,false,"Pop up is displayed after clicking on cancel");
            assignments.getAssignmentName().click();//Click on assignment

            assignments.popUp_diagnosticTest_beginDiagnostic.click();//Click on continue diagnostic button

            Thread.sleep(60000);
            //Expected - First unattempted diagnostic questions should get rendered
            Assert.assertEquals(questions.questionLabel.getText(),"Q 2.13:","first unattempted queston is not displayed i.e. 13 as expected");

        }catch (Exception e){
            Assert.fail("Exception in test case 'diagnosticInProgressAndPracticeAssignedInAssignment' of class 'AdaptivePractice'", e);

        }
    }

    @Test(priority = 6, enabled = true)
    public void diagnosticInProgressAndPracticeAssignedInCourseStream(){
        try{
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver,CourseStreamPage.class);
            Questions questions = PageFactory.initElements(driver, Questions.class);

            new LoginUsingLTI().ltiLogin("392_1");//login as a student
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            new DiagnosticTest().startTest(1);//Start diagnostic test
            new DiagnosticTest().DiagonesticTestQuitBetween(1, 12, "correct", false, false, false);//continue the diagnostic test after attempting 12 questions

            new LoginUsingLTI().ltiLogin("392");//login as an instructor
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab
            lessonPage.practice_Arrow.click();//Click on practice arrow
            lessonPage.assignThis_link.click();//Click on assign this link
            new AssignLesson().assignTOCFromOrionAdaptivePractice(392);//Assign to class

            new LoginUsingLTI().ltiLogin("392_1");//login as a student
            new Navigator().NavigateTo("Course Stream");//navigate to Course Stream
            courseStreamPage.assessmentName.click();//Click on assignment

            //Expected - Pop up should have elements as mentioned below
            // Header : Diagnostic Test,Close icon
            // Text: The chapter-level adaptive practice has been assigned by your Instructor/ Mentor/Mentor. Work on the diagnostic first before starting the assignment.
            //Cancel link and Begin Diagnostic button
            Assert.assertEquals(assignments.popUp_diagnosticTestHeader.getText(),"ORION Assignment","'ORION Assignment' text is not displayed in header");
            System.out.println(assignments.popUp_diagnosticTestBody.getText());
            Assert.assertEquals(assignments.popUp_diagnosticTestBody.getText(), "Attention! Before you can start your assignment, you need to finish a quick set of diagnostic questions.\nOnce you finish your diagnostic, you will see a report with a link to continue to your assignment.", "Body text is not displayed as expected");
            Assert.assertEquals(assignments.popUp_diagnosticTestClose.isDisplayed(),true,"Close icon is not displayed");
            Assert.assertEquals(assignments.popUp_diagnosticTestCancel.getText(),"Cancel","Cancel button is not displayed");
            Assert.assertEquals(assignments.popUp_diagnosticTest_beginDiagnostic.getText(),"Continue Diagnostic","'Begin Diagnostic' button is not displayed");

            assignments.popUp_diagnosticTestCancel.click();//Click on cancel button

            boolean popUpDisplayedAfterCancel  = new BooleanValue().presenceOfElement(392,By.className("diagnostic-test-attempt-notification-title"));
            //Expected - Click on “Cancel” should close the popup without any action.
            Assert.assertEquals(popUpDisplayedAfterCancel,false,"Pop up is displayed after clicking on cancel");

            courseStreamPage.assessmentName.click();//Click on assignment
            assignments.popUp_diagnosticTestClose.click();//Click on close button

            boolean popUpDisplayedAfterClose  = new BooleanValue().presenceOfElement(392,By.className("diagnostic-test-attempt-notification-title"));
            //Expected - Pop up should get closed
            Assert.assertEquals(popUpDisplayedAfterClose,false,"Pop up is displayed after clicking on cancel");
            courseStreamPage.assessmentName.click();//Click on assignment

            assignments.popUp_diagnosticTest_beginDiagnostic.click();//Click on continue Diagnostic

            Thread.sleep(60000);
            //Expected - First unattempted diagnostic questions should get rendered
            Assert.assertEquals(questions.questionLabel.getText(),"Q 2.13:","first unattempted queston is not displayed i.e. 13 as expected");

        }catch (Exception e){
            Assert.fail("Exception in test case 'diagnosticInProgressAndPracticeAssignedInCourseStream' of class 'AdaptivePractice'", e);

        }
    }

    @Test(priority = 7, enabled = true)
    public void assignmentTab(){
        try{
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            Questions questions = PageFactory.initElements(driver, Questions.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver,AssignmentTab.class);

            new LoginUsingLTI().ltiLogin("453");//login as an instructor
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab
            lessonPage.practice_Arrow.click();//Click on practice arrow
            lessonPage.assignThis_link.click();//Click on assign this link
            new AssignLesson().assignTOCFromOrionAdaptivePractice(453);//Assign to class

            new LoginUsingLTI().ltiLogin("453_1");//login as a student
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2

            Thread.sleep(3000);
            lessonPage.getIntroductionUnderLesson().get(1).click();//Click on introduction
            Thread.sleep(5000);
            new Navigator().navigateToTab("Assignments");//Navigate to Assignment tab
            assignmentTab.rightArrow.click();//click on arrow
            assignmentTab.open_button.click();//Click on open icon

            //Expected - Pop up should have elements as mentioned below
            //Header : Diagnostic Test,Close icon
            // Text: The chapter-level adaptive practice has been assigned by your Instructor/ Mentor/Mentor. Work on the diagnostic first before starting the assignment.
            // Cancel link and Begin Diagnostic button
            Assert.assertEquals(assignments.popUp_diagnosticTestHeader.getText(),"ORION Assignment","'ORION Assignment' text is not displayed in header");
            System.out.println(assignments.popUp_diagnosticTestBody.getText());
            Assert.assertEquals(assignments.popUp_diagnosticTestBody.getText(), "Attention! Before you can start your assignment, you need to finish a quick set of diagnostic questions.\nOnce you finish your diagnostic, you will see a report with a link to continue to your assignment.", "Body text is not displayed as expected");
            Assert.assertEquals(assignments.popUp_diagnosticTestClose.isDisplayed(),true,"Close icon is not displayed");
            Assert.assertEquals(assignments.popUp_diagnosticTestCancel.getText(),"Cancel","Cancel button is not displayed");
            Assert.assertEquals(assignments.popUp_diagnosticTest_beginDiagnostic.getText(),"Begin Diagnostic","'Begin Diagnostic' button is not displayed");

            assignments.popUp_diagnosticTestCancel.click();//Click on cancel button

            boolean popUpDisplayedAfterCancel  = new BooleanValue().presenceOfElement(453,By.className("diagnostic-test-attempt-notification-title"));
            //Expected - Click on “Cancel” should close the popup without any action.
            Assert.assertEquals(popUpDisplayedAfterCancel,false,"Pop up is displayed after clicking on cancel");

            assignmentTab.rightArrow.click();//click on arrow
            assignmentTab.open_button.click();//Click on open icon

            assignments.popUp_diagnosticTestClose.click();//Click on close button

            boolean popUpDisplayedAfterClose  = new BooleanValue().presenceOfElement(453,By.className("diagnostic-test-attempt-notification-title"));
            //Expected - Pop up should get closed
            Assert.assertEquals(popUpDisplayedAfterClose,false,"Pop up is displayed after clicking on cancel");

            assignmentTab.rightArrow.click();//click on arrow
            assignmentTab.open_button.click();//Click on open icon

            assignments.popUp_diagnosticTest_beginDiagnostic.click();//Click on begin Diagnostic
            selectConfidence(2);

            try {
                List<WebElement> arrows = assignments.list_popUp_diagnosticTest_arrow;
                for (WebElement c : arrows) {
                    if (c.isDisplayed()) {
                        c.click();
                        break;
                    }
                }
            }catch (Exception e){
                Assert.fail("Exception while clicking on arrow");
            }
            Thread.sleep(60000);
            //Expected - First unattempted diagnostic questions should get rendered
            Assert.assertEquals(questions.questionLabel.getText(),"Q 2.1:","first unattempted queston is not displayed i.e. 13 as expected");

        }catch (Exception e){
            Assert.fail("Exception in test case 'assignmentTab' of class 'AdaptivePractice'", e);

        }
    }

    @Test(priority = 8,enabled = true)
    public void diagnosticCompletedPracticeAssigned(){
        try{
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);

            new LoginUsingLTI().ltiLogin("524_1");//login as a student
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            new DiagnosticTest().startTest(1);//Start diagnostic test
            new DiagnosticTest().attemptAllCorrect(1, false, false);//Complete diagnostic test

            new LoginUsingLTI().ltiLogin("524");//login as an instructor
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab
            lessonPage.practice_Arrow.click();//Click on practice arrow
            lessonPage.assignThis_link.click();//Click on assign this link
            new AssignLesson().assignTOCFromOrionAdaptivePractice(524);//Assign to class

            new LoginUsingLTI().ltiLogin("524_1");//login as a student
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab

            //Expected - Due Date of the assignment should be shown - The format should be the same as for other assignments in the study plan.(Eg. Due Date: June 2,2012)
            if(!lessonPage.assignment_dueDate.getText().contains("01, 201")){
                Assert.fail("Due date is not displayed as expected");
            }

            if(!lessonPage.dueDate.getText().contains("(Due Date:")){
                Assert.fail("Due date text is not displayed");
            }

            new Navigator().NavigateTo("Assignments");//navigate to Assignment page

            //Expected : Assignment entry should be available over assignment page
            if(!assignments.getAssignmentName().getText().contains("ORION - Ch 2: The Chemical Foundation of Life")){
                Assert.fail("Assignment entry is not available");
            }
        }catch (Exception e){
            Assert.fail("Exception in test case 'diagnosticCompletedPracticeAssigned' of class 'AdaptivePractice'", e);

        }
    }

    @Test(priority = 9,enabled = true)
    public void diagnosticAndPractice(){
        try{
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);

            new LoginUsingLTI().ltiLogin("580");//login as an instructor
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab
            lessonPage.practice_Arrow.click();//Click on practice arrow
            Thread.sleep(2000);
            lessonPage.assignThis_link.click();//Click on assign this link
            new AssignLesson().assignTOCFromOrionAdaptivePractice(580);//Assign to class

            new LoginUsingLTI().ltiLogin("580_1");//login as a student
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab

            //Expected - Hybrid diagnostic card should be displayed with assignment due date
            Assert.assertEquals(lessonPage.beginDiagnostic.get(1).getAttribute("type"),"submit","Begin diagnostic button is not displayed");

            if(!lessonPage.assignment_dueDate.getText().contains("01, 201")){
                Assert.fail("Due date is not displayed as expected");
            }

            if(!lessonPage.dueDate.getText().contains("(Due Date:")){
                Assert.fail("Due date text is not displayed");
            }

            selectConfidence(1);
            lessonPage.beginDiagnostic.get(1).click();//Click on Begin Diagnostic button
            lessonPage.continue_roboNotification.click();//Click on continue
            new DiagnosticTest().DiagonesticTestQuitBetween(1, 12, "correct", false, false, false);//continue the diagnostic test after attempting 12 questions

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab

            //Expected - Hybrid diagnostic card should be displayed with continue button and practice assignment end date
            Assert.assertEquals(lessonPage.button_continueDiagnostic.getAttribute("value"),"Continue Diagnostic","'Continue Diagnostic button is not displayed'");

            if(!lessonPage.assignment_dueDate.getText().contains("01, 201")){
                Assert.fail("Due date is not displayed as expected");
            }

            if(!lessonPage.dueDate.getText().contains("(Due Date:")){
                Assert.fail("Due date text is not displayed");
            }

            lessonPage.button_continueDiagnostic.click();//Click on Continue Diagnostic button
            new DiagnosticTest().attemptAllCorrect(2, false, false);//Attempt the remaining questions

            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab

            //Expected - Assignment card should be displayed instead of assessment card(Due date should come)
            if(!lessonPage.assignment_dueDate.getText().contains("01, 201")){
                Assert.fail("Due date is not displayed as expected");
            }

            if(!lessonPage.dueDate.getText().contains("(Due Date:")){
                Assert.fail("Due date text is not displayed");
            }

            Assert.assertEquals(lessonPage.text_orionAdaptivePractice.getText(),"ORION Adaptive Practice","Orion Adaptive Practice text is not displayed");

        }catch (Exception e){
            Assert.fail("Exception in test case 'diagnosticAndPractice' of class 'AdaptivePractice'", e);

        }
    }


    @Test(priority = 10,enabled = true)
    public void attemptAndSubmitPracticeAssignment(){
        try{
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            About about = PageFactory.initElements(driver,About.class);
            Assignments assignments= PageFactory.initElements(driver,Assignments.class);
            PerformanceSummaryReport performanceSummaryReport = PageFactory.initElements(driver,PerformanceSummaryReport.class);
            ProficiencyReport proficiencyReport = PageFactory.initElements(driver,ProficiencyReport.class);
            MyActivity myActivity = PageFactory.initElements(driver,MyActivity.class);

            new LoginUsingLTI().ltiLogin("665_1");//login as a student
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            new DiagnosticTest().startTest(1);//Start diagnostic test
            new DiagnosticTest().attemptAllCorrect(1,false,false);//Complete diagnostic test

            new LoginUsingLTI().ltiLogin("665");//login as an instructor
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab
            lessonPage.practice_Arrow.click();//Click on practice arrow
            lessonPage.assignThis_link.click();//Click on assign this link
            new AssignLesson().assignTOCWithDefaultClassSection(665);//Assign to class

            new LoginUsingLTI().ltiLogin("665_1");//login as a student
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new TopicOpen().chapterOpen(1);//Open chapter 2
            lessonPage.orionAdaptivePracticeTab.click();//Click on orion adaptive practice tab

            lessonPage.getForAssignmentName().click();//click on practice assignment

            //Expected - Practice assignment should get rendered
            Assert.assertEquals(lessonPage.practiceAssignment_2ndChapter.getAttribute("class"),"tab_title","Practice assignment is not rendered");

            //Expected - The following details should be shown in the “About” tab:
            //Required Number of Questions : <Number of questions as defined by the Instructor/ Mentor for that adaptive practice assignment>
            Assert.assertEquals(about.getTexts().get(0).getText(),"Required number of questions : 5","Text is not displayed as expected");

            //Expected - Attempted Question Count : < Number of question as submitted by student>
            Assert.assertEquals(about.getTexts().get(1).getText(),"Attempted question count : 0","Text is not displayed as expected");

            //Expected - Question Difficulty - Showing difficulty level of the question and % student who got it correct - same as any personalized practice assessment.
            Assert.assertEquals(about.getQuestionDifficulty().getText(),"Question Difficulty","Question difficulty is not displayed");
            if(!about.getDifficultyLevelBlock().getText().contains("%")){
                Assert.fail("Percentage of the student is not displayed");
            }
            //Expected - Study this topic - same as any assignment
            Assert.assertEquals(about.getStudyThisTopic().getText(),"Study this topic","'Study this Topic' is not displayed");


            for(int i = 0; i < 5; i++){
                new PracticeTest().attemptPracticeTestAfterAssign(1);
            }
            Thread.sleep(2000);
            //Expected - A robo notification should be shown with the following message - “You have reached at the end of assigned questions.The score of this assignment is based on your chapter proficiency. You can continue the assignment to enhance your chapter proficiency. I'm Finished >> Keep the assignment open till due date. >>”
            Assert.assertEquals(lessonPage.getList_notificationMessage().get(0).getText(),"You have reached at the end of assigned questions. The score of this assignment is based on your chapter proficiency. You can continue the assignment to enhance your chapter proficiency.","Robo notification is not displayed as expected");
            Assert.assertEquals(lessonPage.getList_notificationMessage().get(1).getText(),"I'm Finished >> ","Robo notification is not displayed as expected");
            Assert.assertEquals(lessonPage.getList_notificationMessage().get(2).getText(),"Keep the assignment open till due date. >> ","Robo notification is not displayed as expecte");

            lessonPage.getList_notificationMessage().get(1).click();//Click on I'm Finished

            //Expected - Student should get navigated to the “Performance Summary” page for that assignment.
            Assert.assertEquals(performanceSummaryReport.chartTitle.getText(),"Performance Summary  ", "Student is not navigated to Performance Summary page");

            //Expected - Colored bars and cards should be displayed
            Assert.assertEquals(proficiencyReport.getBarChart().isDisplayed(),true,"Bars are not displayed");

            new Navigator().NavigateTo("Assignments");//navigate to Assignment page

            assignments.getAssignmentName().click();//Click on assignment
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("g[class='highcharts-series highcharts-tracker']>path")));

            //Expected - Performance summary should be displayed
            Assert.assertEquals(performanceSummaryReport.list_report.get(0).isDisplayed(),true,"Performance summary is not displayed");

            new Navigator().NavigateTo("My Activity");//navigate to My Activity
            myActivity.getLink_completedPractieAssignment().click();//Click on assignment
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("g[class='highcharts-series highcharts-tracker']>path")));

            //Expected - Performance summary should be displayed
            Assert.assertEquals(performanceSummaryReport.list_report.get(0).isDisplayed(),true,"Performance summary is not displayed");

        }catch (Exception e){
            Assert.fail("Exception in test case 'attemptAndSubmitPracticeAssignment' of class 'AdaptivePractice'", e);

        }
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

    public void selectConfidence(int confidenceLevelIndex)
    {
        try {
            new Click().clickBycssselector("div[data-id='orion-adaptive-practice-tab']");
            List<WebElement> conf = driver.findElements(By.id(Integer.toString(confidenceLevelIndex)));
            for (WebElement c : conf) {
                if (c.isDisplayed()) {
                    c.click();
                    break;
                }
            }
        }catch (Exception e){
            Assert.fail("Exception in test method 'startTest' in class 'StudyPlanEnhancement'",e);
        }
    }








}
