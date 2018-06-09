package com.snapwiz.selenium.tests.staf.learnon.testcases.IT22.R2213;

import com.snapwiz.selenium.tests.staf.learnon.Config;
import com.snapwiz.selenium.tests.staf.learnon.Driver;
import com.snapwiz.selenium.tests.staf.learnon.ReadTestData;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.KeysSend;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.ShareWith;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.List;

/**
 * Created by durgapathi on 12/8/15.
 */
public class AssignThisPopupForAssignments {
    @Test(priority = 1, enabled = true)
    public void assignThisPopupForCustomAssignment() {
        try {
            Driver.startDriver();
            new Assignment().create(11); //Create Assignemnt
            new LoginUsingLTI().ltiLogin("11"); //Login as Teacher
            new Assignment().createCustomeAssignment("11"); //Create Custom Assignment
            Driver.driver.findElement(By.className("assign-now-text")).click();
            boolean aasignThisPopup = Driver.driver.findElement(By.className("ir-ls-assign-dialog-header-wrapper")).isDisplayed();
            if (aasignThisPopup == false) {
                Assert.fail("\"Assign This\" pop up is not displayed.");
            }
            String markedTask = Driver.driver.findElement(By.cssSelector("span[data-localize='gradedLabel']")).getText();
            if (!markedTask.equals("Marked Task")) {
                Assert.fail("\"Assessment Task\" text is not changed to \"Marked Task\" text in Third row.");
            }
            WebElement markedTaskCheckBox = Driver.driver.findElement(By.xpath(".//div[@class='ir-ls-assign-dialog-gradable-label-check']"));
            markedTaskCheckBox.click();
            String markbookWeighting = Driver.driver.findElement(By.xpath(".//*[@class='ir-ls-assignment-categories-wrapper ir-ls-assignment-categories-content-item']")).getText();
            System.out.println("markbookWeighting::" + markbookWeighting);
            if (!markbookWeighting.contains("Markbook Weighting:")) {
                Assert.fail("\"Gradebook weighting\" text is not changed to \"Markbook Weighting\" text.");
            }
        } catch (Exception e) {
            Assert.fail("Exception in testcase assignThisPopupForCustomAssignment in class AssignThisPopupForAssignments", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void assignThispopupForUsePreCreatedAssignment() {
        try {
            String dataIndex = "16";
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", dataIndex);
            System.out.println("assessmentname::" + assessmentname);
            Driver.startDriver();
            new Assignment().create(16);
            new LoginUsingLTI().ltiLogin("16");
            new Navigator().NavigateTo("New Assignment");
            Driver.driver.findElement(By.cssSelector("div[class='ls-inst-dashboard-assignment-popup-button ls--question-banks-view']")).click();
            Thread.sleep(1000);
            Driver.driver.findElement(By.id("all-resource-search-textarea")).sendKeys(assessmentname);
            Thread.sleep(1000);
            Driver.driver.findElement(By.id("all-resource-search-button")).click();
            List<WebElement> assignThis = Driver.driver.findElements(By.className("assign-this"));
            assignThis.get(0).click();
            boolean assignThispopup = Driver.driver.findElement(By.className("ir-ls-assign-dialog-header-wrapper")).isDisplayed();
            if (assignThispopup == false) {
                Assert.fail("\"Assign This\" pop up is not displayed.");
            }
            String markedTask = Driver.driver.findElement(By.cssSelector("span[data-localize='gradedLabel']")).getText();
            if (!markedTask.equals("Marked Task")) {
                Assert.fail("\"Assessment Task\" text is not changed to \"Marked Task\" text in Third row.");
            }
            WebElement markedTaskCheckBox = Driver.driver.findElement(By.xpath(".//div[@class='ir-ls-assign-dialog-gradable-label-check']"));
            markedTaskCheckBox.click();
            String markbookWeighting = Driver.driver.findElement(By.xpath(".//*[@class='ir-ls-assignment-categories-wrapper ir-ls-assignment-categories-content-item']")).getText();
            System.out.println("markbookWeighting::" + markbookWeighting);
            if (!markbookWeighting.contains("Markbook Weighting:")) {
                Assert.fail("\"Gradebook weighting\" text is not changed to \"Markbook Weighting\" text.");
            }

        } catch (Exception e) {
            Assert.fail("Exception in testcase assignThispopupForUsePreCreatedAssignment in class AssignThisPopupForAssignments", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void assignThispopupForFileBasedAssignment() {
        try {
            Driver.startDriver();
            new Assignment().create(22);
            new LoginUsingLTI().ltiLogin("22");
            new Assignment().createFileBasedAssignmentInstructor("22");
            Thread.sleep(1000);
            Driver.driver.findElement(By.className("assign-now-text")).click();
            boolean assignThispopup = Driver.driver.findElement(By.className("ir-ls-assign-dialog-header-wrapper")).isDisplayed();
            if (assignThispopup == false) {
                Assert.fail("\"Assign This\" pop up is not displayed.");
            }
            String markedTask = Driver.driver.findElement(By.cssSelector("span[data-localize='gradedLabel']")).getText();
            if (!markedTask.equals("Marked Task")) {
                Assert.fail("\"Assessment Task\" text is not changed to \"Marked Task\" text in Third row.");
            }
            WebElement markedTaskCheckBox = Driver.driver.findElement(By.xpath(".//div[@class='ir-ls-assign-dialog-gradable-label-check']"));
            markedTaskCheckBox.click();
            String markbookWeighting = Driver.driver.findElement(By.xpath(".//*[@class='ir-ls-assignment-categories-wrapper ir-ls-assignment-categories-content-item']")).getText();
            System.out.println("markbookWeighting::" + markbookWeighting);
            if (!markbookWeighting.contains("Markbook Weighting:")) {
                Assert.fail("\"Gradebook weighting\" text is not changed to \"Markbook Weighting\" text.");
            }
        } catch (Exception e) {
            Assert.fail("Exception in testcase assignThispopupForFileBasedAssignment in class AssignThisPopupForAssignments", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void assignThispopupForStaticAssessmentAssignAsAssignment() {
        try {
            Driver.startDriver();
            new Assignment().create(28);
            new LoginUsingLTI().ltiLogin("28");
            new Click().clickBycssselector("a[class='ls-chapter-navigation-icon ls-toc-sprite ls-study-plan']");
            Thread.sleep(5000);
            List<WebElement> eTextbookAssignThis = Driver.driver.findElements(By.xpath(".//*[@class='assessment-card']/li/div/div[@class='ls-inner-arw']"));
            int size = eTextbookAssignThis.size();
            System.out.println("size::" + size);
            eTextbookAssignThis.get(size - 1).click();
            Thread.sleep(1000);
            new Click().clickBycssselector("div[class='toc-assign-this open-assign-window']");
            Thread.sleep(1000);
            boolean assignThispopup = Driver.driver.findElement(By.className("ir-ls-assign-dialog-header-wrapper")).isDisplayed();
            if (assignThispopup == false) {
                Assert.fail("\"Assign This\" pop up is not displayed.");
            }
            String markedTask = Driver.driver.findElement(By.cssSelector("span[data-localize='gradedLabel']")).getText();
            if (!markedTask.equals("Marked Task")) {
                Assert.fail("\"Assessment Task\" text is not changed to \"Marked Task\" text in Third row.");
            }
            WebElement markedTaskCheckBox = Driver.driver.findElement(By.xpath(".//div[@class='ir-ls-assign-dialog-gradable-label-check']"));
            markedTaskCheckBox.click();
            String markbookWeighting = Driver.driver.findElement(By.xpath(".//*[@class='ir-ls-assignment-categories-wrapper ir-ls-assignment-categories-content-item']")).getText();
            System.out.println("markbookWeighting::" + markbookWeighting);
            if (!markbookWeighting.contains("Markbook Weighting:")) {
                Assert.fail("\"Gradebook weighting\" text is not changed to \"Markbook Weighting\" text.");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase assignThispopupForStaticAssessmentAssignAsAssignment in class AssignThisPopupForAssignments", e);
        }
    }

    @Test(priority = 5,enabled = true)
    public void assignThispopupForAssignmentTab()
    {
        try
        {
            Driver.startDriver();
            String dataIndex = "40";
            String customAssignmentName = ReadTestData.readDataByTagName("", "customAssignmentName",dataIndex);
            System.out.println("customAssignmentName::" + customAssignmentName);
            new Assignment().create(40);
            new LoginUsingLTI().ltiLogin("40");
            new Assignment().createCustomeAssignment("40"); //Create Custom Assignment
            Thread.sleep(1000);
            new Click().clickBycssselector("a[class='ls-chapter-navigation-icon ls-toc-sprite ls-study-plan']");
            Thread.sleep(5000);
            new Click().clickBycssselector("i[class='close-study-plan-icon close-study-plan']");
            Thread.sleep(3000);
            Driver.driver.findElement(By.cssSelector("span[title='Assignments']")).click();
            new Assignment().assignmentScrollDownAndClickAssign(customAssignmentName);
            boolean assignThispopup = Driver.driver.findElement(By.className("ir-ls-assign-dialog-header-wrapper")).isDisplayed();
            if (assignThispopup == false) {
                Assert.fail("\"Assign This\" pop up is not displayed.");
            }
            String markedTask = Driver.driver.findElement(By.cssSelector("span[data-localize='gradedLabel']")).getText();
            if (!markedTask.equals("Marked Task")) {
                Assert.fail("\"Assessment Task\" text is not changed to \"Marked Task\" text in Third row.");
            }
            WebElement markedTaskCheckBox = Driver.driver.findElement(By.xpath(".//div[@class='ir-ls-assign-dialog-gradable-label-check']"));
            markedTaskCheckBox.click();
            String markbookWeighting = Driver.driver.findElement(By.xpath(".//*[@class='ir-ls-assignment-categories-wrapper ir-ls-assignment-categories-content-item']")).getText();
            System.out.println("markbookWeighting::" + markbookWeighting);
            if (!markbookWeighting.contains("Markbook Weighting:")) {
                Assert.fail("\"Gradebook weighting\" text is not changed to \"Markbook Weighting\" text.");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase assignThispopupForAssignmentTab in class AssignThisPopupForAssignments", e);
        }
    }

    @Test(priority = 6,enabled = true)
    public void assignThispopupForMyQuestionBank()
    {
        try
        {
            Driver.startDriver();
            String dataIndex = "47";
            String customAssignmentName = ReadTestData.readDataByTagName("", "customAssignmentName",dataIndex);
            System.out.println("customAssignmentName::" + customAssignmentName);
            new Assignment().create(47);
            new LoginUsingLTI().ltiLogin("47");
            new Assignment().createCustomeAssignment("47"); //Create Custom Assignment
            Thread.sleep(1000);
            new Navigator().NavigateTo("My Question Bank");
            new Click().clickByclassname("assign-this");
            boolean assignThispopup = Driver.driver.findElement(By.className("ir-ls-assign-dialog-header-wrapper")).isDisplayed();
            if (assignThispopup == false) {
                Assert.fail("\"Assign This\" pop up is not displayed.");
            }
            String markedTask = Driver.driver.findElement(By.cssSelector("span[data-localize='gradedLabel']")).getText();
            if (!markedTask.equals("Marked Task")) {
                Assert.fail("\"Assessment Task\" text is not changed to \"Marked Task\" text in Third row.");
            }
            WebElement markedTaskCheckBox = Driver.driver.findElement(By.xpath(".//div[@class='ir-ls-assign-dialog-gradable-label-check']"));
            markedTaskCheckBox.click();
            String markbookWeighting = Driver.driver.findElement(By.xpath(".//*[@class='ir-ls-assignment-categories-wrapper ir-ls-assignment-categories-content-item']")).getText();
            System.out.println("markbookWeighting::" + markbookWeighting);
            if (!markbookWeighting.contains("Markbook Weighting:")) {
                Assert.fail("\"Gradebook weighting\" text is not changed to \"Markbook Weighting\" text.");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase assignThispopupForMyQuestionBank in class AssignThisPopupForAssignments", e);
        }
    }

    @Test(priority = 7,enabled = true)
    public void assignThiPopupForUpdateCustomAssignment()
    {
        try
        {
            int dataIndex = 53;
            Driver.startDriver();
            new Assignment().create(53);
            new LoginUsingLTI().ltiLogin("53");
            Thread.sleep(1000);
            new Assignment().createCustomeAssignment("53");
            new Assignment().assignCustomeOrFileBasedAssignment(53);

            new Navigator().NavigateTo("Current Assignments");
            List<WebElement> updateAssignemnt = Driver.driver.findElements(By.cssSelector("span[title='Update Assignment']"));
            updateAssignemnt.get(0).click();
            boolean reAssign = Driver.driver.findElement(By.className("assignment-update-reassign")).isDisplayed();
            if(reAssign==false)
            {
                Assert.fail("Click on \"Update Assignment\" link for assignment is not Navigated to Update assignment page");
            }
            new Click().clickByclassname("re-assign-text");
            boolean assignThispopup = Driver.driver.findElement(By.className("ir-ls-assign-dialog-header-wrapper")).isDisplayed();
            if (assignThispopup == false) {
                Assert.fail("\"Assign This\" pop up is not displayed.");
            }
            String markedTask = Driver.driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label ir-ls-assign-dialog-label']")).getText();
            System.out.println("markedTask::" + markedTask);
            if (!markedTask.trim().equals("Marked Task:")) {
                Assert.fail("\"Assessment Task\" text is not changed to \"Marked Task\" text in Third row.");
            }
            String markbookWeighting = Driver.driver.findElement(By.xpath(".//*[@class='ir-ls-assignment-categories-wrapper ir-ls-assignment-categories-content-item']")).getText();
            System.out.println("markbookWeighting::" + markbookWeighting);
            if (!markbookWeighting.contains("Markbook Weighting:")) {
                Assert.fail("\"Gradebook weighting\" text is not changed to \"Markbook Weighting\" text.");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase assignThiPopupForUpdateCustomAssignment in class AssignThisPopupForAssignments", e);
        }
    }

    @Test(priority = 8, enabled = true)
    public void assignThispopupForUpdateFileBasedAssignment()
    {
        try
        {
            Driver.startDriver();
            new LoginUsingLTI().ltiLogin("60");
            new Assignment().createFileBasedAssignmentInstructor("60");
            new Assignment().assignCustomeOrFileBasedAssignment(60);
            new Navigator().NavigateTo("Current Assignments");
            List<WebElement> updateAssignemnt = Driver.driver.findElements(By.cssSelector("span[title='Update Assignment']"));
            updateAssignemnt.get(0).click();
            boolean assignThispopup = Driver.driver.findElement(By.className("ir-ls-assign-dialog-header-wrapper")).isDisplayed();
            if (assignThispopup == false) {
                Assert.fail("\"Assign This\" pop up is not displayed.");
            }
            String markedTask = Driver.driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label ir-ls-assign-dialog-label']")).getText();
            System.out.println("markedTask::" + markedTask);
            if (!markedTask.trim().equals("Marked Task:")) {
                Assert.fail("\"Assessment Task\" text is not changed to \"Marked Task\" text in Third row.");
            }
            String markbookWeighting = Driver.driver.findElement(By.xpath("./*//*[@class='ir-ls-assignment-categories-wrapper ir-ls-assignment-categories-content-item']")).getText();
            System.out.println("markbookWeighting::" + markbookWeighting);
            if (!markbookWeighting.contains("Markbook Weighting:")) {
                Assert.fail("\"Gradebook weighting\" text is not changed to \"Markbook Weighting\" text.");
            }

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase assignThispopupForUpdateFileBasedAssignment in class AssignThisPopupForAssignments", e);
        }
    }

    @Test(priority = 9,enabled = true)
    public void assignThispopupForEditAssignment()
    {
        try
        {
            String dataIndex = "66";
            String customAssignmentName = ReadTestData.readDataByTagName("", "customAssignmentName",dataIndex);
            Driver.startDriver();
            new Assignment().create(66);
            new LoginUsingLTI().ltiLogin("66");
            new Assignment().createCustomeAssignment("66");
            new Navigator().NavigateTo("My Question Bank");
            new Click().clickByid("my-resource-search-textarea");
            Driver.driver.findElement(By.id("my-resource-search-textarea")).sendKeys(customAssignmentName);
            new Click().clickByid("my-resource-search-button");
            List<WebElement> editThis = Driver.driver.findElements(By.cssSelector("span[title='Edit This']"));
            editThis.get(0).click();
            new Click().clickByclassname("assign-now-text");
            boolean assignThispopup = Driver.driver.findElement(By.className("ir-ls-assign-dialog-header-wrapper")).isDisplayed();
            if (assignThispopup == false) {
                Assert.fail("\"Assign This\" pop up is not displayed.");
            }
            String markedTask = Driver.driver.findElement(By.cssSelector("div[class='ir-ls-assign-dialog-gradable-label ir-ls-assign-dialog-label']")).getText();
            System.out.println("markedTask::" + markedTask);
            if (!markedTask.trim().equals("Marked Task:")) {
                Assert.fail("\"Assessment Task\" text is not changed to \"Marked Task\" text in Third row.");
            }
            WebElement markedTaskCheckBox = Driver.driver.findElement(By.xpath(".//div[@class='ir-ls-assign-dialog-gradable-label-check']"));
            markedTaskCheckBox.click();
            String markbookWeighting = Driver.driver.findElement(By.xpath(".//*[@class='ir-ls-assignment-categories-wrapper ir-ls-assignment-categories-content-item']")).getText();
            System.out.println("markbookWeighting::" + markbookWeighting);
            if (!markbookWeighting.contains("Markbook Weighting:")) {
                Assert.fail("\"Gradebook weighting\" text is not changed to \"Markbook Weighting\" text.");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase assignThispopupForEditAssignment in class AssignThisPopupForAssignments", e);
        }
    }
}