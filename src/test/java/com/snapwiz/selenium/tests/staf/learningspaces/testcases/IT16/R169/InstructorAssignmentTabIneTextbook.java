package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R169;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by Brajesh Kumar on 16-Sep-14.
 * Testcase-id-IT16-R16.9
 * Testcase -desc.:-Verify text changes in Assignment tab
 *
 */
public class InstructorAssignmentTabIneTextbook extends Driver
{
    @Test(priority=1,enabled = true)
    public void instructorAssignmentTabIneTextbook()
    {
        try
        {
            //Row no-12 -step- Verify text changes in Assignment tab
            new Assignment().create(12);//assignment create by author
            new LoginUsingLTI().ltiLogin("12");//Login as instructor
            new Assignment().assignToStudent(12);//Assign assignment to class section
            new Navigator().NavigateTo("e-Textbook");//Navigate to e-Textbook
            new TopicOpen().lessonOpen(0,0);//open lesson 1 of chapter 1
            new Navigator().navigateToTab("Assignments");//navigate to Assignment tab
            String assignmentText=new TextFetch().textfetchbylistclass("ls-right-user-subhead",0);//text fetch of assignment

            //Excepted:-"Gradable" text should be displayed instead of Graded
            Assert.assertEquals(true,assignmentText.contains("Gradable"),"\"Gradable\" text not displayed instead of Graded for the assigned gradable assignment");

            //Row no-13:- Verify Assignment Status
            String assignmentStatus=new TextFetch().textfetchbylistclass("assessmentStatus",0);
            //Excepted:-"Available for Students” status should appear
            Assert.assertEquals(true,assignmentStatus.contains("Available for Students"),"'Available for Students' status not  appear for the status of assignment");

            new Navigator().NavigateTo("Summary");
            //Row no-21. Verify Student Status label at the right side of the posted assignment
            //Excepted:-"Student Status" label should appear at the right side of the posted assignment
            boolean studentStatusLabel=new BooleanValue().booleanbyclass("ls-assignment-student-status-wrapper");
            Assert.assertEquals(true,studentStatusLabel," \"Student Status\" label not appear at the right side of the posted assignment");

            //Row no-22 3. Verify "All Status" filter
            //Excepted :“Assignment Status” text should appear in filter
            new  Click().clickbylinkText("Assignment Status");
            new Click().clickbylinkText("Available for Students");

            //Row no-23 4. Verify "All activity" filter
            //Excepted :"Discussion Assignment" should appear
            new  Click().clickbylinkText("All Activities");//open dropdown
            new Click().clickbylinkText("Discussion Assignment");//select text
            //row-24-Excepted:“Learning Activity” should appear
            new Click().clickbylinkText("Discussion Assignment");//open dropdown
            new Click().clickbylinkText("Learning Activity");//select text
            new Click().clickbylinkText("Learning Activity");//open dropdown
            new Click().clickbylinkText("All Activities");//select text

            //Row-25:-5. step:-Verify "Gradable" label for gradable assignments
            String gradableText=new TextFetch().textfetchbylistclass("ls-assignment-item-detail",0);
            //Excepted:-"Gradable” text should appear
            Assert.assertEquals(true,gradableText.contains("Gradable"), "\"Gradable\" text not appear for the gradable assignment");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase InstructorAssignmentTabIneTextbook in test method instructorAssignmentTabIneTextbook ",e);
        }
    }

}
