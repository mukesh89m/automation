package com.snapwiz.selenium.tests.staf.orion.testcases.IT15.R151;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import java.util.List;

import com.snapwiz.selenium.tests.staf.orion.Driver;
import com.snapwiz.selenium.tests.staf.orion.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Navigator;
import com.snapwiz.selenium.tests.staf.orion.apphelper.Screenshot;

/*
 * Created by Sumit on 7/10/2014.
 */
public class CustomizeOrionTopics {

	@Test
    public void customizeOrionTopics()
    {
        try
        {
            Driver.startDriver();
            new LoginUsingLTI().ltiLogin("2");		//login as instructor
            new Navigator().navigateFromProfileDropDownForOrion("Settings");
            List<WebElement> text;
            text = Driver.driver.findElements(By.className("instructor-settings-discussion-label"));
            //TC row no. 2
            if(!text.get(2).getText().contains("Customize Orion topics"))
            {
            	new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("\"Customize Orion topics\" is not present in Settings page of Orion course.");
            }
            if(text.get(2).getText().contains("More options"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("\"More options\" is present in Settings page of Orion course.");
            }
            //TC row no. 3
            int offState = Driver.driver.findElements(By.cssSelector("div[class='al-chkbox-holder off-state']")).size();
            if(offState == 0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("Default state of \"Customize Orion topics\" is not OFF.");
            }
            Driver.driver.findElement(By.cssSelector("div[class='al-customize-course-enabled customize-topic-enabled al-chkbox1']")).click();// click ON for "Customize Orion topics"
            Thread.sleep(2000);
            text = Driver.driver.findElements(By.className("instructor-settings-discussion-label"));
            //TC row no. 4
            if(!text.get(2).getText().contains("More options"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After clicking ON for \"Customize Orion topics\" it doesn't show \"More options\" link next to \"Customize Orion topics\" text.");
            }
            //TC row no. 5
            String icon = Driver.driver.findElement(By.className("instructor-more-options-icon")).getCssValue("background-image");
            if(!icon.contains("ins-next-btn.png"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("\"More options\" text doesn't have a icon nearby.");
            }
            Driver.driver.findElement(By.className("instructor-more-options-link")).click();    //click on More option
            Thread.sleep(2000);
            //TC row no. 6
            String name = Driver.driver.findElement(By.className("al-customize-course-text")).getText();
            if(!name.contains("Customize Orion Topics"))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("On clicking \"More option\" it doesn't take to \"Customize the course\" page with title as \"Customize Orion Topics\".");
            }
            //TC row no. 7
            int backIcon = Driver.driver.findElements(By.cssSelector("i[class='al-back-btn-icon']")).size();
            if(backIcon == 0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("\"Customize the course\" page doesn't show app back icon before \"Customize Orion Topics\" title.");
            }
            //TC row no. 9
            int chapterOnIcon = Driver.driver.findElements(By.cssSelector("div[class='chapterToggle al-chkbox-holder on-state']")).size();
            if(chapterOnIcon == 0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("In \"Customize the course\" page Parallel to each chapter name there is no settings to ON/FF in the right side.");
            }
            //TC row no. 10
            int TLOOnIcon = Driver.driver.findElements(By.cssSelector("div[class='tloToggle al-chkbox-holder on-state']")).size();
            if(TLOOnIcon == 0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("In \"Customize the course\" page Parallel to each TLO name there is no settings to ON/FF in the right side.");
            }
            Driver.driver.findElement(By.className("al-question-help-img")).click();    //click on Help Icon
            Thread.sleep(2000);
            //TC row no. 11
            String helpData = Driver.driver.findElement(By.className("help-data-container")).getText();
            if(!helpData.contains("You may hide an entire chapter or individual objectives / sections from your class section by turning it OFF."))
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("On clicking help icon near \"Customize Orion Topics\" the required popup doesn't appear.");
            }
            Driver.driver.findElement(By.xpath("/html/body")).click();
            Thread.sleep(3000);
            //TC row no. 12
            int helpData1 = Driver.driver.findElements(By.className("help-data-container")).size();
            if(helpData1 != 0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("After clicking outside the help popup doesn't close.");
            }
            //TC row no. 13
            int offState1 = Driver.driver.findElements(By.cssSelector("div[class='chapterToggle al-chkbox-holder off-state']")).size();
            if(offState1 != 0)
            {
                new Screenshot().captureScreenshotFromTestCase();
                Assert.fail("In \"Customize the course\" page, By default for all chapter/TLO is not ON.");
            }
        }
        catch (Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in testcase customizeOrionTopics in class CustomizeOrionTopics", e);
        }
    }
    @AfterMethod
    public void tearDown() throws Exception
    {
        Driver.driver.quit();
    }
}
