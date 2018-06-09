package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R1910;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.TocSearch;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by priyanka on 2/21/2015.
 */
public class InstructorShouldBeAbleToBrowseForVariousElements extends Driver{
        @Test(priority = 1, enabled = true)
        public void toCheckTheBrowseResultsBasedOnAFilterSelected() {
            try {
                //tc row no 239
                TocSearch tocSearch = PageFactory.initElements(driver, TocSearch.class);
                new LoginUsingLTI().ltiLogin("239");//login as instructor
                new TOCShow().chaptertree();//click on toc
                tocSearch.getTocSearch().click();//click on toc search
                tocSearch.getImageIcon().click();//click on image icon
                Thread.sleep(2000);
                String chapterHeading = tocSearch.getChapterLevel().getText();
                Assert.assertEquals(chapterHeading, "Chapter Level", "ChapterLevel Heading is not displaying");

            } catch (Exception e) {
                Assert.fail("Exception in test case toCheckTheBrowseResultsBasedOnAFilterSelected in class InstructorShouldBeAbleToBrowseForVariousElements", e);
            }
        }

        @Test(priority = 2, enabled = true)
        public void theFormatForEveryCardEntryForAChapterLevelBrowseResults() {
            try {
                //tc row no 242
                TocSearch tocSearch = PageFactory.initElements(driver, TocSearch.class);
                new LoginUsingLTI().ltiLogin("242");//login as instructor
                new TOCShow().chaptertree();//click on toc
                tocSearch.getTocSearch().click();//click on toc search
                tocSearch.getTocSearch().sendKeys("mitosis ", Keys.chord(Keys.SHIFT, Keys.NUMPAD9) + "also, karyokinesis)");
                tocSearch.getSearch_Icon().click();//click on search
                Thread.sleep(2000);
                tocSearch.getAnimationIcon().click();//click on animation icon
                Thread.sleep(1000);
                String text = tocSearch.getAnimationText1().getText();
                Assert.assertEquals(text, "Animation 2.1: Atoms and Elements", "Animation 2.1: Atoms and Elements is not present at the first line");
                boolean animationicon = tocSearch.getAnimation1().isDisplayed();
                if (animationicon == false) {
                    Assert.fail("Animation icon is not displayong at 2nd line");
                }
                String animationText2 = tocSearch.getAnimationtext2().getText();
                if (!animationText2.contains("This animation")) {
                    Assert.fail("Description is not at the 2nd line");
                }

                String animationText3 = tocSearch.getAnimationtext2().getText();
                if (!animationText3.contains("also")) {
                    Assert.fail(" Description with highlighted search text is not displayed at line 2");
                }

                String highlightedText = tocSearch.getHighlightedText().getAttribute("title");
                if (!text.equals(highlightedText)) {
                    Assert.fail("The search text is not highlighted.");
                }

            } catch (Exception e) {
                Assert.fail("Exception in test case theFormatForEveryCardEntryForAChapterLevelBrowseResults in class InstructorShouldBeAbleToBrowseForVariousElements", e);
            }
        }

        @Test(priority = 3, enabled = true)
        public void theUserShouldBeAbleToBrowseTheResultsAsPerTheSelectedImageFilterIcon() {
            try {
                //tc row no 250
                TocSearch tocSearch = PageFactory.initElements(driver, TocSearch.class);
                new LoginUsingLTI().ltiLogin("250");//login as instructor
                new TOCShow().chaptertree();//click on toc
                tocSearch.getTocSearch().click();//click on toc search
                tocSearch.getTocSearch().sendKeys("mitosis ", Keys.chord(Keys.SHIFT, Keys.NUMPAD9) + "also, karyokinesis)");
                tocSearch.getSearch_Icon().click();//click on search
                Thread.sleep(2000);
                String imageHeading = tocSearch.getGlossaryHeading().getText();
                Assert.assertEquals(imageHeading, "GLOSSARY: mitosis (also, karyokinesis)", "Filter result is not refreshed");
                tocSearch.getImageIcon().click();//click on image icon
                Thread.sleep(2000);
                boolean imageSelected = tocSearch.getImageSelected().isDisplayed();
                if (imageSelected == false) {
                    Assert.fail("The image filter icon is not highlighted");
                }


                tocSearch.getGlossaryHeading().click();
                new WebDriverWait(driver, 30).until(ExpectedConditions.presenceOfElementLocated(By.className("widget-close")));
                boolean imageCloseIcon = tocSearch.getImageClose_Icon().isDisplayed();
                if (imageCloseIcon == false) {
                    Assert.fail("Clicking on image widget browse result is not taking the user to corresponding lesson with widget in viewable area");
                }

            } catch (Exception e) {
                Assert.fail("Exception in test case theUserShouldBeAbleToBrowseTheResultsAsPerTheSelectedImageFilterIcon in class InstructorShouldBeAbleToBrowseForVariousElements", e);
            }
        }

