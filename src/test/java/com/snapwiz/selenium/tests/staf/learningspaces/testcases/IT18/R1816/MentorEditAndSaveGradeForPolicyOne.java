package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R1816;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by Mukesh on 1/9/15.
 */
public class MentorEditAndSaveGradeForPolicyOne extends Driver{
    @Test(priority = 1, enabled = true)
    public void mentorEditAndSaveGradeForGradableAssignmentPolicyOne() {

        //Tc row no 93
        //"1. Login as instructor 2.Navigate to Dashboard from main navigator"

        try {
            ProficiencyReport proficiencyReport;
            MetacognitiveReport metacognitiveReport;
            ProductivityReport productivityReport;
            MostChallengingReport mostChallengingReport;
            EngagementReport engagementReport;
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "27");

            new LoginUsingLTI().ltiLogin("27_1");//login as student
            new LoginUsingLTI().ltiLogin("27");//login as instructor
            new Assignment().create(27);
            for(int i=0;i<11;i++)
                new Assignment().addQuestions(27,"truefalse","");
            new LoginUsingLTI().ltiLogin("27");//login as instructor
            new Navigator().NavigateTo("Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");//till save policy

            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().assignToStudent(27);//assign to student

            new LoginUsingLTI().ltiLogin("27_1");//login as student
            new Assignment().submitAssignmentAsStudent(27); //submit assignment

            new LoginUsingLTI().ltiLogin("27");//login as instructor
            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("27");//login as instructor

            proficiencyReport =PageFactory.initElements(driver,ProficiencyReport.class);
            metacognitiveReport=PageFactory.initElements(driver,MetacognitiveReport.class);
            productivityReport=PageFactory.initElements(driver,ProductivityReport.class);
            mostChallengingReport=PageFactory.initElements(driver,MostChallengingReport.class);
            engagementReport=PageFactory.initElements(driver,EngagementReport.class);

            String sampleText = driver.findElement(By.id("donut-chart")).getText().trim();
            int index1=sampleText.lastIndexOf("e");
            int index2=sampleText.indexOf("%");
            String str=sampleText.substring(index1+1,index2);
            int avgPerformance=Integer.parseInt(str);
            String height=driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")).getAttribute("height");
            int recentlyGraded= Integer.parseInt(height);
            String performance=new TextFetch().textfetchbylistclass("number",2);
            int index4=performance.indexOf("%");
            String performance2=performance.substring(0,index4);
            int avgQuestionPerformance=Integer.parseInt(performance2);


            //Tc row no 70 &71
            //3. Navigate to Proficiency Report from main navigator
            new Navigator().NavigateTo("Proficiency Report"); //Navigate to Proficiency Report

            String teacherProficiencyReport=proficiencyReport.getBarChart().getAttribute("height"); //teacherProficiencyReport

            String studProfReport=proficiencyReport.getStudProficiency().getText().trim();


            //Tc row no 72&73
            //4. Click on the chapter number below the bar
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",proficiencyReport.getChapNumber());
            Thread.sleep(5000);
            String teacherObjProficiencyReport=proficiencyReport.getBarChart().getAttribute("height"); //teacherProficiencyReport

            String studObjProfReport=proficiencyReport.getStudProficiency().getText().trim();

            //Tc row no 74&75
            //5. Click on the TLOs number in the bar
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",proficiencyReport.getTloNumber());
            Thread.sleep(5000);
            String classProficiencyReportByQuestion=proficiencyReport.getBarChart().getAttribute("height"); //teacherProficiencyReport

            String studObjPerformance=proficiencyReport.getStudProficiency().getText().trim();

            //Tc row no 76
            //6. Navigate to 'Metacognitive Report
            //The proficiency for a particular student should get updated in 'Proficiency' column with changed grades for that assignment.
            new Navigator().NavigateTo("Metacognitive Report");//navigate to Metacognitive Report
            String studMetaCogProfReport= metacognitiveReport.getStudProficiency().getText().trim();

            //Tc row no 78 7.Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());

            String colorMarkerProf=metacognitiveReport.getProficiency().getText().trim();
            String colorMarkerProfValue= colorMarkerProf.substring(13,15);

            //Tc row n0 79 8.Click on the colored marker for the student

            metacognitiveReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());

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
            String studProdCogProfReport= productivityReport.getStudProficiency().getText().trim();

            //Tc row no 82 7.Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());

            String prodColorMarkerProf=productivityReport.getProficiency().getText().trim();
            String prodColorMarkerProfValue= prodColorMarkerProf.substring(0,2);
            System.out.println("Productivity Colored marker proficiency:"+prodColorMarkerProfValue);

