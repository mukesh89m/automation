package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;




import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.Click;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;

import java.util.List;

public class AttemptTest
{

	//Attempt Daigonestics Test
	public void Diagonestictest()
	{

		int testend=Driver.driver.findElements(By.className("al-diag-test-timer")).size();

		while(testend==1)
		{
			try
			{
			//Thread.sleep(3000);
			testend=Driver.driver.findElements(By.className("al-diag-test-timer")).size();
			if(testend == 0)
			break;
			//Driver.driver.findElements(By.className("al-diag-test-timer"));
			new SelectAnswerAndSubmit().daigonestianswersubmit("A");
			}
			catch(Exception e)
			{
				Assert.fail("Question or element not found",e);
			}


		}

	}
	//Attempt adaptive test
	public void AdaptiveTest(int numberofquestion)
	{
		for(int i=0;i<=numberofquestion;i++)
		{
			new SelectAnswerAndSubmit().Adaptiveasnswersubmit("B");
		}

	}
	public void StaticTest()
	{

		int testend=Driver.driver.findElements(By.className("al-diag-test-timer")).size();

		while(testend==1)
		{
			try
			{
			Thread.sleep(3000);
			testend=Driver.driver.findElements(By.className("al-diag-test-timer")).size();
			if(testend == 0)
			break;
			Driver.driver.findElements(By.className("al-diag-test-timer"));
			new SelectAnswerAndSubmit().staticanswersubmit("A");
			}
			catch(Exception e)
			{
				Assert.fail("Question or element not found",e);
			}

		}
	}
    public void StaticTestWithConfidence(int confidenceLevel)
    {

        int testend=Driver.driver.findElements(By.className("al-diag-test-timer")).size();

        while(testend==1)
        {
            try
            {
                Thread.sleep(3000);
                testend=Driver.driver.findElements(By.className("al-diag-test-timer")).size();
                if(testend == 0)
                    break;
                Driver.driver.findElements(By.className("al-diag-test-timer"));
                new SelectAnswerAndSubmit().staticAnswerSubmitWithConfidenceLevel("A",confidenceLevel);
            }
            catch(Exception e)
            {
                Assert.fail("Question or element not found",e);
            }

        }
    }

    /*
    * @Author Mukesh
    * Method will attempt all question type of static assessment
    */
    public void StaticTestAttemptAllTypeWithConfidence(int confidencelevel,boolean useHints,boolean useSolutionText)
    {

        int testend=Driver.driver.findElements(By.className("al-diag-test-timer")).size();

        while(testend==1)
        {
            try
            {
                Thread.sleep(3000);
                testend=Driver.driver.findElements(By.className("al-diag-test-timer")).size();
                if(testend == 0)
                    break;
                Driver.driver.findElements(By.className("al-diag-test-timer"));
                attemptAllCorrect(confidencelevel,useHints,useSolutionText);
            }
            catch(Exception e)
            {
                Assert.fail("",e);
            }

        }
    }


