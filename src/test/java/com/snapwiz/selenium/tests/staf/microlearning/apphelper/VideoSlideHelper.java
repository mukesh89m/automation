package com.snapwiz.selenium.tests.staf.microlearning.apphelper;

import com.snapwiz.selenium.tests.staf.framework.utility.WebDriverUtil;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.AuthoringPage;
import com.snapwiz.selenium.tests.staf.microlearning.pagefactory.VideoPage;
import com.snapwiz.selenium.tests.staf.microlearning.stepdefinitions.CucumberDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

/**
 * Created by root on 9/22/16.
 */
public class VideoSlideHelper {
    VideoPage videoPage = PageFactory.initElements(CucumberDriver.getWebDriver(), VideoPage.class);
    public static String  videoSlideNameBeforeAdding;
    public boolean addVideoSlide(String searchVideoText){
        boolean isVideoAdded = false;
        try{
            WebDriverUtil.clickOnElementUsingJavascript(CucumberDriver.getWebDriver().findElement(By.cssSelector("div[attr-type='video']")));
            videoPage.search_textbox.sendKeys(searchVideoText);
           WebDriverUtil.clickOnElementUsingJavascript(videoPage.search_button.get(1));
            Thread.sleep(4000);
            CucumberDriver.getWebDriver().switchTo().frame(videoPage.youtube_frame);
            WebDriverUtil.waitForAjax(CucumberDriver.getWebDriver(), 60);
            WebDriverUtil.clickOnElementUsingJavascript(videoPage.play_button);
            CucumberDriver.getWebDriver().switchTo().defaultContent();
            Thread.sleep(5000);
            videoSlideNameBeforeAdding = videoPage.videoName.getText();
            WebDriverUtil.clickOnElementUsingJavascript(videoPage.addToSlide_button);
            WebDriverUtil.waitTillVisibilityOfElement(videoPage.editSlide_link, 60);
            CucumberDriver.getWebDriver().switchTo().frame(videoPage.youtube_frame);
            String videoSlideNameAfterAdding =  videoPage.videoSlideText.getText();
            WebDriverUtil.clickOnElementUsingJavascript(videoPage.play_button);
            CucumberDriver.getWebDriver().switchTo().defaultContent();
            if(videoSlideNameBeforeAdding.equals(videoSlideNameAfterAdding)){
                isVideoAdded = true;
            }
        }catch(Exception e){
            Assert.fail("Exception in the apphelper 'addVideo' in the class 'VideoHelper'", e);
        }
        return isVideoAdded;
    }

    public boolean editVideoSlide(String searchVideoText){
        boolean isVideoAdded = false;
        try{
            videoPage.editSlide_link.click();
            videoPage.search_textbox.sendKeys(searchVideoText);
            videoPage.search_button.get(1).click();
            Thread.sleep(4000);
            CucumberDriver.getWebDriver().switchTo().frame(videoPage.youtube_frame);
            WebDriverUtil.waitForAjax(CucumberDriver.getWebDriver(), 60);
            WebDriverUtil.clickOnElementUsingJavascript(videoPage.play_button);
            CucumberDriver.getWebDriver().switchTo().defaultContent();
            Thread.sleep(5000);
            videoSlideNameBeforeAdding = videoPage.videoName.getText();
            WebDriverUtil.clickOnElementUsingJavascript(videoPage.updateSlide_button);
            WebDriverUtil.waitTillVisibilityOfElement(videoPage.editSlide_link,60);
            CucumberDriver.getWebDriver().switchTo().frame(videoPage.youtube_frame);
            String videoSlideNameAfterAdding =  videoPage.videoSlideText.getText();
            WebDriverUtil.clickOnElementUsingJavascript(videoPage.play_button);
            CucumberDriver.getWebDriver().switchTo().defaultContent();
            if(videoSlideNameBeforeAdding .equals(videoSlideNameAfterAdding)){
                isVideoAdded = true;
            }
        }catch(Exception e){
            Assert.fail("Exception in the apphelper 'editVideoSlide' in the class 'VideoHelper'",e);
        }
        return isVideoAdded;
    }

}
