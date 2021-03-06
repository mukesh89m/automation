package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/**
 * Created by Sumit on 19/8/14.
 */
public class Gmail {


    //Created by Sumit ....login to Gmail
    public  void login(String email, String password)
    {
        try
        {
            Driver.driver.get("http://www.gmail.com");
            List<WebElement> emailTextbox = Driver.driver.findElements(By.id("Email"));
            if(emailTextbox.get(0).isDisplayed() == false)
            {
                Driver.driver.findElement(By.id("account-chooser-link")).click();
                Thread.sleep(2000);
                Driver.driver.findElement(By.id("account-chooser-add-account")).click();
                Thread.sleep(2000);
            }

            Driver.driver.findElement(By.id("Email")).sendKeys(email);// Enter user id
            Thread.sleep(2000);
            Driver.driver.findElement(By.id("next")).click();// Enter user id
            Thread.sleep(2000);
            Driver.driver.findElement(By.id("Passwd")).sendKeys(password);//Enter Password
            Thread.sleep(2000);
            Driver.driver.findElement(By.id("signIn")).click();//Submit button
            Thread.sleep(25000);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper login in class Gmail",e);
        }
    }
    //Created by Sumit ....open the email and validate
    public boolean openEmailAndValidate(String textToValidate,String dataIndex)
    {
        boolean emailReceived = false;
        try
        {
            Driver.driver.findElement(By.className("yW")).click();
            Thread.sleep(3000);
            String header = Driver.driver.findElement(By.cssSelector("div[class='ha']")).getText();
            if(!header.contains(textToValidate))
                emailReceived = false;
            else
                emailReceived = true;
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper openEmailAndValidate in class Gmail",e);
        }
        return emailReceived;
    }

    //Created by Sumit ....delete the first email
    public void deleteMail(String dataIndex)
    {
        try
        {
            List<WebElement> allCheckBox = Driver.driver.findElements(By.cssSelector("div[class='T-Jo-auh']"));
            allCheckBox.get(1).click(); //select the checkbox
            Thread.sleep(3000);
            Driver.driver.findElement(By.cssSelector("div[class='ar9 T-I-J3 J-J5-Ji']")).click(); //delete the mail
            Thread.sleep(5000);
            boolean emailPresent = false;
            emailPresent = openEmailAndValidate("Content issue reported by", "0");
            if(emailPresent == true)
                Assert.fail("Unable to delete the mail.");
        }
        catch (Exception e)
        {
            Assert.fail("Exception in app helper deleteMail in class Gmail",e);
        }
    }
}
