package com.snapwiz.selenium.tests.staf.orion.testcases.regression.orion;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.orion.apphelper.*;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.Preview.Preview;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.reports.MetaCognitiveReport;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.reports.MostChallengingActivities;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.reports.PerformanceSummaryReport;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.reports.ProductivityReport;
import com.snapwiz.selenium.tests.staf.orion.uihelper.MouseHover;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mukesh on 28/1/16.
 */

public class StudentDiagnosticReports extends Driver {

    String actual="";
    String expected="";
    int actual_int=0;
    int expected_int=0;
    String studProdCogProfReport="";
    String prodColorMarkerProfByChap="";
    String prodColorMarkerProf="";
    String prodColorMarkerProfValue="";
    String prodColorMarkerProfByChapValue="";
    String prodColorMarkerProfByObjective="";
    String prodColorMarkerProfByObjectiveValue="";

    @Test(priority = 1,enabled = true)
    public void quitDiagnosticAndVerifyPerformanceReport(){
        WebDriver driver=Driver.getWebDriver();
        try {
            Preview preview;
            preview= PageFactory.initElements(driver,Preview.class);
            MetaCognitiveReport metaCognitiveReport;
            metaCognitiveReport= PageFactory.initElements(driver,MetaCognitiveReport.class);
            ProductivityReport productivityReport;
            productivityReport= PageFactory.initElements(driver,ProductivityReport.class);
            MostChallengingActivities mostChallengingReport;
            mostChallengingReport= PageFactory.initElements(driver,MostChallengingActivities.class);
            PerformanceSummaryReport performanceSummaryReport= PageFactory.initElements(driver,PerformanceSummaryReport.class);

            new LoginUsingLTI().ltiLogin("1");//login as student
            ReportUtil.log("Login Via LTI", "Instructor logged successfully", "info");
            new DiagnosticTest().startTest(0,2); //start first chapter diag test
            new DiagnosticTest().DiagonesticTestQuitBetween(3, 12, "correct", false, false, true);//attempt diagnostic test

            new Navigator().NavigateToStudentReport();
            new Navigator().NavigateToAllReport(3);
            Thread.sleep(2000);
            new WebDriverUtil().waitTillVisibilityOfElement(performanceSummaryReport.QuestionBar.get(0), 40);
            actual=performanceSummaryReport.title_performanceSummary.getText().trim();
            expected="Course Performance Summary";
            CustomAssert.assertEquals(actual,expected,"Verify Performance title","Performance header label is displaying \"Course Performance Summary\" correctly","Performance header label is not displaying \"Course Performance Summary\"");
            new WebDriverWait(driver,10).until(ExpectedConditions.invisibilityOfElementLocated(By.id("al-footer")));//wait un till robo pop ub to be closed
            performanceSummaryReport.QuestionBar.get(0).click(); //click on the chapter bar chart
            Thread.sleep(9000);
            actual_int=preview.questionCard_questionName.size();
            expected_int=12;
            CustomAssert.assertEquals(actual_int,expected_int,"Verify question card","No of Question card is displaying correctly","No of Question content is not displaying correctly");

            performanceSummaryReport= PageFactory.initElements(driver,PerformanceSummaryReport.class); //reinitialize the objects
            actual=performanceSummaryReport.title_performanceSummary.getText().trim();
            expected="Chapter Performance Summary";
            CustomAssert.assertEquals(actual,expected,"Verify Performance title","Performance header label is displaying \"Chapter Performance Summary\" correctly","Performance header label is not displaying \"Chapter Performance Summary\"");

            actual=performanceSummaryReport.performance_title.getText().trim();
            expected="Ch 1: The Changing Face of Business";
            CustomAssert.assertEquals(actual,expected,"Verify chapter Name","Chapter name is displaying as "+expected+"","Chapter name is not displaying as \"+expected+\"");


            new WebDriverUtil().waitTillVisibilityOfElement(performanceSummaryReport.performance_title,40);
            performanceSummaryReport.QuestionBar.get(0).click(); //click on the objective bar chart
            Thread.sleep(4000);
            CustomAssert.assertEquals(actual_int,expected_int,"Verify question card","No of Question card is displaying correctly","No of Question content is not displaying correctly");

            actual=performanceSummaryReport.title_performanceSummary.getText().trim();
            expected="Objective Performance Summary";
            CustomAssert.assertEquals(actual,expected,"Verify Objective title","Objective header label is displaying as "+expected+"","Objective header label is not displaying as "+expected+" ");

            actual=performanceSummaryReport.performance_title.getText().trim();
            expected="Define what is business.";
            CustomAssert.assertEquals(actual,expected,"Verify Objective Name","Objective name is displaying as "+expected+"","Objective name is not displaying as \"+expected+\"");

            new WebDriverUtil().waitTillVisibilityOfElement(performanceSummaryReport.performance_title,40);
            performanceSummaryReport.QuestionBar.get(0).click();
            actual_int=preview.questionCard_questionName.size();
            expected_int=2;
            CustomAssert.assertEquals(actual_int,expected_int,"Verify question card","No of Question card is displaying correctly","No of Question content is not displaying correctly");

            actual=preview.diagTest_questionPreview.getText().trim();
            expected=preview.questionCard_questionName.get(0).getText().trim();
            CustomAssert.assertEquals(actual,expected,"Verify question preview","Question content is displaying correctly","Question content is not displaying correctly");


            ReportUtil.log("Productivity report", "Verify productivity report", "info");
            new LoginUsingLTI().ltiLogin("1");//login as student
            ReportUtil.log("Login Via LTI", "Instructor logged successfully", "info");

            new Navigator().NavigateToStudentReport();
            new Navigator().NavigateToAllReport(0);
            Thread.sleep(2000);
            studProdCogProfReport= productivityReport.getStudProfPercentage().getText().trim();
            CustomAssert.assertFalse(studProdCogProfReport.equals("")||studProdCogProfReport==null, "Verify Chapter Proficiency in Chapter section", "Chapter Proficiency is displaying correctly", "Chapter Proficiency is empty");


            //Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(productivityReport.coloredMarker.get(0)); //Mouse hover on chapter dot
            prodColorMarkerProf=productivityReport.ToolTip_proficiencyPercentage.getText().trim();
            prodColorMarkerProfValue= prodColorMarkerProf.substring(0,2);
            System.out.println("prodColorMarkerProfValue:" + prodColorMarkerProfValue);
            CustomAssert.assertFalse(prodColorMarkerProfValue.equals("")||prodColorMarkerProfValue==null, "Verify Chapter Proficiency of tool tip", "Chapter Proficiency is displaying correctly", "Chapter Proficiency is empty in tool tip");

            //Click on the colored marker for the student

            productivityReport.coloredMarker.get(0).click();//click on the chapter dot
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(productivityReport.coloredMarker.get(0));
            prodColorMarkerProfByChap=productivityReport.ToolTip_proficiencyPercentage.getText().trim();
            prodColorMarkerProfByChapValue= prodColorMarkerProfByChap.substring(0,2);
            System.out.println("prodColorMarkerProfByChapValue:" + prodColorMarkerProfByChapValue);
            CustomAssert.assertFalse(prodColorMarkerProfByChapValue.equals("")||prodColorMarkerProfByChapValue==null, "Verify Objective Proficiency of tool tip", "Objective Proficiency is displaying correctly", "Objective Proficiency is empty in tool tip");

            actual=productivityReport.report_title.getText().trim();
            expected="Productivity Report ( Objectives )";
            CustomAssert.assertEquals(actual, expected, "Verify Objective Name", "Objective name is displaying as " + expected + "", "Objective name is not displaying as \"+expected+\"");


            prodColorMarkerProfByObjective=productivityReport.getStudProfPercentage().getText().trim();
            prodColorMarkerProfByObjectiveValue= prodColorMarkerProfByObjective.substring(0,2);
            System.out.println("prodColorMarkerProfByObjectiveValue:"+prodColorMarkerProfByObjectiveValue);
            CustomAssert.assertFalse(prodColorMarkerProfByObjectiveValue.equals("")||prodColorMarkerProfByObjectiveValue==null, "Verify Objective Proficiency of in class section ", "Objective Proficiency is displaying correctly in class section", "Objective Proficiency is empty in class section");



            ReportUtil.log("Metacognitive Report", "Verify Metacognitive Report", "info");
            new LoginUsingLTI().ltiLogin("1");//login as student
            ReportUtil.log("Login Via LTI", "Instructor logged successfully", "info");

            new Navigator().NavigateToStudentReport();
            new Navigator().NavigateToAllReport(1); //Navigate to Metacognitive Report
            Thread.sleep(2000);
            studProdCogProfReport= productivityReport.getStudProfPercentage().getText().trim();
            CustomAssert.assertFalse(studProdCogProfReport.equals("")||studProdCogProfReport==null, "Verify Chapter Proficiency in Chapter section", "Chapter Proficiency is displaying correctly", "Chapter Proficiency is empty");


            //Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(productivityReport.coloredMarker.get(0)); //Mouse hover on chapter dot
            prodColorMarkerProf=metaCognitiveReport.getProficiency().getText().trim();
            prodColorMarkerProfValue= prodColorMarkerProf.substring(12,16);
            System.out.println("prodColorMarkerProfValue:" + prodColorMarkerProfValue);
            CustomAssert.assertFalse(prodColorMarkerProfValue.equals("")||prodColorMarkerProfValue==null, "Verify Chapter Proficiency of tool tip", "Chapter Proficiency is displaying correctly", "Chapter Proficiency is empty in tool tip");

            //Click on the colored marker for the student

            productivityReport.coloredMarker.get(0).click();//click on the chapter dot
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(productivityReport.coloredMarker.get(0));
            prodColorMarkerProfByChap=metaCognitiveReport.getProficiency().getText().trim();
            prodColorMarkerProfByChapValue= prodColorMarkerProfByChap.substring(12,16);
            System.out.println("prodColorMarkerProfByChapValue:" + prodColorMarkerProfByChapValue);
            CustomAssert.assertFalse(prodColorMarkerProfByChapValue.equals("")||prodColorMarkerProfByChapValue==null, "Verify Objective Proficiency of tool tip", "Objective Proficiency is displaying correctly", "Objective Proficiency is empty in tool tip");

            actual=metaCognitiveReport.report_title.getText().trim();
            expected="Metacognitive Report ( Objectives )";
            CustomAssert.assertEquals(actual, expected, "Verify Objective Name", "Objective name is displaying as " + expected + "", "Objective name is not displaying as \"+expected+\"");


            prodColorMarkerProfByObjective=productivityReport.getStudProfPercentage().getText().trim();
            prodColorMarkerProfByObjectiveValue= prodColorMarkerProfByObjective.substring(0,2);
            System.out.println("prodColorMarkerProfByObjectiveValue:"+prodColorMarkerProfByObjectiveValue);
            CustomAssert.assertFalse(prodColorMarkerProfByObjectiveValue.equals("")||prodColorMarkerProfByObjectiveValue==null, "Verify Objective Proficiency of in class section ", "Objective Proficiency is displaying correctly in class section", "Objective Proficiency is empty in class section");

            CustomAssert.assertTrue(metaCognitiveReport.reportContent.isDisplayed(),"Verify below report","Proficiency with respect to Objective displayed correctly.","Proficiency with respect to Objective is not displayed correctly.");
            CustomAssert.assertTrue(metaCognitiveReport.confidence_report.isDisplayed(),"Verify below report","Proficiency with respect to Objective displayed correctly.","Proficiency with respect to Objective is not displayed correctly.");


            ReportUtil.log("Most Challenging Report", "Verify Most Challenging Report", "info");
            new LoginUsingLTI().ltiLogin("1");//login as student
            ReportUtil.log("Login Via LTI", "Instructor logged successfully", "info");

            new Navigator().NavigateToStudentReport();
            new Navigator().NavigateToAllReport(2); //Navigate to Most Challenging Report
            Thread.sleep(2000);

            actual=mostChallengingReport.mostChallengingActivityTitle.getText();
            expected="Most Challenging Activities";
            CustomAssert.assertEquals(actual, expected, "Verify Most Challenging title", "Most Challenging title is displaying as " + expected + "", "Most Challenging title not displaying as "+expected+"");


            actual=mostChallengingReport.viewByObjectives.getText().trim();
            expected="View By Objectives";
            CustomAssert.assertEquals(actual, expected, "Verify View By Objectives label", "View By Objectives label is displaying as " + expected + "", "View By Objectives label not displaying as "+expected+"");

            actual=mostChallengingReport.viewByChapter.getText().trim();
            expected="View By Chapters";
            CustomAssert.assertEquals(actual, expected, "Verify View By Chapters label", "View By Chapters label is displaying as " + expected + "", "View By Chapters label not displaying as "+expected+"");


            List<WebElement> tloProficiency = mostChallengingReport.getStudChapProficiency();
            List<String> list=new ArrayList<>();
            for (WebElement each:tloProficiency ){
                list.add(each.getAttribute("title"));
            }

            CustomAssert.assertFalse(list.isEmpty(),"Verify Learning Objective Proficiency","Learning Objective Proficiency is displaying correctly","Learning Objective Proficiency is not displaying correctly");

            List<WebElement> tloPerformance = mostChallengingReport.studChapPerformance;
            List<String> list1=new ArrayList<>();
            for (WebElement each:tloPerformance ){
                list1.add(each.getAttribute("title"));
            }
            CustomAssert.assertFalse(list1.isEmpty(),"Verify Learning Objective Performance","Learning Objective Performance is displaying correctly","Learning Objective Performance is not displaying correctly");

            //16. Click on 'View By Chapters' tab
            mostChallengingReport.viewByChapter.click();//click on the view Chapter tab
            String mostObjectiveProficiency=mostChallengingReport.getStudChapProficiency().get(1).getText().trim();
            System.out.println("Most Challenging proficiency By Chapter:" + mostObjectiveProficiency);
            CustomAssert.assertFalse(mostObjectiveProficiency.equals("") || mostObjectiveProficiency == null, "Verify Most Challenging proficiency By Chapter", "Most Challenging proficiency By Chapter is displaying correctly", "Most Challenging proficiency By Chapter is empty.");

            //"Learning Objective Performance" should be updated taking the changed grade for that assignment
            String mostObjectivePerformance=mostChallengingReport.studChapPerformance.get(0).getText().trim();
            System.out.println("Most Challenging performance By Chapter:"+mostObjectivePerformance);
            CustomAssert.assertFalse(mostObjectivePerformance.equals("") || mostObjectivePerformance == null, "Verify Most Challenging performance By Objective", "Most Challenging performance By Chapter is displaying correctly", "Most Challenging performance By Chapter is empty.");



        } catch (Exception e) {
            Assert.fail("Exception in TC quitDiagnosticAndVerifyAllReport of class StudentDiagnosticReports",e);
        }

    }

