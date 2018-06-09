package com.snapwiz.selenium.tests.staf.orion.testcases.IT22.R223;

import au.com.bytecode.opencsv.CSVReader;
import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.apphelper.*;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.reports.PerformanceReportInstructor;
import com.snapwiz.selenium.tests.staf.orion.uihelper.Click;
import com.snapwiz.selenium.tests.staf.orion.uihelper.robotClick;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by durgapathi on 29/10/15.
 */
public class ExportTheGradebookDataInTheChangedCSVFile {

    public void validateCSVFileContents(String dataIndex,CSVReader path){
        try{

            String assessmentName =  ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            String familyname =  ReadTestData.readDataByTagName("", "familyname", dataIndex);
            String givenname = ReadTestData.readDataByTagName("", "givenname", dataIndex);
            String context_title = ReadTestData.readDataByTagName("", "context_title", dataIndex);

            String [] nextLine;
            ArrayList<String> cellsContent = new ArrayList<>();
            while((nextLine=path.readNext())!=null)
            {
                for(int a=0;a<10;a++) {
                    String assignmentStatus = nextLine[a];
                    cellsContent.add(assignmentStatus);
                    if(nextLine.length==1){
                        break;
                    }
                }
            }

            for(int a=0;a<cellsContent.size();a++){
                System.out.println("assignmentstatus : " + cellsContent.get(a));

            }

            //Expected  - 4.First row should have "First name" text in first columns.
            if(!cellsContent.get(0).equals("First Name"))
                Assert.fail("First row should have \"First name\" text in first columns.");

            //Expected - 5.In second row and first column should be contain Student First name.
            System.out.println("givenname : " + givenname);
            System.out.println("cellsContent.get(10) : " + cellsContent.get(10));

            if(!cellsContent.get(10).trim().equals(givenname))
                Assert.fail("In second row and first column should be contain Student First name.");

            //Expected - 6.First row should have "Last name" text in second columns.
            if(!cellsContent.get(1).equals("Last Name"))
                Assert.fail("First row should have \"Last name\" text in second columns.");

            //Expected - 7.In second row and second column should be contain Student Last name.
            if(!cellsContent.get(11).equals(familyname))
                Assert.fail("In second row and second column should be contain Student Last name.");

            //Expected - 8.First row should have "course name" text in third column.
            if(!cellsContent.get(2).equals("Chapter 1 Proficiency at Diagnostic"))
                Assert.fail("First row should have \"Chapter 1 Proficiency at Diagnostic\" text in third column.");

            //Expected - 9.In second row and third column should contain proficiency.
            if(cellsContent.get(12).length() < 2)
                Assert.fail("In second row and third column should contain proficiency.");

            //Expected - 9.In second row and third column should be contain %.
            if(!cellsContent.get(12).contains("%"))
                Assert.fail("In second row and third column should be contain %.");

            //Expected - 10.First row should have "class Section name" text in Chapter 1 Current Proficiency.
            if(!cellsContent.get(3).equals("Chapter 1 Current Proficiency"))
                Assert.fail("First row should have \"Chapter 1 Current Proficiency\" text in fourth column.");

            //Expected - 11.In second row and fourth column should be contain class section name.
            if(cellsContent.get(13).length() < 2)
                Assert.fail("In second row and fourth column should be contain proficiency.");

            //Expected - 10.In second row and third column should be contain %.
            if(!cellsContent.get(13).contains("%"))
                Assert.fail("In second row and fouth column should be contain %.");

            //Expected - 12.First row should have "Date"  text in fifth column.
            if(!cellsContent.get(4).equals("Chapter 1 Performance"))
                Assert.fail("First row should have \"Chapter 1 Performance\"  text in fifth column.");

            // In second row and 5th column student chapter 1 performance should be displayed.
            if(cellsContent.get(14).length() < 6)
                Assert.fail("In second row and 5th column student chapter 1 performance should be displayed.");

            //Performance should be "(x/y)"  format where x is correct answer and y is total number of question.
            if(!cellsContent.get(14).contains("(") && !cellsContent.get(14).contains(")") && !cellsContent.get(14).contains("/"))
                Assert.fail("Performance should be \"(x/y)\"  format where x is correct answer and y is total number of question.");

            //Expected - The Sixth column should have 'Chapter 1 Time Spent” as text in first row.
            if(!cellsContent.get(5).equals("Chapter 1 Time Spent"))
                Assert.fail("First row should have \"Chapter 1 Time Spent\"  text in sixthcolumn");

            //Expected - In second row and 6th column chapter 1 time spent by student should be displayed.
            if(!cellsContent.get(15).contains("sec") && !cellsContent.get(15).contains("min"))
                Assert.fail("In second row and sixth column should contain time spent");


            // 2nd chapter
            //19.The same above should be repeated for all subsequent  chapter 2.

            //Expected - 8.First row should have "course name" text in 7th column.
            if(!cellsContent.get(6).equals("Chapter 2 Proficiency at Diagnostic"))
                Assert.fail("First row should have \"Chapter 2 Proficiency at Diagnostic\" text in 7th column.");

            //Expected - 9.In second row and third column should contain proficiency.
            if(cellsContent.get(16).length() < 2)
                Assert.fail("In second row and 7th column should contain proficiency.");

            //Expected - 9.In second row and 7th column should be contain %.
            if(!cellsContent.get(16).contains("%"))
                Assert.fail("In second row and 7th column should be contain %.");

            //Expected - 10.First row ans 8th column should have "class Section name" text in Chapter 2 Current Proficiency.
            if(!cellsContent.get(7).equals("Chapter 2 Current Proficiency"))
                Assert.fail("First row should have \"Chapter 2 Current Proficiency\" text in 8th column.");

            //Expected - 11.In second row and 8th column should be contain class section name.
            if(cellsContent.get(17).length() < 2)
                Assert.fail("In second row and 8th column should be contain proficiency.");

            //Expected - 10.In second row and 8th column should be contain %.
            if(!cellsContent.get(17).contains("%"))
                Assert.fail("In second row and 8th column should be contain %.");

            //Expected - 12.First row and 9th column should have "Date"  text in fifth column.
            if(!cellsContent.get(8).equals("Chapter 2 Performance"))
                Assert.fail("First row should have \"Chapter 2 Performance\"  text in 9th column.");

            // In second row and 5th column student chapter 2 performance should be displayed.
            if(cellsContent.get(18).length() < 6)
                Assert.fail("In second row and 9th column student chapter 1 performance should be displayed.");

            //Performance should be "(x/y)"  format where x is correct answer and y is total number of question.
            if(!cellsContent.get(18).contains("(") && !cellsContent.get(18).contains(")") && !cellsContent.get(18).contains("/"))
                Assert.fail("Performance should be \"(x/y)\"  format where x is correct answer and y is total number of question.");

            //Expected - The Sixth column should have 'Chapter 2 Time Spent” as text in first row.
            System.out.println("cellsContent.get(9)::"+cellsContent.get(9));

            if(!cellsContent.get(9).equals("Chapter 2 Time Spent"))
                Assert.fail("First row should have \"Chapter 2 Time Spent\"  text in 10th column");

            //Expected - In second row and 6th column chapter 2 time spent by student should be displayed.
            System.out.println("cellsContent.get(19)::"+cellsContent.get(19));
            if(!cellsContent.get(19).contains("sec") && !cellsContent.get(19).equals("1 min"))
                Assert.fail("In second row and 10th column should contain time spent");

        }
        catch(Exception e){
            Assert.fail("Exception in test method 'validateCSVFileContents' in class 'ExportTheGradebookDataInTheChangedCSVFile'",e);
        }
    }

