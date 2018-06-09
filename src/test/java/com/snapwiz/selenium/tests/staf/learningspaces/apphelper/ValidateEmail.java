package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.ComboBox;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

/*
 * Created by Sumit on 8/5/2014.
 */
public class ValidateEmail extends Driver {

    public boolean validateEmail(int dataIndex,String reportedText) {
        boolean emailReceived = false;
        WebDriver driver=Driver.getWebDriver();
        try {

            String role = ReadTestData.readDataByTagName("", "Role", Integer.toString(dataIndex));
            String context_title = ReadTestData.readDataByTagName("", "context_title", Integer.toString(dataIndex));
            String user_id = ReadTestData.readDataByTagName("", "user_id", Integer.toString(dataIndex));
            String   context_title1=null;
            new Gmail().login(Config.suppportEmail, Config.suppportEmailPassword);//log in to gmail
            if(context_title != "") {
                if (System.getProperty("UCHAR") == null) {
                    context_title1 = Config.context_title + LoginUsingLTI.appendChar;
                } else {
                    context_title1= Config.context_title + System.getProperty("UCHAR");
                }
            }
            System.out.println("context_title1"+context_title1);
            Thread.sleep(2000);
            driver.findElement(By.className("yW")).click();//open the email
            new WebDriverWait(driver,200).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[text()='" + context_title1 + "']")));
            //validate the text
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)", driver.findElement(By.xpath("//td[text()='"+context_title1+"']")));
            String emailContent = driver.findElement(By.cssSelector("div[class='a3s']")).getText();
            System.out.println("emailContent:"+emailContent);
            if(emailContent.contains(reportedText))
                emailReceived = true;

            if(role != null) {
                try {
                    if (!emailContent.contains(context_title) || !emailContent.contains(Config.context_title))
                        Assert.fail("Class section name is not displayed in the email if a student/instructor Reports a content error.");
                    if(!emailContent.contains(user_id))
                        Assert.fail("User Id is not displayed in the email if a student/instructor Reports a content error.");
                } catch (Exception e) {
                    if (!emailContent.contains(context_title) || !emailContent.contains(Config.context_title))
                        Assert.fail("Class section name is not displayed in the email if a student/instructor Reports a content error.");
                    if(!emailContent.contains(user_id))
                        Assert.fail("User Id is not displayed in the email if a student/instructor Reports a content error.");                }

            }

            List<WebElement> allDelete = driver.findElements(By.cssSelector("div[class='ar9 T-I-J3 J-J5-Ji']"));
            allDelete.get(1).click();//click on Delete
            Thread.sleep(2000);

            driver.findElement(By.cssSelector("a[title='Google Account: build@snapwiz.com']")).click();Thread.sleep(3000);//click on account
            Thread.sleep(3000);
            driver.findElement(By.id("gb_71")).click(); // To click the sign out button
            Thread.sleep(20000);
            driver.manage().deleteAllCookies();

        } catch (Exception e) {
            Assert.fail("Exception in apphelper method in validateEmail class ValidateEmail.", e);
        }
        return emailReceived;
    }
}
