package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT20.R201;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.NewAssignment;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.QuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by priyanka on 4/17/2015.
 */
public class ReservedForAssignmentCreatedByCmsUser extends Driver {
    @Test(priority = 1, enabled = true)
    public void instructorShouldBeAbleToTryAnyAssignmentFromAssignmentSummary() {
        try {
            //tc row no 83
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "83");
            new Assignment().create(83); //create assignment
            new LoginUsingLTI().ltiLogin("83");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            questionBank.getQuestionBankTitle().click();//click on question bank
            Thread.sleep(3000);
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Assert.assertEquals(questionBank.tryItIcon.get(0).isDisplayed(), true, "Try it icon is not available");
            String parentWindow = driver.getWindowHandle();
            questionBank.tryItIcon.get(0).click();//Click on tryit icon
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(questionBank.wileyLogo.isDisplayed(), true, "Wiley Plus logo is not displaying at left side header");
            Assert.assertEquals(questionBank.courseName.isDisplayed(), true, "Course name is not displaying at middle part of header");
            Assert.assertEquals(currentAssignments.assignmentNamePreviewPage.getText(), "(P1) Assignment_IT20_201_83", "Assignment name with short name in the next line below the header is not displaying");
            Assert.assertEquals(currentAssignments.summaryDropDown.isDisplayed(), true, "Summary drop-down on the right corner of assignment name line is not present");
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "first question of the assignment is not displaying by default");
            Assert.assertEquals(currentAssignments.getQuestionLabel().getText(), "True False Test Question for IT20_201_83", "first question of the assignment is not displaying by default");
            Assert.assertEquals(currentAssignments.hint_Button.getText(), "Hint", "Hint button is not displaying in buttom footer");
            Assert.assertEquals(currentAssignments.solution_Button.getText(), "Solution", "solution button is not displaying in buttom footer");
            Assert.assertEquals(currentAssignments.checkAnswer_Button.getText(), "Check Answer", "check answer button is not displaying in buttom footer");
            Assert.assertEquals(currentAssignments.reportContentError_Link.isDisplayed(), true, "Report Content Error' icon at the right side bottom corner at footer is not present.");
            Assert.assertEquals(currentAssignments.performanceTab.isDisplayed(), true, "performance tab at the right side of the question part is not displaying");
            Assert.assertEquals(currentAssignments.performanceTabImage.isDisplayed(), true, "performance tab at the right side  of the question part with minimize (-) option is not displaying");
            Assert.assertEquals(currentAssignments.userThumbNail.isDisplayed(), true, "default thumbnail at the right side of header is not displaying");

            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "first question of the assignment is not displaying by default");
            String assignmentName = currentAssignments.assignmentNamePreviewPage.getText();
            driver.switchTo().window(parentWindow);
            currentAssignments.tryIt_Link.get(0).click();//click on try it link
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);


            }
            String assignmentName1 = currentAssignments.assignmentNamePreviewPage.getText();
            if (!assignmentName.equals(assignmentName1)) {
                Assert.fail("same window is not getting maximized");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToTryAnyAssignmentFromAssignmentSummary in class ReservedForAssignmentCreatedByCmsUser", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void mentorShouldBeAbleToTryAnyAssignmentFromAssignmentSummary() {
        try {
            //tc row no 83
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "831");
            new LoginUsingLTI().ltiLogin("831");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            questionBank.getQuestionBankTitle().click();//click on question bank
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Thread.sleep(4000);
            Assert.assertEquals(questionBank.tryItIcon.get(0).isDisplayed(), true, "Try it icon is not available");
            String parentWindow = driver.getWindowHandle();
            questionBank.tryItIcon.get(0).click();//Click on tryit icon
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(questionBank.wileyLogo.isDisplayed(), true, "Wiley Plus logo is not displaying at left side header");
            Assert.assertEquals(questionBank.courseName.isDisplayed(), true, "Course name is not displaying at middle part of header");
            Assert.assertEquals(currentAssignments.assignmentNamePreviewPage.getText(), "(P1) Assignment_IT20_201_83", "Assignment name with short name in the next line below the header is not displaying");
            Assert.assertEquals(currentAssignments.summaryDropDown.isDisplayed(), true, "Summary drop-down on the right corner of assignment name line is not present");
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "first question of the assignment is not displaying by default");
            Assert.assertEquals(currentAssignments.getQuestionLabel().getText(), "True False Test Question for IT20_201_83", "first question of the assignment is not displaying by default");
            Assert.assertEquals(currentAssignments.hint_Button.getText(), "Hint", "Hint button is not displaying in buttom footer");
            Assert.assertEquals(currentAssignments.solution_Button.getText(), "Solution", "solution button is not displaying in buttom footer");
            Assert.assertEquals(currentAssignments.checkAnswer_Button.getText(), "Check Answer", "check answer button is not displaying in buttom footer");
            Assert.assertEquals(currentAssignments.reportContentError_Link.isDisplayed(), true, "Report Content Error' icon at the right side bottom corner at footer is not present.");
            Assert.assertEquals(currentAssignments.performanceTab.isDisplayed(), true, "performance tab at the right side of the question part is not displaying");
            Assert.assertEquals(currentAssignments.performanceTabImage.isDisplayed(), true, "performance tab at the right side  of the question part with minimize (-) option is not displaying");
            Assert.assertEquals(currentAssignments.userThumbNail.isDisplayed(), true, "default thumbnail at the right side of header is not displaying");

            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "first question of the assignment is not displaying by default");
            String assignmentName = currentAssignments.assignmentNamePreviewPage.getText();
            driver.switchTo().window(parentWindow);
            currentAssignments.tryIt_Link.get(0).click();//click on try it link
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);


            }
            String assignmentName1 = currentAssignments.assignmentNamePreviewPage.getText();
            if (!assignmentName.equals(assignmentName1)) {
                Assert.fail("same window is not getting maximized");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case mentorShouldBeAbleToTryAnyAssignmentFromAssignmentSummary in class ReservedForAssignmentCreatedByCmsUser", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void tryItLinkForDifferentQuestionBasedAssignment() {
        try {
            //tc row no 108
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            new LoginUsingLTI().ltiLogin("108");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            questionBank.getQuestionBankTitle().click();//click on question bank
            Thread.sleep(2000);
            String parentWindow = driver.getWindowHandle();
            questionBank.tryItIcon.get(0).click();//click on 1st try-it
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }

            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            Assert.assertEquals(currentAssignments.assignmentNamePreviewPage.isDisplayed(), true, "1st browser pop up is not opened");
            driver.switchTo().window(parentWindow);
            questionBank.tryItIcon.get(1).click();//click on 1st try-it
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            Assert.assertEquals(currentAssignments.assignmentNamePreviewPage.isDisplayed(), true, "2nd browser pop up is not opened");
            driver.switchTo().window(parentWindow);
            questionBank.tryItIcon.get(2).click();//click on 1st try-it
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            Assert.assertEquals(currentAssignments.assignmentNamePreviewPage.isDisplayed(), true, "3rd browser pop up is not opened");
            driver.switchTo().window(parentWindow);
            questionBank.tryItIcon.get(3).click();//click on 1st try-it
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            Assert.assertEquals(currentAssignments.assignmentNamePreviewPage.isDisplayed(), true, "4th browser pop up is not opened");
            driver.switchTo().window(parentWindow);
            questionBank.tryItIcon.get(4).click();//click on 1st try-it
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            Assert.assertEquals(currentAssignments.assignmentNamePreviewPage.isDisplayed(), true, "5th browser pop up is not opened");

        } catch (Exception e) {
            Assert.fail("Exception in test case tryItLinkForDifferentQuestionBasedAssignment in class ReservedForAssignmentCreatedByCmsUser", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void tryItLinkForFileBasedAssignment() {
        try {
            //tc row no 86
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "86");
            new Assignment().createFileBasedAssessment(86);
            new LoginUsingLTI().ltiLogin("86");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            Thread.sleep(3000);
            questionBank.getQuestionBankTitle().click();//click on question bank
            Thread.sleep(3000);
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Thread.sleep(6000);
            List<WebElement> list = driver.findElements(By.xpath("//*[contains(text(),'Try it')]"));
            if (list.size() > 0) {
                Assert.fail("Try it link is available");
            }
        } catch (Exception e) {
            Assert.fail("Exception in test case tryItLinkForFileBasedAssignment in class ReservedForAssignmentCreatedByCmsUser", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void instructorShouldBeAbleToCheckAnswerForQuestion() {
        try {
            //tc row no 110
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "110");
            new Assignment().create(110); //create assignment
            new Assignment().addQuestions(110, "writeboard", "");
            new Assignment().addQuestions(110, "essay", "");
            new Assignment().addQuestions(110, "audio", "");
            new LoginUsingLTI().ltiLogin("110");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            Thread.sleep(3000);
            questionBank.getQuestionBankTitle().click();//click on question bank
            Thread.sleep(3000);
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Thread.sleep(6000);
            questionBank.tryItIcon.get(0).click();//click on 1st try-it
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }

            new WebDriverWait(driver,240).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            currentAssignments.next_Button.click();//click on next button
            Thread.sleep(2000);
            int found= driver.findElements(By.xpath("//div[text()='Check Answer']")).size();
            if(found!=0) {
                Assert.fail("Check Answer option is displayed");

            }
            Assert.assertEquals(currentAssignments.previous_Button.isDisplayed(),true,"Previous button is not available");
            currentAssignments.nextButton.click();//click on next button
            Thread.sleep(2000);
            int found1= driver.findElements(By.xpath("//div[text()='Check Answer']")).size();
            if(found1!=0) {
                Assert.fail("Check Answer option is displayed");

            }
            currentAssignments.nextButton.click();//click on next button
            Thread.sleep(2000);
            int found2= driver.findElements(By.xpath("//div[text()='Check Answer']")).size();
            if(found2!=0) {
                Assert.fail("Check Answer option is displayed");

            }

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToCheckAnswerForQuestion in class ReservedForAssignmentCreatedByCmsUser", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void mentorShouldBeAbleToCheckAnswerForQuestion() {
        try {
            //tc row no 110
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "110");
            new LoginUsingLTI().ltiLogin("1101");//log in as mentor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            questionBank.getQuestionBankTitle().click();//click on question bank
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Thread.sleep(6000);
            questionBank.tryItIcon.get(0).click();//click on 1st try-it
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }

            new WebDriverWait(driver,240).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            currentAssignments.next_Button.click();//click on next button
            Thread.sleep(2000);
            int found= driver.findElements(By.xpath("//div[text()='Check Answer']")).size();
            if(found!=0) {
                Assert.fail("Check Answer option is displayed");

            }
            Assert.assertEquals(currentAssignments.previous_Button.isDisplayed(),true,"Previous button is not available");
            currentAssignments.nextButton.click();//click on next button
            Thread.sleep(2000);
            int found1= driver.findElements(By.xpath("//div[text()='Check Answer']")).size();
            if(found1!=0) {
                Assert.fail("Check Answer option is displayed");

            }
            currentAssignments.nextButton.click();//click on next button
            Thread.sleep(2000);
            int found2= driver.findElements(By.xpath("//div[text()='Check Answer']")).size();
            if(found2!=0) {
                Assert.fail("Check Answer option is displayed");

            }

        } catch (Exception e) {
            Assert.fail("Exception in test case mentorShouldBeAbleToCheckAnswerForQuestion in class ReservedForAssignmentCreatedByCmsUser", e);
        }
    }

    @Test(priority = 7,enabled = true)
    public void instructorShouldBeAbleToCheckAnswerFromQuestionBank() {
        try {
            //tc row no 115
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "115");
            new LoginUsingLTI().ltiLogin("115");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            questionBank.getQuestionBankTitle().click();//click on question bank
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Thread.sleep(6000);
            questionBank.tryItIcon.get(0).click();//click on 1st try-it
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "first question of the assignment is not displaying by default");
            Assert.assertEquals(currentAssignments.hint_Button.getText(),"Hint","Hint button is not displaying in buttom footer");
            Assert.assertEquals(currentAssignments.solution_Button.getText(),"Solution","solution button is not displaying in buttom footer");
            Assert.assertEquals(currentAssignments.checkAnswer_Button.getText(),"Check Answer","check answer button is not displaying in buttom footer");
            currentAssignments.answerChoice.get(0).click();//click on option 'A'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Assert.assertEquals(currentAssignments.footerText.getText(), "You got it right.", "You got it right message is not displaying");
            currentAssignments.answerChoice.get(0).click();//click on option 'B'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Thread.sleep(3000);
            Assert.assertEquals(currentAssignments.footerText.getText(),"You got it wrong.","You got it wrong message is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToCheckAnswerFromQuestionBank in class ReservedForAssignmentCreatedByCmsUser", e);
        }
    }

    @Test(priority = 8,enabled = true)
    public void instructorShouldBeAbleToCheckAnswerPartiallyCorrect() {
        try {
            //tc row no 117
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "117");
            new Assignment().create(117); //create assignment
            new Assignment().addQuestions(117, "multipleselection", "");
            new LoginUsingLTI().ltiLogin("117");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            questionBank.getQuestionBankTitle().click();//click on question bank
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Thread.sleep(6000);
            questionBank.tryItIcon.get(0).click();//click on 1st try-it
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            currentAssignments.next_Button.click();//click on next button
            Thread.sleep(2000);
            currentAssignments.answerOption_MultipleChoice.get(0).click();//click on option 'A'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Thread.sleep(3000);
            Assert.assertEquals(currentAssignments.footerText.getText(), "You got it partially correct.", "You got it partially correct is not displaying");
            currentAssignments.previous_Button.click();//click on previous button
            Thread.sleep(2000);
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Assert.assertEquals(currentAssignments.footerText.getText(), "Choose the answer", "Choose the answer is not displaying");
            currentAssignments.next_Button.click();//click on next button
            Thread.sleep(2000);
            currentAssignments.answerOption_MultipleChoice.get(2).click();//click on option 'C'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Thread.sleep(3000);
            Assert.assertEquals(currentAssignments.footerText.getText(), "You got it wrong.", "You got it wrong is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToCheckAnswerPartiallyCorrect in class InstructorShouldBeAbleToTryAnyAssignment", e);
        }
    }

    @Test(priority = 9,enabled = true)
    public void mentorShouldBeAbleToCheckAnswerFromQuestionBank() {
        try {
            //tc row no 115
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "1151");
            new LoginUsingLTI().ltiLogin("1151");//log in as mentor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            questionBank.getQuestionBankTitle().click();//click on question bank
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Thread.sleep(6000);
            questionBank.tryItIcon.get(0).click();//click on 1st try-it
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "first question of the assignment is not displaying by default");
            Assert.assertEquals(currentAssignments.hint_Button.getText(),"Hint","Hint button is not displaying in buttom footer");
            Assert.assertEquals(currentAssignments.solution_Button.getText(),"Solution","solution button is not displaying in buttom footer");
            Assert.assertEquals(currentAssignments.checkAnswer_Button.getText(),"Check Answer","check answer button is not displaying in buttom footer");
            currentAssignments.answerChoice.get(0).click();//click on option 'A'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Assert.assertEquals(currentAssignments.footerText.getText(), "You got it right.", "You got it right message is not displaying");
            currentAssignments.answerChoice.get(0).click();//click on option 'B'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Thread.sleep(3000);
            Assert.assertEquals(currentAssignments.footerText.getText(),"You got it wrong.","You got it wrong message is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case mentorShouldBeAbleToCheckAnswerFromQuestionBank in class ReservedForAssignmentCreatedByCmsUser", e);
        }
    }

    @Test(priority = 10,enabled = true)
    public void mentorShouldBeAbleToCheckAnswerPartiallyCorrect() {
        try {
            //tc row no 117
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "1171");
            new LoginUsingLTI().ltiLogin("1171");//log in as mentor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            questionBank.getQuestionBankTitle().click();//click on question bank
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Thread.sleep(6000);
            questionBank.tryItIcon.get(0).click();//click on 1st try-it
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            currentAssignments.next_Button.click();//click on next button
            Thread.sleep(2000);
            currentAssignments.answerOption_MultipleChoice.get(0).click();//click on option 'A'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Thread.sleep(3000);
            Assert.assertEquals(currentAssignments.footerText.getText(), "You got it partially correct.", "You got it partially correct is not displaying");
            currentAssignments.previous_Button.click();//click on previous button
            Thread.sleep(2000);
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Assert.assertEquals(currentAssignments.footerText.getText(), "Choose the answer", "Choose the answer is not displaying");
            currentAssignments.next_Button.click();//click on next button
            Thread.sleep(2000);
            currentAssignments.answerOption_MultipleChoice.get(2).click();//click on option 'C'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Thread.sleep(3000);
            Assert.assertEquals(currentAssignments.footerText.getText(), "You got it wrong.", "You got it wrong is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case mentorShouldBeAbleToCheckAnswerPartiallyCorrect in class ReservedForAssignmentCreatedByCmsUser", e);
        }
    }


    @Test(priority = 11,enabled = true)
    public void instructorShouldBeAbleToNavigatePreviousAndNext() {
        try {
            //tc row no 119
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "119");
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            new Assignment().create(119); //create assignment
            new Assignment().addQuestions(119, "truefalse", "");
            new Assignment().addQuestions(119, "truefalse", "");
            new Assignment().addQuestions(119, "truefalse", "");
            new Assignment().addQuestions(119, "truefalse", "");
            new LoginUsingLTI().ltiLogin("119");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            questionBank.getQuestionBankTitle().click();//click on question bank
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Thread.sleep(6000);
            questionBank.tryItIcon.get(0).click();//click on 1st try-it
            String parent=driver.getWindowHandle();
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "first question of the assignment is not displaying by default");
            Assert.assertEquals(currentAssignments.next_Button.isDisplayed(),true, "Next button is not available");
            int found= driver.findElements(By.xpath("//div[text()='Previous']")).size();
            if(found!=0) {
                Assert.fail("previous button is displayed");

            }
            Assert.assertEquals(currentAssignments.reportContentError_Link.isDisplayed(), true, "Report Content Error' icon at the right side bottom corner at footer is not present.");
            currentAssignments.next_Button.click();//click on next
           new UIElement().waitAndFindElement(By.id("question-try-it-previous"));
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q2:", "It is not navigated to the respective question");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q3");
            Thread.sleep(2000);
            currentAssignments.previous_Button.click();//click on previous button
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q2:", "It is not navigated to the respective question");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q5");
            Assert.assertEquals(currentAssignments.finishButton.isDisplayed(), true, "Finish button is not available");
            currentAssignments.finishButton.click();//click on finish button
            driver.switchTo().window(parent);
            String url=driver.getCurrentUrl();
            if(url.contains("tryItAssessmentOrAssignment"))
            {
                Assert.fail("Browser popup is not closed after click on finish button");
            }


        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToNavigatePreviousAndNext in class ReservedForAssignmentCreatedByCmsUser", e);
        }
    }

    @Test(priority = 12,enabled = true)
    public void mentorShouldBeAbleToNavigatePreviousAndNext() {
        try {
            //tc row no 119
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "1191");
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            new LoginUsingLTI().ltiLogin("1191");//log in as mentor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            questionBank.getQuestionBankTitle().click();//click on question bank
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Thread.sleep(6000);
            questionBank.tryItIcon.get(0).click();//click on 1st try-it
            String parent=driver.getWindowHandle();
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "first question of the assignment is not displaying by default");
            Assert.assertEquals(currentAssignments.next_Button.isDisplayed(),true, "Next button is not available");
            int found= driver.findElements(By.xpath("//div[text()='Previous']")).size();
            if(found!=0) {
                Assert.fail("previous button is displayed");

            }
            Assert.assertEquals(currentAssignments.reportContentError_Link.isDisplayed(), true, "Report Content Error' icon at the right side bottom corner at footer is not present.");
            currentAssignments.next_Button.click();//click on next
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q2:", "It is not navigated to the respective question");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q3");
            Thread.sleep(2000);
            currentAssignments.previous_Button.click();//click on previous button
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q2:", "It is not navigated to the respective question");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q5");
            Assert.assertEquals(currentAssignments.finishButton.isDisplayed(), true, "Finish button is not available");
            currentAssignments.finishButton.click();//click on finish button
            driver.switchTo().window(parent);
            String url=driver.getCurrentUrl();
            if(url.contains("tryItAssessmentOrAssignment"))
            {
                Assert.fail("Browser popup is not closed after click on finish button");
            }


        } catch (Exception e) {
            Assert.fail("Exception in test case mentorShouldBeAbleToNavigatePreviousAndNext in class ReservedForAssignmentCreatedByCmsUser", e);
        }
    }

    public void selectQuestion(String questionNo)
    {
        driver.findElement(By.xpath("//div[text()='"+questionNo+"']")).click();

    }
    @Test(priority = 13,enabled = true)
    public void instructorShouldBeAbleToNavigateToParticularQuestion() {
        try {
            //tc row no 127
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "127");
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            new LoginUsingLTI().ltiLogin("127");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            questionBank.getQuestionBankTitle().click();//click on question bank
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Thread.sleep(6000);
            questionBank.tryItIcon.get(0).click();//click on 1st try-it
            String parent=driver.getWindowHandle();
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "first question of the assignment is not displaying by default");
            Assert.assertEquals(currentAssignments.summaryDropDown.isDisplayed(),true,"Summary drop-down on the right corner of assignment name line is not present");
            currentAssignments.helpIconOnSummaryDropDown.click();//click on help icon on summary dropdown
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            Assert.assertEquals(currentAssignments.dropDownQuestion.get(4).isDisplayed(),true,"It should not opened dropdown followed by question");
            WebElement scroll=driver.findElement(By.xpath("//div[text()='Q5']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            selectQuestion("Q3");//select question 3
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q3:", "It is not navigated to the respective question");
            currentAssignments.answerChoice.get(0).click();//click on option 'A'
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q5");//select question 5
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q3");//select question 3
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q3:", "It is not navigated to the respective question");
            Assert.assertEquals(currentAssignments.answerChoice.get(0).isSelected(),false,"The answer is saved for that question");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToNavigateToParticularQuestion in class ReservedForAssignmentCreatedByCmsUser", e);
        }
    }

    @Test(priority = 14,enabled = true)
    public void mentorShouldBeAbleToNavigateToParticularQuestion() {
        try {
            //tc row no 127
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "1271");
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            new LoginUsingLTI().ltiLogin("1271");//log in as mentor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            questionBank.getQuestionBankTitle().click();//click on question bank
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Thread.sleep(6000);
            questionBank.tryItIcon.get(0).click();//click on 1st try-it
            String parent=driver.getWindowHandle();
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "first question of the assignment is not displaying by default");
            Assert.assertEquals(currentAssignments.summaryDropDown.isDisplayed(),true,"Summary drop-down on the right corner of assignment name line is not present");
            currentAssignments.helpIconOnSummaryDropDown.click();//click on help icon on summary dropdown
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            Assert.assertEquals(currentAssignments.dropDownQuestion.get(4).isDisplayed(),true,"It should not opened dropdown followed by question");
            WebElement scroll=driver.findElement(By.xpath("//div[text()='Q5']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            selectQuestion("Q3");//select question 3
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q3:", "It is not navigated to the respective question");
            currentAssignments.answerChoice.get(0).click();//click on option 'A'
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q5");//select question 5
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q3");//select question 3
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q3:", "It is not navigated to the respective question");
            Assert.assertEquals(currentAssignments.answerChoice.get(0).isSelected(),false,"The answer is saved for that question");

        } catch (Exception e) {
            Assert.fail("Exception in test case mentorShouldBeAbleToNavigateToParticularQuestion in class ReservedForAssignmentCreatedByCmsUser", e);
        }
    }

    @Test(priority = 15,enabled = true)
    public void instructorShouldBeAbleToViewQuestion() {
        try {
            //tc row no 133
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "133");
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            new Assignment().create(133);
            new Assignment().addQuestions(1331, "truefalse", "");
            new Assignment().addQuestions(1332, "truefalse", "");
            new LoginUsingLTI().ltiLogin("133");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            Thread.sleep(3000);
            questionBank.getQuestionBankTitle().click();//click on question bank
            Thread.sleep(3000);
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Thread.sleep(6000);
            questionBank.tryItIcon.get(0).click();//click on 1st try-it
            String parent=driver.getWindowHandle();
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "first question of the assignment is not displaying by default");
            Assert.assertEquals(currentAssignments.performanceTab.isDisplayed(),true,"performance tab at the right side of the question part is not displaying");
            Assert.assertEquals(currentAssignments.performanceTabImage.isDisplayed(),true,"performance tab at the right side of the question part with minimize (-) option is not displaying");
            Assert.assertEquals(currentAssignments.totalPoints.isDisplayed(),true,"Total points in performance tab at the righ side panel not displaying");
            Assert.assertEquals(currentAssignments.performanceInQuestion.isDisplayed(),true,"Perfomance in last 10 question not displaying");
            Assert.assertEquals(currentAssignments.performanceGraph.isDisplayed(),true,"blank graph below points available option as of CMS preview is not displaying");
            Assert.assertEquals(currentAssignments.difficultyBar.isDisplayed(),true,"difficulty bar is not available");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q2");//select question 2
            Thread.sleep(3000);
            String str=driver.findElement(By.className("cms-question-preview-sidebar-title-sectn")).getText();
            Assert.assertEquals(str,"Points Available : 5","5 points for question no 2 is not available");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q1");//select question 2
            Thread.sleep(3000);
            String low=currentAssignments.difficultyLevelBar.getAttribute("style");
            if(!low.contains("10%"))
                Assert.fail("Question with difficulty level low is not displaying");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q2");//select question 2
            Thread.sleep(3000);
            String medium=currentAssignments.difficultyLevelBar.getAttribute("style");
            if(!medium.contains("20%"))
                Assert.fail("Question with difficulty level medium is not displaying");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q3");//select question 2
            Thread.sleep(3000);
            String high=currentAssignments.difficultyLevelBar.getAttribute("style");
            if(!high.contains("30%"))
                Assert.fail("Question with difficulty level high is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToViewQuestion in class ReservedForAssignmentCreatedByCmsUser", e);
        }
    }

    @Test(priority = 16,enabled = true)
    public void mentorShouldBeAbleToViewQuestion() {
        try {
            //tc row no 133
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "133_1");
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            new LoginUsingLTI().ltiLogin("133_1");//log in as mentor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            questionBank.getQuestionBankTitle().click();//click on question bank
            driver.findElement(By.id("all-resource-search-textarea")).clear();
            driver.findElement(By.id("all-resource-search-textarea")).sendKeys("\"" + assignmentname + "\"");
            driver.findElement(By.cssSelector("#all-resource-search-button > span.ins-resource-search-sprite.instructor-resource-search-img")).click();
            Thread.sleep(6000);
            questionBank.tryItIcon.get(0).click();//click on 1st try-it
            String parent=driver.getWindowHandle();
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "first question of the assignment is not displaying by default");
            Assert.assertEquals(currentAssignments.performanceTab.isDisplayed(),true,"performance tab at the right side of the question part is not displaying");
            Assert.assertEquals(currentAssignments.performanceTabImage.isDisplayed(),true,"performance tab at the right side of the question part with minimize (-) option is not displaying");
            Assert.assertEquals(currentAssignments.totalPoints.isDisplayed(),true,"Total points in performance tab at the righ side panel not displaying");
            Assert.assertEquals(currentAssignments.performanceInQuestion.isDisplayed(),true,"Perfomance in last 10 question not displaying");
            Assert.assertEquals(currentAssignments.performanceGraph.isDisplayed(),true,"blank graph below points available option as of CMS preview is not displaying");
            Assert.assertEquals(currentAssignments.difficultyBar.isDisplayed(),true,"difficulty bar is not available");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q2");//select question 2
            Thread.sleep(3000);
            String str=driver.findElement(By.className("cms-question-preview-sidebar-title-sectn")).getText();
            Assert.assertEquals(str,"Points Available : 5","5 points for question no 2 is not available");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q1");//select question 2
            Thread.sleep(3000);
            String low=currentAssignments.difficultyLevelBar.getAttribute("style");
            if(!low.contains("10%"))
                Assert.fail("Question with difficulty level low is not displaying");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q2");//select question 2
            Thread.sleep(3000);
            String medium=currentAssignments.difficultyLevelBar.getAttribute("style");
            if(!medium.contains("20%"))
                Assert.fail("Question with difficulty level medium is not displaying");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q3");//select question 2
            Thread.sleep(3000);
            String high=currentAssignments.difficultyLevelBar.getAttribute("style");
            if(!high.contains("30%"))
                Assert.fail("Question with difficulty level high is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in test case mentorShouldBeAbleToViewQuestion in class ReservedForAssignmentCreatedByCmsUser", e);
        }
    }
}