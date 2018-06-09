package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT23.R2313;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.LTILogin;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ScrollElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by durgapathi on 14/9/15.
 */
public class EnhancementToTheExpressionEvaluatorQuestionType extends Driver{

    @Test(priority = 1,enabled = true)
    public void expressionEvaluatorEnhancements()
    {
        try
        {
            new DirectLogin().CMSLogin(); // Login as author
            new Navigator().createQuestionPage(11);
            new Click().clickByid("qtn-expression-evaluator-img");
            Thread.sleep(1000);
            boolean questionPage = driver.findElement(By.id("answer_math_edit")).isDisplayed();
            if(questionPage==false)
            {
                Assert.fail("Not Navigated to Question Creation Page");
            }
            String syntaxSettings = driver.findElement(By.xpath(".//*[@id='syntaxSettings']/h4")).getText();
            System.out.println("syntaxSettings::"+syntaxSettings);
            if(!syntaxSettings.trim().equals("Syntax Settings"))
            {
                Assert.fail("Author is not able to see with text \"Syntax Settings\" next to \"Accept answer\".");
            }
            List<WebElement> syntaxSettingsOptions = driver.findElements(By.className("syntaxradiolabel"));
            System.out.println("syntaxSettingsGeneral::"+syntaxSettingsOptions.get(0).getText());
            if(!syntaxSettingsOptions.get(0).getText().trim().equals("General"))
            {
                Assert.fail("General Option is not present in \"Syntax Settings\"");
            }
            System.out.println("syntaxSettingsQuantity::"+syntaxSettingsOptions.get(1).getText());
            if(!syntaxSettingsOptions.get(1).getText().trim().equals("Quantity"))
            {
                Assert.fail("Quantity Option is not present in \"Syntax Settings\"");
            }
            // Select General By default
            String generalByDefault = driver.findElement(By.xpath(".//*[@key='MixedFraction']")).getAttribute("style");
            System.out.println("generalByDefault::"+generalByDefault);
            if(!generalByDefault.contains("display: none;"))
            {
                Assert.fail("By default, \"General\" is not selected.");
            }
            // Additional Settings
            boolean contentComma = driver.findElement(By.className("content-Comma")).isDisplayed();
            if(contentComma==false)
            {
                Assert.fail("There is no additional settings over the new box.");
            }
            // Separators in General settings
            String separatorsInGeneral = driver.findElement(By.id("separators")).getText();
            if(!separatorsInGeneral.trim().equals("Separators:"))
            {
                Assert.fail("There is no text called \"Separators:\"");
            }
            // Comma in General Settings
            String commaInGeneral = driver.findElement(By.xpath("(.//*[@class='content-label'])[2]")).getText();
            if(!commaInGeneral.trim().equals("Comma"))
            {
                Assert.fail("There is no text called \"Comma\"");
            }
            Thread.sleep(2000);
            //Comma drop down List
            List<WebElement> commaDorpDownOptions = driver.findElements(By.xpath(".//div[@id='content-Comma']/div//ul/li/a"));
            System.out.println("commaDorpDownOptions.0::"+commaDorpDownOptions.get(0).getAttribute("title"));
            if(!commaDorpDownOptions.get(0).getAttribute("title").trim().equals("Digit Groups"))
            {
                Assert.fail("\"Digit Groups\" is not present in the Comma drop down");
            }
            if(!commaDorpDownOptions.get(1).getAttribute("title").trim().equals("List Items"))
            {
                Assert.fail("\"List Items\" is not present in the Comma drop down");
            }
             // Space in General Settings
            String spaceInGeneral = driver.findElement(By.xpath("(.//*[@class='content-label'])[3]")).getText();
            if(!spaceInGeneral.trim().equals("Space"))
            {
                Assert.fail("There is no text called \"Space\"");
            }
            //Space drop down List
            List<WebElement> spaceDorpDownOptions = driver.findElements(By.xpath(".//div[@id='content-Space']/div//ul/li/a"));
            if(!spaceDorpDownOptions.get(0).getAttribute("title").trim().equals("Not Applicable"))
            {
                Assert.fail("\"Not Applicable\" is not present in the Space drop down List");
            }
            if(!spaceDorpDownOptions.get(1).getAttribute("title").trim().equals("Digit Groups"))
            {
                Assert.fail("\"Digit Groups\" is not present in the Space drop down List");
            }
            // List Items select by default
            String listItems = driver.findElement(By.xpath("(.//*[@id='content-Comma']/div/a)[2]")).getText();
            if(!listItems.trim().equals("List Items"))
            {
                Assert.fail("By Default, \"list items\" is not selected over first drop-down under Comma in General");
            }
            String notApplicable = driver.findElement(By.xpath("(.//*[@id='content-Space']/div/a)[2]")).getText();
            if(!notApplicable.trim().equals("Not Applicable"))
            {
                Assert.fail("By Default, \"Not Applicable\" is not selected over first drop-down under Space in General");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase expressionEvaluatorEnhancements in class EnhancementToTheExpressionEvaluatorQuestionType", e);
        }
    }

    @Test(priority = 2,enabled = true)
    public void  expressionEvaluatorQuantity()
    {
        try
        {
            new DirectLogin().CMSLogin(); // Login as author
            new Navigator().createQuestionPage(11);
            new Click().clickByid("qtn-expression-evaluator-img");
            Thread.sleep(1000);
            // Click on Quantity
            WebElement element = driver.findElement(By.className("syntaxradiolabel"));
            new ScrollElement().scrollToViewOfElement(element);
            List<WebElement> clickQuantity = driver.findElements(By.className("syntaxradiolabel"));
            clickQuantity.get(1).click();
            Thread.sleep(1000);
            String mixedFractions = driver.findElement(By.xpath("//*[@key='MixedFraction']/div")).getText();
            System.out.println("mixedFractions::"+mixedFractions);
            if(!mixedFractions.trim().equals("Mixed Fractions:"))
            {
                Assert.fail("There is no text as \"Mixed Fraction\" text with check-box next to it.");
            }
            // Click on Mixed Fractions checkbox
            new Click().clickByid("MixedFraction");
            // Un Check Mixed Fractions checkbox
            new Click().clickByid("MixedFraction");
            // Separators in General settings
            String separatorsInGeneral = driver.findElement(By.id("separators")).getText();
            if(!separatorsInGeneral.trim().equals("Separators:"))
            {
                Assert.fail("There is no text called \"Separators:\"");
            }
            // Comma in General Settings
            String commaInGeneral = driver.findElement(By.xpath("(.//*[@class='content-label'])[2]")).getText();
            if(!commaInGeneral.trim().equals("Comma"))
            {
                Assert.fail("There is no text called \"Comma\"");
            }
            Thread.sleep(2000);
            //Comma drop down List
            List<WebElement> commaDorpDownOptions = driver.findElements(By.xpath(".//div[@id='content-Comma']/div//ul/li/a"));
            System.out.println("commaDorpDownOptions.0::"+commaDorpDownOptions.get(0).getAttribute("title"));
            if(!commaDorpDownOptions.get(0).getAttribute("title").trim().equals("Digit Groups"))
            {
                Assert.fail("\"Digit Groups\" is not present in the Comma drop down");
            }
            if(!commaDorpDownOptions.get(2).getAttribute("title").trim().equals("Decimal Digits"))
            {
                Assert.fail("\"Decimal Digits\" is not present in the Comma drop down");
            }
            // Space in General Settings
            String spaceInGeneral = driver.findElement(By.xpath("(.//*[@class='content-label'])[3]")).getText();
            if(!spaceInGeneral.trim().equals("Space"))
            {
                Assert.fail("There is no text called \"Space\"");
            }
            //Space drop down List
            List<WebElement> spaceDorpDownOptions = driver.findElements(By.xpath(".//div[@id='content-Space']/div//ul/li/a"));
            if(!spaceDorpDownOptions.get(0).getAttribute("title").trim().equals("Not Applicable"))
            {
                Assert.fail("\"Not Applicable\" is not present in the Space drop down List");
            }
            if(!spaceDorpDownOptions.get(1).getAttribute("title").trim().equals("Digit Groups"))
            {
                Assert.fail("\"Digit Groups\" is not present in the Space drop down List");
            }
            // List Items select by default
            String listItems = driver.findElement(By.xpath("(.//*[@id='content-Comma']/div/a)[2]")).getText();
            if(!listItems.trim().equals("Decimal Digits"))
            {
                Assert.fail("By Default, \"Decimal Digits\" is not selected over first drop-down under Comma in General");
            }
            String notApplicable = driver.findElement(By.xpath("(.//*[@id='content-Space']/div/a)[2]")).getText();
            if(!notApplicable.trim().equals("Not Applicable"))
            {
                Assert.fail("By Default, \"Not Applicable\" is not selected over first drop-down under Space in General");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase expressionEvaluatorQuantity in class EnhancementToTheExpressionEvaluatorQuestionType", e);
        }
    }

    @Test(priority = 3,enabled = true)
    public void toggleBetweenGeneralAndQuantity()
    {
        try
        {
            new Assignment().createExpressionEvaluatorQuestion(34,"48");
            Thread.sleep(1000);
            new Click().clickByid("content-Comma");
            Thread.sleep(1000);
            new Click().clickbyxpath("(//*[@id='content-Comma']//li/a)[1]");
            Thread.sleep(1000);
            new Click().clickByid("saveQuestionDetails1");
            Thread.sleep(1000);
            String expEvaluatorChanges = driver.findElement(By.xpath("(.//*[@id='content-Comma']/div/a)[2]")).getText();
            System.out.println("expEvaluatorChanges::"+expEvaluatorChanges);
            if(!expEvaluatorChanges.trim().equals("Digit Groups"))
            {
                Assert.fail("Last saved changes are not present in Expression Evaluator Question");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase toggleBetweenGeneralAndQuantity in class EnhancementToTheExpressionEvaluatorQuestionType", e);
        }
    }

    @Test(priority = 4,enabled = true)
    public void expressionEvaluatorGeneralDefaultSettings()
    {
        try
        {
            int dataIndex=36;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            new Assignment().createExpressionEvaluatorQuestion(36,"48");
            boolean questionPosition = driver.findElement(By.id("question-reposition-icon")).isDisplayed();
            if(questionPosition==false)
            {
                Assert.fail("Question is not created with General default settings");
            }
            Thread.sleep(1000);
            new Click().clickByclassname("editor-draft-drop-down-wrapper");
            Thread.sleep(1000);
            new Click().clickBycssselector("a[title='Publish']");
            new Click().clickByid("saveQuestionDetails1");
            String parentHandle = driver.getWindowHandle();
            new Click().clickByid("preview-the-image-quiz");
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
            }
            System.out.println("newWIndowURL::" + driver.getCurrentUrl());
            boolean previewPage = driver.findElement(By.id("cms-question-preview-header-ass-name-wrapper")).isDisplayed();
            if(previewPage==false)
            {
                Assert.fail("Question preview page is not loaded.");
            }
            Thread.sleep(2000);
            new Click().clickByclassname("square-root-icon");
            Thread.sleep(2000);
            new Click().clickByclassname("wrs_focusElement");
            new Click().clickbyxpath(".//*[@title='Square root']");
            driver.findElement(By.className("wrs_focusElement")).sendKeys("48");
            new Click().clickbyxpath("(.//*[@class='ui-button-text'])[4]");
            new Click().clickByid("question-reveview-submit");
            String youGotItCorrect = driver.findElement(By.id("footer-notification-text")).getText();
            System.out.println("youGotItCorrect::" + youGotItCorrect);
            if(!youGotItCorrect.trim().equals("You got it right."))
            {
                Assert.fail("Correct Answer is not valuated correctly.");
            }
            driver.close(); // close newly opened window when done with it
            driver.switchTo().window(parentHandle);
            new LoginUsingLTI().ltiLogin("36"); //login as student
            new Navigator().NavigateTo("eTextBook");
            Thread.sleep(1000);


            WebElement html = driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

            Thread.sleep(1000);
            List<WebElement> assessmentList = driver.findElements(By.cssSelector("a[data-type='static_assessment']"));
            System.out.println("assessmentList.size()::" + assessmentList.size());
            WebElement element = driver.findElement(By.className("topic-card-left-column"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

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
            Thread.sleep(2000);
            new Click().clickByclassname("square-root-icon");
            Thread.sleep(2000);
            new Click().clickByclassname("wrs_focusElement");
            new Click().clickbyxpath(".//*[@title='Square root']");
            driver.findElement(By.className("wrs_focusElement")).sendKeys("48");
            new Click().clickbyxpath("(.//*[@class='ui-button-text'])[4]");
            new Click().clickByclassname("ls-static-practice-test-submit-button");
            String rightAnswer = driver.findElement(By.id("answer_math_edit")).getAttribute("class");
            if(!rightAnswer.contains("preview-right-answer"))
            {
                Assert.fail("Correct Answer is not valuated correctly in student side.");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase expressionEvaluatorGeneralDefaultSettings in class EnhancementToTheExpressionEvaluatorQuestionType", e);
        }
    }

    @Test(priority = 5,enabled = true)
    public void expressionEvaluatorQuantityDefaultSettings()
    {
        try
        {
            int dataIndex=40;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));

            new Assignment().createExpressionEvaluatorQuestion(40,"48");
            boolean questionPosition = driver.findElement(By.id("question-reposition-icon")).isDisplayed();
            if(questionPosition==false)
            {
                Assert.fail("Question is not created");
            }
            //Click on Quantity
            new Click().clickByid("syntaxOptionsQuantity");

            Thread.sleep(1000);
            new Click().clickByclassname("editor-draft-drop-down-wrapper");
            Thread.sleep(1000);
            new Click().clickBycssselector("a[title='Publish']");
            new Click().clickByid("saveQuestionDetails1");
            boolean mixedFractions = driver.findElement(By.id("content-MixedFraction")).isDisplayed();
            if(mixedFractions==false)
            {
                Assert.fail("Question is not created with Quantity default settings");
            }
            String parentHandle = driver.getWindowHandle();
            new Click().clickByid("preview-the-image-quiz");
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
            }
            System.out.println("newWIndowURL::" + driver.getCurrentUrl());
            boolean previewPage = driver.findElement(By.id("cms-question-preview-header-ass-name-wrapper")).isDisplayed();
            if(previewPage==false)
            {
                Assert.fail("Question preview page is not loaded.");
            }
            Thread.sleep(2000);
            new Click().clickByclassname("square-root-icon");
            Thread.sleep(2000);
            new Click().clickByclassname("wrs_focusElement");
            new Click().clickbyxpath(".//*[@title='Square root']");
            driver.findElement(By.className("wrs_focusElement")).sendKeys("48");
            new Click().clickbyxpath("(.//*[@class='ui-button-text'])[4]");
            new Click().clickByid("question-reveview-submit");
            String youGotItCorrect = driver.findElement(By.id("footer-notification-text")).getText();
            System.out.println("youGotItCorrect::" + youGotItCorrect);
            if(!youGotItCorrect.trim().equals("You got it right."))
            {
                Assert.fail("Correct Answer is not valuated correctly.");
            }
            driver.close(); // close newly opened window when done with it
            driver.switchTo().window(parentHandle);
            new LoginUsingLTI().ltiLogin("40"); //login as instructor
            new Navigator().NavigateTo("eTextBook");
            Thread.sleep(1000);


            WebElement html = driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

            Thread.sleep(1000);
            List<WebElement> assessmentList = driver.findElements(By.cssSelector("a[data-type='static_assessment']"));
            System.out.println("assessmentList.size()::" + assessmentList.size());
            WebElement element = driver.findElement(By.className("topic-card-left-column"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

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
            Thread.sleep(2000);
            new Click().clickByclassname("square-root-icon");
            Thread.sleep(2000);
            new Click().clickByclassname("wrs_focusElement");
            new Click().clickbyxpath(".//*[@title='Square root']");
            driver.findElement(By.className("wrs_focusElement")).sendKeys("48");
            new Click().clickbyxpath("(.//*[@class='ui-button-text'])[4]");
            new Click().clickByclassname("ls-static-practice-test-submit-button");
            String rightAnswer = driver.findElement(By.id("answer_math_edit")).getAttribute("class");
            if(!rightAnswer.contains("preview-right-answer"))
            {
                Assert.fail("Correct Answer is not valuated correctly in student side.");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase expressionEvaluatorQuantityDefaultSettings in class EnhancementToTheExpressionEvaluatorQuestionType", e);
        }
    }

    @Test(priority = 6,enabled = true)
    public void separatorSettingsInTheExpressionEvaluatorQuestionType()
    {
        try
        {
            int dataIndex=44;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new DirectLogin().CMSLogin(); // Login as author
            new Navigator().createQuestionPage(44);
            new Click().clickbyxpath(".//*[@id = 'practice-set-parameter-selectbox-wrapper']/div");
            new Click().clickbyxpath(".//*[@title=' Static Practice ']");
            new Click().clickByid("qtn-expression-evaluator-img");
            Thread.sleep(1000);
            boolean questionPage = driver.findElement(By.id("answer_math_edit")).isDisplayed();
            if(questionPage==false)
            {
                Assert.fail("Not Navigated to Question Creation Page");
            }
            int defaultSelect = driver.findElements(By.cssSelector("input[checked='checked']")).size();
            if(defaultSelect!=1)
            {
                Assert.fail("By default General is not selected.");
            }
            boolean generalSettings = false;
            List<WebElement> defaultSelectValue = driver.findElements(By.cssSelector("input[checked='checked']"));
            for(int i=0;i<defaultSelectValue.size();i++)
            {
                String value = defaultSelectValue.get(i).getAttribute("value");
                System.out.println("defaultSelectValue.get(i).getAttribute::"+defaultSelectValue.get(i).getAttribute("value"));
                if(value.trim().equals("Quantity"))
                {
                    generalSettings = true;
                }
            }
            System.out.println("generalSettingsafterLoop::"+generalSettings);
            if(generalSettings==true)
            {
                Assert.fail("By default General is not selected.");
            }
            // Create question digits group
            new Assignment().createExpressionEvaluatorQuestion(44,"10000");
            new Click().clickByid("content-Comma");
            Thread.sleep(1000);
            new Click().clickbyxpath("(//*[@id='content-Comma']//li/a)[1]");
            Thread.sleep(1000);
            Thread.sleep(1000);
            new Click().clickByclassname("editor-draft-drop-down-wrapper");
            Thread.sleep(1000);
            new Click().clickBycssselector("a[title='Publish']");
            new Click().clickByid("saveQuestionDetails1");
            Thread.sleep(1000);
            String expEvaluatorChanges = driver.findElement(By.xpath("(.//*[@id='content-Comma']/div/a)[2]")).getText();
            System.out.println("expEvaluatorChanges::" + expEvaluatorChanges);
            if(!expEvaluatorChanges.trim().equals("Digit Groups"))
            {
                Assert.fail("Last saved changes are not present in Expression Evaluator Question");
            }



            String parentHandle = driver.getWindowHandle();
            new Click().clickByid("preview-the-image-quiz");
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
            }
            System.out.println("newWIndowURL::" + driver.getCurrentUrl());
            boolean previewPage = driver.findElement(By.id("cms-question-preview-header-ass-name-wrapper")).isDisplayed();
            if(previewPage==false)
            {
                Assert.fail("Question preview page is not loaded.");
            }
            Thread.sleep(2000);
            new Click().clickByclassname("square-root-icon");
            Thread.sleep(2000);
            new Click().clickByclassname("wrs_focusElement");
            new Click().clickbyxpath(".//*[@title='Square root']");
            driver.findElement(By.className("wrs_focusElement")).sendKeys("10,000");
            new Click().clickbyxpath("(.//*[@class='ui-button-text'])[4]");
            new Click().clickByid("question-reveview-submit");
            String youGotItCorrect = driver.findElement(By.id("footer-notification-text")).getText();
            System.out.println("youGotItCorrect::" + youGotItCorrect);
            if(!youGotItCorrect.trim().equals("You got it right."))
            {
                Assert.fail("Correct Answer is not valuated correctly.");
            }
            driver.close(); // close newly opened window when done with it
            driver.switchTo().window(parentHandle);
            new LoginUsingLTI().ltiLogin("44"); //login as instructor
            new Navigator().NavigateTo("eTextBook");
            Thread.sleep(1000);


            WebElement html = driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

            Thread.sleep(1000);
            List<WebElement> assessmentList = driver.findElements(By.cssSelector("a[data-type='static_assessment']"));
            System.out.println("assessmentList.size()::" + assessmentList.size());
            WebElement element = driver.findElement(By.className("topic-card-left-column"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

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
            Thread.sleep(2000);
            new Click().clickByclassname("square-root-icon");
            Thread.sleep(2000);
            new Click().clickByclassname("wrs_focusElement");
            new Click().clickbyxpath(".//*[@title='Square root']");
            driver.findElement(By.className("wrs_focusElement")).sendKeys("10,000");
            new Click().clickbyxpath("(.//*[@class='ui-button-text'])[4]");
            new Click().clickByclassname("ls-static-practice-test-submit-button");
            String rightAnswer = driver.findElement(By.id("answer_math_edit")).getAttribute("class");
            if(!rightAnswer.contains("preview-right-answer"))
            {
                Assert.fail("Correct Answer is not valuated correctly in student side.");
            }

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase separatorSettingsInTheExpressionEvaluatorQuestionType in class EnhancementToTheExpressionEvaluatorQuestionType", e);
        }
    }

    @Test(priority = 7,enabled = false)
    public void separatorSettingsInTheExpressionEvaluatorQuestionWithSpace()
    {
        try
        {
            int dataIndex=50;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new DirectLogin().CMSLogin(); // Login as author
            new Navigator().createQuestionPage(50);
            new Click().clickbyxpath(".//*[@id = 'practice-set-parameter-selectbox-wrapper']/div");
            new Click().clickbyxpath(".//*[@title=' Static Practice ']");
            new Click().clickByid("qtn-expression-evaluator-img");
            Thread.sleep(1000);
            boolean questionPage = driver.findElement(By.id("answer_math_edit")).isDisplayed();
            if(questionPage==false)
            {
                Assert.fail("Not Navigated to Question Creation Page");
            }

            // Create question digits group
            new Assignment().createExpressionEvaluatorQuestion(50,"10000");
            new Click().clickByid("content-Comma");
            Thread.sleep(1000);
            new Click().clickbyxpath("(//*[@id='content-Comma']//li/a)[1]");
            Thread.sleep(1000);
            Thread.sleep(1000);
            new Click().clickByclassname("editor-draft-drop-down-wrapper");
            Thread.sleep(1000);
            new Click().clickBycssselector("a[title='Publish']");
            new Click().clickByid("saveQuestionDetails1");
            Thread.sleep(1000);
            String expEvaluatorChanges = driver.findElement(By.xpath("(.//*[@id='content-Comma']/div/a)[2]")).getText();
            System.out.println("expEvaluatorChanges::" + expEvaluatorChanges);
            if(!expEvaluatorChanges.trim().equals("Digit Groups"))
            {
                Assert.fail("Last saved changes are not present in Expression Evaluator Question");
            }

            String parentHandle = driver.getWindowHandle();
            new Click().clickByid("preview-the-image-quiz");
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
            }
            System.out.println("newWIndowURL::" + driver.getCurrentUrl());
            boolean previewPage = driver.findElement(By.id("cms-question-preview-header-ass-name-wrapper")).isDisplayed();
            if(previewPage==false)
            {
                Assert.fail("Question preview page is not loaded.");
            }
            Thread.sleep(2000);
            new Click().clickByclassname("square-root-icon");
            Thread.sleep(2000);
            new Click().clickByclassname("wrs_focusElement");
            new Click().clickbyxpath(".//*[@title='Square root']");
            driver.findElement(By.className("wrs_focusElement")).sendKeys("10 000");
            new Click().clickbyxpath("(.//*[@class='ui-button-text'])[4]");
            new Click().clickByid("question-reveview-submit");
            String youGotItCorrect = driver.findElement(By.id("footer-notification-text")).getText();
            System.out.println("youGotItCorrect::" + youGotItCorrect);
            if(!youGotItCorrect.trim().equals("You got it right."))
            {
                Assert.fail("Correct Answer is not valuated correctly.");
            }
            driver.close(); // close newly opened window when done with it
            driver.switchTo().window(parentHandle);
            new LoginUsingLTI().ltiLogin("50"); //login as instructor
            new Navigator().NavigateTo("eTextBook");
            Thread.sleep(1000);


            WebElement html = driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

            Thread.sleep(1000);
            List<WebElement> assessmentList = driver.findElements(By.cssSelector("a[data-type='static_assessment']"));
            System.out.println("assessmentList.size()::" + assessmentList.size());
            WebElement element = driver.findElement(By.className("topic-card-left-column"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

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
            Thread.sleep(2000);
            new Click().clickByclassname("square-root-icon");
            Thread.sleep(2000);
            new Click().clickByclassname("wrs_focusElement");
            new Click().clickbyxpath(".//*[@title='Square root']");
            driver.findElement(By.className("wrs_focusElement")).sendKeys("10 000");
            new Click().clickbyxpath("(.//*[@class='ui-button-text'])[4]");
            new Click().clickByclassname("ls-static-practice-test-submit-button");
            String rightAnswer = driver.findElement(By.id("answer_math_edit")).getAttribute("class");
            if(!rightAnswer.contains("preview-right-answer"))
            {
                Assert.fail("Correct Answer is not valuated correctly in student side.");
            }

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase separatorSettingsInTheExpressionEvaluatorQuestionWithSpace in class EnhancementToTheExpressionEvaluatorQuestionType", e);
        }
    }

    @Test(priority = 8,enabled = true)
    public void mixedFractionSettingsInTheExpressionEvaluatorQuestion()
    {
        try
        {
            int dataIndex=55;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new Assignment().createExpressionEvaluatorQuestionValue(55,"5/2");
            Thread.sleep(1000);
            new Click().clickByid("syntaxOptionsQuantity");
            new Click().clickByid("MixedFraction");
            new Click().clickByclassname("editor-draft-drop-down-wrapper");
            Thread.sleep(1000);
            new Click().clickBycssselector("a[title='Publish']");
            new Click().clickByid("saveQuestionDetails1");
            Thread.sleep(1000);


            String parentHandle = driver.getWindowHandle();
            new Click().clickByid("preview-the-image-quiz");
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
            }
            System.out.println("newWIndowURL::" + driver.getCurrentUrl());
            boolean previewPage = driver.findElement(By.id("cms-question-preview-header-ass-name-wrapper")).isDisplayed();
            if(previewPage==false)
            {
                Assert.fail("Question preview page is not loaded.");
            }
            Thread.sleep(2000);
            new Click().clickByclassname("square-root-icon");
            Thread.sleep(2000);
            new Click().clickByclassname("wrs_focusElement");
            driver.findElement(By.className("wrs_focusElement")).sendKeys("2 1/2");
            new Click().clickbyxpath("(.//*[@class='ui-button-text'])[4]");
            new Click().clickByid("question-reveview-submit");
            String youGotItCorrect = driver.findElement(By.id("footer-notification-text")).getText();
            System.out.println("youGotItCorrect::" + youGotItCorrect);
            if(!youGotItCorrect.trim().equals("You got it right."))
            {
                Assert.fail("Correct Answer is not valuated correctly.");
            }
            driver.close(); // close newly opened window when done with it
            driver.switchTo().window(parentHandle);

            // Student
            new LoginUsingLTI().ltiLogin("55"); //login as instructor
            new Navigator().NavigateTo("eTextBook");
            Thread.sleep(1000);


            WebElement html = driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

            Thread.sleep(1000);
            List<WebElement> assessmentList = driver.findElements(By.cssSelector("a[data-type='static_assessment']"));
            System.out.println("assessmentList.size()::" + assessmentList.size());

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
            Thread.sleep(2000);
            new Click().clickByclassname("square-root-icon");
            Thread.sleep(2000);
            new Click().clickByclassname("wrs_focusElement");
            driver.findElement(By.className("wrs_focusElement")).sendKeys("2 1/2");
            new Click().clickbyxpath("(.//*[@class='ui-button-text'])[4]");
            new Click().clickByclassname("ls-static-practice-test-submit-button");
            String rightAnswer = driver.findElement(By.id("answer_math_edit")).getAttribute("class");
            if(!rightAnswer.contains("preview-right-answer"))
            {
                Assert.fail("Correct Answer is not valuated correctly in student side.");
            }

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase mixedFractionSettingsInTheExpressionEvaluatorQuestion in class EnhancementToTheExpressionEvaluatorQuestionType", e);
        }
    }

    @Test(priority = 9,enabled = true)
    public void separatorSettingsInTheExpressionEvaluatorQuantity()
    {
        try
        {
            int dataIndex=61;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new DirectLogin().CMSLogin(); // Login as author
            new Navigator().createQuestionPage(61);
            new Click().clickbyxpath(".//*[@id = 'practice-set-parameter-selectbox-wrapper']/div");
            new Click().clickbyxpath(".//*[@title=' Static Practice ']");
            new Click().clickByid("qtn-expression-evaluator-img");
            Thread.sleep(1000);
            boolean questionPage = driver.findElement(By.id("answer_math_edit")).isDisplayed();
            if(questionPage==false)
            {
                Assert.fail("Not Navigated to Question Creation Page");
            }

            // Create question digits group
            new Assignment().createExpressionEvaluatorQuestion(61, "10000");
            new Click().clickByid("syntaxOptionsQuantity");
            Thread.sleep(1000);
            new Click().clickbyxpath("(//*[@id='content-Comma']//li/a)[1]");
            Thread.sleep(1000);
            new Click().clickByclassname("editor-draft-drop-down-wrapper");
            Thread.sleep(1000);
            new Click().clickBycssselector("a[title='Publish']");
            new Click().clickByid("saveQuestionDetails1");
            Thread.sleep(1000);
            String expEvaluatorChanges = driver.findElement(By.xpath("(.//*[@id='content-Comma']/div/a)[2]")).getText();
            System.out.println("expEvaluatorChanges::" + expEvaluatorChanges);
            if(!expEvaluatorChanges.trim().equals("Digit Groups"))
            {
                Assert.fail("Last saved changes are not present in Expression Evaluator Question");
            }

            String parentHandle = driver.getWindowHandle();
            new Click().clickByid("preview-the-image-quiz");
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
            }
            System.out.println("newWIndowURL::" + driver.getCurrentUrl());
            boolean previewPage = driver.findElement(By.id("cms-question-preview-header-ass-name-wrapper")).isDisplayed();
            if(previewPage==false)
            {
                Assert.fail("Question preview page is not loaded.");
            }
            Thread.sleep(2000);
            new Click().clickByclassname("square-root-icon");
            Thread.sleep(2000);
            new Click().clickByclassname("wrs_focusElement");
            new Click().clickbyxpath(".//*[@title='Square root']");
            driver.findElement(By.className("wrs_focusElement")).sendKeys("10,000");
            new Click().clickbyxpath("(.//*[@class='ui-button-text'])[4]");
            new Click().clickByid("question-reveview-submit");
            String youGotItCorrect = driver.findElement(By.id("footer-notification-text")).getText();
            System.out.println("youGotItCorrect::" + youGotItCorrect);
            if(!youGotItCorrect.trim().equals("You got it right."))
            {
                Assert.fail("Correct Answer is not valuated correctly.");
            }
            driver.close(); // close newly opened window when done with it
            driver.switchTo().window(parentHandle);
            new LoginUsingLTI().ltiLogin("61"); //login as instructor
            new Navigator().NavigateTo("eTextBook");
            Thread.sleep(1000);


            WebElement html = driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

            Thread.sleep(1000);
            List<WebElement> assessmentList = driver.findElements(By.cssSelector("a[data-type='static_assessment']"));
            System.out.println("assessmentList.size()::" + assessmentList.size());
            WebElement element = driver.findElement(By.className("topic-card-left-column"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

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
            Thread.sleep(2000);
            new Click().clickByclassname("square-root-icon");
            Thread.sleep(2000);
            new Click().clickByclassname("wrs_focusElement");
            new Click().clickbyxpath(".//*[@title='Square root']");
            driver.findElement(By.className("wrs_focusElement")).sendKeys("10,000");
            new Click().clickbyxpath("(.//*[@class='ui-button-text'])[4]");
            new Click().clickByclassname("ls-static-practice-test-submit-button");
            String rightAnswer = driver.findElement(By.id("answer_math_edit")).getAttribute("class");
            if(!rightAnswer.contains("preview-right-answer"))
            {
                Assert.fail("Correct Answer is not valuated correctly in student side.");
            }

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase separatorSettingsInTheExpressionEvaluatorQuantity in class EnhancementToTheExpressionEvaluatorQuestionType", e);
        }
    }

    @Test(priority = 10,enabled = false)
    public void quantitySettingsInTheExpressionEvaluatorQuestionWithSpace()
    {
        try
        {
            int dataIndex=67;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new DirectLogin().CMSLogin(); // Login as author
            new Navigator().createQuestionPage(67);
            new Click().clickbyxpath(".//*[@id = 'practice-set-parameter-selectbox-wrapper']/div");
            new Click().clickbyxpath(".//*[@title=' Static Practice ']");
            new Click().clickByid("qtn-expression-evaluator-img");
            Thread.sleep(1000);
            boolean questionPage = driver.findElement(By.id("answer_math_edit")).isDisplayed();
            if(questionPage==false)
            {
                Assert.fail("Not Navigated to Question Creation Page");
            }

            // Create question digits group
            new Assignment().createExpressionEvaluatorQuestion(67, "10000");
            new Click().clickByid("syntaxOptionsQuantity");
            Thread.sleep(1000);
            new Click().clickbyxpath("(//*[@id='content-Comma']//li/a)[1]");
            Thread.sleep(1000);
            new Click().clickByclassname("editor-draft-drop-down-wrapper");
            Thread.sleep(1000);
            new Click().clickBycssselector("a[title='Publish']");
            new Click().clickByid("saveQuestionDetails1");
            Thread.sleep(1000);
            String expEvaluatorChanges = driver.findElement(By.xpath("(.//*[@id='content-Comma']/div/a)[2]")).getText();
            System.out.println("expEvaluatorChanges::" + expEvaluatorChanges);
            if(!expEvaluatorChanges.trim().equals("Digit Groups"))
            {
                Assert.fail("Digit Groups is not selected.");
            }

            String parentHandle = driver.getWindowHandle();
            new Click().clickByid("preview-the-image-quiz");
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
            }
            System.out.println("newWIndowURL::" + driver.getCurrentUrl());
            boolean previewPage = driver.findElement(By.id("cms-question-preview-header-ass-name-wrapper")).isDisplayed();
            if(previewPage==false)
            {
                Assert.fail("Question preview page is not loaded.");
            }
            Thread.sleep(2000);
            new Click().clickByclassname("square-root-icon");
            Thread.sleep(2000);
            new Click().clickByclassname("wrs_focusElement");
            new Click().clickbyxpath(".//*[@title='Square root']");
            driver.findElement(By.className("wrs_focusElement")).sendKeys("10 000");
            new Click().clickbyxpath("(.//*[@class='ui-button-text'])[4]");
            new Click().clickByid("question-reveview-submit");
            String youGotItCorrect = driver.findElement(By.id("footer-notification-text")).getText();
            System.out.println("youGotItCorrect::" + youGotItCorrect);
            if(!youGotItCorrect.trim().equals("You got it right."))
            {
                Assert.fail("Correct Answer is not valuated correctly.");
            }
            driver.close(); // close newly opened window when done with it
            driver.switchTo().window(parentHandle);
            new LoginUsingLTI().ltiLogin("67"); //login as instructor
            new Navigator().NavigateTo("eTextBook");
            Thread.sleep(1000);


            WebElement html = driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

            Thread.sleep(1000);
            List<WebElement> assessmentList = driver.findElements(By.cssSelector("a[data-type='static_assessment']"));
            System.out.println("assessmentList.size()::" + assessmentList.size());
            WebElement element = driver.findElement(By.className("topic-card-left-column"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

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
            Thread.sleep(2000);
            new Click().clickByclassname("square-root-icon");
            Thread.sleep(2000);
            new Click().clickByclassname("wrs_focusElement");
            new Click().clickbyxpath(".//*[@title='Square root']");
            driver.findElement(By.className("wrs_focusElement")).sendKeys("10 000");
            new Click().clickbyxpath("(.//*[@class='ui-button-text'])[4]");
            new Click().clickByclassname("ls-static-practice-test-submit-button");
            String rightAnswer = driver.findElement(By.id("answer_math_edit")).getAttribute("class");
            if(!rightAnswer.contains("preview-right-answer"))
            {
                Assert.fail("Correct Answer is not valuated correctly in student side.");
            }

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase separatorSettingsInTheExpressionEvaluatorQuestionWithSpace in class EnhancementToTheExpressionEvaluatorQuestionType", e);
        }
    }

    @Test(priority = 11,enabled = true)
    public void matrixValidation()
    {
        try
        {
            new Assignment().createExpressionEvaluatorQuestionValue(74,"[1 2.0 2x]");
            String parentHandle = driver.getWindowHandle();
            new Click().clickByid("preview-the-image-quiz");
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
            }
            System.out.println("newWIndowURL::" + driver.getCurrentUrl());
            boolean previewPage = driver.findElement(By.id("cms-question-preview-header-ass-name-wrapper")).isDisplayed();
            if(previewPage==false)
            {
                Assert.fail("Question preview page is not loaded.");
            }
            Thread.sleep(2000);
            new Click().clickByclassname("square-root-icon");
            Thread.sleep(2000);
            new Click().clickByclassname("wrs_focusElement");
            driver.findElement(By.className("wrs_focusElement")).sendKeys("[1 2.0 2x]");
            new Click().clickbyxpath("(.//*[@class='ui-button-text'])[4]");
            new Click().clickByid("question-reveview-submit");
            String youGotItCorrect = driver.findElement(By.id("footer-notification-text")).getText();
            System.out.println("youGotItCorrect::" + youGotItCorrect);
            if(!youGotItCorrect.trim().equals("You got it right."))
            {
                Assert.fail("Correct Answer is not valuated correctly.");
            }
            driver.close(); // close newly opened window when done with it
            driver.switchTo().window(parentHandle);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase matrixValidation in class EnhancementToTheExpressionEvaluatorQuestionType", e);
        }
    }

    @Test(priority = 12,enabled = true)
    public void integralValidation()
    {
        try
        {
            new Assignment().createExpressionEvaluatorQuestionValue(76, "123");
            new Click().clickByclassname("square-root-icon");
            Thread.sleep(1000);

            new Click().clickBycssselector("button[title='Calculus tab']");
            new Click().clickBycssselector("button[title='Integral']");
            driver.findElement(By.className("wrs_focusElement")).sendKeys("kdx=kx+C");
            new Click().clickbyxpath("(.//*[@class='ui-button-text'])[4]");
            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget accept_answer maple_symc_not_accept_answer']");

            new Click().clickByid("saveQuestionDetails1");

            String parentHandle = driver.getWindowHandle();
            new Click().clickByid("preview-the-image-quiz");
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
            }
            System.out.println("newWIndowURL::" + driver.getCurrentUrl());
            boolean previewPage = driver.findElement(By.id("cms-question-preview-header-ass-name-wrapper")).isDisplayed();
            if(previewPage==false)
            {
                Assert.fail("Question preview page is not loaded.");
            }
            Thread.sleep(2000);
            new Click().clickByclassname("square-root-icon");
            Thread.sleep(2000);
            driver.findElement(By.className("wrs_focusElement")).sendKeys("123");
            new Click().clickBycssselector("button[title='Calculus tab']");
            new Click().clickBycssselector("button[title='Integral']");
            driver.findElement(By.className("wrs_focusElement")).sendKeys("kdx=kx+C");
            new Click().clickbyxpath("(.//*[@class='ui-button-text'])[4]");
            new Click().clickByid("question-reveview-submit");
            String youGotItCorrect = driver.findElement(By.id("footer-notification-text")).getText();
            System.out.println("youGotItCorrect::" + youGotItCorrect);
            if(!youGotItCorrect.trim().equals("You got it right."))
            {
                Assert.fail("Correct Answer is not valuated correctly.");
            }
            driver.close(); // close newly opened window when done with it
            driver.switchTo().window(parentHandle);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase integralValidation in class EnhancementToTheExpressionEvaluatorQuestionType", e);
        }
    }

    @Test(priority = 13,enabled = false)
    public void  unitsValidation()
    {
        try
        {
            new Assignment().createExpressionEvaluatorQuestionValue(78,"100cm");
            String parentHandle = driver.getWindowHandle();
            new Click().clickByid("preview-the-image-quiz");
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
            }
            System.out.println("newWIndowURL::" + driver.getCurrentUrl());
            boolean previewPage = driver.findElement(By.id("cms-question-preview-header-ass-name-wrapper")).isDisplayed();
            if(previewPage==false)
            {
                Assert.fail("Question preview page is not loaded.");
            }
            Thread.sleep(2000);
            new Click().clickByclassname("square-root-icon");
            Thread.sleep(2000);
            new Click().clickByclassname("wrs_focusElement");
            driver.findElement(By.className("wrs_focusElement")).sendKeys("1mt");
            new Click().clickbyxpath("(.//*[@class='ui-button-text'])[4]");
            new Click().clickByid("question-reveview-submit");
            String youGotItCorrect = driver.findElement(By.id("footer-notification-text")).getText();
            System.out.println("youGotItCorrect::" + youGotItCorrect);
            if(!youGotItCorrect.trim().equals("You got it right."))
            {
                Assert.fail("Correct Answer is not valuated correctly.");
            }
            driver.close(); // close newly opened window when done with it
            driver.switchTo().window(parentHandle);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase unitsValidation in class EnhancementToTheExpressionEvaluatorQuestionType", e);
        }
    }

    @Test(priority = 14,enabled = true)
    public void expressionEvalutorUnderMPQ()
    {
        try
        {
           int dataIndex = 80;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            String questionset = ReadTestData.readDataByTagName("", "questionset", Integer.toString(dataIndex));
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            System.out.println("dataIndex::" + dataIndex);

            System.out.println("assessmentname::" + assessmentname);
            System.out.println("questionset::" + questionset);
            System.out.println("questiontext::" + questiontext);

            new DirectLogin().CMSLogin(); // Login as author
            new Navigator().createQuestionPage(dataIndex);
            new Click().clickByid("assessmentName");
            driver.findElement(By.id("assessmentName")).sendKeys(assessmentname);
            new Click().clickByid("questionSetName");
            driver.findElement(By.id("questionSetName")).sendKeys(questionset);
            new Click().clickbyxpath(".//*[@id = 'practice-set-parameter-selectbox-wrapper']/div");
            new Click().clickbyxpath(".//*[@title=' Static Practice ']");
            new Click().clickByid("qtn-multi-part");
            Thread.sleep(2000);
            new Click().clickByid("question-mp-raw-content-0");
            driver.findElement(By.id("question-mp-raw-content-0")).sendKeys(questiontext);
            new Click().clickByid("saveQuestionDetails1");
            new Click().clickByclassname("cms-multipart-add-question-part");
            new Click().clickByid("qtn-math-symbolic-notation-img");
            new Click().clickByid("mpq-question-part-input-label");
            driver.findElement(By.id("mpq-question-part-input-label")).sendKeys("1");
            new Click().clickByid("question-raw-content");
            new Click().clickByid("question-raw-content");
            driver.findElement(By.id("question-raw-content")).sendKeys(questiontext);
            Thread.sleep(2000);
            new Click().clickByclassname("square-root-icon");
            Thread.sleep(2000);
            new Click().clickByclassname("wrs_focusElement");
            driver.findElement(By.className("wrs_focusElement")).sendKeys("48");
            new Click().clickbyxpath("(.//*[@class='ui-button-text'])[4]");
            new Click().clickBycssselector("span[class='done-button btn sty-green tab_event_widget accept_answer maple_symc_not_accept_answer']");

            new Click().clickByid("saveQuestionDetails1");
            Thread.sleep(1000);
            boolean questionSave = driver.findElement(By.className("question-container-header-third")).isDisplayed();
            if(questionSave==false)
            {
                Assert.fail("Not able to Create a Expression Evalutor Question type under MPQ .");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase expressionEvalutorUnderMPQ in class EnhancementToTheExpressionEvaluatorQuestionType", e);
        }
    }

    @Test(priority = 15,enabled = true)
    public void  searchOrBrowseExpressionEvaluator()
    {
        try
        {
            int dataIndex=81;
            String questiontext = ReadTestData.readDataByTagName("", "questiontext", Integer.toString(dataIndex));

            new Assignment().createExpressionEvaluatorQuestion(81, "40");
            Thread.sleep(1000);
            new Click().clickByid("content-search-icon");
            new Click().clickByid("content-search-field");
            driver.findElement(By.id("content-search-field")).sendKeys(questiontext);
            new Click().clickByid("search-question-contents-icon");
            Thread.sleep(1000);

            String parentHandle = driver.getWindowHandle();
            new Click().clickByclassname("preview-question-content");
            Thread.sleep(1000);
            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
            }
            System.out.println("newWIndowURL::" + driver.getCurrentUrl());
            boolean previewPage = driver.findElement(By.id("cms-question-preview-header-ass-name-wrapper")).isDisplayed();
            if(previewPage==false)
            {
                Assert.fail("Question preview page is not loaded.");
            }
            Thread.sleep(2000);
            new Click().clickByclassname("square-root-icon");
            Thread.sleep(2000);
            new Click().clickByclassname("wrs_focusElement");
            new Click().clickbyxpath(".//*[@title='Square root']");
            driver.findElement(By.className("wrs_focusElement")).sendKeys("40");
            new Click().clickbyxpath("(.//*[@class='ui-button-text'])[4]");
            new Click().clickByid("question-reveview-submit");
            String youGotItCorrect = driver.findElement(By.id("footer-notification-text")).getText();
            System.out.println("youGotItCorrect::" + youGotItCorrect);
            if(!youGotItCorrect.trim().equals("You got it right."))
            {
                Assert.fail("Correct Answer is not valuated correctly.");
            }
            driver.close(); // close newly opened window when done with it
            driver.switchTo().window(parentHandle);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase searchOrBrowseExpressionEvaluator in class EnhancementToTheExpressionEvaluatorQuestionType", e);
        }
    }

    @Test(priority = 16,enabled = true)
    public void instructorSide()
    {
        try
        {
            int dataIndex = 81;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new Assignment().createExpressionEvaluatorQuestion(81,"40");
            Thread.sleep(1000);
            new Click().clickByclassname("editor-draft-drop-down-wrapper");
            Thread.sleep(1000);
            new Click().clickBycssselector("a[title='Publish']");
            new Click().clickByid("saveQuestionDetails1");
            new LoginUsingLTI().ltiLogin("81");
            new Navigator().NavigateTo("eTextBook");
            WebElement html = driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

            Thread.sleep(1000);
            List<WebElement> assessmentList = driver.findElements(By.cssSelector("a[data-type='static_assessment']"));
            System.out.println("assessmentList.size()::" + assessmentList.size());
            WebElement element = driver.findElement(By.className("topic-card-left-column"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
            String parentHandle = driver.getWindowHandle();

            for(int i=0;i<assessmentList.size();i++)
            {
                String assessment = assessmentList.get(i).getAttribute("title");
                System.out.println("assessmentList.get(i).getText()::"+assessmentList.get(i).getAttribute("title"));
                Thread.sleep(1000);
                if(assessment.trim().equals(assessmentname))
                {
                    Thread.sleep(1000);
                    System.out.println("assessment::" + assessment);
                    int a = i+i;
                    System.out.println("i::" + i);
                    System.out.println("a::" + a);
                    Thread.sleep(1000);
                    List<WebElement> innerArrow = driver.findElements(By.className("ls-inner-arw"));
                    innerArrow.get(a).click();
                    Thread.sleep(1000);
                    List<WebElement> tryIt = driver.findElements(By.className("toc-try-it"));
                    tryIt.get(i).click();
                    break;
                }
            }

            html.sendKeys(Keys.chord(Keys.CONTROL, "0"));

            for (String winHandle : driver.getWindowHandles()) {
                driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
            }
            System.out.println("newWIndowURL::" + driver.getCurrentUrl());
            boolean previewPage = driver.findElement(By.id("cms-question-preview-header-ass-name-wrapper")).isDisplayed();
            if(previewPage==false)
            {
                Assert.fail("Question preview page is not loaded.");
            }
            Thread.sleep(2000);
            new Click().clickByclassname("square-root-icon");
            Thread.sleep(2000);
            new Click().clickByclassname("wrs_focusElement");
            new Click().clickbyxpath(".//*[@title='Square root']");
            driver.findElement(By.className("wrs_focusElement")).sendKeys("40");
            new Click().clickbyxpath("(.//*[@class='ui-button-text'])[4]");
            new Click().clickByid("question-reveview-submit");
            String youGotItCorrect = driver.findElement(By.id("footer-notification-text")).getText();
            System.out.println("youGotItCorrect::" + youGotItCorrect);
            if(!youGotItCorrect.trim().equals("You got it right."))
            {
                Assert.fail("Correct Answer is not valuated correctly.");
            }
            driver.close(); // close newly opened window when done with it
            driver.switchTo().window(parentHandle);

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase instructorSide in class EnhancementToTheExpressionEvaluatorQuestionType", e);
        }
    }

    @Test(priority = 17,enabled = true)
    public void studentSide()
    {
        try
        {
            int dataIndex = 81;
            String assessmentname = ReadTestData.readDataByTagName("", "assessmentname", Integer.toString(dataIndex));
            new LoginUsingLTI().ltiLogin("83"); //login as student
            new Navigator().NavigateTo("eTextBook");
            Thread.sleep(1000);


            WebElement html = driver.findElement(By.tagName("html"));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
            html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));

            Thread.sleep(1000);
            List<WebElement> assessmentList = driver.findElements(By.cssSelector("a[data-type='static_assessment']"));
            System.out.println("assessmentList.size()::" + assessmentList.size());
            WebElement element = driver.findElement(By.className("topic-card-left-column"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

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
            Thread.sleep(2000);
            new Click().clickByclassname("square-root-icon");
            Thread.sleep(2000);
            new Click().clickByclassname("wrs_focusElement");
            new Click().clickbyxpath(".//*[@title='Square root']");
            driver.findElement(By.className("wrs_focusElement")).sendKeys("40");
            new Click().clickbyxpath("(.//*[@class='ui-button-text'])[4]");
            new Click().clickByclassname("ls-static-practice-test-submit-button");
            String rightAnswer = driver.findElement(By.id("answer_math_edit")).getAttribute("class");
            if(!rightAnswer.contains("preview-right-answer"))
            {
                Assert.fail("Correct Answer is not valuated correctly in student side.");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase studentSide in class EnhancementToTheExpressionEvaluatorQuestionType", e);
        }
    }
}
