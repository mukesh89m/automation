package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT23.R237;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Questions;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Gradebook.Gradebook;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.AssignmentTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.PerformanceTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.EngagementReport;
import com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R199.ImpactsOnAssignmentDiscussionOnTheStudentSide;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
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
 * Created by priyanka on 9/23/2015.
 */
public class InstructorAbleToUseNewLanguagesAcrossApplication extends Driver {

    @Test(priority = 1, enabled = true)
    public void instructorAbleToUseNewLanguagesAcrossAssignments() {
        try {
            // tc row no 420
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(420));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(420));
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "420");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "420");
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);


            new Assignment().create(420);
            new LoginUsingLTI().ltiLogin("420"); //login as instructor
            new Navigator().NavigateTo("My Question Bank"); //navigate to question bank
            questionBank.getQuestionBankTitle().click();//click on question bank tab
            Thread.sleep(2000);
            questionBank.getSearchInputFieldOnQuestionBank().get(1).sendKeys("\"" + assignmentname + "\"");
            questionBank.getSearchButtonOnQuestionBank().get(1).click();
            Thread.sleep(3000);
            questionBank.getPreviewButton().click();//click on preview button
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("cms-content-question-review-list-label")));
            String questionContent = questionBank.questionContent.getText().trim();
            Assert.assertTrue(questionContent.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿True False " + questiontext), "data added over Question Editor is not displaying");
            questionBank.getQuestionBankTitle().click();//click on question bank tab
            Thread.sleep(2000);
            questionBank.getSearchInputFieldOnQuestionBank().get(1).sendKeys("\"" + assignmentname + "\"");
            questionBank.getSearchButtonOnQuestionBank().get(1).click();
            Thread.sleep(2000);
            myQuestionBank.customizeThis.click();//click on customize this
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("420");//select three question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            List<WebElement> preview = driver.findElements(By.cssSelector("span[class='ls-preview-wrapper action-links']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", preview.get(0));
            Thread.sleep(2000);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("cms-content-question-review-list-label")));
            Assert.assertTrue(questionContent.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿True False " + questiontext), "data added over Question Editor is not displaying");
            questionBank.getQuestionBankTitle().click();//click on question bank tab
            Thread.sleep(2000);
            questionBank.getSearchInputFieldOnQuestionBank().get(1).sendKeys("\"" + assignmentname + "\"");
            questionBank.getSearchButtonOnQuestionBank().get(1).click();
            Thread.sleep(2000);
            questionBank.getAddToMyQuestionBank().click();//click on add to my question bank
            Thread.sleep(1000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank tab
            Thread.sleep(2000);
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ls-preview-wrapper action-links']"));
            List<WebElement> preview1 = driver.findElements(By.cssSelector("span[class='ls-preview-wrapper action-links']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", preview1.get(0));
            Thread.sleep(2000);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("cms-content-question-review-list-label")));
            Assert.assertTrue(questionContent.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿True False " + questiontext), "data added over Question Editor is not displaying");
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank tab
            Thread.sleep(2000);
            myQuestionBank.getRemoveFromMyQuestionBank().get(0).click();//click on remove from my question bank
            myQuestionBank.getDeleteButtonOfOriginal().click();//click on delete
            myQuestionBank.getYesOnNotificationMessage().click();//click on yes
            new Assignment().assignToStudent(420);

            new LoginUsingLTI().ltiLogin("420_1"); //login as student
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssessmentName().click();//click on assignment
            new UIElement().waitAndFindElement(By.className("resource-title"));
            String questionContent1 = questionBank.questionContent.getText().trim();
            Assert.assertTrue(questionContent1.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿True False " + questiontext), "data added over Question Editor is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToUseNewLanguagesAcrossAssignments  of class InstructorAbleToUseNewLanguagesAcrossApplication:", e);
        }

    }


    @Test(priority = 2, enabled = true)
    public void instructorAbleToUseNewLanguagesMyQuestionBank() {
        try {
            // tc row no 426
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(420));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(420));
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "420");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "420");
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(4261));
            new LoginUsingLTI().ltiLogin("4261"); //login as instructor 2
            new LoginUsingLTI().ltiLogin("426"); //login as instructor
            new Navigator().NavigateTo("My Question Bank"); //navigate to question bank
            questionBank.getQuestionBankTitle().click();//click on question bank tab
            Thread.sleep(2000);
            questionBank.getSearchInputFieldOnQuestionBank().get(1).sendKeys("\"" + assignmentname + "\"");
            questionBank.getSearchButtonOnQuestionBank().get(1).click();
            Thread.sleep(3000);
            myQuestionBank.customizeThis.click();//click on customize this
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("420");//select three question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ls-preview-wrapper action-links']"));
            myQuestionBank.closeTab.click();//click on 'x'
            Thread.sleep(3000);
            myQuestionBank.editThis.click();//click on edit this
            newAssignment.tabTittle.get(3).click();//click on find question tab
            newAssignment.searchButton.click();//click on search button
            newAssignment.searchTextArea.sendKeys("true");
            newAssignment.searchButton.click();//click on search button
            new AssignLesson().selectQuestionForCustomAssignment("420");//select three question
            new AssignLesson().selectQuestionForCustomAssignment("420");//select three question
            Thread.sleep(1000);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ls-preview-wrapper action-links']"));
            List<WebElement> preview1 = driver.findElements(By.cssSelector("span[class='ls-preview-wrapper action-links']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", preview1.get(0));
            Thread.sleep(2000);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("cms-content-question-review-list-label")));
            String questionContent = questionBank.questionContent.getText().trim();
            Assert.assertTrue(questionContent.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿True False " + questiontext), "data added over Question Editor is not displaying");
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank tab
            Thread.sleep(2000);
            myQuestionBank.tryItIcon.get(0).click();//click on tryit
            String parentWindow1 = driver.getWindowHandle();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertTrue(questionContent.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿True False " + questiontext), "data added over Question Editor is not displaying");
            driver.close();
            driver.switchTo().window(parentWindow1);
            new UIElement().waitAndFindElement(By.xpath("//span[@title='Share This']"));
            new Assignment().shareCustomAssignment(shareName);
            myQuestionBank.yesOnShareMessage.click();//click on yes
            Thread.sleep(1000);

            new LoginUsingLTI().ltiLogin("4261"); //login as instructor 2
            new Navigator().NavigateTo("My Question Bank");//click on myquestion bank
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ls-preview-wrapper action-links']"));
            List<WebElement> preview2 = driver.findElements(By.cssSelector("span[class='ls-preview-wrapper action-links']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", preview2.get(0));
            Thread.sleep(2000);
            String questionContent1 = questionBank.questionContent.getText().trim();
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("cms-content-question-review-list-label")));
            Assert.assertTrue(questionContent1.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿True False " + questiontext), "data added over Question Editor is not displaying");
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank tab
            Thread.sleep(2000);
            new AssignLesson().Assigncustomeassignemnt(426);//assign assignment

            new LoginUsingLTI().ltiLogin("426_1"); //login as student
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssessmentName().click();//click on assignment
            new UIElement().waitAndFindElement(By.className("resource-title"));
            String questionContent2 = questionBank.questionContent.getText().trim();
            Assert.assertTrue(questionContent2.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿True False " + questiontext), "data added over Question Editor is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorAbleToUseNewLanguagesMyQuestionBank  of class InstructorAbleToUseNewLanguagesAcrossApplication:", e);
        }

    }

    @Test(priority = 3, enabled = true)
    public void mentorAbleToUseNewLanguagesAcrossAssignments() {
        try {
            // tc row no 420
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(420));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(420));
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "420");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "420");
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);


            new LoginUsingLTI().ltiLogin("439"); //login as instructor
            new Navigator().NavigateTo("My Question Bank"); //navigate to question bank
            questionBank.getQuestionBankTitle().click();//click on question bank tab
            Thread.sleep(2000);
            questionBank.getSearchInputFieldOnQuestionBank().get(1).sendKeys("\"" + assignmentname + "\"");
            questionBank.getSearchButtonOnQuestionBank().get(1).click();
            Thread.sleep(3000);
            questionBank.getPreviewButton().click();//click on preview button
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("cms-content-question-review-list-label")));
            String questionContent = questionBank.questionContent.getText().trim();
            Assert.assertTrue(questionContent.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿True False " + questiontext), "data added over Question Editor is not displaying");
            questionBank.getQuestionBankTitle().click();//click on question bank tab
            Thread.sleep(2000);
            questionBank.getSearchInputFieldOnQuestionBank().get(1).sendKeys("\"" + assignmentname + "\"");
            questionBank.getSearchButtonOnQuestionBank().get(1).click();
            Thread.sleep(2000);
            myQuestionBank.customizeThis.click();//click on customize this
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("420");//select three question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            List<WebElement> preview = driver.findElements(By.cssSelector("span[class='ls-preview-wrapper action-links']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", preview.get(0));
            Thread.sleep(2000);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("cms-content-question-review-list-label")));
            Assert.assertTrue(questionContent.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿True False " + questiontext), "data added over Question Editor is not displaying");
            questionBank.getQuestionBankTitle().click();//click on question bank tab
            Thread.sleep(2000);
            questionBank.getSearchInputFieldOnQuestionBank().get(1).sendKeys("\"" + assignmentname + "\"");
            questionBank.getSearchButtonOnQuestionBank().get(1).click();
            Thread.sleep(2000);
            questionBank.getAddToMyQuestionBank().click();//click on add to my question bank
            Thread.sleep(1000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank tab
            Thread.sleep(2000);
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ls-preview-wrapper action-links']"));
            List<WebElement> preview1 = driver.findElements(By.cssSelector("span[class='ls-preview-wrapper action-links']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", preview1.get(0));
            Thread.sleep(2000);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("cms-content-question-review-list-label")));
            Assert.assertTrue(questionContent.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿True False " + questiontext), "data added over Question Editor is not displaying");
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank tab
            Thread.sleep(2000);
            myQuestionBank.getRemoveFromMyQuestionBank().get(0).click();//click on remove from my question bank
            myQuestionBank.getDeleteButtonOfOriginal().click();//click on delete
            myQuestionBank.getYesOnNotificationMessage().click();//click on yes
            new Assignment().assignToStudent(439);

            new LoginUsingLTI().ltiLogin("439_1"); //login as student
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssessmentName().click();//click on assignment
            new UIElement().waitAndFindElement(By.className("resource-title"));
            String questionContent1 = questionBank.questionContent.getText().trim();
            Assert.assertTrue(questionContent1.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿True False " + questiontext), "data added over Question Editor is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case mentorAbleToUseNewLanguagesAcrossAssignments  of class InstructorAbleToUseNewLanguagesAcrossApplication:", e);
        }

    }


    @Test(priority = 4, enabled = true)
    public void mentorAbleToUseNewLanguagesMyQuestionBank() {
        try {
            // tc row no 426
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            MyQuestionBank myQuestionBank = PageFactory.initElements(driver, MyQuestionBank.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(420));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(420));
            String searchquestion = ReadTestData.readDataByTagName("", "searchquestion", "420");
            String customassignmentname = ReadTestData.readDataByTagName("", "customassignmentname", "420");
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            String shareName = ReadTestData.readDataByTagName("", "shareName", Integer.toString(4261));

           // new LoginUsingLTI().ltiLogin("4401"); //login as instructor 2
            new LoginUsingLTI().ltiLogin("440"); //login as instructor
            new Navigator().NavigateTo("My Question Bank"); //navigate to question bank
            questionBank.getQuestionBankTitle().click();//click on question bank tab
            Thread.sleep(2000);
            questionBank.getSearchInputFieldOnQuestionBank().get(1).sendKeys("\"" + assignmentname + "\"");
            questionBank.getSearchButtonOnQuestionBank().get(1).click();
            Thread.sleep(3000);
            myQuestionBank.customizeThis.click();//click on customize this
            new SearchQuestionInCustomCourseAssignemnt().searchQuestionInCustomCourseAssignemnt(searchquestion);
            new AssignLesson().selectQuestionForCustomAssignment("420");//select three question
            myQuestionBank.getAssignmentNameField().click();//click on name field
            myQuestionBank.getAssignmentNameFieldAfterClick().sendKeys(customassignmentname);
            myQuestionBank.getSaveForLater().click();//click on save for later
           /* Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ls-preview-wrapper action-links']"));
            myQuestionBank.closeTab.click();//click on 'x'
            Thread.sleep(3000);
            myQuestionBank.editThis.click();//click on edit this
            newAssignment.tabTittle.get(3).click();//click on find question tab
            newAssignment.searchButton.click();//click on search button
            newAssignment.searchTextArea.sendKeys("true");
            newAssignment.searchButton.click();//click on search button
            new AssignLesson().selectQuestionForCustomAssignment("420");//select three question
            new AssignLesson().selectQuestionForCustomAssignment("420");//select three question
            Thread.sleep(1000);
            myQuestionBank.getSaveForLater().click();//click on save for later
            Thread.sleep(5000);
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ls-preview-wrapper action-links']"));
            List<WebElement> preview1 = driver.findElements(By.cssSelector("span[class='ls-preview-wrapper action-links']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", preview1.get(0));
            Thread.sleep(2000);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("cms-content-question-review-list-label")));
            String questionContent = questionBank.questionContent.getText().trim();
            System.out.println("questionContent :"+questionContent);
            Assert.assertTrue(questionContent.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿True False " + questiontext), "data added over Question Editor is not displaying");
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank tab
            Thread.sleep(2000);
            myQuestionBank.tryItIcon.get(0).click();//click on tryit
            String parentWindow1 = driver.getWindowHandle();
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
            }
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("cms-question-try-it-header-logo")));
            Assert.assertTrue(questionContent.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿True False " + questiontext), "data added over Question Editor is not displaying");
            driver.close();
            driver.switchTo().window(parentWindow1);
            new UIElement().waitAndFindElement(By.xpath("//span[@title='Share This']"));
            new Assignment().shareCustomAssignment(shareName);
            myQuestionBank.yesOnShareMessage.click();//click on yes
            Thread.sleep(1000);

            new LoginUsingLTI().ltiLogin("4401"); //login as instructor 2
            new Navigator().NavigateTo("My Question Bank");//click on myquestion bank
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ls-preview-wrapper action-links']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", preview1.get(0));
            Thread.sleep(2000);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("cms-content-question-review-list-label")));
            Assert.assertTrue(questionContent.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿True False " + questiontext), "data added over Question Editor is not displaying");
            myQuestionBank.getMyQuestionBankTitle().click();//click on my question bank tab
            Thread.sleep(2000);
            new AssignLesson().Assigncustomeassignemnt(440);//assign assignment

            new LoginUsingLTI().ltiLogin("440_1"); //login as student
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssessmentName().click();//click on assignment
            new UIElement().waitAndFindElement(By.className("resource-title"));
            String questionContent1 = questionBank.questionContent.getText().trim();
            Assert.assertTrue(questionContent1.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿True False " + questiontext), "data added over Question Editor is not displaying");
*/
        } catch (Exception e) {
            Assert.fail("Exception in test case mentorAbleToUseNewLanguagesMyQuestionBank  of class InstructorAbleToUseNewLanguagesAcrossApplication:", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void instructorAbleToUseNewLanguagesAsPartOfDiscussionThread() {
        try {
            // tc row no 441
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

            new Assignment().create(441);
            new LoginUsingLTI().ltiLogin("441"); //login as instructor
            new Assignment().assignToStudent(441);
            new LoginUsingLTI().ltiLogin("441_1"); //login as student
            new Assignment().submitAssignmentAsStudent(441);
            new LoginUsingLTI().ltiLogin("441"); //login as instructor
            new Navigator().NavigateTo("Current Assignments");
            new AddCommentInCSPage().verificationForFrenchInCSPage();
            new Navigator().NavigateTo("Current Assignments");
            new AddCommentInCSPage().verificationForItalianInCSPage();
            new Navigator().NavigateTo("Current Assignments");
            new AddCommentInCSPage().verificationForSpanishInCSPage();
            courseStreamPage.post_Link.get(0).click();

            new LoginUsingLTI().ltiLogin("441_1");//login as student
            new Navigator().NavigateTo("Assignments"); //navigate to course stream
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            Assert.assertTrue(courseStreamPage.postComment_content.get(0).getText().trim().contains("áÁéÉíÍñÑóÓúÚüÜ¡¿áááááááááÁÁÁÁÁÁÁÁÁéééééééééÉÉÉÉÉÉÉÉÉíííííííííÍÍÍÍÍÍÍÍÍñññññññññÑÑÑÑÑÑÑÑÑóóóóóóóóóÓÓÓÓÓÓÓÓÓúúúúúúúúúÚÚÚÚÚÚÚÚÚüüüüüüüüüÜÜÜÜÜÜÜÜÜ¡¡¡¡¡¡¡¡¡¿¿¿¿¿¿¿¿¿"),"All the characters is not getting added to the question content area.");
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
            manageContent.langaugePalette_spanish.click(); //click on the spanish language
            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"Spanish palette popup is not opened");

            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            manageContent.langaugePalette_saveButton.click(); //click on the save button
            courseStreamPage.post_Link.get(0).click();

            new LoginUsingLTI().ltiLogin("441");//login as instructor
            new Navigator().NavigateTo("Current Assignments"); //navigate to course stream
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            Assert.assertTrue(courseStreamPage.postComment_content.get(1).getText().trim().contains("áÁéÉíÍñÑóÓúÚüÜ¡¿"), "All the Comments added by the Student is not displaying at the instructor side.");

        }
        catch (Exception e){
            Assert.fail("Exception in test case instructorAbleToUseNewLanguagesAsPartOfDiscussionThread  of class InstructorAbleToUseNewLanguagesAcrossApplication:", e);

        }

    }




    @Test(priority = 6, enabled = true)
    public void instructorAbleToAddCommentInTheAssignmentResponsesPage() {
        try {
            // tc row no 456
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            new LoginUsingLTI().ltiLogin("456"); //login as instructor
            new Assignment().assignToStudent(456);
            new LoginUsingLTI().ltiLogin("456_1"); //login as student
            new Assignment().submitAssignmentAsStudent(456);
            new LoginUsingLTI().ltiLogin("456"); //login as instructor
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getViewGrade_link().click();//click on view response link
            Thread.sleep(2000);
            verificationForFrenchInCSPage(1,1);
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getViewGrade_link().click();//click on view response link
            Thread.sleep(2000);
            verificationForItalianInCSPage(1,1);
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getViewGrade_link().click();//click on view response link
            Thread.sleep(2000);
            verificationForSpanishInCSPage(1,1);
            courseStreamPage.post_Link.get(1).click();

            new LoginUsingLTI().ltiLogin("456_1");//login as student
            new Navigator().NavigateTo("Assignments"); //navigate to course stream
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            Assert.assertTrue(courseStreamPage.postComment_content.get(0).getText().trim().contains("áÁéÉíÍñÑóÓúÚüÜ¡¿áááááááááÁÁÁÁÁÁÁÁÁéééééééééÉÉÉÉÉÉÉÉÉíííííííííÍÍÍÍÍÍÍÍÍñññññññññÑÑÑÑÑÑÑÑÑóóóóóóóóóÓÓÓÓÓÓÓÓÓúúúúúúúúúÚÚÚÚÚÚÚÚÚüüüüüüüüüÜÜÜÜÜÜÜÜÜ¡¡¡¡¡¡¡¡¡¿¿¿¿¿¿¿¿¿"),"All the characters is not getting added to the question content area.");
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
            manageContent.langaugePalette_spanish.click(); //click on the spanish language
            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"Spanish palette popup is not opened");

            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            manageContent.langaugePalette_saveButton.click(); //click on the save button
            courseStreamPage.post_Link.get(0).click();

            new LoginUsingLTI().ltiLogin("456");//login as instructor
            new Navigator().NavigateTo("Current Assignments"); //navigate to course stream
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            Assert.assertTrue(courseStreamPage.postComment_content.get(1).getText().trim().contains("áÁéÉíÍñÑóÓúÚüÜ¡¿"), "All the Comments added by the Student is not displaying at the instructor side.");

        }
        catch (Exception e){
            Assert.fail("Exception in test case instructorAbleToAddCommentInTheAssignmentResponsesPage  of class InstructorAbleToUseNewLanguagesAcrossApplication:", e);

        }

    }


    @Test(priority = 7, enabled = true)
    public void instructorAddResourcesAndAssociateResourceToAnyChapter() {
        try {
            // tc row no 681
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);

            new LoginUsingLTI().ltiLogin("681");//login as instructor
            new UploadResources().uploadResources("681", false, false, true);//upload chapterlevel resource
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToResourceTab();//navigate to resource tab
            new Navigator().openParticularResourceFromResourceTab(681);
            List<WebElement> allOpenLink = driver.findElements(By.cssSelector("span[class='ls-right-tab-hover-sprite folder-forward-bg']"));
            for(WebElement link:allOpenLink){
                if(link.isDisplayed()){
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",link);
                }
            }

            Thread.sleep(2000);
            verificationForFrenchInCSPage(1,1);
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToResourceTab();//navigate to resource tab
            new Navigator().openParticularResourceFromResourceTab(681);
            List<WebElement> allOpenLink1 = driver.findElements(By.cssSelector("span[class='ls-right-tab-hover-sprite folder-forward-bg']"));

            for(WebElement link:allOpenLink1){
                if(link.isDisplayed()){
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",link);
                }
            }

            Thread.sleep(2000);
            verificationForItalianInCSPage(1, 1);
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToResourceTab();//navigate to resource tab
            new Navigator().openParticularResourceFromResourceTab(681);
            List<WebElement> allOpenLink2 = driver.findElements(By.cssSelector("span[class='ls-right-tab-hover-sprite folder-forward-bg']"));
            for(WebElement link:allOpenLink2){
                if(link.isDisplayed()){
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",link);
                }
            }

            Thread.sleep(2000);
            verificationForSpanishInCSPage(1,1);
            courseStreamPage.post_Link.get(1).click();

            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            verificationForSpanishInCSPage(0, 0);
            courseStreamPage.getJumpOut().click();//click on the jump out icon
            Thread.sleep(9000);
            verificationForSpanishInCSPage(1, 1);
            courseStreamPage.post_Link.get(1).click();

            new LoginUsingLTI().ltiLogin("681_1");//login as student
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            verificationForSpanishInCSPage(0, 0);
            courseStreamPage.getJumpOut().click();//click on the jump out icon
            Thread.sleep(9000);
            verificationForSpanishInCSPage(1, 1);
            courseStreamPage.post_Link.get(1).click();

            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToResourceTab();//navigate to resource tab
            new Navigator().openParticularResourceFromResourceTab(681);
            List<WebElement> allOpenLink5 = driver.findElements(By.cssSelector("span[class='ls-right-tab-hover-sprite folder-forward-bg']"));
            for(WebElement link:allOpenLink5){
                if(link.isDisplayed()){
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",link);
                }
            }

            Thread.sleep(2000);
            verificationForFrenchInCSPage(1,1);
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToResourceTab();//navigate to resource tab
            new Navigator().openParticularResourceFromResourceTab(681);
            List<WebElement> allOpenLink4 = driver.findElements(By.cssSelector("span[class='ls-right-tab-hover-sprite folder-forward-bg']"));

            for(WebElement link:allOpenLink4){
                if(link.isDisplayed()){
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",link);
                }
            }

            Thread.sleep(2000);
            verificationForItalianInCSPage(1, 1);
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToResourceTab();//navigate to resource tab
            new Navigator().openParticularResourceFromResourceTab(681);
            List<WebElement> allOpenLink3 = driver.findElements(By.cssSelector("span[class='ls-right-tab-hover-sprite folder-forward-bg']"));
            for(WebElement link:allOpenLink3){
                if(link.isDisplayed()){
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",link);
                }
            }

            Thread.sleep(2000);
            verificationForSpanishInCSPage(1,1);
            courseStreamPage.post_Link.get(1).click();

            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            verificationForSpanishInCSPage(0, 0);
            courseStreamPage.getJumpOut().click();//click on the jump out icon
            Thread.sleep(9000);
            verificationForSpanishInCSPage(1, 1);
            courseStreamPage.post_Link.get(1).click();

        }

            catch (Exception e){
                Assert.fail("Exception in test case instructorAddResourcesAndAssociateResourceToAnyChapter  of class InstructorAbleToUseNewLanguagesAcrossApplication:", e);

            }

        }


    @Test(priority = 8, enabled = true)
    public void instructorAssignResourcesFromResourcesTab() {
        try {
            // tc row no 701
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);

            new LoginUsingLTI().ltiLogin("701");//login as instructor
            new UploadResources().uploadResources("701", false, false, true);//upload chapterlevel resource
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToResourceTab();//navigate to resource tab
            new Navigator().openParticularResourceFromResourceTab(701);
            List<WebElement> allOpenLink = driver.findElements(By.cssSelector("span[class='ls-assign-this-sprite-right-tab assign-resource-bg"));
            for(WebElement link:allOpenLink){
                if(link.isDisplayed()){
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",link);
                }
            }

            Thread.sleep(2000);
            new AssignLesson().assignLessonWithDefaultClassSection("701");

            new Navigator().NavigateTo("Current Assignments");
            new AddCommentInCSPage().verificationForSpanishInCSPage();
            courseStreamPage.post_Link.get(0).click();

            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            verificationForSpanishInCSPage(0, 0);

            new LoginUsingLTI().ltiLogin("701_1");//login as student
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            verificationForSpanishInCSPage(0, 0);

            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToResourceTab();//navigate to resource tab
            new Navigator().openParticularResourceFromResourceTab(701);
            List<WebElement> allOpenLink1 = driver.findElements(By.cssSelector("span[class='ls-assign-this-sprite-right-tab assign-resource-bg"));
            for(WebElement link:allOpenLink1){
                if(link.isDisplayed()){
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",link);
                }
            }

            Thread.sleep(2000);
            new AssignLesson().assignLessonWithDefaultClassSection("701");

            new Navigator().NavigateTo("Current Assignments");
            new AddCommentInCSPage().verificationForSpanishInCSPage();
            courseStreamPage.post_Link.get(0).click();

            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            verificationForSpanishInCSPage(0, 0);
        }
            catch (Exception e){
                Assert.fail("Exception in test case instructorAssignResourcesFromResourcesTab  of class InstructorAbleToUseNewLanguagesAcrossApplication:", e);

            }

        }



    @Test(priority = 9, enabled = true)
    public void studentShouldHaveAttemptedAssignment() {
        try {
            // tc row no 713
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new LoginUsingLTI().ltiLogin("713");//login as instructor
            new UploadResources().uploadResources("713", false, false, true);//upload chapterlevel resource
            new Navigator().NavigateTo("e-Textbook");//navigate to etextbook
            new TOCShow().tocHide();
            new Navigator().navigateToResourceTab();//navigate to resource tab
            new Navigator().openParticularResourceFromResourceTab(713);
            List<WebElement> allOpenLink = driver.findElements(By.cssSelector("span[class='ls-assign-this-sprite-right-tab assign-resource-bg"));
            for(WebElement link:allOpenLink){
                if(link.isDisplayed()){
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();",link);
                }
            }

            Thread.sleep(2000);
            new AssignLesson().assignLessonWithDefaultClassSection("713");

            new LoginUsingLTI().ltiLogin("713_1");//login as student
            new Navigator().NavigateTo("Assignments");
            assignments.resourceName.click();//clcik on resource name

            new LoginUsingLTI().ltiLogin("713");//login as instructor
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getViewGrade_link().click();//click on view response link
            Thread.sleep(2000);
            verificationForSpanishInCSPage(1,1);
            courseStreamPage.post_Link.get(1).click();

            new LoginUsingLTI().ltiLogin("713_1");//login as student
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            verificationForSpanishInCSPage(0, 0);

            new LoginUsingLTI().ltiLogin("713");//login as instructor
            new Navigator().NavigateTo("Course Stream"); //navigate to course stream
            courseStreamPage.commentLinkIn_CSPage.get(0).click(); //click on the comment link
            verificationForSpanishInCSPage(0, 0);
        }
        catch (Exception e){
            Assert.fail("Exception in test case studentShouldHaveAttemptedAssignment  of class InstructorAbleToUseNewLanguagesAcrossApplication:", e);

        }

    }



    @Test(priority = 10, enabled = true)
    public void instructorAbleViewSpanishCharactersAndProvideGradesGradAble() {
        try {
            // tc row no 768
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            PerformanceTab performanceTab = PageFactory.initElements(driver, PerformanceTab.class);

            new Assignment().create(768);
            new LoginUsingLTI().ltiLogin("768"); //login as instructor
            new Assignment().assignToStudent(768);
            new LoginUsingLTI().ltiLogin("768_1"); //login as student
            new Assignment().submitAssignmentAsStudent(768);

            new LoginUsingLTI().ltiLogin("768"); //login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current Assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            Assert.assertEquals(assignmentResponsesPage.T_Icon.isDisplayed(), true, "T-icon is not displaying");
            assignmentResponsesPage.T_Icon.click();//click on t-icon
            Assert.assertEquals(assignmentResponsesPage.fontFamily.isDisplayed(), true, "fontFamily icon is not displaying");
            Assert.assertEquals(assignmentResponsesPage.languagePalette.isDisplayed(), true, "fontFamily icon is not displaying");
            assignmentResponsesPage.languagePalette.click();//clickon language palette
            verificationForFrench();
            new Navigator().NavigateTo("Current Assignments");//navigate to current Assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            Assert.assertEquals(assignmentResponsesPage.T_Icon.isDisplayed(), true, "T-icon is not displaying");
            assignmentResponsesPage.T_Icon.click();//click on t-icon
            Assert.assertEquals(assignmentResponsesPage.fontFamily.isDisplayed(), true, "fontFamily icon is not displaying");
            Assert.assertEquals(assignmentResponsesPage.languagePalette.isDisplayed(), true, "fontFamily icon is not displaying");
            assignmentResponsesPage.languagePalette.click();//clickon language palette
            verificationForItalian();
            new Navigator().NavigateTo("Current Assignments");//navigate to current Assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            Assert.assertEquals(assignmentResponsesPage.T_Icon.isDisplayed(), true, "T-icon is not displaying");
            assignmentResponsesPage.T_Icon.click();//click on t-icon
            Assert.assertEquals(assignmentResponsesPage.fontFamily.isDisplayed(), true, "fontFamily icon is not displaying");
            Assert.assertEquals(assignmentResponsesPage.languagePalette.isDisplayed(), true, "fontFamily icon is not displaying");
            assignmentResponsesPage.languagePalette.click();//clickon language palette
            verificationForSpanish();
            new Assignment().releaseGrades(768, "Release Grade for All");

            new LoginUsingLTI().ltiLogin("768_1"); //login as student
            new Navigator().NavigateTo("Assignments");
            assignments.assignmentName.click();//click on assignment
            new ClickOnquestionCard().clickonquestioncard(0);
            Assert.assertEquals(performanceTab.teacher_feedback.isDisplayed(),true,"teacher feedback in spanish language is not dispalying");


        }     catch (Exception e){
                Assert.fail("Exception in test case instructorAbleViewSpanishCharactersAndProvideGradesGradAble  of class InstructorAbleToUseNewLanguagesAcrossApplication:", e);

            }

        }



    @Test(priority = 11, enabled = true)
    public void instructorAbleViewSpanishCharactersAndProvideGrades() {
        try {
            // tc row no 7681
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            PerformanceTab performanceTab = PageFactory.initElements(driver, PerformanceTab.class);

            new LoginUsingLTI().ltiLogin("7681"); //login as instructor
            new Assignment().assignToStudent(7681);
            new LoginUsingLTI().ltiLogin("7681_1"); //login as student
            new Assignment().submitAssignmentAsStudent(7681);

            new LoginUsingLTI().ltiLogin("7681"); //login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current Assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            Assert.assertEquals(assignmentResponsesPage.T_Icon.isDisplayed(), true, "T-icon is not displaying");
            assignmentResponsesPage.T_Icon.click();//click on t-icon
            Assert.assertEquals(assignmentResponsesPage.fontFamily.isDisplayed(), true, "fontFamily icon is not displaying");
            Assert.assertEquals(assignmentResponsesPage.languagePalette.isDisplayed(), true, "fontFamily icon is not displaying");
            assignmentResponsesPage.languagePalette.click();//clickon language palette
            verificationForSpanish();

            new LoginUsingLTI().ltiLogin("7681_1"); //login as student
            new Navigator().NavigateTo("Assignments");
            assignments.assignmentName.click();//click on assignment
            new ClickOnquestionCard().clickonquestioncard(0);
            Assert.assertEquals(performanceTab.teacher_feedback.isDisplayed(),true,"teacher feedback in spanish language is not dispalying");


        }     catch (Exception e){
            Assert.fail("Exception in test case instructorAbleViewSpanishCharactersAndProvideGrades  of class InstructorAbleToUseNewLanguagesAcrossApplication:", e);

        }

    }

    @Test(priority = 12, enabled = true)
    public void instructorAbleViewSpanishCharactersAndProvideGradesWithPolicy() {
        try {
            // tc row no 7682
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            PerformanceTab performanceTab = PageFactory.initElements(driver, PerformanceTab.class);
            String assignmentpolicyname = ReadTestData.readDataByTagName("", "assignmentpolicyname", "7682");

            new LoginUsingLTI().ltiLogin("7682"); //login as instructor
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicyname, "Policy description", "2", null, false, "1", "", "Auto-release on assignment submission", "", "", "");//till save policy
            new Assignment().assignToStudent(7682);
            new LoginUsingLTI().ltiLogin("7682_1"); //login as student
            new Assignment().submitAssignmentAsStudent(7682);

            new LoginUsingLTI().ltiLogin("7682"); //login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current Assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            Assert.assertEquals(assignmentResponsesPage.T_Icon.isDisplayed(), true, "T-icon is not displaying");
            assignmentResponsesPage.T_Icon.click();//click on t-icon
            Assert.assertEquals(assignmentResponsesPage.fontFamily.isDisplayed(), true, "fontFamily icon is not displaying");
            Assert.assertEquals(assignmentResponsesPage.languagePalette.isDisplayed(), true, "fontFamily icon is not displaying");
            assignmentResponsesPage.languagePalette.click();//clickon language palette
            verificationForSpanish();

            new LoginUsingLTI().ltiLogin("7682_1"); //login as student
            new Navigator().NavigateTo("Assignments");
            assignments.assignmentName.click();//click on assignment
            new ClickOnquestionCard().clickonquestioncard(0);
            Assert.assertEquals(performanceTab.teacher_feedback.isDisplayed(),true,"teacher feedback in spanish language is not dispalying");


        }     catch (Exception e){
            Assert.fail("Exception in test case instructorAbleViewSpanishCharactersAndProvideGradesWithPolicy  of class InstructorAbleToUseNewLanguagesAcrossApplication:", e);

        }

    }


    @Test(priority = 13, enabled = true)
    public void instructorAbleViewSpanishCharactersInAudioQuestion() {
        try {
            // tc row no 788
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            PerformanceTab performanceTab = PageFactory.initElements(driver, PerformanceTab.class);
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions questions = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions.class);

            new Assignment().create(788);
            new Assignment().addQuestions(788,"audio","");
            new LoginUsingLTI().ltiLogin("788"); //login as instructor
            new Assignment().assignToStudent(788);
            new LoginUsingLTI().ltiLogin("788_1"); //login as student
            new Assignment().submitAssignmentAsStudent(788);

            new LoginUsingLTI().ltiLogin("788"); //login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current Assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(driver.findElement(By.xpath("(//div[@class='idb-gradebook-content-coloumn'])[2]"))); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            Assert.assertEquals(assignmentResponsesPage.T_Icon.isDisplayed(), true, "T-icon is not displaying");
            assignmentResponsesPage.T_Icon.click();//click on t-icon
            Assert.assertEquals(assignmentResponsesPage.fontFamily.isDisplayed(), true, "fontFamily icon is not displaying");
            Assert.assertEquals(assignmentResponsesPage.languagePalette.isDisplayed(), true, "fontFamily icon is not displaying");
            assignmentResponsesPage.languagePalette.click();//clickon language palette
            verificationForSpanish();
            new Assignment().releaseGrades(788, "Release Grade for All");

            new LoginUsingLTI().ltiLogin("788_1"); //login as student
            new Navigator().NavigateTo("Assignments");
            assignments.assignmentName.click();//click on assignment
            questions.question_card.get(1).click();//click on 1st card
            Assert.assertEquals(performanceTab.teacher_feedback.isDisplayed(),true,"teacher feedback in spanish language is not dispalying");


        }     catch (Exception e){
            Assert.fail("Exception in test case instructorAbleViewSpanishCharactersInAudioQuestion  of class InstructorAbleToUseNewLanguagesAcrossApplication:", e);

        }

    }



    @Test(priority = 14, enabled = true)
    public void instructorAbleViewSpanishCharactersforDiscussionWidget() {
        try {
            // tc row no 792
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions questions = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);


            new LoginUsingLTI().ltiLogin("792"); //login as instructor
            new Navigator().NavigateTo("e-Textbook");
            new TopicOpen().openLessonWithDiscussionWidget();//open chapter10.3
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(792);

            new LoginUsingLTI().ltiLogin("792_1");//login a student
            new Navigator().NavigateTo("Assignments");	//navigate to Assignment
            new Click().clickByclassname("learning-activity-title"); //ip 31
            String perspective = new RandomString().randomstring(2);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspective); //add perspective to DW assignemnt

            new LoginUsingLTI().ltiLogin("792"); //login as instructor
            new Navigator().NavigateTo("Current Assignments");//navigate to current Assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            Assert.assertEquals(assignmentResponsesPage.languagePalette.isDisplayed(), true, "fontFamily icon is not displaying");
            assignmentResponsesPage.languagePalette.click();//clickon language palette

            Assert.assertEquals(assignmentResponsesPage.languageOptions.get(0).getText(), "Spanish");
            Assert.assertEquals(assignmentResponsesPage.languageOptions.get(1).getText(), "Italian");
            Assert.assertEquals(assignmentResponsesPage.languageOptions.get(2).getText(), "French");
            assignmentResponsesPage.languageOptions.get(2).click();//click on french
            Assert.assertTrue(assignmentResponsesPage.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");
            Assert.assertTrue(courseStreamPage.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");
            Assert.assertTrue(courseStreamPage.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
            Assert.assertEquals(assignmentResponsesPage.langaugePalette_header.getText().trim(), "French Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
            String inputStringValue2 = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue2);
            Assert.assertEquals(inputStringValue2.length(), 24, "All the characters is not supported as existing Functionality.");
            Assert.assertEquals(inputStringValue2, "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ", "The selected characters is not displayed in the text box of the Palette. ");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            String frenchText = driver.findElement(By.xpath("//div[@id='view-user-question-performance-feedback-box']/p")).getText();
            Assert.assertEquals(frenchText, "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ", "All the saved characters is not added to the POST text box.");
            assignmentResponsesPage.languagePalette.click();//clickon language palette
            assignmentResponsesPage.languageOptions.get(2).click();//click on spanish
            Actions action = new Actions(driver);
            WebElement From = driver.findElement(By.cssSelector("a[class='navigator ls-toc-sprite']"));
            action.dragAndDrop(assignmentResponsesPage.langaugePalette_header, From).build().perform();
            From.click();
            From.click();
            assignmentResponsesPage.languagePalette.click();//clickon language palette
            assignmentResponsesPage.languageOptions.get(2).click();//click on french
            manageContent.langaugePalette_textBox.sendKeys("abc123");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            /* String frenchText1 = driver.findElement(By.xpath("//div[@id='view-user-question-performance-feedback-box']")).getText();
           Assert.assertEquals(frenchText1, "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœabc123", "All the saved characters is not added to the POST text box.");*/



            new Navigator().NavigateTo("Current Assignments");//navigate to current Assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            Assert.assertEquals(assignmentResponsesPage.languagePalette.isDisplayed(), true, "fontFamily icon is not displaying");
            assignmentResponsesPage.languagePalette.click();//clickon language palette
            Assert.assertEquals(assignmentResponsesPage.languageOptions.get(0).getText(), "Spanish");
            Assert.assertEquals(assignmentResponsesPage.languageOptions.get(1).getText(), "Italian");
            Assert.assertEquals(assignmentResponsesPage.languageOptions.get(2).getText(), "French");
            assignmentResponsesPage.languageOptions.get(1).click();//click on italian
            Assert.assertTrue(assignmentResponsesPage.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");
            Assert.assertTrue(courseStreamPage.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");
            Assert.assertTrue(courseStreamPage.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
            Assert.assertEquals(assignmentResponsesPage.langaugePalette_header.getText().trim(), "Italian Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
            String inputStringValue1 = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue1);
            Assert.assertEquals(inputStringValue1.length(), 22, "All the characters is not supported as existing Functionality.");
            Assert.assertEquals(inputStringValue1, "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "The selected characters is not displayed in the text box of the Palette. ");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            String italianText = driver.findElement(By.xpath("//div[@id='view-user-question-performance-feedback-box']/p")).getText();
            Assert.assertEquals(italianText, "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "All the saved characters is not added to the POST text box.");
            assignmentResponsesPage.languagePalette.click();//clickon language palette
            assignmentResponsesPage.languageOptions.get(1).click();//click on spanish
            assignmentResponsesPage.langaugePalette_header.click();
            WebElement From1 = driver.findElement(By.cssSelector("a[class='navigator ls-toc-sprite']"));
            action.dragAndDrop(assignmentResponsesPage.langaugePalette_header, From1).build().perform();
            From.click();
            From.click();
            assignmentResponsesPage.languagePalette.click();//clickon language palette
            assignmentResponsesPage.languageOptions.get(1).click();//click on italian
            manageContent.langaugePalette_textBox.sendKeys("abc123");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
           /* String italianText1 = driver.findElement(By.xpath("//div[@id='view-user-question-performance-feedback-box']")).getText();
           Assert.assertEquals(italianText1, "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤abc123", "All the saved characters is not added to the POST text box.");*/


            new Navigator().NavigateTo("Current Assignments");//navigate to current Assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            Assert.assertEquals(assignmentResponsesPage.languagePalette.isDisplayed(), true, "fontFamily icon is not displaying");
            assignmentResponsesPage.languagePalette.click();//clickon language palette
            Assert.assertEquals(assignmentResponsesPage.languageOptions.get(0).getText(), "Spanish");
            Assert.assertEquals(assignmentResponsesPage.languageOptions.get(1).getText(), "Italian");
            Assert.assertEquals(assignmentResponsesPage.languageOptions.get(2).getText(), "French");
            assignmentResponsesPage.languageOptions.get(0).click();//click on spanish
            Assert.assertTrue(assignmentResponsesPage.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");
            Assert.assertTrue(courseStreamPage.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");
            Assert.assertTrue(courseStreamPage.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
            Assert.assertEquals(assignmentResponsesPage.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            for (WebElement element : manageContent.langaugePalette_allinput) {
                element.click();
            }

            driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
            String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);
            Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");
            Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
            String spanishText = driver.findElement(By.xpath("//div[@id='view-user-question-performance-feedback-box']/p")).getText();
            Assert.assertEquals(spanishText, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the saved characters is not added to the POST text box.");
            assignmentResponsesPage.languagePalette.click();//clickon language palette
            assignmentResponsesPage.languageOptions.get(0).click();//click on spanish
            WebElement From2 = driver.findElement(By.cssSelector("a[class='navigator ls-toc-sprite']"));
            assignmentResponsesPage.langaugePalette_header.click();
            action.dragAndDrop(assignmentResponsesPage.langaugePalette_header, From2).build().perform();
            From2.click();
            From2.click();
            assignmentResponsesPage.languagePalette.click();//clickon language palette
            assignmentResponsesPage.languageOptions.get(0).click();//click on spanish
            manageContent.langaugePalette_textBox.sendKeys("abc123");
            courseStreamPage.langaugePalette_saveButton.click();//click on save
          /* String spanishText1 = driver.findElement(By.xpath("//div[@id='view-user-question-performance-feedback-box']")).getText();
           Assert.assertEquals(spanishText1, "abc123áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the saved characters is not added to the POST text box.");*/

            assignmentResponsesPage.languagePalette.click();//clickon language palette
            assignmentResponsesPage.languageOptions.get(0).click();//click on spanish
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
            assignmentResponsesPage.submit.click();//click on submit
            new Assignment().releaseGrades(792, "Release Grade for All");

            new LoginUsingLTI().ltiLogin("792_1"); //login as student
            new Navigator().NavigateTo("Assignments");	//navigate to Assignment
            new Click().clickByclassname("learning-activity-title"); //ip 31
            lessonPage.jumpIcon.click();//click on jump icon
            Assert.assertEquals(lessonPage.instructorFeedback.isDisplayed(),true,"instructor feedback is not displaying");



        }     catch (Exception e){
            Assert.fail("Exception in test case instructorAbleViewSpanishCharactersforDiscussionWidget  of class InstructorAbleToUseNewLanguagesAcrossApplication:", e);

        }

    }


    @Test(priority = 15, enabled = true)
    public void provideGradesWhileGradingAParticularQuestion() {
        try {
            // tc row no 811
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions questions = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions.class);
            Gradebook gradebook = PageFactory.initElements(driver, Gradebook.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            PerformanceTab performanceTab = PageFactory.initElements(driver, PerformanceTab.class);

            new Assignment().create(811);
            new LoginUsingLTI().ltiLogin("811"); //login as instructor
            new Assignment().assignToStudent(811);
            new LoginUsingLTI().ltiLogin("811_1"); //login as student
            new Assignment().submitAssignmentAsStudent(811);

            new LoginUsingLTI().ltiLogin("811"); //login as instructor
            new Navigator().NavigateTo("Gradebook");// navigate to grade book
            gradebook.getAllAssignmentName().get(0).click();//click on assignment name
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Assert.assertEquals(assignmentResponsesPage.T_Icon.isDisplayed(), true, "T-icon is not displaying");
            assignmentResponsesPage.T_Icon.click();//click on t-icon
            Assert.assertEquals(assignmentResponsesPage.fontFamily.isDisplayed(), true, "fontFamily icon is not displaying");
            Assert.assertEquals(assignmentResponsesPage.languagePalette.isDisplayed(), true, "fontFamily icon is not displaying");
            assignmentResponsesPage.languagePalette.click();//clickon language palette
            verificationForSpanish();
            new Assignment().releaseGrades(811, "Release Grade for All");

            new LoginUsingLTI().ltiLogin("811_1"); //login as student
            new Navigator().NavigateTo("Assignments");
            assignments.assignmentName.click();//click on assignment
            questions.question_card.get(0).click();//click on 1st card
            Assert.assertEquals(performanceTab.teacher_feedback.isDisplayed(),true,"teacher feedback in spanish language is not dispalying");


        }     catch (Exception e){
            Assert.fail("Exception in test case provideGradesWhileGradingAParticularQuestion  of class InstructorAbleToUseNewLanguagesAcrossApplication:", e);

        }

    }


    @Test(priority = 16, enabled = true)
    public void provideGradesWhileGradingAParticularQuestionEngageReport() {
        try {
            // tc row no 828
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions questions = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions.class);
            PerformanceTab performanceTab = PageFactory.initElements(driver, PerformanceTab.class);
            EngagementReport engagementReport = PageFactory.initElements(driver, EngagementReport.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(828));

            new Assignment().create(828);
            new LoginUsingLTI().ltiLogin("828_1"); //login as student
            new TOCShow().chaptertree();//click on chapter tree
            new SelectCourse().selectInvisibleAssignment(assignmentname);
            lessonPage.submitAnswer.click();//click on submit
            Thread.sleep(3000);
            try {
                driver.findElement(By.xpath("//div[@id='footer-info-content']/div[4]/input[@title='Finish']")).click();

            } catch (Exception e) {

                driver.findElement(By.xpath("//div[@id='footer-info-content']/div[4]/input[@title='Finish']")).click();

            }
            Thread.sleep(5000);
            new LoginUsingLTI().ltiLogin("828"); //login as instructor
            new Navigator().NavigateTo("Engagement Report");//navigate to engagement report
            engagementReport.assessmentBox.get(1).click();//click on assessment
            new SelectCourse().selectInvisibleAssignment(assignmentname);
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Assert.assertEquals(assignmentResponsesPage.T_Icon.isDisplayed(), true, "T-icon is not displaying");
            assignmentResponsesPage.T_Icon.click();//click on t-icon
            Assert.assertEquals(assignmentResponsesPage.fontFamily.isDisplayed(), true, "fontFamily icon is not displaying");
            Assert.assertEquals(assignmentResponsesPage.languagePalette.isDisplayed(), true, "fontFamily icon is not displaying");
            assignmentResponsesPage.languagePalette.click();//clickon language palette
            verificationForSpanish();

            new LoginUsingLTI().ltiLogin("828_1"); //login as student
            new TOCShow().chaptertree();//click on chapter tree
            new SelectCourse().selectInvisibleAssignment(assignmentname);
            questions.question_card.get(0).click();//click on 1st card
            Assert.assertEquals(performanceTab.teacher_feedback.isDisplayed(),true,"teacher feedback in spanish language is not dispalying");


        }     catch (Exception e){
            Assert.fail("Exception in test case provideGradesWhileGradingAParticularQuestionEngageReport  of class InstructorAbleToUseNewLanguagesAcrossApplication:", e);

        }

    }

    @Test(priority = 17, enabled = true)
    public void provideGradesWhileGradingAParticularQuestionAsMentor() {
        try {
            // tc row no 828
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions questions = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.DiagnosticAssessment.Questions.class);
            PerformanceTab performanceTab = PageFactory.initElements(driver, PerformanceTab.class);
            EngagementReport engagementReport = PageFactory.initElements(driver, EngagementReport.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(828));

            new LoginUsingLTI().ltiLogin("8281_1"); //login as student
            new TOCShow().chaptertree();//click on chapter tree
            new SelectCourse().selectInvisibleAssignment(assignmentname);
            lessonPage.submitAnswer.click();//click on submit
            Thread.sleep(3000);
            try {
                driver.findElement(By.xpath("//div[@id='footer-info-content']/div[4]/input[@title='Finish']")).click();

            } catch (Exception e) {

                driver.findElement(By.xpath("//div[@id='footer-info-content']/div[4]/input[@title='Finish']")).click();

            }
            Thread.sleep(5000);
            new LoginUsingLTI().ltiLogin("8281"); //login as instructor
            new Navigator().NavigateTo("Engagement Report");//navigate to engagement report
            engagementReport.assessmentBox.get(1).click();//click on assessment
            new SelectCourse().selectInvisibleAssignment(assignmentname);
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getViewResponse_link()); //mouse over view response link
            new WebDriverWait(driver, 180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Assert.assertEquals(assignmentResponsesPage.T_Icon.isDisplayed(), true, "T-icon is not displaying");
            assignmentResponsesPage.T_Icon.click();//click on t-icon
            Assert.assertEquals(assignmentResponsesPage.fontFamily.isDisplayed(), true, "fontFamily icon is not displaying");
            Assert.assertEquals(assignmentResponsesPage.languagePalette.isDisplayed(), true, "fontFamily icon is not displaying");
            assignmentResponsesPage.languagePalette.click();//clickon language palette
            verificationForSpanish();

            new LoginUsingLTI().ltiLogin("8281_1"); //login as student
            new TOCShow().chaptertree();//click on chapter tree
            new SelectCourse().selectInvisibleAssignment(assignmentname);
            questions.question_card.get(0).click();//click on 1st card
            Assert.assertEquals(performanceTab.teacher_feedback.isDisplayed(),true,"teacher feedback in spanish language is not dispalying");


        }     catch (Exception e){
            Assert.fail("Exception in test case provideGradesWhileGradingAParticularQuestionAsMentor  of class InstructorAbleToUseNewLanguagesAcrossApplication:", e);

        }

    }

    @Test(priority = 18, enabled = true)
    public void fileBasedAssignment() {
        try {
            // tc row no 848
            AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(848));
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            NewAssignment newAssignment = PageFactory.initElements(driver, NewAssignment.class);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            QuestionBank questionBank = PageFactory.initElements(driver, QuestionBank.class);
            AssignmentTab assignmentTab = PageFactory.initElements(driver, AssignmentTab.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);

            new LoginUsingLTI().ltiLogin("848"); //login as instructor
            new Navigator().NavigateTo("Current Assignments");//click on current assignment
            currentAssignments.newAssignment_Button.click();//click on new assignment;
            currentAssignments.createFileBasedAssignmentButton.click();//click on file based assignment

            newAssignment.assessmentNameTextBox.click();//click on the text box
            Thread.sleep(2000);
            newAssignment.assessmentName_TextBox.sendKeys(assignmentname);
            Thread.sleep(2000);
            manageContent.questionPrompt.click();//click on question prompt
            Thread.sleep(1000);
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
            new ImpactsOnAssignmentDiscussionOnTheStudentSide().fileUploadNotificationMessageValidate("848", "Your file upload request is being processed...");
            new AssignLesson().assignCustomAssignmentFromCustomAssignmentPage(848);
            Thread.sleep(1000);

            new Navigator().NavigateTo("My Question Bank"); //navigate to question bank
            new UIElement().waitAndFindElement(By.cssSelector("span[class='ls-preview-wrapper action-links']"));
            List<WebElement> preview = driver.findElements(By.cssSelector("span[class='ls-preview-wrapper action-links']"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", preview.get(0));
            Thread.sleep(8000);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("cms-content-question-review-list-label")));
            Assert.assertEquals(questionBank.languagePreview.get(0).getText().trim(), "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "Entry is not displaying for language palette.");

            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getAssessmentName().click();//click on assignment name
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("cms-content-question-review-list-label")));
            Assert.assertEquals(questionBank.languagePreview.get(0).getText().trim(), "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "Entry is not displaying for language palette.");

            new LoginUsingLTI().ltiLogin("848_1"); //login as student
            new Navigator().NavigateTo("Assignments");//navigate to assignment
            currentAssignments.getAssessmentName().click();//click on assignment
            new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.className("resource-title")));
            assignmentTab.finishButton.click();//click on finish assignment
            Thread.sleep(1000);

            new LoginUsingLTI().ltiLogin("848"); //login as instructor
            new Navigator().NavigateTo("Current Assignments");
            currentAssignments.getViewGrade_link().click();//click on view student response
            assignmentResponsesPage.assessmentName_AssignmentResponse.get(1).click();//click on assignment name
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("cms-content-question-review-list-label")));
            Assert.assertEquals(questionBank.languagePreview.get(0).getText().trim(), "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "Entry is not displaying for language palette.");
            new Navigator().NavigateTo("Course Stream");
            courseStreamPage.assessmentName.click();//click on assignment name
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("cms-content-question-review-list-label")));
            Assert.assertEquals(questionBank.languagePreview.get(0).getText().trim(), "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "Entry is not displaying for language palette.");
            new Assignment().openAssignmentFromAssignmentTab(0);
            Assert.assertEquals(questionBank.languagePreview.get(0).getText().trim(), "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "Entry is not displaying for language palette.");

            new Navigator().NavigateTo("Current Assignments");//navigate to current Assignment
            currentAssignments.getViewGrade_link().click();//click on view response
            new MouseHover().mouserhoverbywebelement(driver.findElement(By.xpath("(//div[@class='idb-gradebook-content-coloumn'])"))); //mouse over view response link
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.className("ls-view-response-link")));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link"))); //click on the response link
            Thread.sleep(5000);
            Assert.assertEquals(assignmentResponsesPage.T_Icon.isDisplayed(), true, "T-icon is not displaying");
            assignmentResponsesPage.T_Icon.click();//click on t-icon
            Assert.assertEquals(assignmentResponsesPage.fontFamily.isDisplayed(), true, "fontFamily icon is not displaying");
            Assert.assertEquals(assignmentResponsesPage.languagePalette.isDisplayed(), true, "fontFamily icon is not displaying");
            assignmentResponsesPage.languagePalette.click();//clickon language palette
            verificationForSpanish();

        }     catch (Exception e){
            Assert.fail("Exception in test case fileBasedAssignment  of class InstructorAbleToUseNewLanguagesAcrossApplication:", e);

        }

    }





    public  void verificationForSpanish() {

        CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
        ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver,AssignmentResponsesPage.class);

        Assert.assertEquals(assignmentResponsesPage.languageOptions.get(0).getText(), "Spanish");
        Assert.assertEquals(assignmentResponsesPage.languageOptions.get(1).getText(), "Italian");
        Assert.assertEquals(assignmentResponsesPage.languageOptions.get(2).getText(), "French");
        assignmentResponsesPage.languageOptions.get(0).click();//click on spanish
        Assert.assertTrue(assignmentResponsesPage.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");
        Assert.assertTrue(courseStreamPage.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");
        Assert.assertTrue(courseStreamPage.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
        Assert.assertEquals(assignmentResponsesPage.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

        for (WebElement element : manageContent.langaugePalette_allinput) {
            element.click();
        }

        driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
        String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
        System.out.println("input String value:" + inputStringValue);
        Assert.assertEquals(inputStringValue.length(), 16, "All the characters is not supported as existing Functionality.");
        Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
        courseStreamPage.langaugePalette_saveButton.click();//click on save
        String spanishText = driver.findElement(By.xpath("//div[@id='view-user-question-performance-feedback-box']/p")).getText();
        Assert.assertEquals(spanishText, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the saved characters is not added to the POST text box.");
        assignmentResponsesPage.languagePalette.click();//clickon language palette
        assignmentResponsesPage.languageOptions.get(0).click();//click on spanish
        Actions action = new Actions(driver);
        WebElement From = driver.findElement(By.cssSelector("a[class='navigator ls-toc-sprite']"));
        assignmentResponsesPage.langaugePalette_header.click();
        action.dragAndDrop(assignmentResponsesPage.langaugePalette_header, From).build().perform();
        From.click();
        From.click();
        assignmentResponsesPage.T_Icon.click();//click on t-icon
        assignmentResponsesPage.languagePalette.click();//clickon language palette
        assignmentResponsesPage.languageOptions.get(0).click();//click on spanish
        manageContent.langaugePalette_textBox.sendKeys("abc123");
        courseStreamPage.langaugePalette_saveButton.click();//click on save
       /* String spanishText1 = driver.findElement(By.xpath("//div[@id='view-user-question-performance-feedback-box']")).getText();
        Assert.assertEquals(spanishText1, "abc123áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the saved characters is not added to the POST text box.");*/

        assignmentResponsesPage.languagePalette.click();//clickon language palette
        assignmentResponsesPage.languageOptions.get(0).click();//click on spanish
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
        assignmentResponsesPage.getSaveButton().click();//click on submit

    }

    public  void verificationForItalian() {

        CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
        ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);

        Assert.assertEquals(assignmentResponsesPage.languageOptions.get(0).getText(), "Spanish");
        Assert.assertEquals(assignmentResponsesPage.languageOptions.get(1).getText(), "Italian");
        Assert.assertEquals(assignmentResponsesPage.languageOptions.get(2).getText(), "French");
        assignmentResponsesPage.languageOptions.get(1).click();//click on italian
        Assert.assertTrue(assignmentResponsesPage.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");
        Assert.assertTrue(courseStreamPage.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");
        Assert.assertTrue(courseStreamPage.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
        Assert.assertEquals(assignmentResponsesPage.langaugePalette_header.getText().trim(), "Italian Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

        for (WebElement element : manageContent.langaugePalette_allinput) {
            element.click();
        }

        driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
        String inputStringValue1 = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
        System.out.println("input String value:" + inputStringValue1);
        Assert.assertEquals(inputStringValue1.length(), 22, "All the characters is not supported as existing Functionality.");
        Assert.assertEquals(inputStringValue1, "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "The selected characters is not displayed in the text box of the Palette. ");
        courseStreamPage.langaugePalette_saveButton.click();//click on save
        String italianText = driver.findElement(By.xpath("//div[@id='view-user-question-performance-feedback-box']/p")).getText();
        Assert.assertEquals(italianText, "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "All the saved characters is not added to the POST text box.");
        assignmentResponsesPage.languagePalette.click();//clickon language palette
        assignmentResponsesPage.languageOptions.get(1).click();//click on spanish
        assignmentResponsesPage.langaugePalette_header.click();
        Actions action = new Actions(driver);
        WebElement From = driver.findElement(By.cssSelector("a[class='navigator ls-toc-sprite']"));
        action.dragAndDrop(assignmentResponsesPage.langaugePalette_header, From).build().perform();
        From.click();
        From.click();
        assignmentResponsesPage.T_Icon.click();//click on t-icon
        assignmentResponsesPage.languagePalette.click();//clickon language palette
        assignmentResponsesPage.languageOptions.get(1).click();//click on italian
        manageContent.langaugePalette_textBox.sendKeys("abc123");
        courseStreamPage.langaugePalette_saveButton.click();//click on save
       /* String italianText1 = driver.findElement(By.xpath("//div[@id='view-user-question-performance-feedback-box']")).getText();
        Assert.assertEquals(italianText1, "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤abc123", "All the saved characters is not added to the POST text box.");*/
    }



    public  void verificationForFrench() {
        CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
        ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        AssignmentResponsesPage assignmentResponsesPage = PageFactory.initElements(driver, AssignmentResponsesPage.class);

        Assert.assertEquals(assignmentResponsesPage.languageOptions.get(0).getText(), "Spanish");
        Assert.assertEquals(assignmentResponsesPage.languageOptions.get(1).getText(), "Italian");
        Assert.assertEquals(assignmentResponsesPage.languageOptions.get(2).getText(), "French");
        assignmentResponsesPage.languageOptions.get(2).click();//click on french
        Assert.assertTrue(assignmentResponsesPage.langaugePalette_popup.isDisplayed(), "Spanish palette popup is not opened");
        Assert.assertTrue(courseStreamPage.langaugePalette_cancelButton.isDisplayed(), "The pop up in not having cancel button");
        Assert.assertTrue(courseStreamPage.langaugePalette_saveButton.isDisplayed(), "The pop up in not having save button");
        Assert.assertEquals(assignmentResponsesPage.langaugePalette_header.getText().trim(), "French Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

        for (WebElement element : manageContent.langaugePalette_allinput) {
            element.click();
        }

        driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
        String inputStringValue2 = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
        System.out.println("input String value:" + inputStringValue2);
        Assert.assertEquals(inputStringValue2.length(), 24, "All the characters is not supported as existing Functionality.");
        Assert.assertEquals(inputStringValue2, "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ", "The selected characters is not displayed in the text box of the Palette. ");
        courseStreamPage.langaugePalette_saveButton.click();//click on save
        String frenchText = driver.findElement(By.xpath("//div[@id='view-user-question-performance-feedback-box']/p")).getText();
        Assert.assertEquals(frenchText, "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ", "All the saved characters is not added to the POST text box.");
        assignmentResponsesPage.languagePalette.click();//clickon language palette
        assignmentResponsesPage.languageOptions.get(2).click();//click on spanish
        Actions action = new Actions(driver);
        WebElement From = driver.findElement(By.cssSelector("a[class='navigator ls-toc-sprite']"));
        action.dragAndDrop(assignmentResponsesPage.langaugePalette_header, From).build().perform();
        From.click();
        From.click();
        assignmentResponsesPage.T_Icon.click();//click on t-icon
        assignmentResponsesPage.languagePalette.click();//clickon language palette
        assignmentResponsesPage.languageOptions.get(2).click();//click on french
        manageContent.langaugePalette_textBox.sendKeys("abc123");
        courseStreamPage.langaugePalette_saveButton.click();//click on save
       /* String frenchText1 = driver.findElement(By.xpath("//div[@id='view-user-question-performance-feedback-box']")).getText();
        Assert.assertEquals(frenchText1, "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœabc123", "All the saved characters is not added to the POST text box.");*/

    }

    public  void verificationForFrenchInCSPage(int comment,int commentBox) throws InterruptedException {
        CourseStreamPage courseStreamPage= PageFactory.initElements(driver,CourseStreamPage.class);
        JavascriptExecutor jse=(JavascriptExecutor)driver;
        ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
        //TC row no 14
        new UIElement().waitAndFindElement(courseStreamPage.commentLinkIn_CSPage.get(0));
        courseStreamPage.commentLinkIn_CSPage.get(comment).click(); //click on the comment link
        courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon

        Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
        Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(),"Italian");
        Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(),"French");

        manageContent.langaugePalette_French.click(); //click on the spanish language
        Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"Spanish palette popup is not opened");

        for(WebElement element:manageContent.langaugePalette_allinput){
            element.click();
        }

        String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
        System.out.println("input String value:" + inputStringValue);

        Assert.assertEquals(inputStringValue.length(),24,"All the characters is not supported as existing Functionality.");

        //Tc row no 17
        Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

        //Tc row no 18
        Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
        //Tc row no 19
        Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
        Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

        //TC row no 20
        Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "French Editor", "The popup is not having text has \"French Editor\" text over header, with Left aligned.");

        //TC row no 21
        Assert.assertEquals(inputStringValue,"ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ","The selected characters is not displayed in the text box of the Palette. ");
        //TC row no 22
        int height=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

        manageContent.langaugePalette_saveButton.click(); //click on the save button

        //TC row no 23
        Assert.assertEquals(courseStreamPage.commentBox.get(commentBox).getText().trim(),"ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ","All the characters is not getting added to the question content area.");

        //TC row no 24
        courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
        manageContent.langaugePalette_French.click(); //click on the spanish language
        for(WebElement element:manageContent.langaugePalette_allinput){
            element.click();
            element.click();
            element.click();
            element.click();
            element.click();
        }
        Thread.sleep(3000);
        int height1=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));
        manageContent.langaugePalette_saveButton.click(); //click on the save button

        //Tc row no 106
        if(height>height1){
            Assert.fail("The height of the question textbox is not  increased accordingly");
        }

        courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
        manageContent.langaugePalette_French.click(); //click on the spanish language
        for(WebElement element:manageContent.langaugePalette_allinput){
            element.click();
        }

        //TC row no 25
        manageContent.langaugePalette_cancelButton.click();//click on the cancel button

        Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")),false,"The Editor is not getting closed.");
        Assert.assertEquals(courseStreamPage.commentBox.get(commentBox).getText().trim(),"ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœÀÀÀÀÀàààààÂÂÂÂÂâââââÆÆÆÆÆæææææÇÇÇÇÇçççççÈÈÈÈÈèèèèèÉÉÉÉÉéééééÊÊÊÊÊêêêêêËËËËËëëëëëÎÎÎÎÎîîîîîÏÏÏÏÏïïïïïÔÔÔÔÔôôôôôŒŒŒŒŒœœœœœ","All the characters is not getting added to the question content area.");

    }


    public  void verificationForItalianInCSPage(int comment,int commentBox) throws InterruptedException {
        CourseStreamPage courseStreamPage= PageFactory.initElements(driver,CourseStreamPage.class);
        JavascriptExecutor jse=(JavascriptExecutor)driver;
        ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
        //TC row no 14
        new UIElement().waitAndFindElement(courseStreamPage.commentLinkIn_CSPage.get(0));
        courseStreamPage.commentLinkIn_CSPage.get(comment).click(); //click on the comment link
        courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon

        Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
        Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(),"Italian");
        Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(),"French");

        manageContent.langaugePalette_Italian.click(); //click on the spanish language
        Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"Spanish palette popup is not opened");

        for(WebElement element:manageContent.langaugePalette_allinput){
            element.click();
        }

        String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
        System.out.println("input String value:" + inputStringValue);

        Assert.assertEquals(inputStringValue.length(),22,"All the characters is not supported as existing Functionality.");

        //Tc row no 17
        Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

        //Tc row no 18
        Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
        //Tc row no 19
        Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
        Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

        //TC row no 20
        Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Italian Editor", "The popup is not having text has \"Italian Editor\" text over header, with Left aligned.");

        //TC row no 21
        Assert.assertEquals(inputStringValue,"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤","The selected characters is not displayed in the text box of the Palette. ");
        //TC row no 22
        int height=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

        manageContent.langaugePalette_saveButton.click(); //click on the save button

        //TC row no 23
        Assert.assertEquals(courseStreamPage.commentBox.get(commentBox).getText().trim(),"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤","All the characters is not getting added to the question content area.");

        //TC row no 24
        courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
        manageContent.langaugePalette_Italian.click(); //click on the spanish language
        for(WebElement element:manageContent.langaugePalette_allinput){
            element.click();
            element.click();
            element.click();
            element.click();
            element.click();
            element.click();
        }
        Thread.sleep(3000);
        int height1=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));
        manageContent.langaugePalette_saveButton.click(); //click on the save button

        //Tc row no 106
        if(height>height1){
            Assert.fail("The height of the question textbox is not  increased accordingly");
        }

        courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
        manageContent.langaugePalette_Italian.click(); //click on the spanish language
        for(WebElement element:manageContent.langaugePalette_allinput){
            element.click();
        }

        //TC row no 25
        manageContent.langaugePalette_cancelButton.click();//click on the cancel button

        Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")),false,"The Editor is not getting closed.");
        Assert.assertEquals(courseStreamPage.commentBox.get(commentBox).getText().trim(),"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤ÀÀÀÀÀÀààààààÁÁÁÁÁÁááááááÈÈÈÈÈÈèèèèèèÉÉÉÉÉÉééééééÌÌÌÌÌÌììììììÍÍÍÍÍÍííííííÒÒÒÒÒÒòòòòòòÓÓÓÓÓÓóóóóóóÙÙÙÙÙÙùùùùùùÚÚÚÚÚÚúúúúúú€€€€€€₤₤₤₤₤₤","All the characters is not getting added to the question content area.");

    }


    public  void verificationForSpanishInCSPage(int comment,int commentBox) throws InterruptedException {
        CourseStreamPage courseStreamPage= PageFactory.initElements(driver,CourseStreamPage.class);
        JavascriptExecutor jse=(JavascriptExecutor)driver;
        ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
        //TC row no 14
        new UIElement().waitAndFindElement(courseStreamPage.commentLinkIn_CSPage.get(0));
        courseStreamPage.commentLinkIn_CSPage.get(comment).click(); //click on the comment link
        courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon

        Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
        Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(),"Italian");
        Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(),"French");

        manageContent.langaugePalette_spanish.click(); //click on the spanish language
        Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"Spanish palette popup is not opened");

        for(WebElement element:manageContent.langaugePalette_allinput){
            element.click();
        }

        String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
        System.out.println("input String value:" + inputStringValue);

        Assert.assertEquals(inputStringValue.length(),16,"All the characters is not supported as existing Functionality.");

        //Tc row no 17
        Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

        //Tc row no 18
        Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
        //Tc row no 19
        Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
        Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

        //TC row no 20
        Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

        //TC row no 21
        Assert.assertEquals(inputStringValue,"áÁéÉíÍñÑóÓúÚüÜ¡¿","The selected characters is not displayed in the text box of the Palette. ");
        //TC row no 22
        int height=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));

        manageContent.langaugePalette_saveButton.click(); //click on the save button

        //TC row no 23
        Assert.assertEquals(courseStreamPage.commentBox.get(commentBox).getText().trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the characters is not getting added to the question content area.");

        //TC row no 24
        courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
        manageContent.langaugePalette_spanish.click(); //click on the spanish language
        for(WebElement element:manageContent.langaugePalette_allinput){
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
        int height1=Integer.parseInt(manageContent.langaugePalette_textBox.getCssValue("height").substring(0, 2));
        manageContent.langaugePalette_saveButton.click(); //click on the save button


        //Tc row no 106
        if(height>height1){
            Assert.fail("The height of the question textbox is not  increased accordingly");
        }

        courseStreamPage.languagePaletteIcon_CSPage.get(0).click(); //click on the langauge Palette icon
        manageContent.langaugePalette_spanish.click(); //click on the spanish language
        for(WebElement element:manageContent.langaugePalette_allinput){
            element.click();
        }

        //TC row no 25
        manageContent.langaugePalette_cancelButton.click();//click on the cancel button

        Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")),false,"The Editor is not getting closed.");
        Assert.assertEquals(courseStreamPage.commentBox.get(commentBox).getText().trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿áááááááááÁÁÁÁÁÁÁÁÁéééééééééÉÉÉÉÉÉÉÉÉíííííííííÍÍÍÍÍÍÍÍÍñññññññññÑÑÑÑÑÑÑÑÑóóóóóóóóóÓÓÓÓÓÓÓÓÓúúúúúúúúúÚÚÚÚÚÚÚÚÚüüüüüüüüüÜÜÜÜÜÜÜÜÜ¡¡¡¡¡¡¡¡¡¿¿¿¿¿¿¿¿¿","All the characters is not getting added to the question content area.");

    }

}


