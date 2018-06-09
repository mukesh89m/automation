package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.assignmentflow;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by root on 10/20/15.
 */
public class AssignPage extends Driver{

    @Test(priority = 1,enabled = true)
    public void createAssessmentFromDashboard(){
        try{
            String appendChar = "a";
            int dataIndex = 16;

            MyAssessments myAssessments = PageFactory.initElements(driver,MyAssessments.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            AssessmentDetailsPage assessmentDetailsPage = PageFactory.initElements(driver,AssessmentDetailsPage.class);
            AssignmentReview review = PageFactory.initElements(driver,AssignmentReview.class);
            Assign assign = PageFactory.initElements(driver,Assign.class);

            new SignUp().teacher(appendChar, dataIndex);//Sign up as teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode = new Classes().createClass(appendChar, dataIndex);//create class
            new Assignment().create(dataIndex, "truefalse");//create an Assignment through dashboard
            new Navigator().navigateTo("myAssessments");//Navigate to my assessment
            myAssessments.draft.click();//Click on Draft
            int index = 0;
            List<WebElement> allAssessment = myAssessments.names_assessment;
            for (WebElement assessment : allAssessment)
            {
                if (assessment.getText().equals(assignmentname))
                    break;
                else
                    index++;

            }
            myAssessments.names_assessment.get(index).click();//click on Assessment
            assessmentDetailsPage.editAssesssment.click();//Click on edit button
            review.nextButton.click();//click on next button to assign

            //Assign button should display
            Assert.assertEquals(assign.getButton_assign().getText(),"Assign","Assign button is not displayed");


        }catch (Exception e){
            Assert.fail("Exception in test case 'createAssessmentFromDashboard' of class 'AssignPage'", e);

        }
    }


    @Test(priority = 2,enabled = true)
    public void createAssessmentFromMyAssessment(){
        try{
            String appendChar = "a";
            int dataIndex = 68;

            MyAssessments myAssessments = PageFactory.initElements(driver,MyAssessments.class);
            String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            AssessmentDetailsPage assessmentDetailsPage = PageFactory.initElements(driver,AssessmentDetailsPage.class);
            AssignmentReview review = PageFactory.initElements(driver,AssignmentReview.class);
            Assign assign = PageFactory.initElements(driver,Assign.class);

            new SignUp().teacher(appendChar, dataIndex);//Sign up as teacher
            new School().createWithOnlyName(appendChar, dataIndex);//create school
            String classCode = new Classes().createClass(appendChar, dataIndex);//create class
            new Assignment().create(dataIndex, "truefalse");//create an Assignment through dashboard
            new Navigator().navigateTo("myAssessments");//Navigate to my assessment
            myAssessments.draft.click();//Click on Draft
            int index = 0;
            List<WebElement> allAssessment = myAssessments.names_assessment;
            for (WebElement assessment : allAssessment)
            {
                if (assessment.getText().equals(assignmentname))
                    break;
                else
                    index++;

            }
            myAssessments.names_assessment.get(index).click();//click on Assessment
            assessmentDetailsPage.editAssesssment.click();//Click on edit button
            review.nextButton.click();//click on next button to assign

            //Publish button should display
            Assert.assertEquals(assign.getButton_assign().getText(),"Publish","Publish button is not displayed");


        }catch (Exception e){
            Assert.fail("Exception in test case 'createAssessmentFromMyAssessment' of class 'AssignPage'", e);

        }
    }
}
