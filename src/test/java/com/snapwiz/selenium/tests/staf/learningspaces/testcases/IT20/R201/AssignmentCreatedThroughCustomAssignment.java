package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT20.R201;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.QuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
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
import org.w3c.dom.Document;

/**
 * Created by priyanka on 4/21/2015.
 */
public class AssignmentCreatedThroughCustomAssignment extends Driver {
    @Test(priority = 1, enabled = true)
    public void instructorShouldBeAbleToTryAnyAssignmentFromStudy() {
        try {
            //tc row no 150
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "150");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "150");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            new LoginUsingLTI().ltiLogin("150");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("150");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            Assert.assertEquals(questionBank.tryItIcon.get(0).isDisplayed(), true, "Try it icon is not available");
            String parentWindow = driver.getWindowHandle();
            questionBank.tryItIcon.get(0).click();//Click on tryit icon
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(questionBank.wileyLogo.isDisplayed(), true, "Wiley Plus logo is not displaying at left side header");
            Assert.assertEquals(questionBank.courseName.isDisplayed(), true, "Course name is not displaying at middle part of header");
            String str=currentAssignments.assignmentNamePreviewPage.getText();
            System.out.println(str);
            if(!str.contains("New Name"))
            Assert.fail("Assignment name with short name in the next line below the header is not displaying");
            Assert.assertEquals(currentAssignments.summaryDropDown.isDisplayed(), true, "Summary drop-down on the right corner of assignment name line is not present");
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "first question of the assignment is not displaying by default");
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
            Assert.fail("Exception in test case instructorShouldBeAbleToTryAnyAssignmentFromStudy in class AssignmentCreatedThroughCustomAssignment", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void mentorShouldBeAbleToTryAnyAssignmentFromStudy() {
        try {
            //tc row no 150
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "1501");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "1501");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            new LoginUsingLTI().ltiLogin("1501");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("1501");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            Assert.assertEquals(questionBank.tryItIcon.get(0).isDisplayed(), true, "Try it icon is not available");
            String parentWindow = driver.getWindowHandle();
            questionBank.tryItIcon.get(0).click();//Click on tryit icon
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(questionBank.wileyLogo.isDisplayed(), true, "Wiley Plus logo is not displaying at left side header");
            Assert.assertEquals(questionBank.courseName.isDisplayed(), true, "Course name is not displaying at middle part of header");
            String str=currentAssignments.assignmentNamePreviewPage.getText();
            System.out.println(str);
            if(!str.contains("New Name"))
                Assert.fail("Assignment name with short name in the next line below the header is not displaying");
            Assert.assertEquals(currentAssignments.summaryDropDown.isDisplayed(), true, "Summary drop-down on the right corner of assignment name line is not present");
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "first question of the assignment is not displaying by default");
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
            Assert.fail("Exception in test case mentorShouldBeAbleToTryAnyAssignmentFromStudy in class AssignmentCreatedThroughCustomAssignment", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void tryItLinkForDifferentQuestionBasedCustomAssignment() {
        try {
            //tc row no 175
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "175");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "175");
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            new LoginUsingLTI().ltiLogin("175");//login as instructor
            new Navigator().NavigateTo("My Question Bank");//click on my question bank
            for (int i = 0; i <= 4; i++) {
                myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
                Thread.sleep(2000);
                new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
                new AssignLesson().selectQuestionForCustomAssignment("175");//select two question
                myQuestionBank.getAssignmentNameField().click();//click on name field
                myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
                myQuestionBank.getSaveForLater().click();//click on save for later
                Thread.sleep(5000);
                myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
                myQuestionBank.closeTab.click();//close custom assignment
                Thread.sleep(4000);
            }
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
            Assert.fail("Exception in test case tryItLinkForDifferentQuestionBasedCustomAssignment in class AssignmentCreatedThroughCustomAssignment", e);

        }
    }
    @Test(priority = 4, enabled = true)
     public void instructorShouldBeAbleToCheckAnswerForManualGradedQuestion() {
        try {
                   //tc row no 167
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "167");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "167");
            new Assignment().create(167); //create assignment
            new Assignment().addQuestions(167, "writeboard", "");
            new Assignment().addQuestions(167, "essay", "");
            new Assignment().addQuestions(167, "audio", "");
            new LoginUsingLTI().ltiLogin("167");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("167");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            Thread.sleep(3000);
            questionBank.tryItIcon.get(0).click();//Click on tryit icon
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
            int found1= driver.findElements(By.xpath("//div[text()='Check Answer']")).size();if(found1!=0) {
                        Assert.fail("Check Answer option is displayed");

                    }
            currentAssignments.nextButton.click();//click on next button
            Thread.sleep(2000);
            int found2= driver.findElements(By.xpath("//div[text()='Check Answer']")).size();
            if(found2!=0) {
                Assert.fail("Check Answer option is displayed");
            }

        } catch (Exception e) {
                    Assert.fail("Exception in test case instructorShouldBeAbleToCheckAnswerForManualGradedQuestion in class AssignmentCreatedThroughCustomAssignment", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void mentorShouldBeAbleToCheckAnswerForManualGradedQuestion() {
        try {
            //tc row no 167
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "1671");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "1671");
            new LoginUsingLTI().ltiLogin("1671");//log in as mentor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("1671");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            Thread.sleep(3000);
            questionBank.tryItIcon.get(0).click();//Click on tryit icon
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
            int found1= driver.findElements(By.xpath("//div[text()='Check Answer']")).size();if(found1!=0) {
                Assert.fail("Check Answer option is displayed");

            }
            currentAssignments.nextButton.click();//click on next button
            Thread.sleep(2000);
            int found2= driver.findElements(By.xpath("//div[text()='Check Answer']")).size();
            if(found2!=0) {
                Assert.fail("Check Answer option is displayed");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case mentorShouldBeAbleToCheckAnswerForManualGradedQuestion in class AssignmentCreatedThroughCustomAssignment", e);
        }
    }
    @Test(priority = 6,enabled = true)
    public void instructorShouldBeAbleToCheckAnswerFromQuestionBank() {
        try {
            //tc row no 177
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "177");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "177");
            new LoginUsingLTI().ltiLogin("177");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("177");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            Thread.sleep(3000);
            questionBank.tryItIcon.get(0).click();//Click on tryit icon
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
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.footerText.getText(), "You got it right.", "You got it right message is not displaying");
            currentAssignments.answerChoice.get(0).click();//click on option 'B'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Thread.sleep(3000);
            Assert.assertEquals(currentAssignments.footerText.getText(),"You got it wrong.","You got it wrong message is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToCheckAnswerFromQuestionBank in class AssignmentCreatedThroughCustomAssignment", e);
        }
    }


    @Test(priority = 7,enabled = true)
    public void mentorShouldBeAbleToCheckAnswerFromQuestionBank() {
        try {
            //tc row no 177
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "1771");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "1771");
            new LoginUsingLTI().ltiLogin("1771");//log in as mentor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");

            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("1771");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            Thread.sleep(3000);
            questionBank.tryItIcon.get(0).click();//Click on tryit icon
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
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.footerText.getText(), "You got it right.", "You got it right message is not displaying");
            currentAssignments.answerChoice.get(0).click();//click on option 'B'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Thread.sleep(3000);
            Assert.assertEquals(currentAssignments.footerText.getText(),"You got it wrong.","You got it wrong message is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case mentorShouldBeAbleToCheckAnswerFromQuestionBank in class AssignmentCreatedThroughCustomAssignment", e);
        }
    }

    @Test(priority = 8,enabled = true)
    public void instructorShouldBeAbleToCheckAnswerPartiallyCorrect() {
        try {
            //tc row no 184
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "184");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "184");
            new LoginUsingLTI().ltiLogin("184");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("Multiple Selection");
            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("184");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            Thread.sleep(3000);
            questionBank.tryItIcon.get(0).click();//Click on tryit icon
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            currentAssignments.answerOption_MultipleChoice.get(2).click();//click on option 'A'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Thread.sleep(3000);
            Assert.assertEquals(currentAssignments.footerText.getText(), "You got it partially correct.", "You got it partially correct is not displaying");
        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToCheckAnswerPartiallyCorrect in class AssignmentCreatedThroughCustomAssignment", e);
        }
    }

    @Test(priority = 9,enabled = true)
    public void instructorShouldBeAbleToCheckAnswerChooseAnswer() {
        try {
            //tc row no 185
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "185");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "185");
            new LoginUsingLTI().ltiLogin("185");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");

            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("185");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            Thread.sleep(3000);
            questionBank.tryItIcon.get(0).click();//Click on tryit icon
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Assert.assertEquals(currentAssignments.footerText.getText(), "Choose the answer", "Choose the answer is not displaying");
            currentAssignments.next_Button.click();//click on next button
            Thread.sleep(2000);
            currentAssignments.answerChoice.get(1).click();//click on option 'B'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Thread.sleep(3000);
            Assert.assertEquals(currentAssignments.footerText.getText(), "You got it wrong.", "You got it wrong is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToCheckAnswerChooseAnswer in class AssignmentCreatedThroughCustomAssignment", e);
        }
    }
    @Test(priority = 10,enabled = true)
    public void mentorShouldBeAbleToCheckAnswerPartiallyCorrect() {
        try {
            //tc row no 184
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "1841");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "1841");
            new LoginUsingLTI().ltiLogin("1841");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("Multiple Selection");

            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("1841");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            Thread.sleep(3000);
            questionBank.tryItIcon.get(0).click();//Click on tryit icon
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            currentAssignments.answerOption_MultipleChoice.get(0).click();//click on option 'A'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Thread.sleep(3000);
        } catch (Exception e) {
            Assert.fail("Exception in test case mentorShouldBeAbleToCheckAnswerPartiallyCorrect in class AssignmentCreatedThroughCustomAssignment", e);
        }
    }

    @Test(priority = 11,enabled = true)
    public void mentorShouldBeAbleToCheckAnswerChooseAnswer() {
        try {
            //tc row no 185
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "1851");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "1851");
            new LoginUsingLTI().ltiLogin("1851");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");

            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("1851");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            Thread.sleep(3000);
            questionBank.tryItIcon.get(0).click();//Click on tryit icon
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Assert.assertEquals(currentAssignments.footerText.getText(), "Choose the answer", "Choose the answer is not displaying");
            currentAssignments.next_Button.click();//click on next button
            Thread.sleep(2000);
            currentAssignments.answerChoice.get(1).click();//click on option 'B'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Thread.sleep(3000);
            Assert.assertEquals(currentAssignments.footerText.getText(), "You got it wrong.", "You got it wrong is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case mentorShouldBeAbleToCheckAnswerChooseAnswer in class AssignmentCreatedThroughCustomAssignment", e);
        }
    }
    @Test(priority = 12,enabled = true)
    public void instructorShouldBeAbleToNavigatePreviousAndNext() {
        try {
            //tc row no 186
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "186");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "186");
            new LoginUsingLTI().ltiLogin("186");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");

            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("186");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            Thread.sleep(3000);
            questionBank.tryItIcon.get(0).click();//Click on tryit icon
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
            Thread.sleep(3000);
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
            Assert.fail("Exception in test case instructorShouldBeAbleToNavigatePreviousAndNext in class AssignmentCreatedThroughCustomAssignment", e);
        }
    }
    public void selectQuestion(String questionNo)
    {
        driver.findElement(By.xpath("//div[text()='"+questionNo+"']")).click();

    }

    @Test(priority = 13,enabled = true)
    public void mentorShouldBeAbleToNavigatePreviousAndNext() {
        try {
            //tc row no 186
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "1861");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "1861");
            new LoginUsingLTI().ltiLogin("1861");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");

            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("1861");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            Thread.sleep(3000);
            questionBank.tryItIcon.get(0).click();//Click on tryit icon
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
            Thread.sleep(3000);
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
            Assert.fail("Exception in test case mentorShouldBeAbleToNavigatePreviousAndNext in class AssignmentCreatedThroughCustomAssignment", e);
        }
    }

    @Test(priority = 14,enabled = true)
    public void instructorShouldBeAbleToNavigateToParticularQuestion() {
        try {
            //tc row no 194
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "194");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "194");
            new LoginUsingLTI().ltiLogin("194");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");

            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("194");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            Thread.sleep(3000);
            questionBank.tryItIcon.get(0).click();//Click on tryit icon
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
            Assert.fail("Exception in test case instructorShouldBeAbleToNavigateToParticularQuestion in class AssignmentCreatedThroughCustomAssignment", e);
        }
    }


    @Test(priority = 15,enabled = true)
    public void mentorShouldBeAbleToNavigateToParticularQuestion() {
        try {
            //tc row no 194
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "1941");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "1941");
            new LoginUsingLTI().ltiLogin("1941");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");

            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("1941");//select two question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            Thread.sleep(3000);
            questionBank.tryItIcon.get(0).click();//Click on tryit icon
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
            Assert.fail("Exception in test case mentorShouldBeAbleToNavigateToParticularQuestion in class AssignmentCreatedThroughCustomAssignment", e);
        }
    }

    @Test(priority = 16,enabled = true)
       public void instructorShouldBeAbleToViewQuestion() {
        try {
            //tc row no 200
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "200");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "200");
            new LoginUsingLTI().ltiLogin("200");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.allDifficultyLevelArrow.click();
            Thread.sleep(2000);
            myQuestionBank.easy_Link.click();
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("200");//select two question

            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.allDifficultyLevelArrow.click();
            myQuestionBank.medium_Link.click();
            Thread.sleep(5000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", myQuestionBank.getGo_Button());//click on go
            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("201");//select two question


            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.allDifficultyLevelArrow.click();
            myQuestionBank.hard_Link.click();
            Thread.sleep(5000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", myQuestionBank.getGo_Button());//click on go
            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("202");//select two question

            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            Thread.sleep(3000);
            questionBank.tryItIcon.get(0).click();//Click on tryit icon
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
            Assert.fail("Exception in test case instructorShouldBeAbleToViewQuestion in class AssignmentCreatedThroughCustomAssignment", e);
        }
    }
    @Test(priority = 17,enabled = true)
    public void mentorShouldBeAbleToViewQuestion() {
        try {
            //tc row no 200
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "2001");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "2001");
            new LoginUsingLTI().ltiLogin("2001");//log in as instructor
            new Navigator().NavigateTo("My Question Bank");//navigate to my question bank
            myQuestionBank.getCustomAssignmentButton().click();//click on custom assignment
            Thread.sleep(2000);
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            myQuestionBank.getFilterArrow().click();//click on filter
            Thread.sleep(1000);
            myQuestionBank.allDifficultyLevelArrow.click();
            myQuestionBank.easy_Link.click();
            Thread.sleep(5000);
            myQuestionBank.getAllQuestionTypeArrow().click();//click on all question type
            new SelectParticularQuestionFromCustomAssignment().selectParticularQuestionFromCustomAssignment("True/False");
            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("2001");//select two question


            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.allDifficultyLevelArrow.click();
            myQuestionBank.medium_Link.click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", myQuestionBank.getGo_Button());//click on go
            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("2011");//select two question

            myQuestionBank.getFilterArrow().click();//click on filter
            myQuestionBank.allDifficultyLevelArrow.click();
            myQuestionBank.hard_Link.click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", myQuestionBank.getGo_Button());//click on go
            Thread.sleep(2000);
            new AssignLesson().selectQuestionForCustomAssignment("2021");//select two question

            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            Thread.sleep(3000);
            questionBank.tryItIcon.get(0).click();//Click on tryit icon
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
            Assert.fail("Exception in test case mentorShouldBeAbleToViewQuestion in class AssignmentCreatedThroughCustomAssignment", e);
        }
    }




}




