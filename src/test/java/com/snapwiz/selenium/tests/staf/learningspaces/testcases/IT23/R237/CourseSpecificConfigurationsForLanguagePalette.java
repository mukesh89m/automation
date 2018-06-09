package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT23.R237;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.CourseOutline;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.AssignmentTab;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
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
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.List;

/**
 * Created by priyanka on 9/18/2015.
 */
public class CourseSpecificConfigurationsForLanguagePalette extends Driver {
    @Test(priority = 1, enabled = true)
    public void courseSpecificConfigurationsForLanguagePalette() {
        try {
            // tc row no 279

            CourseOutline courseOutline = PageFactory.initElements(driver, CourseOutline.class);
            new DirectLogin().CMSLogin(); //CMS login as author
            new SelectCourse().selectcourse();
            courseOutline.courseDetails.click(); //click on the courseDetails icon
            Assert.assertEquals(courseOutline.courseDetails.getText().trim(), "Course Details", "Course Details is not displaying");
            Assert.assertEquals(courseOutline.courseAssignmentPolicyLink.getText().trim(), "Course Settings", "Course Assignment Policy is not displaying");
            courseOutline.courseAssignmentPolicyLink.click();//Click on "Course Assignment Policy option"
            Thread.sleep(3000);
            Assert.assertEquals(courseOutline.courseAssignmentPolicyHeader.isDisplayed(), true, " popup is not getting open");
            Assert.assertEquals(courseOutline.courseAssignmentPolicyHeader.getText().trim(), "Course Settings", "Course Assignment Policy title is not displaying");
            courseOutline.getHelpIcon().click();//click on the help pop up
            String popText = "Use this setting to enable or disable course level options which are applicable to specific courses only.";
            Assert.assertEquals(courseOutline.helpText.getText().trim(), popText, "Use this policy to enable or disable special policy setting which are applicable to specific courses only. is not showing");
            Assert.assertEquals(courseOutline.gradingPolicy_Text.get(0).getText().trim(), "Language grading policy", "Language grading policy is not available");
            Assert.assertEquals(courseOutline.gradingPolicy_Text.get(1).getText().trim(), "Language palette for social collaboration", "Language palette for social collaboration is not available");
            Assert.assertEquals(courseOutline.checkedRadioButton.get(1).isDisplayed(), true, "disable radio button is not checked by default");
            Assert.assertEquals(courseOutline.cancel_button.getText().trim(), "Cancel", "Cancel link is not available");
            Assert.assertEquals(courseOutline.save_button.getText().trim(), "Save", "Save button is not available");
            courseOutline.helpIcon_LanguagePalette.click(); //click on the help icon after Language grading policy
            String popText1 = "Use this policy to enable or disable language palette option for social posts and comments.";
            Assert.assertEquals(courseOutline.helpText_LanguagePalette.getText().trim(), popText1, "Help message is not displaying");
            courseOutline.cancel_button.click();//click on the cancel button
            Thread.sleep(3000);
            List<WebElement> list1 = driver.findElements(By.className("course-assignment-policy-body-content"));
            Assert.assertTrue(list1.size() == 0, "Dropdown is displayed after clicking on course Assignment Policy link");
            courseOutline.courseDetails.click(); //click on the courseDetails icon
            Thread.sleep(3000);
            driver.findElement(By.xpath("//a[@rel='3']")).click();
            Thread.sleep(3000);
            courseOutline.enable_radioButtonForLanguagePalette.click();//click on enable language palette radio button
            courseOutline.save_button.click();

        } catch (Exception e) {
            Assert.fail("Exception in test case courseSpecificConfigurationsForLanguagePalette of class CourseSpecificConfigurationsForLanguagePalette:", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void fileBasedAssessment() {
        try {
            // tc row no 292
            String filename = ReadTestData.readDataByTagName("", "filename", Integer.toString(292));
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectcourse();//select course biology
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            manageContent.createPractice.click();//click on create practice
            manageContent.createFileBasedAssessment.click();//click on the create file assessment
            WebDriverWait wait = new WebDriverWait(driver, 120);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='File Based Assessment']")));
            manageContent.fileBasedAssessmentName.sendKeys("FileBased Assessment"); // give assessment name
            Thread.sleep(2000);
            manageContent.questionPrompt.click();//click on question prompt
            manageContent.langaugePalette.click();//click on language palette
            Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
            Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
            Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");
            manageContent.langaugePalette_spanish.click(); //click on the spanish language
            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);
            Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");
            Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
            Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
            Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");
            Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");
            Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
            manageContent.langaugePalette_saveButton.click(); //click on the save button
            Assert.assertEquals(manageContent.questionPrompt.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");
            manageContent.langaugePalette.click();//click on language palette
            manageContent.langaugePalette_spanish.click(); //click on the spanish language
            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
            }
            manageContent.langaugePalette_cancelButton.click();//click on the cancel button
            Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");
            Assert.assertEquals(manageContent.questionPrompt.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");
            manageContent.questionPrompt.click();//click on prompt details
            new UIElement().waitAndFindElement(manageContent.langaugePalette);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", manageContent.langaugePalette);//click on the langauge pallete
            manageContent.langaugePalette_spanish.click(); //click on the spanish language
            manageContent.langaugePalette_textBox.sendKeys("abc123");

            manageContent.langaugePalette_closeIcon.click();//click on the close icon
            Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");


            manageContent.questionPrompt.click();//click on question text box
            manageContent.questionPrompt.clear();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
            Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
            Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
            Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");
            manageContent.langaugePalette_French.click(); //click on the spanish language
            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");
            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }
            String inputStringValue1 = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue1);
            Assert.assertEquals(inputStringValue1.length(), 24, "All the characters is not supported as existing Functionality.");
            Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
            Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
            Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");
            Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "French Editor", "The popup is not having text has \"French Editor\" text over header, with Left aligned.");
            Assert.assertEquals(inputStringValue1, "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ", "The selected characters is not displayed in the text box of the Palette. ");
            manageContent.langaugePalette_saveButton.click(); //click on the save button
            Assert.assertEquals(manageContent.questionPrompt.getText().trim(), "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ", "All the characters is not getting added to the question content area.");
            manageContent.langaugePalette.click();//click on language palette
            manageContent.langaugePalette_French.click(); //click on the French language
            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
                element.click();
                element.click();
            }

            manageContent.langaugePalette_cancelButton.click();//click on the cancel button
            Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");
            Assert.assertEquals(manageContent.questionPrompt.getText().trim(), "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ", "All the characters is not getting added to the question content area.");
            manageContent.questionPrompt.click();//click on question text box
            new UIElement().waitAndFindElement(manageContent.langaugePalette);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", manageContent.langaugePalette);//click on the langauge pallete
            manageContent.langaugePalette_French.click(); //click on the French language
            manageContent.langaugePalette_textBox.sendKeys("abc123");
            manageContent.langaugePalette_closeIcon.click();//click on the close icon
            Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");


