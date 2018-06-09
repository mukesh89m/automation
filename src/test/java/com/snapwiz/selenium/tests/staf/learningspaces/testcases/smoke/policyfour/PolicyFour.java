package com.snapwiz.selenium.tests.staf.learningspaces.testcases.smoke.policyfour;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Gradebook.Gradebook;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.EngagementReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.MostChallengingReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.ProductivityReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.ProficiencyReport;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by priyanka on 15-02-2016.
 */
public class PolicyFour extends Driver {

    String actual;
    String expected;

    @Test(priority = 1, enabled = true)
    public void policyFour() {
        try {

            int actual_int=0;
            int expected_int=0;
            WebDriver driver=Driver.getWebDriver();
            Dashboard dashboard;
            ProficiencyReport proficiencyReport;
            MostChallengingReport mostChallengingReport;
            EngagementReport engagementReport;
            Assignments assignments;
            Gradebook gradebook;
            ProductivityReport productivityReport;
            CurrentAssignments currentAssignments;
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "1");
            currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            ReportUtil.log("Description","This test case validates creation of 12 questions,instructor assign assignment with policy4 to class section,assignment submitted by students,report verification at student and instructor side","info");

            new Assignment().create(1);
            for (int i = 0; i < 4; i++)
                new Assignment().addQuestions(1, "truefalse", "");

            for (int i = 5; i <= 6; i++)
                new Assignment().addQuestions(1, "multiplechoice", "");

            for (int i = 7; i <= 9; i++)
                new Assignment().addQuestions(1, "multipleselection", "");

            for (int i = 10; i < 12; i++)
                new Assignment().addQuestions(1, "textentry", "");

            ReportUtil.log("Create Question", "Created 12 questions of different type", "info");

            new LoginUsingLTI().ltiLogin("1_1");//login as student1
            ReportUtil.log("Student1 Login", "Student1 logged in successfully.", "Pass");

            new LoginUsingLTI().ltiLogin("1_2");//login as student2
            ReportUtil.log("Student2 Login", "Student2 logged in successfully.", "Pass");

            new LoginUsingLTI().ltiLogin("1_3");//login as student3
            ReportUtil.log("Student3 Login", "Student3 logged in successfully.", "Pass");

            new LoginUsingLTI().ltiLogin("1");//login as instructor
            ReportUtil.log("Instructor Login", "Instructor logged in successfully.", "Pass");

            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Release explicitly by the instructor", "", "", "","1");//till save policy
            ReportUtil.log("Navigated to policy", "Created policy with release option 4.", "Pass");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignAssignmentWithDueDate(1);//assign to student
            ReportUtil.log("Navigated to Assignments", "Instructor assigned assignment to class section with due date.", "Pass");


            new LoginUsingLTI().ltiLogin("1_1");//login as student1
            new Assignment().submitAssignmentAsStudent(1); //submit assignment
            ReportUtil.log("Student1 Login", "Student1 submitted the assignment.", "Pass");

            new LoginUsingLTI().ltiLogin("1_2");//login as student2
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();
            new WebDriverWait(driver,360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            for (int i=0;i<=4;i++) {
                new AttemptQuestion().trueFalse(false, "correct", 1);
                new Assignment().nextButtonInQuestionClick();
            }

            ReportUtil.log("Student2 Login", "Student2 attempted 5 questions and left the assignment in progress", "Pass");

            new LoginUsingLTI().ltiLogin("1");//login as instructor
            ReportUtil.log("Instructor Login", "Instructor logged in successfully", "Pass");

            new Assignment().provideGRadeToStudent(1);
            ReportUtil.log("Instructor provides grades", "Instructor provided grades", "Pass");
            Thread.sleep(300000); //wait for 5 minute for due to expiry
            ReportUtil.log("Due Date", "Due date expired", "info");

            new LoginUsingLTI().ltiLogin("1");//login as instructor
            ReportUtil.log("Instructor Login", "Instructor logged in successfully", "Pass");
            new Assignment().releaseGrades(1,"Release Grade for All");
            ReportUtil.log("Instructor releases grades", "Instructor released grades", "Pass");

            new RunScheduledJobs().runScheduledJob("AutoSubmitAssignmentJob");
            new RunScheduledJobs().runScheduledJob("ClassSectionActivityJob");
            new RunScheduledJobs().runScheduledJob("ClassSectionPerformanceJob");
            driver=new ReInitDriver().startDriver("firefox");

            gradebook=PageFactory.initElements(driver,Gradebook.class);
            engagementReport = PageFactory.initElements(driver, EngagementReport.class);
            assignments = PageFactory.initElements(driver, Assignments.class);
            proficiencyReport=PageFactory.initElements(driver,ProficiencyReport.class);
            mostChallengingReport=PageFactory.initElements(driver,MostChallengingReport.class);
            dashboard=PageFactory.initElements(driver,Dashboard.class);
            productivityReport = PageFactory.initElements(driver, ProductivityReport.class);


            new LoginUsingLTI().ltiLogin("1");//login as instructor
            ReportUtil.log("Instructor Login", "Instructor logged in successfully", "Pass");

            actual=dashboard.getQuestionAttempted().getText().trim();
            expected="6";
            CustomAssert.assertEquals(actual, expected, "Verify Question Attempted Number in dashboard", "Question Attempted Number is " + expected + "", "Question Attempted Number is not " + expected + "");

            actual = dashboard.questionPerformanceInPercentage.getText().trim();
            expected  = "47%";
            CustomAssert.assertEquals(actual, expected, "Verify Question performance in dashboard", "Question performance is displaying as " + expected + "", "Question performance is not displaying as "+expected+"");

            String overallScoreAfterDueDate=  proficiencyReport.courseProficiencys.get(1).getText().trim();
            String overallPercentageAfterDueDate=  proficiencyReport.courseProficiencys.get(2).getText().trim();
            try {
                actual = overallScoreAfterDueDate+overallPercentageAfterDueDate;
                expected  = "47%";

                CustomAssert.assertEquals(actual, expected, "Verify overall score in dashboard", "overall score is displaying as " + expected + "", "overall score is not displaying as " + expected + "");
            }
            catch (Throwable e){

                actual = overallScoreAfterDueDate+overallPercentageAfterDueDate;
                expected  = "Performance47";
                CustomAssert.assertEquals(actual, expected, "Verify overall score in dashboard", "overall score is displaying as " + expected + "", "overall score is not displaying as " + expected + "");

            }
            String heightAfterDueDate=dashboard.getGradedBarChart().getAttribute("height");
            int recentlyGradedAfterDueDate= Integer.parseInt(heightAfterDueDate);
            CustomAssert.assertTrue(recentlyGradedAfterDueDate>0,"Verify Recently Graded graph","Recently Graded graph is generated","Recently Graded graph is not generated");

            new Navigator().NavigateTo("Most Challenging Activities Report"); //navigate to Most Challenging Activities Report
            ReportUtil.log("Most Challenging Activities Report", "Navigated to Most Challenging Activities Report", "Pass");

            String mostProficiencyByChapterAfterDueDate=mostChallengingReport.getChapProficiency().get(0).getText().trim();
            CustomAssert.assertTrue(mostProficiencyByChapterAfterDueDate.equals("100%")||mostProficiencyByChapterAfterDueDate.equals("99%"), "Verify Most Challenging proficiency By Chapter", "Most Challenging proficiency By Chapter is displaying correctly", "Most Challenging proficiency By Chapter is empty.");

            String mostPerformanceByChapterAfterDueDate=mostChallengingReport.getChapPerformance().get(0).getText().trim();
            CustomAssert.assertTrue(mostPerformanceByChapterAfterDueDate.equals("6/12"), "Verify Most Challenging performance By Chapter", "Most Challenging performance By Chapter is displaying correctly", "Most Challenging performance By Chapter is empty.");

            mostChallengingReport.getViewByObjective_Tab().click();//click on the view Objective tab

            String mostObjectiveProficiencyAfterDueDate=mostChallengingReport.getChapProficiency().get(1).getText().trim();
            CustomAssert.assertTrue(mostObjectiveProficiencyAfterDueDate.equals("99%"), "Verify Most Challenging proficiency By Objective", "Most Challenging proficiency By Objective is displaying correctly", "Most Challenging proficiency By Objective is empty.");

            String mostPerformanceByObjectiveAfterDueDate=mostChallengingReport.getChapPerformance().get(1).getText().trim();
            CustomAssert.assertTrue(mostPerformanceByObjectiveAfterDueDate.equals("6/12"), "Verify Most Challenging performance By Objective", "Most Challenging proficiency By performance is displaying correctly", "Most Challenging performance By Objective is empty.");

            new Navigator().NavigateTo("Proficiency Report"); //Navigate to Proficiency Report
            ReportUtil.log("Proficiency Report", "Navigated to Proficiency Report", "Pass");

            String proficiencyBar=proficiencyReport.proficiencyBar.get(0).getAttribute("height");
            int chapterProficiencyBarChartHeight = Integer.parseInt(proficiencyBar);
            CustomAssert.assertTrue(chapterProficiencyBarChartHeight > 230, "Verify Class proficiency by chapter performance bar", "Class proficiency by chapter performance bar has generated","Class proficiency by chapter performance bar has not generated");

            List<WebElement> tloProficiency = mostChallengingReport.getChapProficiency();
            if(tloProficiency.get(0).getText().trim().equals("99%")&& tloProficiency.get(1).getText().trim().equals("99%")){
                ReportUtil.log("Verify Class Proficiency By Students section","Class Proficiency By Students proficiency is displaying correct","pass");
            }
            else {
                CustomAssert.fail("Verify Class Proficiency By Students section ", "Class Proficiency By Students proficiency is not expected");

            }

            String avgPerformanceStudent1Num = proficiencyReport.getTloChapterQuestionPerformance().get(0).getText();
            String avgPerformanceStudent1Den = proficiencyReport.getTloQuestionPerformance().get(0).getText();
            String avgPerformanceStud1 = avgPerformanceStudent1Num+"/"+avgPerformanceStudent1Den;
            CustomAssert.assertEquals(avgPerformanceStud1,"24/24", "Verify Class proficiency by chapter performance", "Class proficiency by chapter performance for student1 is available.","Class proficiency by chapter performance for student1 is not available.");

            String avgPerformanceStudent2Num = proficiencyReport.getTloChapterQuestionPerformance().get(1).getText();
            String avgPerformanceStudent2Den = proficiencyReport.getTloQuestionPerformance().get(1).getText();
            String avgPerformanceStud2 = avgPerformanceStudent2Num+"/"+avgPerformanceStudent2Den;
            CustomAssert.assertEquals(avgPerformanceStud2,"10/24", "Verify Class proficiency by chapter performance", "Class proficiency by chapter performance for student2 is available.","Class proficiency by chapter performance for student2 is not available.");

            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",proficiencyReport.getChapNumber());
            Thread.sleep(3000);

            String proficiencyBarByObjectives=proficiencyReport.proficiencyBar.get(0).getAttribute("height");
            CustomAssert.assertEquals(proficiencyBarByObjectives,"256", "Verify Class proficiency by objectives performance bar", "Class proficiency by objectives performance bar has generated","Class proficiency by objectives performance bar has not generated");

            List<WebElement> tloProficiency1 = mostChallengingReport.getChapProficiency();
            if((tloProficiency1.get(0).getText().trim().equals("99%")||tloProficiency1.get(0).getText().trim().equals("100%"))&& (tloProficiency1.get(1).getText().trim().equals("99%")||tloProficiency1.get(1).getText().trim().equals("100%"))){
                ReportUtil.log("Verify Class Proficiency By Students section","Class Proficiency By Students proficiency is displaying correct","pass");
            }
            else {
                CustomAssert.fail("Verify Class Proficiency By Students section ", "Class Proficiency By Students proficiency is not expected");

            }

            String avgChapterPerformanceStudent1Num = proficiencyReport.getTloChapterQuestionPerformance().get(0).getText();
            String avgChapterPerformanceStud1 = avgChapterPerformanceStudent1Num+"/"+"24";
            CustomAssert.assertEquals(avgChapterPerformanceStud1,"24/24", "Verify Class proficiency by objectives performance", "Class proficiency by objectives performance for student1 is available.","Class proficiency by objectives performance for student1 is not available.");

            String avgChapterPerformanceStudent2Num = proficiencyReport.getTloChapterQuestionPerformance().get(1).getText();
            String avgChapterPerformanceStud2 = avgChapterPerformanceStudent2Num+"/"+"24";
            CustomAssert.assertEquals(avgChapterPerformanceStud2,"10/24", "Verify Class proficiency by objectives performance", "Class proficiency by objectives performance for student2 is available.","Class proficiency by objectives performance for student2 is not available.");

            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",proficiencyReport.getTloNumber());
            Thread.sleep(3000);

            int ClassPerformanceByQuestions=proficiencyReport.proficiencyBar.size();
            System.out.println("ClassPerformanceByQuestions:"+ClassPerformanceByQuestions);
            CustomAssert.assertTrue(ClassPerformanceByQuestions==12,"Verify Class Performance by Questions graph","Class Performance by Questions bar chart is generated correctly","Class Performance by Questions bar chart is not generated correctly");

            List<WebElement> tloProficiency2 = mostChallengingReport.getChapProficiency();
            if(tloProficiency2.get(0).getText().trim().equals("99%")&& tloProficiency2.get(1).getText().trim().equals("99%")){
                ReportUtil.log("Verify Class Proficiency By Students section","Class Proficiency By Students proficiency is displaying correct","pass");
            }
            else {
                CustomAssert.fail("Verify Class Proficiency By Students section ", "Class Proficiency By Students proficiency is not expected");

            }

            String avgQuestionPerformanceStudent1Num = proficiencyReport.performanceByQuestion.get(0).getText().trim();
            CustomAssert.assertEquals(avgQuestionPerformanceStudent1Num,"24 / 24", "Verify Class proficiency by question performance", "Class proficiency by question performance for student1 is available.","Class proficiency by question performance for student1 is not available.");

            String avgQuestionPerformanceStudent2Num = proficiencyReport.performanceByQuestion.get(1).getText().trim();
            CustomAssert.assertEquals(avgQuestionPerformanceStudent2Num, "10 / 24", "Verify Class proficiency by question performance", "Class proficiency by question performance for student1 is available.", "Class proficiency by question performance for student1 is not available.");

            new Navigator().NavigateTo("Engagement Report");
            ReportUtil.log("Engagement Report", "Navigated to Engagement Report", "Pass");

            actual = engagementReport.questionPerformance_Count.getText().trim();
            expected = "36"; //No of Question
            CustomAssert.assertEquals(actual, expected, "Verify Question Number of Question Performance Summary", "Question Number of Question Performance Summary  is " + expected + "", "Question Number of Question Performance Summary is not " + expected + "");
            int sum = 0;
            for (int i = 2; i <=3; i++) {
                Thread.sleep(1000);
                sum += Integer.parseInt(engagementReport.questionProficiency.get(i).getText().trim().substring(0, engagementReport.questionProficiency.get(i).getText().indexOf("%")));

            }
            actual_int = sum;
            expected_int = 100;
            CustomAssert.assertEquals(actual_int, expected_int, "Verify Question performance in Engagement Report", "Question performance is displaying as " + expected_int + "", "Question performance is not displaying as " + expected_int + "");

            List<WebElement> grades = engagementReport.studTotalGrades;
            if (grades.get(0).getText().trim().equals("42.0%") && grades.get(1).getText().trim().equals("100.0%")&&grades.get(2).getText().trim().equals("0.0%")) {
                ReportUtil.log("Verify Total grade", "Total grade is displaying correct", "pass");
            } else {
                CustomAssert.fail("Total grade", "Total grade is displaying correct is not expected");

            }


            new Navigator().NavigateTo("Gradebook"); //navigate to Gradebook
            ReportUtil.log("Gradebook", "Navigated to Gradebook", "Pass");

            gradebook.basicView.click();

            List<WebElement> grade=gradebook.gradebookWeight;
            if(grade.get(4).getText().trim().equals("10/24")&& grade.get(9).getText().trim().equals("24/24")&& grade.get(14).getText().trim().equals("0/24")){
                ReportUtil.log("Verify assignment grade of each student","Assignment grade of each student is displaying correct","pass");
            }
            else {
                CustomAssert.fail("Verify assignment grade of each student", "Assignment grade of each student is not expected");

            }
            List<WebElement> overallScore=gradebook.getOverAllScore();
            if(overallScore.get(1).getText().trim().equals("42%")&& overallScore.get(2).getText().trim().equals("100%")&& overallScore.get(3).getText().trim().equals("0%")){
                ReportUtil.log("Verify overall score of each student","overall score of each student is displaying correct","pass");
            }
            else {
                CustomAssert.fail("overall score grade of each student", "overall score of each student is not expected");

            }

            new Navigator().NavigateTo("Productivity Report"); //Navigate to Productivity Report
            ReportUtil.log("Productivity Report", "Navigated to Productivity Report", "Pass");
            List<WebElement> profReport4 = productivityReport.insProficiency;
            if (profReport4.get(0).getText().trim().equals("99%") && profReport4.get(1).getText().trim().equals("99%")) {
                ReportUtil.log("Verify Proficiency", "Proficiency for each student is displaying correct", "pass");
            } else {
                CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

            }
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            if ( productivityReport.getStudProficiency().getText().equals("99%")||productivityReport.getStudProficiency().getText().equals("100%")){
                ReportUtil.log("Verify Proficiency", "Proficiency for each student is displaying correct", "pass");

            }else {
                CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");
            }

            new Navigator().NavigateTo("Metacognitive Report"); //Navigate to Metacognitive Report
            ReportUtil.log("Metacognitive Report", "Navigated to Metacognitive Report", "Pass");
            List<WebElement> profReport5 = productivityReport.insProficiency;
            if (profReport5.get(0).getText().trim().equals("99%") && profReport5.get(1).getText().trim().equals("99%")) {
                ReportUtil.log("Verify Proficiency", "Proficiency for each student is displaying correct", "pass");
            } else {
                CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

            }
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            if ( productivityReport.getStudProficiency().getText().equals("99%")||productivityReport.getStudProficiency().getText().equals("100%")){
                ReportUtil.log("Verify Proficiency", "Proficiency for each student is displaying correct", "pass");

            }else {
                CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");
            }


            new LoginUsingLTI().ltiLogin("1_1");//login as student1
            ReportUtil.log("Student1 Login", "Student1 logged in successfully", "Pass");

            actual = dashboard.getQuestionAttempted().getText().trim();
            expected = "12";
            CustomAssert.assertEquals(actual, expected, "Verify Question Attempted Number in dashboard", "Question Attempted Number is " + expected + "", "Question Attempted Number is not " + expected + "");

            actual = dashboard.questionPerformance.getText().trim();
            expected = "100\n%";
            CustomAssert.assertEquals(actual, expected, "Verify Question performance in dashboard", "Question performance is displaying as " + expected + "", "Question performance is not displaying as " + expected + "");

            String overallScore1 = proficiencyReport.courseProficiencys.get(1).getText().trim();
            String overallPercentage = proficiencyReport.courseProficiencys.get(2).getText().trim();

            actual = overallScore1 + overallPercentage;
            expected = "100%";
            CustomAssert.assertEquals(actual, expected, "Verify overall score in dashboard", "overall score is displaying as " + expected + "", "overall score is not displaying as " + expected + "");

            String height = dashboard.getGradedBarChart().getAttribute("height");
            int recentlyGraded = Integer.parseInt(height);
            CustomAssert.assertTrue(recentlyGraded > 0, "Verify Recently Graded graph", "Recently Graded graph is generated", "Recently Graded graph is not generated");

            new Navigator().NavigateTo("Most Challenging Activities Report"); //navigate to Most Challenging Activities Report
            ReportUtil.log("Most Challenging Activities Report", "Navigated to Most Challenging Activities Report", "Pass");

            String mostProficiencyByChapter = mostChallengingReport.getStudChapProficiency().get(0).getText().trim();
            CustomAssert.assertTrue(mostProficiencyByChapter.equals("99%")||mostProficiencyByChapter.equals("100%"), "Verify Most Challenging proficiency By Chapter", "Most Challenging proficiency By Chapter is displaying correctly", "Most Challenging proficiency By Chapter is empty.");

            String mostPerformanceByChapter = mostChallengingReport.getStudChapPerformance().get(0).getText().trim();
            CustomAssert.assertTrue(mostPerformanceByChapter.equals("24/24"), "Verify Most Challenging performance By Chapter", "Most Challenging performance By Chapter is displaying correctly", "Most Challenging performance By Chapter is empty.");

            mostChallengingReport.getStudViewObjective_Tab().click();//click on the view Objective tab

            String mostObjectiveProficiency = mostChallengingReport.getStudChapProficiency().get(1).getText().trim();
            CustomAssert.assertTrue(mostObjectiveProficiency.equals("99%"), "Verify Most Challenging proficiency By Objective", "Most Challenging proficiency By Objective is displaying correctly", "Most Challenging proficiency By Objective is empty.");

            String mostPerformanceByObjective = mostChallengingReport.getStudChapPerformance().get(1).getText().trim();
            CustomAssert.assertTrue(mostPerformanceByObjective.equals("24/24"), "Verify Most Challenging performance By Objective", "Most Challenging proficiency By performance is displaying correctly", "Most Challenging performance By Objective is empty.");

            new Navigator().NavigateTo("Proficiency Report"); //Navigate to Proficiency Report
            ReportUtil.log("Proficiency Report", "Navigated to Proficiency Report", "Pass");
            Thread.sleep(2000);
            new TopicOpen().filterChapterInProficiencyReport(1, 1);
            String chapterProficiency = proficiencyReport.courseProficiencys.get(0).getText().trim();
            String chapterProficiencyPercentage = proficiencyReport.courseProficiencys.get(1).getText().trim();
            actual = chapterProficiency + chapterProficiencyPercentage;
            if(actual.equals("100%")){
                expected = "100%";
                CustomAssert.assertEquals(actual, expected, "Verify Chapter Proficiency Summary", "Chapter Proficiency Summary is displaying as " + expected + "", "Chapter Proficiency Summary is not displaying as " + expected + "");
            }
            else {
                expected = "99%";
                CustomAssert.assertEquals(actual, expected, "Verify Chapter Proficiency Summary", "Chapter Proficiency Summary is displaying as " + expected + "", "Chapter Proficiency Summary is not displaying as " + expected + "");

            }

            new Navigator().NavigateTo("Productivity Report"); //Navigate to Productivity Report
            ReportUtil.log("Productivity Report", "Navigated to Productivity Report", "Pass");

            if(productivityReport.getStudProfPercentage().getText().equals("99%")||productivityReport.getStudProfPercentage().getText().equals("100%")){
                ReportUtil.log("Proficiency of student", "Proficiency of student is not displaying", "Proficiency of student is displaying");}
            else{
                CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

            }
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            if(productivityReport.getStudProfPercentage().getText().equals("99%")||productivityReport.getStudProfPercentage().getText().equals("100%")){
                ReportUtil.log("Verify Proficiency of student", "Proficiency of student is not displaying", "Proficiency of student is displaying");}
            else{
                CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

            }
            new Navigator().NavigateTo("Metacognitive Report"); //Navigate to Metacognitive Report
            ReportUtil.log("Metacognitive Report", "Navigated to Metacognitive Report", "Pass");

            if(productivityReport.getStudProfPercentage().getText().equals("99%")||productivityReport.getStudProfPercentage().getText().equals("100%")){
                ReportUtil.log("Verify Proficiency of student", "Proficiency of student is not displaying", "Proficiency of student is displaying");}
            else{
                CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

            }
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            if(productivityReport.getStudProfPercentage().getText().equals("99%")||productivityReport.getStudProfPercentage().getText().equals("100%")){
                ReportUtil.log("Proficiency of student", "Proficiency of student is not displaying", "Proficiency of student is displaying");}
            else{
                CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

            }

            new LoginUsingLTI().ltiLogin("1_2");//login as student2
            ReportUtil.log("Student2 Login", "Student2 logged in successfully", "Pass");

            actual = dashboard.getQuestionAttempted().getText().trim();
            expected = "5";
            CustomAssert.assertEquals(actual, expected, "Verify Question Attempted Number in dashboard", "Question Attempted Number is " + expected + "", "Question Attempted Number is not " + expected + "");

            actual = dashboard.questionPerformance.getText().trim();
            expected = "42\n%";
            CustomAssert.assertEquals(actual, expected, "Verify Question performance in dashboard", "Question performance is displaying as " + expected + "", "Question performance is not displaying as " + expected + "");

            String overallScore2 = proficiencyReport.courseProficiencys.get(1).getText().trim();
            String overallPercentage1 = proficiencyReport.courseProficiencys.get(2).getText().trim();

            actual = overallScore2 + overallPercentage1;
            expected = "42%";
            CustomAssert.assertEquals(actual, expected, "Verify overall score in dashboard", "overall score is displaying as " + expected + "", "overall score is not displaying as " + expected + "");

            String height1 = dashboard.getGradedBarChart().getAttribute("height");
            int recentlyGraded1 = Integer.parseInt(height1);
            CustomAssert.assertTrue(recentlyGraded1 > 0, "Verify Recently Graded graph", "Recently Graded graph is generated", "Recently Graded graph is not generated");


            new Navigator().NavigateTo("Most Challenging Activities Report"); //navigate to Most Challenging Activities Report
            ReportUtil.log("Most Challenging Activities Report", "Navigated to Most Challenging Activities Report", "Pass");

            String mostProficiencyByChapter1 = mostChallengingReport.getStudChapProficiency().get(0).getText().trim();
            CustomAssert.assertTrue(mostProficiencyByChapter1.equals("99%")||mostProficiencyByChapter1.equals("100%"), "Verify Most Challenging proficiency By Chapter", "Most Challenging proficiency By Chapter is displaying correctly", "Most Challenging proficiency By Chapter is empty.");

            String mostPerformanceByChapter1 = mostChallengingReport.getStudChapPerformance().get(0).getText().trim();
            CustomAssert.assertTrue(mostPerformanceByChapter1.equals("10/24"), "Verify Most Challenging performance By Chapter", "Most Challenging performance By Chapter is displaying correctly", "Most Challenging performance By Chapter is empty.");

            mostChallengingReport.getStudViewObjective_Tab().click();//click on the view Objective tab

            String mostObjectiveProficiency1 = mostChallengingReport.getStudChapProficiency().get(1).getText().trim();
            CustomAssert.assertTrue(mostObjectiveProficiency1.equals("99%"), "Verify Most Challenging proficiency By Objective", "Most Challenging proficiency By Objective is displaying correctly", "Most Challenging proficiency By Objective is empty.");

            String mostPerformanceByObjective1 = mostChallengingReport.getStudChapPerformance().get(1).getText().trim();
            CustomAssert.assertTrue(mostPerformanceByObjective1.equals("10/24"), "Verify Most Challenging performance By Objective", "Most Challenging proficiency By performance is displaying correctly", "Most Challenging performance By Objective is empty.");

            new Navigator().NavigateTo("Proficiency Report"); //Navigate to Proficiency Report
            ReportUtil.log("Proficiency Report", "Navigated to Proficiency Report", "Pass");

            new TopicOpen().filterChapterInProficiencyReport(1, 1);
            String chapterProficiency1 = proficiencyReport.courseProficiencys.get(0).getText().trim();
            String chapterProficiencyPercentage1 = proficiencyReport.courseProficiencys.get(1).getText().trim();

            actual = chapterProficiency1 + chapterProficiencyPercentage1;
            if(actual.equals("100%")){
                expected = "100%";
                CustomAssert.assertEquals(actual, expected, "Verify Chapter Proficiency Summary", "Chapter Proficiency Summary is displaying as " + expected + "", "Chapter Proficiency Summary is not displaying as " + expected + "");
            }
            else {
                expected = "99%";
                CustomAssert.assertEquals(actual, expected, "Verify Chapter Proficiency Summary", "Chapter Proficiency Summary is displaying as " + expected + "", "Chapter Proficiency Summary is not displaying as " + expected + "");

            }

            new Navigator().NavigateTo("Productivity Report"); //Navigate to Productivity Report
            ReportUtil.log("Productivity Report", "Navigated to Productivity Report", "Pass");

            if(productivityReport.getStudProfPercentage().getText().equals("99%")||productivityReport.getStudProfPercentage().getText().equals("100%")){
                ReportUtil.log("Verify Proficiency of student", "Proficiency of student is not displaying", "Proficiency of student is displaying");}
            else{
                CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

            }
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            if(productivityReport.getStudProfPercentage().getText().equals("99%")||productivityReport.getStudProfPercentage().getText().equals("100%")){
                ReportUtil.log("Verify Proficiency of student", "Proficiency of student is not displaying", "Proficiency of student is displaying");}
            else{
                CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

            }

            new Navigator().NavigateTo("Metacognitive Report"); //Navigate to Metacognitive Report
            ReportUtil.log("Metacognitive Report", "Navigated to Metacognitive Report", "Pass");

            if(productivityReport.getStudProfPercentage().getText().equals("99%")||productivityReport.getStudProfPercentage().getText().equals("100%")){
                ReportUtil.log("Verify Proficiency of student", "Proficiency of student is not displaying", "Proficiency of student is displaying");}
            else{
                CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

            }
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            if(productivityReport.getStudProfPercentage().getText().equals("99%")||productivityReport.getStudProfPercentage().getText().equals("100%")){
                ReportUtil.log("Verify Proficiency of student", "Proficiency of student is not displaying", "Proficiency of student is displaying");}
            else{
                CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

            }

            new LoginUsingLTI().ltiLogin("1_3");//login as student3
            ReportUtil.log("Student3 Login", "Student3 logged in successfully", "info");

            actual = dashboard.getQuestionAttempted().getText().trim();
            expected = "0";
            CustomAssert.assertEquals(actual, expected, "Verify Question Attempted Number in dashboard", "Question Attempted Number is " + expected + "", "Question Attempted Number is not " + expected + "");

            actual = dashboard.questionPerformance.getText().trim();
            expected = "0\n%";
            CustomAssert.assertEquals(actual, expected, "Verify Question performance in dashboard", "Question performance is displaying as " + expected + "", "Question performance is not displaying as " + expected + "");

            String overallScore3 = proficiencyReport.courseProficiencys.get(1).getText().trim();
            String overallPercentage2 = proficiencyReport.courseProficiencys.get(2).getText().trim();

            actual = overallScore3 + overallPercentage2;
            expected = "0%";
            CustomAssert.assertEquals(actual, expected, "Verify overall score in dashboard", "overall score is displaying as " + expected + "", "overall score is not displaying as " + expected + "");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            ReportUtil.log("Assignments", "Navigated to Assignments Report", "Pass");

            String score = assignments.getScore().getText();
            CustomAssert.assertEquals(score, "Score (0/24)", "Verify student status in Assignment page", "student status is displaying as " + score + "", "student status is not displaying as " + score + "");

            Assert.assertEquals(assignments.completed_count.get(0).getText().trim(), "1 Graded");
            Assert.assertEquals(assignments.completed_count.get(1).getText().trim(), "0 Reviewed");
        }
        catch (Exception e) {
            Assert.fail("Exception in test case AllReportsForNotStartedStudentAfterDueDate of class PolicyFour", e);
        }
    }

}