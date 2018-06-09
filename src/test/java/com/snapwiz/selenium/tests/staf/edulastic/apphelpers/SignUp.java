package com.snapwiz.selenium.tests.staf.edulastic.apphelpers;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Edulastic.*;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Google.SignIn;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login.LoginPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.SignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.ExplicitWait;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextSend;
import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import com.snapwiz.selenium.tests.staf.framework.utility.ReportUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.snapwiz.selenium.tests.staf.edulastic.ReadTestData;

import java.sql.ResultSet;

public class SignUp
{
    public WebDriver driver = Driver.getWebDriver();

    public SignUp(){
//        new OpenMixPanel().openMixpanelAndAddCookies();
        Config.readconfiguration();
    }
	//Student registration
	public void studentRegisterViaEmail(String appendCharacter, String instructorEmail, String accessURL, int dataIndex)
	{
        String appendCharacterBuild=System.getProperty("UCHAR");
        if (appendCharacterBuild!=null)
            appendCharacter=appendCharacter+appendCharacterBuild;
		try
		{
            String methodName = new Exception().getStackTrace()[1].getMethodName();
            String firstName = methodName+"st"+appendCharacter;
            String email = methodName+"st"+appendCharacter+"@snapwiz.com";

            //new Click().clickbylinkText("Add students via e-mail"); //clicking on add students via email link
            new Click().clickbylinkText("Add students via e-mail"); //clicking on add students via email link
            new TextSend().textsendbycssSelector(Config.studentEmail,"textarea[class='as-add-students invite-students-input']");
            new Click().clickBycssselector("input[class='as-blue-btn as-invite-studentsBtn']"); //clicking on invite button

            new Gmail().login();
            new Gmail().openEmail(instructorEmail,accessURL,0);
            driver.quit();
            new ReinitDriver().reInitDriver();
            driver.get(accessURL);
			//Driver.driver.findElement(By.cssSelector("a[class='as-green-btn as-registration-studentBtn as-registration-btn']")).click();//click on student
			Thread.sleep(2000);
			driver.findElement(By.id("first-name")).clear();
			driver.findElement(By.id("first-name")).sendKeys(firstName);
			driver.findElement(By.id("user-email")).clear();
			driver.findElement(By.id("user-email")).sendKeys(email);
			driver.findElement(By.id("user-password")).clear();
			driver.findElement(By.id("user-password")).sendKeys("snapwiz");
            driver.findElement(By.id("retype-password")).sendKeys("snapwiz");
            new Click().clickBycssselector("button[class='col-xs-12 btn btn-primary btn-lg as-signup-button col-xs-12 col-sm-6 pull-right']");//clicking on 'Sign Up as a student' button present on student sign up page
            if(!(new TextFetch().textfetchbyclass("getStarted")).contains("Get Started"))
                Assert.fail("Student did not land to Dashboard after getting registered");
            ReportUtil.log("Student log in via email","Student is successfully logged in","pass");
		}
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper UserRegistration in method  student ",e);
		}
	}
	//Teacher Registration
	public void teacher(String appendCharacter,int dataIndex)
	{
        String appendCharacterBuild=System.getProperty("UCHAR");
        if (appendCharacterBuild!=null)
            appendCharacter=appendCharacter+appendCharacterBuild;
        System.out.println("appendCharacter :"+appendCharacter);
        try
		{
            String methodName = new Exception().getStackTrace()[1].getMethodName();
            String firstName = methodName+"ins"+appendCharacter;
            String email = methodName+"ins"+appendCharacter+"@snapwiz.com";
            driver.get(Config.baseURL);//land on home page
            new ExplicitWait().explicitWaitByCssSelector("span[class='btn btn-primary center-block as-goto-signup-button']");
            driver.findElement(By.cssSelector("span[class='btn btn-primary center-block as-goto-signup-button']")).click();
            new ExplicitWait().explicitWaitbyxpath("//span[contains(@class,'as-show-teacher-signup as-signup-option')]");
            driver.findElement(By.xpath("//span[contains(@class,'as-show-teacher-signup as-signup-option')]")).click();
			driver.findElement(By.id("first-name")).clear();//clear teacher 1st name
			driver.findElement(By.id("first-name")).sendKeys(firstName);//give teacher first name
			driver.findElement(By.id("user-email")).clear();
			driver.findElement(By.id("user-email")).sendKeys(email);//give teacher email
			driver.findElement(By.id("user-password")).clear();
			driver.findElement(By.id("user-password")).sendKeys("snapwiz");//give teacher password
            driver.findElement(By.id("retype-password")).sendKeys("snapwiz");//Re type the password
			driver.findElement(By.xpath("//button[@type='submit' and @mode='teacher']")).click();//click on sign up button
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("navbar-username")));
            //driver.manage().deleteAllCookies();
		    ReportUtil.log("Teacher Registration","Teacher registration is successfully completed","pass");
        }
		catch(Exception e)
		{
			Assert.fail("Exception in apphelper UserRegistration in method  teacher ",e);
		}
	}
    //Administrator Registration
    public void administrator(String appendCharacter,int dataIndex)
    {
        String appendCharacterBuild=System.getProperty("UCHAR");
        if (appendCharacterBuild!=null)
            appendCharacter=appendCharacter+appendCharacterBuild;
        try
        {
            String methodName = new Exception().getStackTrace()[1].getMethodName();
            String firstName = methodName+"admin"+appendCharacter;
            String email = methodName+"admin"+appendCharacter+"@snapwiz.com";
            driver.get(Config.baseURL);//land on home page

            new ExplicitWait().explicitWaitByCssSelector("span[class='btn btn-primary center-block as-goto-signup-button']");
            driver.findElement(By.cssSelector("span[class='btn btn-primary center-block as-goto-signup-button']")).click();
            new ExplicitWait().explicitWaitbyxpath("//span[@mode='admin']");
            driver.findElement(By.xpath("//span[@mode='admin']")).click();
            driver.findElement(By.id("first-name")).clear();//clear teacher 1st name
            driver.findElement(By.id("first-name")).sendKeys(firstName);//give teacher first name
            driver.findElement(By.id("user-email")).clear();
            driver.findElement(By.id("user-email")).sendKeys(email);//give teacher email
            driver.findElement(By.id("user-password")).clear();
            driver.findElement(By.id("user-password")).sendKeys("snapwiz");//give teacher password
            driver.findElement(By.id("retype-password")).clear();
            driver.findElement(By.id("retype-password")).sendKeys("snapwiz");//retype the password
            driver.findElement(By.xpath("//button[@type='submit' and @mode='admin ']")).click();//click on sign up button
          //  driver.manage().deleteAllCookies();
        }
        catch(Exception e)
        {
            Assert.fail("Exception in apphelper UserRegistration in method  teacher ",e);
        }
    }
    public void addAndInviteStudentViaEmail(String instructorEmail,String accessURL,int dataIndex)
    {
        try
        {
            new Click().clickbylinkText("Add students via e-mail"); //clicking on add students via email link
            new TextSend().textsendbycssSelector(Config.studentEmail, "textarea[class='as-add-students invite-students-input']");
            new Click().clickBycssselector("input[class='as-blue-btn as-invite-studentsBtn']"); //clicking on invite button
            new Gmail().login();
            new Gmail().openEmail(instructorEmail, accessURL, 0);
            driver.quit();
            new ReinitDriver().reInitDriver();
            driver.get(accessURL);
        }
        catch (Exception e)
        {
            Assert.fail("Exception in apphelper addStudentEmailToInvite in method  teacher ",e);
        }
    }

    public void studentDirectRegister(String appendCharacter, String classCode, int dataIndex)
    {
        String username = ReadTestData.readDataByTagName("", "username", Integer.toString(dataIndex));
        String appendCharacterBuild=System.getProperty("UCHAR");
        if (appendCharacterBuild!=null)
            appendCharacter=appendCharacter+appendCharacterBuild;
        try
        {
            String methodName = new Exception().getStackTrace()[1].getMethodName();
            String firstName = methodName+"st"+appendCharacter;
            String email=null;
            if(username!=null){
                 email = methodName+"st"+appendCharacter;
            }
            else {
                 email = methodName+"st"+appendCharacter+"@snapwiz.com";
            }

            driver.get(Config.baseURL);
            new Navigator().studentSignUp();//Navigate to student registration page
            new TextSend().textsendbyid(firstName,"first-name"); //first name
            new TextSend().textsendbyid(classCode,"class-code"); //class code
            new TextSend().textsendbyid(email,"user-email"); //email
            new TextSend().textsendbyid("snapwiz", "user-password"); //password
            new TextSend().textsendbyid("snapwiz","retype-password"); // retype password
            driver.findElement(By.xpath("//button[@type='submit' and @mode='student']")).click();//click on sign up button
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("navbar-username")));
            ReportUtil.log("Student Registration using class code","Student registration is successfully completed","pass");

            }
        catch (Exception e)
        {
            Assert.fail("Exception in apphelper UserRegistration in method  studentDirectRegister ",e);
        }
    }

    public  String classCodeFromDB(int offSet)
    {
        String classCode = "";
        try {
            ResultSet rst = DBConnect.st.executeQuery("select code as classcode from t_class_section where code is not null limit 1 offset "+Integer.toString(offSet));
            while(rst.next())
            {
                classCode = rst.getString("classcode");
            }
            if(classCode.equals("") || classCode == null)
                Assert.fail("Not able to fetch class code from database");

        }
        catch (Exception e)
        {
            Assert.fail("Exception while fetching class code from MySQL",e);
        }
        return classCode;
    }

    public  String classNameFromDB(int offSet)
    {
        String className = "";
        try {
            ResultSet rst = DBConnect.st.executeQuery("select name as classname from t_class_section where code is not null limit 1 offset "+Integer.toString(offSet));
            while(rst.next())
            {
                className = rst.getString("classname");
            }
            if(className.equals("") || className == null)
                Assert.fail("Not able to fetch class code from database");

        }
        catch (Exception e)
        {
            Assert.fail("Exception while fetching class name from MySQL",e);
        }
        return className;
    }


    public void googleSignIn_EdulasticWithUrl(String email,String password,String url){
        try{
            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Edulastic.Login login=PageFactory.initElements(driver, com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Edulastic.Login.class);
            SignIn signIn = PageFactory.initElements(driver, SignIn.class);
            SignUpPage signUpPage=PageFactory.initElements(driver,SignUpPage.class);

            driver.get(url);
            signUpPage.signUpWithGoogle.click();//Click on button 'With Google'
            Thread.sleep(2000);
            if(driver.findElements(By.id("account-chooser-add-account")).size()!=0){
                driver.findElement(By.id("account-chooser-add-account")).click();
                Thread.sleep(5000);
            }
            signIn.getTextField_email().sendKeys(email);// Type Email
            signIn.buttonNext.get(0).click();
            signIn.getTextField_password().sendKeys(password);//Type Password
            signIn.getButton_SignIn().click();// Click button 'Sign in'
            Thread.sleep(5000);
            new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOf(login.getButton_Teacher()));
            login.getButton_Student().click();
            Thread.sleep(2000);
        }catch(Exception e){
            Assert.fail("Exception in the method 'googleSignIn_Edulastic' in the class 'SignUp'", e);
        }
    }

    public void googleSignIn_EdulasticFromSignUpWithUrl(String email,String password,String url){
        try{
            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            SignIn signIn = PageFactory.initElements(driver, SignIn.class);
            SignUpPage signUpPage=PageFactory.initElements(driver,SignUpPage.class);

            driver.get(url);
            loginPage.getButton_WithGoogle().click();//Click on button 'With Google'
            Thread.sleep(2000);
            if(driver.findElements(By.id("account-chooser-add-account")).size()!=0){
                driver.findElement(By.id("account-chooser-add-account")).click();
                Thread.sleep(5000);
            }
            signIn.getTextField_email().sendKeys(email);// Type Email
            signIn.buttonNext.get(0).click();
            signIn.getTextField_password().sendKeys(password);//Type Password
            signIn.getButton_SignIn().click();// Click button 'Sign in'
            Thread.sleep(5000);
        }catch(Exception e){
            Assert.fail("Exception in the method 'googleSignIn_Edulastic' in the class 'SignUp'", e);
        }
    }

    public void edulasticStudentSignUpWithUrl(String email,String password,String url){
        try{
            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            SignIn signIn = PageFactory.initElements(driver, SignIn.class);
            SignUpPage signUpPage=PageFactory.initElements(driver,SignUpPage.class);

            driver.get(url);
            loginPage.signUpWithGoogle.click();//Click on button 'With Google'
            Thread.sleep(2000);
            if(driver.findElements(By.id("account-chooser-add-account")).size()!=0){
                driver.findElement(By.id("account-chooser-add-account")).click();
                Thread.sleep(5000);
            }
            signIn.getTextField_email().sendKeys(email);// Type Email
            if(signIn.buttonNext.size()>0)
            {
                signIn.buttonNext.get(0).click();

            }
            signIn.getTextField_password().sendKeys(password);//Type Password
            signIn.getButton_SignIn().click();// Click button 'Sign in'
            Thread.sleep(5000);
            if(driver.findElements(By.id("submit_approve_access")).size()>0)
            {
                driver.findElement(By.id("submit_approve_access")).click();
            }
        }catch(Exception e){
            Assert.fail("Exception in the method 'googleSignIn_Edulastic' in the class 'SignUp'", e);
        }
    }

    public void googleSignIn_Edulastic(String email,String password){
        try{
            String baseUrl=Config.launchURL;
            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            SignIn signIn = PageFactory.initElements(driver, SignIn.class);

            driver.get(baseUrl);
            loginPage.getButton_WithGoogle().click();//Click on button 'With Google'
            Thread.sleep(5000);
            if(driver.findElements(By.id("account-chooser-add-account")).size()!=0){
                driver.findElement(By.id("account-chooser-add-account")).click();
                Thread.sleep(5000);
            }
            signIn.getTextField_email().sendKeys(email);// Type Email
            signIn.buttonNext.get(0).click();
            signIn.getTextField_password().sendKeys(password);//Type Password
            Thread.sleep(5000);
            signIn.getButton_SignIn().click();// Click button 'Sign in'
            Thread.sleep(5000);
        }catch(Exception e){
            Assert.fail("Exception in the method 'googleSignIn_Edulastic' in the class 'SignUp'", e);
        }
    }

    public void googleSignUp(String email,String password){
        try{
            SignUpPage signUpPage = PageFactory.initElements(driver,SignUpPage.class);
            String baseUrl=Config.launchURL;
            LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
            SignIn signIn = PageFactory.initElements(driver, SignIn.class);

            driver.get(baseUrl);
            loginPage.getButton_signUpItsFree().click();
            Thread.sleep(3000);
            //new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(signUpPage.getTab_teacher()));
            signUpPage.getTab_teacher().click();//Click on I'm a Teacher
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.id("google-openid-regestration")));
            loginPage.signUpWithGoogle.click();//Click on button 'With Google'
            Thread.sleep(5000);
            if(driver.findElements(By.id("account-chooser-add-account")).size()!=0){
                driver.findElement(By.id("account-chooser-add-account")).click();
                Thread.sleep(5000);
            }
            signIn.getTextField_email().sendKeys(email);// Type Email
            if(signIn.buttonNext.size()>0)
            {
                signIn.buttonNext.get(0).click();
            }
            signIn.getTextField_password().sendKeys(password);//Type Password
            Thread.sleep(5000);
            signIn.getButton_SignIn().click();// Click button 'Sign in'
            Thread.sleep(5000);
            if(driver.findElements(By.id("submit_approve_access")).size()>0)
            {
                driver.findElement(By.id("submit_approve_access")).click();
            }
        }catch(Exception e){
            Assert.fail("Exception in the method 'googleSignIn_Edulastic' in the class 'SignUp'", e);
        }
    }

    public void studentStaticSignUp(String firstName,String appendCharacter,String email, String classCode, int dataIndex)
    {
        String appendCharacterBuild=System.getProperty("UCHAR");
        if (appendCharacterBuild!=null)
            appendCharacter=appendCharacter+appendCharacterBuild;
       try{
           driver.get(Config.baseURL);
           new Navigator().studentSignUp();//Navigate to student registration page
           new TextSend().textsendbyid(firstName+appendCharacter,"first-name"); //first name
           new TextSend().textsendbyid(classCode,"class-code"); //class code
           new TextSend().textsendbyid(email,"user-email"); //email
           new TextSend().textsendbyid("snapwiz", "user-password"); //password
           new TextSend().textsendbyid("snapwiz","retype-password"); // retype password
           driver.findElement(By.xpath("//button[@type='submit' and @mode='student']")).click();//click on sign up button


       }catch (Exception e){
           Assert.fail("Exception in the method 'studentStaticSignUp' in the class 'SignUp'", e);

       }
    }

    public void studetnSignUpUsingUrl(int dataIndex,String url,String appendCharacter)
    {
        String appendCharacterBuild=System.getProperty("UCHAR");
        if (appendCharacterBuild!=null)
            appendCharacter=appendCharacter+appendCharacterBuild;
        try{

            String methodName = new Exception().getStackTrace()[1].getMethodName();
            String firstName = methodName+"st"+appendCharacter;
            String email = methodName+"st"+appendCharacter+"@snapwiz.com";
            driver.get(url);
            new TextSend().textsendbyid(firstName,"first-name"); //first name
            new TextSend().textsendbyid(email,"user-email"); //email
            new TextSend().textsendbyid("snapwiz", "user-password"); //password
            new TextSend().textsendbyid("snapwiz","retype-password"); // retype password
            driver.findElement(By.xpath("//button[@type='submit' and @mode='student']")).click();//click on sign up button
            new WebDriverWait(driver,30).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("navbar-username")));
            ReportUtil.log("Student sign up using url","Student is successfully registered using url","pass");

        }catch (Exception e){
            Assert.fail("Exception in apphelper UserRegistration in method  'studetnSignUpUsingUrl' ",e);

        }
    }
}
