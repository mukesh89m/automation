package com.snapwiz.selenium.tests.staf.orion.testcases.IT24.R244;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.apphelper.*;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.Dashboard.ClassSectionDropDown;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.Dashboard.DashBoard;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.allActivity.AllActivity;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.reports.MetaCognitiveReport;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.reports.MostChallengingActivities;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.reports.PerformanceReportInstructor;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.reports.ProductivityReport;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by priyanka on 20-11-2015.
 */
public class InstructorResetStudentProficiencyData {

    @Test(priority = 1, enabled = true)
    public void instructorResetStudentProficiencyData() {

        try {
            //tc row no 10-32
            Driver.startDriver();
            PerformanceReportInstructor performanceReportInstructor = PageFactory.initElements(Driver.driver, PerformanceReportInstructor.class);
            new LoginUsingLTI().ltiLogin("10"); // Login as Student
            new DiagnosticTest().startTest(0, 2);
            new DiagnosticTest().attemptAllCorrect(2, false, false);

            new LoginUsingLTI().ltiLogin("10_1"); // Login as Instructor
            performanceReportInstructor.viewClassReport.click();//click on view class report
            performanceReportInstructor.performanceReport.click();//click on performance Report
            Assert.assertEquals(performanceReportInstructor.performanceReport.getText(), "Performance Report", "Performance report page is not displaying");
            Assert.assertEquals(performanceReportInstructor.resetProficiency_button.isDisplayed(), true, "Reset Proficiency button is not displaying");

            boolean elementFound = false;
            try {
                WebElement ele = performanceReportInstructor.resetProficiency_enable;
                ele.isDisplayed();
                elementFound = true;
            } catch (Exception e) {
            }
            Assert.assertEquals(elementFound, false, "Reset Proficiency button is not by default in disable state");
            performanceReportInstructor.checkBoxStudent.get(0).click();//click on check box
            Assert.assertEquals(performanceReportInstructor.resetProficiency_enable.isDisplayed(), true, "Reset Proficiency button is not enabled");
            performanceReportInstructor.checkBoxStudent.get(0).click();//uncheck  check box
            boolean elementFound1 = false;
            try {
                WebElement ele = performanceReportInstructor.resetProficiency_enable;
                ele.isDisplayed();
                elementFound1 = true;
            } catch (Exception e) {
            }
            Assert.assertEquals(elementFound1, false, "Reset Proficiency button is not disable");
            performanceReportInstructor.checkBoxStudent.get(0).click();//uncheck  check box
            performanceReportInstructor.resetProficiency_button.click();//click on reset proficiency
            Assert.assertEquals(performanceReportInstructor.resetProficiencyHeader.getText(), "RESET PROFICIENCY", "Reset Proficiency as header is displaying");
            Assert.assertEquals(performanceReportInstructor.studentProficiencyReset.getText(), "Following student’s proficiency will be reset", "Following student’s proficiency will be reset message is not displaying");
            Assert.assertEquals(performanceReportInstructor.studentName.get(3).getText(), "family, givenname", "selected student name is not displaying");
            Assert.assertEquals(performanceReportInstructor.proficiencyInformationMessage.getText(), "If you would like a record of this Performance Report before you reset, please export to a CSV file for your records.", "proficiency message is not displaying");

            String colour = performanceReportInstructor.proficiencyInformationMessage.getCssValue("color");
            Assert.assertEquals(colour, "rgba(93, 171, 74, 1)", "proficiency message is not in green color");
            Assert.assertEquals(performanceReportInstructor.cancelLink.isDisplayed(), true, "cancel Link is not available");
            Assert.assertEquals(performanceReportInstructor.resetLink.isDisplayed(), true, "reset Link is not available");
            performanceReportInstructor.resetLink.click();//click on reset link
            String message = performanceReportInstructor.notificationMessage.getText();
            Assert.assertEquals(message, "Are you sure you want to permanently reset proficiency for the following students?YesNo", "Confirmation message is not available");
            performanceReportInstructor.noLink.click();//click on no link

            boolean elementFound2 = false;
            try {
                WebElement ele = performanceReportInstructor.noLink;
                ele.isDisplayed();
                elementFound2 = true;
            } catch (Exception e) {
            }
            Assert.assertEquals(elementFound2, false, "confirmation pop is not closed");
            performanceReportInstructor.cancelLink.click();//click on cancel
            Assert.assertEquals(performanceReportInstructor.student_Name.get(0).isDisplayed(), true, "The Proficiency data got reset for student");

            performanceReportInstructor.resetProficiency_button.click();//click on reset proficiency
            performanceReportInstructor.cancelLink.click();//click on cancel
            Assert.assertEquals(performanceReportInstructor.student_Name.get(0).isDisplayed(), true, "The Proficiency data got reset for student");

            performanceReportInstructor.resetProficiency_button.click();//click on reset proficiency
            performanceReportInstructor.resetLink.click();//click on reset
            performanceReportInstructor.yesLink.click();//click on yes link
            Driver.driver.navigate().refresh();
            performanceReportInstructor.viewClassReport.click();//click on view class report
            performanceReportInstructor.performanceReport.click();//click on performance Report
            boolean elementFound3 = false;
            try {
                WebElement ele = performanceReportInstructor.student_Name.get(0);
                ele.isDisplayed();
                elementFound3 = true;
            } catch (Exception e) {
            }
            Assert.assertEquals(elementFound3, false, "proficiency data is not reset for student");

        } catch (Exception e) {
            Assert.fail("Exception in test method instructorResetStudentProficiencyData of class InstructorResetStudentProficiencyData", e);
        }
    }