        @Test(priority = 6, enabled = true)
        public void theUserShouldBeAbleToBrowseTheResultsAsPerTheSelectedLessonFilterIcon() {
            try {
                //tc row no 259
                TocSearch tocSearch = PageFactory.initElements(driver, TocSearch.class);
                new LoginUsingLTI().ltiLogin("259");//login as instructor
                new TOCShow().chaptertree();//click on toc
                tocSearch.getTocSearch().click();//click on toc search
                tocSearch.getTocSearch().sendKeys("mitosis ", Keys.chord(Keys.SHIFT, Keys.NUMPAD9) + "also, karyokinesis)");
                tocSearch.getSearch_Icon().click();//click on search
                tocSearch.getLessonIcon().click();//click on lesson icon
                Thread.sleep(2000);
                boolean lessonSelected = tocSearch.getLessonSelected().isDisplayed();
                if (lessonSelected == false) {
                    Assert.fail("The lesson filter icon is not highlighted");
                }

                String lessonHeading = tocSearch.getGlossaryHeading().getText();
                Assert.assertEquals(lessonHeading, "1.1: The Science of Biology", "Filter result is not refreshed");
                tocSearch.getGlossaryHeading().click();
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("ch01-sec1-1")));
                boolean lessonText = tocSearch.getLessonText().isDisplayed();
                if (lessonText == false) {
                    Assert.fail("Clicking on lesson widget browse result is not taking the user to corresponding lesson with widget in viewable area");
                }
            } catch (Exception e) {
                Assert.fail("Exception in test case theUserShouldBeAbleToBrowseTheResultsAsPerTheSelectedLessonFilterIcon in class InstructorShouldBeAbleToBrowseForVariousElements", e);
            }
        }

        @Test(priority = 7, enabled = true)
        public void theUserShouldBeAbleToBrowseTheResultsAsPerTheSelectedDiscussionFilterIcon() {
            try {
                //tc row no 262
                TocSearch tocSearch = PageFactory.initElements(driver, TocSearch.class);
                new LoginUsingLTI().ltiLogin("262");//login as instructor
                new TOCShow().chaptertree();//click on toc
                tocSearch.getTocSearch().click();//click on toc search
                tocSearch.getDiscussionIcon().click();//click on discussion icon
                boolean discussionSelected = tocSearch.getDiscussionSelected().isDisplayed();
                if (discussionSelected == false) {
                    Assert.fail("The discussion filter icon is not highlighted");
                }

                String discussionHeading = tocSearch.getGlossaryHeading().getText();
                Assert.assertEquals(discussionHeading, "10.2: The Cell Cycle", "Filter result is not refreshed");
                tocSearch.getGlossaryHeading().click();
                new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
                boolean perspective = tocSearch.getPerspective().isDisplayed();
                if (perspective == false) {
                    Assert.fail("Discussion is not expanded after clicking on discussion icon");
                }


            } catch (Exception e) {
                Assert.fail("Exception in test case theUserShouldBeAbleToBrowseTheResultsAsPerTheSelectedDiscussionFilterIcon in class InstructorShouldBeAbleToBrowseForVariousElements", e);
            }
        }

        @Test(priority = 8, enabled = true)
        public void theUserShouldBeAbleToBrowseTheResultsAsPerTheSelectedAnimationFilterIcon() {
            try {
                //tc row no 265
                TocSearch tocSearch = PageFactory.initElements(driver, TocSearch.class);
                new LoginUsingLTI().ltiLogin("265");//login as instructor
                new TOCShow().chaptertree();//click on toc
                tocSearch.getTocSearch().click();//click on toc search
                tocSearch.getTocSearch().sendKeys("mitosis ", Keys.chord(Keys.SHIFT, Keys.NUMPAD9) + "also, karyokinesis)");
                tocSearch.getSearch_Icon().click();//click on search
                Thread.sleep(2000);
                tocSearch.getAnimationIcon().click();//click on animation icon
                Thread.sleep(2000);
                boolean animationSelected = tocSearch.getAnimationSelected().isDisplayed();
                if (animationSelected == false) {
                    Assert.fail("The discussion filter icon is not highlighted");
                }

                String animationHeading = tocSearch.getGlossaryHeading().getText();
                Assert.assertEquals(animationHeading, "Animation 2.1: Atoms and Elements", "Filter result is not refreshed");
                tocSearch.getGlossaryHeading().click();
                new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.className("widget-close")));
                boolean animationText = tocSearch.getImageClose_Icon().isDisplayed();
                if (animationText == false) {
                    Assert.fail("Animation is not expanded after clicking on animation icon");
                }

            } catch (Exception e) {
                Assert.fail("Exception in test case theUserShouldBeAbleToBrowseTheResultsAsPerTheSelectedAnimationFilterIcon in class InstructorShouldBeAbleToBrowseForVariousElements", e);
            }
        }

        @Test(priority = 9, enabled = true)
        public void theUserMustBeAbleToToggleBetweenFilters() {
            try {
                //tc row no 268
                TocSearch tocSearch = PageFactory.initElements(driver, TocSearch.class);
                new LoginUsingLTI().ltiLogin("268");//login as instructor
                new TOCShow().chaptertree();//click on toc
                tocSearch.getTocSearch().click();//click on toc search
                tocSearch.getTocSearch().sendKeys("mitosis ", Keys.chord(Keys.SHIFT, Keys.NUMPAD9) + "also, karyokinesis)");
                tocSearch.getSearch_Icon().click();//click on search
                Thread.sleep(2000);
                tocSearch.getImageIcon().click();//click on image icon
                Thread.sleep(2000);
                String imageHeading = tocSearch.getGlossaryHeading().getText();
                tocSearch.getLessonIcon().click();//click on lesson icon
                new WebDriverWait(driver, 600).until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//span[@class='ls-result-view-title'])[1]")));
                Thread.sleep(2000);
                String lessonHeading = tocSearch.getGlossaryHeading().getText();
                if(imageHeading.equals(lessonHeading)){
                    Assert.fail("The search results is not changed based on the re-selected filter icon");
                }
                boolean lessonSelected = tocSearch.getLessonSelected().isDisplayed();
                if (lessonSelected == false) {
                    Assert.fail("The lesson filter icon is not highlighted");
                }

                boolean image = tocSearch.getImageIcon().isDisplayed();
                if (lessonSelected == false) {
                    Assert.fail("The previously selected filter icon is highlighted");
                }

            } catch (Exception e) {
                Assert.fail("Exception in test case theUserMustBeAbleToToggleBetweenFilters in class InstructorShouldBeAbleToBrowseForVariousElements", e);
            }
        }


    }


