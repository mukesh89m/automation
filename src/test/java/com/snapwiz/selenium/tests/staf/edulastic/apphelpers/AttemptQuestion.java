package com.snapwiz.selenium.tests.staf.edulastic.apphelpers;

import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.assignments.GraphingQuestionCreation;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.*;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;

/*
 * Created by Sumit on 9/12/2014.
 */
public class AttemptQuestion extends Driver {

    public void  trueFalse(boolean useHint, String answerChoice, int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            if(!answerChoice.equalsIgnoreCase("skip")) {
                if (answerChoice.equalsIgnoreCase("correct"))
                {
                    List<WebElement> allOptions = driver.findElements(By.className("true-false-student-content-text"));
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
                    List<WebElement> allOptions = driver.findElements(By.className("true-false-student-content-text"));
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
            ReportUtil.log("Attempt true false question type","True false question type is attempted","pass");
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
            WebDriver driver=Driver.getWebDriver();
            if(!answerChoice.equalsIgnoreCase("skip")) {
                if (answerChoice.equalsIgnoreCase("correct"))
                    new Click().clickByclassname("choice-value");//click on Option A
                if (answerChoice.equalsIgnoreCase("incorrect"))
                    new Click().clickbylist("choice-value", 1);//click on Option B
            }
            ReportUtil.log("Attempt multiple choice question type","Multiple choice question type is attempted","pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper AttemptQuestion in method multipleChoice.", e);
        }
    }
    public void multipleSelection(boolean useHint, String answerChoice, int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            if(!answerChoice.equalsIgnoreCase("skip")) {
                if (answerChoice.equalsIgnoreCase("correct"))
                {
                    List<WebElement> allOptions = driver.findElements(By.className("multiple-ans-preview"));
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
                    List<WebElement> allOptions = driver.findElements(By.className("multiple-ans-preview"));
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
                if (answerChoice.equalsIgnoreCase("partialCorrect"))
                {
                    List<WebElement> allOptions = driver.findElements(By.className("multiple-ans-preview"));
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
                }
            }
            ReportUtil.log("Attempt multiple selection question type","Multiple selection question type is attempted","pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper AttemptQuestion in method multipleSelection.", e);
        }
    }

    public void textEntry(boolean useHint, String answerChoice, int dataIndex)
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            if(!answerChoice.equalsIgnoreCase("skip")) {

                if (answerChoice.equalsIgnoreCase("correct"))
                    new TextSend().textsendbycssSelector("Correct Answer","input[type='text']");

                else
                    new TextSend().textsendbycssSelector("InCorrect Answer","input[type='text']");
            }
            ReportUtil.log("Attempt text entry question type","Text Entry question type is attempted","pass");
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
            ReportUtil.log("Attempt text drop down question type","Text drop down question type is attempted","pass");

        }
        catch (Exception e) {

            Assert.fail("Exception while attempting textDropDown type Question",e);
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
            ReportUtil.log("Attempt numeric entry with units question type","Numeric entry with units question type is attempted","pass");

        }
        catch (Exception e)
        {
            Assert.fail("Exception while attempting numeric entry with units question",e);
        }
    }

    public void numeric(boolean useHint,String answerChoice, int dataIndex)
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            if(!answerChoice.equalsIgnoreCase("skip")) {
                System.out.println(answerChoice);

                if (answerChoice.equalsIgnoreCase("correct")) {
                    Thread.sleep(5000);
                    new Click().clickByid("answer_math_edit");
                    Thread.sleep(3000);
                    driver.findElement(By.className("wrs_focusElement")).sendKeys("3");
                    //driver.findElement(By.id("wiris-answer-container-save-choice1")).click();//click on Save
                }

                else
                {
                    new Click().clickByid("answer_math_edit");
                    Thread.sleep(1000);

                    driver.findElement(By.className("wrs_focusElement")).sendKeys("5");
                    //driver.findElement(By.id("wiris-answer-container-save-choice1")).click();//click on Save
                }
            }
            ReportUtil.log("Attempt numeric question type","Numeric question type is attempted","pass");

        }
        catch (Exception e)
        {
            Assert.fail("Exception while attempting numeric question",e);
        }
    }

    public void expressionEvaluator(boolean useHint,String answerChoice, int dataIndex)
    {
        try {
            if(!answerChoice.equalsIgnoreCase("skip")) {

                if (answerChoice.equalsIgnoreCase("correct")) {
                    new Click().clickByid("answer_math_edit");
                    new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","5");
                }
                else {
                    new Click().clickByid("answer_math_edit");
                    Thread.sleep(3000);
                    new QuestionCreate().enterValueInMathMLEditorInExpressionEvaluatorTypeQuestion("Square root","10");
                }
            }
            ReportUtil.log("Attempt expression evaluator question type","Expression evaluator question type is attempted","pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception while attempting expressionEvaluator question",e);
        }
    }

    public void matchTheFollowing(boolean useHint,String answerChoice, int dataIndex)
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            if(!answerChoice.equalsIgnoreCase("skip")) {
                Actions ac = new Actions(driver);
                if (answerChoice.equalsIgnoreCase("correct"))
                {
                    for(int i=3;i<6;i++)
                    {
                        if((driver.findElements(By.cssSelector("div[class='answer-con match formula-to-control']")).get(i).getText().replaceAll("/n"," ").trim().contains("5")))
                        {
                            ac.dragAndDrop(driver.findElements(By.cssSelector("div[class='answer-con match formula-to-control']")).get(i), driver.findElements(By.cssSelector("div[class='stud-dnd-match-rhs ui-droppable']")).get(0)).build().perform();
                        }
                        if((driver.findElements(By.cssSelector("div[class='answer-con match formula-to-control']")).get(i).getText().replaceAll("/n"," ").trim().contains("6")))
                        {
                            ac.dragAndDrop(driver.findElements(By.cssSelector("div[class='answer-con match formula-to-control']")).get(i), driver.findElements(By.cssSelector("div[class='stud-dnd-match-rhs ui-droppable']")).get(3)).build().perform();
                        }
                        if((driver.findElements(By.cssSelector("div[class='answer-con match formula-to-control']")).get(i).getText().replaceAll("/n"," ").trim().contains("7")))
                        {
                            ac.dragAndDrop(driver.findElements(By.cssSelector("div[class='answer-con match formula-to-control']")).get(i), driver.findElements(By.cssSelector("div[class='stud-dnd-match-rhs ui-droppable']")).get(4)).build().perform();
                        }
                    }



                    if(driver.findElements(By.cssSelector("div[class='answer-con match label-to-control']")).get(1).getText().contains("1000"))
                    {
                        ac.dragAndDrop(driver.findElements(By.cssSelector("div[class='answer-con match label-to-control']")).get(1), driver.findElements(By.cssSelector("div[class='stud-dnd-match-rhs ui-droppable']")).get(1)).build().perform();
                        ac.dragAndDrop(driver.findElements(By.cssSelector("div[class='answer-con match label-to-control']")).get(2), driver.findElements(By.cssSelector("div[class='stud-dnd-match-rhs ui-droppable']")).get(2)).build().perform();
                    }
                    else
                    {
                        ac.dragAndDrop(driver.findElements(By.cssSelector("div[class='answer-con match label-to-control']")).get(1), driver.findElements(By.cssSelector("div[class='stud-dnd-match-rhs ui-droppable']")).get(2)).build().perform();
                        ac.dragAndDrop(driver.findElements(By.cssSelector("div[class='answer-con match label-to-control']")).get(2), driver.findElements(By.cssSelector("div[class='stud-dnd-match-rhs ui-droppable']")).get(1)).build().perform();
                    }

                    ac.dragAndDrop(driver.findElements(By.cssSelector("div[class='answer-con match label-to-control']")).get(1), driver.findElements(By.cssSelector("div[class='stud-dnd-match-rhs ui-droppable']")).get(1)).build().perform();
                    ac.dragAndDrop(driver.findElements(By.cssSelector("div[class='answer-con match label-to-control']")).get(2), driver.findElements(By.cssSelector("div[class='stud-dnd-match-rhs ui-droppable']")).get(2)).build().perform();

                }


                else
                {
                    for(int i=3;i<6;i++)
                    {
                        if((driver.findElements(By.cssSelector("div[class='answer-con match formula-to-control']")).get(i).getText().replaceAll("/n"," ").trim().contains("5")))
                        {
                            ac.dragAndDrop(driver.findElements(By.cssSelector("div[class='answer-con match formula-to-control']")).get(i), driver.findElements(By.cssSelector("div[class='stud-dnd-match-rhs ui-droppable']")).get(3)).build().perform();
                            Thread.sleep(2000);
                        }
                        if((driver.findElements(By.cssSelector("div[class='answer-con match formula-to-control']")).get(i).getText().replaceAll("/n"," ").trim().contains("6")))
                        {
                            ac.dragAndDrop(driver.findElements(By.cssSelector("div[class='answer-con match formula-to-control']")).get(i), driver.findElements(By.cssSelector("div[class='stud-dnd-match-rhs ui-droppable']")).get(4)).build().perform();
                            Thread.sleep(2000);
                        }
                        if((driver.findElements(By.cssSelector("div[class='answer-con match formula-to-control']")).get(i).getText().replaceAll("/n"," ").trim().contains("7")))
                        {
                            ac.dragAndDrop(driver.findElements(By.cssSelector("div[class='answer-con match formula-to-control']")).get(i), driver.findElements(By.cssSelector("div[class='stud-dnd-match-rhs ui-droppable']")).get(0)).build().perform();
                            Thread.sleep(2000);

                        }
                    }
                    Thread.sleep(9000);
                    WebDriverUtil.waitForAjax(driver,60);
                    ac.dragAndDrop(driver.findElements(By.cssSelector("div[class='answer-con match label-to-control']")).get(2), driver.findElements(By.cssSelector("div[class='stud-dnd-match-rhs ui-droppable']")).get(1)).build().perform();
                    Thread.sleep(9000);
                    ac.dragAndDrop(driver.findElements(By.cssSelector("div[class='answer-con match label-to-control']")).get(2), driver.findElements(By.cssSelector("div[class='stud-dnd-match-rhs ui-droppable']")).get(2)).build().perform();
                    Thread.sleep(2000);

                }
            }
            ReportUtil.log("Attempt match th following question type","match the following question type is attempted","pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception while attempting match the following question",e);
        }
    }

    public void dragAndDrop(boolean useHint,String answerChoice, int dataIndex)
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            if (!answerChoice.equalsIgnoreCase("skip")) {
                Actions ac = new Actions(driver);
                if (answerChoice.equalsIgnoreCase("correct")) {
                    if (driver.findElements(By.cssSelector("span.swformula")).get(0).getText().contains("5")) {
                        ac.dragAndDrop(driver.findElements(By.cssSelector("span.swformula")).get(0), driver.findElements(By.cssSelector("div[class='student-dnd-drop-zone ui-droppable']")).get(0)).build().perform();
                    } else {
                        ac.dragAndDrop(driver.findElements(By.cssSelector("span.swformula")).get(1), driver.findElements(By.cssSelector("div[class='student-dnd-drop-zone ui-droppable']")).get(0)).build().perform();
                    }

                }
                else
                {
                    if (driver.findElements(By.cssSelector("span.swformula")).get(0).getText().contains("5")) {
                        ac.dragAndDrop(driver.findElements(By.cssSelector("span.swformula")).get(1), driver.findElements(By.cssSelector("div[class='student-dnd-drop-zone ui-droppable']")).get(0)).build().perform();
                    } else {
                        ac.dragAndDrop(driver.findElements(By.cssSelector("span.swformula")).get(0), driver.findElements(By.cssSelector("div[class='student-dnd-drop-zone ui-droppable']")).get(0)).build().perform();
                    }
                }
            }
            ReportUtil.log("Attempt drag and drop question type","TDrag and drop question type is attempted","pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception while attempting dragAndDrop question",e);
        }
    }

    public void attemptClozeFormula(boolean useHint, String answerChoice, int dataIndex)
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            Actions ac = new Actions(driver);
            if(!answerChoice.equalsIgnoreCase("skip")) {
                if (answerChoice.equalsIgnoreCase("correct")) {
                    ac.dragAndDrop(driver.findElement(By.id("ans_ch_1")), driver.findElements(By.cssSelector("div[class='dnd-match-rhs box cloze-formula-rhs ui-droppable']")).get(0)).build().perform();
                    ac.dragAndDrop(driver.findElement(By.id("ans_ch_2")), driver.findElements(By.cssSelector("div[class='dnd-match-rhs box cloze-formula-rhs ui-droppable']")).get(1)).build().perform();
                    ac.dragAndDrop(driver.findElement(By.id("ans_ch_3")), driver.findElements(By.cssSelector("div[class='dnd-match-rhs box cloze-formula-rhs ui-droppable']")).get(2)).build().perform();
                    ac.dragAndDrop(driver.findElement(By.id("ans_ch_4")), driver.findElements(By.cssSelector("div[class='dnd-match-rhs box cloze-formula-rhs ui-droppable']")).get(3)).build().perform();
                    ac.dragAndDrop(driver.findElement(By.id("ans_ch_5")), driver.findElements(By.cssSelector("div[class='dnd-match-rhs box cloze-formula-rhs ui-droppable']")).get(4)).build().perform();
                }
                else if (answerChoice.equalsIgnoreCase("incorrect"))
                {
                    ac.dragAndDrop(driver.findElement(By.id("ans_ch_1")), driver.findElements(By.cssSelector("div[class='dnd-match-rhs box cloze-formula-rhs ui-droppable']")).get(4)).build().perform();
                    ac.dragAndDrop(driver.findElement(By.id("ans_ch_2")), driver.findElements(By.cssSelector("div[class='dnd-match-rhs box cloze-formula-rhs ui-droppable']")).get(2)).build().perform();
                    ac.dragAndDrop(driver.findElement(By.id("ans_ch_3")), driver.findElements(By.cssSelector("div[class='dnd-match-rhs box cloze-formula-rhs ui-droppable']")).get(3)).build().perform();
                    ac.dragAndDrop(driver.findElement(By.id("ans_ch_4")), driver.findElements(By.cssSelector("div[class='dnd-match-rhs box cloze-formula-rhs ui-droppable']")).get(1)).build().perform();
                    ac.dragAndDrop(driver.findElement(By.id("ans_ch_5")), driver.findElements(By.cssSelector("div[class='dnd-match-rhs box cloze-formula-rhs ui-droppable']")).get(0)).build().perform();
                }
            }
            ReportUtil.log("Attempt cloze formula question type","Cloze formula question type is attempted","pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception while attempting cloze formula question",e);
        }

    }

    public void attemptGraphPlotter(boolean useHint, String answerChoice, int dataIndex)
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            Actions ac = new Actions(driver);
            if(!answerChoice.equalsIgnoreCase("skip")) {
                if (answerChoice.equalsIgnoreCase("correct")) {

                }
                else if (answerChoice.equalsIgnoreCase("incorrect"))
                {

                    ac.clickAndHold(driver.findElements(By.cssSelector(".highcharts-series.highcharts-tracker >rect")).get(0)).moveByOffset(0,40).release().build().perform();
                    Thread.sleep(1000);
                    // ac.moveToElement(driver.findElements(By.cssSelector(".highcharts-series.highcharts-tracker >rect")).get(0)).release();
                    Thread.sleep(1000);
                    ac.clickAndHold(driver.findElements(By.cssSelector(".highcharts-series.highcharts-tracker >rect")).get(1)).moveByOffset(0,60).release().build().perform();
                    Thread.sleep(1000);
                    //ac.moveToElement(driver.findElements(By.cssSelector(".highcharts-series.highcharts-tracker >rect")).get(1)).release();
                    Thread.sleep(1000);
                    ac.clickAndHold(driver.findElements(By.cssSelector(".highcharts-series.highcharts-tracker >rect")).get(2)).moveByOffset(0,80).release().build().perform();
                    Thread.sleep(1000);

                }
            }
            ReportUtil.log("Attempt Graph plotter question type","Graph plotter question type is attempted","pass");

        }
        catch (Exception e)
        {
            Assert.fail("Exception while attempting GraphPlotter question",e);
        }

    }


    public void attemptClozeMatrix(boolean useHint, String answerChoice, int dataIndex)
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            Actions ac = new Actions(driver);
            if(!answerChoice.equalsIgnoreCase("skip")) {
                if (answerChoice.equalsIgnoreCase("correct")) {
                    driver.findElement(By.cssSelector(".matrix-box-preview.matrix-ans-redit >input")).clear();
                    driver.switchTo().activeElement().sendKeys("1");
                }
                else if (answerChoice.equalsIgnoreCase("incorrect"))
                {
                    driver.findElement(By.cssSelector(".matrix-box-preview.matrix-ans-redit >input")).clear();
                    driver.switchTo().activeElement().sendKeys("6");

                }
            }
            ReportUtil.log("Attempt Cloze matrix question type","Cloze matrix question type is attempted","pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception while attempting cloze matrix question",e);
        }

    }


    public void attemptResequence(boolean useHint, String answerChoice, int dataIndex)
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            Actions ac = new Actions(driver);
            if(!answerChoice.equalsIgnoreCase("skip")) {
                if (answerChoice.equalsIgnoreCase("correct")) {
                    List<WebElement> webelements=driver.findElements(By.cssSelector(".resequence-answer-normalText.label-to-control"));
                    int size=webelements.size();
                    int index=0;
                    for(int i=0;i<size-1;i++)
                    {
                        String k=driver.findElements(By.cssSelector(".resequence-answer-normalText.label-to-control")).get(i).getText();
                        for (int j=0;j<size;j++)
                        {
                            if(k.compareTo(driver.findElements(By.cssSelector(".resequence-answer-normalText.label-to-control")).get(j).getText())>0)
                            {
                                index=j;
                            }
                        }
                        Actions act =new Actions(driver);
                        WebElement dragAble=driver.findElements(By.cssSelector(".resequence-answer-normalText.label-to-control")).get(index);
                        WebElement dropAble=driver.findElements(By.cssSelector(".resequence-answer-normalText.label-to-control")).get(i);
                        act.clickAndHold(dragAble).build().perform();
                        act.moveToElement(dropAble).release().build().perform();
                    }

                }
                else if (answerChoice.equalsIgnoreCase("incorrect"))
                {
                    List<WebElement> webelements=driver.findElements(By.cssSelector(".resequence-answer-normalText.label-to-control"));
                    int size=webelements.size();
                    int index=0;
                    for(int i=0;i<size-1;i++)
                    {
                        String k=driver.findElements(By.cssSelector(".resequence-answer-normalText.label-to-control")).get(i).getText();
                        for (int j=0;j<size;j++)
                        {
                            if(k.compareTo(driver.findElements(By.cssSelector(".resequence-answer-normalText.label-to-control")).get(j).getText())<0)
                            {
                                index=j;
                            }
                        }
                        Actions act =new Actions(driver);
                        WebElement dragAble=driver.findElements(By.cssSelector(".resequence-answer-normalText.label-to-control")).get(index);
                        WebElement dropAble=driver.findElements(By.cssSelector(".resequence-answer-normalText.label-to-control")).get(i);
                        act.clickAndHold(dragAble).build().perform();
                        act.moveToElement(dropAble).release().build().perform();
                    }

                }
            }
            ReportUtil.log("Attempt resequence question type","Resequence question type is attempted","pass");

        }
        catch (Exception e)
        {
            Assert.fail("Exception while attempting resequence question",e);
        }

    }


    public void attemptLabelAnImageText(boolean useHint, String answerChoice, int dataIndex)
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            Actions ac = new Actions(driver);
            if(!answerChoice.equalsIgnoreCase("skip")) {
                if (answerChoice.equalsIgnoreCase("correct")) {
                    driver.findElement(By.cssSelector("#answer")).clear();
                    driver.switchTo().activeElement().sendKeys("Answer 1");
                }
                else if (answerChoice.equalsIgnoreCase("incorrect"))
                {
                    driver.findElement(By.cssSelector("#answer")).clear();
                    driver.switchTo().activeElement().sendKeys("Answer 5");

                }
            }
            ReportUtil.log("Attempt LabelAnImageText question type","LabelAnImageText question type is attempted","pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception while attempting LabelAnImageText question",e);
        }

    }

    public void attemptLabelAnImageDropdown(boolean useHint, String answerChoice, int dataIndex)
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            if(!answerChoice.equalsIgnoreCase("skip")) {

                if (answerChoice.equalsIgnoreCase("correct")) {
                    Thread.sleep(2000);
                    List<WebElement> ff=driver.findElements(By.xpath("(//select[@class='set-drop-down-value-preview'])"));
                    Select select=new Select(ff.get(0));
                    select.selectByIndex(1);
                    Select select1=new Select(ff.get(1));
                    select1.selectByIndex(1);
                    Select select2=new Select(ff.get(2));
                    select2.selectByIndex(1);
                }
                else if (answerChoice.equalsIgnoreCase("incorrect"))
                {
                    Thread.sleep(2000);
                    Select select=new Select(driver.findElement(By.xpath("//select[@class='set-drop-down-value-preview']")));
                    select.selectByIndex(2);

                }
            }
            ReportUtil.log("Attempt LabelAnImageDropdown question type","LabelAnImageDropdown question type is attempted","pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception while attempting LabelAnImageDropdown question",e);
        }

    }


    public void attemptNumberLine(boolean useHint, String answerChoice, int dataIndex)
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            Actions ac = new Actions(driver);
            if(!answerChoice.equalsIgnoreCase("skip")) {
                if (answerChoice.equalsIgnoreCase("correct")) {
                    List<WebElement> answersToDrop = driver.findElements(By.cssSelector("div.swformula"));
                    List<WebElement> rhsFields = driver.findElements(By.cssSelector("div[class='num-line-box ui-droppable']"));

                    answersToDrop.get(0).click();
                    ac.dragAndDrop(driver.findElement(By.cssSelector("div.swformula")), rhsFields.get(0)).build().perform();

                    answersToDrop.get(1).click();
                    ac.dragAndDrop(driver.findElements(By.cssSelector("div.swformula")).get(1), rhsFields.get(10)).build().perform();

                    answersToDrop.get(2).click();
                    ac.dragAndDrop(driver.findElements(By.cssSelector("div.swformula")).get(2), rhsFields.get(2)).build().perform();
                }
                else if (answerChoice.equalsIgnoreCase("incorrect"))
                {
                    List<WebElement> answersToDrop = driver.findElements(By.cssSelector("div.swformula"));
                    List<WebElement> rhsFields = driver.findElements(By.cssSelector("div[class='num-line-box ui-droppable']"));
                    int size=driver.findElements(By.cssSelector("div.remove-numline-choice")).size();
                    if(size!=0)
                    {
                        for (int i=0;i<size;i++)
                        {
                            driver.findElements(By.cssSelector("div.remove-numline-choice")).get(0).click();
                        }
                    }
                    answersToDrop.get(0).click();
                    ac.dragAndDrop(driver.findElement(By.cssSelector("div.swformula")), rhsFields.get(3)).build().perform();

                }
            }
            ReportUtil.log("Attempt NumberLine question type","NumberLine question type is attempted","pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception while attempting NumberLine question",e);
        }

    }



    public void attemptClassification(boolean useHint, String answerChoice, int dataIndex)
    {
        try {
            WebDriver driver=Driver.getWebDriver();
            Actions ac = new Actions(driver);
            if(!answerChoice.equalsIgnoreCase("skip")) {
                if (answerChoice.equalsIgnoreCase("correct")) {
                    List<WebElement> answersToDrop = driver.findElements(By.cssSelector("div.dnd-preview-answer.answer"));
                    WebElement dstanswersToDrop= driver.findElement(By.cssSelector("div[class='classification-div-content']"));

                    answersToDrop.get(0).click();
                    ac.dragAndDrop(driver.findElement(By.cssSelector("div.dnd-preview-answer.answer")), dstanswersToDrop).build().perform();

                    answersToDrop.get(1).click();
                    ac.dragAndDrop(driver.findElements(By.cssSelector("div.dnd-preview-answer.answer")).get(1),dstanswersToDrop).build().perform();

                    answersToDrop.get(2).click();
                    ac.dragAndDrop(driver.findElements(By.cssSelector("div.dnd-preview-answer.answer")).get(2), dstanswersToDrop).build().perform();
                }
                else if (answerChoice.equalsIgnoreCase("incorrect"))
                {
                    List<WebElement> answersToDrop = driver.findElements(By.cssSelector("div.dnd-preview-answer.answer"));
                    WebElement rhsFields = driver.findElement(By.cssSelector("div[class='classification-div-content']"));
                    int size=driver.findElements(By.cssSelector("span.remove-answer-choice.pull-right > img")).size();
                    if(size!=0)
                    {
                        for (int i=0;i<size;i++)
                        {
                            driver.findElements(By.cssSelector("span.remove-answer-choice.pull-right > img")).get(0).click();
                            Thread.sleep(1000);
                        }
                    }
                    answersToDrop.get(3).click();
                    ac.dragAndDrop(answersToDrop.get(3), rhsFields).build().perform();
                }
            }
            ReportUtil.log("Attempt Classification question type","Classification question type is attempted","pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception while attempting Classification question",e);
        }

    }



    public void  attemptPassageBased(boolean useHint, String answerChoice, int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            if(!answerChoice.equalsIgnoreCase("skip")) {
                if (answerChoice.equalsIgnoreCase("correct"))
                {
                    List<WebElement> allOptions = driver.findElements(By.className("true-false-student-content-text"));
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
                    List<WebElement> allOptions = driver.findElements(By.className("true-false-student-content-text"));
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
            ReportUtil.log("Attempt PassageBased question type","PassageBased question type is attempted","pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper AttemptQuestion in method 'attemptPassageBased.'", e);
        }
    }


    public void  attemptSentenceResponse(boolean useHint, String answerChoice, int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            if(!answerChoice.equalsIgnoreCase("skip")) {
                if (answerChoice.equalsIgnoreCase("correct"))
                {
                    List<WebElement> webElements=driver.findElements(By.xpath("//span[contains(@class,'sentence-response-selectiontext')]"));
                    Actions action=new Actions(driver);
                    action.moveToElement(driver.findElements(By.xpath("//span[contains(@class,'sentence-response-selectiontext')]")).get(0)).click().build().perform();
                    Thread.sleep(2000);

                }
                if (answerChoice.equalsIgnoreCase("incorrect"))
                {
                    List<WebElement> webElements=driver.findElements(By.xpath("//span[contains(@class,'sentence-response-selectiontext')]"));
                    Actions action=new Actions(driver);
                    action.moveToElement(driver.findElements(By.xpath("//span[contains(@class,'sentence-response-selectiontext')]")).get(1)).clickAndHold().release().build().perform();
                    Thread.sleep(2000);
                }
            }
            ReportUtil.log("Attempt SentenceResponse question type","SentenceResponse question type is attempted","pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper AttemptQuestion in method SentenceResponse", e);
        }
    }



    public void  attemptMatchingTables(boolean useHint, String answerChoice, int dataIndex)
    {
        try
        {

            if(!answerChoice.equalsIgnoreCase("skip")) {
                if (answerChoice.equalsIgnoreCase("correct"))
                {
                    new Click().clickBycssselector("label[for='matrix-box-preview-btn-2-1']");
                    new Click().clickBycssselector("label[for='matrix-box-preview-btn-3-1']");
                }
                if (answerChoice.equalsIgnoreCase("incorrect"))
                {

                   /* new Click().clickBycssselector("label[for='matrix-box-preview-btn-2-1']");
                    new Click().clickBycssselector("label[for='matrix-box-preview-btn-3-1']");*/
                    new Click().clickBycssselector("label[for='matrix-box-preview-btn-2-2']");
                    new Click().clickBycssselector("label[for='matrix-box-preview-btn-3-2']");
                }
            }
            ReportUtil.log("Attempt MatchingTables question type","MatchingTables question type is attempted","pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper AttemptQuestion in method MatchingTables", e);
        }
    }




    public void  attemptLinePlot(boolean useHint, String answerChoice, int dataIndex)
    {
        try
        {

            if(!answerChoice.equalsIgnoreCase("skip")) {
                if (answerChoice.equalsIgnoreCase("correct"))
                {
                    new Click().clickBycssselector("div#numberLineAnsBox_0");
                }
                if (answerChoice.equalsIgnoreCase("incorrect"))
                {

                    new Click().clickBycssselector("div#numberLineAnsBox_0");
                }
            }
            ReportUtil.log("Attempt LinePlot question type","LinePlot question type is attempted","pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper AttemptQuestion in method LinePlot", e);
        }
    }

    public void  attemptRangePlotter(boolean useHint, String answerChoice, int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            if(!answerChoice.equalsIgnoreCase("skip")) {
                if (answerChoice.equalsIgnoreCase("correct"))
                {
                    WebElement src = driver.findElement(By.xpath("(//div[@type='segment'])[1]"));
                    WebElement dst = driver.findElement(By.id("numberLineAnsBox_2"));

                    new DragAndDrop().dragAndDrop(src,dst);

                    new Click().clickByid("numberLineAnsBox_2");

                    WebElement srcObject = driver.findElement(By.cssSelector("div[class='range-nl-create-point finite-closed']"));
                    WebElement dstLine = driver.findElement(By.id("numberLineAnsBox_4"));

                    new DragAndDrop().dragAndDrop(srcObject,dstLine);//Draw the object

                    new Click().clickByid("numberLineAnsBox_4");
                }
                if (answerChoice.equalsIgnoreCase("incorrect"))
                {

                    WebElement src = driver.findElement(By.xpath("(//div[@type='segment'])[1]"));
                    WebElement dst = driver.findElement(By.id("numberLineAnsBox_2"));

                    new DragAndDrop().dragAndDrop(src,dst);

                    new Click().clickByid("numberLineAnsBox_2");

                    WebElement srcObject = driver.findElement(By.cssSelector("div[class='range-nl-create-point finite-closed']"));
                    WebElement dstLine = driver.findElement(By.id("numberLineAnsBox_3"));

                    new DragAndDrop().dragAndDrop(srcObject,dstLine);//Draw the object

                    new Click().clickByid("numberLineAnsBox_3");
                }
            }
            ReportUtil.log("Attempt RangePlotter question type","RangePlotter question type is attempted","pass");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper AttemptQuestion in method RangePlotter.", e);
        }
    }

    public void  attemptFractionEditor(boolean useHint, String answerChoice, int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            if(!answerChoice.equalsIgnoreCase("skip")) {
                if (answerChoice.equalsIgnoreCase("correct"))
                {
                    new Click().clickBycssselector("div.rect-column");
                }
                if (answerChoice.equalsIgnoreCase("incorrect"))
                {
                    int size=driver.findElements(By.cssSelector("div.rect-column.active-block")).size();
                    if(size>0)
                    {
                        new Click().clickBycssselector("div.rect-column.active-block");
                    }
                    new Click().clickBycssselector("div.rect-column");
                    Thread.sleep(500);
                    new Click().clickbylistcssselector("div.rect-column", 2);
                }
            }
            ReportUtil.log("Attempt FractionEditor question type","FractionEditor question type is attempted","pass");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper AttemptQuestion in method FractionEditor.", e);
        }
    }

    public void  attemptGraphing(boolean useHint, String answerChoice, int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            GraphingQuestionCreation graphing = PageFactory.initElements(driver, GraphingQuestionCreation.class);

            if(!answerChoice.equalsIgnoreCase("skip")) {
                if (answerChoice.equalsIgnoreCase("correct"))
                {
                    graphing.getLabelsInGrid().get(19).click();//Place the point on graph
                }
                if (answerChoice.equalsIgnoreCase("incorrect"))
                {
                    new Click().clickBycssselector("#resetGraphInPreview");
                    Thread.sleep(2000);
                    graphing.getLabelsInGrid().get(14).click();//Place the point on graph
                }
            }
            ReportUtil.log("Attempt Graphing question type","Graphing question type is attempted","pass");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper AttemptQuestion in method Graphing.", e);
        }
    }



    public void  essayType(boolean useHint, String answerChoice, int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            driver.findElement(By.cssSelector("div[id^='html-editor-non-draggable']")).sendKeys("essay type question ");


            ReportUtil.log("Attempt essayType question type","essayType question type is attempted","pass");

        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper AttemptQuestion in method essayType.", e);
        }
    }



    public void  sentenceCorrection(boolean useHint, String answerChoice, int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            if(!answerChoice.equalsIgnoreCase("skip")) {
                if (answerChoice.equalsIgnoreCase("correct"))
                {
                    driver.findElement(By.id("choiceSequence-0")).click();
                }
                if (answerChoice.equalsIgnoreCase("incorrect"))
                {
                    driver.findElement(By.id("choiceSequence-1")).click();
                }
            }
            ReportUtil.log("Attempt sentenceCorrection question type","sentenceCorrection question type is attempted","pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper AttemptQuestion in method sentenceCorrection.", e);
        }
    }

    public void  graphPlacement(boolean useHint, String answerChoice, int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        Actions ac = new Actions(driver);
        try
        {
            if(!answerChoice.equalsIgnoreCase("skip")) {
                if (answerChoice.equalsIgnoreCase("correct"))
                {
                    ac.dragAndDrop(driver.findElements(By.cssSelector("div[class='dnd-preview-answer']")).get(0), driver.findElements(By.xpath("//div[text()='2']")).get(1)).build().perform();
                }
                if (answerChoice.equalsIgnoreCase("incorrect"))
                {
                    ac.dragAndDrop(driver.findElements(By.cssSelector("div[class='dnd-preview-answer']")).get(1), driver.findElements(By.xpath("//div[text()='2']")).get(1)).build().perform();
                }
            }
            ReportUtil.log("Attempt graphPlacement question type","graphPlacement question type is attempted","pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper AttemptQuestion in method graphPlacement.", e);
        }
    }


    public void  attemptpictograph(boolean useHint, String answerChoice, int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        Actions ac = new Actions(driver);
        try
        {
            if(!answerChoice.equalsIgnoreCase("skip")) {
                if (answerChoice.equalsIgnoreCase("correct"))
                {
                    ac.dragAndDrop(driver.findElements(By.cssSelector("div.handle-matchching-question-drag")).get(1), driver.findElement(By.cssSelector("div.classification-div-content"))).build().perform();
                }
                if (answerChoice.equalsIgnoreCase("incorrect"))
                {
                    ac.dragAndDrop(driver.findElements(By.cssSelector("div.handle-matchching-question-drag")).get(0), driver.findElement(By.cssSelector("div.classification-div-content"))).build().perform();
                }
            }
            ReportUtil.log("Attempt pictograph question type","pictograph question type is attempted","pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper AttemptQuestion in method pictograph.", e);
        }
    }


    public void  multipart(boolean useHint, String answerChoice, int dataIndex)

    {
        WebDriver driver=Driver.getWebDriver();
        Actions ac = new Actions(driver);
        try
        {

            String questionText = new TextFetch().textfetchbyid("question-edit");
            List<WebElement> ansChoiceMultipleSelection = driver.findElements(By.xpath("div[class='multi-select-choice-icon-preview multi-select-choice-icon-deselect']"));
            List<WebElement> ansChoiceMultipleChoice = driver.findElements(By.xpath("//div[starts-with(@class,'single-select-choice-icon-preview single-select-choice-icon-')]/span"));

            if(!answerChoice.equalsIgnoreCase("skip")) {
                if (answerChoice.equalsIgnoreCase("correct"))
                {
                    if(questionText.contains("Multipart : MultipleChoice Question"))
                    {
                        List<WebElement> allOptions = driver.findElements(By.className("multi-ans-preview"));
                        int index = 0;
                        for(WebElement option : allOptions)
                        {
                            if(option.getText().equals("Answer Choice1"))
                            {
                                break;
                            }
                            index++;
                        }

                        new Click().clickbylist("choice-value", index);//click on first correct option
                        int index1 = 0;
                        for(WebElement option : allOptions)
                        {
                            if(option.getText().equals("Answer Choice2"))
                            {
                                break;
                            }
                            index1++;
                        }
                        new Click().clickbylist("choice-value", index1);//click on second correct option


                        WebDriverUtil.clickOnElementUsingJavascript(ansChoiceMultipleChoice.get(0));//Select answer choice A for multiple choice question type
                    }

                    if(questionText.contains("Multipart : TextDropdown Question"))
                    {
                        new DropDown().selectValue("class","question-raw-content-dropdown","Answer1");
                        new TextSend().textsendbycssSelector("20","input[type='text']");//Enter 20 as correct answer for text entry question type
                    }

                    if(questionText.contains("Multipart : Numeric"))
                    {
                        new TextSend().textsendbycssSelector("20", "input[type='text']");
                    }

                }
                if (answerChoice.equalsIgnoreCase("incorrect"))
                {
                    if(questionText.contains("Multipart : MultipleChoice Question"))
                    {
                        List<WebElement> allOptions = driver.findElements(By.className("multi-ans-preview"));
                        int index = 0;
                        for(WebElement option : allOptions)
                        {
                            if(option.getText().equals("Answer Choice3"))
                            {
                                break;
                            }
                            index++;
                        }

                        new Click().clickbylist("choice-value", index);//click on first correct option
                        int index1 = 0;
                        for(WebElement option : allOptions)
                        {
                            if(option.getText().equals("Answer Choice4"))
                            {
                                break;
                            }
                            index1++;
                        }
                        new Click().clickbylist("choice-value", index1);//click on second correct option


                        ansChoiceMultipleChoice.get(1).click();//Select answer choice A for multiple choice question type
                    }

                    if(questionText.contains("Multipart : TextEntry Question"))
                    {
                        new DropDown().selectValue("class","question-raw-content-dropdown","Answer1");
                        new TextSend().textsendbycssSelector("10","input[type='text']");//Enter 20 as correct answer for text entry question type
                    }

                    if(questionText.contains("Multipart : Numeric"))
                    {
                        new TextSend().textsendbycssSelector("10", "input[type='text']");
                    }
                }
            }
            ReportUtil.log("Attempt multipart question type","multipart question type is attempted","pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper AttemptQuestion in method multipart", e);
        }
    }

}
