package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT17.R1714;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.AssignmentResponsesPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
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
 * Created by root on 5/7/15.
 */
public class AssigningMPQAssignment extends Driver{
    String actual = null;
    String expected = null;


    @Test(priority=1,enabled = true)
    public void instructorToBeAbleToAssignMPQAssignment(){
        try{
            /*Row No -  2 : "1. Login as instructor of LS Course
            2. Go to Assignments->Question AssigningMPQAssignmentAsMentorBanks page
            3. Click on Assign this icon and assign the static assessment having multipart as gradable assignment"*/


            //Expected - 1 : The instructor should be able to assign any assessment having multi-part questions as per the current assignment flow.
            CurrentAssignments currentAssignments= PageFactory.initElements(driver, CurrentAssignments.class);
            int dataIndex = 2;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new Assignment().create(dataIndex);
            new Assignment().addQuestions(3,"multipart",assessmentName);
            new LoginUsingLTI().ltiLogin("2_1");//Login as in instructor
            new Assignment().assignToStudent(dataIndex);
            if(driver.findElements(By.xpath("//span[@title = '(shor) "+assessmentName+"']//following-sibling::div//span")).size()==0){
                Assert.fail("The instructor should be able to assign any assessment having multi-part questions as per the current assignment flow.");
            }


            //Row No - 4. Assign the same assessment again as non-gradable assignment
            //Expected - The instructor should be able to assign any assessment having multi-part questions as per the current assignment flow.
            new Assignment().assignToStudent(3);
            if(!(driver.findElements(By.xpath("//span[@title = '(shor) "+assessmentName+"']//following-sibling::div")).size()>=2)){
                Assert.fail("The instructor should be able to assign any assessment having multi-part questions as per the current assignment flow.");
            }

        }catch(Exception e){
            Assert.fail("Exception in the testcase 'instructorToBeAbleToAssignMPQAssignment' in the class 'AssigningMPQAssignment'", e);
        }

    }



    @Test(priority=2,enabled = true)
    public void instructorToBeAbleToProvideFeedbackForMPQGradableAssignment(){
        try{
           /*Row No - 4 : "1. Login as instructor of LS Course
            4. Go to Assignments->Question Banks page
            5. Click on Assign this icon and assign the static assessment having multipart as gradable assignment
            4. Login as student
            5. Submit the assignment
            6. Login as instructor again
            7. Go to assignment responses page and click on view responses link for a multipart question"*/


            //Expected - 1 : Clicking on view responses,response page for multipart question should get opened and all the question parts should be present
            AssignmentResponsesPage assignmentResponsesPage= PageFactory.initElements(driver, AssignmentResponsesPage.class);

            int dataIndex = 4;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String marks = ReadTestData.readDataByTagName("", "marks", Integer.toString(dataIndex));
            String feedback = ReadTestData.readDataByTagName("", "feedback", Integer.toString(dataIndex));

            new Assignment().create(dataIndex);
            new Assignment().addQuestions(5,"multipart",assessmentName);
            new Assignment().addQuestions(5,"truefalse",assessmentName);

            new LoginUsingLTI().ltiLogin("4_1");//Login as in instructor
            new Assignment().assignToStudent(dataIndex);
            new LoginUsingLTI().ltiLogin("4_2");//Login as a student
            new Assignment().submitAssignmentAsStudent(5);
            new LoginUsingLTI().ltiLogin("4_1");//Login as in instructor
            new Assignment().switchToAssignmentResponsePage(5);
            Thread.sleep(5000);
            new MouseHover().mouserhover("idb-gradebook-question-content");
            Thread.sleep(5000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link")));
            Assert.assertEquals(assignmentResponsesPage.label_assignmentResponsesPage.getText(),assessmentName,"Clicking on view responses,response page for multipart question should get opened");





            //Expected - 4 : For any scorable part that is skipped/not attempted a score of zero should be awarded to the student by the product.
            /*Assert.assertEquals(assignmentResponsesPage.getPointsNrEditFieldList().get(1).getAttribute("inner‌​HTML"),"0","For any scorable part that is skipped/not attempted a score of zero should be awarded to the student by the product.");
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.className("multi-part-question-score-block-header")));
*/



            //Expected - 2 : Instructor should be able to add the grades or feedback for ALL question parts which are part of the multi-part questions in the same view.
            List<WebElement> scoreElementsList =  driver.findElements(By.className("multi-part-question-score"));
            for(int a=0;a<scoreElementsList.size();a++){
                scoreElementsList.get(a).clear();
                scoreElementsList.get(a).sendKeys(marks);
            }
            assignmentResponsesPage.getSaveButton().click();

            for(int a=0;a<assignmentResponsesPage.getTextArea_FeedbackList().size();a++){
                assignmentResponsesPage.getTextArea_FeedbackList().get(a).clear();
                assignmentResponsesPage.getTextArea_FeedbackList().get(a).sendKeys(feedback);
            }
            assignmentResponsesPage.getSaveButton().click();


            //Row No - 15 : 8. Enter grade and feedback for any question part response and click on Save button
           //Expected -1 :  Numerator value in Total points field should get updated when instructor changes the grade for a particular question part response
            scoreElementsList =  driver.findElements(By.className("multi-part-question-score"));
            for(int a=0;a<scoreElementsList.size();a++){
                scoreElementsList.get(a).clear();
                scoreElementsList.get(a).sendKeys(marks);
            }
            assignmentResponsesPage.getSaveButton().click();



            //Expected - 2 : Comment icon should appear over Multipart question in Assignment responses page
            new Assignment().switchToAssignmentResponsePage(5);
            if(!(assignmentResponsesPage.icon_feedback.isDisplayed())){
            Assert.fail("Comment icon should appear over Multipart question in Assignment responses page");
            }


            /*Row No - 17 : "9. Go to Assignment response page
            10. Mouse hover on the student' name and click on enter grade"*/
            //Expected - Instructor should not be able to enter grade for a multipart question for MPQ level
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getLink_UserName().get(0));
            new UIElement().waitAndFindElement(assignmentResponsesPage.getEnterGrade());
            assignmentResponsesPage.getEnterGrade().click();
            List<WebElement> gradePointsElementsList = driver.findElements(By.className("idb-grade-points"));
            for(int a=0;a<gradePointsElementsList.size()-1;a++){
             System.out.println("gradePointsElementsList.get(a).isEnabled()1 : " + gradePointsElementsList.get(a).isEnabled());
            if(gradePointsElementsList.get(a).isEnabled()){
                Assert.fail("Instructor should not be able to enter grade for a multipart question for MPQ level");
            }
            }


            //Expected - 18 : Instructor should get the option to enter grades for other question types
            if(!(gradePointsElementsList.get(2).isEnabled())){
                Assert.fail("Instructor should get the option to enter grades for other question types");
            }
        }catch(Exception e){
            Assert.fail("Exception in the testcase 'instructorToBeAbleToProvideFeedbackForMPQGradableAssignment' in the class 'AssigningMPQAssignment'", e);
        }

    }

