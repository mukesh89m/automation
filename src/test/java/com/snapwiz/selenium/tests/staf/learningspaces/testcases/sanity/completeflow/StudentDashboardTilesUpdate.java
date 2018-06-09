package com.snapwiz.selenium.tests.staf.learningspaces.testcases.sanity.completeflow;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.MyActivity.MyActivity;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.MyNotes.MyNote;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.DiscussionTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.ResourceTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Perspective;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.PerformanceSummaryReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.ProficiencyReport;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

/**
 * Created by Mukesh on 10-Nov-14.
 */
public class StudentDashboardTilesUpdate extends Driver {

    LessonPage lessonPage;
    DiscussionTab discussionTab;
    CourseStreamPage courseStreamPage;
    Perspective perspective;
    Assignments assignments;
    Dashboard dashboard;
    ProficiencyReport proficiencyReport;
    AssignmentResponsesPage assignmentResponsesPage;
    CurrentAssignments currentAssignments;
    PerformanceSummaryReport performanceSummaryReport;
    ResourceTab resourceTab;
    MyNote myNote;
    MyActivity myActivity;

    @BeforeMethod
    public void initializeWebElement() {
        WebDriver driver=Driver.getWebDriver();
        lessonPage = PageFactory.initElements(driver, LessonPage.class);
        discussionTab = PageFactory.initElements(driver, DiscussionTab.class);
        courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
        perspective = PageFactory.initElements(driver, Perspective.class);
        assignments = PageFactory.initElements(driver, Assignments.class);
        dashboard = PageFactory.initElements(driver, Dashboard.class);
        proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);
        assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
        currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
        performanceSummaryReport = PageFactory.initElements(driver, PerformanceSummaryReport.class);
        resourceTab = PageFactory.initElements(driver, ResourceTab.class);
        myNote = PageFactory.initElements(driver, MyNote.class);
        myActivity = PageFactory.initElements(driver, MyActivity.class);


    }

    @Test(priority = 1,enabled = true)
    public void updateRecentActivityAndStatistics()
    {
        try {

            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "43");

            new Assignment().create(43); //create assignment
            ReportUtil.log("create assessment", "Assessment created  successfully", "info");

            ReportUtil.log("Login as instructor", "Login as instructor for updateRecentActivityAndStatistics method", "pass");
            new LoginUsingLTI().ltiLogin("43");//login as instructor
            new Assignment().assignToStudent(43); //assign to the student
            ReportUtil.log("Assign Assignment", "Instructor assigned assignment to complete class section", "pass");


            WebDriverUtil.clickOnElementUsingJavascript(courseStreamPage.commentLinkIn_CSPage.get(0));
            String csComment = new RandomString().randomstring(6);
            courseStreamPage.commentBox.get(0).sendKeys(csComment + Keys.RETURN);

            ReportUtil.log("Login as student", "Login as student for updateRecentActivityAndStatistics method", "info");
            new LoginUsingLTI().ltiLogin("43_1"); //login as student
            new Assignment().submitAssignmentAsStudent(43); //submit assignment
            ReportUtil.log("submit Assignment", "student submitted assignment", "pass");

            new Navigator().NavigateTo("Dashboard");
            ReportUtil.log("Navigate to Dashboard", "Navigated to Dashboard successfully", "Pass");

            String courseName=dashboard.course_title.getText().trim();
            CustomAssert.assertEquals(courseName,"Biology","Verify Course Name","Course name'"+courseName+"' is  present in the student dashboard","Course name'"+courseName+"' is not present in the student dashboard");

            String updatedRecentActivity=dashboard.assessmentLink.get(0).getText().trim();
            if(!updatedRecentActivity.trim().equals(assessmentName)){
                CustomAssert.fail("Verify Assignment Name", "Assignment Name is not displaying in dashboard");
            }

            ReportUtil.log("Login as student", "Login as student for updateRecentActivityAndStatistics method", "info");
            new LoginUsingLTI().ltiLogin("43_1"); //login as student
            String time = dashboard.timeSpent.getText().trim();//Time Spent
            System.out.println("Average Time:"+time);
            if(time.equals(" ")||time==null) {
                CustomAssert.fail("Verify Time spent","The average time spent tile in student dashboard is not producing the accurate result.");
            }
            else{
                ReportUtil.log("Verify Time spent", "The average time spent tile in student dashboard is  producing the accurate result.", "pass");
            }

            String questionPerformance=dashboard.questionPerformance.getText().trim();//Question performance
            System.out.println("questionPerformance:"+questionPerformance);
            if(questionPerformance.equals(" ")|| questionPerformance==null)
                CustomAssert.fail("Verify Question Performance","The Question performance tile in student dashboard is not updating");
            else
                ReportUtil.log("Verify Question Performance", "The Question performance tile in student dashboard is updating", "pass");

            String courseStreamAssignment=dashboard.assessment.getText().trim();
            if(!courseStreamAssignment.trim().equals(assessmentName)){
                CustomAssert.fail("Verify Assignment Name in course stream section", "Assignment Name is not displaying in course stream section");
            }

        } catch (Exception e) {
            Assert.fail("Exception in TC updateRecentActivityAndStatistics of class StudentDashboardTilesUpdate",e);
        }
    }
    @Test(priority = 2,enabled = true)
    public void updateCourseStream()
    {
        try {

            ReportUtil.log("Login as student", "Login as student for updateCourseStream method", "info");
            new LoginUsingLTI().ltiLogin("44"); //login as student
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream

            new PostMessage().postmessage("hi this new post");
            new PostMessage().postlink("www.google.com");
            new FileUpload().fileUpload("44", true);
            ReportUtil.log("Verify Entry", "Link ,File and Discussion posted student successfully", "pass");

            new Navigator().NavigateTo("Dashboard");
            String updatedFile=dashboard.fileUpload_entry.getText().trim();
            System.out.println("updatedFile:"+updatedFile);
            if(!updatedFile.contains("img.png"))
                CustomAssert.fail("Verify file entry","file is not updating in student dashboard");

            String updatedLinkText=dashboard.entry_courseStreamSection.get(0).getText().trim();
            System.out.println("updatedLinkText:"+updatedLinkText);
            if(!updatedLinkText.contains("www.google.com"))
                CustomAssert.fail("Verify link entry","Link is not updating in student dashboard");

            String updatedPost=dashboard.entry_courseStreamSection.get(1).getText().trim();
            System.out.println("updatedPost:"+updatedPost);
            if(!updatedPost.contains("hi this new post"))
                CustomAssert.fail("verify post entry","post is not updating in student dashboard");


        } catch (Exception e) {
            Assert.fail("Exception in TC updateCourseStream of class StudentDashboardTilesUpdate", e);
        }
    }

    @Test(priority=3,enabled = true)
    public void updateGradedAssignment()
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            Dashboard dashboard= PageFactory.initElements(driver,Dashboard.class);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "47");

            new LoginUsingLTI().ltiLogin("47_1");//login as student
            new Assignment().create(47); //create assignment
            ReportUtil.log("create assessment", "Assessment created  successfully", "info");

            ReportUtil.log("Login as instructor", "Login as instructor for updateGradedAssignment method", "pass");
            new LoginUsingLTI().ltiLogin("47");//login as instructor
            new Assignment().assignToStudent(47); //assign to the student
            ReportUtil.log("Assign Assignment", "Instructor assigned assignment  to complete class section", "pass");

            ReportUtil.log("Login as student", "Login as student for updateGradedAssignment method", "pass");
            new LoginUsingLTI().ltiLogin("47_1"); //login as student
            new Assignment().submitAssignmentAsStudent(47); //submit assignment
            ReportUtil.log("submit Assignment", "Instructor submit assignment  to complete class section", "pass");

            ReportUtil.log("Login as instructor", "Login as instructor for updateGradedAssignment method", "pass");
            new LoginUsingLTI().ltiLogin("47");//login as instructor
            new Assignment().provideGRadeToStudent(47);
            ReportUtil.log("Provide grade to Assignment", "Instructor provided grade successfully", "pass");

            new Assignment().clickViewResponse(assessmentName);
            new Assignment().releaseGrades(47, "Release Grade for All");//click on the release Grade
            ReportUtil.log("Release Grade for All", "Instructor Released grade successfully", "pass");

            ReportUtil.log("Login as student", "Login as student for updateGradedAssignment method", "pass");
            new LoginUsingLTI().ltiLogin("47_1");//login as student
            new Navigator().NavigateTo("Dashboard");

            new UIElement().waitAndFindElement(By.id("bar-chart"));
            dashboard.getGradedBarChart().click();
            String currentUrlForChart=driver.getCurrentUrl();
            System.out.println("currentUrlForChart:"+currentUrlForChart);
            if(!currentUrlForChart.contains("/studentAssignment"))
                CustomAssert.fail("Verify overall graded","Graph is not generate and student is not navigating to Student Assignment");
        }
        catch(Exception e)
        {
            Assert.fail("Exception in TC updateGrandAssignment of class StudentDashboardTilesUpdate",e );
        }
    }

    @Test(priority=4,enabled = true)
    public void upcomingSectionUpdate()
    {
        try
        {
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", "45");

            new Assignment().create(45); //create assignment
            ReportUtil.log("create assessment", "Assessment created  successfully", "info");

            ReportUtil.log("Login as instructor", "Login as instructor for upcomingSectionUpdate method", "pass");
            new LoginUsingLTI().ltiLogin("45");//login as instructor
            new Assignment().assignToStudent(45); //assign to the student
            ReportUtil.log("Assign Assignment", "Instructor assigned assignment to complete class section", "pass");

            ReportUtil.log("Login as student", "Login as student for updateRecentActivityAndStatistics method", "info");
            new LoginUsingLTI().ltiLogin("45_1");//login as student
            new Navigator().NavigateTo("Dashboard");

            String upcomingText=dashboard.upcoming_Text.getText().trim();
            CustomAssert.assertEquals(upcomingText,"Upcoming","Verify Upcoming text","Upcoming Text is visible","Upcoming Text is  not visible");
            String assignment=dashboard.upcoming_assignment.getText().trim();
            CustomAssert.assertEquals(assignment,assessmentName,"Verify Assignment Entry","Assignment name is displaying in upcoming section","Assignment name is not displaying in upcoming section");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in TC updateGrandAssignment of class StudentDashboardTilesUpdate",e );
        }
    }

    @Test(priority = 5,enabled = true)
    public void participatingRating()
    {
        WebDriver driver=Driver.getWebDriver();
        try {

            ReportUtil.log("Login as student", "Login as student for participatingRating method", "pass");
            new LoginUsingLTI().ltiLogin("46"); //login as student
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            String postMessage = new RandomString().randomstring(6);
            new PostMessage().postmessage(postMessage);
            String comment = new RandomString().randomstring(6);
            new CommentOnPost().commentOnPost(comment, 0);
            comment = new RandomString().randomstring(6);
            new CommentOnPost().commentOnPost(comment, 0);
            comment = new RandomString().randomstring(6);
            new CommentOnPost().commentOnPost(comment, 0);
            comment = new RandomString().randomstring(6);
            new CommentOnPost().commentOnPost(comment, 0);

            new RunScheduledJobs().runScheduledJobsForDashboard();
            driver=new ReInitDriver().startDriver("firefox");
            dashboard = PageFactory.initElements(driver, Dashboard.class);
            ReportUtil.log("Login as student", "Login as student for participatingRating method", "pass");
            new LoginUsingLTI().ltiLogin("46"); //login as student
            new Navigator().NavigateTo("Dashboard");
            String avgParticipatingRation=dashboard.averageParticipationRating.getText().trim();
            CustomAssert.assertEquals(avgParticipatingRation,"50\n%","Verify Average participating rate","Average participating rate is  generating","Average participating rate is not generating");

        } catch (Exception e) {
            Assert.fail("Exception in TC participatingRating of class StudentDashboardTilesUpdate ",e);
        }
    }


}
