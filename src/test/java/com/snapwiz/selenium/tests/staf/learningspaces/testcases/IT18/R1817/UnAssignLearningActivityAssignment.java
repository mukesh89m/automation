package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R1817;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AssignLesson;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.MyActivity.MyActivity;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.EngagementReport;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*
 * Created by sumit on 6/1/15.
 */
public class UnAssignLearningActivityAssignment extends Driver{

    @Test(priority = 1, enabled = true)
    public void unAssignNotStartedLearningActivityAssignment()
    {
        try
        {

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            EngagementReport engagementReport = PageFactory.initElements(driver, EngagementReport.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            MyActivity myActivity = PageFactory.initElements(driver, MyActivity.class);

            String str;
            String popUp;
            String no;
            String yes;
            boolean popUpFound;
            String noAssignment;
            boolean classActivity;
            boolean csEntry;
            String notStartedCount;
            String tab;
            String noAssignmentMessage;
            String noActivityMessage;
            boolean assessmentFound;

            new LoginUsingLTI().ltiLogin("126_1");		//login as student1
            new LoginUsingLTI().ltiLogin("126_2");      //login as student2
            new LoginUsingLTI().ltiLogin("126");		//login as instructor
            new Navigator().NavigateTo("e-Textbook");
            new AssignLesson().assignAllLesson("126");  //assign all lesson
            new Navigator().NavigateTo("Current Assignments");
            //On the assignment summary page of the instructor,the current option of “Delete Assignment” should be changed to “Unassign Assignment”.
            //TC row no. 126
            str = currentAssignments.getUnAssignAssignment_button().getText();
            if(!str.contains("Un-assign Assignment")){
                Assert.fail("Un-assign Assignment link is not present in Current Assignments page after instructor assigns the assignment.");
            }
            //TC row no. 127
            // It should display a pop-up message "You are un-assigning the assignment for ALL students. Are you sure ?" with 'Yes' and 'No' buttons along with close(X) on the top.
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-Assign link
            popUp = currentAssignments.getUnAssignAssignment_popup().getText();
            Assert.assertEquals(popUp, "You are un-assigning the assignment for ALL students. Are you sure?", "Pop-up notification is not shown on clicking Un-Assign assignment.");

            no = currentAssignments.getNoForUnAssign_button().getText();
            Assert.assertEquals(no, "No", "No button is absent in Un-Assign assignment pop-up.");

            yes = currentAssignments.getYesForUnAssign_button().getText();

            Assert.assertEquals(yes, "Yes", "Yes button is absent in Un-Assign assignment pop-up.");
            //TC row no. 128
            //4. Click on Close(X)
            //Expected: The pop-up message should disappear.//
            currentAssignments.getCloseForUnAssign_icon().click();//click on Close icon
            popUpFound = new BooleanValue().presenceOfElement(101, By.className("delete-notification"));
            Assert.assertEquals(popUpFound, false, "The pop-up message doesn't disappear after clicking on Close icon");

            //TC row no. 129
            //5. Click on No
            //It should not Unassign the assignment and pop-up message should disappear.//
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-Assign link
            currentAssignments.getNoForUnAssign_button().click();//click on NO link on Un-assign assignment pop-up
            popUpFound = new BooleanValue().presenceOfElement(101, By.className("delete-notification"));
            Assert.assertEquals(popUpFound, false, "The pop-up message doesn't disappear after clicking on No link on Un-assign assignment pop-up");

            assessmentFound = new BooleanValue().presenceOfElement(126, By.className("ls-assignment-name-block"));
            Assert.assertEquals(assessmentFound, true, "clicking on NO link on Un-assign assignment pop-up it Unassigns the assignment");

            //TC row no. 130
            //6. Click on Yes
            //Expected: The assignment should get unassigned and the entry should get removed from Assignment Summary page.//
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-Assign link
            currentAssignments.getYesForUnAssign_button().click();//click on Yes link on Un-assign assignment pop-up
            noAssignment = currentAssignments.getNoAssignmentMessage().getText();
            if(!noAssignment.contains("No Assignment exists.")){
                Assert.fail("The assignment doesn't get unassigned and the entry doesn't get removed from Assignment Summary page.");
            }

            //TC row no. 131
            //7. Navigate to Dashboard
            //Expected: On Dashboard,the assignment entry should get removed from Course Stream tab
            new Navigator().NavigateTo("Dashboard");
            classActivity = new BooleanValue().presenceOfElement(101, By.id("course-stream"));
            Assert.assertEquals(classActivity, false, "On Dashboard,the assignment entry does not get removed from Course Stream tab.");
            //TC row no. 133
            //"8. Navigate to Course Stream Page from Main navigator"
            //Expected: The assignment entry should get removed  along with social elements from Course Stream Page
            new Navigator().NavigateTo("Course Stream");//navigate to Course Stream
            csEntry = new BooleanValue().presenceOfElement(101, By.cssSelector("li[class='ls-media ls-stream-post ls-stream-post--question']"));
            Assert.assertEquals(csEntry, false, "On CS ,the assignment entry does not get removed from Course Stream tab.");
            //TC row no. 134, 135
            //"11. Select the lesson page where assignment is associated
            //12. Go to Assignment tab"
            // Expected: The assignment entry in the assignment tab should be removed for that assignment.
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Assignments");
            assessmentFound = new BooleanValue().presenceOfElement(126, By.className("ls-assignment-name-block"));
            Assert.assertEquals(assessmentFound, false, "the assignment entry is not deleted In assignments tab of e-Textbook after it is un-assigned.");

            //TC row no. 136
            //13. Navigate to Engagement Report page
            //Items that you have assigned section should remove the contribution of that assignment for "Not-started" column
            new Navigator().NavigateTo("Engagement Report");//Engagement Report
            notStartedCount = engagementReport.getNotStartedCount().getText();
            Assert.assertNotEquals(notStartedCount, "2", "the assignment entry is not deleted In assignments tab of e-Textbook after it is un-assigned. ");

            new LoginUsingLTI().ltiLogin("126_1");		//login as student1
            //TC row no. 137
            // 14. Login as Student & navigate to Dashboard
            //Expected: On Student Dashboard, the assignment entry should get removed from Course Stream tab//
            classActivity = new BooleanValue().presenceOfElement(101, By.id("course-stream"));
            Assert.assertEquals(classActivity, false, "On Dashboard,the assignment entry does not get removed from Course Stream tab.");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments

            //TC row no 138
            // "15. Navigate to Assignments Page from Main navigator
            //The assignment should get Unassigned and the entry should get removed from Assignments page.//
            noAssignmentMessage = assignments.getNoAssignmentMessage().getText();
            if(!noAssignmentMessage.contains("Your instructor has not yet assigned you anything. Please check back at a later point of time.")){
                Assert.fail("The entry for that assignment is not removed from Assignments page of student side when its unassigned by the instructor.");
            }
            //TC row no 139
            //"16. Navigate to Course Stream Page from Main navigator
            //Expected: The card which shows the instructor posted that assignment should be removed with its social elements.//
            new Navigator().NavigateTo("Course Stream");//navigate to Course Stream
            csEntry = new BooleanValue().presenceOfElement(101, By.cssSelector("li[class='ls-media ls-stream-post ls-stream-post--question']"));
            Assert.assertEquals(csEntry, false, "On CS ,the assignment entry does not get removed from Course Stream tab of student side.");

            //TC row no 142
            //"17. Navigate to My Activity Page from Main navigator
            //Expected: The assignment card entry should get removed from My Activity Page.
            new Navigator().NavigateTo("My Activity");//navigate to My Activity
            noActivityMessage = myActivity.getNoActivityMessage().getText();
            if(!noActivityMessage.contains("It seems you have not done any learning activity yet.")){
                Assert.fail("The entry for that assignment is not removed from My Activity page of student side when its unassigned by the instructor.");
            }

            //TC row no 140, 141
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            new TOCShow().tocHide();
            tab = lessonPage.getAssignmentsTab().getText();
            Assert.assertNotEquals(tab, "Assignments", "The assignment entry does not get removed from assignment tab of e-textbook in student side.");

            new LoginUsingLTI().ltiLogin("126");		//login as instructor
            new Navigator().NavigateTo("e-Textbook");
            new AssignLesson().assignAllLesson("126");  //assign all lesson again

            new LoginUsingLTI().ltiLogin("126_1");		//login as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getLessonAssignment().click();//open lesson assignment to make it in progress

            new LoginUsingLTI().ltiLogin("126");		//login as instructor
            new Navigator().NavigateTo("Current Assignments");
            //On the assignment summary page of the instructor,the current option of “Delete Assignment” should be changed to “Unassign Assignment”.
            //TC row no. 143
            str = currentAssignments.getUnAssignAssignment_button().getText();
            if(!str.contains("Un-assign Assignment")){
                Assert.fail("Un-assign Assignment link is not present in Current Assignments page after instructor assigns the assignment.");
            }
            //TC row no. 144
            // It should display a pop-up message "You are un-assigning the assignment for ALL students. Are you sure ?" with 'Yes' and 'No' buttons along with close(X) on the top.
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-Assign link
            popUp = currentAssignments.getUnAssignAssignment_popup().getText();
            Assert.assertEquals(popUp, "You are un-assigning the assignment for ALL students. Are you sure?", "Pop-up notification is not shown on clicking Un-Assign assignment.");

            no = currentAssignments.getNoForUnAssign_button().getText();
            Assert.assertEquals(no, "No", "No button is absent in Un-Assign assignment pop-up.");

            yes = currentAssignments.getYesForUnAssign_button().getText();

            Assert.assertEquals(yes, "Yes", "Yes button is absent in Un-Assign assignment pop-up.");
            //TC row no. 145
            //4. Click on Close(X)
            //Expected: The pop-up message should disappear.//
            currentAssignments.getCloseForUnAssign_icon().click();//click on Close icon
            popUpFound = new BooleanValue().presenceOfElement(101, By.className("delete-notification"));
            Assert.assertEquals(popUpFound, false, "The pop-up message doesn't disappear after clicking on Close icon");

            //TC row no. 146
            //5. Click on No
            //It should not Unassign the assignment and pop-up message should disappear.//
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-Assign link
            currentAssignments.getNoForUnAssign_button().click();//click on NO link on Un-assign assignment pop-up
            popUpFound = new BooleanValue().presenceOfElement(101, By.className("delete-notification"));
            Assert.assertEquals(popUpFound, false, "The pop-up message doesn't disappear after clicking on No link on Un-assign assignment pop-up");

            //name = currentAssignments.getAssignmentName().getText();
            assessmentFound = new BooleanValue().presenceOfElement(126, By.className("ls-assignment-name-block"));
            Assert.assertEquals(assessmentFound, true, "clicking on NO link on Un-assign assignment pop-up it Unassigns the assignment");

            //TC row no. 147
            //6. Click on Yes
            //Expected: The assignment should get unassigned and the entry should get removed from Assignment Summary page.//
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-Assign link
            currentAssignments.getYesForUnAssign_button().click();//click on Yes link on Un-assign assignment pop-up
            noAssignment = currentAssignments.getNoAssignmentMessage().getText();
            if(!noAssignment.contains("No Assignment exists.")){
                Assert.fail("The assignment doesn't get unassigned and the entry doesn't get removed from Assignment Summary page.");
            }

            //TC row no. 148
            //7. Navigate to Dashboard
            //Expected: On Dashboard,the assignment entry should get removed from Course Stream tab
            new Navigator().NavigateTo("Dashboard");
            classActivity = new BooleanValue().presenceOfElement(101, By.id("course-stream"));
            Assert.assertEquals(classActivity, false, "On Dashboard,the assignment entry does not get removed from Course Stream tab.");
            //TC row no. 149
            //"8. Navigate to Course Stream Page from Main navigator"
            //Expected: The assignment entry should get removed  along with social elements from Course Stream Page
            new Navigator().NavigateTo("Course Stream");//navigate to Course Stream
            csEntry = new BooleanValue().presenceOfElement(101, By.cssSelector("li[class='ls-media ls-stream-post ls-stream-post--question']"));
            Assert.assertEquals(csEntry, false, "On CS ,the assignment entry does not get removed from Course Stream tab.");
            //TC row no. 151, 152
            //"11. Select the lesson page where assignment is associated
            //12. Go to Assignment tab"
            // Expected: The assignment entry in the assignment tab should be removed for that assignment.
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Assignments");
            assessmentFound = new BooleanValue().presenceOfElement(126, By.className("ls-assignment-name-block"));
            Assert.assertEquals(assessmentFound, false, "the assignment entry is not deleted In assignments tab of e-Textbook after it is un-assigned. ");

            //TC row no. 153
            //13. Navigate to Engagement Report page
            //Items that you have assigned section should remove the contribution of that assignment for "Not-started" column
            new Navigator().NavigateTo("Engagement Report");//Engagement Report
            notStartedCount = engagementReport.getNotStartedCount().getText();
            Assert.assertNotEquals(notStartedCount, "2", "the assignment entry is not deleted In assignments tab of e-Textbook after it is un-assigned. ");

            new LoginUsingLTI().ltiLogin("126_1");		//login as student1
            //TC row no. 154
            // 14. Login as Student & navigate to Dashboard
            //Expected: On Student Dashboard, the assignment entry should get removed from Course Stream tab//
            classActivity = new BooleanValue().presenceOfElement(101, By.id("course-stream"));
            Assert.assertEquals(classActivity, false, "On Dashboard,the assignment entry does not get removed from Course Stream tab.");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments

            //TC row no 156
            // "15. Navigate to Assignments Page from Main navigator
            //The assignment should get Unassigned and the entry should get removed from Assignments page.//
            noAssignmentMessage = assignments.getNoAssignmentMessage().getText();
            if(!noAssignmentMessage.contains("Your instructor has not yet assigned you anything. Please check back at a later point of time.")){
                Assert.fail("The entry for that assignment is not removed from Assignments page of student side when its unassigned by the instructor.");
            }
            //TC row no 157
            //"16. Navigate to Course Stream Page from Main navigator
            //Expected: The card which shows the instructor posted that assignment should be removed with its social elements.//
            new Navigator().NavigateTo("Course Stream");//navigate to Course Stream
            csEntry = new BooleanValue().presenceOfElement(101, By.cssSelector("li[class='ls-media ls-stream-post ls-stream-post--question']"));
            Assert.assertEquals(csEntry, false, "On CS ,the assignment entry does not get removed from Course Stream tab of student side.");


            //TC row no 159, 160
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            new TOCShow().tocHide();
            tab = lessonPage.getAssignmentsTab().getText();
            Assert.assertNotEquals(tab, "Assignments", "The assignment entry does not get removed from assignment tab of e-textbook in student side.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class UnAssignLearningActivityAssignment in method unAssignLearningActivityAssignment.", e);
        }
    }

}
