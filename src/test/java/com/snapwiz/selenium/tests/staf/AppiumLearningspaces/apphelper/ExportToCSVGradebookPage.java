package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by durgapathi on 7/8/2015.
 */
public class ExportToCSVGradebookPage extends Driver {
    public void exportCSVFile(){
        try
        {
            driver.findElement(By.xpath(".//*[@title='Gradebook']")).click();
            Thread.sleep(5000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        }
        catch (Exception e)
        {
            Assert.fail("exportCSVFile" +e);
        }
    }
}
