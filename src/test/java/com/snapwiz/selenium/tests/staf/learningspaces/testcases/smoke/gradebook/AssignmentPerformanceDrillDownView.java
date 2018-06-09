package com.snapwiz.selenium.tests.staf.learningspaces.testcases.smoke.gradebook;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Gradebook.Gradebook;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.MyActivity.MyActivity;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.AssignmentTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.Filter;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.TocSearch;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ScrollElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by mukesh on 21/7/16.
 */
public class AssignmentPerformanceDrillDownView extends Driver {
    WebDriver driver;
    AssignmentResponsesPage assignmentResponsesPage;
    AssessmentResponses assessmentResponses;
    CurrentAssignments currentAssignments;
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
    Filter filter;
    TocSearch tocSearch;
    String actual = "";
    String expected = "";
    int actualSize = 0;
    int expectedSize = 0;

    String assignmentName="";
    @BeforeMethod
    public void initElement() {
        driver = Driver.getWebDriver();
        assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);
        assessmentResponses = PageFactory.initElements(driver, AssessmentResponses.class);
        currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
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
        tocSearch= PageFactory.initElements(driver,TocSearch.class);
        lessonPage= PageFactory.initElements(driver, LessonPage.class);
        filter=PageFactory.initElements(driver,Filter.class);
    }

    @Test(priority = 1,enabled = true)
    public void drillDownToAssignment(){
        try {
            assignmentName="5GradableAssignment";
            new LoginUsingLTI().ltiLogin("1");//log in as instructor
            navigateToGradeBookPage();
            for (WebElement ele:gradebook.classPerformanceByAssignment_link){
                if(ele.getAttribute("title").equals(assignmentName)){
                    WebDriverUtil.clickOnElementUsingJavascript(ele);
                    break;
                }
            }

            actual = gradebook.activeTab.getAttribute("title");
            expected = "Assignment Performance";
            CustomAssert.assertEquals(actual, expected, "Verify assignment performance tab", "Class Performance for particular assignment page should be displayed on the same tab.", "Class Performance for particular assignment page is not  displaying on the same tab.");

            actual=gradebook.tabHeader.get(0).getText().trim();
            expected="Class Performance for "+assignmentName;
            CustomAssert.assertEquals(actual, expected, "Verify assignment name order", "Assignment name order is correct", "Assignment name order is not correct");

            actual=gradebook.tab_header_assignment_label.getCssValue("color");
            expected="rgba(43, 133, 192, 1)";
            CustomAssert.assertEquals(actual, expected, "Verify assignment color", "Assignment name is in blue color", "Assignment name is not in blue color");

            WebDriverUtil.clickOnElementUsingJavascript(gradebook.backButton); //click on the back button

            for (WebElement ele:gradebook.classPerformanceByAssignment_link){
                if(ele.getAttribute("title").equals(assignmentName)){
                    WebDriverUtil.clickOnElementUsingJavascript(ele);
                    break;
                }
            }

            WebDriverUtil.clickOnElementUsingJavascript(gradebook.tab_header_assignment_label);
            String responsePageTitle = assignmentResponsesPage.getPageTitle().getText();
            CustomAssert.assertEquals(responsePageTitle, "Assignment Responses", "Verify student response page", "Instructor is  navigated to Assignment Response page", "Instructor is not navigated to Assignment Response page");


            new Assignment().enterGradeOnParticularQuestion(0,0,"0.7");
            WebDriverUtil.clickOnElementUsingJavascript(gradebook.goBackButton); //click on the back button


            actual = gradebook.transferred_gradebook_button.getAttribute("title").trim();
            expected = "Transferred Gradebook";
            CustomAssert.assertEquals(actual, expected, "Verify Transferred Gradebook button", "Transferred Gradebook button is displaying", "Transferred Gradebook button not is displaying");

            WebDriverUtil.clickOnElementUsingJavascript(gradebook.getGradebookWeighting());
            gradebook.getEnterGradebookWeighting().get(0).sendKeys("10");
            WebDriverUtil.clickOnElementUsingJavascript(gradebook.getgradebookWeightingSaveButton());

            actual = gradebook.notification_message.getText().trim();
            expected = "*Total Must be equal to 100";
            CustomAssert.assertEquals(actual, expected, "Verify Total Must be equal to 100 message", "Total Must be equal to 100 message is displaying", "Total Must be equal to 100 message not is displaying");

            gradebook.getEnterGradebookWeighting().get(0).clear();
            new KeysSend().sendKeyBoardKeys("^");
            WebDriverUtil.clickOnElementUsingJavascript(gradebook.getgradebookWeightingSaveButton());
            Thread.sleep(4000);
            CustomAssert.assertEquals(new BooleanValue().presenceOfElement(1, gradebook.gradebook_popup), false, "verify  \"Gradebook Weighting\" pop up", "\"Gradebook Weighting\" pop up should be closed .", "\"Gradebook Weighting\" pop up should is not getting closed");
            WebDriverUtil.clickOnElementUsingJavascript(gradebook.getGradebookWeighting());
            driver.findElement(By.cssSelector("body")).click();
            CustomAssert.assertEquals(new BooleanValue().presenceOfElement(1, gradebook.gradebook_popup), false, "verify  \"Gradebook Weighting\" pop up", "\"Gradebook Weighting\" pop up should be closed .", "\"Gradebook Weighting\" pop up should is not getting closed");

        } catch (Exception e) {
            Assert.fail("Exception in TC drillDownToAssignment of class  AssignmentPerformanceDrillDownView", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void verifyGradeIntervalFilterZeroTo59() {
        try {

            assignmentName="3GradableAssignment";
            new LoginUsingLTI().ltiLogin("1");//log in as existing instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            navigateToGradeBookPage();

            clickOnTheAssignmentLink(assignmentName);
            gradebook.gradeRangeSelector.get(0).click(); //click on the first range
            new MouseHover().mouserhoverbywebelement(gradebook.assignment_barEntry.get(1));//Mouse hover on the average time spent
            actual = gradebook.gradeBox_color.get(3).getAttribute("stroke").trim();
            expected = "rgb(205,71,15)";//red
            CustomAssert.assertEquals(actual, expected, "Verify average grade (0-59) range color", "Average grade (0-59) range  color is " + expected + "", "Average grade  (0-59) range color is not " + expected + "");

            Thread.sleep(1000);
            actual = gradebook.dotBox.get(0).getText().trim();
            expected = "b, month\n" + "Grade: 50%";
            CustomAssert.assertEquals(actual, expected, "Verify Bar Graph Value OnMouseHover of (0%-59%)", " Bar Graph Value OnMouseHover of (0%-59%) is " + expected + "", " Bar Graph Value OnMouseHover of (0%-59%) is not " + expected + "");

            actual = gradebook.getAverage_grade_label.get(0).getText().trim();
            expected = "Average\n75%";
            CustomAssert.assertEquals(actual, expected, "Verify Bar Graph Value OnMouseHover of (0%-59%)", " Bar Graph Value OnMouseHover of (0%-59%) is " + expected + "", " Bar Graph Value OnMouseHover of (0%-59%) is not " + expected + "");

            new MouseHover().mouserhoverbywebelement(gradebook.assignment_barEntry.get(4));//Mouse hover on the average time spent
            actual = gradebook.gradeBox_color.get(7).getAttribute("stroke").trim();
            expected = "rgb(126, 180, 212)";//blue
            CustomAssert.assertEquals(actual, expected, "Verify average time spent (60-79) range color", "average time spent (60-79) range  color is " + expected + "", "average time spent (60-79) range color is not " + expected + "");

            Thread.sleep(1000);
            actual = gradebook.dotBox.get(1).getText().trim();
            expected = "b, month\n"+"Time Spent: 1 min(s)";
            CustomAssert.assertEquals(actual, expected, "Verify average time spent Value OnMouseHover of (60%-79%)", "Average time spent Value OnMouseHover of (60%-79%) is " + expected + "", "Average time spent Value OnMouseHover of (60%-79%) is not " + expected + "");

            actual = gradebook.getAverage_grade_label.get(1).getText().trim();
            expected = "Average\n1 min(s)";
            CustomAssert.assertEquals(actual, expected, "Verify Bar Graph Value OnMouseHover of (0%-59%)", " Bar Graph Value OnMouseHover of (0%-59%) is " + expected + "", " Bar Graph Value OnMouseHover of (0%-59%) is not " + expected + "");

        } catch (Exception e) {
            Assert.fail("Exception in TC verifyGradeIntervalFilterZeroTo59 of class  AssignmentPerformanceDrillDownView", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void verifyGradeIntervalFilter60To79() {
        try {

            assignmentName="5GradableAssignment";
            new LoginUsingLTI().ltiLogin("1");//log in as existing instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            navigateToGradeBookPage();

            clickOnTheAssignmentLink(assignmentName);
            gradebook.gradeRangeSelector.get(1).click(); //click on the second range
            new MouseHover().mouserhoverbywebelement(gradebook.assignment_barEntry.get(0));//Mouse hover on the average time spent
            actual = gradebook.gradeBox_color.get(3).getAttribute("stroke").trim();
            expected = "rgb(235,187,61)";//yellow
            CustomAssert.assertEquals(actual, expected, "Verify average time spent (60-79) range color", "average time spent (60-79) range  color is " + expected + "", "average time spent (60-79) range color is not " + expected + "");
            Thread.sleep(1000);

            actual = gradebook.dotBox.get(0).getText().trim();
            expected = "b, month\n" + "Grade: 68%";
            CustomAssert.assertEquals(actual, expected, "Verify Bar Graph Value OnMouseHover of (0%-59%)", " Bar Graph Value OnMouseHover of (0%-59%) is " + expected + "", " Bar Graph Value OnMouseHover of (0%-59%) is not " + expected + "");

            actual = gradebook.getAverage_grade_label.get(0).getText().trim();
            expected = "Average\n87%";
            CustomAssert.assertEquals(actual, expected, "Verify Bar Graph Value OnMouseHover of (0%-59%)", " Bar Graph Value OnMouseHover of (0%-59%) is " + expected + "", " Bar Graph Value OnMouseHover of (0%-59%) is not " + expected + "");

          /*  new MouseHover().mouserhoverbywebelement(gradebook.assignment_barEntry.get(4));//Mouse hover on the average time spent
            actual = gradebook.gradeBox_color.get(7).getAttribute("stroke").trim();
            expected = "rgb(126, 180, 212)";//blue
            CustomAssert.assertEquals(actual, expected, "Verify average time spent (60-79) range color", "average time spent (60-79) range  color is " + expected + "", "average time spent (60-79) range color is not " + expected + "");

            Thread.sleep(1000);
            actual = gradebook.dotBox.get(1).getText().trim();
            expected = "b, month\n"+"Time Spent: 1 min(s)";
            CustomAssert.assertEquals(actual, expected, "Verify average time spent Value OnMouseHover of (60%-79%)", "Average time spent Value OnMouseHover of (60%-79%) is " + expected + "", "Average time spent Value OnMouseHover of (60%-79%) is not " + expected + "");
*/
            actual = gradebook.getAverage_grade_label.get(1).getText().trim();
            expected = "Average\n0 min(s)";
            CustomAssert.assertEquals(actual, expected, "Verify Bar Graph Value OnMouseHover of (0%-59%)", " Bar Graph Value OnMouseHover of (0%-59%) is " + expected + "", " Bar Graph Value OnMouseHover of (0%-59%) is not " + expected + "");

        } catch (Exception e) {
            Assert.fail("Exception in TC verifyGradeIntervalFilter60To79 of class  AssignmentPerformanceDrillDownView", e);
        }
    }


    @Test(priority = 4, enabled = true)
    public void verifyGradeIntervalFilter80To100() {
        try {

            assignmentName="AssignmentPerformance_1";
            new LoginUsingLTI().ltiLogin("1");//log in as existing instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            navigateToGradeBookPage();

            WebDriverUtil.clickOnElementUsingJavascript(gradebook.nextArrow.get(0));
            clickOnTheAssignmentLink(assignmentName);

            gradebook.gradeRangeSelector.get(2).click(); //click on the second range
            new MouseHover().mouserhoverbywebelement(gradebook.assignment_barEntry.get(0));//Mouse hover on the average time spent
            actual = gradebook.assignment_barEntry.get(0).getAttribute("fill").trim();
            expected = "#6bb45f"; //yellow
            CustomAssert.assertEquals(actual, expected, "Verify (80-100) range color", "(80-100) range color is " + expected + "", "(80-100) range color is not " + expected + "");
            Thread.sleep(1000);
            actual = gradebook.dotBox.get(0).getText().trim();
            expected = "d, month\n" + "Grade: 100%";
            CustomAssert.assertEquals(actual, expected, "Verify Bar Graph Value OnMouseHover of (0%-59%)", " Bar Graph Value OnMouseHover of (0%-59%) is " + expected + "", " Bar Graph Value OnMouseHover of (0%-59%) is not " + expected + "");

            actual = gradebook.getAverage_grade_label.get(0).getText().trim();
            expected = "Average\n58%";
            CustomAssert.assertEquals(actual, expected, "Verify Bar Graph Value OnMouseHover of (0%-59%)", " Bar Graph Value OnMouseHover of (0%-59%) is " + expected + "", " Bar Graph Value OnMouseHover of (0%-59%) is not " + expected + "");

            new MouseHover().mouserhoverbywebelement(gradebook.assignment_barEntry.get(1));//Mouse hover on the average time spent
            actual = gradebook.gradeBox_color.get(7).getAttribute("stroke").trim();
            expected = "rgb(126, 180, 212)";//blue
            CustomAssert.assertEquals(actual, expected, "Verify average time spent (60-79) range color", "average time spent (60-79) range  color is " + expected + "", "average time spent (60-79) range color is not " + expected + "");

            Thread.sleep(1000);
            actual = gradebook.dotBox.get(1).getText().trim();
            expected = "d, month\n"+"Time Spent: 1 min(s)";
            CustomAssert.assertEquals(actual, expected, "Verify average time spent Value OnMouseHover of (60%-79%)", "Average time spent Value OnMouseHover of (60%-79%) is " + expected + "", "Average time spent Value OnMouseHover of (60%-79%) is not " + expected + "");


            actual = gradebook.getAverage_grade_label.get(1).getText().trim();
            expected = "Average\n1 min(s)";
            CustomAssert.assertEquals(actual, expected, "Verify Bar Graph Value OnMouseHover of (0%-59%)", " Bar Graph Value OnMouseHover of (0%-59%) is " + expected + "", " Bar Graph Value OnMouseHover of (0%-59%) is not " + expected + "");

        } catch (Exception e) {
            Assert.fail("Exception in TC verifyGradeIntervalFilter60To79 of class  AssignmentPerformanceDrillDownView", e);
        }
    }


    @Test(priority = 5, enabled = true)
    public void verifyAverageTimeForFBAAndDWAssignment() {
        try {

            String filename = ReadTestData.readDataByTagName("", "filename", "3");
            new LoginUsingLTI().ltiLogin("3");//log in as instructor
            new Assignment().createFileBasedAssessmentAtInstructorSide(3);
            new Assignment().assignFileBasedAssignmentFromMyQuestionBank(3);

            new LoginUsingLTI().ltiLogin("3_1");//log in as student
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getAssessmentName());
            Thread.sleep(60000);
            WebDriverUtil.clickOnElementUsingJavascript(newAssignment.uploadFileButton);//click on the upload file link;
            Thread.sleep(3000);
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(60000);
            new LoginUsingLTI().ltiLogin("3_2");//log in as student
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getAssessmentName());
            Thread.sleep(60000);
            WebDriverUtil.clickOnElementUsingJavascript(newAssignment.uploadFileButton);//click on the upload file link;
            Thread.sleep(3000);
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(60000);
            assignmentTab.finishButton.click();//click on finish assignment

            new LoginUsingLTI().ltiLogin("3_3");//log in as student
            new Navigator().NavigateTo("Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getAssessmentName());
            Thread.sleep(60000);
            WebDriverUtil.clickOnElementUsingJavascript(newAssignment.uploadFileButton);//click on the upload file link;
            Thread.sleep(3000);
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            assignmentTab.finishButton.click();//click on finish assignment/*


            new LoginUsingLTI().ltiLogin("3");//log in as instructor

            assignmentName=ReadTestData.readDataByTagName("","assessmentname","3");
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.refresh_icon);

            try {
                assessmentResponses.resumeGrading_button.click();//click on yes
                WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.refresh_icon);

            } catch (Exception e) {
            }
            navigateToGradeBookPage();
            clickOnTheAssignmentLink(assignmentName); //click on the file based assignment link;
            Thread.sleep(1000);
            actual = gradebook.getAverage_grade_label.get(1).getText().trim();
            expected = "Average\nNot Available";
            CustomAssert.assertEquals(actual, expected, "Verify Bar Graph Value OnMouseHover of (0%-59%)", " Bar Graph Value OnMouseHover of (0%-59%) is " + expected + "", " Bar Graph Value OnMouseHover of (0%-59%) is not " + expected + "");

        } catch (Exception e) {
            Assert.fail("Exception in TC verifyAverageTimeForFBAAndDWAssignment of class  AssignmentPerformanceDrillDownView", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void nameColumn() {
        try {

            assignmentName="3GradableAssignment";
            new LoginUsingLTI().ltiLogin("1");//log in as existing instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            navigateToGradeBookPage();
            Thread.sleep(5000);
            clickOnTheAssignmentLink(assignmentName);
            new ScrollElement().scrollBottomOfPage();
            //Verification of 'NAME' column
            actual = gradebook.drillDown_AssignmentMetrics.get(0).getText().trim();
            expected = "Name";
            CustomAssert.assertEquals(actual, expected, "Verify the 1st column header in the table", "1st column header is as per expected", "1st column header is not as per expected");

            List<String> student = new ArrayList<String>();
            List<String> actualStudent = new ArrayList<String>();
            actualStudent.add("a, month");
            actualStudent.add("b, month");
            actualStudent.add("c, month");
            actualStudent.add("d, month");
            actualStudent.add("e, month");
            List<WebElement> studentName = gradebook.drillDown_studentName;
            for (WebElement we : studentName) {
                student.add(we.getText());
            }

            if (!student.equals(actualStudent)) {
                CustomAssert.fail("Verify student name", "student name is not in ascending order");
            }

            new MouseHover().mouserhoverbywebelement(gradebook.ascending);//mouse hover on sort in "Name" header
            actual = gradebook.ascending.getAttribute("title");
            expected = "Ascending";
            CustomAssert.assertEquals(actual, expected, "Verify tool tip for Ascending", "Ascending is displaying in tool tip", "Ascending is not displaying in tool tip");

            WebDriverUtil.clickOnElementUsingJavascript(gradebook.ascending);
            student = new ArrayList<String>();
            actualStudent = new ArrayList<String>();
            actualStudent.add("e, month");
            actualStudent.add("d, month");
            actualStudent.add("c, month");
            actualStudent.add("b, month");
            actualStudent.add("a, month");
            studentName = gradebook.drillDown_studentName;
            for (WebElement we : studentName) {
                student.add(we.getText());
            }

            if (!student.equals(actualStudent)) {
                CustomAssert.fail("Verify student name", "student name is not in ascending order");
            }

            new MouseHover().mouserhoverbywebelement(gradebook.desc_sorting);//mouse hover on sort in "Name" header
            actual = gradebook.desc_sorting.getAttribute("title");
            expected = "Descending";
            CustomAssert.assertEquals(actual, expected, "Verify tool tip for Descending", "Descending is displaying in tool tip", "Descending is not displaying in tool tip");


        } catch (Exception e) {
            Assert.fail("Exception in TC nameColumn of class  AssignmentPerformance", e);
        }
    }

    @Test(priority = 7, enabled = true)
    public void completionLevelColumn() {
        try {

            assignmentName="3GradableAssignment";
            new LoginUsingLTI().ltiLogin("1");//log in as existing instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            navigateToGradeBookPage();
            Thread.sleep(5000);
            clickOnTheAssignmentLink(assignmentName);
            new ScrollElement().scrollBottomOfPage();
            //Verification of 'NAME' column
            actual = gradebook.drillDown_AssignmentMetrics.get(1).getText().trim();
            expected = "Completion Level";
            CustomAssert.assertEquals(actual, expected, "Verify the second column header in the table", "second column header is as per expected", "second column header is not as per expected");

            gradebook.drillDown_helpIcon.get(0).click();//click on help icon available in 4th column
            actual = gradebook.getHelpNotification().getText();
            expected = "This shows assignment submission status for each student";
            CustomAssert.assertEquals(actual, expected, "Verify help text", "Help text is as per expected", "Help text is not as per expected");

            WebDriverUtil.clickOnElementUsingJavascript(gradebook.drillDown_sortIcon.get(1));
            List<String> student = new ArrayList<String>();
            List<String> actualCompletionLevel = new ArrayList<String>();
            actualCompletionLevel.add("Did Not Start");
            actualCompletionLevel.add("Did Not Start");
            actualCompletionLevel.add("Did Not Finish");
            actualCompletionLevel.add("Finished");
            actualCompletionLevel.add("Finished");
            List<WebElement> completionLevel = gradebook.drillDown_completionLevel;
            for (WebElement we : completionLevel) {
                System.out.println("dd"+we.getText());
                student.add(we.getText());
            }

            if (!student.equals(actualCompletionLevel)) {
                CustomAssert.fail("Verify completionLevel", "completionLevel is not in ascending order");
            }

            new MouseHover().mouserhoverbywebelement(gradebook.ascending);//mouse hover on sort in "Name" header
            actual = gradebook.ascending.getAttribute("title");
            expected = "Ascending";
            CustomAssert.assertEquals(actual, expected, "Verify tool tip for Ascending", "Ascending is displaying in tool tip", "Ascending is not displaying in tool tip");

            WebDriverUtil.clickOnElementUsingJavascript(gradebook.ascending);
            student = new ArrayList<String>();
            actualCompletionLevel = new ArrayList<String>();
            actualCompletionLevel.add("Finished");
            actualCompletionLevel.add("Finished");
            actualCompletionLevel.add("Did Not Finish");
            actualCompletionLevel.add("Did Not Start");
            actualCompletionLevel.add("Did Not Start");

            completionLevel = gradebook.drillDown_completionLevel;
            for (WebElement we : completionLevel) {
                Thread.sleep(1000);
                student.add(we.getText());
            }

            if (!student.equals(completionLevel)) {
                CustomAssert.fail("Verify completionLevel", "completionLevel is not in Descending order");
            }

            new MouseHover().mouserhoverbywebelement(gradebook.desc_sorting);//mouse hover on sort in "Name" header
            actual = gradebook.desc_sorting.getAttribute("title");
            expected = "Descending";
            CustomAssert.assertEquals(actual, expected, "Verify tool tip for Descending", "Descending is displaying in tool tip", "Descending is not displaying in tool tip");


        } catch (Exception e) {
            Assert.fail("Exception in TC completionLevelColumn of class  AssignmentPerformance", e);
        }
    }

    @Test(priority = 8, enabled = true)
    public void timeSpentColumn() {
        try {

            assignmentName="3GradableAssignment";
            new LoginUsingLTI().ltiLogin("1");//log in as existing instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            navigateToGradeBookPage();
            Thread.sleep(5000);
            clickOnTheAssignmentLink(assignmentName);
            new ScrollElement().scrollBottomOfPage();
            //Verification of 'NAME' column
            actual = gradebook.drillDown_AssignmentMetrics.get(2).getText().trim();
            expected = "Time Spent";
            CustomAssert.assertEquals(actual, expected, "Verify the third column header in the table", "third column header is as per expected", "third column header is not as per expected");

            gradebook.drillDown_helpIcon.get(1).click();//click on help icon available in 4th column
            actual = gradebook.getHelpNotification().getText();
            expected = "This shows the time spent on this assignment by each student.";
            CustomAssert.assertEquals(actual, expected, "Verify help text", "Help text is as per expected", "Help text is not as per expected");

            WebDriverUtil.clickOnElementUsingJavascript(gradebook.drillDown_sortIcon.get(2));
            List<String> student = new ArrayList<String>();
            List<String> actualTimeSpent = new ArrayList<String>();
            actualTimeSpent.add("0 min(s)");
            actualTimeSpent.add("0 min(s)");
            actualTimeSpent.add("0 min(s)");
            actualTimeSpent.add("1 min(s)");
            actualTimeSpent.add("1 min(s)");
            List<WebElement> timeSpent = gradebook.drillDown_timeSpent;
            for (WebElement we : timeSpent) {
                student.add(we.getText());
            }

            if (!student.equals(actualTimeSpent)) {
                CustomAssert.fail("Verify TimeSpent", "TimeSpent is not in ascending order");
            }

            new MouseHover().mouserhoverbywebelement(gradebook.ascending);//mouse hover on sort in "Name" header
            actual = gradebook.ascending.getAttribute("title");
            expected = "Ascending";
            CustomAssert.assertEquals(actual, expected, "Verify tool tip for Ascending", "Ascending is displaying in tool tip", "Ascending is not displaying in tool tip");

            WebDriverUtil.clickOnElementUsingJavascript(gradebook.ascending);
            student = new ArrayList<String>();
            actualTimeSpent = new ArrayList<String>();
            actualTimeSpent.add("1 min(s)");
            actualTimeSpent.add("1 min(s)");
            actualTimeSpent.add("0 min(s)");
            actualTimeSpent.add("0 min(s)");
            actualTimeSpent.add("0 min(s)");

            for (WebElement we : timeSpent) {
                student.add(we.getText());
            }
            if (!student.equals(actualTimeSpent)) {
                CustomAssert.fail("Verify TimeSpent", "TimeSpent is not in ascending order");
            }


            new MouseHover().mouserhoverbywebelement(gradebook.desc_sorting);//mouse hover on sort in "Name" header
            actual = gradebook.desc_sorting.getAttribute("title");
            expected = "Descending";
            CustomAssert.assertEquals(actual, expected, "Verify tool tip for Descending", "Descending is displaying in tool tip", "Descending is not displaying in tool tip");


        } catch (Exception e) {
            Assert.fail("Exception in TC timeSpentColumn of class  AssignmentPerformance", e);
        }
    }

    @Test(priority = 9, enabled = true)
    public void notApplicableStudent() {
        try {

            String assignmentName =  ReadTestData.readDataByTagName("", "assessmentname", "4");

            new Assignment().create(4);
            new LoginUsingLTI().ltiLogin("4_1");//log in as student
            ReportUtil.log("Login as student1", "student logged successfully", "pass");
            new LoginUsingLTI().ltiLogin("4_2");//log in as student
            ReportUtil.log("Login as student2", "student logged successfully", "pass");
            new LoginUsingLTI().ltiLogin("4_3");//log in as student
            ReportUtil.log("Login as student3", "student logged successfully", "pass");

            new LoginUsingLTI().ltiLogin("4");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Assignment().assignAssignmentWithDueDate(4);


            new LoginUsingLTI().ltiLogin("4_1");//log in as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().submitButtonInQuestionClick();

            new LoginUsingLTI().ltiLogin("4_2");//log in as student
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().submitButtonInQuestionClick();

            new LoginUsingLTI().ltiLogin("4");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Navigator().NavigateTo("Class Assignments");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getViewGrade_link());

            notApplicable(1); //NA student
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.refresh_icon);
            try {
                assessmentResponses.resumeGrading_button.click();//click on yes
                WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.refresh_icon);

            } catch (Exception e) {
            }
            assignmentResponsesPage.getReleaseGradeForAll().click();//click on release grade for all*/
            navigateToGradeBookPage();
            clickOnTheAssignmentLink(assignmentName);

            actual = gradebook.drillDown_AssignmentMetrics.get(3).getText().trim();
            expected = "Grade";
            CustomAssert.assertEquals(actual, expected, "Verify the third column header in the table", "third column header is as per expected", "third column header is not as per expected");

            gradebook.drillDown_helpIcon.get(2).click();//click on help icon available in 4th column
            actual = gradebook.getHelpNotification().getText();
            expected = "This shows the grade on this assignment for each student.";
            CustomAssert.assertEquals(actual, expected, "Verify help text", "Help text is as per expected", "Help text is not as per expected");

            WebDriverUtil.clickOnElementUsingJavascript(gradebook.drillDown_sortIcon.get(4));
            actual = gradebook.drillDown_grade.get(0).getText().trim();
            expected = "Not Applicable";
            CustomAssert.assertEquals(actual, expected, "Verify grade", "For NA student grade is 'Not Applicable'", "For NA student grade is not 'Not Applicable'");

            WebDriverUtil.clickOnElementUsingJavascript(gradebook.ascending);
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(gradebook.desc_sorting);//mouse hover on sort in "Name" header
            actual = gradebook.desc_sorting.getAttribute("title");
            expected = "Descending";
            CustomAssert.assertEquals(actual, expected, "Verify tool tip for Descending", "Descending is displaying in tool tip", "Descending is not displaying in tool tip");

            actual = gradebook.drillDown_grade.get(2).getText().trim();
            expected = "Not Applicable";
            CustomAssert.assertEquals(actual, expected, "Verify grade", "For NA student grade is 'Not Applicable'", "For NA student grade is not 'Not Applicable'");


        } catch (Exception e) {
            Assert.fail("Exception in TC notApplicableStudent of class  AssignmentPerformance", e);
        }
    }

    @Test(priority = 10, enabled = true)
    public void deActivateQuestionFlow() {
        try {

            String assignmentName =  ReadTestData.readDataByTagName("", "assessmentname", "5");

            new Assignment().create(5);
            new Assignment().addQuestions(5, "truefalse", " ");
            new Assignment().addQuestions(5,"multiplechoice"," ");

            new LoginUsingLTI().ltiLogin("5_1");//log in as student
            ReportUtil.log("Login as student1", "student logged successfully", "pass");

            new LoginUsingLTI().ltiLogin("5");//log in as instructor
            ReportUtil.log("Login as instructor", "Instructor logged successfully", "pass");
            new Assignment().assignAssignmentWithDueDate(5);

            new LoginUsingLTI().ltiLogin("5");//log in as instructor
            new Navigator().NavigateTo("Current Assignments"); //navigate to Current Assignments
            currentAssignments.updateAssignment_link.get(0).click();
            currentAssignments.reAssign_link.click();

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@title='Select an Assignment Policy']")));
            new Click().clickbyxpath("//a[@rel='addNewAssignmentPolicy']");
            new AssignmentPolicy().createAssignmentPolicyWhileAssigning("New Policy", "Policy Description", "4", null, false, "1", "", "Auto-release on assignment submission", "", "", "");
            WebDriverUtil.clickOnElementUsingJavascript(currentAssignments.getUpdateButtonInReassign_button().get(0));


            new LoginUsingLTI().ltiLogin("5_1");//log in as student
            Thread.sleep(5000);
            new Assignment().submitAssignmentAsStudent(5);

            new LoginUsingLTI().ltiLogin("5");//log in as instructor
            Thread.sleep(5000);
            new Assignment().releaseGrades(5, "Release Grade for All");
            clickOnTheAssignmentLink(assignmentName);
            gradebook.drillDown_grade.get(0).getText().trim();
            actual =gradebook.drillDown_grade.get(3).getText().trim();
            expected = "100%\n12 / 12";
            CustomAssert.assertEquals(actual, expected, "Verify  grade", "Grade is displaying correctly", "Grade is not displaying correctly");



        } catch (Exception e) {
            Assert.fail("Exception in TC deActivateQuestionFlow of class  AssignmentPerformance", e);
        }
    }


    public static void navigateToGradeBookPage() {
        new Navigator().NavigateTo("Gradebook");
    }

    public  void clickOnTheAssignmentLink(String assignmentName) {

        for (WebElement ele:gradebook.classPerformanceByAssignment_link){
            if(ele.getAttribute("title").equals(assignmentName)){
                WebDriverUtil.clickOnElementUsingJavascript(ele);
                break;
            }
        }
    }

    public void notApplicable(int index) {
        try {
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.student_checkBox.get(index));
            WebDriverUtil.clickOnElementUsingJavascript(assignmentResponsesPage.menuIcon);
            assignmentResponsesPage.notApplicable.click();
            assignmentResponsesPage.yes_NotApplicable.click();
        } catch (Exception e) {
            Assert.fail("Exception in method notApplicable in class AssignmentPerformance", e);
        }

    }
}