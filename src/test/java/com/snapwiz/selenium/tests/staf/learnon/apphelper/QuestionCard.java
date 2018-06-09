package com.snapwiz.selenium.tests.staf.learnon.apphelper;

import com.snapwiz.selenium.tests.staf.learnon.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/*
 * Created by Sumit on 8/12/2014.
 */
public class QuestionCard {

    //Author Sumit
    //click on question card based on index
    public void clickOnCard(String dataIndex, int cardIndex)
    {
        try
        {
            List<WebElement> allQuestionCard = Driver.driver.findElements(By.className("question-card-question-content"));
            allQuestionCard.get(cardIndex).click();//click on question card
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper QuestionCard in method clickOnCard", e);
        }
    }

}
