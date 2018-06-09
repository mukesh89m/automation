package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT23.R237;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by priyanka on 10/7/2015.
 */
public class SpanishItalianFrenchPaletteForStudentForLs extends Driver{

        @Test(priority = 1,enabled = true)
        public void postInCourseStream()
        {
            try
            {
                int dataIndex = 920;
                String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));

                new LoginUsingLTI().ltiLogin("920"); //login as student
                new Navigator().NavigateTo("Course Stream");
                new Navigator().spanishItalianFrenchPaletteInStudentCS("920");
                // Cancel
                boolean cancelEditor = driver.findElement(By.className("cancel-language-dialog")).isDisplayed();
                if(cancelEditor == false)
                {
                    Assert.fail(" \"Cancel\" button is not present.");
                }
                //Save
                boolean saveEditor = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(saveEditor == false)
                {
                    Assert.fail(" \"Save\" button is not present.");
                }
                // Spanish Characters
                int spanishCharsList = driver.findElements(By.xpath(".//*[@class='language-palette-buttons language-icon-containers']/input")).size();
                if(spanishCharsList!=16)
                {
                    Assert.fail("All spanish characters are not present");
                }
                // Click on each spanish character
                List<WebElement> clickSpanishChars = driver.findElements(By.xpath(".//*[@class='language-palette-buttons language-icon-containers']/input"));
                for(int a = 0; a<spanishCharsList;a++)
                {
                    clickSpanishChars.get(a).click();
                }
                Thread.sleep(2000);
                driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                JavascriptExecutor jse=(JavascriptExecutor)driver;
                String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("inputStringValue::" + inputStringValue);
                if(inputStringValue.trim().equals(""))
                {
                    Assert.fail("The selected character are not dispalyed in the text box of the Palette .");
                }
                // Spanish characters at post text box
                driver.findElement(By.id("save-language-text")).click(); // Save
                int editorAfterSave = driver.findElements(By.id("sw-language-palette-wrapper")).size();
                if(editorAfterSave!=0)
                {
                    Assert.fail("Save is not closed palette popup is not closed");
                }
                Thread.sleep(2000);
                WebElement iframeMsg = driver.findElement(By.xpath(".//*[contains(@id, 'iframe-user-text-input-div')]"));
                driver.switchTo().frame(iframeMsg);
                WebElement body = driver.findElement(By.cssSelector("body"));
                System.out.println("body.getText::" + body.getText());
                if(!body.getText().equals(inputStringValue))
                {
                    Assert.fail("All the saved characters are not added to the POST text box.");
                }
                driver.switchTo().defaultContent();
                Thread.sleep(3000);
                // Open Spanish palette again
                new Click().clickByid("languages");
                if(selectLanguagePalette.equals("spanish"))
                {
                    new Click().clickByid("spanish");
                    boolean spanishEditorpopUp = driver.findElement(By.cssSelector("div[class='language-palette-title palette-header']")).isDisplayed();
                    if(spanishEditorpopUp==false)
                    {
                        Assert.fail("Language editor popup is not enabled.");
                    }

                    String spanishEditor = driver.findElement(By.cssSelector("div[class='language-palette-title palette-header']")).getText();
                    if(!spanishEditor.trim().equals("Spanish Editor"))
                    {
                        Assert.fail("Spanish editor popup is not displayed.");
                    }
                }
                // enter keyboard chars
                driver.findElement(By.id("languagePaletteBox")).click();;
                driver.findElement(By.id("languagePaletteBox")).sendKeys("abcde");
                String NormalinputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("NormalinputStringValue::" + NormalinputStringValue);
                if(!NormalinputStringValue.trim().equals("abcde"))
                {
                    Assert.fail("The characters entered normal are not displayed in the text box of the Palette .");
                }
                // Normal characters at post text box
                driver.findElement(By.id("save-language-text")).click(); // Save
                Thread.sleep(2000);
                iframeMsg = driver.findElement(By.xpath(".//*[contains(@id, 'iframe-user-text-input-div')]"));
                driver.switchTo().frame(iframeMsg);
                body = driver.findElement(By.cssSelector("body"));
                System.out.println("body.getText::"+body.getText());
                System.out.println("inputStringValue+NormalinputStringValue::" + inputStringValue + NormalinputStringValue);

