package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.assessmentsharing;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentLibrary;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 * Created by sumit on 22/1/15.
 */
public class AssessmentSharingWithInSchool extends Driver{

    @Test(priority = 1, enabled = true)
    public void assessmentSharingWithInSchool()
    {
        try
        {
            String appendChar = "a";
            String appendChar1 = "b";
            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentLibrary assessmentLibrary = PageFactory.initElements(driver,AssessmentLibrary.class);
            int index = 10;

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(index));

            new SignUp().teacher(appendChar, index);//Sign up as teacher
            String zipcode=new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index);//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out

            new SignUp().teacher(appendChar1, index);//Sign up as teacher2
            new School().enterAndSelectSchool(zipcode, index, false);
            schoolPage.getContinueButton().click(); //clicking on save button
            new Classes().createClass(appendChar, index);//create class
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher1
            new Assignment().create(index, "truefalse");//create an Assignment
            new Assignment().addQuestion(index, "truefalse");//add a question
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1, index);//login as teacher2
            new Navigator().navigateTo("assignment");//navigate To Assignments
            assignments.getButton_newAssessment().click();//Click on Create New Assignment
            assignments.getSearch().click();//Click on search
            new Assignment().selectOwner(Integer.toString(index), "School");//select school
            String name = assessmentLibrary.getList_assessment().get(0).getText();
            Assert.assertEquals(name, assessmentname, "Assessments name is not found when shared with school.");

            new Assignment().useExistingAssignment(index);

        }
        catch (Exception e){
            Assert.fail("Exception in test case assessmentSharingWithInSchool of class AssessmentSharingWithInSchool.", e);
        }
    }

}
