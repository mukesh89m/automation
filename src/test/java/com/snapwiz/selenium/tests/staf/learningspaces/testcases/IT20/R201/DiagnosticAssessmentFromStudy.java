package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT20.R201;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.QuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.CourseOutline;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.util.List;


/**
 * Created by priyanka on 4/22/2015.
 */
public class DiagnosticAssessmentFromStudy extends Driver{
    @Test(priority = 1, enabled = true)
    public void instructorShouldBeAbleToTryAnyAssignmentFromSummary() {
        try {
            //tc row no 217
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            CourseOutline courseOutline=PageFactory.initElements(driver,CourseOutline.class);
            String chapterName = ReadTestData.readDataByTagName("", "newChapterName", Integer.toString(217));
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);
            WebDriverWait wait=new WebDriverWait(driver,200);

            new Assignment().createChapter(217,1);//create a chapter
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectcourse();//select a course
            Thread.sleep(2000);
            courseOutline.courseOutline.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@title,'Add New Chapter')]")));

            List<WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label node course-chapter-label-unpublished')]"));
            for (WebElement chapters : allChapters) {
                if (chapters.getText().contains(chapterName)) {
                    Locatable hoverItem = (Locatable) chapters;
                    Mouse mouse = ((HasInputDevices) driver).getMouse();
                    mouse.mouseMove(hoverItem.getCoordinates());
                }
            }
            Thread.sleep(500);
            courseOutline.editButtonAtChapter.click();
            Thread.sleep(500);
            courseOutline.checkBoxToPublishChapter.click();//click on publish
            courseOutline.saveButton.click();//click on save
            Thread.sleep(5000);
            courseOutline.saveMyChanges.click();
            Thread.sleep(2000);
            create(217);//Create an assignment
            new LoginUsingLTI().ltiLogin("217");//login as instructor
            new TOCShow().chaptertree();//click on toc
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");//click on particular chapter
            lessonPage.orionAdaptive_link.get(1).click();//click on orionAdaptive
            lessonPage.diagnostic_Arrow.click();//click on '>' link
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
            if(!str.contains("IT20_R201_Assessment_217"))
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
            Assert.fail("Exception in test case instructorShouldBeAbleToTryAnyAssignmentFromSummary in class DiagnosticAssessmentFromStudy", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void mentorShouldBeAbleToTryAnyAssignmentFromSummary() {
        try {
            //tc row no 217
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            String chapterName = ReadTestData.readDataByTagName("", "newChapterName", Integer.toString(2171));
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);
            new LoginUsingLTI().ltiLogin("2171");//login as mentor
            new TOCShow().chaptertree();//click on toc
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");//click on particular chapter
            lessonPage.orionAdaptive_link.get(1).click();//click on orionAdaptive
            lessonPage.diagnostic_Arrow.click();//click on '>' link
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
            if(!str.contains("IT20_R201_Assessment_217"))
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
            Assert.fail("Exception in test case mentorShouldBeAbleToTryAnyAssignmentFromSummary in class DiagnosticAssessmentFromStudy", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void instructorShouldBeAbleToCheckAnswerForManualGradedQuestion() {
        try {
            //tc row no 243
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            CourseOutline courseOutline=PageFactory.initElements(driver,CourseOutline.class);
            String chapterName = ReadTestData.readDataByTagName("", "newChapterName", Integer.toString(243));
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);
            WebDriverWait wait=new WebDriverWait(driver,200);
            new Assignment().createChapter(243,1);//create a chapter
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectcourse();//select a course
            Thread.sleep(2000);
            courseOutline.courseOutline.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@title,'Add New Chapter')]")));
        //    Actions actions=new Actions(driver);
            //publish chapter
            List<WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label node course-chapter-label-unpublished')]"));
            for (WebElement chapters : allChapters) {
                if (chapters.getText().contains(chapterName)) {
                    Locatable hoverItem = (Locatable) chapters;
                    Mouse mouse = ((HasInputDevices) driver).getMouse();
                    mouse.mouseMove(hoverItem.getCoordinates());
                }
            }
          //  actions.moveToElement(driver.findElement(By.xpath("//div[contains(text(),'"+chapterName+"')]"))).build().perform();
            Thread.sleep(500);
            courseOutline.editButtonAtChapter.click();
            Thread.sleep(500);
            courseOutline.checkBoxToPublishChapter.click();//click on publish
            courseOutline.saveButton.click();//click on save
            Thread.sleep(5000);
            courseOutline.saveMyChanges.click();
            Thread.sleep(2000);
            create(243); //create assignment
            addQuestions(243, "writeboard", "");
            addQuestions(243, "essay", "");
            addQuestions(243, "audio", "");
            new LoginUsingLTI().ltiLogin("243");//login as instructor
            new TOCShow().chaptertree();//click on toc
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");//click on particular chapter
            lessonPage.orionAdaptive_link.get(1).click();//click on orionAdaptive
            lessonPage.diagnostic_Arrow.click();//click on '>' link
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
            Assert.fail("Exception in test case instructorShouldBeAbleToCheckAnswerForManualGradedQuestion in class DiagnosticAssessmentFromStudy", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void mentorShouldBeAbleToCheckAnswerForManualGradedQuestion() {
        try {
            //tc row no 243
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String chapterName = ReadTestData.readDataByTagName("", "newChapterName", Integer.toString(2431));
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);
            new LoginUsingLTI().ltiLogin("2431");//login as mentor
            new TOCShow().chaptertree();//click on toc
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");//click on particular chapter
            lessonPage.orionAdaptive_link.get(1).click();//click on orionAdaptive
            lessonPage.diagnostic_Arrow.click();//click on '>' link
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
            Assert.fail("Exception in test case mentorShouldBeAbleToCheckAnswerForManualGradedQuestion in class DiagnosticAssessmentFromStudy", e);
        }
    }

    @Test(priority = 5,enabled = true)
    public void instructorShouldBeAbleToCheckAnswerForQuestion() {
        try {
            //tc row no 244
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String chapterName = ReadTestData.readDataByTagName("", "newChapterName", Integer.toString(244));
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);
            new LoginUsingLTI().ltiLogin("244");//login as instructor
            new TOCShow().chaptertree();//click on toc
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");//click on particular chapter
            lessonPage.orionAdaptive_link.get(1).click();//click on orionAdaptive
            lessonPage.diagnostic_Arrow.click();//click on '>' link
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
            Assert.fail("Exception in test case instructorShouldBeAbleToCheckAnswerForQuestion in class DiagnosticAssessmentFromStudy", e);
        }
    }

    @Test(priority = 6,enabled = true)
    public void mentorShouldBeAbleToCheckAnswerForQuestion() {
        try {
            //tc row no 244
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String chapterName = ReadTestData.readDataByTagName("", "newChapterName", Integer.toString(2441));
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);
            new LoginUsingLTI().ltiLogin("2441");//login as mentor
            new TOCShow().chaptertree();//click on toc
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");//click on particular chapter
            lessonPage.orionAdaptive_link.get(1).click();//click on orionAdaptive
            lessonPage.diagnostic_Arrow.click();//click on '>' link
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
            Assert.fail("Exception in test case mentorShouldBeAbleToCheckAnswerForQuestion in class DiagnosticAssessmentFromStudy", e);
        }
    }

    @Test(priority = 7,enabled = true)
    public void instructorShouldBeAbleToCheckAnswerPartiallyCorrect() {
        try {
            //tc row no 250
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            CourseOutline courseOutline=PageFactory.initElements(driver,CourseOutline.class);
            String chapterName = ReadTestData.readDataByTagName("", "newChapterName", Integer.toString(250));
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            WebDriverWait wait=new WebDriverWait(driver,200);
            new Assignment().createChapter(250, 1);//create a chapter
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectcourse();//select a course
            Thread.sleep(2000);
            courseOutline.courseOutline.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@title,'Add New Chapter')]")));
            //publish chapter
            List<WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label node course-chapter-label-unpublished')]"));
            for (WebElement chapters : allChapters) {
                if (chapters.getText().contains(chapterName)) {
                    Locatable hoverItem = (Locatable) chapters;
                    Mouse mouse = ((HasInputDevices) driver).getMouse();
                    mouse.mouseMove(hoverItem.getCoordinates());
                }
            }
            Thread.sleep(500);
            courseOutline.editButtonAtChapter.click();
            Thread.sleep(500);
            courseOutline.checkBoxToPublishChapter.click();//click on publish
            courseOutline.saveButton.click();//click on save
            Thread.sleep(5000);
            courseOutline.saveMyChanges.click();
            Thread.sleep(2000);
            create(250); //create assignment
            addQuestions(250, "multipleselection", "");


            new LoginUsingLTI().ltiLogin("250");//login as instructor
            new TOCShow().chaptertree();//click on toc
            new TopicOpen().openLastChapter();
            lessonPage.orionAdaptive_link.get(1).click();//click on orionAdaptive
            lessonPage.diagnostic_Arrow.click();//click on '>' link
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));

            for(String handle:driver.getWindowHandles())
            {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            currentAssignments.nextButton.click();
            currentAssignments.answerOption_MultipleChoice.get(1).click();//click on option 'B'
            currentAssignments.checkAnswer_Button.click();//click on check answer
            Thread.sleep(3000);
            Assert.assertEquals(currentAssignments.footerText.getText(), "You got it partially correct.", "You got it partially correct is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToCheckAnswerPartiallyCorrect in class DiagnosticAssessmentFromStudy", e);
        }
    }

    @Test(priority = 8,enabled = true)
    public void instructorShouldBeAbleToCheckAnswerChooseAnswer() {
        try {
            //tc row no 251
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String chapterName = ReadTestData.readDataByTagName("", "newChapterName", Integer.toString(251));
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);
            new LoginUsingLTI().ltiLogin("251");//login as instructor
            new TOCShow().chaptertree();//click on toc
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");//click on particular chapter
            lessonPage.orionAdaptive_link.get(1).click();//click on orionAdaptive
            lessonPage.diagnostic_Arrow.click();//click on '>' link
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
            Assert.fail("Exception in test case instructorShouldBeAbleToCheckAnswerChooseAnswer in class DiagnosticAssessmentFromStudy", e);
        }
    }

    @Test(priority = 9,enabled = true)
    public void mentorShouldBeAbleToCheckAnswerChooseAnswer() {
        try {
            //tc row no 2511
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String chapterName = ReadTestData.readDataByTagName("", "newChapterName", Integer.toString(2511));
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);
            new LoginUsingLTI().ltiLogin("2511");//login as instructor
            new TOCShow().chaptertree();//click on toc
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");//click on particular chapter
            lessonPage.orionAdaptive_link.get(1).click();//click on orionAdaptive
            lessonPage.diagnostic_Arrow.click();//click on '>' link
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
            Assert.fail("Exception in test case mentorShouldBeAbleToCheckAnswerChooseAnswer in class DiagnosticAssessmentFromStudy", e);
        }
    }

    @Test(priority = 10,enabled = true)
    public void instructorShouldBeAbleToNavigatePreviousAndNext() {
        try {
            //tc row no 252
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String chapterName = ReadTestData.readDataByTagName("", "newChapterName", Integer.toString(252));
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);
            new LoginUsingLTI().ltiLogin("252");//login as instructor
            new TOCShow().chaptertree();//click on toc
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");//click on particular chapter
            lessonPage.orionAdaptive_link.get(1).click();//click on orionAdaptive
            lessonPage.diagnostic_Arrow.click();//click on '>' link
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
            Thread.sleep(5000);
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
            Assert.fail("Exception in test case instructorShouldBeAbleToNavigatePreviousAndNext in class DiagnosticAssessmentFromStudy", e);
        }
    }
    public void selectQuestion(String questionNo)
    {
        driver.findElement(By.xpath("//div[text()='"+questionNo+"']")).click();

    }

    @Test(priority = 11,enabled = true)
    public void mentorShouldBeAbleToNavigatePreviousAndNext() {
        try {
            //tc row no 252
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String chapterName = ReadTestData.readDataByTagName("", "newChapterName", Integer.toString(2521));
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);
            new LoginUsingLTI().ltiLogin("2521");//login as instructor
            new TOCShow().chaptertree();//click on toc
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");//click on particular chapter
            lessonPage.orionAdaptive_link.get(1).click();//click on orionAdaptive
            lessonPage.diagnostic_Arrow.click();//click on '>' link
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
            Thread.sleep(6000);
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
            Assert.fail("Exception in test case mentorShouldBeAbleToNavigatePreviousAndNext in class DiagnosticAssessmentFromStudy", e);
        }
    }


    @Test(priority = 12,enabled = true)
    public void instructorShouldBeAbleToNavigateToParticularQuestion() {
        try {
            //tc row no 260
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String chapterName = ReadTestData.readDataByTagName("", "newChapterName", Integer.toString(243));
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);
            new LoginUsingLTI().ltiLogin("260");//login as instructor
            new TOCShow().chaptertree();//click on toc
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");//click on particular chapter
            lessonPage.orionAdaptive_link.get(1).click();//click on orionAdaptive
            lessonPage.diagnostic_Arrow.click();//click on '>' link
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));
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
            Assert.assertEquals(currentAssignments.dropDownQuestion.get(3).isDisplayed(),true,"It should not opened dropdown followed by question");
            WebElement scroll=driver.findElement(By.xpath("//div[text()='Q1']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            selectQuestion("Q1");//select question 3
            Thread.sleep(3000);
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "It is not navigated to the respective question");
            currentAssignments.answerChoice.get(0).click();//click on option 'A'
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            Thread.sleep(5000);
            selectQuestion("Q2");//select question 5
            Thread.sleep(4000);
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q1");//select question 3
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "It is not navigated to the respective question");
            Assert.assertEquals(currentAssignments.answerChoice.get(0).isSelected(),false,"The answer is saved for that question");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToNavigateToParticularQuestion in class DiagnosticAssessmentFromStudy", e);
        }
    }

    @Test(priority = 13,enabled = true)
    public void mentorShouldBeAbleToNavigateToParticularQuestion() {
        try {
            //tc row no 260
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String chapterName = ReadTestData.readDataByTagName("", "newChapterName", Integer.toString(2601));
            LessonPage lessonPage = PageFactory.initElements(driver,LessonPage.class);
            new LoginUsingLTI().ltiLogin("2601");//login as instructor
            new TOCShow().chaptertree();//click on toc
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");//click on particular chapter
            lessonPage.orionAdaptive_link.get(1).click();//click on orionAdaptive
            lessonPage.diagnostic_Arrow.click();//click on '>' link
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));
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
            Assert.assertEquals(currentAssignments.dropDownQuestion.get(3).isDisplayed(),true,"It should not opened dropdown followed by question");
            WebElement scroll=driver.findElement(By.xpath("//div[text()='Q4']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            selectQuestion("Q1");//select question 3
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "It is not navigated to the respective question");
            currentAssignments.answerChoice.get(0).click();//click on option 'A'
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q2");//select question 5
            Thread.sleep(4000);
            currentAssignments.summaryDropDown.click();//click on summary dropdown
            selectQuestion("Q1");//select question 3
            Thread.sleep(2000);
            Assert.assertEquals(currentAssignments.getQuestionCount().get(0).getText(), "Q1:", "It is not navigated to the respective question");
            Assert.assertEquals(currentAssignments.answerChoice.get(0).isSelected(),false,"The answer is saved for that question");

        } catch (Exception e) {
            Assert.fail("Exception in test case mentorShouldBeAbleToNavigateToParticularQuestion in class DiagnosticAssessmentFromStudy", e);
        }
    }

    @Test(priority = 14,enabled = true)
    public void instructorShouldBeAbleToViewQuestion() {
        try {
            //tc row no 266
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            CourseOutline courseOutline=PageFactory.initElements(driver,CourseOutline.class);
            String chapterName = ReadTestData.readDataByTagName("", "newChapterName", Integer.toString(266));
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            WebDriverWait wait=new WebDriverWait(driver,200);
            new Assignment().createChapter(266, 1);//create a chapter
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectcourse();//select a course
            Thread.sleep(2000);
            courseOutline.courseOutline.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@title,'Add New Chapter')]")));
           // Actions actions=new Actions(driver);
            //publish chapter
            List<WebElement> allChapters = driver.findElements(By.xpath("//*[starts-with(@class, 'course-chapter-label node course-chapter-label-unpublished')]"));
            for (WebElement chapters : allChapters) {
                if (chapters.getText().contains(chapterName)) {
                    Locatable hoverItem = (Locatable) chapters;
                    Mouse mouse = ((HasInputDevices) driver).getMouse();
                    mouse.mouseMove(hoverItem.getCoordinates());
                }
            }
           // actions.moveToElement(driver.findElement(By.xpath("//div[contains(text(),'"+chapterName+"')]"))).build().perform();
            Thread.sleep(500);
            courseOutline.editButtonAtChapter.click();
            Thread.sleep(500);
            courseOutline.checkBoxToPublishChapter.click();//click on publish
            courseOutline.saveButton.click();//click on save
            Thread.sleep(5000);
            courseOutline.saveMyChanges.click();
            Thread.sleep(2000);
            create(266);//Create an assignment
            addQuestions(2661, "truefalse", "");
            addQuestions(2662, "truefalse", "");
            new LoginUsingLTI().ltiLogin("266");//login as instructor
            new TOCShow().chaptertree();//click on toc
            List<WebElement> ele=driver.findElements(By.xpath("//div[contains(@title,'" + chapterName + "')]"));//click on particular chapter
            driver.findElements(By.xpath("//div[contains(@title,'" + chapterName + "')]")).get(ele.size()-1).click();
            lessonPage.orionAdaptive_link.get(1).click();//click on orionAdaptive
            lessonPage.diagnostic_Arrow.click();//click on '>' link
            String parentWindow=driver.getWindowHandle();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));

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
            Assert.fail("Exception in test case instructorShouldBeAbleToViewQuestion in class DiagnosticAssessmentFromStudy", e);
        }
    }

    @Test(priority = 15,enabled = false)
    public void mentorShouldBeAbleToViewQuestion() {
        try {
            //tc row no 266
            CurrentAssignments currentAssignments = PageFactory.initElements(driver,CurrentAssignments.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            WebDriverWait wait=new WebDriverWait(driver,200);
            new LoginUsingLTI().ltiLogin("26611");//login as mentor
            new TOCShow().chaptertree();//click on toc
           // new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");//click on particular chapter
            new TopicOpen().openLastChapter();
            lessonPage.orionAdaptive_link.get(1).click();//click on orionAdaptive
            lessonPage.diagnostic_Arrow.click();//click on '>' link
            String parentWindow=driver.getWindowHandle();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//div[@class='toc-actions-div']/div[3][@title='Try it']")));

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
            Assert.fail("Exception in test case mentorShouldBeAbleToViewQuestion in class DiagnosticAssessmentFromStudy", e);
        }
    }



    public void create(int dataIndex) {
        try {
            String course = "";
            String overrideDefaultLogin = ReadTestData.readDataByTagName("", "overrideDefaultLogin", Integer.toString(dataIndex));
            String course_type = ReadTestData.readDataByTagName("", "course_type", Integer.toString(dataIndex));
            String course_name = ReadTestData.readDataByTagName("", "course_name", Integer.toString(dataIndex));
            String practice_type = ReadTestData.readDataByTagName("", "practice_type", Integer.toString(dataIndex));

            String overrideDefaultQuestionCreate = ReadTestData.readDataByTagName("", "overrideDefaultQuestionCreate", Integer.toString(dataIndex));

            course = course_name;
            if (course_name == null) {
                if (course_type == null || course_type.equals(""))
                    course = Config.course;

                else if (course_type.equals("ls"))
                    course = Config.lscourse;
            }
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            String questiontype = ReadTestData.readDataByTagName("", "questiontype", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            String courselevel = ReadTestData.readDataByTagName("", "courselevel", Integer.toString(dataIndex));
            String reservforassignment = ReadTestData.readDataByTagName("", "reservforassignment", Integer.toString(dataIndex));

            if (overrideDefaultLogin == null)
                new DirectLogin().CMSLogin();
            else {
                String role = ReadTestData.readDataByTagName("", "role", Integer.toString(dataIndex));
                new DBConnect().Connect();
                ResultSet rs = DBConnect.st.executeQuery("select id,username from t_user where id in (SELECT user_id FROM t_course_user where course_id = 253) and id in(select user_id from t_user_role where type = '" + role + "') limit 1,1;");
                String username = "";
                while (rs.next()) {
                    username = rs.getString("username");
                }
                new DirectLogin().directLoginWithCreditial(username, "snapwiz", 0);
                new Click().clickbyxpath("(//select[@name='product-drop-down']/following-sibling::div/a)[1]");
                new Click().clickbylinkText("Learning Space + Adaptive Component");
                DBConnect.conn.close();
            }

            String title = driver.getTitle();
            if (title.equals("Course Content Details")) {
                driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                //driver.findElement(By.cssSelector("img[alt='"+course+"']")).click();

                if (chapterName == null) {
                    // driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
                    new Click().clickBycssselector("div.course-chapter-label.node");
                } else if (courselevel != null) {
                    new Click().clickBycssselector("div[class='course-label node']");
                } else {
                    List<WebElement> allChapterswithSameName = driver.findElements(By.xpath("//div[contains(@title,'"+chapterName+"')]"));
                    driver.findElements(By.xpath("//div[contains(@title, '"+chapterName+"')]")).get(allChapterswithSameName.size()-1).click();
                    /*for (WebElement chapters : allChapters) {
                        if (chapters.getText().contains(chapterName)) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", chapters);
                            Thread.sleep(4000);
                            break;
                        }

                    }*/

                }
                driver.findElement(By.cssSelector("div.create-practice")).click();

                if (practice_type == null) {
                    new Click().clickByclassname("create-regular-assessment-popup-item"); //click on the create regular assessment
                } else {
                    new Click().clickByclassname("create-file-assessment-popup-item");//click on the create file assessment
                }

                new Click().clickbylinkText("Adaptive Component Diagnostic");
                new Click().clickbylinkText(questiontype);
                int popup = 1;
                try {
                    WebDriverWait wait = new WebDriverWait(driver, 3);
                    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[class='cms-notification-message-body']")));

                } catch (Exception e) {
                    popup = 0;
                }
                //int popup = driver.findElements(By.cssSelector("div[class='cms-notification-message-body']")).size();
                if (popup == 0) {
                    driver.findElement(By.id("assessmentName")).click();
                    driver.findElement(By.id("assessmentName")).clear();
                    driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
                    driver.findElement(By.id("questionSetName")).clear();
                    driver.findElement(By.id("questionSetName")).sendKeys(questionset);

                    if (reservforassignment == null) {
                        driver.findElement(By.xpath("//*[@id='instructor-only-check-box-div']/label")).click(); //checking 'Enable for intructor only'
                    }

                    if(overrideDefaultQuestionCreate ==null){
                        new QuestionCreate().trueFalseQuestions(dataIndex);
                    }else{
                        if(overrideDefaultQuestionCreate.equalsIgnoreCase("essay")){
                            new QuestionCreate().essay(dataIndex);
                        } else if(overrideDefaultQuestionCreate.equalsIgnoreCase("multipart")){
                            new QuestionCreate().multiPartQuestion(dataIndex);
                        }
                    }
                }

            } else {
                Assert.fail("CMS login failed");
            }
            if (!(Config.browser.equalsIgnoreCase("firefox"))) {
                driver.quit();
                startDriver("firefox");
            }
        } catch (Exception e) {
            Assert.fail("Exception in create in Apphelper create in class AssignmentCreate", e);
        }
    }





    public void addQuestions(int dataIndex, String questionType, String assignmentname) {
        try {
            System.out.println("dataIndex:"+dataIndex);
            String course = "";
            assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String overrideDefaultLogin = ReadTestData.readDataByTagName("", "overrideDefaultLogin", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            String courselevel = ReadTestData.readDataByTagName("", "courselevel", Integer.toString(dataIndex));
            String course_type = ReadTestData.readDataByTagName("", "course_type", Integer.toString(dataIndex));
            String course_name = ReadTestData.readDataByTagName("", "course_name", Integer.toString(dataIndex));
            String subSection_level = ReadTestData.readDataByTagName("", "subSection_level", Integer.toString(dataIndex));
            String chapterIndex = ReadTestData.readDataByTagName("", "chapterIndex", Integer.toString(dataIndex));
            String topicIndex = ReadTestData.readDataByTagName("", "topicIndex", Integer.toString(dataIndex));

            course = course_name;

            if (course_name == null) {
                if (course_type == null || course_type.equals(""))
                    course = Config.course;

                else if (course_type.equals("ls"))
                    course = Config.lscourse;
            }
            if (overrideDefaultLogin == null)
                new DirectLogin().CMSLogin();
            else {
                String role = ReadTestData.readDataByTagName("", "role", Integer.toString(dataIndex));
                new DBConnect().Connect();
                ResultSet rs = DBConnect.st.executeQuery("select id,username from t_user where id in (SELECT user_id FROM t_course_user where course_id = 253) and id in(select user_id from t_user_role where type = '" + role + "') limit 1,1;");
                String username = "";
                while (rs.next()) {
                    username = rs.getString("username");
                    //System.out.println(username);
                }
                new DirectLogin().directLoginWithCreditial(username, "snapwiz", dataIndex);
                new Click().clickbyxpath("(//select[@name='product-drop-down']/following-sibling::div/a)[1]");
                new Click().clickbylinkText("Learning Space + Adaptive Component");
            }
            String title = driver.getTitle();
            if (title.equals("Course Content Details")) {
                driver.findElement(By.cssSelector("img[alt=\"" + course + "\"]")).click();
                if (chapterName == null) {
                    driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
                } else if (courselevel != null) {
                    new Click().clickBycssselector("div[class='course-label node']");
                } else {
                    List<WebElement> allChapterswithSameName = driver.findElements(By.xpath("//div[contains(@title,'" + chapterName + "')]"));
                    driver.findElements(By.xpath("//div[contains(@title, '" + chapterName + "')]")).get(allChapterswithSameName.size() - 1).click();
                }
                Thread.sleep(3000);
                if (subSection_level != null) {
                    new SelectCourse().expandChapterTreeByIndex(dataIndex, Integer.parseInt(chapterIndex));
                    new SelectCourse().selectTopicByIndex(dataIndex, Integer.parseInt(topicIndex));

                }
                //driver.findElement(By.cssSelector("div.course-chapter-label.node")).click();
                boolean assignmentExists = false;
                List<WebElement> elements = driver.findElements(By.xpath("//div[@title='" + assignmentname + "']"));
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elements.get(elements.size()-1));
                Thread.sleep(5000);
                try {
                    new Click().clickByElement(elements.get(elements.size()-1));
                    //elements.get(elements.size() - 1).click();
                    assignmentExists = true;
                }
                catch (Exception e) {

                }

                if (assignmentExists == true)
                    addQuestionLink();
                Thread.sleep(2000);
                if (assignmentExists == true) {
                    if (questionType.equalsIgnoreCase("all")) {
                        new QuestionCreate().trueFalseQuestions(dataIndex);
                        addQuestionLink();
                        new QuestionCreate().multipleChoice(dataIndex);
                        addQuestionLink();
                        new QuestionCreate().multipleSelection(dataIndex);
                        addQuestionLink();
                        new QuestionCreate().essay(dataIndex);
                        addQuestionLink();
                        new QuestionCreate().writeBoard(dataIndex);
                        addQuestionLink();
                        new QuestionCreate().audio(dataIndex);
                    } else if (questionType.equals("truefalse") || questionType.equals("qtn-type-true-false-img"))
                        new QuestionCreate().trueFalseQuestions(dataIndex);

                    else if (questionType.equals("multiplechoice") || questionType.equals("qtn-multiple-choice-img"))
                        new QuestionCreate().multipleChoice(dataIndex);

                    else if (questionType.equals("multipleselection") || questionType.equals("qtn-multiple-selection-img"))
                        new QuestionCreate().multipleSelection(dataIndex);

                    else if (questionType.equals("essay") || questionType.equals("qtn-essay-type"))
                        new QuestionCreate().essay(dataIndex);

                    else if (questionType.equals("writeboard") || questionType.equals("qtn-writeboard-type-new"))
                        new QuestionCreate().writeBoard(dataIndex);

                    else if (questionType.equals("audio") || questionType.equals("qtn-audio-type"))
                        new QuestionCreate().audio(dataIndex);

                }

            } //if condition  ends here
            if (!(Config.browser.equalsIgnoreCase("firefox"))) {
                driver.quit();
                startDriver("firefox");
            }
        } catch (Exception e) {

            Assert.fail("Exception in create in Apphelper addQuestions in class AssignmentCreate", e);
        }
    }

    public void addQuestionLink() {
        try {
            Thread.sleep(2000);
            new Click().clickByid("questionOptions");
            Thread.sleep(2000);
            new Click().clickByid("addQuestion");
        } catch (Exception e) {
            Assert.fail("Exception while clicking on add questions link in CMS", e);
        }

    }
}