    @Test(priority = 2,enabled = true)
    public void submitDiagnosticAndVerifyPerformanceReport(){
        WebDriver driver=Driver.getWebDriver();
        try {
            Preview preview;
            preview= PageFactory.initElements(driver,Preview.class);
            MetaCognitiveReport metaCognitiveReport;
            metaCognitiveReport= PageFactory.initElements(driver,MetaCognitiveReport.class);
            ProductivityReport productivityReport;
            productivityReport= PageFactory.initElements(driver,ProductivityReport.class);
            MostChallengingActivities mostChallengingReport;
            mostChallengingReport= PageFactory.initElements(driver,MostChallengingActivities.class);
            PerformanceSummaryReport performanceSummaryReport= PageFactory.initElements(driver,PerformanceSummaryReport.class);


            new LoginUsingLTI().ltiLogin("2");//login as student
            ReportUtil.log("Login Via LTI", "Instructor logged successfully", "info");
            new DiagnosticTest().startTest(0,2); //start first chapter diag test
            new DiagnosticTest().attemptAllCorrect(3,false, false);//finish diagnostic test

            new Navigator().NavigateToStudentReport();
            new Navigator().NavigateToAllReport(3);
            Thread.sleep(2000);

            new WebDriverUtil().waitTillVisibilityOfElement(performanceSummaryReport.QuestionBar.get(0), 40);
            actual=performanceSummaryReport.title_performanceSummary.getText().trim();
            expected="Course Performance Summary";
            CustomAssert.assertEquals(actual,expected,"Verify Performance title","Performance header label is displaying \"Course Performance Summary\" correctly","Performance header label is not displaying \"Course Performance Summary\"");
            new WebDriverWait(driver,10).until(ExpectedConditions.invisibilityOfElementLocated(By.id("al-footer")));//close popup
            //new WebDriverUtil().clickOnElementUsingJavascript(performanceSummaryReport.QuestionBar.get(0)); //click on the chapter bar chart
            performanceSummaryReport.QuestionBar.get(0).click();
            new WebDriverUtil().waitTillVisibilityOfElement(performanceSummaryReport.title_performanceSummary,50);
            actual_int=preview.questionCard_questionName.size();
            expected_int=20;
            CustomAssert.assertEquals(actual_int,expected_int,"Verify question card","No of Question card is displaying correctly","No of Question content is not displaying correctly");
            Thread.sleep(5000);
            actual=performanceSummaryReport.title_performanceSummary.getText().trim();
            expected="Chapter Performance Summary";
            CustomAssert.assertEquals(actual,expected,"Verify Performance title","Performance header label is displaying \"Chapter Performance Summary\" correctly","Performance header label is not displaying \"Chapter Performance Summary\"");

            actual=performanceSummaryReport.performance_title.getText().trim();
            expected="Ch 1: The Changing Face of Business";
            CustomAssert.assertEquals(actual,expected,"Verify chapter Name","Chapter name is displaying as "+expected+"","Chapter name is not displaying as \"+expected+\"");


            new WebDriverUtil().waitTillVisibilityOfElement(performanceSummaryReport.performance_title,40);
            performanceSummaryReport.QuestionBar.get(0).click(); //click on the objective bar chart
            CustomAssert.assertEquals(actual_int,expected_int,"Verify question card","No of Question card is displaying correctly","No of Question content is not displaying correctly");

            actual=performanceSummaryReport.title_performanceSummary.getText().trim();
            expected="Objective Performance Summary";
            CustomAssert.assertEquals(actual,expected,"Verify Objective title","Objective header label is displaying as "+expected+"","Objective header label is not displaying as "+expected+" ");

            actual=performanceSummaryReport.performance_title.getText().trim();
            expected="Define what is business.";
            CustomAssert.assertEquals(actual,expected,"Verify Objective Name","Objective name is displaying as "+expected+"","Objective name is not displaying as \"+expected+\"");

            new WebDriverUtil().waitTillVisibilityOfElement(performanceSummaryReport.performance_title,40);
            performanceSummaryReport.QuestionBar.get(0).click();
            actual_int=preview.questionCard_questionName.size();
            expected_int=2;
            CustomAssert.assertEquals(actual_int,expected_int,"Verify question card","No of Question card is displaying correctly","No of Question content is not displaying correctly");

            actual=preview.diagTest_questionPreview.getText().trim();
            expected=preview.questionCard_questionName.get(0).getText().trim();
            CustomAssert.assertEquals(actual,expected,"Verify question preview","Question content is displaying correctly","Question content is not displaying correctly");



            ReportUtil.log("Productivity report", "Verify productivity report", "info");
            new LoginUsingLTI().ltiLogin("2");//login as student
            ReportUtil.log("Login Via LTI", "Instructor logged successfully", "info");

            new Navigator().NavigateToStudentReport();
            new Navigator().NavigateToAllReport(0); //Navigate to productivity report
            Thread.sleep(2000);
            studProdCogProfReport= productivityReport.getStudProfPercentage().getText().trim();
            CustomAssert.assertFalse(studProdCogProfReport.equals("")||studProdCogProfReport==null, "Verify Chapter Proficiency in Chapter section", "Chapter Proficiency is displaying correctly", "Chapter Proficiency is empty");


            //Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(productivityReport.coloredMarker.get(0)); //Mouse hover on chapter dot
            prodColorMarkerProf=productivityReport.ToolTip_proficiencyPercentage.getText().trim();
            prodColorMarkerProfValue= prodColorMarkerProf.substring(0,2);
            System.out.println("prodColorMarkerProfValue:" + prodColorMarkerProfValue);
            CustomAssert.assertFalse(prodColorMarkerProfValue.equals("")||prodColorMarkerProfValue==null, "Verify Chapter Proficiency of tool tip", "Chapter Proficiency is displaying correctly", "Chapter Proficiency is empty in tool tip");

            //Click on the colored marker for the student

            productivityReport.coloredMarker.get(0).click();//click on the chapter dot
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(productivityReport.coloredMarker.get(0));
            prodColorMarkerProfByChap=productivityReport.ToolTip_proficiencyPercentage.getText().trim();
            prodColorMarkerProfByChapValue= prodColorMarkerProfByChap.substring(0,2);
            System.out.println("prodColorMarkerProfByChapValue:" + prodColorMarkerProfByChapValue);
            CustomAssert.assertFalse(prodColorMarkerProfByChapValue.equals("")||prodColorMarkerProfByChapValue==null, "Verify Objective Proficiency of tool tip", "Objective Proficiency is displaying correctly", "Objective Proficiency is empty in tool tip");

            actual=productivityReport.report_title.getText().trim();
            expected="Productivity Report ( Objectives )";
            CustomAssert.assertEquals(actual, expected, "Verify Objective Name", "Objective name is displaying as " + expected + "", "Objective name is not displaying as \"+expected+\"");


            String prodColorMarkerProfByObjective=productivityReport.getStudProfPercentage().getText().trim();
            String prodColorMarkerProfByObjectiveValue= prodColorMarkerProfByObjective.substring(0,2);
            System.out.println("prodColorMarkerProfByObjectiveValue:"+prodColorMarkerProfByObjectiveValue);
            CustomAssert.assertFalse(prodColorMarkerProfByObjectiveValue.equals("")||prodColorMarkerProfByObjectiveValue==null, "Verify Objective Proficiency of in class section ", "Objective Proficiency is displaying correctly in class section", "Objective Proficiency is empty in class section");


            ReportUtil.log("Metacognitive Report", "Verify Metacognitive Report", "info");
            new LoginUsingLTI().ltiLogin("2");//login as student
            ReportUtil.log("Login Via LTI", "Instructor logged successfully", "info");

            new Navigator().NavigateToStudentReport();
            new Navigator().NavigateToAllReport(1); //Navigate to Metacognitive Report
            Thread.sleep(2000);
            studProdCogProfReport= productivityReport.getStudProfPercentage().getText().trim();
            CustomAssert.assertFalse(studProdCogProfReport.equals("")||studProdCogProfReport==null, "Verify Chapter Proficiency in Chapter section", "Chapter Proficiency is displaying correctly", "Chapter Proficiency is empty");


            //Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(productivityReport.coloredMarker.get(0)); //Mouse hover on chapter dot
            prodColorMarkerProf=metaCognitiveReport.getProficiency().getText().trim();
            prodColorMarkerProfValue= prodColorMarkerProf.substring(12,16);
            System.out.println("prodColorMarkerProfValue:" + prodColorMarkerProfValue);
            CustomAssert.assertFalse(prodColorMarkerProfValue.equals("")||prodColorMarkerProfValue==null, "Verify Chapter Proficiency of tool tip", "Chapter Proficiency is displaying correctly", "Chapter Proficiency is empty in tool tip");

            //Click on the colored marker for the student

            productivityReport.coloredMarker.get(0).click();//click on the chapter dot
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(productivityReport.coloredMarker.get(0));
            prodColorMarkerProfByChap=metaCognitiveReport.getProficiency().getText().trim();
            prodColorMarkerProfByChapValue= prodColorMarkerProfByChap.substring(12,16);
            System.out.println("prodColorMarkerProfByChapValue:" + prodColorMarkerProfByChapValue);
            CustomAssert.assertFalse(prodColorMarkerProfByChapValue.equals("")||prodColorMarkerProfByChapValue==null, "Verify Objective Proficiency of tool tip", "Objective Proficiency is displaying correctly", "Objective Proficiency is empty in tool tip");

            actual=metaCognitiveReport.report_title.getText().trim();
            expected="Metacognitive Report ( Objectives )";
            CustomAssert.assertEquals(actual, expected, "Verify Objective Name", "Objective name is displaying as " + expected + "", "Objective name is not displaying as \"+expected+\"");


            prodColorMarkerProfByObjective=productivityReport.getStudProfPercentage().getText().trim();
            prodColorMarkerProfByObjectiveValue= prodColorMarkerProfByObjective.substring(0,2);
            System.out.println("prodColorMarkerProfByObjectiveValue:"+prodColorMarkerProfByObjectiveValue);
            CustomAssert.assertFalse(prodColorMarkerProfByObjectiveValue.equals("")||prodColorMarkerProfByObjectiveValue==null, "Verify Objective Proficiency of in class section ", "Objective Proficiency is displaying correctly in class section", "Objective Proficiency is empty in class section");

            CustomAssert.assertTrue(metaCognitiveReport.reportContent.isDisplayed(),"Verify below report","Proficiency with respect to Objective displayed correctly.","Proficiency with respect to Objective is not displayed correctly.");
            CustomAssert.assertTrue(metaCognitiveReport.confidence_report.isDisplayed(),"Verify below report","Proficiency with respect to Objective displayed correctly.","Proficiency with respect to Objective is not displayed correctly.");


            ReportUtil.log("Most Challenging Report", "Verify Most Challenging Report", "info");
            new LoginUsingLTI().ltiLogin("2");//login as student
            ReportUtil.log("Login Via LTI", "Instructor logged successfully", "info");

            new Navigator().NavigateToStudentReport();
            new Navigator().NavigateToAllReport(2); //Navigate to Most Challenging Report
            Thread.sleep(2000);

            actual=mostChallengingReport.mostChallengingActivityTitle.getText();
            expected="Most Challenging Activities";
            CustomAssert.assertEquals(actual, expected, "Verify Most Challenging title", "Most Challenging title is displaying as " + expected + "", "Most Challenging title not displaying as "+expected+"");


            actual=mostChallengingReport.viewByObjectives.getText().trim();
            expected="View By Objectives";
            CustomAssert.assertEquals(actual, expected, "Verify View By Objectives label", "View By Objectives label is displaying as " + expected + "", "View By Objectives label not displaying as "+expected+"");

            actual=mostChallengingReport.viewByChapter.getText().trim();
            expected="View By Chapters";
            CustomAssert.assertEquals(actual, expected, "Verify View By Chapters label", "View By Chapters label is displaying as " + expected + "", "View By Chapters label not displaying as "+expected+"");


            List<WebElement> tloProficiency = mostChallengingReport.getStudChapProficiency();
            List<String> list=new ArrayList<>();
            for (WebElement each:tloProficiency ){
                list.add(each.getAttribute("title"));
            }

            CustomAssert.assertFalse(list.isEmpty(),"Verify Learning Objective Proficiency","Learning Objective Proficiency is displaying correctly","Learning Objective Proficiency is not displaying correctly");

            List<WebElement> tloPerformance = mostChallengingReport.studChapPerformance;
            List<String> list1=new ArrayList<>();
            for (WebElement each:tloPerformance ){
                list1.add(each.getAttribute("title"));
            }
            CustomAssert.assertFalse(list1.isEmpty(),"Verify Learning Objective Performance","Learning Objective Performance is displaying correctly","Learning Objective Performance is not displaying correctly");

            //16. Click on 'View By Chapters' tab
            mostChallengingReport.viewByChapter.click();//click on the view Chapter tab
            String mostObjectiveProficiency=mostChallengingReport.getStudChapProficiency().get(1).getText().trim();
            System.out.println("Most Challenging proficiency By Chapter:" + mostObjectiveProficiency);
            CustomAssert.assertFalse(mostObjectiveProficiency.equals("") || mostObjectiveProficiency == null, "Verify Most Challenging proficiency By Chapter", "Most Challenging proficiency By Chapter is displaying correctly", "Most Challenging proficiency By Chapter is empty.");

            //"Learning Objective Performance" should be updated taking the changed grade for that assignment
            String mostObjectivePerformance=mostChallengingReport.studChapPerformance.get(0).getText().trim();
            System.out.println("Most Challenging performance By Chapter:"+mostObjectivePerformance);
            CustomAssert.assertFalse(mostObjectivePerformance.equals("") || mostObjectivePerformance == null, "Verify Most Challenging performance By Objective", "Most Challenging performance By Chapter is displaying correctly", "Most Challenging performance By Chapter is empty.");




            ReportUtil.log("Productivity Report", "Verify Practice link of Productivity Report", "info");
            String questionNo="";
            new LoginUsingLTI().ltiLogin("2");//login as student
            ReportUtil.log("Login Via LTI", "Instructor logged successfully", "info");

            new Navigator().NavigateToStudentReport();
            new Navigator().NavigateToAllReport(0); //Navigate to productivity report
            Thread.sleep(2000);

            //Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(productivityReport.coloredMarker.get(0)); //Mouse hover on chapter dot
            new Navigator().clickOnPracticeLink();

            questionNo=preview.question_No.getText().trim();
            String expected="Q 1.21:";
            CustomAssert.assertEquals(questionNo, expected, "Verify Question Number", "Question Number is displaying as " + expected + "", "Question Number not displaying as "+expected+"");

            actual=preview.diagTest_questionPreview.getText().trim();
            CustomAssert.assertFalse(actual.equals("")||actual==null, "Verify Question Name", "Question content displaying correctly", "Question Name is empty.");


            //Click on the colored marker for Objective the student
            new Navigator().NavigateToStudentReport();
            new Navigator().NavigateToAllReport(0); //Navigate to productivity report
            productivityReport.coloredMarker.get(0).click();//click on the chapter dot
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(productivityReport.coloredMarker.get(0));
            new Navigator().clickOnPracticeLink();
            String questionNo1=preview.question_No.getText().trim();
            String expected1="Q 1.21:";
            CustomAssert.assertEquals(questionNo1, expected1, "Verify Question Number", "Question Number is displaying as " + expected + "", "Question Number not displaying as "+expected+"");
            actual=preview.diagTest_questionPreview.getText().trim();
            CustomAssert.assertFalse(actual.equals("") || actual == null, "Verify Question Name", "Question content displaying correctly", "Question Name is empty.");


            ReportUtil.log("Practice Link","Practice Link in chapter section","info");
            new Navigator().NavigateToStudentReport();
            new Navigator().NavigateToAllReport(0); //Navigate to productivity report
            new MouseHover().mouserhoverbywebelement(preview.contentReport);
            new Navigator().clickOnPracticeLink();

            questionNo=preview.question_No.getText().trim();
            expected="Q 1.21:";
            CustomAssert.assertEquals(questionNo, expected, "Verify Question Number", "Question Number is displaying as " + expected + "", "Question Number not displaying as "+expected+"");
            actual=preview.diagTest_questionPreview.getText().trim();
            CustomAssert.assertFalse(actual.equals("") || actual == null, "Verify Question Name", "Question content displaying correctly", "Question Name is empty.");

            ReportUtil.log("Practice Link","Practice Link in Objective section","info");

            new Navigator().NavigateToStudentReport();
            new Navigator().NavigateToAllReport(0); //Navigate to productivity report
            productivityReport.coloredMarker.get(0).click();//click on the chapter dot
            Thread.sleep(4000);
            new MouseHover().mouserhoverbywebelement(preview.contentReport);
            new Navigator().clickOnPracticeLink();

            questionNo=preview.question_No.getText().trim();
            expected="Q 1.21:";
            CustomAssert.assertEquals(questionNo, expected, "Verify Question Number", "Question Number is displaying as " + expected + "", "Question Number not displaying as "+expected+"");
            actual=preview.diagTest_questionPreview.getText().trim();
            CustomAssert.assertFalse(actual.equals("") || actual == null, "Verify Question Name", "Question content displaying correctly", "Question Name is empty.");


            new LoginUsingLTI().ltiLogin("2");//login as student
            ReportUtil.log("Login Via LTI", "Instructor logged successfully", "info");

            new Navigator().NavigateToStudentReport();
            new Navigator().NavigateToAllReport(1); //Navigate to productivity report
            Thread.sleep(2000);

            //Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(productivityReport.coloredMarker.get(0)); //Mouse hover on chapter dot
            new Navigator().clickOnPracticeLink();

            questionNo=preview.question_No.getText().trim();
            expected="Q 1.21:";
            CustomAssert.assertEquals(questionNo, expected, "Verify Question Number", "Question Number is displaying as " + expected + "", "Question Number not displaying as "+expected+"");

            actual=preview.diagTest_questionPreview.getText().trim();
            CustomAssert.assertFalse(actual.equals("")||actual==null, "Verify Question Name", "Question content displaying correctly", "Question Name is empty.");

            ReportUtil.log("Metacognitive report", "Verify Practice link of Metacognitive Report", "info");
            //Click on the colored marker for Objective the student
            new Navigator().NavigateToStudentReport();
            new Navigator().NavigateToAllReport(1); //Navigate to metacognitive report
            productivityReport.coloredMarker.get(0).click();//click on the chapter dot
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(productivityReport.coloredMarker.get(0));
            new Navigator().clickOnPracticeLink();
            questionNo=preview.question_No.getText().trim();
            expected="Q 1.21:";
            CustomAssert.assertEquals(questionNo, expected, "Verify Question Number", "Question Number is displaying as " + expected + "", "Question Number not displaying as "+expected+"");
            actual=preview.diagTest_questionPreview.getText().trim();
            CustomAssert.assertFalse(actual.equals("") || actual == null, "Verify Question Name", "Question content displaying correctly", "Question Name is empty.");


            ReportUtil.log("Practice Link","Practice Link in chapter section","info");
            new Navigator().NavigateToStudentReport();
            new Navigator().NavigateToAllReport(1); //Navigate to metacognitive report
            new MouseHover().mouserhoverbywebelement(preview.contentReport);
            new Navigator().clickOnPracticeLink();

            questionNo=preview.question_No.getText().trim();
            expected="Q 1.21:";
            CustomAssert.assertEquals(questionNo, expected, "Verify Question Number", "Question Number is displaying as " + expected + "", "Question Number not displaying as "+expected+"");
            actual=preview.diagTest_questionPreview.getText().trim();
            CustomAssert.assertFalse(actual.equals("") || actual == null, "Verify Question Name", "Question content displaying correctly", "Question Name is empty.");

            ReportUtil.log("Practice Link","Practice Link in Objective section","info");

            new Navigator().NavigateToStudentReport();
            new Navigator().NavigateToAllReport(1); //Navigate to metacognitive report
            productivityReport.coloredMarker.get(0).click();//click on the chapter dot
            Thread.sleep(4000);
            new MouseHover().mouserhoverbywebelement(preview.contentReport);
            new Navigator().clickOnPracticeLink();

            questionNo=preview.question_No.getText().trim();
            expected="Q 1.21:";
            CustomAssert.assertEquals(questionNo, expected, "Verify Question Number", "Question Number is displaying as " + expected + "", "Question Number not displaying as "+expected+"");
            actual=preview.diagTest_questionPreview.getText().trim();
            CustomAssert.assertFalse(actual.equals("") || actual == null, "Verify Question Name", "Question content displaying correctly", "Question Name is empty.");


            ReportUtil.log("Most Challenging Report", "Verify Practice link of Most Challenging Report", "info");
            MostChallengingActivities mostChallengingActivities=PageFactory.initElements(driver,MostChallengingActivities.class);
            new LoginUsingLTI().ltiLogin("2");//login as student
            ReportUtil.log("Login Via LTI", "Student logged successfully", "info");

            new Navigator().NavigateToStudentReport();
            new Navigator().NavigateToAllReport(2); //Navigate to productivity report
            Thread.sleep(2000);

            ReportUtil.log("Practice Link","Practice Link in view by Chapter","info");
            mostChallengingActivities.viewByChapter.click();

            new MouseHover().mouserhoverbywebelement(mostChallengingActivities.chapterName_viewByChapter); //Mouse hover on chapter name
            new Navigator().clickOnPracticeLink();
            questionNo=preview.question_No.getText().trim();
            expected="Q 1.21:";
            CustomAssert.assertEquals(questionNo, expected, "Verify Question Number", "Question Number is displaying as " + expected + "", "Question Number not displaying as "+expected+"");
            actual=preview.diagTest_questionPreview.getText().trim();
            CustomAssert.assertFalse(actual.equals("")||actual==null, "Verify Question Name", "Question content displaying correctly", "Question Name is empty.");


            ReportUtil.log("Practice Link","Practice Link in view by objective","info");
            new Navigator().NavigateToStudentReport();
            new Navigator().NavigateToAllReport(2); //Navigate to metacognitive report

            new MouseHover().mouserhoverbywebelement(mostChallengingActivities.objectiveName_viewByObjective); //Mouse hover on objective name
            new Navigator().clickOnPracticeLink();
            questionNo1=preview.question_No.getText().trim();
            expected1="Q 1.21:";
            CustomAssert.assertEquals(questionNo1, expected1, "Verify Question Number", "Question Number is displaying as " + expected + "", "Question Number not displaying as "+expected+"");
            actual=preview.diagTest_questionPreview.getText().trim();
            CustomAssert.assertFalse(actual.equals("") || actual == null, "Verify Question Name", "Question content displaying correctly", "Question Name is empty.");



        } catch (Exception e) {
            Assert.fail("Exception in TC submitDiagnosticAndVerifyMostChallengingReport of class StudentDiagnosticReports",e);
        }
    }


