package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT24.R2414;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.MyQuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.QuestionBank;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.CourseOutline;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Preview.Preview;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by mukesh on 24/12/15.
 */
public class VerifyOrionAdaptivePracticeTextInInstructorSide extends Driver{
    @Test(priority = 1,enabled = true)
    public void verifyOrionAdaptivePracticeTextFromTOC(){

        try {
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            new LoginUsingLTI().ltiLogin("139"); //login as student
            lessonPage.getToc().click(); //click on the TOC
            new TopicOpen().chapterOpen(2); // Click on chapter 3
            new TopicOpen().clickOnPreview();
            new UIElement().waitAndFindElement(By.className("question-label"));
            Assert.assertEquals(lessonPage.orionAdaptive_link.get(3).getAttribute("title").trim(), "ORION  Ch 3: Biological Macromolecules", "label as “ORION Ch X: Chapter Name” as tooltip is not displaying");
            new MouseHover().mouserhoverbyXpath("//span[@class='cms-content-question-review-list-label']");
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.getDiagnosticTestTitle().getAttribute("title").trim(),"(P3) ORION  Ch 3: Biological Macromolecules","label as “ORION Ch X: Chapter Name” as tooltip is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in TC verifyOrionAdaptivePracticeTextFromTOC of class VerifyOrionAdaptivePracticeTextInInstructorSide",e);
        }
    }
    @Test(priority = 2,enabled = true)
    public void verifyOrionAdaptivePracticeTextForTryIt(){

        try {
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            Preview preview= PageFactory.initElements(driver, Preview.class);

            new LoginUsingLTI().ltiLogin("139"); //login as student
            lessonPage.getToc().click(); //click on the TOC
            new TopicOpen().chapterOpen(2); // Click on chapter 3
            new Navigator().NavigateToOrion();
            lessonPage.practice_Arrow.click(); // click on the static arrow link
            lessonPage.tryIt_link.click();
            String windowHandleBefore = driver.getWindowHandle();
            for(String window : driver.getWindowHandles()){
                driver.switchTo().window(window);
            }
            new UIElement().waitAndFindElement(By.className("question-label"));
            new MouseHover().mouserhoverbyXpath("//span[@id='cms-question-preview-header-ass-name']");
            Thread.sleep(2000);
            Assert.assertEquals(preview.previewHeader.getAttribute("title").trim(),"ORION Ch 3: Biological Macromolecules","label as “ORION Ch X: Chapter Name” as tooltip is not displaying");

            driver.close();

        } catch (Exception e) {
            Assert.fail("Exception in TC verifyOrionAdaptivePracticeTextForTryIt of class VerifyOrionAdaptivePracticeTextInInstructorSide",e);
        }
    }


    @Test(priority = 3,enabled = true)
    public void verifyOrionAdaptivePracticeTextForNewTab(){

        try {
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            new LoginUsingLTI().ltiLogin("139"); //login as student
            lessonPage.getToc().click(); //click on the TOC
            new TopicOpen().chapterOpen(2); // Click on chapter 3
            new TopicOpen().clickOnAdaptivePracticeArrow();
            new TopicOpen().openInnerTopic();
            new UIElement().waitAndFindElement(By.className("question-label"));
            Assert.assertEquals(lessonPage.orionAdaptive_link.get(3).getAttribute("title").trim(), "ORION  Ch 3: Biological Macromolecules", "label as “ORION Ch X: Chapter Name” as tooltip is not displaying");
            new MouseHover().mouserhoverbyXpath("//span[@class='cms-content-question-review-list-label']");
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.getDiagnosticTestTitle().getAttribute("title").trim(), "(P3) ORION  Ch 3: Biological Macromolecules", "label as “ORION Ch X: Chapter Name” as tooltip is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in TC verifyOrionAdaptivePracticeTextForNewTab of class VerifyOrionAdaptivePracticeTextInInstructorSide",e);
        }
    }

    @Test(priority = 4,enabled = true)
    public void verifyOrionAdaptivePracticeTextFromQuestionBank(){

        try {
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);
            String chapterName = ReadTestData.readDataByTagName("", "newChapterName", Integer.toString(157));
            WebDriverWait wait=new WebDriverWait(driver,200);
            new Assignment().createChapter(157,2);//create a chapter
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
            new Assignment().create(157);//Create an diagnostic test
            new Assignment().create(158);//Create an practice test

            new LoginUsingLTI().ltiLogin("157");
            new Navigator().NavigateTo("Question Banks");
            questionBank.questionBankDropdown.click();
            questionBank.practiceSetsOnly.click();
            Thread.sleep(2000);
            Assert.assertEquals(questionBank.assessmentName.get(0).getAttribute("title").trim(), "ORION Ch  79:  Diagnostic_Assessment_IT22_R2414_157", "label as “ORION Ch X: Chapter Name” as tooltip is not displaying");

            questionBank.preview.get(0).click(); //click on the preview link
            Thread.sleep(3000);
            Assert.assertEquals(lessonPage.orionAdaptive_link.get(2).getAttribute("title").trim(), "ORION Ch  79:  Diagnostic_Assessment_IT22_R2414_157", "label as “ORION Ch X: Chapter Name” as tooltip is not displaying");
            new MouseHover().mouserhoverbyXpath("//span[@class='cms-content-question-review-list-label']");
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.getDiagnosticTestTitle().getAttribute("title").trim(), "ORION Ch  79:  Diagnostic_Assessment_IT22_R2414_157", "label as “ORION Ch X: Chapter Name” as tooltip is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in TC verifyOrionAdaptivePracticeTextFromQuestionBank of class VerifyOrionAdaptivePracticeTextInInstructorSide",e);
        }
    }

    @Test(priority = 5,enabled = true)
    public void verifyOrionAdaptivePracticeTextFromMyQuestionBank(){

        try {
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);

            new LoginUsingLTI().ltiLogin("157");
            new Navigator().NavigateTo("Question Banks");
            questionBank.questionBankDropdown.click();
            questionBank.practiceSetsOnly.click();
            questionBank.getAddToMyQuestionBank().click();//bookmark
            new Navigator().NavigateTo("My Question Bank");
            Thread.sleep(3000);
            new MouseHover().mouserhover("resource-title ");
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.resource_title.getAttribute("title").trim(),"ORION Ch  79:  Diagnostic_Assessment_IT22_R2414_157","label as “ORION Ch X: Chapter Name” as tooltip is not displaying");
            myQuestionBank.previewLink.get(0).click();
            Thread.sleep(3000);
            Assert.assertEquals(lessonPage.orionAdaptive_link.get(2).getAttribute("title").trim(), "ORION Ch  79:  Diagnostic_Assessment_IT22_R2414_157", "label as “ORION Ch X: Chapter Name” as tooltip is not displaying");
            new MouseHover().mouserhoverbyXpath("//span[@class='cms-content-question-review-list-label']");
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.getDiagnosticTestTitle().getAttribute("title").trim(), "ORION Ch  79:  Diagnostic_Assessment_IT22_R2414_157", "label as “ORION Ch X: Chapter Name” as tooltip is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in TC verifyOrionAdaptivePracticeTextFromMyQuestionBank of class VerifyOrionAdaptivePracticeTextInInstructorSide",e);
        }
    }

    @Test(priority = 6,enabled = true)
    public void verifyOrionAdaptivePracticeTextFromMyQuestionBankTryIT(){

        try {
            LessonPage lessonPage= PageFactory.initElements(driver, LessonPage.class);
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            Preview preview= PageFactory.initElements(driver, Preview.class);

            new LoginUsingLTI().ltiLogin("157");
         /*   new Navigator().NavigateTo("Question Banks");
            questionBank.questionBankDropdown.click();
            questionBank.practiceSetsOnly.click();
            questionBank.getAddToMyQuestionBank().click();//bookmark*/
            new Navigator().NavigateTo("My Question Bank");
            Thread.sleep(3000);
            new MouseHover().mouserhover("resource-title ");
            Thread.sleep(2000);
            Assert.assertEquals(lessonPage.resource_title.getAttribute("title").trim(),"ORION Ch  79:  Diagnostic_Assessment_IT22_R2414_157","label as “ORION Ch X: Chapter Name” as tooltip is not displaying");
            myQuestionBank.tryIT_link.click();
            for(String window : driver.getWindowHandles()){
                driver.switchTo().window(window);
            }
            new UIElement().waitAndFindElement(By.className("question-label"));
            new MouseHover().mouserhoverbyXpath("//span[@id='cms-question-preview-header-ass-name']");
            Thread.sleep(2000);
            Assert.assertEquals(preview.previewHeader.getAttribute("title").trim(),"ORION Ch 3: Biological Macromolecules","label as “ORION Ch X: Chapter Name” as tooltip is not displaying");

            driver.close();

        } catch (Exception e) {
            Assert.fail("Exception in TC verifyOrionAdaptivePracticeTextFromMyQuestionBankTryIT of class VerifyOrionAdaptivePracticeTextInInstructorSide",e);
        }
    }

}
