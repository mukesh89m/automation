package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R1922;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.Preview;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.MyActivity.MyActivity;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.PerformanceTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Mukesh on 2/19/15.
 */
public class StudentFeedbackInStaticAssessment extends Driver {
    @Test(priority = 1,enabled = true)
    public void studentFeedbackInStaticAssessment()
    {
        try
        {
            //Tc row no 9
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(9));
            Preview preview = PageFactory.initElements(driver, Preview.class);
            Questions question=PageFactory.initElements(driver,Questions.class);
            EngagementReport engagementReport = PageFactory.initElements(driver,EngagementReport.class);
            PerformanceSummaryReport performanceSummaryReport=PageFactory.initElements(driver,PerformanceSummaryReport.class);
            PerformanceTab performanceTab=PageFactory.initElements(driver, PerformanceTab.class);
            ProficiencyReport proficiencyReport;
            MetacognitiveReport metacognitiveReport;
            ProductivityReport productivityReport;
            MostChallengingReport mostChallengingReport;
            proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);
            metacognitiveReport=PageFactory.initElements(driver,MetacognitiveReport.class);
            productivityReport=PageFactory.initElements(driver,ProductivityReport.class);
            mostChallengingReport=PageFactory.initElements(driver,MostChallengingReport.class);
           new LoginUsingLTI().ltiLogin("9");//login as instructor
            new LoginUsingLTI().ltiLogin("9_1");//login as student
            new Assignment().create(9);
            new Assignment().addQuestions(9, "essay", "");
            for(int i=0;i<3;i++) {
                new Assignment().addQuestions(9, "multiplechoice", "");
            }
            for(int i=0;i<7;i++) {
                new Assignment().addQuestions(9, "truefalse", "");
            }

            new LoginUsingLTI().ltiLogin("9_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //Navigate to e_textbook
            Thread.sleep(3000);
            new TopicOpen().chapterOpen(1); //open second chapter
            new SelectCourse().selectInvisibleAssignment(assessmentname); //select assessment

            drawSquareInWriteBoardInStudentSide(9);
            question.crossIcon.click(); //click on x icon
            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new Assignment().submitButtonInQuestionClick();//click on submit
            new Assignment().nextButtonInQuestionClick(); //click on the next button

            new AttemptTest().StaticTestAttemptAllTypeWithConfidence(2,false,false);

            //Tc row no 21
            new Navigator().NavigateTo("Proficiency Report"); //Navigate to Proficiency Report
            String classProficiencyChap=proficiencyReport.getBarChart().getAttribute("height");
            String classProficiency=proficiencyReport.getCourseProficiency().getText().trim();

            //Tc row no 25
            //7.Click on Chapter bar in 'Course Proficiency By Chapter'
            driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")).click();//Click on Chapter bar in 'Course Proficiency By Chapter'
            Thread.sleep(5000);
            String chapProficiencySummary=proficiencyReport.getCourseProficiency().getText().trim();

            String chapObjProficiency=proficiencyReport.getBarChart().getAttribute("height"); //teacherProficiencyReport

            //Tc row no 29
            // 8.Click on Objective bar in 'Chapter Proficiency By Objectives'
            driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")).click(); //Click on Objective bar in 'Chapter Proficiency By Objectives'


            //Tc row no 31
            //9.Click on Reports and select 'Metacognitive Report'
            //The proficiency for a particular student should get updated in 'Proficiency' column with changed grades for that assignment.
            new Navigator().NavigateTo("Metacognitive Report");//navigate to Metacognitive Report
            String studMetaCogProfReport= metacognitiveReport.getStudProfPercentage().getText().trim();

            //.Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());
            System.out.println("pass");
            String colorMarkerProf=metacognitiveReport.getProficiency().getText().trim();
            String colorMarkerProfValue= colorMarkerProf.substring(13,15);

            //Click on the colored marker for the student

            metacognitiveReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());
            System.out.println("pass");
            String colorMarkerProfByChap=metacognitiveReport.getProficiency().getText().trim();
            String colorMarkerProfByChapValue= colorMarkerProfByChap.substring(13,15);

