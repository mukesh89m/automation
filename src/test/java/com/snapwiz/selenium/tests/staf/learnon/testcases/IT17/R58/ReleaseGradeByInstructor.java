package com.snapwiz.selenium.tests.staf.learnon.testcases.IT17.R58;
/*
 * Created by Sumit on 9/22/2414.
 */

import com.snapwiz.selenium.tests.staf.learnon.Driver;
import com.snapwiz.selenium.tests.staf.learnon.ReadTestData;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.TextFetch;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ReleaseGradeByInstructor {

    @Test
    public void releaseGradeByInstructor()
    {
        try
        {
            Driver.startDriver();
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "22");
            String text;
            String status;
            new Assignment().create(22);
            new Assignment().addQuestions(22, "multiplechoice", "");
            new Assignment().addQuestions(22, "multipleselection", "");

            new LoginUsingLTI().ltiLogin("24"); //log in as student
            new LoginUsingLTI().ltiLogin("22"); //log in as instructor
            new Assignment().assignToStudent(22);//assign to student

            new LoginUsingLTI().ltiLogin("24"); //log in as student
            new Assignment().submitAssignmentAsStudent(22);//submit the assignment

            new LoginUsingLTI().ltiLogin("22"); //log in as instructor
            new Navigator().NavigateTo("Summary");
            status = new TextFetch().textfetchbyclass("ls-assignment-status-in-red-grading-in-progress");
            Assert.assertEquals(status , "Needs Marking", "Status of assignment does not changes to \"Needs Marking\" in Summary page after student submits the assignment.");

            new Assignment().clickViewResponse(assessmentname);
            status = new TextFetch().textfetchbycssselector("span[class='ls-assignment-status-grading-in-progress']");
            Assert.assertEquals(status , "Needs Marking", "Status of assignment does not changes to \"Needs Marking\" in Grade book page after student submits the assignment.");

            new Navigator().NavigateTo("Course");//go to Course
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Assignments");//navigate to Assignments tab
            status = new TextFetch().textfetchbyclass("assessmentStatus");
            Assert.assertEquals(status , "Needs Marking", "Status of assignment does not changes to \"Needs Marking\" in Assignment tab after student submits the assignment.");

            new Assignment().releaseGrades(22, "Release Marks for All");//click on Release Marks for All
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            status = new TextFetch().textfetchbyclass("ls-assignment-status-grades-released");
            Assert.assertEquals(status , "Marked", "Status of assignment does not changes to \"Marked\" after Mark is Released by the instructor.");

            new Assignment().clickViewResponse(assessmentname);
            String status1 = new TextFetch().textfetchbylistclass("ls-assignment-status-grades-released", 1);
            Assert.assertEquals(status1 , "Marked", "Status of assignment does not changes to \"Marked\" in Grade book page after Mark is Released by the instructor.");

            new Navigator().NavigateTo("Course");//go to Course
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Assignments");//navigate to Assignments tab
            text = new TextFetch().textfetchbyclass("assessmentStatus");
            Assert.assertEquals(text , "Marked", "Status of assignment does not changes to \"Marked\" in Assignment tab after Mark is Released by the instructor..");

            new Navigator().NavigateTo("Dashboard");//go to Dashboard
            text = new TextFetch().textfetchbyid("bar-chart");
            if(!text.contains("Recently Marked"))
                Assert.fail("In instructor side, \"Recently Marked\" label is absent Assignment Tile of Dashboard.");

            if(!text.contains("Marks (%)"))
                Assert.fail("In instructor side, \"Marks (%)\" label is absent along the x-axis of bar graph in Assignment Tile of Dashboard.");

            text = new TextFetch().textfetchbylistclass("ls-assignment-progress-text", 3);
            if(!text.contains("Marked/ Reviewed"))
                Assert.fail("In instructor side, \"Marked/ Reviewed\" status text is absent in 3rd section of Assignment Tile.");

            new LoginUsingLTI().ltiLogin("24"); //log in as student
            text = new TextFetch().textfetchbyid("donut-chart");
            if(!text.contains("Overall Marks"))
                Assert.fail("In student side, \"Overall Marks\" label in donut chart is absent Assignment Tile of Dashboard.");
            text = new TextFetch().textfetchbyid("bar-chart");
            if(!text.contains("Recently Marked"))
                Assert.fail("In student side, \"Recently Marked\" label bar graph is absent Assignment Tile of Dashboard.");

            new Assignment().openAssignmentFromCourseStream("22");
            text = new TextFetch().textfetchbyid("performance-bar-chart-wrapper");
            if(!text.contains("Marks"))
                Assert.fail("\"Marks\" is not displayed along Y-axis in report graph of static assignment.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in TC releaseGradeByInstructor in class ReleaseGradeByInstructor.", e);
        }
    }
    @AfterMethod
    public void tearDown() throws Exception
    {
        Driver.driver.quit();
    }
}
