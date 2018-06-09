package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT23.R237;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Assignment;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Discussion;
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
public class SpanishFrenchItalianPaletteAsStudentForLs extends Driver{

        @Test(priority = 1,enabled = true)
        public void spanishFrenchItalianPaletteInStaticAssessment()
        {
            try
            {
                int dataIndex = 981;
                String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
                System.out.println("assessmentname::" + assessmentname);
                new Assignment().create(981); // Create assessment
                // Ass Text entry questions
                new Assignment().addQuestions(981, "textentry", "");
                new Assignment().addQuestions(982, "textentry", "");
                new Assignment().addQuestions(983,"textentry","");
                new Assignment().addQuestions(984,"essay","");
                new LoginUsingLTI().ltiLogin("981"); // Login as student
                new Navigator().NavigateTo("eTextBook"); // Navigate to e_textbook
                // Click on assessment
                WebElement html = driver.findElement(By.tagName("html"));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

                List<WebElement> assessmentList = driver.findElements(By.cssSelector("a[data-type='static_assessment']"));
                for(int i=0;i<assessmentList.size();i++)
                {
                    String assessment = assessmentList.get(i).getAttribute("title");
                    System.out.println("assessmentList.get(i).getText()::"+assessmentList.get(i).getAttribute("title"));
                    Thread.sleep(1000);
                    if(assessment.trim().equals(assessmentname))
                    {
                        Thread.sleep(1000);
                        System.out.println("assessment::" + assessment);
                        Thread.sleep(1000);
                        assessmentList.get(i).click();
                        System.out.println("i::"+i);
                        break;
                    }
                }
                html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
                //Select 2nd questions
                driver.findElement(By.className("al-diag-chapter-details")).click();
                driver.findElement(By.cssSelector("tr[qindex='2']")).click();
                Thread.sleep(2000);
                boolean spanishPopUp = driver.findElement(By.cssSelector("span[class='spanish-popup spanish-popup-alignment']")).isDisplayed();
                if(spanishPopUp==false)
                {
                    Assert.fail("Text entry question with spanish option is not displayed.");
                }
                boolean paletteIcon = driver.findElement(By.xpath(".//div[@class='text-entry-input-wrapper visible_redactor_input_wrapper']//div[@class='show-spanish-bg spanish-bg-alignment']")).isDisplayed();
                if(paletteIcon==false)
                {
                    Assert.fail("In Question text box is not contain language palette icon.");
                }
                // Enter KeyBoardChars in textBox
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).click();
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).sendKeys("abcd");
                // Enter spanish chars
                driver.findElement(By.cssSelector("div[class='show-spanish-bg spanish-bg-alignment']")).click();
                // verify spanish palette popup
                boolean spanishpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(spanishpopUp==false)
                {
                    Assert.fail("Language palette popup is not displayed.");
                }
                // verify Spanish Editor popup
                String paletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!paletteHeader.trim().equals("Spanish Editor"))
                {
                    Assert.fail("The \"Spanish Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                boolean cancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(cancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Spanish Palette .");
                }
                // Palette Save
                boolean savePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(savePalette==false)
                {
                    Assert.fail("\"Save\" button is not present Spanish Palette .");
                }
                // Spanish Characters
                int spanishCharsList = driver.findElements(By.className("language-icon")).size();
                if(spanishCharsList!=16)
                {
                    Assert.fail("All spanish characters are not present");
                }
                // Click on each spanish character
                List<WebElement> clickSpanishChars = driver.findElements(By.className("language-icon"));
                for(int a = 0; a<clickSpanishChars.size();a++)
                {
                    clickSpanishChars.get(a).click();
                }
                Thread.sleep(2000);
                //Click on Save
                driver.findElement(By.id("save-language-text")).click();
                boolean paletteClosed = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(paletteClosed==true)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Submit question
                driver.findElement(By.className("ls-static-practice-test-submit-button")).click();
                boolean questionReviewPage = driver.findElement(By.className("yourresponse-correctanswer")).isDisplayed();
                if (questionReviewPage==false)
                {
                    Assert.fail("Question review page is not displayed.");
                }
                // Next Question
                driver.findElement(By.className("ls-static-practice-test-next-button")).click();
                // Question with French palette
                Thread.sleep(2000);
                boolean frenchPopUp = driver.findElement(By.cssSelector("span[class='spanish-popup spanish-popup-alignment']")).isDisplayed();
                if(frenchPopUp==false)
                {
                    Assert.fail("Text entry question with french option is not displayed.");
                }
                boolean frenchPaletteIcon = driver.findElement(By.xpath(".//div[@class='text-entry-input-wrapper visible_redactor_input_wrapper']//div[@class='show-spanish-bg spanish-bg-alignment']")).isDisplayed();
                if(frenchPaletteIcon==false)
                {
                    Assert.fail("In Question text box is not contain language french palette icon.");
                }
                // Enter KeyBoardChars in textBox
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).click();
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).sendKeys("fghij");
                // Enter french chars
                driver.findElement(By.cssSelector("div[class='show-spanish-bg spanish-bg-alignment']")).click();
                // verify french palette popup
                boolean frenchpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(frenchpopUp==false)
                {
                    Assert.fail("Language palette popup is not displayed.");
                }
                // verify french Editor popup
                String frenchPaletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!frenchPaletteHeader.trim().equals("French Editor"))
                {
                    Assert.fail("The \"French Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                boolean frenchCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(frenchCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Spanish Palette .");
                }
                // Palette Save
                boolean frenchSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(frenchSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present Spanish Palette .");
                }
                // french Characters
                int frenchCharsList = driver.findElements(By.className("language-icon")).size();
                if(frenchCharsList!=24)
                {
                    Assert.fail("All french characters are not present");
                }
                // Click on each french character
                List<WebElement> clickFrenchChars = driver.findElements(By.className("language-icon"));
                for(int i = 0; i<clickFrenchChars.size();i++)
                {
                    clickFrenchChars.get(i).click();
                }
                Thread.sleep(2000);
                //Click on Save
                driver.findElement(By.id("save-language-text")).click();
                paletteClosed = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(paletteClosed==true)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Submit question
                driver.findElement(By.className("ls-static-practice-test-submit-button")).click();
                boolean questionReview = driver.findElement(By.className("yourresponse-correctanswer")).isDisplayed();
                if (questionReview==false)
                {
                    Assert.fail("Question review page is not displayed.");
                }
                // Next Question
                driver.findElement(By.className("ls-static-practice-test-next-button")).click();
                // Question with Italian palette
                Thread.sleep(2000);
                boolean italianPopUp = driver.findElement(By.cssSelector("span[class='spanish-popup spanish-popup-alignment']")).isDisplayed();
                if(italianPopUp==false)
                {
                    Assert.fail("Text entry question with italian option is not displayed.");
                }
                boolean italianPaletteIcon = driver.findElement(By.xpath(".//div[@class='text-entry-input-wrapper visible_redactor_input_wrapper']//div[@class='show-spanish-bg spanish-bg-alignment']")).isDisplayed();
                if(italianPaletteIcon==false)
                {
                    Assert.fail("In Question text box is not contain language italian palette icon.");
                }
                // Enter KeyBoardChars in textBox
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).click();
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).sendKeys("klmn");
                // Enter italian chars
                driver.findElement(By.cssSelector("div[class='show-spanish-bg spanish-bg-alignment']")).click();
                // verify french palette popup
                boolean italianpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(italianpopUp==false)
                {
                    Assert.fail("Language palette popup is not displayed.");
                }
                // verify french Editor popup
                String italianPaletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!italianPaletteHeader.trim().equals("Italian Editor"))
                {
                    Assert.fail("The \"Italian Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                boolean italianCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(italianCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Italian Palette .");
                }
                // Palette Save
                boolean italianSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(italianSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present Italian Palette .");
                }
                // Italian Characters
                int italianCharsList = driver.findElements(By.className("language-icon")).size();
                if(italianCharsList!=22)
                {
                    Assert.fail("All italian characters are not present");
                }
                // Click on each french character
                List<WebElement> clickItalianChars = driver.findElements(By.className("language-icon"));
                for(int i = 0; i<clickItalianChars.size();i++)
                {
                    clickItalianChars.get(i).click();
                }
                Thread.sleep(2000);
                //Click on Save
                driver.findElement(By.id("save-language-text")).click();
                paletteClosed = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(paletteClosed==true)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Submit question
                driver.findElement(By.className("ls-static-practice-test-submit-button")).click();
                questionReviewPage = driver.findElement(By.className("yourresponse-correctanswer")).isDisplayed();
                if (questionReviewPage==false)
                {
                    Assert.fail("Question review page is not displayed.");
                }
                // Next Question
                driver.findElement(By.className("ls-static-practice-test-next-button")).click();

                // Essay question
                boolean essayQuestion = driver.findElement(By.className("essay-question-redactor-content-wrapper")).isDisplayed();
                if (essayQuestion==false)
                {
                    Assert.fail("Eassy type question is not displayed.");
                }
                boolean languagePalette = driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).isDisplayed();
                if (languagePalette==false)
                {
                    Assert.fail("The langauge palette icon is not in the top vertical tool options.");
                }
            }
            catch (Exception e)
            {
                Assert.fail("Exception in test case spanishFrenchItalianPaletteInStaticAssessment of class SpanishFrenchItalianPaletteAsStudent:", e);
            }
        }


        @Test(priority = 2,enabled = true)
        public void languagePaletteForEssayQuestion()
        {
            try
            {
                int dataIndex = 981;
                String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
                System.out.println("assessmentname::" + assessmentname);
                new LoginUsingLTI().ltiLogin("981"); // Login as student
                new Navigator().NavigateTo("eTextBook"); // Navigate to e_textbook
                // Click on assessment
                WebElement html = driver.findElement(By.tagName("html"));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

                List<WebElement> assessmentList = driver.findElements(By.cssSelector("a[data-type='static_assessment']"));
                for(int i=0;i<assessmentList.size();i++)
                {
                    String assessment = assessmentList.get(i).getAttribute("title");
                    System.out.println("assessmentList.get(i).getText()::"+assessmentList.get(i).getAttribute("title"));
                    Thread.sleep(1000);
                    if(assessment.trim().equals(assessmentname))
                    {
                        Thread.sleep(1000);
                        System.out.println("assessment::" + assessment);
                        Thread.sleep(1000);
                        assessmentList.get(i).click();
                        System.out.println("i::"+i);
                        break;
                    }
                }
                html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
                // Essay question
                boolean essayQuestion = driver.findElement(By.className("essay-question-redactor-content-wrapper")).isDisplayed();
                if (essayQuestion==false)
                {
                    Assert.fail("Eassy type question is not displayed.");
                }
                boolean languagePalette = driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).isDisplayed();
                if (languagePalette==false)
                {
                    Assert.fail("The langauge palette icon is not in the top vertical tool options.");
                }
                // Click on language palette
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                List<WebElement> listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                System.out.println("listOfLanguages.get(0).getText()::"+listOfLanguages.get(0).getText());
                System.out.println("listOfLanguages.get(0).getText()::"+listOfLanguages.get(1).getText());
                System.out.println("listOfLanguages.get(0).getText()::"+listOfLanguages.get(2).getText());

                if(!listOfLanguages.get(0).getText().trim().equals("Spanish") && !listOfLanguages.get(1).getText().trim().equals("Italian") && !listOfLanguages.get(2).getText().trim().equals("French") )
                {
                    Assert.fail("The language options are not displayed in same order as 1.Spanish 2.Italian 3.French.");
                }
                // Click on the Spanish Palette
                listOfLanguages.get(0).click();
                // verify spanish palette popup
                boolean spanishpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(spanishpopUp==false)
                {
                    Assert.fail("Language palette popup is not displayed.");
                }
                // verify Spanish Editor popup
                String paletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!paletteHeader.trim().equals("Spanish Editor"))
                {
                    Assert.fail("The \"Spanish Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                boolean spanishCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(spanishCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Italian Palette .");
                }
                // Palette Save
                boolean spanishSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(spanishSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present Italian Palette .");
                }
                // Spanish Characters
                int spanishCharsList = driver.findElements(By.className("language-icon")).size();
                if(spanishCharsList!=16)
                {
                    Assert.fail("All spanish characters are not present");
                }
                // Click on each spanish character
                List<WebElement> clickSpanishChars = driver.findElements(By.className("language-icon"));
                for(int a = 0; a<clickSpanishChars.size();a++)
                {
                    clickSpanishChars.get(a).click();
                }
                Thread.sleep(2000);
                driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                JavascriptExecutor jse=(JavascriptExecutor)driver;
                String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("inputStringValue::" + inputStringValue);
                if(!inputStringValue.trim().equals("áÁéÉíÍñÑóÓúÚüÜ¡¿"))
                {
                    Assert.fail("The selected character are not displayed in the text box of the Palette .");
                }
                driver.findElement(By.id("save-language-text")).click(); // Save

                String textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(!textInEssayBox.trim().equals("áÁéÉíÍñÑóÓúÚüÜ¡¿"))
                {
                    Assert.fail("All the character ate not saved in Essay text box.");
                }
                // Save spanish palette popup close
                int paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Click on Cancel
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(0).click();
                // enter some character
                driver.findElement(By.id("languagePaletteBox")).click();
                driver.findElement(By.id("languagePaletteBox")).sendKeys("abcd1233");

                driver.findElement(By.id("cancelLanguage")).click(); // click on cancel

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(textInEssayBox.trim().contains("abcd1233"))
                {
                    Assert.fail("On click of Cancel characters are present in essay text box.");
                }
                // cancel spanish palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                driver.findElement(By.id("html-editor-non-draggable")).click();
                driver.findElement(By.id("html-editor-non-draggable")).clear();
                // Italian Palette
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(1).click(); // click on italian
                // verify Italian palette popup
                boolean italianpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(italianpopUp==false)
                {
                    Assert.fail("Italian Language palette popup is not displayed.");
                }
                // verify Italian Editor popup
                paletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!paletteHeader.trim().equals("Italian Editor"))
                {
                    Assert.fail("The \"Italian Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                spanishCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(spanishCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Italian Palette .");
                }
                // Palette Save
                spanishSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(spanishSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present Italian Palette .");
                }
                // Italian Characters
                spanishCharsList = driver.findElements(By.className("language-icon")).size();
                if(spanishCharsList!=22)
                {
                    Assert.fail("All Italian characters are not present");
                }
                // Click on each Italian character
                clickSpanishChars = driver.findElements(By.className("language-icon"));
                for(int a = 0; a<clickSpanishChars.size();a++)
                {
                    clickSpanishChars.get(a).click();
                }
                Thread.sleep(2000);
                driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                jse=(JavascriptExecutor)driver;
                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("inputStringValue::" + inputStringValue);
                if(!inputStringValue.trim().equals("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤"))
                {
                    Assert.fail("The selected character are not displayed in the text box of the Italian Palette .");
                }
                driver.findElement(By.id("save-language-text")).click(); // Save

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(!textInEssayBox.trim().equals("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤"))
                {
                    Assert.fail("All the character are not saved in Essay text box.");
                }
                // Save Italian palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Click on Cancel
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(1).click();
                // enter some character
                driver.findElement(By.id("languagePaletteBox")).click();
                driver.findElement(By.id("languagePaletteBox")).sendKeys("abcd1233");

                driver.findElement(By.id("cancelLanguage")).click(); // click on cancel

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(textInEssayBox.trim().contains("abcd1233"))
                {
                    Assert.fail("On click of Cancel characters are present in essay text box.");
                }
                // cancel spanish palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                driver.findElement(By.id("html-editor-non-draggable")).click();
                driver.findElement(By.id("html-editor-non-draggable")).clear();

                // French Palette

                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(2).click(); // click on french
                // verify French palette popup
                boolean frenchpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(frenchpopUp==false)
                {
                    Assert.fail("French Language palette popup is not displayed.");
                }
                // verify French Editor popup
                paletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!paletteHeader.trim().equals("French Editor"))
                {
                    Assert.fail("The \"French Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                spanishCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(spanishCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present French Palette .");
                }
                // Palette Save
                spanishSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(spanishSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present French Palette .");
                }
                // French Characters
                spanishCharsList = driver.findElements(By.className("language-icon")).size();
                if(spanishCharsList!=24)
                {
                    Assert.fail("All French characters are not present");
                }
                // Click on each French character
                clickSpanishChars = driver.findElements(By.className("language-icon"));
                for(int a = 0; a<clickSpanishChars.size();a++)
                {
                    clickSpanishChars.get(a).click();
                }
                Thread.sleep(2000);
                driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                jse=(JavascriptExecutor)driver;
                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("inputStringValue::" + inputStringValue);
                if(!inputStringValue.trim().equals("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"))
                {
                    Assert.fail("The selected character are not displayed in the text box of the French Palette .");
                }
                driver.findElement(By.id("save-language-text")).click(); // Save

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(!textInEssayBox.trim().equals("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"))
                {
                    Assert.fail("All the character are not saved in Essay text box.");
                }
                // Save French palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Click on Cancel
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(2).click();
                // enter some character
                driver.findElement(By.id("languagePaletteBox")).click();
                driver.findElement(By.id("languagePaletteBox")).sendKeys("abcd1233");

                driver.findElement(By.id("cancelLanguage")).click(); // click on cancel

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(textInEssayBox.trim().contains("abcd1233"))
                {
                    Assert.fail("On click of Cancel characters are present in essay text box.");
                }
                // cancel spanish palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                driver.findElement(By.className("ls-static-practice-test-submit-button")).click(); // Submit Essay Qs
                boolean questionReviewPage = driver.findElement(By.className("report-view-user-response-title")).isDisplayed();
                if(questionReviewPage == false)
                {
                    Assert.fail("Question review page is not displayed.");
                }
                String studentResponse = driver.findElement(By.id("html-editor-non-draggable")).getText();
                System.out.println("studentResponse::"+studentResponse);
                if(!studentResponse.trim().equals("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"))
                {
                    Assert.fail("Text box is not contain same characters enter by student.");
                }
                Thread.sleep(1000);
                driver.findElement(By.className("ls-static-practice-test-next-button")).click();
            }
            catch (Exception e)
            {
                Assert.fail("Exception in test case languagePaletteForEssayQuestion of class SpanishFrenchItalianPaletteAsStudent:", e);
            }
        }

        @Test(priority = 3,enabled = true)
        public void performanceSummaryPage()
        {
            try
            {
                int dataIndex = 981;
                String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
                System.out.println("assessmentname::" + assessmentname);
                new LoginUsingLTI().ltiLogin("981"); // Login as student
                new Navigator().NavigateTo("eTextBook"); // Navigate to e_textbook
                // Click on assessment
                WebElement html = driver.findElement(By.tagName("html"));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

                List<WebElement> assessmentList = driver.findElements(By.cssSelector("a[data-type='static_assessment']"));
                for(int i=0;i<assessmentList.size();i++)
                {
                    String assessment = assessmentList.get(i).getAttribute("title");
                    System.out.println("assessmentList.get(i).getText()::"+assessmentList.get(i).getAttribute("title"));
                    Thread.sleep(1000);
                    if(assessment.trim().equals(assessmentname))
                    {
                        Thread.sleep(1000);
                        //System.out.println("assessment::" + assessment);
                        Thread.sleep(1000);
                        assessmentList.get(i).click();
                        System.out.println("i::"+i);
                        break;
                    }
                }
                html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
                boolean performanceChart = driver.findElement(By.id("performance-chart")).isDisplayed();
                if(performanceChart==false)
                {
                    Assert.fail("Performance report page is not displayed.");
                }
                Thread.sleep(2000);
                // Click on Question card
                List<WebElement> questionCards = driver.findElements(By.className("question-card-question-no"));
                System.out.println("questionCards.size()::"+questionCards.size());
                for(int i = 0; i < questionCards.size(); i++)
                {
                    new Navigator().NavigateTo("eTextBook"); // Navigate to e_textbook
                    // Click on assessment
                    html = driver.findElement(By.tagName("html"));
                    html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                    html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                    html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                    html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

                    assessmentList = driver.findElements(By.cssSelector("a[data-type='static_assessment']"));
                    for(int a=0;a<assessmentList.size();a++)
                    {
                        String assessment = assessmentList.get(a).getAttribute("title");
                        System.out.println("assessmentList.get(a).getText()::"+assessmentList.get(i).getAttribute("title"));
                        Thread.sleep(1000);
                        if(assessment.trim().equals(assessmentname))
                        {
                            Thread.sleep(1000);
                            // System.out.println("assessment::" + assessment);
                            Thread.sleep(1000);
                            assessmentList.get(a).click();
                            // System.out.println("a::"+a);
                            break;
                        }
                    }
                    html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
                    questionCards = driver.findElements(By.className("question-card-question-no"));
                    String questionNumber = questionCards.get(i).getText();
                    System.out.println("questionNumber::" + questionNumber);
                    questionCards.get(i).click();
                    //Post Discussion
                    new Discussion().addDiscussionInQuestionReviewPage();
                    new Navigator().NavigateTo("Course Stream");
                    // Click on Jump Out
                    List<WebElement> jumpOut = driver.findElements(By.cssSelector("span[class='ls-jumpout-icon-link ls-jumpout-icon']"));
                    jumpOut.get(0).click();
                    Thread.sleep(3000);
                    String questionLabel = driver.findElement(By.xpath(".//*[starts-with(@class,'question-display-label')]")).getText().replace(" ","");
                    System.out.println("questionLabel::"+questionLabel);
                    if(!questionLabel.trim().contains(questionNumber))
                    {
                        Assert.fail("Question review is not displayed.");
                    }
                }
            }
            catch (Exception e)
            {
                Assert.fail("Exception in test case performanceSummaryPage of class SpanishFrenchItalianPaletteAsStudent:", e);
            }
        }

        @Test(priority = 4,enabled = true)
        public void proficiencyReportPage()
        {
            try
            {
                new LoginUsingLTI().ltiLogin("981"); // Login as student
                new Navigator().NavigateTo("Proficiency Report"); // Navigate to Proficiency Report
                boolean reportTitle = driver.findElement(By.id("reportTitle")).isDisplayed();
                if(reportTitle==false)
                {
                    Assert.fail("Proficiency report page is not displayed.");
                }
                // Click on Question card
                List<WebElement> questionCards = driver.findElements(By.className("question-card-question-no"));
                System.out.println("questionCards.size()::"+questionCards.size());
                for(int i = 0; i < questionCards.size(); i++)
                {
                    new Navigator().NavigateTo("Proficiency Report"); // Navigate to Proficiency Report
                    questionCards = driver.findElements(By.className("question-card-question-no"));
                    String questionNumber = questionCards.get(i).getText();
                    System.out.println("questionNumber::" + questionNumber);
                    //if(questionNumber.trim().equals("Q'"+a+"'"))
                    //  {
                    questionCards.get(i).click();
                    //Post Discussion
                    new Discussion().addDiscussionInQuestionReviewPage();
                    new Navigator().NavigateTo("Course Stream");
                    List<WebElement> jumpOut = driver.findElements(By.cssSelector("span[class='ls-jumpout-icon-link ls-jumpout-icon']"));
                    jumpOut.get(0).click();
                    Thread.sleep(3000);
                    String questionLabel = driver.findElement(By.xpath(".//*[starts-with(@class,'question-display-label')]")).getText().replace(" ","");
                    System.out.println("questionLabel::"+questionLabel);
                    if(!questionLabel.trim().contains(questionNumber))
                    {
                        Assert.fail("Question review is not displayed.");
                    }
                    // }
                }
                // Click on Jump Out

            }
            catch (Exception e)
            {
                Assert.fail("Exception in test case proficiencyReportPage of class SpanishFrenchItalianPaletteAsStudent:", e);
            }
        }

        @Test(priority = 5,enabled = true)
        public void spanishFrenchItalianPaletteIntryItForStaticAssessment()
        {
            try
            {
                int dataIndex = 1043;
                String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

                new LoginUsingLTI().ltiLogin("1043");
                new Navigator().NavigateTo("eTextBook");
                // Click on assessment
                WebElement html = driver.findElement(By.tagName("html"));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

                List<WebElement> assessmentList = driver.findElements(By.xpath(".//*[@class='inner-assessment-block static_assessment']/a"));
                String parentHandle = driver.getWindowHandle();

                for(int i=0;i<assessmentList.size();i++)
                {
                    String assessment = assessmentList.get(i).getAttribute("title");
                    System.out.println("assessmentList.get(i).getText()::"+assessmentList.get(i).getAttribute("title"));
                    Thread.sleep(1000);
                    if(assessment.trim().equals(assessmentname))
                    {
                        int a = i + i;
                        Thread.sleep(1000);
                        List<WebElement> arrowList = driver.findElements(By.xpath(".//*[@class='inner-assessment-block static_assessment']/div[@class='ls-inner-arw']"));
                        arrowList.get(a).click();
                        new Click().clickbyxpath(".//*[@class='toc-actions-div']/div[@class='toc-try-it']");
                        System.out.println("i::" + i);
                        System.out.println("a::"+a);
                        break;
                    }
                }
                html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
                for (String winHandle : driver.getWindowHandles()) {
                    driver.switchTo().window(winHandle).manage().window().maximize(); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
                }
                //Select 2nd questions
                System.out.println("currentURL::"+driver.getCurrentUrl());
                driver.findElement(By.className("tryit-preview-dropdown-container")).click();
                List<WebElement> qsList = driver.findElements(By.className("tryit-dropdown-listItem"));
                for(int q = 0; q < qsList.size(); q++)
                {
                    String questionLabel = qsList.get(q).getText();
                    if(questionLabel.trim().equals("Q2"))
                    {
                        qsList.get(q).click();
                        break;
                    }
                }
                Thread.sleep(2000);
                boolean spanishPopUp = driver.findElement(By.cssSelector("span[class='spanish-popup spanish-popup-alignment']")).isDisplayed();
                if(spanishPopUp==false)
                {
                    Assert.fail("Text entry question with spanish option is not displayed.");
                }
                boolean paletteIcon = driver.findElement(By.xpath(".//div[@class='text-entry-input-wrapper visible_redactor_input_wrapper']//div[@class='show-spanish-bg spanish-bg-alignment']")).isDisplayed();
                if(paletteIcon==false)
                {
                    Assert.fail("In Question text box is not contain language palette icon.");
                }
                // Enter KeyBoardChars in textBox
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).click();
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).sendKeys("abcd");
                // Enter spanish chars
                driver.findElement(By.cssSelector("div[class='show-spanish-bg spanish-bg-alignment']")).click();
                // verify spanish palette popup
                boolean spanishpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(spanishpopUp==false)
                {
                    Assert.fail("Language palette popup is not displayed.");
                }
                // verify Spanish Editor popup
                String paletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!paletteHeader.trim().equals("Spanish Editor"))
                {
                    Assert.fail("The \"Spanish Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                boolean cancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(cancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Spanish Palette .");
                }
                // Palette Save
                boolean savePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(savePalette==false)
                {
                    Assert.fail("\"Save\" button is not present Spanish Palette .");
                }
                // Spanish Characters
                int spanishCharsList = driver.findElements(By.className("language-icon")).size();
                if(spanishCharsList!=16)
                {
                    Assert.fail("All spanish characters are not present");
                }
                // Click on each spanish character
                List<WebElement> clickSpanishChars = driver.findElements(By.className("language-icon"));
                for(int a = 0; a<clickSpanishChars.size();a++)
                {
                    clickSpanishChars.get(a).click();
                }
                Thread.sleep(2000);
                //Click on Save
                driver.findElement(By.id("save-language-text")).click();
                boolean paletteClosed = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(paletteClosed==true)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Submit question
                driver.findElement(By.id("question-try-it-next")).click();
            /*boolean questionReviewPage = driver.findElement(By.className("yourresponse-correctanswer")).isDisplayed();
            if (questionReviewPage==false)
            {
                Assert.fail("Question review page is not displayed.");
            }*/
                // Next Question
                // driver.findElement(By.className("ls-static-practice-test-next-button")).click();
                // Question with French palette
                Thread.sleep(2000);
                boolean frenchPopUp = driver.findElement(By.cssSelector("span[class='spanish-popup spanish-popup-alignment']")).isDisplayed();
                if(frenchPopUp==false)
                {
                    Assert.fail("Text entry question with french option is not displayed.");
                }
                boolean frenchPaletteIcon = driver.findElement(By.xpath(".//div[@class='text-entry-input-wrapper visible_redactor_input_wrapper']//div[@class='show-spanish-bg spanish-bg-alignment']")).isDisplayed();
                if(frenchPaletteIcon==false)
                {
                    Assert.fail("In Question text box is not contain language french palette icon.");
                }
                // Enter KeyBoardChars in textBox
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).click();
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).sendKeys("fghij");
                // Enter french chars
                driver.findElement(By.cssSelector("div[class='show-spanish-bg spanish-bg-alignment']")).click();
                // verify french palette popup
                boolean frenchpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(frenchpopUp==false)
                {
                    Assert.fail("Language palette popup is not displayed.");
                }
                // verify french Editor popup
                String frenchPaletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!frenchPaletteHeader.trim().equals("French Editor"))
                {
                    Assert.fail("The \"French Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                boolean frenchCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(frenchCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Spanish Palette .");
                }
                // Palette Save
                boolean frenchSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(frenchSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present Spanish Palette .");
                }
                // french Characters
                int frenchCharsList = driver.findElements(By.className("language-icon")).size();
                if(frenchCharsList!=24)
                {
                    Assert.fail("All french characters are not present");
                }
                // Click on each french character
                List<WebElement> clickFrenchChars = driver.findElements(By.className("language-icon"));
                for(int i = 0; i<clickFrenchChars.size();i++)
                {
                    clickFrenchChars.get(i).click();
                }
                Thread.sleep(2000);
                //Click on Save
                driver.findElement(By.id("save-language-text")).click();
                paletteClosed = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(paletteClosed==true)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Submit question
                driver.findElement(By.id("question-try-it-next")).click();
           /* boolean questionReview = driver.findElement(By.className("yourresponse-correctanswer")).isDisplayed();
            if (questionReview==false)
            {
                Assert.fail("Question review page is not displayed.");
            }*/
                // Next Question
                // driver.findElement(By.className("ls-static-practice-test-next-button")).click();
                // Question with Italian palette
                Thread.sleep(2000);
                boolean italianPopUp = driver.findElement(By.cssSelector("span[class='spanish-popup spanish-popup-alignment']")).isDisplayed();
                if(italianPopUp==false)
                {
                    Assert.fail("Text entry question with italian option is not displayed.");
                }
                boolean italianPaletteIcon = driver.findElement(By.xpath(".//div[@class='text-entry-input-wrapper visible_redactor_input_wrapper']//div[@class='show-spanish-bg spanish-bg-alignment']")).isDisplayed();
                if(italianPaletteIcon==false)
                {
                    Assert.fail("In Question text box is not contain language italian palette icon.");
                }
                // Enter KeyBoardChars in textBox
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).click();
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).sendKeys("klmn");
                // Enter italian chars
                driver.findElement(By.cssSelector("div[class='show-spanish-bg spanish-bg-alignment']")).click();
                // verify french palette popup
                boolean italianpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(italianpopUp==false)
                {
                    Assert.fail("Language palette popup is not displayed.");
                }
                // verify french Editor popup
                String italianPaletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!italianPaletteHeader.trim().equals("Italian Editor"))
                {
                    Assert.fail("The \"Italian Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                boolean italianCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(italianCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Italian Palette .");
                }
                // Palette Save
                boolean italianSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(italianSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present Italian Palette .");
                }
                // Italian Characters
                int italianCharsList = driver.findElements(By.className("language-icon")).size();
                if(italianCharsList!=22)
                {
                    Assert.fail("All italian characters are not present");
                }
                // Click on each french character
                List<WebElement> clickItalianChars = driver.findElements(By.className("language-icon"));
                for(int i = 0; i<clickItalianChars.size();i++)
                {
                    clickItalianChars.get(i).click();
                }
                Thread.sleep(2000);
                //Click on Save
                driver.findElement(By.id("save-language-text")).click();
                paletteClosed = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(paletteClosed==true)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Submit question
                driver.findElement(By.id("question-try-it-next")).click();
            /*questionReviewPage = driver.findElement(By.className("yourresponse-correctanswer")).isDisplayed();
            if (questionReviewPage==false)
            {
                Assert.fail("Question review page is not displayed.");
            }*/
                // Next Question
                // driver.findElement(By.className("ls-static-practice-test-next-button")).click();

                // Essay question
                boolean essayQuestion = driver.findElement(By.className("essay-question-redactor-content-wrapper")).isDisplayed();
                if (essayQuestion==false)
                {
                    Assert.fail("Eassy type question is not displayed.");
                }
                boolean languagePalette = driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).isDisplayed();
                if (languagePalette==false)
                {
                    Assert.fail("The langauge palette icon is not in the top vertical tool options.");
                }

                driver.close(); // close newly opened window when done with it
                driver.switchTo().window(parentHandle);

            }
            catch (Exception e)
            {
                Assert.fail("Exception in test case spanishFrenchItalianPaletteIntryItForStaticAssessment of class SpanishFrenchItalianPaletteAsStudent:", e);
            }
        }

        @Test(priority = 6,enabled = true)
        public void spanishFrenchItalianPaletteEssayQsInstructor()
        {
            try
            {
                int dataIndex = 1043;
                String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

                new LoginUsingLTI().ltiLogin("1043");
                new Navigator().NavigateTo("eTextBook");
                // Click on assessment
                WebElement html = driver.findElement(By.tagName("html"));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

                List<WebElement> assessmentList = driver.findElements(By.xpath(".//*[@class='inner-assessment-block static_assessment']/a"));
                String parentHandle = driver.getWindowHandle();

                for(int i=0;i<assessmentList.size();i++)
                {
                    String assessment = assessmentList.get(i).getAttribute("title");
                    System.out.println("assessmentList.get(i).getText()::"+assessmentList.get(i).getAttribute("title"));
                    Thread.sleep(1000);
                    if(assessment.trim().equals(assessmentname))
                    {
                        int a = i + i;
                        Thread.sleep(1000);
                        List<WebElement> arrowList = driver.findElements(By.xpath(".//*[@class='inner-assessment-block static_assessment']/div[@class='ls-inner-arw']"));
                        arrowList.get(a).click();
                        new Click().clickbyxpath(".//*[@class='toc-actions-div']/div[@class='toc-try-it']");
                        System.out.println("i::" + i);
                        System.out.println("a::"+a);
                        break;
                    }
                }
                html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
                for (String winHandle : driver.getWindowHandles()) {
                    driver.switchTo().window(winHandle).manage().window().maximize(); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
                }
                //Select essay questions
                System.out.println("currentURL::"+driver.getCurrentUrl());
                driver.findElement(By.className("tryit-preview-dropdown-container")).click();
                List<WebElement> qsList = driver.findElements(By.className("tryit-dropdown-listItem"));
                for(int q = 0; q < qsList.size(); q++)
                {
                    String questionLabel = qsList.get(q).getText();
                    if(questionLabel.trim().equals("Q5"))
                    {
                        qsList.get(q).click();
                        break;
                    }
                }
                Thread.sleep(2000);
                // Essay question
                boolean essayQuestion = driver.findElement(By.className("essay-question-redactor-content-wrapper")).isDisplayed();
                if (essayQuestion==false)
                {
                    Assert.fail("Eassy type question is not displayed.");
                }
                boolean languagePalette = driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).isDisplayed();
                if (languagePalette==false)
                {
                    Assert.fail("The langauge palette icon is not in the top vertical tool options.");
                }
                // Click on language palette
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                List<WebElement> listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                System.out.println("listOfLanguages.get(0).getText()::"+listOfLanguages.get(0).getText());
                System.out.println("listOfLanguages.get(0).getText()::"+listOfLanguages.get(1).getText());
                System.out.println("listOfLanguages.get(0).getText()::"+listOfLanguages.get(2).getText());

                if(!listOfLanguages.get(0).getText().trim().equals("Spanish") && !listOfLanguages.get(1).getText().trim().equals("Italian") && !listOfLanguages.get(2).getText().trim().equals("French") )
                {
                    Assert.fail("The language options are not displayed in same order as 1.Spanish 2.Italian 3.French.");
                }
                // Click on the Spanish Palette
                listOfLanguages.get(0).click();
                // verify spanish palette popup
                boolean spanishpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(spanishpopUp==false)
                {
                    Assert.fail("Language palette popup is not displayed.");
                }
                // verify Spanish Editor popup
                String paletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!paletteHeader.trim().equals("Spanish Editor"))
                {
                    Assert.fail("The \"Spanish Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                boolean spanishCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(spanishCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Italian Palette .");
                }
                // Palette Save
                boolean spanishSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(spanishSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present Italian Palette .");
                }
                // Spanish Characters
                int spanishCharsList = driver.findElements(By.className("language-icon")).size();
                if(spanishCharsList!=16)
                {
                    Assert.fail("All spanish characters are not present");
                }
                // Click on each spanish character
                List<WebElement> clickSpanishChars = driver.findElements(By.className("language-icon"));
                for(int a = 0; a<clickSpanishChars.size();a++)
                {
                    clickSpanishChars.get(a).click();
                }
                Thread.sleep(2000);
                driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                JavascriptExecutor jse=(JavascriptExecutor)driver;
                String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("inputStringValue::" + inputStringValue);
                if(!inputStringValue.trim().equals("áÁéÉíÍñÑóÓúÚüÜ¡¿"))
                {
                    Assert.fail("The selected character are not displayed in the text box of the Palette .");
                }
                driver.findElement(By.id("save-language-text")).click(); // Save

                String textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(!textInEssayBox.trim().equals("áÁéÉíÍñÑóÓúÚüÜ¡¿"))
                {
                    Assert.fail("All the character ate not saved in Essay text box.");
                }
                // Save spanish palette popup close
                int paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Click on Cancel
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(0).click();
                // enter some character
                driver.findElement(By.id("languagePaletteBox")).click();
                driver.findElement(By.id("languagePaletteBox")).sendKeys("abcd1233");

                driver.findElement(By.id("cancelLanguage")).click(); // click on cancel

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(textInEssayBox.trim().contains("abcd1233"))
                {
                    Assert.fail("On click of Cancel characters are present in essay text box.");
                }
                // cancel spanish palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                driver.findElement(By.id("html-editor-non-draggable")).click();
                driver.findElement(By.id("html-editor-non-draggable")).clear();
                // Italian Palette
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(1).click(); // click on italian
                // verify Italian palette popup
                boolean italianpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(italianpopUp==false)
                {
                    Assert.fail("Italian Language palette popup is not displayed.");
                }
                // verify Italian Editor popup
                paletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!paletteHeader.trim().equals("Italian Editor"))
                {
                    Assert.fail("The \"Italian Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                spanishCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(spanishCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Italian Palette .");
                }
                // Palette Save
                spanishSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(spanishSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present Italian Palette .");
                }
                // Italian Characters
                spanishCharsList = driver.findElements(By.className("language-icon")).size();
                if(spanishCharsList!=22)
                {
                    Assert.fail("All Italian characters are not present");
                }
                // Click on each Italian character
                clickSpanishChars = driver.findElements(By.className("language-icon"));
                for(int a = 0; a<clickSpanishChars.size();a++)
                {
                    clickSpanishChars.get(a).click();
                }
                Thread.sleep(2000);
                driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                jse=(JavascriptExecutor)driver;
                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("inputStringValue::" + inputStringValue);
                if(!inputStringValue.trim().equals("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤"))
                {
                    Assert.fail("The selected character are not displayed in the text box of the Italian Palette .");
                }
                driver.findElement(By.id("save-language-text")).click(); // Save

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(!textInEssayBox.trim().equals("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤"))
                {
                    Assert.fail("All the character are not saved in Essay text box.");
                }
                // Save Italian palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Click on Cancel
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(1).click();
                // enter some character
                driver.findElement(By.id("languagePaletteBox")).click();
                driver.findElement(By.id("languagePaletteBox")).sendKeys("abcd1233");

                driver.findElement(By.id("cancelLanguage")).click(); // click on cancel

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(textInEssayBox.trim().contains("abcd1233"))
                {
                    Assert.fail("On click of Cancel characters are present in essay text box.");
                }
                // cancel spanish palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                driver.findElement(By.id("html-editor-non-draggable")).click();
                driver.findElement(By.id("html-editor-non-draggable")).clear();

                // French Palette

                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(2).click(); // click on french
                // verify French palette popup
                boolean frenchpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(frenchpopUp==false)
                {
                    Assert.fail("French Language palette popup is not displayed.");
                }
                // verify French Editor popup
                paletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!paletteHeader.trim().equals("French Editor"))
                {
                    Assert.fail("The \"French Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                spanishCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(spanishCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present French Palette .");
                }
                // Palette Save
                spanishSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(spanishSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present French Palette .");
                }
                // French Characters
                spanishCharsList = driver.findElements(By.className("language-icon")).size();
                if(spanishCharsList!=24)
                {
                    Assert.fail("All French characters are not present");
                }
                // Click on each French character
                clickSpanishChars = driver.findElements(By.className("language-icon"));
                for(int a = 0; a<clickSpanishChars.size();a++)
                {
                    clickSpanishChars.get(a).click();
                }
                Thread.sleep(2000);
                driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                jse=(JavascriptExecutor)driver;
                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("inputStringValue::" + inputStringValue);
                if(!inputStringValue.trim().equals("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"))
                {
                    Assert.fail("The selected character are not displayed in the text box of the French Palette .");
                }
                driver.findElement(By.id("save-language-text")).click(); // Save

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(!textInEssayBox.trim().equals("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"))
                {
                    Assert.fail("All the character are not saved in Essay text box.");
                }
                // Save French palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Click on Cancel
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(2).click();
                // enter some character
                driver.findElement(By.id("languagePaletteBox")).click();
                driver.findElement(By.id("languagePaletteBox")).sendKeys("abcd1233");

                driver.findElement(By.id("cancelLanguage")).click(); // click on cancel

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(textInEssayBox.trim().contains("abcd1233"))
                {
                    Assert.fail("On click of Cancel characters are present in essay text box.");
                }
                // cancel spanish palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                driver.findElement(By.id("question-try-it-finish")).click(); // Submit Essay Qs
            /*boolean questionReviewPage = driver.findElement(By.className("report-view-user-response-title")).isDisplayed();
            if(questionReviewPage == false)
            {
                Assert.fail("Question review page is not displayed.");
            }
            String studentResponse = driver.findElement(By.id("html-editor-non-draggable")).getText();
            System.out.println("studentResponse::"+studentResponse);
            if(!studentResponse.trim().equals("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"))
            {
                Assert.fail("Text box is not contain same characters enter by student.");
            }
            Thread.sleep(1000);
            driver.findElement(By.className("ls-static-practice-test-next-button")).click();*/
                // driver.close(); // close newly opened window when done with it
                driver.switchTo().window(parentHandle);
            }
            catch (Exception e)
            {
                Assert.fail("Exception in test case spanishFrenchItalianPaletteEssayQsInstructor of class SpanishFrenchItalianPaletteAsStudent:", e);
            }
        }

        @Test(priority = 7,enabled = true)
        public void diagnosticAssessment()
        {
            try
            {
                int dataIndex = 1045;
                String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
                System.out.println("assessmentname::" + assessmentname);

                // Ass Text entry questions
                new Assignment().addQuestions(1045, "textentry", "");
                new Assignment().addQuestions(1046, "textentry", "");
                new Assignment().addQuestions(1047, "textentry", "");
                new Assignment().addQuestions(1048, "essay", "");
                new LoginUsingLTI().ltiLogin("1045"); // Login as student
                new Navigator().NavigateTo("eTextBook"); // Navigate to e_textbook
                // Click on Orion Practice tab
                driver.findElement(By.cssSelector("span[title='ORION Adaptive Practice']")).click();
                List<WebElement> confidenceLevel = driver.findElements(By.xpath(".//*[@id='ls-diag-self-rating-outer-wrapper']//a[@id='3']"));
                System.out.println("confidenceLevel::"+confidenceLevel.size());
                confidenceLevel.get(0).click();
                // Click Begin Diagnostic
                List<WebElement> beginDiagnostic = driver.findElements(By.cssSelector("input[class='practice-assigned ls-assessment-continue-btn orion-adaptive-practice-tab-btn']"));
                beginDiagnostic.get(0).click();
                int diagQuit = driver.findElements(By.className("al-quit-diag-test-icon")).size();
                while(diagQuit>0)
                {
                    String questionText = driver.findElement(By.id("question-content-label")).getText();
                    if(questionText.trim().contains("áÁéÉíÍñÑóÓúÚüÜ¡¿"))
                    {
                        break;
                    }
                    else
                    {
                        driver.findElement(By.id("ls-test-submit-next-button")).click();
                        Thread.sleep(1000);
                        diagQuit = driver.findElements(By.className("al-quit-diag-test-icon")).size();
                    }
                }
                //
                boolean spanishPopUp = driver.findElement(By.cssSelector("span[class='spanish-popup spanish-popup-alignment']")).isDisplayed();
                if(spanishPopUp==false)
                {
                    Assert.fail("Text entry question with spanish option is not displayed.");
                }
                boolean paletteIcon = driver.findElement(By.xpath(".//div[@class='text-entry-input-wrapper visible_redactor_input_wrapper']//div[@class='show-spanish-bg spanish-bg-alignment']")).isDisplayed();
                if(paletteIcon==false)
                {
                    Assert.fail("In Question text box is not contain language palette icon.");
                }
                // Enter KeyBoardChars in textBox
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).click();
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).sendKeys("abcd");
                // Enter spanish chars
                driver.findElement(By.cssSelector("div[class='show-spanish-bg spanish-bg-alignment']")).click();
                // verify spanish palette popup
                boolean spanishpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(spanishpopUp==false)
                {
                    Assert.fail("Language palette popup is not displayed.");
                }
                // verify Spanish Editor popup
                String paletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!paletteHeader.trim().equals("Spanish Editor"))
                {
                    Assert.fail("The \"Spanish Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                boolean cancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(cancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Spanish Palette .");
                }
                // Palette Save
                boolean savePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(savePalette==false)
                {
                    Assert.fail("\"Save\" button is not present Spanish Palette .");
                }
                // Spanish Characters
                int spanishCharsList = driver.findElements(By.className("language-icon")).size();
                if(spanishCharsList!=16)
                {
                    Assert.fail("All spanish characters are not present");
                }
                // Click on each spanish character
                List<WebElement> clickSpanishChars = driver.findElements(By.className("language-icon"));
                for(int a = 0; a<clickSpanishChars.size();a++)
                {
                    clickSpanishChars.get(a).click();
                }
                Thread.sleep(2000);
                //Click on Save
                driver.findElement(By.id("save-language-text")).click();
                boolean paletteClosed = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(paletteClosed==true)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Submit question
                driver.findElement(By.id("ls-test-submit-next-button")).click();
            /*boolean questionReviewPage = driver.findElement(By.className("yourresponse-correctanswer")).isDisplayed();
            if (questionReviewPage==false)
            {
                Assert.fail("Question review page is not displayed.");
            }
            // Next Question
            driver.findElement(By.className("ls-static-practice-test-next-button")).click();*/
                // Question with French palette
                Thread.sleep(2000);
                boolean frenchPopUp = driver.findElement(By.cssSelector("span[class='spanish-popup spanish-popup-alignment']")).isDisplayed();
                if(frenchPopUp==false)
                {
                    Assert.fail("Text entry question with french option is not displayed.");
                }
                boolean frenchPaletteIcon = driver.findElement(By.xpath(".//div[@class='text-entry-input-wrapper visible_redactor_input_wrapper']//div[@class='show-spanish-bg spanish-bg-alignment']")).isDisplayed();
                if(frenchPaletteIcon==false)
                {
                    Assert.fail("In Question text box is not contain language french palette icon.");
                }
                // Enter KeyBoardChars in textBox
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).click();
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).sendKeys("fghij");
                // Enter french chars
                driver.findElement(By.cssSelector("div[class='show-spanish-bg spanish-bg-alignment']")).click();
                // verify french palette popup
                boolean frenchpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(frenchpopUp==false)
                {
                    Assert.fail("Language palette popup is not displayed.");
                }
                // verify french Editor popup
                String frenchPaletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!frenchPaletteHeader.trim().equals("French Editor"))
                {
                    Assert.fail("The \"French Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                boolean frenchCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(frenchCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Spanish Palette .");
                }
                // Palette Save
                boolean frenchSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(frenchSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present Spanish Palette .");
                }
                // french Characters
                int frenchCharsList = driver.findElements(By.className("language-icon")).size();
                if(frenchCharsList!=24)
                {
                    Assert.fail("All french characters are not present");
                }
                // Click on each french character
                List<WebElement> clickFrenchChars = driver.findElements(By.className("language-icon"));
                for(int i = 0; i<clickFrenchChars.size();i++)
                {
                    clickFrenchChars.get(i).click();
                }
                Thread.sleep(2000);
                //Click on Save
                driver.findElement(By.id("save-language-text")).click();
                paletteClosed = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(paletteClosed==true)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Submit question
                driver.findElement(By.id("ls-test-submit-next-button")).click();
           /* boolean questionReview = driver.findElement(By.className("yourresponse-correctanswer")).isDisplayed();
            if (questionReview==false)
            {
                Assert.fail("Question review page is not displayed.");
            }
            // Next Question
            driver.findElement(By.className("ls-static-practice-test-next-button")).click();*/
                // Question with Italian palette
                Thread.sleep(2000);
                boolean italianPopUp = driver.findElement(By.cssSelector("span[class='spanish-popup spanish-popup-alignment']")).isDisplayed();
                if(italianPopUp==false)
                {
                    Assert.fail("Text entry question with italian option is not displayed.");
                }
                boolean italianPaletteIcon = driver.findElement(By.xpath(".//div[@class='text-entry-input-wrapper visible_redactor_input_wrapper']//div[@class='show-spanish-bg spanish-bg-alignment']")).isDisplayed();
                if(italianPaletteIcon==false)
                {
                    Assert.fail("In Question text box is not contain language italian palette icon.");
                }
                // Enter KeyBoardChars in textBox
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).click();
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).sendKeys("klmn");
                // Enter italian chars
                driver.findElement(By.cssSelector("div[class='show-spanish-bg spanish-bg-alignment']")).click();
                // verify french palette popup
                boolean italianpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(italianpopUp==false)
                {
                    Assert.fail("Language palette popup is not displayed.");
                }
                // verify french Editor popup
                String italianPaletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!italianPaletteHeader.trim().equals("Italian Editor"))
                {
                    Assert.fail("The \"Italian Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                boolean italianCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(italianCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Italian Palette .");
                }
                // Palette Save
                boolean italianSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(italianSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present Italian Palette .");
                }
                // Italian Characters
                int italianCharsList = driver.findElements(By.className("language-icon")).size();
                if(italianCharsList!=22)
                {
                    Assert.fail("All italian characters are not present");
                }
                // Click on each french character
                List<WebElement> clickItalianChars = driver.findElements(By.className("language-icon"));
                for(int i = 0; i<clickItalianChars.size();i++)
                {
                    clickItalianChars.get(i).click();
                }
                Thread.sleep(2000);
                //Click on Save
                driver.findElement(By.id("save-language-text")).click();
                paletteClosed = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(paletteClosed==true)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Submit question
                driver.findElement(By.id("ls-test-submit-next-button")).click();
            /*questionReviewPage = driver.findElement(By.className("yourresponse-correctanswer")).isDisplayed();
            if (questionReviewPage==false)
            {
                Assert.fail("Question review page is not displayed.");
            }
            // Next Question
            driver.findElement(By.className("ls-static-practice-test-next-button")).click();
*/
                // Essay question
                boolean essayQuestion = driver.findElement(By.className("essay-question-redactor-content-wrapper")).isDisplayed();
                if (essayQuestion==false)
                {
                    Assert.fail("Eassy type question is not displayed.");
                }
                boolean languagePalette = driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).isDisplayed();
                if (languagePalette==false)
                {
                    Assert.fail("The langauge palette icon is not in the top vertical tool options.");
                }
            }
            catch (Exception e)
            {
                Assert.fail("Exception in test case spanishFrenchItalianPaletteEssayQsInstructor of class SpanishFrenchItalianPaletteAsStudent:", e);
            }
        }

        @Test(priority = 8,enabled = true)
        public void diagnosticAssessmentEssayQuestion()
        {
            try
            {
                int dataIndex = 1045;
                String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
                System.out.println("assessmentname::" + assessmentname);
                new LoginUsingLTI().ltiLogin("1045"); // Login as student
                new Navigator().NavigateTo("eTextBook"); // Navigate to e_textbook
                // Click on Orion Practice tab
                driver.findElement(By.cssSelector("span[title='ORION Adaptive Practice']")).click();
                Thread.sleep(1000);
                driver.findElement(By.id("ls-diag-assessment-continue-btn")).click();
                // Essay question
                boolean essayQuestion = driver.findElement(By.className("essay-question-redactor-content-wrapper")).isDisplayed();
                if (essayQuestion==false)
                {
                    Assert.fail("Eassy type question is not displayed.");
                }
                boolean languagePalette = driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).isDisplayed();
                if (languagePalette==false)
                {
                    Assert.fail("The langauge palette icon is not in the top vertical tool options.");
                }
                // Click on language palette
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                List<WebElement> listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                System.out.println("listOfLanguages.get(0).getText()::"+listOfLanguages.get(0).getText());
                System.out.println("listOfLanguages.get(0).getText()::"+listOfLanguages.get(1).getText());
                System.out.println("listOfLanguages.get(0).getText()::" + listOfLanguages.get(2).getText());

                if(!listOfLanguages.get(0).getText().trim().equals("Spanish") && !listOfLanguages.get(1).getText().trim().equals("Italian") && !listOfLanguages.get(2).getText().trim().equals("French") )
                {
                    Assert.fail("The language options are not displayed in same order as 1.Spanish 2.Italian 3.French.");
                }
                // Click on the Spanish Palette
                listOfLanguages.get(0).click();
                // verify spanish palette popup
                boolean spanishpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(spanishpopUp==false)
                {
                    Assert.fail("Language palette popup is not displayed.");
                }
                // verify Spanish Editor popup
                String paletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!paletteHeader.trim().equals("Spanish Editor"))
                {
                    Assert.fail("The \"Spanish Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                boolean spanishCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(spanishCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Italian Palette .");
                }
                // Palette Save
                boolean spanishSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(spanishSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present Italian Palette .");
                }
                // Spanish Characters
                int spanishCharsList = driver.findElements(By.className("language-icon")).size();
                if(spanishCharsList!=16)
                {
                    Assert.fail("All spanish characters are not present");
                }
                // Click on each spanish character
                List<WebElement> clickSpanishChars = driver.findElements(By.className("language-icon"));
                for(int a = 0; a<clickSpanishChars.size();a++)
                {
                    clickSpanishChars.get(a).click();
                }
                Thread.sleep(2000);
                driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                JavascriptExecutor jse=(JavascriptExecutor)driver;
                String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("inputStringValue::" + inputStringValue);
                if(!inputStringValue.trim().equals("áÁéÉíÍñÑóÓúÚüÜ¡¿"))
                {
                    Assert.fail("The selected character are not displayed in the text box of the Palette .");
                }
                driver.findElement(By.id("save-language-text")).click(); // Save

                String textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(!textInEssayBox.trim().equals("áÁéÉíÍñÑóÓúÚüÜ¡¿"))
                {
                    Assert.fail("All the character ate not saved in Essay text box.");
                }
                // Save spanish palette popup close
                int paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Click on Cancel
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(0).click();
                // enter some character
                driver.findElement(By.id("languagePaletteBox")).click();
                driver.findElement(By.id("languagePaletteBox")).sendKeys("abcd1233");

                driver.findElement(By.id("cancelLanguage")).click(); // click on cancel

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(textInEssayBox.trim().contains("abcd1233"))
                {
                    Assert.fail("On click of Cancel characters are present in essay text box.");
                }
                // cancel spanish palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                driver.findElement(By.id("html-editor-non-draggable")).click();
                driver.findElement(By.id("html-editor-non-draggable")).clear();
                // Italian Palette
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(1).click(); // click on italian
                // verify Italian palette popup
                boolean italianpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(italianpopUp==false)
                {
                    Assert.fail("Italian Language palette popup is not displayed.");
                }
                // verify Italian Editor popup
                paletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!paletteHeader.trim().equals("Italian Editor"))
                {
                    Assert.fail("The \"Italian Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                spanishCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(spanishCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Italian Palette .");
                }
                // Palette Save
                spanishSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(spanishSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present Italian Palette .");
                }
                // Italian Characters
                spanishCharsList = driver.findElements(By.className("language-icon")).size();
                if(spanishCharsList!=22)
                {
                    Assert.fail("All Italian characters are not present");
                }
                // Click on each Italian character
                clickSpanishChars = driver.findElements(By.className("language-icon"));
                for(int a = 0; a<clickSpanishChars.size();a++)
                {
                    clickSpanishChars.get(a).click();
                }
                Thread.sleep(2000);
                driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                jse=(JavascriptExecutor)driver;
                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("inputStringValue::" + inputStringValue);
                if(!inputStringValue.trim().equals("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤"))
                {
                    Assert.fail("The selected character are not displayed in the text box of the Italian Palette .");
                }
                driver.findElement(By.id("save-language-text")).click(); // Save

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(!textInEssayBox.trim().equals("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤"))
                {
                    Assert.fail("All the character are not saved in Essay text box.");
                }
                // Save Italian palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Click on Cancel
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(1).click();
                // enter some character
                driver.findElement(By.id("languagePaletteBox")).click();
                driver.findElement(By.id("languagePaletteBox")).sendKeys("abcd1233");

                driver.findElement(By.id("cancelLanguage")).click(); // click on cancel

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(textInEssayBox.trim().contains("abcd1233"))
                {
                    Assert.fail("On click of Cancel characters are present in essay text box.");
                }
                // cancel spanish palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                driver.findElement(By.id("html-editor-non-draggable")).click();
                driver.findElement(By.id("html-editor-non-draggable")).clear();

                // French Palette

                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(2).click(); // click on french
                // verify French palette popup
                boolean frenchpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(frenchpopUp==false)
                {
                    Assert.fail("French Language palette popup is not displayed.");
                }
                // verify French Editor popup
                paletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!paletteHeader.trim().equals("French Editor"))
                {
                    Assert.fail("The \"French Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                spanishCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(spanishCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present French Palette .");
                }
                // Palette Save
                spanishSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(spanishSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present French Palette .");
                }
                // French Characters
                spanishCharsList = driver.findElements(By.className("language-icon")).size();
                if(spanishCharsList!=24)
                {
                    Assert.fail("All French characters are not present");
                }
                // Click on each French character
                clickSpanishChars = driver.findElements(By.className("language-icon"));
                for(int a = 0; a<clickSpanishChars.size();a++)
                {
                    clickSpanishChars.get(a).click();
                }
                Thread.sleep(2000);
                driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                jse=(JavascriptExecutor)driver;
                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("inputStringValue::" + inputStringValue);
                if(!inputStringValue.trim().equals("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"))
                {
                    Assert.fail("The selected character are not displayed in the text box of the French Palette .");
                }
                driver.findElement(By.id("save-language-text")).click(); // Save

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(!textInEssayBox.trim().equals("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"))
                {
                    Assert.fail("All the character are not saved in Essay text box.");
                }
                // Save French palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Click on Cancel
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(2).click();
                // enter some character
                driver.findElement(By.id("languagePaletteBox")).click();
                driver.findElement(By.id("languagePaletteBox")).sendKeys("abcd1233");

                driver.findElement(By.id("cancelLanguage")).click(); // click on cancel

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(textInEssayBox.trim().contains("abcd1233"))
                {
                    Assert.fail("On click of Cancel characters are present in essay text box.");
                }
                // cancel spanish palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                int submitTest = driver.findElements(By.id("ls-test-submit-next-button")).size();
                while (submitTest > 0)
                {
                    driver.findElement(By.id("ls-test-submit-next-button")).click();
                    submitTest = driver.findElements(By.id("ls-test-submit-next-button")).size();
                }
                driver.findElement(By.className("al-diag-test-submit-button")).click(); // Submit Essay Qs
                boolean performancePage = driver.findElement(By.className("al-preformance-chart-content")).isDisplayed();
                if(performancePage==false)
                {
                    Assert.fail("Diag Test Not Completed");
                }

                performancePage = driver.findElement(By.className("al-preformance-chart-content")).isDisplayed();
                if(performancePage==false)
                {
                    Assert.fail("Not navigated to Performance Page");
                }

            }
            catch (Exception e)
            {
                Assert.fail("Exception in test case spanishFrenchItalianPaletteEssayQsInstructor of class SpanishFrenchItalianPaletteAsStudent:", e);
            }
        }

        @Test(priority = 9,enabled = true)
        public void diagnosticPerformanceSummaryPage()
        {
            try
            {
                new LoginUsingLTI().ltiLogin("1045"); // Login as student
                new Navigator().NavigateTo("Dashboard"); // Navigate to DashBoard
                // Click on assessment
                driver.findElement(By.cssSelector("a[class='activity-row activity-practice']")).click();
                Thread.sleep(3000);
                boolean performancePage = driver.findElement(By.className("al-preformance-chart-content")).isDisplayed();
                if(performancePage==false)
                {
                    Assert.fail("Not navigated to Performance Page");
                }

                Thread.sleep(2000);
                // Click on Question card
                List<WebElement> questionCards = driver.findElements(By.className("question-card-question-no"));
                System.out.println("questionCards.size()::"+questionCards.size());
                for(int i = 0; i < 5; i++)
                {
                    new Navigator().NavigateTo("Dashboard"); // Navigate to DashBoard
                    // Click on assessment
                    driver.findElement(By.cssSelector("a[class='activity-row activity-practice']")).click();

                    questionCards = driver.findElements(By.className("question-card-question-no"));
                    String questionNumber = questionCards.get(i).getText();
                    System.out.println("questionNumber::" + questionNumber);
                    questionCards.get(i).click();
                    //Post Discussion
                    new Discussion().addDiscussionInQuestionReviewPage();
                    new Navigator().NavigateTo("Course Stream");
                    // Click on Jump Out
                    List<WebElement> jumpOut = driver.findElements(By.cssSelector("span[class='ls-jumpout-icon-link ls-jumpout-icon']"));
                    jumpOut.get(0).click();
                    Thread.sleep(3000);
                    String questionLabel = driver.findElement(By.xpath(".//*[starts-with(@class,'question-display-label')]")).getText().replace(" ","");
                    System.out.println("questionLabel::"+questionLabel);
                    if(!questionLabel.trim().contains(questionNumber))
                    {
                        Assert.fail("Question review is not displayed.");
                    }
                }
            }
            catch (Exception e)
            {
                Assert.fail("Exception in test case diagnosticPerformanceSummaryPage of class SpanishFrenchItalianPaletteAsStudent:", e);
            }
        }

        @Test(priority = 10,enabled = true)
        public void diagnosticTestProficiencyReportPage()
        {
            try
            {
                new LoginUsingLTI().ltiLogin("1045"); // Login as student
                new Navigator().NavigateTo("Proficiency Report"); // Navigate to Proficiency Report
                boolean reportTitle = driver.findElement(By.id("reportTitle")).isDisplayed();
                if(reportTitle==false)
                {
                    Assert.fail("Proficiency report page is not displayed.");
                }
                // Click on Question card
                List<WebElement> questionCards = driver.findElements(By.className("question-card-question-no"));
                System.out.println("questionCards.size()::"+questionCards.size());
                for(int i = 0; i < 5; i++)
                {
                    new Navigator().NavigateTo("Proficiency Report"); // Navigate to Proficiency Report
                    questionCards = driver.findElements(By.className("question-card-question-no"));
                    String questionNumber = questionCards.get(i).getText();
                    System.out.println("questionNumber::" + questionNumber);
                    questionCards.get(i).click();
                    //Post Discussion
                    new Discussion().addDiscussionInQuestionReviewPage();
                    new Navigator().NavigateTo("Course Stream");
                    List<WebElement> jumpOut = driver.findElements(By.cssSelector("span[class='ls-jumpout-icon-link ls-jumpout-icon']"));
                    jumpOut.get(0).click();
                    Thread.sleep(3000);
                    String questionLabel = driver.findElement(By.xpath(".//*[starts-with(@class,'question-display-label')]")).getText().replace(" ","");
                    System.out.println("questionLabel::"+questionLabel);
                    if(!questionLabel.trim().contains(questionNumber))
                    {
                        Assert.fail("Question review is not displayed.");
                    }
                }
            }
            catch (Exception e)
            {
                Assert.fail("Exception in test case diagnosticTestProficiencyReportPage of class SpanishFrenchItalianPaletteAsStudent:", e);
            }

        }

        @Test(priority = 11,enabled = true)
        public void spanishFrenchItalianPaletteIntryItForDiagTest()
        {
            try
            {
                int dataIndex = 1049;
                String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

                new LoginUsingLTI().ltiLogin("1049"); // Login as Instructor
                new Navigator().NavigateTo("eTextBook");
                // Click on assessment
                driver.findElement(By.cssSelector("span[title='ORION Adaptive Practice']")).click();

                Thread.sleep(1000);
                driver.findElement(By.xpath(".//*[@class='inner-assessment-block diagnostic_assessment']//div[@class='ls-inner-arw']")).click(); // Click on Arrow
                String parentHandle = driver.getWindowHandle();

                driver.findElement(By.xpath(".//*[@class='inner-assessment-block diagnostic_assessment']//div[@class='toc-try-it']")).click(); // Click on TryIt
                Thread.sleep(5000);
                for (String winHandle : driver.getWindowHandles()) {
                    driver.switchTo().window(winHandle).manage().window().maximize(); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
                }
                // Navigate to TextEntry Qs
                System.out.println("currentURL::" + driver.getCurrentUrl());
                int previewdropdown = driver.findElements(By.className("tryit-preview-dropdown")).size();
                System.out.println("previewdropdown::"+previewdropdown);
                while(previewdropdown>0)
                {
                    String questionText = driver.findElement(By.id("question-raw-content-preview")).getText();
                    System.out.println("questionText::"+questionText);
                    if(questionText.trim().contains("áÁéÉíÍñÑóÓúÚüÜ¡¿"))
                    {
                        break;
                    }
                    else
                    {
                        driver.findElement(By.cssSelector("i[class='footer-btn-arws next-btn-arw']")).click();
                        Thread.sleep(1000);
                        previewdropdown = driver.findElements(By.className("tryit-preview-dropdown")).size();
                    }
                }

                Thread.sleep(2000);
                boolean spanishPopUp = driver.findElement(By.cssSelector("span[class='spanish-popup spanish-popup-alignment']")).isDisplayed();
                if(spanishPopUp==false)
                {
                    Assert.fail("Text entry question with spanish option is not displayed.");
                }
                boolean paletteIcon = driver.findElement(By.xpath(".//div[@class='text-entry-input-wrapper visible_redactor_input_wrapper']//div[@class='show-spanish-bg spanish-bg-alignment']")).isDisplayed();
                if(paletteIcon==false)
                {
                    Assert.fail("In Question text box is not contain language palette icon.");
                }
                // Enter KeyBoardChars in textBox
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).click();
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).sendKeys("abcd");
                // Enter spanish chars
                driver.findElement(By.cssSelector("div[class='show-spanish-bg spanish-bg-alignment']")).click();
                // verify spanish palette popup
                boolean spanishpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(spanishpopUp==false)
                {
                    Assert.fail("Language palette popup is not displayed.");
                }
                // verify Spanish Editor popup
                String paletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!paletteHeader.trim().equals("Spanish Editor"))
                {
                    Assert.fail("The \"Spanish Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                boolean cancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(cancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Spanish Palette .");
                }
                // Palette Save
                boolean savePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(savePalette==false)
                {
                    Assert.fail("\"Save\" button is not present Spanish Palette .");
                }
                // Spanish Characters
                int spanishCharsList = driver.findElements(By.className("language-icon")).size();
                if(spanishCharsList!=16)
                {
                    Assert.fail("All spanish characters are not present");
                }
                // Click on each spanish character
                List<WebElement> clickSpanishChars = driver.findElements(By.className("language-icon"));
                for(int a = 0; a<clickSpanishChars.size();a++)
                {
                    clickSpanishChars.get(a).click();
                }
                Thread.sleep(2000);
                //Click on Save
                driver.findElement(By.id("save-language-text")).click();
                boolean paletteClosed = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(paletteClosed==true)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Submit question
                driver.findElement(By.id("question-try-it-next")).click();
            /*boolean questionReviewPage = driver.findElement(By.className("yourresponse-correctanswer")).isDisplayed();
            if (questionReviewPage==false)
            {
                Assert.fail("Question review page is not displayed.");
            }*/
                // Next Question
                // driver.findElement(By.className("ls-static-practice-test-next-button")).click();
                // Question with French palette
                Thread.sleep(2000);
                boolean frenchPopUp = driver.findElement(By.cssSelector("span[class='spanish-popup spanish-popup-alignment']")).isDisplayed();
                if(frenchPopUp==false)
                {
                    Assert.fail("Text entry question with french option is not displayed.");
                }
                boolean frenchPaletteIcon = driver.findElement(By.xpath(".//div[@class='text-entry-input-wrapper visible_redactor_input_wrapper']//div[@class='show-spanish-bg spanish-bg-alignment']")).isDisplayed();
                if(frenchPaletteIcon==false)
                {
                    Assert.fail("In Question text box is not contain language french palette icon.");
                }
                // Enter KeyBoardChars in textBox
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).click();
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).sendKeys("fghij");
                // Enter french chars
                driver.findElement(By.cssSelector("div[class='show-spanish-bg spanish-bg-alignment']")).click();
                // verify french palette popup
                boolean frenchpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(frenchpopUp==false)
                {
                    Assert.fail("Language palette popup is not displayed.");
                }
                // verify french Editor popup
                String frenchPaletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!frenchPaletteHeader.trim().equals("French Editor"))
                {
                    Assert.fail("The \"French Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                boolean frenchCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(frenchCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Spanish Palette .");
                }
                // Palette Save
                boolean frenchSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(frenchSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present Spanish Palette .");
                }
                // french Characters
                int frenchCharsList = driver.findElements(By.className("language-icon")).size();
                if(frenchCharsList!=24)
                {
                    Assert.fail("All french characters are not present");
                }
                // Click on each french character
                List<WebElement> clickFrenchChars = driver.findElements(By.className("language-icon"));
                for(int i = 0; i<clickFrenchChars.size();i++)
                {
                    clickFrenchChars.get(i).click();
                }
                Thread.sleep(2000);
                //Click on Save
                driver.findElement(By.id("save-language-text")).click();
                paletteClosed = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(paletteClosed==true)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Submit question
                driver.findElement(By.id("question-try-it-next")).click();
           /* boolean questionReview = driver.findElement(By.className("yourresponse-correctanswer")).isDisplayed();
            if (questionReview==false)
            {
                Assert.fail("Question review page is not displayed.");
            }*/
                // Next Question
                // driver.findElement(By.className("ls-static-practice-test-next-button")).click();
                // Question with Italian palette
                Thread.sleep(2000);
                boolean italianPopUp = driver.findElement(By.cssSelector("span[class='spanish-popup spanish-popup-alignment']")).isDisplayed();
                if(italianPopUp==false)
                {
                    Assert.fail("Text entry question with italian option is not displayed.");
                }
                boolean italianPaletteIcon = driver.findElement(By.xpath(".//div[@class='text-entry-input-wrapper visible_redactor_input_wrapper']//div[@class='show-spanish-bg spanish-bg-alignment']")).isDisplayed();
                if(italianPaletteIcon==false)
                {
                    Assert.fail("In Question text box is not contain language italian palette icon.");
                }
                // Enter KeyBoardChars in textBox
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).click();
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).sendKeys("klmn");
                // Enter italian chars
                driver.findElement(By.cssSelector("div[class='show-spanish-bg spanish-bg-alignment']")).click();
                // verify french palette popup
                boolean italianpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(italianpopUp==false)
                {
                    Assert.fail("Language palette popup is not displayed.");
                }
                // verify french Editor popup
                String italianPaletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!italianPaletteHeader.trim().equals("Italian Editor"))
                {
                    Assert.fail("The \"Italian Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                boolean italianCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(italianCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Italian Palette .");
                }
                // Palette Save
                boolean italianSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(italianSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present Italian Palette .");
                }
                // Italian Characters
                int italianCharsList = driver.findElements(By.className("language-icon")).size();
                if(italianCharsList!=22)
                {
                    Assert.fail("All italian characters are not present");
                }
                // Click on each french character
                List<WebElement> clickItalianChars = driver.findElements(By.className("language-icon"));
                for(int i = 0; i<clickItalianChars.size();i++)
                {
                    clickItalianChars.get(i).click();
                }
                Thread.sleep(2000);
                //Click on Save
                driver.findElement(By.id("save-language-text")).click();
                paletteClosed = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(paletteClosed==true)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Submit question
                driver.findElement(By.id("question-try-it-next")).click();
            /*questionReviewPage = driver.findElement(By.className("yourresponse-correctanswer")).isDisplayed();
            if (questionReviewPage==false)
            {
                Assert.fail("Question review page is not displayed.");
            }*/
                // Next Question
                // driver.findElement(By.className("ls-static-practice-test-next-button")).click();

                // Essay question
                boolean essayQuestion = driver.findElement(By.className("essay-question-redactor-content-wrapper")).isDisplayed();
                if (essayQuestion==false)
                {
                    Assert.fail("Eassy type question is not displayed.");
                }
                boolean languagePalette = driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).isDisplayed();
                if (languagePalette==false)
                {
                    Assert.fail("The langauge palette icon is not in the top vertical tool options.");
                }

                driver.close(); // close newly opened window when done with it
                driver.switchTo().window(parentHandle);

            }
            catch (Exception e)
            {
                Assert.fail("Exception in test case spanishFrenchItalianPaletteIntryItForDiagTest of class SpanishFrenchItalianPaletteAsStudent:", e);
            }
        }

        @Test(priority = 12,enabled = true)
        public void spanishFrenchItalianPaletteEssayQsInstructoraa()
        {
            try
            {
                int dataIndex = 1049;
                String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

                new LoginUsingLTI().ltiLogin("1049");
                new Navigator().NavigateTo("eTextBook");
                // Click on assessment
                driver.findElement(By.cssSelector("span[title='ORION Adaptive Practice']")).click();

                Thread.sleep(1000);
                driver.findElement(By.xpath(".//*[@class='inner-assessment-block diagnostic_assessment']//div[@class='ls-inner-arw']")).click(); // Click on Arrow
                String parentHandle = driver.getWindowHandle();

                driver.findElement(By.xpath(".//*[@class='inner-assessment-block diagnostic_assessment']//div[@class='toc-try-it']")).click(); // Click on TryIt
                Thread.sleep(5000);
                for (String winHandle : driver.getWindowHandles()) {
                    driver.switchTo().window(winHandle).manage().window().maximize(); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
                }
                //Select essay questions
                System.out.println("currentURL::"+driver.getCurrentUrl());
                int previewdropdown = driver.findElements(By.className("tryit-preview-dropdown")).size();
                System.out.println("previewdropdown::" + previewdropdown);
                while(previewdropdown>0)
                {
                    String questionText = driver.findElement(By.id("question-raw-content-preview")).getText();
                    System.out.println("questionText::"+questionText);
                    if(questionText.trim().contains("Essay QuestionText_IT23_R237_1045"))
                    {
                        break;
                    }
                    else
                    {
                        driver.findElement(By.cssSelector("i[class='footer-btn-arws next-btn-arw']")).click();
                        Thread.sleep(1000);
                        previewdropdown = driver.findElements(By.className("tryit-preview-dropdown")).size();
                    }
                }
                // Essay question
                boolean essayQuestion = driver.findElement(By.className("essay-question-redactor-content-wrapper")).isDisplayed();
                if (essayQuestion==false)
                {
                    Assert.fail("Eassy type question is not displayed.");
                }
                boolean languagePalette = driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).isDisplayed();
                if (languagePalette==false)
                {
                    Assert.fail("The langauge palette icon is not in the top vertical tool options.");
                }
                // Click on language palette
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                List<WebElement> listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                System.out.println("listOfLanguages.get(0).getText()::"+listOfLanguages.get(0).getText());
                System.out.println("listOfLanguages.get(0).getText()::"+listOfLanguages.get(1).getText());
                System.out.println("listOfLanguages.get(0).getText()::"+listOfLanguages.get(2).getText());

                if(!listOfLanguages.get(0).getText().trim().equals("Spanish") && !listOfLanguages.get(1).getText().trim().equals("Italian") && !listOfLanguages.get(2).getText().trim().equals("French") )
                {
                    Assert.fail("The language options are not displayed in same order as 1.Spanish 2.Italian 3.French.");
                }
                // Click on the Spanish Palette
                listOfLanguages.get(0).click();
                // verify spanish palette popup
                boolean spanishpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(spanishpopUp==false)
                {
                    Assert.fail("Language palette popup is not displayed.");
                }
                // verify Spanish Editor popup
                String paletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!paletteHeader.trim().equals("Spanish Editor"))
                {
                    Assert.fail("The \"Spanish Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                boolean spanishCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(spanishCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Italian Palette .");
                }
                // Palette Save
                boolean spanishSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(spanishSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present Italian Palette .");
                }
                // Spanish Characters
                int spanishCharsList = driver.findElements(By.className("language-icon")).size();
                if(spanishCharsList!=16)
                {
                    Assert.fail("All spanish characters are not present");
                }
                // Click on each spanish character
                List<WebElement> clickSpanishChars = driver.findElements(By.className("language-icon"));
                for(int a = 0; a<clickSpanishChars.size();a++)
                {
                    clickSpanishChars.get(a).click();
                }
                Thread.sleep(2000);
                driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                JavascriptExecutor jse=(JavascriptExecutor)driver;
                String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("inputStringValue::" + inputStringValue);
                if(!inputStringValue.trim().equals("áÁéÉíÍñÑóÓúÚüÜ¡¿"))
                {
                    Assert.fail("The selected character are not displayed in the text box of the Palette .");
                }
                driver.findElement(By.id("save-language-text")).click(); // Save

                String textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(!textInEssayBox.trim().equals("áÁéÉíÍñÑóÓúÚüÜ¡¿"))
                {
                    Assert.fail("All the character ate not saved in Essay text box.");
                }
                // Save spanish palette popup close
                int paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Click on Cancel
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(0).click();
                // enter some character
                driver.findElement(By.id("languagePaletteBox")).click();
                driver.findElement(By.id("languagePaletteBox")).sendKeys("abcd1233");

                driver.findElement(By.id("cancelLanguage")).click(); // click on cancel

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(textInEssayBox.trim().contains("abcd1233"))
                {
                    Assert.fail("On click of Cancel characters are present in essay text box.");
                }
                // cancel spanish palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                driver.findElement(By.id("html-editor-non-draggable")).click();
                driver.findElement(By.id("html-editor-non-draggable")).clear();
                // Italian Palette
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(1).click(); // click on italian
                // verify Italian palette popup
                boolean italianpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(italianpopUp==false)
                {
                    Assert.fail("Italian Language palette popup is not displayed.");
                }
                // verify Italian Editor popup
                paletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!paletteHeader.trim().equals("Italian Editor"))
                {
                    Assert.fail("The \"Italian Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                spanishCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(spanishCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Italian Palette .");
                }
                // Palette Save
                spanishSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(spanishSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present Italian Palette .");
                }
                // Italian Characters
                spanishCharsList = driver.findElements(By.className("language-icon")).size();
                if(spanishCharsList!=22)
                {
                    Assert.fail("All Italian characters are not present");
                }
                // Click on each Italian character
                clickSpanishChars = driver.findElements(By.className("language-icon"));
                for(int a = 0; a<clickSpanishChars.size();a++)
                {
                    clickSpanishChars.get(a).click();
                }
                Thread.sleep(2000);
                driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                jse=(JavascriptExecutor)driver;
                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("inputStringValue::" + inputStringValue);
                if(!inputStringValue.trim().equals("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤"))
                {
                    Assert.fail("The selected character are not displayed in the text box of the Italian Palette .");
                }
                driver.findElement(By.id("save-language-text")).click(); // Save

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(!textInEssayBox.trim().equals("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤"))
                {
                    Assert.fail("All the character are not saved in Essay text box.");
                }
                // Save Italian palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Click on Cancel
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(1).click();
                // enter some character
                driver.findElement(By.id("languagePaletteBox")).click();
                driver.findElement(By.id("languagePaletteBox")).sendKeys("abcd1233");

                driver.findElement(By.id("cancelLanguage")).click(); // click on cancel

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(textInEssayBox.trim().contains("abcd1233"))
                {
                    Assert.fail("On click of Cancel characters are present in essay text box.");
                }
                // cancel spanish palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                driver.findElement(By.id("html-editor-non-draggable")).click();
                driver.findElement(By.id("html-editor-non-draggable")).clear();

                // French Palette

                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(2).click(); // click on french
                // verify French palette popup
                boolean frenchpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(frenchpopUp==false)
                {
                    Assert.fail("French Language palette popup is not displayed.");
                }
                // verify French Editor popup
                paletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!paletteHeader.trim().equals("French Editor"))
                {
                    Assert.fail("The \"French Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                spanishCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(spanishCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present French Palette .");
                }
                // Palette Save
                spanishSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(spanishSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present French Palette .");
                }
                // French Characters
                spanishCharsList = driver.findElements(By.className("language-icon")).size();
                if(spanishCharsList!=24)
                {
                    Assert.fail("All French characters are not present");
                }
                // Click on each French character
                clickSpanishChars = driver.findElements(By.className("language-icon"));
                for(int a = 0; a<clickSpanishChars.size();a++)
                {
                    clickSpanishChars.get(a).click();
                }
                Thread.sleep(2000);
                driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                jse=(JavascriptExecutor)driver;
                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("inputStringValue::" + inputStringValue);
                if(!inputStringValue.trim().equals("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"))
                {
                    Assert.fail("The selected character are not displayed in the text box of the French Palette .");
                }
                driver.findElement(By.id("save-language-text")).click(); // Save

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(!textInEssayBox.trim().equals("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"))
                {
                    Assert.fail("All the character are not saved in Essay text box.");
                }
                // Save French palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Click on Cancel
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(2).click();
                // enter some character
                driver.findElement(By.id("languagePaletteBox")).click();
                driver.findElement(By.id("languagePaletteBox")).sendKeys("abcd1233");

                driver.findElement(By.id("cancelLanguage")).click(); // click on cancel

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(textInEssayBox.trim().contains("abcd1233"))
                {
                    Assert.fail("On click of Cancel characters are present in essay text box.");
                }
                // cancel spanish palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                driver.findElement(By.id("question-try-it-finish")).click(); // Submit Essay Qs
                driver.switchTo().window(parentHandle);
            }
            catch (Exception e)
            {
                Assert.fail("Exception in test case spanishFrenchItalianPaletteEssayQsInstructor of class SpanishFrenchItalianPaletteAsStudent:", e);
            }
        }

        @Test(priority = 13,enabled = true)
        public void assignmentAsStudent()
        {
            try
            {
                int dataIndex = 1051;
                String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
                System.out.println("assessmentname::" + assessmentname);
                new Assignment().create(1051); // Create assessment
                // Ass Text entry questions
                new Assignment().addQuestions(1051, "textentry", "");
                new Assignment().addQuestions(1052, "textentry", "");
                new Assignment().addQuestions(1053, "textentry", "");
                new Assignment().addQuestions(1054,"essay","");
                new LoginUsingLTI().ltiLogin("1051"); // Login as instructor
                new Navigator().NavigateTo("Question Banks"); // Navigate to Assignments
                // Click on assessment
                List<WebElement> assessmentList = driver.findElements(By.cssSelector("div[subtype='static_assessment']"));
                for(int i=0;i<assessmentList.size();i++)
                {
                    String assessment = assessmentList.get(i).getAttribute("title");
                    System.out.println("assessmentList.get(i).getText()::" + assessmentList.get(i).getAttribute("title"));
                    Thread.sleep(1000);
                    if(assessment.trim().equals(assessmentname))
                    {
                        Thread.sleep(1000);
                        System.out.println("assessment::" + assessment);
                        Thread.sleep(1000);
                        List<WebElement> assignThis = driver.findElements(By.cssSelector("span[title='Assign This']"));
                        assignThis.get(i).click();
                        System.out.println("i::"+i);
                        break;
                    }
                }
                new Assignment().assignToStudent(1051);
                new LoginUsingLTI().ltiLogin("1052"); // Login as student
                new Navigator().NavigateTo("Assignments"); // Navigate to Assignments
                List<WebElement> assignmentsList = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
                for(int a = 0; a < assignmentsList.size(); a++)
                {
                    String assignmentsName = assignmentsList.get(a).getText();
                    if(assignmentsName.trim().contains(assessmentname))
                    {
                        assignmentsList.get(a).click();
                    }
                }
                Thread.sleep(2000);
                // Navigate to Text entry Qs
                driver.findElement(By.id("show-question-detials")).click();
                List<WebElement> questionList = driver.findElements(By.className("s--check-enable"));
                questionList.get(0).click();
                // Text entry
                Thread.sleep(2000);
                boolean spanishPopUp = driver.findElement(By.cssSelector("span[class='spanish-popup spanish-popup-alignment']")).isDisplayed();
                if(spanishPopUp==false)
                {
                    Assert.fail("Text entry question with spanish option is not displayed.");
                }
                boolean paletteIcon = driver.findElement(By.xpath(".//div[@class='text-entry-input-wrapper visible_redactor_input_wrapper']//div[@class='show-spanish-bg spanish-bg-alignment']")).isDisplayed();
                if(paletteIcon==false)
                {
                    Assert.fail("In Question text box is not contain language palette icon.");
                }
                // Enter KeyBoardChars in textBox
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).click();
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).sendKeys("abcd");
                // Enter spanish chars
                driver.findElement(By.cssSelector("div[class='show-spanish-bg spanish-bg-alignment']")).click();
                // verify spanish palette popup
                boolean spanishpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(spanishpopUp==false)
                {
                    Assert.fail("Language palette popup is not displayed.");
                }
                // verify Spanish Editor popup
                String paletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!paletteHeader.trim().equals("Spanish Editor"))
                {
                    Assert.fail("The \"Spanish Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                boolean cancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(cancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Spanish Palette .");
                }
                // Palette Save
                boolean savePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(savePalette==false)
                {
                    Assert.fail("\"Save\" button is not present Spanish Palette .");
                }
                // Spanish Characters
                int spanishCharsList = driver.findElements(By.className("language-icon")).size();
                if(spanishCharsList!=16)
                {
                    Assert.fail("All spanish characters are not present");
                }
                // Click on each spanish character
                List<WebElement> clickSpanishChars = driver.findElements(By.className("language-icon"));
                for(int a = 0; a<clickSpanishChars.size();a++)
                {
                    clickSpanishChars.get(a).click();
                }
                Thread.sleep(2000);
                //Click on Save
                driver.findElement(By.id("save-language-text")).click();
                boolean paletteClosed = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(paletteClosed==true)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Submit question
                driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).click();
            /*boolean questionReviewPage = driver.findElement(By.className("yourresponse-correctanswer")).isDisplayed();
            if (questionReviewPage==false)
            {
                Assert.fail("Question review page is not displayed.");
            }
            // Next Question
            driver.findElement(By.className("ls-static-practice-test-next-button")).click();*/
                // Question with French palette
                Thread.sleep(2000);
                boolean frenchPopUp = driver.findElement(By.cssSelector("span[class='spanish-popup spanish-popup-alignment']")).isDisplayed();
                if(frenchPopUp==false)
                {
                    Assert.fail("Text entry question with french option is not displayed.");
                }
                boolean frenchPaletteIcon = driver.findElement(By.xpath(".//div[@class='text-entry-input-wrapper visible_redactor_input_wrapper']//div[@class='show-spanish-bg spanish-bg-alignment']")).isDisplayed();
                if(frenchPaletteIcon==false)
                {
                    Assert.fail("In Question text box is not contain language french palette icon.");
                }
                // Enter KeyBoardChars in textBox
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).click();
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).sendKeys("fghij");
                // Enter french chars
                driver.findElement(By.cssSelector("div[class='show-spanish-bg spanish-bg-alignment']")).click();
                // verify french palette popup
                boolean frenchpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(frenchpopUp==false)
                {
                    Assert.fail("Language palette popup is not displayed.");
                }
                // verify french Editor popup
                String frenchPaletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!frenchPaletteHeader.trim().equals("French Editor"))
                {
                    Assert.fail("The \"French Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                boolean frenchCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(frenchCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Spanish Palette .");
                }
                // Palette Save
                boolean frenchSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(frenchSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present Spanish Palette .");
                }
                // french Characters
                int frenchCharsList = driver.findElements(By.className("language-icon")).size();
                if(frenchCharsList!=24)
                {
                    Assert.fail("All french characters are not present");
                }
                // Click on each french character
                List<WebElement> clickFrenchChars = driver.findElements(By.className("language-icon"));
                for(int i = 0; i<clickFrenchChars.size();i++)
                {
                    clickFrenchChars.get(i).click();
                }
                Thread.sleep(2000);
                //Click on Save
                driver.findElement(By.id("save-language-text")).click();
                paletteClosed = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(paletteClosed==true)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Submit question
                driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).click();
            /*boolean questionReview = driver.findElement(By.className("yourresponse-correctanswer")).isDisplayed();
            if (questionReview==false)
            {
                Assert.fail("Question review page is not displayed.");
            }
            // Next Question
            driver.findElement(By.className("ls-static-practice-test-next-button")).click();*/
                // Question with Italian palette
                Thread.sleep(2000);
                boolean italianPopUp = driver.findElement(By.cssSelector("span[class='spanish-popup spanish-popup-alignment']")).isDisplayed();
                if(italianPopUp==false)
                {
                    Assert.fail("Text entry question with italian option is not displayed.");
                }
                boolean italianPaletteIcon = driver.findElement(By.xpath(".//div[@class='text-entry-input-wrapper visible_redactor_input_wrapper']//div[@class='show-spanish-bg spanish-bg-alignment']")).isDisplayed();
                if(italianPaletteIcon==false)
                {
                    Assert.fail("In Question text box is not contain language italian palette icon.");
                }
                // Enter KeyBoardChars in textBox
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).click();
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).sendKeys("klmn");
                // Enter italian chars
                driver.findElement(By.cssSelector("div[class='show-spanish-bg spanish-bg-alignment']")).click();
                // verify french palette popup
                boolean italianpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(italianpopUp==false)
                {
                    Assert.fail("Language palette popup is not displayed.");
                }
                // verify french Editor popup
                String italianPaletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!italianPaletteHeader.trim().equals("Italian Editor"))
                {
                    Assert.fail("The \"Italian Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                boolean italianCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(italianCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Italian Palette .");
                }
                // Palette Save
                boolean italianSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(italianSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present Italian Palette .");
                }
                // Italian Characters
                int italianCharsList = driver.findElements(By.className("language-icon")).size();
                if(italianCharsList!=22)
                {
                    Assert.fail("All italian characters are not present");
                }
                // Click on each french character
                List<WebElement> clickItalianChars = driver.findElements(By.className("language-icon"));
                for(int i = 0; i<clickItalianChars.size();i++)
                {
                    clickItalianChars.get(i).click();
                }
                Thread.sleep(2000);
                //Click on Save
                driver.findElement(By.id("save-language-text")).click();
                paletteClosed = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(paletteClosed==true)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Submit question
                driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--next long-text-button']")).click();
            /*questionReviewPage = driver.findElement(By.className("yourresponse-correctanswer")).isDisplayed();
            if (questionReviewPage==false)
            {
                Assert.fail("Question review page is not displayed.");
            }
            // Next Question
            driver.findElement(By.className("ls-static-practice-test-next-button")).click();*/

                // Essay question
                boolean essayQuestion = driver.findElement(By.className("essay-question-redactor-content-wrapper")).isDisplayed();
                if (essayQuestion==false)
                {
                    Assert.fail("Eassy type question is not displayed.");
                }
                boolean languagePalette = driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).isDisplayed();
                if (languagePalette==false)
                {
                    Assert.fail("The langauge palette icon is not in the top vertical tool options.");
                }
            }
            catch (Exception e)
            {
                Assert.fail("Exception in test case assignmentAsStudent of class SpanishFrenchItalianPaletteAsStudent:", e);
            }
        }

        @Test(priority = 14,enabled = true)
        public void essayQuestionInassignmentAsStudent()
        {
            try
            {
                int dataIndex = 1052;
                String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
                System.out.println("assessmentname::" + assessmentname);
                new LoginUsingLTI().ltiLogin("1052"); // Login as student
                new Navigator().NavigateTo("Assignments"); // Navigate to Assignments
                List<WebElement> assignmentsList = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
                for(int a = 0; a < assignmentsList.size(); a++)
                {
                    String assignmentsName = assignmentsList.get(a).getText();
                    if(assignmentsName.trim().contains(assessmentname))
                    {
                        assignmentsList.get(a).click();
                    }
                }
                Thread.sleep(2000);
                // Essay question
                boolean essayQuestion = driver.findElement(By.className("essay-question-redactor-content-wrapper")).isDisplayed();
                if (essayQuestion==false)
                {
                    Assert.fail("Eassy type question is not displayed.");
                }
                boolean languagePalette = driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).isDisplayed();
                if (languagePalette==false)
                {
                    Assert.fail("The langauge palette icon is not in the top vertical tool options.");
                }
                // Click on language palette
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                List<WebElement> listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                System.out.println("listOfLanguages.get(0).getText()::"+listOfLanguages.get(0).getText());
                System.out.println("listOfLanguages.get(0).getText()::"+listOfLanguages.get(1).getText());
                System.out.println("listOfLanguages.get(0).getText()::"+listOfLanguages.get(2).getText());

                if(!listOfLanguages.get(0).getText().trim().equals("Spanish") && !listOfLanguages.get(1).getText().trim().equals("Italian") && !listOfLanguages.get(2).getText().trim().equals("French") )
                {
                    Assert.fail("The language options are not displayed in same order as 1.Spanish 2.Italian 3.French.");
                }
                // Click on the Spanish Palette
                listOfLanguages.get(0).click();
                // verify spanish palette popup
                boolean spanishpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(spanishpopUp==false)
                {
                    Assert.fail("Language palette popup is not displayed.");
                }
                // verify Spanish Editor popup
                String paletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!paletteHeader.trim().equals("Spanish Editor"))
                {
                    Assert.fail("The \"Spanish Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                boolean spanishCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(spanishCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Italian Palette .");
                }
                // Palette Save
                boolean spanishSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(spanishSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present Italian Palette .");
                }
                // Spanish Characters
                int spanishCharsList = driver.findElements(By.className("language-icon")).size();
                if(spanishCharsList!=16)
                {
                    Assert.fail("All spanish characters are not present");
                }
                // Click on each spanish character
                List<WebElement> clickSpanishChars = driver.findElements(By.className("language-icon"));
                for(int a = 0; a<clickSpanishChars.size();a++)
                {
                    clickSpanishChars.get(a).click();
                }
                Thread.sleep(2000);
                driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                JavascriptExecutor jse=(JavascriptExecutor)driver;
                String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("inputStringValue::" + inputStringValue);
                if(!inputStringValue.trim().equals("áÁéÉíÍñÑóÓúÚüÜ¡¿"))
                {
                    Assert.fail("The selected character are not displayed in the text box of the Palette .");
                }
                driver.findElement(By.id("save-language-text")).click(); // Save

                String textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(!textInEssayBox.trim().equals("áÁéÉíÍñÑóÓúÚüÜ¡¿"))
                {
                    Assert.fail("All the character ate not saved in Essay text box.");
                }
                // Save spanish palette popup close
                int paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Click on Cancel
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(0).click();
                // enter some character
                driver.findElement(By.id("languagePaletteBox")).click();
                driver.findElement(By.id("languagePaletteBox")).sendKeys("abcd1233");

                driver.findElement(By.id("cancelLanguage")).click(); // click on cancel

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(textInEssayBox.trim().contains("abcd1233"))
                {
                    Assert.fail("On click of Cancel characters are present in essay text box.");
                }
                // cancel spanish palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                driver.findElement(By.id("html-editor-non-draggable")).click();
                driver.findElement(By.id("html-editor-non-draggable")).clear();
                // Italian Palette
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(1).click(); // click on italian
                // verify Italian palette popup
                boolean italianpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(italianpopUp==false)
                {
                    Assert.fail("Italian Language palette popup is not displayed.");
                }
                // verify Italian Editor popup
                paletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!paletteHeader.trim().equals("Italian Editor"))
                {
                    Assert.fail("The \"Italian Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                spanishCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(spanishCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Italian Palette .");
                }
                // Palette Save
                spanishSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(spanishSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present Italian Palette .");
                }
                // Italian Characters
                spanishCharsList = driver.findElements(By.className("language-icon")).size();
                if(spanishCharsList!=22)
                {
                    Assert.fail("All Italian characters are not present");
                }
                // Click on each Italian character
                clickSpanishChars = driver.findElements(By.className("language-icon"));
                for(int a = 0; a<clickSpanishChars.size();a++)
                {
                    clickSpanishChars.get(a).click();
                }
                Thread.sleep(2000);
                driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                jse=(JavascriptExecutor)driver;
                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("inputStringValue::" + inputStringValue);
                if(!inputStringValue.trim().equals("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤"))
                {
                    Assert.fail("The selected character are not displayed in the text box of the Italian Palette .");
                }
                driver.findElement(By.id("save-language-text")).click(); // Save

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(!textInEssayBox.trim().equals("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤"))
                {
                    Assert.fail("All the character are not saved in Essay text box.");
                }
                // Save Italian palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Click on Cancel
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(1).click();
                // enter some character
                driver.findElement(By.id("languagePaletteBox")).click();
                driver.findElement(By.id("languagePaletteBox")).sendKeys("abcd1233");

                driver.findElement(By.id("cancelLanguage")).click(); // click on cancel

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(textInEssayBox.trim().contains("abcd1233"))
                {
                    Assert.fail("On click of Cancel characters are present in essay text box.");
                }
                // cancel spanish palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                driver.findElement(By.id("html-editor-non-draggable")).click();
                driver.findElement(By.id("html-editor-non-draggable")).clear();

                // French Palette

                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(2).click(); // click on french
                // verify French palette popup
                boolean frenchpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(frenchpopUp==false)
                {
                    Assert.fail("French Language palette popup is not displayed.");
                }
                // verify French Editor popup
                paletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!paletteHeader.trim().equals("French Editor"))
                {
                    Assert.fail("The \"French Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                spanishCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(spanishCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present French Palette .");
                }
                // Palette Save
                spanishSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(spanishSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present French Palette .");
                }
                // French Characters
                spanishCharsList = driver.findElements(By.className("language-icon")).size();
                if(spanishCharsList!=24)
                {
                    Assert.fail("All French characters are not present");
                }
                // Click on each French character
                clickSpanishChars = driver.findElements(By.className("language-icon"));
                for(int a = 0; a<clickSpanishChars.size();a++)
                {
                    clickSpanishChars.get(a).click();
                }
                Thread.sleep(2000);
                driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                jse=(JavascriptExecutor)driver;
                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("inputStringValue::" + inputStringValue);
                if(!inputStringValue.trim().equals("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"))
                {
                    Assert.fail("The selected character are not displayed in the text box of the French Palette .");
                }
                driver.findElement(By.id("save-language-text")).click(); // Save

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(!textInEssayBox.trim().equals("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"))
                {
                    Assert.fail("All the character are not saved in Essay text box.");
                }
                // Save French palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Click on Cancel
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(2).click();
                // enter some character
                driver.findElement(By.id("languagePaletteBox")).click();
                driver.findElement(By.id("languagePaletteBox")).sendKeys("abcd1233");

                driver.findElement(By.id("cancelLanguage")).click(); // click on cancel

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(textInEssayBox.trim().contains("abcd1233"))
                {
                    Assert.fail("On click of Cancel characters are present in essay text box.");
                }
                // cancel spanish palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit long-text-button']")).click(); // Submit Essay Qs
                boolean questionReviewPage = driver.findElement(By.id("performance-chart-performance-summery")).isDisplayed();
                if(questionReviewPage == false)
                {
                    Assert.fail("Not Navigated to Performance page");
                }
            }
            catch (Exception e)
            {
                Assert.fail("Exception in test case essayQuestionInassignmentAsStudent of class SpanishFrenchItalianPaletteAsStudent:", e);
            }
        }

        @Test(priority = 15,enabled = true)
        public void asignmentPerformanceSummaryPage()
        {
            try
            {
                int dataIndex = 1052;
                String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
                System.out.println("assessmentname::" + assessmentname);
                new LoginUsingLTI().ltiLogin("1052"); // Login as student
                new Navigator().NavigateTo("Assignments"); // Navigate to Assignments
                // Click on assessment
                List<WebElement> assignmentsList = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
                for(int a = 0; a < assignmentsList.size(); a++)
                {
                    String assignmentsName = assignmentsList.get(a).getText();
                    if(assignmentsName.trim().contains(assessmentname))
                    {
                        assignmentsList.get(a).click();
                    }
                }

                boolean questionReviewPage = driver.findElement(By.id("performance-chart-performance-summery")).isDisplayed();
                if(questionReviewPage == false)
                {
                    Assert.fail("Not Navigated to Performance page");
                }
                Thread.sleep(2000);
                // Click on Question card
                List<WebElement> questionCards = driver.findElements(By.className("question-card-question-no"));
                System.out.println("questionCards.size()::"+questionCards.size());
                for(int i = 0; i < questionCards.size(); i++)
                {
                    new Navigator().NavigateTo("Assignments"); // Navigate to Assignments
                    // Click on assessment
                    assignmentsList = driver.findElements(By.cssSelector("span[class='ls-assignment-name instructor-assessment-review']"));
                    for(int a = 0; a < assignmentsList.size(); a++)
                    {
                        String assignmentsName = assignmentsList.get(a).getText();
                        if(assignmentsName.trim().contains(assessmentname))
                        {
                            assignmentsList.get(a).click();
                            break;
                        }
                    }
                    questionCards = driver.findElements(By.className("question-card-question-no"));
                    String questionNumber = questionCards.get(i).getText();
                    System.out.println("questionNumber::" + questionNumber);
                    questionCards.get(i).click();
                    //Post Discussion
                    new Discussion().addDiscussionInQuestionReviewPage();
                    new Navigator().NavigateTo("Course Stream");
                    // Click on Jump Out
                    List<WebElement> jumpOut = driver.findElements(By.cssSelector("span[class='ls-jumpout-icon-link ls-jumpout-icon']"));
                    jumpOut.get(0).click();
                    Thread.sleep(3000);
                    String questionLabel = driver.findElement(By.xpath(".//*[starts-with(@class,'question-display-label')]")).getText().replace(" ","");
                    System.out.println("questionLabel::"+questionLabel);
                    if(!questionLabel.trim().contains(questionNumber))
                    {
                        Assert.fail("Question review is not displayed.");
                    }
                }
            }
            catch (Exception e)
            {
                Assert.fail("Exception in test case asignmentPerformanceSummaryPage of class SpanishFrenchItalianPaletteAsStudent:", e);
            }
        }

        @Test(priority = 16,enabled = true)
        public void assignmentProficiencyReportPage()
        {
            try
            {
                new LoginUsingLTI().ltiLogin("1052"); // Login as student
                new Navigator().NavigateTo("Proficiency Report"); // Navigate to Proficiency Report
                boolean reportTitle = driver.findElement(By.id("reportTitle")).isDisplayed();
                if(reportTitle==false)
                {
                    Assert.fail("Proficiency report page is not displayed.");
                }
                // Click on Question card
                List<WebElement> questionCards = driver.findElements(By.className("question-card-question-no"));
                System.out.println("questionCards.size()::"+questionCards.size());
                for(int i = 0; i < questionCards.size(); i++)
                {
                    new Navigator().NavigateTo("Proficiency Report"); // Navigate to Proficiency Report
                    questionCards = driver.findElements(By.className("question-card-question-no"));
                    String questionNumber = questionCards.get(i).getText();
                    System.out.println("questionNumber::" + questionNumber);
                    questionCards.get(i).click();
                    //Post Discussion
                    new Discussion().addDiscussionInQuestionReviewPage();
                    new Navigator().NavigateTo("Course Stream");
                    List<WebElement> jumpOut = driver.findElements(By.cssSelector("span[class='ls-jumpout-icon-link ls-jumpout-icon']"));
                    jumpOut.get(0).click();
                    Thread.sleep(3000);
                    String questionLabel = driver.findElement(By.xpath(".//*[starts-with(@class,'question-display-label')]")).getText().replace(" ","");
                    System.out.println("questionLabel::"+questionLabel);
                    if(!questionLabel.trim().contains(questionNumber))
                    {
                        Assert.fail("Question review is not displayed.");
                    }
                }

            }
            catch (Exception e)
            {
                Assert.fail("Exception in test case assignmentProficiencyReportPage of class SpanishFrenchItalianPaletteAsStudent:", e);
            }
        }

        @Test(priority = 17,enabled = true)
        public void MPQQuestionTextEntry()
        {
            try
            {
                int dataIndex = 1060;
                String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            /*new Assignment().createMPQQs(1060,"textEntry","1.1","textanswer1",true);
            new Assignment().addMPQQs(1061,"textEntry","1.1","textanswer2",true);
            new Assignment().addMPQQs(1062,"textEntry","1.1","textanswer3",true);
            new Assignment().addMPQQs(1063,"essay","1.1","",true);*/
                new LoginUsingLTI().ltiLogin("1060"); // login as student
                new Navigator().NavigateTo("eTextbook");
                // click on assessment
                WebElement html = driver.findElement(By.tagName("html"));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

                List<WebElement> assessmentList = driver.findElements(By.cssSelector("a[data-type='static_assessment']"));
                for(int i=0;i<assessmentList.size();i++)
                {
                    String assessment = assessmentList.get(i).getAttribute("title");
                    System.out.println("assessmentList.get(i).getText()::"+assessmentList.get(i).getAttribute("title"));
                    Thread.sleep(1000);
                    if(assessment.trim().equals(assignmentname))
                    {
                        Thread.sleep(1000);
                        System.out.println("assessment::" + assessment);
                        Thread.sleep(1000);
                        assessmentList.get(i).click();
                        break;
                    }
                }
                html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
                // expand button
                boolean expandIcon = driver.findElement(By.cssSelector("div[class='question-toggle-arrow-icon collapse']")).isDisplayed();
                if(expandIcon == false)
                {
                    Assert.fail("Text Entry question is not expanded.");
                }
                boolean paletteIcon = driver.findElement(By.xpath(".//div[@class='text-entry-input-wrapper visible_redactor_input_wrapper']//div[@class='show-spanish-bg spanish-bg-alignment']")).isDisplayed();
                if(paletteIcon==false)
                {
                    Assert.fail("In Question text box is not contain language palette icon.");
                }
                // Enter KeyBoardChars in textBox
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).click();
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).sendKeys("abcd");
                // Enter spanish chars
                driver.findElement(By.cssSelector("div[class='show-spanish-bg spanish-bg-alignment']")).click();
                // verify spanish palette popup
                boolean spanishpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(spanishpopUp==false)
                {
                    Assert.fail("Language palette popup is not displayed.");
                }
                // verify Spanish Editor popup
                String paletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!paletteHeader.trim().equals("Spanish Editor"))
                {
                    Assert.fail("The \"Spanish Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                boolean cancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(cancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Spanish Palette .");
                }
                // Palette Save
                boolean savePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(savePalette==false)
                {
                    Assert.fail("\"Save\" button is not present Spanish Palette .");
                }
                // Spanish Characters
                int spanishCharsList = driver.findElements(By.className("language-icon")).size();
                if(spanishCharsList!=16)
                {
                    Assert.fail("All spanish characters are not present");
                }
                // Click on each spanish character
                List<WebElement> clickSpanishChars = driver.findElements(By.className("language-icon"));
                for(int a = 0; a<clickSpanishChars.size();a++)
                {
                    clickSpanishChars.get(a).click();
                }
                Thread.sleep(2000);
                //Click on Save
                driver.findElement(By.id("save-language-text")).click();
                boolean paletteClosed = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(paletteClosed==true)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Submit question
                driver.findElement(By.className("ls-static-practice-test-submit-button")).click();
                boolean questionReviewPage = driver.findElement(By.className("yourresponse-correctanswer")).isDisplayed();
                if (questionReviewPage==false)
                {
                    Assert.fail("Question review page is not displayed.");
                }
                // Next Question
                driver.findElement(By.className("ls-static-practice-test-next-button")).click();
                // Question with French palette
                Thread.sleep(2000);
                // expand button
                expandIcon = driver.findElement(By.cssSelector("div[class='question-toggle-arrow-icon collapse']")).isDisplayed();
                if(expandIcon == false)
                {
                    Assert.fail("Text Entry question is not expanded.");
                }

                boolean frenchPopUp = driver.findElement(By.cssSelector("span[class='spanish-popup spanish-popup-alignment']")).isDisplayed();
                if(frenchPopUp==false)
                {
                    Assert.fail("Text entry question with french option is not displayed.");
                }
                boolean frenchPaletteIcon = driver.findElement(By.xpath(".//div[@class='text-entry-input-wrapper visible_redactor_input_wrapper']//div[@class='show-spanish-bg spanish-bg-alignment']")).isDisplayed();
                if(frenchPaletteIcon==false)
                {
                    Assert.fail("In Question text box is not contain language french palette icon.");
                }
                // Enter KeyBoardChars in textBox
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).click();
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).sendKeys("fghij");
                // Enter french chars
                driver.findElement(By.cssSelector("div[class='show-spanish-bg spanish-bg-alignment']")).click();
                // verify french palette popup
                boolean frenchpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(frenchpopUp==false)
                {
                    Assert.fail("Language palette popup is not displayed.");
                }
                // verify french Editor popup
                String frenchPaletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!frenchPaletteHeader.trim().equals("French Editor"))
                {
                    Assert.fail("The \"French Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                boolean frenchCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(frenchCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Spanish Palette .");
                }
                // Palette Save
                boolean frenchSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(frenchSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present Spanish Palette .");
                }
                // french Characters
                int frenchCharsList = driver.findElements(By.className("language-icon")).size();
                if(frenchCharsList!=24)
                {
                    Assert.fail("All french characters are not present");
                }
                // Click on each french character
                List<WebElement> clickFrenchChars = driver.findElements(By.className("language-icon"));
                for(int i = 0; i<clickFrenchChars.size();i++)
                {
                    clickFrenchChars.get(i).click();
                }
                Thread.sleep(2000);
                //Click on Save
                driver.findElement(By.id("save-language-text")).click();
                paletteClosed = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(paletteClosed==true)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Submit question
                driver.findElement(By.className("ls-static-practice-test-submit-button")).click();
                boolean questionReview = driver.findElement(By.className("yourresponse-correctanswer")).isDisplayed();
                if (questionReview==false)
                {
                    Assert.fail("Question review page is not displayed.");
                }
                // Next Question
                driver.findElement(By.className("ls-static-practice-test-next-button")).click();
                // Question with Italian palette
                Thread.sleep(2000);
                // expand button
                expandIcon = driver.findElement(By.cssSelector("div[class='question-toggle-arrow-icon collapse']")).isDisplayed();
                if(expandIcon == false)
                {
                    Assert.fail("Text Entry question is not expanded.");
                }
                boolean italianPopUp = driver.findElement(By.cssSelector("span[class='spanish-popup spanish-popup-alignment']")).isDisplayed();
                if(italianPopUp==false)
                {
                    Assert.fail("Text entry question with italian option is not displayed.");
                }
                boolean italianPaletteIcon = driver.findElement(By.xpath(".//div[@class='text-entry-input-wrapper visible_redactor_input_wrapper']//div[@class='show-spanish-bg spanish-bg-alignment']")).isDisplayed();
                if(italianPaletteIcon==false)
                {
                    Assert.fail("In Question text box is not contain language italian palette icon.");
                }
                // Enter KeyBoardChars in textBox
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).click();
                driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).sendKeys("klmn");
                // Enter italian chars
                driver.findElement(By.cssSelector("div[class='show-spanish-bg spanish-bg-alignment']")).click();
                // verify french palette popup
                boolean italianpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(italianpopUp==false)
                {
                    Assert.fail("Language palette popup is not displayed.");
                }
                // verify french Editor popup
                String italianPaletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!italianPaletteHeader.trim().equals("Italian Editor"))
                {
                    Assert.fail("The \"Italian Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                boolean italianCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(italianCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Italian Palette .");
                }
                // Palette Save
                boolean italianSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(italianSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present Italian Palette .");
                }
                // Italian Characters
                int italianCharsList = driver.findElements(By.className("language-icon")).size();
                if(italianCharsList!=22)
                {
                    Assert.fail("All italian characters are not present");
                }
                // Click on each Italian character
                List<WebElement> clickItalianChars = driver.findElements(By.className("language-icon"));
                for(int i = 0; i<clickItalianChars.size();i++)
                {
                    clickItalianChars.get(i).click();
                }
                Thread.sleep(2000);
                //Click on Save
                driver.findElement(By.id("save-language-text")).click();
                paletteClosed = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(paletteClosed==true)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Submit question
                driver.findElement(By.className("ls-static-practice-test-submit-button")).click();
                questionReviewPage = driver.findElement(By.className("yourresponse-correctanswer")).isDisplayed();
                if (questionReviewPage==false)
                {
                    Assert.fail("Question review page is not displayed.");
                }
                // Next Question
                driver.findElement(By.className("ls-static-practice-test-next-button")).click();

                // Essay question
                boolean essayQuestion = driver.findElement(By.className("essay-question-redactor-content-wrapper")).isDisplayed();
                if (essayQuestion==false)
                {
                    Assert.fail("Eassy type question is not displayed.");
                }
                boolean languagePalette = driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).isDisplayed();
                if (languagePalette==false)
                {
                    Assert.fail("The langauge palette icon is not in the top vertical tool options.");
                }
            }
            catch (Exception e)
            {
                Assert.fail("Exception in test case MPQQuestionTextEntry of class SpanishFrenchItalianPaletteAsStudent:", e);
            }
        }


        @Test(priority = 18,enabled = true)
        public void MPQQuestionEssay()
        {
            try
            {
                int dataIndex = 1060;
                String assignmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

                new LoginUsingLTI().ltiLogin("1060"); // login as student
                new Navigator().NavigateTo("eTextbook");
                // click on assessment
                WebElement html = driver.findElement(By.tagName("html"));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

                List<WebElement> assessmentList = driver.findElements(By.cssSelector("a[data-type='static_assessment']"));
                for(int i=0;i<assessmentList.size();i++)
                {
                    String assessment = assessmentList.get(i).getAttribute("title");
                    System.out.println("assessmentList.get(i).getText()::"+assessmentList.get(i).getAttribute("title"));
                    Thread.sleep(1000);
                    if(assessment.trim().equals(assignmentname))
                    {
                        Thread.sleep(1000);
                        System.out.println("assessment::" + assessment);
                        Thread.sleep(1000);
                        assessmentList.get(i).click();
                        break;
                    }
                }
                html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
                // expand button
                boolean expandIcon = driver.findElement(By.cssSelector("div[class='question-toggle-arrow-icon collapse']")).isDisplayed();
                if(expandIcon == false)
                {
                    Assert.fail("Text Entry question is not expanded.");
                }
                // Essay question
                boolean essayQuestion = driver.findElement(By.className("essay-question-redactor-content-wrapper")).isDisplayed();
                if (essayQuestion==false)
                {
                    Assert.fail("Eassy type question is not displayed.");
                }
                boolean languagePalette = driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).isDisplayed();
                if (languagePalette==false)
                {
                    Assert.fail("The langauge palette icon is not in the top vertical tool options.");
                }
                // Click on language palette
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                List<WebElement> listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                System.out.println("listOfLanguages.get(0).getText()::"+listOfLanguages.get(0).getText());
                System.out.println("listOfLanguages.get(0).getText()::"+listOfLanguages.get(1).getText());
                System.out.println("listOfLanguages.get(0).getText()::"+listOfLanguages.get(2).getText());

                if(!listOfLanguages.get(0).getText().trim().equals("Spanish") && !listOfLanguages.get(1).getText().trim().equals("Italian") && !listOfLanguages.get(2).getText().trim().equals("French") )
                {
                    Assert.fail("The language options are not displayed in same order as 1.Spanish 2.Italian 3.French.");
                }
                // Click on the Spanish Palette
                listOfLanguages.get(0).click();
                // verify spanish palette popup
                boolean spanishpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(spanishpopUp==false)
                {
                    Assert.fail("Language palette popup is not displayed.");
                }
                // verify Spanish Editor popup
                String paletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!paletteHeader.trim().equals("Spanish Editor"))
                {
                    Assert.fail("The \"Spanish Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                boolean spanishCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(spanishCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Italian Palette .");
                }
                // Palette Save
                boolean spanishSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(spanishSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present Italian Palette .");
                }
                // Spanish Characters
                int spanishCharsList = driver.findElements(By.className("language-icon")).size();
                if(spanishCharsList!=16)
                {
                    Assert.fail("All spanish characters are not present");
                }
                // Click on each spanish character
                List<WebElement> clickSpanishChars = driver.findElements(By.className("language-icon"));
                for(int a = 0; a<clickSpanishChars.size();a++)
                {
                    clickSpanishChars.get(a).click();
                }
                Thread.sleep(2000);
                driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                JavascriptExecutor jse=(JavascriptExecutor)driver;
                String inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("inputStringValue::" + inputStringValue);
                if(!inputStringValue.trim().equals("áÁéÉíÍñÑóÓúÚüÜ¡¿"))
                {
                    Assert.fail("The selected character are not displayed in the text box of the Palette .");
                }
                driver.findElement(By.id("save-language-text")).click(); // Save

                String textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(!textInEssayBox.trim().equals("áÁéÉíÍñÑóÓúÚüÜ¡¿"))
                {
                    Assert.fail("All the character ate not saved in Essay text box.");
                }
                // Save spanish palette popup close
                int paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Click on Cancel
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(0).click();
                // enter some character
                driver.findElement(By.id("languagePaletteBox")).click();
                driver.findElement(By.id("languagePaletteBox")).sendKeys("abcd1233");

                driver.findElement(By.id("cancelLanguage")).click(); // click on cancel

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(textInEssayBox.trim().contains("abcd1233"))
                {
                    Assert.fail("On click of Cancel characters are present in essay text box.");
                }
                // cancel spanish palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                driver.findElement(By.id("html-editor-non-draggable")).click();
                driver.findElement(By.id("html-editor-non-draggable")).clear();
                // Italian Palette
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(1).click(); // click on italian
                // verify Italian palette popup
                boolean italianpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(italianpopUp==false)
                {
                    Assert.fail("Italian Language palette popup is not displayed.");
                }
                // verify Italian Editor popup
                paletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!paletteHeader.trim().equals("Italian Editor"))
                {
                    Assert.fail("The \"Italian Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                spanishCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(spanishCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present Italian Palette .");
                }
                // Palette Save
                spanishSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(spanishSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present Italian Palette .");
                }
                // Italian Characters
                spanishCharsList = driver.findElements(By.className("language-icon")).size();
                if(spanishCharsList!=22)
                {
                    Assert.fail("All Italian characters are not present");
                }
                // Click on each Italian character
                clickSpanishChars = driver.findElements(By.className("language-icon"));
                for(int a = 0; a<clickSpanishChars.size();a++)
                {
                    clickSpanishChars.get(a).click();
                }
                Thread.sleep(2000);
                driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                jse=(JavascriptExecutor)driver;
                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("inputStringValue::" + inputStringValue);
                if(!inputStringValue.trim().equals("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤"))
                {
                    Assert.fail("The selected character are not displayed in the text box of the Italian Palette .");
                }
                driver.findElement(By.id("save-language-text")).click(); // Save

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(!textInEssayBox.trim().equals("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤"))
                {
                    Assert.fail("All the character are not saved in Essay text box.");
                }
                // Save Italian palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Click on Cancel
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(1).click();
                // enter some character
                driver.findElement(By.id("languagePaletteBox")).click();
                driver.findElement(By.id("languagePaletteBox")).sendKeys("abcd1233");

                driver.findElement(By.id("cancelLanguage")).click(); // click on cancel

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(textInEssayBox.trim().contains("abcd1233"))
                {
                    Assert.fail("On click of Cancel characters are present in essay text box.");
                }
                // cancel spanish palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                driver.findElement(By.id("html-editor-non-draggable")).click();
                driver.findElement(By.id("html-editor-non-draggable")).clear();

                // French Palette

                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(2).click(); // click on french
                // verify French palette popup
                boolean frenchpopUp = driver.findElement(By.id("language-palette-outer-wrapper")).isDisplayed();
                if(frenchpopUp==false)
                {
                    Assert.fail("French Language palette popup is not displayed.");
                }
                // verify French Editor popup
                paletteHeader = driver.findElement(By.className("palette-header")).getText();
                if(!paletteHeader.trim().equals("French Editor"))
                {
                    Assert.fail("The \"French Editor\" text is not displayed in header of language editor popup.");
                }
                // Palette Cancel
                spanishCancelPalette = driver.findElement(By.id("cancelLanguage")).isDisplayed();
                if(spanishCancelPalette==false)
                {
                    Assert.fail("\"Cancel\" button is not present French Palette .");
                }
                // Palette Save
                spanishSavePalette = driver.findElement(By.id("save-language-text")).isDisplayed();
                if(spanishSavePalette == false)
                {
                    Assert.fail("\"Save\" button is not present French Palette .");
                }
                // French Characters
                spanishCharsList = driver.findElements(By.className("language-icon")).size();
                if(spanishCharsList!=24)
                {
                    Assert.fail("All French characters are not present");
                }
                // Click on each French character
                clickSpanishChars = driver.findElements(By.className("language-icon"));
                for(int a = 0; a<clickSpanishChars.size();a++)
                {
                    clickSpanishChars.get(a).click();
                }
                Thread.sleep(2000);
                driver.findElement(By.id("languagePaletteBox")).sendKeys(Keys.chord(Keys.CONTROL, "c"));
                jse=(JavascriptExecutor)driver;
                inputStringValue = (String) jse.executeScript("return document.getElementsByClassName('hiddenpalettediv common')[0].textContent;");
                System.out.println("inputStringValue::" + inputStringValue);
                if(!inputStringValue.trim().equals("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"))
                {
                    Assert.fail("The selected character are not displayed in the text box of the French Palette .");
                }
                driver.findElement(By.id("save-language-text")).click(); // Save

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(!textInEssayBox.trim().equals("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"))
                {
                    Assert.fail("All the character are not saved in Essay text box.");
                }
                // Save French palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                // Click on Cancel
                driver.findElement(By.cssSelector("a[class='re-icon re-language redactor-btn-image']")).click();
                listOfLanguages = driver.findElements(By.xpath(".//div[@class='redactor_dropdown redactor_dropdown_box_language']/a"));
                listOfLanguages.get(2).click();
                // enter some character
                driver.findElement(By.id("languagePaletteBox")).click();
                driver.findElement(By.id("languagePaletteBox")).sendKeys("abcd1233");

                driver.findElement(By.id("cancelLanguage")).click(); // click on cancel

                textInEssayBox = driver.findElement(By.id("html-editor-non-draggable")).getText();
                if(textInEssayBox.trim().contains("abcd1233"))
                {
                    Assert.fail("On click of Cancel characters are present in essay text box.");
                }
                // cancel palette popup close
                paletteClosed = driver.findElements(By.id("language-palette-outer-wrapper")).size();
                if(paletteClosed != 0)
                {
                    Assert.fail("The language palette popup is not closed.");
                }
                driver.findElement(By.className("ls-static-practice-test-submit-button")).click(); // Submit Essay Qs
                Thread.sleep(3000);
                boolean questionReviewPage = driver.findElement(By.className("report-view-user-response-title")).isDisplayed();
                if(questionReviewPage == false)
                {
                    Assert.fail("Question review page is not displayed.");
                }
                String studentResponse = driver.findElement(By.id("html-editor-non-draggable")).getText();
                System.out.println("studentResponse::"+studentResponse);
                if(!studentResponse.trim().equals("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"))
                {
                    Assert.fail("Text box is not contain same characters enter by student.");
                }
                Thread.sleep(1000);
                driver.findElement(By.className("ls-static-practice-test-next-button")).click();
            }
            catch (Exception e)
            {
                Assert.fail("Exception in test case MPQQuestionEssay of class SpanishFrenchItalianPaletteAsStudent:", e);
            }
        }

        @Test(priority = 19,enabled = true)
        public void performanceSummaryPageWithMPQ()
        {
            try
            {
                int dataIndex = 1060;
                String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
                System.out.println("assessmentname::" + assessmentname);
                new LoginUsingLTI().ltiLogin("1060"); // Login as student
                new Navigator().NavigateTo("eTextBook"); // Navigate to e_textbook
                // Click on assessment
                WebElement html = driver.findElement(By.tagName("html"));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

                List<WebElement> assessmentList = driver.findElements(By.cssSelector("a[data-type='static_assessment']"));
                for(int i=0;i<assessmentList.size();i++)
                {
                    String assessment = assessmentList.get(i).getAttribute("title");
                    System.out.println("assessmentList.get(i).getText()::"+assessmentList.get(i).getAttribute("title"));
                    Thread.sleep(1000);
                    if(assessment.trim().equals(assessmentname))
                    {
                        Thread.sleep(1000);
                        System.out.println("assessment::" + assessment);
                        Thread.sleep(1000);
                        assessmentList.get(i).click();
                        break;
                    }
                }
                html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
                boolean performanceChart = driver.findElement(By.id("performance-chart")).isDisplayed();
                if(performanceChart==false)
                {
                    Assert.fail("Performance report page is not displayed.");
                }
                Thread.sleep(2000);
                // Click on Question card
                List<WebElement> questionCards = driver.findElements(By.className("question-card-question-no"));
                System.out.println("questionCards.size()::"+questionCards.size());
                for(int i = 0; i < questionCards.size(); i++)
                {
                    new Navigator().NavigateTo("eTextBook"); // Navigate to e_textbook
                    // Click on assessment
                    html = driver.findElement(By.tagName("html"));
                    html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                    html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                    html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                    html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

                    assessmentList = driver.findElements(By.cssSelector("a[data-type='static_assessment']"));
                    for(int a=0;a<assessmentList.size();a++)
                    {
                        String assessment = assessmentList.get(a).getAttribute("title");
                        System.out.println("assessmentList.get(a).getText()::"+assessmentList.get(a).getAttribute("title"));
                        Thread.sleep(1000);
                        if(assessment.trim().equals(assessmentname))
                        {
                            Thread.sleep(1000);
                            System.out.println("assessment::" + assessment);
                            Thread.sleep(1000);
                            assessmentList.get(a).click();
                            break;
                        }
                    }
                    html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
                    questionCards = driver.findElements(By.className("question-card-question-no"));
                    String questionNumber = questionCards.get(i).getText().replace(" ",".");
                    System.out.println("questionNumber::" + questionNumber);
                    questionCards.get(i).click();
                    //Post Discussion
                    new Discussion().addDiscussionInQuestionReviewPage();
                    new Navigator().NavigateTo("Course Stream");
                    // Click on Jump Out
                    List<WebElement> jumpOut = driver.findElements(By.cssSelector("span[class='ls-jumpout-icon-link ls-jumpout-icon']"));
                    jumpOut.get(0).click();
                    Thread.sleep(3000);
                    String questionLabel = driver.findElement(By.xpath(".//*[starts-with(@class,'question-display-label')]")).getText().replace(" ","");
                    System.out.println("questionLabel::"+questionLabel);
                    if(!questionLabel.trim().contains(questionNumber))
                    {
                        Assert.fail("Question review is not displayed.");
                    }
                }
            }
            catch (Exception e)
            {
                Assert.fail("Exception in test case performanceSummaryPageWithMPQ of class SpanishFrenchItalianPaletteAsStudent:", e);
            }
        }

        @Test(priority = 20,enabled = true)
        public void proficiencyReportPageWithMPQ()
        {
            try
            {
                new LoginUsingLTI().ltiLogin("1060"); // Login as student
                new Navigator().NavigateTo("Proficiency Report"); // Navigate to Proficiency Report
                boolean reportTitle = driver.findElement(By.id("reportTitle")).isDisplayed();
                if(reportTitle==false)
                {
                    Assert.fail("Proficiency report page is not displayed.");
                }
                // Click on Question card
                List<WebElement> questionCards = driver.findElements(By.className("question-card-question-no"));
                System.out.println("questionCards.size()::"+questionCards.size());
                for(int i = 0; i < questionCards.size(); i++)
                {
                    new Navigator().NavigateTo("Proficiency Report"); // Navigate to Proficiency Report
                    questionCards = driver.findElements(By.className("question-card-question-no"));
                    String questionNumber = questionCards.get(i).getText().replace(" ",".");
                    System.out.println("questionNumber::" + questionNumber);
                    questionCards.get(i).click();
                    //Post Discussion
                    new Discussion().addDiscussionInQuestionReviewPage();
                    new Navigator().NavigateTo("Course Stream");
                    List<WebElement> jumpOut = driver.findElements(By.cssSelector("span[class='ls-jumpout-icon-link ls-jumpout-icon']"));
                    jumpOut.get(0).click();
                    Thread.sleep(5000);
                    //  String questionLabel = driver.findElement(By.xpath(".//*[starts-with(@class,'question-display-label')]")).getText().replace(" ", "");
                    String displayLabel = driver.findElement(By.id("display-label")).getText().replace(" ","");
                    System.out.println("displayLabel::"+displayLabel);

                    if(!displayLabel.trim().contains(questionNumber))
                    {
                        Assert.fail("Question review is not displayed.");
                    }
                }
            }
            catch (Exception e)
            {
                Assert.fail("Exception in test case proficiencyReportPageWithMPQ of class SpanishFrenchItalianPaletteAsStudent:", e);
            }
        }

        @Test(priority = 21,enabled = true)
        public void questionCardInPerformanceSummaryPage()
        {
            try
            {
                int dataIndex = 1124;
                String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
                System.out.println("assessmentname::" + assessmentname);
                new Assignment().create(1124); // Create assessment
                // Ass Text entry questions
                new Assignment().addQuestions(1124, "textentry", "");
                new Assignment().addQuestions(1125, "textentry", "");
                new Assignment().addQuestions(1126,"textentry","");
                new LoginUsingLTI().ltiLogin("1124"); // Login as student
                new Navigator().NavigateTo("eTextBook"); // Navigate to e_textbook
                // Click on assessment
                WebElement html = driver.findElement(By.tagName("html"));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
                html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

                List<WebElement> assessmentList = driver.findElements(By.cssSelector("a[data-type='static_assessment']"));
                for(int i=0;i<assessmentList.size();i++)
                {
                    String assessment = assessmentList.get(i).getAttribute("title");
                    System.out.println("assessmentList.get(i).getText()::"+assessmentList.get(i).getAttribute("title"));
                    Thread.sleep(1000);
                    if(assessment.trim().equals(assessmentname))
                    {
                        Thread.sleep(1000);
                        System.out.println("assessment::" + assessment);
                        Thread.sleep(1000);
                        assessmentList.get(i).click();
                        System.out.println("i::"+i);
                        break;
                    }
                }
                html.sendKeys(Keys.chord(Keys.CONTROL, "0"));
                //Select 2nd questions
                driver.findElement(By.className("al-diag-chapter-details")).click();
                driver.findElement(By.cssSelector("tr[qindex='2']")).click();
                Thread.sleep(2000);
                // Click on each spanish character
                for(int n = 0; n < 3; n++)
                {
                    driver.findElement(By.cssSelector("div[class='show-spanish-bg spanish-bg-alignment']")).click();
                    List<WebElement> clickSpanishChars = driver.findElements(By.className("language-icon"));
                    for(int a = 0; a<clickSpanishChars.size();a++)
                    {
                        clickSpanishChars.get(a).click();
                    }
                    Thread.sleep(2000);
                    //Click on Save
                    driver.findElement(By.id("save-language-text")).click();
                    // Submit question
                    driver.findElement(By.className("ls-static-practice-test-submit-button")).click();
                    Thread.sleep(3000);
                    driver.findElement(By.className("ls-static-practice-test-next-button")).click();  // Finish
                }
                Thread.sleep(2000);
                // performance page
                boolean performanceChart = driver.findElement(By.id("performance-chart")).isDisplayed();
                if(performanceChart==false)
                {
                    Assert.fail("Performance report page is not displayed.");
                }
                Thread.sleep(2000);
                // question Card
                boolean questionCard = driver.findElement(By.className("al-performance-report-sidebar-content")).isDisplayed();
                if(questionCard == false)
                {
                    Assert.fail("Question card is not displayed in right side in filter option.");
                }
                //question text in Card
                List<WebElement> questionTextInCardList = driver.findElements(By.className("question-card-question-content"));
                String questionTextInCard = questionTextInCardList.get(0).getAttribute("title");
                if(!questionTextInCard.trim().contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤"))
                {
                    Assert.fail("Italian Characters are displaying in Card");
                }
                questionTextInCard = questionTextInCardList.get(1).getAttribute("title");
                if(!questionTextInCard.trim().contains("ÀàÂâÆæÇçÈèÉéÊêËëÎîÏïÔôŒœ"))
                {
                    Assert.fail("French Characters are displaying in Card");
                }
                questionTextInCard = questionTextInCardList.get(2).getAttribute("title");
                if(!questionTextInCard.trim().contains("áÁéÉíÍñÑóÓúÚüÜ¡¿"))
                {
                    Assert.fail("Spanish Characters are not displaying in Card");
                }
                // question Review Page
                questionTextInCardList.get(0).click();
                Thread.sleep(1000);
                boolean reviewPage = driver.findElement(By.className("your-answer-wrapper")).isDisplayed();
                if(reviewPage == false)
                {
                    Assert.fail("Question review page is not displayed.");
                }
                // text in Review Page
                String textinReviewPage = driver.findElement(By.id("question-content-label")).getText();
                if(!textinReviewPage.trim().contains("ÀàÁáÈèÉéÌìÍíÒòÓóÙùÚú€₤"))
                {
                    Assert.fail("Question review page not contains language palette characters same as author added.");
                }
            }
            catch (Exception e)
            {
                Assert.fail("Exception in test case questionCardInPerformanceSummaryPage of class SpanishFrenchItalianPaletteAsStudent:", e);
            }
        }

    }

