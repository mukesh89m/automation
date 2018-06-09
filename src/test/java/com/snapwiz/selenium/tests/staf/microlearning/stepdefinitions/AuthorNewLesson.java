package com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions;

import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.AuthorNewLessonPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by murthi on 16-09-2016.
 */
public class AuthorNewLesson {
    AuthorNewLessonPage authorNewLesson = PageFactory.initElements(CucumberDriver.getWebDriver(), AuthorNewLessonPage.class);

    @When("^I click on Edit Slide button$")
    public void clickOnEditSlideButton() {
        authorNewLesson.edit_slide.click();
    }

    @And("^I click on the \"([^\"]*)\" in the delete popup$")
    public void iClickOnTheInTheDeletePopup(String button) throws Throwable {
        authorNewLesson = PageFactory.initElements(CucumberDriver.getWebDriver(), AuthorNewLessonPage.class);
        switch (button.trim().toLowerCase()) {
            case "yes, delete":
            case "yes,delete":
                authorNewLesson.btnYesDelete.click();
                WebDriverUtil.waitForAjax(CucumberDriver.getWebDriver(), 60);
                break;
            case "no, cancel":
            case "no,cancel":
                authorNewLesson.btnNoCancel.click();
                break;

            default:
                Assert.assertFalse("invalid arugment", true);
        }
    }

    @Then("^I should not see added \"([^\"]*)\" slide$")
    public void iShouldNotSeeAddedSlide(String slide) throws Throwable {
        try {
            authorNewLesson.edit_slide.isDisplayed();
        } catch (NoSuchElementException nse) {
            Assert.assertFalse("Added slide " + slide + " is not deleted", false);
        } catch (Exception ex) {
            Assert.assertFalse("Some exception in the method", true);
        }
    }
}
