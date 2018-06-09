package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;


public class ClickQuestionCardSpecificQuestionShouldBeDisplayed extends Driver
{
    private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.ClickQuestionCardSpecificQuestionShouldBeDisplayed");

    @Test
    public void clickQuestionCardSpecificQuestionShouldBeDisplayed()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("1278");
            new Navigator().NavigateTo("eTextbook");
            new DiagnosticTest().startTest(2);
            new DiagnosticTest().attemptAllCorrect(0, false, false);
            new QuestionCard().clickOnCard("", 0);//click on question card

            String str = driver.findElement(By.cssSelector("div[id='question-raw-content-preview']")).getText();

            if(!(str.length() > 1))
                Assert.fail("No question is displayed onclicking the question card");

            String quescardstyle;
            List<WebElement> questioncard = driver.findElements(By.xpath("//*[starts-with(@class, 'report-sidebar-question-card-sectn question-card')]"));
            quescardstyle = questioncard.get(0).getAttribute("style");
            System.out.println(quescardstyle);
            if(quescardstyle.trim().contains("3px solid rgb(65, 165, 229);") )
            {
                logger.log(Level.INFO,"SElected Question is highlighted");
            }
            else
            {
                Assert.fail("SElected Question is not highlighted");
            }
            driver.findElement(By.cssSelector("img[title=\"Performance by Questions\"]")).click();
            Thread.sleep(3000);
            String performanceSummaryText = driver.findElement(By.cssSelector("div[class='al-performance-chart-title']")).getText();

            if(performanceSummaryText.equals("Performance Summary"))
            {
                logger.log(Level.INFO,"Goes back to previous accessed level on clicking graph icon");
            }
            else
            {
                Assert.fail("Doesnt go back to previous accessed level on clicking graph icon");
            }
        }
        catch(Exception e)
        {
            Assert.fail("Exception in TestCase clickQuestionCardSpecificQuestionShouldBeDisplayed in class ClickQuestionCardSpecificQuestionShouldBeDisplayed",e);
        }
    }

}
