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
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/*
 * Created by Dharaneesh TGowda on 17-Oct-14.
 */

public class CapturingAuthorAndCreationDateForQuestionCreated_SubjectMatterExpert extends Driver {

    @Test(priority=1,enabled = true)
    public void captureAuthorAndCreationDateForQues() {
        String actual = null;
        String expected = null;
        String authorFailureMessge = null;
        String creationDateFailureMessage = null;
        String authorName = null;
        try {

            //Row No - 120 : "1. Login as CMS user.2. Select a course.3. Select a chapter.4. Select an assessment
            //5. Click on new option to add question 6. Select a question type 7. Fill in question details 8. Click on save.

            ArrayList<String> dbInfoList = new DirectLogin().loginWithDifferentRole(Config.courseid,"ROLE_SUBJECT_MATTER_EXPERT",176,"1");
            authorName = dbInfoList.get(3) + ", " + dbInfoList.get(2);
            new Assignment().create(120);
            String questiontText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(120));
            expected  = questiontText;
            new TextSend().textsendbyid(questiontText,"question-raw-content");//Type a Questiony
            actual = new TextFetch().textfetchbyid("question-raw-content");
            Assert.assertEquals(actual,expected,"The text box is not editable in Quesion Creation");


            //Expected  - Name of the author who has created this particular question should be displayed with label "Author:"
            actual = new TextFetch().textfetchbyxpath("//div[@class='fieldrow' and @key = 'authorName']");
            expected = "Author:\n" + authorName;
            Assert.assertEquals(actual,expected,"The Author name is not displaying as per the format with the label 'Author'");


            //Expected - The Date and time when the question was created should be displayed with label "Creation Date:"
            String[] creationDateTones = new TextFetch().textfetchbyxpath("//div[@class='fieldrow' and @key = 'creationDate']").split("[|]");
            actual = creationDateTones[0].trim();
            expected = "Creation Date:\n" + new Questions().getTodaysDateAsperFormat();
            Assert.assertEquals(actual,expected,"The Creation data is not displaying as per the format with the label 'Creation Date'");


            //Expected - Author and creation date should be displayed
            authorFailureMessge = "Author Name is not displaying in Question Creation";
            creationDateFailureMessage = "Creation Date is not displaying in Question Creation";
            checkAuthorNameAndCreationDateDisplay(authorFailureMessge,creationDateFailureMessage,authorName);


            //Row No - 128 : Create a True False question
            //Expected - Author and creation date should be displayed
            authorFailureMessge = "Author Name is not displaying in True false Question Creation";
            creationDateFailureMessage = "Creation Date is not displaying in True false Question Creation";
            new Assignment().addQuestions(120,"truefalse","");
            checkAuthorNameAndCreationDateDisplay(authorFailureMessge,creationDateFailureMessage,authorName);

            //Row No - 130 : Create a Multiple Selection question
            //Expected - Author and creation date should be displayed
            authorFailureMessge = "Author Name is not displaying in Multiple Selection Question Creation";
            creationDateFailureMessage = "Creation Date is not displaying in Multiple Selection Question Creation";
            new Assignment().addQuestions(120,"multipleselection","");
            checkAuthorNameAndCreationDateDisplay(authorFailureMessge,creationDateFailureMessage,authorName);

            //Row No - 131   : Create a Text Entry question
            //Expected - Author and creation date should be displayed
            authorFailureMessge = "Author Name is not displaying in Text Entry Question Creation";
            creationDateFailureMessage = "Creation Date is not displaying in Text Entry Question Creation";
            new Assignment().addQuestions(120,"textentry","");
            checkAuthorNameAndCreationDateDisplay(authorFailureMessge,creationDateFailureMessage,authorName);

            //Row No - 132 : Create a Text Dropdown question
            //Expected - Author and creation date should be ;
            authorFailureMessge = "Author Name is not displaying in Text Dropdown Question Creation";
            creationDateFailureMessage = "Creation Date is not displaying in Text Dropdown Question Creation";
            new Assignment().addQuestions(120,"textdropdown","");
            checkAuthorNameAndCreationDateDisplay(authorFailureMessge,creationDateFailureMessage,authorName);

            //Row No - 134 : 16. Create a Numeric Entry with Units question
            //Expected - Author and creation date should be displayed
            authorFailureMessge = "Author Name is not displaying in Numeric Entry with Units Question Creation";
            creationDateFailureMessage = "Creation Date is not displaying in Numeric Entry with Units Question Creation";
            new Assignment().addQuestions(120,"numericentrywithunits","");
            checkAuthorNameAndCreationDateDisplay(authorFailureMessge,creationDateFailureMessage,authorName);

            //Row No -  135 : Create a Maple Numeric/Advanced Numeric question
            //Expected - Author and creation date should be displayed
            authorFailureMessge = "Author Name is not displaying in Maple Numeric/Advanced Numeric Question Creation";
            creationDateFailureMessage = "Creation Date is not displaying in Maple Numeric/Advanced Numeric Question Creation";
            new Assignment().addQuestions(120,"advancednumeric","");
            checkAuthorNameAndCreationDateDisplay(authorFailureMessge,creationDateFailureMessage,authorName);

            //Row No - 136 : Create a Maple Symbolic/Expression Evaluator question
            //Expected - Author and creation date should be displayed
            authorFailureMessge = "Author Name is not displaying in Maple Symbolic/Expression Evaluator Question Creation";
            creationDateFailureMessage = "Creation Date is not displaying in Maple Symbolic/Expression Evaluator Question Creation";
            new Assignment().addQuestions(120,"expressionevaluator","");
            checkAuthorNameAndCreationDateDisplay(authorFailureMessge,creationDateFailureMessage,authorName);

            //Row No - 139 : 21. Create a Essay type question
            //Expected - Author and creation date should be displayed
            authorFailureMessge = "Author Name is not displaying in Essay type Question Creation";
            creationDateFailureMessage = "Creation Date is not displaying in Essay type Evaluator Question Creation";
            new Assignment().addQuestions(120,"essay","");
            checkAuthorNameAndCreationDateDisplay(authorFailureMessge,creationDateFailureMessage,authorName);

            //Row No - 142 : Create a Writeboard question
            //Expected - Author and creation date should be displayed
            authorFailureMessge = "Author Name is not displaying in Writeboard Question Creation";
            creationDateFailureMessage = "Creation Date is not displaying in Writeboard Question Creation";
            new Assignment().addQuestions(120,"writeboard","");
            checkAuthorNameAndCreationDateDisplay(authorFailureMessge,creationDateFailureMessage,authorName);

        } catch (Exception e) {
            Assert.fail("Exception in testcase captureAuthorAndCreationDateForQues in class CapturingAuthorAndCreationDateForQuestionCreated_SubjectMatterExpert.",e);
        }
    }



    @Test(priority=2,enabled = true)
    public void checkAuthorAndCreationDateForDuplicateQuestion(){
        String actual;
        String expected;
        try{
            //Row No - 158 : "1. Login as CMS user. 2. Go to a question page 3. Create duplicate question 4. Verify duplicate question"
            //Expected 1 - User who performed duplication action should be displayed as author
            //Expected 2 - Creation date should be the date of duplication
            ArrayList<String> dbInfoList = new DirectLogin().loginWithDifferentRole(Config.courseid,"ROLE_SUBJECT_MATTER_EXPERT",176,"1");
            new Click().clickbylistxpath("//a[contains(@id,'sbSelector_')]",2);//Click on 'All Course Types'
            new Click().clickbylinkText("Learning Space + Adaptive Component");// Click on 'Learning Space + Adaptive Component'
            new Questions().duplicateQuestion(158);
            new Click().clickbyxpath("//div[text() = 'Duplicate']");
            Thread.sleep(2000);
            new Click().clickByid("saveQuestionDetails1");
            actual = new Questions().getQuestionsAuthorName();
            expected = dbInfoList.get(3) + ", " + dbInfoList.get(2);
            Assert.assertEquals(actual,expected);
            actual = new Questions().getQuestionsCreationDate();
            expected = new Questions().getTodaysDateAsperFormat();
            Assert.assertEquals(actual,expected);

        }catch(Exception e){
            Assert.fail("Exception in testcase createAuthorAndDateForDuplicateQuestion in class CapturingAuthorAndCreationDateForQuestionCreated_SubjectMatterExpert",e);
        }
    }



    @Test(priority=3,enabled = true)
    public void checkAuthorAndCreationDateForCopyorMoveQuestion1(){
        String expected_authorName = null;
        String expected_creationDate = null;
        String actual_authorName = null;
        String actual_creationDate = null;
        try {


            //Row No - 160 : "1. Login as CMS user. 2. Select a question 3. Copy the question without reference to other course"
            //Expected : Question should keep on displaying original author and creation date

            ArrayList<String> dbInfoList = new DirectLogin().loginWithDifferentRole(Config.courseid,"ROLE_SUBJECT_MATTER_EXPERT",176,"1");
            new Assignment().create(160);
            new Questions().navigateToQuestionPage(160);//navigates to question Page
            new Questions().clickingEditButtoninQuestionTofetchCreationDateAndAuthorName();
            expected_authorName = new Questions().getQuestionsAuthorName();//Get the author Name from the existing Question in the edit mode
            expected_creationDate = new Questions().getQuestionsCreationDate();//Get the Creation Date from the existing Question in the edit mode
            driver.navigate().back();// Navigated Browser back to history for one page
            new Questions().copySecondQuestionWithOutReferenceToOtherCourse(160);//Function call to copy the second question With reference
            //new Questions().clickingEditButtoninQuestionTofetchCreationDateAndAuthorName();
            new Questions().clickingEditButtoninQuestionTofetchCreationDateAndAuthorName(160);
            actual_authorName = new Questions().getQuestionsAuthorName();//Get the author Name from the existing Question in the edit mode
            actual_creationDate = new Questions().getQuestionsCreationDate();//Get the Creation Date from the existing Question in the edit mode
            driver.navigate().back();// Navigated Browser back to history for one page
            Assert.assertEquals(actual_authorName,expected_authorName,"The Author Name is not same after copying question to other course without reference in the original Question");
            Assert.assertEquals(actual_creationDate,expected_creationDate,"The Creation Date is not same after copying question to other course without reference in the original Question");


            //Row No - 161 : "4. Go to the other course 5. Open the copied question"
            //Expected 1 : User who performed copy action should be displayed as author
            //Expected 2 : Creation date should be the date of copy operation
            new Click().clickByid("header-change-course");// Click on the 'Change' link
            String course  = Config.lscourse;
            new Click().clickbyxpath("//img[@alt = '"+course+"']");//Select the course
            new SelectCourse().selectChapterByIndex(3);//Select a 2nd Chapter
            new Click().clickByclassname("collection-assessment-name");
            new Questions().clickOnLatQuestionInQuestionPage();//function Call to go to the last Question & Click on it in a question Page
            Thread.sleep(2000);
            actual_authorName = new Questions().getQuestionsAuthorName();//Get the author Name from the existing Question in the edit mode
            actual_creationDate = new Questions().getQuestionsCreationDate();//Get the Creation Date from the existing Question in the edit mode
            expected_authorName = dbInfoList.get(3) + ", " + dbInfoList.get(2);
            System.out.println("expected_authorName(FNAME +LNAME) : " + expected_authorName);
            Assert.assertEquals(actual_authorName,expected_authorName,"The Author Name is not same after copying question to other course without reference in the copied Question");
            Assert.assertEquals(actual_creationDate,new Questions().getTodaysDateAsperFormat(),"The Creation Date is not same after copying question to other course without reference in the copied Question");



            //Row No - 163 : "6. Select a question 7. Copy the question with reference to other course"
            //Expected : Question should keep on displaying original author and creation date
            new Click().clickByid("header-change-course");// Click on the 'Change' link
            new Assignment().create(160);
            new Questions().navigateToQuestionPage(160);//navigates to question Page
            new Questions().clickingEditButtoninQuestionTofetchCreationDateAndAuthorName();
            expected_authorName = new Questions().getQuestionsAuthorName();//Get the author Name from the existing Question in the edit mode
            expected_creationDate = new Questions().getQuestionsCreationDate();//Get the Creation Date from the existing Question in the edit mode
            driver.navigate().back();// Navigated Browser back to history for one page
            new Questions().copySecondQuestionWithReferenceToOtherCourse(160);//Function call to copy the second question With reference
            //new Questions().clickingEditButtoninQuestionTofetchCreationDateAndAuthorName();
            new Questions().clickingEditButtoninQuestionTofetchCreationDateAndAuthorName(160);
            actual_authorName = new Questions().getQuestionsAuthorName();//Get the author Name from the existing Question in the edit mode
            actual_creationDate = new Questions().getQuestionsCreationDate();//Get the Creation Date from the existing Question in the edit mode
            driver.navigate().back();// Navigated Browser back to history for one page
            Assert.assertEquals(actual_authorName,expected_authorName,"The Author Name is not same after copying question to other course with reference in the original Question");
            Assert.assertEquals(actual_creationDate,expected_creationDate,"The Creation Date is not same after copying question to other course with reference in the original Question");



            //Row No - 164 : "8. Go to the other course 9. Open the copied question"
            //Expected 1: Question should keep on displaying original author
            //Expected 2 : Question should keep on displaying original creation date
            new Click().clickByid("header-change-course");// Click on the 'Change' link
            new Click().clickBycssselector("img[alt=\""+Config.lscourse+"\"]");// Select the 'Geography' Course
            new SelectCourse().selectChapterByIndex(3);//Select a 2nd Chapter
            new Click().clickByclassname("collection-assessment-name");
            new Questions().clickOnLatQuestionInQuestionPage();//function Call to go to the last Question & Click on it in a question Page
            Thread.sleep(2000);
            actual_authorName = new Questions().getQuestionsAuthorName();//Get the author Name from the existing Question in the edit mode
            actual_creationDate = new Questions().getQuestionsCreationDate();//Get the Creation Date from the existing Question in the edit mode
            Assert.assertEquals(actual_authorName,expected_authorName,"The Author Name is not same after copying question to other course with reference in the copied Question");
            Assert.assertEquals(actual_creationDate,expected_creationDate,"The Creation Date is not same after copying question to other course with reference in the copied Question");



            //Row No - 166 : "10. Select a question 11. Copy the question without reference to other chapter of same course 12. Open the original question."
            //Expected - Question should keep on displaying original author and creation date
            new Click().clickByid("header-change-course");// Click on the 'Change' link*/
            new Assignment().create(160);
            new Questions().navigateToQuestionPage(160);//navigates to question Page
            new Questions().clickingEditButtoninQuestionTofetchCreationDateAndAuthorName();
            expected_authorName = new Questions().getQuestionsAuthorName();//Get the author Name from the existing Question in the edit mode
            expected_creationDate = new Questions().getQuestionsCreationDate();//Get the Creation Date from the existing Question in the edit mode
            driver.navigate().back();// Navigated Browser back to history for one page
            new Questions().copySecondQuestionWithOutReferenceToOtherChapterOfSameCourse("160");//Function call to copy the second question With reference
            //new Questions().clickingEditButtoninQuestionTofetchCreationDateAndAuthorName();
            new Questions().clickingEditButtoninQuestionTofetchCreationDateAndAuthorName(160);
            actual_authorName = new Questions().getQuestionsAuthorName();//Get the author Name from the existing Question in the edit mode
            actual_creationDate = new Questions().getQuestionsCreationDate();//Get the Creation Date from the existing Question in the edit mode
            driver.navigate().back();// Navigated Browser back to history for one page
            Assert.assertEquals(actual_authorName,expected_authorName,"The Author Name is not same after copying question to the same course without reference to the other chapter in the original Question");
            Assert.assertEquals(actual_creationDate,expected_creationDate,"The Creation Date is not same after copying question to the same course without reference to the other chapter in the original Question");



            //Row No - 167 : 13. Open copied question
            //Expected 1 - User who performed copy action should be displayed as author
            //Expected 2 - Creation date should be the date of copy operation
            new Click().clickByid("header-change-course");// Click on the 'Change' link
            //new Click().clickBycssselector("img[alt=\""+Config.course+"\"]");// Select the 'Biology' Course
            new Click().clickByid("253");
            new SelectCourse().selectChapterByIndex(1);//Select a 2nd Chapter
            new Click().clickByclassname("collection-assessment-name");
            new Questions().clickOnLatQuestionInQuestionPage();//function Call to go to the last Question & Click on it in a question Page
            Thread.sleep(2000);
            actual_authorName = new Questions().getQuestionsAuthorName();//Get the author Name from the existing Question in the edit mode
            actual_creationDate = new Questions().getQuestionsCreationDate();//Get the Creation Date from the existing Question in the edit mode
            expected_authorName = dbInfoList.get(3) + ", " + dbInfoList.get(2);
            Assert.assertEquals(actual_authorName,expected_authorName,"The Author Name is not same after copying question to other course with reference in the copied Question");
            Assert.assertEquals(actual_creationDate,new Questions().getTodaysDateAsperFormat(),"The Creation Date is not same after copying question to other course with reference in the copied Question");


            //Row N0 - 169 : "14. Select a question 15. Move the question to other course 16. Go to other course 17. Open the moved question"
            //Expected 1- User who performed move action should be displayed as author
            //Expected 2 - Creation date should be the date of move operation
            new Click().clickByid("header-change-course");// Click on the 'Change' link
            new Assignment().create(169);
            new Questions().navigateToQuestionPage(169);
            if(driver.findElements(By.xpath(".//*[@id='content-search-move-btn']//img")).size()!=0){
                Assert.fail("'Move' button should not be visible for Subject Matter Expert");
            }


        }catch(Exception e){
            Assert.fail("Exception in Testcase checkAuthorAndCreationDateForCopyorMoveQuestion in class CapturingAuthorAndCreationDateForQuestionCreated_SubjectMatterExpert.",e);
        }
    }


    @Test(priority=4,enabled = true)
    public void checkVisibilityOfAuthorAndCreationDate(){
        try {
            String actual = null;
            String expected = null;
            String authorName = null;
            //Row No - 175 : "1. Login as CMS user1 2. Create a question 3. Login as CMS user2 4. Open the question created by user 1"
            //Expected : Author and creation date should be visible
            //Driver.startDriver();
            ArrayList<String> dbInfoList = new DirectLogin().loginWithDifferentRole(Config.courseid,"ROLE_SUBJECT_MATTER_EXPERT",176,"1");
            authorName = dbInfoList.get(3) + ", " + dbInfoList.get(2);
            new Assignment().create(175);//Create a Question from user1 'lspaces2'
            new DirectLogin().loginWithDifferentRole(Config.courseid,"ROLE_SUBJECT_MATTER_EXPERT",176,"2");//Create a Question from user2 'wiley1'
            new Click().clickbylistxpath("//a[contains(@id,'sbSelector_')]", 2);//Click on 'All Course Types'
            new Click().clickbylinkText("Learning Space + Adaptive Component");// Click on 'Learning Space + Adaptive Component'
            new Click().clickbyxpath("//img[@title = '"+Config.course+"']");// Click on 'Biology Course'
            Thread.sleep(2000);
            new SelectCourse().selectChapterByIndex(0);//Select a 1st Chapter
            Thread.sleep(2000);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(175));
            List<WebElement> assessmentElementsList = driver.findElements(By.xpath("//div[@title = '"+assessmentName+"']"));
            WebElement we =  assessmentElementsList.get(assessmentElementsList.size()-1);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",we);
            Thread.sleep(2000);
            //new Questions().clickOnLatQuestionInQuestionPage();//function call
            actual = new TextFetch().textfetchbyxpath("//div[@class='fieldrow' and @key = 'authorName']");
            expected = "Author:\n" + authorName;
            Assert.assertEquals(actual, expected, "The Author name is not displaying as per the format with the label 'Author'");
            String dateText = new TextFetch().textfetchbyxpath("//div[@class='fieldrow' and @key = 'creationDate']");
            System.out.println("dateText : " +dateText);
            String[] creationDateTones = dateText.split("[|]");
            actual = creationDateTones[0].trim();
            expected = "Creation Date:\n" + new Questions().getTodaysDateAsperFormat();
            Assert.assertEquals(actual,expected,"The Creation data is not displaying as per the format with the label 'Creation Date'");

        }catch(Exception e){
            Assert.fail("Exception in Testcase checkVisibilityOfAuthorAndCreationDate in class CapturingAuthorAndCreationDateForQuestionCreated_SubjectMatterExpert.",e);
        }
    }


    @Test(priority=5,enabled = true)
    public void updatingExistingQuestion(){
        try{
            String expected_authorName = null;
            String expected_creationDate = null;
            String actual_authorName = null;
            String actual_creationDate = null;
            String authorName = null;

            //Row No - 176 : "1. Login as CMS user1 2. Create a question  3. Leave the question in draft state 4. Login as CMS user2
            //5. Go to the question page. 6. Save modifications to question"
            // Expected - Author and creation date should not get updated and remain as original

            ArrayList<String> dbInfoList = new DirectLogin().loginWithDifferentRole(Config.courseid,"ROLE_SUBJECT_MATTER_EXPERT",176,"1");
            authorName = dbInfoList.get(3) + ", " + dbInfoList.get(2);
            new Assignment().create(176);//Create a Question from user1 'lspaces2'
            new Assignment().addQuestions(176,"truefalse","");
            expected_creationDate = "Creation Date:\n" + new Questions().getTodaysDateAsperFormat();
            expected_authorName = "Author:\n" +authorName;
            new DirectLogin().loginWithDifferentRole(Config.courseid,"ROLE_SUBJECT_MATTER_EXPERT",176,"2");
            new Click().clickbylistxpath("//a[contains(@id,'sbSelector_')]",2);//Click on 'All Course Types'
            new Click().clickbylinkText("Learning Space + Adaptive Component");// Click on 'Learning Space + Adaptive Component'
            new Click().clickbyxpath("//img[@title = '"+Config.course+"']");// Click on 'Biology Course'
            Thread.sleep(2000);
            new SelectCourse().selectChapterByIndex(0);//Select a 1st Chapter
            Thread.sleep(2000);
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(176));
            List<WebElement> assessmentElementsList = driver.findElements(By.xpath("//div[@title = '"+assessmentName+"']"));
            WebElement we =  assessmentElementsList.get(assessmentElementsList.size()-1);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",we);
            Thread.sleep(2000);
            new SelectCourse().selectassignment(assessmentName);//Select a created assignment
            Thread.sleep(7000);
            new Questions().clickOnLatQuestionInQuestionPage();//function call
            new UIElement().waitAndFindElement(By.id("saveQuestionDetails1"));
            new Click().clickByid("saveQuestionDetails1");
            actual_authorName = new TextFetch().textfetchbyxpath("//div[@class='fieldrow' and @key = 'authorName']");
            Assert.assertEquals(actual_authorName,expected_authorName,"The Author name is not displaying as per the format with the label 'Author'");
            String dateText= new TextFetch().textfetchbyxpath("//div[@class='fieldrow' and @key = 'creationDate']");
            String[] creationDateTones = dateText.split("[|]");
            actual_creationDate = creationDateTones[0].trim();
            Assert.assertEquals(actual_creationDate,expected_creationDate,"The Creation data is not displaying as per the format with the label 'Creation Date'");
        }catch(Exception e){
            Assert.fail("Exception in Testcase updatingExistingQuestion in class CapturingAuthorAndCreationDateForQuestionCreated_SubjectMatterExpert.",e);
        }
    }



    private void checkAuthorNameAndCreationDateDisplay(String authorFailureMessage,String creationDateFailureMessage,String author) {
        try {
            String actual_authorName =  new Questions().getQuestionsAuthorName().trim();
            String actual_creationDate =  new Questions().getQuestionsCreationDate().trim();
            String expected_authorName =  author;
            String expected_creationDate =  new Questions().getTodaysDateAsperFormat();
            Assert.assertEquals(actual_authorName,expected_authorName,authorFailureMessage);
            Assert.assertEquals(actual_creationDate,expected_creationDate,creationDateFailureMessage);
        } catch (Exception e) {
            Assert.fail("Exception in Method checkAuthorNameAndCreationDateDisplay in class CapturingAuthorAndCreationDateForQuestionCreated_SubjectMatterExpert.",e);
        }
    }

}