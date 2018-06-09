package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT20.R203;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Calender;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Gradebook.Gradebook;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.MyActivity.MyActivity;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.AssignmentTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.DiagnosticTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.TocSearch;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.EngagementReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.MostChallengingReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.PerformanceSummaryReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.ProficiencyReport;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
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

import java.util.Calendar;
import java.util.List;

/**
 * Created by Mukesh on 4/8/2015.
 */
public class AssignStaticPracticeFromStudy extends Driver{

    @Test(priority = 1,enabled = true)
    public void assignStaticPracticeFromStudyForLS()
    {
        try {
            AssignmentTab assignmentTab= PageFactory.initElements(driver, AssignmentTab.class);
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            Gradebook gradebook=PageFactory.initElements(driver,Gradebook.class);
            AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage.class);
            CourseStreamPage courseStreamPage=PageFactory.initElements(driver,CourseStreamPage.class);
            TocSearch tocSearch=PageFactory.initElements(driver,TocSearch.class);
            EngagementReport engagementReport=PageFactory.initElements(driver,EngagementReport.class);

            new LoginUsingLTI().ltiLogin("113");//login as instructor
            new LoginUsingLTI().ltiLogin("113_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            new SelectCourse().selectInvisibleAssignment("1A.8 Concept Check");
            Thread.sleep(9000);

            new LoginUsingLTI().ltiLogin("113");//login as instructor
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            lessonPage.conceptCheck.click(); // click on the static arrow link
            boolean tryIT = lessonPage.tryIt_link.isDisplayed();
            Assert.assertEquals(tryIT,true,"Try it button is not displayed");
            boolean assignThis = lessonPage.assignThis_link.isDisplayed();
            Assert.assertEquals(assignThis,true,"Assign button is not displayed");
            boolean  pointerArrow= lessonPage.pointer_arrow.isDisplayed();
            Assert.assertEquals(pointerArrow,true,"New Tab button is not displayed");

            //Tc row no 12
            //On clicking Assign icon, Assign pop-up should appear
            lessonPage.assignThis_link.click(); //click on the assign this link
            boolean assign_popup = lessonPage.assign_popup.isDisplayed();
            Assert.assertEquals(assign_popup, true, "On clicking Assign icon, Assign pop-up is not displayed");

            Assert.assertTrue(assignmentTab.AssignTO.isDisplayed());
            Assert.assertTrue(assignmentTab.gradable.isDisplayed());
            Assert.assertTrue(assignmentTab.gradingPolicy.isDisplayed());
            Assert.assertTrue(assignmentTab.accessibleDate.isDisplayed());
            Assert.assertTrue(assignmentTab.dueDate.isDisplayed());
            Assert.assertTrue(assignmentTab.additionalNotes.isDisplayed());
            new AssignLesson().assignTOCWithDefaultClassSection(113); //Assign Toc with default class section

            //Tc row no 16
            //User should remain over study plan
            new TopicOpen().chapterOpen(1);

            //Tc row no 17
            //6. Navigate to Current Assignment page
            new Navigator().NavigateTo("Current Assignments"); //Navigate to the current assignment
            boolean gradableIcon = currentAssignments.gradable_icon.isDisplayed();
            Assert.assertEquals(gradableIcon,true,"Assignment entry of static practice is not present with \"Gradable\" Label");

            //Tc row no 18
            boolean viewResponseLink=currentAssignments.getViewGrade_link().isDisplayed();
            Assert.assertEquals(viewResponseLink,true," View student responses is not displayed");

            boolean updateAssignment=currentAssignments.getUpdateAssignment_button().isDisplayed();
            Assert.assertEquals(updateAssignment,true," Update Assignment is not displayed");

            boolean unAssignAssignment=currentAssignments.getUnAssignButtonOfVersionAssignment().isDisplayed();
            Assert.assertEquals(unAssignAssignment, true, " Un-assign assignment is not displayed");

            //Tc row no 20
            currentAssignments.getAssignmentName().click(); //click on the assignment name
            Thread.sleep(3000);
            String questionPreview = currentAssignments.getQuestionCount().get(0).getText().trim();
            if(!questionPreview.contains("Q1"))
                Assert.fail("On clicking the name of the assignment, assignment preview is not getting opened");

            //Tc row no 21
            new Navigator().navigateToTab("Current Assignments");
            currentAssignments.getUpdateAssignment_button().click(); //click on the update assignment link
            int question=currentAssignments.previewQuestion.size();
            Assert.assertEquals(question,3,"update assignment page is not showing all the questions");

            currentAssignments.getReassign_button().click();//click on reassign
            driver.findElement(By.id("due-date")).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText("27")).click();
            lessonPage.assign_button1.click(); //click on the assign this link


            //Tc row no 25
            //8. Navigate to Gradebook page
            new Navigator().NavigateTo("Gradebook"); //navigate to Gradebook
            gradebook.getAllAssignmentName().get(0).click(); //click on the static test in grade book
            String responsePageTitle=assignmentResponsesPage.getPageTitle().getText().trim();
            Assert.assertEquals(responsePageTitle,"Assignment Responses","On clicking the name of the assignment, it is not redirect to assignment response page");


            //Tc row no 27
            //9. Navigate to Course stream page
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            String assessmentName=courseStreamPage.assessmentName.getAttribute("title").trim();
            Assert.assertEquals(assessmentName,"1A.8 Concept Check","Assignment entry of static practice is not present in Course Stream page");


            new LoginUsingLTI().ltiLogin("113_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            int assignmentIcon = tocSearch.studentAssessment_icon.size();
            System.out.println("assignmentIcon:"+assignmentIcon);
            Assert.assertEquals(assignmentIcon,1,"The assessment icon in not changed to assignment icon.");

            String assignmentName=tocSearch.assignment_Name.getAttribute("title").trim();
            Assert.assertEquals(assignmentName,"1A.8 Concept Check","The name of the assignment should be the is not same as the assessment");
            String dueDate=tocSearch.tocDueDate.getText();
            Calendar calendar = Calendar.getInstance();
            String cal=Calender.getCurrentMonthName(calendar.get(Calendar.MONTH)).substring(0,3)+" 01, 2016";
            String exactDueDate="(Due Date: "+cal+")";
            Assert.assertEquals(dueDate,exactDueDate,"Due Date of the assignment should be is not shown : The format should be the same as for other assignments in the Study Plan.");

            //Tc row no 29
            //Assignment entry of static practice should be present in Assignments tab of lesson page to which static practice is associated
            new SelectCourse().selectInvisibleAssignment("1A.8: European Unification");
            Thread.sleep(5000);
            new Navigator().navigateToTab("Assignments"); //navigate to Assignments tab
            boolean assessmentName2 = assignmentTab.assessmentName.isDisplayed();
            Assert.assertEquals(assessmentName2,true,"Assignment entry of static practice is not present in Assignments tab of lesson page to which static practice is associated ");

            new LoginUsingLTI().ltiLogin("113");//login as instructor
            new Navigator().NavigateTo("Engagement Report");//navigate to engagement report
            engagementReport.assessment.click(); //click on the Assessment name
            String studentTOC=tocSearch.studentTOC.getText().trim();

            if(!studentTOC.contains("Student: family, givenname"))
                Assert.fail("Instructor is not able to navigate to student TOC view");

            tocSearch.backButton.click(); //click on the back button
            Thread.sleep(3000);
            engagementReport.notStarted_label.click(); //click on the get not started
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")));
            String assessmentName1=currentAssignments.getAssignmentName().getText().trim();
            if(!assessmentName1.equals("1A.8 Concept Check"))
                Assert.fail("Assignment entry is not present in student Assignments page view");

            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            lessonPage.static_arrow.click(); // click on the static arrow link
            lessonPage.assignThis_link.click(); //click on the assign this link
            boolean assign_popup1 = lessonPage.assign_popup.isDisplayed();
            Assert.assertEquals(assign_popup1, true, "On clicking Assign icon, Assign pop-up is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in test case  assignStaticPracticeFromStudyForLS of class AssignStaticPracticeFromStudy",e);
        }
    }
    @Test(priority = 2,enabled = true)
    public void assignStaticPracticeAsNonGradableFromStudyForLS()
    {
        try {
            AssignmentTab assignmentTab= PageFactory.initElements(driver, AssignmentTab.class);
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            Gradebook gradebook=PageFactory.initElements(driver,Gradebook.class);
            AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage.class);
            CourseStreamPage courseStreamPage=PageFactory.initElements(driver,CourseStreamPage.class);
            TocSearch tocSearch=PageFactory.initElements(driver,TocSearch.class);
            EngagementReport engagementReport=PageFactory.initElements(driver,EngagementReport.class);

            Calendar calendar = Calendar.getInstance();
            new LoginUsingLTI().ltiLogin("113");//login as instructor
            new LoginUsingLTI().ltiLogin("113_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            new SelectCourse().selectInvisibleAssignment("1A.8 Concept Check");
            Thread.sleep(9000);

            new LoginUsingLTI().ltiLogin("113");//login as instructor
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            new SelectCourse().selectInvisibleAssignment("1A.8 Concept Check");

            String instructorPreview=lessonPage.getDiagnosticTestTitle().getAttribute("title").trim();
            Assert.assertEquals(instructorPreview, "(P1A.8) 1A.8 Concept Check", "Instructor is not able to open preview");
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            /*WebElement html = driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));*/
            String dueDate=tocSearch.tocDueDate.getText();

            String cal=Calender.getCurrentMonthName(calendar.get(Calendar.MONTH)).substring(0,3)+" 01, 2016";
            String exactDueDate="(Due Date: "+cal+")";
            Assert.assertEquals(dueDate,exactDueDate,"Assignment entry in eTextbook should display the assignment entry is not having latest due date in case of mutiple assignment");

            lessonPage.static_arrow.click(); // click on the static arrow link
            lessonPage.assignThis_link.click(); //click on the assign this link
            boolean assign_popup1 = lessonPage.assign_popup.isDisplayed();
            Assert.assertEquals(assign_popup1, true, "On clicking Assign icon, Assign pop-up is not displayed");
           // html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
            new AssignLesson().assignTOCWithDefaultClassSection(114); //Assign Toc with default class section

            //Tc row no 17
            //6. Navigate to Current Assignment page
            new Navigator().NavigateTo("Current Assignments"); //Navigate to the current assignment
            String assessmentName3=currentAssignments.getList_assignmentName().get(1).getText().trim();
            Assert.assertEquals(assessmentName3,"1A.2 Concept Check","Assignment entry of static practice is not present in Course Stream page");

            //Tc row no 18
            boolean viewResponseLink=currentAssignments.getViewGrade_link().isDisplayed();
            Assert.assertEquals(viewResponseLink,true," View student responses is not displayed");

            boolean updateAssignment=currentAssignments.getUpdateAssignment_button().isDisplayed();
            Assert.assertEquals(updateAssignment,true," Update Assignment is not displayed");

            boolean unAssignAssignment=currentAssignments.getUnAssignButtonOfVersionAssignment().isDisplayed();
            Assert.assertEquals(unAssignAssignment, true, " Un-assign assignment is not displayed");

            //Tc row no 20
            currentAssignments.getList_assignmentName().get(1).click(); //click on the assignment name
            Thread.sleep(2000);
            String questionPreview = currentAssignments.getQuestionCount().get(0).getText().trim();
            if(!questionPreview.contains("Q1"))
                Assert.fail("On clicking the name of the assignment, assignment preview is not getting opened");

            //Tc row no 21
            new Navigator().navigateToTab("Current Assignments");
            currentAssignments.updateAssignment_link.get(1).click(); //click on the update assignment link
            int question=currentAssignments.previewQuestion.size();
            Assert.assertEquals(question, 1, "update assignment page is not showing all the questions");

            currentAssignments.getReassign_button().click();//click on reassign
            driver.findElement(By.id("due-date")).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText("27")).click();
            lessonPage.assign_button1.click(); //click on the assign this link

            //Tc row no 25
            //8. Navigate to Gradebook page
            new Navigator().NavigateTo("Gradebook"); //navigate to Gradebook
            gradebook.getAllAssignmentName().get(0).click(); //click on the static test in grade book
            String responsePageTitle=assignmentResponsesPage.getPageTitle().getText().trim();
            Assert.assertEquals(responsePageTitle,"Assignment Responses","On clicking the name of the assignment, it is not redirect to assignment response page");


            //Tc row no 27
            //9. Navigate to Course stream page
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            String assessmentName=courseStreamPage.assessmentName.getAttribute("title").trim();
            Assert.assertEquals(assessmentName, "1A.2 Concept Check", "Assignment entry of static practice is not present in Course Stream page");

            new LoginUsingLTI().ltiLogin("113_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            int assignmentIcon = tocSearch.studentAssessment_icon.size();
            System.out.println("assignmentIcon:" + assignmentIcon);
            Assert.assertEquals(assignmentIcon, 1, "The assessment icon in not changed to assignment icon.");

            String assignmentName=tocSearch.assignmentName.getAttribute("title").trim();
            Assert.assertEquals(assignmentName,"1A.2 Concept Check","The name of the assignment should be the is not same as the assessment");
            String dueDate1=tocSearch.tocDueDate.getText();
            String cal1=Calender.getCurrentMonthName(calendar.get(Calendar.MONTH)).substring(0,3)+" 01, 2016";
            String exactDueDate1="(Due Date: "+cal1+")";
            Assert.assertEquals(dueDate1,exactDueDate1,"Due Date of the assignment should be is not shown : The format should be the same as for other assignments in the Study Plan.");

            //Tc row no 29
            //Assignment entry of static practice should be present in Assignments tab of lesson page to which static practice is associated
            new SelectCourse().selectInvisibleAssignment("1A.2: Geographical Features");
            Thread.sleep(5000);
            new Navigator().navigateToTab("Assignments"); //navigate to Assignments tab
            boolean assessmentName2 = assignmentTab.assessmentName.isDisplayed();
            Assert.assertEquals(assessmentName2,true,"Assignment entry of static practice is not present in Assignments tab of lesson page to which static practice is associated ");

            new LoginUsingLTI().ltiLogin("113");//login as instructor
            new Navigator().NavigateTo("Engagement Report");//navigate to engagement report
            engagementReport.assessment.click(); //click on the Assessment name
            String studentTOC=tocSearch.studentTOC.getText().trim();

            if(!studentTOC.contains("Student: family, givenname"))
                Assert.fail("Instructor is not able to navigate to student TOC view");

            tocSearch.backButton.click(); //click on the back button
            Thread.sleep(3000);
            engagementReport.notStarted_label.click(); //click on the get not started
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")));
            String assessmentName1=currentAssignments.getAssignmentName().getText().trim();
            if(!assessmentName1.equals("1A.8 Concept Check"))
                Assert.fail("Assignment entry is not present in student Assignments page view");



        } catch (Exception e) {
            Assert.fail("Exception in test case  assignStaticPracticeAsNonGradableFromStudyForLS of class AssignStaticPracticeFromStudy",e);
        }
    }
    @Test(priority = 3,enabled = true)
    public void viewUnAttemptedStaticPracticeAssignment()
    {
        try {
            AssignmentTab assignmentTab= PageFactory.initElements(driver, AssignmentTab.class);
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            TocSearch tocSearch=PageFactory.initElements(driver,TocSearch.class);
            Assignments assignments=PageFactory.initElements(driver, Assignments.class);
            MyActivity myActivity=PageFactory.initElements(driver,MyActivity.class);
            PerformanceSummaryReport performanceSummaryReport=PageFactory.initElements(driver, PerformanceSummaryReport.class);

            new LoginUsingLTI().ltiLogin("148");//login as instructor
            new LoginUsingLTI().ltiLogin("148_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            boolean assessment_icon=tocSearch.assessment_icon.isDisplayed();
            Assert.assertEquals(assessment_icon, true, "Before the static practice was assigned , the Study is not showing the assessment with the default view");

            new LoginUsingLTI().ltiLogin("148");//login as instructor
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            lessonPage.static_arrow.click(); // click on the static arrow link

            //Tc row no 12
            //On clicking Assign icon, Assign pop-up should appear
            lessonPage.assignThis_link.click(); //click on the assign this link
            boolean assign_popup1 = lessonPage.assign_popup.isDisplayed();
            Assert.assertEquals(assign_popup1, true, "On clicking Assign icon, Assign pop-up is not displayed");
            new AssignLesson().assignTOCWithDefaultClassSection(148); //Assign Toc with default class section
            new TopicOpen().chapterOpen(1);


            new LoginUsingLTI().ltiLogin("148_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            int assignmentIcon = tocSearch.studentAssessment_icon.size();
            System.out.println("assignmentIcon:"+assignmentIcon);
            Assert.assertEquals(assignmentIcon, 1, "The assessment icon in not changed to assignment icon.");

            String assignmentName=tocSearch.assignmentName.getAttribute("title").trim();
            Assert.assertEquals(assignmentName, "1A.2 Concept Check", "The name of the assignment should be the is not same as the assessment");

            String dueDate=tocSearch.tocDueDate.getText();

            Calendar calendar = Calendar.getInstance();
            String cal1=Calender.getCurrentMonthName(calendar.get(Calendar.MONTH)).substring(0,3)+" 01, 2016";
            String exactDueDate="(Due Date: "+cal1+")";
            Assert.assertEquals(dueDate, exactDueDate, "Due Date of the assignment should be is not shown : The format should be the same as for other assignments in the Study Plan.");

            //Tc row no 48
            new SelectCourse().selectInvisibleAssignment("1A.2: Geographical Features");
            Thread.sleep(5000);
            new Navigator().navigateToTab("Assignments"); //navigate to Assignments tab
            boolean assessmentName2 = assignmentTab.assessmentName.isDisplayed();
            Assert.assertEquals(assessmentName2, true, "Assignment entry of static practice is not present in Assignments tab of lesson page to which static practice is associated ");

            // Tc row no 50
            new Navigator().NavigateTo("Assignments"); //navigate to Assignments
            Assert.assertEquals(assignments.status_inProgress.getText().trim(), "Not Started", "Assignment entry is not present in Assignments page showing \"Not started\" status");

            assignments.assignmentName.click(); //click on the assignment name

            //Tc row no 52
            new Navigator().NavigateTo("My Activity");
            Thread.sleep(5000);
            myActivity.assignment_card.click();  //click on the assessment card
            new WebDriverWait(driver,300).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title ")));
            new Assignment().submitButtonInQuestionClick(); //finish assignment

            boolean performanceReport=performanceSummaryReport.report.isDisplayed();
            Assert.assertEquals(performanceReport, true, "performance is not generated");

            //Tc row no 54
            //Retake option should not be available
            boolean found= new BooleanValue().presenceOfElement(54, By.xpath("//div[text()='Retake']"));
            Assert.assertEquals(found,false,"Retake option is displayed");

            new TOCShow().chaptertree();
            new TopicOpen().chapterOpen(1);
            Thread.sleep(3000);
            tocSearch.assignmentname.click(); //click on the assessment name

            boolean performanceReport1=performanceSummaryReport.report.isDisplayed();
            Assert.assertEquals(performanceReport1,true,"performance is not generated");


        } catch (Exception e) {
            Assert.fail("Exception in test case  viewUnAttemptedStaticPracticeAssignment of class AssignStaticPracticeFromStudy",e);
        }
    }

    @Test(priority = 4,enabled = true)
    public void assignmentWithPolicyOne()
    {
        try {

            //Tc row no 160
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            MyActivity myActivity=PageFactory.initElements(driver, MyActivity.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "160");
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage.class);
            MostChallengingReport mostChallengingReport=null;
            EngagementReport engagementReport=null;

            new LoginUsingLTI().ltiLogin("160_1");//login as student
            new LoginUsingLTI().ltiLogin("160");//login as instructor
            new Assignment().create(160);
            for(int i=0;i<10;i++)
                new Assignment().addQuestions(160,"truefalse","");
            new LoginUsingLTI().ltiLogin("160");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");//policy one

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(160);//assign to student

            new LoginUsingLTI().ltiLogin("160_1");//login as student
            new Assignment().submitAssignmentAsStudent(160); //submit assignment
            new Navigator().NavigateTo("My Activity"); //Navigate to my activity
            myActivity.assignment_card.click();  //click on the assessment card

            new Click().clickbylistcssselector("g.highcharts-series.highcharts-tracker > rect", 0); //click on the tlo question
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            boolean overallScore=assignments.overAll_score.isDisplayed();
            Assert.assertEquals(overallScore,true,"Score is not  displayed on Assignments page");

            new Navigator().NavigateTo("Performance Report"); //navigate to Performance Report
            List<String> questionNumber=new TextFetch().fetchhiddentextbyxpath("//div[@class='question-card-question-no']");
            if(questionNumber.size()<11)
                Assert.fail("All the question bars and question cards is not present");

            new LoginUsingLTI().ltiLogin("160");//login as instructor
            new Navigator().NavigateTo("Assignments");
            currentAssignments.getViewGrade_link().click(); //click on the view grade link

            Actions actions=new Actions(driver);
            actions.keyDown(Keys.CONTROL).sendKeys(Keys.END);
            Actions action = new Actions(driver);
            Thread.sleep(2000);
            WebElement wee = assignmentResponsesPage.getViewResponse_link();
            action.moveToElement(wee).build().perform();//mouse over view response link
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            for(int i=1;i<=10;i++) {
                assignmentResponsesPage.getNextArrow().click();
                Thread.sleep(2000);
            }

            String questionIndex=assignmentResponsesPage.getQuestionIndex().getText().trim();
            Assert.assertEquals(questionIndex,"(11 of 11)","All the questions which were part of static assessment is not present in the assignment response page");
            //Tc row no 167
            new Navigator().navigateToTab("Response - Assignment_IT18_160_1");
            Thread.sleep(5000);
            String status=assignmentResponsesPage.getReviewStatus().getAttribute("title");
            Assert.assertEquals(status,"Graded","Assignment status is not a graded");

            new Assignment().updateGradeAfterGradeRelease(162);
            String grade=assignmentResponsesPage.getGrade().getText();
            Assert.assertEquals(grade,"11.6","Instructoris not able to change the grade after grade release as the product supports currently");

            //Tc row no 169
            new RunScheduledJobs().runScheduledJobsForDashboard();

            mostChallengingReport=PageFactory.initElements(driver,MostChallengingReport.class);
            engagementReport=PageFactory.initElements(driver,EngagementReport.class);
            new LoginUsingLTI().ltiLogin("160");//login as instructor

            new Navigator().NavigateTo("Performance Report"); //navigate to Performance Report
            driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")).click();//click on ch 1
            new Click().clickByclassname("lsCoursePerformanceByChapters-xAxisLabel");
            String proficiencyDrill=new TextFetch().textfetchbyclass("ir-report-header-title");
            String expected="Class Performance by Questions";
            if(!proficiencyDrill.contains(expected))
                Assert.fail("Instructor is not able to drill down to lowest level of Proficiency Report");

            new Navigator().NavigateTo("Most Challenging Chapters Report");
            String mostPerformance1=mostChallengingReport.getChapPerformance().get(0).getText().trim();
            if(!mostPerformance1.equals("6/11")) {
                Assert.fail("Performance report is not generating correctly");
            }
            //17. Navigate to Engagement Reports
            new Navigator().NavigateTo("Engagement Report");//navigate to Engagement Report
            String engagQuestionPercentage1= engagementReport.getQuestionPerSummary().getText().trim();
            if(engagQuestionPercentage1.equals(" ")|| engagQuestionPercentage1==null){
                Assert.fail("Engagement report is not generating correctly");

            }
            String totalGrade1=engagementReport.getStudTotalGrade().getText().trim();
            if(totalGrade1.equals(" ")|| totalGrade1==null){
                Assert.fail("Total Grade is not generating correctly");

            }


        } catch (Exception e) {
            Assert.fail("Exception in test case  assignmentWithPolicyOne of class AssignStaticPracticeFromStudy",e);
        }
    }
    @Test(priority = 5,enabled = true)
    public void assignmentWithPolicyThree()
    {
        try {

            //Tc row no 160
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "181");
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage.class);
            MostChallengingReport mostChallengingReport;
            new LoginUsingLTI().ltiLogin("181_1");//login as student
            new LoginUsingLTI().ltiLogin("181");//login as instructor
            new Assignment().create(181);
            new Assignment().addQuestions(181, "essay", "");
            new Assignment().addQuestions(181, "essay", "");
            for(int i=0;i<7;i++)
                new Assignment().addQuestions(181,"truefalse","");
            new LoginUsingLTI().ltiLogin("181");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "");//policy three

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(181);//assign to student

