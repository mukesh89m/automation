package com.snapwiz.selenium.tests.staf.orion.testcases.IT24.R243;

import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DiagnosticTest;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.PracticeTest;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.Dashboard.DashBoard;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.reports.MetaCognitiveReport;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.reports.MostChallengingActivities;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.reports.PerformanceReportInstructor;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.reports.ProductivityReport;
import com.snapwiz.selenium.tests.staf.orion.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.orion.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Dharaneesha on 11/4/15.
 */
public class AssignmentFlow{



    @Test(priority = 1,enabled = true)
    public void popUpVerificationThroughNormalLogin()
    {
        //Pre Condition: Log in as a normal LTI student(Without Assigning diag Chapters) & attempt any chapters diagTest

        //Scenario1:Log in as a same LTI student(with Assigning diag Chapters), attempt 2nd Assigned Chapters and validate the smaller popup as per expected in testcase document
        //Scenario2: Then Attempt 3rd Assigned Chapter & validate the smaller pop up as per expected in testcase document

        try {
            Driver.startDriver();
            PerformanceReportInstructor performanceReportInstructor = PageFactory.initElements(Driver.driver, PerformanceReportInstructor.class);
            DashBoard dashBoard = PageFactory.initElements(Driver.driver, DashBoard.class);

            new LoginUsingLTI().ltiLogin("10"); // Login as Student
            new DiagnosticTest().startTest(0, 3);
            new DiagnosticTest().attemptDiagnosticTestRandomly();
            Thread.sleep(2000);

            new LoginUsingLTI().ltiLogin("11"); // Login as Student by assigning diag Chapters
            //1. Student Dashboard should be loaded.
            Assert.assertEquals(dashBoard.getHeaderLogo().isDisplayed(),true,"Student Dashboard should be loaded.");

            //2. The chapter number passed in custom_lis_result_source_id should be highlighted in red on the student Dashboard page.
            int size = Driver.driver.findElements(By.cssSelector("div[class='al-content-body-wrapper-disabled  lti-chapter-red-outline']")).size();
            if(size!=2){
                Assert.fail("The chapter number passed in custom_lis_result_source_id should be highlighted in red on the student Dashboard page.");
            }

            new DiagnosticTest().startTest(0, 3);
            new DiagnosticTest().attemptDiagnosticTestRandomly();
            validateSmallerNewPopUp();

            /*Row No - 18 : "7. Login as user student through LTI form.
            8. Attempt any assigned chapter's Diagnostic and complete it.
            9. When the robo pop-up is open in Performance reports page, access the reports page."*/
            //Expected - 1. The robo pop-up should be closed.

            new LoginUsingLTI().ltiLogin("11"); // Login as Student by assigning diag Chapters
            new DiagnosticTest().startTest(0, 3);
            new DiagnosticTest().attemptDiagnosticTestRandomly();
            Thread.sleep(2000);
            validateSmallerNewPopUp();
            dashBoard.getHeaderLogo().click();
            if(Driver.driver.findElements(By.className("al-notification-message-wrapper")).size()!=0){
                Assert.fail("The robo pop-up should be closed.");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testmethod 'popUpVerificationThroughNormalLogin' of class AssignmentFlow", e);
        }
    }



    @Test(priority = 2,enabled = true)
    public void popUpVerificationWhenStudentNotAttendingDiagTest()
    {

        try {
            Driver.startDriver();
            PerformanceReportInstructor performanceReportInstructor = PageFactory.initElements(Driver.driver, PerformanceReportInstructor.class);
            DashBoard dashBoard = PageFactory.initElements(Driver.driver, DashBoard.class);

            new LoginUsingLTI().ltiLogin("19"); // Login as Student by assigning diag Chapters
            //1. Student Dashboard should be loaded.
            Assert.assertEquals(dashBoard.getHeaderLogo().isDisplayed(), true, "Student Dashboard should be loaded.");

            //2. The chapter number passed in custom_lis_result_source_id should be highlighted in red on the student Dashboard page.
            int size = Driver.driver.findElements(By.cssSelector("div[class='al-content-body-wrapper-disabled  lti-chapter-red-outline']")).size();
            if(size!=2){
                Assert.fail("The chapter number passed in custom_lis_result_source_id should be highlighted in red on the student Dashboard page.");
            }

            new DiagnosticTest().startTest(0, 3);
            new DiagnosticTest().attemptDiagnosticTestRandomly();
            validateBiggerNewPopUp();

            /*Row No - 18 : "7. Login as user student through LTI form.
            8. Attempt any assigned chapter's Diagnostic and complete it.
            9. When the robo pop-up is open in Performance reports page, access the reports page."*/
            //Expected - 1. The robo pop-up should be closed.

            new LoginUsingLTI().ltiLogin("19"); // Login as Student by assigning diag Chapters
            new DiagnosticTest().startTest(0, 3);
            new DiagnosticTest().attemptDiagnosticTestRandomly();
            Thread.sleep(2000);
            validateSmallerNewPopUp();
            dashBoard.getHeaderLogo().click();
            if(Driver.driver.findElements(By.className("al-notification-message-wrapper")).size()!=0){
                Assert.fail("The robo pop-up should be closed.");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testmethod 'popUpVerificationWhenStudentNotAttendingDiagTest' of class AssignmentFlow", e);
        }
    }



    @Test(priority = 3,enabled = true)
    public void popUpVerificationForInprogressAssessment()
    {

        try {
            Driver.startDriver();
            PerformanceReportInstructor performanceReportInstructor = PageFactory.initElements(Driver.driver, PerformanceReportInstructor.class);
            DashBoard dashBoard = PageFactory.initElements(Driver.driver, DashBoard.class);

            new LoginUsingLTI().ltiLogin("29"); // Login as Student by assigning diag Chapters
            new DiagnosticTest().startTest(1, 3);
            new DiagnosticTest().attemptDiagnosticTestRandomly(5);

            new LoginUsingLTI().ltiLogin("29"); // Login as Student by assigning diag Chapters
            //1. Student Dashboard should be loaded.
            Assert.assertEquals(dashBoard.getHeaderLogo().isDisplayed(),true,"Student Dashboard should be loaded.");

            new DiagnosticTest().startTest(0, 3);
            new DiagnosticTest().attemptDiagnosticTestRandomly();
            validateBiggerNewPopUpForUnAssignedChapters();

            /*Row No - 29"1. Login as user student through LTI Login.
            2. Click on 'Continue' button of the particular chapter from Dashboard.
            3. Complete the Diagnostic."*/
            new LoginUsingLTI().ltiLogin("29"); // Login as Student by assigning diag Chapters
            new DiagnosticTest().continueDiagnosticTest(0, 3);
            new DiagnosticTest().attemptDiagnosticTestRandomly();
            Thread.sleep(2000);
            validateSmallerNewPopUp();
            dashBoard.getHeaderLogo().click();
            if(Driver.driver.findElements(By.className("al-notification-message-wrapper")).size()!=0){
                Assert.fail("The robo pop-up should be closed.");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testmethod 'popUpVerificationForInprogressAssessment' of class AssignmentFlow", e);
        }
    }




    @Test(priority = 4,enabled = true)
    public void popUpContentVerificationForUnAssignedChapterDiag()
    {
        //Row No - 36

        try {
            Driver.startDriver();
            PerformanceReportInstructor performanceReportInstructor = PageFactory.initElements(Driver.driver, PerformanceReportInstructor.class);
            DashBoard dashBoard = PageFactory.initElements(Driver.driver, DashBoard.class);


            new LoginUsingLTI().ltiLogin("36"); // Login as Student by assigning diag Chapters
            //1. Student Dashboard should be loaded.
            Assert.assertEquals(dashBoard.getHeaderLogo().isDisplayed(), true, "Student Dashboard should be loaded.");
            new DiagnosticTest().startTest(0, 3);
            new DiagnosticTest().attemptDiagnosticTestRandomly();
            validateBiggerNewPopUpForUnAssignedChapters();


        }
        catch (Exception e)
        {
            Assert.fail("Exception in test method 'popUpContentVerificationForUnAssignedChapterDiag' of class AssignmentFlow", e);
        }
    }




    @Test(priority = 5,enabled = true)
    public void popUpContentVerificationForSnapwizChapter()
    {
        //Row No - 40

        try {
            Driver.startDriver();
            PerformanceReportInstructor performanceReportInstructor = PageFactory.initElements(Driver.driver, PerformanceReportInstructor.class);
            DashBoard dashBoard = PageFactory.initElements(Driver.driver, DashBoard.class);


            new LoginUsingLTI().ltiLogin("40"); // Login as Student by assigning diag Chapters
            //1. Student Dashboard should be loaded.
            Assert.assertEquals(dashBoard.getHeaderLogo().isDisplayed(), true, "Student Dashboard should be loaded.");

            //2. The chapter number passed in custom_lis_result_source_id should be highlighted in blue on the student Dashboard page.
            Thread.sleep(2000);
            String rightBorderColor = Driver.driver.findElement(By.cssSelector("div[class='al-content-body-wrapper-disabled  ']")).getCssValue("border-right-color");

            if(rightBorderColor.equals("#449BD8")){
                Assert.fail("2. The chapter number passed in custom_lis_result_source_id should be highlighted in blue on the student Dashboard page.");
            }

            new DiagnosticTest().startTest(0, 3);
            new DiagnosticTest().attemptDiagnosticTestRandomly();
            validateBiggerNewPopUpForUnAssignedChapters();
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test method 'popUpContentVerificationForUnAssignedChapterDiag' of class AssignmentFlow", e);
        }
    }



    @Test(priority = 6,enabled = true)
    public void popUpContentVerificationForAssignedPracticeTestQuit()
    {
        //Row No - 50

        try {

            Driver.startDriver();
            DashBoard dashboard=PageFactory.initElements(Driver.driver,DashBoard.class);
            PerformanceReportInstructor performanceReportInstructor = PageFactory.initElements(Driver.driver, PerformanceReportInstructor.class);
            DashBoard dashBoard = PageFactory.initElements(Driver.driver, DashBoard.class);
            MetaCognitiveReport metacognitivereports= PageFactory.initElements(Driver.driver,MetaCognitiveReport.class);


            new LoginUsingLTI().ltiLogin("52"); // Login as Student by assigning diag Chapters
            new DiagnosticTest().startTest(0, 3);
            new DiagnosticTest().attemptDiagnosticTestRandomly();

            new LoginUsingLTI().ltiLogin("52"); // Login as Student by assigning diag Chapters
            new PracticeTest().startTest();
            new PracticeTest().attemptPracticeTestRandomly(10);

            performanceReportInstructor.notificationPerformanceReportView.click();// click on"No, Quit practising now and view Performance report" link.


            boolean elementFound = true;// verify pop up should not appear


            try {
                elementFound = Driver.driver.findElement(By.className("al-notification-message-header")).isDisplayed();

            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound, false, "pop is displayed in practice, its not working fine");


            dashBoard.orionDashBoardIcon.click();

            new PracticeTest().startTest();// Quit pratice and check whether pop up appears

            for(int i=1;i<=3;i++) {
                new PracticeTest().AttemptCorrectAnswer(2);
            }
            new PracticeTest().quitTestAndGoToReport();



            boolean elementFound1 = true;// verify pop up should not appear


            try {
                elementFound1 = Driver.driver.findElement(By.className("al-notification-message-header")).isDisplayed();

            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound1, false, "pop is displayed in practice, its not working fine");




        }
        catch (Exception e)
        {
            Assert.fail("Exception in test method 'popUpContentVerificationForAssignedPracticeTestQuit' of class AssignmentFlow", e);
        }
    }



    @Test(priority = 7,enabled = true)
    public void popUpContentVerificationForAssignedPracticeTestMetacognitiveReports()
    {

        try {
            Driver.startDriver();
            ProductivityReport productivityReport=PageFactory.initElements(Driver.driver,ProductivityReport.class);
            MetaCognitiveReport metaCognitiveReport=PageFactory.initElements(Driver.driver,MetaCognitiveReport.class);
            PerformanceReportInstructor performanceReportInstructor = PageFactory.initElements(Driver.driver, PerformanceReportInstructor.class);
            DashBoard dashBoard = PageFactory.initElements(Driver.driver, DashBoard.class);

            new LoginUsingLTI().ltiLogin("54"); // Login as Student by assigning diag Chapters

            new DiagnosticTest().startTest(0, 3);// verify pop up from metacognitive when clicked on View report after 10 questions

            new DiagnosticTest().attemptDiagnosticTestRandomly();
            Driver.driver.findElement(By.xpath("/html/body")).click();

            performanceReportInstructor.performanceReport.click();
            metaCognitiveReport.metaCognitiveReport.click();
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(metaCognitiveReport.coloredMarker);
            metaCognitiveReport.praticeFromMetacognitive.click();
            new PracticeTest().attemptPracticeTestRandomly(10);

            performanceReportInstructor.notificationPerformanceReportView.click();// click on"No, Quit practising now and view Performance report" link.


            boolean elementFound = true;// verify pop up should not appear


            try {
                elementFound = Driver.driver.findElement(By.className("al-notification-message-header")).isDisplayed();

            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound, false, "pop is displayed in practice, its not working fine");


            performanceReportInstructor.performanceReport.click();// verify pop up from metacognitive when clicked on View report at any point
            metaCognitiveReport.metaCognitiveReport.click();
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(metaCognitiveReport.coloredMarker);
            metaCognitiveReport.praticeFromMetacognitive.click();

            for(int i=1;i<=3;i++) {
                new PracticeTest().AttemptCorrectAnswer(2);
            }
            new PracticeTest().quitTestAndGoToReport();



            boolean elementFound1 = true;// verify pop up should not appear


            try {
                elementFound1 = Driver.driver.findElement(By.className("al-notification-message-header")).isDisplayed();

            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound1, false, "pop is displayed in practice, its not working fine");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in test method 'popUpContentVerificationForAssignedPracticeTestMetacognitiveReports' of class AssignmentFlow", e);
        }
    }
    @Test(priority = 8,enabled = true)
    public void popUpContentVerificationForAssignedPracticeTestMostChallengingActivitiesReports()
    {

        try {
            Driver.startDriver();
            ProductivityReport productivityReport=PageFactory.initElements(Driver.driver,ProductivityReport.class);
            MetaCognitiveReport metaCognitiveReport=PageFactory.initElements(Driver.driver,MetaCognitiveReport.class);
            MostChallengingActivities mostChallengingActivities=PageFactory.initElements(Driver.driver,MostChallengingActivities.class);

            PerformanceReportInstructor performanceReportInstructor = PageFactory.initElements(Driver.driver, PerformanceReportInstructor.class);
            DashBoard dashBoard = PageFactory.initElements(Driver.driver, DashBoard.class);



            new LoginUsingLTI().ltiLogin("58"); // Login as Student by assigning diag Chapters

           new DiagnosticTest().startTest(0, 3);// verify pop up from metacognitive when clicked on View report after 10 questions

            new DiagnosticTest().attemptDiagnosticTestRandomly();
            Driver.driver.findElement(By.xpath("/html/body")).click();// body click action to close pop up

            performanceReportInstructor.performanceReport.click();
            mostChallengingActivities.mostChallengingActivityDropDown.click();//navigate to most challenging reports
            Thread.sleep(1000);
            mostChallengingActivities.viewByChapter.click();// click on View by chapter tab
            new MouseHover().mouserhoverbywebelement(mostChallengingActivities.viewByChapterHover.get(0));// mouse hover on the chapter entry
            mostChallengingActivities.hoverAndClickOnPractice.get(0).click();
            new PracticeTest().attemptPracticeTestRandomly(10);//attempt practice

            performanceReportInstructor.notificationPerformanceReportView.click();// click on"No, Quit practising now and view Performance report" link.


            boolean elementFound = true;// verify pop up should not appear


            try {
                elementFound = Driver.driver.findElement(By.className("al-notification-message-header")).isDisplayed();

            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound, false, "pop is displayed in practice, its not working fine");

            // verification of the pop-up when quit practice at any point
            performanceReportInstructor.performanceReport.click();
            mostChallengingActivities.mostChallengingActivityDropDown.click();//navigate to most challenging reports
            Thread.sleep(1000);
            mostChallengingActivities.viewByChapter.click();// click on View by chapter tab
            new MouseHover().mouserhoverbywebelement(mostChallengingActivities.viewByChapterHover.get(0));// mouse hover on the chapter entry
            mostChallengingActivities.hoverAndClickOnPractice.get(0).click();

            for(int i=1;i<=3;i++) {//attempt practice
                new PracticeTest().AttemptCorrectAnswer(2);
            }
            new PracticeTest().quitTestAndGoToReport();



            boolean elementFound1 = true;// verify pop up should not appear


            try {
                elementFound1 = Driver.driver.findElement(By.className("al-notification-message-header")).isDisplayed();

            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound1, false, "pop is displayed in practice, its not working fine");

            // check all the above test cases when navigated through 'View by Objectives tab'


            performanceReportInstructor.performanceReport.click();
            mostChallengingActivities.mostChallengingActivityDropDown.click();//navigate to most challenging reports
            Thread.sleep(1000);


            // click on View by chapter tab
            new MouseHover().mouserhoverbywebelement(mostChallengingActivities.viewByChapterHover.get(1));// mouse hover on the chapter entry
            mostChallengingActivities.hoverAndClickOnPractice.get(1).click();

            for(int i=1;i<=3;i++) {//attempt practice
                new PracticeTest().AttemptCorrectAnswer(2);
            }
            new PracticeTest().quitTestAndGoToReport();
            boolean elementFound2 = true;// verify pop up should not appear


            try {
                elementFound2 = Driver.driver.findElement(By.className("al-notification-message-header")).isDisplayed();

            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound2, false, "pop is displayed in practice, its not working fine");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in test method 'popUpContentVerificationForAssignedPracticeTestMostChallengingActivitiesReports' of class AssignmentFlow", e);
        }
    }

    @Test(priority = 9,enabled = true)
    public void popUpContentVerificationForAssignedPracticeTestProductivityReports()
    {

        try {
            Driver.startDriver();
            ProductivityReport productivityReport=PageFactory.initElements(Driver.driver,ProductivityReport.class);
            MetaCognitiveReport metaCognitiveReport=PageFactory.initElements(Driver.driver,MetaCognitiveReport.class);

            PerformanceReportInstructor performanceReportInstructor = PageFactory.initElements(Driver.driver, PerformanceReportInstructor.class);
            DashBoard dashBoard = PageFactory.initElements(Driver.driver, DashBoard.class);



            new LoginUsingLTI().ltiLogin("62"); // Login as Student by assigning diag Chapters

            new DiagnosticTest().startTest(0, 3);// verify pop up from metacognitive when clicked on View report after 10 questions

            new DiagnosticTest().attemptDiagnosticTestRandomly();
            Driver.driver.findElement(By.xpath("/html/body")).click();

            performanceReportInstructor.performanceReport.click();
            productivityReport.productivityReport.click();
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(metaCognitiveReport.coloredMarker);
            productivityReport.praticeFromProductivity.click();
            new PracticeTest().attemptPracticeTestRandomly(10);

            performanceReportInstructor.notificationPerformanceReportView.click();// click on"No, Quit practising now and view Performance report" link.


            boolean elementFound = true;// verify pop up should not appear


            try {
                elementFound = Driver.driver.findElement(By.className("al-notification-message-header")).isDisplayed();

            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound, false, "pop is displayed in practice, its not working fine");


            performanceReportInstructor.performanceReport.click();// verify pop up from metacognitive when clicked on View report at any point
            productivityReport.productivityReport.click();
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(metaCognitiveReport.coloredMarker);
            productivityReport.praticeFromProductivity.click();

            for(int i=1;i<=3;i++) {
                new PracticeTest().AttemptCorrectAnswer(2);
            }
            new PracticeTest().quitTestAndGoToReport();



            boolean elementFound1 = true;// verify pop up should not appear


            try {
                elementFound1 = Driver.driver.findElement(By.className("al-notification-message-header")).isDisplayed();

            }
            catch (Exception e){
                //empty catch block
            }
            Assert.assertEquals(elementFound1, false, "pop is displayed in practice, its not working fine");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in test method 'popUpContentVerificationForAssignedPracticeTestProductivityReports' of class AssignmentFlow", e);
        }
    }


    public void validateSmallerNewPopUp(){
        try{
            PerformanceReportInstructor performanceReportInstructor = PageFactory.initElements(Driver.driver, PerformanceReportInstructor.class);


            //1. A Performance report should be generated.
            Assert.assertEquals(performanceReportInstructor.label_chapterPerformanceSummary.getText(),"Chapter Performance Summary","1. A Performance report should be generated.");


            //2. A robo pop-up should appear.

            Assert.assertEquals(performanceReportInstructor.notificationPopUp.isDisplayed(),true,"A robo pop-up should appear.");

            //2a. Header of the robo pop-up should contain 'Information' text with 'i' icon.

            Assert.assertEquals(performanceReportInstructor.labelInformation.isDisplayed(),true,"Header of the robo pop-up should contain 'Information' text");
            Assert.assertEquals(performanceReportInstructor.iconI.isDisplayed(),true,"Header of the robo pop-up should contain 'Information' text with 'i' icon.");


            //3. Robo pop-up should contain following text “You have completed your diagnostic for this chapter! To check how many questions you have completed towards this assignment, return to WileyPLUS to see your status.”
            Assert.assertEquals(performanceReportInstructor.notification.getText(),"You have completed your diagnostic for this chapter!\n" +
                    "To check how many questions you have completed towards this assignment, return to WileyPLUS to see your status.","Robo pop-up should contain following text “You have completed your diagnostic for this chapter! To check how many questions you have completed towards this assignment, return to WileyPLUS to see your status.”");


            //2b. Header of the robo pop-up should contain 'close' icon.
            //6. Click on the close icon in the robo pop-up.
            //1. The robo pop-up should be closed.
            performanceReportInstructor.closeIcon.click();
            Thread.sleep(9000);

            if(Driver.driver.findElements(By.className("al-notification-message-wrapper")).size()!=0){
                Assert.fail("The robo pop-up should be closed.");
            }

        }catch(Exception e){
            Assert.fail("Exception in method 'validateSmallerNewPopUp' in class 'AssignmentFlow'",e);
        }
    }




    public void validateBiggerNewPopUp(){
        try{
            PerformanceReportInstructor performanceReportInstructor = PageFactory.initElements(Driver.driver, PerformanceReportInstructor.class);

            //1. A Performance report should be generated.
            Assert.assertEquals(performanceReportInstructor.label_chapterPerformanceSummary.getText(),"Chapter Performance Summary","1. A Performance report should be generated.");


            //2. A robo pop-up should appear.

            Assert.assertEquals(performanceReportInstructor.notificationPopUp.isDisplayed(),true,"A robo pop-up should appear.");

            //2a. Header of the robo pop-up should contain 'Information' text with 'i' icon.

            Assert.assertEquals(performanceReportInstructor.labelInformation.isDisplayed(),true,"Header of the robo pop-up should contain 'Information' text");
            Assert.assertEquals(performanceReportInstructor.iconI.isDisplayed(),true,"Header of the robo pop-up should contain 'Information' text with 'i' icon.");

            //3. Robo pop-up should contain following text “You have completed your diagnostic for this chapter! To check how many questions you have completed towards this assignment, return to WileyPLUS to see your status.”
            Assert.assertEquals(performanceReportInstructor.notification.getText(),"This Activity Report shows you how you did on your Diagnostic or Practice questions. The graphs will help you learn more about how you are doing on the chapter objectives. Use the filtering tools to the right to view your activity for other chapters or to find questions that you want to review. You can go back to your ORION Dashboard at any time by clicking on the ORION Star at the top of every page.\n" +
                    "To check how many questions you have completed towards this assignment, return to WileyPLUS to see your status.","Robo pop-up should contain following text “You have completed your diagnostic for this chapter! To check how many questions you have completed towards this assignment, return to WileyPLUS to see your status.”");



            //4. The robo notification should contain "Dashboard" icon.
            Assert.assertEquals(performanceReportInstructor.startIcon.isDisplayed(), true, "The robo notification should contain \"Dashboard\" icon.");


            http://10.0.0.29:8080
            if(!performanceReportInstructor.startIcon.getAttribute("src").equals(Config.baseURL + "/webresources/images/al/orion-icon.png")){
                Assert.fail("The robo notification should contain \"Dashboard\" icon.");
            }


            //Assert.assertEquals(performanceReportInstructor.helpLink.isDisplayed(),true,"Help link is not available in the bigger popup");


            //2b. Header of the robo pop-up should contain 'close' icon.
            //6. Click on the close icon in the robo pop-up.
            //1. The robo pop-up should be closed.
            performanceReportInstructor.closeIcon.click();
            Thread.sleep(9000);

            if(Driver.driver.findElements(By.className("al-notification-message-wrapper")).size()!=0){
                Assert.fail("The robo pop-up should be closed.");
            }

        }catch(Exception e){
            Assert.fail("Exception in method 'validateBiggerNewPopUp' in class 'AssignmentFlow'",e);
        }
    }


    public void validateBiggerNewPopUpForUnAssignedChapters(){
        try{
            PerformanceReportInstructor performanceReportInstructor = PageFactory.initElements(Driver.driver, PerformanceReportInstructor.class);

            //1. A Performance report should be generated.
            Assert.assertEquals(performanceReportInstructor.label_chapterPerformanceSummary.getText(),"Chapter Performance Summary","1. A Performance report should be generated.");


            //2. A robo pop-up should appear.

            Assert.assertEquals(performanceReportInstructor.notificationPopUp.isDisplayed(),true,"A robo pop-up should appear.");

            //2a. Header of the robo pop-up should contain 'Information' text with 'i' icon.

            Assert.assertEquals(performanceReportInstructor.labelInformation.isDisplayed(),true,"Header of the robo pop-up should contain 'Information' text");
            Assert.assertEquals(performanceReportInstructor.iconI.isDisplayed(),true,"Header of the robo pop-up should contain 'Information' text with 'i' icon.");

            //3. Robo pop-up should contain following text “You have completed your diagnostic for this chapter! To check how many questions you have completed towards this assignment, return to WileyPLUS to see your status.”
            Assert.assertEquals(performanceReportInstructor.notification.getText(),"This Activity Report shows you how you did on your Diagnostic or Practice questions. The graphs will help you learn more about how you are doing on the chapter objectives. Use the filtering tools to the right to view your activity for other chapters or to find questions that you want to review. You can go back to your ORION Dashboard at any time by clicking on the ORION Star at the top of every page.\n" +
                    "\n" +
                    "\n" +
                    "For more information, please see the help page for this report.","Robo pop-up should contain following text “You have completed your diagnostic for this chapter! To check how many questions you have completed towards this assignment, return to WileyPLUS to see your status.”");



            //4. The robo notification should contain "Dashboard" icon.
            Assert.assertEquals(performanceReportInstructor.startIcon.isDisplayed(), true, "The robo notification should contain \"Dashboard\" icon.");



            if(!performanceReportInstructor.startIcon.getAttribute("src").equals(Config.baseURL + "/webresources/images/al/orion-icon.png")){
                Assert.fail("The robo notification should contain \"Dashboard\" icon.");
            }




            Assert.assertEquals(performanceReportInstructor.helpLink.isDisplayed(),true,"Help link is not available in the bigger popup");


            //2b. Header of the robo pop-up should contain 'close' icon.
            //6. Click on the close icon in the robo pop-up.
            //1. The robo pop-up should be closed.
            performanceReportInstructor.closeIcon.click();
            Thread.sleep(9000);

            if(Driver.driver.findElements(By.className("al-notification-message-wrapper")).size()!=0){
                Assert.fail("The robo pop-up should be closed.");
            }

        }catch(Exception e){
            Assert.fail("Exception in method 'validateBiggerNewPopUpForUnAssignedChapters' in class 'AssignmentFlow'",e);
        }
    }


}
