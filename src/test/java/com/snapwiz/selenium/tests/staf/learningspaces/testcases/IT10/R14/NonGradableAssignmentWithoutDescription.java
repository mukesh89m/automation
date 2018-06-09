package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT10.R14;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class NonGradableAssignmentWithoutDescription extends Driver
{

    @Test(priority = 1, enabled = true)
    public void nonGradableAssignmentWithoutDescription()
    {
        try
        {
            new Assignment().create(1716);
            new LoginUsingLTI().ltiLogin("1716");
            new Assignment().assignToStudent(1716);
            new LoginUsingLTI().ltiLogin("1716");

            //checking for description is present or not
            int sizeDesc = driver.findElements(By.cssSelector("div[id='idb-additional-note-section']")).size();

            //checking for grading policy is present or not
            int sizeGradPolicy = driver.findElements(By.cssSelector("div[class='ls-assignment-grading-desc']")).size();

            if(sizeDesc != 0 || sizeGradPolicy != 0)
            {
                Assert.fail("Description and grading policy Label are present for an non-gradable assignment posted without any description and grading policy");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in nonGradableAssignmentWithoutDescription in class NonGradableAssignmentWithoutDescription",e);
        }
    }
    @Test(priority = 2, enabled = true)
    public void nonGradableAssignmentWithDescription()
    {
        try
        {
            new Assignment().create(1717);
            new LoginUsingLTI().ltiLogin("1717");
            new Assignment().assignToStudent(1717);
            new LoginUsingLTI().ltiLogin("1717");
            new Navigator().NavigateTo("Assignments");
            //checking for description is present or not
            int sizeDesc = driver.findElements(By.cssSelector("div[id='idb-additional-note-section']")).size();

            if(sizeDesc == 0)
            {
                Assert.fail("Description is not present for an non-gradable assignment posted with a description");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in nonGradableAssignmentWithDescription in class NonGradableAssignmentWithoutDescription",e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void gradableAssignmentWithoutDescriptionAndGradingPolicy()
    {
        try
        {
            new Assignment().create(1718);
            new LoginUsingLTI().ltiLogin("1718");
            new Assignment().assignToStudent(1718);

            new LoginUsingLTI().ltiLogin("1718");
            new Navigator().NavigateTo("Assignments");
			/*
			 * Checking for description and grading policy for a gradable assignment without description and grading policy
			 */
            int sizeDesc = driver.findElements(By.cssSelector("div[id='idb-additional-note-section']")).size();

            int sizeGradPolicy = driver.findElements(By.cssSelector("div[class='ls-assignment-grading-desc']")).size();


            if(sizeDesc != 0 || sizeGradPolicy != 0)
            {
                Assert.fail("Description and grading policy Label are present for an gradable assignment posted without any description and grading policy");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in gradableAssignmentWithoutDescriptionAndGradingPolicy in class NonGradableAssignmentWithoutDescription",e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void gradableAssignmentWithDescriptionOnly()
    {
        try
        {
            new Assignment().create(1719);
            new LoginUsingLTI().ltiLogin("1719");
            new Assignment().assignToStudent(1719);
            new LoginUsingLTI().ltiLogin("1719");

            String additionalnote  = ReadTestData.readDataByTagName("", "additionalnote","1719");
            new Navigator().NavigateTo("Assignments");

            int sizeDesc = driver.findElements(By.cssSelector("div[id='idb-additional-note-section']")).size();

            int sizeGradPolicy = driver.findElements(By.cssSelector("div[class='ls-assignment-grading-desc']")).size();

            String desc = driver.findElement(By.cssSelector("div[id='idb-additional-note-section']")).getText();

            if(sizeDesc != 1 || sizeGradPolicy != 0 || !desc.trim().equals("Description: "+additionalnote))
            {
                Assert.fail("Description is not present for an gradable assignment which has been posted with a description");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in gradableAssignmentWithDescriptionOnly in class NonGradableAssignmentWithoutDescription",e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void gradableAssignmentWithGradingPolicyOnly()
    {
        try
        {
            new Assignment().create(1720);
            new LoginUsingLTI().ltiLogin("1720");
            new Assignment().assignToStudent(1720);
            new LoginUsingLTI().ltiLogin("1720");

            String gradingpolicy  = ReadTestData.readDataByTagName("", "gradingpolicy","1720");
            new Navigator().NavigateTo("Assignments");

            int sizeDesc = driver.findElements(By.cssSelector("div[id='idb-additional-note-section']")).size();

            int sizeGradPolicy = driver.findElements(By.cssSelector("div[class='ls-assignment-grading-desc']")).size();

            String desc = driver.findElement(By.cssSelector("div[class='ls-assignment-grading-desc']")).getText();

            if(sizeDesc != 0 || sizeGradPolicy != 1 || !desc.trim().equals("Assignment Reference: "+gradingpolicy))
            {
                Assert.fail("Grading policy is not present for an gradable assignment which has been posted with grading policy");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in gradableAssignmentWithGradingPolicyOnly in class NonGradableAssignmentWithoutDescription",e);
        }
    }

    @Test(priority = 6, enabled = true)
    public void gradableAssignmentWithBothDescriptionAndGradingPolicy()
    {
        try
        {
            new Assignment().create(1721);
            new LoginUsingLTI().ltiLogin("1721");
            new Assignment().assignToStudent(1721);

            String additionalnote  = ReadTestData.readDataByTagName("", "additionalnote","1721");
            String gradingpolicy  = ReadTestData.readDataByTagName("", "gradingpolicy","1721");
            new Navigator().NavigateTo("Assignments");

            int sizeDesc = driver.findElements(By.cssSelector("div[id='idb-additional-note-section']")).size();

            int sizeGradPolicy = driver.findElements(By.cssSelector("div[class='ls-assignment-grading-desc']")).size();

            String desc = driver.findElement(By.cssSelector("div[id='idb-additional-note-section']")).getText();

            String gradPolicy = driver.findElement(By.cssSelector("div[class='ls-assignment-grading-desc']")).getText();

            if(sizeDesc != 1 || sizeGradPolicy != 1 || !desc.trim().equals("Description: "+additionalnote) || !gradPolicy.trim().equals("Assignment Reference: "+gradingpolicy) )
            {
                Assert.fail("Description and grading policy Label are not present for an gradable assignment posted with any description and gradable policy");
            }

            //Checking for 'Responses'
            String details = driver.findElement(By.cssSelector("span[class='ls-assignment-assign-more']")).getText();
            System.out.println("Details:"+details);
            boolean bool1 = details.equals("View Student Responses | Update Assignment | Un-assign Assignment | Try it");


            //Checking for 'Action'
            String actions = driver.findElements(By.className("ls-assignment-item-detail")).get(1).getText();
            boolean bool2 = actions.trim().contains("Actions:");


            //Checking for Like, Comment and Age of post
            String likeComment = driver.findElement(By.cssSelector("ul[class='ls-stream-post__footer']")).getText();
            boolean bool3 = likeComment.contains("Like") && likeComment.contains("Comments");


            //Checking for status present at RHS
            String status = driver.findElement(By.cssSelector("div[class='ls-assignment-status']")).getText();
            boolean bool4 = status.trim().equals("Class Status:  Available for Students");


            //Checking for due date present at RHS
            String dueDate = driver.findElement(By.cssSelector("div[class='ls-assignment-date-block']")).getText();
            boolean bool5 = dueDate.trim().contains("Due Date:");


            //Checking for accessible after date present at RHS
            String accessibleAfter = driver.findElement(By.cssSelector("div[class='ls-assignment-accessible-after-title']")).getText();
            boolean bool6 = accessibleAfter.trim().contains("Accessible After:");

            if(bool1==false || bool2==false || bool3==false || bool4 == false || bool5==false || bool6 == false)
            {
                Assert.fail("Actions and Like and Comment Age of post options alongwith status is not present");
            }

            String[] stausOnRight = {"Not Started", "In Progress", "Submitted", "Graded"};


            List<String> stringarray = new ArrayList<String>();
            List<WebElement> statusOnRHS = driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards-status ']"));

            //Comparing the status boxes present at RHS
            for(WebElement statusright: statusOnRHS)
            {
                stringarray.add(statusright.getText());
            }

            String [] statusresults = stringarray.toArray(new String[stringarray.size()]);

            if (stausOnRight.length == statusresults.length)
            {
                {for (int i = 0; i < stausOnRight.length; i++)
                {
                    if (!stausOnRight[i].equals(statusresults[i]))
                    {
                        Assert.fail("Boxes with status is not available at right");
                    }

                }

                }
            }
            else
            {
                Assert.fail("Boxes with status is not available at right");
            }

            int statusCount = driver.findElements(By.cssSelector("div[class='ls-instructor-activity-cards-count ']")).size();

            if(statusCount != 4)
            {
                Assert.fail("Boxes with status count is not available at right");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in gradableAssignmentWithBothDescriptionAndGradingPolicy in class NonGradableAssignmentWithoutDescription",e);
        }
    }


}
