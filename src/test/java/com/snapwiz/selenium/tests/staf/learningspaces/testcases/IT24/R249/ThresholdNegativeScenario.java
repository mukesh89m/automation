package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT24.R249;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Gradebook.Gradebook;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.Filter;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.TocSearch;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.PerformanceSummaryReport;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.ProficiencyReport;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Calendar;

/**
 * Created by mukesh on 27/11/15.
 */
public class ThresholdNegativeScenario extends Driver {
    @Test(priority = 1,enabled = true)
    public  void thresholdRangeBox(){
        try {
            Dashboard dashboard= PageFactory.initElements(driver, Dashboard.class);
            TocSearch tocSearch= PageFactory.initElements(driver,TocSearch.class);
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);

            new LoginUsingLTI().ltiLogin("58"); //login as instructor
            dashboard.toc_drop.click(); //click on the study plan icon
            new Navigator().NavigateToOrion();
            new TopicOpen().clickOnAdaptivePracticeArrow();
            new TopicOpen().clickOnAssignThisIcon();
            new AssignLesson().assignTOCFromOrionAdaptivePractice(58);
            Thread.sleep(2000);
            Assert.assertEquals(tocSearch.rangeErrorMessage.getText().trim(),"The score for ORION adaptive practice cannot\nbe more than 100","Warning message  \"The score for ORION adaptive practice cannot be more than 100\" is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in TC thresholdRangeBox of class  ThresholdNegativeScenario", e);
        }

    }
    @Test(priority = 2,enabled = true)
    public  void thresholdDescendingOrder(){
        try {
            Dashboard dashboard= PageFactory.initElements(driver, Dashboard.class);
            TocSearch tocSearch= PageFactory.initElements(driver,TocSearch.class);
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);

            new LoginUsingLTI().ltiLogin("64"); //login as instructor
            dashboard.toc_drop.click(); //click on the study plan icon
            new Navigator().NavigateToOrion();
            new TopicOpen().clickOnAdaptivePracticeArrow();
            new TopicOpen().clickOnAssignThisIcon();
            new AssignLesson().assignTOCFromOrionAdaptivePractice(64);
            Thread.sleep(4000);
            new UIElement().waitAndFindElement(tocSearch.rangeErrorMessage);
            Assert.assertEquals(tocSearch.rangeErrorMessage.getText().trim(),"Values should be in ascending order","Values is not in ASCENDING order");

        } catch (Exception e) {
            Assert.fail("Exception in TC thresholdDescendingOrder of class  ThresholdNegativeScenario", e);
        }

    }
    @Test(priority = 3,enabled = true)
    public  void clearThreshold(){
        try {
            Dashboard dashboard= PageFactory.initElements(driver, Dashboard.class);
            TocSearch tocSearch= PageFactory.initElements(driver,TocSearch.class);
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            int dataIndex=71;
            String thresholdRangeOne = ReadTestData.readDataByTagName("", "thresholdRangeOne", Integer.toString(dataIndex));
            String thresholdRangeTwo = ReadTestData.readDataByTagName("", "thresholdRangeTwo", Integer.toString(dataIndex));
            String thresholdRangeThree = ReadTestData.readDataByTagName("", "thresholdRangeThree", Integer.toString(dataIndex));
            String thresholdRangeFour = ReadTestData.readDataByTagName("", "thresholdRangeFour", Integer.toString(dataIndex));
            String thresholdRangeFive = ReadTestData.readDataByTagName("", "thresholdRangeFive", Integer.toString(dataIndex));

            new LoginUsingLTI().ltiLogin("71"); //login as instructor
            dashboard.toc_drop.click(); //click on the study plan icon
            new Navigator().NavigateToOrion();
            new TopicOpen().clickOnAdaptivePracticeArrow();
            new TopicOpen().clickOnAssignThisIcon();
            new AssignLesson().assignTOCFromOrionAdaptivePractice(71);

            String firstRange=null;
            String secondRange=null;
            String thirdRange=null;
            String fourthRange=null;
            String fiveRange=null;
            firstRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-one').value;").toString();
            secondRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-two').value;").toString();
            thirdRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-three').value;").toString();
            fourthRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-four').value;").toString();
            fiveRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-five').value;").toString();
            if(!(firstRange.equals(thresholdRangeOne)&&secondRange.equals(thresholdRangeTwo)&&thirdRange.equals(thresholdRangeThree)&&fourthRange.equals(thresholdRangeFour)&&fiveRange.equals(thresholdRangeFive))){
                Assert.fail("Entered threshold values is not seen in Threshold textboxes.");
            }

            Thread.sleep(4000);
            tocSearch.selectedGradable_checkbox.click();
            Thread.sleep(5000);
            Assert.assertEquals(new BooleanValue().presenceOfElement(74, By.className("div[class='ir-ls-assign-dialog-gradable-threshold-label-check']")),false,"Grading Thresholds\" checkbox is still displaying");

            tocSearch.gradable_checkbox.click();
            tocSearch.gradableTreshold_checkbox.click();

            firstRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-one').value;").toString();
            secondRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-two').value;").toString();
            thirdRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-three').value;").toString();
            fourthRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-four').value;").toString();
            fiveRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-five').value;").toString();

            Assert.assertEquals(firstRange,"");
            Assert.assertEquals(secondRange,"");
            Assert.assertEquals(thirdRange,"");
            Assert.assertEquals(fourthRange,"");
            Assert.assertEquals(fiveRange,"");


        } catch (Exception e) {
            Assert.fail("Exception in TC clearThreshold of class  ThresholdNegativeScenario", e);
        }

    }
    @Test(priority = 4,enabled = true)
    public  void emptyThreshold(){
        try {

            //TC row no 78
            Dashboard dashboard= PageFactory.initElements(driver, Dashboard.class);
            TocSearch tocSearch= PageFactory.initElements(driver,TocSearch.class);
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            int dataIndex=71;
            new LoginUsingLTI().ltiLogin("71"); //login as instructor
            dashboard.toc_drop.click(); //click on the study plan icon
            new Navigator().NavigateToOrion();
            new TopicOpen().clickOnAdaptivePracticeArrow();
            new TopicOpen().clickOnAssignThisIcon();

            tocSearch.gradable_checkbox.click();
            tocSearch.gradableTreshold_checkbox.click();
            String firstRange=null;
            String secondRange=null;
            String thirdRange=null;
            String fourthRange=null;
            String fiveRange=null;

            firstRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-one').value;").toString();
            secondRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-two').value;").toString();
            thirdRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-three').value;").toString();
            fourthRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-four').value;").toString();
            fiveRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-five').value;").toString();

            Assert.assertEquals(firstRange,"","Threshold textboxes1 is not empty.");
            Assert.assertEquals(secondRange,"","Threshold textboxes2 is not empty.");
            Assert.assertEquals(thirdRange,"","Threshold textboxes3 is not empty.");
            Assert.assertEquals(fourthRange,"","Threshold textboxes4 is not empty.");
            Assert.assertEquals(fiveRange,"","Threshold textboxes5is not empty.");


            Thread.sleep(4000);
            tocSearch.selectedThresholdRange.click(); //Uncheck the "Grading Thresholds" checkbox.
            Thread.sleep(5000);
            Assert.assertEquals(new BooleanValue().presenceOfElement(74, By.className("div[class='ir-ls-assign-dialog-gradable-threshold-label-check']")), false, "Grading Thresholds\" checkbox is still displaying");

            tocSearch.gradable_checkbox.click();
            tocSearch.gradableTreshold_checkbox.click();

            firstRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-one').value;").toString();
            secondRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-two').value;").toString();
            thirdRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-three').value;").toString();
            fourthRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-four').value;").toString();
            fiveRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-five').value;").toString();


            Assert.assertEquals(firstRange,"","Threshold textboxes1 is not empty.");
            Assert.assertEquals(secondRange,"","Threshold textboxes2 is not empty.");
            Assert.assertEquals(thirdRange,"","Threshold textboxes3 is not empty.");
            Assert.assertEquals(fourthRange,"","Threshold textboxes4 is not empty.");
            Assert.assertEquals(fiveRange,"","Threshold textboxes5is not empty.");



        } catch (Exception e) {
            Assert.fail("Exception in TC emptyThreshold of class  ThresholdNegativeScenario", e);
        }

    }
    @Test(priority = 5,enabled = true)
    public  void clearFunctionalityForAddingThreshold(){
        try {

            //TC row no 85
            Dashboard dashboard= PageFactory.initElements(driver, Dashboard.class);
            TocSearch tocSearch= PageFactory.initElements(driver,TocSearch.class);
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            int dataIndex=71;
            String thresholdRangeOne = ReadTestData.readDataByTagName("", "thresholdRangeOne", Integer.toString(dataIndex));
            String thresholdRangeTwo = ReadTestData.readDataByTagName("", "thresholdRangeTwo", Integer.toString(dataIndex));
            String thresholdRangeThree = ReadTestData.readDataByTagName("", "thresholdRangeThree", Integer.toString(dataIndex));
            String thresholdRangeFour = ReadTestData.readDataByTagName("", "thresholdRangeFour", Integer.toString(dataIndex));
            String thresholdRangeFive = ReadTestData.readDataByTagName("", "thresholdRangeFive", Integer.toString(dataIndex));
            new LoginUsingLTI().ltiLogin("71"); //login as instructor
            dashboard.toc_drop.click(); //click on the study plan icon
            new Navigator().NavigateToOrion();
            new TopicOpen().clickOnAdaptivePracticeArrow();
            new TopicOpen().clickOnAssignThisIcon();
            new AssignLesson().assignTOCFromOrionAdaptivePractice(71);

            String firstRange=null;
            String secondRange=null;
            String thirdRange=null;
            String fourthRange=null;
            String fiveRange=null;
            firstRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-one').value;").toString();
            secondRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-two').value;").toString();
            thirdRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-three').value;").toString();
            fourthRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-four').value;").toString();
            fiveRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-five').value;").toString();
            if(!(firstRange.equals(thresholdRangeOne)&&secondRange.equals(thresholdRangeTwo)&&thirdRange.equals(thresholdRangeThree)&&fourthRange.equals(thresholdRangeFour)&&fiveRange.equals(thresholdRangeFive))){
                Assert.fail("Entered threshold values is not seen in Threshold textboxes.");
            }

            Thread.sleep(4000);
            tocSearch.selectedThresholdRange.click(); //9. Uncheck the "Grading Thresholds" checkbox..
            Thread.sleep(2000);
            try{
                tocSearch.threshold_val_one.sendKeys("10");
                Assert.fail("The \"gradebook threshold ranges in thermometer type\" approach is not going off");
            }catch(Exception e){

            }
            tocSearch.gradableTreshold_checkbox.click();
            firstRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-one').value;").toString();
            secondRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-two').value;").toString();
            thirdRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-three').value;").toString();
            fourthRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-four').value;").toString();
            fiveRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-five').value;").toString();

            Assert.assertEquals(firstRange,"","Threshold textboxes1 is not empty.");
            Assert.assertEquals(secondRange,"","Threshold textboxes2 is not empty.");
            Assert.assertEquals(thirdRange,"","Threshold textboxes3 is not empty.");
            Assert.assertEquals(fourthRange,"","Threshold textboxes4 is not empty.");
            Assert.assertEquals(fiveRange,"","Threshold textboxes5is not empty.");




        } catch (Exception e) {
            Assert.fail("Exception in TC clearFunctionalityForAddingThreshold of class  ThresholdNegativeScenario", e);
        }

    }
    @Test(priority = 6,enabled = true)
    public  void clearFunctionalityForEmptyThreshold(){
        try {

            //TC row no 90
            Dashboard dashboard= PageFactory.initElements(driver, Dashboard.class);
            TocSearch tocSearch= PageFactory.initElements(driver,TocSearch.class);
            int dataIndex=71;
            new LoginUsingLTI().ltiLogin("71"); //login as instructor
            dashboard.toc_drop.click(); //click on the study plan icon
            new Navigator().NavigateToOrion();
            new TopicOpen().clickOnAdaptivePracticeArrow();
            new TopicOpen().clickOnAssignThisIcon();
            tocSearch.gradable_checkbox.click();
            tocSearch.gradableTreshold_checkbox.click();


            String firstRange=null;
            String secondRange=null;
            String thirdRange=null;
            String fourthRange=null;
            String fiveRange=null;
            firstRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-one').value;").toString();
            secondRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-two').value;").toString();
            thirdRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-three').value;").toString();
            fourthRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-four').value;").toString();
            fiveRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-five').value;").toString();

            Assert.assertEquals(firstRange,"","Threshold textboxes1 is not empty.");
            Assert.assertEquals(secondRange,"","Threshold textboxes2 is not empty.");
            Assert.assertEquals(thirdRange,"","Threshold textboxes3 is not empty.");
            Assert.assertEquals(fourthRange,"","Threshold textboxes4 is not empty.");
            Assert.assertEquals(fiveRange,"","Threshold textboxes5is not empty.");

            Thread.sleep(4000);
            tocSearch.selectedThresholdRange.click(); //9. Uncheck the "Grading Thresholds" checkbox..
            Thread.sleep(2000);
            try{
                tocSearch.threshold_val_one.sendKeys("10");
                Assert.fail("The \"gradebook threshold ranges in thermometer type\" approach is not going off");
            }catch(Exception e){

            }
            tocSearch.gradableTreshold_checkbox.click();
            firstRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-one').value;").toString();
            secondRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-two').value;").toString();
            thirdRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-three').value;").toString();
            fourthRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-four').value;").toString();
            fiveRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-five').value;").toString();

            Assert.assertEquals(firstRange,"","Threshold textboxes1 is not empty.");
            Assert.assertEquals(secondRange,"","Threshold textboxes2 is not empty.");
            Assert.assertEquals(thirdRange,"","Threshold textboxes3 is not empty.");
            Assert.assertEquals(fourthRange,"","Threshold textboxes4 is not empty.");
            Assert.assertEquals(fiveRange,"","Threshold textboxes5is not empty.");




        } catch (Exception e) {
            Assert.fail("Exception in TC clearFunctionalityForEmptyThreshold of class  ThresholdNegativeScenario", e);
        }

    }
    @Test(priority = 7,enabled = true)
    public  void verifyScore(){
        try {

            //TC row no 90
            Dashboard dashboard= PageFactory.initElements(driver, Dashboard.class);
            TocSearch tocSearch= PageFactory.initElements(driver,TocSearch.class);
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            int dataIndex=71;
            new LoginUsingLTI().ltiLogin("71"); //login as instructor
            dashboard.toc_drop.click(); //click on the study plan icon
            new Navigator().NavigateToOrion();
            new TopicOpen().clickOnAdaptivePracticeArrow();
            new TopicOpen().clickOnAssignThisIcon();
            tocSearch.gradable_checkbox.click();
            Assert.assertTrue(tocSearch.gradableCheckbox_help.isDisplayed());
            tocSearch.gradableTreshold_checkbox.click();
            Assert.assertTrue(tocSearch.gradableThresholdCheckbox_help.isDisplayed());
            tocSearch.gradableThresholdCheckbox_help.click(); //click on the threshold help
            String helpText="You can allocate the points students receive if they fall within a certain proficiency range for this chapter in ORION.";
            Assert.assertEquals(tocSearch.gradableThresholdCheckbox_helpText.getText().trim(),helpText,"It should show message as “You can allocate the points students receive if they fall within a certain proficiency range for this chapter in ORION.");


        } catch (Exception e) {
            Assert.fail("Exception in TC verifyScore of class  ThresholdNegativeScenario", e);
        }

    }
    @Test(priority = 8,enabled = true)
    public  void oneRangeBoxEmpty(){
        try {

            //TC row no 90
            Dashboard dashboard= PageFactory.initElements(driver, Dashboard.class);
            TocSearch tocSearch= PageFactory.initElements(driver,TocSearch.class);
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            int dataIndex=71;
            new LoginUsingLTI().ltiLogin("71"); //login as instructor
            dashboard.toc_drop.click(); //click on the study plan icon
            new Navigator().NavigateToOrion();
            new TopicOpen().clickOnAdaptivePracticeArrow();
            new TopicOpen().clickOnAssignThisIcon();
            new AssignLesson().assignTOCFromOrionAdaptivePractice(71);



        } catch (Exception e) {
            Assert.fail("Exception in TC oneRangeBoxEmpty of class  ThresholdNegativeScenario", e);
        }
    }

    @Test(priority = 9,enabled = true)
    public  void verifyScoreForAdaptive(){
        try {

            //TC row no 92 &130
            Dashboard dashboard= PageFactory.initElements(driver, Dashboard.class);
            TocSearch tocSearch= PageFactory.initElements(driver,TocSearch.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.Filter filter=PageFactory.initElements(driver, Filter.class);
            ProficiencyReport proficiencyReport=PageFactory.initElements(driver,ProficiencyReport.class);
            int dataIndex=96;

            new LoginUsingLTI().ltiLogin("96_1"); //login as student
            new LoginUsingLTI().ltiLogin("96"); //login as instructor
            dashboard.toc_drop.click(); //click on the study plan icon
            new Navigator().NavigateToOrion();
            new TopicOpen().clickOnAdaptivePracticeArrow();
            new TopicOpen().clickOnAssignThisIcon();
            new AssignLesson().assignTOCFromOrionAdaptivePractice(96);
            String dueDate=tocSearch.tocDueDate.getText();
            Calendar calendar = Calendar.getInstance();

            String cal=Calender.getCurrentMonthName(calendar.get(Calendar.MONTH)).substring(0,3)+" 01, 2016";
            String exactDueDate="(Due Date: "+cal+")";
            Assert.assertEquals(dueDate,exactDueDate,"Due Date of the assignment should be is not shown : The format should be the same as for other assignments in the Study Plan.");

            new Navigator().NavigateTo("Current Assignments"); //navigate to Current Assignments
            String expectedAssignmentName="ORION Ch 1: The Study of Life";
            String assignmentName=currentAssignments.getAssignmentName().getText().trim();
            Assert.assertEquals(assignmentName,expectedAssignmentName,"Assignment entry in not be seen.");

            new LoginUsingLTI().ltiLogin("96_1"); //login as student
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new DiagnosticTest().startDiagnosticTestForAssignedPractice(2);
            new DiagnosticTest().DiagonesticTestQuitBetween(2, 10, "correct", false, false, true);//quit*/

            new Navigator().NavigateTo("e-Textbook"); //navigate to eTextbook
            new Click().clickBycssselector("span[class='ls-toc-due-date ls-adaptive-toc-due-date']");
            new AttemptTest().attemptAllCorrectForAdaptivePractive(2, false, false, 10);
            assignments.adaptiveFinish_button.click();//click on the finish button
            Thread.sleep(2000);
            Assert.assertEquals(assignments.assignmentOpenTillDueDate_link.getText().trim(),"Keep the assignment open till due date.","Keep the assignment open till due date suggestion is not showing");
            assignments.iamFinished_link.click(); //click on the i am finished link

            new Navigator().NavigateTo("Proficiency Report");
            filter.showChapter_link.click(); //click on the select chapter
            filter.selectChapter.get(1).click(); //select first chapter

            int overallScore=Integer.parseInt(proficiencyReport.getCourseProficiency().getText().trim());
            System.out.println("overallScore:"+overallScore);
            if(overallScore>10){
                Assert.fail("score of student is not given ranges");
            }
            new Navigator().NavigateTo("Assignments"); //navigate to Assignments
            String score=assignments.getScore().getText();
            Assert.assertEquals(score,"Score (10/100)","score of student is not 10/100 according to given ranges ");



        } catch (Exception e) {
            Assert.fail("Exception in TC verifyScoreForAdaptive of class  ThresholdNegativeScenario", e);
        }

    }

    @Test(priority = 10,enabled = true)
    public  void verifyScoreForAdaptivePractice(){
        try {

            //TC row no 92 &130
            Dashboard dashboard= PageFactory.initElements(driver, Dashboard.class);
            TocSearch tocSearch= PageFactory.initElements(driver,TocSearch.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.Filter filter=PageFactory.initElements(driver, Filter.class);
            PerformanceSummaryReport performanceSummaryReport=PageFactory.initElements(driver,PerformanceSummaryReport.class);
            int dataIndex=96;

            new LoginUsingLTI().ltiLogin("96_1"); //login as student
            new LoginUsingLTI().ltiLogin("96"); //login as instructor
            dashboard.toc_drop.click(); //click on the study plan icon
            new Navigator().NavigateToOrion();
            new TopicOpen().clickOnAdaptivePracticeArrow();
            new TopicOpen().clickOnAssignThisIcon();
            new AssignLesson().assignTOCFromOrionAdaptivePractice(96);
            String dueDate=tocSearch.tocDueDate.getText();
            Calendar calendar = Calendar.getInstance();

            String cal=Calender.getCurrentMonthName(calendar.get(Calendar.MONTH)).substring(0,3)+" 01, 2016";
            String exactDueDate="(Due Date: "+cal+")";
            Assert.assertEquals(dueDate,exactDueDate,"Due Date of the assignment should be is not shown : The format should be the same as for other assignments in the Study Plan.");

            new Navigator().NavigateTo("Current Assignments"); //navigate to Current Assignments
            String expectedAssignmentName="ORION Ch 1: The Study of Life";
            String assignmentName=currentAssignments.getAssignmentName().getText().trim();
            Assert.assertEquals(assignmentName,expectedAssignmentName,"Assignment entry in not be seen.");

            new LoginUsingLTI().ltiLogin("96_1"); //login as student
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new DiagnosticTest().startDiagnosticTestForAssignedPractice(2);
            new DiagnosticTest().DiagonesticTestQuitBetween(2, 10, "correct", false, false, true);//quit*/

            new Navigator().NavigateTo("e-Textbook"); //navigate to eTextbook
            new Click().clickBycssselector("span[class='ls-toc-due-date ls-adaptive-toc-due-date']");
            new AttemptTest().attemptAllCorrectForAdaptivePractive(2, false, false, 10);
            assignments.adaptiveFinish_button.click();//click on the finish button
            Thread.sleep(2000);
            Assert.assertEquals(assignments.assignmentOpenTillDueDate_link.getText().trim(),"Keep the assignment open till due date.","Keep the assignment open till due date suggestion is not showing");
            assignments.iamFinished_link.click(); //click on the i am finished link

            String questionNo= performanceSummaryReport.performanceChart_questionNo.getText().trim();
            Assert.assertEquals(performanceSummaryReport.chartTitle.getText(),"Performance Summary  ", "Student is not navigated to Performance Summary page");
            Assert.assertEquals(questionNo,"11\nQuestions","");

            new Navigator().NavigateTo("Assignments"); //navigate to Assignments
            String score=assignments.getScore().getText();
            Assert.assertEquals(score,"Score (10/100)","score of student is not 10/100 according to given ranges ");



        } catch (Exception e) {
            Assert.fail("Exception in TC verifyScoreForAdaptivePractice of class  ThresholdNegativeScenario", e);
        }

    }

    @Test(priority = 11,enabled = true)
    public  void keepTheAssignmentOpen(){
        try {

            //TC row no 92 &130
            Dashboard dashboard= PageFactory.initElements(driver, Dashboard.class);
            TocSearch tocSearch= PageFactory.initElements(driver,TocSearch.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.Filter filter=PageFactory.initElements(driver, Filter.class);

            new LoginUsingLTI().ltiLogin("137_1"); //login as student
            new LoginUsingLTI().ltiLogin("137"); //login as instructor
            dashboard.toc_drop.click(); //click on the study plan icon
            new Navigator().NavigateToOrion();
            new TopicOpen().clickOnAdaptivePracticeArrow();
            new TopicOpen().clickOnAssignThisIcon();
            new AssignLesson().assignTOCFromOrionAdaptivePractice(137);
            String dueDate=tocSearch.tocDueDate.getText();
            Calendar calendar = Calendar.getInstance();

            String cal=Calender.getCurrentMonthName(calendar.get(Calendar.MONTH)).substring(0,3)+" 01, 2016";
            String exactDueDate="(Due Date: "+cal+")";
            Assert.assertEquals(dueDate,exactDueDate,"Due Date of the assignment should be is not shown : The format should be the same as for other assignments in the Study Plan.");

            new Navigator().NavigateTo("Current Assignments"); //navigate to Current Assignments
            String expectedAssignmentName="ORION Ch 1: The Study of Life";
            String assignmentName=currentAssignments.getAssignmentName().getText().trim();
            Assert.assertEquals(assignmentName,expectedAssignmentName,"Assignment entry in not be seen.");

            new LoginUsingLTI().ltiLogin("137_1"); //login as student
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new DiagnosticTest().startDiagnosticTestForAssignedPractice(2);
            new DiagnosticTest().DiagonesticTestQuitBetween(2, 10, "correct", false, false, true);//quit*/

            new Navigator().NavigateTo("e-Textbook"); //navigate to eTextbook
            new Click().clickBycssselector("span[class='ls-toc-due-date ls-adaptive-toc-due-date']");
            new AttemptTest().attemptAllCorrectForAdaptivePractive(2, false, false, 10);
            assignments.adaptiveFinish_button.click();//click on the finish button
            Thread.sleep(2000);
            Assert.assertEquals(assignments.assignmentOpenTillDueDate_link.getText().trim(), "Keep the assignment open till due date.", "Keep the assignment open till due date suggestion is not showing");
            Assert.assertEquals(assignments.iamFinished_link.getText().trim(),"I'm Finished"," I'm Finished suggestion is not showing");
            assignments.assignmentOpenTillDueDate_link.click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']"))); //click on teh next button



        } catch (Exception e) {
            Assert.fail("Exception in TC keepTheAssignmentOpen of class  ThresholdNegativeScenario", e);
        }

    }

    @Test(priority = 12,enabled = true)
    public  void updateAssignmnetInCurrentAssignmentPage(){
        try {

            //TC row no 142
            Dashboard dashboard= PageFactory.initElements(driver, Dashboard.class);
            TocSearch tocSearch= PageFactory.initElements(driver,TocSearch.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.Filter filter=PageFactory.initElements(driver, Filter.class);

            new LoginUsingLTI().ltiLogin("142_1"); //login as student1
            new LoginUsingLTI().ltiLogin("142_2"); //login as student2

            new LoginUsingLTI().ltiLogin("142"); //login as instructor
            dashboard.toc_drop.click(); //click on the study plan icon
            new Navigator().NavigateToOrion();
            new TopicOpen().clickOnAdaptivePracticeArrow();
            new TopicOpen().clickOnAssignThisIcon();
            new AssignLesson().assignTOCFromOrionAdaptivePractice(142);

            new Navigator().NavigateTo("Current Assignments"); //navigate to Current Assignments
            String expectedAssignmentName="ORION Ch 1: The Study of Life";
            String assignmentName=currentAssignments.getAssignmentName().getText().trim();
            Assert.assertEquals(assignmentName,expectedAssignmentName,"Assignment entry in not be seen.");

            new LoginUsingLTI().ltiLogin("142_1"); //login as student1
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new DiagnosticTest().startDiagnosticTestForAssignedPractice(2);
            new DiagnosticTest().DiagonesticTestQuitBetween(2, 10, "correct", false, false, true);//quit


            new Navigator().NavigateTo("e-Textbook"); //navigate to eTextbook
            new Click().clickBycssselector("span[class='ls-toc-due-date ls-adaptive-toc-due-date']");
            new AttemptTest().attemptAllCorrectForAdaptivePractive(2, false, false, 1);

            new LoginUsingLTI().ltiLogin("142"); //login as instructor
            new Navigator().NavigateTo("Current Assignments"); //navigate to Current Assignments

            currentAssignments.updateAssignment_link.get(0).click();
            Assert.assertEquals(tocSearch.threshold_val_one.getAttribute("disabled"),"true","The grading Threshold Range Textboxes should is not ReadOnly.");
            Assert.assertEquals(tocSearch.selectedThresholdRange.getAttribute("checked"),"true","The “Grading Thresholds” check box option is not read only");



        } catch (Exception e) {
            Assert.fail("Exception in TC updateAssignmnetInCurrentAssignmentPage of class  ThresholdNegativeScenario", e);
        }

    }

    @Test(priority = 13,enabled = true)
    public  void noStudentStartedAssignment(){
        try {

            //TC row no 142
            Dashboard dashboard= PageFactory.initElements(driver, Dashboard.class);
            TocSearch tocSearch= PageFactory.initElements(driver,TocSearch.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage.class);

            new LoginUsingLTI().ltiLogin("148_1"); //login as student1

            new LoginUsingLTI().ltiLogin("148"); //login as instructor
            dashboard.toc_drop.click(); //click on the study plan icon
            new Navigator().NavigateToOrion();
            new TopicOpen().clickOnAdaptivePracticeArrow();
            new TopicOpen().clickOnAssignThisIcon();
            new AssignLesson().assignTOCFromOrionAdaptivePractice(148);

            new Navigator().NavigateTo("Current Assignments"); //navigate to Current Assignments
            String expectedAssignmentName="ORION Ch 1: The Study of Life";
            String assignmentName=currentAssignments.getAssignmentName().getText().trim();
            Assert.assertEquals(assignmentName,expectedAssignmentName,"Assignment entry in not be seen.");

            currentAssignments.updateAssignment_link.get(0).click();
            Assert.assertEquals(tocSearch.threshold_val_one.getAttribute("disabled"), null, "The grading Threshold Range Textboxes should is not ReadOnly.");
            Assert.assertEquals(tocSearch.selectedThresholdRange.getAttribute("checked"),"true","The “Grading Thresholds” check box option is not read only");

            tocSearch.threshold_val_one.clear();
            tocSearch.threshold_val_one.sendKeys("20");

            tocSearch.threshold_val_two.clear();
            tocSearch.threshold_val_two.sendKeys("40");

            assignmentResponsesPage.assign_button.click(); //click on the assign
            String firstRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-one').value;").toString();
            String secondRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-two').value;").toString();

            Assert.assertEquals(firstRange,"20","“Grading Threshold\" Ranges textboxes are not updated with new values.");
            Assert.assertEquals(secondRange,"40","“Grading Threshold\" Ranges textboxes are not updated with new values.");


        } catch (Exception e) {
            Assert.fail("Exception in TC noStudentStartedAssignment of class  ThresholdNegativeScenario", e);
        }
    }

    @Test(priority = 13,enabled = true)
    public  void cancelbuttonWhileAssigningAssignment(){
        try {

            //TC row no 142
            Dashboard dashboard= PageFactory.initElements(driver, Dashboard.class);
            TocSearch tocSearch= PageFactory.initElements(driver,TocSearch.class);
            CurrentAssignments currentAssignments=PageFactory.initElements(driver,CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage=PageFactory.initElements(driver,AssignmentResponsesPage.class);
            int dataIndex=157;
            String thresholdRangeOne = ReadTestData.readDataByTagName("", "thresholdRangeOne", Integer.toString(dataIndex));
            String thresholdRangeTwo = ReadTestData.readDataByTagName("", "thresholdRangeTwo", Integer.toString(dataIndex));
            String thresholdRangeThree = ReadTestData.readDataByTagName("", "thresholdRangeThree", Integer.toString(dataIndex));
            String thresholdRangeFour = ReadTestData.readDataByTagName("", "thresholdRangeFour", Integer.toString(dataIndex));
            String thresholdRangeFive = ReadTestData.readDataByTagName("", "thresholdRangeFive", Integer.toString(dataIndex));

            new LoginUsingLTI().ltiLogin("157_1"); //login as student1

            new LoginUsingLTI().ltiLogin("157"); //login as instructor
            dashboard.toc_drop.click(); //click on the study plan icon
            new Navigator().NavigateToOrion();
            new TopicOpen().clickOnAdaptivePracticeArrow();
            new TopicOpen().clickOnAssignThisIcon();
            new AssignLesson().assignTOCFromOrionAdaptivePractice(157);

            new Navigator().NavigateTo("Current Assignments"); //navigate to Current Assignments
            String expectedAssignmentName="ORION Ch 1: The Study of Life";
            String assignmentName=currentAssignments.getAssignmentName().getText().trim();
            Assert.assertEquals(assignmentName,expectedAssignmentName,"Assignment entry in not be seen.");

            currentAssignments.updateAssignment_link.get(0).click();
            Assert.assertEquals(tocSearch.threshold_val_one.getAttribute("disabled"), null, "The grading Threshold Range Textboxes should is not ReadOnly.");
            Assert.assertEquals(tocSearch.selectedThresholdRange.getAttribute("checked"),"true","The “Grading Thresholds” check box option is not read only");

            tocSearch.threshold_val_one.clear();
            tocSearch.threshold_val_one.sendKeys("20");

            tocSearch.threshold_val_two.clear();
            tocSearch.threshold_val_two.sendKeys("40");

            tocSearch.threshold_val_three.clear();
            tocSearch.threshold_val_three.sendKeys("60");

            tocSearch.threshold_val_four.clear();
            tocSearch.threshold_val_four.sendKeys("80");

            tocSearch.threshold_val_five.clear();
            tocSearch.threshold_val_five.sendKeys("100");

            assignmentResponsesPage.cancel_button.click(); //click on the cancel button
            currentAssignments.updateAssignment_link.get(0).click(); //click on the update assignment

            String firstRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-one').value;").toString();
            String secondRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-two').value;").toString();
            String thirdRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-three').value;").toString();
            String fourthRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-four').value;").toString();
            String fiveRange=((JavascriptExecutor)driver).executeScript("return document.getElementById('gradable-threshold-range-five').value;").toString();

            if(!(firstRange.equals(thresholdRangeOne)&&secondRange.equals(thresholdRangeTwo)&&thirdRange.equals(thresholdRangeThree)&&fourthRange.equals(thresholdRangeFour)&&fiveRange.equals(thresholdRangeFive))){
                Assert.fail("Entered threshold values is not seen in Threshold textboxes.");
            }

            Assert.assertNotEquals(firstRange, "20", "“Grading Threshold\" Ranges textboxes are not updated with new values.");
            Assert.assertNotEquals(secondRange, "40", "“Grading Threshold\" Ranges textboxes are not updated with new values.");



        } catch (Exception e) {
            Assert.fail("Exception in TC cancelbuttonWhileAssigningAssignment of class  ThresholdNegativeScenario", e);
        }

    }

    @Test(priority = 14,enabled = true)
    public  void verifyScoreInGradebookPage(){
        try {

            //TC row no 142
            Dashboard dashboard= PageFactory.initElements(driver, Dashboard.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            Gradebook gradebook=PageFactory.initElements(driver,Gradebook.class);
            int dataIndex=166;

            new LoginUsingLTI().ltiLogin("166_1"); //login as student1

            new LoginUsingLTI().ltiLogin("166"); //login as instructor
            dashboard.toc_drop.click(); //click on the study plan icon
            new Navigator().NavigateToOrion();
            new TopicOpen().clickOnAdaptivePracticeArrow();
            new TopicOpen().clickOnAssignThisIcon();
            new AssignLesson().assignTOCFromOrionAdaptivePractice(166);

            new LoginUsingLTI().ltiLogin("166_1"); //login as student
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new DiagnosticTest().startDiagnosticTestForAssignedPractice(2);
            new DiagnosticTest().DiagonesticTestQuitBetween(2, 10, "correct", false, false, true);//quit*//*

            new Navigator().NavigateTo("e-Textbook"); //navigate to eTextbook
            new Click().clickBycssselector("span[class='ls-toc-due-date ls-adaptive-toc-due-date']");
            new AttemptTest().attemptAllCorrectForAdaptivePractive(2, false, false, 10);
            assignments.adaptiveFinish_button.click();
            assignments.iamFinished_link.click();//click on the finish button


            new LoginUsingLTI().ltiLogin("166"); //login as instructor
            new Navigator().NavigateTo("Gradebook"); //navigate to Gradebook
            for(WebElement ele:gradebook.gradebookWeight){
                if(ele.isDisplayed()){
                    String value=ele.getText().trim();
                    Assert.assertEquals(value,"10/100");
                    break;
                }
            }

            String overallScore=gradebook.getOverAllScore().get(1).getText().trim();
            Assert.assertEquals(overallScore,"10%");

        } catch (Exception e) {
            Assert.fail("Exception in TC verifyScoreInGradebookPage of class  ThresholdNegativeScenario", e);
        }
    }

    @Test(priority = 15,enabled = true)
    public  void  verifyScoreWhenDueDateExpiry(){
        try {

            //TC row no 142
            Dashboard dashboard= PageFactory.initElements(driver, Dashboard.class);
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            Gradebook gradebook=PageFactory.initElements(driver,Gradebook.class);
            int dataIndex=185;

            new LoginUsingLTI().ltiLogin("185_1"); //login as student1

            new LoginUsingLTI().ltiLogin("185"); //login as instructor
            dashboard.toc_drop.click(); //click on the study plan icon
            new Navigator().NavigateToOrion();
            new TopicOpen().clickOnAdaptivePracticeArrow();
            new TopicOpen().clickOnAssignThisIcon();
            new AssignLesson().assignTOCFromOrionAdaptivePracticeWithDueDate(185);

            StopWatch stopWatch=new StopWatch();
            stopWatch.start();
            new LoginUsingLTI().ltiLogin("185_1"); //login as student
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new DiagnosticTest().startDiagnosticTestForAssignedPractice(2);
            new DiagnosticTest().DiagonesticTestQuitBetween(2, 10, "correct", false, false, true);//quit*/
            stopWatch.stop();
            Thread.sleep(420000);
            new Navigator().NavigateTo("Assignments"); //navigate to Assignments
            String score=assignments.getScore().getText();
            Assert.assertEquals(score,"Score (0/100)","score of student is not 10/100 according to given ranges ");


        } catch (Exception e) {
            Assert.fail("Exception in TC verifyScoreWhenDueDateExpiry of class  ThresholdNegativeScenario", e);
        }
    }
}
