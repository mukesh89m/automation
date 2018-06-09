package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R1817;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CourseStream.CourseStreamPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.dashboard.Dashboard;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.EngagementReport;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*
 * Created by Sumit on 6/1/15.
 */
public class UnAssignDWAssignment extends Driver {

    @Test(priority = 1, enabled = true)
    public void unAssignGradableDWAssignment()
    {
        try
        {

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            EngagementReport engagementReport = PageFactory.initElements(driver, EngagementReport.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            Dashboard dashboard = PageFactory.initElements(driver, Dashboard.class);
            CourseStreamPage courseStreamPage = PageFactory.initElements(driver, CourseStreamPage.class);


            String str;
            String popUp;
            String no;
            String yes;
            boolean popUpFound;
            String noAssignment;
            String classActivity;
            boolean csEntry;
            boolean courseStreamTileEntry;
            String csEntryName;
            String notStartedCount;
            String tab;
            String noAssignmentMessage;
            boolean assessmentFound;

            new LoginUsingLTI().ltiLogin("165_1");		//login as student1
            new LoginUsingLTI().ltiLogin("165_2");      //login as student2
            new LoginUsingLTI().ltiLogin("165");		//login as instructor
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TopicOpen().openLessonWithDiscussionWidget();
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(165);
            new Navigator().NavigateTo("Current Assignments");
            //On the assignment summary page of the instructor,the current option of “Delete Assignment” should be changed to “Unassign Assignment”.
            //TC row no. 165
            str = currentAssignments.getUnAssignAssignment_button().getText();
            if(!str.contains("Un-assign Assignment")){
                Assert.fail("Un-assign Assignment link is not present in Current Assignments page after instructor assigns the assignment.");
            }
            //TC row no. 166
            // It should display a pop-up message "You are un-assigning the assignment for ALL students. Are you sure ?" with 'Yes' and 'No' buttons along with close(X) on the top.
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-Assign link
            popUp = currentAssignments.getUnAssignAssignment_popup().getText();
            Assert.assertEquals(popUp, "You are un-assigning the assignment for ALL students. Are you sure?", "Pop-up notification is not shown on clicking Un-Assign assignment.");

            no = currentAssignments.getNoForUnAssign_button().getText();
            Assert.assertEquals(no, "No", "No button is absent in Un-Assign assignment pop-up.");

            yes = currentAssignments.getYesForUnAssign_button().getText();

            Assert.assertEquals(yes, "Yes", "Yes button is absent in Un-Assign assignment pop-up.");
            //TC row no. 167
            //4. Click on Close(X)
            //Expected: The pop-up message should disappear.//
            currentAssignments.getCloseForUnAssign_icon().click();//click on Close icon
            popUpFound = new BooleanValue().presenceOfElement(165, By.className("delete-notification"));
            Assert.assertEquals(popUpFound, false, "The pop-up message doesn't disappear after clicking on Close icon");

            //TC row no. 168
            //5. Click on No
            //It should not Unassign the assignment and pop-up message should disappear.//
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-Assign link
            currentAssignments.getNoForUnAssign_button().click();//click on NO link on Un-assign assignment pop-up
            popUpFound = new BooleanValue().presenceOfElement(165, By.className("delete-notification"));
            Assert.assertEquals(popUpFound, false, "The pop-up message doesn't disappear after clicking on No link on Un-assign assignment pop-up");

            assessmentFound = new BooleanValue().presenceOfElement(165, By.className("ls-assignment-name-block"));
            Assert.assertEquals(assessmentFound, true, "clicking on NO link on Un-assign assignment pop-up it Unassigns the assignment");

            //TC row no. 169
            //6. Click on Yes
            //Expected: The assignment should get unassigned and the entry should get removed from Assignment Summary page.//
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-Assign link
            currentAssignments.getYesForUnAssign_button().click();//click on Yes link on Un-assign assignment pop-up
            noAssignment = currentAssignments.getNoAssignmentMessage().getText();
            if(!noAssignment.contains("No Assignment exists.")){
                Assert.fail("The assignment doesn't get unassigned and the entry doesn't get removed from Assignment Summary page.");
            }

            //TC row no. 170
            //7. Navigate to Dashboard
            //Expected: On Dashboard,the assignment entry should get removed from Course Stream tab
            new Navigator().NavigateTo("Dashboard");
            courseStreamTileEntry = new BooleanValue().presenceOfElement(101, By.id("course-stream"));
            Assert.assertEquals(courseStreamTileEntry, false, "On Dashboard,the assignment entry does not get removed from Course Stream tab.");
            //TC row no. 172
            //"8. Navigate to Course Stream Page from Main navigator"
            //Expected: The assignment entry should get removed  along with social elements from Course Stream Page
            new Navigator().NavigateTo("Course Stream");//navigate to Course Stream
            csEntry = new BooleanValue().presenceOfElement(165, By.cssSelector("li[class='ls-media ls-stream-post ls-stream-post--question']"));
            Assert.assertEquals(csEntry, false, "On CS ,the assignment entry does not get removed from Course Stream tab.");
            //TC row no. 173, 174
            //"11. Select the lesson page where assignment is associated
            //12. Go to Assignment tab"
            // Expected: The assignment entry in the assignment tab should be removed for that assignment.
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            new TopicOpen().openLessonWithDiscussionWidget();
            new Navigator().navigateToTab("Assignments");
            assessmentFound = new BooleanValue().presenceOfElement(165, By.className("ls-assignment-name-block"));
            Assert.assertEquals(assessmentFound, false, "the assignment entry is not deleted In assignments tab of e-Textbook after it is un-assigned.");

            //TC row no. 175
            //13. Navigate to Engagement Report page
            //Items that you have assigned section should remove the contribution of that assignment for "Not-started" column
            new Navigator().NavigateTo("Engagement Report");//Engagement Report
            notStartedCount = engagementReport.getNotStartedCount().getText();
            Assert.assertNotEquals(notStartedCount, "2", "the assignment entry is not deleted In assignments tab of e-Textbook after it is un-assigned. ");

            new LoginUsingLTI().ltiLogin("165_1");		//login as student1
            //TC row no. 176
            // 14. Login as Student & navigate to Dashboard
            //Expected: On Student Dashboard, the assignment entry should get removed from Course Stream tab//
            courseStreamTileEntry = new BooleanValue().presenceOfElement(101, By.id("course-stream"));
            Assert.assertEquals(courseStreamTileEntry, false, "On Dashboard,the assignment entry does not get removed from Course Stream tab.");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments

            //TC row no 177
            // "15. Navigate to Assignments Page from Main navigator
            //The assignment should get Unassigned and the entry should get removed from Assignments page.//
            noAssignmentMessage = assignments.getNoAssignmentMessage().getText();
            if(!noAssignmentMessage.contains("Your instructor has not yet assigned you anything. Please check back at a later point of time.")){
                Assert.fail("The entry for that assignment is not removed from Assignments page of student side when its unassigned by the instructor.");
            }
            //TC row no 178
            //"16. Navigate to Course Stream Page from Main navigator
            //Expected: The card which shows the instructor posted that assignment should be removed with its social elements.//
            new Navigator().NavigateTo("Course Stream");//navigate to Course Stream
            csEntry = new BooleanValue().presenceOfElement(165, By.cssSelector("li[class='ls-media ls-stream-post ls-stream-post--question']"));
            Assert.assertEquals(csEntry, false, "On CS ,the assignment entry does not get removed from Course Stream tab of student side.");

            //TC row no 180, 181
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            new TopicOpen().openLessonWithDiscussionWidget();
            tab = lessonPage.getAssignmentsTab().getText();
            Assert.assertNotEquals(tab, "Assignments", "The assignment entry does not get removed from assignment tab of e-textbook in student side.");

            new LoginUsingLTI().ltiLogin("165");		//login as instructor
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TopicOpen().openLessonWithDiscussionWidget();
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(165);

            new LoginUsingLTI().ltiLogin("165_1");		//login as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getLessonAssignment().click();//open DW assignment
            String perspective = new RandomString().randomstring(15);
            new DiscussionWidget().addPerspectiveForDWAssignment(perspective);//submit the DW assignment

            new LoginUsingLTI().ltiLogin("165");		//login as instructor
            new Navigator().NavigateTo("Current Assignments");
            //On the assignment summary page of the instructor,the current option of “Delete Assignment” should be changed to “Unassign Assignment”.
            //TC row no. 182
            str = currentAssignments.getUnAssignAssignment_button().getText();
            if(!str.contains("Un-assign Assignment")){
                Assert.fail("Un-assign Assignment link is not present in Current Assignments page after instructor assigns the assignment.");
            }
            //TC row no. 183
            // It should display a pop-up message "You are un-assigning the assignment for ALL students. Are you sure ?" with 'Yes' and 'No' buttons along with close(X) on the top.
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-Assign link
            popUp = currentAssignments.getUnAssignAssignment_popup().getText();
            Assert.assertEquals(popUp, "You are un-assigning the assignment for ALL students. Are you sure?", "Pop-up notification is not shown on clicking Un-Assign assignment.");

            no = currentAssignments.getNoForUnAssign_button().getText();
            Assert.assertEquals(no, "No", "No button is absent in Un-Assign assignment pop-up.");

            yes = currentAssignments.getYesForUnAssign_button().getText();

            Assert.assertEquals(yes, "Yes", "Yes button is absent in Un-Assign assignment pop-up.");
            //TC row no. 184
            //4. Click on Close(X)
            //Expected: The pop-up message should disappear.//
            currentAssignments.getCloseForUnAssign_icon().click();//click on Close icon
            popUpFound = new BooleanValue().presenceOfElement(165, By.className("delete-notification"));
            Assert.assertEquals(popUpFound, false, "The pop-up message doesn't disappear after clicking on Close icon");

            //TC row no. 185
            //5. Click on No
            //It should not Unassign the assignment and pop-up message should disappear.//
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-Assign link
            currentAssignments.getNoForUnAssign_button().click();//click on NO link on Un-assign assignment pop-up
            popUpFound = new BooleanValue().presenceOfElement(165, By.className("delete-notification"));
            Assert.assertEquals(popUpFound, false, "The pop-up message doesn't disappear after clicking on No link on Un-assign assignment pop-up");

            assessmentFound = new BooleanValue().presenceOfElement(165, By.className("ls-assignment-name-block"));
            Assert.assertEquals(assessmentFound, true, "clicking on NO link on Un-assign assignment pop-up it Unassigns the assignment");

            //TC row no. 186
            //6. Click on Yes
            //Expected: The assignment should get unassigned and the entry should get removed from Assignment Summary page.//
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-Assign link
            currentAssignments.getYesForUnAssign_button().click();//click on Yes link on Un-assign assignment pop-up
            noAssignment = currentAssignments.getNoAssignmentMessage().getText();
            if(!noAssignment.contains("No Assignment exists.")){
                Assert.fail("The assignment doesn't get unassigned and the entry doesn't get removed from Assignment Summary page.");
            }

            //TC row no. 187
            //7. Navigate to Dashboard
            //Expected: On Dashboard,the assignment entry should get removed from Course Stream tab
            new Navigator().NavigateTo("Dashboard");
            classActivity = dashboard.getEntryInCourseStreamTile().get(0).getText();
            Assert.assertNotEquals(classActivity, "posted an assignment", "On Dashboard,the assignment entry does not get removed from Course Stream tab.");
            //TC row no. 189
            //"8. Navigate to Course Stream Page from Main navigator"
            //Expected: The assignment entry should get removed  along with social elements from Course Stream Page
            new Navigator().NavigateTo("Course Stream");//navigate to Course Stream
            csEntryName = courseStreamPage.getPostedItemType().get(0).getText();
            Assert.assertNotEquals(csEntryName, "posted an assignment", "On CS ,the assignment entry does not get removed from Course Stream tab.");
            //TC row no. 190, 191
            //"11. Select the lesson page where assignment is associated
            //12. Go to Assignment tab"
            // Expected: The assignment entry in the assignment tab should be removed for that assignment.
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            new TopicOpen().openLessonWithDiscussionWidget();
            new Navigator().navigateToTab("Assignments");
            assessmentFound = new BooleanValue().presenceOfElement(165, By.className("ls-assignment-name-block"));
            Assert.assertEquals(assessmentFound, false, "the assignment entry is not deleted In assignments tab of e-Textbook after it is un-assigned. ");

            //TC row no. 192
            //13. Navigate to Engagement Report page
            //Items that you have assigned section should remove the contribution of that assignment for "Not-started" column
            new Navigator().NavigateTo("Engagement Report");//Engagement Report
            notStartedCount = engagementReport.getNotStartedCount().getText();
            Assert.assertNotEquals(notStartedCount, "2", "the assignment entry is not deleted In assignments tab of e-Textbook after it is un-assigned. ");

            new LoginUsingLTI().ltiLogin("165_1");		//login as student1
            //TC row no. 193
            // 14. Login as Student & navigate to Dashboard
            //Expected: On Student Dashboard, the assignment entry should get removed from Course Stream tab//
            classActivity = dashboard.getEntryInCourseStreamTile().get(0).getText();
            Assert.assertNotEquals(classActivity, "posted an assignment", "On Dashboard,the assignment entry does not get removed from Course Stream tab.");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments

            //TC row no 195
            // "15. Navigate to Assignments Page from Main navigator
            //The assignment should get Unassigned and the entry should get removed from Assignments page.//
            noAssignmentMessage = assignments.getNoAssignmentMessage().getText();
            if(!noAssignmentMessage.contains("Your instructor has not yet assigned you anything. Please check back at a later point of time.")){
                Assert.fail("The entry for that assignment is not removed from Assignments page of student side when its unassigned by the instructor.");
            }
            //TC row no 196
            //"16. Navigate to Course Stream Page from Main navigator
            //Expected: The card which shows the instructor posted that assignment should be removed with its social elements.//
            new Navigator().NavigateTo("Course Stream");//navigate to Course Stream
            csEntryName = courseStreamPage.getPostedItemType().get(0).getText();
            Assert.assertNotEquals(csEntryName, "posted an assignment", "On CS ,the assignment entry does not get removed from Course Stream tab of student side.");


            //TC row no 198, 199
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            new TopicOpen().openLessonWithDiscussionWidget();
            tab = lessonPage.getAssignmentsTab().getText();
            Assert.assertNotEquals(tab, "Assignments", "The assignment entry does not get removed from assignment tab of e-textbook in student side.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class UnAssignDWAssignment in method unAssignGradableDWAssignment.", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void unAssignNonGradableDWAssignment()
    {
        try
        {

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            EngagementReport engagementReport = PageFactory.initElements(driver, EngagementReport.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);

            String str;
            String popUp;
            String no;
            String yes;
            boolean popUpFound;
            String noAssignment;
            boolean csEntry;
            boolean courseStreamTileEntry;
            String notStartedCount;
            String tab;
            String noAssignmentMessage;
            boolean assessmentFound;

            new LoginUsingLTI().ltiLogin("166_1");		//login as student1
            new LoginUsingLTI().ltiLogin("166_2");      //login as student2
            new LoginUsingLTI().ltiLogin("166");		//login as instructor
            new Navigator().NavigateTo("eTextbook");	//navigate to etextbook
            new TopicOpen().openLessonWithDiscussionWidget();
            new DiscussionWidget().assignDiscussionWidgetWithDefaultClassSection(166);
            new Navigator().NavigateTo("Current Assignments");
            //On the assignment summary page of the instructor,the current option of “Delete Assignment” should be changed to “Unassign Assignment”.
            //TC row no. 166
            str = currentAssignments.getUnAssignAssignment_button().getText();
            if(!str.contains("Un-assign Assignment")){
                Assert.fail("Un-assign Assignment link is not present in Current Assignments page after instructor assigns the assignment.");
            }
            //TC row no. 166
            // It should display a pop-up message "You are un-assigning the assignment for ALL students. Are you sure ?" with 'Yes' and 'No' buttons along with close(X) on the top.
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-Assign link
            popUp = currentAssignments.getUnAssignAssignment_popup().getText();
            Assert.assertEquals(popUp, "You are un-assigning the assignment for ALL students. Are you sure?", "Pop-up notification is not shown on clicking Un-Assign assignment.");

            no = currentAssignments.getNoForUnAssign_button().getText();
            Assert.assertEquals(no, "No", "No button is absent in Un-Assign assignment pop-up.");

            yes = currentAssignments.getYesForUnAssign_button().getText();

            Assert.assertEquals(yes, "Yes", "Yes button is absent in Un-Assign assignment pop-up.");
            //TC row no. 167
            //4. Click on Close(X)
            //Expected: The pop-up message should disappear.//
            currentAssignments.getCloseForUnAssign_icon().click();//click on Close icon
            popUpFound = new BooleanValue().presenceOfElement(166, By.className("delete-notification"));
            Assert.assertEquals(popUpFound, false, "The pop-up message doesn't disappear after clicking on Close icon");

            //TC row no. 168
            //5. Click on No
            //It should not Unassign the assignment and pop-up message should disappear.//
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-Assign link
            currentAssignments.getNoForUnAssign_button().click();//click on NO link on Un-assign assignment pop-up
            popUpFound = new BooleanValue().presenceOfElement(166, By.className("delete-notification"));
            Assert.assertEquals(popUpFound, false, "The pop-up message doesn't disappear after clicking on No link on Un-assign assignment pop-up");

            assessmentFound = new BooleanValue().presenceOfElement(166, By.className("ls-assignment-name-block"));
            Assert.assertEquals(assessmentFound, true, "clicking on NO link on Un-assign assignment pop-up it Unassigns the assignment");

            //TC row no. 169
            //6. Click on Yes
            //Expected: The assignment should get unassigned and the entry should get removed from Assignment Summary page.//
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-Assign link
            currentAssignments.getYesForUnAssign_button().click();//click on Yes link on Un-assign assignment pop-up
            noAssignment = currentAssignments.getNoAssignmentMessage().getText();
            if(!noAssignment.contains("No Assignment exists.")){
                Assert.fail("The assignment doesn't get unassigned and the entry doesn't get removed from Assignment Summary page.");
            }

            //TC row no. 170
            //7. Navigate to Dashboard
            //Expected: On Dashboard,the assignment entry should get removed from Course Stream tab
            new Navigator().NavigateTo("Dashboard");
            courseStreamTileEntry = new BooleanValue().presenceOfElement(101, By.id("course-stream"));
            Assert.assertEquals(courseStreamTileEntry, false, "On Dashboard,the assignment entry does not get removed from Course Stream tab.");
            //TC row no. 172
            //"8. Navigate to Course Stream Page from Main navigator"
            //Expected: The assignment entry should get removed  along with social elements from Course Stream Page
            new Navigator().NavigateTo("Course Stream");//navigate to Course Stream
            csEntry = new BooleanValue().presenceOfElement(166, By.cssSelector("li[class='ls-media ls-stream-post ls-stream-post--question']"));
            Assert.assertEquals(csEntry, false, "On CS ,the assignment entry does not get removed from Course Stream tab.");
            //TC row no. 173, 174
            //"11. Select the lesson page where assignment is associated
            //12. Go to Assignment tab"
            // Expected: The assignment entry in the assignment tab should be removed for that assignment.
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            new TopicOpen().openLessonWithDiscussionWidget();
            new Navigator().navigateToTab("Assignments");
            assessmentFound = new BooleanValue().presenceOfElement(166, By.className("ls-assignment-name-block"));
            Assert.assertEquals(assessmentFound, false, "the assignment entry is not deleted In assignments tab of e-Textbook after it is un-assigned.");

            //TC row no. 175
            //13. Navigate to Engagement Report page
            //Items that you have assigned section should remove the contribution of that assignment for "Not-started" column
            new Navigator().NavigateTo("Engagement Report");//Engagement Report
            notStartedCount = engagementReport.getNotStartedCount().getText();
            Assert.assertNotEquals(notStartedCount, "2", "the assignment entry is not deleted In assignments tab of e-Textbook after it is un-assigned. ");

            new LoginUsingLTI().ltiLogin("166_1");		//login as student1
            //TC row no. 176
            // 14. Login as Student & navigate to Dashboard
            //Expected: On Student Dashboard, the assignment entry should get removed from Course Stream tab//
            courseStreamTileEntry = new BooleanValue().presenceOfElement(101, By.id("course-stream"));
            Assert.assertEquals(courseStreamTileEntry, false, "On Dashboard,the assignment entry does not get removed from Course Stream tab.");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments

            //TC row no 177
            // "15. Navigate to Assignments Page from Main navigator
            //The assignment should get Unassigned and the entry should get removed from Assignments page.//
            noAssignmentMessage = assignments.getNoAssignmentMessage().getText();
            if(!noAssignmentMessage.contains("Your instructor has not yet assigned you anything. Please check back at a later point of time.")){
                Assert.fail("The entry for that assignment is not removed from Assignments page of student side when its unassigned by the instructor.");
            }
            //TC row no 178
            //"16. Navigate to Course Stream Page from Main navigator
            //Expected: The card which shows the instructor posted that assignment should be removed with its social elements.//
            new Navigator().NavigateTo("Course Stream");//navigate to Course Stream
            csEntry = new BooleanValue().presenceOfElement(166, By.cssSelector("li[class='ls-media ls-stream-post ls-stream-post--question']"));
            Assert.assertEquals(csEntry, false, "On CS ,the assignment entry does not get removed from Course Stream tab of student side.");

            //TC row no 180, 181
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            new TopicOpen().openLessonWithDiscussionWidget();
            tab = lessonPage.getAssignmentsTab().getText();
            Assert.assertNotEquals(tab, "Assignments", "The assignment entry does not get removed from assignment tab of e-textbook in student side.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class UnAssignDWAssignment in method unAssignGradableDWAssignment.", e);
        }
    }

}
