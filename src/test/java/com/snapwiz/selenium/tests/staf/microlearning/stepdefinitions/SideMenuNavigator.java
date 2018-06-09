package com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions;

import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.microlearning.apphelper.NavigatorHelper;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.LessonPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.When;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by mukesh on 24/8/16.
 */
public class SideMenuNavigator {

    LessonPage lesson = PageFactory.initElements(CucumberDriver.getWebDriver(), LessonPage.class);


    @When("^i navigate to \"([^\"]*)\" page$")
    public void iNavigateToPage(String menuName) throws Throwable {
        new NavigatorHelper().navigateTo(menuName);
    }

    @When("^I am navigated to \"([^\"]*)\" page$")
    public void I_am_navigated_to_page(String menuName) throws Throwable {
        new NavigatorHelper().navigateTo(menuName);
    }
}
