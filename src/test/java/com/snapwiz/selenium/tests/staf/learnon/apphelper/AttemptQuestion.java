package com.snapwiz.selenium.tests.staf.learnon.apphelper;

import com.snapwiz.selenium.tests.staf.learnon.Driver;
import com.snapwiz.selenium.tests.staf.learnon.apphelper.DropDown;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.Click;
import com.snapwiz.selenium.tests.staf.learnon.uihelper.TextSend;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import java.util.List;

public class AttemptQuestion {
	
	
	public void trueFalse(boolean useHint, String answerChoice, int dataIndex)
    {
        try
        {

           if(!answerChoice.equalsIgnoreCase("skip")) {
               if (answerChoice.equalsIgnoreCase("correct"))
               {
                   List<WebElement> allOptions = Driver.driver.findElements(By.className("true-false-student-content-text"));
                   int index = 0;
                   for(WebElement option : allOptions)
                   {
                       if(option.getText().equals("True"))
                       {
                           break;
                       }
                       index++;
                   }
                   new Click().clickbylist("true-false-student-answer-label", index);//click on correct option
               }
               if (answerChoice.equalsIgnoreCase("incorrect"))
               {
                   List<WebElement> allOptions = Driver.driver.findElements(By.className("true-false-student-content-text"));
                   int index = 0;
                   for(WebElement option : allOptions)
                   {
                       if(option.getText().equals("False"))
                       {
                           break;
                       }
                       index++;
                   }
                   new Click().clickbylist("true-false-student-answer-label", index);//click on Incorrect option
               }
           }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper AttemptQuestion in method trueFalse.", e);
        }
    }
	public void multipleChoice(boolean useHint, String answerChoice, int dataIndex)
    {
        try
        {
            if(!answerChoice.equalsIgnoreCase("skip")) {
                if (answerChoice.equalsIgnoreCase("correct"))
                    new Click().clickByclassname("choice-value");//click on Option A
                if (answerChoice.equalsIgnoreCase("incorrect"))
                    new Click().clickbylist("choice-value", 1);//click on Option B
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper AttemptQuestion in method trueFalse.", e);
        }
    }
    public void multipleSelection(boolean useHint, String answerChoice, int dataIndex)
    {
        try
        {
            if(!answerChoice.equalsIgnoreCase("skip")) {
                if (answerChoice.equalsIgnoreCase("correct"))
                {
                    List<WebElement> allOptions = Driver.driver.findElements(By.className("multiple-ans-preview"));
                    int index = 0;
                    for(WebElement option : allOptions)
                    {
                        if(option.getText().equals("Option 1"))
                        {
                            break;
                        }
                        index++;
                    }
                    new Click().clickbylist("choice-value", index);//click on first correct option
                    int index1 = 0;
                    for(WebElement option : allOptions)
                    {
                        if(option.getText().equals("Option 2"))
                        {
                            break;
                        }
                        index1++;
                    }
                    new Click().clickbylist("choice-value", index1);//click on second correct option
                }
                if (answerChoice.equalsIgnoreCase("incorrect"))
                {
                    List<WebElement> allOptions = Driver.driver.findElements(By.className("multiple-ans-preview"));
                    int index = 0;
                    for(WebElement option : allOptions)
                    {
                        if(option.getText().equals("Option 3"))
                        {
                            break;
                        }
                        index++;
                    }
                    new Click().clickbylist("choice-value", index);//click on first incorrect option
                    int index1 = 0;
                    for(WebElement option : allOptions)
                    {
                        if(option.getText().equals("Option 4"))
                        {
                            break;
                        }
                        index1++;
                    }
                    new Click().clickbylist("choice-value", index1);//click on second incorrect option
                }
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper AttemptQuestion in method trueFalse.", e);
        }
    }

    public void textEntry(boolean useHint, String answerChoice, int dataIndex)
    {
        try {

            if(!answerChoice.equalsIgnoreCase("skip")) {

                if (answerChoice.equalsIgnoreCase("correct"))
                    new TextSend().textsendbycssSelector("Correct Answer", "input[class='visible_redactor_input bg-color-white']");


                else
                    new TextSend().textsendbycssSelector("InCorrect Answer","input[class='visible_redactor_input bg-color-white']");

            }
        }
        catch (Exception e) {

            Assert.fail("Exception while attempting Text Entry type Question",e);
        }
    }

    public void textDropDown(boolean useHint,String answerChoice, int dataIndex)
    {
        try {

            if(!answerChoice.equalsIgnoreCase("skip")) {

                if (answerChoice.equalsIgnoreCase("correct"))
                    new DropDown().selectValue("class","question-raw-content-dropdown","Answer1");

                else
                    new DropDown().selectValue("class","question-raw-content-dropdown","Answer2");

            }
        }
        catch (Exception e) {

            Assert.fail("Exception while attempting Text Entry type Question",e);
        }
    }

    public void numericEntryWithUnits(boolean useHint,String answerChoice, int dataIndex)
    {
        try {
            if(!answerChoice.equalsIgnoreCase("skip")) {

                if (answerChoice.equalsIgnoreCase("correct")) {
                    new TextSend().textsendbyclass("10", "numeric_entry_student_input");
                    new DropDown().selectValue("class","question-raw-content-dropdown","feet");
                }
                else {
                    new TextSend().textsendbyclass("20", "numeric_entry_student_input");
                    new DropDown().selectValue("class","question-raw-content-dropdown","feet");
                }


            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception while attempting numeric entry with units question",e);
        }
    }

    public void advancedNumeric(boolean useHint,String answerChoice, int dataIndex)
    {
        try {
            if(!answerChoice.equalsIgnoreCase("skip")) {

                if (answerChoice.equalsIgnoreCase("correct"))
                    new TextSend().textsendbycssSelector("10", "input[class='visible_redactor_input bg-color-white']");

                else
                    new TextSend().textsendbycssSelector("20", "input[class='visible_redactor_input bg-color-white']");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception while attempting numeric entry with units question",e);
        }
    }

    public void expressionEvaluator(boolean useHint,String answerChoice, int dataIndex)
    {
        try {
            if(!answerChoice.equalsIgnoreCase("skip")) {

                if (answerChoice.equalsIgnoreCase("correct")) {
                    new Click().clickByid("answer_math_edit");
                    new QuestionCreate().enterValueInMathMLEditor("Square root","5");
                }
                else {
                    new Click().clickByid("answer_math_edit");
                    new QuestionCreate().enterValueInMathMLEditor("Square root","10");
                }
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception while attempting numeric entry with units question",e);
        }
    }

    public void matchTheFollowing(boolean useHint,String answerChoice, int dataIndex)
    {
        try {
            if(!answerChoice.equalsIgnoreCase("skip")) {
                Actions ac = new Actions(Driver.driver);
                if (answerChoice.equalsIgnoreCase("correct")) {
                    ac.dragAndDrop(Driver.driver.findElements(By.cssSelector("div[class='answer-con match label-to-control']")).get(1), Driver.driver.findElements(By.cssSelector("div[class='stud-dnd-match-rhs ui-droppable']")).get(0)).build().perform();
                    ac.dragAndDrop(Driver.driver.findElements(By.cssSelector("div[class='answer-con match formula-to-control']")).get(3), Driver.driver.findElements(By.cssSelector("div[class='stud-dnd-match-rhs ui-droppable']")).get(1)).build().perform();
                    ac.dragAndDrop(Driver.driver.findElements(By.cssSelector("div[class='answer-con match formula-to-control']")).get(4), Driver.driver.findElements(By.cssSelector("div[class='stud-dnd-match-rhs ui-droppable']")).get(2)).build().perform();
                    ac.dragAndDrop(Driver.driver.findElements(By.cssSelector("div[class='answer-con match formula-to-control']")).get(5), Driver.driver.findElements(By.cssSelector("div[class='stud-dnd-match-rhs ui-droppable']")).get(3)).build().perform();
                    ac.dragAndDrop(Driver.driver.findElements(By.cssSelector("div[class='answer-con match label-to-control']")).get(2), Driver.driver.findElements(By.cssSelector("div[class='stud-dnd-match-rhs ui-droppable']")).get(4)).build().perform();
                }
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception while attempting match the following question",e);
        }
    }

    public void dragAndDrop(boolean useHint,String answerChoice, int dataIndex)
    {
        try {
            if(!answerChoice.equalsIgnoreCase("skip")) {
                Actions ac = new Actions(Driver.driver);
                if (answerChoice.equalsIgnoreCase("correct"))
                    ac.dragAndDrop(Driver.driver.findElements(By.className("dnd-preview-answer")).get(0), Driver.driver.findElements(By.cssSelector("div[class='student-dnd-drop-zone ui-droppable']")).get(0)).build().perform();

                else
                    ac.dragAndDrop(Driver.driver.findElements(By.className("dnd-preview-answer")).get(1), Driver.driver.findElements(By.cssSelector("div[class='student-dnd-drop-zone ui-droppable']")).get(0)).build().perform();
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception while attempting match the following question",e);
        }
    }

    public void attemptClozeFormula(boolean useHint, String answerChoice, int dataIndex)
    {
        try {
            
            Actions ac = new Actions(Driver.driver);
            ac.dragAndDrop(Driver.driver.findElement(By.id("ans_ch_1")), Driver.driver.findElements(By.cssSelector("div[class='dnd-match-rhs box cloze-formula-rhs ui-droppable']")).get(0)).build().perform();
            ac.dragAndDrop(Driver.driver.findElement(By.id("ans_ch_2")), Driver.driver.findElements(By.cssSelector("div[class='dnd-match-rhs box cloze-formula-rhs ui-droppable']")).get(1)).build().perform();
            ac.dragAndDrop(Driver.driver.findElement(By.id("ans_ch_3")), Driver.driver.findElements(By.cssSelector("div[class='dnd-match-rhs box cloze-formula-rhs ui-droppable']")).get(2)).build().perform();
            ac.dragAndDrop(Driver.driver.findElement(By.id("ans_ch_4")), Driver.driver.findElements(By.cssSelector("div[class='dnd-match-rhs box cloze-formula-rhs ui-droppable']")).get(3)).build().perform();
            ac.dragAndDrop(Driver.driver.findElement(By.id("ans_ch_5")), Driver.driver.findElements(By.cssSelector("div[class='dnd-match-rhs box cloze-formula-rhs ui-droppable']")).get(4)).build().perform();

        }
        catch (Exception e)
        {
            Assert.fail("Exception while attempting cloze formula question",e);
        }

    }

    public void essay(boolean useHint,String answerChoice, int dataIndex)
    {
        try
        {

            if(!answerChoice.equalsIgnoreCase("skip")) {

                if (answerChoice.equalsIgnoreCase("correct"))
                {
                    new Click().clickByid("html-editor-non-draggable");
                    new TextSend().textsendbyid("Correct Essay Answer Text", "html-editor-non-draggable");
                }

                else
                    new TextSend().textsendbyid("Incorrect Essay Answer Text", "html-editor-non-draggable");
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception while attempting essay type question.",e);
        }
    }

    public void writeBoard(boolean useHint,String answerChoice, int dataIndex)
    {
        try
        {

            if(!answerChoice.equalsIgnoreCase("skip")) {

                if (answerChoice.equalsIgnoreCase("correct"))
                {
                    Thread.sleep(20000);//time for Write board to load
                    System.out.println("Inside attempt for Write Board");
                    Driver.driver.switchTo().frame(0);
                    new Click().clickByid("square-btn");//click inside the board
                    Thread.sleep(2000);
                    Driver.driver.switchTo().defaultContent();
                    Driver.driver.findElement(By.xpath("/html/body")).click();
                    Thread.sleep(2000);
                }
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception while attempting write board question.",e);
        }
    }
}
