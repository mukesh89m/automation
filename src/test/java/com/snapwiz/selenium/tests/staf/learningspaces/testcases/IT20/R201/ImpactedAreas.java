package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT20.R201;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.CourseOutline;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by priyanka on 4/27/2015.
 */
public class ImpactedAreas extends Driver {
    @Test(priority = 1, enabled = true)
    public void repositionInCms() {
        try {
            //tc row no 413
            CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "413");
            new Assignment().create(413); //create assignment
            new Assignment().addQuestions(413, "writeboard", "");
            new Assignment().addQuestions(413, "essay", "");
            new Assignment().addQuestions(413, "audio", "");
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectcourse();//select a course
            Thread.sleep(2000);
            driver.findElements(By.cssSelector("div[class='course-chapter-label node']")).get(2).click();//select 3rd chapter
            driver.findElement(By.xpath("//div[@title='" + assignmentname + "']")).click();//select assessement
            Thread.sleep(4000);
            Actions act = new Actions(driver);
            WebElement element = driver.findElement(By.className("position-text-title"));
            act.moveToElement(element).build().perform();
            driver.findElement(By.xpath("//*[@id='question-reposition-icon']/img")).click();//click on reposition icon
            selectQuestion("1");
            selectQuestion("4");
            driver.findElement(By.id("saveQuestionDetails1")).click();
            new LoginUsingLTI().ltiLogin("413");//login as instructor
            new TOCShow().chaptertree();
            Thread.sleep(3000);
            new TopicOpen().chapterOpen(2); //open 3rd chapter
            driver.findElement(By.xpath("//a[@title='IT201_static Assessment_413']/following-sibling::div[1]")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            String question=driver.findElement(By.cssSelector("label[class='control-label writeboard-question-summary-page']")).getText();
            System.out.println(question);
            if (!question.contains("Write Board"))
               Assert.fail("Correct question ordered is not displaying after repositioning");
        } catch (Exception e) {
            Assert.fail("Exception in test case repositionInCms in class ImpactedAreas", e);
        }
    }
        public void selectQuestion(String questionNo)
        {
            driver.findElement(By.xpath("//a[text()='"+questionNo+"']")).click();

        }