    @Test(priority = 3,enabled = true)
    public void continueDiagnosticAndVerifyPerformanceReport(){
        WebDriver driver=Driver.getWebDriver();
        try {
            Preview preview;
            preview= PageFactory.initElements(driver,Preview.class);
            MetaCognitiveReport metaCognitiveReport;
            metaCognitiveReport= PageFactory.initElements(driver,MetaCognitiveReport.class);
            ProductivityReport productivityReport;
            productivityReport= PageFactory.initElements(driver,ProductivityReport.class);
            MostChallengingActivities mostChallengingReport;
            mostChallengingReport= PageFactory.initElements(driver,MostChallengingActivities.class);
            PerformanceSummaryReport performanceSummaryReport= PageFactory.initElements(driver,PerformanceSummaryReport.class);


            new LoginUsingLTI().ltiLogin("3");//login as student
            ReportUtil.log("Login Via LTI", "Instructor logged successfully", "info");
            new DiagnosticTest().startTest(0,2); //start first chapter diag test
            new DiagnosticTest().DiagonesticTestQuitBetween(3, 3, "correct", false, false, false);//attempt diagnostic test and click on continue later

            new DiagnosticTest().continueDiagnosticTest(0,2); //continue test
            new DiagnosticTest().attemptAllCorrect(3, false, false);//finish diagnostic test

            new Navigator().NavigateToStudentReport();
            new Navigator().NavigateToAllReport(3);
            Thread.sleep(2000);
            new WebDriverUtil().waitTillVisibilityOfElement(performanceSummaryReport.QuestionBar.get(0), 40);
            actual=performanceSummaryReport.title_performanceSummary.getText().trim();
            expected="Course Performance Summary";
            CustomAssert.assertEquals(actual,expected,"Verify Performance title","Performance header label is displaying \"Course Performance Summary\" correctly","Performance header label is not displaying \"Course Performance Summary\"");

            performanceSummaryReport.QuestionBar.get(0).click(); //click on the chapter bar chart
            actual_int=preview.questionCard_questionName.size();
            expected_int=20;
            CustomAssert.assertEquals(actual_int,expected_int,"Verify question card","No of Question card is displaying correctly","No of Question content is not displaying correctly");

            actual=performanceSummaryReport.title_performanceSummary.getText().trim();
            expected="Chapter Performance Summary";
            CustomAssert.assertEquals(actual,expected,"Verify Performance title","Performance header label is displaying \"Chapter Performance Summary\" correctly","Performance header label is not displaying \"Chapter Performance Summary\"");

            actual=performanceSummaryReport.performance_title.getText().trim();
            expected="Ch 1: The Changing Face of Business";
            CustomAssert.assertEquals(actual,expected,"Verify chapter Name","Chapter name is displaying as "+expected+"","Chapter name is not displaying as \"+expected+\"");


            new WebDriverUtil().waitTillVisibilityOfElement(performanceSummaryReport.performance_title,40);
            performanceSummaryReport.QuestionBar.get(0).click(); //click on the objective bar chart
            CustomAssert.assertEquals(actual_int,expected_int,"Verify question card","No of Question card is displaying correctly","No of Question content is not displaying correctly");

            actual=performanceSummaryReport.title_performanceSummary.getText().trim();
            expected="Objective Performance Summary";
            CustomAssert.assertEquals(actual,expected,"Verify Objective title","Objective header label is displaying as "+expected+"","Objective header label is not displaying as "+expected+" ");

            actual=performanceSummaryReport.performance_title.getText().trim();
            expected="Define what is business.";
            CustomAssert.assertEquals(actual,expected,"Verify Objective Name","Objective name is displaying as "+expected+"","Objective name is not displaying as \"+expected+\"");

            new WebDriverUtil().waitTillVisibilityOfElement(performanceSummaryReport.performance_title,40);
            performanceSummaryReport.QuestionBar.get(0).click();
            actual_int=preview.questionCard_questionName.size();
            expected_int=2;
            CustomAssert.assertEquals(actual_int,expected_int,"Verify question card","No of Question card is displaying correctly","No of Question content is not displaying correctly");

            actual=preview.diagTest_questionPreview.getText().trim();
            expected=preview.questionCard_questionName.get(0).getText().trim();
            CustomAssert.assertEquals(actual,expected,"Verify question preview","Question content is displaying correctly","Question content is not displaying correctly");


            ReportUtil.log("Productivity report", "Verify productivity report", "info");
            new LoginUsingLTI().ltiLogin("3");//login as student
            ReportUtil.log("Login Via LTI", "Instructor logged successfully", "info");

            new Navigator().NavigateToStudentReport();
            new Navigator().NavigateToAllReport(0); //Navigate to productivity report
            Thread.sleep(2000);
            studProdCogProfReport= productivityReport.getStudProfPercentage().getText().trim();
            CustomAssert.assertFalse(studProdCogProfReport.equals("")||studProdCogProfReport==null, "Verify Chapter Proficiency in Chapter section", "Chapter Proficiency is displaying correctly", "Chapter Proficiency is empty");


            //Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(productivityReport.coloredMarker.get(0)); //Mouse hover on chapter dot
            prodColorMarkerProf=productivityReport.ToolTip_proficiencyPercentage.getText().trim();
            prodColorMarkerProfValue= prodColorMarkerProf.substring(0,2);
            System.out.println("prodColorMarkerProfValue:" + prodColorMarkerProfValue);
            CustomAssert.assertFalse(prodColorMarkerProfValue.equals("")||prodColorMarkerProfValue==null, "Verify Chapter Proficiency of tool tip", "Chapter Proficiency is displaying correctly", "Chapter Proficiency is empty in tool tip");

            //Click on the colored marker for the student

            productivityReport.coloredMarker.get(0).click();//click on the chapter dot
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(productivityReport.coloredMarker.get(0));
            prodColorMarkerProfByChap=productivityReport.ToolTip_proficiencyPercentage.getText().trim();
            prodColorMarkerProfByChapValue= prodColorMarkerProfByChap.substring(0,2);
            System.out.println("prodColorMarkerProfByChapValue:" + prodColorMarkerProfByChapValue);
            CustomAssert.assertFalse(prodColorMarkerProfByChapValue.equals("")||prodColorMarkerProfByChapValue==null, "Verify Objective Proficiency of tool tip", "Objective Proficiency is displaying correctly", "Objective Proficiency is empty in tool tip");

            actual=productivityReport.report_title.getText().trim();
            expected="Productivity Report ( Objectives )";
            CustomAssert.assertEquals(actual, expected, "Verify Objective Name", "Objective name is displaying as " + expected + "", "Objective name is not displaying as \"+expected+\"");


            prodColorMarkerProfByObjective=productivityReport.getStudProfPercentage().getText().trim();
            prodColorMarkerProfByObjectiveValue= prodColorMarkerProfByObjective.substring(0,2);
            System.out.println("prodColorMarkerProfByObjectiveValue:"+prodColorMarkerProfByObjectiveValue);
            CustomAssert.assertFalse(prodColorMarkerProfByObjectiveValue.equals("")||prodColorMarkerProfByObjectiveValue==null, "Verify Objective Proficiency of in class section ", "Objective Proficiency is displaying correctly in class section", "Objective Proficiency is empty in class section");


            ReportUtil.log("Metacognitive Report", "Verify Metacognitive Report", "info");
            new LoginUsingLTI().ltiLogin("3");//login as student
            ReportUtil.log("Login Via LTI", "Instructor logged successfully", "info");

            new Navigator().NavigateToStudentReport();
            new Navigator().NavigateToAllReport(1); //Navigate to Metacognitive Report
            Thread.sleep(2000);
            studProdCogProfReport= productivityReport.getStudProfPercentage().getText().trim();
            CustomAssert.assertFalse(studProdCogProfReport.equals("")||studProdCogProfReport==null, "Verify Chapter Proficiency in Chapter section", "Chapter Proficiency is displaying correctly", "Chapter Proficiency is empty");


            //Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(productivityReport.coloredMarker.get(0)); //Mouse hover on chapter dot
            prodColorMarkerProf=metaCognitiveReport.getProficiency().getText().trim();
            prodColorMarkerProfValue= prodColorMarkerProf.substring(12,16);
            System.out.println("prodColorMarkerProfValue:" + prodColorMarkerProfValue);
            CustomAssert.assertFalse(prodColorMarkerProfValue.equals("")||prodColorMarkerProfValue==null, "Verify Chapter Proficiency of tool tip", "Chapter Proficiency is displaying correctly", "Chapter Proficiency is empty in tool tip");

            //Click on the colored marker for the student

            productivityReport.coloredMarker.get(0).click();//click on the chapter dot
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(productivityReport.coloredMarker.get(0));
            prodColorMarkerProfByChap=metaCognitiveReport.getProficiency().getText().trim();
            prodColorMarkerProfByChapValue= prodColorMarkerProfByChap.substring(12,16);
            System.out.println("prodColorMarkerProfByChapValue:" + prodColorMarkerProfByChapValue);
            CustomAssert.assertFalse(prodColorMarkerProfByChapValue.equals("")||prodColorMarkerProfByChapValue==null, "Verify Objective Proficiency of tool tip", "Objective Proficiency is displaying correctly", "Objective Proficiency is empty in tool tip");

            actual=metaCognitiveReport.report_title.getText().trim();
            expected="Metacognitive Report ( Objectives )";
            CustomAssert.assertEquals(actual, expected, "Verify Objective Name", "Objective name is displaying as " + expected + "", "Objective name is not displaying as \"+expected+\"");


            prodColorMarkerProfByObjective=productivityReport.getStudProfPercentage().getText().trim();
            prodColorMarkerProfByObjectiveValue= prodColorMarkerProfByObjective.substring(0,2);
            System.out.println("prodColorMarkerProfByObjectiveValue:"+prodColorMarkerProfByObjectiveValue);
            CustomAssert.assertFalse(prodColorMarkerProfByObjectiveValue.equals("")||prodColorMarkerProfByObjectiveValue==null, "Verify Objective Proficiency of in class section ", "Objective Proficiency is displaying correctly in class section", "Objective Proficiency is empty in class section");

            CustomAssert.assertTrue(metaCognitiveReport.reportContent.isDisplayed(),"Verify below report","Proficiency with respect to Objective displayed correctly.","Proficiency with respect to Objective is not displayed correctly.");
            CustomAssert.assertTrue(metaCognitiveReport.confidence_report.isDisplayed(),"Verify below report","Proficiency with respect to Objective displayed correctly.","Proficiency with respect to Objective is not displayed correctly.");


            ReportUtil.log("Most Challenging Report", "Verify Most Challenging Report", "info");

            new LoginUsingLTI().ltiLogin("3");//login as student
            ReportUtil.log("Login Via LTI", "Instructor logged successfully", "info");

            new Navigator().NavigateToStudentReport();
            new Navigator().NavigateToAllReport(2); //Navigate to Most Challenging Report
            Thread.sleep(2000);

            actual=mostChallengingReport.mostChallengingActivityTitle.getText();
            expected="Most Challenging Activities";
            CustomAssert.assertEquals(actual, expected, "Verify Most Challenging title", "Most Challenging title is displaying as " + expected + "", "Most Challenging title not displaying as "+expected+"");


            actual=mostChallengingReport.viewByObjectives.getText().trim();
            expected="View By Objectives";
            CustomAssert.assertEquals(actual, expected, "Verify View By Objectives label", "View By Objectives label is displaying as " + expected + "", "View By Objectives label not displaying as "+expected+"");

            actual=mostChallengingReport.viewByChapter.getText().trim();
            expected="View By Chapters";
            CustomAssert.assertEquals(actual, expected, "Verify View By Chapters label", "View By Chapters label is displaying as " + expected + "", "View By Chapters label not displaying as "+expected+"");


            List<WebElement> tloProficiency = mostChallengingReport.getStudChapProficiency();
            List<String> list=new ArrayList<>();
            for (WebElement each:tloProficiency ){
                list.add(each.getAttribute("title"));

            }

            CustomAssert.assertFalse(list.isEmpty(),"Verify Learning Objective Proficiency","Learning Objective Proficiency is displaying correctly","Learning Objective Proficiency is not displaying correctly");

            List<WebElement> tloPerformance = mostChallengingReport.studChapPerformance;
            List<String> list1=new ArrayList<>();
            for (WebElement each:tloPerformance ){
                list1.add(each.getAttribute("title"));
            }
            CustomAssert.assertFalse(list1.isEmpty(),"Verify Learning Objective Performance","Learning Objective Performance is displaying correctly","Learning Objective Performance is not displaying correctly");

            //16. Click on 'View By Chapters' tab
            mostChallengingReport.viewByChapter.click();//click on the view Chapter tab
            String mostObjectiveProficiency=mostChallengingReport.getStudChapProficiency().get(1).getText().trim();
            System.out.println("Most Challenging proficiency By Chapter:" + mostObjectiveProficiency);
            CustomAssert.assertFalse(mostObjectiveProficiency.equals("") || mostObjectiveProficiency == null, "Verify Most Challenging proficiency By Chapter", "Most Challenging proficiency By Chapter is displaying correctly", "Most Challenging proficiency By Chapter is empty.");

            //"Learning Objective Performance" should be updated taking the changed grade for that assignment
            String mostObjectivePerformance=mostChallengingReport.studChapPerformance.get(0).getText().trim();
            System.out.println("Most Challenging performance By Chapter:"+mostObjectivePerformance);
            CustomAssert.assertFalse(mostObjectivePerformance.equals("") || mostObjectivePerformance == null, "Verify Most Challenging performance By Objective", "Most Challenging performance By Chapter is displaying correctly", "Most Challenging performance By Chapter is empty.");

        } catch (Exception e) {
            Assert.fail("Exception in TC continueDiagnosticAndVerifyMostChallengingReport of class StudentDiagnosticReports",e);
        }
    }




