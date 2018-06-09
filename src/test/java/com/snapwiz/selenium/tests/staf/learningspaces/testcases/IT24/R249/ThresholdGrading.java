package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT24.R249;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.TocSearch;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Calendar;
import java.util.List;

/**
 * Created by navin on 22-11-2015.
 */
public class ThresholdGrading extends Driver {
    @Test(priority = 0, enabled = true)
    public void gradableThresholdRangeBoxes() {
        try {
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            TocSearch tocSearch = PageFactory.initElements(driver, TocSearch.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);

            new LoginUsingLTI().ltiLogin("12");
            //Minimizes Rdsolution
            WebElement html = driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

            dashboard.toc_tree.click();
            dashboard.toc_drop.click();
            new Navigator().NavigateToOrion();
            new TopicOpen().clickOnAdaptivePracticeArrow();
            new TopicOpen().clickOnAssignThisIcon();
            Thread.sleep(2000);
            tocSearch.gradable_checkbox.click();

            Assert.assertTrue(tocSearch.gradableThresholdCheckbox_help.isDisplayed());
            tocSearch.gradableThresholdCheckbox_help.click(); //click on the threshold help
            String helpText = "You can allocate the points students receive if they fall within a certain proficiency range for this chapter in ORION.";
            Assert.assertEquals(tocSearch.gradableThresholdCheckbox_helpText.getText().trim(), helpText, "It should show message as “You can allocate the points students receive if they fall within a certain proficiency range for this chapter in ORION.");

            tocSearch.gradableTreshold_checkbox.click();
            Assert.assertEquals(tocSearch.totalMarks1.getText().trim(),"/100\n 0%", "Total Score is not coming in expectedformat");
            Assert.assertEquals(tocSearch.totalMarks2.getText().trim(),"/100\n 20%", "Total Score is not coming in expectedformat");
            Assert.assertEquals(tocSearch.totalMarks3.getText().trim(),"/100\n 40%","Total Score is not coming in expectedformat");
            Assert.assertEquals(tocSearch.totalMarks4.getText().trim(),"/100\n 60%","Total Score is not coming in expectedformat");
            Assert.assertEquals(tocSearch.totalMarks5.getText().trim(),"/100\n 80%\n100%","Total Score is not coming in expectedformat");

            String firstRange = null;
            String secondRange = null;
            String thirdRange = null;
            String fourthRange = null;
            String fiveRange = null;
            firstRange = ((JavascriptExecutor) driver).executeScript("return document.getElementById('gradable-threshold-range-one').value;").toString();
            secondRange = ((JavascriptExecutor) driver).executeScript("return document.getElementById('gradable-threshold-range-two').value;").toString();
            thirdRange = ((JavascriptExecutor) driver).executeScript("return document.getElementById('gradable-threshold-range-three').value;").toString();
            fourthRange = ((JavascriptExecutor) driver).executeScript("return document.getElementById('gradable-threshold-range-four').value;").toString();
            fiveRange = ((JavascriptExecutor) driver).executeScript("return document.getElementById('gradable-threshold-range-five').value;").toString();
            Assert.assertEquals(firstRange, "", "Threshold textboxes1 is not empty.");
            Assert.assertEquals(secondRange, "", "Threshold textboxes2 is not empty.");
            Assert.assertEquals(thirdRange, "", "Threshold textboxes3 is not empty.");
            Assert.assertEquals(fourthRange, "", "Threshold textboxes4 is not empty.");
            Assert.assertEquals(fiveRange, "", "Threshold textboxes5is not empty.");
            tocSearch.selectedGradable_checkbox.click();
            Assert.assertTrue(!tocSearch.gradableTreshold_checkbox.isDisplayed());
            Assert.assertTrue(!tocSearch.gradableThresholdCheckbox_help.isDisplayed());

        } catch (Exception e) {
            Assert.fail("Exception in TC gradableThresholdRangeBoxes of class  ThresholdGrading", e);
        }

    }

