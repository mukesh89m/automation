package com.snapwiz.selenium.tests.staf.datacreation.apphelper;

//import java.sql.ResultSet;

import com.snapwiz.selenium.tests.staf.datacreation.Driver;
import com.snapwiz.selenium.tests.staf.datacreation.uihelper.Click;
import com.snapwiz.selenium.tests.staf.datacreation.uihelper.RandomNumber;
import com.snapwiz.selenium.tests.staf.datacreation.uihelper.TextSend;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
;


public class DiagnosticTest extends Driver {

	public void startTest(int index,int confidencelevel)
	{
		try
		{
			String confidence=Integer.toString(confidencelevel);
            Thread.sleep(3000);
			List<WebElement> allbegin=Driver.driver.findElements(By.cssSelector("img[title=\"Begin\"]"));			
			((JavascriptExecutor)Driver.driver).executeScript("arguments[0].click();",allbegin.get(index));//click on test ,thats we want to start
			Thread.sleep(2000);
			Driver.driver.findElement(By.cssSelector("a[id='"+confidence+"']")).click();//click on confidence level
			Thread.sleep(2000);
			Driver.driver.findElement(By.className("al-continue-to-diagnostic")).click();//click on continue button to start test
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper startTest in class DiagnosticTest",e);
		}
	}

