package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.assessmentsharing;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentLibrary;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/*
 * Created by sumit on 22/1/15.
 */
public class AssessmentSharingWithPublic extends Driver{

    @Test(priority = 1, enabled = true)
    public void assessmentSharingWithPublic()
    {
        try
        {
            String appendChar = "a";
            String appendChar1 = "b";
            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentLibrary assessmentLibrary = PageFactory.initElements(driver,AssessmentLibrary.class);
            int index = 30;

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(index));

            new SignUp().teacher(appendChar, index);//Sign up as teacher
            String zipcode= new School().createWithOnlyName(appendChar, index);//create school
            String classCode = new Classes().createClass(appendChar, index,"Grade 4","Mathematics","Math - Common Core");//create class
            new Navigator().logout();//log out

            new SignUp().studentDirectRegister(appendChar, classCode, index);//create student1
            new Navigator().logout();//log out

            new SignUp().teacher(appendChar1, index);//Sign up as teacher2
            new School().enterAndSelectSchool(zipcode, index, false);
            schoolPage.getContinueButton().click(); //clicking on save button
            new Classes().createClass(appendChar, index,"Grade 4","Mathematics","Math - Common Core");//create class
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar, index);//log in as teacher1
            new Assignment().create(index, "truefalse");//create an Assignment
            new Assignment().addQuestion(index, "truefalse");//add a question
            new Assignment().assignToStudent(index, appendChar);//assign to student1; //status awaiting submission
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1, index);//login as teacher2
            new Navigator().navigateTo("assignment");//navigate To Assignments
            assignments.getList_button_newAssessment().get(0).click();//Click on create new assignment
            assignments.getSearch().click();//Click on search
            new Assignment().selectOwner(Integer.toString(index), "Community");//select District
            boolean found = false;
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
            Thread.sleep(2000);
            ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");

            List<WebElement> allNames = assessmentLibrary.getList_assessment();
            for(WebElement name: allNames){
                if(name.getText().contains(assessmentname)){
                    found = true;
                    break;
                }
            }

            Assert.assertEquals(found, true, "Assessments name is not found when shared with Public.");

            new Assignment().useExistingAssignment(index);


        }
        catch (Exception e){
            Assert.fail("Exception in test case assessmentSharingWithPublic of class AssessmentSharingWithPublic.", e);
        }
    }

}
