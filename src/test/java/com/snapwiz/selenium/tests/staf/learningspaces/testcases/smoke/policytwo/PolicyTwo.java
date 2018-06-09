package com.snapwiz.selenium.tests.staf.learningspaces.testcases.smoke.policytwo;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Gradebook.Gradebook;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.List;

/**
 * Created by mukesh on 10/2/16.
 */
public class PolicyTwo extends Driver {

   String actual = "";
   String expected = "";
   int actual_int=0;
   int expected_int=0;
   String height ="";
   int recentlyGraded=0;
   String mostProficiencyByChapter="";
   String mostPerformanceByChapter="";
   String mostObjectiveProficiency="";
   String mostPerformanceByObjective="";
   @Test(priority = 1, enabled = true)
   public void dashboardQuestionPerformance() {
      WebDriver driver=Driver.getWebDriver();
      try {
         Dashboard dashboard;
         Assignments assignments;
         ProficiencyReport proficiencyReport;
         MostChallengingReport mostChallengingReport;
         PerformanceSummaryReport performanceSummaryReport;
         CurrentAssignments currentAssignments;
         ProductivityReport productivityReport;
         productivityReport = PageFactory.initElements(driver, ProductivityReport.class);

         dashboard = PageFactory.initElements(driver, Dashboard.class);
         performanceSummaryReport = PageFactory.initElements(driver, PerformanceSummaryReport.class);
         currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
         EngagementReport engagementReport;
         engagementReport = PageFactory.initElements(driver, EngagementReport.class);
         Gradebook gradebook;
         gradebook=PageFactory.initElements(driver,Gradebook.class);



         String assignmentPolicyName = ReadTestData.readDataByTagName("", "assignmentpolicyname", "1");

         ReportUtil.log("Create Question", "Create 10 questions of different type", "info");

         new Assignment().create(1);
         for (int i = 0; i < 4; i++)
            new Assignment().addQuestions(1, "truefalse", "");

         for (int i = 5; i < 6; i++)
            new Assignment().addQuestions(1, "multiplechoice", "");

         for (int i = 7; i < 9; i++)
            new Assignment().addQuestions(1, "multipleselection", "");

         for (int i = 10; i < 12; i++)
            new Assignment().addQuestions(1, "textentry", "");


         ReportUtil.log("Student Login", "Login as student one", "info");
         new LoginUsingLTI().ltiLogin("2");//login as student 1

         ReportUtil.log("Student Login", "Login as student two", "info");
         new LoginUsingLTI().ltiLogin("3");//login as student 2

         ReportUtil.log("Student Login", "Login as student three", "info");
         new LoginUsingLTI().ltiLogin("4");//login as student 3


         ReportUtil.log("Instructor Login", "Login as instructor", "info");
         new LoginUsingLTI().ltiLogin("1");//login as instructor
         new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
         new AssignmentPolicy().createAssignmentPolicy(assignmentPolicyName, "Policy description", "2", null, false, "1", "", "Auto-release on due date", "", "", "", "1");

         new Navigator().NavigateTo("Assignments");//navigate to Assignments
         new Assignment().assignAssignmentWithDueDate(1);

         StopWatch stopWatch = new StopWatch();
         stopWatch.start();
         ReportUtil.log("Student Login", "Login as student one", "info");
         new LoginUsingLTI().ltiLogin("2");//login as student 1
         new Assignment().submitAssignmentAsStudent(1); //submit assignment

         actual = performanceSummaryReport.chartTitle.getText().trim();
         expected = "Performance Summary";
         CustomAssert.assertEquals(actual, expected, "Verify Performance Summary page", "Student is navigated to " + expected + " page", "Student is not navigated to " + expected + " page");

         String questionNo = performanceSummaryReport.performanceChart_questionNo.getText().trim();
         CustomAssert.assertEquals(questionNo, "10\nQuestions", "Verify Question Number", "Attempted Number of Question should be  " + questionNo + "", "Attempted Number of Question not displaying correctly");

         ReportUtil.log("Student Dashboard", "Navigate to student dashboard", "info");
         new Navigator().NavigateTo("Dashboard");
         actual = dashboard.getQuestionAttempted().getText().trim();
         expected = "10";
         CustomAssert.assertEquals(actual, expected, "Verify Question Attempted Number in dashboard", "Question Attempted Number is " + expected + "", "Question Attempted Number is not " + expected + "");

         new LoginUsingLTI().ltiLogin("1");//login as instructor
         new Navigator().NavigateTo("Gradebook"); //navigate to Gradebook
         gradebook.basicView.click();

         List<WebElement> grade=gradebook.getOverAllScore();
         int count=0;
         for (int i = 1; i <=grade.size()-1 ; i++) {
            if(!grade.get(i).getText().equals("NA")){
               count++;
               break;
            }
         }

         if (count>0) {
            CustomAssert.fail("Verify Assignment grade of each student", "Assignment grade of each student is not \"NA\"");
         }

         List<WebElement> weight=gradebook.gradebookWeight;
         if(weight.get(4).getText().trim().equals("Grades Not Released")&& weight.get(9).getText().trim().equals("Grades Not Released")&& weight.get(14).getText().trim().equals("Grades Not Released")){
            ReportUtil.log("Verify assignment grade of each student","Assignment grade of each student is displaying correct","pass");
         }
         else {
            CustomAssert.fail("Verify assignment grade of each student", "Assignment grade of each student is not expected");

         }


         ReportUtil.log("Student Login", "Login as student two", "info");
         new LoginUsingLTI().ltiLogin("3");//login as student 2
         new Navigator().NavigateTo("Assignments");  //navigate to Assignments
         currentAssignments.getAssessmentName().click();
         new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
         for (int i = 0; i < 5; i++) {
            new AttemptQuestion().trueFalse(false, "correct", 1);
            new Assignment().nextButtonInQuestionClick();
         }

         new AttemptQuestion().multipleChoice(false, "incorrect", 1);
         new Assignment().nextButtonInQuestionClick();

         ReportUtil.log("Student Dashboard", "Navigate to student dashboard", "info");
         new Navigator().NavigateTo("Dashboard");
         actual = dashboard.getQuestionAttempted().getText().trim();
         expected = "0";
         CustomAssert.assertEquals(actual, expected, "Verify Question Attempted Number in dashboard", "Question Attempted Number is " + expected + "", "Question Attempted Number is not " + expected + "");

         ReportUtil.log("Student Login", "Login as student three", "info");
         new LoginUsingLTI().ltiLogin("4");//login as student 3

         stopWatch.stop();
         System.out.println("time:" + stopWatch);

         Thread.sleep(300000); //wait for 5 minute for due to expiry

         new RunScheduledJobs().runScheduledJob("AutoSubmitAssignmentJob");
         new RunScheduledJobs().runScheduledJob("ClassSectionActivityJob");
         new RunScheduledJobs().runScheduledJob("ClassSectionPerformanceJob");


         driver=new ReInitDriver().startDriver("firefox");

         assignments = PageFactory.initElements(driver, Assignments.class);
         dashboard = PageFactory.initElements(driver, Dashboard.class);
         proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);
         mostChallengingReport = PageFactory.initElements(driver, MostChallengingReport.class);
         productivityReport = PageFactory.initElements(driver, ProductivityReport.class);
         ReportUtil.log("Student Login", "Login as student three", "info");

         new LoginUsingLTI().ltiLogin("4");//login as student 3
         actual = dashboard.getQuestionAttempted().getText().trim();
         expected = "0";
         CustomAssert.assertEquals(actual, expected, "Verify Question Attempted Number in dashboard", "Question Attempted Number is " + expected + "", "Question Attempted Number is not " + expected + "");

         actual = dashboard.questionPerformance.getText().trim();
         expected = "0\n%";
         CustomAssert.assertEquals(actual, expected, "Verify Question performance in dashboard", "Question performance is displaying as " + expected + "", "Question performance is not displaying as " + expected + "");

         String overallScore1 = proficiencyReport.courseProficiencys.get(1).getText().trim();
         String overallPercentage = proficiencyReport.courseProficiencys.get(2).getText().trim();

         actual = overallScore1 + overallPercentage;
         expected = "0%";
         CustomAssert.assertEquals(actual, expected, "Verify overall score in dashboard", "overall score is displaying as " + expected + "", "overall score is not displaying as " + expected + "");

         new Navigator().NavigateTo("Assignments");//navigate to Assignments
         String score = assignments.getScore().getText();
         CustomAssert.assertEquals(score, "Score (0/20)", "Verify student status in Assignment page", "student status is displaying as " + score + "", "student status is not displaying as " + score + "");

         Assert.assertEquals(assignments.completed_count.get(0).getText().trim(), "1 Graded");
         Assert.assertEquals(assignments.completed_count.get(1).getText().trim(), "0 Reviewed");




         new LoginUsingLTI().ltiLogin("3");//login as student 2
         actual = dashboard.getQuestionAttempted().getText().trim();
         expected = "6";
         CustomAssert.assertEquals(actual, expected, "Verify Question Attempted Number in dashboard", "Question Attempted Number is " + expected + "", "Question Attempted Number is not " + expected + "");

         actual = dashboard.questionPerformance.getText().trim();
         expected = "50\n%";
         CustomAssert.assertEquals(actual, expected, "Verify Question performance in dashboard", "Question performance is displaying as " + expected + "", "Question performance is not displaying as " + expected + "");

         String overallScore2 = proficiencyReport.courseProficiencys.get(1).getText().trim();
         String overallPercentage1 = proficiencyReport.courseProficiencys.get(2).getText().trim();

         actual = overallScore2 + overallPercentage1;
         expected = "50%";
         CustomAssert.assertEquals(actual, expected, "Verify overall score in dashboard", "overall score is displaying as " + expected + "", "overall score is not displaying as " + expected + "");

         height = dashboard.getGradedBarChart().getAttribute("height");
         recentlyGraded = Integer.parseInt(height);
         CustomAssert.assertTrue(recentlyGraded > 0, "Verify Recently Graded graph", "Recently Graded graph is generated", "Recently Graded graph is not generated");
         Thread.sleep(9000);

         new LoginUsingLTI().ltiLogin("3");//login as student 2
         ReportUtil.log("Most Challenging Activities Report", "Navigate to Most Challenging Activities Report", "info");
         new Navigator().NavigateTo("Most Challenging Activities Report"); //navigate to Most Challenging Activities Report
         mostProficiencyByChapter = mostChallengingReport.getStudChapProficiency().get(0).getText().trim();
         System.out.println("Most Challenging proficiency By Chapter:" + mostProficiencyByChapter);
         CustomAssert.assertTrue(mostProficiencyByChapter.equals("95%"), "Verify Most Challenging proficiency By Chapter", "Most Challenging proficiency By Chapter is displaying correctly", "Most Challenging proficiency By Chapter is empty.");

         mostPerformanceByChapter = mostChallengingReport.getStudChapPerformance().get(0).getText().trim();
         System.out.println("Most Challenging performance By Chapter:" + mostPerformanceByChapter);
         CustomAssert.assertTrue(mostPerformanceByChapter.equals("10/20"), "Verify Most Challenging performance By Chapter", "Most Challenging performance By Chapter is displaying correctly", "Most Challenging performance By Chapter is empty.");

         mostChallengingReport.getStudViewObjective_Tab().click();//click on the view Objective tab

         mostObjectiveProficiency = mostChallengingReport.getStudChapProficiency().get(1).getText().trim();
         CustomAssert.assertTrue(mostObjectiveProficiency.equals("95%"), "Verify Most Challenging proficiency By Objective", "Most Challenging proficiency By Objective is displaying correctly", "Most Challenging proficiency By Objective is empty.");

         mostPerformanceByObjective = mostChallengingReport.getStudChapPerformance().get(1).getText().trim();
         CustomAssert.assertTrue(mostPerformanceByObjective.equals("10/20"), "Verify Most Challenging performance By Objective", "Most Challenging proficiency By performance is displaying correctly", "Most Challenging performance By Objective is empty.");


         ReportUtil.log("Proficiency Report", "Navigate to Proficiency Report", "info");
         new Navigator().NavigateTo("Proficiency Report"); //Navigate to Proficiency Report
         new TopicOpen().filterChapterInProficiencyReport(1, 1);
         Thread.sleep(5000);
         String chapterProficiency = proficiencyReport.courseProficiencys.get(0).getText().trim();
         String chapterProficiencyPercentage = proficiencyReport.courseProficiencys.get(1).getText().trim();

         actual = chapterProficiency + chapterProficiencyPercentage;
          if(actual.equals("95%")){
              expected = "95%";
              CustomAssert.assertEquals(actual, expected, "Verify Chapter Proficiency Summary", "Chapter Proficiency Summary is displaying as " + expected + "", "Chapter Proficiency Summary is not displaying as " + expected + "");
          }
          else {
              CustomAssert.assertEquals(actual, expected, "Verify Chapter Proficiency Summary", "Chapter Proficiency Summary is displaying as " + expected + "", "Chapter Proficiency Summary is not displaying as " + expected + "");

          }
         String chapterProficiencyBarChart = proficiencyReport.getBarChart().getAttribute("height");
         int chapterProficiencyBarChartHeight = Integer.parseInt(chapterProficiencyBarChart);
         CustomAssert.assertTrue(chapterProficiencyBarChartHeight > 130, "Verify Chapter Proficiency By Objective ", "Chapter Proficiency By Objective graph is generated", "Chapter Proficiency By Objective graph is not generated");

         new Navigator().NavigateTo("Productivity Report"); //Navigate to Productivity Report
         ReportUtil.log("Productivity Report", "Navigated to Productivity Report", "Pass");
         Thread.sleep(2000);
         if(productivityReport.getStudProfPercentage().getText().equals("96%")||productivityReport.getStudProfPercentage().getText().equals("95%")){
            ReportUtil.log("Proficiency of student", "Proficiency of student is not displaying", "Proficiency of student is displaying");}
         else{
            CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

         }
         new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
         productivityReport.getColoredMarker().click();//click on the colored marker
         Thread.sleep(2000);
         if(productivityReport.getStudProfPercentage().getText().equals("96%")||productivityReport.getStudProfPercentage().getText().equals("95%")){
            ReportUtil.log("Proficiency of student", "Proficiency of student is not displaying", "Proficiency of student is displaying");}
         else{
            CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

         }

         new Navigator().NavigateTo("Metacognitive Report"); //Navigate to Metacognitive Report
         ReportUtil.log("Metacognitive Report", "Navigated to Metacognitive Report", "Pass");

         if(productivityReport.getStudProfPercentage().getText().equals("96%")||productivityReport.getStudProfPercentage().getText().equals("95%")){
            ReportUtil.log("Proficiency of student", "Proficiency of student is not displaying", "Proficiency of student is displaying");}
         else{
            CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

         }
         new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
         productivityReport.getColoredMarker().click();//click on the colored marker
         Thread.sleep(2000);
         if(productivityReport.getStudProfPercentage().getText().equals("96%")||productivityReport.getStudProfPercentage().getText().equals("95%")){
            ReportUtil.log("Proficiency of student", "Proficiency of student is not displaying", "Proficiency of student is displaying");}
         else{
            CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

         }



         new LoginUsingLTI().ltiLogin("2");//login as student 1
         actual = dashboard.getQuestionAttempted().getText().trim();
         expected = "10";
         CustomAssert.assertEquals(actual, expected, "Verify Question Attempted Number in dashboard", "Question Attempted Number is " + expected + "", "Question Attempted Number is not " + expected + "");

         actual = dashboard.questionPerformance.getText().trim();
         expected = "100\n%";
         CustomAssert.assertEquals(actual, expected, "Verify Question performance in dashboard", "Question performance is displaying as " + expected + "", "Question performance is not displaying as " + expected + "");

         String overallScore3 = proficiencyReport.courseProficiencys.get(1).getText().trim();
         String overallPercentage3 = proficiencyReport.courseProficiencys.get(2).getText().trim();

         actual = overallScore3 + overallPercentage3;
         expected = "100%";
         CustomAssert.assertEquals(actual, expected, "Verify overall score in dashboard", "overall score is displaying as " + expected + "", "overall score is not displaying as " + expected + "");

         height = dashboard.getGradedBarChart().getAttribute("height");
         recentlyGraded = Integer.parseInt(height);
         CustomAssert.assertTrue(recentlyGraded > 120, "Verify Recently Graded graph", "Recently Graded graph is generated", "Recently Graded graph is not generated");

         ReportUtil.log("Most Challenging Activities Report", "Navigate to Most Challenging Activities Report", "info");
         new Navigator().NavigateTo("Most Challenging Activities Report"); //navigate to Most Challenging Activities Report
         mostProficiencyByChapter = mostChallengingReport.getStudChapProficiency().get(0).getText().trim();
         System.out.println("Most Challenging proficiency By Chapter:" + mostProficiencyByChapter);
         CustomAssert.assertTrue(mostProficiencyByChapter.equals("99%")||mostProficiencyByChapter.equals("100%"), "Verify Most Challenging proficiency By Chapter", "Most Challenging proficiency By Chapter is displaying correctly", "Most Challenging proficiency By Chapter is empty.");

         mostPerformanceByChapter = mostChallengingReport.getStudChapPerformance().get(0).getText().trim();
         System.out.println("Most Challenging performance By Chapter:" + mostPerformanceByChapter);
         CustomAssert.assertTrue(mostPerformanceByChapter.equals("20/20"), "Verify Most Challenging performance By Chapter", "Most Challenging performance By Chapter is displaying correctly", "Most Challenging performance By Chapter is empty.");

         mostChallengingReport.getStudViewObjective_Tab().click();//click on the view Objective tab

         mostObjectiveProficiency = mostChallengingReport.getStudChapProficiency().get(1).getText().trim();
         CustomAssert.assertTrue(mostObjectiveProficiency.equals("99%"), "Verify Most Challenging proficiency By Objective", "Most Challenging proficiency By Objective is displaying correctly", "Most Challenging proficiency By Objective is empty.");

         mostPerformanceByObjective = mostChallengingReport.getStudChapPerformance().get(1).getText().trim();
         CustomAssert.assertTrue(mostPerformanceByObjective.equals("20/20"), "Verify Most Challenging performance By Objective", "Most Challenging proficiency By performance is displaying correctly", "Most Challenging performance By Objective is empty.");


         ReportUtil.log("Proficiency Report", "Navigate to Proficiency Report", "info");
         new Navigator().NavigateTo("Proficiency Report"); //Navigate to Proficiency Report
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

          String chapterProficiencyBarChart1 = proficiencyReport.getBarChart().getAttribute("height");
         int chapterProficiencyBarChartHeight1 = Integer.parseInt(chapterProficiencyBarChart1);
         CustomAssert.assertTrue(chapterProficiencyBarChartHeight1 > 130, "Verify Chapter Proficiency By Objective ", "Chapter Proficiency By Objective graph is generated", "Chapter Proficiency By Objective graph is not generated");

         new Navigator().NavigateTo("Productivity Report"); //Navigate to Productivity Report
         ReportUtil.log("Productivity Report", "Navigated to Productivity Report", "Pass");

         if(productivityReport.getStudProfPercentage().getText().equals("100%")||productivityReport.getStudProfPercentage().getText().equals("99%")){
            ReportUtil.log("Proficiency of student", "Proficiency of student is not displaying", "Proficiency of student is displaying");}
         else{
            CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

         }
         new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
         productivityReport.getColoredMarker().click();//click on the colored marker
         Thread.sleep(2000);
         if(productivityReport.getStudProfPercentage().getText().equals("100%")||productivityReport.getStudProfPercentage().getText().equals("99%")){
            ReportUtil.log("Proficiency of student", "Proficiency of student is not displaying", "Proficiency of student is displaying");}
         else{
            CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

         }

         new Navigator().NavigateTo("Metacognitive Report"); //Navigate to Metacognitive Report
         ReportUtil.log("Metacognitive Report", "Navigated to Metacognitive Report", "Pass");

         if(productivityReport.getStudProfPercentage().getText().equals("100%")||productivityReport.getStudProfPercentage().getText().equals("99%")){
            ReportUtil.log("Proficiency of student", "Proficiency of student is not displaying", "Proficiency of student is displaying");}
         else{
            CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

         }
         new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
         productivityReport.getColoredMarker().click();//click on the colored marker
         Thread.sleep(2000);
         if(productivityReport.getStudProfPercentage().getText().equals("100%")||productivityReport.getStudProfPercentage().getText().equals("99%")){
            ReportUtil.log("Proficiency of student", "Proficiency of student is not displaying", "Proficiency of student is displaying");}
         else{
            CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

         }


         ReportUtil.log("Instructor Login", "Login as login as instructor", "info");
         new LoginUsingLTI().ltiLogin("1");//login as instructor

         actual = dashboard.getQuestionAttempted().getText().trim();
         expected = "6";
         CustomAssert.assertEquals(actual, expected, "Verify Question Attempted Number in dashboard", "Question Attempted Number is " + expected + "", "Question Attempted Number is not " + expected + "");

         actual = dashboard.getAvgQuestionPerformance().get(2).getText().trim();
         expected = "50%";
         CustomAssert.assertEquals(actual, expected, "Verify Question performance in dashboard", "Question performance is displaying as " + expected + "", "Question performance is not displaying as " + expected + "");

         String overallScore4 = proficiencyReport.courseProficiencys.get(1).getText().trim();
         String overallPercentage4 = proficiencyReport.courseProficiencys.get(2).getText().trim();

         actual = overallScore4 + overallPercentage4;
         expected = "50%";
         CustomAssert.assertEquals(actual, expected, "Verify overall score in dashboard", "overall score is displaying as " + expected + "", "overall score is not displaying as " + expected + "");

         height = dashboard.getGradedBarChart().getAttribute("height");
         recentlyGraded = Integer.parseInt(height);
         CustomAssert.assertTrue(recentlyGraded > 60, "Verify Recently Graded graph", "Recently Graded graph is generated", "Recently Graded graph is not generated");


         new Navigator().NavigateTo("Proficiency Report"); //Navigate to Proficiency Report
         String proficiencyBar = proficiencyReport.proficiencyBar.get(0).getAttribute("height");
         CustomAssert.assertTrue(Integer.parseInt(proficiencyBar) > 220, "Verify Class proficiency by chapter performance bar", "Class proficiency by chapter performance bar has generated", "Class proficiency by chapter performance bar has not generated");

         String avgPerformanceStudent1Num = proficiencyReport.getTloChapterQuestionPerformance().get(0).getText();
         String avgPerformanceStudent1Den = proficiencyReport.getTloQuestionPerformance().get(0).getText();
         String avgPerformanceStud1 = avgPerformanceStudent1Num + "/" + avgPerformanceStudent1Den;
         CustomAssert.assertEquals(avgPerformanceStud1, "10/20", "Verify Class proficiency by chapter performance", "Class proficiency by chapter performance for student1 is available.", "Class proficiency by chapter performance for student1 is not available.");

         String avgPerformanceStudent2Num = proficiencyReport.getTloChapterQuestionPerformance().get(1).getText();
         String avgPerformanceStudent2Den = proficiencyReport.getTloQuestionPerformance().get(1).getText();
         String avgPerformanceStud2 = avgPerformanceStudent2Num + "/" + avgPerformanceStudent2Den;
         CustomAssert.assertEquals(avgPerformanceStud2, "20/20", "Verify Class proficiency by chapter performance", "Class proficiency by chapter performance for student2 is available.", "Class proficiency by chapter performance for student2 is not available.");

         ((JavascriptExecutor) driver).executeScript("arguments[0].click();", proficiencyReport.getChapNumber());
         Thread.sleep(3000);

         String proficiencyBarByObjectives = proficiencyReport.proficiencyBar.get(0).getAttribute("height");
         CustomAssert.assertTrue(Integer.parseInt(proficiencyBarByObjectives) > 250, "Verify Class Proficiency by Chapters graph", "Class Proficiency by Chapters is generated correctly", "Class Proficiency by Chapters is not generated correctly");


         List<WebElement> tloProficiency = mostChallengingReport.getChapProficiency();
         if ((tloProficiency.get(0).getText().trim().equals("96%")|| tloProficiency.get(0).getText().trim().equals("95%"))&& (tloProficiency.get(1).getText().trim().equals("100%")||tloProficiency.get(1).getText().trim().equals("99%"))) {
            ReportUtil.log("Verify Class Proficiency By Students section", "Class Proficiency By Students proficiency is displaying correct", "pass");
         } else {
            CustomAssert.fail("Verify Class Proficiency By Students section ", "Class Proficiency By Students proficiency is not expected");

         }


         String avgChapterPerformanceStudent1Num = proficiencyReport.getTloChapterQuestionPerformance().get(0).getText();
         String avgChapterPerformanceStud1 = avgChapterPerformanceStudent1Num + "/" + "20";
         CustomAssert.assertEquals(avgChapterPerformanceStud1, "10/20", "Verify Class proficiency by chapter performance", "Class proficiency by chapter performance for student1 is available.", "Class proficiency by chapter performance for student1 is not available.");

         String avgChapterPerformanceStudent2Num = proficiencyReport.getTloChapterQuestionPerformance().get(1).getText();
         String avgChapterPerformanceStud2 = avgChapterPerformanceStudent2Num + "/" + "20";
         CustomAssert.assertEquals(avgChapterPerformanceStud2, "20/20", "Verify Class proficiency by chapter performance", "Class proficiency by chapter performance for student2 is available.", "Class proficiency by chapter performance for student2 is not available.");

         ((JavascriptExecutor) driver).executeScript("arguments[0].click();", proficiencyReport.getTloNumber()); //click on the tlo number
         Thread.sleep(3000);
         List<WebElement> objectivePerformanceProficiency = mostChallengingReport.getChapProficiency();
         if (objectivePerformanceProficiency.get(0).getText().trim().equals("95%") && objectivePerformanceProficiency.get(1).getText().trim().equals("99%")) {
            ReportUtil.log("Verify Objective Performance By Students Proficiency", "Objective Performance By Students Proficiency is displaying correct", "pass");
         } else {
            CustomAssert.fail("Verify Objective Performance By Students Proficiency section ", "Objective Performance By Students Proficiency is not expected");

         }
         List<WebElement> objectivePerformance = proficiencyReport.objectivePerformance;
         String value1 = ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent", objectivePerformance.get(0)).toString();
         String value2 = ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent", objectivePerformance.get(1)).toString();
         if (value1.equals("10  / 20") && value2.equals("20  / 20")) {
            ReportUtil.log("Verify Objective Performance By Students Proficiency", "Objective Performance By Students Proficiency is displaying correct", "pass");
         } else {
            CustomAssert.fail("Verify Objective Performance By Students Proficiency section ", "Objective Performance By Students Proficiency is not expected");

         }

         int ClassPerformanceByQuestions = proficiencyReport.proficiencyBar.size();
         System.out.println(ClassPerformanceByQuestions);
         CustomAssert.assertTrue(ClassPerformanceByQuestions == 10, "Verify Class Performance by Questions graph", "Class Performance by Questions bar chart is generated correctly", "Class Performance by Questions bar chart is not generated correctly");


         ReportUtil.log("Most Challenging Activities Report", "Navigate to Most Challenging Activities Report", "info");
         new Navigator().NavigateTo("Most Challenging Activities Report"); //navigate to Most Challenging Activities Report
         mostProficiencyByChapter = mostChallengingReport.getChapProficiency().get(0).getText().trim();
         System.out.println("Most Challenging proficiency By Chapter:" + mostProficiencyByChapter);
         CustomAssert.assertTrue(mostProficiencyByChapter.equals("98%")||mostProficiencyByChapter.equals("97%"), "Verify Most Challenging proficiency By Chapter", "Most Challenging proficiency By Chapter is displaying correctly", "Most Challenging proficiency By Chapter is empty.");

         mostPerformanceByChapter = mostChallengingReport.getChapPerformance().get(0).getText().trim();
         System.out.println("Most Challenging performance By Chapter:" + mostPerformanceByChapter);
         CustomAssert.assertTrue(mostPerformanceByChapter.equals("5/10"), "Verify Most Challenging performance By Chapter", "Most Challenging performance By Chapter is displaying correctly", "Most Challenging performance By Chapter is empty.");

         mostChallengingReport.getViewByObjective_Tab().click();//click on the view Objective tab
         Thread.sleep(2000);

         mostObjectiveProficiency = mostChallengingReport.getChapProficiency().get(1).getText().trim();
         CustomAssert.assertTrue(mostObjectiveProficiency.equals("97%"), "Verify Most Challenging proficiency By Objective", "Most Challenging proficiency By Objective is displaying correctly", "Most Challenging proficiency By Objective is empty.");

         mostPerformanceByObjective = mostChallengingReport.getChapPerformance().get(1).getText().trim();
         CustomAssert.assertTrue(mostPerformanceByObjective.equals("5/10"), "Verify Most Challenging performance By Objective", "Most Challenging proficiency By performance is displaying correctly", "Most Challenging performance By Objective is empty.");


         gradebook=PageFactory.initElements(driver,Gradebook.class);
         engagementReport = PageFactory.initElements(driver, EngagementReport.class);


         ReportUtil.log("Engagement Report", "Navigate to Engagement Report", "info");
         new Navigator().NavigateTo("Engagement Report");
         actual = engagementReport.questionPerformance_Count.getText().trim();
         expected = "30"; //No of Question
         CustomAssert.assertEquals(actual, expected, "Verify Question Number of Question Performance Summary", "Question Number of Question Performance Summary  is " + expected + "", "Question Number of Question Performance Summary is not " + expected + "");
         int sum = 0;
         for (int i = 2; i < 5; i++) {
            Thread.sleep(1000);
            sum += Integer.parseInt(engagementReport.questionProficiency.get(i).getText().trim().substring(0, engagementReport.questionProficiency.get(i).getText().indexOf("%")));

         }
         actual_int = sum;
         expected_int = 100;
         CustomAssert.assertEquals(actual_int, expected_int, "Verify Question performance in Engagement Report", "Question performance is displaying as " + expected_int + "", "Question performance is not displaying as " + expected_int + "");

         List<WebElement> grades = engagementReport.studTotalGrades;
         if (grades.get(0).getText().trim().equals("0.0%") && grades.get(1).getText().trim().equals("100.0%")&&grades.get(2).getText().trim().equals("50.0%")) {
            ReportUtil.log("Verify Total grade", "Total grade is displaying correct", "pass");
         } else {
            CustomAssert.fail("Total grade", "Total grade is displaying correct is not expected");

         }

         ReportUtil.log("Gradebook", "Navigate to Gradebook", "info");
         new Navigator().NavigateTo("Gradebook"); //navigate to Gradebook
         gradebook.basicView.click();

         List<WebElement> weight1=gradebook.gradebookWeight;
         if(weight1.get(4).getText().trim().equals("0/20")&& weight1.get(9).getText().trim().equals("20/20")&& weight1.get(14).getText().trim().equals("10/20")){
            ReportUtil.log("Verify assignment grade of each student","Assignment grade of each student is displaying correct","pass");
         }
         else {
            CustomAssert.fail("Verify assignment grade of each student", "Assignment grade of each student is not expected");

         }
         List<WebElement> overallScore=gradebook.getOverAllScore();
         if(overallScore.get(1).getText().trim().equals("0%")&& overallScore.get(2).getText().trim().equals("100%")&& overallScore.get(3).getText().trim().equals("50%")){
            ReportUtil.log("Verify overall score of each student","overall score of each student is displaying correct","pass");
         }
         else {
            CustomAssert.fail("overall score grade of each student", "overall score of each student is not expected");

         }


          new Navigator().NavigateTo("Productivity Report"); //Navigate to Productivity Report
          ReportUtil.log("Productivity Report", "Navigated to Productivity Report", "Pass");

          List<WebElement> profReport4 = productivityReport.insProficiency;
          if (profReport4.get(0).getText().trim().equals("95%") && profReport4.get(1).getText().trim().equals("99%")) {
              ReportUtil.log("Verify Proficiency", "Proficiency for each student is displaying correct", "pass");
          } else {
              CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

          }
          new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
          productivityReport.getColoredMarker().click();//click on the colored marker
          Thread.sleep(2000);
          if(productivityReport.getStudProficiency().getText().equals("99%")||productivityReport.getStudProficiency().getText().equals("100%")){
              ReportUtil.log("Verify Proficiency of student", "Proficiency of student is not displaying", "Proficiency of student is displaying");}
          else{
              CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

          }
          new Navigator().NavigateTo("Metacognitive Report"); //Navigate to Metacognitive Report
          ReportUtil.log("Metacognitive Report", "Navigated to Metacognitive Report", "Pass");

          List<WebElement> profReport5 = productivityReport.insProficiency;
          if (profReport5.get(0).getText().trim().equals("95%") && profReport5.get(1).getText().trim().equals("99%")) {
              ReportUtil.log("Verify Proficiency", "Proficiency for each student is displaying correct", "pass");
          } else {
              CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

          }
          new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
          productivityReport.getColoredMarker().click();//click on the colored marker
          Thread.sleep(2000);
          if(productivityReport.getStudProficiency().getText().equals("99%")||productivityReport.getStudProficiency().getText().equals("100%")){
              ReportUtil.log("Verify Proficiency of student", "Proficiency of student is not displaying", "Proficiency of student is displaying");}
          else{
              CustomAssert.fail("Verify Proficiency", "Proficiency for each student is not displaying correct");

          }

      } catch (Exception e) {
         Assert.fail("Exception in TC dashboardQuestionPerformance of class PolicyTwo", e);
      }
   }


}
