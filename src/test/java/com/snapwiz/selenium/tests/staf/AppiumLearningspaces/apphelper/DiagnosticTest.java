package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import java.util.List;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.ReadTestData;


public class DiagnosticTest {

	public void startTest(int confidenceLevelIndex)
	{
		try
		{
		  new Click().clickBycssselector("div[data-id='orion-adaptive-practice-tab']");
          List<WebElement> conf =  Driver.driver.findElements(By.id(Integer.toString(confidenceLevelIndex)));
            for(WebElement c : conf) {
                if(c.isDisplayed()) {
                    c.click();
                    break;
                }
            }
            List<WebElement> startButtons =  Driver.driver.findElements(By.xpath("//input[@class='practice-assigned ls-assessment-continue-btn orion-adaptive-practice-tab-btn']"));
            for(WebElement startButton : startButtons) {
                if(startButton.isDisplayed()) {
                    startButton.click();
                    break;
                }
            }
            // ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//input[@class='ls-assessment-continue']")));

        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper startTest in class DiagnosticTest",e);
        }
    }

    public void startTest(int confidenceLevelIndex, int dataIndex)
    {
        try
        {
            String chapterName = ReadTestData.readDataByTagName("", "chapterName", ""+dataIndex);//fetch assignment name from textdata
            WebDriverWait wait  = new WebDriverWait(Driver.driver,60);
            wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("selected-chapter-name"),"Ch 1:"));
            if(chapterName!=null){
                List<WebElement> chapterHeadingList = Driver.driver.findElements(By.cssSelector("div[class='chapter-heading']"));
                for(int a=0;a<chapterHeadingList.size();a++){
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].scrollIntoView(true);", chapterHeadingList.get(a));
                    if(chapterHeadingList.get(a).getText().contains(chapterName)){
                        List<WebElement> duplicateChaptersElementsList = Driver.driver.findElements(By.xpath(".//*[@title='" + chapterName + "']"));
                        ((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();", duplicateChaptersElementsList.get(duplicateChaptersElementsList.size() - 1));
                        wait  = new WebDriverWait(Driver.driver,60);
                        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.className("selected-chapter-name"),chapterName));
                        break;
                    }
                }
            }
            new Click().clickBycssselector("div[data-id='orion-adaptive-practice-tab']");
            List<WebElement> conf =  Driver.driver.findElements(By.id(Integer.toString(confidenceLevelIndex)));
            for(WebElement c : conf) {
                if(c.isDisplayed()) {
                    c.click();
                    break;
                }
            }
            List<WebElement> startButtons =  Driver.driver.findElements(By.xpath("//input[@class='practice-assigned ls-assessment-continue-btn orion-adaptive-practice-tab-btn']"));
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

            new Click().clickbyxpath("//h3[contains(text(),'"+chapterName+"')]");
            new Click().clickBycssselector("div[data-id='orion-adaptive-practice-tab']");
            List<WebElement> conf =  Driver.driver.findElements(By.id(Integer.toString(confidenceLevelIndex)));
            for(WebElement c : conf) {
                if(c.isDisplayed()) {
                    c.click();
                    break;
                }
            }

            List<WebElement> startButtons =  Driver.driver.findElements(By.xpath("//input[@class='practice-assigned ls-assessment-continue-btn orion-adaptive-practice-tab-btn']"));
            for(WebElement startButton : startButtons) {
                if(startButton.isDisplayed()) {
                    startButton.click();
                    break;
                }
            }

            //((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//input[@class='ls-assessment-continue-btn orion-adaptive-practice-tab-btn']")));
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
            new Click().clickBycssselector("div[data-id='orion-adaptive-practice-tab']");
            Driver.driver.findElement(By.cssSelector("input[id='preview-test-button']")).click();//click on 1st chapter's Diagnostic Test
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
            String card1topic1 = ReadTestData.readDataByTagName("tocdata",testName, "1");
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("a[title='"+card1topic1+"']")));
            WebElement confidencelevel =     (new WebDriverWait(Driver.driver, 2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.id(Integer.toString(confidenceLevelIndex))));
            if(confidencelevel != null)
            {
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id(Integer.toString(confidenceLevelIndex))));
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input.ls-assessment-continue-btn")));
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
            String confidence=Integer.toString(confidencelevel);
            new UIElement().waitAndFindElement(By.className("question-label"));
            boolean timer=new UIElement().waitAndFindElementWithoutAssert(By.cssSelector("span[id='timer-label']"));
            while(timer==true)
            {
                if(useHints == true) {
                    Driver.driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();
                }
                if(Driver.driver.findElements(By.className("preview-single-selection-answer-choices")).size() > 0)
                    new AttemptDiagnosticQuestion().attemptMultipleChoice("correct", useHints, useSolutionText, confidence, "");

                else if(Driver.driver.findElements(By.className("preview-multiple-select-answer-choice")).size() >0) {
                    System.out.println("before attempting");
                    new AttemptDiagnosticQuestion().attemptMultipleSelection("correct", useHints, useSolutionText, confidence, "");
                    System.out.println("after attempting");
                }

                else if(Driver.driver.findElements(By.className("true-false-answer-choices")).size() > 0)
                    new AttemptDiagnosticQuestion().attemptTrueFalse("correct", useHints, useSolutionText, confidence, "");

                else if(Driver.driver.findElements(By.cssSelector("div[class='text-entry-input-wrapper visible_redactor_input_wrapper']")).size()>0)
                    new AttemptDiagnosticQuestion().attemptTextEntry("correct", useHints, useSolutionText, confidence, "");

                else if(Driver.driver.findElements(By.cssSelector("div[class='text-entry-dropdown-wrapper visible_redactor_input_wrapper']")).size()>0)
                    new AttemptDiagnosticQuestion().attemptTextDropDown("correct", useHints, useSolutionText, confidence, "");

                else if(Driver.driver.findElements(By.className("numeric_entry_student_title")).size()>0)
                    new AttemptDiagnosticQuestion().attemptNumericEntryWithUnits("correct", useHints, useSolutionText, confidence, "");


                else if(Driver.driver.findElements(By.cssSelector("div[class='symb-notation-math-edit-icon-preview']")).size()>0)
                    new AttemptDiagnosticQuestion().attemptExpressionEvaluator("correct", useHints, useSolutionText, confidence, "");

                else if(Driver.driver.findElements(By.cssSelector("div[class='html-editor-non-draggable']")).size()>0)
                    new AttemptDiagnosticQuestion().attemptEssayType("correct", useHints, useSolutionText, confidence, "");
                new WebDriverWait(Driver.driver,300).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("al-diag-test-submit-button")));
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("al-diag-test-submit-button")));
                Thread.sleep(500);
                if(Driver.driver.findElements(By.className("al-notification-message-body")).size()>0)

                {
                    String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                    if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
                    {
                        Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();

                        new WebDriverWait(Driver.driver,300).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("al-diag-test-submit-button")));
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("al-diag-test-submit-button")));
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
            String confidence=Integer.toString(confidencelevel);
            if(Driver.driver.findElements(By.className("preview-multiple-select-answer-choice")).size()>0)
                new AttemptDiagnosticQuestion().attemptMultipleSelection("correct", false, false, confidence, "");

            else if(Driver.driver.findElements(By.className("preview-single-selection-answer-choices")).size() > 0)
                new AttemptDiagnosticQuestion().attemptMultipleChoice("correct", false, false, confidence, "");

            else if(Driver.driver.findElements(By.className("true-false-answer-choices")).size() > 0)
                new AttemptDiagnosticQuestion().attemptTrueFalse("correct", false, false, confidence, "");

            else if(Driver.driver.findElements(By.cssSelector("div[class='text-entry-input-wrapper visible_redactor_input_wrapper']")).size() > 0)
                new AttemptDiagnosticQuestion().attemptTextEntry("correct", false, false, confidence, "");

            else if(Driver.driver.findElements(By.cssSelector("div[class='text-entry-dropdown-wrapper visible_redactor_input_wrapper']")).size() > 0)
                new AttemptDiagnosticQuestion().attemptTextDropDown("correct", false, false, confidence, "");

            else if(Driver.driver.findElements(By.className("numeric_entry_student_title")).size() > 0)
                new AttemptDiagnosticQuestion().attemptNumericEntryWithUnits("correct", false, false, confidence, "");

            else if(Driver.driver.findElements(By.cssSelector("div[class='text-entry-input-wrapper visible_redactor_input_wrapper']")).size() > 0)
                new AttemptDiagnosticQuestion().attemptAdvancedNumeric("correct", false, false, confidence, "");

            else if(Driver.driver.findElements(By.cssSelector("div[class='symb-notation-math-edit-icon-preview']")).size() > 0)
                new AttemptDiagnosticQuestion().attemptExpressionEvaluator("correct", false, false, confidence, "");

            else if(Driver.driver.findElements(By.cssSelector("div[id='html-editor-non-draggable']")).size() > 0)
                new AttemptDiagnosticQuestion().attemptEssayType("correct", false, false, confidence, "");

            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("al-diag-test-submit-button")));
            Thread.sleep(2000);
            int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
            if(noticesize==1)
            {
                String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
                {
                    Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("al-diag-test-submit-button")));
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
            String confidence=Integer.toString(confidencelevel);

            if(Driver.driver.findElements(By.className("multiple-selection-answer-choice-label-wrapper")).size()>0)
            {
                String corranswer=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
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

                //String corranswer1=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();

                List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
                for (WebElement answerchoice: answer)
                {

                    if(answerchoice.getText().trim().equals(correctchoice))
                    {
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",answerchoice);
                        break;
                    }
                }
                List<WebElement> answer1 = Driver.driver.findElements(By.className("qtn-label"));
                for (WebElement answerchoice1: answer1)
                {

                    if(answerchoice1.getText().trim().equals(correctchoice1))
                    {
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",answerchoice1);
                        break;
                    }
                }

            }

            else if(Driver.driver.findElements(By.className("answer-choice-label-wrapper")).size() > 0)
            {

                String corranswer=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
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


                List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
                for (WebElement answerchoice: answer)
                {

                    if(answerchoice.getText().trim().equals(correctchoice))
                    {
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",answerchoice);
                        break;
                    }
                }
            }
            else if(Driver.driver.findElements(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']")).size() > 0)
            {
                String corranswertext=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                int lastindex=corranswertext.length();
                String corrcttextanswer=corranswertext.substring(16, lastindex);
                Driver.driver.findElement(By.cssSelector("input[id='1']")).sendKeys(corrcttextanswer+"bh");
            }
            else if(Driver.driver.findElements(By.className("sbHolder")).size() > 0)
            {
                String corranswertext=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                int lastindex=corranswertext.length();
                String corrcttextanswer=corranswertext.substring(16, lastindex);
                Driver.driver.findElement(By.className("sbToggle")).click();
                Thread.sleep(2000);

                String values = Driver.driver.findElement(By.cssSelector("ul[class='sbOptions']")).getText();
                //values = values.replaceAll("\n", " ");
                String [] val = values.split("\n");


                for(String element:val)
                {

                    if(!element.equals(corrcttextanswer))
                    {

                        Driver.driver.findElement(By.linkText(element)).click();
                        break;
                    }
                }
            }
            Thread.sleep(2000);
            if(confidencelevel!=0)
            {
                new UIElement().waitAndFindElement(By.cssSelector("a[id='"+confidence+"']"));
                Driver.driver.findElement(By.cssSelector("a[id='"+confidence+"']")).click();//click on confidence level
                Thread.sleep(2000);
            }
            new UIElement().waitAndFindElement(By.className("al-diag-test-submit-button"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("al-diag-test-submit-button")));
            Thread.sleep(2000);
            int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
            if(noticesize==1)

            {
                String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
                {
                    Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("al-diag-test-submit-button")));
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
            if(answeroption.equals("correct"))
            {
                for(int i=1;i<=numberofquestionattempt;i++)
                {
                    String confidence=Integer.toString(confidencelevel);
                    if(useHints == true)
                        Driver.driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();

                    if(Driver.driver.findElements(By.className("preview-single-selection-answer-choices")).size() > 0)
                        new AttemptDiagnosticQuestion().attemptMultipleChoice("correct", false, false, confidence, "");

                    else if(Driver.driver.findElements(By.className("preview-multiple-select-answer-choice")).size()>0)
                        new AttemptDiagnosticQuestion().attemptMultipleSelection("correct", false, false, confidence, "");

                    else if(Driver.driver.findElements(By.className("true-false-answer-choices")).size() > 0)
                        new AttemptDiagnosticQuestion().attemptTrueFalse("correct", false, false, confidence, "");

                    else if(Driver.driver.findElements(By.cssSelector("div[class='text-entry-input-wrapper visible_redactor_input_wrapper']")).size() > 0)
                        new AttemptDiagnosticQuestion().attemptTextEntry("correct", false, false, confidence, "");

                    else if(Driver.driver.findElements(By.cssSelector("div[class='text-entry-dropdown-wrapper visible_redactor_input_wrapper']")).size() > 0)
                        new AttemptDiagnosticQuestion().attemptTextDropDown("correct", false, false, confidence, "");

                    else if(Driver.driver.findElements(By.className("numeric_entry_student_title")).size() > 0)
                        new AttemptDiagnosticQuestion().attemptNumericEntryWithUnits("correct", false, false, confidence, "");

                    else if(Driver.driver.findElements(By.cssSelector("div[class='text-entry-input-wrapper visible_redactor_input_wrapper']")).size() > 0)
                        new AttemptDiagnosticQuestion().attemptAdvancedNumeric("correct", false, false, confidence, "");

                    else if(Driver.driver.findElements(By.cssSelector("div[class='symb-notation-math-edit-icon-preview']")).size() > 0)
                        new AttemptDiagnosticQuestion().attemptExpressionEvaluator("correct", false, false, confidence, "");

                    else if(Driver.driver.findElements(By.cssSelector("div[id='html-editor-non-draggable']")).size() > 0)
                        new AttemptDiagnosticQuestion().attemptEssayType("correct", false, false, confidence, "");

                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("al-diag-test-submit-button")));
                    Thread.sleep(2000);
                    int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
                    if(noticesize==1)

                    {
                        String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                        if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
                        {
                            Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("al-diag-test-submit-button")));
                        }

                    }

                } //for loop for correct answer submit ends

            } //if condition for correct answer submit ends

            else //Attempting all questions as incorrect
            {
                for(int j=1;j<=numberofquestionattempt;j++)
                {
                    if(useHints == true)
                        Driver.driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();
                    String confidence=Integer.toString(confidencelevel);

                    String corranswertext=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                    int lastindex=corranswertext.length();
                    String corrcttextanswer=corranswertext.substring(16, lastindex);

                    if(Driver.driver.findElements(By.className("multiple-selection-answer-choice-label-wrapper")).size()>0)
                    {
                        String corranswer=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
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
                        List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
                        for (WebElement answerchoice: answer)
                        {

                            if(answerchoice.getText().trim().equals(correctchoice))
                            {
                                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",answerchoice);
                                break;
                            }
                        }
                        List<WebElement> answer1 = Driver.driver.findElements(By.className("qtn-label"));
                        for (WebElement answerchoice1: answer1)
                        {

                            if(answerchoice1.getText().trim().equals(correctchoice1))
                            {
                                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",answerchoice1);
                                break;
                            }
                        }

                    }

                    else if(Driver.driver.findElements(By.className("answer-choice-label-wrapper")).size() > 0)
                    {
                        String corranswer=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
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
                        List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
                        for (WebElement answerchoice: answer)
                        {

                            if(answerchoice.getText().trim().equals(correctchoice))
                            {
                                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",answerchoice);
                                break;
                            }
                        }

                    }

                    else if(Driver.driver.findElements(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']")).size() > 0)
                    {

                        Driver.driver.findElement(By.cssSelector("input[id='1']")).sendKeys(corrcttextanswer+"bh");
                    }
                    else if(Driver.driver.findElements(By.className("sbHolder")).size() > 0)
                    {
                        Driver.driver.findElement(By.className("sbToggle")).click();
                        new UIElement().waitAndFindElement(By.cssSelector("ul[class='sbOptions']"));

                        String values = Driver.driver.findElement(By.cssSelector("ul[class='sbOptions']")).getText();
                        //values = values.replaceAll("\n", " ");
                        String [] val = values.split("\n");


                        for(String element:val)
                        {
                            if(!element.equals(corrcttextanswer))
                            {
                                Driver.driver.findElement(By.linkText(element)).click();
                                break;
                            }
                        }
                    }
                    Thread.sleep(2000);

                    if(confidencelevel<5)
                    {
                        new UIElement().waitAndFindElement(By.cssSelector("a[id='"+confidence+"']"));
                        Driver.driver.findElement(By.cssSelector("a[id='"+confidence+"']")).click();//click on confidence level
                        Thread.sleep(2000);
                    }
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("al-diag-test-submit-button")));
                    Thread.sleep(2000);
                    int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
                    if(noticesize==1)

                    {
                        String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                        if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
                        {
                            Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("al-diag-test-submit-button")));
                        }

                    }

                } //for loop for attempting all questions incorrectly ends
            } //else condition for attempting all questions incorrectly ends

            Driver.driver.findElement(By.className("al-quit-diag-test-icon")).click();
            Thread.sleep(1000);
            if(quitorcontinue==true)
            {
                new UIElement().waitAndFindElement(By.className("al-quit-diag-test"));
                Driver.driver.findElement(By.className("al-quit-diag-test")).click();
                Thread.sleep(2000);
            }
            else
            {
                new UIElement().waitAndFindElement(By.className("al-diag-test-continue-later"));
                Driver.driver.findElement(By.className("al-diag-test-continue-later")).click();
                Thread.sleep(2000);
            }


        }
        catch(Exception e)
        {
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Screenshot().captureScreenshotFromAppHelper();
            Assert.fail("Exception in App helper method  DiagonesticTestQuitBetween",e);
        }
    }
    public void startDiagnosticTest(int confidenceLevelIndex,String chapterName)
    {
        try
        {

            new Click().clickbyxpath("//a[@title='"+chapterName+"']");
            WebElement confidencelevel =     (new WebDriverWait(Driver.driver, 2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.id(Integer.toString(confidenceLevelIndex))));
            if(confidencelevel != null)
            {
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id(Integer.toString(confidenceLevelIndex))));
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input.ls-assessment-continue-btn")));
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
            new Click().clickBycssselector("div[data-id='orion-adaptive-practice-tab']");
            List<WebElement> continueButtons =  Driver.driver.findElements(By.id("ls-diag-assessment-continue-btn"));
            for(WebElement startButton : continueButtons) {
                if(startButton.isDisplayed()) {
                    startButton.click();
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

            new Click().clickBycssselector("a[title='"+title+"']");
            WebElement confidencelevel = 	(new WebDriverWait(Driver.driver, 2))
                    .until(ExpectedConditions.presenceOfElementLocated(By.id(Integer.toString(confidenceLevelIndex))));
            if(confidencelevel != null)
            {
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.id(Integer.toString(confidenceLevelIndex))));
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input.ls-assessment-continue-btn")));
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
            Thread.sleep(1000);
            Driver.driver.findElement(By.className("al-quit-diag-test-icon")).click();
            Thread.sleep(1000);
            Driver.driver.findElement(By.className("ls-practice-test-view-report")).click();
            Thread.sleep(1000);
        }
        catch(Exception e)
        {

            Assert.fail("Exception in quitting the diagnostic test",e);
        }
    }

}