package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R1817;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Gradebook.Gradebook;
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
 * Created by sumit on 8/1/15.
 */
public class MentorAbleToUnAssignGradableAssignmentWithPolicy extends Driver {

    @Test(priority = 1, enabled = true)
    public void mentorAbleToUnAssignGradableAssignmentWithPolicy()
    {
        try
        {
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", "32");
            String assignmentpolicy = ReadTestData.readDataByTagName("", "assignmentpolicy", "32");

            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);
            Gradebook gradebook = PageFactory.initElements(driver, Gradebook.class);
            LessonPage lessonPage = PageFactory.initElements(driver, LessonPage.class);
            EngagementReport engagementReport = PageFactory.initElements(driver, EngagementReport.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            MyActivity myActivity = PageFactory.initElements(driver, MyActivity.class);

            String str;
            String popUp;
            String no;
            String yes;
            String name;
            boolean popUpFound;
            String noAssignment;
            boolean classActivity;
            boolean csEntry;
            String noGradeBook;
            String notStartedCount;
            String tab;
            String noAssignmentMessage;
            String noActivityMessage;

            new Assignment().create(32);

            new LoginUsingLTI().ltiLogin("32_1");		//login as student1
            new LoginUsingLTI().ltiLogin("32_2");    //login as student2
            new LoginUsingLTI().ltiLogin("32");		//login a mentor
            new Navigator().NavigateTo("Assignment Policies");//navigate to Assignment Policies
            new AssignmentPolicy().createAssignmentPolicy(assignmentpolicy, "63 Policy description", "2", null, false, "1", "", "Release explicitly by the mentor", "", "", "");
            new Assignment().assignToStudent(32);
            //On the assignment summary page of the mentor,the current option of “Delete Assignment” should be changed to “Unassign Assignment”.
            //TC row no. 32
            str = currentAssignments.getUnAssignAssignment_button().getText();
            if(!str.contains("Un-assign Assignment")){
                Assert.fail("Un-assign Assignment link is not present in Current Assignments page after mentor assigns the assignment.");
            }
            //TC row no. 11
            // It should display a pop-up message "You are un-assigning the assignment for ALL students. Are you sure ?" with 'Yes' and 'No' buttons along with close(X) on the top.
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-Assign link
            popUp = currentAssignments.getUnAssignAssignment_popup().getText();
            Assert.assertEquals(popUp, "You are un-assigning the assignment for ALL students. Are you sure?", "Pop-up notification is not shown on clicking Un-Assign assignment.");

            no = currentAssignments.getNoForUnAssign_button().getText();
            Assert.assertEquals(no, "No", "No button is absent in Un-Assign assignment pop-up.");

            yes = currentAssignments.getYesForUnAssign_button().getText();

            Assert.assertEquals(yes, "Yes", "Yes button is absent in Un-Assign assignment pop-up.");
            //TC row no. 12
            //4. Click on Close(X)
            //Expected: The pop-up message should disappear.//
            currentAssignments.getCloseForUnAssign_icon().click();//click on Close icon
            popUpFound = new BooleanValue().presenceOfElement(32, By.className("delete-notification"));
            Assert.assertEquals(popUpFound, false, "The pop-up message doesn't disappear after clicking on Close icon");

            //TC row no. 13
            //5. Click on No
            //It should not Unassign the assignment and pop-up message should disappear.//
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-Assign link
            currentAssignments.getNoForUnAssign_button().click();//click on NO link on Un-assign assignment pop-up
            popUpFound = new BooleanValue().presenceOfElement(32, By.className("delete-notification"));
            Assert.assertEquals(popUpFound, false, "The pop-up message doesn't disappear after clicking on No link on Un-assign assignment pop-up");

            name = currentAssignments.getAssignmentName().getText();
            Assert.assertEquals(name, "(shor) "+assessmentname, "click on NO link on Un-assign assignment pop-up it Unassigns the assignment");

            //TC row no. 14
            //6. Click on Yes
            //Expected: The assignment should get unassigned and the entry should get removed from Assignment Summary page.//
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-Assign link
            currentAssignments.getYesForUnAssign_button().click();//click on Yes link on Un-assign assignment pop-up
            noAssignment = currentAssignments.getNoAssignmentMessage().getText();
            if(!noAssignment.contains("No Assignment exists.")){
                Assert.fail("The assignment doesn't get unassigned and the entry doesn't get removed from Assignment Summary page.");
            }

            //TC row no. 15
            //7. Navigate to Dashboard
            //Expected: On Dashboard,the assignment entry should get removed from Course Stream tab
            new Navigator().NavigateTo("Dashboard");
            classActivity = new BooleanValue().presenceOfElement(32, By.id("course-stream"));
            Assert.assertEquals(classActivity, false, "On Dashboard,the assignment entry does not get removed from Course Stream tab.");
            //TC row no. 17
            //"8. Navigate to Course Stream Page from Main navigator"
            //Expected: The assignment entry should get removed  along with social elements from Course Stream Page
            new Navigator().NavigateTo("Course Stream");//navigate to Course Stream
            csEntry = new BooleanValue().presenceOfElement(32, By.cssSelector("li[class='ls-media ls-stream-post ls-stream-post--question']"));
            Assert.assertEquals(csEntry, false, "On CS ,the assignment entry does not get removed from Course Stream tab.");
            //TC row no. 18
            //9. Navigate to Gradebook page
            //The entry for that assignment should be removed.//
            new Navigator().NavigateTo("Gradebook");//navigate to Gradebook page
            noGradeBook = gradebook.getNoGradeBookMessage().getText();
            if(!noGradeBook.contains("It seems no student has been assigned any gradable assignment.")){
                Assert.fail("The entry for that assignment is not removed from Gradebook page after it is unassigned by the mentor.");
            }
            Thread.sleep(2000);
            //TC row no. 19, 20
            //"11. Select the lesson page where assignment is associated
            //12. Go to Assignment tab"
            // Expected: The assignment entry in the assignment tab should be removed for that assignment.
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Assignments");
            String name1 = lessonPage.getAssignmentName().getText();
            Assert.assertNotEquals(name1, "(shor) "+assessmentname, "the assignment entry is not deleted In assignments tab of e-Textbook after it is un-assigned. ");

            //TC row no. 21
            //13. Navigate to Engagement Report page
            //Items that you have assigned section should remove the contribution of that assignment for "Not-started" column
            new Navigator().NavigateTo("Engagement Report");//Engagement Report
            notStartedCount = engagementReport.getNotStartedCount().getText();
            Assert.assertNotEquals(notStartedCount, "2", "the assignment entry is not deleted In assignments tab of e-Textbook after it is un-assigned. ");

            new LoginUsingLTI().ltiLogin("32_1");		//login as student1
            //TC row no. 23
            // 14. Login as Student & navigate to Dashboard
            //Expected: On Student Dashboard, the assignment entry should get removed from Course Stream tab//
            classActivity = new BooleanValue().presenceOfElement(32, By.id("course-stream"));
            Assert.assertEquals(classActivity, false, "On Dashboard,the assignment entry does not get removed from Course Stream tab.");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments

            //TC row no 24
            // "15. Navigate to Assignments Page from Main navigator
            //The assignment should get Unassigned and the entry should get removed from Assignments page.//
            noAssignmentMessage = assignments.getNoAssignmentMessage().getText();
            if(!noAssignmentMessage.contains("Your instructor has not yet assigned you anything. Please check back at a later point of time.")){
                Assert.fail("The entry for that assignment is not removed from Assignments page of student side when its unassigned by the mentor.");
            }
            //TC row no 26
            //"16. Navigate to Course Stream Page from Main navigator
            //Expected: The card which shows the mentor posted that assignment should be removed with its social elements.//
            new Navigator().NavigateTo("Course Stream");//navigate to Course Stream
            boolean csEntry1 = new BooleanValue().presenceOfElement(32, By.cssSelector("li[class='ls-media ls-stream-post ls-stream-post--question']"));
            Assert.assertEquals(csEntry1, false, "On CS ,the assignment entry does not get removed from Course Stream tab of student side.");

            //TC row no 27
            //"17. Navigate to My Activity Page from Main navigator
            //Expected: The assignment card entry should get removed from My Activity Page.
            new Navigator().NavigateTo("My Activity");//navigate to My Activity
            noActivityMessage = myActivity.getNoActivityMessage().getText();
            if(!noActivityMessage.contains("It seems you have not done any learning activity yet.")){
                Assert.fail("The entry for that assignment is not removed from My Activity page of student side when its unassigned by the mentor.");
            }

            //TC row no 28, 29
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            new TOCShow().tocHide();
            tab = lessonPage.getAssignmentsTab().getText();
            Assert.assertNotEquals(tab, "Assignments", "The assignment entry does not get removed from assignment tab of e-textbook in student side.");


            new LoginUsingLTI().ltiLogin("32");		//login a mentor
            new Assignment().assignToStudent(32);//assign once again

            new LoginUsingLTI().ltiLogin("32_1");		//login as student1
            new Navigator().NavigateTo("Assignments");//navigate to Assignments
            new Assignment().clickonAssignment(assessmentname);//open the assignment to make it In Progress
            Thread.sleep(2000);

            new LoginUsingLTI().ltiLogin("32");		//login a mentor
            new Navigator().NavigateTo("Current Assignments");

            //On the assignment summary page of the mentor,the current option of “Delete Assignment” should be changed to “Unassign Assignment”.
            //TC row no. 32
            str = currentAssignments.getUnAssignAssignment_button().getText();
            if(!str.contains("Un-assign Assignment")){
                Assert.fail("Un-assign Assignment link is not present in Current Assignments page after mentor assigns the assignment.");
            }
            //TC row no. 32
            // It should display a pop-up message "You are un-assigning the assignment for ALL students. Are you sure ?" with 'Yes' and 'No' buttons along with close(X) on the top.
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-Assign link
            popUp = currentAssignments.getUnAssignAssignment_popup().getText();
            Assert.assertEquals(popUp, "You are un-assigning the assignment for ALL students. Are you sure?", "Pop-up notification is not shown on clicking Un-Assign assignment.");

            no = currentAssignments.getNoForUnAssign_button().getText();
            Assert.assertEquals(no, "No", "No button is absent in Un-Assign assignment pop-up.");

            yes = currentAssignments.getYesForUnAssign_button().getText();

            Assert.assertEquals(yes, "Yes", "Yes button is absent in Un-Assign assignment pop-up.");
            //TC row no. 33
            //4. Click on Close(X)
            //Expected: The pop-up message should disappear.
            currentAssignments.getCloseForUnAssign_icon().click();//click on Close icon
            popUpFound = new BooleanValue().presenceOfElement(32, By.className("delete-notification"));
            Assert.assertEquals(popUpFound, false, "The pop-up message doesn't disappear after clicking on Close icon");

            //TC row no. 34
            //5. Click on No
            //It should not Unassign the assignment and pop-up message should disappear.
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-Assign link
            currentAssignments.getNoForUnAssign_button().click();//click on NO link on Un-assign assignment pop-up
            popUpFound = new BooleanValue().presenceOfElement(32, By.className("delete-notification"));
            Assert.assertEquals(popUpFound, false, "The pop-up message doesn't disappear after clicking on No link on Un-assign assignment pop-up");

            name = currentAssignments.getAssignmentName().getText();
            Assert.assertEquals(name, "(shor) "+assessmentname, "click on NO link on Un-assign assignment pop-up it Unassigns the assignment");

            //TC row no. 35
            //6. Click on Yes
            //Expected: The assignment should get unassigned and the entry should get removed from Assignment Summary page.
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-Assign link
            currentAssignments.getYesForUnAssign_button().click();//click on Yes link on Un-assign assignment pop-up
            noAssignment = currentAssignments.getNoAssignmentMessage().getText();
            if(!noAssignment.contains("No Assignment exists.")){
                Assert.fail("The assignment doesn't get unassigned and the entry doesn't get removed from Assignment Summary page.");
            }

            //TC row no. 36
            //7. Navigate to Dashboard
            //Expected: On Dashboard,the assignment entry should get removed from Course Stream tab//
            new Navigator().NavigateTo("Dashboard");
            classActivity = new BooleanValue().presenceOfElement(32, By.id("course-stream"));
            Assert.assertEquals(classActivity, false, "On Dashboard,the assignment entry does not get removed from Course Stream tab.");
            //TC row no. 42
            //"8. Navigate to Course Stream Page from Main navigator"
            //Expected: The assignment entry should get removed  along with social elements from Course Stream Page//
            new Navigator().NavigateTo("Course Stream");//navigate to Course Stream
            csEntry = new BooleanValue().presenceOfElement(32, By.cssSelector("li[class='ls-media ls-stream-post ls-stream-post--question']"));
            Assert.assertEquals(csEntry, false, "On CS ,the assignment entry does not get removed from Course Stream tab.");
            //TC row no. 44
            //9. Navigate to Gradebook page
            //The entry for that assignment should be removed.
            new Navigator().NavigateTo("Gradebook");//navigate to Gradebook page
            noGradeBook = gradebook.getNoGradeBookMessage().getText();
            if(!noGradeBook.contains("It seems no student has been assigned any gradable assignment.")){
                Assert.fail("The entry for that assignment is not removed from Gradebook page after it is unassigned by the mentor.");
            }
            Thread.sleep(2000);
            //TC row no. 45, 46
            //"11. Select the lesson page where assignment is associated
            //12. Go to Assignment tab"
            //Expected: The assignment entry in the assignment tab should be removed for that assignment.
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Assignments");
            name = lessonPage.getAssignmentName().getText();
            Assert.assertNotEquals(name, "(shor) "+assessmentname, "the assignment entry is not deleted In assignments tab of e-Textbook after it is un-assigned. ");

            //TC row no. 47
            //13. Navigate to Engagement Report page
            //Items that you have assigned section should remove the contribution of that assignment for "Not-started" column
            new Navigator().NavigateTo("Engagement Report");//Engagement Report
            notStartedCount = engagementReport.getNotStartedCount().getText();
            Assert.assertNotEquals(notStartedCount, "2", "the assignment entry is not deleted In assignments tab of e-Textbook after it is un-assigned. ");

            new LoginUsingLTI().ltiLogin("32_1");		//login as student1
            //TC row no. 49
            // 14. Login as Student & navigate to Dashboard
            //Expected: On Student Dashboard, the assignment entry should get removed from Course Stream tab
            classActivity = new BooleanValue().presenceOfElement(32, By.id("course-stream"));
            Assert.assertEquals(classActivity, false, "On Dashboard,the assignment entry does not get removed from Course Stream tab.");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments

            //TC row no 56
            // "15. Navigate to Assignments Page from Main navigator
            //The assignment should get Unassigned and the entry should get removed from Assignments page.
            noAssignmentMessage = assignments.getNoAssignmentMessage().getText();
            if(!noAssignmentMessage.contains("Your instructor has not yet assigned you anything. Please check back at a later point of time.")){
                Assert.fail("The entry for that assignment is not removed from Assignments page of student side when its unassigned by the mentor.");
            }
            //TC row no 57
            //"16. Navigate to Course Stream Page from Main navigator
            //Expected: The card which shows the mentor posted that assignment should be removed with its social elements.
            new Navigator().NavigateTo("Course Stream");//navigate to Course Stream
            csEntry = new BooleanValue().presenceOfElement(32, By.cssSelector("li[class='ls-media ls-stream-post ls-stream-post--question']"));
            Assert.assertEquals(csEntry, false, "On CS ,the assignment entry does not get removed from Course Stream tab of student side.");

            //TC row no 59, 60
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            new TOCShow().tocHide();
            tab = lessonPage.getAssignmentsTab().getText();
            Assert.assertNotEquals(tab, "Assignments", "The assignment entry does not get removed from assignment tab of e-textbook in student side.");

            new LoginUsingLTI().ltiLogin("32");		//login a mentor
            new Assignment().assignToStudent(32);//assign once again

            new LoginUsingLTI().ltiLogin("32_1");		//login as student1
            new Assignment().submitAssignmentAsStudent(32);//submit the Assignment

            new LoginUsingLTI().ltiLogin("32");		//login a mentor
            new Navigator().NavigateTo("Current Assignments");

            //On the assignment summary page of the mentor,the current option of “Delete Assignment” should be changed to “Unassign Assignment”.
            //TC row no. 32
            str = currentAssignments.getUnAssignAssignment_button().getText();
            if(!str.contains("Un-assign Assignment")){
                Assert.fail("Un-assign Assignment link is not present in Current Assignments page after mentor assigns the assignment.");
            }
            //TC row no. 32
            // It should display a pop-up message "You are un-assigning the assignment for ALL students. Are you sure ?" with 'Yes' and 'No' buttons along with close(X) on the top.
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-Assign link
            popUp = currentAssignments.getUnAssignAssignment_popup().getText();
            Assert.assertEquals(popUp, "You are un-assigning the assignment for ALL students. Are you sure?", "Pop-up notification is not shown on clicking Un-Assign assignment.");

            no = currentAssignments.getNoForUnAssign_button().getText();
            Assert.assertEquals(no, "No", "No button is absent in Un-Assign assignment pop-up.");

            yes = currentAssignments.getYesForUnAssign_button().getText();

            Assert.assertEquals(yes, "Yes", "Yes button is absent in Un-Assign assignment pop-up.");
            //TC row no. 33
            //4. Click on Close(X)
            //Expected: The pop-up message should disappear.
            currentAssignments.getCloseForUnAssign_icon().click();//click on Close icon
            popUpFound = new BooleanValue().presenceOfElement(32, By.className("delete-notification"));
            Assert.assertEquals(popUpFound, false, "The pop-up message doesn't disappear after clicking on Close icon");

            //TC row no. 34
            //5. Click on No
            //It should not Unassign the assignment and pop-up message should disappear.
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-Assign link
            currentAssignments.getNoForUnAssign_button().click();//click on NO link on Un-assign assignment pop-up
            popUpFound = new BooleanValue().presenceOfElement(32, By.className("delete-notification"));
            Assert.assertEquals(popUpFound, false, "The pop-up message doesn't disappear after clicking on No link on Un-assign assignment pop-up");

            name = currentAssignments.getAssignmentName().getText();
            Assert.assertEquals(name, "(shor) "+assessmentname, "click on NO link on Un-assign assignment pop-up it Unassigns the assignment");

            //TC row no. 35
            //6. Click on Yes
            //Expected: The assignment should get unassigned and the entry should get removed from Assignment Summary page.
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-Assign link
            currentAssignments.getYesForUnAssign_button().click();//click on Yes link on Un-assign assignment pop-up
            noAssignment = currentAssignments.getNoAssignmentMessage().getText();
            if(!noAssignment.contains("No Assignment exists.")){
                Assert.fail("The assignment doesn't get unassigned and the entry doesn't get removed from Assignment Summary page.");
            }

            //TC row no. 36
            //7. Navigate to Dashboard
            //Expected: On Dashboard,the assignment entry should get removed from Course Stream tab//
            new Navigator().NavigateTo("Dashboard");
            classActivity = new BooleanValue().presenceOfElement(32, By.id("course-stream"));
            Assert.assertEquals(classActivity, false, "On Dashboard,the assignment entry does not get removed from Course Stream tab.");
            //TC row no. 42
            //"8. Navigate to Course Stream Page from Main navigator"
            //Expected: The assignment entry should get removed  along with social elements from Course Stream Page//
            new Navigator().NavigateTo("Course Stream");//navigate to Course Stream
            csEntry = new BooleanValue().presenceOfElement(32, By.cssSelector("li[class='ls-media ls-stream-post ls-stream-post--question']"));
            Assert.assertEquals(csEntry, false, "On CS ,the assignment entry does not get removed from Course Stream tab.");
            //TC row no. 44
            //9. Navigate to Gradebook page
            //The entry for that assignment should be removed.
            new Navigator().NavigateTo("Gradebook");//navigate to Gradebook page
            noGradeBook = gradebook.getNoGradeBookMessage().getText();
            if(!noGradeBook.contains("It seems no student has been assigned any gradable assignment.")){
                Assert.fail("The entry for that assignment is not removed from Gradebook page after it is unassigned by the mentor.");
            }
            Thread.sleep(2000);
            //TC row no. 45, 46
            //"11. Select the lesson page where assignment is associated
            //12. Go to Assignment tab"
            //Expected: The assignment entry in the assignment tab should be removed for that assignment.
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Assignments");
            name = lessonPage.getAssignmentName().getText();
            Assert.assertNotEquals(name, "(shor) "+assessmentname, "the assignment entry is not deleted In assignments tab of e-Textbook after it is un-assigned. ");

            //TC row no. 47
            //13. Navigate to Engagement Report page
            //Items that you have assigned section should remove the contribution of that assignment for "Not-started" column
            new Navigator().NavigateTo("Engagement Report");//Engagement Report
            notStartedCount = engagementReport.getNotStartedCount().getText();
            Assert.assertNotEquals(notStartedCount, "2", "the assignment entry is not deleted In assignments tab of e-Textbook after it is un-assigned. ");

            new LoginUsingLTI().ltiLogin("32_1");		//login as student1
            //TC row no. 49
            // 14. Login as Student & navigate to Dashboard
            //Expected: On Student Dashboard, the assignment entry should get removed from Course Stream tab
            classActivity = new BooleanValue().presenceOfElement(32, By.id("course-stream"));
            Assert.assertEquals(classActivity, false, "On Dashboard,the assignment entry does not get removed from Course Stream tab.");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments

            //TC row no 56
            // "15. Navigate to Assignments Page from Main navigator
            //The assignment should get Unassigned and the entry should get removed from Assignments page.
            noAssignmentMessage = assignments.getNoAssignmentMessage().getText();
            if(!noAssignmentMessage.contains("Your instructor has not yet assigned you anything. Please check back at a later point of time.")){
                Assert.fail("The entry for that assignment is not removed from Assignments page of student side when its unassigned by the mentor.");
            }
            //TC row no 57
            //"16. Navigate to Course Stream Page from Main navigator
            //Expected: The card which shows the mentor posted that assignment should be removed with its social elements.
            new Navigator().NavigateTo("Course Stream");//navigate to Course Stream
            csEntry = new BooleanValue().presenceOfElement(32, By.cssSelector("li[class='ls-media ls-stream-post ls-stream-post--question']"));
            Assert.assertEquals(csEntry, false, "On CS ,the assignment entry does not get removed from Course Stream tab of student side.");

            //TC row no 59, 60
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            new TOCShow().tocHide();
            tab = lessonPage.getAssignmentsTab().getText();
            Assert.assertNotEquals(tab, "Assignments", "The assignment entry does not get removed from assignment tab of e-textbook in student side.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in class MentorAbleToUnAssignGradableAssignmentWithPolicy in method mentorAbleToUnAssignGradableAssignmentWithPolicy.", e);

        }
    }

}
