package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R1816;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.MetacognitiveReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.MostChallengingReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.ProductivityReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.ProficiencyReport;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by Mukesh on 1/7/15.
 */
public class GradableAssignmentWithPolicyOneInStudentSide extends Driver{
    @Test(priority = 1, enabled = true)
    public void gradableAssignmentWithPolicyOneInStudentSide() {

        //Tc row no 234
        //"1. Login as student 2.Navigate to Dashboard from main navigator"

        try {
            ProficiencyReport proficiencyReport;
            MetacognitiveReport metacognitiveReport;
            ProductivityReport productivityReport;
            MostChallengingReport mostChallengingReport;
            Dashboard dashboard;
            Assignments assignments;
            assignments= PageFactory.initElements(driver, Assignments.class);
            proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);
            metacognitiveReport=PageFactory.initElements(driver,MetacognitiveReport.class);
            productivityReport=PageFactory.initElements(driver,ProductivityReport.class);
            mostChallengingReport=PageFactory.initElements(driver,MostChallengingReport.class);
            dashboard=PageFactory.initElements(driver,Dashboard.class);

            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "234");

            new LoginUsingLTI().ltiLogin("234_1");//login as student
            new LoginUsingLTI().ltiLogin("234");//login as instructor
            new Assignment().create(234);
            for(int i=0;i<11;i++)
                new Assignment().addQuestions(234,"truefalse","");
            new LoginUsingLTI().ltiLogin("234");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");//till save policy

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(234);//assign to student

            new LoginUsingLTI().ltiLogin("234_1");//login as student
            new Assignment().submitAssignmentAsStudent(234); //submit assignment


            new LoginUsingLTI().ltiLogin("234_1");//login as student
            String sampleText =dashboard.getAvgPerformance().getText().trim();
            int index1=sampleText.lastIndexOf("e");
            int index2=sampleText.indexOf("%");
            String dashboardOverallScore=sampleText.substring(index1+1,index2);
            Thread.sleep(5000);
            System.out.println("Average Performance : " + dashboardOverallScore);

            String recentlyGraded=dashboard.getGradedBarChart().getAttribute("height");
            System.out.println("Height:"+recentlyGraded);
            Thread.sleep(4000);
            String performance=dashboard.getAvgQuestionPerformance().get(2).getText().trim();
            int index4=performance.indexOf("%");
            String questionPerformance=performance.substring(0,index4);
            Thread.sleep(2000);
            System.out.println("AvgQuestionPerformance :"+questionPerformance);
            System.out.println("pass");

