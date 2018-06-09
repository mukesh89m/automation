package com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions;

import com.snapwiz.selenium.tests.staf.framework.utility.Properties;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.microlearning.apphelper.FilterHelper;
import com.snapwiz.selenium.tests.staf.microlearning.apphelper.SignUpHelper;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.*;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.HeaderPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by mukesh on 19/8/16.
 */
public class Home {

    HeaderPage header = PageFactory.initElements(CucumberDriver.getWebDriver(), HeaderPage.class);
    SignupPage signup = PageFactory.initElements(CucumberDriver.getWebDriver(), SignupPage.class);
    HomePage homePage = PageFactory.initElements(CucumberDriver.getWebDriver(), HomePage.class);
    LessonPage lessonPage = PageFactory.initElements(CucumberDriver.getWebDriver(), LessonPage.class);
    AuthoringPage authoringPage = PageFactory.initElements(CucumberDriver.getWebDriver(), AuthoringPage.class);
    Actions actions = new Actions(CucumberDriver.getWebDriver());
    String lessonName = "";
    String totalItems = "";

    String filterName;

    @Given("^User is on ML home page$")
    public void user_is_on_ML_home_page() throws Throwable {
        WebDriver driver = CucumberDriver.getWebDriver();
        String baseURL = Properties.getPropertyValue("BaseURL");
        if (driver != null)
            driver.get(baseURL);
        else
            System.out.println("driver is not initialized yet.");
    }


    @Then("^User should be able to see SignUp and Login button$")
    public void user_should_be_able_to_see_SignUp_and_Login_button() throws Throwable {
        Assert.assertEquals("Sign Up link is not present in the application page", "Sign Up", signup.signUp_link.getText().trim());
        Assert.assertEquals("Login link is not present in the application page", "Login", signup.login_button.getText().trim());

    }

    @When("^User clicks on SignUp button$")
    public void user_clicks_on_SignUp_button() throws Throwable {
        WebDriverUtil.clickOnElementUsingJavascript(signup.signUp_link); //click on the sign up link
    }

    /**
     * this will search lessons by text on landing page of ML
     *
     * @param searchText
     * @throws Throwable
     * @author murthis
     */
    @When("^I search lesson by text \"([^\"]*)\"$")
    public void userSearchesLessonByText(String searchText) throws Throwable {
        header.SearchBox.clear();
        header.SearchBox.sendKeys(searchText);
        header.SearchButton.click();
        WebDriverUtil.waitForAjax(CucumberDriver.getWebDriver(), 60);
    }

    /**
     * This method will be used to click on the filter icon on landing page of ML.
     * The possible filter argument values are ALL, Beginner, Intermediate and Expert.
     *
     * @param filter
     * @throws Throwable
     */
    @When("^I click on \"([^\"]*)\" filter$")
    public void userClicksOnFilter(String filter) throws Throwable {
        WebDriver driver = CucumberDriver.getWebDriver();
        driver.findElement(By.className("dl-filter-" + filter.trim().toLowerCase())).click();
        WebDriverUtil.waitForAjax(driver, 60);
    }


    @Given("^I am on ML home page$")
    public void iAmOnMLHomePage() throws Throwable {
        WebDriverUtil.launchURL();
        lessonName = homePage.lessonName.getText().trim();
        totalItems = authoringPage.totalSlides.get(0).getText().trim().substring(0, 1);

    }

    @Then("^I should see ML Logo in Header in ML home page$")
    public void I_should_see_ML_Logo_in_Header_in_ML_home_page() throws Throwable {
        Assert.assertEquals("I can't see ML Logo in Header in ML home page", header.microLessonLogo.isDisplayed(), true);
    }

    @Then("^I should see the 'Sign Up' button in the header in ML home page$")
    public void I_should_see_the_Sign_Up_button_in_the_header_in_ML_home_page() throws Throwable {
        Assert.assertEquals("I can't see the 'Sign Up' button in the header in ML home page", signup.signUp_link.isDisplayed(), true);
    }

    @Then("^I should see the 'Login' button in the header in ML home page$")
    public void I_should_see_the_Login_button_in_the_header_in_ML_home_page() throws Throwable {
        Assert.assertEquals("I can't see the 'Login' button in the header in ML home page", signup.login_button.isDisplayed(), true);
    }

