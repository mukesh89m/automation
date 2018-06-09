package com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions;

import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.HeaderPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by murthi on 08-09-2016.
 */
public class Header {

    HeaderPage header = PageFactory.initElements(CucumberDriver.getWebDriver(), HeaderPage.class);

    @When("^I click on Add To Slide button$")
    public void iClickonAddToSlideButton() throws Throwable {
        Thread.sleep(500);
        header.btnAddToSlide.click();
        WebDriverUtil.waitForAjax(CucumberDriver.getWebDriver(),60);
    }
}

