package com.snapwiz.selenium.tests.staf.edulastic.testcases.regression.rubric;

import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Classes;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Navigator;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.School;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.SignUp;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Murthi on 10/17/2016.
 */
public class Rubric extends Driver {

    @Test
    public void gradingRubricOptionForNonPremiumUser() {

        WebDriver driver = Driver.getWebDriver();
        try {
            ReportUtil.log("Description", "This test case verifies assignment creation with rubric rating for non premium user", "info");

            //page object init
            AddQuestion addQuestion = PageFactory.initElements(driver, AddQuestion.class);
            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);

            //Rubric for non-premium user
            //Rubric option should not be available for non premium user

            String appendChar1 = "a" + StringUtil.generateRandomString(5, StringUtil.Mode.ALPHA);
            int dataIndex = 1;
            String assessmentName = "RubricAssignment" + appendChar1;


            new SignUp().teacher(appendChar1, dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("987654963", dataIndex, false);//Create school use zipcode 987654963 for non premium user
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode = new Classes().createClass(appendChar1, dataIndex);//Create class
            System.out.println("classCode :" + classCode);

            new Navigator().navigateTo("assignment");//Navigate to assignment page
            WebDriverUtil.waitForAjax(driver, 60);

            assessments.button_createCommonAssessment.click();//click on create new assignment

            driver.findElement(By.id("new-assessment-name")).clear();
            driver.findElement(By.id("new-assessment-name")).sendKeys(assessmentName);//give the assignment title
            new Click().clickByid("new-assessment-name");
            new Click().clickByXpath("//html/body");
            Thread.sleep(2000);
            new Click().clickByid("create-assessment-with-val");//click on Create
            WebDriverUtil.waitForAjax(driver, 60);

            new Click().clickBycssselector("button[class^='btn btn-rounded btn-default authorQuestion']");//Click on Author Question
            WebDriverUtil.waitForAjax(driver, 60);

            questionTypesPage.getIcon_essay().click();
            WebDriverUtil.waitForAjax(driver, 60);


            if (driver.findElements(By.cssSelector(".essay-question-rubric-wrapper>label")).size() > 0) //check for non existence of rubric option
                CustomAssert.fail("Verify Grading Rubric option for non premium user", "Rubric Option is available for non premium user");

            new Navigator().navigateTo("dashboard");//Navigate to dashboard page
            new Navigator().logout();//log out


        } catch (Exception e) {
            Assert.fail("Exception in 'gradingRubricOptionForNonPremiumUser' method in 'Rubric' class", e);
        }
    }


