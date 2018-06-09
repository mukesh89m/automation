package com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions;

import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.AuthoringPage;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.SideMenuLink;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.VideoPage;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by mukesh on 6/9/16.
 */
public class AddVideoFile {


    static String url = "";
    VideoPage videoPage = PageFactory.initElements(CucumberDriver.getWebDriver(), VideoPage.class);
    SideMenuLink sideMenuLink = PageFactory.initElements(CucumberDriver.getWebDriver(), SideMenuLink.class);
    AuthoringPage authoringPage = PageFactory.initElements(CucumberDriver.getWebDriver(), AuthoringPage.class);

    @When("^I click on the slide type \"([^\"]*)\"$")
    public void iClickOnTheSlideType(String type) throws Throwable {
        Thread.sleep(5000);
        WebDriverUtil.clickOnElementUsingJavascript(CucumberDriver.getWebDriver().findElement(By.xpath(".//*[contains(@class,'ml-slider-qBox')" +
                "][contains(.,'" + type.trim() + "')]")));
    }

    @Then("^I should see \"([^\"]*)\" as header label$")
    public void iShouldSeeAsHeaderLabel(String addVideo) throws Throwable {
        Assert.assertEquals("\"Add Video\" as header label is not present!", addVideo.trim(), videoPage.slide_title.getText().trim());
    }


    @When("^I search video by text \"([^\"]*)\"$")
    public void iSearchVideoByText(String searchText) throws Throwable {
        videoPage.search_textbox.sendKeys(searchText);
        WebDriverUtil.clickOnElementUsingJavascript(videoPage.search_button.get(1));
        Thread.sleep(4000);

    }

    @Then("^I should see Video Player in Add Video page$")
    public void iShouldSeeVideoPlayerInAddVideoPage() throws Throwable {
        CucumberDriver.getWebDriver().switchTo().frame(videoPage.youtube_frame);
        WebDriverUtil.waitForAjax(CucumberDriver.getWebDriver(), 60);
        WebDriverUtil.clickOnElementUsingJavascript(videoPage.play_button);
        CucumberDriver.getWebDriver().switchTo().defaultContent();

    }

    @When("^I click on Add To Slide Button$")
    public void iClickOnAddToSlideButton() throws Throwable {
        Thread.sleep(5000);
        try {
            url = videoPage.youtube_frame.getAttribute("src");
            System.out.println("URL:" + url);
        } catch (Exception e) {
        }
        WebDriverUtil.waitForAjax(CucumberDriver.getWebDriver(), 60);
        WebDriverUtil.clickOnElementUsingJavascript(videoPage.addToSlide_button);
        WebDriverUtil.waitTillVisibilityOfElement(videoPage.editSlide_link, 60);

    }

    @Then("^I should see added video slide$")
    public void iShouldSeeAddedVideoSlide() throws Throwable {
        CucumberDriver.getWebDriver().switchTo().frame(videoPage.youtube_frame);
        boolean popup = WebDriverUtil.isElementPresent(videoPage.byAddedVideo_newsildePage);
        Assert.assertTrue("Added video slide is displaying in new slide page", popup);
        WebDriverUtil.clickOnElementUsingJavascript(videoPage.play_button);
        CucumberDriver.getWebDriver().switchTo().defaultContent();
        Assert.assertTrue("Added video slide is displaying in new slide page", WebDriverUtil.isElementPresent(videoPage.byUploadedVideo_slide));

    }

    @When("^I am on Add video page$")
    public void iAmOnAddVideoPage() throws Throwable {
        sideMenuLink.authoring_link.click();
        authoringPage.draft_link.click();
        WebDriverUtil.mouseHover(By.className("ml-lesson-img"));
        WebDriverUtil.clickOnElementUsingJavascript(authoringPage.edit_link.get(0));
        videoPage.newSlide_label.click();
        videoPage.video_link.click();

    }

    @When("^I click on the Load More Results$")
    public void iClickOnTheLoadMoreResults() throws Throwable {
        WebDriverUtil.scrollIntoView(videoPage.loadMoreResult, false);
        WebDriverUtil.clickOnElementUsingJavascript(videoPage.loadMoreResult);
    }

    @Then("^I should see next set of (\\d+) videos$")
    public void iShouldSeeNextSetOfVideos(int vedioLenght) throws Throwable {
        WebDriverUtil.waitUntilTextToBePresentInElement(videoPage.loadMoreResult, "Load More Results >>", 60);
        Assert.assertEquals("Next set of 20 vedio is not getting loaded!!", vedioLenght + 21, videoPage.videoList.size());

    }