    @Then("^I should see the Lesson Filter \"([^\"]*)\" in the  page body$")
    public void I_should_see_the_Lesson_Filter_in_the_page_body(String filterName) throws Throwable {
        WebDriver driver = CucumberDriver.getWebDriver();
        this.filterName = filterName.substring(0, 1).toUpperCase() + filterName.substring(1);
        Assert.assertEquals("I should see the Lesson Filter \"([^\"]*)\" in the  page body", driver.findElement(By.xpath("//div[@id = 'micro-lesson-content']//li[contains(text(),'" + this.filterName + "')]")).isDisplayed(), true);
    }


    @Then("^I should see Lesson cards on page body$")
    public void I_should_see_Lesson_cards_on_page_body() throws Throwable {
        Assert.assertEquals("I can't see Lesson cards on page body", homePage.lessonCardHolder.isDisplayed(), true);
    }

    @Then("^I should see All filter selected by default on ML home page$")
    public void I_should_see_All_filter_selected_by_default_on_ML_home_page() throws Throwable {
        Assert.assertEquals("I can't see All filter selected by default on ML home page", homePage.all_button.isDisplayed(), true);
    }

    @When("^I click on Sign Up button on Default Explore page$")
    public void iClickOnSignUpButtonOnDefaultExplorePage() throws Throwable {
        WebDriverUtil.clickOnElementUsingJavascript(signup.signUp_link);
    }

    @When("^I click on the \"([^\"]*)\" in the home page$")
    public void I_click_on_the_in_the_home_page(String filterName) throws Throwable {
        WebDriver driver = CucumberDriver.getWebDriver();
        this.filterName = filterName.substring(0, 1).toUpperCase() + filterName.substring(1);
        driver.findElement(By.xpath("//div[@id = 'micro-lesson-content']//li[contains(text(),'" + this.filterName + "')]")).click();
    }


    @And("^I should see Tick mark before the \"([^\"]*)\" Label in home page$")
    public void I_should_see_Tick_mark_before_the_Label_in_home_page(String filterName) throws Throwable {
        WebDriver driver = CucumberDriver.getWebDriver();
        this.filterName = filterName.substring(0, 1).toLowerCase() + filterName.substring(1);
        Assert.assertEquals("I can't see tick mark before the label", driver.findElement(By.xpath("//li[@class='ml-lesson-filter-btn dl-filter-" + this.filterName + " js-lesson-filter-btn white-bg selected']//span[@class='fa fa-check']")).isDisplayed(), true);
    }


    @And("^I should not see tick for other than \"([^\"]*)\" filter label on ML home page$")
    public void I_should_not_see_tick_for_other_than_filter_label_on_ML_home_page(String filterName) throws Throwable {
        WebDriver driver = CucumberDriver.getWebDriver();
        String filterList = "All Beginner Intermediate Expert";
        filterList.replace(filterName, "").trim().toLowerCase();
        String[] filterListTokens = filterList.split(" ");
        if (driver.findElements(By.xpath("//li[@class='ml-lesson-filter-btn dl-filter-" + filterListTokens[0].trim() + " js-lesson-filter-btn white-bg selected']//span[@class='fa fa-check']")).size() != 0 ||
                driver.findElements(By.xpath("//li[@class='ml-lesson-filter-btn dl-filter-" + filterListTokens[1].trim() + " js-lesson-filter-btn white-bg selected']//span[@class='fa fa-check']")).size() != 0 ||
                driver.findElements(By.xpath("//li[@class='ml-lesson-filter-btn dl-filter-" + filterListTokens[2].trim() + " js-lesson-filter-btn white-bg selected']//span[@class='fa fa-check']")).size() != 0) {
            Assert.fail("I can't not see tick for other than mentioned filter label");
        }

    }


    @Then("^I should see Search Result Indication Text as Search results for \"([^\"]*)\" on ML home page$")
    public void I_should_see_Search_Result_Indication_Text_as_Search_results_for_on_ML_home_page(String searchText) throws Throwable {
        Assert.assertEquals("I can't see Search Result Indication Text as Search results for Biology on Home page", homePage.searchResultText.getText(), "Search results for \" " + searchText + "\"");
    }

    @And("^I should see Cross icon at end of search box to clear the search on ML home page$")
    public void I_should_see_Cross_icon_at_end_of_search_box_to_clear_the_search_on_ML_home_page() throws Throwable {
        WebDriverUtil.waitTillVisibilityOfElement(homePage.closeIcon, 180);
        Assert.assertEquals("I can't see Cross icon at end of search box to clear the search on ML home page", homePage.closeIcon.isDisplayed(), true);
    }