    public void startTestLS(int confidenceLevelIndex, int diagNo)
    {
        try
        {
            if(diagNo == 1) {
                (new WebDriverWait(Driver.driver, 12))
                        .until(ExpectedConditions.presenceOfElementLocated(By.linkText("Diagnostic - Ch 3: Así es mi familia")));
                ((JavascriptExecutor) driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.linkText("Diagnostic - Ch 3: Así es mi familia")));
                //driver.findElement(By.linkText("Diagnostic - Ch 1: The Changing Face of Business")).click();
            }
            else if (diagNo == 2) {
                (new WebDriverWait(Driver.driver, 12))
                        .until(ExpectedConditions.presenceOfElementLocated(By.linkText("Diagnostic - Ch 2: The Chemical Foundation of Life")));
                driver.findElement(By.linkText("Diagnostic - Ch 2: The Chemical Foundation of Life")).click();
            }
            else if (diagNo == 3) {
                (new WebDriverWait(Driver.driver, 12))
                        .until(ExpectedConditions.presenceOfElementLocated(By.linkText("Diagnostic - Ch 3: Economic Challenges Facing Contemporary Business")));
                driver.findElement(By.linkText("Diagnostic - Ch 3: Economic Challenges Facing Contemporary Business")).click();
            }
            //new Click().clickBycssselector("a[data-type='diagnostic_assessment']");
            //driver.findElements(By.id("3")).get(diagNo-1).click();
            /*WebElement confidencelevel = 	(new WebDriverWait(Driver.driver, 4))
                    .until(ExpectedConditions.presenceOfElementLocated(By.id(Integer.toString(confidenceLevelIndex))));*/
            //if(confidencelevel != null)
            {
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElements(By.id(Integer.toString(confidenceLevelIndex))).get(diagNo-1));
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input.ls-assessment-continue-btn")));
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper startTest in class DiagnosticTest",e);
        }
    }

	public void quitTestAndGoToDashboard()
	{
		try
		{
			Thread.sleep(1000);
			Driver.driver.findElement(By.className("al-quit-diag-test-icon")).click();
			Thread.sleep(1000);
			Driver.driver.findElement(By.className("al-quit-diag-test")).click();
			Thread.sleep(1000);
		}
		catch(Exception e)
		{

			Assert.fail("Exception in quitting the diagnostic test",e);
		}
	}


    public void attemptProfile(int profileNumber, String testType, String productType,int userNo) {
        try {
            //Diagnostic Test
            if(profileNumber == 1 && testType.equalsIgnoreCase("Diagnostic")) {
                if(productType.equalsIgnoreCase("orion")) {
                    attemptCorrect(2, testType,userNo);
                }
                else if(productType.equalsIgnoreCase("lsadp")) {
                    attemptCorrectDiagLSAdp(24,testType,userNo);
                }
            }

            else if(profileNumber == 2 && testType.equalsIgnoreCase("Diagnostic")) {
                if(productType.equalsIgnoreCase("orion")) {
                    attemptInCorrect(0, testType,userNo);
                    //attemptCorrect(10, testType,userNo);
                }
                else if(productType.equalsIgnoreCase("lsadp")) {
                    attemptIncorrectDiagLSAdp(10,testType,userNo);
                    attemptCorrectLSAdp(14,testType,userNo);
                }
            }

            else if(profileNumber == 3 && testType.equalsIgnoreCase("Diagnostic")) {
                if(productType.equalsIgnoreCase("orion")) {
                    attemptInCorrect(18, testType,userNo);
                    attemptCorrect(6, testType,userNo);
                }
                else if(productType.equalsIgnoreCase("lsadp")) {
                    attemptIncorrectDiagLSAdp(6,testType,userNo);
                    attemptCorrectDiagLSAdp(18,testType,userNo);
                }
            }

            else if(profileNumber == 4 && testType.equalsIgnoreCase("Diagnostic")) {
                if(productType.equalsIgnoreCase("orion")) {
                    attemptInCorrect(6, testType,userNo);
                    attemptCorrect(18, testType,userNo);
                }
                else if(productType.equalsIgnoreCase("lsadp")) {
                    attemptIncorrectDiagLSAdp(2,testType,userNo);
                    attemptCorrectDiagLSAdp(22,testType,userNo);
                }

            }

            else if(profileNumber == 5 && testType.equalsIgnoreCase("Diagnostic")) {
                if(productType.equalsIgnoreCase("orion")) {
                    attemptInCorrect(20, testType,userNo);
                    attemptCorrect(4, testType,userNo);
                }
                else if(productType.equalsIgnoreCase("lsadp")) {
                    attemptIncorrectDiagLSAdp(15,testType,userNo);
                    attemptCorrectDiagLSAdp(9,testType,userNo);
                }
            }

            //Practice Test
            if(profileNumber == 1 && testType.equalsIgnoreCase("Practice")) {
                if(productType.equalsIgnoreCase("orion")) {
                    attemptCorrect(new RandomNumber().generateRandomNumber(20, 25), testType,userNo);
                }
                else if(productType.equalsIgnoreCase("lsadp")) {
                    attemptCorrectLSAdp(new RandomNumber().generateRandomNumber(20, 25),testType,userNo);
                }

            }

            else if(profileNumber == 2 && testType.equalsIgnoreCase("Practice")) {
                if(productType.equalsIgnoreCase("orion")) {
                    attemptInCorrect(new RandomNumber().generateRandomNumber(10, 15), testType,userNo);
                    attemptCorrect(new RandomNumber().generateRandomNumber(10, 15), testType,userNo);
                }
                else if(productType.equalsIgnoreCase("lsadp")) {
                    attemptIncorrectLSAdp(new RandomNumber().generateRandomNumber(5, 20), testType,userNo);
                  //  attemptCorrectLSAdp(new RandomNumber().generateRandomNumber(10, 20), testType,userNo);
                }
            }

            else if(profileNumber == 3 && testType.equalsIgnoreCase("Practice")) {
                if(productType.equalsIgnoreCase("orion")) {
                    attemptInCorrect(new RandomNumber().generateRandomNumber(10, 15), testType,userNo);
                    attemptCorrect(new RandomNumber().generateRandomNumber(5, 10), testType,userNo);
                }
                else if(productType.equalsIgnoreCase("lsadp")) {
                    attemptIncorrectLSAdp(new RandomNumber().generateRandomNumber(1, 10), testType,userNo);
                    attemptCorrectLSAdp(new RandomNumber().generateRandomNumber(10, 20), testType,userNo);
                }

            }

            else if(profileNumber == 4 && testType.equalsIgnoreCase("Practice")) {
                if(productType.equalsIgnoreCase("orion")) {
                    attemptInCorrect(new RandomNumber().generateRandomNumber(5, 10), testType,userNo);
                    attemptCorrect(new RandomNumber().generateRandomNumber(15, 20), testType,userNo);
                }
                else if(productType.equalsIgnoreCase("lsadp")) {
                    attemptIncorrectLSAdp(new RandomNumber().generateRandomNumber(12, 15), testType,userNo);
                    attemptCorrectLSAdp(new RandomNumber().generateRandomNumber(1, 5), testType,userNo);
                }
            }

            else if(profileNumber == 5 && testType.equalsIgnoreCase("Practice")) {
                if(productType.equalsIgnoreCase("orion")) {
                    attemptInCorrect(new RandomNumber().generateRandomNumber(18, 25), testType,userNo);
                    attemptCorrect(new RandomNumber().generateRandomNumber(2, 5), testType,userNo);
                }
                else if(productType.equalsIgnoreCase("lsadp")) {
                    //attemptIncorrectLSAdp(new RandomNumber().generateRandomNumber(18, 25), testType,userNo);
                    attemptCorrectLSAdp(new RandomNumber().generateRandomNumber(2, 5), testType,userNo);
                }
            }



        }
        catch (Exception e) {
            Assert.fail("Exception in app helper attemptProfileOne in class DiagnosticTest",e);
        }
    }

    public void attemptInCorrect(int noOfQuestions, String testType,int userNo)
    {
        try {
            int timer=1;
            int questionCount = 0;
            while(timer!=0) {
                boolean attempted = false;
                //True False and Single Select Question Type
                try {
                    driver.findElement(By.className("answer-choice-label-wrapper"));
                    String correctAnswer=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                    char correctAnswerOption=correctAnswer.charAt(17);
                    String correctChoice=Character.toString(correctAnswerOption);
                    String answerIndex="0";
                    if(correctChoice.equals("A")) {
                        answerIndex = "2";
                    }
                    else if (correctChoice.equals("B")) {
                        answerIndex = "1";
                    }
                    else if (correctChoice.equals("C")) {
                        answerIndex = "1";
                    }
                    else if (correctChoice.equals("D")) {
                        answerIndex = "1";
                    }
                    driver.findElement(By.xpath("//div[@class='al-diag-test-answerChoices-content-wrapper']/div["+answerIndex+"]/div[1]/label")).click();
                    attempted = true;

                }
                catch (Exception e) {

                }
                //Multiple Selection Question Type
                if(attempted ==false) {
                    try {
                        driver.findElement(By.className("multiple-selection-answer-choice-label-wrapper"));
                        String correctAnswer1 = driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        char correctOption1;
                        if (correctAnswer1.charAt(17) == '(') {
                            correctOption1 = correctAnswer1.charAt(18);
                        } else {
                            correctOption1 = correctAnswer1.charAt(17);
                        }

                        String correctChoice1 = Character.toString(correctOption1); //Answer 1

                        String correctAnswer2 = driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        char correctOption2 = correctAnswer2.charAt(22);

                        String correctChoice2 = Character.toString(correctOption2); //Answer 2

                        String answerIndex1 = "0",answerIndex2 = "0";

                        if((correctChoice1.equals("A") && correctChoice2.equals("B")) || (correctChoice1.equals("B") && correctChoice2.equals("A")) ) {
                        	answerIndex1="3"; answerIndex2 = "4";
                        }
                        else if(correctChoice1.equals("A") && correctChoice2.equals("C") || (correctChoice1.equals("C") && correctChoice2.equals("A"))) {
                        	answerIndex1="2"; answerIndex2 = "4";
                        }
                        else if(correctChoice1.equals("A") && correctChoice2.equals("D") || (correctChoice1.equals("D") && correctChoice2.equals("A"))) {
                             answerIndex1="2"; answerIndex2 = "3";
                        }
                        else if(correctChoice1.equals("A") && correctChoice2.equals("E") || (correctChoice1.equals("E") && correctChoice2.equals("A"))) {
                            answerIndex1="2"; answerIndex2 = "3";
                        }
                        else if(correctChoice1.equals("A") && correctChoice2.equals("F") || (correctChoice1.equals("F") && correctChoice2.equals("A"))) {
                            answerIndex1="2"; answerIndex2 = "3";
                        }


                        else if((correctChoice1.equals("B") && correctChoice2.equals("C")) || (correctChoice1.equals("C") && correctChoice2.equals("B")) ) {
                        	answerIndex1="1"; answerIndex2 = "4";
                        }
                        else if(correctChoice1.equals("B") && correctChoice2.equals("D") || (correctChoice1.equals("D") && correctChoice2.equals("B"))) {
                        	answerIndex1="1"; answerIndex2 = "3";
                        }
                        else if(correctChoice1.equals("B") && correctChoice2.equals("E") || (correctChoice1.equals("E") && correctChoice2.equals("B"))) {
                            answerIndex1="1"; answerIndex2 = "3";
                        }
                        else if(correctChoice1.equals("B") && correctChoice2.equals("F") || (correctChoice1.equals("F") && correctChoice2.equals("B"))) {
                            answerIndex1="1"; answerIndex2 = "3";
                        }

                        else if((correctChoice1.equals("C") && correctChoice2.equals("D")) || (correctChoice1.equals("D") && correctChoice2.equals("C")) ) {
                            answerIndex1="1"; answerIndex2 = "2";
                        }
                        else if(correctChoice1.equals("C") && correctChoice2.equals("E") || (correctChoice1.equals("E") && correctChoice2.equals("C"))) {
                            answerIndex1="1"; answerIndex2 = "2";
                        }
                        else if(correctChoice1.equals("C") && correctChoice2.equals("F") || (correctChoice1.equals("F") && correctChoice2.equals("C"))) {
                            answerIndex1="1"; answerIndex2 = "2";
                        }

                        else if((correctChoice1.equals("D") && correctChoice2.equals("E")) || (correctChoice1.equals("E") && correctChoice2.equals("D"))) {
                        	answerIndex1="1"; answerIndex2 = "2";
                        }
                        else if(correctChoice1.equals("D") && correctChoice2.equals("F") || (correctChoice1.equals("F") && correctChoice2.equals("D"))) {
                        	answerIndex1="1"; answerIndex2 = "2";
                        }

                        else if((correctChoice1.equals("E") && correctChoice2.equals("F")) || (correctChoice1.equals("F") && correctChoice2.equals("E")) ) {
                           answerIndex1="1"; answerIndex2 = "2";
                        }

                        driver.findElement(By.xpath("//div[@class='al-diag-test-answerChoices-content-wrapper']/div[" + answerIndex1 + "]/div[1]/label")).click();
                        driver.findElement(By.xpath("//div[@class='al-diag-test-answerChoices-content-wrapper']/div[" + answerIndex2 + "]/div[1]/label")).click();
                        attempted = true;

                    } catch (Exception e) {

                    }
                }
                //Drop Down Question Type
                if(attempted == false) {
                    try {
                        driver.findElement(By.className("sbHolder"));
                        String correctAnswerText=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        String correctAnswer=correctAnswerText.substring(16, correctAnswerText.length());
                        Driver.driver.findElement(By.className("sbToggle")).click();
                        Thread.sleep(1000);
                        /*Driver.driver.findElement(By.linkText(correctAnswer)).click();*/

                        String values = Driver.driver.findElement(By.cssSelector("ul[class='sbOptions']")).getText();
                        String [] val = values.split("\n");
                        for(String element:val)
                        {
                            if(!element.equals(correctAnswer))
                            {

                                Driver.driver.findElement(By.linkText(element)).click();
                                break;
                            }
                        }

                        attempted = true;
                    } catch (Exception e) {

                    }
                }
                //Text Entry Question Type
                if(attempted == false) {
                    try {
                        driver.findElement(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']"));
                        Driver.driver.findElement(By.cssSelector("input[id='1']")).sendKeys("deliberately");
                        attempted = true;
                    }
                    catch (Exception e) {

                    }
                }
                try {
                 //   driver.findElement(By.cssSelector("a[id='" + new RandomNumber().generateRandomNumber(1, 4) + "']")).click();

                        if (userNo % 2 == 0) {
                            driver.findElement(By.cssSelector("a[id='3']")).click();
                        } else {
                            driver.findElement(By.cssSelector("a[id='4']")).click();
                        }
                   // driver.findElement(By.cssSelector("a[id='" + new RandomNumber().generateRandomNumber(1, 4) + "']")).click();
                  /*  if(userNo <= 60 || userNo > 70) {
                        if (userNo % 2 == 0) {
                            driver.findElement(By.cssSelector("a[id='3']")).click();
                        } else {
                            driver.findElement(By.cssSelector("a[id='4']")).click();
                        }
                    }
                    else {
                        driver.findElement(By.cssSelector("a[id='" + new RandomNumber().generateRandomNumber(1, 4) + "']")).click();
                    }*/

                }
                catch (Exception e) {

                }
                if(testType.equals("Diagnostic")) {
                    try {
                        (new WebDriverWait(Driver.driver, 2))
                                .until(ExpectedConditions.presenceOfElementLocated(By.className("al-diag-test-submit-button"))).click();
                    }
                    catch (Exception e) {

                    }
                }
                else {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                    try {
                        Driver.driver.findElement(By.className("al-notification-message-body"));

                        String notice = Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                        if (notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering.")) {
                            Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                            Driver.driver.findElement(By.className("al-practice-test-submit-button")).click();
                            Thread.sleep(1000);
                        }


                    }
                    catch (Exception e) {

                    }
                    Thread.sleep(500);
                    try {
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")));
                    }
                    catch (Exception e) {

                    }

                }

                Thread.sleep(1000);
                questionCount++;
                if(noOfQuestions == 0) {
                try {
                    Driver.driver.findElement(By.cssSelector("span[id='timer-label']"));
                }
                catch (Exception e) {
                    timer = 0;
                    }
                }
                else {
                        if(noOfQuestions == questionCount) {
                            timer = 0;
                        }
                }

            }
        }
        catch (Exception e) {
            Assert.fail("Exception while attempting the diagnostic question as correct",e);
        }
    }

    public void attemptCorrect(int noOfQuestions, String testType,int userNo)
    {
        try {
            int timer=1;
            int questionCount = 0;
            while(timer!=0) {
                boolean attempted = false;
                //True False and Single Select Question Type
                try {
                    driver.findElement(By.className("answer-choice-label-wrapper"));
                    String correctAnswer=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                    char correctAnswerOption=correctAnswer.charAt(17);
                    String correctChoice=Character.toString(correctAnswerOption);
                    String answerIndex="0";
                    if(correctChoice.equals("A")) {
                        answerIndex = "1";
                    }
                    else if (correctChoice.equals("B")) {
                        answerIndex = "2";
                    }
                    else if (correctChoice.equals("C")) {
                        answerIndex = "3";
                    }
                    else if (correctChoice.equals("D")) {
                        answerIndex = "4";
                    }
                    driver.findElement(By.xpath("//div[@class='al-diag-test-answerChoices-content-wrapper']/div["+answerIndex+"]/div[1]/label")).click();
                    attempted = true;

                }
                catch (Exception e) {

                }
                //Multiple Selection Question Type
                if(attempted ==false) {
                    try {
                        driver.findElement(By.className("multiple-selection-answer-choice-label-wrapper"));
                        String correctAnswer1 = driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        char correctOption1;
                        if (correctAnswer1.charAt(17) == '(') {
                            correctOption1 = correctAnswer1.charAt(18);
                        } else {
                            correctOption1 = correctAnswer1.charAt(17);
                        }

                        String correctChoice1 = Character.toString(correctOption1); //Answer 1

                        String correctAnswer2 = driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        char correctOption2 = correctAnswer2.charAt(22);

                        String correctChoice2 = Character.toString(correctOption2); //Answer 2

                        String answerIndex = "0";
                        if (correctChoice1.equals("A")) {
                            answerIndex = "1";
                        } else if (correctChoice1.equals("B")) {
                            answerIndex = "2";
                        } else if (correctChoice1.equals("C")) {
                            answerIndex = "3";
                        } else if (correctChoice1.equals("D")) {
                            answerIndex = "4";
                        } else if (correctChoice1.equals("E")) {
                            answerIndex = "5";
                        } else if (correctChoice1.equals("F")) {
                            answerIndex = "6";
                        }
                        driver.findElement(By.xpath("//div[@class='al-diag-test-answerChoices-content-wrapper']/div[" + answerIndex + "]/div[1]/label")).click();

                        answerIndex = "0";

                        if (correctChoice2.equals("A")) {
                            answerIndex = "1";
                        } else if (correctChoice2.equals("B")) {
                            answerIndex = "2";
                        } else if (correctChoice2.equals("C")) {
                            answerIndex = "3";
                        } else if (correctChoice2.equals("D")) {
                            answerIndex = "4";
                        } else if (correctChoice2.equals("E")) {
                            answerIndex = "5";
                        } else if (correctChoice2.equals("F")) {
                            answerIndex = "6";
                        }
                        driver.findElement(By.xpath("//div[@class='al-diag-test-answerChoices-content-wrapper']/div[" + answerIndex + "]/div[1]/label")).click();
                        attempted = true;

                    } catch (Exception e) {

                    }
                }
                //Drop Down Question Type
                if(attempted == false) {
                    try {
                        driver.findElement(By.className("sbHolder"));
                        String correctAnswerText=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        String correctAnswer=correctAnswerText.substring(16, correctAnswerText.length());
                        Driver.driver.findElement(By.className("sbToggle")).click();
                        Thread.sleep(1000);
                        Driver.driver.findElement(By.linkText(correctAnswer)).click();
                        attempted = true;
                    } catch (Exception e) {

                    }
                }
                //Text Entry Question Type
                if(attempted == false) {
                    try {
                        driver.findElement(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']"));
                        String correctAnswerText=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        String correctAnswer=correctAnswerText.substring(16, correctAnswerText.length());
                        Driver.driver.findElement(By.cssSelector("input[id='1']")).sendKeys(correctAnswer);
                        attempted = true;
                    }
                    catch (Exception e) {

                    }
                }
                try {
                  //  driver.findElement(By.cssSelector("a[id='" + new RandomNumber().generateRandomNumber(1, 4) + "']")).click();
                        if (userNo % 2 == 0) {
                            driver.findElement(By.cssSelector("a[id='3']")).click();
                        } else {
                            driver.findElement(By.cssSelector("a[id='4']")).click();
                        }
                  //  driver.findElement(By.cssSelector("a[id='" + new RandomNumber().generateRandomNumber(1, 4) + "']")).click();
                   /* if(userNo <= 60 || userNo > 70) {
                        if (userNo % 2 == 0) {
                            driver.findElement(By.cssSelector("a[id='3']")).click();
                        } else {
                            driver.findElement(By.cssSelector("a[id='4']")).click();
                        }
                    }
                    else {
                        driver.findElement(By.cssSelector("a[id='" + new RandomNumber().generateRandomNumber(1, 4) + "']")).click();
                    }
*/
                }
                catch (Exception e) {
                    
                }
                if(testType.equals("Diagnostic")) {
                    try {
                        (new WebDriverWait(Driver.driver, 2))
                                .until(ExpectedConditions.presenceOfElementLocated(By.className("al-diag-test-submit-button"))).click();
                    }
                    catch (Exception e) {

                    }
                }
                else {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                    try {
                        Driver.driver.findElement(By.className("al-notification-message-body"));

                        String notice = Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                        if (notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering.")) {
                            Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                            Driver.driver.findElement(By.className("al-practice-test-submit-button")).click();
                            Thread.sleep(1000);
                        }


                    }
                    catch (Exception e) {

                    }
                    Thread.sleep(500);
                    try {
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")));
                    }
                    catch (Exception e) {

                    }

                }
                Thread.sleep(1000);
                questionCount++;
                if(noOfQuestions == 0) {
                    try {
                        Driver.driver.findElement(By.cssSelector("span[id='timer-label']"));
                    } catch (Exception e) {
                        timer = 0;
                    }
                }
                else {
                    if(noOfQuestions == questionCount) {
                        timer = 0;
                    }
                }



            }
        }
        catch (Exception e) {
            Assert.fail("Exception while attempting the diagnostic question as correct",e);
        }
    }

    public void attemptIncorrectLSAdp(int noOfQuestions, String testType, int userNo)
    {
        try {
            int timer=1;
            int questionCount = 0;
            while(timer!=0) {
                boolean attempted = false;
                //True False and Single Select Question Type
                try {
                    driver.findElement(By.className("preview-single-selection-answer-choices"));
                    String correctChoice=Driver.driver.findElement(By.id("sw-question-correct-answers")).getText();
                    String answerIndex="0";
                    if(correctChoice.equals("A")) {
                        answerIndex = "2";
                    }
                    else if (correctChoice.equals("B")) {
                        answerIndex = "1";
                    }
                    else if (correctChoice.equals("C")) {
                        answerIndex = "1";
                    }
                    else if (correctChoice.equals("D")) {
                        answerIndex = "1";
                    }
                    if(!answerIndex.equals("0")) {
                        try {
                            driver.findElement(By.xpath("//div[@class='preview-single-selection-answer-choices']/div[" + answerIndex + "]/div[1]/span")).click();
                        }
                        catch (Exception e) {
                            System.out.println("Not a single select answer type. Trying for true false");
                            driver.findElement(By.xpath("//div[@class='true-false-answer-choices']/div[" + answerIndex + "]/table/tbody/tr/td[2]/div/span")).click();

                        }

                    }
                    attempted = true;

                }
                catch (Exception e) {

                }
                //True False
                if(attempted == false) {
                    try {

                        driver.findElement(By.className("true-false-answer-choices"));
                        String correctChoice=Driver.driver.findElement(By.id("sw-question-correct-answers")).getText();
                        String answerIndex="0";
                        if(correctChoice.equals("A")) {
                            answerIndex = "2";
                        }
                        else if (correctChoice.equals("B")) {
                            answerIndex = "1";
                        }
                        if(!answerIndex.equals("0")) {
                            driver.findElement(By.xpath("//div[@class='true-false-answer-choices']/div[" + answerIndex + "]/table/tbody/tr/td[2]/div/span")).click();
                        }
                        attempted = true;
                    }
                    catch (Exception e) {

                    }
                }

                //Multiple Selection Question Type
               /* if(attempted ==false) {
                    try {
                        driver.findElement(By.className("multiple-selection-answer-choice-label-wrapper"));
                        String correctAnswer1 = driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        char correctOption1;
                        if (correctAnswer1.charAt(17) == '(') {
                            correctOption1 = correctAnswer1.charAt(18);
                        } else {
                            correctOption1 = correctAnswer1.charAt(17);
                        }

                        String correctChoice1 = Character.toString(correctOption1); //Answer 1

                        String correctAnswer2 = driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        char correctOption2 = correctAnswer2.charAt(22);

                        String correctChoice2 = Character.toString(correctOption2); //Answer 2

                        String answerIndex = "0";
                        if (correctChoice1.equals("A")) {
                            answerIndex = "1";
                        } else if (correctChoice1.equals("B")) {
                            answerIndex = "2";
                        } else if (correctChoice1.equals("C")) {
                            answerIndex = "3";
                        } else if (correctChoice1.equals("D")) {
                            answerIndex = "4";
                        } else if (correctChoice1.equals("E")) {
                            answerIndex = "5";
                        } else if (correctChoice1.equals("F")) {
                            answerIndex = "6";
                        }
                        driver.findElement(By.xpath("//div[@class='al-diag-test-answerChoices-content-wrapper']/div[" + answerIndex + "]/div[1]/label")).click();

                        answerIndex = "0";

                        if (correctChoice2.equals("A")) {
                            answerIndex = "1";
                        } else if (correctChoice2.equals("B")) {
                            answerIndex = "2";
                        } else if (correctChoice2.equals("C")) {
                            answerIndex = "3";
                        } else if (correctChoice2.equals("D")) {
                            answerIndex = "4";
                        } else if (correctChoice2.equals("E")) {
                            answerIndex = "5";
                        } else if (correctChoice2.equals("F")) {
                            answerIndex = "6";
                        }
                        driver.findElement(By.xpath("//div[@class='al-diag-test-answerChoices-content-wrapper']/div[" + answerIndex + "]/div[1]/label")).click();
                        attempted = true;

                    } catch (Exception e) {

                    }
                }*/
              /*  //Drop Down Question Type
                if(attempted == false) {
                    try {
                        driver.findElement(By.className("question-raw-content-dropdown"));
                        String correctAnswerText=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        String correctAnswer=correctAnswerText.substring(16, correctAnswerText.length());
                        Select dropDown = new Select(Driver.driver.findElement(By.className("question-raw-content-dropdown")));
                        dropDown.selectByVisibleText(correctAnswer);
                        *//*Driver.driver.findElement(By.className("question-raw-content-dropdown")).click();
                        Thread.sleep(1000);
                        Driver.driver.findElement(By.linkText(correctAnswer)).click();*//*
                        attempted = true;
                    } catch (Exception e) {

                    }
                }
                //Text Entry Question Type
                if(attempted == false) {
                    try {
                        driver.findElement(By.cssSelector("input[class='text-entry-input-wrapper  visible_redactor_input_wrapper']"));
                        String correctAnswerText=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        String correctAnswer=correctAnswerText.substring(16, correctAnswerText.length());
                        Driver.driver.findElement(By.cssSelector("input[class='text-entry-input-wrapper  visible_redactor_input_wrapper']")).sendKeys(correctAnswer);
                        attempted = true;
                    }
                    catch (Exception e) {

                    }
                }*/
                try {
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-guess")));
                   /* if(userNo % 2 == 0) {
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-almost")));
                    }
                    else {
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-i-know-it")));
                    }*/
                    //if(userNo < 21) {
                      /*  if (new RandomNumber().generateRandomNumber(1, 5) == 1) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-guess")));
                        } else if (new RandomNumber().generateRandomNumber(1, 5) == 2) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-somewhat")));
                        } else if (new RandomNumber().generateRandomNumber(1, 5) == 3) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-almost")));
                        } else if (new RandomNumber().generateRandomNumber(1, 5) == 4) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-i-know-it")));
                        }*/
                    /*}
                    else {
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-i-know-it")));
                    }*/


                    //driver.findElement(By.cssSelector("a[id='" + new RandomNumber().generateRandomNumber(1, 5) + "']")).click();
                }
                catch (Exception e) {
                    System.out.println("Not able to select confidence level "+e );
                }
                if(testType.equals("Diagnostic")) {
                    try {
                        (new WebDriverWait(Driver.driver, 2))
                                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='button']"))).click();
                        if(Driver.driver.findElement(By.className("al-diag-chapter-details")).getText().contains("(1 of 20)")) {

                            Driver.driver.findElement(By.className("al-notification-message-body"));
                            Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                            Driver.driver.findElement(By.cssSelector("input[type='button']")).click();
                        }


                    }
                    catch (Exception e) {

                    }
                }
                else {
                    try {
                        (new WebDriverWait(Driver.driver, 2))
                                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='button']"))).click();

                        (new WebDriverWait(Driver.driver, 2))
                                .until(ExpectedConditions.presenceOfElementLocated(By.className("al-notification-message-body")));
                        //Driver.driver.findElement(By.className("al-notification-message-body"));
                        Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                        (new WebDriverWait(Driver.driver, 2))
                                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='button']"))).click();

                    }
                    catch (Exception e) {

                    }

                    try {
                        (new WebDriverWait(Driver.driver, 2))
                                .until(ExpectedConditions.presenceOfElementLocated(By.id("next-practice-question-button"))).click(); //Clicking Next Question Button
                    }
                    catch (Exception e) {

                    }


                }
                Thread.sleep(1000);
                questionCount++;
                if(noOfQuestions == 0) {
                    try {
                        Driver.driver.findElement(By.cssSelector("span[id='timer-label']"));
                    } catch (Exception e) {
                        timer = 0;
                    }
                }
                else {
                    if(noOfQuestions == questionCount) {
                        timer = 0;
                    }
                }



            }
        }
        catch (Exception e) {
            Assert.fail("Exception while attempting the diagnostic question as correct",e);
        }
    }


    public void attemptCorrectLSAdp(int noOfQuestions, String testType, int userNo)
    {
        try {
            int timer=1;
            int questionCount = 0;
            while(timer!=0) {
                boolean attempted = false;
                //True False and Single Select Question Type
                try {
                    driver.findElement(By.className("preview-single-selection-answer-choices"));
                    String correctChoice=Driver.driver.findElement(By.id("sw-question-correct-answers")).getText();
                    String answerIndex="0";
                    if(correctChoice.equals("A")) {
                        answerIndex = "1";
                    }
                    else if (correctChoice.equals("B")) {
                        answerIndex = "2";
                    }
                    else if (correctChoice.equals("C")) {
                        answerIndex = "3";
                    }
                    else if (correctChoice.equals("D")) {
                        answerIndex = "4";
                    }
                    if(!answerIndex.equals("0")) {
                        try {
                            driver.findElement(By.xpath("//div[@class='preview-single-selection-answer-choices']/div[" + answerIndex + "]/div[1]/span")).click();
                        }
                        catch (Exception e) {
                            System.out.println("Not a single select answer type. Trying for true false");
                            driver.findElement(By.xpath("//div[@class='true-false-answer-choices']/div[" + answerIndex + "]/table/tbody/tr/td[2]/div/span")).click();

                        }

                    }
                    attempted = true;

                }
                catch (Exception e) {

                }
                //True False
                if(attempted == false) {
                    try {

                        driver.findElement(By.className("true-false-answer-choices"));
                        String correctChoice=Driver.driver.findElement(By.id("sw-question-correct-answers")).getText();
                        String answerIndex="0";
                        if(correctChoice.equals("A")) {
                            answerIndex = "1";
                        }
                        else if (correctChoice.equals("B")) {
                            answerIndex = "2";
                        }
                        if(!answerIndex.equals("0")) {
                                driver.findElement(By.xpath("//div[@class='true-false-answer-choices']/div[" + answerIndex + "]/table/tbody/tr/td[2]/div/span")).click();
                        }
                        attempted = true;
                    }
                    catch (Exception e) {

                    }
                }

                //Multiple Selection Question Type
               /* if(attempted ==false) {
                    try {
                        driver.findElement(By.className("multiple-selection-answer-choice-label-wrapper"));
                        String correctAnswer1 = driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        char correctOption1;
                        if (correctAnswer1.charAt(17) == '(') {
                            correctOption1 = correctAnswer1.charAt(18);
                        } else {
                            correctOption1 = correctAnswer1.charAt(17);
                        }

                        String correctChoice1 = Character.toString(correctOption1); //Answer 1

                        String correctAnswer2 = driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        char correctOption2 = correctAnswer2.charAt(22);

                        String correctChoice2 = Character.toString(correctOption2); //Answer 2

                        String answerIndex = "0";
                        if (correctChoice1.equals("A")) {
                            answerIndex = "1";
                        } else if (correctChoice1.equals("B")) {
                            answerIndex = "2";
                        } else if (correctChoice1.equals("C")) {
                            answerIndex = "3";
                        } else if (correctChoice1.equals("D")) {
                            answerIndex = "4";
                        } else if (correctChoice1.equals("E")) {
                            answerIndex = "5";
                        } else if (correctChoice1.equals("F")) {
                            answerIndex = "6";
                        }
                        driver.findElement(By.xpath("//div[@class='al-diag-test-answerChoices-content-wrapper']/div[" + answerIndex + "]/div[1]/label")).click();

                        answerIndex = "0";

                        if (correctChoice2.equals("A")) {
                            answerIndex = "1";
                        } else if (correctChoice2.equals("B")) {
                            answerIndex = "2";
                        } else if (correctChoice2.equals("C")) {
                            answerIndex = "3";
                        } else if (correctChoice2.equals("D")) {
                            answerIndex = "4";
                        } else if (correctChoice2.equals("E")) {
                            answerIndex = "5";
                        } else if (correctChoice2.equals("F")) {
                            answerIndex = "6";
                        }
                        driver.findElement(By.xpath("//div[@class='al-diag-test-answerChoices-content-wrapper']/div[" + answerIndex + "]/div[1]/label")).click();
                        attempted = true;

                    } catch (Exception e) {

                    }
                }*/
              /*  //Drop Down Question Type
                if(attempted == false) {
                    try {
                        driver.findElement(By.className("question-raw-content-dropdown"));
                        String correctAnswerText=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        String correctAnswer=correctAnswerText.substring(16, correctAnswerText.length());
                        Select dropDown = new Select(Driver.driver.findElement(By.className("question-raw-content-dropdown")));
                        dropDown.selectByVisibleText(correctAnswer);
                        *//*Driver.driver.findElement(By.className("question-raw-content-dropdown")).click();
                        Thread.sleep(1000);
                        Driver.driver.findElement(By.linkText(correctAnswer)).click();*//*
                        attempted = true;
                    } catch (Exception e) {

                    }
                }
                //Text Entry Question Type
                if(attempted == false) {
                    try {
                        driver.findElement(By.cssSelector("input[class='text-entry-input-wrapper  visible_redactor_input_wrapper']"));
                        String correctAnswerText=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                        String correctAnswer=correctAnswerText.substring(16, correctAnswerText.length());
                        Driver.driver.findElement(By.cssSelector("input[class='text-entry-input-wrapper  visible_redactor_input_wrapper']")).sendKeys(correctAnswer);
                        attempted = true;
                    }
                    catch (Exception e) {

                    }
                }*/
                try {
                    if(userNo % 2 == 0) {
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-almost")));
                    }
                    else {
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-i-know-it")));
                    }
                    //if(userNo < 21 ) {
                       /* if (new RandomNumber().generateRandomNumber(1, 5) == 1) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-guess")));
                        } else if (new RandomNumber().generateRandomNumber(1, 5) == 2) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-somewhat")));
                        } else if (new RandomNumber().generateRandomNumber(1, 5) == 3) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-almost")));
                        } else if (new RandomNumber().generateRandomNumber(1, 5) == 4) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-i-know-it")));
                        }*/
                    /*}
                    else {
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-i-know-it")));
                    }*/


                    //driver.findElement(By.cssSelector("a[id='" + new RandomNumber().generateRandomNumber(1, 5) + "']")).click();
                }
                catch (Exception e) {
                    System.out.println("Not able to select confidence level "+e );
                }
                if(testType.equals("Diagnostic")) {
                    try {
                        (new WebDriverWait(Driver.driver, 2))
                                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='button']")));
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type='button']")));
                        if(Driver.driver.findElement(By.className("al-diag-chapter-details")).getText().contains("(1 of 20)")) {

                            Driver.driver.findElement(By.className("al-notification-message-body"));
                            Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                            Driver.driver.findElement(By.cssSelector("input[type='button']")).click();
                        }


                    }
                    catch (Exception e) {

                    }
                }
                else {
                    try {
                    (new WebDriverWait(Driver.driver, 2))
                            .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='button']"))).click();

                        (new WebDriverWait(Driver.driver, 2))
                                .until(ExpectedConditions.presenceOfElementLocated(By.className("al-notification-message-body")));
                            //Driver.driver.findElement(By.className("al-notification-message-body"));
                            Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                            (new WebDriverWait(Driver.driver, 2))
                                    .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='button']"))).click();

                    }
                    catch (Exception e) {

                    }

                    try {
                        (new WebDriverWait(Driver.driver, 2))
                                .until(ExpectedConditions.presenceOfElementLocated(By.id("next-practice-question-button"))).click(); //Clicking Next Question Button
                    }
                    catch (Exception e) {

                    }


                }
                Thread.sleep(1000);
                questionCount++;
                if(noOfQuestions == 0) {
                    try {
                        Driver.driver.findElement(By.cssSelector("span[id='timer-label']"));
                    } catch (Exception e) {
                        timer = 0;
                    }
                }
                else {
                    if(noOfQuestions == questionCount) {
                        timer = 0;
                    }
                }



            }
        }
        catch (Exception e) {
            Assert.fail("Exception while attempting the diagnostic question as correct",e);
        }
    }



    public void attemptQuestion()
    {
        try
        {
            int timer=1;

            while(timer!=0)
            {
                try {
                    String corranswer=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                    char correctanswer=corranswer.charAt(17);
                    String correctchoice=Character.toString(correctanswer);

                    if(correctchoice.equals("A") || correctchoice.equals("B") || correctchoice.equals("C") || correctchoice.equals("D")) {
                        int random = new RandomNumber().generateRandomNumber(0, 100);
                        if(random  > 70) {
                            if(correctchoice.equals("A") || correctchoice.equals("C") || correctchoice.equals("D")) {
                                correctchoice = "B";
                            }
                                else
                                correctchoice = "A";

                        }
                        List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
                        for (WebElement answerchoice : answer) {
                            if (answerchoice.getText().trim().equals(correctchoice)) {
                                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerchoice);
                                break;
                            }
                        }
                    }
                    else {
                        Driver.driver.findElement(By.className("qtn-label")).click();
                    }

                }
                catch (Exception e)  {

                }
                int confidencelevel = new RandomNumber().generateRandomNumber(0, 5);
                if(confidencelevel!=0)
                {
                    Driver.driver.findElement(By.cssSelector("a[id='"+confidencelevel+"']")).click();//click on confidence level
                }
                (new WebDriverWait(Driver.driver, 2))
                        .until(ExpectedConditions.presenceOfElementLocated(By.className("al-diag-test-submit-button"))).click();

                Thread.sleep(1000);
                try {
                    Driver.driver.findElement(By.cssSelector("span[id='timer-label']"));
                }
                catch (Exception e) {
                    timer = 0;
                }
            }
        }
        catch(Exception e)
        {

            Assert.fail("Exception in app helper attemptRandomly in class DiagnosticTest",e);
        }
    }





	public void attemptSkip(int confidencelevel)
	{
		try
		{
			String confidence=Integer.toString(confidencelevel);
			if(confidencelevel!=0)
			{
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
		}
		catch(Exception e)
		{
			Assert.fail("Exception in app helper attemptSkip in class DiagnosticTest",e);
		}
	}


	//select correct or incorrect option upto which Question number u want
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
					if(Driver.driver.findElements(By.className("multiple-selection-answer-choice-label-wrapper")).size()>0)
					{
						String corranswer=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
						char correctanswer;							  
						if(corranswer.charAt(17) == '(')
							correctanswer=corranswer.charAt(18);
						else
							correctanswer=corranswer.charAt(17);
						String correctchoice=Character.toString(correctanswer);

						String corranswer1=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
						char correctanswer1=corranswer1.charAt(22);

						String correctchoice1=Character.toString(correctanswer1);

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
						Driver.driver.findElement(By.cssSelector("input[id='1']")).sendKeys(corrcttextanswer);
					}
					else if(Driver.driver.findElements(By.className("sbHolder")).size() > 0)
					{			
						String corranswertext=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
						int lastindex=corranswertext.length();
						String corrcttextanswer=corranswertext.substring(16, lastindex);
						Driver.driver.findElement(By.className("sbToggle")).click();
						Thread.sleep(2000);
						Driver.driver.findElement(By.linkText(corrcttextanswer)).click();				
					}
					Thread.sleep(2000);
					if(confidencelevel!=0)
					{
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
						{	correctchoice="C"; correctchoice1 = "D";} 
						else if(correctchoice.equals("A") && correctchoice1.equals("C") || (correctchoice.equals("C") && correctchoice1.equals("A")))
						{	correctchoice="B"; correctchoice1 = "D"; }
						else if(correctchoice.equals("A") && correctchoice1.equals("D") || (correctchoice.equals("D") && correctchoice1.equals("A")))
						{ correctchoice="B"; correctchoice1 = "C";}
						else if(correctchoice.equals("A") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("A")))
						{ correctchoice="B"; correctchoice1 = "C";}
						else if(correctchoice.equals("A") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("A")))
						{ correctchoice="B"; correctchoice1 = "C";}


						else if((correctchoice.equals("B") && correctchoice1.equals("C")) || (correctchoice.equals("C") && correctchoice1.equals("B")) )
						{	correctchoice="A"; correctchoice1 = "D";} 
						else if(correctchoice.equals("B") && correctchoice1.equals("D") || (correctchoice.equals("D") && correctchoice1.equals("B")))
						{	correctchoice="A"; correctchoice1 = "C"; }
						else if(correctchoice.equals("B") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("B")))
						{ correctchoice="A"; correctchoice1 = "C";}
						else if(correctchoice.equals("B") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("B")))
						{ correctchoice="A"; correctchoice1 = "C";}

						else if((correctchoice.equals("C") && correctchoice1.equals("D")) || (correctchoice.equals("D") && correctchoice1.equals("C")) )
						{	correctchoice="A"; correctchoice1 = "B";} 
						else if(correctchoice.equals("C") && correctchoice1.equals("E") || (correctchoice.equals("E") && correctchoice1.equals("C")))
						{	correctchoice="A"; correctchoice1 = "B"; }
						else if(correctchoice.equals("C") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("C")))
						{ correctchoice="A"; correctchoice1 = "B";}


						else if((correctchoice.equals("D") && correctchoice1.equals("E")) || (correctchoice.equals("E") && correctchoice1.equals("D")) )
						{	correctchoice="A"; correctchoice1 = "B";} 
						else if(correctchoice.equals("D") && correctchoice1.equals("F") || (correctchoice.equals("F") && correctchoice1.equals("D")))
						{	correctchoice="A"; correctchoice1 = "B"; }

						else if((correctchoice.equals("E") && correctchoice1.equals("F")) || (correctchoice.equals("F") && correctchoice1.equals("E")) )
						{	correctchoice="A"; correctchoice1 = "B";}
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

					if(confidencelevel<5)
					{
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
				Driver.driver.findElement(By.className("al-quit-diag-test")).click();
				Thread.sleep(2000);
			}
			else
			{
				Driver.driver.findElement(By.className("al-diag-test-continue-later")).click();
				Thread.sleep(2000);
			}


		}
		catch(Exception e)
		{
			Assert.fail("Exception in App helper method  DiagonesticTestQuitBetween",e);
		}
	}

    public void attemptAllCorrectLS(boolean useHints,boolean useSolutionText)
    {
        try
        {
            boolean skipOthers;
            int timer=1;
            while(timer!=0)
            {
                int random = new RandomNumber().generateRandomNumber(0, 100);
                skipOthers = false;
                if(useHints == true) {
                    Driver.driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();
                }
                try {
                    if(random <= 60) {
                        (new WebDriverWait(Driver.driver, 2))
                                .until(ExpectedConditions.presenceOfElementLocated(By.className("choice-value"))).click();
                    }
                    else {
                        Driver.driver.findElements(By.className("choice-value")).get(1).click();
                    }
                    //Driver.driver.findElement(By.className("choice-value")).click();
                }
                catch (Exception e) {
                    skipOthers = true;
                }
                System.out.println(skipOthers);
                /*if(skipOthers == false) {
                    System.out.println("I am inside this loop");
                    if (Driver.driver.findElements(By.className("preview-multiple-select-answer-choice")).size() > 0)
                        new AttemptDiagnosticQuestion().attemptMultipleSelection("correct", useHints, useSolutionText, new RandomNumber().generateRandomNumber(1, 5), "");

                    else if (Driver.driver.findElements(By.className("preview-single-selection-answer-choices")).size() > 0)
                        new AttemptDiagnosticQuestion().attemptMultipleChoice("correct", useHints, useSolutionText, new RandomNumber().generateRandomNumber(1, 5), "");

                    else if (Driver.driver.findElements(By.className("true-false-answer-choices")).size() > 0)
                        new AttemptDiagnosticQuestion().attemptTrueFalse("correct", useHints, useSolutionText, new RandomNumber().generateRandomNumber(0, 5), "");

                    else if (Driver.driver.findElements(By.cssSelector("div[class='text-entry-input-wrapper visible_redactor_input_wrapper']")).size() > 0)
                        new AttemptDiagnosticQuestion().attemptTextEntry("correct", useHints, useSolutionText, new RandomNumber().generateRandomNumber(0, 5), "");

                    else if (Driver.driver.findElements(By.cssSelector("div[class='text-entry-dropdown-wrapper visible_redactor_input_wrapper']")).size() > 0)
                        new AttemptDiagnosticQuestion().attemptTextDropDown("correct", useHints, useSolutionText, new RandomNumber().generateRandomNumber(0, 5), "");

                    else if (Driver.driver.findElements(By.className("numeric_entry_student_title")).size() > 0)
                        new AttemptDiagnosticQuestion().attemptNumericEntryWithUnits("correct", useHints, useSolutionText, new RandomNumber().generateRandomNumber(0, 5), "");

                    else if (Driver.driver.findElements(By.cssSelector("div[class='text-entry-input-wrapper visible_redactor_input_wrapper']")).size() > 0)
                        new AttemptDiagnosticQuestion().attemptAdvancedNumeric("correct", useHints, useSolutionText, new RandomNumber().generateRandomNumber(0, 5), "");

                    else if (Driver.driver.findElements(By.cssSelector("div[class='symb-notation-math-edit-icon-preview']")).size() > 0)
                        new AttemptDiagnosticQuestion().attemptExpressionEvaluator("correct", useHints, useSolutionText, new RandomNumber().generateRandomNumber(0, 5), "");

                    else if (Driver.driver.findElements(By.cssSelector("div[id='html-editor-non-draggable']")).size() > 0)
                        new AttemptDiagnosticQuestion().attemptEssayType("correct", useHints, useSolutionText, new RandomNumber().generateRandomNumber(0, 5), "");

                }
*/
                (new WebDriverWait(Driver.driver, 2))
                        .until(ExpectedConditions.presenceOfElementLocated(By.className("al-diag-test-submit-button"))).click();
                //((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("al-diag-test-submit-button")));

                try {
                    Driver.driver.findElement(By.className("al-notification-message-body"));

                    String notice = Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                    if (notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering.")) {
                        Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                        Driver.driver.findElement(By.className("al-practice-test-submit-button")).click();
                        Thread.sleep(1000);
                    }


                }
                catch (Exception e) {

                }
                try {
                    Driver.driver.findElement(By.cssSelector("span[id='timer-label']"));
                }
                catch (Exception e) {
                    timer = 0;
                }

                //timer=Driver.driver.findElements(By.cssSelector("span[id='timer-label']")).size();
                //Thread.sleep(1000);
            } //while loop ends
        }
        catch(Exception e)
        {
            Assert.fail("Exception in app helper attemptCorrect in class DiagnosticTest",e);
        }
    }

    public void attemptIncorrectDiagLSAdp(int noOfQuestions, String testType, int userNo)
    {
        try {
            int timer=1;
            int questionCount = 0;
            while(timer!=0) {
                boolean attempted = false;
                (new WebDriverWait(Driver.driver, 20))
                        .until(ExpectedConditions.presenceOfElementLocated(By.className("question-display-label")));
                //True False and Single Select Question Type
                //attempt Q 3.1:
                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.1:")) {
                        try {
                            new TextSend().textsendbycssSelector("tengoa", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.2:
                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.2:")) {
                        try {
                            new TextSend().textsendbycssSelector("tienea", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.3:
                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.3:")) {
                        try {
                            new TextSend().textsendbycssSelector("tenemosa", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.4:
                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.4:")) {
                        try {
                            new TextSend().textsendbycssSelector("tienesa", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.5:
                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.5:")) {
                        try {
                            String answer = Driver.driver.findElement(By.id("sw-question-correct-answers")).getText();
                            if(answer.equals("A")) {
                                answer = "B";
                            }
                            else if (answer.equals("B")) {
                                answer = "A";
                            }
                            else if (answer.equals("C")) {
                                answer = "A";
                            }
                            else if (answer.equals("D")) {
                                answer = "A";
                            }
                            Driver.driver.findElement(By.xpath(".//*[@class='single-select-choice-icon-preview single-select-choice-icon-deselect']/span[contains(text(),'"+answer+"')]")).click();
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.6:
                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.6:")) {
                        try {
                            String answer = Driver.driver.findElement(By.id("sw-question-correct-answers")).getText();
                            if(answer.equals("A")) {
                                answer = "B";
                            }
                            else if (answer.equals("B")) {
                                answer = "A";
                            }
                            else if (answer.equals("C")) {
                                answer = "A";
                            }
                            else if (answer.equals("D")) {
                                answer = "A";
                            }
                            Driver.driver.findElement(By.xpath(".//*[@class='single-select-choice-icon-preview single-select-choice-icon-deselect']/span[contains(text(),'"+answer+"')]")).click();
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.7:
                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.7:")) {
                        try {
                            String answer = Driver.driver.findElement(By.id("sw-question-correct-answers")).getText();
                            if(answer.equals("A")) {
                                answer = "B";
                            }
                            else if (answer.equals("B")) {
                                answer = "A";
                            }
                            else if (answer.equals("C")) {
                                answer = "A";
                            }
                            else if (answer.equals("D")) {
                                answer = "A";
                            }
                            Driver.driver.findElement(By.xpath(".//*[@class='single-select-choice-icon-preview single-select-choice-icon-deselect']/span[contains(text(),'"+answer+"')]")).click();
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.8:
                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.8:")) {
                        try {
                            String answer = Driver.driver.findElement(By.id("sw-question-correct-answers")).getText();
                            if(answer.equals("A")) {
                                answer = "B";
                            }
                            else if (answer.equals("B")) {
                                answer = "A";
                            }
                            else if (answer.equals("C")) {
                                answer = "A";
                            }
                            else if (answer.equals("D")) {
                                answer = "A";
                            }
                            Driver.driver.findElement(By.xpath(".//*[@class='true-false-student-answer-select']/span[contains(text(),'"+answer+"')]")).click();
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }

                //attempt Q 3.10:
                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.10:")) {
                        try {
                            String answer = Driver.driver.findElement(By.id("sw-question-correct-answers")).getText();
                            if(answer.equals("A")) {
                                answer = "B";
                            }
                            else if (answer.equals("B")) {
                                answer = "A";
                            }
                            else if (answer.equals("C")) {
                                answer = "A";
                            }
                            else if (answer.equals("D")) {
                                answer = "A";
                            }
                            Driver.driver.findElement(By.xpath(".//*[@class='true-false-student-answer-select']/span[contains(text(),'"+answer+"')]")).click();
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.11:
                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.11:")) {
                        try {
                            new TextSend().textsendbycssSelector("argentinasa", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.12:
                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.12:")) {
                        try {
                            new TextSend().textsendbycssSelector("malaa", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.13:
                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.13:")) {
                        try {
                            Driver.driver.findElement(By.xpath(".//*[@class='multiple-select-choice-icon-preview multiple-select-choice-icon-deselect']/span[contains(text(),'B')]")).click();
                            Driver.driver.findElement(By.xpath(".//*[@class='multiple-select-choice-icon-preview multiple-select-choice-icon-deselect']/span[contains(text(),'C')]")).click();

                            //new TextSend().textsendbycssSelector("tienes", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.14:
                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.14:")) {
                        try {
                            Driver.driver.findElement(By.xpath(".//*[@class='multiple-select-choice-icon-preview multiple-select-choice-icon-deselect']/span[contains(text(),'A')]")).click();
                            Driver.driver.findElement(By.xpath(".//*[@class='multiple-select-choice-icon-preview multiple-select-choice-icon-deselect']/span[contains(text(),'B')]")).click();

                            Driver.driver.findElement(By.xpath(".//*[@class='multiple-select-choice-icon-preview multiple-select-choice-icon-deselect']/span[contains(text(),'D')]")).click();
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.15:
                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.15:")) {
                        try {
                            String answer = Driver.driver.findElement(By.id("sw-question-correct-answers")).getText();
                            if(answer.equals("A")) {
                                answer = "B";
                            }
                            else if (answer.equals("B")) {
                                answer = "A";
                            }
                            else if (answer.equals("C")) {
                                answer = "A";
                            }
                            else if (answer.equals("D")) {
                                answer = "A";
                            }
                            Driver.driver.findElement(By.xpath(".//*[@class='true-false-student-answer-select']/span[contains(text(),'"+answer+"')]")).click();
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.16:
                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.16:")) {
                        try {
                            new TextSend().textsendbycssSelector("sua", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.17:
                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.17:")) {
                        try {
                            new TextSend().textsendbycssSelector("es divertidoa", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.18:
                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.18:")) {
                        try {
                            new TextSend().textsendbycssSelector("es ricoa", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.19:
                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.19:")) {
                        try {
                            String answer = Driver.driver.findElement(By.id("sw-question-correct-answers")).getText();
                            if(answer.equals("A")) {
                                answer = "B";
                            }
                            else if (answer.equals("B")) {
                                answer = "A";
                            }
                            else if (answer.equals("C")) {
                                answer = "A";
                            }
                            else if (answer.equals("D")) {
                                answer = "A";
                            }
                            Driver.driver.findElement(By.xpath(".//*[@class='single-select-choice-icon-preview single-select-choice-icon-deselect']/span[contains(text(),'"+answer+"')]")).click();
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.20:
                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.20:")) {
                        try {
                            new TextSend().textsendbycssSelector("sua", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.21:
                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.21:")) {
                        try {
                            new TextSend().textsendbycssSelector("sua", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                /*//attempt Q 3.22:
                if (attempted == false) {
                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.22:")) {
                        try {
                            new TextSend().textsendbycssSelector("estan", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.23:
                if (attempted == false) {
                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.23:")) {
                        try {
                            new TextSend().textsendbycssSelector("esta", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.24:
                if (attempted == false) {
                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.24:")) {
                        try {
                            new TextSend().textsendbycssSelector("esta", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }*/
                try {
                    if(userNo % 2 == 0) {
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-almost")));
                    }
                    else {
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-i-know-it")));
                    }
                    //if(userNo < 21) {
                       /* if (new RandomNumber().generateRandomNumber(1, 5) == 1) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-guess")));
                        } else if (new RandomNumber().generateRandomNumber(1, 5) == 2) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-somewhat")));
                        } else if (new RandomNumber().generateRandomNumber(1, 5) == 3) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-almost")));
                        } else if (new RandomNumber().generateRandomNumber(1, 5) == 4) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-i-know-it")));
                        }*/
                    /*}
                    else {
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-i-know-it")));
                    }*/


                    //driver.findElement(By.cssSelector("a[id='" + new RandomNumber().generateRandomNumber(1, 5) + "']")).click();
                }
                catch (Exception e) {
                    System.out.println("Not able to select confidence level "+e );
                }
                if(testType.equals("Diagnostic")) {
                    try {
                        (new WebDriverWait(Driver.driver, 2))
                                .until(ExpectedConditions.presenceOfElementLocated(By.className("al-diag-test-submit-button"))).click();
                        if(Driver.driver.findElement(By.className("al-diag-chapter-details")).getText().contains("(1 of 20)")) {

                            Driver.driver.findElement(By.className("al-notification-message-body"));
                            Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                            Driver.driver.findElement(By.cssSelector("input[type='button']")).click();
                        }


                    }
                    catch (Exception e) {

                    }
                }
                else {
                    try {
                        (new WebDriverWait(Driver.driver, 2))
                                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='button']"))).click();

                        (new WebDriverWait(Driver.driver, 2))
                                .until(ExpectedConditions.presenceOfElementLocated(By.className("al-notification-message-body")));
                        //Driver.driver.findElement(By.className("al-notification-message-body"));
                        Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                        (new WebDriverWait(Driver.driver, 2))
                                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='button']"))).click();

                    }
                    catch (Exception e) {

                    }

                    try {
                        (new WebDriverWait(Driver.driver, 2))
                                .until(ExpectedConditions.presenceOfElementLocated(By.id("next-practice-question-button"))).click(); //Clicking Next Question Button
                    }
                    catch (Exception e) {

                    }


                }
                Thread.sleep(5000);
                questionCount++;
                if(noOfQuestions == 0) {
                    try {
                        Driver.driver.findElement(By.cssSelector("span[id='timer-label']"));
                    } catch (Exception e) {
                        timer = 0;
                    }
                }
                else {
                    if(noOfQuestions == questionCount) {
                        timer = 0;
                    }
                }



            }
        }
        catch (Exception e) {
            Assert.fail("Exception while attempting the diagnostic question as correct",e);
        }
    }

    public void attemptCorrectDiagLSAdp(int noOfQuestions, String testType, int userNo)
    {
        try {
            int timer=1;
            int questionCount = 0;
            while(timer!=0) {
                boolean attempted = false;
                (new WebDriverWait(Driver.driver, 20))
                        .until(ExpectedConditions.presenceOfElementLocated(By.className("question-display-label")));
                //attempt Q 3.1:

                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.1:")) {
                        try {
                            new TextSend().textsendbycssSelector("tengo", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.2:

                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.2:")) {
                        try {
                            new TextSend().textsendbycssSelector("tiene", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.3:

                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.3:")) {
                        try {
                            new TextSend().textsendbycssSelector("tenemos", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.4:

                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.4:")) {
                        try {
                            new TextSend().textsendbycssSelector("tienes", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.5:

                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.5:")) {
                        try {
                            String answer = Driver.driver.findElement(By.id("sw-question-correct-answers")).getText();
                            Driver.driver.findElement(By.xpath(".//*[@class='single-select-choice-icon-preview single-select-choice-icon-deselect']/span[contains(text(),'"+answer+"')]")).click();
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.6:

                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.6:")) {
                        try {
                            String answer = Driver.driver.findElement(By.id("sw-question-correct-answers")).getText();
                            Driver.driver.findElement(By.xpath(".//*[@class='single-select-choice-icon-preview single-select-choice-icon-deselect']/span[contains(text(),'"+answer+"')]")).click();
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.7:

                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.7:")) {
                        try {
                            String answer = Driver.driver.findElement(By.id("sw-question-correct-answers")).getText();
                            Driver.driver.findElement(By.xpath(".//*[@class='single-select-choice-icon-preview single-select-choice-icon-deselect']/span[contains(text(),'"+answer+"')]")).click();
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.8:

                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.8:")) {
                        try {
                            String answer = Driver.driver.findElement(By.id("sw-question-correct-answers")).getText();
                            Driver.driver.findElement(By.xpath(".//*[@class='true-false-student-answer-select']/span[contains(text(),'"+answer+"')]")).click();
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }

                //attempt Q 3.10:

                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.10:")) {
                        try {
                            String answer = Driver.driver.findElement(By.id("sw-question-correct-answers")).getText();
                            Driver.driver.findElement(By.xpath(".//*[@class='true-false-student-answer-select']/span[contains(text(),'"+answer+"')]")).click();
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.11:

                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.11:")) {
                        try {
                            new TextSend().textsendbycssSelector("argentinas", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.12:

                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.12:")) {
                        try {
                            new TextSend().textsendbycssSelector("mala", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.13:

                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.13:")) {
                        try {
                            Driver.driver.findElement(By.xpath(".//*[@class='multiple-select-choice-icon-preview multiple-select-choice-icon-deselect']/span[contains(text(),'B')]")).click();
                            Driver.driver.findElement(By.xpath(".//*[@class='multiple-select-choice-icon-preview multiple-select-choice-icon-deselect']/span[contains(text(),'C')]")).click();

                            //new TextSend().textsendbycssSelector("tienes", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.14:

                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.14:")) {
                        try {
                            Driver.driver.findElement(By.xpath(".//*[@class='multiple-select-choice-icon-preview multiple-select-choice-icon-deselect']/span[contains(text(),'A')]")).click();
                            Driver.driver.findElement(By.xpath(".//*[@class='multiple-select-choice-icon-preview multiple-select-choice-icon-deselect']/span[contains(text(),'B')]")).click();

                            Driver.driver.findElement(By.xpath(".//*[@class='multiple-select-choice-icon-preview multiple-select-choice-icon-deselect']/span[contains(text(),'D')]")).click();
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.15:

                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.15:")) {
                        try {
                            String answer = Driver.driver.findElement(By.id("sw-question-correct-answers")).getText();
                            Driver.driver.findElement(By.xpath(".//*[@class='true-false-student-answer-select']/span[contains(text(),'"+answer+"')]")).click();
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.16:

                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.16:")) {
                        try {
                            new TextSend().textsendbycssSelector("su", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.17:

                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.17:")) {
                        try {
                            new TextSend().textsendbycssSelector("es divertido", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.18:

                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.18:")) {
                        try {
                            new TextSend().textsendbycssSelector("es rico", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.19:

                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.19:")) {
                        try {
                            String answer = Driver.driver.findElement(By.id("sw-question-correct-answers")).getText();
                            Driver.driver.findElement(By.xpath(".//*[@class='single-select-choice-icon-preview single-select-choice-icon-deselect']/span[contains(text(),'"+answer+"')]")).click();
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.20:

                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.20:")) {
                        try {
                            new TextSend().textsendbycssSelector("su", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.21:

                if (attempted == false) {

                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.21:")) {
                        try {
                            new TextSend().textsendbycssSelector("su", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                /*//attempt Q 3.22:
                if (attempted == false) {
                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.22:")) {
                        try {
                            new TextSend().textsendbycssSelector("estan", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.23:
                if (attempted == false) {
                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.23:")) {
                        try {
                            new TextSend().textsendbycssSelector("esta", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }
                //attempt Q 3.24:
                if (attempted == false) {
                    if(Driver.driver.findElement(By.className("question-display-label")).getText().equals("Q 3.24:")) {
                        try {
                            new TextSend().textsendbycssSelector("esta", "input[class='visible_redactor_input bg-color-white']");
                            attempted = true;
                        } catch (Exception e) {

                        }
                    }
                }*/

                try {
                    if(userNo % 2 == 0) {
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-almost")));
                    }
                    else {
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-i-know-it")));
                    }
                    //if(userNo < 21 ) {
                       /* if (new RandomNumber().generateRandomNumber(1, 5) == 1) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-guess")));
                        } else if (new RandomNumber().generateRandomNumber(1, 5) == 2) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-somewhat")));
                        } else if (new RandomNumber().generateRandomNumber(1, 5) == 3) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-almost")));
                        } else if (new RandomNumber().generateRandomNumber(1, 5) == 4) {
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-i-know-it")));
                        }*/
                    /*}
                    else {
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("confidence-level-i-know-it")));
                    }*/


                    //driver.findElement(By.cssSelector("a[id='" + new RandomNumber().generateRandomNumber(1, 5) + "']")).click();
                }
                catch (Exception e) {
                    System.out.println("Not able to select confidence level "+e );
                }
                if(testType.equals("Diagnostic")) {
                    try {
                        (new WebDriverWait(Driver.driver, 2))
                                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='button']")));
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("al-diag-test-submit-button")));
                        if(Driver.driver.findElement(By.className("al-diag-chapter-details")).getText().contains("(1 of 24)")) {

                            Driver.driver.findElement(By.className("al-notification-message-body"));
                            Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                            Driver.driver.findElement(By.cssSelector("input[type='button']")).click();
                        }


                    }
                    catch (Exception e) {

                    }
                }
                else {
                    try {
                        (new WebDriverWait(Driver.driver, 2))
                                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='button']"))).click();

                        (new WebDriverWait(Driver.driver, 2))
                                .until(ExpectedConditions.presenceOfElementLocated(By.className("al-notification-message-body")));
                        //Driver.driver.findElement(By.className("al-notification-message-body"));
                        Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                        (new WebDriverWait(Driver.driver, 2))
                                .until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='button']"))).click();

                    }
                    catch (Exception e) {

                    }

                    try {
                        (new WebDriverWait(Driver.driver, 2))
                                .until(ExpectedConditions.presenceOfElementLocated(By.id("next-practice-question-button"))).click(); //Clicking Next Question Button
                    }
                    catch (Exception e) {

                    }


                }
                Thread.sleep(1000);
                questionCount++;
                if(noOfQuestions == 0) {
                    try {
                        Driver.driver.findElement(By.cssSelector("span[id='timer-label']"));
                    } catch (Exception e) {
                        timer = 0;
                    }
                }
                else {
                    if(noOfQuestions == questionCount) {
                        timer = 0;
                    }
                }



            }
        }
        catch (Exception e) {
            Assert.fail("Exception while attempting the diagnostic question as correct",e);
        }
    }

}
