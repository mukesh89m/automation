package com.snapwiz.selenium.tests.staf.edulastic.testcases.regression.editpoints;

import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Assignment;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Common;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Navigator;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssessmentQuestionsListPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.AssignmentReview;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.QuestionEditPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.TrueFalseQuestionCreation;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.preview.PreviewPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.BooleanValue;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.KeysSend;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.MouseHover;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.CustomAssert;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.apache.xpath.SourceTree;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;

/**
 * Created by pragyas on 18-07-2016.
 */
public class EditPointVerification extends Driver {

    public static void questionEditPointVerification(int dataIndex,String editedPoint,String questionText, String questionType)
    {
        try{

            WebDriver driver = getWebDriver();
            TrueFalseQuestionCreation questionCreate = PageFactory.initElements(driver, TrueFalseQuestionCreation.class);
            AssignmentReview assessmentReview = PageFactory.initElements(driver, AssignmentReview.class);
            AssessmentQuestionsListPage listPage = PageFactory.initElements(driver, AssessmentQuestionsListPage.class);
            PreviewPage previewPage = PageFactory.initElements(driver, PreviewPage.class);

            String defaultPoint = (String) ((JavascriptExecutor)driver).executeScript("return document.getElementById('points-input-tag').value");

            if(questionType.equals("Multipart"))
            {
                //Verify the default point
                CustomAssert.assertEquals(defaultPoint, "2","Check default point","Default value is displayed as expected","Question is not carrying default point");

            }else {
                //Verify the default point
                CustomAssert.assertEquals(defaultPoint, "1","Check default point","Default value is displayed as expected","Question is not carrying default point");

            }

            new Common().waitForToastBlockerToClose(60);
            questionCreate.getPoint_editor().click();//Click on point editor

            //Remove the point
//            new KeysSend().sendKeyBoardKeys("$");
//            new KeysSend().sendKeyBoardKeys("<");
            questionCreate.getPoint_textbox().clear();

            questionCreate.button_savePoint.click();//Click on save button

            //An error message should be displayed if no points are assigned
            CustomAssert.assertEquals(questionCreate.getPoint_textbox().getCssValue("color"), "rgba(103, 106, 108, 1)","Verify red color on point text box if no point is entered","Red color is not displayed as error" ,"Point text box is not becoming red if no point is entered");

            // Negative and 0 points should not be allowed
            questionCreate.getPoint_textbox().click();//Click on point text box
            questionCreate.getPoint_textbox().sendKeys("0");//Enter the point
            questionCreate.button_savePoint.click();//Click on save button

            String pointAfterEnteringZero = (String) ((JavascriptExecutor)driver).executeScript("return document.getElementById('points-input-tag').value");
            //No point should be displayed after entering zero value
            CustomAssert.assertEquals(pointAfterEnteringZero,"","Verify the point value after entering 0","0 is not allowed in text box","Point text box is allowing 0 to be entered");

            questionCreate.getPoint_textbox().click();//Click on point text box
            questionCreate.getPoint_textbox().sendKeys("-");//Enter the point
            questionCreate.button_savePoint.click();//Click on save button

            String negativePoint = (String) ((JavascriptExecutor)driver).executeScript("return document.getElementById('points-input-tag').value");
            //No point should be displayed after entering negative value
            CustomAssert.assertEquals(negativePoint,"","Verify the point value after entering negative value","Negative value is not allowed in text box","Point text box is allowing negative value to be entered");

            // After dot only 2 digits decimal allowed.
            questionCreate.getPoint_textbox().click();//Click on point text box
            questionCreate.getPoint_textbox().sendKeys("2.3334");//Enter the point
            questionCreate.button_savePoint.click();//Click on save button
            new Common().waitForToastBlockerToClose(10);
            String pointInDecimal = (String) ((JavascriptExecutor)driver).executeScript("return document.getElementById('points-input-tag').value");
            CustomAssert.assertEquals(pointInDecimal, "2.33","Verify the points in decimal","Only 2 digits are allowed after decimal as expected","More than 2 digits are allowed after dot");

            //Only 2 digits are allowed for point edit
            questionCreate.getPoint_editor().click();
            Actions actions = new Actions(driver);
//            actions.sendKeys(Keys.END);
            questionCreate.getPoint_textbox().clear();

            questionCreate.getPoint_textbox().sendKeys("153");//Enter the point
            questionCreate.button_savePoint.click();//Click on save button
            new Common().waitForToastBlockerToClose(10);
            String point_3_digits = (String) ((JavascriptExecutor)driver).executeScript("return document.getElementById('points-input-tag').value");
            CustomAssert.assertEquals(point_3_digits, "15","Verify the points","Only 2 digits are allowed as expected","More than 2 digits are allowed");

            questionCreate.getPoint_editor().click();

            new MouseHover().mouserhoverbywebelement(questionCreate.button_savePoint);//Hover over on save button
            //"Save points" text should display
            CustomAssert.assertEquals(questionCreate.button_savePoint.getAttribute("title"),"Save Points","Verify save points on toool tio of save button","Save point s is displayed","Save point is not displayed after hover over on save button");

//            actions.sendKeys(Keys.END);
            questionCreate.getPoint_textbox().clear();
            questionCreate.getPoint_textbox().sendKeys("3");//Enter the point
            questionCreate.button_savePoint.click();//Click on save button

            //Point should be saved
            String PointsAfterEdit = (String) ((JavascriptExecutor)driver).executeScript("return document.getElementById('points-input-tag').value");
            CustomAssert.assertEquals(PointsAfterEdit, "3","Verify the save points","Point is saved as expected","Point is saved as expected");

            //Edit point but don't save
            questionCreate.getPoint_editor().click();
            actions.sendKeys(Keys.END);
            questionCreate.getPoint_textbox().clear();
            questionCreate.getPoint_textbox().sendKeys("4");//Enter the point

            new WebDriverWait(driver,30).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[text()='Successfully saved.']")));
            new Common().waitForToastBlockerToClose(120);
            questionCreate.getButton_review().click();//click on review assessment button
            WebDriverUtil.waitForAjax(driver,60);

            //Instructor review screen
            //Question details should display like  question text, question type, points and standards aligned with the question.
            CustomAssert.assertTrue(listPage.getList_questionBody().get(0).getText().trim().contains(questionText),"Verify question name","Question name is displayed as expected","Question name is not displayed as expected");
            CustomAssert.assertEquals(listPage.getQuestionType().getText(),questionType,"Verify question type","Question type is displayed as expected","Question type is not displayed as expected");
            CustomAssert.assertEquals(listPage.getPoint().getAttribute("value"),"4","Verify question point","Question point is displayed as expected","Question point is not displayed as expected");
            CustomAssert.assertEquals(listPage.getLabelValue_StandardTlo().getText(),"1.OA1.OA.A.1","Verify standards","Standard is displayed as expected","Standard is not displayed as expected");

            questionCreate.getPoint_editor().click();//Click on point editor
            actions.sendKeys(Keys.END);
            listPage.getPoint().clear();
            listPage.getPoint().sendKeys("5");//change the point in short view
            questionCreate.button_savePoint.click();//Click on save button
            Thread.sleep(1000);
            listPage.getArrowLink().click();//Click on arrow
            Thread.sleep(1000);

            //Instructor expanded view
            //Edited point should be displayed and disabled
            CustomAssert.assertEquals(questionCreate.getPoint_textbox().getAttribute("value"), "5","Verify the edited point at instructor expanded view","Edited point is displayed as expected", "Edited Point is not displayed correctly in instructor expanded view");
            CustomAssert.assertEquals(questionCreate.getPoint_textbox().getAttribute("disabled"),"true","Verify edited point at expanded view","Edited point is displayed as disabled", "Edited Point is not disabled in instructor expanded view");

            assessmentReview.getButton_Edit().click();//Click on edit button
            Thread.sleep(1000);
            questionCreate.getPoint_editor().click();//Click on point editor
            actions.sendKeys(Keys.END);
            questionCreate.getPoint_textbox().clear();//Remove the point
            questionCreate.getPoint_textbox().sendKeys(editedPoint);//Enter the point
            questionCreate.button_savePoint.click();//Click on save button
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.xpath("//div[text()='Successfully saved.']")),30);
            new Common().waitForToastBlockerToClose(60);
            String pointAfterEdit = (String) ((JavascriptExecutor)driver).executeScript("return document.getElementById('points-input-tag').value");

