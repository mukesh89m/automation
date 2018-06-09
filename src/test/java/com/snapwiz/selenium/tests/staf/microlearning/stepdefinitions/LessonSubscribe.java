package com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions;

import com.galenframework.page.Page;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by navin on 9/23/2016.
 */
public class LessonSubscribe {

    HeaderPage header = PageFactory.initElements(CucumberDriver.getWebDriver(), HeaderPage.class);
    SignupPage signup = PageFactory.initElements(CucumberDriver.getWebDriver(), SignupPage.class);
    HomePage homePage = PageFactory.initElements(CucumberDriver.getWebDriver(), HomePage.class);
    LessonPage lessonPage = PageFactory.initElements(CucumberDriver.getWebDriver(), LessonPage.class);
    AuthoringPage authoringPage = PageFactory.initElements(CucumberDriver.getWebDriver(), AuthoringPage.class);

    LessonSubscribePage lessonSubscribe = PageFactory.initElements(CucumberDriver.getWebDriver(), LessonSubscribePage.class);

    Actions actions = new Actions(CucumberDriver.getWebDriver());
    String lessonName = "";
    String totalItems = "";
    String filterName;



    @And("^I should see the five star rating scale$")
    public void I_should_see_the_five_star_rating_scale() throws Throwable {
        Assert.assertEquals("Five start rating is not displayed on preview page on home page",true,lessonSubscribe.completeRatingBlock.isDisplayed());
    }

    @And("^I should see the rating in number followed by the Rating label within rounded brackets$")
    public void I_should_see_the_rating_in_number_followed_by_the_Rating_label_within_rounded_brackets() throws Throwable {
        Assert.assertEquals("rating label is not correct","(129 Ratings)",lessonSubscribe.CompleteRatingLabel.getText().trim());
    }

    @And("^I should see Top section of the slide with Label 'Add To My Library' button with plus mark$")
    public void I_should_see_Top_section_of_the_slide_with_Label_Add_To_My_Library_button_with_plus_mark() throws Throwable {
        Assert.assertEquals("addToMyLibraryButton is not correct on explore preview page","",lessonSubscribe.addToMyLibraryButton.getText().trim());
    }

}