    @Test(priority = 4,enabled = true)
    public void verifyPracticeTestPerformanceReportOfInstructor(){
        WebDriver driver=Driver.getWebDriver();
        try {
            PerformanceSummaryReport performanceSummaryReport;
            performanceSummaryReport= PageFactory.initElements(driver,PerformanceSummaryReport.class);
            Preview preview;
            preview= PageFactory.initElements(driver,Preview.class);

            new LoginUsingLTI().ltiLogin("5");//login as student
            ReportUtil.log("Login Via LTI", "Student logged successfully", "info");
            new DiagnosticTest().startTest(0,2); //start first chapter diag test
            new DiagnosticTest().attemptAllCorrect(3,false, false);//finish diagnostic test


            new LoginUsingLTI().ltiLogin("5");//login as student
            new PracticeTest().startTest();
            for(int i=0;i<5;i++) {
                new PracticeTest().attemptQuestion("correct", 2, false, false);//click on chapter level practice button
            }
            new PracticeTest().quitTestAndGoToReport();
            new RunScheduledJobs().runScheduledJob("ClassSectionActivityJob");

            preview= PageFactory.initElements(driver,Preview.class);
            performanceSummaryReport= PageFactory.initElements(driver,PerformanceSummaryReport.class);

            new LoginUsingLTI().ltiLogin("4");//login as instructor
            new Navigator().NavigateToInstructorReport();
            new Navigator().NavigateToAllReport(3); //Navigate to Performance report
            Thread.sleep(2000);

            actual=performanceSummaryReport.classPerformanceByChapter_label.getText().trim();
            expected="Class Performance by Chapters";
            CustomAssert.assertEquals(actual, expected, "Verify Class Performance by Chapters Label", "Class Performance by Chapters Label is displaying as " + expected + "", "Class Performance by Chapters Label as "+expected+"");


            actual=performanceSummaryReport.classPerformanceByStudent_label.getText().trim();
            expected="Class Performance By Students";
            CustomAssert.assertEquals(actual, expected, "Verify Class Performance by Student Label", "Class Performance by Student Label is displaying as " + expected + "", "Class Performance by Chapters Student as "+expected+"");

            String classPerformanceByStudentProficiency = performanceSummaryReport.proficiency_percentage.get(0).getText().trim();
            CustomAssert.assertFalse(classPerformanceByStudentProficiency.equals("") && classPerformanceByStudentProficiency == null, "Verify Class Performance By Students Proficiency", "Class Performance By Students Proficiency is displaying correctly", "Class Performance By Students Proficiency is empty.");

            String classPerformanceByStudentPerformance = performanceSummaryReport.performanceEntry_classPerformance.get(0).getText().trim();
            CustomAssert.assertFalse(classPerformanceByStudentPerformance.equals("") && classPerformanceByStudentPerformance == null, "Verify Class Performance By Students Performance", "Class Performance By Students Performance is displaying correctly", "Class Performance By Students Performance is empty.");

            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", performanceSummaryReport.chapNumber); //click on the chapter number
            Thread.sleep(3000);
            String chapterPerformanceByStudentProficiency = performanceSummaryReport.proficiency_percentage.get(0).getText().trim();
            CustomAssert.assertFalse(chapterPerformanceByStudentProficiency.equals("") && chapterPerformanceByStudentProficiency == null, "Verify Chapter Performance By Students Proficiency", "Chapter Performance By Students Proficiency is displaying correctly", "Chapter Performance By Students Proficiency is empty.");

            String chapterPerformanceByStudentPerformance = performanceSummaryReport.performanceEntry_classPerformance.get(0).getText().trim();
            CustomAssert.assertFalse(chapterPerformanceByStudentPerformance.equals("") && chapterPerformanceByStudentPerformance == null, "Verify Chapter Performance By Students Performance", "Chapter Performance By Students Performance is displaying correctly", "Chapter Performance By Students Performance is empty.");


            actual=performanceSummaryReport.classPerformanceByChapter_label.getText().trim();
            expected="Class Performance by Objectives";
            CustomAssert.assertEquals(actual, expected, "Verify Class Performance by Objectives Label", "Class Performance by Objectives Label is displaying as " + expected + "", "Class Performance by Objectives Label as "+expected+"");


            actual=performanceSummaryReport.classPerformanceByStudent_label.getText().trim();
            expected="Chapter Performance By Students";
            CustomAssert.assertEquals(actual, expected, "Verify Chapter Performance by Student Label", "Chapter Performance by Student Label is displaying as " + expected + "", "Chapter Performance by Chapters Student as "+expected+"");


            actual=performanceSummaryReport.classPerformance_subHeading.getText().trim();
            expected="(Ch 1: The Changing Face of Business)";
            CustomAssert.assertEquals(actual, expected, "Verify Chapter Performance sub heading", "Chapter Performance sub heading is displaying as " + expected + "", "Chapter Performance sub heading Student as "+expected+"");


            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", performanceSummaryReport.tloNumber); //click on the tlo number

            Thread.sleep(3000);
            String objectivePerformanceByStudentProficiency = performanceSummaryReport.proficiency_percentage.get(0).getText().trim();
            CustomAssert.assertFalse(objectivePerformanceByStudentProficiency.equals("") && objectivePerformanceByStudentProficiency == null, "Verify Objective Performance By Students Proficiency", "Objective Performance By Students Proficiency is displaying correctly", "Objective Performance By Students Proficiency is empty.");

            String objectivePerformanceByStudentPerformance = performanceSummaryReport.performanceEntry_objectivePerformance.getText().trim();
            CustomAssert.assertFalse(objectivePerformanceByStudentPerformance.equals("") && objectivePerformanceByStudentPerformance == null, "Verify Objective Performance By Students Performance", "Objective Performance By Students Performance is displaying correctly", "Objective Performance By Students Performance is empty.");


            actual=performanceSummaryReport.classPerformanceByChapter_label.getText().trim();
            expected="Class Performance by Questions";
            CustomAssert.assertEquals(actual, expected, "Verify Class Performance by Questions Label", "Class Performance by Questions Label is displaying as " + expected + "", "Class Performance by Questions Label as "+expected+"");


            actual=performanceSummaryReport.classPerformanceByStudent_label.getText().trim();
            expected="Objective Performance By Students";
            CustomAssert.assertEquals(actual, expected, "Verify Objective Performance by Student Label", "Objective Performance by Student Label is displaying as " + expected + "", "Objective Performance by Chapters Student as "+expected+"");


            actual=performanceSummaryReport.classPerformance_subHeading.getText().trim();
            expected="(1.1: Define what is business.)";
            CustomAssert.assertEquals(actual, expected, "Verify Chapter Performance sub heading", "Chapter Performance sub heading is displaying as " + expected + "", "Chapter Performance sub heading Student as "+expected+"");

            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", performanceSummaryReport.questionNumber); //click on the question number

            Thread.sleep(3000);
            actual=preview.questionName.getText().trim();
            CustomAssert.assertFalse(actual.equals("") && actual==null, "Verify Question Name", "Question content displaying correctly", "Question Name is empty.");




            ProductivityReport productivityReport= PageFactory.initElements(driver,ProductivityReport.class);
            MostChallengingActivities mostChallengingActivities=PageFactory.initElements(driver,MostChallengingActivities.class);

            new LoginUsingLTI().ltiLogin("4");//login as instructor
            new Navigator().NavigateToInstructorReport();
            new Navigator().NavigateToAllReport(0); //Navigate to productive report
            Thread.sleep(2000);
            studProdCogProfReport= productivityReport.getStudProfPercentage().getText().trim();
            CustomAssert.assertFalse(studProdCogProfReport.equals("")||studProdCogProfReport==null, "Verify Chapter Proficiency in Chapter section", "Chapter Proficiency is displaying correctly", "Chapter Proficiency is empty");


            //Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(productivityReport.coloredMarker.get(0)); //Mouse hover on chapter dot
            String prodColorMarkerProf=productivityReport.ToolTip_proficiencyPercentage.getText().trim();
            String prodColorMarkerProfValue= prodColorMarkerProf.substring(0,2);
            System.out.println("prodColorMarkerProfValue:" + prodColorMarkerProfValue);
            CustomAssert.assertFalse(prodColorMarkerProfValue.equals("")||prodColorMarkerProfValue==null, "Verify Chapter Proficiency of tool tip", "Chapter Proficiency is displaying correctly", "Chapter Proficiency is empty in tool tip");

            //Click on the colored marker for the student

            productivityReport.coloredMarker.get(0).click();//click on the chapter dot
            Thread.sleep(2000);
            new MouseHover().mouserhoverbywebelement(productivityReport.coloredMarker.get(0));
            prodColorMarkerProfByChap=productivityReport.ToolTip_proficiencyPercentage.getText().trim();
            String prodColorMarkerProfByChapValue= prodColorMarkerProfByChap.substring(0,2);
            System.out.println("prodColorMarkerProfByChapValue:" + prodColorMarkerProfByChapValue);
            CustomAssert.assertFalse(prodColorMarkerProfByChapValue.equals("")||prodColorMarkerProfByChapValue==null, "Verify Objective Proficiency of tool tip", "Objective Proficiency is displaying correctly", "Objective Proficiency is empty in tool tip");

            studProdCogProfReport= productivityReport.getStudProfPercentage().getText().trim();
            CustomAssert.assertFalse(studProdCogProfReport.equals("")||studProdCogProfReport==null, "Verify Chapter Proficiency in Chapter section", "Chapter Proficiency is displaying correctly", "Chapter Proficiency is empty");

            productivityReport.coloredMarker.get(0).click();//click on the chapter dot
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(productivityReport.coloredMarker.get(0));
            prodColorMarkerProfByChap=productivityReport.ToolTip_proficiencyPercentage.getText().trim();
            String prodColorMarkerProfByChapValue1= prodColorMarkerProfByChap.substring(0,2);
            System.out.println("prodColorMarkerProfByChapValue:" + prodColorMarkerProfByChapValue);
            CustomAssert.assertFalse(prodColorMarkerProfByChapValue1.equals("") || prodColorMarkerProfByChapValue1 == null, "Verify Objective Proficiency of tool tip", "Objective Proficiency is displaying correctly", "Objective Proficiency is empty in tool tip");

            List<WebElement> tloProficiency = mostChallengingActivities.getStudChapProficiency();
            int count=0;
            for (WebElement each:tloProficiency){
                if(!each.getText().contains("%")){
                    count++;
                    break;
                }
            }
            if (count>0) {
                CustomAssert.fail("Verify Objective Proficiency of in class section ", "Objective Proficiency is empty in class section");
            }


            MetaCognitiveReport metaCognitiveReport= PageFactory.initElements(driver,MetaCognitiveReport.class);
            new LoginUsingLTI().ltiLogin("4");//login as instructor
            ReportUtil.log("Login Via LTI", "Instructor logged successfully", "info");

            new Navigator().NavigateToInstructorReport();
            new Navigator().NavigateToAllReport(1); //Navigate to Metacognitive Report
            Thread.sleep(2000);
            studProdCogProfReport= productivityReport.getStudProfPercentage().getText().trim();
            CustomAssert.assertFalse(studProdCogProfReport.equals("")||studProdCogProfReport==null, "Verify Chapter Proficiency in Chapter section", "Chapter Proficiency is displaying correctly", "Chapter Proficiency is empty");


            //Place the cursor on colored marker
            new MouseHover().mouserhoverbywebelement(productivityReport.coloredMarker.get(0)); //Mouse hover on chapter dot
            prodColorMarkerProf=metaCognitiveReport.getProficiency().getText().trim();
            prodColorMarkerProfValue= prodColorMarkerProf.substring(12,16);
            System.out.println("prodColorMarkerProfValue:" + prodColorMarkerProfValue);
            CustomAssert.assertFalse(prodColorMarkerProfValue.equals("")||prodColorMarkerProfValue==null, "Verify Chapter Proficiency of tool tip", "Chapter Proficiency is displaying correctly", "Chapter Proficiency is empty in tool tip");

            //Click on the colored marker for the student

            productivityReport.coloredMarker.get(0).click();//click on the chapter dot
            Thread.sleep(2000);
            new WebDriverUtil().waitTillVisibilityOfElement(productivityReport.coloredMarker.get(0), 40);
            new MouseHover().mouserhoverbywebelement(productivityReport.coloredMarker.get(0));
            prodColorMarkerProfByChap=metaCognitiveReport.getProficiency().getText().trim();
            prodColorMarkerProfByChapValue= prodColorMarkerProfByChap.substring(12,16);
            System.out.println("prodColorMarkerProfByChapValue:" + prodColorMarkerProfByChapValue);
            CustomAssert.assertFalse(prodColorMarkerProfByChapValue.equals("")||prodColorMarkerProfByChapValue==null, "Verify Objective Proficiency of tool tip", "Objective Proficiency is displaying correctly", "Objective Proficiency is empty in tool tip");

            studProdCogProfReport= productivityReport.getStudProfPercentage().getText().trim();
            CustomAssert.assertFalse(studProdCogProfReport.equals("")||studProdCogProfReport==null, "Verify Chapter Proficiency in Chapter section", "Chapter Proficiency is displaying correctly", "Chapter Proficiency is empty");

            productivityReport.coloredMarker.get(0).click();//click on the chapter dot
            Thread.sleep(3000);
            new WebDriverUtil().waitTillVisibilityOfElement(productivityReport.coloredMarker.get(0), 40);
            new MouseHover().mouserhoverbywebelement(productivityReport.coloredMarker.get(0));
            prodColorMarkerProfByChap=metaCognitiveReport.getProficiency().getText().trim();
            prodColorMarkerProfByChapValue1= prodColorMarkerProfByChap.substring(0,2);
            System.out.println("prodColorMarkerProfByChapValue:" + prodColorMarkerProfByChapValue);
            CustomAssert.assertFalse(prodColorMarkerProfByChapValue1.equals("") || prodColorMarkerProfByChapValue1 == null, "Verify Objective Proficiency of tool tip", "Objective Proficiency is displaying correctly", "Objective Proficiency is empty in tool tip");

            List<WebElement> tloProficiency1 = mostChallengingActivities.getStudChapProficiency();
            int count1=0;
            for (WebElement each:tloProficiency1){
                if(!each.getText().contains("%")){
                    count1++;
                    break;
                }
            }
            if (count1>0) {
                CustomAssert.fail("Verify Objective Proficiency of in class section ", "Objective Proficiency is empty in class section");
            }

            CustomAssert.assertTrue(metaCognitiveReport.reportContent.isDisplayed(),"Verify below report","Proficiency with respect to Objective displayed correctly.","Proficiency with respect to Objective is not displayed correctly.");


            MostChallengingActivities mostChallengingReport= PageFactory.initElements(driver,MostChallengingActivities.class);

            new LoginUsingLTI().ltiLogin("4");//login as Instructor
            ReportUtil.log("Login Via LTI", "Instructor logged successfully", "info");

            new Navigator().NavigateToInstructorReport();
            new Navigator().NavigateToAllReport(2); //Navigate to Most Challenging Report
            Thread.sleep(2000);


            //16. Click on 'View By Chapters' tab
            String mostObjectiveProficiency=mostChallengingReport.getStudChapProficiency().get(0).getText().trim();
            System.out.println("Most Challenging proficiency By Chapter:" + mostObjectiveProficiency);
            CustomAssert.assertTrue(mostObjectiveProficiency.equals("95%"), "Verify Most Challenging proficiency By Chapter", "Most Challenging proficiency By Chapter is displaying correctly", "Most Challenging proficiency By Chapter is empty.");

            //"Learning Objective Performance" should be updated taking the changed grade for that assignment
            String mostObjectivePerformance=mostChallengingReport.instructorChapterPerformance.get(0).getText().trim();
            System.out.println("Most Challenging performance By Chapter:" + mostObjectivePerformance);

          //  CustomAssert.assertTrue(mostObjectivePerformance.equals("25/25"), "Verify Most Challenging performance By Objective", "Most Challenging performance By Chapter is displaying correctly", "Most Challenging performance By Chapter is empty.");

            CustomAssert.assertTrue(mostObjectivePerformance.equals("22/25"), "Verify Most Challenging performance By Objective", "Most Challenging performance By Chapter is displaying correctly", "Most Challenging performance By Chapter is empty.");


            actual=mostChallengingReport.mostChallengingActivityTitle_instructor.getText();
            expected="Most Challenging Activities";
            CustomAssert.assertEquals(actual, expected, "Verify Most Challenging title", "Most Challenging title is displaying as " + expected + "", "Most Challenging title not displaying as "+expected+"");


            actual=mostChallengingReport.viewByObjectives_instructor.getText().trim();
            expected="View By Objectives";
            CustomAssert.assertEquals(actual, expected, "Verify View By Objectives label", "View By Objectives label is displaying as " + expected + "", "View By Objectives label not displaying as " + expected + "");

            actual=mostChallengingReport.viewByChapter_instructor.getText().trim();
            expected="View By Chapters";
            CustomAssert.assertEquals(actual, expected, "Verify View By Chapters label", "View By Chapters label is displaying as " + expected + "", "View By Chapters label not displaying as " + expected + "");


            mostChallengingReport.viewByObjectives_instructor.click(); //click on the view ByObjective
            Thread.sleep(30000);
            List<WebElement> tloProficiency2 = mostChallengingReport.getStudChapProficiency();
            List<String> list=new ArrayList<>();
            for (WebElement each:tloProficiency2 ){
                list.add(each.getAttribute("title"));

            }

            CustomAssert.assertFalse(list.isEmpty(), "Verify Learning Objective Proficiency", "Learning Objective Proficiency is displaying correctly", "Learning Objective Proficiency is not displaying correctly");

            List<WebElement> tloProficiencies = mostChallengingReport.getStudChapProficiency();
            int count2=0;
            for (WebElement each:tloProficiencies){
                if(!each.getAttribute("title").contains("%")){
                    count2++;
                    break;
                }
            }
            if (count2>0) {
                CustomAssert.fail("Verify Objective Proficiency of in class section ", "Objective Proficiency is empty");
            }

            List<WebElement> tloPerformance = mostChallengingReport.instructorChapterPerformance;
            List<String> list1=new ArrayList<>();
            for (WebElement each:tloPerformance ){
                list1.add(each.getAttribute("title"));
            }
            CustomAssert.assertFalse(list1.isEmpty(),"Verify Learning Objective Performance","Learning Objective Performance is displaying correctly","Learning Objective Performance is not displaying correctly");


        } catch (Exception e) {
            Assert.fail("Exception in TC verifyPracticeTestMostChallengingReportOfInstructor of class StudentDiagnosticReports",e);
        }
    }



}
