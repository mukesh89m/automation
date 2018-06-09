package com.snapwiz.selenium.tests.staf.edulastic.testcases.regression.rubric;

import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Priyanka on 25-10-2016.
 */
public class DeleteRubric extends Driver{
    AddQuestion addQuestion;
    Assessments assessments;
    SchoolPage schoolPage;
    QuestionTypesPage questionTypesPage;
    MyAssessments myAssessments;
    AssessmentDetailsPage assessmentDetailsPage;
    AssignmentReview assignmentReview;
    String actual = null;
    String expected = null;

    @BeforeMethod
    public void init(){
        WebDriver driver= Driver.getWebDriver();
        addQuestion= PageFactory.initElements(driver,AddQuestion.class);
        assessments = PageFactory.initElements(driver, Assessments.class);
        schoolPage = PageFactory.initElements(driver, SchoolPage.class);
        questionTypesPage=PageFactory.initElements(driver,QuestionTypesPage.class);
        myAssessments = PageFactory.initElements(driver,MyAssessments.class);;
        assessmentDetailsPage = PageFactory.initElements(driver,AssessmentDetailsPage.class);;
        assignmentReview = PageFactory.initElements(driver,AssignmentReview.class);
    }

    @Test(priority = 1,enabled = true)
    public void deleteRubric(){
        try{

            int dataIndex = 1;
            int dataIndex2 = 2;
            int dataIndex3 = 3;
            WebDriver driver =getWebDriver();

            String assessmentname1 = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(1));
            String assessmentname2 = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(3));

            SchoolPage schoolPage = PageFactory.initElements(Driver.getWebDriver(), SchoolPage.class);
            String appendChar1 = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar2 = "b" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            //String appendChar1="axkL";
            //String appendChar2="bTPZ";

            new SignUp().teacher(appendChar1,dataIndex);//Sign up as an instructor1
            new School().enterAndSelectSchool("654321369",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode1 =  new Classes().createClass(appendChar1,dataIndex);//Create class
            System.out.println("classCode1 :" + classCode1);
            new Navigator().logout();//log out

            new SignUp().teacher(appendChar2, dataIndex);//Sign up as an instructor2
            new School().enterAndSelectSchool("654321369", dataIndex, false);//Create school
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode2 = new Classes().createClass(appendChar2, dataIndex);//Create class
            System.out.println("classCode2 :" + classCode2);
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1, dataIndex);//Login as an instructor1
            new Assignment().create(dataIndex, "truefalse");
            String rubricCreatedByAuthor1passage = new Assignment().addQuestion(dataIndex2, "passage");
            String rubricCreatedByAuthor1essay = new Assignment().addQuestion(dataIndex, "essay");
            Thread.sleep(2000);
            System.out.println("rubricCreatedByAuthor1essay :"+rubricCreatedByAuthor1essay);
            System.out.println("rubricCreatedByAuthor1passage :"+rubricCreatedByAuthor1passage);

         /*  String rubricCreatedByAuthor1essay ="DeleteRubric1Rk";
            String rubricCreatedByAuthor1passage ="DeleteRubric2ne";*/

