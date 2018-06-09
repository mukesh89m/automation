package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R169;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TopicOpen;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by @Brajesh Kumar on 17-Sep-14.
 * Testcase id=IT16 R16.9
 */
public class AssignmentPolicyUIChanges extends Driver
{
    @Test
    public void assignmentPolicyUIChanges()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("36");
            new Navigator().NavigateTo("Policies");

            //Row no-36 1. Verify "+ New Assignment policy" button
            //Excepted:-"+New Assignment Policy" button should be present
            new Click().clickByid("newAssignmentPolicy-link");

            //Row n0-38-3. Verify "Extend due date for Individual students “ and “Extend time limit for the individual students” options
            String policyAllText=new TextFetch().textfetchbyclass("ls-assignment-policy-options-wrapper");
            //Row n0-38:- Excepted “Extend due date for Individual students “ and “Extend time limit for the individual students” should be removed from the screen.
            Assert.assertEquals(false,policyAllText.contains("Extend due date for Individual students")," 'Extend due date for Individual students' not remove");
            Assert.assertEquals(false,policyAllText.contains("Extend time limit for the individual students")," 'Extend time limit for the individual students' not remove");

            //Row no-37:-2. Verify "Save Policy" Button at the bottom of the page
            int savePolicyButton=driver.findElements(By.className("ls-save-policy-btn")).size();
            Assert.assertEquals(true,savePolicyButton==2,"two save policy button not shown");

            new Navigator().NavigateTo("Question Banks");
            new Click().clickbylistcssselector("span[title='Assign This']",0);//click on assign this icon of assignment
            new Click().clickByclassname("ir-ls-assign-dialog-gradable-label-check");//check gradable check box
            new Click().clickbylinkText("Select an assignment policy");//open drop down for new assignment policy
            new Click().clickbylinkText("Create a new assignment policy");//click on link Create a new assignment policy
            //Row -40:- First entry in the drop down should always be “Create a new assignment policy”
            new Click().clickByid("dialog-close");//close policy fly

            //Row no-41:1. Click on main navigator icon
            driver.findElement(By.cssSelector("a[class='navigator ls-toc-sprite']")).click();
            Thread.sleep(3000);
            //Excepted:-all navigator tect shown
            String navigatorText=new TextFetch().textfetchbyclass("ls-site-nav-drop-down");
            Assert.assertEquals(true,navigatorText.contains("Dashboard"),"Dashboard not shown in navigator to navigate ");
            Assert.assertEquals(true,navigatorText.contains("e-Textbook")," e-Textbook not shown in navigator to navigate ");
            Assert.assertEquals(true,navigatorText.contains("Assignments")," Assignments not shown in navigator to navigate ");
            Assert.assertEquals(true,navigatorText.contains("Summary")," Summary not shown in navigator to navigate ");
            Assert.assertEquals(true,navigatorText.contains("Question Banks"),"Question Banks not shown in navigator to navigate ");
            Assert.assertEquals(true,navigatorText.contains("Policies")," Policies not shown in navigator to navigate ");
            Assert.assertEquals(true,navigatorText.contains("Gradebook")," Gradebook not shown in navigator to navigate ");
            Assert.assertEquals(true,navigatorText.contains("Resources")," Resources not shown in navigator to navigate ");
            Assert.assertEquals(true,navigatorText.contains("Course Stream")," Course Stream not shown in navigator to navigate ");
            Assert.assertEquals(true,navigatorText.contains("My Reports")," My Reports not shown in navigator to navigate ");

            driver.findElement(By.cssSelector("a[class='navigator ls-toc-sprite selected']")).click();

           /* //Row no-16
           "1. Login as CMS user
            2. Create an assignment for any chapter
            3. Login as instructor
            */

            new Navigator().NavigateTo("e-Textbook");//Navigate to e-Textbook
            //4. Go to eTextbook and go to same chapter for which assignment is created. Go to assignments tab"
            new TopicOpen().lessonOpen(0,0);//open lesson 1 of chapter 1
            new Navigator().navigateToTab("Assignments");//navigate to Assignment tab
            String publisherName=new TextFetch().textfetchbylistclass("ls-unassigned-userdetails-wrapper",0);
            //Excepted -16:-
            /*"Following change should appear in first row of unassigned assignment
            1)Wiley Icon
            2) Name of the Publisher ie. “John Wiley & Sons”
            3) ""Wiley"" Label"*/
            Assert.assertEquals(true,publisherName.contains("John Wiley"),"\"John Wiley\" name not shown for unassigned assinmanet");
            String assignmentStatus=new TextFetch().textfetchbylistclass("ls-right-assignment-status",0);
            Assert.assertEquals(true,assignmentStatus.contains("Not Yet Assigned"),"for not assigned assignment ,assignment status is not 'Not Yet Assigned' ");
            boolean wileyIcon=new BooleanValue().booleanbyclass("ls-right-publisher-img");
            Assert.assertEquals(true,wileyIcon,"wiley icon not shown for not assigned assignment");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase class AssignmentPolicyUIChanges in method assignmentPolicyUIChanges",e);
        }
    }

}
