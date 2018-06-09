package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT23.R237;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by priyanka on 10/1/2015.
 */
public class DiscussionForStaticInQuestionReviewPage extends Driver {
    @Test(priority = 1, enabled = true)
    public void discussionForStaticInQuestionReviewPage() {
        try {
            // tc row no 1244
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions questions = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions.class);

            new LoginUsingLTI().ltiLogin("1244_1"); //login as student
            new TOCShow().chaptertree();//click on chapter tree
            new SelectCourse().selectInvisibleAssignment("1.1 Concept Check");
            lessonPage.submitAnswer.click();//click on submit
            Thread.sleep(3000);
            verifyLanguagePaletteWithSpanish();

            lessonPage.button_next.click();
            lessonPage.submitAnswer.click();//click on submit
            Thread.sleep(2000);
            lessonPage.button_next.click();
            lessonPage.submitAnswer.click();//click on submit
            Thread.sleep(2000);
            lessonPage.getButton_finish_lsCourse.click();//click on finish
            Thread.sleep(2000);

            questions.question_card.get(0).click();//click on 1st card
            verifyLanguagePaletteWithSpanish();

            new Navigator().NavigateTo("Proficiency Report");
            new QuestionCard().clickOnCard("1244",0);
            verifyLanguagePaletteWithSpanish();

            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            Assert.assertTrue(courseStreamPage.courseStream_content.getText().trim().contains("áÁéÉíÍñÑóÓúÚüÜ¡¿"), "All the Comments added by the Student is not displaying at the instructor side.");
            new AddCommentInCSPage().verificationForSpanishInCSPage();
            courseStreamPage.getJumpOut().click();//click on the jump out icon
            Thread.sleep(7000);
            verifyLanguagePaletteWithSpanishAfterJumpOut();

            new LoginUsingLTI().ltiLogin("1244"); //login as instructor
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            Assert.assertTrue(courseStreamPage.courseStream_content.getText().trim().contains("áÁéÉíÍñÑóÓúÚüÜ¡¿"), "All the Comments added by the Student is not displaying at the instructor side.");
            new AddCommentInCSPage().verificationForSpanishInCSPage();
            courseStreamPage.getJumpOut().click();//click on the jump out icon
            Thread.sleep(7000);
            verifyLanguagePaletteWithSpanishAfterJumpOut();


        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToUseNewLanguagesMyQuestionBank  of class DiscussionForStaticInQuestionReviewPage:", e);
        }

    }



    @Test(priority = 2, enabled = true)
    public void discussionForDiagnosticPerformanceSummaryPage() {
        try {
            // tc row no 1290
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions questions = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions.class);

            new LoginUsingLTI().ltiLogin("1290_1"); //login as student
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new DiagnosticTest().startTest(1);//Start diagnostic test
            new DiagnosticTest().DiagonesticTestQuitBetween(1, 2, "correct", false, false, true);//continue the diagnostic test after attempting 12 questions
            Thread.sleep(3000);
            questions.question_card.get(0).click();//click on 1st card
            verifyLanguagePaletteWithSpanish();

            new Navigator().NavigateTo("Proficiency Report");
            new QuestionCard().clickOnCard("1290",0);
            verifyLanguagePaletteWithSpanish();

            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            Assert.assertTrue(courseStreamPage.courseStream_content.getText().trim().contains("áÁéÉíÍñÑóÓúÚüÜ¡¿"), "All the Comments added by the Student is not displaying at the instructor side.");
            new AddCommentInCSPage().verificationForSpanishInCSPage();
            courseStreamPage.getJumpOut().click();//click on the jump out icon
            Thread.sleep(7000);
            verifyLanguagePaletteWithSpanishAfterJumpOut();

            new LoginUsingLTI().ltiLogin("1290"); //login as instructor
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            Assert.assertTrue(courseStreamPage.courseStream_content.getText().trim().contains("áÁéÉíÍñÑóÓúÚüÜ¡¿"), "All the Comments added by the Student is not displaying at the instructor side.");
            new AddCommentInCSPage().verificationForSpanishInCSPage();
            courseStreamPage.getJumpOut().click();//click on the jump out icon
            Thread.sleep(7000);
            verifyLanguagePaletteWithSpanishAfterJumpOut();


        } catch (Exception e) {
            Assert.fail("Exception in test case discussionForDiagnosticPerformanceSummaryPage  of class DiscussionForStaticInQuestionReviewPage:", e);
        }

    }

    @Test(priority = 3, enabled = true)
    public void discussionForAdaptivePracticePage() {
        try {
            // tc row no 1274
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions questions = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions.class);

            new LoginUsingLTI().ltiLogin("1290_1"); //login as student
            new Navigator().NavigateTo("eTextBook");//navigate to e-textbook
            new PracticeTest().startTest(); //start practice test
            for(int i = 0; i < 3; i++)
            {
                new PracticeTest().AttemptCorrectAnswer(0,"1290");
            }
            new PracticeTest().quitThePracticeTest();
            Thread.sleep(3000);
            questions.question_card.get(0).click();//click on 1st card
            verifyLanguagePaletteWithSpanish();

            new Navigator().NavigateTo("Proficiency Report");
            new QuestionCard().clickOnCard("1290",0);
            verifyLanguagePaletteWithSpanish();

            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            Assert.assertTrue(courseStreamPage.courseStream_content.getText().trim().contains("áÁéÉíÍñÑóÓúÚüÜ¡¿"), "All the Comments added by the Student is not displaying at the instructor side.");
            new AddCommentInCSPage().verificationForSpanishInCSPage();
            courseStreamPage.getJumpOut().click();//click on the jump out icon
            Thread.sleep(7000);
            verifyLanguagePaletteWithSpanishAfterJumpOut();

            new LoginUsingLTI().ltiLogin("1290"); //login as instructor
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            Assert.assertTrue(courseStreamPage.courseStream_content.getText().trim().contains("áÁéÉíÍñÑóÓúÚüÜ¡¿"), "All the Comments added by the Student is not displaying at the instructor side.");
            new AddCommentInCSPage().verificationForSpanishInCSPage();
            courseStreamPage.getJumpOut().click();//click on the jump out icon
            Thread.sleep(7000);
            verifyLanguagePaletteWithSpanishAfterJumpOut();


        } catch (Exception e) {
            Assert.fail("Exception in test case discussionForAdaptivePracticePage  of class DiscussionForStaticInQuestionReviewPage:", e);
        }

    }




    @Test(priority = 3, enabled = true)
    public void discussionForNonGreadableAssignment() {
        try {
            // tc row no 1315
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions questions = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions.class);

            new Assignment().create(1315);
            new LoginUsingLTI().ltiLogin("1315"); //login as instructor
            new Assignment().assignToStudent(1315);
            new LoginUsingLTI().ltiLogin("1315_1"); //login as student
            new Assignment().submitAssignmentAsStudent(1315);
            Thread.sleep(3000);
            questions.question_card.get(0).click();//click on 1st card
            verifyLanguagePaletteWithSpanish();

        } catch (Exception e) {
            Assert.fail("Exception in test case discussionForAdaptivePracticePage  of class DiscussionForStaticInQuestionReviewPage:", e);
        }

    }


    @Test(priority = 5, enabled = true)
    public void discussionForGreadableAssignment() {
        try {
            // tc row no 1315
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions questions = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions.class);

            new LoginUsingLTI().ltiLogin("1316"); //login as instructor
            new Assignment().assignToStudent(1316);
            new LoginUsingLTI().ltiLogin("1316_1"); //login as student
            new Assignment().submitAssignmentAsStudent(1316);
            Thread.sleep(3000);
            questions.question_card.get(0).click();//click on 1st card
            verifyLanguagePaletteWithSpanish();

        } catch (Exception e) {
            Assert.fail("Exception in test case discussionForGreadableAssignment  of class DiscussionForStaticInQuestionReviewPage:", e);
        }

    }


    @Test(priority = 5, enabled = true)
    public void discussionForGreadableAssignmentWithPolicyOne() {
        try {
            // tc row no 1317
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions questions = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "1317");

            new LoginUsingLTI().ltiLogin("1317"); //login as instructor
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");//till save policy
            new Assignment().assignToStudent(1317);
            new LoginUsingLTI().ltiLogin("1317_1"); //login as student
            new Assignment().submitAssignmentAsStudent(1317);
            Thread.sleep(3000);
            questions.question_card.get(0).click();//click on 1st card
            verifyLanguagePaletteWithSpanish();

        } catch (Exception e) {
            Assert.fail("Exception in test case discussionForGreadableAssignmentWithPolicyOne  of class DiscussionForStaticInQuestionReviewPage:", e);
        }

    }

    @Test(priority = 6, enabled = true)
    public void discussionForGreadableAssignmentWithPolicyThree() {
        try {
            // tc row no 1317
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions questions = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "1317");

            new LoginUsingLTI().ltiLogin("1318"); //login as instructor
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Release as they are being graded", "", "", "");//till save policy
            new Assignment().assignToStudent(1318);
            new LoginUsingLTI().ltiLogin("1318_1"); //login as student
            new Assignment().submitAssignmentAsStudent(1318);
            Thread.sleep(3000);
            questions.question_card.get(0).click();//click on 1st card
            verifyLanguagePaletteWithSpanish();

        } catch (Exception e) {
            Assert.fail("Exception in test case discussionForGreadableAssignmentWithPolicyThree  of class DiscussionForStaticInQuestionReviewPage:", e);
        }

    }

    @Test(priority = 7, enabled = true)
    public void discussionForGreadableAssignmentWithPolicyFour() {
        try {
            // tc row no 1318
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions questions = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "1317");

            new LoginUsingLTI().ltiLogin("1319"); //login as instructor
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Release explicitly by the instructor", "", "", "");//till save policy
            new Assignment().assignToStudent(1319);
            new LoginUsingLTI().ltiLogin("1319_1"); //login as student
            new Assignment().submitAssignmentAsStudent(1319);
            Thread.sleep(3000);
            questions.question_card.get(0).click();//click on 1st card
            verifyLanguagePaletteWithSpanish();

        } catch (Exception e) {
            Assert.fail("Exception in test case discussionForGreadableAssignmentWithPolicyFour  of class DiscussionForStaticInQuestionReviewPage:", e);
        }

    }

    public void verifyLanguagePaletteWithSpanish() {


        com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
        ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

        new Navigator().navigateToTab("Discussion"); //navigate to question tab
        String inputStringValue = null;
        int height = 0;
        int height1 = 0;
        new UIElement().waitAndFindElement(discussion.newDiscussion_button);
        discussion.newDiscussion_button.click(); //click on the new discussion
        courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon

        Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
        Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
        Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");

        manageContent.langaugePalette_spanish.click(); //click on the spanish language
        Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

        for (WebElement element : manageContent.langaugePalette_allinput) {
            element.click();
        }

        inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
        System.out.println("input String value:" + inputStringValue);

        Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");

        //Tc row no 17
        Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

        //Tc row no 18
        Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
        //Tc row no 19
        Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
        Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

        //TC row no 20
        Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

        //TC row no 21
        Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
        //TC row no 22
        height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

        manageContent.langaugePalette_saveButton.click(); //click on the save button

        //TC row no 23
        Assert.assertEquals(discussion.discussion_text.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");

        //TC row no 24
        courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
        manageContent.langaugePalette_spanish.click(); //click on the spanish language
        for (WebElement element : manageContent.langaugePalette_allinput) {
            element.click();
            element.click();
            element.click();
            element.click();
            element.click();
            element.click();
            element.click();
            element.click();
            element.click();
        }
        height1 = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 3));
        manageContent.langaugePalette_saveButton.click(); //click on the save button
        discussion.submit_button.click(); //click on the save button

        //Tc row no 106
        if (height > height1) {
            Assert.fail("The height of the question textbox is not  increased accordingly");
        }

    }


    public void verifyLanguagePaletteWithSpanishAfterJumpOut() {
        try {

            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion discussion = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.Discussion.class);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

            List<WebElement> comment = driver.findElements(By.xpath("//li[@class='stream-content-posts-list']"));
            comment.get(0).click();
            Thread.sleep(2000);
            new Click().clickbyxpath("//div[@class='prow editdiscussion-wrapper']//i[@class='ls-icon-img ls--comments-icon']");
            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();

            Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
            Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
            Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");

            new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);

            Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");

            //Tc row no 17
            Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");

            //Tc row no 18
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
            //Tc row no 19
            Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
            Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");

            //TC row no 20
            Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            //TC row no 21
            Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
            //TC row no 22
            int height = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

            manageContent.langaugePalette_saveButton.click(); //click on the save button

            //TC row no 23
            Assert.assertEquals(courseStreamPage.commentContentIn_editComment.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");

            //TC row no 24
            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
            manageContent.langaugePalette_spanish.click(); //click on the spanish language
            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
            }

            Thread.sleep(3000);
            int height1 = Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 3));
            manageContent.langaugePalette_saveButton.click(); //click on the save button

            System.out.println("height :"+height);
            System.out.println("height1 :"+height1);

            //Tc row no 106
            if (height > height1) {
                Assert.fail("The height of the question textbox is not  increased accordingly");
            }

            new LangaugePaletteForQuestionCreation().clickOnLanguagePalette();
            new LangaugePaletteForQuestionCreation().clickOnTheSpanish();
            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            //TC row no 25
            manageContent.langaugePalette_cancelButton.click();//click on the cancel button

            Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");
            Assert.assertEquals(courseStreamPage.commentContentIn_editComment.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿áááááááááÁÁÁÁÁÁÁÁÁéééééééééÉÉÉÉÉÉÉÉÉíííííííííÍÍÍÍÍÍÍÍÍñññññññññÑÑÑÑÑÑÑÑÑóóóóóóóóóÓÓÓÓÓÓÓÓÓúúúúúúúúúÚÚÚÚÚÚÚÚÚüüüüüüüüüÜÜÜÜÜÜÜÜÜ¡¡¡¡¡¡¡¡¡¿¿¿¿¿¿¿¿¿", "All the characters is not getting added to the question content area.");

        } catch (Exception e) {
            Assert.fail("Exception in test case verifyLanguagePaletteWithSpanishAfterJumpOut  of class DiscussionForStaticInQuestionReviewPage:", e);
        }

    }
}