            WebDriverUtil.clickOnElementUsingJavascript(addQuestion.share_link);
            addQuestion.shareRubric_yesLink.click(); //click on the  yes
            addQuestion.label_UseExistingRubric.click(); // click on the use existing Rubric tab
            new Navigator().navigateTo("dashboard");
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar2, dataIndex3);//Login as an instructor2
            new Assignment().create(dataIndex3, "truefalse");
            String rubricCreatedByAuthor2 = new Assignment().addQuestion(dataIndex3, "essay");
            System.out.println("rubricCreatedByAuthor2 :"+rubricCreatedByAuthor2);

            //delete icon should not displayed as rubricCreatedByAuthor1essay is created by author1
            WebDriverUtil.clickOnElementUsingJavascript(addQuestion.label_UseExistingRubric); // click on the use existing Rubric tab
            addQuestion.rubric_search_textarea.sendKeys(rubricCreatedByAuthor1essay);
            addQuestion.rubric_search_icon.click();
            int foundIndex = 0;
            for (WebElement element : addQuestion.rubricNamesCreatedByAuthor) {
                if (element.getText().trim().equals(rubricCreatedByAuthor1essay.trim())) {
                    break;
                }
                foundIndex++;
            }

            new MouseHover().mouserhoverbywebelement(addQuestion.rubricNamesCreatedByAuthor.get(foundIndex));
            int size = addQuestion.rubricOptionIcon.size();
            if(size!=2){
                CustomAssert.fail("Verify icon size","Size is not 2");
            }
            actual = addQuestion.rubricOptionIcon.get(0).getAttribute("title");
            CustomAssert.assertEquals(actual,"Preview","Verify View rubric: eye mark icon","View rubric: eye mark ico is displayed","View rubric: eye mark icon is not displaying");
            actual = addQuestion.rubricOptionIcon.get(1).getAttribute("title");
            CustomAssert.assertEquals(actual,"Clone","Verify Clone icon","Clone icon is displayed","Clone icon is not displaying");
            new Navigator().navigateTo("dashboard");
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1, dataIndex);//Login as an instructor1
            new Navigator().navigateTo("myAssessments");
            WebDriverUtil.waitForAjax(getWebDriver(),60);
            if(myAssessments.assignmentList.size()==0)
            {
                driver.navigate().refresh();
                Thread.sleep(5000);
                myAssessments.draft.click();//Click on Draft
                WebDriverUtil.waitForAjax(driver,60);
                if(myAssessments.assignmentList.size()==0){
                    driver.findElement(By.xpath("//input[@id='published']/..")).click(); //click on the publish
                    WebDriverUtil.waitForAjax(driver,60);
                    myAssessments.draft.click();//Click on Draft
                    WebDriverUtil.waitForAjax(driver,60);
                }

            }

            int index1 = 0;
            List<WebElement> allAssessment1 = myAssessments.names_assessment;
            if(allAssessment1.size()==0)
            {
                getWebDriver().navigate().refresh();
                WebDriverUtil.waitForAjax(getWebDriver(),60);
            }
            allAssessment1 = myAssessments.names_assessment;
            for (WebElement assessment : allAssessment1)
            {
                if (assessment.getText().equals(assessmentname1))
                    break;
                else
                    index1++;

            }
            Thread.sleep(5000);
            WebDriverUtil.clickOnElementUsingJavascript(myAssessments.names_assessment.get(index1));//click on Assessment
            assessmentDetailsPage.editAssesssment.click();//Click on edit button
            Thread.sleep(3000);
            assessmentDetailsPage.clickArrowIcon.get(2).click();
            assignmentReview.editButton.click();
            addQuestion.label_UseExistingRubric.click(); // click on the use existing Rubric tab
            addQuestion.rubric_search_textarea.sendKeys(rubricCreatedByAuthor1essay);
            addQuestion.rubric_search_icon.click();
            int foundIndex1 = 0;
            for (WebElement element : addQuestion.rubricNamesCreatedByAuthor) {
                if (element.getText().trim().equals(rubricCreatedByAuthor1essay.trim())) {
                    break;
                }
                foundIndex1++;
            }

            new MouseHover().mouserhoverbywebelement(addQuestion.rubricNamesCreatedByAuthor.get(foundIndex1));
            actual = addQuestion.rubricOptionIcon.get(2).getAttribute("title");
            CustomAssert.assertEquals(actual,"Delete","Verify Delete icon","Delete icon is displayed","Delete icon is not displaying");
            List<WebElement> rubricSize = getWebDriver().findElements(By.xpath("//div[contains(@class,'rubric-content-row rubric-search-item')]"));
            if(rubricSize.size() !=1){
                CustomAssert.fail("Verify rubric created by author","Created rubric is Rubric is present");
            }

            //Verification of delete rubric verification message
            addQuestion.rubricOptionIcon.get(2).click();//click on delete
            actual = addQuestion.deleteRubricNotificationMessage.getText();
            expected = "Deleting the Rubric will completely remove the data. Do you wish to continue?\n" + "Yes | No";
            CustomAssert.assertEquals(actual,expected,"Verify delete rubric notification message","Message is displaying as per expected","Notification message is not displaying");
            addQuestion.yesOnDeleteRubric.click();//click on yes
            Thread.sleep(3000);
            List<WebElement> rubricSizeAfterDelete = getWebDriver().findElements(By.xpath("//div[contains(@class,'rubric-content-row rubric-search-item')]"));
            if(rubricSizeAfterDelete.size() !=0){
                CustomAssert.fail("Verify deleted rubric ","Rubric is not deleted");
            }

            addQuestion.rubric_search_icon.click();
            Thread.sleep(2000);
            actual = addQuestion.rubricNotAvailableMessage.getText();
            CustomAssert.assertEquals(actual,"Searched Rubric is not available","Verify Searched Rubric is not available notification message","Rubric with the given name is not available","Rubric with the given name is available");
            new Navigator().navigateTo("dashboard");
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar2, dataIndex3);//Login as an instructor2
            new Navigator().navigateTo("myAssessments");
            WebDriverUtil.waitForAjax(driver,60);
            if(myAssessments.assignmentList.size()==0){
                driver.findElement(By.xpath("//input[@id='published']/..")).click(); //click on the publish
                WebDriverUtil.waitForAjax(driver,60);
                myAssessments.draft.click();//Click on Draft
                WebDriverUtil.waitForAjax(driver,60);
            }
            WebDriverUtil.waitForAjax(getWebDriver(),60);
            if(myAssessments.assignmentList.size()==0)
            {
                driver.navigate().refresh();
                Thread.sleep(5000);
                myAssessments.draft.click();//Click on Draft
                WebDriverUtil.waitForAjax(driver,60);
                if(myAssessments.assignmentList.size()==0){
                    driver.findElement(By.xpath("//input[@id='published']/..")).click(); //click on the publish
                    WebDriverUtil.waitForAjax(driver,60);
                    myAssessments.draft.click();//Click on Draft
                    WebDriverUtil.waitForAjax(driver,60);
                }

            }

            int index2 = 0;
            List<WebElement> allAssessment2 = myAssessments.names_assessment;
            if(allAssessment2.size()==0)
            {
                getWebDriver().navigate().refresh();
                WebDriverUtil.waitForAjax(getWebDriver(),60);
            }
            allAssessment2 = myAssessments.names_assessment;
            for (WebElement assessment : allAssessment2)
            {
                if (assessment.getText().equals(assessmentname2))
                    break;
                else
                    index2++;

            }
            WebDriverUtil.clickOnElementUsingJavascript(myAssessments.names_assessment.get(index2));//click on Assessment
            assessmentDetailsPage.editAssesssment.click();//Click on edit button
            Thread.sleep(7000);
            assessmentDetailsPage.clickArrowIcon.get(1).click();
            Thread.sleep(2000);
            assignmentReview.editButton.click();
            addQuestion.label_UseExistingRubric.click(); // click on the use existing Rubric tab
            Thread.sleep(3000);
            addQuestion.rubric_search_textarea.clear();
            addQuestion.rubric_search_textarea.sendKeys(rubricCreatedByAuthor1essay);
            addQuestion.rubric_search_icon.click();
            Thread.sleep(3000);
            actual = addQuestion.rubricNotAvailableMessage.getText();
            CustomAssert.assertEquals(actual,"Searched Rubric is not available","Verify Searched Rubric is not available notification message","Rubric with the given name is not available","Rubric with the given name is available");


        }catch(Exception e){
            Assert.fail("Exception in TC deleteRubric of class DeleteRubric", e);
        }

    }


}