            new LoginUsingLTI().ltiLogin("181_1");//login as student
            new Assignment().submitAssignmentAsStudent(181); //submit assignment*//*

            new LoginUsingLTI().ltiLogin("181");//login as instructor
            new Assignment().updateGradeAfterGradeRelease(181);

            new LoginUsingLTI().ltiLogin("181");//login as instructor
            new Navigator().NavigateTo("Assignments");
            currentAssignments.getViewGrade_link().click(); //click on the view grade link

            Actions actions=new Actions(driver);
            actions.keyDown(Keys.CONTROL).sendKeys(Keys.END);
            Actions action = new Actions(driver);
            Thread.sleep(2000);
            WebElement wee = assignmentResponsesPage.getViewResponse_link();
            action.moveToElement(wee).build().perform();//mouse over view response link
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            for(int i=1;i<=9;i++) {
                assignmentResponsesPage.getNextArrow().click();
                Thread.sleep(2000);
            }

            String questionIndex=assignmentResponsesPage.getQuestionIndex().getText().trim();
            Assert.assertEquals(questionIndex,"(10 of 10)","All the questions which were part of static assessment is not present in the assignment response page");
            //Tc row no 182
            new Navigator().navigateToTab("Response - Assignment_IT18_181_1");
            Thread.sleep(3000);
            assignmentResponsesPage.refresh_icon.click();
            boolean releaseGrade=new BooleanValue().presenceOfElement(181,By.cssSelector("div[title='Release Grade for All']"));
            Thread.sleep(5000);
            Assert.assertEquals(releaseGrade,false,"Grades is not getting released");

