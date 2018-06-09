package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT17.R1721;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;


/*
 * Created by 'Dharaneesh TGowda' on 7/10/14.
 */
public class CapturingAuthorAndCreationDateForQues extends Driver {

    @Test(priority = 1, enabled = true)
    public void captureAuthorAndCreationDateForQues() {
        String actual = null;
        String expected = null;
        String authorFailureMessge = null;
        String creationDateFailureMessage = null;
        try {

            //Row No - 4 : "1. Login as CMS user.2. Select a course.3. Select a chapter.4. Select an assessment
            //5. Click on new option to add question 6. Select a question type 7. Fill in question details 8. Click on save.
            //Driver.startDriver();
            new Assignment().create(4);
            new Assignment().addQuestions(4, "truefalse", "");
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(4));

            //Expected : Question page should be displayed in edit mode
            new TextSend().textsendbyid(questiontext, "question-raw-content");//Type a Question
            expected = questiontext;
            actual = new TextFetch().textfetchbyid("question-raw-content");
            Assert.assertEquals(actual, expected, "The text box is not editable in Quesion Creation");

            //Expected  - Name of the author who has created this particular question should be displayed with label "Author:"
            actual = new TextFetch().textfetchbyxpath("//div[@class='fieldrow' and @key = 'authorName']");
            expected = "Author:\n" + "Spaces, learning";
            Assert.assertEquals(actual, expected, "The Author name is not displaying as per the format with the label 'Author'");

            //Expected - The Date and time when the question was created should be displayed with label "Creation Date:"
            String[] creationDateTones = new TextFetch().textfetchbyxpath("//div[@class='fieldrow' and @key = 'creationDate']").split("[|]");
            actual = creationDateTones[0].trim();
            expected = "Creation Date:\n" + new Questions().getTodaysDateAsperFormat();
            Assert.assertEquals(actual, expected, "The Creation data is not displaying as per the format with the label 'Creation Date'");

            //Expected - Author and creation date should be displayed
            authorFailureMessge = "Author Name is not displaying in Question Creation";
            creationDateFailureMessage = "Creation Date is not displaying in Question Creation";
            checkAuthorNameAndCreationDateDisplay(authorFailureMessge, creationDateFailureMessage);

            //Row No - 13 : Create a True False question
            //Expected - Author and creation date should be displayed
            authorFailureMessge = "Author Name is not displaying in True false Question Creation";
            creationDateFailureMessage = "Creation Date is not displaying in True false Question Creation";
            new Assignment().addQuestions(4, "truefalse", "");
            checkAuthorNameAndCreationDateDisplay(authorFailureMessge, creationDateFailureMessage);

            //Row No - 14 : Create a Multiple Selection question
            //Expected - Author and creation date should be displayed
            authorFailureMessge = "Author Name is not displaying in Multiple Selection Question Creation";
            creationDateFailureMessage = "Creation Date is not displaying in Multiple Selection Question Creation";
            new Assignment().addQuestions(4, "multipleselection", "");
            checkAuthorNameAndCreationDateDisplay(authorFailureMessge, creationDateFailureMessage);

            //Row No - 15 : Create a Text Entry question
            //Expected - Author and creation date should be displayed
            authorFailureMessge = "Author Name is not displaying in Text Entry Question Creation";
            creationDateFailureMessage = "Creation Date is not displaying in Text Entry Question Creation";
            new Assignment().addQuestions(4, "textentry", "");
            checkAuthorNameAndCreationDateDisplay(authorFailureMessge, creationDateFailureMessage);

            //Row No - 16 : Create a Text Dropdown question
            //Expected - Author and creation date should be ;
            authorFailureMessge = "Author Name is not displaying in Text Dropdown Question Creation";
            creationDateFailureMessage = "Creation Date is not displaying in Text Dropdown Question Creation";
            new Assignment().addQuestions(4, "textdropdown", "");
            checkAuthorNameAndCreationDateDisplay(authorFailureMessge, creationDateFailureMessage);

            //Row No - 18 : 16. Create a Numeric Entry with Units question
            //Expected - Author and creation date should be displayed
            authorFailureMessge = "Author Name is not displaying in Numeric Entry with Units Question Creation";
            creationDateFailureMessage = "Creation Date is not displaying in Numeric Entry with Units Question Creation";
            new Assignment().addQuestions(4, "numericentrywithunits", "");
            checkAuthorNameAndCreationDateDisplay(authorFailureMessge, creationDateFailureMessage);

            //Row No - 19 : Create a Maple Numeric/Advanced Numeric question
            //Expected - Author and creation date should be displayed
            authorFailureMessge = "Author Name is not displaying in Maple Numeric/Advanced Numeric Question Creation";
            creationDateFailureMessage = "Creation Date is not displaying in Maple Numeric/Advanced Numeric Question Creation";
            new Assignment().addQuestions(4, "advancednumeric", "");
            checkAuthorNameAndCreationDateDisplay(authorFailureMessge, creationDateFailureMessage);

            //Row No - 20 : Create a Maple Symbolic/Expression Evaluator question
            //Expected - Author and creation date should be displayed
            authorFailureMessge = "Author Name is not displaying in Maple Symbolic/Expression Evaluator Question Creation";
            creationDateFailureMessage = "Creation Date is not displaying in Maple Symbolic/Expression Evaluator Question Creation";
            new Assignment().addQuestions(4, "expressionevaluator", "");
            checkAuthorNameAndCreationDateDisplay(authorFailureMessge, creationDateFailureMessage);

            //Row No - 23 : 21. Create a Essay type question
            //Expected - Author and creation date should be displayed
            authorFailureMessge = "Author Name is not displaying in Essay type Question Creation";
            creationDateFailureMessage = "Creation Date is not displaying in Essay type Evaluator Question Creation";
            new Assignment().addQuestions(4, "essay", "");
            checkAuthorNameAndCreationDateDisplay(authorFailureMessge, creationDateFailureMessage);

            //Row No - 26 : Create a Writeboard question
            //Expected - Author and creation date should be displayed
            authorFailureMessge = "Author Name is not displaying in Writeboard Question Creation";
            creationDateFailureMessage = "Creation Date is not displaying in Writeboard Question Creation";
            new Assignment().addQuestions(4, "writeboard", "");
            checkAuthorNameAndCreationDateDisplay(authorFailureMessge, creationDateFailureMessage);

        } catch (Exception e) {
            Assert.fail("Exception in testcase captureAuthorAndCreationDateForQues in class CapturingAuthorAndCreationDateForQues.", e);
        }
    }


    @Test(priority = 2, enabled = true)
    public void checkAuthorAndCreationDateForDuplicateQuestion() {
        String actual;
        String expected;
        try {
            //Row No - 42 : "1. Login as CMS user. 2. Go to a question page 3. Create duplicate question 4. Verify duplicate question"
            //Expected 1 - User who performed duplication action should be displayed as author
            //Expected 2 - Creation date should be the date of duplication
            //Driver.startDriver();
            new DirectLogin().directLoginWithCreditial("lspaces2", "snapwiz", 42);

            new Click().clickbylistxpath("//a[contains(@id,'sbSelector_')]", 2);//Click on 'All Course Types'
            new Click().clickbylinkText("Learning Space + Adaptive Component");// Click on 'Learning Space + Adaptive Component'
            new Questions().duplicateQuestion(42);
            new Click().clickbyxpath("//div[text() = 'Duplicate']");
            Thread.sleep(2000);
            new Click().clickByid("saveQuestionDetails1");
            actual = new Questions().getQuestionsAuthorName();
            expected = "Spaces, learning";
            Assert.assertEquals(actual, expected);
            actual = new Questions().getQuestionsCreationDate();
            expected = new Questions().getTodaysDateAsperFormat();
            Assert.assertEquals(actual, expected);

        } catch (Exception e) {
            Assert.fail("Exception in testcase createAuthorAndDateForDuplicateQuestion in class CapturingAuthorAndCreationDateForQues", e);
        }
    }


    @Test(priority = 3, enabled = true)
    public void checkAuthorAndCreationDateForCopyorMoveQuestion1() {
        String expected_authorName = null;
        String expected_creationDate = null;
        String actual_authorName = null;
        String actual_creationDate = null;
        try {

            //Row No - 44 : "1. Login as CMS user. 2. Select a question 3. Copy the question without reference to other course"
            //Expected : Question should keep on displaying original author and creation date
            //Driver.startDriver();//Start the browser
            new Assignment().create(44);
            new Assignment().addQuestions(44, "textdropdown", "");
            Thread.sleep(1000);
            new Questions().navigateToQuestionPage(44);
            new Questions().clickingEditButtoninQuestionTofetchCreationDateAndAuthorName();
            expected_authorName = new Questions().getQuestionsAuthorName();//Get the author Name from the existing Question in the edit mode
            expected_creationDate = new Questions().getTodaysDateAsperFormat();//Get the Creation Date from the existing Question in the edit mode
            driver.navigate().back();// Navigated Browser back to history for one page
            Thread.sleep(3000);
            new Questions().copySecondQuestionWithOutReferenceToOtherCourse(44);//Function call to copy the second question With reference
            Thread.sleep(2000);
            //new Questions().clickingEditButtoninQuestionTofetchCreationDateAndAuthorName();
            new Questions().clickingEditButtoninQuestionTofetchCreationDateAndAuthorName(44);

            actual_authorName = new Questions().getQuestionsAuthorName();//Get the author Name from the existing Question in the edit mode
            actual_creationDate = new Questions().getQuestionsCreationDate();//Get the Creation Date from the existing Question in the edit mode
            driver.navigate().back();// Navigated Browser back to history for one page
            Assert.assertEquals(actual_authorName, expected_authorName, "The Author Name is not same after copying question to other course without reference in the original Question");
            Assert.assertEquals(actual_creationDate, expected_creationDate, "The Creation Date is not same after copying question to other course without reference in the original Question");

            //Row No - 45 : "4. Go to the other course 5. Open the copied question"
            //Expected 1 : User who performed copy action should be displayed as author
            //Expected 2 : Creation date should be the date of copy operation
            new Click().clickByid("header-change-course");// Click on the 'Change' link
            Thread.sleep(2000);
            String course = Config.lscourse;
            new Click().clickbyxpath("//img[@alt = '" + course + "']");//Select the course
            new SelectCourse().selectChapterByIndex(3);//Select a 2nd Chapter
            new Click().clickByclassname("collection-assessment-name");
            Thread.sleep(5000);
            new Questions().clickOnLatQuestionInQuestionPage();//function Call to go to the last Question & Click on it in a question Page
            Thread.sleep(5000);
            actual_authorName = new Questions().getQuestionsAuthorName();//Get the author Name from the existing Question in the edit mode
            actual_creationDate = new Questions().getQuestionsCreationDate();//Get the Creation Date from the existing Question in the edit mode
            Assert.assertEquals(actual_authorName, "Spaces, learning", "The Author Name is not same after copying question to other course without reference in the copied Question");
            Assert.assertEquals(actual_creationDate, new Questions().getTodaysDateAsperFormat(), "The Creation Date is not same after copying question to other course without reference in the copied Question");


            //Row No - 47 : "6. Select a question 7. Copy the question with reference to other course"
            //Expected : Question should keep on displaying original author and creation date
            new Click().clickByid("header-change-course");// Click on the 'Change' link
            Thread.sleep(2000);
            new SelectCourse().selectcourse();//Select Course 'Biology'
            new Questions().navigateToQuestionPage(44);//navigates to question Page
            new Questions().clickingEditButtoninQuestionTofetchCreationDateAndAuthorName();
            expected_authorName = new Questions().getQuestionsAuthorName();//Get the author Name from the existing Question in the edit mode
            expected_creationDate = new Questions().getQuestionsCreationDate();//Get the Creation Date from the existing Question in the edit mode
            driver.navigate().back();// Navigated Browser back to history for one page
            new Questions().copySecondQuestionWithReferenceToOtherCourse(44);//Function call to copy the second question With reference
            //new Questions().clickingEditButtoninQuestionTofetchCreationDateAndAuthorName();
            new Questions().clickingEditButtoninQuestionTofetchCreationDateAndAuthorName(44);
            actual_authorName = new Questions().getQuestionsAuthorName();//Get the author Name from the existing Question in the edit mode
            actual_creationDate = new Questions().getQuestionsCreationDate();//Get the Creation Date from the existing Question in the edit mode
            driver.navigate().back();// Navigated Browser back to history for one page
            Assert.assertEquals(actual_authorName, expected_authorName, "The Author Name is not same after copying question to other course with reference in the original Question");
            Assert.assertEquals(actual_creationDate, expected_creationDate, "The Creation Date is not same after copying question to other course with reference in the original Question");


            //Row No - 48 : "8. Go to the other course 9. Open the copied question"
            //Expected 1: Question should keep on displaying original author
            //Expected 2 : Question should keep on displaying original creation date
            new Click().clickByid("header-change-course");// Click on the 'Change' link
            Thread.sleep(2000);
            new Click().clickBycssselector("img[alt=\"" + Config.lscourse + "\"]");// Select the 'Geography' Course
            new SelectCourse().selectChapterByIndex(3);//Select a 2nd Chapter
            new Click().clickByclassname("collection-assessment-name");
            new Questions().clickOnLatQuestionInQuestionPage();//function Call to go to the last Question & Click on it in a question Page
            actual_authorName = new Questions().getQuestionsAuthorName();//Get the author Name from the existing Question in the edit mode
            actual_creationDate = new Questions().getQuestionsCreationDate();//Get the Creation Date from the existing Question in the edit mode
            Assert.assertEquals(actual_authorName, expected_authorName, "The Author Name is not same after copying question to other course with reference in the copied Question");
            Assert.assertEquals(actual_creationDate, expected_creationDate, "The Creation Date is not same after copying question to other course with reference in the copied Question");


            //Row No - 50 : "10. Select a question 11. Copy the question without reference to other chapter of same course 12. Open the original question."
            //Expected - Question should keep on displaying original author and creation date
            new Click().clickByid("header-change-course");// Click on the 'Change' link
            Thread.sleep(2000);
            new SelectCourse().selectcourse();//Select Course 'Biology'
            new Questions().navigateToQuestionPage(44);//navigates to question Page
            new Questions().clickingEditButtoninQuestionTofetchCreationDateAndAuthorName();
            expected_authorName = new Questions().getQuestionsAuthorName();//Get the author Name from the existing Question in the edit mode
            expected_creationDate = new Questions().getQuestionsCreationDate();//Get the Creation Date from the existing Question in the edit mode
            driver.navigate().back();// Navigated Browser back to history for one page
            new Questions().copySecondQuestionWithOutReferenceToOtherChapterOfSameCourse("44");//Function call to copy the second question With reference
            //new Questions().clickingEditButtoninQuestionTofetchCreationDateAndAuthorName();
            new Questions().clickingEditButtoninQuestionTofetchCreationDateAndAuthorName(44);
            actual_authorName = new Questions().getQuestionsAuthorName();//Get the author Name from the existing Question in the edit mode
            actual_creationDate = new Questions().getQuestionsCreationDate();//Get the Creation Date from the existing Question in the edit mode
            driver.navigate().back();// Navigated Browser back to history for one page
            Assert.assertEquals(actual_authorName, expected_authorName, "The Author Name is not same after copying question to the same course without reference to the other chapter in the original Question");
            Assert.assertEquals(actual_creationDate, expected_creationDate, "The Creation Date is not same after copying question to the same course without reference to the other chapter in the original Question");


            //Row No - 51 : 13. Open copied question
            //Expected 1 - User who performed copy action should be displayed as author
            //Expected 2 - Creation date should be the date of copy operation
            new Click().clickByid("header-change-course");// Click on the 'Change' link
            Thread.sleep(2000);
            new Click().clickBycssselector("img[alt=\"" + Config.course + "\"]");// Select the 'Biology' Course
            new SelectCourse().selectChapterByIndex(1);//Select a 2nd Chapter
            new Click().clickByclassname("collection-assessment-name");
            new Questions().clickOnLatQuestionInQuestionPage();//function Call to go to the last Question & Click on it in a question Page
            actual_authorName = new Questions().getQuestionsAuthorName();//Get the author Name from the existing Question in the edit mode
            actual_creationDate = new Questions().getQuestionsCreationDate();//Get the Creation Date from the existing Question in the edit mode
            Assert.assertEquals(actual_authorName, "Spaces, learning", "The Author Name is not same after copying question to other course with reference in the copied Question");
            Assert.assertEquals(actual_creationDate, new Questions().getTodaysDateAsperFormat(), "The Creation Date is not same after copying question to other course with reference in the copied Question");


            //Row N0 - 53 : "14. Select a question 15. Move the question to other course 16. Go to other course 17. Open the moved question"
            //Expected 1- User who performed move action should be displayed as author
            //Expected 2 - Creation date should be the date of move operation
            new Click().clickByid("header-change-course");// Click on the 'Change' link
            Thread.sleep(2000);
            new Assignment().create(53);
            new Questions().navigateToQuestionPage(53);
            new Questions().moveSecondQuestionToOtherCourse(53);
            new Click().clickByid("header-change-course");// Click on the 'Change' link
            Thread.sleep(2000);
            new Click().clickBycssselector("img[alt=\"" + Config.lscourse + "\"]");// Select the 'Geography' Course
            new SelectCourse().selectChapterByIndex(3);//Select a 2nd Chapter
            new Click().clickByclassname("collection-assessment-name");
            new Questions().clickOnLatQuestionInQuestionPage();//function Call to go to the last Question & Click on it in a question Page
            actual_authorName = new Questions().getQuestionsAuthorName();//Get the author Name from the existing Question in the edit mode
            actual_creationDate = new Questions().getQuestionsCreationDate();//Get the Creation Date from the existing Question in the edit mode
            Assert.assertEquals(actual_authorName, "Spaces, learning", "The Author Name is not same after Moving question to other course in the Moved Question");
            Assert.assertEquals(actual_creationDate, new Questions().getTodaysDateAsperFormat(), "The Creation Date is not same after Moving question to other course in the Moved Question");


            //Row - 55 : "18. Select a question 19. Move the question to other chapter of same course 20. Open the moved question
            //Expected 1 - User who performed move action should be displayed as author
            //Expected 2 - Creation date should be the date of move operation
            new Click().clickByid("header-change-course");// Click on the 'Change' link
            Thread.sleep(2000);
            new Assignment().create(55);
            new Questions().navigateToQuestionPage(55);
            new Questions().moveSecondQuestionToOtherChapterOfSameCourse("55");
            new Click().clickByid("header-change-course");// Click on the 'Change' link
            Thread.sleep(2000);
            new Click().clickBycssselector("img[alt=\"" + Config.course + "\"]");// Select the 'Geography' Course
            new SelectCourse().selectChapterByIndex(1);//Select a 2nd Chapter
            new Click().clickByclassname("collection-assessment-name");
            new Questions().clickOnLatQuestionInQuestionPage();//function Call to go to the last Question & Click on it in a qsuestion Page
            new UIElement().waitAndFindElement(By.id("content-authorName"));
            actual_authorName = new Questions().getQuestionsAuthorName();//Get the author Name from the existing Question in the edit mode
            actual_creationDate = new Questions().getQuestionsCreationDate();//Get the Creation Date from the existing Question in the edit mode
            Assert.assertEquals(actual_authorName, "Spaces, learning", "The Author Name is not same after Moving question to other Chapter Of Same Course in the Moved Question");
            Assert.assertEquals(actual_creationDate, new Questions().getTodaysDateAsperFormat(), "The Creation Date is not same after Moving question to other Chapter Of Same Course in the Moved Question");

        } catch (Exception e) {
            Assert.fail("Exception in Testcase checkAuthorAndCreationDateForCopyorMoveQuestion in class CapturingAuthorAndCreationDateForQues.", e);
        }
    }


    @Test(priority = 4, enabled = true)
    public void checkVisibilityOfAuthorAndCreationDate() {
        try {
            String actual = null;
            String expected = null;

            //Row No - 59 : "1. Login as CMS user1 2. Create a question 3. Login as CMS user2 4. Open the question created by user 1"
            //Expected : Author and creation date should be visible
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(59));
            //Driver.startDriver();
            new Assignment().create(59);//Create a Question from user1 'lspaces2'
            String userName = "wiley1";
            DBConnect.Connect();
            DBConnect.st.executeUpdate("update t_user set password = '6ab14d3be1858b6e8f9f223b6980c7a6dc087a1d4871255f7fc366535762f2a90213d242f2eaa0ab' where username = '" + userName + "'");
            new DirectLogin().directLoginWithCreditial("wiley1", "snapwiz", 59);//Create a Question from user2 'wiley1'
            new Click().clickbylistxpath("//a[contains(@id,'sbSelector_')]", 2);//Click on 'All Course Types'
            new Click().clickbylinkText("Learning Space + Adaptive Component");// Click on 'Learning Space + Adaptive Component'
            new Click().clickbyxpath("//img[@title = '" + Config.course + "']");// Click on 'Biology Course'
            Thread.sleep(2000);
            new SelectCourse().selectChapterByIndex(0);//Select a 1st Chapter
            Thread.sleep(2000);
            //WebElement we=driver.findElement(By.xpath("//div[@title = '"+assessmentName+"']"));
            List<WebElement> assessmentElementsList = driver.findElements(By.xpath("//div[@title = '" + assessmentName + "']"));
            WebElement we = assessmentElementsList.get(assessmentElementsList.size() - 1);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", we);
            Thread.sleep(2000);
            //new Questions().clickOnLatQuestionInQuestionPage();//function call
            actual = new TextFetch().textfetchbyxpath("//div[@class='fieldrow' and @key = 'authorName']");
            expected = "Author:\n" + "Spaces, learning";
            Assert.assertEquals(actual, expected, "The Author name is not displaying as per the format with the label 'Author'");
            String dateText = new TextFetch().textfetchbyxpath("//div[@class='fieldrow' and @key = 'creationDate']");
            System.out.println("dateText : " + dateText);
            String[] creationDateTones = dateText.split("[|]");
            actual = creationDateTones[0].trim();
            expected = "Creation Date:\n" + new Questions().getTodaysDateAsperFormat();
            Assert.assertEquals(actual, expected, "The Creation data is not displaying as per the format with the label 'Creation Date'");

        } catch (Exception e) {
            Assert.fail("Exception in Testcase checkVisibilityOfAuthorAndCreationDate in class CapturingAuthorAndCreationDateForQues.", e);

        }
    }


    @Test(priority = 5, enabled = true)
    public void updatingExistingQuestion() {
        try {
            String expected_authorName = null;
            String expected_creationDate = null;
            String actual_authorName = null;
            String actual_creationDate = null;

            //Row No - 60 : "1. Login as CMS user1 2. Create a question  3. Leave the question in draft state 4. Login as CMS user2
            //5. Go to the question page. 6. Save modifications to question"
            // Expected - Author and creation date should not get updated and remain as original
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(60));
            new Assignment().create(60);//Create a Question from user1 'lspaces2'
            new Assignment().addQuestions(60, "truefalse", "");
            expected_creationDate = "Creation Date:\n" + new Questions().getQuestionsCreationDate();
            expected_authorName = "Author:\n" + new Questions().getQuestionsAuthorName();
            String userName = "wiley1";
            DBConnect.Connect();
            DBConnect.st.executeUpdate("update t_user set password = '6ab14d3be1858b6e8f9f223b6980c7a6dc087a1d4871255f7fc366535762f2a90213d242f2eaa0ab' where username = '" + userName + "'");
            new DirectLogin().directLoginWithCreditial("wiley1", "snapwiz", 60);//Create a Question from user2 'wiley1'
            new Click().clickbylistxpath("//a[contains(@id,'sbSelector_')]", 2);//Click on 'All Course Types'
            new Click().clickbylinkText("Learning Space + Adaptive Component");// Click on 'Learning Space + Adaptive Component'
            new Click().clickbyxpath("//img[@title = '" + Config.course + "']");// Click on 'Biology Course'
            Thread.sleep(2000);
            new SelectCourse().selectChapterByIndex(0);//Select a 1st Chapter
            Thread.sleep(2000);
            // WebElement we=driver.findElement(By.xpath("//div[@title = '"+assessmentName+"']"));
            List<WebElement> assessmentElementsList = driver.findElements(By.xpath("//div[@title = '" + assessmentName + "']"));
            WebElement we = assessmentElementsList.get(assessmentElementsList.size() - 1);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", we);
            Thread.sleep(2000);
            new SelectCourse().selectassignment(assessmentName);//Select a created assignment
            Thread.sleep(2000);
            //new Questions().clickOnLatQuestionInQuestionPage();//function call
            new Click().clickByid("saveQuestionDetails1");
            actual_authorName = new TextFetch().textfetchbyxpath("//div[@class='fieldrow' and @key = 'authorName']");
            Assert.assertEquals(actual_authorName, expected_authorName, "The Author name is not displaying as per the format with the label 'Author'");
            String dateText = new TextFetch().textfetchbyxpath("//div[@class='fieldrow' and @key = 'creationDate']");
            String[] creationDateTones = dateText.split("[|]");
            actual_creationDate = creationDateTones[0].trim();
            Assert.assertEquals(actual_creationDate, expected_creationDate, "The Creation data is not displaying as per the format with the label 'Creation Date'");
        } catch (Exception e) {
            Assert.fail("Exception in Testcase updatingExistingQuestion in class CapturingAuthorAndCreationDateForQues.", e);
        }
    }


    private void checkAuthorNameAndCreationDateDisplay(String authorFailureMessage, String creationDateFailureMessage) {
        try {
            String actual_authorName = new Questions().getQuestionsAuthorName();
            String actual_creationDate = new Questions().getQuestionsCreationDate();
            String expected_authorName = "Spaces, learning";
            String expected_creationDate = new Questions().getTodaysDateAsperFormat();
            Assert.assertEquals(actual_authorName, expected_authorName, authorFailureMessage);
            Assert.assertEquals(actual_creationDate, expected_creationDate, creationDateFailureMessage);
        } catch (Exception e) {
            Assert.fail("Exception in Method checkAuthorNameAndCreationDateDisplay in class CapturingAuthorAndCreationDateForQues.", e);
        }
    }

    @AfterMethod
    public void TearDown() throws Exception {
        //driver.quit();
    }
}