    @When("^I click on Back arrow icon$")
    public void iClickOnBackArrowIcon() throws Throwable {
        videoPage.left_arrow.get(0).click();
    }

    @Then("^I should see Add new slide icon$")
    public void iShouldSeeAddNewSlideIcon() throws Throwable {
        Assert.assertEquals("Not navigated to the Add new silde icon!!", "New Slide".trim(), videoPage.newSlide_label.getText().trim());

    }

    @When("^I click on Cancel button$")
    public void iClickOnCancelButton() throws Throwable {
        WebDriverUtil.clickOnElementUsingJavascript(videoPage.cancel_button);
    }

    @When("^I click on the Expand icon$")
    public void iClickOnTheExpandIcon() throws Throwable {
        videoPage.videoExpand_icon.click();
        Thread.sleep(5000);
    }

    @Then("^I should see Exit full screen icon$")
    public void iShouldSeeExitFullScreenIcon() throws Throwable {

        Assert.assertEquals("Exit Full screen icon is not displaying!!", "Exit full screen", videoPage.exitFullScreen_icon.getAttribute("title").trim());

    }

    @When("^I click on the Exit full screen icon$")
    public void iClickOnTheExitFullScreenIcon() throws Throwable {
        WebDriverUtil.clickOnElementUsingJavascript(videoPage.exitFullScreen_icon);
    }


    @Then("^I should see Full screen icon$")
    public void iShouldSeeFullScreenIcon() throws Throwable {
        Assert.assertEquals("Full screen icon is not displaying!!", "Full screen", videoPage.videoExpand_icon.getAttribute("title").trim());

    }

    @When("^I click on the Edit Slide$")
    public void iClickOnTheEditSlide() throws Throwable {
        try {
            url = videoPage.youtube_frame.getAttribute("src");
        } catch (Exception e) {
        }
        WebDriverUtil.clickOnElementUsingJavascript(videoPage.editSlide_link);
    }

    @Then("^I should see Video Player in Replace Video page$")
    public void iShouldSeeVideoPlayerInReplaceVideoPage() throws Throwable {
        CucumberDriver.getWebDriver().switchTo().frame(videoPage.youtube_frame);
        WebDriverUtil.waitForAjax(CucumberDriver.getWebDriver(), 60);
        WebDriverUtil.clickOnElementUsingJavascript(videoPage.play_button);
        CucumberDriver.getWebDriver().switchTo().defaultContent();
    }

    @When("^I click on Update Slide Button$")
    public void iClickOnUpdateSlideButton() throws Throwable {
        Thread.sleep(500);
        videoPage.updateSlide_button.click();

    }

    @Then("^I should see updated replace video slide$")
    public void iShouldSeeUpdatedReplaceVideoSlide() throws Throwable {
        Thread.sleep(5000);
        String replacedUrl = videoPage.youtube_frame.getAttribute("src");
        if (url.equalsIgnoreCase(replacedUrl)) {
            Assert.fail("Video is not getting updated");
        }

    }


    @When("^I click on the Delete Slide Button$")
    public void iClickOnTheDeleteSlideButton() throws Throwable {
        new WebDriverWait(CucumberDriver.getWebDriver(), 10).until(ExpectedConditions.invisibilityOfElementLocated(By.id("slide-delete-confirm")));
        WebDriverUtil.waitTillVisibilityOfElement(videoPage.deleteSlide_link, 60);
        ((JavascriptExecutor)CucumberDriver.getWebDriver()).executeScript("window.scrollTo(0,"+videoPage.deleteSlide_link.getLocation().x+")");
        videoPage.deleteSlide_link.click();
        new WebDriverWait(CucumberDriver.getWebDriver(), 20).until(ExpectedConditions.visibilityOfElementLocated(By.id("slide-delete-confirm")));
    }


    @Then("^I should not see added video slide$")
    public void iShouldNotSeeAddedVideoSlide() throws Throwable {
        Thread.sleep(5000);
        if (videoPage.slide_container.size()>0){
            String replacedUrl = videoPage.youtube_frame.getAttribute("src");
            if (url.equalsIgnoreCase(replacedUrl)) {
                Assert.fail("Video is not getting deleted");

            }
        }
        else {
            boolean popup = WebDriverUtil.isElementPresent(videoPage.byAddedSlide_editPage);
            Assert.assertFalse("Added video slide is not getting deleted", popup);
        }

    }


}