            //Instructor should be able to edit the points assigned to a question, in the Edit view
            CustomAssert.assertEquals(pointAfterEdit, editedPoint,"Verify the point after editing it on edit page","Point is displaying as modified by instructor","Point is not displaying as modified by instructor");


            //No points should be displayed in the preview attempt screen
            questionCreate.getButton_Preview().click();//Click on preview button
//            new Actions(driver).moveToElement(questionCreate.getButton_Preview()).click().build().perform();

            driver.switchTo().frame("iframe");

            WebDriverUtil.waitTillVisibilityOfElement(questionCreate.getPreview_submit(),30);

            boolean pointFound = new BooleanValue().presenceOfElementByWebElement(dataIndex,previewPage.getQuestionPoint_previewPage());
            CustomAssert.assertEquals(pointFound,false,"Verify the point on preview page","Point is not displayed as expected","Point is displayed");

            //Once the answer is submitted, the points should not be displayed in the evaluation view of Preview
            previewPage.getGet_submit().click();//Click on submit button
            boolean pointFoundAfterSubmit = new BooleanValue().presenceOfElementByWebElement(dataIndex,previewPage.getQuestionPoint_previewPage());
            CustomAssert.assertEquals(pointFoundAfterSubmit, false, "Verify the point on preview page after submitting", "Point is not displayed as expected", "Point is displayed");

            previewPage.close_previewQuestion.click();//Click on close button

            driver.switchTo().defaultContent();

        }catch (Exception e){
            Assert.fail("Exception in testcase 'questionEditPointVerification' in class 'EditPointVerification'", e);

        }


    }
}
