package com.snapwiz.selenium.tests.staf.microlearning.apphelper;

import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.SignupPage;
import com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions.CucumberDriver;
import com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions.SignUp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by root on 8/29/16.
 */
public class NavigatorHelper {
    public void navigateTo(String menuName){
        try{
            WebDriver driver = CucumberDriver.getWebDriver();
            WebDriverUtil.waitTillVisibilityOfElement(driver.findElement(By.xpath("//span[@class='nav-label' and text()='"+menuName+"']")),60);
            WebDriverUtil.clickOnElementUsingJavascript(driver.findElement(By.xpath("//span[@class='nav-label' and text()='"+menuName+"']/..")));
            WebDriverUtil.waitForAjax(driver,60);
        }catch(Exception e){
            Assert.fail("Exception in the test method in the class 'Navigator' in the method 'navigateTo'",e);
        }
    }
}
