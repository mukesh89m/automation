package com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions;


import com.snapwiz.selenium.tests.staf.framework.utility.StringUtil;
import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.microlearning.apphelper.Lesson;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.*;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;



/**
 * Created by Priti on 07-09-2016.
 */
public class TrueFalseQuestionSlide {
    TrueFalsePage trueFalsePage = PageFactory.initElements(CucumberDriver.getWebDriver(),TrueFalsePage.class);
    SideMenuLink sideMenuLink =PageFactory.initElements(CucumberDriver.getWebDriver(),SideMenuLink.class);
    AuthoringPage authoringPage =PageFactory.initElements(CucumberDriver.getWebDriver(),AuthoringPage.class);
    VideoPage videoPage = PageFactory.initElements(CucumberDriver.getWebDriver(),VideoPage.class);
    AuthorNewLessonPage authorNewLessonPage = PageFactory.initElements(CucumberDriver.getWebDriver(),AuthorNewLessonPage.class);

    static String updatedQuestionText = "";
    static String questionText = "";
    static int newSlideCount;
    static int slideCount;
    String hint = "Hint text "+ StringUtil.generateRandomString(7, StringUtil.Mode.ALPHA);
    String solution = "Solution text "+ StringUtil.generateRandomString(7, StringUtil.Mode.ALPHANUMERIC);


    @When("^I click on the Question Slide type for creating slide$")
    public void I_click_on_the_Question_Slide_type_for_creating_slide() throws Throwable {
        trueFalsePage.createQuestion_slide.click();
    }

    @When("^I click on Add To Slide Button of question slide$")
    public void I_click_on_Add_To_Slide_Button_of_question_slide() throws Throwable {
        Thread.sleep(2000);
        trueFalsePage.addToSlide_Button.click();
    }

    @When("^I click on the \"([^\"]*)\" Question slide$")
    public void I_click_on_the_Question_slide(String questionType) throws Throwable {
        if (questionType.equals("True or false")){
            trueFalsePage.truefalseQuestion.click();
        }
        if (questionType.equals("Multiple Choice")){
            trueFalsePage.multipleChoiceQuestion.click();
        }
        Thread.sleep(4000);
    }

    @When("^I click on Add To Slide Button for verifying warning message$")
    public void I_click_on_Add_To_Slide_Button_for_verifying_warning_message() throws Throwable {
        WebDriverUtil.clickOnElementUsingJavascript(videoPage.addToSlide_button);
    }

    @Then("^I should see warning message \"([^\"]*)\" for True False Question$")
    public void I_should_see_warning_message_for_True_False_Question(String message) throws Throwable {
        Assert.assertEquals("Warning message is not displayed",message.trim(), trueFalsePage.warningMessage.getText().trim());
    }

    @When("^I enter the required details in the Question slide for True false question type$")
    public void I_enter_the_required_details_in_the_Question_slide_for_True_false_question_type() throws Throwable {
        questionText = "True false question text";
        trueFalsePage.trueFalseQuestionText.sendKeys(questionText);
        trueFalsePage.trueOption.click();
    }

    @Then("^I should see added Question slide$")
    public void I_should_see_added_Question_slide() throws Throwable {
        boolean questionSlide = trueFalsePage.questionSlidePreview.isDisplayed();
        Assert.assertTrue("Added Question slide is displaying in new slide page",questionSlide);
        Thread.sleep(5000);
        slideCount = authorNewLessonPage.allSlide_Type.size();
    }

    @When("^I am on Add True False Question page$")
    public void I_am_on_Add_True_False_Question_page() throws Throwable {
        new Lesson().createLesson("TestLesson","Intermediate Level","Testing",false,"","");
        trueFalsePage.createQuestion_slide.click(); //missing line
        trueFalsePage.truefalseQuestion.click();
    }

    @When("^I update the required details in the Question slide for True false question type$")
    public void i_update_the_required_details_in_the_Question_slide_for_True_false_question_type() throws Throwable {
        trueFalsePage.trueFalseQuestionText.clear();
        updatedQuestionText = "Updated True false question text";
        trueFalsePage.trueFalseQuestionText.sendKeys(updatedQuestionText);
        trueFalsePage.falseOption.click();
    }

    @Then("^I should see updated Question slide$")
    public void i_should_see_updated_Question_slide() throws Throwable {
        System.out.println("test :- "+questionText+updatedQuestionText);
        if (questionText.equalsIgnoreCase(updatedQuestionText)) {
            Assert.fail("Question slide is not updated");
        }
    }

    @Then("^I should not see the deleted Question slide$")
    public void i_should_not_see_the_deleted_Question_slide() throws Throwable {
        Thread.sleep(3000);
        newSlideCount = authorNewLessonPage.allSlide_Type.size();
        if (slideCount == newSlideCount)
            Assert.fail("Question slide is not deleted");
    }


    @When("^I enter the \"([^\"]*)\" in the question slide$")
    public void I_enter_the_in_the_question_slide(String point) throws Throwable {
        trueFalsePage.pointIcon.click();
        trueFalsePage.pointBox.clear();
        trueFalsePage.pointBox.sendKeys(point);
    }

    @When("^I click on save point$")
    public void I_click_on_save_point() throws Throwable {
        trueFalsePage.savePoint_Button.click();
    }

