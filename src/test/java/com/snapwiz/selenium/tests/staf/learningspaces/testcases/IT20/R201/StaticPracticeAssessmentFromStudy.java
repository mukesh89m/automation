package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT20.R201;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.QuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.CourseOutline;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
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

/**
 * Created by priyanka on 4/23/2015.
 */
public class StaticPracticeAssessmentFromStudy extends Driver {

    @Test(priority = 1, enabled = true)
    public void instructorShouldBeAbleToTryAnyAssignmentFromSummary() {
        try {
            //tc row no 348
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            new Assignment().create(348);//Create an assignment
            new LoginUsingLTI().ltiLogin("348");//login as instructor
            new TOCShow().chaptertree();
            //new SelectCourse().selectchapter("Biological Macromolecules");
            new TopicOpen().chapterOpen(2);
            driver.findElement(By.xpath("//a[@title='IT201_static Assessment_348']/following-sibling::div[1]")).click();
            String parentWindow=driver.getWindowHandle();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(questionBank.wileyLogo.isDisplayed(), true, "Wiley Plus logo is not displaying at left side header");
            Assert.assertEquals(questionBank.courseName.isDisplayed(), true, "Course name is not displaying at middle part of header");
            String str=currentAssignments.assignmentNamePreviewPage.getText();
            System.out.println(str);
            if(!str.contains("IT201_static Assessment_348"))
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
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));//click on try it link
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            String assignmentName1 = currentAssignments.assignmentNamePreviewPage.getText();
            if (!assignmentName.equals(assignmentName1)) {
                Assert.fail("same window is not getting maximized");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToTryAnyAssignmentFromSummary in class StaticPracticeAssessmentFromStudy", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void mentorShouldBeAbleToTryAnyAssignmentFromSummary() {
        try {
            //tc row no 348
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            new LoginUsingLTI().ltiLogin("3481");//login as mentor
            new TOCShow().chaptertree();
            new TopicOpen().chapterOpen(2);
            driver.findElement(By.xpath("//a[@title='IT201_static Assessment_348']/following-sibling::div[1]")).click();
            String parentWindow=driver.getWindowHandle();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(questionBank.wileyLogo.isDisplayed(), true, "Wiley Plus logo is not displaying at left side header");
            Assert.assertEquals(questionBank.courseName.isDisplayed(), true, "Course name is not displaying at middle part of header");
            String str=currentAssignments.assignmentNamePreviewPage.getText();
            System.out.println(str);
            if(!str.contains("IT201_static Assessment_348"))
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
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));//click on try it link
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            String assignmentName1 = currentAssignments.assignmentNamePreviewPage.getText();
            if (!assignmentName.equals(assignmentName1)) {
                Assert.fail("same window is not getting maximized");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case mentorShouldBeAbleToTryAnyAssignmentFromSummary in class StaticPracticeAssessmentFromStudy", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void instructorShouldBeAbleToCheckAnswerForManualGradedQuestion() {
        try {
            //tc row no 363
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new Assignment().create(363); //create assignment
            new Assignment().addQuestions(363, "writeboard", "");
            new Assignment().addQuestions(363, "essay", "");
            new Assignment().addQuestions(363, "audio", "");
            new LoginUsingLTI().ltiLogin("363");//login as instructor
            new TOCShow().chaptertree();
            Thread.sleep(3000);
            new TopicOpen().chapterOpen(2);
            driver.findElement(By.xpath("//a[@title='IT201_static Assessment_363']/following-sibling::div[1]")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));
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
            Assert.fail("Exception in test case instructorShouldBeAbleToCheckAnswerForManualGradedQuestion in class StaticPracticeAssessmentFromStudy", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void mentorShouldBeAbleToCheckAnswerForManualGradedQuestion() {
        try {
            //tc row no 363
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("3631");//login as mentor
            new TOCShow().chaptertree();
            Thread.sleep(3000);
            new TopicOpen().chapterOpen(2);
            driver.findElement(By.xpath("//a[@title='IT201_static Assessment_363']/following-sibling::div[1]")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));
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
            Assert.fail("Exception in test case mentorShouldBeAbleToCheckAnswerForManualGradedQuestion in class StaticPracticeAssessmentFromStudy", e);
        }
    }

    @Test(priority = 5,enabled = true)
    public void instructorShouldBeAbleToCheckAnswerForQuestion() {
        try {
            //tc row no 373
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("373");//login as instructor
            new TOCShow().chaptertree();
            Thread.sleep(3000);
            new TopicOpen().chapterOpen(2);
            driver.findElement(By.xpath("//a[@title='IT201_static Assessment_348']/following-sibling::div[1]")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));
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
            new WebDriverWait(driver, 300).until(ExpectedConditions.presenceOfElementLocated(By.id("footer-notification-text")));
            Assert.assertEquals(currentAssignments.footerText.getText(), "You got it right.", "You got it right message is not displaying");
            currentAssignments.answerChoice.get(0).click();//click on option 'B'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Thread.sleep(3000);
            Assert.assertEquals(currentAssignments.footerText.getText(),"You got it wrong.","You got it wrong message is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToCheckAnswerForQuestion in class StaticPracticeAssessmentFromStudy", e);
        }
    }

