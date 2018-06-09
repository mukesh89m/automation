package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT17.R1714;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssessmentResponses;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.Preview;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.AssignmentTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Tabs.DiagnosticTab;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.apache.commons.collections.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by root on 5/13/15.
 */
public class AttemptingMPQAssessmentAsStudent extends Driver {


    String multipleChoiceQuestionText = "Multiple Choice Question";
    String discussionText = "Hi, I have attempted the assignment";
    String postText = "Hi, I have Posted the comments";

    @Test(priority = 1, enabled = true)
    public void studentToBeAbleToViewAndAttemptMPQ() {
        try {

            /*Row No - 2 : "1. Login as a student
            2. Go to eTextbook and start static assessment having multipart question
            3. Go to multipart question with dependent type"*/
            int dataIndex = 2;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);

            new Assignment().create(dataIndex);
            new Assignment().addQuestions(3, "multipart", assessmentName);
            new Assignment().addQuestions(3, "truefalse", assessmentName);


            new LoginUsingLTI().ltiLogin("2_2");//Log in as a student
            new Assignment().startStaticAssignmentFromeTextbook(2, 1);
            Thread.sleep(3000);

            //Expected - 1 :  By default, when the student comes to the multipart question dependent , the first question part should only be enabled
            if (!(driver.findElement(By.id("part-3")).getAttribute("class").equals("question-part-wrapper multi-part-collapsed multi-part-unattempted  disabled"))) {
                Assert.fail("By default, when the student comes to the multipart question dependent , the first question part should only be enabled\n");
            }


            //Expected - 3 : First question part should be expanded by default
            if (!(driver.findElement(By.cssSelector("div[class='question-part-wrapper multi-part-unattempted multi-part-selected']")).findElements(By.tagName("div")).get(0).getAttribute("class").equals("question-toggle-arrow-icon collapse"))) {
                Assert.fail("First question part should be expanded by default");
            }


            //Expected - 4 : There should be a Blue border around the card to signify that it is the latest opened card
            System.out.println("Border Color is : " + driver.findElement(By.className("multi-part-question-container")).getCssValue("border-color"));


            //Expected - 5 : All subsequent parts should be disabled and greyed out
            if (!(driver.findElement(By.id("part-3")).getAttribute("class").equals("question-part-wrapper multi-part-collapsed multi-part-unattempted  disabled"))) {
                Assert.fail("All subsequent parts should be disabled and greyed out");
            }


            //Expected - 6 : "Next question part" button should be enabled for first question part
            if (!(assignments.button_gotoNextQuestionPart.isEnabled())) {
                Assert.fail("\"Next question part\" button should be enabled for first question part");
            }


            //Expected - 7 : "Submit Answer" button should be present on the yellow footer on MPQ level
            if (!(assignments.button_submitAnswer.isEnabled() && assignments.button_submitAnswer.isDisplayed())) {
                Assert.fail("\"Submit Answer\" button should be present on the yellow footer on MPQ level");
            }


            //Expected - 8 : All the question stems should be visibe and there should not be any collapse/expand behavior for it
            if (driver.findElements(By.cssSelector("div[class='question-toggle-arrow-icon collapse']")).size() > 1) {
                Assert.fail("All the question stems should be visibe and there should not be any collapse/expand behavior for it");
            }



            /*Expected - 9 : "Expanded question part should show total points available for that question part.
            For example: Points available :<X>"*/
            Thread.sleep(2000);
            Assert.assertEquals(assignments.label_pointsAvailable.getText().trim(), "Points Available: 1", "Expanded question part should show total points available for that question part.");


            /*Expected - 10 : "Only one line should be maintained above the question stem with “question parts answered” label as “Answered x/y” where
            x - No of question parts attempted
            y - Total no of question parts"*/
            Assert.assertEquals(assignments.label_answered.getText().trim(), "Answered 0/2", "Only one line should be maintained above the question stem with “question parts answered” label as “Answered x/y” where");


            //Expected - 11  : Question time spent should be captured at the multipart question level .
            if (!(assignments.timer_timeSpent.getAttribute("clock").equals("SHOW"))) {
                Assert.fail("Question time spent should be captured at the multipart question level .");
            }


            //Expected- 13 : Visual indicator should be displayed on each question part card on top left portion. It should be grey by default
            if (!assignments.grey_questionIndicator.isDisplayed()) {
                Assert.fail("Visual indicator should be displayed on each question part card on top left portion. It should be grey by default");
            }


            //Expected - 14  : Mark for Review  should be at the multipart question level only.
            Assert.assertEquals(assignments.checkBox_MarkForReview.getText().trim(), "Mark for Review", "Mark for Review  should be at the multipart question level only.");


            /*"Expected  - 15 : Each question part, if enabled, should have the following elements:
                    -Solution ( as per existing functionality)
                    -Hint ( as per existing functionality)
                    -Writeboard (as per existing functionality)"*/
            Assert.assertEquals(assignments.button_hint.getText().trim(), "Hint", "Hint button is not existing");


            //Expected - 16  : Only Performance tab should appear on the right side before submitting the response of multipart question
            //System.out.println("assignments.tab_performance.getText() : " + assignments.tab_performance.getText());
            if (!(assignments.tab_performance.getText().equals("Performance"))) {
                Assert.fail("Only Performance tab should appear on the right side before submitting the response of multipart question");
            }


            /*Expected - 17  : "Performance tab on right side should show the following extra detail:
                    <QNo>- Total Points: + <x> ,
                    where x is sum total of all the points for all the question parts for that particular multipart question."*/
            Assert.assertEquals(assignments.label_totalPoints.getText().trim(), "Total Points : 2", "Performance tab on right side should show ''Total Points : <x> ");


            /*Expected - 18 : "Following details should be present under Performance tab as product supports now:
                    - Performance in Last 10 Qs
                    - Percentage students who got it correct
                    - Study this topic (should show all the LOs associated with that multi-part question)

            Note: For other assessments(Diagnostic and Practice), About this Question label should appear"*/
            Assert.assertEquals(assignments.label_performanceInLast10Qs.getText().trim(), "Performance in Last 10 Qs", "Performance in Last 10 Qs label is not displayed under performance tab ");
            if (!(assignments.labelValue_studentsGotItCorrect.getText().trim().contains("Students got it correct"))) {
                Assert.fail("Students got it correct label is not displayed under performance tab");
            }
            Assert.assertEquals(assignments.label_studyThisTopic.getText().trim(), "Study this topic", "Study this topic label is not displayed under performance tab ");
            assignments.link_questionAssociationSkillID.click();
            Thread.sleep(5000);
            Assert.assertEquals(lessonPage.getLessonTitle().getText(), "1.1 THE SCIENCE OF BIOLOGY", "Study this topic (should show all the LOs associated with that multi-part question");
            assignments.icon_closeforEtextBook.click();
            Thread.sleep(5000);
            //Row No - 20 : 4. Submit the response of question part by clicking "Next question part"
            new Click().clickByElement(assignments.button_true);
            Thread.sleep(5000);
            assignments.button_gotoNextQuestionPart.click();
            Thread.sleep(5000);


            //Expected - 1 : "Next question part of multipart question should get enabled"
            Assert.assertEquals(assignments.questionTextList.get(3).getText().trim(), multipleChoiceQuestionText, "Next question part of multipart question should get enabled\"");
            for (int a = 0; a < assignments.questionTextList.size(); a++) {
                System.out.println("assignments.questionTextList : " + assignments.questionTextList.get(a).getText());

            }

            //Expected - 2 : There should be a 'down blue arrow' present on the question part card view to signify that it can be expanded
            if (!(assignments.icon_upperArrowList.size() == 2)) {
                Assert.fail("There should be a 'down blue arrow' present on the question part card view to signify that it can be expanded");
            }


            //Expected - 3 : Visual indicator on top left portion of the question part card should turn to Blue once student submits the response.
            new Click().clickByclassname("true-false-student-answer-label");
            String backgroundIndicator = assignments.icon_blueTriangle.getAttribute("background-color");
            System.out.println("backgroundIndicator : " + backgroundIndicator);


            //Expected - 4 : Clicking on blue traingle portion should show tooltip "Completed"
            Assert.assertEquals(assignments.icon_blueTriangle.getAttribute("title"), "Completed", "Clicking on blue traingle portion should show tooltip \"Completed\"");


            //Expected - 5 :Performance in Last 10 Qs should not show any response  bar after attempting only question part. It should show the response only after submitting the complete multipart question and move to next question
            if (driver.findElements(By.xpath("//div[@class='al-content-performance-question-bars static-content-performance-question-bars']//div")).size() != 0) {
                Assert.fail("Performance in Last 10 Qs should not show any response  bar after attempting only question part. It should show the response only after submitting the complete multipart question and move to next question");
            }

            //Expected - 6 : Discussion and Star tabs should not appear after submitting the response for a question part
            if (driver.findElement(By.cssSelector("span[title = 'Discussion']")).isDisplayed()) {
                Assert.fail("Discussion tab should not appear after submitting the response for a question part");
            }
            if (driver.findElement(By.cssSelector("span[title = 'Add to My Notes']")).isDisplayed()) {
                Assert.fail("Star tab should not appear after submitting the response for a question part");
            }


            //Row No - 27 : 5. Click on the down arrow for the next question part card
            //Expected - 1 : On clicking the down arrow, the collapsed question part should get expanded
            assignments.icon_upperArrowList.get(1).click();
            Thread.sleep(2000);


            //Row No - 26 - Expected : The question statement after line 1 and the answer area for the question part should not be accessible until the question part is expanded by clicking on the arrow at the end of any the question part card.
            if (driver.findElement(By.xpath("//span[text() = 'Option 4']")).isDisplayed()) {
                Assert.fail("The question statement after line 1 and the answer area for the question part should not be accessible until the question part is expanded by clicking on the arrow at the end of any the question part card.");
            }


            assignments.icon_downArrowList.get(0).click();
            Thread.sleep(2000);
            if (!(driver.findElement(By.xpath("//span[text() = 'Option 4']")).isDisplayed())) {
                Assert.fail("On clicking the down arrow, the collapsed question part should get expanded");
            }


            //Expected - 2 : Expanded question part should show 'up arrow' with grey color
            if (!(assignments.icon_upperArrowList.size() == 2)) {
                Assert.fail("Expanded question part should show 'up arrow' with grey color");
            }


            //Expected - 3 : Clicking on up arrow in the question part card, should collapse the card
            assignments.icon_upperArrowList.get(1).click();
            Thread.sleep(2000);
            if (driver.findElement(By.xpath("//span[text() = 'Option 4']")).isDisplayed()) {
                Assert.fail("Clicking on up arrow in the question part card, should collapse the card");
            }




            /*Row No - 33 : "6. Submit the response for all the question parts except the last one
            7. Go to last question part"*/
            //Expected - For last question part, "Next question part" button should not be present
            assignments.icon_downArrowList.get(0).click();
            new UIElement().waitAndFindElement(assignments.button_submitThisQuestionPart);
            Thread.sleep(3000);
            if (!(assignments.button_submitThisQuestionPart.getText().equals("Submit this question part"))) {
                Assert.fail("For last question part, \"Next question part\" button should not be present & Submit this question part button should eb present");
            }


            //Row No - 34 : 8. Click on "Submit Answer" button present on the yellow footer
            //Expected - 1 : Student should be able to submit the response for last question part by clicking on "Submit Answer" button on MPQ level
            Thread.sleep(5000);
            new Click().clickByElement(assignments.button_option4);
            Thread.sleep(2000);
            assignments.button_submitAnswer.click();
            Thread.sleep(3000);
            if (driver.findElements(By.cssSelector("input[title='Submit Answer']")).size() != 0) {
                Assert.fail("Student should be able to submit the response for last question part by clicking on \"Submit Answer\" button on MPQ level");
            }


            //Expected - 2 : On yellow footer, "Next question" button should appear along with 'report content errors' icon
            try {
                driver.findElement(By.cssSelector("input[title='Next Question']"));
            } catch (Exception e) {
                Assert.fail("On yellow footer, \"Next question\" button should appear along with 'report content errors' icon");
            }
            if (!(assignments.dialog_ContentIssue.isDisplayed())) {
                Assert.fail("Report content errors' icon is n ot appearing");
            }

            //Expected - 3 : Discussion and Star tabs should appear for that multipart question
            if (!(assignments.discussionTab.isDisplayed())) {
                Assert.fail("Discussion tab should appear for that multipart question");
            }
            if (!(assignments.tab_addToMyNotes.isDisplayed())) {
                Assert.fail("Star tab should appear for that multipart question");

            }

            //Expected - 4 : For each question parts, response should be shown as correct or incorrect as per the attempt by student side
            List<WebElement> trueTickMark_mcOptionList = driver.findElements(By.cssSelector("div[class='true-tick']"));
            int trueTickMarkPosition = 0;
            for(int a=0;a<trueTickMark_mcOptionList.size();a++){
                if(trueTickMark_mcOptionList.get(a).isDisplayed()){
                    trueTickMarkPosition = a;
                    break;
                }
            }

            List<WebElement> falseTickMark_mcOptionList = driver.findElements(By.cssSelector("div[class='false-tick']"));
            int falseTickMarkPosition = 0;
            for(int a=0;a<falseTickMark_mcOptionList.size();a++){
                if(falseTickMark_mcOptionList.get(a).isDisplayed()){
                    falseTickMarkPosition=a;
                    break;
                }
            }


            if (!(trueTickMark_mcOptionList.get(trueTickMarkPosition).isDisplayed() && falseTickMark_mcOptionList.get(falseTickMarkPosition).isDisplayed())) {
                Assert.fail("For each question parts, response should be shown as correct or incorrect as per the attempt by student side");
            }


            /*Row No - 38 : "9. Add a discussion through discussion tab
            10. Add a note through Star tab"*/
            //Expected -1 : The discussion tab should allow the student to add discussion at the multipart question level as supported for any other question.
            //Expected - 2 : The star tab should allow the student to add note for a multipart question at the multipart question level as supported for any other question.
            assignments.discussionTab.click();
            new Discussion().postDiscussion(discussionText);
            new Discussion().postNote(postText);


            //Row No - 85 : 11. Click on "Next question" button
            assignments.nextQuestionButton.click();
            //Expected -1 : “Performance on last 10 Q” should get updated for the attempted multipart question
            if (!(driver.findElement(By.xpath("//div[@class='al-content-performance-question-bars static-content-performance-question-bars']//div")).isDisplayed())) {
                Assert.fail("“Performance on last 10 Q” should get updated for the attempted multipart question");
            }


            //Row No - 44 : 10. Click on the question dropdown
            assignments.questionDropdown.click();
            Thread.sleep(2000);
            //Expected - 1 : On clicking the question dropdown,grey tick mark should be displayed for multipart question under Attempted category
            if (!(driver.findElement(By.xpath("//tr[@qindex='1']")).findElements(By.tagName("i")).get(0).getAttribute("class").equals("s s--check-green"))) {
                Assert.fail("On clicking the question dropdown,grey tick mark should be displayed for multipart question under Attempted category");

            }
            //Expected - 2 : If student does not attempt all the question parts or skips the multipart question, then it should show as red blank under Attempted Category
            if (!(driver.findElement(By.xpath("//tr[@qindex='2']")).findElements(By.tagName("i")).get(0).getAttribute("class").equals("s s--check-not-attempted"))) {
                Assert.fail("If student does not attempt all the question parts or skips the multipart question, then it should show as red blank under Attempted Category");

            }


            //Row No - 46 : 12. Select the multipart question dependent in the question dropdown
            //Expected 1 - Student should navigate to multipart question
            assignments.questionDropdown.click();
            driver.findElement(By.xpath("//tr[@qindex='1']")).findElements(By.tagName("i")).get(0).click();
            Thread.sleep(2000);
            assignments.questionDropdown.click();
            driver.findElement(By.xpath("//tr[@qindex='2']")).findElements(By.tagName("i")).get(0).click();
            Thread.sleep(3000);
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(3));
            Assert.assertEquals(assignments.MPQquestionText.getText().trim(), questionText, "Student should navigate to multipart question");