            //Tc row no 183
            Thread.sleep(5000);
            String status=assignmentResponsesPage.getReviewStatus().getAttribute("title");
            Assert.assertEquals(status,"Graded","Assignment status is not a graded");

            new Assignment().updateGradeAfterGradeRelease(182);
            String grade=assignmentResponsesPage.getGrade().getText();
            Assert.assertEquals(grade,"9.6","Instructoris not able to change the grade after grade release as the product supports currently");

            new RunScheduledJobs().runScheduledJobsForDashboard();

            mostChallengingReport=PageFactory.initElements(driver,MostChallengingReport.class);
            ProficiencyReport proficiencyReport=PageFactory.initElements(driver,ProficiencyReport.class);
            new LoginUsingLTI().ltiLogin("181_1");//login as student
            new Navigator().NavigateTo("Performance Report"); //navigate to Performance Report
            List<String> questionNumber=new TextFetch().fetchhiddentextbyxpath("//div[@class='question-card-question-no']");
            if(questionNumber.size()<10)
                Assert.fail("All the question bars and question cards is not present");
            proficiencyReport.getBarChart().click(); //click on the ch 1
            Thread.sleep(4000);
            int questionBarSize=proficiencyReport.getBarChartElementsList().size();
            if(questionBarSize<10)
                Assert.fail("All the question bars and question cards is not present in chapter by performance page");

