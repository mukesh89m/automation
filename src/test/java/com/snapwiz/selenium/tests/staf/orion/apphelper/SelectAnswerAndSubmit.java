package com.snapwiz.selenium.tests.staf.orion.apphelper;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.snapwiz.selenium.tests.staf.orion.Driver;

public class SelectAnswerAndSubmit {

    public void daigonestianswersubmit(String answeroption)
    {
        try
        {
            if(Driver.driver.findElements(By.className("qtn-label")).size() > 0)
            {
                List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
                for (WebElement answerchoice: answer)
                {

                    if(answerchoice.getText().trim().equals(answeroption))
                    {

                        answerchoice.click();
                        break;
                    }
                }
            }
            else if(Driver.driver.findElements(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']")).size() > 0)
            {

                Driver.driver.findElement(By.cssSelector("input[id='1']")).sendKeys("correct");
            }
            else if(Driver.driver.findElements(By.className("sbHolder")).size() > 0)
            {

                Driver.driver.findElement(By.className("sbToggle")).click();
                Thread.sleep(2000);
                Driver.driver.findElement(By.linkText("Value 1")).click();
                Thread.sleep(5000);
            }
            //	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div/div/label")));
            //	Thread.sleep(2000);
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.className("al-diag-test-submit-button")));
            Thread.sleep(2000);
            int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
            if(noticesize==1)

            {
                String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
                {
                    Driver.driver.findElement(By.cssSelector("input[type=\"button\"]")).click();

                }


            }

        }
        catch(Exception e)
        {

            Assert.fail("Exception in App helper SelectAnswerAndSubmit",e);
        }
    }
    public void Adaptiveasnswersubmit(String answeroption)
    {
        try
        {
            if(answeroption != "")
            {
                if(Driver.driver.findElements(By.className("qtn-label")).size() > 0)
                {
                    List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
                    for (WebElement answerchoice: answer)
                    {
                        if(answerchoice.getText().trim().equals(answeroption))
                        {
                            answerchoice.click();
                            break;
                        }
                    }
                }
                else if(Driver.driver.findElements(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']")).size() > 0)
                {
                    Driver.driver.findElement(By.cssSelector("input[id='1']")).sendKeys("correct");
                }
                else if(Driver.driver.findElements(By.className("sbHolder")).size() > 0)
                {
                    //Skipping the drop-down question type
				/*Driver.driver.findElement(By.className("sbToggle")).click();
				Thread.sleep(2000);
				Driver.driver.findElement(By.linkText("Value 1")).click();
				Thread.sleep(3000);*/
                }
            }

            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));

            int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
            if(noticesize==1)

            {


                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[id='submit-practice-question-button']")));
                Thread.sleep(3000);
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[id='next-practice-question-button']")));
                Thread.sleep(3000);
            }
            else
            {

                Thread.sleep(3000);
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[id='next-practice-question-button']")));
                Thread.sleep(3000);
            }

        }

        catch(Exception e)
        {

            Assert.fail("Exception in App helper SelectAnswerAndSubmit",e);
        }

    }