            //Tc row n0 83 8.Click on the colored marker for the student

            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='tooltip-label-subsection-wrapper']")));
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
            //Expected 1 "The instructor should be navigated to 'Most Challenging Activity Reports' page
            Assert.assertEquals(mostChallengingReport.getTitle().getText().trim(), "Most Challenging Activities", "instructor is not navigated to 'Most Challenging Activity Reports' page");

            //Tc row no 86 15. Click on 'View By Chapters' tab
            //Expected1 Chapter Proficiency' should be updated taking the changed grade for that assignment.
            String mostProficiency=mostChallengingReport.getChapProficiency().get(0).getText().trim();

            //Expected 2 Chapter Performance' should be updated taking the changed grade for that assignment.
            String mostPerformance=mostChallengingReport.getChapPerformance().get(0).getText().trim();

            //Tc row no 88
            //16. Click on 'View By Objectives' tab
            mostChallengingReport.getViewByObjective_Tab().click();//click on the view Objective tab
            String mostObjectiveProficiency=mostChallengingReport.getChapProficiency().get(1).getText().trim();
            //Expected 2 "Learning Objective Performance" should be updated taking the changed grade for that assignment
            String mostObjectivePerformance=mostChallengingReport.getChapPerformance().get(1).getText().trim();

            //Tc row no 90
            //17. Navigate to Engagement Reports
            //“Question performance summary” column should updated
            new Navigator().NavigateTo("Engagement Report");//navigate to Engagement Report
            String engagQuestionPercentage= engagementReport.getQuestionPerSummary().getText().trim();

            //Expected 2:“Total Grade” for each student should be updated taking the changed grade into account for that assignment
            String totalGrade=engagementReport.getStudTotalGrade().getText().trim();


            //Tc row no 92 18.Click on GradeBook from the main navigator
            new Navigator().NavigateTo("Gradebook"); //navigate to grade book
            String overallScore=engagementReport.getOverallScore().getText().trim();


            System.out.println("*******************************************");//After updating  grade
            new LoginUsingLTI().ltiLogin("27");//login as instructor
            new Assignment().updateGradeAfterGradeRelease(27); //provide grade to all questions

            new RunScheduledJobs().runScheduledJobsForDashboard();
            new LoginUsingLTI().ltiLogin("27");//login as instructor
            proficiencyReport =PageFactory.initElements(driver,ProficiencyReport.class);
            metacognitiveReport=PageFactory.initElements(driver,MetacognitiveReport.class);
            productivityReport=PageFactory.initElements(driver,ProductivityReport.class);
            mostChallengingReport=PageFactory.initElements(driver,MostChallengingReport.class);
            engagementReport=PageFactory.initElements(driver,EngagementReport.class);
            String sampleText1 = driver.findElement(By.id("donut-chart")).getText().trim();
            int index11=sampleText1.lastIndexOf("e");
            int index21=sampleText1.indexOf("%");
            String str1=sampleText1.substring(index11+1,index21);
            int avgPerformance1=Integer.parseInt(str1);


            String height1=driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")).getAttribute("height");
            int recentlyGraded1= Integer.parseInt(height1);

            String performance1=new TextFetch().textfetchbylistclass("number",2);
            int index41=performance1.indexOf("%");
            String performance21=performance1.substring(0,index41);
            int avgQuestionPerformance1=Integer.parseInt(performance21);


            new Navigator().NavigateTo("Proficiency Report"); //Navigate to Proficiency Report
            String teacherProficiencyReport1=proficiencyReport.getBarChart().getAttribute("height"); //teacherProficiencyReport

            String studProfReport1=proficiencyReport.getStudProficiency().getText().trim();

            //Tc row no 72&73
            //4. Click on the chapter number below the bar
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",proficiencyReport.getChapNumber());
            Thread.sleep(5000);
            String teacherObjProficiencyReport1=proficiencyReport.getBarChart().getAttribute("height"); //teacherProficiencyReport

            String studObjProfReport1=proficiencyReport.getStudProficiency().getText().trim();

            //Tc row no 74&75
            //5. Click on the TLOs number in the bar
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",proficiencyReport.getTloNumber());
            Thread.sleep(5000);
            String classProficiencyReportByQuestion1=proficiencyReport.getBarChart().getAttribute("height"); //teacherProficiencyReport

            String studObjPerformance1=proficiencyReport.getStudProficiency().getText().trim();
            System.out.println("Objective Performance By Students Before Update Grade:"+studObjPerformance1);

            //Tc row no 76
            //6. Navigate to 'Metacognitive Report
            //The proficiency for a particular student should get updated in 'Proficiency' column with changed grades for that assignment.
            new Navigator().NavigateTo("Metacognitive Report");//navigate to Metacognitive Report
            String studMetaCogProfReport1= metacognitiveReport.getStudProficiency().getText().trim();

            //Tc row no 78 7.Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());

            String colorMarkerProf1=metacognitiveReport.getProficiency().getText().trim();
            String colorMarkerProfValue1= colorMarkerProf1.substring(13,15);

            //Tc row n0 79 8.Click on the colored marker for the student

            metacognitiveReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());

            String colorMarkerProfByChap1=metacognitiveReport.getProficiency().getText().trim();
            String colorMarkerProfByChapValue1= colorMarkerProfByChap1.substring(13,15);

            //Tc row no 80 9.Click on the colored marker  for the chapter
            metacognitiveReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());
            String colorMarkerProfByObjective1=metacognitiveReport.getProficiency().getText().trim();
            String colorMarkerProfByObjectiveValue1= colorMarkerProfByObjective1.substring(13,15);
            // System.out.println("Colored marker proficiency By Objective: "+colorMarkerProfByObjectiveValue1);

            //Tc row no 81
            //10.Navigate to 'Productivity Report'
            new Navigator().NavigateTo("Productivity Report"); //navigate to Productivity Report
            String studProdCogProfReport1= productivityReport.getStudProficiency().getText().trim();

            //Tc row no 82 7.Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='tooltip-label-subsection-wrapper']")));
            String prodColorMarkerProf1=productivityReport.getProficiency().getText().trim();
            String prodColorMarkerProfValue1= prodColorMarkerProf1.substring(0,2);

            //Tc row n0 83 8.Click on the colored marker for the student

            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());

            String prodColorMarkerProfByChap1=productivityReport.getProficiency().getText().trim();
            String prodColorMarkerProfByChapValue1= prodColorMarkerProfByChap1.substring(0,2);

            //Tc row no 84 9.Click on the colored marker  for the chapter
            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            String prodColorMarkerProfByObjective1=productivityReport.getProficiency().getText().trim();
            String prodColorMarkerProfByObjectiveValue1= prodColorMarkerProfByObjective1.substring(0,2);

            //Tc row no 85 14. Navigate to 'Most Challenging Activity Reports'

            new Navigator().NavigateTo("Most Challenging Activities Report"); //navigate to Most Challenging Activities Report
            //Tc row no 86 15. Click on 'View By Chapters' tab
            //Expected1 Chapter Proficiency' should be updated taking the changed grade for that assignment.
            String mostProficiency1=mostChallengingReport.getChapProficiency().get(0).getText().trim();

            //Expected 2 Chapter Performance' should be updated taking the changed grade for that assignment.

            String mostPerformance1=mostChallengingReport.getChapPerformance().get(0).getText().trim();

            //Tc row no 88
            //16. Click on 'View By Objectives' tab
            mostChallengingReport.getViewByObjective_Tab().click();//click on the view Objective tab

            String mostObjectiveProficiency1=mostChallengingReport.getChapProficiency().get(1).getText().trim();

            //Expected 2 "Learning Objective Performance" should be updated taking the changed grade for that assignment
            String mostObjectivePerformance1=mostChallengingReport.getChapPerformance().get(1).getText().trim();


            //Tc row no 90
            //17. Navigate to Engagement Reports
            //“Question performance summary” column should updated
            new Navigator().NavigateTo("Engagement Report");//navigate to Engagement Report
            String engagQuestionPercentage1= engagementReport.getQuestionPerSummary().getText().trim();

            //Expected 2:“Total Grade” for each student should be updated taking the changed grade into account for that assignment
            String totalGrade1=engagementReport.getStudTotalGrade().getText().trim();

            //Tc row no 92 18.Click on GradeBook from the main navigator
            new Navigator().NavigateTo("Gradebook"); //navigate to grade book
            String overallScore1=engagementReport.getOverallScore().getText().trim();

            //Expected 1 “Average Performance” tile in the top right panel should be updated taking the changed score into account for all the questions of that assignment.
            if(avgPerformance<avgPerformance1)
                Assert.fail("/“Average Performance/” tile is not updated");

            //Expected 2 "Recently graded" graph for that assignment should be updated

            if(recentlyGraded<recentlyGraded1)
                Assert.fail("\"Recently graded\" graph is not updated");
            // Expected 3 “Average Question Performance” tile should be updated taking the changed score into account for all the questions of that assignment
            if(avgQuestionPerformance<avgQuestionPerformance1)
                Assert.fail("/“Average Question Performance/” tile is not updated ");

            //Expected 4 Class Proficiency By Chapter should be updated
            Assert.assertNotEquals(teacherProficiencyReport, teacherProficiencyReport1, " Class Proficiency By Chapter is not updated");

            //Expected 5 " Class Proficiency By Students should be updated."
            Assert.assertNotEquals(studProfReport,studProfReport1,"Class Proficiency By Students is not updated");

            //Expected 6 Class Proficiency By Objective should be updated
            Assert.assertNotEquals(teacherObjProficiencyReport,teacherObjProficiencyReport1,"Class Proficiency By Objective is not updated");

            //Expected 7 Chapter Proficiency By Students should be updated
            Assert.assertNotEquals(studObjProfReport,studObjProfReport1,"Chapter Proficiency By Students is not updated");

            //Expected 8 Class Performance by Question should be updated
            Assert.assertNotEquals(classProficiencyReportByQuestion,classProficiencyReportByQuestion1,"Class Performance by Question is not updated");

            //Expected 9 Objective Performance by Students should be updated
            Assert.assertNotEquals(studObjPerformance,studObjPerformance1,"Objective Performance by Students is not updated");

            //Expected 10 The proficiency for a particular student should get updated in 'Proficiency' column with changed grades for that assignment.
            Assert.assertNotEquals(studMetaCogProfReport,studMetaCogProfReport1,"proficiency for a particular student is not update");

            //Expected 11  'Proficiency' in percentage should be updated.
            Assert.assertNotEquals(colorMarkerProfValue,colorMarkerProfValue1,"'Proficiency' in percentage is not updated.");

            // Expected 12  Proficiency with respect to Chapter should be updated.
            Assert.assertNotEquals(colorMarkerProfByChapValue,colorMarkerProfByChapValue1,"Proficiency with respect to Chapter is not updated");

            //Expected 13 Proficiency with respect to Objective should be updated.
            Assert.assertNotEquals(colorMarkerProfByObjectiveValue,colorMarkerProfByObjectiveValue1,"Proficiency with respect to Objective is not updated.");

            //Expected 14 Proficiency' column should get updated.
            Assert.assertNotEquals(studProdCogProfReport,studProdCogProfReport1,"Proficiency' column is not updated");

            //Expected 15 Proficiency' in percentage should be updated.
            Assert.assertNotEquals(prodColorMarkerProfValue,prodColorMarkerProfValue1,"Proficiency' in percentage is not updated.");

            //Expected 16 "Proficiency with respect to Chapter should be updated."
            Assert.assertNotEquals(prodColorMarkerProfByChapValue,prodColorMarkerProfByChapValue1,"Proficiency with respect to Chapter is not updated.");

            //Expected 17 Proficiency with respect to Objective should be updated.
            Assert.assertNotEquals(prodColorMarkerProfByObjectiveValue,prodColorMarkerProfByObjectiveValue1,"Proficiency with respect to Objective is not updated.");

            //Expected 18 Chapter Proficiency' should be updated taking the changed grade for that assignment.
            Assert.assertNotEquals(mostProficiency,mostProficiency1," Chapter Proficiency' has not updated in 'View By Chapters' tab");

            //Expected 20 Chapter Performance' should be updated taking the changed grade for that assignment.
            Assert.assertNotEquals(mostPerformance,mostPerformance1," Chapter Performance' has not updated in 'View By Chapters' tab");

            //Expected 21 "Learning Objective Proficiency" should be updated taking the changed grade for that assignment
            Assert.assertNotEquals(mostObjectiveProficiency,mostObjectiveProficiency1,"Learning Objective Proficiency has not updated  on 'View By Objectives' tab");

            //Expected 22 "Learning Objective Performance" should be updated taking the changed grade for that assignment
            Assert.assertNotEquals(mostObjectivePerformance,mostObjectivePerformance1,"Learning Objective Performance has not updated on 'View By Objectives' tab");

            //Expected 23 “Question performance summary” column should updated
            Assert.assertNotEquals(engagQuestionPercentage,engagQuestionPercentage1,"“Question performance summary” column has not updated in Engagement Reports");

            //Expected 24 “Total Grade” for each student should be updated taking the changed grade into account for that assignment
            Assert.assertNotEquals(totalGrade,totalGrade1,"“Total Grade” for each student has not updated in Engagement Reports");

            //Expected 25 Overall Score'should be updated for the particular student
            Assert.assertNotEquals(overallScore,overallScore1," Overall Score'has not  updated for the particular student in gradebook page");



        } catch (Exception e) {
            Assert.fail("Exception in test case mentorEditAndSaveGradeForGradableAssignmentPolicyOne of class MentorEditAndSaveGradeForPolicyOne", e);
        }
    }

}