    @Test(priority = 6,enabled = true)
    public void mentorShouldBeAbleToCheckAnswerForQuestion() {
        try {
            //tc row no 373
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("3731");//login as mentor
            new TOCShow().chaptertree();
            Thread.sleep(3000);
            new TopicOpen().chapterOpen(2);
            driver.findElement(By.xpath("//a[@title='IT201_static Assessment_348']/following-sibling::div[1]")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "first question of the assignment is not displaying by default");
            Assert.assertEquals(currentAssignments.hint_Button.getText(), "Hint", "Hint button is not displaying in buttom footer");
            Assert.assertEquals(currentAssignments.solution_Button.getText(), "Solution", "solution button is not displaying in buttom footer");
            Assert.assertEquals(currentAssignments.checkAnswer_Button.getText(), "Check Answer", "check answer button is not displaying in buttom footer");
            currentAssignments.answerChoice.get(0).click();//click on option 'A'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            new WebDriverWait(driver, 300).until(ExpectedConditions.presenceOfElementLocated(By.id("footer-notification-text")));
            Assert.assertEquals(currentAssignments.footerText.getText(), "You got it right.", "You got it right message is not displaying");
            currentAssignments.answerChoice.get(0).click();//click on option 'B'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Thread.sleep(3000);
            Assert.assertEquals(currentAssignments.footerText.getText(), "You got it wrong.", "You got it wrong message is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case mentorShouldBeAbleToCheckAnswerForQuestion in class StaticPracticeAssessmentFromStudy", e);
        }

    }
    @Test(priority = 7,enabled = true)
    public void instructorShouldBeAbleToCheckAnswerPartiallyCorrect() {
        try {
            //tc row no 380
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new Assignment().create(380); //create assignment
            new Assignment().addQuestions(380, "multipleselection", "");
            new LoginUsingLTI().ltiLogin("380");//login as instructor
            new TOCShow().chaptertree();
            Thread.sleep(3000);
            new TopicOpen().chapterOpen(2);
            driver.findElement(By.xpath("//a[@title='IT201_static Assessment_380']/following-sibling::div[1]")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Thread.sleep(2000);
            currentAssignments.nextButton.click();
            Thread.sleep(1000);
            currentAssignments.answerOption_MultipleChoice.get(1).click();//click on option 'B'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Thread.sleep(3000);
            Assert.assertEquals(currentAssignments.footerText.getText(), "You got it partially correct.", "You got it partially correct is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToCheckAnswerPartiallyCorrect in class StaticPracticeAssessmentFromStudy", e);
        }
    }

    @Test(priority = 8,enabled = true)
    public void mentorShouldBeAbleToCheckAnswerPartiallyCorrect() {
        try {
            //tc row no 380
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("3801");//login as instructor
            new TOCShow().chaptertree();
            Thread.sleep(3000);
            new TopicOpen().chapterOpen(2);
            driver.findElement(By.xpath("//a[@title='IT201_static Assessment_380']/following-sibling::div[1]")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Thread.sleep(3000);
            currentAssignments.nextButton.click();
            Thread.sleep(1000);
            currentAssignments.answerOption_MultipleChoice.get(1).click();//click on option 'B'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Thread.sleep(3000);
            Assert.assertEquals(currentAssignments.footerText.getText(), "You got it partially correct.", "You got it partially correct is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case mentorShouldBeAbleToCheckAnswerPartiallyCorrect in class StaticPracticeAssessmentFromStudy", e);
        }
    }

    @Test(priority = 9,enabled = true)
    public void instructorShouldBeAbleToCheckAnswerChooseAnswer() {
        try {
            //tc row no 381
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("381");//login as instructor
            new TOCShow().chaptertree();
            Thread.sleep(3000);
            new TopicOpen().chapterOpen(2);
            driver.findElement(By.xpath("//a[@title='IT201_static Assessment_348']/following-sibling::div[1]")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));

            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.footerText.getText(), "Choose the answer", "Choose the answer is not displaying");
            currentAssignments.answerChoice.get(1).click();//click on option 'B'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Thread.sleep(3000);
            Assert.assertEquals(currentAssignments.footerText.getText(), "You got it wrong.", "You got it wrong is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToCheckAnswerChooseAnswer in class StaticPracticeAssessmentFromStudy", e);
        }
    }

