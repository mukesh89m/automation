package com.snapwiz.selenium.tests.staf.edulastic.testcases.regression.rubric;

import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AddQuestion;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assessments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.QuestionTypesPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.TakeAssignment;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Created by mukesh on 17/10/16.
 */
public class DisplayRubricQuestionInCreationPage extends Driver{

    AddQuestion addQuestion=null;
    Assessments assessments=null;
    SchoolPage schoolPage=null;
    QuestionTypesPage questionTypesPage=null;
    @BeforeMethod
    public void init(){
        WebDriver driver=Driver.getWebDriver();
        addQuestion=PageFactory.initElements(driver,AddQuestion.class);
        assessments = PageFactory.initElements(driver, Assessments.class);
        schoolPage = PageFactory.initElements(driver, SchoolPage.class);
        questionTypesPage=PageFactory.initElements(driver,QuestionTypesPage.class);
    }

    @Test(priority = 1,enabled = true)
    public void displayRubricQuestionInCreationPage(){
        WebDriver driver=Driver.getWebDriver();
        try{
            int dataIndex = 1;

            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String rubricname = ReadTestData.readDataByTagName("", "rubricname", Integer.toString(dataIndex));
            String rubricdescription = ReadTestData.readDataByTagName("", "rubricdescription", Integer.toString(dataIndex));
            String criteriadescription = ReadTestData.readDataByTagName("", "criteriadescription", Integer.toString(dataIndex));
            String ratingonedescription = ReadTestData.readDataByTagName("", "ratingonedescription", Integer.toString(dataIndex));
            String ratingtwodescription = ReadTestData.readDataByTagName("", "ratingtwodescription", Integer.toString(dataIndex));

            SchoolPage schoolPage = PageFactory.initElements(Driver.getWebDriver(), SchoolPage.class);
            String appendChar = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            //String appendChar="aMTd";


            new SignUp().teacher(appendChar,dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("654321369",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode =  new Classes().createClass(appendChar,dataIndex);//Create class
            System.out.println("classCode :" + classCode);

            new Navigator().navigateTo("assignment");//Navigate to assignment page
            WebDriverUtil.waitForAjax(driver,60);

            assessments.button_createCommonAssessment.click();//click on create new assignment

            driver.findElement(By.id("new-assessment-name")).clear();
            driver.findElement(By.id("new-assessment-name")).sendKeys(assessmentName);//give the assignment title
            new Click().clickByid("new-assessment-name");
            new Click().clickByXpath("//html/body");
            Thread.sleep(2000);
            new Click().clickByid("create-assessment-with-val");//click on Create
            WebDriverUtil.waitForAjax(driver,60);

            new Click().clickBycssselector("button[class^='btn btn-rounded btn-default authorQuestion']");//Click on Author Question
            WebDriverUtil.waitForAjax(driver,60);

            questionTypesPage.getIcon_essay().click(); //click on the essay question type
            WebDriverUtil.waitForAjax(driver,60);
            addQuestion.chkbox_GradingRubric.click(); //click on the grading rubic check box
            addQuestion.editBox_RubricName.sendKeys(rubricname+ StringUtil.generateRandomString(2, StringUtil.Mode.ALPHA));
            addQuestion.editBox_RubricDescription.sendKeys(rubricdescription);
            addQuestion.btn_RubricSaveAndContinue.click();
            Thread.sleep(2000);
            addQuestion.criteria_Description.click();
            WebDriverUtil.enterValueUsingJavascript(addQuestion.criteriaDescription_textarea,criteriadescription);
            addQuestion.rating_Description.get(0).click();
            WebDriverUtil.enterValueUsingJavascript(addQuestion.criteriaDescription_textarea,ratingonedescription);
            addQuestion.rating_Description.get(1).click();
            WebDriverUtil.enterValueUsingJavascript(addQuestion.criteriaDescription_textarea,ratingtwodescription);
            addQuestion.save_rubric_button.click();

            Thread.sleep(5000);
            String actual_rubricName= addQuestion.preview_rubric_name.getText().trim();
            CustomAssert.assertEquals(actual_rubricName.substring(0,actual_rubricName.length()-2),rubricname.trim(),"Verify rubric name","Rubric name is displaying in preview page","Rubric name is not displaying in preview page");
            CustomAssert.assertEquals(addQuestion.preview_rubric_description.getText().trim(),rubricdescription.trim(),"Verify rubric description","Rubric description is displaying in preview page","Rubric description is not displaying in preview page");
            CustomAssert.assertEquals(addQuestion.criteria_Description.getText().trim(),criteriadescription.trim(),"Verify criteria Description","Criteria description is displaying in preview page","Criteria description is not displaying in preview page");
            CustomAssert.assertEquals(addQuestion.rating_Description.get(0).getText().trim(),ratingonedescription.trim(),"Verify rating one ","Rating one is displaying in preview page","Rating one is not displaying in preview page");
            CustomAssert.assertEquals(addQuestion.rating_Description.get(1).getText().trim(),ratingtwodescription.trim(),"Verify rating two","Rating one is displaying in preview page","Rating two is not displaying in preview page");

            CustomAssert.assertEquals(addQuestion.edit_link.getText().trim(),"Edit","Verify Edit link","Edit link is displaying in preview page","Edit link is not displaying in preview page");
            CustomAssert.assertEquals(addQuestion.share_link.getText().trim(),"Share".trim(),"Verify rubric link","Share link is displaying in preview page","Share link is not displaying in preview page");
            CustomAssert.assertEquals(addQuestion.delete_link.getText().trim(),"Delete".trim(),"Verify Delete link","Delete link is displaying in preview page","Delete link is not displaying in preview page");

            //edit rubric
            addQuestion.edit_link.click(); //click on the edit link
            addQuestion.rubric_name_edit_icon.click(); //click on the rubric_name_edit_icon
            addQuestion.editBox_RubricName.clear();
            addQuestion.editBox_RubricName.sendKeys(rubricname+ StringUtil.generateRandomString(4, StringUtil.Mode.ALPHA));
            addQuestion.btn_RubricSaveAndContinue.click(); //click on the save button
            Thread.sleep(5000);

            WebDriverUtil.clickOnElementUsingJavascript(addQuestion.edit_ratingPoints.get(1)); //click on the second rating point;

            addQuestion.edit_rating_points.clear();
            addQuestion.edit_rating_points.sendKeys("3");
            Thread.sleep(2000);
            WebDriverUtil.waitForAjax(driver,60);
            addQuestion.save_rubric_button.click();
            Thread.sleep(5000);
            actual_rubricName= addQuestion.preview_rubric_name.getText().trim();

            CustomAssert.assertEquals(actual_rubricName.substring(0,actual_rubricName.length()-4),rubricname.trim(),"Verify rubric name after edit","Rubric name is getting edited","Rubric name is not getting edited"); //bug id:ED-852
            CustomAssert.assertEquals(addQuestion.edit_ratingPoints.get(1).getText().trim(),"3","Verify rating points","Able to edit rating point","Not able to edit rating point");
            String updatedPoints =(String)((JavascriptExecutor) driver).executeScript("return document.getElementById('points-input-tag').value");
            //String updatedPoints1 =(String)((JavascriptExecutor) driver).executeScript("return $('#points-input-tag').val()");
            CustomAssert.assertEquals(updatedPoints,"3.00","Verify total score","Total score updated","Total score not updated");
            addQuestion.share_link.click();
            addQuestion.shareRubric_yesLink.click(); //click on the  yes


        }catch (Exception e){
            Assert.fail("Exception in TC displayRubricQuestionInCreationPage of class DisplayRubricQuestionInCreationPage", e);

        }
    }

    @Test(priority = 2,enabled = true)
    public void cloneRubricCreatedByOtherTeacher(){
        WebDriver driver=Driver.getWebDriver();
        try{
            int dataIndex = 2;

            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String rubricname = ReadTestData.readDataByTagName("", "rubricname", Integer.toString(dataIndex));
            String rubricdescription = ReadTestData.readDataByTagName("", "rubricdescription", Integer.toString(dataIndex));
            String criteriadescription = ReadTestData.readDataByTagName("", "criteriadescription", Integer.toString(dataIndex));
            String ratingonedescription = ReadTestData.readDataByTagName("", "ratingonedescription", Integer.toString(dataIndex));
            String ratingtwodescription = ReadTestData.readDataByTagName("", "ratingtwodescription", Integer.toString(dataIndex));


            SchoolPage schoolPage = PageFactory.initElements(Driver.getWebDriver(), SchoolPage.class);
            String appendChar = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            //String appendChar="aMTd";


            new SignUp().teacher(appendChar,dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("654321369",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode =  new Classes().createClass(appendChar,dataIndex);//Create class
            System.out.println("classCode :" + classCode);

            new Navigator().navigateTo("assignment");//Navigate to assignment page
            WebDriverUtil.waitForAjax(driver,60);

            assessments.button_createCommonAssessment.click();//click on create new assignment

            driver.findElement(By.id("new-assessment-name")).clear();
            driver.findElement(By.id("new-assessment-name")).sendKeys(assessmentName);//give the assignment title
            new Click().clickByid("new-assessment-name");
            new Click().clickByXpath("//html/body");
            new Click().clickByid("create-assessment-with-val");//click on Create
            WebDriverUtil.waitForAjax(driver,60);

            new Click().clickBycssselector("button[class^='btn btn-rounded btn-default authorQuestion']");//Click on Author Question
            WebDriverUtil.waitForAjax(driver,60);

            questionTypesPage.getIcon_essay().click(); //click on the essay question type
            WebDriverUtil.waitForAjax(driver, 60);
            addQuestion.chkbox_GradingRubric.click(); //click on the grading rubic check box
            addQuestion.label_UseExistingRubric.click(); // click on the use existing Rubric tab
            addQuestion.rubric_search_textarea.sendKeys(rubricname);
            addQuestion.rubric_search_icon.click();  //click on the search icon
            Thread.sleep(3000);
            WebDriverUtil.mouseHover(addQuestion.rubric_content.get(0));
            addQuestion.previewLink.get(0).click(); //click on the preview link
            addQuestion.clone_link.click(); //click on the clone link
            Thread.sleep(5000);
            addQuestion.btn_RubricSaveAndContinue.click();
            Thread.sleep(5000);
            addQuestion.save_rubric_button.click();

            Thread.sleep(5000);
            String actual_rubricName= addQuestion.preview_rubric_name.getText().trim();
            CustomAssert.assertEquals(actual_rubricName.substring(0,actual_rubricName.length()-4),"Clone of "+rubricname.trim(),"Verify rubric name","Rubric name is displaying in preview page","Rubric name is not displaying in preview page");
            CustomAssert.assertEquals(addQuestion.preview_rubric_description.getText().trim(),rubricdescription.trim(),"Verify rubric description","Rubric description is displaying in preview page","Rubric description is not displaying in preview page");
            CustomAssert.assertEquals(addQuestion.criteria_Description.getText().trim(),criteriadescription.trim(),"Verify criteria Description","Criteria description is displaying in preview page","Criteria description is not displaying in preview page");
            CustomAssert.assertEquals(addQuestion.rating_Description.get(0).getText().trim(),ratingonedescription.trim(),"Verify rating one ","Rating one is displaying in preview page","Rating one is not displaying in preview page");
            CustomAssert.assertEquals(addQuestion.rating_Description.get(1).getText().trim(),ratingtwodescription.trim(),"Verify rating two","Rating one is displaying in preview page","Rating two is not displaying in preview page");

            CustomAssert.assertEquals(addQuestion.edit_link.getText().trim(),"Edit","Verify Edit link","Edit link is displaying in preview page","Edit link is not displaying in preview page");
            CustomAssert.assertEquals(addQuestion.share_link.getText().trim(),"Share".trim(),"Verify rubric link","Share link is displaying in preview page","Share link is not displaying in preview page");
            CustomAssert.assertEquals(addQuestion.delete_link.getText().trim(),"Delete".trim(),"Verify Delete link","Delete link is displaying in preview page","Delete link is not displaying in preview page");

            CustomAssert.assertEquals(addQuestion.edit_ratingPoints.get(1).getText().trim(),"3","Verify rating points","Able to edit rating point","Not able to edit rating point");
            String updatedPoints =(String)((JavascriptExecutor) driver).executeScript("return document.getElementById('points-input-tag').value");
            CustomAssert.assertEquals(updatedPoints,"3.00","Verify total score","Total score updated","Total score not updated");

            addQuestion.label_UseExistingRubric.click(); // click on the use existing Rubric tab
            addQuestion.rubric_search_textarea.sendKeys(rubricname);
            addQuestion.rubric_search_icon.click();  //click on the search icon
            CustomAssert.assertEquals(addQuestion.rubric_content.get(0).getText().trim().substring(0,actual_rubricName.length()-4),"Clone of "+rubricname.trim(),"Verify cloned rubric name","Cloned Rubric name is displaying in preview page","Cloned Rubric name is not displaying in preview page");
            CustomAssert.assertEquals(addQuestion.rubric_options.size(),4,"Verify rubric options of cloned rubric","Rubric options is displaying for cloned rubric","Rubric options is not displaying for cloned rubric");


        }catch (Exception e){
            Assert.fail("Exception in TC cloneRubricCreatedByOtherTeacher of class DisplayRubricQuestionInCreationPage", e);

        }
    }

    @Test(priority = 3,enabled = true)
    public void cloneRubric(){
        WebDriver driver=Driver.getWebDriver();
        try{
            int dataIndex = 3;

            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String rubricname = ReadTestData.readDataByTagName("", "rubricname", Integer.toString(dataIndex));
            String rubricdescription = ReadTestData.readDataByTagName("", "rubricdescription", Integer.toString(dataIndex));
            String criteriadescription = ReadTestData.readDataByTagName("", "criteriadescription", Integer.toString(dataIndex));
            String ratingonedescription = ReadTestData.readDataByTagName("", "ratingonedescription", Integer.toString(dataIndex));
            String ratingtwodescription = ReadTestData.readDataByTagName("", "ratingtwodescription", Integer.toString(dataIndex));


            SchoolPage schoolPage = PageFactory.initElements(Driver.getWebDriver(), SchoolPage.class);
            String appendChar = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            //String appendChar="aMTd";
            System.out.println("appendChar:"+appendChar);

            new SignUp().teacher(appendChar,dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("654321369",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode =  new Classes().createClass(appendChar,dataIndex);//Create class
            System.out.println("classCode :" + classCode);

            new Navigator().navigateTo("assignment");//Navigate to assignment page
            WebDriverUtil.waitForAjax(driver,60);

            assessments.button_createCommonAssessment.click();//click on create new assignment

            driver.findElement(By.id("new-assessment-name")).clear();
            driver.findElement(By.id("new-assessment-name")).sendKeys(assessmentName);//give the assignment title
            new Click().clickByid("new-assessment-name");
            new Click().clickByXpath("//html/body");
            new Click().clickByid("create-assessment-with-val");//click on Create
            WebDriverUtil.waitForAjax(driver,60);

            new Click().clickBycssselector("button[class^='btn btn-rounded btn-default authorQuestion']");//Click on Author Question
            WebDriverUtil.waitForAjax(driver,60);

            questionTypesPage.getIcon_essay().click(); //click on the essay question type
            WebDriverUtil.waitForAjax(driver,60);
            addQuestion.chkbox_GradingRubric.click(); //click on the grading rubic check box
            addQuestion.editBox_RubricName.sendKeys(rubricname+ StringUtil.generateRandomString(2, StringUtil.Mode.ALPHA));
            addQuestion.editBox_RubricDescription.sendKeys(rubricdescription);
            addQuestion.btn_RubricSaveAndContinue.click();
            Thread.sleep(2000);
            addQuestion.criteria_Description.click();
            WebDriverUtil.enterValueUsingJavascript(addQuestion.criteriaDescription_textarea,criteriadescription);
            addQuestion.rating_Description.get(0).click();
            WebDriverUtil.enterValueUsingJavascript(addQuestion.criteriaDescription_textarea,ratingonedescription);
            addQuestion.rating_Description.get(1).click();
            WebDriverUtil.enterValueUsingJavascript(addQuestion.criteriaDescription_textarea,ratingtwodescription);
            addQuestion.save_rubric_button.click();
            Thread.sleep(5000);
            //clone rubric
            WebDriverUtil.waitForAjax(driver,60);
            WebDriverUtil.clickOnElementUsingJavascript(addQuestion.label_UseExistingRubric);
            addQuestion.rubric_search_textarea.sendKeys(rubricname);
            Thread.sleep(3000);
            WebDriverUtil.clickOnElementUsingJavascript(addQuestion.rubric_search_icon);
            Thread.sleep(5000);
            WebDriverUtil.mouseHover(addQuestion.rubric_content.get(0));
            addQuestion.previewLink.get(0).click(); //click on the preview link
            WebDriverUtil.clickOnElementUsingJavascript(addQuestion.clone_link); //click on the clone link
            Thread.sleep(5000);
            addQuestion.btn_RubricSaveAndContinue.click();
            Thread.sleep(5000);
            addQuestion.save_rubric_button.click();
            Thread.sleep(5000);
            String actual_rubricName= addQuestion.preview_rubric_name.getText().trim();
            CustomAssert.assertEquals(actual_rubricName.substring(0,actual_rubricName.length()-2),"Clone of "+rubricname.trim(),"Verify rubric name","Rubric name is displaying in preview page","Rubric name is not displaying in preview page");
            CustomAssert.assertEquals(addQuestion.preview_rubric_description.getText().trim(),rubricdescription.trim(),"Verify rubric description","Rubric description is displaying in preview page","Rubric description is not displaying in preview page");
            CustomAssert.assertEquals(addQuestion.criteria_Description.getText().trim(),criteriadescription.trim(),"Verify criteria Description","Criteria description is displaying in preview page","Criteria description is not displaying in preview page");
            CustomAssert.assertEquals(addQuestion.rating_Description.get(0).getText().trim(),ratingonedescription.trim(),"Verify rating one ","Rating one is displaying in preview page","Rating one is not displaying in preview page");
            CustomAssert.assertEquals(addQuestion.rating_Description.get(1).getText().trim(),ratingtwodescription.trim(),"Verify rating two","Rating one is displaying in preview page","Rating two is not displaying in preview page");

            CustomAssert.assertEquals(addQuestion.edit_link.getText().trim(),"Edit","Verify Edit link","Edit link is displaying in preview page","Edit link is not displaying in preview page");
            CustomAssert.assertEquals(addQuestion.share_link.getText().trim(),"Share".trim(),"Verify rubric link","Share link is displaying in preview page","Share link is not displaying in preview page");
            CustomAssert.assertEquals(addQuestion.delete_link.getText().trim(),"Delete".trim(),"Verify Delete link","Delete link is displaying in preview page","Delete link is not displaying in preview page");

            CustomAssert.assertEquals(addQuestion.edit_ratingPoints.get(1).getText().trim(),"1","Verify rating points","Able to edit rating point","Not able to edit rating point");
            String updatedPoints =(String)((JavascriptExecutor) driver).executeScript("return document.getElementById('points-input-tag').value");
            CustomAssert.assertEquals(updatedPoints,"1.00","Verify total score","Total score updated","Total score not updated");

            addQuestion.label_UseExistingRubric.click(); // click on the use existing Rubric tab
            addQuestion.rubric_search_textarea.sendKeys(rubricname);
            addQuestion.rubric_search_icon.click();  //click on the search icon
            Thread.sleep(5000);
            System.out.println("actual_rubricName:"+actual_rubricName);
            CustomAssert.assertEquals(addQuestion.rubric_content.get(1).getText().trim().substring(0,actual_rubricName.length()-2),"Clone of "+rubricname.trim(),"Verify cloned rubric name","Cloned Rubric name is displaying in preview page","Cloned Rubric name is not displaying in preview page");
            WebDriverUtil.mouseHover(addQuestion.rubric_content.get(1));
            CustomAssert.assertEquals(addQuestion.rubric_options.size(),4,"Verify rubric options of cloned rubric","Rubric options is displaying for cloned rubric","Rubric options is not displaying for cloned rubric");


        }catch (Exception e){
            Assert.fail("Exception in TC cloneRubric of class DisplayRubricQuestionInCreationPage", e);

        }
    }

    @Test(priority = 4,enabled = true)
    public void shareRubric(){
        WebDriver driver=Driver.getWebDriver();
        try{
            int dataIndex = 4;

            String assessmentName = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String rubricname = ReadTestData.readDataByTagName("", "rubricname", Integer.toString(dataIndex));
            String rubricdescription = ReadTestData.readDataByTagName("", "rubricdescription", Integer.toString(dataIndex));
            String criteriadescription = ReadTestData.readDataByTagName("", "criteriadescription", Integer.toString(dataIndex));
            String ratingonedescription = ReadTestData.readDataByTagName("", "ratingonedescription", Integer.toString(dataIndex));
            String ratingtwodescription = ReadTestData.readDataByTagName("", "ratingtwodescription", Integer.toString(dataIndex));


            SchoolPage schoolPage = PageFactory.initElements(Driver.getWebDriver(), SchoolPage.class);
            String appendChar = "a" + StringUtil.generateRandomString(2, StringUtil.Mode.ALPHA);
            //String appendChar="aMTd";


            new SignUp().teacher(appendChar,dataIndex);//Sign up as an instructor
            new School().enterAndSelectSchool("654321369",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode =  new Classes().createClass(appendChar,dataIndex);//Create class
            System.out.println("classCode :" + classCode);

            new Navigator().navigateTo("assignment");//Navigate to assignment page
            WebDriverUtil.waitForAjax(driver,60);

            assessments.button_createCommonAssessment.click();//click on create new assignment

            driver.findElement(By.id("new-assessment-name")).clear();
            driver.findElement(By.id("new-assessment-name")).sendKeys(assessmentName);//give the assignment title
            new Click().clickByid("new-assessment-name");
            new Click().clickByXpath("//html/body");
            new Click().clickByid("create-assessment-with-val");//click on Create
            WebDriverUtil.waitForAjax(driver,60);

            new Click().clickBycssselector("button[class^='btn btn-rounded btn-default authorQuestion']");//Click on Author Question
            WebDriverUtil.waitForAjax(driver,60);

            questionTypesPage.getIcon_essay().click(); //click on the essay question type
            WebDriverUtil.waitForAjax(driver,60);
            addQuestion.chkbox_GradingRubric.click(); //click on the grading rubic check box
            addQuestion.editBox_RubricName.sendKeys(rubricname+appendChar);
            addQuestion.editBox_RubricDescription.sendKeys(rubricdescription);
            addQuestion.btn_RubricSaveAndContinue.click();
            Thread.sleep(2000);
            addQuestion.criteria_Description.click();
            WebDriverUtil.enterValueUsingJavascript(addQuestion.criteriaDescription_textarea,criteriadescription);
            addQuestion.rating_Description.get(0).click();
            WebDriverUtil.enterValueUsingJavascript(addQuestion.criteriaDescription_textarea,ratingonedescription);
            addQuestion.rating_Description.get(1).click();
            WebDriverUtil.enterValueUsingJavascript(addQuestion.criteriaDescription_textarea,ratingtwodescription);
            addQuestion.save_rubric_button.click();


            //share rubric
            addQuestion.share_link.click();
            addQuestion.shareRubric_yesLink.click(); //click on the  yes
            Thread.sleep(2000);
            CustomAssert.assertEquals(addQuestion.share_link.getText().trim(),"Unshare".trim(),"Verify Unshare link","Unshare link is displaying in preview page","Unshare link is not displaying in preview page");

            WebDriverUtil.clickOnElementUsingJavascript(addQuestion.label_UseExistingRubric);
            addQuestion.rubric_search_textarea.sendKeys(rubricname+appendChar);
            Thread.sleep(3000);
            WebDriverUtil.clickOnElementUsingJavascript(addQuestion.rubric_search_icon);
            Thread.sleep(5000);
            WebDriverUtil.mouseHover(addQuestion.rubric_content.get(0));
            Thread.sleep(2000);
            CustomAssert.assertEquals(addQuestion.unshare_link.getAttribute("title").trim(),"Unshare".trim(),"Verify Unshare link","Unshare link is displaying in preview page","Unshare link is not displaying in preview page");

        }catch (Exception e){
            Assert.fail("Exception in TC shareRubric of class DisplayRubricQuestionInCreationPage", e);

        }
    }

}
