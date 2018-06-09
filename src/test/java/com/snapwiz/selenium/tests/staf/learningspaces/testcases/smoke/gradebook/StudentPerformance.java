package com.snapwiz.selenium.tests.staf.learningspaces.testcases.smoke.gradebook;

        import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
        import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
        import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
        import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
        import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
        import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
        import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.*;
        import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
        import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Gradebook.Gradebook;
        import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.MyActivity.MyActivity;
        import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.AssignmentTab;
        import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
        import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
        import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.ProficiencyReport;
        import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
        import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ScrollElement;
        import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
        import org.openqa.selenium.By;
        import org.openqa.selenium.JavascriptExecutor;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.PageFactory;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.support.ui.WebDriverWait;
        import org.testng.Assert;
        import org.testng.annotations.BeforeMethod;
        import org.testng.annotations.Test;
        import java.text.DateFormat;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Calendar;
        import java.util.List;

/**
 * Created by Priyanka on 28-06-2016.
 */
public class StudentPerformance extends Driver {

    WebDriver driver;
    AssignmentResponsesPage assignmentResponsesPage;
    AssessmentResponses assessmentResponses;
    CurrentAssignments currentAssignments;
    ProficiencyReport proficiencyReport;
    CourseStreamPage courseStream;
    NewAssignment newAssignment;
    AssignmentTab assignmentTab;
    QuestionBank questionBank;
    QuestionPage questionPage;
    Assignments assignments;
    LessonPage lessonPage;
    MyActivity myActivity;
    Dashboard dashBoard;
    Gradebook gradebook;
    String actual = "";
    String expected = "";

