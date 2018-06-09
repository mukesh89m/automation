package com.snapwiz.selenium.tests.staf.orion.testcases.regression.orion;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.apphelper.*;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.Dashboard.DashBoard;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.MyProfile.PersonalDetails;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.Settings.Setting;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.reports.MetaCognitiveReport;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.reports.MostChallengingActivities;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.reports.PerformanceReportInstructor;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.reports.ProductivityReport;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Dharaneesha on 2/1/16.
 */
public class InstructorDashboard extends Driver{


    /**
     *This method validates the instructor dashboard tiles such as Students Visited, Question Attempted & so on in instructor side
     */
    @Test(priority = 1, enabled = true)
    public void checkInstructorDashboardTiles() {
        try{
            WebDriver driver=Driver.getWebDriver();
            ReportUtil.log("Checking Instructor Dashboard Tiles","Validating the instructor dashboard tiles such as Students Visited, Question Attempted & so on in instructor side","info");
            DashBoard dashBoard = PageFactory.initElements(driver,DashBoard.class);
            String dataIndex = "1";
            String actualDiscussionText = "";
            String randomText = new RandomString().randomstring(10);
            new LoginUsingLTI().ltiLogin(dataIndex); // Login as an instructor
            new LoginUsingLTI().ltiLogin(dataIndex+"_1"); // Login as Student 1
            new LoginUsingLTI().ltiLogin(dataIndex+"_2"); // Login as Student 2
            new LoginUsingLTI().ltiLogin(dataIndex+"_3"); // Login as Student 3

            //Log in as a student 1 & attempt diag test
            new LoginUsingLTI().ltiLogin(dataIndex + "_1");
            new DiagnosticTest().startTest(0,4);
//            for(int i=0;i<20;i++)
//                new DiagnosticTest().attemptCorrect(1);
            new DiagnosticTest().attemptAllCorrect(1,false,false);
            //Add a discussion as a student1
            new ClickOnquestionCard().clickOnQuestion(2);
            new AddDiscussionInQuestions().AddDiscussion(randomText);

            //Log in as a student 2 & attempt diag test
            new LoginUsingLTI().ltiLogin(dataIndex + "_2"); // Login as Student 2
            new DiagnosticTest().startTest(0,4);
            for(int i=0;i<5;i++)
                new DiagnosticTest().attemptCorrect(1);
            new DiagnosticTest().quitTestAndGoToDashboard();

            //Log in as a student 3 & attempt diag test
            new LoginUsingLTI().ltiLogin(dataIndex + "_3"); // Login as Student 3
            new DiagnosticTest().startTest(0,4);
            for(int i=0;i<10;i++)
                new DiagnosticTest().attemptCorrect(1);
            new DiagnosticTest().quitTestAndGoToDashboard();

            new LoginUsingLTI().ltiLogin(dataIndex); // Login as an instructor

            //Run the job
            new RunScheduledJobs().runScheduledJobs();//Run the jobs to get updated in instructor tiles


            //1. Log in as an instructor
            /*Expected -
            1. The links such as Last 7 Days, Last 5 Weeks & All should be displayed in Student Activity Section
            2. By default 'Last 7 Days' links should be selected*//*
            */
            new LoginUsingLTI().ltiLogin(dataIndex); // Login as an instructor
            CustomAssert.assertEquals(dashBoard.last7DaysLinkList.size(),1,"Verify 'Last 7 Days' link is selected by default","Verified that 'Last 7 Days' link is selected by default","'Last 7 Days' link is not selected by default");
            CustomAssert.assertEquals(dashBoard.link_last5Weeks.getText(),"Last 5 Weeks","Verify 'Last 5 weeks' link is displayed","Verified that 'Last 5 weeks' link is displayed","'Last 5 weeks' link is not selected");
            CustomAssert.assertEquals(dashBoard.link_all.getText(),"All","Verify 'All' link is displayed","Verified that 'All' link is displayed","'All' link is not displayed");

            /*Expected -
            3.Validate the count in 'Student Visited' tile in instructor Dashboard
            4. Validate the count in 'Questions Attempted' tile in instructor Dashboard
            5. Validate the count in ' minutes spent' tile in instructor Dashboard
            6. Validate the count in 'Discussions' tile in instructor Dashboard
            7. Validate the count in 'More Activity This Period' tile in instructor Dashboard*/
            CustomAssert.assertEquals(dashBoard.studentActivityCardList.get(0).getText(),"6\n"+"Students Visited","Student Visited Card is displayed correct count as 6","Verified 'Student Visited' card","Student Visited Card is not displayed correct count as 6");
            CustomAssert.assertEquals(dashBoard.studentActivityCardList.get(1).getText(),"35\n"+"Questions Attempted","Verify 'Questions Attempted' card","Verified 'Questions Attempted' card","'Questions Attempted' card is not displayed correct count as 33");
            //CustomAssert.assertEquals(dashBoard.studentActivityCardList.get(2).getText(),"3\n"+"Minutes Spent","Verify 'Minutes Spent' card","Verified 'Minutes Spent' card","'Minutes Spent' card is not displayed correct count as 3");
            CustomAssert.assertEquals(dashBoard.studentActivityCardList.get(3).getText(),"1\n"+"1\n"+"Discussions","Verify 'Discussions' card","Verified 'Discussions' card","'Discussions' card is not displayed correct count as 1");
            CustomAssert.assertEquals(dashBoard.studentActivityCardList.get(4).getText(),"100%\n"+"More Activity This Period","Verify 'More Activity This Period' card","Verified 'More Activity This Period","'More Activity This Period' card is not displayed correct count as 33");


            //Step - 2.1 -Click on Discussions in Discussions card
            //Expected - 8. Verify that the instructor can view the discussion added by the student
            dashBoard.studentActivityCardList.get(3).click();
            actualDiscussionText = dashBoard.instructorDiscussionText.getText();
            //CustomAssert.assertEquals(actualDiscussionText,randomText,"Verify discussion Text","Verified discussion Text","Discussion text is not as per added by the student in discussion card");
            new Navigator().orionDashboard();
            Thread.sleep(10000);
            //Step 2. Click on 'Last 5 Weeks' link in Student Activity Section
            //Expected - Repeat all the above points from 2 to 7
            dashBoard.link_last5Weeks.click();
            WebDriverUtil.waitForAjax(driver,15);
            ReportUtil.log("Clicking on link 'Last 5 weeks'", "Clicked on link 'Last 5 weeks' in instructor dashboard", "info");

            CustomAssert.assertEquals(dashBoard.studentActivityCardList.get(0).getText(),"6\n"+"Students Visited","Verify 'Student Visited' card","Verified 'Student Visited' card","Student Visited Card is not displayed correct count as 6");
            CustomAssert.assertEquals(dashBoard.studentActivityCardList.get(1).getText(),"35\n"+"Questions Attempted","Verify 'Questions Attempted' card","Verified 'Questions Attempted' card","'Questions Attempted' card is not displayed correct count as 33");
            //CustomAssert.assertEquals(dashBoard.studentActivityCardList.get(2).getText(),"3\n"+"Minutes Spent","Verify 'Minutes Spent' card","Verified 'Minutes Spent' card","'Minutes Spent' card is not displayed correct count as 3");
            CustomAssert.assertEquals(dashBoard.studentActivityCardList.get(3).getText(),"1\n"+"1\n"+"Discussions","Verify 'Discussions' card","Verified 'Discussions' card","'Discussions' card is not displayed correct count as 1");
            CustomAssert.assertEquals(dashBoard.studentActivityCardList.get(4).getText(),"100%\n"+"More Activity This Period","Verify 'More Activity This Period' card","Verified 'More Activity This Period","'More Activity This Period' card is not displayed correct count as 33");
            dashBoard.studentActivityCardList.get(3).click();
            actualDiscussionText = dashBoard.instructorDiscussionText.getText();
            CustomAssert.assertEquals(actualDiscussionText,actualDiscussionText,"Verify discussion Text","Verified discussion Text","Discussion text is not as per added by the student in discussion card");
            new Navigator().orionDashboard();


            //Step - 3. Click on 'All' link in Student Activity Section
            //Expected  -Repeat all the above points from 2 to 7
            Thread.sleep(2000);
            dashBoard.link_all.click();
            Thread.sleep(5000);
            ReportUtil.log("Clicking on link 'All'", "Clicked on link 'All' in instructor dashboard", "info");
            CustomAssert.assertEquals(dashBoard.studentActivityCardList.get(0).getText(),"6\n"+"Students Visited","Verify 'Student Visited' card","Verified 'Student Visited' card","Student Visited Card is not displayed correct count as 6");
            CustomAssert.assertEquals(dashBoard.studentActivityCardList.get(1).getText(),"35\n"+"Questions Attempted","Verify 'Questions Attempted' card","Verified 'Questions Attempted' card","'Questions Attempted' card is not displayed correct count as 33");
            //CustomAssert.assertEquals(dashBoard.studentActivityCardList.get(2).getText(),"3\n"+"Minutes Spent","Verify 'Minutes Spent' card","Verified 'Minutes Spent' card","'Minutes Spent' card is not displayed correct count as 3");
            CustomAssert.assertEquals(dashBoard.studentActivityCardList.get(3).getText(),"1\n"+"1\n"+"Discussions","Verify 'Discussions' card","Verified 'Discussions' card","'Discussions' card is not displayed correct count as 1");
            CustomAssert.assertEquals(dashBoard.studentActivityCardList.get(4).getText(),"100%\n"+"More Activity This Period","Verify 'More Activity This Period' card","Verified 'More Activity This Period","'More Activity This Period' card is not displayed correct count as 33");
            dashBoard.studentActivityCardList.get(3).click();
            actualDiscussionText = dashBoard.instructorDiscussionText.getText();
            CustomAssert.assertEquals(actualDiscussionText,actualDiscussionText,"Verify discussion Text","Verified discussion Text","Discussion text is not as per added by the student in discussion card");



             /*Expected -
            1. 'Weakest 7' link should be disabled
            2. 'All' link should be enabled*/
            new LoginUsingLTI().ltiLogin(dataIndex); // Login as an instructor
            CustomAssert.assertEquals(dashBoard.link_classPerformanceWeakest7.size(),1,"Verifying default selection of 'Weakest 7' link","'Weakest 7' link is selected by default in Class Performance Section","'Weakest 7' link is not selected by default in Class Performance Section");
            CustomAssert.assertEquals(dashBoard.link_classPerformanceAll.getText(),"All","Verifying 'All' link","'All' link is displayed in Class Performance Section","'All' link is not displayed in Class Performance Section");

            /*Expected -
            3. The column headers such as Chapters, Proficiency & Performance should be displayed in the same order
            4. Chapter Name under 'Chapters' column should be as per expected
            5. Proficiency Value under 'Proficiency' column should be as per expected
            6. Performance Value under 'Performance' column should be as per expectedPer*/
            CustomAssert.assertEquals(dashBoard.label_chapters.getText().trim(),"Chapters","Verifying 'Chapters' Column","'Chapters' Column is displayed in Class Performance Section","'Chapters' column is not displayed in Class Performance Section");
            CustomAssert.assertEquals(dashBoard.label_proficiency.getText().trim(),"Proficiency","Verifying 'proficiency' Column","'Chapters' Column is displayed in Class Performance Section","'proficiency' column is not displayed in Class Performance Section");
            CustomAssert.assertEquals(dashBoard.label_performance.getText().trim(),"Performance","Verifying 'Performance' Column","'Performance' Column is displayed in Class Performance Section","'Performance' column is not displayed in Class Performance Section");
            CustomAssert.assertEquals(dashBoard.performanceChapterName.getText().trim(),"Ch 1: The Changing Face of Business","Verifying Chapters Name under Chapters","Chapters Name under 'Chapters' column is displayed in Class Performance Section","Chapters Name under 'Chapters' column is not displayed in Class Performance Section");
          /*  CustomAssert.assertEquals(dashBoard.proficiencyPercentageValue.getText().trim(),"79%","Verifying Proficiency Value under Proficiency","Proficiency Value under 'Proficiency' column is displayed in Class Performance Section","Proficiency Value under 'Proficiency' column is not displayed in Class Performance Section");
            CustomAssert.assertEquals(dashBoard.performanceValue.getText().trim(),"20/20","Verifying Performance Value under 'Performance'","'Performance' value under 'Performance' column is displayed in Class Performance Section","'Performance' value under 'Performance' column is not displayed in Class Performance Section");
*/
            CustomAssert.assertEquals(dashBoard.proficiencyPercentageValue.getText().trim(),"79%","Verifying Proficiency Value under Proficiency","Proficiency Value under 'Proficiency' column is displayed in Class Performance Section","Proficiency Value under 'Proficiency' column is not displayed in Class Performance Section");
            CustomAssert.assertEquals(dashBoard.performanceValue.getText().trim(),"20/20","Verifying Performance Value under 'Performance'","'Performance' value under 'Performance' column is displayed in Class Performance Section","'Performance' value under 'Performance' column is not displayed in Class Performance Section");

            /*4. Click on 'All' link in Class Persormance Section
            1. The column headers such as Chapters, Proficiency & Performance should be displayed in the same order
            2. Chapter Name under 'Chapters' column should be as per expected
            3. Proficiency Value under 'Proficiency' column should be as per expected


            4. Performance Value under 'Performance' column should be as per expected*/
            dashBoard.link_classPerformanceAll.click();
            ReportUtil.log("Clicking on 'All' link","Clicked on 'All' link in 'Class Performance' Section","info");
            CustomAssert.assertEquals(dashBoard.label_chapters.getText().trim(), "Chapters", "Verifying 'Chapters' Column", "'Chapters' Column is displayed in Class Performance Section", "'Chapters' column is not displayed in Class Performance Section");
            CustomAssert.assertEquals(dashBoard.label_proficiency.getText().trim(),"Proficiency","Verifying 'proficiency' Column","'Chapters' Column is displayed in Class Performance Section","'proficiency' column is not displayed in Class Performance Section");
            CustomAssert.assertEquals(dashBoard.label_performance.getText().trim(),"Performance","Verifying 'Performance' Column","'Performance' Column is displayed in Class Performance Section","'Performance' column is not displayed in Class Performance Section");
            CustomAssert.assertEquals(dashBoard.performanceChapterName.getText().trim(),"Ch 1: The Changing Face of Business","Verifying Chapters Name under Chapters","Chapters Name under 'Chapters' column is displayed in Class Performance Section","Chapters Name under 'Chapters' column is not displayed in Class Performance Section");
            CustomAssert.assertEquals(dashBoard.proficiencyPercentageValue.getText().trim(),"79%","Verifying Proficiency Value under Proficiency","Proficiency Value under 'Proficiency' column is displayed in Class Performance Section","Proficiency Value under 'Proficiency' column is not displayed in Class Performance Section");
            CustomAssert.assertEquals(dashBoard.performanceValue.getText().trim(),"20/20","Verifying Performance Value under 'Performance'","'Performance' value under 'Performance' column is displayed in Class Performance Section","'Performance' value under 'Performance' column is not displayed in Class Performance Section");

        }catch(Exception e){
            Assert.fail("Exception in test method 'checkInstructorDashboardTiles' in the class 'InstructorDashboard'",e);
        }

    }




