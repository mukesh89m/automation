package com.snapwiz.selenium.tests.staf.edulastic.testcases.e9.editassessesment;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentDetailsPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentLibrary;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssignmentReview;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.Assign;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Shashank on 18-09-2015.
 */
public class EditAssessment extends Driver{

    @Test(priority = 1,enabled = true)
    public void editAssessmentSharingPrivate()

    {
        int dataIndex = 1;
        String appendChar = "a15";
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver,AssessmentDetailsPage.class);
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        Assign assign=PageFactory.initElements(driver,Assign.class);
        try {

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Assignment().create(dataIndex,"truefalse");
            new Assignment().assignToStudent(dataIndex,appendChar);
            new Assignment().editPublishedAssessment(dataIndex,appendChar);
            new Click().clickByid("assessments-back-button");
            new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']");//click on Create link
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys(assessmentname);//give the assignment name
            new QuestionCreate().multipleChoice(dataIndex);
            new Navigator().navigateTo("myAssessments");//Navigate to my assignment page
            new Click().clickByXpath("//input[@id='draft']/following-sibling::ins");
            int index = 0;
            List<WebElement> allAssessment = driver.findElements(By.cssSelector("div[class='as-label ellipsis']"));//as per E8
            for (WebElement assessment : allAssessment)
            {
                if (assessment.getText().equals(assessmentname))
                    break;
                else
                    index++;

            }
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", index);//click on Assignment

            //verify total  number question added
            Assert.assertEquals(assessmentDetailsPage.questionName.size(),2,"All question not present on page");
            //verify text present in question
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");
            assessmentDetailsPage.editAssesssment.click();
            new Click().clickByid("assessments-use-button");//click on next button to assign
            assessmentReview.nextButton.click();
            Assert.assertEquals(assign.totalPoints.getText(),"2","Total point is not matching as expected");
            //verify the sharing level should be private
            Assert.assertTrue(assign.getRadioButton_private().getAttribute("class").contains("checked"), "Sharing level is not private");
            //click on assign button
            assign.saveAssessmentAndUseLater.click();

            assign.getButton_assign().click();
            Thread.sleep(2000);
            new Navigator().navigateTo("assessmentLibrary");
            //select sharing level as 'Private'
            assessmentLibrary.sharingLevelPrivate.click();
            Thread.sleep(3000);
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", 0);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");





          }
        catch (Exception e)
        {
            Assert.fail("Exception in editAssessmentSharingPrivate method in class EditAssessment",e);
        }
    }

    @Test(priority = 2,enabled = true)
    public void editAssessmentSharingSchool()

    {
        int dataIndex = 2;
        String appendChar = "a8";
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        Assign assign=PageFactory.initElements(driver,Assign.class);
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver,AssessmentDetailsPage.class);
        try {

             new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            //new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Assignment().create(dataIndex,"truefalse");
            new Assignment().assignToStudent(dataIndex,appendChar);
            new Assignment().editPublishedAssessment(dataIndex,appendChar);
            new Click().clickByid("assessments-back-button");
            new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']");//click on Create link
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys(assessmentname);//give the assignment name
            new QuestionCreate().multipleChoice(dataIndex);
            new Navigator().navigateTo("myAssessments");//Navigate to my assignment page
            new Click().clickByXpath("//input[@id='draft']/following-sibling::ins");
            int index = 0;
            List<WebElement> allAssessment = driver.findElements(By.cssSelector("div[class='as-label ellipsis']"));//as per E8
            for (WebElement assessment : allAssessment)
            {
                if (assessment.getText().equals(assessmentname))
                    break;
                else
                    index++;

            }
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", index);//click on Assignment

            //verify total  number question added
            Assert.assertEquals(assessmentDetailsPage.questionName.size(),2,"All question not present on page");
            //verify text present in question
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");



            assessmentDetailsPage.editAssesssment.click();
            new Click().clickByid("assessments-use-button");//click on next button to assign
            assessmentReview.nextButton.click();
            Assert.assertEquals(assign.totalPoints.getText(),"2","Total point is not matching as expected");
            //verify the sharing level should be private
            Assert.assertTrue(assign.getRadioButton_school().getAttribute("class").contains("checked"), "Sharing level is not private");
            //click on assign button
            assign.saveAssessmentAndUseLater.click();

            assign.getButton_assign().click();
            Thread.sleep(2000);
            new Navigator().navigateTo("myAssessments");
            new Click().clickByXpath("//input[@id='published']/following-sibling::ins");
            Thread.sleep(3000);
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", 0);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in editAssessmentSharingPrivate method in class EditAssessment");
        }
    }


    @Test(priority = 3,enabled = true)
    public void editAssessmentSharingDistrict()

    {
        int dataIndex =3;
        String appendChar = "a8";
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        Assign assign=PageFactory.initElements(driver,Assign.class);
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver,AssessmentDetailsPage.class);
        try {

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
           // new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Assignment().create(dataIndex,"truefalse");
            new Assignment().assignToStudent(dataIndex,appendChar);
            new Assignment().editPublishedAssessment(dataIndex,appendChar);
            new Click().clickByid("assessments-back-button");
            new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']");//click on Create link
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys(assessmentname);//give the assignment name
            new QuestionCreate().multipleChoice(dataIndex);
            new Navigator().navigateTo("myAssessments");//Navigate to my assignment page
            new Click().clickByXpath("//input[@id='draft']/following-sibling::ins");
            int index = 0;
            List<WebElement> allAssessment = driver.findElements(By.cssSelector("div[class='as-label ellipsis']"));//as per E8
            for (WebElement assessment : allAssessment)
            {
                if (assessment.getText().equals(assessmentname))
                    break;
                else
                    index++;

            }
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", index);//click on Assignment

            //verify total  number question added
            Assert.assertEquals(assessmentDetailsPage.questionName.size(),2,"All question not present on page");
            //verify text present in question
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");

            assessmentDetailsPage.editAssesssment.click();
            new Click().clickByid("assessments-use-button");//click on next button to assign
            assessmentReview.nextButton.click();
            Assert.assertEquals(assign.totalPoints.getText(),"2","Total point is not matching as expected");
            //verify the sharing level should be private
            Assert.assertTrue(assign.getRadioButton_district().getAttribute("class").contains("checked"), "Sharing level is not private");
            //click on assign button
            assign.saveAssessmentAndUseLater.click();
            assign.getButton_assign().click();
            Thread.sleep(2000);
            new Navigator().navigateTo("myAssessments");
            //select sharing level as 'Private'
            new Click().clickByXpath("//input[@id='published']/following-sibling::ins");
            Thread.sleep(3000);
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", 0);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in editAssessmentSharingPrivate method in class EditAssessment");
        }
    }


    @Test(priority = 4,enabled = true)
    public void editAssessmentSharingPublic()

    {
        int dataIndex = 4;
        String appendChar = "a8";
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        Assign assign=PageFactory.initElements(driver,Assign.class);
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver,AssessmentDetailsPage.class);
        try {

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
           // new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Assignment().create(dataIndex,"truefalse");
            new Assignment().assignToStudent(dataIndex,appendChar);
            new Assignment().editPublishedAssessment(dataIndex,appendChar);
            new Click().clickByid("assessments-back-button");
            new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']");//click on Create link
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys(assessmentname);//give the assignment name
            new QuestionCreate().multipleChoice(dataIndex);
            new Navigator().navigateTo("myAssessments");//Navigate to my assignment page
            new Click().clickByXpath("//input[@id='draft']/following-sibling::ins");
            int index = 0;
            List<WebElement> allAssessment = driver.findElements(By.cssSelector("div[class='as-label ellipsis']"));//as per E8
            for (WebElement assessment : allAssessment)
            {
                if (assessment.getText().equals(assessmentname))
                    break;
                else
                    index++;

            }
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", index);//click on Assignment

            //verify total  number question added
            Assert.assertEquals(assessmentDetailsPage.questionName.size(),2,"All question not present on page");
            //verify text present in question
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");
            assessmentDetailsPage.editAssesssment.click();
            new Click().clickByid("assessments-use-button");//click on next button to assign
            assessmentReview.nextButton.click();
            Assert.assertEquals(assign.totalPoints.getText(),"2","Total point is not matching as expected");
            //verify the sharing level should be private
            Assert.assertTrue(assign.getRadioButton_public().getAttribute("class").contains("checked"), "Sharing level is not private");
            //click on assign button
            assign.saveAssessmentAndUseLater.click();
            assign.getButton_assign().click();
            Thread.sleep(2000);
            new Navigator().navigateTo("myAssessments");
            //select sharing level as 'Private'
            new Click().clickByXpath("//input[@id='published']/following-sibling::ins");
            Thread.sleep(3000);
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", 0);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in editAssessmentSharingPrivate method in class EditAssessment");
        }
    }
    @Test(priority = 5,enabled = true)
    public void editAssignmentSharingPrivate()

    {
        int dataIndex = 5;
        String appendChar = "a18";
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver,AssessmentDetailsPage.class);
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        Assign assign=PageFactory.initElements(driver,Assign.class);
        try {

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            // new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Assignment().create(dataIndex,"truefalse");
            new Assignment().assignToStudent(dataIndex,appendChar);
            new Assignment().editPublishedAssignment(dataIndex,appendChar);
            new Click().clickByid("assessments-back-button");
            new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']");//click on Create link
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys(assessmentname);//give the assignment name
            new QuestionCreate().multipleChoice(dataIndex);
            new Navigator().navigateTo("myAssessments");//Navigate to my assignment page
            new Click().clickByXpath("//input[@id='draft']/following-sibling::ins");
            int index = 0;
            List<WebElement> allAssessment = driver.findElements(By.cssSelector("div[class='as-label ellipsis']"));//as per E8
            for (WebElement assessment : allAssessment)
            {
                if (assessment.getText().equals(assessmentname))
                    break;
                else
                    index++;

            }
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", index);//click on Assignment

            //verify total  number question added
            Assert.assertEquals(assessmentDetailsPage.questionName.size(),2,"All question not present on page");
            //verify text present in question
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");
            assessmentDetailsPage.editAssesssment.click();
            new Click().clickByid("assessments-use-button");//click on next button to assign
            assessmentReview.nextButton.click();
            Assert.assertEquals(assign.totalPoints.getText(),"2","Total point is not matching as expected");
            //verify the sharing level should be private
            Assert.assertTrue(assign.getRadioButton_private().getAttribute("class").contains("checked"), "Sharing level is not private");
            //click on assign button
            assign.saveAssessmentAndUseLater.click();

            assign.getButton_assign().click();
            Thread.sleep(2000);
            new Navigator().navigateTo("assessmentLibrary");
            //select sharing level as 'Private'
            assessmentLibrary.sharingLevelPrivate.click();
            Thread.sleep(3000);
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", 0);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");
            //verify usage count
            Assert.assertEquals(assessmentDetailsPage.usageCountOfAssignment.getText(),"1 time(s)","Usage count not matches is not same");



        }
        catch (Exception e)
        {
            Assert.fail("Exception in editAssignmentSharingPrivate method in class EditAssignment",e);
        }
    }

    @Test(priority = 6,enabled = true)
    public void editAssignmentSharingSchool()

    {
        int dataIndex = 6;
        String appendChar = "a96";
        String appendChar1 = "a16";

        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        Assign assign=PageFactory.initElements(driver,Assign.class);
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver,AssessmentDetailsPage.class);
        SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);
        try {

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            //new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
          String zipcode=  new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Assignment().create(dataIndex,"truefalse");
            new Assignment().assignToStudent(dataIndex,appendChar);
            new Assignment().editPublishedAssignment(dataIndex,appendChar);
            new Click().clickByid("assessments-back-button");
            new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']");//click on Create link
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys(assessmentname);//give the assignment name
            new QuestionCreate().multipleChoice(dataIndex);
            new Navigator().navigateTo("myAssessments");//Navigate to my assignment page
            new Click().clickByXpath("//input[@id='draft']/following-sibling::ins");
            int index = 0;
            List<WebElement> allAssessment = driver.findElements(By.cssSelector("div[class='as-label ellipsis']"));//as per E8
            for (WebElement assessment : allAssessment)
            {
                if (assessment.getText().equals(assessmentname))
                    break;
                else
                    index++;

            }
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", index);//click on Assignment

            //verify total  number question added
            Assert.assertEquals(assessmentDetailsPage.questionName.size(),2,"All question not present on page");
            //verify text present in question
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");



            assessmentDetailsPage.editAssesssment.click();
            new Click().clickByid("assessments-use-button");//click on next button to assign
            assessmentReview.nextButton.click();
            Assert.assertEquals(assign.totalPoints.getText(),"2","Total point is not matching as expected");
            //verify the sharing level should be private
            Assert.assertTrue(assign.getRadioButton_school().getAttribute("class").contains("checked"), "Sharing level is not private");
            //click on assign button
            assign.saveAssessmentAndUseLater.click();

            assign.getButton_assign().click();
            Thread.sleep(2000);
            new Navigator().navigateTo("myAssessments");
            new Click().clickByXpath("//input[@id='published']/following-sibling::ins");
            Thread.sleep(3000);
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", 0);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");


            new Navigator().logout();
            //sign with other instructor
            new SignUp().teacher(appendChar1,dataIndex);//Sign up as a teacher
            //new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().enterAndSelectSchool(zipcode,dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on save button
            classCode = new Classes().createClass(appendChar1,dataIndex);//Create class
            new Assignment().useExistingAssignment(dataIndex);
            new Navigator().navigateTo("assessmentLibrary");
            new Click().clickByXpath("(//div[starts-with(@class,'iradio_square-green')])[4]");//checking school radio button for owner
            List<WebElement> allAssignment = driver.findElements(By.xpath("//div[@class='font-semi-bold space-15 assign-title']/a"));
            for(WebElement assignment:allAssignment)
            {
                if (assignment.getText().trim().equals(assessmentname))
                {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", assignment);
                    assignment.click();//Select the assignment
                    break;
                }

            }
            assessmentDetailsPage.tabNotRated.click();
            assessmentDetailsPage.beFirstToRateThisAssessment.click();
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(assessmentDetailsPage.buttonSave));
            assessmentDetailsPage.rating.get(4).click();
            assessmentDetailsPage.rating.get(9).click();
            assessmentDetailsPage.rating.get(14).click();
            assessmentDetailsPage.rating.get(19).click();
            assessmentDetailsPage.buttonSave.click();


            new Navigator().logout();
            //sign with other instructor
            new Login().loginAsInstructor(appendChar,dataIndex);//Sign up as a teacher
            new Navigator().navigateTo("myAssessments");
            new Click().clickByXpath("//input[@id='published']/following-sibling::ins");
            Thread.sleep(3000);
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", 0);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");

            //verify usage count
            Assert.assertEquals(assessmentDetailsPage.usageCountOfAssignment.getText(),"2 time(s)","Usage count not matches is not same");
            assessmentDetailsPage.tabNotRated.click();
            Thread.sleep(1000);
            Assert.assertEquals(assessmentDetailsPage.avgQualityRating.size(),5,"rating is not matching as rated by instructor");
            Assert.assertEquals(assessmentDetailsPage.avgAccuracyRating.size(),5,"rating is not matching as rated by instructor");
            Assert.assertEquals(assessmentDetailsPage.avgStandardsAlignmentRating.size(),5,"rating is not matching as rated by instructor");
            Assert.assertEquals(assessmentDetailsPage.avgCreativityRating.size(),5,"rating is not matching as rated by instructor");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in editAssignmentSharingPrivate method in class EditAssignment");
        }
    }


    @Test(priority = 7,enabled = true)
    public void editAssignmentSharingDistrict()

    {
        int dataIndex =7;
        String appendChar = "a8";
        String appendChar1 = "a9";
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);

        Assign assign=PageFactory.initElements(driver,Assign.class);
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver,AssessmentDetailsPage.class);
        try {

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            // new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            String zipcode=new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Assignment().create(dataIndex,"truefalse");
            new Assignment().assignToStudent(dataIndex,appendChar);
            new Assignment().editPublishedAssignment(dataIndex,appendChar);
            new Click().clickByid("assessments-back-button");
            new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']");//click on Create link
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys(assessmentname);//give the assignment name
            new QuestionCreate().multipleChoice(dataIndex);
            new Navigator().navigateTo("myAssessments");//Navigate to my assignment page
            new Click().clickByXpath("//input[@id='draft']/following-sibling::ins");
            int index = 0;
            List<WebElement> allAssessment = driver.findElements(By.cssSelector("div[class='as-label ellipsis']"));//as per E8
            for (WebElement assessment : allAssessment)
            {
                if (assessment.getText().equals(assessmentname))
                    break;
                else
                    index++;

            }
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", index);//click on Assignment

            //verify total  number question added
            Assert.assertEquals(assessmentDetailsPage.questionName.size(),2,"All question not present on page");
            //verify text present in question
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");

            assessmentDetailsPage.editAssesssment.click();
            new Click().clickByid("assessments-use-button");//click on next button to assign
            assessmentReview.nextButton.click();
            Assert.assertEquals(assign.totalPoints.getText(),"2","Total point is not matching as expected");
            //verify the sharing level should be private
            Assert.assertTrue(assign.getRadioButton_district().getAttribute("class").contains("checked"), "Sharing level is not private");
            //click on assign button
            assign.saveAssessmentAndUseLater.click();
            assign.getButton_assign().click();
            Thread.sleep(2000);
            new Navigator().navigateTo("myAssessments");
            //select sharing level as 'Private'
            new Click().clickByXpath("//input[@id='published']/following-sibling::ins");
            Thread.sleep(3000);
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", 0);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");



            new Navigator().logout();
            //sign with other instructor
            new SignUp().teacher(appendChar1,dataIndex);//Sign up as a teacher
            //new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().enterAndSelectSchool(zipcode,dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on save button
            classCode = new Classes().createClass(appendChar1,dataIndex);//Create class
            new Assignment().useExistingAssignment(dataIndex);
            new Navigator().navigateTo("assessmentLibrary");
            new Click().clickByXpath("(//div[starts-with(@class,'iradio_square-green')])[3]");//checking school radio button for owner
            List<WebElement> allAssignment = driver.findElements(By.xpath("//div[@class='font-semi-bold space-15 assign-title']/a"));
            for(WebElement assignment:allAssignment)
            {
                if (assignment.getText().trim().equals(assessmentname))
                {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", assignment);
                    assignment.click();//Select the assignment
                    break;
                }

            }
            assessmentDetailsPage.tabNotRated.click();
            assessmentDetailsPage.beFirstToRateThisAssessment.click();
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(assessmentDetailsPage.buttonSave));
            assessmentDetailsPage.rating.get(4).click();
            assessmentDetailsPage.rating.get(9).click();
            assessmentDetailsPage.rating.get(14).click();
            assessmentDetailsPage.rating.get(19).click();
            assessmentDetailsPage.buttonSave.click();


            new Navigator().logout();
            //sign with other instructor
            new Login().loginAsInstructor(appendChar,dataIndex);//Sign up as a teacher
            new Navigator().navigateTo("myAssessments");
            new Click().clickByXpath("//input[@id='published']/following-sibling::ins");
            Thread.sleep(3000);
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", 0);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");

            //verify usage count
            Assert.assertEquals(assessmentDetailsPage.usageCountOfAssignment.getText(),"2 time(s)","Usage count not matches is not same");
            assessmentDetailsPage.tabNotRated.click();
            Thread.sleep(1000);
            Assert.assertEquals(assessmentDetailsPage.avgQualityRating.size(),5,"rating is not matching as rated by instructor");
            Assert.assertEquals(assessmentDetailsPage.avgAccuracyRating.size(),5,"rating is not matching as rated by instructor");
            Assert.assertEquals(assessmentDetailsPage.avgStandardsAlignmentRating.size(),5,"rating is not matching as rated by instructor");
            Assert.assertEquals(assessmentDetailsPage.avgCreativityRating.size(),5,"rating is not matching as rated by instructor");


        }
        catch (Exception e)
        {
            Assert.fail("Exception in editAssignmentSharingPrivate method in class EditAssignment");
        }
    }


    @Test(priority = 8,enabled = true)
    public void editAssignmentSharingPublic()

    {
        int dataIndex = 8;
        String appendChar = "a8";
        String appendChar1 = "a9";
        String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);
        AssignmentReview assessmentReview=PageFactory.initElements(driver,AssignmentReview.class);
        AssessmentLibrary assessmentLibrary=PageFactory.initElements(driver,AssessmentLibrary.class);
        Assign assign=PageFactory.initElements(driver,Assign.class);
        AssessmentDetailsPage assessmentDetailsPage= PageFactory.initElements(driver,AssessmentDetailsPage.class);
        try {

            new SignUp().teacher(appendChar,dataIndex);//Sign up as a teacher
            // new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            String zipcode=new School().createWithOnlyName(appendChar,dataIndex);//Create a school
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Assignment().create(dataIndex,"truefalse");
            new Assignment().assignToStudent(dataIndex,appendChar);
            new Assignment().editPublishedAssignment(dataIndex,appendChar);
            new Click().clickByid("assessments-back-button");
            new Click().clickBycssselector("span[class='lsm-create-btn lsm-elo-create-btn btn sp-link']");//click on Create link
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).clear();
            driver.findElement(By.cssSelector("input.lsm-createAssignment-input-name")).sendKeys(assessmentname);//give the assignment name
            new QuestionCreate().multipleChoice(dataIndex);
            new Navigator().navigateTo("myAssessments");//Navigate to my assignment page
            new Click().clickByXpath("//input[@id='draft']/following-sibling::ins");
            int index = 0;
            List<WebElement> allAssessment = driver.findElements(By.cssSelector("div[class='as-label ellipsis']"));//as per E8
            for (WebElement assessment : allAssessment)
            {
                if (assessment.getText().equals(assessmentname))
                    break;
                else
                    index++;

            }
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", index);//click on Assignment

            //verify total  number question added
            Assert.assertEquals(assessmentDetailsPage.questionName.size(),2,"All question not present on page");
            //verify text present in question
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");
            assessmentDetailsPage.editAssesssment.click();
            new Click().clickByid("assessments-use-button");//click on next button to assign
            assessmentReview.nextButton.click();
            Assert.assertEquals(assign.totalPoints.getText(),"2","Total point is not matching as expected");
            //verify the sharing level should be private
            Assert.assertTrue(assign.getRadioButton_public().getAttribute("class").contains("checked"), "Sharing level is not private");
            //click on assign button
            assign.saveAssessmentAndUseLater.click();
            assign.getButton_assign().click();
            Thread.sleep(2000);
            new Navigator().navigateTo("myAssessments");
            //select sharing level as 'Private'
            new Click().clickByXpath("//input[@id='published']/following-sibling::ins");
            Thread.sleep(3000);
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", 0);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");





            new Navigator().logout();
            //sign with other instructor
            new SignUp().teacher(appendChar1,dataIndex);//Sign up as a teacher
            //new Login().loginAsInstructor(appendChar, dataIndex);//Login as a teacher
            new School().enterAndSelectSchool(zipcode,dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on save button
            classCode = new Classes().createClass(appendChar1,dataIndex);//Create class
            new Assignment().useExistingAssignment(dataIndex);
            new Navigator().navigateTo("assessmentLibrary");
            new Click().clickByXpath("(//div[starts-with(@class,'iradio_square-green')])[2]");//checking school radio button for owner
            List<WebElement> allAssignment = driver.findElements(By.xpath("//div[@class='font-semi-bold space-15 assign-title']/a"));
            for(WebElement assignment:allAssignment)
            {
                if (assignment.getText().trim().equals(assessmentname))
                {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", assignment);
                    assignment.click();//Select the assignment
                    break;
                }

            }
            assessmentDetailsPage.tabNotRated.click();
            assessmentDetailsPage.beFirstToRateThisAssessment.click();
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(assessmentDetailsPage.buttonSave));
            assessmentDetailsPage.rating.get(4).click();
            assessmentDetailsPage.rating.get(9).click();
            assessmentDetailsPage.rating.get(14).click();
            assessmentDetailsPage.rating.get(19).click();
            assessmentDetailsPage.buttonSave.click();


            new Navigator().logout();
            //sign with other instructor
            new Login().loginAsInstructor(appendChar,dataIndex);//Sign up as a teacher
            new Navigator().navigateTo("myAssessments");
            new Click().clickByXpath("//input[@id='published']/following-sibling::ins");
            Thread.sleep(3000);
            new Click().clickbylistcssselector("div[class='font-semi-bold space-15 assign-title']", 0);
            Assert.assertTrue(assessmentDetailsPage.questionName.get(0).getText().contains("Q1:  True False " + questiontext), "Question dosen't match");
            Assert.assertTrue(assessmentDetailsPage.questionName.get(1).getText().contains("Q2:  Multiple Choice " + questiontext), "Question dosen't match");

            //verify usage count
            Assert.assertEquals(assessmentDetailsPage.usageCountOfAssignment.getText(),"2 time(s)","Usage count not matches is not same");
            assessmentDetailsPage.tabNotRated.click();
            Thread.sleep(1000);
            Assert.assertEquals(assessmentDetailsPage.avgQualityRating.size(),5,"rating is not matching as rated by instructor");
            Assert.assertEquals(assessmentDetailsPage.avgAccuracyRating.size(),5,"rating is not matching as rated by instructor");
            Assert.assertEquals(assessmentDetailsPage.avgStandardsAlignmentRating.size(),5,"rating is not matching as rated by instructor");
            Assert.assertEquals(assessmentDetailsPage.avgCreativityRating.size(),5,"rating is not matching as rated by instructor");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in editAssessmentSharingPrivate method in class EditAssessment");
        }
    }
}