    @Test(priority = 2, enabled = true)
    public void instructorViewStudentDataWithoutResetProficiency() {

        try {
            //tc row no 36
            Driver.startDriver();
            PerformanceReportInstructor performanceReportInstructor;
            performanceReportInstructor = PageFactory.initElements(Driver.driver, PerformanceReportInstructor.class);
            DashBoard dashBoard;
            dashBoard = PageFactory.initElements(Driver.driver,DashBoard.class);
            ProductivityReport productivityReport;
            productivityReport = PageFactory.initElements(Driver.driver,ProductivityReport.class);
            MetaCognitiveReport metaCognitiveReport;
            metaCognitiveReport = PageFactory.initElements(Driver.driver,MetaCognitiveReport.class);

            new LoginUsingLTI().ltiLogin("36"); // Login as Student1
            new DiagnosticTest().startTest(0, 2);
            new DiagnosticTest().attemptAllCorrect(2,false,false);

            new LoginUsingLTI().ltiLogin("36_2"); // Login as Student2
            new DiagnosticTest().startTest(0, 2);
            new DiagnosticTest().attemptAllCorrect(2,false,false);

            new RunScheduledJobs().runScheduledJob("ClassSectionActivityJob");

            new LoginUsingLTI().ltiLogin("36_1"); // Login as Instructor
            dashBoard = PageFactory.initElements(Driver.driver,DashBoard.class);
            Assert.assertEquals(dashBoard.lastSevenDaysActivity.isDisplayed(),true,"last seven days activity is not displaying by default");
            dashBoard.lastFiveWeeksActivity.get(1).click();//click on last 5 week activity
            dashBoard.lastFiveWeeksActivity.get(2).click();//click on all activity
            String value1=dashBoard.courseProficiencys.get(0).getText();
            String value2=dashBoard.courseProficiencys.get(1).getText();
            String value3=dashBoard.courseProficiencys.get(2).getText();
            String value4=dashBoard.courseProficiencys.get(3).getText();
            String subvalue1=value1.substring(0,value1.lastIndexOf("%"));
            System.out.println(subvalue1);
            String subvalue2=value2.substring(0,value2.lastIndexOf("%"));
            System.out.println(subvalue2);
            String subvalue3=value3.substring(0,value3.lastIndexOf("%"));
            System.out.println(subvalue3);
            String subvalue4=value4.substring(0,value4.lastIndexOf("%"));
            System.out.println(subvalue4);

            int firstValue=Integer.parseInt(subvalue1);
            int secondValue=Integer.parseInt(subvalue2);
            int thirdValue=Integer.parseInt(subvalue3);
            int fourthValue=Integer.parseInt(subvalue4);
            int totalPercentage=firstValue+secondValue+thirdValue+fourthValue;
            System.out.println(totalPercentage);

            try {
                if(totalPercentage != 99){
                    System.out.println("total percentage is not equal to 99 or 101");
                }
            }
            catch (Exception e)
            {
                try {
                    if(totalPercentage != 101){
                        System.out.println("total percentage is not equal to 99 or 101");
                    }
                }
                catch (Exception e1){
                }
                Assert.fail("fail");
            }



            String proficiencyPercentage= dashBoard.proficiencyPercentage.get(0).getText();
            System.out.println("proficiencyPercentage" +proficiencyPercentage);

            String proficiencyPin= Driver.driver.findElements(By.className("idb-proficiency-pin")).get(Driver.driver.findElements(By.className("idb-proficiency-pin")).size()-1).getAttribute("style");
            System.out.println("proficiencyPin" +proficiencyPin);

            String proficiencyValue= Driver.driver.findElements(By.className("idb-preformance-text")).get(Driver.driver.findElements(By.className("idb-preformance-text")).size()-1).getAttribute("title");
            System.out.println("proficiencyValue" +proficiencyValue);

            dashBoard.allLink.click();
            String proficiencyPercentage1= dashBoard.proficiencyPercentage.get(0).getText();
            System.out.println("proficiencyPercentage" +proficiencyPercentage1);

            String proficiencyPin1= Driver.driver.findElements(By.className("idb-proficiency-pin")).get(Driver.driver.findElements(By.className("idb-proficiency-pin")).size()-1).getAttribute("style");
            System.out.println("proficiencyPin" +proficiencyPin1);

            String proficiencyValue1= Driver.driver.findElements(By.className("idb-preformance-text")).get(Driver.driver.findElements(By.className("idb-preformance-text")).size()-1).getAttribute("title");
            System.out.println("proficiencyValue" +proficiencyValue1);

            performanceReportInstructor.viewClassReport.click();//click on view class report
            performanceReportInstructor.performanceReport.click();//click on performance Report

            String performanceBar= Driver.driver.findElement(By.cssSelector("rect[fill='#6bb45f']")).getAttribute("height");
            System.out.println("performanceBar" +performanceBar);

            int studentSize=performanceReportInstructor.student_Name.size();
            System.out.println("studentSize" +studentSize);
            performanceReportInstructor.contributedChapterLink.click();
            Thread.sleep(2000);
            System.out.println(performanceReportInstructor.reportTitle.getText().trim());
            Assert.assertEquals(performanceReportInstructor.reportTitle.getText().trim(),"Class Performance by Objectives","Class Performance by Objectives page not opened");
            int studentSize1=performanceReportInstructor.student_Name.size();
            performanceReportInstructor.backArrow.click();//click on back arrow
            Thread.sleep(2000);
            performanceReportInstructor.checkBoxStudent.get(1).click();//uncheck  check box
            performanceReportInstructor.resetProficiency_button.click();//click on reset proficiency
            performanceReportInstructor.resetLink.click();//click on reset
            performanceReportInstructor.yesLink.click();//click on yes link

            new RunScheduledJobs().runScheduledJob("ClassSectionActivityJob");

            new LoginUsingLTI().ltiLogin("36_1"); // Login as Instructor
            String proficiencyPercentage2= dashBoard.proficiencyPercentage.get(0).getText();
            String proficiencyPin2= Driver.driver.findElements(By.className("idb-proficiency-pin")).get(Driver.driver.findElements(By.className("idb-proficiency-pin")).size()-1).getAttribute("style");
            String proficiencyValue2= Driver.driver.findElements(By.className("idb-preformance-text")).get(Driver.driver.findElements(By.className("idb-preformance-text")).size()-1).getAttribute("title");
            dashBoard.allLink.click();
            String proficiencyPercentage3= dashBoard.proficiencyPercentage.get(0).getText();
            String proficiencyPin3= Driver.driver.findElements(By.className("idb-proficiency-pin")).get(Driver.driver.findElements(By.className("idb-proficiency-pin")).size()-1).getAttribute("style");
            String proficiencyValue3= Driver.driver.findElements(By.className("idb-preformance-text")).get(Driver.driver.findElements(By.className("idb-preformance-text")).size()-1).getAttribute("title");
            performanceReportInstructor.viewClassReport.click();//click on view class report
            performanceReportInstructor.performanceReport.click();//click on performance Report
            String performanceBar1= Driver.driver.findElement(By.cssSelector("rect[fill='#6bb45f']")).getAttribute("height");
            int studentSize2=performanceReportInstructor.student_Name.size();
            performanceReportInstructor.contributedChapterLink.click();
            Thread.sleep(2000);
            Assert.assertEquals(performanceReportInstructor.reportTitle.getText().trim(),"Class Performance by Objectives","Class Performance by Objectives page not opened");
            int studentSize3=performanceReportInstructor.student_Name.size();

            if(proficiencyPercentage.equals(proficiencyPercentage2))
                Assert.fail("proficiencyPercentage is not reverted back");
            if(proficiencyPin.equals(proficiencyPin2))
                Assert.fail("proficiencyPin is not reverted back");
            if(proficiencyValue.equals(proficiencyValue2))
                Assert.fail("proficiencyValue is not reverted back");
            if(proficiencyPercentage1.equals(proficiencyPercentage3))
                Assert.fail("proficiencyPercentage is not reverted back");
            if(proficiencyPin1.equals(proficiencyPin3))
                Assert.fail("proficiencyPin is not reverted back");
            if(proficiencyValue1.equals(proficiencyValue3))
                Assert.fail("proficiencyValue is not reverted back");
            if(performanceBar.equals(performanceBar1))
                Assert.fail("performanceBar is not reverted back");
            if(studentSize ==studentSize2)
                Assert.fail("studentSize is not reverted back");
            if(studentSize1 ==studentSize3)
                Assert.fail("studentSize is not reverted back");

            new LoginUsingLTI().ltiLogin("36_1"); // Login as Instructor
            productivityReport = PageFactory.initElements(Driver.driver,ProductivityReport.class);
            performanceReportInstructor.viewClassReport.click();//click on view class report
            productivityReport.productivityReport.click();//click on productivity report
            int colouredDotSize=productivityReport.coloredMarker.size();
            if(colouredDotSize !=1)
                Assert.fail("It is displaying the dot of Student1 in the graph)");

            if(performanceReportInstructor.student_Name.size()!=1)
                Assert.fail("It is displaying Student1 name in the list)");

            productivityReport.productivityReport.click();//click on productivity report
            metaCognitiveReport.metaCognitiveReport.click();//click on metacobnitive report

            if(productivityReport.coloredMarker.size() !=1)
                Assert.fail("It is displaying the dot of Student1 in the graph)");

            if(performanceReportInstructor.student_Name.size()!=1)
                Assert.fail("It is displaying Student1 name in the list)");


        } catch (Exception e) {
            Assert.fail("Exception in test method instructorViewStudentDataWithoutResetProficiency of class InstructorResetStudentProficiencyData", e);
        }
    }