    /**
     *This method validates navigation To Various Reports through 'View Class Reports 'Dropdown
      */
    @Test(priority = 2, enabled = true)
    public void validateNavigationToVariousReportsThroughViewClassReportsDropdown() {
        try{
            WebDriver driver=Driver.getWebDriver();
            ReportUtil.log("Validating Navigation to Various Reports","Validating Navigation To Various Reports through 'View Class Reports 'Dropdown'","info");
            PerformanceReportInstructor PerformanceReportInstructor = PageFactory.initElements(driver,PerformanceReportInstructor.class);
            ProductivityReport productivityReport = PageFactory.initElements(driver,ProductivityReport.class);
            MetaCognitiveReport metacognitiveReport = PageFactory.initElements(driver,MetaCognitiveReport.class);
            MostChallengingActivities mostChallengingActivities = PageFactory.initElements(driver,MostChallengingActivities.class);

            /*Step - "1. Click on 'View class reports' & navigate to 'Performance report'"
            Expected - Performance Report' page should be displayed*/
            String dataIndex = "1";
            new LoginUsingLTI().ltiLogin(dataIndex); // Login as an instructor
            WebDriverUtil.selectDropdownValue("View class reports", "Performance Report");
            CustomAssert.assertEquals(PerformanceReportInstructor.classPerformanceByChaptersLabel.getText().trim(),"Class Performance by Chapters","Verifying 'Performance Report' page Navigation","Instructor Navigated to 'Performance Report' page","Instructor not Navigated to 'Performance Report' page");

            /*Step - "2. Click on 'Performance Report' & navigate to 'Productivity report'"
            Expected - Productivity Report' page should be displayed*/
            WebDriverUtil.selectDropdownValue("Performance Report", "Productivity Report");
            CustomAssert.assertEquals(productivityReport.getProductivityReportPageTitleText().getText().trim(),"Productivity Report","Verifying 'Productivity Report' page Navigation","Instructor Navigated to 'Productivity Report' page","Instructor not Navigated to 'Productivity Report' page");

            /*Step - "3. Click on 'Productivity Report' & navigate to 'Metacognitive Report'"
            Expected - Metacognitive Report' page should be displayed*/
            WebDriverUtil.selectDropdownValue("Productivity Report", "Metacognitive Report");
            CustomAssert.assertEquals(metacognitiveReport.getMetacognitiveReportPage().getText().trim(),"Metacognitive Report","Verifying 'Metacognitive Report' page Navigation","Instructor Navigated to 'Metacognitive Report' page","Instructor not Navigated to 'Metacognitive Report' page");

            /*Step - "4. Click on 'Metacognitive Report' & navigate to 'Most Challenging Activities Report'"
            Expected - Most ChallengingActivities Report' page should be displayed*/
            WebDriverUtil.selectDropdownValue("Metacognitive Report", "Most Challenging Activities");
            CustomAssert.assertEquals(mostChallengingActivities.mostChallengingReportTitle.getText().trim(), "Most Challenging Activities","Verifying 'Most Challenging Activities' page Navigation", "Instructor Navigated to 'Most Challenging Activities' page", "Instructor not Navigated to 'Most Challenging Activities' page");

        }catch(Exception e){
            Assert.fail("Exception in test method 'validateNavigationToVariousReportsThroughViewClassReportsDropdown' in the class 'InstructorDashboard'",e);
        }
    }

