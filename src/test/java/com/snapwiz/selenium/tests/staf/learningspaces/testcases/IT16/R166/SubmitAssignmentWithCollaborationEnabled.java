package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R166;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by Sumit on 8/13/2014.
 */
public class SubmitAssignmentWithCollaborationEnabled extends Driver{

    @Test
    public void submitAssignmentWithCollaborationEnabled()
    {
        try
        {
            String policyname = ReadTestData.readDataByTagName("", "policyname", "29");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "29");
            new Assignment().create(29);
            new Assignment().addQuestions(29, "qtn-type-true-false-img", assessmentname);
            new Assignment().addQuestionsWithCustomizedQuestionText(29, "qtn-type-true-false-img", assessmentname, 3);

            new LoginUsingLTI().ltiLogin("29_1");//create a student1
            new LoginUsingLTI().ltiLogin("29_2");//create a student2

            new LoginUsingLTI().ltiLogin("29");
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(policyname, "29 Policy description text", "1",null, false, "1", "", null, "", "", "");
            new Assignment().assignToStudent(29);

            new LoginUsingLTI().ltiLogin("29_1");//login as student1
            new Assignment().openAssignmentFromCourseStream("29");//open the assignment

            //TC row no. 29....13. Submit the first question..Student should navigate to next question
            new SelectAnswerAndSubmit().staticAssignment("A");//submit 1st question
            String qNo = driver.findElement(By.className("current-question-index")).getText();
            if(!qNo.contains("2"))
                Assert.fail("After submitting the 1st question with assignment policy the student does not navigate to second question");

            //TC row no. 30 ... 14. Click on question dropdown to navigate the previous question..Discussion and Star tab should appear at the right side of the question preview page for first question
            new StaticAssignmentSubmit().navigateFromQuestionDropdown("1");//navigate question 1
            new Navigator().navigateToTab("Add to My Notes");//Star tab appears
            new Navigator().navigateToTab("Discussion");//discussion tab appears

            //TC row no. 31....15. Add a discussion in Discussion tab through "+New Discussion" button...Posted Discussion should appear under Discussion and Star tab only
            String discussionText = new RandomString().randomstring(10);
            new Discussion().postDiscussion(discussionText);

            boolean isPresentInDiscussionTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Discussion", discussionText);
            if(isPresentInDiscussionTab == false)
                Assert.fail("Discussion is not present under discussion tab.");

            boolean isPresentInStarTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Add to My Notes", discussionText);
            if(isPresentInStarTab == false)
                Assert.fail("Discussion is not present under Star tab.");

            boolean isPresentInAssignmentsTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Assignments", discussionText);
            if(isPresentInAssignmentsTab == true)
                Assert.fail("Discussion is present under Assignments tab.");


            boolean isPresentInAboutTab = new Discussion().isDiscussionOrNotePresentUnderTabs("About", discussionText);
            if(isPresentInAboutTab == true)
                Assert.fail("Discussion is present under About tab.");

            //TC row no. 32...16. Add a note in Star tab through "+New Note" button...Posted note should appear under Star tab only
            new Navigator().navigateToTab("Add to My Notes");//go to Star tab
            String notesText = new RandomString().randomstring(10);
            new Discussion().postNote(notesText);

            boolean isNotePresentInDiscussionTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Discussion", notesText);
            if(isNotePresentInDiscussionTab == true)
                Assert.fail("Notes is present under Discussion tab.");

            boolean isNotePresentInStarTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Add to My Notes", notesText);
            if(isNotePresentInStarTab == false)
                Assert.fail("Notes is not present under Star tab.");

            boolean isNotePresentInAssignmentsTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Assignments", notesText);
            if(isNotePresentInAssignmentsTab == true)
                Assert.fail("notes is present under Assignments tab.");

            boolean isNotePresentInAboutTab = new Discussion().isDiscussionOrNotePresentUnderTabs("About", notesText);
            if(isNotePresentInAboutTab == true)
                Assert.fail("Note is present under About tab.");

            //TC row no. 33.."17. Attempt each question and Finish the assignment18. Go to report page and click on question card for which the discussion and note were posted"
            //Posted discussion should be present under Discussion and Star tab
            new Assignment().submitAssignmentAsStudent(29);
            new QuestionCard().clickOnCard("", 0);//click on 1st question's question card
            boolean isDiscussionPresentInDiscussionTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Discussion", discussionText);
            if(isDiscussionPresentInDiscussionTab == false)
                Assert.fail("Added discussion is not present under discussion tab after navigating from question card.");

            boolean isDiscussionPresentInStarTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Add to My Notes", discussionText);
            if(isDiscussionPresentInStarTab == false)
                Assert.fail("Added discussion is not present under discussion tab after navigating from question card.");

            //TC row no. 34...Posted note should be present under Star tab
            boolean isNotePresentInStarTab1 = new Discussion().isDiscussionOrNotePresentUnderTabs("Add to My Notes", notesText);
            if(isNotePresentInStarTab1 == false)
                Assert.fail("Added Note is not present under Star tab after navigating from question card.");

            new LoginUsingLTI().ltiLogin("29_2"); //login as student 2
            new Assignment().openAssignmentFromCourseStream("29");//open the assignment

            //TC row no. 35....19. Submit as student 2...Discussion posted by student 1 for question1 should appear under Discussion tab of student2
            new SelectAnswerAndSubmit().staticAssignment("A");//submit 1st question
            new StaticAssignmentSubmit().navigateFromQuestionDropdown("1");//navigate question 1
            boolean isDiscussionPresentForStudent2 = new Discussion().isDiscussionOrNotePresentUnderTabs("Discussion", discussionText);
            if(isDiscussionPresentForStudent2 == false)
                Assert.fail("Discussion added by student 1 on 1st question is not present under discussion tab for student 2.");

            boolean isNotePresentForStudent2 = new Discussion().isDiscussionOrNotePresentUnderTabs("Add to My Notes", notesText);
            if(isNotePresentForStudent2 == true)
                Assert.fail("Note added by student 1 on 1st question is present under Star tab for student 2.");


            //TC row no. 37..."22. Complete the assignment..23. Go to Course Stream"...Discussion posted by student1 should appear in Course Stream
            new Assignment().submitAssignmentAsStudent(29);//complete the assignment
            new Navigator().NavigateTo("Course Stream");
            boolean isPresent = new CourseStream().isPresentCourseStream("discussion");
            if(isPresent == false)
                Assert.fail("Discussion added by student 1 is absent Course Stream for student 2.");

            //TC row no. 38, 39..//23. Click on the jump-out icon of that posted discussion....Discussion posted by student 1 should appear under Discussion tab of student2
            driver.findElement(By.cssSelector("span[class='ls-jumpout-icon-link ls-jumpout-icon']")).click();//click on Jump out icon
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[title='Discussion']")));
            boolean isDiscussionPresentForStudent3 = new Discussion().isDiscussionOrNotePresentUnderTabs("Discussion", discussionText);
            if(isDiscussionPresentForStudent3 == false)
                Assert.fail("On clicking jump out icon from Course Stream, the Discussion added by student 1 on 1st question is not present under discussion tab for student 2.");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case submitAssignmentWithCollaborationEnabled in class SubmitAssignmentWithCollaborationEnabled.", e);
        }
    }

}