                if(!body.getText().equals(inputStringValue+NormalinputStringValue))
                {
                    Assert.fail("All the saved characters are not added to the POST text box.");
                }
                driver.switchTo().defaultContent();
                driver.findElement(By.id("post-submit-cancel-button")).click();
                Thread.sleep(1000);
                // French Palette
                dataIndex = 934;
                selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
                new Navigator().spanishItalianFrenchPaletteInStudentCS("934");
                // Cancel
                cancelEditor = driver.findElement(By.className("cancel-language-dialog")).isDisplayed();
                if(cancelEditor == false)
                {
                    Assert.fail(" \"Cancel\" button is not present.");
                }
                //Save
                saveEditor = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(saveEditor == false)
                {
                    Assert.fail(" \"Save\" button is not present.");
                }
                // French Characters
                int frenchCharsList = driver.findElements(By.xpath(".//*[@class='language-palette-buttons language-icon-containers']/input")).size();
                if(frenchCharsList!=24)
                {
                    Assert.fail("All french characters are not present");
                }
                // Click on each French character
                List<WebElement> clickFrenchChars = driver.findElements(By.xpath(".//*[@class='language-palette-buttons language-icon-containers']/input"));
                for(int a = 0; a<frenchCharsList;a++)
                {
                    clickFrenchChars.get(a).click();
                }
                Thread.sleep(2000);
                driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("inputStringValue::" + inputStringValue);
                if(inputStringValue.trim().equals(""))
                {
                    Assert.fail("The selected character are not displayed in the text box of the French Palette .");
                }
                // French characters at post text box
                driver.findElement(By.id("save-language-text")).click(); // Save
                editorAfterSave = driver.findElements(By.id("sw-language-palette-wrapper")).size();
                if(editorAfterSave!=0)
                {
                    Assert.fail("Save is not closed palette popup is not closed");
                }
                Thread.sleep(2000);
                iframeMsg = driver.findElement(By.xpath(".//*[contains(@id, 'iframe-user-text-input-div')]"));
                driver.switchTo().frame(iframeMsg);
                body = driver.findElement(By.cssSelector("body"));
                System.out.println("body.getText::" + body.getText());
                if(!body.getText().equals(inputStringValue))
                {
                    Assert.fail("All the saved french characters are not added to the POST text box.");
                }
                driver.switchTo().defaultContent();
                Thread.sleep(3000);
                // Open French palette again
                new Click().clickByid("languages");
                if(selectLanguagePalette.equals("french"))
                {
                    Thread.sleep(2000);
                    new Click().clickByid("french");
                    boolean spanishEditorpopUp = driver.findElement(By.cssSelector("div[class='language-palette-title palette-header']")).isDisplayed();
                    if(spanishEditorpopUp==false)
                    {
                        Assert.fail("Language editor popup is not enabled.");
                    }

                    String spanishEditor = driver.findElement(By.cssSelector("div[class='language-palette-title palette-header']")).getText();
                    if(!spanishEditor.trim().equals("French Editor"))
                    {
                        Assert.fail("French editor popup is not displayed.");
                    }
                }
                // enter keyboard chars
                driver.findElement(By.id("languagePaletteBox")).click();;
                driver.findElement(By.id("languagePaletteBox")).sendKeys("abcde");
                NormalinputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("NormalinputStringValue::" + NormalinputStringValue);
                if(!NormalinputStringValue.trim().equals("abcde"))
                {
                    Assert.fail("The characters entered normal are not displayed in the text box of the Palette .");
                }
                // Normal characters at post text box
                driver.findElement(By.id("save-language-text")).click(); // Save
                Thread.sleep(2000);
                iframeMsg = driver.findElement(By.xpath(".//*[contains(@id, 'iframe-user-text-input-div')]"));
                driver.switchTo().frame(iframeMsg);
                body = driver.findElement(By.cssSelector("body"));
                System.out.println("body.getText::"+body.getText());
                System.out.println("inputStringValue+NormalinputStringValue::" + inputStringValue + NormalinputStringValue);

                if(!body.getText().equals(inputStringValue+NormalinputStringValue))
                {
                    Assert.fail("All the saved characters are not added to the POST text box.");
                }
                driver.switchTo().defaultContent();
                driver.findElement(By.id("post-submit-cancel-button")).click();
                Thread.sleep(1000);
                // Italian Palette
                dataIndex = 948;
                selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
                new Navigator().spanishItalianFrenchPaletteInStudentCS("948");
                // Cancel
                cancelEditor = driver.findElement(By.className("cancel-language-dialog")).isDisplayed();
                if(cancelEditor == false)
                {
                    Assert.fail(" \"Cancel\" button is not present.");
                }
                //Save
                saveEditor = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(saveEditor == false)
                {
                    Assert.fail(" \"Save\" button is not present.");
                }
                // Italian Characters
                int italianCharsList = driver.findElements(By.xpath(".//*[@class='language-palette-buttons language-icon-containers']/input")).size();
                if(italianCharsList!=22)
                {
                    Assert.fail("All Italian characters are not present");
                }
                // Click on each Italian character
                List<WebElement> clickItalianChars = driver.findElements(By.xpath(".//*[@class='language-palette-buttons language-icon-containers']/input"));
                for(int a = 0; a<italianCharsList;a++)
                {
                    clickItalianChars.get(a).click();
                }
                Thread.sleep(2000);
                driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("inputStringValue::" + inputStringValue);
                if(inputStringValue.trim().equals(""))
                {
                    Assert.fail("The selected character are not displayed in the text box of the Italian Palette .");
                }
                // Italian characters at post text box
                driver.findElement(By.id("save-language-text")).click(); // Save
                editorAfterSave = driver.findElements(By.id("sw-language-palette-wrapper")).size();
                if(editorAfterSave!=0)
                {
                    Assert.fail("Save is not closed palette popup is not closed");
                }
                Thread.sleep(2000);
                //driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                iframeMsg = driver.findElement(By.xpath(".//*[contains(@id, 'iframe-user-text-input-div')]"));
                driver.switchTo().frame(iframeMsg);
                body = driver.findElement(By.cssSelector("body"));
                System.out.println("body.getText::" + body.getText());
                if(!body.getText().equals(inputStringValue))
                {
                    Assert.fail("All the saved Italian characters are not added to the POST text box.");
                }
                driver.switchTo().defaultContent();
                Thread.sleep(3000);
                // Open Italian palette again
                new Click().clickByid("languages");
                if(selectLanguagePalette.equals("italian"))
                {
                    Thread.sleep(2000);
                    new Click().clickByid("italian");
                    boolean spanishEditorpopUp = driver.findElement(By.cssSelector("div[class='language-palette-title palette-header']")).isDisplayed();
                    if(spanishEditorpopUp==false)
                    {
                        Assert.fail("Language editor popup is not enabled.");
                    }

                    String spanishEditor = driver.findElement(By.cssSelector("div[class='language-palette-title palette-header']")).getText();
                    if(!spanishEditor.trim().equals("Italian Editor"))
                    {
                        Assert.fail("Italian editor popup is not displayed.");
                    }
                }
                // enter keyboard chars
                driver.findElement(By.id("languagePaletteBox")).click();;
                driver.findElement(By.id("languagePaletteBox")).sendKeys("abcde");
                NormalinputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("NormalinputStringValue::" + NormalinputStringValue);
                if(!NormalinputStringValue.trim().equals("abcde"))
                {
                    Assert.fail("The characters entered normal are not displayed in the text box of the Palette .");
                }
                // Normal characters at post text box
                driver.findElement(By.id("save-language-text")).click(); // Save
                Thread.sleep(2000);
                iframeMsg = driver.findElement(By.xpath(".//*[contains(@id, 'iframe-user-text-input-div')]"));
                driver.switchTo().frame(iframeMsg);
                body = driver.findElement(By.cssSelector("body"));
                System.out.println("body.getText::"+body.getText());
                System.out.println("inputStringValue+NormalinputStringValue::"+inputStringValue+NormalinputStringValue);

