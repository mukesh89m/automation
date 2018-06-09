package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT17.R1714;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/*
 * Created by sumit on 1/12/14.
 */
public class MultiPartQuestionForStaticAssessment extends Driver {

    @Test(priority = 1, enabled = true)
    public void multiPartQuestionForStaticAssessment()
    {
        try
        {
            //Driver.startDriver();
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse(); //select a course
            new SelectCourse().selectChapterByIndex(0);//select chapter
            driver.findElement(By.cssSelector("div.create-practice")).click();//click on Create Practice
            Thread.sleep(3000);
            new Click().clickByclassname("create-regular-assessment-popup-item");
            Thread.sleep(3000);
            //TC row no. 3..."Multi Part" Question type should be present in the Create Question page
            new Click().clickByid("qtn-multi-part");//click on Multi-part question
            new ComboBox().selectValue(3, "Static Practice");
            //TC row no. 5
            /*"Following fields should be present globally for Multipart Questions:
            “Question Parts” label with “dependent” and “independent” options. "*/
            String text = new TextFetch().textfetchbyclass("cms-multipart-question-parts-text");
            Assert.assertEquals(text, "Question parts", "\"Question parts\" label is absent in multi part question.");
            String text1 = new TextFetch().textfetchbyxpath("//label[@for='one']");
            Assert.assertEquals(text1, "Dependent", "\"Dependent\" radio button is absent in multi part question.");
            String text2 = new TextFetch().textfetchbyxpath("//label[@for='two']");
            Assert.assertEquals(text2, "Independent", "\"Independent\" radio button is absent in multi part question.");
            //TC row no. 6....By default “independent” option should be selected.
            boolean isSelected = driver.findElement(By.cssSelector("input[id='two']")).isSelected();
            Assert.assertEquals(isSelected, true, "\"Independent\" radio button is not selected by default in multi part question.");
            //TC row no. 7...Question stem area should be present with deafult text "Enter the Question Stem"
            String questionStem = new TextFetch().textfetchbyclass("redactor_placeholder");
            Assert.assertEquals(questionStem, "Enter The Question Stem", "Question stem area is not present with default text \"Enter the Question Stem\".");
            //TC row no. 8..."Add new Question part >" button should be present
            String newQuestionPart = new TextFetch().textfetchbyclass("cms-multipart-add-question-part");
            Assert.assertEquals(newQuestionPart, "Add new question part", "\"Add new Question part >\" button is not present.");
            //TC row no. 9...Question status dropdown should be present with default "Draft" status
            String status = new TextFetch().textfetchbyclass("editor-draft-drop-down-wrapper");
            Assert.assertEquals(status, "Draft", "\"Draft >\" status is not present.");
            //TC row no. 10..."On the yellow footer side, following options should be present: Cancel and Save button"
            String cancel = new TextFetch().textfetchbyid("question-editor-cancel");
            Assert.assertEquals(cancel, "Cancel", "Cancel button is not present in footer of multi part question.");
            String save = new TextFetch().textfetchbyid("saveQuestionDetails1");
            Assert.assertEquals(save, "Save", "Save button is not present in footer of multi part question.");

            //TC row no. 13...
            //6. Enter Assessment name and question set name & Click on Save button
            //Expected:  Robo-notification should appear as “Add details to main stem to save multipart question”
            driver.findElement(By.id("assessmentName")).click();
            driver.findElement(By.id("assessmentName")).clear();
            driver.findElement(By.id("assessmentName")).sendKeys("Multipart Question");
            driver.findElement(By.id("questionSetName")).clear();
            driver.findElement(By.id("questionSetName")).sendKeys("Multipart Question Set");

            new Click().clickByid("saveQuestionDetails1");//click on Save
            String message = driver.findElement(By.cssSelector("div[class='cms-notification-message-body-title two-line-title']")).getText();
            Assert.assertEquals(message, "Question stem cannot be left blank. Please add question stem details.", "\"Add details to main stem to save multipart question\" message not appearing if we click on Save button without adding stem.");

            //TC row no. 14
            //7. Click on "Add new question part" button
            //Expected:  Robo-notification should appear as “Add details to main stem to save multipart question”
            new Click().clickByclassname("cms-multipart-add-question-part");//click on Add new question part
            String message1 = driver.findElement(By.cssSelector("div[class='cms-notification-message-body-title two-line-title']")).getText();
            Assert.assertEquals(message1, "Question stem cannot be left blank. Please add question stem details.", "\"Add details to main stem to save multipart question\" message not appearing if we click on Save button without adding stem.");

            //TC row no. 16
            //9. Enter the text in question stem area and click outside the question stem textbox
            //Expected: Question stem should get saved
            new TextSend().textsendbyid("multipart question", "question-mp-raw-content-0");
            new Click().clickByid("saveQuestionDetails1");//click on Save

            //TC row no. 19. 21
            //10. Click inside the question stem textbox
            //On clicking inside the question stem, author should be able to edit the question stem
            String str = new RandomString().randomstring(5);
            new TextSend().textsendbyid(str, "question-mp-raw-content-0");
            new Click().clickByid("saveQuestionDetails1");//click on Save
            String question = new TextFetch().textfetchbyid("question-mp-raw-content-0");
            Assert.assertEquals(question, str, "On clicking inside the question stem, author is unable to edit the question stem");

            //TC row no. 20
            //Delete option should not be available for first question stem
            int deleteIcon = driver.findElements(By.cssSelector("img[alt='delete question stem']")).size();
            Assert.assertEquals(deleteIcon, 0, "Delete icon is present in first question stem.");

            //TC row no. 22
            //12. Remove all the details from the question stem and click on "add new question part" button
            //Expected: Robo - notification should show as “Question stem cannot be left blank. Please add question stem details.”
            driver.findElement(By.id("question-mp-raw-content-0")).click();
            driver.findElement(By.id("question-mp-raw-content-0")).clear();
            new Click().clickByid("saveQuestionDetails1");//click on Save
            Thread.sleep(2000);
            new Click().clickByclassname("cms-multipart-add-question-part");//click on Add new question part
            String message2 = driver.findElement(By.cssSelector("div[class='cms-notification-message-body-title two-line-title']")).getText();
            Assert.assertEquals(message2, "Question stem cannot be left blank. Please add question stem details.", "\"Question stem cannot be left blank. Please add question stem details.\" message not appearing if we click on Save button without adding stem.");

            //TC row no. 23
            //13. Click on Save button
            //Expected: Question stem should get saved successfully
            new Click().clickByid("saveQuestionDetails1");//click on Save

            //TC row no 24
            //14. Select Publish from the dropdown and click on save button
            //Expected: Robo notification should appear "Please enter question details" as author tries to publish a multipart having only question stem
            new TextSend().textsendbyid(str, "question-mp-raw-content-0");
            new Click().clickbylinkText("Draft");//click on Draft option
            new Click().clickbylinkText("Publish");//click on Publish
            new Click().clickByid("saveQuestionDetails1");//click on Save
            String message3 = driver.findElement(By.cssSelector("div[class='cms-notification-message-body-title two-line-title']")).getText();
            Assert.assertEquals(message3, "Please enter question details.", "\"Please enter question details.\" si not shown when author tries to publish a multipart having only question stem." );

            //TC row no 25
            //The author should be able to click on “x” to close the robo-notification .
            new Click().clickByid("close-cms-notification-dialog");//close notification

            //TC row no 26
            //15. Edit the question stem again and Click on "Add new question part" button
            //Expected: After creation of any stem and adding data to it,if the author clicks on “Add new question part“,the complete multipart question should be saved and the author should navigate to the question part creation page .
            boolean multipartFound = false;
            new Click().clickByclassname("cms-multipart-add-question-part");//click on Add new question part
            List<WebElement> allQuestionTypes = driver.findElements(By.className("multipart-qtn-content-icon"));
            for(WebElement questionTypes : allQuestionTypes)
            {
                if(questionTypes.getAttribute("title").equals("Multi Part"))
                {
                    multipartFound = true;
                }
            }
            //TC row no 27, 29
            //Expected: Author should get all question types for Independent Question parts
            Assert.assertEquals(allQuestionTypes.get(0).getAttribute("title"), "True / False", "True / False kind of question is not found when author clicks on \"Add new question part\".");
            Assert.assertEquals(allQuestionTypes.get(1).getAttribute("title"), "Multiple Choice", "Multiple Choice kind of question is not found when author clicks on \"Add new question part\".");
            Assert.assertEquals(allQuestionTypes.get(2).getAttribute("title"), "Multiple Selection", "Multiple Selection kind of question is not found when author clicks on \"Add new question part\".");
            Assert.assertEquals(allQuestionTypes.get(3).getAttribute("title"), "Text Entry", "Text Entry kind of question is not found when author clicks on \"Add new question part\".");
            Assert.assertEquals(allQuestionTypes.get(4).getAttribute("title"), "Numeric Entry w/Units", "Numeric Entry w/Units kind of question is not found when author clicks on \"Add new question part\".");
            Assert.assertEquals(allQuestionTypes.get(5).getAttribute("title"), "Text Drop Down", "Text Drop Down kind of question is not found when author clicks on \"Add new question part\".");
            Assert.assertEquals(allQuestionTypes.get(6).getAttribute("title"), "Advanced Numeric", "Advanced Numeric kind of question is not found when author clicks on \"Add new question part\".");
            Assert.assertEquals(allQuestionTypes.get(7).getAttribute("title"), "Expression Evaluator", "Expression Evaluator kind of question is not found when author clicks on \"Add new question part\".");
            Assert.assertEquals(allQuestionTypes.get(8).getAttribute("title"), "Essay Type Question", "Essay Type Question kind of question is not found when author clicks on \"Add new question part\".");
            Assert.assertEquals(allQuestionTypes.get(9).getAttribute("title"), "Write Board", "Write Board kind of question is not found when author clicks on \"Add new question part\".");

            //TC row no 28
            //Expected: Multipart question type should not be available...
            Assert.assertEquals(multipartFound, false, "Multipart Question is found when author clicks on \"Add new question part\".");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase multiPartQuestionForStaticAssessment in class MultiPartQuestionForStaticAssessment.", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void selectTrueFalseFromMPQ()
    {
        try
        {
            //Driver.startDriver();
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse(); //select a course
            new SelectCourse().selectChapterByIndex(0);//select chapter
            driver.findElement(By.cssSelector("div.create-practice")).click();//click on Create Practice
            Thread.sleep(2000);
            new Click().clickByclassname("create-regular-assessment-popup-item");
            Thread.sleep(2000);
            new Click().clickBycssselector("div[title='Multi Part']");
            //new Click().clickByid("qtn-multi-part");//click on Multi-part question
            new ComboBox().selectValue(3, "Static Practice");
            driver.findElement(By.id("assessmentName")).click();
            driver.findElement(By.id("assessmentName")).clear();
            driver.findElement(By.id("assessmentName")).sendKeys("Multipart Question");
            driver.findElement(By.id("questionSetName")).clear();
            driver.findElement(By.id("questionSetName")).sendKeys("Multipart Question Set");
            String str = new RandomString().randomstring(5);
            new TextSend().textsendbyid(str, "question-mp-raw-content-0");
            new Click().clickByid("saveQuestionDetails1");//click on Save
            new Click().clickByclassname("cms-multipart-add-question-part");//click on Add new question part
            new Click().clickByid("qtn-type-true-false-img");//click on true false type question
            //TC row no. 32...Cancel and Save options should be present
            String cancel = new TextFetch().textfetchbyid("question-editor-cancel");
            Assert.assertEquals(cancel, "Cancel", "Cancel button is not present in footer of multi part question.");
            String save = new TextFetch().textfetchbyid("saveQuestionDetails1");
            Assert.assertEquals(save, "Save", "Save button is not present in footer of multi part question.");
            //TC row no. 33...
           /* "Following options should be present as product supports now:
                    - Solution
                    - Hint
                    - Score
                    - Same But Different Question Type Parameters
                - Tags"*/
            String solution = new TextFetch().textfetchbyxpath("//h4[contains(.,'Solution')]");
            Assert.assertEquals(solution, "Solution", "Solution is absent in true false type question in MPQ \"add new question part\" section.");
            String hint = new TextFetch().textfetchbyxpath("//h4[contains(.,'Hint')]");
            Assert.assertEquals(hint, "Hint", "Hint is absent in true false type question in MPQ \"add new question part\" section.");
            String score = new TextFetch().textfetchbyid("score");
            Assert.assertEquals(score, "Score", "Score is absent in true false type question in MPQ \"add new question part\" section.");
            String samebutdiff = new TextFetch().textfetchbyxpath("//h4[contains(.,'Same But Different Question Type Parameters:')]");
            Assert.assertEquals(samebutdiff, "Same But Different Question Type Parameters:", "\"Same But Different Question Type Parameters:\" is absent in true false type question in MPQ \"add new question part\" section.");
            String tags = new TextFetch().textfetchbyxpath("//h4[contains(.,'Tags')]");
            Assert.assertEquals(tags, "Tags", "\"Tags\" is absent in true false type question in MPQ \"add new question part\" section.");

            //TC row no. 36...
            //Yellow footer should be present with “Bloom’s Taxonomy”, “Difficulty Level”, “Learning Objectives” as the product supports for any other question
            String boolmCode = new TextFetch().textfetchbyclass("footer-bloomcode");
            Assert.assertEquals(boolmCode, "Bloom's Taxonomy", "Bloom's Taxonomy is absent in the footer of true false type question in MPQ \"add new question part\" section.");

            String difficulty = new TextFetch().textfetchbyclass("footer-difficulty-level");
            Assert.assertEquals(difficulty, "Difficulty Level", "Difficulty Level is absent in the footer of true false type question in MPQ \"add new question part\" section.");

            String learningObj = new TextFetch().textfetchbyid("learing-objectives-span");
            Assert.assertEquals(learningObj, "Learning Objectives", "Learning Objectives is absent in the footer of true false type question in MPQ \"add new question part\" section.");

            //TC row no. 37
            //Question Status dropdown should not be present so that author cannot publish the individual question part
            boolean statusFound= false;
            List<WebElement> allElements = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
            for(WebElement ele: allElements)
            {
                if(ele.getText().equals("Draft"))
                {
                    statusFound = true;
                    break;
                }
            }
            Assert.assertEquals(statusFound, false, "Learning Objectives is absent in the footer of true false type question in MPQ \"add new question part\" section.");

            //TC row no. 38
            //17. Fill in all the details of the question part including the footer details and click on Save
            //Expected: Delete icon and report content errors icons should appear once it is saved
            new TextSend().textsendbyid("1", "mpq-question-part-input-label");//enter label in true false MPQ
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("True False For MPQ");//type the question
            new Click().clickBycssselector("span.true-false-answer-label");//click on Answer Option A
            new ComboBox().selectValue(7, "Hard");
            driver.findElement(By.id("content-solution")).sendKeys("Solution Text");
            driver.findElement(By.id("content-hint")).sendKeys("Hint Text");
            new Click().clickByid("saveQuestionDetails1");//click on Save
            //TC row no. 39
            //Expected: Comment icon should not be present for question part
            boolean commentIcon = true;
            try
            {
                driver.findElement(By.id("al-question-discussion-icon-section")).click();
            }
            catch (Exception e)
            {
                commentIcon = false;
            }
            Assert.assertEquals(commentIcon, false, "discussion icon is present after we add true false type question in MPQ");
            //TC row no. 38
            new Click().clickBycssselector("img[alt='Back']");//click on Back
            String src = driver.findElement(By.cssSelector("img[alt='Delete Question']")).getAttribute("src");
            if(!src.contains("delete-question-icon.png")){
                Assert.fail("Delete icon is absent after we add true false type question in MPQ");
            }
            new Click().clickByid("saveQuestionDetails1");//click on Save
            new Click().clickByclassname("cms-multipart-question-arw");//click on question part arrow
            //TC row no. 41
            //18. Click on delete icon
            //Expected: "Robo notification should come up as :“Yes, delete the question part >>”,"Cancel"
            new Click().clickBycssselector("img[alt='Delete Question']");//click on delete question

            String deleteLink = driver.findElement(By.cssSelector("span[class='cms-notification-message-ignore-changes cms-notification-message-delete-question']")).getText();
            if(!deleteLink.contains("Yes, delete the question part"))
                Assert.fail("\"Yes, delete the question part\" is missing in delete notification of question part in MPQ.");
            String cancelLink = driver.findElement(By.cssSelector("span[class='cms-notification-message-back-to-question cms-notification-message-delete-associated-content-cancel']")).getText();
            if(!cancelLink.contains("Cancel"))
                Assert.fail("\"Cancel\" is missing in delete notification of question part in MPQ.");

            //TC row no. 42
            //19. Click on Cancel
            //On clicking Cancel, the question stem should not be deleted and the robo notification should be closed
            new Click().clickBycssselector("span[class='cms-notification-message-back-to-question cms-notification-message-delete-associated-content-cancel']");//click on cancel link on pop-up
            String question = driver.findElement(By.id("question-raw-content")).getText();
            Assert.assertEquals(question, "True False For MPQ", "On clicking Cancel link from the delete notification of question part in MPQ the question stem gets deleted.");
            boolean notificationClosed = true;
            try
            {
                driver.findElement(By.cssSelector("span[class='cms-notification-message-back-to-question cms-notification-message-delete-associated-content-cancel']"));
            }
            catch (Exception e)
            {
                notificationClosed = false;
            }
            Assert.assertEquals(notificationClosed, false, "On clicking Cancel link from the delete notification of question part in MPQ the notification does not close.");
            //TC row no. 43
            //20. Click on delete icon again and select 'yes, delete the question part' option
            //Particular question part should be deleted .
            new Click().clickBycssselector("img[alt='Delete Question']");//click on delete question
            new Click().clickBycssselector("span[class='cms-notification-message-ignore-changes cms-notification-message-delete-question']");//click on delete link on pop-up
            boolean questionDeleted = false;
            try
            {
                driver.findElement(By.id("question-raw-content")).getText();
            }
            catch (Exception e)
            {
                questionDeleted = true;
            }
            Assert.assertEquals(questionDeleted, true, "Question part is not deleted in MPQ once we click on \"Delete\" link on notification pop-up.");

            //TC row no. 44
            //Expected: Author should get back to the MPQ card view
            String text = new TextFetch().textfetchbyclass("cms-multipart-add-question-part");
            Assert.assertEquals(text, "Add new question part", "After deletion of question part from MPQ Author does not get back to the MPQ card view.");

            //TC row no. 45
            /*"21. Click on 'Add new question part"" button
            22. Choose True/False question type
            23. Fill in all the details for the question
            24. Select Bloom's taxonomy, difficulty level and Add Learning Objectives and click on Save"*/
            //Expected:  Question part should get saved successfully
            new Click().clickByclassname("cms-multipart-add-question-part");//click on Add new question part
            new Click().clickByid("qtn-type-true-false-img");//click on true false type question
            new Click().clickByid("question-raw-content");//click on Question
            new TextSend().textsendbyid("1", "mpq-question-part-input-label");//enter label in true false MPQ
            driver.findElement(By.id("question-raw-content")).sendKeys("True False For MPQ");//type the question
            new Click().clickBycssselector("span.true-false-answer-label");//click on Answer Option A
            new ComboBox().selectValue(7, "Hard");
            driver.findElement(By.id("content-solution")).sendKeys("Solution Text");
            driver.findElement(By.id("content-hint")).sendKeys("Hint Text");
            new Click().clickByid("saveQuestionDetails1");//click on Save
            String question1 = driver.findElement(By.id("question-raw-content")).getText();
            Assert.assertEquals(question1, "True False For MPQ", "Unable to add question stem after we delete a question stem.");

            //TC row no. 46
            //25. Click on Back button present at top left of the question part card view
            //Expected: Author should get navigated back to the MPQ card view and Question part should get saved successfully
            new Click().clickBycssselector("img[alt='Back']");//click on Back
            String str1 = new TextFetch().textfetchbyclass("cms-multipart-question-no");
            Assert.assertEquals(str1, "Q1.1:", "Click on Back button present at top left of the question part card view Author does not get navigated back to the MPQ card view and Question part is not saved successfully");

            //TC row no. 47
            //Expected: Author should get delete,comment and report content errors icon on MPQ level
            String deleteIcon = driver.findElement(By.cssSelector("img[alt='Delete Question']")).getAttribute("src");
            if(!deleteIcon.contains("delete-question-icon.png")){
                Assert.fail("Delete icon is absent after we add true false type question in MPQ and click back.");
            }
            String commentIcon1 = driver.findElement(By.id("al-question-discussion-icon-section")).getCssValue("background-image");
            if(!commentIcon1.contains("discussion-icon.png")){
                Assert.fail("Comment icon is absent after we add true false type question in MPQ and click back.");
            }
            String reportErrorIcon = driver.findElement(By.cssSelector("div[class='add-content-error show-content-issues-dialog']")).getCssValue("background-image");
            if(!reportErrorIcon.contains("plus_icon.png")){
                Assert.fail("Content report error icon is absent after we add true false type question in MPQ and click back.");
            }

            //TC row no. 48
            //Expected: Bloom's taxonomy, Difficult level and Learning Objective should not be present on the yellow footer on MPQ level
            boolean taxonomy= false;
            boolean difficultyLevel= false;
            boolean learningObjective= false;
            List<WebElement> allElements1 = driver.findElements(By.xpath("//*[starts-with(@id, 'sbSelector_')]"));
            for(WebElement ele: allElements1)
            {
                if(ele.getText().equals("Bloom's Taxonomy"))
                {
                    taxonomy = true;
                }
                if(ele.getText().equals("Difficulty Level"))
                {
                    difficultyLevel = true;
                }
                if(ele.getText().equals("Learning Objective"))
                {
                    learningObjective = true;
                }
            }
            Assert.assertEquals(taxonomy, false, "Taxonomy dropdown is displayed in footer of MPQ.");
            Assert.assertEquals(difficultyLevel, false, "Difficulty Level dropdown is displayed in footer of MPQ.");
            Assert.assertEquals(learningObjective, false, "Learning Objective dropdown is displayed in footer of MPQ.");

            //TC row no. 49
            //Expected: "After creating first question part, following new elements should appear in MPQ card view: "Add question stem>"" button along with ""Add new question part>"""
            String newQuestionPart = new TextFetch().textfetchbyclass("cms-multipart-add-question-part");
            Assert.assertEquals(newQuestionPart, "Add new question part", "\"Add new Question part >\" button is not present after adding first question part.");
            String addStem = new TextFetch().textfetchbyclass("cms-multipart-add-stem");
            Assert.assertEquals(addStem, "Add question stem", "\"Add question stem\" label is absent in multi part question after adding first question part.");

            //TC row no. 50
            //26. Click on "Add question stem" button
            //Expected: New question stem textbox should appear
            new Click().clickByclassname("cms-multipart-add-stem");//Click on "Add question stem" button
            String newStem = new TextFetch().textfetchbyid("question-mp-raw-content-2");
            Assert.assertEquals(newStem, "Enter The Question Stem", "Unable to add new question stem after adding first question part.");

            //TC row no. 54
            //"27. Enter the text in question stem area and save it
            //28. Click inside the question stem textbox"
            //Expected: Author should be able to edit the question stem

            new TextSend().textsendbyid("2nd question stem", "question-mp-raw-content-2");
            new Click().clickByid("saveQuestionDetails1");//click on Save
            String str2 = new RandomString().randomstring(5);
            new TextSend().textsendbyid(str2, "question-mp-raw-content-2");
            String question2 = new TextFetch().textfetchbyid("question-mp-raw-content-2");
            Assert.assertEquals(question2, str2, "Unable to edit the 2nd question stem");

            //TC row no. 55
            //Expected: Delete option should only be available from question stem 2 onwards
            String deleteQuestionStem = driver.findElement(By.cssSelector("img[alt='delete question stem']")).getAttribute("src");
            if(!deleteQuestionStem.contains("delete-question-icon.png"))
                Assert.fail("Delete option is not available from question stem 2 onwards");

            //TC row no. 57
            //29. Click on the delete icon
            //"On clicking delete icon, Robo-notification should appear saying "Yes delete the question stem "Cancel"
            driver.findElement(By.cssSelector("img[alt='delete question stem']")).click();
            Thread.sleep(2000);
            String cancelStem = new TextFetch().textfetchbycssselector("span[class='cms-notification-message-back-to-question cms-notification-message-delete-associated-content-cancel']");
            if(!cancelStem.contains("Cancel")){
                Assert.fail("On clicking delete icon, Robo-notification does not appear saying \"Cancel\"");
            }
            String deleteStem = new TextFetch().textfetchbycssselector("span[class='multipart-yes-link']");
            if(!deleteStem.contains("Yes, delete the question stem")){
                Assert.fail("On clicking delete icon, Robo-notification does not appear saying \"Yes delete the question stem\"");
            }

            //TC row no. 58
            //30. Click on Cancel option
            //Expected: The question stem should not be deleted and the robo-notification should be closed
            new Click().clickbyxpath("//span[contains(.,'Cancel')]");//click on Cancel link
            boolean notification = true;
            try {
                driver.findElement(By.id("cms-notification-dialog"));
            }
            catch (Exception e)
            {
                notification = false;
            }
            Assert.assertEquals(notification, true, "When author clicks on cancel link while deleting a question stem the notification doesnt closes.");
            String question3 = new TextFetch().textfetchbyid("question-mp-raw-content-2");
            Assert.assertEquals(question3, str2, "Delete the question stem When author clicks on cancel link.");

            //TC row no. 56, 59
            //Expected: The author should be able to delete the last stem below which there are no question parts.
            driver.findElement(By.cssSelector("img[alt='delete question stem']")).click();
            new Click().clickByclassname("multipart-yes-link");//click on Yes link
            boolean secondQuestionPart = true;
            try {
                driver.findElement(By.id("question-mp-raw-content-2"));
            }
            catch (Exception e)
            {
                secondQuestionPart = false;
            }
            Assert.assertEquals(secondQuestionPart, false, "The author is unable to delete the last stem below which there are no question parts.");

            //TC Row no. 60
            //32. Add the question stem again and click on "add new question part"
            //The complete multipart question should be saved and the author should navigate to the question part creation page
            new Click().clickByclassname("cms-multipart-add-stem");//Click on "Add question stem" button
            new TextSend().textsendbyid(str2, "question-mp-raw-content-2");
            new Click().clickByid("saveQuestionDetails1");//click on save button
            new Click().clickByclassname("cms-multipart-add-question-part");//click on Add new question part
            //Row no. 61
            //33. Add 2 or 3 question parts with different question types by clicking on "Add new question part
            new Click().clickbylistid("qtn-multiple-choice-img", 0);//click on Multiple Choice type question
            new Click().clickByid("question-mc-raw-content");//click on Question
            driver.findElement(By.id("question-mc-raw-content")).sendKeys("Multiple Choice Question");//type the question
            new Click().clickBycssselector("div[class='single-select-choice-icon single-select-choice-icon-deselect']"); //select correct answer as A
            List<WebElement> answerOptions = driver.findElements(By.xpath("//*[starts-with(@id, 'popupEditor_')]"));
            answerOptions.get(0).sendKeys("Option 1");
            answerOptions.get(1).sendKeys("Option 2");
            answerOptions.get(2).sendKeys("Option 3");
            answerOptions.get(3).sendKeys("Option 4");
            new TextSend().textsendbyid("1", "mpq-question-part-input-label");//enter label in true false MPQ
            new Click().clickByid("saveQuestionDetails1");//click on save button
            new Click().clickBycssselector("img[alt='Back']");//click on Back
            new Click().clickByclassname("cms-multipart-add-stem");//Click on "Add question stem" button
            String str3 = new RandomString().randomstring(5);
            new TextSend().textsendbyid(str3, "question-mp-raw-content-4");
            new Click().clickByid("saveQuestionDetails1");//click on save button
            new Click().clickByclassname("cms-multipart-add-question-part");//click on Add new question part
            new Click().clickbylistid("qtn-text-entry-img", 0);//click on Text Entry type question
            new Click().clickByid("question-raw-content");//click on Question
            driver.findElement(By.id("question-raw-content")).sendKeys("Text Entry ");//type the question
            new TextSend().textsendbycssSelector("Correct Answer", "input[class='numeric_text_entry_input get-user-entry is-value-changed-txt-entry']");
            new Click().clickBycssselector("span[class='btn sty-green save-spanish-text accept_answer text_entry_accept_answer']"); //click on Accept answer button
            new TextSend().textsendbyid("1", "mpq-question-part-input-label");//enter label in true false MPQ
            new Click().clickByid("saveQuestionDetails1");//click on save button
            new Click().clickBycssselector("img[alt='Back']");//click on Back
            //TC row no. 64
            //Expected: The order of cards including multiple stems and questions parts should be guided by the order in which they are created by the author
            List<WebElement> allElements2 = driver.findElements(By.xpath("//*[starts-with(@id, 'question-mp-raw-content-')]"));
            if(!allElements2.get(0).getText().contains(str) || !allElements2.get(1).getText().contains(str2) || !allElements2.get(2).getText().contains(str3)){
                Assert.fail("The order question stem is not correct.");
            }
            //TC row no. 65
            //All the question parts should be shown with the question number + <question label>
            String label = new TextFetch().textfetchbyclass("cms-multipart-question-no");
            Assert.assertEquals(label, "Q1.1:", "All the question parts is not shown with the question number + <question label>.");

            //TC row no. 67-73
            //34. Click on the arrow icon at the end of any question part
            //Author should be able to click on the arrow icon
            new Click().clickByclassname("cms-multipart-question-arw");//click on question part arrow
            new Click().clickByid("question-raw-content");//click on Question
            new TextSend().textsendbyid("2", "mpq-question-part-input-label");//enter label in true false MPQ
            new TextSend().textsendbyid("True False For MPQ Edited", "question-raw-content");//type the question
            new Click().clickByid("saveQuestionDetails1");//click on save button
            new Click().clickBycssselector("img[alt='Back']");//click on Back
            String editedQpart = new TextFetch().textfetchbyclass("cms-multipart-question-content");
            Assert.assertEquals(editedQpart, "True False For MPQ Edited", "Author is unable to edit the question part.");

            String labelEdited = new TextFetch().textfetchbyclass("cms-multipart-question-no");
            Assert.assertEquals(labelEdited, "Q1.2:", "Author is unable to edit the label of question part.");

            Thread.sleep(3000);
            //TC row no. 75, 76
            /* "37. Delete all the question parts present under the question stem
            38. Delete the question stem"*/
            //Expected: Question stem should get deleted successfully
            new Click().clickbylist("cms-multipart-question-arw", 1);//click on question part arrow
            new Click().clickBycssselector("img[alt='Delete Question']");//click on delete question
            new Click().clickBycssselector("span[class='cms-notification-message-ignore-changes cms-notification-message-delete-question']");//click Yes to delete
            driver.findElement(By.id("question-mp-raw-content-2")).click();//click on 1st question part
            driver.findElement(By.id("question-mp-raw-content-2")).click();//click on 1st question part
            new Click().clickBycssselector("img[alt='delete question stem']");//click on delete to question stem
            new Click().clickByclassname("multipart-yes-link");//click on Yes link
            boolean questionStemFound = false;
            List<WebElement> allElements3 = driver.findElements(By.xpath("//*[starts-with(@id, 'question-mp-raw-content-')]"));
            for(WebElement element: allElements3)
            {
                if(element.getText().contains(str2))
                {
                    questionStemFound = true;
                }
            }
            Assert.assertEquals(questionStemFound, false, "Author is unable to edit delete a question stem.");
            //Row no 78
            //The “Save” button should be disabled and the author should not be able to make any changes to that version of multipart question.
            new ComboBox().selectValue(5, "Publish");//publish the question
            new Click().clickByid("saveQuestionDetails1");//click on save button
            boolean saveButtonEnabled1 = driver.findElement(By.id("save-question-disabled")).isSelected();
            Assert.assertEquals(saveButtonEnabled1, false, "After publishing the MPQ the save button is still enabled.");

            //Row no 80
            //The author should be able to add comments for the MPQ as supported by the product now for any other question type .
            new Click().clickByid("al-question-discussion-icon-section");
            driver.findElement(By.className("al-question-discussion-input-section")).sendKeys("Commnet on MPQ"+ Keys.RETURN);
            //TC row no 81
            //40. Click on "+" icon present near Question type and click on revision.
            new Click().clickByid("questionOptions");
            new Click().clickbyxpath("//div[contains(.,'Revisions')]");
            //TC row no. 83
            new ReportContentIssue().reportContentIssue(new RandomString().randomstring(5));


        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase selectTrueFalseFromMPQ in  class MultiPartQuestionForStaticAssessment.", e);
        }
    }
    @AfterMethod
    public void TearDown()throws Exception
    {
        //driver.quit();
    }
}
