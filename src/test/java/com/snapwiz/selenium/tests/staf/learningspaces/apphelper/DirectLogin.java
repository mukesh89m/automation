package com.snapwiz.selenium.tests.staf.learningspaces.apphelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.UIElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.learningspaces.Config;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.ReadTestData;

public class DirectLogin extends Driver{
    private static Logger logger = Logger.getLogger("com.snapwiz.selenium.tests.staf.learningspaces.apphelper.DirectLogin");

    public DirectLogin(){
        Config.readconfiguration();
    }

    /* Method for direct login for learning spaces. This method enters user id and password and logs in. The login will fail is the text verified
     * is not present on the loaded page after clicking the login button. */
    public void directLogin(String dataIndex)
    {
        WebDriver driver=Driver.getWebDriver();
        try
        {
            String  username  =  ReadTestData.readDataByTagName("", "username", dataIndex);
            String  password  =  ReadTestData.readDataByTagName("", "password", dataIndex);
            logger.log(Level.INFO,"Logging in using Direct Login of Learning Spaces");
            driver.get(Config.baseURL + "/");
            logger.log(Level.INFO,"Base URL for Direct login is:"+ Config.baseURL);
            driver.findElement(By.id("username")).sendKeys(username);
            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.id("loginSubmitBtn")).click();
            logger.log(Level.INFO,"Clicked on Submit Button of Login page");
            int welcompopup = driver.findElements(By.className("al-dialog-welcome-close-icon")).size();
            if(welcompopup==1)
            {
                driver.findElement(By.className("al-dialog-welcome-close-icon")).click();
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
            WebDriver driver=Driver.getWebDriver();
            driver.get(Config.baseURL);
            new UIElement().waitAndFindElement(By.id("username"));
            driver.findElement(By.id("username")).sendKeys(Config.cmsUserName);
            driver.findElement(By.id("password")).sendKeys(Config.cmsPassword);
            driver.findElement(By.id("loginSubmitBtn")).click();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in CMSLogin in class DirectLogin",e);
        }
    }

    public void publisherAdmin()
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            driver.get(Config.baseURL);
            new UIElement().waitAndFindElement(By.id("username"));
            driver.findElement(By.id("username")).sendKeys(Config.publisheradmin);
            driver.findElement(By.id("password")).sendKeys(Config.cmsPassword);
            driver.findElement(By.id("loginSubmitBtn")).click();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in CMSLogin in class DirectLogin",e);
        }
    }

    public void directLoginWithCreditial(String username,String password, int dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            driver.get(Config.baseURL);
            new UIElement().waitAndFindElement(By.id("username"));
            driver.findElement(By.id("username")).sendKeys(username);
            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.id("loginSubmitBtn")).click();
        }
        catch(Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in the method directLoginWithCreditial in class 'DirectLogin'",e);
        }
    }

  /*  Author Mukesh
    This method is use for login with different type of user(SME,PD) of Different courses*/

    public void loginWithDifferentRole(int course_id,String role,int dataIndex)
    {
        try{

            new DBConnect().Connect();
            ResultSet rs=DBConnect.st.executeQuery("select id, username from t_user where id in (SELECT user_id FROM t_course_user where course_id = '"+course_id+"') and id in(select user_id from t_user_role where type = '"+role+"')");
            String id="";
            String userName="";
            while (rs.next())
            {
                id=rs.getString("id");
                userName=rs.getString("username");

            }
            System.out.println("UserName is:"+userName);
            DBConnect.st.executeUpdate("update t_user set password = '6ab14d3be1858b6e8f9f223b6980c7a6dc087a1d4871255f7fc366535762f2a90213d242f2eaa0ab' where id ='" + id + "'");
            directLoginWithCreditial(userName,"snapwiz",dataIndex);

        }catch(Exception e){
            Assert.fail("Exception in method loginWithDifferentRole of class DirectLogin");
        }
    }



    public void loginAsQAVendorRole(String dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String userId = ReadTestData.readDataByTagName("", "user_id", dataIndex);
            System.out.println("userId : " + userId);
            new LoginUsingLTI().ltiLogin(dataIndex);//Login as a user
            new DBConnect().Connect();
            DBConnect.st.executeUpdate("update t_user_role set permission = 'OP_QA' where user_id = (select id from t_user where username ='" + userId + LoginUsingLTI.appendChar + "');");
            DBConnect.st.executeUpdate("update t_user set password = '6ab14d3be1858b6e8f9f223b6980c7a6dc087a1d4871255f7fc366535762f2a90213d242f2eaa0ab' where username ='" + userId + LoginUsingLTI.appendChar + "';");
            //directLoginWithCreditial(userId + LoginUsingLTI.appendChar, "snapwiz",Integer.parseInt(dataIndex));
            new ReInitDriver().startDriver("firefox");
            driver.get(Config.baseURL);
            new UIElement().waitAndFindElement(By.id("username"));
            driver.findElement(By.id("username")).sendKeys(userId + LoginUsingLTI.appendChar);
            driver.findElement(By.id("password")).sendKeys("snapwiz");
            driver.findElement(By.id("loginSubmitBtn")).click();

        }
        catch(Exception e)
        {
            Assert.fail("Exception in CMSLogin in class DirectLogin",e);
        }
    }

    public ArrayList<String> loginWithDifferentRole(String course_id,String role,int dataIndex,String startFrom)
    {
        ArrayList<String> elementsList = new ArrayList<String>();
        try{
            new DBConnect().Connect();
            ResultSet rs=DBConnect.st.executeQuery("select id,username,firstname,lastname from t_user where id in (SELECT user_id FROM t_course_user where course_id = 253) and id in(select user_id from t_user_role where type = '"+role+"') limit "+startFrom+",1;");
            String id="";
            String userName="";
            while (rs.next())
            {
                elementsList.add(rs.getString("id"));
                elementsList.add(rs.getString("username"));
                elementsList.add(rs.getString("firstname"));
                elementsList.add(rs.getString("lastname"));
                id=rs.getString("id");
                userName=rs.getString("userName");
            }
            DBConnect.st.executeUpdate("update t_user set password = '6ab14d3be1858b6e8f9f223b6980c7a6dc087a1d4871255f7fc366535762f2a90213d242f2eaa0ab' where id ='" + id + "'");
            directLoginWithCreditial(userName, "snapwiz", dataIndex);

        }catch(Exception e){
            Assert.fail("Exception in method loginWithDifferentRole of class DirectLogin",e);
        }

        return elementsList;
    }

    public void directLoginWithCreditial(String dataIndex)
    {
        try
        {
            WebDriver driver=Driver.getWebDriver();
            String username = ReadTestData.readDataByTagName("", "username", dataIndex);
            String password = ReadTestData.readDataByTagName("", "password", dataIndex);
            driver.get(Config.baseURL);
            new UIElement().waitAndFindElement(By.id("username"));
            driver.findElement(By.id("username")).sendKeys(username);
            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.id("loginSubmitBtn")).click();
        }
        catch(Exception e)
        {
            new Screenshot().captureScreenshotFromTestCase();
            Assert.fail("Exception in the method directLoginWithCreditial in class 'DirectLogin'",e);
        }
    }

}