                if(!body.getText().equals(inputStringValue+NormalinputStringValue))
                {
                    Assert.fail("All the saved characters are not added to the POST text box.");
                }
                driver.switchTo().defaultContent();
                driver.findElement(By.id("post-submit-cancel-button")).click();
                Thread.sleep(1000);

            }
            catch (Exception e)
            {
                Assert.fail("Exception in postInCourseStream in class SpanishItalianFrenchPaletteForStudent", e);
            }
        }

        @Test(priority = 2,enabled = true)
        public void languagePalettePopUp()
        {
            try
            {
                new LoginUsingLTI().ltiLogin("963"); //login as student
                new Navigator().NavigateTo("Course Stream");
                // "Another text edit tool"
                new Navigator().spanishItalianFrenchPaletteInStudentCS("963");
                Thread.sleep(1000);
                // new Click().clickByid("forecolor"); // Click on Color
                driver.findElement(By.id("forecolor")).click();
                int frenchEditorpopUp = driver.findElements(By.cssSelector("div[class='language-palette-title palette-header']")).size();
                if(frenchEditorpopUp>0)
                {
                    Assert.fail("Language editor popup is still enabled.");
                }
                // "X" icon
                new Navigator().spanishItalianFrenchPaletteInStudentCS("963");
                new Click().clickByclassname("palette-close-icon");
                frenchEditorpopUp = driver.findElements(By.cssSelector("div[class='language-palette-title palette-header']")).size();
                if(frenchEditorpopUp>0)
                {
                    Assert.fail("Language editor popup is still enabled.");
                }
                //Entry in "course stream"
                new Navigator().spanishItalianFrenchPaletteInStudentCS("963");
                int frenchCharsList = driver.findElements(By.xpath(".//*[@class='language-palette-buttons language-icon-containers']/input")).size();
                List<WebElement> clickFrenchChars = driver.findElements(By.xpath(".//*[@class='language-palette-buttons language-icon-containers']/input"));
                for(int a = 0; a<frenchCharsList;a++)
                {
                    clickFrenchChars.get(a).click();
                }
                driver.findElement(By.id("save-language-text")).click(); // Save

                Thread.sleep(2000);
                WebElement iframeMsg = driver.findElement(By.xpath(".//*[contains(@id, 'iframe-user-text-input-div')]"));
                driver.switchTo().frame(iframeMsg);
                WebElement body = driver.findElement(By.cssSelector("body"));
                System.out.println("body.getText::" + body.getText());
                driver.switchTo().defaultContent();
                new Click().clickByid("post-submit-button");
                Thread.sleep(1000);
                List<WebElement> entryInCS = driver.findElements(By.xpath(".//*[@class='ls-media ls-stream-post ls-stream-post--question']//time"));
                String time = entryInCS.get(0).getText();
                System.out.println("time::" + time);
                if(!time.trim().equals("a second ago"))
                {
                    Assert.fail("Entry is not displyed in Course stream.");
                }
                List<WebElement> postTextinCS = driver.findElements(By.xpath(".//*[@class='ls-media ls-stream-post ls-stream-post--question']//span[@class='ls-link-span']"));
                String text = postTextinCS.get(0).getText();
                System.out.println("text::" + text);
                if(!text.equals("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"))
                {
                    Assert.fail("All the saved characters are not posted.");
                }
                //"Instructor" course stream
                new LoginUsingLTI().ltiLogin("969");
                new Navigator().NavigateTo("Course Stream");
                List<WebElement> postTextinCSIns = driver.findElements(By.xpath(".//*[@class='ls-media ls-stream-post ls-stream-post--question']//span[@class='ls-link-span']"));
                String textIns = postTextinCSIns.get(0).getText();
                System.out.println("textIns::" + textIns);
                if(!textIns.equals("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"))
                {
                    Assert.fail("Post entry is not displyed in Course stream.");
                }
            }
            catch (Exception e)
            {
                Assert.fail("Exception in languagePalettePopUp in class SpanishItalianFrenchPaletteForStudent", e);
            }
        }

        @Test(priority = 3,enabled = true)
        public void linkInCourseStream()
        {
            try
            {
                int dataIndex = 971;
                String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));

                new LoginUsingLTI().ltiLogin("971"); //login as student
                new Navigator().NavigateTo("Course Stream");
                new Navigator().spanishItalianFrenchPaletteInStudentCS("971");
                // Cancel
                boolean cancelEditor = driver.findElement(By.className("cancel-language-dialog")).isDisplayed();
                if(cancelEditor == false)
                {
                    Assert.fail(" \"Cancel\" button is not present.");
                }
                //Save
                boolean saveEditor = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(saveEditor == false)
                {
                    Assert.fail(" \"Save\" button is not present.");
                }
                // Spanish Characters
                int spanishCharsList = driver.findElements(By.xpath(".//*[@class='language-palette-buttons language-icon-containers']/input")).size();
                if(spanishCharsList!=16)
                {
                    Assert.fail("All spanish characters are not present");
                }
                // Click on each spanish character
                List<WebElement> clickSpanishChars = driver.findElements(By.xpath(".//*[@class='language-palette-buttons language-icon-containers']/input"));
                for(int a = 0; a<spanishCharsList;a++)
                {
                    clickSpanishChars.get(a).click();
                }
                Thread.sleep(2000);
                driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                JavascriptExecutor jse=(JavascriptExecutor)driver;
                String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("inputStringValue::" + inputStringValue);
                if(inputStringValue.trim().equals(""))
                {
                    Assert.fail("The selected character are not dispalyed in the text box of the Palette .");
                }
                // Spanish characters at post text box
                driver.findElement(By.id("save-language-text")).click(); // Save
                int editorAfterSave = driver.findElements(By.id("sw-language-palette-wrapper")).size();
                if(editorAfterSave!=0)
                {
                    Assert.fail("Save is not closed palette popup is not closed");
                }
                Thread.sleep(2000);
                WebElement iframeMsg = driver.findElement(By.xpath(".//*[contains(@id, 'iframe-user-link-input-div')]"));
                driver.switchTo().frame(iframeMsg);
                WebElement body = driver.findElement(By.cssSelector("body"));
                System.out.println("body.getText::" + body.getText());
                if(!body.getText().equals(inputStringValue))
                {
                    Assert.fail("All the saved characters are not added to the POST text box.");
                }
                driver.switchTo().defaultContent();
                Thread.sleep(3000);
                // Open Spanish palette again
                new Click().clickByid("languages");
                if(selectLanguagePalette.equals("spanish"))
                {
                    new Click().clickByid("spanish");
                    boolean spanishEditorpopUp = driver.findElement(By.cssSelector("div[class='language-palette-title palette-header']")).isDisplayed();
                    if(spanishEditorpopUp==false)
                    {
                        Assert.fail("Language editor popup is not enabled.");
                    }

                    String spanishEditor = driver.findElement(By.cssSelector("div[class='language-palette-title palette-header']")).getText();
                    if(!spanishEditor.trim().equals("Spanish Editor"))
                    {
                        Assert.fail("Spanish editor popup is not displayed.");
                    }
                }
                // enter keyboard chars
                driver.findElement(By.id("languagePaletteBox")).click();;
                driver.findElement(By.id("languagePaletteBox")).sendKeys("abcde");
                String NormalinputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("NormalinputStringValue::" + NormalinputStringValue);
                if(!NormalinputStringValue.trim().equals("abcde"))
                {
                    Assert.fail("The characters entered normal are not displayed in the text box of the Palette .");
                }
                // Normal characters at post text box
                driver.findElement(By.id("save-language-text")).click(); // Save
                Thread.sleep(2000);
                iframeMsg = driver.findElement(By.xpath(".//*[contains(@id, 'iframe-user-link-input-div')]"));
                driver.switchTo().frame(iframeMsg);
                body = driver.findElement(By.cssSelector("body"));
                System.out.println("body.getText::"+body.getText());
                System.out.println("inputStringValue+NormalinputStringValue::" + inputStringValue + NormalinputStringValue);

                if(!body.getText().equals(inputStringValue+NormalinputStringValue))
                {
                    Assert.fail("All the saved characters are not added to the POST text box.");
                }
                driver.switchTo().defaultContent();
                driver.findElement(By.id("post-submit-cancel-button")).click();
                Thread.sleep(1000);
                // French Palette
                dataIndex = 972;
                selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
                new Navigator().spanishItalianFrenchPaletteInStudentCS("972");
                // Cancel
                cancelEditor = driver.findElement(By.className("cancel-language-dialog")).isDisplayed();
                if(cancelEditor == false)
                {
                    Assert.fail(" \"Cancel\" button is not present.");
                }
                //Save
                saveEditor = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(saveEditor == false)
                {
                    Assert.fail(" \"Save\" button is not present.");
                }
                // French Characters
                int frenchCharsList = driver.findElements(By.xpath(".//*[@class='language-palette-buttons language-icon-containers']/input")).size();
                if(frenchCharsList!=24)
                {
                    Assert.fail("All french characters are not present");
                }
                // Click on each French character
                List<WebElement> clickFrenchChars = driver.findElements(By.xpath(".//*[@class='language-palette-buttons language-icon-containers']/input"));
                for(int a = 0; a<frenchCharsList;a++)
                {
                    clickFrenchChars.get(a).click();
                }
                Thread.sleep(2000);
                driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("inputStringValue::" + inputStringValue);
                if(inputStringValue.trim().equals(""))
                {
                    Assert.fail("The selected character are not displayed in the text box of the French Palette .");
                }
                // French characters at post text box
                driver.findElement(By.id("save-language-text")).click(); // Save
                editorAfterSave = driver.findElements(By.id("sw-language-palette-wrapper")).size();
                if(editorAfterSave!=0)
                {
                    Assert.fail("Save is not closed palette popup is not closed");
                }
                Thread.sleep(2000);
                iframeMsg = driver.findElement(By.xpath(".//*[contains(@id, 'iframe-user-link-input-div')]"));
                driver.switchTo().frame(iframeMsg);
                body = driver.findElement(By.cssSelector("body"));
                String org=body.getText().substring(0,24);
                System.out.println("body.getText::" + body.getText());
                if(!org.equals(inputStringValue))
                {
                    Assert.fail("All the saved french characters are not added to the POST text box.");
                }
                driver.switchTo().defaultContent();
                Thread.sleep(3000);
                // Open French palette again
                new Click().clickByid("languages");
                if(selectLanguagePalette.equals("french"))
                {
                    Thread.sleep(2000);
                    new Click().clickByid("french");
                    boolean spanishEditorpopUp = driver.findElement(By.cssSelector("div[class='language-palette-title palette-header']")).isDisplayed();
                    if(spanishEditorpopUp==false)
                    {
                        Assert.fail("Language editor popup is not enabled.");
                    }

                    String spanishEditor = driver.findElement(By.cssSelector("div[class='language-palette-title palette-header']")).getText();
                    if(!spanishEditor.trim().equals("French Editor"))
                    {
                        Assert.fail("French editor popup is not displayed.");
                    }
                }
                // enter keyboard chars
                driver.findElement(By.id("languagePaletteBox")).click();;
                driver.findElement(By.id("languagePaletteBox")).sendKeys("abcde");
                NormalinputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("NormalinputStringValue::" + NormalinputStringValue);
                if(!NormalinputStringValue.trim().equals("abcde"))
                {
                    Assert.fail("The characters entered normal are not displayed in the text box of the Palette .");
                }
                // Normal characters at post text box
                driver.findElement(By.id("save-language-text")).click(); // Save
                Thread.sleep(2000);
                iframeMsg = driver.findElement(By.xpath(".//*[contains(@id, 'iframe-user-link-input-div')]"));
                driver.switchTo().frame(iframeMsg);
                body = driver.findElement(By.cssSelector("body"));
                System.out.println("body.getText::"+body.getText());
                System.out.println("inputStringValue+NormalinputStringValue::" + inputStringValue + NormalinputStringValue);

                if(!body.getText().equals(inputStringValue+NormalinputStringValue))
                {
                    Assert.fail("All the saved characters are not added to the POST text box.");
                }
                driver.switchTo().defaultContent();
                driver.findElement(By.id("post-submit-cancel-button")).click();
                Thread.sleep(1000);
                // Italian Palette
                dataIndex = 973;
                selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
                new Navigator().spanishItalianFrenchPaletteInStudentCS("973");
                // Cancel
                cancelEditor = driver.findElement(By.className("cancel-language-dialog")).isDisplayed();
                if(cancelEditor == false)
                {
                    Assert.fail(" \"Cancel\" button is not present.");
                }
                //Save
                saveEditor = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(saveEditor == false)
                {
                    Assert.fail(" \"Save\" button is not present.");
                }
                // Italian Characters
                int italianCharsList = driver.findElements(By.xpath(".//*[@class='language-palette-buttons language-icon-containers']/input")).size();
                if(italianCharsList!=22)
                {
                    Assert.fail("All Italian characters are not present");
                }
                // Click on each Italian character
                List<WebElement> clickItalianChars = driver.findElements(By.xpath(".//*[@class='language-palette-buttons language-icon-containers']/input"));
                for(int a = 0; a<italianCharsList;a++)
                {
                    clickItalianChars.get(a).click();
                }
                Thread.sleep(2000);
                driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("inputStringValue::" + inputStringValue);
                if(inputStringValue.trim().equals(""))
                {
                    Assert.fail("The selected character are not displayed in the text box of the Italian Palette .");
                }
                // Italian characters at post text box
                driver.findElement(By.id("save-language-text")).click(); // Save
                editorAfterSave = driver.findElements(By.id("sw-language-palette-wrapper")).size();
                if(editorAfterSave!=0)
                {
                    Assert.fail("Save is not closed palette popup is not closed");
                }
                Thread.sleep(2000);
                iframeMsg = driver.findElement(By.xpath(".//*[contains(@id, 'iframe-user-link-input-div')]"));
                driver.switchTo().frame(iframeMsg);
                body = driver.findElement(By.xpath("/html/body"));
                System.out.println("body.getText::" + body.getText());
                if(!body.getText().equals(inputStringValue))
                {
                    Assert.fail("All the saved Italian characters are not added to the POST text box.");
                }
                driver.switchTo().defaultContent();
                Thread.sleep(3000);
                // Open Italian palette again
                new Click().clickByid("languages");
                if(selectLanguagePalette.equals("italian"))
                {
                    Thread.sleep(2000);
                    new Click().clickByid("italian");
                    boolean spanishEditorpopUp = driver.findElement(By.cssSelector("div[class='language-palette-title palette-header']")).isDisplayed();
                    if(spanishEditorpopUp==false)
                    {
                        Assert.fail("Language editor popup is not enabled.");
                    }

                    String spanishEditor = driver.findElement(By.cssSelector("div[class='language-palette-title palette-header']")).getText();
                    if(!spanishEditor.trim().equals("Italian Editor"))
                    {
                        Assert.fail("Italian editor popup is not displayed.");
                    }
                }
                // enter keyboard chars
                driver.findElement(By.id("languagePaletteBox")).click();;
                driver.findElement(By.id("languagePaletteBox")).sendKeys("abcde");
                NormalinputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("NormalinputStringValue::" + NormalinputStringValue);
                if(!NormalinputStringValue.trim().equals("abcde"))
                {
                    Assert.fail("The characters entered normal are not displayed in the text box of the Palette .");
                }
                // Normal characters at post text box
                driver.findElement(By.id("save-language-text")).click(); // Save
                Thread.sleep(2000);
                iframeMsg = driver.findElement(By.xpath(".//*[contains(@id, 'iframe-user-link-input-div')]"));
                driver.switchTo().frame(iframeMsg);
                body = driver.findElement(By.cssSelector("body"));
                System.out.println("body.getText::"+body.getText());
                System.out.println("inputStringValue+NormalinputStringValue::"+inputStringValue+NormalinputStringValue);

                if(!body.getText().equals(inputStringValue+NormalinputStringValue))
                {
                    Assert.fail("All the saved characters are not added to the POST text box.");
                }
                driver.switchTo().defaultContent();
                driver.findElement(By.id("post-submit-cancel-button")).click();
                Thread.sleep(1000);

            }
            catch (Exception e)
            {
                Assert.fail("Exception in linkInCourseStream in class SpanishItalianFrenchPaletteForStudent", e);
            }
        }

        @Test(priority = 4,enabled = true)
        public void linkLanguagePalettePopUp()
        {
            try
            {
                new LoginUsingLTI().ltiLogin("974"); //login as student
                new Navigator().NavigateTo("Course Stream");
                // "Another text edit tool"
                new Navigator().spanishItalianFrenchPaletteInStudentCS("974");
                Thread.sleep(1000);
                // new Click().clickByid("forecolor"); // Click on Color
                driver.findElement(By.xpath("(.//div[@id='forecolor'])[2]")).click();
                int frenchEditorpopUp = driver.findElements(By.cssSelector("div[class='language-palette-title palette-header']")).size();
                if(frenchEditorpopUp>0)
                {
                    Assert.fail("Language editor popup is still enabled.");
                }
                // "X" icon
                new Navigator().spanishItalianFrenchPaletteInStudentCS("974");
                new Click().clickByclassname("palette-close-icon");
                frenchEditorpopUp = driver.findElements(By.cssSelector("div[class='language-palette-title palette-header']")).size();
                if(frenchEditorpopUp>0)
                {
                    Assert.fail("Language editor popup is still enabled.");
                }
                //Entry in "course stream"
                new Navigator().spanishItalianFrenchPaletteInStudentCS("974");
                int frenchCharsList = driver.findElements(By.xpath(".//*[@class='language-palette-buttons language-icon-containers']/input")).size();
                List<WebElement> clickFrenchChars = driver.findElements(By.xpath(".//*[@class='language-palette-buttons language-icon-containers']/input"));
                for(int a = 0; a<frenchCharsList;a++)
                {
                    clickFrenchChars.get(a).click();
                }
                driver.findElement(By.id("save-language-text")).click(); // Save

                Thread.sleep(2000);
                WebElement iframeMsg = driver.findElement(By.xpath(".//*[contains(@id, 'iframe-user-link-input-div')]"));
                driver.switchTo().frame(iframeMsg);
                WebElement body = driver.findElement(By.cssSelector("body"));
                System.out.println("body.getText::" + body.getText());
                driver.switchTo().defaultContent();
                new Click().clickByid("post-submit-button");
                Thread.sleep(1000);
                List<WebElement> entryInCS = driver.findElements(By.xpath(".//*[@class='ls-media ls-stream-post ls-stream-post--question']//time"));
                String time = entryInCS.get(0).getText();
                System.out.println("time::" + time);
                if(!time.trim().equals("a second ago"))
                {
                    Assert.fail("Entry is not displyed in Course stream.");
                }
                List<WebElement> postTextinCS = driver.findElements(By.xpath(".//*[@class='ls-media ls-stream-post ls-stream-post--question']//span[@class='ls-link-span']"));
                String text = postTextinCS.get(0).getText();
                System.out.println("text::" + text);
                if(!text.equals("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"))
                {
                    Assert.fail("All the saved characters are not posted.");
                }
                //"Instructor" course stream
                new LoginUsingLTI().ltiLogin("975");
                new Navigator().NavigateTo("Course Stream");
                List<WebElement> postTextinCSIns = driver.findElements(By.xpath(".//*[@class='ls-media ls-stream-post ls-stream-post--question']//span[@class='ls-link-span']"));
                String textIns = postTextinCSIns.get(0).getText();
                System.out.println("textIns::" + textIns);
                if(!textIns.equals("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"))
                {
                    Assert.fail("Post entry is not displyed in Course stream.");
                }
            }
            catch (Exception e)
            {
                Assert.fail("Exception in linkLanguagePalettePopUp in class SpanishItalianFrenchPaletteForStudent", e);
            }
        }

        @Test(priority = 5,enabled = true)
        public void fileInCourseStream()
        {
            try
            {
                int dataIndex = 976;
                String selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));

                new LoginUsingLTI().ltiLogin("976"); //login as student
                new Navigator().NavigateTo("Course Stream");
                new Navigator().spanishItalianFrenchPaletteInStudentCS("976");
                // Cancel
                boolean cancelEditor = driver.findElement(By.className("cancel-language-dialog")).isDisplayed();
                if(cancelEditor == false)
                {
                    Assert.fail(" \"Cancel\" button is not present.");
                }
                //Save
                boolean saveEditor = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(saveEditor == false)
                {
                    Assert.fail(" \"Save\" button is not present.");
                }
                // Spanish Characters
                int spanishCharsList = driver.findElements(By.xpath(".//*[@class='language-palette-buttons language-icon-containers']/input")).size();
                if(spanishCharsList!=16)
                {
                    Assert.fail("All spanish characters are not present");
                }
                // Click on each spanish character
                List<WebElement> clickSpanishChars = driver.findElements(By.xpath(".//*[@class='language-palette-buttons language-icon-containers']/input"));
                for(int a = 0; a<spanishCharsList;a++)
                {
                    clickSpanishChars.get(a).click();
                }
                Thread.sleep(2000);
                driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                JavascriptExecutor jse=(JavascriptExecutor)driver;
                String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("inputStringValue::" + inputStringValue);
                if(inputStringValue.trim().equals(""))
                {
                    Assert.fail("The selected character are not dispalyed in the text box of the Palette .");
                }
                // Spanish characters at post text box
                driver.findElement(By.id("save-language-text")).click(); // Save
                int editorAfterSave = driver.findElements(By.id("sw-language-palette-wrapper")).size();
                if(editorAfterSave!=0)
                {
                    Assert.fail("Save is not closed palette popup is not closed");
                }
                Thread.sleep(2000);
                WebElement iframeMsg = driver.findElement(By.xpath(".//*[contains(@id, 'iframe-user-file-input-div')]"));
                driver.switchTo().frame(iframeMsg);
                WebElement body = driver.findElement(By.cssSelector("body"));
                System.out.println("body.getText::" + body.getText());
                if(!body.getText().equals(inputStringValue))
                {
                    Assert.fail("All the saved characters are not added to the POST text box.");
                }
                driver.switchTo().defaultContent();
                Thread.sleep(3000);
                // Open Spanish palette again
                new Click().clickByid("languages");
                if(selectLanguagePalette.equals("spanish"))
                {
                    new Click().clickByid("spanish");
                    boolean spanishEditorpopUp = driver.findElement(By.cssSelector("div[class='language-palette-title palette-header']")).isDisplayed();
                    if(spanishEditorpopUp==false)
                    {
                        Assert.fail("Language editor popup is not enabled.");
                    }

                    String spanishEditor = driver.findElement(By.cssSelector("div[class='language-palette-title palette-header']")).getText();
                    if(!spanishEditor.trim().equals("Spanish Editor"))
                    {
                        Assert.fail("Spanish editor popup is not displayed.");
                    }
                }
                // enter keyboard chars
                driver.findElement(By.id("languagePaletteBox")).click();;
                driver.findElement(By.id("languagePaletteBox")).sendKeys("abcde");
                String NormalinputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("NormalinputStringValue::" + NormalinputStringValue);
                if(!NormalinputStringValue.trim().equals("abcde"))
                {
                    Assert.fail("The characters entered normal are not displayed in the text box of the Palette .");
                }
                // Normal characters at post text box
                driver.findElement(By.id("save-language-text")).click(); // Save
                Thread.sleep(2000);
                iframeMsg = driver.findElement(By.xpath(".//*[contains(@id, 'iframe-user-file-input-div')]"));
                driver.switchTo().frame(iframeMsg);
                body = driver.findElement(By.cssSelector("body"));
                System.out.println("body.getText::"+body.getText());
                System.out.println("inputStringValue+NormalinputStringValue::"+inputStringValue+NormalinputStringValue);

                if(!body.getText().equals(inputStringValue+NormalinputStringValue))
                {
                    Assert.fail("All the saved characters are not added to the POST text box.");
                }
                driver.switchTo().defaultContent();
                driver.findElement(By.id("post-submit-cancel-button")).click();
                Thread.sleep(1000);
                // French Palette
                dataIndex = 977;
                selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
                new Navigator().spanishItalianFrenchPaletteInStudentCS("977");
                // Cancel
                cancelEditor = driver.findElement(By.className("cancel-language-dialog")).isDisplayed();
                if(cancelEditor == false)
                {
                    Assert.fail(" \"Cancel\" button is not present.");
                }
                //Save
                saveEditor = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(saveEditor == false)
                {
                    Assert.fail(" \"Save\" button is not present.");
                }
                // French Characters
                int frenchCharsList = driver.findElements(By.xpath(".//*[@class='language-palette-buttons language-icon-containers']/input")).size();
                if(frenchCharsList!=24)
                {
                    Assert.fail("All french characters are not present");
                }
                // Click on each French character
                List<WebElement> clickFrenchChars = driver.findElements(By.xpath(".//*[@class='language-palette-buttons language-icon-containers']/input"));
                for(int a = 0; a<frenchCharsList;a++)
                {
                    clickFrenchChars.get(a).click();
                }
                Thread.sleep(2000);
                driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("inputStringValue::" + inputStringValue);
                if(inputStringValue.trim().equals(""))
                {
                    Assert.fail("The selected character are not displayed in the text box of the French Palette .");
                }
                // French characters at post text box
                driver.findElement(By.id("save-language-text")).click(); // Save
                editorAfterSave = driver.findElements(By.id("sw-language-palette-wrapper")).size();
                if(editorAfterSave!=0)
                {
                    Assert.fail("Save is not closed palette popup is not closed");
                }
                Thread.sleep(2000);
                iframeMsg = driver.findElement(By.xpath(".//*[contains(@id, 'iframe-user-file-input-div')]"));
                driver.switchTo().frame(iframeMsg);
                body = driver.findElement(By.cssSelector("body"));
                System.out.println("body.getText::" + body.getText());
                if(!body.getText().equals(inputStringValue))
                {
                    Assert.fail("All the saved french characters are not added to the POST text box.");
                }
                driver.switchTo().defaultContent();
                Thread.sleep(3000);
                // Open French palette again
                new Click().clickByid("languages");
                if(selectLanguagePalette.equals("french"))
                {
                    Thread.sleep(2000);
                    new Click().clickByid("french");
                    boolean spanishEditorpopUp = driver.findElement(By.cssSelector("div[class='language-palette-title palette-header']")).isDisplayed();
                    if(spanishEditorpopUp==false)
                    {
                        Assert.fail("Language editor popup is not enabled.");
                    }

                    String spanishEditor = driver.findElement(By.cssSelector("div[class='language-palette-title palette-header']")).getText();
                    if(!spanishEditor.trim().equals("French Editor"))
                    {
                        Assert.fail("French editor popup is not displayed.");
                    }
                }
                // enter keyboard chars
                driver.findElement(By.id("languagePaletteBox")).click();;
                driver.findElement(By.id("languagePaletteBox")).sendKeys("abcde");
                NormalinputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("NormalinputStringValue::" + NormalinputStringValue);
                if(!NormalinputStringValue.trim().equals("abcde"))
                {
                    Assert.fail("The characters entered normal are not displayed in the text box of the Palette .");
                }
                // Normal characters at post text box
                driver.findElement(By.id("save-language-text")).click(); // Save
                Thread.sleep(2000);
                iframeMsg = driver.findElement(By.xpath(".//*[contains(@id, 'iframe-user-file-input-div')]"));
                driver.switchTo().frame(iframeMsg);
                body = driver.findElement(By.cssSelector("body"));
                System.out.println("body.getText::"+body.getText());
                System.out.println("inputStringValue+NormalinputStringValue::"+inputStringValue+NormalinputStringValue);

                if(!body.getText().equals(inputStringValue+NormalinputStringValue))
                {
                    Assert.fail("All the saved characters are not added to the POST text box.");
                }
                driver.switchTo().defaultContent();
                driver.findElement(By.id("post-submit-cancel-button")).click();
                Thread.sleep(1000);
                // Italian Palette
                dataIndex = 978;
                selectLanguagePalette = ReadTestData.readDataByTagName("", "selectLanguagePalette", Integer.toString(dataIndex));
                new Navigator().spanishItalianFrenchPaletteInStudentCS("978");
                // Cancel
                cancelEditor = driver.findElement(By.className("cancel-language-dialog")).isDisplayed();
                if(cancelEditor == false)
                {
                    Assert.fail(" \"Cancel\" button is not present.");
                }
                //Save
                saveEditor = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(saveEditor == false)
                {
                    Assert.fail(" \"Save\" button is not present.");
                }
                // Italian Characters
                int italianCharsList = driver.findElements(By.xpath(".//*[@class='language-palette-buttons language-icon-containers']/input")).size();
                if(italianCharsList!=22)
                {
                    Assert.fail("All Italian characters are not present");
                }
                // Click on each Italian character
                List<WebElement> clickItalianChars = driver.findElements(By.xpath(".//*[@class='language-palette-buttons language-icon-containers']/input"));
                for(int a = 0; a<italianCharsList;a++)
                {
                    clickItalianChars.get(a).click();
                }
                Thread.sleep(2000);
                driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("inputStringValue::" + inputStringValue);
                if(inputStringValue.trim().equals(""))
                {
                    Assert.fail("The selected character are not displayed in the text box of the Italian Palette .");
                }
                // Italian characters at post text box
                driver.findElement(By.id("save-language-text")).click(); // Save
                editorAfterSave = driver.findElements(By.id("sw-language-palette-wrapper")).size();
                if(editorAfterSave!=0)
                {
                    Assert.fail("Save is not closed palette popup is not closed");
                }
                Thread.sleep(2000);
                iframeMsg = driver.findElement(By.xpath(".//*[contains(@id, 'iframe-user-file-input-div')]"));
                driver.switchTo().frame(iframeMsg);
                body = driver.findElement(By.cssSelector("body"));
                System.out.println("body.getText::" + body.getText());
                if(!body.getText().equals(inputStringValue))
                {
                    Assert.fail("All the saved Italian characters are not added to the POST text box.");
                }
                driver.switchTo().defaultContent();
                Thread.sleep(3000);
                // Open Italian palette again
                new Click().clickByid("languages");
                if(selectLanguagePalette.equals("italian"))
                {
                    Thread.sleep(2000);
                    new Click().clickByid("italian");
                    boolean spanishEditorpopUp = driver.findElement(By.cssSelector("div[class='language-palette-title palette-header']")).isDisplayed();
                    if(spanishEditorpopUp==false)
                    {
                        Assert.fail("Language editor popup is not enabled.");
                    }

                    String spanishEditor = driver.findElement(By.cssSelector("div[class='language-palette-title palette-header']")).getText();
                    if(!spanishEditor.trim().equals("Italian Editor"))
                    {
                        Assert.fail("Italian editor popup is not displayed.");
                    }
                }
                // enter keyboard chars
                driver.findElement(By.id("languagePaletteBox")).click();;
                driver.findElement(By.id("languagePaletteBox")).sendKeys("abcde");
                NormalinputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("NormalinputStringValue::" + NormalinputStringValue);
                if(!NormalinputStringValue.trim().equals("abcde"))
                {
                    Assert.fail("The characters entered normal are not displayed in the text box of the Palette .");
                }
                // Normal characters at post text box
                driver.findElement(By.id("save-language-text")).click(); // Save
                Thread.sleep(2000);
                iframeMsg = driver.findElement(By.xpath(".//*[contains(@id, 'iframe-user-file-input-div')]"));
                driver.switchTo().frame(iframeMsg);
                body = driver.findElement(By.cssSelector("body"));
                System.out.println("body.getText::"+body.getText());
                System.out.println("inputStringValue+NormalinputStringValue::"+inputStringValue+NormalinputStringValue);

                if(!body.getText().equals(inputStringValue+NormalinputStringValue))
                {
                    Assert.fail("All the saved characters are not added to the POST text box.");
                }
                driver.switchTo().defaultContent();
                driver.findElement(By.id("post-submit-cancel-button")).click();
                Thread.sleep(1000);

            }
            catch (Exception e)
            {
                Assert.fail("Exception in fileInCourseStream in class SpanishItalianFrenchPaletteForStudent", e);
            }
        }

        @Test(priority = 6,enabled = true)
        public void fileLanguagePalettePopUp()
        {
            try
            {
                new LoginUsingLTI().ltiLogin("979"); //login as student
                new Navigator().NavigateTo("Course Stream");
                // "Another text edit tool"
                new Navigator().spanishItalianFrenchPaletteInStudentCS("979");
                Thread.sleep(1000);
                // new Click().clickByid("forecolor"); // Click on Color
                driver.findElement(By.xpath("(.//div[@id='forecolor'])[3]")).click();
                int frenchEditorpopUp = driver.findElements(By.cssSelector("div[class='language-palette-title palette-header']")).size();
                if(frenchEditorpopUp>0)
                {
                    Assert.fail("Language editor popup is still enabled.");
                }
                // "X" icon
                new Navigator().spanishItalianFrenchPaletteInStudentCS("979");
                new Click().clickByclassname("palette-close-icon");
                frenchEditorpopUp = driver.findElements(By.cssSelector("div[class='language-palette-title palette-header']")).size();
                if(frenchEditorpopUp>0)
                {
                    Assert.fail("Language editor popup is still enabled.");
                }
                //Entry in "course stream"
                new Navigator().spanishItalianFrenchPaletteInStudentCS("979");
                int frenchCharsList = driver.findElements(By.xpath(".//*[@class='language-palette-buttons language-icon-containers']/input")).size();
                List<WebElement> clickFrenchChars = driver.findElements(By.xpath(".//*[@class='language-palette-buttons language-icon-containers']/input"));
                for(int a = 0; a<frenchCharsList;a++)
                {
                    clickFrenchChars.get(a).click();
                }
                driver.findElement(By.id("save-language-text")).click(); // Save

                Thread.sleep(2000);
                WebElement iframeMsg = driver.findElement(By.xpath(".//*[contains(@id, 'iframe-user-file-input-div')]"));
                driver.switchTo().frame(iframeMsg);
                WebElement body = driver.findElement(By.cssSelector("body"));
                System.out.println("body.getText::" + body.getText());
                driver.switchTo().defaultContent();
                new Click().clickByid("post-submit-button");
                Thread.sleep(1000);
                List<WebElement> entryInCS = driver.findElements(By.xpath(".//*[@class='ls-media ls-stream-post ls-stream-post--question']//time"));
                String time = entryInCS.get(0).getText();
                System.out.println("time::" + time);
                if(!time.trim().equals("a second ago"))
                {
                    Assert.fail("Entry is not displyed in Course stream.");
                }
                List<WebElement> postTextinCS = driver.findElements(By.xpath(".//*[@class='ls-media ls-stream-post ls-stream-post--question']//span[@class='ls-link-span']"));
                String text = postTextinCS.get(0).getText();
                System.out.println("text::" + text);
                if(!text.equals("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"))
                {
                    Assert.fail("All the saved characters are not posted.");
                }
                //"Instructor" course stream
                new LoginUsingLTI().ltiLogin("980");
                new Navigator().NavigateTo("Course Stream");
                List<WebElement> postTextinCSIns = driver.findElements(By.xpath(".//*[@class='ls-media ls-stream-post ls-stream-post--question']//span[@class='ls-link-span']"));
                String textIns = postTextinCSIns.get(0).getText();
                System.out.println("textIns::" + textIns);
                if(!textIns.equals("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"))
                {
                    Assert.fail("Post entry is not displyed in Course stream.");
                }
            }
            catch (Exception e)
            {
                Assert.fail("Exception in fileLanguagePalettePopUp in class SpanishItalianFrenchPaletteForStudent", e);
            }
        }


    }


