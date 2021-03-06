package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;
/*
 * Created by Sumit on 9/25/2014.
 */

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

public class AttemptDiagnosticQuestion extends Driver {

    public void attemptTrueFalse(String answerValue, boolean hint, boolean solution, String confidenceLevel, String dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
        /*String corranswer= driver.findElement(By.id("show-debug-correct-answer-block")).getText();
        char correctanswer=corranswer.charAt(17);
        String correctchoice=Character.toString(correctanswer);*/
        System.out.println("Attempting Truer false type");
        String correctchoice = "A";
        List<WebElement> answer = driver.findElements(By.className("true-false-student-answer-label"));
        for (WebElement answerchoice : answer) {
            if (answerchoice.getText().trim().equals(correctchoice)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", answerchoice);
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


                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@id='"+confidenceLevel+"' and @class='"+classAttribute+"']")));
            }

    }
    catch (Exception e)
    {
        Assert.fail("Exception in apphelper attemptTrueFalse on class AttemptDiagnosticQuestion.", e);
    }
    }

    public void attemptMultipleChoice(String answerValue, boolean hint, boolean solution, String confidenceLevel, String dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
        /*String corranswer=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
					char correctanswer=corranswer.charAt(17);
					String correctchoice=Character.toString(correctanswer);*/
        System.out.println("Attempting Multiple choice type");
        String correctchoice = "A";
        new UIElement().waitAndFindElement(By.xpath("//span[text() = 'A']"));
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.xpath("//span[text() = 'A']")));
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


                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@id='"+confidenceLevel+"' and @class='"+classAttribute+"']")));
            }
    }
        catch (Exception e)
        {
            Assert.fail("Exception in apphelper attemptMultipleChoice on class AttemptDiagnosticQuestion.", e);
        }
    }

    public void attemptMultipleSelection(String answerValue, boolean hint, boolean solution, String confidenceLevel, String dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try{
        /*String corranswer=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
					char correctanswer;
					if(corranswer.charAt(17) == '(')
						correctanswer=corranswer.charAt(18);
					else
						correctanswer=corranswer.charAt(17);
					String correctchoice=Character.toString(correctanswer);
					String corranswer1=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
					char correctanswer1=corranswer1.charAt(22);

					String correctchoice1=Character.toString(correctanswer1);*/
        System.out.println("Attempting Multiple selection");
        String correctchoice = "A";
        String correctchoice1 = "B";
        List<WebElement> answer = driver.findElements(By.className("choice-value"));
        for (WebElement answerchoice : answer) {

            if (answerchoice.getText().trim().equals(correctchoice)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", answerchoice);
                System.out.println("Attempted 1st choice");
                break;
            }
        }
        List<WebElement> answer1 = driver.findElements(By.className("choice-value"));
        for (WebElement answerchoice1 : answer1) {

            if (answerchoice1.getText().trim().equals(correctchoice1)) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", answerchoice1);
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


                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@id='"+confidenceLevel+"' and @class='"+classAttribute+"']")));
            }
    }
        catch (Exception e)
        {
            Assert.fail("Exception in apphelper attemptMultipleSelection on class AttemptDiagnosticQuestion.", e);
        }
    }

    public void attemptTextEntry(String answerValue, boolean hint, boolean solution, String confidenceLevel, String dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
try {
        /*String corranswertext=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
					int lastindex=corranswertext.length();
					String corrcttextanswer=corranswertext.substring(16, lastindex);*/
    System.out.println("Attempting text entry");
    String corrcttextanswer = "answer";
    driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).sendKeys(corrcttextanswer);
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


        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@id='"+confidenceLevel+"' and @class='"+classAttribute+"']")));
    }
}
        catch (Exception e)
        {
            Assert.fail("Exception in apphelper attemptTextEntry on class AttemptDiagnosticQuestion.", e);
        }
    }
    public void attemptTextDropDown(String answerValue, boolean hint, boolean solution, String confidenceLevel, String dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            /*String corranswertext=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
					int lastindex=corranswertext.length();
					String corrcttextanswer=corranswertext.substring(16, lastindex);*/
            System.out.println("Attempting text dropdown");
            String corrcttextanswer = "ghi";
            //new Click().clickByclassname("question-raw-content-dropdown");
            new Select(driver.findElement(By.className("question-raw-content-dropdown"))).selectByIndex(1);
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


                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@id='"+confidenceLevel+"' and @class='"+classAttribute+"']")));
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in apphelper attemptTextDropDown on class AttemptDiagnosticQuestion.", e);
        }
    }

    public void attemptNumericEntryWithUnits(String answerValue, boolean hint, boolean solution, String confidenceLevel, String dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            /*String corranswertext=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
					int lastindex=corranswertext.length();
					String corrcttextanswer=corranswertext.substring(16, lastindex);*/
            String corrcttextanswer = "10";
            String correctUnit = "feet";
            System.out.println("Attempting numeric entry with units");
            driver.findElement(By.className("numeric_entry_student_input")).sendKeys(corrcttextanswer);
            Thread.sleep(2000);
            new Select(driver.findElement(By.cssSelector("select[class='question-raw-content-dropdown numeric-unit-preview-selectbox']"))).selectByVisibleText(correctUnit);//select unit
            //driver.findElement(By.linkText(correctUnit)).click();//select unit
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


                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@id='"+confidenceLevel+"' and @class='"+classAttribute+"']")));
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in apphelper attemptNumericEntryWithUnits on class AttemptDiagnosticQuestion.", e);
        }
    }

    public void attemptAdvancedNumeric(String answerValue, boolean hint, boolean solution, String confidenceLevel, String dataIndex) {
        try {
            WebDriver driver=Driver.getWebDriver();
        /*String corranswertext=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
					int lastindex=corranswertext.length();
					String corrcttextanswer=corranswertext.substring(16, lastindex);*/
            System.out.println("Attempting Advanced Numeric");
            String corrcttextanswer = "answer";
            driver.findElement(By.cssSelector("input[class='visible_redactor_input bg-color-white']")).sendKeys(corrcttextanswer);
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


                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@id='"+confidenceLevel+"' and @class='"+classAttribute+"']")));
            }
        } catch (Exception e) {
            Assert.fail("Exception in apphelper attemptAdvancedNumeric on class AttemptDiagnosticQuestion.", e);
        }
    }

    public void attemptExpressionEvaluator(String answerValue, boolean hint, boolean solution, String confidenceLevel, String dataIndex) {
        try {
            WebDriver driver=Driver.getWebDriver();
        /*String corranswertext=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
					int lastindex=corranswertext.length();
					String corrcttextanswer=corranswertext.substring(16, lastindex);*/
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


                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@id='"+confidenceLevel+"' and @class='"+classAttribute+"']")));
            }

        } catch (Exception e) {
            Assert.fail("Exception in apphelper attemptExpressionEvaluator on class AttemptDiagnosticQuestion.", e);
        }
    }
    public void attemptEssayType(String answerValue, boolean hint, boolean solution, String confidenceLevel, String dataIndex) {
        try {
            WebDriver driver=Driver.getWebDriver();
        /*String corranswertext=driver.findElement(By.id("show-debug-correct-answer-block")).getText();
					int lastindex=corranswertext.length();
					String corrcttextanswer=corranswertext.substring(16, lastindex);*/
            String corrcttextanswer = "5";
            System.out.println("Attempting Essay type");
            driver.findElement(By.id("html-editor-non-draggable")).click();
            driver.findElement(By.id("html-editor-non-draggable")).sendKeys(corrcttextanswer);
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


                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath("//a[@id='"+confidenceLevel+"' and @class='"+classAttribute+"']")));
            }

        } catch (Exception e) {
            Assert.fail("Exception in apphelper attemptExpressionEvaluator on class AttemptDiagnosticQuestion.", e);
        }
    }
}
