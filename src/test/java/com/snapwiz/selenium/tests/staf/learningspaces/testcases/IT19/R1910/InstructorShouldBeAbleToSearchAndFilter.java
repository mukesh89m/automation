package com.snapwiz.selenium.tests.staf.learningspaces.testcases.IT19.R1910;

import com.snapwiz.selenium.tests.staf.learningspaces.Driver;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.LoginUsingLTI;
import com.snapwiz.selenium.tests.staf.learningspaces.apphelper.TOCShow;
import com.snapwiz.selenium.tests.staf.learningspaces.pagefactory.eTextbook.TocSearch;
import com.snapwiz.selenium.tests.staf.learningspaces.uihelper.MouseHover;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

/**
 * Created by priyanka on 2/21/2015.
 */
public class InstructorShouldBeAbleToSearchAndFilter extends Driver{
    @Test(priority = 1, enabled = true)
    public void instructorShouldBeAbleToSearchAndFilter() {
        try {
            //tc row no 164
            TocSearch tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new LoginUsingLTI().ltiLogin("164");//login as instructor
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            Thread.sleep(3000);
            boolean tocScreen = tocSearch.getTocScreen().isDisplayed();
            if (tocScreen == false) {
                Assert.fail("All the activities are displaying except toc search");
            }
            String image = tocSearch.getImageIcon().getAttribute("title");
            Assert.assertEquals(image, "Image", "Image icon is not displaying");
            String video = tocSearch.getVideoIcon().getAttribute("title");
            Assert.assertEquals(video, "Video", "Video icon is not displaying");
            String quiz = tocSearch.getQuizIcon().getAttribute("title");
            Assert.assertEquals(quiz, "Quiz", "Quiz icon is not displaying");
            String lesson = tocSearch.getLessonIcon().getAttribute("title");
            Assert.assertEquals(lesson, "Lesson", "Lesson icon is not displaying");
            String discussion = tocSearch.getDiscussionIcon().getAttribute("title");
            Assert.assertEquals(discussion, "Discussion", "Discussion icon is not displaying");
            String animation = tocSearch.getAnimationIcon().getAttribute("title");
            Assert.assertEquals(animation, "Animation", "Animation icon is not displaying");
            String doneButton = tocSearch.getDone_Button().getAttribute("title");
            Assert.assertEquals(doneButton, "Done", "Done button is not displaying");
            tocSearch.getDone_Button().click();//click on done
            Thread.sleep(3000);
            boolean done = tocSearch.getDone_Button().isDisplayed();
            if (done == true) {
                Assert.fail("Toc search field is not displaying");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case instructorShouldBeAbleToSearchAndFilter in class InstructorShouldBeAbleToSearchAndFilter", e);
        }
    }

    @Test(priority = 2, enabled = true)
    public void checkingTooltipForDifferentFilterIcon() {
        try {
            //tc row no 174
            TocSearch tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new LoginUsingLTI().ltiLogin("174");//login as instructor
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            new MouseHover().mouserhoverbywebelement(tocSearch.getImageIcon());
            String image = tocSearch.getImageIcon().getAttribute("title");
            Assert.assertEquals(image, "Image", "Image icon is not displaying");
            new MouseHover().mouserhoverbywebelement(tocSearch.getVideoIcon());
            String video = tocSearch.getVideoIcon().getAttribute("title");
            Assert.assertEquals(video, "Video", "Video icon is not displaying");
            new MouseHover().mouserhoverbywebelement(tocSearch.getQuizIcon());
            String quiz = tocSearch.getQuizIcon().getAttribute("title");
            Assert.assertEquals(quiz, "Quiz", "Quiz icon is not displaying");
            new MouseHover().mouserhoverbywebelement(tocSearch.getLessonIcon());
            String lesson = tocSearch.getLessonIcon().getAttribute("title");
            Assert.assertEquals(lesson, "Lesson", "Lesson icon is not displaying");
            new MouseHover().mouserhoverbywebelement(tocSearch.getDiscussionIcon());
            String discussion = tocSearch.getDiscussionIcon().getAttribute("title");
            Assert.assertEquals(discussion, "Discussion", "Discussion icon is not displaying");
            new MouseHover().mouserhoverbywebelement(tocSearch.getAnimationIcon());
            String animation = tocSearch.getAnimationIcon().getAttribute("title");
            Assert.assertEquals(animation, "Animation", "Animation icon is not displaying");
        } catch (Exception e) {
            Assert.fail("Exception in test case checkingTooltipForDifferentFilterIcon in class InstructorShouldBeAbleToSearchAndFilter", e);
        }
    }
    @Test(priority = 3, enabled = true)
    public void searchSpaceForEachElement() {
        try {
            //tc row no 180
            TocSearch tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new LoginUsingLTI().ltiLogin("180");//login as instructor
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            Thread.sleep(2000);
            tocSearch.getTocSearch().sendKeys("mitosis ", Keys.chord(Keys.SHIFT, Keys.NUMPAD9) + "also, karyokinesis)");
            tocSearch.getSearch_Icon().click();
            WebElement scroll = driver.findElement(By.xpath("(//i[contains(@class,'search-data-icon search-data-img-icon')])[1]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            boolean image = tocSearch.getImage().isDisplayed();
            if (image == false) {
                Assert.fail("Image is not displaying");
            }

            WebElement scroll1 = driver.findElement(By.xpath("(//i[contains(@class,'search-data-icon search-data-lesson-icon')])[1]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll1);
            boolean lesson = tocSearch.getLesson().isDisplayed();
            if (lesson == false) {
                Assert.fail("Lesson is not displaying");
            }

            WebElement scroll2 = driver.findElement(By.xpath("(//i[contains(@class,'search-data-icon search-data-animation-icon')])[1]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll2);
            boolean animation = tocSearch.getAnimation().isDisplayed();
            if (animation == false) {
                Assert.fail("Lesson is not displaying");
            }

            tocSearch.getTocSearch().clear();
            tocSearch.getDiscussionIcon().click();//click on discussion icon
            boolean discussion = tocSearch.getDiscussion().isDisplayed();
            if (discussion == false) {
                Assert.fail("Discussion is not displaying");
            }


        } catch (Exception e) {
            Assert.fail("Exception in test case searchSpaceForEachElement in class InstructorShouldBeAbleToSearchAndFilter", e);
        }
    }

    @Test(priority = 4, enabled = true)
    public void clickingOverASearchResult() {
        try {
            //tc row no 186
            TocSearch tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new LoginUsingLTI().ltiLogin("186");//login as instructor
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            Thread.sleep(2000);
            tocSearch.getTocSearch().sendKeys("mitosis ", Keys.chord(Keys.SHIFT, Keys.NUMPAD9) + "also, karyokinesis)");
            tocSearch.getSearch_Icon().click();
            WebElement scroll = driver.findElement(By.xpath("(//i[contains(@class,'search-data-icon search-data-img-icon')])[1]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll);
            tocSearch.getImage().click();//click on image
            new WebDriverWait(driver, 50).until(ExpectedConditions.presenceOfElementLocated(By.className("widget-close")));
            boolean imageCloseIcon = tocSearch.getImageClose_Icon().isDisplayed();
            if (imageCloseIcon == false) {
                Assert.fail("Image is not expanded after clicking on image icon");
            }

            tocSearch.getImageClose_Icon().click();//click on close icon on image
            new TOCShow().chaptertree();//click on toc
            WebElement scroll1 = driver.findElement(By.xpath("(//i[contains(@class,'search-data-icon search-data-lesson-icon')])[1]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll1);
            tocSearch.getLesson().click();//click on lesson icon
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.id("ch01-sec1-1")));
            boolean lessonText = tocSearch.getLessonText().isDisplayed();
            if (lessonText == false) {
                Assert.fail("Lesson is not expanded after clicking on lesson icon");
            }

            new TOCShow().chaptertree();//click on toc
            WebElement scroll2 = driver.findElement(By.xpath("(//i[contains(@class,'search-data-icon search-data-animation-icon')])[1]"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", scroll2);
            tocSearch.getAnimation().click();
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.className("widget-close")));
            boolean animationText = tocSearch.getImageClose_Icon().isDisplayed();
            if (animationText == false) {
                Assert.fail("Animation is not expanded after clicking on animation icon");
            }
            tocSearch.getImageClose_Icon().click();//click on close icon on image
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().clear();
            tocSearch.getDiscussionIcon().click();//click on discussion icon
            tocSearch.getDiscussion().click();//click on discussion
            new WebDriverWait(driver, 240).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("a[class='ls-content-post__footer-perspective-link js-toggle-comments']")));
            boolean perspective = tocSearch.getPerspective().isDisplayed();
            if (perspective == false) {
                Assert.fail("Discussion is not expanded after clicking on discussion icon");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case clickingOverASearchResult in class InstructorShouldBeAbleToSearchAndFilter", e);
        }
    }

    @Test(priority = 5, enabled = true)
    public void theSearchResultsMustHaveDifferentCategories() {
        try {
            //tc row no 193
            TocSearch tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new LoginUsingLTI().ltiLogin("193");//login as instructor
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            Thread.sleep(2000);
            tocSearch.getTocSearch().sendKeys("mitosis ", Keys.chord(Keys.SHIFT, Keys.NUMPAD9) + "also, karyokinesis)");
            tocSearch.getSearch_Icon().click();
            String chapterHeading = tocSearch.getChapterLevel().getText();
            Assert.assertEquals(chapterHeading, "Chapter Level", "ChapterLevel Heading is not displaying");
            String glossaryHeading = tocSearch.getGlossaryHeading().getText();
            Assert.assertEquals(glossaryHeading, "GLOSSARY: mitosis (also, karyokinesis)", "Glossary Heading is not displaying");

        } catch (Exception e) {
            Assert.fail("Exception in test case theSearchResultsMustHaveDifferentCategories in class InstructorShouldBeAbleToSearchAndFilter", e);
        }
    }
        @Test(priority = 7, enabled = true)
        public void theFormatForEveryCardEntryForAChapterLevelSearchResults () {
            try {
                //tc row no 202
                TocSearch tocSearch = PageFactory.initElements(driver, TocSearch.class);
                new LoginUsingLTI().ltiLogin("202");//login as instructor
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
                Assert.fail("Exception in test case theFormatForEveryCardEntryForAChapterLevelSearchResults in class InstructorShouldBeAbleToSearchAndFilter", e);
            }
        }
    @Test(priority = 8, enabled = true)
    public void theUserShouldBeAbleToFilterTheResultsAsPerTheImageElement() {
        try {
            //tc row no 212
            TocSearch tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new LoginUsingLTI().ltiLogin("212");//login as instructor
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



        } catch (Exception e) {
            Assert.fail("Exception in test case theUserShouldBeAbleToFilterTheResultsAsPerTheImageElement in class InstructorShouldBeAbleToSearchAndFilter", e);
        }
    }

    @Test(priority = 11, enabled = true)
    public void theUserShouldBeAbleToFilterTheResultsAsPerTheLessonElement() {
        try {
            //tc row no 224
            TocSearch tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new LoginUsingLTI().ltiLogin("224");//login as instructor
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            tocSearch.getTocSearch().sendKeys("mitosis ", Keys.chord(Keys.SHIFT, Keys.NUMPAD9) + "also, karyokinesis)");
            tocSearch.getSearch_Icon().click();//click on search
            tocSearch.getLessonIcon().click();//click on lesson icon
            boolean lessonSelected = tocSearch.getLessonSelected().isDisplayed();
            if (lessonSelected == false) {
                Assert.fail("The lesson filter icon is not highlighted");
            }
            Thread.sleep(5000);
            String lessonHeading = tocSearch.getGlossaryHeading().getText();
            Assert.assertEquals(lessonHeading, "1.1: The Science of Biology", "Filter result is not refreshed");



        } catch (Exception e) {
            Assert.fail("Exception in test case theUserShouldBeAbleToFilterTheResultsAsPerTheLessonElement in class InstructorShouldBeAbleToSearchAndFilter", e);
        }
    }

    @Test(priority = 12, enabled = true)
    public void theUserShouldBeAbleToFilterTheResultsAsPerTheDiscussionElement() {
        try {
            //tc row no 228
            TocSearch tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new LoginUsingLTI().ltiLogin("228");//login as instructor
            new TOCShow().chaptertree();//click on toc
            tocSearch.getTocSearch().click();//click on toc search
            tocSearch.getDiscussionIcon().click();//click on discussion icon
            boolean discussionSelected = tocSearch.getDiscussionSelected().isDisplayed();
            if (discussionSelected == false) {
                Assert.fail("The discussion filter icon is not highlighted");
            }

            String discussionHeading = tocSearch.getGlossaryHeading().getText();
            Assert.assertEquals(discussionHeading, "10.2: The Cell Cycle", "Filter result is not refreshed");


        } catch (Exception e) {
            Assert.fail("Exception in test case theUserShouldBeAbleToFilterTheResultsAsPerTheDiscussionElement in class InstructorShouldBeAbleToSearchAndFilter", e);
        }
    }

    @Test(priority = 13, enabled = true)
    public void theUserShouldBeAbleToFilterTheResultsAsPerTheAnimationElement() {
        try {
            //tc row no 232
            TocSearch tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new LoginUsingLTI().ltiLogin("232");//login as instructor
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

        } catch (Exception e) {
            Assert.fail("Exception in test case theUserShouldBeAbleToFilterTheResultsAsPerTheAnimationElement in class InstructorShouldBeAbleToSearchAndFilter", e);
        }
    }
    @Test(priority = 14, enabled = true)
    public void theUserMustBeAbleToToggleBetweenFilter() {
        try {
            //tc row no 236
            TocSearch tocSearch = PageFactory.initElements(driver, TocSearch.class);
            new LoginUsingLTI().ltiLogin("236");//login as instructor
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
            if (image == false) {
                Assert.fail("The previously selected filter icon is highlighted");
            }

        } catch (Exception e) {
            Assert.fail("Exception in test case theUserMustBeAbleToToggleBetweenFilter in class InstructorShouldBeAbleToSearchAndFilter", e);
        }
    }


    }

