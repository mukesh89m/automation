package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R166;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*
 * Created by Sumit on 8/13/2014.
 */
public class SubmitAssignmentWithCollaborationAndImmediateFeedbackEnabled extends Driver {

    @Test
    public void submitAssignmentWithCollaborationAndImmediateFeedbackEnabled()
    {
        try
        {
            String policyname = ReadTestData.readDataByTagName("", "policyname", "40");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "40");
            new Assignment().create(40);
            new Assignment().addQuestions(40, "qtn-type-true-false-img", assessmentname);
            new Assignment().addQuestionsWithCustomizedQuestionText(40, "qtn-type-true-false-img", assessmentname, 3);

            new LoginUsingLTI().ltiLogin("40_1");//create a student1
            new LoginUsingLTI().ltiLogin("40_2");//create a student2

            new LoginUsingLTI().ltiLogin("40");
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(policyname, "40 Policy description text", "1",null, true, "1", "", null, "", "", "");
            new Assignment().assignToStudent(40);

            new LoginUsingLTI().ltiLogin("40_1");//login as student1
            new Assignment().openAssignmentFromCourseStream("9");//open the assignment

            new SelectAnswerAndSubmit().staticAssignment("A");//submit 1st question
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("next-or-submit-link")));//click on next link on popup
            Thread.sleep(2000);
            String qNo = driver.findElement(By.className("current-question-index")).getText();
            if(!qNo.contains("2"))
               Assert.fail("After submitting the 1st question of Assignment with (immediate feedback + Allow Collaboration) enabled the student does not navigate to second question");

            //TC row no. 40, 44 ...  Click on question dropdown to navigate the previous question..Discussion and Star tab should appear at the right side of the question preview page for first question
            new StaticAssignmentSubmit().navigateFromQuestionDropdown("1");//navigate question 1
            new Navigator().navigateToTab("Add to My Notes");//Star tab appears
            new Navigator().navigateToTab("Discussion");//discussion tab appears

            //TC row no. 45.... Add a discussion in Discussion tab through "+New Discussion" button...Posted Discussion should appear under Discussion and Star tab only
            String discussionText = new RandomString().randomstring(10);
            new Discussion().postDiscussion(discussionText);

            boolean isPresentInDiscussionTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Discussion", discussionText);
            if(isPresentInDiscussionTab == false)
                Assert.fail("Discussion is not present under discussion tab for Assignment with (immediate feedback + Allow Collaboration) enabled.");

            boolean isPresentInStarTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Add to My Notes", discussionText);
            if(isPresentInStarTab == false)
                Assert.fail("Discussion is not present under Star tab.");

            boolean isPresentInAssignmentsTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Assignments", discussionText);
            if(isPresentInAssignmentsTab == true)
                Assert.fail("Discussion is present under Assignments tab for Assignment with (immediate feedback + Allow Collaboration) enabled.");


            boolean isPresentInAboutTab = new Discussion().isDiscussionOrNotePresentUnderTabs("About", discussionText);
            if(isPresentInAboutTab == true)
                Assert.fail("Discussion is present under About tab for Assignment with (immediate feedback + Allow Collaboration) enabled.");

            //TC row no. 46... Add a note in Star tab through "+New Note" button...Posted note should appear under Star tab only
            new Navigator().navigateToTab("Add to My Notes");//go to Star tab
            String notesText = new RandomString().randomstring(10);
            new Discussion().postNote(notesText);

            boolean isNotePresentInDiscussionTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Discussion", notesText);
            if(isNotePresentInDiscussionTab == true)
                Assert.fail("Notes is present under Discussion tab for Assignment with (immediate feedback + Allow Collaboration) enabled.");

            boolean isNotePresentInStarTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Add to My Notes", notesText);
            if(isNotePresentInStarTab == false)
                Assert.fail("Notes is not present under Star tab for Assignment with (immediate feedback + Allow Collaboration) enabled.");

            boolean isNotePresentInAssignmentsTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Assignments", notesText);
            if(isNotePresentInAssignmentsTab == true)
                Assert.fail("notes is present under Assignments tab for Assignment with (immediate feedback + Allow Collaboration) enabled.");

            boolean isNotePresentInAboutTab = new Discussion().isDiscussionOrNotePresentUnderTabs("About", notesText);
            if(isNotePresentInAboutTab == true)
                Assert.fail("Note is present under About tab for Assignment with (immediate feedback + Allow Collaboration) enabled.");

            //TC row no. 47.."14. Attempt each question and Finish the assignment 15. Go to report page and click on question card for which the discussion and note were posted"
            //Posted discussion should be present under Discussion and Star tab
            new Assignment().submitAssignmentWithImmediateFeedBack(40);
            driver.findElement(By.linkText("Finish Assignment")).click();//click on finish link
            System.out.println("clicked finish1");
            new QuestionCard().clickOnCard("", 0);//click on 1st question's question card
            boolean isDiscussionPresentInDiscussionTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Discussion", discussionText);
            if(isDiscussionPresentInDiscussionTab == false)
                Assert.fail("Added discussion is not present under discussion tab after navigating from question card for Assignment with (immediate feedback + Allow Collaboration) enabled.");

            boolean isDiscussionPresentInStarTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Add to My Notes", discussionText);
            if(isDiscussionPresentInStarTab == false)
                Assert.fail("Added discussion is not present under discussion tab after navigating from question card for Assignment with (immediate feedback + Allow Collaboration) enabled.");

            //TC row no. 48...Posted note should be present under Star tab
            boolean isNotePresentInStarTab1 = new Discussion().isDiscussionOrNotePresentUnderTabs("Add to My Notes", notesText);
            if(isNotePresentInStarTab1 == false)
                Assert.fail("Added Note is not present under Star tab after navigating from question card for Assignment with (immediate feedback + Allow Collaboration) enabled.");

            new LoginUsingLTI().ltiLogin("40_2"); //login as student 2
            new Assignment().openAssignmentFromCourseStream("40");//open the assignment

            //TC row no. 49....16. Submit as student 2...Discussion posted by student 1 for question1 should appear under Discussion tab of student2
            new SelectAnswerAndSubmit().staticAssignment("A");//submit 1st question
            new StaticAssignmentSubmit().navigateFromQuestionDropdown("1");//navigate question 1
            boolean isDiscussionPresentForStudent2 = new Discussion().isDiscussionOrNotePresentUnderTabs("Discussion", discussionText);
            if(isDiscussionPresentForStudent2 == false)
                Assert.fail("Discussion added by student 1 on 1st question is not present under discussion tab for student 2.");

            //TC row no. 50
            boolean isNotePresentForStudent2 = new Discussion().isDiscussionOrNotePresentUnderTabs("Add to My Notes", notesText);
            if(isNotePresentForStudent2 == true)
                Assert.fail("Note added by student 1 on 1st question of Assignment with (immediate feedback + Allow Collaboration) enabled is present under Star tab for student 2.");


            //TC row no. 51..."18. Complete the assignment..19. Go to Course Stream"...Discussion posted by student1 should appear in Course Stream
            new Assignment().submitAssignmentWithImmediateFeedBack(40);//complete the assignment
            new Navigator().NavigateTo("Course Stream");
            boolean isPresent = new CourseStream().isPresentCourseStream("discussion");
            if(isPresent == false)
                Assert.fail("Discussion added by student 1 is absent Course Stream for student 2.");

            //TC row no. 52, 53..//20. Click on the jump-out icon of that posted discussion....Discussion posted by student 1 should appear under Discussion tab of student2
            driver.findElement(By.cssSelector("span[class='ls-jumpout-icon-link ls-jumpout-icon']")).click();//click on Jump out icon
            new WebDriverWait(driver, 360).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[title='Discussion']")));
            boolean isDiscussionPresentForStudent3 = new Discussion().isDiscussionOrNotePresentUnderTabs("Discussion", discussionText);
            if(isDiscussionPresentForStudent3 == false)
                Assert.fail("On clicking jump out icon from Course Stream, the Discussion added by student 1 on 1st question of Assignment with (immediate feedback + Allow Collaboration) enabled is not present under discussion tab for student 2.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case submitAssignmentWithCollaborationAndImmediateFeedbackEnabled in class SubmitAssignmentWithCollaborationAndImmediateFeedbackEnabled.", e);
        }
    }

}