    @Given("^I am signed up$")
    public void I_am_signed_up() throws Throwable {
        new SignUpHelper().signUpUser();
    }

    @And("^I should see All filter count is equals to addition of other three filters count$")
    public void I_should_see_All_filter_count_is_equals_to_addition_of_other_three_filters_count() throws Throwable {
        int allFilterCount = Integer.parseInt(homePage.filterCountList.get(0).getText());
        int totalFilterCount = Integer.parseInt(homePage.filterCountList.get(1).getText()) + Integer.parseInt(homePage.filterCountList.get(2).getText()) + Integer.parseInt(homePage.filterCountList.get(3).getText());

        if (allFilterCount != totalFilterCount) {
            Assert.fail("I can't see All filter count is equals to addition of other three filters count");
        }
    }

    @And("^I should see Lesson count in the \"([^\"]*)\" filter label and should match the lesson cards count on page$")
    public void I_should_see_Lesson_count_in_the_filter_label_and_should_match_the_lesson_cards_count_on_page(String filterName) throws Throwable {
        WebDriver driver = CucumberDriver.getWebDriver();
        this.filterName = filterName.substring(0, 1).toUpperCase() + filterName.substring(1);
        int filterCount = new FilterHelper().getFilterCount(this.filterName);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        if (homePage.lessonCardHolderList.size() != filterCount) {
            Assert.fail("I can't see Lesson count in the \"([^\"]*)\" filter label and should match the lesson cards count on page");
        }
    }

    @When("^I click on Cross mark on default explore Search box$")
    public void I_click_on_Cross_mark_on_default_explore_Search_box() throws Throwable {
        WebDriverUtil.clickOnElementUsingJavascript(homePage.closeIcon);
        Thread.sleep(3000);
    }

    @Then("^I should not see Search Result Indication Text with Search Text \"([^\"]*)\"$")
    public void I_should_not_see_Search_Result_Indication_Text_with_Search_Text(String searchKey) throws Throwable {
        if(!CucumberDriver.getWebDriver().getPageSource().contains(searchKey)){
            Assert.fail("I can see Search Result Indication Text with Search Text");
        }
    }

    @Then("^I should see the \"([^\"]*)\" filter is selected on ML home page$")
    public void I_should_see_the_filter_is_selected_on_ML_home_page(String filterName) throws Throwable {
        WebDriver driver = CucumberDriver.getWebDriver();
        this.filterName = filterName.substring(0, 1).toLowerCase() + filterName.substring(1);
        Assert.assertEquals("I can't see the mentioned filter", driver.findElement(By.cssSelector("li[class='ml-lesson-filter-btn dl-filter-" + this.filterName + " js-lesson-filter-btn white-bg selected']")).isDisplayed(), true);
    }


    @And("^I should see  Lessons with the search text \"([^\"]*)\"$")
    public void I_should_see_Lessons_with_the_search_text(String searchText) throws Throwable {

        boolean isSearchTextTokenAvailable;
        isSearchTextTokenAvailable = searchTextToken(searchText, homePage.lessonName);
        if (isSearchTextTokenAvailable == false) {
            isSearchTextTokenAvailable = searchTextToken(searchText, homePage.tagNames);
            if (isSearchTextTokenAvailable == false) {
                isSearchTextTokenAvailable = searchTextToken(searchText, homePage.authorName);
                if (isSearchTextTokenAvailable == false) {
                    authoringPage.lessonName.click();
                    isSearchTextTokenAvailable = searchTextToken(searchText, homePage.lessonDescription);
                    userSearchesLessonByText(searchText);
                    if (isSearchTextTokenAvailable == false) {
                        Assert.fail("I can't see Lessons with the Search Text");
                    }
                }
            }
        }
    }

    public boolean searchTextToken(String searchKey, WebElement element) throws Throwable {
        String searchTextTokens[] = searchKey.toLowerCase().split(" ");
        boolean isSearchTextTokensAvailable = false;
        for (int a = 0; a < searchTextTokens.length; a++) {
            if (StringUtils.containsIgnoreCase(element.getText().trim(), searchTextTokens[a])) {
                isSearchTextTokensAvailable = true;
                break;
            }
        }
        return isSearchTextTokensAvailable;
    }

    @When("^I mouse hover on lesson card thumbnail on the explore page and Click on preview$")
    public void I_mouse_hover_on_lesson_card_thumbnail_on_the_explore_page_and_Click_on_preview() throws Throwable {
        actions.moveToElement(homePage.lessonThumbnail).build().perform();

    }
}
