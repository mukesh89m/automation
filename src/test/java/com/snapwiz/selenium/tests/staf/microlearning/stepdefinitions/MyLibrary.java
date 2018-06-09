package com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions;

import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.*;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by Priti on 23-09-2016.
 */
public class MyLibrary {

    MyLibraryPage myLibraryPage = PageFactory.initElements(CucumberDriver.getWebDriver(), MyLibraryPage.class);
    ExplorePage explorePage = PageFactory.initElements(CucumberDriver.getWebDriver(), ExplorePage.class);
    TrueFalsePage trueFalsePage = PageFactory.initElements(CucumberDriver.getWebDriver(), TrueFalsePage.class);
    VideoPage videoPage = PageFactory.initElements(CucumberDriver.getWebDriver(),VideoPage.class);
    RichTextPage richTextPage = PageFactory.initElements(CucumberDriver.getWebDriver(),RichTextPage.class);
    String lessonTitleExplorePage = "";
    int lessonCountBeforeRemove;
    String slideType = "";


    @When("^I add Lesson to My library$")
    public void I_add_Lesson_to_My_library() throws Throwable {
        Thread.sleep(3000);
        for (int i = 0; i <= myLibraryPage.lessonCard.size(); i++) {
            WebDriverUtil.clickOnElementUsingJavascript(myLibraryPage.lessonCard.get(i));
            String buttonText = myLibraryPage.addToMyLibraryButton.getText().trim();
            String expectedButtonText = "Add To My Library";
            if (buttonText.equals(expectedButtonText)) {
                lessonTitleExplorePage = explorePage.lessonTitlePreviewPage.getText().trim();
                myLibraryPage.addToMyLibraryButton.click();
                break;
            } else {
                myLibraryPage.slideNavigationArrow.get(0).click();
            }
        }
        Thread.sleep(3000);
    }

    @Then("^I should see \"([^\"]*)\" button$")
    public void I_should_see_button(String buttonLabel) throws Throwable {
        Thread.sleep(3000);
        Assert.assertEquals("Button text is not as expected", buttonLabel.trim(), myLibraryPage.addToMyLibraryButton.getText().trim());
    }

    @Then("^I should see the Added Micro Lesson$")
    public void I_should_see_the_Added_Micro_Lesson() throws Throwable {
        boolean found = false;
        List<WebElement> lessonTitleLibraryPage = myLibraryPage.lessonTitleLibrary;
        for (int i = 0; i <= lessonTitleLibraryPage.size() - 1; i++) {
            if (lessonTitleLibraryPage.get(i).getText().trim().equals(lessonTitleExplorePage)) {
                found = true;
                break;
            }
        }
        if (found == false) {
            Assert.fail("Lesson not found in My library page");
        }
    }

    @When("^I click on the Remove Button on lesson card$")
    public void I_click_on_the_Remove_Button_on_lesson_card() throws Throwable {
        lessonCountBeforeRemove = myLibraryPage.lessonCard.size();
        WebDriverUtil.mouseHover(By.className("ml-lesson-img"));
        WebDriverUtil.clickOnElementUsingJavascript(myLibraryPage.removeLink.get(0));
    }

    @And("^I click on the \"([^\"]*)\" in the Remove popup$")
    public void I_click_on_the_in_the_Remove_popup(String buttonTxt) throws Throwable {
        if (buttonTxt.equals("No,Cancel")) {
            WebDriverUtil.clickOnElementUsingJavascript(myLibraryPage.cancelButton.get(1));
        } else if (buttonTxt.equals("Yes,Remove")) {
            WebDriverUtil.clickOnElementUsingJavascript(myLibraryPage.removeButton);
        } else {
            Assert.fail("Invalid Argument of Remove pop up");
        }
    }

    @Then("^I should not see the Removed Lesson$")
    public void I_should_not_see_the_Removed_Lesson() throws Throwable {
        Thread.sleep(5000);
        int lessonCountAfterRemove = myLibraryPage.lessonCard.size();
        if (lessonCountBeforeRemove <= lessonCountAfterRemove) {
            Assert.fail("Lesson is not deleted");
        }
    }

