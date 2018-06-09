package com.snapwiz.selenium.tests.staf.edulastic.testcases.IT1.R2;

import com.snapwiz.selenium.tests.staf.edulastic.Config;
import com.snapwiz.selenium.tests.staf.edulastic.Driver;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.Login.LoginPage;
import com.snapwiz.selenium.tests.staf.edulastic.pagefactory.signup.SignUpPage;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.Click;
import com.snapwiz.selenium.tests.staf.edulastic.uihelper.TextFetch;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

/*
 * Created by Akansh on 25/8/14.
 */
public class GooglePlus extends Driver {

    @Test
    public void googlePlusLabel()
    {
        try
        {

            SignUpPage signUp = PageFactory.initElements(driver,SignUpPage.class);
            LoginPage login = PageFactory.initElements(driver,LoginPage.class);

            driver.get(Config.baseURL);//land on home page
            String str = login.getButton_WithGoogle().getText();
            Assert.assertEquals(str, "Sign in with Google", "Google registration link is not present or not proper in Sign In Page.");
            login.getButton_signUpItsFree().click();
            new WebDriverWait(driver,20).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("span[class='navbar-text']")));
            String str1 = signUp.signUpWithGoogle.getText();
            Assert.assertEquals(str1, "Sign up with Google", "With Google registration link is not present or not proper in Teacher Registration Page.");
            signUp.getButton_signIn().click();//Click on sign in button

            String str2 = login.getButton_WithGoogle().getText();
            Assert.assertEquals(str2, "Sign in with Google", "With Google registration link is not present or not proper in Student Registration Page.");
            login.getButton_WithGoogle().click(); //clicking on with google link on Student Registration page
        }
        catch (Exception e)
        {
            Assert.fail("Exception in testcase googlePlusLabel in class GooglePlus",e);
        }
    }

}
