package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.filterAtQuestionLevel;

import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Assignment;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Classes;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.Navigator;
import com.snapwiz.selenium.tests.staf.edulastic.apphelpers.School;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Google.SignIn;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login.LoginPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.ExplicitWait;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by shashank on 15-04-2015.
 */
public class FilterAtQuestionLevel extends Driver{

    String url="http://edulastic-automation.snapwiz.net/";
    @Test(priority = 1)
    public void filterAtQuestionLevel()
    {
        String instructorFirst="filterAtQuestionLevelinsFirst";
        String instructorSecond="filterAtQuestionLevelinsSecond";
        String instructorThird="filterAtQuestionLevelinsThird";
        String instructorForth="filterAtQuestionLevelinsForth";
        String password="Snapwiz@123";
        String appendCharFirst="a";
        String appendCharSecond="b";
        String appendCharThird="c";
        String appendCharForth="d";
        String classCode=null;
        int index=10;
        try
        {
            googleSignUpForAdmin_Edulastic(instructorFirst,password);//signup using google
            new School().createWithOnlyName(appendCharFirst, index);//create school
            classCode = new Classes().createClass(appendCharFirst,index);//create class
            new Navigator().logout();
            driver.close();
            reInitDriver();
            driver.get(url);
            googleSignUpForAdmin_Edulastic(instructorSecond,password);//signup using google
            new School().createWithOnlyName(appendCharSecond, index);//create school
            classCode = new Classes().createClass(appendCharSecond,index);//create class
            new Navigator().logout();
            driver.close();
            reInitDriver();
            driver.get(url);
            googleSignUpForAdmin_Edulastic(instructorThird,password);//signup using google
            new School().createWithOnlyName(appendCharThird, index);//create school
            classCode = new Classes().createClass(appendCharThird,index);//create class
            new Navigator().logout();
            driver.close();
            reInitDriver();
            driver.get(url);
            googleSignUpForAdmin_Edulastic(instructorForth,password);//signup using google
            new School().createWithOnlyName(appendCharForth, index);//create school
            classCode = new Classes().createClass(appendCharForth,index);//create class
            new Navigator().logout();
            driver.close();
            reInitDriver();
            driver.get(url);
            googleSignIn_Edulastic(instructorFirst,password);
            new ExplicitWait().explicitWaitbyxpath("//div[@class='nav-menu-toggle']");
            new Click().clickByXpath("//div[@class='nav-menu-toggle']/i");
            new Assignment().create(10,"truefalse");
            new Assignment().assignToStudent(10,appendCharFirst);
            new Navigator().logout();
            driver.close();
            reInitDriver();
            driver.get(url);
            googleSignIn_Edulastic(instructorSecond,password);
            new Assignment().create(11,"truefalse");
            new Assignment().assignToStudent(11);
            googleSignIn_Edulastic(instructorThird,password);
            new Assignment().create(12,"truefalse");
            new Assignment().assignToStudent(12);
            googleSignIn_Edulastic(instructorForth,password);
            new Assignment().create(13,"truefalse");
            new Assignment().assignToStudent(13);



        }
        catch (Exception e)
        {
            Assert.fail("Exception in FilterAtQuestionLevel class in filterAtQuestionLevel method",e);
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
