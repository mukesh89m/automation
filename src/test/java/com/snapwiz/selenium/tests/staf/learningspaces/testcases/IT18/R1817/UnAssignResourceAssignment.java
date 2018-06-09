package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R1817;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.reports.EngagementReport;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*
 * Created by sumit on 7/1/15.
 */
public class UnAssignResourceAssignment extends Driver{

    @Test(priority = 1, enabled = true)
    public void unAssignResourceAssignment() {
        try {
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
            boolean classActivity;
            boolean csEntry;
            String notStartedCount;
            String tab;
            String noAssignmentMessage;
            boolean assessmentFound;

            new LoginUsingLTI().ltiLogin("206_1");        //login as student1
            new LoginUsingLTI().ltiLogin("206_2");    //login as student2


            new LoginUsingLTI().ltiLogin("206");        //login a instructor
            new UploadResources().uploadResources("206", true, false, true);//upload resource
            new AssignLesson().assignResourceFromMyResources(206);//assign with class

            new Navigator().NavigateTo("Current Assignments");
            //On the assignment summary page of the instructor,the current option of “Delete Assignment” should be changed to “Unassign Assignment”.
            //TC row no. 206
            str = currentAssignments.getUnAssignAssignment_button().getText();
            if(!str.contains("Un-assign Assignment")){
                Assert.fail("Un-assign Assignment link is not present in Current Assignments page after instructor assigns the assignment.");
            }
            //TC row no. 207
            // It should display a pop-up message "You are un-assigning the assignment for ALL students. Are you sure ?" with 'Yes' and 'No' buttons along with close(X) on the top.
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-Assign link
            popUp = currentAssignments.getUnAssignAssignment_popup().getText();
            Assert.assertEquals(popUp, "You are un-assigning the assignment for ALL students. Are you sure?", "Pop-up notification is not shown on clicking Un-Assign assignment.");

            no = currentAssignments.getNoForUnAssign_button().getText();
            Assert.assertEquals(no, "No", "No button is absent in Un-Assign assignment pop-up.");

            yes = currentAssignments.getYesForUnAssign_button().getText();

            Assert.assertEquals(yes, "Yes", "Yes button is absent in Un-Assign assignment pop-up.");
            //TC row no. 208
            //4. Click on Close(X)
            //Expected: The pop-up message should disappear.//
            currentAssignments.getCloseForUnAssign_icon().click();//click on Close icon
            popUpFound = new BooleanValue().presenceOfElement(206, By.className("delete-notification"));
            Assert.assertEquals(popUpFound, false, "The pop-up message doesn't disappear after clicking on Close icon");

            //TC row no. 209
            //5. Click on No
            //It should not Unassign the assignment and pop-up message should disappear.
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-Assign link
            currentAssignments.getNoForUnAssign_button().click();//click on NO link on Un-assign assignment pop-up
            popUpFound = new BooleanValue().presenceOfElement(206, By.className("delete-notification"));
            Assert.assertEquals(popUpFound, false, "The pop-up message doesn't disappear after clicking on No link on Un-assign assignment pop-up");

            assessmentFound = new BooleanValue().presenceOfElement(206, By.className("ls-assignment-name-block"));
            Assert.assertEquals(assessmentFound, true, "clicking on NO link on Un-assign assignment pop-up it Unassigns the assignment");

            //TC row no. 210
            //6. Click on Yes
            //Expected: The assignment should get unassigned and the entry should get removed from Assignment Summary page.
            currentAssignments.getUnAssignAssignment_button().click();//click on Un-Assign link
            currentAssignments.getYesForUnAssign_button().click();//click on Yes link on Un-assign assignment pop-up
            noAssignment = currentAssignments.getNoAssignmentMessage().getText();
            if(!noAssignment.contains("No Assignment exists.")){
                Assert.fail("The assignment doesn't get unassigned and the entry doesn't get removed from Assignment Summary page.");
            }

            //TC row no. 211
            //7. Navigate to Dashboard
            //Expected: On Dashboard,the assignment entry should get removed from Course Stream tab
            new Navigator().NavigateTo("Dashboard");
            classActivity = new BooleanValue().presenceOfElement(206, By.id("course-stream"));
            Assert.assertEquals(classActivity, false, "On Dashboard,the assignment entry does not get removed from Course Stream tab.");
            //TC row no. 213
            //"8. Navigate to Course Stream Page from Main navigator"
            //Expected: The assignment entry should get removed  along with social elements from Course Stream Page
            new Navigator().NavigateTo("Course Stream");//navigate to Course Stream
            csEntry = new BooleanValue().presenceOfElement(206, By.cssSelector("li[class='ls-media ls-stream-post ls-stream-post--question']"));
            Assert.assertEquals(csEntry, false, "On CS ,the assignment entry does not get removed from Course Stream tab.");
            //TC row no. 214, 215
            //"11. Select the lesson page where assignment is associated
            //12. Go to Assignment tab"
            // Expected: The assignment entry in the assignment tab should be removed for that assignment.
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            new TOCShow().tocHide();
            new Navigator().navigateToTab("Assignments");
            assessmentFound = new BooleanValue().presenceOfElement(206, By.className("ls-assignment-name-block"));
            Assert.assertEquals(assessmentFound, false, "the assignment entry is not deleted In assignments tab of e-Textbook after it is un-assigned.");

            //TC row no. 216
            //13. Navigate to Engagement Report page
            //Items that you have assigned section should remove the contribution of that assignment for "Not-started" column
            new Navigator().NavigateTo("Engagement Report");//Engagement Report
            notStartedCount = engagementReport.getNotStartedCount().getText();
            Assert.assertNotEquals(notStartedCount, "2", "the assignment entry is not deleted In assignments tab of e-Textbook after it is un-assigned. ");

            new LoginUsingLTI().ltiLogin("206_1");		//login as student1
            //TC row no. 217
            // 14. Login as Student & navigate to Dashboard
            //Expected: On Student Dashboard, the assignment entry should get removed from Course Stream tab
            classActivity = new BooleanValue().presenceOfElement(206, By.id("course-stream"));
            Assert.assertEquals(classActivity, false, "On Dashboard,the assignment entry does not get removed from Course Stream tab.");

            new Navigator().NavigateTo("Assignments");//navigate to Assignments

            //TC row no 218
            // "15. Navigate to Assignments Page from Main navigator
            //The assignment should get Unassigned and the entry should get removed from Assignments page.
            noAssignmentMessage = assignments.getNoAssignmentMessage().getText();
            if(!noAssignmentMessage.contains("Your instructor has not yet assigned you anything. Please check back at a later point of time.")){
                Assert.fail("The entry for that assignment is not removed from Assignments page of student side when its unassigned by the instructor.");
            }
            //TC row no 219
            //"16. Navigate to Course Stream Page from Main navigator
            //Expected: The card which shows the instructor posted that assignment should be removed with its social elements.
            new Navigator().NavigateTo("Course Stream");//navigate to Course Stream
            csEntry = new BooleanValue().presenceOfElement(206, By.cssSelector("li[class='ls-media ls-stream-post ls-stream-post--question']"));
            Assert.assertEquals(csEntry, false, "On CS ,the assignment entry does not get removed from Course Stream tab of student side.");

            //TC row no 220, 221
            new Navigator().NavigateTo("e-Textbook");//navigate to e-Textbook
            new TOCShow().tocHide();
            tab = lessonPage.getAssignmentsTab().getText();
            Assert.assertNotEquals(tab, "Assignments", "The assignment entry does not get removed from assignment tab of e-textbook in student side.");

            new LoginUsingLTI().ltiLogin("206");        //login a instructor
            new AssignLesson().assignResourceFromMyResources(206);//assign with class

            new LoginUsingLTI().ltiLogin("206_1");		//login as student1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getLessonAssignment().click();//open lesson assignment to submit
            Thread.sleep(2000);

            //TC row no 222
            new LoginUsingLTI().ltiLogin("206");        //login a instructor
            new Navigator().NavigateTo("Current Assignments");
            boolean unAssign = new BooleanValue().presenceOfElement(201, By.className("delete-assigned-task"));
            Assert.assertEquals(unAssign, true, "\"Unassign Assignment\" option is present for Resource Assignment where one student has submitted the assignment.");

            new LoginUsingLTI().ltiLogin("206_2");		//login as student2
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getLessonAssignment().click();//open lesson assignment to submit
            Thread.sleep(2000);

            //TC row no 223
            new LoginUsingLTI().ltiLogin("206");        //login a instructor
            new Navigator().NavigateTo("Current Assignments");
            boolean unAssign1 = new BooleanValue().presenceOfElement(201, By.className("delete-assigned-task"));
            Assert.assertEquals(unAssign1, false, "\"Unassign Assignment\" option is present for Resource Assignment where all the student has submitted the assignment.");

        } catch (Exception e) {
            Assert.fail("Exception in class UnAssignResourceAssignment in method unAssignResourceAssignment.", e);
        }
    }


}
