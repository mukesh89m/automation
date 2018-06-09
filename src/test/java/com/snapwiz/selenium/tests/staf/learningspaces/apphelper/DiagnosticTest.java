package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.util.List;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Assignments.Assignments;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.LessonPage;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;


public class DiagnosticTest extends Driver {

    public void startTest(int confidenceLevelIndex)
    {
        try
        {

            WebDriver driver=Driver.getWebDriver();
            LessonPage lessonPage=PageFactory.initElements(driver,LessonPage.class);
            new Click().clickBycssselector("div[data-id='orion-adaptive-practice-tab']");
            List<WebElement> conf =  driver.findElements(By.id(Integer.toString(confidenceLevelIndex)));
            for(WebElement c : conf) {
                if(c.isDisplayed()) {
                    WebDriverUtil.clickOnElementUsingJavascript(c);
                    break;
                }
            }
            List<WebElement> startButtons =  driver.findElements(By.xpath("//input[@class='practice-assigned ls-assessment-continue-btn orion-adaptive-practice-tab-btn']"));
            for(WebElement startButton : startButtons) {
                if(startButton.isDisplayed()) {
                    WebDriverUtil.clickOnElementUsingJavascript(startButton);
                    break;
                }
            }
            // ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@class='ls-assessment-continue']")));
            //WebDriverUtil.clickOnElementUsingJavascript(lessonPage.continue_roboNotification);//Click on continue

        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper startTest in class DiagnosticTest",e);
        }
    }

