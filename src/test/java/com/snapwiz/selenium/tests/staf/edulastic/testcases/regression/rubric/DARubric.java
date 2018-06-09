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
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Priyanka on 26-10-2016.
 */
public class DARubric extends Driver{

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
    public void daUseExistingRubric(){
        try{

            String email = "testda@snapwiz.com";
            int dataIndex = 1;

            String rubricname1 = ReadTestData.readDataByTagName("", "rubricname", Integer.toString(1));
            String rubricname2 = ReadTestData.readDataByTagName("", "rubricname", Integer.toString(2));

            String rubricdescription = ReadTestData.readDataByTagName("", "rubricdescription", Integer.toString(dataIndex));
            String criteriadescription = ReadTestData.readDataByTagName("", "criteriadescription", Integer.toString(2));
            String ratingonedescription = ReadTestData.readDataByTagName("", "ratingonedescription", Integer.toString(2));
            String ratingtwodescription = ReadTestData.readDataByTagName("", "ratingtwodescription", Integer.toString(2));
            String assessmentname1 = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(1));

            SchoolPage schoolPage = PageFactory.initElements(Driver.getWebDriver(), SchoolPage.class);
            String appendChar1 = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            //String appendChar1="aiAY";

            String methodName = new Exception().getStackTrace()[0].getMethodName();
            System.out.println(methodName);
            String daname = "TESTDA";
            String author1 = methodName+"ins"+appendChar1;

            //Teacher1 signup
            new SignUp().teacher(appendChar1,dataIndex);//Sign up as an instructor1
            new School().enterAndSelectSchool("654321369",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on continue button
            String classCode1 =  new Classes().createClass(appendChar1,dataIndex);//Create class
            System.out.println("classCode1 :" + classCode1);
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1, dataIndex);//Login as an instructor1
            new Assignment().create(dataIndex, "truefalse");
            String rubricCreatedByAuthor1 = new Assignment().addQuestion(dataIndex, "essay");
            System.out.println(rubricCreatedByAuthor1);
            new Navigator().navigateTo("dashboard");
            new Navigator().logout();//log out

            new Login().loginAsDA(dataIndex, email); //login as District Admin
            String rubricCreatedByDA = new Assignment().createByDA(dataIndex, "essay");

           /* String rubricCreatedByAuthor1 = "Rubric1BK";
            String rubricCreatedByDA = "Rubric1Sa";*/

            System.out.println(rubricCreatedByDA);
            //share the created rubric with other teacher in same district
            addQuestion.share_link.click();
            addQuestion.shareRubric_yesLink.click(); //click on the  yes
            Thread.sleep(1000);
            addQuestion.label_UseExistingRubric.click(); // click on the use existing Rubric tab
            actual = addQuestion.rubric_search_textarea.getAttribute("placeholder");
            CustomAssert.assertEquals(actual,"Search by rubric name or author name","Verify default search text","Default search text is displayed","Default search test is not displayed");
            CustomAssert.assertEquals(addQuestion.rubric_search_icon.isDisplayed(),true,"Verify search icon","Search icon is played","Search icon is not displayed");
            addQuestion.rubric_search_textarea.sendKeys(rubricname1);
            addQuestion.rubric_search_icon.click();
            actual = addQuestion.rubricLabel.get(0).getText();
            CustomAssert.assertEquals(actual,"Rubric Name","Verify 1st column heading as 'Rubric Name'","Heading is as per expected","Heading is not as per expected");
            actual = addQuestion.rubricLabel.get(1).getText();
            CustomAssert.assertEquals(actual,"Author","Verify 1st column heading as 'Author'","Heading is as per expected","Heading is not as per expected");
            actual = addQuestion.rubricLabel.get(2).getText();
            CustomAssert.assertEquals(actual,"Description","Verify 1st column heading as 'Description'","Heading is as per expected","Heading is not as per expected");
            Thread.sleep(3000);
            int foundIndex = 0;
            for (WebElement element : addQuestion.rubricNamesCreatedByAuthor) {
                if (element.getText().trim().equals(rubricCreatedByDA.trim())) {
                    break;
                }
                foundIndex++;
            }
            actual = addQuestion.rubricNamesCreatedByAuthor.get(foundIndex).getText();
            expected = rubricCreatedByDA;
            CustomAssert.assertEquals(actual,expected,"Verify rubric created by same author","Rubric created by author is present","Rubric created by author is not present");
            new MouseHover().mouserhoverbywebelement(addQuestion.rubricNamesCreatedByAuthor.get(foundIndex));

            List<WebElement> rubricOptionIcon = getWebDriver().findElements(By.xpath("(//span[text()='"+rubricCreatedByDA+"']/..)[2]/following-sibling::div[2]/div/span"));
            actual = rubricOptionIcon.get(0).getAttribute("title");
            CustomAssert.assertEquals(actual,"Preview","Verify View rubric: eye mark icon","View rubric: eye mark ico is displayed","View rubric: eye mark icon is not displaying");
            actual = rubricOptionIcon.get(1).getAttribute("title");
            CustomAssert.assertEquals(actual,"Clone","Verify Clone icon","Clone icon is displayed","Clone icon is not displaying");
            Thread.sleep(2000);
            actual = rubricOptionIcon.get(2).getAttribute("title");
            CustomAssert.assertEquals(actual,"Delete","Verify Delete icon","Delete icon is displayed","Delete icon is not displaying");
            actual = rubricOptionIcon.get(3).getAttribute("title");
            CustomAssert.assertEquals(actual,"Unshare","Verify Share mark icon","Share icon is displayed","Share icon is not displaying");
            new Navigator().navigateTo("myAssessments");
            new Navigator().logout();//log out

            new Login().loginAsInstructor(appendChar1, dataIndex);//Login as an instructor1
            new Navigator().navigateTo("myAssessments");
            WebDriverUtil.clickOnElementUsingJavascript(myAssessments.draft);//Click on Draft
            Thread.sleep(6000);
            int index1 = 0;
            List<WebElement> allAssessment1 = myAssessments.names_assessment;
            for (WebElement assessment : allAssessment1)
            {
                if (assessment.getText().equals(assessmentname1))
                    break;
                else
                    index1++;

            }
            WebDriverUtil.clickOnElementUsingJavascript(myAssessments.names_assessment.get(index1));//click on Assessment
            assessmentDetailsPage.editAssesssment.click();//Click on edit button
            assessmentDetailsPage.clickArrowIcon.get(1).click();
            assignmentReview.editButton.click();
            addQuestion.label_UseExistingRubric.click(); //click on the use existing Rubric tab
            addQuestion.rubric_search_textarea.clear();
            addQuestion.rubric_search_textarea.sendKeys(rubricname2);
            addQuestion.rubric_search_icon.click();
            int foundIndex1 = 0;
            for (WebElement element : addQuestion.rubricNamesCreatedByAuthor) {
                if (element.getText().trim().equals(rubricCreatedByDA.trim())) {
                    break;
                }
                foundIndex1++;
            }
            actual = addQuestion.rubricNamesCreatedByAuthor.get(foundIndex1).getText();
            expected = rubricCreatedByDA;
            CustomAssert.assertEquals(actual,expected,"Verify rubric created by other author in same district","Rubric created by other author is present","Rubric created by other author is not present");
            new MouseHover().mouserhoverbywebelement(addQuestion.rubricNamesCreatedByAuthor.get(foundIndex1));
            List<WebElement> rubricOptionIcon1 = getWebDriver().findElements(By.xpath("(//span[text()='"+rubricCreatedByDA+"']/..)[1]/following-sibling::div[2]/div/span"));
            actual = rubricOptionIcon1.get(0).getAttribute("title");
            CustomAssert.assertEquals(actual,"Preview","Verify View rubric: eye mark icon","View rubric: eye mark ico is displayed","View rubric: eye mark icon is not displaying");
            actual = rubricOptionIcon1.get(1).getAttribute("title");
            CustomAssert.assertEquals(actual,"Clone","Verify Clone icon","Clone icon is displayed","Clone icon is not displaying");

            //Rubric search from author2 name
            addQuestion.rubric_search_textarea.clear();
            addQuestion.rubric_search_textarea.sendKeys(rubricCreatedByDA);
            addQuestion.rubric_search_icon.click();
            int foundIndex3 = 0;
            for (WebElement element : addQuestion.rubricNamesCreatedByAuthor) {
                if (element.getText().trim().equals(rubricCreatedByDA.trim())) {
                    break;
                }
                foundIndex3++;
            }
            actual = addQuestion.rubricNamesCreatedByAuthor.get(foundIndex3).getText();
            expected = rubricCreatedByDA;
            CustomAssert.assertEquals(actual,expected,"Verify rubric created by other author in same district","Rubric created by other author is present","Rubric created by other author is not present");
            new MouseHover().mouserhoverbywebelement(addQuestion.rubricNamesCreatedByAuthor.get(foundIndex3));
            addQuestion.rubricOptionIcon.get(0).click();//click on view rubric
            Thread.sleep(2000);
            actual = addQuestion.rubric_name.get(2).getText().trim();
            CustomAssert.assertEquals(actual,expected,"Verify rubric name","Rubric created by other author is present","Rubric created by other author is not present");
            actual = addQuestion.rubricDescription.get(1).getText();
            CustomAssert.assertEquals(actual,rubricdescription,"Verify rubric description","Rubric description created by other author is present","Rubric description created by other author is not present");

            CustomAssert.assertEquals(addQuestion.criteriaDescription.get(1).getText().trim(),criteriadescription.trim(),"Verify criteria Description","Criteria description is displaying in preview page","Criteria description is not displaying in preview page");
            CustomAssert.assertEquals(addQuestion.rating_Description.get(2).getText().trim(),ratingonedescription.trim(),"Verify rating one ","Rating one is displaying in preview page","Rating one is not displaying in preview page");
            CustomAssert.assertEquals(addQuestion.rating_Description.get(3).getText().trim(),ratingtwodescription.trim(),"Verify rating two","Rating one is displaying in preview page","Rating two is not displaying in preview page");
            addQuestion.backButton.get(1).click();//click on back
            new MouseHover().mouserhoverbywebelement(addQuestion.rubricNamesCreatedByAuthor.get(foundIndex3));
            List<WebElement> rubricOptionIcon2 = getWebDriver().findElements(By.xpath("(//span[text()='"+rubricCreatedByDA+"']/..)[1]/following-sibling::div[2]/div/span"));
            actual = rubricOptionIcon2.get(0).getAttribute("title");
            CustomAssert.assertEquals(actual,"Preview","Verify View rubric: eye mark icon","View rubric: eye mark ico is displayed","View rubric: eye mark icon is not displaying");
            actual = rubricOptionIcon2.get(1).getAttribute("title");
            CustomAssert.assertEquals(actual,"Clone","Verify Clone icon","Clone icon is displayed","Clone icon is not displaying");

            //searched rubric is not available
            addQuestion.rubric_search_textarea.clear();
            addQuestion.rubric_search_textarea.sendKeys("name");
            addQuestion.rubric_search_icon.click();
            actual = addQuestion.rubricNotAvailableMessage.getText();


        }catch (Exception e){
            Assert.fail("Exception in testcase daUseExistingRubric in class DARubric",e);

        }
    }


}