    @AfterMethod
    public void deleteCSVFile()
    {
        try
        {
           // Driver.startDriver();
            String cName = Config.course;
            String csvFileTitle= "StudentsCoursePerformanceReport-"+cName+".csv";
            System.out.println("csvFileTitle::" + csvFileTitle);
            File filePath = new File(Config.downloadPath+csvFileTitle);
            System.out.println("filePath::"+Config.downloadPath+csvFileTitle);
            filePath.delete();
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test method 'deleteCSVFile' in class 'ExportTheGradebookDataInTheChangedCSVFile'",e);
        }
    }

    @Test(priority = 1,enabled = true)
    public void performanceReportPageAfterDiagTest()
    {
        try
        {
            Driver.startDriver();
            new LoginUsingLTI().ltiLogin("12"); // Login as Student
            new DiagnosticTest().startTest(0, 3);
            new DiagnosticTest().attemptDiagnosticTestRandomly();
            Thread.sleep(2000);
            new Click().clickByclassname("al-home-icon");
            Thread.sleep(2000);
            new DiagnosticTest().startTest(0, 3);
            new DiagnosticTest().attemptDiagnosticTestRandomly();
            new LoginUsingLTI().ltiLogin("12_1"); // Login as instructor
            new Navigator().navigateToReport("Performance Report");
            Thread.sleep(2000);
            // verify performance report page
            boolean perfReportPage = Driver.driver.findElement(By.id("ir-performance-report-by-chapters")).isDisplayed();
            if(perfReportPage == false)
            {
                Assert.fail("Performance report page is not displayed.");
            }
            // export csv button
            boolean exportCSVButton = Driver.driver.findElement(By.className("idb-export-csv-performance-report")).isDisplayed();
            if(exportCSVButton == false)
            {
                Assert.fail("\"Export to CSV\" button is not displayed in class performace by student section.");
            }
            // Click on export CSV report
            new Click().clickByclassname("idb-export-csv-performance-report");
            new robotClick().robotClick();
            Thread.sleep(5000);
            CSVReader path = new ReadCSVFile().readCSVFilePath();
            validateCSVFileContents("12",path);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase performanceReportPageAfterDiagTest of class ExportTheGradebookDataInTheChangedCSVFile", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void performanceReportPageAfterPracticeTest()
    {
        try
        {
            Driver.startDriver();
            new LoginUsingLTI().ltiLogin("12");
            new PracticeTest().startTest();
            Thread.sleep(2000);
            new PracticeTest().attemptPracticeTestRandomly(6);
            new LoginUsingLTI().ltiLogin("12_1"); // Login as instructor
            new Navigator().navigateToReport("Performance Report");
            Thread.sleep(2000);
            // verify performance report page
            boolean perfReportPage = Driver.driver.findElement(By.id("ir-performance-report-by-chapters")).isDisplayed();
            if(perfReportPage == false)
            {
                Assert.fail("Performance report page is not displayed.");
            }
            // export csv button
            boolean exportCSVButton = Driver.driver.findElement(By.className("idb-export-csv-performance-report")).isDisplayed();
            if(exportCSVButton == false)
            {
                Assert.fail("\"Export to CSV\" button is not displayed in class performace by student section.");
            }
            // Click on export CSV report
            new Click().clickByclassname("idb-export-csv-performance-report");
            new robotClick().robotClick();
            Thread.sleep(5000);
            CSVReader path = new ReadCSVFile().readCSVFilePath();
            validateCSVFileContents("12",path);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase performanceReportPageAfterPracticeTest of class ExportTheGradebookDataInTheChangedCSVFile", e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void existingUser()
    {
        try
        {
            Driver.startDriver();
            new DirectLogin().directLogin("49");
            new DiagnosticTest().startTest(0, 3);
            new DiagnosticTest().attemptDiagnosticTestRandomly();
            Thread.sleep(2000);
            new Click().clickByclassname("al-home-icon");
            Thread.sleep(2000);
            new DiagnosticTest().startTest(0, 3);
            new DiagnosticTest().attemptDiagnosticTestRandomly();
            new DirectLogin().directLogin("49_1"); // Login as instructor
            new Navigator().navigateToReport("Performance Report");
            Thread.sleep(2000);
            // verify performance report page
            boolean perfReportPage = Driver.driver.findElement(By.id("ir-performance-report-by-chapters")).isDisplayed();
            if(perfReportPage == false)
            {
                Assert.fail("Performance report page is not displayed.");
            }
            // export csv button
            boolean exportCSVButton = Driver.driver.findElement(By.className("idb-export-csv-performance-report")).isDisplayed();
            if(exportCSVButton == false)
            {
                Assert.fail("\"Export to CSV\" button is not displayed in class performace by student section.");
            }
            // Click on export CSV report
            new Click().clickByclassname("idb-export-csv-performance-report");
            new robotClick().robotClick();
            Thread.sleep(5000);
            CSVReader path = new ReadCSVFile().readCSVFilePath();
            validateCSVFileContents("49",path);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase existingUser of class ExportTheGradebookDataInTheChangedCSVFile", e);
        }
    }

    @Test(priority = 4,enabled = true)
    public void performanceReportPageAfterDiagTestAsMentor()
    {
        try
        {
            Driver.startDriver();
            new LoginUsingLTI().ltiLogin("12_2"); // Login as Student
            new DiagnosticTest().startTest(0, 3);
            new DiagnosticTest().attemptDiagnosticTestRandomly();
            Thread.sleep(2000);
            new Click().clickByclassname("al-home-icon");
            Thread.sleep(2000);
            new DiagnosticTest().startTest(0, 3);
            new DiagnosticTest().attemptDiagnosticTestRandomly();
            new LoginUsingLTI().ltiLogin("12_3"); // Login as mentor
            new Navigator().navigateToReport("Performance Report");
            Thread.sleep(2000);
            // verify performance report page
            boolean perfReportPage = Driver.driver.findElement(By.id("ir-performance-report-by-chapters")).isDisplayed();
            if(perfReportPage == false)
            {
                Assert.fail("Performance report page is not displayed.");
            }
            // export csv button
            boolean exportCSVButton = Driver.driver.findElement(By.className("idb-export-csv-performance-report")).isDisplayed();
            if(exportCSVButton == false)
            {
                Assert.fail("\"Export to CSV\" button is not displayed in class performace by student section.");
            }
            // Click on export CSV report
            new Click().clickByclassname("idb-export-csv-performance-report");
            new robotClick().robotClick();
            Thread.sleep(5000);
            CSVReader path = new ReadCSVFile().readCSVFilePath();
            validateCSVFileContents("12_2",path);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase performanceReportPageAfterDiagTestAsMentor of class ExportTheGradebookDataInTheChangedCSVFile", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void performanceReportPageAfterPracticeTestAsMentor()
    {
        try
        {
            Driver.startDriver();
            new LoginUsingLTI().ltiLogin("12_2");
            new PracticeTest().startTest();
            Thread.sleep(2000);
            new PracticeTest().attemptPracticeTestRandomly(6);
            new LoginUsingLTI().ltiLogin("12_3"); // Login as instructor
            new Navigator().navigateToReport("Performance Report");
            Thread.sleep(2000);
            // verify performance report page
            boolean perfReportPage = Driver.driver.findElement(By.id("ir-performance-report-by-chapters")).isDisplayed();
            if(perfReportPage == false)
            {
                Assert.fail("Performance report page is not displayed.");
            }
            // export csv button
            boolean exportCSVButton = Driver.driver.findElement(By.className("idb-export-csv-performance-report")).isDisplayed();
            if(exportCSVButton == false)
            {
                Assert.fail("\"Export to CSV\" button is not displayed in class performace by student section.");
            }
            // Click on export CSV report
            new Click().clickByclassname("idb-export-csv-performance-report");
            new robotClick().robotClick();
            Thread.sleep(5000);
            CSVReader path = new ReadCSVFile().readCSVFilePath();
            validateCSVFileContents("12_2",path);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase performanceReportPageAfterPracticeTestAsMentor of class ExportTheGradebookDataInTheChangedCSVFile", e);
        }
    }

    @Test(priority = 6,enabled = true)
    public void existingUserAsMentor()
    {
        try
        {
            Driver.startDriver();
            new DirectLogin().directLogin("49_2");
            new DiagnosticTest().startTest(0, 3);
            new DiagnosticTest().attemptDiagnosticTestRandomly();
            Thread.sleep(2000);
            new Click().clickByclassname("al-home-icon");
            Thread.sleep(2000);
            new DiagnosticTest().startTest(0, 3);
            new DiagnosticTest().attemptDiagnosticTestRandomly();
            new DirectLogin().directLogin("49_3"); // Login as mentor
            new Navigator().navigateToReport("Performance Report");
            Thread.sleep(2000);
            // verify performance report page
            boolean perfReportPage = Driver.driver.findElement(By.id("ir-performance-report-by-chapters")).isDisplayed();
            if(perfReportPage == false)
            {
                Assert.fail("Performance report page is not displayed.");
            }
            // export csv button
            boolean exportCSVButton = Driver.driver.findElement(By.className("idb-export-csv-performance-report")).isDisplayed();
            if(exportCSVButton == false)
            {
                Assert.fail("\"Export to CSV\" button is not displayed in class performace by student section.");
            }
            // Click on export CSV report
            new Click().clickByclassname("idb-export-csv-performance-report");
            new robotClick().robotClick();
            Thread.sleep(5000);
            CSVReader path = new ReadCSVFile().readCSVFilePath();
            validateCSVFileContents("49_2",path);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase existingUserAsMentor of class ExportTheGradebookDataInTheChangedCSVFile", e);
        }
    }
}
