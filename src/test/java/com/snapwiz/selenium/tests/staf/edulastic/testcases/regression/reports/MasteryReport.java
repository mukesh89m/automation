package com.snapwiz.selenium.tests.staf.edulastic.testcases.regression.reports;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentDetailsPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssignmentReview;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assign;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.MyAssessments;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by shashank on 25-01-2016.
 */
public class MasteryReport extends Driver {

    @Test(priority = 1,enabled = true)
    public void masteryCriteria()
    {
        int dataIndex = 1;
        String appendChar = "a19";
        String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        MyAssessments myAssessments= PageFactory.initElements(driver,MyAssessments.class);
        AssessmentDetailsPage assessmentDetailsPage=PageFactory.initElements(driver,AssessmentDetailsPage.class);
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        Assign assign=PageFactory.initElements(driver,Assign.class);
        try
        {
            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Assignment().create(dataIndex,"truefalse");
            new Navigator().navigateTo("myAssessments");//Navigate to my assessment page
            myAssessments.draft.click();
            if(myAssessments.names_assessment.size()==0)
            {
                driver.navigate().refresh();
                myAssessments.draft.click();//Click on Draft

            }
            int index=0;
            List<WebElement> allAssessment = myAssessments.names_assessment;//WILL BE chenge on E9
            for (WebElement assessment : allAssessment)
            {
                if (assessment.getText().equals(assignmentname))
                    break;
                else
                    index++;

            }
            myAssessments.names_assessment.get(index).click();
            assessmentDetailsPage.editAssessment.click();
            assessmentReview.nextButton.click();
            assign.editMasteryCriteria.click();
            Thread.sleep(3000);
            //verfiy text present on screen
            List<WebElement> editMastercriteriaHeader=assign.editMasterCriteriaHeader;
            Assert.assertEquals(editMastercriteriaHeader.get(0).getText(),"Standard","Standard not present on edit mastery criteria");
            Assert.assertEquals(editMastercriteriaHeader.get(1).getText(),"Questions in this Assessments","Questions in this Assessments not present on edit mastery criteria");
            Assert.assertEquals(editMastercriteriaHeader.get(2).getText(),"Total Points","Total Points not present on edit mastery criteria");
            Assert.assertEquals(editMastercriteriaHeader.get(3).getText(),"Minimum Questions for Mastery","Minimum Questions for Mastery not present on edit mastery criteria");
            Assert.assertEquals(editMastercriteriaHeader.get(4).getText(),"Mastery Threshold","Mastery Threshold not present on edit mastery criteria");
            Assert.assertEquals(editMastercriteriaHeader.get(5).getText(),"Almost Mastered Threshold","Almost Mastered Threshold not present on edit mastery criteria");

            List<WebElement> editMastercriteriaData=assign.editMasterCriteriaBody;
            Assert.assertEquals(editMastercriteriaData.get(0).getText(),"1.OA.A.1","Elo not present on edit mastery criteria");
            Assert.assertEquals(editMastercriteriaData.get(1).getText(),"1","number of Questions in this Assessments not present on edit mastery criteria");
            Assert.assertEquals(editMastercriteriaData.get(2).getText(),"1","Total Points not present on edit mastery criteria");
            Assert.assertEquals(editMastercriteriaData.get(3).getText(),"5","number of Minimum Questions for Mastery not present on edit mastery criteria");
            Assert.assertEquals(editMastercriteriaData.get(4).getText(),"80%","Mastery Threshold not present on edit mastery criteria");
            Assert.assertEquals(editMastercriteriaData.get(5).getText(),"60%","Almost Mastered Threshold not present on edit mastery criteria");


            //drcrease number of Question in the assessments
            assign.decreaseCount.get(0).click();
            //decrease Mastery Threashold
            assign.decreaseCount.get(1).click();
            //decrease Almost Mastered Threshold
            assign.decreaseCount.get(2).click();
            assign.buttonApplyCriteria.click();

            Thread.sleep(2000);
            if(assign.buttonApplyCriteria.isDisplayed())
            {
                Assert.fail("Button Apply criteria is still present");
            }
            assign.editMasteryCriteria.click();
            Thread.sleep(3000);
            editMastercriteriaData=assign.editMasterCriteriaBody;
            Assert.assertEquals(editMastercriteriaData.get(3).getText(),"4","number of Minimum Questions for Mastery not present on edit mastery criteria");
            Assert.assertEquals(editMastercriteriaData.get(4).getText(),"79%","Mastery Threshold not present on edit mastery criteria");
            Assert.assertEquals(editMastercriteriaData.get(5).getText(),"59%","Almost Mastered Threshold not present on edit mastery criteria");

            //click on 'X'
            assign.closeEditMasterCriteria.click();
            Thread.sleep(2000);
            if(assign.buttonApplyCriteria.isDisplayed())
            {
                Assert.fail("Button Apply criteria is still present");
            }




        }
        catch (Exception e)
        {
            Assert.fail("Exception in masterCriteria method in class MasteryReport",e);
        }

    }
}