    public void staticanswersubmit(String answeroption)
    {
        try
        {
            List<WebElement> answer = Driver.driver.findElements(By.className("qtn-label"));
            for (WebElement answerchoice: answer)
            {

                if(answerchoice.getText().trim().equals(answeroption))
                {

                    answerchoice.click();
                    break;
                }
            }
            //	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div/div/label")));
            Thread.sleep(2000);
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type=\"button\"]")));
            Thread.sleep(2000);
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("input[type=\"button\"]")));
            Thread.sleep(2000);

        }
        catch(Exception e)
        {

            Assert.fail("Exception in App helper SelectAnswerAndSubmit",e);
        }

    }
    public void directAssignmentselectanswerandsubmit()
    {
        try
        {
            List<WebElement> allelement=Driver.driver.findElements(By.className("qtn-label"));//select answer
            for(WebElement element:allelement)
            {
                element.click();
                break;
            }
            Driver.driver.findElement(By.cssSelector("a[class='btn btn--primary btn--large btn--submit']")).click();//click on submit button
        }
        catch(Exception e)
        {

            Assert.fail("Exception in App helper directAssignmentselectanswerandsubmit",e);
        }
    }
    //Attempt diagonestic test with correct answer
    public void DiagTestWithCorrectAnswer(int confidencelevel)
    {
        try
        {
            int timer=Driver.driver.findElements(By.cssSelector("span[id='timer-label']")).size();
            while(timer!=0)
            {
                String confidence=Integer.toString(confidencelevel);
                String corranswer=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                char correctanswer=corranswer.charAt(17);
                String correctchoice=Character.toString(correctanswer);
                String corranswertext=Driver.driver.findElement(By.id("show-debug-correct-answer-block")).getText();
                int lastindex=corranswertext.length();
                String corrcttextanswer=corranswertext.substring(16, lastindex);
                if(Driver.driver.findElements(By.className("answer-choice-label-wrapper")).size() > 0)
                {

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
                else if(Driver.driver.findElements(By.className("multiple-selection-answer-choice-label-wrapper")).size()>0)
                {
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

                else if(Driver.driver.findElements(By.cssSelector("span[class='input-tag-wrapper question-element-wrapper']")).size() > 0)
                {

                    Driver.driver.findElement(By.cssSelector("input[id='1']")).sendKeys(corrcttextanswer);
                }
                else if(Driver.driver.findElements(By.className("sbHolder")).size() > 0)
                {

                    Driver.driver.findElement(By.className("sbToggle")).click();
                    Thread.sleep(2000);
                    Driver.driver.findElement(By.linkText(corrcttextanswer)).click();
                }
                //	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div/div/label")));
                //	Thread.sleep(2000);
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
                timer=Driver.driver.findElements(By.cssSelector("span[id='timer-label']")).size();
            }
        }
        catch(Exception e)
        {

            Assert.fail("Exception in App helper DiagTestWithCorrectAnswer",e);
        }

    }

    //Attempt adpative Test for Orion
    public void adpativeTest(int numberOfQuestions)
    {
        try
        {
            for(int i = 0; i<numberOfQuestions; i++)
            {
                Driver.driver.findElement(By.className("qtn-label")).click();
                Thread.sleep(3000);
                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                Thread.sleep(2000);
                int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
                if(noticesize==1)

                {
                    String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                    if(notice.length()>0)
                    {
                        Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                        ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                    }

                }

                ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")));
                Thread.sleep(2000);
            }
        }
        catch(Exception e)
        {

            Assert.fail("Exception in App helper adpativeTest in class SelectAnswerAndSubmit",e);
        }

    }

    public void practiceTestAttempt(int confidencelevel,int numberofquestionattempt,String answeroption,boolean useHints,boolean showSolutionText)
    {
        try
        {
            int recommendation = Driver.driver.findElements(By.className("al-notification-message-wrapper")).size();
            if(recommendation > 0)
            {
                String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                if(notice.contains("Looks like you might want to study this objective in WileyPLUS. Go to WileyPLUS for"))
                {
                    Thread.sleep(3000);
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();",Driver.driver.findElement(By.cssSelector("span[id='exit-practice-test-block']")));
                    //Driver.driver.findElement(By.cssSelector("span[id='exit-practice-test-block']")).click();

                }
            }
            if(answeroption.equalsIgnoreCase("skip"))
            {
                for(int i=0;i<numberofquestionattempt;i++)
                {
                    if(useHints == true)
                        Driver.driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();

                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                    Thread.sleep(2000);
                    int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
                    if(noticesize==1)

                    {
                        String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                        if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
                        {
                            Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                            Thread.sleep(2000);

                        }

                    }
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")));
                    Thread.sleep(2000);
                }
            } //logic for skipping questions ends

            else if(answeroption.equalsIgnoreCase("correct"))
            {
                for(int i=0;i<numberofquestionattempt;i++)
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

                        String corranswer1=corranswer;
                        if(corranswer1.length()>21) {
                            char correctanswer1 = corranswer1.charAt(22);
                            String correctchoice1 = Character.toString(correctanswer1);
                            List<WebElement> answer1 = Driver.driver.findElements(By.className("qtn-label"));
                            for (WebElement answerchoice1 : answer1) {

                                if (answerchoice1.getText().trim().equals(correctchoice1)) {
                                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", answerchoice1);
                                    break;
                                }
                            }
                        }
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
                        Thread.sleep(5000);
                    }

                    Thread.sleep(2000);
                    if(confidencelevel!=0)
                    {
                        Driver.driver.findElement(By.cssSelector("a[id='"+confidence+"']")).click();//click on confidence level
                        Thread.sleep(2000);
                    }
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                    Thread.sleep(2000);
                    int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
                    if(noticesize==1)

                    {
                        String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                        if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
                        {
                            Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                            Thread.sleep(2000);
                        }
                    }
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")));
                    Thread.sleep(2000);


                }
            } //attepting correct answer ends
            else
            {
                for(int j=0;j<numberofquestionattempt;j++)
                {
                    if(useHints == true)
                        Driver.driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();
                    Thread.sleep(2000);
                    int hintpopup=Driver.driver.findElements(By.className("al-notification-message-header")).size();
                    if(hintpopup>0)
                    {
                        Driver.driver.findElement(By.xpath("/html/body")).click();
                        Thread.sleep(2000);
                    }
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
                        System.out.println("Correct answer 1 in attemptincorrect in muliple chocce "+correctchoice);
                        System.out.println("Correct answer 2 in attemptincorrect in muliple chocce "+correctchoice1);


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
                    //	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div/div/label")));
                    //	Thread.sleep(2000);
                    Thread.sleep(2000);
                    if(confidencelevel!=0)
                    {
                        Driver.driver.findElement(By.cssSelector("a[id='"+confidence+"']")).click();//click on confidence level
                        Thread.sleep(2000);
                    }
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                    Thread.sleep(2000);
                    int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
                    if(noticesize==1)

                    {
                        String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                        if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
                        {
                            Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                            Thread.sleep(2000);

                        }

                    }
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")));
                    Thread.sleep(2000);

                }
            }
        }
        catch(Exception e)
        {

            Assert.fail("Exception in App helper method  AdaptiveTestInBetween",e);
        }
    }

    public String[] fetchQuestionOfPracticeTestAttempted(int confidencelevel,int numberofquestionattempt,String answeroption,boolean useHints,boolean showSolutionText)
    {
        String [] questions=null;
        try
        {
            List<String> stringarray = new ArrayList<String>();
            if(answeroption.equalsIgnoreCase("skip"))
            {

                for(int i=0;i<numberofquestionattempt;i++)
                {

                    if(useHints == true)
                        Driver.driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();

                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                    Thread.sleep(2000);
                    int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
                    if(noticesize==1)

                    {
                        String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                        if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
                        {
                            Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                            Thread.sleep(2000);

                        }

                    }
                    String question = Driver.driver.findElement(By.className("al-diag-test-question-raw-content")).getText();
                    stringarray.add(question);
                    questions = stringarray.toArray(new String[stringarray.size()]);

                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")));
                    Thread.sleep(2000);
                }
            } //logic for skipping questions ends

            else if(answeroption.equalsIgnoreCase("correct"))
            {
                for(int i=0;i<numberofquestionattempt;i++)
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

                        String corranswer1=corranswer;
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
                        Thread.sleep(5000);
                    }

                    Thread.sleep(2000);
                    if(confidencelevel!=0)
                    {
                        Driver.driver.findElement(By.cssSelector("a[id='"+confidence+"']")).click();//click on confidence level
                        Thread.sleep(2000);
                    }
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                    Thread.sleep(2000);
                    int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
                    if(noticesize==1)

                    {
                        String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                        if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
                        {
                            Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                            Thread.sleep(2000);
                        }
                    }
                    String question = Driver.driver.findElement(By.className("al-diag-test-question-raw-content")).getText();
                    stringarray.add(question);
                    questions = stringarray.toArray(new String[stringarray.size()]);
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")));
                    Thread.sleep(2000);


                }
            } //attepting correct answer ends
            else
            {
                for(int j=0;j<numberofquestionattempt;j++)
                {
                    if(useHints == true)
                        Driver.driver.findElement(By.cssSelector("img[title=\"Hint\"]")).click();
                    Thread.sleep(2000);
                    int hintpopup=Driver.driver.findElements(By.className("al-notification-message-header")).size();
                    if(hintpopup>0)
                    {
                        Driver.driver.findElement(By.xpath("/html/body")).click();
                        Thread.sleep(2000);
                    }
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
                        System.out.println("Correct answer 1 in attemptincorrect in muliple chocce "+correctchoice);
                        System.out.println("Correct answer 2 in attemptincorrect in muliple chocce "+correctchoice1);


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
                    //	((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div[2]/div[2]/div/div/div[2]/div[2]/div/div/label")));
                    //	Thread.sleep(2000);
                    Thread.sleep(2000);
                    if(confidencelevel!=0)
                    {
                        Driver.driver.findElement(By.cssSelector("a[id='"+confidence+"']")).click();//click on confidence level
                        Thread.sleep(2000);
                    }
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                    Thread.sleep(2000);
                    int noticesize = Driver.driver.findElements(By.className("al-notification-message-body")).size();
                    if(noticesize==1)

                    {
                        String notice =  Driver.driver.findElement(By.className("al-notification-message-body")).getText();
                        if(notice.equals("You will build your proficiency faster if you say how confident you feel about the question before answering."))
                        {
                            Driver.driver.findElement(By.cssSelector("div[id='close-al-notification-dialog']")).click();
                            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Submit\"]")));
                            Thread.sleep(2000);

                        }

                    }
                    String question = Driver.driver.findElement(By.className("al-diag-test-question-raw-content")).getText();
                    stringarray.add(question);
                    questions = stringarray.toArray(new String[stringarray.size()]);
                    ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].click();", Driver.driver.findElement(By.cssSelector("img[title=\"Next\"]")));
                    Thread.sleep(2000);

                }

            }

        }
        catch(Exception e)
        {

            Assert.fail("Exception in App helper method  AdaptiveTestInBetween",e);
        }
        return questions;
    }
}
