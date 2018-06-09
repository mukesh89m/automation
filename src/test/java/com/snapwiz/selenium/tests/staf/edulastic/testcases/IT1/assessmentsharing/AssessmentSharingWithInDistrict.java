package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.assessmentsharing;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assessments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/*
 * Created by sumit on 22/1/15.
 */
public class AssessmentSharingWithInDistrict extends Driver{

    @Test(priority = 1, enabled = true)
    public void assessmentSharingWithInDistrict()
    {
        try
        {
            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            int index = 20;

            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(index));

            new Login().directLoginAsInstructor(index, "da1autoteacher@snapwiz.com");
            new Assignment().create(index, "truefalse");//create an Assignment
            new Assignment().addQuestion(index, "truefalse");//add a question
            new Assignment().assignToStudent(index);//assign to student
            new Navigator().logout();//log out

            new Login().directLoginAsInstructor(index, "da1autoteacher2@snapwiz.com");//login as teacher2
            new Navigator().navigateTo("assignment");//navigate To Assignments
            assessments.getButton_newAssessment().click();//click on +new Assignment button
            assignments.getSearch().click();//click on search

            driver.findElement(By.xpath("//span[@id='select2-gradeSelection-container']")).click();
            Thread.sleep(2000);
            driver.findElement(By.xpath("/*//*[contains(@id,'Grade 9')]")).click();//Select grade9
            new Assignment().selectOwner(Integer.toString(index), "District");//select District
            boolean found = false;
            List<WebElement> allNames = assignments.getAllAssessmentsNames();
            for(WebElement name: allNames){
                if(name.getText().contains(assessmentname)){
                    found = true;
                    break;
                }
            }
            Assert.assertEquals(found, true, "Assessments name is not found when shared with District.");

            new Assignment().useExistingAssignment(index);


        }
        catch (Exception e){
            Assert.fail("Exception in test case assessmentSharingWithInDistrict of class AssessmentSharingWithInDistrict.", e);
        }
    }

}
