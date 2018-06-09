package com.snapwiz.selenium.tests.staf.orion.testcases.IT23.R237;

import com.snapwiz.selenium.tests.staf.orion.Config;
import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.ReadTestData;
import com.snapwiz.selenium.tests.staf.orion.apphelper.*;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.CMS.CourseOutline;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.orion.pagefactory.CMS.Preview;
import com.snapwiz.selenium.tests.staf.orion.uihelper.Click;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by priyanka on 10/7/2015.
 */
public class InstructorPreviewPage {

    @Test(priority = 1,enabled = true)
    public void  instructorPreviewPage(){

        try {
            //TC ROW NO 1502
            Driver.startDriver();
            ManageContent manageContent = PageFactory.initElements(Driver.driver, ManageContent.class);
            Preview preview =PageFactory.initElements(Driver.driver,Preview.class);
            JavascriptExecutor jse=(JavascriptExecutor)Driver.driver;

            String assignmentname=ReadTestData.readDataByTagName("", "assessmentname", "1502");
            String chapterName = ReadTestData.readDataByTagName("", "newChapterName", Integer.toString(1502));
            String course = Config.course;

            CourseOutline courseOutline=PageFactory.initElements(Driver.driver, CourseOutline.class);
            WebDriverWait wait=new WebDriverWait(Driver.driver,200);

            new Assignment().createChapter(1502);

            new DirectLogin().CMSLogin();//login in cms
            Driver.driver.findElement(By.cssSelector("img[alt=\""+course+"\"]")).click();

            Thread.sleep(2000);
            Driver.driver.findElement(By.id("manage-toc")).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@title,'Add New Chapter')]")));

            List<WebElement> allChapters = Driver.driver.findElements(By.xpath("/*//*[starts-with(@class, 'course-chapter-label node course-chapter-label-unpublished')]"));
            for (WebElement chapters : allChapters) {
                if (chapters.getText().contains(chapterName)) {
                    Locatable hoverItem = (Locatable) chapters;
                    Mouse mouse = ((HasInputDevices) Driver.driver).getMouse();
                    mouse.mouseMove(hoverItem.getCoordinates());
                }
            }
            Thread.sleep(500);
            courseOutline.editButtonAtChapter.click();
            Thread.sleep(500);
            courseOutline.checkBoxToPublishChapter.click();//click on publish
            courseOutline.saveButton.click();//click on save
            Thread.sleep(5000);
            courseOutline.saveMyChanges.click();
            Thread.sleep(2000);
            new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Text Entry",1476);
            new WebDriverWait(Driver.driver,60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='question-edit']")));
            //TC row no 14

            manageContent.enableSpanish_checkbox.click();//click on the Enable spanish palette
            manageContent.languagePalette_icon.get(0).click();//click on the spanish icon
            for(WebElement element:manageContent.langauge_input){
                element.click();
            }
            manageContent.langaugePalette_saveButton.click(); //click on the save button
            manageContent.accept_answer_TextEntry.click(); //publish remaining
            manageContent.save_Button.click();
           new LoginUsingLTI().ltiLogin("1502"); //login as instructor
           new Click().clickByid("idb-switch-role-to-student");
           List<WebElement> allElement=  Driver.driver.findElements(By.xpath("//img[@alt='Begin Diagnostic']"));
            allElement.get(allElement.size()-1).click();
            new Click().clickByid("2");
            new Click().clickBycssselector("div[title='Continue']");


            //TC row no 14
            manageContent.languagePalette_icon.get(0).click();//click on the spanish icon
            Assert.assertTrue(manageContent.spanish_header.isDisplayed(),"Spanish palette is not displayed");
            Assert.assertEquals(manageContent.spanish_header.getText().trim(),"Spanish","Header does not  contain Spanish text.");

            for(WebElement element:manageContent.langauge_input){
                element.click();
            }

            Actions action=new Actions(Driver.driver);
            WebElement from=Driver.driver.findElement(By.className("al-diag-test-question-label"));
            action.dragAndDrop(manageContent.spanish_header,from).build().perform();

            manageContent.languagePalette_icon.get(0).click();//click on the spanish icon
            for(WebElement element:manageContent.langauge_input){
                element.click();
            }
            manageContent.langaugePalette_saveButton.click(); //click on the save button
            String correctAnswer=Driver.driver.findElement(By.id("1")).getAttribute("renderdata").trim();
            Map<String,Object> convertJSONStringTOMap = convertJSONStringTOMap(correctAnswer);
            Thread.sleep(5000);
            String answer=null;
            answer=convertJSONStringTOMap.get("correctAnswer").toString();
            Assert.assertEquals(answer,"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the characters is not getting added to the question content area.");

        } catch (Exception e) {
            Assert.fail("Exception in TC instructorPreviewPage of class InstructorPreviewPage",e);
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
