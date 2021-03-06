package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R166;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Sumit on 8/19/2014.
 */
public class MentorDisablesAllowCollaborationAndImmediateFeedback extends Driver {

    @Test
    public void mentorDisablesAllowCollaborationAndImmediateFeedback()
    {
        try
        {
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", "134");
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "134");
            new Assignment().create(134);
            new Assignment().addQuestionsWithCustomizedQuestionText(134, "qtn-type-true-false-img", assessmentname, 3);

            new LoginUsingLTI().ltiLogin("134");//login as mentor
            new Navigator().NavigateTo("Policies");//go to Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicy, "", "1", "", false, "1", "", "", "", "", "");
            new AssignmentPolicy().updateAllowCollaboration("134", false);

            new LoginUsingLTI().ltiLogin("134_1"); //Creating user student 1
            new LoginUsingLTI().ltiLogin("134_2");//Creating user student 2

            new LoginUsingLTI().ltiLogin("134"); //  Logging in as instructor
            new Assignment().assignToStudent(134);  //Assigning assignment

            new LoginUsingLTI().ltiLogin("134_1"); //login as student 1
            new Assignment().openAssignmentFromCourseStream("134");//open the assignment

            //TC row no. 134....6. Submit the first question..Student should navigate to next question
            new SelectAnswerAndSubmit().staticAssignment("A");//submit 1st question
            String qNo = driver.findElement(By.className("current-question-index")).getText();
            if(!qNo.contains("2"))
                Assert.fail("After submitting the 1st question with policy \"Allow Collaboration\" Disabled  the student does not navigate to second question.");

            //TC row no. 135 ... 7. Click on question dropdown to navigate the previous question..Discussion and Star tab should not appear
            new StaticAssignmentSubmit().navigateFromQuestionDropdown("1");//navigate question 1
            boolean isTabPresent = false;

            List<WebElement> allTabs = driver.findElements(By.className("tabs"));
            for(WebElement tab: allTabs)
            {
                System.out.println("tabd: "+tab.getText());
                if(tab.getText().equals("Discussion"))
                {
                    isTabPresent = true;
                    break;
                }
            }
            if(isTabPresent == true)
                Assert.fail("The discussion tab for attempted question appears for assignment with policy \"Allow Collaboration\" Disabled.");
            //TC row no. 136..""8. Finish the assignment and go to report page  9. Click on any question card ...Discussion and Star tab should appear
            new Assignment().submitAssignmentAsStudent(134);
            new QuestionCard().clickOnCard("", 0);//click on 1st question's question card
            new Navigator().navigateToTab("Add to My Notes");//Star tab appears
            new Navigator().navigateToTab("Discussion");//discussion tab appears

            //TC row no. 136.. ..10. Add a discussion in discussion tab..Posted Discussion should appear under Discussion and Star tab
            String discussionText = new RandomString().randomstring(10);
            new Discussion().postDiscussion(discussionText);

            boolean isPresentInDiscussionTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Discussion", discussionText);
            if(isPresentInDiscussionTab == false)
                Assert.fail("Discussion posted for question of assignment with policy \"Allow Collaboration\" Disabled is not present under discussion tab.");

            boolean isPresentInStarTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Add to My Notes", discussionText);
            if(isPresentInStarTab == false)
                Assert.fail("Discussion posted for question of assignment with policy \"Allow Collaboration\" Disabled is not present under Star tab.");

            boolean isPresentInAssignmentsTab = new Discussion().isDiscussionOrNotePresentUnderTabs("Assignments", discussionText);
            if(isPresentInAssignmentsTab == true)
                Assert.fail("Discussion posted for question of assignment with policy \"Allow Collaboration\" Disabled is present under Assignments tab.");

            //TC row no. 137..."11. Login as student2. 12. Go to course stream page"...Posted Discussion by student1 should not appear in Course Stream page
            new LoginUsingLTI().ltiLogin("134_2");//login as student 2
            new Navigator().NavigateTo("Course Stream");
            boolean isPresent = new CourseStream().isPresentCourseStream("discussion");
            if(isPresent == true)
                Assert.fail("Discussion posted for question of assignment with policy \"Allow Collaboration\" Disabled by student 1 is present in Course Stream for student 2.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case mentorDisablesAllowCollaborationAndImmediateFeedback in class MentorDisablesAllowCollaborationAndImmediateFeedback.", e);
        }
    }

}