            //Expected - 2 : Student should view the multipart question with all the question parts open as done in the last visit.
            ArrayList<String> questionList = new AssigningMPQAssignment().traverseQuestionsPartsInMPQInResponsePage(46);
            Thread.sleep(5000);
            System.out.println("questionList : " + questionList);
            ArrayList<String> expectedQuestionList = new ArrayList<>();
            String questionText1 = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(2));
            String questionText2 = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(3));
            String trueFalseQuestionText = "True False For MPQ";
            String stem1Question = "Stem 2 Question";
            String stem2Question = "Stem 3 Question";
            expectedQuestionList.add(questionText1);
            expectedQuestionList.add(trueFalseQuestionText);
            expectedQuestionList.add(stem1Question);
            expectedQuestionList.add(questionText2);
            expectedQuestionList.add(trueFalseQuestionText);
            expectedQuestionList.add(stem2Question);
            expectedQuestionList.add(questionText2);
            expectedQuestionList.add(trueFalseQuestionText);
            expectedQuestionList.add(stem2Question);
            expectedQuestionList.add(questionText2);
            expectedQuestionList.add(trueFalseQuestionText);
            System.out.println("expectedQuestionList : " + expectedQuestionList);
            Collections.sort(expectedQuestionList);
            Collections.sort(questionList);
            if (!(expectedQuestionList.contains(questionList))) {
                //Assert.fail("The instructor should be able to view all the question part in the same way as it renders for single question in the product now.");
            }


            //Row No - 42 : Expected - If the student skips all the question parts,then the bar should be shown in grey color(Skipped)
            System.out.println("Style : " + driver.findElement(By.xpath("//div[@class='al-content-performance-question-bars static-content-performance-question-bars']//div[3]")).getAttribute("style"));
            if (!driver.findElement(By.xpath("//div[@class='al-content-performance-question-bars static-content-performance-question-bars']//div[3]")).getAttribute("style").equals("background: none repeat scroll 0% 0% rgb(204, 204, 204); height: 18px; border: 1px solid rgb(204, 204, 204);")) {
                Assert.fail("If the student skips all the question parts,then the bar should be shown in grey color(Skipped)");
            }


            assignments.button_submitAnswer.click();
            new UIElement().waitAndFindElement(assignments.nextQuestionButton);
            Thread.sleep(1000);
            assignments.nextQuestionButton.click();

            if (!(assignments.hint_button.isDisplayed())) {
                Assert.fail("Hint button is not displayed");
            }


            if (!(preview.plusWorkBoard.isDisplayed())) {
                Assert.fail("Work board is not displayed");
            }


        } catch (Exception e) {
            Assert.fail("Exception in the testcase 'studentToBeAbleToViewAndAttemptMPQ' in the class 'studentToBeAbleToViewAndAttemptMPQ'", e);
        }
    }


    @Test(priority = 2, enabled = true)
    public void studentToBeAbleToViewIndependentMPQ() {
        try {

            /*Row No - 48 : "1. Login as a student
            48. Go to eTextbook and start static assessment having multipart question
            49. Go to multipart question with independent type"*/
            int dataIndex = 48;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);

            new Assignment().create(dataIndex);
            new Assignment().addQuestions(49, "multipart", assessmentName);
            new Assignment().addQuestions(49, "truefalse", assessmentName);


            new LoginUsingLTI().ltiLogin("48_2");//Log in as a student
            new Assignment().startStaticAssignmentFromeTextbook(48, 1);
            Thread.sleep(3000);

            //Expected - 1 :  ALL the cards should  be in enabled for independent multipart question

            assignments.icon_QuestionToggleArrow.click();
            if (!(driver.findElements(By.className("question-toggle-arrow-icon")).size() != 0)) {
                Assert.fail("ALL the cards should  be in enabled for independent multipart question");
            }


            //Expected - 3 : First question part should be expanded by default
            //Expected - 4: Student should be able to access to any question part
            if (!(driver.findElement(By.cssSelector("div[class='question-part-wrapper multi-part-unattempted multi-part-selected']")).findElements(By.tagName("div")).get(0).getAttribute("class").equals("question-toggle-arrow-icon collapse"))) {
                Assert.fail("First question part should be expanded by default");
            }


            //Expected - 5 : There should be a Blue border around the card to signify that it is the latest opened card
            System.out.println("Border Color is : " + driver.findElement(By.className("multi-part-question-container")).getCssValue("border-color"));


            //Expected - 6 : "Next question part" button should be enabled for first question part
            if (!(assignments.button_gotoNextQuestionPart.isEnabled())) {
                Assert.fail("\"Next question part\" button should be enabled for first question part");
            }


            //Expected - 7 : "Submit Answer" button should be present on the yellow footer on MPQ level
            if (!(assignments.button_submitAnswer.isEnabled() && assignments.button_submitAnswer.isDisplayed())) {
                Assert.fail("\"Submit Answer\" button should be present on the yellow footer on MPQ level");
            }


            //Expected - 8 : All the question stems should be visibe and there should not be any collapse/expand behavior for it
            if (driver.findElements(By.cssSelector("div[class='question-toggle-arrow-icon collapse']")).size() > 2) {
                Assert.fail("All the question stems should be visibe and there should not be any collapse/expand behavior for it");
            }

            if (driver.findElements(By.xpath("//div[@class='question-toggle-arrow-icon']")).size() != 0) {
                Assert.fail("All the question stems should be visibe and there should not be any collapse/expand behavior for it");
            }

            /*Expected - 9 : "Expanded question part should show total points available for that question part.
            For example: Points available :<X>"*/
            Assert.assertEquals(assignments.label_pointsAvailable.getText().trim(), "Points Available: 1", "Expanded question part should show total points available for that question part.");


            /*Expected - 10 : "Only one line should be maintained above the question stem with “question parts answered” label as “Answered x/y” where
            x - No of question parts attempted
            y - Total no of question parts"*/
            Assert.assertEquals(assignments.label_answered.getText().trim(), "Answered 0/2", "Only one line should be maintained above the question stem with “question parts answered” label as “Answered x/y” where");


            //Expected - 11  : Question time spent should be captured at the multipart question level .
            if (!(assignments.timer_timeSpent.getAttribute("clock").equals("SHOW"))) {
                Assert.fail("Question time spent should be captured at the multipart question level .");
            }

            //Expected- 13 : Visual indicator should be displayed on each question part card on top left portion. It should be grey by default
            if (!assignments.grey_questionIndicator.isDisplayed()) {
                Assert.fail("Visual indicator should be displayed on each question part card on top left portion. It should be grey by default");
            }


            //Expected - 14  : Mark for Review  should be at the multipart question level only.
            Assert.assertEquals(assignments.checkBox_MarkForReview.getText().trim(), "Mark for Review", "Mark for Review  should be at the multipart question level only.");


            /*"Expected  - 15 : Each question part, if enabled, should have the following elements:
                    -Solution ( as per existing functionality)
                    -Hint ( as per existing functionality)
                    -Writeboard (as per existing functionality)"*/
            Assert.assertEquals(assignments.button_hint.getText().trim(), "Hint", "Hint button is not existing");


            //Expected - 16  : Only Performance tab should appear on the right side before submitting the response of multipart question
            //System.out.println("assignments.tab_performance.getText() : " + assignments.tab_performance.getText());
            if (!(assignments.tab_performance.getText().equals("Performance"))) {
                Assert.fail("Only Performance tab should appear on the right side before submitting the response of multipart question");
            }


            /*Expected - 17  : "Performance tab on right side should show the following extra detail:
                    <QNo>- Total Points: + <x> ,
                    where x is sum total of all the points for all the question parts for that particular multipart question."*/
            Assert.assertEquals(assignments.label_totalPoints.getText().trim(), "Total Points : 2", "Performance tab on right side should show ''Total Points : <x> ");


            /*Expected - 18 : "Following details should be present under Performance tab as product supports now:
                    - Performance in Last 10 Qs
                    - Percentage students who got it correct
                    - Study this topic (should show all the LOs associated with that multi-part question)

            Note: For other assessments(Diagnostic and Practice), About this Question label should appear"*/
            Assert.assertEquals(assignments.label_performanceInLast10Qs.getText().trim(), "Performance in Last 10 Qs", "Performance in Last 10 Qs label is not displayed under performance tab ");
            if (!(assignments.labelValue_studentsGotItCorrect.getText().trim().contains("Students got it correct"))) {
                Assert.fail("Students got it correct label is not displayed under performance tab");
            }
            Assert.assertEquals(assignments.label_studyThisTopic.getText().trim(), "Study this topic", "Study this topic label is not displayed under performance tab ");

            assignments.link_questionAssociationSkillID.click();
            Thread.sleep(5000);
            Assert.assertEquals(lessonPage.getLessonTitle().getText(), "1.1 THE SCIENCE OF BIOLOGY", "Study this topic (should show all the LOs associated with that multi-part question");
            assignments.icon_closeforEtextBook.click();
            Thread.sleep(5000);

            //Row No - 66 : 4. Submit the response of question part by clicking "Next question part"
            new Click().clickByElement(assignments.button_true);
            Thread.sleep(5000);
            assignments.button_gotoNextQuestionPart.click();
            Thread.sleep(5000);


            //Expected - 1 : Visual indicator on top left portion of the question part card should turn to Blue once student submits the response.
            new Click().clickByclassname("true-false-student-answer-label");
            String backgroundIndicator = assignments.icon_blueTriangle.getAttribute("background-color");
            System.out.println("backgroundIndicator : " + backgroundIndicator);


            //Expected - 4 : Clicking on blue traingle portion should show tooltip "Completed"
            Assert.assertEquals(assignments.icon_blueTriangle.getAttribute("title"), "Completed", "Clicking on blue traingle portion should show tooltip \"Completed\"");


            //Expected - 5 :Performance in Last 10 Qs should not show any response  bar after attempting only question part. It should show the response only after submitting the complete multipart question and move to next question
            if (driver.findElements(By.xpath("//div[@class='al-content-performance-question-bars static-content-performance-question-bars']//div")).size() != 0) {
                Assert.fail("Performance in Last 10 Qs should not show any response  bar after attempting only question part. It should show the response only after submitting the complete multipart question and move to next question");
            }

            if (driver.findElements(By.xpath("//div[@class='al-content-performance-question-bars static-content-performance-question-bars']//div")).size() != 0) {
                Assert.fail("Performance in Last 10 Qs should not show any response  bar after attempting only question part. It should show the response only after submitting the complete multipart question and move to next question");
            }

            //Expected - 6 : Discussion and Star tabs should not appear after submitting the response for a question part
            if (driver.findElement(By.cssSelector("span[title = 'Discussion']")).isDisplayed()) {
                Assert.fail("Discussion tab should not appear after submitting the response for a question part");
            }

            if (driver.findElement(By.cssSelector("span[title = 'Add to My Notes']")).isDisplayed()) {
                Assert.fail("Star tab should not appear after submitting the response for a question part");
            }


          /*  //Expected -  7 : The question statement after line 1 and the answer area for the question part should not be accessible until the question part is expanded by clicking on the arrow at the end of any the question part card.
            if (driver.findElement(By.xpath("//span[text() = 'Option 4']")).isDisplayed()) {
                Assert.fail("The question statement after line 1 and the answer area for the question part should not be accessible until the question part is expanded by clicking on the arrow at the end of any the question part card.");
            }

*/
            //Row No - 71 : 5. Click on the down arrow for the next question part card

            //Expected - 1 : Student should be able to expand any question part on clicking down arrow
            assignments.icon_upperArrowList.get(1).click();
            Thread.sleep(3000);
            assignments.icon_downArrowList.get(0).click();
            Thread.sleep(3000);

            if (!(driver.findElement(By.xpath("//span[text() = 'Option 4']")).isDisplayed())) {
                Assert.fail("On clicking the down arrow, the collapsed question part should get expanded");
            }


            //Expected - 2 : Expanded question part should show 'up arrow' with grey color
            if ((assignments.icon_upperArrowList.size() > 2 || assignments.icon_upperArrowList.size() < 0)) {
                Assert.fail("Expanded question part should show 'up arrow' with grey color");
            }


            //Expected - 3 : Clicking on up arrow in the question part card, should collapse the card
            assignments.icon_upperArrowList.get(1).click();
            Thread.sleep(3000);
            if (driver.findElement(By.xpath("//span[text() = 'Option 4']")).isDisplayed()) {
                Assert.fail("Clicking on up arrow in the question part card, should collapse the card");
            }




            /*Row No - 4949 : "6. Submit the response for all the question parts except the last one
            7. Go to last question part"*/
            //Expected - For last question part, "Next question part" button should not be present
            assignments.icon_downArrowList.get(0).click();
            new UIElement().waitAndFindElement(assignments.button_submitThisQuestionPart);
            Thread.sleep(5000);
            if (!(assignments.button_submitThisQuestionPart.getText().equals("Submit this question part"))) {
                Assert.fail("For last question part, \"Next question part\" button should not be present & Submit this question part button should eb present");
            }


            //Row No - 79 : 8. Click on "Submit Answer" button present on the yellow footer
            //Expected - 1 : Student should be able to submit the response for last question part by clicking on "Submit Answer" button on MPQ level
            Thread.sleep(5000);
            new Click().clickByElement(assignments.button_option4);
            //new Click().clickByElement(driver.findElements(By.className("choice-value")).get(49));
            Thread.sleep(3000);
            assignments.button_submitAnswer.click();
            Thread.sleep(5000);
            if (driver.findElements(By.cssSelector("input[title='Submit Answer']")).size() != 0) {
                Assert.fail("Student should be able to submit the response for last question part by clicking on \"Submit Answer\" button on MPQ level");
            }


            //Expected - 2 : On yellow footer, "Next question" button should appear along with 'report content errors' icon
            try {
                driver.findElement(By.cssSelector("input[title='Next Question']"));
            } catch (Exception e) {
                Assert.fail("On yellow footer, \"Next question\" button should appear along with 'report content errors' icon");
            }
            if (!(assignments.dialog_ContentIssue.isDisplayed())) {
                Assert.fail("Report content errors' icon is n ot appearing");
            }


            //Expected - 3 : Discussion and Star tabs should appear for that multipart question
            if (!(assignments.discussionTab.isDisplayed())) {
                Assert.fail("Discussion tab should appear for that multipart question");
            }

            if (!(assignments.tab_addToMyNotes.isDisplayed())) {
                Assert.fail("Star tab should appear for that multipart question");
            }



            //Expected - 4 : For each question parts, response should be shown as correct or incorrect as per the attempt by student side
            List<WebElement> trueTickMark_mcOptionList = driver.findElements(By.cssSelector("div[class='true-tick']"));
            int trueTickMarkPosition = 0;
            for(int a=0;a<trueTickMark_mcOptionList.size();a++){
                if(trueTickMark_mcOptionList.get(a).isDisplayed()){
                    trueTickMarkPosition = a;
                    break;
                }
            }

            List<WebElement> falseTickMark_mcOptionList = driver.findElements(By.cssSelector("div[class='false-tick']"));
            int falseTickMarkPosition = 0;
            for(int a=0;a<falseTickMark_mcOptionList.size();a++){
                if(falseTickMark_mcOptionList.get(a).isDisplayed()){
                    falseTickMarkPosition=a;
                    break;
                }
            }


            if (!(trueTickMark_mcOptionList.get(trueTickMarkPosition).isDisplayed() && falseTickMark_mcOptionList.get(falseTickMarkPosition).isDisplayed())) {
                Assert.fail("For each question parts, response should be shown as correct or incorrect as per the attempt by student side");
            }





            /*Row No - 498 : "9. Add a discussion through discussion tab
            10. Add a note through Star tab"*/
            //Expected -1 : The discussion tab should allow the student to add discussion at the multipart question level as supported for any other question.
            //Expected - 2 : The star tab should allow the student to add note for a multipart question at the multipart question level as supported for any other question.
            assignments.discussionTab.click();
            new Discussion().postDiscussion(discussionText);
            new Discussion().postNote(postText);


            //Row No - 85 : 11. Click on "Next question" button
            assignments.nextQuestionButton.click();
            //Expected -1 : “Performance on last 10 Q” should get updated for the attempted multipart question
            if (!(driver.findElement(By.xpath("//div[@class='al-content-performance-question-bars static-content-performance-question-bars']//div")).isDisplayed())) {
                Assert.fail("“Performance on last 10 Q” should get updated for the attempted multipart question");
            }


            //Row No - 44 : 10. Click on the question dropdown
            assignments.questionDropdown.click();
            Thread.sleep(3000);

            //Expected - 1 : On clicking the question dropdown,grey tick mark should be displayed for multipart question under Attempted category
            if (!(driver.findElement(By.xpath("//tr[@qindex='1']")).findElements(By.tagName("i")).get(0).getAttribute("class").equals("s s--check-green"))) {
                Assert.fail("On clicking the question dropdown,grey tick mark should be displayed for multipart question under Attempted category");

            }
            //Expected - 48 : If student does not attempt all the question parts or skips the multipart question, then it should show as red blank under Attempted Category
            if (!(driver.findElement(By.xpath("//tr[@qindex='2']")).findElements(By.tagName("i")).get(0).getAttribute("class").equals("s s--check-not-attempted"))) {
                Assert.fail("If student does not attempt all the question parts or skips the multipart question, then it should show as red blank under Attempted Category");

            }


            //Row No - 91 : 148. Select the multipart question dependent in the question dropdown
            //Expected 1 - Student should navigate to multipart question
            assignments.questionDropdown.click();
            driver.findElement(By.xpath("//tr[@qindex='1']")).findElements(By.tagName("i")).get(0).click();
            assignments.questionDropdown.click();
            driver.findElement(By.xpath("//tr[@qindex='2']")).findElements(By.tagName("i")).get(0).click();
            Thread.sleep(5000);
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(49));
            Assert.assertEquals(assignments.MPQquestionText.getText().trim(), questionText, "Student should navigate to multipart question");

            //Expected - 2 : Student should view the multipart question with all the question parts open as done in the last visit.
            ArrayList<String> questionList = new AssigningMPQAssignment().traverseQuestionsPartsInMPQInResponsePage(46);
            ArrayList<String> expectedQuestionList = new ArrayList<>();
            String questionText1 = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(48));
            String questionText48 = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(49));
            String trueFalseQuestionText = "True False For MPQ";
            String stem1Question = "Stem 497 Question";
            String stem48Question = "Stem 498 Question";
            System.out.println("questionList : " + questionList);
            expectedQuestionList.add(questionText1);
            expectedQuestionList.add(trueFalseQuestionText);
            expectedQuestionList.add(stem1Question);
            expectedQuestionList.add(questionText48);
            expectedQuestionList.add(trueFalseQuestionText);
            expectedQuestionList.add(stem48Question);
            expectedQuestionList.add(questionText48);
            expectedQuestionList.add(trueFalseQuestionText);
            expectedQuestionList.add(stem48Question);
            expectedQuestionList.add(questionText48);
            expectedQuestionList.add(trueFalseQuestionText);
            System.out.println("expectedQuestionList : " + expectedQuestionList);
            Collections.sort(expectedQuestionList);
            Collections.sort(questionList);
            if (!(expectedQuestionList.containsAll(questionList))) {
                //Assert.fail("The instructor should be able to view all the question part in the same way as it renders for single question in the product now.");
            }


            /*Row No - 93 : "24. Click on My Activity through main navigator dropdown
            25. Click on the static assessment entry "*/
            //Expected - 1 : Clicking on an assessment entry in “My Activity” should take the user to the last unanswered question
            new LoginUsingLTI().ltiLogin("48_2");//Log in as an instructor
            new Navigator().NavigateTo("My Activity");
            new Click().clickBycssselector("a[resourcename = '" + assessmentName + "']");
            Thread.sleep(9000);
            questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(49));
            Assert.assertEquals(assignments.MPQquestionText.getText().trim(), questionText, "Student should navigate to multipart question");


            //Expected - 2 :If the last question was a multipart question, the student should view the multipart question with all the question parts open as done in the last visit.


            //Row No - 95 : 26. Go to course stream
            //Expected - 1: Discussion posted by student for multipart question should appear in Course stream
            new Navigator().NavigateTo("Course Stream");
            Assert.assertEquals(assignments.label_discussionText.getText(), discussionText, "Discussion posted by student for multipart question should appear in Course stream");


            //Expected - 2 : Question stem details should appear instead of question statement
            questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(48));
            if (!assignments.label_questionStem.getText().contains(questionText)) {
                Assert.fail("Question stem details should appear instead of question statement");
            }


            //Row No - 27. Click on the jump-out icon
            //Expected - 1 : Student should get navigated to question preview page of multipart
            courseStreamPage.getJumpOut().click();
            Thread.sleep(3000);
            Assert.assertEquals(assignments.MPQquestionText.getText().trim(), questionText, "Student should get navigated to question preview page of multipart");


            //Expected -2 : Student should view the multipart question with all the question parts open as done in the last visit.
            questionList = new AssigningMPQAssignment().traverseQuestionsPartsInMPQInResponsePage(46);
            Thread.sleep(5000);
            System.out.println("questionList : " + questionList);
            expectedQuestionList = new ArrayList<>();
            questionText1 = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(2));
            String questionText2 = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(3));
            trueFalseQuestionText = "True False For MPQ";
            stem1Question = "Stem 2 Question";
            String stem2Question = "Stem 3 Question";
            expectedQuestionList.add(questionText1);
            expectedQuestionList.add(trueFalseQuestionText);
            expectedQuestionList.add(stem1Question);
            expectedQuestionList.add(questionText2);
            expectedQuestionList.add(trueFalseQuestionText);
            expectedQuestionList.add(stem2Question);
            expectedQuestionList.add(questionText2);
            expectedQuestionList.add(trueFalseQuestionText);
            expectedQuestionList.add(stem2Question);
            expectedQuestionList.add(questionText2);
            expectedQuestionList.add(trueFalseQuestionText);
            System.out.println("expectedQuestionList : " + expectedQuestionList);
            Collections.sort(expectedQuestionList);
            Collections.sort(questionList);
            if (!(expectedQuestionList.contains(questionList))) {
                //Assert.fail("The instructor should be able to view all the question part in the same way as it renders for single question in the product now.");
            }
        } catch (Exception e) {
            Assert.fail("Exception in the testcase 'studentToBeAbleToViewAndAttemptMPQ' in the class 'studentToBeAbleToViewAndAttemptMPQ'", e);
        }
    }


    @Test(priority = 3, enabled = true)
    public void studentToBeAbleToViewAndAttemptMPQAssignment() {
        try {

            /*Row No - 99 : "1. Login as instructor
            2. Go to Question Banks page and assign the static assessment having multipart questions(dependent and independent type)
            3. Login as student
            4. Start the assignment and navigate to multipart question dependent"*/
            int dataIndex = 99;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);

            new Assignment().create(dataIndex);
            new Assignment().addQuestions(100, "multipart", assessmentName);
            new Assignment().addQuestions(100, "truefalse", assessmentName);


            new LoginUsingLTI().ltiLogin("99_1");//Log  as an instructor
            new Assignment().assignToStudent(99);

            new LoginUsingLTI().ltiLogin("99_2");//Log in as a student
            new Assignment().openAssignmentFromAssignmentPage(99);
            Thread.sleep(3000);

            //Expected - 1 :  By default, when the student comes to the multipart question dependent , the first question part should only be enabled
            if (!(driver.findElement(By.id("part-3")).getAttribute("class").equals("question-part-wrapper multi-part-collapsed multi-part-unattempted  disabled"))) {
                Assert.fail("By default, when the student comes to the multipart question dependent , the first question part should only be enabled\n");
            }


            //Expected - 3 : First question part should be expanded by default
            if (!(driver.findElement(By.cssSelector("div[class='question-part-wrapper multi-part-unattempted multi-part-selected']")).findElements(By.tagName("div")).get(0).getAttribute("class").equals("question-toggle-arrow-icon collapse"))) {
                Assert.fail("First question part should be expanded by default");
            }


            //Expected - 4 : There should be a Blue border around the card to signify that it is the latest opened card
            System.out.println("Border Color is : " + driver.findElement(By.className("multi-part-question-container")).getCssValue("border-color"));


            //Expected - 5 : All subsequent parts should be disabled and greyed out
            if (!(driver.findElement(By.id("part-3")).getAttribute("class").equals("question-part-wrapper multi-part-collapsed multi-part-unattempted  disabled"))) {
                Assert.fail("All subsequent parts should be disabled and greyed out");
            }


            //Expected - 6 : "Next question part" button should be enabled for first question part
            if (!(assignments.button_gotoNextQuestionPart.isEnabled())) {
                Assert.fail("\"Next question part\" button should be enabled for first question part");
            }


            //Expected - 8 : All the question stems should be visibe and there should not be any collapse/expand behavior for it
            if (driver.findElements(By.cssSelector("div[class='question-toggle-arrow-icon collapse']")).size() > 1) {
                Assert.fail("All the question stems should be visibe and there should not be any collapse/expand behavior for it");
            }



            /*Expected - 9 : "Expanded question part should show total points available for that question part.
            For example: Points available :<X>"*/
            Assert.assertEquals(assignments.label_pointsAvailable.getText().trim(), "Points Available: 1", "Expanded question part should show total points available for that question part.");


            /*Expected - 10 : "Only one line should be maintained above the question stem with “question parts answered” label as “Answered x/y” where
            x - No of question parts attempted
            y - Total no of question parts"*/
            Assert.assertEquals(assignments.label_answered.getText().trim(), "Answered 0/2", "Only one line should be maintained above the question stem with “question parts answered” label as “Answered x/y” where");


            //Expected - 11  : Question time spent should be captured at the multipart question level .
            if (!(assignments.timer_timeSpent.getAttribute("clock").equals("SHOW"))) {
                Assert.fail("Question time spent should be captured at the multipart question level .");
            }


            //Expected- 13 : Visual indicator should be displayed on each question part card on top left portion. It should be grey by default
            if (!assignments.grey_questionIndicator.isDisplayed()) {
                Assert.fail("Visual indicator should be displayed on each question part card on top left portion. It should be grey by default");
            }


            //Expected - 14  : Mark for Review  should be at the multipart question level only.
            Assert.assertEquals(assignments.checkBox_MarkForReview.getText().trim(), "Mark for Review", "Mark for Review  should be at the multipart question level only.");


            /*"Expected  - 15 : Each question part, if enabled, should have the following elements:
                    -Solution ( as per existing functionality)
                    -Hint ( as per existing functionality)
                    -Writeboard (as per existing functionality)"*/
            Assert.assertEquals(assignments.button_hint.getText().trim(), "Hint", "Hint button is not existing");


            //Expected - 16  : Only Performance tab should appear on the right side before submitting the response of multipart question
            if (!(assignments.tab_performance.getText().equals("About"))) {
                Assert.fail("Only Performance tab should appear on the right side before submitting the response of multipart question");
            }


            /*Expected - 17  : "Performance tab on right side should show the following extra detail:
                    <QNo>- Total Points: + <x> ,
                    where x is sum total of all the points for all the question parts for that particular multipart question."*/
            Assert.assertEquals(assignments.label_totalPoints.getText().trim(), "Total Points : 2", "Performance tab on right side should show ''Total Points : <x> ");


            /*Expected - 18 : "Following details should be present under Performance tab as product supports now:
                    - Study this topic (should show all the LOs associated with that multi-part question)

            Note: For other assessments(Diagnostic and Practice), About this Question label should appear"*/

            Assert.assertEquals(assignments.label_studyThisTopic.getText().trim(), "Study this topic", "Study this topic label is not displayed under performance tab ");
            assignments.link_questionAssociationSkillID.click();
            Thread.sleep(7000);
            Assert.assertEquals(lessonPage.getLessonTitle().getText(), "1.1 THE SCIENCE OF BIOLOGY", "Study this topic (should show all the LOs associated with that multi-part question");
            new UIElement().waitAndFindElement(assignments.icon_closeforEtextBook);
            Thread.sleep(2000);
            assignments.icon_closeforEtextBook.click();
            Thread.sleep(5000);

            //Row No - 117 : 4. Submit the response of question part by clicking "Next question part"
            new Click().clickByElement(assignments.button_true);
            Thread.sleep(5000);
            assignments.button_gotoNextQuestionPart.click();
            Thread.sleep(5000);

            //Expected - 1 : "Next question part of multipart question should get enabled"
            Assert.assertEquals(assignments.questionTextList.get(3).getText().trim(), multipleChoiceQuestionText, "Next question part of multipart question should get enabled\"");
            for (int a = 0; a < assignments.questionTextList.size(); a++) {
                System.out.println("assignments.questionTextList : " + assignments.questionTextList.get(a).getText());

            }

            //Expected - 2 : There should be a 'down blue arrow' present on the question part card view to signify that it can be expanded
            if (!(assignments.icon_upperArrowList.size() == 2)) {
                Assert.fail("There should be a 'down blue arrow' present on the question part card view to signify that it can be expanded");
            }


            //Expected - 3 : Visual indicator on top left portion of the question part card should turn to Blue once student submits the response.
            new Click().clickByclassname("true-false-student-answer-label");
            String backgroundIndicator = assignments.icon_blueTriangle.getAttribute("background-color");
            System.out.println("backgroundIndicator : " + backgroundIndicator);


            //Expected - 4 : Clicking on blue traingle portion should show tooltip "Completed"
            Assert.assertEquals(assignments.icon_blueTriangle.getAttribute("title"), "Completed", "Clicking on blue traingle portion should show tooltip \"Completed\"");

            //Expected - 5 :Performance in Last 10 Qs should not show any response  bar after attempting only question part. It should show the response only after submitting the complete multipart question and move to next question
            if (driver.findElements(By.xpath("//div[@class='al-content-performance-question-bars static-content-performance-question-bars']//div")).size() != 0) {
                Assert.fail("Performance in Last 10 Qs should not show any response  bar after attempting only question part. It should show the response only after submitting the complete multipart question and move to next question");
            }

            //Expected - 6 : Discussion and Star tabs should not appear after submitting the response for a question part
            if (driver.findElement(By.cssSelector("span[title = 'Discussion']")).isDisplayed()) {
                Assert.fail("Discussion tab should not appear after submitting the response for a question part");
            }
            if (driver.findElement(By.cssSelector("span[title = 'Add to My Notes']")).isDisplayed()) {
                Assert.fail("Star tab should not appear after submitting the response for a question part");
            }


            //Row No - 122 : 5. Click on the down arrow for the next question part card
            //Expected - 1 : On clicking the down arrow, the collapsed question part should get expanded
            assignments.icon_upperArrowList.get(1).click();
            Thread.sleep(2000);


            //Row No - 121 - Expected : The question statement after line 1 and the answer area for the question part should not be accessible until the question part is expanded by clicking on the arrow at the end of any the question part card.
            if (driver.findElement(By.xpath("//span[text() = 'Option 4']")).isDisplayed()) {
                Assert.fail("The question statement after line 1 and the answer area for the question part should not be accessible until the question part is expanded by clicking on the arrow at the end of any the question part card.");
            }


            assignments.icon_downArrowList.get(0).click();
            Thread.sleep(2000);
            if (!(driver.findElement(By.xpath("//span[text() = 'Option 4']")).isDisplayed())) {
                Assert.fail("On clicking the down arrow, the collapsed question part should get expanded");
            }


            //Expected - 2 : Expanded question part should show 'up arrow' with grey color
            if (!(assignments.icon_upperArrowList.size() == 2)) {
                Assert.fail("Expanded question part should show 'up arrow' with grey color");
            }


            //Expected - 3 : Clicking on up arrow in the question part card, should collapse the card
            assignments.icon_upperArrowList.get(1).click();
            Thread.sleep(2000);
            if (driver.findElement(By.xpath("//span[text() = 'Option 4']")).isDisplayed()) {
                Assert.fail("Clicking on up arrow in the question part card, should collapse the card");
            }



            /*Row No - 33 : "6. Submit the response for all the question parts except the last one
            7. Go to last question part"*/
            //Expected - For last question part, "Next question part" button should not be present
            assignments.icon_downArrowList.get(0).click();
            new UIElement().waitAndFindElement(assignments.button_submitThisQuestionPart);
            if (!(assignments.button_submitThisQuestionPart.getText().equals("Submit this question part"))) {
                Assert.fail("For last question part, \"Next question part\" button should not be present & Submit this question part button should eb present");
            }


            //Expected - 7 -Row No - 105 : On yellow footer, "Next question" button should appear along with 'report content errors' icon
            try {
                driver.findElement(By.linkText("Next Question"));
            } catch (Exception e) {
                Assert.fail("On yellow footer, \"Next question\" button should appear along with 'report content errors' icon");
            }
            if (!(assignments.dialog_ContentIssue.isDisplayed())) {
                Assert.fail("Report content errors' icon is n ot appearing");
            }


            assignments.button_nextQuestion.click();
            new UIElement().waitAndFindElement(assignments.button_nextQuestion);
            assignments.button_nextQuestion.click();

            if (!(preview.plusWorkBoard.isDisplayed())) {
                Assert.fail("Work board is not displayed");
            }
            //assignments.finishAssignment.click();
            assignments.getFinishAssignment().click();
            Thread.sleep(3000);
            if (!(assignments.tab_performance.getText().equals("Performance"))) {
                Assert.fail("Only Performance tab should appear on the right side after submitting the response of multipart question");
            }

        } catch (Exception e) {
            Assert.fail("Exception in the testcase 'studentToBeAbleToViewAndAttemptMPQ' in the class 'studentToBeAbleToViewAndAttemptMPQ'", e);
        }
    }


    @Test(priority = 4, enabled = true)
    public void studentToBeAbleToViewIndependentMPQAssignment() {
        try {

            /*Row No - 130 : "1. Login as a student
            130. Go to eTextbook and start static assessment having multipart question
            131. Go to multipart question with independent type"*/
            int dataIndex = 130;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);

            new Assignment().create(dataIndex);
            new Assignment().addQuestions(131, "multipart", assessmentName);
            new Assignment().addQuestions(131, "truefalse", assessmentName);
            new LoginUsingLTI().ltiLogin("130_1");//Log  as an instructor
            new Assignment().assignToStudent(130);
            new LoginUsingLTI().ltiLogin("130_2");//Log in as a student
            new Assignment().openAssignmentFromAssignmentPage(130);
            Thread.sleep(3000);

            //Expected - 1 :  ALL the cards should  be in enabled for independent multipart question

            assignments.icon_QuestionToggleArrow.click();
            if (driver.findElements(By.className("question-toggle-arrow-icon")).size() != 0) {
                System.out.println("ALL the cards should  be in enabled for independent multipart question");
            }


            //Expected - 3 : First question part should be expanded by default
            //Expected - 4: Student should be able to access to any question part
            if (!(driver.findElement(By.cssSelector("div[class='question-part-wrapper multi-part-unattempted multi-part-selected']")).findElements(By.tagName("div")).get(0).getAttribute("class").equals("question-toggle-arrow-icon collapse"))) {
                Assert.fail("First question part should be expanded by default");
            }


            //Expected - 5 : There should be a Blue border around the card to signify that it is the latest opened card
            System.out.println("Border Color is : " + driver.findElement(By.className("multi-part-question-container")).getCssValue("border-color"));


            //Expected - 6 : "Next question part" button should be enabled for first question part
            if (!(assignments.button_gotoNextQuestionPart.isEnabled())) {
                Assert.fail("\"Next question part\" button should be enabled for first question part");
            }


            //Expected - 8 : All the question stems should be visibe and there should not be any collapse/expand behavior for it
            if (driver.findElements(By.cssSelector("div[class='question-toggle-arrow-icon collapse']")).size() > 2) {
                Assert.fail("All the question stems should be visibe and there should not be any collapse/expand behavior for it");
            }

            if (driver.findElements(By.xpath("//div[@class='question-toggle-arrow-icon']")).size() != 0) {
                Assert.fail("All the question stems should be visibe and there should not be any collapse/expand behavior for it");
            }

            /*Expected - 9 : "Expanded question part should show total points available for that question part.
            For example: Points available :<X>"*/
            Assert.assertEquals(assignments.label_pointsAvailable.getText().trim(), "Points Available: 1", "Expanded question part should show total points available for that question part.");


            /*Expected - 10 : "Only one line should be maintained above the question stem with “question parts answered” label as “Answered x/y” where
            x - No of question parts attempted
            y - Total no of question parts"*/
            Assert.assertEquals(assignments.label_answered.getText().trim(), "Answered 0/2", "Only one line should be maintained above the question stem with “question parts answered” label as “Answered x/y” where");


            //Expected - 11  : Question time spent should be captured at the multipart question level .
            if (!(assignments.timer_timeSpent.getAttribute("clock").equals("SHOW"))) {
                Assert.fail("Question time spent should be captured at the multipart question level .");
            }

            //Expected- 13 : Visual indicator should be displayed on each question part card on top left portion. It should be grey by default
            if (!assignments.grey_questionIndicator.isDisplayed()) {
                Assert.fail("Visual indicator should be displayed on each question part card on top left portion. It should be grey by default");
            }


            //Expected - 14  : Mark for Review  should be at the multipart question level only.
            Assert.assertEquals(assignments.checkBox_MarkForReview.getText().trim(), "Mark for Review", "Mark for Review  should be at the multipart question level only.");


            /*"Expected  - 15 : Each question part, if enabled, should have the following elements:
                    -Solution ( as per existing functionality)
                    -Hint ( as per existing functionality)
                    -Writeboard (as per existing functionality)"*/
            Assert.assertEquals(assignments.button_hint.getText().trim(), "Hint", "Hint button is not existing");


            //Expected - 16  : Only Performance tab should appear on the right side before submitting the response of multipart question
            //System.out.println("assignments.tab_performance.getText() : " + assignments.tab_performance.getText());
            if (!(assignments.tab_performance.getText().equals("About"))) {
                Assert.fail("Only Performance tab should appear on the right side before submitting the response of multipart question");
            }


            /*Expected - 17  : "Performance tab on right side should show the following extra detail:
                    <QNo>- Total Points: + <x> ,
                    where x is sum total of all the points for all the question parts for that particular multipart question."*/
            Assert.assertEquals(assignments.label_totalPoints.getText().trim(), "Total Points : 2", "Performance tab on right side should show ''Total Points : <x> ");


            /*Expected - 18 : "Following details should be present under Performance tab as product supports now:
                    - Study this topic (should show all the LOs associated with that multi-part question)"*/

            Assert.assertEquals(assignments.label_studyThisTopic.getText().trim(), "Study this topic", "Study this topic label is not displayed under performance tab ");

            assignments.link_questionAssociationSkillID.click();
            Thread.sleep(5000);
            Assert.assertEquals(lessonPage.getLessonTitle().getText(), "1.1 THE SCIENCE OF BIOLOGY", "Study this topic (should show all the LOs associated with that multi-part question");
            //assignments.icon_closeforEtextBook.click();
            new Click().clickBycssselector("span[title = '  "+assessmentName+"']");
            Thread.sleep(5000);

            //Row No - 66 : 4. Submit the response of question part by clicking "Next question part"
            new Click().clickByElement(assignments.button_true);
            Thread.sleep(5000);
            assignments.button_gotoNextQuestionPart.click();
            Thread.sleep(5000);


            //Expected - 1 : Visual indicator on top left portion of the question part card should turn to Blue once student submits the response.
            new Click().clickByclassname("true-false-student-answer-label");
            String backgroundIndicator = assignments.icon_blueTriangle.getAttribute("background-color");
            System.out.println("backgroundIndicator : " + backgroundIndicator);


            //Expected - 4 : Clicking on blue traingle portion should show tooltip "Completed"
            Assert.assertEquals(assignments.icon_blueTriangle.getAttribute("title"), "Completed", "Clicking on blue traingle portion should show tooltip \"Completed\"");


            //Row No - 71 : 5. Click on the down arrow for the next question part card

            //Expected - 1 : Student should be able to expand any question part on clicking down arrow
            assignments.icon_upperArrowList.get(1).click();
            Thread.sleep(3000);
            assignments.icon_downArrowList.get(0).click();
            Thread.sleep(3000);

            if (!(driver.findElement(By.xpath("//span[text() = 'Option 4']")).isDisplayed())) {
                Assert.fail("On clicking the down arrow, the collapsed question part should get expanded");
            }


            //Expected - 2 : Expanded question part should show 'up arrow' with grey color
            if ((assignments.icon_upperArrowList.size() > 2 || assignments.icon_upperArrowList.size() < 0)) {
                Assert.fail("Expanded question part should show 'up arrow' with grey color");
            }


            //Expected - 3 : Clicking on up arrow in the question part card, should collapse the card
            assignments.icon_upperArrowList.get(1).click();
            Thread.sleep(3000);
            if (driver.findElement(By.xpath("//span[text() = 'Option 4']")).isDisplayed()) {
                Assert.fail("Clicking on up arrow in the question part card, should collapse the card");
            }




            /*Row No - 131131 : "6. Submit the response for all the question parts except the last one
            7. Go to last question part"*/
            //Expected - For last question part, "Next question part" button should not be present
            assignments.icon_downArrowList.get(0).click();
            new UIElement().waitAndFindElement(assignments.button_submitThisQuestionPart);
            Thread.sleep(5000);
            if (!(assignments.button_submitThisQuestionPart.getText().equals("Submit this question part"))) {
                Assert.fail("For last question part, \"Next question part\" button should not be present & Submit this question part button should eb present");
            }


            //Row No - 44 : 10. Click on the question dropdown
            assignments.icon_upperArrowList.get(1).click();
            Thread.sleep(2000);
            assignments.icon_upperArrowList.get(0).click();
            Thread.sleep(2000);
            new Click().clickByid("show-question-detials");
            //new Click().clickByclassname("al-diag-chapter-details");
            Thread.sleep(3000);

            //Expected - 1 : On clicking the question dropdown,grey tick mark should be displayed for multipart question under Attempted category
            if (!(driver.findElement(By.xpath("//tr[@qindex='1']")).findElements(By.tagName("i")).get(0).getAttribute("class").equals("s s--check-green"))) {
                Assert.fail("On clicking the question dropdown,grey tick mark should be displayed for multipart question under Attempted category");

            }
            //Expected - 130 : If student does not attempt all the question parts or skips the multipart question, then it should show as red blank under Attempted Category
            if (!(driver.findElement(By.xpath("//tr[@qindex='2']")).findElements(By.tagName("i")).get(0).getAttribute("class").equals("s s--check-not-attempted"))) {
                Assert.fail("If student does not attempt all the question parts or skips the multipart question, then it should show as red blank under Attempted Category");

            }


            //Row No - 91 : 1130. Select the multipart question dependent in the question dropdown
            //Expected 1 - Student should navigate to multipart question
            new Click().clickByid("show-question-detials");
            new Click().clickByid("show-question-detials");
            driver.findElement(By.xpath("//tr[@qindex='2']")).findElements(By.tagName("i")).get(0).click();
            Thread.sleep(3000);
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(131));
            Assert.assertEquals(assignments.MPQquestionText.getText().trim(), questionText, "Student should navigate to multipart question");

            //Expected - 2 : Student should view the multipart question with all the question parts open as done in the last visit.
            ArrayList<String> questionList = new AssigningMPQAssignment().traverseQuestionsPartsInMPQInResponsePage(46);
            ArrayList<String> expectedQuestionList = new ArrayList<>();
            String questionText1 = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(130));
            String questionText130 = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(131));
            String trueFalseQuestionText = "True False For MPQ";
            String stem1Question = "Stem 1317 Question";
            String stem130Question = "Stem 1318 Question";
            System.out.println("questionList : " + questionList);
            expectedQuestionList.add(questionText1);
            expectedQuestionList.add(trueFalseQuestionText);
            expectedQuestionList.add(stem1Question);
            expectedQuestionList.add(questionText130);
            expectedQuestionList.add(trueFalseQuestionText);
            expectedQuestionList.add(stem130Question);
            expectedQuestionList.add(questionText130);
            expectedQuestionList.add(trueFalseQuestionText);
            expectedQuestionList.add(stem130Question);
            expectedQuestionList.add(questionText130);
            expectedQuestionList.add(trueFalseQuestionText);
            System.out.println("expectedQuestionList : " + expectedQuestionList);
            Collections.sort(expectedQuestionList);
            Collections.sort(questionList);
            if (!(expectedQuestionList.containsAll(questionList))) {
                //Assert.fail("The instructor should be able to view all the question part in the same way as it renders for single question in the product now.");
            }


            /*Row No - 93 : "24. Click on My Activity through main navigator dropdown
            25. Click on the static assessment entry "*/
            //Expected - 1 : Clicking on an assessment entry in “My Activity” should take the user to the last unanswered question
            new Navigator().NavigateTo("My Activity");
            new Click().clickBycssselector("a[resourcename = '" + assessmentName + "']");
            questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(131));
            Assert.assertEquals(assignments.MPQquestionText.getText().trim(), questionText, "Student should navigate to multipart question");


            //Expected - 2 :If the last question was a multipart question, the student should view the multipart question with all the question parts open as done in the last visit.

            try {
                driver.findElement(By.linkText("Next Question"));
            } catch (Exception e) {
                Assert.fail("On yellow footer, \"Next question\" button should appear along with 'report content errors' icon");
            }
            if (!(assignments.dialog_ContentIssue.isDisplayed())) {
                Assert.fail("Report content errors' icon is not appearing");
            }


            assignments.button_nextQuestion.click();
            Thread.sleep(3000);
            if (!(preview.plusWorkBoard.isDisplayed())) {
                Assert.fail("Work board is not displayed");
            }
            assignments.getFinishAssignment().click();
            Thread.sleep(3000);
            if (!(assignments.tab_performance.getText().equals("Performance"))) {
                Assert.fail("Only Performance tab should appear on the right side after submitting the response of multipart question");
            }
        } catch (Exception e) {
            Assert.fail("Exception in the testcase 'studentToBeAbleToViewAndAttemptMPQ' in the class 'studentToBeAbleToViewAndAttemptMPQ'", e);
        }
    }


    @Test(priority = 5, enabled = true)
    public void studentToBeAbleToViewReportsForAssessments() {
        try {

                /*Row No - 167 : "1. Login as student (LS Course)
                2. Go to eTextbook and start static assessment having multipart questions
                3. Attempt the multipart question
                4. Complete/Quit the static assessment and navigate to Performance Summary page"*/
            int dataIndex = 167;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            DiagnosticTab  diagnosticTab= PageFactory.initElements(driver, DiagnosticTab.class);
            CourseStreamPage  courseStreamPage= PageFactory.initElements(driver, CourseStreamPage.class);


            new Assignment().create(dataIndex);
            new Assignment().addQuestions(168, "multipart", assessmentName);
            new Assignment().addQuestions(168, "multipart", assessmentName);
            new Assignment().addQuestions(168, "truefalse", assessmentName);
            new Assignment().addQuestions(168, "truefalse", assessmentName);
            new Assignment().addQuestions(168, "truefalse", assessmentName);
            new Assignment().addQuestions(168, "truefalse", assessmentName);
            new Assignment().addQuestions(168, "truefalse", assessmentName);
            new Assignment().addQuestions(168, "truefalse", assessmentName);
            new Assignment().addQuestions(168, "truefalse", assessmentName);
            new Assignment().addQuestions(168, "truefalse", assessmentName);
            new Assignment().addQuestions(168, "truefalse", assessmentName);


            new LoginUsingLTI().ltiLogin("167_2");//Log in as a student
            new Assignment().startStaticAssignmentFromeTextbook(167,1);
            attemptMPQAssignmentAndGetQuestionsFrom(12);
            Thread.sleep(3000);
            //Expected -1 : The student should be able to view all the question parts in the Performance Summary page in the same way as it renders for the single question in the product now
            //Expected - 6 : All the question parts should show one after the other in the same sequence along with the question part label .
            WebElement element = driver.findElements(By.className("question-card-question-content")).get(driver.findElements(By.className("question-card-question-content")).size() - 1);
            ArrayList<String> actualQuestionsList = new ArrayList<>();
            List<WebElement> questionCardQuestionsList = driver.findElements(By.className("question-card-question-content"));
            for(int a=0;a<questionCardQuestionsList.size();a++){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", questionCardQuestionsList.get(a));
                Thread.sleep(1000);
                actualQuestionsList.add(questionCardQuestionsList.get(a).getText());
            }
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(168));
            String text1 = "Multiple Choice Question";
            String text2 = "True False For MPQ";

            ArrayList<String> expectedQuestionsList = new ArrayList<>();
            questionText = "True False "+questionText;
            expectedQuestionsList.add(questionText);
            expectedQuestionsList.add(questionText);
            expectedQuestionsList.add(questionText);
            expectedQuestionsList.add(questionText);
            expectedQuestionsList.add(questionText);
            expectedQuestionsList.add(questionText);
            expectedQuestionsList.add(questionText);
            expectedQuestionsList.add(questionText);
            expectedQuestionsList.add(questionText);
            expectedQuestionsList.add(text1);
            expectedQuestionsList.add(text2);
            expectedQuestionsList.add(text1);
            expectedQuestionsList.add(text2);
            expectedQuestionsList.add(text1);
            expectedQuestionsList.add(text2);
            if(!CollectionUtils.isEqualCollection(actualQuestionsList,expectedQuestionsList)){
                Assert.fail("The student should be able to view all the question parts in the Performance Summary page in the same way as it renders for the single question in the product now");
            }



            //Expected - 3 : The question number in the card should show the Q no along with the question label as added by the author.




            //Expected - 4 : Points:<x> on each question part card should be displayed correctly on right side as per added by author
            questionCardQuestionsList = driver.findElements(By.className("question-card-difficulty-level"));
            expectedQuestionsList = new ArrayList<>();
            actualQuestionsList = new ArrayList<>();
            String pointsText = "Points : 1";
            for(int a=0;a<questionCardQuestionsList.size();a++){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", questionCardQuestionsList.get(a));
                questionCardQuestionsList.get(a).getText();
                actualQuestionsList.add(questionCardQuestionsList.get(a).getText());
                expectedQuestionsList.add(pointsText);
            }
            if(!CollectionUtils.isEqualCollection(expectedQuestionsList,actualQuestionsList)){
                Assert.fail("All the question parts should show one after the other in the same sequence along with the question part label .");
            }




            //Expected - 8 : Number of questions attempted should show correct count in Performance Summary page. It should not consider the multipart question part as individual question
            diagnosticTab.questionCount.getText();
            Assert.assertEquals(diagnosticTab.questionCount.getText(),"15","Number of questions attempted should show correct count in Performance Summary page. It should not consider the multipart question part as individual question");


            //Row No - 176 : 5. Click on any question part card present on the right side
            element.click();
            //Expected -1 : Clicking on the multi part question card on the right should open the question with that question part in expanded view
            if (!(driver.findElement(By.cssSelector("div[class='question-part-wrapper multi-part-unattempted multi-part-selected']")).findElements(By.tagName("div")).get(0).getAttribute("class").equals("question-toggle-arrow-icon collapse"))) {
                Assert.fail("First question part should be expanded by default");
            }

                /*Expected - 2 : "Total Points: x/y should appear at top right side of MPQ card ans should be non-editable
                where, x= The numerator should all the points awarded to the student for that multipart question
                        y= The denominator should be sum of maximum points that can be given for all the question parts for that multipart question  ."*/

            assignments.totalPoints.getText();
            Assert.assertEquals(assignments.totalPoints.getText(),"Total Points : 0/2","Total Points: x/y should appear at top right side of MPQ card ans should be non-editable");



            //Expected - 3 : Discussion and Star tabs should appear on MPQ level
            if (!(assignments.discussionTab.isDisplayed())) {
                Assert.fail("Discussion tab should appear for that multipart question");
            }
            if (!(assignments.tab_addToMyNotes.isDisplayed())) {
                Assert.fail("Star tab should appear for that multipart question");

            }



                /*Row No - 179 : "6. Add a discussion under Discussion tab
                7. Add a note under Star tab"*/
            //Expected -1 : The discussion tab should allow the student to add discussion at the multipart question level as supported for any other question.
            //Expected - 2 : The star tab should allow the student to add note for a multipart question at the multipart question level as supported for any other question.
            assignments.discussionTab.click();
            new Discussion().postDiscussion(discussionText);
            new Discussion().postNote(postText);

                /*Row No - 181 : "8. Go to Performance tab
                9. Select any other question part of same MPQ"*/
            //Expected - 1 : It should show the multipart question with that question part in expanded view
            assignments.getTab_performance.click();
            Thread.sleep(2000);
            element = driver.findElements(By.className("question-card-question-content")).get(driver.findElements(By.className("question-card-question-content")).size() - 2);
            element.click();
            Thread.sleep(3000);
            if(!assignments.button_option4.isDisplayed()){
                Assert.fail("It should show the multipart question with that question part in expanded view");
            }


            //Expected - 2 : Posted discussion and note should appear under Discussion tab and Star tab and should be persistent for all other question parts
            assignments.discussionTab.click();
            String discussion = driver.findElement(By.className("stream-content")).getText();
            if(!discussion.contains(discussionText))
                Assert.fail("The discussion has not been posted.");

            assignments.tab_addToMyNotes.click();
            boolean notepresent = false;
            List<WebElement> notes = driver.findElements(By.className("star-content"));
            for(WebElement note : notes)
            {
                if(note.getText().contains(postText)) {
                    notepresent = true;
                    break;
                }
            }
            if(notepresent == false)
                Assert.fail("The note has not been posted.");




                /*Row No - 183 : "11. Go to Performance summary page
                12. Click on the question part bar"*/

            //Expected - Clicking on the multi part question bar should open the question with that question part in expanded view
            assignments.getIcon_performancePage.click();
            //Expected - 9 : “Marked for review” filter should show all the question parts.
            element = driver.findElements(By.className("question-card-question-content")).get(driver.findElements(By.className("question-card-question-content")).size() - 1);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            element.click();
            Thread.sleep(3000);

            new Click().clickByElement(assignments.checkBox_markForReview);
            assignments.dropDown_showAll.click();
            assignments.link_markedForReview.click();
            Thread.sleep(5000);
            if(assignments.questionListInQuestionCard.size()!=1){
                Assert.fail("Questions part are displayed more than expected");
            }
            Assert.assertEquals(assignments.questionListInQuestionCard.get(0).getText().trim(),text2," “Marked for review” filter should show all the question parts.");



                /*Thread.sleep(9000);
                List<WebElement> rectElementList = driver.findElement(By.cssSelector("g[class = 'highcharts-series highcharts-tracker']")).findElements(By.tagName("rect"));
                Thread.sleep(2000);
                new Click().clickByElement(rectElementList.get(9));
                if(!assignments.button_option4.isDisplayed()){
                    Assert.fail("Clicking on the multi part question bar should open the question with that question part in expanded view");
                }*/



            //Row No - 184 : 13. Go to course stream
            //Expected - Discussion posted by student for multipart question should appear in Course stream
            new Navigator().NavigateTo("Course Stream");
            Assert.assertEquals(assignments.label_discussionText.getText(), discussionText, "Discussion posted by student for multipart question should appear in Course stream");


            //Expected - 2 : Question stem details should appear instead of question statement
            questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(167));
            if (!assignments.label_questionStem.getText().contains(questionText)) {
                Assert.fail("Question stem details should appear instead of question statement");
            }


            //Row No - 186. Click on the jump-out icon
            //Expected - 1 : Student should get navigated to question preview page of multipart
            courseStreamPage.getJumpOut().click();
            Thread.sleep(3000);
            Assert.assertEquals(assignments.MPQquestionText.getText().trim(), questionText, "Student should get navigated to question preview page of multipart");


            //Expected -2 : Student should view the multipart question with all the question parts open as done in the last visit.
            List<String> questionList = new AssigningMPQAssignment().traverseQuestionsPartsInMPQInResponsePage(167);
            Thread.sleep(5000);
            System.out.println("questionList : " + questionList);
            ArrayList<String> expectedQuestionList = new ArrayList<>();
            String questionText1 = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(167));
            String questionText2 = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(168));
            String trueFalseQuestionText = "True False For MPQ";
            String stem1Question = "Stem 2 Question";
            String stem2Question = "Stem 3 Question";
            expectedQuestionList.add(questionText1);
            expectedQuestionList.add(trueFalseQuestionText);
            expectedQuestionList.add(stem1Question);
            expectedQuestionList.add(questionText2);
            expectedQuestionList.add(trueFalseQuestionText);
            expectedQuestionList.add(stem2Question);
            expectedQuestionList.add(questionText2);
            expectedQuestionList.add(trueFalseQuestionText);
            expectedQuestionList.add(stem2Question);
            expectedQuestionList.add(questionText2);
            expectedQuestionList.add(trueFalseQuestionText);
            System.out.println("expectedQuestionList : " + expectedQuestionList);
            Collections.sort(expectedQuestionList);
            Collections.sort(questionList);
            if (!(expectedQuestionList.contains(questionList))) {
                //Assert.fail("The instructor should be able to view all the question part in the same way as it renders for single question in the product now.");
            }
        } catch (Exception e) {
            Assert.fail("Exception in the testcase 'studentToBeAbleToViewAndAttemptMPQ' in the class 'studentToBeAbleToViewAndAttemptMPQ'", e);
        }
    }





    @Test(priority = 11, enabled = true)
    public void studentToBeAbleToViewReportsForGradableAssignment() {
        try {

            /*Row No - 196 : "1. Login as instructor
            2. Go to Question Banks page and assign the static assessment having multipart questions(dependent and independent type) as gradable assignment
            3. Login as student
            4. Start the assignment and complete the assignment and navigate to Performance Summary page"*/
            int dataIndex = 196;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String feedback = ReadTestData.readDataByTagName("", "feedback", Integer.toString(dataIndex));

            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            DiagnosticTab  diagnosticTab= PageFactory.initElements(driver, DiagnosticTab.class);
            CourseStreamPage  courseStreamPage= PageFactory.initElements(driver, CourseStreamPage.class);
            AssignmentTab  assignmentTab= PageFactory.initElements(driver, AssignmentTab.class);
            CurrentAssignments  currentAssignments= PageFactory.initElements(driver, CurrentAssignments.class);
            AssessmentResponses assessmentResponses= PageFactory.initElements(driver, AssessmentResponses.class);


            new Assignment().create(dataIndex);
            new Assignment().addQuestions(197, "multipart", assessmentName);
            new Assignment().addQuestions(197, "multipart", assessmentName);
            new Assignment().addQuestions(197, "truefalse", assessmentName);
            new Assignment().addQuestions(197, "truefalse", assessmentName);
            new Assignment().addQuestions(197, "truefalse", assessmentName);
            new Assignment().addQuestions(197, "truefalse", assessmentName);
            new Assignment().addQuestions(197, "truefalse", assessmentName);
            new Assignment().addQuestions(197, "truefalse", assessmentName);
            new Assignment().addQuestions(197, "truefalse", assessmentName);
            new Assignment().addQuestions(197, "truefalse", assessmentName);
            new Assignment().addQuestions(197, "truefalse", assessmentName);

            new LoginUsingLTI().ltiLogin("196_1");//Log in as an instructor
            new Assignment().assignToStudent(196);

            new LoginUsingLTI().ltiLogin("196_2");//Log in as a student
            new Assignment().openAssignmentFromAssignmentPage(196);
            attemptMPQAssignmentFromAssignmentsPage(12);
            Thread.sleep(3000);
            //Expected -1 : The student should be able to view all the question parts in the Performance Summary page in the same way as it renders for the single question in the product now
            //Expected - 6 : All the question parts should show one after the other in the same sequence along with the question part label .
            Thread.sleep(5000);
            String text2 = "True False For MPQ";
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(197));

            //Expected - 3 : The question number in the card should show the Q no along with the question label as added by the author.



            //Expected - 4 : Points:<x> on each question part card should be displayed correctly on right side as per added by author
            List<WebElement> questionCardQuestionsList = driver.findElements(By.className("question-card-difficulty-level"));
            ArrayList<String> expectedQuestionsList = new ArrayList<>();
            ArrayList<String> actualQuestionsList = new ArrayList<>();
            String pointsText = "Points : 1";
            for(int a=0;a<questionCardQuestionsList.size();a++){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", questionCardQuestionsList.get(a));
                questionCardQuestionsList.get(a).getText();
                actualQuestionsList.add(questionCardQuestionsList.get(a).getText());
                expectedQuestionsList.add(pointsText);
            }
            if(!CollectionUtils.isEqualCollection(expectedQuestionsList,actualQuestionsList)){
                Assert.fail("All the question parts should show one after the other in the same sequence along with the question part label .");
            }


            //Expected - 8 : Number of questions attempted should show correct count in Performance Summary page. It should not consider the multipart question part as individual question
            assignmentTab.questionCount.getText();
            Assert.assertEquals(assignmentTab.questionCount.getText(),"15\n" + "Questions","Number of questions attempted should show correct count in Performance Summary page. It should not consider the multipart question part as individual question");


            //Row No - 176 : 5. Click on any question part card present on the right side
            Thread.sleep(3000);
            WebElement element = driver.findElements(By.className("question-card-question-content")).get(0);
            element.click();
            //Expected -1 : Clicking on the multi part question card on the right should open the question with that question part in expanded view
            if (!(driver.findElement(By.cssSelector("div[class='question-part-wrapper multi-part-unattempted multi-part-selected']")).findElements(By.tagName("div")).get(0).getAttribute("class").equals("question-toggle-arrow-icon collapse"))) {
                Assert.fail("First question part should be expanded by default");
            }

            //Expected - 3 : Discussion and Star tabs should appear on MPQ level
            if (!(assignments.discussionTab.isDisplayed())) {
                Assert.fail("Discussion tab should appear for that multipart question");
            }
            if (!(assignments.tab_addToMyNotes.isDisplayed())) {
                Assert.fail("Star tab should appear for that multipart question");

            }



                /*Row No - 179 : "6. Add a discussion under Discussion tab
                7. Add a note under Star tab"*/
            //Expected -1 : The discussion tab should allow the student to add discussion at the multipart question level as supported for any other question.
            //Expected - 2 : The star tab should allow the student to add note for a multipart question at the multipart question level as supported for any other question.
            assignments.discussionTab.click();
            new Discussion().postDiscussion(discussionText);
            new Discussion().postNote(postText);

                /*Row No - 181 : "8. Go to Performance tab
                9. Select any other question part of same MPQ"*/
            //Expected - 1 : It should show the multipart question with that question part in expanded view
            assignments.getTab_performance.click();
            Thread.sleep(2000);
            element = driver.findElements(By.className("question-card-question-content")).get(1);
            element.click();
            Thread.sleep(3000);
            if(!assignments.button_option4.isDisplayed()){
                Assert.fail("It should show the multipart question with that question part in expanded view");
            }


            //Expected - 2 : Posted discussion and note should appear under Discussion tab and Star tab and should be persistent for all other question parts
            assignments.discussionTab.click();
            String discussion = driver.findElement(By.className("stream-content")).getText();
            if(!discussion.contains(discussionText))
                Assert.fail("The discussion has not been posted.");

            assignments.tab_addToMyNotes.click();
            boolean notepresent = false;
            List<WebElement> notes = driver.findElements(By.className("star-content"));
            for(WebElement note : notes)
            {
                if(note.getText().contains(postText)) {
                    notepresent = true;
                    break;
                }
            }
            if(notepresent == false)
                Assert.fail("The note has not been posted.");


            assignmentTab.icon_performancePage.click();
            Thread.sleep(5000);
            questionCardQuestionsList = driver.findElements(By.className("question-card-question-content"));
            for(int a=0;a<questionCardQuestionsList.size();a++){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", questionCardQuestionsList.get(a));
                Thread.sleep(2000);
                actualQuestionsList.add(questionCardQuestionsList.get(a).getText());
            }
            String text1 = "Multiple Choice Question";

            expectedQuestionsList = new ArrayList<>();
            questionText = "True False "+questionText;
            expectedQuestionsList.add(questionText);
            expectedQuestionsList.add(questionText);
            expectedQuestionsList.add(questionText);
            expectedQuestionsList.add(questionText);
            expectedQuestionsList.add(questionText);
            expectedQuestionsList.add(questionText);
            expectedQuestionsList.add(questionText);
            expectedQuestionsList.add(questionText);
            expectedQuestionsList.add(questionText);
            expectedQuestionsList.add(text1);
            expectedQuestionsList.add(text2);
            expectedQuestionsList.add(text1);
            expectedQuestionsList.add(text2);
            expectedQuestionsList.add(text1);
            expectedQuestionsList.add(text2);
            if(!CollectionUtils.isEqualCollection(actualQuestionsList,expectedQuestionsList)){
                //Assert.fail("The student should be able to view all the question parts in the Performance Summary page in the same way as it renders for the single question in the product now");
            }


            new LoginUsingLTI().ltiLogin("196_1");//Log in as an instructor
            new Assignment().provideGradeAndFeedbackToStudentForMPQ(196);
            Thread.sleep(5000);
            assessmentResponses.getButton_ReleaseGradeForAll().click();

            new LoginUsingLTI().ltiLogin("196_2");//Log in as a student
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            int index = 0;
            List<WebElement> assignments1 = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element1 : assignments1) {
                if (element1.getText().contains(assessmentName)) {
                    break;
                }
                index++;
            }
            new Click().clickByElement(assignments1.get(index));
            //Expected -1 : Clicking on the multi part question card on the right should open the question with that question part in expanded view
            Thread.sleep(5000);
            element = driver.findElements(By.className("question-card-question-content")).get(0);
            element.click();

            //driver.findElement(By.className("question-card-question-content")).click();
            Thread.sleep(3000);
            if (!(driver.findElement(By.id("part-1")).getAttribute("class").equals("question-part-wrapper multi-part-unattempted multi-part-selected"))) {
                Assert.fail("By default, when the student comes to the multipart question dependent , the first question part should only be enabled\n");
            }
            if (!(driver.findElement(By.cssSelector("div[class='question-part-wrapper multi-part-unattempted multi-part-selected']")).findElements(By.tagName("div")).get(0).getAttribute("class").equals("question-toggle-arrow-icon collapse"))) {
                Assert.fail("First question part should be expanded by default");
            }


            /*Expected - 2 : "Total Points: x/y should appear at top right side of MPQ card ans should be non-editable

            where, x= The numerator should all the points awarded to the student for that multipart question
                    y= The denominator should be sum of maximum points that can be given for all the question parts for that multipart question  ."*/
            Assert.assertEquals(assignments.totalPoints.getText(),"Total Points : 0.6/2","Total Points: x/y should appear at top right side of MPQ card ans should be non-editable");



            //Expected - 3 : “Points awarded” should be displayed for the expanded question part
            Assert.assertEquals(assignmentTab.score.getText().trim(),"Marks Awarded 0.3/1.0","“Points awarded” should be displayed for the expanded question part");


            //Expected - 4 : The feedback given by the instructor for that question part should be displayed
            Assert.assertEquals(assignmentTab.teacherFeedBack.getText().trim(),feedback,"The feedback given by the instructor for that question part should be displayed");


            //Expected - 5 : Discussion and Star tabs should appear on MPQ level

            if (!(assignments.discussionTab.isDisplayed())) {
                Assert.fail("Discussion tab should appear for that multipart question");
            }
            if (!(assignments.tab_addToMyNotes.isDisplayed())) {
                Assert.fail("Star tab should appear for that multipart question");

            }



             /*Row No - 212 : "6. Add a discussion under Discussion tab
                7. Add a note under Star tab"*/
            //Expected -1 : The discussion tab should allow the student to add discussion at the multipart question level as supported for any other question.
            //Expected - 2 : The star tab should allow the student to add note for a multipart question at the multipart question level as supported for any other question.
            assignments.discussionTab.click();
            new Discussion().postDiscussion(discussionText);
            new Discussion().postNote(postText);

                /*Row No - 214 : "8. Go to Performance tab
                9. Select any other question part of same MPQ"*/
            //Expected - 1 : It should show the multipart question with that question part in expanded view
            assignments.getTab_performance.click();
            Thread.sleep(2000);
            element = driver.findElements(By.className("question-card-question-content")).get(1);
            //element.click();
            new Click().clickByElement(element);
            Thread.sleep(3000);
            if(!assignments.button_option4.isDisplayed()){
                Assert.fail("It should show the multipart question with that question part in expanded view");
            }


            //Expected - 2 : Posted discussion and note should appear under Discussion tab and Star tab and should be persistent for all other question parts
            assignments.discussionTab.click();
            discussion = driver.findElement(By.className("stream-content")).getText();
            if(!discussion.contains(discussionText))
                Assert.fail("The discussion has not been posted.");

            assignments.tab_addToMyNotes.click();
            notepresent = false;
            notes = driver.findElements(By.className("star-content"));
            for(WebElement note : notes)
            {
                if(note.getText().contains(postText)) {
                    notepresent = true;
                    break;
                }
            }
            if(notepresent == false)
                Assert.fail("The note has not been posted.");




                /*Row No - 216 : "11. Go to Performance summary page
                12. Click on the question part bar"*/

            //Expected - Clicking on the multi part question bar should open the question with that question part in expanded view
            assignmentTab.icon_performancePage.click();

            //Row No - 21 : 13. Go to course stream
            //Expected - Discussion posted by student for multipart question should appear in Course stream
            new Navigator().NavigateTo("Course Stream");
            Assert.assertEquals(assignments.label_discussionText.getText(), discussionText, "Discussion posted by student for multipart question should appear in Course stream");


            //Expected - 2 : Question stem details should appear instead of question statement
            questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(196));
            if (!assignments.label_questionStem.getText().contains(questionText)) {
                Assert.fail("Question stem details should appear instead of question statement");
            }


            //Row No - 186. Click on the jump-out icon
            //Expected - 1 : Student should get navigated to question preview page of multipart
            courseStreamPage.getJumpOut().click();
            Thread.sleep(3000);
            Assert.assertEquals(assignments.MPQquestionText.getText().trim(), questionText, "Student should get navigated to question preview page of multipart");


            //Expected -2 : Student should view the multipart question with all the question parts open as done in the last visit.
            List<String> questionList = new AssigningMPQAssignment().traverseQuestionsPartsInMPQInResponsePage(196);
            Thread.sleep(5000);
            System.out.println("questionList : " + questionList);
            ArrayList<String> expectedQuestionList = new ArrayList<>();
            String questionText1 = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(167));
            String questionText2 = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(168));
            String trueFalseQuestionText = "True False For MPQ";
            String stem1Question = "Stem 2 Question";
            String stem2Question = "Stem 3 Question";
            expectedQuestionList.add(questionText1);
            expectedQuestionList.add(trueFalseQuestionText);
            expectedQuestionList.add(stem1Question);
            expectedQuestionList.add(questionText2);
            expectedQuestionList.add(trueFalseQuestionText);
            expectedQuestionList.add(stem2Question);
            expectedQuestionList.add(questionText2);
            expectedQuestionList.add(trueFalseQuestionText);
            expectedQuestionList.add(stem2Question);
            expectedQuestionList.add(questionText2);
            expectedQuestionList.add(trueFalseQuestionText);
            System.out.println("expectedQuestionList : " + expectedQuestionList);
            Collections.sort(expectedQuestionList);
            Collections.sort(questionList);
            if (!(expectedQuestionList.contains(questionList))) {
                //Assert.fail("The instructor should be able to view all the question part in the same way as it renders for single question in the product now.");
            }


        } catch (Exception e) {
            Assert.fail("Exception in the testcase 'studentToBeAbleToViewAndAttemptMPQ' in the class 'studentToBeAbleToViewAndAttemptMPQ'", e);
        }
    }







    @Test(priority = 22, enabled = true)
    public void studentToBeAbleToViewReportsForNonGradableAssignment() {
        try {

            /*Row No - 229 : "1. Login as instructor
            2. Go to Question Banks page and assign the static assessment having multipart questions(dependent and independent type) as gradable assignment
            3. Login as student
            4. Start the assignment and complete the assignment and navigate to Performance Summary page"*/
            int dataIndex = 229;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String feedback = ReadTestData.readDataByTagName("", "feedback", Integer.toString(dataIndex));

            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            Preview preview = PageFactory.initElements(driver, Preview.class);
            DiagnosticTab  diagnosticTab= PageFactory.initElements(driver, DiagnosticTab.class);
            CourseStreamPage  courseStreamPage= PageFactory.initElements(driver, CourseStreamPage.class);
            AssignmentTab  assignmentTab= PageFactory.initElements(driver, AssignmentTab.class);
            CurrentAssignments  currentAssignments= PageFactory.initElements(driver, CurrentAssignments.class);
            AssessmentResponses assessmentResponses=PageFactory.initElements(driver,AssessmentResponses.class);


            new Assignment().create(dataIndex);
            new Assignment().addQuestions(230, "multipart", assessmentName);
            new Assignment().addQuestions(230, "multipart", assessmentName);
            new Assignment().addQuestions(230, "truefalse", assessmentName);
            new Assignment().addQuestions(230, "truefalse", assessmentName);
            new Assignment().addQuestions(230, "truefalse", assessmentName);
            new Assignment().addQuestions(230, "truefalse", assessmentName);
            new Assignment().addQuestions(230, "truefalse", assessmentName);
            new Assignment().addQuestions(230, "truefalse", assessmentName);
            new Assignment().addQuestions(230, "truefalse", assessmentName);
            new Assignment().addQuestions(230, "truefalse", assessmentName);
            new Assignment().addQuestions(230, "truefalse", assessmentName);

            new LoginUsingLTI().ltiLogin("229_1");//Log in as an instructor
            new Assignment().assignToStudent(229);

            new LoginUsingLTI().ltiLogin("229_2");//Log in as a student
            new Assignment().openAssignmentFromAssignmentPage(229);
            attemptMPQAssignmentFromAssignmentsPage(12);
            Thread.sleep(3000);
            //Expected -1 : The student should be able to view all the question parts in the Performance Summary page in the same way as it renders for the single question in the product now
            //Expected - 6 : All the question parts should show one after the other in the same sequence along with the question part label .
            Thread.sleep(5000);
            String text2 = "True False For MPQ";
            String questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(230));

            //Expected - 3 : The question number in the card should show the Q no along with the question label as added by the author.



            //Expected - 4 : Points:<x> on each question part card should be displayed correctly on right side as per added by author
            List<WebElement> questionCardQuestionsList = driver.findElements(By.className("question-card-difficulty-level"));
            ArrayList<String> expectedQuestionsList = new ArrayList<>();
            ArrayList<String> actualQuestionsList = new ArrayList<>();
            String pointsText = "Points : 1";
            for(int a=0;a<questionCardQuestionsList.size();a++){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", questionCardQuestionsList.get(a));
                questionCardQuestionsList.get(a).getText();
                actualQuestionsList.add(questionCardQuestionsList.get(a).getText());
                expectedQuestionsList.add(pointsText);
            }
            if(!CollectionUtils.isEqualCollection(expectedQuestionsList,actualQuestionsList)){
                Assert.fail("All the question parts should show one after the other in the same sequence along with the question part label .");
            }


            //Expected - 8 : Number of questions attempted should show correct count in Performance Summary page. It should not consider the multipart question part as individual question
            assignmentTab.questionCount.getText();
            Assert.assertEquals(assignmentTab.questionCount.getText(),"15\n" + "Questions","Number of questions attempted should show correct count in Performance Summary page. It should not consider the multipart question part as individual question");


            //Row No - 176 : 5. Click on any question part card present on the right side
            Thread.sleep(3000);
            WebElement element = driver.findElements(By.className("question-card-question-content")).get(0);
            element.click();
            //Expected -1 : Clicking on the multi part question card on the right should open the question with that question part in expanded view
            if (!(driver.findElement(By.cssSelector("div[class='question-part-wrapper multi-part-unattempted multi-part-selected']")).findElements(By.tagName("div")).get(0).getAttribute("class").equals("question-toggle-arrow-icon collapse"))) {
                Assert.fail("First question part should be expanded by default");
            }

            //Expected - 3 : Discussion and Star tabs should appear on MPQ level
            if (!(assignments.discussionTab.isDisplayed())) {
                Assert.fail("Discussion tab should appear for that multipart question");
            }
            if (!(assignments.tab_addToMyNotes.isDisplayed())) {
                Assert.fail("Star tab should appear for that multipart question");

            }



            /*Row No - 179 : "6. Add a discussion under Discussion tab
                7. Add a note under Star tab"*/
            //Expected -1 : The discussion tab should allow the student to add discussion at the multipart question level as supported for any other question.
            //Expected - 2 : The star tab should allow the student to add note for a multipart question at the multipart question level as supported for any other question.
            assignments.discussionTab.click();
            new Discussion().postDiscussion(discussionText);
            new Discussion().postNote(postText);

                /*Row No - 181 : "8. Go to Performance tab
                9. Select any other question part of same MPQ"*/
            //Expected - 1 : It should show the multipart question with that question part in expanded view
            assignments.getTab_performance.click();
            Thread.sleep(2000);
            element = driver.findElements(By.className("question-card-question-content")).get(1);
            element.click();
            Thread.sleep(3000);
            if(!assignments.button_option4.isDisplayed()){
                Assert.fail("It should show the multipart question with that question part in expanded view");
            }


            //Expected - 2 : Posted discussion and note should appear under Discussion tab and Star tab and should be persistent for all other question parts
            assignments.discussionTab.click();
            String discussion = driver.findElement(By.className("stream-content")).getText();
            if(!discussion.contains(discussionText))
                Assert.fail("The discussion has not been posted.");

            assignments.tab_addToMyNotes.click();
            boolean notepresent = false;
            List<WebElement> notes = driver.findElements(By.className("star-content"));
            for(WebElement note : notes)
            {
                if(note.getText().contains(postText)) {
                    notepresent = true;
                    break;
                }
            }
            if(notepresent == false)
                Assert.fail("The note has not been posted.");


            assignmentTab.icon_performancePage.click();


            questionCardQuestionsList = driver.findElements(By.className("question-card-question-content"));
            for(int a=0;a<questionCardQuestionsList.size();a++){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", questionCardQuestionsList.get(a));
                Thread.sleep(2000);
                actualQuestionsList.add(questionCardQuestionsList.get(a).getText());
            }
            String text1 = "Multiple Choice Question";

            expectedQuestionsList = new ArrayList<>();
            questionText = "True False "+questionText;
            expectedQuestionsList.add(questionText);
            expectedQuestionsList.add(questionText);
            expectedQuestionsList.add(questionText);
            expectedQuestionsList.add(questionText);
            expectedQuestionsList.add(questionText);
            expectedQuestionsList.add(questionText);
            expectedQuestionsList.add(questionText);
            expectedQuestionsList.add(questionText);
            expectedQuestionsList.add(questionText);
            expectedQuestionsList.add(text1);
            expectedQuestionsList.add(text2);
            expectedQuestionsList.add(text1);
            expectedQuestionsList.add(text2);
            expectedQuestionsList.add(text1);
            expectedQuestionsList.add(text2);
            if(!CollectionUtils.isEqualCollection(actualQuestionsList,expectedQuestionsList)){
                //Assert.fail("The student should be able to view all the question parts in the Performance Summary page in the same way as it renders for the single question in the product now");
            }


            new LoginUsingLTI().ltiLogin("229_1");//Log in as an instructor
            new Assignment().provideGradeAndFeedbackToStudentForMPQ(229);
            Thread.sleep(5000);
            assessmentResponses.getButton_ReleaseFeedBackForAll().click();

            new LoginUsingLTI().ltiLogin("229_2");//Log in as a student
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            int index = 0;
            List<WebElement> assignments1 = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
            for (WebElement element1 : assignments1) {
                System.out.println("element1 : "  +element1.getText());
                if (element1.getText().contains(assessmentName)) {
                    break;
                }
                index++;
            }
            //driver.findElement(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']")).click();//click on assignment
            new Click().clickByElement(assignments1.get(index));

            //Expected -1 : Clicking on the multi part question card on the right should open the question with that question part in expanded view
            Thread.sleep(5000);
            element = driver.findElements(By.className("question-card-question-content")).get(0);
            element.click();
            Thread.sleep(3000);
            if (!(driver.findElement(By.id("part-1")).getAttribute("class").equals("question-part-wrapper multi-part-unattempted multi-part-selected"))) {
                Assert.fail("By default, when the student comes to the multipart question dependent , the first question part should only be enabled\n");
            }
            if (!(driver.findElement(By.cssSelector("div[class='question-part-wrapper multi-part-unattempted multi-part-selected']")).findElements(By.tagName("div")).get(0).getAttribute("class").equals("question-toggle-arrow-icon collapse"))) {
                Assert.fail("First question part should be expanded by default");
            }


            //Expected - 4 : The feedback given by the instructor for that question part should be displayed
            Assert.assertEquals(assignmentTab.teacherFeedBack.getText().trim(),feedback,"The feedback given by the instructor for that question part should be displayed");


            //Expected - 5 : Discussion and Star tabs should appear on MPQ level

            if (!(assignments.discussionTab.isDisplayed())) {
                Assert.fail("Discussion tab should appear for that multipart question");
            }
            if (!(assignments.tab_addToMyNotes.isDisplayed())) {
                Assert.fail("Star tab should appear for that multipart question");

            }



             /*Row No - 212 : "6. Add a discussion under Discussion tab
                7. Add a note under Star tab"*/
            //Expected -1 : The discussion tab should allow the student to add discussion at the multipart question level as supported for any other question.
            //Expected - 2 : The star tab should allow the student to add note for a multipart question at the multipart question level as supported for any other question.
            assignments.discussionTab.click();
            new Discussion().postDiscussion(discussionText);
            new Discussion().postNote(postText);

                /*Row No - 214 : "8. Go to Performance tab
                9. Select any other question part of same MPQ"*/
            //Expected - 1 : It should show the multipart question with that question part in expanded view
            assignments.getTab_performance.click();
            Thread.sleep(2000);
            element = driver.findElements(By.className("question-card-question-content")).get(1);
            //element.click();
            new Click().clickByElement(element);
            Thread.sleep(3000);
            if(!assignments.button_option4.isDisplayed()){
                Assert.fail("It should show the multipart question with that question part in expanded view");
            }


            //Expected - 2 : Posted discussion and note should appear under Discussion tab and Star tab and should be persistent for all other question parts
            assignments.discussionTab.click();
            discussion = driver.findElement(By.className("stream-content")).getText();
            if(!discussion.contains(discussionText))
                Assert.fail("The discussion has not been posted.");

            assignments.tab_addToMyNotes.click();
            notepresent = false;
            notes = driver.findElements(By.className("star-content"));
            for(WebElement note : notes)
            {
                if(note.getText().contains(postText)) {
                    notepresent = true;
                    break;
                }
            }
            if(notepresent == false)
                Assert.fail("The note has not been posted.");




                /*Row No - 216 : "11. Go to Performance summary page
                12. Click on the question part bar"*/

            //Expected - Clicking on the multi part question bar should open the question with that question part in expanded view
            assignmentTab.icon_performancePage.click();

            //Row No - 21 : 13. Go to course stream
            //Expected - Discussion posted by student for multipart question should appear in Course stream
            new Navigator().NavigateTo("Course Stream");
            Assert.assertEquals(assignments.label_discussionText.getText(), discussionText, "Discussion posted by student for multipart question should appear in Course stream");


            //Expected - 2 : Question stem details should appear instead of question statement
            questionText = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(229));
            if (!assignments.label_questionStem.getText().contains(questionText)) {
                Assert.fail("Question stem details should appear instead of question statement");
            }


            //Row No - 186. Click on the jump-out icon
            //Expected - 1 : Student should get navigated to question preview page of multipart
            courseStreamPage.getJumpOut().click();
            Thread.sleep(3000);
            Assert.assertEquals(assignments.MPQquestionText.getText().trim(), questionText, "Student should get navigated to question preview page of multipart");


            //Expected -2 : Student should view the multipart question with all the question parts open as done in the last visit.
            List<String> questionList = new AssigningMPQAssignment().traverseQuestionsPartsInMPQInResponsePage(229);
            Thread.sleep(5000);
            System.out.println("questionList : " + questionList);
            ArrayList<String> expectedQuestionList = new ArrayList<>();
            String questionText1 = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(167));
            String questionText2 = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(168));
            String trueFalseQuestionText = "True False For MPQ";
            String stem1Question = "Stem 2 Question";
            String stem2Question = "Stem 3 Question";
            expectedQuestionList.add(questionText1);
            expectedQuestionList.add(trueFalseQuestionText);
            expectedQuestionList.add(stem1Question);
            expectedQuestionList.add(questionText2);
            expectedQuestionList.add(trueFalseQuestionText);
            expectedQuestionList.add(stem2Question);
            expectedQuestionList.add(questionText2);
            expectedQuestionList.add(trueFalseQuestionText);
            expectedQuestionList.add(stem2Question);
            expectedQuestionList.add(questionText2);
            expectedQuestionList.add(trueFalseQuestionText);
            System.out.println("expectedQuestionList : " + expectedQuestionList);
            Collections.sort(expectedQuestionList);
            Collections.sort(questionList);
            if (!(expectedQuestionList.contains(questionList))) {
                //Assert.fail("The instructor should be able to view all the question part in the same way as it renders for single question in the product now.");
            }


        } catch (Exception e) {
            Assert.fail("Exception in the testcase 'studentToBeAbleToViewAndAttemptMPQ' in the class 'studentToBeAbleToViewAndAttemptMPQ'", e);
        }
    }








    public List<String> attemptMPQAssignmentAndGetQuestionsFrom(int numberOfQuestionsToBeAttempted)
    {
        List<String> statusList = new ArrayList<>();
        try
        {
            Thread.sleep(3000);
            for (int a=0;a<numberOfQuestionsToBeAttempted;a++){
                new Click().clickBycssselector("input[value = 'Submit Answer']");
                if(!(a==numberOfQuestionsToBeAttempted-1)){
                    new UIElement().waitAndFindElement(By.cssSelector("input[title = 'Next Question']"));
                    new Click().clickBycssselector("input[title = 'Next Question']");
                }else{
                    new UIElement().waitAndFindElement(By.cssSelector("input[title='Finish']"));
                    new Click().clickBycssselector("input[title='Finish']");
                }
            }
        }
        catch(Exception e)
        {
            new Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in create in Apphelper getMPQQuestionStatus in class AssignmentCreate",e);
        }
        Collections.sort(statusList);
        return statusList;
    }


    public List<String> attemptMPQAssignmentFromAssignmentsPage(int numberOfQuestionsToBeAttempted)
    {
        List<String> statusList = new ArrayList<String>();
        try
        {
            Thread.sleep(3000);
            for (int a=0;a<numberOfQuestionsToBeAttempted;a++){
                //new Click().clickBycssselector("input[value = 'Submit Answer']");
                if(!(a==numberOfQuestionsToBeAttempted-1)){
                    new UIElement().waitAndFindElement(By.linkText("Next Question"));
                    new Click().clickbylinkText("Next Question");
                }else{
                    new UIElement().waitAndFindElement(By.linkText("Finish Assignment"));
                    new Click().clickbylinkText("Finish Assignment");

                }
            }
        }
        catch(Exception e)
        {
            new Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in create in Apphelper getMPQQuestionStatus in class AssignmentCreate",e);
        }
        Collections.sort(statusList);
        return statusList;
    }

}