    @Test(priority = 3, enabled = true)
    public void studentStartAFreshInORIONAgain() {

        try {
            //tc row no 86
            Driver.startDriver();
            PerformanceReportInstructor performanceReportInstructor = PageFactory.initElements(Driver.driver, PerformanceReportInstructor.class);
            DashBoard dashBoard = PageFactory.initElements(Driver.driver,DashBoard.class);
            ClassSectionDropDown classSectionDropDown = PageFactory.initElements(Driver.driver,ClassSectionDropDown.class);
            MostChallengingActivities mostChallengingActivities = PageFactory.initElements(Driver.driver,MostChallengingActivities.class);
            ProductivityReport productivityReport = PageFactory.initElements(Driver.driver,ProductivityReport.class);
            MetaCognitiveReport metaCognitiveReport = PageFactory.initElements(Driver.driver,MetaCognitiveReport.class);
            AllActivity allActivity = PageFactory.initElements(Driver.driver,AllActivity.class);

            new LoginUsingLTI().ltiLogin("86"); // Login as Student
            new DiagnosticTest().startTest(0, 2);
            new DiagnosticTest().attemptAllCorrect(2,false,false);

            new LoginUsingLTI().ltiLogin("86_1"); // Login as Instructor
            performanceReportInstructor.viewClassReport.click();//click on view class report
            performanceReportInstructor.performanceReport.click();//click on performance Report
            performanceReportInstructor.checkBoxStudent.get(0).click();//uncheck  check box
            performanceReportInstructor.resetProficiency_button.click();//click on reset proficiency
            performanceReportInstructor.resetLink.click();//click on reset
            performanceReportInstructor.yesLink.click();//click on yes link

            new LoginUsingLTI().ltiLogin("86"); // Login as Student
            Assert.assertEquals(performanceReportInstructor.notificationStudent.getText(),"NOTICE: Your instructor has cleared all of your past question attempts and results, and reset your proficiency. Please contact your instructor with questions about this.","Robo notification is not displaying");
            Thread.sleep(10000);
            boolean elementFound1 = false;
            try {
                WebElement ele = performanceReportInstructor.notificationStudent;
                ele.isDisplayed();
                elementFound1 = true;
            } catch (Exception e) {
            }
            Assert.assertEquals(elementFound1, false, "after 10sec robo notification is still displaying");


            new LoginUsingLTI().ltiLogin("86"); // Login as Student
            new DiagnosticTest().startTest(0, 2);
            new DiagnosticTest().attemptAllCorrect(2,false,false);

            new LoginUsingLTI().ltiLogin("86_1"); // Login as Instructor
            performanceReportInstructor.viewClassReport.click();//click on view class report
            performanceReportInstructor.performanceReport.click();//click on performance Report
            performanceReportInstructor.checkBoxStudent.get(0).click();//uncheck  check box
            performanceReportInstructor.resetProficiency_button.click();//click on reset proficiency
            performanceReportInstructor.resetLink.click();//click on reset
            performanceReportInstructor.yesLink.click();//click on yes link

            new LoginUsingLTI().ltiLogin("86"); // Login as Student
            performanceReportInstructor.closeIcon.click();//click on close icon
            boolean elementFound2 = false;
            try {
                WebElement ele = performanceReportInstructor.notificationStudent;
                ele.isDisplayed();
                elementFound2 = true;
            } catch (Exception e) {
            }
            Assert.assertEquals(elementFound2, false, "after click on close icon robo notification is still displaying");


            new LoginUsingLTI().ltiLogin("86"); // Login as Student
            new DiagnosticTest().startTest(0, 2);
            new DiagnosticTest().attemptAllCorrect(2,false,false);

            new LoginUsingLTI().ltiLogin("86_1"); // Login as Instructor
            performanceReportInstructor.viewClassReport.click();//click on view class report
            performanceReportInstructor.performanceReport.click();//click on performance Report
            performanceReportInstructor.checkBoxStudent.get(0).click();//uncheck  check box
            performanceReportInstructor.resetProficiency_button.click();//click on reset proficiency
            performanceReportInstructor.resetLink.click();//click on reset
            performanceReportInstructor.yesLink.click();//click on yes link

            new LoginUsingLTI().ltiLogin("86"); // Login as Student
            Driver.driver.findElement(By.xpath("/html/body")).click();
            boolean elementFound5 = false;
            try {
                WebElement ele = performanceReportInstructor.notificationStudent;
                ele.isDisplayed();
                elementFound5 = true;
            } catch (Exception e) {
            }
            Assert.assertEquals(elementFound5, false, "after click on body notification is still displaying");
            Assert.assertEquals(performanceReportInstructor.beginDiagnostic.getAttribute("title"),"Begin Diagnostic", "Begin Diagnostic for all the chapters is not showing");
            Assert.assertEquals(dashBoard.leastProficientChapter.isDisplayed(), true, "Least Proficient Chapters\" is not reset to default screen.");
            classSectionDropDown.classSectionDropDownStudent.click();//click on class section dropdown
            classSectionDropDown.myReport.click();//click on my report
            Assert.assertEquals(mostChallengingActivities.mostChallengingActivityTitle.getText(), "Most Challenging Activities", "Most Challenging Activities title is not displaying");
            Assert.assertEquals(mostChallengingActivities.viewByChapter.getText(), "View By Chapters", "View By Chapters tab is not displaying");
            Assert.assertEquals(mostChallengingActivities.viewByObjectives.getText(), "View By Objectives", "View By Objectives tab is not displaying");
            Assert.assertEquals(mostChallengingActivities.noMostChallengingActivityData.get(1).getText(), "No most challenging activities data", "No most challenging activities data message is not displaying");
            mostChallengingActivities.viewByChapter.click();//click on view by objectives
            Assert.assertEquals(mostChallengingActivities.noMostChallengingActivityData.get(0).getText(), "No most challenging activities data", "No most challenging activities data message is not displaying");
            mostChallengingActivities.mostChallengingActivityDropDown.click();//click on most challenging activity dropdown
            productivityReport.productivityReport.click();//click on productivity report
            Assert.assertEquals(productivityReport.productivityReportTitle.getText(), "Productivity Report", "Productivity Report title is not displaying");
            Assert.assertEquals(productivityReport.noDataAvailableMessage.getText(), "No Data Available", "No Data Available message is not displaying");
            productivityReport.productivityReport.click();//click on productivity report
            metaCognitiveReport.metaCognitiveReport.click();//click on meta cognitive report from drop down
            Assert.assertEquals(metaCognitiveReport.metacognitiveReportTitle.getText(), "Metacognitive Report", "Metacognitive Report title is not displaying");
            Assert.assertEquals(metaCognitiveReport.noDataAvailableMessage.getText(), "No Data Available", "No Data Available message is not displaying");
            metaCognitiveReport.metaCognitiveReport.click();//click on metacognitive report
            performanceReportInstructor.performanceReport.click();//click on performance report
            Assert.assertEquals(performanceReportInstructor.notificationStudent.getText(), "Not enough data is available to show the performance report.", "robo notification is not displaying");
            classSectionDropDown.profileDropDown.click();//click on class section dropdown
            allActivity.allActivity.click();//click on all activity
            Assert.assertEquals(allActivity.notificationStudent.getText(), "No Activity Found.", "robo notification is not displaying");

            new LoginUsingLTI().ltiLogin("86"); // Login as Student
            new DiagnosticTest().startTest(0, 2);
            new DiagnosticTest().attemptAllCorrect(2,false,false);
            new ClickOnquestionCard().clickOnQuestion(0);//click on question  which is attempt last in daig
            new AddNotesInQuestions().addnotes("this is notes 1");//add note to above question
            classSectionDropDown.profileDropDown.click();//click on class section dropdown
            allActivity.allActivity.click();//click on all activity
            Assert.assertEquals(allActivity.allActivityTitle.getText(), "All Activity", "All Activity title is not displaying");
            allActivity.myJournal.click();//click on my journal
            Assert.assertEquals(allActivity.allActivityTitle.getText(), "All Activity", "All Activity title is not displaying");
            allActivity.myJournal.click();//click on my journal
            Assert.assertEquals(allActivity.myJournalTitle.getText(), "My Journal", "My Journal title is not displaying");

            new LoginUsingLTI().ltiLogin("86_1"); // Login as Instructor
            performanceReportInstructor.viewClassReport.click();//click on view class report
            performanceReportInstructor.performanceReport.click();//click on performance Report
            performanceReportInstructor.checkBoxStudent.get(0).click();//uncheck  check box
            performanceReportInstructor.resetProficiency_button.click();//click on reset proficiency
            performanceReportInstructor.resetLink.click();//click on reset
            performanceReportInstructor.yesLink.click();//click on yes link

            new LoginUsingLTI().ltiLogin("86"); // Login as Student
            classSectionDropDown.profileDropDown.click();//click on class section dropdown
            allActivity.allActivity.click();//click on all activity

            boolean elementFound4 = false;
            try {
                WebElement ele = Driver.driver.findElement(By.xpath("//li[@data-side='right']"));
                ele.isDisplayed();
                elementFound4 = true;
            } catch (Exception e) {
            }
            Assert.assertEquals(elementFound4, false, "All the cards related to Practice and Diagnostic is not removed.");



        } catch (Exception e) {
            Assert.fail("Exception in test method studentStartAFreshInORIONAgain of class InstructorResetStudentProficiencyData", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void impactedAreasOne() {

        try {
            //tc row no 113
            Driver.startDriver();
            PerformanceReportInstructor performanceReportInstructor = PageFactory.initElements(Driver.driver, PerformanceReportInstructor.class);

            new LoginUsingLTI().ltiLogin("113_1"); // Login as Student
            new DiagnosticTest().startTest(0, 2);
            new DiagnosticTest().attemptAllCorrect(2,false,false);

            new LoginUsingLTI().ltiLogin("113"); // Login as Instructor
            performanceReportInstructor.viewClassReport.click();//click on view class report
            performanceReportInstructor.performanceReport.click();//click on performance Report
            performanceReportInstructor.checkBoxStudent.get(0).click();//uncheck  check box
            performanceReportInstructor.resetProficiency_button.click();//click on reset proficiency
            performanceReportInstructor.resetLink.click();//click on reset
            performanceReportInstructor.yesLink.click();//click on yes link

            new LoginUsingLTI().ltiLogin("113_1"); // Login as Student
            new DiagnosticTest().startTest(0, 2);
            new DiagnosticTest().attemptAllCorrect(2,false,false);

            new LoginUsingLTI().ltiLogin("113"); // Login as Instructor
            performanceReportInstructor.viewClassReport.click();//click on view class report
            performanceReportInstructor.performanceReport.click();//click on performance Report
            performanceReportInstructor.checkBoxStudent.get(0).click();//uncheck  check box
            performanceReportInstructor.resetProficiency_button.click();//click on reset proficiency
            performanceReportInstructor.resetLink.click();//click on reset
            performanceReportInstructor.yesLink.click();//click on yes link

            new LoginUsingLTI().ltiLogin("113_2"); // Login as Student
            new DiagnosticTest().startTest(0, 2);
            new DiagnosticTest().attemptAllCorrect(2,false,false);


        } catch (Exception e) {
            Assert.fail("Exception in test method impactedAreasOne of class InstructorResetStudentProficiencyData", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void impactedAreasTwo() {

        try {
            //tc row no 129
            Driver.startDriver();
            PerformanceReportInstructor performanceReportInstructor = PageFactory.initElements(Driver.driver, PerformanceReportInstructor.class);
            String context_title = ReadTestData.readDataByTagName("", "context_title", "129_2");
            System.out.println("context_title"+context_title);
            String context_title1 = ReadTestData.readDataByTagName("", "context_title", "129");
            ClassSectionDropDown classSectionDropDown = PageFactory.initElements(Driver.driver,ClassSectionDropDown.class);

            new LoginUsingLTI().ltiLogin("129_4"); // Login as Student
            new DiagnosticTest().startTest(0, 2);
            new DiagnosticTest().attemptAllCorrect(2,false,false);

            new LoginUsingLTI().ltiLogin("129"); // Login as instructor active
            new LoginUsingLTI().ltiLogin("129_1"); // Login as instructor active
            new LoginUsingLTI().ltiLogin("129_2"); // Login as instructor inactive
            new LoginUsingLTI().ltiLogin("129_3"); // Login as instructor finished

            new LoginUsingLTI().ltiLogin("129_2"); // Login as instructor inactive
            Assert.assertEquals(classSectionDropDown.defaultClassSectionName.getText(), context_title, "instructor not login to appropriate class section");
            classSectionDropDown.defaultClassSectionName.click();//click on default class section
            classSectionDropDown.active_tab.click();//click on active tab
            classSectionDropDown.listDropDown.get(3).click();//click on active class section
            Thread.sleep(1000);
            Assert.assertEquals(classSectionDropDown.defaultClassSectionName.getText(), context_title1, "instructor not login to appropriate class section");
            performanceReportInstructor.viewClassReport.click();//click on view class report
            performanceReportInstructor.performanceReport.click();//click on performance Report
            Assert.assertEquals(performanceReportInstructor.performanceReport.getText(), "Performance Report", "Performance report page is not displaying");
            performanceReportInstructor.checkBoxStudent.get(0).click();//uncheck  check box
            performanceReportInstructor.resetProficiency_button.click();//click on reset proficiency
            performanceReportInstructor.resetLink.click();//click on reset
            performanceReportInstructor.yesLink.click();//click on yes link

            new LoginUsingLTI().ltiLogin("129_4"); // Login as Student
            Assert.assertEquals(performanceReportInstructor.notificationStudent.getText(),"NOTICE: Your instructor has cleared all of your past question attempts and results, and reset your proficiency. Please contact your instructor with questions about this.","Robo notification is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in test method impactedAreasTwo of class InstructorResetStudentProficiencyData", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void impactedAreasFour() {

        try {
            //tc row no 172
            Driver.startDriver();
            PerformanceReportInstructor performanceReportInstructor;
            ClassSectionDropDown classSectionDropDown;

            new LoginUsingLTI().ltiLogin("172_1"); // Login as Student
            new DiagnosticTest().startTest(0, 2);
            new DiagnosticTest().attemptAllCorrect(2,false,false);

            new PracticeTest().startTest();
            new PracticeTest().attemptPracticeTestRandomly(3);

            new RunScheduledJobs().runScheduledJob("ClassSectionActivityJob");

            WebDriver firefoxDriver=new FirefoxDriver();
            new LoginUsingLTI().ltiLogin("172",firefoxDriver); // Login as instructor
            classSectionDropDown = PageFactory.initElements(Driver.driver,ClassSectionDropDown.class);
            performanceReportInstructor = PageFactory.initElements(firefoxDriver, PerformanceReportInstructor.class);

            performanceReportInstructor.viewClassReport.click();//click on view class report
            performanceReportInstructor.performanceReport.click();//click on performance Report
            Assert.assertEquals(performanceReportInstructor.performanceReport.getText(), "Performance Report", "Performance report page is not displaying");
            performanceReportInstructor.checkBoxStudent.get(0).click();//uncheck  check box
            performanceReportInstructor.resetProficiency_button.click();//click on reset proficiency
            performanceReportInstructor.resetLink.click();//click on reset
            performanceReportInstructor.yesLink.click();//click on yes link

            new LoginUsingLTI().ltiLogin("172_1"); // Login as Student
            //Assert.assertEquals(performanceReportInstructor.notificationStudent.getText(),"NOTICE: Your instructor has cleared all of your past question attempts and results, and reset your proficiency. Please contact your instructor with questions about this.","Robo notification is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in test method impactedAreasFour of class InstructorResetStudentProficiencyData", e);
        }
    }
}