package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT23.R237;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.CMS.ManageContent;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by priyanka on 10/6/2015.
 */
public class LangaugePaletteForQuestionCreationForLs extends Driver {

    int count=2;
    @DataProvider(name = "questionType")
    public Object[][] getQuestionTypes() {
        return new Object[][] {
                { "True / False" },
                { "Multiple Choice"},
                { "Multiple Selection"},
                { "Text Entry" },
                { "Text Drop Down"},
                { "Numeric Entry w/Units"},
                { "Advanced Numeric" },
                { "Expression Evaluator"},
                { "Essay Type Question"},
                { "Workboard" },
                { "Audio"},
                { "Match the following"},
                { "Drag and Drop" },
                { "Label an image (Text)"},
                { "Label an image (Dropdown)"},
                { "Multi Part"},
        };
    }


    @Test(priority = 1,enabled = true,dataProvider = "questionType")
    public void langaugePaletteForQuestionCreation(String QuestionType){
        try {
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLsCourse();//select course biology
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS(QuestionType, 13);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='question-edit']/div/div[1]")));
            verificationForSpanish();
            verificationForItalian();
            verificationForFrench();



        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForQuestionCreation of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 2,enabled = false)
    public void langaugePaletteForAllQuestion(){
        try {
            new Assignment().create(54);
            new Assignment().addQuestions(54,"all","");


        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForAllQuestion of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void langaugePaletteForSolutionContentArea(){
        try {
            //TC row no 57
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            int dataIndex=57;
            int dataIndex1=71;
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLsCourse();//select course biology
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("True / False", 57);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='question-edit']/div/div[1]")));
            manageContent.contentSolution.sendKeys("content area");
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.contentSolution);
            clickOnLanguagePalette();
            Thread.sleep(2000);
            contentVerificationForSpanish(dataIndex);
            contentVerificationForItalian(dataIndex);
            contentVerificationForFrench(dataIndex);

            //TC row no 72
            Thread.sleep(4000);
            manageContent.contentHint.sendKeys("content Hint");
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.contentHint);
            clickOnLanguagePalette();
            contentVerificationForSpanish(dataIndex1);
            contentVerificationForItalian(dataIndex1);
            contentVerificationForFrench(dataIndex1);


        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForSolutionContentArea of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 4,enabled = true)
    public void langaugePaletteForTextEntryQuestion(){
        try {
            //TC row no 88
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLsCourse();//select course biology
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Text Entry", 57);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='question-edit']/div/div[1]")));
            Assert.assertEquals(new BooleanValue().presenceOfElement(88, By.linkText("Enable Spanish Palette")), false, "Enable Spanish Palette is still displaying");
            new Click().clickBycssselector("a[title='Select Language Palette']"); //select language
            Assert.assertEquals(manageContent.selectLangaugePalete_TextEntry.get(1).getAttribute("rel"), "spanish");
            Assert.assertEquals(manageContent.selectLangaugePalete_TextEntry.get(2).getAttribute("rel"),"italian");
            Assert.assertEquals(manageContent.selectLangaugePalete_TextEntry.get(3).getAttribute("rel"),"french");

            //TC row no 92
            manageContent.selectLangaugePalete_TextEntry.get(1).click(); //click on the spanish
            new Click().clickByclassname("spanish-popup"); //click on the spanish icon


            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"Spanish palette popup is not opened");

            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);

            //Assert.assertEquals(inputStringValue.length(),16,"All the characters is not supported as existing Functionality.");

            //Tc row no 17
            Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

            //Tc row no 18
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
            //Tc row no 19
            Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
            Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

            //TC row no 20
            Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            //TC row no 21
            // Assert.assertEquals(inputStringValue,"áÁéÉíÍñÑóÓúÚüÜ¡¿","The selected characters is not displayed in the text box of the Palette. ");
            //TC row no 22
            manageContent.langaugePalette_saveButton.click(); //click on the save button
            manageContent.accept_answer.click(); //click on the accept answer;


            //TC row no 23
            Assert.assertEquals(manageContent.textEntryQuestionContent.getAttribute("value").trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the characters is not getting added to the question content area.");

            //TC row no 24
            new Click().clickByclassname("spanish-popup"); //click on the spanish icon
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
                element.click();
                element.click();
                element.click();
            }
            //TC row no 25
            manageContent.langaugePalette_cancelButton.click();//click on the cancel button

            Assert.assertEquals(manageContent.langaugePalette_closeIcon.isDisplayed(),false,"The Editor is not getting closed.");
            Assert.assertEquals(manageContent.textEntryQuestionContent.getAttribute("value").trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿","The Question content area is getting saved be saved with language content.");
            //TC row no 26
            int width=Integer.parseInt(manageContent.textEntryQuestionContent.getCssValue("width").substring(0,2));

            new Click().clickByclassname("spanish-popup"); //click on the spanish icon
            manageContent.langaugePalette_closeIcon.click();//click on the close icon
            Assert.assertEquals(manageContent.langaugePalette_closeIcon.isDisplayed(), false, "The Editor is not getting closed.");

            new Click().clickByclassname("spanish-popup"); //click on the spanish icon
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
                element.click();
                element.click();
                element.click();
            }

            manageContent.langaugePalette_saveButton.click(); //click on the save button
            manageContent.accept_answer.click(); //click on the accept answer;
            int width1=Integer.parseInt(manageContent.textEntryQuestionContent.getCssValue("width").substring(0,2));

            //Tc row no 106
            if(width>width1){
                Assert.fail("The width of the question textbox is not  increased accordingly");
            }
            Assert.assertEquals(manageContent.textEntryQuestionContent.getAttribute("value").length(),80,"All the characters is not supported as existing Functionality.");

            //TC row no 117
            new Click().clickByclassname("spanish-popup"); //click on the spanish icon
            manageContent.langaugePalette_cancelButton.click();//click on the cancel button
            Assert.assertEquals(manageContent.langaugePalette_closeIcon.isDisplayed(),false,"The Spanish Editor is not getting closed.");

        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForTextEntryQuestion of class LangaugePaletteForQuestionCreation", e);
        }
    }


    @Test(priority = 5,enabled = true)
    public void langaugePaletteIntalinForTextEntryQuestion(){
        try {
            //TC row no 88
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLsCourse();//select course biology
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Text Entry", 57);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='question-edit']/div/div[1]")));
            Assert.assertEquals(new BooleanValue().presenceOfElement(88, By.linkText("Enable Spanish Palette")), false, "Enable Spanish Palette is still displaying");

            new Click().clickBycssselector("a[title='Select Language Palette']"); //select language
            Assert.assertEquals(manageContent.selectLangaugePalete_TextEntry.get(1).getAttribute("rel"), "spanish");
            Assert.assertEquals(manageContent.selectLangaugePalete_TextEntry.get(2).getAttribute("rel"),"italian");
            Assert.assertEquals(manageContent.selectLangaugePalete_TextEntry.get(3).getAttribute("rel"),"french");

            //TC row no 92
            manageContent.selectLangaugePalete_TextEntry.get(2).click(); //click on the italian
            new Click().clickByclassname("spanish-popup"); //click on the spanish icon


            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"Spanish palette popup is not opened");

            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);

            //Assert.assertEquals(inputStringValue.length(),16,"All the characters is not supported as existing Functionality.");

            //Tc row no 17
            Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

            //Tc row no 18
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
            //Tc row no 19
            Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
            Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

            //TC row no 20
            Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Italian Editor", "The popup is not having text has \"Italian Editor\" text over header, with Left aligned.");

            //TC row no 21
            // Assert.assertEquals(inputStringValue,"áÁéÉíÍñÑóÓúÚüÜ¡¿","The selected characters is not displayed in the text box of the Palette. ");
            //TC row no 22
            manageContent.langaugePalette_saveButton.click(); //click on the save button
            manageContent.accept_answer.click(); //click on the accept answer;


            //TC row no 23
            Assert.assertEquals(manageContent.textEntryQuestionContent.getAttribute("value"),"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤","All the characters is not getting added to the question content area.");

            //TC row no 24
            new Click().clickByclassname("spanish-popup"); //click on the spanish icon

            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
                element.click();
                element.click();
                element.click();
            }
            //TC row no 25
            manageContent.langaugePalette_cancelButton.click();//click on the cancel button

            Assert.assertEquals(manageContent.langaugePalette_closeIcon.isDisplayed(),false,"The Editor is not getting closed.");
            Assert.assertEquals(manageContent.textEntryQuestionContent.getAttribute("value"),"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤","All the characters is not getting added to the question content area.");
            int width=Integer.parseInt(manageContent.textEntryQuestionContent.getCssValue("width").substring(0,2));

            //TC row no 26
            new Click().clickByclassname("spanish-popup"); //click on the spanish icon
            manageContent.langaugePalette_closeIcon.click();//click on the close icon
            Assert.assertEquals(manageContent.langaugePalette_closeIcon.isDisplayed(), false, "The Editor is not getting closed.");

            new Click().clickByclassname("spanish-popup"); //click on the spanish icon
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
                element.click();

            }

            manageContent.langaugePalette_saveButton.click(); //click on the save button
            manageContent.accept_answer.click(); //click on the accept answer;

            //Tc row no 106
            int width1=Integer.parseInt(manageContent.textEntryQuestionContent.getCssValue("width").substring(0,2));

            if(width>width1){
                Assert.fail("The width of the question textbox is not  increased accordingly");
            }

            //TC row no 117
            new Click().clickByclassname("spanish-popup"); //click on the spanish icon
            manageContent.langaugePalette_cancelButton.click();//click on the cancel button
            Assert.assertEquals(manageContent.langaugePalette_closeIcon.isDisplayed(),false,"The Spanish Editor is not getting closed.");

        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteIntalinForTextEntryQuestion of class LangaugePaletteForQuestionCreation", e);
        }
    }


    @Test(priority = 6,enabled = true)
    public void langaugePaletteFrenchForTextEntryQuestion(){
        try {
            //TC row no 88
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLsCourse();//select course biology
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Text Entry", 57);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='question-edit']/div/div[1]")));
            Assert.assertEquals(new BooleanValue().presenceOfElement(88, By.linkText("Enable Spanish Palette")), false, "Enable Spanish Palette is still displaying");

            new Click().clickBycssselector("a[title='Select Language Palette']"); //select language
            Assert.assertEquals(manageContent.selectLangaugePalete_TextEntry.get(1).getAttribute("rel"), "spanish");
            Assert.assertEquals(manageContent.selectLangaugePalete_TextEntry.get(2).getAttribute("rel"),"italian");
            Assert.assertEquals(manageContent.selectLangaugePalete_TextEntry.get(3).getAttribute("rel"),"french");

            //TC row no 92
            manageContent.selectLangaugePalete_TextEntry.get(3).click(); //click on the italian
            new Click().clickByclassname("spanish-popup"); //click on the spanish icon


            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"Spanish palette popup is not opened");

            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);

            //Assert.assertEquals(inputStringValue.length(),16,"All the characters is not supported as existing Functionality.");

            //Tc row no 17
            Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

            //Tc row no 18
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
            //Tc row no 19
            Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
            Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

            //TC row no 20
            Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "French Editor", "The popup is not having text has \"Italian Editor\" text over header, with Left aligned.");

            //TC row no 21
            // Assert.assertEquals(inputStringValue,"áÁéÉíÍñÑóÓúÚüÜ¡¿","The selected characters is not displayed in the text box of the Palette. ");
            //TC row no 22
            manageContent.langaugePalette_saveButton.click(); //click on the save button
            manageContent.accept_answer.click(); //click on the accept answer;


            //TC row no 23
            Assert.assertEquals(manageContent.textEntryQuestionContent.getAttribute("value"),"ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ","All the characters is not getting added to the question content area.");

            //TC row no 24
            new Click().clickByclassname("spanish-popup"); //click on the spanish icon

            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
                element.click();
                element.click();
                element.click();
            }
            //TC row no 25
            manageContent.langaugePalette_cancelButton.click();//click on the cancel button

            Assert.assertEquals(manageContent.langaugePalette_closeIcon.isDisplayed(),false,"The Editor is not getting closed.");
            Assert.assertEquals(manageContent.textEntryQuestionContent.getAttribute("value"),"ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ","All the characters is not getting added to the question content area.");
            int width=Integer.parseInt(manageContent.textEntryQuestionContent.getCssValue("width").substring(0,2));

            //TC row no 26
            new Click().clickByclassname("spanish-popup"); //click on the spanish icon
            manageContent.langaugePalette_closeIcon.click();//click on the close icon
            Assert.assertEquals(manageContent.langaugePalette_closeIcon.isDisplayed(), false, "The Editor is not getting closed.");

            new Click().clickByclassname("spanish-popup"); //click on the spanish icon
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
                element.click();

            }

            manageContent.langaugePalette_saveButton.click(); //click on the save button
            manageContent.accept_answer.click(); //click on the accept answer;

            //Tc row no 106
            int width1=Integer.parseInt(manageContent.textEntryQuestionContent.getCssValue("width").substring(0,2));

            if(width>width1){
                Assert.fail("The width of the question textbox is not  increased accordingly");
            }

            //TC row no 117
            new Click().clickByclassname("spanish-popup"); //click on the spanish icon
            manageContent.langaugePalette_cancelButton.click();//click on the cancel button
            Assert.assertEquals(manageContent.langaugePalette_closeIcon.isDisplayed(),false,"The Spanish Editor is not getting closed.");

        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteFrenchForTextEntryQuestion of class LangaugePaletteForQuestionCreation", e);
        }
    }


    @Test(priority = 7,enabled = true)
    public void langaugePaletteSpanishForMultipleChoice(){
        try {
            //TC row no 111
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLsCourse();//select course biology
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Multiple Choice", 57);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='question-edit']/div/div[1]")));
            checkForMultipleChoiceAndMultipleSelection(111,"mchoice");
        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteSpanishForMultipleChoice of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 8,enabled = true)
    public void langaugePaletteFrenchForMultipleChoice(){
        try {
            //TC row no 111
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLsCourse();//select course biology
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Multiple Choice", 57);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='question-edit']/div/div[1]")));
            checkForMultipleChoiceAndMultipleSelection(113,"mchoice");

        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteFrenchForMultipleChoice of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 9,enabled = true)
    public void langaugePaletteItalianForMultipleChoice(){
        try {
            //TC row no 111
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLsCourse();//select course biology
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Multiple Choice", 57);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='question-edit']/div/div[1]")));
            checkForMultipleChoiceAndMultipleSelection(112,"mchoice");

        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteItalianForMultipleChoice of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 10,enabled = true)
    public void langaugePaletteSpanishForMultipleSelection(){
        try {
            //TC row no 111
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLsCourse();//select course biology
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Multiple Selection", 57);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='question-edit']/div/div[1]")));
            checkForMultipleChoiceAndMultipleSelection(111,"mselection");
        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteSpanishForMultipleSelection of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 11,enabled = true)
    public void langaugePaletteFrenchForMultipleSelection(){
        try {
            //TC row no 111
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLsCourse();//select course biology
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Multiple Selection", 57);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='question-edit']/div/div[1]")));
            checkForMultipleChoiceAndMultipleSelection(113,"mselection");

        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteFrenchForMultipleSelection of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 12,enabled = true)
    public void langaugePaletteItalianForMultipleSelection(){
        try {
            //TC row no 111
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new DirectLogin().CMSLogin();//login in cms
            new SelectCourse().selectLsCourse();//select course biology
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Multiple Selection", 57);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='question-edit']/div/div[1]")));
            checkForMultipleChoiceAndMultipleSelection(112,"mselection");

        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteItalianForMultipleSelection of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 13,enabled = true)
    public void langaugePaletteItalianForDuplicateQuestion(){
        try {
            //TC row no 131
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new Assignment().create(131);
            new Click().clickByid("questionOptions");
            new Click().clickByid("copyQuestion");
            manageContent.questionTextBox.clear();
            verificationForSpanish();
            Thread.sleep(3000);
            verificationForItalian();
            Thread.sleep(3000);
            verificationForFrench();


        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteItalianForDuplicateQuestion of class LangaugePaletteForQuestionCreation", e);
        }
    }


    @Test(priority = 14,enabled = true)
    public void langaugePaletteItalianForNewVersion(){
        try {
            //TC row no 150
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new Assignment().create(150);
            new Click().clickByid("questionOptions"); //click on question option
            new Click().clickByid("questionRevisions"); // click on revisions
            new Click().clickByid("cms-question-revision-new-version-link");//click on the create new Version
            manageContent.questionTextBox.clear();
            verificationForSpanish();
            Thread.sleep(3000);
            verificationForItalian();
            Thread.sleep(3000);
            verificationForFrench();

        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteItalianForNewVersion of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 15,enabled = true)
    public void langaugePaletteItalianForGetDiff(){
        try {
            //TC row no 152
            new Assignment().create(152);
            varificationForGetDiffForAllQuestion();

            new Assignment().addQuestions(152,"multiplechoice","");
            varificationForGetDiffForAllQuestion();

            new Assignment().addQuestions(152,"multipleselection","");
            varificationForGetDiffForAllQuestion();

            new Assignment().addQuestions(152,"textentry","");
            varificationForGetDiffForAllQuestion();

            new Assignment().addQuestions(152,"multiplechoice","");
            varificationForGetDiffForAllQuestion();

            new Assignment().addQuestions(152,"numericentrywithunits","");
            varificationForGetDiffForAllQuestion();

            new Assignment().addQuestions(152,"advancednumeric","");
            varificationForGetDiffForAllQuestion();

            new Assignment().addQuestions(152,"expressionevaluator","");
            varificationForGetDiffForAllQuestion();

            new Assignment().addQuestions(152,"essay","");
            varificationForGetDiffForAllQuestion();

            new Assignment().addQuestions(152,"audio","");
            varificationForGetDiffForAllQuestion();

            new Assignment().addQuestions(152,"multipart","");
            varificationForGetDiffForAllQuestion();

            new Assignment().addQuestions(152,"draganddrop","");
            varificationForGetDiffForAllQuestion();

            new Assignment().addQuestions(152,"matchthefollowing","");
            varificationForGetDiffForAllQuestion();

            new Assignment().addQuestions(152,"labelAnImageText","");
            varificationForGetDiffForAllQuestion();

            new Assignment().addQuestions(152,"labelAnImageDropdown","");
            varificationForGetDiffForAllQuestion();



        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteItalianForGetDiff of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 16,enabled = true)
    public void langaugePaletteForSearchForSpanish(){
        try {
            //TC row no 161
            int dataIndex=161;
            new Assignment().create(161);
            new Assignment().addQuestions(161,"all","");
            varificationForSearchAndBrowse("True False ",dataIndex);
            varificationForSearchAndBrowse("Multiple Choice ",dataIndex);
            varificationForBrowse("Text Entry ",dataIndex);
            varificationForBrowse("Text Drop Down ",dataIndex);
            varificationForBrowse("Numeric Entry With Units ",dataIndex);
            varificationForBrowse("Advanced Numeric ",dataIndex);
            varificationForBrowse("Expression Evaluator ",dataIndex);
            varificationForSearchAndBrowse("Match the Following ",dataIndex);
            varificationForSearchAndBrowse("Drag and Drop ",dataIndex);
            varificationForSearchAndBrowse("Multi Selection ",dataIndex);
            varificationForSearchAndBrowse("Essay ",dataIndex);
            varificationForSearchAndBrowse("Write Board ",dataIndex);
            varificationForSearchAndBrowse("audio ",dataIndex);
            varificationForSearchAndBrowse("Label An Image(Text) question text",dataIndex);
            varificationForSearchAndBrowse("Label An Image(dropdown)",dataIndex);

        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForSearchForSpanish of class LangaugePaletteForQuestionCreation", e);
        }
    }
    @Test(priority = 17,enabled = true)
    public void langaugePaletteForSearchForItalian(){
        try {
            //TC row no 161
            int dataIndex=162;
            new Assignment().create(162);
            new Assignment().addQuestions(162,"all","");
            varificationForSearchAndBrowse("True False ",dataIndex);
            varificationForSearchAndBrowse("Multiple Choice ",dataIndex);
            varificationForBrowse("Text Entry ", dataIndex);
            varificationForBrowse("Text Drop Down ", dataIndex);
            varificationForBrowse("Numeric Entry With Units ", dataIndex);
            varificationForBrowse("Advanced Numeric ", dataIndex);
            varificationForBrowse("Expression Evaluator ", dataIndex);
            varificationForSearchAndBrowse("Match the Following ",dataIndex);
            varificationForSearchAndBrowse("Drag and Drop ",dataIndex);
            varificationForSearchAndBrowse("Multi Selection ",dataIndex);
            varificationForSearchAndBrowse("Essay ",dataIndex);
            varificationForSearchAndBrowse("Write Board ",dataIndex);
            varificationForSearchAndBrowse("audio ",dataIndex);
            varificationForSearchAndBrowse("Label An Image(Text) question text",dataIndex);
            varificationForSearchAndBrowse("Label An Image(dropdown)",dataIndex);


        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForSearchForItalian of class LangaugePaletteForQuestionCreation", e);
        }
    }
    @Test(priority = 18,enabled = true)
    public void langaugePaletteForSearchForFrench(){
        try {
            //TC row no 161
            int dataIndex=163;
            new Assignment().create(163);
            new Assignment().addQuestions(163,"all","");
            varificationForSearchAndBrowse("True False ",dataIndex);
            varificationForSearchAndBrowse("Multiple Choice ",dataIndex);
            varificationForBrowse("Text Entry ", dataIndex);
            varificationForBrowse("Text Drop Down ", dataIndex);
            varificationForBrowse("Numeric Entry With Units ", dataIndex);
            varificationForBrowse("Advanced Numeric ", dataIndex);
            varificationForBrowse("Expression Evaluator ", dataIndex);
            varificationForSearchAndBrowse("Match the Following ",dataIndex);
            varificationForSearchAndBrowse("Drag and Drop ",dataIndex);
            varificationForSearchAndBrowse("Multi Selection ",dataIndex);
            varificationForSearchAndBrowse("Essay ",dataIndex);
            varificationForSearchAndBrowse("Write Board ",dataIndex);
            varificationForSearchAndBrowse("audio ",dataIndex);
            varificationForSearchAndBrowse("Label An Image(Text) question text",dataIndex);
            varificationForSearchAndBrowse("Label An Image(dropdown)",dataIndex);

        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForSearchForFrench of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @DataProvider(name = "questionTypes")
    public Object[][] getQuestionType() {
        return new Object[][] {
                { "True / False","True False " },
                { "Multiple Choice","Multiple Choice "},
                { "Multiple Selection","Multi Selection "},
                { "Text Entry","Text Entry " },
                { "Text Drop Down","Text Drop Down "},
                { "Numeric Entry w/Units","Numeric Entry With Units "},
                { "Advanced Numeric","Advanced Numeric "},
                { "Expression Evaluator","Expression Evaluator "},
                { "Essay Type Question","Essay "},
                { "Workboard","Write Board " },
                { "Audio","audio "},
                { "Match the following","Match the Following "},
                { "Drag and Drop","Drag and Drop " },
                { "Label an image (Text)","Label An Image(Text) question text"},
                { "Label an image (Dropdown)","Label An Image(dropdown)"},
        };
    }

    @Test(priority = 19,enabled = true)
    public void langaugePaletteForBrowseForSpanishTrueFalse(){
        try {
            //TC row no 161
            int dataIndex=161;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

            String QuestionType="True False ";
            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLS("True / False",dataIndex);
            Thread.sleep(3000);
            Actions action2 = new Actions(driver);
            List<WebElement> we2 = manageContent.checkboxes;
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the firsr question
            Thread.sleep(2000);
            manageContent.quickPreview.get(manageContent.quickPreview.size()-1).click(); //click on the quickPreview link
            new UIElement().waitAndFindElement(manageContent.QuestionContents.get(0));
            String questionContent=manageContent.QuestionContents.get(0).getText().trim();

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionContent.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionContent.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    System.out.println(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿");
                    Assert.assertTrue(questionContent.contains(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿"),"data added over Question Editor is not displaying");

                }
            }

            List<WebElement>edit =manageContent.edit_Link;
            Thread.sleep(3000);
            edit.get(edit.size()-1).click();//click on the edit Button
            new UIElement().waitAndFindElement(manageContent.questionSet_title);
            Assert.assertTrue(manageContent.questionSet_title.isDisplayed());
            Assert.assertTrue(manageContent.assessment_title.isDisplayed());
            String questionName=manageContent.questionTextBox.getText().trim();
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionName.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    Assert.assertTrue(questionName.contains(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿"),"data added over Question Editor is not displaying");

                }
            }


        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForBrowseForSpanishTrueFalse of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 20,enabled = true)
    public void langaugePaletteForBrowseForSpanishEssayType(){
        try {
            //TC row no 161
            int dataIndex=161;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String QuestionType="Essay ";
            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLS("Essay Type", dataIndex);

            Actions action2 = new Actions(driver);
            List<WebElement> we2 = manageContent.checkboxes;
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the firsr question
            Thread.sleep(2000);
            manageContent.quickPreview.get(manageContent.quickPreview.size()-1).click(); //click on the quickPreview link
            new UIElement().waitAndFindElement(manageContent.QuestionContents.get(0));
            String questionContent=manageContent.QuestionContents.get(0).getText().trim();

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionContent.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionContent.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    System.out.println(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿");
                    Assert.assertTrue(questionContent.contains(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿"),"data added over Question Editor is not displaying");
                }
            }

            new ScrollElement().scrollBottomOfPage();
            List<WebElement>edit =manageContent.edit_Link;
            Thread.sleep(3000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",edit.get(edit.size()-1));
            new UIElement().waitAndFindElement(manageContent.questionSet_title);
            Assert.assertTrue(manageContent.questionSet_title.isDisplayed());
            Assert.assertTrue(manageContent.assessment_title.isDisplayed());
            String questionName=manageContent.questionTextBox.getText().trim();
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionContent.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    Assert.assertTrue(questionContent.contains(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿"),"data added over Question Editor is not displaying");
                }
            }


        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForBrowseForSpanishEssayType of class LangaugePaletteForQuestionCreation", e);
        }
    }
    @Test(priority = 21,enabled = true)
    public void langaugePaletteForBrowseForSpanishWorkboard(){
        try {
            //TC row no 161
            int dataIndex=161;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

            String QuestionType="Write Board ";
            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLS("Workboard", dataIndex);

            Actions action2 = new Actions(driver);
            List<WebElement> we2 = manageContent.checkboxes;
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the firsr question
            Thread.sleep(2000);
            manageContent.quickPreview.get(manageContent.quickPreview.size()-1).click(); //click on the quickPreview link
            new UIElement().waitAndFindElement(manageContent.QuestionContents.get(0));
            String questionContent=manageContent.QuestionContents.get(0).getText().trim();

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionContent.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionContent.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    System.out.println(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿");
                    Assert.assertTrue(questionContent.contains(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿"),"data added over Question Editor is not displaying");

                }
            }

            new ScrollElement().scrollBottomOfPage();
            List<WebElement>edit =manageContent.edit_Link;
            Thread.sleep(3000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",edit.get(edit.size()-1));
            new UIElement().waitAndFindElement(manageContent.questionSet_title);
            Assert.assertTrue(manageContent.questionSet_title.isDisplayed());
            Assert.assertTrue(manageContent.assessment_title.isDisplayed());
            String questionName=manageContent.questionTextBox.getText().trim();
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionName.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    Assert.assertTrue(questionContent.contains(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿"),"data added over Question Editor is not displaying");

                }
            }


        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForBrowseForSpanishWorkboard of class LangaugePaletteForQuestionCreation", e);
        }
    }
    @Test(priority = 22,enabled = true)
    public void langaugePaletteForBrowseForSpanishaudio(){
        try {
            //TC row no 161
            int dataIndex=161;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

            String QuestionType="audio ";
            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLS("Audio Recorder",dataIndex);


            Actions action2 = new Actions(driver);
            List<WebElement> we2 = manageContent.checkboxes;
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the firsr question
            Thread.sleep(2000);
            manageContent.quickPreview.get(manageContent.quickPreview.size()-1).click(); //click on the quickPreview link
            new UIElement().waitAndFindElement(manageContent.QuestionContents.get(0));
            String questionContent=manageContent.QuestionContents.get(0).getText().trim();

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionContent.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionContent.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    System.out.println(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿");
                    Assert.assertTrue(questionContent.contains(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿"),"data added over Question Editor is not displaying");
                }
            }

            new ScrollElement().scrollBottomOfPage();
            List<WebElement>edit =manageContent.edit_Link;
            Thread.sleep(3000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",edit.get(edit.size()-1));
            new UIElement().waitAndFindElement(manageContent.questionSet_title);
            Assert.assertTrue(manageContent.questionSet_title.isDisplayed());
            Assert.assertTrue(manageContent.assessment_title.isDisplayed());
            String questionName=manageContent.questionTextBox.getText().trim();
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionName.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    Assert.assertTrue(questionName.contains(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿"),"data added over Question Editor is not displaying");
                }
            }


        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForBrowseForSpanishaudio of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 23,enabled = true)
    public void langaugePaletteForBrowseForSpanishMatchthefollowing(){
        try {
            //TC row no 161
            int dataIndex=161;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

            String QuestionType="Match the Following ";
            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLS("Match the following",dataIndex);


            Actions action2 = new Actions(driver);
            List<WebElement> we2 = manageContent.checkboxes;
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the firsr question
            Thread.sleep(2000);
            manageContent.quickPreview.get(manageContent.quickPreview.size()-1).click(); //click on the quickPreview link
            new UIElement().waitAndFindElement(manageContent.QuestionContents.get(0));
            String questionContent=manageContent.QuestionContents.get(0).getText().trim();

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionContent.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionContent.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    System.out.println(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿");
                    Assert.assertTrue(questionContent.contains(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿"),"data added over Question Editor is not displaying");
                }
            }

            new ScrollElement().scrollBottomOfPage();
            List<WebElement>edit =manageContent.edit_Link;
            Thread.sleep(3000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",edit.get(edit.size()-1));
            new UIElement().waitAndFindElement(manageContent.questionSet_title);
            Assert.assertTrue(manageContent.questionSet_title.isDisplayed());
            Assert.assertTrue(manageContent.assessment_title.isDisplayed());
            String questionName=manageContent.questionTextBox.getText().trim();
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionName.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    Assert.assertTrue(questionName.contains(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿"),"data added over Question Editor is not displaying");
                }
            }


        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForBrowseForSpanishMatchthefollowing of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 24,enabled = true)
    public void langaugePaletteForBrowseForSpanishDragandDrop(){
        try {
            //TC row no 161
            int dataIndex=161;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

            String QuestionType="Drag and Drop ";
            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLS("Drag and Drop",dataIndex);


            Actions action2 = new Actions(driver);
            List<WebElement> we2 = manageContent.checkboxes;
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the firsr question
            Thread.sleep(2000);
            manageContent.quickPreview.get(manageContent.quickPreview.size()-1).click(); //click on the quickPreview link
            new UIElement().waitAndFindElement(manageContent.QuestionContents.get(0));
            String questionContent=manageContent.QuestionContents.get(0).getText().trim();

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionContent.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionContent.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    System.out.println(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿");
                    Assert.assertTrue(questionContent.contains(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿"),"data added over Question Editor is not displaying");

                }
            }

            new ScrollElement().scrollBottomOfPage();
            List<WebElement>edit =manageContent.edit_Link;
            Thread.sleep(3000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",edit.get(edit.size()-1));
            new UIElement().waitAndFindElement(manageContent.questionSet_title);
            Assert.assertTrue(manageContent.questionSet_title.isDisplayed());
            Assert.assertTrue(manageContent.assessment_title.isDisplayed());
            String questionName=manageContent.questionTextBox.getText().trim();
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionName.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    Assert.assertTrue(questionName.contains(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿"),"data added over Question Editor is not displaying");
                }
            }


        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForBrowseForSpanishDragandDrop of class LangaugePaletteForQuestionCreation", e);
        }
    }


    @Test(priority = 25,enabled = true)
    public void langaugePaletteForBrowseForSpanishLabelanimageText(){
        try {
            //TC row no 161
            int dataIndex=161;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

            String QuestionType="Label An Image(Text) question text";
            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLS("Label an image (Text)",dataIndex);


            Actions action2 = new Actions(driver);
            List<WebElement> we2 = manageContent.checkboxes;
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the firsr question
            Thread.sleep(2000);
            manageContent.quickPreview.get(manageContent.quickPreview.size()-1).click(); //click on the quickPreview link
            new UIElement().waitAndFindElement(manageContent.QuestionContents.get(0));
            String questionContent=manageContent.QuestionContents.get(0).getText().trim();

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionContent.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionContent.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    System.out.println(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿");
                    Assert.assertTrue(questionContent.contains(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿"),"data added over Question Editor is not displaying");

                }
            }

            new ScrollElement().scrollBottomOfPage();
            List<WebElement>edit =manageContent.edit_Link;
            Thread.sleep(3000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",edit.get(edit.size()-1));
            new UIElement().waitAndFindElement(manageContent.questionSet_title);
            Assert.assertTrue(manageContent.questionSet_title.isDisplayed());
            Assert.assertTrue(manageContent.assessment_title.isDisplayed());
            String questionName=manageContent.questionTextBox.getText().trim();
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionName.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    Assert.assertTrue(questionName.contains(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿"),"data added over Question Editor is not displaying");
                }
            }


        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForBrowseForSpanishLabelanimageText of class LangaugePaletteForQuestionCreation", e);
        }
    }


    @Test(priority = 26,enabled = true)
    public void langaugePaletteForBrowseForSpanishLabelanimageDropdown(){
        try {
            //TC row no 161
            int dataIndex=161;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

            String QuestionType="Label An Image(dropdown)";
            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLS("Label an image (Dropdown)",dataIndex);


            Actions action2 = new Actions(driver);
            List<WebElement> we2 = manageContent.checkboxes;
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the firsr question
            Thread.sleep(2000);
            manageContent.quickPreview.get(manageContent.quickPreview.size()-1).click(); //click on the quickPreview link
            new UIElement().waitAndFindElement(manageContent.QuestionContents.get(0));
            String questionContent=manageContent.QuestionContents.get(0).getText().trim();

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionContent.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionContent.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    System.out.println(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿");
                    Assert.assertTrue(questionContent.contains(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿"),"data added over Question Editor is not displaying");

                }
            }

            new ScrollElement().scrollBottomOfPage();
            List<WebElement>edit =manageContent.edit_Link;
            Thread.sleep(3000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",edit.get(edit.size()-1));
            new UIElement().waitAndFindElement(manageContent.questionSet_title);
            Assert.assertTrue(manageContent.questionSet_title.isDisplayed());
            Assert.assertTrue(manageContent.assessment_title.isDisplayed());
            String questionName=manageContent.questionTextBox.getText().trim();
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionName.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    Assert.assertTrue(questionName.contains(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿"),"data added over Question Editor is not displaying");
                }
            }


        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForBrowseForSpanishLabelanimageDropdown of class LangaugePaletteForQuestionCreation", e);
        }
    }


    @Test(priority = 27,enabled = true)
    public void langaugePaletteForBrowseForSpanishExpressionEvaluator(){
        try {
            //TC row no 161
            int dataIndex=161;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

            String QuestionType="Expression Evaluator ";
            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLS("Expression Evaluator",dataIndex);

            Actions action2 = new Actions(driver);
            List<WebElement> we2 = manageContent.checkboxes;
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the firsr question
            Thread.sleep(2000);
            manageContent.quickPreview.get(manageContent.quickPreview.size()-1).click(); //click on the quickPreview link
            new UIElement().waitAndFindElement(manageContent.QuestionContents.get(0));
            String questionContent=manageContent.QuestionContents.get(0).getText().trim();

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionContent.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionContent.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    System.out.println(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿");
                    Assert.assertTrue(questionContent.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿" + QuestionType + questiontext), "data added over Question Editor is not displaying");

                }
            }

            new ScrollElement().scrollBottomOfPage();
            List<WebElement>edit =manageContent.edit_Link;
            Thread.sleep(3000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",edit.get(edit.size()-1));
            new UIElement().waitAndFindElement(manageContent.questionSet_title);
            Assert.assertTrue(manageContent.questionSet_title.isDisplayed());
            Assert.assertTrue(manageContent.assessment_title.isDisplayed());
            String questionName=manageContent.questionTextBox.getText().trim();
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionName.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    Assert.assertTrue(questionContent.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿" + QuestionType + questiontext), "data added over Question Editor is not displaying");

                }
            }


        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForBrowseForSpanishExpressionEvaluator of class LangaugePaletteForQuestionCreation", e);
        }
    }


    @Test(priority = 28,enabled = true)
    public void langaugePaletteForBrowseForSpanishAdvancedNumeric(){
        try {
            //TC row no 161
            int dataIndex=161;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String QuestionType="Advanced Numeric ";
            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLS("Advanced Numeric",dataIndex);

            Actions action2 = new Actions(driver);
            List<WebElement> we2 = manageContent.checkboxes;
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the firsr question
            Thread.sleep(2000);
            manageContent.quickPreview.get(manageContent.quickPreview.size()-1).click(); //click on the quickPreview link
            new UIElement().waitAndFindElement(manageContent.QuestionContents.get(0));
            String questionContent=manageContent.QuestionContents.get(0).getText().trim();

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionContent.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionContent.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    System.out.println(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿");
                    Assert.assertTrue(questionContent.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿" + QuestionType + questiontext), "data added over Question Editor is not displaying");

                }
            }

            new ScrollElement().scrollBottomOfPage();
            List<WebElement>edit =manageContent.edit_Link;
            Thread.sleep(3000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",edit.get(edit.size()-1));
            new UIElement().waitAndFindElement(manageContent.questionSet_title);
            Assert.assertTrue(manageContent.questionSet_title.isDisplayed());
            Assert.assertTrue(manageContent.assessment_title.isDisplayed());
            String questionName=manageContent.questionTextBox.getText().trim();
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionName.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    Assert.assertTrue(questionContent.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿" + QuestionType + questiontext), "data added over Question Editor is not displaying");

                }
            }


        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForBrowseForSpanishAdvancedNumeric of class LangaugePaletteForQuestionCreation", e);
        }
    }



    @Test(priority = 29,enabled = true)
    public void langaugePaletteForBrowseForSpanishMultipleSelection(){
        try {
            //TC row no 161
            int dataIndex=161;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

            String QuestionType="Multi Selection ";
            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLS("Multiple Selection",dataIndex);


            Actions action2 = new Actions(driver);
            List<WebElement> we2 = manageContent.checkboxes;
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the firsr question
            Thread.sleep(2000);
            manageContent.quickPreview.get(manageContent.quickPreview.size()-1).click(); //click on the quickPreview link
            new UIElement().waitAndFindElement(manageContent.QuestionContents.get(0));
            String questionContent=manageContent.QuestionContents.get(0).getText().trim();

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionContent.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionContent.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    System.out.println(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿");
                    Assert.assertTrue(questionContent.contains(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿"),"data added over Question Editor is not displaying");

                }
            }

            new ScrollElement().scrollBottomOfPage();
            List<WebElement>edit =manageContent.edit_Link;
            Thread.sleep(3000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",edit.get(edit.size()-1));
            Assert.assertTrue(manageContent.questionSet_title.isDisplayed());
            Assert.assertTrue(manageContent.assessment_title.isDisplayed());
            String questionName=manageContent.questionTextBox.getText().trim();
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionName.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    Assert.assertTrue(questionContent.contains(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿"),"data added over Question Editor is not displaying");

                }
            }


        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForBrowseForSpanishMultipleSelection of class LangaugePaletteForQuestionCreation", e);
        }
    } @Test(priority = 30,enabled = true)
      public void langaugePaletteForBrowseForSpanishTextEntry(){
        try {
            //TC row no 161
            int dataIndex=161;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

            String QuestionType="Text Entry ";
            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLS("Text Entry",dataIndex);

            Actions action2 = new Actions(driver);
            List<WebElement> we2 = manageContent.checkboxes;
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the firsr question
            Thread.sleep(2000);
            manageContent.quickPreview.get(manageContent.quickPreview.size()-1).click(); //click on the quickPreview link
            new UIElement().waitAndFindElement(manageContent.QuestionContents.get(0));
            String questionContent=manageContent.QuestionContents.get(0).getText().trim();

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionContent.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionContent.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    System.out.println(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿");
                    Assert.assertTrue(questionContent.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            new ScrollElement().scrollBottomOfPage();
            List<WebElement>edit =manageContent.edit_Link;
            Thread.sleep(3000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",edit.get(edit.size()-1));
            new UIElement().waitAndFindElement(manageContent.questionSet_title);
            Assert.assertTrue(manageContent.questionSet_title.isDisplayed());
            Assert.assertTrue(manageContent.assessment_title.isDisplayed());
            String questionName=manageContent.questionTextBox.getText().trim();
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionName.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    Assert.assertTrue(questionContent.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }


        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForBrowseForSpanishTextEntry of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 31,enabled = true)
    public void langaugePaletteForBrowseForSpanishTextDropDown(){
        try {
            //TC row no 161
            int dataIndex=161;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

            String QuestionType="Text Drop Down ";
            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLS("Text Drop Down",dataIndex);

            Actions action2 = new Actions(driver);
            List<WebElement> we2 = manageContent.checkboxes;
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the firsr question
            Thread.sleep(2000);
            manageContent.quickPreview.get(manageContent.quickPreview.size()-1).click(); //click on the quickPreview link
            new UIElement().waitAndFindElement(manageContent.QuestionContents.get(0));
            String questionContent=manageContent.QuestionContents.get(0).getText().trim();

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionContent.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionContent.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    System.out.println(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿");
                    Assert.assertTrue(questionContent.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            new ScrollElement().scrollBottomOfPage();
            List<WebElement>edit =manageContent.edit_Link;
            Thread.sleep(3000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",edit.get(edit.size()-1));
            new UIElement().waitAndFindElement(manageContent.questionSet_title);
            Assert.assertTrue(manageContent.questionSet_title.isDisplayed());
            Assert.assertTrue(manageContent.assessment_title.isDisplayed());
            String questionName=manageContent.questionTextBox.getText().trim();
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionName.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    Assert.assertTrue(questionName.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }


        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForBrowseForSpanishTextDropDown of class LangaugePaletteForQuestionCreation", e);
        }
    } @Test(priority = 32,enabled = true)
      public void langaugePaletteForBrowseForSpanishNumericEntryunits(){
        try {
            //TC row no 161
            int dataIndex=161;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

            String QuestionType="Numeric Entry With Units ";
            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLS("Numeric Entry w/units",dataIndex);

            Actions action2 = new Actions(driver);
            List<WebElement> we2 = manageContent.checkboxes;
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the firsr question
            Thread.sleep(2000);
            manageContent.quickPreview.get(manageContent.quickPreview.size()-1).click(); //click on the quickPreview link
            new UIElement().waitAndFindElement(manageContent.QuestionContents.get(0));
            String questionContent=manageContent.QuestionContents.get(0).getText().trim();

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionContent.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionContent.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    System.out.println(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿");
                    Assert.assertTrue(questionContent.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            new ScrollElement().scrollBottomOfPage();
            List<WebElement>edit =manageContent.edit_Link;
            Thread.sleep(3000);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();",edit.get(edit.size()-1));
            new UIElement().waitAndFindElement(manageContent.questionSet_title);
            Assert.assertTrue(manageContent.questionSet_title.isDisplayed());
            Assert.assertTrue(manageContent.assessment_title.isDisplayed());
            String questionName=manageContent.questionTextBox.getText().trim();
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionName.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    Assert.assertTrue(questionContent.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }


        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForBrowseForSpanishNumericEntryunits of class LangaugePaletteForQuestionCreation", e);
        }
    }






    @Test(priority = 33,enabled = true)
    public void langaugePaletteForFullPreview(){
        try {
            //TC row no 161
            int dataIndex=161;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

            String QuestionType="True False ";

            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLS("True / False",dataIndex);
            Thread.sleep(5000);
            Actions action2 = new Actions(driver);
            List<WebElement> we2 = manageContent.checkboxes;
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the first question
            Thread.sleep(2000);
            List<WebElement>edit =manageContent.preview_link;
            edit.get(edit.size()-1).click();//click on the preview Button
            String parentWindow=driver.getWindowHandle();
            for(String child:driver.getWindowHandles()){
                driver.switchTo().window(child);
            }
            new UIElement().waitAndFindElement(driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")));
            String questionName=driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")).getText().trim();
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionName.contains(QuestionType + questiontext +"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤"),"data added over Question Editor is not displaying");
                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionName.contains(QuestionType + questiontext +"ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    Assert.assertTrue(questionName.contains(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿"),"data added over Question Editor is not displaying");

                }
            }
            driver.close();
            driver.switchTo().window(parentWindow);

        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForFullPreview of class LangaugePaletteForQuestionCreation", e);
        }
    }


    @Test(priority = 34,enabled = true)
    public void langaugePaletteForFullPreviewMSelection(){
        try {
            //TC row no 161
            int dataIndex=161;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

            String QuestionType="Multi Selection ";
            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLS("Multiple Selection",dataIndex);
            Thread.sleep(5000);
            Actions action2 = new Actions(driver);
            List<WebElement> we2 = manageContent.checkboxes;
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the first question
            Thread.sleep(2000);
            List<WebElement>edit =manageContent.preview_link;
            edit.get(edit.size()-1).click();//click on the preview Button
            String parentWindow=driver.getWindowHandle();
            for(String child:driver.getWindowHandles()){
                driver.switchTo().window(child);
            }
            new UIElement().waitAndFindElement(driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")));
            String questionName=driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")).getText().trim();
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionName.contains(QuestionType + questiontext +"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤"),"data added over Question Editor is not displaying");
                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionName.contains(QuestionType + questiontext +"ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    Assert.assertTrue(questionName.contains(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿"),"data added over Question Editor is not displaying");

                }
            }
            driver.close();
            driver.switchTo().window(parentWindow);

        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForFullPreviewMSelection of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 35,enabled = true)
    public void langaugePaletteForFullPreviewTextEntry(){
        try {
            //TC row no 161
            int dataIndex=161;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String QuestionType="Text Entry ";
            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLS("Text Entry",dataIndex);
            Thread.sleep(5000);
            Actions action2 = new Actions(driver);
            List<WebElement> we2 = manageContent.checkboxes;
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the first question
            Thread.sleep(2000);
            List<WebElement>edit =manageContent.preview_link;
            edit.get(edit.size()-1).click();//click on the preview Button
            String parentWindow=driver.getWindowHandle();
            for(String child:driver.getWindowHandles()){
                driver.switchTo().window(child);
            }
            new UIElement().waitAndFindElement(driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")));
            String questionName=driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")).getText().trim();
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionName.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    Assert.assertTrue(questionName.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            driver.close();
            driver.switchTo().window(parentWindow);

        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForFullPreviewTextEntry of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 36,enabled = true)
    public void langaugePaletteForFullPreviewTextDropDown(){
        try {
            //TC row no 161
            int dataIndex=161;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String QuestionType="Text Drop Down ";
            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLS("Text Drop Down",dataIndex);
            Thread.sleep(5000);
            Actions action2 = new Actions(driver);
            List<WebElement> we2 = manageContent.checkboxes;
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the first question
            Thread.sleep(2000);
            List<WebElement>edit =manageContent.preview_link;
            edit.get(edit.size()-1).click();//click on the preview Button
            String parentWindow=driver.getWindowHandle();
            for(String child:driver.getWindowHandles()){
                driver.switchTo().window(child);
            }
            new UIElement().waitAndFindElement(driver.findElement(By.className("text_dropdown_preview_Q")));
            String questionName=driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")).getText().trim();
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionName.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    Assert.assertTrue(questionName.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            driver.close();
            driver.switchTo().window(parentWindow);

        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForFullPreviewTextDropDown of class LangaugePaletteForQuestionCreation", e);
        }
    }


    @Test(priority = 37,enabled = true)
    public void langaugePaletteForFullPreviewNumericEntryWithUnits(){
        try {
            //TC row no 161
            int dataIndex=161;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String QuestionType="Numeric Entry With Units ";
            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLS("Numeric Entry w/units",dataIndex);
            Thread.sleep(5000);
            Actions action2 = new Actions(driver);
            List<WebElement> we2 = manageContent.checkboxes;
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the first question
            Thread.sleep(2000);
            List<WebElement>edit =manageContent.preview_link;
            edit.get(edit.size()-1).click();//click on the preview Button
            String parentWindow=driver.getWindowHandle();
            for(String child:driver.getWindowHandles()){
                driver.switchTo().window(child);
            }
            new UIElement().waitAndFindElement(driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")));
            String questionName=driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")).getText().trim();
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionName.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    Assert.assertTrue(questionName.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            driver.close();
            driver.switchTo().window(parentWindow);

        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForFullPreviewNumericEntryWithUnits of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 38,enabled = true)
    public void langaugePaletteForFullPreviewAdvancedNumeric(){
        try {
            //TC row no 161
            int dataIndex=161;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String QuestionType="Advanced Numeric ";
            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLS("Advanced Numeric",dataIndex);
            Thread.sleep(5000);
            Actions action2 = new Actions(driver);
            List<WebElement> we2 = manageContent.checkboxes;
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the first question
            Thread.sleep(2000);
            List<WebElement>edit =manageContent.preview_link;
            edit.get(edit.size()-1).click();//click on the preview Button
            String parentWindow=driver.getWindowHandle();
            for(String child:driver.getWindowHandles()){
                driver.switchTo().window(child);
            }
            new UIElement().waitAndFindElement(driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")));
            String questionName=driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")).getText().trim();
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionName.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    System.out.println("áÁéÉíÍñÑóÓúÚüÜ¡¿" + QuestionType + questiontext);
                    Assert.assertTrue(questionName.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿" + QuestionType + questiontext), "data added over Question Editor is not displaying");

                }
            }
            driver.close();
            driver.switchTo().window(parentWindow);

        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForFullPreviewNumericEntryWithUnits of class LangaugePaletteForQuestionCreation", e);
        }
    }
    @Test(priority = 39,enabled = true)
    public void langaugePaletteForFullPreviewExpressionEvaluator(){
        try {
            //TC row no 161
            int dataIndex=161;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String QuestionType="Expression Evaluator ";
            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLS("Expression Evaluator",dataIndex);
            Thread.sleep(5000);
            Actions action2 = new Actions(driver);
            List<WebElement> we2 = manageContent.checkboxes;
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the first question
            Thread.sleep(2000);
            List<WebElement>edit =manageContent.preview_link;
            edit.get(edit.size()-1).click();//click on the preview Button
            String parentWindow=driver.getWindowHandle();
            for(String child:driver.getWindowHandles()){
                driver.switchTo().window(child);
            }
            new UIElement().waitAndFindElement(driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")));
            String questionName=driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")).getText().trim();
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionName.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    Assert.assertTrue(questionName.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            driver.close();
            driver.switchTo().window(parentWindow);

        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForFullPreviewNumericEntryWithUnits of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 40,enabled = true)
    public void langaugePaletteForFullPreviewEssayTypeQuestion(){
        try {
            //TC row no 161
            int dataIndex=161;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String QuestionType="Essay ";
            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLS("Essay Type", dataIndex);
            Thread.sleep(5000);
            Actions action2 = new Actions(driver);
            List<WebElement> we2 = manageContent.checkboxes;
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the first question
            Thread.sleep(2000);
            List<WebElement>edit =manageContent.preview_link;
            edit.get(edit.size()-1).click();//click on the preview Button
            String parentWindow=driver.getWindowHandle();
            for(String child:driver.getWindowHandles()){
                driver.switchTo().window(child);
            }
            new UIElement().waitAndFindElement(driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")));
            String questionName=driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")).getText().trim();
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionName.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    System.out.println("áÁéÉíÍñÑóÓúÚüÜ¡¿" + QuestionType + questiontext);
                    Assert.assertTrue(questionName.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            driver.close();
            driver.switchTo().window(parentWindow);

        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForFullPreviewNumericEntryWithUnits of class LangaugePaletteForQuestionCreation", e);
        }
    }
    @Test(priority = 41,enabled = true)
    public void langaugePaletteForFullPreviewWorkboard(){
        try {
            //TC row no 161
            int dataIndex=161;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String QuestionType="Write Board ";
            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLS("Workboard",dataIndex);
            Thread.sleep(5000);
            Actions action2 = new Actions(driver);
            List<WebElement> we2 = manageContent.checkboxes;
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the first question
            Thread.sleep(2000);
            List<WebElement>edit =manageContent.preview_link;
            edit.get(edit.size()-1).click();//click on the preview Button
            String parentWindow=driver.getWindowHandle();
            for(String child:driver.getWindowHandles()){
                driver.switchTo().window(child);
            }
            new UIElement().waitAndFindElement(driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")));
            String questionName=driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")).getText().trim();
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionName.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    System.out.println(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿");
                    Assert.assertTrue(questionName.contains(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿"),"data added over Question Editor is not displaying");


                }
            }
            driver.close();
            driver.switchTo().window(parentWindow);

        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForFullPreviewNumericEntryWithUnits of class LangaugePaletteForQuestionCreation", e);
        }
    }
    @Test(priority = 42,enabled = true)
    public void langaugePaletteForFullPreviewAudio(){
        try {
            //TC row no 161
            int dataIndex=161;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String QuestionType="audio ";
            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLS("Audio Recorder",dataIndex);
            Thread.sleep(5000);
            Actions action2 = new Actions(driver);
            List<WebElement> we2 = manageContent.checkboxes;
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the first question
            Thread.sleep(2000);
            List<WebElement>edit =manageContent.preview_link;
            edit.get(edit.size()-1).click();//click on the preview Button
            String parentWindow=driver.getWindowHandle();
            for(String child:driver.getWindowHandles()){
                driver.switchTo().window(child);
            }
            new UIElement().waitAndFindElement(driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")));
            String questionName=driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")).getText().trim();
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionName.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    System.out.println(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿");
                    Assert.assertTrue(questionName.contains(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿"),"data added over Question Editor is not displaying");

                }
            }
            driver.close();
            driver.switchTo().window(parentWindow);

        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForFullPreviewNumericEntryWithUnits of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 43,enabled = true)
    public void langaugePaletteForFullPreviewMatchthefollowing(){
        try {
            //TC row no 161
            int dataIndex=161;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String QuestionType="Match the Following ";
            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLS("Match the following",dataIndex);
            Thread.sleep(5000);
            Actions action2 = new Actions(driver);
            List<WebElement> we2 = manageContent.checkboxes;
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the first question
            Thread.sleep(2000);
            List<WebElement>edit =manageContent.preview_link;
            edit.get(edit.size()-1).click();//click on the preview Button
            String parentWindow=driver.getWindowHandle();
            for(String child:driver.getWindowHandles()){
                driver.switchTo().window(child);
            }
            new UIElement().waitAndFindElement(driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")));
            String questionName=driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")).getText().trim();
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionName.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    System.out.println(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿");
                    Assert.assertTrue(questionName.contains(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿"),"data added over Question Editor is not displaying");

                }
            }
            driver.close();
            driver.switchTo().window(parentWindow);

        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForFullPreviewMatchthefollowing of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 44,enabled = true)
    public void langaugePaletteForFullPreviewDragandDrop(){
        try {
            //TC row no 161
            int dataIndex=161;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String QuestionType="Drag and Drop ";
            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLS("Drag and Drop",dataIndex);
            Thread.sleep(5000);
            Actions action2 = new Actions(driver);
            List<WebElement> we2 = manageContent.checkboxes;
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the first question
            Thread.sleep(2000);
            List<WebElement>edit =manageContent.preview_link;
            edit.get(edit.size()-1).click();//click on the preview Button
            String parentWindow=driver.getWindowHandle();
            for(String child:driver.getWindowHandles()){
                driver.switchTo().window(child);
            }
            new UIElement().waitAndFindElement(driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")));
            String questionName=driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")).getText().trim();
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionName.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    System.out.println(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿");
                    Assert.assertTrue(questionName.contains(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿"),"data added over Question Editor is not displaying");

                }
            }
            driver.close();
            driver.switchTo().window(parentWindow);

        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForFullPreviewDragandDrop of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 45,enabled = true)
    public void langaugePaletteForFullPreviewLabelanimageText(){
        try {
            //TC row no 161
            int dataIndex=161;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String QuestionType="Label An Image(Text) question text";
            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLS("Label an image (Text)",dataIndex);
            Thread.sleep(5000);
            Actions action2 = new Actions(driver);
            List<WebElement> we2 = manageContent.checkboxes;
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the first question
            Thread.sleep(2000);
            List<WebElement>edit =manageContent.preview_link;
            edit.get(edit.size()-1).click();//click on the preview Button
            String parentWindow=driver.getWindowHandle();
            for(String child:driver.getWindowHandles()){
                driver.switchTo().window(child);
            }
            new UIElement().waitAndFindElement(driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")));
            String questionName=driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")).getText().trim();
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionName.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }

            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    System.out.println(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿");
                    Assert.assertTrue(questionName.contains(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿"),"data added over Question Editor is not displaying");

                }
            }
            driver.close();
            driver.switchTo().window(parentWindow);

        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForFullPreviewLabelanimageText of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 46,enabled = true)
    public void langaugePaletteForFullPreviewLabelAnImagedropdown(){
        try {
            //TC row no 161
            int dataIndex=161;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String QuestionType="Label An Image(dropdown)";
            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLS("Label an image (Dropdown)",dataIndex);
            Thread.sleep(5000);
            Actions action2 = new Actions(driver);
            List<WebElement> we2 = manageContent.checkboxes;
            action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the first question
            Thread.sleep(2000);
            List<WebElement>edit =manageContent.preview_link;
            edit.get(edit.size()-1).click();//click on the preview Button
            String parentWindow=driver.getWindowHandle();
            for(String child:driver.getWindowHandles()){
                driver.switchTo().window(child);
            }
            new UIElement().waitAndFindElement(driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")));
            String questionName=driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")).getText().trim();
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("italian")){
                    Assert.assertTrue(questionName.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }


            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("french")){
                    Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

                }
            }
            if(selectLanguagePalette!=null){
                if(selectLanguagePalette.equals("spanish")){
                    System.out.println(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿");
                    Assert.assertTrue(questionName.contains(QuestionType + questiontext +"áÁéÉíÍñÑóÓúÚüÜ¡¿"),"data added over Question Editor is not displaying");

                }
            }
            driver.close();
            driver.switchTo().window(parentWindow);

        } catch (Exception e) {
            Assert.fail("Exception in TC langaugePaletteForFullPreviewLabelAnImagedropdown of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 47,enabled = true)
    public void copyQuestionWithinCourse(){
        try {

            //TC row no 167
            int dataIndex=167; //create assessment for biology
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

            new Assignment().create(167);
            new Assignment().addQuestions(167,"textentry","");
            new Assignment().addQuestions(167,"advancednumeric","");

            new Assignment().create(168); //create assessment for geography
            new Assignment().addQuestions(168,"textentry","");
            new Assignment().addQuestions(168,"advancednumeric","");

            new Assignment().create(169);
            new OpenSearchPage().openSearchPageFORLS();
            new OpenSearchPage().searchquestion(questiontext);
            new Questions().copyQuestionWithinCourse("167", "Ch 8");
            List<WebElement> questionContents=manageContent.questionContentsInSearchPage;
            String questionContent=questionContents.get(questionContents.size()-1).getText().trim();
            System.out.println("questionContent:"+questionContent);
            Assert.assertTrue(questionContent.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœTrue False "+ questiontext),"The Copied question is not displaying in the Search page with the language Palette characters used in the question");

            List<WebElement> chapterNames=manageContent.chapterNameInSearchPage;
            String chapter=chapterNames.get(questionContents.size()-1).getText().trim();
            System.out.println("chapter:"+chapter);
            Assert.assertTrue(chapter.contains("Photosynthesis"),"The Copied question is not displaying in the Search page with the language Palette characters used in the question");


            List<WebElement>edit =manageContent.edit_Link;
            Thread.sleep(2000);
            edit.get(edit.size()-1).click();//click on the edit Button

            String questionName=manageContent.questionTextBox.getText().trim();
            Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœTrue False "+ questiontext),"data added over Question Editor is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in TC copyQuestionWithinCourse of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 48,enabled = true)
    public void copyQuestionBetweenCourse(){
        try {

            //TC row no 167
            int dataIndex=167; //create assessment for biology
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(168));

            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new OpenSearchPage().openSearchPageFORLS();
            new OpenSearchPage().searchquestion(questiontext);
            new Questions().copyQuestionBetweenCourses(167, "Ch 1B: European Regions");
            new UIElement().waitAndFindElement(manageContent.change_link);
            manageContent.change_link.click(); //click on the change link
            driver.findElement(By.cssSelector("img[alt=\"" + Config.lscourse + "\"]")).click();
            new Click().clickbyxpath("//div[contains(@title,'Ch 1B: European Regions')]");
            Thread.sleep(3000);
            new Click().clickbyxpath("//div[@title='"+assessmentname+"']");
            new CMSActions().navigateToQuestionNo(4);
            String questionName=manageContent.questionTextBox.getText().trim();
            Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœTrue False "+ questiontext),"data added over Question Editor is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in TC copyQuestionBetweenCourse of class LangaugePaletteForQuestionCreation", e);
        }
    }


    @Test(priority = 49,enabled = true)
    public void moveQuestionWithinCourse(){
        try {

            //TC row no 167
            int dataIndex=167; //create assessment for biology
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new OpenSearchPage().openSearchPageFORLS();
            new OpenSearchPage().searchquestion(questiontext);
            int questionCountBeforeMove = Integer.parseInt(driver.findElement(By.id("search-content-count")).getAttribute("count"));
            new Questions().moveQuestionWithinCourse("167", "Ch 9");
            List<WebElement> questionContents=manageContent.questionContentsInSearchPage;
            String questionContent=questionContents.get(questionContents.size()-1).getText().trim();
            System.out.println("questionContent:"+questionContent);
            int questionCountaftertMove = Integer.parseInt(driver.findElement(By.id("search-content-count")).getAttribute("count"));

            if(questionCountaftertMove>=questionCountBeforeMove){
                Assert.fail("Question has not moved to the within courses");
            }

            List<WebElement> chapterNames=manageContent.chapterNameInSearchPage;
            String chapter=chapterNames.get(questionContents.size()-1).getText().trim();
            System.out.println("chapter:"+chapter);
            Assert.assertTrue(chapter.contains("The Russian Realm"),"The Copied question is not displaying in the Search page with the language Palette characters used in the question");

            List<WebElement>edit =manageContent.edit_Link;
            Thread.sleep(2000);
            edit.get(edit.size()-1).click();//click on the edit Button

            String questionName=manageContent.questionTextBox.getText().trim();
            //Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœTrue False "+ questiontext),"data added over Question Editor is not displaying");


        } catch (Exception e) {
            Assert.fail("Exception in TC moveQuestionWithinCourse of class LangaugePaletteForQuestionCreation", e);
        }
    }


    @Test(priority = 50,enabled = true)
    public void moveQuestionBetweenCourse(){
        try {

            //TC row no 167
            int dataIndex=167; //create assessment for biology
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(168));

            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new OpenSearchPage().openSearchPageFORLS();
            new OpenSearchPage().searchquestion(questiontext);
            new Questions().moveQuestionBetweenCourses(167, "Ch 2A: The Russian Realm");
            new UIElement().waitAndFindElement(manageContent.change_link);
            manageContent.change_link.click(); //click on the change link
            driver.findElement(By.cssSelector("img[alt=\"" + Config.lscourse + "\"]")).click();
            new Click().clickbyxpath("//div[contains(@title,'Ch 2A: The Russian Realm')]");
            Thread.sleep(3000);
            new Click().clickbyxpath("//div[@title='Russian Realm Countries Questions']");
            new CMSActions().navigateToQuestionNo(5);
            String questionName=manageContent.questionTextBox.getText().trim();
            Thread.sleep(5000);
            Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœText Entry  "+ questiontext),"data added over Question Editor is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in TC moveQuestionBetweenCourse of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 51,enabled = true)
    public void launchReviewForSearch(){
        try {

            //TC row no 186
            int dataIndex=167; //create assessment for biology
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new OpenSearchPage().openSearchPageFORLS();
            new OpenSearchPage().searchquestion(questiontext);
            List<WebElement> questionContents=manageContent.questionContentsInSearchPage;
            String questionContent=questionContents.get(0).getText().trim();
            System.out.println("questionContent:"+questionContent);
            new Questions().checkForLaunchReview(167);
            Thread.sleep(3000);
            new UIElement().waitAndFindElement(manageContent.questionContent_preview);
            String questionName=manageContent.questionContent_preview.getText().trim();
            Assert.assertTrue(questionName.contains(questionContent),"data added over Question Editor is not displaying");

            ((JavascriptExecutor)driver).executeScript("arguments[0].click()",manageContent.previewEdit_link);
            verificationForFrench();


        } catch (Exception e) {
            Assert.fail("Exception in TC launchReviewForSearch of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 52,enabled = true)
    public void launchReviewForBrowse(){
        try {

            //TC row no 186
            int dataIndex=169; //create assessment for biology
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new OpenSearchPage().openSearchPageFORLS();
            new OpenSearchPage().OpenBrowsePageAndFilterChapterNameForLSDefaultStatus("True / False",169);
            List<WebElement> questionContents=manageContent.questionContentsInSearchPage;
            String questionContent=questionContents.get(questionContents.size()-1).getText().trim();
            System.out.println("questionContent:"+questionContent);
            new Questions().checkForLaunchReview(169);
            Thread.sleep(3000);
            new UIElement().waitAndFindElement(manageContent.questionContent_preview);
            String questionName=manageContent.questionContent_preview.getText().trim();
            Assert.assertTrue(questionName.contains(questionContent),"data added over Question Editor is not displaying");
            ((JavascriptExecutor)driver).executeScript("arguments[0].click()",manageContent.previewEdit_link);
            verificationForFrench();


        } catch (Exception e) {
            Assert.fail("Exception in TC launchReviewForBrowse of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 53,enabled = true)
    public void copyAssessmentWithinCourse(){
        try {

            //TC row no 186
            int dataIndex=197; //create assessment for biology
            int dataIndex1=198;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex1));

            new Assignment().create(197);
            new Assignment().create(198);

            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new OpenSearchPage().openSearchPageFORLS();//open search page
            new Click().clickbylinkText("Bulk Operations");//click on Bulk Operations dropdown
            new Click().clickbylinkText("Copy Assessments");//click on Copy Assessments option

            new Click().clickbylinkText("Select an option");//click on Select a course
            new Click().clickbypartiallinkText("Ch 4: Cell Structure");//click on Ch 6:
            List<WebElement> selectTwoCheckBox=driver.findElements(By.xpath("//div[@class='cms-assessment-checkbox-row']/input"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectTwoCheckBox.get(selectTwoCheckBox.size()-1));
            new Click().clickbylinkText("Select a course");//click on Select a course
            new Click().clickbyxpath("(//a[text()='"+Config.course+"'])[3]"); //click on the destination biology
            new Click().clickbyxpath("(//a[text()='Select an option'])[4]");//select a destination chapter
            new Click().clickbyxpath("(//a[@title='Ch 10: Cell Reproduction'])[4]");
            List<WebElement> radioButtons=driver.findElements(By.xpath("//div[@class='cms-assessment-radiobtn-row']/input"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", radioButtons.get(radioButtons.size()-1));
            manageContent.copy_button.click(); //click on the copy button
            Assert.assertEquals(manageContent.confirmationMsg.getText().trim(),"The operation has been successfully completed");
            manageContent.close_icon.click();
          /*  new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();*/

            manageContent.manageContent.click();
            new Click().clickbyxpath("//div[contains(@title,'Ch 10: Cell Reproduction')]");
            Thread.sleep(3000);
            new Click().clickbyxpath("//div[@title='"+assessmentname+"']");
            manageContent.questionSet_dropdown.click();
            Thread.sleep(2000);
            manageContent.questionSet_dropdownList.get(1).click();
            new UIElement().waitAndFindElement(manageContent.questionTextBox);
            String questionName=manageContent.questionTextBox.getText().trim();
            Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœTrue False "+ questiontext),"The question is not dispalying  in the assignment to which it was copied");



        } catch (Exception e) {
            Assert.fail("Exception in TC copyAssessmentWithinCourse of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 54,enabled = true)
    public void copyAssessmentBetweenCourse(){
        try {

            //TC row no 186
            int dataIndex=197; //create assessment for biology
            int dataIndex1=202;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex1));

            new Assignment().create(202);
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new OpenSearchPage().openSearchPageFORLS();//open search page
            new Click().clickbylinkText("Bulk Operations");//click on Bulk Operations dropdown
            new Click().clickbylinkText("Copy Assessments");//click on Copy Assessments option

            new Click().clickbylinkText("Select an option");//click on Select a course
            new Click().clickbypartiallinkText("Ch 4: Cell Structure");//click on Ch 6:
            List<WebElement> selectTwoCheckBox=driver.findElements(By.xpath("//div[@class='cms-assessment-checkbox-row']/input"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectTwoCheckBox.get(selectTwoCheckBox.size() - 1));
            new Click().clickbylinkText("Select a course");//click on Select a course
            new Click().clickbyxpath("(//a[@title='Geography: Realms, Regions, and Concepts - Sixteenth Edition'])[2]");
            new Click().clickbyxpath("(//a[text()='Select an option'])[4]");//select a destination chapter
            Thread.sleep(3000);
            new Click().clickbypartiallinkText("Ch 2B: Russian Regions");//Select a 2nd Chapter
            //new Click().clickbyxpath("(//a[@title='Ch 2B: Russian Regions'])[3]");
            List<WebElement> radioButtons=driver.findElements(By.xpath("//div[@class='cms-assessment-radiobtn-row']/input"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", radioButtons.get(radioButtons.size()-1));
            manageContent.copy_button.click(); //click on the copy button
            Assert.assertEquals(manageContent.confirmationMsg.getText().trim(),"The operation has been successfully completed");
            manageContent.close_icon.click();

          /*  new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();*/

            manageContent.manageContent.click();
            manageContent.change_link.click(); //click on the change link
            driver.findElement(By.cssSelector("img[alt=\"" + Config.lscourse + "\"]")).click();
            new Click().clickbyxpath("//div[contains(@title,'Ch 2B: Russian Regions')]");
            Thread.sleep(3000);
            new Click().clickbyxpath("//div[@title='"+assessmentname+"']");
            manageContent.questionSet_dropdown.click();
            Thread.sleep(2000);
            manageContent.questionSet_dropdownList.get(1).click();
            Thread.sleep(3000);
            new UIElement().waitAndFindElement(manageContent.questionTextBox);
            String questionName=manageContent.questionTextBox.getText().trim();
            Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœTrue False "+ questiontext),"The question is not dispalying  in the assignment to which it was copied");


        } catch (Exception e) {
            Assert.fail("Exception in TC copyAssessmentBetweenCourse of class LangaugePaletteForQuestionCreation", e);
        }
    }


    @Test(priority = 55,enabled = true)
    public void moveAssessmentBetweenCourse(){
        try {

            //TC row no 186
            int dataIndex=167; //create assessment for biology
            int dataIndex1=212;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex1));
            new Assignment().create(212);

            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            new OpenSearchPage().openSearchPageFORLS();//open search page
            new Click().clickbylinkText("Bulk Operations");//click on Bulk Operations dropdown
            new Click().clickbylinkText("Move Assessments");//click on Move Assessments option

            new Click().clickbylinkText("Select an option");//click on Select a course
            new Click().clickbypartiallinkText("Ch 7: Cellular Respiration");//click on Ch 6:
            List<WebElement> selectTwoCheckBox=driver.findElements(By.xpath("//div[@class='cms-assessment-checkbox-row']/input"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", selectTwoCheckBox.get(selectTwoCheckBox.size() - 1));
            new Click().clickbylinkText("Select a course");//click on Select a course
            new Click().clickbyxpath("(//a[@title='Geography: Realms, Regions, and Concepts - Sixteenth Edition'])[2]");
            new Click().clickbyxpath("(//a[text()='Select an option'])[4]");//select a destination chapter
            Thread.sleep(3000);
            new Click().clickbypartiallinkText("Ch 3A: The North American Realm");//Select a 2nd Chapter
            List<WebElement> radioButtons=driver.findElements(By.xpath("//div[@class='cms-assessment-radiobtn-row']/input"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", radioButtons.get(radioButtons.size()-1));

            manageContent.copy_button.click(); //click on the move button
            Assert.assertEquals(manageContent.confirmationMsg.getText().trim(),"The operation has been successfully completed");
            manageContent.close_icon.click();
           /* new DirectLogin().CMSLogin();
            new SelectCourse().selectcourse();*/

            manageContent.manageContent.click();
            manageContent.change_link.click(); //click on the change link
            driver.findElement(By.cssSelector("img[alt=\"" + Config.lscourse + "\"]")).click();
            new Click().clickbyxpath("//div[contains(@title,'Ch 3A: The North American Realm')]");
            Thread.sleep(3000);
            new Click().clickbylistxpath("//div[@title='"+assessmentname+"']",1);
            manageContent.questionSet_dropdown.click();
            Thread.sleep(2000);
            manageContent.questionSet_dropdownList.get(2).click();
            Thread.sleep(3000);
            new UIElement().waitAndFindElement(manageContent.questionTextBox);
            String questionName=manageContent.questionTextBox.getText().trim();
            Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœTrue False "+ questiontext),"The question is not dispalying  in the assignment to which it was copied");


        } catch (Exception e) {
            Assert.fail("Exception in TC moveAssessmentBetweenCourse of class LangaugePaletteForQuestionCreation", e);
        }
    }
    @DataProvider(name = "preview")
    public Object[][] getPreviewQuestion() {
        return new Object[][] {
                {"True False " },
                {"Multiple Choice "},
                {"Multi Selection "},
                {"Text Entry " },
                {"Text Drop Down "},
                {"Numeric Entry With Units "},
                {"Advanced Numeric "},
                {"Expression Evaluator "},
                {"Essay "},
                {"Write Board " },
                {"audio "},
                { "Label An Image(Text) question text"},
                { "Label An Image(dropdown)"},
        };
    }

    @Test(priority = 56,enabled = true)
    public void createQuestionForQuestionPreview(){
        try {

            //TC row no 217
            int dataIndex=217; //create assessment for biology
            new Assignment().create(217);
            new Assignment().addQuestions(217,"all","");
        } catch (Exception e) {

            Assert.fail("Exception in TC createQuestionForQuestionPreview of class LangaugePaletteForQuestionCreation", e);
        }
    }


    @Test(priority = 57,enabled = true)
    public void langaugePaleteForQuestionPreviewTrueFalse(){
        try {

            //TC row no 217
            int dataIndex=217; //create assessment for biology
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));

            String questionType="True False ";
            new DirectLogin().CMSLogin();
            new SelectCourse().selectLsCourse();
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");
            Thread.sleep(3000);
            new Click().clickbyxpath("//div[@title='" + assessmentname + "']");
            new CMSActions().navigateToQuestionNo(2);
            count++;
            System.out.println("count:"+count);
            manageContent.preview_Button.click();
            String parentWindow=driver.getWindowHandle();
            for(String child:driver.getWindowHandles()){
                driver.switchTo().window(child);
            }
            new UIElement().waitAndFindElement(driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")));
            String questionName=driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")).getText().trim();
            Assert.assertTrue(questionName.contains(questionType + questiontext + "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"), "data added over Question Editor is not displaying");
            driver.close();
            driver.switchTo().window(parentWindow);

        } catch (Exception e) {

            Assert.fail("Exception in TC langaugePaleteForQuestionPreviewTrueFalse of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 58,enabled = true)
    public void langaugePaleteForQuestionPreviewMultipleChoice(){
        try {

            //TC row no 217
            int dataIndex=217; //create assessment for biology
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));

            String questionType="Multiple Choice ";
            new DirectLogin().CMSLogin();
            new SelectCourse().selectLsCourse();
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");
            Thread.sleep(3000);
            new Click().clickbyxpath("//div[@title='" + assessmentname + "']");
            new CMSActions().navigateToQuestionNo(3);
            // count++;
            System.out.println("count:"+count);
            manageContent.preview_Button.click();
            String parentWindow=driver.getWindowHandle();
            for(String child:driver.getWindowHandles()){
                driver.switchTo().window(child);
            }
            new UIElement().waitAndFindElement(driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")));
            String questionName=driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")).getText().trim();
            Assert.assertTrue(questionName.contains(questionType + questiontext + "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"), "data added over Question Editor is not displaying");
            driver.close();
            driver.switchTo().window(parentWindow);

        } catch (Exception e) {

            Assert.fail("Exception in TC langaugePaleteForQuestionPreviewMultipleChoice of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 59,enabled = true)
    public void langaugePaleteForQuestionPreviewMultiSelection(){
        try {

            //TC row no 217
            int dataIndex=217; //create assessment for biology
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));

            String questionType="Multi Selection ";
            new DirectLogin().CMSLogin();
            new SelectCourse().selectLsCourse();
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");
            Thread.sleep(3000);
            new Click().clickbyxpath("//div[@title='" + assessmentname + "']");
            new CMSActions().navigateToQuestionNo(4);
            // count++;
            System.out.println("count:"+count);
            manageContent.preview_Button.click();
            String parentWindow=driver.getWindowHandle();
            for(String child:driver.getWindowHandles()){
                driver.switchTo().window(child);
            }
            new UIElement().waitAndFindElement(driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")));
            String questionName=driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")).getText().trim();
            Assert.assertTrue(questionName.contains(questionType + questiontext + "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"), "data added over Question Editor is not displaying");
            driver.close();
            driver.switchTo().window(parentWindow);

        } catch (Exception e) {

            Assert.fail("Exception in TC langaugePaleteForQuestionPreviewMultiSelection of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 60,enabled = true)
    public void langaugePaleteForQuestionPreviewTextEntry(){
        try {

            //TC row no 217
            int dataIndex=217; //create assessment for biology
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));
            String questionType="Text Entry ";

            new DirectLogin().CMSLogin();
            new SelectCourse().selectLsCourse();
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");
            Thread.sleep(3000);
            new Click().clickbyxpath("//div[@title='" + assessmentname + "']");
            new CMSActions().navigateToQuestionNo(5);
            //count++;
            System.out.println("count:"+count);
            manageContent.preview_Button.click();
            String parentWindow=driver.getWindowHandle();
            for(String child:driver.getWindowHandles()){
                driver.switchTo().window(child);
            }
            new UIElement().waitAndFindElement(driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")));
            String questionName=driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")).getText().trim();
            Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" +questionType + questiontext), "data added over Question Editor is not displaying");
            driver.close();
            driver.switchTo().window(parentWindow);

        } catch (Exception e) {

            Assert.fail("Exception in TC langaugePaleteForQuestionPreviewTextEntry of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 61,enabled = true)
    public void langaugePaleteForQuestionPreviewTextDropDown (){
        try {

            //TC row no 217
            int dataIndex=217; //create assessment for biology
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));

            String questionType="Text Drop Down ";

            new DirectLogin().CMSLogin();
            new SelectCourse().selectLsCourse();
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");
            Thread.sleep(3000);
            new Click().clickbyxpath("//div[@title='" + assessmentname + "']");
            new CMSActions().navigateToQuestionNo(6);
            count++;
            System.out.println("count:"+count);
            manageContent.preview_Button.click();
            String parentWindow=driver.getWindowHandle();
            for(String child:driver.getWindowHandles()){
                driver.switchTo().window(child);
            }
            new UIElement().waitAndFindElement(driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")));
            String questionName=driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")).getText().trim();
            Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" +questionType + questiontext), "data added over Question Editor is not displaying");
            driver.close();
            driver.switchTo().window(parentWindow);

        } catch (Exception e) {

            Assert.fail("Exception in TC langaugePaleteForQuestionPreviewTextDropDown of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 62,enabled = true)
    public void langaugePaleteForQuestionPreviewNumericEntryWithUnits(){
        try {

            //TC row no 217
            int dataIndex=217; //create assessment for biology
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));

            String questionType="Numeric Entry With Units ";

            new DirectLogin().CMSLogin();
            new SelectCourse().selectLsCourse();
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");
            Thread.sleep(3000);
            new Click().clickbyxpath("//div[@title='" + assessmentname + "']");
            new CMSActions().navigateToQuestionNo(7);
            count++;
            System.out.println("count:"+count);
            manageContent.preview_Button.click();
            String parentWindow=driver.getWindowHandle();
            for(String child:driver.getWindowHandles()){
                driver.switchTo().window(child);
            }
            new UIElement().waitAndFindElement(driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")));
            String questionName=driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")).getText().trim();
            Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" +questionType + questiontext), "data added over Question Editor is not displaying");
            driver.close();
            driver.switchTo().window(parentWindow);

        } catch (Exception e) {

            Assert.fail("Exception in TC langaugePaleteForQuestionPreviewNumericEntryWithUnits of class LangaugePaletteForQuestionCreation", e);
        }
    }
    @Test(priority = 63,enabled = true)
    public void langaugePaleteForQuestionPreviewAdvancedNumeric(){
        try {

            //TC row no 217
            int dataIndex=217; //create assessment for biology
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));

            String questionType="Advanced Numeric ";

            new DirectLogin().CMSLogin();
            new SelectCourse().selectLsCourse();
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");
            Thread.sleep(3000);
            new Click().clickbyxpath("//div[@title='" + assessmentname + "']");
            new CMSActions().navigateToQuestionNo(8);
            count++;
            System.out.println("count:"+count);
            manageContent.preview_Button.click();
            String parentWindow=driver.getWindowHandle();
            for(String child:driver.getWindowHandles()){
                driver.switchTo().window(child);
            }
            new UIElement().waitAndFindElement(driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")));
            String questionName=driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")).getText().trim();
            Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" +questionType + questiontext), "data added over Question Editor is not displaying");
            driver.close();
            driver.switchTo().window(parentWindow);

        } catch (Exception e) {

            Assert.fail("Exception in TC langaugePaleteForQuestionPreviewAdvancedNumeric of class LangaugePaletteForQuestionCreation", e);
        }
    }
    @Test(priority = 64,enabled = true)
    public void langaugePaleteForQuestionPreviewExpressionEvaluator(){
        try {

            //TC row no 217
            int dataIndex=217; //create assessment for biology
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));

            String questionType="Expression Evaluator ";
            new DirectLogin().CMSLogin();
            new SelectCourse().selectLsCourse();
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");
            Thread.sleep(3000);
            new Click().clickbyxpath("//div[@title='" + assessmentname + "']");
            new CMSActions().navigateToQuestionNo(9);
            count++;
            System.out.println("count:"+count);
            manageContent.preview_Button.click();
            String parentWindow=driver.getWindowHandle();
            for(String child:driver.getWindowHandles()){
                driver.switchTo().window(child);
            }
            new UIElement().waitAndFindElement(driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")));
            String questionName=driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")).getText().trim();
            Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" +questionType + questiontext), "data added over Question Editor is not displaying");
            driver.close();
            driver.switchTo().window(parentWindow);

        } catch (Exception e) {

            Assert.fail("Exception in TC langaugePaleteForQuestionPreviewExpressionEvaluator of class LangaugePaletteForQuestionCreation", e);
        }
    }
    @Test(priority = 65,enabled = true)
    public void langaugePaleteForQuestionPreviewEssay(){
        try {

            //TC row no 217
            int dataIndex=217; //create assessment for biology
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));

            String questionType="Essay ";

            new DirectLogin().CMSLogin();
            new SelectCourse().selectLsCourse();
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");
            Thread.sleep(3000);
            new Click().clickbyxpath("//div[@title='" + assessmentname + "']");
            new CMSActions().navigateToQuestionNo(10);
            count++;
            System.out.println("count:"+count);
            manageContent.preview_Button.click();
            String parentWindow=driver.getWindowHandle();
            for(String child:driver.getWindowHandles()){
                driver.switchTo().window(child);
            }
            new UIElement().waitAndFindElement(driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")));
            String questionName=driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")).getText().trim();
            Assert.assertTrue(questionName.contains(questionType + questiontext + "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"), "data added over Question Editor is not displaying");
            driver.close();
            driver.switchTo().window(parentWindow);

        } catch (Exception e) {

            Assert.fail("Exception in TC langaugePaleteForQuestionPreviewEssay of class LangaugePaletteForQuestionCreation", e);
        }
    }
    @Test(priority = 66,enabled = true)
    public void langaugePaleteForQuestionPreviewWriteBoard(){
        try {

            //TC row no 217
            int dataIndex=217; //create assessment for biology
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));

            String questionType="Write Board ";


            new DirectLogin().CMSLogin();
            new SelectCourse().selectLsCourse();
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");
            Thread.sleep(3000);
            new Click().clickbyxpath("//div[@title='" + assessmentname + "']");
            new CMSActions().navigateToQuestionNo(11);
            count++;
            System.out.println("count:"+count);
            manageContent.preview_Button.click();
            String parentWindow=driver.getWindowHandle();
            for(String child:driver.getWindowHandles()){
                driver.switchTo().window(child);
            }
            new UIElement().waitAndFindElement(driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")));
            String questionName=driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")).getText().trim();
            Assert.assertTrue(questionName.contains(questionType + questiontext + "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"), "data added over Question Editor is not displaying");
            driver.close();
            driver.switchTo().window(parentWindow);

        } catch (Exception e) {

            Assert.fail("Exception in TC langaugePaleteForQuestionPreviewWriteBoard of class LangaugePaletteForQuestionCreation", e);
        }
    }
    @Test(priority = 67,enabled = true)
    public void langaugePaleteForQuestionPreviewaudio(){
        try {

            //TC row no 217
            int dataIndex=217; //create assessment for biology
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));

            String questionType="audio ";

            new DirectLogin().CMSLogin();
            new SelectCourse().selectLsCourse();
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");
            Thread.sleep(3000);
            new Click().clickbyxpath("//div[@title='" + assessmentname + "']");
            new CMSActions().navigateToQuestionNo(12);
            Thread.sleep(5000);
            manageContent.preview_Button.click();
            String parentWindow=driver.getWindowHandle();
            for(String child:driver.getWindowHandles()){
                driver.switchTo().window(child);
            }
            new UIElement().waitAndFindElement(driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")));
            String questionName=driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")).getText().trim();
            Assert.assertTrue(questionName.contains(questionType + questiontext + "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"), "data added over Question Editor is not displaying");
            driver.close();
            driver.switchTo().window(parentWindow);

        } catch (Exception e) {

            Assert.fail("Exception in TC langaugePaleteForQuestionPreviewaudio of class LangaugePaletteForQuestionCreation", e);
        }
    }
    @Test(priority = 68,enabled = false)
    public void langaugePaleteForQuestionPreviewLabelAnImage(){
        try {

            //TC row no 217
            int dataIndex=217; //create assessment for biology
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));

            String questionType="Label An Image(Text) question text";

            new DirectLogin().CMSLogin();
            new SelectCourse().selectLsCourse();
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");
            Thread.sleep(3000);
            new Click().clickbyxpath("//div[@title='" + assessmentname + "']");
            new CMSActions().navigateToQuestionNo(13);
            count++;
            System.out.println("count:"+count);
            manageContent.preview_Button.click();
            String parentWindow=driver.getWindowHandle();
            for(String child:driver.getWindowHandles()){
                driver.switchTo().window(child);
            }
            new UIElement().waitAndFindElement(driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")));
            String questionName=driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")).getText().trim();
            Assert.assertTrue(questionName.contains(questionType + questiontext + "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"), "data added over Question Editor is not displaying");
            driver.close();
            driver.switchTo().window(parentWindow);

        } catch (Exception e) {

            Assert.fail("Exception in TC langaugePaleteForQuestionPreviewLabelAnImage of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 69,enabled = false)
    public void langaugePaleteForQuestionPreviewLabelAnImageDropdown(){
        try {

            //TC row no 217
            int dataIndex=217; //create assessment for biology
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", Integer.toString(dataIndex));

            String questionType="Label An Image(dropdown)";

            new DirectLogin().CMSLogin();
            new SelectCourse().selectLsCourse();
            new Click().clickbyxpath("//div[contains(@title,'" + chapterName + "')]");
            Thread.sleep(3000);
            new Click().clickbyxpath("//div[@title='" + assessmentname + "']");
            new CMSActions().navigateToQuestionNo(14);
            count++;
            System.out.println("count:"+count);
            manageContent.preview_Button.click();
            String parentWindow=driver.getWindowHandle();
            for(String child:driver.getWindowHandles()){
                driver.switchTo().window(child);
            }
            new UIElement().waitAndFindElement(driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")));
            String questionName=driver.findElement(By.xpath(".//*[@id='question-raw-content-preview']/label")).getText().trim();
            Assert.assertTrue(questionName.contains(questionType + questiontext + "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"), "data added over Question Editor is not displaying");
            driver.close();
            driver.switchTo().window(parentWindow);

        } catch (Exception e) {

            Assert.fail("Exception in TC langaugePaleteForQuestionPreviewLabelAnImageDropdown of class LangaugePaletteForQuestionCreation", e);
        }
    }







    @Test(priority = 70,enabled = true)
    public void langaugePaleteForMPQQuestion(){
        try {

            //TC row no 222
            int dataIndex=222;
            new DirectLogin().CMSLogin();
            new SelectCourse().selectLsCourse();
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Multi Part", 222);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='question-edit']/div/div[1]")));
            CreateMultipartQuestion(222);
            WebElement html = driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            verificationForSpanish();
            verificationForFrench();
            verificationForItalian();

        } catch (Exception e) {

            Assert.fail("Exception in TC langaugePaleteForMPQQuestion of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 71,enabled = true)
    public void langaugePaleteForTextEntryMPQQuestion(){
        try {

            //TC row no 222
            int dataIndex=222;
            ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
            JavascriptExecutor jse=(JavascriptExecutor)driver;
            new DirectLogin().CMSLogin();
            new SelectCourse().selectLsCourse();
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Multi Part", 222);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='question-edit']/div/div[1]")));
            CreateTextEntryMultipartQuestion(222);

            Assert.assertEquals(new BooleanValue().presenceOfElement(88, By.linkText("Enable Spanish Palette")), false, "Enable Spanish Palette is still displaying");
            new Click().clickBycssselector("a[title='Select Language Palette']"); //select language
            Assert.assertEquals(manageContent.selectLangaugePalete_TextEntry.get(1).getAttribute("rel"), "spanish");
            Assert.assertEquals(manageContent.selectLangaugePalete_TextEntry.get(2).getAttribute("rel"),"italian");
            Assert.assertEquals(manageContent.selectLangaugePalete_TextEntry.get(3).getAttribute("rel"),"french");

            //TC row no 92
            manageContent.selectLangaugePalete_TextEntry.get(1).click(); //click on the spanish
            new Click().clickByclassname("spanish-popup"); //click on the spanish icon


            Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"Spanish palette popup is not opened");

            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
            }

            String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
            System.out.println("input String value:" + inputStringValue);


            //Tc row no 17
            Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

            //Tc row no 18
            Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
            //Tc row no 19
            Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
            Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

            //TC row no 20
            Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

            //TC row no 21
            //TC row no 22
            manageContent.langaugePalette_saveButton.click(); //click on the save button
            manageContent.accept_answer.click(); //click on the accept answer;


            //TC row no 23
            Assert.assertEquals(manageContent.textEntryQuestionContent.getAttribute("value").trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the characters is not getting added to the question content area.");

            //TC row no 24
            new Click().clickByclassname("spanish-popup"); //click on the spanish icon
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
                element.click();
                element.click();
                element.click();
            }
            //TC row no 25
            manageContent.langaugePalette_cancelButton.click();//click on the cancel button

            Assert.assertEquals(manageContent.langaugePalette_closeIcon.isDisplayed(),false,"The Editor is not getting closed.");
            Assert.assertEquals(manageContent.textEntryQuestionContent.getAttribute("value").trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿","The Question content area is getting saved be saved with language content.");
            //TC row no 26
            int width=Integer.parseInt(manageContent.textEntryQuestionContent.getCssValue("width").substring(0,2));

            new Click().clickByclassname("spanish-popup"); //click on the spanish icon
            manageContent.langaugePalette_closeIcon.click();//click on the close icon
            Assert.assertEquals(manageContent.langaugePalette_closeIcon.isDisplayed(), false, "The Editor is not getting closed.");

            new Click().clickByclassname("spanish-popup"); //click on the spanish icon
            for(WebElement element:manageContent.langaugePalette_allinput){
                element.click();
                element.click();
                element.click();
                element.click();
            }

            manageContent.langaugePalette_saveButton.click(); //click on the save button
            manageContent.accept_answer.click(); //click on the accept answer;
            int width1=Integer.parseInt(manageContent.textEntryQuestionContent.getCssValue("width").substring(0,2));

            //Tc row no 106
            if(width>width1){
                Assert.fail("The width of the question textbox is not  increased accordingly");
            }
            Assert.assertEquals(manageContent.textEntryQuestionContent.getAttribute("value").length(),80,"All the characters is not supported as existing Functionality.");

            //TC row no 117
            new Click().clickByclassname("spanish-popup"); //click on the spanish icon
            manageContent.langaugePalette_cancelButton.click();//click on the cancel button
            Assert.assertEquals(manageContent.langaugePalette_closeIcon.isDisplayed(),false,"The Spanish Editor is not getting closed.");


        } catch (Exception e) {

            Assert.fail("Exception in TC langaugePaleteForTextEntryMPQQuestion of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 72,enabled = true)
    public void langaugePaleteForMultipleChoiceMPQQuestion(){
        try {

            //TC row no 222
            new DirectLogin().CMSLogin();
            new SelectCourse().selectLsCourse();
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Multi Part", 222);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='question-edit']/div/div[1]")));
            CreateMultipleChoiceMultipartQuestion(222);
            WebElement html = driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            checkForMultipleChoiceAndMultipleSelection(222, "mchoice");
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.NUMPAD0));

        } catch (Exception e) {

            Assert.fail("Exception in TC langaugePaleteForMultipleChoiceMPQQuestion of class LangaugePaletteForQuestionCreation", e);
        }
    }
    @Test(priority = 73,enabled = true)
    public void langaugePaleteForMultipleSelectionMPQQuestion(){
        try {

            //TC row no 222
            new DirectLogin().CMSLogin();
            new SelectCourse().selectLsCourse();
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Multi Part", 222);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='question-edit']/div/div[1]")));
            CreateMultipleSelectionMultipartQuestion(222);
            WebElement html = driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            checkForMultipleChoiceAndMultipleSelection(222, "mselection");
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.NUMPAD0));

        } catch (Exception e) {

            Assert.fail("Exception in TC langaugePaleteForMultipleSelectionMPQQuestion of class LangaugePaletteForQuestionCreation", e);
        }
    }

    @Test(priority = 74,enabled = true)
    public void langaugePaleteForEasyMPQQuestion(){
        try {

            new DirectLogin().CMSLogin();
            new SelectCourse().selectLsCourse();
            new SelectCourse().selectChapterByIndex(0);//select 1st chapter
            new Assignment().selectParticularQuestionInCMS("Multi Part", 222);
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='question-edit']/div/div[1]")));
            CreateEssayMultipartQuestion(222);
            WebElement html = driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            verificationForSpanish();
            verificationForFrench();
            verificationForItalian();

        } catch (Exception e) {

            Assert.fail("Exception in TC langaugePaleteForEasyMPQQuestion of class LangaugePaletteForQuestionCreation", e);
        }
    }

    public  void verificationForSpanish() throws InterruptedException {

        JavascriptExecutor jse=(JavascriptExecutor)driver;
        ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
        new UIElement().waitAndFindElement(manageContent.langaugePalette);
        //TC row no 14

        try {
            new WebDriverWait(driver,120).until(ExpectedConditions.visibilityOf(manageContent.questionTextBox));
            new Click().clickbyxpath("//div[@id='question-edit']/div/div[1]");
            new Click().clickbyxpath("//div[@id='question-edit']/div/div[1]");
        } catch (Exception e) {
            new WebDriverWait(driver,120).until(ExpectedConditions.visibilityOf(manageContent.questionTextBox));
            manageContent.questionTextBox.click();//click on question text box

        }

        try {
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
        } catch (Exception e) {
            new Click().clickBycssselector("a[class='re-icon re-language redactor-btn-image']");
        }


        Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
        Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(),"Italian");
        Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(),"French");

        //manageContent.langaugePalette_spanish.click(); //click on the spanish language
        clickOnTheSpanish();

        Thread.sleep(2000);
        Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"Spanish palette popup is not opened");

        for(WebElement element:manageContent.langaugePalette_allinput){
            element.click();
        }

        String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
        System.out.println("input String value:" + inputStringValue);

        Assert.assertEquals(inputStringValue.length(),16,"All the characters is not supported as existing Functionality.");

        //Tc row no 17
        Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

        //Tc row no 18
        Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
        //Tc row no 19
        Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
        Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

        //TC row no 20
        Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

        //TC row no 21
        Assert.assertEquals(inputStringValue,"áÁéÉíÍñÑóÓúÚüÜ¡¿","The selected characters is not displayed in the text box of the Palette. ");
        //TC row no 22
        manageContent.langaugePalette_saveButton.click(); //click on the save button

        //TC row no 23
        Assert.assertEquals(manageContent.questionTextBox.getText().trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the characters is not getting added to the question content area.");

        //TC row no 24
        //manageContent.langaugePalette_spanish.click(); //click on the spanish language
        clickOnTheSpanish();

        for(WebElement element:manageContent.langaugePalette_allinput){
            element.click();
            element.click();
            element.click();
            element.click();
            element.click();
        }

        //TC row no 25
        manageContent.langaugePalette_cancelButton.click();//click on the cancel button

        Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")),false,"The Editor is not getting closed.");
        Assert.assertEquals(manageContent.questionTextBox.getText().trim(),"áÁéÉíÍñÑóÓúÚüÜ¡¿","All the characters is not getting added to the question content area.");
        //TC row no 27
        manageContent.questionTextBox.click();//click on question text box
        manageContent.questionTextBox.click();//click on question text box

        new UIElement().waitAndFindElement(manageContent.langaugePalette);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);//click on the langauge pallete
        // manageContent.langaugePalette_spanish.click(); //click on the spanish language
        clickOnTheSpanish();
        manageContent.langaugePalette_textBox.sendKeys("abc123");

        //TC row no 26
        manageContent.langaugePalette_closeIcon.click();//click on the close icon
        Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");

    }

    public  void verificationForItalian(){

        JavascriptExecutor jse=(JavascriptExecutor)driver;
        ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
        new UIElement().waitAndFindElement(manageContent.langaugePalette);
        //TC row no 14
        manageContent.questionTextBox.click();//click on question text box
        manageContent.questionTextBox.click();//click on question text box
        manageContent.questionTextBox.clear();

        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);

        Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
        Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(),"Italian");
        Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(),"French");
        // manageContent.langaugePalette_Italian.click(); //click on the spanish language
        clickOnTheItalian();
        Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"Spanish palette popup is not opened");

        for(WebElement element:manageContent.langaugePalette_allinput){
            element.click();
        }

        String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
        System.out.println("input String value:" + inputStringValue);

        Assert.assertEquals(inputStringValue.length(),22,"All the characters is not supported as existing Functionality.");

        //Tc row no 17
        Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

        //Tc row no 18
        Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
        //Tc row no 19
        Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
        Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

        //TC row no 20
        Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Italian Editor", "The popup is not having text has \"Italian Editor\" text over header, with Left aligned.");

        //TC row no 21
        Assert.assertEquals(inputStringValue,"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤","The selected characters is not displayed in the text box of the Palette. ");
        //TC row no 22
        manageContent.langaugePalette_saveButton.click(); //click on the save button

        //TC row no 23
        Assert.assertEquals(manageContent.questionTextBox.getText().trim(),"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤","All the characters is not getting added to the question content area.");

        //TC row no 24
        //manageContent.langaugePalette_Italian.click(); //click on the spanish language
        clickOnTheItalian();
        for(WebElement element:manageContent.langaugePalette_allinput){
            element.click();
            element.click();
            element.click();
            element.click();
            element.click();
        }

        //TC row no 25
        manageContent.langaugePalette_cancelButton.click();//click on the cancel button

        Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")),false,"The Editor is not getting closed.");
        Assert.assertEquals(manageContent.questionTextBox.getText().trim(),"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤","All the characters is not getting added to the question content area.");
        //TC row no 27
        manageContent.questionTextBox.click();//click on question text box
        manageContent.questionTextBox.click();//click on question text box

        new UIElement().waitAndFindElement(manageContent.langaugePalette);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);//click on the langauge pallete
        // manageContent.langaugePalette_Italian.click(); //click on the spanish language
        clickOnTheItalian();
        manageContent.langaugePalette_textBox.sendKeys("abc123");

        //TC row no 26
        manageContent.langaugePalette_closeIcon.click();//click on the close icon
        Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");

    }

    public  void verificationForFrench(){

        JavascriptExecutor jse=(JavascriptExecutor)driver;
        ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
        new UIElement().waitAndFindElement(manageContent.langaugePalette);
        //TC row no 14

        manageContent.questionTextBox.click();//click on question text box
       // manageContent.questionTextBox.click();//click on question text box
        manageContent.questionTextBox.clear();

        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);

        Assert.assertEquals(manageContent.langaugePalette_option.get(0).getText(), "Spanish");
        Assert.assertEquals(manageContent.langaugePalette_option.get(1).getText(),"Italian");
        Assert.assertEquals(manageContent.langaugePalette_option.get(2).getText(),"French");
        //manageContent.langaugePalette_French.click(); //click on the spanish language
        clickOnTheFrench();
        Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"Spanish palette popup is not opened");

        for(WebElement element:manageContent.langaugePalette_allinput){
            element.click();
        }

        String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
        System.out.println("input String value:" + inputStringValue);

        Assert.assertEquals(inputStringValue.length(),24,"All the characters is not supported as existing Functionality.");

        //Tc row no 17
        Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

        //Tc row no 18
        Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
        //Tc row no 19
        Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
        Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

        //TC row no 20
        Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "French Editor", "The popup is not having text has \"French Editor\" text over header, with Left aligned.");

        //TC row no 21
        Assert.assertEquals(inputStringValue,"ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ","The selected characters is not displayed in the text box of the Palette. ");
        //TC row no 22
        manageContent.langaugePalette_saveButton.click(); //click on the save button

        //TC row no 23
        Assert.assertEquals(manageContent.questionTextBox.getText().trim(),"ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ","All the characters is not getting added to the question content area.");

        //TC row no 24
        // manageContent.langaugePalette_French.click(); //click on the spanish language
        clickOnTheFrench();
        for(WebElement element:manageContent.langaugePalette_allinput){
            element.click();
            element.click();
            element.click();
        }

        //TC row no 25
        manageContent.langaugePalette_cancelButton.click();//click on the cancel button

        Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")),false,"The Editor is not getting closed.");
        Assert.assertEquals(manageContent.questionTextBox.getText().trim(),"ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ","All the characters is not getting added to the question content area.");
        //TC row no 27
        manageContent.questionTextBox.click();//click on question text box
        manageContent.questionTextBox.click();//click on question text box

        new UIElement().waitAndFindElement(manageContent.langaugePalette);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);//click on the langauge pallete
        // manageContent.langaugePalette_French.click(); //click on the spanish language
        clickOnTheFrench();
        manageContent.langaugePalette_textBox.sendKeys("abc123");

        //TC row no 26
        manageContent.langaugePalette_closeIcon.click();//click on the close icon
        Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");
    }


    public  void clickOnLanguagePalette()
    {
        ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
        List<WebElement>content=manageContent.langaugePalettes;
        for (WebElement contents:content){
            if(contents.isDisplayed()){
                contents.click();
                break;
            }
        }
    }

    public  void clickOnTheSpanish()
    {
        ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
        for(WebElement ele:manageContent.langaugePalette_spanishs){
            if(ele.isDisplayed()){
                ele.click();//click on the spanish language
            }
        }
    }
    public  void clickOnTheItalian()
    {
        ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
        for(WebElement ele:manageContent.langaugePalette_Italians){
            if(ele.isDisplayed()){
                ele.click();////click on the italian language
            }
        }
    }

    public  void clickOnTheFrench()
    {
        ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
        for(WebElement ele:manageContent.langaugePalette_Frenchs){
            if(ele.isDisplayed()){
                ele.click();//click on the spanish language
            }
        }
    }


    public  void contentVerificationForSpanish(int dataIndex){

        String hint = ReadTestData.readDataByTagName("", "hint", Integer.toString(dataIndex));
        JavascriptExecutor jse=(JavascriptExecutor)driver;
        ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
        new UIElement().waitAndFindElement(manageContent.langaugePalette);
        //TC row no 14
        if(hint!=null){
            if(hint.equals("true"))
            {
                manageContent.contentHint.clear();
                Assert.assertEquals(manageContent.langaugePalette_option.get(6).getText(), "Spanish");
                Assert.assertEquals(manageContent.langaugePalette_option.get(7).getText(), "Italian");
                Assert.assertEquals(manageContent.langaugePalette_option.get(8).getText(), "French");
            }
        }
        else {
            manageContent.contentSolution.clear();
            Assert.assertEquals(manageContent.langaugePalette_option.get(3).getText(), "Spanish");
            Assert.assertEquals(manageContent.langaugePalette_option.get(4).getText(), "Italian");
            Assert.assertEquals(manageContent.langaugePalette_option.get(5).getText(), "French");
        }

        for(WebElement ele:manageContent.langaugePalette_spanishs){
            if(ele.isDisplayed()){
                ele.click();////click on the spanish language
            }
        }
        Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"Spanish palette popup is not opened");

        for(WebElement element:manageContent.langaugePalette_allinput){
            element.click();
        }

        String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
        System.out.println("input String value:" + inputStringValue);

        Assert.assertEquals(inputStringValue.length(),16,"All the characters is not supported as existing Functionality.");

        //Tc row no 17
        Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

        //Tc row no 18
        Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
        //Tc row no 19
        Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
        Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

        //TC row no 20
        Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");

        //TC row no 21
        Assert.assertEquals(inputStringValue,"áÁéÉíÍñÑóÓúÚüÜ¡¿","The selected characters is not displayed in the text box of the Palette. ");
        //TC row no 22
        manageContent.langaugePalette_saveButton.click(); //click on the save button

        //TC row no 23
        if(hint!=null){
            if(hint.equals("true"))
            {
                Assert.assertEquals(manageContent.contentHint.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");
            }
        }
        else {
            Assert.assertEquals(manageContent.contentSolution.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");
        }
        //TC row no 24
        for(WebElement ele:manageContent.langaugePalette_spanishs){
            if(ele.isDisplayed()){
                ele.click();////click on the spanish language
            }
        }
        for(WebElement element:manageContent.langaugePalette_allinput) {
            if (element.isDisplayed()) {
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
            }
        }

        //TC row no 25
        manageContent.langaugePalette_cancelButton.click();//click on the cancel button

        Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")),false,"The Editor is not getting closed.");

        //TC row no 23
        if(hint!=null){
            if(hint.equals("true"))
            {
                Assert.assertEquals(manageContent.contentHint.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");
                manageContent.contentHint.click();
            }
        }
        else {
            Assert.assertEquals(manageContent.contentSolution.getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");
            manageContent.contentSolution.click();//click on question text box

        }

        //TC row no 27
        clickOnLanguagePalette();
        new UIElement().waitAndFindElement(manageContent.langaugePalette);
        for(WebElement ele:manageContent.langaugePalette_spanishs){
            if(ele.isDisplayed()){
                ele.click();////click on the spanish language
            }
        }
        manageContent.langaugePalette_textBox.sendKeys("abc123");

        //TC row no 26
        manageContent.langaugePalette_closeIcon.click();//click on the close icon
        Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");

    }

    public  void contentVerificationForItalian(int dataIndex){
        String hint = ReadTestData.readDataByTagName("", "hint", Integer.toString(dataIndex));
        JavascriptExecutor jse=(JavascriptExecutor)driver;
        ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
        new UIElement().waitAndFindElement(manageContent.langaugePalette);
        //TC row no 14
        if(hint!=null){
            if(hint.equals("true"))
            {
                manageContent.contentHint.clear();
                Assert.assertEquals(manageContent.langaugePalette_option.get(6).getText(), "Spanish");
                Assert.assertEquals(manageContent.langaugePalette_option.get(7).getText(), "Italian");
                Assert.assertEquals(manageContent.langaugePalette_option.get(8).getText(), "French");
            }
        }
        else {
            manageContent.contentSolution.clear();
            Assert.assertEquals(manageContent.langaugePalette_option.get(3).getText(), "Spanish");
            Assert.assertEquals(manageContent.langaugePalette_option.get(4).getText(), "Italian");
            Assert.assertEquals(manageContent.langaugePalette_option.get(5).getText(), "French");
        }


        for(WebElement ele:manageContent.langaugePalette_Italians){
            if(ele.isDisplayed()){
                ele.click();////click on the spanish language
            }
        }
        for(WebElement element:manageContent.langaugePalette_allinput) {
            if (element.isDisplayed()) {
                element.click();
            }
        }

        String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
        System.out.println("input String value:" + inputStringValue);

        Assert.assertEquals(inputStringValue.length(),22,"All the characters is not supported as existing Functionality.");

        //Tc row no 17
        Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

        //Tc row no 18
        Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
        //Tc row no 19
        Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
        Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

        //TC row no 20
        Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Italian Editor", "The popup is not having text has \"Italian Editor\" text over header, with Left aligned.");

        //TC row no 21
        Assert.assertEquals(inputStringValue,"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤","The selected characters is not displayed in the text box of the Palette. ");
        //TC row no 22
        manageContent.langaugePalette_saveButton.click(); //click on the save button

        //TC row no 23
        if(hint!=null){
            if(hint.equals("true"))
            {
                Assert.assertEquals(manageContent.contentHint.getText().trim(), "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "All the characters is not getting added to the question content area.");
            }
        }
        else {
            Assert.assertEquals(manageContent.contentSolution.getText().trim(), "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "All the characters is not getting added to the question content area.");
        }
        //TC row no 24
        for(WebElement ele:manageContent.langaugePalette_Italians){
            if(ele.isDisplayed()){
                ele.click();////click on the spanish language
            }
        }
        for(WebElement element:manageContent.langaugePalette_allinput) {
            if (element.isDisplayed()) {
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
            }
        }
        //TC row no 25
        manageContent.langaugePalette_cancelButton.click();//click on the cancel button

        Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")),false,"The Editor is not getting closed.");

        if(hint!=null){
            if(hint.equals("true"))
            {
                Assert.assertEquals(manageContent.contentHint.getText().trim(),"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤","All the characters is not getting added to the question content area.");
                manageContent.contentHint.click();
            }
        }
        else {
            Assert.assertEquals(manageContent.contentSolution.getText().trim(),"ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤","All the characters is not getting added to the question content area.");
            manageContent.contentSolution.click();//click on question text box

        }

        //TC row no 27

        new UIElement().waitAndFindElement(manageContent.langaugePalette);
        clickOnLanguagePalette();
        for(WebElement ele:manageContent.langaugePalette_Italians){
            if(ele.isDisplayed()){
                ele.click();////click on the spanish language
            }
        }
        manageContent.langaugePalette_textBox.sendKeys("abc123");

        //TC row no 26
        manageContent.langaugePalette_closeIcon.click();//click on the close icon
        Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");

    }

    public  void contentVerificationForFrench(int dataIndex){
        String hint = ReadTestData.readDataByTagName("", "hint", Integer.toString(dataIndex));
        JavascriptExecutor jse=(JavascriptExecutor)driver;
        ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
        new UIElement().waitAndFindElement(manageContent.langaugePalette);
        //TC row no 14
        if(hint!=null){
            if(hint.equals("true"))
            {
                manageContent.contentHint.clear();
                Assert.assertEquals(manageContent.langaugePalette_option.get(6).getText(), "Spanish");
                Assert.assertEquals(manageContent.langaugePalette_option.get(7).getText(), "Italian");
                Assert.assertEquals(manageContent.langaugePalette_option.get(8).getText(), "French");
            }
        }
        else {
            manageContent.contentSolution.clear();
            Assert.assertEquals(manageContent.langaugePalette_option.get(3).getText(), "Spanish");
            Assert.assertEquals(manageContent.langaugePalette_option.get(4).getText(), "Italian");
            Assert.assertEquals(manageContent.langaugePalette_option.get(5).getText(), "French");
        }

        for(WebElement ele:manageContent.langaugePalette_Frenchs){
            if(ele.isDisplayed()){
                ele.click();////click on the french language
            }
        }
        for(WebElement element:manageContent.langaugePalette_allinput) {
            if (element.isDisplayed()) {
                element.click();
            }
        }

        String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
        System.out.println("input String value:" + inputStringValue);

        Assert.assertEquals(inputStringValue.length(),24,"All the characters is not supported as existing Functionality.");

        //Tc row no 17
        Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

        //Tc row no 18
        Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
        //Tc row no 19
        Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
        Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

        //TC row no 20
        Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "French Editor", "The popup is not having text has \"French Editor\" text over header, with Left aligned.");

        //TC row no 21
        Assert.assertEquals(inputStringValue,"ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ","The selected characters is not displayed in the text box of the Palette. ");
        //TC row no 22
        manageContent.langaugePalette_saveButton.click(); //click on the save button

        //TC row no 23
        if(hint!=null){
            if(hint.equals("true"))
            {
                Assert.assertEquals(manageContent.contentHint.getText().trim(),"ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ","All the characters is not getting added to the question content area.");
            }
        }
        else {
            Assert.assertEquals(manageContent.contentSolution.getText().trim(),"ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ","All the characters is not getting added to the question content area.");
        }

        //TC row no 24
        for(WebElement ele:manageContent.langaugePalette_Frenchs){
            if(ele.isDisplayed()){
                ele.click();////click on the spanish language
            }
        }
        for(WebElement element:manageContent.langaugePalette_allinput) {
            if (element.isDisplayed()) {
                element.click();
                element.click();
            }
        }
        //TC row no 25
        manageContent.langaugePalette_cancelButton.click();//click on the cancel button

        Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")),false,"The Editor is not getting closed.");

        if(hint!=null){
            if(hint.equals("true"))
            {
                Assert.assertEquals(manageContent.contentHint.getText().trim(),"ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ","All the characters is not getting added to the question content area.");
                manageContent.contentHint.click();
            }
        }
        else {
            Assert.assertEquals(manageContent.contentSolution.getText().trim(),"ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ","All the characters is not getting added to the question content area.");
            manageContent.contentSolution.click();//click on question text box

        }
        //TC row no 27

        new UIElement().waitAndFindElement(manageContent.langaugePalette);
        clickOnLanguagePalette();
        for(WebElement ele:manageContent.langaugePalette_Frenchs){
            if(ele.isDisplayed()){
                ele.click();////click on the spanish language
            }
        }
        manageContent.langaugePalette_textBox.sendKeys("abc123");

        //TC row no 26
        manageContent.langaugePalette_closeIcon.click();//click on the close icon
        Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")), false, "The Editor is not getting closed.");

    }

    public   void checkForMultipleChoiceAndMultipleSelection(int dataIndex,String langauge) throws InterruptedException {


        String langaugeOption = ReadTestData.readDataByTagName("", "langaugeOption", Integer.toString(dataIndex));
        ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
        JavascriptExecutor jse=(JavascriptExecutor)driver;
        WebElement html = driver.findElement(By.tagName("html"));
        html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
        if(langauge.equals("mchoice")){
            new Click().clickBycssselector("div[class='single-select-choice-icon single-select-choice-icon-deselect']"); //select correct answer as A

        }
        if(langauge.equals("mselection")){
            new Click().clickBycssselector("div[class='multiple-select-choice-icon multiple-select-choice-icon-deselect']"); //select correct answer as A

        }
        List<WebElement> answerOptions = driver.findElements(By.xpath("//*[starts-with(@id, 'popupEditor_')]"));
        answerOptions.get(0).click();
        clickOnLanguagePalette();
        Thread.sleep(4000);
        Assert.assertEquals(manageContent.multipleChoice_spanish.get(1).getText(), "Spanish");
        Assert.assertEquals(manageContent.multipleChoice_italian.get(1).getText(),"Italian");
        Assert.assertEquals(manageContent.multipleChoice_fench.get(1).getText(),"French");

        if(langaugeOption!=null){
            if(langaugeOption.equals("italian")){
                for(WebElement ele:manageContent.langaugePalette_Italians){
                    if(ele.isDisplayed()){
                        ele.click();////click on the italian language
                    }
                }

            }
        }
        if(langaugeOption!=null){
            if(langaugeOption.equals("french")){
                for(WebElement ele:manageContent.langaugePalette_Frenchs){
                    if(ele.isDisplayed()){
                        ele.click();////click on the french language
                    }
                }

            }
        }
        if(langaugeOption!=null){
            if(langaugeOption.equals("spanish")){
                for(WebElement ele:manageContent.langaugePalette_spanishs){
                    if(ele.isDisplayed()){
                        ele.click();////click on the spanish language
                    }
                }

            }
        }

        Assert.assertTrue(manageContent.langaugePalette_popup.isDisplayed(),"Spanish palette popup is not opened");

        for(WebElement element:manageContent.langaugePalette_allinput){
            element.click();
        }

        String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
        System.out.println("input String value:" + inputStringValue);

        //Tc row no 17
        Assert.assertTrue(manageContent.langaugePalette_textBox.isDisplayed(),"The pop up is not having a text area..");

        //Tc row no 18
        Assert.assertTrue(manageContent.langaugePalette_closeIcon.isDisplayed(),"The pop up in not having close icon");
        //Tc row no 19
        Assert.assertTrue(manageContent.langaugePalette_saveButton.isDisplayed(),"The pop up in not having save button");
        Assert.assertTrue(manageContent.langaugePalette_cancelButton.isDisplayed(),"The pop up in not having cancel button");

        //TC row no 20
        if(langaugeOption!=null){
            if(langaugeOption.equals("italian")){
                Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Italian Editor", "The popup is not having text has \"Italian Editor\" text over header, with Left aligned.");

            }
        }

        if(langaugeOption!=null){
            if(langaugeOption.equals("french")){
                Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "French Editor", "The popup is not having text has \"French Editor\" text over header, with Left aligned.");

            }
        }
        if(langaugeOption!=null){
            if(langaugeOption.equals("spanish")){
                Assert.assertEquals(manageContent.langaugePalette_header.getText().trim(), "Spanish Editor", "The popup is not having text has \"Spanish Editor\" text over header, with Left aligned.");
            }
        }

        //TC row no 21

        //TC row no 22
        if(langaugeOption!=null){
            if(langaugeOption.equals("italian")){
                Assert.assertEquals(inputStringValue, "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "The selected characters is not displayed in the text box of the Palette. ");

            }
        }

        if(langaugeOption!=null){
            if(langaugeOption.equals("french")){
                Assert.assertEquals(inputStringValue, "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ", "The selected characters is not displayed in the text box of the Palette. ");

            }
        }
        if(langaugeOption!=null){
            if(langaugeOption.equals("spanish")){
                Assert.assertEquals(inputStringValue, "áÁéÉíÍñÑóÓúÚüÜ¡¿", "The selected characters is not displayed in the text box of the Palette. ");
            }
        }
        manageContent.langaugePalette_saveButton.click(); //click on the save button
        int width=Integer.parseInt(answerOptions.get(0).getCssValue("width").substring(0, 2));



        if(langaugeOption!=null){
            if(langaugeOption.equals("italian")){
                Assert.assertEquals(answerOptions.get(0).getText().trim(), "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "All the characters is not getting added to the question content area.");

            }
        }

        if(langaugeOption!=null){
            if(langaugeOption.equals("french")){
                Assert.assertEquals(answerOptions.get(0).getText().trim(), "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ", "All the characters is not getting added to the question content area.");

            }
        }
        if(langaugeOption!=null){
            if(langaugeOption.equals("spanish")){
                Assert.assertEquals(answerOptions.get(0).getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");
            }
        }

        if(langaugeOption!=null){
            if(langaugeOption.equals("italian")){
                clickOnTheItalian();

            }
        }

        if(langaugeOption!=null){
            if(langaugeOption.equals("french")){
                clickOnTheFrench();

            }
        }
        if(langaugeOption!=null){
            if(langaugeOption.equals("spanish")){
                clickOnTheSpanish();
            }
        }

        manageContent.langaugePalette_cancelButton.click();//click on the cancel button

        Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")),false,"The Editor is not getting closed.");

        if(langaugeOption!=null){
            if(langaugeOption.equals("italian")){
                Assert.assertEquals(answerOptions.get(0).getText().trim(), "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤", "All the characters is not getting added to the question content area.");

            }
        }

        if(langaugeOption!=null){
            if(langaugeOption.equals("french")){
                Assert.assertEquals(answerOptions.get(0).getText().trim(), "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ", "All the characters is not getting added to the question content area.");

            }
        }
        if(langaugeOption!=null){
            if(langaugeOption.equals("spanish")){
                Assert.assertEquals(answerOptions.get(0).getText().trim(), "áÁéÉíÍñÑóÓúÚüÜ¡¿", "All the characters is not getting added to the question content area.");
            }
        }

        if(langaugeOption!=null){
            if(langaugeOption.equals("italian")){
                clickOnTheItalian();

            }
        }
        answerOptions.get(0).click();

        clickOnLanguagePalette();
        if(langaugeOption!=null){
            if(langaugeOption.equals("italian")){
                clickOnTheItalian();

            }
        }

        if(langaugeOption!=null){
            if(langaugeOption.equals("french")){
                clickOnTheFrench();

            }
        }
        if(langaugeOption!=null){
            if(langaugeOption.equals("spanish")){
                clickOnTheSpanish();
            }
        }

        for(WebElement element:manageContent.langaugePalette_allinput) {
            if (element.isDisplayed()) {
                element.click();
                element.click();
                element.click();
                element.click();
                element.click();
            }
        }
        manageContent.langaugePalette_saveButton.click(); //click on the save button
        int width1=Integer.parseInt(answerOptions.get(0).getCssValue("width").substring(0,2));

        if(width>width1){
            Assert.fail("The width of the question textbox is not  increased accordingly");
        }

        if(langaugeOption!=null){
            if(langaugeOption.equals("italian")){
                clickOnTheItalian();

            }
        }

        if(langaugeOption!=null){
            if(langaugeOption.equals("french")){
                clickOnTheFrench();

            }
        }
        if(langaugeOption!=null){
            if(langaugeOption.equals("spanish")){
                clickOnTheSpanish();
            }
        }

        manageContent.langaugePalette_cancelButton.click();
        Assert.assertEquals(new BooleanValue().presenceOfElement(25, By.id("language-palette-outer-wrapper")),false,"The Spanish Editor is not getting closed");

    }

    public   void varificationForGetDiffForAllQuestion() throws InterruptedException {
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(152));
        ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
        new Click().clickByid("questionOptions"); //click on question option
        new Click().clickByid("questionRevisions"); // click on revisions
        new Click().clickByid("cms-question-revision-new-version-link");//click on the create new Version
        manageContent.questionTextBox.click();
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", manageContent.langaugePalette);
        //manageContent.langaugePalette_spanish.click(); //click on the spanish language
        clickOnTheSpanish();
        for(WebElement element:manageContent.langaugePalette_allinput){
            element.click();
        }
        manageContent.langaugePalette_saveButton.click();
        new Click().clickByid("saveQuestionDetails1");//click on save button
        new Click().clickByid("questionOptions"); //click on question option
        new Click().clickByid("questionRevisions"); // click on revisions
        manageContent.questionRevision_chechkbok.get(0).click(); //select edit checkbox
        Thread.sleep(2000);
        manageContent.questionRevision_chechkbok.get(1).click();//select draft checkbox
        manageContent.getDiff_link.click(); //click on the getdiff link
        Thread.sleep(3000);
        String diffValue=manageContent.diffValue.getText().trim();
        Assert.assertEquals(questiontext+diffValue,questiontext+"áÁéÉíÍñÑóÓúÚüÜ¡¿","The  changes done from \"Language pallete\" characters is not seen over \"Get diff\" popup.");

    }

    public  void varificationForSearchAndBrowse(String QuestionType,int dataIndex) throws InterruptedException {

        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
        ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);
        new OpenSearchPage().openSearchPageFORLS();
        new OpenSearchPage().searchquestion(QuestionType+questiontext);
        Actions action2 = new Actions(driver);
        List<WebElement> we2 = manageContent.checkboxes;
        action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the firsr question
        Thread.sleep(2000);
        manageContent.quickPreview.get(manageContent.quickPreview.size()-1).click(); //click on the quickPreview link
        new UIElement().waitAndFindElement(manageContent.QuestionContents.get(0));
        String questionContent=manageContent.QuestionContents.get(0).getText().trim();

        if(selectLanguagePalette!=null){
            if(selectLanguagePalette.equals("italian")){
                System.out.println(QuestionType + questiontext + "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤");
                Assert.assertTrue(questionContent.contains(QuestionType + questiontext + "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤"), "data added over Question Editor is not displaying");

            }
        }

        if(selectLanguagePalette!=null){
            if(selectLanguagePalette.equals("french")){
                Assert.assertTrue(questionContent.contains(QuestionType + questiontext + "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"), "data added over Question Editor is not displaying");

            }
        }
        if(selectLanguagePalette!=null){
            if(selectLanguagePalette.equals("spanish")){
                Assert.assertTrue(questionContent.contains(QuestionType + questiontext + "áÁéÉíÍñÑóÓúÚüÜ¡¿"), "data added over Question Editor is not displaying");

            }
        }

        List<WebElement>edit =manageContent.edit_Link;
        Thread.sleep(2000);
        edit.get(edit.size()-1).click();//click on the edit Button
        new UIElement().waitAndFindElement(manageContent.questionSet_title);
        Assert.assertTrue(manageContent.questionSet_title.isDisplayed());
        Assert.assertTrue(manageContent.assessment_title.isDisplayed());
        String questionName=manageContent.questionTextBox.getText().trim();
        if(selectLanguagePalette!=null){
            if(selectLanguagePalette.equals("italian")){
                System.out.println(questionName);
                Assert.assertTrue(questionName.contains(QuestionType + questiontext + "ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤"), "data added over Question Editor is not displaying");


            }
        }

        if(selectLanguagePalette!=null){
            if(selectLanguagePalette.equals("french")){
                Assert.assertTrue(questionName.contains(QuestionType + questiontext + "ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"), "data added over Question Editor is not displaying");


            }
        }
        if(selectLanguagePalette!=null){
            if(selectLanguagePalette.equals("spanish")){
                Assert.assertTrue(questionName.contains(QuestionType + questiontext + "áÁéÉíÍñÑóÓúÚüÜ¡¿"), "data added over Question Editor is not displaying");


            }
        }
    }


    public  void varificationForBrowse(String QuestionType,int dataIndex) throws InterruptedException {

        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
        ManageContent manageContent = PageFactory.initElements(driver, ManageContent.class);

        new OpenSearchPage().openSearchPageFORLS();
        new OpenSearchPage().searchquestion(QuestionType+questiontext);
        Actions action2 = new Actions(driver);
        List<WebElement> we2 = manageContent.checkboxes;
        action2.moveToElement(we2.get(we2.size()-1)).build().perform();// Mouse Over on the firsr question
        Thread.sleep(2000);
        manageContent.quickPreview.get(manageContent.quickPreview.size()-1).click(); //click on the quickPreview link
        new UIElement().waitAndFindElement(manageContent.QuestionContents.get(0));
        String questionContent=manageContent.QuestionContents.get(0).getText().trim();

        if(selectLanguagePalette!=null){
            if(selectLanguagePalette.equals("italian")){
                Assert.assertTrue(questionContent.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

            }
        }

        if(selectLanguagePalette!=null){
            if(selectLanguagePalette.equals("french")){
                Assert.assertTrue(questionContent.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

            }
        }
        if(selectLanguagePalette!=null){
            if(selectLanguagePalette.equals("spanish")){
                Assert.assertTrue(questionContent.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿" + QuestionType + questiontext),"data added over Question Editor is not displaying");

            }
        }

        List<WebElement>edit =manageContent.edit_Link;
        Thread.sleep(2000);
        edit.get(edit.size()-1).click();//click on the edit Button
        new UIElement().waitAndFindElement(manageContent.questionSet_title);
        Assert.assertTrue(manageContent.questionSet_title.isDisplayed());
        Assert.assertTrue(manageContent.assessment_title.isDisplayed());
        String questionName=manageContent.questionTextBox.getText().trim();
        if(selectLanguagePalette!=null){
            if(selectLanguagePalette.equals("italian")){
                Assert.assertTrue(questionName.contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤" + QuestionType + questiontext),"data added over Question Editor is not displaying");

            }
        }

        if(selectLanguagePalette!=null){
            if(selectLanguagePalette.equals("french")){
                Assert.assertTrue(questionName.contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ" + QuestionType + questiontext),"data added over Question Editor is not displaying");

            }
        }
        if(selectLanguagePalette!=null){
            if(selectLanguagePalette.equals("spanish")){
                Assert.assertTrue(questionName.contains("áÁéÉíÍñÑóÓúÚüÜ¡¿" + QuestionType + questiontext),"data added over Question Editor is not displaying");

            }
        }
    }


    public    void CreateMultipartQuestion(int dataIndex)
    {
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        new TextSend().textsendbyid(questiontext, "question-mp-raw-content-0");
        new Click().clickByid("saveQuestionDetails1");//click on Save
        new Click().clickByclassname("cms-multipart-add-question-part");//click on Add new question part
        new Click().clickByid("qtn-type-true-false-img");//click on true false type question
        new TextSend().textsendbyid("1", "mpq-question-part-input-label");//enter label in true false MPQ
        new Click().clickByid("question-raw-content");//click on Question

    }
    public    void CreateTextEntryMultipartQuestion(int dataIndex)
    {
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        new TextSend().textsendbyid(questiontext, "question-mp-raw-content-0");
        new Click().clickByid("saveQuestionDetails1");//click on Save
        new Click().clickByclassname("cms-multipart-add-question-part");//click on Add new question part
        new Click().clickByid("qtn-text-entry-img");//click on true false type question
        new TextSend().textsendbyid("1", "mpq-question-part-input-label");//enter label in true false MPQ
        new Click().clickByid("question-raw-content");//click on Question

    }

    public    void CreateMultipleChoiceMultipartQuestion(int dataIndex)
    {
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        new TextSend().textsendbyid(questiontext, "question-mp-raw-content-0");
        new Click().clickByid("saveQuestionDetails1");//click on Save
        new Click().clickByclassname("cms-multipart-add-question-part");//click on Add new question part
        new Click().clickByid("qtn-multiple-choice-img");//click on multiple choice question
        new TextSend().textsendbyid("1", "mpq-question-part-input-label");//enter label in true false MPQ
        new Click().clickByid("question-mc-raw-content");//click on Question

    }
    public    void CreateMultipleSelectionMultipartQuestion(int dataIndex)
    {
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        new TextSend().textsendbyid(questiontext, "question-mp-raw-content-0");
        new Click().clickByid("saveQuestionDetails1");//click on Save
        new Click().clickByclassname("cms-multipart-add-question-part");//click on Add new question part
        new Click().clickByid("qtn-multiple-selection-img");//click on multiple choice question
        new TextSend().textsendbyid("1", "mpq-question-part-input-label");//enter label in true false MPQ
        new Click().clickByid("question-ms-raw-content");//click on Question

    }

    public    void CreateEssayMultipartQuestion(int dataIndex)
    {
        String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));
        new TextSend().textsendbyid(questiontext, "question-mp-raw-content-0");
        new Click().clickByid("saveQuestionDetails1");//click on Save
        new Click().clickByclassname("cms-multipart-add-question-part");//click on Add new question part
        new Click().clickByid("qtn-essay-type");//click on essay question
        new TextSend().textsendbyid("1", "mpq-question-part-input-label");//enter label in true false MPQ
        new Click().clickByid("question-raw-content");//click on Question

    }
    }


