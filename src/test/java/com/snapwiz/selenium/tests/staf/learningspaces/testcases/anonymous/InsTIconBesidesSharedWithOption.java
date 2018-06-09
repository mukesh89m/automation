package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

public class InsTIconBesidesSharedWithOption extends Driver{
    private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous.InsTIconBesidesSharedWithOption");
    /*
     * 65-68
     */
    @Test
    public void tIconBesidesSharedWithOption()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("65_R24");
            new Navigator().NavigateTo("Course Stream");
            driver.findElement(By.linkText("Post")).click();
            int ticon = driver.findElements(By.className("ls-shareImg")).size(); //checking T-icon
            if(ticon == 0)
                Assert.fail("T-Icon not found Beside the share with option in course stream");

            WebElement value=driver.findElement(By.className("ls-shareImg"));
            boolean elementvaluepost=value.isDisplayed();
            driver.findElement(By.linkText("Link")).click();
            Thread.sleep(3000);
            ticon = driver.findElements(By.className("ls-shareImg")).size(); //checking T-icon
            if(ticon == 0)
                Assert.fail("T-Icon not found Beside the share with option in course stream");
            WebElement value1=driver.findElement(By.className("ls-shareImg"));
            boolean elementvalueLink=value1.isDisplayed();
            driver.findElement(By.linkText("File")).click();
            Thread.sleep(3000);
            ticon = driver.findElements(By.className("ls-shareImg")).size(); //checking T-icon
            if(ticon == 0)
                Assert.fail("T-Icon not found Beside the share with option in course stream");

            WebElement value2=driver.findElement(By.className("ls-shareImg"));
            boolean elementvalueupload=value2.isDisplayed();

            if(elementvaluepost==true && elementvalueLink==true && elementvalueupload==true)
            {
                logger.log(Level.INFO,"Testcase InsTIconBesidesSharedWithOption Pass");
            }
            else
            {
                logger.log(Level.INFO,"Testcase InsTIconBesidesSharedWithOption Fail");
                Assert.fail("T option not  present for Post/Link/file share.");
            }

        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase tIconBesidesSharedWithOption in class InsTIconBesidesSharedWithOption.",e);
        }
    }


}
