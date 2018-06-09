package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT17.R1714;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*
 * Created by sumit on 4/12/14.
 */
public class AuthorShouldBeAbleToSearchMPQ extends Driver {

    @Test(priority = 1, enabled = true)

    public void authorShouldBeAbleToSearchMPQ()
    {
        try
        {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "106");
            String questionset = ReadTestData.readDataByTagName("", "questionset", "106");
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", "106");
            //Driver.startDriver();
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse(); //select a course
            new SelectCourse().selectChapterByIndex(0);//select chapter
            driver.findElement(By.cssSelector("div.create-practice")).click();//click on Create Practice
            Thread.sleep(3000);
            new Click().clickByclassname("create-regular-assessment-popup-item");
            Thread.sleep(3000);
            new ComboBox().selectValue(3, "Static Practice");
            driver.findElement(By.id("assessmentName")).click();
            driver.findElement(By.id("assessmentName")).clear();
            driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
            driver.findElement(By.id("questionSetName")).clear();
            driver.findElement(By.id("questionSetName")).sendKeys(questionset);
            new QuestionCreate().multiPartQuestion(106);//Create MPQ
            new Click().clickByid("content-search-icon");//click on Search Icon
            new TextSend().textsendbyid(questiontext,"content-search-field");
            new Click().clickByid("search-question-contents-icon");//click on Go
            //TC Row no. 106, 107
            //Multi-part question should appear under Search result
            String question = new TextFetch().textfetchbycssselector("div[class='search-question-rawcontent search-question-content']");
            Assert.assertEquals(question, questiontext, "Multi-part question does not appear under Search result");

            //TC Row no. 109
            //A “Question Group: Multi part” should be added to the top right card
            String questionGroup = new TextFetch().textfetchbyclass("question-group-block");
            Assert.assertEquals(questionGroup, "Question Group : Multi part", "“Question Group: Multi part” is not added to the top right card");

            //TC Row no. 110
            String totalQs = new TextFetch().textfetchbyclass("mpq-total-question-block");
            Assert.assertEquals(totalQs, "Questions (2)", "\"Question Group: Multi part\" is not added to the top right card");

            //TC Row no. 111
            new MouseHover().mouserhoverbyid("content-search-results-block");
            boolean previewIconDisplayed = driver.findElement(By.className("preview-question-content")).isDisplayed();
            Assert.assertEquals(previewIconDisplayed, true, "Edit icon is missing in MPQ search result.");

            boolean editIconDisplayed = driver.findElement(By.className("edit-question-content")).isDisplayed();
            Assert.assertEquals(editIconDisplayed, true, "Edit icon is missing in MPQ search result.");

            String winHandleBefore = driver.getWindowHandle();//Store the current window handle

            new Click().clickByclassname("mpq-total-question-block");//click total question count block
            Thread.sleep(15000);
            //Switch to new window opened
            for(String winHandle : driver.getWindowHandles()){
                driver.switchTo().window(winHandle);
            }
            String points = new TextFetch().textfetchbyclass("multi-part-question-points");
            Assert.assertEquals(points, "Points Available: 1", "Points Available:1 is absent in MPQ preview.");

            String totalPoints = new TextFetch().textfetchbyclass("question-preview-sidebar-total-points");
            Assert.assertEquals(totalPoints, "Total Points: 2", "Total Points:1 is absent in performance tab of MPQ preview.");

            String perfTenQs = new TextFetch().textfetchbyclass("cms-question-preview-sidebar-title-sectn");
            Assert.assertEquals(perfTenQs, "Performance in Last 10 Qs", "\"Performance in Last 10 Qs\" label is absent in performance tab of MPQ preview.");

            String abtThisQs = new TextFetch().textfetchbyclass("cms-about-this-question-title");
            Assert.assertEquals(abtThisQs, "About this Question", "\"About this Question\" label is absent in performance tab of MPQ preview.");

            String hint = new TextFetch().textfetchbyclass("part-question-hint");
            Assert.assertEquals(hint, "Hint", "\"Hint\" option is absent in MPQ preview.");

            String solution = new TextFetch().textfetchbyclass("part-question-solution");
            Assert.assertEquals(solution, "Solution", "\"Solution\" option is absent in MPQ preview.");

            String checkAnswer = new TextFetch().textfetchbyclass("part-question-check-answer");
            Assert.assertEquals(checkAnswer, "Check Answer", "\"Check Answer\" option is absent in MPQ preview.");

            new Click().clickByclassname("true-false-student-answer-label");//select answer A
            new Click().clickByclassname("part-question-check-answer");//click on Check answer
            String result = driver.findElement(By.xpath("//*[starts-with(@class, 'part-evaluated-result part-question-check-answer-evaluated-result')]")).getText();
            Assert.assertEquals(result, "You got it right", "\"You got it right\" message is not coming when we click on Check Answer in Preview page of MPQ.");

            new Click().clickbylist("question-toggle-arrow-icon", 1);//click on expand second question

            String points1 = new TextFetch().textfetchbylistclass("multi-part-question-points", 1);
            Assert.assertEquals(points1, "Points Available: 1", "Points Available:1 is absent in MPQ preview for 2nd question part.");

            driver.close();//Close the new window, if that window no more required

            driver.switchTo().window(winHandleBefore);//Switch back to original browser (first window)

            //TC row no. 122 ..."5. Close the preview window  6. Click on Full Preview icon"
            String winHandleBefore1 = driver.getWindowHandle();//Store the current window handle
            new MouseHover().mouserhoverbyid("content-search-results-block");
            driver.findElement(By.className("preview-question-content")).click();
            Thread.sleep(15000);
            //Switch to new window opened
            for(String winHandle : driver.getWindowHandles()){
                driver.switchTo().window(winHandle);
            }
            String points2 = new TextFetch().textfetchbyclass("multi-part-question-points");
            Assert.assertEquals(points2, "Points Available: 1", "Points Available:1 is absent in MPQ preview.");

            String totalPoints1 = new TextFetch().textfetchbyclass("question-preview-sidebar-total-points");
            Assert.assertEquals(totalPoints1, "Total Points: 2", "Total Points:1 is absent in performance tab of MPQ preview.");

            String perfTenQs1 = new TextFetch().textfetchbyclass("cms-question-preview-sidebar-title-sectn");
            Assert.assertEquals(perfTenQs1, "Performance in Last 10 Qs", "\"Performance in Last 10 Qs\" label is absent in performance tab of MPQ preview.");

            String abtThisQs1 = new TextFetch().textfetchbyclass("cms-about-this-question-title");
            Assert.assertEquals(abtThisQs1, "About this Question", "\"About this Question\" label is absent in performance tab of MPQ preview.");

            String hint1 = new TextFetch().textfetchbyclass("part-question-hint");
            Assert.assertEquals(hint1, "Hint", "\"Hint\" option is absent in MPQ preview.");

            String solution1 = new TextFetch().textfetchbyclass("part-question-solution");
            Assert.assertEquals(solution1, "Solution", "\"Solution\" option is absent in MPQ preview.");

            String checkAnswer1 = new TextFetch().textfetchbyclass("part-question-check-answer");
            Assert.assertEquals(checkAnswer1, "Check Answer", "\"Check Answer\" option is absent in MPQ preview.");

            new Click().clickByclassname("true-false-student-answer-label");//select answer A
            new Click().clickByclassname("part-question-check-answer");//click on Check answer
            String result1 = driver.findElement(By.xpath("//*[starts-with(@class, 'part-evaluated-result part-question-check-answer-evaluated-result')]")).getText();
            Assert.assertEquals(result1, "You got it right", "\"You got it right\" message is not coming when we click on Check Answer in Preview page of MPQ.");

            new Click().clickbylist("question-toggle-arrow-icon", 1);//click on expand second question

            String points3 = new TextFetch().textfetchbylistclass("multi-part-question-points", 1);
            Assert.assertEquals(points3, "Points Available: 1", "Points Available:1 is absent in MPQ preview for 2nd question part.");

            driver.close();//Close the new window, if that window no more required

            driver.switchTo().window(winHandleBefore1);//Switch back to original browser (first window)
            Thread.sleep(2000);
            //TC row no. 132...7. Click on Edit icon
            new MouseHover().mouserhoverbyid("content-search-results-block");
            new Click().clickByclassname("edit-question-content");//click on Edit
            String url = driver.getCurrentUrl();
            if(!url.contains("quesEditView"))
                Assert.fail("After clicking on edit icon from Question Search page the author does not navigate to MPQ card view.");

            new Click().clickByclassname("cms-multipart-question-arw");//click on question part arrow
            new Click().clickByid("question-raw-content");//click on Question
            new TextSend().textsendbyid("2", "mpq-question-part-input-label");//enter label in true false MPQ
            new TextSend().textsendbyid("True False For MPQ Edited", "question-raw-content");//type the question
            new Click().clickByid("saveQuestionDetails1");//click on save button
            new Click().clickBycssselector("img[alt='Back']");//click on Back
            String editedQpart = new TextFetch().textfetchbyclass("cms-multipart-question-content");
            Assert.assertEquals(editedQpart, "True False For MPQ Edited", "Author is unable to edit the question part from the edit button of MPQ search page.");

            //TC row no. 134
            /*"8. Again go to search page and search for multipart question
            9. Select the multipart question including other type of questions and click on Launch review button"*/


            new Click().clickByid("content-search-icon");//click on Search Icon
            new TextSend().textsendbyid(questiontext, "content-search-field");
            new Click().clickByid("search-question-contents-icon");//click on Go
            String checkboxId = driver.findElement(By.className("content-search-title-section")).getAttribute("questionid");
            new Click().clickBycssselector("label[id='"+checkboxId+"']");//click on Check box
            new Click().clickBycssselector("img[title='Launch review']");//click on Launch Review
            Thread.sleep(10000);
            String question1 = new TextFetch().textfetchbyclass("cms-preview-stem-content");
            Assert.assertEquals(question1, questiontext, "MPQ question is not displayed on clicking Launch Review from Question Search Page.");

            String qtype = new TextFetch().textfetchbyid("cms-content-review-question-type-wrapper");
            Assert.assertEquals(qtype, "QuestionType: Multi Part", "MPQ question is not displayed on clicking Launch Review from Question Search Page.");

            String qId = new TextFetch().textfetchbyclass("cms-preview-stem-no");
            Assert.assertEquals(qId.trim(), "QId - "+checkboxId+":", "Question label as <Q Id> + text followed by the first question stem is not shown for the multipart question in the first line.");

            String comment = new TextFetch().textfetchbyclass("question-comments-link");
            //Assert.assertEquals(comment, "Comments (1)", "Comment link is absent in Launch Review page.");
            if(!(comment.contains("Comments"))){
                Assert.fail("Comment link is absent in Launch Review page.");
            }

            String points4 = new TextFetch().textfetchbyclass("multi-part-question-points");
            Assert.assertEquals(points4, "Points Available: 1", "Points Available:1 is absent in MPQ Launch Review page.");

            new Click().clickbylist("question-toggle-arrow-icon", 1);//click on expand second question

            String points5 = new TextFetch().textfetchbylistclass("multi-part-question-points", 1);
            Assert.assertEquals(points5, "Points Available: 1", "Points Available:1 is absent in MPQ Launch Review page for 2nd question part.");

            new Click().clickByclassname("question-comments-link");//click on comment link
            String commentText = new RandomString().randomstring(5);
            driver.findElement(By.className("al-question-discussion-input-section")).sendKeys(commentText+ Keys.RETURN);
            String comment1 = new TextFetch().textfetchbyid("al-user-discussion-question-content");
            Assert.assertEquals(comment1, "Q: "+commentText, "Author unable to add comment for a MPQ Launch Review page.");

            new Click().clickBycssselector("input[id='"+checkboxId+"']");//click on Check box

            new Click().clickByclassname("question-edit-link");//edit question
            String url1 = driver.getCurrentUrl();
            if(!url1.contains("quesEditView"))
                Assert.fail("After clicking on edit question link from icon from Launch Review page the author does not navigate to MPQ card view.");

            new Click().clickByclassname("cms-multipart-question-arw");//click on question part arrow
            new Click().clickByid("question-raw-content");//click on Question
            new TextSend().textsendbyid("2", "mpq-question-part-input-label");//enter label in true false MPQ
            String editQuestion = new RandomString().randomstring(5);
            new TextSend().textsendbyid(editQuestion, "question-raw-content");//type the question
            new Click().clickByid("saveQuestionDetails1");//click on save button
            new Click().clickBycssselector("img[alt='Back']");//click on Back
            String editedQpart1 = new TextFetch().textfetchbyclass("cms-multipart-question-content");
            Assert.assertEquals(editedQpart1, editQuestion, "Author is unable to edit the question part from the edit button of MPQ search page.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase authorShouldBeAbleToSearchMPQ in  class AuthorShouldBeAbleToSearchMPQ.", e);

        }
    }
    /*@AfterMethod
    public void TearDown()throws Exception
    {
        driver.quit();
    }*/
}
