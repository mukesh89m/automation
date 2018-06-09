package com.snapwiz.selenium.tests.staf.orion.testcases.IT23.R237;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.orion.apphelper.DirectLogin;
import com.snapwiz.selenium.tests.staf.orion.apphelper.SelectCourse;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.CMS.Preview;
import com.snapwiz.selenium.tests.staf.orion.uihelper.BooleanValue;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;

/**
 * Created by mukesh on 6/10/15.
 */
public class LangaugePalette {

    @Test(priority = 1,enabled = true)
    public void  spanishPaletteForAllQuestionType(){
        try {
            Driver.startDriver();
            ManageContent manageContent = PageFactory.initElements(Driver.driver, ManageContent.class);
            Preview preview =PageFactory.initElements(Driver.driver,Preview.class);
            JavascriptExecutor jse=(JavascriptExecutor)Driver.driver;

            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("True / False",1463);
            new WebDriverWait(Driver.driver,60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='question-edit']")));
            //TC row no 14
            manageContent.questionTextBox_orion.get(0).click();//click on question text box
            Assert.assertTrue(manageContent.langaugePalette_orion.isDisplayed(), "Language palette icon is not displaying in text editor popup.");
            new WebDriverWait(Driver.driver,60).until(ExpectedConditions.visibilityOf(manageContent.langaugePalette_orion));
            manageContent.langaugePalette_orion.click();
            Assert.assertTrue(manageContent.spanish_header.isDisplayed(),"Spanish palette is not displayed");
            Assert.assertEquals(manageContent.spanish_header.getText().trim(),"Spanish","Header does not  contain Spanish text.");

            for(WebElement element:manageContent.langauge_input){
                element.click();
            }

            Actions action=new Actions(Driver.driver);
            WebElement from=Driver.driver.findElement(By.id("course-header-name"));
            action.dragAndDrop(manageContent.spanish_header,from).build().perform();

            manageContent.save_button_orion.click(); //click on the save button
            Driver.driver.findElement(By.id("question-editor-outer-wrapper")).click();
            Thread.sleep(4000);
            Assert.assertEquals(manageContent.questionTextBox_orion.get(0).getText().trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the characters is not getting added to the question content area.");

            //Solution
            manageContent.questionTextBox_orion.get(1).click();//click on question text box
            Assert.assertTrue(manageContent.langaugePalette_orion.isDisplayed(), "Language palette icon is not displaying in text editor popup.");
            new WebDriverWait(Driver.driver,60).until(ExpectedConditions.visibilityOf(manageContent.langaugePalette_orion));
            manageContent.langaugePalette_orion.click();
            Assert.assertTrue(manageContent.spanish_header.isDisplayed(),"Spanish palette is not displayed");
            Assert.assertEquals(manageContent.spanish_header.getText().trim(),"Spanish","Header does not  contain Spanish text.");

            for(WebElement element:manageContent.langauge_input){
                element.click();
            }

            manageContent.save_button_orion.click(); //click on the save button
            Driver.driver.findElement(By.id("question-editor-outer-wrapper")).click();

            Thread.sleep(4000);
            Assert.assertEquals(manageContent.questionTextBox_orion.get(1).getText().trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the characters is not getting added to the question content area.");

            //Hint
            manageContent.questionTextBox_orion.get(2).click();//click on question text box
            Assert.assertTrue(manageContent.langaugePalette_orion.isDisplayed(), "Language palette icon is not displaying in text editor popup.");
            new WebDriverWait(Driver.driver,60).until(ExpectedConditions.visibilityOf(manageContent.langaugePalette_orion));
            manageContent.langaugePalette_orion.click();
            Assert.assertTrue(manageContent.spanish_header.isDisplayed(),"Spanish palette is not displayed");
            Assert.assertEquals(manageContent.spanish_header.getText().trim(),"Spanish","Header does not  contain Spanish text.");

            for(WebElement element:manageContent.langauge_input){
                element.click();
            }

            manageContent.save_button_orion.click(); //click on the save button
            Thread.sleep(4000);
            Driver.driver.findElement(By.id("question-editor-outer-wrapper")).click();
            Assert.assertEquals(manageContent.questionTextBox_orion.get(2).getText().trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the characters is not getting added to the question content area.");

            manageContent.questionLabel_orion.get(0).click(); //click on tha A
            manageContent.save_Button.click();
            manageContent.preview_Button.click(); //click on the preview link

            String parentWindow=Driver.driver.getWindowHandle();
            for(String child:Driver.driver.getWindowHandles()){
                Driver.driver.switchTo().window(child);
            }
            Assert.assertEquals(manageContent.questionTextBox_orion.get(0).getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");

            preview.solution_Button.click();
            Assert.assertEquals(preview.solutionContent.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");

            preview.hint_button.click();
            Assert.assertEquals(preview.hintContent.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");

            Driver.driver.close();
            Driver.driver.switchTo().window(parentWindow);

        } catch (Exception e) {
            Assert.fail("Exception in TC spanishPaletteForAllQuestionType of class LangaugePalette",e);
        }

    }

    @Test(priority = 2,enabled = true)
    public void  spanishPaletteForTextEntry(){
        try {
            Driver.startDriver();
            ManageContent manageContent = PageFactory.initElements(Driver.driver, ManageContent.class);
            Preview preview =PageFactory.initElements(Driver.driver,Preview.class);
            JavascriptExecutor jse=(JavascriptExecutor)Driver.driver;

            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment(). selectParticularQuestionInCMS("Text Entry",1476);
            new WebDriverWait(Driver.driver,60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='question-edit']")));
            //TC row no 14

            manageContent.enableSpanish_checkbox.click();//click on the Enable spanish palette

           // manageContent.languagePalette_icon.click();//click on the spanish icon
            manageContent.languagePalette_icon.get(0).click();//click on the spanish icon
            Assert.assertTrue(manageContent.spanish_header.isDisplayed(),"Spanish palette is not displayed");
            Assert.assertEquals(manageContent.spanish_header.getText().trim(),"Spanish","Header does not  contain Spanish text.");

            for(WebElement element:manageContent.langauge_input){
                element.click();
            }

            Actions action=new Actions(Driver.driver);
            WebElement from=Driver.driver.findElement(By.id("course-header-name"));
            action.dragAndDrop(manageContent.spanish_header,from).build().perform();

           // manageContent.languagePalette_icon.click();//click on the spanish icon

            manageContent.languagePalette_icon.get(0).click();//click on the spanish icon
            Assert.assertTrue(manageContent.spanish_header.isDisplayed(),"Spanish palette is not displayed");
            Assert.assertEquals(manageContent.spanish_header.getText().trim(),"Spanish","Header does not  contain Spanish text.");

            for(WebElement element:manageContent.langauge_input){
                element.click();
            }
            manageContent.langaugePalette_saveButton.click(); //click on the save button
            manageContent.accept_answer_TextEntry.click();

            String correctAnswer=Driver.driver.findElement(By.id("1")).getAttribute("renderdata").trim();
            Map<String,Object> convertJSONStringTOMap = convertJSONStringTOMap(correctAnswer);
            Thread.sleep(5000);
            String answer=null;
            answer=convertJSONStringTOMap.get("correctAnswer").toString();
            Assert.assertEquals(answer,"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the characters is not getting added to the question content area.");

            manageContent.alterNativeSpanish_icon.click(); //click on the alternative spanish icon
            Assert.assertEquals(manageContent.alterNative_answer.get(2).getText(),"Alternate Answer","Language palette icon is not displayed in \"Alternet Answer\" text box.");

            manageContent.languagePalette_icon.get(1).click();//click on the spanish icon
            Assert.assertTrue(manageContent.spanish_header.isDisplayed(),"Spanish palette is not displayed");
            Assert.assertEquals(manageContent.spanish_header.getText().trim(),"Spanish","Header does not  contain Spanish text.");

            for(WebElement element:manageContent.langauge_input){
                element.click();
            }
            manageContent.langaugePalette_saveButton.click(); //click on the save button
            manageContent.accept_answer_TextEntry.click();

            String correctAnswer1=Driver.driver.findElement(By.id("1")).getAttribute("renderdata").trim();
            Map<String,Object> convertJSONStringTOMap1 = convertJSONStringTOMap(correctAnswer1);
            Thread.sleep(5000);
            answer=convertJSONStringTOMap1.get("correctAnswer").toString();
            Assert.assertEquals(answer,"JSON::[\"áÁéÉíÍñÑóÓúÚüÜ¡¿\",\"áÁéÉíÍñÑóÓúÚüÜ¡¿\"]","All the characters is not getting added to the question content area.");
            manageContent.save_Button.click();
            manageContent.preview_Button.click(); //click on the preview link

            String parentWindow=Driver.driver.getWindowHandle();
            for(String child:Driver.driver.getWindowHandles()){
                Driver.driver.switchTo().window(child);
            }
            manageContent.languagePalette_icon.get(0).click();//click on the spanish icon
            Assert.assertTrue(manageContent.spanish_header.isDisplayed(),"Spanish palette is not displayed");
            Assert.assertEquals(manageContent.spanish_header.getText().trim(),"Spanish","Header does not  contain Spanish text.");

            for(WebElement element:manageContent.langauge_input){
                element.click();
            }

            Actions action1=new Actions(Driver.driver);
            WebElement from1=Driver.driver.findElement(By.id("question-label"));
            action1.dragAndDrop(manageContent.spanish_header,from1).build().perform();

            manageContent.languagePalette_icon.get(0).click();//click on the spanish icon
            Assert.assertTrue(manageContent.spanish_header.isDisplayed(),"Spanish palette is not displayed");
            Assert.assertEquals(manageContent.spanish_header.getText().trim(),"Spanish","Header does not  contain Spanish text.");

            for(WebElement element:manageContent.langauge_input){
                element.click();
            }
            manageContent.langaugePalette_saveButton.click(); //click on the save button
            String correctAnswer2=Driver.driver.findElement(By.id("1")).getAttribute("renderdata").trim();
            Map<String,Object> convertJSONStringTOMap2 = convertJSONStringTOMap(correctAnswer2);
            Thread.sleep(5000);
            answer=convertJSONStringTOMap2.get("correctAnswer").toString();
            Assert.assertEquals(answer,"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the characters is not getting added to the question content area.");

        } catch (Exception e) {
            Assert.fail("Exception in TC spanishPaletteForTextEntry of class LangaugePalette",e);
        }

    }

    @Test(priority = 3,enabled = true)
    public void  spanishPaletteForEssay(){
        try {
            Driver.startDriver();
            ManageContent manageContent = PageFactory.initElements(Driver.driver, ManageContent.class);
            Preview preview =PageFactory.initElements(Driver.driver,Preview.class);
            JavascriptExecutor jse=(JavascriptExecutor)Driver.driver;
            String assignmentname= ReadTestData.readDataByTagName("", "assessmentname", "1494");

            new DirectLogin().CMSLogin();
          //  new Assignment().create(1494);
            new Assignment().addQuestions(1494, "qtn-essay-type", assignmentname, false, false,null, false);//add question 2
            manageContent.preview_Button.click(); //click on the preview link

            String parentWindow=Driver.driver.getWindowHandle();
            for(String child:Driver.driver.getWindowHandles()){
                Driver.driver.switchTo().window(child);
            }
            manageContent.langaugePalette_orion.click();//click on the spanish icon
            Assert.assertTrue(manageContent.spanish_header.isDisplayed(),"Spanish palette is not displayed");
            Assert.assertEquals(manageContent.spanish_header.getText().trim(),"Spanish","Header does not  contain Spanish text.");

            for(WebElement element:manageContent.langauge_input){
                element.click();
            }

            Actions action1=new Actions(Driver.driver);
            WebElement from1=Driver.driver.findElement(By.id("question-label"));
            action1.dragAndDrop(manageContent.spanish_header,from1).build().perform();

            manageContent.langaugePalette_orion.click();//click on the spanish icon
            Assert.assertTrue(manageContent.spanish_header.isDisplayed(),"Spanish palette is not displayed");
            Assert.assertEquals(manageContent.spanish_header.getText().trim(),"Spanish","Header does not  contain Spanish text.");

            for(WebElement element:manageContent.langauge_input){
                element.click();
            }
            manageContent.langaugePalette_saveButton.click(); //click on the save button
            Driver.driver.switchTo().frame(0);
            String text=Driver.driver.findElement(By.xpath("//html/body")).getText().trim();
            Assert.assertEquals(text,"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the characters is not getting added to the question content area.");


        } catch (Exception e) {
            Assert.fail("Exception in TC spanishPaletteForEssay of class LangaugePalette",e);
        }

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