    public void startDiagnosticTestForAssignedPractice(int confidenceLevelIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            new Click().clickBycssselector("div[data-id='orion-adaptive-practice-tab']");
            List<WebElement> conf =  driver.findElements(By.id(Integer.toString(confidenceLevelIndex)));
            for(WebElement c : conf) {
                if(c.isDisplayed()) {
                    WebDriverUtil.clickOnElementUsingJavascript(c);
                    //c.click();
                    break;
                }
            }
            List<WebElement> startButtons =  driver.findElements(By.xpath("//input[@class='practice-assigned ls-assessment-continue-btn orion-adaptive-practice-tab-btn']"));
            for(WebElement startButton : startButtons) {
                if(startButton.isDisplayed()) {
                    startButton.click();
                    break;
                }
            }
            //((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("continue-diagnostic-test")));

        } catch(Exception e)
        {
            Assert.fail("Exception in app helper startTest in class DiagnosticTest",e);
        }
    }

    public void startTest(int confidenceLevelIndex, int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", ""+dataIndex);//fetch assignment name from textdata
            WebDriverWait wait  = new WebDriverWait(driver,60);
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("selected-chapter-name"),"Ch 1:"));
            if(chapterName!=null){
                List<WebElement> chapterHeadingList = driver.findElements(By.cssSelector("div[class='chapter-heading']"));
                for(int a=0;a<chapterHeadingList.size();a++){
                    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", chapterHeadingList.get(a));
                    if(chapterHeadingList.get(a).getText().contains(chapterName)){
                        List<WebElement> duplicateChaptersElementsList = driver.findElements(By.xpath(".//*[@title='" + chapterName + "']"));
                        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", duplicateChaptersElementsList.get(duplicateChaptersElementsList.size() - 1));
                        wait  = new WebDriverWait(driver,60);
                        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("selected-chapter-name"),chapterName));
                        break;
                    }
                }
            }
            new Click().clickBycssselector("div[data-id='orion-adaptive-practice-tab']");
            List<WebElement> conf =  driver.findElements(By.id(Integer.toString(confidenceLevelIndex)));
            for(WebElement c : conf) {
                if(c.isDisplayed()) {
                    c.click();
                    break;
                }
            }
            List<WebElement> startButtons =  driver.findElements(By.xpath("//input[@class='practice-assigned ls-assessment-continue-btn orion-adaptive-practice-tab-btn']"));
            for(WebElement startButton : startButtons) {
                if(startButton.isDisplayed()) {
                    startButton.click();
                    break;
                }
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper startTest in class DiagnosticTest",e);
        }
    }

    public void startTestByName(String chapterName,int confidenceLevelIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            new Click().clickbyxpath("//h3[contains(text(),'"+chapterName+"')]");
            new Click().clickBycssselector("div[data-id='orion-adaptive-practice-tab']");
            List<WebElement> conf =  driver.findElements(By.id(Integer.toString(confidenceLevelIndex)));
            for(WebElement c : conf) {
                if(c.isDisplayed()) {
                    c.click();
                    break;
                }
            }

            List<WebElement> startButtons =  driver.findElements(By.xpath("//input[@class='practice-assigned ls-assessment-continue-btn orion-adaptive-practice-tab-btn']"));
            for(WebElement startButton : startButtons) {
                if(startButton.isDisplayed()) {
                    startButton.click();
                    break;
                }
            }

            //((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//input[@class='ls-assessment-continue-btn orion-adaptive-practice-tab-btn']")));
        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper startTest in class DiagnosticTest",e);
        }
    }
    public void startTestForInstructor()
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            new Click().clickBycssselector("div[data-id='orion-adaptive-practice-tab']");
            driver.findElement(By.cssSelector("input[id='preview-test-button']")).click();//click on 1st chapter's Diagnostic Test
            Thread.sleep(3000);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper startTestForInstructor in class DiagnosticTest",e);
        }
    }


    public void startTestNonAdaptive(int confidenceLevelIndex, String testName)                    // LS NonAdaptive Diagnostic
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String card1topic1 = ReadTestData.readDataByTagName("tocdata",testName, "1");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("a[title='"+card1topic1+"']")));
            WebElement confidencelevel =     (new WebDriverWait(driver, 2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.id(Integer.toString(confidenceLevelIndex))));
            if(confidencelevel != null)
            {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id(Integer.toString(confidenceLevelIndex))));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("input.ls-assessment-continue-btn")));
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper startTest in class DiagnosticTest",e);
        }
    }

    public void attemptAllCorrect(int confidencelevel,boolean useHints,boolean useSolutionText)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String confidence=Integer.toString(confidencelevel);
            new UIElement().waitAndFindElement(By.className("question-label"));
            boolean timer=new UIElement().waitAndFindElementWithoutAssert(By.cssSelector("span[id='timer-label']"));
            while(timer==true)
            {
                if(useHints == true) {
                    driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();
                }
                if(driver.findElements(By.className("preview-single-selection-answer-choices")).size() > 0)
                    new AttemptDiagnosticQuestion().attemptMultipleChoice("correct", useHints, useSolutionText, confidence, "");

                else if(driver.findElements(By.className("preview-multiple-select-answer-choice")).size() >0) {
                    System.out.println("before attempting");
                    new AttemptDiagnosticQuestion().attemptMultipleSelection("correct", useHints, useSolutionText, confidence, "");
                    System.out.println("after attempting");
                }

                else if(driver.findElements(By.className("true-false-answer-choices")).size() > 0)
                    new AttemptDiagnosticQuestion().attemptTrueFalse("correct", useHints, useSolutionText, confidence, "");

                else if(driver.findElements(By.cssSelector("div[class='text-entry-input-wrapper visible_redactor_input_wrapper']")).size()>0)
                    new AttemptDiagnosticQuestion().attemptTextEntry("correct", useHints, useSolutionText, confidence, "");

                else if(driver.findElements(By.cssSelector("div[class='text-entry-dropdown-wrapper visible_redactor_input_wrapper']")).size()>0)
                    new AttemptDiagnosticQuestion().attemptTextDropDown("correct", useHints, useSolutionText, confidence, "");

                else if(driver.findElements(By.className("numeric_entry_student_title")).size()>0)
                    new AttemptDiagnosticQuestion().attemptNumericEntryWithUnits("correct", useHints, useSolutionText, confidence, "");


                else if(driver.findElements(By.cssSelector("div[class='symb-notation-math-edit-icon-preview']")).size()>0)
                    new AttemptDiagnosticQuestion().attemptExpressionEvaluator("correct", useHints, useSolutionText, confidence, "");

                else if(driver.findElements(By.cssSelector("div[class='html-editor-non-draggable']")).size()>0)
                    new AttemptDiagnosticQuestion().attemptEssayType("correct", useHints, useSolutionText, confidence, "");
                new WebDriverWait(driver,300).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("al-diag-test-submit-button")));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("al-diag-test-submit-button")));

                if(driver.findElements(By.xpath("//input[@value='Finish']")).size()>0) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("al-diag-test-submit-button")));
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='ls-summary-report-link']")));
                }

                Thread.sleep(500);
                if(driver.findElements(By.className("al-notification-message-body")).size()>0)

                {
                    String notice =  driver.findElement(By.className("al-notification-message-body")).getText();
                    if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
                    {
                        driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();

                        new WebDriverWait(driver,300).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("al-diag-test-submit-button")));
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("al-diag-test-submit-button")));
                        if(driver.findElements(By.xpath("//input[@value='Finish']")).size()>0) {
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("al-diag-test-submit-button")));
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='ls-summary-report-link']")));

                        }
                    }

                }
                timer= new UIElement().waitAndFindElementWithoutAssert(By.cssSelector("span[id='timer-label']"));
            } //while loop ends
        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper attemptCorrect in class DiagnosticTest",e);
        }
    }


    public void attemptCorrect(int confidencelevel)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String confidence=Integer.toString(confidencelevel);
            if(driver.findElements(By.className("preview-multiple-select-answer-choice")).size()>0)
                new AttemptDiagnosticQuestion().attemptMultipleSelection("correct", false, false, confidence, "");

            else if(driver.findElements(By.className("preview-single-selection-answer-choices")).size() > 0)
                new AttemptDiagnosticQuestion().attemptMultipleChoice("correct", false, false, confidence, "");

            else if(driver.findElements(By.className("true-false-answer-choices")).size() > 0)
                new AttemptDiagnosticQuestion().attemptTrueFalse("correct", false, false, confidence, "");

            else if(driver.findElements(By.cssSelector("div[class='text-entry-input-wrapper visible_redactor_input_wrapper']")).size() > 0)
                new AttemptDiagnosticQuestion().attemptTextEntry("correct", false, false, confidence, "");

            else if(driver.findElements(By.cssSelector("div[class='text-entry-dropdown-wrapper visible_redactor_input_wrapper']")).size() > 0)
                new AttemptDiagnosticQuestion().attemptTextDropDown("correct", false, false, confidence, "");

            else if(driver.findElements(By.className("numeric_entry_student_title")).size() > 0)
                new AttemptDiagnosticQuestion().attemptNumericEntryWithUnits("correct", false, false, confidence, "");

            else if(driver.findElements(By.cssSelector("div[class='text-entry-input-wrapper visible_redactor_input_wrapper']")).size() > 0)
                new AttemptDiagnosticQuestion().attemptAdvancedNumeric("correct", false, false, confidence, "");

            else if(driver.findElements(By.cssSelector("div[class='symb-notation-math-edit-icon-preview']")).size() > 0)
                new AttemptDiagnosticQuestion().attemptExpressionEvaluator("correct", false, false, confidence, "");

            else if(driver.findElements(By.cssSelector("div[id='html-editor-non-draggable']")).size() > 0)
                new AttemptDiagnosticQuestion().attemptEssayType("correct", false, false, confidence, "");

            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("al-diag-test-submit-button")));
            Thread.sleep(2000);
            int noticesize = driver.findElements(By.className("al-notification-message-body")).size();
            if(noticesize==1)
            {
                String notice =  driver.findElement(By.className("al-notification-message-body")).getText();
                if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
                {
                    driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("al-diag-test-submit-button")));
                }

            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper attemptCorrect in class DiagnosticTest",e);
        }
    }

    public void attemptIncorrect(int confidencelevel)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String confidence=Integer.toString(confidencelevel);

            if(driver.findElements(By.className("multiple-selection-answer-choice-label-wrapper")).size()>0)
            {
                String corranswer=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                char correctanswer;
                if(corranswer.charAt(17) == '(')
                    correctanswer=corranswer.charAt(18);
                else
                    correctanswer=corranswer.charAt(17);

                String correctchoice=Character.toString(correctanswer); //first correct choice

                char correctanswer1=corranswer.charAt(22);
                String correctchoice1=Character.toString(correctanswer1); //second correct choice

                if((correctchoice.equals("A") && correctchoice1.equals("B")) || (correctchoice.equals("B") && correctchoice1.equals("A")) )
                {    correctchoice="C"; correctchoice1 = "D";}
                else if(correctchoice.equals("A") && correctchoice1.equals("C") || (correctchoice.equals("C") && correctchoice1.equals("A")))
                {    correctchoice="B"; correctchoice1 = "D"; }
                else if(correctchoice.equals("A") && correctchoice1.equals("D") || (correctchoice.equals("D") && correctchoice1.equals("A")))
                { correctchoice="B"; correctchoice1 = "C";}
                else if(correctchoice.equals("A") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("A")))
                { correctchoice="B"; correctchoice1 = "C";}
                else if(correctchoice.equals("A") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("A")))
                { correctchoice="B"; correctchoice1 = "C";}


                else if((correctchoice.equals("B") && correctchoice1.equals("C")) || (correctchoice.equals("C") && correctchoice1.equals("B")) )
                {    correctchoice="A"; correctchoice1 = "D";}
                else if(correctchoice.equals("B") && correctchoice1.equals("D") || (correctchoice.equals("D") && correctchoice1.equals("B")))
                {    correctchoice="A"; correctchoice1 = "C"; }
                else if(correctchoice.equals("B") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("B")))
                { correctchoice="A"; correctchoice1 = "C";}
                else if(correctchoice.equals("B") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("B")))
                { correctchoice="A"; correctchoice1 = "C";}

                else if((correctchoice.equals("C") && correctchoice1.equals("D")) || (correctchoice.equals("D") && correctchoice1.equals("C")) )
                {    correctchoice="A"; correctchoice1 = "B";}
                else if(correctchoice.equals("C") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("C")))
                {    correctchoice="A"; correctchoice1 = "B"; }
                else if(correctchoice.equals("C") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("C")))
                { correctchoice="A"; correctchoice1 = "B";}


                else if((correctchoice.equals("D") && correctchoice1.equals("E")) || (correctchoice.equals("E") && correctchoice1.equals("D")) )
                {    correctchoice="A"; correctchoice1 = "B";}
                else if(correctchoice.equals("D") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("D")))
                {    correctchoice="A"; correctchoice1 = "B"; }

                else if((correctchoice.equals("E") && correctchoice1.equals("F")) || (correctchoice.equals("F") && correctchoice1.equals("E")) )
                {    correctchoice="A"; correctchoice1 = "B";}

                //String corranswer1=driver.findElement(By.id("show-debug-correct-answer-block")).getText();

                List<WebElement> answer = driver.findElements(By.className("qtn-label"));
                for (WebElement answerchoice: answer)
                {

                    if(answerchoice.getText().trim().equals(correctchoice))
                    {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice);
                        break;
                    }
                }
                List<WebElement> answer1 = driver.findElements(By.className("qtn-label"));
                for (WebElement answerchoice1: answer1)
                {

                    if(answerchoice1.getText().trim().equals(correctchoice1))
                    {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice1);
                        break;
                    }
                }

            }

            else if(driver.findElements(By.className("answer-choice-label-wrapper")).size() > 0)
            {

                String corranswer=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                char correctanswer = corranswer.charAt(17);
                String correctchoice=Character.toString(correctanswer);
                if(correctchoice.equals("A"))
                    correctchoice="B";
                else if(correctchoice.equals("B"))
                    correctchoice="A";
                else if(correctchoice.equals("C"))
                    correctchoice="D";
                else if(correctchoice.equals("D"))
                    correctchoice="C";


                List<WebElement> answer = driver.findElements(By.className("qtn-label"));
                for (WebElement answerchoice: answer)
                {

                    if(answerchoice.getText().trim().equals(correctchoice))
                    {
                        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice);
                        break;
                    }
                }
            }
            else if(driver.findElements(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']")).size() > 0)
            {
                String corranswertext=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                int lastindex=corranswertext.length();
                String corrcttextanswer=corranswertext.substring(16, lastindex);
                driver.findElement(By.cssSelector("input[id='1']")).sendKeys(corrcttextanswer+"bh");
            }
            else if(driver.findElements(By.className("sbHolder")).size() > 0)
            {
                String corranswertext=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                int lastindex=corranswertext.length();
                String corrcttextanswer=corranswertext.substring(16, lastindex);
                driver.findElement(By.className("sbToggle")).click();
                Thread.sleep(2000);

                String values = driver.findElement(By.cssSelector("ul[class='sbOptions']")).getText();
                //values = values.replaceAll("\n", " ");
                String [] val = values.split("\n");


                for(String element:val)
                {

                    if(!element.equals(corrcttextanswer))
                    {

                        driver.findElement(By.linkText(element)).click();
                        break;
                    }
                }
            }
            Thread.sleep(2000);
            if(confidencelevel!=0)
            {
                new UIElement().waitAndFindElement(By.cssSelector("a[id='"+confidence+"']"));
                driver.findElement(By.cssSelector("a[id='"+confidence+"']")).click();//click on confidence level
                Thread.sleep(2000);
            }
            new UIElement().waitAndFindElement(By.className("al-diag-test-submit-button"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("al-diag-test-submit-button")));
            Thread.sleep(2000);
            int noticesize = driver.findElements(By.className("al-notification-message-body")).size();
            if(noticesize==1)

            {
                String notice =  driver.findElement(By.className("al-notification-message-body")).getText();
                if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
                {
                    driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("al-diag-test-submit-button")));
                }

            }


        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper attemptIncorrect in class DiagnosticTest",e);
        }
    }

    public void DiagonesticTestQuitBetween(int confidencelevel,int numberofquestionattempt,String answeroption,boolean useHints,boolean useSolutionText,boolean quitorcontinue)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            if(answeroption.equals("correct"))
            {
                for(int i=1;i<=numberofquestionattempt;i++)
                {
                    String confidence=Integer.toString(confidencelevel);
                    if(useHints == true)
                        driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();

                    if(driver.findElements(By.className("preview-single-selection-answer-choices")).size() > 0)
                        new AttemptDiagnosticQuestion().attemptMultipleChoice("correct", false, false, confidence, "");

                    else if(driver.findElements(By.className("preview-multiple-select-answer-choice")).size()>0)
                        new AttemptDiagnosticQuestion().attemptMultipleSelection("correct", false, false, confidence, "");

                    else if(driver.findElements(By.className("true-false-answer-choices")).size() > 0)
                        new AttemptDiagnosticQuestion().attemptTrueFalse("correct", false, false, confidence, "");

                    else if(driver.findElements(By.cssSelector("div[class='text-entry-input-wrapper visible_redactor_input_wrapper']")).size() > 0)
                        new AttemptDiagnosticQuestion().attemptTextEntry("correct", false, false, confidence, "");

                    else if(driver.findElements(By.cssSelector("div[class='text-entry-dropdown-wrapper visible_redactor_input_wrapper']")).size() > 0)
                        new AttemptDiagnosticQuestion().attemptTextDropDown("correct", false, false, confidence, "");

                    else if(driver.findElements(By.className("numeric_entry_student_title")).size() > 0)
                        new AttemptDiagnosticQuestion().attemptNumericEntryWithUnits("correct", false, false, confidence, "");

                    else if(driver.findElements(By.cssSelector("div[class='text-entry-input-wrapper visible_redactor_input_wrapper']")).size() > 0)
                        new AttemptDiagnosticQuestion().attemptAdvancedNumeric("correct", false, false, confidence, "");

                    else if(driver.findElements(By.cssSelector("div[class='symb-notation-math-edit-icon-preview']")).size() > 0)
                        new AttemptDiagnosticQuestion().attemptExpressionEvaluator("correct", false, false, confidence, "");

                    else if(driver.findElements(By.cssSelector("div[id='html-editor-non-draggable']")).size() > 0)
                        new AttemptDiagnosticQuestion().attemptEssayType("correct", false, false, confidence, "");

                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("al-diag-test-submit-button")));
                    Thread.sleep(2000);
                    int noticesize = driver.findElements(By.className("al-notification-message-body")).size();
                    if(noticesize==1)

                    {
                        String notice =  driver.findElement(By.className("al-notification-message-body")).getText();
                        if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
                        {
                            driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("al-diag-test-submit-button")));
                        }

                    }

                } //for loop for correct answer submit ends

            } //if condition for correct answer submit ends

            else //Attempting all questions as incorrect
            {
                for(int j=1;j<=numberofquestionattempt;j++)
                {
                    if(useHints == true)
                        driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();
                    String confidence=Integer.toString(confidencelevel);

                    String corranswertext=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                    int lastindex=corranswertext.length();
                    String corrcttextanswer=corranswertext.substring(16, lastindex);

                    if(driver.findElements(By.className("multiple-selection-answer-choice-label-wrapper")).size()>0)
                    {
                        String corranswer=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        char correctanswer;
                        if(corranswer.charAt(17) == '(')
                            correctanswer=corranswer.charAt(18);
                        else
                            correctanswer=corranswer.charAt(17);

                        String  correctchoice=Character.toString(correctanswer); //first correct choice

                        char correctanswer1=corranswer.charAt(22);
                        String correctchoice1=Character.toString(correctanswer1); //second correct choice

                        if((correctchoice.equals("A") && correctchoice1.equals("B")) || (correctchoice.equals("B") && correctchoice1.equals("A")) )
                        {    correctchoice="C"; correctchoice1 = "D";}
                        else if(correctchoice.equals("A") && correctchoice1.equals("C") || (correctchoice.equals("C") && correctchoice1.equals("A")))
                        {    correctchoice="B"; correctchoice1 = "D"; }
                        else if(correctchoice.equals("A") && correctchoice1.equals("D") || (correctchoice.equals("D") && correctchoice1.equals("A")))
                        { correctchoice="B"; correctchoice1 = "C";}
                        else if(correctchoice.equals("A") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("A")))
                        { correctchoice="B"; correctchoice1 = "C";}
                        else if(correctchoice.equals("A") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("A")))
                        { correctchoice="B"; correctchoice1 = "C";}


                        else if((correctchoice.equals("B") && correctchoice1.equals("C")) || (correctchoice.equals("C") && correctchoice1.equals("B")) )
                        {    correctchoice="A"; correctchoice1 = "D";}
                        else if(correctchoice.equals("B") && correctchoice1.equals("D") || (correctchoice.equals("D") && correctchoice1.equals("B")))
                        {    correctchoice="A"; correctchoice1 = "C"; }
                        else if(correctchoice.equals("B") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("B")))
                        { correctchoice="A"; correctchoice1 = "C";}
                        else if(correctchoice.equals("B") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("B")))
                        { correctchoice="A"; correctchoice1 = "C";}

                        else if((correctchoice.equals("C") && correctchoice1.equals("D")) || (correctchoice.equals("D") && correctchoice1.equals("C")) )
                        {    correctchoice="A"; correctchoice1 = "B";}
                        else if(correctchoice.equals("C") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("C")))
                        {    correctchoice="A"; correctchoice1 = "B"; }
                        else if(correctchoice.equals("C") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("C")))
                        { correctchoice="A"; correctchoice1 = "B";}


                        else if((correctchoice.equals("D") && correctchoice1.equals("E")) || (correctchoice.equals("E") && correctchoice1.equals("D")) )
                        {    correctchoice="A"; correctchoice1 = "B";}
                        else if(correctchoice.equals("D") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("D")))
                        {    correctchoice="A"; correctchoice1 = "B"; }

                        else if((correctchoice.equals("E") && correctchoice1.equals("F")) || (correctchoice.equals("F") && correctchoice1.equals("E")) )
                        {    correctchoice="A"; correctchoice1 = "B";}
                        List<WebElement> answer = driver.findElements(By.className("qtn-label"));
                        for (WebElement answerchoice: answer)
                        {

                            if(answerchoice.getText().trim().equals(correctchoice))
                            {
                                ((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice);
                                break;
                            }
                        }
                        List<WebElement> answer1 = driver.findElements(By.className("qtn-label"));
                        for (WebElement answerchoice1: answer1)
                        {

                            if(answerchoice1.getText().trim().equals(correctchoice1))
                            {
                                ((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice1);
                                break;
                            }
                        }

                    }

                    else if(driver.findElements(By.className("answer-choice-label-wrapper")).size() > 0)
                    {
                        String corranswer=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        char correctanswer=corranswer.charAt(17);
                        String correctchoice=Character.toString(correctanswer);
                        if(correctchoice.equals("A"))
                            correctchoice="B";
                        else if(correctchoice.equals("B"))
                            correctchoice="A";
                        else if(correctchoice.equals("C"))
                            correctchoice="D";
                        else if(correctchoice.equals("D"))
                            correctchoice="C";
                        List<WebElement> answer = driver.findElements(By.className("qtn-label"));
                        for (WebElement answerchoice: answer)
                        {

                            if(answerchoice.getText().trim().equals(correctchoice))
                            {
                                ((JavascriptExecutor) driver).executeScript("arguments[0].click();",answerchoice);
                                break;
                            }
                        }

                    }

                    else if(driver.findElements(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']")).size() > 0)
                    {

                        driver.findElement(By.cssSelector("input[id='1']")).sendKeys(corrcttextanswer+"bh");
                    }
                    else if(driver.findElements(By.className("sbHolder")).size() > 0)
                    {
                        driver.findElement(By.className("sbToggle")).click();
                        new UIElement().waitAndFindElement(By.cssSelector("ul[class='sbOptions']"));

                        String values = driver.findElement(By.cssSelector("ul[class='sbOptions']")).getText();
                        //values = values.replaceAll("\n", " ");
                        String [] val = values.split("\n");


                        for(String element:val)
                        {
                            if(!element.equals(corrcttextanswer))
                            {
                                driver.findElement(By.linkText(element)).click();
                                break;
                            }
                        }
                    }
                    Thread.sleep(2000);

                    if(confidencelevel<5)
                    {
                        new UIElement().waitAndFindElement(By.cssSelector("a[id='"+confidence+"']"));
                        driver.findElement(By.cssSelector("a[id='"+confidence+"']")).click();//click on confidence level
                        Thread.sleep(2000);
                    }
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("al-diag-test-submit-button")));
                    Thread.sleep(2000);
                    int noticesize = driver.findElements(By.className("al-notification-message-body")).size();
                    if(noticesize==1)

                    {
                        String notice =  driver.findElement(By.className("al-notification-message-body")).getText();
                        if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
                        {
                            driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.className("al-diag-test-submit-button")));
                        }

                    }

                } //for loop for attempting all questions incorrectly ends
            } //else condition for attempting all questions incorrectly ends

            driver.findElement(By.className("al-quit-diag-test-icon")).click();
            Thread.sleep(1000);
            if(quitorcontinue==true)
            {
                new UIElement().waitAndFindElement(By.className("al-quit-diag-test"));
                driver.findElement(By.className("al-quit-diag-test")).click();
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("span[class='ls-summary-report-link']")));
                Thread.sleep(2000);
            }
            else
            {
                new UIElement().waitAndFindElement(By.className("al-diag-test-continue-later"));
                driver.findElement(By.className("al-diag-test-continue-later")).click();
                Thread.sleep(2000);
            }


        }
        catch(Exception e)
        {
            new Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in App helper method  DiagonesticTestQuitBetween",e);
        }
    }
    public void startDiagnosticTest(int confidenceLevelIndex,String chapterName)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            new Click().clickbyxpath("//a[@title='"+chapterName+"']");
            WebElement confidencelevel =(new WebDriverWait(driver, 2)).until(ExpectedConditions.presenceOfElementLocated(By.id(Integer.toString(confidenceLevelIndex))));
            if(confidencelevel != null) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id(Integer.toString(confidenceLevelIndex))));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("input.ls-assessment-continue-btn")));
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper startDiagnosticTest in class DiagnosticTest",e);
        }
    }


    public void continueDiagnosticTest()
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            new Click().clickBycssselector("div[data-id='orion-adaptive-practice-tab']");
            List<WebElement> continueButtons =  driver.findElements(By.id("ls-diag-assessment-continue-btn"));
            for(WebElement startButton : continueButtons) {
                if(startButton.isDisplayed()) {
                    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", startButton);
                    break;
                }
            }

        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper continueDiagnosticTest in class DiagnosticTest",e);
        }
    }




    public void startTestBasedOnTitle(int confidenceLevelIndex,String title)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();

            new Click().clickBycssselector("a[title='"+title+"']");
            WebElement confidencelevel = 	(new WebDriverWait(driver, 2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.id(Integer.toString(confidenceLevelIndex))));
            if(confidencelevel != null)
            {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.id(Integer.toString(confidenceLevelIndex))));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.cssSelector("input.ls-assessment-continue-btn")));
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper startTest in class DiagnosticTest",e);
        }
    }


    public void quitTestAndViewReport()
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            Thread.sleep(1000);
            driver.findElement(By.className("al-quit-diag-test-icon")).click();
            Thread.sleep(1000);
            driver.findElement(By.className("ls-practice-test-view-report")).click();
            Thread.sleep(1000);
        }
        catch(Exception e)
        {

            Assert.fail("Exception in quitting the diagnostic test",e);
        }
    }


    public void beginDiagnosticFromAssignmentPage(int confidenceLevelIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);

            new Navigator().NavigateTo("Assignments");//navigate to Assignment page
            assignments.getAssignmentName().click();//Click on assignment
            assignments.popUp_diagnosticTest_beginDiagnostic.click();//Click on begin Diagnostic
            List<WebElement> conf =  driver.findElements(By.id(Integer.toString(confidenceLevelIndex)));
            for(WebElement c : conf) {
                if(c.isDisplayed()) {
                    c.click();
                    break;
                }
            }
            Thread.sleep(2000);
            assignments.popUp_diagnosticTest_arrow.click();//Click on arrow
            Thread.sleep(1000);

        }
        catch(Exception e)
        {

            Assert.fail("Exception in beginDiagnosticFromAssignmentPage the diagnostic test",e);
        }
    }


    public void continueDiagnosticFromAssignmentPage()
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            Assignments assignments = PageFactory.initElements(driver, Assignments.class);

            new Navigator().NavigateTo("Assignments");//navigate to Assignment page
            assignments.getAssignmentName().click();//Click on assignment
            assignments.popUp_diagnosticTest_beginDiagnostic.click();//Click on begin Diagnostic
            Thread.sleep(2000);

        }
        catch(Exception e)
        {

            Assert.fail("Exception in beginDiagnosticFromAssignmentPage the diagnostic test",e);
        }
    }



}