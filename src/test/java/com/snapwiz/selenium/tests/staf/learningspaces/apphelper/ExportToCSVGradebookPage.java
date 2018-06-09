package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.Gradebook.Gradebook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by durgapathi on 7/8/2015.
 */
public class ExportToCSVGradebookPage extends Driver {
    public void exportCSVFile(){
        try
        {
            WebDriver driver=Driver.getWebDriver();
            driver.findElement(By.cssSelector("div[class='export-gradebook-summary-csv student-performance-report-export-csv idb-export-csv-performance-report']")).click();
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
            Assert.fail("exportCSVFile" +e);
        }
    }
}
