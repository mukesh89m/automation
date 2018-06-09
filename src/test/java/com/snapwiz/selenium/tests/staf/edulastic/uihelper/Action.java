package com.snapwiz.selenium.tests.staf.edulastic.uihelper;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;


/**
 * Created by pragya on 23-01-2015.
 */
public class Action extends Driver{

      public  void doubleClick(WebElement we){
          try{
              WebDriver driver=Driver.getWebDriver();
              Actions ac = new Actions(driver);
              ac.doubleClick(we).build().perform();

          }catch(Exception e)
          {
              Assert.fail("Exception in method 'doubleClick' of uihelper 'Action'",e);
          }
      }
    }

