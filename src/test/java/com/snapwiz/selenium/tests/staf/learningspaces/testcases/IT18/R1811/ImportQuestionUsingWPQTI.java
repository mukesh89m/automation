package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT18.R1811;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.NewQuestionDataEntry;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.Preview;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Mukesh on 1/12/15.
 */
public class ImportQuestionUsingWPQTI extends Driver{
    String email="wiley1";
    String password="snapwiz";
    @Test(priority = 1,enabled = true)
    public void importTrueFalseQuestionUsingWPQTI()
    {
        try {
            //Tc row no 11
            int index=11;
            new DirectLogin().directLoginWithCreditial(email,password,index);
            new Assignment().importQuestionUsingWPQTI(index);
            verifyQuestionType("True / False",index);


        } catch (Exception e) {
            Assert.fail("Exception in testcase importTrueFalseQuestionUsingWPQTI of class ImportQuestionUsingWPQTI,",e);
        }
    }

    @Test(priority = 2,enabled = true)
    public void importMultipleChoiceQuestionUsingWPQTI()
    {
        try {
            //Tc row no 19
            int index=19;
            new DirectLogin().directLoginWithCreditial(email,password,index);
            new Assignment().importQuestionUsingWPQTI(index);
            //verifyQuestionType("Multiple Choice",index);
            NewQuestionDataEntry newQuestionDataEntry= PageFactory.initElements(driver,NewQuestionDataEntry.class);
            Preview preview=PageFactory.initElements(driver,Preview.class);
            //Tc row no 15
            //7. Check question created through WPQTI file
            //Expected # 1. Question type should be True and False
            System.out.println("question type:"+newQuestionDataEntry.getQuestionType().getText());
            System.out.println("question type:"+newQuestionDataEntry.getQuestionType().getText());
            System.out.println("question type:"+newQuestionDataEntry.getQuestionType().getText());

            Assert.assertEquals(newQuestionDataEntry.getQuestionType().getText().trim(),"Multiple Choice","Question type is not Multiple Choice");

            //Expected 2# 2. Question status should be QA
            newQuestionDataEntry.questionStatus.click();//click on the QA question status
            driver.findElement(By.xpath("//html/body")).click();

            String correctAnswer=driver.findElement(By.cssSelector("div[class='qtn-label-selected']")).getText().trim();
            System.out.println("Correct Answer:"+correctAnswer);

             newQuestionDataEntry.shuffle_checkbox.click();//click on the shuffle checkbox
             newQuestionDataEntry.save_button.click();

            newQuestionDataEntry.preview_link.click(); //click on the preview link

            for(String child:driver.getWindowHandles())
            {
                driver.switchTo().window(child);
            }


            //Tc row no 25
            //9 Click on preview button, select the correct answer and click on "Check answer" button
            driver.findElement(By.xpath("//td[@classname='qtn-label'][contains(text(),'"+correctAnswer+"')]")).click();
            //driver.findElement(By.xpath("//span[text()='"+correctAnswer+"']")).click();
            preview.checkAnswer_button.click();//click on the check Answer button

            //Expected #1. It should show message as "You got it right"
            Assert.assertEquals(preview.trueNotificationMsg.getText().trim(),"You got it right.","Message  \"You got it right\" is not displaying ");

            //Tc row now 26.
            //8. Click on preview button, select the wrong answer and click on "Check answer" button
            preview.select_multipleChoice.get(0).click();//click on the A answer which is incorrect byDefault
            preview.checkAnswer_button.click();//click on the check Answer button

            //Expected #1. It should show message as "You got it wrong"
            Assert.assertEquals(preview.wrongNotificationMsg.getText().trim(),"You got it wrong.","Message  \"You got it wrong\" is not displaying ");

            preview.select_multipleChoice.get(2).click();//click on the answer  label c
            preview.checkAnswer_button.click();//click on the check Answer button

            //Expected #1. It should show message as "You got it wrong"
            Assert.assertEquals(preview.wrongNotificationMsg.getText().trim(),"You got it wrong.","Message  \"You got it wrong\" is not displaying ");


            preview.select_multipleChoice.get(2).click();//click on the answer  label D
            preview.checkAnswer_button.click();//click on the check Answer button

            //Expected #1. It should show message as "You got it wrong"
            Assert.assertEquals(preview.wrongNotificationMsg.getText().trim(),"You got it wrong.","Message  \"You got it wrong\" is not displaying ");



        } catch (Exception e) {
            Assert.fail("Exception in testcase importMultipleChoiceQuestionUsingWPQTI of class ImportQuestionUsingWPQTI,",e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void importMultipleSelectionQuestionUsingWPQTI()
    {
        try {
            //Tc row no 27
            int index=27;
            NewQuestionDataEntry newQuestionDataEntry= PageFactory.initElements(driver,NewQuestionDataEntry.class);
            Preview preview=PageFactory.initElements(driver,Preview.class);

            new DirectLogin().directLoginWithCreditial(email,password,index);
            new Assignment().importQuestionUsingWPQTI(index);
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.id("question-type-title")));

            //Tc row no 15
            //7. Check question created through WPQTI file
            //Expected # 1. Question type should be True and False
            Assert.assertEquals(newQuestionDataEntry.getQuestionType().getText().trim(),"Multiple Selection","Question type is not Multiple Selection");

            //Expected 2# 2. Question status should be QA
            newQuestionDataEntry.questionStatus.click();//click on the QA question status
            driver.findElement(By.xpath("//html/body")).click();

            String correctAnswer=newQuestionDataEntry.selected_multipleSelection.getText().trim();
            System.out.println("Correct Answer:"+correctAnswer);
            newQuestionDataEntry.preview_link.click(); //click on the preview link

            for(String child:driver.getWindowHandles())
            {
                driver.switchTo().window(child);
            }


            //Tc row no 33
            //9 Click on preview button, select the correct answer and click on "Check answer" button
            driver.findElement(By.xpath("//span[text()='"+correctAnswer+"']")).click();
            preview.checkAnswer_button.click();//click on the check Answer button

            //Expected #1. It should show message as "You got it right"
            Assert.assertEquals(preview.notificationMsg.getText().trim(),"You got it right.","Message  \"You got it right\" is not displaying ");

            //Tc row now 35.
            //8. Click on preview button, select the wrong answer and click on "Check answer" button
            preview.deselect_multipleSelection.click();//click on the A answer which is incorrect byDefault
            driver.findElement(By.xpath("//span[text()='"+correctAnswer+"']")).click();

            preview.checkAnswer_button.click();//click on the check Answer button
            Thread.sleep(2000);
            //Expected #1. It should show message as "You got it wrong"
            Assert.assertEquals(preview.notificationMsg.getText().trim(),"You got it partially correct.","Message  \"You got it Partially Correct.\" is not displaying ");

            //Tc row no 34
            preview.deselect_multipleSelection.click();//click on the A answer which is incorrect byDefault
            preview.checkAnswer_button.click();//click on the check Answer button
            Thread.sleep(2000);
            //Expected #1. It should show message as "You got it wrong"
            Assert.assertEquals(preview.notificationMsg.getText().trim(),"You got it wrong.","Message  \"You got it wrong\" is not displaying ");



        } catch (Exception e) {
            Assert.fail("Exception in testcase importMultipleSelectionQuestionUsingWPQTI of class ImportQuestionUsingWPQTI,",e);
        }
    }

    @Test(priority = 4,enabled = true)
    public void importTextEntryQuestionUsingWPQTI()
    {
        try {
            //Tc row no 36
            int index=36;
            NewQuestionDataEntry newQuestionDataEntry= PageFactory.initElements(driver,NewQuestionDataEntry.class);
            Preview preview=PageFactory.initElements(driver,Preview.class);

            new DirectLogin().directLoginWithCreditial(email,password,index);
            new Assignment().importQuestionUsingWPQTI(index);
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.id("question-type-title")));

            //Tc row no 15
            //7. Check question created through WPQTI file
            //Expected # 1. Question type should be True and False
            Assert.assertEquals(newQuestionDataEntry.getQuestionType().getText().trim(),"Text Entry","Question type is not Text Entry");

            //Expected 2# 2. Question status should be QA
            newQuestionDataEntry.questionStatus.click();//click on the QA question status
            driver.findElement(By.xpath("//html/body")).click();

            newQuestionDataEntry.preview_link.click(); //click on the preview link

            for(String child:driver.getWindowHandles())
            {
                driver.switchTo().window(child);
            }


            //Tc row no 42
            //9 Click on preview button, select the correct answer and click on "Check answer" button
            new TextSend().textsendbycssSelector("$i","span[class='input-tag-wrapper question-element-wrapper']>input");
            preview.checkAnswer_button.click();//click on the check Answer button

            //Expected #1. It should show message as "You got it right"
            Assert.assertEquals(preview.trueNotificationMsg.getText().trim(),"You got it right.","Message  \"You got it right\" is not displaying ");


            //Tc row no 43
            new TextSend().textsendbycssSelector("$i22","span[class='input-tag-wrapper question-element-wrapper']>input");
            preview.checkAnswer_button.click();//click on the check Answer button
            Thread.sleep(2000);
            //Expected #1. It should show message as "You got it wrong"
            Assert.assertEquals(preview.wrongNotificationMsg.getText().trim(),"You got it wrong.","Message  \"You got it wrong\" is not displaying ");



        } catch (Exception e) {
            Assert.fail("Exception in testcase importTextEntryQuestionUsingWPQTI of class ImportQuestionUsingWPQTI,",e);
        }
    }

    @Test(priority = 5,enabled = true)
    public void importTextDropDownQuestionUsingWPQTI()
    {
        try {
            //Tc row no 45
            int index=45;
            NewQuestionDataEntry newQuestionDataEntry= PageFactory.initElements(driver,NewQuestionDataEntry.class);
            Preview preview=PageFactory.initElements(driver,Preview.class);

            new DirectLogin().directLoginWithCreditial(email,password,index);
            new Assignment().importQuestionUsingWPQTI(index);
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.id("question-type-title")));
            //Tc row no 15
            //7. Check question created through WPQTI file
            //Expected # 1. Question type should be True and False
            Assert.assertEquals(newQuestionDataEntry.getQuestionType().getText().trim(),"Text Drop Down","Question type is not Text Drop Down");

            //Expected 2# 2. Question status should be QA
            newQuestionDataEntry.questionStatus.click();//click on the QA question status
            driver.findElement(By.xpath("//html/body")).click();

            newQuestionDataEntry.preview_link.click(); //click on the preview link
            Thread.sleep(5000);

            for(String child:driver.getWindowHandles())
            {
                driver.switchTo().window(child);
            }

           //Tc row no 42
            //9 Click on preview button, select the correct answer and click on "Check answer" button
           // new DropDown().selectValueByIndex("question-raw-content-dropdown",1);
            driver.findElement(By.xpath("//a[starts-with(@id,'sbToggle_')]")).click();
            driver.findElement(By.xpath("//a[@title='Not a function']")).click();
            preview.checkAnswer_button.click();//click on the check Answer button
            //Expected #1. It should show message as "You got it right"
            Assert.assertEquals(preview.trueNotificationMsg.getText().trim(),"You got it right.","Message  \"You got it right\" is not displaying ");

            //Tc row no 43
            driver.findElement(By.xpath("//a[starts-with(@id,'sbToggle_')]")).click();
            driver.findElement(By.xpath("//a[@title='Function']")).click();
            preview.checkAnswer_button.click();//click on the check Answer button
            Thread.sleep(2000);
            //Expected #1. It should show message as "You got it wrong"
            Assert.assertEquals(preview.wrongNotificationMsg.getText().trim(),"You got it wrong.","Message  \"You got it wrong\" is not displaying ");

        } catch (Exception e) {
            Assert.fail("Exception in testcase importTextDropDownQuestionUsingWPQTI of class ImportQuestionUsingWPQTI,",e);
        }
    }


    @Test(priority = 6,enabled = true)
    public void importMapleNumericQuestionUsingWPQTI()
    {
        try {
            //Tc row no 54
            int index=54;
            NewQuestionDataEntry newQuestionDataEntry= PageFactory.initElements(driver,NewQuestionDataEntry.class);
            Preview preview=PageFactory.initElements(driver,Preview.class);

            new DirectLogin().directLoginWithCreditial(email,password,index);
            new Assignment().importQuestionUsingWPQTI(index);
            new WebDriverWait(driver,180).until(ExpectedConditions.presenceOfElementLocated(By.id("question-type-title")));

            //Tc row no 15
            //7. Check question created through WPQTI file
            //Expected # 1. Question type should be True and False
            Assert.assertEquals(newQuestionDataEntry.getQuestionType().getText().trim(),"Advanced Numeric","Question type is not Advanced Numeric");

            //Expected 2# 2. Question status should be QA
            newQuestionDataEntry.questionStatus.click();//click on the QA question status
            driver.findElement(By.xpath("//html/body")).click();

            preview.mapleNumericContent.click();//click on the question
            preview.mapleNumericContent.sendKeys("$ans");

            Thread.sleep(3000);
            newQuestionDataEntry.hint_textBox.click();
            newQuestionDataEntry.hint_textBox.sendKeys("$ans");

            newQuestionDataEntry.save_button.click(); //click on the save button

            newQuestionDataEntry.preview_link.click(); //click on the preview link
            Thread.sleep(5000);

            for(String child:driver.getWindowHandles())
            {
                driver.switchTo().window(child);
            }

            //Tc row no 60
            //9 Click on preview button, select the correct answer and click on "Check answer" button
            preview.hint_button.click();
            Thread.sleep(3000);
            String correctAnswer=preview.mapleNumeric_textBox.getAttribute("renderdata").trim();
            Map<String,Object> convertJSONStringTOMap = convertJSONStringTOMap(correctAnswer);
            Thread.sleep(5000);
            String answer=convertJSONStringTOMap.get("correctAnswer").toString();
            preview.mapleNumeric_textBox.sendKeys(answer);
            preview.checkAnswer_button.click();//click on the check Answer button
            //Expected #1. It should show message as "You got it right"
            Assert.assertEquals(preview.trueNotificationMsg.getText().trim(),"You got it right.","Message  \"You got it right\" is not displaying ");
            //Tc row no 61

            preview.mapleNumeric_textBox.clear();
            preview.mapleNumeric_textBox.sendKeys("$ansss");
            preview.checkAnswer_button.click();//click on the check Answer button
            Thread.sleep(2000);
            //Expected #1. It should show message as "You got it wrong"
            Assert.assertEquals(preview.wrongNotificationMsg.getText().trim(),"You got it wrong.","Message  \"You got it wrong\" is not displaying ");

        } catch (Exception e) {
            Assert.fail("Exception in testcase importMapleNumericQuestionUsingWPQTI of class ImportQuestionUsingWPQTI,",e);
        }
    }


    @Test(priority = 7,enabled = true)
    public void importTrueFalseAndMultipleChoiceQuestionUsingWPQTI()
    {
        try {
            //Tc row no 72
            int index=72;
            NewQuestionDataEntry newQuestionDataEntry= PageFactory.initElements(driver,NewQuestionDataEntry.class);
            Preview preview=PageFactory.initElements(driver,Preview.class);

            new DirectLogin().directLoginWithCreditial(email,password,index);
            new Assignment().importQuestionUsingWPQTI(index);
            //Tc row no 15
            //7. Check question created through WPQTI file
            //Expected # 1. Question type should be True and False
            Assert.assertEquals(newQuestionDataEntry.getQuestionType().getText().trim(),"True / False","Question type is not True / False");
            newQuestionDataEntry.questionStatus.click();//click on the QA question status
            driver.findElement(By.xpath("//html/body")).click();

            newQuestionDataEntry.jumpToQuestionSelector.click();//click on jump to question
            driver.findElement(By.xpath("(//a[.='2'])[2]")).click();

            Thread.sleep(5000);
            Assert.assertEquals(newQuestionDataEntry.getQuestionType().getText().trim(),"Multiple Choice","Question type is not Multiple Choice");
            //Expected 2# 2. Question status should be QA

            newQuestionDataEntry.questionStatus.click();//click on the QA question status
            driver.findElement(By.xpath("//html/body")).click();

            String correctAnswer=newQuestionDataEntry.selected_multipleChoice.getText().trim();
            System.out.println("Correct Answer:"+correctAnswer);

            newQuestionDataEntry.multipleChoiceShuffle_checkbox.click();//click on the shuffle checkbox
            newQuestionDataEntry.save_button.click();

            newQuestionDataEntry.preview_link.click(); //click on the preview link
            Thread.sleep(5000);

            for(String child:driver.getWindowHandles())
            {
                driver.switchTo().window(child);
            }

            //Tc row no 78
            //9 Click on preview button, select the correct answer and click on "Check answer" button
            // driver.findElement(By.xpath("//div[contains(@class,'single-select-choice-icon-preview single-select-choice-icon-deselect')]//span[contains(text(),'"+correctAnswer+"')]")).click();
            driver.findElement(By.xpath("//td[contains(text(),'"+correctAnswer+"')]")).click();
            preview.checkAnswer_button.click();//click on the check Answer button

            //Expected #1. It should show message as "You got it right"
            Assert.assertEquals(preview.trueNotificationMsg.getText().trim(),"You got it right.","Message  \"You got it right\" is not displaying ");

            //Tc row now 79
            //8. Click on preview button, select the wrong answer and click on "Check answer" button
            preview.select_multipleChoice.get(0).click();//click on the A answer which is incorrect byDefault
            preview.checkAnswer_button.click();//click on the check Answer button

            //Expected #1. It should show message as "You got it wrong"
            Assert.assertEquals(preview.wrongNotificationMsg.getText().trim(),"You got it wrong.","Message  \"You got it right\" is not displaying ");


        } catch (Exception e) {
            Assert.fail("Exception in testcase importTrueFalseAndMultipleChoiceQuestionUsingWPQTI of class ImportQuestionUsingWPQTI,",e);
        }
    }


    @Test(priority = 8,enabled = true)
    public void importAllQuestionTypeUsingWPQTI()
    {
        try {
            //Tc row no 90
            int index=90;
            NewQuestionDataEntry newQuestionDataEntry= PageFactory.initElements(driver,NewQuestionDataEntry.class);
            Preview preview=PageFactory.initElements(driver,Preview.class);

            new DirectLogin().directLoginWithCreditial(email,password,index);
            new Assignment().importQuestionUsingWPQTI(103);
            //Tc row no 15
            //7. Check question created through WPQTI file
            //Expected # 1. Question type should be True and False
            Assert.assertEquals(newQuestionDataEntry.getQuestionType().getText().trim(),"Multiple Choice","Question type is not Multiple Choice");
            newQuestionDataEntry.questionStatus.click();//click on the QA question status
            driver.findElement(By.xpath("//html/body")).click();

            //newQuestionDataEntry.jumpToQuestionSelector.click();//click on jump to question
            new CMSActions().navigateToQuestionNo(6);
            Thread.sleep(5000);
            //Expected 2# 2. Question status should be QA
            Assert.assertEquals(newQuestionDataEntry.getQuestionType().getText().trim(),"True / False","Question type is not True / False");
            newQuestionDataEntry.questionStatus.click();//click on the QA question status
            driver.findElement(By.xpath("//html/body")).click();

            Thread.sleep(5000);
            new CMSActions().navigateToQuestionNo(5);

            newQuestionDataEntry.questionStatus.click();//click on the QA question status
            driver.findElement(By.xpath("//html/body")).click();

            newQuestionDataEntry.preview_link.click(); //click on the preview link
            Thread.sleep(5000);

            for(String child:driver.getWindowHandles())
            {
                driver.switchTo().window(child);
            }

            //Tc row no 42
            //9 Click on preview button, select the correct answer and click on "Check answer" button
            // new DropDown().selectValueByIndex("question-raw-content-dropdown",1);
            driver.findElement(By.xpath("//a[starts-with(@id,'sbToggle_')]")).click();
            driver.findElement(By.xpath("//a[@title='Not a function']")).click();
            preview.checkAnswer_button.click();//click on the check Answer button
            //Expected #1. It should show message as "You got it right"
            Assert.assertEquals(preview.trueNotificationMsg.getText().trim(),"You got it right.","Message  \"You got it right\" is not displaying ");

            //Tc row no 43
            driver.findElement(By.xpath("//a[starts-with(@id,'sbToggle_')]")).click();
            driver.findElement(By.xpath("//a[@title='Function']")).click();
            preview.checkAnswer_button.click();//click on the check Answer button
            Thread.sleep(2000);
            //Expected #1. It should show message as "You got it wrong"
            Assert.assertEquals(preview.wrongNotificationMsg.getText().trim(),"You got it wrong.","Message  \"You got it wrong\" is not displaying ");


        } catch (Exception e) {
            Assert.fail("Exception in testcase importAllQuestionTypeUsingWPQTI of class ImportQuestionUsingWPQTI,",e);
        }
    }


    @Test(priority = 9,enabled = false)
    public void diagnosticAssessmentAllQuestionTypeUsingWPQTI()
    {
        try {
            //Tc row no 103
            int index=103;
            NewQuestionDataEntry newQuestionDataEntry= PageFactory.initElements(driver,NewQuestionDataEntry.class);
            Preview preview=PageFactory.initElements(driver,Preview.class);
            new LoginUsingLTI().ltiLogin("104");//login as student
            new LoginUsingLTI().ltiLogin("103"); //login as instructor
            new DirectLogin().directLoginWithCreditial(email,password,index);
            new Assignment().importQuestionUsingWPQTI(index);
            Thread.sleep(6000);

            newQuestionDataEntry.questionStatus.click();//click on the QA question status
            driver.findElement(By.xpath("//a[@title='Publish']")).click(); //click on the publish
            newQuestionDataEntry.save_button.click();//click on the save button
            new LoginUsingLTI().ltiLogin("104");//login as student

        } catch (Exception e) {
            Assert.fail("Exception in testcase diagnosticAssessmentAllQuestionTypeUsingWPQTI of class ImportQuestionUsingWPQTI,",e);
        }
    }


    public void verifyQuestionType(String type,int dataIndex) throws InterruptedException {
        NewQuestionDataEntry newQuestionDataEntry= PageFactory.initElements(driver,NewQuestionDataEntry.class);
        Preview preview=PageFactory.initElements(driver,Preview.class);
        //Tc row no 15
        //7. Check question created through WPQTI file
        //Expected # 1. Question type should be True and False
        Assert.assertEquals(newQuestionDataEntry.getQuestionType().getText().trim(),type,"Question type is not "+type+"");

        //Expected 2# 2. Question status should be QA
        newQuestionDataEntry.questionStatus.click();//click on the QA question status
        driver.findElement(By.xpath("//html/body")).click();

        //Tc row now 18.
        //8. Click on preview button, select the wrong answer and click on "Check answer" button

        newQuestionDataEntry.preview_link.click(); //click on the preview link

        for(String child:driver.getWindowHandles())
        {
            driver.switchTo().window(child);
        }

        Thread.sleep(5000);
       preview.trueFalseAnswer_label.get(0).click();//click on the A answer which is incorrect byDefault
       preview.checkAnswer_button.click();//click on the check Answer button

       //Expected #1. It should show message as "You got it wrong"
       Assert.assertEquals(preview.wrongNotificationMsg.getText().trim(),"You got it wrong.","Message  \"You got it wrong\" is not displaying ");

        //Tc row no 17
        //9 Click on preview button, select the correct answer and click on "Check answer" button
        Thread.sleep(5000);
        preview.trueFalseAnswer_label.get(0).click();//click on the B answer which is correct byDefault
        preview.checkAnswer_button.click();//click on the check Answer button

        //Expected #1. It should show message as "You got it right"
        Assert.assertEquals(preview.trueNotificationMsg.getText().trim(),"You got it right.","Message  \"You got it right\" is not displaying ");


    }

    public static Map<String,Object> convertJSONStringTOMap(String jsonString){
        return convertJSONStringTOMap(jsonString, false);
    }

    public static Map<String,Object> convertJSONStringTOMap(String jsonString, boolean isSingleQuotedJson){
        if(jsonString == null){
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        if (isSingleQuotedJson) {
            mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        }
        try
        {
            Map<String,Object> map = (Map<String,Object>)mapper.readValue(jsonString, Map.class);
            return map;
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    }