    @Test(priority=3,enabled = true)
    public void instructorToBeAbleToProvideFeedbackForMPQNonGradableAssignment(){
        try{
            /*Row No - 19 : "1. Login as instructor of LS Course
            2. Go to Assignments->Question Banks page
            3. Click on Assign this icon and assign the static assessment having multipart as non-gradable assignment
            4. Login as student
            5. Submit the assignment
            6. Login as instructor again
            7. Go to assignment responses page and click on view responses link for a multipart question"*/


            //Expected - 1 : Clicking on view responses,response page for multipart question should get opened and all the question parts should be present
            CurrentAssignments currentAssignments= PageFactory.initElements(driver, CurrentAssignments.class);
            int dataIndex = 19;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            AssignmentResponsesPage assignmentResponsesPage= PageFactory.initElements(driver, AssignmentResponsesPage.class);
            String marks = ReadTestData.readDataByTagName("", "marks", Integer.toString(dataIndex));
            String feedback = ReadTestData.readDataByTagName("", "feedback", Integer.toString(dataIndex));

            new Assignment().create(dataIndex);
            new Assignment().addQuestions(20,"multipart",assessmentName);
            new Assignment().addQuestions(20,"truefalse",assessmentName);

            new LoginUsingLTI().ltiLogin("19_1");//Login as in instructor
            new Assignment().assignToStudent(dataIndex);
            new LoginUsingLTI().ltiLogin("19_2");//Login as a student
            new Assignment().submitAssignmentAsStudent(20);

            new LoginUsingLTI().ltiLogin("19_1");//Login as in instructor
            new Assignment().switchToAssignmentResponsePage(19);
            Thread.sleep(5000);
            new MouseHover().mouserhover("idb-gradebook-question-content");
            Thread.sleep(5000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("ls-view-response-link")));
            Assert.assertEquals(assignmentResponsesPage.label_assignmentResponsesPage.getText(), assessmentName, "Clicking on view responses,response page for multipart question should get opened");
            assignmentResponsesPage.getSaveButton().click();
            Thread.sleep(1000);

            for(int a=0;a<assignmentResponsesPage.getTextArea_FeedbackList().size();a++){
                assignmentResponsesPage.getTextArea_FeedbackList().get(a).clear();
                assignmentResponsesPage.getTextArea_FeedbackList().get(a).sendKeys(feedback);
            }
            assignmentResponsesPage.getSaveButton().click();

            //Expected - 2 : Comment icon should appear over Multipart question in Assignment responses page
            new Assignment().switchToAssignmentResponsePage(19);
            if(!(assignmentResponsesPage.icon_feedback.isDisplayed())){
                Assert.fail("Comment icon should appear over Multipart question in Assignment responses page");
            }

            /*Row No - 17 : "9. Go to Assignment response page
            10. Mouse hover on the student' name and click on enter grade"*/
            //Expected - Instructor should not be able to enter grade for a multipart question for MPQ level
            new MouseHover().mouserhoverbywebelement(assignmentResponsesPage.getLink_UserName().get(0));
            Thread.sleep(9000);
            if(driver.findElements(By.id("idb-grade-now-link")).size()!=0){
                Assert.fail("Instructor should not be able to enter grade for a multipart question for MPQ level");
            }

        }catch(Exception e){
            Assert.fail("Exception in the testcase 'instructorToBeAbleToProvideFeedbackForMPQGradableAssignment' in the class 'AssigningMPQAssignment'", e);
        }

    }




    @Test(priority=4,enabled = true)
    public void instructorTobeAbleToPreviewMPQQuestions(){
        try{

           /*Row No  - 37 : "1. Login as instructor of LS Course
            2. Go to eTextbook and select static assessment having multipart question"*/

            //Expected -1  : The instructor should be able to view all the question part in the same way as it renders for single question in the product now.
            //Expected - 2 : All the question parts of that question should show one after the other in the same sequence with question part labels as rendered to the student.
            CurrentAssignments currentAssignments= PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);
            int dataIndex = 37;
            new Assignment().create(dataIndex);
            new LoginUsingLTI().ltiLogin("37_1");//Login as in instructor
            new Assignment().startStaticAssignmentFromeTextbook(dataIndex,1);
            ArrayList<String> questionList  = traverseQuestionsPartsInMPQInResponsePage(dataIndex);
            ArrayList<String> expectedQuestionList = new ArrayList<>();

            String questionText1 = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(37));
            String questionText2 = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(38));
            String trueFalseQuestionText = "True False For MPQ";
            String stem1Question = "Stem 37 Question";
            String stem2Question = "Stem 38 Question";

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
            Collections.sort(expectedQuestionList);
            Collections.sort(questionList);
            if(!(expectedQuestionList.containsAll(questionList))){
                Assert.fail("The instructor should be able to view all the question part in the same way as it renders for single question in the product now.");
            }



            //Expected - 3 : All the question part cards should be expanded by default with the visual indication of correct answer choice.
            new LoginUsingLTI().ltiLogin("37_1");//Login as in instructor
            new Assignment().startStaticAssignmentFromeTextbook(dataIndex,1);

            if(driver.findElements(By.cssSelector("div[class='true-false-student-tick true-false-student-right']")).size()==0){
                Assert.fail("All the question part cards should be expanded by default with the visual indication of correct answer choice.\n");
            }


            //Expected - 6 : There should be QID given for one multipart question only.
            System.out.println("assignments.questionId.getText(); : " + assignments.questionId.getText());
            if(driver.findElements(By.cssSelector("question-label-with-questionid")).size()>1){
               Assert.fail("There should be QID given for one multipart question only.");
            }


            //Expected - 5 : The question text for any part should have the question label displayed as well.
            Thread.sleep(5000);
            List<WebElement> questionLabelsElementsList = driver.findElements(By.xpath(".//*[contains(@class,'control-label')]"));
            for(int a=0;a<questionLabelsElementsList.size();a++){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", questionLabelsElementsList.get(a));
                Thread.sleep(1000);
            }
            Assert.assertEquals(assignments.questionWithLabel.getText(),"1:\n"+ trueFalseQuestionText,"The question text for any part should have the question label displayed as well.");




            //Expected - 7 : All the question stems should be non-expandable.
            if(driver.findElements(By.cssSelector("div[class='question-toggle-arrow-icon']")).size()!=0){
                int size = driver.findElements(By.cssSelector("div[class='question-toggle-arrow-icon']")).size();
                if(size!=1){
                    Assert.fail("All the question stems should be non-expandable.");
                }
            }


            //Expected - 8 : All the question parts should be collapsable if the instructor specifically clicks on the arrow.
            assignments.icon_QuestionToggleArrow.click();
            if(driver.findElements(By.cssSelector("div[class='question-toggle-arrow-icon collapse']")).size()!=2){
                Assert.fail("All the question parts should be collapsable if the instructor specifically clicks on the arrow.");
            }

            //Expected - 10 : The right side cards should ONLY contain the Assignment and Resources tab as supported currently.
            if(!(driver.findElement(By.cssSelector("span[title = 'Assignments']")).isDisplayed())){
                Assert.fail("The right side cards should ONLY contain the Assignments tab");
            }

            if(!(driver.findElement(By.cssSelector("span[title = 'Resources']")).isDisplayed())){
                Assert.fail("The right side cards should ONLY contain the Resources tab");
            }


        }catch(Exception e){
            Assert.fail("Exception in the testcase 'instructorToBeAbleToProvideFeedbackForMPQGradableAssignment' in the class 'AssigningMPQAssignment'", e);
        }

    }



    @Test(priority=5,enabled = true)
    public void instructorTobeAbleToPreviewMPQAssignment(){
        try{

            /*Row No - 49 : "1. Login as instructor of LS Course
            2. Go to Assignments Summary page and click on the assignment name having multipart questions"*/

            //Expected -1  : The instructor should be able to view all the question part in the same way as it renders for single question in the product now.
            //Expected - 2 : All the question parts of that question should show one after the other in the same sequence with question part labels as rendered to the student.
            CurrentAssignments currentAssignments= PageFactory.initElements(driver, CurrentAssignments.class);
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);
            int dataIndex = 49;
            String trueFalseQuestionText = "True False For MPQ";

            new Assignment().create(dataIndex);
            new LoginUsingLTI().ltiLogin("49_1");//Login as in instructor
            new Assignment().assignToStudent(49);
            new Assignment().openAssignmentFromAssignmentPage(49);
            ArrayList<String> questionList  = traverseQuestionsPartsInMPQInResponsePage(dataIndex);
            ArrayList<String> expectedQuestionList = new ArrayList<>();

            String questionText1 = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(49));
            String questionText2 = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(50));
            String stem1Question = "Stem 49 Question";
            String stem2Question = "Stem 50 Question";

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
            Collections.sort(expectedQuestionList);
            Collections.sort(questionList);
            if(!(expectedQuestionList.containsAll(questionList))){
                Assert.fail("The instructor should be able to view all the question part in the same way as it renders for single question in the product now.");
            }


            //Expected - 3 : All the question part cards should be expanded by default with the visual indication of correct answer choice.
            new LoginUsingLTI().ltiLogin("49_1");//Login as in instructor
            new Assignment().openAssignmentFromAssignmentPage(49);
            if(driver.findElements(By.cssSelector("div[class='true-false-student-tick true-false-student-right']")).size()==0){
                Assert.fail("All the question part cards should be expanded by default with the visual indication of correct answer choice.\n");
            }
            Thread.sleep(5000);
            //Expected - 5 : The question text for any part should have the question label displayed as well.
            Assert.assertEquals(assignments.questionWithLabel.getText(),"1:\n"+ trueFalseQuestionText,"The question text for any part should have the question label displayed as well.");

            //Expected - 6 : There should be QID given for one multipart question only.
            System.out.println("assignments.questionId.getText(); : " + assignments.questionId.getText());
            if(driver.findElements(By.cssSelector("question-label-with-questionid")).size()>1){
                Assert.fail("There should be QID given for one multipart question only.");
            }


            //Expected - 7 : All the question stems should be non-expandable.
            Thread.sleep(5000);
            List<WebElement> questionLabelsElementsList = driver.findElements(By.xpath("./*//*[contains(@class,'control-label')]"));
            for(int a=0;a<questionLabelsElementsList.size();a++){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", questionLabelsElementsList.get(a));
                Thread.sleep(1000);
            }




            if(driver.findElements(By.cssSelector("div[class='question-toggle-arrow-icon']")).size()!=0){
                int size = driver.findElements(By.cssSelector("div[class='question-toggle-arrow-icon']")).size();
                if(size!=1){
                    Assert.fail("All the question stems should be non-expandable.");
                }
            }


            //Expected - 8 : All the question parts should be collapsable if the instructor specifically clicks on the arrow.
            assignments.icon_QuestionToggleArrow.click();
            if(driver.findElements(By.cssSelector("div[class='question-toggle-arrow-icon collapse']")).size()!=2){
                Assert.fail("All the question parts should be collapsable if the instructor specifically clicks on the arrow.");
            }



            //Row No -61 : 3. Go to Question Banks page and click on the static assessment having multipart
            new Assignment().openAssignmentFromQuestionBanksPageAsInstructor(49);
            questionList  = traverseQuestionsPartsInMPQInResponsePage(dataIndex);
            Collections.sort(questionList);
            if(!(expectedQuestionList.containsAll(questionList))){
                Assert.fail("The instructor should be able to view all the question part in the same way as it renders for single question in the product now.");
            }


            //Expected - 3 : All the question part cards should be expanded by default with the visual indication of correct answer choice.
            new LoginUsingLTI().ltiLogin("49_1");//Login as in instructor
            new Assignment().openAssignmentFromAssignmentPage(49);
            if(driver.findElements(By.cssSelector("div[class='true-false-student-tick true-false-student-right']")).size()==0){
                Assert.fail("All the question part cards should be expanded by default with the visual indication of correct answer choice.\n");
            }

            //Expected - 5 : The question text for any part should have the question label displayed as well.
            new UIElement().waitAndFindElement(assignments.questionWithLabel);
            Thread.sleep(2000);
            Assert.assertEquals(assignments.questionWithLabel.getText(),"1:\n"+ trueFalseQuestionText,"The question text for any part should have the question label displayed as well.");

            //Expected - 6 : There should be QID given for one multipart question only.
            System.out.println("assignments.questionId.getText(); : " + assignments.questionId.getText());
            if(driver.findElements(By.cssSelector("question-label-with-questionid")).size()>1){
                Assert.fail("There should be QID given for one multipart question only.");
            }


            //Expected - 7 : All the question stems should be non-expandable.
            Thread.sleep(5000);
            questionLabelsElementsList = driver.findElements(By.xpath(".//*[contains(@class,'control-label')]"));
            for(int a=0;a<questionLabelsElementsList.size();a++){
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", questionLabelsElementsList.get(a));
                Thread.sleep(1000);
            }


            if(driver.findElements(By.cssSelector("div[class='question-toggle-arrow-icon']")).size()!=0){
                int size = driver.findElements(By.cssSelector("div[class='question-toggle-arrow-icon']")).size();
                if(size!=1){
                    Assert.fail("All the question stems should be non-expandable.");
                }
            }

            //Expected - 8 : All the question parts should be collapsable if the instructor specifically clicks on the arrow.
            assignments.icon_QuestionToggleArrow.click();
            if(driver.findElements(By.cssSelector("div[class='question-toggle-arrow-icon collapse']")).size()!=2){
                Assert.fail("All the question parts should be collapsable if the instructor specifically clicks on the arrow.");
            }
        }catch(Exception e){
            Assert.fail("Exception in the testcase 'instructorTobeAbleToPreviewMPQAssignment' in the class 'AssigningMPQAssignment'", e);
        }

    }


    @Test(priority=6,enabled = true)
    public void instructorTobeAbleToReportContentIssues(){
        try{

            /*Row No - 73  : "1. Login as instructor
            2. Go to Question Banks page
            3. Click on the static assessment having multipart questions
            4. Click on the report content errors icon present on question part and send the error content"*/

            //Expected -1 : Instructor should be able to send report content error successfully

            CurrentAssignments currentAssignments= PageFactory.initElements(driver, CurrentAssignments.class);
            int dataIndex = 73;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            AssignmentResponsesPage assignmentResponsesPage= PageFactory.initElements(driver, AssignmentResponsesPage.class);
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);
            new Assignment().create(75);
            new LoginUsingLTI().ltiLogin("73_1");//Login as in instructor
            new Assignment().openAssignmentFromQuestionBanksPageAsInstructor(75);
            Thread.sleep(3000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.className("associated-content-details-label")));
            Thread.sleep(2000);
            //((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.className("question-toggle-arrow-icon")));
            new Click().clickByElement(assignments.icon_reportContentError);
            assignments.textArea_EnterContentIssue.sendKeys("There is some mistake in the question");
            assignments.buttonReportContent_Save.click();
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            new Click().clickbyxpath("//div[contains(text(),'Ch 3:')]");
            assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(75));
            new SelectCourse().selectInvisibleAssignment(assessmentName);
            new Click().clickByElement(assignments.dialog_ContentIssue);
            if(!(assignments.message_ReportContentIssue.getText().contains("There is some mistake in the question"))){
                Assert.fail("Instructor should be able to send report content error successfully");
            }

            new Assignment().create(dataIndex);
            new Assignment().addQuestions(74,"multipart",assessmentName);
            new Assignment().addQuestions(74,"multipart",assessmentName);
            new LoginUsingLTI().ltiLogin("73_1");//Login as in instructor
            new Assignment().assignToStudent(75);
            new LoginUsingLTI().ltiLogin("73_2");//Login as a student
            new Assignment().openAssignmentFromAssignmentPage(75);
            new Click().clickByElement(assignments.dialog_ContentIssue);
            assignments.textArea_EnterContentIssue.sendKeys("There is some mistake in the question");
            assignments.buttonReportContent_Save.click();
            Thread.sleep(5000);
            expected = "Are you sure there is a problem with this question? You may want to start a discussion on this question with your classmates before reporting a problem.\n" +
                    "\n" +
                    "Click Yes to report this issue, or\n" +
                    "\n" +
                    "Cancel to go back to the question";
            Assert.assertEquals(assignments.notification.getText(),expected,"A Notification should be shown with the text - “Are you sure there is a problem with this question? You may want to start a Discussion on this question with your classmates before reporting a problem. Click Yes to report this issue or Cancel to go back to the question”.");



            /*Row No - 74 : "5. Go to eTextbook and click on assessment having multipart question
            6.  Click on the report content errors icon present on question part and send the error content report"*/
            //Expected  - Instructor should be able to send report content error successfully
            new Assignment().startStaticAssignmentFromeTextbook(75,dataIndex);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.className("question-toggle-arrow-icon")));
            Thread.sleep(3000);
            new Click().clickByElement(assignments.dialog_ContentIssue);
            assignments.textArea_EnterContentIssue.sendKeys("There is some mistake in the question");
            assignments.buttonReportContent_Save.click();
            assignments.link_yes.click();

            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            new Click().clickbyxpath("//div[contains(text(),'Ch 3:')]");
            assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(75));
            new SelectCourse().selectInvisibleAssignment(assessmentName);
            new Click().clickByElement(assignments.dialog_ContentIssue);
            if(!(assignments.message_ReportContentIssue.getText().contains("There is some mistake in the question"))){
                Assert.fail("Instructor should be able to send report content error successfully");
            }



            /*Row No - 75 : "7. Login as student
            8. Go to Assignments page and start the assignment
            9. Click on report content icon present on question part
            10. Type the text and click on Send button"*/
            //Expected - A Notification should be shown with the text - “Are you sure there is a problem with this question? You may want to start a Discussion on this question with your classmates before reporting a problem. Click Yes to report this issue or Cancel to go back to the question”.
            new Assignment().create(75);
            new LoginUsingLTI().ltiLogin("73_1");//Login as in instructor
            new Assignment().assignToStudent(75);
            new LoginUsingLTI().ltiLogin("73_2");//Login as a student
            new Assignment().openAssignmentFromAssignmentPage(75);
            new Click().clickByElement(assignments.dialog_ContentIssue);
            assignments.textArea_EnterContentIssue.sendKeys("There is some mistake in the question");
            assignments.buttonReportContent_Save.click();
            expected = "Are you sure there is a problem with this question? You may want to start a discussion on this question with your classmates before reporting a problem.\n" +
                    "\n" +
                    "Click Yes to report this issue, or\n" +
                    "\n" +
                    "Cancel to go back to the question";
            Thread.sleep(3000);
            Assert.assertEquals(assignments.notification.getText(),expected,"A Notification should be shown with the text - “Are you sure there is a problem with this question? You may want to start a Discussion on this question with your classmates before reporting a problem. Click Yes to report this issue or Cancel to go back to the question”.");


            //Row N0 - 76:   Click on Yes option
            //Expected  - Student should be able to send the report content error successfully
            assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(75));
            assignments.link_yes.click();//Click on yes button
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            new Click().clickbyxpath("//div[contains(text(),'Ch 3:')]");
            new SelectCourse().selectInvisibleAssignment(assessmentName);
            new Click().clickByElement(assignments.dialog_ContentIssue);
            if(!(assignments.message_ReportContentIssue.getText().contains("There is some mistake in the question"))){
                Assert.fail("Instructor should be able to send report content error successfully");
            }


             /*"Row No - 77 : 12. Go to eTextbook and start the assessment having multipart question
            13. Click on the report content errors icon present on question part
            14. Type the text and click on Send button"*/
            //Expected - A Notification should be shown with the text - “Are you sure there is a problem with this question? You may want to start a Discussion on this question with your classmates before reporting a problem. Click Yes to report this issue or Cancel to go back to the question”.
            new LoginUsingLTI().ltiLogin("73_2");//Login as a student
            new Assignment().startStaticAssignmentFromeTextbook(73, 1);
            assignments.button_submitAnswer.click();
            new Click().clickByElement(assignments.dialog_ContentIssue);
            assignments.textArea_EnterContentIssue.sendKeys("There is some mistake in the question");
            assignments.buttonReportContent_Save.click();
            Thread.sleep(3000);
            expected = "Are you sure there is a problem with this question? You may want to start a discussion on this question with your classmates before reporting a problem.\n" +
                    "\n" +
                    "Click Yes to report this issue, or\n" +
                    "\n" +
                    "Cancel to go back to the question";
            Assert.assertEquals(assignments.notification.getText(),expected,"A Notification should be shown with the text - “Are you sure there is a problem with this question? You may want to start a Discussion on this question with your classmates before reporting a problem. Click Yes to report this issue or Cancel to go back to the question”.");



            //Row No - 78 : . Click on Yes option
            //Expected  - Student should be able to send the report content error successfully
            assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(73));
            assignments.link_yes.click();//Click on yes button
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            new Click().clickbyxpath("//div[contains(text(),'Ch 3:')]");
            new SelectCourse().selectInvisibleAssignment(assessmentName);
            new Click().clickByElement(assignments.dialog_ContentIssue);
            if(!(assignments.message_ReportContentIssue.getText().contains("There is some mistake in the question"))){
                Assert.fail("Instructor should be able to send report content error successfully");
            }

        }catch(Exception e){
            Assert.fail("Exception in the testcase 'instructorToBeAbleToProvideFeedbackForMPQGradableAssignment' in the class 'AssigningMPQAssignment'", e);
        }

    }






    @Test(priority=7,enabled = true)
    public void authorToBeAbleToReportContentIssues(){
        try{

            /*Row No - 79  : "1. Login as a CMS user
            2. Create a multipart question
            3. Save the multipart question"*/
            //Expected -1 : Author should get the report content errors icon once author saves the question successfully


            CurrentAssignments currentAssignments= PageFactory.initElements(driver, CurrentAssignments.class);
            int dataIndex = 79;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            AssignmentResponsesPage assignmentResponsesPage= PageFactory.initElements(driver, AssignmentResponsesPage.class);
            String marks = ReadTestData.readDataByTagName("", "marks", Integer.toString(dataIndex));
            String feedback = ReadTestData.readDataByTagName("", "feedback", Integer.toString(dataIndex));
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);



            new Assignment().create(79);
            Thread.sleep(3000);
            if(driver.findElements(By.cssSelector("div[class='add-content-error show-content-issues-dialog']")).size()==0){
                Assert.fail("Author should get the report content errors icon once author saves the question successfully");
            }

            //Expected - 3 :Each of the already reported content issues should have a "Resolve" button on the top-right of the comment area.
            new LoginUsingLTI().ltiLogin("79_1");//Login as in instructor
            new Assignment().openAssignmentFromQuestionBanksPageAsInstructor(79);
            Thread.sleep(3000);
            new Click().clickByElement(assignments.icon_reportContentError);
            assignments.textArea_EnterContentIssue.sendKeys("There is some mistake in the question");
            new Click().clickByElement(assignments.buttonReportContent_Save);

            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            new Click().clickbyxpath("//div[contains(text(),'Ch 3:')]");
            new AssigningMPQAssignmentAsMentor().selectInvisiblebottomAssignment(assessmentName);
            new Click().clickByElement(assignments.dialog_ContentIssue);
            if(!(assignments.button_Resolved.getText().contains("Resolved"))){
                Assert.fail("Instructor should be able to send report content error successfully");
            }
            Assert.assertEquals(assignments.button_Resolved.getText(),"Resolved","Each of the already reported content issues should have a \"Resolve\" button on the top-right of the comment area.");

           //Row No - 82 :  Go to Summary page and click on right arrow icon next to  "Content issue reported"
           //Expected - It should display the content error reported by student/instructor/mentor for multipart question along with other questions
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            assignments.link_summary.click();
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250);");
            System.out.println("assignments.label_issuesCount.getText() : " + assignments.label_issuesCount.getText());
            new Click().clickByclassname("review-potential-text");
            Assert.assertEquals(assignments.label_comments.getText(),"There is some mistake in the question","It should display the content error reported by student/instructor/mentor for multipart question along with other questions");
            Assert.assertEquals(assignments.button_fixit.getText(),"Fix It","It should display Fix it button");



            /*Row No - 83 : "5. Click on Fix it button for which the student/instructor has reported issue(multipart question)
            6. Click on report content icon and resolve the issue
            7. Go to Summary page and navigate to report content issue page"*/
            //Expected - It should display the status as "Closed"
            assignments.button_fixit.click();
            new Click().clickByElement(assignments.icon_reportContentError);
            assignments.button_Resolved.click();
            assignments.link_summary.click();
            new Click().clickByclassname("review-potential-text");
            Assert.assertEquals(assignments.label_comments.getText(),"There is some mistake in the question","It should display the content error reported by student/instructor/mentor for multipart question along with other questions");
            Assert.assertEquals(assignments.label_colStatus.getText(),"Closed","It should display the status as \"Closed\"");
        }catch(Exception e){
            Assert.fail("Exception in the testcase 'instructorToBeAbleToProvideFeedbackForMPQGradableAssignment' in the class 'AssigningMPQAssignment'", e);
        }
    }



    @Test(priority=8,enabled = false)
    public void checkLocalizationChangesForAuthorSideForMPQ(){
        try{

            /*ROw No - 86 : "1. Login as a CMS user for Learnon
            2. Create a multipart question.
            3. Click on Preview button"*/

            /*Expected - "For Learnon,”Points” should be changed to “Marks” on following places:
                    -Question part card view
                    -Performance tab"*/


            CurrentAssignments currentAssignments= PageFactory.initElements(driver, CurrentAssignments.class);
            int dataIndex = 86;
            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            AssignmentResponsesPage assignmentResponsesPage= PageFactory.initElements(driver, AssignmentResponsesPage.class);
            String marks = ReadTestData.readDataByTagName("", "marks", Integer.toString(dataIndex));
            String feedback = ReadTestData.readDataByTagName("", "feedback", Integer.toString(dataIndex));
            Assignments assignments= PageFactory.initElements(driver, Assignments.class);



            new Assignment().create(86);
            Thread.sleep(3000);
            if(driver.findElements(By.cssSelector("div[class='add-content-error show-content-issues-dialog']")).size()==0){
                Assert.fail("Author should get the report content errors icon once author saves the question successfully");
            }

            //Expected - 3 :Each of the already reported content issues should have a "Resolve" button on the top-right of the comment area.
            new LoginUsingLTI().ltiLogin("86_1");//Login as in instructor
            new Assignment().openAssignmentFromQuestionBanksPageAsInstructor(86);
            Thread.sleep(3000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.className("associated-content-details-label")));
            Thread.sleep(2000);
            new Click().clickByElement(assignments.icon_reportContentError);
            assignments.textArea_EnterContentIssue.sendKeys("There is some mistake in the question");
            assignments.buttonReportContent_Save.click();

            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            new Click().clickbyxpath("//div[contains(text(),'Ch 3:')]");
            new SelectCourse().selectInvisibleAssignment(assessmentName);
            new Click().clickByElement(assignments.dialog_ContentIssue);
            if(!(assignments.button_Resolved.getText().contains("Resolved"))){
                Assert.fail("Instructor should be able to send report content error successfully");
            }
            Assert.assertEquals(assignments.button_Resolved.getText(),"Resolved","Each of the already reported content issues should have a \"Resolve\" button on the top-right of the comment area.");

            //Row No - 82 :  Go to Summary page and click on right arrow icon next to  "Content issue reported"
            //Expected - It should display the content error reported by student/instructor/mentor for multipart question along with other questions
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            assignments.link_summary.click();
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,250);");
            System.out.println("assignments.label_issuesCount.getText() : " + assignments.label_issuesCount.getText());
            String issuesCount = assignments.label_issuesCount.getText();
            new Click().clickByclassname("review-potential-text");
            Assert.assertEquals(assignments.label_comments.getText(),"There is some mistake in the question","It should display the content error reported by student/instructor/mentor for multipart question along with other questions");
            Assert.assertEquals(assignments.button_fixit.getText(),"Fix It","It should display Fix it button");



            /*Row No - 83 : "5. Click on Fix it button for which the student/instructor has reported issue(multipart question)
            6. Click on report content icon and resolve the issue
            7. Go to Summary page and navigate to report content issue page"*/
            //Expected - It should display the status as "Closed"
            assignments.button_fixit.click();
            new Click().clickByElement(assignments.icon_reportContentError);
            assignments.button_Resolved.click();
            assignments.link_summary.click();
            new Click().clickByclassname("review-potential-text");
            Assert.assertEquals(assignments.label_comments.getText(),"There is some mistake in the question","It should display the content error reported by student/instructor/mentor for multipart question along with other questions");
            Assert.assertEquals(assignments.label_colStatus.getText(),"Closed","It should display the status as \"Closed\"");
        }catch(Exception e){
            Assert.fail("Exception in the testcase 'instructorToBeAbleToProvideFeedbackForMPQGradableAssignment' in the class 'AssigningMPQAssignment'", e);
        }
    }


    public ArrayList<String> traverseQuestionsPartsInMPQInResponsePage(int dataIndex){
        ArrayList<String> questionList  =  new ArrayList<>();
        try{
            List<WebElement> questionLabelsElementsList = driver.findElements(By.xpath(".//*[contains(@class,'control-label')]"));
             for(int a=0;a<questionLabelsElementsList.size();a++){
                 ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", questionLabelsElementsList.get(a));
                 Thread.sleep(1000);
                 System.out.println("questionLabelsElementsList.get(a).getText() : " + questionLabelsElementsList.get(a).getText());
                 questionList.add(questionLabelsElementsList.get(a).getText());
             }

        }catch(Exception e){
            Assert.fail("Exception in the method 'traverseQuestionsPartsInMPQInResponsePage' in the class 'AssigningMPQAssignment'",e);
        }
        return questionList;
    }
}