    @Then("^I should see the given point \"([^\"]*)\"$")
    public void I_should_see_the_given_point(String point) throws Throwable {
       Thread.sleep(5000);
       String actualPoint= (String)((JavascriptExecutor)CucumberDriver.getWebDriver()).executeScript("return document.getElementById('points-input-tag').value");
       Assert.assertEquals("points not saved", point.trim(),actualPoint.trim());
    }

    @When("^I click on Preview Button$")
    public void I_click_on_Preview_Button() throws Throwable {
        trueFalsePage.previewButton.click();
    }

    @And("^I click on Submit Button of question slide$")
    public void I_click_on_Submit_Button_of_question_slide() throws Throwable {
        WebDriverUtil.clickOnElementUsingJavascript(trueFalsePage.submitQuestionButton);
    }

    @Then("^I should see the \"([^\"]*)\"$")
    public void I_should_see_the(String point) throws Throwable {
       Assert.assertEquals("Total score not displayed",point.trim(), trueFalsePage.totalScore.getText().trim());
    }

    @Then("^I should see red border around point edit box$")
    public void I_should_see_red_border_around_point_edit_box() throws Throwable {
        Assert.assertEquals("Tob Border color is not red", trueFalsePage.errorPointBox.getCssValue("border-top-color"),"rgba(255, 0, 0, 1)");
        Assert.assertEquals("Right Border color is not red", trueFalsePage.errorPointBox.getCssValue("border-right-color"),"rgba(255, 0, 0, 1)");
        Assert.assertEquals("Bottom Border color is not red", trueFalsePage.errorPointBox.getCssValue("border-bottom-color"),"rgba(255, 0, 0, 1)");
        Assert.assertEquals("Left Border color is not red", trueFalsePage.errorPointBox.getCssValue("border-left-color"),"rgba(255, 0, 0, 1)");
        }

    @When("^I enter Hint text in the hint text box$")
    public void I_enter_Hint_text_in_the_hint_text_box() throws Throwable {
        trueFalsePage.hintTextBox.sendKeys(hint);
    }

    @And("^I click on Hint Button$")
    public void I_click_on_Hint_Button() throws Throwable {
        trueFalsePage.hintButton.click();
    }

    @Then("^I should see the Hint text$")
    public void I_should_see_the_Hint_text() throws Throwable {
        WebDriverUtil.waitTillVisibilityOfElement(trueFalsePage.hintText,30);
        Assert.assertEquals("Hint text is not displayed",hint.trim(), trueFalsePage.hintText.getText().trim());
    }

    @When("^I enter Solution text in the solution text box$")
    public void I_enter_Solution_text_in_the_solution_text_box() throws Throwable {
        trueFalsePage.solutionTextBox.sendKeys(solution);
    }

    @Then("^I should see the Solution text$")
    public void I_should_see_the_Solution_text() throws Throwable {
        WebDriverUtil.waitTillVisibilityOfElement(trueFalsePage.solutionText,30);
        Assert.assertEquals("Solution Text is not displayed",solution.trim(), trueFalsePage.solutionText.getText().trim());
    }

    @When("^I click on Question text area of True false question type$")
    public void I_click_on_Question_text_area_of_True_false_question_type() throws Throwable {
        trueFalsePage.trueFalseQuestionText.sendKeys("t");
        trueFalsePage.trueFalseQuestionText.click();
    }

    @Then("^I should see Text Editor popup$")
    public void I_should_see_Text_Editor_popup() throws Throwable {
        Assert.assertTrue("Text Editor is not displayed", WebDriverUtil.isElementPresent(By.cssSelector("#radactor-toolbar-separate")));

    }

    @When("^I click on \"([^\"]*)\" of text editor$")
    public void I_click_on_of_text_editor(String option) throws Throwable {
        WebElement textEditorOption = CucumberDriver.getWebDriver().findElement(By.xpath("//a[@title='" + option + "']"));
          try {
            WebDriverUtil.clickOnElementUsingJavascript(textEditorOption);
        } catch (Exception e) {
            WebDriverUtil.clickOnElementUsingJavascript(textEditorOption);
        }
//        Thread.sleep(3000);
    }

    @Then("^I should see the True False question text font style according \"([^\"]*)\"$")
    public void I_should_see_the_True_False_question_text_font_style_according(String tag) throws Throwable {
        Assert.assertTrue(WebDriverUtil.isElementPresent(By.xpath("//div[@id='question-raw-content']/" + tag + "")));
    }


    @When("^I create True False Question Type having hint and solution$")
    public void I_create_True_False_Question_Type_having_hint_and_solution() throws Throwable {
        WebDriverUtil.clickOnElementUsingJavascript(videoPage.newSlide_label);
        WebDriverUtil.clickOnElementUsingJavascript(trueFalsePage.createQuestion_slide);
        WebDriverUtil.clickOnElementUsingJavascript(trueFalsePage.truefalseQuestion);
        questionText = "True false question text";
        trueFalsePage.trueFalseQuestionText.sendKeys(questionText);
        trueFalsePage.trueOption.click();
        trueFalsePage.hintTextBox.sendKeys(hint);
        trueFalsePage.solutionTextBox.sendKeys(solution);
        WebDriverUtil.clickOnElementUsingJavascript(trueFalsePage.addToSlide_Button);
        WebDriverUtil.waitTillVisibilityOfElement(videoPage.editSlide_link, 60);
    }
}