    @Test(priority = 1, enabled = true)
    public void thresholdAscendingOrder() {
        try {
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            TocSearch tocSearch = PageFactory.initElements(driver, TocSearch.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new LoginUsingLTI().ltiLogin("40"); //login as instructor
            dashboard.toc_drop.click(); //click on the study plan icon
            new Navigator().NavigateToOrion(); //Navigate to Orion Tab
            new TopicOpen().clickOnAdaptivePracticeArrow(); //Click on Arrow
            new TopicOpen().clickOnAssignThisIcon(); //
            new AssignLesson().assignTOCFromOrionAdaptivePractice(40);

            String dueDate=tocSearch.tocDueDate.getText();
            Calendar calendar = Calendar.getInstance();

            String cal=Calender.getCurrentMonthName(calendar.get(Calendar.MONTH)).substring(0,3)+" 01, 2016";
            String exactDueDate="(Due Date: "+cal+")";
            Assert.assertEquals(dueDate,exactDueDate,"Due Date of the assignment should be is not shown : The format should be the same as for other assignments in the Study Plan.");

            new Navigator().NavigateTo("Current Assignments"); //navigate to Current Assignments
            String expectedAssignmentName="(shor) ORION Ch 1: The Study of Life";
            String assignmentName=currentAssignments.getAssignmentName().getText().trim();
            Assert.assertEquals(assignmentName, expectedAssignmentName, "Assignment entry in not be seen.");


        } catch (Exception e) {
            Assert.fail("Exception in TC thresholdAscendingOrder of class  ThresholdGrading", e);
        }

    }

    @Test(priority = 2, enabled = true)
    public void clearThresholdsOnCancel() {
        try {
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            TocSearch tocSearch = PageFactory.initElements(driver, TocSearch.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            NewAssignment  newAssignment = PageFactory.initElements(driver, NewAssignment.class);

            new LoginUsingLTI().ltiLogin("120"); //login as instructor
            dashboard.toc_drop.click(); //click on the study plan icon
            new Navigator().NavigateToOrion(); //Navigate to Orion Tab
            new TopicOpen().clickOnAdaptivePracticeArrow(); //Click on Arrow
            new TopicOpen().clickOnAssignThisIcon(); //
            new AssignLesson().assignTOCFromOrionAdaptivePractice(120); // entering the details
            Thread.sleep(5000);
            driver.findElement(By.id("assign-cancel")).click();
            //newAssignment.cancelPopUp.click(); //Clicking on cancel

            new Navigator().NavigateToOrion();
            new TopicOpen().clickOnAdaptivePracticeArrow(); //Click on Arrow
            new TopicOpen().clickOnAssignThisIcon(); // Click on AssignThisButton
            new UIElement().waitAndFindElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label-check']"));
            tocSearch.gradable_checkbox.click();
            Assert.assertTrue(tocSearch.gradableThresholdCheckbox_help.isDisplayed());
            tocSearch.gradableThresholdCheckbox_help.click(); //click on the threshold help
            String helpText = "You can allocate the points students receive if they fall within a certain proficiency range for this chapter in ORION.";
            Assert.assertEquals(tocSearch.gradableThresholdCheckbox_helpText.getText().trim(), helpText, "It should show message as “You can allocate the points students receive if they fall within a certain proficiency range for this chapter in ORION.");
            tocSearch.gradableTreshold_checkbox.click();
            String firstRange = null;
            String secondRange = null;
            String thirdRange = null;
            String fourthRange = null;
            String fiveRange = null;
            firstRange = ((JavascriptExecutor) driver).executeScript("return document.getElementById('gradable-threshold-range-one').value;").toString();
            secondRange = ((JavascriptExecutor) driver).executeScript("return document.getElementById('gradable-threshold-range-two').value;").toString();
            thirdRange = ((JavascriptExecutor) driver).executeScript("return document.getElementById('gradable-threshold-range-three').value;").toString();
            fourthRange = ((JavascriptExecutor) driver).executeScript("return document.getElementById('gradable-threshold-range-four').value;").toString();
            fiveRange = ((JavascriptExecutor) driver).executeScript("return document.getElementById('gradable-threshold-range-five').value;").toString();
            Assert.assertEquals(firstRange, "", "Threshold textboxes1 is not empty.");
            Assert.assertEquals(secondRange, "", "Threshold textboxes2 is not empty.");
            Assert.assertEquals(thirdRange, "", "Threshold textboxes3 is not empty.");
            Assert.assertEquals(fourthRange, "", "Threshold textboxes4 is not empty.");
            Assert.assertEquals(fiveRange, "", "Threshold textboxes5is not empty.");

        } catch (Exception e) {
            Assert.fail("Exception in TC clearThresholdsOnCancel of class  ThresholdGrading", e);
        }

    }

    @Test(priority = 3, enabled = true)
    public void instructorStudentFlow() {
        try {
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            TocSearch tocSearch = PageFactory.initElements(driver, TocSearch.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
           /* new LoginUsingLTI().ltiLogin("130"); //login as instructor
            dashboard.toc_drop.click(); //click on the study plan icon
            new Navigator().NavigateToOrion(); //Navigate to Orion Tab
            new TopicOpen().clickOnAdaptivePracticeArrow(); //Click on Arrow
            new TopicOpen().clickOnAssignThisIcon(); //
            new AssignLesson().assignTOCFromOrionAdaptivePractice(130);

            String dueDate=tocSearch.tocDueDate.getText();
            Calendar calendar = Calendar.getInstance();
            String cal=Calender.getCurrentMonthName(calendar.get(Calendar.MONTH)).substring(0,3)+" 01, 2016";
            String exactDueDate="(Due Date: "+cal+")";
            Assert.assertEquals(dueDate, exactDueDate, "Due Date of the assignment should be is not shown : The format should be the same as for other assignments in the Study Plan.");
            new Navigator().NavigateTo("Current Assignments"); //navigate to Current Assignments
            String expectedAssignmentName="(shor) ORION Ch 1: The Study of Life";
            String assignmentName=currentAssignments.getAssignmentName().getText().trim();
            Assert.assertEquals(assignmentName, expectedAssignmentName, "Assignment entry in not be seen.");*/

            new LoginUsingLTI().ltiLogin("131"); //login as student
           /* new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new DiagnosticTest().startDiagnosticTestForAssignedPractice(2);
            new DiagnosticTest().DiagonesticTestQuitBetween(2, 5, "correct", false, false, true);
*/

            new Navigator().NavigateTo("e-Textbook"); //navigate to eTextbook
            new Click().clickBycssselector("span[class='ls-toc-due-date ls-adaptive-toc-due-date']");
            new PracticeTest().quitThePracticeTest();

        } catch (Exception e) {
            Assert.fail("Exception in TC instructorStudentFlow of class  ThresholdGrading", e);
        }

    }




}

