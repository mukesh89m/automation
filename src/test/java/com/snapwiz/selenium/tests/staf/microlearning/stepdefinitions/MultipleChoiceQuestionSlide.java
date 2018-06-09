package com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions;


import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.microlearning.apphelper.Lesson;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.TrueFalsePage;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.MultipleChoicePage;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.VideoPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Priti on 22-09-2016.
 */
public class MultipleChoiceQuestionSlide {

   MultipleChoicePage multipleChoicePage = PageFactory.initElements(CucumberDriver.getWebDriver(),MultipleChoicePage.class);
   TrueFalsePage trueFalsePage = PageFactory.initElements(CucumberDriver.getWebDriver(),TrueFalsePage.class);
   VideoPage videoPage = PageFactory.initElements(CucumberDriver.getWebDriver(),VideoPage.class);


    @Then("^I should see warning message \"([^\"]*)\" for Multiple choice Question$")
    public void I_should_see_warning_message_for_Multiple_choice_Question(String message) throws Throwable {
        Assert.assertEquals("Warning message is not displayed",message.trim(), trueFalsePage.warningMessage.getText().trim());

    }

    @When("^I enter the required details in the Question slide for Multiple choice question type$")
    public void I_enter_the_required_details_in_the_Question_slide_for_Multiple_choice_question_type() throws Throwable {
        TrueFalseQuestionSlide.questionText = "Multiple choice Question text"+ StringUtil.generateRandomString(2,StringUtil.Mode.ALPHANUMERIC);
        multipleChoicePage.multipleChoiceQuestionText.sendKeys(TrueFalseQuestionSlide.questionText);
        multipleChoicePage.multipleChoiceAnswerOptionsText.get(0).sendKeys("Option A");
        multipleChoicePage.multipleChoiceAnswerOptionsText.get(1).sendKeys("Option B");
        multipleChoicePage.multipleChoiceAnswerOptionsText.get(2).sendKeys("Option C");
        multipleChoicePage.multipleChoiceAnswerOptionsText.get(3).sendKeys("Option D");
        multipleChoicePage.multipleChoiceAnswerOptions.get(0).click();
    }

    @When("^I am on Multiple choice Question page$")
    public void I_am_on_Multiple_choice_Question_page() throws Throwable {
        new Lesson().createLesson("TestLesson", "Intermediate Level", "Testing", false, "", "");
        trueFalsePage.createQuestion_slide.click();
        trueFalsePage.multipleChoiceQuestion.click();
    }

    @When("^I update the required details in the Question slide for Multiple choice question type$")
    public void I_update_the_required_details_in_the_Question_slide_for_Multiple_choice_question_type() throws Throwable {
        multipleChoicePage.multipleChoiceQuestionText.clear();
        TrueFalseQuestionSlide.updatedQuestionText =StringUtil.generateRandomString(5,StringUtil.Mode.ALPHA) ;
        multipleChoicePage.multipleChoiceQuestionText.sendKeys(TrueFalseQuestionSlide.updatedQuestionText);
        multipleChoicePage.multipleChoiceAnswerOptionsText.get(0).clear();
        multipleChoicePage.multipleChoiceAnswerOptionsText.get(0).sendKeys("Updated option A");
        multipleChoicePage.multipleChoiceAnswerOptions.get(2).click();
    }

    @When("^I click on Question text area of Multiple choice question type$")
    public void I_click_on_Question_text_area_of_Multiple_choice_question_type() throws Throwable {
//       multipleChoicePage.multipleChoiceQuestionText.sendKeys("t");
       multipleChoicePage.multipleChoiceQuestionText.click();
    }

    @Then("^I should see the Multiple choice question text font style according \"([^\"]*)\"$")
    public void I_should_see_the_Multiple_choice_question_text_font_style_according(String tag) throws Throwable {
        Assert.assertTrue(WebDriverUtil.isElementPresent(By.xpath("//div[@id='question-mc-raw-content']/"+tag +"")));
    }

    @When("^I create Multiple choice Question Type having hint and solution$")
    public void I_create_Multiple_choice_Question_Type_having_hint_and_solution() throws Throwable {
        WebDriverUtil.clickOnElementUsingJavascript(videoPage.newSlide_label);
        WebDriverUtil.waitTillVisibilityOfElement(trueFalsePage.createQuestion_slide,30);
        WebDriverUtil.clickOnElementUsingJavascript(trueFalsePage.createQuestion_slide);
        WebDriverUtil.clickOnElementUsingJavascript(trueFalsePage.multipleChoiceQuestion);
        TrueFalseQuestionSlide.questionText = "Multiple choice Question text"+ StringUtil.generateRandomString(2,StringUtil.Mode.ALPHANUMERIC);
        multipleChoicePage.multipleChoiceQuestionText.sendKeys(TrueFalseQuestionSlide.questionText);
        multipleChoicePage.multipleChoiceAnswerOptionsText.get(0).sendKeys("Option A");
        multipleChoicePage.multipleChoiceAnswerOptionsText.get(1).sendKeys("Option B");
        multipleChoicePage.multipleChoiceAnswerOptionsText.get(2).sendKeys("Option C");
        multipleChoicePage.multipleChoiceAnswerOptionsText.get(3).sendKeys("Option D");
        multipleChoicePage.multipleChoiceAnswerOptions.get(0).click();
        trueFalsePage.hintTextBox.sendKeys("Hint text");
        trueFalsePage.solutionTextBox.sendKeys("Solution text");
        WebDriverUtil.clickOnElementUsingJavascript(trueFalsePage.addToSlide_Button);

    }
}
