package com.snapwiz.selenium.tests.staf.learningspaces.testcases.anonymous;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.Navigator;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/*TestCases from 95 to 103*/
public class LSShareWithOption extends Driver{

    @Test
    public void autoSuggestShareBox()
    {
        try
        {
            new LoginUsingLTI().ltiLogin("101");
            new Navigator().NavigateTo("Course Stream");//navigate to Course Stream
            driver.findElement(By.linkText("Post")).click();
            String post = driver.findElement(By.className("ls-post-tab")).getText();
            Assert.assertEquals(post, "Post", "Post tab is not found in Course stream of instructor.");
            String link = driver.findElement(By.cssSelector("li[title='Post a link']")).getText();
            Assert.assertEquals(link, "Link", "Link tab is not found in Course stream of instructor.");
            String file = driver.findElement(By.cssSelector("li[title='Post a file']")).getText();
            Assert.assertEquals(file, "File", "File tab is not found in Course stream of instructor.");

            driver.findElement(By.className("maininput")).click();
            driver.findElement(By.className("closebutton")).click();
            driver.findElement(By.className("maininput")).sendKeys(Config.context_title);
            String str = driver.findElement(By.xpath("//*[starts-with(@rel, 'cls_')]")).getText();
            Assert.assertEquals(str, Config.context_title, "Auto Suggest feature of share box not working in CS of instructor.");

        }
        catch(Exception e)
        {
            Assert.fail("Exception in TestCase autoSuggestShareBox ic class LSShareWithOption.",e);
        }
    }


}
