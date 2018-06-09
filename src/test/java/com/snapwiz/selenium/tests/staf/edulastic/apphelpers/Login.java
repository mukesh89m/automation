package com.snapwiz.selenium.tests.staf.edulastic.apphelpers;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.canvasclassroom.CanvasClassRoom;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.cleversync.CleverSync;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.ExplicitWait;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextSend;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

/**
 * Created by root on 8/8/14.
 */
public class Login extends Driver{
    public WebDriver driver = Driver.getWebDriver();

    public Login(){
        Config.readconfiguration();
    }

    public void loginAsInstructor(String appendCharacter, int dataIndex)
    {
        String appendCharacterBuild=System.getProperty("UCHAR");
        if (appendCharacterBuild!=null)
            appendCharacter=appendCharacter+appendCharacterBuild;
        try
        {
            driver.get(Config.baseURL);
            String methodName = new Exception().getStackTrace()[1].getMethodName();
            String email = methodName+"ins"+appendCharacter+"@snapwiz.com";
            new TextSend().textsendbyid(email,"login-email");
            new TextSend().textsendbyid("snapwiz","login-password");
            driver.findElement(By.cssSelector("button[class='btn btn-blue btn-md col-xs-12 col-sm-12 col-md-6 pull-right']")).click();
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("navbar-username")));
            ReportUtil.log("Instructor Login", "Teacher login is successfully completed", "pass");

        }
        catch (Exception e)
        {
           Assert.fail("Exception while logging in as student", e);
        }
    }





    public void loginAsInstructor(String appendCharacter, int dataIndex,WebDriver firefoxdriver)
    {
        String appendCharacterBuild=System.getProperty("UCHAR");
        if (appendCharacterBuild!=null)
            appendCharacter=appendCharacter+appendCharacterBuild;
        try
        {
            firefoxdriver.get(Config.baseURL);
            String methodName = new Exception().getStackTrace()[1].getMethodName();
            String email = methodName+"ins"+appendCharacter+"@snapwiz.com";
            firefoxdriver.findElement(By.id("login-email")).sendKeys(email);
            firefoxdriver.findElement(By.id("login-password")).sendKeys("snapwiz");

            firefoxdriver.findElement(By.cssSelector("button[class='btn btn-blue btn-md col-xs-12 col-sm-12 col-md-6 pull-right']")).click();
            new WebDriverWait(firefoxdriver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("navbar-username")));
        }
        catch (Exception e)
        {
            Assert.fail("Exception while logging in as student", e);
        }
    }


    public void loginAsStudent(String appendCharacter, int dataIndex)
    {
        String appendCharacterBuild=System.getProperty("UCHAR");
        if (appendCharacterBuild!=null)
            appendCharacter=appendCharacter+appendCharacterBuild;
        try
        {
            driver.get(Config.baseURL);
            String methodName = new Exception().getStackTrace()[1].getMethodName();
            String email = methodName+"st"+appendCharacter+"@snapwiz.com";
            new TextSend().textsendbyid(email,"login-email");
            new TextSend().textsendbyid("snapwiz","login-password");
            driver.findElement(By.id("signIn")).click();
            WebDriverUtil.waitForAjax(driver,60);
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("navbar-username")));
            ReportUtil.log("Student Login","Student is successfully logged in","pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception while logging in as student", e);
        }
    }

    public void loginAsStudent(String appendCharacter, int dataIndex,WebDriver webDriver)
    {
        String appendCharacterBuild=System.getProperty("UCHAR");
        if (appendCharacterBuild!=null)
            appendCharacter=appendCharacter+appendCharacterBuild;
        try
        {
            webDriver.get(Config.baseURL);
            String methodName = new Exception().getStackTrace()[1].getMethodName();
            String email = methodName+"st"+appendCharacter+"@snapwiz.com";
            webDriver.findElement(By.id("login-email")).clear();
            webDriver.findElement(By.id("login-email")).sendKeys(email);
            webDriver.findElement(By.id("login-password")).clear();
            webDriver.findElement(By.id("login-password")).sendKeys("snapwiz");
            webDriver.findElement(By.cssSelector("button[class='btn btn-blue btn-md col-xs-12 col-sm-12 col-md-6 pull-right']")).click();
            new WebDriverWait(webDriver,30).until(ExpectedConditions.presenceOfElementLocated(By.className("navbar-username")));
        }
        catch (Exception e)
        {
            Assert.fail("Exception while logging in as student", e);
        }
    }
    public void signInAsAuthor(int dataIndex)
    {
        try
        {
            driver.get(Config.baseURL);
            String email ="gaurav.author@snapwiz.com";
            new TextSend().textsendbyid(email,"login-email");
            new TextSend().textsendbyid("snapwiz","login-password");
            driver.findElement(By.cssSelector("input[type='submit']")).click();
        }
        catch (Exception e)
        {
            Assert.fail("Exception while logging in as Author", e);
        }
    }

    public void directLoginAsInstructor(int dataIndex,String email)
    {
        try
        {
            driver.get(Config.baseURL);
            new TextSend().textsendbyid(email,"login-email");
            new TextSend().textsendbyid("snapwiz","login-password");
            driver.findElement(By.id("signIn")).click();
            Thread.sleep(9000);
            ReportUtil.log("Instructor direct login using mail id","Instructor is successfully logged in using mail id","pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception while logging in as student", e);
        }
    }
    public void directLoginAsInstructor(int dataIndex,String email,WebDriver driver)
    {
        try
        {
            driver.get(Config.baseURL);
            driver.findElement(By.id("login-email")).sendKeys(email);
            driver.findElement(By.id("login-password")).sendKeys("snapwiz");
            driver.findElement(By.id("signIn")).click();
            WebDriverUtil.waitForAjax(driver, 60);
        }
        catch (Exception e)
        {
            Assert.fail("Exception while logging in as student", e);
        }
    }

    public void directLoginAsInstructor(int dataIndex,String email,String password)
    {
        try
        {
            driver.get(Config.baseURL);
            new TextSend().textsendbyid(email,"login-email");
            new TextSend().textsendbyid(password,"login-password");
            driver.findElement(By.id("signIn")).click();
            ReportUtil.log("Instructor direct login using mail id and password given","Instructor is successfully logged in using mail id and password given","pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception while logging in as student", e);
        }
    }

    public void directLoginAsDATeacher(int dataIndex,String email)
    {
        try
        {
            driver.get(Config.baseURL);
            new TextSend().textsendbyid(email,"login-email");
            new TextSend().textsendbyid("snapwiz","login-password");
            driver.findElement(By.cssSelector("button[id='signIn']")).click();
        }
        catch (Exception e)
        {
            Assert.fail("Exception while logging in as student", e);
        }
    }
    public void directLoginAsDAStudent(int dataIndex,String email)
    {
        try
        {
            driver.get(Config.baseURL);
            new TextSend().textsendbyid(email,"login-email");
            new TextSend().textsendbyid("snapwiz","login-password");
            driver.findElement(By.cssSelector("input[type='submit']")).click();
        }
        catch (Exception e)
        {
            Assert.fail("Exception while logging in as student", e);
        }
    }

    public void directLoginAsStudent(int dataIndex,String email)
    {

        try
        {
            driver.get(Config.baseURL);
            new TextSend().textsendbyid(email,"login-email");
            new TextSend().textsendbyid("snapwiz","login-password");
            driver.findElement(By.id("signIn")).click();
            ReportUtil.log("Student direct login","Student is logged in successfully","pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception while logging in as student", e);
        }
    }
    public void loginAsDA(int dataIndex,String email)
    {
        try
        {
            driver.get(Config.baseURL);
            new TextSend().textsendbyid(email,"login-email");
            new TextSend().textsendbyid("snapwiz","login-password");
            driver.findElement(By.cssSelector("button[type='button']")).click();
            WebDriverUtil.waitForAjax(driver, 60);
            ReportUtil.log("DA Login","DA is successfully logged in","pass");
        }
        catch (Exception e)
        {
            Assert.fail("Exception while logging in as student", e);
        }
    }

    public void googleLogin(int dataIndex, String userType, String email, String password)
    {
        try
        {
            driver.get(Config.baseURL);
            new Click().clickByid("google-openid-login");//click on Google button in login page
            List<WebElement> emailTextbox = driver.findElements(By.id("Email"));
            if(emailTextbox.size()>0)
            {
                driver.findElement(By.id("account-chooser-link")).click();
                Thread.sleep(2000);
                driver.findElement(By.id("account-chooser-add-account")).click();
                Thread.sleep(2000);
            }

            driver.findElement(By.id("Email")).sendKeys(email);// Enter user id
            Thread.sleep(2000);
            driver.findElement(By.id("Passwd")).sendKeys(password);//Enter Password
            Thread.sleep(2000);
            driver.findElement(By.id("signIn")).click();//Submit button
            Thread.sleep(10000);
            new Click().clickByid("submit_approve_access");//click on Accept button
            if(userType.equalsIgnoreCase("teacher")) {
                new Click().clickBycssselector("a[class='as-blue-btn as-registration-teacherBtn register-openid-user']");
            }
            if(userType.equalsIgnoreCase("student")) {
                new Click().clickBycssselector("a[class='as-green-btn as-registration-studentBtn register-openid-user btn-transition']");
            }
            Thread.sleep(2000);
        }
        catch (Exception e)
        {
            Assert.fail("Exception while logging in as student", e);
        }
    }

    public void cleverSyncLogin( String email, String password)
    {
        try
        {
            CleverSync cleverSync= PageFactory.initElements(driver, CleverSync.class);
            driver.get(Config.baseURL+"/district/demo");
            cleverSync.signInWithClever.click();//click on clever sync in login page
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Log in with Clever")));
            cleverSync.logInWithClever.click();
            cleverSync.username.sendKeys(email);
            cleverSync.password.sendKeys(password);
            cleverSync.logInIntoClever.click();
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.className("navbar-username")));
        }
        catch (Exception e)
        {
            Assert.fail("Exception while logging in as student", e);
        }
    }
    public void cleverSyncLogin( String email, String password,String assessmentUrl)
    {
        try
        {
            CleverSync cleverSync= PageFactory.initElements(driver, CleverSync.class);
           // driver.get(Config.baseURL+"/district/demo");
            driver.get(assessmentUrl);
            cleverSync.signInWithClever.click();//click on clever sync in login page
            new WebDriverWait(driver,60).until(ExpectedConditions.presenceOfElementLocated(By.linkText("Log in with Clever")));
            cleverSync.logInWithClever.click();
            cleverSync.username.sendKeys(email);
            cleverSync.password.sendKeys(password);
            cleverSync.logInIntoClever.click();
            new WebDriverWait(driver,120).until(ExpectedConditions.presenceOfElementLocated(By.className("navbar-username")));
        }
        catch (Exception e)
        {
            Assert.fail("Exception while logging in as student", e);
        }
    }

    public void directLoginAsAuthor() {
        try
        {
            driver.get(Config.baseURL);
            new TextSend().textsendbyid(Config.cmsUserName,"login-email");
            new TextSend().textsendbyid(Config.cmsPassword,"login-password");
            driver.findElement(By.cssSelector("button[class='btn btn-purple btn-outline']")).click();
            new ExplicitWait().explicitWaitByClass("snapwiz-logo-div",20);

        }
        catch (Exception e)
        {
            Assert.fail("Exception while logging in as student", e);
        }
    }

    public void directLoginInGoogleClassRoom(int dataIndex,String email, String password)
    {
        try
        {
            driver.get("https://classroom.google.com");//Open the google classroom link

            if(driver.findElements(By.className("hero-text")).size()>0){
                new Click().clickByclassname("hero-text");//click on sign in button
            }
            else{

                if(driver.findElements(By.id("account-chooser-link")).size()>0)
                {
                    driver.findElement(By.id("account-chooser-link")).click();//Click on sign in with different account

                }

                driver.findElement(By.linkText("Add account")).click();//Click on add account
            }

            driver.findElement(By.id("Email")).sendKeys(email);// Enter user id
            driver.findElement(By.id("next")).click();//Click on next button
            Thread.sleep(1000);
            driver.findElement(By.id("Passwd")).sendKeys(password);//Enter Password
            Thread.sleep(1000);
            driver.findElement(By.id("signIn")).click();//Submit button

        }
        catch (Exception e)
        {
            Assert.fail("Exception while logging in as student", e);
        }
    }


    public void directLoginToCanvasClassRoom(int dataIndex)
    {
        try
        {
            driver.findElement(By.id("pseudonym_session_unique_id")).sendKeys("auto.tech1@yopmail.com");// Enter user id
            driver.findElement(By.id("pseudonym_session_password")).sendKeys("snapwiz");//Enter Password
            Thread.sleep(1000);
            driver.findElement(By.cssSelector("button[type='submit']")).click();//Submit button
            driver.findElement(By.cssSelector("input[type='submit']")).click();//click on authorize
        }
        catch (Exception e)
        {
            Assert.fail("Exception while logging in as student", e);
        }
    }


    public void loginToCanvasClassRoom(int dataIndex,String email)
    {
        try
        {
            driver.get("https://edulastic.instructure.com");//Open the canvas classroom link
            driver.findElement(By.id("pseudonym_session_unique_id")).sendKeys(email);// Enter user id
            driver.findElement(By.id("pseudonym_session_password")).sendKeys("snapwiz");//Enter Password
            Thread.sleep(1000);
            driver.findElement(By.cssSelector("button[type='submit']")).click();//Submit button
            Thread.sleep(2000);

        }
        catch (Exception e)
        {
            Assert.fail("Exception while logging in as student", e);
        }
    }

    public void  canvasLogout(){
        try{

            CanvasClassRoom canvasClassRoom = PageFactory.initElements(driver,CanvasClassRoom.class);
            canvasClassRoom.sideNavigator.get(0).click();//click on account
            Thread.sleep(1000);
            canvasClassRoom.logout.click();//click on logout
        } catch (Exception e)
        {
            Assert.fail("Exception while logging out in canvas", e);
        }
    }

}
