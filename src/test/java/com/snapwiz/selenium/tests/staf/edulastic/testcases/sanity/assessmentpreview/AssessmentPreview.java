package com.snapwiz.selenium.tests.staf.edulastic.testcases.sanity.assessmentpreview;

import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.preview.PreviewPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.school.SchoolPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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
 * Created by pragyas on 01-03-2016.
 */
public class AssessmentPreview extends Driver {

    int dataIndex = 1;

    MyAssessments myAssessments;
    PreviewPage previewPage;
    TakeAssignment takeAssignment;
    Assignments assignments;
    AssignmentReview assignmentReview;
    String questiontext;

    @BeforeMethod
    public void init(){
        WebDriver driver=Driver.getWebDriver();
        questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        myAssessments = PageFactory.initElements(driver,MyAssessments.class);
        previewPage = PageFactory.initElements(driver,PreviewPage.class);
        takeAssignment = PageFactory.initElements(driver,TakeAssignment.class);
        assignments = PageFactory.initElements(driver,Assignments.class);
        assignmentReview = PageFactory.initElements(driver,AssignmentReview.class);
    }


    @Test(priority = 1,enabled = true)
    public void previewAssessment(){
        try{
            WebDriver driver=Driver.getWebDriver();
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            AssessmentLibrary assessmentLibrary = PageFactory.initElements(driver,AssessmentLibrary.class);
            SchoolPage schoolPage = PageFactory.initElements(driver, SchoolPage.class);

            ReportUtil.log("Description","This case validates the preview page from multiple scenarios i.e. from MyAssessments,Assignment,AssessmentDetails page","info");

            String appendChar = "a" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            //String appendChar = "aOpq";
            String appendChar1 = "b" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);
            String appendChar2 = "c" + StringUtil.generateRandomString(3, StringUtil.Mode.ALPHA);

            new SignUp().teacher(appendChar,dataIndex);//sign up as an instructor
            new School().enterAndSelectSchool("987654963",dataIndex,false);
            schoolPage.getContinueButton().click(); //clicking on save button
            String classCode = new Classes().createClass(appendChar,dataIndex);//Create class
            new Assignment().create(dataIndex,"truefalse");//Create an assignment
            new Assignment().addQuestion(dataIndex,"multiplechoice");
            new Assignment().addQuestion(dataIndex,"multipleselection");
            new Assignment().addQuestion(dataIndex,"textentry");
            new Assignment().addQuestion(dataIndex,"textdropdown");
            WebDriverUtil.clickOnElementUsingJavascript(assignments.getButton_review());//Click on review button

            for(int i=0;i<3;i++)
            {
                WebDriverUtil.clickOnElementUsingJavascript(assignmentReview.button_viewAsStudent);//Click on view as student button
                if(driver.findElements(By.id("iframe")).size()>0)
                {
                    break;
                }
            }
            //verify the preview page
            verifyPreviewPage();//Verify the fields on preview page and close it

            //Assignment preview from MyAssessment page under Draft
            new Navigator().navigateTo("myAssessments");//Navigate to my assessment
            Thread.sleep(3000);
            if(myAssessments.assignmentList.size()==0)
            {
                driver.navigate().refresh();
                Thread.sleep(8000);
                WebDriverUtil.clickOnElementUsingJavascript(myAssessments.draft);//Click on Draft
                WebDriverUtil.waitForAjax(driver,60);
                if(myAssessments.assignmentList.size()==0){
                    driver.findElement(By.xpath("//input[@id='published']/..")).click(); //click on the publish
                    WebDriverUtil.waitForAjax(driver,60);
                    myAssessments.draft.click();//Click on Draft
                    WebDriverUtil.waitForAjax(driver,60);
                }
                Thread.sleep(9000);
                new Click().clickByXpath("//input[@id='draft']/..");//Click on Draft
            }
            new MouseHover().mouserhoverbywebelement(myAssessments.cards_assessments.get(0));//Do mouse hover on the assessment
            WebDriverUtil.clickOnElementUsingJavascript(myAssessments.preview.get(0));//Click on preview
            verifyPreviewPage();//Verify the fields on preview page and close it

            //Assignment preview from MyAssessment-->AssessmentDetails page navigating through Draft
            myAssessments.names_assessment.get(0).click();//Select assessment
            myAssessments.preview.get(0).click();//Click on preview
            verifyPreviewPage();//Verify the fields on preview page and close it
            new Assignment().assignToStudent(dataIndex,appendChar);
            new Navigator().logout();//log out

            //Assignment status 'Not Started'
            new SignUp().studentDirectRegister(appendChar,classCode,dataIndex);//Sign up as a student1
            new Navigator().logout();//log out

            //Assignment status 'In Progress'
            new SignUp().studentDirectRegister(appendChar1,classCode,dataIndex);//Sign up as a student2
            new Assignment().openAssignment(dataIndex);
            new Navigator().navigateTo("assignment");
            new Navigator().logout();//log out

            //Assignment status 'Turned In'
            new SignUp().studentDirectRegister(appendChar2,classCode,dataIndex);//Sign up as a student3
            new Assignment().submitAssignment(dataIndex);//Submit assignment
            new Navigator().navigateTo("assignment");
            new Navigator().logout();//log out

            //Assignment Preview from assignment page after student's submission
            new Login().loginAsInstructor(appendChar,dataIndex);//Login as an instructor
            new Navigator().navigateTo("assignment");//Navigate to assignment page
            Thread.sleep(2000);
            assignments.more.click();
            driver.findElement(By.xpath("//div[contains(@class,'as-preview')]")).click();
            //assignments.preview.click();//Click on preview
            verifyPreviewPage();//Verify the fields on preview page and close it

            //Assignment preview from MyAssessment page under Published tab
            new Navigator().navigateTo("myAssessments");//Navigate to my assessment
            Thread.sleep(3000);
            new MouseHover().mouserhoverbywebelement(myAssessments.cards_assessments.get(0));//Do mouse hover on the assessment
            myAssessments.preview.get(0).click();//Click on preview
            verifyPreviewPage();//Verify the fields on preview page and close it

            //Assignment preview from MyAssessment-->AssessmentDetails page navigating through Published tab
            myAssessments.names_assessment.get(0).click();//Select assessment
            myAssessments.preview.get(0).click();//Click on preview
            verifyPreviewPage();//Verify the fields on preview page and close it

            //Assignment preview from Assessment Library page
            new Navigator().navigateTo("assessmentLibrary");//Navigate to assessment library page
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.xpath("//span[@data-localize='sharing-district']")));
            assessmentLibrary.textBox_search.clear();
            assessmentLibrary.textBox_search.sendKeys(assessmentname);//Type the assessment name
            Actions ac = new Actions(driver);
            ac.sendKeys(Keys.ENTER).build().perform();//Enter
            Thread.sleep(1000);
            new MouseHover().mouserhoverbywebelement(myAssessments.cards_assessments.get(0));//Do mouse hover on the assessment
            myAssessments.preview.get(0).click();//Click on preview
            verifyPreviewPage();//Verify the fields on preview page and close it

            //Assignment preview from Assessment Library--->Assessment Details page
            assessmentLibrary.getList_assessment().get(0).click();//Select assignment
            myAssessments.preview.get(0).click();//Click on preview
            verifyPreviewPage();//Verify the fields on preview page and close it

        }catch (Exception e)
        {
            Assert.fail("Exception in testcase 'previewAssessment' in class 'AssessmentPreview'", e);

        }
    }


    public void verifyPreviewPage(){
        try{

            WebDriver driver=Driver.getWebDriver();
            driver.switchTo().frame("iframe");
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.id("question-raw-content-preview")));
            //Verify the question name(Should be same as created by instructor) for Q1
            CustomAssert.assertEquals(previewPage.getQuestion_text().getText(),"True False "+questiontext,"Verify the question text on preview page","Question is dispalyed as created by instructor","Question is not displayed the same as created by instructor");

            //Verify the option(No option should be selected)for Q1
            if(previewPage.getAnswerOption().size()!=2)
            {
                CustomAssert.fail("Verify the option in true false question type","Both the options are not deselected");
            }
            WebDriverUtil.clickOnElementUsingJavascript(previewPage.getAnswerOption().get(0));//Select correct answer option
            takeAssignment.button_next.click();//Click on next button

            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='single-select-choice-icon-preview single-select-choice-icon-deselect']")));
            //Verify the question name(Should be same as created by instructor) for Q2
            CustomAssert.assertEquals(previewPage.getQuestion_text().getText(),"Multiple Choice "+questiontext,"Verify the question text on preview page","Question is dispalyed as created by instructor","Question is not displayed the same as created by instructor");

            //Verify the option(No option should be selected)for Q2
            if(previewPage.getMultipleChoiceAnswerOption().size()!=4)
            {
                CustomAssert.fail("Verify the option in multiple choice question type","All the options are not deselected");
            }
            previewPage.getMultipleChoiceAnswerOption().get(1).click();//Select correct answer option(A)
            takeAssignment.button_next.click();//Click on next button

            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("div[class='multiple-select-choice-icon-preview multiple-select-choice-icon-deselect']")));
            //Verify the question name(Should be same as created by instructor) for Q3
            CustomAssert.assertEquals(previewPage.getQuestion_text().getText(),"Multi Selection "+questiontext,"Verify the question text on preview page","Question is dispalyed as created by instructor","Question is not displayed the same as created by instructor");

            //Verify the option(No option should be selected)for Q3
            if(previewPage.getMultipleSelectionAnswerOption().size()!=4)
            {
                CustomAssert.fail("Verify the option in multiple selection question type","All the options are not deselected");
            }
            WebDriverUtil.clickOnElementUsingJavascript(previewPage.getMultipleSelectionAnswerOption().get(0));//Select one correct option(partially correct)
            takeAssignment.button_next.click();//Click on next button

            new WebDriverWait(driver,160).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("input[class='visible_redactor_input bg-color-white']")));
            //Verify the question name(Should be same as created by instructor) for Q4
            CustomAssert.assertEquals(previewPage.getQuestion_text().getText().trim(),"Text Entry "+questiontext,"Verify the question text on preview page","Question is dispalyed as created by instructor","Question is not displayed the same as created by instructor");

            previewPage.getTextField_AdvancedNumeric().sendKeys("Correct Answer");//Type incorrect answer
            takeAssignment.button_next.click();//Click on next button
            Thread.sleep(3000);
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("question-raw-content-dropdown")));
            //Verify the question name(Should be same as created by instructor) for Q5
            CustomAssert.assertTrue(previewPage.getQuestion_text().getText().contains("Text Drop Down " + questiontext), "Verify the question text on preview page", "Question is displayed as created by instructor", "Question is not displayed the same as created by instructor");

            takeAssignment.button_next.click();//Click on submit button
            takeAssignment.submitButton.click();//Click on submit button

            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='ibox-content text-center']/div")));
            //Verify the performance bar for all the questions
            String height=previewPage.performanceBar_green.get(0).getAttribute("height");

            CustomAssert.assertEquals(driver.findElements(By.xpath("//*[@height='"+height+"']")).get(0).getAttribute("fill"),"#73B966","Verify the performance bar for Q1","Performance bar is displayed as green for Q1","Performance bar is not displayed as green as expected");
            CustomAssert.assertEquals(driver.findElements(By.xpath("//*[@height='"+height+"']")).get(3).getAttribute("fill"),"#73B966","Verify the performance bar for Q4","Performance bar is displayed as green for Q4","Performance bar is not displayed as green as expected");
            CustomAssert.assertEquals(driver.findElements(By.xpath("//*[@height='"+height+"']")).get(4).getAttribute("fill"),"#767676","Verify the performance bar for Q5","Performance bar is displayed as grey for Q5","Performance bar is not displayed as grey as expected");

            //Verify the score
            CustomAssert.assertEquals(previewPage.scoreBox.get(1).getText(),"2.5","Verify the student marks","Student marks is displayed as expected","Student marks is not displayed as expected");
            CustomAssert.assertEquals(previewPage.scoreBox.get(2).getText(),"5","Verify the question point","Question point is displayed as expected","Question point is not displayed as expected");

            driver.switchTo().defaultContent();
            previewPage.close.click();//Click on close button
        }catch (Exception e)
        {
            Assert.fail("Exception in testcase 'verifyPreviewPage' in class 'AssessmentPreview'", e);

        }

    }



}