            //Click on the colored marker  for the chapter
            metacognitiveReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());
            System.out.println("pass");
            String colorMarkerProfByObjective=metacognitiveReport.getProficiency().getText().trim();
            String colorMarkerProfByObjectiveValue= colorMarkerProfByObjective.substring(13,15);

            //Tc row no 36
            //10.Navigate to 'Productivity Report'
            new Navigator().NavigateTo("Productivity Report"); //navigate to Productivity Report
            String studProdCogProfReport= productivityReport.getStudProfPercentage().getText().trim();

            //.Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            System.out.println("pass");
            String prodColorMarkerProf=productivityReport.getProficiency().getText().trim();
            String prodColorMarkerProfValue= prodColorMarkerProf.substring(0,2);

            //Click on the colored marker for the student

            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            System.out.println("pass");
            String prodColorMarkerProfByChap=productivityReport.getProficiency().getText().trim();
            String prodColorMarkerProfByChapValue= prodColorMarkerProfByChap.substring(0,2);

            //Click on the colored marker  for the chapter
            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            System.out.println("pass");
            String prodColorMarkerProfByObjective=productivityReport.getProficiency().getText().trim();
            String prodColorMarkerProfByObjectiveValue= prodColorMarkerProfByObjective.substring(0,2);

            //Tc row no 40
            new Navigator().NavigateTo("Most Challenging Activities Report"); //navigate to Most Challenging Activities Report
            //Click on 'View By Chapters' tab
            //Expected1 Chapter Proficiency' should be updated taking the changed grade for that assignment.
            String mostProficiency=mostChallengingReport.getStudChapProficiency().get(0).getText().trim();

            //Expected 2 Chapter Performance' should be updated taking the changed grade for that assignment.
            String mostPerformance=mostChallengingReport.getStudChapPerformance().get(0).getText().trim();
            //Tc row no 88
            //16. Click on 'View By Objectives' tab
            mostChallengingReport.getStudViewObjective_Tab().click();//click on the view Objective tab
            String mostObjectiveProficiency=mostChallengingReport.getStudChapProficiency().get(1).getText().trim();
            Thread.sleep(5000);
            //Expected 2 "Learning Objective Performance" should be updated taking the changed grade for that assignment
            String mostObjectivePerformance=mostChallengingReport.getStudChapPerformance().get(1).getText().trim();


            new LoginUsingLTI().ltiLogin("9");//login as instructor
            new Navigator().NavigateTo("Engagement Report");//navigate to current Assignment
            Thread.sleep(2000);
            driver.findElement(By.xpath("(//div[@class='row-non-assigned-status']/div[2]/span)[2]")).click(); //click on the assignment count
            new TopicOpen().chapterOpen(1); //select Chapter from testData
            new SelectCourse().selectInvisibleAssignment(assessmentname); //select assessment from testData
            Thread.sleep(5000);
            provideFeedbackToAllQuestion(9);
            new Assignment().provideGradeToStudentFromAssignmentResponsePage(9);

            new LoginUsingLTI().ltiLogin("9_1");//login as student
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(1); //select Chapter from testData
            new SelectCourse().selectInvisibleAssignment(assessmentname); //select assessment from testData

            //Tc row no 9
            String summaryPage=performanceSummaryReport.performanceSummaryTitle.getText().trim();
            System.out.println("Summary Page:"+summaryPage);
            if(!summaryPage.contains("Performance Summary"))
                Assert.fail("Student is not able to navigate to Performance Summary page");

            //Tc row no 10
            int indicatorSize=performanceTab.visualIndicator_Icon.size();
            Assert.assertEquals(indicatorSize,8,"Visual indicator is not displayed on question card");

            //Tc row no 13

            String toolTip=performanceTab.visualIndicator_Icon.get(4).getAttribute("title");
            Assert.assertEquals(toolTip,"Score/feedback has been added by your instructor","Tooltip of visual indicator is not a “Score/feedback has been added by your instructor”");

           //Tc row no 14
           new QuestionCard().clickOnInvisibleCard("14",11);
           Thread.sleep(3000);

            int indicatorSize1=performanceTab.visualIndicator_Icon.size();
            Assert.assertEquals(indicatorSize1,8,"Visual indicator is not displayed on question card");

           //Tc row no 15
           String feedback=performanceTab.teacher_feedback.getText().trim();
           Assert.assertEquals(feedback,"This is a FeedbackText","Student is not able to view the teacher feedback");

           String mark=performanceTab.teacher_mark.getText().trim();
           if(!mark.contains("Marks Awarded 0.5/1.0"))
               Assert.fail("Student is not able to view the grade added by teacher");

            System.out.println("*************************");

            new Navigator().NavigateTo("Proficiency Report"); //Navigate to Proficiency Report
            String insClassProficiency=proficiencyReport.getCourseProficiency().getText().trim();
            String insClassProficiencyChap1=proficiencyReport.getBarChart().getAttribute("height"); //teacherProficiencyReport

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
            String prodColorMarkerProf1=productivityReport.getProficiency().getText().trim();
            String prodColorMarkerProfValue1= prodColorMarkerProf1.substring(0,2);

            //Tc row n0 227 8.Click on the colored marker for the student

            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            String prodColorMarkerProfByChap1=productivityReport.getProficiency().getText().trim();
            String prodColorMarkerProfByChapValue1= prodColorMarkerProfByChap1.substring(0,2);

            //Tc row no 228 9.Click on the colored marker  for the chapter
            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            System.out.println("pass");
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
            String mostObjectiveProficiency1=mostChallengingReport.getStudChapProficiency().get(1).getText().trim();
            Thread.sleep(5000);
            //Expected 2 "Learning Objective Performance" should be updated taking the changed grade for that assignment
            String mostObjectivePerformance1=mostChallengingReport.getStudChapPerformance().get(1).getText().trim();



            //Expected 1 Course Proficiency Summary' should be updated
            Assert.assertNotEquals(classProficiency,insClassProficiency,"Course Proficiency Summary' has not updated");

            //Expected 2 Course Proficiency By Chapter' should be updated
            Assert.assertNotEquals(classProficiencyChap,insClassProficiencyChap1,"Course Proficiency By Chapter' has not updated");

            //Expected 3 Chapter Proficiency Summary should be updated
            Assert.assertNotEquals(chapProficiencySummary,chapProficiencySummary1,"Chapter Proficiency Summary has not updated ");

            //Expected 4 Chapter Proficiency by Objective should be updated
            Assert.assertNotEquals(chapObjProficiency,chapObjProficiency1,"Chapter Proficiency by Objective has not updated");


            //Expected 5 The proficiency for a particular student should get updated in 'Proficiency' column with changed grades for that assignment.
            Assert.assertNotEquals(studMetaCogProfReport,studMetaCogProfReport1,"proficiency for a particular student has not  updated");
            //Expected 6 'Proficiency' in percentage should be updated.
            Assert.assertNotEquals(colorMarkerProfValue,colorMarkerProfValue1,"'Proficiency' in percentage has not  updated.");

            //Expected 7 Proficiency with respect to Chapter should be updated.
            Assert.assertNotEquals(colorMarkerProfByChapValue,colorMarkerProfByChapValue1,"Proficiency with respect to Chapter has not updated");

            //Expected 8 Proficiency with respect to Objective should be updated.
            Assert.assertNotEquals(colorMarkerProfByObjectiveValue,colorMarkerProfByObjectiveValue1,"Proficiency with respect to Objective is not updated.");

            //Expected 9 Proficiency' column should get updated.
            Assert.assertNotEquals(studProdCogProfReport,studProdCogProfReport1,"Proficiency' column is not updated");

            //Expected 10 Proficiency' in percentage with respect to Chapter should be updated.
            Assert.assertNotEquals(prodColorMarkerProfValue,prodColorMarkerProfValue1,"Proficiency' in percentage is not updated.");

            //Expected 11 Proficiency in  proficiency column with respect to Objective should be updated.
            Assert.assertNotEquals(prodColorMarkerProfByChapValue,prodColorMarkerProfByChapValue1,"Proficiency with respect to Chapter is not updated.");

            //Expected 12 Proficiency' in percentage with respect to Objective should be updated
            Assert.assertNotEquals(prodColorMarkerProfByObjectiveValue,prodColorMarkerProfByObjectiveValue1,"Proficiency with respect to Objective is not updated.");

            //Expected 13 Chapter Performance' should be updated taking the changed grade for that assignment.
            Assert.assertNotEquals(mostProficiency,mostProficiency1," Chapter Proficiency' has not updated in 'View By Chapters' tab");

            //Expected 14 Chapter Performance' should be updated taking the changed grade for that assignment.
            Assert.assertNotEquals(mostPerformance,mostPerformance1," Chapter Performance' has not updated in 'View By Chapters' tab");

            //Expected 15 "Learning Objective Proficiency" should be updated taking the changed grade for that assignment
            Assert.assertNotEquals(mostObjectiveProficiency,mostObjectiveProficiency1,"Learning Objective Proficiency has not updated  on 'View By Objectives' tab");

            //Expected 16 "Learning Objective Performance" should be updated taking the changed grade for that assignment
            Assert.assertNotEquals(mostObjectivePerformance,mostObjectivePerformance1,"Learning Objective Performance has not updated on 'View By Objectives' tab");


            new RunScheduledJobs().runScheduledJobsForDashboard();

            new LoginUsingLTI().ltiLogin("9");//login as instructor

            proficiencyReport =PageFactory.initElements(driver,ProficiencyReport.class);
            metacognitiveReport=PageFactory.initElements(driver,MetacognitiveReport.class);
            productivityReport=PageFactory.initElements(driver,ProductivityReport.class);
            mostChallengingReport=PageFactory.initElements(driver,MostChallengingReport.class);


            new Navigator().NavigateTo("Proficiency Report"); //Navigate to Proficiency Report
            String teacherProficiencyReport=proficiencyReport.getBarChart().getAttribute("height"); //teacherProficiencyReport
            System.out.println("Teacher Proficiency Report Before Update Grade:"+teacherProficiencyReport);
            String insStudProfReport=proficiencyReport.getStudProficiency().getText().trim();
            System.out.println("Student Proficiency Report Before Update Grade:"+insStudProfReport);

            //Tc row no 72&73
            //4. Click on the chapter number below the bar
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",proficiencyReport.getChapNumber());
            Thread.sleep(5000);
            String teacherObjProficiencyReport=proficiencyReport.getBarChart().getAttribute("height"); //teacherProficiencyReport
            System.out.println("Teacher Proficiency Report By Objective Before Update Grade:"+teacherObjProficiencyReport);
            String insStudObjProfReport=proficiencyReport.getStudProficiency().getText().trim();
            System.out.println("Student Proficiency Report  BY Objective Before Update Grade:" + insStudObjProfReport);

            //Tc row no 74&75
            //5. Click on the TLOs number in the bar
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",proficiencyReport.getTloNumber());
            Thread.sleep(5000);

            String insStudObjPerformance=proficiencyReport.getStudProficiency().getText().trim();
            System.out.println("Objective Performance By Students Before Update Grade:"+insStudObjPerformance);
            Thread.sleep(2000);

            //Tc row no 76
            //6. Navigate to 'Metacognitive Report
            //The proficiency for a particular student should get updated in 'Proficiency' column with changed grades for that assignment.
            new Navigator().NavigateTo("Metacognitive Report");//navigate to Metacognitive Report
            String insStudMetaCogProfReport= metacognitiveReport.getStudProficiency().getText().trim();
            System.out.println("Student MetaCogProfReport: "+insStudMetaCogProfReport);

            //Tc row no 78 7.Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());
            System.out.println("pass");
            String colorMarkerProf2=metacognitiveReport.getProficiency().getText().trim();
            String insColorMarkerProfValue= colorMarkerProf2.substring(13,15);
            System.out.println("Colored marker proficiency:"+insColorMarkerProfValue);

            //Tc row n0 79 8.Click on the colored marker for the student

            metacognitiveReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());
            System.out.println("pass");
            String colorMarkerProfByChap2=metacognitiveReport.getProficiency().getText().trim();
            String insColorMarkerProfByChapValue= colorMarkerProfByChap2.substring(13,15);
            System.out.println("Colored marker proficiency By chapter: "+insColorMarkerProfByChapValue);

            //Tc row no 80 9.Click on the colored marker  for the chapter
            metacognitiveReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());
            Thread.sleep(2000);
            String colorMarkerProfByObjective2=metacognitiveReport.getProficiency().getText().trim();
            String insColorMarkerProfByObjectiveValue= colorMarkerProfByObjective2.substring(13,15);
            System.out.println("Colored marker proficiency By Objective: "+insColorMarkerProfByObjectiveValue);

            //Tc row no 81
            //10.Navigate to 'Productivity Report'
            new Navigator().NavigateTo("Productivity Report"); //navigate to Productivity Report
            String studProdCogProfReport4= productivityReport.getStudProficiency().getText().trim();
            System.out.println("Student ProductivityCogProfReport: "+studProdCogProfReport4);

            //Tc row no 82 7.Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            System.out.println("pass");
            String prodColorMarkerProf2=productivityReport.getProficiency().getText().trim();
            String insProdColorMarkerProfValue= prodColorMarkerProf2.substring(0,2);
            System.out.println("Productivity Colored marker proficiency:"+insProdColorMarkerProfValue);

            //Tc row n0 83 8.Click on the colored marker for the student

            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            System.out.println("pass");
            String prodColorMarkerProfByChap2=productivityReport.getProficiency().getText().trim();
            String insProdColorMarkerProfByChapValue= prodColorMarkerProfByChap2.substring(0,2);
            System.out.println(" Productivity Colored marker proficiency By chapter:"+insProdColorMarkerProfByChapValue);

            //Tc row no 84 9.Click on the colored marker  for the chapter
            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            String prodColorMarkerProfByObjective2=productivityReport.getProficiency().getText().trim();
            String insProdColorMarkerProfByObjectiveValue= prodColorMarkerProfByObjective2.substring(0,2);
            System.out.println("Productivity Colored marker proficiency By Objective:"+insProdColorMarkerProfByObjectiveValue);



            new Assignment().provideGradeToStudentFromAssignmentResponsePage(10);
            new RunScheduledJobs().runScheduledJobsForDashboard();

            new LoginUsingLTI().ltiLogin("9");//login as instructor

            proficiencyReport =PageFactory.initElements(driver,ProficiencyReport.class);
            metacognitiveReport=PageFactory.initElements(driver,MetacognitiveReport.class);
            productivityReport=PageFactory.initElements(driver,ProductivityReport.class);
            mostChallengingReport=PageFactory.initElements(driver,MostChallengingReport.class);

            new Navigator().NavigateTo("Proficiency Report"); //Navigate to Proficiency Report
            String teacherProficiencyReport1=proficiencyReport.getBarChart().getAttribute("height"); //teacherProficiencyReport
            System.out.println("Teacher Proficiency Report Before Update Grade:"+teacherProficiencyReport1);
            String insStudProfReport1=proficiencyReport.getStudProficiency().getText().trim();
            System.out.println("Student Proficiency Report Before Update Grade:"+insStudProfReport1);

            //Tc row no 72&73
            //4. Click on the chapter number below the bar
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",proficiencyReport.getChapNumber());
            Thread.sleep(5000);
            String teacherObjProficiencyReport1=proficiencyReport.getBarChart().getAttribute("height"); //teacherProficiencyReport
            System.out.println("Teacher Proficiency Report By Objective Before Update Grade:"+teacherObjProficiencyReport1);
            String insStudObjProfReport1=proficiencyReport.getStudProficiency().getText().trim();
            System.out.println("Student Proficiency Report  BY Objective Before Update Grade:" + insStudObjProfReport1);

            //Tc row no 74&75
            //5. Click on the TLOs number in the bar
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",proficiencyReport.getTloNumber());
            Thread.sleep(5000);

            String insStudObjPerformance1=proficiencyReport.getStudProficiency().getText().trim();
            System.out.println("Objective Performance By Students Before Update Grade:"+insStudObjPerformance1);
            Thread.sleep(2000);

            //Tc row no 76
            //6. Navigate to 'Metacognitive Report
            //The proficiency for a particular student should get updated in 'Proficiency' column with changed grades for that assignment.
            new Navigator().NavigateTo("Metacognitive Report");//navigate to Metacognitive Report
            String insStudMetaCogProfReport1= metacognitiveReport.getStudProficiency().getText().trim();
            System.out.println("Student MetaCogProfReport: "+insStudMetaCogProfReport1);

            //Tc row no 78 7.Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());
            System.out.println("pass");
            String colorMarkerProf3=metacognitiveReport.getProficiency().getText().trim();
            String insColorMarkerProfValue1= colorMarkerProf3.substring(13,15);
            System.out.println("Colored marker proficiency:"+insColorMarkerProfValue1);

            //Tc row n0 79 8.Click on the colored marker for the student

            metacognitiveReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());
            System.out.println("pass");
            String colorMarkerProfByChap3=metacognitiveReport.getProficiency().getText().trim();
            String insColorMarkerProfByChapValue1= colorMarkerProfByChap3.substring(13,15);
            System.out.println("Colored marker proficiency By chapter: "+insColorMarkerProfByChapValue1);

            //Tc row no 80 9.Click on the colored marker  for the chapter
            metacognitiveReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());
            String colorMarkerProfByObjective3=metacognitiveReport.getProficiency().getText().trim();
            String insColorMarkerProfByObjectiveValue1= colorMarkerProfByObjective3.substring(13,15);
            System.out.println("Colored marker proficiency By Objective: "+insColorMarkerProfByObjectiveValue1);

            //Tc row no 81
            //10.Navigate to 'Productivity Report'
            new Navigator().NavigateTo("Productivity Report"); //navigate to Productivity Report
            String studProdCogProfReport2= productivityReport.getStudProficiency().getText().trim();
            System.out.println("Student ProductivityCogProfReport: "+studProdCogProfReport2);

            //Tc row no 82 7.Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            System.out.println("pass");
            String prodColorMarkerProf3=productivityReport.getProficiency().getText().trim();
            String insProdColorMarkerProfValue1= prodColorMarkerProf3.substring(0,2);
            System.out.println("Productivity Colored marker proficiency:"+insProdColorMarkerProfValue1);

            //Tc row n0 83 8.Click on the colored marker for the student

            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            System.out.println("pass");
            String prodColorMarkerProfByChap3=productivityReport.getProficiency().getText().trim();
            String insProdColorMarkerProfByChapValue1= prodColorMarkerProfByChap3.substring(0,2);
            System.out.println(" Productivity Colored marker proficiency By chapter:"+insProdColorMarkerProfByChapValue1);

            //Tc row no 84 9.Click on the colored marker  for the chapter
            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            String prodColorMarkerProfByObjective3=productivityReport.getProficiency().getText().trim();
            String insProdColorMarkerProfByObjectiveValue1= prodColorMarkerProfByObjective3.substring(0,2);
            System.out.println("Productivity Colored marker proficiency By Objective:"+insProdColorMarkerProfByObjectiveValue1);


            Assert.assertNotEquals(teacherProficiencyReport, teacherProficiencyReport1, " Class Proficiency By Chapter is not updated");

            //Expected 5 " Class Proficiency By Students should be updated."
            Assert.assertNotEquals(insStudProfReport,insStudObjProfReport1,"Class Proficiency By Students is not updated");

            //Expected 6 Class Proficiency By Objective should be updated
            Assert.assertNotEquals(teacherObjProficiencyReport,teacherObjProficiencyReport1,"Class Proficiency By Objective is not updated");

            //Expected 7 Chapter Proficiency By Students should be updated
            Assert.assertNotEquals(insStudObjProfReport,insStudObjProfReport1,"Chapter Proficiency By Students is not updated");

            //Expected 8 Class Performance by Question should be updated
            //Assert.assertNotEquals(classProficiencyReportByQuestion,classProficiencyReportByQuestion1,"Class Performance by Question is not updated");

            //Expected 9 Objective Performance by Students should be updated
            Assert.assertNotEquals(insStudObjPerformance,insStudObjPerformance1,"Objective Performance by Students is not updated");

            //Expected 10 The proficiency for a particular student should get updated in 'Proficiency' column with changed grades for that assignment.
            Assert.assertNotEquals(insStudMetaCogProfReport,insStudMetaCogProfReport1,"proficiency for a particular student is not update");

            //Expected 11  'Proficiency' in percentage should be updated.
            Assert.assertNotEquals(insColorMarkerProfValue,insColorMarkerProfValue1,"'Proficiency' in percentage is not updated.");

            // Expected 12  Proficiency with respect to Chapter should be updated.
            Assert.assertNotEquals(insColorMarkerProfByChapValue,insColorMarkerProfByChapValue1,"Proficiency with respect to Chapter is not updated");

            //Expected 13 Proficiency with respect to Objective should be updated.
            Assert.assertNotEquals(insColorMarkerProfByObjectiveValue,insColorMarkerProfByObjectiveValue1,"Proficiency with respect to Objective is not updated.");

            //Expected 14 Proficiency' column should get updated.
            //Assert.assertNotEquals(,insStudProdCogProfReport1,"Proficiency' column is not updated");

            //Expected 15 Proficiency' in percentage should be updated.
            Assert.assertNotEquals(insProdColorMarkerProfValue,insProdColorMarkerProfValue1,"Proficiency' in percentage is not updated.");

            //Expected 16 "Proficiency with respect to Chapter should be updated."
            Assert.assertNotEquals(insProdColorMarkerProfByChapValue,insProdColorMarkerProfByChapValue1,"Proficiency with respect to Chapter is not updated.");

            //Expected 17 Proficiency with respect to Objective should be updated.
            Assert.assertNotEquals(insProdColorMarkerProfByObjectiveValue,insProdColorMarkerProfByObjectiveValue1,"Proficiency with respect to Objective is not updated.");


          /*  //Expected 23 “Question performance summary” column should updated
            Assert.assertNotEquals(engagQuestionPercentage,engagQuestionPercentage1,"“Question performance summary” column has not updated in Engagement Reports");

            //Expected 24 “Total Grade” for each student should be updated taking the changed grade into account for that assignment
            Assert.assertNotEquals(totalGrade,totalGrade1,"“Total Grade” for each student has not updated in Engagement Reports");

            //Expected 25 Overall Score'should be updated for the particular student
            Assert.assertNotEquals(overallScore,overallScore1," Overall Score'has not  updated for the particular student in gradebook page");
*/

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class studentFeedbackInStaticAssessment in method StudentFeedbackInStaticAssessment.", e);
        }
    }
    @Test(priority = 2,enabled = true)
    public void studentReattemptStaticAssessment()
    {
        try
        {
            //Tc row no 66

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(66));
            Preview preview = PageFactory.initElements(driver, Preview.class);
            Assignments assignments = PageFactory.initElements(driver,Assignments.class);
            Questions question=PageFactory.initElements(driver,Questions.class);
            EngagementReport engagementReport = PageFactory.initElements(driver,EngagementReport.class);
            PerformanceSummaryReport performanceSummaryReport=PageFactory.initElements(driver,PerformanceSummaryReport.class);
            PerformanceTab performanceTab=PageFactory.initElements(driver, PerformanceTab.class);
            MyActivity myActivity=PageFactory.initElements(driver,MyActivity.class);

            ProficiencyReport proficiencyReport;
            MetacognitiveReport metacognitiveReport;
            ProductivityReport productivityReport;
            MostChallengingReport mostChallengingReport;
            proficiencyReport = PageFactory.initElements(driver, ProficiencyReport.class);
            metacognitiveReport=PageFactory.initElements(driver,MetacognitiveReport.class);
            productivityReport=PageFactory.initElements(driver,ProductivityReport.class);
            mostChallengingReport=PageFactory.initElements(driver,MostChallengingReport.class);


            new LoginUsingLTI().ltiLogin("66_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //Navigate to e_textbook
            Thread.sleep(3000);
            new TopicOpen().chapterOpen(1); //open second chapter
            new SelectCourse().selectInvisibleAssignment(assessmentname); //select assessment

            drawSquareInWriteBoardInStudentSide(66);
            question.crossIcon.click(); //click on x icon
            preview.trueFalseAnswerLabel.get(0).click();//click on answer option 'A'
            new Assignment().submitButtonInQuestionClick();//click on submit
            new Assignment().nextButtonInQuestionClick(); //click on the next button

            new AttemptTest().StaticTestAttemptAllTypeWithConfidence(2,false,false);

            List<WebElement> allQuestionCard = driver.findElements(By.className("question-card-question-content"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", allQuestionCard.get(11));//click on question card
            Thread.sleep(5000);
            String correctColor= allQuestionCard.get(11).getCssValue("background-color");
            System.out.println("pass");
            System.out.println("correct question color:" + correctColor);

            //Tc row no 21
            new Navigator().NavigateTo("Proficiency Report"); //Navigate to Proficiency Report
            String classProficiencyChap=proficiencyReport.getBarChart().getAttribute("height");
            String classProficiency=proficiencyReport.getCourseProficiency().getText().trim();

            //Tc row no 25
            //7.Click on Chapter bar in 'Course Proficiency By Chapter'
            driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")).click();//Click on Chapter bar in 'Course Proficiency By Chapter'
            Thread.sleep(5000);
            String chapProficiencySummary=proficiencyReport.getCourseProficiency().getText().trim();

            String chapObjProficiency=proficiencyReport.getBarChart().getAttribute("height"); //teacherProficiencyReport

            //Tc row no 29
            // 8.Click on Objective bar in 'Chapter Proficiency By Objectives'
            driver.findElement(By.cssSelector("g.highcharts-series.highcharts-tracker > rect")).click(); //Click on Objective bar in 'Chapter Proficiency By Objectives'


            //Tc row no 31
            //9.Click on Reports and select 'Metacognitive Report'
            //The proficiency for a particular student should get updated in 'Proficiency' column with changed grades for that assignment.
            new Navigator().NavigateTo("Metacognitive Report");//navigate to Metacognitive Report
            String studMetaCogProfReport= metacognitiveReport.getStudProfPercentage().getText().trim();

            //.Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());
            System.out.println("pass");
            String colorMarkerProf=metacognitiveReport.getProficiency().getText().trim();
            String colorMarkerProfValue= colorMarkerProf.substring(13,15);

            //Click on the colored marker for the student

            metacognitiveReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());
            System.out.println("pass");
            String colorMarkerProfByChap=metacognitiveReport.getProficiency().getText().trim();
            String colorMarkerProfByChapValue= colorMarkerProfByChap.substring(13,15);

            //Click on the colored marker  for the chapter
            metacognitiveReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());
            System.out.println("pass");
            String colorMarkerProfByObjective=metacognitiveReport.getProficiency().getText().trim();
            String colorMarkerProfByObjectiveValue= colorMarkerProfByObjective.substring(13,15);

            //Tc row no 36
            //10.Navigate to 'Productivity Report'
            new Navigator().NavigateTo("Productivity Report"); //navigate to Productivity Report
            String studProdCogProfReport= productivityReport.getStudProfPercentage().getText().trim();

            //.Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            System.out.println("pass");
            String prodColorMarkerProf=productivityReport.getProficiency().getText().trim();
            String prodColorMarkerProfValue= prodColorMarkerProf.substring(0,2);

            //Click on the colored marker for the student

            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            System.out.println("pass");
            String prodColorMarkerProfByChap=productivityReport.getProficiency().getText().trim();
            String prodColorMarkerProfByChapValue= prodColorMarkerProfByChap.substring(0,2);

            //Click on the colored marker  for the chapter
            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            System.out.println("pass");
            String prodColorMarkerProfByObjective=productivityReport.getProficiency().getText().trim();
            String prodColorMarkerProfByObjectiveValue= prodColorMarkerProfByObjective.substring(0,2);

            //Tc row no 40
            new Navigator().NavigateTo("Most Challenging Activities Report"); //navigate to Most Challenging Activities Report
            //Click on 'View By Chapters' tab
            //Expected1 Chapter Proficiency' should be updated taking the changed grade for that assignment.
            String mostProficiency=mostChallengingReport.getStudChapProficiency().get(0).getText().trim();

            //Expected 2 Chapter Performance' should be updated taking the changed grade for that assignment.
            String mostPerformance=mostChallengingReport.getStudChapPerformance().get(0).getText().trim();
            //Tc row no 88
            //16. Click on 'View By Objectives' tab
            mostChallengingReport.getStudViewObjective_Tab().click();//click on the view Objective tab
            String mostObjectiveProficiency=mostChallengingReport.getStudChapProficiency().get(1).getText().trim();
            Thread.sleep(5000);
            //Expected 2 "Learning Objective Performance" should be updated taking the changed grade for that assignment
            String mostObjectivePerformance=mostChallengingReport.getStudChapPerformance().get(1).getText().trim();


            new LoginUsingLTI().ltiLogin("66");//login as instructor
            new Navigator().NavigateTo("Engagement Report");//navigate to current Assignment
            Thread.sleep(2000);
            driver.findElement(By.xpath("(//div[@class='row-non-assigned-status']/div[2]/span)[2]")).click(); //click on the assignment count
            new TopicOpen().chapterOpen(1); //select Chapter from testData
            new SelectCourse().selectInvisibleAssignment(assessmentname); //select assessment from testData
            Thread.sleep(5000);
            provideFeedbackToAllQuestion(66);
            new Assignment().provideGradeToStudentFromAssignmentResponsePage(66);

            new LoginUsingLTI().ltiLogin("66_1");//login as student
            new Navigator().NavigateTo("e-Textbook"); //Navigate to e_textbook
            Thread.sleep(3000);
            new TopicOpen().chapterOpen(1); //open second chapter
            new SelectCourse().selectInvisibleAssignment(assessmentname); //select assessment
           Thread.sleep(3000);
            performanceTab.reTake_button.click(); //click on the retake button

            drawSquareInWriteBoardInStudentSide(66);
            question.crossIcon.click(); //click on x icon
            preview.trueFalseAnswerLabel.get(1).click();//click on answer option 'B'
            new Assignment().submitButtonInQuestionClick();//click on submit
            new Assignment().nextButtonInQuestionClick(); //click on the next button

            new AttemptTest().StaticTestAttemptAllTypeWithConfidence(2,false,false);

            new LoginUsingLTI().ltiLogin("66");//login as instructor
            new Navigator().NavigateTo("Engagement Report");//navigate to current Assignment
            Thread.sleep(2000);
            driver.findElement(By.xpath("(//div[@class='row-non-assigned-status']/div[2]/span)[2]")).click(); //click on the assignment count
            new TopicOpen().chapterOpen(1); //select Chapter from testData
            new SelectCourse().selectInvisibleAssignment(assessmentname); //select assessment from testData
            Thread.sleep(5000);
            provideFeedbackToAllQuestion(66);
            new Assignment().provideGradeToStudentFromAssignmentResponsePage(67);


            new LoginUsingLTI().ltiLogin("66_1");
            new Navigator().NavigateTo("My Activity");//navigate to  My Activity
            myActivity.assessmentLink.click();//click on the assessment link

            //Tc row no 9
            String summaryPage=performanceSummaryReport.performanceSummaryTitle.getText().trim();
            System.out.println("Summary Page:"+summaryPage);
            if(!summaryPage.contains("Performance Summary"))
                Assert.fail("Student is not able to navigate to Performance Summary page");

            //Tc row no 10
            int indicatorSize=performanceTab.visualIndicator_Icon.size();
            Assert.assertEquals(indicatorSize,8,"Visual indicator is not displayed on question card");

            //Tc row no 13

            String toolTip=performanceTab.visualIndicator_Icon.get(4).getAttribute("title");
            Assert.assertEquals(toolTip,"Score/feedback has been added by your instructor","Tooltip of visual indicator is not a “Score/feedback has been added by your instructor”");

            //Tc row no 14
            new QuestionCard().clickOnInvisibleCard("14",11);
            Thread.sleep(3000);

            //Tc row no 15
            String feedback=performanceTab.teacher_feedback.getText().trim();
            Assert.assertEquals(feedback,"This is a FeedbackText","Student is not able to view the teacher feedback");

            String mark=performanceTab.teacher_mark.getText().trim();
            if(!mark.contains("Marks Awarded 0.5/1.0"))
                Assert.fail("Student is not able to view the grade added by teacher");


            System.out.println("*************************");


            new Navigator().NavigateTo("Proficiency Report"); //Navigate to Proficiency Report
            String insClassProficiency=proficiencyReport.getCourseProficiency().getText().trim();
            String insClassProficiencyChap1=proficiencyReport.getBarChart().getAttribute("height"); //teacherProficiencyReport

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

            //Tc row no 220
            //9.Click on Reports and select 'Metacognitive Report'
            //The proficiency for a particular student should get updated in 'Proficiency' column with changed grades for that assignment.
            new Navigator().NavigateTo("Metacognitive Report");//navigate to Metacognitive Report
            String studMetaCogProfReport1= metacognitiveReport.getStudProfPercentage().getText().trim();
            Thread.sleep(2000);
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
            System.out.println("pass");
            String colorMarkerProfByObjective1=metacognitiveReport.getProficiency().getText().trim();
            String colorMarkerProfByObjectiveValue1= colorMarkerProfByObjective1.substring(13,15);

            //Tc row no 225
            //10.Navigate to 'Productivity Report'
            new Navigator().NavigateTo("Productivity Report"); //navigate to Productivity Report
            String studProdCogProfReport1= productivityReport.getStudProfPercentage().getText().trim();

            //Tc row no 226 7.Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            System.out.println("pass");
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
            System.out.println("pass");
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
            String mostObjectiveProficiency1=mostChallengingReport.getStudChapProficiency().get(1).getText().trim();
            Thread.sleep(5000);
            //Expected 2 "Learning Objective Performance" should be updated taking the changed grade for that assignment
            String mostObjectivePerformance1=mostChallengingReport.getStudChapPerformance().get(1).getText().trim();



            //Expected 1 Course Proficiency Summary' should be updated
            Assert.assertNotEquals(classProficiency,insClassProficiency,"Course Proficiency Summary' has not updated");

            //Expected 2 Course Proficiency By Chapter' should be updated
            Assert.assertNotEquals(classProficiencyChap,insClassProficiencyChap1,"Course Proficiency By Chapter' has not updated");

            //Expected 3 Chapter Proficiency Summary should be updated
            Assert.assertNotEquals(chapProficiencySummary,chapProficiencySummary1,"Chapter Proficiency Summary has not updated ");

            //Expected 4 Chapter Proficiency by Objective should be updated
            Assert.assertNotEquals(chapObjProficiency,chapObjProficiency1,"Chapter Proficiency by Objective has not updated");


            //Expected 5 The proficiency for a particular student should get updated in 'Proficiency' column with changed grades for that assignment.
            Assert.assertNotEquals(studMetaCogProfReport,studMetaCogProfReport1,"proficiency for a particular student has not  updated");
            //Expected 6 'Proficiency' in percentage should be updated.
            Assert.assertNotEquals(colorMarkerProfValue,colorMarkerProfValue1,"'Proficiency' in percentage has not  updated.");

            //Expected 7 Proficiency with respect to Chapter should be updated.
            Assert.assertNotEquals(colorMarkerProfByChapValue,colorMarkerProfByChapValue1,"Proficiency with respect to Chapter has not updated");

            //Expected 8 Proficiency with respect to Objective should be updated.
            Assert.assertNotEquals(colorMarkerProfByObjectiveValue,colorMarkerProfByObjectiveValue1,"Proficiency with respect to Objective is not updated.");

            //Expected 9 Proficiency' column should get updated.
            Assert.assertNotEquals(studProdCogProfReport,studProdCogProfReport1,"Proficiency' column is not updated");

            //Expected 10 Proficiency' in percentage with respect to Chapter should be updated.
            Assert.assertNotEquals(prodColorMarkerProfValue,prodColorMarkerProfValue1,"Proficiency' in percentage is not updated.");

            //Expected 11 Proficiency in  proficiency column with respect to Objective should be updated.
            Assert.assertNotEquals(prodColorMarkerProfByChapValue,prodColorMarkerProfByChapValue1,"Proficiency with respect to Chapter is not updated.");

            //Expected 12 Proficiency' in percentage with respect to Objective should be updated
            Assert.assertNotEquals(prodColorMarkerProfByObjectiveValue,prodColorMarkerProfByObjectiveValue1,"Proficiency with respect to Objective is not updated.");

            //Expected 13 Chapter Performance' should be updated taking the changed grade for that assignment.
            Assert.assertNotEquals(mostProficiency,mostProficiency1," Chapter Proficiency' has not updated in 'View By Chapters' tab");

            //Expected 14 Chapter Performance' should be updated taking the changed grade for that assignment.
            Assert.assertNotEquals(mostPerformance,mostPerformance1," Chapter Performance' has not updated in 'View By Chapters' tab");

            //Expected 15 "Learning Objective Proficiency" should be updated taking the changed grade for that assignment
            Assert.assertNotEquals(mostObjectiveProficiency,mostObjectiveProficiency1,"Learning Objective Proficiency has not updated  on 'View By Objectives' tab");

            //Expected 16 "Learning Objective Performance" should be updated taking the changed grade for that assignment
            Assert.assertNotEquals(mostObjectivePerformance,mostObjectivePerformance1,"Learning Objective Performance has not updated on 'View By Objectives' tab");


           new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().chapterOpen(1); //select Chapter from testData
            new SelectCourse().selectInvisibleAssignment(assessmentname); //select assessment from testData

            //Tc row no 14
            new QuestionCard().clickOnInvisibleCard("14",11);
            Thread.sleep(3000);

            //Tc row no 15
            String feedback1=performanceTab.teacher_feedback.getText().trim();
            Assert.assertEquals(feedback1,"This is a FeedbackText","Student is not able to view the teacher feedback");

            String mark1=performanceTab.teacher_mark.getText().trim();
            if(!mark1.contains("Marks Awarded 0.5/1.0"))
                Assert.fail("Student is not able to view the grade added by teacher");


            new RunScheduledJobs().runScheduledJobsForDashboard();

            new LoginUsingLTI().ltiLogin("66");//login as instructor

            proficiencyReport =PageFactory.initElements(driver,ProficiencyReport.class);
            metacognitiveReport=PageFactory.initElements(driver,MetacognitiveReport.class);
            productivityReport=PageFactory.initElements(driver,ProductivityReport.class);
            mostChallengingReport=PageFactory.initElements(driver,MostChallengingReport.class);
            engagementReport=PageFactory.initElements(driver,EngagementReport.class);


            new Navigator().NavigateTo("Proficiency Report"); //Navigate to Proficiency Report
            String teacherProficiencyReport=proficiencyReport.getBarChart().getAttribute("height"); //teacherProficiencyReport
            System.out.println("Teacher Proficiency Report Before Update Grade:"+teacherProficiencyReport);
            String insStudProfReport=proficiencyReport.getStudProficiency().getText().trim();
            System.out.println("Student Proficiency Report Before Update Grade:"+insStudProfReport);

            //Tc row no 72&73
            //4. Click on the chapter number below the bar
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",proficiencyReport.getChapNumber());
            Thread.sleep(5000);
            String teacherObjProficiencyReport=proficiencyReport.getBarChart().getAttribute("height"); //teacherProficiencyReport
            System.out.println("Teacher Proficiency Report By Objective Before Update Grade:"+teacherObjProficiencyReport);
            String insStudObjProfReport=proficiencyReport.getStudProficiency().getText().trim();
            System.out.println("Student Proficiency Report  BY Objective Before Update Grade:" + insStudObjProfReport);

            //Tc row no 74&75
            //5. Click on the TLOs number in the bar
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",proficiencyReport.getTloNumber());
            Thread.sleep(5000);

            String insStudObjPerformance=proficiencyReport.getStudProficiency().getText().trim();
            System.out.println("Objective Performance By Students Before Update Grade:"+insStudObjPerformance);
            Thread.sleep(2000);

            //Tc row no 76
            //6. Navigate to 'Metacognitive Report
            //The proficiency for a particular student should get updated in 'Proficiency' column with changed grades for that assignment.
            new Navigator().NavigateTo("Metacognitive Report");//navigate to Metacognitive Report
            String insStudMetaCogProfReport= metacognitiveReport.getStudProficiency().getText().trim();
            System.out.println("Student MetaCogProfReport: "+insStudMetaCogProfReport);

            //Tc row no 78 7.Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());
            System.out.println("pass");
            String colorMarkerProf2=metacognitiveReport.getProficiency().getText().trim();
            String insColorMarkerProfValue= colorMarkerProf2.substring(13,15);
            System.out.println("Colored marker proficiency:"+insColorMarkerProfValue);

            //Tc row n0 79 8.Click on the colored marker for the student

            metacognitiveReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());
            System.out.println("pass");
            String colorMarkerProfByChap2=metacognitiveReport.getProficiency().getText().trim();
            String insColorMarkerProfByChapValue= colorMarkerProfByChap2.substring(13,15);
            System.out.println("Colored marker proficiency By chapter: "+insColorMarkerProfByChapValue);

            //Tc row no 80 9.Click on the colored marker  for the chapter
            metacognitiveReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());
            String colorMarkerProfByObjective2=metacognitiveReport.getProficiency().getText().trim();
            String insColorMarkerProfByObjectiveValue= colorMarkerProfByObjective2.substring(13,15);
            System.out.println("Colored marker proficiency By Objective: "+insColorMarkerProfByObjectiveValue);

            //Tc row no 81
            //10.Navigate to 'Productivity Report'
            new Navigator().NavigateTo("Productivity Report"); //navigate to Productivity Report
            String studProdCogProfReport4= productivityReport.getStudProficiency().getText().trim();
            System.out.println("Student ProductivityCogProfReport: "+studProdCogProfReport4);

            //Tc row no 82 7.Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            System.out.println("pass");
            String prodColorMarkerProf2=productivityReport.getProficiency().getText().trim();
            String insProdColorMarkerProfValue= prodColorMarkerProf2.substring(0,2);
            System.out.println("Productivity Colored marker proficiency:"+insProdColorMarkerProfValue);

            //Tc row n0 83 8.Click on the colored marker for the student

            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            System.out.println("pass");
            String prodColorMarkerProfByChap2=productivityReport.getProficiency().getText().trim();
            String insProdColorMarkerProfByChapValue= prodColorMarkerProfByChap2.substring(0,2);
            System.out.println(" Productivity Colored marker proficiency By chapter:"+insProdColorMarkerProfByChapValue);

            //Tc row no 84 9.Click on the colored marker  for the chapter
            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            String prodColorMarkerProfByObjective2=productivityReport.getProficiency().getText().trim();
            String insProdColorMarkerProfByObjectiveValue= prodColorMarkerProfByObjective2.substring(0,2);
            System.out.println("Productivity Colored marker proficiency By Objective:"+insProdColorMarkerProfByObjectiveValue);

            //Tc row no 85 14. Navigate to 'Most Challenging Activity Reports'

          /*  new Navigator().NavigateTo("Most Challenging Activities Report"); //navigate to Most Challenging Activities Report
            //Tc row no 86 15. Click on 'View By Chapters' tab
            //Expected1 Chapter Proficiency' should be updated taking the changed grade for that assignment.
            String insMostProficiency=mostChallengingReport.getChapProficiency().get(0).getText().trim();
            System.out.println("Most Challenging proficiency By chapter:"+insMostProficiency);

            //Expected 2 Chapter Performance' should be updated taking the changed grade for that assignment.

            String insMostPerformance=mostChallengingReport.getChapPerformance().get(0).getText().trim();
            System.out.println("pass");
            System.out.println("Most Challenging performance By chapter:"+insMostPerformance);

            //Tc row no 88
            //16. Click on 'View By Objectives' tab
            mostChallengingReport.getViewByObjective_Tab().click();//click on the view Objective tab
            System.out.println("Pass");
            String insMostObjectiveProficiency=mostChallengingReport.getChapProficiency().get(1).getText().trim();
            System.out.println("Most Challenging proficiency By Objective:" + insMostObjectiveProficiency);
            Thread.sleep(5000);
            //Expected 2 "Learning Objective Performance" should be updated taking the changed grade for that assignment
            String insMostObjectivePerformance=mostChallengingReport.getChapPerformance().get(1).getText().trim();
            System.out.println("pass");
            System.out.println("Most Challenging performance By Objective:"+insMostObjectivePerformance);*/

            new Navigator().NavigateTo("Engagement Report");//navigate to Engagement Report
            String engagQuestionPercentage= engagementReport.getQuestionPerSummary().getText().trim();



            new Assignment().provideGradeToStudentFromAssignmentResponsePage(68);
            new RunScheduledJobs().runScheduledJobsForDashboard();

            new LoginUsingLTI().ltiLogin("66");//login as instructor

            proficiencyReport =PageFactory.initElements(driver,ProficiencyReport.class);
            metacognitiveReport=PageFactory.initElements(driver,MetacognitiveReport.class);
            productivityReport=PageFactory.initElements(driver,ProductivityReport.class);
            mostChallengingReport=PageFactory.initElements(driver,MostChallengingReport.class);
            engagementReport=PageFactory.initElements(driver,EngagementReport.class);

            new Navigator().NavigateTo("Proficiency Report"); //Navigate to Proficiency Report
            String teacherProficiencyReport1=proficiencyReport.getBarChart().getAttribute("height"); //teacherProficiencyReport
            System.out.println("Teacher Proficiency Report Before Update Grade:"+teacherProficiencyReport1);
            String insStudProfReport1=proficiencyReport.getStudProficiency().getText().trim();
            System.out.println("Student Proficiency Report Before Update Grade:"+insStudProfReport1);

            //Tc row no 72&73
            //4. Click on the chapter number below the bar
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",proficiencyReport.getChapNumber());
            Thread.sleep(5000);
            String teacherObjProficiencyReport1=proficiencyReport.getBarChart().getAttribute("height"); //teacherProficiencyReport
            System.out.println("Teacher Proficiency Report By Objective Before Update Grade:"+teacherObjProficiencyReport1);
            String insStudObjProfReport1=proficiencyReport.getStudProficiency().getText().trim();
            System.out.println("Student Proficiency Report  BY Objective Before Update Grade:" + insStudObjProfReport1);

            //Tc row no 74&75
            //5. Click on the TLOs number in the bar
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",proficiencyReport.getTloNumber());
            Thread.sleep(5000);

            String insStudObjPerformance1=proficiencyReport.getStudProficiency().getText().trim();
            System.out.println("Objective Performance By Students Before Update Grade:"+insStudObjPerformance1);
            Thread.sleep(2000);

            //Tc row no 76
            //6. Navigate to 'Metacognitive Report
            //The proficiency for a particular student should get updated in 'Proficiency' column with changed grades for that assignment.
            new Navigator().NavigateTo("Metacognitive Report");//navigate to Metacognitive Report
            String insStudMetaCogProfReport1= metacognitiveReport.getStudProficiency().getText().trim();
            System.out.println("Student MetaCogProfReport: "+insStudMetaCogProfReport1);

            //Tc row no 78 7.Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());
            System.out.println("pass");
            String colorMarkerProf3=metacognitiveReport.getProficiency().getText().trim();
            String insColorMarkerProfValue1= colorMarkerProf3.substring(13,15);
            System.out.println("Colored marker proficiency:"+insColorMarkerProfValue1);

            //Tc row n0 79 8.Click on the colored marker for the student

            metacognitiveReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());
            System.out.println("pass");
            String colorMarkerProfByChap3=metacognitiveReport.getProficiency().getText().trim();
            String insColorMarkerProfByChapValue1= colorMarkerProfByChap3.substring(13,15);
            System.out.println("Colored marker proficiency By chapter: "+insColorMarkerProfByChapValue1);

            //Tc row no 80 9.Click on the colored marker  for the chapter
            metacognitiveReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(metacognitiveReport.getColoredMarker());
            String colorMarkerProfByObjective3=metacognitiveReport.getProficiency().getText().trim();
            String insColorMarkerProfByObjectiveValue1= colorMarkerProfByObjective3.substring(13,15);
            System.out.println("Colored marker proficiency By Objective: "+insColorMarkerProfByObjectiveValue1);

            //Tc row no 81
            //10.Navigate to 'Productivity Report'
            new Navigator().NavigateTo("Productivity Report"); //navigate to Productivity Report
            String studProdCogProfReport2= productivityReport.getStudProficiency().getText().trim();
            System.out.println("Student ProductivityCogProfReport: "+studProdCogProfReport2);

            //Tc row no 82 7.Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            System.out.println("pass");
            String prodColorMarkerProf3=productivityReport.getProficiency().getText().trim();
            String insProdColorMarkerProfValue1= prodColorMarkerProf3.substring(0,2);
            System.out.println("Productivity Colored marker proficiency:"+insProdColorMarkerProfValue1);

            //Tc row n0 83 8.Click on the colored marker for the student

            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            System.out.println("pass");
            String prodColorMarkerProfByChap3=productivityReport.getProficiency().getText().trim();
            String insProdColorMarkerProfByChapValue1= prodColorMarkerProfByChap3.substring(0,2);
            System.out.println(" Productivity Colored marker proficiency By chapter:"+insProdColorMarkerProfByChapValue1);

            //Tc row no 84 9.Click on the colored marker  for the chapter
            productivityReport.getColoredMarker().click();//click on the colored marker
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(productivityReport.getColoredMarker());
            String prodColorMarkerProfByObjective3=productivityReport.getProficiency().getText().trim();
            String insProdColorMarkerProfByObjectiveValue1= prodColorMarkerProfByObjective3.substring(0,2);
            System.out.println("Productivity Colored marker proficiency By Objective:"+insProdColorMarkerProfByObjectiveValue1);

            new Navigator().NavigateTo("Engagement Report");//navigate to Engagement Report
            String engagQuestionPercentage1= engagementReport.getQuestionPerSummary().getText().trim();


            Assert.assertNotEquals(teacherProficiencyReport, teacherProficiencyReport1, " Class Proficiency By Chapter is not updated");

            //Expected 5 " Class Proficiency By Students should be updated."
            Assert.assertNotEquals(insStudProfReport,insStudObjProfReport1,"Class Proficiency By Students is not updated");

            //Expected 6 Class Proficiency By Objective should be updated
            Assert.assertNotEquals(teacherObjProficiencyReport,teacherObjProficiencyReport1,"Class Proficiency By Objective is not updated");

            //Expected 7 Chapter Proficiency By Students should be updated
            Assert.assertNotEquals(insStudObjProfReport,insStudObjProfReport1,"Chapter Proficiency By Students is not updated");

            //Expected 8 Class Performance by Question should be updated
            //Assert.assertNotEquals(classProficiencyReportByQuestion,classProficiencyReportByQuestion1,"Class Performance by Question is not updated");

            //Expected 9 Objective Performance by Students should be updated
            Assert.assertNotEquals(insStudObjPerformance,insStudObjPerformance1,"Objective Performance by Students is not updated");

            //Expected 10 The proficiency for a particular student should get updated in 'Proficiency' column with changed grades for that assignment.
            Assert.assertNotEquals(insStudMetaCogProfReport,insStudMetaCogProfReport1,"proficiency for a particular student is not update");

            //Expected 11  'Proficiency' in percentage should be updated.
            Assert.assertNotEquals(insColorMarkerProfValue,insColorMarkerProfValue1,"'Proficiency' in percentage is not updated.");

            // Expected 12  Proficiency with respect to Chapter should be updated.
            Assert.assertNotEquals(insColorMarkerProfByChapValue,insColorMarkerProfByChapValue1,"Proficiency with respect to Chapter is not updated");

            //Expected 13 Proficiency with respect to Objective should be updated.
            Assert.assertNotEquals(insColorMarkerProfByObjectiveValue,insColorMarkerProfByObjectiveValue1,"Proficiency with respect to Objective is not updated.");

            //Expected 14 Proficiency' column should get updated.
            //Assert.assertNotEquals(,insStudProdCogProfReport1,"Proficiency' column is not updated");

            //Expected 15 Proficiency' in percentage should be updated.
            Assert.assertNotEquals(insProdColorMarkerProfValue,insProdColorMarkerProfValue1,"Proficiency' in percentage is not updated.");

            //Expected 16 "Proficiency with respect to Chapter should be updated."
            Assert.assertNotEquals(insProdColorMarkerProfByChapValue,insProdColorMarkerProfByChapValue1,"Proficiency with respect to Chapter is not updated.");

            //Expected 17 Proficiency with respect to Objective should be updated.
            Assert.assertNotEquals(insProdColorMarkerProfByObjectiveValue,insProdColorMarkerProfByObjectiveValue1,"Proficiency with respect to Objective is not updated.");


           //Expected 23 “Question performance summary” column should updated
          //Assert.assertNotEquals(engagQuestionPercentage,engagQuestionPercentage1,"“Question performance summary” column has not updated in Engagement Reports");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in class studentFeedbackInStaticAssessment in method StudentFeedbackInStaticAssessment.", e);
        }
    }


    public void provideFeedbackToAllQuestion(int dataIndex)
    {
        int index = 0;
        List<WebElement> provideFeedback=driver.findElements(By.cssSelector("div[class='idb-gradebook-content-coloumn']"));
        for(WebElement eachFeedback:provideFeedback)
        {
            for(int i = index; i < provideFeedback.size(); i++){
                new MouseHover().mouserHoverByClassList("idb-gradebook-question-content", index);
                index++;
                System.out.println("hovered "+i);
                break;
            }
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link")));
            driver.findElement(By.id("view-user-question-performance-feedback-box")).sendKeys("This is a FeedbackText");
            driver.findElement(By.className("view-user-question-performance-save-btn")).click();
            new Click().clickByid("close-view-responses"); //close tab
        }
    }


    public void drawSquareInWriteBoardInStudentSide(int dataIndex) {

        try
        {
            new Click().clickbyxpath("//span[@title='Workboard']");//click on the workboard
            driver.switchTo().frame(driver.findElement(By.xpath("//iframe[starts-with(@id,'whiteBoard_iframe_kedukWBSTUDENT')]")));
            new Click().clickByid("square-btn");//click inside the board
            Thread.sleep(2000);
            String widthvalue= driver.findElement(By.xpath("//canvas")).getAttribute("width");
            driver.findElement(By.cssSelector("canvas[width='"+widthvalue+"']")).click();
            Thread.sleep(3000);
            driver.switchTo().defaultContent();
            driver.findElement(By.xpath("/html/body")).click();
            Thread.sleep(2000);

        } catch (Exception e) {
            Assert.fail("Exception in apphelper drawSquareInWriteBoardInstructorSide in class WriteBoard.", e);
        }
    }

}