            new Navigator().NavigateTo("Most Challenging Chapters Report");
            String mostPerformance1=mostChallengingReport.getStudChapPerformance().get(0).getText().trim();
            if(!mostPerformance1.equals("10/20")) {
                Assert.fail("Performance report is not generating correctly");
            }


        } catch (Exception e) {
            Assert.fail("Exception in test case  assignmentWithPolicyThree of class AssignStaticPracticeFromStudy",e);
        }
    }
    @Test(priority = 6,enabled = true)
    public void assignmentWithPolicyFour()
    {
        try {

            //Tc row no 191
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "191");
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver, AssignmentResponsesPage.class);
            MostChallengingReport mostChallengingReport;
            new LoginUsingLTI().ltiLogin("191_1");//login as student
            new LoginUsingLTI().ltiLogin("191");//login as instructor
            new Assignment().create(191);
            for(int i=0;i<9;i++)
                new Assignment().addQuestions(191,"truefalse","");
            new LoginUsingLTI().ltiLogin("191");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description21", "2", null, false, "1", "", "", "", "", "");//Policy 4

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(191);//assign to student

            new LoginUsingLTI().ltiLogin("191_1");//login as student
            new Assignment().submitAssignmentAsStudent(191); //submit assignment

            new LoginUsingLTI().ltiLogin("191");//login as instructor
            new Navigator().NavigateTo("Assignments");
            currentAssignments.getViewGrade_link().click(); //click on the view grade link

            Actions actions=new Actions(driver);
            actions.keyDown(Keys.CONTROL).sendKeys(Keys.END);
            Actions action = new Actions(driver);
            Thread.sleep(2000);
            WebElement wee = assignmentResponsesPage.getViewResponse_link();
            action.moveToElement(wee).build().perform();//mouse over view response link
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            for(int i=1;i<=9;i++) {
                assignmentResponsesPage.getNextArrow().click();
                Thread.sleep(2000);
            }

            String questionIndex=assignmentResponsesPage.getQuestionIndex().getText().trim();
            Assert.assertEquals(questionIndex, "(10 of 10)", "All the questions which were part of static assessment is not present in the assignment response page");
            //Tc row no 182
            new Navigator().navigateToTab("Response - Assignment_IT18_191_1");
            Thread.sleep(3000);
            new Assignment().releaseGrades(191, "Release Grade for All");
            boolean releaseGrade=new BooleanValue().presenceOfElement(191,By.cssSelector("div[data-localize='gradeReleased']"));
            Assert.assertEquals(releaseGrade, true, "Grades is not getting released");
            //Tc row no 183
            Thread.sleep(5000);
            String status=assignmentResponsesPage.getReviewStatus().getAttribute("title");
            Assert.assertEquals(status, "Graded", "Assignment status is not a graded");

            new Assignment().updateGradeAfterGradeRelease(192);
            Thread.sleep(4000);
            String grade=assignmentResponsesPage.getGrade().getText();
            Assert.assertEquals(grade, "9.6", "Instructoris not able to change the grade after grade release as the product supports currently");

            new RunScheduledJobs().runScheduledJobsForDashboard();

            mostChallengingReport=PageFactory.initElements(driver,MostChallengingReport.class);
            ProficiencyReport proficiencyReport=PageFactory.initElements(driver, ProficiencyReport.class);
            new LoginUsingLTI().ltiLogin("191_1");//login as student
            new Navigator().NavigateTo("Performance Report"); //navigate to Performance Report
            List<String> questionNumber=new TextFetch().fetchhiddentextbyxpath("//div[@class='question-card-question-no']");
            if(questionNumber.size()<10)
                Assert.fail("All the question bars and question cards is not present");
            proficiencyReport.getBarChart().click(); //click on the ch 1
            Thread.sleep(4000);
            int questionBarSize=proficiencyReport.getBarChartElementsList().size();
            if(questionBarSize<10)
                Assert.fail("All the question bars and question cards is not present in chapter by performance page");

            new Navigator().NavigateTo("Most Challenging Chapters Report");
            String mostPerformance1=mostChallengingReport.getStudChapPerformance().get(0).getText().trim();
            if(!mostPerformance1.equals("10/20")) {
                Assert.fail("Performance report is not generating correctly");
            }


        } catch (Exception e) {
            Assert.fail("Exception in test case  assignmentWithPolicyFour of class AssignStaticPracticeFromStudy",e);
        }
    }
    @Test(priority = 7,enabled = true)
    public void staticAssessmentAsNonGradableAssignment()
    {
        try {

            //Tc row no 201
            CurrentAssignments currentAssignments=PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage.class);
            MyActivity myActivity=PageFactory.initElements(driver, MyActivity.class);
            PerformanceSummaryReport performanceSummaryReport=PageFactory.initElements(driver,PerformanceSummaryReport.class);
            MostChallengingReport mostChallengingReport=null;
            EngagementReport engagementReport=null;
            Assignments assignments=null;
            ProficiencyReport proficiencyReport=null;

            new LoginUsingLTI().ltiLogin("201_1");//login as student
            new LoginUsingLTI().ltiLogin("201");//login as instructor
            new Assignment().create(201);
            for(int i=0;i<9;i++)
                new Assignment().addQuestions(201,"truefalse","");

            new LoginUsingLTI().ltiLogin("201");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(201);//assign to student*/

            new LoginUsingLTI().ltiLogin("201_1");//login as student
            new Assignment().submitAssignmentAsStudent(201); //submit assignment

            new Navigator().NavigateTo("My Activity");
            Thread.sleep(5000);
            myActivity.assignment_card.click();  //click on the assessment card
            new WebDriverWait(driver,300).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title ")));

            boolean performanceReport=performanceSummaryReport.report.isDisplayed();
            Assert.assertEquals(performanceReport,true,"performance is not generated");

            new LoginUsingLTI().ltiLogin("201");//login as instructor

            new Navigator().NavigateTo("Assignments");
            currentAssignments.getViewGrade_link().click(); //click on the view grade link

            Actions actions=new Actions(driver);
            actions.keyDown(Keys.CONTROL).sendKeys(Keys.END);
            Actions action = new Actions(driver);
            Thread.sleep(2000);
            WebElement wee = assignmentResponsesPage.getViewResponse_link();
            action.moveToElement(wee).build().perform();//mouse over view response link
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            //TC row no 203 & 204

            currentAssignments.getFeedBack_textBox().click();//click on the feedback box
            currentAssignments.getFeedBack_textBox().clear();
            currentAssignments.getFeedBack_textBox().sendKeys("This is a new FeedbackText");
            assignmentResponsesPage.getSaveButton().click();
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getSaveMessage().getText().trim(),"Saved successfully.","Grade and feedback is not save successfully");
            for(int i=1;i<=9;i++) {
                assignmentResponsesPage.getNextArrow().click();
                Thread.sleep(2000);
            }
            //Tc row no 202

            String questionIndex=assignmentResponsesPage.getQuestionIndex().getText().trim();
            Assert.assertEquals(questionIndex,"(10 of 10)","All the questions which were part of static assessment is not present in the assignment response page");

            //Tc row no 205
            new Assignment().releaseGrades(201, "Release Feedback for All");//click on the release feedback

            //Tc row no 206
            Thread.sleep(3000);
            String status=assignmentResponsesPage.getReviewStatus().getAttribute("title");
            Assert.assertEquals(status,"Reviewed","Assignment status is not a Reviewed");

            new RunScheduledJobs().runScheduledJobsForDashboard();
             mostChallengingReport=PageFactory.initElements(driver,MostChallengingReport.class);
             engagementReport=PageFactory.initElements(driver,EngagementReport.class);
             assignments=PageFactory.initElements(driver,Assignments.class);
             proficiencyReport=PageFactory.initElements(driver,ProficiencyReport.class);

            new LoginUsingLTI().ltiLogin("201");//login as instructor
            new Navigator().NavigateTo("Performance Report"); //navigate to Performance Report
            driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")).click();//click on ch 1
            new Click().clickByclassname("lsCoursePerformanceByChapters-xAxisLabel");
            String proficiencyDrill=new TextFetch().textfetchbyclass("ir-report-header-title");
            String expected="Class Performance by Questions";
            if(!proficiencyDrill.contains(expected))
                Assert.fail("Instructor is not able to drill down to lowest level of Proficiency Report");

            new Navigator().NavigateTo("Most Challenging Chapters Report");
            String mostPerformance1=mostChallengingReport.getChapPerformance().get(0).getText().trim();
            if(!mostPerformance1.equals("10/10")) {
                Assert.fail("Performance report is not generating correctly");
            }
            //17. Navigate to Engagement Reports
            new Navigator().NavigateTo("Engagement Report");//navigate to Engagement Report
            String engagQuestionPercentage1= engagementReport.getQuestionPerSummary().getText().trim();
            if(!engagQuestionPercentage1.equals("100%")){
                Assert.fail("Engagement report is not generating correctly");

            }
            String totalGrade1=engagementReport.getStudTotalGrade().getText().trim();
            System.out.println("totalGrade1:"+totalGrade1);
            if(totalGrade1==null) {
                Assert.fail("Total Grade is not generating correctly");

            }
            //Tc row no 207
            new LoginUsingLTI().ltiLogin("201_1");//login as student
            new Navigator().NavigateTo("Assignments");
            Thread.sleep(2000);
            Assert.assertEquals(assignments.assignmentFilter_review.getText().trim(), "Reviewed", "The status of the activity is not \"Reviewed\"");

            Assert.assertEquals(assignments.status_submitted.getText().trim(),"Reviewed","The status of the activity is not \"Reviewed\"");

            new Navigator().NavigateTo("Performance Report"); //navigate to Performance Report
            List<String> questionNumber=new TextFetch().fetchhiddentextbyxpath("//div[@class='question-card-question-no']");
            if(questionNumber.size()<10)
                Assert.fail("All the question bars and question cards is not present");
            proficiencyReport.getBarChart().click(); //click on the ch 1
            Thread.sleep(4000);
            int questionBarSize=proficiencyReport.getBarChartElementsList().size();
            if(questionBarSize<10)
                Assert.fail("All the question bars and question cards is not present in chapter by performance page");

            new Navigator().NavigateTo("Most Challenging Chapters Report");
            String mostPerformance2=mostChallengingReport.getStudChapPerformance().get(0).getText().trim();
            if(!mostPerformance2.equals("10/10")) {
                Assert.fail("Performance report is not generating correctly");
            }



        } catch (Exception e) {
            Assert.fail("Exception in test case  staticAssessmentAsNonGradableAssignment of class AssignStaticPracticeFromStudy",e);
        }
    }
    @Test(priority = 8,enabled = true)
    public void assignStaticPracticeFromStudy()
    {
        try {
            AssignmentTab assignmentTab= PageFactory.initElements(driver, AssignmentTab.class);
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            Gradebook gradebook=PageFactory.initElements(driver,Gradebook.class);
            AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage.class);
            CourseStreamPage courseStreamPage=PageFactory.initElements(driver,CourseStreamPage.class);
            TocSearch tocSearch=PageFactory.initElements(driver,TocSearch.class);
            EngagementReport engagementReport=PageFactory.initElements(driver,EngagementReport.class);

            new LoginUsingLTI().ltiLogin("11");//login as instructor
            new LoginUsingLTI().ltiLogin("11_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            new SelectCourse().selectInvisibleAssignment("2.1 Concept Check");
            Thread.sleep(9000);

            new LoginUsingLTI().ltiLogin("11");//login as instructor
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            lessonPage.static_arrow1.click(); // click on the static arrow link
            boolean tryIT = lessonPage.tryIt_link.isDisplayed();
            Assert.assertEquals(tryIT,true,"Try it button is not displayed");
            boolean assignThis = lessonPage.assignThis_link.isDisplayed();
            Assert.assertEquals(assignThis,true,"Assign button is not displayed");
            boolean  pointerArrow= lessonPage.pointer_arrow.isDisplayed();
            Assert.assertEquals(pointerArrow,true,"New Tab button is not displayed");

            //Tc row no 12
            //On clicking Assign icon, Assign pop-up should appear
            lessonPage.assignThis_link.click(); //click on the assign this link
            boolean assign_popup = lessonPage.assign_popup.isDisplayed();
            Assert.assertEquals(assign_popup, true, "On clicking Assign icon, Assign pop-up is not displayed");

            Assert.assertTrue(assignmentTab.AssignTO.isDisplayed());
            Assert.assertTrue(assignmentTab.gradable.isDisplayed());
            Assert.assertTrue(assignmentTab.gradingPolicy.isDisplayed());
            Assert.assertTrue(assignmentTab.accessibleDate.isDisplayed());
            Assert.assertTrue(assignmentTab.dueDate.isDisplayed());
            Assert.assertTrue(assignmentTab.additionalNotes.isDisplayed());
            new AssignLesson().assignTOCWithDefaultClassSection(11); //Assign Toc with default class section

            //Tc row no 16
            //User should remain over study plan
            new TopicOpen().chapterOpen(1);

            //Tc row no 17
            //6. Navigate to Current Assignment page
            new Navigator().NavigateTo("Current Assignments"); //Navigate to the current assignment
            boolean gradableIcon = currentAssignments.gradable_icon.isDisplayed();
            Assert.assertEquals(gradableIcon,true,"Assignment entry of static practice is not present with \"Gradable\" Label");

            //Tc row no 18
            boolean viewResponseLink=currentAssignments.getViewGrade_link().isDisplayed();
            Assert.assertEquals(viewResponseLink,true," View student responses is not displayed");

            boolean updateAssignment=currentAssignments.getUpdateAssignment_button().isDisplayed();
            Assert.assertEquals(updateAssignment,true," Update Assignment is not displayed");

            boolean unAssignAssignment=currentAssignments.getUnAssignButtonOfVersionAssignment().isDisplayed();
            Assert.assertEquals(unAssignAssignment,true," Un-assign assignment is not displayed");

            //Tc row no 20
            currentAssignments.getAssignmentName().click(); //click on the assignment name
            String questionPreview = currentAssignments.getQuestionCount().get(0).getText().trim();
            if(!questionPreview.contains("Q1"))
                Assert.fail("On clicking the name of the assignment, assignment preview is not getting opened");

            //Tc row no 21
            new Navigator().navigateToTab("Current Assignments");
            currentAssignments.getUpdateAssignment_button().click(); //click on the update assignment link
            int question=currentAssignments.previewQuestion.size();
            Assert.assertEquals(question,3,"update assignment page is not showing all the questions");

            currentAssignments.getReassign_button().click();//click on reassign
            driver.findElement(By.id("due-date")).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText("27")).click();
            lessonPage.assign_button1.click(); //click on the assign this link


            //Tc row no 25
            //8. Navigate to Gradebook page
            new LoginUsingLTI().ltiLogin("11");//login as instructor
            new Navigator().NavigateTo("Gradebook"); //navigate to Gradebook
            gradebook.getAllAssignmentName().get(0).click(); //click on the static test in grade book
            String responsePageTitle=assignmentResponsesPage.getPageTitle().getText().trim();
            Assert.assertEquals(responsePageTitle,"Assignment Responses","On clicking the name of the assignment, it is not redirect to assignment response page");


            //Tc row no 27
            //9. Navigate to Course stream page
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            String assessmentName=courseStreamPage.assessmentName.getAttribute("title").trim();
            Assert.assertEquals(assessmentName, "2.1 Concept Check", "Assignment entry of static practice is not present in Course Stream page");

            new LoginUsingLTI().ltiLogin("11_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            int assignmentIcon = tocSearch.studentAssessment_icon1.size();
            System.out.println("assignmentIcon:"+assignmentIcon);
            Assert.assertEquals(assignmentIcon,1,"The assessment icon in not changed to assignment icon.");

            String assignmentName=tocSearch.assignmentName1.getText().trim();
            Assert.assertEquals(assignmentName,"2.1 Concept Check","The name of the assignment should be the is not same as the assessment");
            String dueDate=tocSearch.tocDueDate.getText();
            Calendar calendar=Calendar.getInstance();
            String cal=Calender.getCurrentMonthName(calendar.get(Calendar.MONTH)).substring(0,3)+" 01, 2016";
            String exactDueDate="(Due Date: "+cal+")";
            Assert.assertEquals(dueDate,exactDueDate,"Due Date of the assignment should be is not shown : The format should be the same as for other assignments in the Study Plan.");

            //Tc row no 29
            //Assignment entry of static practice should be present in Assignments tab of lesson page to which static practice is associated
            new SelectCourse().selectInvisibleAssignment("2.1: Atoms, Isotopes, Ions, and Molecules: The Building Blocks");
            Thread.sleep(5000);
            new Navigator().navigateToTab("Assignments"); //navigate to Assignments tab
            boolean assessmentName2 = assignmentTab.assessmentName.isDisplayed();
            Assert.assertEquals(assessmentName2,true,"Assignment entry of static practice is not present in Assignments tab of lesson page to which static practice is associated ");

            new LoginUsingLTI().ltiLogin("11");//login as instructor
            new Navigator().NavigateTo("Engagement Report");//navigate to engagement report
            engagementReport.assessment.click(); //click on the Assessment name
            String studentTOC=tocSearch.studentTOC.getText().trim();

            if(!studentTOC.contains("Student: family, givenname"))
                Assert.fail("Instructor is not able to navigate to student TOC view");

            tocSearch.backButton.click(); //click on the back button
            Thread.sleep(3000);
            engagementReport.notStarted_label.click(); //click on the get not started
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")));
            String assessmentName1=currentAssignments.getAssignmentName().getText().trim();
            if(!assessmentName1.equals("2.1 Concept Check"))
                Assert.fail("Assignment entry is not present in student Assignments page view");

            //Tc row no 32
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            lessonPage.static_arrow1.click(); // click on the static arrow link
            lessonPage.assignThis_link.click(); //click on the assign this link
            boolean assign_popup1 = lessonPage.assign_popup.isDisplayed();
            Assert.assertEquals(assign_popup1, true, "On clicking Assign icon, Assign pop-up is not displayed");


        } catch (Exception e) {
            Assert.fail("Exception in test case  assignStaticPracticeFromStudy of class AssignStaticPracticeFromStudy",e);
        }
    }
    @Test(priority = 9,enabled = true)
    public void assignStaticPracticeAsNonGradableFromStudy()
    {
        try {
            AssignmentTab assignmentTab= PageFactory.initElements(driver, AssignmentTab.class);
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            Gradebook gradebook=PageFactory.initElements(driver,Gradebook.class);
            AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage.class);
            CourseStreamPage courseStreamPage=PageFactory.initElements(driver,CourseStreamPage.class);
            TocSearch tocSearch=PageFactory.initElements(driver,TocSearch.class);
            EngagementReport engagementReport=PageFactory.initElements(driver,EngagementReport.class);
            Calendar calendar=Calendar.getInstance();

            new LoginUsingLTI().ltiLogin("11");//login as instructor
            new LoginUsingLTI().ltiLogin("11_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            new SelectCourse().selectInvisibleAssignment("2.1 Concept Check");
            Thread.sleep(9000);

            new LoginUsingLTI().ltiLogin("11");//login as instructor
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            new SelectCourse().selectInvisibleAssignment("2.1 Concept Check");

            String instructorPreview=lessonPage.getDiagnosticTestTitle().getAttribute("title").trim();
            Assert.assertEquals(instructorPreview,"(P2.1) 2.1 Concept Check","Instructor is not able to open preview");

            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            String dueDate=tocSearch.tocDueDate.getText();
            String cal=Calender.getCurrentMonthName(calendar.get(Calendar.MONTH)).substring(0,3)+" 01, 2016";
            String exactDueDate="(Due Date: "+cal+")";
            Assert.assertEquals(dueDate,exactDueDate,"Assignment entry in eTextbook should display the assignment entry is not having latest due date in case of mutiple assignment");

            lessonPage.static_arrow1.click(); // click on the static arrow link
            lessonPage.assignThis_link.click(); //click on the assign this link
            boolean assign_popup1 = lessonPage.assign_popup.isDisplayed();
            Assert.assertEquals(assign_popup1, true, "On clicking Assign icon, Assign pop-up is not displayed");
            new AssignLesson().assignTOCWithDefaultClassSection(12); //Assign Toc with default class section

            //Tc row no 36
            String dueDate1=tocSearch.tocDueDate.getText();
            String cal1=Calender.getCurrentMonthName(calendar.get(Calendar.MONTH)).substring(0,3)+" 01, 2016";
            String exactDueDate1="(Due Date: "+cal1+")";
            Assert.assertEquals(dueDate1,exactDueDate1,"Assignment entry in eTextbook should display the assignment entry is not having latest due date in case of mutiple assignment");



            //Tc row no 17
            //6. Navigate to Current Assignment page
            new Navigator().NavigateTo("Current Assignments"); //Navigate to the current assignment
            boolean gradableIcon = currentAssignments.gradable_icon.isDisplayed();
            Assert.assertEquals(gradableIcon,true,"Assignment entry of static practice is not present with \"Gradable\" Label");

            //Tc row no 18
            boolean viewResponseLink=currentAssignments.getViewGrade_link().isDisplayed();
            Assert.assertEquals(viewResponseLink,true," View student responses is not displayed");

            boolean updateAssignment=currentAssignments.getUpdateAssignment_button().isDisplayed();
            Assert.assertEquals(updateAssignment,true," Update Assignment is not displayed");

            boolean unAssignAssignment=currentAssignments.getUnAssignButtonOfVersionAssignment().isDisplayed();
            Assert.assertEquals(unAssignAssignment,true," Un-assign assignment is not displayed");

            //Tc row no 20
            currentAssignments.getAssignmentName().click(); //click on the assignment name
            String questionPreview = currentAssignments.getQuestionCount().get(0).getText().trim();
            if(!questionPreview.contains("Q1"))
                Assert.fail("On clicking the name of the assignment, assignment preview is not getting opened");

            //Tc row no 21
            new Navigator().navigateToTab("Current Assignments");
            currentAssignments.getUpdateAssignment_button().click(); //click on the update assignment link
            currentAssignments.getReassign_button().click();//click on reassign
            driver.findElement(By.id("due-date")).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("span[class='ui-icon ui-icon-circle-triangle-e']")).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText("1")).click();
            lessonPage.assign_button1.click(); //click on the assign this link

            //Tc row no 25
            //8. Navigate to Gradebook page
            new LoginUsingLTI().ltiLogin("11");//login as instructor
            new Navigator().NavigateTo("Gradebook"); //navigate to Gradebook
            gradebook.getAllAssignmentName().get(0).click(); //click on the static test in grade book
            String responsePageTitle=assignmentResponsesPage.getPageTitle().getText().trim();
            Assert.assertEquals(responsePageTitle,"Assignment Responses","On clicking the name of the assignment, it is not redirect to assignment response page");


            //Tc row no 27
            //9. Navigate to Course stream page
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            String assessmentName=courseStreamPage.assessmentName.getAttribute("title").trim();
            Assert.assertEquals(assessmentName,"2.1 Concept Check","Assignment entry of static practice is not present in Course Stream page");

            new LoginUsingLTI().ltiLogin("11_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            int assignmentIcon = tocSearch.studentAssessment_icon1.size();
            System.out.println("assignmentIcon:"+assignmentIcon);
            Assert.assertEquals(assignmentIcon,1,"The assessment icon in not changed to assignment icon.");

            String assignmentName=tocSearch.assignmentName1.getText().trim();
            Assert.assertEquals(assignmentName,"2.1 Concept Check","The name of the assignment should be the is not same as the assessment");
            String dueDate2=tocSearch.tocDueDate.getText();
            String cal2=Calender.getCurrentMonthName(calendar.get(Calendar.MONTH)).substring(0,3)+" 01, 2016";
            String exactDueDate2="(Due Date: "+cal2+")";
            Assert.assertEquals(dueDate2,exactDueDate2,"Due Date of the assignment should be is not shown : The format should be the same as for other assignments in the Study Plan.");

            //Tc row no 29
            //Assignment entry of static practice should be present in Assignments tab of lesson page to which static practice is associated
            new SelectCourse().selectInvisibleAssignment("2.1: Atoms, Isotopes, Ions, and Molecules: The Building Blocks");
            Thread.sleep(5000);
            new Navigator().navigateToTab("Assignments"); //navigate to Assignments tab
            boolean assessmentName2 = assignmentTab.assessmentName.isDisplayed();
            Assert.assertEquals(assessmentName2,true,"Assignment entry of static practice is not present in Assignments tab of lesson page to which static practice is associated ");

            new LoginUsingLTI().ltiLogin("11");//login as instructor
            new Navigator().NavigateTo("Engagement Report");//navigate to engagement report
            engagementReport.assessment.click(); //click on the Assessment name
            String studentTOC=tocSearch.studentTOC.getText().trim();

            if(!studentTOC.contains("Student: family, givenname"))
                Assert.fail("Instructor is not able to navigate to student TOC view");

            tocSearch.backButton.click(); //click on the back button
            Thread.sleep(3000);
            //Tc row no 45
            engagementReport.notStarted_label.click(); //click on the get not started
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")));
            String assessmentName1=currentAssignments.getAssignmentName().getText().trim();
            if(!assessmentName1.equals("2.1 Concept Check"))
                Assert.fail("Assignment entry is not present in student Assignments page view");


        } catch (Exception e) {
            Assert.fail("Exception in test case  assignStaticPracticeAsNonGradableFromStudy of class AssignStaticPracticeFromStudy",e);
        }
    }



    @Test(priority = 10,enabled = true)
    public void assignStaticPracticeFromStudyForLSAdaptive()
    {
        try {
            AssignmentTab assignmentTab= PageFactory.initElements(driver, AssignmentTab.class);
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            CourseStreamPage courseStreamPage=PageFactory.initElements(driver,CourseStreamPage.class);
            TocSearch tocSearch=PageFactory.initElements(driver,TocSearch.class);
            new LoginUsingLTI().ltiLogin("215");//login as instructor
            WebElement html = driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            lessonPage.orionAdaptivePracticeTab.click();
            lessonPage.practice_Arrow.click(); // click on the static arrow link
            boolean tryIT = lessonPage.tryIt_link.isDisplayed();
            Assert.assertEquals(tryIT,true,"Try it button is not displayed");
            boolean assignThis = lessonPage.assignThis_link.isDisplayed();
            Assert.assertEquals(assignThis,true,"Assign button is not displayed");
            boolean  pointerArrow= lessonPage.pointer_arrow.isDisplayed();
            Assert.assertEquals(pointerArrow,true,"New Tab button is not displayed");
            //Tc row no 12
            //On clicking Assign icon, Assign pop-up should appear
            lessonPage.assignThis_link.click(); //click on the assign this link
            boolean assign_popup = lessonPage.assign_popup.isDisplayed();
            Assert.assertEquals(assign_popup, true, "On clicking Assign icon, Assign pop-up is not displayed");
            Assert.assertTrue(assignmentTab.AssignTO.isDisplayed(),"Assignto is not displaying");
            Assert.assertTrue(assignmentTab.numberOfQuestions.isDisplayed(),"Number of questions is not diaplaying");
            Assert.assertTrue(assignmentTab.gradable.isDisplayed(),"Gradable checkbox is not displaying");
            Assert.assertTrue(assignmentTab.gradingPolicy.isDisplayed(),"Grading policy is not displaying");
            Assert.assertTrue(assignmentTab.accessibleDate.isDisplayed(), "Accessible date is not displaying");
            Assert.assertTrue(assignmentTab.dueDate.isDisplayed(),"Due date is not displaying");
            //html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            Assert.assertTrue(assignmentTab.additionalNotes.isDisplayed(), "Additional note is not displaying");
            //check assignment name at Header
            Assert.assertEquals(assignmentTab.assignmentName.getText(), "ORION Ch 2: The Chemical Foundation of Life", "Header name is not showing properly");
            //try to edit assignment name
            assignmentTab.editAssignmentName.click();
            assignmentTab.inputAssignmentName.clear();
            assignmentTab.inputAssignmentName.sendKeys("Assignment name");
            Assert.assertEquals(assignmentTab.assignmentName.getText(), "Assignment name", "Header name is not showing properly");
            driver.findElement(By.xpath("//html/body")).click();
            Thread.sleep(2000);
            lessonPage.practice_Arrow.click(); // click on the static arrow link
            lessonPage.assignThis_link.click();
            assignmentTab.numberOfQuestionHelpLabel.click();
            Assert.assertEquals(assignmentTab.getNumberOfQuestionHelpText.getText(), "Enter the number of questions that students are required to answer. Students will receive a grade of zero if they do not answer the required number of questions.", "Application is not displaying help text");
            int totalNumberOfQuestions=Integer.parseInt(assignmentTab.numberOfQuestions.getAttribute("totalquestions"))+1;
            assignmentTab.numberOfQuestions.clear();
            assignmentTab.numberOfQuestions.sendKeys(String.valueOf(totalNumberOfQuestions));
            //verify error message for total number of question when user give more than total number of questions
            Assert.assertTrue(assignmentTab.getErrorMessageForTotalNumberOfQuestions.getText().contains("The number of questions for this assignment should not be more than"), "Error message for total number of question is not displaying properly");
            //verify help text for gradable textbox
            assignmentTab.gradableHelpLabel.click();
            Assert.assertEquals(assignmentTab.getNumberOfQuestionHelpText.getText(), "The default grading policy would be to release the grade for a student post submitting the assignment or on auto-submission because of due date", "Application is not displaying help text");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id("assign-cancel")));
            lessonPage.practice_Arrow.click(); // click on the static arrow link
            lessonPage.assignThis_link.click();

            new AssignLesson().assignTOCFromOrionAdaptivePractice(215); //Assign Toc with default class section
            //navigate to course stream
            // Navigate to Course stream page
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            String assessmentName=courseStreamPage.assessmentName.getAttribute("title").trim();
            Assert.assertEquals(assessmentName,"ORION Ch 2: The Chemical Foundation of Life","Assignment entry of static practice is not present in Course Stream page");

            //6. Navigate to Current Assignment page
            new Navigator().NavigateTo("Current Assignments"); //Navigate to the current assignment
            boolean gradableIcon = currentAssignments.gradable_icon.isDisplayed();
            Assert.assertEquals(gradableIcon,true,"Assignment entry of static practice is not present with \"Gradable\" Label");

            //Tc row no 18
            boolean viewResponseLink=currentAssignments.getViewGrade_link().isDisplayed();
            Assert.assertEquals(viewResponseLink,true," View student responses is not displayed");

            boolean updateAssignment=currentAssignments.getUpdateAssignment_button().isDisplayed();
            Assert.assertEquals(updateAssignment,true," Update Assignment is not displayed");

            boolean unAssignAssignment=currentAssignments.getUnAssignButtonOfVersionAssignment().isDisplayed();
            Assert.assertEquals(unAssignAssignment,true," Un-assign assignment is not displayed");

            new LoginUsingLTI().ltiLogin("215_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            lessonPage.orionAdaptivePracticeTab.click();
            int assignmentIcon = tocSearch.studentAssessment_icon.size();
            System.out.println("assignmentIcon:"+assignmentIcon);
            Assert.assertTrue(tocSearch.adaptiveTocDueDate.getText().contains("(Due Date:"),"Assessment is not displaying at Student side");

            new LoginUsingLTI().ltiLogin("215");//login as instructor
            new Navigator().NavigateTo("Current Assignments"); //navigate to Assignments tab

            //unassign the assignment
            currentAssignments.getUnAssignButtonOfVersionAssignment().click();
            currentAssignments.getYesOnUnAssignPopUp().click();
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            lessonPage.orionAdaptivePracticeTab.click();
            //check Assignment due date should present on screen
            try {
                if(tocSearch.tocDueDate.isDisplayed())
                    Assert.fail("Assignment due date is still displaying on toc");

            }
            catch(Exception e)
            {}
            //assign chapter level assignment
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            lessonPage.orionAdaptivePracticeTab.click();
            lessonPage.practice_Arrow.click(); // click on the static arrow link
            lessonPage.assignThis_link.click();
            new AssignLesson().assignTOCFromOrionAdaptivePractice(215); //Assign Toc with default class section
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.updateAssignment_link.get(0).click();
            driver.findElement(By.id("due-date")).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText("30")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")));
            Thread.sleep(5000);
            Assert.assertTrue(currentAssignments.getOriginalDueDate().getText().contains("30"),"Due date not get updated as  Expected");
            //unassign the assessment
            currentAssignments.getUnAssignButtonOfVersionAssignment().click();
            currentAssignments.getYesOnUnAssignPopUp().click();

            //tc no 247
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            lessonPage.orionAdaptivePracticeTab.click();
            lessonPage.practice_Arrow.click(); // click on the static arrow link
            lessonPage.assignThis_link.click();
            new AssignLesson().assignTOCFromOrionAdaptivePractice(216); //Assign Toc with default class section
            // Navigate to Course stream page
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            assessmentName=courseStreamPage.assessmentName.getAttribute("title").trim();
            Assert.assertEquals(assessmentName,"ORION Ch 2: The Chemical Foundation of Life","Assignment entry of static practice is not present in Course Stream page");

            //6. Navigate to Current Assignment page
            new Navigator().NavigateTo("Current Assignments"); //Navigate to the current assignment
            gradableIcon = currentAssignments.gradable_icon.isDisplayed();
            Assert.assertEquals(gradableIcon,true,"Assignment entry of static practice is not present with \"Gradable\" Label");

            //Tc row no 18
            viewResponseLink=currentAssignments.getViewGrade_link().isDisplayed();
            Assert.assertEquals(viewResponseLink,true," View student responses is not displayed");

            updateAssignment=currentAssignments.getUpdateAssignment_button().isDisplayed();
            Assert.assertEquals(updateAssignment,true," Update Assignment is not displayed");

            unAssignAssignment=currentAssignments.getUnAssignButtonOfVersionAssignment().isDisplayed();
            Assert.assertEquals(unAssignAssignment,true," Un-assign assignment is not displayed");

            new LoginUsingLTI().ltiLogin("215_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            lessonPage.orionAdaptivePracticeTab.click();
            Assert.assertTrue(tocSearch.adaptiveTocDueDate.getText().contains("(Due Date:"),"Assessment is not displaying at Student side");

            new LoginUsingLTI().ltiLogin("215");//login as instructor
            new Navigator().NavigateTo("Current Assignments"); //navigate to Assignments tab

            //unassign the assignment
            currentAssignments.getUnAssignButtonOfVersionAssignment().click();
            currentAssignments.getYesOnUnAssignPopUp().click();
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            lessonPage.orionAdaptivePracticeTab.click();
            //check Assignment due date should present on screen
            try {
                if(tocSearch.tocDueDate.isDisplayed())
                    Assert.fail("Assignment due date is still displaying on toc");

            }
            catch(Exception e)
            {}
            //assign chapter level assignment
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            lessonPage.orionAdaptivePracticeTab.click();
            lessonPage.practice_Arrow.click(); // click on the static arrow link
            lessonPage.assignThis_link.click();
            new AssignLesson().assignTOCFromOrionAdaptivePractice(216); //Assign Toc with default class section
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.updateAssignment_link.get(0).click();
            driver.findElement(By.id("due-date")).click();
            Thread.sleep(2000);
            driver.findElement(By.linkText("30")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='btn sty-green submit-assign']")));
            Thread.sleep(5000);
            Assert.assertTrue(currentAssignments.getOriginalDueDate().getText().contains("30"), "Due date not get updated as  Expected");
            html.sendKeys(Keys.chord(Keys.CONTROL, "0"));


        } catch (Exception e) {
            Assert.fail("Exception in test case  assignStaticPracticeFromStudyForLSAdaptive of class AssignStaticPracticeFromStudy",e);
        }
    }
    @Test(priority = 11,enabled = true)
    public void viewUnAttemptedStaticPracticeAssignmentAdaptive()
    {
        try {
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            TocSearch tocSearch=PageFactory.initElements(driver,TocSearch.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            MyActivity myActivity=PageFactory.initElements(driver,MyActivity.class);
            PerformanceSummaryReport performanceSummaryReport=PageFactory.initElements(driver,PerformanceSummaryReport.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);


            new LoginUsingLTI().ltiLogin("46");//login as instructor
            new LoginUsingLTI().ltiLogin("46_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            boolean assessment_icon=tocSearch.assessment_icon_Adaptive.isDisplayed();
            Assert.assertEquals(assessment_icon,true,"Before the static practice was assigned , the Study is not showing the assessment with the default view");


            new LoginUsingLTI().ltiLogin("46");//login as instructor
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            Thread.sleep(3000);
            lessonPage.static_arrow1.click(); // click on the static arrow link
            //Tc row no 12
            //On clicking Assign icon, Assign pop-up should appear
            lessonPage.assignThis_link.click(); //click on the assign this link
            boolean assign_popup1 = lessonPage.assign_popup.isDisplayed();
            Assert.assertEquals(assign_popup1, true, "On clicking Assign icon, Assign pop-up is not displayed");
            new AssignLesson().assignTOCFromOrionAdaptivePractice(46); //Assign Toc with default class section
            new TopicOpen().chapterOpen(1);

            new LoginUsingLTI().ltiLogin("46_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            int assignmentIcon = tocSearch.studentAssessment_icon1.size();
            System.out.println("assignmentIcon:"+assignmentIcon);
            Assert.assertEquals(assignmentIcon,1,"The assessment icon in not changed to assignment icon.");

            String assignmentName=tocSearch.assignmentNameAdaptive.getAttribute("title").trim();
            Assert.assertEquals(assignmentName,"2.1 Concept Check","The name of the assignment should be the is not same as the assessment");
            String dueDate=tocSearch.tocDueDate.getText();
            Calendar calendar = Calendar.getInstance();
            String cal2=Calender.getCurrentMonthName(calendar.get(Calendar.MONTH)+ 1).substring(0,3)+" 01, 2015";
            String exactDueDate2="(Due Date: "+cal2+")";
            Assert.assertEquals(dueDate,exactDueDate2,"Due Date of the assignment should be is not shown : The format should be the same as for other assignments in the Study Plan.");

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
            new LoginUsingLTI().ltiLogin("46_1");//login as student
            new Navigator().NavigateTo("My Activity");
            Thread.sleep(5000);
            myActivity.assignment_card.click();  //click on the assessment card
            new WebDriverWait(driver,300).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title ")));

            new Assignment().submitButtonInQuestionClick(); //finish assignment

            new Navigator().NavigateTo("e-Textbook"); //navigate to e-Textbook
            new TopicOpen().chapterOpen(1);
            new SelectCourse().selectInvisibleAssignment("2.1 Concept Check");
            new AttemptTest().StaticTestAttemptAllTypeWithConfidence(1,false,false);
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
            Assert.fail("Exception in test case  viewUnAttemptedStaticPracticeAssignment of class AssignStaticPracticeFromStudy",e);
        }
    }





    @Test(priority = 12,enabled = true)
    public void assignmentWithPolicyOneAdaptive()
    {
        try {

            //Tc row no 58
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            MyActivity myActivity=PageFactory.initElements(driver, MyActivity.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "58");
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage.class);
            MostChallengingReport mostChallengingReport=null;
            EngagementReport engagementReport=null;

            new LoginUsingLTI().ltiLogin("58_1");//login as student
            new LoginUsingLTI().ltiLogin("58");//login as instructor
            new Assignment().create(58);
            for(int i=0;i<10;i++)
                new Assignment().addQuestions(58,"truefalse","");
            new LoginUsingLTI().ltiLogin("58");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");//policy one

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(58);//assign to student

            new LoginUsingLTI().ltiLogin("58_1");//login as student
            new LoginUsingLTI().ltiLogin("58_1");//login as student
            new Assignment().submitAssignmentAsStudent(58); //submit assignment
            new Navigator().NavigateTo("My Activity"); //Navigate to my activity
            myActivity.assignment_card.click();  //click on the assessment card
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")));
            ((JavascriptExecutor)driver).executeScript("",driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")));
            new Navigator().NavigateTo("Assignments"); //navigate to assignment
            boolean overallScore=assignments.overAll_score.isDisplayed();
            Assert.assertEquals(overallScore,true,"Score is not  displayed on Assignments page");


            new Navigator().NavigateTo("Proficiency Report"); //navigate to Performance Report
            List<String> questionNumber=new TextFetch().fetchhiddentextbyxpath("//div[@class='question-card-question-no']");
            if(questionNumber.size()<11)
                Assert.fail("All the question bars and question cards is not present");

            new LoginUsingLTI().ltiLogin("58");//login as instructor
            new Navigator().NavigateTo("Assignments");
            currentAssignments.getViewGrade_link().click(); //click on the view grade link

            Actions actions=new Actions(driver);
            actions.keyDown(Keys.CONTROL).sendKeys(Keys.END);
            Actions action = new Actions(driver);
            Thread.sleep(2000);
            WebElement wee = assignmentResponsesPage.getViewResponse_link();
            action.moveToElement(wee).build().perform();//mouse over view response link
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            for(int i=1;i<=10;i++) {
                assignmentResponsesPage.getNextArrow().click();
                Thread.sleep(2000);
            }

            String questionIndex=assignmentResponsesPage.getQuestionIndex().getText().trim();
            Assert.assertEquals(questionIndex,"(11 of 11)","All the questions which were part of static assessment is not present in the assignment response page");
            //Tc row no 167
            new Navigator().navigateToTab("Response - Assignment_IT203_58_1");
            Thread.sleep(5000);
            String status=assignmentResponsesPage.getReviewStatus().getAttribute("title");
            Assert.assertEquals(status,"Graded","Assignment status is not a graded");

            new Assignment().updateGradeAfterGradeRelease(59);
            String grade=assignmentResponsesPage.getGrade().getText();
            Assert.assertEquals(grade,"11.6","Instructoris not able to change the grade after grade release as the product supports currently");


            //Tc row no 169
            new RunScheduledJobs().runScheduledJobsForDashboard();
            mostChallengingReport=PageFactory.initElements(driver,MostChallengingReport.class);
            engagementReport=PageFactory.initElements(driver,EngagementReport.class);
            new LoginUsingLTI().ltiLogin("58");//login as instructor
            new Navigator().NavigateTo("Proficiency Report"); //navigate to Performance Report
            driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")).click();//click on ch 1
            new Click().clickByclassname("coursePerformanceByChapters-xAxisLabel");
            String proficiencyDrill=new TextFetch().textfetchbyclass("ir-report-header-title");
            String expected="Class Proficiency by Objectives";
            if(!proficiencyDrill.contains(expected))
                Assert.fail("Instructor is not able to drill down to lowest level of Proficiency Report");

            new Navigator().NavigateTo("Most Challenging Activities Report");
            String mostPerformance1=mostChallengingReport.getChapPerformance().get(0).getText().trim();
            if(!mostPerformance1.equals("6/11")) {
                Assert.fail("Performance report is not generating correctly");
            }
            //17. Navigate to Engagement Reports
            new Navigator().NavigateTo("Engagement Report");//navigate to Engagement Report
            String engagQuestionPercentage1= engagementReport.getQuestionPerSummary().getText().trim();
            System.out.println("engagementReport "+engagementReport.getQuestionPerSummary().getText().trim());
            if(!engagQuestionPercentage1.equals("27%64%9%")){
                Assert.fail("Engagement report is not generating correctly");

            }
            String totalGrade1=engagementReport.getStudTotalGrade().getText().trim();
            System.out.println("getStudTotalGrade "+engagementReport.getStudTotalGrade().getText().trim());
            if(!totalGrade1.equals("53.0%")){
                Assert.fail("Total Grade is not generating correctly");
            }
        } catch (Exception e) {
            Assert.fail("Exception in test case  assignmentWithPolicyOneAdaptive of class AssignStaticPracticeFromStudy",e);
        }
    }
    @Test(priority = 13,enabled = true)
    public void assignmentWithPolicyThreeAdaptive()
    {
        try {

            //Tc row no 160
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "79");
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage.class);
            MostChallengingReport mostChallengingReport;
            EngagementReport engagementReport;
            new LoginUsingLTI().ltiLogin("79_1");//login as student
            new LoginUsingLTI().ltiLogin("79");//login as instructor
            new Assignment().create(79);
            new Assignment().addQuestions(79, "essay", "");
            new Assignment().addQuestions(79, "essay", "");
            for(int i=0;i<7;i++)
                new Assignment().addQuestions(79,"truefalse","");
            new LoginUsingLTI().ltiLogin("79");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "");//policy three

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(79);//assign to student

            new LoginUsingLTI().ltiLogin("79_1");//login as student
            new Assignment().submitAssignmentAsStudent(79); //submit assignment*//**//*

            new LoginUsingLTI().ltiLogin("79");//login as instructor
            new Assignment().updateGradeAfterGradeRelease(79);

            new LoginUsingLTI().ltiLogin("79");//login as instructor
            new Navigator().NavigateTo("Assignments");
            currentAssignments.getViewGrade_link().click(); //click on the view grade link

            Actions actions=new Actions(driver);
            actions.keyDown(Keys.CONTROL).sendKeys(Keys.END);
            Actions action = new Actions(driver);
            Thread.sleep(2000);
            WebElement wee = assignmentResponsesPage.getViewResponse_link();
            action.moveToElement(wee).build().perform();//mouse over view response link
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            for(int i=1;i<=9;i++) {
                assignmentResponsesPage.getNextArrow().click();
                Thread.sleep(2000);
            }

            String questionIndex=assignmentResponsesPage.getQuestionIndex().getText().trim();
            Assert.assertEquals(questionIndex,"(10 of 10)","All the questions which were part of static assessment is not present in the assignment response page");
            //Tc row no 182
            new Navigator().navigateToTab("Response - Assignment_IT20_79_1");
            Thread.sleep(3000);
            assignmentResponsesPage.refresh_icon.click();
            boolean releaseGrade=new BooleanValue().presenceOfElement(79,By.cssSelector("div[title='Release Grade for All']"));
            Thread.sleep(5000);
            Assert.assertEquals(releaseGrade,false,"Grades is not getting released");

            //Tc row no 183
            Thread.sleep(5000);
            String status=assignmentResponsesPage.getReviewStatus().getAttribute("title");
            Assert.assertEquals(status,"Graded","Assignment status is not a graded");

            new Assignment().updateGradeAfterGradeRelease(80);
            String grade=assignmentResponsesPage.getGrade().getText();
            Assert.assertEquals(grade,"9.6","Instructoris not able to change the grade after grade release as the product supports currently");

            mostChallengingReport=PageFactory.initElements(driver,MostChallengingReport.class);
            ProficiencyReport proficiencyReport=PageFactory.initElements(driver,ProficiencyReport.class);
            new LoginUsingLTI().ltiLogin("79_1");//login as student
            new Navigator().NavigateTo("Proficiency Report"); //navigate to Performance Report
            List<String> questionNumber=new TextFetch().fetchhiddentextbyxpath("//div[@class='question-card-question-no']");
            if(questionNumber.size()<10)
                Assert.fail("All the question bars and question cards is not present");
            proficiencyReport.getBarChart().click(); //click on the ch 1
            Thread.sleep(4000);
            proficiencyReport.getBarChart().click(); //click on the ch 1
            Thread.sleep(4000);
            int questionBarSize=proficiencyReport.getBarChartElementsList().size();
            if(questionBarSize<10)
                Assert.fail("All the question bars and question cards is not present in chapter by performance page");

            new Navigator().NavigateTo("Most Challenging Activities Report");
            String mostPerformance1=mostChallengingReport.getStudChapPerformance().get(0).getText().trim();
            if(!mostPerformance1.equals("10/20")) {
                Assert.fail("Performance report is not generating correctly");
            }


        } catch (Exception e) {
            Assert.fail("Exception in test case  assignmentWithPolicyThree of class AssignStaticPracticeFromStudy",e);
        }
    }
    @Test(priority = 14,enabled = true)
    public void assignmentWithPolicyFourAdaptive()
    {
        try {

            //Tc row no 89
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "89");
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage.class);
            MostChallengingReport mostChallengingReport;
            new LoginUsingLTI().ltiLogin("89_1");//login as student
            new LoginUsingLTI().ltiLogin("89");//login as instructor
            new Assignment().create(89);
            for(int i=0;i<9;i++)
                new Assignment().addQuestions(89,"truefalse","");
            new LoginUsingLTI().ltiLogin("89");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description21", "2", null, false, "1", "", "", "", "", "");//Policy 4

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(89);//assign to student

            new LoginUsingLTI().ltiLogin("89_1");//login as student
            new Assignment().submitAssignmentAsStudent(89); //submit assignment

            new LoginUsingLTI().ltiLogin("89");//login as instructor
            new Navigator().NavigateTo("Assignments");
            currentAssignments.getViewGrade_link().click(); //click on the view grade link

            Actions actions=new Actions(driver);
            actions.keyDown(Keys.CONTROL).sendKeys(Keys.END);
            Actions action = new Actions(driver);
            Thread.sleep(2000);
            WebElement wee = assignmentResponsesPage.getViewResponse_link();
            action.moveToElement(wee).build().perform();//mouse over view response link
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            for(int i=1;i<=9;i++) {
                assignmentResponsesPage.getNextArrow().click();
                Thread.sleep(2000);
            }

            String questionIndex=assignmentResponsesPage.getQuestionIndex().getText().trim();
            Assert.assertEquals(questionIndex, "(10 of 10)", "All the questions which were part of static assessment is not present in the assignment response page");
            //Tc row no 182
            new Navigator().navigateToTab("Response - Assignment_IT20_89_1");
            Thread.sleep(3000);
            new Assignment().releaseGrades(89, "Release Grade for All");
            Thread.sleep(1000);
            boolean releaseGrade=driver.findElement(By.cssSelector("div[data-localize='gradeReleased']")).isDisplayed();
            //new BooleanValue().presenceOfElement(191,By.cssSelector("div[data-localize='gradeReleased']"));
            Assert.assertEquals(releaseGrade, false, "Grades is not getting released");
            //Tc row no 183
            Thread.sleep(5000);
            String status=assignmentResponsesPage.getReviewStatus().getAttribute("title");
            Assert.assertEquals(status,"Graded","Assignment status is not a graded");

            new Assignment().updateGradeAfterGradeRelease(90);
            Thread.sleep(4000);
            String grade=assignmentResponsesPage.getGrade().getText();
            Assert.assertEquals(grade,"9.6","Instructoris not able to change the grade after grade release as the product supports currently");
            new RunScheduledJobs().runScheduledJobsForDashboard();

            mostChallengingReport=PageFactory.initElements(driver,MostChallengingReport.class);
            ProficiencyReport proficiencyReport=PageFactory.initElements(driver,ProficiencyReport.class);
            new LoginUsingLTI().ltiLogin("89_1");//login as student
            new Navigator().NavigateTo("Proficiency Report"); //navigate to Performance Report
            List<String> questionNumber=new TextFetch().fetchhiddentextbyxpath("//div[@class='question-card-question-no']");
            if(questionNumber.size()<10)
                Assert.fail("All the question bars and question cards is not present");

            proficiencyReport.getBarChart().click(); //click on the ch 1
            Thread.sleep(2000);
            proficiencyReport.getBarChart().click(); //click on the ch 1
            Thread.sleep(4000);
            int questionBarSize=proficiencyReport.getBarChartElementsList().size();
            if(questionBarSize<10)
                Assert.fail("All the question bars and question cards is not present in chapter by performance page");

            new Navigator().NavigateTo("Most Challenging Activities Report");
            String mostPerformance1=mostChallengingReport.getStudChapPerformance().get(0).getText().trim();
            if(!mostPerformance1.equals("10/20")) {
                Assert.fail("Performance report is not generating correctly");
            }


        } catch (Exception e) {
            Assert.fail("Exception in test case  assignmentWithPolicyFour of class AssignStaticPracticeFromStudy",e);
        }
    }




    @Test(priority = 15,enabled = true)
    public void staticAssessmentAsNonGradableAssignmentAdaptive()
    {
        try {

            //Tc row no 99
            CurrentAssignments currentAssignments=PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage.class);
            MyActivity myActivity=PageFactory.initElements(driver,MyActivity.class);
            PerformanceSummaryReport performanceSummaryReport=PageFactory.initElements(driver,PerformanceSummaryReport.class);
            MostChallengingReport mostChallengingReport=null;
            EngagementReport engagementReport=null;
            Assignments assignments=null;
            ProficiencyReport proficiencyReport=null;

            new LoginUsingLTI().ltiLogin("99_1");//login as student
            new LoginUsingLTI().ltiLogin("99");//login as instructor
            new Assignment().create(99);
            for(int i=0;i<9;i++)
                new Assignment().addQuestions(99,"truefalse","");

            new LoginUsingLTI().ltiLogin("99");//login as instructor
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(99);//assign to student

            new LoginUsingLTI().ltiLogin("99_1");//login as student
            new Assignment().submitAssignmentAsStudent(99); //submit assignment

            new Navigator().NavigateTo("My Activity");
            Thread.sleep(5000);
            myActivity.assignment_card.click();  //click on the assessment card
            new WebDriverWait(driver,300).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title ")));

            boolean performanceReport=performanceSummaryReport.report.isDisplayed();
            Assert.assertEquals(performanceReport,true,"performance is not generated");

            new LoginUsingLTI().ltiLogin("99");//login as instructor

            new Navigator().NavigateTo("Assignments");
            currentAssignments.getViewGrade_link().click(); //click on the view grade link

            Actions actions=new Actions(driver);
            actions.keyDown(Keys.CONTROL).sendKeys(Keys.END);
            Actions action = new Actions(driver);
            Thread.sleep(2000);
            WebElement wee = assignmentResponsesPage.getViewResponse_link();
            action.moveToElement(wee).build().perform();//mouse over view response link
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            //TC row no 203 & 204

            currentAssignments.getFeedBack_textBox().click();//click on the feedback box
            currentAssignments.getFeedBack_textBox().clear();
            currentAssignments.getFeedBack_textBox().sendKeys("This is a new FeedbackText");
            assignmentResponsesPage.getSaveButton().click();
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getSaveMessage().getText().trim(),"Saved successfully.","Grade and feedback is not save successfully");
            for(int i=1;i<=9;i++) {
                assignmentResponsesPage.getNextArrow().click();
                Thread.sleep(2000);
            }
            //Tc row no 202

            String questionIndex=assignmentResponsesPage.getQuestionIndex().getText().trim();
            Assert.assertEquals(questionIndex,"(10 of 10)","All the questions which were part of static assessment is not present in the assignment response page");

            //Tc row no 205
            new Assignment().releaseGrades(99, "Release Feedback for All");//click on the release feedback

            //Tc row no 206
            Thread.sleep(3000);
            String status=assignmentResponsesPage.getReviewStatus().getAttribute("title");
            Assert.assertEquals(status,"Reviewed","Assignment status is not a Reviewed");

            new RunScheduledJobs().runScheduledJobsForDashboard();

            mostChallengingReport=PageFactory.initElements(driver,MostChallengingReport.class);
            engagementReport=PageFactory.initElements(driver,EngagementReport.class);
            assignments=PageFactory.initElements(driver,Assignments.class);
            proficiencyReport=PageFactory.initElements(driver,ProficiencyReport.class);
            new LoginUsingLTI().ltiLogin("99");//login as instructor

            new Navigator().NavigateTo("Proficiency Report"); //navigate to Performance Report
            driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")).click();//click on ch 1
            new Click().clickByclassname("coursePerformanceByChapters-xAxisLabel");
            String proficiencyDrill=new TextFetch().textfetchbyclass("ir-report-header-title");
            String expected="Class Performance by Questions";
            if(!proficiencyDrill.contains(expected))
                Assert.fail("Instructor is not able to drill down to lowest level of Proficiency Report");

            new Navigator().NavigateTo("Most Challenging Activities Report");
            String mostPerformance1=mostChallengingReport.getChapPerformance().get(0).getText().trim();
            if(!mostPerformance1.equals("10/10")) {
                Assert.fail("Performance report is not generating correctly");
            }
            //17. Navigate to Engagement Reports
            new Navigator().NavigateTo("Engagement Report");//navigate to Engagement Report
            String engagQuestionPercentage1= engagementReport.getQuestionPerSummary().getText().trim();
            if(!engagQuestionPercentage1.equals("100%")){
                Assert.fail("Engagement report is not generating correctly");

            }
            String totalGrade1=engagementReport.getStudTotalGrade().getText().trim();
            System.out.println("totalGrade1:"+totalGrade1);
            if(totalGrade1==null) {
                Assert.fail("Total Grade is not generating correctly");

            }
            //Tc row no 207
            new LoginUsingLTI().ltiLogin("99_1");//login as student
            new Navigator().NavigateTo("Assignments");
            Assert.assertEquals(assignments.assignmentFilter_review.getText().trim(),"Reviewed","The status of the activity is not \"Reviewed\"");

            Assert.assertEquals(assignments.status_submitted.getText().trim(),"Reviewed","The status of the activity is not \"Reviewed\"");

            new Navigator().NavigateTo("Proficiency Report"); //navigate to Performance Report
            List<String> questionNumber=new TextFetch().fetchhiddentextbyxpath("//div[@class='question-card-question-no']");
            if(questionNumber.size()<10)
                Assert.fail("All the question bars and question cards is not present");
            proficiencyReport.getBarChart().click(); //click on the ch 1
            Thread.sleep(3000);
            proficiencyReport.getBarChart().click(); //click on the ch 1
            Thread.sleep(4000);
            int questionBarSize=proficiencyReport.getBarChartElementsList().size();
            if(questionBarSize<10)
                Assert.fail("All the question bars and question cards is not present in chapter by performance page");

            new Navigator().NavigateTo("Metacognitive Report");
            driver.findElement(By.cssSelector("g.highcharts-markers.highcharts-tracker > path")).click();//click on the Red circle
            Thread.sleep(5000);
            String metacogDrill=new TextFetch().textfetchbyid("reportSubTitle");
            Assert.assertEquals("Objectives",metacogDrill,"Student si not  able to drill down to lowest level of Metacognitive Report");

            (new Navigator()).NavigateTo("Productivity Report");
            driver.findElement(By.cssSelector("g.highcharts-markers.highcharts-tracker > path")).click();//click on the Red circle
            Thread.sleep(5000);
            String prodDrill=new TextFetch().textfetchbyid("reportSubTitle");
            Assert.assertEquals("Objectives",prodDrill,"Student si not  able to drill down to lowest level of Productivity Report");

            new Navigator().NavigateTo("Most Challenging Activities Report");
            String mostPerformance2=mostChallengingReport.getStudChapPerformance().get(0).getText().trim();
            if(!mostPerformance2.equals("10/10")) {
                Assert.fail("Performance report is not generating correctly");
            }



        } catch (Exception e) {
            Assert.fail("Exception in test case  staticAssessmentAsNonGradableAssignment of class AssignStaticPracticeFromStudy",e);
        }
    }
}