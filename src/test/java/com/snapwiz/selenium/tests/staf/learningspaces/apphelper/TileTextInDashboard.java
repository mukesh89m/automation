package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/*
 * Created by Sumit on 7/22/14.
 */
public class TileTextInDashboard extends Driver {

    //find the text in lower part of Participation score(My Score:)
    public String studentParticipationScoreLowerText()
    {
        String score = null;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            score = driver.findElement(By.className("percent")).getText();
            System.out.println("score: "+score);
        }
        catch (Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in apphelper studentParticipationScoreUpperText in class TileTextInDashboard", e);
        }
        return score;
    }


    public String participationRating(int index)
    {
        String score = null;
        WebDriver driver=Driver.getWebDriver();
        try
        {
            score = driver.findElement(By.xpath("//div[@class='box class-participation-score']/div["+index+"]")).getText().trim();
            System.out.println("participationRatingTile: "+score);
        }
        catch (Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in apphelper studentParticipationScoreUpperText in class TileTextInDashboard", e);
        }
        return score;
    }

}