    @Test(priority = 2, enabled = true)
    public void repositionInCmsForMentor() {
        try {
            //tc row no 413
            new LoginUsingLTI().ltiLogin("4131");//login as mentor
            new TOCShow().chaptertree();
            Thread.sleep(3000);
            new TopicOpen().chapterOpen(2); //open 3rd chapter
            driver.findElement(By.xpath("//a[@title='IT201_static Assessment_413']/following-sibling::div[1]")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            String question=driver.findElement(By.cssSelector("label[class='control-label writeboard-question-summary-page']")).getText();
            System.out.println(question);
            if (!question.contains("Write Board"))
                Assert.fail("Correct question ordered is not displaying after repositioning");
        } catch (Exception e) {
            Assert.fail("Exception in test case repositionInCmsForMentor in class ImpactedAreas", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void deActiveTheQuestionInCms() {
        try {
            //tc row no 414
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "414");
            String QuestionNo = ReadTestData.readDataByTagName("", "QuestionNo", "414");
            new Assignment().create(414); //create assignment
            new Assignment().addQuestions(414, "writeboard", "");
            new Assignment().addQuestions(414, "essay", "");
            new Assignment().addQuestions(414, "audio", "");
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectcourse();//select a course
            Thread.sleep(2000);
            driver.findElements(By.cssSelector("div[class='course-chapter-label node']")).get(2).click();//select 3rd chapter
            new Assignment().selectInvisiblebottomAssignment(assignmentname);
            Thread.sleep(4000);
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Jump To Q#']")));
            driver.findElement(By.xpath("//a[@title='Jump To Q#']")).click();
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.linkText(QuestionNo)));
            driver.findElement(By.linkText(QuestionNo)).click();
            Thread.sleep(2000);
            new Click().clickByid("questionOptions"); //click on question option
            Thread.sleep(2000);
            new Click().clickByid("questionRevisions"); // click on revisions
            Thread.sleep(2000);
            new Click().clickByid("cms-question-revision-deactivate-button");//click on the deactivate button

            new LoginUsingLTI().ltiLogin("414");//login as instructor
            new TOCShow().chaptertree();
            Thread.sleep(5000);
            new TopicOpen().chapterOpen(2); //open 3rd chapter
            driver.findElement(By.xpath("//a[@title='IT201_static Assessment_414']/following-sibling::div[1]")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            String str=driver.findElement(By.className("tryit-preview-dropdown-container")).getText();
            if (!str.equals("Question (1 of 3)"))
                Assert.fail("Deactivated question is displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case deActiveTheQuestionInCms in class ImpactedAreas", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void deActiveTheQuestionInCmsForMentor() {
        try {
            //tc row no 414
            new LoginUsingLTI().ltiLogin("4141");//login as instructor
            new TOCShow().chaptertree();
            Thread.sleep(5000);
            new TopicOpen().chapterOpen(2); //open 3rd chapter
            driver.findElement(By.xpath("//a[@title='IT201_static Assessment_414']/following-sibling::div[1]")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            String str=driver.findElement(By.className("tryit-preview-dropdown-container")).getText();
            if (!str.equals("Question (1 of 3)"))
                Assert.fail("Deactivated question is displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case deActiveTheQuestionInCmsForMentor in class ImpactedAreas", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void createTheVersionOfTheQuestionsInCms() {
        try {
            //tc row no 415
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "415");
            new Assignment().create(415); //create assignment
            new Assignment().addQuestions(415, "writeboard", "");
            new Assignment().addQuestions(415, "essay", "");
            new Assignment().addQuestions(415, "audio", "");
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectcourse();//select a course
            Thread.sleep(2000);
            driver.findElements(By.cssSelector("div[class='course-chapter-label node']")).get(2).click();//select 3rd chapter
            new Assignment().selectInvisiblebottomAssignment(assignmentname);
            Thread.sleep(4000);
            new Click().clickByid("questionOptions"); //click on question option
            Thread.sleep(4000);
            new Click().clickByid("questionRevisions"); // click on revisions
            Thread.sleep(4000);
            new Click().clickByid("cms-question-revision-new-version-link");//click on the create new version  button
            Thread.sleep(4000);
            driver.findElement(By.xpath("//a[text()='Draft']")).click();
            driver.findElement(By.xpath("//a[text()='Publish']")).click();
            Thread.sleep(4000);
            driver.findElement(By.id("saveQuestionDetails1")).click();

            new LoginUsingLTI().ltiLogin("415");//login as instructor
            new TOCShow().chaptertree();
            Thread.sleep(5000);
            new TopicOpen().chapterOpen(2); //open 3rd chapter
            driver.findElement(By.xpath("//a[@title='IT201_static Assessment_415']/following-sibling::div[1]")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            String question=driver.findElement(By.cssSelector("label[class='control-label writeboard-question-summary-page']")).getText();
            if (!question.contains("Write Board"))
                Assert.fail("Correct question ordered is not displaying after repositioning");
        } catch (Exception e) {
            Assert.fail("Exception in test case deActiveTheQuestionInCms in class ImpactedAreas", e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void questionWithDifferentQuestionSet() {
        try {
            //tc row no 416
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new Assignment().create(416);
            new Assignment().addQuestions(416, "writeboard", "");
            new Assignment().addQuestions(416, "essay", "");
            driver.findElement(By.className("question-set-add-text")).click();
            Thread.sleep(3000);
            driver.findElement(By.id("questionSetName")).sendKeys("set1");
            manageContent.trueFalseQuestion.click();// click on truefalse question
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("content-label")));
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("True False");//type the question
            new Click().clickBycssselector("span.true-false-answer-label");//click on Answer Option A
            Thread.sleep(2000);
            driver.findElement(By.xpath("//a[text()='Draft']")).click();
            driver.findElement(By.xpath("//a[text()='Publish']")).click();
            Thread.sleep(4000);
            driver.findElement(By.id("saveQuestionDetails1")).click();
            new LoginUsingLTI().ltiLogin("416");//login as instructor
            new TOCShow().chaptertree();
            Thread.sleep(5000);
            new TopicOpen().chapterOpen(2); //open 3rd chapter
            driver.findElement(By.xpath("//a[@title='IT201_static Assessment_416']/following-sibling::div[1]")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            String str=driver.findElement(By.className("tryit-preview-dropdown-container")).getText();
            if (!str.equals("Question (1 of 4)"))
                Assert.fail("Deactivated question is displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case questionWithDifferentQuestionSet in class ImpactedAreas", e);
        }
    }

    @Test(priority = 6, enabled = false)
    public void assessmentDoesNotContainAnyQuestion() {
        try {
            //tc row no 420
            new Assignment().create(420);
            new LoginUsingLTI().ltiLogin("420");//login as instructor
            new TOCShow().chaptertree();
            Thread.sleep(5000);
            new TopicOpen().chapterOpen(2); //open 3rd chapter
            driver.findElement(By.xpath("//a[@title='IT201_static Assessment_420']/following-sibling::div[1]")).click();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            String str=driver.findElement(By.className("tryit-preview-dropdown-container")).getText();
            if (!str.equals("Question (1 of 4)"))
                Assert.fail("Deactivated question is displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case assessmentDoesNotContainAnyQuestion in class ImpactedAreas", e);
        }
    }
}


