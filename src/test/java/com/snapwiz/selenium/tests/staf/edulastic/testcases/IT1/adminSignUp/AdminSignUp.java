package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.adminSignUp;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.*;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.DBConnect;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Login;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Navigator;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Edulastic.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Google.SignIn;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login.LoginPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.manageClass.ManageClass;
import com.snapwiz.selenium.tests.staf.edulastic.Config;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.ResultSet;

/**
 * Created by shashank on 13-04-2015.
 */
public class AdminSignUp extends Driver {
    String url="http://edulastic-automation.snapwiz.net/";
    @Test(priority = 1)
public void  adminSignUp()
{

    int index=0;
        String password = "snapwizedulastic";
    String adminemail = "edulasticdministratorlogic";
    String appendChar="a8";
    String appendChar1="b8";
    int dataIndex=30;
    ManageClass manageClass= PageFactory.initElements(driver, ManageClass.class);
    try {
        String appendCharacterBuild=System.getProperty("UCHAR");
        if (appendCharacterBuild!=null) {
            appendChar = appendChar + appendCharacterBuild;
            appendChar1 = appendChar1 + appendCharacterBuild;
        }
    new SignUp().administrator(appendChar, dataIndex);//SignUp as a Admin
    new Login().loginAsDA(dataIndex,"adminSignUpadmin"+appendChar+"@snapwiz.com");//Login as a admin
    new School().createWithOnlyName(appendChar, dataIndex);//create school
    String classCode=new Classes().createClass(appendChar, dataIndex);//create class

        DBConnect.Connect();
        //fetch role value from t_user table with corresponding email id
        ResultSet rs = DBConnect.st.executeQuery("select role from t_user where email='adminSignUpadmin"+appendChar+"@snapwiz.com';");
        String username = "";
        while (rs.next()) {
            username = rs.getString("role");
            //System.out.println("username "+username);
        }
        Assert.assertTrue(username.equals("6"),"Role is not updating in the database");
        new Navigator().logout();
        //validate google sign up
        googleSignUpForAdmin_Edulastic(adminemail,password);
        new School().createWithOnlyName(appendChar1, index);//create school
        classCode = new Classes().createClass(appendChar1,index);//create class
        new Navigator().logout();
        googleSignIn_Edulastic(adminemail,password);
    }
    catch(Exception e)
    {
        Assert.fail("Exception in method adminSignUp of class AdminSignUp",e);
    }
}
    public void googleSignIn_Edulastic(String email,String password){
        try{
            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            SignIn signIn = PageFactory.initElements(driver, SignIn.class);
            driver.get(url);
            loginPage.getButton_WithGoogle().click();//Click on button 'With Google'
            Thread.sleep(5000);
            if(driver.findElements(By.id("submit_approve_access")).size()!=0){
                driver.findElement(By.id("submit_approve_access")).click();
                Thread.sleep(5000);
            }
            signIn.getTextField_email().sendKeys(email);// Type Email
            signIn.getTextField_password().sendKeys(password);//Type Password
            Thread.sleep(5000);
            signIn.getButton_SignIn().click();// Click button 'Sign in'
            Thread.sleep(5000);
        }catch(Exception e){
            Assert.fail("Exception in the method 'googleSignIn_Edulastic' in the class 'WithGoogle'", e);
        }
    }
    public void googleSignUpForAdmin_Edulastic(String email,String password){
        try{
            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            SignIn signIn = PageFactory.initElements(driver, SignIn.class);
            com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Edulastic.Login login = PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Edulastic.Login.class);
            driver.get(url);
            login.getButton_Teacher().click();//Click on Teacher button
            Thread.sleep(3000);
            loginPage.getButton_WithGoogle().click();//Click on button 'With Google'
            signIn.getTextField_email().sendKeys(email);// Type Email
            signIn.getTextField_password().sendKeys(password);//Type Password
            signIn.getButton_SignIn().click();// Click button 'Sign in'
            Thread.sleep(10000);
            if(driver.findElements(By.id("submit_approve_access")).size()!=0){
                WebElement we = driver.findElement(By.id("submit_approve_access"));
                ((JavascriptExecutor)driver).executeScript("arguments[0].click();",we);
                Thread.sleep(5000);
            }
        }catch(Exception e){
            Assert.fail("Exception in the method 'googleSignUp_Edulastic' in the class 'WithGoogle'", e);
        }
    }

}
