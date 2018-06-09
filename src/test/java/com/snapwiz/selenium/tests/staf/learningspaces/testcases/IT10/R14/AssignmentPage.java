package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R14;


import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class AssignmentPage extends Driver
{

    /*
     * Testcases 3, 4, 5
     */
    @Test
    public void assignmentPage()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("1700");
            new Navigator().NavigateTo("Assignments");
            String assignmentTab = driver.findElement(By.cssSelector("span[title='Current Assignments']")).getText();

            if(!assignmentTab.trim().equals("Current Assignments"))
            {
                Assert.fail("On Clicking on 'New Assignment' button its not taking to Resource Tab");
            }
            String noAssignment = driver.findElement(By.className("ls-assignment-post-label")).getText();

            String newAssignment = driver.findElement(By.className("ls-assignment-createnew-label")).getText();

            if(!noAssignment.trim().equals("No Assignment exists.") || !newAssignment.trim().equals("+ New Assignment"))

            {
                Assert.fail("For a new instructor empty page is NOT present with message 'No Assignment exists' and '+ New Assignment' ");
            }
            driver.findElement(By.cssSelector("span.instructor-assignment-new-txt")).click();
            new Click().clickBycssselector("div[class='ls-inst-dashboard-assignment-popup-button ls--question-banks-view']");

            String resourceTab = driver.findElement(By.cssSelector("span[title='Question Banks']")).getText();

            if(!resourceTab.trim().equals("Question Banks"))
            {
                Assert.fail("On Clicking on 'New Assignment' button its not taking to Question Banks Tab");
            }

        }
        catch(Exception e)
        {
            Assert.fail("Exception in assignmentPage in class AssignmentPage",e);
        }
    }

}
