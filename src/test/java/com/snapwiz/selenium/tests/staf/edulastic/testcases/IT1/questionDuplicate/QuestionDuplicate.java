package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.questionDuplicate;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentQuestionsListPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assignments;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.manageClass.ManageClass;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextSend;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by shashank on 05-03-2015.
 */
public class QuestionDuplicate extends Driver{
    @Test(priority = 1)
    public void questionDuplicate()
    {
        try {
            String appendChar = "a14";
            String appendChar1 ="b14";
            int dataIndex = 10;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            AssessmentQuestionsListPage assessmentQuestionsListPage=PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            ManageClass manageClass= PageFactory.initElements(driver,ManageClass.class);

            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher

           new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher

            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
           createWithOnlyName(appendChar, dataIndex);//create school
            String classCode=new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar, classCode, 1119);//create student
            new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar1, classCode, 1119);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            closeHelp();
            Thread.sleep(5000);
            new Assignment().create(dataIndex,"truefalse");//create assessment with one question
            assignments.getButton_review().click();//click on review button
            assessmentQuestionsListPage.getButton_saveForLater().click();//click on save for later
            Thread.sleep(2000);
            new Navigator().navigateTo("assignment");
            driver.findElement(By.xpath("//span[@class='as-assignment-draftTxt']")).click();
            Thread.sleep(1000);
            new Click().clickBycssselector("span.as-edit");
            new Click().clickBycssselector("span.as-questionDetails-clickArrow");
            manageClass.duplicateLinkWebElement().get(0).click();
            Thread.sleep(2000);
            Assert.assertTrue(driver.findElement(By.xpath("//span[@id='footer-notification-text']/label")).getText().contains("has been duplicated"),"Exception in QuestionDuplicate in method questionDuplicate");
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='lsm-createAssignment-total-questions']")).getText().contains("2"),"Exception in QuestionDuplicate in method questionDuplicate");
            assignments.getButton_review().click();//click on review button
            assignments.getButton_saveForLater().click();
            new Navigator().navigateTo("assignment");
            driver.findElement(By.xpath("//span[@class='as-assignment-draftTxt']")).click();
            Thread.sleep(1000);
            new Click().clickBycssselector("span.as-edit");
            Thread.sleep(1000);
            Assert.assertTrue(driver.findElements(By.xpath("//div[@class='question-row question-mode-review']")).size()==2,"Exception in QuestionDuplicate in method questionDuplicate");
            assessmentQuestionsListPage.getButton_saveForLater().click();//click on save for later*//**//*
            new Navigator().navigateTo("assignment");
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickByXpath("//div[text()='Create New Assessment']");//click on Create New Assignment
            manageClass.getOwnerMe().click();
            Thread.sleep(2000);
            manageClass.autoSelectWebElement().click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys(assessmentname);//give the assignment name
            assignments.getButton_review().click();
            Thread.sleep(1000);
            new Click().clickByclassname("as-questionDetails-clickArrow");
            manageClass.duplicateLinkWebElement().get(0).click();
            Thread.sleep(2000);
            Assert.assertTrue(driver.findElement(By.xpath("//span[@id='footer-notification-text']/label")).getText().contains("has been duplicated"),"Exception in QuestionDuplicate in method questionDuplicate");
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='lsm-createAssignment-total-questions']")).getText().contains("2"),"Exception in QuestionDuplicate in method questionDuplicate");
            assignments.getButton_review().click();//click on review button
            assignments.getButton_saveForLater().click();
            new Navigator().navigateTo("assignment");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[@class='as-assignment-draftTxt']")).click();
            Thread.sleep(1000);
            new Click().clickBycssselector("span.as-edit");
            Thread.sleep(1000);
            Assert.assertTrue(driver.findElements(By.xpath("//div[@class='question-row question-mode-review']")).size()==2,"Exception in QuestionDuplicate in method questionDuplicate");

            assignments.getButton_saveForLater().click();
            Thread.sleep(2000);
            new Assignment().assignToStudent(dataIndex,appendChar);//assign assessment to class
            Thread.sleep(4000);
            new Navigator().navigateTo("assignment");
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickByXpath("//div[text()='Use Existing Assessment']");//click on Create New Assignment
            Thread.sleep(2000);
            manageClass.getOwnerMeUsingExisting().click();
            new Click().clickBycssselector("span.as-questionDetails-clickArrow");
            Thread.sleep(2000);
            new Click().clickBycssselector("span.as-questionDetails-clickArrow");
            try {
                Assert.assertTrue(!manageClass.duplicateLinkWebElement().get(0).isDisplayed(),"Exception in QuestionDuplicate in method questionDuplicate");

            }
            catch (Exception e)
            {}
            new Click().clickBycssselector("span.as-question-preview-back-button");
            new Click().clickByid("assessments-back-link");

            new Click().clickByclassname("as-customize-button");
            Thread.sleep(2000);
            new Click().clickBycssselector("span.as-questionDetails-clickArrow");
            Assert.assertTrue(manageClass.duplicateLinkWebElement().get(0).isDisplayed(),"Exception in QuestionDuplicate in method questionDuplicate");
            manageClass.duplicateLinkWebElement().get(0).click();
            Thread.sleep(2000);
            Assert.assertTrue(driver.findElement(By.xpath("//input[@class='lsm-createAssignment-input-name']")).isEnabled(),"Exception in QuestionDuplicate in method questionDuplicate");
            Assert.assertTrue(driver.findElement(By.xpath("//div[@id='question-raw-content']")).isEnabled(),"Exception in QuestionDuplicate in method questionDuplicate");
            Assert.assertTrue(driver.findElement(By.xpath("//div[@id='content-solution']")).isEnabled(),"Exception in QuestionDuplicate in method questionDuplicate");
            Assert.assertTrue(driver.findElement(By.xpath("//div[@id='content-hint']")).isEnabled(),"Exception in QuestionDuplicate in method questionDuplicate");
            Assert.assertTrue(driver.findElement(By.xpath("//select[@id='difficulty-level-drop-down']")).isEnabled(),"Exception in QuestionDuplicate in method questionDuplicate");
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='as-footer-learning-objectives']")).isEnabled(),"Exception in QuestionDuplicate in method questionDuplicate");
           }
        catch (Exception e)
        {
            Assert.fail("Exception in QuestionDuplicate Class in method questionDuplicate",e);
        }

    }

    @Test(priority = 2)
    public void questionSharedAtDifferentLevels() {
        try {
            String appendChar = "a34";
            String appendChar1 ="b34";
            String appendChar2 ="c34";
            String appendChar3 ="d34";
            int dataIndex = 14;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            AssessmentQuestionsListPage assessmentQuestionsListPage=PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            ManageClass manageClass= PageFactory.initElements(driver,ManageClass.class);
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new SignUp().teacher(appendChar1,dataIndex);//SignUp as a teacher
            new SignUp().teacher(appendChar2,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            createWithOnlyName(appendChar, 11);//create school
            String classCode=new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar1, dataIndex);//Login as a teacher
            createWithOnlyName(appendChar1, 12);//create school
            String classCode2=new Classes().createClass(appendChar1, dataIndex);//create class
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar2, dataIndex);//Login as a teacher
            createWithOnlyName(appendChar3, 13);//create school
            String classCode3=new Classes().createClass(appendChar2, dataIndex);//create class
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"truefalse");//create assessment with one question
            assignments.getButton_review().click();//click on review button
            assessmentQuestionsListPage.getButton_saveForLater().click();//click on save for later
            Thread.sleep(2000);
            new Assignment().assignToStudent(11,appendChar);//assign assessment to class
            Thread.sleep(2000);

            //create assessment using Create New Assessment using Owner filter as ME
            new Navigator().navigateTo("assignment");
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickByXpath("//div[text()='Create New Assessment']");//click on Create New Assignment
            manageClass.getOwnerMe().click();
            Thread.sleep(2000);
            manageClass.selectButtonWebElement().click();
            Thread.sleep(2000);
            Assert.assertEquals(driver.findElements(By.xpath("//div[@class='as-question-details']")).size(), 1, "Error");
            new Click().clickBycssselector("span.as-questionDetails-clickArrow");
            manageClass.duplicateLinkWebElement().get(0).click();
            Thread.sleep(2000);
            Assert.assertTrue(driver.findElement(By.xpath("//span[@id='footer-notification-text']/label")).getText().contains("has been duplicated"),"Exception in QuestionDuplicate in method questionDuplicate");
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='lsm-createAssignment-total-questions']")).getText().contains("1"),"Exception in QuestionDuplicate in method questionDuplicate");
            driver.findElement(By.xpath("//input[@class='lsm-createAssignment-input-name']")).sendKeys(assessmentname);
            assignments.getButton_review().click();//click on review button
            assignments.getButton_saveForLater().click();


            Thread.sleep(3000);
             //create assessment using existing Assessment using Owner filter as ME
            new Navigator().navigateTo("assignment");
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickByXpath("//div[text()='Use Existing Assessment']");//click on Using Existing assessment
            manageClass.getOwnerMeUsingExisting().click();
            Thread.sleep(2000);
            manageClass.nextArrowWebElement().click();
            Thread.sleep(2000);
            Assert.assertEquals(driver.findElements(By.xpath("//div[@class='as-question-details']")).size(), 1, "Exception in QuestionDuplicate class in method questionSharedAtDifferentLevels");
            manageClass.nextArrowWebElement().click();
            Thread.sleep(2000);
            Assert.assertEquals(manageClass.duplicateLinkWebElement().size(), 0, "Exception in QuestionDuplicate class in method questionSharedAtDifferentLevels");
            manageClass.backArrowWebElement().click();
            Thread.sleep(1000);
            manageClass.getBackLink().click();
            manageClass.customizeButtonWebElement().click();
            manageClass.nextArrowWebElement().click();
            Thread.sleep(1000);
            manageClass.duplicateLinkWebElement().get(0).click();
            Thread.sleep(2000);
            Assert.assertTrue(driver.findElement(By.xpath("//span[@id='footer-notification-text']/label")).getText().contains("has been duplicated"),"Exception in QuestionDuplicate in method questionDuplicate");
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='lsm-createAssignment-total-questions']")).getText().contains("2"),"Exception in QuestionDuplicate in method questionDuplicate");
            driver.findElement(By.xpath("//input[@class='lsm-createAssignment-input-name']")).sendKeys(assessmentname);
            assignments.getButton_review().click();//click on review button
            assignments.getButton_saveForLater().click();

            Thread.sleep(3000);
            //create assessment using existing Assessment using Owner filter as District
            new Navigator().navigateTo("assignment");
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickByXpath("//div[text()='Use Existing Assessment']");//click on Using Existing assessment
            manageClass.getOwnerDistrictUsingExisting().click();
            Thread.sleep(2000);
            manageClass.nextArrowWebElement().click();
            Thread.sleep(2000);
            Assert.assertEquals(driver.findElements(By.xpath("//div[@class='as-question-details']")).size(), 1, "Exception in QuestionDuplicate class in method questionSharedAtDifferentLevels");
            manageClass.nextArrowWebElement().click();
            Thread.sleep(2000);
            Assert.assertEquals(manageClass.duplicateLinkWebElement().size(), 0, "Exception in QuestionDuplicate class in method questionSharedAtDifferentLevels");
            manageClass.backArrowWebElement().click();
            Thread.sleep(1000);
            manageClass.getBackLink().click();
            manageClass.customizeButtonWebElement().click();
            manageClass.nextArrowWebElement().click();
            Thread.sleep(1000);
            manageClass.duplicateLinkWebElement().get(0).click();
            Thread.sleep(2000);
            Assert.assertTrue(driver.findElement(By.xpath("//span[@id='footer-notification-text']/label")).getText().contains("has been duplicated"),"Exception in QuestionDuplicate in method questionDuplicate");
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='lsm-createAssignment-total-questions']")).getText().contains("2"),"Exception in QuestionDuplicate in method questionDuplicate");
            driver.findElement(By.xpath("//input[@class='lsm-createAssignment-input-name']")).sendKeys(assessmentname);
            assignments.getButton_review().click();//click on review button
            assignments.getButton_saveForLater().click();

            Thread.sleep(3000);
            //create assessment using Create New Assessment using Owner filter as DISTRICT
            new Navigator().navigateTo("assignment");
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickByXpath("//div[text()='Create New Assessment']");//click on Create New Assignment
            manageClass.getOwnerMe().click();
            Thread.sleep(2000);
            manageClass.selectButtonWebElement().click();
            Thread.sleep(2000);
            Assert.assertEquals(driver.findElements(By.xpath("//div[@class='as-question-details']")).size(), 1, "Error");
            new Click().clickBycssselector("span.as-questionDetails-clickArrow");
            manageClass.duplicateLinkWebElement().get(0).click();
            Thread.sleep(2000);
            Assert.assertTrue(driver.findElement(By.xpath("//span[@id='footer-notification-text']/label")).getText().contains("has been duplicated"),"Exception in QuestionDuplicate in method questionDuplicate");
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='lsm-createAssignment-total-questions']")).getText().contains("1"),"Exception in QuestionDuplicate in method questionDuplicate");
            driver.findElement(By.xpath("//input[@class='lsm-createAssignment-input-name']")).sendKeys(assessmentname);
            assignments.getButton_review().click();//click on review button
            assignments.getButton_saveForLater().click();
            new Navigator().logout();


            //login with with teacher in same district
            new Login().loginAsInstructor(appendChar1, dataIndex);//Login as a teacher
            //create assessment using existing Assessment using Owner filter as ME
            new Navigator().navigateTo("assignment");
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickByXpath("//div[text()='Use Existing Assessment']");//click on Using Existing assessment
            Thread.sleep(2000);
            manageClass.getOwnerMeUsingExisting().click();
            Thread.sleep(2000);
            Assert.assertTrue(driver.findElement(By.xpath("//span[@class='as-noData-title']")).getText().contains("Assessment Not available"),"Exception in QuestionDuplicate class in method questionSharedAtDifferentLevels");
            new Click().clickBycssselector("span#assessments-cancel-button");
            Thread.sleep(3000);
            //create assessment using existing Assessment using Owner filter as District
            new Navigator().navigateTo("assignment");
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickByXpath("//div[text()='Use Existing Assessment']");//click on Using Existing assessment
            manageClass.getOwnerDistrictUsingExisting().click();
            Thread.sleep(2000);
            manageClass.nextArrowWebElement().click();
            Thread.sleep(2000);
            Assert.assertEquals(driver.findElements(By.xpath("//div[@class='as-question-details']")).size(), 1, "Exception in QuestionDuplicate class in method questionSharedAtDifferentLevels");
            manageClass.nextArrowWebElement().click();
            Thread.sleep(2000);
            Assert.assertEquals(manageClass.duplicateLinkWebElement().size(), 0, "Exception in QuestionDuplicate class in method questionSharedAtDifferentLevels");
            manageClass.backArrowWebElement().click();
            Thread.sleep(1000);
            manageClass.getBackLink().click();
            manageClass.customizeButtonWebElement().click();
            manageClass.nextArrowWebElement().click();
            Thread.sleep(1000);
            manageClass.duplicateLinkWebElement().get(0).click();
            Thread.sleep(2000);
            Assert.assertTrue(driver.findElement(By.xpath("//span[@id='footer-notification-text']/label")).getText().contains("has been duplicated"),"Exception in QuestionDuplicate in method questionDuplicate");
            driver.findElement(By.xpath("//input[@class='lsm-createAssignment-input-name']")).sendKeys(assessmentname);
            assignments.getButton_review().click();//click on review button
            assignments.getButton_saveForLater().click();

            Thread.sleep(3000);
            //create assessment using Create New Assessment using Owner filter as DISTRICT
            new Navigator().navigateTo("assignment");
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickByXpath("//div[text()='Create New Assessment']");//click on Create New Assignment
            manageClass.getDistrictUsers().click();
            Thread.sleep(2000);
