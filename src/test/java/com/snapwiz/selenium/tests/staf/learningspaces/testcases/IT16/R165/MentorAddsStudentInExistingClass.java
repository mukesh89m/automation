package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT16.R165;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.AttemptQuestion;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.CurrentAssignments;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Sumit on 8/28/2014.
 */
public class MentorAddsStudentInExistingClass extends Driver{

    @Test
    public void mentorAddsStudentInExistingClass()
    {
        try
        {
            CurrentAssignments currentAssignments = PageFactory.initElements(driver, CurrentAssignments.class);

            new Assignment().create(32);//create assignment
            new Assignment().create(35);//create assignment

            new LoginUsingLTI().ltiLogin("33");    //create student1

            new LoginUsingLTI().ltiLogin("32");    //login as mentor
            new Assignment().assignToStudent(33);//assign to class section as gradable assignment

            new LoginUsingLTI().ltiLogin("33");    //log in as student 1
            new Navigator().NavigateTo("Assignments");  //navigate to Assignments
            currentAssignments.getAssessmentName().click();//open lesson assignment
            Thread.sleep(10000);
            new AttemptQuestion().trueFalse(false,"incorrect",33);
            driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")).click();
          //  new Assignment().submitAssignmentAsStudent(33);//submit the assignment by student1

            new LoginUsingLTI().ltiLogin("32");    //login as mentor
            new Assignment().releaseGrades(32, "Release Grade for All");  //release the grades

            new LoginUsingLTI().ltiLogin("32");    //login as mentor
            new Assignment().assignToStudent(35);//assign to class section as gradable assignment

            new LoginUsingLTI().ltiLogin("34");    //add new student2 in the class section

            new LoginUsingLTI().ltiLogin("32");    //login as mentor
            new Navigator().NavigateTo("Gradebook");    //go to Gradebook

            //TC row no. 67..."Assignment Cell of previously graded assignment for new student should show "Not Assigned"
            List<WebElement> allScore = driver.findElements(By.cssSelector("div[class='student-assignment-score-cell']"));
            if(!allScore.get(3).getText().equals("Not Assigned"))//which is 3rd index
                Assert.fail("For mentor the Assignment Cell of previously graded assignment for new student does not show \"Not Assigned\".");

            //TC row no. 68...Newly added Assignment cell should be  "Grades Not Released".
            if(!allScore.get(2).getText().equals("Grades Not Released"))//which is 2nd index
                Assert.fail("For mentor, the Newly added Assignment cell is not \"Grades Not Released\".");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in test case mentorAddsStudentInExistingClass in class MentorAddsStudentInExistingClass.", e);
        }
    }

}