    @Test
    public void createRubric() {

        WebDriver driver = Driver.getWebDriver();
        try {
            ReportUtil.log("Description", "This test case verifies assignment creation with rubric rating for premium user", "info");


            //page object init
            AddQuestion addQuestion = PageFactory.initElements(driver, AddQuestion.class);
            Assessments assessments = PageFactory.initElements(driver, Assessments.class);
            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);
            QuestionTypesPage questionTypesPage = PageFactory.initElements(driver, QuestionTypesPage.class);

            //Rubric option for premium user
             String appendChar1 = "a" + StringUtil.generateRandomString(5, StringUtil.Mode.ALPHA);
            int dataIndex = 1;
            String methodName = new Exception().getStackTrace()[0].getMethodName();
            String assessmentName = methodName + appendChar1;

            new SignUp().teacher(appendChar1, dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("654321369", dataIndex, false);//Create school use zip code 654321369 for non premium user
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode = new Classes().createClass(appendChar1, dataIndex);//Create class
            System.out.println("classCode :" + classCode);

            new Navigator().navigateTo("assignment");//Navigate to assignment page
            WebDriverUtil.waitForAjax(driver, 60);

            assessments.button_createCommonAssessment.click();//click on create new assignment

            driver.findElement(By.id("new-assessment-name")).clear();
            driver.findElement(By.id("new-assessment-name")).sendKeys(assessmentName);//give the assignment title
            new Click().clickByid("new-assessment-name");
            new Click().clickByXpath("//html/body");
            new Click().clickByid("create-assessment-with-val");//click on Create
            WebDriverUtil.waitForAjax(driver, 60);

            new Click().clickBycssselector("button[class^='btn btn-rounded btn-default authorQuestion']");//Click on Author Question
            WebDriverUtil.waitForAjax(driver, 60);

            questionTypesPage.getIcon_essay().click();
            WebDriverUtil.waitForAjax(driver, 60);

            CustomAssert.assertEquals(addQuestion.chkbox_GradingRubric.isDisplayed(),true,"Rubric option should be displayed","Rubric option is available for premium user","Rubric option is not available for premium user"); //Checking 'grading rubric' checkbox

            addQuestion.chkbox_GradingRubric.click();//click on grading rubric checkbox

            //UI - create rubric tab,Rubric creation
            CustomAssert.assertEquals(addQuestion.label_CreateNewRubric.isDisplayed(),true,"Create New Rubric Tab should be displayed","Create New Rubric Tab is available for premium user","Create New Rubric Tab is not available after click on grading rubric checkbox"); //Checking 'Create New Rubric' Tab
            CustomAssert.assertEquals(addQuestion.label_UseExistingRubric.isDisplayed(),true,"Use Existing Rubric Tab should be displayed","Use Existing Rubric Tab is available for premium user","Use Existing Rubric Tab is not available after click on grading rubric checkbox"); //Checking 'Use Existing Rubric' Tab

            //a)A text field with default text 'Enter rubric name' must be displayed.
            CustomAssert.assertEquals(addQuestion.editBox_RubricName.isDisplayed(),true,"Edit box Rubric Name should be displayed","Edit box Rubric Name is available for premium user","Edit box Rubric Name is not available after click on grading rubric checkbox");
            CustomAssert.assertEquals(addQuestion.editBox_RubricName.getAttribute("placeholder"),"Enter rubric name","Default text 'Enter rubric name' is displayed","Default text 'Enter rubric name' is displayed with name field","Default text 'Enter rubric name' is not displayed with name field");

            //c)A text field with default text 'description' must be displayed
            CustomAssert.assertEquals(addQuestion.editBox_RubricDescription.isDisplayed(),true,"Edit box Rubric Description should be displayed","Edit box Rubric Description is available for premium user","Edit box Rubric Description is not available after click on grading rubric checkbox");
            CustomAssert.assertEquals(addQuestion.editBox_RubricDescription.getAttribute("placeholder"),"Description","Default text 'Description' is displayed","Default text 'Description' is displayed with Description field","Default text 'Description' is not displayed with Description field");

            //b)'Create rubric' button must be displayed beside the rubric name text field.
             CustomAssert.assertEquals(addQuestion.btn_RubricSaveAndContinue.isDisplayed(),true,"Rubric save and continue button is displayed","Rubric save and continue button is displayed after clicking on grading rubric option","Rubric save and continue button is not displayed after clicking on grading rubric option");

            // Rubric name field - manadatory field if given blank validation message must be displayed
            addQuestion.btn_RubricSaveAndContinue.click();
            CustomAssert.assertEquals(addQuestion.errMsg_RubricName.isDisplayed(),true,"Validation message 'Please provide a valid rubric name' is displayed","validation message 'Please provide a valid rubric name' is displayed when we save without name","validation message 'Please provide a valid rubric name' is not displayed when we save without name");


            //Adding less than 100 characters in the rubric name field
            addQuestion.editBox_RubricName.sendKeys("Rubric"+appendChar1);


            //Creation of default criteria and ratings, UI - default criteria and rating template
            addQuestion.btn_RubricSaveAndContinue.click();

            //1.Author must be able to save the rubric.
            CustomAssert.assertEquals(addQuestion.createCriteria.isDisplayed(),true,"Author able to save the rubric","Author able to save the rubric","Author is not able to save the rubric");
            CustomAssert.assertEquals(addQuestion.lbl_RubricName.isDisplayed(),true,"Rubric Name should be displayed","Rubric name displayed after creating it","Rubric name is displayed after creating it");
            CustomAssert.assertEquals(addQuestion.btn_RubricNameEdit.isDisplayed(),true,"Rubric Name Edit(pencil) icon should be displayed","Rubric name edit(pencil) icon displayed beside rubric name","Rubric name edit(pencil) icon is not displayed beside rubric name");

            //The rubric must be created with the default
            CustomAssert.assertEquals(addQuestion.lbl_CriteriaName1.isDisplayed(),true,"Default criteria created","Default criteria created with label Criteria Name 1","Default criteria is not created with label Criteria Name 1");
            CustomAssert.assertEquals(addQuestion.lstLbl_CriteriaRatings.size()==2,true,"Default criteria ratings created","Default number of criteria ratings created is 2","Default number of criteria ratings is not 2");
            CustomAssert.assertEquals(addQuestion.lstLbl_CriteriaPoints.get(0).getText(),"0","Default criteria rating point is 0","Default criteria rating point is 0 for Rating 1","Default criteria rating point is not 0 for Rating 1");

            //e) 'Add Rating' button must be displayed beside the Rating name and Rating description.
            CustomAssert.assertEquals(addQuestion.btn_AddRating.isDisplayed(),true,"Add Rating button should be displayed","Add Rating button displayed after creating rubric","Add Rating button is displayed after creating rubric");

            //f) 'Add criteria' button below the Criteria tabular.
            CustomAssert.assertEquals(addQuestion.btn_AddCriteria.isDisplayed(),true,"Add Criteria button should be displayed","Add Criteria button displayed after creating rubric","Add Criteria button is displayed after creating rubric");


            //g) 'Save' and 'Cancel' buttons."
            CustomAssert.assertEquals(addQuestion.btn_RubricCriteriaCancel.isDisplayed(),true,"Cancel Criteria button should be displayed","Cancel Criteria button displayed after creating rubric","Cancel Criteria button is displayed after creating rubric");
            CustomAssert.assertEquals(addQuestion.btn_RubricCriteriaSave.isDisplayed(),true,"Save Criteria button should be displayed","Save Criteria button displayed after creating rubric","Save Criteria button is displayed after creating rubric");

            //Criteria creation- Validation message will be displayed when the name field is given blank
//            addQuestion.lbl_CriteriaName1.click();
//            addQuestion.editBox_CriteriaName1.clear();
//            addQuestion.btn_RubricCriteriaSave.click();

            //1.Criteria must not be created and warning message ""Please provide a valid criteria name must be displayed

            //6.Enter the criteria name and description and click on save.
            addQuestion.lbl_CriteriaName1.click();
            addQuestion.editBox_CriteriaName1.sendKeys("Criteria1");
            addQuestion.lbl_CriteriaDesc1.click();
            addQuestion.editBox_CriteriaDesc1.sendKeys("Criteria1Description");
            addQuestion.btn_RubricCriteriaSave.click();

            //3.The criteria with the entered name must be created.
            CustomAssert.assertEquals(addQuestion.lnk_EditRubric.isDisplayed(),true,"Criteria saved","Criteria save with rubric","Criteria is not saved with rubric");
            CustomAssert.assertEquals(addQuestion.lbl_CriteriaName1.getText(),"Criteria1","Criteria is saved with name Criteria1","Criteria is saved with name Criteria1","Criteria is not saved with name Criteria1. The actual criteria name is "+addQuestion.lbl_CriteriaName1.getText());
            CustomAssert.assertEquals(addQuestion.lbl_CriteriaDesc1.getText(),"Criteria1","Criteria is saved with description Criteria1Description","Criteria is saved with description Criteria1Description","Criteria is not saved with description Criteria1Description. The actual criteria description is "+addQuestion.lbl_CriteriaDesc1.getText());


            //E16.6.1.12 -- Teacher editing the rating points during the rubric creation
            //Enter the name and description for both the ratings(say first rating - R1 and second rating - R2) and click on save
            addQuestion.lnk_EditRubric.click();
            addQuestion.lstLbl_CriteriaRatings.get(0).click();
            addQuestion.editBox_CriteriaRating.sendKeys("R1");

            addQuestion.lstLbl_CriteriaRatings.get(1).click();
            addQuestion.editBox_CriteriaRating.sendKeys("R2");

            addQuestion.btn_RubricCriteriaSave.click();

            CustomAssert.assertEquals(addQuestion.lstLbl_CriteriaRatings.get(0).getText(),"R1","Rating 1 name is changed to R1","Rating 1 name is changed to R1 after editing it","Rating 1 name is not change to R1 after editing it and the actual name is "+addQuestion.lstLbl_CriteriaRatings.get(0).getText());
            CustomAssert.assertEquals(addQuestion.lstLbl_CriteriaRatings.get(1).getText(),"R2","Rating 2 name is changed to R2","Rating 2 name is changed to R2 after editing it","Rating 2 name is not change to R2 after editing it and the actual name is "+addQuestion.lstLbl_CriteriaRatings.get(1).getText());

            //Adding multiple criteria

            addQuestion.lnk_EditRubric.click();

            addQuestion.btn_AddCriteria.click();

            addQuestion.btn_RubricCriteriaSave.click();

            CustomAssert.assertEquals(addQuestion.lstLbl_CriteriaRatings.size(),4,"Added second criteria to rubric","Able to add second criteria to rubric","Unable to add second criteria to rubric");

            addQuestion.btn_CancelQuestion.click();

            WebDriverUtil.waitForAjax(driver,60);

            //Adding rubric to passage question

            addQuestion.button_authorNewQuestion.click();

            questionTypesPage.tab_MathTechEnhanced.click();

            questionTypesPage.getIcon_passageType().click();
            WebDriverUtil.waitForAjax(driver,60);

            PassageBasedQuestionCreation passageBasedQuestionCreation=PageFactory.initElements(driver,PassageBasedQuestionCreation.class);

            passageBasedQuestionCreation.getTextField_EnterInstructions().sendKeys("This contain rubric ratings");







        } catch (Exception e) {
            Assert.fail("Exception in 'gradingRubricOptionForPremiumUser' method in 'Rubric' class", e);
        }
    }


}