            new Navigator().NavigateTo("Assignments");//navigate to assignment
            String overallScore=assignments.getOverallScorePercentage().getText().trim();
            System.out.println("OverAll Score:"+overallScore);
            assignments.getAssignmentName().click();//click on the assignment name
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.className("title-and-question-header")));
            String performanceSummary=assignments.getPerformanceSummary().getText().trim();
            System.out.println("Performance Summary:"+performanceSummary);
            new ClickOnquestionCard().clickonquestioncard(0); //click on the question card


            //Tc row no 214 &215
            //3. Navigate to Proficiency Report from main navigator
            new Navigator().NavigateTo("Proficiency Report"); //Navigate to Proficiency Report

            String classProficiencyChap=proficiencyReport.getBarChart().getAttribute("height"); //teacherProficiencyReport
            System.out.println("Course Proficiency By chapter Before Update Grade:"+classProficiencyChap);
            String classProficiency=proficiencyReport.getCourseProficiency().getText().trim();
            System.out.println("Course Proficiency Summary Report Before Update Grade:"+classProficiency);

            //Tc row no 216&217
            //7.Click on Chapter bar in 'Course Proficiency By Chapter'
            driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")).click();//Click on Chapter bar in 'Course Proficiency By Chapter'
            Thread.sleep(5000);
            String chapProficiencySummary=proficiencyReport.getCourseProficiency().getText().trim();
            String chapObjProficiency=proficiencyReport.getBarChart().getAttribute("height"); //teacherProficiencyReport

            //Tc row no 218&219
            // 8.Click on Objective bar in 'Chapter Proficiency By Objectives'
            driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")).click(); //Click on Objective bar in 'Chapter Proficiency By Objectives'
            Thread.sleep(5000);
            String objProficiencySummary=proficiencyReport.getCourseProficiency().getText().trim();
            String objProficiencyByQuestion=proficiencyReport.getBarChart().getAttribute("height"); //teacherProficiencyReport


            //Tc row no 220
            //9.Click on Reports and select 'Metacognitive Report'
            //The proficiency for a particular student should get updated in 'Proficiency' column with changed grades for that assignment.
            new Navigator().NavigateTo("Metacognitive Report");//navigate to Metacognitive Report
            String studMetaCogProfReport= metacognitiveReport.getStudProfPercentage().getText().trim();

            //Tc row no 78 7.Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());
            System.out.println("pass");
            String colorMarkerProf=metacognitiveReport.getProficiency().getText().trim();
            String colorMarkerProfValue= colorMarkerProf.substring(13,15);
            System.out.println("Colored marker proficiency:"+colorMarkerProfValue);

            //Tc row n0 79 8.Click on the colored marker for the student

            metacognitiveReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());
            System.out.println("pass");
            String colorMarkerProfByChap=metacognitiveReport.getProficiency().getText().trim();
            String colorMarkerProfByChapValue= colorMarkerProfByChap.substring(13,15);

            //Tc row no 80 9.Click on the colored marker  for the chapter
            metacognitiveReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());
            String colorMarkerProfByObjective=metacognitiveReport.getProficiency().getText().trim();
            String colorMarkerProfByObjectiveValue= colorMarkerProfByObjective.substring(13,15);

            //Tc row no 81
            //10.Navigate to 'Productivity Report'
            new Navigator().NavigateTo("Productivity Report"); //navigate to Productivity Report
            String studProdCogProfReport= productivityReport.getStudProfPercentage().getText().trim();

            //Tc row no 82 7.Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='tooltip-label-subsection-wrapper']")));

            String prodColorMarkerProf=productivityReport.getProficiency().getText().trim();
            String prodColorMarkerProfValue= prodColorMarkerProf.substring(0,2);

            //Tc row n0 83 8.Click on the colored marker for the student

            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            System.out.println("pass");
            String prodColorMarkerProfByChap=productivityReport.getProficiency().getText().trim();
            String prodColorMarkerProfByChapValue= prodColorMarkerProfByChap.substring(0,2);

            //Tc row no 84 9.Click on the colored marker  for the chapter
            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            String prodColorMarkerProfByObjective=productivityReport.getProficiency().getText().trim();
            String prodColorMarkerProfByObjectiveValue= prodColorMarkerProfByObjective.substring(0,2);

            //Tc row no 85 14. Navigate to 'Most Challenging Activity Reports'

            new Navigator().NavigateTo("Most Challenging Activities Report"); //navigate to Most Challenging Activities Report
            //Tc row no 86 15. Click on 'View By Chapters' tab
            //Expected1 Chapter Proficiency' should be updated taking the changed grade for that assignment.
            String mostProficiency=mostChallengingReport.getStudChapProficiency().get(0).getText().trim();
            //Expected 2 Chapter Performance' should be updated taking the changed grade for that assignment.
            String mostPerformance=mostChallengingReport.getStudChapPerformance().get(0).getText().trim();

            //Tc row no 88
            //16. Click on 'View By Objectives' tab
            mostChallengingReport.getStudViewObjective_Tab().click();//click on the view Objective tab
            System.out.println("Pass");
            String mostObjectiveProficiency=mostChallengingReport.getStudChapProficiency().get(1).getText().trim();
            Thread.sleep(5000);
            //Expected 2 "Learning Objective Performance" should be updated taking the changed grade for that assignment
            String mostObjectivePerformance=mostChallengingReport.getStudChapPerformance().get(1).getText().trim();
            new LoginUsingLTI().ltiLogin("234");//login as instructor
            new Assignment().updateGradeAfterGradeRelease(234); //provide grade to all question

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            new LoginUsingLTI().ltiLogin("234_1");//login as student
            String sampleText1 =dashboard.getAvgPerformance().getText().trim();
            System.out.println("sampleText: "+sampleText1);
            int index11=sampleText1.lastIndexOf("e");
            int index21=sampleText1.indexOf("%");
            String dashboardOverallScore1=sampleText1.substring(index11+1,index21);

            String recentlyGraded1=dashboard.getGradedBarChart().getAttribute("height");
            //int recentlyGraded1= Integer.parseInt(height1);
            Thread.sleep(4000);
            String performance1=dashboard.getAvgQuestionPerformance().get(2).getText().trim();
            int index41=performance1.indexOf("%");
            String questionPerformance1=performance1.substring(0,index41);

            new Navigator().NavigateTo("Assignments");//navigate to assignment
            //Expected 4 Student should be navigated to 'Assignment Summary' page
            Assert.assertEquals(driver.findElement(By.xpath("//span[@title='(shor)  Assignment_IT18_234']")).isDisplayed(), true, " Student is not navigated to 'Assignment Summary' page");
            String overallScore1=assignments.getOverallScorePercentage().getText().trim();

            String dueDateMessage1=assignments.getDueDateMessage().get(1).getText().trim();
            assignments.getAssignmentName().click();//click on the assignment name
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.className("title-and-question-header")));
            String performanceSummary1=assignments.getPerformanceSummary().getText().trim();
            System.out.println("Performance Summary:"+performanceSummary1);
            new ClickOnquestionCard().clickonquestioncard(0); //click on the question card


            //Tc row no 214 &215
            //3. Navigate to Proficiency Report from main navigator
            new Navigator().NavigateTo("Proficiency Report"); //Navigate to Proficiency Report
            String classProficiency1=proficiencyReport.getCourseProficiency().getText().trim();
            String classProficiencyChap1=proficiencyReport.getBarChart().getAttribute("height"); //teacherProficiencyReport

            //Tc row no 216&217
            //7.Click on Chapter bar in 'Course Proficiency By Chapter'
            driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")).click();//Click on Chapter bar in 'Course Proficiency By Chapter'
            Thread.sleep(5000);
            String chapProficiencySummary1=proficiencyReport.getCourseProficiency().getText().trim();

            String chapObjProficiency1=proficiencyReport.getBarChart().getAttribute("height"); //teacherProficiencyReport

            //Tc row no 218&219
            // 8.Click on Objective bar in 'Chapter Proficiency By Objectives'
            driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")).click(); //Click on Objective bar in 'Chapter Proficiency By Objectives'
            Thread.sleep(5000);
            String objProficiencySummary1=proficiencyReport.getCourseProficiency().getText().trim();

            String objProficiencyByQuestion1=proficiencyReport.getBarChart().getAttribute("height"); //teacherProficiencyReport

            //Tc row no 220
            //9.Click on Reports and select 'Metacognitive Report'
            //The proficiency for a particular student should get updated in 'Proficiency' column with changed grades for that assignment.
            new Navigator().NavigateTo("Metacognitive Report");//navigate to Metacognitive Report
            String studMetaCogProfReport1= metacognitiveReport.getStudProfPercentage().getText().trim();

            //Tc row no 222 7.Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());
            System.out.println("pass");
            String colorMarkerProf1=metacognitiveReport.getProficiency().getText().trim();
            String colorMarkerProfValue1= colorMarkerProf1.substring(13,15);

            //Tc row n0 223 8.Click on the colored marker for the student

            metacognitiveReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());
            System.out.println("pass");
            String colorMarkerProfByChap1=metacognitiveReport.getProficiency().getText().trim();
            String colorMarkerProfByChapValue1= colorMarkerProfByChap1.substring(13,15);

            //Tc row no 224 9.Click on the colored marker  for the chapter
            metacognitiveReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());
            String colorMarkerProfByObjective1=metacognitiveReport.getProficiency().getText().trim();
            String colorMarkerProfByObjectiveValue1= colorMarkerProfByObjective1.substring(13,15);

            //Tc row no 225
            //10.Navigate to 'Productivity Report'
            new Navigator().NavigateTo("Productivity Report"); //navigate to Productivity Report
            String studProdCogProfReport1= productivityReport.getStudProfPercentage().getText().trim();

            //Tc row no 226 7.Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='tooltip-label-subsection-wrapper']")));
            String prodColorMarkerProf1=productivityReport.getProficiency().getText().trim();
            String prodColorMarkerProfValue1= prodColorMarkerProf1.substring(0,2);

            //Tc row n0 227 8.Click on the colored marker for the student

            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            System.out.println("pass");
            String prodColorMarkerProfByChap1=productivityReport.getProficiency().getText().trim();
            String prodColorMarkerProfByChapValue1= prodColorMarkerProfByChap1.substring(0,2);

            //Tc row no 228 9.Click on the colored marker  for the chapter
            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            String prodColorMarkerProfByObjective1=productivityReport.getProficiency().getText().trim();
            String prodColorMarkerProfByObjectiveValue1= prodColorMarkerProfByObjective1.substring(0,2);

            //Tc row no 229 14. Navigate to 'Most Challenging Activity Reports'

            new Navigator().NavigateTo("Most Challenging Activities Report"); //navigate to Most Challenging Activities Report
            //Tc row no 229 15. Click on 'View By Chapters' tab
            //Expected1 Chapter Proficiency' should be updated taking the changed grade for that assignment.
            String mostProficiency1=mostChallengingReport.getStudChapProficiency().get(0).getText().trim();
            //Expected 2 Chapter Performance' should be updated taking the changed grade for that assignment.
            String mostPerformance1=mostChallengingReport.getStudChapPerformance().get(0).getText().trim();

            //Tc row no 232
            //16. Click on 'View By Objectives' tab
            mostChallengingReport.getStudViewObjective_Tab().click();//click on the view Objective tab
            System.out.println("Pass");
            String mostObjectiveProficiency1=mostChallengingReport.getStudChapProficiency().get(1).getText().trim();
            Thread.sleep(5000);
            //Expected 2 "Learning Objective Performance" should be updated taking the changed grade for that assignment
            String mostObjectivePerformance1=mostChallengingReport.getStudChapPerformance().get(1).getText().trim();

            //Expected 1 The Percentage value in “Overall Score” tile in the top right panel should update
           Assert.assertNotEquals(dashboardOverallScore,dashboardOverallScore1,"Percentage value in “Overall Score” tile in the top right panel has not updated");

            //Expected 2 "The "Recently graded" graph for that assignment should update.
            Assert.assertNotEquals(recentlyGraded,recentlyGraded1,"\"Recently graded\" graph is not updated\"");

            // Expected 3 The value in “Question Performance” tile should be updated taking the changed score into account for all the questions of that assignment.
           Assert.assertNotEquals(questionPerformance,questionPerformance1,"/“Average Question Performance/” tile has not updated ");


            //Expected 5 Percentage value in 'Overall Score' should be updated
            Assert.assertNotEquals(overallScore,overallScore1,"Percentage value in 'Overall Score' has not updated");

            //Expected 6 The assignment card should show a label as 'Your Grade or Feedback has been altered' for that assignment beside the assignment name.
            Assert.assertEquals(dueDateMessage1,"Your grade or feedback has been altered","Your grade or feedback has been altered is not displayed");

            //Expected 7 The grades should be updated in 'Performance Summary' pie chart
            Assert.assertNotEquals(performanceSummary,performanceSummary1," The grades has not updated in 'Performance Summary' pie chart");

            //Expected 11 Course Proficiency Summary' should be updated
            Assert.assertNotEquals(classProficiency,classProficiency1,"Course Proficiency Summary' has not updated");

            //Expected 12 Course Proficiency By Chapter' should be updated
            Assert.assertNotEquals(classProficiencyChap,classProficiencyChap1,"Course Proficiency By Chapter' has not updated");

            //Expected 13 Chapter Proficiency Summary should be updated
            Assert.assertNotEquals(chapProficiencySummary,chapProficiencySummary1,"Chapter Proficiency Summary has not updated ");

            //Expected 14 Chapter Proficiency by Objective should be updated
            Assert.assertNotEquals(chapObjProficiency,chapObjProficiency1,"Chapter Proficiency by Objective has not updated");

            //Expected 15 Objective Proficiency Summary' should be updated
            Assert.assertNotEquals(objProficiencySummary,objProficiencySummary1,"Objective Proficiency Summary' has not updated");

            //Expected 16 Objective Proficiency By Question' should be updated
            //  Assert.assertNotEquals(objProficiencyByQuestion,objProficiencyByQuestion1,"Objective Proficiency By Question' has not updated");

            //Expected 17 The proficiency for a particular student should get updated in 'Proficiency' column with changed grades for that assignment.
            Assert.assertNotEquals(studMetaCogProfReport,studMetaCogProfReport1,"proficiency for a particular student has not  updated");
            //Expected 19 'Proficiency' in percentage should be updated.
            Assert.assertNotEquals(colorMarkerProfValue,colorMarkerProfValue1,"'Proficiency' in percentage has not  updated.");

            //Expected 20 Proficiency with respect to Chapter should be updated.
            Assert.assertNotEquals(colorMarkerProfByChapValue,colorMarkerProfByChapValue1,"Proficiency with respect to Chapter has not updated");

            //Expected 21 Proficiency with respect to Objective should be updated.
            Assert.assertNotEquals(colorMarkerProfByObjectiveValue,colorMarkerProfByObjectiveValue1,"Proficiency with respect to Objective is not updated.");

            //Expected 22 Proficiency' column should get updated.
            Assert.assertNotEquals(studProdCogProfReport,studProdCogProfReport1,"Proficiency' column is not updated");

            //Expected 23 Proficiency' in percentage with respect to Chapter should be updated.
            Assert.assertNotEquals(prodColorMarkerProfValue,prodColorMarkerProfValue1,"Proficiency' in percentage is not updated.");

            //Expected 24 Proficiency in  proficiency column with respect to Objective should be updated.
            Assert.assertNotEquals(prodColorMarkerProfByChapValue,prodColorMarkerProfByChapValue1,"Proficiency with respect to Chapter is not updated.");

            //Expected 25 Proficiency' in percentage with respect to Objective should be updated
            Assert.assertNotEquals(prodColorMarkerProfByObjectiveValue,prodColorMarkerProfByObjectiveValue1,"Proficiency with respect to Objective is not updated.");

            //Expected 27 Chapter Performance' should be updated taking the changed grade for that assignment.
            Assert.assertNotEquals(mostProficiency,mostProficiency1," Chapter Proficiency' has not updated in 'View By Chapters' tab");

            //Expected 28 Chapter Performance' should be updated taking the changed grade for that assignment.
            Assert.assertNotEquals(mostPerformance,mostPerformance1," Chapter Performance' has not updated in 'View By Chapters' tab");

            //Expected 29 "Learning Objective Proficiency" should be updated taking the changed grade for that assignment
            Assert.assertNotEquals(mostObjectiveProficiency,mostObjectiveProficiency1,"Learning Objective Proficiency has not updated  on 'View By Objectives' tab");

            //Expected 30 "Learning Objective Performance" should be updated taking the changed grade for that assignment
            Assert.assertNotEquals(mostObjectivePerformance,mostObjectivePerformance1,"Learning Objective Performance has not updated on 'View By Objectives' tab");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case gradableAssignmentWithPolicyOneInStudentSide of class GradableAssignmentWithPolicyOneInStudentSide", e);
        }
    }

}