    /*Static Test*/
    public void attemptAllCorrect(int confidencelevel,boolean useHints,boolean useSolutionText)
    {
        try
        {
            String confidence=Integer.toString(confidencelevel);
            int timer=Driver.driver.findElements(By.cssSelector("span[id='timer-label']")).size();
            while(timer!=0)
            {
                if(useHints == true)
                    Driver.driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();
                if(Driver.driver.findElements(By.className("preview-multiple-select-answer-choice")).size()>0)
                    attemptMultipleSelection("correct", useHints, useSolutionText, confidence, "");

                else if(Driver.driver.findElements(By.className("preview-single-selection-answer-choices")).size() > 0)
                    attemptMultipleChoice("correct", useHints, useSolutionText, confidence, "");

                else if(Driver.driver.findElements(By.className("true-false-answer-choices")).size() > 0)
                    attemptTrueFalse("correct", useHints, useSolutionText, confidence, "");

                else if(Driver.driver.findElements(By.cssSelector("div[class='text-entry-input-wrapper visible_redactor_input_wrapper']")).size() > 0)
                    attemptTextEntry("correct", useHints, useSolutionText, confidence, "");

                else if(Driver.driver.findElements(By.cssSelector("div[class='text-entry-dropdown-wrapper visible_redactor_input_wrapper']")).size() > 0)
                    attemptTextDropDown("correct", useHints, useSolutionText, confidence, "");

                else if(Driver.driver.findElements(By.className("numeric_entry_student_title")).size() > 0)
                    attemptNumericEntryWithUnits("correct", useHints, useSolutionText, confidence, "");

                else if(Driver.driver.findElements(By.cssSelector("div[class='text-entry-input-wrapper visible_redactor_input_wrapper']")).size() > 0)
                    new AttemptDiagnosticQuestion().attemptAdvancedNumeric("correct", useHints, useSolutionText, confidence, "");

                else if(Driver.driver.findElements(By.cssSelector("div[class='symb-notation-math-edit-icon-preview']")).size() > 0)
                    attemptExpressionEvaluator("correct", useHints, useSolutionText, confidence, "");


                else if(Driver.driver.findElements(By.cssSelector("div[id='html-editor-non-draggable']")).size() > 0)
                    attemptEssayType("correct", useHints, useSolutionText, confidence, "");

                else if(Driver.driver.findElements(By.id("white-board-view-section")).size() > 0) {
                    Driver.driver.switchTo().frame(0);
                    attemptWriteBoardType("correct", useHints, useSolutionText, confidence, "");
                }

                Thread.sleep(2000);
                Thread.sleep(2000);
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='ls-static-practice-test-submit-button']")));
                Thread.sleep(2000);
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='ls-static-practice-test-next-button']")));
                Thread.sleep(2000);
                int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
                if(noticesize==1)

                {
                    String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                    if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
                    {
                        Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                        Thread.sleep(2000);
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='ls-static-practice-test-submit-button']")));
                        Thread.sleep(2000);
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("div[class='ls-static-practice-test-next-button']")));
                        Thread.sleep(2000);
                    }

                }
                timer=Driver.driver.findElements(By.cssSelector("span[id='timer-label']")).size();
            } //while loop ends
        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper attemptCorrect in class DiagnosticTest",e);
        }
    }
    public void attemptTrueFalse(String answerValue, boolean hint, boolean solution, String confidenceLevel, String dataIndex)
    {
        try
        {

            System.out.println("Attempting Truer false type");
            String correctchoice = "A";
            List<WebElement> answer = Driver.driver.findElements(By.className("true-false-student-answer-label"));
            for (WebElement answerchoice : answer) {
                if (answerchoice.getText().trim().equals(correctchoice)) {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerchoice);
                    System.out.println("Attempted");
                    break;
                }
            }
            String classAttribute=null;
            int confidenselevelIndex=Integer.parseInt(confidenceLevel);
            if(confidenceLevel != null && confidenselevelIndex>=1 && confidenselevelIndex<=4 )
            {
                if(confidenceLevel.equals("1"))
                    classAttribute="confidence-level-guess";
                if(confidenceLevel.equals("2"))
                    classAttribute="confidence-level-somewhat";
                if(confidenceLevel.equals("3"))
                    classAttribute="confidence-level-almost";
                if(confidenceLevel.equals("4"))
                    classAttribute="confidence-level-i-know-it";


                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//a[@id='"+confidenceLevel+"' and @class='"+classAttribute+"']")));
            }

        }
        catch (Exception e)
        {
            Assert.fail("Exception in apphelper attemptTrueFalse on class AttemptDiagnosticQuestion.", e);
        }
    }

    public void attemptMultipleChoice(String answerValue, boolean hint, boolean solution, String confidenceLevel, String dataIndex)
    {
        try
        {

            System.out.println("Attempting Multiple choice type");
            String correctchoice = "A";
            List<WebElement> answer = Driver.driver.findElements(By.className("choice-value"));
            for (WebElement answerchoice : answer) {
                if (answerchoice.getText().trim().equals(correctchoice)) {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerchoice);
                    System.out.println("Attempted");
                    break;
                }
            }
            String classAttribute=null;
            int confidenselevelIndex=Integer.parseInt(confidenceLevel);
            if(confidenceLevel != null && confidenselevelIndex>=1 && confidenselevelIndex<=4 )
            {
                if(confidenceLevel.equals("1"))
                    classAttribute="confidence-level-guess";
                if(confidenceLevel.equals("2"))
                    classAttribute="confidence-level-somewhat";
                if(confidenceLevel.equals("3"))
                    classAttribute="confidence-level-almost";
                if(confidenceLevel.equals("4"))
                    classAttribute="confidence-level-i-know-it";


                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//a[@id='"+confidenceLevel+"' and @class='"+classAttribute+"']")));
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in apphelper attemptMultipleChoice on class AttemptDiagnosticQuestion.", e);
        }
    }

    public void attemptMultipleSelection(String answerValue, boolean hint, boolean solution, String confidenceLevel, String dataIndex)
    {
        try{

            System.out.println("Attempting Multiple selection");
            String correctchoice = "A";
            String correctchoice1 = "B";
            List<WebElement> answer = Driver.driver.findElements(By.className("choice-value"));
            for (WebElement answerchoice : answer) {

                if (answerchoice.getText().trim().equals(correctchoice)) {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerchoice);
                    System.out.println("Attempted 1st choice");
                    break;
                }
            }
            List<WebElement> answer1 = Driver.driver.findElements(By.className("choice-value"));
            for (WebElement answerchoice1 : answer1) {

                if (answerchoice1.getText().trim().equals(correctchoice1)) {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerchoice1);
                    System.out.println("Attempted 2nd choice");
                    break;
                }
            }

            String classAttribute=null;
            int confidenselevelIndex=Integer.parseInt(confidenceLevel);
            if(confidenceLevel != null && confidenselevelIndex>=1 && confidenselevelIndex<=4 )
            {
                if(confidenceLevel.equals("1"))
                    classAttribute="confidence-level-guess";
                if(confidenceLevel.equals("2"))
                    classAttribute="confidence-level-somewhat";
                if(confidenceLevel.equals("3"))
                    classAttribute="confidence-level-almost";
                if(confidenceLevel.equals("4"))
                    classAttribute="confidence-level-i-know-it";


                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//a[@id='"+confidenceLevel+"' and @class='"+classAttribute+"']")));
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in apphelper attemptMultipleSelection on class AttemptDiagnosticQuestion.", e);
        }
    }

    public void attemptTextEntry(String answerValue, boolean hint, boolean solution, String confidenceLevel, String dataIndex)
    {
        try {

            System.out.println("Attempting text entry");
            String corrcttextanswer = "answer";
            Driver.driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).sendKeys(corrcttextanswer);
            String classAttribute=null;
            int confidenselevelIndex=Integer.parseInt(confidenceLevel);
            if(confidenceLevel != null && confidenselevelIndex>=1 && confidenselevelIndex<=4 )
            {
                if(confidenceLevel.equals("1"))
                    classAttribute="confidence-level-guess";
                if(confidenceLevel.equals("2"))
                    classAttribute="confidence-level-somewhat";
                if(confidenceLevel.equals("3"))
                    classAttribute="confidence-level-almost";
                if(confidenceLevel.equals("4"))
                    classAttribute="confidence-level-i-know-it";


                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//a[@id='"+confidenceLevel+"' and @class='"+classAttribute+"']")));
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in apphelper attemptTextEntry on class AttemptDiagnosticQuestion.", e);
        }
    }
    public void attemptTextDropDown(String answerValue, boolean hint, boolean solution, String confidenceLevel, String dataIndex)
    {
        try
        {
            System.out.println("Attempting text dropdown");
            new Select(Driver.driver.findElement(By.className("question-raw-content-dropdown"))).selectByIndex(1);
            String classAttribute=null;
            int confidenselevelIndex=Integer.parseInt(confidenceLevel);
            if(confidenceLevel != null && confidenselevelIndex>=1 && confidenselevelIndex<=4 )
            {
                if(confidenceLevel.equals("1"))
                    classAttribute="confidence-level-guess";
                if(confidenceLevel.equals("2"))
                    classAttribute="confidence-level-somewhat";
                if(confidenceLevel.equals("3"))
                    classAttribute="confidence-level-almost";
                if(confidenceLevel.equals("4"))
                    classAttribute="confidence-level-i-know-it";


                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//a[@id='"+confidenceLevel+"' and @class='"+classAttribute+"']")));
            }
        }

        catch (Exception e)
        {
            Assert.fail("Exception in apphelper attemptTextDropDown on class AttemptDiagnosticQuestion.", e);
        }
    }

    public void attemptNumericEntryWithUnits(String answerValue, boolean hint, boolean solution, String confidenceLevel, String dataIndex)
    {
        try
        {
            String corrcttextanswer = "10";
            String correctUnit = "feet";
            System.out.println("Attempting numeric entry with units");
            Driver.driver.findElement(By.className("numeric_entry_student_input")).sendKeys(corrcttextanswer);
            Thread.sleep(2000);
            new Select(Driver.driver.findElement(By.cssSelector("select[class='question-raw-content-dropdown numeric-unit-preview-selectbox']"))).selectByVisibleText(correctUnit);//select unit
            //Driver.driver.findElement(By.linkText(correctUnit)).click();//select unit
            String classAttribute=null;
            int confidenselevelIndex=Integer.parseInt(confidenceLevel);
            if(confidenceLevel != null && confidenselevelIndex>=1 && confidenselevelIndex<=4 )
            {
                if(confidenceLevel.equals("1"))
                    classAttribute="confidence-level-guess";
                if(confidenceLevel.equals("2"))
                    classAttribute="confidence-level-somewhat";
                if(confidenceLevel.equals("3"))
                    classAttribute="confidence-level-almost";
                if(confidenceLevel.equals("4"))
                    classAttribute="confidence-level-i-know-it";


                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//a[@id='"+confidenceLevel+"' and @class='"+classAttribute+"']")));
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in apphelper attemptNumericEntryWithUnits on class AttemptDiagnosticQuestion.", e);
        }
    }

    public void attemptAdvancedNumeric(String answerValue, boolean hint, boolean solution, String confidenceLevel, String dataIndex) {
        try {

            System.out.println("Attempting Advanced Numeric");
            String corrcttextanswer = "answer";
            Driver.driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).sendKeys(corrcttextanswer);
            String classAttribute=null;
            int confidenselevelIndex=Integer.parseInt(confidenceLevel);
            if(confidenceLevel != null && confidenselevelIndex>=1 && confidenselevelIndex<=4 )
            {
                if(confidenceLevel.equals("1"))
                    classAttribute="confidence-level-guess";
                if(confidenceLevel.equals("2"))
                    classAttribute="confidence-level-somewhat";
                if(confidenceLevel.equals("3"))
                    classAttribute="confidence-level-almost";
                if(confidenceLevel.equals("4"))
                    classAttribute="confidence-level-i-know-it";


                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//a[@id='"+confidenceLevel+"' and @class='"+classAttribute+"']")));
            }
        } catch (Exception e) {
            Assert.fail("Exception in apphelper attemptAdvancedNumeric on class AttemptDiagnosticQuestion.", e);
        }
    }

    public void attemptExpressionEvaluator(String answerValue, boolean hint, boolean solution, String confidenceLevel, String dataIndex) {
        try {

            System.out.println("Attempting Expression Evaluator");
            String corrcttextanswer = "5";
            new Click().clickByclassname("symb-notation-math-edit-icon-preview");//click on
            new QuestionCreate().enterValueInMathMLEditor("Square root","5");
            String classAttribute=null;
            int confidenselevelIndex=Integer.parseInt(confidenceLevel);
            if(confidenceLevel != null && confidenselevelIndex>=1 && confidenselevelIndex<=4 )
            {
                if(confidenceLevel.equals("1"))
                    classAttribute="confidence-level-guess";
                if(confidenceLevel.equals("2"))
                    classAttribute="confidence-level-somewhat";
                if(confidenceLevel.equals("3"))
                    classAttribute="confidence-level-almost";
                if(confidenceLevel.equals("4"))
                    classAttribute="confidence-level-i-know-it";


                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//a[@id='"+confidenceLevel+"' and @class='"+classAttribute+"']")));
            }

        } catch (Exception e) {
            Assert.fail("Exception in apphelper attemptExpressionEvaluator on class AttemptDiagnosticQuestion.", e);
        }
    }
    public void attemptEssayType(String answerValue, boolean hint, boolean solution, String confidenceLevel, String dataIndex) {
        try {

            String corrcttextanswer = "5";
            System.out.println("Attempting Essay type");
            Driver.driver.findElement(By.id("html-editor-non-draggable")).click();
            Driver.driver.findElement(By.id("html-editor-non-draggable")).sendKeys(corrcttextanswer);
            String classAttribute=null;
            int confidenselevelIndex=Integer.parseInt(confidenceLevel);
            if(confidenceLevel != null && confidenselevelIndex>=1 && confidenselevelIndex<=4 )
            {
                if(confidenceLevel.equals("1"))
                    classAttribute="confidence-level-guess";
                if(confidenceLevel.equals("2"))
                    classAttribute="confidence-level-somewhat";
                if(confidenceLevel.equals("3"))
                    classAttribute="confidence-level-almost";
                if(confidenceLevel.equals("4"))
                    classAttribute="confidence-level-i-know-it";


                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//a[@id='"+confidenceLevel+"' and @class='"+classAttribute+"']")));
            }

        } catch (Exception e) {
            Assert.fail("Exception in apphelper attemptExpressionEvaluator on class AttemptDiagnosticQuestion.", e);
        }
    }
    public void attemptWriteBoardType(String answerValue, boolean hint, boolean solution, String confidenceLevel, String dataIndex) {
        try {
            new Click().clickByid("square-btn");//click inside the board
            Thread.sleep(2000);
            Driver.driver.findElement(By.cssSelector("canvas[height='545']")).click();
            Driver.driver.switchTo().defaultContent();
            Driver.driver.findElement(By.xpath("/html/body")).click();
            Thread.sleep(2000);
            String classAttribute=null;
            int confidenselevelIndex=Integer.parseInt(confidenceLevel);
            if(confidenceLevel != null && confidenselevelIndex>=1 && confidenselevelIndex<=4 )
            {
                if(confidenceLevel.equals("1"))
                    classAttribute="confidence-level-guess";
                if(confidenceLevel.equals("2"))
                    classAttribute="confidence-level-somewhat";
                if(confidenceLevel.equals("3"))
                    classAttribute="confidence-level-almost";
                if(confidenceLevel.equals("4"))
                    classAttribute="confidence-level-i-know-it";

                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("//a[@id='"+confidenceLevel+"' and @class='"+classAttribute+"']")));
            }

        } catch (Exception e) {
            Assert.fail("Exception in apphelper attemptWriteBoardType on class AttemptDiagnosticQuestion.", e);
        }
    }
}