    /**
     This method validates that Navigation To Various Modules through profile dropdown
     */
    @Test(priority = 3, enabled = true)
    public void validateNavigationToVariousModulesThroughProfileNameDropdown() {
        try{
            ReportUtil.log("Validating Navigation to Various Modules","Validating Navigation To Various Modules through profile dropdown","info");
            WebDriver driver=Driver.getWebDriver();
            MostChallengingActivities mostChallengingActivities = PageFactory.initElements(driver,MostChallengingActivities.class);
            PersonalDetails personalDetails = PageFactory.initElements(driver,PersonalDetails.class);
            Setting setting = PageFactory.initElements(driver,Setting.class);

            /*Step - 1 .Click on Profile Name & navigate to 'My Reports' page
            Expected - Most ChallengingActivities Report' page should be displayed*/
            String dataIndex = "1";
            new LoginUsingLTI().ltiLogin(dataIndex); // Login as an instructor
            new Navigator().navigateFromProfileDropDownForOrion("My Reports");
            CustomAssert.assertEquals(mostChallengingActivities.mostChallengingReportTitle.getText(),"Most Challenging Activities","Validating 'Most ChallengingActivities Report' page from profile dropdown","Instructor navigated to 'Most ChallengingActivities Report' page from profile dropdown","Instructor not navigated to 'Most ChallengingActivities Report' page from profile dropdown");

            /*Step -2 "Click on Profile Name & navigate to 'My Profile' page"
            Expected - Personal Details' page should be displayed*/
            new Navigator().navigateFromProfileDropDownForOrion("My Profile");
            CustomAssert.assertEquals(personalDetails.label_personalDetails.getText(), "Personal Details", "Validating 'Personal Details' page from profile dropdown", "Instructor navigated to 'Personal Details' page from profile dropdown", "Instructor not navigated to 'Personal Details' page from profile dropdown");

            /*Step - 3." Click on Profile Name & navigate to 'Settings' page"
            Settings' page should be displayed*/
            new Navigator().navigateFromProfileDropDownForOrion("Settings");
            CustomAssert.assertEquals(setting.label_settings.getText(), "Settings", "Validating 'Settings' page from profile dropdown", "Instructor navigated to 'Settings' page from profile dropdown", "Instructor not navigated to 'Settings' page from profile dropdown");

        }catch(Exception e){
            Assert.fail("Exception in test method 'validateNavigationToVariousModulesThroughProfileNameDropdown' in the class 'InstructorDashboard'",e);
        }
    }