    @Test(priority = 10,enabled = true)
    public void mentorShouldBeAbleToCheckAnswerChooseAnswer() {
        try {
            //tc row no 381
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("3811");//login as mentor
            new TOCShow().chaptertree();
            Thread.sleep(3000);
            new TopicOpen().chapterOpen(2);
            driver.findElement(By.xpath("//a[@title='IT201_static Assessment_348']/following-sibling::div[1]")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));

            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Assert.assertEquals(currentAssignments.footerText.getText(), "Choose the answer", "Choose the answer is not displaying");
            currentAssignments.answerChoice.get(1).click();//click on option 'B'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Thread.sleep(3000);
            Assert.assertEquals(currentAssignments.footerText.getText(), "You got it wrong.", "You got it wrong is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case mentorShouldBeAbleToCheckAnswerChooseAnswer in class StaticPracticeAssessmentFromStudy", e);
        }
    }

    @Test(priority = 11,enabled = true)
    public void instructorShouldBeAbleToNavigatePreviousAndNext() {
        try {
            //tc row no 382
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("382");//login as instructor
            new TOCShow().chaptertree();
            Thread.sleep(3000);
            new TopicOpen().chapterOpen(2);
            driver.findElement(By.xpath("//a[@title='IT201_static Assessment_363']/following-sibling::div[1]")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));
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
            selectQuestion("Q4");
            Assert.assertEquals(currentAssignments.finishButton.isDisplayed(), true, "Finish button is not available");
            currentAssignments.finishButton.click();//click on finish button
            driver.switchTo().window(parent);
            String url=driver.getCurrentUrl();
            if(url.contains("tryItAssessmentOrAssignment"))
            {
                Assert.fail("Browser popup is not closed after click on finish button");
            }


        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToNavigatePreviousAndNext in class StaticPracticeAssessmentFromStudy", e);
        }
    }
    public void selectQuestion(String questionNo)
    {
          driver.findElement(By.xpath("//div[text()='"+questionNo+"']")).click();

    }

    @Test(priority = 12,enabled = true)
    public void mentorShouldBeAbleToNavigatePreviousAndNext() {
        try {
            //tc row no 382
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("3821");//login as mentor
            new TOCShow().chaptertree();
            Thread.sleep(3000);
            new TopicOpen().chapterOpen(2);
            driver.findElement(By.xpath("//a[@title='IT201_static Assessment_363']/following-sibling::div[1]")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));
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
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("question-label")));
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q2:", "It is not navigated to the respective question");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q3");
            Thread.sleep(2000);
            currentAssignments.previous_Button.click();//click on previous button
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q2:", "It is not navigated to the respective question");
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q4");
            Assert.assertEquals(currentAssignments.finishButton.isDisplayed(), true, "Finish button is not available");
            currentAssignments.finishButton.click();//click on finish button
            driver.switchTo().window(parent);
            String url=driver.getCurrentUrl();
            if(url.contains("tryItAssessmentOrAssignment"))
            {
                Assert.fail("Browser popup is not closed after click on finish button");
            }


        } catch (Exception e) {
            Assert.fail("Exception in test case mentorShouldBeAbleToNavigatePreviousAndNext in class StaticPracticeAssessmentFromStudy", e);
        }
    }

    @Test(priority = 13,enabled = true)
    public void instructorShouldBeAbleToNavigateToParticularQuestion() {
        try {
            //tc row no 390
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("390");//login as instructor
            new TOCShow().chaptertree();
            Thread.sleep(3000);
            new TopicOpen().chapterOpen(2);
            driver.findElement(By.xpath("//a[@title='IT201_static Assessment_363']/following-sibling::div[1]")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "first question of the assignment is not displaying by default");
            Assert.assertEquals(currentAssignments.summaryDropDown.isDisplayed(),true,"Summary drop-down on the right corner of assignment name line is not present");
            currentAssignments.helpIconOnSummaryDropDown.click();//click on help icon on summary dropdown
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            Assert.assertEquals(currentAssignments.dropDownQuestion.get(3).isDisplayed(),true,"It should not opened dropdown followed by question");
            WebElement scroll=driver.findElement(By.xpath("//div[text()='Q4']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            selectQuestion("Q1");//select question 3
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "It is not navigated to the respective question");
            currentAssignments.answerChoice.get(0).click();//click on option 'A'
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q4");//select question 5
            Thread.sleep(5000);
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            Thread.sleep(2000);
            selectQuestion("Q1");//select question 1
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "It is not navigated to the respective question");
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.answerChoice.get(0).isSelected(),false,"The answer is saved for that question");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToNavigateToParticularQuestion in class StaticPracticeAssessmentFromStudy", e);
        }
    }

    @Test(priority = 14,enabled = true)
    public void mentorShouldBeAbleToNavigateToParticularQuestion() {
        try {
            //tc row no 390
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("3901");//login as instructor
            new TOCShow().chaptertree();
            Thread.sleep(3000);
            new TopicOpen().chapterOpen(2);
            driver.findElement(By.xpath("//a[@title='IT201_static Assessment_363']/following-sibling::div[1]")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));
            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "first question of the assignment is not displaying by default");
            Assert.assertEquals(currentAssignments.summaryDropDown.isDisplayed(),true,"Summary drop-down on the right corner of assignment name line is not present");
            currentAssignments.helpIconOnSummaryDropDown.click();//click on help icon on summary dropdown
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            Assert.assertEquals(currentAssignments.dropDownQuestion.get(3).isDisplayed(),true,"It should not opened dropdown followed by question");
            WebElement scroll=driver.findElement(By.xpath("//div[text()='Q4']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            selectQuestion("Q1");//select question 3
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "It is not navigated to the respective question");
            currentAssignments.answerChoice.get(0).click();//click on option 'A'
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q4");//select question 5
            Thread.sleep(5000);
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            Thread.sleep(2000);
            selectQuestion("Q1");//select question 1
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "It is not navigated to the respective question");
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.answerChoice.get(0).isSelected(),false,"The answer is saved for that question");

        } catch (Exception e) {
            Assert.fail("Exception in test case mentorShouldBeAbleToNavigateToParticularQuestion in class StaticPracticeAssessmentFromStudy", e);
        }
    }

    @Test(priority = 15,enabled = true)
    public void instructorShouldBeAbleToViewQuestion() {
        try {
            //tc row no 396
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            new Assignment().create(396);//Create an assignment
            new Assignment().addQuestions(3961, "truefalse", "");
            new Assignment().addQuestions(3962, "truefalse", "");
            new LoginUsingLTI().ltiLogin("396");//login as instructor
            new TOCShow().chaptertree();
            Thread.sleep(3000);
            new TopicOpen().chapterOpen(2);
            Thread.sleep(4000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//a[@title='Assignment_IT20_201_396']/following-sibling::div[1]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));
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
            Assert.fail("Exception in test case instructorShouldBeAbleToViewQuestion in class StaticPracticeAssessmentFromStudy", e);
        }
    }

    @Test(priority = 16,enabled = true)
    public void mentorShouldBeAbleToViewQuestion() {
        try {
            //tc row no 396
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("3961");//login as mentor
            new TOCShow().chaptertree();
            Thread.sleep(3000);
            new TopicOpen().chapterOpen(2);
            Thread.sleep(4000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();",driver.findElement(By.xpath("//a[@title='Assignment_IT20_201_396']/following-sibling::div[1]")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));
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
            Assert.fail("Exception in test case mentorShouldBeAbleToViewQuestion in class StaticPracticeAssessmentFromStudy", e);
        }
    }

}
