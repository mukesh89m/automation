package com.snapwiz.selenium.tests.staf.edulastic.apphelpers;

import com.snapwiz.selenium.tests.staf.framework.controller.Driver;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

/**
 * Created by pragyas on 02-02-2016.
 */
public class OpenMixPanel extends Driver {

    public void openMixpanelAndAddCookies(){
        WebDriver driver=Driver.getWebDriver();
        try{
            driver.get("https://.mixpanel.com/404");
            Cookie cookie = new Cookie("mp_optout", "1", ".mixpanel.com", "/", null);
            driver.manage().addCookie(cookie);

        }catch (Exception e)
        {
            Assert.fail("Exception in testcase 'openMixpanelAndAddCookies' in class 'OpenMixPanel'", e);

        }

    }
}