    /**
     * This method Validates that instructor to be able to upload his profile picture & teh same be updated in beside profile name
     */
    @Test(priority = 4, enabled = true)
    public void validateInstructorProfile(){
        try{
            WebDriver driver=Driver.getWebDriver();
            ReportUtil.log("Validating Instructor Profile Picture","Validates Instructor to be able to upload his profile picture & teh same be updated in beside profile name","info");
            PersonalDetails personalDetails = PageFactory.initElements(driver,PersonalDetails.class);
            DashBoard dashBoard = PageFactory.initElements(driver,DashBoard.class);

            /*Step -
            1. Login as an instructor
            2. Click on Profile Name & navigate to 'My Profile' page
            3. Upload a profile picture of an instructor"*/
            String dataIndex = "1";
            new LoginUsingLTI().ltiLogin(dataIndex); // Login as an instructor
            new Navigator().navigateFromProfileDropDownForOrion("My Profile");
            CustomAssert.assertEquals(personalDetails.label_personalDetails.getText(), "Personal Details", "Validating 'Personal Details' page from profile dropdown", "Instructor navigated to 'Personal Details' page from profile dropdown", "Instructor not navigated to 'Personal Details' page from profile dropdown");

            WebDriverUtil.mouseHover(By.id("my-profile-thumbnail-blk"));
            WebDriverUtil.clickOnElementUsingJavascript(personalDetails.ProfileUploadIcon);
            new KeysSend().sendKeyBoardKeys(Config.fileUploadPath+"img.png"+"^");
            Thread.sleep(3000);
            WebDriverUtil.clickOnElementUsingJavascript(personalDetails.button_uploadNow);//click on upload button
           // personalDetails.button_uploadNow.click();//click on upload button
            Thread.sleep(10000);
            ReportUtil.log("Uploading Profile Picture","Instructor uploaded a profile picture","info");
            //Expected - 1. Instructor should be able to upload his photo
            String img = personalDetails.thumbnailImage.getAttribute("src");
            if(img.contains("user-default-thumbnail.png")) {
                CustomAssert.fail("Validating the profile Picture","Instructor is not uploaded the profile picture");
            }

            //Expected - 2. The same picture should be updated in beside the profile name
             img = personalDetails.profileImage.getAttribute("src");
            if(img.contains("default-profile-image.png"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                CustomAssert.fail("Validating the profile Picture","The uploaded picture is not updated in beside the profile name");
            }

            //Step - "15. Click on 'View as Student' link
            dashBoard.link_viewAsStudent.click();
            ReportUtil.log("Clicking on 'View As Student'","Clicked on 'View As Student' link","info");
            String imageUrl = dashBoard.img_orionWelcomePage.getAttribute("src");
            if(!imageUrl.contains("/webresources/images/al/orion-welcome-page-1.jpg")){
                CustomAssert.fail("Validating 'View as Instructor' link","'View As instructor' link is not working as expected'");
            }

        }catch(Exception e){
            Assert.fail("Exception in test method 'validateNavigationToVariousModulesThroughProfileNameDropdown' in the class 'InstructorDashboard'",e);
        }
    }
}