//            Assert.assertEquals(driver.findElements(By.xpath("//div[@class='as-question-details']")).size(), 1, "Error");
            manageClass.selectButtonWebElement().click();
            Thread.sleep(1000);
            driver.findElement(By.className("as-grey-checkbox")).click();
            new Click().clickBycssselector("span.as-questionDetails-clickArrow");
            manageClass.duplicateLinkWebElement().get(0).click();
            new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@id='footer-notification-text']/label")));
            Assert.assertTrue(driver.findElement(By.xpath("//span[@id='footer-notification-text']/label")).getText().contains("has been duplicated"),"Exception in QuestionDuplicate in method questionDuplicate");
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='lsm-createAssignment-total-questions']")).getText().contains("2"),"Exception in QuestionDuplicate in method questionDuplicate");
            driver.findElement(By.xpath("//input[@class='lsm-createAssignment-input-name']")).sendKeys(assessmentname);
            assignments.getButton_review().click();//click on review button
            assignments.getButton_saveForLater().click();



            Thread.sleep(3000);
            //create assessment using Create New Assessment using Owner filter as Owner Me
            new Navigator().navigateTo("assignment");
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickByXpath("//div[text()='Create New Assessment']");//click on Create New Assignment
            manageClass.getOwnerMe().click();
            Thread.sleep(2000);
            manageClass.selectButtonWebElement().click();
            Thread.sleep(1000);
            Assert.assertTrue(driver.findElement(By.xpath("//span[@class='as-noData-title']")).getText().contains("Questions Not Available"),"Exception in QuestionDuplicate class in method questionSharedAtDifferentLevels");
            Assert.assertEquals(driver.findElements(By.xpath("//div[@class='as-question-details']")).size(), 0, "Error");
            new Click().clickBycssselector("span.as-create-asignmentHeader-close");
            new Navigator().logout();//logout




            //login with with teacher in same district
            new Login().loginAsInstructor(appendChar2, dataIndex);//Login as a teacher
            //create assessment using existing Assessment using Owner filter as ME
            new Navigator().navigateTo("assignment");
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickByXpath("//div[text()='Use Existing Assessment']");//click on Using Existing assessment
            Thread.sleep(2000);
            manageClass.getOwnerMeUsingExisting().click();
            Thread.sleep(2000);
            Assert.assertTrue(driver.findElement(By.xpath("//span[@class='as-noData-title']")).getText().contains("Assessment Not available"),"Exception in QuestionDuplicate class in method questionSharedAtDifferentLevels");
            new Click().clickBycssselector("span#assessments-cancel-button");
            Thread.sleep(3000);
            //create assessment using existing Assessment using Owner filter as District
            new Navigator().navigateTo("assignment");
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickByXpath("//div[text()='Use Existing Assessment']");//click on Using Existing assessment
            manageClass.getOwnerDistrictUsingExisting().click();
            Thread.sleep(2000);
            Assert.assertTrue(driver.findElement(By.xpath("//span[@class='as-noData-title']")).getText().contains("Assessment Not available"),"Exception in QuestionDuplicate class in method questionSharedAtDifferentLevels");
            new Click().clickBycssselector("span#assessments-cancel-button");

            Thread.sleep(3000);
            //create assessment using Create New Assessment using Owner filter as DISTRICT
            new Navigator().navigateTo("assignment");
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickByXpath("//div[text()='Create New Assessment']");//click on Create New Assignment
            manageClass.getDistrictUsers().click();
            Thread.sleep(2000);
            manageClass.selectButtonWebElement().click();
            Thread.sleep(1000);
            Assert.assertTrue(driver.findElement(By.xpath("//span[@class='as-noData-title']")).getText().contains("Questions Not Available"),"Exception in QuestionDuplicate class in method questionSharedAtDifferentLevels");
            Assert.assertEquals(driver.findElements(By.xpath("//div[@class='as-question-details']")).size(), 0, "Error");
            new Click().clickBycssselector("span.as-create-asignmentHeader-close");



            Thread.sleep(3000);
            //create assessment using Create New Assessment using Owner filter as Owner Me
            new Navigator().navigateTo("assignment");
            new Click().clickBycssselector("span.instructor-assignment-new-txt");//click on +New Assignment button
            new Click().clickByXpath("//div[text()='Create New Assessment']");//click on Create New Assignment
            manageClass.getOwnerMe().click();
            Thread.sleep(2000);
            manageClass.selectButtonWebElement().click();
            Thread.sleep(1000);
            Assert.assertTrue(driver.findElement(By.xpath("//span[@class='as-noData-title']")).getText().contains("Questions Not Available"),"Exception in QuestionDuplicate class in method questionSharedAtDifferentLevels");
            Assert.assertEquals(driver.findElements(By.xpath("//div[@class='as-question-details']")).size(), 0, "Error");
            new Click().clickBycssselector("span.as-create-asignmentHeader-close");
















        } catch (Exception e) {
            Assert.fail("Exception in QuestionDuplicate Class in method questionSharedAtDifferentLevels", e);
        }

    }
    public void createWithOnlyName(String appendChar,int dataIndex)
    {
        try
        {
            String appendCharacterBuild=System.getProperty("UCHAR");
            boolean districtFound = false;
            String districtName=ReadTestData.readDataByTagName("", "districtName", Integer.toString(dataIndex));
            String  schoolName = new Exception().getStackTrace()[1].getMethodName()+"school"+appendChar+appendCharacterBuild;
            new Click().clickByclassname("as-add-link"); //Click on add school link after instructor logs in
            new TextSend().textsendbyid(schoolName,"school-name");//Enter school name in school name field

            if(districtName!=null) {
                new TextSend().textsendbyclass(districtName, "maininput"); //adding first three characters to the District
                Thread.sleep(2000);
                List<WebElement> suggestedNames = driver.findElements(By.xpath("(//div[@class='facebook-auto'])//li"));
                for (WebElement suggestedName : suggestedNames) {
                    if (suggestedName.getText().trim().equals(districtName)) {
                        suggestedName.click();
                        districtFound = true;
                        break;
                    }
                }

            }

            driver.findElement(By.cssSelector("div[class='as-search-blue-btn as-add-save-btn']")).click(); //clicking on save button
            Thread.sleep(3000);
            closeHelp();

        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper create in class School",e);
        }
    }


    @Test(priority = 3)
    public void questionDuplicateAllTypes()
    {
        try {
            String appendChar = "a7";
            int dataIndex = 15;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            Assignments assignments=PageFactory.initElements(driver,Assignments.class);
            AssessmentQuestionsListPage assessmentQuestionsListPage=PageFactory.initElements(driver,AssessmentQuestionsListPage.class);
            ManageClass manageClass= PageFactory.initElements(driver, ManageClass.class);
            String classCode=null;


            //************Duplicate True False question***********//
            new SignUp().teacher(appendChar,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            createWithOnlyName(appendChar, dataIndex);//create school
            classCode=new Classes().createClass(appendChar, dataIndex);//create class
            new Navigator().logout();//logout new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar, classCode, 0);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"truefalse");//create assessment with one question
            duplicateQuestion();
            Thread.sleep(3000);
            new Assignment().assignToStudent(dataIndex,appendChar);//assign assessment to class
            Thread.sleep(3000);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Graded"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==1,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            new Click().clickByXpath("//div[contains(@class,'as-question-score-wrapper')]/div/div");
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            manageClass.getNextArrowPerformanceView().click();
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            new Navigator().logout();






            //************Duplicate Multiple Choice question***********//*
            new SignUp().teacher(appendChar+1,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar+1, dataIndex);//Login as a teacher
            createWithOnlyName(appendChar+1, dataIndex);//create school
            classCode=new Classes().createClass(appendChar+1, dataIndex);//create class
            new Navigator().logout();//logout new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar+1, classCode, 0);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar+1, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"multiplechoice");//create assessment with one question
            duplicateQuestion();
            new Assignment().assignToStudent(dataIndex,appendChar+1);//assign assessment to class
            Thread.sleep(3000);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar+1,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar+1, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Graded"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==1,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            new Click().clickByXpath("//div[contains(@class,'as-question-score-wrapper')]/div/div");
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            manageClass.getNextArrowPerformanceView().click();
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            new Navigator().logout();


            //************Duplicate Multiple Selection question***********//*/
            new SignUp().teacher(appendChar+2,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar+2, dataIndex);//Login as a teacher
            createWithOnlyName(appendChar+2, dataIndex);//create school
            classCode=new Classes().createClass(appendChar+2, dataIndex);//create class
            new Navigator().logout();//logout new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar+2, classCode, 0);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar+2, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"multipleselection");//create assessment with one question
            duplicateQuestion();
            new Assignment().assignToStudent(dataIndex,appendChar+2);//assign assessment to class
            Thread.sleep(3000);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar+2,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar+2, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Graded"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==1,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            new Click().clickByXpath("//div[contains(@class,'as-question-score-wrapper')]/div/div");
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            manageClass.getNextArrowPerformanceView().click();
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            new Navigator().logout();


           //************Duplicate Text Entry question***********//**//*//*
            new SignUp().teacher(appendChar+3,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar+3, dataIndex);//Login as a teacher
            createWithOnlyName(appendChar+3, dataIndex);//create school
            classCode=new Classes().createClass(appendChar+3, dataIndex);//create class
            new Navigator().logout();//logout new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar+3, classCode, 0);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar+3, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"textentry");//create assessment with one question
            duplicateQuestion();
            new Assignment().assignToStudent(dataIndex,appendChar+3);//assign assessment to class
            Thread.sleep(3000);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar+3,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar+3, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Graded"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==1,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            new Click().clickByXpath("//div[contains(@class,'as-question-score-wrapper')]/div/div");
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            manageClass.getNextArrowPerformanceView().click();
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            new Navigator().logout();


            //************Duplicate Text Dropdown question***********//**//*//*
            new SignUp().teacher(appendChar+4,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar+4, dataIndex);//Login as a teacher
            createWithOnlyName(appendChar+4, dataIndex);//create school
            classCode=new Classes().createClass(appendChar+4, dataIndex);//create class
            new Navigator().logout();//logout new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar+4, classCode, 0);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar+4, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"textdropdown");//create assessment with one question
            duplicateQuestion();
            new Assignment().assignToStudent(dataIndex,appendChar+4);//assign assessment to class
            Thread.sleep(3000);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar+4,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar+4, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Graded"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==1,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            new Click().clickByXpath("//div[contains(@class,'as-question-score-wrapper')]/div/div");
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            manageClass.getNextArrowPerformanceView().click();
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            new Navigator().logout();

            //************Duplicate Numeric Entry with Units question***********//*/
            new SignUp().teacher(appendChar+5,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar+5, dataIndex);//Login as a teacher
            createWithOnlyName(appendChar+5, dataIndex);//create school
            classCode=new Classes().createClass(appendChar+5, dataIndex);//create class
            new Navigator().logout();//logout new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar+5, classCode, 0);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar+5, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"numericentrywithunits");//create assessment with one question
            duplicateQuestion();
            new Assignment().assignToStudent(dataIndex,appendChar+5);//assign assessment to class
            Thread.sleep(3000);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar+5,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar+5, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Graded"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==1,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            new Click().clickByXpath("//div[contains(@class,'as-question-score-wrapper')]/div/div");
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            manageClass.getNextArrowPerformanceView().click();
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            new Navigator().logout();

            //************Duplicate Advanced Numeric question***********//**//*//*
            new SignUp().teacher(appendChar+6,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar+6, dataIndex);//Login as a teacher
            createWithOnlyName(appendChar+6, dataIndex);//create school
            classCode=new Classes().createClass(appendChar+6, dataIndex);//create class
            new Navigator().logout();//logout new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar+6, classCode, 0);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar+6, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"advancednumeric");//create assessment with one question
            duplicateQuestion();
            new Assignment().assignToStudent(dataIndex,appendChar+6);//assign assessment to class
            Thread.sleep(3000);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar+6,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar+6, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Graded"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==1,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            new Click().clickByXpath("//div[contains(@class,'as-question-score-wrapper')]/div/div");
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            manageClass.getNextArrowPerformanceView().click();
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            new Navigator().logout();

            //************Duplicate Expression evaluator question***********//**//*//*
            new SignUp().teacher(appendChar+7,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar+7, dataIndex);//Login as a teacher
            createWithOnlyName(appendChar+7, dataIndex);//create school
            classCode=new Classes().createClass(appendChar+7, dataIndex);//create class
            new Navigator().logout();//logout new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar+7, classCode, 0);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar+7, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"expressionevaluator");//create assessment with one question
            duplicateQuestion();
            new Assignment().assignToStudent(dataIndex,appendChar+7);//assign assessment to class
            Thread.sleep(3000);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar+7,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar+7, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Graded"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==1,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            new Click().clickByXpath("//div[contains(@class,'as-question-score-wrapper')]/div/div");
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            manageClass.getNextArrowPerformanceView().click();
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            new Navigator().logout();

            //************Duplicate Match the following question***********//**//*//*
            new SignUp().teacher(appendChar+8,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar+8, dataIndex);//Login as a teacher
            createWithOnlyName(appendChar+8, dataIndex);//create school
            classCode=new Classes().createClass(appendChar+8, dataIndex);//create class
            new Navigator().logout();//logout new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar+8, classCode, 0);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar+8, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"matchthefollowing");//create assessment with one question
            duplicateQuestion();
            new Assignment().assignToStudent(dataIndex,appendChar+8);//assign assessment to class
            Thread.sleep(3000);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar+8,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar+8, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Graded"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==1,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            new Click().clickByXpath("//div[contains(@class,'as-question-score-wrapper')]/div/div");
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            manageClass.getNextArrowPerformanceView().click();
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            new Navigator().logout();


            //************Duplicate Drag and drop question***********//*/
            new SignUp().teacher(appendChar+9,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar+9, dataIndex);//Login as a teacher
            createWithOnlyName(appendChar+9, dataIndex);//create school
            classCode=new Classes().createClass(appendChar+9, dataIndex);//create class
            new Navigator().logout();//logout new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar+9, classCode, 0);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar+9, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"draganddrop");//create assessment with one question
            duplicateQuestion();
            new Assignment().assignToStudent(dataIndex,appendChar+9);//assign assessment to class
            Thread.sleep(3000);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar+9,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar+9, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Graded"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==1,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            new Click().clickByXpath("//div[contains(@class,'as-question-score-wrapper')]/div/div");
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            manageClass.getNextArrowPerformanceView().click();
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            new Navigator().logout();


            //************Duplicate Cloze Formula question***********//*/
            new SignUp().teacher(appendChar+10,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar+10, dataIndex);//Login as a teacher
            createWithOnlyName(appendChar+10, dataIndex);//create school
            classCode=new Classes().createClass(appendChar+10, dataIndex);//create class
            new Navigator().logout();//logout new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar+10, classCode, 0);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar+10, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"clozeformula");//create assessment with one question
            duplicateQuestion();
            new Assignment().assignToStudent(dataIndex,appendChar+10);//assign assessment to class
            Thread.sleep(3000);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar+10,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar+10, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Graded"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==1,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            new Click().clickByXpath("//div[contains(@class,'as-question-score-wrapper')]/div/div");
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            manageClass.getNextArrowPerformanceView().click();
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            new Navigator().logout();


            //************Duplicate Graph Plotter question***********//*/
            new SignUp().teacher(appendChar+11,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar+11, dataIndex);//Login as a teacher

            createWithOnlyName(appendChar+11, dataIndex);//create school
            classCode=new Classes().createClass(appendChar+11, dataIndex);//create class
            new Navigator().logout();//logout new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar+11, classCode, 0);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar+11, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"graphplotter");//create assessment with one question
            duplicateQuestion();
            new Assignment().assignToStudent(dataIndex,appendChar+11);//assign assessment to class
            Thread.sleep(3000);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar+11,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar+11, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Graded"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==1,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
           new Click().clickByXpath("//div[contains(@class,'as-question-score-wrapper')]/div/div");
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            manageClass.getNextArrowPerformanceView().click();
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            new Navigator().logout();



            //************Duplicate Close Matrix question***********//*/
            new SignUp().teacher(appendChar+12,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar+12, dataIndex);//Login as a teacher
            createWithOnlyName(appendChar+12, dataIndex);//create school
            classCode=new Classes().createClass(appendChar+12, dataIndex);//create class
            new Navigator().logout();//logout new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar+12, classCode, 0);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar+12, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"clozematrix");//create assessment with one question
            duplicateQuestion();
            new Assignment().assignToStudent(dataIndex,appendChar+12);//assign assessment to class
            Thread.sleep(3000);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar+12,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar+12, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Graded"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==1,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
           new Click().clickByXpath("//div[contains(@class,'as-question-score-wrapper')]/div/div[1]");

            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            manageClass.getNextArrowPerformanceView().click();
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            new Navigator().logout();



            //************Duplicate Resequence question***********
            new SignUp().teacher(appendChar+13,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar+13, dataIndex);//Login as a teacher
            createWithOnlyName(appendChar+13, dataIndex);//create school
            classCode=new Classes().createClass(appendChar+13, dataIndex);//create class
            new Navigator().logout();//logout new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar+13, classCode, 0);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar+13, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"resequence");//create assessment with one question
            duplicateQuestion();
            new Assignment().assignToStudent(dataIndex,appendChar+13);//assign assessment to class
            Thread.sleep(3000);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar+13,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar+13, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Graded"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==1,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
           new Click().clickByXpath("//div[contains(@class,'as-question-score-wrapper')]/div/div[1]");
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            manageClass.getNextArrowPerformanceView().click();
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            new Navigator().logout();



            //************Duplicate Essay question***********/
            new SignUp().teacher(appendChar+14,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar+14, dataIndex);//Login as a teacher
            createWithOnlyName(appendChar+14, dataIndex);//create school
            classCode=new Classes().createClass(appendChar+14, dataIndex);//create class
            new Navigator().logout();//logout new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar+14, classCode, 0);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar+14, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"essay");//create assessment with one question
            duplicateQuestion();
            new Assignment().assignToStudent(dataIndex,appendChar+14);//assign assessment to class
            Thread.sleep(3000);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar+14,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar+14, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Grading in Progress"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==1,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
           new Click().clickByXpath("//div[contains(@class,'as-question-score-wrapper')]/div/div[1]");
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            manageClass.getNextArrowPerformanceView().click();
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            new Navigator().logout();


            //************Duplicate Label an Image Text question***********//*/
            new SignUp().teacher(appendChar+15,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar+15, dataIndex);//Login as a teacher
            createWithOnlyName(appendChar+15, dataIndex);//create school
            classCode=new Classes().createClass(appendChar+15, dataIndex);//create class
            new Navigator().logout();//logout new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar+15, classCode, 0);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar+15, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"labelanimagetext");//create assessment with one question
            duplicateQuestion();
            new Assignment().assignToStudent(dataIndex,appendChar+15);//assign assessment to class
            Thread.sleep(3000);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar+15,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar+15, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Graded"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==1,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
           new Click().clickByXpath("//div[contains(@class,'as-question-score-wrapper')]/div/div[1]");
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            manageClass.getNextArrowPerformanceView().click();
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            new Navigator().logout();



            //************Duplicate Label an Image Dropdown question***********//**//*//*
            new SignUp().teacher(appendChar+16,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar+16, dataIndex);//Login as a teacher
            createWithOnlyName(appendChar+16, dataIndex);//create school
            classCode=new Classes().createClass(appendChar+16, dataIndex);//create class
            new Navigator().logout();//logout new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar+16, classCode, 0);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar+16, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"labelanimagedropdown");//create assessment with one question
            duplicateQuestion();
            new Assignment().assignToStudent(dataIndex,appendChar+16);//assign assessment to class
            Thread.sleep(3000);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar+16,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar+16, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Graded"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==1,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
           new Click().clickByXpath("//div[contains(@class,'as-question-score-wrapper')]/div/div[1]");
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            manageClass.getNextArrowPerformanceView().click();
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            new Navigator().logout();



            //************Duplicate number line question***********//**//*//*
            new SignUp().teacher(appendChar+17,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar+17, dataIndex);//Login as a teacher
            createWithOnlyName(appendChar+17, dataIndex);//create school
            classCode=new Classes().createClass(appendChar+17, dataIndex);//create class
            new Navigator().logout();//logout new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar+17, classCode, 0);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar+17, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"numberline");//create assessment with one question
            duplicateQuestion();
            new Assignment().assignToStudent(dataIndex,appendChar+17);//assign assessment to class
            Thread.sleep(3000);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar+17,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar+17, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Graded"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==1,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
           new Click().clickByXpath("//div[contains(@class,'as-question-score-wrapper')]/div/div[1]");
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            manageClass.getNextArrowPerformanceView().click();
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            new Navigator().logout();



            //************Duplicate Classification question***********//**//*//*
            new SignUp().teacher(appendChar+18,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar+18, dataIndex);//Login as a teacher
            createWithOnlyName(appendChar+18, dataIndex);//create school
            classCode=new Classes().createClass(appendChar+18, dataIndex);//create class
            new Navigator().logout();//logout new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar+18, classCode, 0);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar+18, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"classification");//create assessment with one question
            duplicateQuestion();
            new Assignment().assignToStudent(dataIndex,appendChar+18);//assign assessment to class
            Thread.sleep(3000);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar+18,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar+18, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Graded"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==1,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
           new Click().clickByXpath("//div[contains(@class,'as-question-score-wrapper')]/div/div[1]");
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            manageClass.getNextArrowPerformanceView().click();
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            new Navigator().logout();


            //************Duplicate Sentence response question***********//**//*//*
            new SignUp().teacher(appendChar+19,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar+19, dataIndex);//Login as a teacher
            createWithOnlyName(appendChar+19, dataIndex);//create school
            classCode=new Classes().createClass(appendChar+19, dataIndex);//create class
            new Navigator().logout();//logout new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar+19, classCode, 0);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar+19, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"sentenceresponse");//create assessment with one question
            duplicateQuestion();
            new Assignment().assignToStudent(dataIndex,appendChar+19);//assign assessment to class
            Thread.sleep(3000);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar+19,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar+19, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Graded"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==1,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
           new Click().clickByXpath("//div[contains(@class,'as-question-score-wrapper')]/div/div[1]");
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            manageClass.getNextArrowPerformanceView().click();
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            new Navigator().logout();



            //************Duplicate Matching tables question***********//**//*//*
            new SignUp().teacher(appendChar+20,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar+20, dataIndex);//Login as a teacher
            createWithOnlyName(appendChar+20, dataIndex);//create school
            classCode=new Classes().createClass(appendChar+20, dataIndex);//create class
            new Navigator().logout();//logout new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar+20, classCode, 0);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar+20, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"matchingtables");//create assessment with one question
            duplicateQuestion();
            new Assignment().assignToStudent(dataIndex,appendChar+20);//assign assessment to class
            Thread.sleep(3000);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar+20,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar+20, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Graded"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==1,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
           new Click().clickByXpath("//div[contains(@class,'as-question-score-wrapper')]/div/div[1]");
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            manageClass.getNextArrowPerformanceView().click();
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            new Navigator().logout();



            //************Duplicate Passage question***********//**//*//*
            new SignUp().teacher(appendChar+21,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar+21, dataIndex);//Login as a teacher
            createWithOnlyName(appendChar+21, dataIndex);//create school
            classCode=new Classes().createClass(appendChar+21, dataIndex);//create class
            new Navigator().logout();//logout new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar+21, classCode, 0);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar+21, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"passage");//create assessment with one question
            duplicateQuestion();
            new Assignment().assignToStudent(dataIndex,appendChar+21);//assign assessment to class
            Thread.sleep(3000);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar+21,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar+21, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Graded"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==1,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
           new Click().clickByXpath("//div[contains(@class,'as-question-score-wrapper')]/div/div[1]");
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            manageClass.getNextArrowPerformanceView().click();
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            new Navigator().logout();



            //************Duplicate Line Plot question***********//*/
            new SignUp().teacher(appendChar+22,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar+22, dataIndex);//Login as a teacher
            createWithOnlyName(appendChar+22, dataIndex);//create school
            classCode=new Classes().createClass(appendChar+22, dataIndex);//create class
            new Navigator().logout();//logout new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar+22, classCode, 0);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar+22, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"lineplot");//create assessment with one question
            duplicateQuestion();
            new Assignment().assignToStudent(dataIndex,appendChar+22);//assign assessment to class
            Thread.sleep(3000);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar+22,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar+22, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Graded"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==1,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
           new Click().clickByXpath("//div[contains(@class,'as-question-score-wrapper')]/div/div[1]");
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            manageClass.getNextArrowPerformanceView().click();
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            new Navigator().logout();


            //************Duplicate True False question***********//*/
            new SignUp().teacher(appendChar+23,dataIndex);//SignUp as a teacher
            new Login().loginAsInstructor(appendChar+23, dataIndex);//Login as a teacher
            createWithOnlyName(appendChar+23, dataIndex);//create school
            classCode=new Classes().createClass(appendChar+23, dataIndex);//create class
            new Navigator().logout();//logout new Navigator().logout();//logout
            new SignUp().studentDirectRegister(appendChar+23, classCode, 0);//create student
            new Navigator().logout();//logout
            new Login().loginAsInstructor(appendChar+23, dataIndex);//Login as a teacher
            new Assignment().create(dataIndex,"rangeplotter");//create assessment with one question
            duplicateQuestion();
            new Assignment().assignToStudent(dataIndex,appendChar+23);//assign assessment to class
            Thread.sleep(3000);
            new Navigator().logout();
            new Login().loginAsStudent(appendChar+23,dataIndex);
            new Assignment().submitAssignment(dataIndex);
            new Navigator().logout();
            new Login().loginAsInstructor(appendChar+23, dataIndex);//Login as a teacher
            new Navigator().navigateTo("assignment");
            Assert.assertTrue(manageClass.getAssignmentStatus().get(0).getText().contains("Graded"), "Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
            manageClass.getViewResponse().click();
            Thread.sleep(1000);
            Assert.assertTrue(manageClass.getStudentListViewResponse().size()==1,"Exception in DeleteStudentGradable Class in method deleteStudentGradableScheduledAfterAssessment");
           new Click().clickByXpath("//div[contains(@class,'as-question-score-wrapper')]/div/div[1]");
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            manageClass.getNextArrowPerformanceView().click();
            Thread.sleep(2000);
            Assert.assertTrue(manageClass.getTloText().getText().contains("1.OA - Operations & Algebraic Thinking"),"Exceptionin class QuestionDuplicate in Method questionDuplicateAllTypes");
            new Navigator().logout();

        }
        catch (Exception e)
        {
            Assert.fail("Exception in QuestionDuplicate Class in method questionDuplicateAllTypes",e);
        }

    }

    public void duplicateQuestion()
    {
        try {
            ManageClass manageClass=PageFactory.initElements(driver,ManageClass.class);
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);
            AssessmentQuestionsListPage assessmentQuestionsListPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            assignments.getButton_review().click();//click on review button
            assessmentQuestionsListPage.getButton_saveForLater().click();//click on save for later
            Thread.sleep(2000);
            new Navigator().navigateTo("assignment");
            driver.findElement(By.xpath("//span[@class='as-assignment-draftTxt']")).click();
            Thread.sleep(1000);
            new Click().clickBycssselector("span.as-edit");
            new Click().clickBycssselector("span.as-questionDetails-clickArrow");
            manageClass.duplicateLinkWebElement().get(0).click();
            Thread.sleep(2000);
            Assert.assertTrue(driver.findElement(By.xpath("//span[@id='footer-notification-text']/label")).getText().contains("has been duplicated"),"Exception in QuestionDuplicate in method questionDuplicate");
            Assert.assertTrue(driver.findElement(By.xpath("//div[@class='lsm-createAssignment-total-questions']")).getText().contains("2"),"Exception in QuestionDuplicate in method questionDuplicate");
            assignments.getButton_review().click();//click on review button
            assignments.getButton_saveForLater().click();
            Thread.sleep(3000);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in class QuestionDuplicate in method duplicateQuestion",e);
        }

    }
}