    @BeforeMethod
    public void inItElement() {
        driver = Driver.getWebDriver();
        assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
        assessmentResponses = PageFactory.initElements(driver, AssessmentResponses.class);
        currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
        proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);
        courseStream = PageFactory.initElements(driver, CourseStreamPage.class);
        newAssignment = PageFactory.initElements(driver, NewAssignment.class);
        assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
        questionPage = PageFactory.initElements(driver, QuestionPage.class);
        questionBank = PageFactory.initElements(driver, QuestionBank.class);
        assignments = PageFactory.initElements(driver, Assignments.class);
        lessonPage = PageFactory.initElements(driver, LessonPage.class);
        myActivity = PageFactory.initElements(driver, MyActivity.class);
        dashBoard = PageFactory.initElements(driver, Dashboard.class);
        gradebook = PageFactory.initElements(driver, Gradebook.class);

    }

    @Test(priority = 1, enabled = true)
    public void instructorCanViewTheTextOnStudentPerformanceTab() {
        try {

            ReportUtil.log("Description", "Test case validates instructor can see the text on student performance tab", "info");

            new LoginUsingLTI().ltiLogin("2");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");
            navigateToGradeBook();
            gradebook.studentPerformanceTab.click();//click on student performance tab

            List<WebElement> gradeBookMessage = gradebook.gradeBookMessage;
            for (WebElement message : gradeBookMessage) {
                if (message.isDisplayed()) {
                    actual = message.getText();
                }
            }
            expected = "It seems that grades have not been released for any gradable assignment yet.\nPlease check back after grades are released for at least one gradable assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify message in student performance tab", "Message is as per expected", "Message is not as per expected");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorCanViewTheTextOnStudentPerformanceTab in class StudentPerformance", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void instructorCanViewLastViewedStateOnPerformanceTab() {
        try {

            ReportUtil.log("Description", "Test case validates instructor can see the last viewed state on assignment performance tab", "info");

            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            navigateToGradeBook();
            actual = gradebook.activeTab.getAttribute("title");
            expected = "Assignment Performance";
            CustomAssert.assertEquals(actual, expected, "Verify assignment performance tab", " Assignment Performance tab is loaded by default.", "Assignment Performance tab is not loaded by default.");
            gradebook.gradeRangeSelector.get(0).click();//click on 0%-59% grade range selector
            CustomAssert.assertTrue(gradebook.zeroToFiftyNineSelectedGrade.isDisplayed(), "Verify selected grade range i.e 0%-59%", "0%-59% grade range is selected", "0%-59% grade range is not selected");
            gradebook.studentPerformanceTab.click();//click on student performance tab
            actual = gradebook.tabHeader.get(2).getText();
            expected = "Student Performance on Assignments";
            CustomAssert.assertEquals(actual, expected, "Verify student performance tab header", "Student performance header is as per expected", "Student performance header is not as per expected");
            new MouseHover().mouserhoverbywebelement(gradebook.coloredMarker.get(1));
            actual = gradebook.student_PerformanceBox.get(5).getText();
            expected = "d, month\n" +
                    "\n" +
                    "Overall Score: 86%\n" +
                    "Average Time Spent: 0 min(s)";
            CustomAssert.assertEquals(actual, expected, "Verify details of student after mouse hover on green dot", "Student details is as per expected", "Student details is not as per expected");
            gradebook.coloredMarker.get(1).click();//click on green color dot
            CustomAssert.assertTrue(gradebook.backButton.isDisplayed(), "Verify the drill down assignment report ", "user is navigated to the drill down assignment report ", "user is not navigated to the drill down assignment report ");
            gradebook.backButton.click();//click on back button

            new MouseHover().mouserhoverbywebelement(gradebook.coloredMarker.get(2));
            actual = gradebook.student_PerformanceBox.get(5).getText();
            expected = "c, month\n" +
                    "\n" +
                    "Overall Score: 19%\n" +
                    "Average Time Spent: 0 min(s)";
            CustomAssert.assertEquals(actual, expected, "Verify details of student after mouse hover on red dot", "Student details is as per expected", "Student details is not as per expected");

            new MouseHover().mouserhoverbywebelement(gradebook.coloredMarker.get(0));
            actual = gradebook.student_PerformanceBox.get(5).getText();
            expected = "e, month\n" +
                    "\n" +
                    "Overall Score: 61%\n" +
                    "Average Time Spent: 0 min(s)";
            CustomAssert.assertEquals(actual, expected, "Verify details of student after mouse hover on yellow dot", "Student details is as per expected", "Student details is not as per expected");
            WebDriverUtil.clickOnElementUsingJavascript(gradebook.assignmentPerformanceTab);//click on assignment performance tab
            CustomAssert.assertTrue(true, "Verify selected grade range i.e 0%-59%", "0%-59% grade range is selected", "Last viewed state is reset to default view");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorCanViewLastViewedStateOnPerformanceTab in class StudentPerformance", e);
        }
    }


    @Test(priority = 3, enabled = true)
    public void instructorCanViewTable1BelowTheScatterPlot() {
        try {

            ReportUtil.log("Description", "Test case validates instructor can see the table 1 below the scatter plot on studentl performance tab", "info");

            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            navigateToGradeBook();
            gradebook.studentPerformanceTab.click();//click on student performance tab
            actual = gradebook.tabHeader.get(3).getText();
            expected = "Metrics by Student";
            CustomAssert.assertEquals(actual, expected, "Verify the Label of the table", "Label of the table is as per expected", "Label of the table is not as per expected");

            //Verification of 'NAME' column
            actual = gradebook.tableHeaders.get(0).getText().trim();
            expected = "Name";
            CustomAssert.assertEquals(actual, expected, "Verify the 1st column header in the table", "1st column header is as per expected", "1st column header is not as per expected");
            WebDriverUtil.scrollIntoView(gradebook.studentsName.get(0),false);
            Thread.sleep(2000);
            WebDriverUtil.clickOnElementUsingJavascript(gradebook.studentsName.get(0));//click on 1st student name
            CustomAssert.assertTrue(gradebook.backButton.isDisplayed(), "Verify the drill down assignment report ", "user is navigated to the drill down assignment report ", "user is not navigated to the drill down assignment report ");
            gradebook.backButton.click();//click on back button

            List<String> student = new ArrayList<String>();

            List<String> student1 = new ArrayList<String>();
            student1.add("a, month");
            student1.add("b, month");
            student1.add("c, month");
            student1.add("d, month");
            student1.add("e, month");
            List<WebElement> studentName = gradebook.studentsName;
            for (WebElement we : studentName) {
                student.add(we.getText());
            }

            if (!student.equals(student1)) {
                CustomAssert.fail("Verify student name", "student name is not in ascending order");
            }

            new MouseHover().mouserhoverbywebelement(gradebook.ascending);//mouse hover on sort in "Name" header
            actual = gradebook.ascending.getAttribute("title");
            expected = "Ascending";
            CustomAssert.assertEquals(actual, expected, "Verify tool tip", "Ascending is displaying in tool tip", "Ascending is not displaying in tool tip");

            //Verification of 'Assignments Finished' column
            actual = gradebook.tableHeaders.get(1).getText().trim();
            expected = "Assignments Finished";
            CustomAssert.assertEquals(actual, expected, "Verify the 2nd column header in the table", "2nd column header is as per expected", "2nd column header is not as per expected");

            actual = gradebook.finishedPercentage.get(0).getText();
            String actual1 = actual.substring(0, 4);
            expected = "14 %";//((2/14)%100)
            /*Calculation :- <x> % where x = ( a / b) *100 ,
            where a = total number of assignments finished by the student by clicking on the “Yes , Finish now”  link in the robo-notification after clicking on the “Finish Assignment” button at the last question of any assignment (not auto-submitted by the product) and
            b = total number of all the assignments assigned to that student in that class-section.*/
            CustomAssert.assertEquals(actual1, expected, "Verify assignment finished percentage", "Assignment finished percentage is not as per expected", "Assignment finished percentage is not as per expected");

            gradebook.helpIcon.get(0).click();//click on help icon besides assignment finished
            actual = gradebook.getHelpNotification().getText();
            expected = "This shows the total number of graded assignments finished by each students on their own.";
            CustomAssert.assertEquals(actual, expected, "Verify help text", "Help text is as per expected", "Help text is not as per expected");

            new MouseHover().mouserhoverbywebelement(gradebook.sortIcon.get(6));
            actual = gradebook.sortIcon.get(6).getAttribute("title");
            expected = "Sort";
            CustomAssert.assertEquals(actual, expected, "Verify sort icon besides the assignment finished", "Sort icon is displayed", "Sort icon is not displayed");
            Thread.sleep(1000);

            gradebook.sortIcon.get(6).click();//click on sort icon besides assignment finished

            List<String> assignmentFinished1 = new ArrayList<String>();
            assignmentFinished1.add("7%");
            assignmentFinished1.add("14%");
            assignmentFinished1.add("14%");
            assignmentFinished1.add("14%");
            assignmentFinished1.add("21%");
            List<WebElement> assignmentScore = gradebook.finishedPercentage;
            boolean mismatch = false;
            for (int i = 0; i < assignmentScore.size(); i++) {
                if (assignmentFinished1.get(i).equals(assignmentScore.get(i).getText().substring(0, 4).replace(" %", "%"))) {
                    mismatch = true;
                } else {
                    mismatch = false;
                }
            }

            Assert.assertEquals(mismatch, true);

            new MouseHover().mouserhoverbywebelement(gradebook.ascending);//mouse hover on sort in "Name" header
            actual = gradebook.ascending.getAttribute("title");
            expected = "Ascending";
            CustomAssert.assertEquals(actual, expected, "Verify tool tip", "Ascending is displaying in tool tip", "Ascending is not displaying in tool tip");

            //Verification of 'Average Time Spent' column
            actual = gradebook.tableHeaders.get(2).getText().trim();
            expected = "Average Time Spent";
            CustomAssert.assertEquals(actual, expected, "Verify the 3rd column header in the table", "3rd column header is as per expected", "3rd column header is not as per expected");

            gradebook.helpIcon.get(1).click();//click on help icon besides average time spent
            actual = gradebook.getHelpNotification().getText();
            expected = "This shows the average time spent by each student on all assigned assignments.";
            CustomAssert.assertEquals(actual, expected, "Verify help text", "Help text is as per expected", "Help text is not as per expected");

            new MouseHover().mouserhoverbywebelement(gradebook.sortIcon.get(7));
            actual = gradebook.sortIcon.get(8).getAttribute("title");
            expected = "Sort";
            CustomAssert.assertEquals(actual, expected, "Verify sort icon besides the average time spent", "Sort icon is displayed", "Sort icon is not displayed");

            actual = gradebook.avgTimeSpent.get(0).getText().trim();
            expected = "0 min(s)";
            CustomAssert.assertEquals(actual, expected, "Verify average time spent", "Average time spent is as  per expected", "Average time spent is not as  per expected");

            gradebook.sortIcon.get(8).click();//click on sort icon besides average time spent

            new MouseHover().mouserhoverbywebelement(gradebook.ascending);//mouse hover on sort in "Name" header
            actual = gradebook.ascending.getAttribute("title");
            expected = "Ascending";
            CustomAssert.assertEquals(actual, expected, "Verify tool tip", "Ascending is displaying in tool tip", "Ascending is not displaying in tool tip");

            //Verification of 'Overall Score' column
            actual = gradebook.tableHeaders.get(3).getText().trim();
            expected = "Overall Score";
            CustomAssert.assertEquals(actual, expected, "Verify the 4th column header in the table", "4th column header is as per expected", "4th column header is not as per expected");

            gradebook.helpIcon.get(2).click();//click on help icon besides overall score
            actual = gradebook.getHelpNotification().getText();
            expected = "Overall score for a student shows the average performance for ALL gradable assignments assigned to him/her.";
            CustomAssert.assertEquals(actual, expected, "Verify help text", "Help text is as per expected", "Help text is not as per expected");

            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(gradebook.sortIcon.get(8));
            actual = gradebook.sortIcon.get(9).getAttribute("title");
            expected = "Sort";
            CustomAssert.assertEquals(actual, expected, "Verify sort icon besides the overall score", "Sort icon is displayed", "Sort icon is not displayed");

            gradebook.sortIcon.get(9).click();//click on sort icon besides overall score

            List<String> overAllScore1 = new ArrayList<String>();
            overAllScore1.add("14%");//(((50+75+88)/1400)%100)=15%
            overAllScore1.add("15%");
            overAllScore1.add("19%");
            overAllScore1.add("61%");
            overAllScore1.add("86%");
            List<WebElement> overAllScores = gradebook.overAllScorePercentage;
            boolean scoreMismatch = false;
            for (int i = 0; i < overAllScores.size(); i++) {
                if (overAllScore1.get(i).equals(overAllScores.get(i).getText())) {
                    scoreMismatch = true;
                } else {
                    scoreMismatch = false;
                }
            }

            Assert.assertEquals(scoreMismatch, true);

            new MouseHover().mouserhoverbywebelement(gradebook.ascending);//mouse hover on sort in "Name" header
            actual = gradebook.ascending.getAttribute("title");
            expected = "Ascending";
            CustomAssert.assertEquals(actual, expected, "Verify tool tip", "Ascending is displaying in tool tip", "Ascending is not displaying in tool tip");

            //Verification of overall score at student dashboard
            new LoginUsingLTI().ltiLogin("1_1");//log in as student
            ReportUtil.log("Student login", "Student logged in successfully", "pass");
            String overallScore = proficiencyReport.courseProficiencys.get(1).getText().trim();
            String overallPercentage = proficiencyReport.courseProficiencys.get(2).getText().trim();

            actual = overallScore + overallPercentage;
            expected = "15%";
            CustomAssert.assertEquals(actual, expected, "Verify overall score in dashboard", "overall score is displaying as " + expected + "", "overall score is not displaying as " + expected + "");

            //Verification of overall score at student Assignment page
            new Navigator().NavigateTo("Assignments");
            ReportUtil.log("Navigate to assignment page", "Student navigated to assignment page successfully", "pass");
            CustomAssert.assertEquals(assignments.overAllScore.getText(), "Overall Score: 15%", "Verify overall score", "The Percentage in the Overall Score tab should be updated accordingly", "Overall score is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorCanViewTable1BelowTheScatterPlot in class StudentPerformance", e);
        }
    }


    @Test(priority = 4, enabled = true)
    public void instructorCanViewDrillDownDetailsInStudentPerformance() {
        try {

            ReportUtil.log("Description", "Test case validates instructor can see the drill down details in student performance tab", "info");

            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");
            navigateToGradeBook();
            gradebook.studentPerformanceTab.click();//click on student performance tab
            new MouseHover().mouserhoverbywebelement(gradebook.coloredMarker.get(1));
            gradebook.coloredMarker.get(1).click();//click on dot on scatter plot
            actual = gradebook.userThumbnail.getAttribute("src").substring(22);
            expected = "/webresources/images/instructor/user-profile.png";
            CustomAssert.assertEquals(actual, expected, "Verify user thumbnail", "User thubnail is available", "User thubnail is not available");

            actual = gradebook.tabHeader.get(2).getText();
            expected = "Student: d, month";
            CustomAssert.assertEquals(actual, expected, "Verify student performance tab header", "Student performance header is as per expected", "Student performance header is not as per expected");
            CustomAssert.assertEquals(gradebook.gradeLabel.getCssValue("text-align"), "left", "Verify Grade% graph", "Grade% graph is displayed", "Grade% graph is not displayed");

            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(gradebook.assignment_barEntry.get(26));
            Thread.sleep(1000);
            actual = gradebook.dotBox.get(2).getText().trim();
            DateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
            Calendar now = Calendar.getInstance();
            String calenderFormat = dateFormat.format(now.getTime());
            System.out.println("calenderFormat:" + calenderFormat);
            expected = "12GradableAssignment\n" +
                    "Grade: 100%\n" +
                    "Due Date: " + calenderFormat;
            CustomAssert.assertEquals(actual, expected, "Verify Bar Graph Value OnMouseHover on assignment bar", " Bar Graph Value OnMouseHover on assignment bar is " + expected + "", " Bar Graph Value OnMouseHover on assignment bar is not " + expected + "");

            new MouseHover().mouserhoverbywebelement(gradebook.studentAssignmentPerformance_link.get(0));
            actual = gradebook.studentAssignmentPerformance_link.get(0).getText();
            expected = "disc";
            CustomAssert.assertEquals(actual, expected, "Verify First four characters of the Assignment name", "First four characters of the Assignment name is displayed.", "First four characters of the Assignment name is not displayed.");

            actual = gradebook.studentAssignmentPerformance_link.get(0).getAttribute("title").trim();
            expected = "discussionAssignment_AssignmentPerformance_3";
            CustomAssert.assertEquals(actual, expected, "Verify student Performance on Assignments Name on mouse hover", "On hover ,the complete assignment name is displaying on tooltip.", "On hover ,the complete assignment name is not displaying on tooltip.");

            new MouseHover().mouserhoverbywebelement(gradebook.assignment_barEntry.get(26));//Mouse hover on the average time spent
            actual = gradebook.assignment_barEntry.get(2).getAttribute("fill").trim();
            expected = "#6bb45f"; //green
            CustomAssert.assertEquals(actual, expected, "Verify (80-100) range color", "(80-100) range color is " + expected + "", "(80-100) range color is not " + expected + "");
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(gradebook.studentAssignmentPerformance_link.get(0));
            gradebook.studentAssignmentPerformance_link.get(0).click();//click on assignment label below x-axis

            Thread.sleep(1000);
            CustomAssert.assertEquals(assignmentResponsesPage.getPageTitle().getText(), "Assignment Responses", "Verify “Assignment Response", "It is navigate to the “Assignment Response", "It is not navigate to the “Assignment Response”");
            assignmentResponsesPage.getBack_Button().click();//click on back button
            actual = gradebook.tabHeader.get(0).getText();
            expected = "Student: d, month";
            CustomAssert.assertEquals(actual, expected, "Verify student performance tab header", "Student performance header is as per expected", "Student performance header is not as per expected");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorCanViewDrillDownDetailsInStudentPerformance in class StudentPerformance", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void VerifyTimeSpentGraph() {
        try {

            ReportUtil.log("Description", "Test case validates time spent graph in student performance tab", "info");

            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");
            navigateToGradeBook();
            gradebook.studentPerformanceTab.click();//click on student performance tab
            new MouseHover().mouserhoverbywebelement(gradebook.coloredMarker.get(1));
            gradebook.coloredMarker.get(1).click();//click on dot on scatter plot
            CustomAssert.assertEquals(gradebook.timeSpent_label.getCssValue("text-align"), "left", "Verify Grade% graph", "Grade% graph is displayed", "Grade% graph is not displayed");

            new MouseHover().mouserhoverbywebelement(gradebook.studentAssignmentPerformance_link.get(12));
            actual = gradebook.studentAssignmentPerformance_link.get(12).getText();
            expected = "disc";
            CustomAssert.assertEquals(actual, expected, "Verify First four characters of the Assignment name", "First four characters of the Assignment name is displayed.", "First four characters of the Assignment name is not displayed.");

            actual = gradebook.studentAssignmentPerformance_link.get(12).getAttribute("title").trim();
            expected = "discussionAssignment_AssignmentPerformance_3";
            CustomAssert.assertEquals(actual, expected, "Verify student Performance on Assignments Name on mouse hover", "On hover ,the complete assignment name is displaying on tooltip.", "On hover ,the complete assignment name is not displaying on tooltip.");

            WebDriverUtil.clickOnElementUsingJavascript(gradebook.nextArrow.get(1));
            new MouseHover().mouserhoverbywebelement(gradebook.assignment_barEntry.get(27));//Mouse hover on the time spent
            actual = gradebook.assignment_barEntry.get(27).getAttribute("fill").trim();
            expected = "rgb(126, 180, 212)"; //blue
            CustomAssert.assertEquals(actual, expected, "Verify Boundary colour of the tooltip should be  the same as of the bar graph", "Boundary colour of the tooltip is same as of the bar graph.", "Boundary colour of the tooltip is not same as of the bar graph.");

            DateFormat dateFormat = new SimpleDateFormat("MMM d, yyyy");
            Calendar now = Calendar.getInstance();
            String calenderFormat = dateFormat.format(now.getTime());
            System.out.println("calenderFormat:" + calenderFormat);
            actual = gradebook.dotBox.get(3).getText();
            expected = "AssignmentPerformance_1\n" +
                    "Time Spent: 1 min(s)\n" +
                    "Due Date: " + calenderFormat;
            CustomAssert.assertEquals(actual, expected, "Verify details of student after mouse hover in time spent bar", "Assignment details on time spent bar is as per expected", "Assignment details on time spent bar is not as per expected");

            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(gradebook.studentAssignmentPerformance_link.get(3));
            gradebook.studentAssignmentPerformance_link.get(3).click();//click on assignment label below x-axis

            Thread.sleep(1000);
            CustomAssert.assertEquals(assignmentResponsesPage.getPageTitle().getText(), "Assignment Responses", "Verify “Assignment Response", "It is navigate to the “Assignment Response", "It is not navigate to the “Assignment Response”");
            assignmentResponsesPage.getBack_Button().click();//click on back button
            actual = gradebook.tabHeader.get(0).getText();
            expected = "Student: d, month";
            CustomAssert.assertEquals(actual, expected, "Verify student performance tab header", "Student performance header is as per expected", "Student performance header is not as per expected");

        } catch (Exception e) {
            Assert.fail("Exception in test case VerifyTimeSpentGraph in class StudentPerformance", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void tableMetricsOnStudentAssignment() {
        try {

            ReportUtil.log("Description", "Test case validates student metrics table details", "info");

            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");
            navigateToGradeBook();
            gradebook.studentPerformanceTab.click();//click on student performance tab
            new MouseHover().mouserhoverbywebelement(gradebook.coloredMarker.get(1));
            gradebook.coloredMarker.get(1).click();//click on dot on scatter plot
            Thread.sleep(2000);
            actual = gradebook.tabHeader.get(3).getText();
            expected = "Metrics on Student's Assignment";
            CustomAssert.assertEquals(actual, expected, "Verify the Label of the table", "Label of the table is as per expected", "Label of the table is not as per expected");

            //Verification of 'Assignments' column
            actual = gradebook.tableHeaders.get(0).getText().trim();
            expected = "Assignments";
            CustomAssert.assertEquals(actual, expected, "Verify the 1st column header in the table", "2nd column header is as per expected", "2nd column header is not as per expected");

            WebDriverUtil.scrollIntoView(gradebook.assignment_Name.get(3), false);
            new MouseHover().mouserhoverbywebelement(gradebook.sortIcon.get(6));
            actual = gradebook.sortIcon.get(6).getAttribute("title");
            expected = "Sort";
            CustomAssert.assertEquals(actual, expected, "Verify sort icon besides the assignments", "Sort icon is displayed", "Sort icon is not displayed");
            Thread.sleep(1000);

            gradebook.sortIcon.get(6).click();//click on sort icon of 1st column

            List<String> assignment = new ArrayList<String>();

            List<String> assignment1 = new ArrayList<String>();
            assignment1.add("10GradableAssignment");
            assignment1.add("11GradableAssignment");
            assignment1.add("12GradableAssignment");
            assignment1.add("2GradableAssignment");
            assignment1.add("3GradableAssignment");
            List<WebElement> assignments = gradebook.assignment_Name;
            for (int i = 0; i <= assignments.size() - 10; i++) {
                assignment.add(assignments.get(i).getText());
            }

            if (!assignment.equals(assignment1)) {
                CustomAssert.fail("Verify assignment name", "Assignment name is not in ascending order");
            }
            new MouseHover().mouserhoverbywebelement(gradebook.ascending);//mouse hover on sort in "Name" header
            actual = gradebook.ascending.getAttribute("title");
            expected = "Ascending";
            CustomAssert.assertEquals(actual, expected, "Verify tool tip", "Ascending is displaying in tool tip", "Ascending is not displaying in tool tip");

            gradebook.ascending.click();//click on ascending icon

            List<String> asceAssignment = new ArrayList<String>();

            List<String> asceAssignment1 = new ArrayList<String>();
            asceAssignment1.add("discussionAssignment_AssignmentPerformance_3");
            asceAssignment1.add("FileBasedAssignment_AssignmentPerformance_2");
            asceAssignment1.add("AssignmentPerformance_1");
            asceAssignment1.add("9GradableAssignment");
            asceAssignment1.add("8GradableAssignment");
            List<WebElement> asceAssignments = gradebook.assignment_Name;
            for (int i = 0; i <= asceAssignments.size() - 11; i++) {
                asceAssignment.add(asceAssignments.get(i).getText());
            }

            if (!assignment.equals(assignment1)) {
                CustomAssert.fail("Verify assignment name", "Assignment name is not in descending order");
            }

            //Verification of 'Completion Level' column
            actual = gradebook.tableHeaders.get(1).getText().trim();
            expected = "Completion Level";
            CustomAssert.assertEquals(actual, expected, "Verify the 2nd column header in the table", "1st column header is as per expected", "1st column header is not as per expected");

            gradebook.helpIconStudentMetrics.get(0).click();//click on help icon available in 2nd column
            actual = gradebook.getHelpNotification().getText();
            expected = "This shows assignment submission status for each assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify help text", "Help text is as per expected", "Help text is not as per expected");

            new MouseHover().mouserhoverbywebelement(gradebook.sortIcon.get(7));
            actual = gradebook.sortIcon.get(7).getAttribute("title");
            expected = "Sort";
            CustomAssert.assertEquals(actual, expected, "Verify sort icon besides the assignments", "Sort icon is displayed", "Sort icon is not displayed");
            Thread.sleep(1000);

            gradebook.sortIcon.get(7).click();//click on sort icon of 1st column

            List<String> completionLevel = new ArrayList<String>();

            List<String> completionLevel1 = new ArrayList<String>();
            completionLevel1.add("Did Not Start");
            //completionLevel1.add("Did Not Finish");
            completionLevel1.add("Finished");
            List<WebElement> completionLevels = gradebook.completionLevel;
            for (int i = 11; i <= completionLevels.size()-2; i++) {

                WebDriverUtil.scrollIntoView(completionLevels.get(i), false);
                completionLevel.add(completionLevels.get(i).getText());
            }

            if (!completionLevel.equals(completionLevel1)) {
                CustomAssert.fail("Verify Completion level", "Completion level is not in ascending order");
            }

            new MouseHover().mouserhoverbywebelement(gradebook.ascending);//mouse hover on sort in "Name" header
            actual = gradebook.ascending.getAttribute("title");
            expected = "Ascending";
            CustomAssert.assertEquals(actual, expected, "Verify tool tip", "Ascending is displaying in tool tip", "Ascending is not displaying in tool tip");

            gradebook.ascending.click();//click on sort icon of 1st column

            List<String> ascecompletionLevel = new ArrayList<String>();

            List<String> ascecompletionLevel1 = new ArrayList<String>();
            ascecompletionLevel1.add("Finished");
            //ascecompletionLevel1.add("Did Not Finish");
            ascecompletionLevel1.add("Did Not Start");
            List<WebElement> ascecompletionLevels = gradebook.completionLevel;
            for (int i = 1; i <= ascecompletionLevels.size()-12; i++) {
                WebDriverUtil.scrollIntoView(ascecompletionLevels.get(i), false);
                ascecompletionLevel.add(ascecompletionLevels.get(i).getText());
            }

            if (!ascecompletionLevel.equals(ascecompletionLevel1)) {
                CustomAssert.fail("Verify Completion level", "Completion level is not in descending order");
            }

            //Verification of 'Time Spent' column
            actual = gradebook.tableHeaders.get(2).getText().trim();
            expected = "Time Spent";
            CustomAssert.assertEquals(actual, expected, "Verify the 3rd column header in the table", "3rd column header is as per expected", "3rd column header is not as per expected");

            gradebook.helpIconStudentMetrics.get(1).click();//click on help icon available in 3rd column
            actual = gradebook.getHelpNotification().getText();
            expected = "This shows the time spent on each assignment.";
            CustomAssert.assertEquals(actual, expected, "Verify help text", "Help text is as per expected", "Help text is not as per expected");

            new MouseHover().mouserhoverbywebelement(gradebook.sortIcon.get(8));
            actual = gradebook.sortIcon.get(8).getAttribute("title");
            expected = "Sort";
            CustomAssert.assertEquals(actual, expected, "Verify sort icon besides the assignments", "Sort icon is displayed", "Sort icon is not displayed");
            Thread.sleep(1000);

            gradebook.sortIcon.get(8).click();//click on sort icon of 1st column

            new MouseHover().mouserhoverbywebelement(gradebook.ascending);//mouse hover on sort in "Name" header
            actual = gradebook.ascending.getAttribute("title");
            expected = "Ascending";
            CustomAssert.assertEquals(actual, expected, "Verify tool tip", "Ascending is displaying in tool tip", "Ascending is not displaying in tool tip");

            //Verification of 'Average Time Spent' column
            actual = gradebook.tableHeaders.get(3).getText().trim();
            expected = "Average\n" +
                    "Time Spent";
            CustomAssert.assertEquals(actual, expected, "Verify the 4th column header in the table", "4th column header is as per expected", "4th column header is not as per expected");

            gradebook.helpIconStudentMetrics.get(2).click();//click on help icon available in 4th column
            actual = gradebook.getHelpNotification().getText();
            expected = "This shows the average time spent on each assignment across all the students assigned.";
            CustomAssert.assertEquals(actual, expected, "Verify help text", "Help text is as per expected", "Help text is not as per expected");

            new MouseHover().mouserhoverbywebelement(gradebook.sortIcon.get(9));
            actual = gradebook.sortIcon.get(9).getAttribute("title");
            expected = "Sort";
            CustomAssert.assertEquals(actual, expected, "Verify sort icon besides the assignments", "Sort icon is displayed", "Sort icon is not displayed");
            Thread.sleep(1000);

            gradebook.sortIcon.get(9).click();//click on sort icon of 4th column

            List<String> averageTimeSpent = new ArrayList<String>();

            List<String> averageTimeSpent1 = new ArrayList<String>();
            averageTimeSpent1.add("0 min(s)");
            averageTimeSpent1.add("1 min(s)");

            List<WebElement> averageTimeSpents = gradebook.averageTimeSpent;
            for (int i = 10; i <= averageTimeSpents.size() - 3; i++) {
                WebDriverUtil.scrollIntoView(averageTimeSpents.get(i), false);
                System.out.println("index" + averageTimeSpents.get(i).getText());
                averageTimeSpent.add(averageTimeSpents.get(i).getText());
                Thread.sleep(1000);
            }

            if (!averageTimeSpent.equals(averageTimeSpent1)) {
                CustomAssert.fail("Verify average Time Spent", "Average Time Spent is not in ascending order");
            }

            //Verification of 'Grade' column
            actual = gradebook.tableHeaders.get(4).getText().trim();
            expected = "Grade";
            CustomAssert.assertEquals(actual, expected, "Verify the 5th column header in the table", "5th column header is as per expected", "5th column header is not as per expected");

            gradebook.helpIconStudentMetrics.get(3).click();//click on help icon available in 5th column
            actual = gradebook.getHelpNotification().getText();
            expected = "This shows the grade for each assignment";
            CustomAssert.assertEquals(actual, expected, "Verify help text", "Help text is as per expected", "Help text is not as per expected");


            //Verification of 'Average Grade' column
            actual = gradebook.tableHeaders.get(5).getText().trim();
            expected = "Average\n" +
                    "Grade";
            CustomAssert.assertEquals(actual, expected, "Verify the 6th column header in the table", "6th column header is as per expected", "6th column header is not as per expected");

            gradebook.helpIconStudentMetrics.get(4).click();//click on help icon available in 6th column
            actual = gradebook.getHelpNotification().getText();
            expected = "This shows the average grade on each assignment across all the students assigned.";
            CustomAssert.assertEquals(actual, expected, "Verify help text", "Help text is as per expected", "Help text is not as per expected");

            new MouseHover().mouserhoverbywebelement(gradebook.sortIcon.get(11));
            actual = gradebook.sortIcon.get(11).getAttribute("title");
            expected = "Sort";
            CustomAssert.assertEquals(actual, expected, "Verify sort icon besides the assignments", "Sort icon is displayed", "Sort icon is not displayed");
            Thread.sleep(1000);

            gradebook.sortIcon.get(11).click();//click on sort icon of 1st column

            List<String> averageGrade = new ArrayList<String>();

            List<String> averageGrade1 = new ArrayList<String>();
            averageGrade1.add("56%");
            averageGrade1.add("58%");
            averageGrade1.add("75%");
            List<WebElement> averageGrades = gradebook.averageGrade;
            for (int i = 2; i <= averageGrades.size() - 10; i++) {
                WebDriverUtil.scrollIntoView(averageGrades.get(i), false);
                averageGrade.add(averageGrades.get(i).getText());
                Thread.sleep(1000);
            }

            if (!averageGrade.equals(averageGrade1)) {
                CustomAssert.fail("Verify average grade", "Average grade is not in ascending order");
            }

            //Verification of 'Not Applicable' status for file based and dw assignment in time spent and average time spent column

            actual = "Not Available";
            expected = gradebook.timeSpent.get(0).getText();
            CustomAssert.assertEquals(actual, expected, "Verify not applicable status", "Not applicable status is available in time spent column", "Not applicable status is not available in time spent column");


            actual = "Not Available";
            expected = gradebook.averageTimeSpent.get(0).getText();
            CustomAssert.assertEquals(actual, expected, "Verify not applicable status", "Not applicable status is available in average time spent column", "Not applicable status is not available in average time spent column");


        } catch (Exception e) {
            Assert.fail("Exception in test case tableMetricsOnStudentAssignment in class StudentPerformance", e);
        }
    }

    @Test(priority = 7, enabled = true)
    public void paginationMoreThanTwelve() {
        try {

            ReportUtil.log("Description", "Test case validates pagination", "info");

            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");
            navigateToGradeBook();
            gradebook.studentPerformanceTab.click();//click on student performance tab
            new MouseHover().mouserhoverbywebelement(gradebook.coloredMarker.get(1));
            gradebook.coloredMarker.get(1).click();//click on dot on scatter plot
            Thread.sleep(2000);
            actual = gradebook.nextChartIcon.getCssValue("right");
            System.out.println(actual);
            expected = "0px";
            CustomAssert.assertEquals(actual, expected, "Verify next pagination icon", "Next pagination icon is displayed", "Next pagination icon is not displayed");

            gradebook.nextChartIcon.click();//click on next chart arrow icon

            actual = gradebook.previousChartIcon.getCssValue("left");
            expected = "0px";
            CustomAssert.assertEquals(actual, expected, "Verify previous pagination icon", "Previous pagination icon is displayed", "Previous pagination icon is not displayed");

            gradebook.previousChartIcon.click();//click on next chart arrow icon

            actual = gradebook.nextChartIcon.getCssValue("right");
            System.out.println(actual);
            expected = "0px";
            CustomAssert.assertEquals(actual, expected, "Verify next pagination icon", "Next pagination icon is displayed", "Next pagination icon is not displayed");

        } catch (Exception e) {
            Assert.fail("Exception in test case paginationMoreThanTwelve in class StudentPerformance", e);
        }
    }

    @Test(priority = 8, enabled = true)
    public void enterGradeForStudentWhoHasNotStarted() {
        try {

            ReportUtil.log("Description", "Test case validates enter grade for student who has not started the assignment", "info");

            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");
            new Navigator().NavigateTo("Gradebook");
            ReportUtil.log("Navigate to grade book", "Instructor navigated to grade book page successfully", "pass");

            gradebook.studentPerformanceTab.click();//click on student performance tab
            new MouseHover().mouserhoverbywebelement(gradebook.coloredMarker.get(1));
            gradebook.coloredMarker.get(1).click();//click on dot on scatter plot
            Thread.sleep(2000);

            String scoreBeforeGrade = gradeBookStatusBoxCount("Grade", "11GradableAssignment");
            String valueBeforeGrade = gradeBookStatusBoxCount("AverageGrade", "11GradableAssignment");

            clickOnAssignment("11GradableAssignment");
            new Assignment().enterGradeOnParticularQuestion(3, 0, "0.5");
            new Assignment().enterGradeOnParticularQuestion(3, 1, "0.5");
            Thread.sleep(1000);
            WebDriverUtil.scrollIntoView(gradebook.goBackButton, true);
            WebDriverUtil.clickOnElementUsingJavascript(gradebook.goBackButton);//click on go back arrow
            Thread.sleep(1000);

            String scoreAfterGrade = gradeBookStatusBoxCount("Grade", "11GradableAssignment");
            String valueAfterGrade = gradeBookStatusBoxCount("AverageGrade", "11GradableAssignment");

            if (scoreBeforeGrade.equals(scoreAfterGrade)) {
                CustomAssert.fail("Verify average grade", "grade' for the particular assignment is changed in the table");
            }

            if (valueBeforeGrade.equals(valueAfterGrade)) {
                CustomAssert.fail("Verify average grade", "'Average grade' for the particular assignment is changed in the table");
            }


            clickOnAssignment("11GradableAssignment");
            new Assignment().enterGradeOnParticularQuestion(3, 0, "0");
            new Assignment().enterGradeOnParticularQuestion(3, 1, "0");
            new Assignment().enterGradeOnParticularQuestion(3, 2, "0");
            new Assignment().enterGradeOnParticularQuestion(3, 3, "0");

            Thread.sleep(1000);
            WebDriverUtil.scrollIntoView(gradebook.goBackButton, true);
            WebDriverUtil.clickOnElementUsingJavascript(gradebook.goBackButton);//click on go back arrow
            Thread.sleep(1000);

            String grade = gradeBookStatusBoxCount("Grade", "11GradableAssignment").replaceAll("[\n\r]", "").trim();
            String averageGrade = gradeBookStatusBoxCount("AverageGrade", "11GradableAssignment");
            if (!grade.equals("0%" + "0/4")) {
                CustomAssert.fail("Verify average grade", "grade' for the particular assignment is changed in the table");
            }

            if (valueAfterGrade.equals(averageGrade)) {
                CustomAssert.fail("Verify average grade", "'Average grade' for the particular assignment is changed in the table");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case enterGradeForStudentWhoHasNotStarted in class StudentPerformance", e);
        }
    }


    @Test(priority = 9, enabled = true)
    public void notApplicableImpacts() {
        try {
            ReportUtil.log("Description", "Test case validates 'NA' student performance in student performance tab", "info");

            new LoginUsingLTI().ltiLogin("9_1");//log in as student1
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("9_2");//log in as student2
            ReportUtil.log("Student2 login", "Student2 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("9_3");//log in as student3
            ReportUtil.log("Student3 login", "Student3 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("9");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Assignment().assignAssignmentWithDueDate(9);
            for (int i = 1; i < 2; i++) {
                new Assignment().assignCustomAssignmentFromMyQuestionBank(9, i);
            }
            Thread.sleep(120000);

            clickOnParticularVSRP("AssignmentPerformance_1");
            notApplicable(0);
            WebDriverUtil.clickOnElementUsingJavascript(assessmentResponses.resumeGrading_button);//click on yes
            Thread.sleep(1000);
            assignmentResponsesPage.getRefreshButton().click();
            Thread.sleep(1000);
            new Assignment().enterGradeOnParticularQuestion(0, 0, "1.0");
            new Assignment().enterGradeOnParticularQuestion(0, 1, "1.0");
            new Assignment().enterGradeOnParticularQuestion(0, 2, "1.0");

            new Assignment().enterGradeOnParticularQuestion(1, 0, "1.0");
            new Assignment().enterGradeOnParticularQuestion(1, 1, "1.0");
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());

            clickOnParticularVSRP("1GradableAssignment");
            WebDriverUtil.clickOnElementUsingJavascript(assessmentResponses.resumeGrading_button);//click on yes
            Thread.sleep(1000);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            Thread.sleep(1000);
            new Assignment().enterGradeOnParticularQuestion(0, 0, "1.0");
            new Assignment().enterGradeOnParticularQuestion(0, 1, "1.0");

            new Assignment().enterGradeOnParticularQuestion(1, 0, "1.0");
            new Assignment().enterGradeOnParticularQuestion(1, 1, "1.0");
            new Assignment().enterGradeOnParticularQuestion(1, 2, "1.0");

            new Assignment().enterGradeOnParticularQuestion(2, 0, "0.5");
            new Assignment().enterGradeOnParticularQuestion(2, 1, "0.5");
            new Assignment().enterGradeOnParticularQuestion(2, 2, "0.5");

            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());

            new Navigator().NavigateTo("Gradebook");
            ReportUtil.log("Navigate to grade book", "Instructor navigated to grade book page successfully", "pass");

            gradebook.studentPerformanceTab.click();//click on student performance tab
            new MouseHover().mouserhoverbywebelement(gradebook.coloredMarker.get(2));
            Thread.sleep(2000);
            gradebook.coloredMarker.get(2).click();//click on dot on scatter plot
            Thread.sleep(2000);

            actual = gradeBookStatusBoxCount("Completion Level", "AssignmentPerformance_1");
            expected = "Not Applicable";
            CustomAssert.assertEquals(actual, expected, "Verify completion level", "For NA student completion level is 'Not Applicable'", "For NA student completion level is not 'Not Applicable'");

            actual = gradeBookStatusBoxCount("Grade", "AssignmentPerformance_1");
            expected = "Not Applicable";
            CustomAssert.assertEquals(actual, expected, "Verify grade", "For NA student grade is 'Not Applicable'", "For NA student grade is not 'Not Applicable'");

            WebDriverUtil.clickOnElementUsingJavascript(gradebook.backButton);//click on back

            new MouseHover().mouserhoverbywebelement(gradebook.coloredMarker.get(0));
            Thread.sleep(1000);
            gradebook.coloredMarker.get(0).click();//click on dot on scatter plot
            Thread.sleep(2000);
            actual = gradeBookStatusBoxCount("AverageGrade", "AssignmentPerformance_1");
            expected = "50%";
            CustomAssert.assertEquals(actual, expected, "Verify Average grade", "For NA student Average grade is contribution is not added ", "For NA student Average grade contribution is added");

        } catch (Exception e) {
            Assert.fail("Exception in test case notApplicableImpacts in class StudentPerformance", e);
        }
    }

    @Test(priority = 10, enabled = true)
    public void moveStudentToAnotherClassSectionImpacts() {
        try {

            ReportUtil.log("Description", "Test case validates move student to another class section impacts in student performance tab", "info");

            new LoginUsingLTI().ltiLogin("9_4");//log in as student3 in other class section
            ReportUtil.log("Student3 login", "Student3 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("9");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Navigator().NavigateTo("Gradebook");
            ReportUtil.log("Navigate to grade book", "Instructor navigated to grade book page successfully", "pass");

            gradebook.studentPerformanceTab.click();//click on student performance tab


            if (gradebook.studentsName.size() > 2) {
                CustomAssert.fail("Verify moved student", "Moved student is still present in class section");
            }
            new MouseHover().mouserhoverbywebelement(gradebook.coloredMarker.get(0));
            Thread.sleep(2000);
            gradebook.coloredMarker.get(0).click();//click on dot on scatter plot
            Thread.sleep(2000);

            actual = gradeBookStatusBoxCount("AverageGrade", "1GradableAssignment");
            expected = "63%";
            CustomAssert.assertEquals(actual, expected, "Verify Average grade contribution of moved student", "In average grade moved student contribution is removed", "In average grade moved student contribution is  not removed");


        } catch (Exception e) {
            Assert.fail("Exception in test case notApplicableImpacts in class StudentPerformance", e);
        }
    }


    @Test(priority = 11, enabled = true)
    public void alteringScoreFromQuestionsResponsePage() {
        try {
            ReportUtil.log("Description", "Test case validates 'NA' student performance in student performance tab", "info");

            new LoginUsingLTI().ltiLogin("11_1");//log in as student1
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("11_2");//log in as student2
            ReportUtil.log("Student2 login", "Student2 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("11_3");//log in as student3
            ReportUtil.log("Student3 login", "Student3 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("11");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new Assignment().assignAssignmentWithDueDate(11);

            new LoginUsingLTI().ltiLogin("11_1");//log in as student1
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new Navigator().NavigateTo("Assignments");
            assignments.assignmentName.click();
            Thread.sleep(2000);

            new LoginUsingLTI().ltiLogin("11_2");//log in as student2
            ReportUtil.log("Student2 login", "Student2 logged in successfully", "pass");

            new Navigator().NavigateTo("Assignments");
            assignments.assignmentName.click();
            Thread.sleep(2000);

            new LoginUsingLTI().ltiLogin("11_3");//log in as student3
            ReportUtil.log("Student3 login", "Student3 logged in successfully", "pass");

            new Navigator().NavigateTo("Assignments");
            assignments.assignmentName.click();
            Thread.sleep(2000);

            new LoginUsingLTI().ltiLogin("11");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");
            Thread.sleep(120000);
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.viewStudentResponses.get(0));
            WebDriverUtil.clickOnElementUsingJavascript(assessmentResponses.resumeGrading_button);//click on yes
            Thread.sleep(1000);
            assignmentResponsesPage.getRefreshButton().click();
            Thread.sleep(1000);
            new Assignment().enterGradeOnParticularQuestion(0, 0, "1.0");
            new Assignment().enterGradeOnParticularQuestion(0, 1, "1.0");
            new Assignment().enterGradeOnParticularQuestion(0, 2, "1.0");

            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());

            new Navigator().NavigateTo("Gradebook");
            ReportUtil.log("Navigate to grade book", "Instructor navigated to grade book page successfully", "pass");

            gradebook.studentPerformanceTab.click();//click on student performance tab
            new MouseHover().mouserhoverbywebelement(gradebook.coloredMarker.get(2));
            Thread.sleep(2000);
            gradebook.coloredMarker.get(2).click();//click on dot on scatter plot
            Thread.sleep(2000);

            clickOnAssignment("AssignmentPerformance_1");

            WebDriverUtil.scrollIntoView(assessmentResponses.studentName.get(2), false);
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link());
            WebDriverUtil.waitTillVisibilityOfElement(assignmentResponsesPage.getViewResponseLink(), 10);

            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getViewResponseLink());
            assignmentResponsesPage.getScore().clear();
            assignmentResponsesPage.getScore().sendKeys("0");
            assignmentResponsesPage.getSaveButton().click();//click on save

            new Navigator().NavigateTo("Gradebook");
            ReportUtil.log("Navigate to grade book", "Instructor navigated to grade book page successfully", "pass");

            gradebook.studentPerformanceTab.click();//click on student performance tab

            actual = gradebook.overAllScorePercentage.get(0).getText();
            expected = "50%";
            CustomAssert.assertEquals(actual, expected, "Verify overall score", "Overall score for student is updated", "Overall score for student is not updated");

        } catch (Exception e) {
            Assert.fail("Exception in test case notApplicableImpacts in class StudentPerformance", e);
        }
    }


    @Test(priority = 12, enabled = true)
    public void removeStudentVerification() {
        try {
            new Assignment().create(12);
            new LoginUsingLTI().ltiLogin("12_1");//log in as student
            ReportUtil.log("Login as student1", "student logged successfully", "pass");
            new LoginUsingLTI().ltiLogin("12_2");//log in as student
            ReportUtil.log("Login as student2", "student logged successfully", "pass");
            new LoginUsingLTI().ltiLogin("12_3");//log in as student
            ReportUtil.log("Login as student3", "student logged successfully", "pass");

            new LoginUsingLTI().ltiLogin("12");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Assignment().assignAssignmentWithDueDate(12);

            new LoginUsingLTI().ltiLogin("12_1");//log in as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().submitButtonInQuestionClick();

            new LoginUsingLTI().ltiLogin("12_2");//log in as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            Thread.sleep(60000);
            new LoginUsingLTI().ltiLogin("12");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());

            try {
                WebDriverUtil.clickOnElementUsingJavascript(assessmentResponses.resumeGrading_button);//click on yes
                Thread.sleep(2000);
                WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
                Thread.sleep(2000);
            } catch (Exception e) {

            }
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            Thread.sleep(2000);
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            Thread.sleep(2000);
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());

            Thread.sleep(2000);
            new Assignment().enterGradeOnParticularQuestion(0, 0, "1.0");
            new Assignment().enterGradeOnParticularQuestion(1, 0, "1.0");
            new Assignment().enterGradeOnParticularQuestion(2, 0, "0.5");

            new LoginUsingLTI().ltiLogin("13");//log in as instructor to remove student
            new LoginUsingLTI().ltiLogin("12");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");

            navigateToGradeBook();
            gradebook.studentPerformanceTab.click();//click on student performance tab
            new MouseHover().mouserhoverbywebelement(gradebook.coloredMarker.get(0));
            Thread.sleep(2000);
            gradebook.coloredMarker.get(0).click();//click on dot on scatter plot
            Thread.sleep(2000);
            actual = gradeBookStatusBoxCount("AverageGrade", "studentPerformance_12");
            expected = "75%";
            CustomAssert.assertEquals(actual, expected, "Verify Average grade contribution of removed student", "In average grade moved student contribution is removed", "In average grade moved student contribution is  not removed");

        } catch (Exception e) {
            Assert.fail("Exception in TC removeStudentVerification of class  StudentPerformance", e);
        }
    }

    @Test(priority = 13, enabled = true)
    public void assignmentAssignToSpecificGroups() {
        try {
            ReportUtil.log("Description", "Test case validates assign assignment to groups", "info");

            new Assignment().create(14);
            ReportUtil.log("Assignment creation", "Test case validates creation of assessment with true false question", "info");

            new LoginUsingLTI().ltiLogin("13_1");//log in as student1
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");
            new LoginUsingLTI().ltiLogin("13_2");//log in as student2
            ReportUtil.log("Student2 login", "Student2 logged in successfully", "pass");
            new LoginUsingLTI().ltiLogin("13_3");//log in as student3
            ReportUtil.log("Student3 login", "Student3 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("14");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new AddGroup().addGroup(14);
            ReportUtil.log("Creation of group", "Group created successfully", "pass");

            new AddGroup().addStudentToParticularGroup(1, 0, 3);
            ReportUtil.log("Adding student to group", "2 students added to group successfully", "pass");

            new Assignment().assignAssignmentToGroup(14);
            ReportUtil.log("Assign assignment to group", "Assignment assign to group successfully", "pass");

            new LoginUsingLTI().ltiLogin("13_1");//log in as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getAssessmentName());
            new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().submitButtonInQuestionClick();
            ReportUtil.log("Submit assignment", "Student1 submitted assignment successfully", "pass");

            new LoginUsingLTI().ltiLogin("13_2");//log in as student2
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getAssessmentName());
            Thread.sleep(60000);
            ReportUtil.log("Assignment in Progress", "Student2 left the assignment in progress", "pass");

            new LoginUsingLTI().ltiLogin("14");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());

            try {
                assessmentResponses.resumeGrading_button.click();//click on yes
                WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            } catch (Exception e) {

            }
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            navigateToGradeBook();
            gradebook.studentPerformanceTab.click();//click on student performance tab
            Thread.sleep(1000);

            //Verification of 'NAME' column
            WebDriverUtil.scrollIntoView(gradebook.studentsName.get(0),false);
            Thread.sleep(2000);
            List<String> student = new ArrayList<String>();
            List<String> student1 = new ArrayList<String>();
            student1.add("a, month");
            student1.add("b, month");
            student1.add("family, givenname");
            List<WebElement> studentName = gradebook.studentsName;
            for (WebElement we : studentName) {
                student.add(we.getText());
            }

            if (!student.equals(student1)) {
                CustomAssert.fail("Verify student name", "student name is not in ascending order");
            }

            //Verification of 'Assignments Finished' column
            new GradeBook().sortValueInAscendingOrder(13,"AssignmentFinished");

            //Verification of 'Overall Score' column
            List<String> overAllScore1 = new ArrayList<String>();
            overAllScore1.add("100%");
            overAllScore1.add("0%");
            overAllScore1.add("0%");
            List<WebElement> overAllScores = gradebook.overAllScorePercentage;
            boolean scoreMismatch = false;
            for (int i = 0; i < overAllScores.size(); i++) {
                if (overAllScore1.get(i).equals(overAllScores.get(i).getText())) {
                    scoreMismatch = true;
                } else {
                    scoreMismatch = false;
                }
            }

            Assert.assertEquals(scoreMismatch, true);
            Thread.sleep(1000);
            gradebook.coloredMarker.get(2).click();
            Thread.sleep(1000);
            actual = gradeBookStatusBoxCount("Completion Level", "studentPerformance_13");
            expected = "Finished";
            CustomAssert.assertEquals(actual, expected, "Verify completion level", "For completed student completion level is 'Finished'", "For completed  student completion level is not 'Finished'");

            actual = gradeBookStatusBoxCount("Grade", "studentPerformance_13");
            expected = "100%\n" +
                    "1/1";
            CustomAssert.assertEquals(actual, expected, "Verify grade", "For completed student grade is '100%", "For completed student grade is not '100%'");

            actual = gradeBookStatusBoxCount("AverageGrade", "studentPerformance_13");
            expected = "50%";
            CustomAssert.assertEquals(actual, expected, "Verify Average grade", "For completed average grade is as per expected", "For completed student Average grade is not as per expected");

        } catch (Exception e) {
            Assert.fail("Exception in method assignmentAssignToSpecificGroups in class StudentPerformance", e);
        }

    }


    @Test(priority = 14, enabled = true)
    public void addStudentInTheGroups() {
        try {
            ReportUtil.log("Description", "Test case validates assign assignment to groups", "info");

            new Assignment().create(15);
            ReportUtil.log("Assignment creation", "Test case validates creation of assessment with true false question", "info");

            new LoginUsingLTI().ltiLogin("15_1");//log in as student1
            ReportUtil.log("Student1 login", "Student1 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("15");//log in as instructor
            ReportUtil.log("Instructor login", "Instructor logged in successfully", "pass");

            new AddGroup().addGroup(15);
            ReportUtil.log("Creation of group", "Group created successfully", "pass");

            new AddGroup().addStudentToParticularGroup(1, 0, 1);
            ReportUtil.log("Adding student to group", "1 students added to group successfully", "pass");

            new Assignment().assignAssignmentToGroup(15);
            ReportUtil.log("Assign assignment to group", "Assignment assign to group successfully", "pass");

            new LoginUsingLTI().ltiLogin("15_1");//log in as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getAssessmentName());
            new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().submitButtonInQuestionClick();
            ReportUtil.log("Submit assignment", "Student1 submitted assignment successfully", "pass");

            new LoginUsingLTI().ltiLogin("15_2");//log in as student2
            ReportUtil.log("Student2 login", "Student2 logged in successfully", "pass");

            new LoginUsingLTI().ltiLogin("15");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");

            new Navigator().NavigateTo("Groups");
            ReportUtil.log("Navigate to group", "Navigated to Group page", "info");
            new AddGroup().addStudentToParticularGroup(1, 1, 2);
            Thread.sleep(60000);
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            new Assignment().enterGradeOnParticularQuestion(1, 0, "0.7");


            try {
                assessmentResponses.resumeGrading_button.click();//click on yes
                WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.getRefreshButton());
            } catch (Exception e) {

            }
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all
            navigateToGradeBook();
            gradebook.studentPerformanceTab.click();//click on student performance tab
            Thread.sleep(1000);

            //Verification of 'NAME' column
            WebDriverUtil.scrollIntoView(gradebook.studentsName.get(0),false);
            Thread.sleep(2000);
            List<String> student = new ArrayList<String>();
            List<String> student1 = new ArrayList<String>();
            student1.add("a, month");
            student1.add("b, month");
            List<WebElement> studentName = gradebook.studentsName;
            for (WebElement we : studentName) {
                student.add(we.getText());
            }

            if (!student.equals(student1)) {
                CustomAssert.fail("Verify student name", "student name is not in ascending order");
            }

            //Verification of 'Assignments Finished' column
            new GradeBook().sortValueInAscendingOrder(15,"AssignmentFinished");

            //Verification of 'Overall Score' column
            List<String> overAllScore1 = new ArrayList<String>();
            overAllScore1.add("100%");
            overAllScore1.add("70%");
            List<WebElement> overAllScores = gradebook.overAllScorePercentage;
            boolean scoreMismatch = false;
            for (int i = 0; i < overAllScores.size(); i++) {
                if (overAllScore1.get(i).equals(overAllScores.get(i).getText())) {
                    scoreMismatch = true;
                } else {
                    scoreMismatch = false;
                }
            }

            Assert.assertEquals(scoreMismatch, true);
            Thread.sleep(2000);
            gradebook.coloredMarker.get(0).click();
            Thread.sleep(1000);
            actual = gradeBookStatusBoxCount("Completion Level", "studentPerformance_15");
            expected = "Did Not Start";
            CustomAssert.assertEquals(actual, expected, "Verify completion level", "For later added student completion level is 'Did Not Start'", "For later added student completion level is not 'Did Not Start'");

            actual = gradeBookStatusBoxCount("Grade", "studentPerformance_15");
            expected = "70%\n" +
                    "0.7/1";
            CustomAssert.assertEquals(actual, expected, "Verify grade", "For later added student grade is '70%", "For later added student grade is not '70%'");

            actual = gradeBookStatusBoxCount("AverageGrade", "studentPerformance_15");
            expected = "85%";
            CustomAssert.assertEquals(actual, expected, "Verify Average grade", "For later added student average grade is as per expected", "For later added student Average grade is not as per expected");

        } catch (Exception e) {
            Assert.fail("Exception in method assignmentAssignToSpecificGroups in class StudentPerformance", e);
        }

    }

    public void notApplicable(int index) {
        try {
            assignmentResponsesPage.student_checkBox.get(index).click();
            assignmentResponsesPage.menuIcon.click();
            assignmentResponsesPage.notApplicable.click();
            assignmentResponsesPage.yes_NotApplicable.click();

        } catch (Exception e) {
            Assert.fail("Exception in method clickOnTheParticularAssignment in class StudentPerformance", e);
        }

    }

    public void clickOnParticularVSRP(String assignmentName) {
        try {
            new Navigator().NavigateTo("Class Assignments");
            int index = 0;
            List<WebElement> assessment = currentAssignments.getList_assignmentName();
            for (WebElement ele : assessment) {
                if (ele.getText().trim().equals(assignmentName)) {
                    break;
                }
                index++;
            }
            System.out.println("Index:" + index);
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.viewStudentResponses.get(index));
        } catch (Exception e) {
            Assert.fail("Exception in Method clickOnParticularVSRP of class StudentPerformance", e);
        }

    }

    public String gradeBookStatusBoxCount(String boxName, String assignmentName) {
        driver = Driver.getWebDriver();
        String value = "";
        int index = 0;
        List<WebElement> assignments = gradebook.assignment_Name;
        for (WebElement element : assignments) {
            new ScrollElement().scrollToViewOfElement(element);
            if (element.getText().trim().equals(assignmentName)) {
                break;
            }
            System.out.println("index:" + index);
            index++;
        }

        if (boxName.equals("Grade")) {
            List<WebElement> grade = gradebook.studentGrade;
            value = grade.get(index).getText().trim();
        }

        if (boxName.equals("AverageGrade")) {
            List<WebElement> averageGrade = gradebook.averageGrade;
            value = averageGrade.get(index).getText().trim();
        }

        if (boxName.equals("Completion Level")) {
            List<WebElement> completionLevel = gradebook.completionLevel;
            value = completionLevel.get(index).getText().trim();
        }

        return value;

    }

    public void clickOnAssignment(String assignmentName) {
        driver = Driver.getWebDriver();
        List<WebElement> assignments = gradebook.assignment_Name;
        for (WebElement element : assignments) {
            new ScrollElement().scrollToViewOfElement(element);
            if (element.getText().trim().equals(assignmentName)) {
                element.click();
                break;
            }
        }
    }

    public void navigateToGradeBook() {
        driver = Driver.getWebDriver();
        new Navigator().NavigateTo("Gradebook");
        ReportUtil.log("Navigate to grade book", "Instructor navigated to grade book page successfully", "pass");
    }
}
