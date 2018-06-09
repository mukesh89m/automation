package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sumit on 8/12/2014.
 */
public class QuestionCard {

    //Author Sumit
    //click on question card based on index
    public void clickOnCard(String dataIndex, int cardIndex) {
        try {
            List<WebElement> allQuestionCard = Driver.driver.findElements(By.className("question-card-question-content"));
            allQuestionCard.get(cardIndex).click();//click on question card
        } catch (Exception e) {
            Assert.fail("Exception in app helper QuestionCard in method clickOnCard", e);
        }
    }

    public void clickOnInvisibleCard(String dataIndex, int cardIndex) {
        try {
            List<WebElement> allQuestionCard = Driver.driver.findElements(By.className("question-card-question-content"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].scrollIntoView(true);", allQuestionCard.get(cardIndex));//click on question card
            allQuestionCard.get(cardIndex).click();//click on question card
        } catch (Exception e) {
            Assert.fail("Exception in app helper QuestionCard in method clickOnCard", e);
        }
    }

    public String fetchQuestionNamOfInvisibleCard(String dataIndex, int cardIndex) {
        String value = null;
        try {
            List<WebElement> allQuestionCard = Driver.driver.findElements(By.className("question-card-question-content"));
            ((JavascriptExecutor) Driver.driver).executeScript("arguments[0].scrollIntoView(true);", allQuestionCard.get(cardIndex));//click on question card
            value = allQuestionCard.get(cardIndex).getText().trim();
        } catch (Exception e) {
            Assert.fail("Exception in app helper QuestionCard in method clickOnCard", e);
        }
        return value;
    }

    public List<String> fetchAllQuestionName(String xpath) {
        List<String> allText = new ArrayList<>();

        try {
            List<WebElement> allList = Driver.driver.findElements(By.xpath(xpath));
            for (WebElement temp : allList) {
                allText.add(temp.getText());

            }

        } catch (Exception e) {
            Assert.fail("Exception in  apphelper  fetchhiddentextbyxpath ", e);
        }
        return allText;
    }
}