            manageContent.questionPrompt.click();//click on question text box
            manageContent.questionPrompt.clear();
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
            Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
            Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(), "Italian");
            Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(), "French");
            manageContent.langaugePalette_Italian.click(); //click on the spanish language
            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");

            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            String inputStringValue2 = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue2);
            Assert.assertEquals(inputStringValue2.length(), 22, "All the characters is not supported as existing Functionality.");
            Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(), "The pop up is not having a text area..");
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
            Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
            Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");
            Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Italian Editor", "The popup is not having text has \"Italian Editor\" text over header, with Left aligned.");
            Assert.assertEquals(inputStringValue2, "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "The selected characters is not displayed in the text box of the Palette. ");
            manageContent.langaugePalette_saveButton.click(); //click on the save button
            Assert.assertEquals(manageContent.questionPrompt.getText().trim(), "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "All the characters is not getting added to the question content area.");
            manageContent.langaugePalette.click();
            manageContent.langaugePalette_Italian.click(); //click on the Italian language
            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
            }
            manageContent.langaugePalette_cancelButton.click();//click on the cancel button
            Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");
            Assert.assertEquals(manageContent.questionPrompt.getText().trim(), "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "All the characters is not getting added to the question content area.");
            manageContent.questionPrompt.click();//click on question text box
            new UIElement().waitAndFindElement(manageContent.langaugePalette);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", manageContent.langaugePalette);//click on the langauge pallete
            manageContent.langaugePalette_Italian.click(); //click on the spanish language
            manageContent.langaugePalette_textBox.sendKeys("abc123");
            manageContent.langaugePalette_closeIcon.click();//click on the close icon
            Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");
            manageContent.uploadButton.click();//click on upload
            new KeysSend().sendKeyBoardKeys("$" + Config.fileUploadPath + filename + "^");
            Thread.sleep(2000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='ls-delete-file-icon']")));
            manageContent.saveButtonFileBased.click(); //click on save button
            Thread.sleep(2000);
            List<WebElement> elements = driver.findElements(By.xpath("//div[@title='FileBased Assessment']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elements.get(elements.size() - 1));
            Thread.sleep(2000);
            new Click().clickByElement(elements.get(elements.size() - 1));
            Thread.sleep(1000);
            Assert.assertEquals(manageContent.questionPrompt.getText().trim(), "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "All the characters is not getting added to the question content area.");


        } catch (Exception e) {
            Assert.fail("Exception in test case fileBasedAssessment of class CourseSpecificConfigurationsForLanguagePalette:", e);
        }

    }

    @Test(priority = 3, enabled = true)
    public void verifyFileBasedAssessment() {
        try {
            // tc row no 337
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(337));
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);

            new LoginUsingLTI().ltiLogin("337"); //login as instructor
            new Navigator().NavigateTo("My Question Bank"); //navigate to question bank
            questionBank.getQuestionBankTitle().click();//click on question bank tab
            Thread.sleep(2000);
            questionBank.getSearchInputFieldOnQuestionBank().get(1).sendKeys("\"" + assignmentname + "\"");
            questionBank.getSearchButtonOnQuestionBank().get(1).click();
            Thread.sleep(4000);
            questionBank.getPreviewButton().click();//click on preview button
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("cms-content-question-review-list-label")));
            Assert.assertEquals(questionBank.languagePreview.get(0).getText().trim(), "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "Entry is not displaying for language palette.");
            questionBank.getQuestionBankTitle().click();//click on question bank tab
            Thread.sleep(2000);
            questionBank.getSearchInputFieldOnQuestionBank().get(1).sendKeys("\"" + assignmentname + "\"");
            questionBank.getSearchButtonOnQuestionBank().get(1).click();
            Thread.sleep(2000);
            questionBank.getAddToMyQuestionBank().click();//click on add to my question bank
            Thread.sleep(1000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank tab
            Thread.sleep(8000);
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ls-preview-wrapper action-links']"));
            List<WebElement> preview = driver.findElements(By.cssSelector("span[class='ls-preview-wrapper action-links']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", preview.get(0));
            Thread.sleep(8000);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("cms-content-question-review-list-label")));
            Assert.assertEquals(questionBank.languagePreview.get(0).getText().trim(), "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "Entry is not displaying for language palette.");
            new Assignment().assignFileBasedAssignmentFromMyQuestionBank(337);
            currentAssignments.getList_assignmentName().get(0).click();//click on assignment name
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("cms-content-question-review-list-label")));
            Assert.assertEquals(questionBank.languagePreview.get(0).getText().trim(), "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "Entry is not displaying for language palette.");

            new LoginUsingLTI().ltiLogin("337_1"); //login as student
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssessmentName().click();//click on assignment
            new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title")));
            assignmentTab.finishButton.click();//click on finish assignment
            Thread.sleep(1000);

            new LoginUsingLTI().ltiLogin("337"); //login as instructor
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getViewGrade_link().click();//click on view student response
            assignmentResponsesPage.assessmentName_AssignmentResponse.get(1).click();//click on assignment name
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("cms-content-question-review-list-label")));
            Assert.assertEquals(questionBank.languagePreview.get(0).getText().trim(), "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "Entry is not displaying for language palette.");
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.assessmentName.click();//click on assignment name
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("cms-content-question-review-list-label")));
            Assert.assertEquals(questionBank.languagePreview.get(0).getText().trim(), "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "Entry is not displaying for language palette.");

            new LoginUsingLTI().ltiLogin("337_1"); //login as student
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssessmentName().click();//click on assignment
            new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title")));
            Assert.assertEquals(questionBank.languagePreview.get(0).getText().trim(), "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "Entry is not displaying for language palette.");
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.assessmentName.click();//click on assignment name
            new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title")));
            Assert.assertEquals(questionBank.languagePreview.get(0).getText().trim(), "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "Entry is not displaying for language palette.");

        } catch (Exception e) {
            Assert.fail("Exception in test case verifyFileBasedAssessment of class CourseSpecificConfigurationsForLanguagePalette:", e);
        }

    }

    @Test(priority = 4, enabled = true)
    public void authorCreatedAsessmentWhichHaveTextEntryQuestion() {
        try {
            // tc row no 355
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", "355");
            String QuestionNo = ReadTestData.readDataByTagName("", "QuestionNo", "355");
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);

            new Assignment().create(355);
            new Assignment().addQuestions(355, "textentry", "");

            new LoginUsingLTI().ltiLogin("355"); //login as instructor
            new Assignment().assignToStudent(355);

            new LoginUsingLTI().ltiLogin("355_1"); //login as student1
            new Assignment().submitAssignmentAsStudent(355);

            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectcourse();//select a course
            Thread.sleep(2000);
            driver.findElements(By.cssSelector("div[class='course-chapter-label node']")).get(0).click();//select 3rd chapter

            List<WebElement> elements = driver.findElements(By.xpath("//div[@title='" + assignmentname + "']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elements.get(elements.size() - 1));
            Thread.sleep(2000);
            new Click().clickByElement(elements.get(elements.size() - 1));
            Thread.sleep(1000);
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@title='Jump To Q#']")));
            manageContent.jumpToQuestion.click();//click on jump to question
            Thread.sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.linkText(QuestionNo)));
            driver.findElement(By.linkText(QuestionNo)).click();
            Thread.sleep(2000);
            manageContent.newLink.click();//click on new link
            manageContent.revisionLink.click(); // click on revisions
            manageContent.createNewVersion.click();//click on the create new version  button
            Thread.sleep(4000);
            manageContent.languageDropDown.click(); //select language
            manageContent.italianLanguage.click();//click on italian
            manageContent.draftLink.click();//click on draft
            manageContent.publishLink.click();//click on publish
            Thread.sleep(4000);
            manageContent.save_Button.click();//click on save

            new LoginUsingLTI().ltiLogin("355_2"); //login as student2
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssessmentName().click();//click on assignment
            new AttemptQuestion().trueFalse(false, "correct", 355);
            assignments.getNextQuestion().click();
            new AttemptQuestion().textEntryWithItalian(false, "correct", 355);
            new Assignment().submitButtonInQuestionClick();
            Thread.sleep(3000);

        } catch (Exception e) {
            Assert.fail("Exception in test case verifyFileBasedAssessment of class CourseSpecificConfigurationsForLanguagePalette:", e);
        }

    }


    @Test(priority = 5, enabled = true)
    public void  instructorAbleToUseThePaletteInCourseStream() {
        try {
            // tc row no 361
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            JavascriptExecutor jse = (JavascriptExecutor) driver;

            new LoginUsingLTI().ltiLogin("361"); //login as instructor
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.getPost_button().click();//click on post
            courseStreamPage.textEditor.click();//click on text editor
            Assert.assertEquals(courseStreamPage.languages.get(0).isDisplayed(), true, "language palette icon is not displying");

            courseStreamPage.languages.get(0).click();//click on language palette
            Assert.assertEquals(courseStreamPage.languageOptions.get(0).getText(), "Spanish");
            Assert.assertEquals(courseStreamPage.languageOptions.get(1).getText(), "Italian");
            Assert.assertEquals(courseStreamPage.languageOptions.get(2).getText(), "French");
            courseStreamPage.languageOptions.get(0).click();//click on spanish
            Assert.assertTrue(courseStreamPage.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");
            Assert.assertTrue(courseStreamPage.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");
            Assert.assertTrue(courseStreamPage.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
            Assert.assertEquals(courseStreamPage.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL,"c"));
            String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);
            Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");
            Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame);
            String spanishText=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(spanishText,"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.languages.get(0).click();//click on language palette
            courseStreamPage.languageOptions.get(0).click();//click on spanish
            Actions action=new Actions(driver);
            WebElement From=driver.findElement(By.cssSelector("a[class='navigator ls-toc-sprite']"));
            courseStreamPage.langaugePalette_header.click();
            action.dragAndDrop(courseStreamPage.langaugePalette_header,From).build().perform();
            From.click();
            courseStreamPage.languages.get(0).click();//click on language palette
            courseStreamPage.languageOptions.get(0).click();//click on spanish
            manageContent.langaugePalette_textBox.sendKeys("abc123");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame);
            String spanishText1=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(spanishText1, "áÁéÉíÍñÑóÓúÚüÜ¡¿abc123", "All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.cancelPost.click();//click on cancel


            courseStreamPage.getPost_button().click();//click on post
            courseStreamPage.textEditor.click();//click on text editor
            courseStreamPage.languages.get(0).click();//click on language palette
            Assert.assertEquals(courseStreamPage.languageOptions.get(0).getText(), "Spanish");
            Assert.assertEquals(courseStreamPage.languageOptions.get(1).getText(), "Italian");
            Assert.assertEquals(courseStreamPage.languageOptions.get(2).getText(), "French");
            courseStreamPage.languageOptions.get(1).click();//click on italian
            Assert.assertTrue(courseStreamPage.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");
            Assert.assertTrue(courseStreamPage.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");
            Assert.assertTrue(courseStreamPage.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
            Assert.assertEquals(courseStreamPage.langaugePalette_header.getText().trim(), "Italian Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL,"c"));
            String inputStringValue1 = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue1);
            Assert.assertEquals(inputStringValue1.length(), 22, "All the characters is not supported as existing Functionality.");
            Assert.assertEquals(inputStringValue1, "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "The selected characters is not displayed in the text box of the Palette. ");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame);
            String italianText=driver.findElement(By.xpath("/html/body")).getText();
            String act=italianText.substring(0, 22);
            Assert.assertEquals(act,"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤","All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.languages.get(0).click();//click on language palette
            courseStreamPage.languageOptions.get(1).click();//click on italian
            courseStreamPage.langaugePalette_header.click();
            action.dragAndDrop(courseStreamPage.langaugePalette_header,From).build().perform();
            From.click();
            courseStreamPage.languages.get(0).click();//click on language palette
            courseStreamPage.languageOptions.get(1).click();//click on italian
            manageContent.langaugePalette_textBox.sendKeys("abc123");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame);
            String italianText1=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(italianText1,"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤abc123","All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.cancelPost.click();//click on cancel

            courseStreamPage.getPost_button().click();//click on post
            courseStreamPage.textEditor.click();//click on text editor
            courseStreamPage.languages.get(0).click();//click on language palette
            Assert.assertEquals(courseStreamPage.languageOptions.get(0).getText(), "Spanish");
            Assert.assertEquals(courseStreamPage.languageOptions.get(1).getText(), "Italian");
            Assert.assertEquals(courseStreamPage.languageOptions.get(2).getText(), "French");
            courseStreamPage.languageOptions.get(2).click();//click on french
            Assert.assertTrue(courseStreamPage.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");
            Assert.assertTrue(courseStreamPage.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");
            Assert.assertTrue(courseStreamPage.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
            Assert.assertEquals(courseStreamPage.langaugePalette_header.getText().trim(), "French Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL,"c"));
            String inputStringValue2 = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue2);
            Assert.assertEquals(inputStringValue2.length(), 24, "All the characters is not supported as existing Functionality.");
            Assert.assertEquals(inputStringValue2, "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ", "The selected characters is not displayed in the text box of the Palette. ");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame);
            String frenchText=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(frenchText,"ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ","All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.languages.get(0).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            courseStreamPage.langaugePalette_header.click();
            action.dragAndDrop(courseStreamPage.langaugePalette_header,From).build().perform();
            From.click();
            courseStreamPage.languages.get(0).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            manageContent.langaugePalette_textBox.sendKeys("abc123");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame);
            String frenchText1=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(frenchText1, "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœabc123", "All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();

            courseStreamPage.languages.get(0).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            courseStreamPage.textColour.get(0).click();//click on text colour
            boolean popo = new BooleanValue().presenceOfElement(22, By.id("sw-language-palette-wrapper"));
            Assert.assertEquals(popo,false,"French palette popup is not closed");
            courseStreamPage.languages.get(0).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
            manageContent.langaugePalette_closeIcon.click();//click on 'x' icon on language popoup
            boolean popo1 = new BooleanValue().presenceOfElement(22, By.id("sw-language-palette-wrapper"));
            Assert.assertEquals(popo1,false,"French palette popup is not closed");


            courseStreamPage.languages.get(0).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            int height=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0,2));
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
            }

            int height1=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0,2));
            if(height>height1){
                Assert.fail("The width of the question textbox is not  increased accordingly");
            }
            manageContent.langaugePalette_saveButton.click(); //click on the save button
            courseStreamPage.submitButton.click();//click on submit
            Assert.assertEquals(courseStreamPage.postText.isDisplayed(), true, "Added characters is not displaying in course entry. ");

            new LoginUsingLTI().ltiLogin("361_1"); //login as student
            new Navigator().NavigateTo("Course Stream");
            Assert.assertEquals(courseStreamPage.postText.isDisplayed(), true, "Added characters is not displaying in course entry. ");


            new LoginUsingLTI().ltiLogin("3611"); //login as instructor2
            new Navigator().NavigateTo("Course Stream");
            Assert.assertEquals(courseStreamPage.postText.isDisplayed(), true, "Added characters is not displaying in course entry. ");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToUseThePaletteInCourseStream of class CourseSpecificConfigurationsForLanguagePalette:", e);
        }

    }

    @Test(priority = 6, enabled = true)
    public void  instructorAbleToUseThePaletteInLinkInCourseStream() {
        try {
            // tc row no 415
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            JavascriptExecutor jse = (JavascriptExecutor) driver;

            new LoginUsingLTI().ltiLogin("415"); //login as instructor
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.link_link.click();//click on link
            courseStreamPage.textEditor.click();//click on text editor
            Thread.sleep(2000);
            List<WebElement>list=courseStreamPage.languages;
            for(WebElement esch:list){
                System.out.println(esch.isDisplayed());
            }
            Assert.assertEquals(courseStreamPage.languages.get(1).isDisplayed(), true, "language palette icon is not displying");

            courseStreamPage.languages.get(1).click();//click on language palette
            Assert.assertEquals(courseStreamPage.languageOptions.get(0).getText(), "Spanish");
            Assert.assertEquals(courseStreamPage.languageOptions.get(1).getText(), "Italian");
            Assert.assertEquals(courseStreamPage.languageOptions.get(2).getText(), "French");
            courseStreamPage.languageOptions.get(0).click();//click on spanish
            Assert.assertTrue(courseStreamPage.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");
            Assert.assertTrue(courseStreamPage.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");
            Assert.assertTrue(courseStreamPage.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
            Assert.assertEquals(courseStreamPage.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL,"c"));
            String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);
            Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");
            Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame_Link);
            String spanishText=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(spanishText,"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.languages.get(1).click();//click on language palette
            courseStreamPage.languageOptions.get(0).click();//click on spanish
            Actions action=new Actions(driver);
            WebElement From=driver.findElement(By.cssSelector("a[class='navigator ls-toc-sprite']"));
            courseStreamPage.langaugePalette_header.click();
            action.dragAndDrop(courseStreamPage.langaugePalette_header,From).build().perform();
            From.click();
            courseStreamPage.languages.get(1).click();//click on language palette
            courseStreamPage.languageOptions.get(0).click();//click on spanish
            manageContent.langaugePalette_textBox.sendKeys("abc123");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame_Link);
            String spanishText1=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(spanishText1, "áÁéÉíÍñÑóÓúÚüÜ¡¿abc123", "All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.cancelPost.click();//click on cancel


            courseStreamPage.link_link.click();//click on link
            courseStreamPage.textEditor.click();//click on text editor
            courseStreamPage.languages.get(1).click();//click on language palette
            Assert.assertEquals(courseStreamPage.languageOptions.get(0).getText(), "Spanish");
            Assert.assertEquals(courseStreamPage.languageOptions.get(1).getText(), "Italian");
            Assert.assertEquals(courseStreamPage.languageOptions.get(2).getText(), "French");
            courseStreamPage.languageOptions.get(1).click();//click on italian
            Assert.assertTrue(courseStreamPage.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");
            Assert.assertTrue(courseStreamPage.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");
            Assert.assertTrue(courseStreamPage.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
            Assert.assertEquals(courseStreamPage.langaugePalette_header.getText().trim(), "Italian Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL,"c"));
            String inputStringValue1 = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue1);
            Assert.assertEquals(inputStringValue1.length(), 22, "All the characters is not supported as existing Functionality.");
            Assert.assertEquals(inputStringValue1, "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "The selected characters is not displayed in the text box of the Palette. ");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame_Link);
            String italianText=driver.findElement(By.xpath("/html/body")).getText();
            String act=italianText.substring(0,22);
            Assert.assertEquals(act,"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤","All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.languages.get(1).click();//click on language palette
            courseStreamPage.languageOptions.get(1).click();//click on italian
            courseStreamPage.langaugePalette_header.click();
            action.dragAndDrop(courseStreamPage.langaugePalette_header,From).build().perform();
            From.click();
            courseStreamPage.languages.get(1).click();//click on language palette
            courseStreamPage.languageOptions.get(1).click();//click on italian
            manageContent.langaugePalette_textBox.sendKeys("abc123");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame_Link);
            String italianText1=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(italianText1,"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤abc123","All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.cancelPost.click();//click on cancel


            courseStreamPage.link_link.click();//click on link
            courseStreamPage.textEditor.click();//click on text editor
            courseStreamPage.languages.get(1).click();//click on language palette
            Assert.assertEquals(courseStreamPage.languageOptions.get(0).getText(), "Spanish");
            Assert.assertEquals(courseStreamPage.languageOptions.get(1).getText(), "Italian");
            Assert.assertEquals(courseStreamPage.languageOptions.get(2).getText(), "French");
            courseStreamPage.languageOptions.get(2).click();//click on french
            Assert.assertTrue(courseStreamPage.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");
            Assert.assertTrue(courseStreamPage.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");
            Assert.assertTrue(courseStreamPage.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
            Assert.assertEquals(courseStreamPage.langaugePalette_header.getText().trim(), "French Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL,"c"));
            String inputStringValue2 = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue2);
            Assert.assertEquals(inputStringValue2.length(), 24, "All the characters is not supported as existing Functionality.");
            Assert.assertEquals(inputStringValue2, "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ", "The selected characters is not displayed in the text box of the Palette. ");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame_Link);
            String frenchText=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(frenchText,"ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ","All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.languages.get(1).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            courseStreamPage.langaugePalette_header.click();
            action.dragAndDrop(courseStreamPage.langaugePalette_header,From).build().perform();
            From.click();
            courseStreamPage.languages.get(1).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            manageContent.langaugePalette_textBox.sendKeys("abc123");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame_Link);
            String frenchText1=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(frenchText1, "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœabc123", "All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();

            courseStreamPage.languages.get(1).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            courseStreamPage.textColour.get(1).click();//click on text colour
            boolean popo = new BooleanValue().presenceOfElement(22, By.id("sw-language-palette-wrapper"));
            Assert.assertEquals(popo,false,"French palette popup is not closed");
            Thread.sleep(2000);
            courseStreamPage.languages.get(1).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
            manageContent.langaugePalette_closeIcon.click();//click on 'x' icon on language popoup
            boolean popo1 = new BooleanValue().presenceOfElement(22, By.id("sw-language-palette-wrapper"));
            Assert.assertEquals(popo1,false,"French palette popup is not closed");


            courseStreamPage.languages.get(1).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            int height=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0,2));
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
            }

            int height1=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0,2));
            if(height>height1){
                Assert.fail("The width of the question textbox is not  increased accordingly");
            }
            manageContent.langaugePalette_saveButton.click(); //click on the save button
            courseStreamPage.submitButton.click();//click on submit
            Assert.assertEquals(courseStreamPage.postText.isDisplayed(), true, "Added characters is not displaying in course entry. ");

            new LoginUsingLTI().ltiLogin("415_1"); //login as student
            new Navigator().NavigateTo("Course Stream");
            Assert.assertEquals(courseStreamPage.postText.isDisplayed(), true, "Added characters is not displaying in course entry. ");


            new LoginUsingLTI().ltiLogin("4151"); //login as instructor2
            new Navigator().NavigateTo("Course Stream");
            Assert.assertEquals(courseStreamPage.postText.isDisplayed(), true, "Added characters is not displaying in course entry. ");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToUseThePaletteInLinkInCourseStream  of class CourseSpecificConfigurationsForLanguagePalette:", e);
        }

    }



    @Test(priority = 7, enabled = true)
    public void  instructorAbleToUseThePaletteInFileInCourseStream() {
        try {
            // tc row no 416
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            JavascriptExecutor jse = (JavascriptExecutor) driver;

            new LoginUsingLTI().ltiLogin("416"); //login as instructor
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("416",false);
            courseStreamPage.textEditor.click();//click on text editor
            Assert.assertEquals(courseStreamPage.languages.get(2).isDisplayed(), true, "language palette icon is not displying");

            courseStreamPage.languages.get(2).click();//click on language palette
            Assert.assertEquals(courseStreamPage.languageOptions.get(0).getText(), "Spanish");
            Assert.assertEquals(courseStreamPage.languageOptions.get(1).getText(), "Italian");
            Assert.assertEquals(courseStreamPage.languageOptions.get(2).getText(), "French");
            courseStreamPage.languageOptions.get(0).click();//click on spanish
            Assert.assertTrue(courseStreamPage.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");
            Assert.assertTrue(courseStreamPage.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");
            Assert.assertTrue(courseStreamPage.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
            Assert.assertEquals(courseStreamPage.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL,"c"));
            String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);
            Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");
            Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame_File);
            String spanishText=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(spanishText,"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.languages.get(2).click();//click on language palette
            courseStreamPage.languageOptions.get(0).click();//click on spanish
            Actions action=new Actions(driver);
            WebElement From=driver.findElement(By.cssSelector("a[class='navigator ls-toc-sprite']"));
            courseStreamPage.langaugePalette_header.click();
            action.dragAndDrop(courseStreamPage.langaugePalette_header,From).build().perform();
            From.click();
            courseStreamPage.languages.get(2).click();//click on language palette
            courseStreamPage.languageOptions.get(0).click();//click on spanish
            manageContent.langaugePalette_textBox.sendKeys("abc123");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame_File);
            String spanishText1=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(spanishText1, "áÁéÉíÍñÑóÓúÚüÜ¡¿abc123", "All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.cancelPost.click();//click on cancel


            new FileUpload().fileUpload("416", false);
            courseStreamPage.textEditor.click();//click on text editor
            courseStreamPage.languages.get(2).click();//click on language palette
            Assert.assertEquals(courseStreamPage.languageOptions.get(0).getText(), "Spanish");
            Assert.assertEquals(courseStreamPage.languageOptions.get(1).getText(), "Italian");
            Assert.assertEquals(courseStreamPage.languageOptions.get(2).getText(), "French");
            courseStreamPage.languageOptions.get(1).click();//click on italian
            Assert.assertTrue(courseStreamPage.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");
            Assert.assertTrue(courseStreamPage.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");
            Assert.assertTrue(courseStreamPage.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
            Assert.assertEquals(courseStreamPage.langaugePalette_header.getText().trim(), "Italian Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL,"c"));
            String inputStringValue1 = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue1);
            Assert.assertEquals(inputStringValue1.length(), 22, "All the characters is not supported as existing Functionality.");
            Assert.assertEquals(inputStringValue1, "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "The selected characters is not displayed in the text box of the Palette. ");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame_File);
            String italianText=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(italianText,"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤","All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.languages.get(2).click();//click on language palette
            courseStreamPage.languageOptions.get(1).click();//click on italian
            courseStreamPage.langaugePalette_header.click();
            action.dragAndDrop(courseStreamPage.langaugePalette_header,From).build().perform();
            From.click();
            courseStreamPage.languages.get(2).click();//click on language palette
            courseStreamPage.languageOptions.get(1).click();//click on italian
            manageContent.langaugePalette_textBox.sendKeys("abc123");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame_File);
            String italianText1=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(italianText1,"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤abc123","All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.cancelPost.click();//click on cancel


            new FileUpload().fileUpload("416", false);
            courseStreamPage.textEditor.click();//click on text editor
            courseStreamPage.languages.get(2).click();//click on language palette
            Assert.assertEquals(courseStreamPage.languageOptions.get(0).getText(), "Spanish");
            Assert.assertEquals(courseStreamPage.languageOptions.get(1).getText(), "Italian");
            Assert.assertEquals(courseStreamPage.languageOptions.get(2).getText(), "French");
            courseStreamPage.languageOptions.get(2).click();//click on french
            Assert.assertTrue(courseStreamPage.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");
            Assert.assertTrue(courseStreamPage.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");
            Assert.assertTrue(courseStreamPage.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
            Assert.assertEquals(courseStreamPage.langaugePalette_header.getText().trim(), "French Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL,"c"));
            String inputStringValue2 = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue2);
            Assert.assertEquals(inputStringValue2.length(), 24, "All the characters is not supported as existing Functionality.");
            Assert.assertEquals(inputStringValue2, "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ", "The selected characters is not displayed in the text box of the Palette. ");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame_File);
            String frenchText=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(frenchText,"ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ","All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.languages.get(2).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            courseStreamPage.langaugePalette_header.click();
            action.dragAndDrop(courseStreamPage.langaugePalette_header,From).build().perform();
            From.click();
            courseStreamPage.languages.get(2).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            manageContent.langaugePalette_textBox.sendKeys("abc123");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame_File);
            String frenchText1=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(frenchText1, "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœabc123", "All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();

            courseStreamPage.languages.get(2).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            courseStreamPage.textColour.get(2).click();//click on text colour
            boolean popo = new BooleanValue().presenceOfElement(22, By.id("sw-language-palette-wrapper"));
            Assert.assertEquals(popo,false,"French palette popup is not closed");
            courseStreamPage.languages.get(2).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
            manageContent.langaugePalette_closeIcon.click();//click on 'x' icon on language popoup
            boolean popo1 = new BooleanValue().presenceOfElement(22, By.id("sw-language-palette-wrapper"));
            Assert.assertEquals(popo1,false,"French palette popup is not closed");


            courseStreamPage.languages.get(2).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            int height=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0,2));
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
            }

            int height1=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0,2));
            if(height>height1){
                Assert.fail("The width of the question textbox is not  increased accordingly");
            }
            manageContent.langaugePalette_saveButton.click(); //click on the save button
            courseStreamPage.submitButton.click();//click on submit
            Assert.assertEquals(courseStreamPage.postText.isDisplayed(), true, "Added characters is not displaying in course entry. ");

            new LoginUsingLTI().ltiLogin("416_1"); //login as student
            new Navigator().NavigateTo("Course Stream");
            Assert.assertEquals(courseStreamPage.postText.isDisplayed(), true, "Added characters is not displaying in course entry. ");


            new LoginUsingLTI().ltiLogin("4161"); //login as instructor2
            new Navigator().NavigateTo("Course Stream");
            Assert.assertEquals(courseStreamPage.postText.isDisplayed(), true, "Added characters is not displaying in course entry. ");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToUseThePaletteInFileInCourseStream  of class CourseSpecificConfigurationsForLanguagePalette:", e);
        }

    }


    @Test(priority = 8, enabled = true)
    public void  mentorAbleToUseThePaletteInCourseStream() {
        try {
            // tc row no 361
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            JavascriptExecutor jse = (JavascriptExecutor) driver;

            new LoginUsingLTI().ltiLogin("417"); //login as instructor
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.getPost_button().click();//click on post
            courseStreamPage.textEditor.click();//click on text editor
            Assert.assertEquals(courseStreamPage.languages.get(0).isDisplayed(), true, "language palette icon is not displying");

            courseStreamPage.languages.get(0).click();//click on language palette
            Assert.assertEquals(courseStreamPage.languageOptions.get(0).getText(), "Spanish");
            Assert.assertEquals(courseStreamPage.languageOptions.get(1).getText(), "Italian");
            Assert.assertEquals(courseStreamPage.languageOptions.get(2).getText(), "French");
            courseStreamPage.languageOptions.get(0).click();//click on spanish
            Assert.assertTrue(courseStreamPage.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");
            Assert.assertTrue(courseStreamPage.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");
            Assert.assertTrue(courseStreamPage.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
            Assert.assertEquals(courseStreamPage.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL,"c"));
            String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);
            Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");
            Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame);
            String spanishText=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(spanishText,"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.languages.get(0).click();//click on language palette
            courseStreamPage.languageOptions.get(0).click();//click on spanish
            Actions action=new Actions(driver);
            WebElement From=driver.findElement(By.cssSelector("a[class='navigator ls-toc-sprite']"));
            courseStreamPage.langaugePalette_header.click();
            action.dragAndDrop(courseStreamPage.langaugePalette_header,From).build().perform();
            From.click();
            courseStreamPage.languages.get(0).click();//click on language palette
            courseStreamPage.languageOptions.get(0).click();//click on spanish
            manageContent.langaugePalette_textBox.sendKeys("abc123");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame);
            String spanishText1=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(spanishText1, "áÁéÉíÍñÑóÓúÚüÜ¡¿abc123", "All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.cancelPost.click();//click on cancel


            courseStreamPage.getPost_button().click();//click on post
            courseStreamPage.textEditor.click();//click on text editor
            courseStreamPage.languages.get(0).click();//click on language palette
            Assert.assertEquals(courseStreamPage.languageOptions.get(0).getText(), "Spanish");
            Assert.assertEquals(courseStreamPage.languageOptions.get(1).getText(), "Italian");
            Assert.assertEquals(courseStreamPage.languageOptions.get(2).getText(), "French");
            courseStreamPage.languageOptions.get(1).click();//click on italian
            Assert.assertTrue(courseStreamPage.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");
            Assert.assertTrue(courseStreamPage.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");
            Assert.assertTrue(courseStreamPage.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
            Assert.assertEquals(courseStreamPage.langaugePalette_header.getText().trim(), "Italian Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL,"c"));
            String inputStringValue1 = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue1);
            Assert.assertEquals(inputStringValue1.length(), 22, "All the characters is not supported as existing Functionality.");
            Assert.assertEquals(inputStringValue1, "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "The selected characters is not displayed in the text box of the Palette. ");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame);
            String italianText=driver.findElement(By.xpath("/html/body")).getText();
            String act=italianText.substring(0,22);
            Assert.assertEquals(act,"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤","All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.languages.get(0).click();//click on language palette
            courseStreamPage.languageOptions.get(1).click();//click on italian
            courseStreamPage.langaugePalette_header.click();
            action.dragAndDrop(courseStreamPage.langaugePalette_header,From).build().perform();
            From.click();
            courseStreamPage.languages.get(0).click();//click on language palette
            courseStreamPage.languageOptions.get(1).click();//click on italian
            manageContent.langaugePalette_textBox.sendKeys("abc123");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame);
            String italianText1=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(italianText1,"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤abc123","All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.cancelPost.click();//click on cancel


            courseStreamPage.getPost_button().click();//click on post
            courseStreamPage.textEditor.click();//click on text editor
            courseStreamPage.languages.get(0).click();//click on language palette
            Assert.assertEquals(courseStreamPage.languageOptions.get(0).getText(), "Spanish");
            Assert.assertEquals(courseStreamPage.languageOptions.get(1).getText(), "Italian");
            Assert.assertEquals(courseStreamPage.languageOptions.get(2).getText(), "French");
            courseStreamPage.languageOptions.get(2).click();//click on french
            Assert.assertTrue(courseStreamPage.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");
            Assert.assertTrue(courseStreamPage.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");
            Assert.assertTrue(courseStreamPage.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
            Assert.assertEquals(courseStreamPage.langaugePalette_header.getText().trim(), "French Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL,"c"));
            String inputStringValue2 = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue2);
            Assert.assertEquals(inputStringValue2.length(), 24, "All the characters is not supported as existing Functionality.");
            Assert.assertEquals(inputStringValue2, "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ", "The selected characters is not displayed in the text box of the Palette. ");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame);
            String frenchText=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(frenchText,"ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ","All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.languages.get(0).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            courseStreamPage.langaugePalette_header.click();
            action.dragAndDrop(courseStreamPage.langaugePalette_header,From).build().perform();
            From.click();
            courseStreamPage.languages.get(0).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            manageContent.langaugePalette_textBox.sendKeys("abc123");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame);
            String frenchText1=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(frenchText1, "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœabc123", "All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();

            courseStreamPage.languages.get(0).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            courseStreamPage.textColour.get(0).click();//click on text colour
            boolean popo = new BooleanValue().presenceOfElement(22, By.id("sw-language-palette-wrapper"));
            Assert.assertEquals(popo,false,"French palette popup is not closed");
            courseStreamPage.languages.get(0).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
            manageContent.langaugePalette_closeIcon.click();//click on 'x' icon on language popoup
            boolean popo1 = new BooleanValue().presenceOfElement(22, By.id("sw-language-palette-wrapper"));
            Assert.assertEquals(popo1,false,"French palette popup is not closed");


            courseStreamPage.languages.get(0).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            int height=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0,2));
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
            }

            int height1=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0,2));
            if(height>height1){
                Assert.fail("The width of the question textbox is not  increased accordingly");
            }
            manageContent.langaugePalette_saveButton.click(); //click on the save button
            courseStreamPage.submitButton.click();//click on submit
            Assert.assertEquals(courseStreamPage.postText.isDisplayed(), true, "Added characters is not displaying in course entry. ");

            new LoginUsingLTI().ltiLogin("417_1"); //login as student
            new Navigator().NavigateTo("Course Stream");
            Assert.assertEquals(courseStreamPage.postText.isDisplayed(), true, "Added characters is not displaying in course entry. ");


            new LoginUsingLTI().ltiLogin("4171"); //login as instructor2
            new Navigator().NavigateTo("Course Stream");
            Assert.assertEquals(courseStreamPage.postText.isDisplayed(), true, "Added characters is not displaying in course entry. ");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToUseThePaletteInCourseStream of class CourseSpecificConfigurationsForLanguagePalette:", e);
        }

    }

    @Test(priority = 9, enabled = true)
    public void  mentorAbleToUseThePaletteInLinkInCourseStream() {
        try {
            // tc row no 415
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            JavascriptExecutor jse = (JavascriptExecutor) driver;

            new LoginUsingLTI().ltiLogin("4172"); //login as instructor
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.link_link.click();//click on link
            courseStreamPage.textEditor.click();//click on text editor
            Thread.sleep(2000);
            List<WebElement>list=courseStreamPage.languages;
            for(WebElement esch:list){
                System.out.println(esch.isDisplayed());
            }
            Assert.assertEquals(courseStreamPage.languages.get(1).isDisplayed(), true, "language palette icon is not displying");

            courseStreamPage.languages.get(1).click();//click on language palette
            Assert.assertEquals(courseStreamPage.languageOptions.get(0).getText(), "Spanish");
            Assert.assertEquals(courseStreamPage.languageOptions.get(1).getText(), "Italian");
            Assert.assertEquals(courseStreamPage.languageOptions.get(2).getText(), "French");
            courseStreamPage.languageOptions.get(0).click();//click on spanish
            Assert.assertTrue(courseStreamPage.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");
            Assert.assertTrue(courseStreamPage.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");
            Assert.assertTrue(courseStreamPage.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
            Assert.assertEquals(courseStreamPage.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL,"c"));
            String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);
            Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");
            Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame_Link);
            String spanishText=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(spanishText,"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.languages.get(1).click();//click on language palette
            courseStreamPage.languageOptions.get(0).click();//click on spanish
            Actions action=new Actions(driver);
            WebElement From=driver.findElement(By.cssSelector("a[class='navigator ls-toc-sprite']"));
            courseStreamPage.langaugePalette_header.click();
            action.dragAndDrop(courseStreamPage.langaugePalette_header,From).build().perform();
            From.click();
            courseStreamPage.languages.get(1).click();//click on language palette
            courseStreamPage.languageOptions.get(0).click();//click on spanish
            manageContent.langaugePalette_textBox.sendKeys("abc123");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame_Link);
            String spanishText1=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(spanishText1, "áÁéÉíÍñÑóÓúÚüÜ¡¿abc123", "All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.cancelPost.click();//click on cancel


            courseStreamPage.link_link.click();//click on link
            courseStreamPage.textEditor.click();//click on text editor
            courseStreamPage.languages.get(1).click();//click on language palette
            Assert.assertEquals(courseStreamPage.languageOptions.get(0).getText(), "Spanish");
            Assert.assertEquals(courseStreamPage.languageOptions.get(1).getText(), "Italian");
            Assert.assertEquals(courseStreamPage.languageOptions.get(2).getText(), "French");
            courseStreamPage.languageOptions.get(1).click();//click on italian
            Assert.assertTrue(courseStreamPage.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");
            Assert.assertTrue(courseStreamPage.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");
            Assert.assertTrue(courseStreamPage.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
            Assert.assertEquals(courseStreamPage.langaugePalette_header.getText().trim(), "Italian Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL,"c"));
            String inputStringValue1 = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue1);
            Assert.assertEquals(inputStringValue1.length(), 22, "All the characters is not supported as existing Functionality.");
            Assert.assertEquals(inputStringValue1, "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "The selected characters is not displayed in the text box of the Palette. ");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame_Link);
            String italianText=driver.findElement(By.xpath("/html/body")).getText();
            String act=italianText.substring(0,22);
            Assert.assertEquals(act,"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤","All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.languages.get(1).click();//click on language palette
            courseStreamPage.languageOptions.get(1).click();//click on italian
            courseStreamPage.langaugePalette_header.click();
            action.dragAndDrop(courseStreamPage.langaugePalette_header,From).build().perform();
            From.click();
            courseStreamPage.languages.get(1).click();//click on language palette
            courseStreamPage.languageOptions.get(1).click();//click on italian
            manageContent.langaugePalette_textBox.sendKeys("abc123");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame_Link);
            String italianText1=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(italianText1,"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤abc123","All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.cancelPost.click();//click on cancel


            courseStreamPage.link_link.click();//click on link
            courseStreamPage.textEditor.click();//click on text editor
            courseStreamPage.languages.get(1).click();//click on language palette
            Assert.assertEquals(courseStreamPage.languageOptions.get(0).getText(), "Spanish");
            Assert.assertEquals(courseStreamPage.languageOptions.get(1).getText(), "Italian");
            Assert.assertEquals(courseStreamPage.languageOptions.get(2).getText(), "French");
            courseStreamPage.languageOptions.get(2).click();//click on french
            Assert.assertTrue(courseStreamPage.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");
            Assert.assertTrue(courseStreamPage.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");
            Assert.assertTrue(courseStreamPage.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
            Assert.assertEquals(courseStreamPage.langaugePalette_header.getText().trim(), "French Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL,"c"));
            String inputStringValue2 = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue2);
            Assert.assertEquals(inputStringValue2.length(), 24, "All the characters is not supported as existing Functionality.");
            Assert.assertEquals(inputStringValue2, "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ", "The selected characters is not displayed in the text box of the Palette. ");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame_Link);
            String frenchText=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(frenchText,"ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ","All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.languages.get(1).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            courseStreamPage.langaugePalette_header.click();
            action.dragAndDrop(courseStreamPage.langaugePalette_header,From).build().perform();
            From.click();
            courseStreamPage.languages.get(1).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            manageContent.langaugePalette_textBox.sendKeys("abc123");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame_Link);
            String frenchText1=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(frenchText1, "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœabc123", "All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();

            courseStreamPage.languages.get(1).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            courseStreamPage.textColour.get(1).click();//click on text colour
            boolean popo = new BooleanValue().presenceOfElement(22, By.id("sw-language-palette-wrapper"));
            Assert.assertEquals(popo,false,"French palette popup is not closed");
            courseStreamPage.languages.get(1).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
            manageContent.langaugePalette_closeIcon.click();//click on 'x' icon on language popoup
            boolean popo1 = new BooleanValue().presenceOfElement(22, By.id("sw-language-palette-wrapper"));
            Assert.assertEquals(popo1,false,"French palette popup is not closed");


            courseStreamPage.languages.get(1).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            int height=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0,2));
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
            }

            int height1=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0,2));
            if(height>height1){
                Assert.fail("The width of the question textbox is not  increased accordingly");
            }
            manageContent.langaugePalette_saveButton.click(); //click on the save button
            courseStreamPage.submitButton.click();//click on submit
            Assert.assertEquals(courseStreamPage.postText.isDisplayed(), true, "Added characters is not displaying in course entry. ");

            new LoginUsingLTI().ltiLogin("4172_1"); //login as student
            new Navigator().NavigateTo("Course Stream");
            Assert.assertEquals(courseStreamPage.postText.isDisplayed(), true, "Added characters is not displaying in course entry. ");


            new LoginUsingLTI().ltiLogin("41721"); //login as instructor2
            new Navigator().NavigateTo("Course Stream");
            Assert.assertEquals(courseStreamPage.postText.isDisplayed(), true, "Added characters is not displaying in course entry. ");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToUseThePaletteInLinkInCourseStream  of class CourseSpecificConfigurationsForLanguagePalette:", e);
        }

    }



    @Test(priority = 10, enabled = true)
    public void  mentorAbleToUseThePaletteInFileInCourseStream() {
        try {
            // tc row no 416
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            JavascriptExecutor jse = (JavascriptExecutor) driver;

            new LoginUsingLTI().ltiLogin("4173"); //login as instructor
            new Navigator().NavigateTo("Course Stream");
            new FileUpload().fileUpload("416",false);
            courseStreamPage.textEditor.click();//click on text editor
            Assert.assertEquals(courseStreamPage.languages.get(2).isDisplayed(), true, "language palette icon is not displying");

            courseStreamPage.languages.get(2).click();//click on language palette
            Assert.assertEquals(courseStreamPage.languageOptions.get(0).getText(), "Spanish");
            Assert.assertEquals(courseStreamPage.languageOptions.get(1).getText(), "Italian");
            Assert.assertEquals(courseStreamPage.languageOptions.get(2).getText(), "French");
            courseStreamPage.languageOptions.get(0).click();//click on spanish
            Assert.assertTrue(courseStreamPage.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");
            Assert.assertTrue(courseStreamPage.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");
            Assert.assertTrue(courseStreamPage.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
            Assert.assertEquals(courseStreamPage.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL,"c"));
            String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);
            Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");
            Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame_File);
            String spanishText=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(spanishText,"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.languages.get(2).click();//click on language palette
            courseStreamPage.languageOptions.get(0).click();//click on spanish
            Actions action=new Actions(driver);
            WebElement From=driver.findElement(By.cssSelector("a[class='navigator ls-toc-sprite']"));
            courseStreamPage.langaugePalette_header.click();
            action.dragAndDrop(courseStreamPage.langaugePalette_header,From).build().perform();
            From.click();
            courseStreamPage.languages.get(2).click();//click on language palette
            courseStreamPage.languageOptions.get(0).click();//click on spanish
            manageContent.langaugePalette_textBox.sendKeys("abc123");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame_File);
            String spanishText1=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(spanishText1, "áÁéÉíÍñÑóÓúÚüÜ¡¿abc123", "All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.cancelPost.click();//click on cancel


            new FileUpload().fileUpload("416", false);
            courseStreamPage.textEditor.click();//click on text editor
            courseStreamPage.languages.get(2).click();//click on language palette
            Assert.assertEquals(courseStreamPage.languageOptions.get(0).getText(), "Spanish");
            Assert.assertEquals(courseStreamPage.languageOptions.get(1).getText(), "Italian");
            Assert.assertEquals(courseStreamPage.languageOptions.get(2).getText(), "French");
            courseStreamPage.languageOptions.get(1).click();//click on italian
            Assert.assertTrue(courseStreamPage.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");
            Assert.assertTrue(courseStreamPage.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");
            Assert.assertTrue(courseStreamPage.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
            Assert.assertEquals(courseStreamPage.langaugePalette_header.getText().trim(), "Italian Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL,"c"));
            String inputStringValue1 = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue1);
            Assert.assertEquals(inputStringValue1.length(), 22, "All the characters is not supported as existing Functionality.");
            Assert.assertEquals(inputStringValue1, "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "The selected characters is not displayed in the text box of the Palette. ");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame_File);
            String italianText=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(italianText,"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤","All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.languages.get(2).click();//click on language palette
            courseStreamPage.languageOptions.get(1).click();//click on italian
            courseStreamPage.langaugePalette_header.click();
            action.dragAndDrop(courseStreamPage.langaugePalette_header,From).build().perform();
            From.click();
            courseStreamPage.languages.get(2).click();//click on language palette
            courseStreamPage.languageOptions.get(1).click();//click on italian
            manageContent.langaugePalette_textBox.sendKeys("abc123");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame_File);
            String italianText1=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(italianText1,"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤abc123","All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.cancelPost.click();//click on cancel


            new FileUpload().fileUpload("4173", false);
            courseStreamPage.textEditor.click();//click on text editor
            courseStreamPage.languages.get(2).click();//click on language palette
            Assert.assertEquals(courseStreamPage.languageOptions.get(0).getText(), "Spanish");
            Assert.assertEquals(courseStreamPage.languageOptions.get(1).getText(), "Italian");
            Assert.assertEquals(courseStreamPage.languageOptions.get(2).getText(), "French");
            courseStreamPage.languageOptions.get(2).click();//click on french
            Assert.assertTrue(courseStreamPage.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");
            Assert.assertTrue(courseStreamPage.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");
            Assert.assertTrue(courseStreamPage.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
            Assert.assertEquals(courseStreamPage.langaugePalette_header.getText().trim(), "French Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL,"c"));
            String inputStringValue2 = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue2);
            Assert.assertEquals(inputStringValue2.length(), 24, "All the characters is not supported as existing Functionality.");
            Assert.assertEquals(inputStringValue2, "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ", "The selected characters is not displayed in the text box of the Palette. ");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame_File);
            String frenchText=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(frenchText,"ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ","All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();
            courseStreamPage.languages.get(2).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            courseStreamPage.langaugePalette_header.click();
            action.dragAndDrop(courseStreamPage.langaugePalette_header,From).build().perform();
            From.click();
            courseStreamPage.languages.get(2).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            manageContent.langaugePalette_textBox.sendKeys("abc123");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            driver.switchTo().frame(courseStreamPage.frame_File);
            String frenchText1=driver.findElement(By.xpath("/html/body")).getText();
            Assert.assertEquals(frenchText1, "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœabc123", "All the saved characters is not added to the POST text box.");
            driver.switchTo().defaultContent();

            courseStreamPage.languages.get(2).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            courseStreamPage.textColour.get(2).click();//click on text colour
            boolean popo = new BooleanValue().presenceOfElement(22, By.id("sw-language-palette-wrapper"));
            Assert.assertEquals(popo,false,"French palette popup is not closed");
            courseStreamPage.languages.get(2).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(), "The pop up in not having close icon");
            manageContent.langaugePalette_closeIcon.click();//click on 'x' icon on language popoup
            boolean popo1 = new BooleanValue().presenceOfElement(22, By.id("sw-language-palette-wrapper"));
            Assert.assertEquals(popo1,false,"French palette popup is not closed");


            courseStreamPage.languages.get(2).click();//click on language palette
            courseStreamPage.languageOptions.get(2).click();//click on french
            int height=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0,2));
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
            }

            int height1=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0,2));
            if(height>height1){
                Assert.fail("The width of the question textbox is not  increased accordingly");
            }
            manageContent.langaugePalette_saveButton.click(); //click on the save button
            courseStreamPage.submitButton.click();//click on submit
            Assert.assertEquals(courseStreamPage.postText.isDisplayed(), true, "Added characters is not displaying in course entry. ");

            new LoginUsingLTI().ltiLogin("4173_1"); //login as student
            new Navigator().NavigateTo("Course Stream");
            Assert.assertEquals(courseStreamPage.postText.isDisplayed(), true, "Added characters is not displaying in course entry. ");


            new LoginUsingLTI().ltiLogin("41731"); //login as instructor2
            new Navigator().NavigateTo("Course Stream");
            Assert.assertEquals(courseStreamPage.postText.isDisplayed(), true, "Added characters is not displaying in course entry. ");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToUseThePaletteInFileInCourseStream  of class CourseSpecificConfigurationsForLanguagePalette:", e);
        }

    }

}