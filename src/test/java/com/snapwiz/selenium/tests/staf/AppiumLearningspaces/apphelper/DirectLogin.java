package com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


//import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.DBConnect;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Config;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.Driver;
import com.snapwiz.selenium.tests.staf.AppiumLearningspaces.ReadTestData;

public class DirectLogin {
    private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.DirectLogin");

    /* Method for direct login for learning spaces. This method enters user id and password and logs in. The login will fail is the text verified
     * is not present on the loaded page after clicking the login button. */
    public void directLogin(String dataIndex)
    {
        try
        {
            String  username  =  ReadTestData.readDataByTagName("", "username", dataIndex);
            String  password  =  ReadTestData.readDataByTagName("", "password", dataIndex);
            logger.log(Level.INFO,"Logging in using Direct Login of Learning Spaces");
            Driver.driver.get(Config.baseURL + "/");
            logger.log(Level.INFO,"Base URL for Direct login is:"+ Config.baseURL);
            Driver.driver.findElement(By.id("username")).sendKeys(username);
            Driver.driver.findElement(By.id("password")).sendKeys(password);
            Driver.driver.findElement(By.id("loginSubmitBtn")).click();
            logger.log(Level.INFO, "Clicked on Submit Button of Login page");
            int welcompopup = Driver.driver.findElements(By.className("al-dialog-welcome-close-icon")).size();
            if(welcompopup==1)
            {
                Driver.driver.findElement(By.className("al-dialog-welcome-close-icon")).click();
            }
        }
        catch (Exception e)
        {
            Assert.fail("Exception in directLogin in class DirectLogin",e);
        }

    }

    public void CMSLogin()
    {
        try
        {
            new Driver().firefoxDriverStart();
            logger.log(Level.INFO, "Firefox Browser Started");
            Driver.driver.get(Config.baseURL);
            new UIElement().waitAndFindElement(By.id("username"));
            Driver.driver.findElement(By.id("username")).sendKeys(Config.cmsAuthorName);
            Driver.driver.findElement(By.id("password")).sendKeys(Config.cmsPassword);
            Driver.driver.findElement(By.id("loginSubmitBtn")).click();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in CMSLogin in class DirectLogin",e);
        }
    }



    public void directLoginWithCreditial(String userName,String password, String dataIndex)
    {
        try
        {
            DBConnect.Connect();
            DBConnect.st.executeUpdate("update t_user set password = '6ab14d3be1858b6e8f9f223b6980c7a6dc087a1d4871255f7fc366535762f2a90213d242f2eaa0ab' where username ='" + userName + "'");
            //DBConnect.conn.close();
            new Driver().firefoxDriverStart();
            Driver.driver.get(Config.baseURL);
            new UIElement().waitAndFindElement(By.id("username"));
            Driver.driver.findElement(By.id("username")).sendKeys(userName);
            Driver.driver.findElement(By.id("password")).sendKeys(password);
            Driver.driver.findElement(By.id("loginSubmitBtn")).click();
        }
        catch(Exception e)
        {
            //new com.snapwiz.selenium.tests.staf.AppiumLearningspaces.apphelper.Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in the method directLoginWithCreditial in class 'DirectLogin'",e);
        }
    }
    public void publisherAdmin()
    {
        try
        {
            Driver.driver.get(Config.baseURL);
            new UIElement().waitAndFindElement(By.id("username"));
            Driver.driver.findElement(By.id("username")).sendKeys(Config.publisheradmin);
            Driver.driver.findElement(By.id("password")).sendKeys(Config.cmsPassword);
            Driver.driver.findElement(By.id("loginSubmitBtn")).click();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in CMSLogin in class DirectLogin",e);
        }
    }

}

