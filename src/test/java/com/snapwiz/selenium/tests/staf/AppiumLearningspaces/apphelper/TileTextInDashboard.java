package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import org.openqa.selenium.By;
import org.testng.Assert;

/*
 * Created by Sumit on 7/22/14.
 */
public class TileTextInDashboard {

    //find the text in lower part of Participation score(My Score:)
    public String studentParticipationScoreLowerText()
    {
        String score = null;
        try
        {
            score = Driver.driver.findElement(By.className("percent")).getText();
            System.out.println("score: "+score);
        }
        catch (Exception e)
        {
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in apphelper studentParticipationScoreUpperText in class TileTextInDashboard", e);
        }
        return score;
    }


    public String participationRating(int index)
    {
        String score = null;
        try
        {
            score = Driver.driver.findElement(By.xpath("//div[@class='box class-participation-score']/div["+index+"]")).getText().trim();
            System.out.println("participationRatingTile: "+score);
        }
        catch (Exception e)
        {
            new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in apphelper studentParticipationScoreUpperText in class TileTextInDashboard", e);
        }
        return score;
    }

}
