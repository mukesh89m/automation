package com.snapwiz.selenium.tests.staf.edulastic.testcases.e9.editassessesment;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentDetailsPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentLibrary;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssignmentReview;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assign;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextSend;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by shashank on 15-10-2015.
 */
public class EditAssessmentByDA extends Driver{



    @Test(priority = 1,enabled = true)
    public void editAssessmentSharingDistrict()

    {
        String email="daadmin@snapwiz.com";
        String teacherEmail="da1autoteacher@snapwiz.com ";
        int dataIndex = 89;
        String appendChar = "a15";
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver, AssessmentDetailsPage.class);
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        Assign assign=PageFactory.initElements(driver,Assign.class);
        try {

            new Login().directLoginAsDATeacher(dataIndex, email);
            new Navigator().selectGradeAndSubject(dataIndex);
            new Navigator().navigateTo("districtAssessments");//Navigate to assessment
            new Click().clickBycssselector("a[class='btn btn-blue btn-rounded']");
            driver.findElement(By.id("new-assessment-name")).clear();
            driver.findElement(By.id("new-assessment-name")).sendKeys(assessmentname);//give the assignment title
            new Click().clickByid("create-assessment-with-val");
            new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']");//click on the create besides elos
            new Click().clickByid("qtn-true-false-type");//click on the true false question
            new TextSend().textsendbyid("sample true false question", "question-raw-content");
            new Click().clickByclassname("true-false-answer-select");
            new TextSend().textsendbyclass("assignmnet", "lsm-createAssignment-input-name");
            new Click().clickByid("saveQuestionDetails1");//click on the save button
            new Click().clickBycssselector("div[class='btn btn-rounded btn-default lsm-createAssignment-done m-r-md selected btn-blue']");//click on the review button
            new Click().clickByid("assessments-use-button");//click on the next button
            new Click().clickByid("share-assessment-button");
            new Navigator().navigateTo("districtAssessments");//Navigate to assessment
            driver.findElement(By.cssSelector("span.as-edit")).click();
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span.as-modal-yes-btn.yes-delete")));
            driver.findElement(By.cssSelector("span.as-modal-yes-btn.yes-delete")).click();
            driver.findElement(By.cssSelector("span#assessments-back-button")).click();
            new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']");//click on the create besides elos
            new Click().clickByid("qtn-true-false-type");//click on the true false question
            new TextSend().textsendbyid("sample true false question", "question-raw-content");
            new Click().clickByclassname("true-false-answer-select");
            new TextSend().textsendbyclass("assignmnet", "lsm-createAssignment-input-name");
            new Click().clickByid("saveQuestionDetails1");//click on the save button
            new Click().clickBycssselector("div[class='btn btn-rounded btn-default lsm-createAssignment-done m-r-md selected btn-blue']");//click on the review button
            new Click().clickByid("assessments-use-button");//click on the next button
            Assert.assertEquals(driver.findElement(By.cssSelector("div[class='col-xs-5 col-sm-2 lsm-assignment-total-points total_point m-t-xs m-l-sm']")).getText(),"2","Total point not increased");
            Assert.assertTrue(assign.getRadioButton_district().getText().contains("checked"),"Sharing level is Not selected as district");
            new Click().clickByid("share-assessment-button");
            new Click().clickByid("view-assign-button");
            driver.findElement(By.cssSelector("h4[class='as-title as-label']")).click();
            Assert.assertEquals(driver.findElements(By.cssSelector(".lsm-createAssignment-Question")).size(),2,"Number of question not increased");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in editAssessmentSharingPrivate method in class EditAssessment",e);
        }
    }
}
