package com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions;

import com.snapwiz.selenium.tests.staf.microlearning.apphelper.Lesson;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.AuthorNewLessonPage;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.RichTextPage;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ThreadGuard;

/**
 * Created by murthi on 08-09-2016.
 */
public class RichTextSlide {

    RichTextPage richTextPage = PageFactory.initElements(CucumberDriver.getWebDriver(),RichTextPage.class);
    AuthorNewLessonPage authorNewLessonPage = PageFactory.initElements(CucumberDriver.getWebDriver(),AuthorNewLessonPage.class);

    @And("^I should see Froala Editor$")
    public void iShouldSeeFroalaEditor() throws Throwable {
        Assert.assertTrue("I am not able to see the Froala Editor",richTextPage.froala_editor.isDisplayed());
    }


    @Then("^I should see Add To Slide button is disabled$")
    public void iShouldSeeAddToSlideButtonIsDisabled() throws Throwable {
        Assert.assertFalse("Add To Slide button is not disalbed",richTextPage.addToSlide_button.isEnabled());
    }

    @When("^I enter the text \"([^\"]*)\" in Froala Editor$")
    public void iEnterTheTextInFroalaEditor(String text) throws Throwable {
//        richTextPage.froala_editor_text_box.click();
        richTextPage.froala_editor_text_box.sendKeys(text);
        Thread.sleep(500);
    }


    @Then("^I should see added \"([^\"]*)\" slide$")
    public void iShouldSeeAddedSlide(String type) throws Throwable {
        Assert.assertTrue("I am not able to see the slide added",authorNewLessonPage.active_slide_type.getText().trim().toLowerCase().equals
                (type.trim().toLowerCase()));
    }

    @When("^I am on Add Rich Text page$")
    public void iAmOnAddRichTextPage() throws Throwable {
        new Lesson().createLesson("TestLesson","Intermediate Level","Testing",false,"","");
        new AddVideoFile().iClickOnTheSlideType("Text");
    }

    @Then("^I should see the Text slide preview$")
    public void iShouldSeeTheTextSlidePreview() throws Throwable {
        Assert.assertTrue("Rich Text slide preview is not available",authorNewLessonPage.rich_text_preview.isDisplayed());
    }

    @When("^I click on \"([^\"]*)\" button of Froala editor$")
    public void ClickOnFormatButtonOfFroalaEditor(String format) throws Throwable {
        switch(format.trim().toLowerCase()){
            case "bold":
                richTextPage.froala_editor_btnBold.click();
                break;
            case "italic":
                richTextPage.froala_editor_btnItalic.click();
                break;
                default:
                    Assert.assertFalse("the format option is not matching any case",true);
        }
    }

    @Then("^I should see the text \"([^\"]*)\" is formatted as \"([^\"]*)\"$")
    public void iShouldSeeTheTextIsFormattedAs(String text, String format) throws Throwable {
        WebElement ele = null;
        try {
            ele = CucumberDriver.getWebDriver().findElement(By.xpath(".//*[contains(@class,'fr-element')]//*[contains(.," + text + ")]"));
            try {
                if (ele != null)
                    ele = ele.findElement(By.xpath(".//*"));
            } catch (Exception ex) {
            }


            switch (format.trim().toLowerCase()) {
                case "bold":
                    Assert.assertTrue("The text " + text + " is not formated as " + format, "strong".equals(ele.getTagName().toLowerCase()));
                    break;
                case "italic":
                    Assert.assertTrue("The text " + text + " is not formated as " + format, "em".equals(ele.getTagName().toLowerCase()));
                    break;
                default:
                    Assert.assertFalse("the format option is not matching any case", true);
            }
        } catch (Exception ex) {
            Assert.assertFalse("The text " + text + " is not formated as " + format, true);
        }
    }

}