    @When("^I add all Slide Type in lesson$")
    public void I_add_all_Slide_Type_in_lesson(DataTable datatable) throws Throwable {
        for (Map<String, String> data : datatable.asMaps(String.class, String.class)) {
            slideType = data.get("Slide Type");
            if (slideType.equals("Video")) {
                videoPage.video_link.click();
                videoPage.search_textbox.sendKeys("Software Testing");
                Thread.sleep(2000);
                videoPage.search_button.get(1).click();
                Thread.sleep(3000);
                WebDriverUtil.waitForAjax(CucumberDriver.getWebDriver(), 60);
                WebDriverUtil.clickOnElementUsingJavascript(videoPage.addToSlide_button);
                WebDriverUtil.waitTillVisibilityOfElement(videoPage.editSlide_link, 60);
                Assert.assertTrue("Added video slide is displaying in new slide page", WebDriverUtil.isElementPresent(videoPage.byUploadedVideo_slide));
            }
            else if (slideType.equals("Question")) {
                videoPage.newSlide_label.click();
                trueFalsePage.createQuestion_slide.click();
                trueFalsePage.truefalseQuestion.click();
                TrueFalseQuestionSlide.questionText = "True false question text";
                trueFalsePage.trueFalseQuestionText.sendKeys(TrueFalseQuestionSlide.questionText);
                trueFalsePage.trueOption.click();
                trueFalsePage.addToSlide_Button.click();
                WebDriverUtil.waitTillVisibilityOfElement(videoPage.editSlide_link, 60);
                boolean questionSlide = trueFalsePage.questionSlidePreview.isDisplayed();
                Assert.assertTrue("Added Question slide is displaying in new slide page",questionSlide);
            }
            else if (slideType.equals("Rich Text")){
                videoPage.newSlide_label.click();
                WebDriverUtil.clickOnElementUsingJavascript(CucumberDriver.getWebDriver().findElement(By.cssSelector(".icon-rich-text")));
                richTextPage.froala_editor_text_box.sendKeys("Software Testing");
                Thread.sleep(500);
                trueFalsePage.addToSlide_Button.click();
                WebDriverUtil.waitTillVisibilityOfElement(videoPage.editSlide_link, 60);
                Assert.assertTrue("I am not able to see the slide added",richTextPage.textSlidePreview.isDisplayed());
            }
        }


    }
    @And("^I click on the Play Button on lesson card$")
    public void I_click_on_the_Play_Button_on_lesson_card() throws Throwable {
        WebDriverUtil.mouseHover(By.className("ml-lesson-img"));
        WebDriverUtil.clickOnElementUsingJavascript(myLibraryPage.playLink.get(0));
    }

    @Then("^I should see \"([^\"]*)\" as header label on MyLibrary$")
    public void I_should_see_as_header_label_on_MyLibrary(String label) throws Throwable {
        Assert.assertEquals("Header label not as expected",label.trim(),myLibraryPage.headerLabel.getText().trim());

    }

    @And("^I should see the label \"([^\"]*)\" on the slide$")
    public void I_should_see_the_label_on_the_slide(String slideNum) throws Throwable {
       Assert.assertEquals("Slide count wrong",slideNum.trim(),myLibraryPage.slideCount.getText().trim());
    }

    @When("^I click on the play button of video slide$")
    public void I_click_on_the_play_button_of_video_slide() throws Throwable {
        CucumberDriver.getWebDriver().switchTo().frame(videoPage.youtube_frame);
        WebDriverUtil.clickOnElementUsingJavascript(videoPage.play_button);
        CucumberDriver.getWebDriver().switchTo().defaultContent();

    }

    @Then("^I should see the Pause button in the video slide$")
    public void I_should_see_the_Pause_button_in_the_video_slide() throws Throwable {
        CucumberDriver.getWebDriver().switchTo().frame(videoPage.youtube_frame);
        WebDriverUtil.clickOnElementUsingJavascript(myLibraryPage.pauseVideoButton);
        CucumberDriver.getWebDriver().switchTo().defaultContent();
    }

    @When("^I click on the Next slide button$")
    public void I_click_on_the_Next_slide_button() throws Throwable {
        WebDriverUtil.clickOnElementUsingJavascript(myLibraryPage.nextSlideButton);

    }

    @Then("^I should see the Question Attempt score card for question slide$")
    public void I_should_see_the_Question_Attempt_score_card_for_question_slide() throws Throwable {
        WebDriverUtil.waitTillVisibilityOfElement(myLibraryPage.scoreCard,30);
        Assert.assertTrue("Question score card is not displayed",WebDriverUtil.isElementPresent(By.cssSelector(".question-attempt-score")));
        String score = myLibraryPage.scoreCard.getText().trim();
        if (score == null){
            Assert.fail("Score card displayed is Null");
        }
    }

    @When("^I click on the Previous slide button$")
    public void I_click_on_the_Previous_slide_button() throws Throwable {
        WebDriverUtil.clickOnElementUsingJavascript(myLibraryPage.previousButton);
        Thread.sleep(3000);
    }

    @And("^I should not see the Next slide button$")
    public void I_should_not_see_the_Next_slide_button() throws Throwable {
        Thread.sleep(3000);
        boolean found = myLibraryPage.nextSlideButton.isDisplayed();
        Assert.assertFalse("Next button is displayed on last slide",found);
    }

    @And("^I should see added Rich Text slide$")
    public void I_should_see_added_Rich_Text_slide() throws Throwable {
        Assert.assertTrue("Rich text slide is not displayed",WebDriverUtil.isElementPresent(By.cssSelector("#dl-rich-text-preview")));
    }

    @And("^I click on the \"([^\"]*)\" in the Skip Slide popup$")
    public void I_click_on_the_in_the_Skip_Slide_popup(String btnTxt) throws Throwable {
        if (btnTxt.equals("No,Cancel")) {
            WebDriverUtil.clickOnElementUsingJavascript(myLibraryPage.skipSlideCloseButton.get(1));
        }
        else if (btnTxt.equals("Yes")){
            WebDriverUtil.clickOnElementUsingJavascript(myLibraryPage.skipSlideYesButton);
        }
    }

    @When("^I enter the Answer in \"([^\"]*)\" slide$")
    public void I_enter_the_Answer_in_slide(String arg1) throws Throwable {
        WebDriverUtil.clickOnElementUsingJavascript(trueFalsePage.trueFalseAnswerSelect.get(0));
    }
}
























