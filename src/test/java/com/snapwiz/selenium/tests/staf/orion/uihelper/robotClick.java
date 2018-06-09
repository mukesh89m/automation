package com.snapwiz.selenium.tests.staf.orion.uihelper;

import org.junit.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by durgapathi on 29/10/15.
 */
public class robotClick {
    public void robotClick(){
        try
        {
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
            Assert.fail("robotClick" + e);
        }
    }
}
