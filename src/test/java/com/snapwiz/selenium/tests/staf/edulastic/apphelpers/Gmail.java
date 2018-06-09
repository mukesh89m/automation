package com.snapwiz.selenium.tests.staf.edulastic.apphelpers;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.ExplicitWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/**
 * Created by root on 19/8/14.
 */
public class Gmail extends Driver {

    public  void login()
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            driver.get("http://www.gmail.com");
            List<WebElement> emailTextbox = driver.findElements(By.id("Email"));
            if(emailTextbox.get(0).isDisplayed() == false)
            {
                driver.findElement(By.id("account-chooser-link")).click();
                Thread.sleep(2000);
                driver.findElement(By.id("account-chooser-add-account")).click();
                Thread.sleep(2000);
            }

            driver.findElement(By.id("Email")).sendKeys(Config.studentEmail);// Enter user id
            new Click().clickByid("next");//Click on next button
            driver.findElement(By.id("Passwd")).sendKeys(Config.emailPassword);//Enter Password
            Thread.sleep(2000);
            driver.findElement(By.id("signIn")).click();//Submit button
            Thread.sleep(25000);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper loginAsInstructor in class Gmail",e);
        }
    }
    public  void openEmail(String instructorEmail,String accessURL,int dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
           if(!Config.platform.equals("android"))
               driver.navigate().refresh();
                Thread.sleep(10000);
               driver.findElement(By.className("yW")).click();
               //Driver.driver.findElement(By.xpath("//span[@id=':n4']/b")).click();
              // Driver.driver.findElement(By.xpath("//span[text()='addStudentsViaEmailinsa']")).click();
           //Driver.driver.findElement(By.xpath("(//span[@name='addStudentsViaEmailinsa'])")).click();
            Thread.sleep(10000);
            new Click().clickbypartiallinkText(accessURL);


        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper checkEmail in class Gmail",e);
        }
    }


    public void login(String email,String password)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            driver.get("http://www.gmail.com");
            List<WebElement> emailTextbox = driver.findElements(By.id("Email"));
            if(emailTextbox.get(0).isDisplayed() == false)
            {
                driver.findElement(By.id("account-chooser-link")).click();
                Thread.sleep(2000);
                driver.findElement(By.id("account-chooser-add-account")).click();
                Thread.sleep(2000);
            }

            driver.findElement(By.id("Email")).sendKeys(email);// Enter user id
            new Click().clickByid("next");//Click on next button
            driver.findElement(By.id("Passwd")).sendKeys(password);//Enter Password
            Thread.sleep(2000);
            driver.findElement(By.id("signIn")).click();//Submit button
            Thread.sleep(25000);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper loginAsInstructor in class Gmail",e);
        }
    }
}
