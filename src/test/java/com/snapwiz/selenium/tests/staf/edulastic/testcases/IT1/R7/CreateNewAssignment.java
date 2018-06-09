package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.R7;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 * Created by Sumit on 9/4/2014.
 */
public class CreateNewAssignment extends Driver {

    @Test(priority = 1, enabled = true)
    public void createNewAssignment()
    {
        try
        {
            String appendChar = "a";
            new SignUp().teacher(appendChar, 2);
            new School().createWithOnlyName(appendChar, 2);
            new Classes().createClass(appendChar, 2);
            for(int i=0; i<5; i++){
                try{
                    driver.findElement(By.xpath("(//a[@class='btn btn-blue btn-rounded'])[2]"));
                    break;

                }catch (Exception e){

                }
            }

            //verify "Create New Assignment" link
            String text = new TextFetch().textFetchByXpath("(//a[@class='btn btn-blue btn-rounded'])[2]");
            if(!text.contains("Create Your First Assignment"))
                Assert.fail("\"After logging in as new user(teacher), \"Create New Assignment\" link is not Present");

            //click on Create New Assignment user should navigate to Create Assessment page
            new Click().clickByXpath("(//a[@class='btn btn-blue btn-rounded'])[2]");    //click on Create new Assignment
            String url = driver.getCurrentUrl();
            if(!url.contains("createAssessment"))
                Assert.fail("On clicking \"Create your first assignment\" the user does not navigate to Create Assignment page.");

            //In Assignment page the "Create" link should be present.
            String text1 = new TextFetch().textfetchbyid("create-assessment-with-val");
            if(!text1.contains("Create"))
                Assert.fail("In Assignment page the \"Create\" link is not present.");

            //1. After logging in as old user(teacher),click on “Assignments”...1.User should be directed to home page of assignment
            new Navigator().navigateTo("assignment");    //go to Assignments
            String url1 = driver.getCurrentUrl();
            if(!url1.contains("assignment"))
                Assert.fail("On clicking \"Assessment\" from main navigator the user does not navigate to Assessment page.");

            //"1.Following should be available: Awaiting Submission, Grading in progress, Review in Progress, Graded, Reviewed, Scheduled"
            String filters = new TextFetch().textfetchbyid("as-assignment-filter-viewall");
            if(!filters.contains("All Status"))
                Assert.fail("\"All Status\" dropdown is not present in Assignment page.");

            if(!filters.contains("Awaiting Submission"))
                Assert.fail("\"Awaiting Submission\" option is not present under \"All Status\"dropdown in Assignment page.");
            if(!filters.contains("Grading in Progress"))
                Assert.fail("\"Grading in Progress\" option is not present under \"All Status\"dropdown in Assignment page.");
            if(!filters.contains("Review in Progress"))
                Assert.fail("\"Review in Progress\" option is not present under \"All Status\"dropdown in Assignment page.");
            if(!filters.contains("Graded"))
                Assert.fail("\"Graded\" option is not present under \"All Status\"dropdown in Assignment page.");
            if(!filters.contains("Reviewed"))
                Assert.fail("\"Reviewed\" option is not present under \"All Status\"dropdown in Assignment page.");
            if(!filters.contains("Scheduled"))
                Assert.fail("\"Scheduled\" option is not present under \"All Status\"dropdown in Assignment page.");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase createNewAssignment in class CreateNewAssignment", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void verifyExistingAssignment()
    {
        try
        {

            String appendChar = "a";
            new SignUp().teacher(appendChar, 39);
            new School().createWithOnlyName(appendChar, 39);
            new Classes().createClass(appendChar, 39);

            new Click().clickByXpath("(//a[@class='btn btn-blue btn-rounded'])[2]");//click on Create New Assignment button
            new Click().clickByid("search-assessment-with-val");//click on search

            String text = new TextFetch().textfetchbyid("select2-sortSelection-container");
            if(!text.contains("Rating"))
                Assert.fail("On clicking \"Search\" the teacher is not navigated to \"Pick an assessment\" page.");

            new Assignment().create(39, "truefalse");//create an Assignmnet
            new Assignment().assignToStudent(39, appendChar);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase verifyUseExistingAssignment in class CreateNewAssignment", e);
        }
    }

    @Test(priority = 3, enabled = true)
    public void assignmentFlow()
    {
        try
        {
            String appendChar = "a";
            String appendCharSecondStudent = "b";
            String appendCharThirdStudent = "c";
            String appendCharFourthStudent = "d";
            new SignUp().teacher(appendChar, 42);//Sign up as teacher
            new School().createWithOnlyName(appendChar, 42);//create class
            String classCode = new Classes().createClass(appendChar, 42);
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendChar, classCode, 42);//create student1
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharSecondStudent, classCode, 42);//create student2
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharThirdStudent, classCode, 42);//create student3
            new Navigator().logout();//log out
            new SignUp().studentDirectRegister(appendCharFourthStudent, classCode, 42);//create student4
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, 42);//log in as teacher
            new Assignment().create(42, "truefalse");//create an Assignment
            new Assignment().addQuestion(42, "truefalse");//add a question
            new Assignment().assignToStudent(42, appendChar);//assign to class
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendChar, 42);//log in as student 1
            new Assignment().submitAssignment(42);
            new Navigator().logout();//log out

            new Login().loginAsStudent(appendCharSecondStudent, 42);//log in as student 2
            new Assignment().submitAssignment(42);
            new Navigator().logout();//log out


        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase assignmentFlowB in class CreateNewAssignment", e);
        }
    }
}
