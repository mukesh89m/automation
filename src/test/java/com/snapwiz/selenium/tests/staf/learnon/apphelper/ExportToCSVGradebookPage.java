package com.snapwiz.selenium.tests.staf.learnon.apphelper;

import com.snapwiz.selenium.tests.staf.learnon.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by root on 8/12/15.
 */
public class ExportToCSVGradebookPage {
    public void exportCSVFile(){
        try
        {
            Driver.driver.findElement(By.xpath(".//*[@title='Markbook']")).click();
            Thread.sleep(5000);
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_DOWN);
            robot.keyRelease(KeyEvent.VK_DOWN);
            Thread.sleep(5000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

        }
        catch (Exception e)
        {
            Assert.fail("exportCSVFile" + e);
        }
    }
}
